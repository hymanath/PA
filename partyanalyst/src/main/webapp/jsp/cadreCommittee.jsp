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
   	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
			<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<style>
	#publicrepresantative,#mandalaffiliated,#advancedSearchDiv,#committeeDetailsDiv,#searchcadrenewDiv,#committeLocationsDiv,
	#designationDivId,#step1Id,#step2Id,#step3Id,#cadreDetailsDiv
	{
		display:none;
	}
	#noMembersErr{
	  font-weight:bold;	  
	}
	#committeeLocationIdErr,#committeeTypeIdErr,#afflitCommitteeIdErr,#searchErrDiv,#committeLocationIdErr,#advancedSearchErrDiv,
	#committeePositionIdErr,#searchLevelErrDiv,#nonAfflitCommitteeIdErr{
		font-weight:bold;
		color:red;
	}
	.m_top5{margin-top:5px;}
	.m_top30{margin-top:30px;}
	
	
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
	<!--<div class="container-fluid">
		<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12 text-center">
				<img src="images/cadreCommitee/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
			<div class="col-md-3  col-xs-3 col-sm-3">
				<a href="#" class="dropdown-toggle btn btn-default btn-xs m_top20" data-toggle="dropdown" aria-expanded="false" style="margin-top: 60px;">
				Menu <img src="images/menu_icon.png" />
				</a>
				<ul class="dropdown-menu" role="menu" aria-labelledby="drop6" style="    background-color: rgb(239, 64, 54);top: 91px;">
				  <li><a tabindex="-1" href="cadreCommitteeAction.action">Home</a></li>
				  <li><a tabindex="-1" href="cadreCommitteeSummaryAction.action">Summary Report</a></li>
				  <li><a tabindex="-1" href="cadreCommitteeRequestAction.action">Request For Positions Increase</a></li>
				  <li role="presentation" class="divider" style="background-color: rgba(229, 229, 229,0.6);"></li>
				  <li><a tabindex="-1" href="newlogoutAction.action">Sign Out</a></li>
				</ul>
            </div>
		</div>-->
		<div class="container">
		<div class="row ">
		<h3 class="text-center">${finalStatus} &nbsp;CONSTITUENCY	</h3>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12" >
				<div class="row" >
				     
					<div class="col-md-4 col-md-offset-0 col-sm-4 col-xs-4" >
						<a class="btn btn-success btn-block arrow_selected" id="basicCommitteeTab" href="javascript:{showAndHideTabs('basicCommitteeDiv');}">Committee <br>Management</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4">
						<a class="btn btn-success btn-block "   id="publicrepresantativeTab" href="javascript:{showAndHideTabs('publicrepresantative');}">Mandal/Muncipality Main <br> Committee Electoral Management</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4">
						<a class="btn btn-success btn-block "  id="mandalaffiliatedTab" href="javascript:{showAndHideTabs('mandalaffiliated');}" >Mandal/Muncipality Affiliated   <br>Committee Electoral Management</a>				
					</div>
				</div> 		
			</div> 		
		</div> 
		<input type="hidden" value="1" id="committeeMngtType"/>		
		<input type="hidden" value="1" id="areaTypeId"/>
		<div class="row" id="basicCommitteeDiv">
		<div class="row m_top20">
			<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				<div class="radio pull-right">
				  <label>
				    <input type="radio" name="committeeType" onclick="validateSearchType('1');getCommitteeLocations();" checked="true" value="1" id="villageId"> Village / Ward
				  </label>
			    </div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-6">
				<div class="radio">
				  <label>
				    <input type="radio" id="mndlLvlCommittSelec" name="committeeType" onclick="validateSearchType('2');getCommitteeLocations();" value="2"/> Mandal / Town / Division 
				  </label>
			    </div>
			</div>
			
		</div> 
		
		<div class="row m_top20">
			<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				<div class="form-group col-xs-12 pull-right">
					<label for="committeeLocationId">SELECT LOCATION <span style="color:red">*</span><img id="dataLoadingImg" src="images/icons/loading.gif" style="width:25px;height:20px;display:none;"/> </label>
					<select onchange="populateDefaultValue(1);" class="form-control" id="committeeLocationId" ><option value="0">Select Location</option></select >
					<div id="committeeLocationIdErr"></div>
				 </div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-6">
				<div class="form-group col-xs-12">
					<label for="committeeTypeId">COMMITTEE TYPE <span style="color:red">*</span></label>
					<select class="form-control" id="committeeTypeId" onchange="getAffiliatedCommitsForALoc();populateDefaultValue(2);getCommitteCadreMembersInfo(1)" ><option value="0">Select Committee Type</option><option value="1">Main Committee</option><option value="2">Affiliated Committee</option></select >
					<div id="committeeTypeIdErr"></div>
				 </div>
			</div>
		</div>
		<div id="committeeMainId" class="row">	
			<div class="col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-12 ">
				<div class="form-group col-xs-12">
					<label for="committeeId">AFFILIATED COMMITTEE <span style="color:red">*</span></label>
					<select class="form-control" onchange="hideMembers();getCommitteCadreMembersInfo(2)" id="afflitCommitteeId"><option>Select Affiliated Committee</option></select >
					<div id="afflitCommitteeIdErr"></div>
				 </div>
			</div>			
		</div> 
		<!--<div class="row m_top20">
			<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 "  >
				<div class="form-group col-xs-12 pull-right" >
					<label for="committeeId">AFFILIATED COMMITTEE</label>
					<select class="form-control" onchange="hideMembers();getCommitteCadreMembersInfo(2)" id="afflitCommitteeId"><option>Select Affiliated Committee</option></select >
					<div id="afflitCommitteeIdErr"></div>
				 </div>
			</div>
			<!--
			<div class="col-md-4 col-sm-6 col-xs-6" id="designationDivId">
				<div class="form-group col-xs-12">
					<label for="exampleInputEmail1">COMMITTEE DESIGNATION</label>
					<select  class="form-control" id="committeePositionId"  name="eligibleRoles[0].cadreRoleId"><option value="0">Select Designation </option></select >
					<div id="committeePositionIdErr"></div>
				 </div>
			</div>
			
		</div>-->
		<div class="row">	
			<div class="col-md-12 col-sm-12 col-xs-12 text-center">
					<ul class="list-inline">
						<li><input type="button" id="viewMembrsBtn" class="btn btn-success" onclick="getCommitteMembersInfo();" value="VIEW" /></li>
						<li><input type="button"  class="btn btn-success" onclick="showSearchInfo();" value="ADD" /></li>
					</ul>
			</div> 
		</div> 
		
		<!-------VIEW BLOCK------>
		<div class="row" id="committeeDetailsDiv">	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4><span id="affComitteeMainTitle"></span></h4>
				<hr style="margin: 0px;">
				<ul class="list-inline pull-right ">
					<li><span style="color:#fff;font-weight:bold;">TOTAL </span></li>
					<li><span style="color:#C9302C;font-weight:bold;">FILLED </span></li>
					<li><span style="color:#449D44;font-weight:bold;">VACANCY</span></li>
				</ul>
				
			</div>			
			
			<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 ">
				<div class="variable-width" id="commitMembrsCountDiv"></div>
			</div>
		
			<div id="committeeMmbrsMainDiv" class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 col-xs-offset-0 text-center"></div>
		</div>
		<!-------/VIEW BLOCK END------>
		<div style="border-top:1px solid #fff;" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2 text-center m_top20"  id="step1Id">
			<span class="badge" style="z-index: 2; margin-top: -10px;">STEP-1</span>
		   
		  </div>
  
		<div id="designationDivId" class="row">	
			<div class="col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-12 ">
				<div class="form-group col-xs-12">
					<label for="exampleInputEmail1">COMMITTEE DESIGNATION</label>
					<select  class="form-control" id="committeePositionId"  name="eligibleRoles[0].cadreRoleId"><option value="0">Select Designation </option></select >
					<div id="committeePositionIdErr"></div>
				 </div>
			</div>			
		</div> 
		
		<!-------ADD BLOCK------>		
	</div>		
		<!-------/ADD BLOCK END------>
		
		<!-- start public Representatives Block  -->
		
		<div class="row" id="publicrepresantative" >	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1  m_top20 text-center">
				<h3 class="text-success text-uppercase" >Process to Add <br> public representatives as mandal Electoral</h3>
				
				<hr style="margin: 0px;">
			</div>

		</div>	
		<!-- end public Representatives Block  -->
		
		<!-- start mandal committee Block  -->
		
		<div class="container-fluid" id="mandalaffiliated">
				
		<div class="row m_top20">
			<div class="com-md-8 col-sm-12 col-xs-12 text-center">
				<h3 class="text-success text-uppercase">Process to add Cadre as Mandal/Municipality Affiliated Electoral</h3>
			</div>
		</div>
		<div id="nonafiliatedCommitteeDivId" class="row">	
			<div class="col-md-6 col-md-offset-2 col-sm-6 col-sm-offset-2 col-xs-12 ">
				<div class="form-group col-xs-12">
					<label for="committeeId">AFFILIATED COMMITTEE</label>
						<s:select theme="simple" cssClass="form-control selectBoxWidth span12 input-block-level editClass" id="nonafiliatedCommitteeId" list="cadreRolesVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Affiliated Committee  " style="width:100%;height:35px;" />	
					<div id="nonAfflitCommitteeIdErr"></div>
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
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4 id="headingDiv" class="text-uppercase"> Select Candidate For selected Designation </h4>
			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline ">
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
						<option value="1">Panchayat/Ward</option>
						<option value="2">Mandal/Town/Division</option>
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
						<button class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
					</div>			
				</div>			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20" id="advancedSearchDiv">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<!--<h6>Advanced Search</h6>-->
					<div id="advancedSearchErrDiv"></div>
					<div class="row">					
						<div class="col-md-2 col-sm-6 col-xs-6 ">
							<label>Caste-Group
								<select class="form-control col-md-12 col-sm-12 col-xs-12 " id="casteCategory" onchange="casteDetailsByGroupId();">
								<option value="0">All</option>
								<option value="1">OC</option>
								<option value="2">BC</option>
								<option value="3">SC</option>
								<option value="4">ST</option>
								</select>
							</label>
						</div>
						<div class="col-md-3 col-sm-6 col-xs-6 ">
							<label>Caste Name
								
								<s:select theme="simple" cssClass="form-control editClass col-md-12 col-sm-12 col-xs-12" id="casteList" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Caste " style="width: 200px;"/>
							</label>
						</div>
						
						<div class="col-md-2 col-sm-4 col-xs-4 ">
							<label>Age Range
								<select class="form-control col-md-12 col-sm-12 col-xs-12"  id="ageRange" onchange="clearbetwbAgeFields()" ><option>select</option></select>
							</label>
						</div>
						<div class="col-md-2 col-sm-4 col-xs-4 ">
							<label> Between Age<br>
								<input type="text" id="fromAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" From "/> - <input type="text" id="toAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" To  "/> 
							</label>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-4 ">
							<label>Gender
								<select class="form-control col-md-12 col-sm-12 col-xs-12"  id="gender"><option>All</option><option>Male</option><option>Female</option></select>
							</label>
						</div>
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
						</div>				
					</div>			
				</div>			
			</div>
			</div>

	<div class="row">
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;" id="step3Id">
				 <span style="font-weight:bold;">Hint (Step - 3): </span> <span> CLICK  SELECT & UPDATE PROFILE FOR ADD A CADRE TO COMMITTEE. </span>
				</div>			
			</div>	
	</div>
	<div class="row">
	
			
			<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-left: 660px;margin-top: 20px;width:70px;height:60px;display:none;"/>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;" id="cadreDetailsDiv"></div>			
			</div>
		</div>	
		<!-- end mandal committee Block  -->
		
		
	</div>
	<!--<footer class="text-center m_top20">
			&copy; 2015 Telugu Desam Party
	</footer>-->

	<script>	
	 $("#nonafiliatedCommitteeId").append($("#nonafiliatedCommitteeId option:gt(0)").sort(function (a, b) {
              return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
          }));
	    var slickCount = 0;
		
		var pancayatId = '';
		var commityTypeId = '';
		var commityId = '';
		var cadreRoleId = '';
		var task = '';
		var defaultName = '';
		var isFirstPancayatSettingValues = true;
		var isFirstCommityIdSettingValues = true;
		var isFirstCadreRoleIdSettingValues = true;
		
		 pancayatId = '${panchayatId}';
		 commityTypeId = '${committeeTypeId}';
		 commityId = '${committeeId}';
		 cadreRoleId = '${result3}';
		 task = '${task}';
		 defaultName = '${result4}';
			
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
			
			$('#committeePositionId').on('change',function(){
				$('#cadreDetailsDiv').html('');
				$('#cadreDetailsDiv,#step3Id').hide();
			});

			$('#committeePositionId').on('change',function(){
				var roleId = $('#committeePositionId').val();
				$('#committeePositionIdErr').html('');
				$('#searchBy').val('');
				$("#membershipId").prop("checked","checked");
				$('#cadreSearchType').val('membershipId');
				$("#searchcadrenewDiv").hide();
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
					         
					if(result != null)
					{
						  if(typeof result == "string"){
								if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
								  location.reload(); 
								}
			                  }
						if(result.trim().length>0)
						{
							$('#committeePositionIdErr').html(''+result.trim()+'');	
						}
						else
						{
							$("#searchcadrenewDiv").show();
							$('#cadreDetailsDiv').html('');
							$('#cadreDetailsDiv,#step3Id').hide();
						}
					}						
				});
				
			});
			$('.searchTypeCls').click(function(){
			
			var highlightCls = $('#basicCommitteeTab').attr('class');
			
			var id = $(this).attr('id');
			$('#nonAfflitCommitteeIdErr').html('');			
			$('#advancedSearchDiv').hide();			
			$('#basicSearchDiv').show();
			$('#committeLocationsDiv').hide();
			var committeTypeID = $('#committeeMngtType').val();
			
			
			if(id.trim() == 'membershipId')
			{
				$('#cadreSearchType').val('membershipId');
			}
			if(id.trim() == 'voterId')
			{
				$('#cadreSearchType').val('voterId');
			}
			if(id.trim() == 'mobileNo')
			{
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
		 
		});
		
	function getCommitteeLocations(){
		hideMembers();
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
			data : {locationType:reqLocationType} ,
		}).done(function(result){
		$("#dataLoadingsImg").hide();
		$("#dataLoadingImg").hide();
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
		var locId = $("#committeeLocationId").val();
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
		$("#designationDivId,#step1Id,#step2Id,#step3Id,#cadreDetailsDiv").hide();
		$("#committeeLocationIdErr").html("");
		$('#cadreDetailsDiv').html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		$("#searchcadrenewDiv").hide();
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
		 $("#committeeDetailsDiv").show();
		 $("#searchcadrenewDiv").hide();
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
				slickCount = slickCount+1;
				var counts = result.result;
				var members = result.hamletsOfTownship;
				var str ='<div id="variable-width'+slickCount+'">';
			   for(var i in  counts){
				str+='<div class="slick_widget">';
				str+='	<h5>'+counts[i].locationName+'</h5>';
				str+='	<ul class="list-inline text-center" >';
				if(counts[i].population!=0){
					str+='		<li class="btn btn-xs btn-default" disabled="disabled">'+counts[i].population+'</li>';
				}else{
					str+='		<li class="btn btn-xs btn-default" disabled="disabled">N/A</li>';
				}
				str+='		<li class="btn btn-xs btn-danger" disabled="disabled">'+counts[i].votesPolled+'</li>';
				if(counts[i].population!=0){
					str+='		<li class="btn btn-xs btn-success" disabled="disabled">'+counts[i].total+'</li>';
				}else{
					str+='		<li class="btn btn-xs btn-success" disabled="disabled">N/A</li>';
				}
				str+='	</ul>';
				str+='</div>';
			   }
			   $("#commitMembrsCountDiv").html(str);
			   str ="";
			   if(members != null && members.length > 0){
				   str+='<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#fff;">';
				   str+='	<tbody>';
				   for(var i in members){
					  str+=' <tr>';
					  str+=' 	<td>'+members[i].value+'</td>';
					  str+=' 	<td><img width="32" id="imagecdr'+i+'" height="32" src="http://www.mytdp.com/images/cadre_images/'+members[i].url+'" onerror="setDefaultImage(this);"/></td>';
					  str+=' 	<td>'+members[i].name+'</td>';
					  str+=' 	<td>'+members[i].type+'</td>';
					  str+=' </tr>';
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
	function setDefaultImage(img)
	{
		img.src = "images/cadreCommitee/Member_thamb_image.png";
	}
	function populateDefaultValue(level){
		 $("#cadreDetailsDiv,#step3Id,#searchcadrenewDiv,#designationDivId,#step1Id,#committeeMainId").hide();
		 $("#cadreDetailsDiv").html("");
		 if(level == 1)
			$('#committeeTypeId').val(0)
		var committeeTypeId = $('#committeeTypeId').val();

		if(level == 1){
		  $("#committeeLocationIdErr").html("");
		  $("#committeeTypeId").val(0);
		  $("#committeeDetailsDiv").hide();
		}
		if(committeeTypeId != null && committeeTypeId == 2) 
		{
			$("#committeeMainId").show();
		}
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
	}
	function getCommitteCadreMembersInfo(type){
		
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
		}
		/*if(type == 2)
		{		alert(2222);
			if($("#committeeTypeId").val() == 2){
				 if(locVal == null || locVal == 0){
					$("#afflitCommitteeIdErr").html("Please Select Affiliated Committee");
					return;
				}
			}
		}
			*/	
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

				if(result != null)				
				{
					if(result.result != null && result.result.length>0)
					{
						for(var i in result.result)
						{
							$("#committeePositionId").append('<option value="'+result.result[i].locationId+'">'+result.result[i].locationName+'</option>');
						}
						if(cadreRoleId != 0 && isFirstCadreRoleIdSettingValues)
						{
							console.log("srishailam : "+cadreRoleId);
							
							isFirstCadreRoleIdSettingValues = false;
							$("#committeePositionId").val(cadreRoleId);							
							$('#searchBy').val(defaultName);
							showSearchInfo();
							var committeTypeID = $('#committeeMngtType').val();
							if(committeTypeID == 1)
							{
								 setTimeout(function(){ 
								getCadreDetailsBySearchCriteria();}, 1000);
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
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
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
		getCommitteeLocations();
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
		str+='			<input type="text"  id="fromDateIda'+generatedId+'" key ="a'+dttCnt+'"  class="form-control fromDateCls'+cadreId+'" name="eligibleRoles['+elegbleRolCnt+'].fromDateStr">';
		str+='          <br><span id="fromDateIda'+generatedId+'Err" class="validErrCls" style="color:red;font-size:12px;"></span>'; 
		str+='	   </div>';
		str+='	   <div class="form-group col-md-2 col-md-offset-0 col-sm-4 col-xs-4">';
		str+='			<label >To Date</label>';
		str+='			<input type="text"  id="toDateIda'+generatedId+'"  class="form-control toDateCls'+cadreId+'" name="eligibleRoles['+elegbleRolCnt+'].toDateStr">';
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
	getCommitteeLocations();
	getUserLocation();
	</script>
  </body>
</html>