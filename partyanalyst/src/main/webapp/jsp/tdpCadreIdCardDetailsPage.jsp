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
	.textWidth{
	    width: 156px;
	}
	.marginWidth{
	  margin-left: 6px;
	  }
	  .detailsCls{
	    cursor:pointer;
	  }
	</style>
   
	
</head>
  <body class="bgc">
  
  	<!-- Header Row -->
		<div class="row-fluid">
			<div class="span12 header-bg text-center">
				<div class="row-fluid">
				  <div class="span4 offset4 ">
						<img src="images/cadre_images/2014-cadre-Registration-Logo.png">
				  </div>
				  <div class="span4">
					 <a href="newlogoutAction.action" style="font-weight: bold;" class="btn btn-mini pull-left m_top20">Logout</a>
				  </div>
				</div>
			</div>
		</div><!-- Header Row End-->
		
	<div class="container" id="yourElement">
	<div id="myDiv"></div>
	<div id="tableDivForCadre" class="table-responsive"></div>
	
		<div class="span6 offset3 show-grid pad-10b" style="">
		<div id="errorDiv" style="color:#ff0020;"></div>
			<h5 class="text-align">SELECT CONSTITUENCY</h5>

			<s:select theme="simple" cssClass="selectBoxWidth span12 input-block-level" id="userConstituencyId" list="selectOptionVOList" listKey="id" listValue="name" headerKey="0" headerValue=" Select Constituency" style="width:460px;" onChange="getConstituencyWiseDetails();"/>
			<select class="textWidth" id="panchayatList" onchange="getLocationWiseDetails();" multiple><option value="0"> Select Location </option></select>		
			<select class="span4 marginWidth" id="boothsList" multiple> <option value="0"> Select Booth </option> </select> 	
			<!-- <select style="width:150px;" id="vilagecovrdList"> <option value="0"> Select Covered Village </option> </select>  -->
			<img src='images/icons/search.gif' id="loadingImg" style="display:none;"/>
				
				
					
					
					<a href="javascript:{searchCandidatesDetailsBySearchCriteria();}" class="btn btn-success m_top20 col-xs-offset-4 border-radius-0 offset2"> Search  <span class="glyphicon glyphicon-chevron-right"></span></a>
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
		
	function getConstituencyWiseDetails(){
		var cosntiteucnyId = $('#userConstituencyId').val();
		
		$('#errorDiv').html('');
		$('#searchNameId').val('');
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
	
	function getLocationWiseDetails(){
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
					url : "getMultipleLocationWiseDetailsAction.action",
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
	
	
	</script>
  </body>
</html>