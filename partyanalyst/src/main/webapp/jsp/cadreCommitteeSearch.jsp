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
    <title>Cadre Committee Search</title>
    
    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/cadreCommittee/bootstrap.min.js"></script>
	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/cadreCommittee.js"></script>
	<style>
		.required{color:red;}
	</style>
</head>
<body>
	<div class="container">
			<!-------ADD BLOCK------>
			<div class="row-fluid" id="searchcadrenewDiv">	
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 text-center">
					<h4>SEARCH BASED ON</h4>
					<hr style="margin: 0px;">
				</div>
				
				<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center">
					<div class="form-inline m_top20">
						<div class="radio">
							<label><input type="radio" name="searchBasedOn" checked="true" class="searchTypeCls" id="membershipId" value="1"> Membership ID &nbsp;&nbsp;</label>
						
							<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="voterId"  value="2" > Voter ID &nbsp;&nbsp;</label>
						
							<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="mobileNo"  value="3"> Mobile No &nbsp;&nbsp;</label>
						
							<label><input type="radio" name="searchBasedOn" class="searchTypeCls" id="name"  value="4"> Name &nbsp;&nbsp;</label>

							<input type="hidden" id="cadreSearchType" value="membershipId" />
						</div>				  
					</div>
				</div>
				<div class="row">
					<div id="constitunecyId" class="span2 offset2 m_top10"></div>
					<div id="mandalId" class="span2 m_top10"></div>	
					<div id="panchayatId" class="span2 m_top10"></div>	
					<div id="boothId" class="span2 m_top10"></div>
				</div>
				<div id="constitunecyIdError" class="span3 offset2 required" style="margin-left:182px;"></div>
				<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">	
					<div class="row">      
						<div class="span8 offset1 ">
							<input type="text" placeholder="ENTER NO / NAME"  class="form-control" id="searchBy" style="height: 34px;">
						</div>	
						<div class="span2 ">
							<button class="btn btn-success btn-block" type="button" id="searchFunctiionId" >SEARCH</button>
							<img id="ajaxImgStyleNew" style="display:none;" src="images/icons/search.gif"/>
						</div>			
					</div>			
				</div>
				<div class="span3 offset3 required" id="searchTextBoxError" style="margin-left: 254px;"></div>
			</div>
			<div class="col-md-8 col-md-offset-2 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 m_top20">
				<div class="well well-sm" style="background: none repeat scroll 0% 0% rgba(0, 0, 0, 0.1); border: medium none transparent;margin-bottom:2px;" id="cadreDetailsDiv"></div>			
			</div>
	</div>
	
	<footer class="text-center m_top20">
			&copy; 2015 Telugu Desam Party
	</footer>

	<!-- Script -->
	<script>
	$('.searchTypeCls').click(function(){
			 $("#constitunecyId  option").remove();
			 $("#mandalId  option").remove();
			 $("#searchBy").val('');
			 $("#searchTextBoxError").html('');
			 
			var id = $(this).attr('id');
			$('#advancedSearchDiv').hide();
			$('#constitunecyId').hide();
			$('#mandalId').hide();
			$('#boothId').hide();
			$('#panchayatId').hide();
			
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
				$('#constitunecyId').show();

				$('#cadreSearchType').val('name');
				gettingAllConstituencys("ALL");
			}
		});
		function gettingAllConstituencys(repType){
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
						str+='<select class="input-block-level" id="constituencyDispalyId" onchange="getTehsils();">';
						str+='<option value="0">Select Constituency</option>';
						for(var i in allConstituencies){
							str+='<option value='+allConstituencies[i].id+'>'+allConstituencies[i].name+'</option>';
						}
						str+='</select>';
						$("#constitunecyId").html(str);
				}
			   });
		}
		 function getTehsils(){
			$("#panchayatId").hide();
			$("#boothId").hide();
			$("#constitunecyIdError").html('');
			
			var str='';
			var constiId =$("#constituencyDispalyId").val();
			
		//	  $("#mandalDispalyId option").remove();
			  if(constiId == 0){
				$("#mandalId").hide();
				return;
			  }
			  var jsObj=
				{
					constituencyId:constiId			
				}
			$.ajax({
				  type:'GET',
				  url: 'gettingTehsilsByConstituencyAction.action',
				  dataType: 'json',
			  	  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result !=null){
				$('#mandalId').show();
				str+='<select class="input-block-level" id="mandalDispalyId" onchange="getPanchayat();">';
				str+='<option value="0">Select Location</option>';
						for(var i in result){
							str+='<option value='+result[i].locationId+'>'+result[i].locationName+'</option>';
						}
				str+='</select>';
				$("#mandalId").html(str);
			}
	   });	
	}
	
	function getPanchayat(){
			$("#panchayatId").show();
			$("#boothId").hide();
		var mandalDispalyId=$("#mandalDispalyId").val();
		
		// $("#panchayatId option").remove();
		 
		   if(mandalDispalyId == 0){
				$("#panchayatId").hide();
				return;
			  }
		 
		var str='';
	/*	var mandalId;
			if(mandalDispalyId.substr(0,1)==2){
					mandalId=mandalDispalyId.substr(1);
					return;
			}
			else if(mandalDispalyId.substr(0,1)==1){
					mandalId=mandalDispalyId.substr(1);
					return;
			}*/
			var jsObj={
				mandalId :mandalDispalyId
			}
			$.ajax({
				type:"POST",
				url :"getPanchayatDetailsAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
					if(result !=null){
					$("#panchayatId").show();
				str+='<select class="input-block-level" id="panchayatDisplayId" onchange="getBoothDetails();">';
						str+='<option value="0">Select Panchayat/Ward</option>';
						for(var i in result){
							str+='<option value='+result[i].id+'>'+result[i].name+'</option>';
						}
				str+='</select>';
				$("#panchayatId").html(str);
			}
			});
	}
	function getBoothDetails(){
	$("#boothId").show();
	
		var panchayatId = $("#panchayatDisplayId").val();
		var mandalDispalyId=$("#mandalDispalyId").val();
		
		if(panchayatId ==0){
			$("#boothId").hide();
			return;
		}
		// $("#boothId option").remove();
		 
		 
		var str='';
		if(mandalDispalyId.substr(0,1)==1){
		$("#boothId").hide();
			return;
		}
		var jsObj={
				panchayatId :panchayatId
			}
		  $.ajax({
				type:"POST",
				url :"getBoothDetailsAction.action",
				 dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(result !=null){
				$("#boothId").show();
					str+='<select class="input-block-level" id="boothDisplayId">';
					str+='<option value="0">Select Booth</option>';
					for(var i in result){
						str+='<option value='+result[i]+'>Booth - '+result[i]+'</option>';
					}
					str+='</select>';
					$("#boothId").html(str);
				}
			});
	}
	$(document).on("click","#searchFunctiionId",function(e){
	var constituencyId = $('#constituencyDispalyId').val();
		//getCadreDetailsBySearchCriteria();
		
		var searchTextBox=$('#searchBy').val().trim();

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
		
		$('#cadreDetailsDiv').html('');
		
		var searchRadioType = $('#cadreSearchType').val();
		
		if(searchRadioType == 'membershipId')
		{
			memberShipCardNo = $('#searchBy').val().trim();
		}			
		if(searchRadioType == 'voterId')
		{
			voterCardNo = $('#searchBy').val().trim();
		}
		if(searchRadioType == 'mobileNo')
		{
			mobileNo = $('#searchBy').val().trim();
		}
		if(searchRadioType == 'name')
		{
			searchName = $('#searchBy').val().trim();
			var mandalId = $('#mandalDispalyId').val();
		    var	panchayatId=$('#panchayatDisplayId').val();

			var boothId=$('#boothDisplayId').val();
			if(constituencyId == 0)
			{
				$("#constitunecyIdError").html("Please Select constituency.");	
				return;			
			}
			if(mandalId == 0)
			{
				locationValue = constituencyId;
				locationLevel = 4;				
			}
			else 
			{
				locationValue = mandalId.substr(1);
				locationLevel = 5;
			}
			if(mandalId.substr(0,1)==2){
				locationValue = mandalId.substr(1);
				locationLevel = 5;
				
				if(panchayatId ==0){
					locationValue= mandalId.substr(1);
					locationLevel = 5;
				}
				else{
					locationValue= panchayatId;
					locationLevel = 6;
				}
				if(boothId ==0 && panchayatId !=0){
					locationValue= panchayatId;
					locationLevel = 6;
				}
				else if(boothId !=0 && panchayatId !=0){
					locationValue= boothId;
					locationLevel = 9;
				}
			}
			else if(mandalId.substr(0,1)==1){
				if(panchayatId ==0){
					locationValue= mandalId.substr(1);
					locationLevel = 7;
				}
				else{
					locationValue= panchayatId;
					locationLevel = 8;
				}
			
			}
		}
		
		if(searchTextBox.length ==0){
			$("#searchTextBoxError").html("Please Enter Number/Name.");
			return;
		}
		
		$("#ajaxImgStyleNew").show();
		var jsObj =
		{
			locationLevel :locationLevel,
			locationValue:locationValue,
			searchName :searchName,
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
			enrollmentId : 3,
			removedStatus:"false",
			task:"search"
		}
			$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				$("#searchTextBoxError").html('');
				$("#ajaxImgStyleNew").hide();
				/*if(result != null && result.tdpCadreDetailsList != null && result.tdpCadreDetailsList.length>0)
				{
					buildCadreDetails(result.tdpCadreDetailsList);
				}*/
			if(result.previousRoles != null && result.previousRoles != null && result.previousRoles.length>0)
				{
					buildCadreDetails(result.previousRoles);
				}
				else
				{
					$('#cadreDetailsDiv').html("No data Available");
					console.log("no data available...");
				}
			});
		 
	});
	function buildCadreDetails(result)
	{

		var str ='';
		
		if(result != null)
		{
			for(var i in result)
			{			
				str+='<div class="media">';
				str+='<a href="#" class="media-left">';
				str+='<img style="width: 64px; height: 64px;" src="images/cadre_images/'+result[i].imageURL+'" />';
				str+='</a>';
				str+='<div class="media-body">';
				str+='<h4 class="media-heading">'+result[i].cadreName+'</h4>';
				str+='<ul class="list-inline">';
				str+='<li>Age:'+result[i].age+'</i>';
				str+='<li>Gender: '+result[i].gender+'</i>';
				str+='<li>Mobile No: '+result[i].mobileNo+'</i>';
				str+='<li>Caste: '+result[i].casteName+'</i>';
				str+='<li>Voter ID: '+result[i].voterCardNo+'</i>';
				//str+='<li>Aadhar: '+result[i].imageURL+'</i>';
				str+='</ul>';
				str+='</div>';
				str+='</div>';
			}
			$('#cadreDetailsDiv').html(str);
		}
		
	}
	
	
	</script>
</body>
</html>