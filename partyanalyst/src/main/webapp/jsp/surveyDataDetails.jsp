<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Survey Details</title>
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"> </script>
</head>
<body>

<script>

function AssignTab()
{
	
	/*var uname = $trim($("#uname").val());
	var tabNo = $trim($("#tabNo").val());
	var remarks = $trim($("#remarks").val());
	var date = $("#date").val()*/
	var jObj = {
		uname : "test",
		tabNo :"1",
		remarks : "remarks",
		date : "7-1-2014"
			
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