//GOOGLE TRANSLATOR -- START
	google.load("elements", "1", {
		packages: "transliteration"
	});  
		
	var control ;		
	function onLoad() {
		var options = {
			sourceLanguage:
			  google.elements.transliteration.LanguageCode.ENGLISH,
			destinationLanguage:
			  [google.elements.transliteration.LanguageCode.TELUGU],
			shortcutKey: 'alt+t',
			transliterationEnabled: true
		};

		// Create an instance on TransliterationControl with the required
		// options.
		control =
			new google.elements.transliteration.TransliterationControl(options);

		// Enable transliteration in the textbox with id
		// 'descrptionId'.
		control.makeTransliteratable(['voterTeluguNameId']);
		
	}
	google.setOnLoadCallback(onLoad);
	//GOOGLE TRANSLATOR -- END