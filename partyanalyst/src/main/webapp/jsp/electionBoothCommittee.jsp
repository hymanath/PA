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
    <link href="newCoreDashBoard/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="newCoreDashBoard/Plugins/Date/daterangepicker.css" type="text/css" rel="stylesheet"/>
	<link href="dist/2016DashBoard/Plugins/Datatable/jquery.dataTables.css" type="text/css" rel="stylesheet"/>
	<link href="newCoreDashBoard/css/custom.css" rel="stylesheet" type="text/css">
	<link href="dist/newmultiselect/chosen.css" rel="stylesheet" type="text/css">
	<link href="dist/scroll/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css">
	<script src="https://use.fontawesome.com/07d3416f74.js"></script>
  	
	
	<style>
	.dataTables_wrapper .dataTables_paginate .paginate_button
	{
		padding:0px;
	}
	#publicrepresantative,#mandalaffiliated,#advancedSearchDiv,#committeeDetailsDiv,#searchcadrenewDiv,#committeLocationsDiv,
	#designationDivId,#step1Id,#step2Id,#step3Id,#cadreDetailsDiv
	{
		display:none;
	}
	#noMembersErr{
	  font-weight:bold;	  
	}
	#committeeLocationIdErr,#committeeTypeIdErr,#afflitCommitteeIdErr,#searchErrDiv,#committeLocationIdErr,#advancedSearchErrDiv,
	#committeePositionIdErr,#searchLevelErrDiv,#nonAfflitCommitteeIdErr,#mandalDivIdErr{
		font-weight:bold;
		color:red;
	}
	.m_top5{margin-top:5px;}
	.m_top30{margin-top:30px;}
	.editDesignation{margin-bottom: 10px;}
	.hideRowClass{display:none;}
	
	.greyClass{ background-color:#777 !important ; color : #FFF !important;font-weight:bold !important }
	.orangeCls{background-color:orange !important;color:#fff !important;font-weight:bold !important}	
	.redCls{background-color:red !important;color:#fff !important;font-weight:bold !important} 	
	
	</style>
	<script>
		var allRolesList = new Array();
		var ageRangeArr = new Array();	
			<c:forEach var="election" items="${ageRangeList}">
				var elections ={
				name:'${election}'
				}
				ageRangeArr.push(elections);
			</c:forEach>
			
			<c:forEach var="rol" items="${locations}">
				var rols ={
				id:"${rol.locationId}",
				name:"${rol.locationName}"
				}
				allRolesList.push(rols);
			</c:forEach>	
	
	</script>
  </head>
  <body>
	
		<div class="container">
		
			<input type="hidden" value="1" id="committeeMngtType"/>		
			<input type="hidden" value="1" id="areaTypeId"/>
			<div class="row" id="basicCommitteeDiv">
				<div id="errorMessegeId" style ="color:red;"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title text-center">${finalStatus} &nbsp;CONSTITUENCY	</h4> 
						</div>
						
						<div class="panel-body" id="accessToUpdationDivId" style="display:none;">
							<div class="row" style="float:right;margin-right:15px;">
								<button class="btn btn-success btn-min btn-xs" id="addMembersBtn"> ADD MEMBERS </button>
								<button  class="btn btn-warning btn-min btn-xs" id="unlockBtn"> UNLOCK COMMITTEE </button>
							</div>
						</div>
						<div class="panel-body" id="unlockCommitteeId" style="display:none;" >
							<div class="row">
								<div class="col-sm-4" id="mandalMainDivId">
									<label for="">SELECT CONSTITUENCY <span style="color:red">*</span> </label>
									<select onchange="getCommitteeFinalizedBoothListforUnlock(this.value)" class="form-control" id="constituencyId" ><option value="0">Select Constituency </option></select>
								</div>								
								<div class="col-sm-4">
									<label for="committeeLocationIds1">SELECT BOOTH <span style="color:red">*</span><img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/> </label>
									<select onchange="populateDefaultValue(1);getBoothInchargeRoles();gettingBoothInchargeRoleDetails();" class="form-control" id="committeeLocationIds1" ><option value="0">Select Booth </option></select >
								</div>	
								
								<button class="btn btn-danger btn-min btn-xs" onclick="removeLock()" style="margin-top:30px"> Remove Lock</button>
							</div>
						</div>
						<div class="panel-body" id="addMemebrsDiv">
							<div class="row">
								<div class="col-sm-4" id="mandalMainDivId">
									<label for="">SELECT MANDAL/MUNCIPALITY/CORPORATION <span style="color:red">*</span><img style="width: 25px; height: 20px;" src="images/icons/loading.gif" id="dataLoadingImgForMandal"> </label>
									<div id="mandalDivId"></div>
								</div>
								<!--<div class="col-sm-4" id="committeePanchayatId">
									<label for="committeeLocationId">SELECT PANCHAYAT <span style="color:red">*</span><img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/> </label>
									<select onchange="populateDefaultValue(1);gePanchayatOrBooth1();" class="form-control" id="committeeLocationId" ><option value="0">Select PANCHAYAT</option></select >
								</div>-->
								<div class="col-sm-4">
									<label for="committeeLocationId1">SELECT BOOTH <span style="color:red">*</span><img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/> </label>
									<select onchange="populateDefaultValue(1);getBoothInchargeRoles();gettingBoothInchargeRoleDetails();" class="form-control" id="committeeLocationId1" ><option value="0">Select Booth </option></select >
								</div>		
								
								<div class="col-sm-4" id="committeeDesigDivId1" >
									<label for="committeeDesigId">SELECT DESIGNATION <span style="color:red">*</span><img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/> </label>
									<select  class="form-control" id="committeeDesignationId">
										<option value="0">Select Designation</option>
									</select>
								</div>
								
								<div class="col-sm-8 m_top20" >
									<div id="boothInchargeRoleDivId1"></div>
									<div class="panel-title" id='cadreSerialNoWiseId'></div>
									<div class="scrollDiv">
										<div  id="cadreDetailsDiv1">
										</div>
									</div>	
								</div>
								
								<div class="row">
									<div class="col-sm-4" style="margin-top:25px;margin-left:325px;">
										<ul class="list-inline">
											<li><input type="button" id="viewMembrsBtn" class="btn btn-success" onclick="getBoothUserDetails();" value="VIEW" /></li>
											<li><input type="button" id="addMembrsBtn" class="btn btn-success" style="display:none;" onclick="showSearchDiv();" value="FIND YOUR SERIAL NO " /></li>
										</ul>
										<img id="viewDataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/>
									</div>
								</div>
									<div class="row" style="margin-left:10px;">
										<h6 class=""><b >NOTE:</b> <br> 1)  To add <b style="red"> MEMBER </b> Position, we must and should select cadre from Serial No ranges. Other wise ADD PROFILE button will not visible to you. <br> 2) We can add <b style="red"> CONVENER </b> Position directly from any range.	</h6> 
									</div>
								<!--<div class="col-sm-4" id="committeeDesigDivId" style="display:none">
									<label for="committeeDesigId">SELECT DESIGNATION <span style="color:red">*</span><img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/> </label>
									<select  class="form-control" id="committeeDesignationId">
										<option value="0">Select Designation</option>
									</select>
								</div>-->
								<div class="col-sm-12">
									<!--<div id="boothInchargeRoleDivId" style="display:none;"></div>-->
									<div id="locationDivId"></div>
									<div id="userDetailsId"></div>
								</div>
							</div>
					</div>
			</div>
			
		
  
	<div class="row" id="searchcadrenewDiv">	
	
	<div class="row">
		<div style="border-top:1px solid #fff;" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2 text-center m_top20" id="step2Id">
			<span class="badge" style="z-index: 2; margin-top: -10px;">STEP- 2</span>
	   
		</div>
	</div>
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center" style="margin-left: 175px;">
				<h4 id="headingDiv" class="text-uppercase" style="margin-left: -262;"> Search Candidate For Booth Committee Incharge </h4>
			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center" >
				<div class="form-inline " style="margin-left: -212px;">
					<div class="radio">
						<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" > Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"> Mobile No &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"> Name &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="advancedSearch"  onclick="refreshExistingDetails();"  value="5"> Advanced Search &nbsp;&nbsp;</label>
						<input type="hidden" id="cadreSearchType" value="membershipId" />
					</div>				  
				</div>
			</div>
			<div class="row m_top20" id="committeLocationsDiv" >
				<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6  m_top20">
					<div class="form-group col-xs-12 pull-right">
						<label for="searchLevel">SEARCH LEVEL <img id="dataLoadingsImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/></label>
						<select class="form-control" id="searchLevelId" onchange="updateSearchType(this.value)">
						<option value="0">Select Location</option>
						<option value="1">Village/Ward</option>
						<option value="2">Town/Division</option>
						</select >
						<div id="searchLevelErrDiv"></div>
					 </div>
				</div>	
				<div class="col-md-4  col-sm-6 col-xs-6  m_top20">
					<div class="form-group col-xs-12 pull-right">
						<label for="committeLocationId">SELECT LOCATION </label>
						<select class="form-control" id="committeLocationId" ><option value="0">Select Location</option></select >
						<div id="committeLocationIdErr"></div>
					 </div>
				</div>			
			</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0"  id="basicSearchDiv">	
				<div class="row">      
					<div class="col-md-9 col-sm-9 col-xs-9 ">
						<input type="text" placeholder="ENTER MEMBERSHIP ID / VOTER ID / MOBILE NO / NAME"  class="form-control" id="searchBy">
						<div id="searchErrDiv"></div>
					</div>	
					<div class="col-md-3 col-sm-3 col-xs-3 ">
						<button class="btn btn-success btn-block" type="button" onclick="getCadreDetailsForBoothBySearchCriteria()" id="searchButnId">SEARCH</button>
					</div>			
				</div>			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20" id="advancedSearchDiv">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<!--<h6>Advanced Search</h6>-->
					<div id="advancedSearchErrDiv"></div>
					<div class="row">					
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							<label>Caste-Group
								<select class="form-control col-md-12 col-sm-12 col-xs-12 " id="casteCategory" onchange="casteDetailsByGroupId();">
								<option value="0" selected>All</option>
								<option value="1">OC</option>
								<option value="2">BC</option>
								<option value="3">SC</option>
								<option value="4">ST</option>
								</select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-5 ">
							<label>Caste Name
								
								<s:select theme="simple" cssClass="form-control editClass col-md-12 col-sm-12 col-xs-12" id="casteList" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Caste " style="width: 200px;"/>
							</label>
						</div>
						
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Age Range
								<select class="form-control col-md-12 col-sm-12 col-xs-12"  id="ageRange" onchange="clearbetwbAgeFields()" ><option>select</option></select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							 <b>Between Age<br>
								<input type="text" id="fromAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" From "/> - <input type="text" id="toAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" To  "/> 
							</b>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-4 ">
							<label>Gender
								<select class="form-control col-md-12 col-sm-12 col-xs-12"  id="gender"><option value="0" selected>All</option><option  value="1" >Male</option><option  value="2" >Female</option></select>
							</label>
						</div>
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block" onclick="getCadreDetailsForBoothBySearchCriteria()">SEARCH</button>
						</div>						
					</div>			
				</div>			
			</div>
			</div>

	<!--<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;" id="step3Id">
				 <span> CLICK  SELECT & UPDATE PROFILE FOR ADD A CADRE TO COMMITTEE. </span>
				</div>			
			</div>	
	</div>-->
	<div class="row">
	
			
			<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-left: 660px;margin-top: 20px;width:70px;height:60px;display:none;"/>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;" id="cadreDetailsDiv"></div>			
			</div>
		</div>	
		<!-- end mandal committee Block  -->
		
		
	</div>
	<div class="col-sm-8 col-sm-offset-2 m_top20">
		<div id="cadreAvailableDetailsDivId"></div>
	</div>	
	<!--<footer class="text-center m_top20">
			&copy; 2015 Telugu Desam Party
	</footer>-->

<script src="newCoreDashBoard/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="newCoreDashBoard/js/bootstrap.min.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mCustomScrollbar.js" type="text/javascript"></script>
<script src="dist/scroll/jquery.mousewheel.js" type="text/javascript"></script>
<script src="Assets/Plugins/DataTable/dataTable.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/moment.js" type="text/javascript"></script>
<script src="newCoreDashBoard/Plugins/Date/daterangepicker.js" type="text/javascript"></script>
<script src="dist/newmultiselect/chosen.jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="js/cadreCommittee/electionBoothCommittee.js"></script>
<script type="text/javascript">
 validateSearchType('3');
getTdpCommitteeMandalCorporationsByConstituency(2);	
var globalLocationId = '${sessionScope.USER.accessValue}';
var globalAccessLevel = '${sessionScope.USER.accessType}';
	 $("#nonafiliatedCommitteeId").append($("#nonafiliatedCommitteeId option:gt(0)").sort(function (a, b) {
              return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
          }));
	    var slickCount = 0;
		var reqlocationId ='${locationId}';
		var pancayatId = '';
		var commityTypeId = '';
		var commityId = '';
		var cadreRoleId = '';
		var task = '';
		var defaultName = '';
		var mandalNewId='';
		var isFirstMandalSettingValues = true;
		var isFirstPancayatSettingValues = true;
		var isFirstCommityIdSettingValues = true;
		var isFirstCadreRoleIdSettingValues = true;
		var afflicatedCommIds=new Array();
		var isAvailableOrNOt="true";
		
		 pancayatId = '${panchayatId}';
		 commityTypeId = '${committeeTypeId}';
		 commityId = '${committeeId}';
		 cadreRoleId = '${result3}';
		 task = '${task}';
		 defaultName = '${result4}';
		 mandalNewId ='${mandalId}';
		
		$('document').ready(function(){
			
			$('#committeeTypeId').val(commityTypeId);
			if(commityTypeId ==1)
			{
				getAffiliatedCommitsForALoc();
			}
			if(task.trim() == 2)
			{		
				$('#areaTypeId').val(task);
				$("#mndlLvlCommittSelec").prop("checked","checked");
			}
			$('.ageRangeCls').click(function(){
				//alert($(this).attr('id'));
				$('#ageRange').val(0);
			});
			
			/*$('#committeePositionId').on('change',function(){
				$('#cadreDetailsDiv').html('');
				$('#cadreDetailsDiv,#step3Id').hide();
			});*/

			$('#committeePositionId').on('change',function(){
				var roleId = $('#committeePositionId').val();
				$('#committeePositionIdErr').html('');
				if(defaultName==""){
					$('#searchBy').val('');
					$("#membershipId").trigger("click");
				}else{
					$("#membershipId").attr("checked","true");
				}
				$('#cadreSearchType').val('membershipId');
				$("#searchcadrenewDiv").hide();
				$('#searchBy').val('');
				if(roleId == null || roleId ==0)
				{
					$('#committeePositionIdErr').html('Please Select Designation.');
					return;
				}

				var jsObj={					
					designationId : roleId
				}
				$.ajax({
					type : "POST",
					url : "checkIsVacancyForDesignation.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					 if(typeof result == "string"){
								if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
								  location.reload(); 
								}
			         } 
						
					if(result != null)
					{ 
						if(result.trim().length>0)
						{
							$('#committeePositionIdErr').html(''+result.trim()+'');	
						}
						else
						{
							$("#searchcadrenewDiv").show();
							if(defaultName==""){
								$('#cadreDetailsDiv').html('');
								$('#cadreDetailsDiv,#step3Id').hide();
							}
						}
					}						
				});
				
			});
			$('.searchTypeCls').click(function(){
			
			var highlightCls = $('#basicCommitteeTab').attr('class');
			  $("#searchBy").html("");
			  $("#cadreDetailsDiv").hide();
			var id = $(this).attr('id');
			$('#nonAfflitCommitteeIdErr').html('');			
			$('#advancedSearchDiv').hide();			
			$('#basicSearchDiv').show();
			$('#committeLocationsDiv').hide();
			var committeTypeID = $('#committeeMngtType').val();
			
			
			if(id.trim() == 'membershipId')
			{
				
				if(committeTypeID != 1){
					$('#committeLocationsDiv').show();				
				}
				$('#cadreSearchType').val('membershipId');
			}
			if(id.trim() == 'voterId')
			{
				
				if(committeTypeID != 1){
					$('#committeLocationsDiv').show();				
				}
				$('#cadreSearchType').val('voterId');
			}
			if(id.trim() == 'mobileNo')
			{
				
				if(committeTypeID != 1){
					$('#committeLocationsDiv').show();				
				}
				$('#cadreSearchType').val('mobileNo');
			}
			
			if(id.trim() == 'name')
			{
				if(committeTypeID != 1){
					$('#committeLocationsDiv').show();				
				}
				if($('#basicCommitteeTab').attr('class') != 'btn btn-success btn-block arrow_selected')
				{
					//$('#basicCommitteeDiv1').show();
				}
				else{
					//$('#basicCommitteeDiv1').hide();
				}
				$('#cadreSearchType').val('name');
			}
			if(id.trim() == 'advancedSearch')
			{			
				if(committeTypeID != 1){
					$('#committeLocationsDiv').show();				
				}
				if($('#basicCommitteeTab').attr('class') != 'btn btn-success btn-block arrow_selected')
				{
					//$('#basicCommitteeDiv1').show();
				}
				else{
					//$('#basicCommitteeDiv1').hide();
				}
			
				$('#basicSearchDiv').hide();
				$('#advancedSearchDiv').show();
				$('#cadreSearchType').val('advancedSearch');
				$('#ageRange').find('option').remove();
				$('#ageRange').append('<option  value="0"> All </option>');
				if(ageRangeArr != null && ageRangeArr.length>0)
				{

					for(var i in ageRangeArr)
					{
						$('#ageRange').append('<option  value="'+ageRangeArr[i].name+'">'+ageRangeArr[i].name+'</option>');
					}
				}
			}
		});
		 
		 //getBoothsByConstituencyIds();
		});
		
	function getCommitteeLocations(){
		var id = 0;
		if(reqlocationId != null && reqlocationId > 0)
		id = reqlocationId;
		if($("#villageId").is(':checked')){
			return;
		}
		$("#printBtnDiv").hide();
		$("#mandalMainDivId").hide();		
		hideMembers();
		$("#affiliCommitteeAllInfoDivId").html("");
		$("#elctarolInfoDivId").html("");
		$("#addMembrsBtn").show();
		$("#viewMembrsBtn").show();
		$("#committeeTypeId").val(0);
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		$("#committeeMainId").hide();
		$("#dataLoadingsImg").show();
		$("#dataLoadingImg").show();

		var reqLocationType ="";
		var committeTypesID = $('#committeeMngtType').val();
		var searchLevelId = 0;
		if(committeTypesID != 1)
			searchLevelId = $('#searchLevelId').val();
		if(task == 2 || searchLevelId == 2 || $("#mndlLvlCommittSelec").is(':checked')){
		  reqLocationType ="mandal";
		}
		$.ajax({
			type : "POST",
			url : "getCommitteLocationsAction.action",
			data : {locationType:reqLocationType,locationId:id} ,
		}).done(function(result){		
		$("#dataLoadingsImg").hide();
		$("#dataLoadingImg").hide();
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
		
			if(committeTypesID == 1)
			{
				$("#committeeLocationId  option").remove();
				$("#committeeLocationId").append('<option value="0">Select Location</option>');
			}
			
			$("#committeLocationId  option").remove();
			$("#committeLocationId").append('<option value="0">Select Location</option>');
			
			var reqNewLocationType ="";
			if(task == 2 ||  searchLevelId == 2 || $("#mndlLvlCommittSelec").is(':checked')){
			  reqNewLocationType ="mandal";
			}
			if(reqNewLocationType == reqLocationType){
				 if(committeTypesID == 1)
					for(var i in result){
						$("#committeeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
				
				 if(committeTypesID != 1)
					 for(var i in result){
						$("#committeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
				if(pancayatId != 0 && isFirstPancayatSettingValues)
				{
					task="";
					isFirstPancayatSettingValues = false;
					$("#committeeLocationId,#committeLocationId").val(pancayatId);
					getAffiliatedCommitsForALoc();
				}
			}
		});
	}
	function SortByName(a, b){
		  var aName = a.locationName.toLowerCase();
		  var bName = b.locationName.toLowerCase(); 
		  return ((aName < bName) ? -1 : ((aName > bName) ? 1 : 0));
		}
	function getAffiliatedCommitsForALoc(){
		$("#committeeDetailsDiv").hide();
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		$("#committeeMainId").hide();
	//	alert("typeId: "+$("#committeeTypeId").val())
		var locId = $("#committeeLocationId").val();
		
		var panchayatWardByMandal= $("#panchayatWardByMandal").val();
		
	/*	if( panchayatWardByMandal == null || panchayatWardByMandal == 0){
			$("#mandalDivIdErr").html("Please Select Mandal");
			//return;
		}  */
		if( locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
		
		if($("#committeeTypeId").val() == 2){
			$("#committeeMainId").show();
			var reqLocationType = "";
			var reqLocationValue = "";
			if($("#mndlLvlCommittSelec").is(':checked')){
			  reqLocationType ="mandal";
			}
			reqLocationValue=$("#committeeLocationId").val();
			//alert(reqLocationValue)
			$.ajax({
				type : "POST",
				url : "getAllAffiliatedCommittiesAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue} ,
			}).done(function(result){	
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
    		      location.reload(); 
    	        }
			}
				if(result != null)
				{
					result.sort(SortByName);
					for(var i in result){
					   $("#afflitCommitteeId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
				}
				if(cadreRoleId !=0 && isFirstCommityIdSettingValues)
				{
					
					$("#afflitCommitteeId").val(commityId);
					hideMembers();
					getCommitteCadreMembersInfo(2);  
				}
			});
					
		}else if($("#committeeTypeId").val() == 3){
			
			getElctoralInfoForALocation();
			getAllCommitteeMembersInfoInALoc();
		}else{

			$("#committeeMainId").hide();
			//$("#afflitCommitteeId").hide();
			//$("#afflitCommitteeId").attr("disabled","disabled");
			if(cadreRoleId !=0 &&  isFirstCommityIdSettingValues)
				{
					isFirstCommityIdSettingValues = false;
					$("#afflitCommitteeId").val(commityId);
					hideMembers();
					getCommitteCadreMembersInfo(2);
				}
		}
		
	}
	
	function getCommitteMembersInfo(){
		//alert(1);
		//shrinuFunction();
		$("#desigChangErrs").html("");
		$("#designationDivId,#step1Id,#step2Id,#step3Id,#cadreDetailsDiv").hide();
		$("#committeeLocationIdErr").html("");
		$('#cadreDetailsDiv').html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		$("#searchcadrenewDiv").hide();
		$('#searchBy').val('');
		var locId = $("#committeeLocationId").val();
		var locVal = $("#afflitCommitteeId").val();
		if(locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		//getBoothUserDetails();
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		if($("#committeeTypeId").val() == 2){
			 if(locVal == null || locVal == 0){
				$("#afflitCommitteeIdErr").html("Please Select Affiliated Committee");
				return;
			}
		 }
		 $("#committeeDetailsDiv").show();
		 $("#searchcadrenewDiv").hide();
		 $('#searchBy').val('');
		 $("#commitMembrsCountDiv").html('<center><img src="images/icons/loading.gif"  /></center>');
		 $("#committeeMmbrsMainDiv").html("");
		 var reqCommitteeType = "main";
		 var reqLocationType = "";
		 var title ="MAIN COMMITTEE";
		 if($("#committeeTypeId").val() == 2){
			 reqCommitteeType = "affiliated";
			 title =$.trim($("#afflitCommitteeId option:selected").text())+" COMMITTEE";
		 }
		 $("#affComitteeMainTitle").html(title.toUpperCase());
		 if(reqCommitteeType == "main"){
		   if($("#mndlLvlCommittSelec").is(':checked')){
		     reqLocationType ="mandal";
		   }
		   reqLocationValue=$("#committeeLocationId").val();
		 }else{
			 reqLocationValue=$("#afflitCommitteeId").val();
		 }
		 $("#viewMembrsBtn").attr("disabled","disabled");
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue,committeeType:reqCommitteeType} ,
			}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
			    }
				$("#viewMembrsBtn").removeAttr("disabled");
				//Summary Building
				slickCount = slickCount+1;
				var counts = result.result;
				var str ='<div id="variable-width'+slickCount+'">';
			    for(var i in  counts){
					str+='<div class="slick_widget">';
					str+='	<h5>'+counts[i].locationName+'</h5>';
					str+='	<ul class="list-inline text-center" >';
					
					if(counts[i].totalCount!=0){
						str+='<li class="btn btn-xs  greyClass"  disabled="disabled">'+counts[i].totalCount+'</li>';
					}else{
						str+='<li class="btn btn-xs  greyClass" disabled="disabled">N/A</li>';
					}
					
					if(counts[i].roleType != null && counts[i].roleType == 'P'){
						if(counts[i].proposedCount != null && counts[i].proposedCount != 0){
						   str+='<li class="btn btn-xs orangeCls" disabled="disabled" style="margin-left: 5px;">'+counts[i].proposedCount+'</li>';
						}else{
							str+='<li class="btn btn-xs orangeCls" disabled="disabled" style="margin-left: 5px;">0</li>';
						}
					}
					
					if(counts[i].finalizedCount != 0){
					  str+='<li class="btn btn-xs btn-success" disabled="disabled" style="margin-left: 5px;font-weight:bold !important">'+counts[i].finalizedCount+'</li>';	
					}else{
						str+='<li class="btn btn-xs btn-success" disabled="disabled" style="margin-left: 5px;font-weight:bold !important">0</li>';
					}
					
					if(counts[i].totalCount!= 0){
						str+='<li class="btn btn-xs redCls" disabled="disabled" style="margin-left: 5px;">'+counts[i].vaccancyCount+'</li>';
					}else{
						str+='<li class="btn btn-xs redCls" disabled="disabled" style="margin-left: 5px;">N/A</li>';
					}
					str+='	</ul>';
					str+='</div>';
			   }
			   
			   $("#commitMembrsCountDiv").html(str);
			   //COMMITTEE MEMBERS BUILDING
			   var members = result.hamletsOfTownship;
			   str ="";
			   if(members != null && members.length > 0){
				   
				   /*  if(result.locationName != null && result.locationName == "N"){
				      str+='<div class="text-right editDesignation"><input type="button" value="EDIT DESIGNATION" onclick="showEditInfo();" class="btn btn-success" /></div>';
				    }  */
				   str+='<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#000;">';
				   str+='<thead style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.2); color:#000">';
                   str+='<th>Designation</th>';
                   str+='<th>Image</th>';
                   str+='<th>Name</th>';
				   str+='<th>Enrollment No</th>';
                   str+='<th  class="hideRowClass">Edit Designation</th>';
				   str+='<th  class="hideRowClass">Update</th>';
				   str+='<th>Status</th>'
				   str+='</thead>';
				   
				   str+='<tbody>';
				   var x = 0;
				   for(var i in members){
					  str+=' <tr>';
					  str+=' 	<td>'+members[i].value+'</td>';
					  str+=' 	<td><img width="32" id="imagecdr'+i+'" height="32" src="images/cadre_images/'+members[i].url+'" onerror="setDefaultImage(this);"/></td>';
					  str+=' 	<td>'+members[i].name+'</td>';
					  str+=' 	<td>'+members[i].type+'</td>';
					  str+=' 	<td class="hideRowClass" style="color:#3d3d3d;"><select currtdpComMembId="'+members[i].mainAccountId+'" reqtdpComMembId="'+members[i].id+'" reqtdpCadreId="'+members[i].orderId+'" class="editOldDesig form-control">';
					  for(var j in counts){
						  if(members[i].mainAccountId ==  counts[j].locationId){
					        str+='  <option value="'+counts[j].locationId+'" selected="selected">'+counts[j].locationName+'</option>';
						  }else{
							str+='  <option value="'+counts[j].locationId+'">'+counts[j].locationName+'</option>';  
						  }
					  }
					  str+='</select></td>';
					  //if(x == 0){
					    //str+=' 	<td class="hideRowClass" rowspan="'+members.length+'"><div class="hideRowClass"><input type="button" value="UPDATE DESIGNATION" onclick="updateExistingDesig('+result.population+');" class="btn btn-success" /></div><br><div id="desigChangErrs"></div></td>';
						str+=' 	<td class="hideRowClass" ><div class="hideRowClass"><input type="button" value="UPDATE DESIGNATION" onclick="updateExistingDesig('+result.population+');" class="btn btn-success" /></div><br><div id="desigChangErrs"></div></td>';
					  //}
					  if(members[i].committeeMemberStatus != null && members[i].committeeMemberStatus.trim().length > 0){
						  if(members[i].committeeMemberStatus == 'F'){//Finalized
							  str+='<td > Finalized ';
						  }else{
							   str+='<td style="background-color:orange; "> Proposed ';
						  }
						  
						  if(result.locationName == 'N')
							str+='<div class="pull-right  btn btn-default btn-sm deleteCls deleteCls'+i+'" ><i style="cursor:pointer;" class="glyphicon glyphicon-trash " onclick="deleteCadreRole(\''+members[i].id+'\',\'deleteCls'+i+'\');"></i></div> ';
						
						  str+=' </td> ';
						  
					  }else{
						   str+='<td> - </td>';
					  }
					  str+=' </tr>';
					  x++;
				   }
				   str+='	</tbody>';
				   str+='</table>';
			   }else{
				   str+='<span id="noMembersErr">No Members Added To This Committee</span>';
			   }
			   $("#committeeMmbrsMainDiv").html(str);
			   
			   $('#variable-width'+slickCount).slick({
					  dots: false,
					  infinite: false,
					  speed: 300,
					  autoplay: false,
					  autoplaySpeed: 2000,
					  variableWidth: true
					});  
		    });
	}
	 function showEditInfo(){
		$("#desigChangErrs").html("");
		$(".hideRowClass").each(function(){
			$(this).show();
		});
	}
	function setDefaultImage(img)
	{
		img.src = "images/cadreCommitee/Member_thamb_image.png";
	}
	function populateDefaultValue(level){
		$("#affiliCommitteeAllInfoDivId").html("");
	    $("#searchcadrenewDiv").hide();
		$("#addMembrsBtn").show();
		$("#viewMembrsBtn").show();
		 $("#cadreDetailsDiv,#step3Id,#searchcadrenewDiv,#designationDivId,#step1Id,#committeeMainId").hide();
		 $("#cadreDetailsDiv").html("");
		 $('#searchBy').val('');
		 $("#affiliCommitteeAllInfoDivId").html("");
		  $("#viewMembrsBtn").removeAttr("disabled");
		  
		 if(level == 1)
			$('#committeeTypeId').val(0)
		var committeeTypeId = $('#committeeTypeId').val();

		if(level == 1){
		  $("#committeeLocationIdErr").html("");
		  $("#committeeTypeId").val(0);
		  $("#committeeDetailsDiv").hide();
		  $("#elctarolInfoDivId").html("");
		  $("#printBtnDiv").hide();
		}
		
		if(level == 2){
			if($('#committeeTypeId').val() == 3 ){
			$("#viewMembrsBtn").hide();
			$("#addMembrsBtn").hide();
			}
			$("#printBtnDiv").hide();
			$("#elctarolInfoDivId").html("");
		}
		if(committeeTypeId != null && committeeTypeId == 2) 
		{
			$("#committeeMainId").show();
		}
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
	}
	function getCommitteCadreMembersInfo(type){
		//alert(2);
		var areaType = $('#areaTypeId').val();
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		var locId = $("#committeeLocationId").val();
		var locVal = $("#afflitCommitteeId").val();
		if(locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		if(locVal == 0)
		{
			$("#designationDivId,#step1Id,#step2Id,#step3Id").hide();
			$("#searchcadrenewDiv").hide();
			$('#searchBy').val('');
		}
		
		 $("#committeeMmbrsMainDiv").html("");
		 var reqCommitteeType = "main";
		 var reqLocationType = "";
		 var title ="MAIN COMMITTEE";
		 if($("#committeeTypeId").val() == 2){
			 reqCommitteeType = "affiliated";
			 title =$.trim($("#afflitCommitteeId option:selected").text())+" COMMITTEE";
		 }
		 $("#affComitteeMainTitle").html(title.toUpperCase());
		 if(reqCommitteeType == "main"){
		   if(areaType == 2){
		     reqLocationType ="mandal";
		   }
		   reqLocationValue=$("#committeeLocationId").val();
		 }else{
			 reqLocationValue=$("#afflitCommitteeId").val();
		 }
		 $("#committeePositionId  option").remove();
		 $("#committeePositionId").append('<option value="0">Select Designation</option>');
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue,committeeType:reqCommitteeType} ,
			}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				if(result != null)				
				{
					if(result.result != null && result.result.length>0)
					{
						for(var i in result.result)
						{
							$("#committeePositionId").append('<option value="'+result.result[i].locationId+'" attr_role_type = "'+result.result[i].roleType+'">'+result.result[i].locationName+'</option>');
						}
						
						if(cadreRoleId != 0 && isFirstCadreRoleIdSettingValues)
						{
							
							isFirstCadreRoleIdSettingValues = false;
														
							$('#searchBy').val(defaultName);
							showSearchInfo();
							$("#committeePositionId").val(cadreRoleId);
							var committeTypeID = $('#committeeMngtType').val();
							if(committeTypeID == 1)
							{
								 setTimeout(function(){ 
								getCadreDetailsForBoothBySearchCriteria();}, 1000);
							}
						
						}
							
					}
				}
		    });
	}
	
	function hideMembers(){
		$("#committeeDetailsDiv").hide();
	}
	
	function clearbetwbAgeFields()
	{
		$('.ageRangeCls').val('');
		
	}
	function refreshExistingDetails(){
		//$("#fromAgeId").val("");
		//$("#toAgeId").val("");
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
		//$("#casteCategory").val(0);
		//$("#casteList").val(0);
		//$("#ageRange").val(0);
		//$("#gender").val(0);
	}
	function showSearchInfo(){
		$('#committeePositionIdErr').html('');
		$("#cadreDetailsDiv").hide();
		$('#cadreDetailsDiv').html("");
		
		if(cadreRoleId !=0 && isFirstCommityIdSettingValues)
		{			
			isFirstCommityIdSettingValues = false;
			$("#searchcadrenewDiv").show();
		}
		else{
			$("#committeeDetailsDiv").hide();
			$("#searchcadrenewDiv").hide();
			$('#searchBy').val('');
			$("#committeePositionId").val(0);
		}
		 
		var locId = $("#committeeLocationId").val();
		var locVal = $("#afflitCommitteeId").val();
		if(locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		if($("#committeeTypeId").val() == 2){
			 if(locVal == null || locVal == 0){
				$("#afflitCommitteeIdErr").html("Please Select Affiliated Committee");
				return;
			}
		 }
		 
		 //$("#searchcadrenewDiv").show();
		 var committeTypeID = $('#committeeMngtType').val();
				if(committeTypeID == 1)
				{
					$("#designationDivId,#step1Id,#step2Id").show();
				}
	}
	function updateSearchType(levelId)
	{
		var reqLocationType = "";
		if(levelId==0){
			$("#committeLocationId  option").remove();
			$("#committeLocationId").append('<option value="0">Select Location</option>');
		}
		if(levelId==2){
			reqLocationType = "mandal";
		}
		$("#dataLoadingsImg").show();
		$.ajax({
			type : "POST",
			url : "getCommitteLocationsAction.action",
			data : {locationType:reqLocationType} ,
		}).done(function(result){
			$("#dataLoadingsImg").hide();
			$("#committeLocationId  option").remove();
			$("#committeLocationId").append('<option value="0">Select Location</option>');
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			
			for(var i in result){
				if(levelId==1)
				{
					$("#committeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}else{
					var locId = result[i].locationId+"";
					if(locId.substr(0,1) == "1" || locId.substr(0,1) == "3"){
				       $("#committeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
				}
			}
		});
		if(levelId==1)
		{
			$('#areaTypeId').val(levelId);
		}
		else if(levelId==2)
		{
			$('#areaTypeId').val(levelId);
		}
	}
	
	var elegbleRolCnt=0;
	var dttCnt = 0;
	
	function addMoreEligibleRoles(divId,index,btnDivId,cadreId){
		elegbleRolCnt=elegbleRolCnt+1;
		dttCnt = dttCnt+1;
		var generatedId=index+''+cadreId;
       var str='';
        str+='<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"  id="eligibleRolesDivs'+elegbleRolCnt+'">';
		str+='	<div class="row">';
		str+='	  <div class="form-group col-md-3 col-md-offset-0 col-sm-4 col-xs-4 ">';
		str+='		<label >Designation</label>';
		str+='		<select class="form-control designationCls'+cadreId+'"  id="designation'+generatedId+'" name="eligibleRoles['+elegbleRolCnt+'].designationLevelId">';
		str+='		   <option value="0"> Select Designation</option>';
		    for(var i in allRolesList){
		      str+='<option value="'+allRolesList[i].id+'">'+allRolesList[i].name+'</option>';
			}
		str+='		</select>';
		str+='<br><span id="designation'+generatedId+'Err" class="validErrCls" style="color:red;font-size:12px;"></span>';
		str+='	  </div>';
		str+='	   <div class="form-group col-md-3 col-md-offset-0 col-sm-4 col-xs-4">';
		str+='			<label >From Date</label>';
		str+='			<input type="text" placeholder="Select From Date" id="fromDateIda'+generatedId+'" key ="a'+dttCnt+'"  class="form-control fromDateCls'+cadreId+'" name="eligibleRoles['+elegbleRolCnt+'].fromDateStr">';
		str+='          <br><span id="fromDateIda'+generatedId+'Err" class="validErrCls" style="color:red;font-size:12px;"></span>'; 
		str+='	   </div>';
		str+='	   <div class="form-group col-md-2 col-md-offset-1 col-sm-4 col-xs-4">';
		str+='			<label >To Date</label>';
		str+='			<input type="text" placeholder="Select To Date" id="toDateIda'+generatedId+'"  class="form-control toDateCls'+cadreId+'" name="eligibleRoles['+elegbleRolCnt+'].toDateStr">';
		str+='          <br><span id="toDateIda'+generatedId+'Err" class="validErrCls" style="color:red;font-size:12px;"></span>';
		str+='		</div>	';	  
		str+='	</div>';
		if(index != 0)
			str+='<a style="margin-left: 17px;" class="btn btn-danger btn-xs " href="javascript:{removeselDiv(\'eligibleRolesDivs'+elegbleRolCnt+'\','+index+');}"> Remove </a>';
		str+='</div>';
		$('#'+divId+'').append(str);
		$('#'+divId+'').show();
		$('.'+divId+'').show();
		$('#fromDateIda'+generatedId).datepicker({
				dateFormat: 'yy-mm-dd',
				maxDate: new Date(),
				changeMonth: true,
				changeYear: true,
				yearRange: "-100:+0"
			  });
		$('#toDateIda'+generatedId).datepicker({
				dateFormat: 'yy-mm-dd',
				maxDate: new Date(),
				changeMonth: true,
				changeYear: true,
				yearRange: "-100:+0"
			  });
			  index = index+1;
			  var str1 = '';
			  str1+='<a href="javascript:{addMoreEligibleRoles(\''+divId+'\','+index+',\''+btnDivId+'\','+cadreId+');}" class="btn btn-danger btn-xs ">Click here to Add+ Details</a>';	
			  $('#'+btnDivId+'').html(str1);
	}	

	function updateExistingDesig(reqCommitteeId){
		$("#desigChangErrs").html("");
		var newRequestArray =  new Array();
		var changesFound = false;
		$(".editOldDesig").each(function(){
			var obj={
					 tdpCommitteeMemberId:$(this).attr("reqtdpcommembid"),
					 tdpCadreId:$(this).attr("reqtdpcadreid"),
					 currentRole:$(this).attr("currtdpcommembid"),
					 newRole:$(this).val(),
				   } 
			newRequestArray.push(obj);
			if($(this).attr("currtdpcommembid") != $(this).val()){
			  changesFound = true;
			}	
		 });
		 if(!changesFound){
			$("#desigChangErrs").html("<span style='color:green;'>No Changes Found To Update</span>");
            return;			
		 }
		 $("#desigChangErrs").html('<img src="images/icons/search.gif" class="ajaxImgStyle">');
		var jsObj={					
					committeeId : reqCommitteeId,
					requestArray:newRequestArray
				}
		  $.ajax({
				type : "GET",
				url : "updateCommitteeMemberDesignation.action",
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#desigChangErrs").html('');
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				if(result.resultCode == 1){
					$("#desigChangErrs").html("<span style='color:green;'>Designations Updated Successfully</span>");
					getCommitteMembersInfo();
				}else if(result.resultCode == 0){
				 $("#desigChangErrs").html("<span style='color:red;'>Error Occured.Please try Again.</span>");
				}else if(result.resultCode == 2){
					if(result.message == "This Committee Is Already Confirmed.You Cannot Update Any Information"){
				        $("#desigChangErrs").html("<span style='color:red;'>"+result.message+"</span>");
					}else{
						$("#desigChangErrs").html("<span style='color:red;'>Max Count Exceeded For "+result.message+" Designation</span>");
					}
				}
			});
	}
	/*function getMandalCorporationsByConstituency()
	{		
		var id = 0;
		if(reqlocationId != null && reqlocationId > 0)
		id = reqlocationId;
		if(task == 2){
			$('#mndlLvlCommittSelec').trigger('click');
			getCommitteeLocations();
		}else{
			if($("#mndlLvlCommittSelec").is(':checked')){
					return;
				}
			$("#mandalMainDivId").show();
			//console.log("mandalNewId  :"+mandalNewId);
			$("#committeeMainId").hide();
			
			$("#committeeLocationId option").remove();
			$("#committeeLocationId").append('<option value="0">Select Location</option>');
			
			$("#panchayatWardByMandal option").remove();
			$("#panchayatWardByMandal").append('<option value="0">Select Mandal</option>');
			
				$("#dataLoadingImgForMandal").show();
				var jsObj = {
					locationId :id,
						task:""
				}
				 $.ajax({
					type : "POST",
					
					url : "getMandalCorporationsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				$("#dataLoadingImgForMandal").hide();
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				var str='';
				if(result !=null){
						str+='<select id="panchayatWardByMandal" class="form-control" onchange="getPanchayatWardByMandal();">';
						str+='<option value="0">Select Location</option>';
						for(var i in result)
						{
							str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>'
						}
						str+='</select>';	
				}
					$("#mandalDivId").html(str);
					if(mandalNewId != 0 && isFirstMandalSettingValues)
					{
						task="";
						isFirstMandalSettingValues = false;
						$("#panchayatWardByMandal").val(mandalNewId);
						getPanchayatWardByMandal();
					}
				});
			}
	
	}*/
	
	/*function getPanchayatWardByMandal(){
		     $('#cadreDetailsDiv').html('');
			 $('#cadreDetailsDiv,#step3Id').hide();
			 $('#designationDivId').hide();
             $('#searchcadrenewDiv').hide();
			 $('#step1Id').hide();
			if($("#mndlLvlCommittSelec").is(':checked')){
				return;
			}
			$("#mandalDivIdErr").html('');
			
			$("#affiliCommitteeAllInfoDivId").html("");
			$("#elctarolInfoDivId").html("");
			$("#addMembrsBtn").show();
			$("#viewMembrsBtn").show();
			$("#printBtnDiv").hide();
			
			var mandalId=$("#panchayatWardByMandal").val();
			hideMembers();
			//$("#committeeTypeId").val(0);
			$("#committeeLocationIdErr").html("");
			$("#committeeTypeIdErr").html("");
			$("#afflitCommitteeIdErr").html("");
			$("#committeeMainId").hide();
			$("#dataLoadingsImg").show();
			$("#dataLoadingImg").show();
			var reqLocationType ="";
			var committeTypesID = $('#committeeMngtType').val();
			var searchLevelId = 0;
			if(committeTypesID != 1)
				searchLevelId = $('#searchLevelId').val();
			if(task == 2 || searchLevelId == 2 || $("#mndlLvlCommittSelec").is(':checked')){
			  reqLocationType ="mandal";
			}
			var jsObj={
				mandalId:mandalId,
				//constituencyId:'${locationId}'
				constituencyId:0
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){	
		$("#dataLoadingsImg").hide();
		$("#dataLoadingImg").hide();
			if(typeof result == "string"){
				if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
				  location.reload(); 
				}
			}
			if(committeTypesID == 1)
			{
				$("#committeeLocationId  option").remove();
				$("#committeeLocationId").append('<option value="0">Select Location</option>');
			}
			
			$("#committeLocationId  option").remove();
			$("#committeLocationId").append('<option value="0">Select Location</option>');
			
			var reqNewLocationType ="";
			if(task == 2 ||  searchLevelId == 2 || $("#mndlLvlCommittSelec").is(':checked')){
			  reqNewLocationType ="mandal";
			}
			if(reqNewLocationType == reqLocationType){
				 if(committeTypesID == 1)
					for(var i in result){
						$("#committeeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
				
				 if(committeTypesID != 1)
					 for(var i in result){
						$("#committeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
				
				if(pancayatId != 0 && isFirstPancayatSettingValues)
				{
					task="";
					isFirstPancayatSettingValues = false;
					$("#committeeLocationId").val(pancayatId);
					getAffiliatedCommitsForALoc();
				} 
			}
		
		});	
			
	}*/
	
	 function getAllCommitteeMembersInfoInALoc(){
		  $("#viewMembrsBtn").hide();
			$("#addMembrsBtn").hide();
		 var reqLocationType = "";
			var reqLocationValue = "";
			if($("#mndlLvlCommittSelec").is(':checked')){
			  reqLocationType ="mandal";
			}
			reqLocationValue=$("#committeeLocationId").val();
				$.ajax({
				type : "POST",
				url : "getAllCommitteeMembersInfoInALoc.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue} ,
				}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}				
					var str='';
					str+='<div class="col-md-12 col-md-offset-0" style="text-align:center; font-size:22px;>';
                    str+='<h3 class="panel-header">COMMITTEE MEMBERS INFO</h3>'
                    str+='<hr style="border-color:#F00;margin-top:10px;"></div>';
				 if(result!=null && result.hamletsOfTownship!=null && result.hamletsOfTownship.length > 0){
					var members = result.hamletsOfTownship;
					str+='<table class="table table-bordered" style="borde:1 solid #000;background-color:rgba(0,0,0,0.1);"><thead style="background-color:rgba(0,0,0,0.2);"><tr><th style="width:15%">Committee Name</th><th style="width:15%">Designation</th><th style="width:5%">Image</th><th style="width:38%">Name</th><th style="width:27%">Enrolment Number</th><th style="width:27%">Status</th></tr></thead>';
					str+='<tbody>';
					
					for(var i in members){
					  str+=' <tr>';
					  str+='<td>'+members[i].mandalName+'</td>'
					  str+=' <td>'+members[i].value+'</td>';
					  str+='<td><img width="32" id="imagecdr'+i+'" height="32" src="images/cadre_images/'+members[i].url+'" onerror="setDefaultImage(this);"/></td>';
					  str+=' 	<td>'+members[i].name+'</td>';
					  str+='<td>'+members[i].type+'</td>';
					  if(members[i].committeeMemberStatus != null && members[i].committeeMemberStatus.trim().length > 0){
						  if(members[i].committeeMemberStatus == 'F'){//Finalized
							  str+='<td> Finalized </td>';
						  }else{
							   str+='<td> Proposed </td>';
						  }
						}else{
						   str+='<td> - </td>';
						}
					  str+='</tr>';
				   }
					
					str+='</tbody></table>';
				 }else{
					 str+='<center><div style="margin-bottom:10px;"><b>No Data Available</b></div></center>';
				 }
				 $("#affiliCommitteeAllInfoDivId").html(str);
			});
	}
	 
	 function getElctoralInfoForALocation(){
		var locationId=$("#committeeLocationId").val();
		if(!($('#villageId').is(':checked'))){
			return;
		}
		var jObj={
					locationId:locationId,
					task:"getElectoralDetails"
				}
				$.ajax({
				type : "POST",
				url : "getElctoralInfoForALocationAction.action",
				data : {task:JSON.stringify(jObj)} ,
				}).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
					var str='';
					str+='<div class="col-md-12 col-md-offset-0" style="text-align:center; font-size:22px;>';
					str+='<h3 class="panel-header">ELECTORALS INFO</h3>'
					str+='<hr style="border-color:#F00;margin-top:10px;"></div>';
					if(result != null && result.length > 0){
						str+='<table class="table table-bordered" style="borde:1 solid #000;background-color:rgba(0,0,0,0.1);"><thead style="background-color:rgba(0,0,0,0.2);"><tr><th style="width:20%">Committee Name</th><th style="width:45%">Name</th><th style="width:10%">Image</th><th style="width:25%">Enrolment Number</th><th style="width:25%">Status</th></tr></thead>';
						str+='<tbody>';
						
						for(var i in result){
						  str+=' <tr>';
						  str+=' <td>'+result[i].fromTime+'</td>';
						  str+=' 	<td>'+result[i].toTime+'</td>';
						  str+='<td><img width="32" id="imagecdr'+i+'" height="32" src="images/cadre_images/'+result[i].pageUrl+'" onerror="setDefaultImage(this);"/></td>';
						  str+='<td>'+result[i].timeSpent+'</td>';
						  if(result[i].committeeMemberStatus != null && result[i].committeeMemberStatus.trim().length > 0){
							  if(result[i].committeeMemberStatus == 'F'){//Finalized
								  str+='<td> Finalized </td>';
							  }else{
								   str+='<td> Proposed </td>';
							  }
							}else{
							   str+='<td> - </td>';
							}
							str+='</tr>';
					   }
					   str+='</tbody></table>';
					 }else{
						str+='<center><b>No Data Available</b></center>';
					 }
					  $("#elctarolInfoDivId").html(str);
				});
	 }
	//getCommitteeLocations();
	//getMandalCorporationsByConstituency();
	 // getTdpCommitteeMandalCorporationsByConstituency(1);
	
	
	if(reqlocationId != null && reqlocationId !='')
	getUserLocation1(reqlocationId);
	else
	getUserLocation();
	
	function CallPrint(strid) {
            var prtContent = document.getElementById(strid);
            var WinPrint = window.open();
            WinPrint.document.write(prtContent.innerHTML);
            WinPrint.document.close();
            WinPrint.focus();
            WinPrint.print();
            //WinPrint.close();
        }
      
function deleteCadreRole(tdpCommitteeMemberId,className)
	{
		//var committeeEnrollmentId =$("#tdpCommitteeYearId1").val();
		var date =$("#reportrange1").val();
		//var fromDate;
		//var toDate;
	//	var dates = date.split("-");
		//fromDate = dates[0];
		//toDate = dates[1];
	var r=confirm("Are You sure want to Remove ?");
		if(r)
		{
	var jsObj = 
	{
		tdpcommitteeMemberId:tdpCommitteeMemberId,
		committeeEnrollmentId :["2"],
		fromDate :'01/01/2017',
		toDate :'01/01/2019',
		task:"deleterole"
	}
	
	$.ajax({
          type:'GET',
          url: 'getCommitteeDetailsByStatusPopUpAction.action',
          dataType: 'json',
          data: {task:JSON.stringify(jsObj)},
     	  }).done(function(result){
				if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
			  if(result != null){
				  {
					if(result[0].status == "Removed"){
						alert("Removed Successfully..")
						getCommitteMembersInfo();
						$('.'+className+'').hide();
					}else
						alert("Committee Already Confirmed");
				  }
			}
	   });
		}
	
	}
	
	$(document).on("click",".redirectHomePage",function(){
	
		window.location.replace('committeeInfoAction.action');
	});
	function getTdpCommitteeMandalCorporationsByConstituency(num)
	{	
	$("#dataLoadingImgForMandal").show();
		var jsObj = {
			locationId :'${locationValue}',
			enrollmentId : 2,
			task:""
		}
		 $.ajax({
			type : "POST",
			url : "getTdpCommitteeMandalCorporationsByConstituencyAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			$("#dataLoadingImgForMandal").hide();
				var str='';
				if(result !=null){
					if(num == 2)
						<!--str+='<select id="panchayatWardByMandal" class="form-control" onChange="getTdpCommitteePanchayatWardByMandal()">';-->
					    str+='<select id="panchayatWardByMandal" class="form-control" onChange="gePanchayatOrBooth(0)">';
						str+='<option value="0">Select Mandal/Muncipality/Corporation</option>';
						for(var i in result)
						{
							str+='<option value="'+result[i].id+'">'+result[i].name+'</option>'
						}
						str+='</select>';	
				}
				$("#mandalDivId").html(str);
		});
	}
/*$(document).on("change","#panchayatWardByMandal",function(){
	getTdpCommitteePanchayatWardByMandal($(this).val());
});	*/	
			
	function getTdpCommitteePanchayatWardByMandal(){
		var mandalId = $('#panchayatWardByMandal').val();
		
		    $("#committeeLocationId  option").remove();
			 $("#committeeLocationId1  option").remove();
			var num =$("#panchayatWardByMandal").val();
	      var res = num.charAt(0);
			var enrllmentId=2;
			if(parseInt(res) == 1){
				$("#committeePanchayatId").hide();
				gePanchayatOrBooth(0);
				enrllmentId = 0;
				return;
			}else{
				$("#committeePanchayatId").show();
			}
			var jsObj={
				mandalId:mandalId,
				constituencyId:'${locationValue}',
				enrollmentId:enrllmentId
			}
		$("#dataLoadingsImg").show();
		$("#dataLoadingImg").show();
			$.ajax({
				type : "POST",
				url : "getTdpCommitteePanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){	
		$("#dataLoadingsImg").hide();
		$("#dataLoadingImg").hide();
			if(result != null){
					$("#committeeLocationId").append('<option value="0">Select Panchayat</option>');
					$("#committeeLocationId1").append('<option value="0">Select Booth </option>');
				for(var i in result){
						$("#committeeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
						
				}
			}
			
		});	
			
	}
function gePanchayatOrBooth(type){
	if(parseInt(type) >0)
		return;
	
	$('#committeeDesigDivId1').show();
	$("#cadreDetailsDiv1,#cadreSerialNoWiseId,#boothInchargeRoleDivId1").html('');
	$("#cadreAvailableDetailsDivId").html('');
	var num =$("#panchayatWardByMandal").val();
	$('#userDetailsId,#locationDivId,#cadreDetailsDiv').html('');
	$('#addMembrsBtn,#boothInchargeRoleDivId,#committeeDesigDivId,#searchcadrenewDiv,#cadreDetailsDiv').hide();
	$('#searchBy').val('');
	 $("#committeeLocationId1  option").remove();
	 $("#committeeLocationId1").append('<option value="0">Select Booth</option>');
	var res = num.charAt(0);
	if(res == 1){
		getBoothsByMandal($('#panchayatWardByMandal').val());	
	}else if(res == 2){
		getBoothsByMandal($('#panchayatWardByMandal').val());
	}
	
}
function getBoothsByMandal(mandalId){
		    $("#committeeLocationId1  option").remove();
			var jsObj={
				mandalId:mandalId,
				constituencyId:'${locationValue}',
				//constituencyId:212
			}
		$("#dataLoadingsImg").show();
		$("#dataLoadingImg").show();
			$.ajax({
				type : "POST",
				url : "getBoothsForMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){	
		$("#dataLoadingsImg").hide();
		$("#dataLoadingImg").hide();
			if(result != null){
					$("#committeeLocationId1").append('<option value="0">Select Booth</option>');
				for(var i in result){
						$("#committeeLocationId1").append('<option value='+result[i].id+'>Booth No - '+result[i].roleId+' ('+result[i].relativeName+' )</option>');
				}
			}
		});
	}

$(document).on("click","#committeeDesignationId",function(){
	var id =$(this).val();
	$('#searchcadrenewDiv').hide();
	$('#searchBy').val('');
	if(id>0){
		//$('#searchcadrenewDiv').show();	
	}
});

$(document).on("click","#addMembersBtn",function(){
	$('#addMemebrsDiv').show();
	$('#unlockCommitteeId').hide();
});
$(document).on("click","#unlockBtn",function(){
	$('#addMemebrsDiv').hide();
	$('#unlockCommitteeId').show();
});

function showSearchDiv(){
	$('#searchcadrenewDiv').show();
	$('#cadreAvailableDetailsDivId').hide();
	var id =$('#committeeDesignationId').val();
	$('#searchBy').val('');
	if(id>0){
		$('#searchcadrenewDiv').show();	
	}
	
	var errMsg ="";
	//$('#committeeDesignationId').val(0);
	$("#userDetailsId").html("");
	$("#searchBy,#locationDivId").html('');
	//$('#searchcadrenewDiv,#boothInchargeRoleDivId').hide();
	$('#searchBy').val('');
	var mandalId = $("#panchayatWardByMandal").val();	
	var boothId = $("#committeeLocationId1").val();
	if(mandalId == 0 || mandalId.length==0){
		errMsg=errMsg+"Please select atleast one Mandal/Muncipality/Corporation.<br/>";
	} 
	if(boothId == 0 || boothId.length ==0){
		errMsg=errMsg+"Please select atleast one Booth...<br/>";
	}
	if(errMsg.length>0){
	$("#errorMessegeId").html(errMsg);
		return;
	}else{
		$("#errorMessegeId").html('');
		$("#committeeDesigDivId").show();
	}
}

function getBoothUserDetails(){
	$('#locationDivId').show();
	$('#userDetailsId').show();
	$('#cadreAvailableDetailsDivId').hide();
	$("#searchcadrenewDiv").hide();
	$('#searchBy').val('');
	$("#committeeDesigDivId").hide();
	$('#committeeDesignationId').val(0);
	$("#cadreDetailsDiv").hide();
	$("#userDetailsId").html('');
	$("#locationDivId").html('');
	$("#searchBy").val('');
	$("#membershipId").trigger("click");
	var errMsg="";	
	var constiName ='${finalStatus}';
	var selectLocationName = constiName+" Constituency";
	var mandalId = $("#panchayatWardByMandal").val();
	var boothId = $("#committeeLocationId1").val();
	//var panchayatId = $("#committeeLocationId").val();
	var mandalName = $("#panchayatWardByMandal option:selected").text();
	var boothName = $("#committeeLocationId1 option:selected").text();
	$("#boothInchargeRoleDivId").hide();

	  //if(mandalId == 0 || mandalId.length==0){
	//	errMsg=errMsg+"Please select atleast one Mandal/Muncipality/Corporation.<br/>";
	 // } 
	  /*if(panchayatId ==0 || panchayatId.length == 0){
		  errMsg=errMsg+"Please select atleast one panchayat<br/>";
	  }*/
	 /* if(boothId == 0 || boothId.length ==0){
			errMsg=errMsg+"Please select atleast one Booth<br/>";
	  }*/
	  if(boothId !=0 && boothId>0)
		{
			$("#boothInchargeRoleDivId").show();
			boothId = boothId;
			mandalId = 0;
			selectLocationName = boothName;
		}
		else if(mandalId !=0 && mandalId>0)
		{
			//mandalId = mandalId.substr(1);
			boothId = 0;
			selectLocationName = mandalName;
		}
		if(errMsg.length>0){
		  $("#errorMessegeId").html(errMsg);
		     return;
	   }else{
		 $("#errorMessegeId").html('');
	    } 
		$("#viewDataLoadingImg").show();
		
	var jObj={
		constituencyId:globalLocationId,
		mandalId:mandalId,
		boothId:boothId,
		cadreType:""
	}
	
	$.ajax({
	
	type:"GET",
	url:"getBoothUserDetailsAction.action",
	data:{task:JSON.stringify(jObj)}
	}).done(function(result){
		$("#viewDataLoadingImg").hide();
			if(result != null && result != ''){
			  getBoothUserDetailsbuild(result,selectLocationName,boothId);
			}else{
				$("#userDetailsId").show();
				$("#userDetailsId").html(' <center> No Data available... </center>');
			}
		});
	}
function getBoothsByConstituencyIds(){
	var jsObj =
			{
			constituencyId : globalLocationId	
			}
			
			$.ajax({
					type : "POST",
					url : "getBoothsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null){
						buildTotalConstituency(result);
					}else{
						$("#totStaredNotStartedConstiencyId").html('No Data Available');
					}
				});
		
}

function buildTotalConstituency(result){
	var str ='';
	str +='<table class="table table-bordered" id="totalStartedConstituencyId">';
	str +='<thead>'
	str +='<tr>'
	str +='<th>Total Constituency</th>';
	str +='<th>Total Started Constituency</th>';
	str +='<th>Total Not Started Constituency</th>';
	str +='</tr>';
	str +='</thead>';
	str +='<tbody>';
	str +='<tr>';
	if(result != null){
		//alert(result.totalCount);
		str +='<td>'+result.totalCount+'</td>';
		str +='<td>'+result.total+'</td>';
		str +='<td>'+result.totNotStartedBothCnt+'</td>';
		str +='</tr>';
	}
	str +='</tbody>';
	str +='</table>'
	$("#totStaredNotStartedConstiencyId").html(str);
}
function getTdpCommitteePanchayatWardByMandal1(mandalId){
		//var mandalId = $('#committeeLocationId').val();
		    $("#committeeLocationId1  option").remove();
			//var num =$("#panchayatWardByMandal").val();
	      var res = mandalId.charAt(0);
			var enrllmentId=2;
			if(parseInt(res) == 1){
				enrllmentId = 0;
			}
			var jsObj={
				mandalId:mandalId,
				constituencyId:'${locationValue}',
				enrollmentId:enrllmentId
			}
		$("#dataLoadingsImg").show();
		$("#dataLoadingImg").show();
			$.ajax({
				type : "POST",
				url : "getTdpCommitteePanchayatWardByMandalAction1.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){	
		$("#dataLoadingsImg").hide();
		$("#dataLoadingImg").hide();
			if(result != null){
					$("#committeeLocationId1").append('<option value="0">Select Booth</option>');
				for(var i in result){
						$("#committeeLocationId1").append('<option value='+result[i].locationId+'>Booth No - '+result[i].locationName+'</option>');
				}
			}
			
		});	
			
	}
	function gePanchayatOrBooth1(){
	var num =$("#committeeLocationId").val();
	var res = num.charAt(0);
	if(res == 1){
		getTdpCommitteePanchayatWardByMandal2();	
	}else if(res == 2){
		getBoothsByMandal($('#committeeLocationId').val());
	}
	
}

function getTdpCommitteePanchayatWardByMandal2(){
		var mandalId = $('#committeeLocationId').val();
		    $("#committeeLocationId1  option").remove();
			//var num =$("#panchayatWardByMandal").val();
	      var res = mandalId.charAt(0);
			var enrllmentId=2;
			if(parseInt(res) == 1){
				enrllmentId = 0;
			}
			var jsObj={
				mandalId:mandalId,
				constituencyId:'${locationValue}',
				enrollmentId:enrllmentId
			}
		$("#dataLoadingsImg").show();
		$("#dataLoadingImg").show();
			$.ajax({
				type : "POST",
				url : "getTdpCommitteePanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){	
		$("#dataLoadingsImg").hide();
		$("#dataLoadingImg").hide();
			if(result != null){
					$("#committeeLocationId1").append('<option value="0">Select Booth </option>');
				for(var i in result){
						$("#committeeLocationId1").append('<option value='+result[i].locationId+'>Booth No - '+result[i].locationName+'</option>');
				}
			}
			
		});	
			
	}
	// while Pressing Enter button event
	$(document).keydown(function(event) {
    var keycode = (event.keyCode ? event.keyCode : event.which);
    if (keycode == '13') {
        $('#searchButnId').click();
    }
});

function getBoothInchargeRoles()
{
	$("#committeeDesignationId  option").remove();	
	$("#committeeDesignationId").append('<option value="0">Select Designation</option>');	
	
	var boothId = $("#committeeLocationId1").val();
	if(parseInt(boothId)>0){
		var enrollmentYrIds = [];
		enrollmentYrIds.push(1);
		var jsObj = {
			boothId :boothId,
			enrollmentYrIds:enrollmentYrIds
		};
		 $.ajax({
			type : "POST",
			url : "getBoothInchargeRolesAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			if(result != null && result != 0){
				isAvailableOrNOt="true";
				for(var i in result){
					$("#committeeDesignationId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				}
			}else{
				isAvailableOrNOt="false";
			}
		});
	}
}
	
	function gettingBoothInchargeRoleDetails(){
		
		$("#boothInchargeRoleDivId1,#locationDivId").html('');
		$("#cadreDetailsDiv1,#cadreSerialNoWiseId").html('');
		var boothId = $('#committeeLocationId1').val();
		//$('#addMembrsBtn').show();
		$('#searchBy').val('');
		$('#boothInchargeRoleDivId,#searchcadrenewDiv,#userDetailsId,#committeeDesigDivId').hide();
		if(boothId==0){
			$('#addMembrsBtn').hide();
		}
		
	    var  enrllmentId =0;
			var jsObj={
				boothId:boothId,
				constituencyId:'${locationValue}',
				boothInchargeEnrollmentId:enrllmentId
			}
			$.ajax({
				type : "POST",
				url : "gettingBoothInchargeRoleDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){	
			if(result != null){
				$('#addMembrsBtn').show();
				buildBoothInchargeRoleDetails(result);	
			}else{
				$('#addMembrsBtn').hide();
			}
		});	
	}
	function buildBoothInchargeRoleDetails(result){
	var str ='';
	str +='<div class="col-sm-12">';
	str +='<div class= "row">';
	str +='<div class= "col-sm-5 col-sm-offset-3">';
	str +='<table class="table table-bordered" id="roleTableId">';
	str +='<thead>';
	str +='<tr>';
	str +='<th style="background-color:#d3d3d3">ROLE NAME</th>';
	str +='<th style="background-color:#d3d3d3">TOTAL</th>';
	str +='<th style="background-color:#d3d3d3">FILLED</th>';
	str +='<th style="background-color:#d3d3d3">VACANCY</th>';
	str +='</tr>';
	str +='</thead>';
	str +='<tbody>';
	str +='<tr>';
	for( var i in result){
		str +='<td>'+result[i].roleName+'</td>';
		str +='<td>'+result[i].maxMemberCount+'</td>';
		str +='<td>'+result[i].count+'</td>';
		str +='<td>'+result[i].vacancyCount+'</td>';
		str +='</tr>';
	}
	str +='</tbody>';
	str +='</table>';
	str +='</div>';
	str +='</div>';
	str +='</div>';
	$("#boothInchargeRoleDivId1").html(str);
}
	function updateRangeIdsOfBoothIncharge(boothId)
	{	
	//var boothId = $("#committeeLocationId1").val();
	$("#cadreDetailsDiv1,#cadreSerialNoWiseId").html('');
	var boothIncrgRoleId = $("#committeeDesignationId").val();
	var enrollmentYrIds = [];
	enrollmentYrIds.push(1);
		var jsObj = {
			boothId :boothId,
			enrollmentYrIds:enrollmentYrIds,
			boothIncgRoleId:boothIncrgRoleId
		}
		 $.ajax({
			type : "POST",
			url : "updateRangeIdsOfBoothInchargeAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			
		});
	}
</script>
<script>
var tableToExcel = (function() {
   var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
</script>
  </body>
</html>