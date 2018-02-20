 getDesignationWiseTourSubmittedOverviewDtls();
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
	