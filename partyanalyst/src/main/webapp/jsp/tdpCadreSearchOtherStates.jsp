<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
    <title>TDP Cadre Search </title>

  
    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	 	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
		<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 

		<script type="text/javascript" src="js/exportexcel.js"></script>
		<link rel="stylesheet" type="text/css" href="styles/simplePagination-1/simplePagination_1.css"/> 
<script type="text/javascript" src="js/simplePagination/simplePagination1.js" ></script>
		
		<link rel="stylesheet" href="js/flipclock/flipclock.css">		
		<script src="js/flipclock/flipclock.js"></script>	
		<script src="dist/js/bootstrap.min.js" type="text/javascript"></script>
	<style>
	
	.dropdown-menu > li > a:hover {
    background: none repeat scroll 0 0 #f5f5f5 !important;
    color: #333;
}

	body{margin-bottom:120px;}
	.footerFixedStrip{
		bottom:0px; 
		left:0px; 
		position:fixed; 
		background:#ff3333; 
		width:100%;
		height:100px;
		padding:20px 10px 5px 10px; 
		z-index:999;
		display:none;
	}
	.flip{transform:none !important;}
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.f-16{font-size: 16px;}
	 .bgc{background-color:#3598db}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}

		#searchCadreTab  thead th {
			background-color: #dff0d8  !important;
			color : #468847 !important;
			line-height: 15px !important;
		}

	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	 .bgc{background-color:#3598db}
	 
	.dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}

	.header-bg{background:#3598DB url('./images/cadre_images/2014-Header-BG.png') repeat-x; height:179px;}
	.color-white{color:#f9f9f9;}
	.offset1 {
	    margin-left: 70px;
	}
	.span10 {
	    width: 840px;
	}

	#dayWiseUsersDetailsId  thead th{
				background-color: #dff0d8  !important;
				color : #468847 !important;
				line-height: 20px !important;
			}
			
			
	</style>
   
	
</head>
  <body class="bgc">
  
  	<!-- Header Row 
	
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<div class="row-fluid">
				  <div class="span4 offset4 ">
						<img src="images/cadre_images/2014-cadre-Registration-Logo.png">
				  </div>
				  <div class="span4">
					<span>
					 <a href="newlogoutAction.action" class="btn btn-mini pull-left m_top20">Logout</a>	
					</span>
					<span>
					  <a  class="btn btn-info btn-mini offset1" style="float: left;margin-top: 20px;" id="statusDivsId1" href="javascript:{hideDashBoard();}" > Know User Status </a>
					 </span>
					<span>
					  <a class="btn btn-info btn-mini offset1" style="float: left;margin-top: 20px; width: 90px;display:none;" id="statusDivsId2" href="javascript:{showDashBoard();}"> Home </a>	
					</span>
				  </div>
				</div>
			</div>
		</div><!-- Header Row End-->

	<div class="container" id="yourElement">
	<div id="myDiv"></div>
	<div id="tableDivForCadre" class="table-responsive"></div>
	
		<div class="span6 offset3 show-grid pad-10b" >
		<div id="errorDiv" style="color:#ff0020;"></div>
		<c:if test="${sessionScope.USER.accessType == 'STATE' && sessionScope.USER.accessValue != '29'}">
			<h5 class="text-align">SELECT CONSTITUENCY </h5>

			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:440px;" onChange="getConstituencyWiseDetails();hideSearchResult();"/>
			<select style="width:440px;" id="panchayatList"><option value="0"> Select Booth </option></select>		
			<!--<select style="width:250px;" onchange="hideSearchResult();" id="boothsList"> <option value="0"> Select Booth </option> </select> 	 -->
			<!-- <select style="width:150px;" id="vilagecovrdList"> <option value="0"> Select Covered Village </option> </select>  -->
			</c:if>
			<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
				<span style="display:none;">
				<h5 class="text-align small m_top15">SEARCH BY</h5>
					<div class="span6">
					
						<div class="row form-inline text-center">
									<label class="radio"><input type="radio" value="voter"  name="searchTypeRadio" checked="true" class="icheckBtns"> VOTER</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
					</div>
				</span>	
				<div class=" m_top10">
						<div class="row-fluid">
						
							<div class="span6 " >
							<h5 class="text-align1">Select State</h5>
								<select id="stateLocationId" style="margin-top:-5px;" class=" form-control" >
									<option value="0"> Select State</option>
									<option value="24"> Tamil Nadu</option>
									<option value="12"> Karnataka</option>
									<option value="29"> Andaman and Nicobar</option>
									<option value="20"> Orissa</option>
								</select>
							</div>
							
							<div class="span6">
							<h5 class="text-align1">Select Constituency</h5>
								<select id="consituenctDetailsId" style="margin-top:-5px;" class=" form-control">
								<option value="0"> Select Constituency</option>
									
								</select>
							</div>
						</div>
					</div>
					<div class="pad-10b">
					<h5 class="text-align1"  style="margin-left: -10px">CANDIDATE NAME</h5>
							<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="searchNameId" name="searchName" style="width:425px;margin-left: -10px;border-radius: 5px ;height: 30px">
					</div>
					<div class=" m_top10 pad-10b">
						<div class="row-fluid">
						
							<div class="span6">
							<h5 class="text-align1">VOTER ID</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Voter ID"  id="searchVoterCardId"  name="searchVoterCard" style="margin-left: -10px;border-radius: 5px ;height: 30px">
							</div>
							
							<div class="span6">
							<h5 class="text-align1">H NO</h5>
								<input type="text" class="form-control border-radius-0" placeholder="House Number"  id="searchHNoId"   name="searchHNo" style="margin-left: -10px;border-radius: 5px ;height: 30px">
							</div>
						</div>
					</div>
					<br/>
					<div class="pad-10b">
						<!--<input type="checkbox" style="margin-top: 0px;" onchange="showhideRelativeType();" class="form-control border-radius-0" id="isFamilyVoterOrNot" > &nbsp; <b>Check Here If You Are Registering Cadre Using His Family VoterId</b>
						<div style="display:none;margin-top:10px;" id="showhideRelativeTypeId"><div id="relativeTypeIdErr" style="color:red;font-weight:bold;"></div><div><select id="relativeTypeId"></select></div></div>
						-->
						<!--
						<div class="row">
						<h5 class="text-align1"> Select Type of Registration </h5>
						<div class="col-md-6" >
						  <span class="text-align1" style="font-size:12px;margin-left:-10px"><input type="radio" id="voterId" name="registrationType" class="regisrationTypeCls" value="voterId" checked="true" onchange="handleRequest('voterId')"/> Voter Id</span>
						  <span class="text-align1" class="regisrationTypeCls" style="font-size:12px;"><input type="radio" id="familyVoter" name="registrationType" value="familyVoter" onchange="handleRequest('familyVoter')"/> Family Voter</span>
							</div>
							<div class="col-md-4" >
						  
							</div>
						<div class="col-md-3" >
						  <label class="text-align1" class="regisrationTypeCls" style="font-size:12px;margin-left:-10px"><input type="radio" id="apVoter" name="registrationType" value="apVoterId" onchange="handleRequest('apVoterId')"/> AP Voter </lebel>
						</div>
					  
					   <div class="col-md-3" >
						  <label class="text-align1" class="regisrationTypeCls" style="font-size:12px;margin-left:-10px"><input type="radio" id="noVoterId" name="registrationType" value="noVoter" onchange="handleRequest('noVoter')"/> No Voter</lebel>
						</div>					  
					  </div>
					  -->
					  <div class="row">
						<h5 class="text-align1"> Select Type of Registration </h5>
						<div class="col-md-4">
						  <label style="font-size:12px;" class="text-align1"><input type="radio" onchange="handleRequest('voterId')" checked="true" value="voterId" class="regisrationTypeCls" name="registrationType" id="voterId"> With Voter Id</label>
						  </div>
						  <div class="col-md-5">
						  <label style="font-size:12px;" class="text-align1"><input type="radio" onchange="handleRequest('familyVoter')" value="familyVoter" name="registrationType" id="familyVoter"> With Family Voter</label>
						  </div>
						   <div class="col-md-5">
							<!--<input type="checkbox" style="margin-top: 0px;" onchange="showhideRelativeType();" class="form-control border-radius-0" id="isFamilyVoterOrNot" > &nbsp; <b>Check Here If You Are 	Registering Cadre Using His Family VoterId</b>-->
							<div style="display:none;margin-top:10px;" id="showhideRelativeTypeId"><div id="relativeTypeIdErr" style="color:red;font-weight:bold;"></div><div><select id="relativeTypeId"></select></div></div>
						</div>
						<!--

						  <span style="font-size:12px;" class="text-align1"><input type="radio" onchange="handleRequest('apVoterId')" value="apVoterId" name="registrationType" id="apVoter"> AP Voter 
						</span>

						  <span style="font-size:12px;" class="text-align1"><input type="radio" onchange="handleRequest('noVoter')" value="noVoter" name="registrationType" id="noVoterId"> No Voter
						</span>  
						-->
						
					  </div>
					</div>
					<input type="hidden" value="voterId" id="registrationTypeId" />
					 <div class="row m_top10" >
					  <div class="col-md-3 offset2">
					<button onclick="searchCandidatesDetailsBySearchCriteria(0);" id="searchBtnId" class="btn btn-success"> Search <span class="glyphicon glyphicon-chevron-right"></span> </button>
						</div>
					   <div class="col-md-3">
							<button onclick="skipDetailsForUser('skip');" title="If Candidate did not have VoterCard or not a Cadre Click Here" class="btn btn-success" >Skip<span class="glyphicon glyphicon-chevron-right"></span></button>
						</div>
					</div>
					
					
		</div>
		
		
	</div>
	<div class="container " id="dashboadElmnt" style="display:none;">	
	
		<div id="locationWiseCadreInfoDiv">
		    <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-0 " style="padding:0px;">
					<h3 class="offset4 text-uppercase"> user dashboard </h3>
				</div>
			</div>
			<div class="row-fluid show-grid">
			   <div class="row-fluid offset3">						
							<div class="span5" style="margin-bottom:-20px;">
							<h5 class="text-align1">Form Date : 
							<input type="text" class="form-control border-radius-0 datePickerCls" placeholder="From Date " id="fromDateId" style="width: 220px;cursor:text;height: 30px" readonly="true"></h5>
							</div>							
							<div class="span">
								<h5 class="text-align1"> To Date : 
								<input type="text" class="form-control border-radius-0 datePickerCls" placeholder="To Date " id="toDateId" style="width: 220px;cursor:text;height: 30px" readonly="true"></h5>
							</div>
						</div>
						<a href="javascript:{getDashboardDetailsForUser();}" class="btn btn-success col-xs-offset-4 border-radius-0 offset5"> Get Details  <span class="glyphicon glyphicon-chevron-right"></span></a>
						
						<div><img src='images/Loading-data.gif' class="offset5"  id="searchDashboardImg" style="width:70px;height:60px;display:none;"/>
						</div>
						<div id="dashBoadDiv" style="padding: 10px;"></div>
						
			</div>
		</div>
	</div>
	<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-left: 660px;margin-top: 20px;width:70px;height:60px;display:none;"/>
	<div class="container" id="tableElement" style="margin-top:25px;display:none;">
		<div class="span10 offset1 show-grid pad-5 m-bottom-10">
			<h3 class="text-align">SEARCH DETAILS</h3>
			<div class="table-responsive" id="searchDetailsDiv" ></div>
            <div class="span12 text-center">
				<div id="paginationId"></div>
			</div>
		</div>
	</div>
<!-- start FlipClock 
<div class="footerFixedStrip" >		
		<div class="" style="width:44%; float:left;">
			<h1 style="margin-top:0px;float:right;color:#cccccc; text-shadow:0 1px 2px rgb(0, 0, 0);;font-size:18px">2014 Cadre  Registrations Will be Closed <br>By 2014-12-23 After Noon 04:00PM </h2>
		</div>		
		<div class="" style="width:56%;float:left;">
			<div class="message" style="font-size: 25px;margin-top:30px;margin-left: 125px;"></div>
			<div class="clock"></div>
		</div>
		
	</div>

<!-- end FlipClock -->
		<!-- Footer Row
		
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
	
	<script>
	  function showhideRelativeType(){
		  $("#relativeTypeIdErr").html("");
		  if($('#isFamilyVoterOrNot').is(':checked')){
		       $('#showhideRelativeTypeId').show();
		  }else{
			  $('#showhideRelativeTypeId').hide();
		  }
	  }
	function getAllRelationDetails(){
		 $.ajax({
			type : "POST",
			url : "getAllRelationDetails.action"
		 }).done(function(result){
		  if(result != null && result.length > 0){
		   $('#relativeTypeId').append('<option value="0">Select Relation</option>');
		   for(var i in result){
			 $('#relativeTypeId').append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		  }
		});
    }
		
		var remainingTime = '${countDownTime}';
		$(document).ready(function() {
			var clock;
			clock = $('.clock').FlipClock({
		        clockFace: 'DailyCounter',
		        autoStart: false,
		        callbacks: {
		        	stop: function() {
		        		$('.message').html('Registrations Closed By 4:00PM!');
						$('.clock').hide();
		        	}
		        }
		    });
				    
			if(remainingTime.trim().length >0)
			{
				$('.footerFixedStrip').hide();
				clock.setTime(parseInt(remainingTime));
				clock.setCountdown(true);
				clock.start();			
			}
			var currentDate = new Date();
		$( "#fromDateId" ).datepicker({
					dateFormat: 'dd-mm-yy',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date()	
		});
		$( "#toDateId" ).datepicker({	
					dateFormat: 'dd-mm-yy',
					changeMonth: true,
					changeYear: true,
					maxDate: new Date()
		});
		$("#fromDateId").datepicker("setDate", currentDate);
		$("#toDateId").datepicker("setDate", currentDate);
  
		});
		
	function isValid(str)
	{
		var flag = true;
		var iChars = "`~!@#$%^&*()_-+=}]{[\"':;|\?/><,";		 
		for (var i = 0; i < str.length; i++) 
		{
			if (iChars.indexOf(str.charAt(i)) != -1) 
			{			
				flag = false;
			}
		}
	return flag;
	}
	
	var request;
	function hideSearchResult(){
      $('#tableElement').hide();
	}	
	function searchCandidatesDetailsBySearchCriteria(start)
	{
		
		var cosntiteucnyId = $('#userConstituencyId').val();
		var candidateName = $('#searchNameId').val();
		var voterCardNo = $('#searchVoterCardId').val();
		var houseNo = $('#searchHNoId').val();
		var searchType = $('input[name="searchTypeRadio"]:checked').val();
		var panchayatId = $('#panchayatList').val();
		var boothId = $('#boothsList').val();
		//var villageCoveredId = $('#vilagecovrdList').val(); 
		var ischecked = "0";
	
	cosntiteucnyId = (cosntiteucnyId != null && cosntiteucnyId != null ? cosntiteucnyId = cosntiteucnyId:0);
	 candidateName = (candidateName != null && candidateName.length > 0 ? candidateName = candidateName:"");
	   voterCardNo = (voterCardNo != null && voterCardNo.length > 0 ? voterCardNo = voterCardNo:"");
	       houseNo = (houseNo != null && houseNo.length > 0 ? houseNo = houseNo:"");
	    searchType = (searchType != null && searchType.length > 0 ? searchType = searchType:"");
	   panchayatId = (typeof(panchayatId) != 'undefined' && panchayatId != null ? panchayatId = panchayatId:0);
	       boothId = ( typeof(boothId) != 'undefined' && boothId != null ? boothId = boothId:0);
		    var stateId = ${sessionScope.USER.accessValue};
			  //console.log("panchayatId  :"+panchayatId);
		if($("#isNewCadre").is(':checked'))
			ischecked = "true";  // checked
		else
			ischecked = 0  // unchecked
			
		var villageCovered = '';
		$('#errorDiv').html('');
		if(stateId != 29)
		{
			if(cosntiteucnyId == 0 )
			{
				$('#errorDiv').html('Please Select Constituency.');
				return;
			}
		}
		
	/*	if(panchayatId == 0)
		{
			$('#errorDiv').html('Please Select Panchayat.');
			return;
		}
*/
		var isError = false ;
		
		if(candidateName != null && candidateName.trim().length>0 && !(/^[a-zA-Z ]+$/.test(candidateName)))
		{
				$('#errorDiv').html('Candidate Name allows only alphabets.');
			return;
		}
		  
		if(!isValid(candidateName))
		{
			$('#errorDiv').html('Special Characters not allowed for Candidate Name.');
			return ;
		}
		if(!isValid(voterCardNo))
		{
			var iChars = "`~!@#$%^&*()._-+=}]{[\"':;|\?/><,";		 
			for (var i = 0; i < voterCardNo.length; i++) 
			{
				if (iChars.indexOf(voterCardNo.charAt(i)) != -1) 
				{			
					$('#errorDiv').html('Special Characters not allowed for Voter Card No.');
				return ;
				}
			}
		}
	
		if(!isValid(houseNo))
		{
			var iChars = "`~!@#$%^&*()_+=}]{[\"':;|\?><,.";		 
			for (var i = 0; i < houseNo.length; i++) 
			{
				if (iChars.indexOf(houseNo.charAt(i)) != -1) 
				{			
					$('#errorDiv').html('Special Characters not allowed for House No.');
					return ;
				}
			} 
		}
		
		if((voterCardNo == null || voterCardNo.length == 0) && (houseNo == null || houseNo.length == 0) && (candidateName == null || candidateName.length ==0))
		{
			$('#errorDiv').html('Enter any search criteria for details.');
			 isError = true ;
		}
		
		if(candidateName == null || candidateName.length <=2)
		{	
			if(voterCardNo != null && voterCardNo.length  >=3 )
			{
				 isError = false ;
			}
			else if(houseNo != null && houseNo.length >=1 )
			{						
				  isError = false ;
			} 
			else 
			{
				$('#errorDiv').html('Atleast 3 Characters required for Candidate Name.');
				isError = true ;	
			}		
		}
		else
		{
			 isError = false ;
		}

		if(!isError)
		{			
			$('#errorDiv').html('');
			
			$('#searchDetailsDiv').html('');
			$('#tableElement').hide();
			
			if(typeof(request) != "undefined")
			{
				request.abort();
			}
					
			$('#searchDataImg').show();

			if(stateId == 29)
			{
				cosntiteucnyId = 90237;
				stateId = 0;
				
			}
		
			var jsObj = 
				   {
					  stateId:stateId,
					  constituencyId:cosntiteucnyId,
					  searchType :searchType, 
					  candidateName:candidateName,
					  houseNo : houseNo,
					  voterCardNo : voterCardNo,
					  panchayatId : panchayatId,
					  boothId : boothId ,
					  isPresentCadre : ischecked,
					  startIndex : start,
					  maxIndex:30,
					  task:"searchCandidatesDtailsBySearchCriteria"             
				   }

				   
				   
				request =   $.ajax({
						type : "POST",
						url : "searchVoterAndCadreInfoForOtherStates.action",
						data : {task:JSON.stringify(jsObj)}
					}).done(function(result){
						isSubmit = true;
						$('#searchDataImg').hide();
						if(result != null && result.length >0)
						{
							buildSearchDetails(result,start);
						}
						else
						{
							$('#searchDetailsDiv').html('No Data Available...');
							$("#paginationId").html("");
							$('#tableElement').show();
						}
					});
		}
			
	}
	
	function buildSearchDetails(result,startIndex)
	{

	var str = '';
			str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab">';
			str +='<thead>';
			str +='<tr>';
			str +='<th class="text-align1">PHOTO</th>';
			str +='<th class="text-align1">NAME</th>';
			str +='<th class="text-align1">VOTER ID</th>';
			str +='<th class="text-align1">GUARDIAN NAME</th>';
			str +='<th class="text-align1">RELATION</th>';
			str +='<th class="text-align1">AGE</th>';
			str +='<th class="text-align1">GENDER</th>';
			str +='<th class="text-align1">H.NO</th>';
			str +='</tr>';
			str +='</thead>';
			str +='<tbody>';
			
			for(var i in result){
			 if(result[i].isRegistered == 'Y'){
				str +='<tr  onclick="getDetailsForUser('+result[i].id+',\'Y\',\''+result[i].memberShipId+'\');">';
				str +=' <td style="background-color: #f9f9f9;"><img style="width:80px;height:80px;" class="detailsCls" src="voter_images/'+result[i].image+'" id="'+result[i].id+'" onerror="setDefaultImage(this);" /></td>';
                str +=' <td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].name+'</span></td>';
				str +=' <td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].voterCardNo+'</span></td>';
				str +=' <td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relativeName+'</span></td>';
				str +=' <td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relationType+'</span></td>';
				str +=' <td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].age+'</span></td>';
				str +=' <td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].gender+'</span></td>';
				str +=' <td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].houseNo+'</span></td>';
				str +='</tr>';
			 }else{
				str +='<tr onclick="getDetailsForUser('+result[i].id+',\'N\',\'\');">';
				str +=' <td style="background-color: #f9f9f9;cursor:pointer;"><img style="width:80px;height:80px;" class="detailsCls" src="voter_images/'+result[i].image+'" id="'+result[i].id+'" onerror="setDefaultImage(this);" /></td>';
                str +=' <td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].name+'</span></td>';
				str +=' <td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].voterCardNo+'</span></td>';
				str +=' <td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relativeName+'</span></td>';
				str +=' <td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relationType+'</span></td>';
				str +=' <td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].age+'</span></td>';
				str +=' <td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].gender+'</span></td>';
				str +=' <td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].houseNo+'</span></td>';
				str +='</tr>';
			 }
			}
			
			str +='</tbody>';
			str +='</table>';
		$('#searchDetailsDiv').html(str);
		$('#tableElement').show();
		 
     if(startIndex == 0 && result.length > 0){
		$("#paginationId").pagination({
			items: result[0].count,
			itemsOnPage: 30,
			cssStyle: 'light-theme'
		});
    }
		
	}
	
	function skipDetailsForUser(skip)
	{
		$('#registrationTypeId').val(skip);	
		$('#errorDiv').html('');
		var searchType = $('input[name="searchTypeRadio"]:checked').val();
		var cosntiteucnyId = $('#userConstituencyId').val();
		var stateId = ${sessionScope.USER.accessValue};
		var registrationType = $('#registrationTypeId').val();
		if(stateId == 29)
		{
			window.open('cadreEnrollment.action?id1=90237&id2=0&id3=0&id4=0&searchType='+registrationType);
		}
		else if(stateId != 29 && cosntiteucnyId != 0)
		{
			var registrationType = $('#registrationTypeId').val();	
			var boothId = $('#panchayatList').val();	
			var houseNo = 0;	
			var candidateId = 0;
			window.open('cadreEnrollment.action?id1='+cosntiteucnyId+'&id2='+houseNo+'&id3='+boothId+'&id4='+candidateId+'&searchType='+registrationType);
		}
		else
		{			
			$('#errorDiv').html('Please select Constituency.');
		}	
	}
	
	function getDetailsForUser(candidateId,status,trno)
	{ 
	   $("#relativeTypeIdErr").html("");
	   $('#errorDiv').html('');
	   var aa = $('#familyVoter').is(':checked');
	    if(status == "Y" && !$('#familyVoter').is(':checked')){
			alert("Voter Already Registered As Cadre  with \" "+trno+" \" TR No.If You Are Registering His/Her Family Member Using His/Her VoterId, Please Check CheckBox Present Above Search Button.");
			return;
		}
		
		var searchType = $('input[name="searchTypeRadio"]:checked').val();
		var cosntiteucnyId = $('#userConstituencyId').val();
		var userStateId = ${sessionScope.USER.accessValue};
		
			
		var boothId = 0;	
		var houseNo = $('#panchayatList').val();	
		
		var panchayatId = 0;
		if($("#isNewCadre").is(':checked'))
			panchayatId = "true";  // checked
		else
			panchayatId = 0  // unchecked
		
		if(userStateId != 29)
		{
			if(cosntiteucnyId == null || cosntiteucnyId == 0){
				$('#errorDiv').html('Please Select Constituency.');
				 return;
			}
		}
		
		
		if(userStateId == 29)
		{
			cosntiteucnyId = 90237;		
			houseNo='';
			boothId =0;
		}
		
			var isFamilyVoter = $('#familyVoter').is(':checked'); 
			var isvoterId = $('#voterId').is(':checked'); 
			if(isFamilyVoter)
			{
				$('#registrationTypeId').val('familyVoterId');
			}			
			else if(isvoterId)
			{
				$('#registrationTypeId').val('voterId');
			}
			var registrationTypeId = $('#registrationTypeId').val();
			
		if($('#familyVoter').is(':checked'))
		{
           var relativeTypeId = $("#relativeTypeId").val();
		   if(relativeTypeId == null || relativeTypeId == 0){
			   $("#relativeTypeIdErr").html("Please Select Relation");
			   return;
		   }
		  window.open('cadreEnrollment.action?id1='+cosntiteucnyId+'&id2='+houseNo+'&id3='+boothId+'&id5='+candidateId+'&countDownTime='+relativeTypeId+'&searchType='+registrationTypeId);
		}else{
	
		  window.open('cadreEnrollment.action?id1='+cosntiteucnyId+'&id2='+houseNo+'&id3='+boothId+'&id4='+candidateId+'&searchType='+registrationTypeId);	
		}
		
	}
	
	function getConstituencyWiseDetails()
	{
		var cosntiteucnyId = $('#userConstituencyId').val();
		
		$('#errorDiv').html('');
		$('#searchNameId').val('');
		$('#panchayatList').find('option').remove();
		$('#panchayatList').append('<option value="0"> Select Booth </option>');
		
		//$('#boothsList').find('option').remove();
		//$('#boothsList').append('<option value="0"> Select Booth </option>');
						
		if(cosntiteucnyId == 0 )
		{
			$('#errorDiv').html('Please Select Constituency.');
			return;
		}
		$('#filterSearchDiv').show();
		$('#loadingImg').show();
		var jsObj = 
			   {
				  constituencyId:cosntiteucnyId,				
				  task:"getBoothsForConstituency"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstituncyWiseTehsilDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
						
					$('#loadingImg').hide();
					if(result != null )
					{
						for(var i in result)
						{
							$('#panchayatList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
				});
	}
	
	function getLocationWiseDetails()
	{
		var cosntiteucnyId = $('#userConstituencyId').val();
		var locationId = $('#panchayatList').val();
		$('#errorDiv').html('');
		$('#boothsList').find('option').remove();
		$('#boothsList').append('<option value="0"> Select Booth </option>');
		$('#loadingImg').show();			
		var jsObj = 
			   {
					constituencyId:cosntiteucnyId,	
					locationId : locationId,
					task:"getLocationWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getLocationWiseBoothDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					$('#loadingImg').hide();
					if(result != null )
					{
						for(var i in result)
						{
							$('#boothsList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
				});
	}
	/*
	function getBoothCoverdVillagesDetails()
	{
		var boothsArr = [];	
		var locationId = $('#boothsList').val();		
		boothsArr.push(locationId);
		$('#errorDiv').html('');
		$('#vilagecovrdList').find('option').remove();
		$('#vilagecovrdList').append('<option value="0"> Select Covered Village  </option>');
		$('#loadingImg').show();
		var jsObj = 
			   {
				  boothsArr:boothsArr,				
				  task:"getBoothCoverdVillagesDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getBoothCoverdVillagesDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					$('#loadingImg').hide();				
					if(result != null )
					{
						for(var i in result)
						{
							$('#vilagecovrdList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
						}
					}
				});
	}
	*/
	function getPrintDetails()
	{
		var jsObj = 
			   {
				  memberNo:'TRW0001546209',				
				  task:"getBoothCoverdVillagesDetails"             
			   }	
		 $.ajax({
					type : "POST",
					url : "getCadrePrintDetails.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null)
					{
						var str = '';
						str += '<img src="images/Empty.jpg" style="width:1011px;height:638px;">';
						str += '<div style="position: absolute; top: 0px; left: 0px;">';
						str += '<img src="images/prasad.jpg" style="margin-top: 94px; height: 211px; width: 160px; margin-left: 260px;"></img>';
						str += '<h4 style="font-weight: bold; font-family: Gautami; font-size: 33px; color: yellow; margin-top: -202px; margin-left: 435px;">APN-14</h4>';
						str += '<h4 style="font-weight: bold; font-family: Gautami; font-size: 33px; color: yellow; margin-top: 17px; margin-left: 433px;">05305919</h4>';
						str += '<h4 style="font-weight: bold; font-size: 30px; margin-top: 26px; margin-left: 439px;">'+result.districtName+'</h4>';
						str += '<h4 style="font-weight: bold; font-size: 30px; margin-top: -2px; margin-left: 437px;">'+result.districtName+'  '+result.districtName+' </h4>';
						str += '<h4 style="font-weight: bold; font-size: 30px; margin-top: 14px; margin-left: 438px;">'+result.districtName+' </h4>';
						str += '</div>';
						$('#myDiv').html(str);
						printDiv('myDiv');
					}
					
				});
	
	}
	function printDiv(divName) 
	{
		 var printContents = document.getElementById(divName).innerHTML;
		 var originalContents = document.body.innerHTML;
		 document.body.innerHTML = printContents;
		 document.body.innerHTML = originalContents;
		 window.print();
	}
	//getCadreDetailsByPanchayat();
	function getCadreDetailsByPanchayat()
	{
		var jsObj = 
			   {
				  panchayatId:'3297',				
				  task:"getSelectedLevelCadreDetails"             
			   }	
		 $.ajax({
					type : "POST",
					url : "getSelectedLevelCadreDetails.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result != null)
					{
						var str = '';
						str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab">';
						str +='<thead>';
						str +='<tr>';
						str +='<th class="text-align1">TR NUMBER</th>';
						str +='<th class="text-align1">VOTER NAME</th>';
						str +='<th class="text-align1">OPTIONS</th>';
						str +='</tr>';
						str +='</thead>';
						str +='<tbody>';
						
						for(var i in result)
						{
							str +='<tr>';
							str +='<td>'+result[i].name+'</td>';
							str +='<td>'+result[i].mandalName+'</td>';
							str +='<td><a class="btn btn-success" onClick="openFormForCadreDetails(\''+result[i].name+'\')">Get Details</a></td>';
							str +='</tr>';
						}
						
						str +='</tbody>';
						str +='</table>';
						
						$('#tableDivForCadre').html(str);
					}
					
				});
	}
	
	function openFormForCadreDetails(refNum)
	{
		var jsObj = 
			   {
				  memberNo:refNum,				
				  task:"getBoothCoverdVillagesDetails"             
			   }	
		 $.ajax({
					type : "POST",
					url : "getCadrePrintDetails.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					var str = '';
						str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab">';
						str +='<thead>';
						str +='<tr>';
						str +='<th class="text-align1">MEMBER SHIP NUMBER</th>';
						str +='<th class="text-align1">VOTER NAME</th>';
						str +='<th class="text-align1">RELATIVE NAME</th>';
						str +='<th class="text-align1">DISTRICT</th>';
						str +='<th class="text-align1">CONSTITUENCY</th>';
						str +='<th class="text-align1">MANDAL</th>';
						str +='<th class="text-align1">VILLAGE</th>';
						str +='<th class="text-align1">NFC NUMBER</th>';
						str +='<th class="text-align1">TAG</th>';
						str +='</tr>';
						str +='</thead>';
						str +='<tbody>';

							str +='<tr>';
							str +='<td>'+result.firstCode+'</td>';
							str +='<td>'+result.voterName+'</td>';
							str +='<td>'+result.relativeName+'</td>';
							str +='<td>'+result.districtEng+'</td>';
							str +='<td>'+result.constiEng+'</td>';
							str +='<td>'+result.mandalEng+'</td>';
							str +='<td>'+result.villageEng+'</td>';
							str +='<td><input type="text" id="cardNumber"></input></td>';
							str +='<td><a class="btn btn-success" onClick="tagVoterNFCNumber(\''+result.firstCode+'\','+result.voterId+')">Get Details</a></td>';
							str +='</tr>';

						
						str +='</tbody>';
						str +='</table>';
						$('#tableDivForCadre').html(str);
				});
	}
	function setDefaultImage(img)
	{
		img.src = "images/mahaNadu/user image.jpg";
	}

	function tagVoterNFCNumber(cardNumber,voterId)
	{
			var jsObj = 
			   {
				  cardNo:cardNumber,	
				  voterId:voterId,					  
				  task:"tagCardIdForNFCReader"             
			   }	
		 $.ajax({
					type : "POST",
					url : "tagCardIdForNFCReader.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					if(result == 'success')
					{
						getPrintDetails();
					}
				});
	}
	
	function showDashBoard() 
	{	
		$('#yourElement').show();
		$('#dashboadElmnt').hide();		
		$('#statusDivsId1').show();
		$('#statusDivsId2').hide();
		$('#searchDetailsDiv').html('');
		$('#dashBoadDiv').html('');
	}
	function hideDashBoard()
	{
		$('#yourElement').hide();
		$('#dashboadElmnt').show();		
		$('#statusDivsId1').hide();
		$('#tableElement').hide();
		$('#statusDivsId2').show();
		$('#searchDetailsDiv').html('');
		$('#dashBoadDiv').html('');
	}
	function getDashboardDetailsForUser()
	{
		$('#dashBoadDiv').html('');
		$('#searchDashboardImg').show();
		//var userId = $('#webUserId).val();
		var formDate = $('#fromDateId').val();
		var toDate = $('#toDateId').val();
		$('#searchDetailsDiv').html('');
		var jsObj = 
			   {
				  userId:0,	 // 4015
				  fromDate:formDate,					  
				  toDate:toDate,					  
				  task:"getDaywiseWebUserDetails"             
			   }	
		 $.ajax({
					url : "getDaywiseWebUserDetailsAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
				$('#searchDashboardImg').hide();
					if(result != null)
					{
						buildDashBoardDetails(result);
					}
				});
				
	}
	
	function buildDashBoardDetails(result)
	{
		var str='<div class="span12" style="font-weight:bold;">';
		str+='<div class="span3 show-grid" style="background-color:#D3D3D3;padding:5px;"> Total Records :'+result.recordsCount+' </div> ';
		str+='<div class="span3 show-grid" style="background-color:#D3D3D3;padding:5px;"> Total Amount :'+result.actualAmount+' </div> ';
		str+='<div class="span3 show-grid" style="background-color:#D3D3D3;padding:5px;"> Paid  Amount :'+result.depositedAmount+' </div> ';
		str+='<div class="span3 show-grid" style="background-color:#D3D3D3;padding:5px;"> Balance Amount  :'+result.remainingAmount+' </div> ';
			str+='</div><br></br>';
			str +='<h4 align="center" > USER DAY WISE REPORT </h4>';
			str+='<table id="dayWiseUsersDetailsId" class="table table-bordered ">';
			str+='<thead>';
			str+='<tr>';
			str+='<th> Survey Date </th>';
			str+='<th> Total Records </th>';
			str+='<th> Total Amount </th>';
			str+='<th> Depositted Amount </th>';
			str+='<th> Balance Amount </th>';
			str+='</tr>';
			str+='</thead>';
			str+='<tbody>';			
			if(result.surveyTransactionVOList != null && result.surveyTransactionVOList.length >0)
			{
				for(var i in result.surveyTransactionVOList)
				{
					str+='<tr>';
						str+='<td style="text-align:center">'+result.surveyTransactionVOList[i].surveyDate+'</td>';
						if(result.surveyTransactionVOList[i].recordsCount != 0)
						{
							str+='<td style="text-align:center">'+result.surveyTransactionVOList[i].recordsCount+'</td>';
						}
						else
						{
							str+='<td style="text-align:center"> -- </td>';
						}
						if(result.surveyTransactionVOList[i].actualAmount != 0)
						{
							str+='<td style="text-align:center">'+result.surveyTransactionVOList[i].actualAmount+'</td>';
						}
						else
						{
							str+='<td style="text-align:center"> -- </td>';
						}
						if(result.surveyTransactionVOList[i].depositedAmount != 0)
						{
							str+='<td style="text-align:center">'+result.surveyTransactionVOList[i].depositedAmount+'</td>';
						}
						else
						{
							str+='<td style="text-align:center"> -- </td>';
						}
						if(result.surveyTransactionVOList[i].remainingAmount != 0)
						{
							str+='<td style="text-align:center">'+result.surveyTransactionVOList[i].remainingAmount+'</td>';
						}
						else
						{
							str+='<td style="text-align:center"> -- </td>';
						}
					str+='</tr>';
				}
			}
					
			str+='</tr>';
			str+='</tr>';
			str+='</tbody>';
			str+='</table>';
			
			$('#dashBoadDiv').html(str);
		$('#dayWiseUsersDetailsId').dataTable({
				  "aaSorting": [[ 0, "desc" ]],
			      "iDisplayLength": 10,
			      "aLengthMenu": [[10,20,50, 100, 200, -1], [10,20,50, 100, 200, "All"]]
		         });
	}
	
	function handleRequest(requestType)
	{
		$('#searchBtnId').hide();
		$('#tableElement').hide();
		$('#showhideRelativeTypeId').hide();
		$('#skipButtonId').show();
		$('#relativeTypeId').val(0);
		$('#searchDetailsDiv').html('');
		if(requestType =='voterId')
		{
			$('#searchBtnId').show();
			$('#skipButtonId').hide();
			$('#registrationTypeId').val('voterId');
			
		}
		else if(requestType =='familyVoter')
		{
			$('#searchBtnId').show();
			$('#showhideRelativeTypeId').show();
			$('#skipButtonId').hide();
			$('#registrationTypeId').val('familyVoterId');
			$('#relativeTypeId').val(0);
			
		}		
		else if(requestType =='apVoterId')
		{
			$('#registrationTypeId').val('apVoterId');
		}
		else if(requestType =='noVoter')
		{
			$('#registrationTypeId').val('noVoterId');
		}		
	}
	
	getAllRelationDetails();
		</script>
		 <script>$('#yourElement').addClass('animated fadeInDown');
		 
$( "#stateLocationId" ).change(function() {
	var stateId= $(this).val();
	getConstituenciesOfState();
	if(stateId == 0){
		setTimeout(function(){
			$('#consituenctDetailsId').append('<option value="0"> Select Constituency </option>');
		}, 600);
		
		
	}
	});
	function getConstituenciesOfState(){
		
		
		var stateId= $("#stateLocationId").val();
		var jsObj = 
		   {
			 stateId : stateId            
		   }	
			$.ajax({
				type : "POST",
				url : "getConstituencyByStateAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				var str='';
				if(result != null && result.length > 0){
					str+='<option value="0">Select Constituency</option>';
				   for(var i in result){
						str+='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
				  }
				
				$("#consituenctDetailsId").html(str);
			});
	}

	
</script>
	<!----->
	

  </body>
</html>