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
    <title> Cadre Transactions Details </title>

    <link href="css/bootstrap.min.css" rel="stylesheet"/>	
    <link href="css/style.css" rel="stylesheet"/>
    <link href="css/animate.css" rel="stylesheet"/>	
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	
	<script type="text/javascript" src="js/jquery.dataTables.js"></script>
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"/> 
	<script type="text/javascript" src="js/photobooth/photobooth_min.js"></script>
		<script type="text/javascript" src="js/photobooth/website/js/cadre.js"></script>
		<script type="text/javascript" src="js/exportexcel.js"></script>
		<link type="text/css" rel="stylesheet" media="screen" href="js/photobooth/website/css/page.css" />
	<style>
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

	</style>
   
	
</head>
  <body  class="bgc">
	
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
		

	<div class="container ">	
	
		<div id="locationWiseCadreInfoDiv">
		    <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-0 " style="padding:0px;">
					<h3 class="text-center text-uppercase"> Cadre Membership Card Printing Details
					<c:if test="${sessionScope.USER.isAdmin == 'true'}">
					<a class="btn btn-success" style="float: right;margin-right:25px;" href="cadreDashBoardAction.action"> home </a>					
					</c:if>
					</h3>
				</div>
			</div>
			<div class="row-fluid ">
			   <div style="min-height: 300px;background:#ffffff;" class="span12 show-grid well well-small border-radius-0 mb-10 form-inline">
				  <div id="ErrorLDiv" align="center" style="color:#FF0000;"></div>
				  <table  style="margin-left: 270px;">
				    
				     <tr>
						 <td><b>Membership ID :</b></td>
						 <td>  
						   <input type="text" id="membershipId" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls " placeholder="Enter Membership ID"  ></input>
						 </td>
					 </tr>					
					 <tr>
					 <td></td>
					 <td> <input style="margin-top:10px;" type="button" id="locationSubmitBtn" class="btn btn-success" onclick="getTdpCadreDetailsBySearchCriteria();" value="Search Card"/>
					 
					 <img id="ajaxImgStyleNew2" style="display:none;margin-left:10px; margin-top:10px;height: 60px;" src="images/Loading-data.gif"/></td>
					 </tr>
				  </table>
				<div id="searchCadreInfoDiv" align="center" class="span8"></div>
		   </div>
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
	
	function getTdpCadreDetailsBySearchCriteria()
		{
		$('#searchCadreInfoDiv').hide();
		$('#searchCadreInfoDiv').html('');
		$('#ErrorLDiv').html('');
		var membershipId = $('#membershipId').val();
		var isError =true;		
		
		if((membershipId == null || membershipId.trim().length == 0))
		{
				$('#ErrorLDiv').html('MembershipId is Required.');	
				isError = false;
		}
		
		if(isError)
		{
			$('#ajaxImgStyleNew2').show();
				
			var jsObj = {
				membershipId:membershipId,
				tesk:"getTdpCadreDetailsBySearchCriteria"            
			}
	  
			$.ajax({
				type : "POST",
				url : "getCadreDetailsInTeluguByMembershipIdAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
			$('#ajaxImgStyleNew2').hide();
			
				if(result != null)
				{
					buildSearchCadreInfo(result);
				}
				else
				{
					$('#searchCadreInfoDiv').html(' <span style="font-weight:bold;"> No Data Available... </span>');
				}
				$('#searchCadreInfoDiv').show();
				
			});
		}		
		}
		
	function buildSearchCadreInfo(result)
	{
		$('#searchCadreInfoDiv').html('');
		var str='';	
		if(result != null && result.length>0)
		{
			for(var i in result)
			{
				str+=' <div class="span2 offset3"> <img class="offset1" src="http://'+result[i].imageBase64String+'" style="float: inherit;position: absolute;margin-top: 70px;width: 75px;height: 92px;margin-left: 10px;"></div>';
				str+=' <div class="span5 " style="float: inherit;position: absolute;margin-top: 67px;font-size: 12px;font-weight: bold;color: yellow;margin-left: 55px;"> <span> '+result[i].previousEnrollmentNumber+' </span> <br><span style="margin-top:5px;"> 2014-2016 </span></div>';
				str+=' <div class="span5 offset2" style="float: inherit;position: absolute;margin-top: 105px;margin-left: 80px;">';

				str+='<div class="pull-left offset4" style="margin-left:210px;" > '+result[i].voterName+' </div><br> ';
				if(result[i].panchayatId != null)
				{
					str+='<span style="margin-left:-285px;"> '+result[i].panchayatId+'</span> ';
				}
				if(result[i].mandalId != null)
				{
					str+='<span> '+result[i].mandalId+'</span><div class="pull-left " style="margin-left:210px"> ';
				}				
				if(result[i].muncipalityId != null)
				{
					str+='<span> '+result[i].muncipalityId+'</span> <div class="pull-left " style="margin-left:210px"> ';
				}
				if(result[i].constituencyId != null)
				{
					str+='<br><span> '+result[i].constituencyId+'</span> ';
				}
				if(result[i].address != null)
				{
					str+=' <span>'+result[i].address+'</span> </div> ';
				}
				str+='</div>';
				str+=' <div class="offset3"> <img src="images/MembershipCard.jpg" style="width: 450px"/></div>';
			}
		}
	$('#searchCadreInfoDiv').html(str);
	}
	</script>

  </body>
</html>