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

#thId {
color:#0000AA;
font-family:verdana;
font-weight:bold;
text-align:left;
}
</style>

<script type="text/javascript">
 var status = '${problemCompleteDetailsVO.problemBasicDetails.problemStatus}';
 
 var pHistoryId = <%=request.getParameter("pHistoryId")%>;

function limitText(limitField, limitCount, limitNum)
{		
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
}

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

function populateDeptLocations(index)
{
	var deptEl = document.getElementById("deptAreaHeadId");
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	
	deptEl.style.display = 'none';
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	
	if(index >= 1)
	{
		deptEl.style.display = '';
		row1El.style.display = '';
	}
	if(index >= 2)
	{
		row2El.style.display = '';
	}
	if(index >= 3)
	{
		row3El.style.display = '';
		row4El.style.display = '';
	}
	if(index >= 7)
	{
		row5El.style.display = '';
	}

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
		<td><input type="button" style="width:160px;height:30px;" value='MOVE TO ${problemCompleteDetailsVO.problemBasicDetails.problemStatus}' class="button" onclick="showProbStatusDetails();getProblemDepartments(0,'getProblemResolvingDeptScopes');getProblemDepartments(0,'getProblemTypes')"/></td> 
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
		<th width="225px"><s:label for="problemTypeId" id="problemTypeLabelId" theme="simple" value="Problem Type"/></th>
		<td><s:select id="problemTypeId" cssClass="selectWidth" name="problemType" theme="simple" list="#session.impactedRegionsList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Type" onChange="populateDeptLocations(this.options[this.selectedIndex].value);"></s:select></td>
	</tr>

	<tr>
		<th width="225px"><s:label for="resolvingDeptScopeId" id="resolvingDeptScope" theme="simple" value="Problem Resolving Dept Scope"/></th>
		<td><s:select id="resolvingDeptScopeId" cssClass="selectWidth" name="resolvingDeptScope" theme="simple" list="#session.impactedRegionsList" onChange="getProblemDepartments(this.options[this.selectedIndex],'getDepartmentCategories')"listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Scope"></s:select></td>
	</tr>

	<tr>
		<th><s:label for="deptCategoryId" id="deptCategoryLabelId"  theme="simple" value="Department Category"/></th>
		<td>
			<s:select id="deptCategoryId" cssClass="selectWidth" name="deptCategory" theme="simple" onChange="getProblemDepartments(this.options[this.selectedIndex],'getDepartments')" list="{'01:social','02:Personal'}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Type"></s:select>
		</td>
	</Tr>

	<tr>
		<th><s:label for="problemTypeId" id="wardOrHamletLabel" theme="simple" value="Select Department"/></th>
		<td>
			<s:select id="deptId" cssClass="selectWidth" name="dept" theme="simple" list="{'01:social','02:Personal'}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Dept"></s:select>
		</td>
	</tr>

	<tr id="deptAreaHeadId" style="display:none;">
		<th id="thId" colspan="4"><u>Problem Resolving Dept Area</u></th>
	</tr>

	<tr id="row1" style="display:none;">
		<th><s:label for="stateId" id="stateLabelId" theme="simple" value="Select State"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="stateId" cssClass="selectWidth" name="state" theme="simple" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select State"></s:select>
		</td>
	</tr>

	<tr id="row2" style="display:none;">
		<th><s:label for="districtId" id="districtLabelId" theme="simple" value="Select District"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="districtId" cssClass="selectWidth" name="district" theme="simple" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select District"></s:select>
		</td>
	</tr>

	<tr id="row3" style="display:none;">
		<th><s:label for="constituencyId" id="constituencyLabelId" theme="simple" value="Select Constituency"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="constituencyId" cssClass="selectWidth" name="constituency" theme="simple" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Constituency"></s:select>
		</td>
	</tr>

	<tr id="row4" style="display:none;">
		<th><s:label for="mandalId" id="mandalLabelId" theme="simple" value="Select Mandal/CORP/GMC"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="mandalId" cssClass="selectWidth" name="mandal" theme="simple" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Mandal/CORP/GMC"></s:select>
		</td>
	</tr>

	<tr id="row5" style="display:none;">
		<th><s:label for="villageId" id="villageLabelId" theme="simple" value="Select village"/><font class="requiredFont"> * </font></th>
		<td>
			<s:select id="villageId" cssClass="selectWidth" name="village" theme="simple" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Village"></s:select>
		</td>
	</tr>

</table>
</FIELDSET>
</DIV>
</td></tr>

<tr><td>
	<div>
		<table>
			
			<tr>
				<th width="225px"><s:label for="problemTypeId" id="wardOrHamletLabel" theme="simple" value="Assign This Problem To Cadre"/></th>
				<td><input type="button" style="width:120px;height:30px;" value="Get Cadre" class="button" onclick="getCadreDetails()"/></td>
			</tr>
			<tr>
				<div id="cadreDetailsDiv" style="display:none;" theme="simple" ></div></tr>
			</tr>
		</table>
	</div>
</td></tr>

<tr><td>
	<div>
		<table>
			<tr>
				<th width="100px;" theme="simple">Comments</th>
				<td style="padding-left: 15px;"><s:textarea rows="3" cols="45" id="descTextArea" theme="simple"  onkeyup="limitText('descTextArea','maxcount',500)"  name="description"/></td>
			</tr>	
		</table>
		<table style="width:100%;"><tr>
				<td style="width:50%;"><div id="remainChars"><span id="maxcount">500 </span> <span>chars remaining..</span></div></td>
				<td style="width:50%;"><div>Should not exceed 500 chars</div></td>
		</tr></table>
	</div>
</td></tr>

<tr><td>
	<div>
		<br><br>
		<table align="center" id="sumitTableId">
			<tr>
				<td width="110px"><s:submit name="Save" cssClass="button" style="width:100px;height:30px;background-color:#9871F3;" theme="simple" /></td>

				<td width="110px"><input type="button" value="Exit" class="button" style="width:100px;height:30px;background-color:#9871F3;" onclick="window.close()"/></td>
			</tr>
		</table>
	</div>
</td></tr>
</table>
<input type="hidden" id="cadreInputId" name="cadreId"/>
<input type="hidden" id="probHistoryId" name="probHistoryId"/>
</form>
<script>
doExecuteOnLoad();
</script>
</body>
</html>