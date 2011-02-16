<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problem Details</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

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

.problemDetailsTable td,th
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

.requiredFont {
color:red;
margin-left:5px;
}
</style>

<script type="text/javascript">
 var status = '${problemCompleteDetailsVO.problemBasicDetails.problemStatus}';
 
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

function showProbStatusDetails()
{ 
	var AssignTabEle = document.getElementById("probAssigningTabId");
	AssignTabEle.style.display = "block";
}

function getProblemDepartments(selected,task)
{
	var jsObj=
		{
			selected : selected,
			task	 : task
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemDepartmentsAjaxAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
						}
						catch(e)
						{   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o )
					 {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
					 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
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
<DIV><P>Fields marked with <font class="requiredFont"> * </font> are mandatory</P></DIV>
<DIV>
<table>
	<tr>
		<td colspan="2">
			<div style="color: red;">
				<s:actionerror />
				<s:fielderror />
			</div>
		</td>
	</tr>
</table>
</DIV>
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

<DIV id="assigningButtonDiv">

<Table align="center">
	<TR>
		<td><input type="button" style="width:160px;height:30px;" value='MOVE TO ${problemCompleteDetailsVO.problemBasicDetails.problemStatus}' class="button" onclick="showProbStatusDetails();getProblemDepartments('getProblemResolvingDeptScopes')"/></td> 
	</TR>
<Table>
</DIV>

<form method="post" action="problemAssigningAction.action">
<table width="100%" id="probAssigningTabId" style="display:none;">
<tr><td>
<DIV id="problemAssigningDiv">
<FIELDSET>
<LEGEND>Problem Details</LEGEND>
<TABLE>
	<tr>
		<th width="225px"><s:label for="scopeLevel" id="wardOrHamletLabel" theme="simple" value="Problem Resolving Dept Scope"/></th>
		<td><s:select id="scopeLevel" cssClass="selectWidth" name="problemResolvingRegionId" theme="simple" list="#session.impactedRegionsList" onChange="getProblemDepartments(this.options[this.selectedIndex],'getDepartmentCategories')"listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Scope"></s:select></td>
	</tr>

	<tr>
		<th><s:label for="problemTypeId" id="wardOrHamletLabel"  theme="simple" value="Department Category"/></th>
		<td>
			<s:select id="problemTypeId" cssClass="selectWidth" name="problemType" theme="simple" onChange="getProblemDepartments(this.options[this.selectedIndex],'getDepartments')" list="{'01:social','02:Personal'}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Type"></s:select>
		</td>
	</Tr>

	<tr>
		<th><s:label for="problemTypeId" id="wardOrHamletLabel" theme="simple" value="Select Department"/></th>
		<td>
			<s:select id="deptId" cssClass="selectWidth" name="dept" theme="simple" list="{'01:social','02:Personal'}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Dept"></s:select>
		</td>
	</tr>

	<tr>
		<th width="150px"><s:label for="problemTypeId" id="wardOrHamletLabel" theme="simple" value="Assign To Any Cadre"/></th>
		<td><input type="button" style="width:120px;height:30px;" value="Get Cadre" class="button" onclick="getCadreDetails()"/></td>
	</tr>
	<tr>
		<td></td>
		<td><s:submit name="Save" theme="simple" /></td>
	</tr>
	<tr>
		<div id="cadreDetailsDiv" style="display:none;" theme="simple" ></div></tr>
	</tr>

	<input type="hidden" id="cadreInputId" name="cadreId"/>
	<input type="hidden" id="probHistoryId" name="probHistoryId"/>
	</form>
	
</table>
</FIELDSET>
</DIV>
</td></tr>
</table>

<script>
doExecuteOnLoad();
</script>
</body>
</html>