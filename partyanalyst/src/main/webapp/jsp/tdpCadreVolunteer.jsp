<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html lang="en">
  <head>
	 <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		 
	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css">      

	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<script type="text/javascript" src="js/photobooth/photobooth_min.js"></script>
		<script type="text/javascript" src="js/photobooth/website/js/cadre.js"></script>
		
		<link type="text/css" rel="stylesheet" media="screen" href="js/photobooth/website/css/page.css" />
		<link rel="stylesheet" href="js/flipclock/flipclock.css">		
		<script src="js/flipclock/flipclock.js"></script>

	<!-- YUI Dependency files (End) -->
	
  </head>
  <body>
  <script>

  </script>
	<div class="container">
	<div id="mainDiv">
	<div id="errorDiv"></div>
	<form action="tdpCadreVolunteerRegistrationAction.action" method="POST" enctype="multipart/form-data" name="uploadCadreForm">	
		<div class="row">
			<div class="span6 offset3">
				<div class="well well-small border-radius-0" style="margin-top:8%;">
				<h4 class="text-center">VOLUNTEER ENROLLMENT FORM</h4>
				<hr>
					<label> Your Name
						<input class="input-block-level border-radius-0" type="text" placeholder="Enter Your Name"  id="nameId" name="tdpCadreVolunteerVO.name">	
					</label>
					<label>Your Email ID
						<input class="input-block-level border-radius-0" type="text" placeholder="Enter Email ID"  id="emailId" name="tdpCadreVolunteerVO.email">
					</label>
					<label>Your Contact Number
						<input  class="input-block-level border-radius-0" type="text" placeholder="Enter Mobile Number"  id="mobileId" name="tdpCadreVolunteerVO.mobileNo">
					</label>
					<label>Select Your Constituency Name
					<s:select multiple="true" theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyId" list="constituencyList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:220px;" name="tdpCadreVolunteerVO.constituencyId" />
					
					</label>
					
					<!----Availability Times---->
					<h4>Select Your Availability Times</h4>
					<label class="checkbox">
						Date: 17-Dec-2014 &nbsp; [Wednesday]<input type="checkbox"  class="dateCheck" name="tdpCadreVolunteerVO.date" value="17-12-2014">
					</label>
					<label class="checkbox">
						Date: 18-Dec-2014 &nbsp; [Thursday]<input type="checkbox" class="dateCheck" name="tdpCadreVolunteerVO.date" value="18-12-2014">
					</label>
					<label class="checkbox" name="tdpCadreVolunteerVO.date">
						Date: 19-Dec-2014 &nbsp; [Friday]<input type="checkbox" class="dateCheck" name="tdpCadreVolunteerVO.date" value="19-12-2014">
					</label>
					<label class="checkbox">
						Date: 20-Dec-2014 &nbsp; [Saturday]<input type="checkbox"  class="dateCheck" name="tdpCadreVolunteerVO.date" value="20-12-2014">
					</label>
					<label class="checkbox">
						Date: 21-Dec-2014 &nbsp; [Sunday]<input type="checkbox" class="dateCheck" name="tdpCadreVolunteerVO.date" value="21-12-2014">
					</label>
					<label class="checkbox">
						Date: 22-Dec-2014 &nbsp; [Monday]<input type="checkbox" class="dateCheck" name="tdpCadreVolunteerVO.date" value="22-12-2014">
					</label><!----/Availability Times---->
					
					<!----Laptop---->
					<h4>Do you have Laptop?</h4>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.lapTop" value="Y" checked>Yes, I have Laptop.
					</label>
					
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.lapTop" value="N">No, I don't have Laptop.
					</label><!----/Laptop---->
					
					<!----Internet Connection---->
					<h4>Do you have Internet Connection?</h4>
					<label class="radio inline">
						<input class="" type="radio" name="tdpCadreVolunteerVO.internet" value="Y" checked>Yes, I have.
					</label>					
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.internet" value="N">No, I don't have.
					</label><!----/Internet Connection---->
					
					<!----Smart-phone with 2G/3G Internet Connection---->
					<h4>Do you have Smart-phone with 2G/3G Internet Connection?</h4>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.smartPhone" value="2G" checked>Yes, I have Smart-phone with 2G.
					</label>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.smartPhone" value="3G">Yes, I have Smart-phone with 3G.
					</label>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.smartPhone" value="no">No, I don't have Smart-phone.
					</label><!----/Smart-phone with 2G/3G Internet Connection---->
					
					<!----TAB with 2G/3G Internet Connection---->
					<h4>Do you have TAB/i-Pad with 2G/3G Internet Connection?</h4>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.tablet" value="2G" class="tabipod tabipodcheck">Yes, I have TAB with 2G.
					</label>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.tablet" value="3G" class="tabipod tabipodcheck">Yes, I have TAB with 3G.
					</label>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.ipod" value="2G" class="tabipod tabipodcheck"> Yes, I have i-Pad with 2G.
					</label>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.ipod" value="3G" class="tabipod tabipodcheck">Yes, I have i-Pad with 3G.
					</label>					
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.noTab" value="no"class = "tabipodcheck" onclick="clearRadioGrp();">No, I don't have TAB/i-Pad.
					</label><!----/Smart-phone with 2G/3G Internet Connection---->
					
					<h4>Your Address</h4>
					<textarea rows="3" class="input-block-level border-radius-0" name="tdpCadreVolunteerVO.address">
					</textarea>
					
					<input type="button" id="submitCadreFormBtnReqId" class="btn btn-success btn-large btn-block border-radius-0" value="SUBMIT DETAILS"/>
				</div>
			</div>
		</div>
		</form>
		</div>
		<div id="statusDiv">
</div>
	</div>
	<script>
	function submitCadreForm()
	{	
alert('a');
if(validateDetails())
{
				var uploadHandler = {
						upload: function(o) {
							uploadResult = o.responseText;
							//console.log(uploadResult);
							showUploadStatus(uploadResult);	
						}
					};

				YAHOO.util.Connect.setForm('uploadCadreForm',true);
				YAHOO.util.Connect.asyncRequest('POST','tdpCadreVolunteerRegistrationAction.action',uploadHandler);
		}	
else return;		
	}
	function validateDetails()
	{
	alert('aa');
	$("#errorDiv").html('');
	var flag = true;
	var errorstr = '';
	var name = $.trim($("#nameId").val());
	var emailId = $.trim($("#emailId").val());
	var mobileId = $.trim($("#mobileId").val());
	var constituencyId = $.trim($("#constituencyId").val());
	 if(name == '')
	{
	 errorstr +='Name is required</br>';
	 flag = false;
	 }
	  if(emailId == '')
	{
	 errorstr +='email is required</br>';
	 flag = false;
	 }
	   if(mobileId == '')
	{
	 errorstr +='mobile is required</br>';
	 flag = false;
	 }
	if(constituencyId == null)
	{
	 errorstr +='Constituency is required<br/>';
	 flag = false;
	 }
	
	$(".dateCheck").each(function()
	{
		 if($(this).is(':checked') == false)
		 {
		 errorstr +='Date is required';
		 flag = false;
		 return ;
		 }
	});
	
	$("#errorDiv").html(errorstr);
	alert(flag)
     return flag;
	
	}
	function showUploadStatus(myResult)
	{
		$('#mainDiv').html('');
		var result = (String)(myResult);
	//var errorDivEle = document.getElementById('errorMsgDiv');
		var str = '';
		var resultArr = result.split(',');
		if(result.search('SUCCESS') != -1)
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align">Successfully Registration Completed</h3>';
			str+= '</div>';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Your Enrollment No :'+resultArr[1]+' </p>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreVolunteerAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		else if(result.search('FAILURE') != -1)
		{
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<h3 class="text-align">Error raised while cadre registration</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<a href="tdpCadreVolunteerAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		
		$('#statusDiv').html(str);
	}
	function clearRadioGrp()
	{
	$(".tabipod").attr('checked', false);
	}
	</script>
  </body>