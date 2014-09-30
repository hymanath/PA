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

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">	
	<!-- Custom Styles-->
    <link href="css/style.css" rel="stylesheet">
	<!-- CSS animation -->
    <link href="css/animate.css" rel="stylesheet">	
	<!-- icheck Css-->
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet">
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
	
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
	
		<div class="span6 offset2 show-grid pad-10b" style="">
			<h5 class="text-align">SELECT CONSTITUENCY</h5>

			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:450px;" />
			
				<h5 class="text-align small m_top15">SEARCH BY</h5>
					<div class="span6">
					
						<div class="row form-inline text-center">
									<label class="radio"><input type="radio" value="voter"  name="searchTypeRadio"> VOTER</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio"><input type="radio" value="cadre"  name="searchTypeRadio"> CADRE</label>
								
						</div>
					</div>
					
					<div class="pad-10b">
					<h5 class="text-align1">CANDIDATE NAME</h5>
							<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="searchNameId" name="searchName" style="width:425px;">
						</div>
					<div class=" m_top10 pad-10b">
						<div class="row-fluid">
						
							<div class="span6">
							<h5 class="text-align1">VOTER ID</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Voter ID"  id="searchVoterCardId"  name="searchVoterCard">
							</div>
							
							<div class="span6">
							<h5 class="text-align1">H NO</h5>
								<input type="text" class="form-control border-radius-0" placeholder="House Number"  id="searchHNoId"   name="searchHNo">
							</div>
						</div>
					</div>
					<a href="javascript:{searchCandidatesDetailsBySearchCriteria();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0">Success  <span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>
		
	</div>
	
	<div class="container" id="tableElement" style="margin-top:25px;display:none;">
		<div class="col-xs-8 col-xs-offset-2 show-grid">
			<h3 class="text-align">SEARCH DETAILS</h3>
			<div class="table-responsive" id="searchDetailsDiv" ></div>
			<a href="search-constituency.html" class="btn btn-default active col-xs-1 m_top20 pull-right border-radius-0 ">Skip 	
			<span class="glyphicon glyphicon-chevron-right"></span></a>
			<a href="javascript:{getDetailsForUser()}" class="btn btn-success col-xs-1 m_top20 pull-right m-right17 border-radius-0">Ok 
			<span class="glyphicon glyphicon-chevron-right"></span></a>
	
		</div>
	</div>
		
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
     <script src="js2.3.2/bootstrap.min.js"></script>
		<script src="js/icheck/jquery.js"></script>
	 <script src="js/icheck/icheck.js"></script>
<!-- iCheck -->
	<script>
		$(document).ready(function(){
		  $('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		});
		
		
		function searchCandidatesDetailsBySearchCriteria()
	{
	
		var cosntiteucnyId = $('#userConstituencyId').val();
		var candidateName = $('#searchNameId').val();
		var voterCardNo = $('#searchVoterCardId').val();
		var houseNo = $('#searchHNoId').val();
		var searchType = $('input[name="searchTypeRadio"]:checked').val();

		$('#searchDetailsDiv').html('');
		$('#tableElement').hide();
		
		var jsObj = 
			   {
				  constituencyId:cosntiteucnyId,
				  searchType :searchType, 
				  candidateName:candidateName,
				  houseNo : houseNo,
				  voterCardNo : voterCardNo,
				  panchayatId : 0,
				  boothId : 0 ,
				  locationId : 0,
				  task:"searchCandidatesDtailsBySearchCriteria"             
			   }	
			   
			   $.ajax({
					type : "POST",
					url : "searchVoterAndCadreInfoBySearchCriteriaAction.action",
					data : {task:JSON.stringify(jsObj)} ,
				}).done(function(result){
					
					if(result != null && result.length >0)
					{
						buildSearchDetails(result);
					}
					else
					{
						$('#searchDetailsDiv').html('No Data Available...');
					}
				});

	}
	
	function buildSearchDetails(result)
	{
	
	var str = '';
			str +='<table class="table table-bordered" id="seachDetalsTab">';
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
				str +='<td>'+result[i].name+'</td>';
				str +='<td>'+result[i].relativeName+'</td>';
				str +='<td>'+result[i].age+'</td>';
				str +='<td>'+result[i].gender+'</td>';
				str +='<td>'+result[i].relationType+'</td>';
				str +='<td>'+result[i].houseNo+'<label class="pull-right">';
				str +='<input type="radio" value="'+result[i].id+'" name="optionsRadios"></label></td>';
				str +='</tr>';
			}
			
			str +='</tbody>';
			str +='</table>';
		$('#searchDetailsDiv').html(str);
		//$('#seachDetalsTab').dateTable();
		$('#tableElement').show();
	}
	
	function getDetailsForUser()
	{
		var candidateId = $('input[name="optionsRadios"]:checked').val();
		var searchType = $('input[name="searchTypeRadio"]:checked').val();

		window.open('tdpCadreRegistrationAction.action?candidateId='+candidateId+'&searchType='+searchType+'');
		
	}
	
		</script>
		 <script>$('#yourElement').addClass('animated fadeInDown');</script>
	<!----->
	
  </body>
</html>