var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var orgChart = '';
//var departmentWiseArr=[{name:'Promotions',id:'1',color:'#0D3B54',image:'promotions',blockName:'promotions'},{name:'E Office',id:'2',color:'#1394B9',image:'eOffice',blockName:'eOffice'},{name:'Meeseva & SLA',id:'3',color:'#638D00',image:'meeseva',blockName:'meesevaSla'},{name:'Meeseva & KPI',id:'4',color:'#9B7A00',image:'meesevaHigh',blockName:'meesevaKpi'},{name:'eProcurement',id:'5',color:'#F06C1F',image:'eProcurement',blockName:'eProcurement'},{name:'CM eoDB',id:'6',color:'#C02D1D',image:'cMeoDB',blockName:'cMeoDB'}];

var departmentWiseArr = [{name:'Promotions',id:'1',color:'#0D3B54',image:'promotions',blockName:'promotions'},{name:'e Office',id:'2',color:'#1394B9',image:'eOffice',blockName:'eOffice'},{name:'Meeseva - SLA/KPI',id:'3',color:'#638D00',image:'meeseva',blockName:'meesevaSlaKpi'},{name:'AP Innovation Society',id:'7',color:'#F06C1F',image:'apInnovationSociety',blockName:'apInnovationSociety'},{name:'CM EODB',id:'6',color:'#C02D1D',image:'cMeoDB',blockName:'cMeoDB'},{name:'Bio-Metric',id:'8',color:'#9B7A00',image:'BioMetric',blockName:'BioMetric'}];

var globalFromDateSLA = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var globalToDateSLA = moment().format("DD/MM/YYYY");

var globalFromDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var globalToDate = moment().format("DD/MM/YYYY");
var globalDeptCode = "27001701024";
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});
$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});
$(document).on("click","#promotionsBlockSwitch li",function(){	
	$("#promotionsBlockSwitch li").removeClass("active");
	$(this).addClass("active");
	var typeOfBlock = $(this).attr("attr_type");
	if(typeOfBlock != 'Total')
	{
		$("[promotions]").hide();
		$("[promotions="+typeOfBlock+"]").show();
	}else{
		$("[promotions]").show();
	}
	getITSectorWiseOverviewDetails();
	getITSectorCategoryWiseDetails("RED",typeOfBlock);
	getITSectorCategoryWiseDetails("GREEN",typeOfBlock);
	getITSectorCategoryWiseDetails("DROPPED",typeOfBlock);
	getITSectorLeadCategoryWiseDetails("RED",typeOfBlock);
	getITSectorLeadCategoryWiseDetails("GREEN",typeOfBlock);
	getITSectorLeadCategoryWiseDetails("DROPPED",typeOfBlock);
});
function highcharts(id,type,data,plotOptions,title,tooltip,legend){
	'use strict';
	$('#'+id).highcharts({
		chart: type,
		title: title,
		tooltip:tooltip,
		subtitle: {
			text: null
		},
		plotOptions: plotOptions,
		legend:legend,
		series: data
	});
}
onloadCalls();
function onloadCalls(){
	if(searchParams == null)
	{
		departmentBlockWiseDetails("promotions");
	}else{
		departmentBlockWiseDetails(searchParams);
	}
	
	departmentWiseOverView();
	getITSectorWiseOverviewDetails();
	//getMeesevaSLAOverviewDtls("meesevaSla",5);
	getMeesevaSLACatWiseAbstarctDetails("meesevaSla",5,"onload")
	getMeesevaKPIOverViewDetails("onload","","");
	
	//AP Innovation Society Ajax Call Start
	getAPInnovationSocietyOverview('onload','apInnovationSociety');
	getEOfcDepartWiseOverviewDetails('onload');
	getCMEDOBOverview("","","overview");
	getBioMetricDashboardOverViewDtls();
	
	
}
$(document).on("click",".cohortIdClick",function(){
	$("#modalId").modal('show');
	var id = $(this).attr("attr_id");
	getCohortDetailsByCohortId(id);
});
function departmentWiseOverView(){
	var block='';
	for(var i in departmentWiseArr){
		if(departmentWiseArr[i].id ==8){
			block+='<div class="col-sm-2 m_top10 biometricTextAlign">';
					if(searchParams == null)
					{
						block+='<a href="bioMetricDashBoard"  target="_blank"><div style="background-color:'+departmentWiseArr[i].color+';cursor:pointer;color: #ffffff;padding: 10px;" class="" main-block="'+departmentWiseArr[i].blockName+'" attr_block_name="'+departmentWiseArr[i].blockName+'">';
					}else{
						if(searchParams == departmentWiseArr[i].blockName)
						{
							block+='<a href="bioMetricDashBoard"  target="_blank"><div style="background-color:'+departmentWiseArr[i].color+';cursor:pointer;color: #ffffff;padding: 10px;" class="active " main-block="'+departmentWiseArr[i].blockName+'" attr_block_name="'+departmentWiseArr[i].blockName+'">';
						}else{
							block+='<a href="bioMetricDashBoard"  target="_blank"><div style="background-color:'+departmentWiseArr[i].color+';cursor:pointer;color: #ffffff;padding: 10px;" class="" main-block="'+departmentWiseArr[i].blockName+'" attr_block_name="'+departmentWiseArr[i].blockName+'">';
						}
					}
					block+='<div class="media">';
						block+='<div class="media-left">';
							block+='<img src="Assests/icons/ITC/'+departmentWiseArr[i].image+'.png" class="media-object" style="height:20px;width:20px;">';
						block+='</div>';
						block+='<div class="media-body">';
							block+='<h5><b>'+departmentWiseArr[i].name+'</b></h5>';
						block+='</div>';
					block+='</div>';	
					
					block+='<div class="m_top20">';
						block+='<h3 id="bioMetricBlockId"></h3>';
						block+='<h6 class="m_top10">TotalEmployee/Present</h6>';
					block+='</div>';
					
				block+='</div></a>';
			block+='</div>';
		}else{
			block+='<div class="col-sm-2 m_top10">';
				if(searchParams == null)
				{
					if(i == 0)
					{
						block+='<div style="background-color:'+departmentWiseArr[i].color+';cursor:pointer" class="active block_style_ITC blockWiseDetails" main-block="'+departmentWiseArr[i].blockName+'" attr_block_name="'+departmentWiseArr[i].blockName+'">';
					}else{
						block+='<div style="background-color:'+departmentWiseArr[i].color+';cursor:pointer" class="block_style_ITC blockWiseDetails" main-block="'+departmentWiseArr[i].blockName+'" attr_block_name="'+departmentWiseArr[i].blockName+'">';
					}
				}else{
					if(searchParams == departmentWiseArr[i].blockName)
					{
						block+='<div style="background-color:'+departmentWiseArr[i].color+';cursor:pointer" class="active block_style_ITC blockWiseDetails" main-block="'+departmentWiseArr[i].blockName+'" attr_block_name="'+departmentWiseArr[i].blockName+'">';
					}else{
						block+='<div style="background-color:'+departmentWiseArr[i].color+';cursor:pointer" class="block_style_ITC blockWiseDetails" main-block="'+departmentWiseArr[i].blockName+'" attr_block_name="'+departmentWiseArr[i].blockName+'">';
					}
				}
				
				
						block+='<div class="media">';
							block+='<div class="media-left">';
								block+='<img src="Assests/icons/ITC/'+departmentWiseArr[i].image+'.png" class="media-object" style="height:20px;width:20px;">';
							block+='</div>';
							block+='<div class="media-body">';
							block+='<h5><b>'+departmentWiseArr[i].name+'</b></h5>';
							if(departmentWiseArr[i].id ==3){
								//block+='<h6 style="font-size:8px;color:#d3d3d3;">Department & District Wise SLA Monitoring</h6>';
							}else if(departmentWiseArr[i].id ==4){
								block+='<h6 style="font-size:8px;color:#d3d3d3;">Highest Performance</h6>';
							}
						  block+='</div>';
						block+='</div>';
						if(departmentWiseArr[i].id ==1){
							block+='<div class="m_top20">';
								block+='<h3 id="promotionsHeadingId"></h3>';
								block+='<h6>Committed Investment</h6>';
							block+='<h6>(IT,E&F)</h6>';
							block+='</div>';
						}else if(departmentWiseArr[i].id ==2){
							block+='<div class="m_top20">';
								block+='<h3 id="itcDeptWiseCount"></h3>';
								block+='<h6 class="m_top10">Total Pendency</h6>';
							block+='</div>';
						}else if(departmentWiseArr[i].id ==3){
							block+='<div class="m_top20">';
								block+='<h6 class="m_top10">Beyond SLA <span id="meesevaHeadingId" class="pull-right" style="font-size: 18px;"></span> </h6>';
								block+='<h6 class="m_top20">eTaal - KPI <span id="meesevaKPIHeadingId" class="pull-right" style="font-size: 18px;"></span> </h6>';
							block+='</div>';
						}else if(departmentWiseArr[i].id ==4){
							block+='<div class="m_top20">';
								block+='<h3>3499</h3>';
								block+='<h6 class="m_top10">eTaal - KPI</h6>';
							block+='</div>';
						}else if(departmentWiseArr[i].id ==5){
							block+='<div class="m_top20">';
								block+='<h3>11,25.Cr</h3>';
								block+='<h6 class="m_top10">IT&E Department Tenders</h6>';
							block+='</div>';
						}else if(departmentWiseArr[i].id ==6){
							block+='<div class="m_top5">';
								block+='<h4 id="cMeoDBTotalId"></h4>';
								block+='<h3 id="cMeoDBApprovedId"></h3>';
								block+='<h6 class="m_top10">Total / Approved</h6>';
							block+='</div>';
						}else if(departmentWiseArr[i].id ==7){
							block+='<div class="m_top20">';
								block+='<h3 id="apInnovationSociety"></h3>';
								block+='<h6 class="m_top10">Startups</h6>';
							block+='</div>';
						}
				block+='</div>';
			block+='</div>';
		}
		
	}
	$("#departmentWiseDivId").html(block);
	
}
$(document).on('click','.blockWiseDetails',function(){
	$(".blockWiseDetails").removeClass("active");
	$(this).addClass("active");
	$("#campusOverviewBlock,#APISXLr8APOverview,#campaignsOverviewBlock").html("");
	var blockName = $(this).attr("attr_block_name");
	departmentBlockWiseDetails(blockName);	
});

$(document).on('click','#droppedShowHideId',function(){
	$(".droppedClass").toggle();
});

function departmentBlockWiseDetails(divId)
{
	var levelWiseBlockArr='';
	if(divId == "promotions"){
		
		levelWiseBlockArr=[{name:'Promotions',id:'1'}]//,{name:'Electronics',id:'2'},{name:'FinTech',id:'3'}];
		
	}else if(divId == "eOffice"){
		
		levelWiseBlockArr=[{name:'e Office',id:'4'}];
		
	}else if(divId == "meesevaSlaKpi"){
		
		levelWiseBlockArr=[{name:'Meeseva-SLA/KPI',id:'5'}];//
		
	}else if(divId == "meesevaKpi"){
		
		levelWiseBlockArr=[{name:'Meeseva & KPI',id:'6'}];
		
	}else if(divId == "eProcurement"){
		levelWiseBlockArr = [{name:'eProcurement',id:'7'}];
		
	}else if(divId == "cMeoDB"){
		
		levelWiseBlockArr = [{name:'CM EODB',id:'8'}];
	}else if(divId == "apInnovationSociety"){
		
		levelWiseBlockArr = [{name:'AP Innovation Society',id:'9'}];
	}
	
	var collapse='';
		for(var i in levelWiseBlockArr)
		{
			collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" role="tablist" aria-multiselectable="true">';
				collapse+='<div class="panel panel-default panel-black">';
					collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseBlockArr[i].id+'">';
						if(i == 0)
						{
							collapse+='<a role="button" class="panelCollapseIcon '+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
						}else{
							collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'"  data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" href="#collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
						}
						collapse+='<h4 class="panel-title">'+levelWiseBlockArr[i].name+' Overview</h4>';
							
						collapse+='</a>';
					collapse+='</div>';
					if(i == 0)
					{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
					}else{
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+levelWiseBlockArr[i].id+'">';
					}
					
						collapse+='<div class="panel-body">';
							
							if(divId == 'apInnovationSociety')
							{
								collapse+='<h4 style="margin-bottom:20px">Applications Received</h4>';
							}
							if(divId == 'eOffice')
							{
								collapse+='<div class="row">';
										collapse+='<div id="eOfficeDeparmentsOverViewBlock"></div>';
								collapse+='</div>';
								
								collapse+='<div class="row m_top10">';
									collapse+='<div class="col-sm-12">';
										collapse+='<div id="hieraricalShowHideDiv"></div>';
									collapse+='</div>';	
								collapse+='</div>';
								
								collapse+='<div class="row m_top10">';
									collapse+='<div class="col-sm-12">';
										collapse+='<div id="hieraricalViewErr"></div>';
										collapse+='<div id="hieraricalView"></div>';
									collapse+='</div>';
								collapse+='</div>';
							}
							if(divId == "promotions")
							{
								collapse+='<div class="row">';
									collapse+='<div class="col-sm-12">';
										collapse+='<div class="row">';
											collapse+='<div class="col-sm-7">';
												collapse+='<h4>INFORMATION TECHNOLOGY ELECTRONICS AND COMMUNICATION OVERVIEW</h4>';
												collapse+='<hr/>';
											collapse+='</div>';
											collapse+='<div class="col-sm-3">';
												collapse+='<ul class="list-inline switch-btn" id="promotionsBlockSwitch">';
													collapse+='<li class="active" attr_type="Total">ALL</li>';
													collapse+='<li attr_type="Electronics">Electronics</li>';
													collapse+='<li attr_type="Fintech">Fintech</li>';
													collapse+='<li attr_type="IT">IT</li>';
												collapse+='</ul>';
											collapse+='</div>';
											collapse+='<div class="col-sm-2" id="">';
												collapse+='<i class="glyphicon glyphicon-download pull-right" id="addIcon" aria-hidden="true" style="cursor:pointer;font-size:35px;display:none;"></i>';
												collapse+='<div class="col-sm-12" id="promotionsStageDroppedBlockId" style="display:none;"></div>';
												collapse+='<div class="col-sm-12" id="promotionsStageDroppedBlockId1" style="display:none;"></div>';
											collapse+='</div>';
										collapse+='</div>';
									
									collapse+='</div>';
									collapse+='<div class="col-sm-3" id="promotionsTotalBlockId"></div>';
									collapse+='<div class="col-sm-4" id="">';
										collapse+='<div class="col-sm-12" id="promotionsStageGreenBlockId"></div>';
										collapse+='<div class="col-sm-12" id="promotionsStageGreenBlockId1"></div>';
									collapse+='</div>';
									collapse+='<div class="col-sm-4" id="">';
										collapse+='<div class="col-sm-12" id="promotionsStageRedBlockId"></div>';
										collapse+='<div class="col-sm-12" id="promotionsStageRedBlockId1"></div>';
									collapse+='</div>';
									collapse+='<div class="col-sm-12">';
										collapse+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
										var	levelWiseBlockArrPromotions =[{name:'IT',id:'1'},{name:'Electronics',id:'2'},{name:'Fintech',id:'3'}];
										for(var l in levelWiseBlockArrPromotions)
										{
											collapse+='<div class="panel panel-default m_top20" promotions="'+levelWiseBlockArrPromotions[l].name+'">';
												collapse+='<div class="panel-heading" role="tab" id="headingOne'+levelWiseBlockArrPromotions[l].name+'">';
													collapse+='<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne'+levelWiseBlockArrPromotions[l].name+'" aria-expanded="true" aria-controls="collapseOne'+levelWiseBlockArrPromotions[l].name+'">';
														collapse+='<h4 class="panel-title">'+levelWiseBlockArrPromotions[l].name+' Overview</h4>';
													collapse+='</a>';
												collapse+='</div>';
												collapse+='<div id="collapseOne'+levelWiseBlockArrPromotions[l].name+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+levelWiseBlockArrPromotions[l].name+'">';
													collapse+='<div class="panel-body">';
														collapse+='<div id="'+levelWiseBlockArrPromotions[l].name+'OverviewBlockCheckBoxId"></div>';
														collapse+='<div id="'+levelWiseBlockArrPromotions[l].name+'OverviewBlockDivId" class="m_top20"></div>';
													collapse+='</div>';
												collapse+='</div>';
											collapse+='</div>';
										}													
											
										collapse+='</div>';
									collapse+='</div>';
								collapse+='</div>';
							}
							if(divId == "cMeoDB")
							{
								collapse+='<div class="row">';	
									collapse+='<div class="col-sm-12">';
											collapse+='<ul class="list-inline pull-right">';
												  collapse+='<div class="input-group">';
													collapse+='<span class="input-group-addon">';
														collapse+='<i class="glyphicon glyphicon-calendar"></i>';
													collapse+='</span>';
													collapse+=' <input type="text" class="form-control" id="itcDateRangePickerId" style="width: 200px;"/>';
												collapse+=' </div>';
											collapse+='</ul>';
										collapse+='</div>';
									collapse+='</div>';
								
								collapse+='<div class="row m_top10">';	
									collapse+='<div class="col-sm-4">';
										collapse+='<div id="cmedobBlockMainDivId"></div>';
									collapse+='</div>';
									collapse+='<div class="col-sm-4">';
										collapse+='<div id="cmedobSectorWiseInformationId"></div>';
									collapse+='</div>';
									collapse+='<div class="col-sm-4">';
										collapse+='<div id="cmedobSectorWiseElectronicSectorId"></div>';
									collapse+='</div>';
								collapse+='</div>';
									
								/* collapse+='<div class="row">';	
									collapse+='<div class="col-sm-12 m_top10">';
										collapse+='<div id="cmedobSectorWiseStatusId"></div>';
									collapse+='</div>';
								collapse+='</div>';
								
								collapse+='<div class="row" style="margin-bottom:50px;">';
									collapse+='<div class="col-sm-12 m_top10" style="margin-bottom: 20px; padding-left: 0px; padding-right: 0px;">';
										collapse+='<div id="cmedobBlockMainDivId"></div>';
									collapse+='</div>';	
								collapse+='</div>'; */
								collapse+='<div class="row m_top20">';
									collapse+='<div class="col-sm-12">';
										collapse+='<div id="cmedobDepartmentBlockMainDivId"></div>';
									collapse+='</div>';	
								collapse+='</div>';
								collapse+='<div class="row m_top20" style="margin-top:60px;">';
									collapse+='<div class="col-sm-3">'
										collapse+='<h4><b>IT & E &nbsp Sector Wise Status Report</b></h4>';
									collapse+='</div>';
									collapse+='<div class="col-sm-4">'
										collapse+='<div class="form-group form-inline">';
										collapse+='<label class="col-sm-2  control-label" for="Sector" style="margin-top: 6px;">Sector: </label>';
												collapse+='<select class="form-control chosenSelect" id="sectorSelId">';
													collapse+='<option value="B">All</option>';
													collapse+='<option value="E">Electronics</option>';
													collapse+='<option value="I">Information Technology</option>';;
												collapse+='</select>';
										collapse+='</div>';
									collapse+='</div>';
									
								collapse+='</div>';
								
								collapse+='<div class="row">';
									collapse+='<div id="cmedobDivId"></div>';
								collapse+='</div>';
								
							}
							if(divId == "meesevaSlaKpi"){
								collapse+='<div class="row">';
									collapse+='<div class="col-sm-12">';
										collapse+='<ul class="list-inline switch-btn pull-right meesevaSlaKpiCls">';
											collapse+='<li class="active" attr_type="meesevaSla">SLA</li>';
											collapse+='<li attr_type="meesevaKpi">KPI</li>';
										collapse+='</ul>';		
									collapse+='</div>';
								collapse+='</div>';
							}
							collapse+='<div id="'+divId.replace(/\s+/g, '')+'Block'+levelWiseBlockArr[i].id+'"></div>';
							if(divId == 'apInnovationSociety')
							{

								collapse+='<div class="row">';
									collapse+='<div class="col-sm-4 m_top20" id="APISXLr8AP"></div>';
									collapse+='<div class="col-sm-4 m_top20" id="Campaigns"></div>';
									collapse+='<div class="col-sm-4 m_top20" id="CampusInnovationCenters"></div>';
								collapse+='</div>';
							}
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
			/* if(divId == 'eOffice')
			{
				collapse+='<div class="panel-group" id="accordionEOffc" role="tablist" aria-multiselectable="true">';
					collapse+='<div class="panel panel-default panel-black">';
						collapse+='<div class="panel-heading" role="tab" id="headingOneEOffc">';
							collapse+='<a role="button" class="panelCollapseIcon " data-toggle="collapse" data-parent="#accordionEOffc" href="#collapseOneEOffc" aria-expanded="true" aria-controls="collapseOneEOffc">';
								collapse+='<h4 class="panel-title">E-OFFICE PENDENCY STATUS</h4>';
							collapse+='</a>';
						collapse+='</div>';
						collapse+='<div id="collapseOneEOffc" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOneEOffc">';
							collapse+='<div class="panel-body">';
								collapse+='<div id="eOfficePendencyWise"></div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			} */
			
		}
	$("#departmentBlockWiseDetailsId").html(collapse);
	//CMEDOB
	$("#itcDateRangePickerId").daterangepicker({
		opens: 'left',
		startDate: globalFromDate,
		endDate: globalToDate,
		locale: {
			format: 'DD/MM/YYYY'
		},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().format("DD/MM/YYYY")],
			'Today' : [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
			'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
			'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
			'This Month': [moment().startOf('month'), moment()],
			'This Year': [moment().startOf('Year'), moment()]
		}
	});
	var dates= $("#itcDateRangePickerId").val();
	var pickerDates = globalFromDate+' - '+globalToDate
	if(dates == pickerDates)
	{
		$("#itcDateRangePickerId").val('All');
	}
	//MeesavaSLA
	$("#itcMessavaSlaDateRangePickerId").daterangepicker({
		opens: 'left',
		startDate: globalFromDateSLA,
		endDate: globalToDateSLA,
		locale: {
			format: 'DD/MM/YYYY'
		},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().format("DD/MM/YYYY")],
			'Today' : [moment(), moment()],
			'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
			'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
			'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
			'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
			'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
			'This Month': [moment().startOf('month'), moment()],
			'This Year': [moment().startOf('Year'), moment()]
		}
	});
	var dates= $("#itcMessavaSlaDateRangePickerId").val();
	var pickerDates = globalFromDateSLA+' - '+globalToDateSLA
	if(dates == pickerDates)
	{
		$("#itcMessavaSlaDateRangePickerId").val('All');
	}
	
	for(var i in levelWiseBlockArr){
		if(divId == "meesevaSlaKpi"){
			//getMeesevaSLAOverviewDtls(divId,levelWiseBlockArr[i].id);
			getMeesevaSLACatWiseAbstarctDetails(divId,levelWiseBlockArr[i].id,"change")
		}else if(divId == 'apInnovationSociety')
		{
			getAPInnovationSocietyOverview('overview',divId.replace(/\s+/g, '')+'Block'+levelWiseBlockArr[i].id);
			getAPISXLR8APDetailedData();
			getCampaignsDetailedData();
			getCampusInnovationCentersDetailedData();
		}else if(divId == 'eOffice')
		{
			getEOfcDepartWiseOverviewDetails('overview');
			//getEofficeDesignationWisePendencyDetails();
		}else if(divId == 'meesevaKpi'){
			getMeesavaKpiGraphBuild(divId,levelWiseBlockArr[i].id);
		}else if(divId == 'cMeoDB'){
			getCMEDOBOverview(divId,levelWiseBlockArr[i].id,"Detailed");
			getCMEDOBReportStatusWise("B");
			getCMeoDBSectorWiseStatusDetais();
		}
	}
	if(divId == 'promotions')
	{
		getITSectorWiseOverviewDetails();
		getITSectorCategoryWiseDetails("RED",'Total');
		getITSectorCategoryWiseDetails("GREEN",'Total');
		getITSectorCategoryWiseDetails("DROPPED",'Total');
		getITSectorLeadCategoryWiseDetails("RED",'Total');
		getITSectorLeadCategoryWiseDetails("GREEN",'Total');
		getITSectorLeadCategoryWiseDetails("DROPPED",'Total');
		getITDistrictWiseDetails("IT","ALL",'body');
		getITDistrictWiseDetails("Electronics","ALL",'body');
		getITDistrictWiseDetails("Fintech","ALL",'body');
	}
	
	$('#itcDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		globalFromDate = picker.startDate.format('DD/MM/YYYY');
		globalToDate = picker.endDate.format('DD/MM/YYYY'); 
		if(picker.chosenLabel == 'All')
		{
			$("#itcDateRangePickerId").val('All');
		}
		$("#sectorSelId").val("B");
		$("#cmedobSectorWiseInformationId,#cmedobSectorWiseElectronicSectorId,#cmedobBlockMainDivId,#cmedobDivId").html('');
		getCMEDOBOverview("cMeoDB",5,"Detailed");
		getCMEDOBReportStatusWise("B");
		getCMeoDBSectorWiseStatusDetais();
	});

	/* $('#itcMessavaSlaDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
		
		globalFromDateSLA = picker.startDate.format('DD/MM/YYYY');
		globalToDateSLA = picker.endDate.format('DD/MM/YYYY'); 
		if(picker.chosenLabel == 'All')
		{
			$("#itcMessavaSlaDateRangePickerId").val('All');
		}
			getMeesevaSLAOverviewDtls("meesevaSla",8);
	}); */
}

/* function getOverAllDetils(divId,blockId){
	
	var str='';
	
	if(divId == "promotions"){
		if(blockId == 1){
			str+='Promotionsfsdfsdfs';
		}else if(blockId == 2){
			str+='electronics';
		}else if(blockId == 3){
			str+='fintech';
		}
	}else if(divId == "eOffice"){
		str+='Promotionsdfsdfs';
	}else if(divId == "meesevaSla"){
		
		
		
	}else if(divId == "meesevaKpi"){
		str+='Promotiondasdsadada233sdfss';
	}else if(divId == "eProcurement"){
		str+='Promotionsadfse';
	}else if(divId == "cMeoDB"){
		str+='Promotiosadsaedsansf';
	} 
	$("#"+divId+"Block"+blockId).html(str);
} */
function getMeesevaSLAOverviewDtls(divId,blockId){
	$("#meesevaHeadingId").html(spinner);
	$("#"+divId+"Block"+blockId).html(spinner);
	var json = {
	    fromDate:globalFromDateSLA,
		toDate:globalToDateSLA,
		filterId:"2",//sending type in web service 2 means getting department wise data
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#meesevaHeadingId").html('');
	    $("#"+divId+"Block"+blockId).html('');
		if(result !=null && result.length>0){
			buildMeesevaSLAOverviewDtls(result,divId,blockId);
		}else {
			$("#"+divId+"Block"+blockId).html('NO DATA AVAILABLE.');
		}
		getMeesevaSLAMonitoringDtlsDepartmentWise(divId,blockId);
	});		
}

function buildMeesevaSLAOverviewDtls(result,divId,blockId){
	var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12">';
				for(var i in result){
					str+='<div class="col-sm-4">';
						str+='<div class="white_block_ITC">';
							if(result[i].name == "TOTAL TRANSACTIONS"){
								str+='<div style="border-left:5px solid #000;">';
							}else if(result[i].name == "With in SLA"){
								str+='<div style="border-left:5px solid #009587;">';
							}else if(result[i].name == "Beyond SLA"){
								$("#meesevaHeadingId").html(result[i].totalCount);
								str+='<div style="border-left:5px solid #F75C5D;">';
							}
							
								str+='<h4 class="m_left10"><b>'+result[i].name+'</b></h4>';
								str+='<h5 class="m_left10">('+result[i].departmentCount+' DEPARTMENTS)</h5>';
								str+='<h1 class="m_top10 m_left10">'+result[i].totalCount+'</h1>';
							str+='</div>';
							str+='<div class="row m_top20">';
								str+='<div class="col-sm-12 white_block_Dep">';
									for(var j in result[i].subList){
										str+='<div class="col-sm-6">';
											str+='<h5 class="m_top5"><b>'+result[i].subList[j].name+'</b>';
											str+='<h3 class="m_top10">'+result[i].subList[j].totalCount+'</h3>';
										str+='</div>';
									}
								str+='</div>';	
							str+='</div>';
						str+='</div>';
						str+='</div>';
				}
			str+='</div>';
		str+='</div>';
		str+='<div class="m_top20">';
			str+='<div class="col-sm-12">';
				str+='<div class="white_block_ITC">';
					str+='<h3>MEESEVA SLA MONITORING</h3>';
					str+='<div id="meesevaSalTable'+divId+''+blockId+'"></div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	$("#"+divId+"Block"+blockId).html(str);
}

function getMeesevaSLAMonitoringDtlsDepartmentWise(divId,blockId){
	$("#meesevaSalTable"+divId+blockId).html(spinner);
	var json = {
		fromDate:globalFromDate,
		toDate:globalToDate,
		year:"",
		filterId:"2"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAMonitoringDtlsDepartmentWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 if (result != null && result.length > 0) {
			 buildMeesevaSlaMonitoringDtls(result,divId,blockId);
		 } 
	});		
}
function buildMeesevaSlaMonitoringDtls(result,divId,blockId) {
	var str = '';
	str+='<div class="table-responsive m_top20">';	
	str+='<table class="table table-bordered" id="meesevaSlaMonitoringDataTblId">';
		str+='<thead>';
			str+='<tr>';
				str+='<th rowspan="2">Departments</th>';
					str+='<th colspan="2" style="text-align:center;">Category - A</th>';
					str+='<th colspan="6" style="text-align:center;">Category - B</th>';
				str+='</tr>';
				
				str+='<tr>';
					str+='<th>Grand Total</th>';
					str+='<th>Total</th>';
					str+='<th>Total</th>';
					str+='<th>Approved</th>';
					str+='<th>Rejected</th>';
					str+='<th>Pending with in SLA</th>';
					str+='<th>Pending Beyond SLA</th>';
					str+='<th>Revoked</th>';
			str+='</tr>';
			
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
					str+='<tr>';
					 str+='<td>'+result[i].name+'</td>';
					 str+='<td>'+result[i].totalTransactionCount+'</td>';
					 str+='<td>'+result[i].cateoryA+'</td>';
					 str+='<td>'+result[i].categoryB+'</td>';
					 str+='<td>'+result[i].bApproved+'</td>';
					 str+='<td>'+result[i].bRejected+'</td>';
					 str+='<td>'+result[i].pendingWithinSla+'</td>';
					 str+='<td>'+result[i].pendingBeyondSla+'</td>';
					 str+='<td>'+result[i].revoked+'</td>';
				 str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	str+='</div>';
	$("#meesevaSalTable"+divId+blockId).html(str);
	$("#meesevaSlaMonitoringDataTblId").dataTable();
}

function getITSectorWiseOverviewDetails(){
	$("#promotionsTotalBlockId").html(spinner);
	var json = {
		category:'ALL'
	}
	$.ajax({                
		type:'POST',    
		url: 'getITSectorCategoryWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result);
		}
	});		
	function buildData(result)
	{
		var selectedBlockType = $("#promotionsBlockSwitch li.active").attr("attr_type");
		for(var i in result)
		{
			if(result[i].sector == "Total")
			{
				$("#promotionsHeadingId").html(result[i].investment+" Cr");
			}
			
			var str = '';
			var str1 = '';
			if(selectedBlockType == result[i].sector)
			{
				str1+='<div class="white_block_ITC" style="background-color:#F1F1F1">';
					str1+='<p>TOTAL</p>';
					str1+='<div class="media m_top40">';
						str1+='<div class="media-left">';
							str1+='<img src="Assests/icons/ITC/Group 2818.png" class="media-object"/>';
						str1+='</div>';
						str1+='<div class="media-body">';
							str1+='<p>Industry Count</p>';
							str1+='<h3 class="m_top10">'+result[i].noProjects+'</h3>';
						str1+='</div>';
					str1+='</div>';
					str1+='<div class="media m_top40">';
						str1+='<div class="media-left">';
							str1+='<img src="Assests/icons/ITC/Group 2817.png" class="media-object"/>';
						str1+='</div>';
						str1+='<div class="media-body">';
							str1+='<p>Commited Investments</p>';
							str1+='<h3 class="m_top10">'+result[i].investment+' Cr</h3>';
						str1+='</div>';
					str1+='</div>';
					str1+='<div class="media m_top40">';
						str1+='<div class="media-left">';
							str1+='<img src="Assests/icons/ITC/Group 2813.png" class="media-object"/>';
						str1+='</div>';
						str1+='<div class="media-body">';
							str1+='<p>Commited Employment</p>';
							str1+='<h3 class="m_top10">'+result[i].employment+'</h3>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
				/* str1+='<ul class="list-inline m_top10">';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#058E46;color:#fff">G1</span>Gone into Production';
					str1+='</li>';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#058E46;color:#fff">G2</span>Trial Production	';
					str1+='</li>';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#058E46;color:#fff">G4</span>Civil Works commenced	';
					str1+='</li>';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#058E46;color:#fff">Y</span>Ready for Foundation Stone';
					str1+='</li>';
				str1+='</ul>';
				str1+='<ul class="list-inline m_top20">';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#F75C5D;color:#fff">R1</span>Land in possession and approvals granted	';
					str1+='</li>';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#F75C5D;color:#fff">R2</span>Land in possession and approvals in progress	';
					str1+='</li>';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#F75C5D;color:#fff">R3</span>Government land sought, but not allocated	';
					str1+='</li>';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#F75C5D;color:#fff">R4</span>DPR to be submitted';
					str1+='</li>';
				str1+='</ul>';
				str1+='<ul class="list-inline m_top20">';
					str1+='<li>';
						str1+='<span class="roundCircle" style="background-color:#91CCC7;color:#fff">D</span>Dropped';
					str1+='</li>';
				str1+='</ul>'; */
				$("#promotionsTotalBlockId").html(str1);
			}
		}
	}
}
$(document).on("click",".overview-click",function(){
	var selectedBlockType = $("#promotionsBlockSwitch li.active").attr("attr_type");
	var categoryType = $(this).attr("attr_category");
	if(categoryType != null)
	{
		getITSectorSubLeadCategoryWiseDetails(selectedBlockType,categoryType)
	}else{
		getITDistrictWiseDetails(selectedBlockType,$(this).attr("attr_type"),'modal');
	}
});
function getITSectorCategoryWiseDetails(type,typeOfBlock){
	if(type == "GREEN")
	{
		$("#promotionsStageGreenBlockId").html(spinner);
	}else if(type == "RED")
	{
		$("#promotionsStageRedBlockId").html(spinner);
	}else if(type == "DROPPED")
	{
		$("#promotionsStageDroppedBlockId").html(spinner);
		$("#droppedDataFormModal").html(spinner);
	}
	var json = {
		category:type
	}
	$.ajax({                
		type:'POST',    
		url: 'getITSectorCategoryWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			//if(typeOfBlock == 'Total')
			//{
				//getITSectorLeadCategoryWiseDetails(type,typeOfBlock);
			//}
			return buildData(result,type);
		}else{
			if(type == "GREEN")
			{
				$("#promotionsStageGreenBlockId").html("");
			}else if(type == "RED")
			{
				$("#promotionsStageRedBlockId").html("");
			}else if(type == "DROPPED")
			{
				$("#promotionsStageDroppedBlockId").html("");
				$("#addIcon").hide();
			}
		}
	});		
	function buildData(result,type)
	{
		var str='';
		var selectedBlockType = $("#promotionsBlockSwitch li.active").attr("attr_type");
		
		for(var i in result)
		{
			if(selectedBlockType == result[i].sector)
			{
				if(type == "GREEN")
				{
					str+='<div class="white_block_ITC" style="padding:5px 10px;background-color:#058E46;color:#fff">';
						str+='<p class="text-center">';
							str+='<span>Civil Works commencement and beyond</span>';
						str+='</p>';
					str+='</div>';
				}else if(type == "RED")
				{	
					str+='<div class="white_block_ITC" style="padding:5px 10px;background-color:#F75C5D;color:#fff">';
						str+='<p class="text-center">';
							str+='<span>Before Civil Works commencement</span>';
						str+='</p>';
					str+='</div>';
				}else if(type == "DROPPED")
				{	
					str+='<div class="white_block_ITC" style="padding:5px 10px;background-color:#91CCC7;color:#fff;cursor:pointer;display:none;" id="droppedShowHideId">';
						str+='<i class="fa fa-plus" id="addIcon" aria-hidden="true"></i>';
					str+='</div>';
				}
					//str+='<div class="white_block_ITC m_top20" style="background-color:#F1F1F1">';
						if(type == "GREEN")
						{
							str+='<div class="white_block_ITC m_top10" style="background-color:#F1F1F1;padding:8px;">';
							str+='<p>';
								str+='<span style="padding:5px 10px;background-color:#058E46;color:#fff">Overall</span>';
							str+='</p>';
						}else if(type == "RED")
						{
							str+='<div class="white_block_ITC m_top10" style="background-color:#F1F1F1;padding:8px;">';
							str+='<p>';
								str+='<span style="padding:5px 10px;background-color:#F75C5D;color:#fff">Overall</span>';
							str+='</p>';
						}else if(type == "DROPPED")
						{
							str+='<div class="white_block_ITC m_top10 droppedClass" style="background-color:#F1F1F1;padding:8px;">';
								str+='<p>';
									str+='<span style="padding:5px 10px;background-color:#91CCC7;color:#fff">Overall</span>';
								str+='</p>';
						}
						str+='<div class="row m_top10">';
							str+='<div class="col-sm-4">';
								str+='<h4 class="overview-click" style="cursor:pointer;" attr_type="'+type+'">'+result[i].noProjects+'</h4>';
								str+='<p><small>INDUSTRIES</small></p>';
							str+='</div>';
							str+='<div class="col-sm-4">';
								str+='<h4 style="font-size:16px;">'+result[i].investment+'&nbsp;Cr</h4>';
								str+='<p><small>INVESTMENTS</small></p>';
							str+='</div>';
							str+='<div class="col-sm-4">';
								str+='<h4>'+result[i].employment+'</h4>';
								str+='<p><small>EMPLOYMENT</small></p>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				
			}
			
			if(type == "GREEN")
			{
				$("#promotionsStageGreenBlockId").html(str);
			}else if(type == "RED")
			{
				$("#promotionsStageRedBlockId").html(str);
			}else if(type == "DROPPED")
			{
				$("#promotionsStageDroppedBlockId").html(str);
				$('#droppedDataFormModal').html(str);
				$("#addIcon").show();
			}
		}
	}
	
}
function getITSectorLeadCategoryWiseDetails(type,sector){
	if(type == "GREEN")
	{
		$("#promotionsStageGreenBlockId1").html(spinner);
	}else if(type == "RED")
	{
		$("#promotionsStageRedBlockId1").html(spinner);
	}else if(type == "DROPPED")
	{
		$("#promotionsStageDroppedBlockId1").html(spinner);
		$("#droppedDataFormModal1").html(spinner);
	}
	var json = {
		leadName:"0",
		category:type,
		sector:sector
	}
	$.ajax({                
		type:'POST',    
		url: 'getITSectorLeadCategoryWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result,type);
		}else{
			if(type == "GREEN")
			{
				$("#promotionsStageGreenBlockId1").html("");
			}else if(type == "RED")
			{
				$("#promotionsStageRedBlockId1").html("");
			}else if(type == "DROPPED")
			{
				$("#promotionsStageDroppedBlockId1").html("");
				$("#addIcon").hide();
			}
		}
	});
	function buildData(result,type)
	{
		var str='';
		var selectedBlockType = $("#promotionsBlockSwitch li.active").attr("attr_type");
		for(var i in result)
		{
			//str+='<div class="white_block_ITC m_top20 droppedClass" style="background-color:#F1F1F1">';
				if(type == "GREEN")
				{
					str+='<div class="white_block_ITC m_top10" style="background-color:#F1F1F1;padding:8px;">';
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#058E46;color:#fff">'+result[i].category+' <span style="font-size:12px;"> - '+result[i].name+'</span> </span>';
					str+='</p>';
				}else if(type == "RED")
				{
					str+='<div class="white_block_ITC m_top10" style="background-color:#F1F1F1;padding:8px;">';
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#F75C5D;color:#fff">'+result[i].category+' <span style="font-size:12px;"> - '+result[i].name+'</span> </span>';
					str+='</p>';
				}else if(type == "DROPPED")
				{
					str+='<div class="white_block_ITC m_top10 droppedClass" style="background-color:#F1F1F1;padding:8px;">';
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#91CCC7;color:#fff">'+result[i].category+' <span style="font-size:12px;"> - '+result[i].name+'</span> </span>';
					str+='</p>';
				}
				str+='<div class="row m_top10">';
					str+='<div class="col-sm-4">';
						str+='<h4 class="overview-click" attr_category="'+result[i].category+'" style="cursor:pointer;" attr_type="'+type+'">'+result[i].categoryCount+'</h4>';
						str+='<p><small>INDUSTRIES</small></p>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<h4 style="font-size:16px;">'+result[i].investment+'&nbsp;Cr</h4>';
						str+='<p><small>INVESTMENTS</small></p>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<h4>'+result[i].employment+'</h4>';
						str+='<p><small>EMPLOYMENT</small></p>';
					str+='</div>';
				str+='</div>';
			str+='</div>';				
		}
		if(type == "GREEN")
		{
			$("#promotionsStageGreenBlockId1").html(str);
		}else if(type == "RED")
		{
			$("#promotionsStageRedBlockId1").html(str);
		}else if(type == "DROPPED")
		{
			$("#promotionsStageDroppedBlockId1").html(str);
			$('#droppedDataFormModal1').html(str);
			$("#addIcon").show();
		}
	}
}

function getITSectorSubLeadCategoryWiseDetails(type,categoryType){
	$("#modalId").modal('show');
	$("#cohortId").html(spinner);
	
	var json = {
		leadName:"0",
		category:type,
		reportType:categoryType
	}
	$.ajax({                
		type:'POST',    
		url: 'getITSectorSubLeadCategoryWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result,type)
		}
	});
	function buildData(result,type)
	{
		var str='';
		str+='<table class="table table-bordered" id="'+type+'DataTable">';
			str+='<thead>';
				str+='<th>District</th>';
				str+='<th>Sector</th>';
				str+='<th>Sub Sector</th>';
				str+='<th>Department</th>';
				str+='<th>Company</th>';
				str+='<th>Line Of Activity</th>';
				str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
				str+='<th>Committed Employment</th>';
			str+='</thead>';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td>'+result[i].districtName+'</td>';
					str+='<td>'+result[i].itSector+'</td>';
					str+='<td>'+result[i].subSector+'</td>';
					str+='<td>'+result[i].deptName+'</td>';
					str+='<td>'+result[i].nameOfCompany+'</td>';
					str+='<td>'+result[i].lineOfActivity+'</td>';
					str+='<td>'+result[i].investment+'</td>';
					str+='<td>'+result[i].employment+'</td>';
				str+='</tr>';
			}
			
		str+='</table>';
		$("#modalTitleId").html(type+' '+categoryType+' '+'Indurstrices');
		$("#cohortId").html(str);
		$("#"+type+"DataTable").dataTable();
	}
}
function getITDistrictWiseDetails(type,category,divType){
	if(divType == 'body')
	{
		$("#"+type+"OverviewBlockDivId").html(spinner);
	}else{
		$("#modalId").modal('show');
		$("#cohortId").html(spinner);
	}
	
	var json = {
		category:category,
		sector:type
	}
	$.ajax({                
		type:'POST',    
		url: 'getITDistrictWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result,type,divType,category);
		}else{
			$("#"+type+"OverviewBlockDivId").html("NO DATA AVAILABLE");
		}
	});
	function buildData(result,type,divType,category)
	{
		var str='';
		var cstr='';
		
		str+='<div class="row">';
			cstr+='<div class="col-sm-12">';
				cstr+='<div class="pull-right"><label class="checkbox-inline"><input type="checkbox" class="checkBoxCls'+type+'" attr_divType="'+type+'" id="droppedForCheckId" ></input>Dropped</label></div>';
			cstr+='</div>';
			str+='<div class="col-sm-12 m_top20">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered" id="'+type+'DataTable'+divType+'">';
						str+='<thead>';
							str+='<tr>';
								str+='<th rowspan="2">District</th>';
								//str+='<th colspan="3" class="text-center">Total</th>';
								str+='<th colspan="5" class="text-center">Green</th>';
								str+='<th colspan="3" class="text-center">Red</th>';
								str+='<th colspan="3" class="text-center dropedCls'+type+'"  style="display:none;">Dropped</th>';
							str+='</tr>';
							str+='<tr>';
								//str+='<th>Industries</th>';
								//str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
								//str+='<th>Committed Employment</th>';
								str+='<th>Industries</th>';
								str+='<th>Actual Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
								str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
								str+='<th>Actual Employment</th>';
								str+='<th>Committed Employment</th>';
								str+='<th>Industries</th>';
								str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
								str+='<th>Committed Employment</th>';
								str+='<th class="dropedCls'+type+'" style="display:none;">Industries</th>';
								str+='<th class="dropedCls'+type+'" style="display:none;">Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
								str+='<th class="dropedCls'+type+'" style="display:none;">Committed Employment</th>';
							str+='</tr>';
						str+='</thead>';
						for(var i in result)
						{
							if(result[i].district != 'ZTotal')
							{
								str+='<tr>';
								
								str+='<td>'+result[i].district+'</td>';
								//if(result[i].district != null && result[i].district == 'ZTotal'){
									//str+='<td>'+result[i].noProjects+'</td>';
								//}else{
									//str+='<td class="sectorWiseCuntCls" attr_block_name="'+type+'" attr_category="'+category+'" attr_district="'+result[i].district+'" style="cursor:pointer;">'+result[i].noProjects+'</td>';
								//}
								
								//str+='<td>'+result[i].investment+'</td>';
								//str+='<td>'+result[i].employment+'</td>';
								for(var j in result[i].subList)
								{
									if(result[i].subList[j].category != null && result[i].subList[j].category == 'DROPPED'){
										if(result[i].subList[j].noProjects == 'undefined' || result[i].subList[j].noProjects === undefined)
										{
											str+='<td class="dropedCls'+type+'" style="display:none;">-</td>';
										}else{
											str+='<td class="dropedCls'+type+' sectorWiseCuntCls" style="display:none;cursor:pointer;"  attr_block_name="'+type+'" attr_category="'+result[i].subList[j].category+'" attr_district="'+result[i].district+'">'+result[i].subList[j].noProjects+'</td>';
										}
										
										if(result[i].subList[j].investment == 'undefined' || result[i].subList[j].investment === undefined)
										{
											str+='<td class="dropedCls'+type+'" style="display:none;">-</td>';
										}else{
											str+='<td class="dropedCls'+type+'" style="display:none;">'+result[i].subList[j].investment+'</td>';
										}
										
										if(result[i].subList[j].employment == 'undefined' || result[i].subList[j].employment === undefined)
										{
											str+='<td class="dropedCls'+type+'" style="display:none;">-</td>';
										}else{
											str+='<td class="dropedCls'+type+'" style="display:none;">'+result[i].subList[j].employment+'</td>';
										}	
									}else{
										if(result[i].subList[j].noProjects == 'undefined' || result[i].subList[j].noProjects === undefined)
										{
											str+='<td>-</td>';
										}else{
											str+='<td class="sectorWiseCuntCls" attr_block_name="'+type+'" attr_category="'+result[i].subList[j].category+'" attr_district="'+result[i].district+'" style="cursor:pointer;">'+result[i].subList[j].noProjects+'</td>';
										}
										if(result[i].subList[j].category != null && result[i].subList[j].category == 'GREEN'){
											str+='<td>-</td>';
										}
										if(result[i].subList[j].investment == 'undefined' || result[i].subList[j].investment === undefined)
										{
											str+='<td>-</td>';
										}else{
											str+='<td>'+result[i].subList[j].investment+'</td>';
										}
										if(result[i].subList[j].category != null && result[i].subList[j].category == 'GREEN'){
											str+='<td>-</td>';
										}
										if(result[i].subList[j].employment == 'undefined' || result[i].subList[j].employment === undefined)
										{
											str+='<td>-</td>';
										}else{
											str+='<td>'+result[i].subList[j].employment+'</td>';
										}
									}
									
								}
							str+='</tr>';
							}
						}
						for(var i in result)
						{
							if(result[i].district == 'ZTotal')
							{
								str+='<tfoot>';
									str+='<tr style="background-color:#ddd;">';
								
										str+='<td>Total</td>';
										//if(result[i].district != null && result[i].district == 'ZTotal'){
											//str+='<td>'+result[i].noProjects+'</td>';
										//}else{
											//str+='<td class="sectorWiseCuntCls" attr_block_name="'+type+'" attr_category="'+category+'" attr_district="'+result[i].district+'" style="cursor:pointer;">'+result[i].noProjects+'</td>';
										//}
										//if(result[i].subList[j].category != null && result[i].subList[j].category == 'GREEN'){
											//str+='<td>-</td>';
										//}
										//str+='<td>'+result[i].investment+'</td>';
										//if(result[i].subList[j].category != null && result[i].subList[j].category == 'GREEN'){
											//str+='<td>-</td>';
										//}
										//str+='<td>'+result[i].employment+'</td>';
										 for(var j in result[i].subList)
										{
											if(result[i].subList[j].category != null && result[i].subList[j].category == 'DROPPED'){
												if(result[i].subList[j].noProjects == 'undefined' || result[i].subList[j].noProjects === undefined)
												{
													str+='<td class="dropedCls'+type+'" style="display:none;">-</td>';
												}else{
													str+='<td class="dropedCls'+type+'" style="display:none;">'+result[i].subList[j].noProjects+'</td>';
												}
												
												if(result[i].subList[j].investment == 'undefined' || result[i].subList[j].investment === undefined)
												{
													str+='<td class="dropedCls'+type+'" style="display:none;">-</td>';
												}else{
													str+='<td class="dropedCls'+type+'" style="display:none;">'+result[i].subList[j].investment+'</td>';
												}
												
												if(result[i].subList[j].employment == 'undefined' || result[i].subList[j].employment === undefined)
												{
													str+='<td class="dropedCls'+type+'" style="display:none;">-</td>';
												}else{
													str+='<td class="dropedCls'+type+'" style="display:none;">'+result[i].subList[j].employment+'</td>';
												}	
											}else{
												if(result[i].subList[j].noProjects == 'undefined' || result[i].subList[j].noProjects === undefined)
												{
													str+='<td>-</td>';
												}else{
													str+='<td>'+result[i].subList[j].noProjects+'</td>';
												}
												if(result[i].subList[j].category != null && result[i].subList[j].category == 'GREEN'){
													str+='<td>-</td>';
												}
												if(result[i].subList[j].investment == 'undefined' || result[i].subList[j].investment === undefined)
												{
													str+='<td>-</td>';
												}else{
													str+='<td>'+result[i].subList[j].investment+'</td>';
												}
												if(result[i].subList[j].category != null && result[i].subList[j].category == 'GREEN'){
													str+='<td>-</td>';
												}
												if(result[i].subList[j].employment == 'undefined' || result[i].subList[j].employment === undefined)
												{
													str+='<td>-</td>';
												}else{
													str+='<td>'+result[i].subList[j].employment+'</td>';
												}
											}
										} 
									str+='</tr>';
								str+='</tfoot>';
							}
						}
						
						
						
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		if(divType == 'body')
		{
			$("#"+type+"OverviewBlockCheckBoxId").html(cstr);
			$("#"+type+"OverviewBlockDivId").html(str);
			
		}else{
			$("#modalTitleId").html(type);
			$("#cohortId").html(str);
		}
		$("#"+type+"DataTable"+divType).dataTable();
	}
}
function getAPInnovationSocietyOverview(type,divId){
	$("#"+divId).html(spinner);
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getAPInnovationSocietyOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(type == 'onload')
		{
			$("#apInnovationSociety").html(result.startups);
		}else if(type == 'overview'){
			return buildOverview(result,divId);
		}
	});		
	function buildOverview(result,divId)
	{
		var str='';
		var dataArr = [{"name":'startups',"color":"#007810"},{"name":"schools","color":"#5C28AB"},{"name":"colleges","color":"#F75C5D"},{"name":"incubators","color":"#D28000"},{"name":"mentors","color":"#950038"}];
		str+='<div class="row">';
		for(var i in dataArr)
		{
			str+='<div class="col-sm-2">';
				str+='<div class="panel panel-default">';
					str+='<div class="panel-body"><h4 class=" text-capitalize" style="color:'+dataArr[i].color+'">'+dataArr[i].name+' &nbsp;&nbsp;<img src="Assests/icons/ITC/'+dataArr[i].name+'.png"/></h4></div>';
					if(dataArr[i].name == 'startups')
					{
						str+='<div class="panel-footer"><h4>'+result.startups+'</h4></div>';
					}else if(dataArr[i].name == 'schools')
					{
						str+='<div class="panel-footer"><h4>'+result.schools+'</h4></div>';
					}else if(dataArr[i].name == 'colleges')
					{
						str+='<div class="panel-footer"><h4>'+result.colleges+'</h4></div>';
					}else if(dataArr[i].name == 'incubators')
					{
						str+='<div class="panel-footer"><h4>'+result.incubators+'</h4></div>';
					}else if(dataArr[i].name == 'mentors')
					{
						str+='<div class="panel-footer"><h4>'+result.mentors+'</h4></div>';
					}
				str+='</div>';
			str+='</div>';
		}
		str+='</div>';
		$("#"+divId).html(str);
	}
}
function getAPISXLR8APDetailedData(){
	$("#APISXLr8APOverview,#Campaigns").html(spinner);
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getAPISXLR8APDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		return buildData(result);
	});
	function buildData(result)
	{
		var overview = '';
		var tableView ='';
		var totalBatches = 0;
		var totalCompanies = 0;
		var totalJobsCreated = 0;
		for(var i in result)
		{
			totalBatches = result.length;
			totalCompanies = totalCompanies + result[i].companiesRegisterd;
			if(result[i].jobsCreated != null && result[i].jobsCreated != '-' && result[i].jobsCreated.length > 0)
			{
				totalJobsCreated = totalJobsCreated + parseInt(result[i].jobsCreated);
			}
		}
		overview+='<div class="white_block_ITC" style="border:1px solid #F87071;border-radius:5px;">';
			overview+='<h4 class="m_top10"><span  style="padding:5px 10px;background-color:#F87071">APIS-XLr8AP</span></h4>';
			overview+='<div style="padding:10px;">';
				overview+='<div class="row">';
					overview+='<div class="col-sm-12">';
						overview+='<h4>Batches</h4>';
						overview+='<h3 class="m_top10">'+totalBatches+'</h3>';
					overview+='</div>';
					overview+='<div class="col-sm-6">';
						overview+='<h4>Companies Registered</h4>';
						overview+='<h3 class="m_top10">'+totalCompanies+'</h3>';
					overview+='</div>';
					overview+='<div class="col-sm-6">';
						overview+='<h4>Job Created</h4>';
						overview+='<h3 class="m_top10">'+totalJobsCreated+'</h3>';
					overview+='</div>';
				overview+='</div>';
			overview+='</div>';
		overview+='</div>';
		
		tableView+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
			tableView+='<div class="panel panel-default panel-black">';
				tableView+='<div class="panel-heading" role="tab" id="headingOne">';
					tableView+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
						tableView+='<h4 class="panel-title">APIS-XLr8AP</h4>';
					tableView+='</a>';
				tableView+='</div>';
				tableView+='<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">';
					tableView+='<div class="panel-body">';
						tableView+='<table class="table table-bordered" id="APISXLr8APOverviewTable">';
							tableView+='<thead>';
								tableView+='<th>Batch</th>';
								tableView+='<th style="background-color:#F8F8F8">Duration</th>';
								tableView+='<th style="background-color:#FFFAF3">Companies Registered</th>';
								tableView+='<th style="background-color:#FFFAF3">Jobs Created</th>';
							tableView+='</thead>';
							for(var i in result)
							{
								tableView+='<tr>';
									tableView+='<td class="cohortIdClick" style="cursor:pointer" attr_id="'+result[i].batchId+'">'+result[i].batch+'</td>';
									tableView+='<td style="background-color:#F8F8F8">'+result[i].duration+'</td>';
									tableView+='<td style="background-color:#FFFAF3">'+result[i].companiesRegisterd+'</td>';
									tableView+='<td style="background-color:#FFFAF3">'+result[i].jobsCreated+'</td>';
								tableView+='</tr>';
							}
						tableView+='</table>';
					tableView+='</div>';
				tableView+='</div>';
			tableView+='</div>';
		tableView+='</div>';
		
		$("#APISXLr8AP").html(overview);
		$("#APISXLr8APOverview").html(tableView);
		$("#APISXLr8APOverviewTable").dataTable()
	}
}
function getCampaignsDetailedData(){
	$("#campaignsOverviewBlock,#Campaigns").html(spinner);
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCampaignsDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		return buildData(result);
	});	
	function buildData(result)
	{
		var overview = '';
		var tableView ='';
		overview+='<div class="white_block_ITC" style="border:1px solid #D78F1F;border-radius:5px;">';
			overview+='<h4 class="m_top10"><span  style="padding:5px 10px;background-color:#D78F1F">Campaigns</span></h4>';
			overview+='<div style="padding:10px;">';
				overview+='<div class="row">';
					overview+='<div class="col-sm-12 m_top25" style="margin-bottom:42px;">';
						overview+='<h4>Batchs</h4>';
						overview+='<h3>'+result.length+'</h3>';
					overview+='</div>';
				overview+='</div>';
			overview+='</div>';
		overview+='</div>';
		tableView+='<div class="panel-group" id="accordionCampaign" role="tablist" aria-multiselectable="true">';
			tableView+='<div class="panel panel-default panel-black">';
				tableView+='<div class="panel-heading" role="tab" id="headingOneCampaign">';
					tableView+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordionCampaign" href="#collapseOneCampaign" aria-expanded="true" aria-controls="collapseOneCampaign">';
						tableView+='<h4 class="panel-title">Campaigns</h4>';
					tableView+='</a>';
				tableView+='</div>';
				tableView+='<div id="collapseOneCampaign" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneCampaign">';
					tableView+='<div class="panel-body">';
						tableView+='<table class="table table-bordered m_top20" id="campaignsOverviewBlockTable">';
							tableView+='<thead>';
								tableView+='<th style="background-color:#FFFAF3">Name</th>';
								tableView+='<th>Submited Date</th>';
								tableView+='<th>Campaign Name</th>';
								tableView+='<th>Campaign Type</th>';
							tableView+='</thead>';
							for(var i in result)
							{
								tableView+='<tr>';
									tableView+='<td style="background-color:#FFFAF3">'+result[i].location+'</td>';
									tableView+='<td>'+result[i].duration+'</td>';
									tableView+='<td>'+result[i].campaignName+'</td>';
									tableView+='<td>'+result[i].campaignType+'</td>';
								tableView+='</tr>';
							}
						tableView+='</table>';
					tableView+='</div>';
				tableView+='</div>';
			tableView+='</div>';
		tableView+='</div>';
		
		$("#Campaigns").html(overview);
		$("#campaignsOverviewBlock").html(tableView);
		$("#campaignsOverviewBlockTable").dataTable()
	}	
}
function getCampusInnovationCentersDetailedData(){
	$("#campusOverviewBlock,#CampusInnovationCenters").html(spinner);
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getCampusInnovationCentersDetailedData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		return buildData(result);
	});	
	function buildData(result)
	{
		var overview = '';
		var tableView ='';
		overview+='<div class="white_block_ITC" style="border:1px solid #4C4C4C;border-radius:5px;">';
			overview+='<h4 class="m_top10"><span  style="padding:5px 10px;background-color:#4C4C4C;color:#fff;">Campus Innovation Centers</span></h4>';
			overview+='<div style="padding:10px;">';
				overview+='<div class="row">';
					overview+='<div class="col-sm-12 m_top25" style="margin-bottom:42px;">';
						overview+='<h4>No Of University / No Of  College</h4>';
						overview+='<h3>'+result.length+'</h3>';
					overview+='</div>';
				overview+='</div>';
			overview+='</div>';
		overview+='</div>';
		tableView+='<div class="panel-group" id="accordionCampus" role="tablist" aria-multiselectable="true">';
			tableView+='<div class="panel panel-default panel-black">';
				tableView+='<div class="panel-heading" role="tab" id="headingOneCampus">';
					tableView+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordionCampus" href="#collapseOneCampus" aria-expanded="true" aria-controls="collapseOneCampus">';
						tableView+='<h4 class="panel-title">Campus Innovation Centers</h4>';
					tableView+='</a>';
				tableView+='</div>';
				tableView+='<div id="collapseOneCampus" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneCampus">';
					tableView+='<div class="panel-body">';
						tableView+='<table class="table table-bordered m_top20" id="campusOverviewBlockTable">';
							tableView+='<thead>';
								tableView+='<th style="background-color:#F8F8F8">NAME OF THE UNIVERSITY OR COLLEGE</th>';
								tableView+='<th>NAME OF THE INNOVATION CENTRE</th>';
								tableView+='<th>LOCATION</th>';
							tableView+='</thead>';
							for(var i in result)
							{
								tableView+='<tr>';
									tableView+='<td style="background-color:#F8F8F8">'+result[i].universityORCollegeName+'</td>';
									tableView+='<td>'+result[i].innovationCentreName+'</td>';
									tableView+='<td>'+result[i].location+'</td>';
								tableView+='</tr>';
							}
						tableView+='</table>';
					tableView+='</div>';
				tableView+='</div>';
			tableView+='</div>';
		tableView+='</div>';
		$("#CampusInnovationCenters").html(overview);
		$("#campusOverviewBlock").html(tableView);
		$("#campusOverviewBlockTable").dataTable()
	}	
}
function getCohortDetailsByCohortId(id){
	$("#cohortId").html(spinner);
	var json = {
		fromDate:"",
		toDate:"",
		year:"",
		searchLevelId:id
	}
	$.ajax({                
		type:'POST',    
		url: 'getCohortDetailsByCohortId',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result);
		}else{
			$("#cohortId").html("NO DATA AVAILABLE");
		}
	});		
	function buildData(result)
	{
		var str='';
		str+='<table class="table table-bordered" id="cohortIdTable">';
			str+='<thead class="text-capital">';
				str+='<th>cohort</th>';
				str+='<th>innovator name</th>';
				str+='<th>company name</th>';
				str+='<th>permanent</th>';
				str+='<th>Intern</th>';
				str+='<th>innovation</th>';
			str+='</thead>';
			for(var i in result)
			{
				str+='<tr>';
					str+='<td>'+result[i].cohort+'</td>';
					str+='<td>'+result[i].innovator_name+'</td>';
					str+='<td>'+result[i].company_name+'</td>';
					if(result[i].permanent_jobs != null && result[i].permanent_jobs.length > 0)
					{
						str+='<td>'+result[i].permanent_jobs+'</td>';
					}else{
						str+='<td>-</td>';
					}
					if(result[i].interns != null && result[i].interns.length > 0)
					{
						str+='<td>'+result[i].interns+'</td>';
					}else{
						str+='<td>-</td>';
					}
					str+='<td>'+result[i].innovation+'</td>';
				str+='</tr>';
			}
		str+='</table>';
		$("#cohortId").html(str);
		$("#cohortIdTable").dataTable();
	}
}

var eOfcDeptResult = '';
/* function getEOfcDepartWiseOverviewDetails(type){
	if(type == 'onload')
	{
		$("#itcDeptWiseCount").html(spinner);
	}else{
		$("#eOfficeDeparmentsOverViewBlock").html(spinner);
	}
	var json = {
		departmentid:"",		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfcDepartWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		eOfcDeptResult = result;
		//getEofficeDesignationWiseDetails()
		if(type == 'onload')
		{
			for(var i in result){
				if(result[i].departmentName != null && result[i].departmentName == "ITE & C")
					$("#itcDeptWiseCount").html(result[i].totalCount+'/<small style="color:#fff;font-size:14px;top:0px;">'+result[i].created+'</small>');
			}
		}else{
			buildEOfcDepartWiseOverviewDetails(result);
		}
	});		
} */
//getEOfcDepartOverviewDetails
function getEOfcDepartWiseOverviewDetails(type){
	if(type == 'onload')
	{
		$("#itcDeptWiseCount").html(spinner);
	}else{
		$("#eOfficeDeparmentsOverViewBlock").html(spinner);
	}
	var json = {
		//fromDate:"",	
		//toDate:""	
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfcDepartOverviewDetailsNew',//'getEOfcDepartOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		eOfcDeptResult = result;
		//getEofficeDesignationWiseDetails()
		if(type == 'onload')
		{
			for(var i in result){
				if(result[i].departmentName != null && result[i].departmentName == "ITE & C")
					$("#itcDeptWiseCount").html(result[i].totalCount+'/<small style="color:#fff;font-size:14px;top:0px;">'+result[i].created+'</small>');
			}
		}else{
			buildEOfcDepartWiseOverviewDetails(result);
		}
	});		
}
function getEOfcDeptPendancyStatusWiseDetails(){
	$("#departmentWise").html(spinner);
	var json = {
		departmentid:"",		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfcDeptPendancyStatusWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildTable(result);
		}else{
			$("#departmentWise").html("NO DATA AVAILABLE");
		}
	});
	function buildTable(result)
	{
		var tableView = '';
		tableView+='<div class="table-responsive">';
			tableView+='<table class="table table-bordered" id="eOfcDataTableId">';
				tableView+='<thead>';
					tableView+='<th style="background-color:#fff;">Departments</th>';
					tableView+='<th style="background-color:#B2DFDB">Total</th>';
					tableView+='<th style="background-color:#FBACAC">Total Pendency</th>';
					tableView+='<th style="background-color:#FBACAC">%</th>';
					tableView+='<th style="background-color:#FDCECE">0 - 7 days</th>';
					tableView+='<th style="background-color:#FDCECE">8 - 15 days</th>';
					tableView+='<th style="background-color:#FDCECE">16 - 30 days</th>';
					tableView+='<th style="background-color:#FDCECE">31 - 60 days</th>';
					tableView+='<th style="background-color:#FDCECE"> > 60 days</th>';
				tableView+='</thead>';
				for(var i in result)
				{
					tableView+='<tr>';
						tableView+='<td>'+result[i].departmentName+'</td>';
						tableView+='<td style="background-color:#B2DFDB">'+result[i].created+'</td>';
						tableView+='<td style="background-color:#FBACAC">'+result[i].totalCount+'</td>';
						tableView+='<td style="background-color:#FBACAC">'+result[i].percentage+'</td>';
						tableView+='<td style="background-color:#FDCECE">'+result[i].zeroToSeven+'</td>';
						tableView+='<td style="background-color:#FDCECE">'+result[i].eightToFifteen+'</td>';
						tableView+='<td style="background-color:#FDCECE">'+result[i].sixteenToThirty+'</td>';
						tableView+='<td style="background-color:#FDCECE">'+result[i].thirtyoneToSixty+'</td>';
						tableView+='<td style="background-color:#FDCECE">'+result[i].aboveSixty+'</td>';
					tableView+='</tr>';
				}			
			tableView+='</table>';
		tableView+='</div>';
		$("#departmentWise").html(tableView);
		$("#eOfcDataTableId").dataTable();
	}
}

function getEofficeDesignationWiseDetails(){
	$("#eOfficeBlock4").html(spinner);
	var json = {
		designation:"",		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEofficeDesignationWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildTable(result);
		}else{
			$("#eOfficeBlock4").html("NO DATA AVAILABLE");
		}
	});
	function buildTable(result)
	{
		var tableView = '';
		var colorsArr = ['#009587','#84ED50','#FFB300','#FF2C95','#F75C5D','#FF2C95','#FFB300','#009587','#84ED50','#FFB300','#FF2C95','#F75C5D','#FF2C95','#FFB300'];
		tableView+='<div class="table-responsive" style="height:600px;">';
			tableView+='<table class="table-desig">';
				tableView+='<tr>';
					tableView+='<td>DEPARTMENT WISE</td>';
					for(var i in eOfcDeptResult)
					{
						tableView+='<td style="border-left:3px solid '+colorsArr[i]+'">';
							tableView+='<p class="f-16"><b>'+eOfcDeptResult[i].departmentName+'</b></p>';
							tableView+='<p>'+eOfcDeptResult[i].totalCount+' / <small>'+eOfcDeptResult[i].created+'</small></p>';
						tableView+='</td>';
					}
				tableView+='</tr>';
				for(var i in result)
				{
					tableView+='<tr>';
						tableView+='<td>'+result[i].designation+'</td>';
						for(var j in result[i].subList)
						{
							tableView+='<td style="border-left:3px solid '+colorsArr[j]+'">';
								tableView+='<p class="f-16"><b>'+result[i].subList[j].employeeName+'</b></p>';
								tableView+='<p>'+result[i].subList[j].totalCount+' / <small>'+result[i].subList[j].created+'</small></p>';
							tableView+='</td>';
						}
					tableView+='</tr>';
				}
			tableView+='</table>';
		tableView+='</div>';
		$("#eOfficeBlock4").html(tableView);
		//$(".table-desig-scroll").mCustomScrollbar({setHeight:'500px'});
	}
}
function getEofficeDesignationWisePendencyDetails()
{
	$("#eOfficePendencyWise").html(spinner);
	var json = {
		departmentid:"",		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEofficeDesignationWisePendencyDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			buildTabs(result);
		}else{
			$("#eOfficePendencyWise").html("NO DATA AVAILABLE");
		}
	});
	function buildTabs(result)
	{
		var tabView = '';
		tabView+='<div class="row">';
			/* tabView+='<div class="col-sm-3">';
				tabView+='<div class="eOfcScroll">';
					tabView+='<ul class="nav nav-tabs tab-view-eofc" role="tablist">';
						tabView+='<li role="presentation" class="active"><a EofficeDesignationId="" EofficeDesignation="departmentWise" href="#departmentWise" aria-controls="departmentWise" role="tab" data-toggle="tab">DEPARTMENT WISE</a></li>';
						for(var i in result)
						{
							tabView+='<li role="presentation"><a EofficeDesignationId="'+result[i].departmentId+'" EofficeDesignation="'+result[i].designation.replace(/\s+/g, '')+'" href="#'+result[i].designation.replace(/\s+/g, '')+'" aria-controls="'+result[i].designation.replace(/\s+/g, '')+'" role="tab" data-toggle="tab">'+result[i].designation+'</a></li>';
						}
					tabView+='</ul>';
				tabView+='</div>';
			tabView+='</div>'; */
			tabView+='<div class="col-sm-12">';
				/* tabView+='<div class="tab-content">';
					tabView+='<div role="tabpanel" class="tab-pane active" id="departmentWise">'+spinner+'</div>';
					
						tabView+='<div role="tabpanel" class="tab-pane" id="'+result[i].designation.replace(/\s+/g, '')+'">'; */
							tabView+='<div class="table-responsive">';
								tabView+='<table class="table table-bordered" id="eOfcDataTableId">';
									tabView+='<thead>';
										tabView+='<th style="background-color:#fff;">Designation</th>';
										tabView+='<th style="background-color:#fff;">Employee</th>';
										tabView+='<th style="background-color:#B2DFDB">Total</th>';
										tabView+='<th style="background-color:#FBACAC">Total Pendency</th>';
										tabView+='<th style="background-color:#FBACAC">%</th>';
										tabView+='<th style="background-color:#FDCECE">0 - 7 days</th>';
										tabView+='<th style="background-color:#FDCECE">8 - 15 days</th>';
										tabView+='<th style="background-color:#FDCECE">16 - 30 days</th>';
										tabView+='<th style="background-color:#FDCECE">31 - 60 days</th>';
										tabView+='<th style="background-color:#FDCECE"> > 60 days</th>';
									tabView+='</thead>';
									for(var i in result)
									{
										for(var j in result[i].subList)
										{
											tabView+='<tr>';
												tabView+='<td>'+result[i].designation+'</td>';
												tabView+='<td>'+result[i].subList[j].employeeName+'</td>';
												tabView+='<td style="background-color:#B2DFDB">'+result[i].subList[j].created+'</td>';
												tabView+='<td style="background-color:#FBACAC">'+result[i].subList[j].totalCount+'</td>';
												tabView+='<td style="background-color:#FBACAC">'+result[i].subList[j].percentage+'</td>';
												tabView+='<td style="background-color:#FDCECE">'+result[i].subList[j].zeroToSeven+'</td>';
												tabView+='<td style="background-color:#FDCECE">'+result[i].subList[j].eightToFifteen+'</td>';
												tabView+='<td style="background-color:#FDCECE">'+result[i].subList[j].sixteenToThirty+'</td>';
												tabView+='<td style="background-color:#FDCECE">'+result[i].subList[j].thirtyoneToSixty+'</td>';
												tabView+='<td style="background-color:#FDCECE">'+result[i].subList[j].aboveSixty+'</td>';
											tabView+='</tr>';
										}
									}									
								tabView+='</table>';
						/* 	tabView+='</div>';
						tabView+='</div>'; */
					
				tabView+='</div>';
			tabView+='</div>';
		tabView+='</div>';
		$("#eOfficePendencyWise").html(tabView);
		$("#eOfcDataTableId").dataTable();
		//getEOfcDeptPendancyStatusWiseDetails();
	}
}
function getMeesavaKpiGraphBuild(divId,id){
	var str='';
	var dateWiseArr=['Monthly','Quarterly','HalfYearly','Yearly']
	var meesavaCenterOpenedArr=[{name:"Srikakulam",id:45},{name:"Vizianagaram",id:0},{name:"Visakhapatnam",id:0},{name:"East Godavari",id:74},{name:"West Godavari",id:341},{name:"Krishna",id:0},{name:"Guntur",id:36},{name:"Prakasam",id:0},{name:"Sri Potti Sriramulu Nellore",id:0},{name:"Chittoor",id:0},{name:"Y.S.R",id:0},{name:"Ananthapuramu",id:53},{name:"Kurnool",id:0},{name:"Total",id:549}]
	
	var meesavaOnlineArr=[{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE CHANGE OF FIRM NAME',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE CHANGE OF OFFICE ADDRESS',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE CHANGE OF RESPONSIBLE PERSON DETAILS',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE DELETION OF UNITS',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE INCLUSION OF IMPORTED PRIVATE VARIETIES',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE INCLUSION OF NOTIFIED VARIETIES',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE INCLUSION OF PRIVATE VARIETIES FOR STORAGE',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE INCLUSION OF PRIVATE VARIETY FOR REGULAR MARKETING',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE INCLUSION OF PRIVATE VARIETY FOR TRAIL MARKETING',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT CENTRALIZED SEED LICENSE INCLUSION OF UNITS',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT SEED DEAER LICENSE CHANGE OF FIRM NAME',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT SEED DEALER CHANGE OF OFFICE ADDRESS',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT SEED DEALER LICENSE CHANGE IN SALE POINT PREMISES',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT SEED DEALER LICENSE CHANGE OF PERSON RESPONSIBLE',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT SEED DEALER LICENSE STORAGE POINT INCLUSION',year:"2016-2017"},{name:'AGRICULTURE',service:'AMENDMENT SEED DEALER STORAGE POINT DELETION',year:"2016-2017"},{name:'AGRICULTURE',service:'DUPLICATE CENTRALIZED SEED LICENSE',year:"2016-2017"},{name:'AGRICULTURE',service:'DUPLICATE SEED DEALER LICENSE',year:"2016-2017"},{name:'AGRICULTURE',service:'NEW CENTRALIZED SEED LICENSE',year:"2016-2017"},{name:'AGRICULTURE',service:'NEW SEED DEALER LICENSE',year:"2016-2017"},{name:'AGRICULTURE',service:'OTHER STATE OFFICE ADDRESS CHANGE FOR AMENDMENT SEED CSL',year:"2016-2017"},{name:'AGRICULTURE',service:'RENEWAL CENTRALIZED SEED LICENSE',year:"2016-2017"},{name:'AGRICULTURE',service:'RENEWAL SEED DEALER LICENSE',year:"2016-2017"},{name:'CRDA',service:'My Brick - My Amaravati',year:"2016-2017"},{name:'ELECTION',service:'Correct your Card (Form - 8)',year:"2016-2017"},{name:'ELECTION',service:'Know your status',year:"2016-2017"},{name:'ELECTION',service:'New Enrollment (Form - 6)',year:"2016-2017"},{name:'ELECTION',service:'Objection to Inclusion of Names (Form - 7)',year:"2016-2017"},{name:'ELECTION',service:'Transpose your Card (Form - 8A)',year:"2016-2017"},{name:'HEALTH CARE',service:'AP Pharmacy Council Fee Payment',year:"2016-2017"},{name:'Income Tax Pan Services Unit',service:'Changes or Corrections in PAN Details',year:"2016-2017"},{name:'Income Tax Pan Services Unit',service:'New Pan for Foreign Citizens (Form 49AA)',year:"2016-2017"},{name:'Income Tax Pan Services Unit',service:'New Pan for Indian Citizens (Form 49A)',year:"2016-2017"},{name:'Income Tax Pan Services Unit',service:'Reprint of PAN Card',year:"2016-2017"},{name:'Income Tax Pan Services Unit',service:'Status Track for PAN Application',year:"2016-2017"},{name:'Income Tax Pan Services Unit',service:'Transaction Status Enquiry',year:"2016-2017"},{name:'LABOUR',service:'APPLICATION FOR AMENDMENT OF ISSUE OF INTEGRATED REGISTRATION OF ESTABLISHMENT UNDER LABOUR LAWS',year:"2016-2017"},{name:'LABOUR',service:'Combined Annual Return under Labour Laws',year:"2016-2017"},{name:'MEE BHOOMI (REVENUE)',service:'Aadhaar Seeding Request',year:"2016-2017"},{name:'MEE BHOOMI (REVENUE)',service:'FMB',year:"2016-2017"},{name:'MEE BHOOMI (REVENUE)',service:'ROR 1B',year:"2016-2017"},{name:'MEE BHOOMI (REVENUE)',service:'Village Adangal',year:"2016-2017"},{name:'MEE BHOOMI (REVENUE)',service:'Village Map Copy',year:"2016-2017"},{name:'MEE BHOOMI (REVENUE)',service:'Village ROR',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'Building Permission',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'House Tax online Payment',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'Layout Permission',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'Marriage Certificate',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'Mutation',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'NOC for small,medium and large scale industries',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'Private Water Tap Connection',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'Property Valuation Certificate',year:"2016-2017"},{name:'PANCHAYATHIRAJ DEPARTMENT',service:'Trade License',year:"2016-2017"},{name:'REVENUE',service:'Issuance Of Local Status Certificate',year:"2016-2017"},{name:'TECHNICAL EDUCATION',service:'e Pariksha Exam Fee Payment Service',year:"2016-2017"},{name:'APMAPB (Ayush): Andhra Pradesh Medical & Aromatic Plants Board',service:'FARMERS SUBSIDY APPLICATION FORM',year:"2016-2017"}]
	
	var meesavaAppArr=['TSSPDCL','Aadhaar Card Printing','Vodafone','Aircel','Bsnl','Idea','Indicom','Docomo','Reliance GSM','Uninor','Airtel','Relinace CDMA','Airtel TV','Big TV','Dish TV','Sun TV','Tatasky TV','Videcon TV','Reliance NetConnect','Idea Netsetter','BSNL Data','Aircel Data','AIRTEL - Postpaid','BSNL LANDLINE','IDEA - Postpaid','Tata Docomo - Postpaid','Vodafone - Postpaid','Aircel - Postpaid','Reliance - Postpaid','AIRTEL Landline','CellOne - Postpaid','BSNL','Tikona','Airtel','Idea','Aircel','MTS','Reliance CDMA','Tata Indicom','Tata Docomo','Vodafone','Uninor','T24','Airtel Data','Idea Netsetter','BSNL Data','Vodafone Data','Airtel DTH','DISH TV','SUN TV','Videocon D2H','Reliance GSM','LIC','SBI LIFE','ICICI PRUDENTIAL','AVIVA','Hyderabad Metropolitan Water Supply & Sewerage Board','Bookmyshow','Airtel','Idea','Reliance CDMA','Reliance GSM','Tata Indicom','Tata Docomo','Uninor','Vodafone','JIO','ADANGAL / PHAHANI','ROR 1B','BIRTH CERTIFICATE','DEATH CERTIFICATE','AGRICULTURE INCOME','INCOME CERTIFICATE','EBC CERTIFICATE','OBC CERTIFICATE','INTEGRATED CERTIFICATE','FAMILY MEMBERSHIP','NO EARNING MEMBER','ENCUMBRANCE CERTIFICATE','Sri Kalahasteeswara Swamy Vari Devasthanam Seva Booking, Srikalahasti','Sri Durga Malleswara Swamy Seva Booking, Vijayawada','Sri Venkateswara Swamy Seva Booking, Dwaraka Tirumala','AMARAVATI DONATIONS','F.M.B COPY','New Issuance Of Voter/EPIC Card(PVC)','Re-Issuance of Integrated Certificate','Issuance Of Voter/EPIC Card','Child Name Inclusion - CDMA','Corrections in birth certificate -CDMA','Non availability birth application - CDMA','Non availability death application - CDMA','Late Registration Of Birth/Death','Surrender of Ration Card','New Gas Connection Application','Sanction of Incentives','Sri Kalahasteeswara Swamy Temple Room Booking, Srikalahasti','Sri Veera Venkata Satyanarayana Swamy Temple Room Booking, Annavaram','Sri Venkateswara Swamy Temple Room Booking, Dwaraka Tirumala','POSESSION CERTIFICATE','Corrections in death certificate -CDMA','Ration Card Transfer','AIRTEL POSTPAID','AIRTEL LANDLINE','IDEA POSTPAID','ELECTRICITY BILL - EPDCL','ELECTRICITY BILL - SPDCL','Traffic Challan - Chittoor Dist','Traffic Challan - Prakasam Dist','Traffic Challan - Vizianagaram Dist','Traffic Challan - West Godavari Dist','Traffic Challan - Nellore Dist','Traffic Challan - East Godavari Dist','Traffic Challan - Krishna Dist','Traffic Challan - Srikakulam Dist','Traffic Challan - Ananthapur Dist','Traffic Challan - VISAKHAPATNAM Dist']
	
		str+='<div class="row">';
			str+='<ul class="list-inline calendar_active_IHHL_cls meesavaKpi">';
				str+='<li class="active" attr_type="meesavaCenterEst" >No.of MeeSeva Centres Established</li>';
				str+='<li attr_type="meeasvaCenterOpen">No.of Meeseva Centers opened</li>';
				str+='<li attr_type="meeasvaOnline">Online Services</li>';
				str+='<li attr_type="meesavaApp">MeeSeva App Services</li>';
			str+='</ul>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="m_top20 meesavaCenterEst">';
			for(var i in dateWiseArr){
				str+='<div class="col-sm-3">';
					str+='<div id="indicatorProgressGraphId'+dateWiseArr[i]+'" style="height:300px;"></div>';
				str+='</div>';
			}
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-6 meeasvaCenterOpen" style="display:none;">';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered" id="meeasvaCenterOpenDT">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>District Name</th>';
						str+='<th>No.Of Meeseva Centers opened</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in meesavaCenterOpenedArr){
					str+='<tr>';
						str+='<td>'+meesavaCenterOpenedArr[i].name+'</td>';
						str+='<td>'+meesavaCenterOpenedArr[i].id+'</td>';
					str+='</tr>';
				}
				str+='<tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
		str+='<div class="col-sm-12 meeasvaOnline" style="display:none;">';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered" id="meeasvaOnlineDT">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Department Name</th>';
						str+='<th>No.Of Meeseva Centers opened</th>';
						str+='<th>Year</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in meesavaOnlineArr){
					str+='<tr>';
						str+='<td>'+meesavaOnlineArr[i].name+'</td>';
						str+='<td>'+meesavaOnlineArr[i].service+'</td>';
						str+='<td>'+meesavaOnlineArr[i].year+'</td>';
					str+='</tr>';
				}
				str+='<tbody>';
				str+='</table>';
			str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-6 meesavaApp" style="display:none;">';
			str+='<h4 style="text-align:center;text-transform:uppercase;"><b>Total Meesava  App  Services - 116</b></h4>';	
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table table-bordered" id="meesavaAppDT">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>MeeSeva App Services </th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in meesavaAppArr){
					str+='<tr>';
						str+='<td>'+meesavaAppArr[i]+'</td>';
					str+='</tr>';
				}
				str+='<tbody>';
				str+='</table>';
			str+='</div>';
			str+='</div>';
		str+='</div>';
		
	$("#"+divId+"Block"+id).html(str);
	$("#meesavaAppDT ,#meeasvaOnlineDT, #meeasvaCenterOpenDT").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
	
	for(var i in dateWiseArr){
		var mainArr=[];
		if(dateWiseArr[i] == "Monthly"){
			var obj= {
				name:'Monthly',
				data:[{"y":300,color:"#009587"},{"y":549,color:"#ADD2CE"}]
			}
		}else if(dateWiseArr[i] == "Quarterly"){
			var obj= {
				name:'Quarterly',
				data:[{"y":800,color:"#009587"},{"y":1802,color:"#ADD2CE"}]
			}
		}else if(dateWiseArr[i] == "HalfYearly"){
			var obj= {
				name:'Half Yearly',
				data:[{"y":1400,color:"#009587"},{"y":1935,color:"#ADD2CE"}]
			}
		}else if(dateWiseArr[i] == "Yearly"){
			var obj= {
				name:'Yearly',
				data:[{"y":3499,color:"#009587"},{"y":1935,color:"#ADD2CE"}]
			}
		}
		mainArr.push(obj);
		$("#indicatorProgressGraphId"+dateWiseArr[i]).highcharts({
			chart: {
				type: 'column'
			},

			title: {
				text: dateWiseArr[i],
				align:'left',
					style: {
						color: '#000',
						font: 'bold 16px "Lato", sans-serif'
					}
			},
			xAxis: {
				 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,	
				categories: ['Target', 'Achivement']
			},
			yAxis: {
				 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				title: {
					text: ''
				},
			},
			legend: {
				enabled: false
			},
			tooltip: {
				formatter: function () {
					return '<b>' + this.x + '</b><br/>' +
						this.series.name + ': ' + this.y
				}
			},

			plotOptions: {
				column: {
				   // stacking: 'normal',
				      dataLabels: {
						enabled: true,
						color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'gray',
						formatter: function() {
							return (this.y);
						},
					},
				}
			},

			series: mainArr
		});
		
	}
	
}
$(document).on("click",".calendar_active_IHHL_cls li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var blockType = $(this).attr("attr_type");
	if(blockType == "meesavaCenterEst"){
		$(".meesavaCenterEst").show();
		$(".meeasvaCenterOpen").hide();
		$(".meeasvaOnline").hide();
		$(".meesavaApp").hide();
		
	}else if(blockType == "meeasvaCenterOpen"){
		$(".meesavaCenterEst").hide();
		$(".meeasvaCenterOpen").show();
		$(".meeasvaOnline").hide();
		$(".meesavaApp").hide();
	}else if(blockType == "meeasvaOnline"){
		$(".meesavaCenterEst").hide();
		$(".meeasvaCenterOpen").hide();
		$(".meeasvaOnline").show();
		$(".meesavaApp").hide();
	}else if(blockType == "meesavaApp"){
		$(".meesavaCenterEst").hide();
		$(".meeasvaCenterOpen").hide();
		$(".meeasvaOnline").hide();
		$(".meesavaApp").show();
	}
});

$(document).on("click",".sectorWiseCuntCls",function(){
	$("#sectorModalTitleId").html("");
	var sectorType = $(this).attr("attr_block_name");
	var category = $(this).attr("attr_category");
	var district = $(this).attr("attr_district");
	getSectorWiseOverviewCountDetails(sectorType,category,district);
});

function getSectorWiseOverviewCountDetails(sectorType,category,district){
	$("#sectorModalId").modal('show');
	$("#sectorModalDivId").html(spinner);
	
	var json = {
		leadName:"0",
		category:category,
		sector : sectorType,
		districtValue : district
	}
	$.ajax({                
		type:'POST',    
		url: 'getSectorWiseOverviewCountDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0)
		{
			return buildData(result,district,sectorType)
		}
	});
}
function buildData(result,district,sectorType){
	var str='';
	str+='<table class="table table-bordered" id="'+district.replace(/\s+/g, '')+'DataTable">';
		str+='<thead>';
			//str+='<th>District</th>';
			//str+='<th>Sector</th>';
			str+='<th>Sub Sector</th>';
			str+='<th>Department</th>';
			str+='<th>Company</th>';
			str+='<th>Line Of Activity</th>';
			str+='<th>Category</th>';
			str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
			str+='<th>Committed Employment</th>';
		str+='</thead>';
		for(var i in result)
		{
			str+='<tr>';
				//str+='<td>'+result[i].districtName+'</td>';
				//str+='<td>'+result[i].itSector+'</td>';
				str+='<td>'+result[i].subSector+'</td>';
				str+='<td>'+result[i].deptName+'</td>';
				str+='<td>'+result[i].nameOfCompany+'</td>';
				str+='<td>'+result[i].lineOfActivity+'</td>';
				str+='<td>'+result[i].category+'</td>';
				str+='<td>'+result[i].investment+'</td>';
				str+='<td>'+result[i].employment+'</td>';
			str+='</tr>';
		}
		
	str+='</table>';
	$("#sectorModalTitleId").html(district+' '+'District'+' '+sectorType+' '+'Industries'+' '+'Details');
	$("#sectorModalDivId").html(str);
	$("#"+district.replace(/\s+/g, '')+"DataTable").dataTable();
}

function getDepartmentWiseHierarchicalDetails(){
	$("#hieraricalViewErr").html(spinner);
	$("#hieraricalViewErr").append("<p>Please wait till the chart loads</p>");
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getDepartmentWiseHierarchicalDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#hieraricalViewErr").html(" ");
		var str='';
		str+='<button type="button" class="btn btn-default btn-sm hieraricalViewCls pull-right" attr_type="hide">Hide Graph View</button>';
		$("#hieraricalShowHideDiv").html(str);
		
		var hodCount = 0;
		var hodTotal = 0;
		for(var i in result)
		{
			if(i != 0 )
			{
				hodCount = hodCount + result[0].created;
				hodTotal = hodTotal + result[0].totalCount;
			}
		}
		var dataArr = [];
			dataArr.push({"id": 1, "parentId": 0, "Name": "SECRETARY IT,E & C DEPARTMENT", "title": "SECRETARY IT,E & C DEPARTMENT"});
			//dataArr.push({"id": 2, "parentId": 1, "Name": "MINISTER NARA LOKESH", "title": "MINISTER NARA LOKESH"});
			for(var i in result[0].ministerList)
			{
				dataArr.push({"id": 2, "parentId":1,"Name": result[0].ministerList[i].employeeName, "title": result[0].ministerList[i].employeeName, "postname": result[0].ministerList[i].postName, "count": result[0].ministerList[i].totalCount+"/"+result[0].ministerList[i].created});
			
			}
			dataArr.push({"id": 3, "parentId": 2, "Name": "VIJAYANAND", "title": "VIJAYANAND"});
			dataArr.push({"id": 4, "parentId": 3, "Name": "IT,E & C", "title": "IT,E & C", "count": result[0].totalCount+"/"+result[0].created});
			dataArr.push({"id": 5, "parentId": 3, "Name": "HODS", "title": "HODS", "count": hodTotal+"/"+hodCount});
			var k = 6;
			
			for(var i in result)
			{
				if(i == 0 )
				{
					dataArr.push({"id": k, "parentId": 4 , "Name": result[i].departmentName, "title": result[i].departmentName , "count": result[i].totalCount+"/"+result[i].created});
				}else{
					if(result[i].departmentName == 'ANDHRA PRADESH TECHNOLOGY SERVICES')
					{
						dataArr.push({"id": k, "parentId": 5, "Name": result[i].departmentName, "title": result[i].departmentName, "postname": "VALETI PREMCHAND", "count": result[i].totalCount+"/"+result[i].created});
					}else if(result[i].departmentName == 'DIRECTOR ESD')
					{
						dataArr.push({"id": k, "parentId": 5, "Name": result[i].departmentName, "title": result[i].departmentName, "postname": "SUNDAR B", "count": result[i].totalCount+"/"+result[i].created});
					}else if(result[i].departmentName == 'ANDHRAPRADESH INFORMATION TECHNOLOGY ACADEMY')
					{
						dataArr.push({"id": k, "parentId": 5, "Name": result[i].departmentName, "title": result[i].departmentName, "postname": "SUNDAR B", "count": result[i].totalCount+"/"+result[i].created});
					}else if(result[i].departmentName == 'ANDHRAPRADESH INNOVATION SOCIETY')
					{
						dataArr.push({"id": k, "parentId": 5, "Name": result[i].departmentName, "title": result[i].departmentName, "postname": "VALLI KUMARI VATSAVAYI", "count": result[i].totalCount+"/"+result[i].created});
					}else if(result[i].departmentName == 'ANDHRAPRADESH E PRAGATI AUTHORITY')
					{
						dataArr.push({"id": k, "parentId": 5, "Name": result[i].departmentName, "title": result[i].departmentName, "postname": "SUNDAR B", "count": result[i].totalCount+"/"+result[i].created});
					}else if(result[i].departmentName == 'ANDHRAPRADESH ELECTRONICS AND IT AGENCY')
					{
						dataArr.push({"id": k, "parentId": 5, "Name": result[i].departmentName, "title": result[i].departmentName, "postname": "K. BHASKAR REDDY", "count": result[i].totalCount+"/"+result[i].created});
					}else if(result[i].departmentName == 'SOCIETY FOR ANDHRA PRADESH NETWORK')
					{
						dataArr.push({"id": k, "parentId": 5, "Name": result[i].departmentName, "title": result[i].departmentName, "postname": "SUNDAR B", "count": result[i].totalCount+"/"+result[i].created});
					}
					
				}
				k = k +1;
			}
			for(var i in result[0].jsList)
			{
				dataArr.push({"id": k, "parentId": 6,"Name": result[0].jsList[i].employeeName, "title": result[0].jsList[i].employeeName, "postname": result[0].jsList[i].postName, "count": result[0].jsList[i].totalCount+"/"+result[0].jsList[i].created});
				k = k +1;
			}
			for(var i in result[0].directorList)
			{
				dataArr.push({"id": k, "parentId": 6, "Name": result[0].directorList[i].employeeName, "title": result[0].directorList[i].employeeName, "postname": result[0].directorList[i].postName, "count": result[0].directorList[i].totalCount+"/"+result[0].directorList[i].created});
				k = k +1;
			}
			for(var i in result[0].jdList)
			{
				dataArr.push({"id": k, "parentId": 6, "Name": result[0].jdList[i].employeeName, "title": result[0].jdList[i].employeeName, "postname": result[0].jdList[i].postName, "count": result[0].jdList[i].totalCount+"/"+result[0].jdList[i].created});
				k = k +1;
			}
			for(var i in result[0].specialOfficerList)
			{
				dataArr.push({"id": k, "parentId": 14, "Name": result[0].specialOfficerList[i].employeeName, "title": result[0].specialOfficerList[i].employeeName, "postname": result[0].specialOfficerList[i].postName, "count": result[0].specialOfficerList[i].totalCount+"/"+result[0].specialOfficerList[i].created});
				k = k +1;
			}
			for(var i in result[0].aaoList)
			{
				dataArr.push({"id": k, "parentId": 14, "Name": result[0].aaoList[i].employeeName, "title": result[0].aaoList[i].employeeName, "postname": result[0].aaoList[i].postName,"count": result[0].aaoList[i].totalCount+"/"+result[0].aaoList[i].created});
				k = k +1;
			}
			for(var i in result[0].pmList)
			{
				dataArr.push({"id": k, "parentId": 15, "Name": result[0].pmList[i].employeeName, "title": result[0].pmList[i].employeeName, "postname": result[0].pmList[i].postName,"count": result[0].pmList[i].totalCount+"/"+result[0].pmList[i].created});
				k = k +1;
			}
			for(var i in result[0].soList)
			{
				dataArr.push({"id": k, "parentId": 18, "Name": result[0].soList[i].employeeName, "title": result[0].soList[i].employeeName, "postname": result[0].soList[i].postName, "count": result[0].soList[i].totalCount+"/"+result[0].soList[i].created});
				k = k +1;
			}
			for(var i in result[0].asoList)
			{
				dataArr.push({"id": k, "parentId": 27, "Name": result[0].asoList[i].employeeName, "title": result[0].asoList[i].employeeName, "postname": result[0].asoList[i].postName, "count": result[0].asoList[i].totalCount+"/"+result[0].asoList[i].created});
				k = k +1;
			}
			for(var i in result[0].otherList)
			{
				dataArr.push({"id": k, "parentId": 28, "Name": result[0].otherList[i].employeeName, "title": result[0].otherList[i].employeeName, "postname": result[0].otherList[i].postName, "count": result[0].otherList[i].totalCount+"/"+result[0].otherList[i].created});
				k = k +1;
			}
			for(var i in result)
			{
				if(i != 0 )
				{
					if(result[i].otherList != null && result[i].otherList.length > 0)
					{
						for(var j in result[i].otherList)
						{
							if(result[i].departmentName == "ANDHRA PRADESH TECHNOLOGY SERVICES")
							{
								dataArr.push({"id": k, "parentId": 7, "Name": result[i].otherList[j].employeeName, "title": result[i].otherList[j].employeeName, "postname": result[i].otherList[j].postName, "count": result[i].otherList[j].totalCount+"/"+result[i].otherList[j].created});
								k = k +1;
							}else if(result[i].departmentName == "DIRECTOR ESD")
							{
								dataArr.push({"id": k, "parentId": 8, "Name": result[i].otherList[j].employeeName, "title": result[i].otherList[j].employeeName, "postname": result[i].otherList[j].postName, "count": result[i].otherList[j].totalCount+"/"+result[i].otherList[j].created});
								k = k +1;
							}else if(result[i].departmentName == "ANDHRAPRADESH INFORMATION TECHNOLOGY ACADEMY")
							{
								dataArr.push({"id": k, "parentId": 9, "Name": result[i].otherList[j].employeeName, "title": result[i].otherList[j].employeeName, "postname": result[i].otherList[j].postName, "count": result[i].otherList[j].totalCount+"/"+result[i].otherList[j].created});
								k = k +1;
							}else if(result[i].departmentName == "SOCIETY FOR ANDHRA PRADESH NETWORK")
							{
								dataArr.push({"id": k, "parentId": 10, "Name": result[i].otherList[j].employeeName, "title": result[i].otherList[j].employeeName, "postname": result[i].otherList[j].postName, "count": result[i].otherList[j].totalCount+"/"+result[i].otherList[j].created});
								k = k +1;
							}else if(result[i].departmentName == "ANDHRAPRADESH INNOVATION SOCIETY")
							{
								dataArr.push({"id": k, "parentId": 11, "Name": result[i].otherList[j].employeeName, "title": result[i].otherList[j].employeeName, "postname": result[i].otherList[j].postName, "count": result[i].otherList[j].totalCount+"/"+result[i].otherList[j].created});
								k = k +1;
							}else if(result[i].departmentName == "ANDHRAPRADESH E PRAGATI AUTHORITY")
							{
								dataArr.push({"id": k, "parentId": 12, "Name": result[i].otherList[j].employeeName, "title": result[i].otherList[j].employeeName, "postname": result[i].otherList[j].postName, "count": result[i].otherList[j].totalCount+"/"+result[i].otherList[j].created});
								k = k +1;
							}else if(result[i].departmentName == "ANDHRAPRADESH ELECTRONICS AND IT AGENCY")
							{
								dataArr.push({"id": k, "parentId": 13, "Name": result[i].otherList[j].employeeName, "title": result[i].otherList[j].employeeName, "postname": result[i].otherList[j].postName, "count": result[i].otherList[j].totalCount+"/"+result[i].otherList[j].created});
								k = k +1;
							}
						}
					}
					
				}
			}
		var peopleElement = document.getElementById("hieraricalView");
		orgChart = new getOrgChart(peopleElement, {
			primaryFields: ["name", "title", "postname", "count"],
			enableEdit: false,
			expandToLevel: 4,
			scale: 12,
			enableDetailsView: false,
			layout: getOrgChart.MIXED_HIERARCHY_RIGHT_LINKS,
			boxSizeInPercentage: {
				minBoxSize: {
					width: 7,
					height: 7
				},
				boxSize: {
					width: 22,
					height: 25
				},
				maxBoxSize: {
					width: 120,
					height: 120
				}
			},
			dataSource: dataArr
		});
		//orgChart.insertNode(5,{"id": k , "Name": "JS(HRD)"});
		//orgChart.insertNode(5,{"id": k , "Name": "Director (Comms & Infra)"});
		//orgChart.insertNode(5,{"id": k , "Name": "JD (Prom - 1)"});
	});
}
function getCMEDOBOverview(divId,blockId,type){
	if(type =="overview"){
		$("#cMeoDBTotalId").html(spinner)
	}else{
		$("#cmedobBlockMainDivId").html(spinner);
		$("#cmedobDepartmentBlockMainDivId").html(spinner);
	}
	var json = {
		 sector:"B",
		 fromDate:getDateInRequiredFormat(globalFromDate),
		 toDate:getDateInRequiredFormat(globalToDate)
	}
	$.ajax({                
		type:'POST',    
		url: 'getCMEDOBOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			if(type =="overview"){
				buildgetCMEDOBOverview(result);
			}else{
				buildgetCMEDOBOverview(result);
				buildgetCMEDOBDetailed(result,divId,blockId,type);
				buildgetCMEDOBDepartmentDetailed(result);
			}
		}else{
			if(type =="overview"){
				$("#cMeoDBTotalId").html("0 /")
				$("#cMeoDBApprovedId").html("0")
			}else{
				$("#"+divId+"Block"+blockId).html("No Data Available");
				$("#cmedobBlockMainDivId").html("No Data Available");
				$("#cmedobDepartmentBlockMainDivId").html("No Data Available");
			}
			
		}
	});		
}
function buildgetCMEDOBOverview(result){
	
	if(result !=null && result.overviewDtls != null){
		if(result.overviewDtls.total !=null && result.overviewDtls.total>0){
			$("#cMeoDBTotalId").html(result.overviewDtls.total+ "/")
		}else{
			$("#cMeoDBTotalId").html("0 /")
		}
		if(result.overviewDtls.aprooved !=null && result.overviewDtls.aprooved>0){
			$("#cMeoDBApprovedId").html(result.overviewDtls.aprooved)
		}else{
			$("#cMeoDBApprovedId").html("0")
		}
		
	}
}
function buildgetCMEDOBDetailed(result,divId,blockId,type){
	var str='';
	if(result !=null && result.overviewDtls !=null){
	str+='<div class="block_styles">';
		str+='<div class="row">';
			str+='<div class="col-sm-2">';	
				str+='<img src="Assests/images/total-icon.png" />';
			str+='</div>';
			str+='<div class="col-sm-10">';
				str+='<h3 class="font_weight">Total Status<br> OverView </h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-6">';	
				str+='<h4 class="font_weight m_top10">Total - '+result.overviewDtls.total+'</h4>';
				str+='<div id="totalStatusGraphId" style="height:200px;"></div>';	
			str+='</div>';
			str+='<div class="col-sm-6">';	
				str+='<div class="statusColorCss">';
					str+='<div class="row">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Approved_icon.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#47E68D"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='Approved';
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.overviewDtls.aprooved+' <small style="color:green;font-weight:bold;">'+result.overviewDtls.approvedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		
				str+='<div class="statusColorCss" style="border-top:none;">';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Rejected_iocn.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#F55A5A"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='Rejected'; 
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.overviewDtls.rejected+' <small style="color:green;font-weight:bold;">'+result.overviewDtls.rejectedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		
				str+='<div class="statusColorCss" style="border-top:none;">';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Approved_icon.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#8D4653"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='ReApproved'; 
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.overviewDtls.reAprooved+' <small style="color:green;font-weight:bold;">'+result.overviewDtls.reApprovedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';

	str+='<div class="pendingstatusColorCss">';
		str+='<div class="row">';
			str+='<div class="col-sm-3">';	
				str+='<img src="Assests/images/Pending_icon 70x70.png" />';
			str+='</div>';
			str+='<div class="col-sm-9">';	
				str+='<h4 class="font_weight"><span class="approvedMainCss" style="background-color:#71A8EE;margin-right: 5px;"></span>Pending</h4>';
					str+='<h4 class="font_weight m_top10">'+result.overviewDtls.totalPending+' <small style="color:green;font-weight:bold;">'+result.overviewDtls.pendingPerc+'%</small></h4>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-6">';
							str+='<h5 class="">Within SLA</h5>';
							str+='<h4 class="font_weight">'+result.overviewDtls.pendingWithinSLA+' <small style="color:green;font-weight:bold;">'+result.overviewDtls.withinSLAPerc+'%</small></h4>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<h5 class="">Beyond SLA</h5>';
							str+='<h4 class="font_weight">'+result.overviewDtls.pendingBeyondSLA+' <small style="color:green;font-weight:bold;">'+result.overviewDtls.beyongSLAPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';	
		str+='</div>';	
	str+='</div>';
	$("#cmedobBlockMainDivId").html(str);
}else{
	$("#cmedobBlockMainDivId").html("No Data Available");
}

	var ApprovedCount=0;
	var RejectedCount=0;
	var ReApprovedCount=0;
	var PendingCount=0;
	
	if(result !=null){
		var pendingTotal = result.overviewDtls.pendingWithinSLA+result.overviewDtls.pendingBeyondSLA;
		
		ApprovedCount=parseFloat(result.overviewDtls.approvedPerc);
		RejectedCount=parseFloat(result.overviewDtls.rejectedPerc);
		ReApprovedCount=parseFloat(result.overviewDtls.reApprovedPerc);
		PendingCount=parseFloat(result.overviewDtls.pendingPerc);
	}
	
	var id = 'totalStatusGraphId';
	var type = {
		//width:350,
		type: 'pie',
		backgroundColor:'transparent',
		options3d: {
			enabled: true,
			alpha: 25
		}
	};
	var title = {
		text: ''
	};
	var tooltip = {
		useHTML: true,
		backgroundColor: '#FCFFC5', 
		formatter: function() {
			var cnt = this.point.count;
			return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+Highcharts.numberFormat(this.percentage,1)+"%</b>";
		}  
	}; 
	var plotOptions ={ 
		pie: {
			innerSize: 100,
			depth: 70,
			dataLabels:{
				useHTML: true,
				enabled: false,
				formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return "<b style='color:"+this.point.color+"'>"+this.point.name+"<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
						}
					} 
			},
			showInLegend: true
		},
	};
	var legend = {
		enabled: false,
		layout: 'vertical',
		align: 'left',
		verticalAlign: 'bottom',
		useHTML: true,
		
		labelFormatter: function() {
			return '<div><span style="color:'+this.color+'">'+this.name + '-'+Highcharts.numberFormat(this.percentage,1)+'%</span></div>';
		}
	};
	var data = [{
		name: '',
		data: [{
				name: 'Approved',
				y: ApprovedCount,
				color:"#47E68D"
			}, {
				name: 'Rejected',
				y: RejectedCount,
				color:"#F55A5A"
			}, {
				name: 'Re-Approved',
				y: ReApprovedCount,
				color:"#8D4653"
			}, {
				name: 'Pending',
				y: PendingCount,
				color:"#71A8EE"
			}]
	}];
	highcharts(id,type,data,plotOptions,title,tooltip,legend);
	
	
}
function buildgetCMEDOBDepartmentDetailed(result){
	
	var str='';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-4">';
			str+='<div class="department_block" style="border:2px solid #007500">';
				str+='<div class="media">';
					str+='<div class="media-left" style="vertical-align: middle;">';
						str+='<img src="Assests/images/Approved_icon.png">';
					str+='</div>';
					str+='<div class="media-body">';
							str+='<h3 class="font_weight">High <span style="color:#007500">Approval</span> Department</h3>';
							str+='<p class="m_top5">'+result.highApprovalDepartmentName+'</p>';
							str+='<h3 class="font_weight m_top5">'+result.highApprovalDepartmentCount+'</h3>';
							str+='<p class="m_top10" style="border-top: 1px solid green;"></p>';	
							str+='<h3 class="font_weight m_top10">Low <span style="color:#007500">Approval</span> Department</h3>';
							str+='<p class="m_top5">'+result.lowApprovalDepartmentName+'</p>';
							str+='<h3 class="font_weight m_top5">'+result.lowApprovalDepartmentCount+'</h3>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-4">';
			str+='<div class="department_block" style="border:2px solid #FF003C">';
				str+='<div class="media">';
					str+='<div class="media-left" style="vertical-align: middle;">';
						str+='<img src="Assests/images/Rejected_iocn.png">';
					str+='</div>';
					str+='<div class="media-body">';
							str+='<h3 class="font_weight">High <span style="color:#FF003C">Rejected</span> Department</h3>';
							str+='<p class="m_top5">'+result.highRejectedDepartmentName+'</p>';
							str+='<h3  class="font_weight m_top5">'+result.highRejectedDepartmentCount+'</h3>';
						str+='<p class="m_top10" style="border-top: 1px solid #FF003C;"></p>';	
							str+='<h3 class="font_weight m_top10">Low <span style="color:#FF003C">Rejected</span> Department</h3>';
							str+='<p class="m_top5">'+result.lowRejectedDepartmentName+'</p>';
							str+='<h3 class="font_weight m_top5">'+result.lowRejectedDepartmentCount+'</h3>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-4">';
			str+='<div class="department_block" style="border:2px solid #71A8EE">';
				str+='<div class="media">';
					str+='<div class="media-left" style="vertical-align: middle;">';
						str+='<img src="Assests/images/Pending_Icon.png">';
					str+='</div>';
					str+='<div class="media-body">';
							str+='<h3 class="font_weight">High <span style="color:#71A8EE">Pending</span> Department</h3>';
							str+='<p class="m_top5">'+result.highPendingDepartmentName+'</p>';
							str+='<h3  class="font_weight m_top5">'+result.highPendingDepartmentCount+'</h3>';
						str+='<p class="m_top10" style="border-top: 1px solid #71A8EE;"></p>';	
							str+='<h3 class="font_weight m_top10">Low <span style="color:#71A8EE">Pending</span> Department</h3>';
							str+='<p class="m_top5">'+result.lowPendingDepartmentName+'</p>';
							str+='<h3 class="font_weight m_top5">'+result.lowPendingDepartmentCount+'</h3>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';		
			
	  str+='</div>';
	  $("#cmedobDepartmentBlockMainDivId").html(str);
}

function getCMEDOBReportStatusWise(sectorType){
	$("#cmedobDivId").html(spinner);
	   var json = {
		 sector:sectorType,
		 fromDate:getDateInRequiredFormat(globalFromDate),
		 toDate:getDateInRequiredFormat(globalToDate)
	     }
	$.ajax({                
		type:'POST',    
		url: 'getCMEDOBReportStatusWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result != null && result.length > 0) {
			buildCMEDOBReportStatusWise(result,sectorType)	
		} else {
			$("#cmedobDivId").html("<p style='margin-left:13px'>NO DATA AVAILABLE.</p>");
		}
		
	});
		
}
function buildCMEDOBReportStatusWise(result,sectorType){
	var str='';
		str+='<div class="col-sm-12  m_top20">';
			str+='<div class="table-responsive">';	
				str+='<table  class="table table-bordered"  id="cmedobTableId">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Departments Name</th>';
							str+='<th>Clearance Name</th>';
							str+='<th>Total</th>';
							str+='<th>Approved</th>';
							str+='<th>Rejected</th>';
							str+='<th>Re-Approved</th>';
							str+='<th>Total Pending</th>';
							str+='<th>Pending Within SLA </th>';
							str+='<th>Pending Beyond SLA</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
					
						for(var j in result[i].subList){
							str+='<tr>';
								str+='<td >'+result[i].dashboardName+'</td>';
								str+='<td id="'+result[i].subList[j].clearenceId+'">'+result[i].subList[j].clearenceName+'</td>';
								if (result[i].subList[j].totalApplications > 0) {
									str+='<td  style="cursor:pointer;color:rgb(51, 122, 183);" class="cmeodbStatusCls" attr_status_id = "0" attr_clearncr_id ='+result[i].subList[j].clearenceId+' attr_dept_code = '+result[i].subList[j].dashBoardNO+' attr_sector_type="'+sectorType+'">'+result[i].subList[j].totalApplications+'</td>';
								} else {
									str+='<td > - </td>';
								}
								if (result[i].subList[j].totalApproved > 0) {
									str+='<td class="cmeodbStatusCls" style="cursor:pointer;color:rgb(51, 122, 183);" attr_status_id = "1" attr_clearncr_id ='+result[i].subList[j].clearenceId+' attr_dept_code = '+result[i].subList[j].dashBoardNO+' attr_sector_type="'+sectorType+'">'+result[i].subList[j].totalApproved+'</td>';
								} else {
									str+='<td > - </td>';
								}
								if (result[i].subList[j].totalRejected > 0) {
									str+='<td class="cmeodbStatusCls" style="cursor:pointer;color:rgb(51, 122, 183);" attr_status_id = "3" attr_clearncr_id ='+result[i].subList[j].clearenceId+' attr_dept_code = '+result[i].subList[j].dashBoardNO+' attr_sector_type="'+sectorType+'">'+result[i].subList[j].totalRejected+'</td>';
								} else {
									str+='<td > - </td>';
								}
								if (result[i].subList[j].totalReApproved > 0) {
									str+='<td class="cmeodbStatusCls" style="cursor:pointer;color:rgb(51, 122, 183);" attr_status_id = "2" attr_clearncr_id ='+result[i].subList[j].clearenceId+' attr_dept_code = '+result[i].subList[j].dashBoardNO+' attr_sector_type="'+sectorType+'">'+result[i].subList[j].totalReApproved+'</td>';
								} else {
									str+='<td > - </td>';
								}
								if (result[i].subList[j].totalPending > 0) {
									str+='<td class="cmeodbStatusCls"  style="cursor:pointer;color:rgb(51, 122, 183);" attr_status_id = "4" attr_clearncr_id ='+result[i].subList[j].clearenceId+' attr_dept_code = '+result[i].subList[j].dashBoardNO+' attr_sector_type="'+sectorType+'">'+result[i].subList[j].totalPending+'</td>';
								} else {
									str+='<td >- </td>';
								}
								
								if (result[i].subList[j].pendingWithInSLA > 0) {
									str+='<td class="cmeodbStatusCls" style="cursor:pointer;color:rgb(51, 122, 183);" attr_status_id = "5" attr_clearncr_id ='+result[i].subList[j].clearenceId+' attr_dept_code = '+result[i].subList[j].dashBoardNO+' attr_sector_type="'+sectorType+'">'+result[i].subList[j].pendingWithInSLA+'</td>';
								} else {
									str+='<td >- </td>';
								}
								if (result[i].subList[j].pendingBeyondSLA > 0) {
									str+='<td class="cmeodbStatusCls" style="cursor:pointer;color:rgb(51, 122, 183);" attr_status_id = "6" attr_clearncr_id ='+result[i].subList[j].clearenceId+' attr_dept_code = '+result[i].subList[j].dashBoardNO+' attr_sector_type="'+sectorType+'">'+result[i].subList[j].pendingBeyondSLA+'</td>';
							
								} else {
									str+='<td >- </td>';
								}
							str+='</tr>';
						}
					}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
		$("#cmedobDivId").html(str);
		$("#cmedobTableId").dataTable();
}


$(document).on("click",".cmeodbStatusCls",function(){
  $("#emeodbStatusModalId").modal("show");
  $("#emeodbApplicationDtlsDivId").html(spinner)
   var statusId=$(this).attr("attr_status_id");
   var clearenceCode=$(this).attr("attr_clearncr_id");
   var deptCode=$(this).attr("attr_dept_code");
   var sectorType=$(this).attr("attr_sector_type");
	var json = {
		 sector:sectorType,
		 fromDate:getDateInRequiredFormat(globalFromDate),
		 toDate:getDateInRequiredFormat(globalToDate),
		 deptCode:deptCode,
		 clearence:clearenceCode,
		 status:statusId
		
	}
  $.ajax({                
    type:'POST',    
    url: 'getCMeoDBStatusCountDetails',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
    if (result != null && result.length > 0) {
     buildCMeoDBStatusCountDetails(result)  
    } else {
      $("#emeodbApplicationDtlsDivId").html("NO DATA AVAILABLE.");
    }
  });
    
});
function buildCMeoDBStatusCountDetails(result){
	var str='';
		str+='<div class="table-responsive">';	
			str+='<table  class="table table-bordered"  id="emeodbApplicationDtlsDataTblId">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>District Name</th>';
						str+='<th>Sector Name</th>';
						str+='<th>Industry Name</th>';
						str+='<th>Category Name</th>';
						str+='<th>Activity</th>';
						str+='<th>Employment</th>';
						str+='<th>Investment Amount</th>';
						str+='<th>Status</th>';
						str+='<th>Approval File Id</th>';
						str+='<th>Address</th>';
						str+='<th>Appication Filled Date</th>';
						str+='<th>Recieved Date</th>';
						str+='<th>Approval Date</th>';
						str+='<th>PermApproval Date</th>';
						str+='<th>Sla Days </th>';
						//str+='<th>Document URL</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
				for(var i in result){
				str+='<tr>';
							str+='<td >'+result[i].districtName+'</td>';
							if (result[i].sectorName != null && result[i].sectorName.length > 0) {
									str+='<td class="tooltipCls" data-container="body"  title="'+result[i].sectorName+'" style="cursor:pointer;">'+result[i].sectorName.substr(0,10)+'...</td>';
							} else {
								str+='<td>-</td>';
							}
							if (result[i].industryName != null ) {
							str+='<td class="tooltipCls" data-container="body" title="'+result[i].industryName+'" style="cursor:pointer;">'+result[i].industryName.substr(0,10)+"..."+'</td>';	
							} else {
								str+='<td>-</td>';
							}
							str+='<td >'+result[i].category+'</td>';
							if (result[i].activity != null && result[i].activity.length > 0) {
							  str+='<td class="tooltipCls" data-container="body" title="'+result[i].activity+'" style="cursor:pointer;">'+result[i].activity.substr(0,10)+"..."+'</td>';	
							} else {
								 str+='<td>-</td>';
							}
							str+='<td >'+result[i].empolyeement+'</td>';
							str+='<td >'+result[i].investmentAmount+'</td>';
							str+='<td >'+result[i].status+'</td>';
							//str+='<td >'+result[i].approvalFileId+'</td>';
							 if (result[i].url != null && result[i].url.length > 0) {
								 var urlDtsArr = result[i].url.split("=");
								str+='<td><a href="'+result[i].url+'" target="_blank">'+urlDtsArr[1]+'</a></td>';
							} else {
								str+='<td>-</td>';
							} 
							str+='<td class="tooltipCls" data-container="body" title="'+result[i].address+'" style="cursor:pointer;">'+result[i].address.substr(0,10)+"..."+'</td>';
							str+='<td >'+result[i].appFilledDate+'</td>';
							str+='<td >'+result[i].recievedDate+'</td>';
							str+='<td >'+result[i].approvalDate+'</td>';
							str+='<td >'+result[i].permApprovalDate+'</td>';
							str+='<td >'+result[i].slaDays+'</td>';
							
							
				str+='</tr>';
				}
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	$("#emeodbApplicationDtlsDivId").html(str);
	$("#emeodbApplicationDtlsDataTblId").dataTable();
	$(".tooltipCls").tooltip();	
}

function getCMeoDBSectorWiseStatusDetais(){
	$("#cmedobSectorWiseInformationId").html(spinner)
	$("#cmedobSectorWiseElectronicSectorId").html(spinner)
	
	var json={
		sector:"B",
		fromDate:getDateInRequiredFormat(globalFromDate),
		toDate:getDateInRequiredFormat(globalToDate)
	}
	$.ajax({                
		type:'POST',    
		url: 'getCMeoDBSectorWiseStatusDetais',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildCMeoDBSectorWiseStatusDetais(result);
			buildCMeoDBSectorWiseElectronicSectorDetais(result);
		}else{
			$("#cmedobSectorWiseInformationId").html("No Data Available")
			$("#cmedobSectorWiseElectronicSectorId").html("No Data Available")
		}
	});	
}

function buildEOfcDepartWiseOverviewDetails(result){
	var str = '';
	var daysArr=["0to7DAYS","8to15DAYS","16to30DAYS","31to60DAYS","60DAYS"];
	str+='<div class="row">';	
		str+='<div class="col-sm-12">';	
			str+='<h4 class="text-capital font_weight text-center">Information TECHNOLOGY Electronics And Communication Department</h4>';
		str+='</div>';
		/*str+='<div class="col-sm-2">';	
			str+='<button type="button" class="btn btn-default btn-sm hieraricalViewCls pull-right hieraricalButtonShowCls" attr_type="show" >Show Graph View</button>';
		str+='</div>';*/
	str+='</div>';
	str+='<div class="row">';
	str+='<div class="col-sm-6" style="padding-left: 30px;">';	
			str+='<ul class="list-inline ">';	
				str+='<li><span class="roundCircleITC" style="background-color:#00af50"></span> 0% - 10%</li>';	
				str+='<li><span class="roundCircleITC" style="background-color:#ffba00"></span> 10% - 20%</li>';	
				str+='<li><span class="roundCircleITC" style="background-color:#ff0000"></span> 20% and above&nbsp;&nbsp;&nbsp;</li>';	
			str+='</ul>  ';	
	str+='</div>'; 
	str+='<div class="col-sm-6 ">';	
			str+='<div class="pull-right" id="lastUpdatedTimeDivId"><span style="padding-right: 15px;"><b>Last Updated Time : </b>'+result[0].lastUpdatedTime+'</span></div>';
	str+='</div>'; 
	str+='</div>'; 
	for(var i in result){
		if(result[i].departmentName == "INFORMATION TECHNOLOGY ELECTRONICS AND COMMUNICATION DEPARTMENT"){
				str+='<div class="col-sm-12 m_top20">';	
				str+='<div class="col-sm-12" style="border:1px solid #000;padding:5px;">';	
						
						str+='<div class="col-sm-4">';
							str+='<h3 class="font_weight" style="text-align: center;margin-top: 40px;">Secretariat Department</h3>';
							str+='<div class="row" style="margin-top: 50px;">';
								str+='<div class="col-sm-4">';
									str+='<h4 class="font_weight">'+result[i].created+'</h4>';
									str+='<h5 class="font_weight m_top10">TOTAL</h5>';
								str+='</div>';
								str+='<div class="col-sm-4">';
									str+='<h4 class="font_weight">'+result[i].actionFiles+'</h4>';
									str+='<h5 class="font_weight m_top10">ACTION</h5>';
								str+='</div>';
								str+='<div class="col-sm-4">';
									str+='<h4 class="font_weight">'+result[i].totalCount+' <span style="color:green;">&nbsp; &nbsp;&nbsp;'+result[i].percentage+'%</span></h4>';
									str+='<h5 class="font_weight m_top10">TOTAL PENDENCY</h5>';
								str+='</div>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-8">';
							/* str+='<div class="row">';
							for(var d in daysArr){
								str+='<div class="col-sm-2 border_left_RightE">';
									str+='<div id="'+daysArr[d]+'" style="height:200px;"></div>';
								str+='</div>';
							}
							str+='</div>'; */
							str+='<ul class="itcdashboard-list">';
							for(var d in daysArr){
								str+='<li class=" border_left_RightE">';
									str+='<div id="'+daysArr[d]+'" style="height:200px;"></div>';
								str+='</li>';
							}
							str+='</ul>';
						str+='</div>';
				str+='</div>';
			str+='</div>';
		
		}
	}
	
	str+='<div class="col-sm-12 m_top20">';	
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered table_ITC" id="dataTableITCDepartment">';
				str+='<thead style="background-color:#CCCCCC">';
					str+='<tr>';
						str+='<th>Departments</th>';
						str+='<th>Total</th>';
						str+='<th>Action</th>';
						str+='<th>Total Pendency</th>';
						str+='<th>%</th>';
						str+='<th>0 - 7 days</th>';
						str+='<th>8 - 15 days</th>';
						str+='<th>16 - 30 days</th>';
						str+='<th>31 - 60 days</th>';
						str+='<th> > 60 days</th>';
					str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
						if(result[i].departmentName != "ITE & C" && result[i].departmentName != "INFORMATION TECHNOLOGY ELECTRONICS AND COMMUNICATION DEPARTMENT"){
							str+='<tr>';
								str+='<td style="cursor:pointer;"><i class="fa fa-external-link departmentDetailsCls" aria-hidden="true" class="" attr_department_id="'+result[i].departmentId+'" attr_department_name="'+result[i].departmentName+'"></i><b> '+result[i].departmentName+'</b></td>';
								str+='<td >'+result[i].created+'</td>';
								str+='<td >'+result[i].actionFiles+'</td>';
								str+='<td >'+result[i].totalCount+'</td>';
								if(result[i].percentage < 10){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage >= 10 && result[i].percentage < 20){
									str+='<td style="background-color:#FFBA00;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage >= 20){
									str+='<td style="background-color:#ff0000;color:#fff">'+result[i].percentage+'</td>';
								}
								str+='<td >'+result[i].zeroToSeven+'</td>';
								str+='<td >'+result[i].eightToFifteen+'</td>';
								str+='<td >'+result[i].sixteenToThirty+'</td>';
								str+='<td >'+result[i].thirtyoneToSixty+'</td>';
								str+='<td >'+result[i].aboveSixty+'</td>';
							str+='</tr>';
						}
					}
					str+='</tbody>';
					for(var i in result){
						if(result[i].departmentName == "ITE & C"){
							str+='<tr>';
								str+='<td style="text-align: right;" class="font_weight">GRAND TOTAL</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].created+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].actionFiles+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].totalCount+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].percentage+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].zeroToSeven+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].eightToFifteen+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].sixteenToThirty+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].thirtyoneToSixty+'</td>';
								str+='<td class="font_weight" style="background-color:#e7e7e7;">'+result[i].aboveSixty+'</td>';
							str+='</tr>';
						}
					}
					
			str+='</table>';
		str+='</div>';
	str+='</div>';
	str+='<div class="col-sm-12 m_top20">';	
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered table_ITC" id="dataTableITCHODS">';
				str+='<thead style="background-color:#CCCCCC">';
					str+='<tr>';
						//str+='<th style="color:#A349A4;text-align:center">HODS</th>';
						str+='<th>HODS</th>';
						str+='<th>Department</th>';
						str+='<th>Total</th>';
						str+='<th>Action</th>';
						str+='<th>Total Pendency</th>';
						str+='<th>%</th>';
						str+='<th>0 - 7 days</th>';
						str+='<th>8 - 15 days</th>';
						str+='<th>16 - 30 days</th>';
						str+='<th>31 - 60 days</th>';
						str+='<th> > 60 days</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					for(var i in result[0].subList){
						if(result[0].subList[i] != null){
							str+='<tr>';
								str+='<td>'+result[0].subList[i].postName+'</td>';
								str+='<td>'+result[0].subList[i].departmentName+'</a></td>';
								str+='<td>'+result[0].subList[i].created+'</td>';
								str+='<td>'+result[0].subList[i].actionFiles+'</td>';
								str+='<td>'+result[0].subList[i].totalCount+'</td>';
								if(result[0].subList[i].percentage < 10){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[0].subList[i].percentage+'</td>';
								}else if(result[0].subList[i].percentage >= 10 && result[0].subList[i].percentage < 20){
									str+='<td style="background-color:#FFBA00;color:#fff">'+result[0].subList[i].percentage+'</td>';
								}else if(result[0].subList[i].percentage >= 20){
									str+='<td style="background-color:#FF0000;color:#fff">'+result[0].subList[i].percentage+'</td>';
								}
								str+='<td >'+result[0].subList[i].zeroToSeven+'</td>';
								str+='<td >'+result[0].subList[i].eightToFifteen+'</td>';
								str+='<td >'+result[0].subList[i].sixteenToThirty+'</td>';
								str+='<td >'+result[0].subList[i].thirtyoneToSixty+'</td>';
								str+='<td >'+result[0].subList[i].aboveSixty+'</td>';
							str+='</tr>';
						}
					}
					str+='<tbody>';
			str+='</table>';
		str+='</div>';
	str+='</div>';
	
$("#eOfficeDeparmentsOverViewBlock").html(str);
$("#dataTableITCDepartment").dataTable({
		"paging":   false,
		"info":     false,
		"searching": false,
		"autoWidth": true
});
$("#dataTableITCHODS").dataTable({
	"paging":   false,
	"info":     false,
	"searching": false,
	"autoWidth": true
});
for(var i in daysArr){
		var mainArr=[];
		var daysNamesArr=[];
		var globalStatusObj={}
		for(var j in result){
			if(result[j].departmentName == "INFORMATION TECHNOLOGY ELECTRONICS AND COMMUNICATION DEPARTMENT"){
				globalStatusObj={"0-7 DAYS":result[j].zeroToSeven,"8-15 DAYS":result[j].eightToFifteen,"16-30 DAYS":result[j].sixteenToThirty,"31-60 DAYS":result[j].thirtyoneToSixty,"Above 60 DAYS":result[j].aboveSixty}
				
				var eightToFifteenPercArr=[];
				var sixteenToThirtyPercArr=[];
				var thirtyoneToSixtyPercArr=[];
				var aboveSixtyPercArr=[];
				var zeroToSevenPercArr=[];
				eightToFifteenPercArr.push(parseFloat(result[j].eightToFifteenPerc))
				sixteenToThirtyPercArr.push(parseFloat(result[j].sixteenToThirtyPerc))
				thirtyoneToSixtyPercArr.push(parseFloat(result[j].thirtyoneToSixtyPerc))
				aboveSixtyPercArr.push(parseFloat(result[j].aboveSixtyPerc))
				zeroToSevenPercArr.push(parseFloat(result[j].zeroToSevenPerc))
			}
		}
		if(daysArr[i] == "0to7DAYS"){
			mainArr.push(zeroToSevenPercArr)
			daysNamesArr.push("0-7 DAYS")
		}else if(daysArr[i] == "8to15DAYS"){
			mainArr.push(eightToFifteenPercArr)
			daysNamesArr.push("8-15 DAYS")
		}else if(daysArr[i] == "16to30DAYS"){
			mainArr.push(sixteenToThirtyPercArr)
			daysNamesArr.push("16-30 DAYS")
		}else if(daysArr[i] == "31to60DAYS"){
			mainArr.push(thirtyoneToSixtyPercArr)
			daysNamesArr.push("31-60 DAYS")
		}else if(daysArr[i] == "60DAYS"){
			mainArr.push(aboveSixtyPercArr)
			daysNamesArr.push("Above 60 DAYS")
		}
		
		
		$("#"+daysArr[i]).highcharts({
			colors:['#12A89D'],
			chart: {
				type: 'column'
			},
			title: {
				text: ''
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,	
				 categories:daysNamesArr,
				 labels: {
					useHTML:true,
					formatter: function() {
						return '<h5>'+this.value+'<br/><p style="color:#000;text-align:center;"><b>'+globalStatusObj[this.value]+'</b></p></h5>';
						
						
					},
					
				}
			},
			yAxis: {
				 min: 0,
				 gridLineWidth: 0,
				 minorGridLineWidth: 0,
				labels: {
					enabled: false
				},
				title: {
					text: ''
				}
			},
			legend: {
				enabled: false
			},
			plotOptions:{
				column: {
						pointWidth: 30,
						gridLineWidth: 15,
					}
				},
			tooltip: {
				pointFormat: 'Pendency:<b>{point.y}</b>'
			},
			series: [{
				name: 'Pendency',
				data: mainArr,
				dataLabels: {
					useHTML:true,
					enabled: true,
					color: '#000',
					align: 'center',
					style: {
						fontSize:"14px",
						fontWeight:"bold"
					},
					formatter: function() {
							return '<span>'+this.y+' %</span>';
					},
						
				}
			}]
		});
	}
}
$(document).on("click",".departmentDetailsCls",function(){	
	var departmentId =  $(this).attr("attr_department_id")
	var departmentName =  $(this).attr("attr_department_name")
	$("#departmentModalId").modal("show");
	$("#headingTitle").html("<b>"+departmentName+ "  DETAILS</b>")
	getEofficeDesginationDetailsByDepartment(departmentId);
});
function getEofficeDesginationDetailsByDepartment(departmentId){
	$("#departmentDetailsDivId").html(spinner);
	var json = {
		departmentId:departmentId,	
		//fromDate:"2017-11-01",	
		//toDate:"2017-12-31"		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEofficeDesginationWiseDetailsFrDepartmentNew',  //'getEofficeDesginationWiseDetailsFrDepartment',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildEofficeDesginationDetailsByDepartment(result)
		}else{
			$("#departmentDetailsDivId").html("No Data Available");
		}
	});		
}
function buildEofficeDesginationDetailsByDepartment(result){
	var str='';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table_ITC" id="dataTableDepartmentId">';
						str+='<thead style="background-color:#CCCCCC">';
						str+='<tr>';
							str+='<th class="text-center">POST NAME</th>';
							str+='<th class="text-center">EMPLOYEE</th>';
							str+='<th>Total</th>';
							str+='<th>Action</th>';
							str+='<th>Total Pendency</th>';
							str+='<th>%</th>';
							str+='<th>0 - 7 days</th>';
							str+='<th>8 - 15 days</th>';
							str+='<th>16 - 30 days</th>';
							str+='<th>31 - 60 days</th>';
							str+='<th> > 60 days</th>';
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td>'+result[i].designation+'</td>';
								str+='<td>'+result[i].employeeName+'</td>';
								str+='<td>'+result[i].created+'</td>';
								str+='<td>'+result[i].actionFiles+'</td>';
								str+='<td>'+result[i].totalCount+'</td>';
								if(result[i].percentage < 10){
									str+='<td style="background-color:#00AF50;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage>= 10 && result[i].percentage < 20){
									str+='<td style="background-color:#FFBA00;color:#fff">'+result[i].percentage+'</td>';
								}else if(result[i].percentage >= 20){
									str+='<td style="background-color:#FF0000;color:#fff">'+result[i].percentage+'</td>';
								}
								//str+='<td>'+result[i].percentage+'</td>';
								str+='<td>'+result[i].zeroToSeven+'</td>';
								str+='<td >'+result[i].eightToFifteen+'</td>';
								str+='<td>'+result[i].sixteenToThirty+'</td>';
								str+='<td >'+result[i].thirtyoneToSixty+'</td>';
								str+='<td>'+result[i].aboveSixty+'</td>';
							str+='</tr>';
						}
						str+='</tbody>';
				str+='</table>';
			str+='</div>';
		$("#departmentDetailsDivId").html(str);
		$("#dataTableDepartmentId").dataTable({
			"paging":   true,
			"info":     false,
			"searching": true,
			"autoWidth": true,
			"iDisplayLength": 13,
			"aaSorting": [], 
			"aLengthMenu": [[13, 15, 20, -1], [13, 15, 20, "All"]]
		});
}
$(document).on("click",".hieraricalViewCls",function(){	
	var type = $(this).attr("attr_type");
	
	if(type == "show"){
		$(".hieraricalButtonShowCls").hide();
		$("#hieraricalShowHideDiv").html('');
		$("#hieraricalViewErr").html('');
		$("#hieraricalView").html('');
		getDepartmentWiseHierarchicalDetails();
	}else{
		$("#hieraricalShowHideDiv").html('');
		$("#hieraricalViewErr").html('');
		$("#hieraricalView").html('');
		$("#hieraricalView").removeAttr('style');
		$(".hieraricalButtonShowCls").show();
	}
});

$(document).on("click",".blockWiseDetails",function(){
	$("#dateRAngeSectionHideId").hide();
	var blockName=$(this).attr("main-block");
	if( blockName == "cMeoDB" || blockName == "meesevaSla" ){
	 //$("#dateRAngeSectionHideId").show();
	}
});
function getDateInRequiredFormat(date) {
	var dateArr = date.split("/");
	return [dateArr[2],dateArr[1],dateArr[0]].join("-");
}
function buildCMeoDBSectorWiseStatusDetais(result){
	var str='';
	if(result !=null && result.itDtlsVO !=null){
	str+='<div class="block_styles">';
		str+='<div class="row">';
			str+='<div class="col-sm-2">';	
				str+='<img src="Assests/images/IT_icon.png" />';
			str+='</div>';
			str+='<div class="col-sm-10">';
				str+='<h3 class="font_weight">IT Sector<br>Status Overview </h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-6">';	
				str+='<h4 class="font_weight m_top10">Total - '+result.itDtlsVO.total+'</h4>';
				str+='<div id="informationSectorGraphId" style="height:200px;"></div>';	
			str+='</div>';
			str+='<div class="col-sm-6">';	
				str+='<div class="statusColorCss">';
					str+='<div class="row">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Approved_icon.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#47E68D"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='Approved';
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.itDtlsVO.aprooved+' <small style="color:green;font-weight:bold;">'+result.itDtlsVO.approvedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		
				str+='<div class="statusColorCss" style="border-top:none;">';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Rejected_iocn.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#F55A5A"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='Rejected'; 
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.itDtlsVO.rejected+' <small style="color:green;font-weight:bold;">'+result.itDtlsVO.rejectedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		
				str+='<div class="statusColorCss" style="border-top:none;">';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Approved_icon.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#8D4653"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='ReApproved'; 
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.itDtlsVO.reAprooved+' <small style="color:green;font-weight:bold;">'+result.itDtlsVO.reApprovedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';

	str+='<div class="pendingstatusColorCss">';
		str+='<div class="row">';
			str+='<div class="col-sm-3">';	
				str+='<img src="Assests/images/Pending_icon 70x70.png" />';
			str+='</div>';
			str+='<div class="col-sm-9">';	
				str+='<h4 class="font_weight"><span class="approvedMainCss" style="background-color:#71A8EE;margin-right: 5px;"></span>Pending</h4>';
					str+='<h4 class="font_weight m_top10">'+result.itDtlsVO.totalPending+' <small style="color:green;font-weight:bold;">'+result.itDtlsVO.pendingPerc+'%</small></h4>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-6">';
							str+='<h5 class="">Within SLA</h5>';
							str+='<h4 class="font_weight">'+result.itDtlsVO.pendingWithinSLA+' <small style="color:green;font-weight:bold;">'+result.itDtlsVO.withinSLAPerc+'%</small></h4>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<h5 class="">Beyond SLA</h5>';
							str+='<h4 class="font_weight">'+result.itDtlsVO.pendingBeyondSLA+' <small style="color:green;font-weight:bold;">'+result.itDtlsVO.beyongSLAPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';	
		str+='</div>';	
	str+='</div>';
	$("#cmedobSectorWiseInformationId").html(str)
}else{
	$("#cmedobSectorWiseInformationId").html("No Data Available")
}
	var ApprovedCount=0;
	var RejectedCount=0;
	var ReApprovedCount=0;
	var PendingCount=0;
	
	if(result !=null){
		var pendingTotal = result.itDtlsVO.pendingWithinSLA+result.itDtlsVO.pendingBeyondSLA;
		
		ApprovedCount=parseFloat(result.itDtlsVO.approvedPerc);
		RejectedCount=parseFloat(result.itDtlsVO.rejectedPerc);
		ReApprovedCount=parseFloat(result.itDtlsVO.reApprovedPerc);
		PendingCount=parseFloat(result.itDtlsVO.pendingPerc);
	}
	
	var id = 'informationSectorGraphId';
	var type = {
		//width:350,
		type: 'pie',
		backgroundColor:'transparent',
		options3d: {
			enabled: true,
			alpha: 25
		}
	};
	var title = {
		text: ''
	};
	var tooltip = {
		useHTML: true,
		backgroundColor: '#FCFFC5', 
		formatter: function() {
			var cnt = this.point.count;
			return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+Highcharts.numberFormat(this.percentage,1)+"%</b>";
		}  
	}; 
	var plotOptions ={ 
		pie: {
			innerSize: 100,
			depth: 70,
			dataLabels:{
				useHTML: true,
				enabled: false,
				formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return "<b style='color:"+this.point.color+"'>"+this.point.name+"<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
						}
					} 
			},
			showInLegend: true
		},
	};
	var legend = {
		enabled: false,
		layout: 'vertical',
		align: 'left',
		verticalAlign: 'bottom',
		useHTML: true,
		
		labelFormatter: function() {
			return '<div><span style="color:'+this.color+'">'+this.name + '-'+Highcharts.numberFormat(this.percentage,1)+'%</span></div>';
		}
	};
	var data = [{
		name: '',
		data: [{
				name: 'Approved',
				y: ApprovedCount,
				color:"#47E68D"
			}, {
				name: 'Rejected',
				y: RejectedCount,
				color:"#F55A5A"
			}, {
				name: 'Re-Approved',
				y: ReApprovedCount,
				color:"#8D4653"
			}, {
				name: 'Pending',
				y: PendingCount,
				color:"#71A8EE"
			}]
	}];
	highcharts(id,type,data,plotOptions,title,tooltip,legend);
										
	
}
function buildCMeoDBSectorWiseElectronicSectorDetais(result){
	var str='';
	if(result !=null && result.electronicsDtlsVO !=null){
	str+='<div class="block_styles">';
		str+='<div class="row">';
			str+='<div class="col-sm-2">';	
				str+='<img src="Assests/images/Ec_icon.png" />';
			str+='</div>';
			str+='<div class="col-sm-10">';
				str+='<h3 class="font_weight">Electronic Sector<br>Status Overview</h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-6">';	
				str+='<h4 class="font_weight m_top10">Total - '+result.electronicsDtlsVO.total+'</h4>';
				str+='<div id="electronicSectorGraphId" style="height:200px;"></div>';	
			str+='</div>';
			str+='<div class="col-sm-6">';	
				str+='<div class="statusColorCss">';
					str+='<div class="row">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Approved_icon.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#47E68D"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='Approved';
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.electronicsDtlsVO.aprooved+' <small style="color:green;font-weight:bold;">'+result.electronicsDtlsVO.approvedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		
				str+='<div class="statusColorCss" style="border-top:none;">';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Rejected_iocn.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#F55A5A"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='Rejected'; 
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.electronicsDtlsVO.rejected+' <small style="color:green;font-weight:bold;">'+result.electronicsDtlsVO.rejectedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
		
				str+='<div class="statusColorCss" style="border-top:none;">';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-4">';	
							str+='<img src="Assests/images/Approved_icon.png" />';
						str+='</div>';
						str+='<div class="col-sm-8">';	
							str+='<h4 class="font_weight">';
								str+='<div class="row">';
									str+='<div class="col-sm-1" style="padding-right:0px;">';
										str+='<span class="approvedMainCss" style="background-color:#8D4653"></span>'; 
									str+='</div>';
									str+='<div class="col-sm-6">';
										str+='ReApproved'; 
									str+='</div>';
								str+='</div>';
							str+='</h4>';
							str+='<h4 class="font_weight m_top10">'+result.electronicsDtlsVO.reAprooved+' <small style="color:green;font-weight:bold;">'+result.electronicsDtlsVO.reApprovedPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';

	str+='<div class="pendingstatusColorCss">';
		str+='<div class="row">';
			str+='<div class="col-sm-3">';	
				str+='<img src="Assests/images/Pending_icon 70x70.png" />';
			str+='</div>';
			str+='<div class="col-sm-9">';	
				str+='<h4 class="font_weight"><span class="approvedMainCss" style="background-color:#71A8EE;margin-right: 5px;"></span>Pending</h4>';
					str+='<h4 class="font_weight m_top10">'+result.electronicsDtlsVO.totalPending+' <small style="color:green;font-weight:bold;">'+result.electronicsDtlsVO.pendingPerc+'%</small></h4>';
					str+='<div class="row m_top10">';
						str+='<div class="col-sm-6">';
							str+='<h5 class="">Within SLA</h5>';
							str+='<h4 class="font_weight">'+result.electronicsDtlsVO.pendingWithinSLA+' <small style="color:green;font-weight:bold;">'+result.electronicsDtlsVO.withinSLAPerc+'%</small></h4>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<h5 class="">Beyond SLA</h5>';
							str+='<h4 class="font_weight">'+result.electronicsDtlsVO.pendingBeyondSLA+' <small style="color:green;font-weight:bold;">'+result.electronicsDtlsVO.beyongSLAPerc+'%</small></h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';	
		str+='</div>';	
	str+='</div>';
	$("#cmedobSectorWiseElectronicSectorId").html(str)
}else{
	$("#cmedobSectorWiseElectronicSectorId").html("No Data Available")
}
	var ApprovedCount=0;
	var RejectedCount=0;
	var ReApprovedCount=0;
	var PendingCount=0;
	
	if(result !=null){
		var pendingTotal = result.electronicsDtlsVO.pendingWithinSLA+result.electronicsDtlsVO.pendingBeyondSLA;
		
		ApprovedCount=parseFloat(result.electronicsDtlsVO.approvedPerc);
		RejectedCount=parseFloat(result.electronicsDtlsVO.rejectedPerc);
		ReApprovedCount=parseFloat(result.electronicsDtlsVO.reApprovedPerc);
		PendingCount=parseFloat(result.electronicsDtlsVO.pendingPerc);
	}
	
	var id = 'electronicSectorGraphId';
	var type = {
		//width:350,
		type: 'pie',
		backgroundColor:'transparent',
		options3d: {
			enabled: true,
			alpha: 25
		}
	};
	var title = {
		text: ''
	};
	var tooltip = {
		useHTML: true,
		backgroundColor: '#FCFFC5', 
		formatter: function() {
			var cnt = this.point.count;
			return "<b style='color:"+this.point.color+"'>"+this.point.name+" - "+Highcharts.numberFormat(this.percentage,1)+"%</b>";
		}  
	}; 
	var plotOptions ={ 
		pie: {
			innerSize: 100,
			depth: 70,
			dataLabels:{
				useHTML: true,
				enabled: false,
				formatter: function() {
						if (this.y === 0) {
							return null;
						} else {
							return "<b style='color:"+this.point.color+"'>"+this.point.name+"<br/>("+Highcharts.numberFormat(this.percentage,1)+"%)</b>";
						}
					} 
			},
			showInLegend: true
		},
	};
	var legend = {
		enabled: false,
		layout: 'vertical',
		align: 'left',
		verticalAlign: 'bottom',
		useHTML: true,
		
		labelFormatter: function() {
			return '<div><span style="color:'+this.color+'">'+this.name + '-'+Highcharts.numberFormat(this.percentage,1)+'%</span></div>';
		}
	};
	var data = [{
		name: '',
		data: [{
				name: 'Approved',
				y: ApprovedCount,
				color:"#47E68D"
			}, {
				name: 'Rejected',
				y: RejectedCount,
				color:"#F55A5A"
			}, {
				name: 'Re-Approved',
				y: ReApprovedCount,
				color:"#8D4653"
			}, {
				name: 'Pending',
				y: PendingCount,
				color:"#71A8EE"
			}]
	}];
	highcharts(id,type,data,plotOptions,title,tooltip,legend);
	
}
$(document).on("change","#sectorSelId",function(){
var sectorVal=$("#sectorSelId").val();
getCMEDOBReportStatusWise(sectorVal);
});
$(document).on("click",".meesevaSlaKpiCls li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
		
	var blockType = $(this).attr("attr_type");
	if(blockType == "meesevaSla"){
		//getMeesevaSLAOverviewDtls("meesevaSlaKpi",5);
		getMeesevaSLACatWiseAbstarctDetails("meesevaSlaKpi",5,"change");
	}else{
		
		getMeesevaKPIOverViewDetails("change","meesevaSlaKpi",5);
		//getMeesavaKpiGraphBuild("meesevaSlaKpi",5);
	}
});

function getBioMetricDashboardOverViewDtls(){
	$("#bioMetricBlockId").html(spinner);
	var json = {
		deptCode:globalDeptCode
	}
	$.ajax({                
		type:'POST',    
		url: 'getBioMetricDashboardOverViewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			$("#bioMetricBlockId").html(result.totalCount+"/"+result.presentCount);
		}else{
			$("#bioMetricBlockId").html("0/0");
		}
		
	});	
}

function getMeesevaSLACatWiseAbstarctDetails(divId,blockId,type){
	if(type == "onload"){
		$("#meesevaHeadingId").html(spinner);
	}
	$("#"+divId+"Block"+blockId).html(spinner);
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLACatWiseAbstarctDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(type == "onload"){
			$("#meesevaHeadingId").html('');
		}
		
	    $("#"+divId+"Block"+blockId).html('');
		if(result !=null){
			var totalBeyondSlaCount = result.catgryABeyondSLACount+result.catgryBBeyondSLACount;
			if(type == "onload"){
				$("#meesevaHeadingId").html(totalBeyondSlaCount)
			}
			buildMeesevaSLACatWiseAbstarctDetails(result,divId,blockId);
		}else {
			$("#"+divId+"Block"+blockId).html('NO DATA AVAILABLE.');
		}
		getMeesevaSLADepartmentDetails(divId,blockId);
		getMeesevaSLAServiceWiseDetails(divId,blockId);
		
	});	
}
function buildMeesevaSLACatWiseAbstarctDetails(result,divId,blockId){
	var str='';
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-12">';
			str+='<div class="col-sm-6">';
				str+='<div class="white_block_ITC border_right">';
					str+='<h4 class="panel-title f_18 font_weight">CATEGORY - A</h4>';
						str+='<div class="row border_top m_top10">';
							str+='<div class="col-sm-4 border_right m_top10">';
								str+='<h4 class="font_weight f_18 m_top10">Total<br/>Departments</h4>';
								str+='<h4 class="font_weight m_top40">'+result.categoryACount+'</h4>';
							str+='</div>';
							str+='<div class="col-sm-4 border_right m_top10">';
								str+='<h4 class="font_weight f_18 m_top10">Total<br/>Services</h4>';
								str+='<h4 class="font_weight m_top40">'+result.catgryAServicesCount+'</h4>';
							str+='</div>';
							str+='<div class="col-sm-4 m_top10">';
								str+='<h4 class="font_weight f_18 m_top10">Total<br/>Transactions</h4>';
								str+='<h4 class="font_weight m_top40">'+result.catgryATransCount+'</h4>';
									//str+='<div class="border_top m_top5">';
										/* str+='<div class="row m_top5">';
											str+='<div class="col-sm-6">';
												str+='<h4 class="font_weight f_18">With In SLA</h4>';
												str+='<h4 class="font_weight m_top5">'+result.catgryAWithInSLACount+'</h4>';
											str+='</div>';
											str+='<div class="col-sm-6">';
												str+='<h4 class="font_weight f_18">Beyond SLA</h4>';
												str+='<h4 class="font_weight m_top5">'+result.catgryABeyondSLACount+'</h4>';
											str+='</div>';
										str+='</div>'; */
							//str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			str+='<div class="col-sm-6">';
				str+='<div class="white_block_ITC border_left">';
					str+='<h4 class="panel-title f_18 font_weight">CATEGORY - B</h4>';
						str+='<div class="row border_top m_top10">';
							str+='<div class="col-sm-3 border_right m_top10">';
								str+='<h4 class="font_weight f_18">Total<br/>Departments</h4>';
								str+='<h4 class="font_weight m_top30">'+result.categoryBCount+'</h4>';
							str+='</div>';
							str+='<div class="col-sm-2 border_right m_top10">';
								str+='<h4 class="font_weight f_18">Total<br/>Services</h4>';
								str+='<h4 class="font_weight m_top30">'+result.catgryBServicesCount+'</h4>';
							str+='</div>';
							str+='<div class="col-sm-7 m_top10">';
								str+='<h4 class="font_weight f_18 text-center">Total Transactions</h4>';
								str+='<h4 class="font_weight text-center">'+result.catgryBTransCount+'</h4>';
									str+='<div class="m_top5">';
									str+='<div class="border_top">';
										str+='<div class="col-sm-4">';
											str+='<h5 class="font_weight m_top5">Total SLA&apos;S</h5>';
											str+='<h5 class="font_weight m_top5">'+result.catBTotalSlaCunt+'</h5>';
										str+='</div>';
										str+='<div class="col-sm-4">';
											str+='<h5 class="font_weight  m_top5">With&nbsp;In&nbsp;SLA</h5>';
											str+='<h5 class="font_weight m_top5">'+result.catgryBWithInSLACount+'<br><span class="meesavaKpiPerc">('+result.withInSLAPerc+'&nbsp;%)</span></h4>';
										str+='</div>';
										str+='<div class="col-sm-4">';
											str+='<h5 class="font_weight   m_top5">Beyond&nbsp;SLA</h5>';
											str+='<h5 class="font_weight m_top5">'+result.catgryBBeyondSLACount+'<br><span class="meesavaKpiPerc">('+result.beyondSLAPerc+'&nbsp;%)</span></h5>';
										str+='</div>';
									str+='</div>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
		str+='</div>';
	str+='</div>';
	
	str+='<div class="m_top10">';
		str+='<div class="col-sm-12">';
			str+='<div id="meesevaSlaDepartmentWise'+divId+''+blockId+'"></div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="m_top10">';
		str+='<div class="col-sm-12">';
			str+='<div id="meesevaSlaServiceWise'+divId+''+blockId+'"></div>';
		str+='</div>';
	str+='</div>';
	
	$("#"+divId+"Block"+blockId).html("");					
	$("#"+divId+"Block"+blockId).html(str);					
}
function getMeesevaSLADepartmentDetails(divId,blockId){
	$("#meesevaSlaDepartmentWise"+divId+blockId).html(spinner);
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLADepartmentDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaSLADepartmentDetails(result,divId,blockId);
		}else{
			$("#meesevaSlaDepartmentWise"+divId+blockId).html("No Data Available");
		}
		
	});	
}
function buildMeesevaSLADepartmentDetails(result,divId,blockId){
	var str='';
	str+='<div class="panel-group" id="accordionDeptSLA" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-black">';
				str+='<div class="panel-heading" role="tab" id="headingDeptSLA">';
					str+='<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordionDeptSLA" href="#collapseDeptSLA" aria-expanded="true" aria-controls="collapseDeptSLA">';
						str+='<h4 class="panel-title">DEPARTMENTS WISE</h4>';
					str+='</a>';
				str+='</div>';
			str+='<div id="collapseDeptSLA" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingDeptSLA">';
				str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
						str+='<table class="table table_customP" id="departmentWiseMeesavaSla" style="width:100%">';
							str+='<thead>';
								str+='<tr>';
									str+='<th rowspan="2">Department</th>';
									str+='<th colspan="2" class="text-center" style="border-left:1px solid #ccc;">Category - A</th>';
									str+='<th colspan="7" class="text-center" style="border-left:1px solid #ccc;">Category - B</th>';
								str+='</tr>';
								str+='<tr>';
									str+='<th style="background-color: #FDF1F1 !important;">Services</th>';
									str+='<th>Transactions</th>';
									//str+='<th>With in SLA</th>';
									//str+='<th>Beyond SLA</th>';
									str+='<th style="border-left:1px solid #ccc;">Services</th>';
									str+='<th>Transactions</th>';
									str+='<th>SLA Transactions</th>';
									str+='<th>With in SLA</th>';
									str+='<th>%</th>';
									str+='<th>Beyond SLA</th>';
									str+='<th>%</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									var catBWithSLAValue = result[i].catgryBWithInSLACount;
									var catBBeyondSLAValue = result[i].catgryBBeyondSLACount;
									var catBTotalSLATrans = catBWithSLAValue+catBBeyondSLAValue;
									var catBWithSLAPerc = "0.00";
									var catBBeyondSLAPerc = "0.00";
									if(catBWithSLAValue > 0 && catBTotalSLATrans > 0)
										catBWithSLAPerc = ((catBWithSLAValue*100)/catBTotalSLATrans).toFixed(2);
									if(catBBeyondSLAValue > 0 && catBTotalSLATrans > 0)
										catBBeyondSLAPerc = ((catBBeyondSLAValue*100)/catBTotalSLATrans).toFixed(2);
									str+='<tr>';
										str+='<td style="border-right:1px solid #ccc">'+result[i].name+'</td>';
										if(result[i].catgryAServicesCount !=null && result[i].catgryAServicesCount>0 ){
											str+='<td>'+result[i].catgryAServicesCount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].catgryATransCount !=null && result[i].catgryATransCount>0){
											str+='<td>'+result[i].catgryATransCount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].catgryBServicesCount !=null && result[i].catgryBServicesCount>0){
											str+='<td>'+result[i].catgryBServicesCount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										
										/* if(result[i].catgryAWithInSLACount !=null && result[i].catgryAWithInSLACount>0){
											str+='<td>'+result[i].catgryAWithInSLACount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										
										if(result[i].catgryABeyondSLACount !=null && result[i].catgryABeyondSLACount>0){
											str+='<td>'+result[i].catgryABeyondSLACount+'</td>';
										}else{
											str+='<td>-</td>';
										} */
										if(result[i].catgryBTransCount !=null && result[i].catgryBTransCount>0){
											str+='<td>'+result[i].catgryBTransCount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(catBTotalSLATrans !=null && catBTotalSLATrans >0)
											str+='<td>'+catBTotalSLATrans+'</td>';
										else
											str+='<td>-</td>';
										
										if(result[i].catgryBWithInSLACount !=null && result[i].catgryBWithInSLACount>0){
											str+='<td>'+result[i].catgryBWithInSLACount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(catBWithSLAPerc !=null && catBWithSLAPerc >0)
											str+='<td>'+catBWithSLAPerc+'</td>';
										else
											str+='<td>-</td>';
										
										if(result[i].catgryBBeyondSLACount !=null && result[i].catgryBBeyondSLACount>0){
											str+='<td>'+result[i].catgryBBeyondSLACount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(catBBeyondSLAPerc !=null && catBBeyondSLAPerc >0)
											str+='<td>'+catBBeyondSLAPerc+'</td>';
										else
											str+='<td>-</td>';
										//str+='<td>'+catBBeyondSLAPerc+'</td>';
									str+='</tr>';
								}
							str+='<tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#meesevaSlaDepartmentWise"+divId+blockId).html(str);
	$("#departmentWiseMeesavaSla").dataTable();
}
function getMeesevaSLAServiceWiseDetails(divId,blockId){
	$("#meesevaSlaServiceWise"+divId+blockId).html(spinner);
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAServiceWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaSLAServiceWiseDetails(result,divId,blockId);
		}else{
			$("#meesevaSlaServiceWise"+divId+blockId).html("No Data Available");
		}
		
	});	
}
function buildMeesevaSLAServiceWiseDetails(result,divId,blockId){
	var str='';
	str+='<div class="panel-group" id="accordionServSLA" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-black">';
				str+='<div class="panel-heading" role="tab" id="headingServSLA">';
					str+='<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordionDeptSLA" href="#collapseServSLA" aria-expanded="true" aria-controls="collapseServSLA">';
						str+='<h4 class="panel-title">SERVICE WISE</h4>';
					str+='</a>';
				str+='</div>';
			str+='<div id="collapseServSLA" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingServSLA">';
				str+='<div class="panel-body">';
					str+='<div class="table-responsive">';
						str+='<table class="table table_customPS" id="serviceWiseMeesavaSla">';
							str+='<thead>';
								str+='<tr>';
									str+='<th>Department</th>';
									str+='<th>Service name</th>';
									str+='<th>Category</th>';
									str+='<th>Transactions</th>';
									str+='<th style="border-left:1px solid #ddd !important">Approved</th>';
									str+='<th>Revoked</th>';
									str+='<th>Rejected</th>';
									str+='<th style="border-right:1px solid #ddd !important">Rejected %</th>';
									str+='<th>InProgess</th>';
									str+='<th>With in SLA</th>';
									str+='<th>Beyond SLA</th>';
									str+='<th>Beyond SLA%</th>';
									
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var i in result){
									var BeyondPerc=0;
									var totalRejectedPerc=0;
									var total=result[i].totalWithInSlaCount+result[i].totalBeyondSlaCount;
									var totalRejectedCount=result[i].approved+result[i].revoke+result[i].rejected;
									BeyondPerc = (result[i].totalBeyondSlaCount/total*100).toFixed(2);
									totalRejectedPerc = (result[i].rejected/totalRejectedCount*100).toFixed(2);
									str+='<tr>';
										str+='<td>'+result[i].name+'</td>';
										str+='<td>'+result[i].serviceName+'</td>';
										str+='<td>'+result[i].cateoryA+'</td>';
										if(result[i].totalTransactionCount !=null && result[i].totalTransactionCount>0){
											str+='<td>'+result[i].totalTransactionCount+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].approved !=null && result[i].approved>0){
											str+='<td style="border-left:1px solid #ddd !important">'+result[i].approved+'</td>';
										}else{
											str+='<td style="border-left:1px solid #ddd !important">-</td>';
										}
										if(result[i].revoke !=null && result[i].revoke>0){
											str+='<td>'+result[i].revoke+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].rejected !=null && result[i].rejected>0){
											str+='<td>'+result[i].rejected+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(totalRejectedCount !=null && totalRejectedCount>0){
											str+='<td style="border-right:1px solid #ddd !important">'+totalRejectedPerc+'</td>';
										}else{
											str+='<td style="border-right:1px solid #ddd !important">-</td>';
										}
										
										str+='<td>'+total+'</td>';
										str+='<td>'+result[i].totalWithInSlaCount+'</td>';
										str+='<td>'+result[i].totalBeyondSlaCount+'</td>';
										if(total !=null && total>0){
											str+='<td>'+BeyondPerc+'</td>';
										}else{
											str+='<td>-</td>';
										}
										
										
									str+='</tr>';
								}
							str+='<tbody>';
						str+='</table>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#meesevaSlaServiceWise"+divId+blockId).html(str);
	$("#serviceWiseMeesavaSla").dataTable();
}

function getMeesevaKPIOverViewDetails(type,divId,blockId){
	$("#"+divId+"Block"+blockId).html(spinner);
	
	var json = {
		year : "2017"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIOverViewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			if(type == "onload"){
				if(result.totalMeesevaCentres !=null && result.totalMeesevaCentres>0){
					$("#meesevaKPIHeadingId").html(result.totalMeesevaCentres)
				}else{
					$("#meesevaKPIHeadingId").html("0")
				}
				
			}
			buildMeesevaKPIOverViewDetails(result,divId,blockId);
		}else{
			$("#"+divId+"Block"+blockId).html("No Data Available");
		}
		getMeesevaKPITargetAchieveDetails(divId,blockId);
		getMeesevaKPILocationWiseDetails(divId,blockId);
		//getMeesevaKPIOnlineServiceDetails();Old Call FR Online Services
		//getMeesevaKPIMobileSevicesDetails();Old Call FR Mobile App Services
		getMeesevaKPIOnlineServiceOverviewCount();
		getMeesevaKPIMobileAppServiceOverviewCount();
		getMeesevaKPIOnlineServiceYearWiseDetails();//New Call FR Online Services
		getMeesevaKPIMobileSevicesYearWiseDetails();//New Call FR Mobile App Services
		getMeesevaKPINewOnlineServiceOverviewCount();//New OnLine Services OverView
		getMeesevaKPINewOnlineServiceYearWiseDetails();//New OnLine Services Year Data
	});	
}
function buildMeesevaKPIOverViewDetails(result,divId,blockId){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12">';
			str+='<h3 class="font_weight">MEESEVA CENTERS</h3>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-2  m_top10">';
			str+='<div class="white_block_ITC">';
				str+='<h4 class=""><b>Total<br/> Meeseva Centers</b></h4>';
				str+='<h4 class="m_top10"><b>'+result.totalMeesevaCentres+'</b></h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2  m_top10">';
			str+='<div class="white_block_ITC">';
				str+='<h4 class=""><b>Established<br/> From 2014</b></h4>';
				str+='<h4 class="m_top10"><b>'+result.establishedFrom2014+'</b></h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2  m_top10">';
			str+='<div class="white_block_ITC">';
				str+='<h4 class=""><b>Established In<br/> Last Year</b></h4>';
				str+='<h4 class="m_top10"><b>'+result.establishedLastYear+'</b></h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2  m_top10">';
			str+='<div class="white_block_ITC">';
				str+='<h4 class=""><b>Established In<br/> This Year</b></h4>';
				str+='<h4 class="m_top10"><b>'+result.establishedThisYear+'</b></h4>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2  m_top10">';
			str+='<div class="white_block_ITC">';
				str+='<h4 class=""><b>Established In<br/> Last 1 Month</b></h4>';
				str+='<h4 class="m_top10"><b>'+result.establishedLastOneMonth+'</b></h4>';
			str+='</div>';
		str+='</div>';
		
	str+='</div>';
	str+='<div class="row">';
		str+='<div class="col-sm-4  m_top10">';
			str+='<div class="white_block_ITC">';
				str+='<div id="newOnlineSerOvrCuntCls"></div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4  m_top10">';
			str+='<div class="white_block_ITC">';
				str+='<div id="onlineSerOvrCuntCls"></div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4  m_top10">';
			str+='<div class="white_block_ITC">';
					str+='<div id="mobileAppSerOvrCuntCls"></div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-12">';
		
			str+='<div class="panel-group" id="accordionMonthKPI" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default panel-black">';
						str+='<div class="panel-heading" role="tab" id="headingMonthKPI">';
							str+='<a role="button" class="panelCollapseIcon"  data-toggle="collapse" data-parent="#accordionMonthKPI" href="#collapseMonthKPI" aria-expanded="true" aria-controls="collapseMonthKPI">';
								str+='<h4 class="panel-title">2017 MEESEVA CENTERS TARGET WISE ACHIEVED</h4>';
							str+='</a>';
						str+='</div>';
					str+='<div id="collapseMonthKPI" class="panel-collapse collapse in " role="tabpanel" aria-labelledby="headingMonthKPI">';
						str+='<div class="panel-body">';
							str+='<div id="monthWiseMeesavaKPIGraph" style="height:300px;"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row m_top10">';
		str+='<div class="col-sm-12">';
		
			str+='<div class="panel-group" id="accordionlocationKPI" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default panel-black">';
						str+='<div class="panel-heading" role="tab" id="headinglocationKPI">';
							str+='<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordionlocationKPI" href="#collapselocationKPI" aria-expanded="true" aria-controls="collapselocationKPI">';
								str+='<h4 class="panel-title">LOCATION WISE MEESEVA CENTERS</h4>';
							str+='</a>';
						str+='</div>';
					str+='<div id="collapselocationKPI" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headinglocationKPI">';
						str+='<div class="panel-body">';
							str+='<div id="locationWiseMeesavaCentres"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
			
			
		str+='</div>';
	str+='</div>';
	
	//New OnLine Services
	str+='<div class="row m_top10 OnlinServiceKPIOpen">';
		str+='<div class="col-sm-4">';
			str+='<div class="panel-group" id="accordionNewOnlinServiceKPI" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default panel-black">';
						str+='<div class="panel-heading" role="tab" id="headingNewOnlinServiceKPI">';
							str+='<a role="button" class="panelCollapseIcon NewOnlinServiceKPICollapsed collapsed"  data-toggle="collapse" data-parent="#accordionNewOnlinServiceKPI" href="#collapseNewOnlinServiceKPI" aria-expanded="true" aria-controls="collapseNewOnlinServiceKPI">';
								str+='<h4 class="panel-title">NEW ONLINE SERVICES</h4>';
							str+='</a>';
						str+='</div>';
					str+='<div id="collapseNewOnlinServiceKPI" class="panel-collapse collapse newOnlinServiceKPICollapsedIN" role="tabpanel" aria-labelledby="headingNewOnlinServiceKPI">';
						str+='<div class="panel-body borderColorCSSBlack table-responsive">';
							str+='<div id="newOnlineServicesDiv"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		//OnLine Services
	 // str+='<div class="row m_top10 OnlinServiceKPIOpen">';
		str+='<div class="col-sm-4">';
			str+='<div class="panel-group" id="accordionOnlinServiceKPI" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default panel-black">';
						str+='<div class="panel-heading" role="tab" id="headingOnlinServiceKPI">';
							str+='<a role="button" class="panelCollapseIcon OnlinServiceKPICollapsed collapsed"  data-toggle="collapse" data-parent="#accordionOnlinServiceKPI" href="#collapseOnlinServiceKPI" aria-expanded="true" aria-controls="collapseOnlinServiceKPI">';
								str+='<h4 class="panel-title">ONLINE SERVICES</h4>';
							str+='</a>';
						str+='</div>';
					str+='<div id="collapseOnlinServiceKPI" class="panel-collapse collapse OnlinServiceKPICollapsedIN" role="tabpanel" aria-labelledby="headingOnlinServiceKPI">';
						str+='<div class="panel-body borderColorCSSBlack table-responsive">';
							str+='<div id="onlineServicesDiv"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		//Mobile App Services
		str+='<div class="col-sm-4">';
			str+='<div class="panel-group" id="accordionMobileAppServiceKPI" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default panel-black">';
						str+='<div class="panel-heading" role="tab" id="headingMobileAppServiceKPI">';
							str+='<a role="button" class="panelCollapseIcon mobileKPICollapsed collapsed"  data-toggle="collapse" data-parent="#accordionMobileAppServiceKPI" href="#collapseMobileAppServiceKPI" aria-expanded="true" aria-controls="collapseMobileAppServiceKPI">';
								str+='<h4 class="panel-title">MOBILE APP SERVICES</h4>';
							str+='</a>';
						str+='</div>';
					str+='<div id="collapseMobileAppServiceKPI" class="panel-collapse collapse mobileKPICollapsedIN" role="tabpanel" aria-labelledby="headingMobileAppServiceKPI">';
						str+='<div class="panel-body borderColorCSSBlack table-responsive">';
							str+='<div id="mobileAppServicesDiv"></div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	
	$("#"+divId+"Block"+blockId).html(str);
}
function getMeesevaKPITargetAchieveDetails(divId,blockId){
	$("#monthWiseMeesavaKPIGraph").html(spinner)
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPITargetAchieveDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			var targetArr=[];
			var achievedArr=[];
			var monthArr=[];
			for(var i in result){
					targetArr.push(result[i].target)
					achievedArr.push(result[i].acheived)
					monthArr.push(result[i].name)
				}
					$("#monthWiseMeesavaKPIGraph").highcharts({
						chart: {
							type: 'column'
						},

						title: {
							text: ''
						},
						xAxis: {
							 min: 0,
							 gridLineWidth: 0,
							 minorGridLineWidth: 0,	
							 categories:monthArr
						},
						yAxis: {
							 min: 0,
							 gridLineWidth: 0,
							 minorGridLineWidth: 0,
							title: {
								text: ''
							},
						},
						legend: {
							enabled: true
						},
						tooltip: {
							formatter: function () {
								return '<b>' + this.x + '</b><br/>' +
									this.series.name + ': ' + this.y
							}
						},

						plotOptions: {
							column: {
								pointWidth: 30,
								gridLineWidth: 15,
							   // stacking: 'normal',
								  dataLabels: {
									enabled: true,
									color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'gray',
									formatter: function() {
										return (this.y);
									},
								},
							}
						},

						series: [{
								name: 'Target',
								data: targetArr,
								color:"#12A89D"

							},{
								name: 'Achieved',
								data: achievedArr,
								color:"#9C6BAF"

							}]
					});
			}else{
				$("#monthWiseMeesavaKPIGraph").html("No Data Available")
			}
		});	
	}

function getMeesevaKPILocationWiseDetails(divId,blockId){
	$("#locationWiseMeesavaCentres").html(spinner);
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPILocationWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaKPILocationWiseDetails(result);
		}else{
			$("#locationWiseMeesavaCentres").html("No Data Available")
		}
	});	
}
function buildMeesevaKPILocationWiseDetails(result){
	var str='';
	str+='<table class="table table_customPS" id="locationWiseMeesavaKPI">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Location</th>';
					str+='<th>Total Center</th>';
					str+='<th>%</th>';
					str+='<th>Est from 2014</th>';
					str+='<th>Last Year Est</th>';
					str+='<th>This Year Est</th>';
					str+='<th>This Month Est</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						str+='<td>'+result[i].totalMeesevaCentres+'</td>';
						str+='<td>'+result[i].percenatge+'</td>';
						str+='<td>'+result[i].establishedFrom2014+'</td>';
						str+='<td>'+result[i].establishedLastYear+'</td>';
						str+='<td>'+result[i].establishedThisYear+'</td>';
						str+='<td>'+result[i].establishedLastOneMonth+'</td>';
					str+='</tr>';
				}
			str+='<tbody>';
		str+='</table>';
		$("#locationWiseMeesavaCentres").html(str);
		$("#locationWiseMeesavaKPI").dataTable();
}
function getMeesevaKPIOnlineDeptWiseDetails(departmentId,year){
	$("#kpiOnlineDeptDivId").html(spinner);
	var json = {
		year : year,
		deptId : departmentId
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIOnlineDeptWiseCuntDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaKPIOnlineDeptWiseDetails(result);
		}else{
			$("#kpiOnlineDeptDivId").html("No Data Available")
		}
	});	
}

function getMeesevaKPIOnlineServiceYearWiseDetails(){
	$("#onlineServicesDiv").html(spinner);
	var json = {
		year : "2014"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIOnlineServiceYearWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaKPIOnlineServiceDetails(result,'online');
		}else{
			$("#onlineServicesDiv").html("No Data Available")
		}
	});	
}

function buildMeesevaKPIOnlineServiceDetails(result,type){
	var str='';
	str+='<h4><b>Summary</b></h4>';
	if(type != null && type == 'online'){
		str+='<div class="row">';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2014</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalOnlServ2014+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2015</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalOnlServ2015+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2016</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalOnlServ2016+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="text-align:center;">';
				str+='<h4><b>2017</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalOnlServ2017+'</h5>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	}else{
		str+='<div class="row">';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2014</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalNewOnlServ2014+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2015</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalNewOnlServ2015+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2016</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalNewOnlServ2016+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="text-align:center;">';
				str+='<h4><b>2017</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalNewOnlServ2017+'</h5>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	}
	
	
	str+='<div class="m_top20">';
			if(type != null && type == 'online'){
				str+='<table class="table" id="onlineServicesTableId">';
			}else{
				str+='<table class="table" id="newOnlineServicesTableId">';
			}
			str+='<thead>';
				str+='<tr>';
					str+='<th>Department</th>';
					str+='<th>2014</th>';
					str+='<th>2015</th>';
					str+='<th>2016</th>';
					str+='<th>2017</th>';
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						if(result[i].onLineServices2014 != null && result[i].onLineServices2014 != 0){
							str+='<td class="dptSrvCuntCls" attr_dept_id = "'+result[i].id+'" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'" attr_type="'+type+'" attr_year="2014"><u>'+result[i].onLineServices2014+'</u></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].onLineServices2015 != null && result[i].onLineServices2015 != 0){
							str+='<td class="dptSrvCuntCls" attr_dept_id = "'+result[i].id+'" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'"  attr_type="'+type+'" attr_year="2015"><u>'+result[i].onLineServices2015+'</u></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].onLineServices2016 != null && result[i].onLineServices2016 != 0){
							str+='<td class="dptSrvCuntCls" attr_dept_id = "'+result[i].id+'" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'"  attr_type="'+type+'" attr_year="2016"><u>'+result[i].onLineServices2016+'</u></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].onLineServices2017 != null && result[i].onLineServices2017 != 0){
							str+='<td class="dptSrvCuntCls" attr_dept_id = "'+result[i].id+'" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'"  attr_type="'+type+'" attr_year="2017"><u>'+result[i].onLineServices2017+'</u></td>';
						}else{
							str+='<td>0</td>';
						}
						
						//str+='<td class="dptSrvCuntCls" attr_dept_id = "'+result[i].id+'" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'"><u>'+result[i].onLineServicesCount+'</u></td>';
					str+='</tr>';
				}
			str+='<tbody>';
		str+='</table>';
		str+='</div>';
		if(type != null && type == 'online'){
			$("#onlineServicesDiv").html(str);
			$("#onlineServicesTableId").dataTable();
		}else{
			$("#newOnlineServicesDiv").html(str);
			$("#newOnlineServicesTableId").dataTable();
		}
}
$(document).on("click",".dptSrvCuntCls",function(){
	$("#kpiOnlineDeptModalId").modal("show");
	var deptName =  $(this).attr("attr_dept_name");
	var year =  $(this).attr("attr_year");
	var type =  $(this).attr("attr_type");
	$("#deptServiceHeadingId").html(deptName +" Department "+year+" Services");
	if(type != null && type == 'online'){
		getMeesevaKPIOnlineDeptWiseDetails($(this).attr("attr_dept_id"),year);
	}else{
		getMeesevaKPINewOnlineDeptWiseCuntDetails($(this).attr("attr_dept_id"),year);
	}
	
});
function buildMeesevaKPIOnlineDeptWiseDetails(result){
	var str='';
	str+='<table class="table" id="kpiOnlineDeptTableId">';
		str+='<thead>';
			str+='<tr>';
				str+='<th>Service Name</th>';
				str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].serviceName+'</td>';
				str+='</tr>';
			}
		str+='<tbody>';
	str+='</table>';
	$("#kpiOnlineDeptDivId").html(str);
	$("#kpiOnlineDeptTableId").dataTable();
}

function getMeesevaKPIMobileSevicesYearWiseDetails(){
	$("#mobileAppServicesDiv").html(spinner);
	var json = {
		year : "2014"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIMobileSevicesYearWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaKPIMobileSevicesDetails(result);
		}else{
			$("#mobileAppServicesDiv").html("No Data Available")
		}
	});	
}

function buildMeesevaKPIMobileSevicesDetails(result){
	var str='';
	str+='<h4><b>Summary</b></h4>';
	str+='<div class="row">';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2014</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalMblAppServ2014+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2015</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalMblAppServ2015+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="border-right:1px solid #000;text-align:center;">';
				str+='<h4><b>2016</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalMblAppServ2016+'</h5>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div style="text-align:center;">';
				str+='<h4><b>2017</b></h4>';
				str+='<h5 class="m_top10 onlinePanelBlock">'+globalMblAppServ2017+'</h5>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="m_top20">';
	str+='<table class="table" id="mobileAppServicesTableId">';
		str+='<thead>';
			str+='<tr>';
				str+='<th>Department</th>';
				str+='<th>2014</th>';
				str+='<th>2015</th>';
				str+='<th>2016</th>';
				str+='<th>2017</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].name+'</td>';
					if(result[i].mobileAppServices2014 != null && result[i].mobileAppServices2014 != 0){
							str+='<td class="dptMbleSrvCuntCls" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'" attr_year="2014"><u>'+result[i].mobileAppServices2014+'</u></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].mobileAppServices2015 != null && result[i].mobileAppServices2015 != 0){
							str+='<td class="dptMbleSrvCuntCls" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'" attr_year="2015"><u>'+result[i].mobileAppServices2015+'</u></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].mobileAppServices2016 != null && result[i].mobileAppServices2016 != 0){
							str+='<td class="dptMbleSrvCuntCls" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'" attr_year="2016"><u>'+result[i].mobileAppServices2016+'</u></td>';
						}else{
							str+='<td>0</td>';
						}
						if(result[i].mobileAppServices2017 != null && result[i].mobileAppServices2017 != 0){
							str+='<td class="dptMbleSrvCuntCls" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'" attr_year="2017"><u>'+result[i].mobileAppServices2017+'</u></td>';
						}else{
							str+='<td>0</td>';
						}

					//str+='<td class="dptMbleSrvCuntCls" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'" attr_year="2017"><u>'+result[i].currentYearAchievement+'</u></td>';
					//str+='<td class="dptMbleSrvCuntCls" style="cursor:pointer;" attr_dept_name = "'+result[i].name+'" attr_year="'+year+'"><u>'+result[i].previousYearAchievementCount+'</u></td>';
				str+='</tr>';
			}
		str+='<tbody>';
	str+='</table>';
	str+='</div>';
	$("#mobileAppServicesDiv").html(str);
	$("#mobileAppServicesTableId").dataTable();
}

$(document).on("click",".dptMbleSrvCuntCls",function(){
	$("#kpiMobileAppDeptModalId").modal("show");
	var deptName =  $(this).attr("attr_dept_name");
	var year =  $(this).attr("attr_year");
	$("#mobileAppHeadingId").html(deptName +" Department "+year+" Mobile App Services");
	getMeesevaKPIMobileDeptSevicesDetails(deptName,year);
});

function getMeesevaKPIMobileDeptSevicesDetails(department,year){
	$("#kpiMobileAppDeptDivId").html(spinner);
	var json = {
		year : year,
		groupName : department  //DepartmentName
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIMobileDeptSevicesDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaKPIMobileDeptSevicesDetails(result);
		}else{
			$("#kpiMobileAppDeptDivId").html("No Data Available")
		}
	});	
}

function buildMeesevaKPIMobileDeptSevicesDetails(result){
	var str='';
	str+='<table class="table" id="kpiMobileAppDeptTableId">';
		str+='<thead>';
			str+='<tr>';
				str+='<th>Name</th>';
				str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].serviceName+'</td>';
				str+='</tr>';
			}
		str+='<tbody>';
	str+='</table>';
	$("#kpiMobileAppDeptDivId").html(str);
	$("#kpiMobileAppDeptTableId").dataTable();
}

function getMeesevaKPIOnlineServiceOverviewCount(){
	$("#onlineSerOvrCuntCls").html(spinner);
	var json = {
		year : "2014"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIOnlineServiceOverviewCount',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildMeesevaKPIOnlineServiceOverviewCount(result);
		}else{
			$("#onlineSerOvrCuntCls").html("No Data Available")
		}
	});	
}

function buildMeesevaKPIOnlineServiceOverviewCount(result){
	globalOnlServ2014 = result.onLineServices2014;
	globalOnlServ2015 = result.onLineServices2015;
	globalOnlServ2016 = result.onLineServices2016;
	globalOnlServ2017 = result.onLineServices2017;
	var str='';
		str+='<h4 class="text-center"><b style="font-size: 20px !important;">Online Services - '+result.onLineServicesCount+'</b></h4>';
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2014</b></h4>';
					str+='<h5 class="m_top10 onlinePanelBlock">'+result.onLineServices2014+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2015</b></h4>';
					str+='<h5 class="m_top10 onlinePanelBlock">'+result.onLineServices2015+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2016</b></h4>';
					str+='<h5 class="m_top10 onlinePanelBlock">'+result.onLineServices2016+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="text-align:center;">';
					str+='<h4><b>2017</b></h4>';
					str+='<h5 class="m_top10 onlinePanelBlock">'+result.onLineServices2017+'</h5>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#onlineSerOvrCuntCls").html(str);
		
}
function getMeesevaKPIMobileAppServiceOverviewCount(){
	$("#mobileAppSerOvrCuntCls").html(spinner);
	var json = {
		year : "2014"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIMobileAppServiceOverviewCount',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildMeesevaKPIMobileAppServiceOverviewCount(result);
		}else{
			$("#mobileAppSerOvrCuntCls").html("No Data Available")
		}
	});	
}
var globalMblAppServ2014;
var globalMblAppServ2015;
var globalMblAppServ2016;
var globalMblAppServ2017;
function buildMeesevaKPIMobileAppServiceOverviewCount(result){
	globalMblAppServ2014 = result.mobileAppServices2014;
	globalMblAppServ2015 = result.mobileAppServices2015;
	globalMblAppServ2016 = result.mobileAppServices2016;
	globalMblAppServ2017 = result.mobileAppServices2017;
	var str='';
		str+='<h4 class="text-center"><b style="font-size: 20px !important;">Mobile App Services - '+result.totalMobileAppServices+'</b></h4>';
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2014</b></h4>';
					str+='<h5 class="m_top10 mobilePanelBlock">'+result.mobileAppServices2014+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2015</b></h4>';
					str+='<h5 class="m_top10 mobilePanelBlock">'+result.mobileAppServices2015+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2016</b></h4>';
					str+='<h5 class="m_top10 mobilePanelBlock">'+result.mobileAppServices2016+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="text-align:center;">';
					str+='<h4><b>2017</b></h4>';
					str+='<h5 class="m_top10 mobilePanelBlock">'+result.mobileAppServices2017+'</h5>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	$("#mobileAppSerOvrCuntCls").html(str);
}
$(document).on("click",".onlinePanelBlock",function(){
	if(!$(".OnlinServiceKPICollapsedIN").hasClass("in")){
		$(".OnlinServiceKPICollapsed").removeClass("collapsed")
		$(".OnlinServiceKPICollapsedIN").addClass("in")
		$('html,body').animate({
			scrollTop: $(".OnlinServiceKPIOpen").offset().top},
		'slow');
		$(".borderColorCSSBlack").addClass("border_black")
	}else{
		$('html,body').animate({
			scrollTop: $(".OnlinServiceKPIOpen").offset().top},
		'slow');
		$(".borderColorCSSBlack").addClass("border_black")
	}
});
$(document).on("click",".mobilePanelBlock",function(){	
	if(!$(".mobileKPICollapsedIN").hasClass("in")){
		$(".mobileKPICollapsed").removeClass("collapsed")
		$(".mobileKPICollapsedIN").addClass("in")
		$('html,body').animate({
			scrollTop: $(".OnlinServiceKPIOpen").offset().top},
		'slow');
		$(".borderColorCSSBlack").addClass("border_black")
	}else{
		$('html,body').animate({
			scrollTop: $(".OnlinServiceKPIOpen").offset().top},
		'slow');
		$(".borderColorCSSBlack").addClass("border_black")
	}
});
$(document).on("click","#addIcon",function(){
	$('#droppedBlockModalId').modal('show');

});

function getMeesevaKPINewOnlineServiceOverviewCount(){
	$("#newOnlineSerOvrCuntCls").html(spinner);
	var json = {
		year : "2014"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPINewOnlineServiceOverviewCount',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildMeesevaKPINewOnlineServiceOverviewCount(result);
		}else{
			$("#newOnlineSerOvrCuntCls").html("No Data Available")
		}
	});	
}
var globalNewOnlServ2014;
var globalNewOnlServ2015;
var globalNewOnlServ2016;
var globalNewOnlServ2017;
function buildMeesevaKPINewOnlineServiceOverviewCount(result){
	globalNewOnlServ2014 = result.onLineServices2014;
	globalNewOnlServ2015 = result.onLineServices2015;
	globalNewOnlServ2016 = result.onLineServices2016;
	globalNewOnlServ2017 = result.onLineServices2017;
	var str='';
		str+='<h4 class="text-center"><b style="font-size: 20px !important;">New Online Services - '+result.onLineServicesCount+'</b></h4>';
		str+='<div class="row m_top10">';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2014</b></h4>';
					str+='<h5 class="m_top10 newOnlinePanelBlock">'+result.onLineServices2014+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2015</b></h4>';
					str+='<h5 class="m_top10 newOnlinePanelBlock">'+result.onLineServices2015+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="border-right:1px solid #000;text-align:center;">';
					str+='<h4><b>2016</b></h4>';
					str+='<h5 class="m_top10 newOnlinePanelBlock">'+result.onLineServices2016+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-3 m_top10">';
				str+='<div style="text-align:center;">';
					str+='<h4><b>2017</b></h4>';
					str+='<h5 class="m_top10 newOnlinePanelBlock">'+result.onLineServices2017+'</h5>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		$("#newOnlineSerOvrCuntCls").html(str);
}
$(document).on("click",".newOnlinePanelBlock",function(){
if(!$(".newOnlinServiceKPICollapsedIN").hasClass("in")){
		$(".NewOnlinServiceKPICollapsed").removeClass("collapsed")
		$(".newOnlinServiceKPICollapsedIN").addClass("in")
		$('html,body').animate({
			scrollTop: $(".OnlinServiceKPIOpen").offset().top},
		'slow');
		$(".borderColorCSSBlack").addClass("border_black")
	}else{
		$('html,body').animate({
			scrollTop: $(".OnlinServiceKPIOpen").offset().top},
		'slow');
		$(".borderColorCSSBlack").addClass("border_black")
	}
});

function getMeesevaKPINewOnlineServiceYearWiseDetails(){
	$("#newOnlineServicesDiv").html(spinner);
	var json = {
		year : "2014"
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPINewOnlineServiceYearWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaKPIOnlineServiceDetails(result,'New Online');
		}else{
			$("#newOnlineServicesDiv").html("No Data Available")
		}
	});	
}
function getMeesevaKPINewOnlineDeptWiseCuntDetails(departmentId,year){
	$("#kpiOnlineDeptDivId").html(spinner);
	var json = {
		year : year,
		deptId : departmentId
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPINewOnlineDeptWiseCuntDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildMeesevaKPIOnlineDeptWiseDetails(result);
		}else{
			$("#kpiOnlineDeptDivId").html("No Data Available")
		}
	});	
}
$(document).on("click","#droppedForCheckId",function(){
	var divType = $(this).attr('attr_divType');
	if($(".checkBoxCls"+divType).prop("checked")==true)
		$('.dropedCls'+divType).show();
	else
		$('.dropedCls'+divType).hide();	
});
 