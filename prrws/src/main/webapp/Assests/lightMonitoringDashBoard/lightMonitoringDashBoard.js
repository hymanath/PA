var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var glStartDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
var glStartDate = moment().format('DD-MM-YYYY');
var glEndDate = moment().format('DD-MM-YYYY');
onLoadCalls();
function onLoadCalls()
{
	getLedOverviewForStartedLocationsDetailsCounts();
	getBasicLedOverviewDetails();
	projectData('',2)
	
	$(".chosen-select").chosen();
	
	$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
}
$(document).on('click','.calendar_active_cls li', function(){
	var date = $(this).attr("attr_val");
	$(".tableMenu li").removeClass("active");
	$(".tableMenu li:first-child").addClass("active");
	
	
	if(date == 'Today')
	{
		glStartDate = moment().format('DD-MM-YYYY');
		glEndDate = moment().format('DD-MM-YYYY');
		$("#selectedDateIvr").html("TODAY");
	}else if(date == 'Week'){
		glStartDate = moment().subtract(1,'week').format('DD-MM-YYYY');
		glEndDate = moment().format('DD-MM-YYYY');
		$("#selectedDateIvr").html("WEEK");
	}else if(date == 'Month'){
		glStartDate = moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY');
		glEndDate = moment().format('DD-MM-YYYY');
		$("#selectedDateIvr").html("MONTH");
	}else if(date == '3Months'){
		glStartDate = moment().subtract(3,'month').startOf("month").format('DD-MM-YYYY');
		glEndDate = moment().format('DD-MM-YYYY');
		$("#selectedDateIvr").html("3 MONTHS");
	}else if(date == '6Months'){
		glStartDate = moment().subtract(6,'month').startOf("month").format('DD-MM-YYYY');
		glEndDate = moment().format('DD-MM-YYYY');
		$("#selectedDateIvr").html("6 MONTHS");
	}else if(date == 'Overall'){
		glStartDate = moment().subtract(15,'years').startOf("year").format('DD-MM-YYYY');
		glEndDate = moment().format('DD-MM-YYYY');
		$("#selectedDateIvr").html("OVERALL");
	}
	
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	if(date != "custom"){
		onLoadCalls();
	}
	
});
$("#singleDateRangePicker").daterangepicker({
		opens: 'left',
		startDate: glStartDate,
		endDate: glEndDate,
		locale: {
		  format: 'DD-MM-YYYY'
		}
	});
$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	
	glStartDate = picker.startDate.format('DD-MM-YYYY')
	glEndDate = picker.endDate.format('DD-MM-YYYY')
	onLoadCalls();
	
});
$(document).on("click",".daterangeViewCls",function(){
		$(".dateRangeWiseDetails").show();
});
$(document).on("click",".daterangeViewLiveCls",function(){
		$(".dateRangeWiseDetails").hide();
});
function getLedOverviewForStartedLocationsDetailsCounts(){
	$("#ledOverViewDiv").html(spinner);
	var json = {
		fromDate:glStartDate,
		toDate:glEndDate,
		locationType:"",
		locationValue:0
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
		$("#ledOverViewDiv").html('');
		if(result != null){
			buildLedOverviewForStartedLocationsDetailsCounts(result);	
		}
	});
}
function getBasicLedOverviewDetails(){
	$("#overviewBlockId").html(spinner);
	var json = {
		fromDate:glStartDate,
		toDate:glEndDate,
		locationType:"",
		locationValue:0
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
		if (result != null ) {
		 buildBasicLedOverviewDetails(result);	
		}
		
	});		
}
function buildLedOverviewForStartedLocationsDetailsCounts(result){
	var str='';
	str+='<div class="col-sm-12 border_top padding_10" style="background-color:#F9F9F9;">';
				 /*str+='<div class="col-sm-2 media">';
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
				str+='</div>';*/
				str+='<div class="col-sm-3 media">';
					str+='<div class="media-left">';
					   str+=' <img src="Assests/icons/District_Survy_icon.png" alt="start_date">';
				   str+=' </div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#827C13;">DISTRICTS</span> SURVEY SATRTED</h5>';
					   str+=' <h3>'+result[0].totalDistCnt+'</h3>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-3 media">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/Constituency_Survy_icon.png" alt="start_date">';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <h5>NO OF <span style="color:#02B0AC;">CONSTITUENCIES</span>SURVEY STARTED</h5>';
						str+='<h3>'+result[0].totalConstituencyCnt+'</h3>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-3 media">';
					str+='<div class="media-left">';
					   str+=' <img src="Assests/icons/Mandal_Survy_icon.png" alt="">';
				   str+=' </div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#00BFE8;">MANDALS</span>SURVEY SATRTED</h5>';
							str+='<h3>'+result[0].totalMandalCnt+'</h3>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-3 media">';
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
function getAllLevelWiseDataOverView(locType,filterType,locId,divId){
	$("#"+divId+"TableId").html(spinner);
	var json = {
			"locationType"      :locType,
			"filterType"         : filterType ,
			"locationId"   : locId,
			"fromDate": glStartDate,
		    "toDate": glEndDate
		}
	$.ajax({                
		type:'POST',    
		url: 'getAllLevelWiseDataOverView',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#"+divId+"TableId").html('');
		if(result != null && result.length > 0)
		{
			tableView(result,divId,locType);
		}else{
			$("#"+divId+"TableId").html("NO DATA AVAILABLE.");
		}
	});
}

function buildBasicLedOverviewDetails(result)
{
	var str='';
	str+='<div class="col-sm-2 lightsBlock">';
		str+='<img src="Assests/icons/On_Off_light_icon.png">';
		str+='<p>ON/OFF LIGHTS</p>';
		str+='<h4>'+result[0].onLights+'/'+result[0].offLights+'</h4>';
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
								    if(dataArr[i]=="district"){
									    collapse+='<h4 class="panel-title text-capital districtLevelHeadingDivCls">'+dataArr[i]+' level overview</h4>';	
									} else {
										collapse+='<h4 class="panel-title text-capital">'+dataArr[i]+' level overview</h4>';
									}
									
								collapse+='</a>';
							collapse+='</div>';
							if(i == 0)
							{
								collapse+='<div id="collapse'+dataArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+dataArr[i]+'">';
							}else{
								collapse+='<div id="collapse'+dataArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+dataArr[i]+'">';
							}
							
								collapse+='<div class="panel-body">';
									collapse+='<div class="row m_top10">';
										collapse+='<div class="col-sm-12">';
										collapse+='<div class="col-sm-3">';
											collapse+='<ul class="nav navbar-nav list_inline tableMenu" role="tabDrains_menu" attr_blockId="3">';
												collapse+='<li class="active ledResultTypeCls"  attr_location_level='+dataArr[i]+'  attr_tab_type="district">Districts</li>';
												collapse+='<li class="ledResultTypeCls" attr_location_level='+dataArr[i]+' attr_tab_type="parliament">Parliament</li>';
											collapse+='</ul>';
										collapse+='</div>';
									if(dataArr[i] == "constituency"){
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="consLvlLedDistrictSelectBoxId" attr_location_level="constituency" attr_filter_type="district" attr_sub_location_type="" id="consLvlLedDistrictSelectBoxId">';
											collapse+='<option>ALL DISTRICT</option>';
											collapse+='</select>';
										collapse+='</div>';
										
									}
									if(dataArr[i] == "mandal"){
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="mandalLvlLedConstituencySelectBoxId" attr_child_div_id="mandalLvlLedConstituencySelectBoxId" attr_location_level="mandal" attr_filter_type="district" attr_sub_location_type="constituency" id="mandalLvlLedDistrictSelectBoxId">';
											collapse+='<option>ALL DISTRICT</option>';
											collapse+='</select>';
										collapse+='</div>';
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="mandalLvlLedDistrictSelectBoxId" attr_location_level="mandal" attr_filter_type="constituency" attr_sub_location_type="" id="mandalLvlLedConstituencySelectBoxId">';
											collapse+='<option>SELECT CONSTITUENCY</option>';
											collapse+='</select>';
										collapse+='</div>';
									}
									collapse+='</div>';
									collapse+='</div>';
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
		getAllLevelWiseDataOverView(dataArr[i],dataArr[i],"",dataArr[i]);
	}	
   getLocationBasedOnSelection("district","",0,"");
}
function tableView(result,divId,locType)
{
	var tableView = '';
	tableView+='<table class="table" id="'+divId+'Table">';
		tableView+='<thead>';
			tableView+='<tr>';
				if(divId == 'district')
				{
					tableView+='<th>'+locType.toUpperCase()+'</th>';
				}else if(divId == 'constituency')
				{
					tableView+='<th>CONSTITUENCY</th>';
				}else if(divId == 'mandal')
				{
					tableView+='<th>MANDAL</th>';
				}
				if(divId == 'district'){
				  tableView+='<th><img src="Assests/icons/mandals_icon.png"><br/>TOTAL MANDALS</th>';	
				}
				
				tableView+='<th><img src="Assests/icons/Mandal_Survy_icon.png"><br/>SURVEY STARTED MANDALS</th>';
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
			for(var i in result)
			{
				tableView+='<tr>';
					tableView+='<td>'+result[i].locationName+'</td>';
					if(divId == 'district'){
				     tableView+='<td>'+result[i].totalMandals+'</td>';	
				    }
					if(divId!="district"){
						if (result[i].surveyStartedtotalMandals > 0) {
							tableView+='<td>Yes</td>';
						} else {
						   tableView+='<td>No</td>';	
						}
					}else{
						tableView+='<td>'+result[i].surveyStartedtotalMandals+'</td>';
					}
					
					tableView+='<td>'+result[i].totalGps+'</td>';
					tableView+='<td>'+result[i].surveyStartedtotalGps+'</td>';
					tableView+='<td>'+result[i].totalPoles+'</td>';
					tableView+='<td>'+result[i].totalPanels+'</td>';
					tableView+='<td>'+result[i].totalLedLIghtInstalledCount+'</td>';
					tableView+='<td>'+result[i].workingLights+'</td>';
					tableView+='<td>'+result[i].onLights+'</td>';
					tableView+='<td>'+result[i].offLights+'</td>';
				tableView+='</tr>';
			}
		tableView+='</tbody>';
	tableView+='</table>';

	$("#"+divId+"TableId").html(tableView);
	$("#"+divId+"Table").dataTable({
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				orientation: "landscape",
				pageSize:'A3',
				customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}
			}
		]
	});
}

/* Filter Block Start */
$(document).on("click",".ledResultTypeCls",function() {
	$(this).parent().find(".ledResultTypeCls").removeClass("active");
	$(this).addClass("active");
	var locationLevel = $(this).attr("attr_location_level");
	var resultType = $(this).attr("attr_tab_type");
	var filterType="";
	var filterValue=0;
	if (locationLevel != null && locationLevel=="district") {
		if (resultType != null && resultType=="district" || resultType=="parliament") {
		  getAllLevelWiseDataOverView(resultType,filterType,filterValue,locationLevel);
		  $(".districtLevelHeadingDivCls").html(resultType+" level overview");
		  $("#districtLelvdlDistrictHeadingId").html(resultType.toUpperCase());
		} 
	}else if(locationLevel=="constituency" || locationLevel=="mandal") {
		var divId = '';
		 if(locationLevel=="constituency") {
			 divId = "consLvlLedDistrictSelectBoxId";
		 } else if (locationLevel=="mandal") {
			 divId = "mandalLvlLedDistrictSelectBoxId";
		 }
		$("#"+divId).attr("attr_filter_type",resultType);
		getLocationBasedOnSelection(resultType,filterType,filterValue,divId);
		getAllLevelWiseDataOverView(locationLevel,filterType,filterValue,locationLevel);
	}
});

$(document).on("change",".lebSelectBoxCls",function(){
	var locationValue = $(this).val();
	var locationLevel=$(this).attr("attr_location_level");
	var parentDivId=$(this).attr("attr_parent_div_id");
	var childDivId=$(this).attr("attr_child_div_id");
	var filterType = $(this).attr("attr_filter_type");
	var subLevel = $(this).attr("attr_sub_location_type");
	if(subLevel!='') {
		getLocationBasedOnSelection(subLevel,filterType,locationValue,childDivId);	
	}
	if (filterType=="constituency" && locationValue==0) {
		filterType = $("#"+parentDivId).attr("attr_filter_type");
		locationValue = $("#"+parentDivId).val();
	}
	
	getAllLevelWiseDataOverView(locationLevel,filterType,locationValue,locationLevel);
});

/* End */

function getLocationBasedOnSelection(locationType,filterType,filterValue,divId){
	var json = {
			"locationType"  : locationType,
			"filterType"    : filterType ,
			"locationId"    : filterValue,
		}
	$.ajax({                
		type:'POST',    
		url: 'getLocationBasedOnSelection',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
	   if (result != null && result.length > 0) {
		   buildSelextBoxRlst(result,divId,locationType)
	   }
	});
	function buildSelextBoxRlst(result,divId,locationType) {
		$("#"+divId).html('');
		  var str='';
		  str+='<option value="0">'+'ALL '+locationType.toUpperCase()+'</option>';
		 for(var i in result) {
			  str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';
		 }
		  if (locationType=="district") {
			  $("#consLvlLedDistrictSelectBoxId,#mandalLvlLedDistrictSelectBoxId").html(' ');
			  $("#consLvlLedDistrictSelectBoxId,#mandalLvlLedDistrictSelectBoxId").html(str);
			  $("#consLvlLedDistrictSelectBoxId,#mandalLvlLedDistrictSelectBoxId").trigger("chosen:updated");
		  } else {
			$("#"+divId).html(str);
			$("#"+divId).trigger("chosen:updated");
		  }
	}
}