<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>


<script>
$(document).ready(function () {
		var loginStatus = '${resultStr}';
		var passwordChanged = '${passwordChaged}';
		//alert(passwordChanged);
		if(loginStatus == "success")
		{
			$('#loginModal').modal('show');
			$('#errorMsg').html('<b style="color:green">Login Successfully Please Wait.....</b>');
			$('#loginModal').modal('show').delay('slow').modal('hide');
			window.location.href = "homePage.action?passwordChanged="+passwordChanged;
			
		}
		else if(loginStatus == "failure")
		{
			$('#loginModal').modal('show');
			$('#errorMsg').html('<b style="color:red">Please Enter Valid User Name and Password</b>');
		}
		
		/* if(passwordChanged == "NO")
		{
			$('#passwordWindow').show();
		}
		
		else if(passwordChanged == "YES")
		{
			$('#passwordWindow').hide();
		} */
		
	});
</script>

</body>
</html>