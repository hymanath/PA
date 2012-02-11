<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.itgrids.partyanalyst.dto.ConstituencyVO" %>
<%@page import="com.itgrids.partyanalyst.dto.CandidateVO" %>
<%@page import="java.util.List" %>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Results</title>

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

<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>
<script type="text/javascript" src="js/cncSearch.js"></script>	
<script type="text/javascript" src="js/ajaxSearch/ajax.js" ></script>
<script type="text/javascript" src="js/ajaxSearch/ajax-dynamic-list.js" ></script>

<script type="text/javascript"> 		

URL="<%=request.getContextPath()%>/ajaxSearchAction.action?" 	
	
	function ajaxCall(param)
	{		
		var xmlHttp=getXmlHttpObj();		
		xmlHttp.open("post",URL,true);
		xmlHttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xmlHttp.send(param);
		
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState==4 && xmlHttp.status == 200)
			{
				var jObj=eval('('+xmlHttp.responseText+')');				
				buildAutoSuggest(jObj);		
			}			
		}
	} 
 </script>
<style type="text/css">

table.searchresultsTable{
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	width:500px;
	margin-top:10px;
}
table.searchresultsTable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}
table.searchresultsTable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}

#mainDiv
{
	margin-top:10px;
}

.yui-skin-sam 
{
	font-weight:bold;
}
.yui-skin-sam .yui-dt th
{
	background-image:url(images/YUI-images/sprite.png)
}

#yui-dt0-th-Categorize
{
	background-color:blue;
}

.viewReportButtonSpan {
		background-image:url("images/icons/electionResultsAnalysisReport/2.png");
		cursor:pointer;
		height:30px;
		position:relative;
		width:80px;
		border:1px solid ; 
		color:#FFFFFF;
		font-size:12px;
		font-weight:bold;		
		position:relative;
		top:6px;
		margin-bottom: 5px;

	}
	/* Big box with list of options */
	#ajax_listOfOptions{
		position:absolute;	/* Never change this one */
		width:185px;	/* Width of box */
		height:200px;	/* Height of box */
		overflow:auto;	/* Scrolling features */
		border:1px solid #317082;	/* Dark green border */
		background-color:#FFF;	/* White background color */
		text-align:left;
		font-size:0.9em;
		z-index:100;
	}
	#ajax_listOfOptions div{	/* General rule for both .optionDiv and .optionDivSelected */
		margin:1px;		
		padding:1px;
		cursor:pointer;
		font-size:0.9em;
	}
	#ajax_listOfOptions .optionDiv{	/* Div for each item in list */
		
	}
	#ajax_listOfOptions .optionDivSelected{ /* Selected item in the list */
		background-color:#317082;
		color:#FFF;
	}
	#ajax_listOfOptions_iframe{
		background-color:#F00;
		position:absolute;
		z-index:5;
	}
#searchBlock
{
	background-color:#94A9C8;
	border:1px solid;
	color:#FFFFFF;
	font-weight:bold;
	margin-top:15px;
	padding:15px;
	width:450px;
}
.adData_head {
	border-bottom:1px solid #ADADAD;
	font-weight:bold;
	padding:5px;
	width: 210px;
	margin: 10px;
}
table.searchCriteria{
	border-collapse:collapse;
	color:#333333;
	font-family:verdana,arial,sans-serif;
	font-size:11px;
}
#tablerDetails {
	border-bottom:1px solid #DBDCDB;
	border-left:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	margin-bottom:10px;
	margin-top:10px;
	width: 475px;
}

h3 {
	background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
	color:#4B74C6;
	font-size:12px;
	font-weight:bold;
	height:20px;
	margin:0;
	padding:5px;
	text-align:left;
	width:460px;
}
.head {
	background-color:#EBEBEB;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	padding-top: 3px;
	color:#163447;
}
.middle {
	background-color:#EAEAE9;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	color:#474B51;
	padding:5px;
	text-align:left;
}
.edge {
	background-color:#EAEAE9;
	border-bottom:1px solid #DBDCDB;
	color:#474B51;
	padding:5px;
}
</style>

<script type="text/javascript">
var searString = '${searchText}';
var searchName = '${searchName}';
var constType  = '${constType}';
var stateId    = '${state}';
var SearchCount = '${totalSearchCount}';

YAHOO.util.Event.onAvailable("myInput", this.setComponentValues, this); 

function setComponentValues()
{

	if(constType == "MLA")
		document.SearchNameFormName.constType[0].checked = true;
	if(constType == "MP")
		document.SearchNameFormName.constType[1].checked = true;

	if(searchName == "Candidate")
		document.SearchNameFormName.searchName[0].checked = true;
	if(searchName == "Constituency")
		document.SearchNameFormName.searchName[1].checked = true;
	
	var elmt = document.getElementById("stateSelect");
	elmt.selectedIndex = '${state}';
		
	var elmt2 = document.getElementById("myInput");	
	elmt2.value = searString;

}

function buildCandidateSearchResultDataTable()
{
  YAHOO.widget.DataTable.formatEmail = function(elLiner, oRecord, oColumn, oData) 
  {
	var user = oRecord.getData("candidateName");
	var id= oRecord.getData("candidateId");
	elLiner.innerHTML ="<a href='candidateElectionResultsAction.action?candidateId="+id+" '>"+user+"</a>";
		
  };
			
	var CandidateSearchResultColumnDefs = [ 
		    	            {key:"id", label: "SNO",sortable: true} ,
		    	            {key:"candidateName", label: "Name", sortable: true,formatter:YAHOO.widget.DataTable.formatEmail}, 
		    	           	{key:"party", label: "Party", sortable: true},
		    				{key:"year", label: "Year",sortable:true},
							{key:"constituencyName", label: "Constituency",sortable:true},
							{key:"scope", label: "Election",sortable:true},
		    				{key:"position", label: "Position",sortable:true}	    			    			
		    	        ]; 
	var CandidateSearchResultDataSource = new YAHOO.util.DataSource("cnCSearchAjaxAction.action?searchText="+searString+"&constType="+constType+"&state="+stateId+"&"); 
	CandidateSearchResultDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON; 
	
	CandidateSearchResultDataSource.responseSchema = { 
            resultsList:"candidateVOList", 
            fields: [
                     {key:"id", parser:"number"},
                     "candidateName", "party", "year", "constituencyName","scope","position","candidateId"],
            metaFields: {
	            totalRecords: "totalSearchCount" // Access to value in the server response
	        }         
        };


    var myConfigs = {
			        initialRequest: "sort=candidateName&dir=asc&startIndex=0&results=20", // Initial request for first page of data
			        dynamicData: true, // Enables dynamic server-driven data
			        sortedBy : {key:"id", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
			        paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
		};


		var CandidateSearchResultDataTable = new YAHOO.widget.DataTable("searchResultsDiv", CandidateSearchResultColumnDefs,CandidateSearchResultDataSource, myConfigs);

		CandidateSearchResultDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {		
		        oPayload.totalRecords = oResponse.meta.totalRecords;
		        return oPayload;
		}
		
		return {
			oDS: CandidateSearchResultDataSource,
			oDT: CandidateSearchResultDataTable
		};
	}

function buildConstituencySearchResultDataTable()
{

 YAHOO.widget.DataTable.formatEmail = function(elLiner, oRecord, oColumn, oData) 
  {
	var constName = oData;
	var constId= oRecord.getData("constituencyId");
	var distId= oRecord.getData("districtId");
	if(constType == 'MLA')
	{
	  elLiner.innerHTML ="<a href=\"constituencyPageAction.action?districtId="+distId+"&constituencyId="+constId+">"+constName+"</a>";
	}
	else if(constType == 'MP')
	{
	elLiner.innerHTML ="<a href=\"constituencyPageAction.action?constituencyId="+constId+">"+constName+"</a>";
	}
  };

	var ConstituencySearchResultColumnDefs = [ 
		    	            {key:"id", label: "SNO"},
							{key:"name", label: "constituency",sortable: true,formatter:YAHOO.widget.DataTable.formatEmail} ,
		    	            {key:"electionType", label: "Election Type", sortable: true}, 
		    	           	{key:"districtName", label: "District", sortable: true},
		    				{key:"stateName", label: "State",sortable:true},
						    {key:"delemitationInfoStr", label:"Delimination",sortable:true}
						 ]; 
	var ConstituencySearchResultDataSource = new YAHOO.util.DataSource("constituencySearchAjaxAction.action?searchText="+searString+"&constType="+constType+"&state="+stateId+"&"); 

	ConstituencySearchResultDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	
	ConstituencySearchResultDataSource.responseSchema = { 
            resultsList:"constituencyVOList", 
            fields: [
					 {key:"id", parser:"number"},"name","districtId","constituencyId",       
			         "electionType", "districtName", "stateName","delemitationInfoStr"
					],
            metaFields: 
					{
	            totalRecords: "totalSearchCount" // Access to value in the server response
	        }         
        };


    var myConfigs = {
			        initialRequest: "sort=name&dir=asc&startIndex=0&results=20", // Initial request for first page of data
			        dynamicData: true, // Enables dynamic server-driven data
			        sortedBy : {key:"id", dir:YAHOO.widget.DataTable.CLASS_ASC}, // Sets UI initial sort arrow
			        paginator: new YAHOO.widget.Paginator({ rowsPerPage:20 }) // Enables pagination 
		};


		var ConstituencySearchResultDataTable = new YAHOO.widget.DataTable("searchResultsDiv", ConstituencySearchResultColumnDefs,ConstituencySearchResultDataSource, myConfigs);

		ConstituencySearchResultDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {		
		        oPayload.totalRecords = oResponse.meta.totalRecords;
		        return oPayload;
		}
		
		return {
			oDS: ConstituencySearchResultDataSource,
			oDT: ConstituencySearchResultDataTable
		}; 
	}

	
</script>
</head>
<body>

<div id="tablerDetails">
	<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD><IMG src="images/icons/electionResultsAnalysisReport/first.png"></TD>
		<TD><H3>Search for Politician or Constituency</H3></TD>
		<TD><IMG src="images/icons/electionResultsAnalysisReport/second.png"></TD>
		</TR>
	</TABLE>
	 <s:form theme="simple" name="SearchNameFormName" action="cncSearchResultsAction" onsubmit="return validateTextField()" method="post">
	<table cellpadding="0" cellspacing="0" width="475px">
	<tr>
		<th align="left" colspan="2" class="middle">
			Search Criteria
		</th>	
		<td align="left" class="middle">		
			<input id="candidateRadio" type="radio"  name="searchName" value="Candidate" onclick="resetConstTypeOptions()" checked="checked"/>Politician
		</td>
		<td align="left" class="edge">	
			<input id="constituencyRadio" type="radio"  name="searchName" value="Constituency" onclick="resetConstTypeOptions()"/>Constituency	
		</td>
	</tr>
	<tr id="row2">
		<th colspan="2" align="left" class="middle">
			Constituency Type
		</th>	
		<td align="left" class="middle">		
			<input id="mlaRadio" type="radio"  name="constType" value="MLA" onclick="resetStateSelect();getStates(1,'statesInCountry','siteSearch','stateSelect','current','null');" checked="checked"/>MLA
		</td>
		<td align="left" class="edge">	
			<input id="mpRadio" type="radio"  name="constType" value="MP" onclick="resetStateSelect();getStates(1,'stateSelect','siteSearch','stateSelect','current','null');"/>MP	
		</td>		
	</tr>
	<tr>
		<th align="left" colspan="2" class="middle">		
			State	
		</th>	
		<td colspan="2" class="middle">
			<table>
			<tr>
			<td>
				<s:select list="{}" listKey="id" cssStyle="width:177px;" listValue="name" name="state" id="stateSelect" onchange="getParser(this.options[this.selectedIndex].value)"/>
			</td>
			<td>
				<img  id="ajaxLoaderimg" height="16" width="16" src="<%=request.getContextPath()%>/images/icons/ajaxLoader.gif" style="display:none;">
			</td>
			</tr>
			</table>
		</td>	
	</tr>		
	<tr id="row5">	
		<th  align="left" class="middle" colspan="2">
			<div id="labelDiv">Enter Search String</div>
		</th>
		<td colspan="2" class="edge">		
			<div id="textFldDiv"></div>				
		</td>
	</tr>
	<tr>
		<td colspan="4" style="text-align: right"class="middle">
			<input type="submit" name="searchButton" class="viewReportButtonSpan" value="Search"/>	
		</td>
	</tr>		
	</table>
	<div id="errorDiv" style="font-size: 10px; color: red;text-align:center;font-weight:bold;"></div>
</s:form>
	</div>
<div style="padding:0px;font-weight:bold">

	<font color="green">Total <s:property value="totalSearchCount" /> Results Found for " <s:property value="searchText" /> ".</font>
	</div>

	<div id="mainDiv" class="yui-skin-sam">	
	<div id="searchResultsDiv"></div>
	</div>
   
 <script type="text/javascript">
 if(searchName == "Candidate" && SearchCount != 0 )
 {
	buildCandidateSearchResultDataTable();
 }

if(searchName == 'Constituency' && SearchCount != 0 )
 {
	buildConstituencySearchResultDataTable();
 }

 getStates(1,'statesInCountry','siteSearch','stateSelect','current','null');

</script>
</body>
</html>
