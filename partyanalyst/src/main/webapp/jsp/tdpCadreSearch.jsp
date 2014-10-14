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
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet"/>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <script src="js/icheck/icheck.js"></script>

	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	
	<style>
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
	</style>
   
	
</head>
  <body class="bgc">
  
  	<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<img src="images/cadre_images/2014-cadre-Registration-Logo.png" />
			</div>
		</div><!-- Header Row End-->
		
	<div class="container" id="yourElement">
	<div id="myDiv"></div>
	<div id="tableDivForCadre" class="table-responsive"></div>
	
		<div class="span6 offset3 show-grid pad-10b" style="">
		<div id="errorDiv" style="color:#ff0020;"></div>
			<h5 class="text-align">SELECT CONSTITUENCY</h5>

			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:460px;" onChange="getConstituencyWiseDetails();"/>
			<select style="width:150px;" id="panchayatList" onchange="getLocationWiseDetails();"><option value="0"> Select Location </option></select>		
			<select style="width:250px;" id="boothsList"> <option value="0"> Select Booth </option> </select> 	
			<!-- <select style="width:150px;" id="vilagecovrdList"> <option value="0"> Select Covered Village </option> </select>  -->
			<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
				
				<h5 class="text-align small m_top15">SEARCH BY</h5>
					<div class="span6">
					
						<div class="row form-inline text-center">
									<label class="radio"><input type="radio" value="voter"  name="searchTypeRadio" checked="true" class="icheckBtns"> VOTER</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio"><input type="radio" value="cadre"  name="searchTypeRadio"  class="icheckBtns"> CADRE</label>
								
									&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="checkbox"><input type="checkbox" value="2014Cadre" id="isNewCadre"  name="searchTypecheckbox"  class="icheckBtns"> 2014 CADRE </label>
						</div>
					</div>
					
					<div class="pad-10b">
					<h5 class="text-align1">CANDIDATE NAME</h5>
							<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="searchNameId" name="searchName" style="width:425px;" onkeyUp="searchCandidatesDetailsBySearchCriteria();">
					</div>
					<div class=" m_top10 pad-10b">
						<div class="row-fluid">
						
							<div class="span6">
							<h5 class="text-align1">VOTER ID</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Voter ID"  id="searchVoterCardId"  name="searchVoterCard" onkeyUp="searchCandidatesDetailsBySearchCriteria();">
							</div>
							
							<div class="span6">
							<h5 class="text-align1">H NO</h5>
								<input type="text" class="form-control border-radius-0" placeholder="House Number"  id="searchHNoId"   name="searchHNo" onkeyUp="searchCandidatesDetailsBySearchCriteria();">
							</div>
						</div>
					</div>
					<a href="javascript:{searchCandidatesDetailsBySearchCriteria();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>
		
	</div>
	<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style=" margin-left: 660px;margin-top: 20px;width:70px;height:60px;display:none;"/>
	<div class="container" id="tableElement" style="margin-top:25px;display:none;">
		<div class="span10 offset1 show-grid pad-5 m-bottom-10">
		<a class="btn btn-default active col-xs-1 m_top20 pull-right border-radius-0 " href="javascript:{getDetailsForUser(0)}">Skip 	
			<span class="glyphicon glyphicon-chevron-right"></span></a>
			<h3 class="text-align">SEARCH DETAILS</h3>
			<div class="table-responsive" id="searchDetailsDiv" ></div>

		</div>
	</div>

		<!-- Footer Row -->
		<div class="row-fluid">
			<div class="span12 text-center m_top5 color-white">
					Cadre Registration Drive
				<p>Copyright &copy; 2014,  All Rights Reserved</p>
			</div>
		</div>
	<!-- Footer Row End-->
	
	<script>
		$(document).ready(function(){
		  $('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		 
/*
			
			 $('input[name="searchTypeRadio"]').on('ifClicked', function (event) {
				searchCandidatesDetailsBySearchCriteria();
				
			});
			*/
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
	function searchCandidatesDetailsBySearchCriteria()
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
	

		if($("#isNewCadre").is(':checked'))
			ischecked = "true";  // checked
		else
			ischecked = 0  // unchecked
			
		var villageCovered = '';
		$('#errorDiv').html('');
		if(cosntiteucnyId == 0 )
		{
			$('#errorDiv').html('Please Select Constituency.');
			return;
		}
	/*	if(panchayatId == 0)
		{
			$('#errorDiv').html('Please Select Panchayat.');
			return;
		}
*/
		var isError = false ;
		
		if(candidateName != null && candidateName.trim().length>0 && !(/^[a-zA-Z]+$/.test(candidateName)))
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
			else if(houseNo != null && houseNo.length >=3 )
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

			var jsObj = 
				   {
					  constituencyId:cosntiteucnyId,
					  searchType :searchType, 
					  candidateName:candidateName,
					  houseNo : houseNo,
					  voterCardNo : voterCardNo,
					  panchayatId : panchayatId,
					  boothId : boothId ,
					  isPresentCadre : ischecked,
					  task:"searchCandidatesDtailsBySearchCriteria"             
				   }

				   
				   
				request =   $.ajax({
						type : "POST",
						url : "searchVoterAndCadreInfoBySearchCriteriaAction.action",
						data : {task:JSON.stringify(jsObj)} ,
					}).done(function(result){
						isSubmit = true;
						$('#searchDataImg').hide();
						if(result != null && result.length >0)
						{
							buildSearchDetails(result);
						}
						else
						{
							$('#searchDetailsDiv').html('No Data Available...');
							$('#tableElement').show();
						}
					});
		}
			
	}
	
	function buildSearchDetails(result)
	{

	var str = '';
			str +='<table class="table table-bordered m_top20 table-hover table-striped"  id="seachDetalsTab">';
			str +='<thead>';
			str +='<tr>';
			str +='<th class="text-align1">PHOTO</th>';
			str +='<th class="text-align1">NAME</th>';
			str +='<th class="text-align1">GUARDIAN NAME</th>';
			str +='<th class="text-align1">RELATION</th>';
			str +='<th class="text-align1">AGE</th>';
			str +='<th class="text-align1">GENDER</th>';
			str +='<th class="text-align1">H.NO</th>';
			//str +='<th class="text-align1"></th>';
			str +='</tr>';
			str +='</thead>';
			str +='<tbody>';
			
			for(var i in result)
			{
			if(result[i].isRegistered == 'Y')
			{
				str +='<tr>';
				str +='<td  style="background-color: #f9f9f9;"><img style="width:80px;height:80px;" src="'+result[i].image+'" id="candimgShowIdReg'+i+'" onerror="setDefaultImage(this);" /></td>';
				if(result[i].name != null)
					str +='<td style="background-color:#52A552;cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].name+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
					
				if(result[i].relativeName != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relativeName+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				if(result[i].relationType != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relationType+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';					
				if(result[i].age != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].age+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				if(result[i].gender != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].gender+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				
				if(result[i].houseNo != null)
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].houseNo+'</span></td>';
				else
					str +='<td style="background-color:#52A552;"><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				
				//str +='<td style="background-color:#52A552;"><input type="radio" value="'+result[i].id+'" name="optionsRadios" onClick="getDetailsForUser();"></label></td>';
				str +='</tr>';
			}
			else
			{
				str +='<tr>';
				str +='<td style="background-color: #f9f9f9;"><img style="width:80px;height:80px;" src="'+result[i].image+'" id="candimgShowId'+i+'" onerror="setDefaultImage(this);" /></td>';
				if(result[i].name != null)
					str +='<td style="cursor:pointer;"><span  class="detailsCls" id="'+result[i].id+'">'+result[i].name+'</span></td>';
				else
					str +='<td><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
					
				if(result[i].relativeName != null)
					str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relativeName+'</span></td>';
				else
					str +='<td><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				if(result[i].relationType != null)
					str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relationType+'</span></td>';
				else
					str +='<td><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';					
				if(result[i].age != null)
					str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].age+'</span></td>';
				else
					str +='<td><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				if(result[i].gender != null)
					str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].gender+'</span></td>';
				else
					str +='<td><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				
				if(result[i].houseNo != null)
					str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].houseNo+'</span></td>';
				else
					str +='<td><span  class="detailsCls" id="'+result[i].id+'"> -- </span></td>';
				
			//	str +='<td><input type="radio" value="'+result[i].id+'" name="optionsRadios" onClick="getDetailsForUser();"></label></td>';
				str +='</tr>';
			}
			}
			
			str +='</tbody>';
			str +='</table>';
		$('#searchDetailsDiv').html(str);
		$('#tableElement').show();
		 
		 $('#seachDetalsTab').dataTable({
			"iDisplayLength": 100,
			"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
		/*	
			$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		  
		   $('input[name="optionsRadios"]').on('ifClicked', function (event) {
				//alert("You clicked " + this.value);
				getDetailsForUser(this.value);
				
			});
			*/
			 $(".detailsCls").click(function(){
			var id = $(this).attr('id');
			getDetailsForUser(id);
		  
		  });
	}
	
	function getDetailsForUser(candidateId)
	{
		//var candidateId = $('input[name="optionsRadios"]:checked').val();
		var searchType = $('input[name="searchTypeRadio"]:checked').val();
		var cosntiteucnyId = $('#userConstituencyId').val();	
		var boothId = $('#boothsList').val();	
		var houseNo = $('#panchayatList').val();	
		
		var panchayatId = 0;
		if($("#isNewCadre").is(':checked'))
			panchayatId = "true";  // checked
		else
			panchayatId = 0  // unchecked
			
		window.open('tdpCadreRegistrationAction.action?candidateId='+candidateId+'&searchType='+searchType+'&constiteucnyId='+cosntiteucnyId+'&houseNo='+houseNo+'&boothId='+boothId+'&panchayatId='+panchayatId+'');
		
	}
	
	function getConstituencyWiseDetails()
	{
		var cosntiteucnyId = $('#userConstituencyId').val();
		
		$('#errorDiv').html('');
		$('#panchayatList').find('option').remove();
		$('#panchayatList').append('<option value="0"> Select Location </option>');
		
		$('#boothsList').find('option').remove();
		$('#boothsList').append('<option value="0"> Select Booth </option>');
						
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
				  task:"getConstituncyWiseDetails"             
			   }				   
			   $.ajax({
					type : "POST",
					url : "getConstituncyWiseDetailsAction.action",
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
					url : "getLocationWiseDetailsAction.action",
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
		</script>
		 <script>$('#yourElement').addClass('animated fadeInDown');</script>
	<!----->
	
  </body>
</html>