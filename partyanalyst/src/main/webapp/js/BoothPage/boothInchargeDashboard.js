    var globalBoothInchargeEnrollmentId=1;
	var globalFromDate=moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY");
	var globalToDate=moment().endOf('year').add(10, 'years').format("DD/MM/YYYY");
 	
	$(".selectBoxCls").chosen();
	$('#daterangePickerId').daterangepicker({
		opens: 'left',
		startDate: globalFromDate,
		endDate: globalToDate,
		locale: {
			  format: 'DD/MM/YYYY'
			},
		ranges: {
			'All' :[moment().subtract(20, 'years').startOf('year').format("DD/MM/YYYY"), moment().add(10, 'years').endOf('year').format("DD/MM/YYYY")],
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
	
	var dates= $("#daterangePickerId").val();
	var pickerDates = globalFromDate+' - '+globalToDate
	if(dates == pickerDates)
	{
		$("#daterangePickerId").val('All');
	}

	$('#daterangePickerId').on('apply.daterangepicker', function(ev, picker) {
		globalFromDate = picker.startDate.format('DD/MM/YYYY');
		globalToDate = picker.endDate.format('DD/MM/YYYY');
		var filterLevel = $(this).attr("accessType");//filterLevel is nothing but user accessType
        var filterValue = $(this).attr("accessValue");////filterValue is nothing but user accessValue
		if(picker.chosenLabel == 'All')
		{
			$("#daterangePickerId").val('All');
		}
		
		 ajaxCallBasedOnUserAccessLevel(filterLevel,filterValue);
	});
	
	
	$(document).on("change","#boothCommitteeDashbrdRolesId",function(){
		var filterLevel = $(this).attr("accessType");//filterLevel is nothing but user accessType
        var filterValue = $(this).attr("accessValue");////filterValue is nothing but user accessValue
	    ajaxCallBasedOnUserAccessLevel(filterLevel,filterValue);
	 
	
});
//based on user access level.we are building select box and sending ajax call 
function ajaxCallBasedOnUserAccessLevel(filterLevel,filterValue){
		hideShowDiv();//making booth block active and hiding panchayat block.
		if(filterLevel == "CONSTITUENCY"){
			getLocationLevelWiseBoothDetails(filterLevel,filterValue,"All");
			getOverAllBoothDetails("STATE",filterLevel,filterValue);
			getLocationLevelWiseBoothCount("TEHSIL",filterLevel,filterValue,"mandalLevelBoothDtlsDivId");
			//getLocationLevelWiseBoothCount("PANCHAYAT",filterLevel,filterValue,"panchaytLevelBoothDtlsDivId");
			getLocationBasedOnSelection("TEHSIL",filterLevel,filterValue,"","CONSTITUENCY");
			$("#panchaytLevelPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			$("#boothBlockPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			$("#panchaytLevelPanchaytSelectBxId,#boothBlockPanchaytSelectBxId").trigger("chosen:updated");
		}else if(filterLevel == "PARLIAMENT CONSTITUENCY"){
			getLocationBasedOnSelection("CONSTITUENCY",filterLevel,filterValue,"","PARLIAMENT CONSTITUENCY");
			getOverAllBoothDetails("STATE",filterLevel,filterValue);
			getLocationLevelWiseBoothCount("CONSTITUENCY",filterLevel,filterValue,"constituencyLevelBoothDtlsDivId");
			getLocationLevelWiseBoothCount("TEHSIL",filterLevel,filterValue,"mandalLevelBoothDtlsDivId");
			getLocationLevelWiseBoothDetails(filterLevel,filterValue,"All");
			//clearing select box
			$("#boothBlockMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			$("#mandalLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			$("#panchaytLevelPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			$("#boothBlockPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			$("#panchaytLevelPanchaytSelectBxId,#boothBlockPanchaytSelectBxId,#mandalLevelMandalSelectBxId,#boothBlockMandalSelectBxId").trigger("chosen:updated");
		}else if(filterLevel == "DISTRICT" || filterLevel == "STATE"){
		   clearCommonDiv();
		   getLocationBasedOnSelection("DISTRICT",filterLevel,filterValue,"","All");	
		   getOverAllBoothDetails("STATE",filterLevel,filterValue);
		   getLocationLevelWiseBoothCount("DISTRICT",filterLevel,filterValue,"dstrctParlmntLvlBoothDtlsDivId");
		   getLocationLevelWiseBoothCount("CONSTITUENCY",filterLevel,filterValue,"constituencyLevelBoothDtlsDivId");
		   getLocationLevelWiseBoothCount("TEHSIL",filterLevel,filterValue,"mandalLevelBoothDtlsDivId");
		  //clearing select box
		   $("#constituencyLevelConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
		   $("#mandalLevelConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
		   $("#mandalLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
		   $("#panchaytLevelConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
		   $("#panchaytLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
		   $("#panchaytLevelPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
		  
		   $("#boothBlockConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
		   $("#boothBlockMandalSelectBxId").html('<option value="0">All MANDAL</option>');
		   $("#boothBlockPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
		   $("#boothBlockConstituenySelectBxId,#boothBlockMandalSelectBxId,#boothBlockPanchaytSelectBxId").trigger("chosen:updated");
		   $("#constituencyLevelConstituenySelectBxId,#mandalLevelConstituenySelectBxId,#mandalLevelMandalSelectBxId").trigger("chosen:updated");
		   $("#panchaytLevelConstituenySelectBxId,#panchaytLevelMandalSelectBxId,#panchaytLevelPanchaytSelectBxId").trigger("chosen:updated");
	  }
}
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
    var filterValueArr=filterValue.split(","); ;   
 	var boothRoleIdArr = [];
	var roleId = $("#boothCommitteeDashbrdRolesId").val();
	if(roleId != null && roleId != 0){
		boothRoleIdArr.push(roleId);
	}
 	
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevel,
		filterValueArr : filterValueArr,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
		startDate : globalFromDate,
		endDate : globalToDate,
		boothRoleIdArr:boothRoleIdArr
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
	 	  buildOverAllBoothDtls(result,locationLevel);
	});

}
function buildOverAllBoothDtls(result,locationLevel){
	if(result != null && result.length > 0){
		 var overallDataObj = result[0];
		 var rangevoterArr = overallDataObj.subList;
		 if(overallDataObj != null){
			 var totalBooth = overallDataObj.totalBoothCount;
			 var startedBooth = overallDataObj.startedBoothCount;
			 var completedBooth = overallDataObj.completedBoothCount;
			 var notStartedBooth = totalBooth-(startedBooth+completedBooth);
			 var str='';
			 str+='<div class="col-sm-12 blocks">';
                        str+='<div class="col-sm-3">';
							str+='<div class="subBlock text-center">';
                                str+='<p>TOTAL BOOTHS</p>';
								if(totalBooth > 0 ){
									 str+='<h4 class="text-center;">'+totalBooth+'</h4>';
								}else{
									 str+='<h4 class="text-center;"> - </h4>';
								}
                               
							str+='</div>';
                        str+='</div>';
						 str+='<div class="col-sm-3">';
                            str+='<div class="subBlock text-center">';
                            str+='<p>NOT-STARTED BOOTHS</p>';
                              if(notStartedBooth > 0){
									 str+='<h4 class="text-center;">'+notStartedBooth+'</h4>';
								}else{
									 str+='<h4 class="text-center;"> - </h4>';
								}
                           str+=' </div>';
                       str+=' </div>';
                        str+='<div class="col-sm-3">';
                            str+='<div class="subBlock text-center">';
                           str+=' <p>STARTED BOOTHS</p>';
							  if(startedBooth > 0){
									 str+='<h4 class="text-center;">'+startedBooth+'</h4>';
								}else{
									 str+='<h4 class="text-center;"> - </h4>';
								}
                            str+='</div>';
                        str+='</div>';
                        str+='<div class="col-sm-3">';
                            str+='<div class="subBlock text-center">';
                           str+=' <p>COMPLETED BOOTHS</p>';
                             if(completedBooth > 0){
									 str+='<h4 class="text-center;">'+completedBooth+'</h4>';
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
						 //class="cadreDetailsCls" style="cursor: pointer; font-size: 16px; margin-top: 10px;color: rgb(51, 122, 183);" attr_location_level='+locationLevel+' attr_serial_no="'+rangevoterArr[i].roleId+'" attr_filter_level="" attr_filter_value="0"  attr_booth_id="0"
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
	    var filterValueArr=filterValue.split(",");  
	 	var boothRoleIdArr = [];
		var roleId = $("#boothCommitteeDashbrdRolesId").val();
		if(roleId != null && roleId != 0){
			boothRoleIdArr.push(roleId);
		}
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevel,
		filterValueArr : filterValueArr,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
		startDate : globalFromDate,
		endDate : globalToDate,
		boothRoleIdArr:boothRoleIdArr
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
		 str +='<h4><a class="btn btn-xs btn-mini btn-success pull-right" href="javascript:{exportToExcel(\''+divId+'dataTableId\')}"  style="margin-bottom: 7px;"> Export Excel</a></h4>';
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
						 var totalBooth = result[i].totalBoothCount;
						 var startedBooth = result[i].startedBoothCount;
						 var completedBooth = result[i].completedBoothCount;
						 var notStartedBooth = totalBooth-(startedBooth+completedBooth);
					
						 str = str +" "+boothAddressStr;
						if(totalBooth > 0){
							str+='<td class="text-center;">'+totalBooth+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(notStartedBooth > 0){
							str+='<td class="text-center;">'+notStartedBooth+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(startedBooth > 0){
							str+='<td class="text-center;">'+startedBooth+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(completedBooth > 0){
							str+='<td class="text-center;">'+completedBooth+'</td>';
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
	 $("#"+divId+'dataTableId').dataTable({
		"aaSorting": [[ 4, "desc" ]], 
		"iDisplayLength" : 10,
		"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]								
	});
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
	if(locationLevel=="TEHSIL"){
		str+='<th rowspan="2">TEHSIL/MUNCIPALITY/MUNCIPAL CORP</th>';
	}
	if(locationLevel=="PANCHAYAT"){
	  str+='<th rowspan="2">TEHSIL</th>';	
	}
	if(locationLevel=="PANCHAYAT"){
		str+='<th rowspan="2">VILLAGE/WARD</th>';
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
function getLocationBasedOnSelection(locationLevel,filterLevelLevel,filterValue,divId,type){
	 var filterValueArr=filterValue.split(","); ;   
	 var boothRoleIdArr = [];
	 var roleId = $("#boothCommitteeDashbrdRolesId").val();
	 if(roleId != null && roleId != 0){
		boothRoleIdArr.push(roleId);
	 }
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevelLevel,
		filterValueArr : filterValueArr,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
		boothRoleIdArr:boothRoleIdArr
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationBasedOnSelectionAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)} 
	}).done(function(result){ 
		 buildSelectBox(result,locationLevel,divId,type);
	});
}

function buildSelectBox(result,locationLevel,divId,type){
	  var str = '';
	  str+='<option value="0">All '+locationLevel+'</option>'	
	if(result != null && result.length > 0){
		for(var i in result){
			if(divId=="panchaytLevelMandalSelectBxId"){
				var firstDigit = result[i].locationIdStr.substring(0,1);
				if(firstDigit !=2){//we are removing locationElectionBody from dropDown in PANCHAYAT Lavel.
				  str+='<option value="'+result[i].locationIdStr+'">'+result[i].locationName+'</option>'
				}
		   }else {
			  str+='<option value="'+result[i].locationIdStr+'">'+result[i].locationName+'</option>'		
		   }
		}
	}
	if(type == "All"){
		$("#constituencyLevelDistrictSelectBxId").html(str);
     	$("#mandalLevelDistrictSelectBxId").html(str);
     	$("#panchatLevelDistrictSelectBxId").html(str);
		$("#boothBlockDistrictSelectBxId").html(str);
		$("#constituencyLevelDistrictSelectBxId,#panchatLevelDistrictSelectBxId,#mandalLevelDistrictSelectBxId,#boothBlockDistrictSelectBxId").trigger("chosen:updated");
    }else if(type == "PARLIAMENT CONSTITUENCY"){
		$("#constituencyLevelConstituenySelectBxId").html(str);
		$("#mandalLevelConstituenySelectBxId").html(str);
		$("#boothBlockConstituenySelectBxId").html(str);
		$("#constituencyLevelConstituenySelectBxId,#mandalLevelConstituenySelectBxId,#boothBlockConstituenySelectBxId").trigger("chosen:updated");
	}else if(type == "CONSTITUENCY"){
		$("#mandalLevelMandalSelectBxId").html(str);
		$("#boothBlockMandalSelectBxId").html(str);
		$("#mandalLevelMandalSelectBxId,#boothBlockMandalSelectBxId").trigger("chosen:updated");
	}else{
		$("#"+divId).html(str);
		$("#"+divId).trigger("chosen:updated");
	}                               
}
function clearSelectBoxByLocationLevel(resultLevel){
	  if(resultLevel=="CONSTITUENCY"){
			$("#constituencyLevelConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
			$("#constituencyLevelConstituenySelectBxId").trigger("chosen:updated");
		}else if(resultLevel=="TEHSIL"){
			$("#mandalLevelConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
			$("#mandalLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			$("#mandalLevelConstituenySelectBxId,#mandalLevelMandalSelectBxId").trigger("chosen:updated");
		}else if(resultLevel=="PANCHAYAT"){
			$("#panchaytLevelConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
			$("#panchaytLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			$("#panchaytLevelPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			$("#panchaytLevelConstituenySelectBxId,#panchaytLevelMandalSelectBxId,#panchaytLevelPanchaytSelectBxId").trigger("chosen:updated");
		}else if(resultLevel=="BOOTH"){
			$("#boothDtlsDivId").html('');
			$("#boothBlockConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
			$("#boothBlockMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			$("#boothBlockPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			$("#boothBlockConstituenySelectBxId,#boothBlockMandalSelectBxId,#boothBlockPanchaytSelectBxId").trigger("chosen:updated");
		}
}
function clearChildSelectBoxByLocationLevel(locationLevel,resultLevel){
		if(resultLevel=="TEHSIL"){
			 if(locationLevel=="CONSTITUENCY"){
			   $("#mandalLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			   $("#mandalLevelMandalSelectBxId").trigger("chosen:updated");
			 }
		 }else if(resultLevel=="PANCHAYAT"){
			 if(locationLevel=="CONSTITUENCY"){
				 $("#panchaytLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			     $("#panchaytLevelPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			     $("#panchaytLevelMandalSelectBxId,#panchaytLevelPanchaytSelectBxId").trigger("chosen:updated");
			 }else if(locationLevel=="TEHSIL"){
				 $("#panchaytLevelPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			     $("#panchaytLevelPanchaytSelectBxId").trigger("chosen:updated");
			 }
		 }else if(resultLevel=="BOOTH"){
			 if(locationLevel=="CONSTITUENCY"){
				 $("#boothBlockMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			     $("#boothBlockPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			     $("#boothBlockPanchaytSelectBxId,#boothBlockMandalSelectBxId").trigger("chosen:updated");
			 }else if(locationLevel=="TEHSIL"){
				 $("#boothBlockPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
			     $("#boothBlockPanchaytSelectBxId").trigger("chosen:updated");
			 }
		 }
 }
function clearCommonDiv(){
	$("#boothDtlsDivId").html('');
	$("#panchaytLevelBoothDtlsDivId").html('');
}
function hideShowDiv(){
	 $(".panchaytBlockCls").hide();
	 $(".boothBlckCls").show();
	 $(".resultTypeTabCls").removeClass("active");
	 $(".resultTypeUL li:nth-child(2)" ).addClass("active");
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
	clearSelectBoxByLocationLevel(resultLevel);
	getLocationBasedOnSelection(selectedLevel,filterLevel,filterValue,parentSelectBoxId,"Other");
	
});
$(document).on("change",".selectBoxCls",function(){
	var selectValue = $(this).val();
	var subLevelDropBoxId = $(this).attr("attr_sub_level_drop_box_id");
	var selectedLevel = $(this).attr("attr_level");
	var subLevel = $(this).attr("attr_sub_level");
	var resultLevel = $(this).attr("attr_result_level");
	var resultLevelDivId = $(this).attr("attr_result_level_div_id");
	var filterLevel = $("#boothCommitteeDashbrdRolesId").attr("accessType");//user access level 
    var filterValue = $("#boothCommitteeDashbrdRolesId").attr("accessValue");//user access value 
	var parentSelectBoxId = $(this).attr("attr_parent_level_drop_box_id");
	var parentLevel = $(this).attr("attr_parent_level");
	var parentSelectBoxValue = $("#"+parentSelectBoxId).val();

	/* NOTE:We are overriding selected selectedLevel and selectValue based on requirement */
	
	/* in the case of state or district level login user.if user is selecting district filter value all location 
	   then select box value will be 0 and you will get parentSelectBoxValue is undefined.in this case we are 
        sending user access level and their value.*/
	var seletedLocationType="single";
	if(selectValue == 0 && parentSelectBoxValue == undefined){
		selectedLevel = filterLevel;
		selectValue = filterValue;
		seletedLocationType="All";
	    /*there is one case .when MLA/MP are login we are hiding some drop down .
		in this case parentSelectBoxValue will come 0. in this case when user is selecting all location .
		then selected drop down value and parentSelectBoxValue both will be zero .
		in this case we are sending user access level and their value.*/
	}else if(selectValue == 0 && parentSelectBoxValue == 0){
		 selectedLevel = filterLevel;
		 selectValue = filterValue;
		 seletedLocationType="All";
	/*if user is selection child drop down all location level then we are sending parent selected value.*/
	}else if(selectValue == 0){
		 selectedLevel = parentLevel;
		 selectValue = parentSelectBoxValue;
	}
	if(resultLevel == "PANCHAYAT"){
		if(subLevelDropBoxId != "panchaytLevelConstituenySelectBxId"){//except district filter selection we are sending call.
			 if(filterLevel =="STATE" && seletedLocationType=="All"){//in the case of state access user level all selection we are stopping call because huge data is there.
				 clearCommonDiv();
			 }else{
				 getLocationLevelWiseBoothCount(resultLevel,selectedLevel,selectValue,resultLevelDivId);		 
			 }
		}else {
		  clearCommonDiv();
	    }
	}
	if(resultLevel != "PANCHAYAT"){
		getLocationLevelWiseBoothCount(resultLevel,selectedLevel,selectValue,resultLevelDivId);
	}
	if(subLevel != undefined){
	   clearChildSelectBoxByLocationLevel(subLevel,resultLevel);
	   getSubLevelLocationBasedOnSelection(subLevel,selectedLevel,selectValue,subLevelDropBoxId);		 
	}
	
});
function getSubLevelLocationBasedOnSelection(locationLevel,filterLevel,filterValue,divId){
	 var filterValueArr=filterValue.split(","); ;   
	 var boothRoleIdArr = [];
	 var roleId = $("#boothCommitteeDashbrdRolesId").val();
	 if(roleId != null && roleId != 0){
		boothRoleIdArr.push(roleId);
	 }
	var jsObj={  
		locationLevel : locationLevel,         
		filterLevel : filterLevel,
		filterValueArr : filterValueArr,
		boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId,
		boothRoleIdArr:boothRoleIdArr
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
	 str+='<option value="0">All '+locationLevel+'</option>'	
	if(result != null && result.length > 0){
		for(var i in result){
		   if(divId=="panchaytLevelMandalSelectBxId"){
				var firstDigit = result[i].locationIdStr.substring(0,1);
				if(firstDigit !=2){//we are removing locationElectionBody from dropDown in PANCHAYAT Lavel.
				  str+='<option value="'+result[i].locationIdStr+'">'+result[i].locationName+'</option>'
				}
		   }else {
			str+='<option value="'+result[i].locationIdStr+'">'+result[i].locationName+'</option>'	
		  }
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
			$("#panchaytLevelConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
			$("#panchaytLevelMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			$("#panchaytLevelPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
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
			  $("#boothBlockConstituenySelectBxId").html('<option value="0">All CONSTITUENCY</option>');
			  $("#boothBlockMandalSelectBxId").html('<option value="0">All MANDAL</option>');
			  $("#boothBlockPanchaytSelectBxId").html('<option value="0">All PANCHAYAT</option>');
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
	var filterLevel = $("#boothCommitteeDashbrdRolesId").attr("accessType");//user access level
    var filterValue = $("#boothCommitteeDashbrdRolesId").attr("accessValue");//user access value.
	var parentSelectBoxId = $(this).attr("attr_parent_level_drop_box_id");
	var parentLevel = $(this).attr("attr_parent_level");
	var parentSelectBoxValue = $("#"+parentSelectBoxId).val();
	
	/* NOTE:We are overriding selected selectedLevel and selectValue based on requirement */
	
	/* in the case of state or district level login user.if user is selecting district filter value all location 
	   then select box value will be 0 and you will get parentSelectBoxValue is undefined.in this case we are 
        sending user access level and their value.*/
	var seletedLocationType="single";
	if(selectValue == 0 && parentSelectBoxValue == undefined){
		selectedLevel = filterLevel;
		selectValue = filterValue;
		seletedLocationType="All";
	    /*there is one case .when MLA/MP are login we are hiding some drop down .
		in this case parentSelectBoxValue will come 0. in this case when user is selecting all location .
		then selected drop down value and parentSelectBoxValue both will be zero .
		in this case we are sending user access level and their value.*/
	}else if(selectValue == 0 && parentSelectBoxValue == 0){
		 selectedLevel = filterLevel;
		 selectValue = filterValue;
		 seletedLocationType="All";
	/*if user is selection child drop down all location level then we are sending parent selected value.*/
	}else if(selectValue == 0){
		 selectedLevel = parentLevel;
		 selectValue = parentSelectBoxValue;
	}
	var resultType="All";
	
	 if(subLevelDropBoxId != "boothBlockConstituenySelectBxId"){//except district filter selection we are sending call.
		 if(filterLevel =="STATE" && seletedLocationType=="All"){//in the case of state access user level all selection we are stopping call because huge data is there.
			  clearCommonDiv();
		 }else{
			 getLocationLevelWiseBoothDetails(selectedLevel,selectValue,resultType);	 
		 }
	 }else{
		 clearCommonDiv();
	 }
	 if(subLevel != undefined ){
	    clearChildSelectBoxByLocationLevel(subLevel,resultLevel);
	   getSubLevelLocationBasedOnSelection(subLevel,selectedLevel,selectValue,subLevelDropBoxId);		 
	 }
});
function getLocationLevelWiseBoothDetails(filterLevel,filterValue,resultType){
	$("#boothDtlsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var filterValueArr=filterValue.split(","); ;   
    var locationLevel="PANCHAYAT";	   
	var boothRoleIdArr = [];
	var roleId = $("#boothCommitteeDashbrdRolesId").val();
	if(roleId != null && roleId != 0){
		boothRoleIdArr.push(roleId);
	}
	var jObj={  
		filterType : filterLevel,
		filterValueArr : filterValueArr,
		fromDate : globalFromDate,
		toDate : globalToDate,
		boothEnrollementYearId : globalBoothInchargeEnrollmentId,
		resultType : resultType,
		boothRoleIdArr:boothRoleIdArr
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationLevelWiseBoothDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	   buildBoothDetails(result,locationLevel,filterLevel,filterValue);
	});
}
function buildBoothDetails(result,locationLevel,filterLevel,filterValue){
	var str='';
	if(result != null && result.length > 0){
		 str+='<div class="table-responsive">';
		  str +='<h4><a class="btn btn-xs btn-mini btn-success pull-right" href="javascript:{exportToExcel(\'bootDtlsdataTableId\')}"  style="margin-bottom: 7px;"> Export Excel</a></h4>';
		 str+='<table class="table table-bordered" id="bootDtlsdataTableId">';
			str+='<thead>';
				    str+='<th>DISTRICT</th>';
				    str+='<th>PARLIAMENT </th>';
				    str+='<th>CONSTITUENCY</th>';
				    str+='<th>MANDAL</th>';
				    str+='<th>VILLAGE/WARD</th>';
				    str+='<th>Booth NO</th>';
				    str+='<th>FILLED</th>';
				    str+='<th> VACANCY </th>';
					str+='<th>BOOTH STATUS </th>';
					str+='<th>UPDATE STATUS</th>';
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
							str+='<td class="text-center">'+result[i].boothName+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						var addedCount = result[i].convenerCount+result[i].memberCount;
						var maxCount = result[i].totalCount;
						var vacancyCount = parseInt(maxCount)-parseInt(addedCount);
						if(addedCount > 0 ){
						  str+='<td class="text-center cadreDetailsCls" style="cursor: pointer; font-size: 16px; margin-top: 10px;color: rgb(51, 122, 183);" attr_location_level='+locationLevel+' attr_serial_no="0" attr_filter_level="" attr_booth_name="'+result[i].boothName+'" attr_filter_value="0"  attr_booth_id="'+result[i].boothId+'">'+addedCount+'</td>';
						}else {
						  str+='<td class="text-center">-</td>';		
						}
						if(vacancyCount > 0 ){
						  str+='<td class="text-center cadreDetailsCls" style="cursor: pointer; font-size: 16px; margin-top: 10px;color: rgb(51, 122, 183);" attr_location_level='+locationLevel+' attr_serial_no="0" attr_filter_level="" attr_booth_name="'+result[i].boothName+'" attr_filter_value="0"  attr_booth_id="'+result[i].boothId+'">'+vacancyCount+'</td>';
						}else {
						  str+='<td class="text-center">-</td>';		
						}
						
						if(result[i].status != null){
							str+='<td class="text-center;">'+result[i].status+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].isReadyToConfirm == "yes"){
							if(result[i].status != null && result[i].status=='Completed')
								str+='<td class="text-center;">-</td>';
							else
								str+='<td> <button class="btn btn-xs btn-min btn-success" onclick="validateBoothToMakeConfirm('+result[i].boothId+',\''+result[i].boothName+'\',\''+filterLevel+'\',\''+filterValue+'\')"> Entry Finished </button></td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}	
						
						str+='</tr>';
			   }
			str+='</tbody>';
		str+='</table>';
		str+='</div>';
	 $("#boothDtlsDivId").html(str);	
	 $("#bootDtlsdataTableId").dataTable({
		"aaSorting": [[ 4, "desc" ]], 
		"iDisplayLength" : 10,
		"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]					
	 });
	}else{
	  $("#boothDtlsDivId").html('NO DATA AVAILABLE');
	}
}
/* Booth Details Block End */
$(document).on("click",".cadreDetailsCls",function(){
	$("#boothInchargeDataModalDivId").modal("show");
	var locationLevel = $(this).attr("attr_location_level");
	var boothId = $(this).attr("attr_booth_id");
	var boothName = $(this).attr("attr_booth_name");
	var serialRangeId = $(this).attr("attr_serial_no");
	var filterLevel = $(this).attr("attr_filter_level");
	var filterValue = $(this).attr("attr_filter_value");
	var filterValueArr=[];
	if(filterValue > 0){
		filterValueArr.push(filterValue);
	}
	getLocationWiseCadreDetails(locationLevel,filterLevel,filterValueArr,boothId,serialRangeId,boothName);
});

function getLocationWiseCadreDetails(locationLevel,filterLevel,filterValueArr,boothId,serialRangeId,boothName){
	   $("#cadreDetailsDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	    var boothRoleIdArr = [];
		var roleId = $("#boothCommitteeDashbrdRolesId").val();
		if(roleId != null && roleId != 0){
			boothRoleIdArr.push(roleId);
		} 
		var  enrllmentId =0;
		var locationValue =$("#boothBlockConstituenySelectBxId").val();
		gettingBoothInchargeRoleDetails(boothId,locationValue,enrllmentId);
	var jObj={  
		filterType : "",
		filterValueArr : filterValueArr   ,
		fromDate : globalFromDate,
		toDate : globalToDate,
		boothEnrollementYearId : globalBoothInchargeEnrollmentId,
		resultType : "",
		boothRoleIdArr:boothRoleIdArr,
		serialRangeId:serialRangeId,
		boothId:boothId
	} 
	$.ajax({
		type : 'POST',
		url : 'getLocationWiseCadreDetailsAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	   buildCadreDetails(result,locationLevel,boothName);
	});
}
function buildCadreDetails(result,locationLevel,boothName){
	if(result != null && result.length > 0){
		 var str = '';
		 str +='<h4><a class="btn btn-xs btn-mini btn-success pull-right" href="javascript:{exportToExcel(\'cadreDetailsExportDataTableId\')}"  style="margin-bottom: 7px;"> Export Excel</a></h4>';
		 str+='<div class="table-responsive">';
		 str+='<table class="table table-bordered" id="cadreDetailsDataTableId">';
			str+='<thead>';
				str+='<tr>';
				//var locationSpecificHeadingStr = getLocationSpeceficHeading(locationLevel);
				//str = str +" "+locationSpecificHeadingStr;
				//	str+='<th>DISTRICT</th>';
				//	str+='<th>PARLIAMENT CONSTITUENCY</th>';
					
					str+='<th>MEMBERSHIP NO</th>';
					str+='<th>IMAGE</th>';
					str+='<th>CADRE NAME</th>';
					str+='<th>MOBILE NO</th>';
					str+='<th>DESIGNATION</th>';
					str+='<th>SERIAL NO</th>';
					str+='<th>INCHARGE&nbsp;BOOTH&nbsp;NO</th>';
					str+='<th>OWN&nbsp;BOOTH&nbsp;NO</th>';
					str+='<th>CONSTITUENCY</th>';
					str+='<th>TEHSIL</th>';
					str+='<th>VILLAGE/WARD</th>';
				str+='</tr>';
			str+='</thead>';
			 str+='<tbody>';
				for(var i in result){
					str+='<tr>';
					   //var boothAddressStr = getLocationWiseBoothAddress(locationLevel,result[i]);
						 //str = str +" "+boothAddressStr;
					//	str+='<td>'+result[i].districtName+'</td>';
					//	str+='<td>'+result[i].parliamentConstituency+'</td>';
					   if(result[i].memberShipNo != null){
							str+='<td class="text-center;">'+result[i].memberShipNo+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].image != null && result[i].image.length > 0){
							str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].image+'" style="width: 50px; height: 50px;"></td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].cadreName != null){
							str+='<td class="text-center;">'+result[i].cadreName+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].mobileNo != null){
							str+='<td class="text-center;">'+result[i].mobileNo+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].role != null){
							str+='<td class="text-center;">'+result[i].role+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].serialNo != null){
							str+='<td class="text-center;">'+result[i].serialNo+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						str+='<td>BOOTH&nbsp;NO-'+boothName+'</td>';
						str+='<td>BOOTH&nbsp;NO-'+result[i].boothName+'</td>';
						str+='<td>'+result[i].constituencyName+'</td>';
						str+='<td>'+result[i].tehsilName+'</td>';
						str+='<td>'+result[i].panchayat+'</td>';
				str+='</tr>';
			   }
			str+='</tbody>';
		str+='</table>';
		
		 str+='<table class="table table-bordered" style="display:none;" id="cadreDetailsExportDataTableId">';
			str+='<thead>';
				str+='<tr>';
					str+='<th>MEMBERSHIP NO</th>';
					str+='<th>CADRE NAME</th>';
					str+='<th>MOBILE NO</th>';
					str+='<th>DESIGNATION</th>';
					str+='<th>SERIAL NO</th>';
					str+='<th>INCHARGE&nbsp;BOOTH&nbsp;NO</th>';
					str+='<th>OWN&nbsp;BOOTH&nbsp;NO</th>';
					str+='<th>DISTRICT</th>';
					str+='<th>PARLIAMENT CONSTITUENCY</th>';
					str+='<th>CONSTITUENCY</th>';
					str+='<th>TEHSIL</th>';
					str+='<th>VILLAGE/WARD</th>';
				str+='</tr>';
			str+='</thead>';
			 str+='<tbody>';
				for(var i in result){
					str+='<tr>';
					if(result[i].memberShipNo != null){
							str+='<td class="text-center;">'+result[i].memberShipNo+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].cadreName != null){
							str+='<td class="text-center;">'+result[i].cadreName+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].mobileNo != null){
							str+='<td class="text-center;">'+result[i].mobileNo+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].role != null){
							str+='<td class="text-center;">'+result[i].role+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						if(result[i].serialNo != null){
							str+='<td class="text-center;">'+result[i].serialNo+'</td>';
						}else{
							str+='<td class="text-center;">-</td>';	
						}
						str+='<td>BOOTH&nbsp;NO-'+boothName+'</td>';
						str+='<td>BOOTH&nbsp;NO-'+result[i].boothName+'</td>';
						str+='<td>'+result[i].districtName+'</td>';
						str+='<td>'+result[i].parliamentConstituency+'</td>';
						str+='<td>'+result[i].constituencyName+'</td>';
						str+='<td>'+result[i].tehsilName+'</td>';
						str+='<td>'+result[i].panchayat+'</td>';

						if(result[i].image != null && result[i].image.length > 0){
							//str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].image+'" style="width: 50px; height: 50px;"></td>';
						}else{
							//str+='<td class="text-center;">-</td>';	
						}
				str+='</tr>';
			   }
			str+='</tbody>';
		str+='</table>';
		
		str+='</div>';
	 $("#cadreDetailsDivId").html(str);	
	 $("#cadreDetailsDataTableId").dataTable({
		"aaSorting": [[ 4, "desc" ]], 
		"iDisplayLength" : 10,
		"aLengthMenu": [[10,20,50, 100, -1], [10,20,50, 100, "All"]]						
	});
	}else{
	  $("#cadreDetailsDivId").html('NO DATA AVAILABLE.')	
	}
}
function boothInchargeRoles(){
	  var jObj = {   
	    } 
	  $.ajax({
	    type : 'POST',
	    url : 'getBoothInchargersRolesAction.action',  
	    dataType : 'json',
	    data : {task :JSON.stringify(jObj)} 
	  }).done(function(result){ 
	  var str='';
	  str+='<option value="0">All ROLES</option>';
	  if(result != null && result.length > 0){
	    for(var i in result){
	      str+='<option value="'+result[i].roleId+'">'+result[i].roleName+'</option>'
	    }
	  }
		$("#boothCommitteeDashbrdRolesId").html(str);
	    $("#boothCommitteeDashbrdRolesId").trigger("chosen:updated");
	  });
	}
	
function validateBoothToMakeConfirm(boothId,boothName,filterLevel,filterValue){
	
	if(!confirm("Are you sure want confirm this Booth No-"+boothName+"  incharge Committee ?"))
		return;
	var jObj = {  
			boothId : boothId,
			boothInchargeEnrollmentId : globalBoothInchargeEnrollmentId
		} 
	$.ajax({
		type : 'POST',
		url : 'validateBoothToMakeConfirmAction.action',  
		dataType : 'json',
		data : {task :JSON.stringify(jObj)} 
	}).done(function(result){ 
	   if(result != null && result.status=="Success"){
		   alert("BOOTH CONFIRM SUCCESSFULLY.");
		  getLocationLevelWiseBoothDetails(filterLevel,filterValue,"All");
	  }else if(result != null && result.status=="Faliure"){
		  alert("Failure");
	  }else{
		   alert(result.status);
	  }
	});
}
function exportToExcel(tableId)
{
	tableToExcel(''+tableId+'', 'Booth Committee Details.. ');
}
function gettingBoothInchargeRoleDetails(boothId,locationValue,enrllmentId){
		
		$("#boothInchargeRoleDivId").html('');

	    var  enrllmentId =0;
			var jsObj={
				boothId:boothId,
				constituencyId:locationValue,
				boothInchargeEnrollmentId:enrllmentId
			}
			$.ajax({
				type : "POST",
				url : "gettingBoothInchargeRoleDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){	
			if(result != null){
				buildBoothInchargeRoleDetails(result);	
			}else{
				$("#boothInchargeRoleDivId").html('NO DATA AVAILABLE.');
			}
		});	
	}
	function buildBoothInchargeRoleDetails(result){
	var str ='';
	str +='<div class="col-sm-12">';
	str +='<div class= "row">';
	str +='<div class= "col-sm-5 col-sm-offset-3">';
	str +='<table class="table table-bordered" id="roleTableId">';
	str +='<thead>';
	str +='<tr>';
	str +='<th style="background-color:#d3d3d3">ROLE NAME</th>';
	str +='<th style="background-color:#d3d3d3">TOTAL</th>';
	str +='<th style="background-color:#d3d3d3">FILLED</th>';
	str +='<th style="background-color:#d3d3d3">VACANCY</th>';
	str +='</tr>';
	str +='</thead>';
	str +='<tbody>';
	str +='<tr>';
	for( var i in result){
		str +='<td>'+result[i].roleName+'</td>';
		str +='<td>'+result[i].maxMemberCount+'</td>';
		str +='<td>'+result[i].count+'</td>';
		str +='<td>'+result[i].vacancyCount+'</td>';
		str +='</tr>';
	}
	str +='</tbody>';
	str +='</table>';
	str +='</div>';
	str +='</div>';
	str +='</div>';
	$("#boothInchargeRoleDivId").html(str);
}


function getAccessLocationsForUser(){
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Location</option>');
	var jsObj={};
	$.ajax({
		type : "POST",
		url : "getUsersAccessLocatiosLIstAction.action",
		data : {task:JSON.stringify(jsObj)} 
	}).done(function(result){	
		if(result != null){
			for(var i in result)
			{
				$('#constituencyId').append('<option value="'+result[i].locationId+'"> '+result[i].locationName+' </option>');
			}	
		}
	});
}


function getCommitteeFinalizedBoothListforUnlock(id){
	$("#committeeLocationIds1  option").remove();
	$("#committeeLocationIds1").append('<option value="0">Select Location</option>');
	if(parseInt(id) ==0){
		alert("Please select Constituency.");
		return;
	}
	var jsObj={
		assemblyIdsArr:[id]
	};
	$.ajax({
		type : "POST",
		url : "getCommitteeFinalizedBoothListforUnlockAction.action",
		data : {task:JSON.stringify(jsObj)} 
	}).done(function(result){	
		if(result != null && parseInt(id)>0){
			for(var i in result)
			{
				$('#committeeLocationIds1').append('<option value="'+result[i].locationId+'"> BOOTH NO - '+result[i].locationName+' </option>');
			}
		}
	});
}

function removeLock(){
	var committeeId = $('#committeeLocationIds1').val();
	var constituencyId = $('#constituencyId').val();
	var jsObj={boothCommitteeIdsArr:[committeeId]};	
	$.ajax({
		type : "POST",
		url : "unlockBoothCommitteesByCommitteeIdListAction.action",
		data : {task:JSON.stringify(jsObj)} 
	}).done(function(result){	
		if(result != null){
			if(result.trim().length =="success"){
				alert("Successfully removed Lock.");
				getCommitteeFinalizedBoothListforUnlock(constituencyId)
			}else if(result.trim().length =="failure"){
				alert("Unable to remove lock .Please try later.");
			}else{
				alert("Successfully removed Lock.");
				getCommitteeFinalizedBoothListforUnlock(constituencyId)
			}
		}
	});
}
getAccessLocationsForUser();
