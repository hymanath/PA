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
<title>Cadre Online Registration</title>
<link href="dist/cadreRegistration/dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<!--<link href="dist/cadreRegistration/landingPage/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/cadreRegistration/landingPage/css/responsive.css" rel="stylesheet" type="text/css">-->
<link href="https://fonts.googleapis.com/css?family=Ubuntu:300,300i,400,400i,500,500i,700,700i" rel="stylesheet">
<!--<link href="dist/cadreRegistration/dist/css/bootstrap.css" rel="stylesheet" type="text/css">-->
<link href="dist/cadreRegistration/dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/cadreRegistration/dist/css/responsive.css" rel="stylesheet" type="text/css"/>
<link href="dist/cadreRegistration/dist/css/animate.css" rel="stylesheet" type="text/css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
<link href="dist/cadreRegistration/dist/plugins/scrollNew/scroll.css" rel="stylesheet" type="text/css"/>
<link href="dist/Plugins/Chosen/chosen.css" rel="stylesheet" type="text/css"/>
<link href="dist/activity/Timepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link type="text/css" rel="stylesheet" media="screen" href="js/photobooth/website/css/page.css" />
<link rel="stylesheet" href="js/flipclock/flipclock.css">
<!-- online First Page-->
	
		
<style>
.tabcontent {
    -webkit-animation: fadeEffect 1s;
    animation: fadeEffect 1s; /* Fading effect takes 1 second */
}

@-webkit-keyframes fadeEffect {
    from {opacity: 0;}
    to {opacity: 1;}
}

@keyframes fadeEffect {
    from {opacity: 0;}
    to {opacity: 1;}
}

/* Style the list */
ul.tab {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Float the list items side by side */
ul.tab li {float: left;}

/* Style the links inside the list items */
ul.tab li a {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of links on hover */
ul.tab li a:hover {background-color: #ddd;}

/* Create an active/current tablink class */
ul.tab li a:focus, .active {background-color: #ccc;}

/* Style the tab content */
.tabcontent {
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}
</style>

 
</style>
</head>
<body>

<ul class="tab">
  <li><a href="javascript:void(0)" class="tablinks" id="1">UPDATE USER</a></li>
  <li><a href="javascript:void(0)" class="tablinks" id="2" >UPDATE IMEI NO </a></li>
  <li><a href="javascript:void(0)" class="tablinks" id="3"  >ASSIGN USER</a></li>
</ul>

<div id="updateUserDivId" class="tabcontent">
  <h3> UNLOCK THE USER : </h3>
  <input type ="text" name="userName" value="userName" id="UpdteUsrId"/>
  <input type ="submit" value="Get Details" class=" btn btn-success" onclick ="getDetailsByUserName();"/>
</div>

<div id="updateImeiDivId" class="tabcontent"  style="display:none;">
  <h3>UNLOCK THE IMEI NO : </h3>
  <input type ="text" name="IMEINo" value="IMEINo" id="updatedIMEIId"/>
  <input type ="submit" value="Get Details" class=" btn btn-success"  onclick ="getIMEINumberDetails();"/>
</div>

<div id="assignUserDivId"  class="tabcontent"   style="display:none;">
  <h3> ASSIGN USER TO IMEI NO. </h3>
  <input type ="text" name="userName" value="userName" id="AssignUserNameId"/></br>
  <input type ="text" name="IMEINo" value="IMEINo" id="AssignImeiNumberId"/>
  <input type ="submit" class=" btn btn-success" value="ASSIGN USER" onclick ="getUpdatedIMEINumberDetails();"/>
</div>


<!--<script src="dist/cadreRegistration/dist/js/jquery-1.11.3.js" type="text/javascript"></script>-->
<!-- online First Page Script -->
<!--<script src="dist/cadreRegistration/landingPage/js/jquery-1.11.3.js" type="text/javascript"></script>-->
<!--<script src="dist/cadreRegistration/landingPage/js/bootstrap.js" type="text/javascript"></script>-->

<script src="https://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script src="dist/cadreRegistration/dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/cadreRegistration/dist/plugins/scrollNew/scroll.js" type="text/javascript"></script>
<script src="dist/Plugins/Chosen/chosen.jquery.js" type="text/javascript"></script>

<script src="dist/DateRange/moment.js" type="text/javascript"></script>
<script src="dist/activity/Timepicker/bootstrap-datetimepicker.min.js" type="text/javascript"></script>



<script type="text/javascript">

$('.tablinks').click(function(){
	$('.tabcontent').hide();
	var tab_id = $(this).attr('id');
	if(tab_id==1)
		$('#updateUserDivId').show();
	else if(tab_id==2)
		$('#updateImeiDivId').show();
	else if(tab_id==3)
		$('#assignUserDivId').show();
	
	
});



function getDetailsByUserName(){
	var uname = $("#UpdteUsrId").val();
	 
	  var jsObj = {
		  userName:"294_001"  
	      
	 }
	 
    $.ajax({
          type:'GET',
          url: 'getDetailsByUserNameAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   
   });
  }
  
  function getIMEINumberDetails(){
	var imeiNo = $("#updatedIMEIId").val();
	 
	  var jsObj = {
		  imeiNumber:imeiNo  
	      
	 }
	 
    $.ajax({
          type:'GET',
          url: 'getUpdatedIMEINumberDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   
   });
  }
  
   function getUpdatedIMEINumberDetails(){
	var assignUname = $("#AssignUserNameId").val();
	var assignImeiNo = $("#AssignImeiNumberId").val();
	  var jsObj = {
		  userName:assignUname,  
	      imeiNumber:assignImeiNo
	 }
	 
    $.ajax({
          type:'GET',
          url: 'getAssigndUsrDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   
   });
  }
  
  
</script>
</body>
</html>