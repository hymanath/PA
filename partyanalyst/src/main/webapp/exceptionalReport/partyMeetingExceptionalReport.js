 getPartyMeetingExceptionReportMeetingLevelWise();
 function getPartyMeetingExceptionReportMeetingLevelWise()
	{    
	 	var jsObj = { 
					 partyMeetingLevel : "mandalTownDivision",
					 fromDate : "01/01/2018",
					 toDate : "31/01/2018",
					 partyMeetingTypeIds:[15],
					 
				  }
		$.ajax({
			type : 'POST',
			url : 'getPartyMeetingExceptionReportMeetingLevelWiseAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		      console.log(result);
		});
	}
	