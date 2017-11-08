var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var orgChart = '';
//var departmentWiseArr=[{name:'Promotions',id:'1',color:'#0D3B54',image:'promotions',blockName:'promotions'},{name:'E Office',id:'2',color:'#1394B9',image:'eOffice',blockName:'eOffice'},{name:'Meeseva & SLA',id:'3',color:'#638D00',image:'meeseva',blockName:'meesevaSla'},{name:'Meeseva & KPI',id:'4',color:'#9B7A00',image:'meesevaHigh',blockName:'meesevaKpi'},{name:'eProcurement',id:'5',color:'#F06C1F',image:'eProcurement',blockName:'eProcurement'},{name:'CM eoDB',id:'6',color:'#C02D1D',image:'cMeoDB',blockName:'cMeoDB'}];

var departmentWiseArr = [{name:'Promotions',id:'1',color:'#0D3B54',image:'promotions',blockName:'promotions'},{name:'e Office',id:'2',color:'#1394B9',image:'eOffice',blockName:'eOffice'},{name:'Meeseva - SLA',id:'3',color:'#638D00',image:'meeseva',blockName:'meesevaSla'},{name:'AP Innovation Society',id:'7',color:'#F06C1F',image:'apInnovationSociety',blockName:'apInnovationSociety'},{name:'Meeseva & KPI',id:'4',color:'#9B7A00',image:'meesevaHigh',blockName:'meesevaKpi'},{name:'CM eoDB',id:'6',color:'#C02D1D',image:'cMeoDB',blockName:'cMeoDB'}];
var globalFromDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var globalToDate = moment().format("DD/MM/YYYY");
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
$('#itcDateRangePickerId').on('apply.daterangepicker', function(ev, picker) {
	globalFromDate = picker.startDate.format('DD/MM/YYYY');
	globalToDate = picker.endDate.format('DD/MM/YYYY'); 
    onloadCalls();
});
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
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
});
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
	getMeesevaSLAOverviewDtls("meesevaSla",5);
	
	//AP Innovation Society Ajax Call Start
	getAPInnovationSocietyOverview('onload','apInnovationSociety');
	getEOfcDepartWiseOverviewDetails('onload');
	getCMEDOBOverview("","","overview");
	
}
$(document).on("click",".cohortIdClick",function(){
	$("#modalId").modal('show');
	var id = $(this).attr("attr_id");
	getCohortDetailsByCohortId(id);
});
function departmentWiseOverView(){
	var block='';
	for(var i in departmentWiseArr){
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
							block+='<h3 id="meesevaHeadingId"></h3>';
							block+='<h6 class="m_top10">Beyond SLA</h6>';
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
	$("#departmentWiseDivId").html(block);
	
}
$(document).on('click','.blockWiseDetails',function(){
	$(".blockWiseDetails").removeClass("active");
	$(this).addClass("active");
	$("#campusOverviewBlock,#APISXLr8APOverview,#campaignsOverviewBlock").html(" ");
	var blockName = $(this).attr("attr_block_name");
	departmentBlockWiseDetails(blockName);	
});
function departmentBlockWiseDetails(divId)
{
	var levelWiseBlockArr='';
	if(divId == "promotions"){
		
		levelWiseBlockArr=[{name:'Promotions',id:'1'}]//,{name:'Electronics',id:'2'},{name:'FinTech',id:'3'}];
		
	}else if(divId == "eOffice"){
		
		levelWiseBlockArr=[{name:'E Office',id:'4'}];
		
	}else if(divId == "meesevaSla"){
		
		levelWiseBlockArr=[{name:'Meeseva-SLA',id:'5'}];
		
	}else if(divId == "meesevaKpi"){
		
		levelWiseBlockArr=[{name:'Meeseva & KPI',id:'6'}];
		
	}else if(divId == "eProcurement"){
		levelWiseBlockArr = [{name:'eProcurement',id:'7'}];
		
	}else if(divId == "cMeoDB"){
		
		levelWiseBlockArr = [{name:'CM eoDB',id:'8'}];
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
						collapse+='<h4 class="panel-title text-capital">'+levelWiseBlockArr[i].name+' overview</h4>';
							
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
												collapse+='<h4>INFORMATION TECHNOLOGY OVERVIEW</h4>';
												collapse+='<hr/>';
											collapse+='</div>';
											collapse+='<div class="col-sm-5">';
												collapse+='<ul class="list-inline switch-btn" id="promotionsBlockSwitch">';
													collapse+='<li class="active" attr_type="Total">ALL</li>';
													collapse+='<li attr_type="Electronics">Electronics</li>';
													collapse+='<li attr_type="Fintech">Fintech</li>';
													collapse+='<li attr_type="IT">IT</li>';
												collapse+='</ul>';
											collapse+='</div>';
										collapse+='</div>';
									
									collapse+='</div>';
									collapse+='<div class="col-sm-3" id="promotionsTotalBlockId"></div>';
									collapse+='<div class="col-sm-3" id="promotionsStageGreenBlockId"></div>';
									collapse+='<div class="col-sm-3" id="promotionsStageRedBlockId"></div>';
									collapse+='<div class="col-sm-3" id="promotionsStageDroppedBlockId"></div>';
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
														collapse+='<div id="'+levelWiseBlockArrPromotions[l].name+'OverviewBlockDivId"></div>';
													collapse+='</div>';
												collapse+='</div>';
											collapse+='</div>';
										}													
											
										collapse+='</div>';
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
							if(divId == 'cMeoDB'){
								collapse+='<div class="col-md-12 m_top10">';
							    	collapse+='<h4><b>CM eoDB Status Report</b></h4>';
								collapse+='</div>';
								collapse+='<div class="row m_top10">';
									collapse+='<div id="cmedobDivId"></div>';
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
	
	for(var i in levelWiseBlockArr){
		if(divId == "meesevaSla"){
			getMeesevaSLAOverviewDtls(divId,levelWiseBlockArr[i].id);
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
			getCMEDOBReportStatusWise();
		}
	}
	if(divId == 'promotions')
	{
		getITSectorWiseOverviewDetails();
		getITSectorCategoryWiseDetails("RED",'Total');
		getITSectorCategoryWiseDetails("GREEN",'Total');
		getITSectorCategoryWiseDetails("DROPPED",'Total');
		getITDistrictWiseDetails("IT","ALL",'body');
		getITDistrictWiseDetails("Electronics","ALL",'body');
		getITDistrictWiseDetails("Fintech","ALL",'body');
	}
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
	    fromDate:globalFromDate,
		toDate:globalToDate,
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
				str1+='<ul class="list-inline m_top10">';
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
				str1+='</ul>';
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
			if(typeOfBlock == 'Total')
			{
				getITSectorLeadCategoryWiseDetails(type)
			}
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
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#058E46;color:#fff">Civil Works commencement and beyond</span>';
					str+='</p>';
				}else if(type == "RED")
				{
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#F75C5D;color:#fff">Before Civil Works commencement</span>';
					str+='</p>';
				}else if(type == "DROPPED")
				{
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#91CCC7;color:#fff">Dropped</span>';
					str+='</p>';
				}
					str+='<div class="white_block_ITC m_top20" style="background-color:#F1F1F1">';
						if(type == "GREEN")
						{
							str+='<p>';
								str+='<span style="padding:5px 10px;background-color:#058E46;color:#fff">Overall</span>';
							str+='</p>';
						}else if(type == "RED")
						{
							str+='<p>';
								str+='<span style="padding:5px 10px;background-color:#F75C5D;color:#fff">Overall</span>';
							str+='</p>';
						}else if(type == "DROPPED")
						{
							str+='<p>';
								str+='<span style="padding:5px 10px;background-color:#91CCC7;color:#fff">Overall</span>';
							str+='</p>';
						}
						str+='<div class="row m_top20">';
							str+='<div class="col-sm-4">';
								str+='<h4 class="overview-click" style="cursor:pointer;" attr_type="'+type+'">'+result[i].noProjects+'</h4>';
								str+='<p><small>INDUSTRIES</small></p>';
							str+='</div>';
							str+='<div class="col-sm-4">';
								str+='<h4 style="font-size:16px;">'+result[i].investment+'Cr</h4>';
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
			}
		}
	}
}
function getITSectorLeadCategoryWiseDetails(type){
	var json = {
		leadName:"0",
		category:type
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
		}
	});
	function buildData(result,type)
	{
		var str='';
		var selectedBlockType = $("#promotionsBlockSwitch li.active").attr("attr_type");
		for(var i in result)
		{
			str+='<div class="white_block_ITC m_top20" style="background-color:#F1F1F1">';
				if(type == "GREEN")
				{
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#058E46;color:#fff">'+result[i].category+'</span>';
					str+='</p>';
				}else if(type == "RED")
				{
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#F75C5D;color:#fff">'+result[i].category+'</span>';
					str+='</p>';
				}else if(type == "DROPPED")
				{
					str+='<p>';
						str+='<span style="padding:5px 10px;background-color:#91CCC7;color:#fff">'+result[i].category+'</span>';
					str+='</p>';
				}
				str+='<div class="row m_top20">';
					str+='<div class="col-sm-4">';
						str+='<h4 class="overview-click" attr_category="'+result[i].category+'" style="cursor:pointer;" attr_type="'+type+'">'+result[i].categoryCount+'</h4>';
						str+='<p><small>INDUSTRIES</small></p>';
					str+='</div>';
					str+='<div class="col-sm-4">';
						str+='<h4 style="font-size:16px;">'+result[i].investment+'Cr</h4>';
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
			$("#promotionsStageGreenBlockId").append(str);
		}else if(type == "RED")
		{
			$("#promotionsStageRedBlockId").append(str);
		}else if(type == "DROPPED")
		{
			$("#promotionsStageDroppedBlockId").append(str);
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
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered" id="'+type+'DataTable'+divType+'">';
				str+='<thead>';
					str+='<tr>';
						str+='<th rowspan="2">District</th>';
						str+='<th colspan="3" class="text-center">Total</th>';
						str+='<th colspan="3" class="text-center">Green</th>';
						str+='<th colspan="3" class="text-center">Red</th>';
						str+='<th colspan="3" class="text-center">Dropped</th>';
					str+='</tr>';
					str+='<tr>';
						str+='<th>Industries</th>';
						str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
						str+='<th>Committed Employment</th>';
						str+='<th>Industries</th>';
						str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
						str+='<th>Committed Employment</th>';
						str+='<th>Industries</th>';
						str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
						str+='<th>Committed Employment</th>';
						str+='<th>Industries</th>';
						str+='<th>Committed Investment(<i class="fa fa-inr"></i> in Cr.)</th>';
						str+='<th>Committed Employment</th>';
					str+='</tr>';
				str+='</thead>';
				for(var i in result)
				{
					str+='<tr>';
						str+='<td>'+result[i].district+'</td>';
						if(result[i].district != null && result[i].district == 'ZTotal'){
							str+='<td>'+result[i].noProjects+'</td>';
						}else{
							str+='<td class="sectorWiseCuntCls" attr_block_name="'+type+'" attr_category="'+category+'" attr_district="'+result[i].district+'" style="cursor:pointer;">'+result[i].noProjects+'</td>';
						}
						
						str+='<td>'+result[i].investment+'</td>';
						str+='<td>'+result[i].employment+'</td>';
						for(var j in result[i].subList)
						{
							if(result[i].subList[j].noProjects == 'undefined' || result[i].subList[j].noProjects === undefined)
							{
								str+='<td>-</td>';
							}else{
								str+='<td>'+result[i].subList[j].noProjects+'</td>';
							}
							if(result[i].subList[j].investment == 'undefined' || result[i].subList[j].investment === undefined)
							{
								str+='<td>-</td>';
							}else{
								str+='<td>'+result[i].subList[j].investment+'</td>';
							}
							if(result[i].subList[j].employment == 'undefined' || result[i].subList[j].employment === undefined)
							{
								str+='<td>-</td>';
							}else{
								str+='<td>'+result[i].subList[j].employment+'</td>';
							}
						}
					str+='</tr>';
				}
				
			str+='</table>';
		str+='</div>';
		if(divType == 'body')
		{
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
						str+='<div class="panel-footer"><h3>'+result.mentors+'</h3></div>';
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
			if(result[i].jobsCreated != null && result[i].jobsCreated != '-')
			{
				totalJobsCreated = totalJobsCreated + parseInt(result[i].jobsCreated);
			}
		}
		overview+='<div class="white_block_ITC" style="border:1px solid #F87071;border-radius:5px;">';
			overview+='<h4 class="m_top10"><span  style="padding:5px 10px;background-color:#F87071">APIS-XLr8AP</span></h4>';
			overview+='<div style="padding:10px;">';
				overview+='<div class="row">';
					overview+='<div class="col-sm-12">';
						overview+='<h4>Batchs</h4>';
						overview+='<h3>'+totalBatches+'</h3>';
					overview+='</div>';
					overview+='<div class="col-sm-6">';
						overview+='<h4>Companies Registered</h4>';
						overview+='<h3>'+totalCompanies+'</h3>';
					overview+='</div>';
					overview+='<div class="col-sm-6">';
						overview+='<h4>Job Created</h4>';
						overview+='<h3>'+totalJobsCreated+'</h3>';
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
					overview+='<div class="col-sm-12 m_top25" style="margin-bottom:30px;">';
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
					overview+='<div class="col-sm-12 m_top25" style="margin-bottom:30px;">';
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
function getEOfcDepartWiseOverviewDetails(type){
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
			str+='<th>District</th>';
			str+='<th>Sector</th>';
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
				str+='<td>'+result[i].districtName+'</td>';
				str+='<td>'+result[i].itSector+'</td>';
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
		$("#"+divId+"Block"+blockId).html(spinner);
	}
	$.ajax({                
		type:'GET',    
		url: 'getCMEDOBOverview',
		dataType: 'json',
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			if(type =="overview"){
				buildgetCMEDOBOverview(result);
			}else{
				buildgetCMEDOBDetailed(result,divId,blockId,type);
			}
		}else{
			if(type =="overview"){
				$("#cMeoDBTotalId").html("0 /")
				$("#cMeoDBApprovedId").html("0")
			}else{
				$("#"+divId+"Block"+blockId).html("No Data Available");
			}
			
		}
	});		
}
function buildgetCMEDOBOverview(result){
	
	if(result !=null){
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
	
	var CMEDOBODetailedArr=[{name:'Total',color:'#D28000',image:'Group 2873.png'},{name:'Approved',color:'#007500',image:'Path -1.png'},{name:'Rejected',color:'#FF003C',image:'Group 2874.png'},{name:'Re-Approve',color:'#007500',image:'Path -1.png'},{name:'Pending',color:'#000',image:'Group 2875.png'}];
	var str='';
	str+='<div class="row">';
	for(var i in CMEDOBODetailedArr){
		if(CMEDOBODetailedArr[i].name == "Total" || CMEDOBODetailedArr[i].name == "Approved" || CMEDOBODetailedArr[i].name == "Rejected"  || CMEDOBODetailedArr[i].name == "Re-Approve"){
			str+='<div class="col-sm-2">';
		}else if(CMEDOBODetailedArr[i].name == "Pending"){
			str+='<div class="col-sm-3">';
		}
				str+='<div class="white_block pad_0">';
					str+='<div class="media pad_10">';
						str+='<div class="media-body">';
							str+='<h4>'+CMEDOBODetailedArr[i].name+'</h4>';
							if(CMEDOBODetailedArr[i].name == "Pending"){
									str+='<div class="col-sm-6 m_top10" style="padding:0px 5px">';
										str+='<h5 style="font-weight:600">Within SLA</h5>';
										str+='<h5 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">'+result.overviewDtls.pendingWithinSLA+'</h5>';
									str+='</div>';
									str+='<div class="col-sm-6 m_top10" style="padding:0px 1px">';
										str+='<h5 style="font-weight:600">Beyond SLA</h5>';
										str+='<h5 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">'+result.overviewDtls.pendingBeyondSLA+'</h5>';
									str+='</div>';
							}else{
								if(CMEDOBODetailedArr[i].name == "Total"){
									if(result.overviewDtls.total !=null && result.overviewDtls.total>0){
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">'+result.overviewDtls.total+'</h4>';
									}else{
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">0</h4>';
									}
									
								}else if(CMEDOBODetailedArr[i].name == "Approved"){
									if(result.overviewDtls.aprooved !=null && result.overviewDtls.aprooved>0){
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">'+result.overviewDtls.aprooved+'</h4>';
									}else{
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">0</h4>';
									}
								}else if(CMEDOBODetailedArr[i].name == "Rejected"){
									if(result.overviewDtls.rejected !=null && result.overviewDtls.rejected>0){
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">'+result.overviewDtls.rejected+'</h4>';
									}else{
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">0</h4>';
									}
								}else if(CMEDOBODetailedArr[i].name == "Re-Approve"){
									if(result.overviewDtls.reAprooved !=null && result.overviewDtls.reAprooved>0){
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">'+result.overviewDtls.reAprooved+'</h4>';
									}else{
										str+='<h4 style="color:'+CMEDOBODetailedArr[i].color+'" class="m_top10">0</h4>';
									}
								}
								
							}
							str+='</div>';
							str+='<div class="media-right">';
								str+='<img src="Assests/icons/ITC/'+CMEDOBODetailedArr[i].image+'" class="pull-right">';
							str+='</div>';
						
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
			
		
	}
	str+='</div>';
	
	str+='<div class="row m_top20">';
		str+='<div class="col-sm-4">';
			str+='<div class="white_block pad_10">';
				str+='<div class="media">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/ITC/Path -2.png">';
					str+='</div>';
					str+='<div class="media-body">';
						str+='<p>';
							str+='<h4 class="font_weight">High Approval Department</h4>';
							str+='<p>'+result.highApprovalDepartmentName+'</p>';
							str+='<h3 style="color:#007500;" class="font_weight">'+result.highApprovalDepartmentCount+'</h3>';
						str+='</p>';
						str+='<p class="m_top10">';
							str+='<h4 class="font_weight">Low Approval Department</h4>';
							str+='<p>'+result.lowApprovalDepartmentName+'</p>';
							str+='<h3 style="color:#007500;" class="font_weight">'+result.lowApprovalDepartmentCount+'</h3>';
						str+='</p>';
				str+='</div>';
				str+='</div>';
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-4">';
			str+='<div class="white_block pad_10">';
				str+='<div class="media">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/ITC/Group 2888.png">';
					str+='</div>';
					str+='<div class="media-body">';
						str+='<p>';
							str+='<h4 class="font_weight">High Rejected Department</h4>';
							str+='<p>'+result.highRejectedDepartmentName+'</p>';
							str+='<h3 style="color:#FF003C;" class="font_weight">'+result.highRejectedDepartmentCount+'</h3>';
					str+='</p>';
					str+='<p class="m_top10">';
						str+='<h4 class="font_weight">Low Rejected Department</h4>';
						str+='<p>'+result.lowRejectedDepartmentName+'</p>';
						str+='<h3 style="color:#FF003C;" class="font_weight">'+result.lowRejectedDepartmentCount+'</h3>';
					str+='</p>';
					str+='</div>';
				str+='</div>';
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-4">';
			str+='<div class="white_block pad_10">';
				str+='<div class="media">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/ITC/Group 2882.png">';
					str+='</div>';
					str+='<div class="media-body">';
						str+='<p>';
							str+='<h4 class="font_weight">High Pending Department</h4>';
							str+='<p>'+result.highPendingDepartmentName+'</p>';
							str+='<h3 style="color:#000;" class="font_weight">'+result.highPendingDepartmentCount+'</h3>';
					str+='</p>';
					str+='<p class="m_top10">';
						str+='<h4 class="font_weight">Low Pending Department</h4>';
						str+='<p>'+result.lowPendingDepartmentName+'</p>';
						str+='<h3 style="color:#000;" class="font_weight">'+result.lowPendingDepartmentCount+'</h3>';
					str+='</p>';
				str+='</div>';
				str+='</div>';
				
			str+='</div>';
		str+='</div>';
	str+='</div>';
			
	$("#"+divId+"Block"+blockId).html(str);
}

function getCMEDOBReportStatusWise(){

	var json = {
		"deptId":"0"
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
			buildCMEDOBReportStatusWise(result)	
		} else {
			$("#cmedobDivId").html("NO DATA AVAILABLE.");
		}
		
	});
		
}
function buildCMEDOBReportStatusWise(result){
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
							str+='<th>Pending Within SLA </th>';
							str+='<th>Pending Beyond SLA</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					for(var i in result){
					str+='<tr>';
						for(var j in result[i].subList){
								str+='<td >'+result[i].dashboardName+'</td>';
								str+='<td id="'+result[i].subList[j].clearenceId+'">'+result[i].subList[j].clearenceName+'</td>';
								str+='<td>'+result[i].subList[j].totalApplications+'</td>';
								str+='<td>'+result[i].subList[j].totalApproved+'</td>';
								str+='<td>'+result[i].subList[j].totalRejected+'</td>';
								str+='<td>'+result[i].subList[j].totalReApproved+'</td>';
								str+='<td>'+result[i].subList[j].pendingWithInSLA+'</td>';
								str+='<td>'+result[i].subList[j].pendingBeyondSLA+'</td>';
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

function buildEOfcDepartWiseOverviewDetails(result){
	var str = '';
	str+='<div class="col-sm-12">';	
		str+='<button type="button" class="btn btn-default btn-sm hieraricalViewCls pull-right hieraricalButtonShowCls" attr_type="show" >Show Graph View</button>';
	str+='</div>';
	for(var i in result){
		if(result[i].departmentName == "ITE & C" || result[i].departmentName == "INFORMATION TECHNOLOGY ELECTRONICS AND COMMUNICATION DEPARTMENT"){
			str+='<div class="col-sm-12 m_top20">';	
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered">';
						
							str+='<tr>';
								if(result[i].departmentName == "ITE & C"){
									str+='<th rowspan="2" style="vertical-align: middle; text-align: center;" >ITE & C</th>';
								}else{
									str+='<th rowspan="2" style="vertical-align: middle; text-align: center;cursor:pointer;"><a class="departmentDetailsCls" attr_department_id="'+result[i].departmentId+'" attr_department_name="'+result[i].departmentName+'">IT - SECRETARIAT</a></th>';
								}
								str+='<th style="background-color:#B2DFDB">Total</th>';
								str+='<th style="background-color:#FBACAC">Total Pendency</th>';
								str+='<th style="background-color:#FBACAC">%</th>';
								str+='<th style="background-color:#FDCECE">0 - 7 days</th>';
								str+='<th style="background-color:#FDCECE">8 - 15 days</th>';
								str+='<th style="background-color:#FDCECE">16 - 30 days</th>';
								str+='<th style="background-color:#FDCECE">31 - 60 days</th>';
								str+='<th style="background-color:#FDCECE"> > 60 days</th>';
							str+='</tr>';
							str+='<tr>';
								str+='<td>'+result[i].created+'</td>';
								str+='<td>'+result[i].totalCount+'</td>';
								str+='<td>'+result[i].percentage+'</td>';
								str+='<td>'+result[i].zeroToSeven+'</td>';
								str+='<td >'+result[i].eightToFifteen+'</td>';
								str+='<td>'+result[i].sixteenToThirty+'</td>';
								str+='<td >'+result[i].thirtyoneToSixty+'</td>';
								str+='<td>'+result[i].aboveSixty+'</td>';
						str+='</tr>';
						
					str+='</table>';
				str+='</div>';
			str+='</div>';
		}
	}
	
	str+='<div class="col-sm-12 m_top20">';	
		str+='<div class="table-responsive">';
			str+='<table class="table table-bordered">';
					str+='<tr>';
						str+='<th>HODS</th>';
						str+='<th style="background-color:#B2DFDB">Total</th>';
						str+='<th style="background-color:#FBACAC">Total Pendency</th>';
						str+='<th style="background-color:#FBACAC">%</th>';
						str+='<th style="background-color:#FDCECE">0 - 7 days</th>';
						str+='<th style="background-color:#FDCECE">8 - 15 days</th>';
						str+='<th style="background-color:#FDCECE">16 - 30 days</th>';
						str+='<th style="background-color:#FDCECE">31 - 60 days</th>';
						str+='<th style="background-color:#FDCECE"> > 60 days</th>';
					str+='</tr>';
					for(var i in result){
						if(result[i].departmentName != "ITE & C" && result[i].departmentName != "INFORMATION TECHNOLOGY ELECTRONICS AND COMMUNICATION DEPARTMENT"){
							str+='<tr>';
								str+='<td style="cursor:pointer;"><a class="departmentDetailsCls" attr_department_id="'+result[i].departmentId+'" attr_department_name="'+result[i].departmentName+'">'+result[i].departmentName+'</a></td>';
								str+='<td>'+result[i].created+'</td>';
								str+='<td>'+result[i].totalCount+'</td>';
								str+='<td>'+result[i].percentage+'</td>';
								str+='<td>'+result[i].zeroToSeven+'</td>';
								str+='<td >'+result[i].eightToFifteen+'</td>';
								str+='<td>'+result[i].sixteenToThirty+'</td>';
								str+='<td >'+result[i].thirtyoneToSixty+'</td>';
								str+='<td>'+result[i].aboveSixty+'</td>';
							str+='</tr>';
						}
						
					}
			str+='</table>';
		str+='</div>';
	str+='</div>';
$("#eOfficeDeparmentsOverViewBlock").html(str);
}
$(document).on("click",".departmentDetailsCls",function(){	
	var departmentId =  $(this).attr("attr_department_id")
	var departmentName =  $(this).attr("attr_department_name")
	$("#departmentModalId").modal("show");
	$("#headingTitle").html("<b>"+departmentName+ "  DETAILS</b>")
	getEofficeDesginationDetailsByDepartment(departmentId,departmentName);
});
function getEofficeDesginationDetailsByDepartment(departmentId,departmentName){
	$("#departmentDetailsDivId").html(spinner);
	var json = {
		departmentId:departmentId,		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEofficeDesginationDetailsByDepartment',
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
				str+='<table class="table table-bordered" id="dataTableDepartmentId">';
						str+='<thead>';
						str+='<tr>';
							str+='<th class="text-center">POST NAME</th>';
							str+='<th style="background-color:#B2DFDB">Total</th>';
							str+='<th style="background-color:#FBACAC">Total Pendency</th>';
							str+='<th style="background-color:#FBACAC">%</th>';
							str+='<th style="background-color:#FDCECE">0 - 7 days</th>';
							str+='<th style="background-color:#FDCECE">8 - 15 days</th>';
							str+='<th style="background-color:#FDCECE">16 - 30 days</th>';
							str+='<th style="background-color:#FDCECE">31 - 60 days</th>';
							str+='<th style="background-color:#FDCECE"> > 60 days</th>';
						str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td>'+result[i].designation+' ('+result[i].ownerName+')</td>';
								str+='<td>'+result[i].created+'</td>';
								str+='<td>'+result[i].totalCount+'</td>';
								str+='<td>'+result[i].percentage+'</td>';
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
