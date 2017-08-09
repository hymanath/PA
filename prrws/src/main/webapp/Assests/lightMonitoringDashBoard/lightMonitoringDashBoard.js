var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = '2017-04-01'//moment().startOf('year').format("YYYY-MM")+'-1';
var glEndDate = moment().format("YYYY-MM")+'-30';

onLoadCalls();
function onLoadCalls()
{
	getLedOverviewForStartedLocationsDetailsCounts();
	getBasicLedOverviewDetails();
	getLevelWiseOverviewDetailsData();
	getLevelWiseOverviewDetails();
	projectData('',2)
}
function getLedOverviewForStartedLocationsDetailsCounts(){
	var json = {
		fromDate:glStartDate,
		toDate:glEndDate
	}
	$.ajax({                
		type:'POST',    
		url: 'getLedOverviewForStartedLocationsDetailsCounts',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			buildLedOverviewForStartedLocationsDetailsCounts(result);	
		}
	});
}
function getBasicLedOverviewDetails(){
	$("#overviewBlockId").html(spinner);
	var json = {
		fromDate:glStartDate,
		toDate:glEndDate
	}
	$.ajax({                
		type:'POST',    
		url: 'getBasicLedOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		buildBasicLedOverviewDetails(result);
	});		
}
function getLevelWiseOverviewDetailsData(){
	$("#overviewBlockId").html(spinner);
	var json = {
		"fromDateStr": "01-08-2017",
		"toDateStr": "01-08-2017",
		"locationTypeId" : 2,
		"locationValues" : [0]
	}
	$.ajax({                
		type:'POST',    
		url: 'getDistrictLevelWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		buildBasicLedOverviewDetails(result);
	});		
}
function buildLedOverviewForStartedLocationsDetailsCounts(result){
var str='';
	str+='<div class="col-sm-12 border_top padding_10" style="background-color:#F9F9F9;">';
				str+='<div class="col-sm-2 media">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/Start_Date_icon.png" alt="start_date">';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <h5>SURVEY START</h5>';
					   str+=' <h3>01 JUN 2017</h3>';
					str+='</div>';
				str+='</div>';
			   str+=' <div class="col-sm-2 media">';
					str+='<div class="media-left">';
					   str+=' <img src="Assests/icons/End_Date_icon.png" alt="end_date">';
					str+='</div>';
				   str+=' <div class="media-body">';
						str+='<h5>SURVEY END</h5>';
					   str+=' <p>Expected Date</p>';
						str+='<h3>20 JUN 2017</h3>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-2 media">';
					str+='<div class="media-left">';
					   str+=' <img src="Assests/icons/District_Survy_icon.png" alt="start_date">';
				   str+=' </div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#827C13;">DISTRICTS</span> SURVEY SATRTED</h5>';
					   str+=' <h3>'+result[0].totalDistCnt+'</h3>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-2 media">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/Constituency_Survy_icon.png" alt="start_date">';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <h5>NO OF <span style="color:#02B0AC;">CONSTITUENCIES</span>SURVEY STARTED</h5>';
						str+='<h3>'+result[0].totalConstituencyCnt+'</h3>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-2 media">';
					str+='<div class="media-left">';
					   str+=' <img src="Assests/icons/Mandal_survy_icon.png" alt="start_date">';
				   str+=' </div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#00BFE8;">MANDALS</span>SURVEY SATRTED</h5>';
							str+='<h3>'+result[0].totalMandalCnt+'</h3>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-2 media">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/GPs_survey_icon.png" alt="start_date">';
					str+='</div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#F45CB5;">GRAM PANCHAYAT</span>SURVEY STARTED</h5>';
						str+='<h3>'+result[0].totalpanchayatCnt+'</h3>';
					str+='</div>';
				str+='</div>';
   str+='</div>';
  $("#ledOverViewDiv").html(str);
}
function getLevelWiseOverviewDetails(){
	var json = {
		

			"fromDateStr"      :"03-08-2017",
			"toDateStr"        :"03-08-2017",
			"year"             : "2017" ,
			"locationValues"   : [0],
			"locationTypeId"   : 2,
			"searchLevelId"    :  2,
			"searchLevelValues": [0]
	}
	$.ajax({                
		type:'POST',    
		url: 'getLevelWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
	});
	
}

function buildBasicLedOverviewDetails(result)
{
	var str='';
	str+='<div class="col-sm-2 lightsBlock">';
		str+='<img src="Assests/icons/On_Off_light_icon.png">';
		str+='<p>ON/OFF LIGHTS</p>';
		str+='<h4>'+result[0].offLights+'/'+result[0].onLights+'</h4>';
	str+='</div>';
	str+='<div class="col-sm-10" style="padding-top:13px;">';
		str+='<div class="col-sm-2 media">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Poles_icon.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#669FF5;">TOTAL POLES</h5>';
				str+='<h3>'+result[0].totalPoles+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 media">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/CCMS_Box_icon.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#8E51Db">TOTAL CCMS-BOX/ PANELS</h5>';
				str+='<h3>'+result[0].totalPanels+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-2 media">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Total_Led_lights_iocn.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#827C13">TOTAL LIGHTS</h5>';
				str+='<h3>'+result[0].totalLights+'</h3>';
			str+='</div>';
		str+='</div>';
	
		str+='<div class="col-sm-2 media">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Operational_LED_Light_Icon.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#339900;">OPERATIONAL</h5>';
				str+='<h3>'+result[0].workingLights+'</h3>';
			str+='</div>';
		str+='</div>';
	
		str+='<div class="col-sm-2 media">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Non_Operational_LED_Light_Ico.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#FF3333;">NON-OPERATIONAL</h5>';
				str+='<h3>'+result[0].notWorkingLights+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-12 border_dashed padding_10">';
			str+='<ul class="nav navbar-nav">';
				for(var i in result[0].wattageList)
				{
					str+='<li><b>'+result[0].wattageList[i].wattage+'W = '+result[0].wattageList[i].lightCount+'</b></li>';
				}
			str+='</ul>';
		str+='</div>';
	str+='</div>';
	$("#overviewBlockId").html(str);
}
function projectData(divId,levelId)
{
	var collapse='';
	var dataArr = '';
	if(levelId == 2)
	{
		dataArr = ['district','constituency','mandal'];
	}else if(levelId == 3)
	{
		dataArr = ['constituency','mandal'];
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
									collapse+='<a role="button" class="panelCollapseIcon" overview-levelId="'+levelId+'" data-toggle="collapse" data-parent="#accordion'+dataArr[i]+'" href="#collapse'+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+dataArr[i]+'">';
								}else{
									collapse+='<a role="button" class="panelCollapseIcon collapsed" overview-levelId="'+levelId+'" overview-level="'+dataArr[i]+'" data-toggle="collapse" data-parent="#accordion'+dataArr[i]+'" href="#collapse'+dataArr[i]+'" aria-expanded="true" aria-controls="collapse'+dataArr[i]+'">';
								}
									collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level overview</h4>';
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+dataArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+dataArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+dataArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div id="'+dataArr[i]+'TableId"></div>';
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
		$("#"+dataArr[i]+"TableId").html(spinner);
		tableView(dataArr[i]);
	}				
}
function tableView(divId)
{
	var tableView = '';
	tableView+='<table class="table" id="districtTable">';
		tableView+='<thead>';
			tableView+='<tr>';
				if(divId == 'district')
				{
					tableView+='<th>DISTRICT</th>';
				}else if(divId == 'constituency')
				{
					tableView+='<th>CONSTITUENCY</th>';
				}else if(divId == 'mandal')
				{
					tableView+='<th>MANDAL</th>';
				}
				
				tableView+='<th><img src="Assests/icons/mandals_icon.png"><br/>TOTAL MANDALS</th>';
				tableView+='<th><img src="Assests/icons/Mandal_survy_icon.png"><br/>SURVEY STARTED MANDALS</th>';
				tableView+='<th><img src="Assests/icons/GPs_icon.png"><br/>TOTAL GPs</th>';
				tableView+='<th><img src="Assests/icons/GPs_survey_icon.png"><br/>SURVEY STARTEDGPs</th>';
				tableView+='<th><img src="Assests/icons/Poles_icon.png"><br/>TOTAL POLES SURVEYED</th>';
				tableView+='<th><img src="Assests/icons/CCMS_Box_icon.png"><br/>TOTAL CCMS-BOX/ PANELS INSTALLED</th>';
				tableView+='<th><img src="Assests/icons/Total_Led_lights_iocn.png"><br/>TOTAL LED LIGHTS INSTALLED</th>';
				tableView+='<th><img src="Assests/icons/Operational_LED_Light_Icon.png"><br/>OPERATIONAL</th>';
				tableView+='<th><img src="Assests/icons/On_light_icon.png"><br/>ON</th>';
				tableView+='<th><img src="Assests/icons/Off_Light_Icon.png"><br/>OFF</th>';
			tableView+='</tr>';
		tableView+='</thead>';
		tableView+='<tbody>';
			tableView+='<tr>';
				tableView+='<td>Srikakulam</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
				tableView+='<td>38</td>';
			tableView+='</tr>';
		tableView+='</tbody>';
	tableView+='</table>';

	$("#"+divId+"TableId").html(tableView);
}