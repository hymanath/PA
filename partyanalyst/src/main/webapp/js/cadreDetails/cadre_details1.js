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
				"aaSorting": [[ 0, 'desc' ]],
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
		$('#'+id).parent().next().next().hide();
		$('#'+id).parent().parent().next().hide();
		$('#'+divId+'tr'+index).hide();
		$('#'+divId+index).hide();
		return;
	}
}else{
	$('#'+divId+'').addClass("table-responsive");
	$('#'+divId+'tr').show();
	$('#'+divId+'').show();
	$('#'+divId+'').html('<center><img id="" src="images/icons/loading.gif" style=""/></center>');

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
						buildCadreLocationWiseEventAttendeeCount(result,"MANDAL","PANCHAYAT",divId,index)
				}
				if(searchType == "PANCHAYAT"){
				buildCadreLocationWiseEventAttendeeCount(result,"PANCHAYAT","",divId,index)	
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
			
			//str+='<div class="col-md-12 m_top10"><button class="btn btn-xs btn-success pull-right" style="margin-bottom:10px" onclick="generatesExcel(\''+divId+'\',\''+locationValue+' Wise Report\');">EXPORT TO EXCEL</button></h4></div>';
			if(index == "onload")
				str+='<table class="table table-bordered table-condensed" id="'+divId+'">';
			else
				str+='<table class="table table-bordered table-condensed" id="'+divId+''+index+'">';
			if(locationValue == "DISTRICT")
			str+='<thead style="background-color:#CCC;font-size:12px !important">';
			if(locationValue == "CONSTITUENCY")
			str+='<thead style="background-color:#EEE;font-size:12px !important">';
			if(locationValue == "MANDAL")
			str+='<thead style="background-color: #f1f1f1;font-size:12px !important">';
			if(locationValue == "PANCHAYAT")
			str+='<thead style="background-color:#f2f2f2;font-size:12px !important">';
			str+='<tr>';
			if(result[i].locationName == "constituency"){
				str+='<th rowspan="2">CONSTITUENCY NO</th>';
		    }else{
				str+='<th rowspan="2">ID</th>';
			}
			if(locationValue == "DISTRICT")
				str+='<th rowspan="2">DISTRICT</th>';
			else if(locationValue == "CONSTITUENCY")
				str+='<th rowspan="2">CONSTITUENCY</th>';
			else if(locationValue == "MANDAL" && searchType == "PANCHAYAT")
				str+='<th rowspan="2">MANDAL/MUNCIPALITY</th>';
			else if(locationValue == "PANCHAYAT" && searchType == "")
				str+='<th rowspan="2">VILLAGE</th>';
			str+='<th rowspan="2">TOTAL INVITEES</th>';
			str+='<th rowspan="2">TOTAL ATTENDED</th>';
			str+='<th colspan="2">INVITEES ATTENDED</th>';
			str+='<th colspan="2">NON INVITEES ATTENDED</th>';
			for(var j in result[0].subList){
							str+='<th class="text-center text-capitalize" colspan="3">'+result[0].subList[j].name+' ATTENDED</th>';
					}
			str+='</tr>';
			str+='<tr>';
				//str+='<th>Count</th>';
				//str+='<th>%</th>';
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
	
	if(result[i].inviteesCalled >0  || result[i].attendees >0 ){
	//str+='<tr style="font-size:12px !important">';
	if(result[i].locationName == "constituency"){
		str+='<td>'+result[i].locationId+'</td>';
	}else{
	str+='<td>'+result[i].id+'</td>';
	}
	if(result[i].name == null ){
		str+='<td> - </td>';
	}else if(result[i].name != null && locationValue == "DISTRICT"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'constTableDiv\',\''+index+''+i+'\',this.id);" id="showHideDist'+i+'"><span class="showIcon" style="cursor:pointer;font-size:12px;"><i class="glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>   '+result[i].name+'</td>';
		
	}else if(result[i].name != null && locationValue == "CONSTITUENCY"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'mandalTableDiv\',\''+index+''+i+'\',this.id);" id="showHideConst'+i+'"><span id="showIcon" style="cursor:pointer;font-size:12px;"><i class="glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>  '+result[i].name+'</td>';
		
	}else if(result[i].name != null && locationValue == "MANDAL" && result[i].locationName == "muncipality"){
		str+='<td >'+result[i].name+' MUNCIPALITY</td>';
	}else if(result[i].name != null && locationValue == "MANDAL" && result[i].locationName == "mandal"){
		str+='<td style="cursor:pointer;" onclick="getCadreLocationWiseEventAttendeeCounts(\''+result[i].id+'\',\''+locationValue+'\',\''+searchType+'\',\'villageTableDiv\',\''+index+''+i+'\',this.id);" id="showHideMandal'+i+'"><span id="showIcon" style="cursor:pointer;font-size:12px;"><i class="glyphicon glyphicon-plus" style="cursor:pointer;"></i></span>'+result[i].name+'</td>';
	}else if(result[i].name != null && locationValue == "PANCHAYAT"){
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
	/* if(result[i].attendeePercantage ==0 || result[i].attendeePercantage == null){
		str+='<td class="text-center">-</td>';
	}else{
		str+='<td class="text-center">'+result[i].attendeePercantage+'</td>';
	} */
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
				str+='<tr style="display:none;font-size:12px !important" class="mandaltrCls'+index+'" id="mandalTableDivtr'+index+''+i+'">';
				str+='<td colspan="18">';
				str+='<div class="mandalCls'+index+'" id="mandalTableDiv'+index+''+i+'" ></div>';
				str+='</td>';
				str+='</tr>';
			}
			if(locationValue == "MANDAL" || searchType == "PANCHAYAT" ){
				str+='<tr style="display:none;font-size:12px !important" class="villagetrCls'+index+'" id="villageTableDivtr'+index+''+i+'">';
				str+='<td colspan="18">';
				str+='<div class="villageCls'+index+'" id="villageTableDiv'+index+''+i+'" ></div>';
				str+='</td>';
				str+='</tr>';
			}
			str+='</tr>';
			
			str+='<tr style="display:none;font-size:12px !important" class="constituencyCls'+index+'" id="constTableDivtr'+index+''+i+'">';
				str+='<td colspan="18">';
					str+='<div id="constTableDiv'+index+''+i+'" class="constituencyCls'+index+'" ></div>';
				str+='</td>';
			str+='</tr>';
	}
	
		if(i==0){
			if(locationValue == "DISTRICT"){
				$("#"+divId+"").html(str);
			}
		}
		if(i == result.length-1){
			
		str+='</table>';
	
			if( index != "onload" && locationValue == "CONSTITUENCY"){
				//$("#constTableDiv"+index+"").css("overflow","scroll");
				$("#constTableDiv"+index+"").addClass("table-responsive");
				$("#constTableDivtr"+index+"").show();
				$("#constTableDiv"+index+"").html(str);
			}
			else if(locationValue == "CONSTITUENCY"){
					$("#"+divId+"").html(str);
			} 
			else if( index != "onload" && locationValue == "MANDAL"){
				//$("#mandalTableDiv"+index+"").css("overflow","scroll");
				$("#mandalTableDiv"+index+"").addClass("table-responsive");
				$("#mandalTableDivtr"+index+"").show();
				$("#constTableDivtr"+index+"").show();
				$("#mandalTableDiv"+index+"").html(str);
			} else if( locationValue == "MANDAL"){
				$("#"+divId+"").html(str);
			}
			if( searchType == "PANCHAYAT" && locationValue != "MANDAL" || locationValue == "PANCHAYAT"){
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
function generatesExcel(id,excelName){
	tableToExcel(id, excelName);
}

function getUpdatedCastePartyInfo(){
	$("#localCastStatsTabContent_header").html("");
    $("#localCastStatsTabContent_body").html("");
	$("#localCastDetailsDiv").html("");
	$("#partyBasicInfoStatsTab").html("");
	$("#partyWiselocalcastDiv").html("");
	$("#localCastChatDiv").html("");	
	$("#localCastDetailsDivId").html("");
	var constituencyId=0;
	if($('#cadreParticipatedDistrictId').val().length > 0)
	 {
		 	if($('#cadreParticipatedConstituencyId').val().length > 0)
		 constituencyId = $("#cadreParticipatedConstituencyId").val();
	 }
	 else 
	{
		if($('#cadreConstituencyId').val().length > 0)
		 constituencyId = $("#cadreConstituencyId").val();
	}
  var castewiseAjaxDiv =  document.getElementById('castewiseAjaxDiv');
		showAjaxImgDiv('castewiseAjaxDiv');
		var jsObj=
			{
				type:"constituency",	
				id:constituencyId,
				typename:$("#participatedConstName").val(),
				publicationDateId:22,
				constituencyId:constituencyId,
				queryType:"sub",
				task:"getCastInfo"
			}
			$.ajax({
			type:'POST',
			 url: 'getvotersCastInfoByConstituency.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(results){
					 $("#casteDiv").show();
					buildCastInfoData(results,jsObj);
					buildCastPiechart(results,jsObj);
			});
}
function getUpdatedCasteLocationWiseInfoForMandalAndVillage(type){
	$("#localCastStatsTabContent_header").html("");
    $("#localCastStatsTabContent_body").html("");
	$("#localCastDetailsDiv").html("");
	$("#partyBasicInfoStatsTab").html("");
	$("#partyWiselocalcastDiv").html("");
	$("#localCastChatDiv").html("");
	$("#localCastDetailsDivId").html("");
	var constituencyId=0;
	if($('#cadreParticipatedDistrictId').val().length > 0)
	 {
		 	if($('#cadreParticipatedConstituencyId').val().length > 0)
		 constituencyId = $("#cadreParticipatedConstituencyId").val();
	 }
	 else 
	{
		if($('#cadreConstituencyId').val().length > 0)
		 constituencyId = $("#cadreConstituencyId").val();
	}
	var locationId = 0;
		if(type == "panchayat"){
			//locationId = $('#cadrePanchaytId').val();;
			locationId = globalPanchayatId;
		}else if(type == "mandal"){
			locationId = "2"+globalTehsilId;
		}
		var locationName = 0;
		if(type == "panchayat"){
			locationName = $("#panchayatId").html();
		}else if(type == "mandal"){
			locationName = $("#mandalId").html();
		}
  var castewiseAjaxDiv =  document.getElementById('castewiseAjaxDiv');
		showAjaxImgDiv('castewiseAjaxDiv');
		var jsObj=
			{
				type:type,	
				id:locationId,
				typename:locationName,
				publicationDateId:22,
				constituencyId:constituencyId,
				queryType:"sub",
				task:"getCastInfo"
			}
			$.ajax({
			type:'POST',
			 url: 'getvotersCastInfoByConstituency.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(results){
					 $("#casteDiv").show();
						buildCastInfoData(results,jsObj);
						buildCastPiechart(results,jsObj);
			});
}
function buildCastInfoData(results,jsObj)
{ 
   var constituencyId = $('#cadreConstituencyId').val();
	// if( constituencyId == jsObj.id)
  //{
	$('#localCastDetailsHeadingDiv').html('');
	$('.localCastStatsVotersTitle').html('');
	
	var result = results.voterCastInfodetails;
	var castewiseAjaxDiv = document.getElementById('castewiseAjaxDiv');
	hideAjaxImgDiv('castewiseAjaxDiv');
	//var localCastStatsTabContent_headerEl = document.getElementById("localCastStatsTabContent_header");
	var totalVoters = result.totalVoters;
	var totalCasts = result.totalCasts;
	var totalMale = result.maleVoters;
	var totalFemale = result.femaleVoters;
	var voters = '';
	if(totalVoters == 0){
		$('#votersMainOuterDiv2').hide();
		$('#casteDiv').hide();
		return false;
	}
	$('#casteDiv').show();
	if(result.maleVoters > 0){
		
       if("constituency" == jsObj.type)
        $("#castPartyPopupShowBtn").hide();
	   else
		   $("#castPartyPopupShowBtn").show();
		   $("#partywiseCasteTitle").html("Party Wise Caste Statistics In "+jsObj.typename+" ");
		$("#partyBasicInfoStatsTabNewTitle").html("<div id='partyDiv'><input type='button' value='Graphical Representation Of Party Wise Voters Details' class='btn btn-info pull-right' onClick='showPartyWiseDetailsInGraph()' id='partyGraphButtonId'/></div>");
	    $("#LocalCastDiv").css('padding-bottom','20px');
	}
	else{
	   		$("#partyBasicInfoStatsTabNewTitle").html("").css("background","#ffffff");
	}
	var localCastStatsTabContent = '<div style="font-family:verdana;font-size:13px;margin-left:2px;font-weight:bold;"><span class="" style="padding:10px;"> Total Voters : '+totalVoters+'</span>';
	var localCastStatsTabContent = '<li style="padding:8px"> Total Voters : '+totalVoters+'</li>';
	localCastStatsTabContent += '<li style="padding:8px">Total Castes : '+totalCasts+'</li>';
	//localCastStatsTabContent += 'Total Male Voters : '+totalMale+'&nbsp;&nbsp;&nbsp;';
	//localCastStatsTabContent += 'Total Female Voters : '+totalFemale+'<br><br>';
	localCastStatsTabContent += '<li style="padding:8px">Caste Assigned Voters : '+result.maleVoters+'</li>';
	//localCastStatsTabContent += '<span class="">Caste Not Assigned Voters : '+result.femaleVoters+'</span>';

	if(result.voterCastInfoVOList != null && result.voterCastInfoVOList.length > 0)
	  $('#localCastDetailsHeadingDiv').html('<h2 id="subHeading">Caste Category wise voters details</h2>');
	else
     	$('#localCastDetailsHeadingDiv').html('').css("background","#ffffff");
	
	voters +='<ul class="casteWiseUl">';
	  var reqx = 0;
	  for(var i=0;i<result.castCategoryWiseVotersList.length;i++){
	    reqx = 1;
	    if(i != 0 && i % 5 == 0)
		 voters +='';
		voters +='<li style="padding-left:14px;margin:2px;clear:both;display:block;padding:5px;">'+result.castCategoryWiseVotersList[i].name+' Voters : '+result.castCategoryWiseVotersList[i].id+'</li>';
	     if(i != 0 && i%5 == 0)
		 voters +='';
	 }
		 if(reqx % 5 != 0)
		    voters+='';
		
    voters +='</ul>';
	
	localCastStatsTabContent += '</div>';
	 
	$('#localCastDetailsDiv').html(voters);
//	localCastStatsTabContent_headerEl.innerHTML = localCastStatsTabContent;
	var typeName = jsObj.typename;	
	var publicationDateId=jsObj.publicationDateId;
	var boothId=jsObj.id;
	var type =jsObj.type;
	var	castIno = new Array();
	var castStatsArray = new Array();
	
	var cast = result.voterCastInfoVOList;
		for(var i in cast)
		{
		var castStats = 
			{
			caste : cast[i].castName,
			casteCategory:cast[i].casteCategoryName,
			castePopulation : cast[i].totalVoters,
			malePopulation : cast[i].maleVoters,
			femalePopulation : cast[i].femaleVoters,
			castePercentage:cast[i].votesPercent,
			casteStateId : cast[i].casteStateId,
			
			};
		castIno.push(castStats);
		castStatsArray = castIno; 
		}
		if(cast != '')
	{
		buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId,type,castStatsArray);	
		$('#castContainerChart').css('display','block');
	}
		
		else{
		$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
		$('.localCastStatsVotersTitle').css("backgrond","#FFF;");
		$('#castContainerChart').css('display','none');
		}
}
function buildCastPiechart(results,jsObj)
{
	$('#localCastChatDiv').html('');
	var results = results.voterCastInfodetails;
	if(results.castCategoryWiseVotersList.length == 0)
	{
		$("#localCastDetailsHeadingDiv").html('').css("background","#FFF");
		$(".castDetailsMainDiv").css("border","none")
		return;
	}
	//$(".castDetailsMainDiv").css("border","1px solid #CCC")
	var data = new google.visualization.DataTable();
	data.addColumn('string','cast');
	data.addColumn('number','percentage');
	data.addRows(results.castCategoryWiseVotersList.length);

	for(var j=0; j<results.castCategoryWiseVotersList.length; j++)
	{
		data.setValue(j,0,results.castCategoryWiseVotersList[j].name);
		data.setValue(j,1,results.castCategoryWiseVotersList[j].id);
	} 

	var chart = new google.visualization.PieChart(document.getElementById('localCastChatDiv'));
		chart.draw(data, {width: 400, height: 200,legend:'right',legendTextStyle:{fontSize:12}, title:'Caste category wise voters details chart',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:13}
	});
}

//The Variable are for Caste Chart -- Created By Teja
    var castesArr=[];
	var totalVotersArr=[];
	var maleVotersArr=[];
	var femaleVotersArr=[];
	var castPercentageArr=[];
	var castArray=[];
	77
function buildLocalCastStatisticsDataTableForBooth(typeName,publicationDateId,boothId,type,castStatsArray)
	{
	 if(castStatsArray == null || castStatsArray.length == 0)
	    return;
	$("#localCastStatsTabContentTitle").html("Local Caste Statistics in "+typeName+" ");
	 castArray = castStatsArray;
	$("#localCastStatsVotersTitle").removeClass("localCastStatsVotersTitle");
         var str =' <table id="localCastStatsJqTable" class="table table-bordered" cellpadding="0" cellspacing="0" border="0" width="100%">';
          str+='  <thead style="background-color:#CCC">';
          str+='   <tr>';
          str+='     <th>Caste</th>';
		  str+='	 <th>Caste Category</th>';
		  str+='     <th>Voters</th>';
          str+='     <th>Male Voters</th>';
          str+='     <th>Female Voters</th>';
          str+='	 <th>Caste Percentage</th>';
          str+='   </tr>';
          str+='  </thead>';
          str+='  <tbody>';
	   
	$.each(castArray, function( key, value ) {
		if(value.castePercentage>=1){
			castesArr.push(value.caste);
			totalVotersArr.push(value.castePopulation);
			maleVotersArr.push(value.malePopulation);
			femaleVotersArr.push(value.femalePopulation);
			castPercentageArr.push(parseFloat(value.castePercentage));
		}
	});
	
	
	/*buildCasteVotersChart(castesArr,totalVotersArr,maleVotersArr,femaleVotersArr,castPercentageArr); */
	   
	   
	   for(var i in castArray){
	      str +='   <tr>';
		  //str +='		<td>'+castArray[i].caste+'</td>';
		  if(type =='booth')
		   {	  
		  str+='<td>'+castArray[i].caste+'</td>';
		   }
		   else if(type =='panchayat')
		   {
			   str+='<td>'+castArray[i].caste+'</td>';
		   }else if(type =='hamlet')
		   {	  
		  str+='<td>'+castArray[i].caste+'</td>';
		   }
		   else
		  str +='		<td>'+castArray[i].caste+'</td>';
		  str+='        <td>'+castArray[i].casteCategory+'</td>';
          str +='		<td>'+castArray[i].castePopulation+'</td>';
          str +='		<td>'+castArray[i].malePopulation+'</td>';
		  str +='		<td>'+castArray[i].femalePopulation+'</td>';
		  str +='		<td>'+castArray[i].castePercentage+'</td>';
          str+='   </tr>';
	   }
          str+='  </tbody>';
          str+=' </table>';
	  
	  $("#localCastStatsTabContent_body").html(str);
	  $("#localCastStatsTabContent_body").css("margin-bottom","35px").css("margin-top","10px");
	  	$('#localCastStatsJqTable').dataTable({
		"aaSorting": [[ 2, "desc" ]],
		"iDisplayLength": 10,
		"aLengthMenu": [[10, 30, 90, -1], [10, 30, 90, "All"]],
		//"bFilter": false,"bInfo": false
		  "aoColumns": [null,null,null,null,null,null
		] 
		});
		$('#localCastStatsJqTable').removeClass("dataTable");
	buildGraphBySlide();
}
function buildGraphBySlide(){
	castesArr=[];
	totalVotersArr=[];
	maleVotersArr=[];
	femaleVotersArr=[];
	castPercentageArr=[];
	
	$.each(castArray, function( key, value ) {
		if(value.castePercentage>=casteRange){
			castesArr.push(value.caste);
			totalVotersArr.push(value.castePopulation);
			maleVotersArr.push(value.malePopulation);
			femaleVotersArr.push(value.femalePopulation);
			castPercentageArr.push(parseFloat(value.castePercentage));
		}
	});
buildCasteVotersChart(castesArr,totalVotersArr,maleVotersArr,femaleVotersArr,castPercentageArr);
}
function buildCasteVotersChart(cs,tv,mv,fv,cp)
 {
 
 
   $('#container').highcharts({
            chart: {
                zoomType: 'xy',
				marginRight: 130,
                marginBottom: 100,
				width:890,height:390
            },
			
            title: {
                text: 'Caste Wise Analysis'
            },
            
            xAxis: [{
                categories: cs,
				labels: {
                                align:'right',
                                style: {
                                      cursor: 'pointer',
                                      fontSize: '14px',
                                      //fontWeight:'bold'
                                },
                                rotation:300, 
                            } 
            }],
            yAxis: [{ // Primary yAxis
				min: 0,
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#89A54E'
                    }
                },
                title: {
                    text: 'caste Percentage ',
                    style: {
                        color: '#89A54E'
                    }
                },
                opposite: true
    
            }, { // Secondary yAxis
                gridLineWidth: 0,
                title: {
                    text: 'Caste Percentage/Total Voters',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#4572A7'
                    }
                }
    
            }, { // Tertiary yAxis
                gridLineWidth: 0,
                title: {
                    text: 'Total Voters',
                    style: {
                        color: '#AA4643'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'';
                    },
                    style: {
                        color: '#AA4643'
                    }
                },
                opposite: true
            }],
            tooltip: {
                shared: true
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 0,
                verticalAlign: 'top',
                y: -10,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [ {
                name: 'Total Voters',
                type: 'spline',
                color: '#AA4643',
                yAxis: 2,
                data: tv,
               /* marker: {
                    enabled: false
                },*/
                //dashStyle: 'shortdot',
                tooltip: {
                    valueSuffix: ''
                }
    
            }, {
                name: 'Caste Percentage',
                color: '#89A54E',
                type: 'spline',
                data: cp,
                tooltip: {
                    valueSuffix: ''
                }
            }]
        });
		$('tspan:last').hide();
    }
	

var casteRange;	
/* $(document).ready(function() {
	var data = new google.visualization.DataTable();
$( "#slider" ).slider({
value:1,
min: 0,
max: 40,
step: 1,
slide: function( event, ui ) {
$( "#amount" ).val( "Perc Of Voters Caste: " + ui.value +" %");
},
change: function( event, ui ) {
$( "#amount" ).val( "Perc Of Voters Caste: " + ui.value +" %");
casteRange=ui.value;
buildGraphBySlide(casteRange);
}
});
casteRange=$( "#amount" ).val( "Perc Of Voters Caste: " + $( "#slider" ).slider( "value" ) +" %");
casteRange=$( "#slider" ).slider( "value" );
}); */
function showAjaxImgDiv(id)
{
	//document.getElementById(id).style.display = 'block';
}	
function hideAjaxImgDiv(id)
{
	//document.getElementById(id).style.display = 'none';
}

function getUpdatedCasteLocationWiseInfoForParliamentAndDistrict(type){
	$("#localCastStatsTabContent_header").html("");
    $("#localCastStatsTabContent_body").html("");
	$("#localCastDetailsDiv").html("");
	$("#partyBasicInfoStatsTab").html("");
	$("#partyWiselocalcastDiv").html("");
	$("#localCastChatDiv").html("");
	$("#localCastDetailsDivId").html("");
	var constituencyId=0;
	if($('#cadreParticipatedDistrictId').val().length > 0)
	 {
		 	if($('#cadreParticipatedConstituencyId').val().length > 0)
		 constituencyId = $("#cadreParticipatedConstituencyId").val();
	 }
	 else 
	{
		if($('#cadreConstituencyId').val().length > 0)
		 constituencyId = $("#cadreConstituencyId").val();
	}
		var locationId = 0;
		if(type == "parliamentConstituency"){
			locationId = tdpCadreParliamentConstituencyId;
		}else if(type == "district"){
			locationId = tdpCadreDistrictId;
		}
	var castewiseAjaxDiv =  document.getElementById('castewiseAjaxDiv');
		showAjaxImgDiv('castewiseAjaxDiv');
		var jsObj=
			{
				type:type,	
				id:locationId,
				publicationDateId:22
			}
			$.ajax({
			type:'POST',
			 url: 'getParliamentAndDistrictwiseCasteInformationAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(results){
					$("#casteDiv").show();
					buildCastInfoData(results,jsObj);
					buildCastPiechart(results,jsObj);
			
				
			});
} 
$(document).on("click","#volunteerId",function(){
		var jsObj=
			{
				tdpcadreId:globalCadreId	
			}
			$.ajax({
			type:'POST',
			 url: 'getVolunteerDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			buildVolunteersDetails(result);	
			});
});
function buildVolunteersDetails(result){
	$("#volunteerModalDivId").modal("show");
	$("#dataLoadiVolunteerDetailsImg").show();
	//$("#volunteerDetailsModalHeadingId").html("<span style='text-transform:uppercase'>"++" </span>");
		if(result != null){
			var str='';
			str+='<div class="table-responsive">';
			str+='<table class="table m_0 table-bordered">';
				str+='<thead>';
					str+='<tr>';
						str+='<th>Date</th>';
						str+='<th>Event</th>';
						str+='<th>Designation</th>';
						str+='<th>Work Area</th>';
					str+='</tr>';
				str+='</thead>';
				str+='<tbody style="background:#f3f3f3;font-size:12px;">'
				for(var i in result){
					str+='<tr>';
						if(result[i].latitude == null || 	result[i].latitude == "")
							str+='<td class="text-center">-</td>';
						else	
						str+='<td>'+result[i].latitude+'</td>';//work Date
					
						str+='<td>'+result[i].type+'</td>';//Event Name
						str+='<td>'+result[i].name+'</td>';//designation
						str+='<td>'+result[i].location+'</td>';//work Area
						str+='</tr>';
					}
				str+='</tbody>'
			str+='</table>';
			str+='</div>';
			
			$("#dataLoadiVolunteerDetailsImg").hide();
			$("#volunteerDetailsModalBodyId").html(str);
		}
	}

	function getCadreAlertDetails(){
		$('#alertDetailsDiv').html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		$("#alertErrMsgId").html('');
		var dates = $("#alertsDatePicker").val();
		var datesArr = dates.split("-");
		var startDate = datesArr[0];
		var endDate = datesArr[1];
		var searchType = $("input[name='radioBtn']:checked").val();
		var alertType = $("input[name='alertRadioBtn']:checked").val();
			var jsObj=
				{
					tdpCadreId :globalCadreId,
					stateId :1, // AP
					startDateStr : startDate, //"2016-11-27",
					endDateStr : endDate, //"2016-12-27",
					searchType : searchType,//"Assigned",
					alertTypeId : alertType, //1,				
				}
			$.ajax({
			type:'POST',
			 url: 'getCadreAlertDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.subList1 != null && result.subList1.length > 0){
					buildAlertDetails(result);
				}
				else{
					$('#alertDetailsDiv').html('<img src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/>');
					$("#alertErrMsgId").html("<span>No data available.</span>");
				}
			});
	}
	//alertDetailsDiv
	
	function buildAlertDetails(result){
		var finalReslt = result.subList1;
		var alertTypeId = $("input[name='alertRadioBtn']:checked").val();
		var str ='';
		str+='<table class="table table-borderd table-condensed" id="alertTableTab" style="font-size: 10px;">';
		str+='<thead>';
		str+='<tr>';
		str+='<th></th>';
		str+='<th> TOTAL ALERTS </th>';
		if(finalReslt != null && finalReslt.length>0){
			var categoryList = finalReslt[0];
			var alertTypeList = categoryList.subList1;
			if(alertTypeList != null && alertTypeList.length>0){
				for(var i in alertTypeList){
					str+='<th> '+alertTypeList[i].alertTypeName.toUpperCase()+' </th>';
				}
				var statusList = alertTypeList[0].subList1;
				for(var i in statusList){
					str+='<th> '+statusList[i].status.toUpperCase()+' </th>';
				}
				
			}
		}
		str+='</tr>';		
		str+='</thead>';		
		str+='<tbody>';	
		if(finalReslt != null && finalReslt.length>0){
			for(var j in finalReslt){
				str+='<tr>';
				str+='<td> '+finalReslt[j].category.toUpperCase()+'</td>';
				if(finalReslt[j].count != null  && parseInt(finalReslt[j].count)>0 )
					str+='<td style="font-weight:bold;"> <a href="javascript:{}" class="cadreAlertCls" attr_categoryId="'+finalReslt[j].categoryId+'" altert_Type_Id="'+alertTypeId+'" status_id="0" attr_category_type="'+finalReslt[j].category+'" ><u>'+finalReslt[j].count+'</u></a></td>';
				else
					str+='<td> 0  </td>';
				
				if(finalReslt[j].subList1 != null && finalReslt[j].subList1.length>0){
					for(var k in finalReslt[j].subList1 ){
						if(finalReslt[j].subList1[k].count != null  && parseInt(finalReslt[j].subList1[k].count)>0 )
							str+='<td style="font-weight:bold;"> <a href="javascript:{}" class="cadreAlertCls" attr_categoryId="'+finalReslt[j].categoryId+'" altert_Type_Id="'+finalReslt[j].subList1[k].alertTypeId+'" status_id="0" attr_category_type="'+finalReslt[j].category+'" ><u>'+finalReslt[j].subList1[k].count+'</u></a></td>';
						else
							str+='<td> 0  </td>';
					}
						
						var statusList = finalReslt[j].subList2;
						for(var l in statusList){
							if(statusList[l].count != null && parseInt(statusList[l].count)>0 )
								str+='<td style="font-weight:bold;"> <a href="javascript:{}" class="cadreAlertCls" attr_categoryId="'+finalReslt[j].categoryId+'" altert_Type_Id="'+alertTypeId+'" status_id="'+statusList[l].statusId+'" attr_category_type="'+finalReslt[j].category+'" > <u>'+statusList[l].count+'</u></a> </td>';
							else
								str+='<td> 0  </td>';
						}
				}
				str+='</tr>';
			}
		}
		str+='</tbody>';
		str+='</table>';
		
		$('#alertDetailsDiv').html(str);
	}
	
	
					
					
	$(document).on("click",".alerttsCls",function(){
		$('#alertDetailsDiv').html('');
	});
	
	$(document).on("click",".cadreAlertCls",function(){
		$("#alertModalDivId").modal('show');
		var categoryId = $(this).attr('attr_categoryId');
		var alertTypeId = $(this).attr('altert_Type_Id');
		var alertType = $(this).attr('attr_category_type');
		var statusId = $(this).attr('status_id');
		var dates = $("#alertsDatePicker").val();
		var datesArr = dates.split("-");
		var startDate = datesArr[0];
		var endDate = datesArr[1];
		var searchType = $("input[name='radioBtn']:checked").val();
		$('#alertModalStrId').html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		var jsObj=
			{
				tdpCadreId :globalCadreId,
				stateId :1, // AP
				startDateStr : startDate, //"2016-11-27",
				endDateStr : endDate, //"2016-12-27",
				searchType : searchType ,//"Assigned",
				alertTypeId :alertTypeId,					
				categoryId :categoryId,
				statusId :statusId
			}
			$.ajax({
			type:'POST',
			 url: 'getCandidateAlertDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				//console.log(result);
				buildAlertsDEtails(result,alertType);
			});
			
	});

	function buildAlertsDEtails(result,alertType){
		var finalResult = result.subList1;
		$("#myModalLabelId").html("IN MANUAL ALERT " +alertType.toUpperCase());
		var str='';
		if(finalResult != null && finalResult.length>0){
			str+='<table class="table table-bordered table condensed" id="alertsTab">';
			str+='<thead>';
			str+='<tr>';
			//str+='<th>DESCRIPTION </th>';
			str+='<th> CREATED ON </th>';
			str+='<th> LAST UPDATED ON </th>';
			str+='<th> PRESENT STATUS </th>';
			str+='<th> LOG DAYS </th>';
			str+='<th> IMPACT LEVEL </th>';
			str+='<th> DETAILS </th>';
			
			str+='</tr>';
			str+='</thead>';
			
			str+='<tbody>';
			for(var i in finalResult){
				str+='<tr>';
			//	str+='<td>'+finalResult[i].desc+'</td>';
				str+='<td>'+finalResult[i].date1+'</td>';
				str+='<td>'+finalResult[i].date2+'</td>';
				str+='<td>'+finalResult[i].status+'</td>';
				str+='<td>'+finalResult[i].noOfDays+' </td>';
				str+='<td>'+finalResult[i].locationName+'</td>';
				str+='<td> <button class="btn btn-success btn-mini btn-xs alertsDetailsCls" attr_alert_id="'+finalResult[i].id+'"> Alert Details </button></td>';
				
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
		}
		$('#alertModalStrId').html(str);
		$('#alertsTab').dataTable({});
	}
	
	$(document).on("click",".alertsDetailsCls",function(){
		var attr_alert_id = $(this).attr('attr_alert_id');
		window.open('cadreAlertDetailsAction.action?alertId='+attr_alert_id+'&status=false');
		
	});
	
	function benefitSchemesRelatedCalls(){
		getBenefitDetailsAlongFamily();
		getOwnAndParticipatedConstituenciesBenefitDetails();
		getLocalityBasedBenefitSchemesDetails();
	}
	function getBenefitDetailsAlongFamily(){//globalCadreId
		$("#familyBenefitsDivId,#personalBenefitsDivId").html('<img src="images/icons/loading.gif" width="50px" height="50px" style="margin:auto;display:block;">');
		var jsObj={
			tdpCadreId :globalCadreId,
			familyCadreIdsArray :familycadreIdsArrayGlobal
		}
		$.ajax({
		type:'POST',
		 url: 'getBenefitDetailsAlongFamilyAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null && result.length > 0 && result[0] != null){
				var str = '';
				str+='<table class="table table-condensed" style="border:1px solid #ddd;">';
				str+='<thead style="background-color:#ccc;">';
				if(result[0].cadreDetailsVO != null && result[0].cadreDetailsVO.length > 0){
					str+='<th>Total Amount</th>';
					for(var i in result[0].cadreDetailsVO){
						str+='<th>'+result[0].cadreDetailsVO[i].benefitName+'</th>';
					}
				}
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						if(result[0].cadreDetailsVO != null && result[0].cadreDetailsVO.length > 0){
							str+='<td>'+result[0].cadreDetailsVO[0].userWisetotalAmount+'</td>';
							for(var i in result[0].cadreDetailsVO){
								if(result[0].cadreDetailsVO[i].status == "Y")
									str+='<td><img src="img/yes.png" style="width: 18px;"> <sub>'+result[0].cadreDetailsVO[i].amount+'</sub></td>';
								else
								{
									if(result[0].cadreDetailsVO[i].amount == 0)
										str+='<td><img src="img/no.png" style="width: 18px;"> </td>';
									else
										str+='<td><img src="img/no.png" style="width: 18px;"> <sub>'+result[0].cadreDetailsVO[i].amount+'</sub></td>';
								}
							}
						}
					str+='</tr>';
				str+='</tbody>';
				str+='</table>';
				
				$("#personalBenefitsDivId").html(str);
			}else{
				$("#personalBenefitsDivId").html("No Benefits Avalilable.");
			}
				
			if(result != null && result.length > 1){
				var str1 = '';
				str1+='<table class="table table-condensed" style="border:1px solid #ddd;">';
				str1+='<thead>';
					if(result[1].cadreDetailsVO != null && result[1].cadreDetailsVO.length > 0){
						str1+='<th></th>';
						str1+='<th>Total Amount</th>';
						for(var t in result[1].cadreDetailsVO){
							str1+='<th>'+result[1].cadreDetailsVO[t].benefitName+'</th>';
						}
					}
				str1+='</thead>';
				str1+='<tbody>';
					var flag = false;
					for(var t=1;t<result.length;t++){
						if(result[t] != null){
							flag = true;
							str1+='<tr>';
							if(result[t].cadreDetailsVO != null && result[t].cadreDetailsVO.length > 0){
								str1+='<td>'+result[t].cadreDetailsVO[0].cadreName+'</td>';
								str1+='<td>'+result[t].cadreDetailsVO[0].userWisetotalAmount+'</td>';
								for(var i in result[t].cadreDetailsVO){
									if(result[t].cadreDetailsVO[i].status == "Y")
										str1+='<td><img src="img/yes.png" style="width: 18px;"> <sub>'+result[t].cadreDetailsVO[i].amount+'</sub></td>';
									else
									{
										if(result[t].cadreDetailsVO[i].amount == 0)
											str1+='<td><img src="img/no.png" style="width: 18px;"> </td>';
										else
											str1+='<td><img src="img/no.png" style="width: 18px;"> <sub>'+result[t].cadreDetailsVO[i].amount+'</sub></td>';
									}	
								}
							}
							str1+='</tr>';
						}
					}
					if(flag){
						str1+='<tr>';
							str1+='<td>Total</td>';
							str1+='<td>'+result[1].cadreDetailsVO[0].sumOfAll+'</td>';
							for(var t in result[1].cadreDetailsVO){
								if(result[1].cadreDetailsVO[t].schemeWiseTotalCount == 0)
									str1+='<td> - </td>';
								else
									str1+='<td>'+result[1].cadreDetailsVO[t].schemeWiseTotalCount+'</td>';
							}
						str1+='</tr>';
					}
				str1+='</tbody>';
				str1+='</table>';
				$("#familyBenefitsDivId").html(str1);
			}else{
				$("#familyBenefitsDivId").html("No Family Member Got Benefits.");
			}
			
			
		});
	}
	
	function getOwnAndParticipatedConstituenciesBenefitDetails(){
		var constituencyId = $("#cadreConstituencyId").val()=="undefined"?0:$("#cadreConstituencyId").val();
		var pConstituencyId = $("#cadreParticipatedConstituencyId").val()=="undefined" || $("#cadreParticipatedConstituencyId").val().trim()==""?0:$("#cadreParticipatedConstituencyId").val();
		 $("#constituencyBenefitsDivId,#partConstituencyBenefitsDivId").html('<img src="images/icons/loading.gif" width="50px" height="50px" style="margin:auto;display:block;">'); 
		 
		
		var jsObj={
			constituencyId : constituencyId,
			pConstituencyId : pConstituencyId
		}
		$.ajax({
		type:'POST',
		 url: 'getOwnAndParticipatedConstituenciesBenefitDetailsAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null){
				$("#errMsgId").html('');
				var str='';
				if(result.cadreDetailsVO != null && result.cadreDetailsVO.length > 0){
					str+='<table class="table table-condensed" style="border:1px solid #ddd;">';
					str+='<thead style="background-color:#ccc;">';
						str+='<th>Constituency Name</th>';
						for(var t in result.cadreDetailsVO){
							str+='<th>'+result.cadreDetailsVO[t].benefitName+'</th>';
						}
					str+='</thead>';
					str+='<tbody>';
					str+='</tbody>';
						str+='<tr>';
							str+='<td>'+result.cadreDetailsVO[0].constituencyName+'</td>';
							for(var t in result.cadreDetailsVO){
								if(result.cadreDetailsVO[t].amount == 0)
									str+='<td> - </td>';
								else
									str+='<td>'+result.cadreDetailsVO[t].amount+'</td>';
							}
						str+='</tr>';
					str+='</table>';
					$("#constituencyBenefitsDivId").html(str);
				}else{
					$("#constituencyBenefitsDivId").html("No Own Constituency Benefits Available")
				}
				
				var strt='';
				if(result.familyDetailsVO != null && result.familyDetailsVO.length > 0){
					strt+='<table class="table table-condensed" style="border:1px solid #ddd;">';
					strt+='<thead style="background-color:#ccc;">';
						strt+='<th>Constituency Name</th>';
						for(var t in result.familyDetailsVO){
							strt+='<th>'+result.familyDetailsVO[t].benefitName+'</th>';
						}
					strt+='</thead>';
					strt+='<tbody>';
					strt+='</tbody>';
						strt+='<tr>';
							strt+='<td>'+result.familyDetailsVO[0].constituencyName+'</td>';
							for(var t in result.familyDetailsVO){
								strt+='<td>'+result.familyDetailsVO[t].amount+'</td>';
							}
						strt+='</tr>';
					strt+='</table>';
					$("#partConstituencyBenefitsDivId").html(strt);
				}else{
					$("#partConstituencyBenefitsDivId").html("No Participated Constituency Benefits Available");
				}
			}
		});
	}
	
	function getLocalityBasedBenefitSchemesDetails(){
		$("#localityBasedBenefitsDivId").html('<img src="images/icons/loading.gif" width="50px" height="50px" style="margin:auto;display:block;">');
		var jsObj={
			tdpCadreId :globalCadreId
		}
		$.ajax({
		type:'POST',
		 url: 'getLocalityBasedBenefitSchemesDetailsAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null && result.length > 0){
				var str='';
				for(var t in result){
					if(result[t].cadreDetailsVO != null && result[t].cadreDetailsVO.length > 0){
						str+='<table class="table table-condensed" style="border:1px solid #ddd;">';
						str+='<thead>';
							str+='<th style="background-color:#E8E8E8">'+result[t].cadreDetailsVO[0].locationtype+'</th>';
							if(result[t].cadreDetailsVO[0].cadreDetailsVO != null && result[t].cadreDetailsVO[0].cadreDetailsVO.length > 0){
								for(var i in result[t].cadreDetailsVO[0].cadreDetailsVO){
									str+='<th style="background-color:#E8E8E8">'+result[t].cadreDetailsVO[0].cadreDetailsVO[i].benefitName+'</th>';
								}
							}
						str+='</thead>';
						str+='<tbody>';
							if(result[t].cadreDetailsVO != null && result[t].cadreDetailsVO.length > 0){
								for(var i in result[t].cadreDetailsVO){
									str+='<tr>';
										str+='<td>'+result[t].cadreDetailsVO[i].locationName+'';
										if(result[t].cadreDetailsVO[i].locationtype == "District"){
											str+='<i class="glyphicon glyphicon-plus expandIcon distExpandBtnCls" attr_distId="'+result[t].cadreDetailsVO[i].locationId+'"></i>';
										}
										
										str+='</td>';
										if(result[t].cadreDetailsVO[i].cadreDetailsVO != null && result[t].cadreDetailsVO[i].cadreDetailsVO.length > 0){
											for(var j in result[t].cadreDetailsVO[i].cadreDetailsVO){
												if(result[t].cadreDetailsVO[i].cadreDetailsVO[j].amount == 0)
													str+='<td> - </td>';
												else
													str+='<td>'+result[t].cadreDetailsVO[i].cadreDetailsVO[j].amount+'</td>';
											}
										}
									str+='</tr>';
									str+='<tr><td colspan="13" class="constituencyDetailsDivId" style="display:none;" id="constituencyBenefitDetailsId'+result[t].cadreDetailsVO[i].locationId+'"></td></tr>';
								}
							}
							str+='<tr>';
								if(result[t].cadreDetailsVO[0] != null){
									if(result[t].cadreDetailsVO[0].cadreDetailsVO != null && result[t].cadreDetailsVO[0].cadreDetailsVO.length > 0){
										str+='<td>Total</td>';
										for(var k in result[t].cadreDetailsVO[0].cadreDetailsVO){
											if(result[t].cadreDetailsVO[0].cadreDetailsVO[k].schemeWiseTotalCount == 0)
												str+='<td> - </td>';
											else
												str+='<td>'+result[t].cadreDetailsVO[0].cadreDetailsVO[k].schemeWiseTotalCount+'</td>';
										
										}
									}
								}
								str+='<td></td>';
							str+='</tr>';
						str+='</tbody>';
						str+='</table>';
					}
				}
				$("#localityBasedBenefitsDivId").html(str);
			}else{
				$("#localityBasedBenefitsDivId").html("No Locality Benefits Available");
			}
		});
	}
	
	$(document).on("click",".distExpandBtnCls",function(){
		var distId = $(this).attr("attr_distId");
		if($(this).hasClass("glyphicon-plus")){
			$(".distExpandBtnCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$(this).removeClass("glyphicon-plus").addClass("glyphicon-minus");
			
			$(".constituencyDetailsDivId").html("");
			$(".constituencyDetailsDivId").hide();
			$("#constituencyBenefitDetailsId"+distId).show();
			$("#constituencyBenefitDetailsId"+distId).html('<img src="images/icons/loading.gif" width="50px" height="50px" style="margin:auto;display:block;">');
			var jsObj={
				distId : distId
			}
			$.ajax({
			type:'POST',
			 url: 'getAllConstBenefitDetailsForADistAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.length > 0){
					var strt='';
					strt+='<i class="glyphicon glyphicon-remove closeIconBenefit" attr_distId="'+distId+'"></i>';
					strt+='<table class="table table-condensed" style="border:1px solid #ddd;margin-bottom:0px;margin-top:20px;">';
					strt+='<thead>';
						strt+='<th>Constituency</th>';
						if(result[0].cadreDetailsVO != null && result[0].cadreDetailsVO.length > 0){
							for(var t in result[0].cadreDetailsVO){
								strt+='<th>'+result[0].cadreDetailsVO[t].benefitName+'</th>';
							}
						}
					strt+='</thead>';
					strt+='<tbody>';
						for(var t in result){
							strt+='<tr>';
							if(result[t].cadreDetailsVO != null && result[t].cadreDetailsVO.length > 0){
								strt+='<td>'+result[t].locationName+'</td>';
								for(var i in result[t].cadreDetailsVO){
									if(result[t].cadreDetailsVO[i].amount == 0)
										strt+='<td> - </td>';
									else
										strt+='<td class="benefitCountCls" attr_benefit_name="'+result[t].cadreDetailsVO[i].benefitName+'" attr_const_id="'+result[t].locationId+'" attr_benefit_id="'+result[t].cadreDetailsVO[i].benefitId+'">'+result[t].cadreDetailsVO[i].amount+'</span></td>';
								
								}
							}
							strt+='</tr>';
						}
					strt+='</tbody>';
					strt+='</table>';
					
					$("#constituencyBenefitDetailsId"+distId).html(strt);
				}else{
					$("#constituencyBenefitDetailsId"+distId).html("No Data Available.");
				}
			});
			
		}else{
			$(this).addClass("glyphicon-plus").removeClass("glyphicon-minus");
			$("#constituencyBenefitDetailsId"+distId).hide();
		}
	});
	
	
	$(document).on("click",".benefitCountCls",function(){
		var benefitName = $(this).attr("attr_benefit_name");
		$("#benefitCountModalHeading").html(benefitName);
		$("#benefitCountModal").modal({ 
			show: true,
            keyboard: false,
            backdrop: 'static'
        });
		$("#benefitCountCorpBene").html('<img src="images/icons/loading.gif" width="50px" height="50px" style="margin:auto;;display:block;">');
		var jsObj={
			locationLevelValue : $(this).attr("attr_const_id"),
			benefitId : $(this).attr("attr_benefit_id")
		}
		$.ajax({
		type:'POST',
		 url: 'getBenefitSchemesMembersDetailsAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			var str='';
			str+='<table class="table table-bordered">';
				str+='<thead>';
					str+='<th>Name Of Benefeciary</th>';
					str+='<th>Father</th>';
					str+='<th>Membership No</th>';
					//tr+='<th>Aadhar No</th>';
					str+='<th>Mobile No</th>';
					str+='<th>Mandal/Municipality</th>';
					str+='<th>Year</th>';
					str+='<th>Scheme</th>';
					str+='<th>Unit Cost</th>';
				str+='</thead>';
				for(var i in result)
				{
					str+='<tr>';
						str+='<td>'+result[i].name+'</td>';
						str+='<td>'+result[i].relativeName+'</td>';
						str+='<td>'+result[i].memberShipNum+'</td>';
						//str+='<td>'+result[i].aadharNum+'</td>';
						str+='<td>'+result[i].mobilenum+'</td>';
						str+='<td>'+result[i].manMumName+'</td>';
						str+='<td>'+result[i].year+'</td>';
						str+='<td>'+result[i].benefitName+'</td>';
						str+='<td>'+result[i].amount+'</td>';
					str+='</tr>';
				}
			str+='</table>';
			$("#benefitCountCorpBene").html(str);
		});
	});
	
	
	$("#benefitsCollapseBodyId").collapse('hide')
	$(document).on("click","#benefitsCollapseHeadingId",function(){
		$("#benefitsCollapseBodyId").collapse('toggle');
	});
	$(document).on("click",".closeIconBenefit",function(){
		var distId = $(this).attr("attr_distId");
		$("#constituencyBenefitDetailsId"+distId).hide();
		$(".distExpandBtnCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");
	});
	
	