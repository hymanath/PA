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
	<script type="text/javascript" src="js/cadreCommittee/cadreCommitteeNew.js"></script>
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
	#committeePositionIdErr,#searchLevelErrDiv,#nonAfflitCommitteeIdErr,#mandalDivIdErr{
		font-weight:bold;
		color:red;
	}
	.m_top5{margin-top:5px;}
	.m_top30{margin-top:30px;}
	.editDesignation{margin-bottom: 10px;}
	#committeeMainId,.hideRowClass{display:none;}
	.slick-prev, .slick-next{background:none repeat scroll 0 0 #ccc !important;padding:2px 5px 5px;}
	</style>
  </head>
  <body>
		<div class="container">
		<div class="row ">
		<h3 class="text-center">${finalStatus}	</h3>		
		</div> 
		<input type="hidden" value="1" id="committeeMngtType"/>		
		<input type="hidden" value="1" id="areaTypeId"/>
		<div class="row" id="basicCommitteeDiv">

		<div class="row m_top20">
			<div class="form-group col-md-8 col-md-offset-2  col-sm-6 col-xs-6">
				<div class="form-group col-xs-12">
					<label for="committeeTypeId">COMMITTEE TYPE <span style="color:red">*</span></label>
					<select class="form-control" id="committeeTypeId" onchange="setDefault();getCommitteCadreMembersInfo(1);populateDefaultValue(2);getAllCommitteeMembersInfoInALoc();setFinalStatus();" >
						<option value="0">Select Committee Type</option>
						<option value="3">View All Committee Info</option>
						<option value="1">Main Committee</option>
						<option value="2">Affiliated Committee</option>
					</select >
					
					<div id="committeeTypeIdErr"></div>
				 </div>
			</div>
		</div>
		<div id="committeeMainId" class="row">	
			<div class="form-group col-md-8 col-md-offset-2  col-sm-6 col-xs-6">
				<div class="form-group col-xs-12">
					<label for="committeeId">AFFILIATED COMMITTEE <span style="color:red">*</span></label>
					<select class="form-control" onchange="hideMembers();getCommitteCadreMembersInfo(2)" id="afflitCommitteeId"><option value="0">Select Affiliated Committee</option></select >
					<div id="afflitCommitteeIdErr"></div>
				 </div>
			</div>			
		</div> 
		
		<div class="row">	
			<div class="col-md-12 col-sm-12 col-xs-12 text-center">
					<ul class="list-inline">
						<li><input type="button" id="viewMembrsBtn" class="btn btn-success" onclick="getCommitteMembersInfo();" value="VIEW" /></li>
						<li><input type="button" id="addMembrsBtn" class="btn btn-success" onclick="showSearchInfo();" value="ADD" /></li>
					</ul>
			</div> 
		</div>
		<div id="printDiv">		
			<div id="affiliCommitteeAllInfoDivId" class="col-md-10 col-md-offset-1 col-sm-12 col-xs-12"></div>
			<div id="elctarolInfoDivId" class="col-md-10 col-md-offset-1 col-sm-12 col-xs-12"></div>
			<div id="printBtnDiv" style="display:none" class="col-md-2 col-md-offset-5 col-sm-4 col-xs-4" >
				<input type="button" value="Print" class="btn btn-success btn-block" onclick="javascript:CallPrint('printDiv')" />
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
				<h3 class="text-success text-uppercase" >Process to Add <br> public representatives as Mandal/Municipality/Division Electoral</h3>
				
				<hr style="margin: 0px;">
			</div>

		</div>	
		<!-- end public Representatives Block  -->
		
		<!-- start mandal committee Block  -->
	
	
  
	<div class="row" id="searchcadrenewDiv">	
	
	<div class="row">
		<div style="border-top:1px solid #fff;" class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2 text-center m_top20" id="step2Id">
			<span class="badge" style="z-index: 2; margin-top: -10px;">STEP- 2</span>
	   
		</div>
	</div>
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4 id="headingDiv" class="text-uppercase"> Search Candidate For selected Designation </h4>
			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline ">
					<div class="radio">
						<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" > Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"> Mobile No &nbsp;&nbsp;</label>
					
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
						<input type="text" placeholder="ENTER MEMBERSHIP ID / VOTER ID / MOBILE NO"  class="form-control" id="searchBy">
						<div id="searchErrDiv"></div>
					</div>	
					<div class="col-md-3 col-sm-3 col-xs-3 ">
						<button class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
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
	var reqlocationId = '${locationId}';
	var reqLocationValue = '${reqLocationValue}';
	var reqLocationType ='${reqLocationType}';
	var globalLocationName ='${globalLocationName}';
	var finalStatus = '${task}';
	var commityTypeId = '${committeeTypeId}';
	var	commityId = '${committeeId}';
	var	cadreRoleId = '${result3}';
	 $("#nonafiliatedCommitteeId").append($("#nonafiliatedCommitteeId option:gt(0)").sort(function (a, b) {
              return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
          }));
	    var slickCount = 0;
		
		var pancayatId = '';
		var task = '';
		var defaultName = '';
		var mandalNewId='';
		var isFirstMandalSettingValues = true;
		var isFirstPancayatSettingValues = true;
		var isFirstCommityIdSettingValues = true;
		var isFirstCadreRoleIdSettingValues = true;
		var afflicatedCommIds=new Array();
		
		 pancayatId = '${panchayatId}';
		 
		 task = '${task}';
		 defaultName = '${result4}';
		 mandalNewId ='${mandalId}';
		
		$('document').ready(function(){
			if(finalStatus == "true"){
                $("#committeeTypeId").val(commityTypeId);
				  populateDefaultValue(2);
				if(commityTypeId == 1){
				  getCommitteCadreMembersInfo(1);
				}else if(commityTypeId == 2){
					hideMembers();
					getAffiliatedCommitsForALoc();
					
				}
	        }	
			
			
        
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
							if(finalStatus == "true"){
								finalStatus = false;
							  $("#membershipId").trigger("click");
							  $("#searchBy").val('${result4}');
							  getCadreDetailsBySearchCriteria();
							}
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
		 
		});
		
	function getCommitteeLocations(){
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
			data : {locationType:reqLocationType} ,
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
				if(finalStatus == "true" && commityTypeId == 2){
					$("#afflitCommitteeId").val(commityId);
					getCommitteCadreMembersInfo(2);
				}
			});		
	}
	if(!(finalStatus == "true")){
	  getAffiliatedCommitsForALoc();
	}
	function getCommitteMembersInfo(){
		$("#desigChangErrs").html("");
		$("#designationDivId,#step1Id,#step2Id,#step3Id,#cadreDetailsDiv").hide();
		$("#committeeLocationIdErr").html("");
		$('#cadreDetailsDiv').html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		$("#searchcadrenewDiv").hide();
		
		var locVal = $("#afflitCommitteeId").val();
		var reqNLocationValue = reqLocationValue;
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		if($("#committeeTypeId").val() == 2){
			 if(locVal == null || locVal == 0){
				$("#afflitCommitteeIdErr").html("Please Select Affiliated Committee");
				return;
			}
			reqNLocationValue = $("#afflitCommitteeId").val();
		 }
		 $("#committeeDetailsDiv").show();
		 $("#searchcadrenewDiv").hide();
		 $("#commitMembrsCountDiv").html('<center><img src="images/icons/loading.gif"  /></center>');
		 $("#committeeMmbrsMainDiv").html("");
		 var reqCommitteeType = "main";
		 var title ="MAIN COMMITTEE";
		 if($("#committeeTypeId").val() == 2){
			 reqCommitteeType = "affiliated";
			 title =$.trim($("#afflitCommitteeId option:selected").text())+" COMMITTEE";
		 }
		 $("#affComitteeMainTitle").html(title.toUpperCase());
		 $("#viewMembrsBtn").attr("disabled","disabled");
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoAction.action",
				data : {locationType:reqLocationType,locationValue:reqNLocationValue,committeeType:reqCommitteeType} ,
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
				if(counts[i].population != null && counts[i].population!=0){
					str+='		<li class="btn btn-xs btn-default" disabled="disabled">'+counts[i].population+'</li>';
				}else{
					str+='		<li class="btn btn-xs btn-default" disabled="disabled">N/A</li>';
				}
				if(counts[i].votesPolled != null && counts[i].votesPolled != 0 )
					str+='		<li class="btn btn-xs btn-danger" disabled="disabled">'+counts[i].votesPolled+'</li>';
				else{
					if(counts[i].finalizedCount != null && counts[i].finalizedCount != 0 )
						str+='		<li class="btn btn-xs btn-danger" disabled="disabled">'+counts[i].finalizedCount+'</li>';
					else
						str+='		<li class="btn btn-xs btn-danger" disabled="disabled">N/A</li>';
				}
				
				if(counts[i].population != null &&  counts[i].population!=0){
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
				   if(result.locationName != null && result.locationName == "N"){
				      str+='<div class="text-right editDesignation"><input type="button" value="EDIT DESIGNATION" onclick="showEditInfo();" class="btn btn-success" /></div>';
				   }
				   str+='<table class="table table-bordered text-left" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); color:#000;">';
				   str+='<thead style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.2); color:#000">';
					 
                   str+='<th>Designation</th>';
                   str+='<th>Image</th>';
                   str+='<th>Name</th>';
				   str+='<th>Enrollment No</th>';
                   str+='<th  class="hideRowClass">Edit Designation</th>';
				   str+='<th  class="hideRowClass">Update</th>';
				   str+='</thead>';
				   str+='<tbody >';
				   str+='	<tbody>';
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
					  str+=' 	</select></td>';
					  if(x == 0){
					    str+=' 	<td class="hideRowClass" rowspan="'+members.length+'"><div class="hideRowClass"><input type="button" value="UPDATE DESIGNATION" onclick="updateExistingDesig('+result.population+');" class="btn btn-success" /></div><br><div id="desigChangErrs"></div></td>';
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
	
	
	
	function setDefaultImage(img)
	{
		img.src = "images/cadreCommitee/Member_thamb_image.png";
	}
	function populateDefaultValue(level){
		$("#affiliCommitteeAllInfoDivId").html("");
	    $("#committeeTypeIdErr").html("");
		$("#addMembrsBtn").show();
		$("#viewMembrsBtn").show();
		 $("#cadreDetailsDiv,#step3Id,#searchcadrenewDiv,#designationDivId,#step1Id,#committeeMainId").hide();
		 $("#cadreDetailsDiv").html("");
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
		
	}
	function getCommitteCadreMembersInfo(type){
		if(type == 1){
			if(!($("#committeeTypeId").val() == 1)){
				return;
			}
		}
		 var reqCommitteeType = "main";
		 var title ="MAIN COMMITTEE";
		 var reqNLocationValue = reqLocationValue;
		 if($("#committeeTypeId").val() == 2){
			 reqCommitteeType = "affiliated";
			 title =$.trim($("#afflitCommitteeId option:selected").text())+" COMMITTEE";
			 
		 }
		 if(type == 2){
			 if($("#afflitCommitteeId").val() == null || $("#afflitCommitteeId").val() == 0){
				 return;
			 }
			 reqNLocationValue = $("#afflitCommitteeId").val();
		 }
		 $("#affComitteeMainTitle").html(title.toUpperCase());
		 $("#committeePositionId  option").remove();
		 $("#committeePositionId").append('<option value="0">Select Designation</option>');
		  $.ajax({
				type : "POST",
				url : "getCommitteMembersInfoAction.action",
				data : {locationType:reqLocationType,locationValue:reqNLocationValue,committeeType:reqCommitteeType} ,
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
							$("#committeePositionId").append('<option value="'+result.result[i].locationId+'">'+result.result[i].locationName+'</option>');
						}
						
						if(finalStatus == "true"){
							showSearchInfo();
							$("#committeePositionId").val('${result3}');
							 $("#committeePositionId").trigger("change");
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
		if($("#committeeTypeId").val() == 0){
			
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
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
		 
		var locVal = $("#afflitCommitteeId").val();
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
	

	
	
	function showEditInfo(){
		$("#desigChangErrs").html("");
		$(".hideRowClass").each(function(){
			$(this).show();
		});
		
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
	
	
	
	
	 function getAllCommitteeMembersInfoInALoc(){
		 if(!($("#committeeTypeId").val() == 3)){
			 return;
		 }
		  $("#viewMembrsBtn").hide();
			$("#addMembrsBtn").hide();
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
					str+='<table class="table table-bordered" style="borde:1 solid #000;background-color:rgba(0,0,0,0.1);"><thead style="background-color:rgba(0,0,0,0.2);"><tr><th style="width:15%">Committee Name</th><th style="width:15%">Designation</th><th style="width:5%">Image</th><th style="width:38%">Name</th><th style="width:27%">Enrolment Number</th></tr></thead>';
					str+='<tbody>';
					
					for(var i in members){
					  str+=' <tr>';
					  str+='<td>'+members[i].mandalName+'</td>'
					  str+=' <td>'+members[i].value+'</td>';
					  str+='<td><img width="32" id="imagecdr'+i+'" height="32" src="images/cadre_images/'+members[i].url+'" onerror="setDefaultImage(this);"/></td>';
					  str+=' 	<td>'+members[i].name+'</td>';
					  str+='<td>'+members[i].type+'</td>';
					  str+='</tr>';
				   }
					
					str+='</tbody></table>';
				 }else{
					 str+='<center><div style="margin-bottom:10px;"><b>No Data Available</b></div></center>';
				 }
				 $("#affiliCommitteeAllInfoDivId").html(str);
			});
	}

	
	function CallPrint(strid) {
            var prtContent = document.getElementById(strid);
            var WinPrint = window.open();
            WinPrint.document.write(prtContent.innerHTML);
            WinPrint.document.close();
            WinPrint.focus();
            WinPrint.print();
            //WinPrint.close();
        }
	
	
      
function setFinalStatus(){
	  finalStatus == "false";
	  
}
function setDefault()
{
	
	$("#afflitCommitteeId").val(0);
	$("#afflitCommitteeIdErr").html("");
	$("#committeeDetailsDiv").hide();
}
	</script>
  </body>
</html>