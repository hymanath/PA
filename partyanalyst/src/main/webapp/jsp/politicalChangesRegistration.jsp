<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
    <%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Political Changes Registration</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	<link rel="stylesheet" href="/resources/demos/style.css" />
	<!-- YUI Dependency files (Start) -->

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 
	<script src="js/yahoo/yui-js-2.8/build/connection/connection_core-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	
	<!-- YUI Skin Sam -->
	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	 <link rel="stylesheet" type="text/css" href="styles/constituencyManagement/constituencyManagement.css">
	<!-- YUI Dependency files (End) -->
	<script type="text/javascript" src="js/constituencyManagement/constituencyManagement.js"></script>
	<script type="text/javascript" src="js/constituencyManagement/cadreManagement.js"></script>
	<script type="text/javascript" src="js/problemManagementReport/problemManagementReport.js"></script>
	<script type="text/javascript" src="js/influencingPeople/influencingPeople.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<script type="text/javascript" src="js/calendar Component/calendarComponent.js"></script>
	<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
	
	
<style type="text/css">
	.selectBoxWidth
	{
		width:180px;
	}
	.headingStyle
	{
		color:#247CD4;
		font-size:19px;
		font-weight:bold;
		padding:10px;
		text-align:center;
	}
	.yui-skin-sam .yui-calcontainer {
		background-color:#F2F2F2;
		border:1px solid #808080;
		font-size:10px;
		padding:10px;
		width:150px;
	}
	.button 
	{
		background-attachment:scroll;
		background-color:#335291;
		background-image:none;
		background-position:0 0;
		background-repeat:repeat;
		color:#FFFFFF;
		width:50px;
	}
	.requiredFont
	{
		color:red;
		margin-left:5px;
	}
</style>
	
<script type="text/javascript">
<%			
	ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
	String STATE = rb.getString("STATE");
	String DISTRICT = rb.getString("DISTRICT");
	String CONSTITUENCY = rb.getString("CONSTITUENCY");
	String MANDAL  = rb.getString("TEHSIL");
	String VILLAGE = rb.getString("VILLAGE");
	String HAMLET   = rb.getString("HAMLET");
	String title   = rb.getString("title");
	String description   = rb.getString("description");
	String moreDetails   = rb.getString("moreDetails");
	String occuredDate   = rb.getString("occuredDate");
	String selectRange   = rb.getString("selectRange");
	String selectSource   = rb.getString("selectSource");
	String reportedDate   = rb.getString("reportedDate");
	String name  = rb.getString("name");
	String mobile = rb.getString("mobile");
	String telephoneNo = rb.getString("telephoneNo");
	String email = rb.getString("email");
	String address = rb.getString("address");  
	String selectParty = rb.getString("selectParty");	
	String mandatoryFields = rb.getString("mandatoryFields");
	String MUNICIPAL = rb.getString("MUNICIPAL");
	String WARD = rb.getString("WARD");
	String BOOTH = rb.getString("BOOTH");
 %> 
 var localizationObj = {
			STATE : '<%=STATE%>',
			DISTRICT : '<%=DISTRICT%>',
			CONSTITUENCY : '<%=CONSTITUENCY%>',
			MANDAL  : '<%=MANDAL%>',
			VILLAGE : '<%=VILLAGE%>',
			MUNICIPAL : '<%=MUNICIPAL%>',
			WARD : '<%=WARD%>',
			BOOTH : '<%=BOOTH%>',
			title	:	'<%=title%>',
			description	:	'<%=description%>',
			moreDetails	:	'<%=moreDetails%>',
			occuredDate	:	'<%=occuredDate%>',
			reportedDate:	'<%=reportedDate%>',
			selectRange	:	'<%=selectRange%>',
			selectSource:	'<%=selectSource%>'							
		  };

//Variable Declaration Start here

	var identifyDate = 1;
	var reportDate = 2;
	
		
	var range='',rangeId=0;
	var localPoliticalChangeId="${localPoliticalChangeId}";
	var requestType = "${type}";
	
	
	
// Variable Declaration Ends here
	//Ajax Calls Starts from here..

	function getSelectOptionVOListForPoliticalChanges(id,task,name)
	{
		rangeId = id;
		if(id == 0)
		{	
			alert("Select Valid Location");
			return;
		}
		if(name == "township")
		{
			if(level == 9)
			{
				rangeId = id;
				getBooths();
			}
			else
			{
				getRespectiveDetails(id,task);
			}
		}
		else if(name == "constituency")
		{
			if(level == 7 || level == 8)
			{
				getConstituencyes();
			}
			else
			{
				getRespectiveDetails(id,task);
			}
		}
		else if(name == "mandal")
		{
			if(level == 7 || level == 8)
			{
				rangeId = id.substr(1);
				getMuncipaliteys();
			}
			else
			{
				getRespectiveDetails(id,task);
			}
		}
		else if(name == "wards")
		{
			rangeId = id.substr(1);
			getWards();
		}
		else if(name == "booths")
		{
			rangeId = id.substr(1);
		}
		
		else
		{
			getRespectiveDetails(id,task);
		}
		var rangeIdDIv= document.getElementById("rangeId");
		rangeIdDIv.value = rangeId;
	}

	function getRespectiveDetails(id, task)
	{
		var jsObj=
			{
					locationId:id,
					task:task						
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/problemManagementReportAjaxAction.action?"+rparam;	
			callAjax(rparam,jsObj,url);
	}
	function checkRequestType()
	{
		document.getElementById("titleTextField").focus();
		var effectedRangeEl = document.getElementById("effectedRange");
		var selectedeffectedRange =effectedRangeEl.options[effectedRangeEl.selectedIndex].value;  
		
		if(requestType=="new" && selectedeffectedRange != '0')
			populateLocations(selectedeffectedRange,'onLoad');	
		
		var externalPersonsDiv = document.getElementById("otherSourcesInformationDiv");
		var userTypeSelectBoxEl = document.getElementById("userTypeSelectBox");
		var userTypeSelectBoxElVal = userTypeSelectBoxEl.options[userTypeSelectBoxEl.selectedIndex].value; 
		if(userTypeSelectBoxElVal == '2' || userTypeSelectBoxElVal == '3' )
			externalPersonsDiv.style.display = 'block';
				
	}
	
	//Ajax Calls ends here...
	function callAjax(param,jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) 
						  {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
															 
									if(jsObj.task == "deltePoliticalChange"){
										getAllPoliticalChangesForTheUser();
									}														
									
									else if(jsObj.task == "getStates")
									{
										clearOptionsListForSelectElmtId("stateId");
										createOptionsForSelectElmtId("stateId",myResults);
									}
									else if(jsObj.task == "getDistricts")
									{
										clearOptionsListForSelectElmtId("districtField");
										createOptionsForSelectElmtId("districtField",myResults);	
									}	
									else if(jsObj.task == "getConstituencies")
									{
										clearOptionsListForSelectElmtId("constituencyField");
										createOptionsForSelectElmtId("constituencyField",myResults);
										
									}		
									else if(jsObj.task == "getMandals")
									{
										clearOptionsListForSelectElmtId("mandalField");
										createOptionsForSelectElmtId("mandalField",myResults);
									}		
									else if(jsObj.task == "getTowhships")
									{
										clearOptionsListForSelectElmtId("villageField");
										createOptionsForSelectElmtId("villageField",myResults);
									}
									else if(jsObj.task == "getVillages")
									{
										clearOptionsListForSelectElmtId("hamletField");
										createOptionsForSelectElmtId("hamletField",myResults);
									}
									else if(jsObj.task == "subRegionsInConstituency")
									{
										clearOptionsListForSelectElmtId("muncipalField");
										createOptionsForSelectElmtId("muncipalField",myResults);
									}
									else if(jsObj.task == "hamletsOrWardsInRegion")
									{
										clearOptionsListForSelectElmtId("wardField");
										createOptionsForSelectElmtId("wardField",myResults);
									}
									else if(jsObj.task == "boothsInTehsilOrMunicipality")
									{
										clearOptionsListForSelectElmtId("boothField");
										createOptionsForSelectElmtId("boothField",myResults);
									}
									else if (jsObj.task == "getConstNotInGivenAreaType")
									{
										clearOptionsListForSelectElmtId("constituencyField");
										createOptionsForSelectElmtId("constituencyField",myResults);
									}
																
								}     
							catch (e)
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

function validateFunction()
{
	
	var title = $('#titleTextField').val();
	var partyNameId = $('#selectedPartyBox :selected').val();
	var scopeId = $('#effectedRange :selected').val();
	var infoScopeId = $('#userTypeSelectBox :selected').val();
	/* var fromDate = $('#identifiedFromText').val();
	var todate = $('#datetext').val();
	 var dt1  = parseInt(fromDate.substring(0,2),10);
      var mon1 = parseInt(fromDate.substring(3,5),10);
      var yr1  = parseInt(fromDate.substring(6,10),10);
      var dt2  = parseInt(todate.substring(0,2),10);
      var mon2 = parseInt(todate.substring(3,5),10);
      var yr2  = parseInt(todate.substring(6,10),10);
      var date1 = new Date(yr1, mon1, dt1);
      var date2 = new Date(yr2, mon2, dt2);
	
	 if(date2 < date1)
	{ 
		$('#errMsgDiv').append("Please Enter a valid Date");
		return false;
	} */
	if(scopeId == 0)
	{
		$('#errMsgDiv').append("Please Enter Effective Scope");
		return false;
	}
	if($.trim(title).length == 0)
	{
		$('#errMsgDiv').append("Please Enter Title");
		return false;
	}
	if(partyNameId == 0)
	{
		$('#errMsgDiv').append("Please Enter Party Name");
		return false;
	}
	
	if(infoScopeId == 0)
	{
		$('#errMsgDiv').append("Please Enter Information Source");
		return false;
	}
	
}
var level = "";
function populateLocations(val,source)
{	
	/* var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");
	var hiddenEl = document.getElementById("effectRangeVal");
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	row6El.style.display = 'none';
	var value = val;
	if(val == 'null')
		value = hiddenEl.value;
	if( (value>2) || (value==2) ){
		if(row1El.style.display == 'none')
		{	
			row1El.style.display = '';
		}		
		range = localizationObj.STATE;
	}
	if( (value>3) || (value==3) ){
		if(row2El.style.display == 'none')
		{	
			row2El.style.display = '';
		}
		range = localizationObj.DISTRICT;
	}
	if( (value>4) || (value==4) ){
		if(row3El.style.display == 'none')
		{	
			row3El.style.display = '';
		}		
		range = localizationObj.CONSTITUENCY;
	}
	if( (value>5) || (value==5) ){
		if(row4El.style.display == 'none')
		{	
			row4El.style.display = '';
		}		
		range = localizationObj.MANDAL;
	}
	if( (value>6) || (value==6) ){
		if(row5El.style.display == 'none')
		{	
			row5El.style.display = '';
		}
		range = localizationObj.VILLAGE;
	}
	if( (value>7) || (value==7) ){
		if(row6El.style.display == 'none')
		{	
			row6El.style.display = '';
		}
		range = localizationObj.HAMLET;
		rangeId = value;
	} */
	$('#row1').hide();
	$('#row2').hide();
	$('#row3').hide();
	$('#row4').hide();
	$('#row5').hide();
	$('#row6').hide();
	$('#row7').hide();
	$('#row8').hide();
	level = $('#effectedRange option:selected').val();
	if(val == 'null')
	{
		val = $('#effectedRange option:selected').val();
	}
	if(val == 2)
	{
		$('#row1').show();
		range = localizationObj.STATE;
	}
	else if(val == 3)
	{
		$('#row1').show();
		$('#row2').show();
		range = localizationObj.DISTRICT;
	}
	else if(val == 4)
	{
		$('#row1').show();
		$('#row2').show();
		$('#row3').show();
		range = localizationObj.CONSTITUENCY;
	}
	else if(val == 5)
	{
		$('#row1').show();
		$('#row2').show();
		$('#row3').show();	
		$('#row4').show();
		range = localizationObj.MANDAL;		
	}
	else if(val == 6)
	{
		$('#row1').show();
		$('#row2').show();
		$('#row3').show();	
		$('#row4').show();	
		$('#row5').show();
		range = localizationObj.VILLAGE;
	}
	else if(val == 7)
	{
		$('#row1').show();
		$('#row2').show();
		$('#row3').show();
		$('#row6').show();
		range = localizationObj.MUNICIPAL;
	}
	else if(val == 8)
	{
		$('#row1').show();
		$('#row2').show();
		$('#row3').show();
		$('#row6').show();
		$('#row7').show();
		range = localizationObj.WARD;
	}
	else if(val == 9)
	{
		$('#row1').show();
		$('#row2').show();
		$('#row3').show();	
		$('#row4').show();
		$('#row8').show();
		range = localizationObj.BOOTH;
	}
	var rangeDIv= document.getElementById("range");
	rangeDIv.value = range;
	if(source == 'checkBox')
		getSelectOptionVOListForPoliticalChanges(1,"getStates","state");	
	 
}


	function getOtherPersonDetails(name)
	{	
		var externalPersonsDiv = document.getElementById("otherSourcesInformationDiv");  
		var nameDiv = document.getElementById("externalPersonName");
		var mobileDiv = document.getElementById("externalPersonMobile");
		var addressDiv = document.getElementById("externalPersonAddress");
		
		if(name=='External Person' || name=='Call Center')
		{			
			otherDetailsForPoliticalChanges = 1;			
			externalPersonsDiv.style.display = 'block';
			nameDiv.value='';
			mobileDiv.value='';
			addressDiv.value='';			
		}else
		{	
			otherDetailsForPoliticalChanges = 0;			
			externalPersonsDiv.style.display = 'none';
			nameDiv.value='name';
			mobileDiv.value='9000000000';
			addressDiv.value='address';
		}
	}
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

	function doUnload()
	{
		var jsObj=
		{
			task:"removeSessionVariablesForPoliticalChangesRegistration"					
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/removeSessionVariablesForPoliticalChangesRegistrationAjaxAction.action?"+rparam;	
		callAjax(rparam,jsObj,url);
		incrementHidden();
		//window.opener.document.location.reload(true);
		window.close();
	}

function addDate(){
date = new Date();
var month = date.getMonth()+1;
var day = date.getDate();
var year = date.getFullYear();

if (document.getElementById('datetext').value == ''){
document.getElementById('datetext').value = day + '/' + month + '/' + year;
}
}

$(document).ready(function() {
  var name = $("#userTypeSelectBox option:selected").text();
  getOtherPersonDetails(name);
  
  $('#identifiedFromText').datepicker({ 
	dateFormat: 'dd/m/yy'
	});
	var successMsg  = '${successMsg}';
	if(successMsg.trim().length > 0)
	{
		window.opener.getAllPoliticalChangesForTheUser();
	}
});

function getMuncipaliteys()
{
	var selectedId = $('#constituencyField option:selected').val();
	var jsObj=
	{				
		id: selectedId,
		task: "subRegionsInConstituency",
		taskType:"cadreReg",
		selectElementId: "mandalField_s" ,
		address: "cadreLevel",
		areaType: "URBAN",
		constId: "null",
		isParliament : "null"
		
	}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

function getWards()
{
	var selectedId = $('#muncipalField option:selected').val();
	var jsObj=
		{				
			id: selectedId,
			task: "hamletsOrWardsInRegion",
			taskType:"cadreReg",
			selectElementId: "hamletField_s" ,
			address: "cadreLevel",
			areaType: "null",
			constId: "null",
			isParliament : "null"
			
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

function getBooths()
{
	var selectedId = '2' + $('#mandalField option:selected').val();
	var constituencyId = $('#constituencyField option:selected').val();
	var jsObj=
		{				
			id: selectedId,
			task: "boothsInTehsilOrMunicipality",
			taskType:"cadreReg",
			selectElementId: "boothField_s" ,
			address: "cadreLevel",
			areaType: "null",
			constId: constituencyId,
			isParliament : "null"
			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

function getConstituencyes()
{
	var selectedId = $('#districtField option:selected').val();
	var jsObj=
		{				
			id: selectedId,
			task: "getConstNotInGivenAreaType",
			taskType:"cadreReg",
			selectElementId: "constituencyField_s" ,
			address: "cadreLevel",
			areaType: "RURAL",
			constId: "null",
			isParliament : "null"
			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/locationsHierarchiesAjaxAction.action?"+rparam;
	callAjax(rparam,jsObj,url);
}

	</script>
</head>

<body  onload="addDate();" style="background-color:#f2f2f2;">	
<c:if test="${type == 'new'}">
<DIV id="headingDiv" class="headingStyle" align="center">New Political Change</div>
</c:if>
<c:if test="${type == 'Edit'}">
<DIV id="headingDiv" class="headingStyle" align="center">Edit Political Change</div>
</c:if>
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
<c:if  test="${resultStatus == '0'}">
	<div id="successMsg" style="color:green;">Political Change registered successfully!</div>
</c:if>	
<c:if  test="${resultStatus == '1'}">
	<div id="successMsg" style="color:red;">Error occurred while saving data.. please check the logs for details</div>
</c:if>
<s:form action="politicalChangesRegistrationAction" method="GET" theme="simple" name="form" onSubmit=" return validateFunction();">	
	
	<div id="mainPoliticalDiv" align="left">
		<fieldset>
			<legend style="font-family:arial,helvetica,clean,sans-serif;">Political Change Info</legend>
			<div id="errMsgDiv" style="color: red;"></div>
			<!--<div>
				<font class="requiredFont"> *  <%=mandatoryFields%></font>
			</div>-->
			<table>
				<tr>
					<td style="width:140px;"><%=title%><font class="requiredFont"> * </font></td>
					<td><s:textfield  name="title" id="titleTextField" maxlength="200" size="50"/></td>
				</tr>	
				<tr>
					<td style="width:140px;"><%=description%></td>
					<td><s:textarea id="descriptionTextBox" name="description" maxlength="500" rows="2" cols="38"/></td>
				</tr>
			</table>
			<table>				
				<tr>
					<td style="width:140px;"><%=occuredDate%><font class="requiredFont"> * </font></td>
					<td>
						<s:textfield  READONLY="READONLY" name ="occuredDate" id="identifiedFromText" style="margin-top:0px;" size="25"/>
						<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div>
					</td>					
					<!--<td valign="top">
						<a href="javascript:{}" title="Click To Select A Date" ><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="politicalChangesCalendarImage" border="0"/></a>
					</td>-->
				</tr>	
				<tr>		
					<td style="width:140px;"><%=reportedDate%></td>
					<td>
						<s:textfield READONLY="READONLY" name ="reportedDate" id="datetext" style="margin-top:0px;" size="25"/>
						<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal "></div></div>
					</td>
				</tr>
			</table>
			<table border="0">	
				<tr>
					<td style="width:140px;">Impacted Party<font class="requiredFont"> * </font></td>
					<td><s:select id="selectedPartyBox" name="party" cssClass="selectBoxWidth" list="#session.mainPartiesList" listKey="id" listValue="name" headerKey="0" headerValue="Select Party"/></td>					
				</tr>					
				<tr>
					<td style="width:140px;">Effective Scope<font class="requiredFont"> * </font></td>
					<td><s:select id="effectedRange" name="effectRange" cssClass="selectBoxWidth" list="#session.impactedRegionsList" listKey="id" listValue="name" headerKey="0" headerValue="Select Effective Scope" class="selectBoxWidth" onchange="populateLocations(this.options[this.selectedIndex].value,'checkBox')"/></td>
				</tr>
				<c:if test="${type == 'Edit'}">
				
				<s:hidden id="effectRangeVal" name="effectRangeVal" value="%{effectRange}" />
			 		<tr>
						<td style="width:140px;">Location</td>
						<td><s:property value="locationName" /></td>
						<td><input type="button" value="Edit" onclick="populateLocations('null','checkBox')"  /></td>
					</tr>
		 		</c:if>				 
		 </table>
		 
		 <div id="locationPopulationDiv"  align="left">
		 <table>
				<tr id="row1" style="display:none;">
					<td style="width:140px;"><%=STATE%><font class="requiredFont"> * </font></td>
					<td><s:select id="stateId" name="state" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select State" cssClass="selectBoxWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getDistricts\',\'district\')" /></td>
				</tr>
				<tr id="row2" style="display:none;">
					<td style="width:140px;"><%=DISTRICT%><font class="requiredFont"> * </font></td>
					<td><s:select id="districtField" name="district" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select District"  cssClass="selectBoxWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getConstituencies\',\'constituency\')" /></td>
				</tr>
				<tr id="row3" style="display:none;">
					<td style="width:140px;"><%=CONSTITUENCY%><font class="requiredFont"> * </font></td>
					<td><s:select id="constituencyField" name="constituency" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select Constituency"  cssClass="selectBoxWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getMandals\',\'mandal\')" /></td>
				</tr>				
				<tr id="row4" style="display:none;">
					<td style="width:140px;"><%=MANDAL%><font class="requiredFont"> * </font></td>
					<td><s:select id="mandalField" name="mandal" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select Mandal"  cssClass="selectBoxWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getTowhships\',\'township\')" /></td>
				</tr>
				<tr id="row5" style="display:none;">
					<td style="width:140px;"><%=VILLAGE%><font class="requiredFont"> * </font></td>
					<td><s:select id="villageField" name="village" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select Village"  cssClass="selectBoxWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,'','')" /></td>
				</tr>
				<tr id="row6" style="display:none;">
					<td style="width:140px;"><%=MUNICIPAL%><font class="requiredFont"> * </font></td>
					<td><s:select id="muncipalField" name="muncipality" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select Muncipality"  cssClass="selectBoxWidth" onChange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,'',\'wards\')"/></td>
				</tr>
				<tr id="row7" style="display:none;">
					<td style="width:140px;"><%=WARD%><font class="requiredFont"> * </font></td>
					<td><s:select id="wardField" name="ward" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select Ward"  cssClass="selectBoxWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,'',\'booths\')"/></td>
				</tr>
				<tr id="row8" style="display:none;">
					<td style="width:140px;"><%=BOOTH%><font class="requiredFont"> * </font></td>
					<td><s:select id="boothField" name="booth" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select booth"  cssClass="selectBoxWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,'','')"/></td>
				</tr>
		</table>
		 </div>
		 </fieldset>
		 <fieldset>
			<legend style="font-family:arial,helvetica,clean,sans-serif;">Information Source</legend>
			<table>
				<tr>		
					<td style="width:140px;">Information Source<font class="requiredFont"> * </font></td>
					<td><s:select id="userTypeSelectBox" list="#session.informationSourcesList" listKey="id" listValue="name" headerKey="0" headerValue="Select Information Source" name="informationSource" class="selectBoxWidth" onchange="getOtherPersonDetails(this.options[this.selectedIndex].text)"/></td>
				</tr>
			</table>		
			<div id="otherSourcesInformationDiv" style="display:none;">
			<table>	
						<tr>	
							<td style="width:140px;"><%=name%><font class="requiredFont"> * </font></td>
							<td><s:textfield size="25"  name="name" id="externalPersonName" maxlength="50" class="politicalChangesFieldBoxesWidth"/></td>
						</tr>		
						<tr>
							<td style="width:140px;"><%=mobile%><font class="requiredFont"> * </font></td>
							<td><s:textfield size="25" name="mobile" id="externalPersonMobile" maxlength="12" class="politicalChangesFieldBoxesWidth"/></td>
						</tr>		
						<tr>
							<td style="width:140px;"><%=telephoneNo%></td>
							<td><s:textfield size="25"  name="telephoneNo" id="externalPersonTelephoneNo" maxlength="12" class="politicalChangesFieldBoxesWidth"/></td>
						</tr>
						<tr>
							<td style="width:140px;"><%=email%></td>
							<td><s:textfield size="25" name="email" id="externalPersonEmail" maxlength="50"  class="politicalChangesFieldBoxesWidth"/></td>
						</tr>		
						<tr>
							<td style="width:140px;"><%=address%><font class="requiredFont"> * </font></td>
							<td><s:textfield size="25" name="address" id="externalPersonAddress" maxlength="100" class="politicalChangesFieldBoxesWidth"/></td>
						</tr>							
				</table>
			
			</div>
			</fieldset>
			
			<input type="hidden" id="range" name="range" value="${range}"></input>
			<input type="hidden" id="rangeId" name="rangeId" value="${rangeId}"></input>
			<input type="hidden" name="localPoliticalChangeId" value='${localPoliticalChangeId}'></input>
			<input type="hidden" id="saveTypeID" name="type" value='${type}'></input>
			
</div>
	<div id="saveDiv" align="center">
		<table>
		<tr>
			<td><s:submit cssClass="button" value="Save" name="Save" ></s:submit></td>
			<td><input type="button" value="Exit" class="button" onclick="doUnload()"/></td>
		</tr>
		</table>
	</div>
	
</s:form>
<script type="text/javascript">
checkRequestType();
</script>
</body>
</html>