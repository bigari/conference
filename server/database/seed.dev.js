var app = require('../server/server');

app.seeder.migrate('User', 5)
	.then((users) => {
		console.log(users);
		app.seeder.migrate('Conference', 3);
	});
