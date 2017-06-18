onLoadCalls();
function onLoadCalls()
{
	getNREGSProjectsOverview()
	$(document).on('click','[overview-block]', function(){
		$("[overview-block]").removeClass("active");
		$(this).addClass("active");
		var projectDivId = $(this).attr("overview-block");
		projectData(projectDivId)
		if(projectDivId == 'Labour Budget')
		{
			
		}else if(projectDivId == 'VERMI')
		{
			getNregsVermiOverview(projectDivId);
		}
	});
	
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
	
	var json = {
			year:"2017",
			fromDate:"2017-04-01",
			toDate:"2017-06-30"
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
					str+='<small><i class="fa fa-long-arrow-top"></i></small></h1>';
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
}

function getNregsVermiOverview(projectDivId)
{
	
	var json = {
			year:"2017",
			fromDate:"2017-04-01",
			toDate:"2017-06-30"
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
	var json = {
			year:"2017",
			fromDate:"2017-04-01",
			toDate:"2017-06-30",
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

function getNREGSIHHLOverview()
{

  var json = {
    year : "2017",
      fromDate : "2017-04-01",
        toDate : "2017-06-30"  
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
      
    }
  });
}

//IHHL Levelwise Data Call — Nandhini

function getNREGSIHHLLelwiseData(divIdd,locationType,theadArr)
{

  var json = {
    year : "2017",
    fromDate : "2017-04-01",
      toDate : "2017-06-30",
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

function getNREGSLabourBudgetOverview()
{
  var json = {
    year : "2017",
      fromDate : "2017-04-01",
        toDate : "2017-06-30"  
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
      
    }
  });
}

//LabourBudget Expenditure Call  — Sravanth

function getNREGSLabourBudgetExpenditure()
{
  var json = {
    year : "2017",
      fromDate : "2017-04-01",
        toDate : "2017-06-30"  
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
      
    }
  });
}

//LabourBudget LevelWise Data Call — Sravanth

function getNREGSLabBugdtLelwiseData(divIdd,locationType)
{
	var theadArr = [locationType,'Target Person days upto 31st May','Generated Person days','(%) Achivement Vs Approved Labour Budget days','Average Wage rate','Total Expanditure( in Crs)'];
  var json = {
    year : "2017",
    fromDate : "2017-04-01",
    toDate : "2017-06-30",
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

function getNREGSFarmPondOverview()
{
  var json = {
    year : "2017",
      fromDate : "2017-04-01",
        toDate : "2017-06-30"  
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
      
    }
  });
}

//FarmPond Levelwise Data Call — Swapna

function getNREGSFarmPondLelwiseData(divIdd,locationType,theadArr)
{

  var json = {
    year : "2017",
    fromDate : "2017-04-01",
      toDate : "2017-06-30",
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
	var str="";
	var str1="";
	str1+='<div class="panel panel-default panel-black">';
		str1+='<div class="panel-heading">';
			str1+='<h4 class="panel-title text-capital">'+projectDivId+' overview</h4>';
		str1+='</div>';
		str1+='<div class="panel-body">';
			str1+='<table class="table table-bordered" >';
				str1+='<tbody>';
					str1+='<tr>';
						str1+='<td>';
							str1+='<div class="col-sm-9">';
								str1+='<div class="media">';
									str1+='<p class="media-body">Average Per District : '+result.averagePerDistrict+'</p>';
								str1+='</div>';
							str1+='</td>';
							str1+='<td>';
								str1+='<div class="media">';
								str1+='<p class="media-body">Total Avg farms in District : '+result.totalAvgFarmsInDistrict+'</p>';
									str1+='</div>';
								/*str1+='<div class="media">';
									str1+='<p class="media-body">Average Per Constituency : '+result.averagePerConstituency+'</p>';
								str1+='</div>';*/
							str1+='</td>';
							str1+='</tr>';
							str1+='<tr>';
								str1+='<td>';
									str1+='<div class="media">';
									str1+='<p class="media-body">Average Per Constituency : '+result.averagePerConstituency+'</p>';
								/*str1+='</div>*/
								str1+='</td>';
								str1+='<td>';
									str1+='<div class="media">';
								str1+='<p class="media-body">Total Avg farms in Constituency : '+result.totalAvgFarmsInConstituency+'</p>';
									str1+='</div>';
									/*str1+='<div class="media">';
									str1+='<p class="media-body">Average Per Constituency : '+result.averagePerConstituency+'</p>';
								str1+='</div>*/
								str1+='</td>';
								str1+='</tr>';
								str1+='<tr>';
									str1+='<td>';
										str1+='<div class="media">';
										str1+='<p class="media-body">Average Per Mandal : '+result.averagePerMandal+'</p>';
										str1+='</div>';
									str1+='</td>';
									str1+='<td>';
										str1+='<div class="media">';
										str1+='<p class="media-body">Total Avg farms in Mandal: '+result.totalAvgFarmsInMandal+'</p>';
									str1+='</div>';
									str1+='</td>';
								str1+='</tr>';
							str1+='<tr>';
								str1+'<td>';
									str1+='<div class="col-sm-3">';
									str1+='<p>Total Budget</p>';
									str1+='<h4>'+result.totalBudget+'</h4>';
									str1+='</div>';
								str1+='</td>';
								str1+='<td>';
									str1+='<div class="col-sm-3">';
									str1+='<p>Total Farms</p>';
									str1+='<h4>0</h4>';
									str1+='</div>';
								str1+='</td>';
							str1+='</tr>';
								/*str1+='</div>';
						str1+='</td>';
						str1+='<td>';
							str1+='<div class="col-sm-9">';
								str1+='<div class="media">';
								str1+='<p class="media-body">Total Avg farms in District : '+result.totalAvgFarmsInDistrict+'</p>';
									str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-body">Total Avg farms in Constituency : '+result.totalAvgFarmsInConstituency+'</p>';
									str1+='</div>';
								str1+='<div class="media">';
									str1+='<p class="media-body">Total Avg farms in Mandal: '+result.totalAvgFarmsInMandal+'</p>';
								str1+='</div>';
								
							str1+='<div class="col-sm-3">';
								str1+='<p>Total Farms</p>';
								str1+='<h4>0</h4>';
							str1+='</div>';
							str1+='</div>';
							
						str1+='</td>';
						
					str1+='</tr>';*/
				str1+='</tbody>';
			str1+='</table>';
			 str1+='<table class="table table-bordered" >';
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
									str1+='<p class="media-body">District in Orange : '+result.districtsInOrgange+'</p>';
								str1+='</div>';
								str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p>';
									str1+='<p class="media-body">District in Green : '+result.districtsInGreen+'</p>';
								str1+='</div>';
								str1+='<div class="col-sm-3">';
								str1+='<p>Total District</p>';
								str1+='<h4>'+result.totalDistricts+'</h4>';
							str1+='</div>';
							str1+='</div>';
						str1+='</td>';
						str1+='<td>';
							str1+='<div class="col-sm-9">';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p>';
								str1+='<p class="media-body">Constituencies in Red : '+result.constituenciesInRed+'</p>';
									str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Constituencies in Orange : '+result.constituenciesInOrgange+'</p>';
									str1+='</div>';
								str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Constituencies in Green : '+result.constituenciesInGreen+'</p>';
								str1+='</div>';
								
							str1+='<div class="col-sm-3">';
								str1+='<p>Total Constituencies</p>';
								str1+='<h4>'+result.totalConstituencies+'</h4>';
							str1+='</div>';
							str1+='</div>';
							
						str1+='</td>';
						str1+='<td>';
							str1+='<div class="col-sm-9">';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p><p class="media-body">Mandals in Red : '+result.mandalsInRed+'</p>';
									str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Mandals in Orange : '+result.mandalsInOrgange+'</p>';
								str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Mandals in Green : '+result.mandalsInGreen+'</p>';
								str1+='</div>';
								
							str1+='<div class="col-sm-3">';
								str1+='<p>Total Mandals</p>';
								str1+='<h4>'+result.totalMandals+'</h4>';
							str1+='</div>';
							str1+='</div>';
						str1+='</td>';
						str1+='<td>';
							str1+='<div class="col-sm-9">';
								str1+='<div class="media">';
									str1+='<p class="media-left"><img src="Assests/icons/Red.png"></p><p class="media-body">Villages in Red : '+result.villagesInRed+'</p>';
								str1+='</div>';
							  str1+=' <div class="media">';
								   str1+='<p class="media-left"><img src="Assests/icons/Orange.png"></p><p class="media-body">Villages in Orange : '+result.villagesInOrgange+'</p>';
								str1+='</div>';
								str1+='<div class="media">';
								str1+='<p class="media-left"><img src="Assests/icons/Green.png"></p><p class="media-body">Villages in Green : '+result.villagesInGreen+'</p>';
								str1+='</div>';
								
							str1+='<div class="col-sm-3">';
								str1+='<p>Total Villages</p>';
								str1+='<h4>'+result.totalVillages+'</h4>';
							str1+='</div>';
							str1+='</div>';
						str1+='</td>';
					str1+='</tr>';
				str1+='</tbody>';
			str1+='</table>';
		str1+='</div>';
	str1+='</div>';		
			
	
	$("#nregasAvgOverData").html(str1);
}