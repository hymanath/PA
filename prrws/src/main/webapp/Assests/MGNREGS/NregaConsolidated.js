var spinnerConsolidated = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = '2017-04-01'//moment().startOf('year').format("YYYY-MM")+'-1';
var glEndDate = moment().format("YYYY-MM")+'-30';
var overViewArrConsolidated = [];
//var overViewArrConsolidated = [{"name":'Labour Budget',"id":"15"},{"name":'Farm Ponds',"id":"15"},{"name":'IHHL',"id":"15"},{"name":'Vermi Compost',"id":"15"},{"name":'Solid Waste Management',"id":"15"},{"name":'Burial Grounds',"id":"15"},{"name":'Play Fields',"id":"15"},{"name":'Agriculture Activities',"id":"15"},{"name":'Average Wage',"id":"15"},{"name":'Average Days of Employment',"id":"15"},{"name":'HH Completed 100 Days',"id":"15"},{"name":'Timely Payment',"id":"15"},{"name":'CC Roads',"id":"15"},{"name":'Anganwadi Buildings',"id":"15"},{"name":'GP Buildings',"id":"15"},{"name":'Mandal Buildings',"id":"15"},{"name":'NTR 90 Days',"id":"15"},{"name":'Production of Bricks',"id":"15"},{"name":'Mulbery',"id":"15"},{"name":'Silk Worms',"id":"15"},{"name":'Cattle Drinking Water Troughs',"id":"15"},{"name":'Raising of Perinnial Fodders',"id":"15"},{"name":'Horticulture',"id":"15"},{"name":'Avenue',"id":"15"},{"name":'Fish Ponds',"id":"15"},{"name":'Fish Drying Platforms',"id":"15"},{"name":'Nurseries',"id":"15"},{"name":'Payments',"id":"15"},{"name":'FAperformance',"id":"15"}];
//var overViewArr = [{"name":'Labour Budget'},{"name":'Farm Ponds'},{"name":'IHHL'},{"name":'Vermi Compost'},{"name":'SMC Trench'},{"name":'Imp to CD'},{"name":'MPT_PT'},{"name":'GC Works'},{"name":'CD_CW'},{"name":'GH'},{"name":'Solid Waste Management'},{"name":'Burial Grounds'},{"name":'Play Fields'},{"name":'Agriculture Activities'},{"name":'Average Wage'},{"name":'Average Days of Employment'},{"name":'HH Completed 100 Days'},{"name":'Timely Payment'},{"name":'CC Roads'},{"name":'Anganwadi Buildings'},{"name":'GP Buildings'},{"name":'Mandal Buildings'},{"name":'NTR 90 Days'},{"name":'Production of Bricks'},{"name":'Mulbery'},{"name":'Silk Worms'},{"name":'Cattle Drinking Water Troughs'},{"name":'Raising of Perinnial Fodders'},{"name":'Horticulture'},{"name":'Avenue'},{"name":'Fish Ponds'},{"name":'Fish Drying Platforms'},{"name":'Nurseries'},{"name":'Payments'},{"name":'FAperformance'},{"name":'NTR Rural House'},{"name":'OPGK-Perinnials'},{"name":'OPGK-Annuals'},{"name":'UGDrainage'}];

var overViewIdsArr = [];
$("#projectsOverviewConsolidated").html(spinnerConsolidated);

$('#dateRangePickerMGNF').on('dp.change', function(e){ 
	glStartDate = e.date.format("YYYY-MM")+"-31";
	var locId = $("#selectedName").attr("attr_id");
	var levelId = $("#selectedName").attr("attr_levelid");
	var districtId = $("#selectedName").attr("attr_distid");
	//buildNREGSProjectsOverviewConsolidated(overViewArr,'',locId,levelId);
	if(levelId == 4)
	{
		projectDataConsolidated(levelId,locId,districtId);
	}else{
		projectDataConsolidated(levelId,locId,'');
	}
	
});
$('#dateRangePickerMGNT').on('dp.change', function(e){ 
	glEndDate = e.date.format("YYYY-MM")+"-31";
	var locId = $("#selectedName").attr("attr_id");
	var levelId = $("#selectedName").attr("attr_levelid");
	var districtId = $("#selectedName").attr("attr_distid");
	//buildNREGSProjectsOverviewConsolidated(overViewArr,'',locId,levelId);
	if(levelId == 4)
	{
		projectDataConsolidated(levelId,locId,districtId);
	}else{
		projectDataConsolidated(levelId,locId,'');
	}
});


function onLoadCallsConsolidated()
{
	getAllConvergenceTypesConsolidated();
}

$(document).on("click","[collapse-click-consolidated]",function(){
	var collapseExpanded = $(this).attr("aria-expanded");
	if(collapseExpanded == true || collapseExpanded == 'true')
	{
		var divId = $(this).attr("collapse-click-consolidated");
		var locId = $("#selectedName").attr("attr_id");
		var levelId = $("#selectedName").attr("attr_levelid");
		var locationType = '';
		var districtId = $("#selectedName").attr("attr_distId");
		if(levelId == 2)
		{
			locationType = 'state';
			subLocType = 'state';
			districtId = '';
			divId = $(this).attr("overview-level-consolidated");
		}else if(levelId == 3)
		{
			locationType = 'district';
			subLocType = 'district';
			districtId = '';
			divId = $(this).attr("overview-level-consolidated");
		}else if(levelId == 4)
		{
			locationType = 'constituency';
			subLocType = 'constituency';
			divId = $(this).attr("overview-level-consolidated");
			districtId = '';
		}
		if(divId == 'district')
		{
			subLocType = 'district';
		}else if(divId == 'constituency')
		{
			subLocType = 'constituency';
		}else if(divId == 'mandal')
		{
			subLocType = 'mandal';
		}else if(divId == 'panchayat')
		{
			subLocType = 'panchayat';
		}
		if(divId != "state")
		{
			var tableId = 'ConsolidatedView'+divId
			getNREGSLevelWiseConsolidatedReportConsolidated(levelId,locationType,subLocType,locId,tableId,districtId);
		}
	}	
});
$(document).on("click",".selectAllCheckbox",function(){
	var selected = $(this).prop("checked");
	var checkBoxClass = $(this).attr("attr_selectAll");
	if(selected == true || selected == 'true')
	{
		$("."+checkBoxClass).prop("checked",true);
	}else{
		$("."+checkBoxClass).prop("checked",false);
	}
});
$(".menu-top-selection .arrow_box_top").hide();
$(document).on("click",".menu-top-selection-icon",function(e){
	e.stopPropagation();
	$(".menu-top-selection .arrow_box_top").show();
});
$(document).on("click",".menu-top-selection",function(e){
	e.stopPropagation();
});
/* $(document).on("click",".panelCollapseIconClick",function(e){
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
}); */
$(document).on("click",".selectionMenuSubmitIdNewCls",function(){
	overViewIdsArr = [] ;
	overViewArrConsolidated = [];
	
	$(".menuSelectionCheckBox").each(function(){
		var checkboxId = $(this).attr("checkboxId");
		if($(this).prop('checked')==true)
		{
			var checkboxName = $(this).attr("checkboxName");
			overViewArrConsolidated.push({"name":checkboxName,"id":checkboxId});
			overViewIdsArr.push(checkboxId);
		}
	});
	
	var locId = $("#selectedName").attr("attr_id");
	var levelId = $("#selectedName").attr("attr_levelid");
	$("#consolidatedView").show();
	$("#projectData,#projectOverviewBlock").hide();
	$('html,body').animate({
		scrollTop: $("#projectDataConsolidated").offset().top},
	'slow');
	setTimeout(function(){
	//	buildNREGSProjectsOverviewConsolidated(overViewArrConsolidated,'',locId,levelId);
		projectDataConsolidated(levelId,locId);
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
/* collapseMenuConsolidated(1,stateArr,'multi-level-selection-menu');
function getAllNregaSubLocationDetails(divId,levelId,locationScopeId,type){
	$("."+divId).html(spinnerConsolidated);
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
			collapseMenuConsolidated(levelId,result,divId)
		}
	});
}
function collapseMenuConsolidated(id,resultArr,buildId)
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
	
	collapse+='<div class="panel-group dashedBorder" id="accordionConsolidated'+id+'" role="tablist" aria-multiselectable="true">';
	for(var i in resultArr)
	{
		collapse+='<div class="panel panel-default panelExpand">';
			collapse+='<div class="panel-heading" role="tab" id="headingConsolidated'+resultArr[i].type+'">';
				collapse+='<h4 class="panel-title">';
					if(levelIdValue == 2 || levelIdValue == 3)
					//if(levelIdValue == 2)
					{
						collapse+='<a role="button" style="height:10px;width:10px;display:inline-block;" attr_levelIdValue="'+levelIdValue+'" attr_distId="'+resultArr[i].type+'" attr_levelId="'+id+'" attr_id="'+resultArr[i].type+'" attr_targetId="collapseMenuConsolidated'+resultArr[i].type+'Id"  class="panelCollapseIcon panelCollapseIconClick collapsed" data-toggle="collapse" data-parent="#accordionConsolidated'+id+'" href="#collapseConsolidated'+resultArr[i].type+'" aria-expanded="true" aria-controls="collapseConsolidated'+resultArr[i].type+'">&nbsp;</a>';
					}
					collapse+='<span style="padding-left:10px;cursor:pointer;" class="menuDataCollapse"  attr_levelIdValue="'+levelIdValue+'" attr_distid="" attr_levelId="'+id+'" attr_id="'+resultArr[i].type+'" attr_targetId="collapseMenuConsolidated'+resultArr[i].type+'Id" >'+resultArr[i].name+'</span>';
				collapse+='</h4>';
			collapse+='</div>';
			collapse+='<div id="collapseConsolidated'+resultArr[i].type+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingConsolidated'+resultArr[i].type+'">';
				collapse+='<div class="panel-body">';
					collapse+='<div class="collapseMenuConsolidated'+resultArr[i].type+'Id"></div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	}
	collapse+='</div>';
	$("."+buildId).html(collapse);
}
 */
/* Menu End*/
function getAllConvergenceTypesConsolidated()
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
	$("#"+divId).html(spinnerConsolidated);
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
		selectionMenu+='<li>';
			selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox selectAllCheckbox" attr_selectAll="'+divId+'"/>Select All</label>';
		selectionMenu+='</li>';
		for(var i in result)
		{
			selectionMenu+='<li>';
				selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>'+result[i].name+'</label>';
			selectionMenu+='</li>';
			overViewArrConsolidated.push({"name":result[i].name,"id":result[i].id});
			overViewIdsArr.push(result[i].id);
		}		
	selectionMenu+='</ul>';
	$("#"+divId).html(selectionMenu);
	if(convergenceId == 4)
	{
		setTimeout(function(){
			var locId = $("#selectedName").attr("attr_id");
			var levelId = $("#selectedName").attr("attr_levelid");
			//buildNREGSProjectsOverviewConsolidated(overViewArr,'',locId,levelId);
			projectDataConsolidated(levelId,locId);
		},500);
	}
}

/* function getNREGSProjectsAbstractNewConsolidated(type,locType,locId,blockName,levelId)
{
	//$("#projectsOverviewConsolidated").html(spinnerConsolidated);
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
			buildNREGSAbstractDataByTypeNewConsolidated(type,ajaxresp,blockName,locId,locType,levelId);
		}
	});
} */

function getNregaPaymentsAbsAndOverviewConsolidated(type,locType,locId,levelId,buildType)
{
	//$("#projectOvervw"+type.replace(/\s+/g, '')).html(spinnerConsolidated);
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
		url: 'getNregaPaymentsAbsAndOverviewDtls',
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
				buildNREGSAbstractDataByTypeNewConsolidated(type,ajaxresp,'',locId,locType,levelId);
			}else if(buildType == 'overview')
			{
				buildPaymentsOverviewData(ajaxresp,type);
			}
			
		}
	});
}
/* function getNREGSAbstractDataByTypeConsolidated(type,locType,locId,blockName,levelId,buildDateType)
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
			buildNREGSAbstractDataByTypeNewConsolidated(type,ajaxresp,blockName,locId,locType,levelId);
		}
	}); 
} */
/* function buildNREGSAbstractDataByTypeNewConsolidated(type,result,blockName,locId,locType,levelId)
{
	$("[overview-block='"+type+"']").removeClass("panel-block-white");
	var str='';
	
	$("[overview-block='"+type+"']").attr("attr_levelId",levelId);
	$("[overview-block='"+type+"']").attr("attr_locationId",locId);
	if(type == 'Payments' && result != null)
	{
		str+='<div class="panel-black-white panel-block-white-high text-center" overview-district="'+type+'" style="padding:24px 15px">';
			if(type.length > 12)
			{
				str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+type+'">'+type.substr(0,12)+'..</h4>';
			}else{
				str+='<h4 class="panel-block-white-title text-capitalize text-center">'+type+'</h4>';
			}
			str+='<small class="text-center">Total Pending</small>';
			if(result[0].totalPendinAmount != null && result[0].totalPendinAmount.length > 0)
			{
				str+='<h1 class="text-center" style="font-size:26px"><i class="fa fa-inr"></i> '+result[0].totalPendinAmount+'</h1>';
			}else{
				str+='<h1 class="text-center">0</h1>';
			}
			str+='<div class="row">';
				str+='<div class="col-sm-6 text-center pad_right0">';
					str+='<label>Wage</label>';
					if(result[0].pendingWage != null && result[0].pendingWage.length > 0)
					{
						str+='<h4><i class="fa fa-inr" style="position:static"></i>'+result[0].pendingWage+'</h4>';
					}else{
						str+='<h4>0</h4>';
					}
					
				str+='</div>';
				str+='<div class="col-sm-6 text-center pad_left0">';
					str+='<label>Material</label>';
					if(result[0].pendingMaterial != null && result[0].pendingMaterial.length > 0)
					{
						str+='<h4><i class="fa fa-inr" style="position:static"></i>'+result[0].pendingMaterial+'</h4>';
					}else{
						str+='<h4>0</h4>';
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	if(result != null && result.length > 0 && type != 'Payments')
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
					}else{
						str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
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
						}else
						{
							str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
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
						}else{
							str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
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
							}else
							{
								str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
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
						}else{
							str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
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
	}else if(type != 'Payments'){
		str+='<div class="panel-black-white panel-block-white-low text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
			str+='<small class="panel-block-white-title text-capitalize text-center">STATE LEVEL - ACHIEVED</small>';
			str+='<h2 class="text-center">0</h2>';
		str+='</div>';
	}
	
	$("[overview-block='"+type+"']").html(str);
	$(".panel-block-white-title").tooltip();
	if(type == blockName)
	{
		$("[overview-block='"+blockName+"']").trigger("click");
	}
	
} */

function projectDataConsolidated(levelId,locId,districtId)
{
	var collapse='';
	var dataArr = '';
	var subLocType = '';
	var locationType = '';
	var divId = '';
	if(levelId == 2)
	{
		dataArr = ['state','district','constituency','mandal','panchayat'];
		subLocType,divId = 'state';
	}else if(levelId == 3)
	{
		dataArr = ['district','constituency','mandal','panchayat'];
		subLocType,locationType,divId = 'district';
	}else if(levelId == 4)
	{
		dataArr = ['constituency','mandal','panchayat'];
		locationType,divId = 'district';
		subLocType = 'constituency';
	}
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in dataArr)
				{
					collapse+='<div class="panel-group" id="accordionConsolidated'+dataArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="headingConsolidated'+dataArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon" overview-levelId="'+levelId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordionConsolidated'+dataArr[i]+'" href="#collapseConsolidated'+dataArr[i]+'" aria-expanded="true" aria-controls="collapseConsolidated'+dataArr[i]+'">';
								}else{
									collapse+='<a role="button" collapse-click-consolidated="'+dataArr[i]+'" class="panelCollapseIcon collapsed" overview-levelId="'+levelId+'" overview-level-consolidated="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordionConsolidated'+dataArr[i]+'" href="#collapseConsolidated'+dataArr[i]+'" aria-expanded="true" aria-controls="collapseConsolidated'+dataArr[i]+'">';
								}
									collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level - consolidated overview</h4>';
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapseConsolidated'+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingConsolidated'+dataArr[i]+'">';
							}else{
								collapse+='<div id="collapseConsolidated'+dataArr[i]+'"  class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingConsolidated'+dataArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="collapseConsolidatedView'+dataArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	
	$("#projectDataConsolidated").html(collapse);
	var locId = $("#selectedName").attr("attr_id");
	var districtId = '';
	if(levelId == 2)
	{
		locationType = 'state';
		subLocType = 'state';
		divId = 'ConsolidatedViewstate'
	}else if(levelId == 3)
	{
		locationType = 'district';
		subLocType = 'district';
		divId = 'ConsolidatedViewdistrict'
	}else if(levelId == 4)
	{
		locationType = 'constituency';
		subLocType = 'constituency';
		divId = 'ConsolidatedViewconstituency';
		districtId = $("#selectedName").attr("attr_distId");
	}
	getNREGSLevelWiseConsolidatedReportConsolidated(levelId,locationType,subLocType,locId,divId,districtId);
	
}
function getNREGSLevelWiseConsolidatedReportConsolidated(levelId,locationType,subLocationType,locationId,divId,districtId)
{
	var districtId = $("#selectedName").attr("attr_distId");
	$("#collapse"+divId).html(spinnerConsolidated);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locationType,
		locationIdStr : locationId,
		subLocationType : subLocationType,
		districtId : districtId,
		componentIds : overViewIdsArr,
		
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
			tableViewConsolidated(ajaxresp,divId,subLocationType);
		}
	});
}
function tableViewConsolidated(result,divId,subLocationType)
{
	var tableView='';
	
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered dataTable'+divId+'">';
			tableView+='<thead class="text-capital">';
				tableView+='<th>'+subLocationType+'</th>';
				for(var i in result[0].subList)
				{
					tableView+='<th>'+result[0].subList[i].component+'</th>';
				}
				/* for(var i in overViewArrConsolidated)
				{
					tableView+='<th>'+overViewArrConsolidated[i].name+'</th>';
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
	$("#collapse"+divId).html(tableView);	
	$(".dataTable"+divId).dataTable({
		"iDisplayLength": 20,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
		//"dom": 'lfBrtip',
		"scrollX":        true,
		"scrollCollapse": true,
		"fixedColumns":   {
            "leftColumns": 1,
		},
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   divId,
				filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				title:	   divId,
				filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				orientation: "landscape",
				pageSize:'A3',
				customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}
			}
		]
	});
}

/* function getNREGSProjectsAbstractNewFrConstituency(type,locType,locId,districtId,blockName,levelId)
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
			buildNREGSAbstractDataByTypeNewConsolidated(type,ajaxresp,blockName,locId,locType,levelId)
		}
	});
} */
/* function getNREGSAbstractDataByTypeFrConstituency(type,locType,locId,districtId,blockName,levelId)
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
			buildNREGSAbstractDataByTypeNewConsolidated(type,ajaxresp,blockName,locId,locType,levelId)
		}
	});
} */