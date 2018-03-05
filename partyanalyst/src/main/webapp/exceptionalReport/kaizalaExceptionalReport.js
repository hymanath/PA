var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
onloadKaizalaCalls();
function onloadKaizalaCalls(){
	getConstituencyWisePoorPerformance("parliament","performanceKaizalaParliamentWiseDetailsDivId");
	getConstituencyWisePoorPerformance("constituency","performanceKaizalaConstituencyWiseDetailsDivId");
	getOverallReport();
}

function getConstituencyWisePoorPerformance(typeval,id){ 
 $("#"+id).html(spinner);
	var jsObj = { 
		stateId : 1,
		size : 50,
		location : typeval //"parliament"//constituency
	}
	$.ajax({
		type : 'POST',      
		url : 'getConstituencyWisePoorPerformance.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildConstituencyWisePoorPerformance(result,typeval,id);
		}else{
			$("#"+id).html("No Data Available");
		}
	});
}
function buildConstituencyWisePoorPerformance(result,typeval,id){
	var str='';
	
			if(typeval == "parliament"){
				str+='<div class="row">';
					str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >Top 7 Parliaments with Poor Performance</h5>';
			}else{
				str+='<div class="row">';
					str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold text-capital font_size24" >Top 10 Assembly Constituencies with Poor Performance</h5>';
			}
				
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview-tour">';
						str+='<thead>';
							str+='<tr>';
							if(typeval == "parliament"){
								str+='<th>Parliament</th>';
								str+='<th>Target</th>';
								//str+='<th>No Smart Phone</th>';
								str+='<th>Committee Installed</th>';
								str+='<th>Committee Not installed</th>';
								str+='<th>%</th>';
								str+='<th>Public Installed</th>';
								str+='<th>Cadre Installed</th>';
								str+='<th>Total Installed</th>';
							}else{
								str+='<th>Constituency</th>';
								str+='<th>Parliament</th>';
								str+='<th>Target</th>';
								//str+='<th>No Smart Phone</th>';
								str+='<th>Committee Installed</th>';
								str+='<th>Committee Not installed</th>';
								str+='<th>%</th>';
								str+='<th>Public Installed</th>';
								str+='<th>Cadre Installed</th>';
								str+='<th>Total Installed</th>';
							}
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						
						if(typeval == "parliament"){
							var countVar1 =0;
						}else{
							var countVar2 =0;
						}
							for(var i in result){
								str+='<tr>';
									if(typeval == "parliament"){
										str+='<td>'+result[i].parliamentName+'</td>';
										str+='<td>'+result[i].target+'</td>';
										str+='<td>'+result[i].committeeInstalled+'</td>';
										str+='<td>'+result[i].committeeNotInstalled+'</td>';
										str+='<td>'+result[i].committeeNotInstalledPer+'</td>';
										str+='<td>'+result[i].publicInstalled+'</td>';
										str+='<td>'+result[i].cadreInstalled+'</td>';
										str+='<td>'+result[i].totalInstalled+'</td>';
									}else{
										str+='<td>'+result[i].constituencyName+'</td>';
										str+='<td>'+result[i].parliamentName+'</td>';
										str+='<td>'+result[i].target+'</td>';
										str+='<td>'+result[i].committeeInstalled+'</td>';
										str+='<td>'+result[i].committeeNotInstalled+'</td>';
										str+='<td>'+result[i].committeeNotInstalledPer+'</td>';
										str+='<td>'+result[i].publicInstalled+'</td>';
										str+='<td>'+result[i].cadreInstalled+'</td>';
										str+='<td>'+result[i].totalInstalled+'</td>';
									}	
									
								
								str+='</tr>';
								if(typeval == "parliament"){
									countVar1 =countVar1+1;
									if (countVar1 === 7) {
										break;
									}
								}else{
									countVar2 =countVar2+1;
									if (countVar2 === 10) {
										break;
									}
								}
								
							}
							
						str+='</tbody>';
					str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#"+id).html(str);
}
function getOverallReport(){
	$("#overAllKaizalaDivId").html(spinner);
	var jsObj = { 
		stateId : 1
	}
	$.ajax({
		type : 'POST',      
		url : 'getOverallReport.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null){
			buildOverallReport(result);
		}else{
			$("#overAllKaizalaDivId").html("No Data Available");
		}  
	});
}      
$(document).on("click",".kaizalaExRRefresh",function(e){
	onloadKaizalaCalls();
});
function buildOverallReport(result){
	var str='';
	
	str+='<div class="row">';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Total Target</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.target+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Installed</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.totalInstalled+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-3 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Not Installed</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.totalNotInstalled+'</h3>';
			str+='</div>';
		str+='</div>';
		/* str+='<div class="col-sm-3 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">No Smart Phone</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result.totalNotHavingSmartphone+'</h3>';
			str+='</div>';
		str+='</div>'; */
		var  Trained= result.totalInstalled+result.totalNotHavingSmartphone;
		str+='<div class="col-sm-3 m_top10">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Trained <small> -  (Installed+NoSmartPhone)</small></h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+Trained+'</h3>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#overAllKaizalaDivId").html(str);
}