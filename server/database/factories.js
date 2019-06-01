var app = require('../server/server');

function genCode(len) {
	let accessCode = "";
	let codeAlph = 
	[
		'0','1','2','3',
		'4','5','6','7',
		'8','9','a','b',
		'c','d','e','f',
		'g','h','i','j',
		'k','l','m','n',
		'o','p','q','r',
		's','t','u','v',
		'w','x','y','z'
	];
	
	for (var i = 0; i < len; i++) { 
		let rndNum = Math.ceil(Math.random() * codeAlph.length) - 1;
		accessCode = accessCode + codeAlph[rndNum];
	};
	console.log(accessCode)
	return accessCode;
}

app.seeder.createFactory('Speaker', {
  'username': '{{internet.userName}}',
  'email': '{{internet.email}}',
  'password': '000000'
});

app.seeder.createFactory('Participant', {
  'accessKey': '1234',
});

app.seeder.createFactory('Conference', {
	'nom': '{{lorem.word}}',
    'dateDebut': '{{date.recent}}',
    'dateFin': '{{date.future}}',
    'codeAcces': genCode(5),
    'speakerId': 1
});

app.seeder.createFactory('Enquete', {
  'intituleEnquete': '{{lorem.word}}',
  'etat': true,
  'conferenceId': 1
});

app.seeder.createFactory('Option', {
  'intituleOption': '{{lorem.word}}',
  'enqueteId': 1
});

app.seeder.createFactory('Vote', {
	'optionId': 1,
	'participantId': 1,
	'enqueteId': 1
});