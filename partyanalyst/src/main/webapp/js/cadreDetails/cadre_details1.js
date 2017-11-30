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
		globalIvrCandidateParticipatedCount = result.totalCount;
		if(globalIvrSurveyOnCandidateCount == 0){
			$("#IvrcandiParticipatedId").closest("li").addClass("active li_arr");
			getTypeWiseIvrDetailsOFCadre();
		}
	}
	else{	
	
	$("#IvrcandiParticipatedId").html('0');
	}
	
}
function getSurveysOnCandidateCount(){
	
	$("#IvrSurveyOnCandiId").html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
	var jsObj={
	
		candidateId:globalCandidateId,
		cadreId : memberCadreId
	}
	
	$.ajax({
			type:'POST',
			 url: 'getSurveysOnCandidateCountAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				
				buildIVRSurveysOnCandidateCount(result);
			});
}

var globalIvrSurveyOnCandidateCount = 0;
var globalIvrCandidateParticipatedCount = 0;
//var globalIvrSurveyInCandidteAreaCount = 0;
function buildIVRSurveysOnCandidateCount(result){
	
	var str = '';
	if(result != null && result.totalCount != null && result.totalCount > 0){
		$("#IvrSurveyOnCandiId").html('<a class="text-bold" onclick="getIVRSurveysOnCandidateDetails();" style="cursor:pointer;"> '+result.totalCount+'</a>');
		globalIvrSurveyOnCandidateCount = result.totalCount;
		$("#IvrSurveyOnCandiId").closest("li").addClass("active li_arr");
		getIVRSurveysOnCandidateDetails();
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
		if(globalIvrSurveyOnCandidateCount == 0 && globalIvrCandidateParticipatedCount == 0){
			$("#IvrcandiAreaId").closest("li").addClass("active li_arr");
			getIVRSurveysOnCandidateAreaDetails();
		}
	}
	else
		$("#IvrcandiAreaId").html('0');
	
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
	str+='<div class="panel-group" style="margin-top:20px" id="accordion1211212" role="tablist" aria-multiselectable="true">';
			str+='<div class="table-responsive">';
			str+='<table id="ivrStatusDataTable">';
			str+='<thead><th style="text-align:right;">sort by date<i style="background: rgb(221, 221, 221) none repeat scroll 0% 0%; border-radius: 50%; padding: 9px; float: right;" class="glyphicon glyphicon-sort-by-attributes"></i></th></thead>';
			for(var i in result){
			str+='<tr>';
			str+='<td>';
			 str+='<div class="panel panel-default">';
					str+='<div class="panel-heading" role="tab" id="headingOne1121212'+i+'">';
						str+='<a role="button" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion1211212" href="#collapseOne1121212'+i+'" aria-expanded="true" aria-controls="collapseOne1121212'+i+'">';
							str+='<h4 class="panel-title">';
								str+='<span class="resWidth">'+result[i].date+'</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="resWidth">'+result[i].name+'</span>';
							str+='</h4>';
						str+='</a>';
					str+='</div>';
					
					str+='<div id="collapseOne1121212'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne1121212'+i+'">';
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
	
	var ivrStateId = $("#cadreStateId").val();
	var ivrDistrictId = $("#cadreDistrictId").val();
	var ivrConstituencyId = $("#cadreConstituencyId").val();
	var ivrMandalId = $("#cadremandalId").val();
	var ivrLebId = $("#cadreRuralORUrbanId").val();
	var ivrPanchayatId = $("#cadrePanchaytId").val();
	var ivrWardId = $("#cadreWardId").val();
	
	if(ivrStateId == "")
		ivrStateId = 0;
	if(ivrDistrictId == "")
		ivrDistrictId = 0;
	if(ivrConstituencyId == "")
		ivrConstituencyId = 0;
	if(ivrMandalId == "")
		ivrMandalId = 0;
	if(ivrLebId == "")
		ivrLebId = 0;
	if(ivrPanchayatId == "")
		ivrPanchayatId = 0;
	if(ivrWardId == "")
		ivrWardId = 0;
	
	var jsObj={
			candidateId:globalCandidateId,
			cadreId : memberCadreId,
			stateId : ivrStateId,
			districtId : ivrDistrictId,
			constituencyId : ivrConstituencyId,
			mandalId : ivrMandalId,
			lebId : ivrLebId,
			panchayatId : ivrPanchayatId,
			wardId : ivrWardId,
			task:""
	}
	$('#ivrsurveyDataLoadoing').show();
	$.ajax({
		type:'GET',
		url :'getSurveysOnCandidateDetailsAction.action',
		data : {task:JSON.stringify(jsObj)} ,
	}).done(function(result){
		$('#ivrsurveyDataLoadoing').hide();
		buildSurveysOnCandidateDetailsNew(result);
	});
}

function buildSurveysOnCandidateDetailsNew(result){
	
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
						var count = parseInt(result[i].questions[j].options.length)+parseInt(1);
						str+='<table class="table table-bordered">';
						str+='<tr><td colspan="'+count+'" style="text-weight:bold">'+result[i].questions[j].name+'</td></tr>';
						str+='<b><tr>';
							str+='<td>Level</td>';
							for(var k in result[i].questions[j].options){
								str+='<td>'+result[i].questions[j].options[k].name+'</td>';
							}
						str+='</tr></b>';
						str+='<tr>';
							str+='<td>'+result[i].questions[j].remarks+'</td>';
							for(var k in result[i].questions[j].options){
								str+='<td>'+result[i].questions[j].options[k].count+' ('+result[i].questions[j].options[k].percentage+'%)</td>';
							}
						str+='</tr>';
						/*str+='<b><tr><td>Option</td><td>Count</td><td>Percentage</td></tr></b>';
						for(var k in result[i].questions[j].options){
							str+='<tr>';
								str+='<td>'+result[i].questions[j].options[k].name+'</td>';
								str+='<td>'+result[i].questions[j].options[k].count+'</td>';
								str+='<td>'+result[i].questions[j].options[k].percentage+'</td>';
							str+='</tr>';
						}*/
						
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

	function getCadreToursDetails(){
		$('#toursDetailsDiv').html('<img src="images/icons/loading.gif" style="width:25px;height:20px;"/>');
		$("#tourErrMsgId").html('');
		var designationId = $('#toursDesignId').val();
		var searchMonth = $('#toursDatePicker').val();
			var jsObj=
				{
					tdpCadreId :globalCadreId,
					stateId :1, // AP
					startDateStr : '', //"2016-11-27",
					endDateStr : '', //"2016-12-27",
					designationId : designationId,//"Assigned",
					searchMonth : "06-2016", //1,	
					searchType	:""
				}
			$.ajax({
			type:'POST',
			 url: 'getCadretoursDetailsAction.action',
			 data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result != null && result.subList != null && result.subList.length > 0){
					buildToursDetails(result);
				}
				else{
					$('#toursDetailsDiv').html('<img src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/>');
					$("#tourErrMsgId").html("<span>No data available.</span>");
				}
			});
	}
	function buildToursDetails(result){
		
		var str='';
		if(result.subList != null && result.subList.length>0){
			str+='<table class="table table-borderd table-condensed" id="alertTableTab" style="font-size: 10px;">';
			str+='<thead>';
			str+='<tr>';
			str+='<th>  </th>';
			str+='<th> TARGET  </th>';
			str+='<th> SUBMITED </th>';
			str+='<th> OVARALL COMPLAINCE </th>';
			str+='</tr>';		
			str+='</thead>';		
			str+='<tbody>';	
			var gtargetcount = 0;
			var gsubmittedcount = 0;
			for(var i in result.subList){
				str+='<tr>';
				str+='<td> '+result.subList[i].name.toUpperCase()+'</td>';
				var targetcount = 0;
				var submittedcount = 0;
				var perc ="0.00";
				if(result.subList[i].totalCount != null && result.subList[i].totalCount>0){				
					str+='<td>'+result.subList[i].totalCount+'</td>';
					targetcount=result.subList[i].totalCount;		
				}
				else
					str+='<td> -  </td>';	
				if(result.subList[i].count != null && result.subList[i].count>0){				
					str+='<td>'+result.subList[i].count+'</td>';
						submittedcount = result.subList[i].count;				}
				else
					str+='<td> - </td>';	
				
				if(parseInt(submittedcount) >0 && parseInt(targetcount) >0)
					perc = parseInt(submittedcount)*100.0/ parseInt(targetcount);
				console.log(perc);
				str+='<td>'+parseFloat(perc).toFixed(2)+'</td>';				
				str+='</tr>';
				
				gtargetcount = parseInt(gtargetcount)+parseInt(targetcount) ;
				gsubmittedcount = parseInt(gsubmittedcount)+parseInt(submittedcount) ;
			}
			
			str+='<tr>';
				str+='<td>  TOTAL TOURS </td>';
				var perc ="0.00";
				if(gtargetcount != null && gtargetcount>0){				
					str+='<td>'+gtargetcount+'</td>';		
				}
				else
					str+='<td> -  </td>';	
				if(gsubmittedcount != null && gsubmittedcount>0){				
					str+='<td>'+gsubmittedcount+'</td>';
				}
				else
					str+='<td> - </td>';	
				
				if(parseInt(gsubmittedcount) >0 && parseInt(gtargetcount) >0)
					perc = parseInt(gsubmittedcount)*100.0/ parseInt(gtargetcount);
				console.log(perc);
				str+='<td>'+parseFloat(perc).toFixed(2)+'</td>';				
				str+='</tr>';
							
			str+='</tbody>';	
			str+='</table>';	
			$('#toursDetailsDiv').html(str);
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
		$("#myModalLabelId").html('');
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
		$("#myModalLabelId").html(alertType.toUpperCase());
		var str='';
		if(finalResult != null && finalResult.length>0){
			str+='<table class="table table-bordered table condensed" id="alertsTab">';
			str+='<thead>';
			str+='<tr>';
			//str+='<th>DESCRIPTION </th>';
			str+='<th> CREATED DATE </th>';
			str+='<th> LAST UPDATED DATE </th>';
			str+='<th> CURRENT STATUS </th>';
			str+='<th> LAGS DAYS </th>';
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
				str+='<td> <button class="btn btn-success btn-mini btn-xs alertsDetailsCls" attr_status='+finalResult[i].status+' attr_alert_id="'+finalResult[i].id+'"> Alert Details </button></td>';
				
				str+='</tr>';
			}
			str+='</tbody>';
			str+='</table>';
		}
		$('#alertModalStrId').html(str);
		$('#alertsTab').dataTable({});
	}
	//santosh
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
			if(result != null){
			var personalBenefitRsltDtls = result.cadreDetailsVO;	
			var familyBenefitsRsltDtls  = result.familyDetailsVO;
			if(personalBenefitRsltDtls != null && personalBenefitRsltDtls.length > 0){
				var str = '';
				str+='<table class="table table-condensed tableHeaderFontSize" style="border:1px solid #ddd;">';
				str+='<thead>';
				str+='<tr>';
				if(personalBenefitRsltDtls[0].cadreDetailsVO != null && personalBenefitRsltDtls[0].cadreDetailsVO.length > 0){
				str+='<th>Total Amount</th>';
					for(var i in personalBenefitRsltDtls[0].cadreDetailsVO){
						str+='<th>'+personalBenefitRsltDtls[0].cadreDetailsVO[i].benefitName+'</th>';
					}
				}
				str+='</tr>';
				str+='</thead>';
				str+='<tbody>';
					str+='<tr>';
						if(personalBenefitRsltDtls[0].cadreDetailsVO != null && personalBenefitRsltDtls[0].cadreDetailsVO.length > 0){
							if(personalBenefitRsltDtls[0].userWisetotalAmount > 0){
							str+='<td>'+personalBenefitRsltDtls[0].userWisetotalAmount+'</td>';	
							}else{
							str+='<td>-</td>';	
							}
							for(var i in personalBenefitRsltDtls[0].cadreDetailsVO){
								if(personalBenefitRsltDtls[0].cadreDetailsVO[i].status == "Y"){
									if(personalBenefitRsltDtls[0].cadreDetailsVO[i].amount > 0){
									  str+='<td><img src="img/yes.png" style="width: 18px;"> <sub>'+personalBenefitRsltDtls[0].cadreDetailsVO[i].amount+'</sub></td>';	
									}else{
									   str+='<td><img src="img/yes.png" style="width: 18px;"></td>';	
									}	
								}
								else
								{
								 str+='<td><img src="img/no.png" style="width: 18px;"> </td>';
								}
							}
						}
					str+='</tr>';
				str+='</tbody>';
				str+='</table>';
				$("#personalBenefitsDivId").html(str);
			}else{
				$("#personalBenefitsDivId").html("");
			}
				
			if(familyBenefitsRsltDtls != null && familyBenefitsRsltDtls.length > 0){
				var str1 = '';
				str1+='<table class="table table-condensed tableHeaderFontSize" style="border:1px solid #ddd;">';
				str1+='<thead >';
					if(familyBenefitsRsltDtls[0].cadreDetailsVO != null && familyBenefitsRsltDtls[0].cadreDetailsVO.length > 0){
						str1+='<tr>';
						str1+='<th></th>';
						str1+='<th>Total Amount</th>';
						for(var t in familyBenefitsRsltDtls[0].cadreDetailsVO){
							str1+='<th>'+familyBenefitsRsltDtls[0].cadreDetailsVO[t].benefitName+'</th>';
						}
						str1+='</tr>';
					}
				str1+='</thead>';
				str1+='<tbody>';
					for(var t=0;t<familyBenefitsRsltDtls.length;t++){
						if(familyBenefitsRsltDtls[t] != null){
							str1+='<tr>';
							if(familyBenefitsRsltDtls[t].cadreDetailsVO != null && familyBenefitsRsltDtls[t].cadreDetailsVO.length > 0){
								str1+='<td>'+familyBenefitsRsltDtls[t].cadreName+'</td>';
								if(familyBenefitsRsltDtls[t].userWisetotalAmount > 0){
								   str1+='<td>'+familyBenefitsRsltDtls[t].userWisetotalAmount+'</td>';	
								}else{
								str1+='<td>-</td>';	
								}
								
								for(var i in familyBenefitsRsltDtls[t].cadreDetailsVO){
									if(familyBenefitsRsltDtls[t].cadreDetailsVO[i].status == "Y"){
										if(familyBenefitsRsltDtls[t].cadreName != null && familyBenefitsRsltDtls[t].cadreName.trim()=="TOTAL"){
											if(familyBenefitsRsltDtls[t].cadreDetailsVO[i].amount > 0){
											str1+='<td>'+familyBenefitsRsltDtls[t].cadreDetailsVO[i].amount+'</td>';		
											}else{
											str1+='<td> - </td>';			
											}
										}else{
											if(familyBenefitsRsltDtls[t].cadreDetailsVO[i].amount > 0){
											   str1+='<td><img src="img/yes.png" style="width: 18px;"> <sub>'+familyBenefitsRsltDtls[t].cadreDetailsVO[i].amount+'</sub></td>';		
											}else{
											  str1+='<td><img src="img/yes.png" style="width: 18px;"></td>';		
											}
										}
									}
									else
									{
										if(familyBenefitsRsltDtls[t].cadreName != null && familyBenefitsRsltDtls[t].cadreName.trim()=="TOTAL"){
											str1+='<td> - </td>';	
										}else{
											 str1+='<td><img src="img/no.png" style="width: 18px;"></td>';	
										}
									
									}	
								}
							}
							str1+='</tr>';
						}
					}
				str1+='</tbody>';
				str1+='</table>';
				$("#familyBenefitsDivId").html(str1);
			}else{
				$("#familyBenefitsDivId").html("");
			}
			}
		});
	}
	
	function getOwnAndParticipatedConstituenciesBenefitDetails(){
		$("#participatedConsGBenefitDivId").show();
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
					str+='<table class="table table-condensed tableHeaderFontSize" style="border:1px solid #ddd;">';
					str+='<thead>';
					str+='<tr>';
						str+='<th>Constituency Name</th>';
						for(var t in result.cadreDetailsVO){
							str+='<th>'+result.cadreDetailsVO[t].benefitName+'</th>';
						}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
					str+='</tbody>';
						str+='<tr>';
							str+='<td>'+result.cadreDetailsVO[0].constituencyName+'</td>';
							for(var t in result.cadreDetailsVO){
								if(result.cadreDetailsVO[t].amount == 0)
									str+='<td> - </td>';
								else
									str+='<td attr_benefit_id="'+result.cadreDetailsVO[t].benefitId+'" style="cursor:pointer;" attr_const_id="'+result.cadreDetailsVO[0].constituencyId+'" attr_benefit_name="'+result.cadreDetailsVO[t].benefitName+'" class="benefitCountCls alertColorFont" >'+result.cadreDetailsVO[t].amount+'</td>';
							}
						str+='</tr>';
					str+='</table>';
					$("#constituencyBenefitsDivId").html(str);
				}else{
					$("#constituencyBenefitsDivId").html("No Own Constituency Benefits Available")
				}
				
				var strt='';
				if(result.familyDetailsVO != null && result.familyDetailsVO.length > 0){
					strt+='<table class="table table-condensed tableHeaderFontSize" style="border:1px solid #ddd;">';
					strt+='<thead>';
					str+='<tr>';
						strt+='<th>Constituency Name</th>';
						for(var t in result.familyDetailsVO){
							strt+='<th>'+result.familyDetailsVO[t].benefitName+'</th>';
						}
						str+='</tr>';
					strt+='</thead>';
					strt+='<tbody>';
					strt+='</tbody>';
						strt+='<tr>';
							strt+='<td>'+result.familyDetailsVO[0].constituencyName+'</td>';
							for(var t in result.familyDetailsVO){
								if(result.familyDetailsVO[t].amount == 0){
									strt+='<td> - </td>';	
								}else{
								 strt+='<td attr_benefit_id="'+result.familyDetailsVO[t].benefitId+'" style="cursor:pointer;" attr_const_id="'+result.familyDetailsVO[0].constituencyId+'" attr_benefit_name="'+result.familyDetailsVO[t].benefitName+'" class="benefitCountCls alertColorFont">'+result.familyDetailsVO[t].amount+'</td>';
								}
							}
						strt+='</tr>';
					strt+='</table>';
					$("#partConstituencyBenefitsDivId").html(strt);
				}else{
					//$("#partConstituencyBenefitsDivId").html("No Participated Constituency Benefits Available");
					$("#participatedConsGBenefitDivId").hide();
				}
			}
		});
	}
	
	function getLocalityBasedBenefitSchemesDetails(){
		$("#localityConsBenefitDivId").show();
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
						str+='<h4><span style="background-color:#E8E8E8;padding:5px;">'+result[t].designationName+'</span></h4>';
						str+='<table class="table table-condensed tableHeaderFontSize  m_top20" style="border:1px solid #ddd;">';
						str+='<thead >';
						str+='<tr>';
							str+='<th style="background-color:#E8E8E8">'+result[t].cadreDetailsVO[0].locationtype+'</th>';
							if(result[t].cadreDetailsVO[0].cadreDetailsVO != null && result[t].cadreDetailsVO[0].cadreDetailsVO.length > 0){
								for(var i in result[t].cadreDetailsVO[0].cadreDetailsVO){
									str+='<th style="background-color:#E8E8E8">'+result[t].cadreDetailsVO[0].cadreDetailsVO[i].benefitName+'</th>';
								}
							}
							str+='</tr>';
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
												if(result[t].cadreDetailsVO[i].cadreDetailsVO[j].amount == 0){
												  str+='<td> - </td>';	
												}else{
													if(result[t].cadreDetailsVO[i].locationtype == "District"){
													  str+='<td>'+result[t].cadreDetailsVO[i].cadreDetailsVO[j].amount+'</td>';		
													}else{
														str+='<td class="benefitCountCls alertColorFont" attr_benefit_name="'+result[t].cadreDetailsVO[i].cadreDetailsVO[j].benefitName+'" attr_const_id="'+result[t].cadreDetailsVO[i].locationId+'" attr_benefit_id="'+result[t].cadreDetailsVO[i].cadreDetailsVO[j].benefitId+'">'+result[t].cadreDetailsVO[i].cadreDetailsVO[j].amount+'</td>';		
													}
												}
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
				//$("#localityBasedBenefitsDivId").html("No Locality Benefits Available");
				$("#localityConsBenefitDivId").hide();
			}
		});
	}
	$(document).on("click",".benefiExpandCls",function(){
		if($(this).hasClass("glyphicon-plus")){
			$(this).removeClass("glyphicon-plus").addClass("glyphicon-minus");
		    $(this).closest("div").find(".hideShowCls").show();	
		}else{
		    $(this).closest("div").find(".hideShowCls").hide();	
			$(this).removeClass("glyphicon-minus").addClass("glyphicon-plus");
		}
	});
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
										strt+='<td class="benefitCountCls alertColorFont" attr_benefit_name="'+result[t].cadreDetailsVO[i].benefitName+'" attr_const_id="'+result[t].locationId+'" attr_benefit_id="'+result[t].cadreDetailsVO[i].benefitId+'">'+result[t].cadreDetailsVO[i].amount+'</span></td>';
								
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
		var	constituencyId = $(this).attr("attr_const_id");
		var benefitId = $(this).attr("attr_benefit_id");
		var benefitName = $(this).attr("attr_benefit_name");
		$("#benefitCountModalHeading").html(benefitName);
	   $("#customPaginationDivId").html('');
		$("#benefitCountModal").modal({ 
			show: true,
            keyboard: false,
            backdrop: 'static'
        });
		$("#benefitCountModal").parent().find(".modal-backdrop").addClass("heightApply");
		getBenefitMemberDetails(constituencyId,benefitId,0);
	});
	
	function getBenefitMemberDetails(constituencyId,benefitId,minValue){
		 $("#benefitCountCorpBene").html('<img src="images/icons/loading.gif" width="50px" height="50px" style="margin:auto;;display:block;">');
		var jsObj={
			locationLevelValue : constituencyId,
			benefitId : benefitId,
			minValue: minValue,
			maxValue: 10
			
		}
		$.ajax({
		type:'POST',
		 url: 'getBenefitSchemesMembersDetailsAction.action',
		 data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
			if(result != null && result.length > 0){
			 buildBenefitMemberDetails(result,constituencyId,benefitId,minValue);	
			}else{
			 $("#benefitCountCorpBene").html('NO DATA AVAILABLE');	
			}
		});
	}
	function buildBenefitMemberDetails(result,constituencyId,benefitId,minValue){
		var str='';
			str+='<table class="table table-bordered" id="dataTableId">';
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
				var totalCount = result[0].totalCount;
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
						if(result[i].amount > 0){
						 str+='<td>'+result[i].amount+'</td>';	
						}else{
						 str+='<td>-</td>';	
						}
						
					str+='</tr>';
				}
			str+='</table>';
			$("#benefitCountCorpBene").html(str);
			$("#dataTableId").dataTable({
			   responsive: true,
			   "paging":   false,
			   "bLengthChange": false,
			   "bInfo": false,
			   "info":     false,
			});
			 $(".paginate_disabled_previous, .paginate_disabled_next").hide(); 
			if(minValue == 0 && totalCount > 10){
				$("#customPaginationDivId").pagination({
					items: totalCount,
					itemsOnPage: 10,
					cssStyle: 'light-theme',
					onPageClick: function(pageNumber) { 
						var num=(pageNumber-1)*10;
						getBenefitMemberDetails(constituencyId,benefitId,num);
					}
				});
			}	
	}
	
	
	$("#benefitsCollapseBodyId").collapse('hide')
	$(document).on("click","#benefitsCollapseHeadingId",function(){
		$("#benefitsCollapseBodyId").collapse('toggle');
	});
	$(document).on("click",".closeIconBenefit",function(){
		var distId = $(this).attr("attr_distId");
		$("#constituencyBenefitDetailsId"+distId).hide();
		$(".distExpandBtnCls").addClass("glyphicon-plus").removeClass("glyphicon-minus");
	});
	
	/* Tour Block Start */	
	 
   $(document).on("click","#toursHeaderId",function(){
	var isVisible = $( "#toursProfileHideId" ).is( ":visible" );
	if(isVisible==false){
		 $( "#toursProfileHideId" ).show();
		 $( "#toursProfileShowId" ).hide();
	}else{
		$( "#toursProfileHideId" ).hide();
		$( "#toursProfileShowId" ).show();
	}
	$("#tourBodyId").collapse('toggle');
     });
	$(document).on("click",".closeShowPdfCls",function(){
		setTimeout(function(){
		$('body').addClass("modal-open");
		}, 500);                     
	}); 
	$("#toursDatePicker").on("dp.change", function(e) {
       getCadreTourDetails();
     });
	function getCadreTourDetails(){
		       $("#cadreToursDetailsDiv").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		       var tourSeletedDate = $("#toursDatePicker").val();
				var jsObj=
					{
						tdpCadreId :globalCadreId,
						startDateStr : tourSeletedDate,
						endDateStr : tourSeletedDate, 
					}
				 $.ajax({
				 type:'POST',
				 url: 'getCadreTourDetailsAction.action',
				 data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					 $("#cadreToursDetailsDiv").html('');
					  buildCadreTourDetails(result);
				});
		}
	function buildCadreTourDetails(result){
		 
		          /* Checking cadre is involve in tour or not.If cadre is involve in tour then only we are showing tour block */
		          if(result != null && result.candDtlsId != null && result.candDtlsId > 0){
					 $("#cadreTourBlockDivId").show();  
				  }
				  var str1='';
				  if(result.subList != null && result.subList.length > 0){
				
					if($(window).width() < 500)
					{
						str1+='<div class="table-responsive">';
					}
					str1+='<table class="table table-bordered borderedWeight">';
						str1+='<thead class="bg_D8">';
							str1+='<tr>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Designation</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Category</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Target</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Submitted Tour</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Complaince</th>';
							str1+='</tr>';
							str1+='<tbody>';
							  
								for(var i in result.subList){
									str1+='<tr>';
									var moxCategoryLength = 0;
									if(result.subList[i].subList != null && result.subList[i].subList.length > 0){
									   moxCategoryLength = result.subList[i].subList.length;
									}
								str1+='<td style="cursor:pointer;color:rgb(51, 122, 183)" attr_designation_name="'+result.subList[i].designation+'" attr_candiate_name="'+result.subList[i].name+'" class="cadreTourDetailsCls" attr_candiate_id="'+result.subList[i].candDtlsId+'" rowspan='+(moxCategoryLength)+'>'+result.subList[i].designation+'</td>';
										if(result.subList[i].subList != null && result.subList[i].subList.length > 0){
											 var categoryVO = result.subList[i].subList[0];
											    if(categoryVO != null){
													 if(categoryVO.name == null || categoryVO.name == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+categoryVO.name+'</td>';
														}
														if(categoryVO.targetDays == null || categoryVO.targetDays == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td class="text-center">'+categoryVO.targetDays+'</td>';
														}
														if(categoryVO.complainceDays == null || categoryVO.complainceDays==0){
															str1+='<td> - </td>';
														}else{
															str1+='<td class="text-center">'+categoryVO.complainceDays+'</td>';
														}	
														
														var complainceDays = categoryVO.complainceDays;
														var targetDays = categoryVO.targetDays;
														if(complainceDays>=targetDays){
														   str1+='<td style="background-color:#3DBC93;text-align:center;width:50px;"><i class="glyphicon glyphicon-ok" style="font-size:15px;display:block;color:#068057;"></i></td>';
														}else{
														   str1+='<td style="background-color:#E35B69;text-align:center;width:50px;"><i class="glyphicon glyphicon-remove" style="font-size:15px;display:block;color:#bf3646;"></i></td>';
														} 	 	
												}
										} 
									str1+='</tr>';
										if(result.subList[i].subList != null && result.subList[i].subList.length > 0){
											var categoryList = result.subList[i].subList ;
												for(var k in categoryList){
													if(k == 0)
													continue;
													str1+='<tr>';
													if(categoryList[k].name == null || categoryList[k].name == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td>'+categoryList[k].name+'</td>';
													}
													if(categoryList[k].targetDays == null || categoryList[k].targetDays == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td class="text-center">'+categoryList[k].targetDays+'</td>';
													}
													if(categoryList[k].complainceDays == null || categoryList[k].complainceDays==0){
														str1+='<td> - </td>';
													}else{
														str1+='<td class="text-center">'+categoryList[k].complainceDays+'</td>';
													}
													var complainceDays = categoryList[k].complainceDays;
													var targetDays = categoryList[k].targetDays;
													if(complainceDays>=targetDays){
													   str1+='<td style="background-color:#3DBC93;text-align:center;width:50px;"><i class="glyphicon glyphicon-ok" style="font-size:15px;display:block;color:#068057;"></i></td>';
													}else{
													   str1+='<td style="background-color:#E35B69;text-align:center;width:50px;"><i class="glyphicon glyphicon-remove" style="font-size:15px;display:block;color:#bf3646;"></i></td>';
													} 	 	
											 str1+='</tr>';
											}
										}
							
							  }
			
							str1+='</tbody>';
						str1+='</thead>';
					str1+='</table>';	
					if($(window).width() < 500)
					{
						str1+='</div>';
					}  
				  }else{
					str1='NO DATA VAILABLE';  
				  }	  
		         $("#cadreToursDetailsDiv").html(str1);  
		
	}	
	
	$(document).on("click",".cadreTourDetailsCls",function(){
		//$("#cadreTourSlider").dateRangeSlider("destroy");
		var candiateId = $(this).attr("attr_candiate_id");
		var designationName = $(this).attr("attr_designation_name");
		var candiateName = $(this).attr("attr_candiate_name");
		$("#dateRangeSliderYear").val(0);
		 getCandiateWiseTourDetails(candiateId,designationName,candiateName);
	});
$(document).on("click",".closeIconModal",function(){
	$("#cadreTourSlider").dateRangeSlider("destroy");
});
function getCandiateWiseTourDetails(candiateId,designationName,candiateName)
	{ 
		$("#cadreTourModalDivId").modal({
            show: true,
            keyboard: false,
            backdrop: 'static'
        });
		$("#cadreTourModalDivId").parent().find(".modal-backdrop").addClass("heightApply");
		$("#subMitBtn").attr("attr_candidate_id",candiateId);
		 var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
	
	    var tourSeletedDate = $("#toursDatePicker").val();
		var fromDate = tourSeletedDate.split("-");
		var toDate = tourSeletedDate.split("-");
		tourSeletedDate = "01"+"/"+fromDate[0]+"/"+fromDate[1];
	  
	
	 	$("#cadreTourSlider").dateRangeSlider({
			 bounds: {min: new Date(fromDate[1], 0, 1), max: new Date(toDate[1], 11, 31)},
			//defaultValues: {min: new Date(2016, 1, 10), max: new Date(2016, 4, 22)},
			   defaultValues: {min: new Date(fromDate[1],fromDate[0]-1,1), max: new Date(toDate[1],toDate[0]-1,30)},
				scales: [{
				  first: function(value){ return value; },
				  end: function(value) {return value; },
				  next: function(value){
					var next = new Date(value);
					return new Date(next.setMonth(value.getMonth() + 1));
				  },
				  label: function(value){
					return months[value.getMonth()];
				  },
				  format: function(tickContainer, tickStart, tickEnd){
					tickContainer.addClass("myCustomClass");
				  }
				}]
		});
		$("#tourCadreHeadingId").html("<h4 class='modal-title text-capital'>"+candiateName+" - <small style='color:#4A5863'>"+designationName+"</small></h4>");
		$("#cadreTourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#cadreTourMonthWiseDtlsBlockDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		$("#cadreMonthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
		var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :tourSeletedDate, 
					 toDate :tourSeletedDate 
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#cadreTourIndividualDetailsBlock").html('');
			$("#cadreTourMonthWiseDtlsBlockDivId").html('');
			$("#cadreMonthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
	}
	
   function buildIndividualPersonTourDetails(result){
	  
	   $("#cadreTourIndividualDetailsBlock").html('');
	 	if(result !=null && result.subList != null && result.subList.length > 0){
			 var str='';
			str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					str+='<div class="row">';
					
						str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">TOTAL COMPLAINCE OVERVIEW</h4>';
							str+='<div id="overAllComplainsGraph" class="" style="height:150px" ></div>';
						str+='</div>';
						str+='<div class="col-md-8 col-xs-12 col-md-12">';
							str+='<div class="row">';
						for(var i in result.subList){
							str+='<div class="col-md-4 col-xs-12 col-md-12">';
							str+='<h4 class="text-capital">'+result.subList[i].name+'</h4>';
							str+='<div id="individualComplainsGraph'+i+'" class="" style="height:150px" ></div>';
							str+='</div>';
						
						}
					str+='</div>';
					str+='</div>';
					str+='</div>';
					
			str+='</div>';
			$("#cadreTourIndividualDetailsBlock").html(str);
		}else{
			$("#cadreTourIndividualDetailsBlock").html("NO DATA AVAILABLE.");
		}
		
		if(result !=null && result.subList != null && result.subList.length > 0){
			
			var mainArrNma=[];
			var individualPerfArr=[];
			var nameArr;
			var jsonObj=[];	
			mainArrNma.push("All")
			jsonObj.push(result.complaincePer);
			for(var i in result.subList){
				jsonObj.push(result.subList[i].complaincePer);
				mainArrNma.push(result.subList[i].name);
				nameArr = result.subList[i].name;
			}
		
		 $(function () {
			  $('#overAllComplainsGraph').highcharts({
				colors: ['#80F6F8'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: mainArrNma,
					title: {
						text: null
					},
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				 tooltip: {formatter: function(){
					return '<b>'+this.x+' : '+ Highcharts.numberFormat(this.y, 2) +'%</b>';
				}      
				},
				plotOptions: {
					column: {  
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return Highcharts.numberFormat(this.y,2) +"%"; 
								}
							}
						  
						},
					},
					
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				
				series:  [{
					name: nameArr,
					data: jsonObj
				}]
			});
		});
	}
	
	if(result !=null && result.subList != null && result.subList.length > 0){
			for(var i in result.subList){
				var performanceArr =[];
				performanceArr.push(result.subList[i].targetDays);
				performanceArr.push(result.subList[i].complainceDays);
				//performanceArr.push(result.subList[i].yetToTourCnt);
		if(performanceArr != 0 && performanceArr.length > 0){		
		 $(function () {
			  $('#individualComplainsGraph'+i+'').highcharts({
				colors: ['#7F7037','#80F6F8'],
				chart: {
					type: 'column'
				},
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: ["Target","Toured"],
					title: {
						text: null
					},
					labels: {
							formatter: function() {
								return this.value.toString().substring(0, 5)+'...';
							},
							
						}
					
				},
				yAxis: {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null,
						align: 'high'
					},
					labels: {
						overflow: 'justify',
						enabled: false,
					}
				},
				 tooltip: {formatter: function(){
					return '<b>'+this.x+' : '+ (this.y) +'</b>';
					
				}      
				},
				plotOptions: {
					column: {  
						//stacking: 'normal',
						dataLabels: {
							enabled: true,
							 formatter: function() {
								if (this.y === 0) {
									return null;
								} else {
									return '<span style="text-align:center">'+(this.y)+'</span>';
								}
							}
						  
						},
					},
					
				},
				legend: {
					enabled: false,
					layout: 'vertical',
					align: 'right',
					verticalAlign: 'top',
					x: -40,
					y: 80,
					floating: true,
					borderWidth: 1,
					backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
					shadow: true
				},
				credits: {
					enabled: false
				},
				
				series:  [{
					name: '',
					data: performanceArr,
					colorByPoint:true
				}]
			});
		});
		}else{
			 $('#individualComplainsGraph'+i+'').html("No Data Availble");
		}
	}	
	}
	
	if(result !=null && result.monthList != null && result.monthList.length > 0){
		  var str2='';
		  
		  	 str2+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				str2+='<h4 class="text-capital">MONTH WISE COMPLIANCE OVERVIEW</h4>';
					 str2+='<div class="slickApplyTourCls" >';	
				for(var i in result.monthList){
						if(i == 0 || i%3 == 0){
							str2+='<div class="row">';
						}
					   if($(window).width() < 500)
						{
							str2+='<div class="table-responsive">';
						}
						str2+='<div class="col-md-4 col-xs-12 col-sm-12 m_top10">';
							str2+='<table class="table table-bordered">';
							 var categoryList = result.monthList[i].subList;
							 
							 if(categoryList != null && categoryList.length > 0){
									var moxCategoryLength = categoryList.length;
									var categoryVO = result.monthList[i].subList[0];
								str2+='<tr>';
									str2+='<td  rowspan='+(moxCategoryLength+1)+' style="font-size:22px;background-color:#EDECE7">'+result.monthList[i].name+'<br>'+result.monthList[i].year+'</td>';
									str2+='<td style="background-color:#EDECE7"><p>'+categoryVO.name+'('+categoryVO.complainceDays+')';
									   if(categoryVO.complainceDays >= categoryVO.targetDays){
											str2+='<small style="text-align: center;"><i  style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-success "></i></small>';
										   }else{
											str2+='<small style="text-align: center;"><i  style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
										   } 
									  str2+='<div class="dropup">';
									str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
										str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu2" style="padding:10px;z-index:999;width:220px">';
											str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
											str2+='<table class="table">';
												str2+='<tr><td style="background-color:#EDECE7">'+categoryVO.name+' - '+categoryVO.targetDays+'</td></tr>';
											str2+='</table>';
										str2+='</div>';
									str2+='</div></p>';  
										  str2+='</td>';  
								        if(result.monthList[i].isComplaince != null && result.monthList[i].isComplaince.trim()=="True"){
										   str2+='<td style="background-color:#3DBC93;text-align:center;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-ok" style="font-size:55px;display:block;color:#068057;"></i><small style="color:#fff;">Compliance</small></td>';
										}else{
										   str2+='<td style="background-color:#E35B69;text-align:center;width:50px;" rowspan="'+(moxCategoryLength+1)+'"><i class="glyphicon glyphicon-remove" style="font-size:55px;display:block;color:#bf3646;"></i><small style="color:#fff;">Non Compliance</small></td>';
										} 	 
								  str2+='</tr>';
								
									for(var k in categoryList){
										if(k==0)
										continue;
										 str2+='<tr>';
										if(categoryList[k].name == null || categoryList[k].name == ""){
											str2+='<td style="background-color:#EDECE7;"> - </td>';
										}else{
										   str2+='<td style="background-color:#EDECE7"><p>'+categoryList[k].name+'('+categoryList[k].complainceDays+')';
											   if(categoryList[k].complainceDays >= categoryList[k].targetDays){
												str2+='<small style="text-align: center;"><i style="color:#3DBC93;margin-left:7px;" class="glyphicon glyphicon-ok text-danger"></i></small>';
											   }else{
													str2+='<small style="text-align: center;"><i style="color:#E35B69;margin-left:7px;" class="glyphicon glyphicon-remove text-danger"></i></small>';
											   } 
											 str2+='<div class="dropup">';
											str2+='<span class="pull-right dropdown-toggle" style="font-size: 20px; font-weight: 600; margin-top: -23px;margin-right:-6px;cursor:pointer;" id="dropdownMenu3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">&#9432;</span>';
												str2+='<div class="dropdown-menu pull-right bg_ED arrow_box_bottom" aria-labelledby="dropdownMenu3" style="padding:10px;z-index:999;width:220px !important;">';
													str2+='<p><span style="font-size: 14px; font-weight: 600; margin-top: -16px;">&#9432; </span><i style="font-size: 13px;">Tours Days Target Per Month</i></p>';
													str2+='<table class="table">';
														str2+='<tr style="background-color:#EDECE7"><td>'+categoryList[k].name+' - '+categoryList[k].targetDays+'</td></tr>';
													str2+='</table>';
												str2+='</div>';
											str2+='</div></p>';  
										   str2+='</td>';
										}
									str2+='</tr>';
								  }
							 }
							str2+='</table>';
							str2+='</div>';
							//str2+='</li>';
						   if(i == 2 || i%3 == 2){
								str2+='</div>';
							}
						 if($(window).width() < 500)
						{
							str2+='</div>';
						}
						
				}
				str2+='</div>';
				str2+='</div>';
			
			
		$("#cadreMonthWiseComplainceDivId").html(str2);
	}
	 
	 if(result !=null && result.subList3 != null && result.subList3.length > 0){
		var str1='';
		str1+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20">';
		str1='<h4 class="text-capital">MONTH WISE COMPLIANCE COMMENTS & ATTACHMENTS</h4>';
				str1+='<div class="m_top20">';
					 if($(window).width() < 500)
					{
						str1+='<div class="table-responsive">';
					}
					str1+='<table class="table table-bordered borderedWeight">';
						str1+='<thead class="bg_D8">';
							str1+='<tr>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Month & Date</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Category</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Tour Type</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">NO OF Days</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">ATTACHMENT</th>';
								str1+='<th class="text-capital text-center" style="vertical-align: middle;">Comment</th>';
							str1+='</tr>';
							str1+='<tbody>';
							  
								for(var i in result.subList3){
									str1+='<tr>';
									var moxCategoryLength = 0;
									if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
									   moxCategoryLength = result.subList3[i].subList.length;
									}
										str1+='<td rowspan='+(moxCategoryLength)+'>'+result.subList3[i].tourDate+'</td>';
										if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
											 var monthVO = result.subList3[i].subList[0];
											    if(monthVO != null){
													 if(monthVO.tourCategory == null || monthVO.tourCategory == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+monthVO.tourCategory+'</td>';
														}
														if(monthVO.tourType == null || monthVO.tourType == ""){
															str1+='<td> - </td>';
														}else{
															str1+='<td>'+monthVO.tourType+'</td>';
														}
														if(monthVO.count == null || monthVO.count==0){
															str1+='<td> - </td>';
														}else{
															str1+='<td class="text-center">'+monthVO.count+'</td>';
														}	
												}
										} 
											if(result.subList3[i].filePath != null && result.subList3[i].filePath.length > 0){
													var filePathArr = result.subList3[i].filePath.split(",");
													if(filePathArr != null && filePathArr.length > 0){
														str1+='<td rowspan='+(moxCategoryLength)+'>';
													for (var m = 0; m < filePathArr.length; m++) { 
															var fullName = filePathArr[m];
															var nameArr = fullName.split(".");
															var type = nameArr[1];
															if(type=="pdf" || type=="PDF"){
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/pdf.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type=="xls" ||type=="xlsx"){  
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/excel.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type=="doc" || type=="docx"){
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/word.jpg" class="media-object" alt="" style="width:30px;"/></span>';
															}else if(type != null){  
																str1+='<span id="showTourPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;display:inline-block;"><img src="images/fileImage.png" class="media-object" alt="" style="width:30px;"/></span>';
															}           
														}
														str1+='</td>';	
													}
												}else{    
													str1+='<td rowspan='+(moxCategoryLength)+'> - </td>';  
												} 	
												
												
												if(result.subList3[i].comment != null && result.subList3[i].comment.length > 0){
														if(result.subList3[i].comment.length > 15){
														 str1+='<td rowspan='+(moxCategoryLength)+' style="cursor:pointer;" title="'+result.subList3[i].comment+'">'+result.subList3[i].comment.substring(0,30)+'...</td>';	
														}else{
														 str1+='<td rowspan='+(moxCategoryLength)+'>'+result.subList3[i].comment+'</td>';	
														}
													}else{
													  str1+='<td rowspan='+(moxCategoryLength)+'> - </td>';	
													}
									str1+='</tr>';
										if(result.subList3[i].subList != null && result.subList3[i].subList.length > 0){
											var categoryList = result.subList3[i].subList;
												for(var k in categoryList){
													if(k == 0)
													continue;
													str1+='<tr>';
													if(categoryList[k].tourCategory == null || categoryList[k].tourCategory == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td>'+categoryList[k].tourCategory+'</td>';
													}
													if(categoryList[k].tourType == null || categoryList[k].tourType == ""){
														str1+='<td> - </td>';
													}else{
														str1+='<td>'+categoryList[k].tourType+'</td>';
													}
													if(categoryList[k].count == null || categoryList[k].count==0){
														str1+='<td> - </td>';
													}else{
														str1+='<td class="text-center">'+categoryList[k].count+'</td>';
													}
											 str1+='</tr>';
											}
										}
							
							  }
			
							str1+='</tbody>';
						str1+='</thead>';
					str1+='</table>';	
					if($(window).width() < 500)
					{
						str1+='</div>';
					}
				str1+='</div>';
			str1+='</div>';
		$("#cadreTourMonthWiseDtlsBlockDivId").html(str1); 
	}
}
$(document).on('click','#showTourPdfId',function(){
		//$("#cdrModelId").modal("show");
		var dbFilePath = $(this).attr("attr_filePath");         
		var str = ''; 
		var fileNameArr = dbFilePath.split(".");
		var extName = fileNameArr[1];
		if((navigator.userAgent.match(/iPhone/i)) ||  (navigator.userAgent.match(/iPad/i))) {
			$("#tourDocumentModalId").modal("hide");
			window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open('http://ieee802.org/secmail/docIZSEwEqHFr.doc','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			//window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}else{
			
			if(extName.trim()=="pdf" || extName.trim()=="PDF"){
				$("#tourDocumentModalId").modal("show");
	       str += '<iframe src="https://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';
				str += '</iframe>';
			}
			if(extName.trim()=="jpg"){  
				$("#tourDocumentModalId").modal("show");
				str += '<iframe src="http://mytdp.com/Reports/tour_documents/'+dbFilePath+'" width="100%" height="800">';    
				str += '</iframe>';
			}              
			if(extName.trim()=="doc" || extName.trim()=="docx"){
				$("#tourDocumentModalId").modal("show");
				str += '<iframe src="https://docs.google.com/gview?url=http://mytdp.com/Reports/tour_documents/'+dbFilePath+'&embedded=true" frameborder="0" style="width: 100%; height: 500px;">';
				str += '</iframe>';
			}
			if(extName.trim()=="xls" || extName.trim()=="xlsx"){      
				window.open('http://mytdp.com/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			}            
			$("#tourNewDocumentBodyId").html(str);
			$("#tourDocumentModalId").attr("isModalOpened","true");
			//window.open(wurl+'/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
			// window.open(wurl+'/PartyAnalyst/Reports/tour_documents/'+dbFilePath+'','toolbar=0,location=0, directories=0, status=0, menubar=0,title=Cadre Reports');
		}      
	});
	$(document).on("change","#dateRangeSliderYear",function(){
	var getYear = $(this).val();
	$("#cadreTourSlider").dateRangeSlider("destroy");
	var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"];
	$("#cadreTourSlider").dateRangeSlider({
		  bounds: {min: new Date(getYear, 0, 1), max: new Date(getYear, 11, 31)},
		//defaultValues: {min: new Date(2012, 1, 10), max: new Date(2012, 4, 22)},
		defaultValues: {min: new Date(getYear, 0,1), max: new Date(getYear,2,30)},
		scales: [{
		  first: function(value){ return value; },
		  end: function(value) {return value; },
		  next: function(value){
			var next = new Date(value);
			return new Date(next.setMonth(value.getMonth() + 1));
		  },
		  label: function(value){
			return months[value.getMonth()];
		  },
		  format: function(tickContainer, tickStart, tickEnd){
			tickContainer.addClass("myCustomClass");
		  }
		 }] 
	});
});

$(document).on("click","#subMitBtn",function(){
	var candiateId = $(this).attr("attr_candidate_id");
	var fromDateDate = $(".ui-rangeSlider-leftLabel").find(".ui-rangeSlider-label-value").html(); 
	var toDateDate = $(".ui-rangeSlider-rightLabel").find(".ui-rangeSlider-label-value").html(); 
	var frmDateInRequiredFormat;
	var toDateInRequiredFormat;
	if(fromDateDate != null && fromDateDate.length > 0){
		var fromDateArr = fromDateDate.split("-");
		frmDateInRequiredFormat =fromDateArr[2].trim()+"/"+fromDateArr[1].trim()+"/"+fromDateArr[0].trim();
	}
	if(toDateDate != null && toDateDate.length > 0){
		var toDateArr = toDateDate.split("-");
		toDateInRequiredFormat =toDateArr[2].trim()+"/"+toDateArr[1].trim()+"/"+toDateArr[0].trim();
	}
	getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat);
}); 

function getIndividualRslBasedOnDateSelection(candiateId,frmDateInRequiredFormat,toDateInRequiredFormat){
	
	$("#cadreTourIndividualDetailsBlock").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#cadreTourMonthWiseDtlsBlockDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$("#cadreMonthWiseComplainceDivId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	var jsObj ={ 
					 candiateId : candiateId,
					 fromDate :frmDateInRequiredFormat ,
					 toDate : toDateInRequiredFormat
				  }
		$.ajax({
			type : 'POST',
			url : 'getIndividualPersonTourDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#cadreTourIndividualDetailsBlock").html('');
			$("#cadreTourMonthWiseDtlsBlockDivId").html('');
			$("#cadreMonthWiseComplainceDivId").html(' ');
			buildIndividualPersonTourDetails(result);
		});
}
  /* Tour Block End */	
  
  /* Alert Description Details Block Start  */
  
    $(document).on("click",".alertsDetailsCls",function(){
		var alertId = $(this).attr('attr_alert_id');
		var alertStatus = $(this).attr("attr_status");
		//window.open('cadreAlertDetailsAction.action?alertId='+attr_alert_id+'&status=false');
		$("#tourDocHeadingId").html("");
		$("#cdrModelId").html("");
		$("#alertDestId").html("");
		$("#sourceHeadingId").html("");
		$("#headingNameId").html("");
		$("#alertAttachTitId").html("");
		$("#alertAttachImgId").html("");
		$("#alertInvolvedCandidates").html("");
		$("#alertAssignedCandidates").html("");
		$("#alertCommentsDiv").html("");
		$("#tourDocHeadingId").html("ALERT TITLE <br>");
		$("#alertVerificationDiv").html("");
		$("#alertVerificationDtlsDiv").html("");
		
		$("#alertDocHeadingId").html("");
		$("#alertDocId").html("");
		
		  $("#cdrModelDivId").modal("show");
		var alertId = $(this).attr("attr_alert_id");
		var alertStatus = $(this).attr("attr_alert_status");
		getAlertData(alertId);
		getAlertAssignedCandidates(alertId);    
		getAlertStatusCommentsTrackingDetails(alertId,alertStatus);
		getVerificationDtls(alertId);
	});
	
  function getAlertData(alertId){    
		var jsObj ={
			alertId  :alertId,
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertsDataAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildAlertData(result);
			}    
		});
	}
	function buildAlertData(result){
		var docName = '';
		var extName =[];
		$("#tourDocHeadingId").html("<h5 style='color:#FFFFFF;font-size:14px;'>ALERT TITLE</h5><h5 class='text-capital m_top10' style='color:#000'>"+result[0].title+"</h5>");
		$("#cdrModelId").html("<h5 class='text-muted headingColorStyling'>ALERT DESCRIPTION</h5>");
		$("#alertDestId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 6px;'>"+result[0].desc+"</p>");
		$("#sourceHeadingId").html("<h5 class='text-muted headingColorStyling'>ALERT SOURCE</h5>");
		$("#headingNameId").html("<p style='border: 1px solid rgb(211, 211, 211); padding: 10px;'>"+result[0].alertSource+"</p>");
		
		if(result[0].documentList != null && result[0].documentList.length >= 1){
			$("#alertDocHeadingId").html("<h5  class='text-muted headingColorStyling'>ALERT DOCUMENTS</h5>");
			var docStr = '';
			docStr+='<ul>';
			for(var i in result[0].documentList){
				docName = result[0].documentList[i];
				extName = docName.split("/");
				docStr+='<li id="document0'+i+'"><a href="/Reports/'+result[0].documentList[i]+'" target="_blank">'+extName[1]+'</a></li>';
			}
			docStr+='</ul>';
			$("#alertDocId").html(docStr);    
		}
		if(result[0].imageUrl != null && result[0].imageUrl.length > 1){    
			$("#alertAttachTitId").html("<h5  class='text-muted headingColorStyling'>ALERT ATTACHMENTS</h5>");
			var imgStr = '';
			imgStr+='<ul class="list-inline imageUrlUlCls" style="border: 1px solid rgb(211, 211, 211); padding:5px;">';
			imgStr+='<li><img src="http://mytdp.com/NewsReaderImages/'+result[0].imageUrl+'" style="width: 90px; height: 90px;cursor:pointer;" class="articleImgDetailsCls" attr_articleId="'+result[0].alertCategoryTypeId+'"></img></li>';
			imgStr+='</ul> '; 
			$("#alertAttachImgId").html(imgStr);  
		}
		var str='';
		var invCandCnt = 0;
		if(result[0].subList.length > 0){
			for(var i in result){
				for(var j in result[i].subList){
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){    
						invCandCnt+=1;
					}
				}    
			}
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+invCandCnt+'</h5>';           
			str+='<ul class="list-inline assignedCandidatesUl1">';     
			for(var i in result){
				for(var j in result[i].subList){   
					if(result[i].subList[j].name != null && result[i].subList[j].name.length > 1){
						str+='<li>';      
							str+='<p style="color: rgb(0, 0, 0);"><b>'+result[i].subList[j].name+'</b></p>';
							if(result[i].subList[j].mobileNo.length <= 1  || result[i].subList[j].mobileNo == null){
							}else{
								str+='<p><i> - </i>'+result[i].subList[j].mobileNo+'</p>';      
							}  
							if(result[i].subList[j].committeePosition != null){
								str+='<p><i> - </i>'+result[i].subList[j].committeePosition+'</p>';  
							}     
						str+='</li>';      
					}
				}    
			}
			str+='</ul>';      
			
			$("#alertInvolvedCandidates").html(str);       
		}else{
			str+='<h5 class="text-muted text-capital headingColorStyling">Involved Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>'; 
			$("#alertInvolvedCandidates").html(str);        
		}
		$(".assignedCandidatesUl1").slick({          
			 slide: 'li',
			 slidesToShow: 4,
			 slidesToScroll: 3,    
			 infinite: false,
			  responsive: [
				{
				  breakpoint: 1024,
				  settings: {
					slidesToShow: 5,
					slidesToScroll: 3,
					infinite: false,
					dots: false
				  }
				},
				{
				  breakpoint: 800,
				  settings: {
					slidesToShow: 3,
					slidesToScroll: 2
				  }
				},
				{
				  breakpoint: 600,
				  settings: {
					slidesToShow: 2,
					slidesToScroll: 1
				  }
				},
				{
				  breakpoint: 480,
				  settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				  }
				}
				
			  ]  
		}); 
	}
	function getAlertAssignedCandidates(alertId){
		GlobalAlertData = [];
		var jsObj ={
			alertId  : alertId,    
			task : ""
		}
		$.ajax({
			type:'GET',
			url: 'getAlertAssignedCandidatesAction.action',
			data: {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){   
				buildAlertAssignedCandidates(result);  
			}else{
				var str = '';
				str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
				$("#alertAssignedCandidates").html(str);    
			}
		});
	}
	function buildAlertAssignedCandidates(result)
	{
	var str='';
	if(result[0].subList.length > 0){  
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;'+result[0].subList.length+'</h5>';
		str+='<ul class="list-inline assignedCandidatesUl">';
		for(var i in result)
		{
			for(var j in result[i].subList)
			{
				str+='<li>';
					str+='<p style="color:#000"><b>'+result[i].subList[j].name+'</b></p>';
					if(result[i].subList[j].committeePosition == null || result[i].subList[j].committeePosition.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].committeePosition+'</i></p>';
					}
					if(result[i].subList[j].mobileNo == null || result[i].subList[j].mobileNo.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].mobileNo+'</i></p>';
					}
					if(result[i].subList[j].locationVO.districtName == null || result[i].subList[j].locationVO.districtName.length <= 1){     
					}else{
						str+='<p><i> - '+result[i].subList[j].locationVO.districtName+'</i></p>';
					}  
				str+='</li>';
			}
		}
		str+='</ul>';
		
		$("#alertAssignedCandidates").html(str);
	}else{
		str+='<h5 class="text-muted text-capital headingColorStyling">Assigned Candidates&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;<strong>0</strong></h5>';  
		$("#alertAssignedCandidates").html(str);                    
	}
	
	$(".assignedCandidatesUl").slick({
		 slide: 'li',
		 slidesToShow: 4,
		 slidesToScroll: 3,
		 infinite: false,
		  responsive: [
			{
			  breakpoint: 1024,
			  settings: {
				slidesToShow: 5,
				slidesToScroll: 3,
				infinite: false,
				dots: false
			  }
			},
			{
			  breakpoint: 800,
			  settings: {
				slidesToShow: 3,
				slidesToScroll: 2
			  }
			},
			{
			  breakpoint: 600,
			  settings: {
				slidesToShow: 2,
				slidesToScroll: 1
			  }
			},
			{
			  breakpoint: 480,
			  settings: {
				slidesToShow: 1,
				slidesToScroll: 1
			  }
			}
			
		  ]
	});  
}

 function getAlertStatusCommentsTrackingDetails(alertId,alertStatus){  
		var jsObj={
			alertId:alertId,
			task:""
		}
		$.ajax({  
			type : 'GET',
			url : 'getAlertStatusCommentsTrackingDetails.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			if(result != null)           
				buildAlertStatusCommentsTrackingDetails(result,alertStatus);    
		});
	}
	function buildAlertStatusCommentsTrackingDetails(result,alertStatus)
{
	var docName = '';
	var extName = [];
	$("#alertStatusDiv").html("<h4 class='text-muted headingColorStyling' style='font-size:15px;'>ALERT STATUS</h4>");          
	if(result != null && result.length > 0){  
		var length = result.length;
		length = length - 1;
		var str='';  
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';  
			for(var i in result)
			{
				
				if(length != i){
					str+='<li class="m_top10" role="presentation"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span class="glyphicon glyphicon-ok  pull-right" style="font-size: 22px;color: #777 !important;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'</span></a></li>';
				}else{
					str+='<li role="presentation" class="active m_top10"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab"><span>'+result[i].status+'</span><span  class="glyphicon glyphicon-hourglass pull-right" style="font-size: 22px;color: #777 !important;margin-left: 15px;"></span><br/><span class="color_FF">'+result[i].sublist2[0].date+'<span></a></li>';
				}        
				
			}
			str+='</ul>';
			str+='<div class="tab-content alertComment">';
				for(var i in result)
				{
					if(length != i)
					{
						str+='<div role="tabpanel" class="tab-pane " id="commentStatus'+i+'">';
					}else{
						str+='<div role="tabpanel" class="tab-pane active" id="commentStatus'+i+'">';
					}
					for(var j in result[i].sublist2)
					{
						str+='<div class="row m_top10">';
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date      
								var dateArr = date.split("-");
								var year = dateArr[0];  
								var month = dateArr[1];
								var day = dateArr[2];
								str+='<table class="table tableCalendar">';
									str+='<tr>';
										str+='<td colspan="2">';
											str+='<h3>'+day+'</h3>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>'+getMonth(month)+'</td>';        
										str+='<td>'+year+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-10 col-xs-12 col-sm-10" style="padding-left:0px;">';
								str+='<ul class="alertStatusTracking">';
									str+='<li>';  
										str+='<div class="arrow_box_left" style="background: #f5f3f8 none repeat scroll 0 0 !important;">';
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE&nbsp;&nbsp;:&nbsp;</span>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span> <span>'+result[i].sublist2[j].sublist[k][l].cadreName+'</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 18px;">|</span>';
												}   
												str+='&nbsp;&nbsp;&nbsp;&nbsp; <small style="font-size:11px">'+result[i].sublist2[j].sublist[k][0].timeString+'</small>';
												str+='</p>';  
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p class="m_top10">'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
												if(result[i].sublist2[j].sublist[k][0].docList != null && result[i].sublist2[j].sublist[k][0].docList.length > 0){
													str+='<p><span style="color:#A286C0;font-size:13px;">DOCUMENTS:</span><br>';
													str+='<ul>';
													for(var t in result[i].sublist2[j].sublist[k][0].docList){
														docName = result[i].sublist2[j].sublist[k][0].docList[t].name;
														extName = docName.split(".");
														str+='<li id="document'+result[i].id+'"><a href="/Reports/'+result[i].sublist2[j].sublist[k][0].docList[t].name+'" target="_blank">Attachment.'+extName[1]+'</a></li>';
													}
													str+='</ul>';    
												}
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
												str+='<hr style="margin-top:20px;border-color:#a792d2 -moz-use-text-color -moz-use-text-color;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						str+='</div>';
					}           
				str+='</div>';
				}
			str+='</div>';
		str+='</div>';
		$("#alertCommentsDiv").html(str);
	}else{
		var str = '';
		var statusArr = {"1":"Pending","2":"Notified","3":"Action In Progess","4":"Completed","5":"Unable To Resolve","6":"Action Not Required","7":"Duplicate"};            
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
		str+='<ul class="nav nav-tabs alertCommentUl" role="tablist">';
		for(var i = 1 ; i <= 7 ; i++){
			if(alertStatus == statusArr[i]){
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<span class="glyphicon glyphicon-ok"></span><br/></a></li>';
			}else{
				str+='<li class="m_top10" role="presentation" style="pointer-events: none;"><a href="#commentStatus'+i+'" aria-controls="commentStatus'+i+'" role="tab" data-toggle="tab">'+statusArr[i]+'<br/></a></li>';
			}
		}
		str+='</ul>';       
		str+='<div class="tab-content alertComment">';    
		$("#alertCommentsDiv").html(str);       
	}//glyphicon glyphicon-ok
	//alertStatus
}
  
	function getVerificationDtls(alertId){
		var jsObj={
			alertId:alertId
		}
        $.ajax({
        type : 'POST',
        url : 'getAlertVerificationDetailsAction.action',
        dataType : 'json',
        data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#converSationDtlsDivId").html(' ');
			buildAlertVerificationStatusRlst(result);
		});
	}
	
	function buildAlertVerificationStatusRlst(result){
		var str = '';
		if(result.conversationList != null && result.conversationList.length > 0){
		//	$("#alertVerificationDiv").html("<h4 class='text-muted verifyHeadingColorStyling' style='font-size:15px;'>VERIFICATION STATUS-"+result.actionTypeStatus+"</h4>");  
			$("#alertVerificationDiv").html("<div class='row'><h4 class='col-md-6 text-muted verifyHeadingColorStyling' style='font-size:15px;margin-left:16px'>ACTION TYPE- VERIFICATION</h4><h4 class='col-md-6 text-muted verifyHeadingColorStyling pull-right' style='font-size:15px;margin-right:16px'>STATUS - "+result.actionTypeStatus.toUpperCase()+"</h4></div>");  
			for(var i in result.conversationList){
				str+='<p class="text-capital panelTitleFont m_top20 verifyHeadingColorStyling" style="font-size:12px;">'+result.conversationList[i].heading+'</p>';  
				if(result.conversationList[i].comments != null && result.conversationList[i].comments.length > 0){
					str+='<p style="border: 1px solid rgb(211, 211, 211); padding: 6px;">'+result.conversationList[i].comments+'</p>';     
				}
				var documentList = result.conversationList[i].documentList;
				if(documentList != null && documentList.length > 0){
					str+='<p style="font-weight:bold;font-size:12px;" class="text-capital m_top10 panelTitleFont headingColorStyling">Attachments</p>';
					str+='<ul class="attachmentsBlock">';
					var order = 0;
					for(var k in documentList){
						order = order+1;
						var fullName = documentList[k];
						var nameArr = fullName.split(".");
						var type = nameArr[1];  
						var orderStr='';
						if(k<9){
							orderStr ="0"+order;
						}else{
							orderStr = order;  
						}
						var attachment = orderStr+'&nbspAttachment.'+type;
						str+='<li id="showAlertVerificationPdfId" attr_filePath="'+fullName+'" style="cursor:pointer;"><i class="glyphicon glyphicon-paperclip"></i><span class="border"> '+attachment+' </span></li>';
					}
					str+='</ul>';
				}
				if(result.conversationList[i].name != null && result.conversationList[i].name.length > 0){
					str+='<p class="text-right" style="color:#7155D6;font-size:12px;">Created By:'+result.conversationList[i].name+'('+result.conversationList[i].updateTime+'&nbsp'+result.conversationList[i].time+')</p>';     
				}  
			}
			str+='<hr class="m_top10" style="border-top: 1px solid #ccc;">';
			$("#alertVerificationDtlsDiv").html(str);
		}
   }
   function getMonth(month){
	if(month=="01"){
		return "Jan"
	}else if(month=="02"){
		return "Feb"
	}else if(month=="03"){
		return "Mar"
	}else if(month=="04"){
		return "Apr"
	}else if(month=="05"){
		return "May"
	}else if(month=="06"){
		return "Jun"
	}else if(month=="07"){
		return "Jul"
	}else if(month=="08"){
		return "Aug"
	}else if(month=="09"){
		return "Sep"
	}else if(month=="10"){
		return "Oct"
	}else if(month=="11"){
		return "Nov"
	}else if(month=="12"){  
		return "Dec"
	}  
}
$(document).on("click",".articleImgDetailsCls",function(){
	var articleId= $(this).attr("attr_articleId");
	getTotalArticledetails(articleId);
});

function getTotalArticledetails(articleId){
	$("#myModalShowNew").modal('show');
	$("#myModalShowNewId").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	
	var url = window.location.href;
	  var wurl = url.substr(0,(url.indexOf(".com")+4));
	  if(wurl.length == 3)
	  wurl = url.substr(0,(url.indexOf(".in")+3));
	  $.ajax({       
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		  
		  //url: "http://mytdp.com/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""          
		  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""     
	}).then(function(results){
			var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","Muncipality/Corporation/GHMC/GVMC","Ward"];
				var result = results[0];
				var str = '';
					str+='<div class="modal-header">';
					str+='<h4 class="modal-title" id="myModalLabel">';
					str+='<button type="button" class="close topModalClose" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
						str+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
					str+='</h4>';
					str+='</div>';
					str+='<div class="modal-body">';
					str+='<div class="row">';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
						str+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
						str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;border:1px solid #ddd;width:100%" alt="Img Title"/>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str+='<h4 class="panel-title text-success">Description</h4>';
					str+='<p class="m_0 f_14">'+result.description+'</p>';
					str+='</div>';
					str+='<div class="col-md-12 col-xs-12 col-sm-12">';
					if( result.subList != null && result.subList.length > 0){
						for(var i in result.subList){
							/* Candidate*/
							str+='<div class="row m_top10">';
							str+='<div class="col-md-6 col-xs-12 col-sm-12">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].fromList[j].candidateName;
										}
										if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
											candidataExist = true; 
											str+=' ('+result.subList[i].fromList[j].designation + ")";
										}
										if(!candidataExist){
											str+=' - ';
										}
										str+='</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
											str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
										}else{ 
											str+='<p class="m_0">Impact Level : - </p>';	
										}
										if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
											str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
										}else{ 
											str+='<p class="m_0">Category : - </p>';	
										}
										if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
											str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
										}else{ 
											str+='<p class="m_0">News Activity : - </p>';	
										}
										if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
											str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
										}else{ 
											str+='<p class="m_0">News type : - </p>';	
										}
										if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
											if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
												str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
											}else{ 
												str+='<p class="m_0">News Related : - </p>';	
											}
											if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
												str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
											}else{ 
												str+='<p class="m_0">Priority : - </p>';	
											}
											if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
												str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
											}else{ 
												str+='<p class="m_0">Solution : - </p>';	
											}
										}
										str+='</td>';
										str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
							str+='</div>';//panel
							str+='</div>';//colmd6
							str+='<div class="col-md-6 col-xs-12  col-sm-12">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* TO Table*/
								if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
									for( var j in result.subList[i].toList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
										}else{
											str+='<td> - </td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].toList[j].candidateName;
																			}
																			if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
																				candidataExist = true; 
																				str+=' ('+result.subList[i].toList[j].designation + ")";
																			}
																			if(!candidataExist){
																				str+=' - ';
																			}
																		   str+='</td>';
																	str+='</tr>';
																	str+='<tr>';
																		str+='<td colspan="2">';
																		    
																			if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
																			  str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Impact Level : - </p>';	
																			}
																		
																		    if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
																			  str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Category : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
																			  str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News Activity : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
																			  str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News type : - </p>';	
																			}
																			if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){
																				
																				if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
																				  str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
																				}else{ 
																				  str+='<p class="m_0">News Related : - </p>';	
																				}
																				if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
																				  str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Priority : - </p>';	
																				}
																				if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
																				  str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Solution : - </p>';	
																				}
																			}
																		str+='</td>';
																	str+='</tr>';
																str+='</table>';
															}
														}
														
													str+='</div>';//panelbody
												str+='</div>';//panel
											str+='</div>';//colmd6
											
										str+='</div>';//row
								  }
								}
								
								str+='</div>';//colmd12
							str+='</div>';//row
								/* Article Scope Location */
								str+='<div class="col-md-12 col-xs-12 col-sm-12">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed">';
												str+='<tr>';
													str+='<td>Impact Scope : </td>';
													if(result.impactScopeId!=null){
														str+='<td>'+obj[result.impactScopeId]+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
												str+='<tr>';
													str+='<td>Location : </td>';
													if(result.scopeLocation!=null){
														str+='<td>'+result.scopeLocation+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
											str+='</table>';       
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="row">';
							/*Lnking*/
							str+='</div>';  
					$("#myModalShowNewId").html(str);
		});    
} 	
   /* Alert Description Details Block End  */

getSurveyQuestionWithMarksDetailsByTDpCadreId();
function getSurveyQuestionWithMarksDetailsByTDpCadreId(){

	var jsObj={
			tdpCadreId :"7174953"
	}	
	$.ajax({
		 type: "POST",
		 url: "getSurveyQuestionWithMarksDetailsByTDpCadreIdAction.action",
		 data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length>0){
				var str="";
				var str1="";
		//for(var i in result){
			str+='<label>'+result[0].surveyName+' : </label>';
		//}		
		str+='<table class="table table-bordered table-condensed" id="normalSurveyTableId">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>Question</th>';
					str+='<th>Option</th>';
					//str+='<th>Marks</th>';
					str+='</tr>';
			str+='</thead>';
			str+='<tbody>';	
					for(var j in result){
							for(var i in result[j].subList){
								if(result[j].surveyTypeId == 5){
								str+='<tr>';
										str+='<td>'+result[j].subList[i].question+'</td>';
										if(result[j].subList[i].name != null && result[j].subList[i].name !=""){
										str+='<td>'+result[j].subList[i].name+'</td>';
										}else{
											str+='<td>-</td>';
										}
								str+='</tr>';				
							  }
						}
					}			
				
		str+='</tbody>';
		str+='</table>';
		$("#normalSurveyDiv").html(str);/* 
		$('#normalSurveyTableId').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
		}); */
		//for(var i in result){
			str1+='<label>'+result[1].surveyName+' :</label>';
		//}	
			str1+='<table class="table table-bordered table-condensed" id="quizSurveyTableId">';
			str1+='<thead>';
				str1+='<tr>';
					str1+='<th>Question</th>';
					str1+='<th>Given Option</th>';
					str1+='<th>Marks</th>';
					str1+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var j in result){
				for(var i in result[j].subList){
						if(result[j].surveyTypeId ==7){
						str1+='<tr>';
								str1+='<td>'+result[j].subList[i].question+'</td>';
									if(result[j].subList[i].name != null && result[j].subList[i].name !=""){
										str1+='<td>'+result[j].subList[i].name+'</td>';
									}else{
										str1+='<td>-</td>';
									}
									str1+='<td>'+result[j].subList[i].marks+'</td>';
						str1+='</tr>';	
					}
				}
			}
				
		str1+='</tbody>';
		str1+='</table>';
			$("#quizSurveyDiv").html(str1);
			/* $('#quizSurveyTableId').dataTable({
		"aaSorting": [[ 0, "asc" ]],
		"iDisplayLength": 15,
		"aLengthMenu": [[15, 50, 100, -1], [15, 50, 100, "All"]]
		}); */
		}	
		});
}		