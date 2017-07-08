var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = moment().startOf('year').format("YYYY-MM")+'-1';
var glEndDate = moment().format("YYYY-MM")+'-30';
var glStartDateForWebservice = moment().format("DD/MM/YYYY");
var glEndDateForWebservice = moment().format("DD/MM/YYYY");
var globalDivName;
var $windowWidth = $(window).width();
onLoadCalls();
function onLoadCalls()
{
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
		$("[overview-state='"+projectDivId+"'],[overview-district='"+projectDivId+"']").addClass("active");
		globalDivName = projectDivId;
		overviewData(projectDivId);
		projectData(projectDivId);
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
	
	$("#dateRangePickerMGNF").val(moment().startOf('year').format("YYYY-MM")+'-1');
	$("#dateRangePickerMGNT").val(moment().format("YYYY-MM")+'-30');
	
	
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
		buildNREGSProjectsOverview(overViewArr,'');
		for(var i in overViewArr)
		{
			$("[overview-block='"+overViewArr[i]+"']").append(spinner);
			if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
				getNREGSProjectsAbstractNew(overViewArr[i],'state',0,'');
			else
				getNREGSAbstractDataByType(overViewArr[i],'state',0,'');
		}
		//getNREGSProjectsOverview(blockName);
	});
	//getNREGSProjectsOverview('')
	
	$(document).on('click','#getWebserviceDetailsId', function(){
		getWebserviceHealthDetails(glStartDateForWebservice,glEndDateForWebservice);
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
		str+='<table id="webserviceHealthDetailsTableId" class="table table-bordered">';
		str+='<thead>';
		str+='<tr>';
		str+='<th>Provider</th>';
		str+='<th>Module</th>';
		str+='<th>Service Name</th>';
		str+='<th>No of Call</th>';
		str+='<th>Success</th>';
		str+='<th>Fail</th>';
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
			str+='<td>'+ajaxresp[i].totalFail+'</td>';
			str+='<td>'+ajaxresp[i].totalTime+'</td>';
			str+='<td>'+ajaxresp[i].averageTime+'</td>';
			str+='</tr>';
		}
		str+='</tbody>';
		str+='</table>';
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
function projectData(divId)
{
	var collapse='';
	var dataArr = ['state','district','constituency','mandal','panchayat']
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
									collapse+='<a role="button" class="panelCollapseIcon" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed" data-toggle="collapse" data-parent="#accordion'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" href="#collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId.replace(/\s+/g, '')+''+dataArr[i]+'">';
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
	var menuLocationId = "-1";
	var menuLocationType = "state";
	
	for(var i in dataArr)
	{
		var theadArr = [dataArr[i],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		if(dataArr[i] == "constituency")
			theadArr = ["district",dataArr[i],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		else if(dataArr[i] == "mandal")
			theadArr = ["district","constituency",dataArr[i],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		else if(dataArr[i] == "panchayat")
			theadArr = ["district","constituency","mandal",dataArr[i],'TARGET','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		if((divId == 'Mulbery' || divId == 'Silk worm' || divId == 'Cattle drinking water trough' || divId == 'Raising of Perinnial Fodder') && dataArr[i] == "state")
			theadArr = [dataArr[i],'TARGET','sanctioned Target','sanctioned Percentage','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		if((divId == 'Fish Ponds' || divId == 'Fish Drying Platforms') && (dataArr[i] == "state" || dataArr[i] == "district"))
			theadArr = [dataArr[i],'TARGET','sanctioned Target','sanctioned Percentage','Grounded','Not-Grounded','In Progress','Completed','Achivement Percentage'];
		
		var tableId = divId.replace(/\s+/g, '')+''+dataArr[i];
		$("#"+tableId).html(spinner);
		if(divId == 'Labour Budget')
			getNREGSLabBugdtLelwiseData(tableId,dataArr[i]);
		else if(divId == "Agriculture Activities")
			getNregaLevelsWiseDataFrAgriculture(tableId,dataArr[i]);
		else if(divId == "Average Wage" || divId == "Average Days of Employment" || divId == "HH Completed 100 Days" || divId == "Nurseries" || divId == "Timely Payment")
			getNregaLevelsWiseDataFrNewCalls(tableId,dataArr[i]);
		else if(divId == "Horticulture")//
			getNregaLevelsWiseDataFrHorticulture(tableId,dataArr[i]);
		else if(divId == "Avenue")//
			getNregaLevelsWiseDataFrAvenue(tableId,dataArr[i]);
		else if(divId == "CC Roads")//
			getNregaLevelsWiseDataForCCRoads(tableId,dataArr[i]);
		else if(divId == "Payments")//
			getNregaLevelsWiseDataForTimelyPayments(tableId,dataArr[i]);
		else
			getNregaLevelsWiseData(tableId,dataArr[i],theadArr,menuLocationType,menuLocationId);
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
	//For Menu
	var menuLocationId = "-1";
	var menuLocationType = "state";
	
	if(divId == 'Labour Budget')
		getNREGSLabourBudgetOverview(divId);
	else
		getNregasOverview(divId,menuLocationType,menuLocationId);
}
function tableView(blockId,theadArr,result,locationType)
{
	var tableView='';
	
	if($windowWidth < 768)
	{
		tableView+='<div class="table-responsive">';
	}
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
	if($windowWidth < 768)
	{
		tableView+='</div>';
	}
	$("#"+blockId).html(tableView);	
	if(locationType == 'constituency' || locationType == 'mandal' || locationType == 'panchayat')
	{
		$(".dataTable"+blockId).dataTable();
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
$('.log').ajaxComplete(function() { 
    clearconsole();
  $(this).text('Triggered ajaxComplete handler.');
});

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
												str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
						str+='<div class="col-sm-2 m_top10">';
							str+='<div class="panel-block-white text-center" overview-block="'+result[i]+'">';	
								str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
							str+='</div>';
						str+='</div>';
					}
				}
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
							str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
											str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
											str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
										str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
											str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
											str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
										str+='<div class="panel-block-white panel-block-white-low text-center" overview-block="'+result[i]+'">';
											str+='<h4 class="panel-block-white-title text-capitalize text-center">'+result[i]+'</h4>';
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
						else if(locationType == "panchayat"){
							str+='<td class="text-capital">'+ajaxresp[i].district+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].constituency+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].mandal+'</td>';
							str+='<td class="text-capital">'+ajaxresp[i].panchayat+'</td>';
						}	
						str+='<td>'+ajaxresp[i].targetPersonDays+'</td>';
						str+='<td>'+ajaxresp[i].generatedPersonDays+'</td>';
						str+='<td>'+ajaxresp[i].perAppLB+'</td>';
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

function buildNregasOverViewBlock(result,projectDivId){
	var $windowWidth = $(window).width();
	var str1="";
	if(result.averagePerDistrict != null)
	{
		if($windowWidth < 768)
		{
			str1+='<div class="table-responsive">';
		}
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
		if($windowWidth < 768)
		{
			str1+='</div>';
		}
	}
	if(result.maleLabour != null){
		if($windowWidth < 768)
		{
			str1+='<div class="table-responsive">';
		}
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
		if($windowWidth < 768)
		{
			str1+='</div>';
		}
	}
	if($windowWidth < 768)
	{
		str1+='<div class="table-responsive">';
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
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
							if(result.villagesInRed != null && result.villagesInRed > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="panchayat" attr_type="red" style="cursor:pointer;">Villages in Red : '+result.villagesInRed+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="panchayat" attr_type="red" style="cursor:pointer;"">Villages in Red : '+result.villagesInRed+'</p>';
						str1+='</div>';
						str1+=' <div class="media">';
						   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
						   if(result.villagesInOrange != null && result.villagesInOrange > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="panchayat" attr_type="orange" style="cursor:pointer;">Villages in Orange : '+result.villagesInOrange+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="panchayat" attr_type="orange" style="cursor:pointer;">Villages in Orange : '+result.villagesInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.villagesInGreen != null && result.villagesInGreen > 0)
								str1+='<p class="media-body overviewPopupCls" attr_locationType="panchayat" attr_type="green" style="cursor:pointer;">Villages in Green : '+result.villagesInGreen+'</p>';
							else
								str1+='<p class="media-body" attr_locationType="panchayat" attr_type="green" style="cursor:pointer;">Villages in Green : '+result.villagesInGreen+'</p>';
						str1+='</div>';
					str1+='</div>';	
				str1+='</td>';
			str1+='</tr>';
		str1+='</tbody>';
	str1+='</table>';
	if($windowWidth < 768)
	{
		str1+='</div>';
	}
	if(result.targettedPersonDays != null)
	{
		str1+='<div id="projectExp'+projectDivId.replace(/\s+/g, '')+'" style="margin-top:10px;"></div>';
		if($windowWidth < 768)
		{
			str1+='<div class="table-responsive">';
		}
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
		if($windowWidth < 768)
		{
			str1+='</div>';
		}
		getNREGSLabourBudgetExpenditure(projectDivId);
	}
	
	$("#projectOvervw"+projectDivId.replace(/\s+/g, '')).html(str1);
	
}

//LabourBudget Exp Builing --  Nandhini
function buildLabrBudgetExpBlock(result,projectDivId){
	var str='';
	str+='<p class="text-center expenditure"><strong>No of Panchayaties Vs Expenditure In Lakhs</strong></p>';
		if($windowWidth < 768)
		{
			str+='<div class="table-responsive">';
		}
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
				str+=' <tr>';
					str+=' <td>Percentage</td>';
					for(var i in result){
						str+='<td>'+result[i].totl+'</td>';
					}
				str+='</tr>';
			str+='</tbody>';
		str+='</table>';
		if($windowWidth < 768)
		{
			str+='</div>';
		}
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
			
			if(globalDivName == "Average Wage" || globalDivName == "Average Days of Employment" || globalDivName == "HH Completed 100 Days" || globalDivName == "Timely Payment"){
				theadArr = [dataArr,'TARGET','Achivement','Percentage'];
				if(dataArr == "constituency")
					theadArr = ["district",dataArr,'TARGET','Achivement','Percentage'];
				else if(dataArr == "mandal")
					theadArr = ["district","constituency",dataArr,'TARGET','Achivement','Percentage'];
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
					if($windowWidth < 768)
					{
						str+='<div class="table-responsive">';
					}
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
									str+='<td>'+result.distList[i].percentage+'</td>';
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
									str+='<td>'+result.distList[i].percentage+'</td>';
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
									str+='<td>'+result.distList[i].pencentageOfPlanting+'</td>';
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
									str+='<td>'+result.distList[i].pencentageOfPlanting+'</td>';
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
									str+='<td>'+result.distList[i].percentage+'</td>';
								str+='</tr>';
								}
							}
						str+='</tbody>';
					str+='</table>';
					if($windowWidth < 768)
					{
						str+='</div>';
					}
					
				}
				else{
					globalDivName
					theadArr = [dataArr,'Target Person days','Generated','Achivement Percentage','Avg Wage rate','Total Expanditure'];
					if(dataArr == "constituency")
						theadArr = ["district",dataArr,'Target Person days','Generated','Achivement Percentage','Avg Wage rate','Total Expanditure'];
					else if(dataArr == "mandal")
						theadArr = ["district","constituency",dataArr,'Target Person days','Generated','Achivement Percentage','Avg Wage rate','Total Expanditure'];
					if($windowWidth < 768)
					{
						str+='<div class="table-responsive">';
					}
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
								
							str+='<td>'+result.distList[i].targetPersonDays+'</td>';
							str+='<td>'+result.distList[i].generatedPersonDays+'</td>';
							str+='<td>'+result.distList[i].perAppLB+'</td>';
							str+='<td>'+result.distList[i].avgWageRate+'</td>';
							str+='<td>'+result.distList[i].totalExpenditure+'</td>';
						str+='</tr>';
					}
					str+='</tbody>';
					str+='</table>';
					if($windowWidth < 768)
					{
						str+='</div>';
					}
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
				if($windowWidth < 768)
				{
					str+='<div class="table-responsive">';
				}
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
				if($windowWidth < 768)
				{
					str+='</div>';
				}
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
				if($windowWidth < 768)
				{
					str+='<div class="table-responsive">';
				}
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
				if($windowWidth < 768)
				{
					str+='</div>';
				}
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
				if($windowWidth < 768)
				{
					str+='<div class="table-responsive">';
				}
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
				if($windowWidth < 768)
				{
					str+='</div>';
				}
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
				if($windowWidth < 768)
				{
					str+='<div class="table-responsive">';
				}
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
				if($windowWidth < 768)
				{
					str+='</div>';
				}
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
				if($windowWidth < 768)
				{
					str+='<div class="table-responsive">';
				}
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
				if($windowWidth < 768)
				{
					str+='</div>';
				}
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
				if($windowWidth < 768)
				{
					str+='<div class="table-responsive">';
				}
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
				if($windowWidth < 768)
				{
					str+='</div>';
				}
			str+='</div>';
		str+='</div>';
	}
	$("#nregsConsitenBodyId").html(str);
	$(".dataTableCls").dataTable({
		"order": [[ 1, "asc" ]]
	});
	$(".dataTableClsDist").dataTable();
	
}

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
		getLabourBudgetClickingOverview();
	else
		getNregasPopupOverview();
	getNREGSConsCuntData(locationType,type,globalDivName);
});

//PopupOverview clicking Block 
function buildPopupOverviewBlock(result,projectDivId){
	var str1='';
	if($windowWidth < 768)
	{
		str1+='<div class="table-responsive">';
	}
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
							str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
							if(result.villagesInRed != null && result.villagesInRed > 0)
								str1+='<p class="media-body detailsCls" attr_location="panchayat" attr_type="red">Villages in Red : '+result.villagesInRed+'</p>';
							else
								str1+='<p class="media-body" attr_location="panchayat" attr_type="red">Villages in Red : '+result.villagesInRed+'</p>';
						str1+='</div>';
						str1+=' <div class="media">';
						   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p>';
						   if(result.villagesInOrange != null && result.villagesInOrange > 0)
								str1+='<p class="media-body detailsCls" attr_location="panchayat" attr_type="orange">Villages in Orange : '+result.villagesInOrange+'</p>';
							else
								str1+='<p class="media-body " attr_location="panchayat" attr_type="orange">Villages in Orange : '+result.villagesInOrange+'</p>';
						str1+='</div>';
						str1+='<div class="media">';
							str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
							if(result.villagesInGreen != null && result.villagesInGreen > 0)
								str1+='<p class="media-body detailsCls" attr_location="panchayat" attr_type="green">Villages in Green : '+result.villagesInGreen+'</p>';
							else
								str1+='<p class="media-body" attr_location="panchayat" attr_type="green">Villages in Green : '+result.villagesInGreen+'</p>';
						str1+='</div>';
					str1+='</div>';	
				str1+='</td>';
			str1+='</tr>';
		str1+='</tbody>';
	str1+='</table>';
	if($windowWidth < 768)
	{
		str1+='</div>';
	}
	$("#nregsOverviewBodyId").html(str1);
	
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
						str+='<td>'+ajaxresp[i].percentage+'</td>';
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
			buildNregasOverViewBlock(ajaxresp,projectDivId);
		}
	});
}

function getNregasPopupOverview()
{
	$("#nregsOverviewBodyId").html(spinner);
	var json = {
		year : "2017",
		fromDate : glStartDate,
        toDate : glEndDate,
		divType : globalDivName
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
			buildPopupOverviewBlock(ajaxresp);
		}
	});
}

function getNregaLevelsWiseDataFrNewCalls(divIdd,locationType)
{
	$("#"+divIdd).html(spinner);
	var theadArr = [locationType,'Target','Achivement','Percentage'];
	if(locationType == "constituency")
		theadArr = ["district",locationType,'Target','Achivement','Percentage'];
	else if(locationType == "mandal")
		theadArr = ["district","constituency",locationType,'Target','Achivement','Percentage'];
	else if(locationType == "panchayat")
		theadArr = ["district","constituency","mandal",locationType,'Target','Achivement','Percentage'];
	
	var json = {
		year : "2017",
		fromDate : glStartDate,
		toDate : glEndDate,
		locationType: locationType,
		divType : globalDivName
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
						str+='<td>'+ajaxresp[i].percentage+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataFrAgriculture(divIdd,locationType)
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
		locationType: locationType,
		divType : globalDivName
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
						str+='<td>'+ajaxresp[i].achivement+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataFrHorticulture(divIdd,locationType)
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
		locationType: locationType,
		divType : globalDivName
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
						str+='<td>'+ajaxresp[i].pencentageOfPlanting+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataFrAvenue(divIdd,locationType)
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
		year 		: "2017",
		fromDate 	: glStartDate,
		toDate 		: glEndDate,
		locationType: locationType,
		divType 	: globalDivName
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
						str+='<td>'+ajaxresp[i].pencentageOfPlanting+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}


//var overViewArr = ['Labour Budget','Farm Ponds','IHHL','Vermi Compost','CC Roads','Anganwadi','Gram Panchayat Buildings','Mandal buildings','NTR 90 Days','Production of Bricks','Mulbery','Silk worm','Cattle drinking water trough','Raising of Perinnial Fodder','Solid Waste Management','Play Fields','Burial Grounds','Fish Drying Platforms','Fish Ponds','Agriculture Activities','Average Wage','Avg days of emp per HH','HH Comp 100 days','Timely Payments','Horticulture','Avenue'];
var overViewArr = ['Labour Budget','Farm Ponds','IHHL','Vermi Compost','Solid Waste Management','Burial Grounds','Play Fields','Agriculture Activities','Average Wage','Average Days of Employment','HH Completed 100 Days','Timely Payment','CC Roads','Anganwadi Buildings','GP Buildings','Mandal Buildings','NTR 90 Days','Production of Bricks','Mulbery','Silk Worms','Cattle Drinking Water Troughs','Raising of Perinnial Fodders','Horticulture','Avenue','Fish Ponds','Fish Drying Platforms'];//,'Nurseries','Payments'
buildNREGSProjectsOverview(overViewArr,'')
for(var i in overViewArr)
{
	$("[overview-block='"+overViewArr[i]+"']").append(spinner);
	if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
		getNREGSProjectsAbstractNew(overViewArr[i],'state',0,'');
	else
		getNREGSAbstractDataByType(overViewArr[i],'state',0,'');
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

function getNregaLevelsWiseDataForCCRoads(divIdd,locationType)
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
		locationType: locationType,
		divType : globalDivName
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
						str+='<td>'+ajaxresp[i].percentage+'</td>';
					str+='</tr>';
				}
			}
			tableView(divIdd,theadArr,str,locationType);
		}
	});
}

function getNregaLevelsWiseDataForTimelyPayments(divIdd,locationType)
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
		divType : globalDivName
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
function getNREGSProjectsAbstractNew(type,locType,locId,blockName)
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
			if(ajaxresp != null && ajaxresp.length > 0){
				buildNREGSAbstractDataByType(type,ajaxresp,blockName)
			}
		}
	});
}
function getNREGSAbstractDataByType(type,locType,locId,blockName)
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
			if(ajaxresp != null && ajaxresp.length > 0){
				buildNREGSAbstractDataByType(type,ajaxresp,blockName)
			}
			
		}
	});
}
function buildNREGSAbstractDataByType(type,result,blockName)
{
	$("[overview-block='"+type+"']").removeClass("panel-block-white");
	var str='';
	if(result[0].percentage < 50)
	{
		str+='<div class="panel-black-white panel-block-white-low text-center" overview-district="'+type+'">';
	}else if(result[0].percentage > 50 && result[0].percentage < 80)
	{
		str+='<div class="panel-black-white panel-block-white-medium text-center" overview-district="'+type+'">';
		
	}else if(result[0].percentage > 80)
	{
		str+='<div class="panel-black-white panel-block-white-high text-center" overview-district="'+type+'">';
	}
	
	
		str+='<h4 class="panel-block-white-title text-capitalize text-center">'+type+'</h4>';
		str+='<small class="text-center">Achieved</small>';
			str+='<h1 class="text-center">'+result[0].percentage+'<small>%</small>';
		if(result[0].percentage < 50)
		{
			//$("[overview-block='"+type+"']").addClass("panel-block-white-low");
			str+='<small><i class="fa fa-long-arrow-down"></i></small></h1>';
		}else if(result[0].percentage > 50 && result[0].percentage < 80)
		{
			//$("[overview-block='"+type+"']").addClass("panel-block-white-medium");
			str+='<small><i class="fa fa-arrows-v"></i></small></h1>';
			
		}else if(result[0].percentage > 80)
		{
			//$("[overview-block='"+type+"']").addClass("panel-block-white-high");
			str+='<small><i class="fa fa-long-arrow-up"></i></small></h1>';
		}
		str+='<div class="row">';
			str+='<div class="col-sm-6 text-center">';
				str+='<label>Target</label>';
				str+='<h4>'+result[0].target+'</h4>';
			str+='</div>';
			str+='<div class="col-sm-6 text-center">';
				str+='<label>Completed</label>';
				str+='<h4>'+result[0].completed+'</h4>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	if(result.length > 1)
	{
		if(result[1].percentage < 50)
		{
			str+='<div class="panel-black-white panel-block-white-low text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
		}else if(result[1].percentage > 50 && result[1].percentage < 80)
		{
			str+='<div class="panel-black-white panel-block-white-medium text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
			
		}else if(result[1].percentage > 80)
		{
			str+='<div class="panel-black-white panel-block-white-high text-center" overview-state="'+type+'" style="border-top:1px solid #333;">';
		}
			str+='<h4 class="panel-block-white-title text-capitalize text-center">STATE LEVEL - ACHIEVED</h4>';
			str+='<h1 class="text-center">'+result[1].percentage+'</h1>';
		str+='</div>';
	}
	$("[overview-block='"+type+"']").html(str);
	if(type == blockName)
	{
		$("[overview-block='"+blockName+"']").trigger("click");
	}
}
$(document).on("click",".menuDataCollapse",function(){
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
	if(levelId == 3)
	{
		for(var i in overViewArr)
		{
			$("[overview-block='"+overViewArr[i]+"']").html(spinner);
			if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
				getNREGSProjectsAbstractNew(overViewArr[i],'state',0,'');
			else
				getNREGSAbstractDataByType(overViewArr[i],'district',locId,blockName)
		}
	}else if(levelId == 2)
	{
		for(var i in overViewArr)
		{
			$("[overview-block='"+overViewArr[i]+"']").html(spinner);
			if(overViewArr[i] == 'Solid Waste Management' || overViewArr[i] == 'Burial Grounds' || overViewArr[i] == 'Play Fields' || overViewArr[i] == 'CC Roads' || overViewArr[i] == 'Anganwadi Buildings' || overViewArr[i] == 'GP Buildings' || overViewArr[i] == 'Mandal Buildings' || overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms')
				getNREGSProjectsAbstractNew(overViewArr[i],'state',0,'');
			else
				getNREGSAbstractDataByType(overViewArr[i],'state',0,blockName)
		}
	}
	$(".multi-level-selection-menu").hide();
});