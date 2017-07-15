/*
NotStarted
Started
Completed
*/

	var userFromDate=moment().startOf('month').format("DD/MM/YYYY");
    var userToDate=moment().endOf('month').format("DD/MM/YYYY");
	
	$('#daterangePickerId').daterangepicker({
		opens: 'left',
		startDate: userFromDate,
		endDate: userToDate,
		locale: {
			  format: 'DD/MM/YYYY'
			},
		ranges: {
			'All':[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
			'Today' : [moment(), moment()],
		   'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		   'Last 3 Months': [moment().subtract(3, 'month'), moment()],
		   'Last 6 Months': [moment().subtract(6, 'month'), moment()],
		   'Last 1 Year': [moment().subtract(1, 'Year'), moment()],
		   'This Month': [moment().startOf('month'), moment()],
		   'This Year': [moment().startOf('Year'), moment()]
		}
	});
	$('#daterangePickerId').on('apply.daterangepicker', function(ev, picker) {
		userFromDate = picker.startDate.format('DD/MM/YYYY');
		userToDate = picker.endDate.format('DD/MM/YYYY');
	});

var blockNames = ["DISTRICT","CONSTITUENCY","MANDAL","PANCHAYAT"];
var stateLevel="STATE";
getOverAllBoothDetails(stateLevel);
getLocationLevelWiseBoothCount("DISTRICT","dstrctParlmntLvlBoothDtlsDivId");
getLocationLevelWiseBoothCount("CONSTITUENCY","constituencyLevelBoothDtlsDivId");
getLocationLevelWiseBoothCount("TEHSIL","mandalLevelBoothDtlsDivId");
getLocationLevelWiseBoothCount("PANCHAYAT","panchaytLevelBoothDtlsDivId");
getLocationBasedOnSelection();
getLocationLevelWiseBoothDetails();
validateBoothToMakeConfirm(); 

$(document).on("click",".districtLevelCls",function(){
	var selecteLegel = $(this).attr("attr_level_value");
	var levelHeading = selecteLegel+" WISE";
	$(".districtParliamentLevleHadingCls").html(levelHeading);
	getLocationLevelWiseBoothCount(selecteLegel,"dstrctParlmntLvlBoothDtlsDivId");
})

 $(document).on('click','.table-menu li',function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var subBlockNames = ["DISTRICT","PARLIAMENT"];
	for(var i in subBlockNames){
		if(subBlockNames[i] == 'DISTRICT'){
			$("#selectConstituencyDistrict").show();
		}
		else if(subBlockNames[i] == 'PARLIAMENT'){
			$("#selectParliament").show();
			$("#selectConstituencyDistrict").hide();
		}
	}
}); 

function getOverAllBoothDetails(locationLevel){
	$("#ajaximageId").show();
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : "",
		filterValue : 0,
		boothInchargeEnrollmentId : 1,
		startDate : userFromDate,
		endDate : userToDate
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothCountAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		$("#ajaximageId").hide();
	 	  buildOverAllBoothDtls(result);
	});

}
function buildOverAllBoothDtls(result){
	if(result != null && result.length > 0){
		 var overallDataObj = result[0];
		 var rangevoterArr = overallDataObj.subList;
		 if(overallDataObj != null){
			 var str='';
			 str+='<div class="col-sm-12 blocks">';
                        str+='<div class="col-sm-3">';
							str+='<div class="subBlock text-center">';
                                str+='<p>TOTAL BOOTHS</p>';
								if(overallDataObj.totalBoothCount != null){
									 str+='<h4 class="text-center;">'+overallDataObj.totalBoothCount+'</h4>';
								}else{
									 str+='<h4 class="text-center;"> - </h4>';
								}
                               
							str+='</div>';
                        str+='</div>';
                        str+='<div class="col-sm-3">';
                            str+='<div class="subBlock text-center">';
                           str+=' <p>STARTED BOOTHS</p>';
							  if(overallDataObj.startedBoothCount != null){
									 str+='<h4 class="text-center;">'+overallDataObj.startedBoothCount+'</h4>';
								}else{
									 str+='<h4 class="text-center;"> - </h4>';
								}
                            str+='</div>';
                        str+='</div>';
                        str+='<div class="col-sm-3">';
                            str+='<div class="subBlock text-center">';
                            str+='<p>NOT-STARTED BOOTHS</p>';
                              if(overallDataObj.notStartedBoothCount != null){
									 str+='<h4 class="text-center;">'+overallDataObj.notStartedBoothCount+'</h4>';
								}else{
									 str+='<h4 class="text-center;"> - </h4>';
								}
                           str+=' </div>';
                       str+=' </div>';
                        str+='<div class="col-sm-3">';
                            str+='<div class="subBlock text-center">';
                           str+=' <p>COMPLETED BOOTHS</p>';
                             if(overallDataObj.completedBoothCount != null){
									 str+='<h4 class="text-center;">'+overallDataObj.completedBoothCount+'</h4>';
								}else{
									 str+='<h4 class="text-center;"> - </h4>';
								}
                            str+='</div>';
                        str+='</div>';
                    str+='</div>';
				$("#overAllBoothDlstDivId").html(str);
		 }else{
			 $("#overAllBoothDlstDivId").html('NO DATA AVAILABLE.');
		 }
		 
		 if(rangevoterArr != null && rangevoterArr.length > 0){
			 var str = '';
			  str+='<ul class="">';
			 for(var i in rangevoterArr){
			   str+=' <li>';
					str+='<p>'+rangevoterArr[i].roleName+'</p>';
				     if(rangevoterArr[i].count > 0){
						 str+='<h4>'+rangevoterArr[i].count+'</h4>';
					 }else{
						 str+='<h4>-</h4>';
					 }
				str+='</li>';
           }
			 str+'</ul>';
			 $("#overAllSerialRangeWiseVoterDivId").html(str);
		 }else{
			$("#overAllSerialRangeWiseVoterDivId").html('NO DATA AVAILABLE'); 
		 }
	}else{
		$("#overAllBoothDlstDivId").html('NO DATA AVAILABLE.');
		$("#overAllSerialRangeWiseVoterDivId").html('NO DATA AVAILABLE'); 
	}
}
function getLocationLevelWiseBoothCount(locationLevel,divId){
    $("#ajaximageId").show();
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : "",
		filterValue : 0,
		boothInchargeEnrollmentId : 1,
		startDate : userFromDate,
		endDate : userToDate
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothCountAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		$("#ajaximageId").hide();
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
		str+='<th rowspan="2">DISTRICT</th>';
	}
	if(locationLevel=="PARLIAMENT CONSTITUENCY" || locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">PARLIAMENT CONSTITUENCY</th>';
	} 
	if(locationLevel=="CONSTITUENCY" || locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">CONSTITUENCY</th>';
	}
	if(locationLevel=="TEHSIL" || locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">TEHSIL</th>';
	}
	if(locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">PANCHAYAT</th>';
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

$("#constituencyWiseParliamentId").hide();
$("#mandalWiseParliamentId").hide();
$("#villegeWiseParliamentId").hide();
$("#constituencyWiseDistrictId").show();
$(document).on('click','#constituencyDistrictId',function(){
	
	$("#constituencyWiseDistrictId").show();
	$("#constituencyWiseParliamentId").hide();	
	});

$(document).on('click','#constituencyParliamentId',function(){
	
	$("#constituencyWiseDistrictId").hide();
	$("#constituencyWiseParliamentId").show();
	});

$(document).on('click','#mandalDistrictId',function(){
	$("#mandalWiseDistrictId").show();
	$("#mandalWiseParliamentId").hide();	
	});

$(document).on('click','#mandalParliamentId',function(){
	$("#mandalWiseDistrictId").hide();
	$("#mandalWiseParliamentId").show();
	});
	
$(document).on('click','#panchayatDistrictId',function(){
	$("#villegeWiseDistrictId").show();
	$("#villegeWiseParliamentId").hide();	
	});

$(document).on('click','#panchayatParliamentId',function(){
	$("#villegeWiseDistrictId").hide();
	$("#villegeWiseParliamentId").show();
	});