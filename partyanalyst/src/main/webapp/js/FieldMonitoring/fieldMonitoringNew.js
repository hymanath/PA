function onLoadCalls(){
	getDistricts();
	getConstituencies(0);
	getUsers(0);
	getCadreRegIssueType();
}

function getDistricts(){
	$('#districtId').find('option').remove();
	var jsObj = { 
	}
	$.ajax({
		type : 'GET',
		url : 'getCadreRegUserAssignedDistrictsAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#districtId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#districtId").trigger("chosen:updated");		
	});
}

function getConstituencies(districtId){
	$('#constituencyId').find('option').remove();
	var jsObj = { 
		districtId :districtId
	}
	$.ajax({
		type : 'GET',
		url : 'getUserAssignedConstituenciesAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#constituencyId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#constituencyId").trigger("chosen:updated");		
	});
}

function getUsers(constituencyId){
	$('#userId').find('option').remove();
	$("#userId").trigger("chosen:updated");		
	/*var constituencyId = [];
	if(type == "onchange")
		constituencyId = $("#constituencyId").val();*/
	
	var jsObj = { 
	constituencyId : constituencyId
	}
	$.ajax({
		type : 'GET',
		url : 'getUserAssignedUsersAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}  
	}).done(function(result){
		$("#userId").append('<option value="0">All</option>');
		if(result != null && result.length > 0){
			for(var i in result){
				$("#userId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#userId").trigger("chosen:updated");		
	});
}

/*$(document).on("click","#getDetails",function(){
	getTabUsersDetailsByVendorAndLocation();
});
*/
$(document).on("click","#getDetails",function(){
	getOverAllDataCollectorsCountsForFieldMontoring();
	getTabUsersDetailsByVendorAndLocation();
});

function getTabUsersDetailsByVendorAndLocation(){
	$("#tabUserDetailsDivId").html("");$("#errorDivId").html("");
	
	var userId = $("#userId").val();
	var constituencyId = $("#constituencyId").val();
	var districtId = $("#districtId").val();
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	
	/*if(userId == 'null' || userId == [])
	{
		$("#errorDivId").html("<span style='color: red;font-size:14px;'>Select Atleast One User</span>");
		return;
	}
	if(constituencyId == 'null' || constituencyId == [])
	{
		$("#errorDivId").html("<span style='color: red;font-size:14px;'>Select Atleast One Constituency</span>");
		return;
	}*/
    $("#tabUserDetailsImgId").show();	
	$("#dataCollectorsDiv").show();
	 var jsObj=
     {				
		cadreSurveyUserId : userId,
		constituencyId : constituencyId,
		//fromDate : fromDate,    //"2016-10-01",
		//toDate : toDate,		//"2016-10-18"
		districtId : districtId,
		stateId : 0
	 }
    $.ajax({
          type:'GET',
         // url: 'getTabUsersDetailsByVendorAndLocationNewAction.action',
          url: 'getDataCollectorsPerformanceDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  if(result != null){
		  buildTabUserDetails(result);
	  }
	  else{
		$("#tabUserDetailsImgId").hide();
		$("#dataCollectorsDiv").hide();
		$("#tabUserDetailsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
	  }
   });
}

function buildTabUserDetails(result){
	$("#dataCollectionsId").show();
	/*$("#totalDataCollectorsId").html(result.totalDataCollectors);
	$("#activeDataCollectorsId").html(result.activeUsers);
	$("#passiveDataCollectorsId").html(result.passiveUsers);*/
	
	if(result.subList != null && result.subList.length > 0){
		var str = '';
		
		str+='<h4 class="panel-title text-muted">Logged In Field Users</h4>';
		str+='<table class="table b_1 m_top10" id="detailsTable">';
			str+='<thead class="text-capitalize">';
				str+='<th>User Id</th>';
				str+='<th>user name</th>';
				str+='<th>user contact number</th>';
				str+='<th>today target</th>';
				str+='<th>first record</th>';
				str+='<th>recent record</th>';
				str+='<th>last hour</th>';
				str+='<th>Total</th>';
				str+='<th>open issues</th>';
				str+='<th>fixed issues</th>';
				str+='<th>closed issues</th>';
				str+='<th></th>';
				//str+='<th></th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.subList){
				str+='<tr>';
					if(result.subList[i].lastHourCount != null && result.subList[i].lastHourCount > 0)
						str+='<td class="issueCmpltd">'+result.subList[i].userName+'</td>';
					else
						str+='<td class="issuePending">'+result.subList[i].userName+'</td>';
					if(result.subList[i].tabUserName != null)
						str+='<td title="UserId : '+result.subList[i].userName+'">'+result.subList[i].tabUserName+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].mobileNo != null)
						str+='<td>'+result.subList[i].mobileNo+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].todayTarget != null)
						str+='<td>'+result.subList[i].todayTarget+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].firstRecord != null)
						str+='<td>'+result.subList[i].firstRecord+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].recentRecord != null)
						str+='<td>'+result.subList[i].recentRecord+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].lastHourCount != null)
						str+='<td>'+result.subList[i].lastHourCount+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].totalCount != null)
						str+='<td>'+result.subList[i].totalCount+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].openIssues != null && result.subList[i].openIssues > 0)
						str+='<td>'+result.subList[i].openIssues+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].fixedIssues != null && result.subList[i].fixedIssues > 0)
						str+='<td>'+result.subList[i].fixedIssues+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].closedIssues != null && result.subList[i].closedIssues > 0)
						str+='<td>'+result.subList[i].closedIssues+'</td>';
					else
						str+='<td> - </td>';
					str+='<td><i class="glyphicon glyphicon-cog manageIssues" attr_cadre_survey_user_id="'+result.subList[i].cadreSurveyUserId+'" attr_tab_user_info_id="'+result.subList[i].tabUserId+'" attr_cadre_survey_userName="'+result.subList[i].userName+'" attr_tab_userName="'+result.subList[i].tabUserName+'" attr_mobileNo="'+result.subList[i].mobileNo+'" attr_constituency_id="'+result.subList[i].constituencyId+'" title="Click Here to Manage Issues" style="cursor:pointer;"></i></td>';
					//str+='<td><i class="glyphicon glyphicon-eye-open userPerformanceCls" title="Click Here to View User Performance" style="cursor:pointer;" attr_cadre_survey_user_id="'+result.subList[i].cadreSurveyUserId+'" attr_tab_user_info_id="'+result.subList[i].tabUserId+'" attr_cadre_survey_userName="'+result.subList[i].userName+'" attr_tab_userName="'+result.subList[i].tabUserName+'" attr_mobileNo="'+result.subList[i].mobileNo+'"></i></td>'
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		
		$("#tabUserDetailsImgId").hide();
		$("#tabUserDetailsDivId").html(str);
	 $('#detailsTable').dataTable({
        "aaSorting": []
    }); 
	}
	else{
		$("#tabUserDetailsImgId").hide();
		$("#dataCollectionsId").hide();
		$("#tabUserDetailsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
	}
}

$(document).on("click",".userPerformanceCls",function(){
	var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
    var tabUserInfoId = $(this).attr("attr_tab_user_info_id");
	var cadreSurveyUserName = $(this).attr("attr_cadre_survey_userName");
	var tabUserName = $(this).attr("attr_tab_userName");
	var mobileNo = $(this).attr("attr_mobileNo");
	
	$(".PerfmCadreUserName").html(cadreSurveyUserName);
	$(".tabUserPerMblDetailsId").html(tabUserName+" - "+mobileNo);
	
	getUserPerformanceInfo(cadreSurveyUserId,tabUserInfoId);
});

function getUserPerformanceInfo(cadreSurveyUserId,tabUserInfoId){
	  $("#userPerformanceBodyId").html("");
	  $("#userPerformanceModal").modal("show");
	  $("#userPerformanceImgId").show();
	   var jsObj = { 
	   cadreSurveyUserId : cadreSurveyUserId,
	   tabUserInfoId : tabUserInfoId
	   }
	  
	   $.ajax({
          type:'POST',
          url: 'getUserPerformanceDetailsByUserAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null && result.length > 0)
			   buildUserPerformanceDetails(result[result.length - 1]);
			else{
				 $("#userPerformanceImgId").hide();
				 $("#userPerformanceBodyId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
			}
	   });
  }
  
  function buildUserPerformanceDetails(result){
	  var str = '';
	  
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12">';
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading" style="background-color:#7FB0EC">';
					str+='<h4 class="panel-title">Today <small>Till Now</small> '+result.overAllCount+' </h4>';
				str+='</div>';
				str+='<div class="panel-body pad_0">';
					str+='<table class="table table-bordered tableModalCls">';
						str+='<tr>';
						if(result.id > 8)
							str+='<td class="completed">';
						else if(result.id == 8)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from8to9Count+'</h4>';
								str+='<p>8AM - 9AM</p>';
							str+='</td>';
						if(result.id > 9)
							str+='<td class="completed">';
						else if(result.id == 9)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from9to10Count+'</h4>';
								str+='<p>9AM - 10AM</p>';
							str+='</td>';
						if(result.id > 10)
							str+='<td class="completed">';
						else if(result.id == 10)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from10to11Count+'</h4>';
								str+='<p>10AM - 11AM</p>';
							str+='</td>';
						if(result.id > 11)
							str+='<td class="completed">';
						else if(result.id == 11)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from11to12Count+'</h4>';
								str+='<p>11AM - 12PM</p>';
							str+='</td>';
						if(result.id > 12)
							str+='<td class="completed">';
						else if(result.id == 12)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from12to1Count+'</h4>';
								str+='<p>12PM - 1PM</p>';
							str+='</td>';
						if(result.id > 13)
							str+='<td class="completed">';
						else if(result.id == 13)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from1to2Count+'</h4>';
								str+='<p>1PM - 2PM</p>';
							str+='</td>';
						if(result.id > 14)
							str+='<td class="completed">';
						else if(result.id == 14)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from2to3Count+'</h4>';
								str+='<p>2PM - 3PM</p>';
							str+='</td>';
						if(result.id > 15)
							str+='<td class="completed">';
						else if(result.id == 15)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from3to4Count+'</h4>';
								str+='<p>3PM - 4PM</p>';
							str+='</td>';
						if(result.id > 16)
							str+='<td class="completed">';
						else if(result.id == 16)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from4to5Count+'</h4>';
								str+='<p>4PM - 5PM</p>';
							str+='</td>';
						if(result.id > 17)
							str+='<td class="completed">';
						else if(result.id == 17)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from5to6Count+'</h4>';
								str+='<p>5PM - 6PM</p>';
							str+='</td>';
						if(result.id > 18)
							str+='<td class="completed">';
						else if(result.id == 18)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from6to7Count+'</h4>';
								str+='<p>6PM - 7PM</p>';
							str+='</td>';
						if(result.id > 19)
							str+='<td class="completed">';
						else if(result.id == 19)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from7to8Count+'</h4>';
								str+='<p>7PM - 8PM</p>';
							str+='</td>';
						if(result.id > 20)
							str+='<td class="completed">';
						else if(result.id == 20)
							str+='<td class="active">';
						else
							str+='<td>';
								str+='<h4>'+result.from8pmto8amCount+'</h4>';
								str+='<p>8PM - 8AM</p>';
							str+='</td>';
							/*str+='<td class="completed arrowOpen">';
								str+='<h4 class="">60</h4>';
								str+='<p>8AM - 9AM</p>';
								str+='<div class="arrowBox">';
									str+='<div class="row">';
										str+='<div class="col-md-12 col-xs-12 col-sm-12">';
											str+='<small>Till 2 PM</small>';
										str+='</div>';
										str+='<div class="col-md-4 col-xs-12 col-sm-4">';
											str+='<h4>01/11/2016</h4>';
										str+='</div>';
										str+='<div class="col-md-8 col-xs-12 col-sm-8">';
											str+='<div class="progress">';
											  str+='<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">120';
											  str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</td>';
							str+='<td class="active">';
								str+='<h4>60</h4>';
								str+='<p>8AM - 9AM</p>';
							str+='</td>';*/
						str+='</tr>';
					str+='</table>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	
	$("#userPerformanceImgId").hide();
	$("#userPerformanceBodyId").html(str);
  }

$(document).on("click",".manageIssues",function(){
	clearErrorFields();
    $("#issueTypeDivId").hide();
    var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
    var tabUserInfoId = $(this).attr("attr_tab_user_info_id");
	var cadreSurveyUserName = $(this).attr("attr_cadre_survey_userName");
	var tabUserName = $(this).attr("attr_tab_userName");
	var mobileNo = $(this).attr("attr_mobileNo");
	var constituencyId = $(this).attr("attr_constituency_id");
	
	$(".modalCadreUserName").html(cadreSurveyUserName);
	$(".tabUserMblDetailsId").html(tabUserName+" - "+mobileNo);
	$("#hiddenCadreSurveyUserId").val(cadreSurveyUserId);
	$("#hiddenTabUserInfoId").val(tabUserInfoId);
	$("#hiddenConstituencyId").val(constituencyId);
	
    $("#submitId").attr("attr_cadre_survey_user_id",cadreSurveyUserId);
    $("#submitId").attr("attr_tab_user_info_id",tabUserInfoId);
	
	getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,0);
	getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId);
	
	/*var vendorId = $("#vendorId option:selected").val();
	var districtId =  $("#districtId option:selected").val();
	var constituencyId =  $("#constituencyId option:selected").val();
	
	if( districtId > 0 || constituencyId > 0){
		getVendorConstituencies('issueConstituencyId');
	}else{
		 getConstituencyByVendor();
	}*/
    $("#issuesModal").modal('show');
  });
  
function getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,issueStatusId){
		$("#issueDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	 
		$("#hiddenIssueStatusId").val(issueStatusId);
		
		var dates = $(".singleDate").val();
		var dateArr = dates.split("-");
		var fromDate;
		var toDate;
		if(dateArr != null){
			fromDate = dateArr[0];
			toDate = dateArr[1];
		}
		
	 var jsObj =
     {				
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		fromDate : fromDate,   
		toDate : toDate,
        issueStatusId : issueStatusId,
		stateId : 0
	 }
    $.ajax({
          type:'GET',
          url: 'getIssuesForATabUserByStatusNewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  if(result != null && result.length > 0)
		buildIssuesForATabUserByStatus(result);
		else
			$("#issueDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
   });
  }
  function buildIssuesForATabUserByStatus(result) {
	
	var str = '';

		str+='<table id="datatableId" class="table table-condensed">';
		str+='<thead>';
		str+='<th></th>';
		str+='</thead>';
		str+='<tbody>';
		for( var i in result) {
		str+='<tr>';
		str+='<td>';
		str += '<h4 class="text-capital">'+ result[i].issueType	+ '</h4>';
			str+='<button class="btn btn-success editBtn pull-right btn-sm" attr_value="'+i+'" attr_issueStatus="'+result[i].issueStatus+'">EDIT</button>';
			str+='<button class="btn btn-success pull-right btn-sm trackingIssueCls" type="button" attr_cadre_reg_issue_id="'+result[i].cadreRegIssueId+'" style="margin-right: 10px;">ISSUE TRACK</button>';
			//str += '</h4>';
			str +='<div class="descriptionCls">';
                str+='<h4> Description </h4>';			
				str += '<p class="issueDesc'+i+'">' + result[i].description + '</p>';
				str += '<p class="m_top10">';
				if(result[i].issueStatus == 'open')
				{
				str += '<span class="text-danger"><i>Issue Status :<span class="statusUpdate'+i+'">'
						+ result[i].issueStatus + '</span></i></span>';
				}else{
				str += '<span class="text-success"><i>Issue Status :<span class="statusUpdate'+i+'">'
						+ result[i].issueStatus + '-'+ result[i].updatedTime +'</span></i></span>';
				}
				str += '<span class="pull-right text-muted"><i>Informed Time:<span class="updatedTime'+i+'">'
						+ result[i].dateStr + '</span></i></span>';
				str += '</p>';
			str +='</div>';		
			str += '<div class="row descriptionEditCls" style="display:none;">';
				str += '<div class="col-md-12 col-xs-12 col-sm-12 m_top10">';
					str += '<textarea class="form-control issueDescEdit'+i+'" cols="120"></textarea>';
				str += '</div>';
				str += '<div class="col-md-4 col-xs-12 col-sm-4">';
					str += '<label>Change Issue Status</label>';
					str += '<select class="select" id="changeIssueStatusId'+i+'">';
					str += '</select>';
				str += '</div>';
				str += '<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<div class="row">'
					str +='<button class="btn btn-success m_top20 updateCls"  attr_value="'+i+'" attr_cadre_reg_issue_id="'+result[i].cadreRegIssueId+'">UPDATE</button>';
					str +='<button class="btn btn-default cancelUpdate m_top20 cancelCls" style="margin-left:10px;">CANCEL</button>';
					str+='</div>'
					str +='<span id="updateDivIdImg'+i+'" style="display:none;"><img src="images/search.gif"/></span>';
				    str +='<div id="updateStatusId'+i+'"></div>';
					
				str += '</div>';
			str += '</div>';
		str+='</td>';
		str+='</tr>';
		
		}
		str+='</tbody>';
		str+='</table>';
	
	$("#issueDivId").html(str);
	
	 $('#datatableId').dataTable({
        "aaSorting": []
    }); 
	
  }
  
function getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId){
	   $("#openIssuesId").html(0);
	   $("#fixedIssuesId").html(0);
	   $("#closedIssuesId").html(0);
	   $("#totalIssuesId").html(0);
	   
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	
	 var jsObj =
     {				
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		fromDate : fromDate,   
		toDate : toDate,
		stateId : 0
	}
    $.ajax({
          type:'GET',
          url: 'getIssuesCountsForATabUserNewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null && result.length > 0){
		   for(var i in result){
			   if(result[i].issueStatusId != null && result[i].issueStatusId == 1)
				   $("#openIssuesId").html(result[i].count);
			   else if(result[i].issueStatusId != null && result[i].issueStatusId == 2)
				   $("#fixedIssuesId").html(result[i].count);
			   else if(result[i].issueStatusId != null && result[i].issueStatusId == 3)
				   $("#closedIssuesId").html(result[i].count);
			   else
				  $("#totalIssuesId").html(result[i].count);
			}
		}
   });
  }

$(document).on("click","#addNewIssueId",function(){ 
	 $("#issueTypeDivId").show();
   });
   
 function getCadreRegIssueType(){
	 
    $.ajax({
          type:'GET',
          url: 'getCadreRegIssueTypeAction.action',
          dataType: 'json',
		  data: {}
   }).done(function(result){
	   $("#issueTypeId").append('<option value="0"> Select IssueType</option>');
     for(var i in result){
	      $("#issueTypeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
	 $("#issueTypeId").trigger("chosen:updated");
   });
  }
  
$(document).on("click","#submitId",function(){ 
      saveFieldIssue();
  });
  function saveFieldIssue(){
	  $("#submitButId").html("");
	  var constituencyId =$("#hiddenConstituencyId").val();  
	  var issueTypeId =$("#issueTypeId option:selected").val();
      var description =$("#descriptionId").val();
      var cadreSurveyUserId= $("#submitId").attr("attr_cadre_survey_user_id");
	  var tabUserInfoId= $("#submitId").attr("attr_tab_user_info_id");
	   if(issueTypeId == 0)
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Select Issue Type</span>");
		   return;
	   }
	   if(description.trim() == '' && description.length == 0)
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Enter description</span>");
		   return;
	   }
	  /* if(constituencyId == 0)       
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Select Constituency</span>");
		   return;
	   }else{
		    $("#submitButId").html("");
	   }*/
	  $("#savingDivIdImg").show();
	 var jsObj=
     {				
		issueTypeId :issueTypeId,
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId :tabUserInfoId,
		description :description,
		constituencyId : constituencyId
	 }
    $.ajax({
          type:'GET',
          url: 'saveFieldIssueAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    $("#savingDivIdImg").hide();
	   if(result.message == 'success' && result.resultCode == 1)
	   {
		    $("#submitButId").html("<span style='color: green;font-size:18px;'> Saved Successfully...</span>");
			setTimeout(function(){
				$("#issueTypeDivId").hide();
				clearErrorFields();
				getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,0);
				getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId);
				//$("#issuesModal").modal('hide');
				//getTabUsersDetailsByVendorAndLocation();
			}, 2000);
	   }else{
		   $("#submitButId").html("<span style='color: red;font-size:18px;'>Saved Failed.Please try Again.</span>");
		   setTimeout(function(){
				$("#issueTypeDivId").hide();
				clearErrorFields();
			}, 2000);
	   }
   });
  }
  
$(document).on("click",".issueTypeCls",function(){
	var issueType = $(this).attr("attr_val");
	var cadreUserId = $("#hiddenCadreSurveyUserId").val();
	var tabUserId = $("#hiddenTabUserInfoId").val();
	
	getIssuesForATabUserByStatus(cadreUserId,tabUserId,issueType);
});

$(document).on("click",".editBtn",function(){
	var value = $(this).attr("attr_value");
	var issueStatus = $(this).attr("attr_issueStatus");
	$(this).closest("td").find(".descriptionCls").hide();
	$(this).closest("td").find(".descriptionEditCls").show();
    $(this).closest("td").find(".trackingIssueCls").show();	
	getCadreRegIssueStatusType(value,issueStatus);
	var desc = $(".issueDesc"+value).text();          
	$(".issueDescEdit"+value).val(desc);
	$("#updateStatusId"+value).html('');

});
$(document).on("click",".cancelUpdate",function(){
	$(this).closest("td").find(".descriptionCls").show();
	$(this).closest("td").find(".descriptionEditCls").hide();
});
function getCadreRegIssueStatusType(value,issueStatus){
	 $("#changeIssueStatusId"+value).empty('').trigger('chosen:updated');
	$("#changeIssueStatusId"+value).chosen({width:'100%'});
    $.ajax({
          type:'GET',
          url: 'getCadreRegIssueStatusTypeAction.action',
          dataType: 'json',
		  data: {}
   }).done(function(result){
	   $("#changeIssueStatusId"+value).append('<option value="0"> Select ChangeIssueStatus</option>');
     for(var i in result){
		  if(result[i].name == issueStatus)
		  {
	      $("#changeIssueStatusId"+value).append('<option selected value='+result[i].id+' attr_text="'+result[i].name+'">'+result[i].name+'</option>');
		  }else{
			  $("#changeIssueStatusId"+value).append('<option value='+result[i].id+' attr_text="'+result[i].name+'">'+result[i].name+'</option>');
		  }
	 }
	 $("#changeIssueStatusId"+value).trigger("chosen:updated");
   });
  }
  
$(document).on("click",".updateCls",function(){
	var value = $(this).attr("attr_value");
	var cadreRegIssueId = $(this).attr("attr_cadre_reg_issue_id");
	var description = $(".issueDescEdit"+value).val();
	var  newStatusId = $('#changeIssueStatusId'+value).val();
	
	updateStatusToACadreRegIssue(value,cadreRegIssueId,description,newStatusId);
});
  
  function updateStatusToACadreRegIssue(value,cadreRegIssueId,description,newStatusId){
	  $("#updateStatusId"+value).html('');
	   var cadreSurveyUserId= $("#submitId").attr("attr_cadre_survey_user_id");
	   $(".issueDesc"+value).text(description);
	   var subValue = $("#changeIssueStatusId"+value+" option:selected").text();
	   $(".statusUpdate"+value).text(subValue);
	   
	   var CadreSrvId = $("#hiddenCadreSurveyUserId").val();
	   var tabUserId = $("#hiddenTabUserInfoId").val();
	   var issueStsId = $("#hiddenIssueStatusId").val();
	   
		if(description.trim() == '' && description.length == 0)
		{
			 $("#updateStatusId"+value).html("<span style='color: red;font-size:13px;'> Enter description</span>");
			 return;
		}
		if(newStatusId == 0)
		{
			$("#updateStatusId"+value).html("<span style='color: red;font-size:13px;'> Select IssueStatus</span>");
			return;
		}
		$("#updateDivIdImg"+value).show();
		
	   var jsObj =
      {				
		cadreRegIssueId :cadreRegIssueId,
		description : description,
		newStatusId : newStatusId
	  }
	   $.ajax({
          type:'POST',
          url: 'updateStatusToACadreRegIssueAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		    $("#updateDivIdImg"+value).hide();
		   if(result.message == 'success' && result.resultCode == 1)
		   {
			   $("#updateStatusId"+value).html("<span style='color: green;font-size:18px;'> update Successfully...</span>");
			   setTimeout(function(){
			   $(".editBtn").closest("td").find(".descriptionCls").show();
	           $(".editBtn").closest("td").find(".descriptionEditCls").hide();
			    $(".editBtn").closest("td").find(".trackingIssueCls").show();	
				getIssuesForATabUserByStatus(CadreSrvId,tabUserId,issueStsId);
				getIssuesCountsForATabUser(CadreSrvId,tabUserId);
				//getTabUsersDetailsByVendorAndLocation();
			}, 2000);
		   }else{
			    $("#updateStatusId").html("<span style='color: red;font-size:18px;'> update Failed.Please try Again..</span>");
		   }
		  var presentTime = moment().format("YYYY-MM-DD hh:mm A");
		  $(".updatedTime"+value).html(presentTime)
		  
	   });
  }
  
$(document).on("click",".trackingIssueCls",function(){
	var value = $(this).attr("attr_value");
	var cadreRegIssueId = $(this).attr("attr_cadre_reg_issue_id");
	trackingRegIssueByRegIssueId(value,cadreRegIssueId);
});	
$(document).on("click",".cancelCls",function(){    
	 $(this).closest("td").find(".trackingIssueCls").show();	
});
$(document).on("click",".closeIconCls",function(){    
	 getTabUsersDetailsByVendorAndLocation();	
});
$( document ).ready(function() {
    $("#stateId").val(0).trigger("chosen:updated");
	//$(".singleDate").val('');
});

function trackingRegIssueByRegIssueId(value,cadreRegIssueId){
	  $("#issueTrackingBodyId").html("");
	  $("#issueTrackingModal").modal("show");
	  $("#issueTrackingImgId").show();
	   var jsObj = { cadreRegIssueId :cadreRegIssueId}
	  
	   $.ajax({
          type:'POST',
          url: 'trackingRegIssueByRegIssueIdAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null && result.length > 0)
			   buildIssueTrackingDetails(result);
			else{
				 $("#issueTrackingImgId").hide();
				 $("#issueTrackingBodyId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
			}
	   });
  }
  
  function buildIssueTrackingDetails(result){
	  var str = '';
	  
	  str+='<table class="table b_1 m_top10" id="detailsTable">';
			str+='<thead class="text-capitalize">';
				str+='<th>Issue Type</th>';
				str+='<th>Description</th>';
				str+='<th>Status</th>';
				str+='<th>UserName</th>';
				str+='<th>Updated Time</th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].issueType+'</td>';
					str+='<td>'+result[i].description+'</td>';
					str+='<td>'+result[i].issueStatus+'</td>';
					str+='<td>'+result[i].userName+'</td>';
					str+='<td>'+result[i].dateStr+'</td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		$("#issueTrackingImgId").hide();
		$("#issueTrackingBodyId").html(str);
  }
  
function clearErrorFields()
{        
	$("#issueTypeId").val(0).trigger('chosen:updated');
    $("#descriptionId").val('');	
	$("#issueConstituencyId").val(0).trigger('chosen:updated');
	$("#submitButId").html('');
	$("#savingDivIdImg").hide();
}

function getOverAllDataCollectorsCountsForFieldMontoring(){
	   
		var dates = $(".singleDate").val();
		var dateArr = dates.split("-");
		var fromDate;
		var toDate;
		var state=0;
		var userId = $("#userId").val();
		var constituencyId = $("#constituencyId").val();
		var districtId = $("#districtId").val();
		if(dateArr != null){
			fromDate = dateArr[0];
			toDate = dateArr[1];
		}
		var jsObj = { 
		  stateId : state,
		  districtId : districtId,
		  constituencyId : constituencyId,
		  userId : userId,
		  fromDate : fromDate,		//"10/01/2016",
		  toDate : toDate		 	//"10/18/2016"  
		}
		$.ajax({
			type : 'GET',
			url : 'getOverAllDataCollectorsDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}  
		}).done(function(result){
			 $("#dataCollectorsDiv").show();
			if(result != null){
				$("#totalDataCollectorsId").html(result.totalDataCollectors);
				$("#totalRegId").html(result.todayRegCount);
				$("#todayActMebrId").html(result.todayActiveUsers);
				$("#lastOneHrActId").html(result.lastOneHrActUsers);
				$("#passOneHrId").html(result.passiveUsers);
				$("#todayNotYetStrtId").html(result.notYetStartedUsers);
				
			}	
		});
	}