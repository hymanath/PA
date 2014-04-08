<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>2009 ASSEMBLY VS 2013 PANCHAYAT RESULTS REPORT</title>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
<script type="text/javascript"> 
  google.load("visualization", "1", {packages:["corechart"]});
</script>
<script type="text/javascript" src="js/commonVoterDetails.js"></script>



	<script type="text/javascript" src="js/blockui.js"></script>
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet" />
	<link type="text/css" href="styles/bootstrapInHome/bootstrap-responsive.min.css" rel="stylesheet" />
	<script type="text/javascript" src="js/connectPeople/connectPeople.js"></script> 
	<script type="text/javascript" src="js/homePage/newHomePage.js"> </script>
	<script type="text/javascript" src="js/homePage/newHomePage_inner.js"> </script>
	<link href="styles/tdphome_inner_styles.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="styles/newhome_styles.css">
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<script type="text/javascript" src="js/md5.js"></script>


<style type="text/css">
table tr.separator { height: 40px; }

  #ajaxImage{
    display:none; 
  }
  
#buildTableDiv table,#buildTableDiv1 table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#buildTableDiv table tr:nth-child(even),#buildTableDiv1 table tr:nth-child(even){background:#EdF5FF;border:1px solid #d3d3d3;}
#buildTableDiv table td,#buildTableDiv1 table td{border:1px solid #d3d3d3;padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#buildTableDiv table th,#buildTableDiv1 table th{
background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 4px;
    padding-left: 4px;
    padding-right: 4px;
    padding-top: 4px;
    text-align: left;
	color:#333333;
	border:1px solid #d3d3d3;
	}
#buildTableDiv,#buildTableDiv1{
	font-family : arial;
	font-size: 13px;
    margin-top:0px;
	padding: 10px 10px 10px 0px;
	 width: 900px;
}

#headingImgStyle{
	 background: none repeat scroll 0 0 #05A8E9;
    border-radius: 5px 5px 5px 5px;
    color: #FFFDFF;
    padding: 6px;
    text-align: center;
    width: 940px;
}
#headDivStyle{
	color: #ffffff;
    font-size: 20px;
    font-weight: normal;
    padding:20px;	 
}
</style>

</head>
<body>
<div id="mainDiv" align="center">
   	<div id= "headDivStyle">
	<table border="0" cellpadding="0" cellspacing="0">          
		<tr><!--<td><img src="images/icons/cadreReport/bg_left.png"/></td>-->
			<td><div id="headingImgStyle"><span>&nbsp;Voter Modification Report </span></div></td>
	       <!--<td><img src="images/icons/cadreReport/bg_right.png"/></td>-->
	   </tr>
		</table>
	</div>
  
		<div class="form-horizontal boothResults " name='boothSelection' style="display:block;border: 3px solid #d3d3d3;margin-top: -20px;margin-bottom: 20px;">
		<div class="control-group" id="errorDiv" style="color:red;"></div>

				  <table>
					<tr class="separator">
					<td><label class="span2" for="districtList">Select District</label></td>
					<td><s:select cssClass="selectstyle" theme="simple" id="districtList" name="crossVotingYear" list="districtsList" listKey="id" listValue="name" onChange=""/> </td>
					</tr>
					<tr class="separator">
					<td><label class="span2" for="prevPublicationId">Previous Publication</label></td>
					<td><select id="prevPublicationId"><option value="9">2014-01-01</option></select></td>
					</tr>
					<tr>
					<td><label class="span2" for="presentPublicationId">Present Publication</label></td>
					<td><select id="presentPublicationId"><option value="10">2014-02-01</option></select></td>
					</tr>
					</table>
					<button class="btn btn-small btnStyle" data-dismiss="modal" aria-hidden="true" onclick="getVoterModifivationReport()" style="margin-top:8px;">SUBMIT</button>
				</div>
				

</div>

<script>
function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task=="forConstituencies"){

									if(jsObj.type == "default")
										buildConstituenciesDefault(myResults);
									else
 									  buildConstituencies(myResults);
								}
								if(jsObj.task == "createPdfs")
								{
									
									if(myResults.name == 'success')
									{
										$.unblockUI();
										alert("Pdfs Created Successfully");
									}
									else
									{
										$.unblockUI();
										alert("Error occured while creating the pdf");
									}
								}
								}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
</script>
</body>
</html>
