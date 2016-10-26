function getDataReConsalationOverView(){
	 
	  //var constistuencyId = $("#vendorId option:selected").val();
	  var dates = $(".datePicker").val();
	  var dateArr = dates.split("-");
	  var fromDate;
	  var toDate;
	  var jsObj = { constistuencyId : 331,
                    fromDate : "10/21/2016",
                   	toDate : "10/26/2016"
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
	 
	  //var constistuencyId = $("#vendorId option:selected").val();
	  var dates = $(".datePicker").val();
	  var dateArr = dates.split("-");
	  var fromDate;
	  var toDate;
	  var jsObj = { constistuencyId : 331,
                    fromDate : "10/21/2016",
                   	toDate : "10/26/2016"
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
				str+='<td>'+result[i].serverPending+'<button class="btn btn-sm btn-success openPopUpModel" style="margin-right: 0px; margin-left: 100px;">VIEW DAY WISE</button></td>';
				str+='</tr>';	
		   }				
				str+='</tbody>';
				str+='</table>';
				str+='</div>';
			    str+='</div>';
				$("#userWiseTotalViewId").html(str);
	 
 }
