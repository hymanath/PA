<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cards Print Dashboard</title>
<link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/custom.css" rel="stylesheet" type="text/css">
<link href="dist/CardPrint/dropkick.css" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" rel="stylesheet" type="text/css"/>
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="newCoreDashBoard/Plugins/Slick/slick.css" type="text/css" rel="stylesheet"/>
<link href="newCoreDashBoard/Plugins/Slick/slick-theme.css" type="text/css" rel="stylesheet"/>
<body>
<div class="container">
   <div class="row">
	 <h4 class="textcenter">This is the Card Printing Status</h4>
   </div>
</div> 
</div>		
</body>
<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script type ="text/javascript">
$(document).ready(function(){
	alert('hi');
	getAlertStatus();
});

function getAlertStatus(){
	var jsObj={
		stateId:1;
	}
	$.ajax({
		url:"getCardPrintingAction.action",
		data:"Post",
		dataType:"JSON",
		data:{task:JSON.stringify(jsObj)}
	}).done(function(result){
		
	})
}
</script>
</head>
</html>