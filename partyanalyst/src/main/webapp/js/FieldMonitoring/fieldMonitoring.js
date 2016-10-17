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
                     $('#'+selectBoxId).append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
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
	 getVendorConstituencies('issueConstituencyId');
    $("#issuesModal").modal('show');
  });
 
   $(document).on("click","#addNewIssueId",function(){ 
	 $("#issueTypeDivId").show();
   });
  
  $(document).on("click","#submitId",function(){ 
      saveFieldIssue();
  });
  function saveFieldIssue(){
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
	  
   });
  }
  
	