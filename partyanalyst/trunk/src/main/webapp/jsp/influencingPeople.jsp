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

<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/carousel/carousel-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">
    <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">
<!-- YUI Dependency files (End) -->
 
	

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
<script type="text/javascript"> 

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
function checkRequestType()
{
	var resultStatus = "${resultStatus}";
	var successMsgDIV = document.getElementById("successMsg");
	var message = '';
	if(resultStatus==""){
		message+='';
	}else if(resultStatus==0){
		message+='<div id="successDIV" style="color:green;">Successfuly Saved</div>';
	}else if(resultStatus==1){
		message+='<div id="successDIV" style="color:green;"><p>Error Raised while saving data please check log for details.</p></div>';
	}
	successMsgDIV.innerHTML = message;
}

function doUnload()
{
	var jsObj=
	{
		task:"removeSessionVariablesForInfluencingPeople"					
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/removeSessionVariablesForInfluencingPeopleAjaxAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);
}

getSelectOptionVOList(this.value,"getStates","COUNTRY");
</script>
<body onload="checkRequestType()" onunload="doUnload()">
<h2 align="center">Influencing People Registration Page</h2>
<div id="registrationMainDiv">

<div id="loginDetailsDiv" class="accessDivMain">
	<div id="loginDetailsDivBody" align="center" class="accessDivBody">
		
	<div id="errorMsgDiv">
		<table class="registrationTable">
			<tr>
				<td colspan="2">
					<div style="color: red;">
						<s:actionerror />
						<s:fielderror />
					</div>
				</td>
			</tr>
		</table>
	</div>		
	
		<s:form action="influencingPeopleSaveAction.action" method="post" theme="simple" name="form">
		
				<table class="registrationTable">			
	<tr>
		<td><font class="requiredFont"> <b style="color:red">*</b></font><s:label class="selectWidth" for="firstNameField" theme="simple" id="fnameLabel"  value="%{getText('firstName')}"/></td>
		<td><s:textfield id="firstNameField" theme="simple" name="firstName"/></td>
	</tr>					
	<tr>
		<td><font class="requiredFont"> <b style="color:red">*</b></font><s:label class="selectWidth" for="lastNameField" theme="simple" id="lastNameLabel" value="%{getText('lastName')}"/></td>
		<td><s:textfield id="lastNameField" theme="simple" name="lastName"/></td>
	</tr>										
	<tr>
		<td style="padding-left:15px;"><s:label class="selectWidth" for="emailField" theme="simple" id="emailLabel" value="%{getText('email')}"/></td>
		<td><s:textfield id="emailField" theme="simple" name="email"/>  </td>
	</tr>										
	<tr>
		<td> <font class="requiredFont">  </font> <s:label class="selectWidth" for="mobileField" id="mobileLabel"  theme="simple" value="%{getText('mobile')}" /></td>
		<td><s:textfield id="mobileField" theme="simple" name="mobile"/>  </td>
	</tr>
	<tr>
		<td><font class="requiredFont"> <b style="color:red">*</b>  </font><s:label class="selectWidth" for="genderField" id="genderLabel" theme="simple" value="%{getText('gender')}" /></td>
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
		<td> <font class="requiredFont"> <b style="color:red">*</b>  </font><%=STATE%></td>
		<td><select id="stateId" name="state" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getDistricts','STATE')" /></td>
	</tr>
	<tr>
		<td> <font class="requiredFont"> <b style="color:red">*</b>  </font><%=DISTRICT%></td>
		<td><select id="districtField" name="district" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getConstituencies','DISTRICT')" /></td>
	</tr>	
	<tr>
		<td> <font class="requiredFont"> <b style="color:red">*</b>  </font><%=CONSTITUENCY%></td>
		<td><select id="constituencyField" name="constituency" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getMandals','CONSTIUENCY')" /></td>
	</tr>
	<tr>
		<td> <font class="requiredFont"> <b style="color:red">*</b>  </font><%=MANDAL%></td>
		<td><select id="mandalField" name="mandal" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getTowhships','TEHSIL')" /></td>
	</tr>
	<tr>
		<td> <font class="requiredFont"> <b style="color:red">*</b>  </font><%=VILLAGE%></td>
		<td><select id="villageField" name="village" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getVillages','REVENUE VILLAGE / TOWN')" /></td>
	</tr>
	<tr>
		<td> <font class="requiredFont"> <b style="color:red">*</b>  </font><s:label for="hamlet" theme="simple" id="hamletLabel"  value="%{getText('hamlet')}" /></td>
		<td><select id="hamletField" name="hamlet" class="selectWidth" onchange="getSelectOptionVOList(this.options[this.selectedIndex].value,'getHamletIdAndRange','HAMLET')"/></td>
	</tr>
	<tr>
		<td width="100px;"> <font class="requiredFont">  </font> <%=Party%> </td>
		<td align="left">
			 <select id="party" name="party" class="selectWidth">
				<c:forEach var="parties" items="${sessionScope.staticParties}">
					<option value='${parties.id}'>${parties.name}</option>
				</c:forEach>
			</select> 
		 </td>
	</tr>
	<tr>
		<td width="100px;"> <font class="requiredFont"> <b style="color:red">*</b>  </font><%=Position%> </td>
		<td align="left"> 
			<select id="position" name="position" class="selectWidth">
				<c:forEach var="positions" items="${sessionScope.positionsList}">
					<option value='${positions.id}'>${positions.name}</option>
				</c:forEach>
			</select> 
		</td>
		<td><div id="specifyPosition"></div></td>
	</tr>					
	<tr>
		<td width="100px;"> <font class="requiredFont"> <b style="color:red">*</b>  </font><%=InfluenceRange%> </td>
		<td align="left"> 
			<select id="effectedRange" name="influenceRange" class="selectWidth" onchange="influenceRangeFunction(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">
				<option value="">Select Range</option>
				<c:forEach var="effectedRange" items="${sessionScope.influenceRange}">
					<option value='${effectedRange.id}'>${effectedRange.name}</option>
				</c:forEach>
			</select> 
		</td>
		<td><input type="hidden" id="influenceRangeInputId" name="influencingRange"></td>
		<td><div id="specifyInfluenceRange"></div></td>
	</tr>									
</table>
				<div id="saveDiv" align="center">
					<s:submit cssClass="button" value="Save" name="Save"></s:submit>
				</div>
			</s:form>

			<div id="successMsg"></div>
		</div>	
	</div>		 
</div>		
</body>
</html>