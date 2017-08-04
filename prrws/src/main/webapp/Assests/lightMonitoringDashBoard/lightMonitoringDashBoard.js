getLedOverviewForStartedLocationsDetailsCounts();
getBasicLedOverviewDetails();
function getLedOverviewForStartedLocationsDetailsCounts(){
	var json = {
	}
	$.ajax({                
		type:'POST',    
		url: 'getLedOverviewForStartedLocationsDetailsCounts',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
	});
	
}
function getBasicLedOverviewDetails(){
		var json = {
		}
		$.ajax({                
			type:'POST',    
			url: 'getBasicLedOverviewDetails',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		});
		
}