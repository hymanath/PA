function getDataReConsalationOverView(){
	 $("#userWiseTotalViewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var constId = $("#constituencyOverViewId").val();
	  var stateId = $("#stateOverViewId").val();	  
      var districtId = $("#districtOverViewId").val();	  
	  var dateArr = $(".datePicker").val();
	  var dateVal = [];
	  dateVal = dateArr.split("-")
	  var strtDate = dateVal[0];
	  var endDate = dateVal[1];
	  var jsObj = {
		  stateId:stateId,	  
	      constistuencyId : constId,
          fromDate : strtDate,
          toDate : endDate,
		  districtId : districtId
	 }
	 
    $.ajax({
          type:'GET',
          url: 'getDataReConsalationOverViewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null){
	   buildDataReConsalationOverView(result);
		}else {
			$("#userWiseTotalViewId").html('No Data Available');
		}
   });
  }
  function getdataReConsalationTotalOverView(){
	  $("#dataReconsalationOverviewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	  var constId = $("#constituencyOverViewId").val();
	  var stateId = $("#stateOverViewId").val();
	  var districtId = $("#districtOverViewId").val();	
	  var dateArr = $(".datePicker").val();
	  var dateVal = [];
	  dateVal = dateArr.split("-")
	  var strtDate = dateVal[0];
	  var endDate = dateVal[1];
	  var jsObj = { 
		  stateId:stateId,
	      constistuencyId : constId,
          fromDate : strtDate,
          toDate : endDate,
		  districtId : districtId
				  }
	 
    $.ajax({
          type:'GET',
          url: 'getdataReConsalationTotalOverViewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
	   buildDataReConsalationTotalOverView(result);
		 }else {
			  $("#dataReconsalationOverviewId").html('No Data Available');
		 }
   });
  }
  function  buildDataReConsalationTotalOverView(result)
  {
 	 var str ='';  
	str+='<div class="panel panel-default m_top10">';    
		str+='<div class="panel-heading">';
			str+='<h4 class=" headingStyle text-capital"><span style="font-size:14px;"><b>DATA RECONSOLIDATION OVERVIEW</b></h4>';
		str+='</div>';
		str+='<div class="panel-body" style="padding: 25px;">';
			str+='<div class="row">';
				str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
				var totImeiNo = result.totalImeiNo;
				var totRecords = result.totalRecords;
				var totSynRec =result.totalSyn;
				var totPending  = result.totalPending;
				var totTabSyn = result.kafkaSync;
				var totTabPending = result.kafkaPending;
				var actualServerCount =result.actualCount;
				
				if(result != null){
				str+='<div class="col-md-2 col-xs-12 col-sm-6 border_right">';
				str+='<p class="text_bold">TOTAL SMART DEVICES</p>';
				if(totImeiNo != null){
						str+='<p class="text_bold">'+totImeiNo+'</p>';
				}else{
					 str+='<p class="text_bold">'+0+'</p>';
				}
				str+='</div>';
				str+='<div class="col-md-2 col-xs-12 col-sm-6 border_right">';
				str+='<p class="text_bold">TOTAL TAB REGISTRATIONS</p>';
				if(totRecords != null){
					str+='<p class="text_bold">'+totRecords+'</p>';
				}else {
					 str+='<p class="text_bold">'+0+'</p>';
				}
				str+='</div>';
				str+='<div class="col-md-1 col-xs-12 col-sm-6 border_right">';
				str+='<p class="text_bold">TAB SUBMITTED</p>';
				if(totSynRec != null){
					str+='<p class="text_bold">'+totSynRec+'</p>';
				}else {
					str+='<p class="text_bold" text-center>'+0+'</p>';
				}
				str+='</div>';
				}
				str+='<div class="col-md-1 col-xs-12 col-sm-6 border_right">';
				str+='<p class="text_bold">TAB PENDING</p>';
				if(totPending !=null){
					 str+='<p class="text_bold">'+totPending+'</p>';
				 }else{
					 str+='<p class="text_bold">'+0+'</p>';
				 }
				str+='</div>';
				
				str+='<div class="col-md-2 col-xs-12 col-sm-6 border_right " >';
				str+='<p class="text_bold">KAFKA SYNCED RECORDS</p>';
				if(totTabSyn !=null){
					 str+='<p class="text_bold">'+totTabSyn+'</p>';
				 }else{
					 str+='<p class="text_bold">'+0+'</p>';
				 }
				str+='</div>';
				str+='<div class="col-md-2 col-xs-12 col-sm-6 border_right " >';
				str+='<p class="text_bold">KAFKA PENDING RECORDS</p>';
				 if(totTabPending !=null){
					 str+='<p class="text_bold">'+totTabPending+'</p>';
				 }else{
					 str+='<p class="text_bold">'+0+'</p>';
				 }
				str+='</div>';
				
				str+='<div class="col-md-2 col-xs-12 col-sm-6 ">';
				str+='<p class="text_bold">ACTUAL SERVER REGISTRATIONS</p>';
				if(actualServerCount != null){
					str+='<p class="text_bold">'+actualServerCount+'</p>';
				}else {
					 str+='<p class="text_bold">'+0+'</p>';
				}
				str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#dataReconsalationOverviewId").html(str);
	      }
 
$(document).on("click","#submitId",function(){
	 $("#errorDivId").html(' ');
	 
	  var stateId = $("#stateOverViewId option:selected").val();
	  var districtId = $("#districtOverViewId").val();
	  var constId = $("#constituencyOverViewId").val();
	  
	  if(stateId == 0){
		  $("#errorDivId").html('Please Select State');
		  return ;
	  }
	   $("#errorDivId").html(' ');
	   
	  /* if(districtId == 0){
		  $("#errorDivId").html('Please Select District');
		  return ;
	  }*/
	   $("#errorDivId").html(' ');  
	getdataReConsalationTotalOverView();
	getDataReConsalationOverView();
	getLocationWiseSmartDevicesCount();
});
 function buildDataReConsalationOverView(result)
 {                                                        
	 var str = '';
	 str+='<div class="panel panel-default">';  
		str+='<div class="panel-heading">';
			str+='<h4 class=" headingStyle text-capital"><span style="font-size:14px;"><b>USER WISE TOTAL REGISTRATIONS & SYNC PENDING DETAILS</b></h4>';
		str+='</div>';
		str+='<div class="panel-body">';
			str+='<div class="table-responsive">';
				str+='<table class="table table-condensed" id="userWiseTotalRegstSyncId">';
				str+='<thead>';
				str+='<tr>'; 
				str+='<th>USER NAME</th>'; 
				//str+='<th>Device Number</th>';
				str+='<th>IMEI NUMBER</th>';
				str+='<th>TAB USER ID</th>'; 
				str+='<th>USER SYNC TYPE</th>';
				str+='<th>TOTAL TAB REGISTRATIONS</th>'; 
				str+='<th>TAB SUBMITTED</th>'; 
				str+='<th>TAB PENDING</th>';
				str+='<th>TAB KAFKA COMPLETED</th>'; 
				str+='<th>TAB KAFKA PENDING</th>'; 
				str+='<th>ACTUAL SERVER REGISTRATIONS</th>';
				str+='<th>UPDATED TIME</th>';
				str+='<th></th>';
				str+='</tr>';
				str+='</thead>'; 
				str+='<tbody>';
           for(var i in result )
		   {			   
				str+='<tr>'; 
				if(result[i].userName != null ){
				str+='<td>'+result[i].userName+'</td>';
				}else{
					 str+='<td>-</td>';
				}
				if(result[i].imeiNo != null ){
				str+='<td>'+result[i].imeiNo+'</td>';
				}else{
					str+='<td>-</td>';
				}
                if(result[i].name != null){				
				str+='<td>'+result[i].name+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(result[i].userSyncType != null){
				str+='<td>'+result[i].userSyncType+'</td>';
				}else{
					str+='<td>-</td>';
				}
				if(result[i].totalRecords !=null){
				str+='<td>'+result[i].totalRecords+'</td>';
				}else{
					str+='<td>0</td>';
				}
				if(result[i].sync  != null){
                str+='<td>'+result[i].sync+'</td>'; 
				}else{
					str+='<td>0</td>';
				}
				if(result[i].pending){
                str+='<td>'+result[i].pending+'</td>'; 
				}else{
					str+='<td>0</td>';
				}
				
                if(result[i].kafkaSync != null){				
                str+='<td>'+result[i].kafkaSync+'</td>';
				}else{
					str+='<td>0</td>';
				}
				if(result[i].kafkaPending != null){
				str+='<td>'+result[i].kafkaPending+'</td>'; 
				}else{
					str+='<td>0</td>';
				}
              if(result[i].actualCount != null){				
				str+='<td>'+result[i].actualCount+'</td>';
              }else{
	            str+='<td>0</td>';
                 }
			 if(result[i].maxRecordTime !=null){
				str+='<td>'+result[i].maxRecordTime.substring(0,19)+'</td>';
				 }else{
					 str+='<td>-</td>';
				 }				
				str+='<td><button attr_cdr_srv_usr_id="'+result[i].cadreSurveyUserId+'" attr_userName="'+result[i].userName+'" class="btn btn-sm btn-success openPopUpModel tabUserDtlsCls">VIEW DAY WISE</button></td>';
				str+='</tr>';	
		   }				
				str+='</tbody>';
			str+='</table>';
			str+='</div>';
		str+='</div>';
    str+='</div>';
	$("#userWiseTotalViewId").html(str);
	//$("#userWiseTotalRegstSyncId").dataTable();
	$("#userWiseTotalRegstSyncId").dataTable({
			"aaSorting": [[ 0, "asc" ]],
	         "iDisplayLength": 50,
	         "aLengthMenu": [[ 50, 100, -1], [ 50, 100, "All"]]
	});
	$("#userWiseTotalRegstSyncId").removeClass("dataTable");
 }
 
 $(document).on('change','#stateOverViewId',function(){
    $("#errorDivId").html(' ');
	 $("#stateDivIdImg").show();
	    $('#districtOverViewId').html(' ');
		$('#districtOverViewId').append('<option value="0">All</option>');
	    $('#constituencyOverViewId').html(' ');
	    $('#constituencyOverViewId').append('<option value="0">All</option>');
	var jsObj = {
			stateId : $("#stateOverViewId option:selected").val()
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsWiseStateAction.action',
		dataType :'json',
		data : {task:JSON.stringify(jsObj)}
			
	}).done(function(result){
	    $("#stateDivIdImg").hide();
		if(result !=null && result.length>0){
			for(var i in result){
				$('#districtOverViewId').append('<option value="'+result[i].id+'">'+result[i].name.toUpperCase()+'</option>');
			}
		}
		
	});
});
 
 $(document).on('change','#districtOverViewId',function(){
  $("#errorDivId").html(' ');
	 $("#districtDivIdImg").show();
	var districtId = $("#districtOverViewId").val();
	$('#constituencyOverViewId').html(' ');
	 $('#constituencyOverViewId').append('<option value="0">All</option>');
	 var jsObj = {
			districtId : districtId
			
	}
	$.ajax({
		type : 'GET',
		url : 'getConstituenciesListForDistrictAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
			
	}).done(function(result){
	 $("#districtDivIdImg").hide();
		//$('#constituencyOverViewId').html(' ');
		//$('#constituencyOverViewId').append('<option value="0">Please Select Constituency </option>');
		if(result !=null && result.length>0){
			for(var i in result){
				$('#constituencyOverViewId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
		
	});
});

$(document).on("click",".tabUserDtlsCls",function(){
	  var cdrSurveyUserId = $(this).attr("attr_cdr_srv_usr_id");
	  var cdrSurveyUserName =$(this).attr("attr_userName");
	  var constId = $("#constituencyOverViewId").val();
	  $("#userHeadinId").html("<b>UserName:"+cdrSurveyUserName+" - Day Wise Registration Details</b>")
	  var dateArr = $(".datePicker").val();
	  var dateVal = [];
	  dateVal = dateArr.split("-")
	  var strtDate = dateVal[0];
	  var endDate = dateVal[1];
	  getCadreSurveyUserWiseRegistrations(cdrSurveyUserId,constId,strtDate,endDate);
	  $("#myModal").modal("show");
	 
 });


function getCadreSurveyUserWiseRegistrations(cdrSurveyUserId,constId,strtDate,endDate){
	$("#tabUserWiseRegistionDetilsId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	var jsObj = {
			cadreSurveyUserId : cdrSurveyUserId,
			constituencyId : constId,
			strtDate : strtDate,
			endDate : endDate
	}
	$.ajax({
		type : "GET",
		url : "getCadreSurveyUserWiseRegistrationsAction.action",
		dataType : "json",
		data : {task:JSON.stringify(jsObj)}
			
	}).done(function(result){
		if(result != null){
			buildPopUpModelDetails(result);
		}else {
			$("#tabUserWiseRegistionDetilsId").html('No Data Available');	
		}
	});
}

function buildPopUpModelDetails(result){
	var str = '';
	str+='<div class="table-responsive">';
	str+='<table class="table table-condensed " id="tabUserDetailsId">';
	str+='<thead>';
	    str+='<tr>';
		str+='<th>DATE</th>';
		str +='<th>IMAGE PATH</th>';
		str+='<th>NAME</th>';
		str+='<th>MOBILE NO</th>';
		str+='<th>FIRST RECORD RECEIVED</th>'; 
		str+='<th>LAST  RECORD RECEIVED</th>'; 
		str+='<th>TOTAL TAB REGISTRATIONS</th>'; 
		str+='<th>TAB SUBMITTED</th>';
		str+='<th>TAB PENDING</th>';
		str+='<th>TAB KAFKA COMPLETED</th>';
		str+='<th>TAB KAFKA PENDING</th>';
		str+='<th>ACTUAL SERVER REGISTRATIONS</th>';
		//str+='<th>TOTAL AMOUNT</th>';
      str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	  for(var i in result){
		str+='<tr>';
		var totRecrds = result[i].totalRecords;
		var totalSync = result[i].sync;
		var totPending = result[i].pending;
		var  kafkaSync =result[i].kafkaSync;
		var kafkaPending = result[i].kafkaPending;
		var actualCount = result[i].actualCount;
		//var totAmount = result[i].totalAmount;
		str +='<td class ="text-center">'+result[i].surveyDate+'</td>';
		str+='<td><img src="http://www.mytdp.com/CR/tab_user_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
		str +='<td class ="text-center">'+result[i].name+'</td>';
		str +='<td class ="text-center">'+result[i].mobileNo+'</td>';
		str +='<td class ="text-center">'+result[i].firstRecord+'</td>';
		str +='<td>'+result[i].lastRecord+'</td>';
		if(totRecrds != null){
			str +='<td class ="text-center">'+totRecrds+'</td>';
		}else {
			str +='<td class ="text-center">'+0+'</td>';
		}
		if(totalSync !=null){
			str +='<td class="textAlign">'+totalSync+'</td>';
		}else {
			str +='<td class="textAlign">'+0+'</td>';
		}
		if(totPending != null){
			str +='<td >'+totPending+'</td>';
		}else {
			str +='<td >'+0+'</td>';
		}
		if(kafkaSync != null){
			  str +='<td >'+kafkaSync+'</td>';
		 }else {
			  str +='<td >'+0+'</td>';
		 }
        if(kafkaPending != null)	{
		   str +='<td >'+kafkaPending+'</td>';
	    }else {
		   str +='<td >'+0+'</td>';
	    }
	  if(actualCount != null){
			str +='<td >'+actualCount+'</td>';
		}else {
			str +='<td >'+0+'</td>';
		}
	   /* if(totAmount != null){
		 str +='<td >'+totAmount+'</td>';
	  }else {
		 str +='<td >'+0+'</td>';
		} */
		str +='</tr>';
	}
	str +='</tbody>';
	str +='</table>';
	str +='</div>';
	$("#tabUserWiseRegistionDetilsId").html(str);	
	//$("#tabUserDetailsId").dataTable();	
	$("#tabUserDetailsId").dataTable({
			"aaSorting": [[ 0, "asc" ]],
	         "iDisplayLength": 50,
	         "aLengthMenu": [[ 50, 100, -1], [ 50, 100, "All"]]
	});
	$("#tabUserDetailsId").removeClass("dataTable");
	var str1 = '';
	str1+='<table class="table table-condensed " id="tabUserExportExcelId">';
	str1+='<thead>';
	    str1+='<tr>';
		str1+='<th>DATE</th>';
		str1+='<th>NAME</th>';
		str1+='<th>MOBILE NO</th>';
		str1+='<th>FIRST RECORD RECEIVED</th>'; 
		str1+='<th>LAST RECORD RECEIVED</th>'; 
		str1+='<th>TOTAL TAB REGISTRATIONS</th>'; 
		str1+='<th>TAB SUBMITTED</th>';
		str1+='<th>TAB PENDING</th>';
		str1+='<th>TAB KAFKA COMPLETED</th>';
		str1+='<th>TAB KAFKA PENDING</th>';
		str1+='<th>ACTUAL SERVER REGISTRATIONS</th>';
		//str1+='<th>TOTAL AMOUNT</th>';
      str1+='</tr>';
	str1+='</thead>';
	str1+='<tbody>';
	  for(var i in result){
		str+='<tr>';
		var totRecrds = result[i].totalRecords;
		var totalSync = result[i].sync;
		var totPending = result[i].pending;
		var  kafkaSync = result[i].kafkaSync;
		var kafkaPending = result[i].kafkaPending;
		var actualCount = result[i].actualCount;
		var totAmount = result[i].totalAmount;
		str1 +='<td class ="text-center">'+result[i].surveyDate+'</td>';
		str1 +='<td class ="text-center">'+result[i].name+'</td>';
		str1 +='<td class ="text-center">'+result[i].mobileNo+'</td>';
		str1 +='<td class ="text-center">'+result[i].firstRecord+'</td>';
		str1 +='<td>'+result[i].lastRecord+'</td>';
		if(totRecrds != null){
			str1 +='<td class ="text-center">'+totRecrds+'</td>';
		}else {
			str1 +='<td class ="text-center">'+0+'</td>';
		}
		if(totalSync !=null){
			str1 +='<td class="textAlign">'+totalSync+'</td>';
		}else {
			str1 +='<td class="textAlign">'+0+'</td>';
		}
		if(totPending != null){
			str1 +='<td >'+totPending+'</td>';
		}else {
			str1 +='<td >'+0+'</td>';
		}
		if(kafkaSync != null){
			  str1 +='<td >'+kafkaSync+'</td>';
		}else {
			  str1 +='<td >'+0+'</td>';
		}
        if(kafkaPending != null)	{
		   str1 +='<td >'+kafkaPending+'</td>';
	    }else {
		   str1 +='<td >'+0+'</td>';
	    }
	   if(actualCount != null){
			str1 +='<td >'+actualCount+'</td>';
		}else {
			str1 +='<td >'+0+'</td>';
		}
	   /* if(totAmount != null){
		 str1 +='<td >'+totAmount+'</td>';
	  }else {
		 str +='<td >'+0+'</td>';
		} */
		str1 +='</tr>';
	}
	str +='</tbody>';
	str +='</table>';
	$("#tabUserWiseExportExcelDivId").html(str1);	
}

function setDefaultImage(img){
    img.onerror = "";
    img.src = "images/cadre_images/human.jpg";
    return true;
  }
  
  function getLocationWiseSmartDevicesCount(){
  $("#locWiseSmartDivCount").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
	 
	  var constId = $("#constituencyOverViewId").val();
	  var districtId = $("#districtOverViewId").val();
	  var stateId = $("#stateOverViewId").val();
	  var dateArr = $(".datePicker").val();
	  var dateVal = [];
	  dateVal = dateArr.split("-")
	  var strtDate = dateVal[0];
	  var endDate = dateVal[1];
	  var jsObj = {
		  stateId : stateId,
		  districtId : districtId,
	      constituencyId : constId,
          strtDate : strtDate,
          endDate : endDate
	 }
	 
    $.ajax({
          type:'GET',
          url: 'getLocationWiseSmartDevicesCountAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null){
			buildLocationWiseSmartDevicesCount(result,districtId);
		}else {
			$("#locWiseSmartDivCount").html('No Data Available');
		}
   });
  }
  function buildLocationWiseSmartDevicesCount(result,districtId){
  $("#locWiseSmartDivCount").show();
  var str = "";
  str+='<div class="panel panel-default">';
  str+='<div class="panel-heading" id="locationId" style="cursor: pointer;">';
  str+='<h4 class=" headingStyle text-capital"><span style="font-size:14px;"><b>LOCATION WISE SMART DEVICES</b><i class="glyphicon glyphicon-chevron-down pull-right"></i></h4>';
	str+='</div>';
	str+='<div class="panel-body collapse" id="locationBodyId">';
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered">';
	str+='<thead>';
	if(districtId > 0){
		str+='<th>CONSTITUENCY NAME</th>';
	}else{
		str+='<th>DISTRICT NAME</th>';
	}
	str+='<th>SMART DEVICES COUNT</th>';
	str+='<th>TOTAL TAB REGISTRATIONS</th>';
	str+='<th>TAB SUBMITTED</th>';
	str+='<th>TAB PENDING</th>';
	str+='<th>TAB KAFKA COMPLETED</th>';
	str+='<th>TAB KAFKA PENDING</th>';
	str+='<th>ACTUAL SERVER REGISTRATIONS</th>';
	str+='</thead>';
	//str+='<tbody>';
	for(var i in result){
	str+='<tbody>';
		str+='<td>'+result[i].name+'</td>';
		str+='<td class ="text-center">'+result[i].totalAmount+'</td>';
		str+='<td class ="text-center">'+result[i].totalRecords+'</td>';
		str+='<td class ="text-center">'+result[i].totalSyn+'</td>';
		str+='<td class ="text-center">'+result[i].totalPending+'</td>';
		str+='<td class ="text-center">'+result[i].kafkaSync+'</td>';
		str+='<td class ="text-center">'+result[i].kafkaPending+'</td>';
		str+='<td class ="text-center">'+result[i].actualCount+'</td>';
		str+='</tbody>';
	}
	//str+='</tbody>';
	str+='</table>';
	str+='</div>';
	str+='</div>';
	str+='</div>';
	$("#locWiseSmartDivCount").html(str);
}

$( document ).ready(function() {
  $("#stateOverViewId").val(0); 
});
$(document).on("click","#locationId",function(){
$('#locationBodyId').collapse('toggle');
$(this).find(".glyphicon").toggleClass("glyphicon-chevron-down").toggleClass("glyphicon-chevron-up")
});