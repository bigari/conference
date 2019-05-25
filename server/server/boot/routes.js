module.exports = function (app) {
  app.get('/api/conferences/join', function(req, res) {
    app.models.Speaker.findOne(
    {
      where: {email: req.query.email},
      include: {
        relation: 'conferences',
        scope: {
          where:{
            codeAcces: req.query.codeacces
          },
          include: ['enquetes', 'questionnaires', 'questions']
        }
      }
    }, function(err, speaker) {
        let speakerJson = speaker.toJSON()
        if (speaker == null) {
          res.status(404)
          res.json({
            status: 404,
            error: "No speaker found with the given email"
          })
        } else {
          if(speakerJson.conferences.length == 0) {
            res.status(404)
            res.json({
              status: 404,
              error: "Wrong access code"
            })
          } else {
            res.json (
              speakerJson.conferences[0]
            )
          }
        }
    })
  });
}