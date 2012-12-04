<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/voterAnalysis/voterAnalysis.js"></script>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->

<title>Voters analysis</title>
<style>
.pull-right{
	 margin-top: -15px;
}

.form-horizontal label{  display:block; font:12px Arial;
    margin-bottom:5px;padding-top:5px;cursor:hand;cursor:pointer;}
	.form-horizontal .radio
	{
	font-size: 13px;
    font-weight: normal;
    margin-top:-1px;
	margin-right:8px;
	}
.buttonStyle {
	-moz-border-radius:5px 5px 5px 5px;
    background: none repeat scroll 0 0 #0063DC;
    border: medium none;
    border-radius: 4px 4px 4px 4px;
    color: #FFFFFF;
    cursor: pointer;
    font-family: inherit;
    font-size: 12px;
    font-weight: bold;
    padding: 4px 6px;
    text-decoration: none;
    white-space: nowrap;
	height:24px;
}
 #votersouterDiv{
	 margin-left: auto;
    margin-right: auto;
    width: 980px;
 }

 fieldset {
    -moz-border-bottom-colors: none;
    -moz-border-left-colors: none;
    -moz-border-right-colors: none;
    -moz-border-top-colors: none;
    border: 3px solid #CFD6DF;
   
    margin-bottom: 10px;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
}
p {
    color: #333333;
    font-size: 13px;
    line-height: 18px;
}
.divInfo
{
 background-color:#FFFFFF;
 border-bottom: 1px solid #B3C1CE;
 border-left: 1px solid #B3C1CE;
 border-right: 1px solid #B3C1CE;
 padding:5px;
}
</style>

<script type="text/javascript">
var Localization = { <%
			
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			
			String ACONSTITUENCY = rb.getString("ACONSTITUENCY");
			String locationAlert = rb.getString("validLocation");
		  %> }
var locationDetails={
				constituencyArr:[],
					};

<c:forEach var="constituency" items="${constituencyList}">
	var ob={
			id:'${constituency.id}',
			value:'${constituency.name}'
			};
locationDetails.constituencyArr.push(ob);
</c:forEach>

	



</script>
</head>
<body>
<br><br>

<div id="votersouterDiv" ></div>
<br><br>
<div id='votersMainOuterDiv'>
<!-- heading -->
<div id='votersHeaderDiv'>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
		  <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg">
		    </td>
		    <td width="98%">
		       <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
		         <span style="text-decoration: none;" class="headerLabelSpan2"><center>Constituency Management Admin</center></span>
		       </div>
		    </td>
		   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
		   </td>
		</tr>
	</table>
</div>

<div id='votersMainInnerDiv' class="divInfo" style="padding:10px;">
	<div id="profileManagementDiv">
		<table class="statusData_table" width="100%">	
		
          <tr>
			<td class="statusData_table_data" width="100%" style="padding-top:23px">
				<table>
				  <tr>
			    	<td style="padding-left:109px"><b><input type="button" class="buttonStyle" value="Important Families" id="importantFamiliesId" style="height:24px;" onclick="showImportantFamiliesDiv()"></b></td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Local Cast Statistics" 
					 id="localCaststatId" style="height:24px;" onclick="showLocalCastDiv()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="Voters" id="votersId" style="height:24px;" onclick="showVotersDiv()"></b> </td>
					<td style="padding-left:50px"><b><input type="button" class="buttonStyle" value="AgeWise" id="ageWiseId" style="height:24px;" onclick="showAgeDiv()"></b> </td>
					
				  </tr>
				</table>
			</td>
		 </tr>
		</table>
	
</div>
<!-- header End -->
</div>



<!-- for  body 1 start    result  -->
<HR>
<div id='votersMainOuterDiv1' style="display:none">
	<div id='votersHeaderDiv1'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Important Families</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='ImportantFamiliesDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 1 end    result  -->




<!-- for  body 2 start    result  -->

<div id='votersMainOuterDiv2' style="display:none">
	<div id='votersHeaderDiv2'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Local Cast statistics</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='LocalCastDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 2 end    result  -->



<!-- for  body3 start    result  -->

<div id='votersMainOuterDiv3' style="display:none">
	<div id='votersHeaderDiv3'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Voters Info</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='votersDiv' class="divInfo">
	
	</div>

</div>

<!-- for  body 3 end    result  -->



<!-- for  body4 start    result  -->

<div id='votersMainOuterDiv4' style="display:none">
	<div id='votersHeaderDiv4'>
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_left.jpg"> 
				   </td>
				   <td width="98%">
					 <div style="text-decoration: none;" class="productFeatureHeaderBackground_center2">
					   <span style="text-decoration: none;" class="headerLabelSpan2">Age Wise</span>
					 </div>
				   </td>
				   <td width="1%"><img height="40" width="25" src="images/icons/homePage_new/blue_header_top_right.jpg">
				   </td>
			 </tr>
		</table>
	</div>

	<div id='ageWiseInfoDiv' class="divInfo">
	
	</div>

</div>

<div style="margin-left:120px;" id="votersByLocationTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>

<div style="margin-left:120px;" id="votersByPanchayatTabContentDiv_body" class="yui-skin-sam yui-dt-sortable"></div>

<!-- for  body 4 end    result  -->

</div>

<script type="text/javascript">
 buildOuterView();
 
 showImportantFamiliesDiv();
</script>

<script>
//buildVotersByLocBoothDataTable();
function buildVotersByLocBoothDataTable()
{

//var checkedele = "pollingstation";

//var boothId = $('#imppollingStationField').val();

var boothId = 115;


var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo"},
{key:"firstName", label: "Name", sortable: true},
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
{key:"relationshipType", label: "Relationship", sortable:true},
{key:"cast", label: "Cast", sortable:true},
{key:"castCatagery", label: "CastCategory", sortable:true}
];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId="+boothId+"&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","relationshipType","cast","castCatagery"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=20", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}


return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}
</script>


<script>
buildVotersByLocPanchayatDataTable();
function buildVotersByLocPanchayatDataTable()
{

//var checkedele = "pollingstation";

//var boothId = $('#imppollingStationField').val();

//var boothId = 115;

var votersByLocBoothColumnDefs = [
{key:"voterId", label: "SNo"},
{key:"firstName", label: "Name", sortable: true},
{key:"gender", label: "Gender", sortable: true},
{key:"age", label: "Age", sortable:true},
{key:"houseNo", label: "House No", sortable:true},
{key:"relativeFirstName", label: "GuardName", sortable:true},
{key:"relationshipType", label: "Relationship", sortable:true},
{key:"cast", label: "Cast", sortable:true},
{key:"castCatagery", label: "CastCategory", sortable:true}
];

//var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?boothId=115&isVoter=true&checkedele="+checkedele+"&");

var votersByLocBoothDataSource = new YAHOO.util.DataSource("getVoterDetails.action?publicationId=1&panchaytId=444&");
votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
votersByLocBoothDataSource.responseSchema = {
resultsList: "voterDetails",
fields: [
{key:"voterId", parser:"number"},
"firstName", "gender", "age", "houseNo","relativeFirstName","relationshipType","cast","castCatagery"],
metaFields: {
totalRecords: "voterDetailsCount" // Access to value in the server response
}
};

var myConfigs = {
initialRequest: "sort=voterId&dir=asc&startIndex=0&results=20", // Initial request for first page of data
dynamicData: true, // Enables dynamic server-driven data
sortedBy : {key:"voterId", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
   paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    })  // Enables pagination
};

var votersByLocBoothDataTable = new YAHOO.widget.DataTable("votersByPanchayatTabContentDiv_body",
votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);

votersByLocBoothDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
oPayload.totalRecords = oResponse.meta.totalRecords;
return oPayload;
}


return {
oDS: votersByLocBoothDataSource,
oDT: votersByLocBoothDataTable
};
}
</script>

<script type="text/javascript">
 buildOuterView();
 
 showImportantFamiliesDiv();
</script>
</body>
</html>