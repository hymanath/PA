
getStatusByLightMonitoringOverview();
getStatusByLightMonitoringVillagesData();
getDistrictLevelCountData();

function getStatusByLightMonitoringOverview(){
		//$("#DepartmentsId").html('');
	alert(1);
		var json = {

		}
		$.ajax({                
			type:'POST',    
			url: 'getRealtimeStatusByVillages',
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
function getDistrictLevelCountData(){
	//$("#DepartmentsId").html('');
	alert("hello");
	var json = {

	}
	$.ajax({                
		type:'POST',    
		url: 'getDistrictLevelCount',
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