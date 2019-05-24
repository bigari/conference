// module.exports = function (app) {
//   const bodyParser = require('body-parser')
//   app.use(bodyParser.json())
//   app.post('/api/login', function(req, res) {
//     console.log(">>>>>>>>>>>>REQ>>>>>>>>>")
//     console.log(req)
//     console.log(req.body)
//     app.models.User.login({
//       email: req.body.email,
//       password: req.body.password
//     }, 'user', function(err, token) {
//       if (err) {
//         res.render('response', { //render view named 'response.ejs'
//           title: 'Login failed',
//           content: err,
//           redirectTo: '/',
//           redirectToLinkText: 'Try again'
//         });
//         return;
//       }

//       res.json({
//         email: req.body.email,
//         accessToken: token.id
//       });
//     });
//   });
// }

// module.exports = function (app) {
//   app.get('/users/:id/conferences', function(req, res) {
    
//   });
// }