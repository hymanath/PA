var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = '2017-04-01'//moment().startOf('year').format("YYYY-MM")+'-1';
var glEndDate = moment().format("YYYY-MM")+'-30';
var overViewArr = [];
//var overViewArr = [{"name":'Labour Budget',"id":"15"},{"name":'Farm Ponds',"id":"15"},{"name":'IHHL',"id":"15"},{"name":'Vermi Compost',"id":"15"},{"name":'Solid Waste Management',"id":"15"},{"name":'Burial Grounds',"id":"15"},{"name":'Play Fields',"id":"15"},{"name":'Agriculture Activities',"id":"15"},{"name":'Average Wage',"id":"15"},{"name":'Average Days of Employment',"id":"15"},{"name":'HH Completed 100 Days',"id":"15"},{"name":'Timely Payment',"id":"15"},{"name":'CC Roads',"id":"15"},{"name":'Anganwadi Buildings',"id":"15"},{"name":'GP Buildings',"id":"15"},{"name":'Mandal Buildings',"id":"15"},{"name":'NTR 90 Days',"id":"15"},{"name":'Production of Bricks',"id":"15"},{"name":'Mulbery',"id":"15"},{"name":'Silk Worms',"id":"15"},{"name":'Cattle Drinking Water Troughs',"id":"15"},{"name":'Raising of Perinnial Fodders',"id":"15"},{"name":'Horticulture',"id":"15"},{"name":'Avenue',"id":"15"},{"name":'Fish Ponds',"id":"15"},{"name":'Fish Drying Platforms',"id":"15"},{"name":'Nurseries',"id":"15"},{"name":'Payments',"id":"15"},{"name":'FAperformance',"id":"15"}];
var overViewIdsArr = [];
$("#projectsOverview").html(spinner);
$("#dateRangePickerMGNF").val('2017-04-01');
$("#dateRangePickerMGNT").val(moment().format("YYYY-MM")+'-30');


$("#dateRangePickerMGNF").datetimepicker({
	format: 'YYYY-MM',
	viewMode:'months'
});
$("#dateRangePickerMGNT").datetimepicker({
	format: 'YYYY-MM',
	viewMode:'months'
});
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
$('#dateRangePickerMGNF').on('dp.change', function(e){ 
	glStartDate = e.date.format("YYYY-MM")+"-31";
	var locId = $("#selectedName").attr("attr_id");
	var levelId = $("#selectedName").attr("attr_levelid");
	buildNREGSProjectsOverview(overViewArr,'',locId,levelId);
	projectData(levelId,locId);
});
$('#dateRangePickerMGNT').on('dp.change', function(e){ 
	glEndDate = e.date.format("YYYY-MM")+"-31";
	var locId = $("#selectedName").attr("attr_id");
	var levelId = $("#selectedName").attr("attr_levelid");
	buildNREGSProjectsOverview(overViewArr,'',locId,levelId);
	projectData(levelId,locId);
});

onLoadCalls()
function onLoadCalls()
{
	getAllConvergenceTypes();
}

$(document).on("click","[collapse-click]",function(){
	var divId = $(this).attr("collapse-click");
	var locId = $("#selectedName").attr("attr_id");
	var levelId = $("#selectedName").attr("attr_levelid");
	var locationType = '';
	if(levelId == 2)
	{
		locationType = 'state';
		subLocType = 'state';
		divId = $(this).attr("overview-level");
	}else if(levelId == 3)
	{
		locationType = 'district';
		subLocType = 'district';
		divId = $(this).attr("overview-level");
	}else if(levelId == 4)
	{
		locationType = 'district';
		subLocType = 'constituency';
		divId = $(this).attr("overview-level");
	}
	if(divId == 'district')
	{
		subLocType = 'district';
	}
	else if(divId == 'constituency')
	{
		subLocType = 'constituency';
	}else if(divId == 'mandal')
	{
		subLocType = 'mandal';
	}
	if(divId != "state")
	{
		getNREGSLevelWiseConsolidatedReport(levelId,locationType,subLocType,locId,divId);
	}
});
$(document).on("click",".menuDataCollapse",function(){
	$(".multi-level-selection-menu").css("display","none");
	$(".arrowIconChanged").find('.fa').removeClass("fa-chevron-up");
	$(".arrowIconChanged").find('.fa').addClass("fa-chevron-down");
	$("#projectData,#projectOverviewBlock").html('');
	var blockName = '';
	$(".panel-block-white").each(function(){
		if($(this).hasClass("active"))
		{
			blockName = $(this).attr("overview-block");
		}
	});
	$(".panel-block-white").removeClass("active");
	var locId = $(this).attr("attr_id");
	$("#selectedName").html($(this).html())
	var districtId = $("#selectedName").attr("attr_distId");
	var levelId = $(this).attr("attr_levelIdValue");
	$("#selectedName").attr("attr_levelid",levelId);
	$("#selectedName").attr("attr_id",locId);
	buildNREGSProjectsOverview(overViewArr,'',locId,levelId);
	projectData(levelId,locId);
});
$(".menu-top-selection .arrow_box_top").hide();
$(document).on("click",".menu-top-selection-icon",function(e){
	e.stopPropagation();
	$(".menu-top-selection .arrow_box_top").show();
});
$(document).on("click",".menu-top-selection",function(e){
	e.stopPropagation();
});
$(document).on("click",".panelCollapseIconClick",function(e){
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
		locationScopeId = $(this).attr("attr_distid");
		type='constituency';
		$("#selectedName").attr("attr_distid",locationScopeId);
	}
	getAllNregaSubLocationDetails(buildId,levelId,locationScopeId,type)
});
$(document).on("click",".selectionMenuSubmitIdNewCls",function(){
	overViewIdsArr = [] ;
	overViewArr = [];
	
	$(".menuSelectionCheckBox").each(function(){
		var checkboxId = $(this).attr("checkboxId");
		if($(this).prop('checked')==true)
		{
			var checkboxName = $(this).attr("checkboxName");
			overViewArr.push({"name":checkboxName,"id":checkboxId});
			overViewIdsArr.push(checkboxId);
		}
	});
	
	var locId = $("#selectedName").attr("attr_id");
	var levelId = $("#selectedName").attr("attr_levelid");
	setTimeout(function(){
		buildNREGSProjectsOverview(overViewArr,'',locId,levelId);
		projectData(levelId,locId);
	},400)
	
});
/* Menu Start*/
$(".multi-level-selection-menu").hide();
$(document).on("click",function(){
	$(".multi-level-selection-menu,.menu-top-selection .arrow_box_top").hide();
});
$(document).on("click","#selectedName",function(e){
	e.stopPropagation();
	$(".multi-level-selection-menu").show();
});

var stateArr = [{'name':'Andhra Pradesh','type':1}];
collapseMenu(1,stateArr,'multi-level-selection-menu');
function getAllNregaSubLocationDetails(divId,levelId,locationScopeId,type){
	$("."+divId).html(spinner);
	//var type = 'constituency' //district to constituency (only consider type like this)
	var json = {
		searchLevelId		: levelId,//3
		menuLvelValue		: locationScopeId,//"03"
		type 				: type//"constituency"//		  
	}
	$.ajax({
		url : "getAllNregaSubLocationDetails",     
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
	}else{
		levelIdValue = 2;
	}
	var collapse = '';
	
	collapse+='<div class="panel-group dashedBorder" id="accordion'+id+'" role="tablist" aria-multiselectable="true">';
	for(var i in resultArr)
	{
		collapse+='<div class="panel panel-default panelExpand">';
			collapse+='<div class="panel-heading" role="tab" id="heading'+resultArr[i].type+'">';
				collapse+='<h4 class="panel-title">';
					if(levelIdValue == 2 || levelIdValue == 3)
					//if(levelIdValue == 2)
					{
						collapse+='<a role="button" style="height:10px;width:10px;display:inline-block;" attr_levelIdValue="'+levelIdValue+'" attr_distId="'+resultArr[i].type+'" attr_levelId="'+id+'" attr_id="'+resultArr[i].type+'" attr_targetId="collapseMenu'+resultArr[i].type+'Id"  class="panelCollapseIcon panelCollapseIconClick collapsed" data-toggle="collapse" data-parent="#accordion'+id+'" href="#collapse'+resultArr[i].type+'" aria-expanded="true" aria-controls="collapse'+resultArr[i].type+'">&nbsp;</a>';
					}
					collapse+='<span style="padding-left:10px;cursor:pointer;" class="menuDataCollapse"  attr_levelIdValue="'+levelIdValue+'" attr_distid="" attr_levelId="'+id+'" attr_id="'+resultArr[i].type+'" attr_targetId="collapseMenu'+resultArr[i].type+'Id" >'+resultArr[i].name+'</span>';
				collapse+='</h4>';
			collapse+='</div>';
			collapse+='<div id="collapse'+resultArr[i].type+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+resultArr[i].type+'">';
				collapse+='<div class="panel-body">';
					collapse+='<div class="collapseMenu'+resultArr[i].type+'Id"></div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	}
	collapse+='</div>';
	$("."+buildId).html(collapse);
}

/* Menu End*/
function getAllConvergenceTypes()
{
	var json = {
	}
	
	$.ajax({
		url: 'getAllConvergenceTypes',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildAllConvergenceTypes(ajaxresp);
		}
	});
}
function buildAllConvergenceTypes(result)
{
	var selectionMenu = '';
	selectionMenu+='<div class="navTabsMenuSelection">';
		selectionMenu+='<div class="col-sm-6">';
			selectionMenu+='<ul class="nav nav-tabs" role="tablist">';
				for(var i in result)
				{
					if(i == 0)
					{
						selectionMenu+='<li role="presentation" class="active"><a href="#selectionMenuId'+result[i].id+'" aria-controls="selectionMenuId'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+'</a></li>';
					}else{
						selectionMenu+='<li role="presentation"><a href="#selectionMenuId'+result[i].id+'" aria-controls="selectionMenuId'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+'</a></li>';
					}
				}
			selectionMenu+='</ul>';
		selectionMenu+='</div>';
		selectionMenu+='<div class="col-sm-6">';
			selectionMenu+='<div class="tab-content">';
				for(var i in result)
				{
					if(i == 0)
					{
						selectionMenu+='<div role="tabpanel" class="tab-pane active" id="selectionMenuId'+result[i].id+'">A'+result[i].id+'</div>';
					}else{
						selectionMenu+='<div role="tabpanel" class="tab-pane" id="selectionMenuId'+result[i].id+'">A'+result[i].id+'</div>';
					}
				}
				
			selectionMenu+='</div>';
			selectionMenu+='<button class="btn btn-success m_top20 btn-sm selectionMenuSubmitIdNewCls" type="button" id="selectionMenuSubmitIdNew">SUBMIT</button>';
		selectionMenu+='</div>';
	selectionMenu+='</div>';
	$("#navTabsMenuSelectionId").html(selectionMenu);
	for(var i in result)
	{
		var convergenceId = result[i].id;
		var divId = 'selectionMenuId'+result[i].id;
		getComponentByConvergType(convergenceId,divId)
	}
}
function getComponentByConvergType(convergenceId,divId)
{
	$("#"+divId).html(spinner);
	var json = {
		convergenceTypeId : convergenceId
	}
	
	$.ajax({
		url: 'getComponentByConvergType',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildComponentByConvergType(ajaxresp,divId,convergenceId);
		}
	});
}
function buildComponentByConvergType(result,divId,convergenceId)
{
	var selectionMenu = '';
	
	selectionMenu+='';
	selectionMenu+='<ul class="menu-selection-body">';
		for(var i in result)
		{
			selectionMenu+='<li>';
				selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>'+result[i].name+'</label>';
			selectionMenu+='</li>';
			overViewArr.push({"name":result[i].name,"id":result[i].id});
			overViewIdsArr.push(result[i].id);
		}		
	selectionMenu+='</ul>';
	$("#"+divId).html(selectionMenu);
	if(convergenceId == 4)
	{
		setTimeout(function(){
			var locId = $("#selectedName").attr("attr_id");
			var levelId = $("#selectedName").attr("attr_levelid");
			buildNREGSProjectsOverview(overViewArr,'',locId,levelId);
			projectData(levelId,locId);
		},500);
	}
}

function buildNREGSProjectsOverview(result,blockName,locId,levelId)
{
	var str='';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<div class=" bg_color"  style="border: 5px solid #fff;padding:15px;">';
				str+='<h4 class="text-center m_top10"><b>NON-CONVERGENCE</b></h4>';
					str+='<div class="row">';
						str+='<div class="col-sm-12">';
							str+='<div class="block-border">';
								str+='<h5 class="text-danger">Labour Budget</h5>';
								str+='<div class="row">';	
									for(var i in result)
									{
										if(result[i].name == "Labour Budget" || result[i].name == "Average Wage" || result[i].name == "Average Days of Employment" || result[i].name == "HH Completed 100 Days" || result[i].name == "Timely Payment")
										{
											str+='<div class="col-sm-2 m_top10">';
												str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';
													if(result[i].name.length > 12)
													{
														str+='<h4 class="panel-block-white-title text-capitalize toolTipTitleCls text-center" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
													}else{
														str+='<h4 class="panel-block-white-title text-capitalize toolTipTitleCls text-center">'+result[i].name+'</h4>';
													}
												str+='</div>';
											str+='</div>';
										}
									}
								str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row">';
					for(var i in result)
					{
						if(result[i].name == "Farm Ponds" || result[i].name == "IHHL" || result[i].name == "Vermi Compost" || result[i].name == "Solid Waste Management" || result[i].name == "Play Fields" || result[i].name == "Burial Grounds" || result[i].name == "Agriculture Activities" || result[i].name == "Payments" || result[i].name == "FAperformance"){
							str+='<div class="col-sm-2 m_top10">';
							if(result[i].name == "FAperformance"){
								str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';	
									str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="Field Assistant Performance">FA Performan..</h4>';
								str+='</div>';
							}
							else{
								str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';	
									if(result[i].name.length > 12)
									{
										str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
									}else{
										str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
									}
								str+='</div>';
							}
							str+='</div>';
						}
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-12">';
			str+='<div class=" bg_color"  style="border: 5px solid #fff;padding:15px;">';
				str+='<h4 class="m_top10 text-center"><b>CONVERGENCE-PR DEPTS</b></h4>';
				str+='<div class="row">';
					for(var i in result)
					{
						if(result[i].name == "CC Roads" || result[i].name == "Anganwadi Buildings" || result[i].name == "GP Buildings" || result[i].name == "Mandal Buildings"){
							str+='<div class="col-sm-2 m_top10">';
								str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';	
									if(result[i].name.length > 12)
									{
										str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
									}else{
										str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
									}
								str+='</div>';
							str+='</div>';
						}
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-12">';
			str+='<div class=" bg_color"  style="border: 5px solid #fff;padding:15px;">';
				str+='<h4 class="m_top10 text-center"><b>CONVERGENCE-OTHER DEPTS</b></h4>';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-4">';
						str+='<div class="block-border">';
							str+='<h5 class="text-danger">Housing</h5>';
							str+='<div class="row">';	
								for(var i in result)
								{
									if(result[i].name == "NTR 90 Days" || result[i].name == "Production of Bricks"){
										str+='<div class="col-sm-6 m_top10">';
											str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';	
												if(result[i].name.length > 12)
												{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
												}else{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
												}
											str+='</div>';
										str+='</div>';
									}
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="block-border">';
							str+='<h5 class="text-danger">Sericulture</h5>';
							str+='<div class="row">';	
								for(var i in result)
								{
									if(result[i].name == "Mulbery" || result[i].name == "Silk Worms" ){
										str+='<div class="col-sm-6 m_top10">';
											str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';	
												if(result[i].name.length > 12)
												{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
												}else{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
												}
											str+='</div>';
										str+='</div>';
									}
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="block-border">';
							str+='<h5 class="text-danger">Animal Husbandry</h5>';
							str+='<div class="row">';	
								for(var i in result)
								{
									if(result[i].name == "Cattle Drinking Water Troughs" || result[i].name == "Raising of Perinnial Fodders"){
										str+='<div class="col-sm-6 m_top10">';
											str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';
												if(result[i].name.length > 12)
												{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
												}else{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
												}
											str+='</div>';
										str+='</div>';
									}
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-4">';
						str+='<div class="block-border">';
							str+='<h5 class="text-danger">SERP</h5>';
							str+='<div class="row">';	
								for(var i in result)
								{
									if(result[i].name == "Horticulture" || result[i].name == "Avenue"){
										str+='<div class="col-sm-6 m_top10">';
											str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';
												if(result[i].name.length > 12)
												{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
												}else{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
												}
											str+='</div>';
										str+='</div>';
									}
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="block-border">';
							str+='<h5 class="text-danger">Fisheries</h5>';
							str+='<div class="row">';	
								for(var i in result)
								{
									if(result[i].name == "Fish Drying Platforms" || result[i].name == "Fish Ponds"){
										str+='<div class="col-sm-6 m_top10">';
											str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';
												if(result[i].name.length > 12)
												{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
												}else{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
												}
											str+='</div>';
										str+='</div>';
									}
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<div class="block-border">';
							str+='<h5 class="text-danger">Forest</h5>';
							str+='<div class="row">';	
								for(var i in result)
								{
									if(result[i].name == "Nurseries"){
										str+='<div class="col-sm-6 m_top10">';
											str+='<div class="panel-block-white text-center" overview-block="'+result[i].name+'">';
												if(result[i].name.length > 12)
												{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls" title="'+result[i].name+'">'+result[i].name.substr(0,12)+'..</h4>';
												}else{
													str+='<h4 class="panel-block-white-title text-capitalize text-center toolTipTitleCls">'+result[i].name+'</h4>';
												}
											str+='</div>';
										str+='</div>';
									}
								}
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#projectsOverview").html(str);

	$(".toolTipTitleCls").tooltip();
	if(blockName != null)
	{
		$('[overview-block]').removeClass("active");
		$('[overview-block="'+blockName+'"]').addClass("active");
		$('[overview-block="'+blockName+'"]').trigger('click');
	}
	var locType = '';
	if(levelId == 2 || levelId == "2")
	{
		locType = 'state';
	}else if(levelId == 3 || levelId == "3")
	{
		locType = 'district';
	}else if(levelId == 4 || levelId == "4")
	{
		locType = 'constituency';
	}
	for(var i in overViewArr)
	{
		$("[overview-block='"+overViewArr[i].name+"']").html(spinner);
		if(overViewArr[i].name == 'Solid Waste Management' || overViewArr[i].name == 'Burial Grounds' || overViewArr[i].name == 'Play Fields' || overViewArr[i].name == 'CC Roads' || overViewArr[i].name == 'Anganwadi Buildings' || overViewArr[i].name == 'GP Buildings' || overViewArr[i].name == 'Mandal Buildings' || overViewArr[i].name == 'NTR 90 Days' || overViewArr[i].name == 'Production of Bricks' || overViewArr[i].name == 'Mulbery' || overViewArr[i].name == 'Silk Worms' || overViewArr[i].name == 'Cattle Drinking Water Troughs' || overViewArr[i].name == 'Raising of Perinnial Fodders' || overViewArr[i].name == 'Fish Ponds' || overViewArr[i].name == 'Fish Drying Platforms')
		{
			if(levelId == 4 || levelId == "4"){
				getNREGSProjectsAbstractNewFrConstituency(overViewArr[i].name,locType,locId,"0",blockName,levelId);
			}else{
				getNREGSProjectsAbstractNew(overViewArr[i].name,locType,locId,blockName,levelId);
			}
			
		}else if(overViewArr[i].name == 'Payments')
		{
			getNregaPaymentsAbsAndOverview(overViewArr[i].name,locId,blockName,levelId,'abstract');
		}else{
			if(levelId == 4 || levelId == "4"){
				getNREGSAbstractDataByTypeFrConstituency(overViewArr[i].name,locType,locId,"0",blockName,levelId);
			}else{
				getNREGSAbstractDataByType(overViewArr[i].name,locType,locId,blockName,levelId,'onLoad');
			}
			
		}
	}

}
function getNREGSProjectsAbstractNew(type,locType,locId,blockName,levelId)
{
	//$("#projectsOverview").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		type : type,
		locationType: locType,
		locationId : locId
	}
	$.ajax({
		url: 'getNREGSProjectsAbstractNew',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId);
		}
	});
}

function getNregaPaymentsAbsAndOverview(type,locType,locId,levelId,buildType)
{
	//$("#projectOvervw"+type.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locType,
		divType : type,
		locationId : locId,
		sublocaType :locType
	}
	$.ajax({
		url: 'getNregaPaymentsAbsAndOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			if(buildType == 'abstract')
			{
				buildNREGSAbstractDataByTypeNew(type,ajaxresp,'',locId,locType,levelId);
			}else if(buildType == 'overview')
			{
				buildPaymentsOverviewData(ajaxresp,type);
			}
			
		}
	});
}
function getNREGSAbstractDataByType(type,locType,locId,blockName,levelId,buildDateType)
{
	if(buildDateType == 'onLoad' && type == 'FAperformance')
	{
		var json = {
			year : "2017",
			fromDate : glStartDate,
			toDate : '2017-05-01',
			type : type,
			locationType: locType,
			locationId : locId
		}
	}else{
		var json = {
			year : "2017",
			fromDate : glStartDate,
			toDate : glEndDate,
			type : type,
			locationType: locType,
			locationId : locId
		}
	}
	
	
	$.ajax({
		url: 'getNREGSAbstractDataByType',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId);
		}
	}); 
}
function buildNREGSAbstractDataByTypeNew(type,result,blockName,locId,locType,levelId)
{
	$("[overview-block='"+type+"']").removeClass("panel-block-white");
	var str='';
	
	$("[overview-block='"+type+"']").attr("attr_levelId",levelId);
	$("[overview-block='"+type+"']").attr("attr_locationId",locId);
	if(type == 'Payments' && result != null)
	{
		str+='<div class="panel-black-white panel-block-white-high text-center" overview-district="'+type+'" style="padding:7px 5px">';
			if(type.length > 12)
			{
				str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+type+'">'+type.substr(0,12)+'..</h4>';
			}else{
				str+='<h4 class="panel-block-white-title text-capitalize text-center">'+type+'</h4>';
			}
			str+='<small class="text-center">Total Pending</small>';
			if(result.totalPendinAmount != null && result.totalPendinAmount.length > 0)
			{
				str+='<h1 class="text-center" style="font-size:26px"><i class="fa fa-inr"></i> '+result.totalPendinAmount+'</h1>';
				str+='<small>('+result.totalPendings+')</small>';
			}else{
				str+='<h1 class="text-center">0</h1>';
			}
			str+='<div class="row">';
				str+='<div class="col-sm-6 text-center pad_right0">';
					str+='<label>Wage</label>';
					if(result.pendingWage != null && result.pendingWage.length > 0)
					{
						str+='<h4>'+result.pendingWage+'</h4> <small>(<i class="fa fa-inr" style="position:static"></i>'+result.pendingWageAmount+')</small>';
					}else{
						str+='<h4>0</h4>';
					}
					
				str+='</div>';
				str+='<div class="col-sm-6 text-center pad_left0">';
					str+='<label>Material</label>';
					if(result.pendingMaterial != null && result.pendingMaterial.length > 0)
					{
						str+='<h4>'+result.pendingMaterial+'</h4><small> (<i class="fa fa-inr" style="position:static"></i>'+result.pendingMaterialAmount+')</small>';
					}else{
						str+='<h4>0</h4>';
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	if(result != null && result.length > 0)
	{
		for(var i in result)
		{
			if(levelId == 2 || levelId == "2" || levelId == 4 || levelId == "4"){
				
				if(result[i].percentage < 50)
				{
					str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
				}else if(result[i].percentage >= 50 && result[i].percentage < 80)
				{
					str+='<div class="panel-black-white panel-block-white-medium text-center" overview-district="'+type+'">';
					
				}else if(result[i].percentage >= 80)
				{
					str+='<div class="panel-black-white panel-block-white-high text-center" overview-district="'+type+'">';
				}
					if(type.length > 12)
					{
						str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+type+'">'+type.substr(0,12)+'..</h4>';
					}else{
						str+='<h4 class="panel-block-white-title text-capitalize text-center">'+type+'</h4>';
					}
					str+='<small class="text-center">Achieved</small>';
					if(result[i].percentage != null && result[i].percentage.length > 0)
					{
						str+='<h1 class="text-center">'+result[i].percentage+'<small>%</small>';
					}else{
						str+='<h1 class="text-center">0<small>%</small>';
					}
						
					if(result[i].percentage < 50)
					{
						str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
					}else if(result[i].percentage >= 50 && result[i].percentage < 80)
					{
						str+='<small><i class="fa fa-arrows-v"></i></small></h1>';
					}else if(result[i].percentage >= 80)
					{
						str+='<small><i class="fa fa-long-arrow-up"></i></small></h1>';
					}
					str+='<div class="row">';
						str+='<div class="col-sm-6 text-center">';
							str+='<label>Target</label>';
							if(result[i].target != null && result[i].target.length > 0)
							{
								if(result[i].parameter == 'Labour Budget' && levelId == 2)
								{
									str+='<h4>'+result[0].target+'L</h4>';
								}else if(result[0].parameter == 'Timely Payments'){
									str+='<h4>'+result[0].target+'%</h4>';
								}else{
									str+='<h4>'+result[0].target+'</h4>';
								}
							}else{
								str+='<h4>0</h4>';
							}
						str+='</div>';
						str+='<div class="col-sm-6 text-center">';
							str+='<label>Completed</label>';
							if(result[i].completed != null && result[i].completed.length > 0)
							{
								if(result[i].parameter == 'Labour Budget' && levelId == 2)
								{
									str+='<h4>'+result[i].completed+'L</h4>';
								}else if(result[i].parameter == 'Timely Payments'){
									str+='<h4>'+result[i].completed+'%</h4>';
								}else{
									str+='<h4>'+result[i].completed+'</h4>';
								}
								
							}else{
								str+='<h4>0</h4>';
							}
						str+='</div>';
					str+='</div>';
				str+='</div>';
				if(levelId == 4 || levelId == "4")
				{
					str+='<div class="panel-black-white panel-block-white-high text-center" overview-'+result[i].type+'="'+type+'" style="border-top:1px solid #333;">';
						str+='<small class="panel-block-white-title text-capitalize text-center">ACHIEVED</small>';
						str+='<div class="row">';
						for(var j in result[i].subList)
						{
							if(result[i].subList != null)
							{
								str+='<div class="col-sm-6">';
									str+='<p>'+result[i].subList[j].type+'</p>';
									if(result[i].subList[j].percentage != null && result[i].subList[j].percentage.length > 0)
									{
										str+='<h2 class="text-center">'+result[i].subList[j].percentage+'</h2>';
									}else{
										str+='<h2 class="text-center">0</h2>';
									}
								str+='</div>';
							}else{
								str+='<div class="col-sm-6">';
									str+='<p>'+result[i].subList[j].type+'</p>';
									str+='<h2 class="text-center">0</h2>';
								str+='</div>';
							}
						}
						str+='</div>';
					str+='</div>';
				}
				
			}else if(levelId == 3 || levelId == "3")
			{
				if(result[i].type == 'DISTRICT')
				{
					if(result[i] != null)
					{
						if(result[i].percentage < 50)
						{
							str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
						}else if(result[i].percentage >= 50 && result[i].percentage < 80)
						{
							str+='<div class="panel-black-white panel-block-white-medium text-center" overview-district="'+type+'">';
							
						}else if(result[i].percentage >= 80)
						{
							str+='<div class="panel-black-white panel-block-white-high text-center" overview-district="'+type+'">';
						}
							if(type.length > 12)
							{
								str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+type+'">'+type.substr(0,12)+'..</h4>';
							}else{
								str+='<h4 class="panel-block-white-title text-capitalize text-center">'+type+'</h4>';
							}
							str+='<small class="text-center">Achieved</small>';
							if(result[i].percentage != null && result[i].percentage.length > 0)
							{
								str+='<h1 class="text-center">'+result[i].percentage+'<small>%</small>';
							}else{
								str+='<h1 class="text-center">0<small>%</small>';
							}
								
							if(result[i].percentage < 50)
							{
								str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
							}else if(result[i].percentage >= 50 && result[i].percentage < 80)
							{
								str+='<small><i class="fa fa-arrows-v"></i></small></h1>';
							}else if(result[i].percentage >= 80)
							{
								str+='<small><i class="fa fa-long-arrow-up"></i></small></h1>';
							}
							str+='<div class="row">';
								str+='<div class="col-sm-6 text-center">';
									str+='<label>Target</label>';
									if(result[i].target != null && result[i].target.length > 0)
									{
										if(result[0].parameter == 'Timely Payments'){
											str+='<h4>'+result[0].target+'%</h4>';
										}else{
											str+='<h4>'+result[0].target+'</h4>';
										}
									}else{
										str+='<h4>0</h4>';
									}
									
								str+='</div>';
								str+='<div class="col-sm-6 text-center">';
									str+='<label>Completed</label>';
									if(result[i].completed != null && result[i].completed.length > 0)
									{
										if(result[i].parameter == 'Timely Payments'){
											str+='<h4>'+result[i].completed+'%</h4>';
										}else{
											str+='<h4>'+result[i].completed+'</h4>';
										}
										
									}else{
										str+='<h4>0</h4>';
									}
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}else{
						str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
							str+='<h1 class="text-center">0<small>%</small></h1>';
							str+='<div class="row">';
								str+='<div class="col-sm-6 text-center">';
									str+='<label>Target</label>';
									str+='<h4>0</h4>';
								str+='</div>';
								str+='<div class="col-sm-6 text-center">';
									str+='<label>Completed</label>';
									str+='<h4>0</h4>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}
					
				}else if(result[i].type == 'STATE')
				{
					if(result[i] != null)
					{
						if(result[i].percentage < 50)
						{
							str+='<div class="panel-black-white panel-block-white-low text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
						}else if(result[i].percentage >= 50 && result[i].percentage < 80)
						{
							str+='<div class="panel-black-white panel-block-white-medium text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
							
						}else if(result[i].percentage >= 80)
						{
							str+='<div class="panel-black-white panel-block-white-high text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
						}
							str+='<small class="panel-block-white-title text-capitalize text-center">STATE LEVEL - ACHIEVED</small>';
							if(result[i].percentage != null && result[i].percentage.length > 0)
							{
								str+='<h2 class="text-center">'+result[i].percentage+'</h2>';
							}else{
								str+='<h2 class="text-center">0</h2>';
							}
						str+='</div>';
					}else{
						str+='<div class="panel-black-white panel-block-white-low text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
							str+='<small class="panel-block-white-title text-capitalize text-center">STATE LEVEL - ACHIEVED</small>';
							str+='<h2 class="text-center">0</h2>';
						str+='</div>';
					}
				}
			}
		}
	}
	
	$("[overview-block='"+type+"']").html(str);
	$(".panel-block-white-title").tooltip();
	if(type == blockName)
	{
		$("[overview-block='"+blockName+"']").trigger("click");
	}
	
}

function projectData(levelId,locId)
{
	//alert(locationId);
	var collapse='';
	var dataArr = '';
	var subLocType = '';
	var locationType = '';
	var divId = '';
	if(levelId == 2)
	{
		dataArr = ['state','district','constituency','mandal'];
		subLocType,divId = 'state';
	}else if(levelId == 3)
	{
		dataArr = ['district','constituency','mandal'];
		subLocType,locationType,divId = 'district';
	}else if(levelId == 4)
	{
		dataArr = ['constituency','mandal'];
		locationType,divId = 'district';
		subLocType = 'constituency';
	}
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in dataArr)
				{
					collapse+='<div class="panel-group" id="accordion'+dataArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+dataArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon" overview-levelId="'+levelId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordion'+dataArr[i]+'" href="#collapse'+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+dataArr[i]+'">';
								}else{
									collapse+='<a role="button" collapse-click="'+dataArr[i]+'" class="panelCollapseIcon collapsed" overview-levelId="'+levelId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordion'+dataArr[i]+'" href="#collapse'+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+dataArr[i]+'">';
								}
									collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level - consolidated overview</h4>';
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+dataArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+dataArr[i]+'"  class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+dataArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+dataArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	
	$("#projectData").html(collapse);
	var locId = $("#selectedName").attr("attr_id");
	if(levelId == 2)
	{
		locationType = 'state';
		subLocType = 'state';
		divId = 'state'
	}else if(levelId == 3)
	{
		locationType = 'district';
		subLocType = 'district';
		divId = 'district'
	}else if(levelId == 4)
	{
		locationType = 'district';
		subLocType = 'constituency';
		divId = 'constituency'
	}
	getNREGSLevelWiseConsolidatedReport(levelId,locationType,subLocType,locId,divId);
	
}
function getNREGSLevelWiseConsolidatedReport(levelId,locationType,subLocationType,locationId,divId)
{
	$("#"+divId).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locationType,
		locationIdStr : locationId,
		subLocationType : subLocationType,
		componentIds : overViewIdsArr
	}
	$.ajax({
		url: 'getNREGSLevelWiseConsolidatedReport',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			tableView(ajaxresp,divId);
		}
	});
}
function tableView(result,divId)
{
	var tableView='';
	
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered dataTable'+divId+'">';
			tableView+='<thead class="text-capital">';
				tableView+='<th>'+divId+'</th>';
				for(var i in result[0].subList)
				{
					tableView+='<th>'+result[0].subList[i].component+'</th>';
				}
				/* for(var i in overViewArr)
				{
					tableView+='<th>'+overViewArr[i].name+'</th>';
				} */
			tableView+='</thead>';
			
 			tableView+='<tbody>';
				for(var i in result)
				{
					tableView+='<tr>';
						tableView+='<td class="text-capital">'+result[i].subList[0].name+'</td>';
						for(var j in result[i].subList)
						{
							if(result[i].subList[j].percentage != null)
							{
								if(result[i].subList[j].percentage < 50)
								{
									tableView+='<td style="background-color:#FF0000;color:#fff">'+result[i].subList[j].percentage+'</td>';
								}else if(result[i].subList[j].percentage >= 50 && result[i].subList[j].percentage < 80)
								{
									tableView+='<td style="background-color:#FFBA00;color:#fff">'+result[i].subList[j].percentage+'</td>';
								}else if(result[i].subList[j].percentage >= 80)
								{
									tableView+='<td style="background-color:#00AF50;color:#fff">'+result[i].subList[j].percentage+'</td>';
								}	
							}else{
								tableView+='<td>-</td>';
							}
						}
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	$("#"+divId).html(tableView);	
	if(divId != 'state')
	{
		$(".dataTable"+divId).dataTable({
			"iDisplayLength": 20,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			"dom": 'Bfrtip',
			buttons: [
				'copy', 'excel', 'pdf', 'print'
			]
		});
	}
}

function getNREGSProjectsAbstractNewFrConstituency(type,locType,locId,districtId,blockName,levelId)
{
	var districtId = $("#selectedName").attr("attr_distId");
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		type : type,
		locationType: locType,
		locationId : locId,
		districtId : districtId
	}
	$.ajax({
		url: 'getNREGSProjectsAbstractNewFrConstituency',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId)
		}
	});
}
function getNREGSAbstractDataByTypeFrConstituency(type,locType,locId,districtId,blockName,levelId)
{
	var districtId = $("#selectedName").attr("attr_distId");
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		type : type,
		locationType: locType,
		locationId : locId,
		districtId : districtId
	}
	
	$.ajax({
		url: 'getNREGSAbstractDataByTypeFrConstituency',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNREGSAbstractDataByTypeNew(type,ajaxresp,blockName,locId,locType,levelId)
		}
	});
}