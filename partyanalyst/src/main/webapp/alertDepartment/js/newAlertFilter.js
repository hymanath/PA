
function assignUser1(globaldepartmentsArrForFilterView)
{
	console.log(globaldepartmentsArrForFilterView)
	var str='';
	str+='<div id="filterMainDiv">';
		str+='<div class="row" >';
		
				str+='<div id="filterDivId">';  
					str+='<div class="col-sm-12">';
						str+='<div id="assignErrorDivId" class="text-danger"></div>';
					str+='</div>';
					 str+='<div class="col-sm-3 m_top10">';
						str+='<label>Department<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
						str+='<select  class="chosenSelect" id="departmentSelectedId">';
							str+='<option value="0">Select Department</option>';
							if(globaldepartmentsArrForFilterView !=null && globaldepartmentsArrForFilterView.length>0){
								for(var i in globaldepartmentsArrForFilterView){
									str+='<option value="'+globaldepartmentsArrForFilterView[i].id+'" selected>'+globaldepartmentsArrForFilterView[i].name+'</option>';
								}
							}
							
						str+='</select>';
					str+='</div>'; 
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Impact Level<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
						str+='<select  class="chosenSelect" id="locationLevelSelectedId" name="alertAssigningVO.levelId">	';
						str+='</select>';
					str+='</div>';
					str+='<div id="parentLevelDivId"> </div>';
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Status<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgStusId"></span></label>';
						str+='<select  class="chosenSelect" id="statusSelectedId">';
							str+='<option value="0">All</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Designation<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDesgId"></span></label>';
						str+='<select  id="designationsWiseId" class="chosenSelect">';
							str+='<option value="0">All</option>';
						str+='</select>';
					str+='</div>';
					str+='<div class="col-sm-3 m_top10">';
						str+='<label>Priority<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDeptId"></span></label>';
						str+='<select class="chosenSelect" id="PriorityId" >	';
							//str+='<option value="0">Select Priority</option>';
							str+='<option value="0" selected>All</option>';
							str+='<option value="1">High</option>';
							str+='<option value="2">Medium</option>';
							str+='<option value="3">Low</option>';
						str+='</select>';
					str+='</div>';
					/* str+='<div class="col-sm-3 m_top10">';
						str+='<label>Date<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgDateId"></span></label>';
						str+='<div class="input-group">';
							str+='<span class="input-group-addon">';
								str+='<i class="glyphicon glyphicon-calendar"></i>';
							str+='</span>';
							str+='<input type="text" class="form-control" id="dateRangeId"/>';
						str+='</div>';
					str+='</div>'; */
				str+='</div>';
				
			str+='</div>';
			str+='<div class="row">';
				str+='<div class="col-sm-9 m_top20">';
					str+='<label class="checkbox-inline"><input type="checkbox" name="lagDays" id="lagDaysId" checked/>Lag Days</label>';
					str+='<div id="tourSlider"></div>';
					str+='<label class="checkbox-inline pull-right"><input type="checkbox" name="MoreDays" id="moreDaysId"/>More than 365 Days</label>';
				str+='</div>';
				str+='<div class="col-sm-3" style="margin-top: 40px;">';
					str+='<button class="btn btn-primary btn-sm text-capital subOrdinateFilterAlertsCls"  type="button">get Details</button>';
				str+='</div>';
			str+='</div>';
				
			/* 
			str+='<div class="panel-footer text-right pad_5 border_1 bg_EE">';
			str+='<button class="btn btn-primary btn-sm text-capital" id="assignOfficerId" type="button">getDeatils</button>';
			str+='<img style="display: none;" alt="Processing Image" src="./images/icons/search.gif" id="assiningLdngImg">';
			str+='<span class="text-success" id="assignSuccess"></span>'; */
		
		str+='</div>';
	str+='</div>';
	$("#assignedUser1").html(str);
	$(".chosenSelect").chosen({width:'100%'});
	$("#dateRangeId").daterangepicker({
		singleDatePicker : true
	});
	$("#tourSlider").rangeSlider({arrows:false,bounds:{min: 0, max: 365},defaultValues:{min: 0, max: 180}});
	
	getDepartmentWiseLevelsDistrictOfficer();
	getStatus();
}
$(document).on("click","#lagDaysId",function(){
	if($("#lagDaysId").is(':checked') == true)
	{
		$("#tourSlider").rangeSlider({
			bounds:{min: 0, max: 365},
			defaultValues:{min: 0, max: 180}
		});
		$("#moreDaysId").closest("label").show();
		$("#moreDaysId").closest("label").show();
	}else{
		$("#tourSlider").rangeSlider({
			bounds: {min: 0, max: 1}
		});
		$("#moreDaysId").closest("label").hide();
	}
});
$(document).on("click","#moreDaysId",function(){
	if($("#moreDaysId").is(':checked') == false)
	{
		$("#tourSlider").rangeSlider({
			bounds:{min: 0, max: 365},
			defaultValues:{min: 0, max: 180}
		});
	}else{
		$("#tourSlider").rangeSlider({
			bounds: {min: 0, max: 1}
		});
	}
});

$(document).on('change','#departmentSelectedId', function() {
		getDepartmentWiseLevelsDistrictOfficer()
});
function getDepartmentWiseLevelsDistrictOfficer(){
	
	$("#locationLevelSelectedId").html('');
	var departmentId = $("#departmentSelectedId option:selected").val();
	
	var jsObj = {
		departmentId : departmentId
	}
	$.ajax({
      type:'GET',
      url: 'getlevlsForDepartmntAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildDepartmentWiseLevelsDistrictOfficer(result);
		}
	});
	
}
function buildDepartmentWiseLevelsDistrictOfficer(result){
	
	var str='';	
	str+='<option value="0">Select Level</option>';
	for(var i in result){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
	}	
	$("#locationLevelSelectedId").html(str);
	$("#locationLevelSelectedId").trigger("chosen:updated");
		
}
$(document).on('change', '#locationLevelSelectedId', function(){
	
	getChildLevelValuesForSubTask1($(this).attr('id'));
	$("#designationsWiseId").html("<option value='0'>All</option>")
	$("#designationsWiseId").trigger('chosen:updated');
});

function getChildLevelValuesForSubTask1(buildId){
	
	var levelId = $("#"+buildId+"").val();
	var departmentId = $("#departmentSelectedId option:selected").val();
	var jsObj = {
		departmentId : departmentId,
		levelId : levelId,
		parentLevelId : globalUserLevelId,
		parentLevelValueArr : globalUserLevelValues
		
	}
	$.ajax({
      type:'GET',
      url: 'getChildLevelValuesForSubTaskAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildChildLevelValuesForSubTask1(result,buildId,departmentId);
			
		}
			
	});
}
 
function buildChildLevelValuesForSubTask1(result,buildId,departmentId){
	
	var buildId="parentLevelDivId";
	if(result !=null && result.length>0){
	var str='';		
		for(var i in result){
			if(i<result.length-1){
				str+='<div class="col-sm-3 m_top10">';
					str+='<label>'+result[i].name+'<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select  class="chosenSelect dynamicSelectList childValCls" attr_dyna_name="'+result[i].name+'" id="locationSubLevelSelectedId'+result[i].id+'" onchange="getGovtSubLevelInfo1('+departmentId+','+result[i].id+')"  attr_dynamic_levelid="'+result[i].id+'"><option value="">All</option></select>';
				str+='</div>';
			}else{
				str+='<div class="col-sm-3 m_top10">';
					str+='<label>Location<span style="color:red">*</span>&nbsp;&nbsp; <span style="color:#18A75A;" id="errMsgLvlId"></span></label>';
					str+='<select class="chosenSelect locationCls1 childValCls" id="locationSubLevelSelectedId'+result[i].id+'" attr_dynamic_levelid="'+result[i].id+'"><option value="">All</option></select>';
				str+='</div>';
			}
		}
		
		$("#"+buildId+"").html(str);
	}else{
		$("#"+buildId+"").html('');
		$("#"+buildId+"").trigger('chosen:updated');
	}
	$(".chosenSelect").chosen();
	if(result !=null && result.length>0){
	for(var i in result){
		var levelId = result[i].id;
		if(result[i].idnameList !=null && result[i].idnameList.length>0){
			var newStr='';
			newStr+='<option value="0">All</option>';
			//newStr+='<option value="0">Select '+result[i].name+'</option>';
			for(var j in result[i].idnameList){
				 newStr+='<option value="'+result[i].idnameList[j].id+'">'+result[i].idnameList[j].name+'</option>';
			}			
			$("#locationSubLevelSelectedId"+levelId+"").html(newStr);
			$("#locationSubLevelSelectedId"+levelId+"").trigger("chosen:updated");
		}else{
			$("#locationSubLevelSelectedId"+levelId+"").html('');
			$("#locationSubLevelSelectedId"+levelId+"").append('<option value="0">All</option>');
			$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
		}
	}
	}
	
}

$(document).on('change','.locationCls1', function(evt, params) {
		designationsByDepartments();
		
});
$(document).on("change","#statusSelectedId",function(){
	designationsByDepartments();
});
function getStatus(){
	$("#statusSelectedId").html('');
	var type = $(".switch-btn-alertType li.active").attr("attr_type")
	if(type == 'alert')
	{
		type = 'alerts'
	}
	var jsObj = {
		type : type //alerts or subTask
	}
	$.ajax({
      type:'GET',
      url: 'getStatusByTypeAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			buildStatusForLevls(result,type);
		}
	});
}
function buildStatusForLevls(result,type){
	var str='';	
	//str+='<option value="0">Select Status</option>';
	str+='<option value="0">All</option>';
	for(var i in result){
		if(type == 'alerts' && result[i].id != 1){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}else if(type == 'subTask'){
			str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
		}
	}	
	$("#statusSelectedId").html(str);
	$("#statusSelectedId").trigger("chosen:updated");
}

function getGovtSubLevelInfo1(departmentId,levelId){
	$("#designationsWiseId").html("<option value='0'>All</option>");
	$("#designationsWiseId").trigger("chosen:updated");	
	
	var locaionLevelValues=$("#locationSubLevelSelectedId"+levelId+"").val();
	var jsObj = {
		departmentId : departmentId,
		levelId :levelId,
		levelValue:locaionLevelValues
	}
	$.ajax({
      type:'GET',
      url: 'getGovtSubLevelInfoAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null){
			buildGovtSubLevelInfoAction1(result,levelId);
		}
			
	});
}
function buildGovtSubLevelInfoAction1(result,levelId){
		if(result !=null && result.idnameList !=null && result.idnameList.length>0){
			var str='';
			str+='<option value="0">All</option>';
			//str+='<option value="0">Select '+result.name+'</option>';
			for(var i in result.idnameList){
				str+='<option value="'+result.idnameList[i].id+'">'+result.idnameList[i].name+'</option>';
			}
			$("#locationSubLevelSelectedId"+result.id+"").html(str);
			$("#locationSubLevelSelectedId"+result.id+"").trigger("chosen:updated");
			
		}else{
			var impactLevel = $("#locationLevelSelectedId").val();
			if(impactLevel == 5){
				if(levelId == 5)
				{
					$("#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
				}
			}else if(impactLevel == 6){
				if(levelId == 5)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").trigger('chosen:updated');
				}else if(levelId == 6)
				{
					$("#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
					$("#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated');
				}
				
			}else if(impactLevel == 7){
				if(levelId == 5)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
				}else if(levelId == 6)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").trigger('chosen:updated');
				}
			
			}else if(impactLevel == 8){
				if(levelId == 5)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
				}else if(levelId == 6)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").trigger('chosen:updated');
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+"").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+"").trigger('chosen:updated');
				}else if(levelId == 7)
				{
					levelId = levelId + 1
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").html('<option value="0">All</option>');
					$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId").trigger('chosen:updated');
				}
			}
			 
			/* $("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").html('<option value="0">All</option>');
			$("#locationSubLevelSelectedId"+levelId+",#designationsWiseId,#PriorityId,#statusSelectedId").trigger('chosen:updated'); */
			
		}
		
		
	
}
function designationsByDepartments()
{
	$("#designationsWiseId").empty();
	$("#designationsWiseId").trigger("chosen:updated");
	var LevelId = $("#locationLevelSelectedId").chosen().val();
	var deprtmntId = $("#departmentSelectedId option:selected").val();;
	var levelValue = $(".locationCls1").chosen().val();
	var jsObj = {
		departmentId	: deprtmntId,
		levelId			: LevelId,
		levelValue			: levelValue
	}
	$.ajax({
      type:'GET',
      url: 'getDesignationsByDepartmentNewAction.action',
	  data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
		var str='';
		//str+='<option value="0">Select Designation</option>';
		str+='<option value="0">All</option>';
		if(result != null && result.length >0){
			for(var i in result)
			{
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#designationsWiseId").html(str);
		$("#designationsWiseId").trigger("chosen:updated");
	});
}
$(document).on("click",".subOrdinateFilterAlertsCls",function(){
	getSubOrdinateFilterAlertsOverview();
});


function getSubOrdinateFilterAlertsOverview(){
	

	var lvelIdsArr =[];
	var deginaIdsArr = [];
	var levelValuesArr = [];
	var deptIdsArr = [];
	var statusIdsArr = [];
	var levelId = 0;
	
	var fromDays = 0;
	var toDays = 0;
	var lagChckedValue =$("#lagDaysId").is(':checked') ? 1 : 0;
	var checkedValue;
		if(lagChckedValue == 1){
			 fromDays = $(".ui-rangeSlider-leftLabel").find(".ui-rangeSlider-label-value").html();
			 toDays = $(".ui-rangeSlider-rightLabel").find(".ui-rangeSlider-label-value").html();
			lagChckedValue = "true";
		}else{
			lagChckedValue = "false";
		}
		
	var moreDAysChckedValue =$("#moreDaysId").is(':checked') ? 1 : 0;
 	  if(moreDAysChckedValue == 1){
		   fromDays = 0;
		   toDays = 0;
		   lagChckedValue = "true";
			moreDAysChckedValue = "true";
		}else{
			moreDAysChckedValue = "false";
		}
	if($("#departmentSelectedId").val() == null || $("#departmentSelectedId").val() == 0){
		$("#assignErrorDivId").html("Please select department");
		return;
	}else{
		deptIdsArr.push($("#departmentSelectedId").val());
	}
	if($("#locationLevelSelectedId").val() == null  || $("#locationLevelSelectedId").val() == 0)
	{
		
		$("#assignErrorDivId").html("Please select impact level");
		return;
	}else{
		lvelIdsArr.push($("#locationLevelSelectedId").val());
		levelId = $("#locationLevelSelectedId").val();
	}
	
	var dynamicLocationValue=$('.dynamicSelectList').val();
	var hasError=false;
	var displayName = "";
	if (typeof(dynamicLocationValue) != "undefined"){
		
		
		$('.dynamicSelectList').each(function(){
			if(!hasError){
				displayName = $(this).attr('attr_dyna_name');
				var id =  $(this).attr('id');
				var dynamicLocationValue=$('#'+id+'').val();
				if(dynamicLocationValue == null )
				{
					hasError=true;
					$("#assignErrorDivId").html("Please select "+displayName+". ");
					return;
				}			
			}else{
				return;
			}
		});	
		if(hasError)
			return;
		
	}
		var childLevelVals =[];	
		var childLevelId = 0; //0
		$('.childValCls').each(function(){
			
			var dynamicLevlId = $(this).attr('attr_dynamic_levelid');
			var id =  $(this).attr('id');
			var dynamicLocationValue=$('#'+id+'').val();
			
			if(dynamicLocationValue != null &&  dynamicLocationValue != 0)
				{
					childLevelVals =[];	
					childLevelId =dynamicLevlId;
					childLevelVals.push(dynamicLocationValue);
				}
		});
	
	hasError=false;
	if(!hasError){
		var locationValue=$(".locationCls1").val();
		if(locationValue == null)
		{
			$("#assignErrorDivId").html("Please select Location. ");
			return;
		}
	}
	if($("#designationsWiseId").val() == null)
	{
		$("#assignErrorDivId").html("Please select designation");
		return;
	}
	if($("#statusSelectedId").val() == null)
	{
		$("#assignErrorDivId").html("Please select Status");
		return;
	}
	if($("#PriorityId").val() == null )
	{
		$("#assignErrorDivId").html("Please select Status");
		return;
	}
	var priorityId = $("#PriorityId").val();
	if($("#designationsWiseId").val() != null && $("#designationsWiseId").val() != 0){
		deginaIdsArr.push($("#designationsWiseId").val());
	}

	if($("#statusSelectedId").val() != null && $("#statusSelectedId").val() != 0){
		statusIdsArr.push($("#statusSelectedId").val());
	}
	var alertType = getAlertType();
	$("#assignErrorDivId").html(' ');
	$("#getSubOrdinateFilterAlertsOverview").html(spinner);
	var jsObj = {
	 
		fromDate : currentFromDate,
		toDateStr : currentToDate,
		govtLevelIds :lvelIdsArr , //locationLevelSelectedId //impact level
		locationValues : [], //locationSubLevelSelectedId
		desigIds : deginaIdsArr,		 //designationsWiseId	
		statusIds : statusIdsArr,      //statusSelectedId
		deptIds : deptIdsArr,			//departmentIds
		priorityId : priorityId,				//PriorityId
		childLevelId:childLevelId,
		childLevelVals:childLevelVals,
		alertType : alertType,
		isLagChkd : lagChckedValue, // true
		isMoreThanYrChkd : moreDAysChckedValue, //check true or false true - 0,0 --- false (particular values)
		lagStartCnt : fromDays,
		lagEndCnt : toDays,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr
    
	}
	$.ajax({
		type:'GET',
		url: 'getSubOrdinateFilterAlertsOverviewAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildSubOrdinateFilterAlertsOverview(result,levelId);
		}else{
			$("#getSubOrdinateFilterAlertsOverview").html("NO DATA");
		}
	});
}
function buildSubOrdinateFilterAlertsOverview(result,levelId)
{
	var str='';
	str+='<div class="table-responsive">';
		str+='<table class="table table-bordered table-condensed">';
			str+='<thead style="background-color:#ccc;">';
				str+='<th>Level</th>';
				if(levelId != 0 && levelId ==1 || levelId ==2 || levelId ==3 || levelId ==4){
					str+='<th>State</th>';
				}if(levelId != 0 && levelId ==5){
					str+='<th>District</th>';
				}else if(levelId != 0 && levelId ==6){
					str+='<th>District</th>';
					str+='<th>Division</th>';
				}else if(levelId != 0 && levelId ==7){
					str+='<th>District</th>';
					str+='<th>Division</th>';
					str+='<th>Sub Division</th>';
				}else if(levelId != 0 && levelId ==8){
					str+='<th>District</th>';
					str+='<th>Division</th>';
					str+='<th>Sub Division</th>';
					str+='<th>Mandal</th>';
				}else if(levelId != 0 && levelId ==9){
					str+='<th>District</th>';
					str+='<th>Division</th>';
					str+='<th>Sub Division</th>';
					str+='<th>Mandal</th>';
					str+='<th>Local Election Body</th>';
				}else if(levelId != 0 && levelId ==10){
					str+='<th>District</th>';
					str+='<th>Division</th>';
					str+='<th>Sub Division</th>';
					str+='<th>Mandal</th>';
					str+='<th>Local Election Body</th>';
					str+='<th>Panchayat</th>';
				}
				
				str+='<th>Designation</th>';
				str+='<th>Total</th>';
				for(var i in result[0].list1[0].list2[0].subList1)
				{
					str+='<th>'+result[0].list1[0].list2[0].subList1[i].name+'</th>';
				}
				
			str+='</thead>';
				for(var i in result)
				{
					
					for(var j in result[i].list1)
					{
						
						for(var k in result[i].list1[j].list2)
						{
							str+='<tr>';
									str+='<td>'+result[i].name+'</td>';
									if(levelId != 0 && levelId ==1 || levelId ==2 || levelId ==3 || levelId ==4){
										if(result[i].list1[j].name != null){
											str+='<td>'+result[i].list1[j].name+'</td>';
										}else{
											tr+='<td>-</td>';
										}
									}else if(levelId != 0 && levelId ==5){
										if(result[i].list1[j].name != null){
											str+='<td>'+result[i].list1[j].name+'</td>';
										}else{
											str+='<td>-</td>';
										}
									}else if(levelId != 0 && levelId ==6){
										if(result[i].list1[j].distName != null && result[i].list1[j].distName != ""){
											str+='<td>'+result[i].list1[j].distName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].name != null){
											str+='<td>'+result[i].list1[j].name+'</td>';
										}else{
											str+='<td>-</td>';
										}
									}else if(levelId != 0 && levelId ==7){
										if(result[i].list1[j].distName != null && result[i].list1[j].distName != ""){
											str+='<td>'+result[i].list1[j].distName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].divName != null && result[i].list1[j].divName != ""){
											str+='<td>'+result[i].list1[j].divName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].name != null){
											str+='<td>'+result[i].list1[j].name+'</td>';
										}else{
											str+='<td>-</td>';
										}
									}else if(levelId != 0 && levelId ==8){
										if(result[i].list1[j].distName != null && result[i].list1[j].distName != ""){
											str+='<td>'+result[i].list1[j].distName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].divName != null && result[i].list1[j].divName != ""){
											str+='<td>'+result[i].list1[j].divName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].subDivName != null && result[i].list1[j].subDivName != ""){
											str+='<td>'+result[i].list1[j].subDivName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].name != null){
											str+='<td>'+result[i].list1[j].name+'</td>';
										}else{
											str+='<td>-</td>';
										}
									}else if(levelId != 0 && levelId ==9){
										if(result[i].list1[j].distName != null && result[i].list1[j].distName != ""){
											str+='<td>'+result[i].list1[j].distName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].divName != null && result[i].list1[j].divName != ""){
											str+='<td>'+result[i].list1[j].divName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].subDivName != null && result[i].list1[j].subDivName != ""){
											str+='<td>'+result[i].list1[j].subDivName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].mandalName != null && result[i].list1[j].mandalName != ""){
											str+='<td>'+result[i].list1[j].mandalName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].name != null){
											str+='<td>'+result[i].list1[j].name+'</td>';
										}else{
											str+='<td>-</td>';
										}
									}else if(levelId != 0 && levelId ==10){
										if(result[i].list1[j].distName != null && result[i].list1[j].distName != ""){
											str+='<td>'+result[i].list1[j].distName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].divName != null && result[i].list1[j].divName != ""){
											str+='<td>'+result[i].list1[j].divName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].subDivName != null && result[i].list1[j].subDivName != ""){
											str+='<td>'+result[i].list1[j].subDivName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].mandalName != null && result[i].list1[j].mandalName != ""){
											str+='<td>'+result[i].list1[j].mandalName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].LEBName != null && result[i].list1[j].LEBName != ""){
											str+='<td>'+result[i].list1[j].LEBName+'</td>';
										}else{
											str+='<td>-</td>';
										}
										if(result[i].list1[j].name != null && result[i].list1[j].name != ""){
											str+='<td>'+result[i].list1[j].name+'</td>';
										}else{
											str+='<td>-</td>';
										}
									}
									str+='<td>'+result[i].list1[j].list2[k].name+'</td>';
									
									if(result[i].list1[j].list2[k].count !=null && result[i].list1[j].list2[k].count>0){
										str+='<td class="getAlertDtlsFilter" attr_disig_id="'+result[i].list1[j].list2[k].id+'" attr_level_id="'+result[i].id+'" attr_childval_id="'+result[i].list1[j].id+'" attr_childlvl_id="" attr_status_id="0" attr_total_count="'+result[i].list1[j].list2[k].count+'" attr_name="Total" style="cursor:pointer;">'+result[i].list1[j].list2[k].count+'</td>';
									}else{
										str+='<td> - </td>';
									}
									
							for(var l in result[i].list1[j].list2[k].subList1)
							{
								if(result[i].list1[j].list2[k].subList1[l].count !=null && result[i].list1[j].list2[k].subList1[l].count>0){
									str+='<td class="getAlertDtlsFilter" attr_disig_id="'+result[i].list1[j].list2[k].id+'" attr_level_id="'+result[i].id+'" attr_childval_id="'+result[i].list1[j].id+'" attr_childlvl_id="" attr_status_id="'+result[i].list1[j].list2[k].subList1[l].id+'" attr_total_count="'+result[i].list1[j].list2[k].subList1[l].count+'" attr_name="'+result[i].list1[j].list2[k].subList1[l].name+'" style="cursor:pointer;">'+result[i].list1[j].list2[k].subList1[l].count+'</td>';
								}else{
									str+='<td> - </td>';
								}
									
								
							}
							str+='</tr>';
						}
					}
				}
			
		str+='</table>';
	str+='</div>';
	$("#getSubOrdinateFilterAlertsOverview").html(str);
}
$(document).on("click",".getAlertDtlsFilter",function(){
	var designationId = $(this).attr("attr_disig_id");
	var levelId = $(this).attr("attr_level_id");
	var locationValueId = $(this).attr("attr_childval_id");
	var statusId = $(this).attr("attr_status_id");
	var totalCount = $(this).attr("attr_total_count");
	var statusName = $(this).attr("attr_name");
	getLocationFilterClickDetails(designationId,levelId,locationValueId,statusId,totalCount,statusName);
	
	
});
//getLocationFilterClickDetails();
function getLocationFilterClickDetails(designationId,levelId,locationValueId,statusId,totalCount,statusName){
	
	var desigArr=[];
	var levelIdsArr =[];
	var locationValueArr=[];
	var statusIdsArr=[];
	var deptIdsArr=[];
	
	
	var fromDays = 0;
	var toDays = 0;
	var lagChckedValue =$("#lagDaysId").is(':checked') ? 1 : 0;
	var checkedValue;
		if(lagChckedValue == 1){
			 fromDays = $(".ui-rangeSlider-leftLabel").find(".ui-rangeSlider-label-value").html();
			 toDays = $(".ui-rangeSlider-rightLabel").find(".ui-rangeSlider-label-value").html();
			lagChckedValue = "true";
		}else{
			lagChckedValue = "false";
		}
		
	var moreDAysChckedValue =$("#moreDaysId").is(':checked') ? 1 : 0;
 	  if(moreDAysChckedValue == 1){
		   fromDays = 0;
		   toDays = 0;
		   lagChckedValue = "true";
			moreDAysChckedValue = "true";
		}else{
			moreDAysChckedValue = "false";
		}
	
	if(designationId == null || designationId == 0){
		desigArr=[];
	}else{
		desigArr.push(designationId);
	}
	if(levelId == null || levelId == 0){
		levelIdsArr=[];
	}else{
		levelIdsArr.push(levelId);
	}
	if(locationValueId == null || locationValueId == 0){
		locationValueArr=[];
	}else{
		locationValueArr.push(locationValueId);
	}
	if(statusId == null || statusId == 0){
		statusIdsArr=[];
	}else{
		statusIdsArr.push(statusId);
	}
	
	
	
	
	
	deptIdsArr.push($("#departmentSelectedId").val());
	var priorityId = $("#PriorityId").val();
	var alertType = getAlertType();
	
		$("#alertManagementPopupBody").html('')
	
		$("#alertManagementPopup").modal({
			show: true,
			keyboard: false,
			backdrop: 'static'
		});
		$("#alertManagementPopupBody").html(spinner);
		
	var jsObj = {
	  
		fromDate : currentFromDate,
		toDateStr : currentToDate,
		govtLevelIds :levelIdsArr , //locationLevelSelectedId //impact level attr_level_id
		locationValues : locationValueArr, //locationSubLevelSelectedId attr_childval_id
		desigIds : desigArr,		 //designationsWiseId	
		statusIds : statusIdsArr,      //statusSelectedId attr_status_id
		deptIds : deptIdsArr,			//departmentIds
		priorityId : priorityId,				//PriorityId
		childLevelId:0,
		alertType : alertType,
		isLagChkd : lagChckedValue, // true
		isMoreThanYrChkd : moreDAysChckedValue, //check true or false true - 0,0 --- false (particular values)
		lagStartCnt : fromDays,
		lagEndCnt : toDays,
		paperIdArr:newspapersGlobalArr,
		chanelIdArr:channelGlobalArr,
		callCenterArr:callCenterGlobalArr,
		socialMediaTypeIdsArr:globalsocialMediaTypeIdsArr
    
	}
	$.ajax({
		type:'GET',
		url: 'getLocationFilterClickDetailsAction.action',
		data: {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result != null && result.length > 0){
			$("#totalAlertsModalTabId").html('');
			buildAlertDtlsBasedOnStatusClick(result,statusName,totalCount);
		}else{
			$("#alertManagementPopupBody").html('<div class="col-xs-12">NO DATA AVAILABLE</div>')
		}
	});
}