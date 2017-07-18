var stateArr = [{'name':'Andhra Pradesh','id':1}];
collapseMenu(1,stateArr,'multi-level-selection-menu');
function getAllSubLocations(divId,levelId,locationScopeId,type){
	$("."+divId).html(spinner);
	//var type = 'constituency' //district to constituency (only consider type like this)
	var json = {
		searchLevelId		: levelId,
		searchLevelValue	: locationScopeId,
		type 				: type		  
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
	if(id == 2)
	{
		levelIdValue = 3;
	}else if(id == 3)
	{
		levelIdValue = 4;
	}else if(id == 4){
		levelIdValue = 5;
	}else{
		levelIdValue = 2;
	}
	var collapse = '';
	
	collapse+='<div class="panel-group dashedBorder" id="accordion'+id+'" role="tablist" aria-multiselectable="true">';
	for(var i in resultArr)
	{
		collapse+='<div class="panel panel-default panelExpand">';
			collapse+='<div class="panel-heading" role="tab" id="heading'+resultArr[i].id+'">';
				collapse+='<h4 class="panel-title">';
					if(levelIdValue == 2 || levelIdValue == 3)
					{
						collapse+='<a role="button" style="height:10px;width:10px;display:inline-block;" attr_levelIdValue="'+levelIdValue+'" attr_levelId="'+id+'" attr_id="'+resultArr[i].id+'" attr_targetId="collapseMenu'+resultArr[i].id+'Id"  class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion'+[id]+'" href="#collapse'+resultArr[i].id+'" aria-expanded="true" aria-controls="collapse'+resultArr[i].id+'">&nbsp;</a>';
					}
					collapse+='<span style="padding-left:10px;cursor:pointer;" class="menuDataCollapse"  attr_levelIdValue="'+levelIdValue+'" attr_levelId="'+id+'" attr_id="'+resultArr[i].id+'" attr_targetId="collapseMenu'+resultArr[i].id+'Id" >'+resultArr[i].name+'</span>';
				collapse+='</h4>';
			collapse+='</div>';
			collapse+='<div id="collapse'+resultArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+resultArr[i].id+'">';
				collapse+='<div class="panel-body">';
					collapse+='<div class="collapseMenu'+resultArr[i].id+'Id"></div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	}
	collapse+='</div>';
	$("."+buildId).html(collapse);
}

$(document).on("click",".panelCollapseIcon",function(e){
	e.stopPropagation();
	var buildId = $(this).attr("attr_targetId");
	var locationScopeId = $(this).attr("attr_id");
	var levelId = $(this).attr("attr_levelIdValue");
	
	var type = '';
	if(levelId == 4)
	{
		type='';
		locationScopeId = locationScopeId;
	}
	if(levelId == "3" || levelId == 3)
	{
		type='constituency';
	}
	getAllSubLocations(buildId,levelId,locationScopeId,type)
});
$(".multi-level-selection-menu").hide();
$(document).on("click",function(){
	$(".multi-level-selection-menu").hide();
});
$(document).on("click","#selectedName",function(e){
	e.stopPropagation();
	$(".multi-level-selection-menu").toggle();
});