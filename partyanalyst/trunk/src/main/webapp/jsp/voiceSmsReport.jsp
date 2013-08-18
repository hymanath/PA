<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Send Voice Sms</title>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link href="styles/assets/css/bootstrap.css" rel="stylesheet">


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
function ajaxToGetRecordingDetails()
{
   $.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});
	    $.ajax({
		  type:'POST',
		  url: 'generateVoiceSmsReport.action',
		  dataType: 'json',
		  data: {},			 
		  success: function(data){ 
			 buildVoiceSmsReport(data);
		 },
		  error:function() { 
			 			console.log('error', arguments);

		  }
	});
}


function buildVoiceSmsReport(results)
{

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
  $(function() {
    $( "#fromDateId , #toDateId" ).datepicker();
  });
  </script>

</head>
<body>

<div  style="text-align:center;margin:113px 0 0 250px;border:1px solid #f4f4f4;width:429px;padding:27px;background-color:#F5F5F5;">

<p>From Date: <input type="text" id="fromDateId" /></p>

<p>To Date: <input type="text" id="toDateId" /></p>

<input type="button" value="Get Details" onClick="ajaxToGetRecordingDetails()();" class="btn"/>

</div>



<div id="reportDiv"></div>
</body>
</html>