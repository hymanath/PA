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
</script>
</head>
<body onload="executeOnload()" class="bodyStyle">
<CENTER>
<TABLE border="0" cellpadding="0" cellspacing="0">
	<TR>
		<TD><img border="none" src="images/icons/cadreReport/bg_left.png"></TD>
		<TD>
			<div class="cadreReportHeader"><span style="margin-top:2px;">Add New Problem</span></div>
		</TD>
		<TD><img border="none" src="images/icons/cadreReport/bg_right.png"></TD>
	</TR>
</TABLE>
</CENTER>
<DIV><P>Fields marked with <font class="requiredFont"> * </font> are mandatory</P></DIV>
<s:form action="addNewProblemSubmitAction" method="GET" theme="simple" name="form" onsubmit="return validateClientSide()">

 
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
		<div id="problemDetailsDivBody" class="accessDivBody">
			<c:if test="${problemBeanFromDB != null && sessionScope.UserType == 'PartyAnalyst'}">
				<DIV id="alertMessage" style="color:green;font-weight:bold">Problem Added Successfully</DIV>
			</c:if>
			<c:if test="${problemBeanFromDB != null && sessionScope.UserType == 'FreeUser'}">
				<DIV id="alertMessage" style="color:green;font-weight:bold">Thanks for posting your problem.Your problem will be reviewed by our team and will be published once it gets acceptance from them</DIV>
			</c:if>
			
			<DIV style="width:500px;">
				<FIELDSET>
					<LEGEND>Problem Details</LEGEND>
					<TABLE class="problemDetailsTable">
						<tr>
							<td width="100px;"><%=problemLabel%><font class="requiredFont"> * </font></td>
							<td style="padding-left: 15px;"><s:textfield size="53" id="nameText" name="problem" maxlength="200"/></td>						
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
							<s:select id="scopeLevel" cssClass="selectWidth" name="problemScope" value="defaultScope" list="#session.impactedRegionsList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Problem Scope" onchange="populateLocations(this.options[this.selectedIndex].value,'onChange')"></s:select>
						</td>
						</tr>
					<c:if test="${isParliament == null || isParliament == false}">
						<tr id="row1" style="display:none;">
							<td><%=STATE%><font class="requiredFont">*</font></td>
							<td style="padding-left: 15px;">
								<s:select id="stateField_s" cssClass="selectWidth" name="state" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select State" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','newProblemPost','districtField_s','currentAdd', 'null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</td>
						</tr>
						<tr id="row2" style="display:none;">
							<td><%=DISTRICT%><font class="requiredFont"> * </font></td>
							<td style="padding-left: 15px;">
								<s:select id="districtField_s" cssClass="selectWidth" name="state" list="#session.districtsList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select District" value="defaultDistrict" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'newProblemPost','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</td>
						</tr>
					</c:if>
					<c:if test="${isParliament == true}">
						<tr id="row1" style="display:none;">
							<td><%=STATE%><font class="requiredFont">*</font></td>
							<td style="padding-left: 15px;">
								<s:select id="stateField_s" cssClass="selectWidth" name="state" list="#session.statesList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select State" value="defaultState" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','newProblemPost','pConstituencyField_s','currentAdd','null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</td>
						</tr>
						<TR id="row2" style="display:none;">	
							<TD><%=PCONSTITUENCY%></TD>
							<TD style="padding-left: 15px;">
							<s:select id="pConstituencyField_s" cssClass="selectWidth" name="state" list="#session.p_constituencies" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Constituency" value="defaultConstituency" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','newProblemPost','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
							</TD>
						</TR>
					</c:if>
					<tr id="row3" style="display:none;">
						<td><%=ACONSTITUENCY%><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;">
							<s:select id="constituencyField_s" cssClass="selectWidth" name="state" list="#session.constituenciesList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Constituency" value="defaultConstituency" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'newProblemPost','mandalField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
						</td>
					</tr>								
					<tr id="row4" style="display:none;">
						<td><%=MANDAL%><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;">
							<s:select id="mandalField_s" cssClass="selectWidth" name="state" list="#session.mandalsList" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'newProblemPost','null','currentAdd','constituencyField_s', 'row6', 'row5');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
						</td>
					</tr>					
					<tr id="row5" style="display:none;">
						<td><%=HAMLET%><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;">
							<s:select id="hamletField_s" cssClass="selectWidth" name="state" list="{}" listKey="id" listValue="name" headerKey = "0" headerValue = "Select Location" onchange="getBoothsInWard('currentAdd','constituencyField_s','boothField_s',this.options[this.selectedIndex].value,'newProblemPost','mandalField_s');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select>
						</td>
					</tr>	
					<tr id="row6" style="display:none;">
						<td >Booth No</td>
						<td style="padding-left: 15px;"><s:select id="boothField_s" cssClass="selectWidth" name="booth" list="{}" listKey="id" listValue="name" onchange="setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
						<td><input type="button" id="pBoothDetailsPanel" value="View Booths Details" onclick="showBoothsCompleteDetails('boothField_s', 'mandalField_s')"/></td>
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
								<TD><s:textfield id="existingFromText" readonly="true" name="existingFromDate" size="20"/>
								<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>
								<TD>
									<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>										
								</TD>
							</TR>
						</TABLE>									
					</td>
				</tr>
				<!--<tr>
					<td><s:label for="problemSourceField" id="problemSourceLabel"  value="%{getText('problemSource')}" /><font class="requiredFont">*</font></td>
					<td style="padding-left:15px;"> 
						<select name="probSource" id="probSource" onchange="getPersonDetails(this.options[this.selectedIndex].text);">
							<c:forEach var="probSource"  items="${sessionScope.problemSources}" >
									<option value='${probSource.id}'>${probSource.name}</option>
								</c:forEach>
						</select>
					</td>
				</tr>													
			--></table>			
			<div id="personDetailsDiv" class="accessDivBody" style="display: none;">
				<table class="personDetailsTable">
					<tr class="accessDivHead">
						<th align="left" colspan="2"><u>Complained Person Details</u></th>
					</tr>
					<tr></tr>
					<tr>
						<td width="100px;"><s:label for="personNameField" id="personNameFieldLabel"  value="%{getText('name')}" /><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;"><s:textfield id="personNameField" name="name" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;"><s:label for="mobileField" id="mobileFieldLabel"  value="%{getText('mobile')}" /><font class="requiredFont"> * </font></td>
						<td style="padding-left: 15px;"><s:textfield id="mobileField" name="mobile" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="telephoneNoField" id="telephoneNoLabel"  value="%{getText('telephoneNo')}" /></td>
						<td style="padding-left:15px;"><s:textfield id="telephoneNoField" name="phone" size="53"/></td>
					</tr>
					<tr>
						<td width="100px;" style="padding-left:15px;"><s:label for="emailField" id="emailLabel"  value="%{getText('email')}" /></td>
						<td style="padding-left:15px;"><s:textfield id="emailField" name="email" size="53"/>  </td>
					</tr>
					<tr>
						<td width="100px;"><s:label for="addressField" id="addressLabel"  value="%{getText('address')}" /><font class="requiredFont">*</font></td>
						<td style="padding-left:15px;"><s:textfield id="addressField" name="address" size="53"/>  </td>
					</tr>
					<tr>
						<td colspan="2"><INPUT type="hidden" value="1" name="status"/></td>
					</tr>
				</table>
			</div>
			</FIELDSET>
			<input type="hidden" id="problemLocation" name="problemLocationId" value="${problemLocation}" />	
			<table>
				<tr>
					<td><div style="margin-left:225px;"><s:submit name="Save" value="Save" cssClass="button"></s:submit></div></td>
					<td><input type="button" value="Exit" class="button" onclick="refreshParentWindow()"/></td>
				</tr>
			</table>	
			</DIV>
		</div>
		
	</div>
</div>		
</s:form>

<script type="text/javascript">
getCurrentDate();
</script>
</body>
</html>