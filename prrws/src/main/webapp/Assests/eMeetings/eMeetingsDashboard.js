var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

getEMeetingsOverViewDetails();

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
					str+='<div class="custom-div">';
						str+='<div class="row">';
							str+='<div class="col-sm-9">';
								str+='<div class="media">';
									str+='<div class="media-left">';
										str+='<img src="Assests/images/total-panchayats.png" style="width:45px; height:45px;"/>';
									str+='</div>';
									str+='<div class="media-body">';
										str+='<h5 class="m_top10 font_weight">Total<br>e-Panchayats</h5>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							str+='<div class="col-sm-3 text-right">';
								str+='<h4 class="font_weight m_top10">'+result.totalPanchayats+'</h4>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
				str+='<div class="col-sm-4">';
					str+='<div class="custom-div">';
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
							str+='<div class="custom-div-bg">';
								str+='<h5 class="font_weight text-center">Conducted</h5>';
								str+='<h5 class="font_weight"><span class="float-lt"><i class="fa fa-check-circle-o green_color fa-2x"></i></span><span class="m_left text_pos m_top5">'+result.conductedMeetings+'</span></h5>';
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
						str+='<div class="custom-div-bg m_top20">';
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
