<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> TDP Cadre Search </title>

    <!-- Bootstrap -->
    <link href="css/cadreRegistrationCSS/bootstrap.min.css" rel="stylesheet">
	
	<!--  -->
	<link href="css/cadreRegistrationCSS/animate.css" rel="stylesheet">
    <link href="css/cadreRegistrationCSS/style.css" rel="stylesheet">
	<!-- icheck Css-->
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet">
	<!-- icheck  JS-->
	
</head>
  <body>
	<div class="container" id="yourElement">
	
		<div class="col-md-4 col-md-offset-4 show-grid" style="position: relative; top: 80px;">
			<h5 class="text-align">SELECT CONSTITUENCY</h5>
			
			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" name="constituency" />
			
				<h5 class="text-align small m_top15">SEARCH BY</h5>
					<div class="col-xs-12">					
						<div class="row form-inline text-center">
									<label class="radio"><input type="radio" value="voter"  name="optionsRadios"> VOTER</label>
										&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="radio"><input type="radio" value="cadre"  name="optionsRadios"> CADRE</label>
								
						</div>
					</div>
					
					<div class="col-xs-12">
					<h5 class="text-align1">CANDIDATE NAME</h5>
						<input type="text" class="form-control border-radius-0" placeholder="Enter Name" id="searchNameId" name="searchName">
						</div>
					<div class="col-xs-12 m_top10">
						<div class="row">
						
							<div class="col-xs-6">
							<h5 class="text-align1">VOTER ID</h5>
								<input type="text" class="form-control border-radius-0" placeholder="Enter Voter ID"  id="searchVoterCardId"  name="searchVoterCard">
							</div>
							
							<div class="col-xs-6">
							<h5 class="text-align1">H NO</h5>
								<input type="text" class="form-control border-radius-0" placeholder="House Number"  id="searchHNoId"   name="searchHNo">
							</div>
						</div>
					</div>
					<a href="javascript:{searchCandidatesDetailsBySearchCriteria();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0">Success  <span class="glyphicon glyphicon-chevron-right"></span></a>
		</div>
		
	
	</div>
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap3/bootstrap.min.js"></script>
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
		var searchType = $('input[name="optionsRadios"]:checked').val();
		
		window.open('tdpCadreSearchDetailsAction.action?constiteucnyId='+cosntiteucnyId+'&candidateName='+candidateName+'&voterCardNo='+voterCardNo+'&houseNo='+houseNo+'&searchType='+searchType+'');
		
	}
	
		</script>
		 <script>$('#yourElement').addClass('animated fadeInDown');</script>
	<!----->
	
  </body>
</html>