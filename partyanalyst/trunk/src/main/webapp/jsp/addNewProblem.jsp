<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Problem</title>
<SCRIPT type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>
<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
 <link href="calendar.css" rel="stylesheet" type="text/css">


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
	 
	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	
<script type="text/javascript">


var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String problemLabel = rb.getString("problem");		
		String name = rb.getString("name");
		String occupation = rb.getString("occupation");		
		String contactnbr= rb.getString("contactnbr");
		String description = rb.getString("description");
		String identifiedDate = rb.getString("identifiedDate");
		String source = rb.getString("source");		
		String date = rb.getString("date");		
		String addNewProb = rb.getString("addNewProb");		
		String STATE = rb.getString("STATE");
		String DISTRICT = rb.getString("DISTRICT");
		String CONSTITUENCY = rb.getString("CONSTITUENCY");
		String ACONSTITUENCY = rb.getString("ACONSTITUENCY");
		String PCONSTITUENCY = rb.getString("PCONSTITUENCY");
		String MANDAL  = rb.getString("subRegions");
		String VILLAGE = rb.getString("VILLAGE");
		String HAMLET   = rb.getString("wardOrHamlet");		
		String email = rb.getString("email");
		String address = rb.getString("address");
		String telephoneNo = rb.getString("telephoneNo");
		String reportedDate = rb.getString("reportedDate");
		String existingFrom = rb.getString("existingFrom");
		String problemSource = rb.getString("problemSource");
		String mobile  = rb.getString("mobile");
		String problemAddingSuccess  = rb.getString("problemAddingSuccess");
		String locationAlert  = rb.getString("locationAlert");
		//String problemScope = rb.getString("problemScope");
	  %> }

var isParliament = '${isParliament}';

var localizationObj={
		problemAddingSuccess:'<%=problemAddingSuccess%>',
		locationAlert:'<%=locationAlert%>'
};
var hidden = '${sessionScope.HiddenCount}';
var userType = '${sessionScope.UserType}';
var accessType = '${sessionScope.USER.accessType}';
var accessValue = '${problemLocation}';
var scope = '${scope}';
var isSaved = '${isSuccessfullyInserted}';
var userType = '${sessionScope.UserType}';

function populateReferenceNo(){

  if('${callTracking}' == 'callTracking'){
    
	window.opener.setReferenceNo('${problemBeanFromDB.problemRefNum}');
	}
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
	//<a href=\"getCadreInfoAction.action?windowTask=cadreInfoPopup&cadreId=';
	//cadreVar += cadreId;
	//cadreVar +='>show details</a></td></tr>';
	cadreVar +='</table>';
	cadreDetailsDivEle.innerHTML = cadreVar;
	cadreDetailsDivEle.style.display = 'block';
}

function showCadreDetails(cadreId)
{
	var showCadreDetailsBrowser = window.open("<s:url action="getCadreInfoAction.action"/>?windowTask=cadreInfoPopup&cadreId="+cadreId,"influencingPeopleAction","scrollbars=yes,height=630,width=620,left=300,top=10");
	showCadreDetailsBrowser.focus();
}
function incrementHidden()
{
	<%
	if(session.getAttribute("HiddenCount")!=null){
		int hidden1 = (Integer)session.getAttribute("HiddenCount");
		 
		hidden1=hidden1+1;
		session.setAttribute( "HiddenCount", hidden1 );
	}	
	   
	%>
	hidden= '${sessionScope.HiddenCount}';
}
function executeOnload()
{
	var effectedRangeEl = document.getElementById("scopeLevel");
	var selectedeffectedRange =effectedRangeEl.options[effectedRangeEl.selectedIndex].value;  
	
	if(selectedeffectedRange != '0')
		populateLocations(selectedeffectedRange, 'onLoad');	
	
}

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

function getComplainedPersonDetails(name)
{	
	var personDetailsDivEle = document.getElementById("personDetailsDiv");
	var cadreDetailsDivEle = document.getElementById("cadreDetailsDiv");
	
	cadreDetailsDivEle.style.display='none';

	if(isSaved == 'true')
	{
	  document.getElementById("personNameField").value = '';
	  document.getElementById("mobileField").value = '';	document.getElementById("telephoneNoField").value = '';	document.getElementById("emailField").value = '';
	  document.getElementById("addressField").value = '';	

	  isSaved = false;
	}
		
	if(name =='External Person' || name=='Call Center')
	{			
		personDetailsDivEle.style.display = 'block';
	}else
	{	
		personDetailsDivEle.style.display = 'none';
	}

	if(name == 'Cadre')
	{
		var cadreEle = document.getElementById("cadreInputId");
		var cadreDivEle = document.getElementById("cadreDiv");

		var cadreDivVar = '';
		
		cadreDivVar += '<table align="center">';
		cadreDivVar += '<tr><td></td><td>';
		cadreDivVar += '<input type="button" style="width:120px;height:30px;" value="Get Cadre" class="button" onclick="getCadreDetails()"/></td>';
		cadreDivVar += '</tr></table>';
		cadreDivEle.innerHTML = cadreDivVar;
		cadreDivEle.style.display = 'block';
	}
	else
	{
		var cadreDivEl = document.getElementById("cadreDiv");
		cadreDivEl.style.display = 'none';
	}
}

function getCadreDetails(type)
{	
	var urlStr = "cadreSearchAction.action?windowTask=Search&addProblem=true";
	var cadreSearchForProblem = window.open(urlStr,"cadreSearchAndSMSPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");	
	cadreSearchForProblem.focus();
}

function hideProblemSourceRow()
{
	var userTypeSelectBoxEle = document.getElementById("userTypeSelectBox");
	if(userType == 'FreeUser')
	{
		var problemSourceRowEle = document.getElementById("problemSourceRowId");
		problemSourceRowEle.style.display = 'none';
	}
	else if(userType == 'PartyAnalyst' && isSaved == 'true')
	{
		userTypeSelectBoxEle.value = 0;
	}

	if(userType == 'PartyAnalyst' && isSaved != 'true')
	{
		var selected =userTypeSelectBoxEle.options[userTypeSelectBoxEle.selectedIndex].value; 
		if(selected == 2 || selected == 3)
		{
			var personDetailsDivEl = document.getElementById("personDetailsDiv");
			personDetailsDivEl.style.display = 'block';
		}
		if(selected == 4)
			userTypeSelectBoxEle.value = 0;
	}
}
function clearSuccessMsg(){
	
	var probSuccessMsg = document.getElementById("probSuccessMsgDiv");
	if(probSuccessMsg !=null)
	  probSuccessMsg.innerHTML='';
}
</script>
</head>
<body onload="executeOnload()" class="bodyStyle">
<CENTER>
<TABLE border="0" cellpadding="0" cellspacing="0">
	<TR>
		<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
		<c:if test="${problemBeanVO.problem == null}">
		<TD>
			<div class="cadreReportHeader"><span style="margin-top:2px;">Add New Problem</span></div>
		</TD>
		</c:if>
		<c:if test="${problemBeanVO.problem != null}">
		<TD>
			<div class="cadreReportHeader"><span style="margin-top:2px;">Update Problem</span></div>
		</TD>
		</c:if>
		<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>
	</TR>
</TABLE>
</CENTER>
<DIV><P>Fields marked with <font class="requiredFont"> * </font> are mandatory</P></DIV>
<s:form action="addNewProblemSubmitAction" method="POST" theme="simple" name="form">

 
<div id = "addNewProblemMainDiv">
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
	<div id="problemDetailsDiv" class="accessDivMain" >		
		<div id="problemDetailsDivBody">
			<c:if test="${problemBeanFromDB != null && sessionScope.UserType == 'PartyAnalyst'}">
			<div id="probSuccessMsgDiv">
				<DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px;">Problem Added Successfully...</DIV>
				<span style="color:green;font-weight:bold;margin:5px;">Problem Reference number is 
				<font color="maroon"><c:out value="${problemBeanFromDB.problemRefNum}">
				</c:out></font> used for further details</span></div>
			</c:if>
			<c:if test="${problemBeanFromDB != null && sessionScope.UserType == 'FreeUser'}">
				<DIV id="alertMessage" style="color:green;font-weight:bold;margin:5px;">Thanks for posting your problem.Your problem will be reviewed by our team and will be published once it gets acceptance from them</DIV><span style="color:green;font-weight:bold;margin:5px;">Problem Reference number is 
				<font color="maroon"><c:out value="${problemBeanFromDB.problemRefNum}">
				</c:out></font> used for further details</span>
			</c:if>
			<DIV style="width:500px;">
				<FIELDSET>
					<LEGEND>Problem Details</LEGEND>
					<TABLE class="problemDetailsTable">
						<tr>
							<td width="100px;"><%=problemLabel%><font class="requiredFont"> * </font></td>
							<td style="padding-left: 15px;"><s:textfield size="53" id="nameText" name="problem" onclick="clearSuccessMsg()" maxlength="100"/></td>						
						</tr>
						<tr>
							<td width="100px;"><%=description%><font class="requiredFont">*</font></td>
							<td style="padding-left: 15px;"><s:textarea cols="40" id="descTextArea" onkeyup="limitText('descTextArea','maxcount',500)"  name="description"/></td>
							
							<div id="limitDiv">
									<table style="width:100%;"><tr>
										<td style="width:50%;"><div id="remainChars"><span id="maxcount">500 </span> <span>chars remaining..</span></div></td>
										<td style="width:50%;"><div>Should not exceed 500 chars</div></td>
									</tr></table>
								</div>	
						</tr>
					</TABLE>
				</FIELDSET>
				<FIELDSET>
					<LEGEND>Problem Location</LEGEND>
					<P>Select Problem Location from the following List boxes</P>
					<DIV id="ajaxImgSpan" style="text-align:center;display:none;margin:10px;"><img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/></DIV>
					<DIV id="locationAlert" style="color:red;font-weight:bold"></DIV>
					<TABLE width="100%">
					<tr>
						<td>Problem Scope<font class="requiredFont">*</font></td>
						<td style="padding-left: 15px;">
							<s:select id="scopeLevel" cssClass="selectWidth" name="problemScope" value="defaultScope" list="#session.impactedRegionsList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Scope" onchange="populateLocations(this.options[this.selectedIndex].value,'onChange');getProblemTypes(this.options[this.selectedIndex].value)">
							</s:select>
						</td>
						</tr>
						
					<c:if test="${isParliament == null || isParliament == false}">
						<tr id="row1" style="display:none;">
							<td><%=STATE%><font class="requiredFont">*</font></td>
							<td style="padding-left: 15px;">
								<s:select id="stateField_s" cssClass="selectWidth" name="state" list="#session.statesList_ap" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','newProblemPost','districtField_s','currentAdd', 'null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</td>
						</tr>
						<tr id="row2" style="display:none;">
							<td><%=DISTRICT%><font class="requiredFont"> * </font></td>
							<td style="padding-left: 15px;">
								<s:select id="districtField_s" cssClass="selectWidth" name="district" list="#session.districtsList_ap" listKey="id" listValue="name" value="defaultDistrict" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'newProblemPost','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</td>
						</tr>
					</c:if>
					<c:if test="${isParliament == true}">
						<tr id="row1" style="display:none;">
							<td><%=STATE%><font class="requiredFont">*</font></td>
							<td style="padding-left: 15px;">
								<s:select id="stateField_s" cssClass="selectWidth" name="state" list="#session.statesList_ap" listKey="id" listValue="name" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','newProblemPost','pConstituencyField_s','currentAdd','null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</td>
						</tr>
						<TR id="row2" style="display:none;">	
							<TD><%=PCONSTITUENCY%></TD>
							<TD style="padding-left: 15px;">
							<s:select id="pConstituencyField_s" cssClass="selectWidth" name="pConstituencyId" list="#session.p_constituencies_ap" listKey="id" listValue="name" value="defaultPConstituency" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','newProblemPost','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</TD>
						</TR>
					</c:if>
					<tr id="row3" style="display:none;">
						<td><%=ACONSTITUENCY%><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;">
							<s:select id="constituencyField_s" cssClass="selectWidth" name="constituency" list="#session.constituenciesList_ap" listKey="id" listValue="name" value="defaultConstituency" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'newProblemPost','mandalField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
						</td>
					</tr>								
					<tr id="row4" style="display:none;">
						<td><%=MANDAL%><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;">
							<s:select id="mandalField_s" cssClass="selectWidth" name="mandal" list="#session.mandalsList_ap" listKey="id" listValue="name" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'newProblemPost','currentAdd','null','constituencyField_s', 'row6', 'row5');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
						</td>
					</tr>					
					<tr id="row5" style="display:none;">
						<td><%=HAMLET%><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;">
							<s:select id="hamletField_s" cssClass="selectWidth" name="village" list="#session.wardsOrHamletsList_ap" listKey="id" listValue="name" onchange="getBoothsInWard('currentAdd','constituencyField_s','boothField_s',this.options[this.selectedIndex].value,'newProblemPost','mandalField_s');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
						</td>
					</tr>	
					<tr id="row6" style="display:none;">
						<td >Booth No</td>
						<td style="padding-left: 15px;"><s:select id="boothField_s" cssClass="selectWidth" name="booth" list="#session.boothsList_ap" listKey="id" listValue="name" onchange="setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
						<td><input type="button" id="pBoothDetailsPanel" value="View Booths Details" onclick="showBoothsCompleteDetails('boothField_s', 'mandalField_s')"/></td>
					</tr>
					<tr>
						<td>Problem Types </td>
						<td style="padding-left: 15px;">
							<s:select id="problemTypeId" cssClass="selectWidth" name="problemTypeId" value="problemTypeId" list="#session.problemTypesList" listKey="id" listValue="name"></s:select>
						</td>
						</tr>
						</TABLE>											
				</FIELDSET>
				<FIELDSET>
				<LEGEND>More Details</LEGEND>
				<Div id="sourceAlert"></Div>								
				<TABLE width="85%">
				<tr>
					<td ><%=reportedDate%></td>
					<td style="padding-left:15px;"><s:textfield readonly="true" id="reportedDateField" name="reportedDate" size="20" /> 
					</td>
				</tr>
				<tr>
					<td ><%=existingFrom%><font class="requiredFont">*</font></td>		 
                    <td style="padding-left:10px;">
						<TABLE>
							<TR>
								<TD><s:textfield id="existingFromText" readonly="true" name="existingFrom" size="20"/>
								<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>
								<TD>
									<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()">
									<IMG width="23" height="23" src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>										
								</TD>
							</TR>
						</TABLE>		
					</td>
				</tr>
				<tr id="problemSourceRowId">
					<td><s:label for="problemSourceField" id="problemSourceLabel"  value="%{getText('problemSource')}" /><font class="requiredFont">*</font></td>
					<td style="padding-left:15px;"> 
					
					<s:select id="userTypeSelectBox"  name="probSource" list="#session.informationSourcesList" listKey="id" listValue="name" headerKey="0" onchange="getComplainedPersonDetails(this.options[this.selectedIndex].text)"/>

					</td>
				</tr>													
			</table>
			<div id="cadreDiv"></div>
			<div id="cadreDetailsDiv" style="display:none;"></div>
			<div id="personDetailsDiv" style="display:none;">
				<table class="personDetailsTable">
					<tr class="accessDivHead">
						<th align="left" colspan="2"><u>Complained Person Details</u></th>
					</tr>
					<tr></tr>
					<tr>
						<td width="100px;"><s:label for="personNameField" id="personNameFieldLabel"  value="%{getText('name')}" /><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;"><s:textfield id="personNameField" name="name" size="35"/></td>
					</tr>
					<tr>
						<td width="100px;"><s:label for="mobileField" id="mobileFieldLabel"  value="%{getText('mobile')}" /><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;"><s:textfield id="mobileField" name="mobile" maxlength="12" size="35"/></td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="telephoneNoField" id="telephoneNoLabel"  value="%{getText('telephoneNo')}" /></td>
						<td style="padding-left:0px;"><s:textfield id="telephoneNoField" name="phone" size="35"/></td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="emailField" id="emailLabel"  value="%{getText('email')}"/><font class="requiredFont"> * </font></td>
						<td style="padding-left:0px;"><s:textfield id="emailField" name="email" size="35"  maxlength = "50"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><s:label for="addressField" id="addressLabel"  value="%{getText('address')}" /><font class="requiredFont">*</font></td>
						<td style="padding-left:15px;"><s:textfield id="addressField" name="address" size="35"/>  </td>
					</tr>
					<tr>
						<td colspan="2"><INPUT type="hidden" value="1" name="status"/></td>
					</tr>
				</table>
			</div>
			</FIELDSET>
		<input type="hidden" id="problemLocation" name="problemLocationId" value="${problemLocation}" />
		<input type="hidden" name="defaultStateId" value="${defaultState}">
		<input type="hidden" name="defaultDistId" value="${defaultDistrict}">
		<input type="hidden" name="defaultConstId" value="${defaultConstituency}">
		<input type="hidden" name="defaultScopeId" value="${defaultScope}">
		<input type="hidden" name="isParliament" value="${isParliament}">	
		<input type="hidden" id="cadreInputId" name="cadreId">
		<input type="hidden" name="callTracking" value="${callTracking}">
    
		
			<table>
				<tr>
					<td><div style="margin-left:225px;"><s:submit name="Save" value="Save" cssClass="button"></s:submit></div></td>
					<td><input type="button" value="Exit" class="button" onclick="populateReferenceNo();refreshParentWindow();"/></td>
				</tr>
			</table>	
			</DIV>
		</div>
		
	</div>
</div>		
</s:form>

<script type="text/javascript">
getCurrentDate();
hideProblemSourceRow();
</script>
</body>
</html>