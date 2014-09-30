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
    <title>TDP Cadre Search Details </title>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap3/bootstrap.min.js"></script>
	<script src="js/icheck/jquery.js"></script>
	 <script src="js/icheck/icheck.js"></script>
	<!-- iCheck -->
   <!-- Bootstrap -->
    <link href="css/cadreRegistrationCSS/bootstrap.min.css" rel="stylesheet">
	
	<!--  -->
	<link href="css/cadreRegistrationCSS/animate.css" rel="stylesheet">
    <link href="css/cadreRegistrationCSS/style.css" rel="stylesheet">
	<!-- icheck Css-->
	<link href="styles/icheck_skins/all.css?v=1.0.2" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
	<!-- icheck  JS-->
	
</head>
  <body>
	<div class="container" id="yourElement">
		<div class="col-xs-8 col-xs-offset-2 show-grid" style="position: relative; top: 80px;">
			<h3 class="text-align">SEARCH DETAILS</h3>
			<div class="table-responsive" id="searchDetailsDiv" style="overflow:scroll;height:500px;"></div>
			<a href="search-constituency.html" class="btn btn-default active col-xs-1 m_top20 pull-right border-radius-0 ">Skip 	
			<span class="glyphicon glyphicon-chevron-right"></span></a>
			<a href="javascript:{getDetailsForUser()}" class="btn btn-success col-xs-1 m_top20 pull-right m-right17 border-radius-0">Ok 
			<span class="glyphicon glyphicon-chevron-right"></span></a>
	
		</div>
	
</div>
	<script>
		$(document).ready(function(){
		  $('input').iCheck({
			checkboxClass: 'icheckbox_square-blue',
			radioClass: 'iradio_square-blue',
			increaseArea: '20%' // optional
		  });
		  searchCandidatesDetailsBySearchCriteria();

		});
		
	function searchCandidatesDetailsBySearchCriteria()
	{
	
	var constiteucnyId = '${constiteucnyId}';
	var candidateName = '${candidateName}';
	var voterCardNo = '${voterCardNo}';
	var houseNo = '${houseNo}';
	var searchType = '${searchType}';
	$('#searchDetailsDiv').html('');
	
	
	var jsObj = 
	       {
		      constituencyId:constiteucnyId,
			  searchType :searchType, 
			  candidateName:candidateName,
			  houseNo : houseNo,
			  voterCardNo : voterCardNo,
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
		$('#seachDetalsTab').dateTable();
	
	}
	
	function getDetailsForUser()
	{
		var candidateId = $('input[name="optionsRadios"]:checked').val();
		var  searchType = '${searchType}';

		window.open('tdpCadreRegistrationAction.action?candidateId='+candidateId+'&searchType='+searchType+'');
		
	}
	</script>
	<script>$('#yourElement').addClass('animated fadeInDown');</script>
	<!----->
	
  </body>
</html>