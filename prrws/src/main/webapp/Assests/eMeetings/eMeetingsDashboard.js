var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var currentFromDate=moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY");
var currentToDate=moment().endOf('year').add(10, 'years').format("DD-MM-YYYY");
$("#dateRangePicker").daterangepicker({
		opens: 'left',
		startDate: currentFromDate,
		endDate: currentToDate,
		locale: {
		  format: 'DD-MM-YYYY'
		},
		ranges: {
		   'All':[moment().subtract(20, 'years').startOf('year').format("DD-MM-YYYY"), moment().add(10, 'years').endOf('year').format("DD-MM-YYYY")],
		   'Today' : [moment(), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
	var dates= $("#dateRangePicker").val();
	var pickerDates = currentFromDate+' - '+currentToDate
	if(dates == pickerDates)
	{
		$("#dateRangePicker").val('All');
	}
	
	$('#dateRangePicker').on('apply.daterangepicker', function(ev, picker) {
		currentFromDate = picker.startDate.format('DD-MM-YYYY');
		currentToDate = picker.endDate.format('DD-MM-YYYY');
		if(picker.chosenLabel == 'All')
		{
			$("#dateRangePicker").val('All');
		}
	
		onloadEmeetingCalls();
	});
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});

onloadEmeetingCalls();
function onloadEmeetingCalls(){
	getEMeetingsOverViewDetails();
	levelWiseEmeetingsData('Emeeting');
	//getAllSubLocationsTax(2,1,'',"district");
}	

function getEMeetingsOverViewDetails(){
	$("#emeetingsOverviewDivId").html(spinner);
	$("#pantVsMetingOvervwDivId").html(spinner);
	
	var json = {
		locationId : "1",
		locationType : "state",
		fromDate : currentFromDate,
		toDate : currentToDate
	};
	$.ajax({                
		type:'POST',    
		url: 'getEMeetingsOverViewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			buildEMeetingsOverViewDetails(result);
		}else{
			$("#emeetingsOverviewDivId").html('No Data Available.');
		}
	});
}

function buildEMeetingsOverViewDetails(result){
	var str = '';
	var str1 = '';
	str+='<div class="row">';
		str+='<div class="col-sm-8">';
		str+='<div class="custom-div">';
			str+='<div class="row">';
				str+='<div class="col-sm-8">';
					str+='<div class="custom-div" style="padding: 30px;">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img src="Assests/images/total-panchayats.png" style="width:45px; height:45px;"/>';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h4 class="m_top10 font_weight">Total<br>e-Panchayats</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3 text-right">';
								str+='<h3 class="font_weight m_top10">'+result.totalPanchayats+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-4">';
					str+='<div class="custom-div" style="padding-bottom: 2px;">';
						str+='<div class="row">';
							str+='<div class="col-sm-7">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img src="Assests/images/icon-meetngs.png" style="width:45px; height:45px;"/>';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="m_top10 font_weight">Total Meetings</h5>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-5">';
								str+='<h4 class="font_weight m_top10">'+result.totalMeetings+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row">';
						str+='<div class="col-sm-12 m_top10">';
							str+='<div class="custom-div-bg" style="padding-top: 8px;padding-bottom: 10px;">';
								str+='<h4 class="font_weight" style="font-size: 15px;"><i class="fa fa-check-circle-o green_color fa-2x" style="position: relative;left: 7px;"></i> <span style="margin-left: 20px;position: relative;top: -3px;">Conducted</span> <span style="margin-left: 40px;position: relative;top: -1px;">'+result.conductedMeetings+'</span></h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4">';
			str+='<div class="custom-div">';
				str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<img src="Assests/images/icon-agenda.png" style="width:45px; height:45px;"/>';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h5 class="m_top10 font_weight">Total Agenda Points</h5>';
							str+='<h4 class="m_top5 font_weight">'+result.totalAgendaPts+'</h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
				str+='<div class="row">';
					str+='<div class="col-sm-12">';
						str+='<div class="custom-div-bg m_top10" style="padding: 5px;">';
							str+='<div class="row">';
								str+='<div class="col-sm-6">';
									str+='<div class="border_left_width_green">';
										str+='<h6 class="font_weight good_color margin_left_8">Approved</h6>';
										str+='<h4 class="m_top10 margin_left_8 font_weight">'+result.approvedAgendaPts+'</h4>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-6">';
									str+='<div class="border_left_width_bad">';
										str+='<h6 class="font_weight bad_color margin_left_8">Not-Approved </h6>';
										str+='<h4 class="m_top10 margin_left_8 font_weight">'+result.notApprovedAgendaPts+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';   
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#emeetingsOverviewDivId").html(str);
	
	str1+='<div class="row">';
		str1+='<div class="col-sm-6">';
			str1+='<div class = "panel panel-default border_radius_5 m_bottom_0">';
				str1+='<div class = "panel-heading">';
					str1+='<div class="media">';
						str1+='<div class="media-left">';
							str1+='<i class="fa fa-check-circle green_color fa-2x"></i>';
						str1+='</div>';
						str1+='<div class="media-body text-center">';
							str1+='<h5 class="font_weight text_pos">Panchayaties - '+result.conductedPanchayats+'</h5>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
				str1+='<div class="panel-body">';
					str1+='<div class="row">';
						str1+='<div class="text-center">';
							str1+='<h5 class="font_weight">Conducted eMeetings - '+result.conductedMeetings+'</h5>';
						str1+='</div>';
					str1+='</div>';
					str1+='<hr style="margin-top:10px;margin-bottom:10px;">';
					str1+='<div class="row">';
						str1+='<div class="col-sm-4 m_top10">';
							str1+='<div class="border_left_blue">';
								str1+='<h6 class="font_weight margin_left_8 text_color_e">General</h6>';
								str1+='<h4 class="font_weight m_top10 margin_left_8">'+result.generalMeetings+'</h4>';
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="col-sm-4 m_top10">';
							str1+='<div class="border_left_width_green ">';
								str1+='<h6 class="font_weight margin_left_8 text_color_e">Requested</h6>';
								str1+='<h4 class="font_weight m_top10 margin_left_8">'+result.reqMeetings+'</h4>';
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="col-sm-4 m_top10">';
							str1+='<div class="border_left_width_bad ">';
								str1+='<h6 class="font_weight margin_left_8 text_color_e">Emergency</h6>';
								str1+='<h4 class="font_weight m_top10 margin_left_8">'+result.emergencyMeetings+'</h4>';
							str1+='</div>';
						str1+='</div>';
					str1+='</div>';
			   str1+='</div>';
			str1+='</div>';
		str1+='</div>';
		str1+='<div class="col-sm-6">';
			str1+='<div class = "panel panel-default border_radius_5 m_bottom_0">';
				str1+='<div class = "panel-heading">';
					str1+='<div class="media">';
						str1+='<div class="media-left">';
							str1+='<i class="fa fa-times-circle-o red_color fa-2x"></i>';
						str1+='</div>';
						str1+='<div class="media-body text-center">';
							str1+='<h5 class="font_weight text_pos">Panchayaties - '+result.notConductedPanchayts+'</h5>';
						str1+='</div>';
					str1+='</div>';
				str1+='</div>';
				str1+='<div class="panel-body">';
					str1+='<div class="row">';
						str1+='<div class="text-center">';
							str1+='<h5 class="font_weight">Not Conducted eMeetings - '+result.notConductedMeetings+'</h5>';
						str1+='</div>';
					str1+='</div>';
					str1+='<hr style="margin-top:10px;margin-bottom:10px;">';
					str1+='<div class="row">';
						str1+='<div class="col-sm-4 m_top10">';
							str1+='<div class="border_left_yellow">';
								str1+='<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;60&nbsp;days</h6>';
								str1+='<h4 class="font_weight m_top10 margin_left_8">'+result.beyond60Days+'</h4>';
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="col-sm-4 m_top10">';
							str1+='<div class="border_left_pink ">';
								str1+='<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;90&nbsp;days</h6>';
								str1+='<h4 class="font_weight m_top10 margin_left_8">'+result.beyond90Days+'</h4>';
							str1+='</div>';
						str1+='</div>';
						str1+='<div class="col-sm-4 m_top10">';
							str1+='<div class="border_left_teak ">';
								str1+='<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;120&nbsp;days</h6>';
								str1+='<h4 class="font_weight m_top10 margin_left_8">'+result.beyond120Days+'</h4>';
							str1+='</div>';
						str1+='</div>';
					str1+='</div>';
			   str1+='</div>';
			str1+='</div>';
		str1+='</div>';
	str1+='</div>';
	$("#pantVsMetingOvervwDivId").html(str1);
}

function levelWiseEmeetingsData(divId)
{
	var levelWiseEmeetingArr = ['district','assembly','mandal'];
	var collapse='';
		for(var i in levelWiseEmeetingArr)
		{
			collapse+='<div class="panel-group" id="accordion'+divId+''+levelWiseEmeetingArr[i]+'" role="tablist" aria-multiselectable="true">';
				collapse+='<div class="panel panel-default panel-black">';
					collapse+='<div class="panel-heading" role="tab" id="heading'+divId+''+levelWiseEmeetingArr[i]+'">';
						if(i == 0)
						{
							collapse+='<a role="button" class="panelCollapseIcon   data-toggle="collapse" data-parent="#accordion'+divId+''+levelWiseEmeetingArr[i]+'" href="#collapse'+divId+''+levelWiseEmeetingArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseEmeetingArr[i]+'">';
						}else{
							collapse+='<a role="button" class="panelCollapseIcon collapsed '+divId+''+levelWiseEmeetingArr[i]+'"  data-toggle="collapse" data-parent="#accordion'+divId+''+levelWiseEmeetingArr[i]+'" href="#collapse'+divId+''+levelWiseEmeetingArr[i]+'" aria-expanded="true" aria-controls="collapse'+divId+''+levelWiseEmeetingArr[i]+'">';
						}
						collapse+='<h4 class="panel-title text-capital">'+levelWiseEmeetingArr[i]+' Overview Details</h4>';
							
						collapse+='</a>';
					collapse+='</div>';
					if(i == 0)
					{
						collapse+='<div id="collapse'+divId+''+levelWiseEmeetingArr[i]+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseEmeetingArr[i]+'">';
					}else{
						collapse+='<div id="collapse'+divId+''+levelWiseEmeetingArr[i]+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+divId+''+levelWiseEmeetingArr[i]+'">';
					}
					
						collapse+='<div class="panel-body">';
							collapse+='<div id="'+divId+''+levelWiseEmeetingArr[i]+'"></div>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		}
		
			
	$("#levelWiseEmeetingsId").html(collapse);
	for(var i in levelWiseEmeetingArr){
		getEMeetingsLevelWiseOverViewDetails(levelWiseEmeetingArr[i]);
	}
}
function getEMeetingsLevelWiseOverViewDetails(sublocationType){
	$("#Emeeting"+sublocationType).html(spinner);
	var json = {
			"locationId" :  "0",//MenuLocationId
			"locationType" : "state",//MenuLocation
			fromDate : currentFromDate,
			toDate : currentToDate,
			"sublocaType" : sublocationType
	};
	$.ajax({                
		type:'POST',    
		url: 'getEMeetingsLevelWiseOverViewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildEMeetingsLevelWiseOverViewDetails(result,sublocationType);
		}else{
			$("#Emeeting"+sublocationType).html("No Data Available")
		}
	});
}
function buildEMeetingsLevelWiseOverViewDetails(result,sublocationType){
	var str='';
	
	str+='<table class="table table_EmeetingCls" id="dataTable'+sublocationType+'" style="width:100%">';
		str+='<thead>';
			
			str+='<tr>';
				if(sublocationType == "district"){
					str+='<th rowspan="2">District</th>';
				}else if(sublocationType == "assembly"){
					str+='<th rowspan="2">District</th>';
					str+='<th rowspan="2">Constituency</th>';
				}else if(sublocationType == "mandal"){
					str+='<th rowspan="2">District</th>';
					str+='<th rowspan="2">Constituency</th>';
					str+='<th rowspan="2">Mandal</th>';
				}
				
				
				str+='<th colspan="2" style="border-left:1px solid #ddd !important;" class="thead_Detail_color">Details</th>';
				str+='<th colspan="4" class="thead_Consduc_color">Conducted eMeetings</th>';
				str+='<th colspan="2" class="thead_Agenda_color">Agenda Points</th>';
				str+='<th colspan="3" class="thead_NotC_color">Not Conducted Panchayaties</th>';
			str+='</tr>';
			str+='<tr>';
				str+='<th class="thead_Detail_color">Total e-M Panchayaties</th>';
				str+='<th class="thead_Detail_color">Total Expected Meetings</th>';
				
				str+='<th class="thead_Consduc_color">Total Meetings</th>';
				str+='<th class="thead_Consduc_color">General Meetings</th>';
				str+='<th class="thead_Consduc_color">Requested Meetings</th>';
				str+='<th class="thead_Consduc_color">Emergency Meetings</th>';
				
				str+='<th class="thead_Agenda_color">Total Agenda</th>';
				str+='<th class="thead_Agenda_color">Approved Agenda</th>';
				
				str+='<th class="thead_NotC_color">Beyond 60Days</th>';
				str+='<th class="thead_NotC_color">Beyond 90Days</th>';
				str+='<th class="thead_NotC_color">Beyond 120Days</th>';
			str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					if(sublocationType == "district"){
						str+='<td style="text-align:left !important;color:#009FFF !important;text-decoration:underline;" class="conductedWiseMeetingCls" attr_name="'+result[i].districtName+'" attr_location_id="'+result[i].districtId+'" attr_location_type="district">'+result[i].districtName+'</td>';
					}else if(sublocationType == "assembly"){
						str+='<td style="text-align:left !important;border-right:1px solid #ddd !important">'+result[i].districtName+'</td>';
						str+='<td style="text-align:left !important;color:#009FFF !important;text-decoration:underline;" class="conductedWiseMeetingCls" attr_name="'+result[i].constituencyName+'" attr_location_id="'+result[i].constituencyId+'" attr_location_type="assembly">'+result[i].constituencyName+'</td>';
					}else if(sublocationType == "mandal"){
						str+='<td style="text-align:left !important;border-right:1px solid #ddd !important">'+result[i].districtName+'</td>';
						str+='<td style="text-align:left !important;border-right:1px solid #ddd !important">'+result[i].constituencyName+'</td>';
						str+='<td style="text-align:left !important;color:#009FFF !important;text-decoration:underline" class="conductedWiseMeetingCls" attr_name="'+result[i].mandalName+'" attr_location_id="'+result[i].mandalId+'" attr_location_type="assembly">'+result[i].mandalName+'</td>';
					}
					
					
					str+='<td class="tbody_Detail_color">'+result[i].totalPanchayats+'</td>';
					str+='<td class="tbody_Detail_color">'+result[i].exceptedMeetings+'</td>';
					
					str+='<td class="tbody_Consduc_color">'+result[i].totalMeetings+'</td>';
					str+='<td class="tbody_Consduc_color">'+result[i].generalMeetings+'</td>';
					str+='<td class="tbody_Consduc_color">'+result[i].reqMeetings+'</td>';
					str+='<td class="tbody_Consduc_color">'+result[i].emergencyMeetings+'</td>';
					
					str+='<td class="tbody_Agenda_color">'+result[i].totalAgendaPts+'</td>';
					str+='<td class="tbody_Agenda_color">'+result[i].approvedAgendaPts+'</td>';
					
					
					str+='<td class="tbody_NotC_color">'+result[i].beyond60Days+'</td>';
					str+='<td class="tbody_NotC_color">'+result[i].beyond90Days+'</td>';
					str+='<td class="tbody_NotC_color">'+result[i].beyond120Days+'</td>';
					
				str+='</tr>';
			}
		str+='</tbody>';
	str+='</table>';
	$("#Emeeting"+sublocationType).html(str);
	$("#dataTable"+sublocationType).dataTable({
		"iDisplayLength": 13,
		"aaSorting": [],
		"aLengthMenu": [[13, 15, 20,50, -1], [13, 15, 20,50, "All"]]
	});
}

$(document).on("change","#districtId",function(){
	var levelValId = $(this).val();
	getAllSubLocationsTax(3,levelValId,"constituency","constituency");
});
$(document).on("change","#constituencyId",function(){
	var levelValId = $(this).val();
	getAllSubLocationsTax(4,levelValId,"","mandal");
});

function getAllSubLocationsTax(levelId,levelValue,typeVal,appendDivId){
	if(appendDivId == "district"){
		$("#districtId").html('');
	}else if(appendDivId == "constituency"){
		$("#constituencyId").html('');
	}else if(appendDivId == "mandal"){
		$("#mandalId").html('');
	}
	var json = {
		searchLevelId :	levelId,
		searchLevelValue : levelValue,
		type :typeVal
	};
	$.ajax({                
		type:'POST',    
		url: 'getAllSubLocations',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildLocationsDetails(result,appendDivId);
		}
	});
}
function buildLocationsDetails(result,appendDivId){
	var str='';
	if(appendDivId == "district"){
		str+='<option value="0">Select District</option>';
	}else if(appendDivId == "constituency"){
		str+='<option value="0">Select Constituency</option>';
	}else if(appendDivId == "mandal"){
		str+='<option value="0">Select Mandal</option>';
	}
	
	
	for(var i in result){
		str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}
	if(appendDivId == "district"){
		$("#districtId").html(str);
		$("#districtId").trigger("chosen:updated");
	}else if(appendDivId == "constituency"){
		$("#constituencyId").html(str);
		$("#constituencyId").trigger("chosen:updated");
	}else if(appendDivId == "mandal"){
		$("#mandalId").html(str);
		$("#mandalId").trigger("chosen:updated");
	}
}
$(document).on("click",".conductedWiseMeetingCls",function(){
	var name = $(this).attr("attr_name");
	var location_id = $(this).attr("attr_location_id");
	var location_type = $(this).attr("attr_location_type");
	$(".chosen-select").chosen();
	$("#conductedMeetingModalDivId").modal('show');
	$("#conductedMeetingHeadingId").html(""+name+" District Conducted Meetings  "+currentFromDate+" / "+currentToDate+"");
	getLevelsWiseConductedMeetingDetails(location_id,'district');	
});
$(document).on("click",".getDetailsCls",function(){
	var districtId = $("#districtId").val();
	var constituencyId = $("#constituencyId").val();
	var mandalId = $("#mandalId").val();
	if(districtId !=0 && constituencyId == 0 && mandalId == 0){
		getLevelsWiseConductedMeetingDetails(districtId,'district');
	}else if(districtId !=0 && constituencyId != 0 && mandalId == 0){
		getLevelsWiseConductedMeetingDetails(constituencyId,'assembly');
	}else if(districtId !=0 && constituencyId != 0 && mandalId != 0){
		getLevelsWiseConductedMeetingDetails(mandalId,'mandal');
	}
	
});


function getLevelsWiseConductedMeetingDetails(locationId,locationType){
	$("#conductedMeetingDivId").html(spinner);
	var json = {
				"locationId" :  15,
				"locationType" :'district'
			};
	$.ajax({                
		type:'POST',    
		url: 'getLevelsWiseConductedMeetingDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildLevelsWiseConductedMeetingDetails(result);
		}else{
			$("#conductedMeetingDivId").html("No Data Available");
		}
	});
} 

function buildLevelsWiseConductedMeetingDetails(result){
	var str='';
	
	str+='<div class="row">';
		str+='<div class="col-sm-8 m_top10">';
		str+='<div class="custom-div">';
			str+='<div class="row">';
				str+='<div class="col-sm-8">';
					str+='<div class="custom-div" style="padding: 30px;">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img src="Assests/images/total-panchayats.png" style="width:45px; height:45px;"/>';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h4 class="m_top10 font_weight">Total<br>e-Panchayats</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3 text-right">';
								str+='<h3 class="font_weight m_top10">'+result.totalPanchayats+'</h3>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-4">';
					str+='<div class="custom-div" style="padding-bottom: 2px;">';
						str+='<div class="row">';
							str+='<div class="col-sm-7">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img src="Assests/images/icon-meetngs.png" style="width:45px; height:45px;"/>';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="m_top10 font_weight">Total Meetings</h5>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-5">';
								str+='<h4 class="font_weight m_top10">'+result.totalMeetings+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					str+='<div class="row">';
						str+='<div class="col-sm-12 m_top10">';
							str+='<div class="custom-div-bg" style="padding-top: 8px;padding-bottom: 10px;">';
								str+='<h4 class="font_weight" style="font-size: 15px;"><i class="fa fa-check-circle-o green_color fa-2x" style="position: relative;left: 7px;"></i> <span style="margin-left: 20px;position: relative;top: -3px;">Conducted</span> <span style="margin-left: 40px;position: relative;top: -1px;">'+result.conductedMeetings+'</span></h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4 m_top10">';
			str+='<div class="custom-div">';
				str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<img src="Assests/images/icon-agenda.png" style="width:45px; height:45px;"/>';
						str+='</div>';
						str+='<div class="media-body">';
							str+='<h5 class="m_top10 font_weight">Total Agenda Points</h5>';
							str+='<h4 class="m_top5 font_weight">'+result.totalAgendaPts+'</h4>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='</div>';
				str+='<div class="row">';
					str+='<div class="col-sm-12">';
						str+='<div class="custom-div-bg m_top10" style="padding: 5px;">';
							str+='<div class="row">';
								str+='<div class="col-sm-6">';
									str+='<div class="border_left_width_green">';
										str+='<h6 class="font_weight good_color margin_left_8">Approved</h6>';
										str+='<h4 class="m_top10 margin_left_8 font_weight">'+result.approvedAgendaPts+'</h4>';
									str+='</div>';
								str+='</div>';
								str+='<div class="col-sm-6">';
									str+='<div class="border_left_width_bad">';
										str+='<h6 class="font_weight bad_color margin_left_8">Not-Approved </h6>';
										str+='<h4 class="m_top10 margin_left_8 font_weight">'+result.notApprovedAgendaPts+'</h4>';
									str+='</div>';
								str+='</div>';
							str+='</div>';   
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	
	str+='<div class="row">';
		str+='<div class="col-sm-6 m_top10">';
			str+='<div class = "panel panel-default border_radius_5 m_bottom_0">';
				str+='<div class = "panel-heading">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<i class="fa fa-check-circle green_color fa-2x"></i>';
						str+='</div>';
						str+='<div class="media-body text-center">';
							str+='<h5 class="font_weight text_pos">Panchayaties - '+result.conductedPanchayats+'</h5>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="panel-body">';
					str+='<div class="row">';
						str+='<div class="text-center">';
							str+='<h5 class="font_weight">Conducted eMeetings - '+result.conductedMeetings+'</h5>';
						str+='</div>';
					str+='</div>';
					str+='<hr style="margin-top:10px;margin-bottom:10px;">';
					str+='<div class="row">';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="border_left_blue">';
								str+='<h6 class="font_weight margin_left_8 text_color_e">General</h6>';
								str+='<h4 class="font_weight m_top10 margin_left_8">'+result.generalMeetings+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="border_left_width_green ">';
								str+='<h6 class="font_weight margin_left_8 text_color_e">Requested</h6>';
								str+='<h4 class="font_weight m_top10 margin_left_8">'+result.reqMeetings+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="border_left_width_bad ">';
								str+='<h6 class="font_weight margin_left_8 text_color_e">Emergency</h6>';
								str+='<h4 class="font_weight m_top10 margin_left_8">'+result.emergencyMeetings+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
			   str+='</div>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-6 m_top10">';
			str+='<div class = "panel panel-default border_radius_5 m_bottom_0">';
				str+='<div class = "panel-heading">';
					str+='<div class="media">';
						str+='<div class="media-left">';
							str+='<i class="fa fa-times-circle-o red_color fa-2x"></i>';
						str+='</div>';
						str+='<div class="media-body text-center">';
							str+='<h5 class="font_weight text_pos">Panchayaties - '+result.notConductedPanchayts+'</h5>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="panel-body">';
					str+='<div class="row">';
						str+='<div class="text-center">';
							str+='<h5 class="font_weight">Not Conducted eMeetings - '+result.notConductedMeetings+'</h5>';
						str+='</div>';
					str+='</div>';
					str+='<hr style="margin-top:10px;margin-bottom:10px;">';
					str+='<div class="row">';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="border_left_yellow">';
								str+='<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;60&nbsp;days</h6>';
								str+='<h4 class="font_weight m_top10 margin_left_8">'+result.beyond60Days+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="border_left_pink ">';
								str+='<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;90&nbsp;days</h6>';
								str+='<h4 class="font_weight m_top10 margin_left_8">'+result.beyond90Days+'</h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="border_left_teak ">';
								str+='<h6 class="font_weight margin_left_8 text_color_e">Beyond&nbsp;120&nbsp;days</h6>';
								str+='<h4 class="font_weight m_top10 margin_left_8">'+result.beyond120Days+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
			   str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table_Cond" id="dataTabledConductedM">';
					str+='<thead>';
						str+='<tr>';
						str+='<th>Panchayat</th>';
						str+='<th>Meeting Name</th>';
						str+='<th>Conducted Date</th>';
						str+='<th>Panchayat memberes</th>';
						str+='<th>Attended memberes</th>';
						str+='<th>Absent memberes</th>';
						str+='<th>MOM</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList){
							str+='<tr>';
								str+='<td>'+result.subList[i].panchayatName+'</td>';
								str+='<td style="color:#009FFF !important;text-decoration:underline" class="meetingWiseDetailsCls" attr_name="'+result.subList[i].meetingName+'" attr_meeting_id="'+result.subList[i].meetingId+'" attr_meeting_date="'+result.subList[i].meetingDate+'">'+result.subList[i].meetingName+'</td>';
								str+='<td>'+result.subList[i].meetingDate+'</td>';
								str+='<td>'+result.subList[i].totalMembers+'</td>';
								str+='<td>'+result.subList[i].attendedMembers+'</td>';
								str+='<td>'+result.subList[i].absentMembers	+'</td>';
								str+='<td>';
									var scanCopySpl = result.subList[i].minutesOfMeeting.split("."); 
									var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
									if(scanCopyExt =="pdf"){
										str+='<span><a class="fancyboxView" href="#inlineMainFLPDF'+i+'">';
											str+='<h5 class="viewCSS">View</h5>';
										str+='</a></span>';
										str+='<div id="inlineMainFLPDF'+i+'" style="width:100%;display: none;">';
											str+='<object data="'+result.subList[i].minutesOfMeeting+'" type="application/pdf"  style="cursor:pointer;height:1000px;width:1000px"></object>';
											
										str+='</div>';
									}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
										str+='<span><a class="fancyboxView" href="#inlineMainFLIMG'+i+'">';
											str+='<h5 class="viewCSS">View</h5>';
										str+='</a></span>';
										str+='<div id="inlineMainFLIMG'+i+'" style="width:100%;display: none;">';
											str+='<img src="'+result.subList[i].minutesOfMeeting+'" style="cursor:pointer;height:1000px;width:1000px"></img>';
											
										str+='</div>';
									}else{
										str+='<span><b>Click <a href="javascript:{};" onclick="openDoc(\''+result.subList[i].minutesOfMeeting+'\')">Here</a> To View Document</b></span>';
									} 
								
								str+='</td>';
							str+='</tr>';
						}
					}else{
						str+='<tr>';
							str+='<td colspan="7">No Data Available</td>';
						str+='</tr>';
					}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#conductedMeetingDivId").html(str);
	$(".fancyboxView").fancybox();
	$("#dataTabledConductedM").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20,50, -1], [10, 15, 20,50, "All"]]
	});
}
$(document).on("click",".meetingWiseDetailsCls",function(){
	var name = $(this).attr("attr_name");
	var meeting_id = $(this).attr("attr_meeting_id");
	var meeting_date = $(this).attr("attr_meeting_date");
	$("#meetingTypeModalDivId").modal('show');
	$("#meetingTypeHeadingId").html(""+name+" Conducted Meeting on "+meeting_date+"");
	getMeetingDetails(meeting_id,name,meeting_date);
});

function getMeetingDetails(meeting_id,name,meeting_date){
	$("#meetingDetailsDivId").html(spinner);
	var json = {
				"deptId" :  meeting_id
			};
	$.ajax({                
		type:'POST',    
		url: 'getMeetingDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			buildMeetingDetails(result,name,meeting_date);
		}else{
			$("#meetingDetailsDivId").html("No Data Available");
		}
	});
}
function buildMeetingDetails(result,name,meeting_date){
	var str='';
	str+='<div class="bg_mee_pop m_top10">';
		str+='<div class="row">';
			str+='<div class="col-sm-2">';
				str+='<div class="meet_Css">';
					str+='<h5 class="font_weight">Meeting Name</h5>';
					str+='<h5 class="m_top25">'+name+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-2">';
				str+='<div class="meet_Css">';
					str+='<h5 class="font_weight">Date Of Conducted</h5>';
					str+='<h5 class="m_top25">'+meeting_date+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-8">';
				str+='<div class="meet_Css">';
					str+='<h5 class="font_weight">Attendance Details</h5>';
					
					str+='<div class="row">';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pan_css" style="border:1px solid #6AB4FF">';
								str+='<h4 class="" style="font-size: 15px;"><i class="fa fa-check-circle-o" ></i> <span >Panchayat Members</span> <span class="pull-right">'+result.totalMembers+'</span></h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pan_css" style="border:1px solid #0EBE5F">';
								str+='<h4 class="" style="font-size: 15px;"><i class="fa fa-check-circle-o green_color" ></i> <span >Attended Members</span> <span class="pull-right">'+result.attendedMembers+'</span></h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pan_css" style="border:1px solid #DE98A4">';
								str+='<h4 class="" style="font-size: 15px;"><i class="fa fa-times-circle-o red_color" ></i> <span >Absent Members</span> <span class="pull-right">'+result.absentMembers+'</span></h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row">';
			str+='<div class="col-sm-2">';
				str+='<div class="meet_Css">';
					str+='<h5 class="font_weight">Minutes of Meeting</h5>';
					str+='<h5 class="m_top25">'+result.minutesOfMeeting	+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-2">';
				str+='<div class="meet_Css">';
					str+='<h5 class="font_weight">Meeting Duration</h5>';
					str+='<h5 class="m_top25">'+result.meetingDuration+'</h5>';
				str+='</div>';
			str+='</div>';
			str+='<div class="col-sm-8">';
				str+='<div class="meet_Css">';
					str+='<h5 class="font_weight">Agenda Points</h5>';
					
					str+='<div class="row">';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pan_css" style="border:1px solid #6AB4FF">';
								str+='<h4 class="" style="font-size: 15px;"><i class="fa fa-check-circle-o" ></i> <span >Total Points</span> <span class="pull-right">'+result.totalAgendaPts+'</span></h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pan_css" style="border:1px solid #0EBE5F">';
								str+='<h4 class="" style="font-size: 15px;"><i class="fa fa-check-circle-o green_color" ></i> <span >Approved</span> <span class="pull-right">'+result.approvedAgendaPts+'</span></h4>';
							str+='</div>';
						str+='</div>';
						str+='<div class="col-sm-4 m_top10">';
							str+='<div class="pan_css" style="border:1px solid #DE98A4">';
								str+='<h4 class="" style="font-size: 15px;"><i class="fa fa-times-circle-o red_color" ></i> <span >Rejected</span> <span class="pull-right">'+result.notApprovedAgendaPts+'</span></h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					
				str+='</div>';
			str+='</div>';
		str+='</div>';
		
	str+='</div>';
	
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<div class="table-responsive">';
				str+='<table class="table table-bordered table_Cond" id="dataTabledMeetingOverview">';
					str+='<thead>';
						str+='<tr>';
						str+='<th>Member Name</th>';
						str+='<th>Designation</th>';
						str+='<th>Mobile No</th>';
						str+='<th>Invitation Status</th>';
						str+='<th>Attendance</th>';
						str+='<th>Vote</th>';
						str+='<th>Remark</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					if(result.subList !=null && result.subList.length>0){
						for(var i in result.subList){
							str+='<tr>';
								str+='<td>'+result.subList[i].memberName+'</td>';
								str+='<td>'+result.subList[i].designation+'</td>';
								str+='<td>'+result.subList[i].mobileNo+'</td>';
								str+='<td>'+result.subList[i].invitationStatus+'</td>';
								str+='<td>'+result.subList[i].attendance+'</td>';
								str+='<td>'+result.subList[i].vote+'</td>';
								str+='<td>'+result.subList[i].remark+'</td>';
							str+='</tr>';
						}
					}else{
						str+='<tr>';
							str+='<td colspan="7">No Data Available</td>';
						str+='</tr>';
					}
						
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#meetingDetailsDivId").html(str);
	$("#dataTabledMeetingOverview").dataTable({
		"iDisplayLength": 10,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20,50, -1], [10, 15, 20,50, "All"]]
	});
}
$(document).on("click",".modalCloseCls",function(){
    setTimeout(function(){
      $("body").addClass("modal-open")
    },1000);
  });
 function openDoc(docmnt){
	 window.open(docmnt);
}