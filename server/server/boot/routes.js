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
            codeAcces: req.query.accesscode
          },
        }
      }
    }, function(err, result) {
        if(!result){
          res.status(404).json({ error: 'wrong email' });
          return;
        }
        var conferences = result.toJSON().conferences;
        if(conferences.length == 0){
          res.status(404).json({ error: 'wrong access code' });
          return;
        }
        res.status(200).json(conferences[0]);
        return;
    })
  });
}