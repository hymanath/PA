   var globalBoothInchargeEnrollmentId=1;
	
 	var globalFromDate=moment().startOf('month').format("DD/MM/YYYY");
    var globalToDate=moment().endOf('month').format("DD/MM/YYYY");
	$(".selectBoxCls").chosen();
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
		var filterLevel = $(this).attr("accessType");
        var filterValue = $(this).attr("accessValue");
		globalFromDate = picker.startDate.format('DD/MM/YYYY');
		globalToDate = picker.endDate.format('DD/MM/YYYY');
		getOverAllBoothDetails("STATE",filterLevel,filterValue);
		getLocationLevelWiseBoothCount("DISTRICT",filterLevel,filterValue,"dstrctParlmntLvlBoothDtlsDivId");
		getLocationLevelWiseBoothCount("CONSTITUENCY",filterLevel,filterValue,"constituencyLevelBoothDtlsDivId");
		getLocationLevelWiseBoothCount("TEHSIL",filterLevel,filterValue,"mandalLevelBoothDtlsDivId");
		getLocationBasedOnSelection("DISTRICT",filterLevel,filterValue,"","All","");
		$("#boothDtlsDivId").html('');
		$("#panchaytLevelBoothDtlsDivId").html('');
	});
 
//making active and de-active location tab
$(document).on('click',' li.districtLevelCls ',function(){
  $(this).parent().find(".districtLevelCls").removeClass("active");
  $(this).addClass("active");
}); 

  $(document).on('click',' li.locationLevelTabCls ',function(){
  $(this).parent().find(".locationLevelTabCls").removeClass("active");
  $(this).addClass("active");
});

$(document).on("click",".districtLevelCls",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var selecteLegel = $(this).attr("attr_tab_level_value");
	var filterLevel = $(this).attr("accessType");
    var filterValue = $(this).attr("accessValue");
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
			str+='<h5 class="">Booth Committee Members Voter ID Serial Numbers wise</h5>';
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
				str+='</tr>';
				if(rangeArr != null && rangeArr.length > 0){
					str+='<tr>';
					for(var i in rangeArr){
						str+='<th>'+rangeArr[i].roleName+'</th>';
					}
					str+='</tr>';
				}
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
		$("#boothBlockDistrictSelectBxId").html(str);
		$("#constituencyLevelDistrictSelectBxId,#panchatLevelDistrictSelectBxId,#mandalLevelDistrictSelectBxId,#boothBlockDistrictSelectBxId").trigger("chosen:updated");
    }else{
		$("#"+divId).html(str);
		$("#"+divId).trigger("chosen:updated");
		if(resultLevel=="CONSTITUENCY"){
			$("#constituencyLevelConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			$("#constituencyLevelConstituenySelectBxId").trigger("chosen:updated");
		}else if(resultLevel=="TEHSIL"){
			$("#mandalLevelConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			$("#mandalLevelMandalSelectBxId").html('<option value="0">SELECT MANDAL</option>');
			$("#mandalLevelConstituenySelectBxId,#mandalLevelMandalSelectBxId").trigger("chosen:updated");
		}else if(resultLevel=="PANCHAYAT"){
			$("#panchaytLevelConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			$("#panchaytLevelMandalSelectBxId").html('<option value="0">SELECT MANDAL</option>');
			$("#panchaytLevelPanchaytSelectBxId").html('<option value="0">SELECT PANCHAYAT</option>');
			$("#panchaytLevelConstituenySelectBxId,#panchaytLevelMandalSelectBxId,#panchaytLevelPanchaytSelectBxId").trigger("chosen:updated");
		}else if(resultLevel=="BOOTH"){
			$("#boothDtlsDivId").html('');
			$("#boothBlockConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			$("#boothBlockMandalSelectBxId").html('<option value="0">SELECT MANDAL</option>');
			$("#boothBlockPanchaytSelectBxId").html('<option value="0">SELECT PANCHAYAT</option>');
			$("#boothBlockConstituenySelectBxId,#boothBlockMandalSelectBxId,#boothBlockPanchaytSelectBxId").trigger("chosen:updated");
		}
	}                               
}
$(document).on("click",".locationLevelTabCls",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");
	var parentSelectBoxId = $(this).attr("attr_select_box_id");
	var selectedLevel = $(this).attr("attr_tab_level_value");
	var resultLevel = $(this).attr("attr_result_level");
	var resultLevelDivId = $(this).attr("attr_result_level_div_id");
	$("#"+parentSelectBoxId).attr("attr_level",selectedLevel);
	var filterLevel = $(this).attr("accessType");
    var filterValue = $(this).attr("accessValue");
	if(resultLevel=="PANCHAYAT"){
		$("#panchaytLevelBoothDtlsDivId").html('');
	}
	if(resultLevel != "BOOTH" && resultLevel != "PANCHAYAT"){
	  getLocationLevelWiseBoothCount(resultLevel,filterLevel,filterValue,resultLevelDivId);	
	}
	getLocationBasedOnSelection(selectedLevel,filterLevel,filterValue,parentSelectBoxId,"Other",resultLevel);
	
});
$(document).on("change",".selectBoxCls",function(){
	var selectValue = $(this).val();
	var subLevelDropBoxId = $(this).attr("attr_sub_level_drop_box_id");
	var selectedLevel = $(this).attr("attr_level");
	var subLevel = $(this).attr("attr_sub_level");
	var resultLevel = $(this).attr("attr_result_level");
	var resultLevelDivId = $(this).attr("attr_result_level_div_id");
	
	if(selectValue>0 && subLevelDropBoxId!="panchaytLevelConstituenySelectBxId"){
	 getLocationLevelWiseBoothCount(resultLevel,selectedLevel,selectValue,resultLevelDivId);
	}
	 if(subLevel != undefined){
	   getSubLevelLocationBasedOnSelection(subLevel,selectedLevel,selectValue,subLevelDropBoxId);		 
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
	$("#"+divId).trigger("chosen:updated");
}
/* Filter Related Script END */

/* Booth Details Block Start */
$(document).on("click",".resultTypeTabCls",function(){
	var tabType = $(this).attr("attr_tab_level_value");
	var clickType = $(this).attr("attr_heading_level");
	var filterLevel = $(this).attr("accessType");
    var filterValue = $(this).attr("accessValue");
 		 if(clickType=="SUMMARY"){
			 $(".panchaytBlockCls").show();
			 $(".boothBlckCls").hide();
			 var locationLevel = $(this).attr("attr_panchayt_result_level");
			 var selectBoxDivId = $(this).attr("attr_pnchyt_lvl_parent_select_box_div_id");
			 getSubLevelLocationBasedOnSelection(locationLevel,filterLevel,filterValue,selectBoxDivId)
			$("#panchaytLevelConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			$("#panchaytLevelMandalSelectBxId").html('<option value="0">SELECT MANDAL</option>');
			$("#panchaytLevelPanchaytSelectBxId").html('<option value="0">SELECT PANCHAYAT</option>');
			$("#panchaytLevelConstituenySelectBxId,#panchaytLevelMandalSelectBxId,#panchaytLevelPanchaytSelectBxId").trigger("chosen:updated");
		
			 $(".resultTypeTabCls").removeClass("active");
			 $(".resultTypeUL li:nth-child(1)" ).addClass("active");
			 $("#panchaytLevelBoothDtlsDivId").html('');
			 
		 }else if(clickType == "DETAILS"){
			  $(".panchaytBlockCls").hide();
			  $(".boothBlckCls").show();
			  var locationLevel = $(".boothResultTypeCls").attr("attr_booth_result_level");
			  var selectBoxDivId = $(".boothResultTypeCls").attr("attr_boot_level_parent_select_box_div_id");
			  getSubLevelLocationBasedOnSelection(locationLevel,filterLevel,filterValue,selectBoxDivId);
			  $("#boothBlockConstituenySelectBxId").html('<option value="0">SELECT CONSTITUENCY</option>');
			  $("#boothBlockMandalSelectBxId").html('<option value="0">SELECT MANDAL</option>');
			  $("#boothBlockPanchaytSelectBxId").html('<option value="0">SELECT PANCHAYAT</option>');
			  $("#boothBlockConstituenySelectBxId,#boothBlockMandalSelectBxId,#boothBlockPanchaytSelectBxId").trigger("chosen:updated");
			  
			  $(".resultTypeTabCls").removeClass("active");
			  $(".resultTypeUL li:nth-child(2)" ).addClass("active");
			  $("#boothDtlsDivId").html('');
		 }
});

$(".boothBlockCls").chosen();
$(document).on("change",".boothBlockCls",function(){
	var selectValue = $(this).val();
	var subLevelDropBoxId = $(this).attr("attr_sub_level_drop_box_id");
	var selectedLevel = $(this).attr("attr_level");
	var subLevel = $(this).attr("attr_sub_level");
	var resultLevel = $(this).attr("attr_result_level");
	var resultLevelDivId = $(this).attr("attr_result_level_div_id");
	var resultType="All";
	 if(selectValue>0 && subLevelDropBoxId != "boothBlockConstituenySelectBxId"){
	   getLocationLevelWiseBoothDetails(selectedLevel,selectValue,resultType);
	 }
	 if(subLevel != undefined ){
	   getSubLevelLocationBasedOnSelection(subLevel,selectedLevel,selectValue,subLevelDropBoxId);		 
	 }
	
});
function getLocationLevelWiseBoothDetails(filterLevel,filterValue,resultType){
	$("#boothDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var jObj={  
		filterType : filterLevel,
		filterValue : filterValue,
		fromDate : globalFromDate,
		toDate : globalToDate,
		boothEnrollementYearId : globalBoothInchargeEnrollmentId,
		resultType : resultType
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	   buildBoothDetails(result);
	});
}
function buildBoothDetails(result){
	var str='';
	if(result != null && result.length > 0){
		 str+='<div class="table-responsive">';
		 str+='<table class="table table-bordered" id="bootDtlsdataTableId">';
			str+='<thead>';
				    str+='<th>DISTRICT</th>';
				    str+='<th>PARLIAMENT CONSTITUENCY</th>';
				    str+='<th>CONSTITUENCY</th>';
				    str+='<th>MANDAL</th>';
				    str+='<th>PANCHAYAT</th>';
				    str+='<th>Booth NO</th>';
					str+='<th>Booth Status</th>';
			str+='</thead>';
			 str+='<tbody>';
				for(var i in result){
					str+='<tr>';
						if(result[i].districtName != null){
							str+='<td class="text-center;">'+result[i].districtName+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].parliamentConstituency != null){
							str+='<td class="text-center;">'+result[i].parliamentConstituency+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].constituencyName != null){
							str+='<td class="text-center;">'+result[i].constituencyName+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].tehsilName != null){
							str+='<td class="text-center;">'+result[i].tehsilName+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].panchayat != null){
							str+='<td class="text-center;">'+result[i].panchayat+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].boothName != null){
							str+='<td class="text-center;">'+result[i].boothName+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].status != null){
							str+='<td class="text-center;">'+result[i].status+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
				str+='</tr>';
			   }
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
	 $("#boothDtlsDivId").html(str);	
	 $('#bootDtlsdataTableId').dataTable();
	}else{
	  $("#boothDtlsDivId").html('NO DATA AVAILABLE');
	}
}
/* Booth Details Block End */
//validateBoothToMakeConfirm(); 
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
