/*
DISTRICT
PARLIAMENT CONSTITUENCY
CONSTITUENCY
TEHSIL
PANCHAYAT

NotStarted
Started
Completed
*/
getLocationLevelWiseBoothCount("DISTRICT","districtLevelBoothDtlsDivId");
getLocationLevelWiseBoothCount("CONSTITUENCY","constituencyLevelBoothDtlsDivId");
getLocationLevelWiseBoothCount("TEHSIL","mandalLevelBoothDtlsDivId");
getLocationLevelWiseBoothCount("PANCHAYAT","panchaytLevelBoothDtlsDivId");
//getLocationBasedOnSelection();
//getLocationLevelWiseBoothDetails();
//validateBoothToMakeConfirm();
function getLocationLevelWiseBoothCount(locationLevel,divId){

	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : "",
		filterValue : 0,
		boothInchargeEnrollmentId : 1,
		startDate : "13/07/2017",
		endDate : "13/07/2017"
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothCountAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	 	  buildLocationLevelWiseBoothDtls(result,locationLevel,divId);
	});
}
 function buildLocationLevelWiseBoothDtls(result,locationLevel,divId){
	if(result != null && result.length > 0){
		 var rangeArr = result[0].subList;
		 var str = '';
		 str+='<div class="table-responsive">';
		 str+='<table class="table table-bordered" id="'+divId+'dataTableId">';
                       str+='<thead>';
                            str+='<tr>';
							var locationSpecificHeadingStr = getLocationSpeceficHeading(locationLevel);
							console.log(locationSpecificHeadingStr);
							str = str +" "+locationSpecificHeadingStr;
                                str+='<th rowspan="2">TOTAL BOOTHS</th>';
								str+='<th rowspan="2">NOT-STARTED BOOTHS</th>';
                                str+='<th rowspan="2">STARTED BOOTHS</th>';
								str+='<th rowspan="2">COMPLETED BOOTHS</th>';
								str+='<th colspan="11" class="text-center">BOOTH COMMITTEE MEMBERS VOTER ID SERIAL NUMBERS WISE</th>';
								if(rangeArr != null && rangeArr.length > 0){
									str+='<tr>';
									for(var i in rangeArr){
										str+='<th>'+rangeArr[i].roleName+'</th>';
									}
									str+='</tr>';
								}
					        str+='</tr>';
                       str+='</thead>';
					     str+='<tbody>';
                            for(var i in result){
								str+='<tr>';
								   var boothAddressStr = getLocationWiseBoothAddress(locationLevel,result[i].boothAddressVO);
								     str = str +" "+boothAddressStr;
									if(result[i].totalBoothCount != null){
										str+='<td class="text-center;">'+result[i].totalBoothCount+'</td>';
									}else{
										str+='<td class="text-center;">-</td>';	
									}
								    if(result[i].notStartedBoothCount != null){
										str+='<td class="text-center;">'+result[i].notStartedBoothCount+'</td>';
									}else{
										str+='<td class="text-center;">-</td>';	
									}
									if(result[i].startedBoothCount != null){
										str+='<td class="text-center;">'+result[i].startedBoothCount+'</td>';
									}else{
										str+='<td class="text-center;">-</td>';	
									}
									if(result[i].completedBoothCount != null){
										str+='<td class="text-center;">'+result[i].completedBoothCount+'</td>';
									}else{
										str+='<td class="text-center;">-</td>';	
									}
									if(result[i].subList != null && result[i].subList.length > 0){
										for(var j in result[i].subList){
											if(result[i].subList[j].count > 0){
											  str+='<td>'+result[i].subList[j].count+'</td>';	
											}else{
											   str+='<td>-</td>';		
											}
											 
										}
									}
							str+='</tr>';
                           }
                     str+='</tbody>';
			str+='</table>';
		str+='</div>';
	 $("#"+divId).html(str);	
	 $("#"+divId+'dataTableId').dataTable();
	}else{
	  $("#"+divId).html('NO DATA AVAILABLE.')	
	}
} 
function getLocationSpeceficHeading(locationLevel){
	var str = '';
	if(locationLevel=="DISTRICT" || locationLevel=="PARLIAMENT CONSTITUENCY" || locationLevel=="PARLIAMENT CONSTITUENCY" || locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">DISTRIC NAME</th>';
	}
	if(locationLevel=="PARLIAMENT CONSTITUENCY" || locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">PARLIAMENT CONSTITUENCY NAME</th>';
	} 
	if(locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">CONSTITUENCY NAME</th>';
	}
	if(locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">TEHSIL NAME</th>';
	}
	if(locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">PANCHAYAT NAME</th>';
	}
	return str;
}
function getLocationWiseBoothAddress(locationLevel,addressObj){
	var str = '';
	if(locationLevel=="DISTRICT" || locationLevel=="PARLIAMENT CONSTITUENCY" || locationLevel=="PARLIAMENT CONSTITUENCY" || locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<td>'+addressObj.districtName+'</td>';
	}
	if(locationLevel=="PARLIAMENT CONSTITUENCY" || locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<td>'+addressObj.parliamentConstituency+'</td>';
	}
	if(locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<td>'+addressObj.constituencyName+'</td>';
	}
	if(locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<td>'+addressObj.tehsilName+'</td>';
	}
	if(locationLevel=="PANCHAYAT"){
		str+='<td>'+addressObj.panchayat+'</td>';
	}
	return str;
}
function getLocationBasedOnSelection(){
	var jsObj={  
		locationLevel : "Constituency",         
		filterLevel : "District",
		filterValue : 19,
		boothInchargeEnrollmentId : 1,
	
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationBasedOnSelectionAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}

function getLocationLevelWiseBoothDetails(){

	var jObj={  
		filterType : "Tehsil",
		filterValue : "1240",
		fromDate : "13/07/2017",
		toDate : "13/07/2017",
		boothEnrollementYearId : 1,
		resultType : "NotStarted"
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}

function validateBoothToMakeConfirm(){

	var jObj = {  
			boothId : 922852,
			boothInchargeEnrollmentId : 1
		} 
	$.ajax({
		type : 'POST',
		url : 'validateBoothToMakeConfirmAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	  console.log(result);
	});
}