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
   
	<style>
		.form-control[disabled] {color:#333 !important;background-color: rgba(0, 0, 0, 0.1) !important;border: medium none rgba(0, 0, 0, 0) !important;
		}		
	</style>
  </head>
  <body>
		
    <div class="container-fluid">
		<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-12 col-sm-12 col-xs-12 text-center">
				<img src="Images/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
		</div>
		
		<div class="row m_top20">
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 text-center" style="border-bottom:1px solid #FD2A34;">
				<button href="#" class="btn btn-xs btn-warning pull-right" id="editFieldsId">EDIT</button>
				<img src="http://www.mytdp.com/images/cadre_images/${cadreCommitteeVO.imageURL}"  class="img-circle" style="border:4px solid rgb(255, 231, 0);"/>
				<h3>${cadreCommitteeVO.cadreName}</h3>
				<h4>Cadre Number : ${cadreCommitteeVO.memberShipCardId}</h4>				
			</div>			
		</div> 	
		<div class="row m_top20">
				<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12">
					<div class="row">
						<div class="col-md-4 col-sm-4 col-xs-4 form-group"> <!--  value="${cadreCommitteeVO.DOB}"-->
							<input type="text" class="form-control editClass" id="dateOfBirth" value="${cadreCommitteeVO.DOB}" readOnly="true" style="cursor:text;" disabled>
						</div>
						<div class="col-md-4  col-sm-4 col-xs-4 form-group">
							<input type="text" id="disabledInput" class="form-control  editClass" value="${cadreCommitteeVO.age} years" placeholder="Age: 33 years old" disabled>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 form-group">
							<input type="text" id="disabledInput" class="form-control  editClass"  value="${cadreCommitteeVO.gender}" placeholder="Gender: Male" disabled>
						</div>
					</div>
				</div>
				
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<input type="text" id="disabledInput" class="form-control  editClass"  value="${cadreCommitteeVO.voterCardNo}" placeholder="Voter ID:96325874" disabled>
				</div>
				<div class="col-md-4   col-sm-6 col-xs-6 form-group">
					<input type="text" id="disabledInput" class="form-control  editClass"  value="${cadreCommitteeVO.adhaarNo}" placeholder="Aadhar No:258963147" disabled>
				</div>
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<input type="text" id="disabledInput" class="form-control editClass mobileNumber"  value="${cadreCommitteeVO.mobileNo}" placeholder="Mobile No:9632587410" disabled>
				</div>
				<!--
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<input type="text" id="disabledInput" class="form-control  editClass"  value="${cadreCommitteeVO.mobileType}" placeholder="Mobile Type: Smart Mobile" disabled>
				</div>
				-->
				
				<!--
				<div class="col-md-4   col-sm-6 col-xs-6 form-group">
					<input type="text" id="disabledInput" class="form-control" value="${cadreCommitteeVO.casteName}" placeholder="Caste: BC -caste Name" disabled>
				</div>
				-->
				
				<div class="col-md-4   col-sm-6 col-xs-6 form-group">
					<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level editClass" id="casteId" list="cadreCommitteeVO.casteList" listKey="casteStateId" listValue="casteName" headerKey="0" headerValue=" Select Caste " style="width:420px;height:35px;" name="cadreRegistrationVO.casteId"   value="%{cadreCommitteeVO.casteStateId}" disabled="true"/>	
				</div>
				
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
					<input type="text" id="disabledInput" class="form-control editClass"  value="${cadreCommitteeVO.address}" placeholder="Address: " disabled>
				</div>
								
				<!--
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<input type="text" id="disabledInput" class="form-control" value="${cadreCommitteeVO.education}" placeholder="Education: " disabled>
				</div>
				-->
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 form-group">
					<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level editClass" id="educationId" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Education " style="width:420px;height:35px;" name="cadreRegistrationVO.educationId" value="%{cadreCommitteeVO.educationId}" disabled="true"/>
				</div>
				<!--
				<div class="col-md-4   col-sm-6 col-xs-6 form-group">
					<input type="text" id="disabledInput" class="form-control" value="${cadreCommitteeVO.occupation}"  placeholder="Occupation:" disabled>
				</div>
				-->
				<div class="col-md-4 col-sm-6 col-xs-6 form-group">
					<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level editClass" id="educationId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Occupation " style="width:420px;height:35px;" name="cadreRegistrationVO.occupationId" value="%{cadreCommitteeVO.occupationId}" disabled="true"/>
				</div>
				
				<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
					<input type="text" id="disabledInput" class="form-control  editClass" value="${cadreCommitteeVO.emailId}"  placeholder="E-Mail ID: " disabled>
				</div>
			
		</div>
		
		<div class="row m_top20">
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 text-center" style="border-bottom:1px solid #FD2A34;">
				<h4>PREVIOUS ROLES IN PARTY</h4>				
			</div>			
		<s:if test="%{cadreCommitteeVO.previousRoles != null && cadreCommitteeVO.previousRoles.size() > 0}">
			<c:forEach var="rolesVO" items="${cadreCommitteeVO.previousRoles}" varStatus="commentLoop">
		<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group m_top20">
					<input type="text" id="disabledInput" class="form-control" placeholder="Previous Role Name #1   in Party  [ From date - To date ] " value="${rolesVO.role} [ ${rolesVO.fromDate}  - ${rolesVO.toDate}  ] " disabled>
			</div>	
			</c:forEach>
		</s:if>
			
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group" id="addMoreRolesDiv">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" >
					<div class="row">
					  <div class="form-group col-md-6 col-sm-6 col-xs-6 ">
						<label for="exampleInputEmail2" >Committee Level</label>
						<select class="form-control "></select>
					  </div>
					  <div class="form-group col-md-6 col-sm-6 col-xs-6">
						<label >Committee Name</label>
						<select class="form-control "></select>
					  </div>					  
					</div>
					
					<div class="row">
					  <div class="form-group col-md-4 col-sm-4 col-xs-4 ">
						<label for="exampleInputEmail2" >Committee Role</label>
						<input type="text" class="form-control ">
					  </div>
					  <div class="form-group col-md-4 col-sm-4 col-xs-4">
						<label >From Date</label>
						<input type="text" class="form-control ">
					  </div>
					  <div class="form-group col-md-4 col-sm-4 col-xs-4">
						<label >To Date</label>
						<input type="text" class="form-control ">
					  </div>					  
					</div>
					<a href="javascript:{addMoreRolesForCadre();}" class="btn btn-danger btn-xs ">Tap to Add+ Details</a>					
				</div>
			</div>
			
		</div> 
		
		<div class="row m_top20">
			<div class="col-md-6 col-md-offset-3 col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 text-center" style="border-bottom:1px solid #FD2A34;">
				<h4>PREVIOUSLY PARTICIPATED IN ELECTION</h4>				
			</div>			
		
			
			<s:if test="%{cadreCommitteeVO.previousElections != null && cadreCommitteeVO.previousElections.size() > 0}">
			<c:forEach var="electionVO" items="${cadreCommitteeVO.previousElections}" varStatus="commentLoop">
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group m_top20">
					<input type="text" id="disabledInput" class="form-control" placeholder="Allure  - MPTC -  2014" value="${electionVO.constituency} - ${electionVO.electionType} - ${electionVO.electionYear}" disabled>
			</div>	
			</c:forEach>
		</s:if>
			
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 form-group">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" >
					<div class="row">
					  <div class="form-group col-md-4 col-sm-4 col-xs-4 ">
						<label for="exampleInputEmail2" >Election Type</label>
						<select class="form-control "></select>
					  </div>
					  <div class="form-group col-md-4 col-sm-4 col-xs-4">
						<label >Location / Constituency</label>
						<select class="form-control "></select>
					  </div>
					  <div class="form-group col-md-4 col-sm-4 col-xs-4">
						<label >Election Year</label>
						<select class="form-control "></select>
					  </div>					  
					</div>
					
					<a href="#" class="btn btn-danger btn-xs ">Tap to Add+ Details</a>					
				</div>
			</div>
			
			<div class="col-md-10 col-md-offset-2  col-sm-12 col-xs-12 m_top20 text-center generateotpdiv">
				<div  class="row">		
					<div class="referencediv"></div>
					<div class="col-md-2 col-md-offset-0  col-sm-3 col-xs-3">
						<button class="btn btn-primary" type="submit" onclick="generateOTP();">Generate OTP</button>
					</div>
					<div class="col-md-2 col-md-offset-0  col-sm-4 col-xs-4">
							<input type="text" class="form-control otptextbox" placeholder="ENTER OTP NUMBER" >						
					</div>
					<div class="col-md-2 col-md-offset-0  col-sm-3 col-xs-3">
						<button class="btn btn-success" type="submit" onclick="checkOTPValid();">Validate OTP</button>
					</div>	
				</div>
			</div>
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 m_top20 text-center updateProfileDivId">
				<button class="btn btn-success btn-block btn-lg m_top20" type="submit">UPDATE PROFILE  &  ADD to MANDAL Affliated Electoral</button>
			</div>			
		</div> 
		
	</div>
	<footer class="text-center m_top20">
			&copy; 2015
	</footer>

   
	<script>		
		var mobnum,refid;
		$('document').ready(function(){
			 $('#dateOfBirth').datepicker({
				dateFormat: 'yy-mm-dd',
				maxDate: new Date(),
				changeMonth: true,
				changeYear: true,
				yearRange: "-100:+0"
			  });
			 
			 $('#editFieldsId').click(function(){
			 $('.editClass').removeProp('disabled');
			 
			 });
			 $('.generateotpdiv').hide();
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
			var mobileNo=$(".mobileNumber").val();
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
					$('.updateProfileDivId').show();
			  });
		}
	</script>
  </body>
</html>