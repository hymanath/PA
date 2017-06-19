var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
var glEndDate = moment().add(10, 'years').endOf('year').format("DD/MM/YYYY");
onLoadCalls();
function onLoadCalls()
{
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
		$("[overview-block]").removeClass("active");
		$(this).addClass("active");
		var projectDivId = $(this).attr("overview-block");
		overviewData(projectDivId)
		projectData(projectDivId)
		$('html,body').animate({
			scrollTop: $("#projectOverviewBlock").offset().top},
        'slow');
	});
	
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
	$(".chosenSelect").chosen({width:'100%'})
	$("#dateRangePickerAUM").daterangepicker({
			opens: 'left',
			startDate: glStartDate,
			endDate: glEndDate,
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
	var dates= $("#dateRangePickerAUM").val();
	var pickerDates = glStartDate+' - '+glEndDate
	if(dates == pickerDates)
	{
		$("#dateRangePickerAUM").val('All');
	}
	$('#dateRangePickerAUM').on('apply.daterangepicker', function(ev, picker) {
		
		$(".switch-btn li").removeClass("active");
		$(".switch-btn li:first-child").addClass("active");
		$('[role="tablist"] li:first-child a').trigger('click');
		$('#tabCons a[href="#consLevelGraph"]').trigger('click');
		glStartDate = picker.startDate.format('DD/MM/YYYY')
		glEndDate = picker.endDate.format('DD/MM/YYYY')
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePickerAUM").val('All');
		}
		getNREGSProjectsOverview();
		$("#projectOverviewBlock,#projectData").html('');
	});
	getNREGSProjectsOverview()
}

function projectData(divId)
{
	var collapse='';
	var dataArr = ['state','district','constituency','mandal']
	collapse+='<section>';
		collapse+='<div class="row">';
			collapse+='<div class="col-sm-12">';
				for(var i in dataArr)
				{
					collapse+='<div class="panel-group" id="accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" role="tablist" aria-multiselectable="true">';
						collapse+='<div class="panel panel-default panel-black">';
							collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+dataArr[i]+'">';
								collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
									collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level overview - '+divId+'</h4>';
								collapse+='</a>';
							collapse+='</div>';
							collapse+='<div id="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
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
	for(var i in dataArr)
	{
		var theadArr = [dataArr[i],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		var tableId = divId.replace(/\s+/g, '')+''+dataArr[i];
		$("#"+tableId).html(spinner);
		if(divId == 'Labour Budget'){
			getNREGSLabBugdtLelwiseData(tableId,dataArr[i]);
		}
		else if(divId == "Farm Pond"){
			getNREGSFarmPondLelwiseData(tableId,dataArr[i],theadArr)
		}
		else if(divId == "IHHL"){
			getNREGSIHHLLelwiseData(tableId,dataArr[i],theadArr)
		}
		else if(divId == "VERMI"){
			getNregsVermiData(tableId,dataArr[i],theadArr);
		}
		else if(divId == "Gram Panchayat Buildings"){
			getNREGSGPBuildingLelwiseData(tableId,dataArr[i],theadArr);
		}else if(divId == "NTR Jala Siri"){
			getNregsNtrsData(tableId,dataArr[i],theadArr);
		}else if(divId == "CC Roads"){
			getCCRoadsData(tableId,dataArr[i],theadArr);
		}else if(divId == "Anganwadi"){
			getNregsAnganwadiData(tableId,dataArr[i],theadArr);
		}else if(divId == "Mandal buildings"){
			getNregsMandalBuildingData(tableId,dataArr[i],theadArr);
		}else if(divId == "NTR 90 Days"){
			
		}else if(divId == "Production of Bricks"){
			
		}else if(divId == "Mulbery"){
			
		}else if(divId == "Silk worm"){
			
		}else if(divId == "Cattle drinking water trough"){
			
		}else if(divId == "Raising of Perinnial Fodder"){
			
		}
	}
}
function overviewData(divId)
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
	
	if(divId == 'Labour Budget')
	{
		getNREGSLabourBudgetOverview(divId);
	}else if(divId == 'Farm Pond')
	{
		getNREGSFarmPondOverview(divId);
	}else if(divId == 'VERMI')
	{
		getNregsVermiOverview(divId);
	}else if(divId == "IHHL")
	{
		getNREGSIHHLOverview(divId)
	}else if(divId == "NTR Jala Siri")
	{
		NtrsOverview(divId)
	}else if(divId == "Anganwadi")
	{
		getNregsAnganwadiOverview(divId)
	}else if(divId == "CC Roads")
	{
		getCCRoadsOverview(divId)
	}else if(divId == "Mandal buildings")
	{
		getNregsMandalBuildingOverview(divId)
	}else if(divId == "Gram Panchayat Buildings")
	{
		getNREGSGPBuildingOverview(divId)
	}
}
function tableView(blockId,theadArr,result)
{
	var tableView='';
	var $windowWidth = $(window).width();
	
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
	$("#"+blockId).html(tableView);	
	$(".dataTable"+blockId).dataTable();
	if($windowWidth < 768)
	{
		$(".dataTable"+blockId).wrap("<div class='table-responsive'></div>");
	}
}
function getNREGSProjectsOverview()
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
			buildNREGSProjectsOverview(ajaxresp);
			
		}
	});
}
$('.log').ajaxComplete(function() { 
    clearconsole();
  $(this).text('Triggered ajaxComplete handler.');
});
function buildNREGSProjectsOverview(result)
{
	var str='';
	str+='<div class="row">';
		for(var i in result)
		{
			str+='<div class="col-sm-2">';
				if(result[i].percentage < 50)
				{
					str+='<div class="panel-block-white panel-block-white-low text-center" overview-block="'+result[i].parameter+'">';
				}else if(result[i].percentage > 50 && result[i].percentage < 80)
				{
					str+='<div class="panel-block-white panel-block-white-medium text-center" overview-block="'+result[i].parameter+'">';
				}else if(result[i].percentage > 80)
				{
					str+='<div class="panel-block-white panel-block-white-high text-center" overview-block="'+result[i].parameter+'">';	
				}
				if(result[i].parameter.length > 14)
					str+='<h4 class="panel-block-white-title toolTipTitleCls text-capitalize text-center" title="'+result[i].parameter+'">'+result[i].parameter.substr(0,10)+'...</h4>';
				else
					str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i].parameter+'</h4>';
					str+='<small class="text-center">Achieved</small>';
					str+='<h1 class="text-center">'+result[i].percentage+'<small>%</small>';
				if(result[i].percentage < 50)
				{
					str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
				}else if(result[i].percentage > 50 && result[i].percentage < 80)
				{
					str+='<small><i class="fa fa-arrows-v"></i></small></h1>';
					
				}else if(result[i].percentage > 80)
				{
					str+='<small><i class="fa fa-long-arrow-up"></i></small></h1>';
				}
					str+='<div class="row">';
						str+='<div class="col-sm-6 text-center">';
							str+='<label>Target</label>';
							str+='<h4>'+result[i].target+'</h4>';
						str+='</div>';
						str+='<div class="col-sm-6 text-center">';
							str+='<label>Generated</label>';
							str+='<h4>'+result[i].completed+'</h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		}
	str+='</div>';
	$("#projectsOverview").html(str);
	$(".toolTipTitleCls").tooltip();
}

function getNregsVermiOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
			year:"2017",
			fromDate:glStartDate,
			toDate:glEndDate
		}
	$.ajax({
		url: 'getNregsVermiOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp){
			if(ajaxresp !=null ){
				buildNregasOverViewBlock(ajaxresp,projectDivId);
			}
		}
	});
}
function getNregsVermiData(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
	var json = {
			year:"2017",
			fromDate:glStartDate,
			toDate:glEndDate,
		    locationType: locationType
		}
	$.ajax({
		url: 'getNregsVermiData',
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
						if(locationType == "state")
							str+='<td class="text-capital">'+locationType+'</td>';
						if(locationType == "district")
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						if(locationType == "constituency")
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						if(locationType == "mandal")
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						str+='<td>'+ajaxresp[i].target+'</td>';
						str+='<td>'+ajaxresp[i].grounded+'</td>';
						str+='<td>'+ajaxresp[i].notGrounded+'</td>';
						str+='<td>'+ajaxresp[i].inProgress+'</td>';
						str+='<td>'+ajaxresp[i].completed+'</td>';
						str+='<td>'+ajaxresp[i].percentage+'</td>';
					str+='</tr>';
				}
			}
		  tableView(divIdd,theadArr,str);
		}
	});
}

function getNREGSIHHLOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaIHHLOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNregasOverViewBlock(ajaxresp,projectDivId);
		}
	});
}

//IHHL Levelwise Data Call — Nandhini

function getNREGSIHHLLelwiseData(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
  var json = {
    year : "2017",
    fromDate : glStartDate,
      toDate : glEndDate,
    locationType: locationType
}
  $.ajax({
    url: 'getNregaLevelsOverviewForIHHL',
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
					if(locationType == "state")
						str+='<td class="text-capital">'+locationType+'</td>';
					if(locationType == "district")
						str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
					if(locationType == "constituency")
						str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
					if(locationType == "mandal")
						str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
					str+='<td>'+ajaxresp[i].target+'</td>';
					str+='<td>'+ajaxresp[i].grounded+'</td>';
					str+='<td>'+ajaxresp[i].notGrounded+'</td>';
					str+='<td>'+ajaxresp[i].inProgress+'</td>';
					str+='<td>'+ajaxresp[i].completed+'</td>';
					str+='<td>'+ajaxresp[i].percentage+'</td>';
				str+='</tr>';
			}
		}
      tableView(divIdd,theadArr,str);
    }
  });
}

//LabourBudget Overview Call — Sravanth

function getNREGSLabourBudgetOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
      fromDate : glStartDate,
        toDate : glEndDate  
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
			buildNregasOverViewBlock(ajaxresp,projectDivId)
		}
	});
}

//LabourBudget Expenditure Call  — Sravanth

function getNREGSLabourBudgetExpenditure(projectDivId)
{
	$("#projectOvervw"+projectDivId).html(spinner);
  var json = {
    year : "2017",
      fromDate : glStartDate,
        toDate : glEndDate  
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
      buildLabrBudgetExpBlock(ajaxresp,projectDivId)
    }
  });
}

//LabourBudget LevelWise Data Call — Sravanth

function getNREGSLabBugdtLelwiseData(divIdd,locationType)
{
	var theadArr = [locationType,'Target Person days upto 31st May','Generated Person days','(%) Achivement Vs Approved Labour Budget days','Average Wage rate','Total Expanditure( in Crs)'];
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locationType  
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
						if(locationType == "state")
							str+='<td class="text-capital">'+locationType+'</td>';
						if(locationType == "district")
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
						if(locationType == "constituency")
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
						if(locationType == "mandal")
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
						str+='<td>'+ajaxresp[i].targetPersonDays+'</td>';
						str+='<td>'+ajaxresp[i].generatedPersonDays+'</td>';
						str+='<td>'+ajaxresp[i].perAppLB+'</td>';
						str+='<td>'+ajaxresp[i].avgWageRate+'</td>';
						str+='<td>'+ajaxresp[i].totalExpenditure+'</td>';
					str+='</tr>';
				}
			}
		  tableView(divIdd,theadArr,str);
		}
	});
}

//FarmPondOverview Call — Swapna

function getNREGSFarmPondOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getFarmPondOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			if(ajaxresp !=null ){
				buildNregasOverViewBlock(ajaxresp,projectDivId);
			}
		}
	});
}

//FarmPond Levelwise Data Call — Swapna

function getNREGSFarmPondLelwiseData(divIdd,locationType,theadArr)
{

  var json = {
    year : "2017",
    fromDate : glStartDate,
      toDate : glEndDate,
    locationType: locationType 
}
  $.ajax({
    url: 'getFarmPondData',
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
					if(locationType == "state")
						str+='<td class="text-capital">'+locationType+'</td>';
					if(locationType == "district")
						str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
					if(locationType == "constituency")
						str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
					if(locationType == "mandal")
						str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
					str+='<td>'+ajaxresp[i].target+'</td>';
					str+='<td>'+ajaxresp[i].grounded+'</td>';
					str+='<td>'+ajaxresp[i].notGrounded+'</td>';
					str+='<td>'+ajaxresp[i].inProgress+'</td>';
					str+='<td>'+ajaxresp[i].completed+'</td>';
					str+='<td>'+ajaxresp[i].percentage+'</td>';
				str+='</tr>';
			}
		}
      tableView(divIdd,theadArr,str);
    }
  });
}

function buildNregasOverViewBlock(result,projectDivId){
	var str1="";
	if(result.averagePerDistrict != null)
	{
		str1+='<table class="table table-bordered" >';
			str1+='<tbody>';
				str1+='<tr>';
					str1+='<td>';
						str1+='<p>Average Per District : '+result.averagePerDistrict+'</p>';
					str1+='</td>';
					str1+='<td>';
						str1+='<p>Total Avg farms in District : '+result.totalAvgFarmsInDistrict+'</p>';
					str1+='</td>';
				str1+='</tr>';
				str1+='<tr>';
					str1+='<td>';
						str1+='<p>Average Per Constituency : '+result.averagePerConstituency+'</p>';
					str1+='</td>';
					str1+='<td>';
						str1+='<p>Total Avg farms in Constituency : '+result.totalAvgFarmsInConstituency+'</p>';
					str1+='</td>';
				str1+='</tr>';
				str1+='<tr>';
					str1+='<td>';
						str1+='<p>Average Per Mandal : '+result.averagePerMandal+'</p>';
					str1+='</td>';
					str1+='<td>';
						str1+='<p>Total Avg farms in Mandal: '+result.totalAvgFarmsInMandal+'</p>';
					str1+='</td>';
				str1+='</tr>';
				str1+='<tr>';
					str1+='<td>';
						str1+='<p>Total Budget</p>';
						str1+='<h4>'+result.totalBudget+'</h4>';
					str1+='</td>';
					str1+='<td>';
						str1+='<p>Total Farms</p>';
						str1+='<h4>0</h4>';
					str1+='</td>';
				str1+='</tr>';
			str1+='</tbody>';
		str1+='</table>';
	}
	if(result.maleLabour != null){
		str1+='<table class="table table-bordered m_top10">';
		str1+='<tbody>';
			str1+='<tr>';
				str1+='<td>';
					str1+='<div class="col-sm-8">';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Male.png"></p><p class="media-body">Male Labours : '+result.maleLabour+'</p>';
							str1+='</div>';
						str1+='<div class="media"> ';  
						str1+='<p class="media-left">';
							str1+='<img src="Assests/icons/Female.png"></p><p class="media-body">Female Labour : '+result.femaleLabour+'</p>';
						str1+='</div> ';
					str1+='</div>';
				str1+='	<div class="col-sm-4">';
						str1+='<p>Total labour</p>';
						str1+='<h4><b>'+result.totalLabour+'</b></h4>';
					str1+='</div>';
				str1+='</td>';
				str1+='<td>';
					str1+='<div class="col-sm-8">';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Green_Worker.png"></p><p class="media-body">On request work allocated : '+result.onRequestWorkAllocated+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Orange_Worker.png"></p><p class="media-body">Got work occutionaly :'+result.gotWorkOccutionally+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Red_Worker.png"></p><p class="media-body">Havent got work :'+result.haveNotGotWork+'</p>';
						str1+='</div>';
					str1+='</div>';
					str1+='<div class="col-sm-4">';
						str1+='<p>Total Response</p>';
						str1+='<h4><b>'+result.totalResponse+'</b></h4>';
				str1+='	</div>';
				str1+='</td>';
				str1+='<td>';
					str1+='<div class="col-sm-8">';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Labours_icon.png"></p><p class="media-body">Budget for Labours : '+result.budgetForLabour+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Bugget_icon.png"></p><p class="media-body">Budget for Material : '+result.budgetForMaterial+'</p>';
							str1+='</div>';
					str1+='</div>';
					str1+='<div class="col-sm-4">';
						str1+='<p>Total Budget</p>';
						str1+='<h4><b>'+result.totalBudget+'</b></h4>';
					str1+='</div>';
				str1+='</td>';
			str1+='</tr>';
			str1+='</tbody>';
		str1+='</table>';
	}
	
	str1+='<table class="table table-bordered m_top10" >';
		str1+='<tbody>';
			str1+='<tr>';
				str1+='<td>';
					str1+='<div class="col-sm-9">';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
							str1+='<p class="media-body">District in Red : '+result.districtsInRed+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							str1+='<p class="media-body">District in Orange : '+result.districtsInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							str1+='<p class="media-body">District in Green : '+result.districtsInGreen+'</p>';
						str1+='</div>';
					str1+='</div>';
					str1+='<div class="col-sm-3">';
						str1+='<p>Total District</p>';
						str1+='<h4>'+result.totalDistricts+'</h4>';
					str1+='</div>';
				str1+='</td>';
				str1+='<td>';
					str1+='<div class="col-sm-9">';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
						str1+='<p class="media-body">Constituencies in Red : '+result.constituenciesInRed+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Constituencies in Green : '+result.constituenciesInGreen+'</p>';
						str1+='</div>';
					str1+='</div>';	
					str1+='<div class="col-sm-3">';
						str1+='<p>Total Constituencies</p>';
						str1+='<h4>'+result.totalConstituencies+'</h4>';
					str1+='</div>';
				str1+='</td>';
				str1+='<td>';
					str1+='<div class="col-sm-9">';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p><p class="media-body">Mandals in Red : '+result.mandalsInRed+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Mandals in Orange : '+result.mandalsInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Mandals in Green : '+result.mandalsInGreen+'</p>';
						str1+='</div>';
					str1+='</div>';	
					str1+='<div class="col-sm-3">';
						str1+='<p>Total Mandals</p>';
						str1+='<h4>'+result.totalMandals+'</h4>';
					str1+='</div>';
				str1+='</td>';
				str1+='<td>';
					str1+='<div class="col-sm-9">';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p><p class="media-body">Villages in Red : '+result.villagesInRed+'</p>';
						str1+='</div>';
						str1+=' <div class="media">';
						   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Villages in Orange : '+result.villagesInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Villages in Green : '+result.villagesInGreen+'</p>';
						str1+='</div>';
					str1+='</div>';	
					str1+='<div class="col-sm-3">';
						str1+='<p>Total Villages</p>';
						str1+='<h4>'+result.totalVillages+'</h4>';
					str1+='</div>';
				str1+='</td>';
			str1+='</tr>';
		str1+='</tbody>';
	str1+='</table>';
	if(result.targettedPersonDays != null)
	{
		str1+='<div id="projectExp'+projectDivId.replace(/\s+/g, '')+'" style="margin-top:10px;"></div>';
		str1+='<table class="table table-bordered m_top20">';
			str1+='<tr>';
				str1+='<td>';
					str1+='<p class="col-sm-9">Targeted Person days upto 31st may</p>';
					str1+='<p class="col-sm-3 text-center">'+result.targettedPersonDays+'</p>';
					str1+='<p class="col-sm-9">Generated person days</p>';
					str1+='<p class="col-sm-3 text-center">'+result.generatedPersonDays+'</p>';
					str1+='<p class="col-sm-9">(%) Acheivement Vs Approved Labour Budget Days</p>';
					str1+='<p class="col-sm-3 bg-danger text-danger text-center"></p>';
				str1+='</td>';
				str1+='<td>';
					str1+='<p class="col-sm-9">(%) of Labour reported over target</p>';
					str1+='<p class="col-sm-3 bg-success text-success text-center"></p>';
					str1+='<p class="col-sm-9">Average Wage Per Person: Rs</p>';
					str1+='<p class="col-sm-3 bg-danger text-danger text-center">'+result.avgWagePerPerson+'</p>';
					str1+='<p class="col-sm-9">Total Expenditure (In Crs): Rs</p>';
					str1+='<p class="col-sm-3 text-center">'+result.totalExpenditure+'</p>';
				str1+='</td>';
			str1+='</tr>';
		str1+='</table>';
		
		getNREGSLabourBudgetExpenditure(projectDivId);
	}
	
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(str1);
}
function NtrsOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
  var json = {
		  year : "2017",
		  fromDate : glStartDate,
		  toDate : glEndDate
  }
  $.ajax({
    url: 'getNregsNtrsOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp) {
		buildNregasOverViewBlock(ajaxresp,projectDivId);
    }
  });
}
function getNregsNtrsData(divIdd,locationType,theadArr)
{
$("#"+divIdd).html(spinner);
  var json = {
		  year : "2017",
		  fromDate : glStartDate,
		  toDate : glEndDate,
		  locationType: locationType
  }
  $.ajax({
    url: 'getNregsNtrsData',
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
					if(locationType == "state")
						str+='<td class="text-capital">'+locationType+'</td>';
					if(locationType == "district")
						str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
					if(locationType == "constituency")
						str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
					if(locationType == "mandal")
						str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
					str+='<td>'+ajaxresp[i].target+'</td>';
					str+='<td>'+ajaxresp[i].grounded+'</td>';
					str+='<td>'+ajaxresp[i].notGrounded+'</td>';
					str+='<td>'+ajaxresp[i].inProgress+'</td>';
					str+='<td>'+ajaxresp[i].completed+'</td>';
					str+='<td>'+ajaxresp[i].percentage+'</td>';
				str+='</tr>';
			}
		}
		tableView(divIdd,theadArr,str);
    }
  });
}
function getNregsAnganwadiOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
  var json = {
		  year : "2017",
		  fromDate : glStartDate,
		  toDate : glEndDate
  }
  $.ajax({
    url: 'getNregsAnganwadiOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp){
		buildNregasOverViewBlock(ajaxresp,projectDivId);
    }
  });
}
function getNregsAnganwadiData(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
  var json = {
		  year : "2017",
		  fromDate : glStartDate,
		  toDate : glEndDate,
		  locationType: locationType
  }
  $.ajax({
    url: 'getNregsAnganwadiData',
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
					if(locationType == "state")
						str+='<td class="text-capital">'+locationType+'</td>';
					if(locationType == "district")
						str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
					if(locationType == "constituency")
						str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
					if(locationType == "mandal")
						str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
					str+='<td>'+ajaxresp[i].target+'</td>';
					str+='<td>'+ajaxresp[i].grounded+'</td>';
					str+='<td>'+ajaxresp[i].notGrounded+'</td>';
					str+='<td>'+ajaxresp[i].inProgress+'</td>';
					str+='<td>'+ajaxresp[i].completed+'</td>';
					str+='<td>'+ajaxresp[i].percentage+'</td>';
				str+='</tr>';
			}
		}
      tableView(divIdd,theadArr,str);
    }
  });
}
function getCCRoadsOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
  var json = {
		  year : "2017",
		  fromDate : glStartDate,
		  toDate : glEndDate
  }
  $.ajax({
    url: 'getCCRoadsOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp){
		buildNregasOverViewBlock(ajaxresp,projectDivId);
    }
  });
}
function getNregsMandalBuildingOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);

  var json = {
		  year : "2017",
		  fromDate : glStartDate,
		  toDate : glEndDate
  }
  $.ajax({
    url: 'getNregsMandalBuildingOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp){
		buildNregasOverViewBlock(ajaxresp,projectDivId);
    }
  });
}
function getNregsMandalBuildingData(divIdd,locationType,theadArr)
{

  var json = {
		  year : "2017",
		  fromDate : glStartDate,
		  toDate : glEndDate,
		  locationType:locationType
  }
  $.ajax({
    url: 'getNregsMandalBuildingData',
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
					if(locationType == "state")
						str+='<td class="text-capital">'+locationType+'</td>';
					if(locationType == "district")
						str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
					if(locationType == "constituency")
						str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
					if(locationType == "mandal")
						str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
					str+='<td>'+ajaxresp[i].target+'</td>';
					str+='<td>'+ajaxresp[i].grounded+'</td>';
					str+='<td>'+ajaxresp[i].notGrounded+'</td>';
					str+='<td>'+ajaxresp[i].inProgress+'</td>';
					str+='<td>'+ajaxresp[i].completed+'</td>';
					str+='<td>'+ajaxresp[i].percentage+'</td>';
				str+='</tr>';
			}
		}
      tableView(divIdd,theadArr,str);
     }
  });
}
//LabourBudget Exp Builing --  Nandhini

function buildLabrBudgetExpBlock(result,projectDivId){
	var str='';
	str+='<p class="text-center expenditure">No of Panchayaties Vs Expenditure In Lakhs</p>';
		str+='<table class="table table-striped">';
			str+='<tbody>';
				 str+='<tr>';
					 str+='<td>District Name</td>';
					 for(var i in result){
						 str+='<td>'+result[i].name+'</td>';
					 }
				  str+='</tr>';
				str+=' <tr>';
					str+=' <td>Grand Total</td>';
					 for(var i in result){
						 str+='<td>'+result[i].count+'</td>';
					 }
				  str+='</tr>';
			str+='</tbody>';
		str+='</table>';
	$("#projectExp"+projectDivId.replace(/\s+/g, '')).html(str);
}

//getNREGSGPBuildingOverview Call -- Nandhini

function getNREGSGPBuildingOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaGPBuilingsOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildNregasOverViewBlock(ajaxresp,projectDivId);
		}
	});
}

//GPBuilding Levelwise Data Call — Nandhini

function getNREGSGPBuildingLelwiseData(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
  var json = {
    year : "2017",
    fromDate : glStartDate,
      toDate : glEndDate,
    locationType: locationType
}
  $.ajax({
    url: 'getNregaLevelsOverviewForGPBuilding',
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
					if(locationType == "state")
						str+='<td class="text-capital">'+locationType+'</td>';
					if(locationType == "district")
						str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
					if(locationType == "constituency")
						str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
					if(locationType == "mandal")
						str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
					str+='<td>'+ajaxresp[i].target+'</td>';
					str+='<td>'+ajaxresp[i].grounded+'</td>';
					str+='<td>'+ajaxresp[i].notGrounded+'</td>';
					str+='<td>'+ajaxresp[i].inProgress+'</td>';
					str+='<td>'+ajaxresp[i].completed+'</td>';
					str+='<td>'+ajaxresp[i].percentage+'</td>';
				str+='</tr>';
			}
		}
      tableView(divIdd,theadArr,str);
    }
  });
}
function getCCRoadsData(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
  var json = {
    year : "2017",
    fromDate : glStartDate,
      toDate : glEndDate,
    locationType: locationType
}
  $.ajax({
    url: 'getCCRoadsData',
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
					if(locationType == "state")
						str+='<td class="text-capital">'+locationType+'</td>';
					if(locationType == "district")
						str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
					if(locationType == "constituency")
						str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
					if(locationType == "mandal")
						str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
					str+='<td>'+ajaxresp[i].target+'</td>';
					str+='<td>'+ajaxresp[i].grounded+'</td>';
					str+='<td>'+ajaxresp[i].notGrounded+'</td>';
					str+='<td>'+ajaxresp[i].inProgress+'</td>';
					str+='<td>'+ajaxresp[i].completed+'</td>';
					str+='<td>'+ajaxresp[i].percentage+'</td>';
				str+='</tr>';
			}
		}
      tableView(divIdd,theadArr,str);
    }
  });
}