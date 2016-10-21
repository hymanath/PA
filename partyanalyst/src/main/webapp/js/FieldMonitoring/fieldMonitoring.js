$(document).on("change", "#stateId", function(e){
			getVendors();
        });  
		$(document).on("change", "#vendorId", function(e){
			getVendorDistricts();
        });
        $(document).on("change", "#districtId", function(e){
			getVendorConstituencies('constituencyId');
        });	
		
		function getVendors(){
			
			clearVendors();
			clearDistricts();
			clearConstituencies('constituencyId');
			var stateId = $("#stateId option:selected").val();
			var jsObj = { stateId : stateId };
			
			if(stateId == 0){
				return;
			}
			 $("#vendorDivIdImg").show();
			$.ajax({
				type : 'GET',
				url : 'getVendorsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
				$("#vendorDivIdImg").hide();
				if(result!=null && result.length>0){
                  for(var i in result){
                     $('#vendorId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
                   }
                }
				refreshSelectBox('vendorId');
			});     
		}
		function getVendorDistricts(){
			clearDistricts();
			clearConstituencies('constituencyId');
			
			var stateId  = $("#stateId option:selected").val();
			var vendorId = $("#vendorId option:selected").val();
			var jsObj = { 
			              stateId : stateId,
			              vendorId : vendorId 
						};        
			$("#districtDivIdImg").show();
			$.ajax({
				type : 'GET',
				url : 'getVendorDistrictsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
			   	$("#districtDivIdImg").hide();
				if(result!=null && result.length>0){
                  for(var i in result){
                     $('#districtId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
                   }
                }
				refreshSelectBox('districtId');
			});
		}
		function getVendorConstituencies(selectBoxId){
			clearConstituencies(selectBoxId); 
			
			var vendorId = $("#vendorId option:selected").val();
			var districtId  = $("#districtId option:selected").val();
			var constituencieId  = $("#constituencyId option:selected").val(); 
			var jsObj = { 
			              vendorId : vendorId ,
						  districtId : districtId
						}; 
           $("#constituencyDivIdImg").show();						
			$.ajax({
				type : 'GET',
				url : 'getVendorConstituenciesAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
				 $("#constituencyDivIdImg").hide();
				if(result!=null && result.length>0){
                  for(var i in result){
					  if(result[i].id ==constituencieId)
					  {
                      $('#'+selectBoxId).append('<option selected value="'+result[i].id+'">'+result[i].name+'</option>');
					  }else{
						  $('#'+selectBoxId).append('<option  value="'+result[i].id+'">'+result[i].name+'</option>');
					  }
					  
                   }
                }
				refreshSelectBox(selectBoxId);
			});
		}
		function clearVendors(){
			$('#vendorId').find('option').remove();
            $('#vendorId').append('<option value="0">Select Vendor</option>');
			refreshSelectBox('vendorId');
		}
		function clearDistricts(){
			$('#districtId').find('option').remove();
            $('#districtId').append('<option value="0">Select District</option>');
			refreshSelectBox('districtId');
		}
		function clearConstituencies(selectBoxId){
			$('#'+selectBoxId).find('option').remove();
            $('#'+selectBoxId).append('<option value="0">Select Constituency</option>');
			refreshSelectBox(selectBoxId);
		}
		function refreshSelectBox(selectBoxId){
			$("#"+selectBoxId).trigger("chosen:updated");
		}
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
	
$(document).on("click",".issueTypeCls",function(){
	var issueType = $(this).attr("attr_val");
	var cadreUserId = $("#hiddenCadreSurveyUserId").val();
	var tabUserId = $("#hiddenTabUserInfoId").val();
	
	getIssuesForATabUserByStatus(cadreUserId,tabUserId,issueType);
});

$(document).on("click",".manageIssues",function(){ 
    $("#issueTypeDivId").hide();
    var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
    var tabUserInfoId = $(this).attr("attr_tab_user_info_id");
	var cadreSurveyUserName = $(this).attr("attr_cadre_survey_userName");
	var tabUserName = $(this).attr("attr_tab_userName");
	var mobileNo = $(this).attr("attr_mobileNo");
	
	$(".modalCadreUserName").html(cadreSurveyUserName);
	$(".tabUserMblDetailsId").html(tabUserName+" - "+mobileNo);
	$("#hiddenCadreSurveyUserId").val(cadreSurveyUserId);
	$("#hiddenTabUserInfoId").val(tabUserInfoId);
	
    $("#submitId").attr("attr_cadre_survey_user_id",cadreSurveyUserId);
    $("#submitId").attr("attr_tab_user_info_id",tabUserInfoId);
	
	getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,0);
	getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId);
	
	var vendorId = $("#vendorId option:selected").val();
	var districtId =  $("#districtId option:selected").val();
	var constituencyId =  $("#constituencyId option:selected").val();
	
	if( districtId > 0 || constituencyId > 0){
		getVendorConstituencies('issueConstituencyId');
	}else{
		 getConstituencyByVendor();
	}
    $("#issuesModal").modal('show');
  });
  
  function getIssuesCountsForATabUser(cadreSurveyUserId,tabUserInfoId){
	   $("#openIssuesId").html(0);
	   $("#fixedIssuesId").html(0);
	   $("#closedIssuesId").html(0);
	   $("#totalIssuesId").html(0);
	   
	var vendorId = $("#vendorId").val();
	var stateId = $("#stateId").val();
	var districtId = $("#districtId").val();
	var constituencyId = $("#constituencyId").val();
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	var locationType;
	var locationVal;

	if(constituencyId > 0){
		locationType = "constituency";
		locationVal = constituencyId;
	}
	else if(districtId > 0){
		locationType = "district";
		locationVal = districtId;
	}
	else{
		locationType = "state";
		locationVal = stateId;
	}
	
	 var jsObj =
     {				
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		fromDate : fromDate,   
		toDate : toDate,
		vendor : vendorId,
		locationType : locationType,
		locationVal : locationVal
     }
    $.ajax({
          type:'GET',
          url: 'getIssuesCountsForATabUserAction.action',
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
  
  function getIssuesForATabUserByStatus(cadreSurveyUserId,tabUserInfoId,issueStatusId){
		$("#issueDivId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');	 
		$("#hiddenIssueStatusId").val(issueStatusId);
		var vendorId = $("#vendorId").val();
		var stateId = $("#stateId").val();
		var districtId = $("#districtId").val();
		var constituencyId = $("#constituencyId").val();
		var dates = $(".singleDate").val();
		var dateArr = dates.split("-");
		var fromDate;
		var toDate;
		if(dateArr != null){
			fromDate = dateArr[0];
			toDate = dateArr[1];
		}
		var locationType;
		var locationVal;

		if(constituencyId > 0){
			locationType = "constituency";
			locationVal = constituencyId;
		}
		else if(districtId > 0){
			locationType = "district";
			locationVal = districtId;
		}
		else{
			locationType = "state";
			locationVal = stateId;
		}
	
	 var jsObj =
     {				
		cadreSurveyUserId : cadreSurveyUserId,
		tabUserInfoId : tabUserInfoId,
		fromDate : fromDate,   
		toDate : toDate,
        issueStatusId : issueStatusId,
		vendor : vendorId,
		locationType : locationType,
		locationVal : locationVal
	 }
    $.ajax({
          type:'GET',
          url: 'getIssuesForATabUserByStatusAction.action',
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
			str+='<button class="btn btn-success editBtn pull-right btn-sm" attr_value="'+i+'" attr_issueStatus="'+result[i].issueStatus+'">edit</button>';
			str+='<button class="btn btn-success pull-right btn-sm trackingIssueCls" type="button" attr_cadre_reg_issue_id="'+result[i].cadreRegIssueId+'" style="margin-right: 10px;">ISSUE TRACK</button>';
			//str += '</h4>';
			str +='<div class="descriptionCls">';			
				str += '<p class="issueDesc'+i+'">' + result[i].description + '</p>';
				str += '<p class="m_top10">';
				str += '<span class="text-danger"><i>Issue Status :<span class="statusUpdate'+i+'">'
						+ result[i].issueStatus + '</span></i></span>';
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
  
   $(document).on("click","#addNewIssueId",function(){ 
	 $("#issueTypeDivId").show();
   });
  
  $(document).on("click","#submitId",function(){ 
      saveFieldIssue();
  });
  function saveFieldIssue(){
	  var constituencyId =$("#issueConstituencyId").val();  
	  var issueTypeId =$("#issueTypeId option:selected").val();
      var description =$("#descriptionId").val();
      var cadreSurveyUserId= $("#submitId").attr("attr_cadre_survey_user_id");
	  var tabUserInfoId= $("#submitId").attr("attr_tab_user_info_id");
	   if(issueTypeId == 0)
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Select Issue Type</span>");
		   return;
	   }else{
		    $("#submitButId").html("");
	   }
	   if(description.trim() == '' && description.length == 0)
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Enter description</span>");
		   return;
	   }else{
		    $("#submitButId").html("");
	   }
	   var constituency = $("#issueConstituencyId").val();
	   if(constituency == 0)       
	   {
		   $("#submitButId").html("<span style='color: red;font-size:13px;'>Select Constituency</span>");
		   return;
	   }else{
		    $("#submitButId").html("");
	   }
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
  
  $(document).on("click",".updateCls",function(){
	var value = $(this).attr("attr_value");
	var cadreRegIssueId = $(this).attr("attr_cadre_reg_issue_id");
	var description = $(".issueDescEdit"+value).val();
	var  newStatusId = $('#changeIssueStatusId'+value).val();
	
	updateStatusToACadreRegIssue(value,cadreRegIssueId,description,newStatusId);
});
  
  function updateStatusToACadreRegIssue(value,cadreRegIssueId,description,newStatusId){
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
		}else
		{
			$("#updateStatusId"+value).html('');
		}
		if(newStatusId == 0)
		{
			$("#updateStatusId"+value).html("<span style='color: red;font-size:13px;'> Select IssueStatus</span>");
			return;
		}else
		{
			$("#updateStatusId"+value).html('');
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
			}, 2000);
		   }else{
			    $("#updateStatusId").html("<span style='color: red;font-size:18px;'> update Failed.Please try Again..</span>");
		   }
		  var presentTime = moment().format("YYYY-MM-DD hh:mm A");
		  $(".updatedTime"+value).html(presentTime)
		  
	   });
  }
  
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
  
  function getConstituencyByVendor(){
	  clearConstituencies('issueConstituencyId');
	  var fieldVendorId = $("#vendorId option:selected").val();
	  var jsObj = { fieldVendorId : fieldVendorId }
	 
    $.ajax({
          type:'GET',
          url: 'getConstituencyByVendorAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
       for(var i in result){
	      $("#issueConstituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 $("#issueConstituencyId").trigger("chosen:updated");
   });
  }

  
$(document).on("click","#getDetails",function(){
	$("#tabUserDetailsDivId").html("");
	
	var vendorId = $("#vendorId").val();
	var stateId = $("#stateId").val();
	var districtId = $("#districtId").val();
	var constituencyId = $("#constituencyId").val();
	var dates = $(".singleDate").val();
	var dateArr = dates.split("-");
	var fromDate;
	var toDate;
	if(dateArr != null){
		fromDate = dateArr[0];
		toDate = dateArr[1];
	}
	var locationType;
	var locationVal;
	
	if(constituencyId > 0){
		locationType = "constituency";
		locationVal = constituencyId;
	}
	else if(districtId > 0){
		locationType = "district";
		locationVal = districtId;
	}
	else{
		locationType = "state";
		locationVal = stateId;
	}
	if(stateId == 0)
	{
		$("#errorDivId").html("<span style='color: red;font-size:14px;'>Select State</span>");
		return;
	}else{
		$("#errorDivId").html("");
	}
	if(vendorId == 0)
	{
		$("#errorDivId").html("<span style='color: red;font-size:14px;'>Select Vendor</span>");
		return;
	}else{
		$("#errorDivId").html("");
	}
    $("#tabUserDetailsImgId").show();	
	$("#dataCollectorsDiv").show();
	 var jsObj=
     {				
		vendorId : vendorId,
		locationType : locationType,
		locationVal : locationVal,
		fromDate : fromDate,    //"2016-10-01",
		toDate : toDate			//"2016-10-18"
	 }
    $.ajax({
          type:'GET',
          url: 'getTabUsersDetailsByVendorAndLocationAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	  if(result != null){
		  buildTabUserDetails(result);
	  }
	  else{
		$("#tabUserDetailsImgId").hide();
		$("#tabUserDetailsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
	  }
   });
});
	
function buildTabUserDetails(result){
	$("#totalDataCollectorsId").html(result.totalDataCollectors);
	$("#activeDataCollectorsId").html(result.activeUsers);
	$("#passiveDataCollectorsId").html(result.passiveUsers);
	
	if(result.subList != null && result.subList.length > 0){
		var str = '';
		
		str+='<h4 class="panel-title text-muted">Logged In FieldUsers</h4>';
		str+='<table class="table b_1 m_top10" id="detailsTable">';
			str+='<thead class="text-capitalize">';
				str+='<th>User Id</th>';
				str+='<th>user name</th>';
				str+='<th>user contact number</th>';
				str+='<th>today target</th>';
				str+='<th>first record</th>';
				str+='<th>recent record</th>';
				str+='<th>lasthour</th>';
				str+='<th>completed registrations</th>';
				str+='<th>open issues</th>';
				str+='<th>fixed issues</th>';
				str+='<th></th>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result.subList){
				str+='<tr>';
					if(result.subList[i].lastHourCount != null && result.subList[i].lastHourCount > 0)
						str+='<td class="issueCmpltd">'+result.subList[i].userName+'</td>';
					else
						str+='<td class="issuePending">'+result.subList[i].userName+'</td>';
					str+='<td>'+result.subList[i].tabUserName+'</td>';
					str+='<td>'+result.subList[i].mobileNo+'</td>';
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
					str+='<td><button class="btn btn-success text-capitalize manageIssues" attr_cadre_survey_user_id="'+result.subList[i].cadreSurveyUserId+'" attr_tab_user_info_id="'+result.subList[i].tabUserId+'" attr_cadre_survey_userName="'+result.subList[i].userName+'" attr_tab_userName="'+result.subList[i].tabUserName+'" attr_mobileNo="'+result.subList[i].mobileNo+'">manage issues</button></td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		
		$("#tabUserDetailsImgId").hide();
		$("#tabUserDetailsDivId").html(str);
	}
	else{
		$("#tabUserDetailsImgId").hide();
		$("#tabUserDetailsDivId").html('<h4 class="text-danger">NO DATA AVAILABLE...</h4>');
	}
}
function clearErrorFields()
{        
	$("#issueTypeId").val(0).trigger('chosen:updated');
    $("#descriptionId").val('');	
	$("#issueConstituencyId").val(0).trigger('chosen:updated');
	$("#submitButId").html('');
	$("#savingDivIdImg").hide();
}

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
$(document).on("click",".trackingIssueCls",function(){
	var value = $(this).attr("attr_value");
	var cadreRegIssueId = $(this).attr("attr_cadre_reg_issue_id");
	trackingRegIssueByRegIssueId(value,cadreRegIssueId);
});	
$(document).on("click",".cancelCls",function(){
	 $(this).closest("td").find(".trackingIssueCls").show();	
});
