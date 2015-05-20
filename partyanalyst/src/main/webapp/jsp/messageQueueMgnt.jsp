<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Mahanadu Message Queue Management</title>
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/TDP.gif">
<script src="dist/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<link href="dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">

<style>


</style>
</head>
<body>
<div class="container">
		
	<div class="row" style="padding:5px;">
	 <h4 class="text-center">Mahanadu Message Queue Management</h4>
	<div id="errorDiv" style="margin-left: 360px;color:red;"></div>
 <form class="navbar-form  text-center" role="search">
 
  <div class="form-group ">
    Consumers Count <input type="text" class="form-control" placeholder="number" id="consumerCnt" onkeypress="return isNumber(event)">
  </div>
  <button type="button" class="btn btn-info" id="createbtn" onclick="createConsumer();">Create Consumer</button>
</form>
</div>
<script>
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}
function createConsumer()
{
	$("#errorDiv").html("");
	var count = $("#consumerCnt").val().trim();
	if(count.length == 0)
	{
		$("#errorDiv").html("enter number");
		return;
	}
		var jObj = {
			count:count,
			 task:""
		}	
		
		$.ajax({
          type:'GET',
          url: 'createConsumerForMsgQueueAction.action',
		  data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
		  })
}
</script>
</body>
</html>
