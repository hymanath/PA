<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Change</title>
</head>
<body>
<script>
$(document).ready(function () {
		var status = '${status}';
		if(status == "success")
		{
			$('#passwordModal').modal('show');
			$('#errorMessage').html('<b style="color:green">Password Changed Successfully...</b>');
			$('#passwordModal').modal('show').delay('slow').modal('hide');
			window.location.href = "homePage.action";
			
		}
		else 
		{
			$('#passwordModal').modal('show');
			//$('#errorMessage').html('<b style="color:red">Please Enter Valid Paaword</b>');
		}
		
	});
</script>
</body>
</html>