<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Cadre Search </title>

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
	<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
	<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination.css"/>
	

	<style>
	#publicrepresantative,#mandalaffiliated,#advancedSearchDiv,#committeeDetailsDiv,#searchcadrenewDiv,#committeLocationsDiv,
	/*#designationDivId,#step1Id,#step2Id,#step3Id,#cadreDetailsDiv
	{
		display:none;
	}*/
	
	#noMembersErr{
	  font-weight:bold;	  
	}
	#committeeLocationIdErr,#committeeTypeIdErr,#afflitCommitteeIdErr,#searchErrDiv,#committeLocationIdErr,#advancedSearchErrDiv,
	#committeePositionIdErr,#searchLevelErrDiv,#nonAfflitCommitteeIdErr,#mandalDivIdErr{
		font-weight:bold;
		color:red;
	}
	.membrshipAndMobileNoCls{
		font-weight:bold;
		color:green;
	}
	.m_top5{margin-top:5px;}
	.m_top30{margin-top:30px;}
	.editDesignation{margin-bottom: 10px;}
	
	/*.hideRowClass{display:none;}*/
	.paginate_disabled_previous,.paginate_enabled_previous,.paginate_enabled_next{
   padding-bottom: 10px;
}
.prev, .next {width:50px !important}
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
			<!-------ADD BLOCK------>

	<div class="row m_top20" id="searchcadrenewDiv">	
	
	
			<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
				<h4 id="headingDiv" class="text-uppercase"> Search Cadre</h4>
			
			</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0">
			<c:if test="${sessionScope.USER.accessType == 'STATE'}">
				 <div class="col-md-8 col-md-offset-2 col-sm-11 col-sm-offset-0 col-xs-11 col-xs-offset-0" id="statedisplaydivid">
						<label>State</label>
						<c:if test="${sessionScope.USER.stateName == 'both'}">
						  <select id="statesDivId" onchange="getDistrictsForStates(this.value);" class="form-control">
							<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="36">Telangana</option>
						  </select>
						  </c:if>
						  <c:if test="${sessionScope.USER.stateName == 'TS'}">
						  <select id="statesDivId" onchange="getDistrictsForStates(this.value);" class="form-control">
							<option value="36">Telangana</option>
						  </select>
						  </c:if>
						   <c:if test="${sessionScope.USER.stateName == 'AP'}">
						  <select id="statesDivId" onchange="getDistrictsForStates(this.value);" class="form-control">
							<option value="1">AndhraPradesh</option>
						  </select>
						  </c:if>
						
				     </div>
					 </c:if>
				
					<!--<img style="float: right; margin-right: 308px; display: none;" alt="Processing Image" src="./images/icons/search.gif" id="constiImge">-->
						<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForDist" style="margin-top: 30px;width:20px;height:20px;display:none;"/>
					
					<div class="col-md-8 col-md-offset-2 col-sm-1 col-sm-offset-0 col-xs-11 col-xs-offset-0" id="districtDiv">
							<label>District</label>
							<select class="form-control " id="districtId" class="form-control" onchange="getConstituenciesForDistricts(this.value)">
							</select>
					</div>
					
						<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForConst" style="margin-top: 30px;width:20px;height:20px;display:none;"/>
					
				   <div class="col-md-8 col-md-offset-2 col-sm-11 col-sm-offset-0 col-xs-11 col-xs-offset-0" id="constitunecyDiv">
							<label>Constituency</label>
							<select class="form-control " id="constituencyId" class="form-control" onchange="getMandalCorporationsByConstituency()">
							<option value="0"> Select Constituency </option>
							</select>
					</div>
					
						<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForMandl" style="margin-top: 30px;width:20px;height:20px;display:none;"/>
			
					<div class="col-md-8 col-md-offset-2 col-sm-11 col-sm-offset-0 col-xs-11 col-xs-offset-0" style="padding-top: 10px" id="mandalDiv">
							<label>Mandal/Municipality</label>
							<select class="form-control " id="mandalList" class="form-control" onchange="getPanchayatWardByMandal();">
							<option value="0"> Select Mandal/Municipality </option>
							</select>
					</div>
				
						<img src='./images/icons/search.gif' class="offset7"  id="searchDataImgForPanc" style="margin-top: 30px;width:20px;height:20px;display:none;"/>
					
					<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0" style="padding-top: 10px" id="panchayatDiv">
							<label>Panchayat</label>
							<select class="form-control " id="panchaytList" class="form-control" onchange="getAllCadreInPanchayat()">
							<option value="0"> Select Panchayat </option>
							</select>
					</div>
			</div>
			
			<div class="col-md-6 col-md-offset-3  text-center" id="removeCadreCheckBoxId" style="margin-top:10px;">
  
			  <span class=" btn btn-primary btn-xs form-inline ">
					<label class="radio"><input type="checkbox" style="vertical-align: text-bottom;" id="onlyCandidatesId"/> Click here to search only removed cadre details. </label>
					
			  </span>
			</div>
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center" style="padding-top: 15px; padding-bottom: 15px;">
				<div class="form-inline btn btn-default">
					<span class="allcls" style="display:none">
							   <label style="font-weight: 100;"> <input type="radio" name="searchBasedOn" value="0" id="allId" title="click here to get all cadres in panchayat" style="cursor:pointer;" class="searchTypeCls" onclick="getPanchayayCadreDetailsBySearchCriteria(0)"/>All &nbsp;&nbsp;
							   </label>
					</span>	
				   
					<div class="radio">
						<!--<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="allId" value="0"> All &nbsp;&nbsp;</label>-->
					
						<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1">  MEMBERSHIP ID    &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" >  VOTER ID    &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"> MOBILE NO &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"> NAME &nbsp;&nbsp;</label>
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="trNo"  onclick="refreshExistingDetails();"  value="5">  TR NO &nbsp;&nbsp;</label><br>
						
						<!--<label style="margin-left: -250px;"><input type="radio" name="searchBasedOn" class="searchTypeCls" id="membershipIdAndMobileNo"  onclick="rfrshEstngDtlsForMembrShipNoAndMblNo();"  value="6"> MembershipID, MobileNo &nbsp;&nbsp;</label>-->
						<!--<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="advancedSearch"  onclick="refreshExistingDetails();"  value="5"> Advanced Search &nbsp;&nbsp;</label>-->
						
						<input type="hidden" id="cadreSearchType" value="membershipId" />
					</div>				  
				</div>
			</div>
			
			<!--<div class="col-md-6 col-md-offset-3  text-center" id="removeCadreCheckBoxId">
  
			  <span class=" btn btn-primary btn-xs form-inline ">
					<label class="radio"><input type="checkbox" style="vertical-align: text-bottom;" id="onlyCandidatesId"/> Click here to search only removed cadre details. </label>
					
			  </span>
			</div>-->
			
			<!--<div class="row m_top20" id="committeLocationsDiv" >
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
			</div>-->
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0"  id="basicSearchDiv" style="margin-top:10px;">	
				<div class="row-fluid">      
					<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0">
						<input type="text" placeholder="ENTER MEMBERSHIP ID / VOTER ID / MOBILE NO / NAME"  class="form-control" id="searchBy">
						<div id="searchErrDiv"></div>
						<div id="membrshipAndMobileNoDiv" class="membrshipAndMobileNoCls"></div>
					</div>	
					<!--<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0">
						<button class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
					</div>	-->		
				</div>			
			</div>
			
			<!--<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20" id="advancedSearchDiv" style="display:none;">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<h6>Advanced Search</h6>
					<div id="advancedSearchErrDiv"></div>
					<div class="row">
						
						<div class="col-md-4 col-sm-4 col-xs-12 ">
							<label>Caste-Group</label>
								<select class="form-control" id="casteCategory" onchange="casteDetailsByGroupId();">
								<option value="0" selected>All</option>
								<option value="1">OC</option>
								<option value="2">BC</option>
								<option value="3">SC</option>
								<option value="4">ST</option>
								</select>
							
						</div>
						<div class="col-md-4 col-sm-4 col-xs-12">
							<label>Caste Name</label>
								
								<s:select theme="simple" cssClass="form-control editClass col-md-12 col-sm-12 col-xs-12" id="casteList" list="genericVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Caste " style="width: 200px;"/>
							
						</div>
						
						<div class="col-md-4 col-sm-4 col-xs-12 ">
							<label>Age Range</label>
								<select class="form-control"  id="ageRange" onchange="clearbetwbAgeFields()" ><option>select</option></select>
							
						</div>
						<div class="col-md-4 col-sm-4 col-xs-4 ">
							 <b>Between Age</b>
							 <div class="row">
								<div class="col-xs-6">
								<input type="text" id="fromAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" From "/> 
								</div>
								<div class="col-xs-6"> <input type="text" id="toAgeId" style="width: 50px;" class="ageRangeCls" placeholder=" To  "/> 
								</div>
							</div>
						</div>
						
						<div class="col-md-4 col-sm-4 col-xs-12">
							<label>Gender</label>
								<select class="form-control"  id="gender"><option value="0" selected>All</option><option  value="1" >Male</option><option  value="2" >Female</option></select>
							
						</div>
									
					</div>	
									
				</div>			
			</div>-->
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
						<div class="row-fluid"><div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 ">
						<input id="searchbtn" class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria(0)" value="SEARCH"></input></div>
					</div>	
					</div>
			</div>
				<div class="row">
	
			
			<img src='images/icons/cadreSearch.gif' class="offset7"  id="searchDataImg" style=" margin-left: 400px;margin-top: 20px;width:250px;height:200px;display:none;"/>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div id="topPaginationDivId" class="paginationDivId"></div>
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;display:none;" id="cadreDetailsDiv"></div>
				<div id="paginationDivId"  class="paginationDivId"></div>

			</div>
		</div>	
			</div>
			<!-- hidden variable -->
			<input type="hidden" value="" id="hiddenCadreId"/>
			
			<!-- Modal for Remove cadre -->
			<div class="modal fade" id="removeModalDivId">
			  <div class="modal-dialog modal-sm">
				<div class="modal-content">
				  <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="removeModalTitleId" style="color:red;">Removing Cadre</h4>
				  </div>
				  <div class="modal-body" id="ramoveModalBodyDivId">
					<div id="errorDivId" style="color:red"></div>
					<div id="successDivId"></div>
					<div class="row">
						<div class="col-md-12">
							<div><b>Cadre Name :</b> <span id="cadreName"></span></div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Reason <span style="color:red">*</span>:</b>
								<select id="reasonSelectId" class="form-control">
									<option value="0">Select Reason</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 m_top10">
							<div><b>Remark <span style="color:red">*</span>:</b> 
							<textarea class="form-control" id="remarkTextAreaId"></textarea></div>
						</div>
					</div>
					</div>
				  <div class="modal-footer">
					<button type="button" class="btn btn-primary btn-sm" id="saveRemovingCadreDetailsId">Remove</button>
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Close</button>
				  </div>
				</div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			
			<script>
	var accessType = "${sessionScope.USER.accessType}";
	var accessValue = "${sessionScope.USER.accessValue}";
	var accessState = "${sessionScope.USER.stateName}";
         $(document).keypress(function(e) {
				if(e.keyCode==13){
					 getCadreDetailsBySearchCriteria(0);
				}
		  });
		  
		//alert(accessType);
		if(accessType == "DISTRICT" || accessType == "MP"){
			$("#districtDiv").hide();
		} 
		  
			function getCadreDetailsBySearchCriteria(startIndex)
		{
		//committeTypeID means 
			//for committiee management 1
			//for Mandal/Muncipality Main Committee Electoral Management 2
			//for Mandal/Muncipality Affiliated Committee Electoral Management 3
		//areaTypeId means
		    //for panchayat level 1
			//for mandal level 2
			
		
		var locationLevel = 0;
		var locationValue = 0;
		var searchName = '';
		var mobileNo = '';
		var casteCategory = '';
		var casteStateId = 0;
		var fromAge = 0;
		var toAge = 0;
		var memberShipCardNo = '';
		var trNumber = '';
		var voterCardNo = '';
		var gender = '';
		var houseNo = '';
		var membershipAndMobileNo = '';
				
		
$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv').html('');
	if(startIndex == 0)
	{
		$(".paginationDivId").html('');
	}
		$(".paginationDivId").hide();	
		

//$("#paginationDivId").hide();
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('#cadreSearchType').val();;
		var parentLocation = 0;
		var panchayatId = $("#panchaytList").val();
		var mandalId = $("#mandalList").val();
		var constituencyId = $("#constituencyId").val();
		var districtId = $("#districtId").val();
		
		if(panchayatId !=0)
		{
			if(panchayatId.substr(0,1) == 1){
				  locationLevel = 6;
			}
			else if(panchayatId.substr(0,1) == 2){
				 locationLevel = 8;
				 
			}								
			locationValue = panchayatId.substr(1);
		}
		else if(mandalId !=0)
		{
			if(mandalId.substr(0,1) == 1){
				 locationLevel = 7;
			}
			else if(mandalId.substr(0,1) == 2){
				 locationLevel = 5;
			}
			else if(mandalId.substr(0,1) == 3){
				 locationLevel = 8;
			}
			locationValue = mandalId.substr(1);
		}
		/*else if(constituencyId == 0)
		{
			if(accessType == "DISTRICT")
			{
				locationLevel = 3;
				locationValue = accessValue;
			}
			if(accessType == "MP")
			{
				locationLevel = 10;
				locationValue = accessValue;
			}
			if(accessType == "STATE")
			{
				var stateId = $("#statesDivId").val();
				locationLevel = 2;
				if($("#statesDivId").val() == 0)
				{
				stateId = 3;
				}
				locationValue = stateId;
			}	
		}*/
		else if(constituencyId != 0)
		{
			locationValue = constituencyId;
			locationLevel = 4;	
		}
		else if(districtId != 0)
		{
			locationValue = districtId;
			locationLevel = 3;
		}
		else
		{
			if(accessType == "DISTRICT")
			{
				locationLevel = 3;
				locationValue = accessValue;
			}
			if(accessType == "MP")
			{
				locationLevel = 10;
				locationValue = accessValue;
			}
			if(accessType == "STATE")
			{
				var stateId = $("#statesDivId").val();
				locationLevel = 2;
				if($("#statesDivId").val() == 0)
				{
					stateId = 3;
				}
				locationValue = stateId;
			}	
		}
		if(searchRadioType == 'membershipId')
		{
			memberShipCardNo = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Membership Card No.');
				return;
			}
		}			
		if(searchRadioType == 'voterId')
		{
			voterCardNo = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Voter Card No.');
				return;
			}
		}
		if(searchRadioType == 'mobileNo')
		{	
			mobileNo = $('#searchBy').val().trim();
			
			if(searchRadioType=="mobileNo"){
					
					var numericExpression = /^[0-9]+$/;
					if(!$('#searchBy').val().match(numericExpression)){
						$('#searchErrDiv').html('Enter Numerics Only.');
						return;
					}
			}	
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Mobile No.');
				return;
			}
			
			else if(mobileNo.trim().length != 10)
			{
				$('#searchErrDiv').html('Invalid Mobile No.');
				return;				
			}
			
			
			
		}
		if(searchRadioType == 'name')
		{
			searchName = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter Name.');
				return;
			}
			else if(searchBy.trim().length < 3)
			{
				$('#searchErrDiv').html('Please enter Minimum 3 Characters.');
				return;
			}
		}
		if(searchRadioType == 'trNo')
		{
			trNumber = $('#searchBy').val().trim();
			
			if(searchBy.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter trNo.');
				return;
			}
			
		}
		if(searchRadioType == 'membershipIdAndMobileNo')
		{
			membershipAndMobileNo = $('#searchBy').val().trim();
			
			var splitList = new Array();
			splitList = membershipAndMobileNo.split(",");
			memberShipCardNo = splitList[0];
			mobileNo = splitList[1];
			
			if(memberShipCardNo.trim().length == 0 )
			{
				$('#searchErrDiv').html('Please enter MemberShip Number..');
				return;
			}
			
			if(mobileNo.trim().length == 10){
					
					var numericExpression = /^[0-9]+$/;
					if(!mobileNo.match(numericExpression)){
						$('#searchErrDiv').html('Enter Numerics Only.');
						return;
					}
			}	
			else
			{
				$('#searchErrDiv').html('Please enter Valid Mobile No.');
				return;
			}
			
		}
		
		var removedStatus =false;
		if($('#onlyCandidatesId').is(':checked')){
			 removedStatus = true;
		 }
		
		$("#searchDataImg").show();
		var jsObj =
		{
			locationLevel :locationLevel,
			locationValue:locationValue,
			searchName : searchName,
			mobileNo: mobileNo,
			casteCategory : casteCategory,
			fromAge : fromAge,
			toAge : toAge,
			memberShipCardNo: memberShipCardNo,
			casteStateId : casteStateId,
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:gender,
			houseNo:houseNo,
			startIndex:startIndex,
			maxIndex : 50,
			removedStatus:removedStatus,
			task:"tdpCadreSearch"
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$(".paginationDivId").show();
				 if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				$("#searchDataImg").hide();
				$('#cadreDetailsDiv').show();
				if(result != null && result.previousRoles != null && result.previousRoles.length>0)
				{
				buildCadreDetails(result.previousRoles,jsObj);
				}
				else
				{
					
					$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				}
			});  

	}
	

	function refreshExistingDetails(){ 
		//$("#fromAgeId").val("");
		//$("#toAgeId").val("");
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
		$(".paginationDivId").html('');
		$("#cadreDetailsDiv").hide();
		$("#searchErrDiv").html("");
		$("#membrshipAndMobileNoDiv").html("");
		
		//$("#casteCategory").val(0);
		//$("#casteList").val(0);
		//$("#ageRange").val(0);
		//$("#gender").val(0);
	}
	
	function rfrshEstngDtlsForMembrShipNoAndMblNo()
  {
		$("#searchBy").val("");
		$("#cadreDetailsDiv").html("");
		$(".paginationDivId").html('');
		$("#cadreDetailsDiv").hide();
		$("#searchErrDiv").html("");
		$("#membrshipAndMobileNoDiv").html("");
		$("#membrshipAndMobileNoDiv").html("Hint:MemberShip No (8digits), Mobile No(10 digits) Example:xxxxxxxx,xxxxxxxxxx");
		
		
  }
	
		$('.searchTypeCls').click(function(){
			
		
			
			var id = $(this).attr('id');
				
			$('#advancedSearchDiv').hide();			
			$('#basicSearchDiv').show();
			$('#committeLocationsDiv').hide();
		
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
			if(id.trim() == 'trNo')
			{	
				
				$('#cadreSearchType').val('trNo');
				
			}
			if(id.trim() == 'membershipIdAndMobileNo')
			{	
				
				$('#cadreSearchType').val('membershipIdAndMobileNo');
				
			}
		});
		
			function buildCadreDetails(result,jsObj)
	{
		$(".paginationDivId").show();
		//$("#paginationDivId").show();
		var str ='';
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{
				
				if(result[i].deletedStatus == "MD"){
					str+='<div class="media eachCadreMainDivCls" style="background: rgba(255, 0, 0, 0.1) none repeat scroll 0 0;padding: 5px;border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}else{
					 str+='<div class="media" id="main'+result[i].tdpCadreId+'" style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}
				
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</li>';
				str+='<li>Gender: '+result[i].gender+'</li>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</li>';
				str+='<li>Caste: '+result[i].casteName+'</li>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</li>';
				str+='<li>MemberShipNo: '+result[i].memberShipCardId+'</li>';
				str+='<li>Registered Through: '+result[i].dataSourceType+'</li>';
				if(result[i].deletedStatus == "MD"){
					str+='<li><b style="color:red;">Deleted Reason</b> : '+result[i].deletedReason+'</li>';
				}
				else{
					str+='<li id="delete'+result[i].tdpCadreId+'"></li>';
				}
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				str+='<div>';
				if(result[i].deletedStatus != "MD"){
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT')}">
						str+='<div id="rc'+result[i].tdpCadreId+'" class="pull-right cadreRemoveCls" style="margin-left:3px;" attr_cadre_id='+result[i].tdpCadreId+' attr_cadre_name ="'+result[i].cadreName+'"><input type="button" value="Remove Cadre" class="btn btn-sm btn-primary"/></div>';
					</c:if> 
				}
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS' )}">
				str+='<div id="cadreDetailsDivId" class="cadreDetailsCls" attr_cadre_id='+result[i].tdpCadreId+' attr_membership_id='+result[i].memberShipCardId+' style="cursor:pointer;"><input type="button" value="More Cadre Details" class="btn btn-sm btn-primary pull-right"></div>';
				</c:if> 
				str+='</div>';
				
				if(result[i].committeePosition != null && result[i].committeePosition.trim().length > 0)
				{
					str+='<ul>';
					str+='<li style="font-weight:bold;display: block;">Existing Designation : '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'</li>';	
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					}
					
					str+='</ul>';	
					str+='</div>';
					str+='</div>';
			}
				else{
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
					    str+='<ul>';
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					    str+='</ul>';
					}
					str+='</div>';
					str+='</div>';
				
				}
				elegRolCnt++;
				dtCnt++;
			}
		if(result[0].mobileType > 50)	
		{
		var itemsCount=result[0].mobileType;
	    var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $(".paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*50;
				getCadreDetailsBySearchCriteria(num);
				
			}
			});
		}
		
		}
		}
		$('#cadreDetailsDiv').html(str);
	}
	$(document).on("click",".cadreDetailsCls",function(){
		var cadreId=$(this).attr("attr_cadre_id");
		var memberShipId=$(this).attr("attr_membership_id");
		var redirectWindow=window.open('cadreDetailsAction.action?cadreId='+cadreId+'','_blank');
	});
	function gettingAllConstituencys(repType){
		$("#constitunecyDiv").show();
		var str='';
			 $.ajax({
			  type:'GET',
			  url: 'getLocationWiseRegistrationInformation.action',
			  data: {task:"assemblyNames",type:repType}
			   }).done(function(result){
					if(repType == 'ALL'){
					  allConstituencies = result;
				   }
				   if(allConstituencies !=null){
					
						$('#constituencyId').append('<option value="0">Select Constituency</option>');
						for(var i in allConstituencies){
							$('#constituencyId').append('<option value="'+allConstituencies[i].id+'">'+allConstituencies[i].name+'</option>');
						}
						
				}
			   });
		}

		 
  function getConstituenciesForState(state){
  
 $("#searchDataImgForConst").show();
   var jsObj=
   {				
				stateId:0,
				elmtId:"stateList",
                type:"default",
				task:"getConstituenciesForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		$("#searchDataImgForConst").hide();
	   $("#constituencyId").empty();
	   
     for(var i in result){
	   if(result[i].id == 0){
         $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	     $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
		
 
  function getAssemblyParlConstituencies(districtId,type){
	  $("#searchDataImgForMandl").show();
	 $("#constituencyId  option").remove();
		var str='';
		var jsObj={
			mainUserLocationId:districtId,
			reportLevel:type
		}
		$.ajax({
			  type:'GET',
			  url: 'getSubUserAccessValueAction.action',
			  data: {task:JSON.stringify(jsObj)}
			
	   }).done(function(result){
		    $("#searchDataImgForMandl").hide();
	   str +='<option value=0>ALL</option>';
				for(var i in result)
				{
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				
				if(type=="Assembly"){
					$("#constituencyId").html(str);
					
				}		
	   });		
	}
	 function getAssemblyConstituencies(constiId){
	var str;
	var jsObj = 
	       {
			  parliamentConstiId : constiId,
			  electionYear:2005,
			  task:"getAssemblyDetailsForParliamnt"             
	       }	
		    $.ajax({
				type : "POST",
				url : "getAssemblyDetailsForParliamntAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				 $("#constituencyId").empty();
				 var result1 = result.selectOptionsList;
				 if(result1 != null && result1.length > 0)
					{
					  $("#constituencyId").append('<option value=0>ALL</option>');
						 for(var i in result1){
						 $("#constituencyId").append('<option value='+result1[i].id+'>'+result1[i].name+'</option>');
						   
						 }
					}
		  });
	 }
/*	function getConstituenciesForStateAjax()
		{
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			refreshExistingDetails();
			document.getElementById('allId').checked = false;
			$(".allcls").hide();
			var stateId = $("#statesDivId").val();
			getConstituenciesForState(stateId);
		}*/
		
			</script>
			<script>
			if(accessType == "DISTRICT")
			getAssemblyParlConstituencies(accessValue,"Assembly");
			if(accessType == "STATE")
			{
				getConstituenciesForState();
				getDistricts();
			}
			
			if(accessType == "MP")
			{
				getAssemblyConstituencies(accessValue);
			}
	
	function getDistricts(){
	$("#searchDataImgForDist").show();
     var jsObj=
		{				
				stateId:1,
				elmtId:"districtList_d",
                type:"default",
				task:"findDistrictsForAState"				
		}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForAStateAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#searchDataImgForDist").hide();
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
			
  function getMandalCorporationsByConstituency()
	{	
			$("#searchDataImgForMandl").show();
			refreshExistingDetails();
			document.getElementById('allId').checked = false;
			$(".allcls").hide();
			var constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			document.getElementById('membershipId').checked = true;
			
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				$("#searchDataImgForMandl").hide();
				if(result !=null)
				{
					for(var i in result)
					{
						$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
					}	
				}				
				});
	}
	
	function getPanchayatWardByMandal(){
			$("#searchDataImgForPanc").show();
			refreshExistingDetails();
			document.getElementById('allId').checked = false;
			$(".allcls").hide();
		     
			var mandalId=$("#mandalList").val();
			
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Panchayat</option>');
			
			var jsObj={
				mandalId:mandalId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				$("#searchDataImgForPanc").hide();
			for(var i in result){
				$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
			}
				
		});	
			
	}
	
	var isLoading = false;
	function getPanchayayCadreDetailsBySearchCriteria(startIndex)
		{
		

			if(!isLoading)
			{
				
				//refreshExistingDetails();
						$("#searchbtn").hide();
						$(".allcls").hide();
					var locationLevel = 0;
					var locationValue = 0;
					var searchName = '';
					var mobileNo = '';
					var casteCategory = '';
					var casteStateId = 0;
					var fromAge = 0;
					var toAge = 0;
					var memberShipCardNo = '';
					var trNumber = '';
					var voterCardNo = '';
					var gender = '';
					var houseNo = '';
					
					
					$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv').html('');
					if(startIndex == 0)
					{
						$(".paginationDivId").html('');
					}
					$(".paginationDivId").hide();
					$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
					$("#cadreDetailsDiv").hide();
					var searchBy = $('#searchBy').val().trim();
					var searchRadioType =$('#cadreSearchType').val();;
					var parentLocation = 0;
					var panchayatId = $("#panchaytList").val();
					var mandalId = $("#mandalList").val();
					var constituencyId = $("#constituencyId").val();
					
					if(panchayatId !=0)
					{
						isLoading = true;
						if(panchayatId.substr(0,1) == 1){
							  locationLevel = 6;
						}
						else if(panchayatId.substr(0,1) == 2){
							 locationLevel = 8;
							 
						}								
						locationValue = panchayatId.substr(1);
						
							var removedStatus =false;
							if($('#onlyCandidatesId').is(':checked')){
								 removedStatus = true;
							 }
						
					$("#searchDataImg").show();
					
					var jsObj =
					{
						locationLevel :locationLevel,
						locationValue:locationValue,
						searchName : searchName,
						mobileNo: mobileNo,
						casteCategory : casteCategory,
						fromAge : fromAge,
						toAge : toAge,
						memberShipCardNo: memberShipCardNo,
						casteStateId : casteStateId,
						trNumber : trNumber,
						voterCardNo:voterCardNo,
						gender:gender,
						houseNo:houseNo,
						startIndex:startIndex,
						maxIndex : 50,
						removedStatus:removedStatus,
						task:"tdpCadreSearch"
					}
					$.ajax({
							type : "POST",
							url : "getCadreSearchDetailsAction.action",
							data : {task:JSON.stringify(jsObj)} ,
						}).done(function(result){
							isLoading = false;
							$('#searchErrDiv').html('');
							$(".paginationDivId").show();
							document.getElementById('allId').checked = false;
							 if(typeof result == "string"){
								if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
								  location.reload(); 
								}
							}
							$("#searchDataImg").hide();
							$('#cadreDetailsDiv').show();
							$("#searchbtn").show();
							$(".allcls").show();
							if(result != null && result.previousRoles != null && result.previousRoles.length>0)
							{
							buildCadrePanchayatDetails(result.previousRoles,jsObj);
							}
							else
							{
								
								$('#cadreDetailsDiv').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
							}
						});  
						
					}
			}
			else
			{
				$('#searchErrDiv').html('Please wait Data is Loading...');
			}
	}
	
	function getAllCadreInPanchayat()
	{
		document.getElementById('allId').checked = false;
		document.getElementById('membershipId').checked = true;
		$(".allcls").show();
		refreshExistingDetails();
	}
	
	function buildCadrePanchayatDetails(result,jsObj)
	{
		var str ='';
		
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{
				
				if(result[i].deletedStatus == "MD"){
					str+='<div class="media eachCadreMainDivCls" style="background: rgba(255, 0, 0, 0.1) none repeat scroll 0 0;padding: 5px;border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}else{
					 str+='<div class="media" id="main'+result[i].tdpCadreId+'"  style="border-bottom: 1px solid rgb(51, 51, 51);" attr_cadre_id='+result[i].tdpCadreId+'>';
				}
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</li>';
				str+='<li>Gender: '+result[i].gender+'</li>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</li>';
				str+='<li>Caste: '+result[i].casteName+'</li>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</li>';
				str+='<li>MemberShipNo: '+result[i].memberShipCardId+'</li>';
				str+='<li>Registered Through: '+result[i].dataSourceType+'</li>';
				if(result[i].deletedStatus == "MD"){
					str+='<li><b style="color:red;">Deleted Reason</b> : '+result[i].deletedReason+'</li>';
				}
				else{
					str+='<li id="delete'+result[i].tdpCadreId+'"></li>';
				}
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				str+='<div>';
				if(result[i].deletedStatus != "MD"){
					<c:if test="${fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT_GROUP') || fn:contains(sessionScope.USER.entitlements, 'CADRE_DELETE_ENTITLEMENT')}">
						str+='<div id="rc'+result[i].tdpCadreId+'" class="pull-right cadreRemoveCls" style="margin-left:3px;" attr_cadre_id='+result[i].tdpCadreId+' attr_cadre_name ="'+result[i].cadreName+'"><input type="button" value="Remove Cadre" class="btn btn-sm btn-primary"/></div>';
					</c:if> 
				}
				<c:if test="${fn:contains(sessionScope.USER.entitlements, 'TDP_CADRE_DETAILS' )}">
				str+='<div id="cadreDetailsDivId" class="cadreDetailsCls" attr_cadre_id='+result[i].tdpCadreId+' attr_membership_id='+result[i].memberShipCardId+' style="cursor:pointer;"><input type="button" value="More Cadre Details" class="btn btn-sm btn-primary pull-right"></div>';
				</c:if> 
				str+='</div>';
				
				if(result[i].committeePosition != null && result[i].committeePosition.trim().length > 0)
				{
					str+='<ul>';
					str+='<li style="font-weight:bold;">Existing Designation : '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'</li>';	
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</li>';
						}
					}
					
					str+='</ul>';	
					str+='</div>';
					str+='</div>';
			}
				else{
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
					    str+='<ul>';
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</i>';
						}
					    str+='</ul>';
					}
					str+='</div>';
					str+='</div>';
				
				}
				elegRolCnt++;
				dtCnt++;
			}
		if(result[0].mobileType > 50)	
		{
		var itemsCount=result[0].mobileType;
	    var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $(".paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*50;
				getPanchayayCadreDetailsBySearchCriteria(num);
				
			}
			});
		}
		
		}
		}
		$('#cadreDetailsDiv').html(str);
	}
	
	
			</script>
			
<script>

	
  
  function getDistrictsForStates(state){
   getConstituenciesForState(state);
    $("#searchDataImgForDist").show();
    refreshExistingDetails();
	$(".allcls").hide();
	$("#districtId  option").remove();
	$("#districtId").append('<option value="0">Select District</option>');
	$("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchaytList  option").remove();
	$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	
	document.getElementById('membershipId').checked = true;
	
   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#districtId").empty();
	   $("#searchDataImgForDist").hide();
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
   function getConstituenciesForDistricts(district){
   $("#searchDataImgForConst").show();
    refreshExistingDetails();
	$(".allcls").hide();
   $("#constituencyId  option").remove();
	$("#constituencyId").append('<option value="0">Select Constituency</option>');
	$("#mandalList  option").remove();
	$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
	$("#panchaytList  option").remove();
	$("#panchaytList").append('<option value="0">Select Panchayat</option>');
	
	document.getElementById('membershipId').checked = true;
	
	var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#constituencyId").empty();
	   $("#searchDataImgForConst").hide();
     for(var i in result){
	   if(result[i].id == 0){
          $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
   });
  }
  
  
  $(document).on("click",".cadreRemoveCls",function(){
	  
	  var tdpCadreId = $(this).attr("attr_cadre_id");
	  var cadreName= $(this).attr("attr_cadre_name");
	  
	  $("#hiddenCadreId").val(tdpCadreId);
	  $("#cadreName").html(cadreName);
	  
	  $("#errorDivId").html("");
	  $("#successDivId").html("");
	   $("#remarkTextAreaId").val("");
	  
	   getAllCadreDeleteReasons();
	  
	$("#removeModalDivId").modal("show");
  });
  
  function getAllCadreDeleteReasons(){
	  $("#reasonSelectId option").remove();
	  
	  $("#reasonSelectId").append('<option value="0">Select Reason</option>');
	  
	  $.ajax({
          type:'GET',
          url: 'getAllCadreDeleteReasonsAction.action',
          dataType: 'json',
		  data: {}
	   }).done(function(result){
		   
		   if(result !=null && result.length>0){
			   for(var i in result){
				   $("#reasonSelectId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
		   }
		  
	   });
  }
  
  $(document).on("click","#saveRemovingCadreDetailsId",function(){
	  saveRemovingCadreDetails();
  });
  
  function saveRemovingCadreDetails(){
	  
	  var errorExist = false;
	  
	  var cadreId = $("#hiddenCadreId").val();
	  var reasonId = $("#reasonSelectId option:selected").val();
	  var reason = $("#reasonSelectId option:selected").text();
	  var remarkTxt = $("#remarkTextAreaId").val();
	  
	  if(reasonId != null && (reasonId == 0 || reasonId == undefined)){
		  $("#errorDivId").html("Please Select Reason");
		  errorExist=true;
	  }
	 else if(remarkTxt !=null && remarkTxt.trim().length ==0){
		  $("#errorDivId").html("Please Enter Remark");
		errorExist=true;
	 }
	 if(errorExist){
		 return;
	 }
	 
	 if(!confirmDelete()){
		 return;
	 }
	
	  var jsObj = {
		  cadreId : cadreId,
		  reasonId : reasonId,
		  remarkTxt : remarkTxt
	  }
	   $.ajax({
          type:'GET',
          url: 'saveRemovingCadreDetailsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result.resultCode == 0){
				$("#successDivId").html("<span style='color:green;'>Cadre Removed Successfully.</span>");
				setTimeout(function(){
				  $("#removeModalDivId").modal("hide");
				}, 1000);
				$('#rc'+cadreId).remove();
				
				$("#main"+cadreId).css({"background":"rgba(255, 0, 0, 0.1)","padding":"5px","border-bottom":"1px solid rgb(51, 51, 51)"});
				
				$("#delete"+cadreId).append('<b style="color:red;">Deleted Reason</b> : '+reason);
				
			}
			else{
				$("#errorDivId").html("Cadre Not Removed Successfully.");
				setTimeout(function(){
				  $("#removeModalDivId").modal("hide");
				}, 1000);
			}
		});
	  
	  
  }
  
  function confirmDelete(){
	var deleteCadre = confirm("Are you sure you want to delete cadre ?");
	  if (deleteCadre)
		  return true;
	  else
		return false;
  }
  
</script>
			
</body>
</html>
			