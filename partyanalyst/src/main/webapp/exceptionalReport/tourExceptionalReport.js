 getDesignationWiseTourSubmittedOverviewDtls();
 getNotSubmittedCandidateDetailsByFilter();
 function getDesignationWiseTourSubmittedOverviewDtls()
	{    
	 	var jsObj = { 
	 				 stateId : "1",
					 fromDate : "01/01/2018",
					 toDate : "31/01/2018"
				   }
		$.ajax({
			type : 'POST',
			url : 'getDesignationWiseTourSubmittedOverviewDtlsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		      console.log(result);
		});
	}
 
 function getNotSubmittedCandidateDetailsByFilter()
	{    
	 	var jsObj = { 
	 				 stateId : "1",
					 fromDate : "01/09/2017",
					 toDate : "31/01/2018",
					 noOfMonth:2
				   }
		$.ajax({
			type : 'POST',
			url : 'getNotSubmittedCandidateDetailsByFilterAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
		      console.log(result);
		});
	}
	
 