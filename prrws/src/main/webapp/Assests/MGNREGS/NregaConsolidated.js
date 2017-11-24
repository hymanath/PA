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
			getNREGSLevelWiseConsolidatedReportConsolidated(levelId,locationType,subLocType,locId,tableId,districtId,'grounded');
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
	//$(".menu-top-selection .arrow_box_top").show();
	$(".menu-top-selection .arrow_box_top").toggle();
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
	$(this).closest(".arrow_box_top").hide();
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
		scrollTop: $("#consolidatedView").offset().top},
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
						selectionMenu+='<div role="tabpanel" class="tab-pane active" id="selectionMenuId'+result[i].id+'">'+result[i].id+'</div>';
					}else{
						selectionMenu+='<div role="tabpanel" class="tab-pane" id="selectionMenuId'+result[i].id+'">'+result[i].id+'</div>';
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
				if(result[i].name == 'GH')
				{
					selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>Greening Of Hillocks</label>';
				}else if(result[i].name == 'CD_CW')
				{
					selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>Check Dams and Check Walls</label>';
				}else if(result[i].name == 'GC Works')
				{
					selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>Gully Control Work</label>';
				}else if(result[i].name == 'MPT_PT')
				{
					selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>Mini Percolation Tanks and Percolation Tanks</label>';
				}else if(result[i].name == 'Imp to CD')
				{
					selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>Improvements to Check Dams and Percolation Tanks</label>';
				}else{
					selectionMenu+='<label class="checkbox-inline"><input type="checkbox" checked class="menuSelectionCheckBox '+divId+'" checkboxName="'+result[i].name+'" checkboxId="'+result[i].id+'"/>'+result[i].name+'</label>';
				}
				
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
									collapse+='<label class="radio-inline"><input type="radio" divId="ConsolidatedView'+dataArr[i]+'" level-id="'+levelId+'" name="collapseConsolidatedViewRadio'+dataArr[i]+'" overview-level="'+dataArr[i]+'"  checked consolidated-view="grounded"/> Grounded</label>';
									collapse+='<label class="radio-inline"><input type="radio" overview-level="'+dataArr[i]+'"  divId="ConsolidatedView'+dataArr[i]+'" level-id="'+levelId+'" name="collapseConsolidatedViewRadio'+dataArr[i]+'" consolidated-view="completed"/> Completed</label>';
									collapse+='<div class="m_top20" id="collapseConsolidatedView'+dataArr[i]+'"></div>';
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
	getNREGSLevelWiseConsolidatedReportConsolidated(levelId,locationType,subLocType,locId,divId,districtId,'grounded');
	
}
$(document).on("click","[consolidated-view]",function(){
	var searchType = $(this).attr("consolidated-view");
	var levelId = $(this).attr("level-id");
	var tableId = $(this).attr("divId");
	var divId = $(this).attr("overview-level");
	var locId = $("#selectedName").attr("attr_id");
	var locationType = '';
	var districtId = $("#selectedName").attr("attr_distId");
	if(levelId == 2)
	{
		locationType = 'state';
		subLocType = 'state';
		districtId = '';
	}else if(levelId == 3)
	{
		locationType = 'district';
		subLocType = 'district';
		districtId = '';
	}else if(levelId == 4)
	{
		locationType = 'constituency';
		subLocType = 'constituency';
		districtId = '';
	}
	if(divId == 'state')
	{
		subLocType = 'state';
	}else if(divId == 'district')
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
	getNREGSLevelWiseConsolidatedReportConsolidated(levelId,locationType,subLocType,locId,tableId,districtId,searchType);
});
function getNREGSLevelWiseConsolidatedReportConsolidated(levelId,locationType,subLocationType,locationId,divId,districtId,searchType)
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
		searchType : searchType,
		program : "-1"
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
	//tableView+='<button class="exportToPdf btn btn-success" attr_id="dataTable'+divId+'">Export To PDF</button>';
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered dataTable'+divId+'">';
			tableView+='<thead class="text-capital">';
				tableView+='<th>'+subLocationType+'</th>';
				for(var i in result[0].subList)
				{
					if(result[0].subList[i].component != null && result[0].subList[i].component == 'IHHL'){
						tableView+='<th>Individual HouseHold Latrines</th>';
					}else if(result[0].subList[i].component != null && result[0].subList[i].component == 'GH'){
						tableView+='<th>Greening of Hillocks</th>';
					}else if(result[0].subList[i].component != null && result[0].subList[i].component == 'Imp to CD'){
						tableView+='<th>Improvements to Check Dams and Percolation Tanks</th>';
					}else if(result[0].subList[i].component != null && result[0].subList[i].component == 'MPT_PT'){
						tableView+='<th>Mini Percolation Tanks and Percolation Tanks</th>';
					}else if(result[0].subList[i].component != null && result[0].subList[i].component == 'GC Works'){
						tableView+='<th>Gully Control Works</th>';
					}else if(result[0].subList[i].component != null && result[0].subList[i].component == 'CD_CW'){
						tableView+='<th>Check Dams and Check Walls</th>';
					}else if(result[0].subList[i].component != null && result[0].subList[i].component == 'NTR Rural House'){
						tableView+='<th>NTR IHHL</th>';
					}else{
						if(result[0].subList[i].component !=null && result[0].subList[i].component.length>15){
							tableView+='<th><span class="tooltipMGCls" style="cursor:pointer;" data-toogle="tooltip" data-placement="bottom" title="'+result[0].subList[i].component+'">'+result[0].subList[i].component.substring(0, 15)+'...</span></th>';
						}else{
							tableView +='<th>'+result[0].subList[i].component+'</th>';
						}
						
					}
					
				}
				tableView+='<th>Gold </th>';
				tableView+='<th>Green </th>';
				tableView+='<th>Orange</th>';
				tableView+='<th>Red</th>';
			tableView+='</thead>';
			
 			tableView+='<tbody>';
				for(var i in result)
				{
					var redColor = 0;
					var GreenColor = 0;
					var OrangeColor = 0;
					var GoldColor = 0;
					tableView+='<tr>';
						tableView+='<td class="text-capital">'+result[i].subList[0].name+'</td>';
						for(var j in result[i].subList)
						{
							if(result[i].subList[j].percentage != null)
							{
								if(result[i].subList[j].component == 'Agriculture Activities')
								{
									if(result[i].subList[j].percentage < 60)
									{
										tableView+='<td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+result[i].subList[j].percentage+'</td>';
										redColor = redColor + 1;
									}else if(result[i].subList[j].percentage >= 60)
									{
										tableView+='<td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+result[i].subList[j].percentage+'</td>';
										GreenColor = GreenColor + 1;
									}
								}else{
									
									if(result[i].subList[j].percentage >= 100){
										tableView+='<td class="color_gold" my_color="#f7b519" style="background-color:#f7b519;color#fff">'+result[i].subList[j].percentage+'</td>';
										GoldColor = GoldColor + 1;
									}else if(result[i].subList[j].percentage >= 90 && result[i].subList[j].percentage < 100){
										tableView+='<td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+result[i].subList[j].percentage+'</td>';
										GreenColor = GreenColor + 1;
									}else if(result[i].subList[j].percentage >= 60 && result[i].subList[j].percentage < 90){
										tableView+='<td class="color_medium" my_color="#ff6600" style="background-color:#ff6600;color#fff">'+result[i].subList[j].percentage+'</td>';
										OrangeColor = OrangeColor + 1;
									}else{
										tableView+='<td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+result[i].subList[j].percentage+'</td>';
										redColor = redColor + 1;
									}
									
									/* if(result[i].subList[j].percentage < 50)
									{
										tableView+='<td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+result[i].subList[j].percentage+'</td>';
										redColor = redColor + 1;
									}else if(result[i].subList[j].percentage >= 50 && result[i].subList[j].percentage < 80)
									{
										tableView+='<td class="color_medium" my_color="#ff6600" style="background-color:#ff6600;color#fff">'+result[i].subList[j].percentage+'</td>';
										OrangeColor = OrangeColor + 1;
									}else if(result[i].subList[j].percentage >= 80)
									{
										tableView+='<td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+result[i].subList[j].percentage+'</td>';
										GreenColor = GreenColor + 1;
									} */
								}
								
							}else{
								tableView+='<td>-</td>';
							}
						}
						tableView+='<td class="color_gold" my_color="#f7b519" style="background-color:#f7b519;color#fff">'+GoldColor+'</td><td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+GreenColor+'</td><td class="color_medium" my_color="#ff6600" style="background-color:#ff6600;color#fff">'+OrangeColor+'</td><td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+redColor+'</td>';
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	tableView+='<table class="table table-bordered" id="dataTable'+divId+'" style="display:none;">';
			tableView+='<thead class="text-capital">';
				tableView+='<th>'+subLocationType+'</th>';
				for(var i in result[0].subList)
				{
					tableView+='<th style="font-size:8px">'+result[0].subList[i].component+'</th>';
				}	
				tableView+='<th>Gold </th>';	
				tableView+='<th>Green </th>';
				tableView+='<th>Orange</th>';
				tableView+='<th>Red</th>';
			tableView+='</thead>';
			
 			tableView+='<tbody>';
				for(var i in result)
				{
					var redColor = 0;
					var GreenColor = 0;
					var OrangeColor = 0;
					var GoldColor = 0;
					tableView+='<tr>';
						tableView+='<td style="font-size:12px" class="text-capital">'+result[i].subList[0].name+'</td>';
						for(var j in result[i].subList)
						{
							if(result[i].subList[j].percentage != null)
							{
								if(result[i].subList[j].component == 'Agriculture Activities')
								{
									if(result[i].subList[j].percentage < 60)
									{
										tableView+='<td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+result[i].subList[j].percentage+'</td>';
										redColor = redColor + 1;
									}else if(result[i].subList[j].percentage >= 60)
									{
										tableView+='<td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+result[i].subList[j].percentage+'</td>';
										GreenColor = GreenColor + 1;
									}
								}else{
									
									if(result[i].subList[j].percentage >= 100){
										tableView+='<td class="color_gold" my_color="#f7b519" style="background-color:#f7b519;color#fff">'+result[i].subList[j].percentage+'</td>';
										GoldColor = GoldColor + 1;
									}else if(result[i].subList[j].percentage >= 90 && result[i].subList[j].percentage < 100){
										tableView+='<td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+result[i].subList[j].percentage+'</td>';
										GreenColor = GreenColor + 1;
									}else if(result[i].subList[j].percentage >= 60 && result[i].subList[j].percentage < 90){
										tableView+='<td class="color_medium" my_color="#ff6600" style="background-color:#ff6600;color#fff">'+result[i].subList[j].percentage+'</td>';
										OrangeColor = OrangeColor + 1;
									}else{
										tableView+='<td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+result[i].subList[j].percentage+'</td>';
										redColor = redColor + 1;
									}
									
									
									/* if(result[i].subList[j].percentage < 50)
									{
										tableView+='<td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+result[i].subList[j].percentage+'</td>';
										redColor = redColor + 1;
									}else if(result[i].subList[j].percentage >= 50 && result[i].subList[j].percentage < 80)
									{
										tableView+='<td class="color_medium" my_color="#ff6600" style="background-color:#ff6600;color#fff">'+result[i].subList[j].percentage+'</td>';
										OrangeColor = OrangeColor + 1;
									}else if(result[i].subList[j].percentage >= 80)
									{
										tableView+='<td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+result[i].subList[j].percentage+'</td>';
										GreenColor = GreenColor + 1;
									} */
								}
							}else{
								tableView+='<td>-</td>';
							}
						}
						tableView+='<td class="color_gold" my_color="#f7b519" style="background-color:#f7b519;color#fff">'+GoldColor+'</td><td class="color_high" my_color="#00AF50" style="background-color:#00AF50;color#fff">'+GreenColor+'</td><td class="color_medium" my_color="#ff6600" style="background-color:#ff6600;color#fff">'+OrangeColor+'</td><td class="color_low" my_color="#FF0000" style="background-color:#FF0000;color#fff">'+redColor+'</td>';
					tableView+='</tr>';
				}
			tableView+='</tbody>';
		tableView+='</table>';
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
				//extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o exportToPdf" attr_id="dataTable'+divId+'"></i>',
				titleAttr: 'PDF',
				//title:	   divId,
				//filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				//orientation: "landscape",
				//pageSize:'A3',
				//customize: function (doc) {
				//	doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				//}
			}
		]
	});
	
	$("a.dt-button,.tooltipMGCls").tooltip({placement:'right'});
}


$(document).on("click",".exportToPdf",function(){
	var id = $(this).attr("attr_id");
	getPdf(id);
});
function getPdf(id)
{
	var 
		form = $("#"+id),
		cache_width = form.width(),
		a1  = [ 70160  , 89933];  // for a4 size paper width and height
	createPDF()
	
	//create pdf
	function createPDF(){
		getCanvas().then(function(canvas){
			var 
			img = canvas.toDataURL("image/png"),
			doc = new jsPDF({
				unit:'px', 
				format:'a0'
			});     
			doc.addImage(img, 'JPEG', 05, 05);
			doc.save(''+id+'.pdf');
			form.width(cache_width);
		});
		$("#"+id).hide();
	}

	// create canvas object
	function getCanvas(){
		//form.width((a1[0]*1.33333) -80).css('max-width','none');
		$("#"+id).show();
		form.width(a1).css('max-width','none');
		return html2canvas(form,{
			imageTimeout:100,
			removeContainer:true
		});	
	}	
}