<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Community News Portal</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">	
	<!-- Custom Styles-->
    <link href="css/style.css" rel="stylesheet">
	<!-- CSS animation -->
    <link href="css/animate.css" rel="stylesheet">	
	<!-- icheck Css-->
	<link href="icheck/skins/all.css?v=1.0.2" rel="stylesheet">
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
<!-- iCheck -->
	
	
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

	<!-- YUI Dependency files (End) -->
	
	
    <script>$('#yourElement').addClass('animated fadeInDown');</script>
    <script>$('#fadeInLeft').addClass('animated fadeInLeft');</script>
    <script>$('#fadeInRight').addClass('animated fadeInRight');</script>
    <script>$('#fadeInUp').addClass('animated fadeInUp');</script>
    <script>$('#fadeInUp1').addClass('animated fadeInUp');</script>


	<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db}
	 
	 .datePickerCls{
			 cursor: text !important;
			}
	</style>
   	<script>
	
	$(document).ready(function(){
	    $('.datePickerCls').datepicker({dateFormat: 'dd-mm-yy',minDate: '01-01-1900',maxDate: new Date()});
	});

	var rolesSize = 1;
	function createNewForm()
	{
		var str = '';
		str += '<div class="row rolesList'+rolesSize+'">';
		str += '<div class="span3">';
		str += '<div class=" m_top20" >';
		str += '<h5 class="text-align1">OCCUPATION</h5>';
		str += '<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].designationLevelId">';
		str += '<option value = "1">kuppam</option>';
		str += '<option value = "2">Nellore</option>';
		str += '<option value = "3">Anantapur</option>';
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span3">';
		str += '<div class=" m_top20" >';
		str += '<h5 class="text-align1">OCCUPATION</h5>';
		str += '<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].designationLevelValue">';
		str += '<option value = "1">kuppam</option>';
		str += '<option value = "2">Nellore</option>';
		str += '<option value = "3">Anantapur</option>';
		str += '</select>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2">';
		str += '<div class=" m_top20" >';
		str += '<h5 class="text-align1">From Date</h5>';
		str += '<div class="input-prepend text-align2 ">';
		str += '<input type="text" class="form-control span2 border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].fromDateStr"></input></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2 ">';
		str += '<div class=" m_top20" >';
		str += '<h5 class="text-align1">To Date</h5>';
		str += '<div class="input-prepend  ">';
		str += '<input type="text" class="form-control span2  border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList['+rolesSize+'].toDateStr"></input></span>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		str += '<div class="span2">';
		str += '<a onClick="deleteRollesForm(\'rolesList'+rolesSize+'\')" class="btn btn-success"></a>';
		str += '<div>';
		str += '</div>';
		
		rolesSize++;
		alert(rolesSize);
		
		$('#rollesDiv').append(str);
		$('.datePickerCls').datepicker({dateFormat: 'dd-mm-yy',minDate: '01-01-1900',maxDate: new Date()});
	}
	
	function deleteRollesForm(id)
	{
		$('.'+id).remove();
	}
	
	function submitCadreForm()
	{
		var uploadHandler = {
				upload: function(o) {
					uploadResult = o.responseText;
					console.log(uploadResult);
					/*showUploadStatus(uploadResult);	
					 $("#cadreSaveBth").removeAttr('disabled'); */
				}
			};

		YAHOO.util.Connect.setForm('uploadCadreForm',true);
		YAHOO.util.Connect.asyncRequest('POST','tdpCadreRegistrationAction.action',uploadHandler);
	}
	</script>
	
</head>
  <body class="bgc">
	<div class="container m_top10 " id="yourElement">
		<div class="span12 show-grid" style="position: relative;">
			<h3 class="text-align">CADRE REGISTRATION</h3>
		</div>
	</div>
<div>
	<form action="tdpCadreRegistrationAction.action" method="POST" name="uploadCadreForm">	
		<div class="container m_top10"style="position: relative;">
		
			<div class="span12" >
				<div class="row-fluid">
					<div class="span6   show-grid"  id="fadeInLeft">
											<h5 class="text-align1">CANDIDATE NAME</h5>
											<input type="text" class="form-control border-radius-0 text-align2" placeholder="Text input" name="cadreRegistrationVO.voterName"  value="${voterInfoVOList[0].name}"></input>
									<div class="row-fluid">
										<div class="span7">
											<h5 class="text-align1">DATE OF BIRTH</h5>
												
												<div class="input-prepend text-align2 ">
													
													<input type="text" class="datePickerCls" name="cadreRegistrationVO.dobStr"></input>
													</div>
													
													<h5 class="text-align1">GENDER</h5>	
												<div class="row-fluid form-inline" style="margin-left:5px;">
												<s:if test="%{voterInfoVOList[0].gender != null}">
												
													<c:if test="${voterInfoVOList[0].gender == 'M'}">
													   <label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" checked="true" >MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender">FEMALE</input></label>
													</c:if>
													<c:if test="${voterInfoVOList[0].gender == 'F'}">												
														<label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" >MALE</input></label>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender"  checked="true">FEMALE</input></label>
													</c:if>
												
												</s:if>
												<s:else>
												  <label class="radio"><input type="radio" value="MALE"  name="cadreRegistrationVO.gender" >MALE</input></label>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<label class="radio"><input type="radio" value="FEMALE"  name="cadreRegistrationVO.gender" >FEMALE</input></label>
												</s:else>
														
												</div>			
										</div>
								
								
										<div class="span4  m_top10">
											<div class="well  pad-5">
												<img src="user.jpg" class="img-responsive" />
											<button class="btn btn-primary btn-xs btn-block border-radius-0 m_top10 " type="button" >Upload Photo </button>
											</div>
										</div>
								
									</div>
										<h5 class="text-align1">GURIDIAN NAME</h5>
										<input type="text" class="form-control border-radius-0 text-align2" placeholder="Text input" name="cadreRegistrationVO.relativeName"   value="${voterInfoVOList[0].relativeName}"></input>
								<div class="m_top10">
										<div class="row-fluid">
										
											<div class="span6">
											<h5 class="text-align1">VOTER ID</h5>
												<input type="text" class="form-control border-radius-0 text-align2" placeholder="Voter Id" name="cadreRegistrationVO.voterCardNumber"   value="${voterInfoVOList[0].voterCardNo}"></input>
											</div>
											
											<div class="span6">
											<h5 class="text-align1">H NO</h5>
												<input type="text" class="form-control border-radius-0 " placeholder="House Number" name="cadreRegistrationVO.houseNo"   value="${voterInfoVOList[0].houseNo}"></input>
											</div>
										</div>
								</div>	
								
								<div class="m_top10">
										<div class="row-fluid">
										
											<div class="span6">
											<h5 class="text-align1">PARTY MEMBER SINCES</h5>
												<input type="text" class="form-control border-radius-0 text-align2 datePickerCls" placeholder="" name="cadreRegistrationVO.partyMemberSinceStr"  value="${voterInfoVOList[0].activeDate}"></input>
											</div>
											
											<div class="span6">
											<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="bloodgroupId" list="voterInfoVOList[0].selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Blood Group" style="width:220px;" name="cadreRegistrationVO.bloodGroupId"  value="%{voterInfoVOList[0].blodGroupId}"/>
											
											</div>
										</div>
								</div>	
								<!--<div class="m_top10">
									
											<h5 class="text-align1">REFERED BY</h5>
												<input type="text" class="form-control border-radius-0 text-align1" placeholder="">
							
								</div>-->
						
					</div>
					<div class="span6 show-grid pad-10b" id="fadeInRight" style="height:643px; " >
					
								<div class=" m_top20" >
										<h5 class="text-align1">STREET/HAMLET</h5>
										<input type="text" class="form-control border-radius-0  input-block-level" placeholder="Text input" name="cadreRegistrationVO.street"></input>
								</div>	
							<div class=" m_top20" >
										<h5 class="text-align1">CASTE NAME</h5>
									<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="casteId" list="voterInfoVOList[0].genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Caste" style="width:220px;" name="cadreRegistrationVO.casteId" value="%{voterInfoVOList[0].casteName}" />
									
							</div>
							<div class=" m_top20" >
										<h5 class="text-align1">MOBILE NUMBER</h5>
										<input type="text" class="form-control border-radius-0 input-block-level" placeholder="Text input"  name="cadreRegistrationVO.mobileNumber"></input>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">EDUCATION</h5>
								<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="educationId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Education " style="width:220px;" name="cadreRegistrationVO.educationId" value="%{voterInfoVOList[0].education}"/>
							</div>
							<div class=" m_top20" >
								<h5 class="text-align1">OCCUPATION</h5>
							<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="occupationId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Occupation " style="width:220px;" name="cadreRegistrationVO.occupationId" value="%{voterInfoVOList[0].occupation}"/>
							
							</div>
							<div class=" m_top20" >
									<h5 class="text-align1">PREVIOUS ENROLLMENT NUMBER</h5>
									<input type="text" class="form-control border-radius-0 input-block-level" placeholder="Text input" name="cadreRegistrationVO.previousEnrollmentNumber"></input>
							</div>
						
					</div>
				</div>
		
		</div>
	</div>
	<div id="fadeInUp">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUS ROLLS PARTICIPATED IN PARTIES</h3>
			</div>
		</div>
		<div class="container m_top10">
			
			<div class="span12 show-grid" style="position: relative;" id="rollesDiv">	
				<div class="row">
					<a class="btn btn-success" style="float:right" onClick="createNewForm();">Add More</a>
				</div>
				<div class="row rolesList">
					<div class="span3">
							<div class=" m_top20" >
								<h5 class="text-align1"> Select Level </h5>
									<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousRollesList[0].designationLevelId">
									    <option value = "1">kuppam</option>
										<option value = "2">Nellore</option>
										<option value = "3">Anantapur</option>
									 </select>
							</div>
					</div>
					<div class="span3">
						<div class=" m_top20" >
							<h5 class="text-align1">Party Designation </h5>
								<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousRollesList[0].designationLevelValue">
								  <option value = "1">kuppam</option>
								  <option value = "2">Nellore</option>
								  <option value = "3">Anantapur</option>
								  </select>
						</div>
		
					</div>
		
					<div class="span2">
						<div class=" m_top20" >
							<h5 class="text-align1">From Date</h5>
								<div class="input-prepend text-align2 ">
													
									<input type="text" class="form-control span2 border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList[0].fromDateStr"></input></span>
								</div>
						</div>
					</div>
					<div class="span2 ">
						<div class=" m_top20" >
							<h5 class="text-align1">To Date</h5>
								<div class="input-prepend  ">
													
									<input type="text" class="form-control span2  border-radius-0 border-right-0 datePickerCls" name="cadreRegistrationVO.previousRollesList[0].toDateStr"></input></span>
								</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	</div>
	<div id="fadeInUp1">
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<h3 class="text-align ">PREVIOUSLY PARTICIPATED IN ELECTION</h3>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<div class="row-fluid">
					<div class="span4">
						<div class=" m_top20" >
							<h5 class="text-align1">Election Type</h5>
							<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionTypeId">
							  <option value ="1">kuppam</option>
							  <option value = "2">Nellore</option>
							  <option value = "3">Anantapur</option>
							 </select>
						</div>
					</div>
					<div class="span4">
						<div class=" m_top20" >
							<h5 class="text-align1">Year</h5>
							<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionYear">
							  <option value ="2006">2009</option>
							  <option value = "2">Nellore</option>
							  <option value = "3">Anantapur</option>
							  </select>
						</div>
					</div>
					<div class="span4">
						<div class=" m_top20" >
							<h5 class="text-align1">Constituency</h5>
							<select class="form-control border-radius-0 text-align1" name="cadreRegistrationVO.previousParicaptedElectionsList[0].constituencyId">
							  <option value ="1">kuppam</option>
							  <option value = "2">Nellore</option>
							  <option value = "3">Anantapur</option>
							  </select>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container m_top10">
			<div class="span12 show-grid" style="position: relative;">
				<a class="btn btn-primary m_top20 border-radius-0 text-align2" href="search-constituency.html"><span class="icon-chevron-left icon-white"></span>&nbsp;&nbsp;Back </a>
				<a  onClick="submitCadreForm();" class="btn btn-success text-align3 m_top20 pull-right border-radius-0"> &nbsp;&nbsp;Next<span class=" icon-chevron-right icon-white"></span></a>
			</div>
		</div>
	</div>
</form>
</div>
</body>
</html>