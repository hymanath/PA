var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
onloadDalithaTejam();
function onloadDalithaTejam(){
	getActivityPerformanceDetailsLocationWise();//first two block
	getActivityAttendedAndImageCoveredDetails();//Last two block
}

$(document).on("click",".dalithaExRRefresh",function(e){
	onloadDalithaTejam();
});
function getActivityPerformanceDetailsLocationWise() {
	$("#overAllAndTop5PoorParliamentDivId").html(spinner);
	var jsObj = {
		activityScopeId : 60,
		fromDate : "",
		toDate : ""
	}
	$.ajax({

		type : 'POST',
		url : 'getActivityPerformanceDetailsLocationWiseAction.action',
		dataType : 'json',
		data : {task : JSON.stringify(jsObj)}
	}).done(function(result) {
		if(result !=null){
			buildActivityPerformanceDetailsLocationWise(result);
		}else{
			$("#overAllAndTop5PoorParliamentDivId").html("No Data Available");
		}
	});
}
function buildActivityPerformanceDetailsLocationWise(result){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<h5 class="text_bold text-capital font_size24" >Top 7 Parliaments with Poor Performance</h5>';
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table details-overview">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Parliament Name</th>';
							str+='<th>Total Village/Ward</th>';
							str+='<th>Village Not Covered</th>';
							str+='<th>Village Not Covered %</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					var countVar =0;
					if(result.subList2 !=null && result.subList2.length>0){
						for(var i in result.subList2){
							str+='<tr>';
							str+='<td>'+result.subList2[i].addressVO.parliamentName+'</td>';
							str+='<td>'+result.subList2[i].totalCount+'</td>';
							str+='<td>'+result.subList2[i].notConductedCount+'</td>';
							str+='<td>'+result.subList2[i].percentage+'</td>';
							str+='</tr>';
							countVar =countVar+1;
							if (countVar === 7) {
								break;
							}
						}
					}else{
						str+='<tr>';
							str+='<td colspan="4">No Data Available</td>';
						str+='</tr>';
					}
						
						
					str+='</tbody>';
				str+='</table>';
		str+='</div>';
	str+='</div>';
str+='</div>';

str+='<div class="row pagebreak">';
		str+='<div class="col-sm-12 m_top10">';
			str+='<h5 class="text_bold text-capital font_size24" >Top 10 - Assembly Constituencies with Poor Performance</h5>';
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table details-overview">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Constituency</th>';
							str+='<th>Parliament Name</th>';
							str+='<th>Total Village/Ward</th>';
							str+='<th>Village Not Covered</th>';
							str+='<th>Village Not Covered %</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					var countVar =0;
					if(result.subList1 !=null && result.subList1.length>0){
						for(var i in result.subList1){
							str+='<tr>';
								str+='<td>'+result.subList1[i].addressVO.constituencyName+'</td>';
								str+='<td>'+result.subList1[i].addressVO.parliamentName+'</td>';
								str+='<td>'+result.subList1[i].totalCount+'</td>';
								str+='<td>'+result.subList1[i].notConductedCount+'</td>';
								str+='<td>'+result.subList1[i].percentage+'</td>';
							str+='</tr>';
							countVar =countVar+1;
							if (countVar === 10) {
								break;
							}
						}
					}else{
						str+='<tr>';
							str+='<td colspan="5">No Data Available</td>';
						str+='</tr>';
					}
						
					str+='</tbody>';
				str+='</table>';
		str+='</div>';
	str+='</div>';
str+='</div>';

$("#overAllAndTop5PoorParliamentDivId").html(str);
}
function getActivityAttendedAndImageCoveredDetails() {
	$("#top10ConstituencyInchAndWhatsImageDivId").html(spinner);
	var jsObj = {
		activityScopeId : 60,
		fromDate : "",
		toDate : ""

	}
	$.ajax({
		type : 'POST',
		url : 'getActivityAttendedAndImageCoveredDetailsAction.action',
		dataType : 'json',
		data : {task : JSON.stringify(jsObj)}
	}).done(function(result) {
		if(result !=null){
			buildActivityAttendedAndImageCoveredDetails(result);
		}else{
			$("#top10ConstituencyInchAndWhatsImageDivId").html("No Data Available");
		}
	});
}
function buildActivityAttendedAndImageCoveredDetails(result){
	var str='';
		str+='<div class="row">';
			str+='<div class="col-sm-12 m_top10">';
				str+='<h5 class="text_bold text-capital font_size24" >Top 10 -  MLA/Constituency Incharge with Poor Attendance</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Constituency</th>';
								str+='<th>Parliament</th>';
								str+='<th>Attendend Days</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar =0;
							if(result.subList1 !=null && result.subList1.length>0){
								for(var i in result.subList1){
									str+='<tr>';
										str+='<td>'+result.subList1[i].addressVO.constituencyName+'</td>';
										str+='<td>'+result.subList1[i].addressVO.parliamentName+'</td>';
										str+='<td>'+result.subList1[i].totalCount+'</td>';
									str+='</tr>';
									countVar =countVar+1;
									if (countVar === 10) {
										break;
									}
								}
							}else{
								str+='<tr>';
									str+='<td colspan="5">No Data Available</td>';
								str+='</tr>';
							}
							
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	str+='<div class="row pagebreak">';
			str+='<div class="col-sm-12 m_top10">';
				str+='<h5 class="text_bold text-capital font_size24" >Top 10 - Low Whatsapp Image Covered Assembly</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Constituency</th>';
								str+='<th>Parliament</th>';
								str+='<th>Images Covered</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						var countVar =0;
							if(result.subList2 !=null && result.subList2.length>0){
								for(var i in result.subList2){
									str+='<tr>';
										str+='<td>'+result.subList2[i].addressVO.constituencyName+'</td>';
										str+='<td>'+result.subList2[i].addressVO.parliamentName+'</td>';
										str+='<td>'+result.subList2[i].totalCount+'</td>';
									str+='</tr>';
									countVar =countVar+1;
									if (countVar === 10) {
										break;
									}
								}
							}else{
								str+='<tr>';
									str+='<td colspan="5">No Data Available</td>';
								str+='</tr>';
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#top10ConstituencyInchAndWhatsImageDivId").html(str);
}