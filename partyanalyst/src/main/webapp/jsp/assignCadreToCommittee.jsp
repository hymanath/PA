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
		
	</style>

  </head>
  <body>
  
	<div class="container-fluid">
		<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-12 col-sm-12 col-xs-12 text-center">
				<img src="images/cadreCommitee/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
			
			
	
		</div>
	
	<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center m_top20 alert alert-success successDiv" >
	<b>  PROFILE UPDATED SUCCESSFULLY... </b>
	</div>
		<s:if test="%{committeeMngtType == 1}">
	
		<div id="assignCommitteeDiv" >
			<div class="row " >
				<div class="col-md-3 col-md-offset-3  col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0 m_top20" >
					<div class="form-group">
						<label for="exampleInputEmail1">SELECT LOCATION</label>
						<select  class="form-control" id="committeeLocationId" name="eligibleRoles[0].cadreCommitteeLevelValue"><option>Location </option></select >
						<input type="hidden" value="${task}" name="eligibleRoles[0].cadreCommitteeLevelId"/>
						<div id="committeeLocationIdErr"></div>
					 </div>
				</div>
				<div class="col-md-3  col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0 m_top20">
					<div class="form-group">
						<label for="exampleInputEmail1">COMMITTEE TYPE</label>
						<select class="form-control" id="committeeTypeId" onchange="getAffiliatedCommitsForALoc();populateDefaultValue(2);getCommitteCadreMembersInfo(1)" name="eligibleRoles[0].cadreCommitteeTypeId"><option value="0">Select Committee Type</option><option value="1">Main Committee</option><option value="2">Affiliated Committee</option></select >
					<div id="committeeTypeIdErr"></div>
					 </div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 col-md-offset-3  col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0 " id="committeeDetailsDiv">
					<label for="exampleInputEmail1">AFFILIATED COMMITTEE NAME</label>
					<select class="form-control" id="afflitCommitteeId" onchange="getCommitteCadreMembersInfo(2)" name="eligibleRoles[0].cadreCommitteeId"><option>Select Affiliated Committee</option></select >
					<div id="afflitCommitteeIdErr"></div>
				 </div>
		
				<div class="col-md-3   col-sm-6 col-sm-offset-0 col-xs-6 col-xs-offset-0">
					<label for="exampleInputEmail1">COMMITTEE POSITION</label>
					<select  class="form-control" id="committeePositionId"  name="eligibleRoles[0].cadreRoleId"><option>POSITION </option></select >
				</div>
			</div>
			
			<div class="col-md-8 col-md-offset-2   col-sm-12 col-xs-12 m_top20 text-center updateProfileDivId">
				<button class="btn btn-success btn-block btn-lg m_top20" id="assignCommitte" type="button" onClick="assignCommitte();"> ASSIGN COMMITTEE </button>
			</div>		
			
		</div>		
	</s:if>
	</div> 
	
	<footer class="text-center m_top20">
			&copy; 2015 Telugu Desam Party
	</footer>

   
	<script>	
	var areaType = 1;
	var pancayatId = '';
	var commityTypeId = '';
	var commityId = '';
	var cadreRoleId = '0';
	var isFirstPancayatSettingValues = true;
	var isFirstCommityIdSettingValues = true;
	var isFirstCadreRoleIdSettingValues = true;
	
	 pancayatId = ${panchayatId};
	 commityTypeId = ${committeeTypeId};
	 commityId = ${committeeId};
	 cadreRoleId = ${result3};
		 
	$('document').ready(function(){
		
		getCommitteeLocations();
		});	
	function getCommitteeLocations()
	{

		var reqLocationType ="";
		
		if(areaType == 2){
		  reqLocationType ="mandal";
		}
		$.ajax({
			type : "POST",
			url : "getCommitteLocationsAction.action",
			data : {locationType:reqLocationType} ,
		}).done(function(result){
			$("#committeeLocationId  option").remove();
			$("#committeeLocationId").append('<option value="0">Select Location</option>');
			var reqNewLocationType ="";
			if(areaType == 2){
			  reqNewLocationType ="mandal";
			}
			if(reqNewLocationType == reqLocationType){
				for(var i in result){
				   $("#committeeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
				if(isFirstPancayatSettingValues)
				{
					isFirstPancayatSettingValues = false;
					$("#committeeLocationId").val(parseInt(${panchayatId}));
					 $("#committeeTypeId").val(parseInt(${committeeTypeId}));
					 getAffiliatedCadreCommitsForALoc();
					 populateDefaultValue(2);
					 getCommitteMembersInfo(1);
				}
				 
			}
		})
	}
	
	function getAffiliatedCadreCommitsForALoc(){
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		var locId = $("#committeeLocationId").val();
		if(locId == null || locId == 0){
			$("#committeeLocationIdErr").html("Please Select Location");
			return;
		}
		if($("#committeeTypeId").val() == 0){
			$("#committeeTypeIdErr").html("Please Select Committee Type");
			return;
		}
		if($("#committeeTypeId").val() == 2){
			$("#committeeMainId").show();
			var reqLocationType = "";
			var reqLocationValue = "";
			if(areaType == 2){
			  reqLocationType ="mandal";
			}
			reqLocationValue=$("#committeeLocationId").val();
			$.ajax({
				type : "POST",
				url : "getAllAffiliatedCommittiesAction.action",
				data : {locationType:reqLocationType,locationValue:reqLocationValue} ,
			}).done(function(result){
				$("#afflitCommitteeId  option").remove();
				$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
				for(var i in result){
				   $("#afflitCommitteeId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}	
				
				if(isFirstCommityIdSettingValues)
				{
					isFirstCommityIdSettingValues = false;
					$('#afflitCommitteeId').val(commityId);
					getCommitteMembersInfo(2);
				}
			});
		}else{
			$("#committeeMainId").hide();
		}
		
	}
	function populateDefaultValue(level){
		if(level == 1){
		  $("#committeeLocationIdErr").html("");
		  $("#committeeTypeId").val(0);
		  $("#committeeDetailsDiv").hide();
		}
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
	}
	
	function getCommitteMembersInfo(type){
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
		if(type == 2)
		{		
			if($("#committeeTypeId").val() == 2){
				 if(locVal == null || locVal == 0){
					$("#afflitCommitteeIdErr").html("Please Select Affiliated Committee");
					return;
				}
			}
		}
		
		 $("#committeeDetailsDiv").show();
		 //$("#searchcadrenewDiv").hide();
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
		   if(areaType == 2){
		     reqLocationType ="mandal";
		   }
		   reqLocationValue=$("#committeeLocationId").val();
		 }else{
			 reqLocationValue=$("#afflitCommitteeId").val();
		 }
		 $("#committeePositionId  option").remove();
		 $("#committeePositionId").append('<option value="0">Select Position</option>');
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
							$("#committeePositionId").append('<option value="'+result.result[i].locationId+'">'+result.result[i].locationName+'</option>');
						
						if(isFirstCadreRoleIdSettingValues)
						{
							isFirstCadreRoleIdSettingValues = false;							
							$("#committeePositionId").val(cadreRoleId)
						}
					}
				}
		    });
	}
	
	function assignCommitte()
	{
		
		var tdpCadreID = ${tdpCadreId};
		var roleId = $('#committeePositionId').val();
		
		var jsObj =	{
				tdpCadreId : tdpCadreID,
				roleId : roleId
			}
		
			$.ajax(
			  {
					type: "POST",
					url:"assigntdpCadreToCommittee.action",
					data:{task :JSON.stringify(jsObj)}
			  }
			  ).done(function(result){
				  if( result != null)
				  {
					$('.successDiv').html('<b> '+result.message+'</b>');
					$('#assignCommitteeDiv').hide();
				  }
			  });
		
	}
	
	</script>
  </body>
</html>