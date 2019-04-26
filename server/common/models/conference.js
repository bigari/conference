'use strict';

module.exports = function(Conference) {
	// TODO- Add validation
	Conference.beforeRemote('create', function(ctx, modelInstance, next){
		let accessCode = "";
		let codeAlph = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
		let codeLength = 5;

		for (var i = 0; i < codeLength; i++) { 
			var rndNum = Math.ceil(Math.random() * codeAlph.length) - 1;
			accessCode = accessCode + codeAlph[rndNum];
		};
		ctx.args.data.codeAcces = accessCode;
		console.log(ctx.args);
		next();
	});


};
