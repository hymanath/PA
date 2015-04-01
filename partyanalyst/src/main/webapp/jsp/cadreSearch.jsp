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
	.m_top5{margin-top:5px;}
	.m_top30{margin-top:30px;}
	.editDesignation{margin-bottom: 10px;}
	
	/*.hideRowClass{display:none;}*/
	
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
			
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20" id="advancedSearchDiv" style="display:none;">	
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;"> 					
					<!--<h6>Advanced Search</h6>-->
					<div id="advancedSearchErrDiv"></div>
					<div class="row">
						<div class="col-md-4 col-sm-4 col-xs-12" id="constitunecyDiv" style="display:none;">
							<label>Constituency</label>
							<select class="form-control " id="constituencyId" >
								
								</select>
							</div>
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
						<div class="col-md-4 col-md-offset-4 col-sm-4 col-sm-offset-4 col-xs-4 col-xs-offset-4 m_top10">
							<button type="submit" class="btn btn-success btn-block" onclick="getCadreDetailsBySearchCriteria()">SEARCH</button>
						</div>				
					</div>			
				</div>			
			</div>
			</div>
				<div class="row">
	
			
			<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-left: 660px;margin-top: 20px;width:70px;height:60px;display:none;"/>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;" id="cadreDetailsDiv"></div>			
			</div>
		</div>	
			</div>
			<script>
			
			function getCadreDetailsBySearchCriteria()
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
		$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv').html('');
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('#cadreSearchType').val();;
		var parentLocation = 0;
		
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
		if(searchRadioType == 'advancedSearch')
		{	
			
			
			gender = $('#gender option:selected').text().trim();
			var casteGroup = $('#casteCategory').val();
			var casteName  = $('#casteList').val();
			var age = $('#ageRange').val();
			
			var locfromAge = $('#fromAgeId').val().trim();
			var loctoAge = $('#toAgeId').val().trim(); 
			var constituencyId = $("#constituencyId").val();
			if(constituencyId == 0)
			{
				$('#advancedSearchErrDiv').html("Please Select constituency.");	
				return;			
			}
			else if(casteGroup == 0 && casteName == 0 && age == 0 && gender == 'All' && locfromAge.length == 0 && loctoAge.length == 0)
			{
				$('#advancedSearchErrDiv').html('Please Select Any of Search Criteria');
				return;			
			}			
			if($('#ageRange').val() != 0)
			{
				var ageRange = $('#ageRange option:selected').text();
				var ageRange = ageRange.split('-');
				fromAge = ageRange[0].trim();
				toAge = ageRange[1].trim();
			}
			else
			{
				fromAge = $('#fromAgeId').val().trim();
				toAge = $('#toAgeId').val().trim(); 
				
				if(fromAge.length >0 || toAge.length >0)
				{
					if(fromAge.length == 0 || toAge.length == 0)
					{
						$('#advancedSearchErrDiv').html('Please Enter Between Age Details.');
						return;	
					}
					if(fromAge > toAge){
						$('#advancedSearchErrDiv').html('From Age Should be Less than To Age.');
						return;							
					}
				}
				else
				{
					fromAge = 0;
					toAge = 0;					
				}
			}				
			casteCategory = $('#casteCategory option:selected').text().trim();
			casteStateId = $('#casteList').val().trim();
			locationValue = constituencyId;
			locationLevel = 4;	
			if(casteCategory == 'All')
			{
				casteCategory = "";				
			}			
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
			houseNo:houseNo
		}
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				 if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				$("#searchDataImg").hide();
				$('#cadreDetailsDiv').show();
				if(result != null && result.previousRoles != null && result.previousRoles.length>0)
				{
				buildCadreDetails(result.previousRoles);
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
		//$("#casteCategory").val(0);
		//$("#casteList").val(0);
		//$("#ageRange").val(0);
		//$("#gender").val(0);
	}
		$('.searchTypeCls').click(function(){
			
		 $("#constituencyId  option").remove();
			$("#constitunecyDiv").hide();
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
				$("#constitunecyDiv").show();
				gettingAllConstituencys("ALL");
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
		
			function buildCadreDetails(result)
	{
		
		var str ='';
		
		var elegRolCnt=0;
		var dtCnt = 0;
		if(result != null)
		{
			for(var i in result)
			{
				
				str+='<div class="media">';
				str+='<span href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="http://www.mytdp.com/images/cadre_images/'+result[i].imageURL+'" />';
				str+='</span>';
				str+='<div class="media-body">';
				str+='<h5 class="media-heading"> <span style="font-weight:bold;"> Name:</span> '+result[i].cadreName+' ; ';				
				str+=' <span style="font-weight:bold;"> Relative Name: </span>'+result[i].relativeName+' </h5>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+';</i>';
				str+='<li>Gender: '+result[i].gender+'</i>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</i>';
				str+='<li>Caste: '+result[i].casteName+'</i>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</i>';
				str+='<li>MemberShipNo: '+result[i].memberShipCardId+'</i>';
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				
				if(result[i].committeePosition != null && result[i].committeePosition.trim().length > 0)
				{
					str+='<ul>';
					str+='<li style="font-weight:bold;">Existing Designation : '+result[i].committeePosition+' for '+result[i].committeeName+' Committee in '+result[i].committeeLocation+'</i>';	
					if(result[i].previousRoles != null && result[i].previousRoles.length > 0){
						for(var j in result[i].previousRoles){
							str+='<li style="font-weight:bold;">Electoral for '+result[i].previousRoles[j].committeeName+' Committee in '+result[i].previousRoles[j].committeeLocation+'</i>';
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
		}
		
		$('#cadreDetailsDiv').html(str);
	}
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
			</script>
			</body>
			</html>
			