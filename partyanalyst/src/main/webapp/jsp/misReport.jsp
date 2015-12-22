<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MIS REPORT</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
 <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
</head>
<body>
    <div class="container m_top10">
      <div id="locationWiseCadreInfoDiv">
		   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">MIS REPORT</h3>
				</div>
			</div>
	  </div>
	   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;min-height:200px;">
				<div>
				     <div id="errMsgDiv"></div>
				     <table  style="margin-left: 270px;margin-top: 15px;">
				       <tr>
					      <td><b>Enter Batch Code :</b></td>
					      <td><input type="text" id="jobCodeId"/></td>
					  </tr>
					  <tr>
					      <td></td>
					      <td><input type="button" value="Get Mis Report" onclick="getMISReport();" class="btn btn-success" id="getDtlsSubmitBtn"/><img id="ajaxImgStyle" style="display:none;margin-left:10px;" src="images/icons/search.gif"/></td>
					  </tr>
					</table>
					<div id="resultTableDiv" style="margin-top:15px;"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function getMISReport(){
			$("#errMsgDiv").html("");
			$("#resultTableDiv").html("");
			var bathNo = $.trim($("#jobCodeId").val());
			if(bathNo.length == 0){
				$("#errMsgDiv").html("<div style='color:red;font-weight:bold;margin-bottom:-10px;margin-left:435px;margin-top:10px;'>Please Enter Batch Code</div>");
				return;
			}
			 $("#getDtlsSubmitBtn").attr("disabled","disabled");
			 $("#ajaxImgStyle").show();
			var jObj = {
				batchCode : bathNo
			}
			$.ajax({
			  type:'GET',
			  url: 'getMISReport.action',
			  data : {task:JSON.stringify(jObj)} ,
	        }).done(function(result){
			    $("#getDtlsSubmitBtn").removeAttr("disabled");
				$("#ajaxImgStyle").hide();
	        	if(result.name == "refresh" || (result.name == undefined && result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1)){
		    		   location.reload(); 
		    	  }
	        	if(result.name == "noData"){
				   $("#resultTableDiv").html("<div style='font-weight:bold;margin-left:415px;'>No Data Available</div>");
				}else if(result.name == "error"){
				   $("#resultTableDiv").html("<div style='font-weight:bold;margin-left:415px;'>Error Occured! Try Again Later.</div>");
				}else if(result.name == "success"){
				   $("#resultTableDiv").html("<div style='font-weight:bold;margin-left:346px;'><b><a href='"+result.mobileNo+"'>Click Here To View Report</a></b></div>");
				}
			});
		}
	</script>
</body>
</html>