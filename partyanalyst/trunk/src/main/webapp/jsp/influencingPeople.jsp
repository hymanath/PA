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
var influenceRangeId,occupationId,positionId,partyId,errorMessage,flag = 0,posSize,newPos;
var fName,lName,eMail,mobile,genderType,castType,hamletName,partyName,influenceType,positionType,occupationType,influenceRangeType;
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
						showStatesInSelectOption(myResults)
					}
					if(jsObj.task == "getDistricts")
					{
						showDistrictsInSelectOption(myResults)
					}	
					if(jsObj.task == "getConstituencies")
					{
						showConstituenciesInSelectOption(myResults)
					}		
					if(jsObj.task == "getMandals")
					{
						showMandalsInSelectOption(myResults)
					}		
					if(jsObj.task == "getTowhships")
					{
						showTowhshipsInSelectOption(myResults)
					}
					if(jsObj.task == "getVillages")
					{
						showVillagesInSelectOption(myResults);
					}	
					if(jsObj.task == "saveDetails")
					{
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

function getStateList(id)
{	
	var jsObj=
		{
				locationId:id,
				task:"getStates"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function removeSelectElements(elmt)
{
	if(!elmt)
		return;

	var len=elmt.length;			
	for(i=len-1;i>=0;i--)
	{
		elmt.remove(i);
	}	
}

function getDistrictsList(id)
{	
	var jsObj=
		{
				locationId:id,
				task:"getDistricts"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}
function getConstituencyList(id)
{
	var jsObj=
		{
				locationId:id,
				task:"getConstituencies"					
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function getMandalList(id)
{	
	var jsObj=
		{
				locationId:id,
				task:"getMandals"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function getTownshipsForMandal(id)
{
	var jsObj=
		{
				locationId:id,
				task:"getTowhships"					
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function getVillagesForMandal(id)
{
	var jsObj=
		{
				locationId:id,
				task:"getVillages"					
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function getHamletForTownship(id)
{
	var jsObj=
		{
				locationId:id,
				task:"getHamlets"					
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);
} 	


function showStatesInSelectOption(results) 
{
	var selectedElmt = document.getElementById("stateId");
	removeSelectElements(selectedElmt);
	for(var i in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
	try
	{
		selectedElmt.add(opElmt,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt); // IE only
	}			
	}
	var constituency = document.getElementById("constituencyField");
	var mandal = document.getElementById("mandalField");
	var village = document.getElementById("villageField");
	var hamlet = document.getElementById("hamletField");
	constituency.selectedIndex = '0';
	mandal.selectedIndex = '0';
	village.selectedIndex = '0';
	hamlet.selectedIndex = '0';
}


function showDistrictsInSelectOption(results) 
{
	var selectedElmt = document.getElementById("districtField");	
	removeSelectElements(selectedElmt);
	for(var i in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
	try
	{
		selectedElmt.add(opElmt,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt); // IE only
	}			
	}
	
	var mandal = document.getElementById("mandalField");
	var village = document.getElementById("villageField");
	var hamlet = document.getElementById("hamletField");
	
	mandal.selectedIndex = '0';
	village.selectedIndex = '0';
	hamlet.selectedIndex = '0';
}
function showConstituenciesInSelectOption(results)
{
	var selectedElmt = document.getElementById("constituencyField");
	removeSelectElements(selectedElmt);	
	for(var i in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
	try
	{
		selectedElmt.add(opElmt,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt); // IE only
	}			
	}

	var village = document.getElementById("villageField");
	var hamlet = document.getElementById("hamletField");
	
	village.selectedIndex = '0';
	hamlet.selectedIndex = '0';
}


function showMandalsInSelectOption(results)
{
	var selectedElmt = document.getElementById("mandalField");
	removeSelectElements(selectedElmt);	
	for(var i in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
	try
	{
		selectedElmt.add(opElmt,null); // standards compliant
	}
	catch(ex)
	{
		selectedElmt.add(opElmt); // IE only
	}			
	}

	var hamlet = document.getElementById("hamletField");
	
	hamlet.selectedIndex = '0';
}
function showTowhshipsInSelectOption(results)
{
	var selectedElmt = document.getElementById("villageField");
	removeSelectElements(selectedElmt);	
	for(var i in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
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

function showVillagesInSelectOption(results)
{
	var selectedElmt = document.getElementById("hamletField");
	removeSelectElements(selectedElmt);
	for(var i in results)
	{			
		var opElmt=document.createElement('option');
		opElmt.value=results[i].id;
		opElmt.text=results[i].name;
	
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

function occupation(id){
	if(id=="other"){
		var specifyBox = document.getElementById("specifyOccupation");
		var specifyOccupation='';
		specifyOccupation+='If Other Occupation Please Specify  &nbsp; &nbsp;';
		specifyOccupation+='&nbsp; <s:textfield id="specifyOccupationField"  theme="simple" name="specifyOccupationName"/> ';
		specifyBox.innerHTML = specifyOccupation;		
	}else{
		var specifyBox = document.getElementById("specifyOccupation");
		var specifyOccupation='';
		specifyBox.innerHTML = specifyOccupation;	
	}
	occupationId = id;
}

function position(ids,id){
	if(id=="other"){
		var specifyBox = document.getElementById("specifyPosition");
		var specifyPosition='';
		specifyPosition+='If Other Position Please Specify &nbsp; &nbsp; &nbsp;';
		specifyPosition+=' &nbsp; &nbsp; <s:textfield id="specifyPositionField"  theme="simple" name="specifyPositionName"/> ';
		specifyBox.innerHTML = specifyPosition;		
	}else{
		var specifyBox = document.getElementById("specifyPosition");
		var specifyPosition='';
		specifyBox.innerHTML = specifyPosition;	
	}
	positionId = ids;
	
}

function influenceRange(ids,id){
	if(id=="Other"){
		var specifyBox = document.getElementById("specifyInfluenceRange");
		var specifyInfluenceRange='';
		specifyInfluenceRange+='If Other Range Please Specify  &nbsp; &nbsp; &nbsp;';
		specifyInfluenceRange+=' &nbsp; &nbsp; <s:textfield id="specifyInfluenceRangeField"  theme="simple" name="specifyInfluenceRangeName"/> ';
		specifyBox.innerHTML = specifyInfluenceRange;		
	}else{
		var specifyBox = document.getElementById("specifyInfluenceRange");
		var specifyInfluenceRange='';
		specifyBox.innerHTML = specifyInfluenceRange;	
	}
	influenceRangeId = id; 
}

function party(id){
	partyId = id;
}

function saveInfluencePeopleRegistrationData(){
	var firstName = document.getElementById("firstNameField");
	var lastName = document.getElementById("lastNameField");
	var email = document.getElementById("emailField");
	var mobile = document.getElementById("mobileField");
	var male = document.getElementById("male");
	var cast =   document.getElementById("castField");
	var hamletId =   document.getElementById("hamletField");
	var occupationId =   document.getElementById("occupationField");
	var newPosition = document.getElementById("specifyPositionField");
	if(newPosition!=null){
		newPos = newPosition.value;
	}
	var newRange = document.getElementById("specifyInfluenceRangeField");
	if(newRange!=null){
		influenceRangeType = newRange.value;
		influenceType = 0;
	}
	
	if(partyId==null){
		partyId = 5;
	}
	fName = firstName.value;
	lName = lastName.value;
	eMail = email.value;
	mobile = mobile.value;
	castType = cast.value;
	hamletName = hamletId.value;
	partyName = partyId;
	occupId = occupationId.value;
	
	if(male.checked==true){
		genderType = "male";	
	}else{
		genderType =  "female";	
	}	
	var specifyInfluenceRange = document.getElementById("specifyInfluenceRangeField");
	if(specifyInfluenceRange!=null){
		influenceRangeType = specifyInfluenceRange.value;
		influenceType = 0;
	}else{
		influenceRangeType = influenceRangeId;
		influenceType = 0;
	}
	var specifyPosition = document.getElementById("specifyPositionField");
	if(specifyPosition!=null){
		positionType = specifyPosition.value;
	}else{
		positionType = positionId;
	}
		
	if(fName==""){
		errorMessage ="Please Enter First Name";
		flag = 1;
	}else if(lName==""){
		errorMessage ="Please Enter Last Name";
		flag = 1;
	}else if(genderType==""){
		errorMessage ="Please Select a Gender";
		flag = 1;
	}else if(hamletName==""){
		errorMessage ="Please Select a Hamlet";
		flag = 1;
	}else if(positionType==null){
		errorMessage ="Please Select a Position";
		flag = 1;
	}else if(influenceRangeType==null){
		errorMessage ="Please Select Range";
		flag = 1;
	}else{
		flag = 0;
	}
	var errorMsg = document.getElementById("errorMessageDisplay");
	var msg='';
	if(flag==1){
		msg+=errorMessage;
	}else{
		msg+='';
	}	
	errorMsg.innerHTML = msg;

	if(flag==0){
		var jsObj=
		{
				firstName:fName,
				lastName:lName,
				email:eMail,
				mobileNumber:mobile,
				cast:castType,
				hamletId:hamletName,
				partyId:partyName,
				gender:genderType,
				range:influenceRangeType,
				position:positionType,
				occupation:occupId,
				task:"saveDetails"					
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/influencePeopleAjaxAction.action?"+rparam;						
		callAjax(rparam,jsObj,url);	
	}
	positionType = "";
	newPos = "";
}
posSize = ${positionSize};
getStateList(this.value);
--></script>
<body>
<h2>Influencing People Registration Page</h2>
<div id="registrationMainDiv">
<div id="loginDetailsDiv" class="accessDivMain">
		<div id="loginDetailsDivHead" class="accessDivHead"><u>Influencing People Details...</u></div>
		<div id="loginDetailsDivBody" class="accessDivBody">
		<div id="errorMessageDisplay" style="color:red,font-size:12px"></div>
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
						<td><s:textfield class="selectWidth" id="occupationField" theme="simple" name="cast"/>  </td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font> <%=Cast%></td>
						<td><s:textfield class="selectWidth" id="castField" theme="simple" name="cast"/>  </td>
					</tr>		
					<tr>
						<td> <font class="requiredFont">  </font><%=STATE%></td>
						<td><select id="stateId" class="selectWidth" list="result" theme="simple" listKey="id" listValue="name" onchange="getDistrictsList(this.value)"/></select></td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font><%=DISTRICT%></td>
						<td><select id="districtField" class="selectWidth" name="district"  onchange="getConstituencyList(this.options[this.selectedIndex].value,false)"/></select></td>
					</tr>	
					<tr>
						<td> <font class="requiredFont">  </font><%=CONSTITUENCY%></td>
						<td><select id="constituencyField" class="selectWidth" name="constituency"  onchange="getMandalList(this.options[this.selectedIndex].value,false)"/></select></td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font><%=MANDAL%></td>
						<td><select id="mandalField" class="selectWidth" name="mandal" onchange="getTownshipsForMandal(this.options[this.selectedIndex].value,false)"/></select></td>
					</tr>
					<tr>
						<td> <font class="requiredFont">  </font><%=VILLAGE%></td>
						<td><select class="selectWidth" id="villageField" name="village" onchange="getVillagesForMandal(this.options[this.selectedIndex].value,false)"/></select></td>
					</tr>
					<tr>
						<td> <font class="requiredFont"> * </font><s:label for="hamlet" theme="simple" id="hamletLabel"  value="%{getText('hamlet')}" /></td>
						<td><select class="selectWidth" theme="simple" id="hamletField" name="hamlet"/></select></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont">  </font> <%=Party%> </td>
						<td align="left"> <s:select name="partiesName" cssClass="selectWidth" id="party" theme="simple" list="staticParties" listKey="id" listValue="name" onchange="party(this.options[this.selectedIndex].value,false)"></s:select></td>
					</tr>
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font><%=Position%> </td>
						<td align="left"> <s:select name="posi" id="position" cssClass="selectWidth" theme="simple" list="positionsList" listKey="id" listValue="name" onchange="position(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)"></s:select> </td>
						<td><div id="specifyPosition"></div></td>
					</tr>					
					<tr>
						<td width="100px;"> <font class="requiredFont"> * </font><%=InfluenceRange%> </td>
						<td align="left"> <s:select name="infRange" id="influRange" cssClass="selectWidth" theme="simple" list="influenceRange" listKey="id" listValue="name" onchange="influenceRange(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)"></s:select> </td>
						<td><div id="specifyInfluenceRange"></div></td>
					</tr>										
				</table>
			</div>	
		 </div>
	
		<div style="text-align: center;">
			<s:submit name="Save" onclick="saveInfluencePeopleRegistrationData()"></s:submit> 
		</div>			
</div>		
</body>
</html>