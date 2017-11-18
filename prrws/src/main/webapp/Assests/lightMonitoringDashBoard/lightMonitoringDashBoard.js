var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var glStartDate = moment().format('DD-MM-YYYY');
var glEndDate = moment().format('DD-MM-YYYY');
var globallocId = 0;
var globallevelId = 0;
var globalLocationName='';
$("#selectedName").attr("attr_id","0");
$("#selectedName").attr("attr_levelidvalue","2");
//onLoadCalls();
 setTimeout(function(){
	callWebService();		
}, 1000);
$("header").on("click",".menu-cls",function(e){
		e.stopPropagation();
		$(".menu-data-cls").toggle();
	});
	$(document).on("click",function(){
		$(".menu-data-cls").hide();
	});
function onLoadCalls()
{
	$(".chosen-select").chosen();
	getLedOverviewForStartedLocationsDetailsCounts();
	getBasicLedOverviewDetails();
	getCompanyWiseLightMonitoringDtls();
	menuWiseDetails();
}

$(document).on("click",".menuDataCollapse",function(){
	globallocId = $(this).attr("attr_id");
	globallevelId = $(this).attr("attr_levelidvalue");
	globalLocationName=$(this).attr("attr_name");
	$("#selectedName").text($(this).html());
	$("#selectedName").attr("attr_id",globallocId);
	$("#selectedName").attr("attr_levelidvalue",globallevelId);
	$("#selectedName").attr("attr_name",globalLocationName);
	getLedOverviewForStartedLocationsDetailsCounts();
	getBasicLedOverviewDetails();
	getCompanyWiseLightMonitoringDtls();
	menuWiseDetails();
});
function menuWiseDetails(){
	if(globallevelId == 2 || globallevelId == 0){
		projectData('',2);
		showLightVendorSelectBox();
		$("#consLvlLedDistrictSelectBoxId_chosen").show();
		$("#mandalLvlLedDistrictSelectBoxId_chosen").show();
		$("#mandalLvlLedConstituencySelectBoxId_chosen").show();
		$("#panchayatLvlLedDistrictSelectBoxId_chosen").show();
		$("#panchayatLvlLedConstituencySelectBoxId_chosen").show();
		$("#panchayatLvlLedMandalSelectBoxId_chosen").show();
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").show();
	}else if(globallevelId == 3){
		projectData('',3)
		hideLightVendorSelectBox();
		$("#consLvlLedDistrictSelectBoxId_chosen").hide();
		$("#mandalLvlLedDistrictSelectBoxId_chosen").hide();
		$("#mandalLvlLedConstituencySelectBoxId_chosen").hide();
		$("#panchayatLvlLedDistrictSelectBoxId_chosen").hide();
		$("#panchayatLvlLedConstituencySelectBoxId_chosen").hide();
		$("#panchayatLvlLedMandalSelectBoxId_chosen").hide();
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
	}else if(globallevelId == 4){
		projectData('',4)
		hideLightVendorSelectBox();
		$("#consLvlLedDistrictSelectBoxId_chosen").hide();
		$("#mandalLvlLedDistrictSelectBoxId_chosen").hide();
		$("#mandalLvlLedConstituencySelectBoxId_chosen").hide();
		$("#panchayatLvlLedDistrictSelectBoxId_chosen").hide();
		$("#panchayatLvlLedConstituencySelectBoxId_chosen").hide();
		$("#panchayatLvlLedMandalSelectBoxId_chosen").hide();
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
	}else if(globallevelId == 5){
		projectData('',5)
		hideLightVendorSelectBox();
		$("#consLvlLedDistrictSelectBoxId_chosen").hide();
		$("#mandalLvlLedDistrictSelectBoxId_chosen").hide();
		$("#mandalLvlLedConstituencySelectBoxId_chosen").hide();
		$("#panchayatLvlLedDistrictSelectBoxId_chosen").hide();
		$("#panchayatLvlLedConstituencySelectBoxId_chosen").hide();
		$("#panchayatLvlLedMandalSelectBoxId_chosen").hide();
		$(".tableMenu li:nth-child(1)").addClass("active");
		$(".tableMenu li:nth-child(2)").hide();
		
	}
	
}
function hideLightVendorSelectBox() {
	$("#districtLevelLightsVendorSelectBoxId_chosen").hide();
	$("#constituencyLevelLightsVendorSelectBoxId_chosen").hide();
	$("#mandalLvlLightsVendorSelectBoxId_chosen").hide();
	$("#panchayatLvlLedLightsVendorSelectBoxId_chosen").hide();
}
function showLightVendorSelectBox() {
	$("#districtLevelLightsVendorSelectBoxId_chosen").show();
	$("#constituencyLevelLightsVendorSelectBoxId_chosen").show();
	$("#mandalLvlLightsVendorSelectBoxId_chosen").show();
	$("#panchayatLvlLedLightsVendorSelectBoxId_chosen").show();
}
$("#singleDateRangePicker").daterangepicker({
		opens: 'left',
		startDate: glStartDate,
		endDate: glEndDate,
		singleDatePicker:true,
		locale: {
		  format: 'DD-MM-YYYY'
		}
	});
$('#singleDateRangePicker').on('apply.daterangepicker', function(ev, picker) {
	
/* 	glStartDate = picker.startDate.format('DD-MM-YYYY');
	glEndDate = picker.endDate.format('DD-MM-YYYY'); */
	var selectDate = picker.endDate.format('DD-MM-YYYY');
	checkIsDataExist(selectDate);
});
$(document).on("click",".daterangeViewCls",function(){
		$(".dateRangeWiseDetails").show();
});
$(document).on("click",".daterangeViewLiveCls",function(){
		$(".dateRangeWiseDetails").hide();
});
function getLedOverviewForStartedLocationsDetailsCounts(){
	$("#ledOverViewDiv").html(spinner);
	var locationType="";
	var locationValue=0;
	if(globallevelId == 2 || globallevelId == 0){
		locationType = "";
		locationValue=0;
	}else if(globallevelId == 3){
		locationType = "district";
		locationValue=globallocId;
	}else if(globallevelId == 4){
		locationType = "constituency";
		locationValue=globallocId;
	}else if(globallevelId == 5){
		locationType = "mandal";
		locationValue=globallocId;
	}
 
	
	var json = {
		fromDate:glStartDate,
		toDate:glEndDate,
		locationType:locationType,
		locationValue:locationValue,
		lightVendorIdList:[2,1]
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
		if(result != null && result.length > 0){
			buildLedOverviewForStartedLocationsDetailsCounts(result);	
		} else {
		$("#ledOverViewDiv").html('NO DATA AVAILABLE.');	
		}
	});
}
function getBasicLedOverviewDetails(){
	$("#overviewBlockId").html(spinner);
	var locationType="";
	var locationValue=0;
	if(globallevelId == 2 || globallevelId == 0){
		locationType = "";
		locationValue=0;
	}else if(globallevelId == 3){
		locationType = "district";
		locationValue=globallocId;
	}else if(globallevelId == 4){
		locationType = "constituency";
		locationValue=globallocId;
	}else if(globallevelId == 5){
		locationType = "mandal";
		locationValue=globallocId;
	}
	
	var json = {
		fromDate:glStartDate,
		toDate:glEndDate,
		locationType:locationType,
		locationValue:locationValue,
		lightVendorIdList:[2,1]
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
	     $("#overviewBlockId").html(' ');
		if (result != null && result.length > 0) {
		   buildBasicLedOverviewDetails(result);	
		} else {
			$("#ledOverViewDiv").html("");
		}
		
	});		
}
function buildLedOverviewForStartedLocationsDetailsCounts(result){
	var str='';
	str+='<div class="col-sm-12 border_top padding_10" style="background-color:#F9F9F9;padding: 10px;">';
				str+='<div class="col-sm-3">';
					str+='<div class="media-left">';
					   str+=' <img src="Assests/icons/District_Survy_icon.png" alt="start_date">';
				   str+=' </div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#827C13;"><b>DISTRICTS</b></span><br/>SURVEY STARTED</h5>';
						if(result[0].totalDistCnt !=null && result[0].totalDistCnt>0){
							str+=' <h3 attr_location_type="district" attr_result_type="surveryStartedLocation" style="cursor:pointer;color:rgb(51, 122, 183)" class="surveyStartedLocationCountCls">'+result[0].totalDistCnt+'</h3>';
						}else{
							str+=' <h3>0</h3>';
						}
					   
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/Constituency_Survy_icon.png" alt="start_date">';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <h5>NO OF <span style="color:#02B0AC;"><b>CONSTITUENCIES</b></span><br/>SURVEY STARTED</h5>';
					   if(result[0].totalConstituencyCnt !=null && result[0].totalConstituencyCnt>0){
							str+=' <h3 attr_location_type="constituency" attr_result_type="surveryStartedLocation" style="cursor:pointer;color:rgb(51, 122, 183)" class="surveyStartedLocationCountCls">'+result[0].totalConstituencyCnt+'</h3>';
						}else{
							str+=' <h3>0</h3>';
						}
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<div class="media-left">';
					   str+=' <img src="Assests/icons/Mandal_Survy_icon.png" alt="">';
				   str+=' </div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#00BFE8;"><b>MANDALS</b></span><br/>SURVEY STARTED</h5>';
						if(result[0].totalMandalCnt !=null && result[0].totalMandalCnt>0){
							str+=' <h3 attr_location_type="mandal" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" class="surveyStartedLocationCountCls">'+result[0].totalMandalCnt+'</h3>';
						}else{
							str+=' <h3>0</h3>';
						}
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-3">';
					str+='<div class="media-left">';
						str+='<img src="Assests/icons/GPs_survey_icon.png" alt="start_date">';
					str+='</div>';
					str+='<div class="media-body">';
						str+='<h5>NO OF <span style="color:#F45CB5;"><b>GRAM PANCHAYAT</b></span><br/>SURVEY STARTED</h5>';
						if(result[0].totalpanchayatCnt !=null && result[0].totalpanchayatCnt>0){
							str+=' <h3 attr_location_type="panchayat" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" class="surveyStartedLocationCountCls">'+result[0].totalpanchayatCnt+'</h3>';
						}else{
							str+=' <h3>0</h3>';
						}
					str+='</div>';
				str+='</div>';
   str+='</div>';
  $("#ledOverViewDiv").html(str);
}
function getAllLevelWiseDataOverView(locType,filterType,locId,divId,lightVendorArr){
	$("#"+divId+"TableId").html(spinner);
		var locationId='';
		var locationType='';
		var filterTypeVal='';
		
		if(globallevelId ==2 || globallevelId ==0){
			locationId = locId;
			locationType =locType;
			filterTypeVal = filterType;	
		}else if(globallevelId ==3){
			locationId = globallocId;
			locationType =locType;
			filterTypeVal = "district";	
		}else if(globallevelId ==4){
			locationId = globallocId;
			locationType =locType;
			filterTypeVal = "constituency";	
		}else if(globallevelId ==5){
			locationId = globallocId;
			locationType =locType;
			filterTypeVal = "mandal";	
		}/* else if(globallevelId ==6){
			locationId = globallocId;
			locationType =locType;
			filterTypeVal = "mandal";	
		} */
		var locationIdArr = [];
		 if (locationId > 0) {
			 locationIdArr.push(locationId);
		 }
	var json = {
			"locationType":locationType,
			"filterType"  :filterTypeVal ,
			"locationIds"  :locationIdArr,
			"fromDate"	  :glStartDate,
		    "toDate"	  :glEndDate,
			"lightVendorIdList":lightVendorArr
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
	str+='<div class="col-sm-12 white-block poles_block"  style="border-bottom: 1px solid gray;">';
	
	//str+='<div class="col-sm-10" >';
	/* str+='<div class="col-sm-1 lightsBlock backgroundImgLED">';
		str+='<img src="Assests/icons/On_Off_light_icon.png" style="height: 39px; width: 40px;margin-left: 19px;">';
		str+='<p style="font-weight: 800; font-size: 10px;">ON/OFF LIGHTS</p>';
		 if (result[0].onLights ==0 && result[0].offLights == 0) {
			str+='<h4>0/0</h4>';
		 } else {
			 str+='<h4 attr_location_type="panchayat" attr_result_type="onOff" style="cursor:pointer;color:rgb(51, 122, 183)" class="surveyStartedLocationCountCls">'+result[0].onLights+'/'+result[0].offLights+'</h4>';
		 }
	str+='</div>'; */
		
		str+='<div class="col-sm-3 media m_top5">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/CCMS_Box_icon.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#8E51Db">TOTAL CCMS-BOX/ PANELS</h5>';
				str+='<h3>'+result[0].totalPanels+'</h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2 media m_top5">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Poles_icon.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#669FF5;">TOTAL POLES</h5>';
				str+='<h3>'+result[0].totalPoles+'</h3>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="col-sm-2 media m_top5">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Total_Led_lights_iocn.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#827C13">TOTAL LIGHTS</h5>';
				str+='<h3>'+result[0].totalLights+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-2 media m_top5">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Non_Operational_LED_Light_Ico.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#FF3333;">NON-OPERATIONAL</h5>';
				str+='<h3>'+result[0].notWorkingLights+'</h3>';
			str+='</div>';
		str+='</div>';
	
		str+='<div class="col-sm-2 media m_top5">';
			str+='<div class="media-left">';
				str+='<img src="Assests/icons/Operational_LED_Light_Icon.png" alt="poles_icon">';
			str+='</div>';
			str+='<div class="media-body">';
				str+='<h5 style="color:#339900;">OPERATIONAL</h5>';
				str+='<h3>'+result[0].workingLights+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-1 lightsBlock backgroundImgLED">';
			str+='<img src="Assests/icons/On_Off_light_icon.png" style="height: 39px; width: 40px;margin-left: 19px;">';
			str+='<p style="font-weight: 800; font-size: 10px;">ON/OFF LIGHTS</p>';
			 if (result[0].onLights ==0 && result[0].offLights == 0) {
				str+='<h4>0/0</h4>';
			 } else {
				 str+='<h4 attr_location_type="panchayat" attr_result_type="onOff" style="cursor:pointer;color:rgb(51, 122, 183)" class="surveyStartedLocationCountCls">'+result[0].onLights+'/'+result[0].offLights+'</h4>';
			 }
		str+='</div>';
	
		str+='<div class="col-sm-12">';
		str+='<div class="dropdown">';
			str+='<span class="pull-right dropdown-toggle tooltipWattCls" style="font-weight: 600; cursor: pointer; font-size: 18px;" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-container="body" title="Wattage Details">&#9432;</span>';
				str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar arrow_box_top_led" aria-labelledby="dropdownMenu" style="margin-top: 32px;">';
				str+='<div class="poles_block">';
				str+='<ul class="nav navbar-nav">';
				str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
						for(var i in result[0].wattageList)
						{
							if (result[0].wattageList[i].wattage !=0 && result[0].wattageList[i].lightCount!=0){
								if(result[0].wattageList[i].wattage == 24 || result[0].wattageList[i].wattage == 32 || result[0].wattageList[i].wattage == 75){
									 str+='<li class="ledWattageColor"><b>'+result[0].wattageList[i].wattage+'W = '+result[0].wattageList[i].lightCount+'</b></li>';
								}else{
									str+='<li class=""><b>'+result[0].wattageList[i].wattage+'W = '+result[0].wattageList[i].lightCount+'</b></li>';
								}
							
							}
						}
					str+='</ul>';
				str+='</div>';
				str+='</div>';
/* 		str+='<div class="col-sm-12 border_dashed padding_10">';
			str+='<ul class="nav navbar-nav">';
				for(var i in result[0].wattageList)
				{
					if (result[0].wattageList[i].wattage !=0 && result[0].wattageList[i].lightCount!=0){
						if(result[0].wattageList[i].wattage == 24 || result[0].wattageList[i].wattage == 32 || result[0].wattageList[i].wattage == 75){
							 str+='<li class="ledWattageColor"><b>'+result[0].wattageList[i].wattage+'W = '+result[0].wattageList[i].lightCount+'</b></li>';
						}else{
							str+='<li class=""><b>'+result[0].wattageList[i].wattage+'W = '+result[0].wattageList[i].lightCount+'</b></li>';
						}
					
					}
				}
			str+='</ul>';
		str+='</div>'; */
	str+='</div>';
	//str+='</div>';
	$("#overviewBlockId").html(str);
}
function projectData(divId,levelId)
{
	var collapse='';
	var dataArr = '';
	if(levelId == 2 || levelId == 3)
	{
		dataArr = ['district','constituency','mandal','panchayat'];
		
	}else if(levelId == 4)
	{
		dataArr = ['constituency','mandal','panchayat'];
		
	}else if(levelId == 5)
	{
		dataArr = ['mandal','panchayat'];
		
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
											collapse+='<ul class="nav navbar-nav list_inline tableMenu tableMenu'+dataArr[i]+'" role="tabDrains_menu" attr_blockId="3" style="padding:0px">';
												collapse+='<li class="active ledResultTypeCls"  attr_location_level='+dataArr[i]+'  attr_tab_type="district">Districts</li>';
												collapse+='<li class="ledResultTypeCls" attr_location_level='+dataArr[i]+' attr_tab_type="parliament">Parliament</li>';
											collapse+='</ul>';
										collapse+='</div>';
										if(dataArr[i] == "district"){
											collapse+='<div class="col-sm-3">';
												collapse+='<select class="form-control chosen-select lightsVendorCls"  attr_location_level="district"  id="districtLevelLightsVendorSelectBoxId">';
												collapse+='<option value="0">All</option>';
												collapse+='</select>';
											collapse+='</div>';
										}
									if(dataArr[i] == "constituency"){
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="consLvlLedDistrictSelectBoxId" attr_location_level="constituency" attr_filter_type="district" attr_sub_location_type="" attr_light_vendor_id="constituencyLevelLightsVendorSelectBoxId"  id="consLvlLedDistrictSelectBoxId">';
											collapse+='<option value="0">ALL DISTRICT</option>';
											collapse+='</select>';
										collapse+='</div>';
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lightsVendorCls" attr_filter_type="district" attr_location_level="constituency"  attr_district_filter_id="consLvlLedDistrictSelectBoxId"  attr_constituency_filter_id="",attr_mandal_filter_id=""  id="constituencyLevelLightsVendorSelectBoxId">';
											collapse+='<option value="0">All</option>';
											collapse+='</select>';
										collapse+='</div>';
										
									}
									if(dataArr[i] == "mandal"){
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="mandalLvlLedConstituencySelectBoxId" attr_child_div_id="mandalLvlLedConstituencySelectBoxId" attr_location_level="mandal" attr_light_vendor_id="mandalLvlLightsVendorSelectBoxId"  attr_filter_type="district" attr_sub_location_type="constituency" id="mandalLvlLedDistrictSelectBoxId">';
											collapse+='<option value="0">ALL DISTRICT</option>';
											collapse+='</select>';
										collapse+='</div>';
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="mandalLvlLedDistrictSelectBoxId" attr_location_level="mandal" attr_filter_type="constituency" attr_sub_location_type="" attr_light_vendor_id="mandalLvlLightsVendorSelectBoxId"  id="mandalLvlLedConstituencySelectBoxId">';
											collapse+='<option value="0">SELECT CONSTITUENCY</option>';
											collapse+='</select>';
										collapse+='</div>';
										
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lightsVendorCls" attr_filter_type="district" attr_location_level="mandal"  attr_district_filter_id="mandalLvlLedDistrictSelectBoxId"  attr_constituency_filter_id="mandalLvlLedConstituencySelectBoxId",attr_mandal_filter_id="" id="mandalLvlLightsVendorSelectBoxId">';
											collapse+='<option value="0">All</option>';
											collapse+='</select>';
										collapse+='</div>';
										
									}
									if(dataArr[i] == "panchayat"){
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="panchayatLvlLedDistrictSelectBoxId" attr_child_div_id="panchayatLvlLedConstituencySelectBoxId" attr_location_level="panchayat" attr_filter_type="district" attr_light_vendor_id="panchayatLvlLedLightsVendorSelectBoxId" attr_sub_location_type="constituency" id="panchayatLvlLedDistrictSelectBoxId">';
											collapse+='<option value="0">ALL DISTRICT</option>';
											collapse+='</select>';
										collapse+='</div>';
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="panchayatLvlLedDistrictSelectBoxId" attr_child_div_id="panchayatLvlLedMandalSelectBoxId" attr_location_level="panchayat" attr_light_vendor_id="panchayatLvlLedLightsVendorSelectBoxId"  attr_filter_type="constituency" attr_sub_location_type="mandal" id="panchayatLvlLedConstituencySelectBoxId">';
											collapse+='<option value="0">SELECT CONSTITUENCY</option>';
											collapse+='</select>';
										collapse+='</div>';
										collapse+='<div class="col-sm-3">';
											collapse+='<select class="form-control chosen-select lebSelectBoxCls" attr_parent_div_id="panchayatLvlLedConstituencySelectBoxId" attr_location_level="panchayat" attr_filter_type="mandal" attr_light_vendor_id="panchayatLvlLedLightsVendorSelectBoxId"  attr_sub_location_type="" id="panchayatLvlLedMandalSelectBoxId">';
											collapse+='<option value="0">SELECT MANDAL</option>';
											collapse+='</select>';
										collapse+='</div>';
										collapse+='<div class="col-sm-3">';
								collapse+='<select class="form-control chosen-select lightsVendorCls" attr_filter_type="district" attr_location_level="panchayat" attr_filter_type="mandal" attr_district_filter_id="panchayatLvlLedDistrictSelectBoxId"  attr_constituency_filter_id="panchayatLvlLedConstituencySelectBoxId" attr_mandal_filter_id="panchayatLvlLedMandalSelectBoxId" id="panchayatLvlLedLightsVendorSelectBoxId">';
											collapse+='<option value="0">All</option>';
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
	$(".chosen-select").chosen();
	var lightVendorArr = [1,2];
	for(var i in dataArr)
	{
		$("#"+dataArr[i]+"TableId").html(spinner);
		getAllLevelWiseDataOverView(dataArr[i],dataArr[i],"",dataArr[i],lightVendorArr);
	}	
   getLocationBasedOnSelection("district","",0,"","otherLocationLevel");
   getLocationBasedOnSelection("district","",0,"","panchayat");
   var venderSelBoxIds="#districtLevelLightsVendorSelectBoxId,#constituencyLevelLightsVendorSelectBoxId,#mandalLvlLightsVendorSelectBoxId,#panchayatLvlLedLightsVendorSelectBoxId"
   	getLightsVendors(venderSelBoxIds);
}
function tableView(result,divId,locType)
{
	var tableView = '';
	var viewTypeDist='';
	var viewTypeCons='';
	var viewTypeman='';
	var viewTypePan='';
		$('.tableMenudistrict li').each(function(i, obj){
				 if($(this).hasClass('active')){
					viewTypeDist = $(this).attr("attr_tab_type");
				 }
		});
		$('.tableMenuconstituency li').each(function(i, obj){
				 if($(this).hasClass('active')){
					viewTypeCons = $(this).attr("attr_tab_type");
				 }
		});
		$('.tableMenumandal li').each(function(i, obj){
				 if($(this).hasClass('active')){
					viewTypeman = $(this).attr("attr_tab_type");
				 }
		});
		$('.tableMenupanchayat li').each(function(i, obj){
				 if($(this).hasClass('active')){
					viewTypePan = $(this).attr("attr_tab_type");
				 }
		});
	tableView+='<div class="table-responsive m_top10">';	
	tableView+='<table class="table tableStyleLed" id="'+divId+'Table">';
		tableView+='<thead>';
			tableView+='<tr>';
			tableView+='<th style="display:none;">Lights Vendor</th>';
			tableView+='<th></th>';
				if(divId == 'district')
				{
					if(viewTypeDist == "district"){
						tableView+='<th class="text-center">DISTRICTS</th>';
					}else{
						tableView+='<th class="text-center">PARLIAMENTS</th>';
					}	
					
				}else if(divId == 'constituency')
				{
					if(viewTypeCons == "district"){
						tableView+='<th class="text-center">DISTRICT</th>';
						tableView+='<th class="text-center">CONSTITUENCY</th>';
					}else{
						tableView+='<th class="text-center">PARLIAMENTS</th>';
						tableView+='<th class="text-center">CONSTITUENCY</th>';
					}	
					
				}else if(divId == 'mandal')
				{
					if(viewTypeman == "district"){
						tableView+='<th class="text-center">DISTRICT</th>';
						tableView+='<th class="text-center">CONSTITUENCY</th>';
						tableView+='<th class="text-center">MANDAL</th>';
					}else{
						tableView+='<th class="text-center">PARLIAMENTS</th>';
						tableView+='<th class="text-center">CONSTITUENCY</th>';
						tableView+='<th class="text-center">MANDAL</th>';
					}						
					
				}else if(divId == 'panchayat')
				{
					if(viewTypePan == "district"){
						tableView+='<th class="text-center">DISTRICT</th>';
						tableView+='<th class="text-center">CONSTITUENCY</th>';
						tableView+='<th class="text-center">MANDAL</th>';
						tableView+='<th class="text-center">PANCHAYAT</th>';
					}else{
						tableView+='<th class="text-center">PARLIAMENTS</th>';
						tableView+='<th class="text-center">CONSTITUENCY</th>';
						tableView+='<th class="text-center">MANDAL</th>';
						tableView+='<th class="text-center">PANCHAYAT</th>';
					}						
					
				}
				if(divId != 'mandal' && divId!='panchayat'){
				  tableView+='<th class="text-center"><img src="Assests/icons/mandals_icon.png" class="imageWidthLed"><br/>TOTAL <br/> MANDALS</th>';	
				}
				if (divId!='panchayat') {
					tableView+='<th class="text-center"><img src="Assests/icons/Mandal_Survy_icon.png" class="imageWidthLed"><br/>MANDALS<br/> STARTED</th>';
				    tableView+='<th class="text-center"><img src="Assests/icons/GPs_icon.png" class="imageWidthLed"><br/>TOTAL<br/> GPs</th>';
				}
				tableView+='<th class="text-center"><img src="Assests/icons/GPs_survey_icon.png" class="imageWidthLed"><br/>GPs STARTED</th>';
				//tableView+='<th><img src="Assests/icons/Poles_icon.png" class="imageWidthLed"><br/>TOTAL POLES SURVEYED</th>';
				tableView+='<th class="text-center"><img src="Assests/icons/CCMS_Box_icon.png" class="imageWidthLed"><br/>TOTAL CCMS-BOX <br/> INSTALLED</th>';
				tableView+='<th class="text-center"><img src="Assests/icons/Total_Led_lights_iocn.png" class="imageWidthLed" style="height: 35px;"><br/>TOTAL LED <br/>LIGHTS</th>';
				tableView+='<th class="text-center" style="vertical-align: middle;"><img src="Assests/icons/Non_Operational_LED_Light_Ico.png" class="" style="width: 28px; height: 35px;"><br/>NON <br/>OPERATIONAL&nbsp;</th>';
				tableView+='<th class="text-center" style="vertical-align: middle;"><img src="Assests/icons/Operational_LED_Light_Icon.png" class="" style="width: 28px; height: 35px;"><br/>OPERATIONAL&nbsp;</th>';
				tableView+='<th class="text-center" style="vertical-align: middle;"><img src="Assests/icons/On_light_icon.png" class="imageWidthLed" style="width: 40px; height: 40px;"><br/>ON&nbsp;</th>';
				tableView+='<th class="text-center" style="vertical-align: middle;"><img src="Assests/icons/Off_Light_Icon.png" class="imageWidthLed" style="width: 33px; height: 33px;"><br/>OFF&nbsp;</th>';
				tableView+='<th class="text-center">WATTAGE<br/>&nbsp;</th>';
			tableView+='</tr>';
		tableView+='</thead>';
		tableView+='<tbody>';
			for(var i in result)
			{
			 if (result[i].subList != null && result[i].subList.length > 0) {
				  for(var j in result[i].subList) {
						 tableView+='<tr style="text-align:center;">';
						
						  if (result[i].subList[j].lightVendorName != null ) {
								tableView+='<td style="display:none;">'+result[i].subList[j].lightVendorName+'</td>';	 
						  } else {
								 tableView+='<td style="display:none;">-</td>';
						  }
							 
						  tableView+='<td>';
							if (result[i].subList[j].lightVendorId != null && result[i].subList[j].lightVendorId == 1) {//essl
								tableView+='<img src="Assests/icons/Essl.jpg" style="width:25px;height:25px;">';
							} else { //nredp
								tableView+='<img src="Assests/icons/Nredp.jpg" style="width:25px;height:25px;">';
							}
							tableView+='</td>';
						if(divId == 'district')
						{
							if(viewTypeDist == "district"){
							
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
							}else{
								tableView+='<td>'+result[i].addressVO.parliamentName+'</td>';
							}
							
						}else if(divId == 'constituency')
						{
							if(viewTypeCons == "district"){
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
							}else{
								tableView+='<td>'+result[i].addressVO.parliamentName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
							}
							
							
						}else if(divId == 'mandal')
						{
							if(viewTypeman == "district"){
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
								tableView+='<td>'+result[i].addressVO.tehsilName+'</td>';
							}else{
								tableView+='<td>'+result[i].addressVO.parliamentName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
								tableView+='<td>'+result[i].addressVO.tehsilName+'</td>';
							}
							
							
						}else if(divId == 'panchayat')
						{
							if(viewTypePan == "district"){
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
								tableView+='<td>'+result[i].addressVO.tehsilName+'</td>';
								tableView+='<td>'+result[i].addressVO.panchayatName+'</td>';
							}else{
								tableView+='<td>'+result[i].addressVO.parliamentName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
								tableView+='<td>'+result[i].addressVO.tehsilName+'</td>';
								tableView+='<td>'+result[i].addressVO.panchayatName+'</td>';
							}
							
							
						}
						if(divId == 'district' || divId=="constituency"){
						 tableView+='<td>'+result[i].totalMandals+'</td>';	
						}
						if (divId!='panchayat') {
							if(divId=="mandal"){
								if (result[i].subList[j].surveyStartedtotalMandals > 0) {
									tableView+='<td>Yes</td>';
								} else {
								   tableView+='<td>No</td>';	
								}
							}else{
								tableView+='<td>'+result[i].subList[j].surveyStartedtotalMandals+'</td>';
							}
							
							tableView+='<td>'+result[i].totalGps+'</td>';
						}
						if(divId=="panchayat"){
							if (result[i].subList[j].surveyStartedtotalGps > 0) {
								tableView+='<td>Yes</td>';
							} else {
							   tableView+='<td>No</td>';	
							}
						}else{
							tableView+='<td>'+result[i].subList[j].surveyStartedtotalGps+'</td>';
						}
						//tableView+='<td>'+result[i].subList[j].totalPoles+'</td>';
						tableView+='<td>'+result[i].subList[j].totalPanels+'</td>';
						tableView+='<td>'+result[i].subList[j].totalLedLIghtInstalledCount+'</td>';
						tableView+='<td>'+result[i].subList[j].notWorkingLights+'</td>';
						tableView+='<td>'+result[i].subList[j].workingLights+'</td>';
						tableView+='<td>'+result[i].subList[j].onLights+'</td>';
						tableView+='<td>'+result[i].subList[j].offLights+'</td>';
						tableView+='<td>';
						tableView+='<div class="dropup">';
							tableView+='<span class="pull-right dropdown-toggle tooltipWattCls" style="font-weight: 600; cursor: pointer; font-size: 18px;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-container="body" title="Wattage Details">&#9432;</span>';
								if(result[i].subList[j].wattageList.length >=4){
									tableView+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar" aria-labelledby="dropdownMenu2" style="padding:10px;width:500px;">';
								}else{
									tableView+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar" aria-labelledby="dropdownMenu2" style="padding:10px;">';
								}
								
								tableView+='<div class="poles_block">';
								tableView+='<ul class="nav navbar-nav" style="float:none;">';
									if(result[i].subList[j].wattageList !=null && result[i].subList[j].wattageList.length>0){
										for(var l in result[i].subList[j].wattageList){
											if (result[i].subList[j].wattageList[l].wattage !=0 && result[i].subList[j].wattageList[l].lightCount!=0){
												if(result[i].subList[j].wattageList[l].wattage == 24 || result[i].subList[j].wattageList[l].wattage == 32 || result[i].subList[j].wattageList[l].wattage == 75){
													tableView+='<li class="ledWattageColor"><b>'+result[i].subList[j].wattageList[l].wattage+'W = '+result[i].subList[j].wattageList[l].lightCount+'</b></li>';
												}else{
													tableView+='<li class=""><b>'+result[i].subList[j].wattageList[l].wattage+'W = '+result[i].subList[j].wattageList[l].lightCount+'</b></li>';
												}
											}
										}
									}else{
										tableView+='<li><b>0W = 0</b></li>';
									}
									tableView+='</ul>';
								tableView+='</div>';
								tableView+='</div>';
							tableView+='</div>';
						tableView+='</td>';
				  tableView+='</tr>';
				}
			 }
		 }
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';

	$("#"+divId+"TableId").html(tableView);
	$(".tooltipWattCls").tooltip();
	if(divId == 'district' && viewTypeDist=='district'){
		$("#"+divId+"Table").dataTable({
			"paging":   false,
			"info":     false,
			"searching": true,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
			"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			buttons: [
				{
					extend:    'csvHtml5',
					text:      '<i class="fa fa-file-text-o"></i>',
					titleAttr: 'CSV',
					title:	   divId+' Level Led DashBoard',
					filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					exportOptions: {
						columns: [1,2,3,4,5,6,7,8,9,10,11]
					}
				},
				{
					extend:    'pdfHtml5',
					text:      '<i class="fa fa-file-pdf-o"></i>',
					titleAttr: 'PDF',
					orientation: "landscape",
					pageSize:'A3',
					title:	   divId+' Level Led DashBoard',
					filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
					exportOptions: {
						columns: [1,2,3,4,5,6,7,8,9,10,11]
					},
					
					customize: function (doc) {
						doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
					}
				}
			]
		 });
	}else{
		$("#"+divId+"Table").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
		"dom": "<'row'<'col-sm-4'l><'col-sm-7'f><'col-sm-1'B>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-5'i><'col-sm-7'p>>",
		buttons: [
			{
				extend:    'csvHtml5',
				text:      '<i class="fa fa-file-text-o"></i>',
				titleAttr: 'CSV',
				title:	   divId+' Level Led DashBoard',
				filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				exportOptions: {
						columns: [0,1,2,3,4,5,6,7,8,9,10,11]
					}
			},
			{
				extend:    'pdfHtml5',
				text:      '<i class="fa fa-file-pdf-o"></i>',
				titleAttr: 'PDF',
				orientation: "landscape",
				pageSize:'A3',
				title:	   divId+' Level Led DashBoard',
				filename:  divId+''+moment().format("DD/MMMM/YYYY  HH:MM"),
				exportOptions: {
						columns: [0,1,2,3,4,5,6,7,8,9,10,11]
					},
					
				/*  customize: function (doc) {
					doc.content[1].table.widths = Array(doc.content[1].table.body[0].length + 1).join('*').split('');
				}  */
			}
		]
	 });
	}
		
}

/* Filter Block Start */
$(document).on("click",".ledResultTypeCls",function() {
	$(this).parent().find(".ledResultTypeCls").removeClass("active");
	$(this).addClass("active");
	var locationLevel = $(this).attr("attr_location_level");
	var resultType = $(this).attr("attr_tab_type");
	var filterType="";
	var filterValue=0;
	var lightVendorArr = [1,2];
	if (locationLevel != null && locationLevel=="district") {
		if (resultType != null && resultType=="district" || resultType=="parliament") {
		  getAllLevelWiseDataOverView(resultType,filterType,filterValue,locationLevel,lightVendorArr);
		  $(".districtLevelHeadingDivCls").html(resultType+" level overview");
		  $("#districtLevelLightsVendorSelectBoxId").val(0);
		  $("#districtLevelLightsVendorSelectBoxId").trigger("chosen:updated");
		  $("#districtLelvdlDistrictHeadingId").html(resultType.toUpperCase());
		  $("#districtLevelLightsVendorSelectBoxId").attr("attr_filter_type",resultType);
		  $("#districtLevelLightsVendorSelectBoxId").attr("attr_location_level",resultType);
		} 
	}else if(locationLevel=="constituency" || locationLevel=="mandal" || locationLevel=='panchayat') {
		var divId = '';
		var lightVendorSelectBoxIdDivId = '';
		 if(locationLevel=="constituency") {
			 divId = "consLvlLedDistrictSelectBoxId";
			 $("#constituencyLevelLightsVendorSelectBoxId").val(0);
			 $("#constituencyLevelLightsVendorSelectBoxId").trigger("chosen:updated");
			 lightVendorSelectBoxIdDivId = "constituencyLevelLightsVendorSelectBoxId";
		 }else if (locationLevel=="mandal") {
			 $("#mandalLvlLedConstituencySelectBoxId").html('');
			 $("#mandalLvlLedConstituencySelectBoxId").append('<option value="0">SELECT CONSTITUENCY</option>');
			 $("#mandalLvlLedConstituencySelectBoxId").trigger("chosen:updated");
			 $("#mandalLvlLightsVendorSelectBoxId").val(0);
			 $("#mandalLvlLightsVendorSelectBoxId").trigger("chosen:updated");
			 divId = "mandalLvlLedDistrictSelectBoxId";
			 lightVendorSelectBoxIdDivId = "mandalLvlLightsVendorSelectBoxId";
		 }else if (locationLevel=="panchayat") {
			 $("#panchayatLvlLedConstituencySelectBoxId").html('');
			 $("#panchayatLvlLedMandalSelectBoxId").html('');
			 $("#panchayatLvlLedConstituencySelectBoxId").append('<option value="0">SELECT CONSTITUENCY</option>');
			 $("#panchayatLvlLedMandalSelectBoxId").append('<option value="0">SELECT MANDAL</option>');
			 $("#panchayatLvlLedConstituencySelectBoxId,#panchayatLvlLedMandalSelectBoxId").trigger("chosen:updated");
			 $("#panchayatLvlLedLightsVendorSelectBoxId").val(0);
			 $("#panchayatLvlLedLightsVendorSelectBoxId").trigger("chosen:updated");
			 divId = "panchayatLvlLedDistrictSelectBoxId";
			 lightVendorSelectBoxIdDivId = "panchayatLvlLedLightsVendorSelectBoxId";
		 }
		$("#"+divId).attr("attr_filter_type",resultType);
		$("#"+lightVendorSelectBoxIdDivId).attr("attr_filter_type",resultType);
		getLocationBasedOnSelection(resultType,filterType,filterValue,divId,locationLevel);
		getAllLevelWiseDataOverView(locationLevel,filterType,filterValue,locationLevel,lightVendorArr);
	}
});

$(document).on("change",".lebSelectBoxCls",function(){
	var locationValue = $(this).val();
	var locationLevel=$(this).attr("attr_location_level");
	var parentDivId=$(this).attr("attr_parent_div_id");
	var childDivId=$(this).attr("attr_child_div_id");
	var filterType = $(this).attr("attr_filter_type");
	var subLevel = $(this).attr("attr_sub_location_type");
	var lightVendorSelectBoxId = $(this).attr("attr_light_vendor_id");
	var lightVendorId = $("#"+lightVendorSelectBoxId).val();
	if ((filterType=="constituency" || filterType=="mandal") && locationValue==0) {
		filterType = $("#"+parentDivId).attr("attr_filter_type");
		locationValue = $("#"+parentDivId).val();
	}
	 var lightVendorArr = [];
	 if (lightVendorId != null && lightVendorId != undefined) {
		 if (lightVendorId == 0) {
		 lightVendorArr.push(1);
		 lightVendorArr.push(2);
		 } else {
			 lightVendorArr.push(lightVendorId);
		 }
	 }
	
	if(subLevel!='') {
		getLocationBasedOnSelection(subLevel,filterType,locationValue,childDivId,locationLevel);	
	}
	getAllLevelWiseDataOverView(locationLevel,filterType,locationValue,locationLevel,lightVendorArr);
});
$(document).on("change",".lightsVendorCls",function(){
	
	 var lightVendorId = $(this).val();
	 var locationLevel=$(this).attr("attr_location_level");
	
	 var districtParliamentId = $(this).attr("attr_district_filter_id");
	 var constituencyId = $(this).attr("attr_constituency_filter_id");
	 var mandalId = $(this).attr("attr_mandal_filter_id");
	 var districtOrParliamentValue = $("#"+districtParliamentId).val();
	 var constituencyValue = $("#"+constituencyId).val();
	 var mandalValue = $("#"+mandalId).val();
	 
	 var lightVendorArr = [];
	if (lightVendorId == 0) {
		 lightVendorArr.push(1);
		 lightVendorArr.push(2);
	 } else {
		 lightVendorArr.push(lightVendorId);
	 }
	 var filterTypes = $(this).attr("attr_filter_type");
	 var filterType = "";
	 var locationValue = 0;
	 if (districtOrParliamentValue != undefined && districtOrParliamentValue > 0) {
		  filterType = filterTypes;
		  locationValue = districtOrParliamentValue;
	  }
	   if (constituencyValue != undefined && constituencyValue > 0) {
		  filterType = "constituency";
		  locationValue = constituencyValue;
	  }
	   if (mandalValue != undefined && mandalValue > 0) {
		  filterType = "mandal";
		  locationValue = mandalValue;
	  }
	 var divId = '';
	  if (locationLevel == "parliament") {
		  divId = "district";
	  } else {
		  divId = locationLevel;
	  }
	getAllLevelWiseDataOverView(locationLevel,filterType,locationValue,divId,lightVendorArr);
});
/* End */

function getLocationBasedOnSelection(locationType,filterType,filterValue,divId,sublocaType){
	    var locationIdArr = [];
		 if (filterValue > 0) {
			 locationIdArr.push(filterValue);
		 }
	var json = {
			"locationType"  : locationType,
			"filterType"    : filterType ,
			"locationIds"    : locationIdArr,
			"sublocaType"   : sublocaType,
			"fromDate"	  :glStartDate,
		    "toDate"	  :glEndDate,
			"lightVendorIdList":[1,2]
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
		   buildSelextBoxRlst(result,divId,locationType,sublocaType)
	   }
	});
	function buildSelextBoxRlst(result,divId,locationType,sublocaType) {
		$("#"+divId).html('');
		  var str='';
		  str+='<option value="0">'+'ALL '+locationType.toUpperCase()+'</option>';
		 for(var i in result) {
			  str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';
		 }
		  if (locationType=="district") {
			  if (sublocaType == "panchayat") {
				  $("#panchayatLvlLedDistrictSelectBoxId").html(' ');
				  $("#panchayatLvlLedDistrictSelectBoxId").html(str);
				  $("#panchayatLvlLedDistrictSelectBoxId").trigger("chosen:updated");
			  } else {
				  $("#consLvlLedDistrictSelectBoxId,#mandalLvlLedDistrictSelectBoxId").html(' ');
				  $("#consLvlLedDistrictSelectBoxId,#mandalLvlLedDistrictSelectBoxId").html(str);
				  $("#consLvlLedDistrictSelectBoxId,#mandalLvlLedDistrictSelectBoxId").trigger("chosen:updated");
			  }
		  } else {
			$("#"+divId).html(str);
			$("#"+divId).trigger("chosen:updated");
		  }
	}
}
$(document).on("click",".commonViewCls",function(){
	$(".commonViewCls").removeClass("active");
	 $(this).addClass("active");
});
$(document).on("click",".liveDataCls",function() {
	$("#statusHeadingId").html("Building Live Panels & Lights Information. Please Wait ...");
	callWebService();
});
$(document).on("click",".todayDataCls",function() {
	 glStartDate = moment().format('DD-MM-YYYY');
     glEndDate = moment().format('DD-MM-YYYY');
	 onLoadCalls();
});
function callWebService(){
	$("#modalMessageDivId").modal("show");
	$("#processingImage").html(spinner);
	var json = {
		
		}
	$.ajax({                
		type:'POST',    
		url: 'callWebService',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 $("#modalMessageDivId").modal("hide");
		 $("#processingImage").html('');
		 if(result.statusCode==0 && result.message=="SUCCESS"){
			  glStartDate = moment().format('DD-MM-YYYY');
			  glEndDate = moment().format('DD-MM-YYYY');
			  onLoadCalls();
		 }else{
			    console.log("Exception Occured While Saving data into database.");
			    alert("Exception Occured,Please try again.");
		 }
	});
}
function getCompanyWiseLightMonitoringDtls(){
	$("#esslAndNredcapDivId").html(spinner);
	if (globallevelId == 2 || globallevelId == 0 || globallevelId ==3){
		$("#esslAndNredcapDivId").show();
	}else{
		$("#esslAndNredcapDivId").hide();
		return;
	}
	
	var locationType="";
	var locationValue=0;
	if(globallevelId == 2 || globallevelId == 0){
		locationType = "";
		locationValue=0;
	}else if(globallevelId == 3){
		locationType = "district";
		locationValue=globallocId;
	}else if(globallevelId == 4){
		locationType = "constituency";
		locationValue=globallocId;
	}else if(globallevelId == 5){
		locationType = "mandal";
		locationValue=globallocId;
	}
	var array=[];
	array.push(locationValue);
	
	var json = {
		fromDate:glStartDate,
		toDate:glEndDate,
		locationType:locationType,
		locationValues:array,
		lightVendorIdList:[1,2]
	}
	$.ajax({                
		type:'POST',    
		url: 'getCompanyWiseLightMonitoringDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildCompanyWiseLightMonitoringDtls(result)
		}
		
	});		
}

function buildCompanyWiseLightMonitoringDtls(result){
	var str='';
	if(result !=null){
			str+='<div class="col-sm-6">';
			str+='<div class="white-block block_Led_styles blockHeights" style="height: 314px;">';
			if(result.eeslVO !=null && result.eeslVO != 'undefined' && result.eeslVO.surveyStartedtotalDistricts !=0){
				str+='<div class="row" style="padding: 10px;border-bottom:1px solid grey;">';
					str+='<h3 style="margin-top:-15px;">EESL</h3>';
					str+='<p>Energy Efficiency Services Limited</p>';
					str+='<div class="media">';
						  str+='<div class="media-left img_middle">';
							 str+='<img class="media-object" src="Assests/icons/Essl.jpg" alt="Essl">';
						 str+='</div>';
						  str+='<div class="media-body">';
							str+='<div class="col-sm-12">';
							 str+='<div class="col-sm-8 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/CCMS_Box_icon.png" alt="poles_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 style="color:#669FF5;">TOTAL CCMS-BOX/ PANELS</h5>';
										str+='<h3>'+result.eeslVO.totalPanels+'</h3>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-4 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Poles_icon.png" alt="poles_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 style="color:#669FF5;">TOTAL POLES</h5>';
										str+='<h3>'+result.eeslVO.totalPoles+'</h3>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-12 m_top5">';
								str+='<div class="col-sm-4 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Total_Led_lights_iocn.png" alt="poles_icon" style="width: 25px; height: 35px;">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h6 style="color:#827C13">TOTAL LIGHTS</h6>';
										str+='<h4>'+result.eeslVO.totalLights+'</h4>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-3 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Operational_LED_Light_Icon.png" alt="poles_icon" style="width: 25px; height: 35px;">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h6 style="color:#339900;">OPERATIONAL</h6>';
										str+='<h4>'+result.eeslVO.workingLights+'</h4>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-5 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Non_Operational_LED_Light_Ico.png" alt="poles_icon" style="width: 25px; height: 35px;">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h6 style="color:#FF3333;">NON-OPERATIONAL</h6>';
										str+='<h4>'+result.eeslVO.notWorkingLights+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						  str+='</div>';
					str+='</div>';
					str+='<div class="dropdown">';
					str+='<span class="pull-right dropdown-toggle tooltipWattCls" style="font-weight: 600; cursor: pointer; font-size: 18px;" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-container="body" title="Wattage Details">&#9432;</span>';
						str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar arrow_box_top_led" aria-labelledby="dropdownMenu3" style="margin-top: 32px;">';
							str+='<div class="poles_block">';
							str+='<ul class="nav navbar-nav">';
							 str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
							if(result.eeslVO.wattageList !=null && result.eeslVO.wattageList.length>0){
								for(var i in result.eeslVO.wattageList){
									if (result.eeslVO.wattageList[i].wattage !=0 && result.eeslVO.wattageList[i].lightCount!=0){
										if(result.eeslVO.wattageList[i].wattage == 24 || result.eeslVO.wattageList[i].wattage == 32 || result.eeslVO.wattageList[i].wattage == 75){
											str+='<li class="ledWattageColor"><b>'+result.eeslVO.wattageList[i].wattage+'W = '+result.eeslVO.wattageList[i].lightCount+'</b></li>';
										}else{
											str+='<li class=""><b>'+result.eeslVO.wattageList[i].wattage+'W = '+result.eeslVO.wattageList[i].lightCount+'</b></li>';
										}
									 
									}
								}
							}else{
								str+='<li><b>0W = 0</b></li>';
							}
							
							str+='</ul>';
						str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='</div>';
					/* str+='<div class="row poles_block" style="padding:10px;border-bottom:1px solid grey">';
						str+='<ul class="nav navbar-nav" style="float:none;">';
						if(result.eeslVO.wattageList !=null && result.eeslVO.wattageList.length>0){
							for(var i in result.eeslVO.wattageList){
								if (result.eeslVO.wattageList[i].wattage !=0 && result.eeslVO.wattageList[i].lightCount!=0){
									if(result.eeslVO.wattageList[i].wattage == 24 || result.eeslVO.wattageList[i].wattage == 32 || result.eeslVO.wattageList[i].wattage == 75){
										str+='<li class="ledWattageColor"><b>'+result.eeslVO.wattageList[i].wattage+'W = '+result.eeslVO.wattageList[i].lightCount+'</b></li>';
									}else{
										str+='<li class=""><b>'+result.eeslVO.wattageList[i].wattage+'W = '+result.eeslVO.wattageList[i].lightCount+'</b></li>';
									}
								 
								}
							}
						}else{
							str+='<li><b>0W = 0</b></li>';
						}
						
						str+='</ul>';
					str+='</div>'; */
					
					str+='<div class="row m_top10" style="padding:10px;">';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#827C13;">DISTRICTS</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="district" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="EESL" class="companyTypeCls">'+result.eeslVO.surveyStartedtotalDistricts+'</h3>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#02B0AC;">CONSTITUENCIES</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="constituency" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="EESL" class="companyTypeCls">'+result.eeslVO.surveyStartedtotalConstituencys+'</h3>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#00BFE8;">MANDALS</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="mandal" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="EESL" class="companyTypeCls">'+result.eeslVO.surveyStartedtotalMandals+'</h3>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#F45CB5;">GRAM PANCHAYAT</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="panchayat" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="EESL" class="companyTypeCls">'+result.eeslVO.surveyStartedtotalGps+'</h3>';
						str+='</div>';
					str+='</div>';
			}else{
				  var str1 = getRequiredTemplate('EESL');
				  str = str+''+str1;
				/* str+='<div class="row" style="padding: 10px;">';
					str+='<h3>EESL</h3>';
					str+='<p>Energy Efficiency Services Limited</p>';
					str+='<div style="text-align:center"><img src="Assests/icons/NODATA.png" alt="NODATA"></div>';
				str+='</div>'; */
			}
				
			str+='</div>';
			
		str+='</div>';
		str+='<div class="col-sm-6">';
			str+='<div class="white-block block_Led_styles blockHeights" style="height: 314px;">';
			if(result.nredcapVO !=null && result.nredcapVO != 'undefined' && result.nredcapVO.surveyStartedtotalDistricts !=0){
				str+='<div class="row" style="padding: 10px;border-bottom:1px solid grey;">';
					str+='<h3 style="margin-top:-15px;">NREDCAP</h3>';
					str+='<p>New & Renewable Energy Development Corporation of Andhra Pradesh Ltd.</p>';
					str+='<div class="media">';
						  str+='<div class="media-left img_middle">';
							 str+='<img class="media-object" src="Assests/icons/Nredp.jpg" alt="Nredp">';
						 str+='</div>';
						  str+='<div class="media-body">';
							str+='<div class="col-sm-12">';
							 str+='<div class="col-sm-8 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/CCMS_Box_icon.png" alt="poles_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 style="color:#669FF5;">TOTAL CCMS-BOX/ PANELS</h5>';
										str+='<h3>'+result.nredcapVO.totalPanels+'</h3>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-4 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Poles_icon.png" alt="poles_icon">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 style="color:#669FF5;">TOTAL POLES</h5>';
										str+='<h3>'+result.nredcapVO.totalPoles+'</h3>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-12 m_top5">';
								str+='<div class="col-sm-4 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Total_Led_lights_iocn.png" alt="poles_icon" style="width: 25px; height: 35px;">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h6 style="color:#827C13">TOTAL LIGHTS</h6>';
										str+='<h4>'+result.nredcapVO.totalLights+'</h4>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-3 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Operational_LED_Light_Icon.png" alt="poles_icon" style="width: 25px; height: 35px;">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h6 style="color:#339900;">OPERATIONAL</h6>';
										str+='<h4>'+result.nredcapVO.workingLights+'</h4>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-5 media m_top5">';
									str+='<div class="media-left">';
										str+='<img src="Assests/icons/Non_Operational_LED_Light_Ico.png" alt="poles_icon" style="width: 25px; height: 35px;">';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h6 style="color:#FF3333;">NON-OPERATIONAL</h6>';
										str+='<h4>'+result.nredcapVO.notWorkingLights+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
						  str+='</div>';
					str+='</div>';
					str+='<div class="dropdown">';
					str+='<span class="pull-right dropdown-toggle tooltipWattCls" style="font-weight: 600; cursor: pointer; font-size: 18px;" id="dropdownMenu4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" data-container="body" title="Wattage Details">&#9432;</span>';
						str+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom verticalScrollBar arrow_box_top_led" aria-labelledby="dropdownMenu4" style="margin-top: 32px;">';
						str+='<div class="poles_block">';
							str+='<ul class="nav navbar-nav">';
							str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
							if(result.nredcapVO.wattageList !=null && result.nredcapVO.wattageList.length>0){
								for(var i in result.nredcapVO.wattageList){
									if (result.nredcapVO.wattageList[i].wattage !=0 && result.nredcapVO.wattageList[i].lightCount!=0){
										if(result.nredcapVO.wattageList[i].wattage == 24 || result.nredcapVO.wattageList[i].wattage == 32 || result.nredcapVO.wattageList[i].wattage == 75){
											str+='<li class="ledWattageColor"><b>'+result.nredcapVO.wattageList[i].wattage+'W = '+result.nredcapVO.wattageList[i].lightCount+'</b></li>';
										}else{
											str+='<li class=""><b>'+result.nredcapVO.wattageList[i].wattage+'W = '+result.nredcapVO.wattageList[i].lightCount+'</b></li>';
										}
									 	
									}
									
								}
							}else{
								str+='<li><b>0W = 0</b></li>';
							}
						str+='</ul>';
						str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='</div>';
					/* str+='<div class="row poles_block" style="padding:10px;border-bottom:1px solid grey">';
						str+='<ul class="nav navbar-nav" style="float:none;">';
							if(result.nredcapVO.wattageList !=null && result.nredcapVO.wattageList.length>0){
								for(var i in result.nredcapVO.wattageList){
									if (result.nredcapVO.wattageList[i].wattage !=0 && result.nredcapVO.wattageList[i].lightCount!=0){
										if(result.nredcapVO.wattageList[i].wattage == 24 || result.nredcapVO.wattageList[i].wattage == 32 || result.nredcapVO.wattageList[i].wattage == 75){
											str+='<li class="ledWattageColor"><b>'+result.nredcapVO.wattageList[i].wattage+'W = '+result.nredcapVO.wattageList[i].lightCount+'</b></li>';
										}else{
											str+='<li class=""><b>'+result.nredcapVO.wattageList[i].wattage+'W = '+result.nredcapVO.wattageList[i].lightCount+'</b></li>';
										}
									 	
									}
									
								}
							}else{
								str+='<li><b>0W = 0</b></li>';
							}
						str+='</ul>';
					str+='</div>'; */
					str+='<div class="row m_top10" style="padding:10px;">';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#827C13;">DISTRICTS</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="district" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="NREDCAP" class="companyTypeCls">'+result.nredcapVO.surveyStartedtotalDistricts+'</h3>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#02B0AC;">CONSTITUENCIES</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="constituency" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="NREDCAP" class="companyTypeCls">'+result.nredcapVO.surveyStartedtotalConstituencys+'</h3>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#00BFE8;">MANDALS</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="mandal" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="NREDCAP" class="companyTypeCls">'+result.nredcapVO.surveyStartedtotalMandals+'</h3>';
						str+='</div>';
						str+='<div class="col-sm-3">';
							str+='<h6><b>NO OF <span style="color:#F45CB5;">GRAM PANCHAYAT</span><br/>SURVEY STARTED</b></h6>';
							str+='<h3 attr_location_type="panchayat" style="cursor:pointer;color:rgb(51, 122, 183)" attr_result_type="surveryStartedLocation" attr_company_type="NREDCAP" class="companyTypeCls">'+result.nredcapVO.surveyStartedtotalGps+'</h3>';
						str+='</div>';
					str+='</div>';
			}else{
				var str1 = getRequiredTemplate('NREDCAP');
				str = str+''+str1;
				/* str+='<div class="row" style="padding: 10px;">';
					str+='<h3>NREDCAP</h3>';
					str+='<p>New & Renewable Energy Development Corporation of Andhra Pradesh Ltd.</p>';
					str+='<div style="text-align:center"><img src="Assests/icons/NODATA.png" alt="NODATA"></div>';
				str+='</div>'; */
			}
			str+='</div>';
			
		str+='</div>';
	}
	
	$("#esslAndNredcapDivId").html(str);
	var maxHeight = 0;
	 $(".blockHeights").each(function(){
	   if ($(this).height() > maxHeight) { maxHeight = $(this).height(); }
	});
	$(".blockHeights").height(maxHeight);
}
function getRequiredTemplate(type){
		var str='';
			str+='<div class="row" style="padding: 14px;border-bottom:1px solid grey">'; //border-bottom: 1px dashed gray;padding: 10px
			if(type=="NREDCAP") {
				str+='<h3>NREDCAP</h3>';
				str+='<p>New & Renewable Energy Development Corporation of Andhra Pradesh Ltd.</p>';
				str+='<div class="media">';
					  str+='<div class="media-left img_middle">';
						 str+='<img class="media-object" src="Assests/icons/Nredp.jpg" alt="Nredp">';	
			} else if(type=="EESL") {
				str+='<h3>EESL</h3>';
				str+='<p>Energy Efficiency Services Limited</p>';
				str+='<div class="media">';
					  str+='<div class="media-left img_middle">';
						 str+='<img class="media-object" src="Assests/icons/Essl.jpg" alt="Essl">';
			}
			 str+='</div>';
			  str+='<div class="media-body">';
				str+='<div class="col-sm-12">';
				 str+='<div class="col-sm-8 media m_top5">';
						str+='<div class="media-left">';
							str+='<img src="Assests/icons/CCMS_Box_icon.png" alt="poles_icon">';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h5 style="color:#669FF5;">TOTAL CCMS-BOX/ PANELS</h5>';
							str+='<h3>0</h3>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-4 media m_top5">';
						str+='<div class="media-left">';
							str+='<img src="Assests/icons/Poles_icon.png" alt="poles_icon">';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h5 style="color:#669FF5;">TOTAL POLES</h5>';
							str+='<h3>0</h3>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-12 m_top5">';
					str+='<div class="col-sm-4 media m_top5">';
						str+='<div class="media-left">';
							str+='<img src="Assests/icons/Total_Led_lights_iocn.png" alt="poles_icon" style="width: 25px; height: 35px;">';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h6 style="color:#827C13">TOTAL LIGHTS</h6>';
							str+='<h4>0</h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-3 media m_top5">';
						str+='<div class="media-left">';
							str+='<img src="Assests/icons/Operational_LED_Light_Icon.png" alt="poles_icon" style="width: 25px; height: 35px;">';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h6 style="color:#339900;">OPERATIONAL</h6>';
							str+='<h4>0</h4>';
						str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-5 media m_top5">';
						str+='<div class="media-left">';
							str+='<img src="Assests/icons/Non_Operational_LED_Light_Ico.png" alt="poles_icon" style="width: 25px; height: 35px;">';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h6 style="color:#FF3333;">NON-OPERATIONAL</h6>';
							str+='<h4>0</h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			  str+='</div>';
		str+='</div>';
		str+='</div>';
		/*str+='<div class="row poles_block" style="padding:10px;border-bottom:1px solid grey">';
			str+='<ul class="nav navbar-nav" style="float:none;">';
					str+='<li><b>0W = 0</b></li>';
			str+='</ul>';
		str+='</div>'; */
		str+='<div class="row m_top10" style="padding:10px;">';
			str+='<div class="col-sm-3">';
				str+='<h6><b>NO OF <span style="color:#827C13;">DISTRICTS</span><br/>SURVEY STARTED</b></h6>';
				str+='<h3>0</h3>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<h6><b>NO OF <span style="color:#02B0AC;">CONSTITUENCIES</span><br/>SURVEY STARTED</b></h6>';
				str+='<h3>0</h3>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<h6><b>NO OF <span style="color:#00BFE8;">MANDALS</span><br/>SURVEY STARTED</b></h6>';
				str+='<h3>0</h3>';
			str+='</div>';
			str+='<div class="col-sm-3">';
				str+='<h6><b>NO OF <span style="color:#F45CB5;">GRAM PANCHAYAT</span><br/>SURVEY STARTED</b></h6>';
				str+='<h3>0</h3>';
			str+='</div>';
		str+='</div>';
 return str;
}

function checkIsDataExist(selectDate){
	$("#processingImage").html('');
	var json = {
		fromDate:selectDate,
		toDate:selectDate
	}
	$.ajax({                
		type:'POST',    
		url: 'checkIsDataExist',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result.status=="YES") {
			glStartDate = selectDate;
	        glEndDate = selectDate;
			onLoadCalls();
		} else {
			$("#statusHeadingId").html("DATA DON'T EXIST IN SELECTED DATE");
	        $("#modalMessageDivId").modal("show");
			setTimeout(function(){ $("#modalMessageDivId").modal("hide"); }, 5000);
		}
	});
}

/* Click Functionality started */
$(document).on("click",".surveyStartedLocationCountCls",function (){
	var locationType = $(this).attr("attr_location_type");
	var resultType = $(this).attr("attr_result_type");
	if (resultType=="onOff") {
		$("#surveryStartedLocHeadingId").html('PANCHAYAT WISE ON/OFF LIGHTS DETAILS')
	} else {
		$("#surveryStartedLocHeadingId").html('Survey Started Location Details');
	}
	    var locationId='';
		var filterType='';
		if(globallevelId ==2 || globallevelId ==0){
			locationId = 0;
			filterType = '';	
		}else if(globallevelId ==3){
			locationId = globallocId;
			filterType = "district";	
		}else if(globallevelId ==4){
			locationId = globallocId;
			filterType = "constituency";	
		}else if(globallevelId ==5){
			locationId = globallocId;
			filterType = "mandal";	
		}
		var locationIdArr = [];
		 if (locationId > 0) {
			 locationIdArr.push(locationId);
		 }
	 var lightVendorIdList = [1,2];
	getSurveryStartedLocation(locationType,resultType,filterType,locationIdArr,lightVendorIdList);
});
$(document).on("click",".companyTypeCls",function (){
	var locationType = $(this).attr("attr_location_type");
	var resultType = $(this).attr("attr_result_type");
	var companyType = $(this).attr("attr_company_type");
	$("#surveryStartedLocHeadingId").html(''+companyType+' Survey Started Location Details');
	// var nredcapArr = [11,12,13,14,15,16,21];
    // var eeslArr = [17,18,19,20,22,23];
		 var locationIdArr = [];
		if(globallevelId ==3){
			locationIdArr.push(globallocId);
		}
    
     var lightVendorIdList = [];
	 var filterType='district';
	   if (companyType=="EESL") {
			lightVendorIdList.push(1);
		} else if (companyType == "NREDCAP") {
			lightVendorIdList.push(2);
		}
		
	getSurveryStartedLocation(locationType,resultType,filterType,locationIdArr,lightVendorIdList);
});


function getSurveryStartedLocation(locType,resultType,filterType,locationIdArr,lightVendorIdList){
	 $("#surveryStartedLocationDtlsModelDivId").modal("show");
	 $("#surveyStartedLocationDtlsDivId").html(spinner);
	var json = {
			"locationType":locType,
			"filterType"  :filterType ,
			"locationIds"  :locationIdArr,
			"fromDate"	  :glStartDate,
		    "toDate"	  :glEndDate,
			"lightVendorIdList":lightVendorIdList
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
		 $("#surveyStartedLocationDtlsDivId").html(' ');
	     if (result != null && result.length > 0 ){
			 buildSurveryStartedLocationDtls(result,locType,resultType);
		 }else {
			 $("#surveyStartedLocationDtlsDivId").html('NO DATA AVAILABLE.');
		 }
	});
}

function buildSurveryStartedLocationDtls(result,divId,resultType){
	var tableView = '';
	tableView+='<div class="table-responsive m_top10">';	
	tableView+='<table class="table tableStyleLed" id="surveyStartedLocationDtlsDataTblId">';
		tableView+='<thead>';
			tableView+='<tr>';
				tableView+='<th style="display:none;">Lights Vendor</th>';	
				tableView+='<th></th>';	
				if(divId == 'district'){
						tableView+='<th>DISTRICTS</th>';
				}else if(divId == 'constituency'){
						tableView+='<th>DISTRICT</th>';
						tableView+='<th>CONSTITUENCY</th>';
				}else if(divId == 'mandal'){
						tableView+='<th>DISTRICT</th>';
						tableView+='<th>CONSTITUENCY</th>';
						tableView+='<th>MANDAL</th>';
				}else if(divId == 'panchayat'){
						tableView+='<th>DISTRICT</th>';
						tableView+='<th>CONSTITUENCY</th>';
						tableView+='<th>MANDAL</th>';
						tableView+='<th>PANCHAYAT</th>';
				}
				if (resultType !="onOff") { 
					if(divId == 'district' || divId=='constituency'){
					  tableView+='<th><img src="Assests/icons/mandals_icon.png" class="imageWidthLed"><br/>TOTAL MANDALS</th>';	
					}
					if(divId !='panchayat') {
						tableView+='<th><img src="Assests/icons/Mandal_Survy_icon.png" class="imageWidthLed"><br/>MANDALS STARTED</th>';
					    tableView+='<th><img src="Assests/icons/GPs_icon.png" class="imageWidthLed"><br/>TOTAL GPs</th>';
					}
					tableView+='<th><img src="Assests/icons/GPs_survey_icon.png" class="imageWidthLed"><br/>GPs STARTED</th>';
					tableView+='<th><img src="Assests/icons/CCMS_Box_icon.png" class="imageWidthLed"><br/>TOTAL CCMS-BOX INSTALLED</th>';
					tableView+='<th><img src="Assests/icons/Total_Led_lights_iocn.png" class="imageWidthLed"><br/>TOTAL LED LIGHTS</th>';
				} else {
				   tableView+='<th><img src="Assests/icons/Total_Led_lights_iocn.png" class="imageWidthLed"><br/>TOTAL LED LIGHTS</th>';
				   tableView+='<th><img src="Assests/icons/Operational_LED_Light_Icon.png" class="" style="width:35px;height:35px;">OPERATIONAL<br/>&nbsp;</th>';
				   tableView+='<th><img src="Assests/icons/On_light_icon.png" class="imageWidthLed">ON<br/>&nbsp;</th>';
				   tableView+='<th><img src="Assests/icons/Off_Light_Icon.png" class="imageWidthLed">OFF<br/>&nbsp;</th>';
				}
				
			tableView+='</tr>';
		tableView+='</thead>';
		tableView+='<tbody>';
			for(var i in result){
				 if (result[i].subList != null && result[i].subList.length > 0) {
					 for(var j in result[i].subList) {
						if (result[i].subList[j].surveyStartedtotalMandals > 0 ){
						tableView+='<tr>';
							  if (result[i].subList[j].lightVendorName != null ) {
									tableView+='<td style="display:none;">'+result[i].subList[j].lightVendorName+'</td>';	 
							  } else {
									 tableView+='<td style="display:none;">-</td>';
							  }
							tableView+='<td>';
								if (result[i].subList[j].lightVendorId != null && result[i].subList[j].lightVendorId == 1) {//essl
									tableView+='<img src="Assests/icons/Essl.jpg" style="width:25px;height:25px;">';
								} else { //nredp
									tableView+='<img src="Assests/icons/Nredp.jpg" style="width:25px;height:25px;">';
								}
							tableView+='</td>';
						if(divId == 'district'){
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
						}else if(divId == 'constituency'){
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
						}else if(divId == 'mandal'){
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
								tableView+='<td>'+result[i].addressVO.tehsilName+'</td>';
						}else if(divId == 'panchayat'){
								tableView+='<td>'+result[i].addressVO.districtName+'</td>';
								tableView+='<td>'+result[i].addressVO.assemblyName+'</td>';
								tableView+='<td>'+result[i].addressVO.tehsilName+'</td>';
								tableView+='<td>'+result[i].addressVO.panchayatName+'</td>';
						}
						
						if (resultType !="onOff") {
							if(divId == 'district' || divId=="constituency"){
							 tableView+='<td>'+result[i].totalMandals+'</td>';	
							}
							if (divId !='panchayat'){
								if(divId=="mandal"){
									if (result[i].subList[j].surveyStartedtotalMandals > 0) {
										tableView+='<td>Yes</td>';
									} else {
									   tableView+='<td>No</td>';	
									}
								}else{
									tableView+='<td>'+result[i].subList[j].surveyStartedtotalMandals+'</td>';
								}
								tableView+='<td>'+result[i].totalGps+'</td>';
							}
							if (divId =='panchayat'){
									if (result[i].subList[j].surveyStartedtotalGps > 0) {
										tableView+='<td>Yes</td>';
									} else {
									   tableView+='<td>No</td>';	
									}	
							}else {
								tableView+='<td>'+result[i].subList[j].surveyStartedtotalGps+'</td>';	
							}
							tableView+='<td>'+result[i].subList[j].totalPanels+'</td>';
							tableView+='<td>'+result[i].subList[j].totalLedLIghtInstalledCount+'</td>';
						} else {
							tableView+='<td>'+result[i].subList[j].totalLedLIghtInstalledCount+'</td>';
							tableView+='<td>'+result[i].subList[j].workingLights+'</td>';
							tableView+='<td>'+result[i].subList[j].onLights+'</td>';
							tableView+='<td>'+result[i].subList[j].offLights+'</td>';
					
						}
				 tableView+='</tr>';
				} 
			 }
			}
				
			}
		tableView+='</tbody>';
	tableView+='</table>';
	tableView+='</div>';
	$("#surveyStartedLocationDtlsDivId").html(tableView);
	if(divId == 'district'){
		$("#surveyStartedLocationDtlsDataTblId").dataTable({
			"paging":   false,
			"info":     false,
			"searching": true,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
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
	}else{
		$("#surveyStartedLocationDtlsDataTblId").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]],
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
}
function getLightsVendors(selIds){ 
var json={}
	$.ajax({                 
		type:'GET',    
		url: 'getLightsVendors',
		dataType: 'json',
		
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
			var str2='';
			str2+='<option value="0">All</option>';
		for(var i in result){
			str2+='<option value="'+result[i].lightsVendorId+'">'+result[i].vendorName+'</option>';
		}
		$(selIds).html(str2);
		 $(selIds).trigger("chosen:updated");
	});

}
/* End */
