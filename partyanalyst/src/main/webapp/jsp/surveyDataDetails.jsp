<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Survey Details</title>
</head>
<body>

<script>

function AssignTab()
{
	var uname = $trim($("#uname").val());
	var tabNo = $trim($("#tabNo").val());
	var remarks = $trim($("#remarks").val());
	var date = $("#date").val()
	var jObj = {
		uname : uname,
		tabNo :tabNo,
		remarks : remarks,
		date : date
			
	}
	$.ajax({
          type:'POST',
          url: 'assignTabAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jObj)},
     	  }).done(function(result){ 
			 
	   });
	
}
</script>



</body>
</html>