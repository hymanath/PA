var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = '2017-04-01'//+moment().subtract(20, 'years').startOf('year').format("YYYY-MM");
var glEndDate = "2017-06-30"//+moment().add(10, 'years').endOf('year').format("YYYY-MM");
var globalDivName;
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
		globalDivName = projectDivId;
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

	//$("#dateRangePickerMGNF").val(moment().subtract(20, 'years').startOf('year').format("YYYY-MM"))
	//$("#dateRangePickerMGNT").val(moment().add(10, 'years').endOf('year').format("YYYY-MM"))
	$("#dateRangePickerMGNF").val("2017-04")
	$("#dateRangePickerMGNT").val("2017-06")
	
	$("#dateRangePickerMGNF").datetimepicker({
		format: 'YYYY-MM',
		viewMode:'months'
	});
	$("#dateRangePickerMGNT").datetimepicker({
		format: 'YYYY-MM',
		viewMode:'months'
	});
	$('#dateRangePickerMGNF').on('dp.change', function(e){ 
		glStartDate = e.date.format("YYYY-MM")+"-01";
	});
	$('#dateRangePickerMGNT').on('dp.change', function(e){ 
		glEndDate = e.date.format("YYYY-MM")+"-31";
		var blockName = '';
		$(".panel-block-white").each(function(){
			if($(this).hasClass("active"))
			{
				blockName = $(this).attr("overview-block");
			}
		});
		$("#projectOverviewBlock,#projectData").html('');
		getNREGSProjectsOverview(blockName);
	});
	/* var dates= $("#dateRangePickerMGNF").val();
	var pickerDates = glStartDate+' - '+glEndDate
	if(dates == pickerDates)
	{
		$("#dateRangePickerMGNF").val('All');
	}
	$('#dateRangePickerMGNF').on('apply.daterangepicker', function(ev, picker) {
		var blockName = '';
		$(".panel-block-white").each(function(){
			if($(this).hasClass("active"))
			{
				blockName = $(this).attr("overview-block");
			}
		});
		glStartDate = picker.startDate.format('DD/MM/YYYY')
		glEndDate = picker.endDate.format('DD/MM/YYYY')
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePickerMGNF").val('All');
		}
		getNREGSProjectsOverview(blockName);
		$("#projectOverviewBlock,#projectData").html('');
	}); */
	getNREGSProjectsOverview('')
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
		if(dataArr[i] == "constituency")
			theadArr = ["district",dataArr[i],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		else if(dataArr[i] == "mandal")
			theadArr = ["district","constituency",dataArr[i],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
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
			getNregsHousingData(tableId,dataArr[i],theadArr);
		}else if(divId == "Production of Bricks"){
			getNregaLevelsOverviewForProductionOfBricks(tableId,dataArr[i],theadArr);
		}else if(divId == "Mulbery"){
			getNregsSericultureData(tableId,dataArr[i],theadArr);
		}else if(divId == "Silk worm"){
			getNregaLevelsOverviewForSilkWarm(tableId,dataArr[i],theadArr);
		}else if(divId == "Cattle drinking water trough"){
			getAHData(tableId,dataArr[i],theadArr);
		}else if(divId == "Raising of Perinnial Fodder"){
			getNregaLevelsOverviewForRaisingOfPerinnialFodder(tableId,dataArr[i],theadArr);
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
	}else if(divId == "Production of Bricks")
	{
		getNregaProductionOfBricksOverview(divId)
	}else if(divId == "Raising of Perinnial Fodder")
	{
		getNregaRaisingOfPerinnialFodderOverview(divId)
	}else if(divId == "Mulbery")
	{
		getNregsSericultureOverview(divId)
	}else if(divId == "NTR 90 Days")
	{
		getNregsHousingOverview(divId)
	}else if(divId == "Cattle drinking water trough")
	{
		getAHOverview(divId)
	}else if(divId == "Silk worm")
	{
		getNregaSilkWormOverview(divId)
	}else{
		$("#projectOvervw"+divId.replace(/\s+/g, '')).html("NO DATA");
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
			buildNREGSProjectsOverview(ajaxresp,blockName);
			
		}
	});
}
$('.log').ajaxComplete(function() { 
    clearconsole();
  $(this).text('Triggered ajaxComplete handler.');
});
function buildNREGSProjectsOverview(result,blockName)
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
	if(blockName != null)
	{
		$('[overview-block]').removeClass("active");
		$('[overview-block="'+blockName+'"]').addClass("active");
		$('[overview-block="'+blockName+'"]').trigger('click');
	}
	
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
	if(locationType == "constituency")
		theadArr = ["district",locationType,'Target Person days upto 31st May','Generated Person days','(%) Achivement Vs Approved Labour Budget days','Average Wage rate','Total Expanditure( in Crs)'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Target Person days upto 31st May','Generated Person days','(%) Achivement Vs Approved Labour Budget days','Average Wage rate','Total Expanditure( in Crs)'];
	
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
	var $windowWidth = $(window).width();
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
					str1+='	<div class="col-sm-12">';
						str1+='<h4><b>Total labour : '+result.totalLabour+'</b></h4>';
					str1+='</div>';
					str1+='<div class="col-sm-12 m_top10">';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Male.png"></p><p class="media-body">Male Labours : '+result.maleLabour+'</p>';
						str1+='</div>';
						str1+='<div class="media"> ';  
						str1+='<p class="media-left">';
							str1+='<img src="Assests/icons/Female.png"></p><p class="media-body">Female Labour : '+result.femaleLabour+'</p>';
						str1+='</div> ';
					str1+='</div>';
				str1+='</td>';
				str1+='<td>';
					str1+='<div class="col-sm-12">';
						str1+='<h4><b>Total Response : '+result.totalResponse+'</b></h4>';
					str1+='	</div>';
					str1+='<div class="col-sm-12 m_top10">';
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
				str1+='</td>';
				str1+='<td>';
					str1+='<div class="col-sm-12">';
						str1+='<h4><b>Total Budget : '+result.totalBudget+'</b></h4>';
					str1+='</div>';
					str1+='<div class="col-sm-12 m_top10">';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Labours_icon.png"></p><p class="media-body">Budget for Labours : '+result.budgetForLabour+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Bugget_icon.png"></p><p class="media-body">Budget for Material : '+result.budgetForMaterial+'</p>';
							str1+='</div>';
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
					str1+='<div class="col-sm-12">';
						str1+='<h4 attr_locationType="district" attr_type="total"><strong>Total Districts : '+result.totalDistricts+'</strong></h4>';
					str1+='</div>';
					str1+='<div class="col-sm-12 m_top10">';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
							if(result.districtsInRed != null && result.districtsInRed > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="district" attr_type="red" style="cursor:pointer;">Districts in Red : '+result.districtsInRed+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="district" attr_type="red">Districts in Red : '+result.districtsInRed+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							if(result.districtsInOrange != null && result.districtsInOrange > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="district" attr_type="orange" style="cursor:pointer;">Districts in Orange : '+result.districtsInOrange+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="district" attr_type="orange" >Districts in Orange : '+result.districtsInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.districtsInGreen != null && result.districtsInGreen > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="district" attr_type="green" style="cursor:pointer;">Districts in Green : '+result.districtsInGreen+'</p>';
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
							str1+='<p class="media-body overviewPopupCls" attr_locationType="constituency" attr_type="red" style="cursor:pointer;">Constituencies in Red : '+result.constituenciesInRed+'</p>';
						else
							str1+='<p class="media-body" attr_locationType="constituency" attr_type="red" >Constituencies in Red : '+result.constituenciesInRed+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
						if(result.constituenciesInOrange != null && result.constituenciesInOrange > 0)
							str1+='<p class="media-body overviewPopupCls" attr_locationType="constituency" attr_type="orange" style="cursor:pointer;">Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
						else
							str1+='<p class="media-body" attr_locationType="constituency" attr_type="orange" >Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.constituenciesInGreen != null && result.constituenciesInGreen > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="constituency" attr_type="green" style="cursor:pointer;">Constituencies in Green : '+result.constituenciesInGreen+'</p>';
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
								str1+='<p class="media-body overviewPopupCls" attr_locationType="mandal" attr_type="red" style="cursor:pointer;">Mandals in Red : '+result.mandalsInRed+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="mandal" attr_type="red">Mandals in Red : '+result.mandalsInRed+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							if(result.mandalsInOrange != null && result.mandalsInOrange > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="mandal" attr_type="orange" style="cursor:pointer;">Mandals in Orange : '+result.mandalsInOrange+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="mandal" attr_type="orange">Mandals in Orange : '+result.mandalsInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.mandalsInGreen != null && result.mandalsInGreen > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="mandal" attr_type="green" style="cursor:pointer;">Mandals in Green : '+result.mandalsInGreen+'</p>';
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
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p><p class="media-body">Villages in Red : '+result.villagesInRed+'</p>';
						str1+='</div>';
						str1+=' <div class="media">';
						   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Villages in Orange : '+result.villagesInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Villages in Green : '+result.villagesInGreen+'</p>';
						str1+='</div>';
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
	if($windowWidth < 768)
	{
		$(".table").wrap("<div class='table-responsive'></div>");
	}
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
	str+='<p class="text-center expenditure"><strong>No of Panchayaties Vs Expenditure In Lakhs</strong></p>';
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

function getNregaRaisingOfPerinnialFodderOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaRaisingOfPerinnialFodderOverview',
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

function getNregaProductionOfBricksOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaProductionOfBricksOverview',
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

function getNregaSilkWormOverview(projectDivId)
{
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaSilkWormOverview',
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

function getNregaLevelsOverviewForProductionOfBricks(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
  var json = {
    year : "2017",
    fromDate : glStartDate,
      toDate : glEndDate,
    locationType: locationType
}
  $.ajax({
    url: 'getNregaLevelsOverviewForProductionOfBricks',
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

function getNregaLevelsOverviewForRaisingOfPerinnialFodder(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
  var json = {
    year : "2017",
    fromDate : glStartDate,
      toDate : glEndDate,
    locationType: locationType
}
  $.ajax({
    url: 'getNregaLevelsOverviewForRaisingOfPerinnialFodder',
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

function getNregaLevelsOverviewForSilkWarm(divIdd,locationType,theadArr)
{
	$("#"+divIdd).html(spinner);
  var json = {
    year : "2017",
    fromDate : glStartDate,
      toDate : glEndDate,
    locationType: locationType
}
  $.ajax({
    url: 'getNregaLevelsOverviewForSilkWarm',
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
function getNregsSericultureOverview(projectDivId)
{
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate 
  };
  $.ajax({
    url: 'getNregsSericultureOverview',
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
//getNregsSericultureData();
function getNregsSericultureData(divIdd,locationType,theadArr)
{
  var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locationType
  };
  $.ajax({
    url: 'getNregsSericultureData',
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
//getNregsHousingOverview();
function getNregsHousingOverview(projectDivId)
{
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate 
  };
  $.ajax({
    url: 'getNregsHousingOverview',
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

function getAHOverview(projectDivId)
{
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate 
  };
  $.ajax({
    url: 'getAHOverview',
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
//getNregsHousingData();
function getNregsHousingData(divIdd,locationType,theadArr)
{
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate,
		locationType: locationType
  };
  $.ajax({
    url: 'getNregsHousingData',
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

function getAHData(divIdd,locationType,theadArr)
{
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate ,
		locationType: locationType
  };
  $.ajax({
    url: 'getAHData',
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
//DistrictConst Call -- Nandhini
function buildDistrictsPopupDetails(result,dataArr){
	var str = '';
	if(result.distConsCuntList != null && result.distConsCuntList.length > 0 )
	{
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">Constituencies in Districts</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
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
	}
	
	if(result.distMandalCuntList != null && result.distMandalCuntList.length > 0 )
	{
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">Mandals in '+dataArr+'s</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				str+='<table class="table table-bordered m_top10 dataTableCls">';
					str+='<thead class="text-capital">';
						str+='<tr>';
							str+='<th>District Name </th>';
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
	}
	
	if(result.distList != null && result.distList.length > 0 )
	{
		str+='<div class="panel panel-default panel-black m_top10">';
			str+='<div class="panel-heading">';
				str+='<h4 class="panel-title text-capital">'+dataArr+' Details</h4>';
			str+='</div>';
			str+='<div class="panel-body">';
				var theadArr = [dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
				if(globalDivName != 'Labour Budget'){
					str+='<table class="table table-bordered m_top10 dataTableCls">';
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
									str+='<td>'+result.distList[i].target+'</td>';
									str+='<td>'+result.distList[i].grounded+'</td>';
									str+='<td>'+result.distList[i].notGrounded+'</td>';
									str+='<td>'+result.distList[i].inProgress+'</td>';
									str+='<td>'+result.distList[i].completed+'</td>';
									str+='<td>'+result.distList[i].percentage+'</td>';
								str+='</tr>';
							}
						str+='</tbody>';
					str+='</table>';
				}
				else{
					theadArr = [dataArr,'Target Person days upto 31st May','Generated Person days','(%) Achivement Vs Approved Labour Budget days','Average Wage rate','Total Expanditure( in Crs)'];
					if(dataArr == "constituency")
						theadArr = ["district",dataArr,'Target Person days upto 31st May','Generated Person days','(%) Achivement Vs Approved Labour Budget days','Average Wage rate','Total Expanditure( in Crs)'];
					else if(dataArr == "mandal")
						theadArr = ["district","constituency",dataArr,'Target Person days upto 31st May','Generated Person days','(%) Achivement Vs Approved Labour Budget days','Average Wage rate','Total Expanditure( in Crs)'];
					
					str+='<table class="table table-bordered m_top10 dataTableCls">';
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
								
							str+='<td>'+result.distList[i].targetPersonDays+'</td>';
							str+='<td>'+result.distList[i].generatedPersonDays+'</td>';
							str+='<td>'+result.distList[i].perAppLB+'</td>';
							str+='<td>'+result.distList[i].avgWageRate+'</td>';
							str+='<td>'+result.distList[i].totalExpenditure+'</td>';
						str+='</tr>';
					}
				}
			str+='</div>';
		str+='</div>';
	}
	
	$("#nregsConsitenBodyId").html(str);
	$(".dataTableCls").dataTable();
}

//getNREGSIHHLConsCuntData()
function getNREGSConsCuntData(locationType,type,globalDivName)
{
	$("#nregsConsitenBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : "2017-04-01",
		toDate : "2017-06-30",
		locationType: locationType,
		type  : type,
		divType : globalDivName
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
	$("#modalHeadingDivId").html(heading);
	getNREGSConsCuntData(locationType,type,globalDivName);
});

$(document).on("click",".overviewPopupCls",function(){
	var locationType = $(this).attr("attr_locationType");
	var type = $(this).attr("attr_type");
	var heading = $(this).html();
	$("#nregsConsitenModalId").modal("show");
	$("#modalHeadingDivId").html(heading);
	if(globalDivName == 'Labour Budget')
	{
		getLabourBudgetClickingOverview();
	}else if(globalDivName == 'Farm Pond'){
		getFarmPondClickingOverview();
	}
	else if(globalDivName == 'VERMI'){
		getVermiClickingOverview();
	}
	else if(globalDivName == "IHHL"){
		getIHHLClickingOverview()
	}
	else if(globalDivName == "NTR Jala Siri"){
		NtrsClickingOverview()
	}
	else if(globalDivName == "Anganwadi"){
		getAnganwadiClickingOverview();
	}
	else if(globalDivName == "CC Roads"){
		getCCRoadsClickingOverview();
	}
	else if(globalDivName == "Mandal buildings"){
		getMandalBuildingClickOverview();
	}
	else if(globalDivName == "Gram Panchayat Buildings"){
		getGPBuildingClickOverview();
	}
	else if(globalDivName == "Production of Bricks"){
		getProductionOfBricksClickOverview();
	}
	else if(globalDivName == "Raising of Perinnial Fodder"){
		getRaisingOfPerinnialFodderClickOverview();
	}
	else if(globalDivName == "Mulbery"){
		getSericultureClickOverview();
	}
	else if(globalDivName == "NTR 90 Days"){
		getHousingClickingOverview();
	}
	else if(globalDivName == "Cattle drinking water trough"){
		getAHClickingOverview();
	}
	else if(globalDivName == "Silk worm"){
		getSilkWormClickingOverview();
	}else{
		$("#nregsOverviewBodyId").html("NO DATA");
	}
	getNREGSConsCuntData(locationType,type,globalDivName);
});

//PopupOverview clicking Block 
function buildPopupOverviewBlock(result,projectDivId){
	var str1='';
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
								str1+='<p class="media-body detailsCls" attr_location="district" attr_type="red">Districts in Red : '+result.districtsInRed+'</p>';
							else
								str1+='<p class="media-body" attr_location="district" attr_type="red">Districts in Red : '+result.districtsInRed+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							if(result.districtsInOrange != null && result.districtsInOrange > 0)
								str1+='<p class="media-body detailsCls" attr_location="district" attr_type="orange">Districts in Orange : '+result.districtsInOrange+'</p>';
							else
								str1+='<p class="media-body" attr_location="district" attr_type="orange">Districts in Orange : '+result.districtsInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.districtsInGreen != null && result.districtsInGreen > 0)
								str1+='<p class="media-body detailsCls" attr_location="district" attr_type="green">Districts in Green : '+result.districtsInGreen+'</p>';
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
							str1+='<p class="media-body detailsCls" attr_location="constituency" attr_type="red">Constituencies in Red : '+result.constituenciesInRed+'</p>';
						else
							str1+='<p class="media-body" attr_location="constituency" attr_type="red">Constituencies in Red : '+result.constituenciesInRed+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
						str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
						if(result.constituenciesInOrange != null && result.constituenciesInOrange > 0)
							str1+='<p class="media-body detailsCls" attr_location="constituency" attr_type="orange">Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
						else
							str1+='<p class="media-body" attr_location="constituency" attr_type="orange">Constituencies in Orange : '+result.constituenciesInOrange+'</p>';
							str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.constituenciesInGreen != null && result.constituenciesInGreen > 0)
								str1+='<p class="media-body detailsCls" attr_location="constituency" attr_type="green">Constituencies in Green : '+result.constituenciesInGreen+'</p>';
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
								str1+='<p class="media-body detailsCls" attr_location="mandal" attr_type="red">Mandals in Red : '+result.mandalsInRed+'</p>';
							else
								str1+='<p class="media-body" attr_location="mandal" attr_type="red">Mandals in Red : '+result.mandalsInRed+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
							if(result.mandalsInOrange != null && result.mandalsInOrange > 0)
								str1+='<p class="media-body detailsCls" attr_location="mandal" attr_type="orange">Mandals in Orange : '+result.mandalsInOrange+'</p>';
							else
								str1+='<p class="media-body" attr_location="mandal" attr_type="orange">Mandals in Orange : '+result.mandalsInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.mandalsInGreen != null && result.mandalsInGreen > 0)
								str1+='<p class="media-body detailsCls" attr_location="mandal" attr_type="green">Mandals in Green : '+result.mandalsInGreen+'</p>';
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
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p><p class="media-body">Villages in Red : '+result.villagesInRed+'</p>';
						str1+='</div>';
						str1+=' <div class="media">';
						   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Villages in Orange : '+result.villagesInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Villages in Green : '+result.villagesInGreen+'</p>';
						str1+='</div>';
					str1+='</div>';	
				str1+='</td>';
			str1+='</tr>';
		str1+='</tbody>';
	str1+='</table>';
	$("#nregsOverviewBodyId").html(str1);
} 

function getVermiClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
				buildPopupOverviewBlock(ajaxresp);
			}
		}
	});
}
function getFarmPondClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
				buildPopupOverviewBlock(ajaxresp);
			}
		}
	});
}
function getIHHLClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
			buildPopupOverviewBlock(ajaxresp);
		}
	});
}
function NtrsClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
		buildPopupOverviewBlock(ajaxresp);
    }
  });
}
function getAnganwadiClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
		buildPopupOverviewBlock(ajaxresp);
    }
  });
}
function getCCRoadsClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
		buildPopupOverviewBlock(ajaxresp);
    }
  });
}
function getMandalBuildingClickOverview()
{
	$("#nregsOverviewBodyId").html(spinner);

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
		buildPopupOverviewBlock(ajaxresp);
    }
  });
}
function getGPBuildingClickOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
			buildPopupOverviewBlock(ajaxresp);
		}
	});
}
function getProductionOfBricksClickOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaProductionOfBricksOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildPopupOverviewBlock(ajaxresp);
		}
	});
}
function getRaisingOfPerinnialFodderClickOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaRaisingOfPerinnialFodderOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildPopupOverviewBlock(ajaxresp);
		}
	});
}
function getSericultureClickOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate 
  };
  $.ajax({
    url: 'getNregsSericultureOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp){
		buildPopupOverviewBlock(ajaxresp);
    }
  });
}
function getHousingClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate 
  };
  $.ajax({
    url: 'getNregsHousingOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp){
		buildPopupOverviewBlock(ajaxresp);
    }
  });
}
function getAHClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
  var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate 
  };
  $.ajax({
    url: 'getAHOverview',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success: function(ajaxresp){
		buildPopupOverviewBlock(ajaxresp);
    }
  });
}
function getSilkWormClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate  
	}
	$.ajax({
		url: 'getNregaSilkWormOverview',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildPopupOverviewBlock(ajaxresp);
		}
	});
}

function getLabourBudgetClickingOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
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
			buildPopupOverviewBlock(ajaxresp);
		}
	});
}