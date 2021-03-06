<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Send Voice Sms</title>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="https://code.jquery.com/jquery-1.9.1.js"></script>
<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link href="styles/assets/css/bootstrap.css" rel="stylesheet">
<link href="dist/css/bootstrap.css" rel="stylesheet"/>

 <style>
  .datagrid table {
	  border-collapse: collapse; 
	  text-align: left; 
	  width: 100%;
   } 
   .datagrid {
	   font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #006699; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px;
  }
  .datagrid table td, .datagrid table th {
	  padding: 3px 10px;
	  text-align:center;
  }
  .datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );#006699;
	  background-color:#006699; 
	  color:#FFFFFF; 
	  font-size: 15px;
	  font-weight: bold;
	  border-left: 1px solid #0070A8;
  } 
  .datagrid table thead th:first-child {
	  border: none; 
  }
  .datagrid table tbody td {
	  color: #00496B; 
	  border-left: 1px solid #E1EEF4;
	  font-size: 12px;
	  font-weight: normal;
   }
   .datagrid table tbody .alt td {
	   background: #E1EEF4;
	   color: #00496B;
	}
	.datagrid table tbody td:first-child {
		border-left: none;
	}
	.datagrid table tbody tr:last-child td {
		border-bottom: none;
	}

	.datagrid table tr:nth-child(even) {background: #E1EEF4}
    .datagrid table tr:nth-child(odd) {background: #FFF}

	
  </style>
<script>

function Dat(){
	$(".eventsheader").find(".span2").removeClass("span2")
	$(".eventsheader").find(".span1").removeClass("span1")
	$(".eventsheader").find(".span3").removeClass("span3")
	$(".eventsheader").find(".span5").removeClass("span5")
}
function ajaxToGetRecordingDetails()
{			 $('#ajaxImage').show();

   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});
	    $.ajax({
		  type:'GET',
		  url: 'generateVoiceSmsReport.action',
		  dataType: 'json',
		  data: {
		  fromDate:document.getElementById("fromDateId").value,
		  toDate:document.getElementById("toDateId").value
		   
		  },			 
		  success: function(data){ 
			 buildVoiceSmsReport(data);
 			 $('#ajaxImage').hide();

		 },
		  error:function() { 
			 			console.log('error', arguments);

		  }
	});
}


function buildVoiceSmsReport(results)
{
	var noOfRecords = 0;
  $.map( results, function(value,index) {
	  noOfRecords++;
		
     });

	 if(noOfRecords == 0){
		  $('#reportDiv').html("<h5 style='text-align:center;color:red;'>No records are available.</h5>");
		  return false;
	 }

	 
	 
	 var str='';
    str+='<div class="datagrid" style="margin-top:57px;">';
	str+='<table>';
	str+='<thead>';
	str+='<tr>';
	 str+='<th>User Name</th>';
 	 str+='<th>Action</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	$.map( results, function(value,index) {
		str+='<tr>';
		  str+='<td>'+index.split("-")[0]+" "+index.split("-")[1]+'</td>';


 	      str+='<td>';

		   $.map( value, function(value1,index1) {

			   str+=index1+"-"+value1+'<br>';
		   });

		  str+='</td>';

		str+='</tr>';

		//alert(+"-"+index);
     });
	 str+='</tbody>';
	 
	 str+='</table>';
	 str+='</div>';

	 $('#reportDiv').html(str);

}
</script>
 <script>
 var arr=[];
  $(function() {
    $( "#fromDateId" ).datepicker({
		
	        changeMonth: true,
            changeYear: true,
			dateFormat: 'dd-mm-yy',
			maxDate: new Date(),
			onSelect: function() {  
				                    
			                        var selectedDate=document.getElementById("fromDateId").value;
									var arr=selectedDate.split('-'); 
						            arr[0]=parseInt(arr[0],10)+parseInt(1);	
									$( "#toDateId" ).datepicker( "option", "minDate", arr.join('-'));
									
                                  } 
    });	                              
								

   $("#toDateId" ).datepicker({
		  
	        changeMonth: true,
            changeYear: true,
			dateFormat: 'dd-mm-yy',
			maxDate: new Date()
	
	 });		                         
});			

 </script>

</head>
<body>

<div  style="text-align:center;margin:113px 0 0 250px;border:1px solid #f4f4f4;width:429px;padding:27px;background-color:#F5F5F5;">

<p>From Date: <input type="text" id="fromDateId" readonly/></p>

<p>To Date: <input type="text" id="toDateId"  readonly style="margin-right: -14px;" /></p>
<input type="button" value="Get Details" onClick="ajaxToGetRecordingDetails();" class="btn"/>

<img src='./images/icons/search.gif' id="ajaxImage" style="display:none;"/>

</div>



<div id="reportDiv"></div>
<script>
Dat();
</script>
</body>
</html>