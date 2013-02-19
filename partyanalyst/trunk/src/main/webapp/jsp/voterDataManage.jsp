<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst</title>
  <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
	<style type="text/css">
	#voterManagementMainDiv{
		float: none;margin: 35px auto 20px;padding-top: 10px;
		text-align: center;
	}
	fieldset {
		border: 1px solid #D3D3D3;
	}
	.headingDiv{
		background: none repeat scroll 0 0 #06ABEA;border-radius: 4px;color: #FFFFFF;
		font-family: arial;font-size: 17px;font-weight: bold;padding: 3px;text-align: center;
		width: 440px; margin-bottom: 15px;
	}
	.voterManagementInnerDiv p{font-size: 13px;}
	#voterDataInsertId{font-weight:bold;}
	#voterChangesButtonId{font-weight:bold;margin-top:10px;}
	.errorMsgDiv{color: red;
    font-size: 13px;
    padding-bottom: 12px; padding-top: 12px;}
	table{font-size:13px;}
	#minResults,#maxResults{width:186px;}
	</style>
</head>
<body>

<div id="voterManagementMainDiv" class="span8">
 <div class="headingDiv">Copy voter data from temporary table to voter table</div>

  <fieldset>
	<div class="errorMsgDiv"></div>
	<div class="voterManagementInnerDiv">
		
	<center>
		<table cellpadding="4">
			<tr>
				<td>Constituency </td>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your Constituency" name="constituenciesList" id="constituencies_List" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
			<tr>
				<td>Publication Date</td>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="publicationDate_List" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
			<tr>
				<td>Starting From</td>
				<td>:</td>
				<td><input type="text" id="minResults"/></td>
			</tr>
			<tr>
				<td>Max Result</td>
				<td>:</td>
				<td><input type="text" id="maxResults"/></td>
			</tr>
		</table>
		<p><input type="button" value="submit" id="voterDataInsertId" class="btn btn-info"/></p>
	</center>		
	</div>
	</fieldset>
</div>

<div id="voterManagementMainDiv" class="span8">
 <div class="headingDiv" style="width:615px;">Copy Voter Modification Data from temporary table to main table</div>

  <fieldset>
	<div id="errorMsgDivId" class="errorMsgDiv"></div>
	<div class="voterManagementInnerDiv">
		
	<center>
		<table cellpadding="4">
			<tr>
				<th>Constituency </th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your Constituency" name="constituenciesListForVoterChanges" id="constituencySelectId" list="constituenciesListForVoterChanges" listKey="id" listValue="name" headerKey="0" headerValue="Select Constituency"></s:select></td>
			</tr>
			<tr>
				<th>Publication Date</th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="publicationDateId" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
		</table>
		<p><input type="button" value="Submit" id="voterChangesButtonId" class="btn btn-info" onClick="insertVoterModifiedData()"/><div id="ajaxImgDivId" style="display:none;"><img src="images/icons/search.gif"/></div></p>
	</center>		
	</div>
	</fieldset>
</div>


<script type="text/javascript">

function callAjax(jsObj, url){


	var callback = {			
				   success : function( o ) {
						try
						{
							
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "insertVoterData")
							{
								showVoterinsertDataStatus(myResults);
							}
							else if(jsObj.task == "insertVoterModificationData")
							{
								showModifiedVotersInsertDataStatus(myResults);
							}
							
						}
						catch(e)
						{   
							//alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
	
	$(document).ready(function() {
		$("#voterDataInsertId").click(function(){
			var constituencyId = $("#constituencies_List").val();
			var publicationDateId = $("#publicationDate_List").val();
			var minResults = $.trim($("#minResults").val());
			var maxResults = $.trim($("#maxResults").val());
			var str = '';
			var errorEle = $(".errorMsgDiv");
			errorEle.html('');
			if(constituencyId == 0 || constituencyId == '')
			{
				errorEle.html('Please Select Constituency');
				return;
			}
			else if(publicationDateId == 0 || publicationDateId== '')
			{
				errorEle.html('Please Select Publication Date');
				return;
			}
			else if(minResults == '' && maxResults == '')
			{
				errorEle.html('Starting From and Max Result is mandatory fields.');
				return;
			}
			else if(minResults == '')
			{
				errorEle.html('Starting From is mandatory field.');
				return;
			}
			else if(maxResults == '')
			{
				errorEle.html('Max Result is mandatory field.');
				return;
			}
			else if(isNaN(minResults) && isNaN(maxResults))
			{
				errorEle.html('Starting From and Max Result Must be integer.');
				return;
			}
			else if(isNaN(minResults))
			{
				errorEle.html('Starting From Must be integer.');
				return;
			}
			else if(isNaN(maxResults))
			{
				errorEle.html('Max Result Must be integer.');
				return;
			}
			else if(minResults < 0)
			{
				errorEle.html('Starting From Must be positive number.');
				return;
			}
			else if(maxResults < 0)
			{
				errorEle.html('Max Result Must be positive number.');
				return;
			}
			
			$(".errorMsgDiv").html('');
			var jsObj=
			{				
				constituencyId: constituencyId,
				publicationDateId: publicationDateId,
				startIndex : minResults,
				maxResults : maxResults,
				task: "insertVoterData"
				
			}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "insertVoterDataAction.action?"+rparam;						
	callAjax(jsObj,url);
			
		});

	});

	function insertVoterModifiedData()
	{
		var constituencyEle = document.getElementById("constituencySelectId");
		var publicationDateEle = document.getElementById("publicationDateId");

		var constituencyId = constituencyEle.options[constituencyEle.selectedIndex].value;
		var publicationDateId = publicationDateEle.options[publicationDateEle.selectedIndex].value;
		var errorDivEle = document.getElementById("errorMsgDivId");
		
		var errorStr = '';
		var flag = false;
		if(constituencyId == 0)
		{
			errorStr += 'Please Select Constituency<BR>';
			flag = true;
		}
		if(publicationDateId == 0)
		{
			errorStr += 'Please Select Publication Date<BR>';
			flag = true;
		}
		if(flag)
		{
			errorDivEle.innerHTML = errorStr;
			return;
		}
		
		errorDivEle.innerHTML = '';
		document.getElementById("ajaxImgDivId").style.display = 'block';
		var jsObj=
		{				
			constituencyId		: constituencyId,
			publicationDateId	: publicationDateId,
			task				: "insertVoterModificationData"
			
		}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "insertVoterDataAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
	function showModifiedVotersInsertDataStatus(results)
	{
		$(".errorMsgDiv").html('');
		document.getElementById("ajaxImgDivId").style.display = 'none';
		if(results.resultCode == 0)
		{
			document.getElementById('constituencySelectId').selectedIndex = 0;
			document.getElementById('publicationDateId').selectedIndex = 0;
			$(".errorMsgDiv").html('Voters Modification Data inserted successfully.').css("color","green");
		}
		else
			$(".errorMsgDiv").html('Voters Modification Data is not inserted.');
	}

	function showVoterinsertDataStatus(results)
	{
		$(".errorMsgDiv").html('');
		if(results.resultCode == 0)
		{
			document.getElementById('constituencies_List').selectedIndex = 0;
			document.getElementById('publicationDate_List').selectedIndex = 0;
			
			$('#minResults').val('');
			$('#maxResults').val('');
			$(".errorMsgDiv").html('Voter Data inserted successfully.').css("color","green");
			return;
		}
		else
		{
			$(".errorMsgDiv").html('Data is not inserted.');
			return;
		}
	}

</script>
</body>
</html>