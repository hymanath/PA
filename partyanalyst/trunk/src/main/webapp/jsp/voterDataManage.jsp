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
	#voterChangesButtonId,#mapVoterButtonId{font-weight:bold;margin-top:10px;}
	.errorMsgDiv,.errorMsgDiv1{color: red;
    font-size: 13px;
    padding-bottom: 12px; padding-top: 12px;}
	table{font-size:13px;}
	table th{font-weight:normal;}
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

			<tr>
				<td></td>
				<td></td>
				<!--<td><input type="checkbox" id="sNOUpdateId">
				Update Serial No</input></td>-->
			</tr>

		</table>
		<p><input type="button" value="submit" id="voterDataInsertId" class="btn btn-info"/></p>
	</center>		
	</div>
	</fieldset>
</div>
<div id="voterManagementMainDiv" class="span8">
 <div class="headingDiv" style="width:488px;">Map Voter Data From One publication to another Publication</div>
 
<fieldset>
<div class="errorMsgDiv1"></div>
<div class="voterManagementInnerDiv">
<center>
<table cellpadding="4">
			<tr>
				<th>From Publication </th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="frompublicationDateId" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
			
			<tr>
				<th>To Publication </th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="topublicationDateId" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getConstituenciesToMapPublicationData();"></s:select></td>
			</tr>

			<tr>
				<th>Constituency </th>
				<td>:</td>
				<td>
				<!-- <s:select cssClass="selectBoxWidth" theme="simple" label="Select Your Constituency" name="constituenciesList" id="mapVoterConstituencyId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select> -->

				<select id="mapVoterConstituencyId" class="selectBoxWidth" name="constituenciesList">
				<!--<option value="0">Select</option>
				<option value="223">KANDUKUR</option>
				<option value="231">GUDUR</option>
				<option value="232">KAVALI</option>
				<option value="233">KOVUR</option>
				<option value="236">SARVEPALLI</option>
				<option value="237">SULLURPET</option>
				<option value="238">UDAYAGIRI</option>
				<option value="239">VENKATAGIRI</option>
				<option value="241">ATMAKUR</option>
				<option value="280">CHANDRAGIRI</option>
				<option value="291">TIRUPATI</option>
				<option value="299">RAPTADU</option>
				<option value="307">KAKINADA RURAL</option>
				<option value="308">KAKINADA CITY</option>
				<option value="340">NELLORE CITY</option>
				<option value="341">NELLORE RURAL</option>
				<option value="347">UPPAL</option>
				<option value="354">VISAKHAPATNAM EAST</option>
				<option value="355">VISAKHAPATNAM SOUTH</option>
				<option value="356">VISAKHAPATNAM NORTH</option>
				<option value="357">VISAKHAPATNAM WEST</option>
				<option value="358">GAJUWAKA</option>
				<option value="368">BHIMILI</option>-->
				</select>
				</td>
			</tr>
			
			</table>
			<div style="margin-left:100px;"><input type="checkbox" id="checkedID">
				
				Create Booth if not Available</div>
			
			<p><input type="button" value="Submit" onclick="InsertmapVoterData();" id="mapVoterButtonId" class="btn btn-info" /><div id="ajaxImgDivId" style="display:none;"><img src="images/icons/search.gif"/></div></p>
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
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your Constituency" name="constituenciesListForVoterChanges" id="constituencySelectId" list="constituenciesList" listKey="id" listValue="name" headerKey="0" headerValue="Select Constituency"></s:select></td>
			</tr>
			<tr>
				<th>Publication Date</th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="publicationDateId" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
		</table>
		<p style="margin-left:64px;"><input type="button" value="Submit" id="voterChangesButtonId" class="btn btn-info" onClick="insertVoterModifiedData()"/>
		<input type="button" value="Delete" id="voterChangesButtonId" class="btn btn-info" onClick="deleteVoterModifiedData()"/>
		<div id="ajaxImgDivId" style="display:none;"><img src="images/icons/search.gif"/></div></p>
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
							else if(jsObj.task == "getConstituencies")
							{
								buildConstituencies(myResults);
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
			//var sNOUpdate = $("#sNOUpdateId").is(':checked');
			
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
				//isUpdateSno: sNOUpdate,
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
	function InsertmapVoterData()
	{
		var constituencyId = $("#mapVoterConstituencyId").val();
		var frompublicationDateId = $("#frompublicationDateId").val();
		var topublicationDateId = $("#topublicationDateId").val();
		
        var boothCreateflag = $("#checkedID").attr('checked');
		alert(constituencyId);
		var str = '';
			var errorEle = $(".errorMsgDiv1");
			errorEle.html('');
			
			 if(frompublicationDateId == 0 || frompublicationDateId== '')
			{
				errorEle.html('Please Select From Publication Date');
				return;
			}
			else if(topublicationDateId == 0 || topublicationDateId== '')
			{
				errorEle.html('Please Select To Publication Date');
				return;
			}
			else if(constituencyId == 0 || constituencyId == '' || constituencyId == null)
			{
				errorEle.html('Please Select Constituency');
				return;
			}
			
		var jsObj=
		{				
			constituencyId		 : constituencyId,
			frompublicationDateId: frompublicationDateId,
			topublicationDateId  :topublicationDateId,
			boothCreateflag      :boothCreateflag,
			task				 : "InsertmapVoterData"
			
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

	function deleteVoterModifiedData()
	{
		$("#errorMsgDivId").html('');
		var constituencyId =$("#constituencySelectId").val();
		var publicationDateId = $("#publicationDateId").val();
		if(constituencyId == 0)
		{
		$("#errorMsgDivId").html("Please Select Constituency");
		return;
		}
		else if(publicationDateId == 0)
		{
		$("#errorMsgDivId").html("Please Select Publication Date");
		return;
		}
	var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"deletecastVotersData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVoterModifiedDataAction.action?"+rparam;	
		 callAjax(jsObj,url);

	}

function getConstituenciesToMapPublicationData()
{

	var fromPublication =$("#frompublicationDateId").val();
	var toPublication = $("#topublicationDateId").val();
	var jsObj=
		{
		  fromPublication:fromPublication,
		  toPublication : toPublication,
		  task:"getConstituencies"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "voterAnalysisAjaxAction.action?"+rparam;	
		 callAjax(jsObj,url);
}

function buildConstituencies(results)
{
	
	var selectedElmt = document.getElementById("mapVoterConstituencyId");
	removeSelectElements(selectedElmt);
	for(var val in results)
	{
		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}
}

function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
</script>
</body>
</html>