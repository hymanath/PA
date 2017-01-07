<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PRINT USER DASHBOARD</title>

<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="js/sha512.js"></script>
</head>
<body>

<div class="container">
	<div class="row">
		<h4>Print User Dashboard</h4>
	</div>
</div>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

<script type="text/javascript">
   
   var printVendorId = '${cardPrintVendorId}'; 
   getPrintStatusWiseConstitCountByLoggedUser();
   function getPrintStatusWiseConstitCountByLoggedUser(){
	   
			var jsObj = { printVendorId : printVendorId }
			$.ajax({
				 type:'POST',
				 url:'getPrintStatusWiseConstitCountByLoggedUserAction.action',
				 dataType: 'json',
				 data: {task:JSON.stringify(jsObj)}
			  }).done(function(result){
				  alert("success...");
			  });
		}
</script>

</body>
</html>