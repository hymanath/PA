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
	

	<script type="text/javascript" src="js/constituencyManagement/constituencyManagement.js"></script>
	<script type="text/javascript" src="js/constituencyManagement/cadreManagement.js"></script>
	<script type="text/javascript" src="js/problemManagementReport/problemManagementReport.js"></script>
	<script type="text/javascript" src="js/influencingPeople/influencingPeople.js"></script>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	
<style type="text/css">
	.politicalChangesFieldBoxesWidth
	{
		width:178px;
	}
	.selectBoxWidth
	{
		width:181px;
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
</style>
	
<script type="text/javascript">


<%			
	ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
	String STATE = rb.getString("STATE");
	String DISTRICT = rb.getString("DISTRICT");
	String CONSTITUENCY = rb.getString("CONSTITUENCY");
	String MANDAL  = rb.getString("MANDAL");
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
 %> 


var localizationObj = {
						STATE : '<%=STATE%>',
						DISTRICT : '<%=DISTRICT%>',
						CONSTITUENCY : '<%=CONSTITUENCY%>',
						MANDAL  : '<%=MANDAL%>',
						VILLAGE : '<%=VILLAGE%>',
						HAMLET  : '<%=HAMLET%>',
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
	var informationSourcesObj = {
			sourceArr : []
	};
	var staticParties = {
			  staticPartiesDataList : []
	}
	var effectedRange = {
			sourceArr : []
	};	
	var range='',rangeId=0;
	var localPoliticalChangeId=0;
	var requestType = "${type}";
	var externalPerson = "EXTERNAL PERSON";
	var editResults='';
	
// Variable Declaration Ends here
	
	function showDateCalendar(eleId) {
	
		var id ;
		elementId = eleId;
		
		if(eleId==1){		
			id = document.getElementById("identifiedFromText_Div");
		}else if(eleId==2){
			id = document.getElementById("reportedFromText_Div");
		}

		if (dateCalendar)
			dateCalendar.destroy();
	
		var navConfig = {
			strings : {
				month : "Choose Month",
				year : "Enter Year",
				submit : "OK",
				cancel : "Cancel",
				invalidYear : "Please enter a valid year"
			},
			monthFormat : YAHOO.widget.Calendar.SHORT,
			initialFocus : "year"
		};
	
		var dateCalendar = new YAHOO.widget.Calendar(id, {
			navigator : navConfig,
			title : "Choose a date:",
			close : true
		});
		
		dateCalendar.selectEvent.subscribe(displayDateTextBoxs, dateCalendar, true);
		dateCalendar.render();
		dateCalendar.show();
	}

	function displayDateTextBoxs(type, args, obj) 
	{
		var dates = args[0];
		var date = dates[0];
		var year = date[0], month = date[1], day = date[2];
		var txtDate1;
		txtDate1 = document.getElementById("identifiedFromText");	
		txtDate1.value = day + "/" + month + "/" + year;
	}
	
	function displayDateTextMethod(type, args, obj) {
		var dates = args[0];
		var date = dates[0];
		var year = date[0], month = date[1], day = date[2];
		var txtDate1;
		txtDate1 = document.getElementById("reportedFromText");
		txtDate1.value = day + "/" + month + "/" + year;		
	}
	
	function showDateCal(eleId) {
		var id ;
		elementID = eleId;
		id = document.getElementById("reportedFromText_Div");

		if (dateCalendar1)
			dateCalendar1.destroy();

		var navConfig = {
			strings : {
				month : "Choose Month",
				year : "Enter Year",
				submit : "OK",
				cancel : "Cancel",
				invalidYear : "Please enter a valid year"
			},
			monthFormat : YAHOO.widget.Calendar.SHORT,
			initialFocus : "year"
		};

		var dateCalendar1 = new YAHOO.widget.Calendar(id, {
			navigator : navConfig,
			title : "Choose a date:",
			close : true
		});
		dateCalendar1.selectEvent.subscribe(displayDateTextMethod, dateCalendar1, true);
		dateCalendar1.render();
		dateCalendar1.show();		
	}
	
	
	
	//Ajax Calls Starts from here..

	function getSelectOptionVOListForPoliticalChanges(id, task)
	{	
		rangeId = id;
		var rangeIdDIv= document.getElementById("rangeId");
		rangeIdDIv.value = rangeId
		
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
		var saveTypeIDDIV = document.getElementById("saveTypeID");
		var heading = document.getElementById("headingDiv");
		var headingDiv = '';
		if(requestType=="Edit"){
			headingDiv+='Edit/Modify Political Changes';
			saveTypeIDDIV.value = "edit";			
		}else{
			
			saveTypeIDDIV.value = "new";	
			headingDiv+='Add Political Changes';
			var politicalId = document.getElementById("politicalChangeId");
			politicalId.value = localPoliticalChangeId;
		}		
		heading.innerHTML = headingDiv;
		if(requestType=="Edit"){									
			localPoliticalChangeId = "${localPoliticalChangeId}";
			var politicalId = document.getElementById("politicalChangeId");
			politicalId.value = localPoliticalChangeId;			
			var jsObj=
				{
					localPoliticalChangeId:localPoliticalChangeId,
					task:"getDetailsBylocalPoliticalChangeId"					
				};
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/getDetailsByPoliticalChangeIdAjaxAction.action?"+rparam;	
				callAjax(rparam,jsObj,url);
		}

		var externalPersonsDiv = document.getElementById("otherSourcesInformationDiv");  
		var nameDiv = document.getElementById("externalPersonName");
		var mobileDiv = document.getElementById("externalPersonMobile");
		var addressDiv = document.getElementById("externalPersonAddress");
		
			otherDetailsForPoliticalChanges = 0;			
			externalPersonsDiv.style.display = 'none';
			nameDiv.value='name';
			mobileDiv.value='9000000000';
			addressDiv.value='address';
		document.getElementById("titleTextField").focus();
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
								
									if(jsObj.task == "getAllPoliticalChangesForTheUser"){
										buildDataTableForLocalPoliticalChanges(myResults);
									} 
									if(jsObj.task == "deltePoliticalChange"){
										getAllPoliticalChangesForTheUser();
									}
									if(jsObj.task == "getExternalPersonDetails"){
										buildExternalPersonDetailsPopUp(myResults);
									}
									if(jsObj.task == "getExternalPersonDetailsForEdit"){ 
										getExternalPersonDetailsToSetData(myResults);
									}
									if(jsObj.task == "effectedRange"){
										setEffectedRangeDetails(myResults);
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
									if(jsObj.task == "getDetailsBylocalPoliticalChangeId")
									{
										editResults = myResults;
										setDefaultDataToEdit(myResults);
									}							
								}     
							catch (e)
								{   
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


//Function to set Data for the DIV's starts here

function setDefaultDataToEdit(results)
{
	
	localPoliticalChangeId = results.politicalChangeId;		
	document.getElementById("titleTextField").value = results.title;
	document.getElementById("descriptionTextBox").value = results.description;
	document.getElementById("reportedFromText").value = results.date;			
	document.getElementById("identifiedFromText").value = results.identifiedDate;
		
	if(externalPerson==results.sourceOfInformation){
		getOtherPersonDetails(externalPerson);
		document.getElementById("externalPersonName").value = results.name;
		document.getElementById("externalPersonMobile").value = results.mobile;
		document.getElementById("externalPersonTelephoneNo").value = results.telephoneNo;
		document.getElementById("externalPersonEmail").value = results.email;
		document.getElementById("externalPersonAddress").value = results.address;				
	}	
	
}

function populateLocations(value)
{	
	//debugger;
	var populateLocationsDiv = '';
	populateLocationsDiv+='<table>';
	if( (value>1) || (value==1) ){
		populateLocationsDiv +='	<tr>';
		populateLocationsDiv +='		<td style="width:154px;">STATE:</td>';
		populateLocationsDiv +='		<td><select id="stateId" name="state" class="selectWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getDistricts\')" /></td>';
		populateLocationsDiv +='	</tr>';
		range = localizationObj.STATE;
	}
	if( (value>2) || (value==2) ){
		populateLocationsDiv +='	<tr>';
		populateLocationsDiv +='		<td>DISTRICT:</td>';
		populateLocationsDiv +='		<td><select id="districtField" name="district" class="selectWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getConstituencies\')" /></td>';
		populateLocationsDiv +='	</tr>';
		range = localizationObj.DISTRICT;
	}
	if( (value>3) || (value==3) ){
		populateLocationsDiv +='	<tr>';
		populateLocationsDiv +='		<td><CONSTITUENCY:</td>';
		populateLocationsDiv +='		<td><select id="constituencyField" name="constituency" class="selectWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getMandals\')" /></td></tr>';
		populateLocationsDiv +='	</tr>';
		range = localizationObj.CONSTITUENCY;
	}
	if( (value>4) || (value==4) ){
		populateLocationsDiv +='	<tr>';
		populateLocationsDiv +='		<td>MANDAL:</td>';
		populateLocationsDiv +='		<th><select id="mandalField" name="mandal" class="selectWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getTowhships\')" /></td>';
		populateLocationsDiv +='	</tr>';
		range = localizationObj.MANDAL;
	}
	if( (value>5) || (value==5) ){
		populateLocationsDiv +='	<tr>';
		populateLocationsDiv +='		<td>Revenue VILLAGE:</td>';
		populateLocationsDiv +='		<td><select id="villageField" name="village" class="selectWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getVillages\')" /></td>';
		populateLocationsDiv +='	</tr>';
		range = localizationObj.VILLAGE;
	}
	if( (value>6) || (value==6) ){
		populateLocationsDiv +='	<tr>';
		populateLocationsDiv +='		<td>Hamlet:</td>';
		populateLocationsDiv +='		<td><select id="hamletField" name="hamlet" class="selectWidth" onchange="getSelectOptionVOListForPoliticalChanges(this.options[this.selectedIndex].value,\'getHamletIdAndRange\')"/></td>';
		populateLocationsDiv +='	</tr>';
		range = localizationObj.HAMLET;
		rangeId = value;
	}
	populateLocationsDiv+='</table>';

	var rangeDIv= document.getElementById("range");
	rangeDIv.value = range
	
	getSelectOptionVOListForPoliticalChanges(1,"getStates");
	
	var elementDiv = document.getElementById("locationPopulationDiv");
	elementDiv.innerHTML = populateLocationsDiv; 
}


	function getOtherPersonDetails(name)
	{
		var externalPersonsDiv = document.getElementById("otherSourcesInformationDiv");  
		var nameDiv = document.getElementById("externalPersonName");
		var mobileDiv = document.getElementById("externalPersonMobile");
		var addressDiv = document.getElementById("externalPersonAddress");
		
		if(name==externalPerson)
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

	function setDefaultDataForEditing()
	{

		document.getElementById("titleTextField").value = title;
		document.getElementById("descriptionTextBox").value = description;
		document.getElementById("reportedFromText").value = date;			
		document.getElementById("identifiedFromText").value = identifiedDate;
		var partySelect = document.getElementById("selectedPartyBox");
		for (var i=0; i<partySelect.options.length; ++i){ 
			if (staticParties.staticPartiesDataList[i].value==partyName) 
				partySelect.options[i].selected = true;				
		}

		var userSelect = document.getElementById("userTypeSelectBox");
		for (var i=0; i<userSelect.options.length; ++i){ 
			if (informationSourcesObj.sourceArr[i].value==sourceOfInformation) 
				userSelect.options[i].selected = true;				
		}
		
		if(externalPerson==sourceOfInformation){
			getExternalPersonDetailsForEdit(politicalChangeId);
			getOtherPersonDetails(sourceOfInformation);
			document.getElementById("externalPersonName").value = externalPersonname;
			document.getElementById("externalPersonMobile").value = externalPersonmobile;
			document.getElementById("externalPersonTelephoneNo").value = externalPersontelephoneno;
			document.getElementById("externalPersonEmail").value = externalPersonemail;
			document.getElementById("externalPersonAddress").value = externalPersonaddress;				
		}	

		
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
	}
	</script>
</head>

<body onload="checkRequestType()" onunload="doUnload()" style="background-color:#f2f2f2;">	

<DIV id="headingDiv" class="headingStyle" align="center"></div>

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
	
<s:form action="politicalChangesRegistrationAction" method="POST" theme="simple" name="form">			
	<div id="mainPoliticalDiv" align="left">
		<table>				
			<tr>
				<td><DIV id="politicalChangeTitleData" style="width:157px;"><b style="color:red">*</b> <%=title%> </div></td>
				<td><input class="politicalChangesFieldBoxesWidth" name="title" id="titleTextField" type="text" width="300px"></input></td>
			</tr>	
			<tr>
				<td><DIV id="politicalChangeDescripionData"><%=description%></div></td>
				<td><textarea id="descriptionTextBox" name="description" rows="2" cols="20"></textarea></td>
			</tr>
		</table>
				
		<fieldset>
			<legend style="font-family:arial,helvetica,clean,sans-serif;"><%=moreDetails%></legend>
			<table>
				<tr>
					<td><b style="color:red">*</b> <%=occuredDate%></td>
					<td>
						<input type="text"  READONLY="READONLY" name ="occuredDate" class="politicalChangesFieldBoxesWidth" id="identifiedFromText" style="margin-top:0px;" name="identifiedFromText" size="20"/>
						<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div></td>					
					</td>
					<td valign="top">
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCalendar(identifyDate)"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="politicalChangesCalendarImage" border="0"/></a>
					</td>
				</tr>	
				<tr>		
					<td> <%=reportedDate%></td>
					<td>
						<input type="text" READONLY="READONLY" name ="reportedDate" class="politicalChangesFieldBoxesWidth" id="reportedFromText" style="margin-top:0px;" name="reportedFromText" size="20"/>
						<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal"></div></div></td>
					</td>
					<td valign="top">
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal(reportDate)"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="politicalChangesCalendarImage" border="0"/></a>
					</td>
				</tr>
				<tr>
					<td>
						<%=selectParty%>	
					</td>
					<td>
						<select id="selectedPartyBox" name="party" class="selectBoxWidth">
							<c:forEach var="parties" varStatus="stat" items="${sessionScope.staticPartiesList}">
								<option value="${parties.id}">${parties.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>	
					
				<tr>
					<td style="width:156px;">
						<b style="color:red">*</b> <%=selectRange%>
					</td>
					<td>
						<select id="effectedRange" name="effectRange" class="selectBoxWidth" onchange="populateLocations(this.options[this.selectedIndex].value)">
							<option value="">Select Range</option>
							<c:forEach var="effectedRange" varStatus="stat" items="${sessionScope.effectedRangeList}">
								<option value="${effectedRange.id}">${effectedRange.name}</option>
							</c:forEach>
						</select>		
					</td>
				</tr>  
				<tr> 
					<table>	
						<tr>
							<td>
								<div id="locationPopulationDiv"  align="left">
									
								</div>		
							</td>
						</tr>	
					</table>
				</tr> 
		 </table>
		 </fieldset>
		<table>
			<tr>		
				<td style="width:156px;">	
					<%=selectSource%>			
				</td>
				<td>
					<select id="userTypeSelectBox" name="sourceInformation" class="selectBoxWidth" onchange="getOtherPersonDetails(this.options[this.selectedIndex].text)">
						<c:forEach var="informationSources" varStatus="stat" items="${sessionScope.informationSourcesList}">
							<option value="${informationSources.id}">${informationSources.name}</option>
						</c:forEach>
					</select>		
				</td>
			</tr>
		</table>	
		
			<div id="otherSourcesInformationDiv" style="display:none;">
			<fieldset>
			<legend style="font-family:arial,helvetica,clean,sans-serif;">Candidate Details</legend>			
				<table>	
						<tr>	
							<td style="width:152px;"> <b style="color:red">*</b> <%=name%></td>
							<td><input type="text" name="name" id="externalPersonName" class="politicalChangesFieldBoxesWidth"></input></td>
						</tr>		
						<tr>
							<td> <b style="color:red">*</b> <%=mobile%></td>
							<td><input type="text" name="mobile" id="externalPersonMobile" class="politicalChangesFieldBoxesWidth"></input></td>
						</tr>		
						<tr>
							<td><%=telephoneNo%></td>
							<td><input type="text" name="telephoneNo" id="externalPersonTelephoneNo" class="politicalChangesFieldBoxesWidth"></input></td>
						</tr>
						<tr>
							<td><%=email%></td>
							<td><input type="text" name="email" id="externalPersonEmail" class="politicalChangesFieldBoxesWidth"></input></td>
						</tr>		
						<tr>
							<td><b style="color:red">*</b> <%=address%></td>
							<td><input type="text" name="address" id="externalPersonAddress" class="politicalChangesFieldBoxesWidth"></input></td>
						</tr>							
				</table>
				</fieldset>
			</div>
					
			<table>			
				<tr>
					<td>
							<input type="hidden" id="range" name="range"></input>
					</td>
					<td>
							<input type="hidden" id="rangeId" name="rangeId"></input>
					</td>
					<td>
							<input type="hidden" id="politicalChangeId" name="politicalChangeId"></input>
					</td>
					<td>
							<input type="hidden" id="saveTypeID" name="saveType"></input>
					</td>
				</tr>
				
				<tr>
					<td>
							<b style="color:red">*</b> <%=mandatoryFields%>
					</td>
				</tr>
				
				<tr>
					<td>
							<div id="successMsg"></div>
					</td>
				</tr>	
		</table>
			
</div>
	<div id="saveDiv" align="center">
		<s:submit cssClass="button" value="Save" name="Save"></s:submit>
	</div>
	
</s:form>
</body>
</html>