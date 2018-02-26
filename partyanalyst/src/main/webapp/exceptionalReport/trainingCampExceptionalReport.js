var spinner = '<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>';
onloadTrainingCalls();
function onloadTrainingCalls(){
	getListOfParliamentsWithPoorPerformance();
	getListOfAssemblyWithPoorPerformance();
}
function getListOfParliamentsWithPoorPerformance(){
		$("#overAllAndTop5ParliamentDivId").html(spinner);
	var jsObj = { 
		fromDateStr : "",
		toDateStr : "",
		stateId : 1,
		size : 7,
		tdpCommitteeLevelIds : [5,6,7,8,9],
		enrollmentYearIds : [4],
		trainingCampProgramIds : [8],   
		locationLevelId : 2,
		locationLevelValues : []
	}
	$.ajax({
		type : 'POST',      
		url : 'getListOfParliamentsWithPoorPerformance.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildListOfParliamentsWithPoorPerformance(result);
		}else{
			$("#overAllAndTop5ParliamentDivId").html("No Data Available");
		}
	});
}
function buildListOfParliamentsWithPoorPerformance(result){
	var str='';
	var OverallCandidatesTrained=result[0].asOfNowTrained+result[0].yetToTrain
	str+='<div class="row">';
		str+='<div class="col-sm-4">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Overall Candidates to be trained</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+OverallCandidatesTrained+'</h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">As of now trained</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result[0].asOfNowTrained+' <small style="color:green;">'+result[0].asOfNowTrainedPer+' %</small></h3>';
			str+='</div>';
		str+='</div>';
		str+='<div class="col-sm-4">';
			str+='<div class="pad_d1ab66">';
				str+='<h4 class="font_size24">Yet to trained</h4>';
				str+='<h3 class="text_bold m_top10 font_size24">'+result[0].yetToTrain+' <small style="color:green;">'+result[0].yetToTrainPer+' %</small></h3>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	str+='<div class="row pagebreak">';
		str+='<div class="col-sm-12 m_top20">';
			str+='<h5 class="text_bold text-capital font_size24" >Top 7 Parliaments with Poor Performance</h5>';
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table details-overview">';
					str+='<thead>';
						str+='<tr>';
							str+='<th>Parliament Name</th>';
							str+='<th>Total Invitees</th>';
							str+='<th>Yet To Trained</th>';
							str+='<th>%</th>';
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							str+='<tr>';
							str+='<td>'+result[i].parliament+'</td>';
							str+='<td>'+result[i].totalEligibleCount+'</td>';
							str+='<td>'+result[i].totalNotAttenedCount+'</td>';
							str+='<td>'+result[i].totalNotAttenedCountPer+'</td>';
							str+='</tr>';
						}
						
					str+='</tbody>';
				str+='</table>';
		str+='</div>';
	str+='</div>';
str+='</div>';
$("#overAllAndTop5ParliamentDivId").html(str);
}

function getListOfAssemblyWithPoorPerformance(){
$("#top5ConstituencyWithPoorPerDivId").html(spinner);    
	var jsObj = { 
		fromDateStr : "",
		toDateStr : "",
		stateId : 1,
		size : 10,
		tdpCommitteeLevelIds : [5,6,7,8,9],
		enrollmentYearIds : [4],
		trainingCampProgramIds : [8],        
		locationLevelId : 2,
		locationLevelValues : []
	}
	$.ajax({
		type : 'POST',
		url : 'getListOfAssemblyWithPoorPerformance.action',       
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildListOfAssemblyWithPoorPerformance(result);
		}else{
			$("#top5ConstituencyWithPoorPerDivId").html("No Data Available");    
		}
	});
}

function buildListOfAssemblyWithPoorPerformance(result){
	var str='';
	str+='<div class="row">';
		str+='<div class="col-sm-12 m_top20">';
			str+='<h5 class="text_bold text-capital font_size24">Top 10 Assembly Constituencies with Poor Performance-Center Wise</h5>';
		str+='</div>';
	str+='</div>';
	for(var i in result){
		if(result[i].name == "SVV CENTER" || result[i].name == "EWK CENTER"){
			str+='<div class="row ">';
		}else if(result[i].name == "GPN CENTER"){
			str+='<div class="row pagebreak_before">';
		}else{
			str+='<div class="row pagebreak ">';
		}
		
			str+='<div class="col-sm-12 m_top20">';
				str+='<h5 class="text_bold">'+result[i].name+'</h5>';
				str+='<div class="table-responsive m_top10">';
					str+='<table class="table details-overview">';
						str+='<thead>';
							str+='<tr>';
								str+='<th>Assembly Constituency</th>';
								str+='<th>Parliament</th>';
								str+='<th>Total Invitees</th>';
								str+='<th>Yet To Trained</th>';
								str+='<th>%</th>';
							str+='</tr>';
						str+='</thead>';
						str+='<tbody>';
						if(result[i].trainingProgramList !=null && result[i].trainingProgramList.length>0){
							for(var j in result[i].trainingProgramList){
								str+='<tr>';
									str+='<td>'+result[i].trainingProgramList[j].constituency+'</td>';
									str+='<td>'+result[i].trainingProgramList[j].parliament+'</td>';
									str+='<td>'+result[i].trainingProgramList[j].totalEligibleCount+'</td>';
									str+='<td>'+result[i].trainingProgramList[j].totalNotAttenedCount+'</td>';
									str+='<td>'+result[i].trainingProgramList[j].totalNotAttenedCountPer+'</td>';
								str+='</tr>';
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
}
$("#top5ConstituencyWithPoorPerDivId").html(str);
}
$(document).on("click",".trainingCampExRRefresh",function(e){
	onloadTrainingCalls();
});
