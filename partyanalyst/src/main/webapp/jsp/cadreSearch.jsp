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
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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
				 <div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0" id="statedisplaydivid">
						<label>State</label>
						<c:if test="${sessionScope.USER.stateName == 'both'}">
						  <select id="statesDivId" onchange="getConstituenciesForStateAjax();" class="form-control">
							<option value="0">All</option>
							<option value="1">AndhraPradesh</option>
							<option value="2">Telangana</option>
						  </select>
						  </c:if>
						  <c:if test="${sessionScope.USER.stateName == 'TS'}">
						  <select id="statesDivId" onchange="getConstituenciesForStateAjax();" class="form-control">
							<option value="2">Telangana</option>
						  </select>
						  </c:if>
						   <c:if test="${sessionScope.USER.stateName == 'AP'}">
						  <select id="statesDivId" onchange="getConstituenciesForStateAjax();" class="form-control">
							<option value="1">AndhraPradesh</option>
						  </select>
						  </c:if>
						
				     </div>
					 </c:if>
					
				   <div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0" id="constitunecyDiv">
							<label>Constituency</label>
							<select class="form-control " id="constituencyId" class="form-control">
							</select>
							</div>
							</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
				<div class="form-inline ">
			
				   
					<div class="radio">
						<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" onclick="refreshExistingDetails();" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  onclick="refreshExistingDetails();"  value="2" > Voter ID &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  onclick="refreshExistingDetails();"  value="3"> Mobile No &nbsp;&nbsp;</label>
					
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  onclick="refreshExistingDetails();"  value="4"> Name &nbsp;&nbsp;</label>
						<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="trNo"  onclick="refreshExistingDetails();"  value="5"> TR No &nbsp;&nbsp;</label>
						<!--<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="advancedSearch"  onclick="refreshExistingDetails();"  value="5"> Advanced Search &nbsp;&nbsp;</label>-->
						
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
					<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0">
						<input type="text" placeholder="ENTER MEMBERSHIP ID / VOTER ID / MOBILE NO / NAME"  class="form-control" id="searchBy">
						<div id="searchErrDiv"></div>
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
						<div class="row"><div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 ">
						<input id="searchbtn" class="btn btn-success btn-block" type="button" onclick="getCadreDetailsBySearchCriteria(0)" value="SEARCH"></input></div>
					</div>	
					</div>
			</div>
				<div class="row">
	
			
			<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-left: 660px;margin-top: 20px;width:70px;height:60px;display:none;"/>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;overflow:scroll:900px;" id="cadreDetailsDiv"></div>
				<div id="paginationDivId"></div>

			</div>
		</div>	
			</div>
			<script>
	var accessType = "${sessionScope.USER.accessType}";
	var accessValue = "${sessionScope.USER.accessValue}";
	var accessState = "${sessionScope.USER.stateName}";
         $(document).keypress(function(e) {
				if(e.keyCode==13){
					 getCadreDetailsBySearchCriteria(0);
				}
		  });
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
		
		
$('#cadreDetailsDiv,#searchErrDiv,#committeeLocationIdErr,#committeLocationIdErr,#advancedSearchErrDiv,#paginationDivId').html('');
		$('#searchLevelErrDiv,#committeePositionIdErr,#nonAfflitCommitteeIdErr').html('');
		$("#cadreDetailsDiv").hide();
		var searchBy = $('#searchBy').val().trim();
		var searchRadioType =$('#cadreSearchType').val();;
		var parentLocation = 0;
		var constituencyId = $("#constituencyId").val();
		
			if(constituencyId == 0)
			{
				if(accessType == "DISTRICT")
					{
					locationLevel = 3;
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
			else
			{
			locationValue = constituencyId;
			locationLevel = 4;	
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
			maxIndex : 100,
			task:"tdpCadreSearch"
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
		//$("#casteCategory").val(0);
		//$("#casteList").val(0);
		//$("#ageRange").val(0);
		//$("#gender").val(0);
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
		});
		
			function buildCadreDetails(result,jsObj)
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
		if(result[0].mobileType > 100)	
		{
		var itemsCount=result[0].mobileType;
	    var maxResults=jsObj.maxIndex;
	   
	     if(jsObj.startIndex==0){
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*100;
				getCadreDetailsBySearchCriteria(num);
				
			}
		});
	}
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

		 
   function getConstituenciesForState(state){
 
   var jsObj=
   {				
				stateId:state,
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
	function getConstituenciesForStateAjax()
		{
			var stateId = $("#statesDivId").val();
			getConstituenciesForState(stateId);
		}
		
			</script>
			<script>
			if(accessType == "DISTRICT")
			getAssemblyParlConstituencies(accessValue,"Assembly");
			if(accessType == "STATE")
			{
			getConstituenciesForStateAjax();
			}
			
		
			</script>
			</body>
			</html>
			