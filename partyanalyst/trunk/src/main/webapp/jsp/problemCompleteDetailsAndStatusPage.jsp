<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problem Details</title>

<style type="text/css">

.problemReportHeader {
		background-image: url("images/icons/cadreReport/bg_center.png");
		background-repeat: repeat-x;
		color: #FFFFFF;
		font-size: 14px;
		font-weight: bold;
		height: 24px;
		padding-top: 1px;
		text-align: center;
		width: 225px;
	}

.bodyStyle {
	font-family:verdana;
	font-size:12px;
}

fieldset {
border:4px solid #CFD6DF;
margin-bottom:10px;
padding:10px;
}

legend {
background-color:#567AAF;
color:#FFFFFF;
font-size:12px;
padding:5px;
}

.problemDetailsTable td
{
	text-align:left;
}

.requiredFont
{
	color:red;
	margin-left:5px;
}

.selectWidth {
	width:250px;
}

.button {
background-attachment:scroll;
background-color:#335291;
background-image:none;
background-position:0 0;
background-repeat:repeat;
color:#FFFFFF;
float:left;
}
</style>

<script type="text/javascript">
 var problemCompleteDetailsVO = '${problemCompleteDetailsVO}';
 var pHistoryId = <%=request.getParameter("pHistoryId")%>;

function getCadreDetails()
{	
	var urlStr = "cadreSearchAction.action?windowTask=Search&addProblem=true";
	var cadreSearchForProblem = window.open(urlStr,"cadreSearchAndSMSPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");	
	cadreSearchForProblem.focus();
}

function setSelectedCadre(cadreId,cadreName)
{
	var cadreInputIdEle = document.getElementById("cadreInputId");
	cadreInputIdEle.value = cadreId;

	var cadreDetailsDivEle = document.getElementById("cadreDetailsDiv");
	var cadreVar ='';
	cadreVar +='<table align="center">';
	cadreVar +='<tr><th>Selected Cadre is :</th>';
	cadreVar +='<th>';
	cadreVar += cadreName;
	cadreVar +='</th>';
	cadreVar +='<td><input type="button" style="width:90px;height:25px;" value="show details" class="button" onclick="showCadreDetails(\''+cadreId+'\')"/></td></tr>';
	cadreVar +='</table>';
	cadreDetailsDivEle.innerHTML = cadreVar;
	cadreDetailsDivEle.style.display = 'block';
}

function showCadreDetails(cadreId)
{
	var showCadreDetailsBrowser = window.open("<s:url action="getCadreInfoAction.action"/>?windowTask=cadreInfoPopup&cadreId="+cadreId,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	showCadreDetailsBrowser.focus();
}

function doExecuteOnLoad()
{
	var probHisEle = document.getElementById("probHistoryId");
	probHisEle.value = pHistoryId;
}

</script>
</head>
<body class="bodyStyle">
<CENTER>
<TABLE border="0" cellpadding="0" cellspacing="0">
	<TR>
		<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
		<TD>
			<div class="problemReportHeader"><span style="margin-top:2px;">Problem Complete Details</span></div>
		</TD>
		<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>
	</TR>
</TABLE>
</CENTER>

<DIV style="width:550px;">
<FIELDSET>
<LEGEND>Problem Details</LEGEND>

<TABLE class="problemDetailsTable" align="left">
<tr>
	<td width="100px;"><b>Problem </td><td>:</td></b>
	<td><b>This is the Problem</b></td>
</tr>
<tr>
	<td width="100px;"><b>Description </td><td>:</td></b>
	<td><b>This is the Problem Description</b></td>
</tr>

<tr>
	<td width="100px;"><b>Location</td><td>:</td></b>
	<td><b>Andhra Pradesh, Nellore, Ananthasagaram</b></td>
</tr>
<tr>
	<td width="100px;"><b>Existing From</td><td>:</td></b>
	<td><b>2010-11-01</b></td>
</tr>

<tr>
	<td width="100px;"><b>Posted Date</td><td>:</td></b>
	<td><b>2011-02-11 18:30:22.0</b></td>
</tr>
</TABLE>
</FIELDSET>
</DIV>

<FIELDSET>
<LEGEND>Problem Moving</LEGEND>
<form method="post" action="problemAssigningAction">
<TABLE align="left"  width="100%">
<tr>
	<td width="200px"><b>Problem Resolving Region</b></td>
	<td><s:select id="scopeLevel" cssClass="selectWidth" name="problemResolvingRegionId" list="#session.impactedRegionsList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Scope"></s:select></td>
</tr>
<tr>
	<td><b>Problem Type</b></td>
	<td>
		<s:select id="problemTypeId" cssClass="selectWidth" name="problemType" list="{'01:social','02:Personal'}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Type"></s:select>
	</td>
</TR>

<tr>
	<td><b>Assign To Any Dept</b></td>
	<td>
		<s:select id="deptId" cssClass="selectWidth" name="dept" list="{'01:social','02:Personal'}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Dept"></s:select>
	</td>
</TR>

<tr>
	<td width="150px"><b>Assign To Any Cadre</b></td>
	<td><input type="button" style="width:120px;height:30px;" value="Get Cadre" class="button" onclick="getCadreDetails()"/></td>
</tr>
<input type="hidden" id="cadreInputId" name="cadreId"/>
<input type="hidden" id="probHistoryId" name="probHistoryId"/>
<s:submit name="Save"/>
</form>
</TABLE>
<table>
<tr><div id="cadreDetailsDiv" style="display:none;"></div></tr>
</table>
</FIELDSET>
<script>
doExecuteOnLoad();
</script>
</body>
</html>