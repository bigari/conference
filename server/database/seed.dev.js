var app = require('../server/server');

app.seeder.migrate('Speaker', 5)
	.then((presenters) => {
		console.log(presenters);
		app.seeder.migrate('Conference', 3);
	});
