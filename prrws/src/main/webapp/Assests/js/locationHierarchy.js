function getAllSubLocations(divId,levelId,locationScopeId){
	//var type = 'constituency' //district to constituency (only consider type like this)
	alert(1);
	var json = {
		searchLevelId		: levelId,
		searchLevelValue	: locationScopeId,
		type 				: ""		  
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
			collapseMenu(levelId,result,divId)
		}
	});
}
function collapseMenu(id,resultArr,buildId)
{
	var collapse = '';
	collapse+='<div class="panel-group dashedBorder" id="accordion'+id+'" role="tablist" aria-multiselectable="true">';
	for(var i in resultArr)
	{
		
		collapse+='<div class="panel panel-default panelExpand">';
			collapse+='<div class="panel-heading" role="tab" id="heading'+i+''+resultArr[i]+'">';
				collapse+='<h4 class="panel-title">';
					if(i == 0)
					{
						collapse+='<a role="button" attr_targetId="collapseMenu'+resultArr[i]+'Id'+i+'" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+[id]+'" href="#collapse'+i+''+resultArr[i]+'" aria-expanded="true" aria-controls="collapse'+i+''+resultArr[i]+'">';
					}else
					{
						collapse+='<a role="button" attr_targetId="collapseMenu'+resultArr[i]+'Id'+i+'"  class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion'+[id]+'" href="#collapse'+i+''+resultArr[i]+'" aria-expanded="true" aria-controls="collapse'+i+''+resultArr[i]+'">';
					}
					
						collapse+='<span style="padding-left:20px;">'+resultArr[i]+'</span>';
					collapse+='</a>';
				collapse+='</h4>';
			collapse+='</div>';
			if(i == 0)
			{
				collapse+='<div id="collapse'+i+''+resultArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+''+resultArr[i]+'">';
			}else{
				collapse+='<div id="collapse'+i+''+resultArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+''+resultArr[i]+'">';
			}
				collapse+='<div class="panel-body">';
					collapse+='<div class="collapseMenu'+resultArr[i]+'Id'+i+'"></div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
		
	}
	collapse+='</div>';
	$("#"+buildId).html(collapse);
}
