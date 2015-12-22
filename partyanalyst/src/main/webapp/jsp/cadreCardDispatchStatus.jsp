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
		<!--<div class="row-fluid">
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
					<h3 class="text-center text-uppercase">Cadre Membership Card Dispatching Details 
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
						 <td><b>TR NO. :</b></td>
						 <td>  
						   <input type="text" id="trNumberId" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls " placeholder="Enter TR No."  ></input>
						 </td>
					 </tr>
					 <tr>
						 <td><b>Mobile No. :</b></td>
						 <td>  
						   <input type="text" id="mobileNoId" maxLength="10" class="levelDtCls form-control border-radius-0 border-right-0 datePickerCls " placeholder="Enter Mobile No." onKeyup="validateMobileNo();" ></input>
						 </td>
					 </tr>
					 
					 
					 <tr>
					 <td></td>
					 <td> <input style="margin-top:10px;" type="button" id="locationSubmitBtn" class="btn btn-success" onclick="getTdpCadreDetailsBySearchCriteria();" value="Search Details"/>
					  <input style="display:none;margin-top: 10px;" type="button" id="exportExclId2" class="btn btn-success" onclick="generateExcel('ttlSrchTable');" value="Export Excel"/>
					 <img id="ajaxImgStyleNew2" style="display:none;margin-left:10px; margin-top:10px;height: 60px;" src="images/Loading-data.gif"/></td>
					 </tr>
				  </table>
				<div id="searchCadreInfoDiv" style="overflow:scroll;display:none;"></div>
				<div id="searchCadreTotalDiv" style="display:none;"></div>
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
	$(document).ready(function(){
		//getTdpCadreDetailsBySearchCriteria();
	});	
	
	function validateMobileNo()
	{
		var str = $('#mobileNoId').val();
		$('#ErrorLDiv').html('');
		if(str.trim().length >0)
		{
			if (isNaN(str)) 
			{
				$('#ErrorLDiv').html('Invalid Mobile No.');		
			}
		}		
	}
 
	function getTdpCadreDetailsBySearchCriteria()
		{

		$('#exportExclId2').hide();
		$('#searchCadreInfoDiv').hide();
		$('#searchCadreInfoDiv').html('');
		var mobileNo = $('#mobileNoId').val();
		var trNo = $('#trNumberId').val();
		var isError =true;		
		
		if((mobileNo == null || mobileNo.trim().length == 0) && (trNo == null || trNo.trim().length == 0) )
		{
				$('#ErrorLDiv').html('TR No. / Mobile No Required.');	
				isError = false;
		}
		
		if(isError)
		{
			$('#ajaxImgStyleNew2').show();
				
			var jsObj = {
				searchType :" ", // not required
				refNo :trNo,
				mobileNo :mobileNo,
				tesk:"getTdpCadreDetailsBySearchCriteria"            
			}
	  
			$.ajax({
				type : "POST",
				url : "getTdpCadreDetailsBySearchCriteriaAction.action",
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
		var str ='';
		str +='<table class="table table-boardered" id="searchCadreTab">';
		str +='<thead>';
		str +='<tr>';
		str +='<th> Name </th>';
		str +='<th> Relative Name </th>';
		str +='<th> Voter Card No </th>';
		str +='<th> Gender </th>';
		str +='<th> Mobile No. </th>';
		str +='<th> Constituency </th>';
		str +='<th> Membership No </th>';
		str +='<th> TR.No </th>';
		str +='<th> Image </th>';
		str +='<th> Dispatch Status </th>';
		str +='</tr>';
		str +='</thead>';
		str +='<tbody>';
		if(result.cadreRegistrationVOList != null && result.cadreRegistrationVOList.length >0)
		{
			for(var i in result.cadreRegistrationVOList)
			{
				str +='<tr>';
			
				str +='<td>'+result.cadreRegistrationVOList[i].voterName+'</td>';
				str +='<td>'+result.cadreRegistrationVOList[i].relativeName+'</td>';
				str +='<td>'+result.cadreRegistrationVOList[i].cadreType+'</td>';
				str +='<td>'+result.cadreRegistrationVOList[i].gender+'</td>';
				str +='<td>'+result.cadreRegistrationVOList[i].mobileNumber+'</td>';
				str +='<td>'+result.cadreRegistrationVOList[i].constituencyId+'</td>';
				str +='<td>'+result.cadreRegistrationVOList[i].previousEnrollmentNumber+'</td>';
				str +='<td style="text-align:center;">'+result.cadreRegistrationVOList[i].refNo+'</td>';
				str +='<td> <img id="'+result.cadreRegistrationVOList[i].enrollmentNumber+'" src="images/cadre_images/'+result.cadreRegistrationVOList[i].uploadImageFileName+'" width="50px"/></td>';
				str +='<td>'+result.cadreRegistrationVOList[i].casteName+'</td>';  // dispatch Status
				
				str +='</tr>';
			}			
		}
		str +='</tbody>';		
		str +='</table>';
		
		var strs ='';
		strs +='<table class="table table-boardered" id="ttlSrchTable">';
		strs +='<thead>';
		strs +='<tr>';
		strs +='<th> Name </th>';
		strs +='<th> Relative Name </th>';
		strs +='<th> Voter Card No </th>';
		strs +='<th> Gender </th>';
		strs +='<th> Mobile No. </th>';
		strs +='<th> Constituency </th>';
		strs +='<th> Membership No </th>';
		strs +='<th> TR.No </th>';
		strs +='<th> Image </th>';
		strs +='<th> Dispatch Status </th>';
		strs +='</tr>';
		strs +='</thead>';
		strs +='<tbody>';
		if(result.cadreRegistrationVOList != null && result.cadreRegistrationVOList.length >0)
		{
			for(var i in result.cadreRegistrationVOList)
			{
				strs +='<tr>';
			
				strs +='<td>'+result.cadreRegistrationVOList[i].voterName+'</td>';
				strs +='<td style="text-align:center;">'+result.cadreRegistrationVOList[i].relativeName+'</td>';
				strs +='<td style="text-align:center;">'+result.cadreRegistrationVOList[i].cadreType+'</td>';
				strs +='<td>'+result.cadreRegistrationVOList[i].gender+'</td>';
				strs +='<td>'+result.cadreRegistrationVOList[i].mobileNumber+'</td>';
				strs +='<td>'+result.cadreRegistrationVOList[i].constituencyId+'</td>';
				strs +='<td>'+result.cadreRegistrationVOList[i].previousEnrollmentNumber+'</td>';
				strs +='<td style="text-align:center;">'+result.cadreRegistrationVOList[i].refNo+'</td>';
				strs +='<td> <img id="'+result.cadreRegistrationVOList[i].enrollmentNumber+'" src="images/cadre_images/'+result.cadreRegistrationVOList[i].uploadImageFileName+'" width="50px"/></td>';
				strs +='<td>'+result.cadreRegistrationVOList[i].casteName+'</td>';  // dispatch Status
				
				strs +='</tr>';
			}			
		}
		strs +='</tbody>';		
		strs +='</table>';
		
		$("#searchCadreTotalDiv").html(strs);
		
		$('#searchCadreInfoDiv').html(str);
		$('#searchCadreTab').dataTable({
			"iDisplayLength": 50,
			"aLengthMenu": [[50, 100, 200, -1], [50, 100, 200, "All"]]
		});
		$('#exportExclId2').show();
		
	}
	function generateExcel(reqId){
     tableToExcel(reqId, 'Cadre Membership Card Dispatching Details');
    }
	</script>

  </body>
</html>