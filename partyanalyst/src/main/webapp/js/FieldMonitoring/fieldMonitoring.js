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
			$.ajax({
				type : 'GET',
				url : 'getVendorsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
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
			
			$.ajax({
				type : 'GET',
				url : 'getVendorDistrictsAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
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
			
			$.ajax({
				type : 'GET',
				url : 'getVendorConstituenciesAction.action',
				dataType : 'json',
				data : {task:JSON.stringify(jsObj)}  
			}).done(function(result){
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
	
$(document).on("click",".manageIssues",function(){ 
    $("#issueTypeDivId").hide();
    var cadreSurveyUserId = $(this).attr("attr_cadre_survey_user_id");
    var tabUserInfoId = $(this).attr("attr_tab_user_info_id");
    $("#submitId").attr("attr_cadre_survey_user_id",cadreSurveyUserId);
    $("#submitId").attr("attr_tab_user_info_id",tabUserInfoId);
	
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
 
   $(document).on("click","#addNewIssueId",function(){ 
	 $("#issueTypeDivId").show();
	 clearErrorFields();
   });
  
  $(document).on("click","#submitId",function(){ 
      saveFieldIssue();
  });
  function saveFieldIssue(){
	  $("#savingDivIdImg").show();
	  var constituencyId =$("#constituencyId").val();  
	  var issueTypeId =$("#issueTypeId option:selected").val();
      var description =$("#descriptionId").val();
      var cadreSurveyUserId= $("#submitId").attr("attr_cadre_survey_user_id");
	  var tabUserInfoId= $("#submitId").attr("attr_tab_user_info_id");
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
		    $("#submitButId").html("<span style='color: green;font-size:12px;'> Saved Successfully...</span>");
			setTimeout(function(){
				$("#issueTypeDivId").hide();
			}, 2000);
	   }else{
		   $("#submitButId").html("<span style='color: red;font-size:12px;'>Saved Failed.Please try Again.</span>");
		   setTimeout(function(){
				$("#issueTypeDivId").hide();
			}, 2000);
	   }
   });
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
	$("#dataCollectorsDiv").show();
	$("#tabUserDetailsImgId").show();
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
					if(result.subList[i].openIssues != null)
						str+='<td>'+result.subList[i].openIssues+'</td>';
					else
						str+='<td> - </td>';
					if(result.subList[i].fixedIssues != null)
						str+='<td>'+result.subList[i].fixedIssues+'</td>';
					else
						str+='<td> - </td>';
					str+='<td><button class="btn btn-success text-capitalize manageIssues" attr_cadre_survey_user_id="'+result.subList[i].cadreSurveyUserId+'" attr_tab_user_info_id="'+result.subList[i].tabUserId+'" >manage issues</button></td>';
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
		
		$("#tabUserDetailsImgId").hide();
		$("#tabUserDetailsDivId").html(str);
		//$("#detailsTable").datatable();
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
