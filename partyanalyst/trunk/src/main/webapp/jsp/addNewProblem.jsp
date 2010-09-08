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
		String MANDAL  = rb.getString("MANDAL");
		String VILLAGE = rb.getString("VILLAGE");
		String HAMLET   = rb.getString("HAMLET");		
		String email = rb.getString("email");
		String address = rb.getString("address");
		String telephoneNo = rb.getString("telephoneNo");
		String reportedDate = rb.getString("reportedDate");
		String existingFrom = rb.getString("existingFrom");
		String problemSource = rb.getString("problemSource");
		String mobile  = rb.getString("mobile");
		String problemAddingSuccess  = rb.getString("problemAddingSuccess");
		String locationAlert  = rb.getString("locationAlert");
	  %> }
var accessType = '${accessType}';
var localizationObj={
		problemAddingSuccess:'<%=problemAddingSuccess%>',
		locationAlert:'<%=locationAlert%>'
};
var hidden = '${sessionScope.HiddenCount}';

function incrementHidden()
{
	<%
	int hidden1 = (Integer)session.getAttribute("HiddenCount");
	 
	hidden1=hidden1+1;
	   session.setAttribute( "HiddenCount", hidden1 );
	%>
	hidden= '${sessionScope.HiddenCount}';
}
</script>
</head>
<body  class="bodyStyle">
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
<s:form action="addNewProblemSubmitAction" method="POST" theme="simple" name="form" onsubmit="return validateClientSide()">

 
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
				<c:if test="${problemBeanFromDB == null}">
					<DIV id="alertMessage" style="display:none;"></DIV>
				</c:if>
				<c:if test="${problemBeanFromDB != null}">
					<DIV id="alertMessage" style="color:green;font-weight:bold">Problem Added Successfully</DIV>
				</c:if>
				<DIV style="width:500px;">
					<FIELDSET>
						<LEGEND>Problem Details</LEGEND>
						<table class="problemDetailsTable">
							<tr>
								<td width="100px;"><%=problemLabel%><font class="requiredFont"> * </font></td>
								<td style="padding-left: 15px;"><INPUT type="text" size="53" id="nameText" name="problem"></td>						
							</tr>
							<tr>
								<td width="100px;"><%=description%><font class="requiredFont">*</font></td>
								<td style="padding-left: 15px;"><textarea cols="40" id="descTextArea" name="description"></textarea></td>
							</tr>
							</TABLE>
					</FIELDSET>	
				</DIV>
							<DIV style="width:500px;">
							<FIELDSET>
								<LEGEND>Problem Location</LEGEND>
								<P>Select Problem Location from the following List boxes</P>
								<DIV id="ajaxImgSpan" style="text-align:center;display:none;margin:10px;"><img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/></DIV>
								<DIV id="locationAlert" style="color:red;font-weight:bold"></DIV>
								<TABLE>
									<tr>
										<td><%=STATE%><font class="requiredFont">*</font></td>
										<td style="padding-left: 15px;"><select id="pstateField" class="selectWidth" name="state" onchange="getDistrictList(this.options[this.selectedIndex].value)">
											<c:forEach var="state"  items="${sessionScope.stateList}" >
												<option value='${state.id}'>${state.name}</option>
											</c:forEach>										
										</select>
										</td>
									</tr>
									<c:if test="${accessType != 'MP'}">
										<tr>
										<td><%=DISTRICT%><font class="requiredFont"> * </font></td>
										<td style="padding-left: 15px;"><select id="pdistrictField" class="selectWidth" name="district" onchange='getConstituencyList(this.name,this.options[this.selectedIndex].value,"addProblem")'>
											<c:forEach var="district"  items="${sessionScope.districtList}" >
												<option value='${district.id}'>${district.name}</option>	
											</c:forEach>									
										</select></td>
									</tr>
								</c:if>
								<c:if test="${accessType == 'MP'}">
										<TR>	
											<TD><%=PCONSTITUENCY%></TD>
											<TD style="padding-left: 15px;"><select id="parlConstField" class="selectWidth"  name="parliamentConstituency" />
												<c:forEach var="parlConstituency"  items="${sessionScope.parliamentConstituencyList}" >
														<option value='${parlConstituency.id}'>${parlConstituency.name}</option>
												</c:forEach>
											</TD>
										</TR>
								</c:if>
								<tr>
										<td><%=ACONSTITUENCY%><font class="requiredFont"> * </font></td>
										<td style="padding-left: 15px;"><select id="pconstituencyField" class="selectWidth" name="constituency" onchange='getMandalList(this.name,this.options[this.selectedIndex].value,"addProblem")'>
											<c:forEach var="constituency"  items="${sessionScope.constituencyList}" >
												<option value='${constituency.id}'>${constituency.name}</option>
											</c:forEach>										
										</select></td>
								</tr>								
								<tr>
									<td><%=MANDAL%><font class="requiredFont"> * </font></td>
									<td style="padding-left: 15px;"><select id="pmandalField" class="selectWidth" name="mandal" onchange='getTownshipsForMandal(this.name,this.options[this.selectedIndex].value,"addProblem")'>
									<c:forEach var="mandal"  items="${sessionScope.mandalList}" >
										<option value='${mandal.id}'>${mandal.name}</option>
									</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td><%=VILLAGE%><font class="requiredFont"> * </font></td>
									<td style="padding-left: 15px;"><select class="selectWidth" id="pvillageField" name="village" onchange='getHamletList(this.name,this.options[this.selectedIndex].value,"addProblem")'>																
									</select></td>
								</tr>
								<tr>
									<td><%=HAMLET%><font class="requiredFont"> * </font></td>
									<td style="padding-left: 15px;"><select class="selectWidth" id="phamletField" name="hamlet" onchange='validateInput(this.options[this.selectedIndex].value)'>																		
									</select></td>
								</tr>	
								</TABLE>											
							</FIELDSET>
							</DIV>	
				</div>
				<div style="width:500px;">
				<FIELDSET>
				<LEGEND>More Details</LEGEND>
				<Div id="sourceAlert"></Div>								
					<TABLE width="85%">
					<tr>
						<td ><%=reportedDate%></td>
						<td style="padding-left:15px;"><INPUT type="text" readonly="readonly" id="reportedDateField" name="reportedDate" size="20" /> 
						</td>
					</tr>
					<tr>
						<td ><%=existingFrom%><font class="requiredFont">*</font></td>						
						<td style="padding-left:10px;">
							<TABLE>
								<TR>
									<TD><input type="text" id="existingFromText" readonly="readonly" name="existingFromDate" size="20"/>
									<DIV class="yui-skin-sam"><DIV id="existingFromText_Div" style="position:absolute;"></DIV></DIV></TD>
									<TD>
										<A href="javascript:{}" title="Click To Select A Date" onclick="showDateCal()"><IMG src="images/icons/constituencyManagement/calendar.jpeg" border="0"/></A>										
									</TD>
								</TR>
							</TABLE>									
						</td>
					</tr>
					<tr>
						<td><s:label for="problemSourceField" id="problemSourceLabel"  value="%{getText('problemSource')}" /><font class="requiredFont">*</font></td>
						<td style="padding-left:15px;"> 
							<select name="probSource" id="probSource" onchange="getPersonDetails(this.options[this.selectedIndex].text);">
								<c:forEach var="probSource"  items="${sessionScope.problemSources}" >
										<option value='${probSource.id}'>${probSource.name}</option>
									</c:forEach>
							</select>
						</td>
					</tr>													
				</table>
			
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
			</div>
			<div style="margin-left:225px;"><s:submit name="Save" value="Save" cssClass="button"></s:submit></div>
            </s:form>
		    <input type="button" value="Exit" class="button" onclick="refreshParentWindow()"/>
			</div>

</div>
<script type="text/javascript">
getCurrentDate();
</script>
</body>
</html>