<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="js/coreDashboard/coreDashboard.js" type="text/javascript"></script>
</head>
<body>
<h4>fff</h4>

<script type="text/javascript">

    //GLOBAL VARIABLES.
	var globalUserId = '${sessionScope.USER.registrationID}';
    
	
	$(document).ready(function(){
		onLoadCalls();
	});
	
    function onLoadCalls(){
		getUserAccessLevelAndValues();
		getUserTypeByUserId();
	}
    function getUserAccessLevelAndValues(){
	   var jsObj ={userId:globalUserId}
	   $.ajax({
			type : 'GET',
			url : 'getUserAccessLevelAndValuesAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
   }
    function getUserTypeByUserId(){
	   var jsObj ={userId:globalUserId}
	   $.ajax({
			type : 'GET',
			url : 'getUserTypeByUserIdAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			
		});
	}
</script>
</body>
</html>