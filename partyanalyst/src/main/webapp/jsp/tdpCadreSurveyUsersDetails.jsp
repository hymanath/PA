<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
 <title> Cadre Survey Users Details </title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <script src="js/icheck/icheck.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<style>	
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	
	</style>	
	
  <script>
	

	function saveNewCadreSurveyUser()
	{
	var userName = $('#userNameId').val();
	var password = $('#passwordId').val();
	var mobileNo = $('#moboleNoId').val();
	$('#errorDiv1').html('');

	
	if(userName.trim().length ==0)
	{
		$('#errorDiv1').html('Please enter User Name.');
		return;
	}
	if(password.trim().length ==0)
	{
		$('#errorDiv1').html('Please enter Password.');
		return;
	}
	if(mobileNo.trim().length ==0)
	{
		$('#errorDiv1').html('Please enter Mobile No.');
		return;
	}
	
		var jsObj = 
			   {	
				surveyUserName : userName,
				password : password,
				mobileNo : mobileNo,
				task:"saveNewCadreSurveyUser"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "saveNewCadreSurveyUserAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					if(result != null)
					{
						if(result.resultCode == 0)
						{
							$('#errorDiv1').html('<span style="color:green;">'+result.message+'</span>');
							 $('#userNameId,#passwordId,#moboleNoId').val('');
						}
						else
						{
							$('#errorDiv1').html(''+result.message+'');
						}
					}
				});
				
	}
	
	
	function getSurveyCadreUsersList()
	{
		var jsObj = 
			   {
				task:"getSurveyCadreUsersList"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getSurveyCadreUsersListAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					$('#assigndUserId').find('option').remove();
					$('#assigndUserId').append('<option value="0"> Select User </option>');
					if(result != null)
					{
						for(var i in result)
						{
							$('#assigndUserId').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}
					
				});
	}
	
	function getSurveyCadreAssignedConstituencyList()
	{
		var jsObj = 
			   {
				task:"getSurveyCadreAssignedConstituencyList"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getSurveyCadreAssignedConstituencyListAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					$('#releseConstituency').find('option').remove();
					$('#releseConstituency').append('<option value="0"> Select Constituency </option>');
					if(result != null)
					{
						for(var i in result)
						{
							$('#releseConstituency').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}
				});
	}
	
	function getSurveyCadreAssignedUsersList()
	{
		var constiteuncyId =  $('#releseConstituency').val();
		$('#statusDiv').html('');
		if(constiteuncyId == 0)
		{
			$('#statusDiv').html('Please Select Constituency.');
			return;
		}
		var jsObj = 
			   {
			   constituencyId : constiteuncyId,
				task:"getSurveyCadreAssignedUsersList"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getSurveyCadreAssignedUsersListAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					$('#releasedUserId').find('option').remove();
					$('#releasedUserId').append('<option value="0"> Select User </option>');
					if(result != null)
					{
						for(var i in result)
						{
							$('#releasedUserId').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}
				});
	}
	
	function releaseCadreSurveyUser()
	{
		var releasedUserId =$('#releasedUserId').val(); 
		var constiteuncyId =  $('#releseConstituency').val();
		$('#statusDiv').html('');
		if(constiteuncyId == 0)
		{
			$('#statusDiv').html('Please Select Constituency.');
			return;
		}
		if(releasedUserId == 0)
		{
			$('#statusDiv').html('Please Select User.');
			return;
		}
		
		var jsObj = 
			   {
			   cadreSurveyUserAssignedId : releasedUserId,
				task:"releaseCadreSurveyUser"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "releaseCadreSurveyUserAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null)
					{
						if(result.resultCode == 0)
						{
							$('#statusDiv').html('<span style="color:green;">'+result.message+'</span>');
							$('#releasedUserId').find('option').remove();
							$('#releasedUserId').append('<option value="0"> Select User </option>');
							
								getSurveyCadreAssignedConstituencyList();
								
						}
						else
						{
							$('#statusDiv').html(''+result.message+'');
						}
						
					}
				});
	}
	
	
	function assgnUserForLocation()
	{
		var levelId 			= $('#levelList').val(); 
		var levelValue			= $('#levelValueList').val(); 
		var constituencyId 		= $('#assigndConstituency').val(); 
		var cadreSurveyUserId 	= $('#assigndUserId').val();
		var tabNo = $('#tabNoId').val();		
		$('#errorDiv2').html('');
		
		if(cadreSurveyUserId == 0)
		{
				$('#errorDiv2').html('Please select user.');
				return;
		}	
		if(constituencyId == 0)
		{
				$('#errorDiv2').html('Please select constituency.');
				return;
		}	
		if(levelValue == 0)
		{
				$('#errorDiv2').html('Please select Location.');
				return;
		}	
		if(tabNo.trim().length == 0)
		{
				$('#errorDiv2').html('Please enter Tab No.');
				return;
		}	
		
		var jsObj = 
			   {
				  levelId:levelId,
				  levelValue:levelValue,
				  constituencyId:constituencyId,
				  cadreSurveyUserId : cadreSurveyUserId,
				  tabNo : tabNo,
				  task:"assgnUserForLocation"          
			   }			   
			   $.ajax({
					type : "POST",
					url : "assignUserForLocationAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				
					if(result != null)
					{
						if(result.resultCode == 0)
						{
							$('#errorDiv2').html('<span style="color:green;">'+result.message+'</span>');
							 $('#assigndConstituency').val(0);
							 $('#levelList').val(1);
						 
							 $('#levelValueList').find('option').remove();
							$('#levelValueList').append('<option value="0"> Select Location </option>');
					
							 getSurveyCadreUsersList();
						}
						else
						{
							$('#errorDiv2').html(''+result.message+'');
						}	
					}		
				});
				
	}
	
	function getAllDetails()
	{
		var constituencyId 		= $('#assigndConstituency').val(); 
		var areaType = "";
		
		var areayTypeId = $('#levelList').val();
		
		if(areayTypeId ==1)
			 areaType = "RURAL";
		if(areayTypeId ==2)
			 areaType = "MUNCIPALITY";
		if(areayTypeId ==3)
			 areaType = "CORPORATION";
		if(areayTypeId ==4)
			 areaType = "DIVISION";
			
			
		 var jsObj =
		{ 
		constituencyId : constituencyId,
		scope : areaType,
		task:"getSubRegionsInConstituency"
		};
		
			   $.ajax({
					type : "POST",
					url : "getSubRegionsInConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					$('#levelValueList').find('option').remove();
					$('#levelValueList').append('<option value="0"> Select Location </option>');
					if(result != null)
					{
						for(var i in result)
						{
								$('#levelValueList').append('<option value="'+result[i].id+'"> '+result[i].name+' </option>');
						}
					}
				});
	}
		
	function checkIsTabAssigned()
	{
		var tabNo = $('#tabNoId').val();
		
		if(tabNo.trim().length > 0)
		{
			var jsObj =
			{ 
			tabNo : tabNo,
			task:"checkIsTabAssigned"
			};
			
			$.ajax({
					type : "POST",
					url : "isTabAssignedAlreadyAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				if(result != null)
				{
					if(result.indexOf('available') < 0 )
					{
						$('#errorDiv2').html(''+result+'');
					}
					else
					{
						$('#errorDiv2').html('<span style="color:green;">'+result+'</span>');
					}
				}
					//
			});
		}
	}
  </script>	
</head>
  <body style="background-color: #3598db;">
		<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<div class="row-fluid">
				  <div class="span4 offset4 ">
						<img src="images/cadre_images/2014-cadre-Registration-Logo.png">
				  </div>
				  <div class="span4">
					 <a href="newlogoutAction.action" style="font-weight: bold;" class="btn btn-mini pull-left m_top20">Logout</a>
				  </div>
				</div>
			</div>
		</div><!-- Header Row End-->
	
	<div class="container">
		<div class="row">
			<div class="span8 offset2 well well-small border-radius-0 pad-5">
				<h4 class="text-center btn btn-block btn-large disabled border-radius-0" style="margin-top: 0px;">Create New Cadre User</h4>
				<div id="errorDiv1" style="color:red;"></div>
					<div class="row-fluid m-bottom-10 ">
				
					<div class="span4">
						<h5> User Name </h5>
						 <input type="text" id="userNameId" placeholder="Enter User Name " class="border-radius-0 input-block-level">
					</div>
					<div class="span4">
						<h5> Password </h5>
						<input type="password" id="passwordId" placeholder="Enter Password " class="form-control border-radius-0 input-block-level">
					</div>
					<div class="span4">
						<h5> Mobile No </h5>
						<input type="text" id="moboleNoId" placeholder="Enter Mobile No " class="form-control border-radius-0  input-block-level" maxlength="10">						
					</div>					
				</div>
				<div class="row-fluid m-bottom-10" style="">
					<div class="span4 offset4 text-center">
						<a href="javascript:{saveNewCadreSurveyUser()}" class="btn btn-success" style=""> Create User </a>	
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="row">
			<div class="span8 offset2 well well-small border-radius-0 pad-5">
				<h4 class="text-center btn btn-block btn-large disabled border-radius-0 " style="margin-top: 0px;">Assign Cadre User</h4>
				<div style="color:red;" id="errorDiv2"></div>
				<div class="row-fluid m-bottom-10 ">
					<div class="span6">
						<h5> User Name </h5> 
						<img src="images/icons/refreshImg.png" title=" Refresh Users List " onclick="getSurveyCadreUsersList();" style="float:right;margin-top:-25px;cursor: pointer;">
						<s:select theme="simple" cssClass="selectBoxWidth  input-block-level" id="assigndUserId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select User "  />									
					</div>
					<div class="span6">
						<h5> Constituency </h5>
						<s:select theme="simple" cssClass="selectBoxWidth  input-block-level" id="assigndConstituency" list="constituencyesList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency "   onchange="getAllDetails();" />						
					</div>	
					
					<div style="margin-left: 0px;" class="span4">
						<h5> Level </h5>
						<select class="input-block-level" id="levelList" onchange="getAllDetails()">
						<option value="1"> Mandal </option>
						<option value="2"> Muncipality </option>
						<option value="3"> Corporation </option>
						<option value="4"> Division  </option>
						</select>
					</div>
					
					<div class="span4">
						<h5> Location </h5>
						<select id="levelValueList" class="input-block-level"><option value="0"> Select Location </option></select>						
					</div>
					<div class="span4">
						<h5> Tab No </h5>
						<input type="text" maxlength="10" class="form-control border-radius-0 input-block-level " placeholder="Enter Tab No " id="tabNoId" onblur="checkIsTabAssigned()">	
					</div>	
					
				</div>

				<div class="row-fluid m-bottom-10 ">
					<div class="span4 offset4 text-center">
						<a class="btn btn-success" href="javascript:{assgnUserForLocation()}"> Assign User </a>							
					</div>
				</div>
			</div>
		</div>
		

		<div class="row">
			<div class="span8 offset2 well well-small border-radius-0 pad-5">
				<h4 style="margin-top: 0px;" class="text-center btn btn-block btn-large disabled border-radius-0 ">Release Cadre User</h4>
				<div id="statusDiv" style="color:red;"></div>
				<div class="row-fluid  m-bottom-10">				
					<div class="span6">
						<h5> Constituency </h5>
						<img src="images/icons/refreshImg.png" title=" Refresh Constituency List " onclick="getSurveyCadreAssignedConstituencyList();" style="float:right;margin-top:-25px;cursor: pointer;">
						<s:select theme="simple" cssClass="selectBoxWidth  input-block-level" id="releseConstituency" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency "   onchange="getSurveyCadreAssignedUsersList()"/>								
					</div>
					<div class="span6">
						<h5> User Name </h5>
						<select class="input-block-level" id="releasedUserId"><option value="0"> Select User </option></select>
					</div>		
				</div>

				<div class="row-fluid">
					<div class="span4 offset4 text-center">
						<a href="javascript:{releaseCadreSurveyUser()}" class="btn btn-success"> Release User </a>	
					</div>
				</div>
			</div>
		</div>
				
	</div>
	<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
		
  </body>

</html>