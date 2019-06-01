module.exports = function (app) {
  app.get('/api/conferences/join', function(req, res) {
    if(!req.query.email){
      res.status(500).json({ error: 'The email field is required.' });
      return;
    }
    if(!req.query.accesscode){
      res.status(500).json({ error: 'The access code field is required.' });
      return;
    }

    app.models.Speaker.findOne(
    {
      where: {email: req.query.email},
      include: {
        relation: 'conferences',
        scope: {
          where:{
            codeAcces: req.query.accesscode,
          },
        }
      }
    }, function(err, result) {
        if(!result){
          res.status(404).json({ error: 'wrong email' });
          return;
        }
        let conferences = result.toJSON().conferences;
        if(conferences.length == 0){
          res.status(404).json({ error: 'wrong access code' });
          return;
        }
        res.status(200).json(conferences[0]);
        return;
    })
  });


  app.post('/api/enquetes/:enqueteId/vote', function(req, res) {
    app.models.Participant.findOne(
      {
        where: {
          id: req.body.participantId
        },
        include: {
          relation: "votes",
          scope: {
            where: {enqueteId: req.params.enqueteId}
          }
        }
      }, function (err, result) {
        if(!result) {
          res.status(404).json({error: 'No participant'});
          return;
        }
        let participant = result.toJSON()
        if (participant.accessKey != req.body.accessKey) {
          res.status(401).json({error: 'Unhautorized'});
          return;
        }
        let votes = participant.votes;
        let vote = {
          participantId: req.body.participantId,
          optionId: req.body.optionId,
          enqueteId: req.params.enqueteId
        }

        if (votes.length == 0 ) {
          app.models.Vote.create(
            vote, function (err, result) {}
          );
          res.status(200).json({status: "created"});
        } else {
          vote.id = votes[0].id;
          app.models.Option.findOne({
            where: {id: vote.optionId},
            include: {
              relation: 'enquete'
            }
          },
          (err, result) => {
             if (result.toJSON().enquete.id != vote.enqueteId) {
                res.status(400).json({error: 'Bad Request'})
             } else {
                app.models.Vote.upsert(
                  vote, function (err, result) {}
                );
                res.status(200).json({status: "updated"});
             }
          })
        }
      }
    )
  });

  app.get('/api/conferences/:conferenceId/enquetes/stats', function (req, res){
    app.models.Conference.findOne(
      {
        where: {
          id: req.params.conferenceId
        },
        include: {
          relation: 'enquetes',
          scope: {
            include: {
              relation: 'options',
              scope: {
                include:  {
                   relation: 'votes'
                 }
                // },
                // fields: {id: true, optionId: false, participantId: false}
              }
            }
          }
        }
      }, function (err, result) {
        if (!result) {
          res.status(404).json({error: 'No active surveys'})
          return;
        }
        let enquetes = result.toJSON().enquetes;
        let stats = []; 
        let i = 0;
        let j = 0;
        enquetes.forEach((enquete) => {
          j=0;
          let options = [];
          enquete.options.forEach((option)=>{
            options[j++] = {
              id: option.id,
              voteCount: option.votes.length
            }
          });
          stats[i++] = {
            id: enquete.id,
            options: options
          }
        })
        console.log('completed');
        res.status(200).json(stats);
      }
    )
  });
}