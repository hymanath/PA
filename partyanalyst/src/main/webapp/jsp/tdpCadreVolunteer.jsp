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

	<form action="tdpCadreVolunteerRegistrationAction.action" method="POST" enctype="multipart/form-data" name="uploadCadreForm" >	
		<div class="row">
			<div class="span6 offset3">
				<div class="well well-small border-radius-0" style="margin-top:8%;">
				 <div id="errorDiv" class="offset1" style="color:red;"></div>
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
					<s:select multiple="true" theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="constituencyId" list="constituencyList" listKey="id" listValue="name" headerKey="0" style="width:220px;" name="tdpCadreVolunteerVO.constituencyId" />
					
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
						<input id="lapradioId" type="radio" name="tdpCadreVolunteerVO.lapTop" value="Y" class="unchek" checked>Yes, I have Laptop.
					</label>
					
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.lapTop" value="N">No, I don't have Laptop.
					</label><!----/Laptop---->
					
					<!----Internet Connection---->
					<h4>Do you have Internet Connection?</h4>
					<label class="radio inline">
						<input id="netradioId" type="radio" name="tdpCadreVolunteerVO.internet" value="Y" checked>Yes, I have.
					</label>					
					<label class="radio inline">
						<input type="radio"   name="tdpCadreVolunteerVO.internet" value="N">No, I don't have.
					</label><!----/Internet Connection---->
					
					<!----Smart-phone with 2G/3G Internet Connection---->
					<h4>Do you have Smart-phone with 2G/3G Internet Connection?</h4>
					<label class="radio inline">
						<input id="samrtPhoneradioId"  type="radio" name="tdpCadreVolunteerVO.smartPhone" value="2G" checked>Yes, I have Smart-phone with 2G.
					</label>
					<label class="radio inline"  style="margin-left:0px;">
						<input type="radio" name="tdpCadreVolunteerVO.smartPhone" value="3G">Yes, I have Smart-phone with 3G.
					</label>
					<label class="radio inline"  style="margin-left:0px;">
						<input type="radio"  name="tdpCadreVolunteerVO.smartPhone" value="no">No, I don't have Smart-phone.
					</label><!----/Smart-phone with 2G/3G Internet Connection---->
					
					<!----TAB with 2G/3G Internet Connection---->
					<h4>Do you have TAB/i-Pad with 2G/3G Internet Connection?</h4>
					<label class="radio inline">
						<input id="tabradioId" type="radio" name="tdpCadreVolunteerVO.tablet" value="2G" class="tabipod tabipodcheck " checked>Yes, I have TAB with 2G.
					</label>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.tablet" value="3G" class="tabipod tabipodcheck ">Yes, I have TAB with 3G.
					</label>
					<label class="radio inline"  style="margin-left:0px;">
						<input id="ipodradioId" type="radio" name="tdpCadreVolunteerVO.ipod" value="2G" class="tabipod tabipodcheck " checked> Yes, I have i-Pad with 2G.
					</label>
					<label class="radio inline">
						<input type="radio" name="tdpCadreVolunteerVO.ipod" value="3G" class="tabipod tabipodcheck ">Yes, I have i-Pad with 3G.
					</label>					
					<label class="radio inline"  style="margin-left:0px;">
						<input type="radio" id="notabipod" name="tdpCadreVolunteerVO.noTab" value="no" class = "tabipodcheck " onclick="clearRadioGrp();">No, I don't have TAB/i-Pad.
					</label><!----/Smart-phone with 2G/3G Internet Connection---->
					
					<h4>Your Address</h4>
					<textarea rows="3" class="input-block-level border-radius-0" id="addressId" name="tdpCadreVolunteerVO.address">
					</textarea>
					
					<input type="button" id="submitCadreFormBtnReqId" class="btn btn-success btn-large btn-block border-radius-0" value="SUBMIT DETAILS" onclick="submitCadreForm();"/>
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
else 
return false;		
	}
	function validateDetails()
	{
	
	$("#errorDiv").html('');
	var flag = true;
	var errorstr = '';
	var name = $.trim($("#nameId").val());
	var emailId = $.trim($("#emailId").val());
	var mobileId = $.trim($("#mobileId").val());
	var constituencyId = $("#constituencyId").val();
	var addressId = $.trim($("#addressId").val());
	var emailreg = /^([A-Za-z0-9_\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

	var datearr = [];
	 if(name == '')
	{
	 errorstr +='Name is required</br>';
	 flag = false;
	 }
	  if(emailId .length == 0)
	{
	 errorstr +='email is required</br>';
	 flag = false;
	 }
	if(emailId .length > 0 && emailreg.test(emailId) == false)
	 {
		errorstr +='Invalid Email</br>';
		 flag = false;
	}
	   if(mobileId.length == 0)
	{
	 errorstr +='mobile is required</br>';
	 flag = false;
	 }
	if(mobileId.length != 0){
			if(isNaN(mobileId) || mobileId.length<10 || mobileId.length>10 || !(mobileId.charAt(0)=="9" || mobileId.charAt(0)=="8" || mobileId.charAt(0)=="7")){
			errorstr +='Invalid Mobile</br>';
			flag=false;
			}
		}
	
	if(constituencyId == null)
	{
	 errorstr +='Select Constituency<br/>';
	 flag = false;
	 }
	
	$(".dateCheck").each(function()
	{
		 if($(this).is(':checked'))
		 {
			datearr.push("true");
		 }
		 
	});
	if(datearr.length  == 0)
	{
	 errorstr +='Select Date<br/>';
	 flag = false;
	}
	if(addressId == '')
	{
	 errorstr +='Address is required<br/>';
	 flag = false;
	}
	if(errorstr.trim().length >0)
		{
	$('html,body').animate({
			scrollTop:  $("#errorDiv").offset().top 
			});
		}	
	$("#errorDiv").html(errorstr);
	
     return flag;
	
	}
	function showUploadStatus(myResult)
	{
		$("#errorDiv").html('');
		var result = (String)(myResult);
		var str = '';
		if(result.indexOf('success'))
		{
		$("#errorDiv").html('Successfully registered').css("color","green");
		}
		else
		{
		$("#errorDiv").html('Exception Occured,Please Try again').css("color","red");
		}
		$('html,body').animate({
			scrollTop:  $("#errorDiv").offset().top 
			});
			clearDataFields();
	}
	function clearDataFields()
	{
		
		$("#nameId").val('');
		$("#emailId").val('');
		$("#mobileId").val('');
		$("#constituencyId option").removeAttr("selected", false);
		$("#addressId").val('');
		$(".dateCheck").attr('checked', false); 
		$("#lapradioId").prop('checked', true);;
		$("#netradioId").prop('checked', true);
		$("#samrtPhoneradioId").prop('checked', true);
		$("#tabradioId").prop('checked', true);
		$("#ipodradioId").prop('checked', true);;
		$("#notabipod").attr('checked', false);
		
	}
	function clearRadioGrp()
	{
	$(".tabipod").attr('checked', false);
	}
	$(".tabipod").click(function() {
	$("#notabipod").attr('checked', false);
	});

	</script>
  </body>