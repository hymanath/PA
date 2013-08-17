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

<script>
ajaxToGetRecordingDetails();
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

	str+='<table border="1">';
	str+='<tr>';
	 str+='<td>User Name</td>';
 	 str+='<td>Action</td>';
	str+='</tr>';
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
	 
	 str+='</table>';

	 $('#reportDiv').html(str);

}
</script>


</head>
<body>

<div id="reportDiv"></div>
</body>
</html>