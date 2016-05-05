/* var answrdTabId = 1;
//var headngAnsdId= 1;
//var collapaseAnwdId=1;
function buildAnsweredIvrSurveys(result)
{
	var str=' '
		
	str+='<div class="panel panel-default">';
		str+='<div class="panel-heading">';
			str+='<h4 class="panel-title">Answered Count</h4>';
		str+='</div>';
		str+='<div class="panel-body">';
			str+='<div class="panel-group" id="AnsweredTabId'+answrdTabId+'" aria-multiselectable="true">';
			for(var i in result.verifierVOList){
				if(result.verifierVOList[i].answerType == "Answered"){
				 str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="headingAnswered'+answrdTabId+'">';
						str+='<a role="button" class="collapsed accordion-toggle" data-toggle="collapse" data-parent="#AnsweredTabId'+answrdTabId+'" href="#collapseAnswered'+answrdTabId+'" aria-expanded="true" aria-controls="collapseAnswered'+collapaseAnwdId+'">';
						  str+='<h4 class="panel-title">'+result.verifierVOList[i].name+'</h4>';
						str+='</a>';
					str+='</div>';
					str+='<div id="collapseAnswered'+answrdTabId+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingAnswered'+answrdTabId+'">';
					  str+='<div class="panel-body">';
						str+='<table class="table table-bordered">';
							str+='<thead style="background-color:#ddd;">';
								str+='<th>Question</th>';
								str+='<th>Options</th>';
								str+='<th>Round</th>';
							str+='</thead>';
							str+='<tbody>';
								for(var j in result.verifierVOList[i].verifierVOList){
									str+='<tr>';
										str+='<td>'+result.verifierVOList[i].verifierVOList[j].name+'</td>';
										str+='<td>'+result.verifierVOList[i].verifierVOList[j].verifierVOList[0].name+'</td>';
										str+='<td>'+result.verifierVOList[i].verifierVOList[j].round+'</td>';
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					  str+='</div>';
					str+='</div>';
				  str+='</div>';
			}
			}
			str+='</div>';
		str+='</div>';
	str+='</div>';
	answrdTabId=answrdTabId+1;
	$(".ivrSurvysDetls").html(str);
}
 function unAnsweredSurvey()
{
	var str=' ';
	
	str+='<div class="panel panel-default">';
		str+='<div class="panel-heading">';
			str+='<h4 class="panel-title">Unanswered Count</h4>';
		str+='</div>';
		str+='<div class="panel-body">';
			str+='<div class="panel-group" id="unAnsweredTabId" aria-multiselectable="true">';
			  str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" role="tab" id="headingUnAnswered">';
					str+='<a role="button" class="collapsed accordion-toggle" data-toggle="collapse" data-parent="#AnsweredTabId" href="#collapseUnAnswered" aria-expanded="true" aria-controls="collapseUnAnswered">';
					  str+='<h4 class="panel-title"></h4>';
					str+='</a>';
				str+='</div>';
				str+='<div id="collapseUnAnswered" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingUnAnswered">';
				  str+='<div class="panel-body">';
					str+='<table class="table table-bordered">';
						str+='<thead>';
							str+='<th>Question</th>';
							str+='<th>Options</th>';
							str+='<th>Round</th>';
						str+='</thead>';
						str+='<tbody>';
							str+='<tr>';
								str+='<td></td>';
								str+='<td></td>';
								str+='<td></td>';
							str+='</tr>';
						str+='</tbody>';
					str+='</table>';
				  str+='</div>';
				str+='</div>';
			  str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
} */
 
function getCandidateParticipatedSurveyCnt(){
	//$("#ivrSurvysCandtCntId").html("");
	$('#ivrSurvysCandtCntId ul li').remove();
	$("#ivrTypeDetailsDivId").html("");
	$('.ivrSurveyCandtDetailsCls').html("");
	var jsObj={
		cadreId:globalCadreId
	}
	
	$.ajax({
			type:'POST',
			 url: 'getCandateParicipatedSurveyCountAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null)
				buildIVRSurveyTabs(result);
			});
}
function buildIVRSurveyTabs(result){
	
	var str = '';
	if(result.totalCount != null && result.totalCount > 0){
	$("#ivrSurvysCandtCntId ul").append('<li style="margin-top:0px;padding:0px;"><a class="text-bold" onclick="getTypeWiseIvrDetailsOFCadre();">CANDIDATE PARTICIPATED SURVEYS&nbsp&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>');
	}else{	
	$("#ivrSurvysCandtCntId ul").append('<li style="padding:10px 15px;">CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;0</li>');
	}
	$('#ivrsurveyDataLoadoing').hide();
}
function getSurveysOnCandidateCount(){
	$('#ivrSurvysCandtCntId ul li').remove();
	$('.ivrSurveyCandtDetailsCls').html("");
	$("#ivrTypeDetailsDivId").html("");
	var jsObj={
	//globalCandidateId
		candidateId:446493
	}
	$('#ivrsurveyDataLoadoing').show();
	$.ajax({
			type:'POST',
			 url: 'getSurveysOnCandidateCountAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				
				buildIVRSurveysOnCandidateCount(result);
			});
}
function buildIVRSurveysOnCandidateCount(result){
	
	var str = '';
	if(result.totalCount != null && result.totalCount > 0){
	$("#ivrSurvysCandtCntId ul").append('<li style="margin-top: 0px;padding:0px; left: 10px;" class="active li_arr"><a class="text-bold" onclick="getIVRSurveysOnCandidateDetails();">SURVEYS ON CANDIDATE&nbsp&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a></li>');
	}else{	
	$("#ivrSurvysCandtCntId ul").append('<li style="padding:10px 15px;" class="text-bold">SURVEYS ON CANDIDATE&nbsp;&nbsp;&nbsp;&nbsp;0</li>');
	}
	$('#ivrsurveyDataLoadoing').hide();
}

$(document).on("click","#ivrSurvysCandtCntId ul li",function(){
	$("#ivrSurvysCandtCntId ul li").removeClass("active li_arr")
	$(this).addClass("active li_arr")
})
function getIVRSurveysOnCandidateDetails()
{
	$("#ivrTypeDetailsDivId").html("");
	$('.ivrSurveyCandtDetailsCls').html("");
	$("#ivrDetailsBodyId").html("");
	//var candidateId = globalCandidateId;
	
	var jsObj={
			candidateId:446493,
			task:""
	}
	$('#ivrsurveyDataLoadoing').show();
	$.ajax({
		type:'GET',
		url :'getSurveysOnCandidateDetailsAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		buildSurveysOnCandidateDetails(result);
	});
}
function buildSurveysOnCandidateDetails(result){
	
	var str = '';
	if(result != null && result.length >0){
	str+='<div class="panel-group" style="margin-top:20px" id="accordion121" role="tablist" aria-multiselectable="true">';
			for(var i in result){
				
			 str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="headingOne'+i+'">';
						str+='<a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion121" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
							str+='<h4 class="panel-title">';
								str+=''+result[i].name+'';
							str+='</h4>';
						str+='</a>';
					str+='</div>';
					
					str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
						str+='<div class="panel-body">';
						str+='<div>';
				for(var j in result[i].questions)
					{
						str+='<table class="table table-bordered">';
						str+='<tr><td colspan="3" style="text-weight:bold">'+result[i].questions[j].name+'</td></tr>';
						str+='<b><tr><td>Option</td><td>Count</td><td>Percentage</td></tr></b>';
						for(var k in result[i].questions[j].options){
							str+='<tr>';
								str+='<td>'+result[i].questions[j].options[k].name+'</td>';
								str+='<td>'+result[i].questions[j].options[k].count+'</td>';
								str+='<td>'+result[i].questions[j].options[k].percentage+'</td>';
							str+='</tr>';
						}
						str+='</table>';
					}
						str+='</div>';
					 str+='</div>';
					str+='</div>';
				  str+='</div>';
			
			}
			str+='</div>';
			$('.ivrSurveyCandtDetailsCls').html(str);
			$('#ivrsurveyDataLoadoing').hide();
	}else{
		$('#ivrsurveyDataLoadoing').hide();
		$('.ivrSurveyCandtDetailsCls').html("NO DATA AVAILABLE");
	}
}