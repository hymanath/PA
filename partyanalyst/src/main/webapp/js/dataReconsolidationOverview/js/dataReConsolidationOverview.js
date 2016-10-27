function getDataReConsalationOverView(){
	
	  var constId = $("#constituencyOverViewId").val();
	  var dateArr = $(".datePicker").val();
	  var dateVal = [];
	  dateVal = dateArr.split("-")
	  var strtDate = dateVal[0];
	  var endDate = dateVal[1];
	  var jsObj = { 
	      constistuencyId : constId,
          fromDate : strtDate,
          toDate : endDate
	 }
	 
    $.ajax({
          type:'GET',
          url: 'getDataReConsalationOverViewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	    if(result != null){
	   buildDataReConsalationOverView(result);
		} 
   });
  }
  function getdataReConsalationTotalOverView(){
	  var constId = $("#constituencyOverViewId").val();
	  var dateArr = $(".datePicker").val();
	  var dateVal = [];
	  dateVal = dateArr.split("-")
	  var strtDate = dateVal[0];
	  var endDate = dateVal[1];
	  var jsObj = { 
	      constistuencyId : constId,
          fromDate : strtDate,
          toDate : endDate
				  }
	 
    $.ajax({
          type:'GET',
          url: 'getdataReConsalationTotalOverViewAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
	   buildDataReConsalationTotalOverView(result);
		 }   
   });
  }
 function  buildDataReConsalationTotalOverView(result)
 {
	 var str ='';
	    str+='<h4 class=" headingStyle text-capital"><b>DATA RECONSALATION OVERVIEW</b></h4>';
				str+='<div class="panel-body" style="padding: 25px;">';
					str+='<div class="row">';
						str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
						for(var i in result){
						str+='<div class="col-md-2 col-xs-12 col-sm-6 border_right">';
						str+='<p class="text_bold">Total Smart Devices</p>';
						str+='<p class="text_bold">'+result[i].totalImeiNo+'</p>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-6 border_right">';
						str+='<p class="text_bold">Total Registration</p>';
						str+='<p class="text_bold">'+result[i].totalRecords+'</p>';
						str+='</div>';
						str+='<div class="col-md-2 col-xs-12 col-sm-6 border_right">';
						str+='<p class="text_bold">Data Synced Records</p>';
						str+='<p class="text_bold">'+result[i].totalSyn+'</p>';
						str+='</div>';
						str+='<div class="col-md-3 col-xs-12 col-sm-6 border_right">';
						str+='<p class="text_bold">Data Synced Pending Records</p>';
						str+='<p class="text_bold">'+result[i].totalPending+'</p>';
						str+='</div>';
						}
						str+='</div>';
					    str+='</div>';
				       str+='</div>';
					   $("#dataReconsalationOverviewId").html(str);
 }
 
$(document).on("click","#submitId",function(){
	
	  var stateId = $("#stateOverViewId option:selected").val();
	  var districtId = $("#districtOverViewId").val();
	  var constId = $("#constituencyOverViewId").val();
	  
	  if(stateId == 0){
		  $("#errorDivId").html('Please Select State');
		  return ;
	  }
	   $("#errorDivId").html(' ');
	   
	   if(districtId == 0){
		  $("#errorDivId").html('Please Select District');
		  return ;
	  }
	   $("#errorDivId").html(' ');
	   
	   if(constId==0){
		  $("#errorDivId").html('Please Select Constituency');
		  return ;
	  }
	   $("#errorDivId").html(' ');
	     
	getdataReConsalationTotalOverView();
	getDataReConsalationOverView();
});
 function buildDataReConsalationOverView(result)
 {
	 var str = '';
	 str+='<h4 class=" headingStyle text-capital"><b>user wise total registrations & sync pending details</b></h4>';
				str+='<div class="panel-body" style="padding: 25px;">';
				str+='<table class="table table-condensed">';
				str+='<thead>';
				str+='<tr>'; 
				str+='<th>User Id</th>'; 
				//str+='<th>Device Number</th>';
				str+='<th>IMEI Number</th>';
				str+='<th>Vendor Name</th>'; 
				str+='<th>Total Registration</th>'; 
				str+='<th>KafkaPending</th>'; 
				str+='<th>ServerPending</th>'; 
				str+='<th>Sync Pending</th>';
				str+='<th>ActualServer Status</th>';
				str+='</tr>';
				str+='</thead>'; 
				str+='<tbody>';
           for(var i in result )
		   {			   
				str+='<tr>'; 
				//str+='<td ></td>';
				str+='<td>'+result[i].cadreSurveyUserId+'</td>';
				str+='<td>'+result[i].imeiNo+'</td>';      
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].totalRecords+'</td>';  
                str+='<td>'+result[i].pending+'</td>'; 
				str+='<td>'+result[i].kafkaPending+'</td>'; 
                str+='<td>'+result[i].serverPending+'</td>';				
				str+='<td>'+result[i].actualCount+'<button attr_cdr_srv_usr_id="'+result[i].cadreSurveyUserId+'" class="btn btn-sm btn-success openPopUpModel tabUserDtlsCls" style="margin-right: 0px; margin-left: 100px;">VIEW DAY WISE</button></td>';
				str+='</tr>';	
		   }				
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
			    str+='</div>';
				$("#userWiseTotalViewId").html(str);
	 
 }
 
 $(document).on('change','#stateOverViewId',function(){
	var jsObj = {
			stateId : $("#stateOverViewId option:selected").val()
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsWiseStateAction.action',
		dataType :'json',
		data : {task:JSON.stringify(jsObj)}
			
	}).done(function(result){
		$('#districtOverViewId').html(' ');
		$('#districtOverViewId').append('<option value="0">Please Select District</option>');
		if(result !=null && result.length>0){
			for(var i in result){
				$('#districtOverViewId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
		
	});
});
 
 $(document).on('change','#districtOverViewId',function(){
	var districtId = $("#districtOverViewId").val();
	 var jsObj = {
			districtId : districtId
			
	}
	$.ajax({
		type : 'GET',
		url : 'getConstituenciesListForDistrictAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)}
			
	}).done(function(result){
		$('#constituencyOverViewId').html(' ');
		$('#constituencyOverViewId').append('<option value="0">Please Select Constituency </option>');
		if(result !=null && result.length>0){
			for(var i in result){
				$('#constituencyOverViewId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
		
	});
});
/* 
$(document).on('click','#viewDayWiseId',function(){
	$("#myModal").modal("show");
	getCadreSurveyUserWiseRegistrations();
}) */

$(document).on("click",".tabUserDtlsCls",function(){
	  var cdrSurveyUserId = $(this).attr("attr_cdr_srv_usr_id");
	  var constId = $("#constituencyOverViewId").val();
	  var dateArr = $(".datePicker").val();
	  var dateVal = [];
	  dateVal = dateArr.split("-")
	  var strtDate = dateVal[0];
	  var endDate = dateVal[1];
	  getCadreSurveyUserWiseRegistrations(cdrSurveyUserId,constId,strtDate,endDate);
	  $("#myModal").modal("show");
	 
 });


function getCadreSurveyUserWiseRegistrations(cdrSurveyUserId,constId,strtDate,endDate){
	
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
		buildPopUpModeldetails(result);
		
	});
}

function buildPopUpModeldetails(result){
	var str ='';
	str +='<table>';
	str +='<thead>';
	str +='<tr>';
	str +='<th>Date</th>';
	str +='<th>Name</th>';
	str +='<th>Mobile No</th>';
	str +='<th>First Record Received</th>'; 
	str +='<th>Last Record Received</th>'; 
    str +='<th>Registrations</th>'; 
	str +='<th>Synced</th>';
	str +='<th>Sync Pending</th>';
	str +='<th>Total Amount</th>';
    str +='</tr>';
	str +='</thead>';
	str +='<tbody>';
	str +='<tr>';
	for(var i in result){
		str +='<td>'+result[i].surveyDate+'</td>';
		str +='<td>'+result[i].name+'</td>';
		str +='<td>'+result[i].mobileNo+'</td>';
		str +='<td>'+result[i].firstRecord+'</td>';
		str +='<td>'+result[i].lastRecord+'</td>';
		str +='<td>'+result[i].totalRecords+'</td>';
		str +='<td>'+result[i].sync+'</td>';
		str +='<td>'+result[i].pending+'</td>';
		str +='<td>'+result[i].totalAmount+'</td>';
	}
	str +='</tr>';
	str +='</tbody>';
	str +='</table>';
	
}
