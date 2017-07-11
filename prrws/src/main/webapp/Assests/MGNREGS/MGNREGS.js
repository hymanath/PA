var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = '2017-04-01'//moment().startOf('year').format("YYYY-MM")+'-1';
var glEndDate = moment().format("YYYY-MM")+'-30';
var glStartDateForWebservice = moment().format("DD/MM/YYYY");
var glEndDateForWebservice = moment().format("DD/MM/YYYY");
var globalDivName;
var $windowWidth = $(window).width();
onLoadCalls();
function onLoadCalls()
{
	$("#selectedName").tooltip();
	$("#getWebserviceDetailsId").tooltip();
	$(document).keydown(function(event){
		if(event.keyCode==123){
			alert("Hoo no! don't try to expose me");
			return false;
		}
		else if(event.ctrlKey && event.shiftKey && event.keyCode==73){        
			alert("Hoo no! don't try to expose me");
			return false;  //Prevent from ctrl+shift+i
		}
	});
	var width = $(window).width()
	if(width > 767)
	{
		header = $('header section'),
		$(window).scroll(function(){
			var windowScrollTop = $(window).scrollTop();

			if (windowScrollTop>50) {
				header.addClass("header-fixed");
			} else {
				header.removeClass("header-fixed");
			}
		});
	}
	$(document).on('click','[overview-block]', function(){
		$("[overview-state],[overview-district]").removeClass("active");
		var projectDivId = $(this).attr("overview-block");
		var levelId = $(this).attr("attr_levelId");
		var locationId = $(this).attr("attr_locationId");
		$("[overview-state='"+projectDivId+"'],[overview-district='"+projectDivId+"']").addClass("active");
		globalDivName = projectDivId;
		overviewData(projectDivId,levelId,locationId);
		projectData(projectDivId,levelId,locationId);
		$('html,body').animate({
			scrollTop: $("#projectOverviewBlock").offset().top},
        'slow');
	});
	$(document).on("click",".rightNavigationMenuRes",function(){
		if($(this).hasClass("active")){
			$(this).removeClass("active");
			$(".rightNavigationMenu ul,.backgroundBlock").hide();
		}
		else{
			$(this).addClass("active");
			$(".rightNavigationMenu ul,.backgroundBlock").show();
		}
	});
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	$(".chosenSelect").chosen({width:'100%'})
	
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
	$('#dateRangePickerMGNT').on('dp.change', function(e){ 
		glEndDate = e.date.format("YYYY-MM")+"-31";
		var levelId = $("#selectedName").attr("attr_levelId");
		var locId = $("#selectedName").attr("attr_id");
		var locType = '';
		if(levelId == 2)
		{
			locType = 'state'
		}else if(levelId == 3)
		{
			locType = 'district'
		}
		var blockName = '';
		$(".panel-block-white").each(function(){
			if($(this).hasClass("active"))
			{
				blockName = $(this).attr("overview-block");
			}
		});
		
		$("#projectOverviewBlock,#projectData").html('');
		buildNREGSProjectsOverview(overViewArr,'');
		for(var i in overViewArr)
		{
			$("[overview-block='"+overViewArr[i]+"']").append(spinner);
			if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
				getNREGSProjectsAbstractNew(overViewArr[i],locType,locId,'',levelId);
			else
				getNREGSAbstractDataByType(overViewArr[i],locType,locId,'',levelId);
		}
	});
	$('#dateRangePickerMGNF').on('dp.change', function(e){ 
		glStartDate = e.date.format("YYYY-MM")+"-01";
		var levelId = $("#selectedName").attr("attr_levelId");
		var locId = $("#selectedName").attr("attr_id");
		var locType = '';
		if(levelId == 2)
		{
			locType = 'state'
		}else if(levelId == 3)
		{
			locType = 'district'
		}
		var blockName = '';
		$(".panel-block-white").each(function(){
			if($(this).hasClass("active"))
			{
				blockName = $(this).attr("overview-block");
			}
		});
		$("#projectOverviewBlock,#projectData").html('');
		
		buildNREGSProjectsOverview(overViewArr,'');
		for(var i in overViewArr)
		{
			$("[overview-block='"+overViewArr[i]+"']").append(spinner);
			if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
				getNREGSProjectsAbstractNew(overViewArr[i],locType,locId,'',levelId);
			else
				getNREGSAbstractDataByType(overViewArr[i],locType,locId,'',levelId);
		}
		//getNREGSProjectsOverview(blockName);
	});
	//getNREGSProjectsOverview('')
	
	$(document).on('click','#getWebserviceDetailsId', function(){
		getWebserviceHealthDetails(glStartDateForWebservice,glEndDateForWebservice);
	});   
	$(document).on('click','[overview-level]', function(){
		alert(112)
		var levelType = $(this).attr("overview-level");
		var divId = $(this).attr("overview-divId");
		var levelId = $(this).attr("overview-levelId");
		var locationId = $(this).attr("overview-locationId");
		var menuLocationId = '';
		var menuLocationType = '';
		if(levelId == 2)
		{
			menuLocationId = "-1";
			menuLocationType = "state";
		}else if(levelId == 3)
		{
			menuLocationId = locationId;
			menuLocationType = "district";
		}
		
		var theadArr = [levelType,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		if(levelType == "constituency")
			theadArr = ["district",levelType,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		else if(levelType == "mandal")
			theadArr = ["district","constituency",levelType,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		else if(levelType == "panchayat")
			theadArr = ["district","constituency","mandal",levelType,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		if((divId == 'Mulbery' || divId == 'Silk worm' || divId == 'Cattle drinking water trough' || divId == 'Raising of Perinnial Fodder') && levelType == "state")
			theadArr = [levelType,'TARGET','sanctioned Target','sanctioned Percentage','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		if((divId == 'Fish Ponds' || divId == 'Fish Drying Platforms') && (levelType == "state" || levelType == "district"))
			theadArr = [levelType,'TARGET','sanctioned Target','sanctioned Percentage','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		var tableId = divId.replace(/\s+/g, '')+''+levelType;
		$("#"+tableId).html(spinner);
		if(levelType == 'district' || levelType == 'constituency' || levelType == 'panchayat' || levelType == 'mandal')
		{
			
			if(divId == 'Labour Budget')
				getNREGSLabBugdtLelwiseData(tableId,levelType,menuLocationType,menuLocationId);
			else if(divId == "Agriculture Activities")
				getNregaLevelsWiseDataFrAgriculture(tableId,levelType,menuLocationType,menuLocationId);
			else if(divId == "Average Wage" || divId == "Average Days of Employment" || divId == "HH Completed 100 Days" || divId == "Nurseries" || divId == "Timely Payment")
				getNregaLevelsWiseDataFrNewCalls(tableId,levelType,menuLocationType,menuLocationId,divId);
			else if(divId == "Horticulture")//
				getNregaLevelsWiseDataFrHorticulture(tableId,levelType,menuLocationType,menuLocationId);
			else if(divId == "Avenue")//
				getNregaLevelsWiseDataFrAvenue(tableId,levelType,menuLocationType,menuLocationId);
			else if(divId == "CC Roads")//
				getNregaLevelsWiseDataForCCRoads(tableId,levelType,menuLocationType,menuLocationId);
			else if(divId == "Payments" && (levelType == "state" || levelType == "district" || levelType == "mandal"))//
				getNregaLevelsWiseDataForTimelyPayments(tableId,levelType,menuLocationType,menuLocationId);
			else if(divId == "FAperformance")
				getNregaLevelsWiseDataForFAPerformance(tableId,levelType,theadArr,menuLocationType,menuLocationId);
			else
				getNregaLevelsWiseData(tableId,levelType,theadArr,menuLocationType,menuLocationId);
		}
	});
	
}
	$("#dateRangePickerAUM").daterangepicker({   
			opens: 'left',
			startDate: glStartDateForWebservice,
			endDate: glEndDateForWebservice,   
		locale: {
		  format: 'DD/MM/YYYY'
		},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
			'Today' : [moment(), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'Last 2 Year': [moment().subtract(2, 'Year'), moment()],
		   'Last 3 Year': [moment().subtract(3, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:first-child").addClass("active");
		$('[role="tablist"] li:first-child a').trigger('click');
		$('#tabCons a[href="#consLevelGraph"]').trigger('click');
		glStartDateForWebservice = picker.startDate.format('DD/MM/YYYY')
		glEndDateForWebservice = picker.endDate.format('DD/MM/YYYY')
		getWebserviceHealthDetails(glStartDateForWebservice,glEndDateForWebservice);
	});
	function getWebserviceHealthDetails(fromDate,toDate){
	$("#webserviceDetailsModalDivId").modal('show');
	$("#webserviceDetailsModalId").html(spinner);    
	var json = {
					fromDate : fromDate,    
					toDate : toDate, 
				};
	$.ajax({
			url : "getWebserviceHealthDetails",             
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				buildWebserviceHealthDetails(ajaxresp);
			}
		});
	}
	function buildWebserviceHealthDetails(ajaxresp){
		var str = "";
		str+='<div class="table-responsive">';
		str+='<table id="webserviceHealthDetailsTableId" class="table table-bordered">';
		str+='<thead>';
		str+='<tr>';
		str+='<th>Provider</th>';
		str+='<th>Module</th>';
		str+='<th>Service Name</th>';
		str+='<th>No of Call</th>';
		str+='<th>Success</th>';
		str+='<th>Fail</th>';
		str+='<th>No Response</th>';
		str+='<th>Total time taken</th>';
		str+='<th>Average time taken</th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for (var i in ajaxresp){  
			str+='<tr>';
			str+='<td>'+ajaxresp[i].providerName+'</td>';
			str+='<td>'+ajaxresp[i].moduleName+'</td>';
			str+='<td>'+ajaxresp[i].webserviceName+'</td>';
			str+='<td>'+ajaxresp[i].totalCalls+'</td>';
			str+='<td>'+ajaxresp[i].totalSuccess+'</td>';
			if(ajaxresp[i].totalFail != 0){
				str+='<td>'+ajaxresp[i].totalFail+'</td>';
			}else{
				str+='<td>-</td>';
			}
			if(ajaxresp[i].noResponce != 0){
				str+='<td>'+ajaxresp[i].noResponce+'</td>';
			}else{
				str+='<td>-</td>';
			}
			str+='<td>'+ajaxresp[i].totalTime+'</td>';
			str+='<td>'+ajaxresp[i].averageTime+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		$("#webserviceDetailsModalId").html(str);
		$("#webserviceHealthDetailsTableId").dataTable();
		
	}
	
function minimise(Id,count)
{
	var id = Id;
	var minimized_elements = $(id);
	minimized_elements.each(function(){    
		var t = $(this).text();        
		if(t.length < count) return;

		$(this).html(
			'<span class="less">'+t.slice(0,count)+'..</span>'+
			'<span style="display:none;" class="more text-capitalize">'+t+'</span>'
		);

	}); 
	$(document).on("mouseover",id,function(){
		$(this).find('span').hide();
		$(this).find('span.more').show();
	});
	$(document).on("mouseout",id,function(){
		$(this).find('span.less').show();
		$(this).find('span.more').hide();
	});	
}
function projectData(divId,levelId,locationId)
{
	//alert(locationId);
	var collapse='';
	var dataArr = '';
	if(levelId == 2)
	{
		dataArr = ['state','district','constituency','mandal','panchayat'];
	}else if(levelId == 3)
	{
		dataArr = ['district','constituency','mandal','panchayat'];
	}
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in dataArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+dataArr[i]+'">';
								if(i == 0)
								{
									collapse+='<a role="button" class="panelCollapseIcon" overview-levelId="'+levelId+'" overview-locationId="'+locationId+'" overview-divId="'+divId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed" overview-levelId="'+levelId+'" overview-divId="'+divId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
								}
									collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level overview - '+divId+'</h4>';
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+divId.replace(/\s+/g, '')+''+dataArr[i]+'"></div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				}
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	$("#projectData").html(collapse);
	//From Menu
	var menuLocationId = '';
	var menuLocationType = '';
	if(levelId == 2)
	{
		menuLocationId = "-1";
		menuLocationType = "state";
	}else if(levelId == 3)
	{
		menuLocationId = locationId;
		menuLocationType = "district";
	}
	
	
		var theadArr = [dataArr[0],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		if(dataArr[0] == "constituency")
			theadArr = ["district",dataArr[0],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		else if(dataArr[0] == "mandal")
			theadArr = ["district","constituency",dataArr[0],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		else if(dataArr[0] == "panchayat")
			theadArr = ["district","constituency","mandal",dataArr[0],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		if((divId == 'Mulbery' || divId == 'Silk worm' || divId == 'Cattle drinking water trough' || divId == 'Raising of Perinnial Fodder') && dataArr[0] == "state")
			theadArr = [dataArr[0],'TARGET','sanctioned Target','sanctioned Percentage','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		if((divId == 'Fish Ponds' || divId == 'Fish Drying Platforms') && (dataArr[0] == "state" || dataArr[0] == "district"))
			theadArr = [dataArr[0],'TARGET','sanctioned Target','sanctioned Percentage','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		var tableId = divId.replace(/\s+/g, '')+''+dataArr[0];
		$("#"+tableId).html(spinner);
		if(divId == 'Labour Budget')
			getNREGSLabBugdtLelwiseData(tableId,dataArr[0],menuLocationType,menuLocationId);
		else if(divId == "Agriculture Activities")
			getNregaLevelsWiseDataFrAgriculture(tableId,dataArr[0],menuLocationType,menuLocationId);
		else if(divId == "Average Wage" || divId == "Average Days of Employment" || divId == "HH Completed 100 Days" || divId == "Nurseries" || divId == "Timely Payment")
			getNregaLevelsWiseDataFrNewCalls(tableId,dataArr[0],menuLocationType,menuLocationId,divId);
		else if(divId == "Horticulture")//
			getNregaLevelsWiseDataFrHorticulture(tableId,dataArr[0],menuLocationType,menuLocationId);
		else if(divId == "Avenue")//
			getNregaLevelsWiseDataFrAvenue(tableId,dataArr[0],menuLocationType,menuLocationId);
		else if(divId == "CC Roads")//
			getNregaLevelsWiseDataForCCRoads(tableId,dataArr[0],menuLocationType,menuLocationId);
		else if(divId == "Payments" && (dataArr[0] == "state"))//
			getNregaLevelsWiseDataForTimelyPayments(tableId,dataArr[0],menuLocationType,menuLocationId);
		else if(divId == "FAperformance")
			getNregaLevelsWiseDataForFAPerformance(tableId,dataArr[0],theadArr,menuLocationType,menuLocationId);
		else
			getNregaLevelsWiseData(tableId,dataArr[0],theadArr,menuLocationType,menuLocationId);
	
}
function overviewData(divId,levelId,locationId)
{
	var collapse = '';
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+'" role="tablist" aria-multiselectable="true">';
					collapse+='<div class="panel panel-default panel-black">';
						collapse+='<div class="panel-heading" role="tab" id="heading'+divId+'">';
							collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+'" href="#collapse'+divId.replace(/\s+/g, '')+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+'">';
								collapse+='<h4 class="panel-title text-capital">'+divId+' overview</h4>';
							collapse+='</a>';
						collapse+='</div>';
						collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+'">';
							collapse+='<div class="panel-body">';
								collapse+='<div id="projectOvervw'+divId.replace(/\s+/g, '')+'"></div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</section>';
	$("#projectOverviewBlock").html(collapse);
	//For Menu
	var menuLocationId = '';
	var menuLocationType = '';
	if(levelId == 2)
	{
		menuLocationId = "-1";
		menuLocationType = "state";
	}else if(levelId == 3)
	{
		menuLocationId = locationId;
		menuLocationType = "district";
	}
	
	
	if(divId == 'Labour Budget')
		getNREGSLabourBudgetOverview(divId,menuLocationType,menuLocationId);
	else if(divId != 'Payments')
		getNregasOverview(divId,menuLocationType,menuLocationId);
}
function tableView(blockId,theadArr,result,locationType)
{
	var tableView='';
	
	tableView+='<div class="table-responsive">';
		tableView+='<table class="table table-bordered dataTable'+blockId+'">';
			tableView+='<thead class="text-capital">';
				for(var i in theadArr)
				{
					tableView+='<th>'+theadArr[i]+'</th>';
				}
			tableView+='</thead>';
			tableView+='<tbody>';
				tableView+=result;
			tableView+='</tbody>';
		tableView+='</table>';
	tableView+='</div>';
	$("#"+blockId).html(tableView);	
	if(locationType == 'constituency' || locationType == 'mandal' || locationType == 'panchayat')
	{
		$(".dataTable"+blockId).dataTable({
			"iDisplayLength": 15,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}else if(locationType == 'district')
	{
		$(".dataTable"+blockId).dataTable({
			"iDisplayLength": 20,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
	
}
function getNREGSProjectsOverview(blockName)
{
	$("#projectsOverview").html(spinner);
	var json = {
			year:"2017",
			fromDate:glStartDate,
			toDate:glEndDate
		}
	$.ajax({
		url: 'getNREGSProjectsOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			//buildNREGSProjectsOverview(ajaxresp,blockName);
			
		}
	});
}

function buildNREGSProjectsOverview(result,blockName)
{
	var str='';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12 bg_color" style="border: 5px solid #fff;padding:15px;">';
			str+='<h4 class="text-center m_top10"><b>NON-CONVERGENCE</b></h4>';
				str+='<div class="row">';
					str+='<div class="col-sm-12">';
						str+='<div class="block-border">';
							str+='<h5 class="text-danger">Labour Budget</h5>';
							str+='<div class="row">';	
								for(var i in result)
								{
									if(result[i] == "Labour Budget" || result[i] == "Average Wage" || result[i] == "Average Days of Employment" || result[i] == "HH Completed 100 Days" || result[i] == "Timely Payment")
									{
										str+='<div class="col-sm-2 m_top10">';
											str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';
												if(result[i].length > 12)
												{
													str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
												}else{
													str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
					if(result[i] == "Farm Ponds" || result[i] == "IHHL" || result[i] == "Vermi Compost" || result[i] == "Solid Waste Management" || result[i] == "Play Fields" || result[i] == "Burial Grounds" || result[i] == "Agriculture Activities" || result[i] == "Payments"){
						if(result[i] == "Payments")
						{
							str+='<div class="col-sm-4 m_top10">';
						}else{
							str+='<div class="col-sm-2 m_top10">';
						}
						
					
							str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';	
								if(result[i].length > 12)
								{
									str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
								}else{
									str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
								}
							str+='</div>';
						str+='</div>';
					}
				}
				str+='<div class="col-sm-2 m_top10">';
					str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';	
						if(result[i].length > 12)
						{
							str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
						}else{
							str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
						}
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-12 bg_color" style="border: 5px solid #fff;padding:15px;">';
			str+='<h4 class="m_top10 text-center"><b>CONVERGENCE-PR DEPTS</b></h4>';
			str+='<div class="row">';
				for(var i in result)
				{
					if(result[i] == "CC Roads" || result[i] == "Anganwadi Buildings" || result[i] == "GP Buildings" || result[i] == "Mandal Buildings"){
						str+='<div class="col-sm-2 m_top10">';
							str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';	
								if(result[i].length > 12)
								{
									str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
								}else{
									str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
								}
							str+='</div>';
						str+='</div>';
					}
				}
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-12 bg_color" style="border: 5px solid #fff;padding:15px;">';
			str+='<h4 class="m_top10 text-center"><b>CONVERGENCE-OTHER DEPTS</b></h4>';
			str+='<div class="row m_top20">';
				str+='<div class="col-sm-4">';
					str+='<div class="block-border">';
						str+='<h5 class="text-danger">Housing</h5>';
						str+='<div class="row">';	
							for(var i in result)
							{
								if(result[i] == "NTR 90 Days" || result[i] == "Production of Bricks"){
									str+='<div class="col-sm-6 m_top10">';
										str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';	
											if(result[i].length > 12)
											{
												str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
											}else{
												str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
						str+='<h5 class="text-danger">Seri Culture</h5>';
						str+='<div class="row">';	
							for(var i in result)
							{
								if(result[i] == "Mulbery" || result[i] == "Silk Worms" ){
									str+='<div class="col-sm-6 m_top10">';
										str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';	
											if(result[i].length > 12)
											{
												str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
											}else{
												str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
						str+='<h5 class="text-danger">Animal Husbendary</h5>';
						str+='<div class="row">';	
							for(var i in result)
							{
								if(result[i] == "Cattle Drinking Water Troughs" || result[i] == "Raising of Perinnial Fodders"){
									str+='<div class="col-sm-6 m_top10">';
										str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';
											if(result[i].length > 12)
											{
												str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
											}else{
												str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
								if(result[i] == "Horticulture" || result[i] == "Avenue"){
									str+='<div class="col-sm-6 m_top10">';
										str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';
											if(result[i].length > 12)
											{
												str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
											}else{
												str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
								if(result[i] == "Fish Drying Platforms" || result[i] == "Fish Ponds"){
									str+='<div class="col-sm-6 m_top10">';
										str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';
											if(result[i].length > 12)
											{
												str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
											}else{
												str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
								if(result[i] == "Nurseries"){
									str+='<div class="col-sm-6 m_top10">';
										str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';
											if(result[i].length > 12)
											{
												str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+result[i]+'">'+result[i].substr(0,12)+'..</h4>';
											}else{
												str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
	$("#projectsOverview").html(str);

	minimise(".rightNavigationMenu li",8)
	$(".toolTipTitleCls").tooltip();
	if(blockName != null)
	{
		$('[overview-block]').removeClass("active");
		$('[overview-block="'+blockName+'"]').addClass("active");
		$('[overview-block="'+blockName+'"]').trigger('click');
	}
	
}

//LabourBudget Overview Call — Sravanth
function getNREGSLabourBudgetOverview(projectDivId,menuLocationType,menuLocationId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate,
		divType : globalDivName,
		locationType : menuLocationType,
		locationId : menuLocationId
	}
	$.ajax({
		url: 'getLabourBudgetOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNregasOverViewBlock(ajaxresp,projectDivId,menuLocationType,menuLocationId)
		}
	});
}

//LabourBudget Expenditure Call  — Sravanth
function getNREGSLabourBudgetExpenditure(projectDivId,menuLocationType,menuLocationId)
{
	$("#projectOvervw"+projectDivId).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate,
        locationType : menuLocationType,
        locationId : menuLocationId
	}
  $.ajax({
    url: 'getLabourBudgetExpenditure',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp) {
		buildLabrBudgetExpBlock(ajaxresp,projectDivId,menuLocationType,menuLocationId)
    }
  });
}

//LabourBudget LevelWise Data Call — Sravanth
function getNREGSLabBugdtLelwiseData(divIdd,locationType,menuLocationType,menuLocationId)
{
	var theadArr = [locationType,'Target Person days','Generated','Achivement Percentage','Wage Expenditure','Material Expenditure','Total Expenditure','Material Perc'];
	if(locationType == "constituency")
		theadArr = ["district",locationType,'Target Person days','Generated','Achivement Percentage','Wage Expenditure','Material Expenditure','Total Expenditure','Material Perc'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Target Person days','Generated','Achivement Percentage','Wage Expenditure','Material Expenditure','Total Expenditure','Material Perc'];
	else if(locationType == "panchayat")
		theadArr = ["district","constituency","mandal",locationType,'Target Person days','Generated','Achivement Percentage','Wage Expenditure','Material Expenditure','Total Expenditure','Material Perc'];
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationType 
	}
	$.ajax({
		url: 'getNregaLevelwiseOverviewForLabourBudgetData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationType == "state"){
							str+='<td class="text-capital">'+locationType+'</td>';
						}
						else if(locationType == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationType == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationType == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}	
						if(locationType == "state"){
							str+='<td>'+ajaxresp[i].targetPersonDays+'L</td>';
							str+='<td>'+ajaxresp[i].generatedPersonDays+'L</td>';
						}else{
							str+='<td>'+ajaxresp[i].targetPersonDays+'</td>';
							str+='<td>'+ajaxresp[i].generatedPersonDays+'</td>';
						}
						
						if(ajaxresp[i].perAppLB < 50)
						{
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].perAppLB+'</td>';
						}else if(ajaxresp[i].perAppLB >= 50 && ajaxresp[i].perAppLB < 80)
						{
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].perAppLB+'</td>';
						}else if(ajaxresp[i].perAppLB >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].perAppLB+'</td>';
						}
						
						str+='<td>'+ajaxresp[i].wageExpenditure+'</td>';
						str+='<td>'+ajaxresp[i].materialExpenditure+'</td>';
						str+='<td>'+ajaxresp[i].totalExpenditure+'</td>';
						str+='<td>'+ajaxresp[i].materialExpenditurePerc+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function buildNregasOverViewBlock(result,projectDivId,menuLocationType,menuLocationId){
	var $windowWidth = $(window).width();
	var str1="";
	if(result.averagePerDistrict != null)
	{
		str1+='<div class="table-responsive">';
			str1+='<table class="table table-bordered" >';
				str1+='<tbody>';
					str1+='<tr>';
						str1+='<td>';
							str1+='<p>Average Per District : '+result.averagePerDistrict+'</p>';
						str1+='</td>';
						str1+='<td>';
							str1+='<p>Total Avg '+projectDivId+' in District : '+result.totalAvgFarmsInDistrict+'</p>';
						str1+='</td>';
					str1+='</tr>';
					str1+='<tr>';
						str1+='<td>';
							str1+='<p>Average Per Constituency : '+result.averagePerConstituency+'</p>';
						str1+='</td>';
						str1+='<td>';
							str1+='<p>Total Avg '+projectDivId+' in Constituency : '+result.totalAvgFarmsInConstituency+'</p>';
						str1+='</td>';
					str1+='</tr>';
					str1+='<tr>';
						str1+='<td>';
							str1+='<p>Average Per Mandal : '+result.averagePerMandal+'</p>';
						str1+='</td>';
						str1+='<td>';
							str1+='<p>Total Avg '+projectDivId+' in Mandal: '+result.totalAvgFarmsInMandal+'</p>';
						str1+='</td>';
					str1+='</tr>';
					str1+='<tr>';
						str1+='<td>';
							str1+='<p>Total Budget</p>';
							str1+='<h4>'+result.totalBudget+'</h4>';
						str1+='</td>';
						str1+='<td>';
							str1+='<p>Total '+projectDivId+'</p>';
							str1+='<h4>'+result.totalBudget+'</h4>';
						str1+='</td>';
					str1+='</tr>';
				str1+='</tbody>';
			str1+='</table>';
		str1+='</div>';
	}
	if(result.maleLabour != null){
		str1+='<div class="table-responsive">';
			str1+='<table class="table table-bordered m_top10">';
				str1+='<tbody>';
					str1+='<tr>';
						str1+='<td>';
							str1+='	<div class="col-sm-12">';
								str1+='<h4><b>Total labour : '+result.totalLabour+'L</b></h4>';
							str1+='</div>';
							str1+='<div class="col-sm-12 m_top10">';
								str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Male.png"></p><p class="media-body">Male Labours : '+result.maleLabour+'L</p>';
								str1+='</div>';
								str1+='<div class="media"> ';  
								str1+='<p class="media-left">';
									str1+='<img src="Assests/icons/Female.png"></p><p class="media-body">Female Labour : '+result.femaleLabour+'L</p>';
								str1+='</div> ';
							str1+='</div>';
						str1+='</td>';
						str1+='<td>';
							str1+='<div class="col-sm-12">';
								str1+='<h4><b>Total Response : '+result.totalResponse+'L</b></h4>';
							str1+='	</div>';
							str1+='<div class="col-sm-12 m_top10">';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green_Worker.png"></p><p class="media-body">On request work allocated : '+result.onRequestWorkAllocated+'L</p>';
									str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange_Worker.png"></p><p class="media-body">Got work occutionaly :'+result.gotWorkOccutionally+'L</p>';
									str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red_Worker.png"></p><p class="media-body">Havent got work :'+result.haveNotGotWork+'L</p>';
								str1+='</div>';
							str1+='</div>';
						str1+='</td>';
						str1+='<td>';
							str1+='<div class="col-sm-12">';
								str1+='<h4><b>Total Budget : <i class="fa fa-inr"></i>'+result.totalBudget+'</b></h4>';
							str1+='</div>';
							str1+='<div class="col-sm-12 m_top10">';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Labours_icon.png"></p><p class="media-body">Budget for Labours : <i class="fa fa-inr"></i>'+result.budgetForLabour+'</p>';
								str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Bugget_icon.png"></p><p class="media-body">Budget for Material : <i class="fa fa-inr"></i>'+result.budgetForMaterial+'</p>';
									str1+='</div>';
							str1+='</div>';
						str1+='</td>';
					str1+='</tr>';
				str1+='</tbody>';
			str1+='</table>';
		str1+='</div>';
		
	}
	str1+='<div class="table-responsive">';
		str1+='<table class="table table-bordered m_top10" >';
			str1+='<tbody>';
				str1+='<tr>';
					str1+='<td>';
						str1+='<div class="col-sm-12">';
							str1+='<h4 attr_locationType="district" attr_type="total"><strong>Total Districts : '+result.totalDistricts+'</strong></h4>';
						str1+='</div>';
						str1+='<div class="col-sm-12 m_top10">';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								if(result.districtsInRed != null && result.districtsInRed > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_locationType="district" attr_type="red" style="cursor:pointer;">Districts in Red : '+result.districtsInRed+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="district" attr_type="red">Districts in Red : '+result.districtsInRed+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
								if(result.districtsInOrange != null && result.districtsInOrange > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="district" attr_type="orange" style="cursor:pointer;">Districts in Orange : '+result.districtsInOrange+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="district" attr_type="orange" >Districts in Orange : '+result.districtsInOrange+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.districtsInGreen != null && result.districtsInGreen > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="district" attr_type="green" style="cursor:pointer;">Districts in Green : '+result.districtsInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="district" attr_type="green" >Districts in Green : '+result.districtsInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';
					str1+='</td>';
					str1+='<td>';
						str1+='<div class="col-sm-12">';
							str1+='<h4 attr_locationType="constituency" attr_type="total"><strong>Total Constituencies : '+result.totalConstituencies+'</strong></h4>';
						str1+='</div>';
						str1+='<div class="col-sm-12 m_top10">';
							str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
							if(result.constituenciesInRed != null && result.constituenciesInRed > 0)
								str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="constituency" attr_type="red" style="cursor:pointer;">Constituencies in Red : '+result.constituenciesInRed+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="constituency" attr_type="red" >Constituencies in Red : '+result.constituenciesInRed+'</p>';
								str1+='</div>';
							str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							if(result.constituenciesInOrange != null && result.constituenciesInOrange > 0)
								str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="constituency" attr_type="orange" style="cursor:pointer;">Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="constituency" attr_type="orange" >Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
								str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.constituenciesInGreen != null && result.constituenciesInGreen > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="constituency" attr_type="green" style="cursor:pointer;">Constituencies in Green : '+result.constituenciesInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="constituency" attr_type="green" >Constituencies in Green : '+result.constituenciesInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';	
						
					str1+='</td>';
					str1+='<td>';
						str1+='<div class="col-sm-12">';
							str1+='<h4 attr_locationType="mandal" attr_type="total"><strong>Total Mandals : '+result.totalMandals+'</strong></h4>';
						str1+='</div>';
						str1+='<div class="col-sm-12 m_top10">';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								if(result.mandalsInRed != null && result.mandalsInRed > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="mandal" attr_type="red" style="cursor:pointer;">Mandals in Red : '+result.mandalsInRed+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="mandal" attr_type="red">Mandals in Red : '+result.mandalsInRed+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
								if(result.mandalsInOrange != null && result.mandalsInOrange > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="mandal" attr_type="orange" style="cursor:pointer;">Mandals in Orange : '+result.mandalsInOrange+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="mandal" attr_type="orange">Mandals in Orange : '+result.mandalsInOrange+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.mandalsInGreen != null && result.mandalsInGreen > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="mandal" attr_type="green" style="cursor:pointer;">Mandals in Green : '+result.mandalsInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="mandal" attr_type="green">Mandals in Green : '+result.mandalsInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';	
					str1+='</td>';
					str1+='<td>';
						str1+='<div class="col-sm-12">';
							str1+='<h4><strong>Total Villages : '+result.totalVillages+'</strong></h4>';
						str1+='</div>';
						str1+='<div class="col-sm-12 m_top10">';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								if(result.villagesInRed != null && result.villagesInRed > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="panchayat" attr_type="red" style="cursor:pointer;">Villages in Red : '+result.villagesInRed+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="panchayat" attr_type="red" style="cursor:pointer;"">Villages in Red : '+result.villagesInRed+'</p>';
							str1+='</div>';
							str1+=' <div class="media">';
							   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							   if(result.villagesInOrange != null && result.villagesInOrange > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="panchayat" attr_type="orange" style="cursor:pointer;">Villages in Orange : '+result.villagesInOrange+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="panchayat" attr_type="orange" style="cursor:pointer;">Villages in Orange : '+result.villagesInOrange+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.villagesInGreen != null && result.villagesInGreen > 0)
									str1+='<p class="media-body overviewPopupCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'"  attr_locationType="panchayat" attr_type="green" style="cursor:pointer;">Villages in Green : '+result.villagesInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_locationType="panchayat" attr_type="green" style="cursor:pointer;">Villages in Green : '+result.villagesInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';	
					str1+='</td>';
				str1+='</tr>';
			str1+='</tbody>';
		str1+='</table>';
	str1+='</div>';

	if(result.targettedPersonDays != null)
	{
		str1+='<div id="projectExp'+projectDivId.replace(/\s+/g, '')+'" style="margin-top:10px;"></div>';
		str1+='<div class="table-responsive">';
		
			str1+='<table class="table table-bordered m_top20">';
				str1+='<tr>';
					str1+='<td>';
						str1+='<p class="col-sm-9">Targeted Person days</p>';
						str1+='<p class="col-sm-3 text-center">'+result.targettedPersonDays+'</p>';
						str1+='<p class="col-sm-9">Generated person days</p>';
						str1+='<p class="col-sm-3 text-center">'+result.generatedPersonDays+'</p>';
						str1+='<p class="col-sm-9">Acheivement Percentage</p>';
						str1+='<p class="col-sm-3 bg-danger text-danger text-center">'+result.achievementPerc+'</p>';
					str1+='</td>';
					str1+='<td>';
						//str1+='<p class="col-sm-9">(%) of Labour reported over target</p>';
						//str1+='<p class="col-sm-3 bg-success text-success text-center"></p>';
						str1+='<p class="col-sm-9">Average Wage Per Person: Rs</p>';
						str1+='<p class="col-sm-3 bg-danger text-danger text-center">'+result.avgWagePerPerson+'</p>';
						str1+='<p class="col-sm-9">Total Expenditure: Rs</p>';
						str1+='<p class="col-sm-3 text-center">'+result.totalExpenditure+'</p>';
					str1+='</td>';
				str1+='</tr>';
			str1+='</table>';
		str1+='</div>';
		getNREGSLabourBudgetExpenditure(projectDivId,menuLocationType,menuLocationId);
	}
	
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(str1);
	
}

//LabourBudget Exp Builing --  Nandhini
function buildLabrBudgetExpBlock(result,projectDivId,menuLocationType,menuLocationId){
	var str='';
	str+='<p class="text-center expenditure"><strong>No of Panchayaties Vs Expenditure In Lakhs</strong></p>';
		str+='<div class="table-responsive">';
			str+='<table class="table table-striped table-bordered m_top10">';
				str+='<tbody>';
					 str+='<tr>';
						 str+='<td></td>';
						 for(var i in result){
							 str+='<td>'+result[i].name+'</td>';
						 }
					  str+='</tr>';
					str+=' <tr>';
						str+=' <td>Grand Total</td>';
						for(var i in result){
							if(result[i].count != null && result[i].count != 0){
								str+='<td class="cuntCls" style="cursor:pointer;" attr_range="'+result[i].name+'" attr_location_type="'+menuLocationType+'" attr_loaction_id="'+menuLocationId+'">'+result[i].count+'</td>';
							}else{
								str+='<td attr_range="'+result[i].name+'" attr_location_type="'+menuLocationType+'" attr_loaction_id="'+menuLocationId+'">'+result[i].count+'</td>';
							}
						}
					str+='</tr>';
					str+=' <tr>';
						str+=' <td>Percentage</td>';
						for(var i in result){
							str+='<td>'+result[i].totl+'</td>';
						}
					str+='</tr>';
				str+='</tbody>';
			str+='</table>';
		str+='</div>';
	$("#projectExp"+projectDivId.replace(/\s+/g, '')).html(str);
}

//DistrictConst Call -- Nandhini
function buildDistrictsPopupDetails(result,dataArr){
	var str = '';
	if(result.distList != null && result.distList.length > 0 )
	{
		var theadArr;
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">'+dataArr+' Details</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
			
			if(globalDivName == "Average Wage" || globalDivName == "Average Days of Employment" || globalDivName == "HH Completed 100 Days"){
				theadArr = [dataArr,'TARGET','Achivement','Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET','Achivement','Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET','Achivement','Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'TARGET','Achivement','Percentage'];
			}else if(globalDivName == "Timely Payment"){
				theadArr = [dataArr,'TARGET Percentage','Achivement Percentage','Achieved Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET Percentage','Achivement Percentage','Achieved Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET Percentage','Achivement Percentage','Achieved Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'TARGET','Achivement','Percentage'];
			}else if(globalDivName == "Agriculture Activities"){
				theadArr = [dataArr,'TARGET','Completed','Achivement'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET','Completed','Achivement'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET','Completed','Achivement'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'TARGET','Completed','Achivement'];
			}else if(globalDivName == "Fish Drying Platforms" || globalDivName == "Fish Ponds"){
				theadArr = [dataArr,'TARGET','Sanctioned Target','Sanctioned Percentage','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
			}else if(globalDivName == "CC Roads"){
				theadArr = [dataArr,'TARGET Length[IN KMS]','Sanctioned Estimate Cost','Sanctioned Length[IN KMS]','Percentage Of Sanctioned Length','Expenditure','Completed Length[IN KMS]','Achivement Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'Sanctioned Estimate Cost','Sanctioned Length[IN KMS]','Expenditure','Completed Length[IN KMS]','Achivement Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'Sanctioned Estimate Cost','Sanctioned Length[IN KMS]','Expenditure','Completed Length[IN KMS]','Achivement Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'Sanctioned Estimate Cost','Sanctioned Length[IN KMS]','Expenditure','Completed Length[IN KMS]','Achivement Percentage'];
			}else if(globalDivName == "Horticulture"){
				theadArr = [dataArr,'TARGET','Sanctioned Area[IN ACRES]','Sanctioned Percentage','Pitting Area[In ACRES]','Planting Area[In ACRES]','Achivement Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'Sanctioned Area[IN ACRES]','Pitting Area[In ACRES]','Planting Area[In ACRES]','Achivement Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'Sanctioned Area[IN ACRES]','Pitting Area[In ACRES]','Planting Area[In ACRES]','Achivement Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'Sanctioned Area[IN ACRES]','Pitting Area[In ACRES]','Planting Area[In ACRES]','Achivement Percentage'];
			}else if(globalDivName == "Avenue"){
				theadArr = [dataArr,'TARGET','Sanctioned Area[IN KMS]','Sanctioned Percentage','Pitting Area[In KMS]','Planting Area[In KMS]','Achivement Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'Sanctioned Area[IN KMS]','Pitting Area[In KMS]','Planting Area[In KMS]','Achivement Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'Sanctioned Area[IN KMS]','Pitting Area[In KMS]','Planting Area[In KMS]','Achivement Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'Sanctioned Area[IN KMS]','Pitting Area[In KMS]','Planting Area[In KMS]','Achivement Percentage'];
			}else if(globalDivName == "Nurseries"){
				theadArr = [dataArr,'TARGET','Completed','Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET','Completed','Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET','Completed','Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'TARGET','Completed','Percentage'];
			}else{
				theadArr = [dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				else if(dataArr == "panchayat")
					theadArr = ["district","constituency","mandal",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
			}
				 
				str+='<div class="table-responsive">';
				if(globalDivName != 'Labour Budget'){
					str+='<div class="table-responsive">';
						str+='<table class="table table-bordered m_top10 dataTableClsDist">';
							str+='<thead>';
								str+='<tr>';
									for(var i in theadArr)
									{
										str+='<th class="text-capital">'+theadArr[i]+'</th>';
									}
								str+='</tr>';
							str+='</thead>';
						str+='<tbody>';
						
								for(var i in result.distList){
									if(globalDivName == "Average Wage" || globalDivName == "Average Days of Employment" || globalDivName == "HH Completed 100 Days" || globalDivName == "Timely Payment"){
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										str+='<td>'+result.distList[i].target+'</td>';
										str+='<td>'+result.distList[i].achivement+'</td>';
										str+='<td>'+result.distList[i].percentage+'</td>';
									str+='</tr>';
									}else if(globalDivName == "Agriculture Activities"){
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										str+='<td>'+result.distList[i].target+'</td>';
										str+='<td>'+result.distList[i].completed+'</td>';
										str+='<td>'+result.distList[i].achivement+'</td>';
									str+='</tr>';
									}else if(globalDivName == "Fish Drying Platforms" || globalDivName == "Fish Ponds"){
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										str+='<td>'+result.distList[i].target+'</td>';
										if(dataArr == "district"){
											str+='<td>'+result.distList[i].sanctionedTarget+'</td>';
											str+='<td>'+result.distList[i].sanctionedPerventage+'</td>';
										}
										str+='<td>'+result.distList[i].grounded+'</td>';
										str+='<td>'+result.distList[i].notGrounded+'</td>';
										str+='<td>'+result.distList[i].inProgress+'</td>';
										str+='<td>'+result.distList[i].completed+'</td>';
										if(result.distList[i].percentage < 50){
											str+='<td style="background-color:#FF0000">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 50 && result.distList[i].percentage < 80){
											str+='<td style="background-color:#FFBA00">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 80)
										{
											str+='<td style="background-color:#00AF50">'+result.distList[i].percentage+'</td>';
										}
										//str+='<td>'+result.distList[i].percentage+'</td>';
									str+='</tr>';
									}else if(globalDivName == "CC Roads"){
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										//str+='<td>'+result.distList[i].target+'</td>';
										if(dataArr == "district")
											str+='<td>'+result.distList[i].targetKMS+'</td>';
											
										str+='<td>'+result.distList[i].sanctionedAmount+'</td>';
										str+='<td>'+result.distList[i].sanctionedKMS+'</td>';
										
										if(dataArr == "district")
											str+='<td>'+result.distList[i].sanctionedPerventage+'</td>';
										
										str+='<td>'+result.distList[i].expenditureAmount+'</td>';
										str+='<td>'+result.distList[i].completedKMS+'</td>';
										if(result.distList[i].percentage < 50){
											str+='<td style="background-color:#FF0000">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 50 && result.distList[i].percentage < 80){
											str+='<td style="background-color:#FFBA00">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 80)
										{
											str+='<td style="background-color:#00AF50">'+result.distList[i].percentage+'</td>';
										}
										//str+='<td>'+result.distList[i].percentage+'</td>';
									str+='</tr>';
									}else if(globalDivName == "Horticulture"){
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										//str+='<td>'+result.distList[i].target+'</td>';
										if(dataArr == "district")
											str+='<td>'+result.distList[i].targetACRES+'</td>';
											
										str+='<td>'+result.distList[i].sanctionedACRES+'</td>';
										if(dataArr == "district")
											str+='<td>'+result.distList[i].sanctionedPerventage+'</td>';
										
										str+='<td>'+result.distList[i].pittingArea+'</td>';
										str+='<td>'+result.distList[i].plantingArea+'</td>';
										if(result.distList[i].pencentageOfPlanting < 50){
											str+='<td style="background-color:#FF0000">'+result.distList[i].pencentageOfPlanting+'</td>';
										}else if(result.distList[i].pencentageOfPlanting >= 50 && result.distList[i].pencentageOfPlanting < 80){
											str+='<td style="background-color:#FFBA00">'+result.distList[i].pencentageOfPlanting+'</td>';
										}else if(result.distList[i].pencentageOfPlanting >= 80)
										{
											str+='<td style="background-color:#00AF50">'+result.distList[i].pencentageOfPlanting+'</td>';
										}
										//str+='<td>'+result.distList[i].pencentageOfPlanting+'</td>';
									str+='</tr>';
									}else if(globalDivName == "Avenue"){
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										//str+='<td>'+result.distList[i].target+'</td>';
										if(dataArr == "district")
											str+='<td>'+result.distList[i].targetKMS+'</td>';
											
										str+='<td>'+result.distList[i].sanctionedKMS+'</td>';
										if(dataArr == "district")
											str+='<td>'+result.distList[i].sanctionedPerventage+'</td>';
										
										str+='<td>'+result.distList[i].pittingKMS+'</td>';
										str+='<td>'+result.distList[i].plantingKMS+'</td>';
										if(result.distList[i].pencentageOfPlanting < 50){
											str+='<td style="background-color:#FF0000">'+result.distList[i].pencentageOfPlanting+'</td>';
										}else if(result.distList[i].pencentageOfPlanting >= 50 && result.distList[i].pencentageOfPlanting < 80){
											str+='<td style="background-color:#FFBA00">'+result.distList[i].pencentageOfPlanting+'</td>';
										}else if(result.distList[i].pencentageOfPlanting >= 80)
										{
											str+='<td style="background-color:#00AF50">'+result.distList[i].pencentageOfPlanting+'</td>';
										}
										//str+='<td>'+result.distList[i].pencentageOfPlanting+'</td>';
									str+='</tr>';
									}else if(globalDivName == "Nurseries"){
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										str+='<td>'+result.distList[i].target+'</td>';
										str+='<td>'+result.distList[i].completed+'</td>';
										if(result.distList[i].percentage < 50){
											str+='<td style="background-color:#FF0000">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 50 && result.distList[i].percentage < 80){
											str+='<td style="background-color:#FFBA00">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 80)
										{
											str+='<td style="background-color:#00AF50">'+result.distList[i].percentage+'</td>';
										}
										//str+='<td>'+result.distList[i].percentage+'</td>';
									str+='</tr>';
									}else{
										str+='<tr>';
										if(dataArr == "district"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
										}
										else if(dataArr == "constituency"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
										}
										else if(dataArr == "mandal"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
										}
										else if(dataArr == "panchayat"){
											str+='<td class="text-capital">'+result.distList[i].district+'</td>';
											str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
											str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
											str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
										}
										str+='<td>'+result.distList[i].target+'</td>';
										str+='<td>'+result.distList[i].grounded+'</td>';
										str+='<td>'+result.distList[i].notGrounded+'</td>';
										str+='<td>'+result.distList[i].inProgress+'</td>';
										str+='<td>'+result.distList[i].completed+'</td>';
										if(result.distList[i].percentage < 50){
											str+='<td style="background-color:#FF0000">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 50 && result.distList[i].percentage < 80){
											str+='<td style="background-color:#FFBA00">'+result.distList[i].percentage+'</td>';
										}else if(result.distList[i].percentage >= 80)
										{
											str+='<td style="background-color:#00AF50">'+result.distList[i].percentage+'</td>';
										}
										//str+='<td>'+result.distList[i].percentage+'</td>';
									str+='</tr>';
									}
								}
							str+='</tbody>';
						str+='</table>';
					str+='</div>';
				}
				else{
					globalDivName
					theadArr = [dataArr,'Target Person days','Generated','Achivement Percentage','Wage Expanditure','Material Expenditure','Total Expenditure','Material Perc'];
					if(dataArr == "constituency")
						theadArr = ["district",dataArr,'Target Person days','Generated','Achivement Percentage','Wage Expanditure','Material Expenditure','Total Expenditure','Material Perc'];
					else if(dataArr == "mandal")
						theadArr = ["district","constituency",dataArr,'Target Person days','Generated','Achivement Percentage','Wage Expanditure','Material Expenditure','Total Expenditure','Material Perc'];
					else if(dataArr == "panchayat")
						theadArr = ["district","constituency","mandal",dataArr,'Target Person days','Generated','Achivement Percentage','Wage Expanditure','Material Expenditure','Total Expenditure','Material Perc'];
					str+='<div class="table-responsive">';
						str+='<table class="table table-bordered m_top10 dataTableClsDist">';
						str+='<thead>';
							str+='<tr>';
								for(var i in theadArr)
								{
									str+='<th class="text-capital">'+theadArr[i]+'</th>';
								}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
					
						for(var i in result.distList){
							str+='<tr>';
								if(dataArr == "district"){
									str+='<td class="text-capital">'+result.distList[i].district+'</td>';
								}
								else if(dataArr == "constituency"){
									str+='<td class="text-capital">'+result.distList[i].district+'</td>';
									str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
								}
								else if(dataArr == "mandal"){
									str+='<td class="text-capital">'+result.distList[i].district+'</td>';
									str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
									str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
								}
								else if(dataArr == "panchayat"){
									str+='<td class="text-capital">'+result.distList[i].district+'</td>';
									str+='<td class="text-capital">'+result.distList[i].constituency+'</td>';
									str+='<td class="text-capital">'+result.distList[i].mandal+'</td>';
									str+='<td class="text-capital">'+result.distList[i].panchayat+'</td>';
								}
									
								str+='<td>'+result.distList[i].targetPersonDays+'</td>';
								str+='<td>'+result.distList[i].generatedPersonDays+'</td>';
								str+='<td>'+result.distList[i].perAppLB+'</td>';
								str+='<td>'+result.distList[i].wageExpenditure+'</td>';
								str+='<td>'+result.distList[i].materialExpenditure+'</td>';
								str+='<td>'+result.distList[i].totalExpenditure+'</td>';
								str+='<td>'+result.distList[i].materialExpenditurePerc+'</td>';
							str+='</tr>';
						}
						str+='</tbody>';
						str+='</table>';
					str+='</div>';
					
				}
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	
	if(result.distConsCuntList != null && result.distConsCuntList.length > 0 )
	{
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">Constituencies in Districts</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered m_top10 dataTableCls">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>District Name </th>';
								str+='<th style="color:red;">Constituencies In Red </th>';
								str+='<th style="color:orange;">Constituencies In Orange </th>';
								str+='<th style="color:green;">Constituencies In Green </th>';
								str+='<th>Total </th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var redTotalCons = 0;
						var orangeTotalCons = 0;
						var greenTotalCons = 0;
						var totalConsiCons = 0;
							for(var i in result.distConsCuntList){
								redTotalCons = redTotalCons+result.distConsCuntList[i].constiInRed;
								orangeTotalCons = orangeTotalCons+result.distConsCuntList[i].constiInOrange;
								greenTotalCons = greenTotalCons+result.distConsCuntList[i].constiInGreen;
								totalConsiCons =redTotalCons+ orangeTotalCons+greenTotalCons;
								str+='<tr>';
									str+='<td>'+result.distConsCuntList[i].district+'</td>';
									str+='<td style="color:red;">'+result.distConsCuntList[i].constiInRed+'</td>';
									str+='<td style="color:orange;">'+result.distConsCuntList[i].constiInOrange+'</td>';
									str+='<td style="color:green;">'+result.distConsCuntList[i].constiInGreen+'</td>';
									str+='<td>'+result.distConsCuntList[i].total+'</td>';
								str+='</tr>';	
							}
							str+='<tr>';
								str+='<td>Total</td>';
								str+='<td>'+redTotalCons+'</td>';
								str+='<td>'+orangeTotalCons+'</td>';
								str+='<td>'+greenTotalCons+'</td>';
								str+='<td>'+totalConsiCons+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	if(result.countList != null && result.countList.length > 0 )
	{
		var total = 0;
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">'+dataArr+'s in Districts</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered m_top10 dataTableCls">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>District Name </th>';
								str+='<th>Count</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							for(var i in result.countList){
								total = total +result.countList[i].count;
								str+='<tr>';
									str+='<td>'+result.countList[i].district+'</td>';
									str+='<td>'+result.countList[i].count+'</td>';
								str+='</tr>';	
							}
							str+='<tr>';
								str+='<td>Total</td>';
								str+='<td>'+total+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	
	if(result.consMandalList != null && result.consMandalList.length > 0 )
	{
		var total = 0;
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">'+dataArr+'s in Constitencies</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered m_top10 dataTableCls">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Constituency Name </th>';
								str+='<th>Count</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							for(var i in result.consMandalList){
								total = total +result.consMandalList[i].count;
								str+='<tr>';
									str+='<td>'+result.consMandalList[i].constituency+'</td>';
									str+='<td>'+result.consMandalList[i].count+'</td>';
								str+='</tr>';	
							}
							str+='<tr>';
								str+='<td>Total</td>';
								str+='<td>'+total+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	if(result.mandalVillageList != null && result.mandalVillageList.length > 0 )
	{
		var total = 0;
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">Panchayats in Mandals</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered m_top10 dataTableCls">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Mandal Name </th>';
								str+='<th>Count</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
							for(var i in result.mandalVillageList){
								total = total +result.mandalVillageList[i].count;
								str+='<tr>';
									str+='<td>'+result.mandalVillageList[i].mandal+'</td>';
									str+='<td>'+result.mandalVillageList[i].count+'</td>';
								str+='</tr>';	
							}
							str+='<tr>';
								str+='<td>Total</td>';
								str+='<td>'+total+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	if(result.distMandalCuntList != null && result.distMandalCuntList.length > 0 )
	{
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">Mandals in '+dataArr+'s</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered m_top10 dataTableCls">';
						str+='<thead class="text-capital">';
							str+='<tr>';
								str+='<th>'+dataArr+'</th>';
								str+='<th style="color:red;">Mandals In Red </th>';
								str+='<th style="color:orange;">Mandals In Orange </th>';
								str+='<th style="color:green;">Mandals In Green </th>';
								str+='<th>Total </th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						redTotalCons = 0;
						orangeTotalCons = 0;
						greenTotalCons = 0;
						totalConsiCons = 0;
							for(var i in result.distMandalCuntList){
								redTotalCons = redTotalCons+result.distMandalCuntList[i].mandalsInRed;
								orangeTotalCons = orangeTotalCons+result.distMandalCuntList[i].mandalsInOrange;
								greenTotalCons = greenTotalCons+result.distMandalCuntList[i].mandalsInGreen;
								totalConsiCons =redTotalCons+ orangeTotalCons+greenTotalCons;
								str+='<tr>';
									str+='<td>'+result.distMandalCuntList[i].district+'</td>';
									str+='<td style="color:red;">'+result.distMandalCuntList[i].mandalsInRed+'</td>';
									str+='<td style="color:orange;">'+result.distMandalCuntList[i].mandalsInOrange+'</td>';
									str+='<td style="color:green;">'+result.distMandalCuntList[i].mandalsInGreen+'</td>';
									str+='<td>'+result.distMandalCuntList[i].total+'</td>';
								str+='</tr>';	
							}
							str+='<tr>';
								str+='<td>Total</td>';
								str+='<td>'+redTotalCons+'</td>';
								str+='<td>'+orangeTotalCons+'</td>';
								str+='<td>'+greenTotalCons+'</td>';
								str+='<td>'+totalConsiCons+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	if(result.distMandalList != null && result.distMandalList.length > 0 )
	{
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">Villages in '+dataArr+'s</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<div class="table-responsive">';
					str+='<table class="table table-bordered m_top10 dataTableCls">';
						str+='<thead class="text-capital">';
							str+='<tr>';
								str+='<th>'+dataArr+'</th>';
								str+='<th style="color:red;">Villages In Red </th>';
								str+='<th style="color:orange;">Villages In Orange </th>';
								str+='<th style="color:green;">Villages In Green </th>';
								str+='<th>Total </th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						redTotalCons = 0;
						orangeTotalCons = 0;
						greenTotalCons = 0;
						totalConsiCons = 0;
							for(var i in result.distMandalList){
								redTotalCons = redTotalCons+result.distMandalList[i].villagesInRed;
								orangeTotalCons = orangeTotalCons+result.distMandalList[i].villagesInOrange;
								greenTotalCons = greenTotalCons+result.distMandalList[i].villagesInGreen;
								totalConsiCons =redTotalCons+ orangeTotalCons+greenTotalCons;
								str+='<tr>';
									str+='<td>'+result.distMandalList[i].district+'</td>';
									str+='<td style="color:red;">'+result.distMandalList[i].villagesInRed+'</td>';
									str+='<td style="color:orange;">'+result.distMandalList[i].villagesInOrange+'</td>';
									str+='<td style="color:green;">'+result.distMandalList[i].villagesInGreen+'</td>';
									str+='<td>'+result.distMandalList[i].total+'</td>';
								str+='</tr>';	
							}
							str+='<tr>';
								str+='<td>Total</td>';
								str+='<td>'+redTotalCons+'</td>';
								str+='<td>'+orangeTotalCons+'</td>';
								str+='<td>'+greenTotalCons+'</td>';
								str+='<td>'+totalConsiCons+'</td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	$("#nregsConsitenBodyId").html(str);
	$(".dataTableCls").dataTable({
		"order": [[ 1, "asc" ]]
	});
	$(".dataTableClsDist").dataTable();
	
}

function getNREGSConsCuntData(locationType,type,globalDivName,menuLocationType,menuLocationId)
{
	$("#nregsConsitenBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		type  : type,
		locationId : menuLocationId,
		sublocaType : locationType
	}
	$.ajax({
		url: 'getMGNregsDistrWiseCount',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildDistrictsPopupDetails(ajaxresp,locationType)
		}
	});
}

$(document).on("click",".detailsCls",function(){
	var locationType = $(this).attr("attr_location");
	var type = $(this).attr("attr_type");
	var heading = $(this).html();
	var menuLocationType = $(this).attr("attr_menuLocationType");
	var menuLocationId = $(this).attr("attr_menuLocationId");
	$("#modalHeadingDivId").html(heading);
	getNREGSConsCuntData(locationType,type,globalDivName,menuLocationType,menuLocationId);
});

$(document).on("click",".overviewPopupCls",function(){
	var locationType = $(this).attr("attr_locationType");
	var type = $(this).attr("attr_type");
	var heading = $(this).html();
	$("#nregsConsitenModalId").modal("show");
	$("#modalHeadingDivId").html(heading);
	var menuLocationType = $(this).attr("attr_menuLocationType");
	var menuLocationId = $(this).attr("attr_menuLocationId");
	if(globalDivName == 'Labour Budget')
		getLabourBudgetClickingOverview(menuLocationType,menuLocationId);
	else
		getNregasPopupOverview(menuLocationType,menuLocationId);
	getNREGSConsCuntData(locationType,type,globalDivName,menuLocationType,menuLocationId);
});

//PopupOverview clicking Block 
function buildPopupOverviewBlock(result,menuLocationType,menuLocationId){
	var str1='';
	str1+='<div class="table-responsive">';
		str1+='<table class="table table-bordered m_top10" >';
			str1+='<tbody>';
				str1+='<tr>';
					str1+='<td>';
						str1+='<div class="col-sm-9">';
							str1+='<div class="media">';
								str1+='<h4 attr_location="district" attr_type="total">Total Districts : '+result.totalDistricts+'</h4>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								if(result.districtsInRed != null && result.districtsInRed > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="district" attr_type="red">Districts in Red : '+result.districtsInRed+'</p>';
								else
									str1+='<p class="media-body" attr_location="district" attr_type="red">Districts in Red : '+result.districtsInRed+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
								if(result.districtsInOrange != null && result.districtsInOrange > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="district" attr_type="orange">Districts in Orange : '+result.districtsInOrange+'</p>';
								else
									str1+='<p class="media-body" attr_location="district" attr_type="orange">Districts in Orange : '+result.districtsInOrange+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.districtsInGreen != null && result.districtsInGreen > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="district" attr_type="green">Districts in Green : '+result.districtsInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_location="district" attr_type="green">Districts in Green : '+result.districtsInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';
					str1+='</td>';
					str1+='<td>';
						str1+='<div class="col-sm-9">';
							str1+='<div class="media">';
								str1+='<h4 attr_location="constituency" attr_type="total">Total Constituencies : '+result.totalConstituencies+'</h4>';
							str1+='</div>';
							str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
							if(result.constituenciesInRed != null && result.constituenciesInRed > 0)
								str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="constituency" attr_type="red">Constituencies in Red : '+result.constituenciesInRed+'</p>';
							else
								str1+='<p class="media-body" attr_location="constituency" attr_type="red">Constituencies in Red : '+result.constituenciesInRed+'</p>';
								str1+='</div>';
							str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							if(result.constituenciesInOrange != null && result.constituenciesInOrange > 0)
								str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="constituency" attr_type="orange">Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
							else
								str1+='<p class="media-body" attr_location="constituency" attr_type="orange">Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
								str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.constituenciesInGreen != null && result.constituenciesInGreen > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="constituency" attr_type="green">Constituencies in Green : '+result.constituenciesInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_location="constituency" attr_type="green">Constituencies in Green : '+result.constituenciesInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';	
					str1+='</td>';
					str1+='<td>';
						str1+='<div class="col-sm-9">';
							str1+='<div class="media">';
								str1+='<h4 attr_location="mandal" attr_type="total">Total Mandals : '+result.totalMandals+'</h4>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								if(result.mandalsInRed != null && result.mandalsInRed > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="mandal" attr_type="red">Mandals in Red : '+result.mandalsInRed+'</p>';
								else
									str1+='<p class="media-body" attr_location="mandal" attr_type="red">Mandals in Red : '+result.mandalsInRed+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
								if(result.mandalsInOrange != null && result.mandalsInOrange > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="mandal" attr_type="orange">Mandals in Orange : '+result.mandalsInOrange+'</p>';
								else
									str1+='<p class="media-body" attr_location="mandal" attr_type="orange">Mandals in Orange : '+result.mandalsInOrange+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.mandalsInGreen != null && result.mandalsInGreen > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="mandal" attr_type="green">Mandals in Green : '+result.mandalsInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_location="mandal" attr_type="green">Mandals in Green : '+result.mandalsInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';	
					str1+='</td>';
					str1+='<td>';
						str1+='<div class="col-sm-9">';
							str1+='<div class="media">';
								str1+='<h4>Total Villages : '+result.totalVillages+'</h4>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								if(result.villagesInRed != null && result.villagesInRed > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="panchayat" attr_type="red">Villages in Red : '+result.villagesInRed+'</p>';
								else
									str1+='<p class="media-body" attr_location="panchayat" attr_type="red">Villages in Red : '+result.villagesInRed+'</p>';
							str1+='</div>';
							str1+=' <div class="media">';
							   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							   if(result.villagesInOrange != null && result.villagesInOrange > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="panchayat" attr_type="orange">Villages in Orange : '+result.villagesInOrange+'</p>';
								else
									str1+='<p class="media-body " attr_location="panchayat" attr_type="orange">Villages in Orange : '+result.villagesInOrange+'</p>';
							str1+='</div>';
							str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
								if(result.villagesInGreen != null && result.villagesInGreen > 0)
									str1+='<p class="media-body detailsCls" attr_menuLocationType="'+menuLocationType+'" attr_menuLocationId="'+menuLocationId+'" attr_location="panchayat" attr_type="green">Villages in Green : '+result.villagesInGreen+'</p>';
								else
									str1+='<p class="media-body" attr_location="panchayat" attr_type="green">Villages in Green : '+result.villagesInGreen+'</p>';
							str1+='</div>';
						str1+='</div>';	
					str1+='</td>';
				str1+='</tr>';
			str1+='</tbody>';
		str1+='</table>';
	str1+='</div>';
	$("#nregsOverviewBodyId").html(str1);
	
} 

function getLabourBudgetClickingOverview(menuLocationType,menuLocationId)
{
	$("#nregsOverviewBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate,
        locationType : menuLocationType,
        locationId : menuLocationId
	}
	$.ajax({
		url: 'getLabourBudgetOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildPopupOverviewBlock(ajaxresp,menuLocationType,menuLocationId);
		}
	});
}

function getNregaLevelsWiseDataForFAPerformance(divIdd,locationTypeNew,theadArr,menuLocationType,menuLocationId)
{
	$("#"+divIdd).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate 	: '2017-05-30',//glEndDate,
		locationType: locationTypeNew,
		divType : globalDivName,
		locationId : menuLocationId
	}
	$.ajax({
		url: 'getNregaLevelsWiseDataForFAPerformance',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationTypeNew == "state"){
							str+='<td class="text-capital">'+locationTypeNew+'</td>';
						}
						else if(locationTypeNew == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationTypeNew == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationTypeNew == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationTypeNew == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						str+='<td>'+ajaxresp[i].target+'</td>';
						if((globalDivName == 'Mulbery' || globalDivName == 'Silk worm' || globalDivName == 'Cattle drinking water trough' || globalDivName == 'Raising of Perinnial Fodder') && locationTypeNew == "state"){
							str+='<td>'+ajaxresp[i].sanctionedTarget+'</td>';
							str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						}
						if((globalDivName == 'Fish Ponds' || globalDivName == 'Fish Drying Platforms') && (locationTypeNew == "state" || locationTypeNew == "district")){
							str+='<td>'+ajaxresp[i].sanctionedTarget+'</td>';
							str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						}
						str+='<td>'+ajaxresp[i].grounded+'</td>';
						str+='<td>'+ajaxresp[i].notGrounded+'</td>';
						str+='<td>'+ajaxresp[i].inProgress+'</td>';
						str+='<td>'+ajaxresp[i].completed+'</td>';
						if(ajaxresp[i].percentage < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 50 && ajaxresp[i].percentage < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].percentage+'</td>';
						}
						//str+='<td>'+ajaxresp[i].percentage+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationTypeNew);
		}
	});
}
function getNregaLevelsWiseData(divIdd,locationTypeNew,theadArr,menuLocationType,menuLocationId)
{
	$("#"+divIdd).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationTypeNew
	}
	$.ajax({
		url: 'getNregaLevelsWiseData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationTypeNew == "state"){
							str+='<td class="text-capital">'+locationTypeNew+'</td>';
						}
						else if(locationTypeNew == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationTypeNew == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationTypeNew == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationTypeNew == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						str+='<td>'+ajaxresp[i].target+'</td>';
						if((globalDivName == 'Mulbery' || globalDivName == 'Silk worm' || globalDivName == 'Cattle drinking water trough' || globalDivName == 'Raising of Perinnial Fodder') && locationTypeNew == "state"){
							str+='<td>'+ajaxresp[i].sanctionedTarget+'</td>';
							str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						}
						if((globalDivName == 'Fish Ponds' || globalDivName == 'Fish Drying Platforms') && (locationTypeNew == "state" || locationTypeNew == "district")){
							str+='<td>'+ajaxresp[i].sanctionedTarget+'</td>';
							str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						}
						str+='<td>'+ajaxresp[i].grounded+'</td>';
						str+='<td>'+ajaxresp[i].notGrounded+'</td>';
						str+='<td>'+ajaxresp[i].inProgress+'</td>';
						str+='<td>'+ajaxresp[i].completed+'</td>';
						if(ajaxresp[i].percentage < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 50 && ajaxresp[i].percentage < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].percentage+'</td>';
						}
						//str+='<td>'+ajaxresp[i].percentage+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationTypeNew);
		}
	});
}

function getNregasOverview(projectDivId,menuLocationType,menuLocationId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate,
		divType : globalDivName,
		locationType : menuLocationType,
		locationId : menuLocationId
	}
	$.ajax({
		url: 'getNregasOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNregasOverViewBlock(ajaxresp,projectDivId,menuLocationType,menuLocationId);
		}
	});
}

function getNregasPopupOverview(menuLocationType,menuLocationId)
{
	$("#nregsOverviewBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate,
		divType : globalDivName,
		locationType : menuLocationType,
		locationId : menuLocationId
	}
	$.ajax({
		url: 'getNregasOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildPopupOverviewBlock(ajaxresp,menuLocationType,menuLocationId);
		}
	});
}

function getNregaLevelsWiseDataFrNewCalls(divIdd,locationType,menuLocationType,menuLocationId,divId)
{
	$("#"+divIdd).html(spinner);
	var theadArr = [locationType,'Target','Achivement','Percentage'];
	if(divId == 'Timely Payment')
	{
		theadArr = [locationType,'Target Percentage','Achivement Percentage','Achieved Percentage'];
		if(locationType == "constituency")
			theadArr = ["district",locationType,'Target Percentage','Achivement Percentage','Achieved Percentage'];
		else if(locationType == "mandal")
			theadArr = ["district","constituency",locationType,'Target Percentage','Achivement Percentage','Achieved Percentage'];
		else if(locationType == "panchayat")
			theadArr = ["district","constituency","mandal",locationType,'Target Percentage','Achivement Percentage','Achieved Percentage'];
	}else{
		if(locationType == "constituency")
			theadArr = ["district",locationType,'Target','Achivement','Percentage'];
		else if(locationType == "mandal")
			theadArr = ["district","constituency",locationType,'Target','Achivement','Percentage'];
		else if(locationType == "panchayat")
			theadArr = ["district","constituency","mandal",locationType,'Target','Achivement','Percentage'];
	}
	
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationType
	}
	$.ajax({
		url: 'getNregaLevelsWiseDataFrNewCalls',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationType == "state"){
							str+='<td class="text-capital">'+locationType+'</td>';
						}
						else if(locationType == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationType == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationType == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						str+='<td>'+ajaxresp[i].target+'</td>';
						str+='<td>'+ajaxresp[i].achivement+'</td>';
						if(ajaxresp[i].percentage < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 50 && ajaxresp[i].percentage < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].percentage+'</td>';
						}
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataFrAgriculture(divIdd,locationType,menuLocationType,menuLocationId)
{
	$("#"+divIdd).html(spinner);
	var theadArr = [locationType,'Total Expenditure','Expenditure on Agriculture & Allied Activities','ACHIVEMENT PERCENTAGE'];
	if(locationType == "constituency")
		theadArr = ["district",locationType,'Total Expenditure','Expenditure on Agriculture & Allied Activities','ACHIVEMENT PERCENTAGE'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Total Expenditure','Expenditure on Agriculture & Allied Activities','ACHIVEMENT PERCENTAGE'];
	else if(locationType == "panchayat")
		theadArr = ["district","constituency","mandal",locationType,'Total Expenditure','Expenditure on Agriculture & Allied Activities','ACHIVEMENT PERCENTAGE'];
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationType
	}
	$.ajax({
		url: 'getNregaLevelsWiseDataFrAgriculture',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationType == "state"){
							str+='<td class="text-capital">'+locationType+'</td>';
						}
						else if(locationType == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationType == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationType == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						str+='<td>'+ajaxresp[i].target+'</td>';
						str+='<td>'+ajaxresp[i].completed+'</td>';
						if(ajaxresp[i].achivement < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].achivement+'</td>';
						}else if(ajaxresp[i].achivement >= 50 && ajaxresp[i].achivement < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].achivement+'</td>';
						}else if(ajaxresp[i].achivement >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].achivement+'</td>';
						}
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataFrHorticulture(divIdd,locationType,menuLocationType,menuLocationId)
{
	$("#"+divIdd).html(spinner);
	 var theadArr = [locationType,'Target','Sanctioned Area (in Acres)','Sanctioned Percentage','Pitting  Area (in Acres)','Planting  Area (in Acres)','Achievement Percentage'];
	if(locationType == "constituency")
		theadArr = ["district",locationType,'Sanctioned Area (in Acres)','Pitting  Area (in Acres)','Planting  Area (in Acres)','Achievement Percentage'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Sanctioned Area (in Acres)','Pitting  Area (in Acres)','Planting  Area (in Acres)','Achievement Percentage'];
	else if(locationType == "panchayat")
		theadArr = ["district","constituency","mandal",locationType,'Sanctioned Area (in Acres)','Pitting  Area (in Acres)','Planting  Area (in Acres)','Achievement Percentage'];
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationType
	}
	$.ajax({
		url: 'getNregaLevelsWiseDataFrHorticulture',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationType == "state"){
							str+='<td class="text-capital">'+locationType+'</td>';
						}
						else if(locationType == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationType == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationType == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						if(locationType == "state" || locationType == "district")
							str+='<td>'+ajaxresp[i].targetACRES+'</td>';
						str+='<td>'+ajaxresp[i].sanctionedACRES+'</td>';
						if(locationType == "state" || locationType == "district")
							str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						str+='<td>'+ajaxresp[i].pittingArea+'</td>';
						str+='<td>'+ajaxresp[i].plantingArea+'</td>';
						if(ajaxresp[i].pencentageOfPlanting < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].pencentageOfPlanting+'</td>';
						}else if(ajaxresp[i].pencentageOfPlanting >= 50 && ajaxresp[i].pencentageOfPlanting < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].pencentageOfPlanting+'</td>';
						}else if(ajaxresp[i].pencentageOfPlanting >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].pencentageOfPlanting+'</td>';
						}
						//str+='<td>'+ajaxresp[i].pencentageOfPlanting+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataFrAvenue(divIdd,locationType,menuLocationType,menuLocationId)
{
	$("#"+divIdd).html(spinner);
	 var theadArr = [locationType,'Target','Sanctioned Area (in Kms)','Sanctioned Percentage','Pitting  Area (in Kms)','Planting  Area (in Kms)','Achievement Percentage'];
	if(locationType == "constituency")
		theadArr = ["district",locationType,'Sanctioned Area (in Kms)','Pitting  Area (in Kms)','Planting  Area (in Kms)','Achievement Percentage'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Sanctioned Area (in Kms)','Pitting  Area (in Kms)','Planting  Area (in Kms)','Achievement Percentage'];
	else if(locationType == "panchayat")
		theadArr = ["district","constituency","mandal",locationType,'Sanctioned Area (in Kms)','Pitting  Area (in Kms)','Planting  Area (in Kms)','Achievement Percentage'];
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationType
	}
	$.ajax({
		url: 'getNregaLevelsWiseDataFrAvenue',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationType == "state"){
							str+='<td class="text-capital">'+locationType+'</td>';
						}
						else if(locationType == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationType == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationType == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						if(locationType == "state" || locationType == "district")
							str+='<td>'+ajaxresp[i].targetKMS+'</td>';
						str+='<td>'+ajaxresp[i].sanctionedKMS+'</td>';
						if(locationType == "state" || locationType == "district")
							str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						str+='<td>'+ajaxresp[i].pittingKMS+'</td>';
						str+='<td>'+ajaxresp[i].plantingKMS+'</td>';
						if(ajaxresp[i].pencentageOfPlanting < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].pencentageOfPlanting+'</td>';
						}else if(ajaxresp[i].pencentageOfPlanting >= 50 && ajaxresp[i].pencentageOfPlanting < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].pencentageOfPlanting+'</td>';
						}else if(ajaxresp[i].pencentageOfPlanting >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].pencentageOfPlanting+'</td>';
						}
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}


//var overViewArr = ['Labour Budget','Farm Ponds','IHHL','Vermi Compost','CC Roads','Anganwadi','Gram Panchayat Buildings','Mandal buildings','NTR 90 Days','Production of Bricks','Mulbery','Silk worm','Cattle drinking water trough','Raising of Perinnial Fodder','Solid Waste Management','Play Fields','Burial Grounds','Fish Drying Platforms','Fish Ponds','Agriculture Activities','Average Wage','Avg days of emp per HH','HH Comp 100 days','Timely Payments','Horticulture','Avenue'];
var overViewArr = ['Labour Budget','Farm Ponds','IHHL','Vermi Compost','Solid Waste Management','Burial Grounds','Play Fields','Agriculture Activities','Average Wage','Average Days of Employment','HH Completed 100 Days','Timely Payment','CC Roads','Anganwadi Buildings','GP Buildings','Mandal Buildings','NTR 90 Days','Production of Bricks','Mulbery','Silk Worms','Cattle Drinking Water Troughs','Raising of Perinnial Fodders','Horticulture','Avenue','Fish Ponds','Fish Drying Platforms','Nurseries','Payments','FAperformance'];
buildNREGSProjectsOverview(overViewArr,'')
for(var i in overViewArr)
{
	$("[overview-block='"+overViewArr[i]+"']").append(spinner);
	if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
		getNREGSProjectsAbstractNew(overViewArr[i],'state',"0",'',2);
	else
		getNREGSAbstractDataByType(overViewArr[i],'state',"0",'',2);
}
/*for(var i in overViewArr){
	getNREGSProjectsOverviewArr(overViewArr[i]);
}

function getNREGSProjectsOverviewArr(parameter)
{
	$("#projectsOverview").html(spinner);
	var json = {
			year:"2017",
			fromDate:glStartDate,
			toDate:glEndDate,
			parameter : parameter
		}
	$.ajax({
		url: 'getNREGSProjectsOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNREGSProjectsOverview(ajaxresp,'');
			
		}
	});
}*/

function getNregaLevelsWiseDataForCCRoads(divIdd,locationType,menuLocationType,menuLocationId)
{
	$("#"+divIdd).html(spinner);
	var theadArr = [locationType,'Target Length (in KMS)','Sanctioned Estimate Cost','Sanctioned Length (in KMS)','Percentage of Sanctioned Length','Expenditure','Completed Length (in KMS)','ACHIVEMENT PERCENTAGE'];
	if(locationType == "constituency")
		theadArr = ["district",locationType,'Sanctioned Estimate Cost','Sanctioned Length (in KMS)','Expenditure','Completed Length (in KMS)','ACHIVEMENT PERCENTAGE'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Sanctioned Estimate Cost','Sanctioned Length (in KMS)','Expenditure','Completed Length (in KMS)','ACHIVEMENT PERCENTAGE'];
	else if(locationType == "panchayat")
		theadArr = ["district","constituency","mandal",locationType,'Sanctioned Estimate Cost','Sanctioned Length (in KMS)','Expenditure','Completed Length (in KMS)','ACHIVEMENT PERCENTAGE'];
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: menuLocationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationType
	}
	$.ajax({
		url: 'getNregaLevelsWiseDataForCCRoads',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						if(locationType == "state"){
							str+='<td class="text-capital">'+locationType+'</td>';
						}
						else if(locationType == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationType == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationType == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}
						if(locationType == "state" || locationType == "district")
							str+='<td>'+ajaxresp[i].targetKMS+'</td>';
						str+='<td>'+ajaxresp[i].sanctionedAmount+'</td>';
						str+='<td>'+ajaxresp[i].sanctionedKMS+'</td>';
						if(locationType == "state" || locationType == "district")
							str+='<td>'+ajaxresp[i].sanctionedPerventage+'</td>';
						str+='<td>'+ajaxresp[i].expenditureAmount+'</td>';
						str+='<td>'+ajaxresp[i].completedKMS+'</td>';
						if(ajaxresp[i].percentage < 50){
							str+='<td style="background-color:#FF0000">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 50 && ajaxresp[i].percentage < 80){
							str+='<td style="background-color:#FFBA00">'+ajaxresp[i].percentage+'</td>';
						}else if(ajaxresp[i].percentage >= 80)
						{
							str+='<td style="background-color:#00AF50">'+ajaxresp[i].percentage+'</td>';
						}
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataForTimelyPayments(divIdd,locationType,menuLocationType,menuLocationId)
{
	$("#"+divIdd).html(spinner);
	var theadArr = [locationType,'FTO_NOT_GEN_CNT','FTO_NOT_GEN_AMT','FTO_NOT_UPLOAD_CNT','FTO_NOT_UPLOAD_AMT','FTO_NOT_SENT_CNT','FTO_NOT_SENT_AMT','REJECT_CNT','REJECT_AMT','PENDING_RESPONSE_CNT','PENDING_RESPONSE_AMT'];
	/*if(locationType == "constituency")
		theadArr = ["district",locationType,'Sanctioned Estimate Cost','Sanctioned Length (in KMS)','Expenditure','Completed Length (in KMS)','ACHIVEMENT PERCENTAGE'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Sanctioned Estimate Cost','Sanctioned Length (in KMS)','Expenditure','Completed Length (in KMS)','ACHIVEMENT PERCENTAGE'];
	else if(locationType == "panchayat")
		theadArr = ["district","constituency","mandal",locationType,'Sanctioned Estimate Cost','Sanctioned Length (in KMS)','Expenditure','Completed Length (in KMS)','ACHIVEMENT PERCENTAGE'];*/
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locationType,
		divType : globalDivName,
		locationId : menuLocationId,
		sublocaType : locationType
	}
	$.ajax({
		url: 'getNregaLevelsWiseDataForTimelyPayments',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					str+='<tr>';
						/*if(locationType == "state"){
							str+='<td class="text-capital">'+locationType+'</td>';
						}
						else if(locationType == "district"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						}
						else if(locationType == "constituency"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						}
						else if(locationType == "mandal"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						}
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}*/
						if(locationType == "district")
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						else if(locationType == "constituency")
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						else if(locationType == "mandal")
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						else if(locationType == "panchayat")
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						
						str+='<td>'+ajaxresp[i].ftoNotGenCnt+'</td>';
						str+='<td>'+ajaxresp[i].ftoNotGenAmt+'</td>';
						str+='<td>'+ajaxresp[i].ftoNotUploadCnt+'</td>';
						str+='<td>'+ajaxresp[i].ftoNotUploadAmt+'</td>';
						str+='<td>'+ajaxresp[i].ftoNotSentCnt+'</td>';
						str+='<td>'+ajaxresp[i].ftoNotSentAmt+'</td>';
						str+='<td>'+ajaxresp[i].rejectCnt+'</td>';
						str+='<td>'+ajaxresp[i].rejectAmt+'</td>';
						str+='<td>'+ajaxresp[i].pendingResponseCnt+'</td>';
						str+='<td>'+ajaxresp[i].pendingResponseAmt+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

/* function getNregaParliamentData(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: "constituency",
		divType : globalDivName
	}
	$.ajax({
		url: 'getNregaParliamentData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var str = '';
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					if($windowWidth < 768)
						{
							str+='<div class="table-responsive">';
						}
						str+='<table class="table table-bordered">';
						if(globalDivName){
							str+='<thead class="text-capital">';
								str+='<th>Parliament Name <th>';
								str+='<th>Target<th>';
								str+='<th>Grounded<th>';
								str+='<th>Not-Grounded<th>';
								str+='<th>InProgress<th>';
								str+='<th>Completed<th>';
								str+='<th>Percentage<th>';
							str+='</thead>';
							str+='<tbody>';
								str+='<tr>';
									str+='<td class="text-capital">'+ajaxresp[i].parliamentName+'</td>';
									str+='<td>'+ajaxresp[i].target+'</td>';
									str+='<td>'+ajaxresp[i].grounded+'</td>';
									str+='<td>'+ajaxresp[i].notGrounded+'</td>';
									str+='<td>'+ajaxresp[i].inProgress+'</td>';
									str+='<td>'+ajaxresp[i].completed+'</td>';
									str+='<td>'+ajaxresp[i].percentage+'</td>';
								str+='</tr>';
							str+='</tbody>';
						}else{
							str+='<thead class="text-capital">';
								str+='<th>Parliament Name <th>';
								str+='<th>Target<th>';
								str+='<th>Grounded<th>';
								str+='<th>Not-Grounded<th>';
								str+='<th>InProgress<th>';
								str+='<th>Completed<th>';
								str+='<th>Percentage<th>';
							str+='</thead>';
							str+='<tbody>';
								str+='<tr>';
									str+='<td class="text-capital">'+ajaxresp[i].parliamentName+'</td>';
									str+='<td>'+ajaxresp[i].target+'</td>';
									str+='<td>'+ajaxresp[i].grounded+'</td>';
									str+='<td>'+ajaxresp[i].notGrounded+'</td>';
									str+='<td>'+ajaxresp[i].inProgress+'</td>';
									str+='<td>'+ajaxresp[i].completed+'</td>';
									str+='<td>'+ajaxresp[i].percentage+'</td>';
								str+='</tr>';
							str+='</tbody>';
						}
							
						str+='</table>';
						if($windowWidth < 768)
						{
							tableView+='</div>';
						}
				}
			}
		}
	});
} */

//ProjectOverviewNew
//getNREGSProjectsAbstractNew();
function getNREGSProjectsAbstractNew(type,locType,locId,blockName,levelId)
{
	$("#nregsOverviewBodyId").html(spinner);
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
			//if(ajaxresp != null && ajaxresp.length > 0){
				buildNREGSAbstractDataByType(type,ajaxresp,blockName,locId,locType,levelId)
			//}
		}
	});
}
function getNREGSAbstractDataByType(type,locType,locId,blockName,levelId)
{

	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		type : type,
		locationType: locType,
		locationId : locId
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
			//if(ajaxresp != null && ajaxresp.length > 0){
				buildNREGSAbstractDataByType(type,ajaxresp,blockName,locId,locType,levelId)
			//}
			
		}
	});
}
function buildNREGSAbstractDataByType(type,result,blockName,locId,locType,levelId)
{
	$("[overview-block='"+type+"']").removeClass("panel-block-white");
	var str='';
	
	$("[overview-block='"+type+"']").attr("attr_levelId",levelId);
	$("[overview-block='"+type+"']").attr("attr_locationId",locId);
	if(result != null && result.length > 0)
	{
		if(result[0].parameter == 'Payments')
		{
			if(locType != 'district')
			{
				str+='<div class="panel-black-white panel-block-white-high text-center" overview-district="'+type+'">';
					if(type.length > 12)
					{
						str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+type+'">'+type.substr(0,12)+'..</h4>';
					}else{
						str+='<h4 class="panel-block-white-title text-capitalize text-center">'+type+'</h4>';
					}
					str+='<div class="row">';
						str+='<div class="col-sm-6">';
							str+='<small>Payment Response Count</small> <h2>'+result[0].pendingresponsecnt+'</h2>';
							str+='<small>FTO Not Uploaded Count</small> <h2>'+result[0].ftonotuploadcnt+'</h2>';
						str+='</div>';
						str+='<div class="col-sm-6">';
							str+='<small>FTO Not Generated Count</small> <h2>'+result[0].ftonotgencnt+'</h2>';
							str+='<small>FTO Not Sent Count</small> <h2>'+result[0].ftonotsentcnt+'</h2>';
						str+='</div>';
						str+='<div class="col-sm-12">';
							str+='<small>Reject Count '+result[0].rejectcnt+'</small>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			}else{
				str+='<div></div>';
			}
		}
		
		//$("[overview-block='Payments'']").closest(".col-sm-2").removeClass("col-sm-2").addClass("col-sm-4");
	}
	if(result[0] != null && result[0].parameter != 'Payments')
	{
		if(result[0].percentage < 50)
		{
			str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
		}else if(result[0].percentage >= 50 && result[0].percentage < 80)
		{
			str+='<div class="panel-black-white panel-block-white-medium text-center" overview-district="'+type+'">';
			
		}else if(result[0].percentage >= 80)
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
			if(result[0].percentage != null && result[0].percentage.length > 0)
			{
				str+='<h1 class="text-center">'+result[0].percentage+'<small>%</small>';
			}else{
				str+='<h1 class="text-center">0<small>%</small>';
			}
				
			if(result[0].percentage < 50)
			{
				str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
			}else if(result[0].percentage >= 50 && result[0].percentage < 80)
			{
				str+='<small><i class="fa fa-arrows-v"></i></small></h1>';
			}else if(result[0].percentage >= 80)
			{
				str+='<small><i class="fa fa-long-arrow-up"></i></small></h1>';
			}
			str+='<div class="row">';
				str+='<div class="col-sm-6 text-center">';
					str+='<label>Target</label>';
					if(result[0].target != null && result[0].target.length > 0)
					{
						if(result[0].parameter == 'Labour Budget' && locType != 'district')
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
					if(result[0].completed != null && result[0].completed.length > 0)
					{
						if(result[0].parameter == 'Labour Budget' && locType != 'district')
						{
							str+='<h4>'+result[0].completed+'L</h4>';
						}else if(result[0].parameter == 'Timely Payments'){
							str+='<h4>'+result[0].completed+'%</h4>';
						}else{
							str+='<h4>'+result[0].completed+'</h4>';
						}
						
					}else{
						str+='<h4>0</h4>';
					}
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}else if(result[0] != null && result[0].parameter == 'Payments')
	{
		str+='<div></div>';
	}else{
		str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
			if(type.length > 12)
			{
				str+='<h4 class="panel-block-white-title text-capitalize text-center" title="'+type+'">'+type.substr(0,12)+'..</h4>';
			}else{
				str+='<h4 class="panel-block-white-title text-capitalize text-center">'+type+'</h4>';
			}
			str+='<small class="text-center">Achieved</small>';
			str+='<h1 class="text-center">0<small>%</small>';
			str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
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
	
	
	if(locType == 'district')
	{
		if(result[1] != null)
		{
			if(result[1].percentage < 50)
			{
				str+='<div class="panel-black-white panel-block-white-low text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
			}else if(result[1].percentage >= 50 && result[1].percentage < 80)
			{
				str+='<div class="panel-black-white panel-block-white-medium text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
				
			}else if(result[1].percentage >= 80)
			{
				str+='<div class="panel-black-white panel-block-white-high text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
			}
				str+='<small class="panel-block-white-title text-capitalize text-center">STATE LEVEL - ACHIEVED</small>';
				if(result[1].percentage != null && result[1].percentage.length > 0)
				{
					str+='<h2 class="text-center">'+result[1].percentage+'</h2>';
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
	$("[overview-block='"+type+"']").html(str);
	$(".panel-block-white-title").tooltip();
	if(type == blockName)
	{
		$("[overview-block='"+blockName+"']").trigger("click");
	}
}
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
	var levelId = $(this).attr("attr_levelIdValue");
	$("#selectedName").attr("attr_levelid",levelId);
	$("#selectedName").attr("attr_id",locId)
	if(levelId == 3)
	{
		
		for(var i in overViewArr)
		{
			if(overViewArr[i] != 'Payments')
			{
				$("[overview-block='"+overViewArr[i]+"']").html(spinner);
			}else{
				$("[overview-block='"+overViewArr[i]+"']").html(' ');
			}
			if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
			{

				getNREGSProjectsAbstractNew(overViewArr[i],'district',locId,blockName,levelId);
			}
			else if(overViewArr[i] != 'Payments'){
				getNREGSAbstractDataByType(overViewArr[i],'district',locId,blockName,levelId);
			}
				
		}
	}else if(levelId == 2)
	{
		for(var i in overViewArr)
		{
			$("[overview-block='"+overViewArr[i]+"']").html(spinner);
			if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
				getNREGSProjectsAbstractNew(overViewArr[i],'state',"0",blockName,levelId);
			else
				getNREGSAbstractDataByType(overViewArr[i],'state',"0",blockName,levelId);
		}
	}
	
});

var stateArr = [{'name':'Andhra Pradesh','id':1}];
collapseMenu(1,stateArr,'multi-level-selection-menu');
function getAllNregaSubLocationDetails(divId,levelId,locationScopeId,type){
	$("."+divId).html(spinner);
	//var type = 'constituency' //district to constituency (only consider type like this)
	var json = {
		searchLevelId		: levelId,
		searchLevelValue	: locationScopeId,
		type 				: type		  
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
					if(levelIdValue == 2)
					{
						collapse+='<a role="button" style="height:10px;width:10px;display:inline-block;" attr_levelIdValue="'+levelIdValue+'" attr_distId="'+resultArr[i].type+'" attr_levelId="'+id+'" attr_id="'+resultArr[i].id+'" attr_targetId="collapseMenu'+resultArr[i].id+'Id"  class="panelCollapseIcon panelCollapseIconClick collapsed" data-toggle="collapse" data-parent="#accordion'+[id]+'" href="#collapse'+resultArr[i].id+'" aria-expanded="true" aria-controls="collapse'+resultArr[i].id+'">&nbsp;</a>';
					}
					collapse+='<span style="padding-left:10px;cursor:pointer;" class="menuDataCollapse"  attr_levelIdValue="'+levelIdValue+'" attr_levelId="'+id+'" attr_id="'+resultArr[i].type+'" attr_targetId="collapseMenu'+resultArr[i].id+'Id" >'+resultArr[i].name+'</span>';
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
		type='constituency';
	}
	getAllNregaSubLocationDetails(buildId,levelId,locationScopeId,type)
});
$(".multi-level-selection-menu").hide();
$(document).on("click",function(){
	$(".multi-level-selection-menu").hide();
});
$(document).on("click","#selectedName",function(e){
	e.stopPropagation();
	$(".multi-level-selection-menu").show();
});


$(document).on("click",".cuntCls",function(){
	$("#LabBudgtPanExBodyId").html('');
	$("#nregsPanExpModalId").modal("show");
	$("#LabBudgtPanExBodyId").html(spinner);
	var range = $(this).attr("attr_range");
	var locationType = $(this).attr("attr_location_type");
	var locationId = $(this).attr("attr_loaction_id");
	var rangeArr;
	var fromRange;
	var toRange;
	if(range == "Below 1"){
		 fromRange = 0;
		 toRange = 1;
	}else if(range == "Above 400"){
		fromRange = 400;
		 toRange = 5000;
	}else if(range == "0"){
		fromRange = 0;
		 toRange = 0;
	}else{
		rangeArr = range.split("-");
		 fromRange = rangeArr[0];
		 toRange = rangeArr[1];
	}
		$("#larBudExpHeadingId").html('No of Panchayaties Vs Expenditure In Lakhs('+range+')');
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locationType,
		locationId : locationId,
		fromRange : fromRange,
		toRange : toRange
		
	}
	$.ajax({
		url : "getNregaLabourBudgetPanchatVsExpData",     
		data : JSON.stringify(json),
		type : "POST",  
		dataTypa : 'json',   
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success : function(result){   
			buildLabourBudgetPanExpData(result);
		}
	});
});

function buildLabourBudgetPanExpData(result){
	var str='';
	str+='<table class="table table-bordered" id="larBudExpTableId">';
		str+='<thead>';
			str+='<th>District</th>';
			str+='<th>Assembly</th>';
			str+='<th>Mandal</th>';
			str+='<th>Panchayat</th>';
			str+='<th>Total Expenditure</th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].district+'</td>';
				str+='<td>'+result[i].constituency+'</td>';
				str+='<td>'+result[i].mandal+'</td>';
				str+='<td>'+result[i].panchayat+'</td>';
				str+='<td>'+result[i].totalExpenditure+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
	str+='</table>';
	$("#LabBudgtPanExBodyId").html(str);
	$("#larBudExpTableId").dataTable();
}

$(document).on("click","#selectedName",function(){
	$(".arrowIconChanged").parent().find('i.fa').removeClass("fa-chevron-down");
	$(".arrowIconChanged").parent().find('i.fa').addClass("fa-chevron-up");
});