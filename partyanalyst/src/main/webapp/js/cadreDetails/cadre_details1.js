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
	var jsObj={
		cadreId:globalCadreId
	}
	$.ajax({
			type:'POST',
			 url: 'getCandateParicipatedSurveyCountAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				
				buildIVRSurveyTabs(result);
			});
}
function buildIVRSurveyTabs(result){
	
	var str = '';
	str+='<ul role="tablist" class="nav nav-tabs tab-list display-style" >';
	if(result.totalCount != null && result.totalCount>0){
	str+='<li style="margin-top: 0px;padding:0px; left: 10px;" class="active li_arr">';
	str+='<a class="text-bold" onclick="getTypeWiseIvrDetailsOFCadre();">CANDIDATE PARTICIPATED SURVEYS&nbsp&nbsp;&nbsp;&nbsp;&nbsp;'+result.totalCount+'</a>';
	str+='</li>';
	//$("#ivrsurveyDataLoadoing").hide();
	}else{	
	str+='<li style="padding:10px 15px;">CANDIDATE PARTICIPATED SURVEYS&nbsp;&nbsp;&nbsp;&nbsp;0</li>';
	//$("#ivrsurveyDataLoadoing").hide();
	}
	str+='</ul>';
	$(".ivrSurvyTabId").html(str);
}