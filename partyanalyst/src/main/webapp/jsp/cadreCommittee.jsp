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
   	
	<style>
	#publicrepresantative,#mandalaffiliated,#advancedSearchDiv,#committeeDetailsDiv,#searchcadrenewDiv,#committeLocationDiv
	{
		display:none;
	}
	#noMembersErr{
	  font-weight:bold;	  
	}
	#committeeLocationIdErr,#committeeTypeIdErr,#afflitCommitteeIdErr,#searchErrDiv,#committeLocationIdErr{
		font-weight:bold;
		color:red;
	}
	</style>
	<script>
		var ageRangeArr = new Array();	
			<c:forEach var="election" items="${ageRangeList}">
				var elections ={
				name:'${election}'
				}
				ageRangeArr.push(elections);
			</c:forEach>
	</script>
  </head>
  <body>
	<div class="container-fluid">
		<div class="row" style="align:center;padding:10px;background:rgba(255,0,51,0.8); border-top:12px solid rgba(19,167,81,0.8);border-bottom:12px solid rgba(19,167,81,0.8);display:flex">
		 	<div class="col-md-12 col-sm-12 col-xs-12 text-center">
				<img src="images/cadreCommitee/committee_logo.png" title="Committee Logo" alt="committee" />
			</div>
		</div>
		<div class="row m_top20">
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12" >
				<div class="row" >
					<div class="col-md-4 col-md-offset-0 col-sm-4 col-xs-4" >
						<a class="btn btn-success btn-block arrow_selected" id="basicCommitteeTab" href="javascript:{showAndHideTabs('basicCommitteeDiv');getCommitteeLocations();}">Committee <br>Management</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4">
						<a class="btn btn-success btn-block "   id="publicrepresantativeTab" href="javascript:{showAndHideTabs('publicrepresantative');}">Public represantative <br>Electoral Management</a>
					</div>
					<div class="col-md-4 col-sm-4 col-xs-4">
						<a class="btn btn-success btn-block "  id="mandalaffiliatedTab" href="javascript:{showAndHideTabs('mandalaffiliated');}" >Mandal affiliated <br>Electoral Management</a>				
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
				    <input type="radio" name="committeeType" onclick="validateSearchType('1');getCommitteeLocations();" checked="true" value="1" id="villageId"> Village / Ward / Division
				  </label>
			    </div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-6">
				<div class="radio">
				  <label>
				    <input type="radio" id="mndlLvlCommittSelec" name="committeeType" onclick="validateSearchType('2');getCommitteeLocations();" value="2"> Mandal / Town / GHMC 
				  </label>
			    </div>
			</div>
			
		</div> 
		
		<div class="row m_top20">
			<div class="col-md-4 col-md-offset-2  col-sm-6 col-xs-6 ">
				<div class="form-group col-xs-12 pull-right">
					<label for="committeeLocationId">SELECT LOCATION</label>
					<select onchange="populateDefaultValue(1);" class="form-control" id="committeeLocationId" ><option value="0">Select Location</option></select >
					<div id="committeeLocationIdErr"></div>
				 </div>
			</div>
			<div class="col-md-4 col-sm-6 col-xs-6">
				<div class="form-group col-xs-12">
					<label for="committeeTypeId">COMMITTEE TYPE</label>
					<select class="form-control" id="committeeTypeId" onchange="getAffiliatedCommitsForALoc();populateDefaultValue(2);" ><option value="0">Select Committee Type</option><option value="1">Main Committee</option><option value="2">Affiliated Committee</option></select >
					<div id="committeeTypeIdErr"></div>
				 </div>
			</div>
		</div>
		
		<div id="committeeMainId" class="row">	
			<div class="col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-2 col-xs-12 ">
				<div class="form-group col-xs-12">
					<label for="committeeId">AFFILIATED COMMITTEE</label>
					<select class="form-control" onchange="hideMembers();" id="afflitCommitteeId"><option>Select Affiliated Committee</option></select >
					<div id="afflitCommitteeIdErr"></div>
				 </div>
			</div>			
		</div> 
		
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
					<li><span style="color:#C9302C;font-weight:bold;">APPLIED </span></li>
					<li><span style="color:#449D44;font-weight:bold;">AVAIL</span></li>
				</ul>
				
			</div>			
			
			<div class="col-md-8 col-md-offset-2 col-xs-10 col-xs-offset-1 ">
				<div class="variable-width" id="commitMembrsCountDiv"></div>
			</div>
		
			<div id="committeeMmbrsMainDiv" class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-xs-12 col-xs-offset-0 text-center"></div>
		</div>
		<!-------/VIEW BLOCK END------>
		
		<!-------ADD BLOCK------>		
	</div>		
		<!-------/ADD BLOCK END------>
		
		<!-- start public Representatives Block  -->
		
		<div class="row" id="publicrepresantative" >	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1  m_top20 text-center">
				<h3 class="text-success text-uppercase" >Process to update <br> public representatives as mandal Electoral</h3>
				
				<hr style="margin: 0px;">
			</div>

		</div>	
		<!-- end public Representatives Block  -->
		
		<!-- start mandal committee Block  -->
		
		<div class="container-fluid" id="mandalaffiliated">
				
		<div class="row m_top20">
			<div class="com-md-8 col-sm-12 col-xs-12 text-center">
				<h3 class="text-success text-uppercase">Process to add NOT affiliated committee member <br>as mandal affiliated electoral</h3>
			</div>
		</div>
	

	</div>
	<div class="row" id="searchcadrenewDiv">	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4>SEARCH BASED ON</h4>
				<hr style="margin: 0px;">
			</div>
			<div class="row m_top20" id="committeLocationDiv" >
			<div class="col-md-4 col-md-offset-4  col-sm-6 col-xs-6  m_top20">
				<div class="form-group col-xs-12 pull-right">
					<label for="committeLocationId">SELECT LOCATION</label>
					<select class="form-control" id="committeLocationId" ><option value="0">Select Location</option></select >
					<div id="committeLocationIdErr"></div>
				 </div>
			</div>			
		</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline m_top20">
					<div class="radio">
						<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  value="2" > Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  value="3"> Mobile No &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  value="4"> Name &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="advancedSearch"  value="5"> Advanced Search &nbsp;&nbsp;</label>
						<input type="hidden" id="cadreSearchType" value="membershipId" />
					</div>				  
				</div>
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20"  id="basicSearchDiv">	
				<div class="row">      
					<div class="col-md-9 col-sm-9 col-xs-9 ">
						<input type="text" placeholder="ENTER NO / NAME"  class="form-control" id="searchBy">
						<div id="searchErrDiv"></div>
					</div>	
					<div class="col-md-3 col-sm-3 col-xs-3 ">
						<button class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
					</div>			
				</div>			
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20" id="advancedSearchDiv">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<h6>Advanced Search</h6>
					<div class="row">					
						<div class="col-md-2 col-sm-2 col-xs-2 ">
							<label>Caste-Group
								<select class="form-control" id="casteCategory" onchange="casteDetailsByGroupId();">
								<option value="0">All</option>
								<option value="1">OC</option>
								<option value="2">BC</option>
								<option value="3">SC</option>
								<option value="4">ST</option>
								</select>
							</label>
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							<label>Caste Name
								
								<s:select theme="simple" cssClass="form-control editClass" id="casteList" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Caste " style="width: 210px;"/>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Age
								<select class="form-control"  id="ageRange"><option>select</option></select>
							</label>
						</div>
						<div class="col-md-3 col-sm-3 col-xs-3 ">
							<label>Gender
								<select class="form-control"  id="gender"><option>Male</option><option>FeMale</option></select>
							</label>
						</div>
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
						</div>				
					</div>			
				</div>			
			</div>	
			
			<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-left: 660px;margin-top: 20px;width:70px;height:60px;display:none;"/>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" id="cadreDetailsDiv"></div>			
			</div>
		</div>	
		<!-- end mandal committee Block  -->

		
	</div>

	<script>	
        var slickCount = 0;	
		$('.searchTypeCls').click(function(){
			var id = $(this).attr('id');
			$('#advancedSearchDiv').hide();
			$('#basicSearchDiv').show();
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
				$('#cadreSearchType').val('name');
			}
			if(id.trim() == 'advancedSearch')
			{
				$('#basicSearchDiv').hide();
				$('#advancedSearchDiv').show();
				$('#cadreSearchType').val('advancedSearch');
				$('#ageRange').find('option').remove();
				//$('#ageRange').append('<option  value="0"> Select Age </option>');
				if(ageRangeArr != null && ageRangeArr.length>0)
				{

					for(var i in ageRangeArr)
					{
						$('#ageRange').append('<option  value="'+ageRangeArr[i].name+'">'+ageRangeArr[i].name+'</option>');
					}
				}
			}
		});
	function getCommitteeLocations(){
		hideMembers();
		$("#committeeTypeId").val(0);
		$("#committeeLocationIdErr").html("");
		$("#committeeTypeIdErr").html("");
		$("#afflitCommitteeIdErr").html("");
		$("#committeeMainId").hide();
		var reqLocationType ="";
		if($("#mndlLvlCommittSelec").is(':checked')){
		  reqLocationType ="mandal";
		}
		$.ajax({
			type : "POST",
			url : "getCommitteLocationsAction.action",
			data : {locationType:reqLocationType} ,
		}).done(function(result){
			$("#committeeLocationId  option").remove();
			$("#committeeLocationId").append('<option value="0">Select Location</option>');
			
			$("#committeLocationId  option").remove();
			$("#committeLocationId").append('<option value="0">Select Location</option>');
			
			var reqNewLocationType ="";
			if($("#mndlLvlCommittSelec").is(':checked')){
			  reqNewLocationType ="mandal";
			}
			if(reqNewLocationType == reqLocationType){
				for(var i in result){
				   $("#committeeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				   $("#committeLocationId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
				}
			}
		});
	}
	function getAffiliatedCommitsForALoc(){
		$("#committeeDetailsDiv").hide();
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
			if($("#mndlLvlCommittSelec").is(':checked')){
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
			});
		}else{
			$("#committeeMainId").hide();
		}
		
	}
	function showSearchInfo(){
		 $("#committeeDetailsDiv").hide();
		 $("#searchcadrenewDiv").show();
	}
	function getCommitteMembersInfo(){
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
				$("#viewMembrsBtn").removeAttr("disabled");
				slickCount = slickCount+1;
				var counts = result.result;
				var members = result.hamletsOfTownship;
				var str ='<div id="variable-width'+slickCount+'">';
			   for(var i in  counts){
				str+='<div class="slick_widget">';
				str+='	<h5>'+counts[i].locationName+'</h5>';
				str+='	<ul class="list-inline text-center" >';
				str+='		<li class="btn btn-xs btn-default" disabled="disabled">'+counts[i].population+'</li>';
				str+='		<li class="btn btn-xs btn-danger" disabled="disabled">'+counts[i].votesPolled+'</li>';
				str+='		<li class="btn btn-xs btn-success" disabled="disabled">'+counts[i].total+'</li>';
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
					  str+=' 	<td><img width="32" id="imagecdr'+i+'" height="32" src="images/cadre_images/'+members[i].url+'" onerror="setDefaultImage(this);"/></td>';
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
					  autoplay: true,
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
		if(level == 1){
		  $("#committeeLocationIdErr").html("");
		  $("#committeeTypeId").val(0);
		  $("#committeeDetailsDiv").hide();
		}
		$("#afflitCommitteeId  option").remove();
		$("#afflitCommitteeId").append('<option value="0">Select Affiliated Committee</option>');
	}
	function hideMembers(){
		$("#committeeDetailsDiv").hide();
	}
	getCommitteeLocations();
	</script>
  </body>
</html>