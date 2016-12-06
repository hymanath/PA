    getAlertOverviewDetails();
	function getAlertOverviewDetails(){
		var jsObj={  
			activityMemberId : 44,      
			stateId : globalStateId,           
			fromDate :"",        
			toDate :""    
		};
		$.ajax({
			type : 'GET',
			url : 'getAlertOverviewDetailsAction.action',
			dataType : 'json',  
			data : {task :JSON.stringify(jsObj)}          
		}).done(function(result){
           console.log(result);
		});	
           
	}