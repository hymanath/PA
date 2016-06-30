function getCandidateParticipatedSurveyCnt(){
	

	$("#IvrcandiParticipatedId").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
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
	if(result != null && result.totalCount != null && result.totalCount > 0){

		$("#IvrcandiParticipatedId").html('<a class="text-bold" onclick="getTypeWiseIvrDetailsOFCadre();" style="cursor:pointer;" >'+result.totalCount+'</a>');
	
	}
	else{	
	
	$("#IvrcandiParticipatedId").html('0');
	}
	
}
function getSurveysOnCandidateCount(){
	
	$("#IvrSurveyOnCandiId").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
	var jsObj={
	
		candidateId:globalCandidateId
	}
	
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
	if(result != null && result.totalCount != null && result.totalCount > 0){
		$("#IvrSurveyOnCandiId").html('<a class="text-bold" onclick="getIVRSurveysOnCandidateDetails();" style="cursor:pointer;" '+result.totalCount+'</a>');
	
	}
	else{	
	
	$("#IvrSurveyOnCandiId").html('0');
	}
	
}


function getIVRSurveysOnCandidateAreaCount(){

	
		$("#IvrcandiAreaId").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
	var districtId=0;

	if($('#cadreParticipatedDistrictId').val().length > 0)
		 districtId = $("#cadreParticipatedDistrictId").val();
	else 
	{
		if($('#cadreDistrictId').val().length > 0)
	 districtId = $("#cadreDistrictId").val();
	}
		
	var jsObj={
		districtId:districtId
	}
	
	$.ajax({
			type:'POST',
			 url: 'getIVRSurveysOnCandidateAreaCountAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				
				buildIVRSurveysOnCandidateAreaCount(result);
				
			});
}
function buildIVRSurveysOnCandidateAreaCount(result){

	var str = '';
	if(result != null && result.totalCount != null && result.totalCount > 0){
		
	
	$("#IvrcandiAreaId").html('<a class="text-bold" onclick="getIVRSurveysOnCandidateAreaDetails();" style="cursor:pointer;" >'+result.totalCount+'</a>');
	}
	else
		$("#IvrSurveyOnCandiId").html('0');
	
}

function getIVRSurveysOnCandidateAreaDetails(){
	$("#ivrTypeDetailsDivId").html("");
	$('.ivrSurveyCandtDetailsCls').html("");
	$("#ivrDetailsBodyId").html("");
	var boothId =0;
	
	var constituencyId=0;
	var districtId=0;
	var parliamentId=0;
	if($('#cadreParticipatedDistrictId').val().length > 0)
	 {
		if($('#cadreParticipatedDistrictId').val().length > 0)
		 districtId = $("#cadreParticipatedDistrictId").val();
	 	if($('#cadreParticipatedConstituencyId').val().length > 0)
		 constituencyId = $("#cadreParticipatedConstituencyId").val();
	 	if($('#cadreParticipatedPConstituencyId').val().length > 0)
		 parliamentId = $('#cadreParticipatedPConstituencyId').val();
		 boothId =0;
	}
	else 
	{
		if($('#cadreDistrictId').val().length > 0)
		districtId = $("#cadreDistrictId").val();
		if($('#cadrePConstituencyId').val().length > 0)
		parliamentId = $('#cadrePConstituencyId').val();
		 if($('#cadreBoothId').val().length > 0)
		 boothId = $("#cadreBoothId").val();
		if($('#cadreConstituencyId').val().length > 0)
		 constituencyId = $("#cadreConstituencyId").val();
	}
	
	var jsObj={
			districtId:districtId,
			constiId:constituencyId,
			parliamentId:parliamentId,
			boothId:boothId,
			task:""
	}
	$('#ivrsurveyDataLoadoing').show();
	$.ajax({
		type:'GET',
		url :'getIVRSurveysOnCandidateAreaDetailsAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		$('#ivrsurveyDataLoadoing').hide();
		buildIVRSurveysOnCandidateAreaDetails(result);
	});
}
function buildIVRSurveysOnCandidateAreaDetails(resultList)
{
var result = resultList.verifierVOList;
		var str = '';
	if(result != null && result.length >0){
	str+='<div class="panel-group" style="margin-top:20px" id="accordion121" role="tablist" aria-multiselectable="true">';
			str+='<div class="table-responsive">';
			str+='<table id="ivrStatusDataTable">';
			str+='<thead><th style="text-align:right;">sort by date<i style="background: rgb(221, 221, 221) none repeat scroll 0% 0%; border-radius: 50%; padding: 9px; float: right;" class="glyphicon glyphicon-sort-by-attributes"></i></th></thead>';
			for(var i in result){
			str+='<tr>';
			str+='<td>';
			 str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="headingOne'+i+'">';
						str+='<a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion121" href="#collapseOne'+i+'" aria-expanded="true" aria-controls="collapseOne'+i+'">';
							str+='<h4 class="panel-title">';
								str+='<span class="resWidth">'+result[i].date+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="resWidth">'+result[i].name+'</span>';
							str+='</h4>';
						str+='</a>';
					str+='</div>';
					
					str+='<div id="collapseOne'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+i+'">';
						str+='<div class="panel-body">';
						str+='<div class="table-responsive">';
				

						
							for(var k in result[i].verifierVOList)
							{

								str+='<h4>'+result[i].verifierVOList[k].name+'</h4>';
								
								str+='<table class="table table-bordered">';

								str+='<thead>';
								str+='<th style="text-align:center;background-color:lightgrey;width:60px;">';
									str+=' Location ';
								str+='</th>';

								for(var  l in result[i].verifierVOList[k].verifierVOList[0].verifierVOList)
								{	
									str+='<th style="background-color:lightgrey;">';
									str+=''+result[i].verifierVOList[k].verifierVOList[0].verifierVOList[l].name+'';
									str+='</th>';
								}
								str+='</thead>';
							   str+='<tbody>';
							for(var l in result[i].verifierVOList[k].verifierVOList){
										str+='<tr>';
											str+='<td>'+result[i].verifierVOList[k].verifierVOList[l].name+'</td>';
									for(var m in result[i].verifierVOList[k].verifierVOList[l].verifierVOList){
											str+='<td>'+result[i].verifierVOList[k].verifierVOList[l].verifierVOList[m].percentage+'</td>';
											
									}
										str+='</tr>';
							}
								str+='</tbody>';
								str+='</table>';
								
							}
							str+='</div>';
						str+='</div>';
					 str+='</div>';
					str+='</div>';
					str+='</tr>';
					str+='</td>';
			}
			str+='</table>';
			str+='</div>';
			str+='</div>';
			$('.ivrSurveyCandtDetailsCls').html(str);
			
			$("#ivrStatusDataTable").DataTable( {
				   responsive: true,
			"paging":   false,
				   "info":     false,
			"searching": false,
			"sDom": '<"top"fl>rt<"bottom"i><"clear"p>',
			"columnDefs": [
			   { "width": "25%", "targets": 0 }]
			   } );
			$("#ivrStatusDataTable").removeClass("dataTable")
			$("#ivrStatusDataTable").css("clear","both")
	}else{
		
		$('.ivrSurveyCandtDetailsCls').html("NO DATA AVAILABLE");
	}
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
	var jsObj={
			candidateId:globalCandidateId,
			task:""
	}
	$('#ivrsurveyDataLoadoing').show();
	$.ajax({
		type:'GET',
		url :'getSurveysOnCandidateDetailsAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		$('#ivrsurveyDataLoadoing').hide();
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
						str+='<div class="table-responsive">';
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
			
	}else{
		
		$('.ivrSurveyCandtDetailsCls').html("NO DATA AVAILABLE");
	}
}
/*
Long panchayatId = jObj.getLong("panchayatId");
			Long mandalId = jObj.getLong("mandalId");
			Long lebId = jObj.getLong("lebId");
			Long assemblyId = jObj.getLong("assemblyId");
			Long districtId = jObj.getLong("districtId");
			Long stateId = jObj.getLong("stateId");
			Long participatedAssemblyId = jObj.getLong("participatedAssemblyId");
			
			
			activityVO = activityService.getCanditeActivtyAttendanceLocationsDtls(cadreId,activityLevelId,panchayatId,mandalId,lebId,assemblyId,districtId,stateId,participatedAssemblyId);
		
		*/
//,Long panchayatId,Long mandalId,Long lebId,Long  assemblyId,Long districtId,Long stateId,Long participatedAssemblyId
function getCanditeActivtyAttendanceLocationsDtls(activityLevelId){
	//$("#IvrcandiParticipatedId").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		//alert(999);
		$("#activityAttented").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
	var jsObj={
		tdpCadreId:globalCadreId,
		activityLevelId:activityLevelId,
		panchayatId: $('#cadrePanchaytId').val(),
		mandalId:$('#cadremandalId').val(),
		lebId:$('#cadreRuralORUrbanId').val(),
		assemblyId:$('#cadreConstituencyId').val(),
		districtId :$('#cadreDistrictId').val(),
		stateId :$('#cadreStateId').val(),
		participatedAssemblyId:0
	}
	
	$.ajax({
			type:'POST',
			 url: 'getCanditeActivtyAttendanceLocationsDtlsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#activityAttented").html('');
				if(result!= null)
					buildLocationsDtlsofCandateActivityAttendance(result);
				else
					$("#activityAttented").html(' Candidate Not Available with any Activity.');
				
				//console.log(result);
			});
}

function buildLocationsDtlsofCandateActivityAttendance(results){
	
	var str = '';
	var result = results.activityVoList;
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered">';
	str+='<thead style="background:#ccc">';
	str+='<th> Activity level </th>';
	str+='<th> Activity Name </th>';
	str+='<th> Attended Location </th>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
	str+='<tr>';
	str+='<td>'+result[i].optionType+'</td>';
	str+='<td>'+result[i].name+'</td>';
	str+='<td>'+result[i].attendendLocation+' ('+result[i].conductedDate+')</td>';
	str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#activityAttented").html(str);
}
function getCandateActivityAttendance(activityLevelId){
	//$("#IvrcandiParticipatedId").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		//alert(999);
		$("#activityAttented").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
	var jsObj={
		cadreId:globalCadreId,
		activityLevelId:activityLevelId	,
		panchayatId: $('#cadrePanchaytId').val(),
		mandalId:$('#cadremandalId').val(),
		lebId:$('#cadreRuralORUrbanId').val(),
		assemblyId:$('#cadreConstituencyId').val(),
		districtId :$('#cadreDistrictId').val(),
		stateId :$('#cadreStateId').val(),
		participatedAssemblyId:0
	}
	
	$.ajax({
			type:'POST',
			 url: 'getCandateActivityAttendanceAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#activityAttented").html('');
				if(result!= null && result.length >0)
					buildCandateActivityAttendance(result);
				else
					$("#activityAttented").html(' Candidate Not Available with any Activity.');
				
				//console.log(result);
			});
}
//getCandateActivityAttendance();
function buildCandateActivityAttendance(result){
	var str = '';
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered">';
	str+='<thead style="background:#ccc">';
	str+='<th>Activity level</th>';
	str+='<th>Activity Name</th>';
	//str+='<th>Status</th>';
	str+='<th>Invited</th>';
	str+='<th>Attended</th>';
	str+='<th>Not Attended </th>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
	str+='<tr>';
	str+='<td>'+result[i].isLocation+'</td>';
	str+='<td>'+result[i].attendendLocation+'</td>';
	//if(result[i].invitteeCnt != null && result[i].invitteeCnt >0 && result[i].attendedCount != null && result[i].attendedCount>0)
	//	str+='<td> Conducted </td>';
	//else if(result[i].conductedDate != null && result[i].conductedDate =='Conducted')
	//	str+='<td> Conducted </td>';
	//if(result[i].conductedDate != null && result[i].conductedDate.length >0 )
	//	str+='<td> Conducted </td>';	
	//else
	//	str+='<td> - </td>';
	if(result[i].invitteeCnt == 0){
		str+='<td style="text-align:center;">-</td>';
	}else{
		str+='<td style="text-align:center;">'+result[i].invitteeCnt+'</td>';
	}
	if(result[i].attendedCount == 0){
	str+='<td style="text-align:center;">-</td>';
	}else{
		str+='<td style="text-align:center;">'+result[i].attendedCount+'</td>';
	}
	if(result[i].abscentCnt == 0){
	str+='<td style="text-align:center;">-</td>';
	}else{
		str+='<td style="text-align:center;">'+result[i].abscentCnt+'</td>';
	}
	
	str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#activityAttented").html(str);
}