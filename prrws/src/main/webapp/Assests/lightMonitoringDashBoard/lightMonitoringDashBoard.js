
getStatusByLightMonitoringOverview();
getStatusByLightMonitoringVillagesData();


function getStatusByLightMonitoringOverview(){
		//$("#DepartmentsId").html('');
		var json = {

		}
		$.ajax({                
			type:'POST',    
			url: 'getStatusByVillages',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		});
		
}
function getStatusByLightMonitoringVillagesData(){
	//$("#DepartmentsId").html('');
	var json = {

	}
	$.ajax({                
		type:'POST',    
		url: 'getVillageIdBasedDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		
	});
	
}
saveRealtimeStatusByVillages();
function saveRealtimeStatusByVillages(){
		var json = {

		}
		$.ajax({                
			type:'POST',    
			url: 'saveRealtimeStatusByVillages',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
		});
		
}