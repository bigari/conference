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
	
	let codeLength = len;

	for (var i = 0; i < codeLength; i++) { 
		let rndNum = Math.ceil(Math.random() * codeAlph.length) - 1;
		accessCode = accessCode + codeAlph[rndNum];
	};
	console.log(accessCode)
	return accessCode;
}

app.seeder.createFactory('User', {
  'username': '{{internet.userName}}',
  'email': '{{internet.email}}',
  'password': '000000'
});

app.seeder.createFactory('Conference', {
	'nom': '{{lorem.word}}',
    'dateDebut': '{{date.recent}}',
    'dateFin': '{{date.future}}',
    'codeAcces': genCode(5),
    'userId': 1
});
