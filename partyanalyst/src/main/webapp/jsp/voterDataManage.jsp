
	
	
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
		width: 440px; margin-bottom: 15px; margin-left: auto;
        margin-right: auto;float:none;
	}
	.voterManagementInnerDiv p{font-size: 13px;}
	#voterDataInsertId{font-weight:bold;}
	#voterChangesButtonId,#mapVoterButtonId{font-weight:bold;margin-top:10px;}
	.errorMsgDiv,.errorMsgDiv1,.errorMsgDiv2,.errorMsgDiv3{color: red;
    font-size: 13px;
    padding-bottom: 12px; padding-top: 12px;}
	table{font-size:13px;}
	table th{font-weight:normal;}
	
	#minResults,#maxResults{width:186px;}

#voterModificationFromPublicationId,#votermodificationConstituencyId,#voterModificationToPublicationId{width: 200px;}

#voterModificationErrorDiv{text-align: center; font-size: 13px;}
 .requiredFont{
 color:red;
 font-size:12px;
 }
 #voterDataInsertDiv{margin-top: 24px;}
 #ConstituencyDiv{padding-bottom: 21px;
    padding-top: 20px;}
#voterModificationErrorDiv{margin-top:3px;}
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
				</select>
				</td>
			</tr>
			
			</table>
			<div style="margin-left:100px;"><input type="checkbox" id="checkedID">
				
				Create Booth if not Available</div>
			
			<p><input type="button" value="Submit" onclick="InsertmapVoterData();" id="mapVoterButtonId" class="btn btn-info" /><span id="mapajaxImgDivId" style="display:none;"><img src="images/icons/search.gif"/></span></p>
				</center>
	</div>
</fieldset>
</div>

<div id="voterManagementMainDiv" class="span8">
 <div class="headingDiv" style="width:488px;">District Wise Voter Data Mapping From One publication to another Publication</div>
 
<fieldset>
<div class="errorMsgDiv2"></div>

<div class="voterManagementInnerDiv">
<center>
<table cellpadding="4">
			<tr>
				<th>From Publication </th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="frompublicationDateIdForDis" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
			
			<tr>
				<th>To Publication </th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="topublicationDateIdForDis" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select" onchange="getConstituenciesToMapPublicationDataForDis();"></s:select></td>
			</tr>

			<tr>
				<th>District </th>
				<td>:</td>
				<td>
				
				<s:select  theme="simple" cssClass="selectBoxWidth" label="Select Your District" name="districtList" id="voterDataDistrictList" list="districts" listKey="id" listValue="name"/>
				
               </td>
			</tr>
			
			</table>
			<div style="margin-left:100px;"><input type="checkbox" id="checkedID">
				
				Create Booth if not Available</div></br>
			
			<p><input type="button" value="Submit" onclick="InsertmapVoterDataForDis();" id="mapVoterButtonIdForDis" class="btn btn-info" /><span id="mapajaxImgDivId" style="display:none;"><img src="images/icons/search.gif"/></span></p>
			<div id="errorMsgDivId1" class="errorMsgDiv3"></div>
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
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Your Constituency" name="constituenciesListForVoterChanges" id="constituencySelectId" list="constituencyList" listKey="id" listValue="name" headerKey="0" headerValue="Select Constituency"></s:select></td>
			</tr>
			<tr>
				<th>Publication Date</th>
				<td>:</td>
				<td><s:select cssClass="selectBoxWidth" theme="simple" label="Select Publication Date" name="publicationDateList" id="publicationDateId" list="publicationDateList" listKey="id" listValue="name" headerKey="0" headerValue="Select"></s:select></td>
			</tr>
		</table>
		<p style="margin-left:64px;"><input type="button" value="Submit" id="voterChangesButtonId" class="btn btn-info" onClick="insertVoterModifiedData()"/>
		<input type="button" value="Delete" id="voterDeleteButtonId" class="btn btn-info" style="margin-top:7px;" onClick="deleteVoterModifiedData()"/>
		<span id="ajaxImgDivId" style="display:none;"><img src="images/icons/search.gif"/></span></p>
	</center>		
	</div>
	</fieldset>
</div>


<!-- voters Modification Info Div -->
<div id="voterManagementMainDiv" class="span8">
<div class="headingDiv" style="width:450px;">Populate voters Modification Info To Intermediate Tables</div>
 <fieldset>
    <div id="voterModificationErrorDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
	<center>
     <table cellpadding="4">
	  <tr>
	    <td>Constituency</td>
		<td>:</td>
		<td><s:select theme="simple" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="votermodificationConstituencyId" list="constituencyList" listKey="id" listValue="name"/></td>
	  </tr>
	  <tr>
	   <td>From Publication Date</td>
	   <td>:</td>
	   <td><select id="voterModificationFromPublicationId" class="selectWidth" style="width:200px;height:25px;" name="publicationDateList" >
		</select></td>
	  </tr>
	  <tr>
	  <td>To Publication Date</td>
	  <td>:</td>
	  <td><select id="voterModificationToPublicationId" class="selectWidth" style="width:200px;height:25px;" name="publicationDateList" >
		</select></td>
	  </tr>
	 </table>
     
		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="votermodificationBtn" />
			<!-- <input type="button" class="btn btn-info" value="Delete Existing Data" id="votermodificationvoterDataDeleteBtn" /> -->
			<img src="./images/icons/search.gif" style="display:none;margin-left: 10px;" id="votermodificationImage" />
		</div>
		</center>
	</div>
 </fieldset>
</div>	
<!-- voters Modification Info Div End -->

<div id="voterManagementMainDiv" class="span8">
<div class="headingDiv" style="width:450px;">Populate voters Modification Info To Intermediate Tables</div>
 <fieldset>
    <div id="voterModificationErrorDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
	<center>
     <table cellpadding="4">
	  <tr>
	    <td>Constituency</td>
		<td>:</td>
		<td><s:select theme="simple" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="votermodificationConstituencyId" list="constituencyList" listKey="id" listValue="name"/></td>
	  </tr>
	  <tr>
	   <td>From Publication Date</td>
	   <td>:</td>
	   <td><select id="voterModificationFromPublicationId" class="selectWidth" style="width:200px;height:25px;" name="publicationDateList" >
		</select></td>
	  </tr>
	  <tr>
	  <td>To Publication Date</td>
	  <td>:</td>
	  <td><select id="voterModificationToPublicationId" class="selectWidth" style="width:200px;height:25px;" name="publicationDateList" >
		</select></td>
	  </tr>
	 </table>
     
		<div id="voterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="votermodificationBtn" />
			<!-- <input type="button" class="btn btn-info" value="Delete Existing Data" id="votermodificationvoterDataDeleteBtn" /> -->
			<img src="./images/icons/search.gif" style="display:none;margin-left: 10px;" id="votermodificationImage" />
		</div>
		</center>
	</div>
 </fieldset>
</div>	
<!-- voters Modification Info Div End -->



<!-- voters Data Insertion Info Div End -->

<div id="voterManagementMainDiv" class="span8">
<div class="headingDiv" style="width:450px;">Populate Telugu voters Data Info To Intermediate Tables</div>
 <fieldset>
    <div id="teluguvoterDataErrorDiv"></div>
	<div id="ConstituencyDiv" class="selectDiv">
	<center>
     <table cellpadding="4">
	  <tr>
	    <td>Constituency</td>
		<td>:</td>
		<td><select theme="simple" cssClass="selectWidth" label="Select Your Constituency" name="constituencyList" id="teluguVoterDataConstituencyId" ></select></td>
	  </tr>
	  </table>
     
		<div id="teluguvoterDataInsertDiv">
			<input type="button" class="btn btn-info" value="Submit" id="teluguVoterDataBtn" onclick="insertTeluguVoterData();" />
			<!-- <input type="button" class="btn btn-info" value="Delete Existing Data" id="votermodificationvoterDataDeleteBtn" /> -->
			<img src="./images/icons/search.gif" style="display:none;margin-left: 10px;" id="teluguVoterDataImg" />
		</div>
		</center>
	</div>
 </fieldset>
</div>	
<!-- voters Data Insertion Info Div End -->


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
							/*else if(jsObj.task == "getConstituenciesForDis")
							{
								buildConstituenciesForDis(myResults);
							}*/
							else if(jsObj.task == "deleteVoterModifiedData")
							{
								showdeleteVoterModifiedDataStatus(myResults);
							}
							
							else if(jsObj.task == "getModifiedVotersBetweenTwoPublications")
							 showVoterModificationSaveStatus(myResults);
							 else if(jsObj.task == "getVoterDataForDis")
							  showVoterModificationSaveStatusForDis(myResults);
							 


							else if(jsObj.task == "getPublicationDatesForVotingModificationBetweenDates")
								buildpublicationDateListForVoterModification(myResults,jsObj);
							else if(jsObj.task == "getTempConstituencies")
							{
								buildTempConstituencies(myResults);
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
			errorStr += '<font color="red">Please Select Constituency</font><BR>';
			flag = true;
		}
		if(publicationDateId == 0)
		{
			errorStr += '<font color="red">Please Select Publication Date</font><BR>';
			flag = true;
		}
		if(flag)
		{
			errorDivEle.innerHTML = errorStr;
			return;
		}
		
		errorDivEle.innerHTML = '';
		
		$("#ajaxImgDivId").css({'display':'block','display':'inline'});
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
	
	function InsertmapVoterDataForDis()
	{
		var DistrictId = $("#voterDataDistrictList").val();
		var frompublicationDateId = $("#frompublicationDateIdForDis").val();
		var topublicationDateId = $("#topublicationDateIdForDis").val();
		
        var boothCreateflag = $("#checkedID").attr('checked');
		
		var str = '';
			var errorEle = $(".errorMsgDiv2");
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
			else if(DistrictId == 0 || DistrictId == '' || DistrictId == null)
			{
				errorEle.html('Please Select District');
				return;
			}
			
		var jsObj=
		{				
			DistrictId		 : DistrictId,
			frompublicationDateIdForDis: frompublicationDateId,
			topublicationDateIdForDis  :topublicationDateId,
			boothCreateflag      :boothCreateflag,
			task				 : "getVoterDataForDis"
			
		}

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "insertVoterDataForDisAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	function showModifiedVotersInsertDataStatus(results)
	{
		$(".errorMsgDiv").html('');
		
		$("#ajaxImgDivId").css("display","none");
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
		$("#errorMsgDivId").html("Please Select Constituency").css("color","red");;
		return;
		}
		else if(publicationDateId == 0)
		{
		$("#errorMsgDivId").html("Please Select Publication Date").css("color","red");;
		return;
		}
		$("#ajaxImgDivId").css({'display':'block','display':'inline'});
		$("#voterDeleteButtonId").attr("disabled", "disabled");
	var jsObj=
		{
		  id				  :constituencyId,
		  publicationDateId : publicationDateId,
		  task:"deleteVoterModifiedData"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "deleteVoterModifiedDataAction.action?"+rparam;	
		 callAjax(jsObj,url);

	}

function getConstituenciesToMapPublicationData()
{

	var fromPublication =$("#frompublicationDateId").val();
	var toPublication = $("#topublicationDateId").val();
	$("#mapajaxImgDivId").css({'display':'block','display':'inline'});
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

function getConstituenciesToMapPublicationDataForDis()
{

	var fromPublication =$("#frompublicationDateIdForDis").val();
	var toPublication = $("#topublicationDateIdForDis").val();
	$("#mapajaxImgDivId").css({'display':'block','display':'inline'});
	var jsObj=
		{
		  fromPublication:fromPublication,
		  toPublication : toPublication,
		  task:"getConstituenciesForDis"
		};
		 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		 var url = "voterAnalysisAjaxForDisAction.action?"+rparam;	
		 callAjax(jsObj,url);
}

function buildConstituencies(results)
{
	$("#mapajaxImgDivId").css("display","none");
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
function buildTempConstituencies(results)
{
	
	var selectedElmt = document.getElementById("teluguVoterDataConstituencyId");
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
function buildConstituenciesForDis(results)
{
	$("#mapajaxImgDivId").css("display","none");
	var selectedElmt = document.getElementById("voterDataDistrictList");
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

	function showdeleteVoterModifiedDataStatus(result)
	{
		$("#ajaxImgDivId").css("display","none");
		$("#voterDeleteButtonId").removeAttr("disabled");
		
		if(result.resultCode == 0)
		{
			$("#errorMsgDivId").html("Voters Modification Data Deleted Successfully.").css("color","green");
				return;
		}
		else
		{
			$("#errorMsgDivId").html("Error Occured try Again.").css("color","red");
				return;
		}
	}


$(document).ready(function(){

$("#votermodificationConstituencyId").change(function(){
   
	 var id = $("#votermodificationConstituencyId").val();
	 var selectElmt = "voterModificationFromPublicationId";
	 var toDate = "voterModificationToPublicationId";
	 
	 $("#voterModificationErrorDiv").html('');
	  if(id == 0)
	  {
	   $("#voterModificationErrorDiv").html('Please Select Constituency.').css("color","red");
		return;
	  }
	
	// $("#castajaxLoad").css('display','block');
	 var jsObj=
	 {
		selected:id,
		selectElmt:selectElmt,
		toDate:toDate,
		task:"getPublicationDatesForVotingModificationBetweenDates"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "voterAnalysisAjaxAction.action?"+rparam;	
	 callAjax(jsObj,url);

	});


$("#votermodificationBtn").click(function(){
	 
	 $("#voterModificationErrorDiv").html('');
	 var fromDate = $("#voterModificationFromPublicationId").val();
	 var toDate = $("#voterModificationToPublicationId").val();
	 var constituencyId = $("#votermodificationConstituencyId").val();
	 var flag = false;
	 var str = '<font color="red">';
	 
	 if(constituencyId == 0)
	 {
	  str +='Please Select Constituency.<br>';
	  flag = true;
	 }
	 if(fromDate == 0 )
	 {
	  str +='Please Select From Date.<br>';
	  flag = true;
	 }
	 if(toDate == 0)
	 {
	  str +='Please Select To Date.<br>';
	  flag = true;
	 }
	 if(flag)
	 { 
	  $("#voterModificationErrorDiv").html(str);
	  return;
	 }
	 
	 $("#votermodificationImage").css("display","inline-block");
	 var jsObj=
	 {
		fromPublicationId:fromDate,
		constituencyId:constituencyId,
		toPublication:toDate,
		task:"getModifiedVotersBetweenTwoPublications"
	 };
	 var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	 var url = "getModifiedVotersBetweenTwoPublicationsAction.action?"+rparam;	
	 callAjax(jsObj,url);
	 
	 
	});
	
});


function showVoterModificationSaveStatus(result)
{
$("#votermodificationImage").css("display","none");
$("#voterModificationErrorDiv").html('');
  if(result.resultCode == 0)
  {
	$("#voterModificationErrorDiv").html('Data Inserted successufully').css("color","green");
 }
 else
  $("#voterModificationErrorDiv").html('Error Occured try Again.').css("color","red");
}


function showVoterModificationSaveStatusForDis(result)
{

$("#errorMsgDivId1").html('');
 
  var str='';
  str+='<table border=4 width=400>';
 
   str+= '<tr><td>Constituency Name</td>';
       str+= '<td colspan=2>From Publication</td>';
		str+= '<td colspan=2>To Publication</td>';
        str+= '<td>Insert Status</td>';
    str+= '</tr>';
	
	for(var i in result)
	{
    str+= '<tr><td>'+result[i].name+'</td>';
	str+= '<td colspan=2><center>'+result[i].validCount+'</center></td>';
    str+= '<td colspan=2><center>'+result[i].totalCount+'</center></td>';
	if(result[i].id==0)
	{
	str+= '<td>Inserted</td>';
	}
	else
	{
	str+= '<td>Exception </td>';
    }
	
	str+='</tr>';
	}
    str+='</table>'
	$("#errorMsgDivId1").html(str).css("color","black");
 
}

function buildpublicationDateListForVoterModification(result,jsObj)
{
 
 $("#voterModificationFromPublicationId").find('option').remove();
 $("#voterModificationToPublicationId").find('option').remove();
 
 
 if(result != null && result.length > 0)
 {
   for(var i in result)
   {
    $("#voterModificationFromPublicationId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
	$("#voterModificationToPublicationId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
   }
 }
}
function insertTeluguVoterData()
{
	        var constituencyId = $("#teluguVoterDataConstituencyId").val();
		    var str = '';
			var errorEle = $("#teluguvoterDataErrorDiv");
			errorEle.html('');
			if(constituencyId == 0 || constituencyId == '')
			{
				errorEle.html('Please Select Constituency');
				return;
			}
			$("#teluguvoterDataErrorDiv").html('');
			var jsObj=
			{				
				constituencyId: constituencyId,
				task: "insertVoterData"
			}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "insertteluguVoterDataAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function getConstituencyTempList(){

	var jsObj= 
		{	
			task:"getTempConstituencies"		
		};
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getVoterTempConstituenciesAction.action?"+param;
		callAjax(jsObj,url);

	}
getConstituencyTempList();
</script>
</body>
</html>