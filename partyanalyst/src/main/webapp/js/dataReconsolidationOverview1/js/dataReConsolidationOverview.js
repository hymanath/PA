function getDataReConsalationOverView(){
	 $("#userWiseTotalViewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
		}else {
			$("#userWiseTotalViewId").html('No Data Available');
		}
   });
  }
  function getdataReConsalationTotalOverView(){
	  $("#dataReconsalationOverviewId").html('<div class="spinner"><div class="dot1"></div><div class="dot2"></div></div>');
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
		 }else {
			  $("#dataReconsalationOverviewId").html('No Data Available');
		 }
   });
  }
  function  buildDataReConsalationTotalOverView(result)
  {
 	 var str ='';  
	    str+='<div class="panel panel-default m_top10">';    
 	    str+='<h4 class=" headingStyle text-capital"><span style="font-size:15px;"><b>DATA RECONSALIDATION OVERVIEW</b></h4>';
 				str+='<div class="panel-body" style="padding: 25px;">';
 					str+='<div class="row">';
 						str+='<div class="col-md-12 col-xs-12 col-sm-12 ">';
 						var totImeiNo = result.totalImeiNo;
 						var totRecords = result.totalRecords;
 						var totSynRec =result.totalSyn;
 						var totPending  = result.totalPending;
 						if(result != null){
 						str+='<div class="col-md-3 col-xs-12 col-sm-6 border_right">';
 						str+='<p class="text_bold">Total Smart Devices</p>';
 						if(totImeiNo != null){
 								str+='<p class="text_bold">'+totImeiNo+'</p>';
 						}else{
 							 str+='<p class="text_bold">'+0+'</p>';
 						}
 						str+='</div>';
 						str+='<div class="col-md-3 col-xs-12 col-sm-6 border_right">';
 						str+='<p class="text_bold">Total Registration</p>';
 						if(totRecords != null){
 							str+='<p class="text_bold">'+result.totalRecords+'</p>';
 						}else {
 							 str+='<p class="text_bold">'+0+'</p>';
 						}
 						str+='</div>';
 						str+='<div class="col-md-3 col-xs-12 col-sm-6 border_right">';
 						str+='<p class="text_bold">Data Synced Records</p>';
 						if(totSynRec != null){
 							str+='<p class="text_bold">'+totSynRec+'</p>';
 						}else {
 							str+='<p class="text_bold" text-center>'+0+'</p>';
 						}
 						str+='</div>';
 						str+='<div class="col-md-3 col-xs-12 col-sm-6 border_right">';
 						str+='<p class="text_bold">Data Synced Pending Records</p>';
 						 if(totPending !=null){
 							 str+='<p class="text_bold">'+totPending+'</p>';
 						 }else{
 							 str+='<p class="text_bold">'+0+'</p>';
 						 }
 						str+='</div>';
 						}
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
	 str+='<div class="panel panel-default">';       
	 str+='<h4 class=" headingStyle text-capital"><span style="font-size:15px;"><b>USER WISE TOTAL REGISTRATIONS & SYNC PENDING DETAILS</b></h4>';
				str+='<div class="panel-body" style="padding: 25px;">';
				str+='<table class="table table-condensed" id="userWiseTotalRegstSyncId">';
				str+='<thead>';
				str+='<tr>'; 
				str+='<th>USER NAME</th>'; 
				//str+='<th>Device Number</th>';
				str+='<th>IMEI NUMBER</th>';
				str+='<th>VENDOR NAME</th>'; 
				str+='<th>TOTAL REGISTRATION</th>'; 
				str+='<th>SYNC PENDING</th>';
				str+='<th>KAFKA PENDING</th>'; 
				str+='<th>KAFKA SYNC</th>'; 
				str+='<th>ACTUAL SERVER SYNC</th>';
				str+='<th></th>';
				str+='</tr>';
				str+='</thead>'; 
				str+='<tbody>';
           for(var i in result )
		   {			   
				str+='<tr>'; 
				str+='<td>'+result[i].userName+'</td>';
				str+='<td>'+result[i].imeiNo+'</td>';      
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].totalRecords+'</td>';  
                str+='<td>'+result[i].pending+'</td>'; 
				str+='<td>'+result[i].kafkaPending+'</td>'; 
                str+='<td>'+result[i].kafkaSync+'</td>';
             if(result[i].actualCount != null){				
				str+='<td>'+result[i].actualCount+'</td>';
              }else{
	            str+='<td>0</td>';
                 }
				str+='<td><button attr_cdr_srv_usr_id="'+result[i].cadreSurveyUserId+'" attr_userName="'+result[i].userName+'" class="btn btn-sm btn-success openPopUpModel tabUserDtlsCls" style="margin-right: 0px; margin-left: 100px;">VIEW DAY WISE</button></td>';
				str+='</tr>';	
		   }				
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
			    str+='</div>';
			
				$("#userWiseTotalViewId").html(str);
				$("#userWiseTotalRegstSyncId").dataTable();
 }
 
 $(document).on('change','#stateOverViewId',function(){
    $("#errorDivId").html(' ');
	 $("#stateDivIdImg").show();
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
		$('#districtOverViewId').html(' ');
		$('#districtOverViewId').append('<option value="0">Please Select District</option>');
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
		$('#constituencyOverViewId').html(' ');
		$('#constituencyOverViewId').append('<option value="0">Please Select Constituency </option>');
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
	str+='<table class="table table-condensed " id="tabUserDetailsId">';
	str+='<thead>';
	    str+='<tr>';
		str+='<th>Date</th>';
		str +='<th>ImagePath</th>';
		str+='<th>Name</th>';
		str+='<th>Mobile No</th>';
		str+='<th>First Record Received</th>'; 
		str+='<th>Last Record Received</th>'; 
		str+='<th>Registrations</th>'; 
		str+='<th>Synced</th>';
		str+='<th>Sync Pending</th>';
		str+='<th>kafka Pending</th>';
		str+='<th>kafka Sync</th>';
		str+='<th>Total Amount</th>';
      str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	  for(var i in result){
		str+='<tr>';
		var totRecrds = result[i].totalRecords;
		var totalSync = result[i].sync;
		var totPending = result[i].pending;
		var kafkaPending = result[i].kafkaPending;
		var  kafkaSync =result[i].kafkaSync;
		var totAmount = result[i].totalAmount;
		str +='<td class ="text-center">'+result[i].surveyDate+'</td>';
		str+='<td><img src="https://www.mytdp.com/tab_user_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
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
       if(kafkaPending != null)	{
		   str +='<td >'+kafkaPending+'</td>';
	   }else {
		   str +='<td >'+0+'</td>';
	   }
	   if(kafkaSync != null){
		  str +='<td >'+kafkaSync+'</td>';
	   }else {
		    str +='<td >'+0+'</td>';
	   }
	   if(totAmount != null){
		 str +='<td >'+totAmount+'</td>';
	  }else {
		 str +='<td >'+0+'</td>';
		}
		str +='</tr>';
	}
	str +='</tbody>';
	str +='</table>';
	$("#tabUserWiseRegistionDetilsId").html(str);	
	$("#tabUserDetailsId").dataTable();	
	
	var str1 = '';
	str1+='<table class="table table-condensed " id="tabUserExportExcelId">';
	str1+='<thead>';
	    str1+='<tr>';
		str1+='<th>Date</th>';
		str1+='<th>Name</th>';
		str1+='<th>Mobile No</th>';
		str1+='<th>First Record Received</th>'; 
		str1+='<th>Last Record Received</th>'; 
		str1+='<th>Registrations</th>'; 
		str1+='<th>Synced</th>';
		str1+='<th>Sync Pending</th>';
		str1+='<th>kafka Pending</th>';
		str1+='<th>kafka Sync</th>';
		str1+='<th>Total Amount</th>';
      str1+='</tr>';
	str1+='</thead>';
	str1+='<tbody>';
	  for(var i in result){
		str+='<tr>';
		var totRecrds = result[i].totalRecords;
		var totalSync = result[i].sync;
		var totPending = result[i].pending;
		var kafkaPending = result[i].kafkaPending;
		var  kafkaSync =result[i].kafkaSync;
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
       if(kafkaPending != null)	{
		   str1 +='<td >'+kafkaPending+'</td>';
	   }else {
		   str1 +='<td >'+0+'</td>';
	   }
	   if(kafkaSync != null){
		  str1 +='<td >'+kafkaSync+'</td>';
	   }else {
		  str1 +='<td >'+0+'</td>';
	   }
	   if(totAmount != null){
		 str1 +='<td >'+totAmount+'</td>';
	  }else {
		 str +='<td >'+0+'</td>';
		}
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