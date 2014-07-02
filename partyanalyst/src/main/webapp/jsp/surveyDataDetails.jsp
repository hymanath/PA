<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
<html>
 <head>	
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
	
		<style>
			body{background:#f0f0f0;}
			.m_top20{margin-top:20px;}
			.widgetservey{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px;}
			.widgetservey_Red{background:#fafafa; display:block; border:1px solid #cccccc; width:100%; padding:0px 20px 20px 20px; border-top:3px solid #ff0000;}
			.widgetservey h4{font-size:26px; color:#333; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.widgetservey_Red h4{font-size:26px; color:#ff0000; line-height:30px; border-bottom: 1px solid #cccccc;text-align:center; text-transform:uppercase; text-shadow: 0px 1px #4f4f4f;}
			.survey_nav{height:40px; background: #ffea51;
					background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/Pgo8c3ZnIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgd2lkdGg9IjEwMCUiIGhlaWdodD0iMTAwJSIgdmlld0JveD0iMCAwIDEgMSIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+CiAgPGxpbmVhckdyYWRpZW50IGlkPSJncmFkLXVjZ2ctZ2VuZXJhdGVkIiBncmFkaWVudFVuaXRzPSJ1c2VyU3BhY2VPblVzZSIgeDE9IjAlIiB5MT0iMCUiIHgyPSIwJSIgeTI9IjEwMCUiPgogICAgPHN0b3Agb2Zmc2V0PSIwJSIgc3RvcC1jb2xvcj0iI2ZmZWE1MSIgc3RvcC1vcGFjaXR5PSIxIi8+CiAgICA8c3RvcCBvZmZzZXQ9IjEwMCUiIHN0b3AtY29sb3I9IiNmZmE2MDAiIHN0b3Atb3BhY2l0eT0iMSIvPgogIDwvbGluZWFyR3JhZGllbnQ+CiAgPHJlY3QgeD0iMCIgeT0iMCIgd2lkdGg9IjEiIGhlaWdodD0iMSIgZmlsbD0idXJsKCNncmFkLXVjZ2ctZ2VuZXJhdGVkKSIgLz4KPC9zdmc+);
					background: -moz-linear-gradient(top,  #ffea51 0%, #ffa600 100%);
					background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ffea51), color-stop(100%,#ffa600));
					background: -webkit-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -o-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: -ms-linear-gradient(top,  #ffea51 0%,#ffa600 100%);
					background: linear-gradient(to bottom,  #ffea51 0%,#ffa600 100%);
					filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ffea51', endColorstr='#ffa600',GradientType=0 );
					}
			.survey_nav ul li{line-height:40px;}
			.survey_nav ul li a{color:#333; font-weight:bold; font-size:13px; padding:12px 5px;text-decoration:none;text-shadow:0px 1px #ffcc00; }
			.survey_nav ul li a:hover{background:rgba(255,0,0,0.1);}
			.survey_nav ul li a.selected{color:#fff; background:red;text-shadow:0px 1px #4f4f4f; }
			
			
		</style>
  </head>
  
  <body>
	<div class="container">
		<div class="row">
			<div class="span10 offset1 m_top20 survey_nav">
				<ul class="inline unstyled">
					<li><a href="#" class="selected"> User Types </a></li>
					<li><a href="#"> User Creation </a></li>
					<li><a href="#"> Tab Assign </a></li>
					<li><a href="#"> Booth Assign </a></li>
					<li><a href="#"> Deactivation </a></li>
					<li><a href="#"> Assign Leader</a></li>
					<li><a href="#"> Verification Details	 </a></li>
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red">
						<h4>Create user types</h4>
							<div class="row">
								<div class="span8 offset2">
									<label>User Type Description</label>
									<textarea id="userTypeDescription" class="input-block-level" rows="2"></textarea>
								</div>
							</div>
							<div class="row text-center m_top20"><button onClick="saveSurveyUserType();" type="button" class="btn btn-large btn-success">UPDATE</button></div>
					</div>
				</div>
			</div>
		</div>
	
		<!----User Creation----->		
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>User Creation</h4>
							<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										<div class="span4">
											<label>Firsr Name</label>
											<input type="text" id="firstName" placeholder="Firsr Name..." class="input-block-level">
										</div>
										<div class="span4">
											<label>Last Name</label>
											<input type="text" id="lastName" placeholder="Last Name..." class="input-block-level">
										</div>
										<div class="span4">
											<label>User Name</label>
											<input type="text" id= "userName" placeholder="User Name..." class="input-block-level">
										</div>
										
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label>Address</label>
											<textarea id="address" class="input-block-level" rows="2"></textarea>
										</div>										
									</div>
									<div class="row-fluid">
										<div class="span6">
											<label>Password</label>
											<input id="password" type="password" placeholder="Password..." class="input-block-level">
										</div>
										<div class="span6">
											<label>Retype Password</label>
											<input id="retypePassword" type="password" placeholder="Retype Password..." class="input-block-level">
										</div>
									</div>
									<div class="row-fluid">
										<div class="span6">
											<label>Mobile Number</label>
											<input id="mobileNumber" type="text" placeholder="Enter Number..." class="input-block-level">
										</div>
										<div class="span6">
											<label>User Type</label>
											<select id = "userType" class="input-block-level"> <option>01</option></select>
										</div>
									</div>
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success" onClick="saveSurveyUser();">SUBMIT</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!----TAB Assign ---->		
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>USer Tab Assign</h4>
							<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										<div class="span4">
											<label>User Name</label>
											<input type="text" placeholder="User Name..." class="input-block-level">
										</div>
										<div class="span2">
											<label>Tab No</label>
											<input type="text" placeholder="Tab No..." class="input-block-level">
										</div>
										<div class="span3">
											<label>Status</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
										<div class="span3">
											<label>Date</label>
											<input type="text" placeholder="User Name..." class="input-block-level">
										</div>										
									</div>
									<div class="row-fluid">
										<div class="span12">
											<label>Remarks</label>
											<textarea class="input-block-level" rows="2"></textarea>
										</div>
									</div>
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success">ASSIGN</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!---- Booth Assign---->		
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
							<h4>Assign Booth To User</h4>
								<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										<div class="span6">
											<label>Select User Name</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
										<div class="span6">
											<label>Select Constituency</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
									</div>	
									<div class="row-fluid">
										<div class="span6">
											<label>Panchayat</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
										<div class="span6">
											<label>Booths</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
									</div>
									</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success">ASSIGN</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!---- User Deactivation---->		
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Deactive User</h4>
							<div class="row">
								<div class="span8 offset2">
										<div class="row-fluid">
											<div class="span12">
												<label>Select User Name</label>
												<select class="input-block-level"> <option>01</option></select>
											</div>										
										</div>
									<div class="row-fluid">
										<div class="span12">
									<label>Remarks</label>
									<textarea class="input-block-level" rows="2"></textarea>
									</div>
									</div>
								</div>
							</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success">DEACTIVATE</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!---- Assign Booth To Leader ---->		
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid ">
					<div class="span12 widgetservey_Red m_top20">
						<h4>Assign Leaders To User</h4>
							<div class="row">
								<div class="span8 offset2">
									<div class="row-fluid">
										<div class="span6">
											<label>Select Constituency </label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
										<div class="span6">
											<label>Select Type</label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
									</div>	
									<div class="row-fluid">
										<div class="span6">
											<label>Select  Leader </label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
										<div class="span6">
											<label>Select User </label>
											<select class="input-block-level"> <option>01</option></select>
										</div>
									</div>
								</div>
									</div>
							<div class="row text-center m_top20"><button type="button" class="btn btn-large btn-success">ASSIGN</button></div>
					</div>
				</div>
			</div>
		</div>
		
		<!---- User Verification Details ---->		
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid">
					<div class="span12 widgetservey_Red m_top20">
						<h4>User Verification Details</h4>
							<div class="row">
								<div class="span8 offset2">
									<table class="table table-bordered">
									  <thead>
										<tr>										  
										  <th>CONSTITUENCY NAME</th>
										  <th>LEADER NAME</th>										  
										</tr>
									  </thead>
									  <tbody>										
										<tr>										  
										  <td>constituency Name</td>
										  <td><a href="#">Leader Name</a></td>
										</tr>										
										<tr>										  
										  <td>constituency Name</td>
										  <td><a href="#">Leader Name</a></td>
										</tr>
									  </tbody>
									</table>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		<!-----LEADER NAME------->
		<div class="row">
			<div class="span10 offset1">
				<div class="row-fluid">
					<div class="span12 widgetservey m_top20">
						<h4>LEADER NAME</h4>
							<div class="row">
								<div class="span8 offset2">
									<table class="table table-bordered">
									  <thead>
										<tr>										  
										  <th>USER NAMES</th>
										  <th>ASSIGned BOOTH NUMBERS</th>										  
										</tr>
									  </thead>
									  <tbody>										
										<tr>										  
										  <td>constituency Name</td>
										  <td>
											<a href="#">01, </a>
											<a href="#">02, </a>
											<a href="#">03, </a>
											<a href="#">04, </a>
										  </td>
										</tr>										
										<tr>										  
										  <td>constituency Name</td>
										  <td>
											<a href="#">01, </a>
											<a href="#">02, </a>
											<a href="#">03, </a>
											<a href="#">04, </a>
										  </td>
										</tr>
									  </tbody>
									</table>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
 <script>

function saveSurveyUser()
{
	var jsObj = 
	{
		firstName : $('#firstName').val(),
		lastName :  $('#lastName').val(),
		userName :  $('#userName').val(),
		password : $('#password').val(),
		address :  $('#address').val(),
		mobileNo :  $('#mobileNumber').val(),
		userType :  $('#userType').val(),
		task : "saveSurveyUser"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveSurveyUserAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
		
		});

}


function saveSurveyUserType()
{
	var jsObj = 
	{
		description : $('#userTypeDescription').val(),
		task : "saveSurveyUserType"
	}
	
	$.ajax({
		type:'GET',
		url: 'saveSurveyUserTypeAction.action',
		dataType: 'json',
		data: {task:JSON.stringify(jsObj)},
		}).done(function(result){
		
		});
}
function AssignTab()
{
	
	/*var uname = $trim($("#uname").val());
	var tabNo = $trim($("#tabNo").val());
	var remarks = $trim($("#remarks").val());
	var date = $("#date").val()*/
	var jObj = {
		uname : "test",
		tabNo :"1",
		remarks : "remarks",
		date : "7-1-2014"
			
	}
	$.ajax({
          type:'POST',
          url: 'assignTabAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jObj)},
     	  }).done(function(result){ 
			 
	   });
	
}
</script>

</body>
</html>