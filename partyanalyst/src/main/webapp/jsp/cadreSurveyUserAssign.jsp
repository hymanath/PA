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
<title> LOCKING AND UNLOCKING TAB USER  </title>
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
<link href="dist/DatatableBootstrap/DatatableB.css" rel="stylesheet" type="text/css"/>
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
<div class="container">  
	<div class="row">   

			<div class="col-md-12 col-xs-12 col-sm-12">
				<div class="block">				
								
				<ul class="tab">
				  <li><a href="javascript:void(0)" class="tablinks" id="1">UPDATE USER</a></li>
				  <li><a href="javascript:void(0)" class="tablinks" id="2" >UPDATE IMEI NO </a></li>
				  <li><a href="javascript:void(0)" class="tablinks" id="3"  >ASSIGN USER</a></li>
				</ul>

				</div>
			</div>
			<div class="col-md-12 col-xs-12 col-sm-12 tabcontent"  id="updateUserDivId" style="margin-left:15px;" >
				<div class="block">
					<div class="row">
						<div class="col-md-6 col-xs-12 col-sm-6" style="margin-top: 25px">
						<div id="errorId" style="color:red;"></div>
								<label> <h6> UNLOCK THE USER : </h6></label>
								<input type ="text" name="userName" placeholder="Enter username" id="UpdteUsrId"/>
								<input type ="submit" value="Get Details" class=" btn btn-success" onclick ="getDetailsByUserName();"/>
						</div>
					</div>
						<div class="row">
							<div class="col-md-6 col-xs-12 col-sm-6" style="margin-top: 25px" id="usersDetailsDiv">							
							</div>
						</div>
				</div>
			</div>	
			
			<div class="col-md-12 col-xs-12 col-sm-12 tabcontent" id="updateImeiDivId"   style="display:none;margin-left:15px;">
				<div class="block">
					<div class="row">
						<div class="col-md-12 col-xs-12 col-sm-6" style="margin-top: 25px">
						 <div id="errorImeiNo" style="color:red;"></div>
							<label> <h6>UNLOCK THE IMEI NO : </h6></label>
							<input type ="text" name="IMEINo" placeholder="Enter IMEI no" id="updatedIMEIId"/>
							<input type ="submit" value="Get Details" class=" btn btn-success"  onclick ="getIMEINumberDetails();"/>
						</div>
					</div>
					<div class="row">
							<div class="col-md-6 col-xs-12 col-sm-6" style="margin-top: 25px" id="imeiDetailsDiv">							
							</div>
						</div>
				</div>
			</div>
			
			<div class="col-md-12 col-xs-12 col-sm-12 tabcontent" id="assignUserDivId"   style="display:none;margin-left:15px;">
				<div class="block">
					<div class="row">
					<div class="col-md-12 col-xs-12 col-sm-6" style="margin-top: 25px">	
                       <div id="assignErrorId" style="color:red;"></div>					
							<div class="col-md-4 col-xs-12 col-sm-6">
								<div class="row">
									<label> <h6> USERNAME : </h6></label>
									<input type ="text" name="userName" placeholder="Enter UserName" id="AssignUserNameId"/></br>
								</div>	
							</div>	
							<div class="col-md-4 col-xs-12 col-sm-6">
								<div class="row">
									<label> <h6> IMEI NO. : </h6></label>
									<input type ="text" name="IMEINo" placeholder="Enter IMEI no " id="AssignImeiNumberId"/></br>
								</div>
							</div>
							<div class="col-md-4 col-xs-12 col-sm-6">
								<div class="row">
									<input type ="submit" class=" btn btn-success" value="ASSIGN USER" onclick ="getUpdatedIMEINumberDetails();"/>
								</div>
							</div>							
					</div>	
					</div>
					</div>
				</div>
			</div>
				
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
<script src="https://cdn.datatables.net/v/bs/dt-1.10.12/datatables.min.js" type="text/javascript"></script>



<script type="text/javascript">

$('.tablinks').click(function(){
	$('.tabcontent').hide();
		   $('#UpdteUsrId,#AssignUserNameId,#AssignImeiNumberId,#updatedIMEIId').val('');
		   $('#usersDetailsDiv').html('');
		   $('#imeiDetailsDiv').html('');
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
		  userName:"180_committee_001"  
	      
	 }
	 if(uname =='' && uname == 0)
	{
		$("#errorId").html("UserName Required");
		return;
	}else{
		$("#errorId").html("");
	}
	 
    $.ajax({
          type:'GET',
          url: 'getDetailsByUserNameAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   buildGetDetailsByUserName(result);
	   }
   });
  }
  function buildGetDetailsByUserName(result)
  {
  var str='';
		
		str+='<table  class="table table-condensed table-bordered" id="userNameTableId">';
			str+='<thead>';
			str+='<tr>';
				str+='<th> IMEI NO </th>';
				str+='<th> PRESENT STATUS </th>';
				str+='<th> LAST LOGIN TIME </th>';
				str+='<th> CONSTISTUENCY NAME </th>';
			str+='</tr>';
			str+='</thead>';
		for(var i in result){
			str+='<tr>';
				str+='<td> '+result[i].imiNo+' </td>';
				str+='<td>  '+result[i].status+' </td>';
				str+='<td>  '+result[i].insertedTime+' </td>';
				str+='<td>  '+result[i].constistuencyName+' </td>';
			str+='</tr>';
			
		}	
		str+='</table>';
	   $('#usersDetailsDiv').html(str);
	  $("#userNameTableId").dataTable({
	   "aaSorting": [[ 0, "asc" ]],
	   "iDisplayLength": 20,
	   "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
});

  }
  
  function getIMEINumberDetails(){
	var imeiNo = $("#updatedIMEIId").val();
	 
	  var jsObj = {
		  imeiNumber:"865498027253814"  //865498027263995  9999
	      
	 }
	 if(imeiNo == 0 && imeiNo == '')
	 {
		 $("#errorImeiNo").html("ImeiNumber Required");
		 return;
	 }else{
		 $("#errorImeiNo").html("");
	 }
	 
    $.ajax({
          type:'GET',
          url: 'getUpdatedIMEINumberDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   buildGetIMEINumberDetails(result);
	   }
   });
  }
function buildGetIMEINumberDetails(result)
{
var str='';
	
	str+='<table  class="table table-condensed table-bordered" id="iMEINumberDetailsId">';
		str+='<thead>';
		str+='<tr>';
			str+='<th> USER NAME  </th>';
			str+='<th> PRESENT STATUS </th>';
			str+='<th> LAST LOGIN TIME </th>';
			str+='<th> CONSTISTUENCY NAME </th>';
		str+='</tr>';
		str+='</thead>';
	for(var i in result){
		str+='<tr>';
			str+='<td> '+result[i].name+' </td>';
			str+='<td>  '+result[i].status+' </td>';
			str+='<td>  '+result[i].insertedTime+' </td>';
			str+='<td>  '+result[i].constistuencyName+' </td>';
		str+='</tr>';
		
	}	
	str+='</table>';
   $('#imeiDetailsDiv').html(str);
	$("#iMEINumberDetailsId").dataTable({
   "aaSorting": [[ 0, "asc" ]],
   "iDisplayLength": 20,
   "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
});

}

   function getUpdatedIMEINumberDetails(){
	var assignUname = $("#AssignUserNameId").val();
	var assignImeiNo = $("#AssignImeiNumberId").val();
	  var jsObj = {
		  userName:"180_committee_001",  
	      imeiNumber:"865498027253814"
	 }
	if(assignUname =='' && assignUname == 0)
	{
		$("#assignErrorId").html("UserName Required");
		return;
	}else{
		$("#assignErrorId").html("");
	}
	if(assignImeiNo =='' && assignImeiNo == 0)
	{
		$("#assignErrorId").html("ImeiNumber Required");
		return;
	}else{
		$("#assignErrorId").html("");
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