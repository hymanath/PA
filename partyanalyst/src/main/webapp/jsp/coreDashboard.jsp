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
		getCommitteesWiseLevelsBasedDetails();
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
    
    function getCommitteesWiseLevelsBasedDetails(){
    	
       var userAccessLevelValues = [];
       userAccessLevelValues.push(19);
       userAccessLevelValues.push(23);
       
       var basicCommitteeIdsArray= [];
       basicCommitteeIdsArray.push(1);
       basicCommitteeIdsArray.push(2);
       basicCommitteeIdsArray.push(3);
	   
       var startDateString = '01/01/2015';
	   var endDateString = '28/07/2016';
	   var userAccessLevelId = '3';
	   var state = 'AP';
	   
 	   var jsObj ={ userAccessLevelId:userAccessLevelId,
 			        userAccessLevelValuesArray : userAccessLevelValues,
 			        state : state,
 			        basicCommitteeIdsArray:basicCommitteeIdsArray,
 			        startDateString : startDateString,
 			        endDateString :   endDateString
 			      };
 	   
 	   $.ajax({
 			type : 'POST',
 			url : 'getCommitteesWiseLevelsBasedDetailsAction.action',
 			dataType : 'json',
 			data : {task:JSON.stringify(jsObj)}
 		}).done(function(result){
 			alert(123);
 		});
 	}
    
</script>
</body>
</html>