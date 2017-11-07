//getSolidInfoLocationWise();
function getSolidInfoLocationWise()
{
	
	var json ={
		
	}
	$.ajax({                
		type:'Post',    
		url: 'getSolidInfoLocationWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
	});
}
//getSolidWasteManagementOverAllCounts(3,19);
function getSolidWasteManagementOverAllCounts(locId,locationType){
	
	
	var json = {
			fromDate : "",
			toDate : "",
			locationType : "19" ,
			locationId:locId
		}
		$.ajax({                
			type:'POST',    
			url: 'getSolidWasteManagementOverAllCounts',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			
			if(result != null){
				
			}
		});
}