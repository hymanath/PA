<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
	
	</style>
   
	
</head>
  <body>
	<div class="container" id="yourElement">
	<div id="errorDiv" style="color:#ff0020;" class="span6 offset2 show-grid pad-10b"></div>
		<div class="span6 offset2 show-grid pad-10b" style="">
			<h5 class="text-align">SELECT CONSTITUENCY</h5>

			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:460px;" onChange="getConstituencyWiseDetails();"/>
			<select style="width:150px;" id="panchayatList" onchange="getLocationWiseDetails();"><option value="0"> Select Panchayat </option></select>		
			<select style="width:150px;" id="boothsList" onchange="getBoothCoverdVillagesDetails();"> <option value="0"> Select Booth </option> </select> 	<select style="width:150px;" id="vilagecovrdList"> <option value="0"> Select Covered Village </option> </select> 
			<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
				
				<h5 class="text-align small m_top15">SEARCH BY</h5>
					<div class="span6">
					
						<div class="row form-inline text-center">
									<label class="radio"><input type="radio" value="voter"  name="searchTypeRadio" checked="true"> VOTER</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio"><input type="radio" value="cadre"  name="searchTypeRadio"> CADRE</label>
								
						</div>
					</div>
					
					<div class="pad-10b">
					<h5 class="text-align1">CANDIDATE NAME</h5>
							<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="searchNameId" name="searchName" style="width:425px;" onkeyUp="searchCandidatesDetailsBySearchCriteria(1);">
					</div>
					<div class=" m_top10 pad-10b">
						<div class="row-fluid">
						
							<div class="span6">
							<h5 class="text-align1">VOTER ID</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Voter ID"  id="searchVoterCardId"  name="searchVoterCard" onkeyUp="searchCandidatesDetailsBySearchCriteria(1);">
							</div>
							
							<div class="span6">
							<h5 class="text-align1">H NO</h5>
								<input type="text" class="form-control border-radius-0" placeholder="House Number"  id="searchHNoId"   name="searchHNo" onkeyUp="searchCandidatesDetailsBySearchCriteria(1);">
							</div>
						</div>
					</div>
					<a href="javascript:{searchCandidatesDetailsBySearchCriteria(2);}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>
		
	</div>
	<img src='images/Loading-data.gif' class="offset7"  id="searchDataImg" style="width:70px;height:60px;display:none;"/>
	<div class="container" id="tableElement" style="margin-top:25px;display:none;">
		<div class="col-xs-8 col-xs-offset-2 show-grid">
		<a class="btn btn-default active col-xs-1 m_top20 pull-right border-radius-0 " href="javascript:{getDetailsForUser(0)}">Skip 	
			<span class="glyphicon glyphicon-chevron-right"></span></a>
			<h3 class="text-align">SEARCH DETAILS</h3>
			<div class="table-responsive" id="searchDetailsDiv" ></div>

		</div>
	</div>

	<script>
		$(document).ready(function(){
		  $('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		 
		});
		
	var request;	
	function searchCandidatesDetailsBySearchCriteria(type)
	{
	
		var cosntiteucnyId = $('#userConstituencyId').val();
		var candidateName = $('#searchNameId').val();
		var voterCardNo = $('#searchVoterCardId').val();
		var houseNo = $('#searchHNoId').val();
		var searchType = $('input[name="searchTypeRadio"]:checked').val();
		var panchayatId = $('#panchayatList').val();
		var boothId = $('#boothsList').val();
		var villageCoveredId = $('#vilagecovrdList').val(); 
		var villageCovered = '';
		$('#errorDiv').html('');
		if(cosntiteucnyId == 0 )
		{
			$('#errorDiv').html('Please Select Constituency.');
			return;
		}
		if(panchayatId == 0)
		{
			$('#errorDiv').html('Please Select Panchayat.');
			return;
		}
		
		var isError = false ;
		
		
			if(voterCardNo == null || voterCardNo.length == 0)
			{	
				$('#errorDiv').html('Enter any search criteria for details.');
				 isError = true ;
			}
			else
			{
				isError = false ;
			}
			
			if(houseNo == null || houseNo.length == 0)
			{	
				$('#errorDiv').html('Enter any search criteria for details.');
				 isError = true ;
			}
			else
			{
				isError = false ;
			}
			
			if(candidateName == null || candidateName.length <=2)
			{	
				$('#errorDiv').html('Candidate Name should containse atleast 3 Charactors.');
				 isError = true ;
			}
			else
			{
				isError = false ;
			}
			
			if((voterCardNo == null || voterCardNo.length == 0) && (houseNo == null || houseNo.length == 0) && (candidateName == null || candidateName.length <=2))
			{
				$('#errorDiv').html('Enter any search criteria for details.');
				 isError = true ;
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
		
			if(villageCoveredId != 0)
			{
				villageCovered = $("#vilagecovrdList option:selected").text();
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
					  locationId : villageCovered,
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
			str +='<th class="text-align1">NAME</th>';
			str +='<th class="text-align1">GUARDIAN NAME</th>';
			str +='<th class="text-align1">AGE</th>';
			str +='<th class="text-align1">GENDER</th>';
			str +='<th class="text-align1">RELATION</th>';
			str +='<th class="text-align1">H.NUMBER</th>';
			str +='</tr>';
			str +='</thead>';
			str +='<tbody>';
			
			for(var i in result)
			{
				str +='<tr>';
				str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].name+'</span></td>';
				str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relativeName+'</span></td>';
				str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].age+'</span></td>';
				str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].gender+'</span></td>';
				str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].relationType+'</span></td>';
				str +='<td><span  class="detailsCls" id="'+result[i].id+'">'+result[i].houseNo+'</span><label class="pull-right">';
				str +='<input type="radio" value="'+result[i].id+'" name="optionsRadios" onClick="getDetailsForUser();"></label></td>';
				str +='</tr>';
			}
			
			str +='</tbody>';
			str +='</table>';
		$('#searchDetailsDiv').html(str);
		$('#tableElement').show();
		 
		 $('#seachDetalsTab').dataTable({
			"iDisplayLength": 100,
			"aLengthMenu": [[100, 200, 500, -1], [100, 200, 500, "All"]]
			});
			
			$('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		  
		   $('input[name="optionsRadios"]').on('ifClicked', function (event) {
				//alert("You clicked " + this.value);
				getDetailsForUser(this.value);
				
			});
			 $(".detailsCls").click(function(){
			var id = $(this).attr('id');
			getDetailsForUser(id);
		  
		  });
	}
	
	function getDetailsForUser(candidateId)
	{
		//var candidateId = $('input[name="optionsRadios"]:checked').val();
		var searchType = $('input[name="searchTypeRadio"]:checked').val();

		window.open('tdpCadreRegistrationAction.action?candidateId='+candidateId+'&searchType='+searchType+'');
		
	}
	
	function getConstituencyWiseDetails()
	{
		var cosntiteucnyId = $('#userConstituencyId').val();
		
		$('#errorDiv').html('');
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
						$('#panchayatList').find('option').remove();
						$('#panchayatList').append('<option value="0"> Select Panchayat </option>');
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
	
		function getBoothCoverdVillagesDetails()
	{
		var boothsArr = [];	
		var locationId = $('#boothsList').val();		
		boothsArr.push(locationId);
		$('#vilagecovrdList').find('option').remove();
		$('#vilagecovrdList').append('<option value="0"> Select Booth </option>');
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
	
		</script>
		 <script>$('#yourElement').addClass('animated fadeInDown');</script>
	<!----->
	
  </body>
</html>