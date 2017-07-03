function getAllSubLocations(divId,levelId){
		//$("#distNames").html('');
        //var type = 'constituency' //district to constituency (only consider type like this)
		var locationScopeId = 22;
		var json = {
		  searchLevelId:levelId,
		  searchLevelValue : locationScopeId,
           type : ""		  
		}
		$.ajax({
			url : "getAllSubLocations",     
			data : JSON.stringify(json),
			type : "POST",  
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(result){   
				if(result !=null && result.length>0){
					 $("#distNames").append('<option value="0">SELECT district</option>');
					for(var i in result){
						$("#distNames").append('<option value="'+result[i].id+'">'+result[i].name+' </option>');
					}
				}
				$("#distNames").trigger('chosen:updated');
			}
		});
	}