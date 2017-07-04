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
	//getALlProgramesAmountDetails();
	function getALlProgramesAmountDetails(){
		var financialYearIdsList =[];
		financialYearIdsList.push(1);
		financialYearIdsList.push(3);
		financialYearIdsList.push(2);
		var deptIdsList=[];
		var sourceIdsList=[];
		var fromDateStr="01/01/1997";
		var toDateStr="01/01/2027";
		var glSearchLevelId=0;
		var glSearchLevelValue=[];
		
		var json = {
		  financialYrIdList:financialYearIdsList,
		  deptIdsList : deptIdsList,
          sourceIdsList : sourceIdsList,
		  fromDateStr:fromDateStr,
		  toDateStr:toDateStr,
		  searchLevelId:glSearchLevelId,
		  searchLvlVals:glSearchLevelValue
		}
		$.ajax({
			url : "getALlProgramesAmountDetails",     
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