<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Influencing People Registration</title>
<!-- Dependencies --> 
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<!-- OPTIONAL: JSON (enables JSON validation) --> 
	<script type="text/javascript" src="js/json/json-min.js"></script> 
  	<!-- Dependencies --> 
   	<script type="text/javascript" src="js/yahoo/yahoo-min.js" ></script>

	<link href="../styles/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<style type="text/css">
	.selectWidth
		{
			width:140px;
		}
	#errorMessageDisplay{
		color:red;
		font-size:14px;
		font-weight:bold;
	}
</style>
</head>
<script type="text/javascript"><!--  

var Localization = { <%
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String STATE = rb.getString("STATE");
			String DISTRICT = rb.getString("DISTRICT");
			String CONSTITUENCY = rb.getString("CONSTITUENCY");
			String MANDAL  = rb.getString("MANDAL");
			String VILLAGE = rb.getString("VILLAGE");
			String HAMLET   = rb.getString("HAMLET");
			String InfluenceRange  = rb.getString("influenceRange");
			String Position = rb.getString("position");
			String Party = rb.getString("party");
			String Cast = rb.getString("cast");
			String Occupation = rb.getString("occupation");
			String Male  = rb.getString("Male");
			String Female = rb.getString("Female");
  %> }


function callAjax(param,jsObj,url){
	var myResults;	
	var callback = {			
	    success : function( o ) {
			try {	
					if(o.responseText.length!=0){
						myResults = YAHOO.lang.JSON.parse(o.responseText);	
					}						
					if(jsObj.task == "getStates")
					{
						clearOptionsListForSelectElmtId("stateId");
						createOptionsForSelectElmtId("stateId",myResults);
					}
					if(jsObj.task == "getDistricts")
					{
						clearOptionsListForSelectElmtId("districtField");
						createOptionsForSelectElmtId("districtField",myResults);	
					}	
					if(jsObj.task == "getConstituencies")
					{
						clearOptionsListForSelectElmtId("constituencyField");
						createOptionsForSelectElmtId("constituencyField",myResults);
					}		
					if(jsObj.task == "getMandals")
					{
						clearOptionsListForSelectElmtId("mandalField");
						createOptionsForSelectElmtId("mandalField",myResults);
					}		
					if(jsObj.task == "getTowhships")
					{
						clearOptionsListForSelectElmtId("villageField");
						createOptionsForSelectElmtId("villageField",myResults);
					}
					if(jsObj.task == "getVillages")
					{
						clearOptionsListForSelectElmtId("hamletField");
						createOptionsForSelectElmtId("hamletField",myResults);
					}				
			}catch (e) {   		
			   	alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}

function getSelectOptionVOList(id, task, influenceRange)
{	
	if(id == 0)
		return;
	
	var jsObj=
		{
				locationId:id,
				task:task						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function influenceRangeFunction(id,text){
		var specifyBox = document.getElementById("influenceRangeInputId");
		specifyBox.value = text;
}

getSelectOptionVOList(this.value,"getStates","COUNTRY");
--></script>
<body>
<h2>Influencing People Registration Page</h2>
<div id="registrationMainDiv">
		<table>
			<tr>
				<td colspan="2"><s:actionerror /></td>
			</tr>
		</table>
<div id="loginDetailsDiv" class="accessDivMain">
		<div id="loginDetailsDivBody" align="center" class="accessDivBody">
		<div id="errorMessageDisplay" style="color:red,font-size:12px"></div>
		<form action="influencingPeopleSaveAction.action" method="GET" theme="simple">
				<table class="registrationTable">			
					<tr>
						<td><font class="requiredFont"> * </font><s:label class="selectWidth" for="firstNameField" theme="simple" id="fnameLabel"  value="%{getText('firstName')}" /></td>
						<td><s:textfield id="firstNameField"  theme="simple" name="firstName"/>  </td>
					</tr>					
					<tr>
						<td> <font class="requiredFont"> * </font> <s:label class="selectWidth" for="lastNameField" id="lastNameLabel"  theme="simple" value="%{getText('lastName')}" /></td>
						<td align="left"><s:textfield id="lastNameField" theme="simple" name="lastName"/>  </td>
					</tr>					
					<tr>
						<td style="padding-left:15px;"><s:label class="selectWidth" for="emailField" id="emailLabel"  theme="simple" value="%{getText('email')}" /></td>
						<td><s:textfield id="emailField" theme="simple" name="email"/>  </td>
					</tr>										
					<tr>
						<td> <font class="requiredFont">  </font> <s:label class="selectWidth" for="mobileField" id="mobileLabel"  theme="simple" value="%{getText('mobile')}" /></td>
						<td><s:textfield id="mobileField" theme="simple" name="mobile"/>  </td>
					</tr>
					<tr>
						<td><font class="requiredFont"> * </font><s:label class="selectWidth" for="genderField" id="genderLabel" theme="simple" value="%{getText('gender')}" /></td>
						<td align="left">
							<input id="male" type="radio" name="gender" value="M" checked="checked"/><%=Male%>
							<input id="feMale" type="radio" name="gender" value="F"/><%=Female%>
						</td>		
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font> <%=Occupation%></td>
						<td><s:textfield class="selectWidth" id="occupationField" theme="simple" name="occupation"/>  </td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font> <%=Cast%></td>
						<td><s:textfield class="selectWidth" id="castField" theme="simple" name="cast"/>  </td>
					</tr>		
					<tr>
						<td> <font class="requiredFont">  </font><%=STATE%></td>
						<td><select id="stateId" name="state" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getDistricts','STATE')" /></td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font><%=DISTRICT%></td>
						<td><select id="districtField" name="district" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getConstituencies','DISTRICT')" /></td>
					</tr>	
					<tr>
						<td> <font class="requiredFont">  </font><%=CONSTITUENCY%></td>
						<td><select id="constituencyField" name="constituency" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getMandals','CONSTIUENCY')" /></td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font><%=MANDAL%></td>
						<td><select id="mandalField" name="mandal" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getTowhships','TEHSIL')" /></td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font><%=VILLAGE%></td>
						<td><select id="villageField" name="village" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getVillages','REVENUE VILLAGE / TOWN')" /></td>
					</tr>
					<tr>
						<td> <font class="requiredFont"> * </font><s:label for="hamlet" theme="simple" id="hamletLabel"  value="%{getText('hamlet')}" /></td>
						<td><select id="hamletField" name="hamlet" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getHamletIdAndRange','HAMLET')"/></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont">  </font> <%=Party%> </td>
						<td align="left"> <s:select name="party" cssClass="selectWidth" id="party" theme="simple" list="staticParties" listKey="id" listValue="name"></s:select></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font><%=Position%> </td>
						<td align="left"> <s:select name="position" id="position" cssClass="selectWidth" theme="simple" list="positionsList" listKey="id" listValue="name"></s:select> </td>
						<td><div id="specifyPosition"></div></td>
					</tr>					
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font><%=InfluenceRange%> </td>
						<td align="left"> <s:select name="influenceRange" id="influRange" cssClass="selectWidth" theme="simple" list="influenceRange" listKey="id" listValue="name" onchange="influenceRangeFunction(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)"></s:select> </td>
						<td><input type="hidden" id="influenceRangeInputId" name="influencingRange"></td>
						<td><div id="specifyInfluenceRange"></div></td>
					</tr>	
					<tr><td><input type="submit" value="Save"/></td></tr>									
				</table>
			</form>
			</div>	
		 </div>
</div>		
</body>
</html>