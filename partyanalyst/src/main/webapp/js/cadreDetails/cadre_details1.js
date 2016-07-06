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
function getCanditeActivtyAttendanceLocationsDtls(activityLevelId,activityScopeId,statusCode){
	//$("#IvrcandiParticipatedId").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		
		$("#activityOverViewAttented").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
	var jsObj={
		tdpCadreId:globalCadreId,
		activityLevelId:activityLevelId,
		activityScopeId:activityScopeId,
		statusCode:statusCode,
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
				$("#activityOverViewAttented").html('');
				if(result!= null)
					buildLocationsDtlsofCandateActivityAttendance(result);
				else
					$("#activityOverViewAttented").html(' Candidate Not Available with any Activity.');
				
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
	str+='<th> Stataus  </th>';
	str+='</thead>';
	str+='<tbody>';
	for(var i in result){
	str+='<tr>';
	str+='<td>'+result[i].optionType+'</td>';
	str+='<td>'+result[i].name+'</td>';
	str+='<td>'+result[i].attendendLocation+' ('+result[i].conductedDate+')</td>';
	str+='<td> Attended </td>';
	str+='</tr>';
	}
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	$("#activityOverViewAttented").html(str);
}
function getCadreLocationWiseEventAttendeeCounts(locationId,locationValue,searchType,divId,index,id){
	$("#errMsgID").hide();
	$('#'+divId+'').css("overflow","scroll");
	$('#'+divId+'tr').show();
	$('#'+divId+'').show();
	$('#'+divId+'').html('<center><img id="" src="images/icons/loading.gif" style=""/></center>');

if(index != "onload"){
	var isVisible1 = "";
	 isVisible1 = $('#'+id).find(".glyphicon-plus").is( ":visible" );
	if(isVisible1==true ){
		$('#'+id).find(".glyphicon-plus").removeClass("glyphicon-plus").addClass("glyphicon-minus");
		$('#'+divId+index).html('<center><img id="" src="images/icons/loading.gif" style=""/></center>');
		$('#'+id).parent().next().show();
		$('#'+divId+index).show();
		
	}else if(isVisible1==false ){
		$('#'+id).find(".glyphicon-minus").removeClass("glyphicon-minus").addClass("glyphicon-plus");
		$('#'+id).parent().next().hide();
		$('#'+divId+index).hide();
		return;
	}
}
	var jsObj={
		eventId:$("#eventsId").val(),
		startdate : "",
		endDate : "",
		locationId:locationId,
		locationValue : locationValue,
		searchType : searchType
	}
	$.ajax({
			type:'POST',
			 url: 'getCadreLocationWiseEventAttendeeCountsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$('#'+divId+'').html('');
				if(result != null && result.length >0){
				if(searchType == "DISTRICT")
				buildCadreLocationWiseEventAttendeeCount(result,"DISTRICT","CONSTITUENCY",divId,index)
				if(searchType == "CONSTITUENCY")
				buildCadreLocationWiseEventAttendeeCount(result,"CONSTITUENCY","MANDAL",divId,index)
				if(searchType == "MANDAL"){
					if($("#cadreRuralORUrbanId").val() != 0 && $("#cadreRuralORUrbanId").val() != "")
						buildCadreLocationWiseEventAttendeeCount(result,"MUNCIPALITY/CORPORATION","WARD",divId,index)
					else if($("#cadremandalId").val() != 0 && $("#cadremandalId").val() != "")
						buildCadreLocationWiseEventAttendeeCount(result,"MANDAL","PANCHAYAT",divId,index)
				}
				if(searchType == "PANCHAYAT"){
				buildCadreLocationWiseEventAttendeeCount(result,"PANCHAYAT","",divId,index)	
				}
				if(searchType == "WARD"){
					buildCadreLocationWiseEventAttendeeCount(result,"WARD","",divId,index)	
				}
				}else{
					$("#errMsgID").show();
					$("#errMsgID").html("NO DATA AVAILABLE");
				}
					
			});
}
function buildCadreLocationWiseEventAttendeeCount(myResult,locationValue,searchType,divId,index){
	var result = [];
	if(index == "onload" && (locationValue == "CONSTITUENCY" || locationValue == "MANDAL" )){
		var locatinId = 0;
		if(locationValue == "CONSTITUENCY"){
			if($("#cadreParticipatedConstituencyId").val() != 0 && $("#cadreParticipatedConstituencyId").val() != ""){
			locatinId = $('#cadreParticipatedConstituencyId').val();
			}else if($("#cadreConstituencyId").val() != 0 && $("#cadreConstituencyId").val() != ""){
				locatinId = $('#cadreConstituencyId').val();
			}
		}else if(locationValue == "MANDAL"){
			if($("#cadreRuralORUrbanId").val() != 0 && $("#cadreRuralORUrbanId").val() != ""){
			locatinId = $('#cadreRuralORUrbanId').val();
			}else if($("#cadremandalId").val() != 0 && $("#cadremandalId").val() != ""){
				locatinId = $('#cadremandalId').val();
			}
		}
		
		for(var i in myResult){
			if(myResult[i].id != null && myResult[i].id == locatinId)
				result.push(myResult[i]);
		}
	}
	else{
		result = myResult;
	}

	for(var i in result){
		
		if(i==0){
			var str ='';
			str+='<table class="table table-bordered">';
			if(locationValue == "DISTRICT")
			str+='<thead style="background-color:#CCCCCC;">';
			if(locationValue == "CONSTITUENCY")
			str+='<thead style="background-color:#ddd;">';
			if(locationValue == "MANDAL" || locationValue == "MUNCIPALITY/CORPORATION")
			str+='<thead style="background-color: #CCCCCC;">';
			if(locationValue == "PANCHAYAT" || locationValue == "WARD")
			str+='<thead style="background-color:#f2f2f2;">';
			str+='<tr>';
			str+='<th rowspan="2">ID</th>';
			if(locationValue == "DISTRICT")
				str+='<th rowspan="2">DISTRICT NAME</th>';
			else if(locationValue == "CONSTITUENCY")
				str+='<th rowspan="2">CONSTITUENCY NAME</th>';
			else if(locationValue == "MUNCIPALITY/CORPORATION" && searchType == "WARD")
				str+='<th rowspan="2">MANDAL NAME</th>';
			else if(locationValue == "MANDAL" && searchType == "PANCHAYAT")
				str+='<th rowspan="2">MANDAL NAME</th>';
			else if(locationValue == "PANCHAYAT" && searchType == "")
				str+='<th rowspan="2">VILLAGE NAME</th>';
			else if(locationValue == "WARD" && searchType == "")
				str+='<th rowspan="2">WARD NAME</th>';
			str+='<th rowspan="2">TOTAL INVITEES</th>';
			str+='<th colspan="2">TOTAL ATTENDED</th>';
			str+='<th colspan="2">INVITEES ATTENDED</th>';
			str+='<th colspan="2">NON INVITEES ATTENDED</th>';
			for(var j in result[0].subList){
							str+='<th class="text-center text-capitalize" colspan="3">'+result[0].subList[j].name+' ATTENDED</th>';
					}
			str+='</tr>';
			str+='<tr>';
				str+='<th>Count</th>';
				str+='<th>%</th>';
				str+='<th>Count</th>';
				str+='<th>%</th>';
				str+='<th>Count</th>';
				str+='<th>%</th>';
			for(var k in result[0].subList){
							str+='<th>Total</th>';
							str+='<th>Invitees</th>';
							str+='<th>Non Invitees</th>';
					}
			str+='</tr>';
			str+='</thead>';
		}
	
	
	str+='<tr>';
	str+='<td>'+result[i].id+'</td>';
	if(result[i].name == null ){
		str+='<td> - </td>';
	}else if(result[i].name != null && locationValue == "DISTRICT"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'constTableDiv\',\''+index+''+i+'\',this.id);" id="showHideDist'+i+'"><span class="showIcon" style="cursor:pointer;"><i class="glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>   '+result[i].name+'</td>';
		
	}else if(result[i].name != null && locationValue == "CONSTITUENCY"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'mandalTableDiv\',\''+index+''+i+'\',this.id);" id="showHideConst'+i+'"><span id="showIcon" style="cursor:pointer;"><i class="glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>  '+result[i].name+'</td>';
		
	}else if(result[i].name != null && locationValue == "MANDAL"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'villageTableDiv\',\''+index+''+i+'\',this.id);" id="showHideMandal'+i+'"><span id="showIcon" style="cursor:pointer;"><i class="glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>'+result[i].name+'</td>';
	}else if(result[i].name != null && locationValue == "MUNCIPALITY/CORPORATION"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'villageTableDiv\',\''+index+''+i+'\',this.id);" id="showHideMuncipal'+i+'"><span id="showIcon" style="cursor:pointer;"><i class="glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>'+result[i].name+'</td>';
	}else if(result[i].name != null && locationValue == "PANCHAYAT"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'villageTableDiv\',\''+index+''+i+'\',this.id);">'+result[i].name+'</td>';
	}else if(result[i].name != null && locationValue == "WARD"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'villageTableDiv\',\''+index+''+i+'\',this.id);">'+result[i].name+'</td>';
	}
	if(result[i].inviteesCalled ==0 || result[i].inviteesCalled == null){
		str+='<td class="text-center"> - </td>';
	}else{
		str+='<td class="text-center">'+result[i].inviteesCalled+'</td>';
	}
	if(result[i].attendees ==0 || result[i].attendees == null){
	    str+='<td class="text-center">-</td>';
	}else{
		str+='<td class="text-center">'+result[i].attendees+'</td>';
	}
	if(result[i].attendeePercantage ==0 || result[i].attendeePercantage == null){
		str+='<td class="text-center">-</td>';
	}else{
		str+='<td class="text-center">'+result[i].attendeePercantage+'</td>';
	}
	if(result[i].invitees ==0 || result[i].invitees == null){
		str+='<td class="text-center"> - </td>';
	}else{
		str+='<td class="text-center">'+result[i].invitees+'</td>';
	}
	if(result[i].inviteePercantage ==0 || result[i].inviteePercantage == null){
		str+='<td class="text-center"> - </td>';
	}else{
		str+='<td class="text-center">'+result[i].inviteePercantage+'</td>';
	}
	if(result[i].nonInvitees ==0 || result[i].nonInvitees == null){
		str+='<td class="text-center"> - </td>';
	}else{
		str+='<td class="text-center">'+result[i].nonInvitees+'</td>';
	}
	 if(result[i].nonInviteePercantage ==0 || result[i].nonInviteePercantage == null){
		str+='<td class="text-center"> - </td>';
	}else{
		str+='<td class="text-center">'+result[i].nonInviteePercantage+'</td>';
	} 
	for(var l in result[i].subList){
				
					if(parseInt(result[i].subList[l].attendees) == 0 || result[i].subList[l].attendees == null){
						
						str+='<td class="text-center"> - </td>';
					}else{
						str+='<td class="text-center">'+result[i].subList[l].attendees+'</td>';
					}
					if(parseInt(result[i].subList[l].invitees) == 0 || result[i].subList[l].invitees == null){
						str+='<td class="text-center"> - </td>';
					}else{
						 str+='<td class="text-center">'+result[i].subList[l].invitees+'</td>';
					}
		 	       if(parseInt(result[i].subList[l].nonInvitees) == 0 || result[i].subList[l].nonInvitees == null){
						str+='<td class="text-center"> - </td>';
					}else{
						str+='<td class="text-center">'+result[i].subList[l].nonInvitees+'</td>';
					}
			}
			if(locationValue == "CONSTITUENCY" || searchType == "MANDAL"){
				str+='<tr style="display:none" class="mandaltrCls'+index+'" id="mandalTableDivtr'+index+''+i+'">';
				str+='<td colspan="18">';
				str+='<div class="mandalCls'+index+'" id="mandalTableDiv'+index+''+i+'" ></div>';
				str+='</td>';
				str+='</tr>';
			}
			if(locationValue == "MUNCIPALITY/CORPORATION" || locationValue == "MANDAL" || searchType == "WARD" || searchType == "PANCHAYAT" ){
				str+='<tr style="display:none" class="villagetrCls'+index+'" id="villageTableDivtr'+index+''+i+'">';
				str+='<td colspan="18">';
				str+='<div class="villageCls'+index+'" id="villageTableDiv'+index+''+i+'" ></div>';
				str+='</td>';
				str+='</tr>';
			}
			str+='</tr>';
			
			str+='<tr style="display:none" class="constituencyCls'+index+'" id="constTableDivtr'+index+''+i+'">';
	str+='<td colspan="18">';
		str+='<div id="constTableDiv'+index+''+i+'" class="constituencyCls'+index+'" ></div>';
	str+='</td>';
	str+='</tr>';
	
	
		if(i==0){
			if(locationValue == "DISTRICT"){
				
				$("#"+divId+"").html(str);
			}
		}
		if(i == result.length-1){
			
		str+='</table>';
	
			if( index != "onload" && locationValue == "CONSTITUENCY"){
				$("#constTableDiv"+index+"").css("overflow","scroll");
				$("#constTableDivtr"+index+"").show();
				$("#constTableDiv"+index+"").html(str);
			}
			else if(locationValue == "CONSTITUENCY"){
					$("#"+divId+"").html(str);
			} 
			else if( index != "onload" && locationValue == "MANDAL" || locationValue == "MUNCIPALITY/CORPORATION"){
				$("#mandalTableDiv"+index+"").css("overflow","scroll");
				$("#mandalTableDivtr"+index+"").show();
				$("#constTableDivtr"+index+"").show();
				$("#mandalTableDiv"+index+"").html(str);
			} else if( locationValue == "MANDAL"){
				$("#"+divId+"").html(str);
			}
			if(searchType == "WARD" || searchType == "PANCHAYAT" && locationValue != "MANDAL" || locationValue == "PANCHAYAT"){
				$("#villageTableDiv"+index+"").css("overflow","scroll");
				$("#villageTableDivtr"+index+"").show();
				$("#villageTableDiv"+index+"").html(str);	
				}
		}
	}
}
$(document).on("click",".locWiseEvnAtnCls",function(){
	$(".locWiseEvnAtnBody").collapse('toggle');
})
$(".locWiseEvnAtnBody").collapse('hide');
/* function getMainEvents()
{
	var jsObj={}
	$.ajax({
		type:'GET',
		url :'getMainEventsAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null && result.length >0){
		for(var i in result){
			$("#eventsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	}
	}
	});
} */
/* var eventStartDate = '';
var eventEndDate = '';
function getStartDateAndEndDate()
{
	 eventStartDate = '';
    eventEndDate = '';
	var jsObj={eventId : $("#eventsId").val()}
	$.ajax({
		type:'GET',
		url :'getStartDateAndEndDateAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		if(result != null){
		eventStartDate = result.description;
		eventEndDate	= result.district;	
		}
	});
	$("#dateEvntBody").daterangepicker({
		minDate: eventStartDate,
		maxDate: eventEndDate,
		format: 'DD/MM/YYYY'
	});
} */
//getMainEvents();
