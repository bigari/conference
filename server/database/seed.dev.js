var app = require('../server/server');

app.seeder.migrate('Speaker', 5)
	.then((presenters) => {
		console.log(presenters);
		app.seeder.migrate('Conference', 3).then((_) => {
			app.seeder.migrate('Enquete', 2).then((_) => {
				app.seeder.migrate('Option', 4).then((_) => {
					app.seeder.migrate('Option', 3, {'enqueteId': 2}).then((_) => {
						app.seeder.migrate('Participant', 26).then((_)=>{
							let optionId = 1;
							for (let i = 5; i <= 26; i++) {
								optionId = (i%7)+1;
								app.seeder.migrate(
									'Vote',
									 1,
									{
										'participantId': i,
										'optionId': optionId,
										'enqueteId': optionId<=4?1:2
									}
								)
							}
						});
					});
				});
			});
		});
	});
