var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

getEMeetingsOverViewDetails();
levelWiseEmeetingsData('Emeeting')
function getEMeetingsOverViewDetails(){
	$("#emeetingsOverviewDivId").html(spinner);
	$("#pantVsMetingOvervwDivId").html(spinner);
	
	var json = {
		locationId : "1",
		locationType : "state",
		fromDate : "01-06-2017",
		toDate : "03-03-2018"
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
	var json = {
			"locationId" :  "0",//MenuLocationId
			"locationType" : "state",//MenuLocation
			"fromDate" : "01-01-2017",
			"toDate" : "28-02-2018",
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