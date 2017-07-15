
    var globalBoothInchargeEnrollmentId=1;
	var globalFromDate=moment().startOf('month').format("DD/MM/YYYY");
    var globalToDate=moment().endOf('month').format("DD/MM/YYYY");
	
	$('#daterangePickerId').daterangepicker({
		opens: 'left',
		startDate: globalFromDate,
		endDate: globalToDate,
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
		globalFromDate = picker.startDate.format('DD/MM/YYYY');
		globalToDate = picker.endDate.format('DD/MM/YYYY');
		getOverAllBoothDetails("STATE");
		getLocationLevelWiseBoothCount("DISTRICT",filterLevel,filterValue,"dstrctParlmntLvlBoothDtlsDivId");
		getLocationLevelWiseBoothCount("CONSTITUENCY",filterLevel,filterValue,"constituencyLevelBoothDtlsDivId");
		getLocationLevelWiseBoothCount("TEHSIL",filterLevel,filterValue,"mandalLevelBoothDtlsDivId");
		getLocationBasedOnSelection("DISTRICT",filterLevel,filterValue,"","All","");
	});

var filterLevel="";
var filterValue=0;
getOverAllBoothDetails("STATE",filterLevel,filterValue);
getLocationLevelWiseBoothCount("DISTRICT",filterLevel,filterValue,"dstrctParlmntLvlBoothDtlsDivId");
getLocationLevelWiseBoothCount("CONSTITUENCY",filterLevel,filterValue,"constituencyLevelBoothDtlsDivId");
getLocationLevelWiseBoothCount("TEHSIL",filterLevel,filterValue,"mandalLevelBoothDtlsDivId");
//getLocationLevelWiseBoothCount("PANCHAYAT",filterLevel,filterValue,"panchaytLevelBoothDtlsDivId");
getLocationBasedOnSelection("DISTRICT",filterLevel,filterValue,"","All","");
getLocationLevelWiseBoothDetails();
//validateBoothToMakeConfirm(); 


$(document).on("click",".districtLevelCls",function(){
	var selecteLegel = $(this).attr("attr_tab_level_value");
	var levelHeading = selecteLegel+" WISE";
	$(".districtParliamentLevleHadingCls").html(levelHeading);
	getLocationLevelWiseBoothCount(selecteLegel,filterLevel,filterValue,"dstrctParlmntLvlBoothDtlsDivId");
})
function getOverAllBoothDetails(locationLevel,filterLevel,filterValue){
	$("#overAllBoothDlstDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	$("#overAllSerialRangeWiseVoterDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevel,
		filterValue : filterValue,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
		startDate : globalFromDate,
		endDate : globalToDate
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothCountAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		 $("#overAllBoothDlstDivId").html(' ');
		 $("#overAllSerialRangeWiseVoterDivId").html(' ');
		  $(".serialRangeHeadingCls").show();
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
function getLocationLevelWiseBoothCount(locationLevel,filterLevel,filterValue,divId){
    $("#"+divId).html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevel,
		filterValue : filterValue,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
		startDate : globalFromDate,
		endDate : globalToDate
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothCountAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){
		$("#"+divId).html('');
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
/* Filter Related Script Start */
function getLocationBasedOnSelection(locationLevel,filterLevelLevel,filterValue,divId,type,resultLevel){
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevelLevel,
		filterValue : filterValue,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationBasedOnSelectionAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	   buildSelectBox(result,locationLevel,divId,type,resultLevel);
	});
}

function buildSelectBox(result,locationLevel,divId,type,resultLevel){
	var str = '';
	str+='<option value="0">SELECT '+locationLevel+'</option>'
	if(result != null && result.length > 0){
		for(var i in result){
			str+='<option value="'+result[i].locationIdStr+'">'+result[i].locationName+'</option>'
		}
	}
	if(type == "All"){
		$("#constituencyLevelDistrictSelectBxId").html(str);
     	$("#mandalLevelDistrictSelectBxId").html(str);
     	$("#panchatLevelDistrictSelectBxId").html(str);
    }else{
		$("#"+divId).html(str);
		if(resultLevel=="CONSTITUENCY"){
			$("#constituencyLevelConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
		}else if(resultLevel=="TEHSIL"){
			$("#mandalLevelConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			$("#mandalLevelMandalSelectBxId").html('<option value="0">SELECT MANDAL</option>');
		}else if(resultLevel=="PANCHAYAT"){
			$("#panchaytLevelConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			$("#panchaytLevelMandalSelectBxId").html('<option value="0">SELECT MANDAL</option>');
			$("#panchaytLevelPanchaytSelectBxId").html('<option value="0">SELECT PANCHAYAT</option>');
		}
	}                               
}
$(document).on("click",".locationLevelTabCls",function(){
	var parentSelectBoxId = $(this).attr("attr_select_box_id");
	var selectedLevel = $(this).attr("attr_tab_level_value");
	var resultLevel = $(this).attr("attr_result_level");
	var resultLevelDivId = $(this).attr("attr_result_level_div_id");
	$("#"+parentSelectBoxId).attr("attr_level",selectedLevel);
	var filterLevel="";
    var filterValue=0;
	getLocationLevelWiseBoothCount(resultLevel,filterLevel,filterValue,resultLevelDivId);
	getLocationBasedOnSelection(selectedLevel,filterLevel,filterValue,parentSelectBoxId,"Other",resultLevel);
	
});
$(document).on("change",".selectBoxCls",function(){
	var selectValue = $(this).val();
	var subLevelDropBoxId = $(this).attr("attr_sub_level_drop_box_id");
	var selectedLevel = $(this).attr("attr_level");
	var subLevel = $(this).attr("attr_sub_level");
	var resultLevel = $(this).attr("attr_result_level");
	var resultLevelDivId = $(this).attr("attr_result_level_div_id");
	
	if(selectValue>0){
	 getLocationLevelWiseBoothCount(resultLevel,selectedLevel,selectValue,resultLevelDivId);
	 if(subLevel != undefined){
	   getSubLevelLocationBasedOnSelection(subLevel,selectedLevel,selectValue,subLevelDropBoxId);		 
	 }
	}
});
function getSubLevelLocationBasedOnSelection(locationLevel,filterLevelLevel,filterValue,divId){
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevelLevel,
		filterValue : filterValue,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationBasedOnSelectionAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
	   buildSubLevelDropDown(result,locationLevel,divId);
	});
}
function buildSubLevelDropDown(result,locationLevel,divId){
	var str = '';
	str+='<option value="0">SELECT '+locationLevel+'</option>'
	if(result != null && result.length > 0){
		for(var i in result){
			str+='<option value="'+result[i].locationIdStr+'">'+result[i].locationName+'</option>'
		}
	}
	$("#"+divId).html(str);                           
}
/* Filter Related Script END */

function getLocationLevelWiseBoothDetails(){

	var jObj={  
		filterType : "Tehsil",
		filterValue : "1240",
		fromDate : "13/07/2017",
		toDate : "13/07/2017",
		boothEnrollementYearId : globalBoothInchargeEnrollmentId,
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
			boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId
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