<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Committee Management</title>

   
    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/bootstrap.min.js"></script>
	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommittee/cadreCommittee.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

	 
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
		
	<style>
		.requiredFields{color:red;}
		.form-control[disabled] {color:#333 !important;background-color: rgba(0, 0, 0, 0.1) !important;border: medium none rgba(0, 0, 0, 0) !important;
		}	
		#assignCommitteeDiv{
			display:none !important;
		}
	</style>
	
	<script>
	
	function prepopulateOptions(){
	
		
	if(participationCount >0)
	{
		$('#rolesDiv').hide();
	}
	if(isRolesCount >0)
	{
		$('#electionsDiv').hide();
	}
	$('#genderDetailsId').val('${cadreCommitteeVO.gender}');
		var jsObj ={}				   
			$.ajax({
				type : "POST",
				url : "getCadreLevelsForCadreSearchAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				if(result.length>0){
					buildSelectBoxes(result);
				}
			});
			
			
	}
	
	var cadreCmmittArr = [];
	var cadreCmmittLvlArr = [];
	var cadreRolesArr = [];
	
	function buildSelectBoxes(results){
		
		
		for(var i in results){
		
			if(results[i].name=="CadreCommitteeList"){
				var str = "";					
					for(var j in results[i].selectOptionsList){						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}							
						cadreCmmittArr.push(cadreCmmtObj);
					}
			}
			if(results[i].name=="CadreCommitteeLevelsList"){
				var str = "";
					for(var j in results[i].selectOptionsList){						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}
							
						cadreCmmittLvlArr.push(cadreCmmtObj);
					}
			}
			if(results[i].name=="CadreRolesList"){
				var str = "";
					for(var j in results[i].selectOptionsList)
					{						
						var cadreCmmtObj = {
								id		:	results[i].selectOptionsList[j].id,
								name	:	results[i].selectOptionsList[j].name								
							}
							
						cadreRolesArr.push(cadreCmmtObj);
					}
			}
			
			
		}
	}
	
	var participationCount = 0;
	var isRolesCount = 0 ;
	
	<s:if test="%{cadreCommitteeVO.previousElections != null && cadreCommitteeVO.previousElections.size() > 0}">
			<c:forEach var="electionVO" items="${cadreCommitteeVO.previousElections}" varStatus="commentLoop">
			participationCount = participationCount + 1;
		</c:forEach>
	</s:if>
  
	
	<s:if test="%{cadreCommitteeVO.previousRoles != null && cadreCommitteeVO.previousRoles.size() > 0}">
		<c:forEach var="rolesVO" items="${cadreCommitteeVO.previousRoles}" varStatus="indexValue">
			isRolesCount = isRolesCount+1;
		</c:forEach>
	</s:if>	
		
	var electionArray = new Array();	
	<c:forEach var="election" items="${eletionTypesList}">
		var elections ={
		id:"${election.id}",
		name:"${election.name}"
		}
		electionArray.push(elections);
	</c:forEach>
			
	</script>
  </head>
  <body>
  
	<div class="container-fluid">
		<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-12 col-sm-12 col-xs-12 text-center">
				<img src="Images/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
		</div>
		<div id="profileDiv">
		<div class="row m_top20">
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 text-center" style="border-bottom:1px solid #FD2A34;">
				<button href="#" class="btn btn-xs btn-warning pull-right" id="editFieldsId">EDIT</button>
				<img src="http://www.mytdp.com/images/cadre_images/${cadreCommitteeVO.imageURL}"  class="img-circle" style="border:4px solid rgb(255, 231, 0);"/>
				<h3>${cadreCommitteeVO.cadreName}</h3>
				<h4>Cadre Number : ${cadreCommitteeVO.memberShipCardId}</h4>				
			</div>			
		</div> 
		
		<form action="tdpCadreSaveRegistrationAction.action" method="POST" enctype="multipart/form-data" name="uploadCadreForm">
			<input type="hidden" value="${cadreCommitteeVO.voterId}" name="cadreRegistrationVO.voterId" >
				<input type="hidden" value="${cadreCommitteeVO.tdpCadreId}" name="cadreRegistrationVO.tdpCadreId" >
		<div class="row m_top20">
				<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-md-4 col-sm-4 col-xs-4 form-group"> <!--  value="${cadreCommitteeVO.DOB}"-->
							<input type="text" class="form-control editClass datesCls" id="dateOfBirth" value="${cadreCommitteeVO.DOB}" readOnly="true" style="cursor:text;" name="cadreRegistrationVO.dobStr" disabled>
						</div>
						<div class="col-md-4  col-sm-4 col-xs-4 form-group">
							<input type="number" id="ageId" class="form-control  editClass" value="${cadreCommitteeVO.age}" placeholder="Age: 33" name="cadreRegistrationVO.age" disabled>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 form-group">
							<select id="genderDetailsId" class="form-control  editClass"  name="cadreRegistrationVO.gender" disabled> 
							<option value="0"> Select Gender </option>
								<option value="M"> Male </option>
								<option value="F"> Female </option>
							</select>
						</div>
					</div>
					<div class="row">
						<div id="dateOfBirthErrorDiv" class="col-md-4 col-sm-4 col-xs-4 form-group requiredFields">
						</div>
						<div id="ageIdErrorDiv" class="col-md-4 col-sm-4 col-xs-4 form-group requiredFields">
						</div>
						<div id="genderIdErrorDiv" class="col-md-4 col-sm-4 col-xs-4 form-group requiredFields">
						</div>
					</div>
				</div>
				
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<input type="text" id="voterCardNoId" class="form-control  editClass"  value="${cadreCommitteeVO.voterCardNo}" placeholder="Voter ID:96325874" name="cadreRegistrationVO.voterCardNumber" disabled>				
				</div>
				<div class="col-md-4   col-sm-6 col-xs-6 form-group">
					<input type="text" id="adhaarNoId" class="form-control  editClass"  value="${cadreCommitteeVO.adhaarNo}" placeholder="Aadhar No:258963147"  name="cadreRegistrationVO.candidateAadherNo" disabled>
				</div>
				<div class="row">
					<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group requiredFields" id="voterCardNoIdError" style="left:11px;">
					</div>
					<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group requiredFields" id="adhaarNoIdError" style="right:228px;">
					</div>
				</div>
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<input type="number" id="mobileNoId" class="form-control editClass mobileNumber"  value="${cadreCommitteeVO.mobileNo}" placeholder="Mobile No:9632587410" name="cadreRegistrationVO.mobileNumber" disabled>
				</div>
								
				<div class="col-md-4   col-sm-6 col-xs-6 form-group">
					<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level editClass" id="casteId" list="cadreCommitteeVO.casteList" listKey="casteStateId" listValue="casteName" headerKey="0" headerValue=" Select Caste " style="width:420px;height:35px;" name="cadreRegistrationVO.casteId"   value="%{cadreCommitteeVO.casteStateId}" disabled="true"/>	
				</div>
				<div class="row">
					<div class="col-md-4   col-sm-6 col-xs-6 form-group requiredFields" id="mobileNOErrorId" style="left:240px;" >
					</div>
					<div class="col-md-4   col-sm-6 col-xs-6 form-group requiredFields" id="casteIdErrorDiv" style="left:232px;">
					</div>
				</div>
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
					<input type="text" id="addressId" class="form-control editClass"  value="${cadreCommitteeVO.address}" placeholder="Address: "  name="cadreRegistrationVO.street" disabled>
				</div>
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group requiredFields" id="addressIdError"></div>
				
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level editClass" id="educationId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Education " style="width:420px;height:35px;" name="cadreRegistrationVO.educationId" value="%{cadreCommitteeVO.educationId}" disabled="true"/>
				</div>
				<div class="col-md-4 col-sm-6 col-xs-6 form-group">
					<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level editClass" id="occupationId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Occupation " style="width:420px;height:35px;" name="cadreRegistrationVO.occupationId" value="%{cadreCommitteeVO.occupationId}" disabled="true"/>
				</div>
				<div class="row">
					<div class="col-md-4 col-sm-6 col-xs-6 form-group requiredFields" id="educationIdError" style="left:240px;"></div>
					<div class="col-md-4 col-sm-6 col-xs-6 form-group requiredFields" id="occupationIdError" style="left:232px;"></div>
				</div>
				
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
					<input type="text" id="emailIdDiv" class="form-control  editClass" value="${cadreCommitteeVO.emailId}"  placeholder="E-Mail ID: " name="cadreRegistrationVO.emailId" disabled>
				</div>
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group requiredFields" id="emailIdDivError"></div>
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group" > 
					<!--<h4> PREVIOUSLY ENROLLED  ? </h4>
					<input type="text" id="preEnrollNoValue" class="form-control border-radius-0 input-block-level" placeholder="Previous Enrollment No."  value="${voterInfoVOList[0].memberShipId}" style="width:260px;"  onkeyup="getExistingCadreInfo2();" name="cadreRegistrationVO.previousEnrollmentNumber" readonly></input>&nbsp;<span onclick="clearPreviousEnrol();" title="Click Here To Clear Previous Enrollment Number" style="cursor: pointer;" class="icon-remove"></span>
					<a id="searchByNameId" class="btn btn-success" href="javascript:{enableSearchByName();}" > LookUp For EnrollmentNo</a>
					<input type="hidden" id="preEnrollNo" class="form-control border-radius-0 input-block-level" placeholder="Text input"  value="${voterInfoVOList[0].memberShipId}" style="width:260px;" ></input>-->
					<div style="border-bottom:1px solid #FD2A34;margin-bottom:20px;" class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 text-center">
						<h4>PREVIOUSLY ENROLLED  ?</h4>				
					</div>
					<div class="col-md-4 col-sm-6 col-xs-6 form-group">
						<input type="text" readonly="" name="cadreRegistrationVO.previousEnrollmentNumber" onkeyup="getExistingCadreInfo2();" style="width:260px;" value="" placeholder="Previous Enrollment No." class="form-control border-radius-0 input-block-level" id="preEnrollNoValue">
					</div>

					<div class="col-md-1 col-sm-1 col-xs-1 form-group">
					  <!--<button class="btn btn-success" onclick="clearPreviousEnrol();" type="button"><span aria-hidden="true" class="glyphicon glyphicon-remove"></span></button>-->
					  <img src="images/close.png" onclick="clearPreviousEnrol();" style="height:25px; width:25px; margin-left:-53px;margin-top:-3px;">
					</div>

					<div class="col-md-3 col-sm-5 col-xs-5 form-group">
						<a href="javascript:{enableSearchByName();}" class="btn btn-success" id="searchByNameId"> LookUp For EnrollmentNo</a>
					</div>

					<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
						<input id="preEnrollNo" class="form-control border-radius-0 input-block-level" type="hidden" style="width:260px;" value="" placeholder="Text input">
					</div>
				</div>
		</div>
		
		<div class="row m_top20">
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 text-center" style="border-bottom:1px solid #FD2A34;margin-bottom:20px;">
				<h4>PREVIOUS ROLES IN PARTY</h4>				
			</div>			
		
			<s:if test="%{cadreCommitteeVO.previousRoles != null && cadreCommitteeVO.previousRoles.size() > 0}">
			<c:forEach var="rolesVO" items="${cadreCommitteeVO.previousRoles}" varStatus="indexValue">
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
					<input type="text" id="disabledInput" class="form-control" placeholder=" " value="${rolesVO.role} [ ${rolesVO.fromDate}  - ${rolesVO.toDate}  ] " disabled>
			</div>
			<div class="hiddenDivCls" style="display:none;">
				<input type="hidden" value="${rolesVO.committeeLevelId}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].cadreCommitteeLevelId"/>				
				<input type="hidden" value="${rolesVO.committeeId}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].cadreCommitteeId"/>				
				<input type="hidden" value="${rolesVO.roleId}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].cadreRoleId"/>				
				<input type="hidden" value="${rolesVO.fromDate}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].fromDateStr"/>				
				<input type="hidden" value="${rolesVO.toDate}" name="cadreRegistrationVO.previousRollesList[${indexValue.index}].toDateStr"/>				
			</div>
			</c:forEach>
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" id="rolesDiv">
				</div>				
				</div>
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group ">
				<a href="javascript:{addMoreRoles();}" class="btn btn-danger btn-xs ">Tap to Add+ Details</a>	
			</div> 
		</s:if>
		<s:else>	
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group" id="addMoreRolesDiv">
					<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" id="rolesDiv">
						<div class="row">
						  <div class="form-group col-md-6 col-sm-6 col-xs-6 ">
							<label for="exampleInputEmail2" >Committee Level</label>
							<select class="form-control " id="cadreCommitteeLevelsId" onchange="getCadreCommitteDetails(this.value,'cadreCommitteeId')" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeLevelId">
							<c:forEach var="educationList" items="${cadreRolesVOList[1].selectOptionsList}" >		
										<c:if test="${educationList.id == role.id }">																
											<option value="${educationList.id}" selected="selected">${educationList.name}</option>
										</c:if>	
										<c:if test="${educationList.id != role.id }">																
											<option value="${educationList.id}">${educationList.name}</option>
										</c:if>	
								</c:forEach>	
							
							</select>
						  </div>
						  <div class="form-group col-md-6 col-sm-6 col-xs-6">
							<label >Committee Name</label>
							<select class="form-control " id="cadreCommitteeId" onchange="getCadreCommitteRoles(this.value,'cadreRolesId','cadreCommitteeLevelsId')" name="cadreRegistrationVO.previousRollesList[0].cadreCommitteeId">
							<option value="0"> Select Committee </option>
							</select>
						  </div>					  
						</div>
						
						<div class="row">
						  <div class="form-group col-md-4 col-sm-4 col-xs-4 ">
							<label for="exampleInputEmail2" >Committee Role</label>
							<select class="form-control " id="cadreRolesId" name="cadreRegistrationVO.previousRollesList[0].cadreRoleId">
							<option value="0"> Select Role </option>
							</select>
						  </div>
						  <div class="form-group col-md-4 col-sm-4 col-xs-4">
							<label >From Date</label>
							<input type="text" class="form-control datesCls" name="cadreRegistrationVO.previousRollesList[0].fromDateStr">
						  </div>
						  <div class="form-group col-md-4 col-sm-4 col-xs-4">
							<label >To Date</label>
							<input type="text" class="form-control datesCls" name="cadreRegistrationVO.previousRollesList[0].toDateStr">
						  </div>					  
						</div>
									
					</div>
					<a href="javascript:{addMoreRoles();}" class="btn btn-danger btn-xs ">Tap to Add+ Details</a>	
				</div>
			
		
		</s:else>
		</div> 
		<div class="row m_top20">
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 text-center" style="border-bottom:1px solid #FD2A34;margin-bottom:20px;">
				<h4>PREVIOUSLY PARTICIPATED IN ELECTION</h4>				
			</div>			
			
			<s:if test="%{cadreCommitteeVO.previousElections != null && cadreCommitteeVO.previousElections.size() > 0}">
				<c:forEach var="electionVO" items="${cadreCommitteeVO.previousElections}" varStatus="indexValue">
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
						<input type="text" id="disabledInput" class="form-control" placeholder="" value="${electionVO.constituency} - ${electionVO.electionType} - ${electionVO.electionYear}" disabled>
						
				</div>	
				
			<div class="hiddenDivCls" style="display:none;">
				<input type="hidden" value="${electionVO.electioinYearId}" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].electionTypeId"/>				
				<input type="hidden" value="${electionVO.constituencyId}" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].constituencyId"/>
				<input type="hidden" value="0" name="cadreRegistrationVO.previousParicaptedElectionsList[${indexValue.index}].candidateId"/>				
							
			</div>
			
				</c:forEach>
				
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" id="electionsDiv">
				</div>				
				</div>
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group ">
					<a href="javascript:{addMoreElectionDetails();}" class="btn btn-danger btn-xs ">Tap to Add+ Details</a>	
				</div>
			</s:if>
			
			<s:else>
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group  m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"  id="electionsDiv">
					<div class="row">
					  <div class="form-group col-md-4 col-sm-4 col-xs-4 ">
						<label for="exampleInputEmail2" >Election Type</label>
						<select class="form-control " onChange="getElectionYears(this.value,'electionYearId')" id="electionsTypeID">
						<option value="0"> Select Election Type</option>
								<c:forEach var="educationList" items="${eletionTypesList}" >															
									<c:if test="${educationList.id == role.id }">																
										<option value="${educationList.id}" selected="selected">${educationList.name}</option>
									</c:if>	
									<c:if test="${educationList.id != role.id }">																
										<option value="${educationList.id}">${educationList.name}</option>
									</c:if>	
								</c:forEach>
							</select>
						</select>
						
					  </div>
					   <div class="form-group col-md-4 col-sm-4 col-xs-4">
						<label >Election Year</label> <img src='images/icons/search.gif' id="loadingImg" style="display:none;margin-left:5px;"/>
						<select class="form-control " id="electionYearId" onchange="getConstiteuncyListForElection(this.value,'constituencyList')" name="cadreRegistrationVO.previousParicaptedElectionsList[0].electionTypeId">
						<option value="0"> Select Election </option>
						</select>
					  </div>	
					  <div class="form-group col-md-4 col-sm-4 col-xs-4">
						<label > Location </label>
						<select class="form-control " id="constituencyList" name="cadreRegistrationVO.previousParicaptedElectionsList[0].constituencyId">
						<option value="0"> Select Location </option>
						</select>
					  </div>
					 				  
					</div>
				</div><!-- add more details electionsDiv Ending -->	
					<a href="javascript:{addMoreElectionDetails();}" class="btn btn-danger btn-xs ">Tap to Add+ Details</a>	
			</div>
			</s:else>
			
			<div class="col-md-10 col-md-offset-2  col-sm-12 col-xs-12 m_top20 text-center generateotpdiv">
				<div  class="row">		
					<div class="referencediv"></div>
					<div class="col-md-2 col-md-offset-0  col-sm-3 col-xs-3">
						<button class="btn btn-primary" type="button" onclick="generateOTP();">Generate OTP</button>
					</div>
					<div class="col-md-2 col-md-offset-0  col-sm-4 col-xs-4">
							<input type="text" class="form-control otptextbox" placeholder="ENTER OTP NUMBER" >						
					</div>
					<div class="col-md-2 col-md-offset-0  col-sm-3 col-xs-3">
						<button class="btn btn-success" type="button" onclick="checkOTPValid();">Validate OTP</button>
						<img id="right" style="width:35px; height:32px; margin-left:5px;" src="images/right.jpg">
						<img id="wrong" style="width:35px; height:32px; margin-right:5px;" src="images/wrong.jpg">
					</div>	
				</div>
			</div>
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 m_top20 text-center updateProfileDivId">
				<button class="btn btn-success btn-block btn-lg m_top20" id="submitCadreFormBtnReqId" type="button" onClick="submitCadreForm();">UPDATE PROFILE  &  ADD to MANDAL Affliated Electoral</button>
			</div>			
		</div> 
	</form>
	</div>
	<div id="assignCommitteeDiv" >
	<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center m_top20 alert alert-success">
				<b>PROFILE UPDATED SUCCESSFULLY..</b>
			</div>	
    		
			<div class="row " >
				<div class="col-md-3 col-md-offset-3  col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0 m_top20" >
					<div class="form-group">
						<label for="exampleInputEmail1">SELECT LOCATION</label>
						<select  class="form-control"><option>Location Name #1</option></select >
					 </div>
				</div>
				<div class="col-md-3  col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0 m_top20">
					<div class="form-group">
						<label for="exampleInputEmail1">COMMITTEE TYPE</label>
						<select class="form-control"><option>Main Committee</option><option>Affiliated Committee</option></select >
					 </div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 col-md-offset-3  col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0 ">
					<label for="exampleInputEmail1">AFFILIATED COMMITTEE NAME</label>
					<select  class="form-control"><option>COMMITTEE NAME</option></select >
				 </div>
		
				<div class="col-md-3   col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0">
					<label for="exampleInputEmail1">COMMITTEE POSITION</label>
					<select  class="form-control"><option>POSITION Name #1</option></select >
				</div>
			</div>
		
			<div class="row">
				<div class="col-md-6 col-md-offset-3  col-sm-6 col-sm-offset-3   col-xs-6 col-xs-offset-3 m_top20 ">
					<button class="btn btn-success btn-block btn-lg m_top20">SUBMIT</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="myModal1" style="display:none;">
					<div style="border:1px solid lightgray;">
						<div class="pad-10b">
								<h5 class="text-align1">CANDIDATE NAME</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="candiNameId" name="searchName1" style="width:425px;" onkeyUp="getExistingCadreInfo1();">
						</div>
						<div class=" m_top10 pad-10b">
							<div class="row-fluid">
								<h5 class="text-align1">PREVIOUS ENROLLMENT NUMBER</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Enrollment Number"  id="enrollmentNoId"  name="searchVoterCard" onkeyUp="getExistingCadreInfo1();">
							</div>
						</div>
						
						<a href="javascript:{getExistingCadreInfo1();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
						<div align="center"><img style="width:70px;height:60px;display:none;" id="searchDataImg1" class="" src="images/Loading-data.gif"></div>
					</div>
					<div id="errorDiv1"  class="mandatory"></div>
					<div class="show-grid pad-5 m-bottom-10">
						<div class="container" id="tableElement1" style="margin-top:25px;display:none;">
							<h3 class="text-align" style="color:red;">SEARCH DETAILS</h3>
							<div class="table-responsive" id="searchDetailsDiv1" ></div>
						</div>
					</div>
				</div>
				
	<footer class="text-center m_top20">
			&copy; 2015
	</footer>
	
   
	<script>		
		var mobnum,refid;
		$('document').ready(function(){
			 $('.datesCls').datepicker({
				dateFormat: 'yy-mm-dd',
				maxDate: new Date(),
				changeMonth: true,
				changeYear: true,
				yearRange: "-100:+0"
			  });
			 
			 $('#editFieldsId').click(function(){
			 $('.editClass').removeProp('disabled');
			 
			 });
			 
			 prepopulateOptions();
			 
			 $('.generateotpdiv').hide();
			 $('#right').hide();
			 $('#wrong').hide();
			 mobnum = $(".mobileNumber").val();
		});	
		
		$("#casteId").change(function(){
			$('.generateotpdiv').show();
			$('.updateProfileDivId').hide();			
		});
			
		$(".mobileNumber").blur(function(){
			if(mobnum!=$(".mobileNumber").val()){
				$('.generateotpdiv').show();
				$('.updateProfileDivId').hide();
			}
		});
			
		function generateOTP(){
			var mobileNo=mobnum;
			var jsObj =	{
				mobileNo : mobileNo
			}
		
			$.ajax(
			  {
					type: "POST",
					url:"generateOTPForEditAction.action",
					data:{task :JSON.stringify(jsObj)}
			  }
			  ).done(function(result){
					if(result!=null){
						refid=result;
						var str='';
							str+='<span style="margin-left:-560px; color:red"><b>Reference Id : '+result+'</b></span>';
							$(".referencediv").html(str);
					}
			  });
		}
		
		function checkOTPValid(){
			var mobileNo=$(".mobileNumber").val();
			var otpNo=$(".otptextbox").val();
			var jsObj =	{
				mobileNo : mobileNo,
				refNo : refid,
				otpNo : otpNo
			}
		
			$.ajax(
			  {
					type: "POST",
					url:"checkValidOTPAction.action",
					data:{task :JSON.stringify(jsObj)}
			  }
			  ).done(function(result){
					if(result=="success"){
						$('#right').show();
						$('#wrong').hide();
						$('.updateProfileDivId').show();
					}
					else{
						$('#right').hide();
						$('#wrong').show();
						$('.updateProfileDivId').hide();
					}
			  });
		}
		
	var electionsCount = 1;
	var isElectionsSet = true;
	function addMoreElectionDetails()
	{
		$('#electionsDiv').show();
		if(participationCount >0 && isElectionsSet)
		{
			electionsCount = participationCount;
			isElectionsSet = false;
		}
		
		var str='';
		str+='<div class="row" id="detailsId'+electionsCount+'">';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4 ">';
		str+='<label for="exampleInputEmail2" >Election Type</label>';
		str+='<select class="form-control " onChange="getElectionYears(this.value,\'electionYearId'+electionsCount+'\')" id="electionsTypeID">';
		str+='<option value="0"> Select Election Type</option>';
		if(electionArray != null && electionArray.length>0)
		{
			for(var i in electionArray)
				str+='<option value="'+electionArray[i].id+'">'+electionArray[i].name+'</option>';
		}
		str+='</select>';
		str+='</div>';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4">';
		str+='<label >Election Year</label> ';
		str+='<select class="form-control " id="electionYearId'+electionsCount+'" onchange="getConstiteuncyListForElection(this.value,\'constituencyList'+electionsCount+'\')" name="cadreRegistrationVO.previousParicaptedElectionsList['+electionsCount+'].electionTypeId">';
		str+='<option value="0"> Select Election </option>';
		str+='</select>';
		str+='</div>	';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4">';
		str+='<label > Location </label>';
		str+='<select class="form-control " id="constituencyList'+electionsCount+'" name="cadreRegistrationVO.previousParicaptedElectionsList['+electionsCount+'].constituencyId">';
		str+='<option value="0"> Select Location </option>';
		str+='</select>';
		str+='<input type="hidden" value="0" name="cadreRegistrationVO.previousParicaptedElectionsList['+electionsCount+'].candidateId"/>';
		str+='</div>';
		str+='<a href="javascript:{clearDiv(\'detailsId'+electionsCount+'\');}" class="btn btn-danger btn-xs " style="margin-left: 17px;"> Remove </a>	';
		$('#electionsDiv').append(str);
		
		electionsCount = electionsCount+1;
	}	

	var isRoleSet = true;
	var rolesCount = 1 ;
	function addMoreRoles()
		{
		$('#rolesDiv').show();
		if(isRolesCount >0 && isRoleSet)
		{
			rolesCount = isRolesCount;
			isRoleSet = false;
		}
		var str='';
		str+='<span id="roleDiv'+rolesCount+'">';
		str+='<div class="row" >';
		str+='<div class="form-group col-md-6 col-sm-6 col-xs-6 ">';
		str+='<label for="exampleInputEmail2" >Committee Level</label>';
		str+='<select class="form-control " id="cadreCommitteeLevelsId'+rolesCount+'" onchange="getCadreCommitteDetails(this.value,\'cadreCommitteeId'+rolesCount+'\')" name="cadreRegistrationVO.previousRollesList['+rolesCount+'].cadreCommitteeLevelId">';
		if(cadreCmmittLvlArr != null && cadreCmmittLvlArr.length>0)
		{
			for(var i in cadreCmmittLvlArr)
			{
				str+='<option value="'+cadreCmmittLvlArr[i].id+'">'+cadreCmmittLvlArr[i].name+'</option>';
			}
		}
		str+='</select>';
		str+='</div>';
		str+='<div class="form-group col-md-6 col-sm-6 col-xs-6">';
		str+='<label >Committee Name</label>';
		str+='<select class="form-control " id="cadreCommitteeId'+rolesCount+'" onchange="getCadreCommitteRoles(this.value,\'cadreRolesId'+rolesCount+'\',\'cadreCommitteeLevelsId'+rolesCount+'\')" name="cadreRegistrationVO.previousRollesList['+rolesCount+'].cadreCommitteeId">';
		str+='<option value="0"> Select Committee </option>';
		str+='</select>';
		str+='</div>	';				  
		str+='</div>';

		str+='<div class="row">';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4 ">';
		str+='<label for="exampleInputEmail2" >Committee Role</label>';
		str+='<select class="form-control " id="cadreRolesId'+rolesCount+'" name="cadreRegistrationVO.previousRollesList['+rolesCount+'].cadreRoleId">';
		str+='<option value="0"> Select Role </option>';
		str+='</select>';
		str+='</div>';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4">';
		str+='<label >From Date</label>';
		str+='<input type="text" class="form-control datesCls" name="cadreRegistrationVO.previousRollesList['+rolesCount+'].fromDateStr">';
		str+='</div>';
		str+='<div class="form-group col-md-4 col-sm-4 col-xs-4">';
		str+='<label >To Date</label>';
		str+='<input type="text" class="form-control datesCls" name="cadreRegistrationVO.previousRollesList['+rolesCount+'].toDateStr">';
		str+='</div>	';				  
		str+='</div>';
		str+='<a href="javascript:{clearDiv(\'roleDiv'+rolesCount+'\');}" class="btn btn-danger btn-xs " style="margin-left: 17px;"> Remove </a>	';
		str+='</span>';
		$('#rolesDiv').append(str);
		
		rolesCount = rolesCount+1;
		 $('.datesCls').datepicker({
				dateFormat: 'yy-mm-dd',
				maxDate: new Date(),
				changeMonth: true,
				changeYear: true,
				yearRange: "-100:+0"
			  });
	}
	
	function enableSearchByName(){
		//$('#preEnrollNoValue').removeAttr('readOnly');
		$('#searchNameId,#searchVoterCardId,#searchHNoId').val("")
		$('#searchDetailsDiv').html("");
		$('#tableElement').hide();
		var errDivId = "#errorDiv1";
		$('#searchDetailsDiv1').html('');
		$('#tableElement1').hide();
		$('#preEnrollNo').val('');
		$(errDivId).html('');
		$( "#myModal1" ).dialog({title: "Search For Enrollment Number", width: "auto",
            height: "auto", position: { my: 'right', at: 'top+10' }});
	}
	
	function getExistingCadreInfo1(){
		var errDivId = "#errorDiv1";
	$('#searchDetailsDiv1').html('');
	$('#tableElement1').hide();
	$('#preEnrollNo').val('');
	$(errDivId).html('');
	
	var candidateName = $('#candiNameId').val();
	var enrollmentNo = $('#enrollmentNoId').val();
	var constituencyId = 181;
	var panchayatId = 18842;  // panchayat Id 
	var boothId = 0;  // boothId Id 
	var isPresentCadre = '${panchayatId}';  // ispresentCader checked ot not 
	
	var isError = false;
	if((candidateName == null || candidateName.length == 0) && (enrollmentNo == null || enrollmentNo.length == 0))
		{
			$(errDivId).html('Enter any search criteria for details.');
			 isError = true ;
		}
		
		if(candidateName == null || candidateName.length <=2)
		{	
			if(enrollmentNo != null && enrollmentNo.length  >=3 )
			{
				 isError = false ;
			}
			else 
			{
				$(errDivId).html('Atleast 3 Characters required for Candidate Name.');
				isError = true ;	
			}		
		}
		else
		{
			 isError = false ;
		}
	if(!isError){
	$('#searchDataImg1').show();
		var jsObj = {	
			name : candidateName,
			constituencyId : constituencyId,
			panchayatId : panchayatId,
			boothId : boothId,
			isPresentCadre:isPresentCadre,
			enrollmentNumber : enrollmentNo,
			task:"getExistingCadreInfo"              
		}
			   
		$.ajax({
			type : "POST",
			url : "gettingExistingCadreInfoAction.action",
			data : {task:JSON.stringify(jsObj)} ,
		}).done(function(result){
		$('#searchDataImg1').hide();
			buildExistingCadres(result);
		});
	}
	}
	
	function buildExistingCadres(results){
			$('#searchDetailsDiv1').html("");
			$('#tableElement1').show();
		if(results==null){

			$('#searchDetailsDiv1').html("<h4 style='text-align:center;'> No Data Available </h4>");	
			return;
		}
		var str = '';
			str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab1">';
				str +='<thead>';
					str +='<tr>';
						str +='<th class="text-align1"></th>';
						str +='<th class="text-align1">NAME</th>';
						str +='<th class="text-align1">GUARDIAN NAME</th>';
						str +='<th class="text-align1">ENROLLMENT NO</th>';
					str +='</tr>';
				str +='</thead>';
				str +='<tbody>';
				for(var i in results){
					str +='<tr>';
						str +='<td class="text-align1"><input type="checkbox" name="voters" onclick="updateEnrollmentNo(\''+results[i].caste+'\')"></th>';
						str +='<td>'+results[i].name+'</td>';
						str +='<td>'+results[i].desc+'</td>';
						str +='<td>'+results[i].caste+'</td>';
					str +='</tr>';
				}
				str +='</tbody>';
			str +='</table>';
			
			$('#searchDetailsDiv1').html(str);
			
		 
			$('#seachDetalsTab').dataTable({
				"iDisplayLength": 100,
				"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
	
	
	}
	
	function updateEnrollmentNo(enrollmentNo){
		$('#myModal1').dialog('close');
		$("#preEnrollNoValue").val(enrollmentNo);
		$('#preEnrollNo').val(enrollmentNo);
	}
	function clearPreviousEnrol(){
		$("#preEnrollNoValue").val("");
	}
	</script>
  </body>
</html>