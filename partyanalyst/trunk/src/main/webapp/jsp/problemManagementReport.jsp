<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page import="java.util.ResourceBundle;" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Problems Search</title>



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
<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>

<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>

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

<!-- YUI Dependency files (End) -->

<style type="text/css">
		.yui-skin-sam .yui-panel 
		{
			background:#FFFFFF none repeat scroll 0 0;
			border-color:#808080;
			border-style:solid;
			border-width:1px 0;
			left:0;
			position:relative;
			top:0;
			z-index:1;
		}
		.detailsDiv
		{
			margin-top:10px;
			margin-bottom:10px;
			margin-right:30px;
			margin-left:30px;
			text-align:left;
		}
		.detailsHead
		{
			font-weight:bold;
			color:#1C4B7A;
			text-decoration:underline;
			font-size:15px;
			padding:5px 5px 5px 0px;
			text-
		}
		.detailsBody
		{
			padding:5px;
			background-color:#F1F5F7;
		}				
		#districtAncSpan
		{
			padding:10px;
		}
		.districtAnc
		{
			color:#1C4B7A;
		}	
		/*.yui-skin-sam .yui-dt th
		{
			
			background:#D8D8DA url(../../../../assets/skins/sam/sprite.png) repeat-x scroll 0 0;
		}*/

		
		.problemHeader
		{
		font-size:20px;
		color:salmon;
		text-align:center;
		margin:10px;
		text-decoration:underline;
		}

		#problemHeading
		{
		margin-left:15px;
		text-align:left;
		}
		#radioLocationHeader
		{
		margin-left:1cm;
		text-align:left;
		}
		
		#areaTypes
		{
		margin-left:1cm;
		text-align:left;
		}
		#areaTypess
		{
		margin-left:1cm;
		text-align:left;
		}
		.selectWidth
		{
			width:160px;
		}
		.selectWidth2
		{
			width:180px;
		}
		#problemInfoDivBody
		{
		width: 900px;
		overflow-x:auto;
		}
		#buildId
		{
		margin-left:1cm;
		text-align:left;
		}
		.popUpDataStyle
		{
			align:left;
			margin-left:0.6cm;
			color:blue;
		}
		legend {
		background-color:#9696C0;
		color:#FFFFFF;
		font-family:status-bar;
		font-size:11px;
		font-weight:bold;
		padding:5px;
	    }
	    }
		fieldset {
			border:4px solid #F6DFC9;
			margin-bottom:10px;
		}
		#postedPersonTable
		{
		  color:highlight;
		  font-family:verdana;
		  text-align:center;
		  width:100%;
		}
		#probDetailsTable
		{
		  color:Gray;
		  font-family:verdana;
		  text-align:center;
		  width:100%;		  
		}
		#assignedDepartmentTable showProblems
		{
		  color:Navy;
		  font-family:verdana;
		  text-align:center;
		  width:100%;
		}
		#headerImageCenterDiv {
		background-image:url(images/icons/constituencyManagement/header_body_blue.png);
		height:30px;
		text-align:center;
		width:250px;		
		}
		#headerImageCenterSpan {
		color:#FFFFFF;
		font-size:14px;
		font-weight:bold;
		position:relative;
		top:6px;
		}
		#mainDIV
		{
		-moz-border-radius-bottomleft:6px;
		-moz-border-radius-bottomright:6px;
		-moz-border-radius-topleft:6px;
		-moz-border-radius-topright:6px;
		background-color:#B2BDC4;
		border:1px solid #B2BDC4;		
		width:96%;
		}
		th {
		color:#113D5B;
		font-size:11px;
		padding:6px;
		text-align:left;
		vertical-align:top;
		width:122px;
		}
		.tdClass
		{
			color:#113D5B;
			font-size:11px;
			padding:6px;
			text-align:left;				
		}
		
		.problemData_main
		{
			border:1px solid #ADADAD;
			margin-top: 15px;
		}
		.problemData_head
		{
			background-color: #B2BDC4;
			border-bottom: 1px solid #ADADAD;
			color: #FFFFFF;
			font-size: 14px;
			font-weight: bold;
			padding: 5px;
			text-align: left;
		}
		.problemData_body
		{
			padding: 10px;
		}
		.widgetHeader
		{
			background-image:url("images/icons/districtPage/header_body.png");
			height:36px;
		}
		.widgetHeaderSpan
		{
			left:10px;
			position:relative;
			top:11px;
			color:#4B74C6;
			font-weight:bold;
		}

		.problemDetailsBody
		{
			border-bottom:1px solid #E0E0D6;
			border-left:1px solid #E0E0D6;
			border-right:1px solid #E0E0D6;
			padding:10px;
		}

		.yui-skin-sam 
		{
			font-weight:bold;
		}
		.yui-skin-sam .yui-dt th .yui-dt th a
		{
			background-image:url(images/YUI-images/sprite.png)
		}
		
		#yui-dt0-th-Categorize
		{
			background-color:blue;
		}
		#stProblemDetails_body
		{
			border-bottom:1px solid #E0E0D6;
			border-left:1px solid #E0E0D6;
			border-right:1px solid #E0E0D6;
			padding-top:5px;
		}

		.problemDetailsBody_table th
		{
			background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
			padding:4px;
			color:#2C31C1;
		}
		.problemDetailsBody_table td
		{
			padding:4px;
			width:25px;
			font-weight : bold;
			color:#C308F2;
		}
		.problemDetailsBody_table_img
		{
			padding-right:10px;
		}
		.anchorStyle
		{
			cursor:pointer;
		}
		
		.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a {
			color:#000000;
			font-weight:bold;
			text-decoration:none;
			vertical-align:bottom;
		}

		.btnClass
		{
			-moz-box-shadow:0 1px 2px rgba(0, 0, 0, 0.1), 0 1px 0 rgba(255, 255, 255, 0.9) inset;
			background-color:#FFFFFF;
			background-image:url("images/icons/constituencyManagement/buttonblue.png");
			background-position:left bottom;
			background-repeat:repeat-x;
			border-color:#C4D1DB #9EB7CD #9EB7CD #C4D1DB;
			border-right:1px solid #9EB7CD;
			border-style:solid;
			border-width:1px;
			font-size:14px;
			font-weight:bold;
			height:33px;
			padding-left:12px;
			padding-right:12px;
			text-shadow:0 1px 1px #FFFFFF;
			margin-bottom : 10px;
		}
		
	</style>   	

</head>
<script type="text/javascript">  

var Localization = { <%
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String STATE = rb.getString("STATE");
			String DISTRICT = rb.getString("DISTRICT");
			String ACONSTITUENCY = rb.getString("ACONSTITUENCY");
			String CONSTITUENCY = rb.getString("CONSTITUENCY");
			String PCONSTITUENCY = rb.getString("PCONSTITUENCY");
			String MANDAL  = rb.getString("MANDAL");
			String VILLAGE = rb.getString("VILLAGE");
			String HAMLET   = rb.getString("HAMLET");			
  %> }


 google.load("visualization", "1", {packages:["corechart"]});

var locationDetails={
		stateArr:[],
		districtArr:[],
		constituencyArr:[],
		mandalArr:[],
		villageArr:[],
		hamletArr:[],
		allDistrictsByStateArr:[]
	};

//problemDetails.problemArray
var problemDetails={
		problemArray:[],
		record:[],
		problemHistoryArray:[]
	};


var type="",createGroupDialog,problemLocationId; 
function executeOnload()
{
	var scopeLevelEl = document.getElementById("scopeLevel");
	var selectedScopeLevel = scopeLevelEl.options[scopeLevelEl.selectedIndex].value; 
	if(selectedScopeLevel != 0)
	{
		showProbRegionsSelect();
		populateLocations(selectedScopeLevel, 'onLoad');
		getProblems();
	}
}
function showProblemsReport(results, jsObj){
	assignToProblemsArray = new Array();

			for(var i in results)
			{
				var problemObj= {
						 problem: results[i].problem,
						 description: results[i].description,
						 existingFrom: results[i].existingFrom,
						 status: results[i].status,						 
						 problemLocation: results[i].problemLocation,
						 department: results[i].department,
						 designation: results[i].designation						 						
					};				
				assignToProblemsArray.push(problemObj);
				problemDetails.problemArray=assignToProblemsArray;	
			}
			var emptyArr = new Array();
		    if(results.length == 0)
			{	
		    	problemDetails.problemArray = emptyArr;				
			}
			if(jsObj.subTask == '')
			{
				initializeResultsTable();
			} else 
			{
				showProblemsReportWithDepartments();
			}
					
}

function getProblemDepartments(selected,task)
{
		var jsObj=
		{
			scopeId		 : selected,
			task		 : task,
			deptCategoryId :  ''
			
		}
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getProblemDepartmentsAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}

function showProblemsReportWithDepartments()
{
	var resultsDataSource = new YAHOO.util.DataSource(problemDetails.problemArray);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "problem"
		}, 	{
			key : "description"
		}, {
			key : "existingFrom"
		}, {
			key : "status"
		}, {
			key : "problemLocation"
		}, {
			key : "department"
		}, {
			key : "designation"
		}]   
	};	

	var resultsColumnDefs = [ {
		key : "problem",
		label : "Problem",
		sortable : true
	}, {
		key : "description",
		label : "Description",
		sortable : true,
		resizable:true
	}, {
		key : "existingFrom",
		label : "Reported Date",
		sortable : true
	}, {
		key : "status",
		label : "Problem Status",
		sortable : true
	}, {
		key : "problemLocation",
		label : "Problem Location",
		sortable : true
	}, {
		key : "department",
		label : "Department",
		sortable : true
	}, {
		key : "designation",
		label : "Designation",
		sortable : true
	}];	
	if(problemDetails.problemArray.length>25)
	{
		var myConfigs = {		
				paginator : new YAHOO.widget.Paginator({
				    rowsPerPage: 25,
				    template: "{PageLinks} Show {RowsPerPageDropdown} per page",
				    rowsPerPageOptions: [25,50,75,100], 
				    pageLinks: 25 
				    }),   
				};	
	}



	var myDataTable = new YAHOO.widget.DataTable("problemInfoDivBody",resultsColumnDefs, resultsDataSource,myConfigs);
	myDataTable.subscribe("linkClickEvent" ,function(oArgs){
		elLinkInDataTable = oArgs.target;
		record = this.getRecord(elLinkInDataTable);
		column = this.getColumn(elLinkInDataTable);
		record.setData(column);
		//buildMoreDetailsPopUp();	
		problemLocationId = record._oData.problemLocationId;
		var jsObj=
		{
				locationId:problemLocationId,
				task:"getProblemDetails"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementHistoryResults.action?"+rparam;						
		callAjax(rparam,jsObj,url);
	});
}

function initializeResultsTable() {

var resultsDataSource = new YAHOO.util.DataSource(problemDetails.problemArray);
resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
resultsDataSource.responseSchema = {
	fields : [ {
		key : "problem"
	}, 	{
		key : "description"
	}, {
		key : "existingFrom"
	}, {
		key : "status"
	},  {
		key : "problemLocation"
	}]   
};	

var resultsColumnDefs = [ {
	key : "problem",
	label : "Problem",
	sortable : true
}, {
	key : "description",
	label : "Description",
	sortable : true,
	resizable:true
}, {
	key : "existingFrom",
	label : "Reported Date",
	sortable : true
}, {
	key : "status",
	label : "Problem Status",
	sortable : true
}, {
	key : "problemLocation",
	label : "Problem Location",
	sortable : true
}];	
if(problemDetails.problemArray.length>25)
{
	var myConfigs = {		
			paginator : new YAHOO.widget.Paginator({
			    rowsPerPage: 25,
			    template: "{PageLinks} Show {RowsPerPageDropdown} per page",
			    rowsPerPageOptions: [25,50,75,100], 
			    pageLinks: 25 
			    }),   
			};	
}



var myDataTable = new YAHOO.widget.DataTable("problemInfoDivBody",resultsColumnDefs, resultsDataSource,myConfigs);
myDataTable.subscribe("linkClickEvent" ,function(oArgs){
	elLinkInDataTable = oArgs.target;
	record = this.getRecord(elLinkInDataTable);
	column = this.getColumn(elLinkInDataTable);
	record.setData(column);
	//buildMoreDetailsPopUp();	
	problemLocationId = record._oData.problemLocationId;
	var jsObj=
	{
			locationId:problemLocationId,
			task:"getProblemDetails"						
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/problemManagementHistoryResults.action?"+rparam;						
	callAjax(rparam,jsObj,url);
});

}

function buildProblemHistoryDataTable() {

	var resultsDataSource = new YAHOO.util.DataSource(problemDetails.problemHistoryArray);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "status"
		}, {
			key : "movedDate"
		},{
			key : "comments"
		},{
			key : "problemHistoryId"
		},{
			key : "isDelete"
		}]   
	};	

		

	var resultsColumnDefs = [ {
		key : "status",
		label : "Status",
		sortable : true,
		resizable:true
	}, {
		key : "movedDate",
		label : "Date",
		sortable : true,
		resizable:true
	},{
		key : "comments",
		label : "Comments If Any",
		sortable : true,
		resizable:true
	},{
		key : "problemHistoryId",
		label : "problemHistoryId",
		sortable : true,
		resizable:true,
		hidden:true	
	},{
		key : "isDelete",
		label : "isDelete",
		sortable : true,
		resizable:true,
		hidden:true	
	} ];	

	var myConfigs = {
		    paginator : new YAHOO.widget.Paginator({
		        rowsPerPage: 10
		    })
		};

	var myDataTable = new YAHOO.widget.DataTable("showProblems",resultsColumnDefs, resultsDataSource,myConfigs);
}

function showProblemsHistoryReport(results){
	assignToProblemsArray = new Array();

			for(var i in results)
			{
				var problemObj= {
						problemHistoryId:results[i].problemHistoryId,
						isDelete:results[i].isDelete,
						comments:results[i].comments,
						movedDate:results[i].movedDate,						
						status:results[i].status					
					};
				
				assignToProblemsArray.push(problemObj);
				problemDetails.problemHistoryArray=assignToProblemsArray;	
			}
			var emptyArr = new Array();
		    if(results.length == 0)
			{	
		    	problemDetails.problemHistoryArray = emptyArr;				
			}
		    buildMoreDetailsPopUp();
		    buildProblemHistoryDataTable();	
}


function  buildMoreDetailsPopUp()
{ 
		var elmt = document.getElementById('constituencyMgmtBodyDiv');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','createGroupmDiv');
		var problemName = record._oData.name;
		record._oData.name = problemName[0].toUpperCase();
		for(var i=1;i<problemName.length;i++){
			record._oData.name = record._oData.name+problemName[i];
		}
		var createGroupContentStr='';		
		createGroupContentStr+='<div align="center"><h3>Complete Report of <span style="color:green">'+record._oData.name+'</span> at<span style="color:blue"> '+record._oData.hamlet+'<span></h3></div>';
		createGroupContentStr+='<fieldset>';  		
		createGroupContentStr+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Details of the Problem</legend>';
		createGroupContentStr+='<table id="probDetailsTable">';
		createGroupContentStr+='<tr><th>Problem</th>';		
		createGroupContentStr+='<th>Description</th>';
		createGroupContentStr+='<th>IdentifiedDate</th></tr>';
		createGroupContentStr+='<tr><td>'+record._oData.name+'</td>';
		createGroupContentStr+='<td>'+record._oData.description+'</td>';
		createGroupContentStr+='<td>'+record._oData.existingFrom+'</td></tr></table>';
		createGroupContentStr+='</fieldset>';

		createGroupContentStr+='<fieldset>';
		createGroupContentStr+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Complained Person</legend>';		
		createGroupContentStr+='<table id="postedPersonTable">';
		createGroupContentStr+='<tr><th>Name</th>';
		createGroupContentStr+='<th>Mobile </th>';					
		createGroupContentStr+='<th>Phone </th>';	
		createGroupContentStr+='<th>Address  </th>';
		createGroupContentStr+='<th>Email</th></tr>';		
		createGroupContentStr+='<tr><td>'+record._oData.postedPersonName+'</td>';
		createGroupContentStr+='<td>'+record._oData.mobile+'</td>';		
		createGroupContentStr+='<td>'+record._oData.phone+'</td>';
		createGroupContentStr+='<td>'+record._oData.address+'</td>';					
		createGroupContentStr+='<td>'+record._oData.email+'</td></tr></table>';			
		createGroupContentStr+='</fieldset>';

		
		createGroupContentStr+='<fieldset>';
		createGroupContentStr+='<legend style="font-family:arial,helvetica,clean,sans-serif;">Assigned Department</legend>';		
		createGroupContentStr+='<table id="assignedDepartmentTable">';
		createGroupContentStr+='<tr><th>Department </th>';
		createGroupContentStr+='<th>Concerned Person</th>';					
		createGroupContentStr+='<th>Designation </th>';	
		createGroupContentStr+='<th>ContactNumber </th>';			
		createGroupContentStr+='<tr><td>'+record._oData.department+'</td>';
		createGroupContentStr+='<td>'+record._oData.departmentConcernedPersonName+'</td>';			
		createGroupContentStr+='<td>'+record._oData.designation+'</td>';				
		createGroupContentStr+='<td>'+record._oData.contactNo+'</td></tr></table>';			
		createGroupContentStr+='</fieldset>';
		
		createGroupContentStr+='<div id="showProblems" class="yui-skin-sam" align="center"></div>';
		
		divChild.innerHTML=createGroupContentStr;
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "600px", 		
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:400,
				  y:300,				  
				  buttons : [ { text:"Ok", handler: handleCreateGroupSubmit, isDefault:true}, 
	                          { text:"Cancel", handler: handleCreateGroupCancel}]
	             } );
		createGroupDialog.render();
}

function getProblemDataForSelectedProblem(id)
{
	var jsObj=
		{
				locationId:id,
				task:"getProblemData"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportResults.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function handleCreateGroupSubmit()
{
	createGroupDialog.hide();			
}

function handleCreateGroupCancel()
{
	this.cancel();
}

function callAjax(param,jsObj,url){
var myResults;	
var callback = {			
    success : function( o ) {
		try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);		
				if(jsObj.task == "getProblemDetails")
				{
					showProblemsHistoryReport(myResults);			
				}
				else if(jsObj.task == "departmentsByScope")
				{
					fillDeptSelect(myResults);			
				}
				else if(jsObj.task == "getProblemsBySelection")
				{
					showProblemsReport(myResults,jsObj);			
				}	
				else if(jsObj.task == "getProblemsGroupedBySelection")
				{
					showProblemsByDepartment(myResults,jsObj);
				}
				else if(jsObj.task == "getTotalProblemsCount")
				{
					buildProblemDetailsChart(myResults);
					getProblems('Total');
				}
				else if(jsObj.task == "getStatusWiseProblems")
				{
					buildResultDataTable(myResults);
				}
				else if(jsObj.task == "getTotalProblemsStatus")
				{
					buildStatusWiseProblemDetailsChart(myResults);
				}
				else if(jsObj.task == "getStatusWiseAllProblems")
				{
					buildResultDataTable(myResults);
				}
				else if(jsObj.task == "getCadreProblemsCountInARegion")
				{
					buildCadreProblemDetailsChart(myResults);
				}
				else if(jsObj.task == "getCadreProblemsInARegion")
				{
					buildResultDataTable(myResults);
				}
				else if(jsObj.task == "getCadreProblemsCountForAnUser")
				{
					buildCadreProblemDetailsChart(myResults);
				}
				else if(jsObj.task == "getDepartmentCategories")
				{
					fillDeptSelect(myResults);
				}
				else if(jsObj.task == "getDeptWiseProblemsCountForAnUser")
				{
					buildDepartmentWiseProblemsCountChart(myResults);
					buildDepartmentWiseProblemsCountTable(myResults);
					
					if(myResults != null && myResults.length > 0)
					getProblemsInADeptScopeBasedOnScope(myResults[0].problemSourceScopeId,'Total');
				}
				else if(jsObj.task == "getDepartmentWiseProblems")
				{
					buildResultDataTable(myResults);
				}
				else if(jsObj.task == "getProblemsInADeptScopeBasedOnScope")
				{
					buildResultDataTable(myResults);
				}
				else if(jsObj.task == "getDeptWiseProblemsStatusInALocation")
				{
					buildDepartmentWiseProblemsCountInARegionChart(myResults);
					buildDepartmentWiseProblemsCountInARegionTable(myResults);
					
					if(myResults != null && myResults.length > 0)
					getDeptProblemsBasedOnStatusInARegion('Total');
				}
				else if(jsObj.task == "getDeptProblemsBasedOnStatusInARegion")
				{
					buildResultDataTable(myResults);
				}
				else if(jsObj.task == "getDeptWiseProblemsBasedOnStatusInARegion")
				{
					buildResultDataTable(myResults);
				}
				else if(jsObj.task == "getCadreDetailsForProblemsInARegion")
				{
					buildCadreDetailsDataTable(myResults);
				}
				
		}catch (e) {   		
		   	alert("Invalid JSON result" + e);   
		}  
    },
    scope : this,
    failure : function( o ) {
     			//alert( "Failed to load result" + o.status + " " + o.statusText);
              }
    };

YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function showProblemsByDepartment(results,jsObj)
{	
	var elmt = document.getElementById("problemInfoDivBody");

	var str = '';
	for(var i=0; i<results.length; i++)
	{
		str += '<div id="problemsData_main_'+i+'" class="problemData_main">';
		str += '<div id="problemsData_head_'+i+'" class="problemData_head">Problems In '+results[i].classificationType+' Department</div>';
		str += '<div id="problemsData_body_'+i+'" class="problemData_body"><img src=""></img></div>';
		str += '</div>';
	}	

	elmt.innerHTML = str;

	for(var i=0; i<results.length; i++)
	{
		var resultsDataSource = new YAHOO.util.DataSource(results[i].problemsList);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "problem"
			}, 	{
				key : "description"
			}, {
				key : "existingFrom"
			}, {
				key : "status"
			}, {
				key : "problemLocation"
			}, {
				key : "department"
			}, {
				key : "designation"
			}]   
		};	

		var resultsColumnDefs = [ {
			key : "problem",
			label : "Problem",
			sortable : true
		}, {
			key : "description",
			label : "Description",
			sortable : true,
			resizable:true
		}, {
			key : "existingFrom",
			label : "Reported Date",
			sortable : true
		}, {
			key : "status",
			label : "Problem Status",
			sortable : true
		}, {
			key : "problemLocation",
			label : "Problem Location",
			sortable : true
		}, {
			key : "department",
			label : "Department",
			sortable : true
		}, {
			key : "designation",
			label : "Designation",
			sortable : true
		}];	
		if(results[i].problemsList.length>10)
		{
			var myConfigs = {		
					paginator : new YAHOO.widget.Paginator({
						rowsPerPage: 10,
						template: "{PageLinks} Show {RowsPerPageDropdown} per page",
						rowsPerPageOptions: [25,50,75,100], 
						pageLinks: 25 
						}),   
					};	
		}



		var myDataTable = new YAHOO.widget.DataTable("problemsData_body_"+i,resultsColumnDefs, resultsDataSource,myConfigs);
	}
}

function fillDeptSelect(results)
{
	clearOptionsListForSelectElmtId('deptsSelectField');
	fillOptionsForSelectedElmt('deptsSelectField', results);
	var tableEl = document.getElementById("deptsSelectTable");
	tableEl.style.display = '';
	
}

function showProbRegionsSelect()
{
	var probRegionSpanEl = document.getElementById("probRegionSpan");
	var statusSpanEl = document.getElementById("statusSpan");
	var deptRegionSpanEl = document.getElementById("deptRegionSpan");
	var tableEl = document.getElementById("deptsSelectTable");
	var problemInfoDivBodyEl = document.getElementById("problemInfoDivBody");
	var locationsTableEl = document.getElementById("locationsTable");
	var problemLocationOptionsDivEl = document.getElementById("problemLocationOptionsDiv");
	
	if(problemLocationOptionsDivEl.style.display == 'none')
		problemLocationOptionsDivEl.style.display = 'block';
	if(locationsTableEl.style.display == 'none')
		locationsTableEl.style.display = 'block';
	if(probRegionSpanEl.style.display == 'none')
		probRegionSpanEl.style.display = 'block';
	if(statusSpanEl.style.display == 'block')
		statusSpanEl.style.display = 'none';
	if(deptRegionSpanEl.style.display == 'block')
		deptRegionSpanEl.style.display = 'none';
	tableEl.style.display = 'none';	
	var errorDivEl = document.getElementById("errorDiv");
	errorDivEl.innerHTML = '';	
	problemInfoDivBodyEl.innerHTML = '';
}

function showProbStatusSelect(cadStr)
{
	var statusSpanEl = document.getElementById("statusSpan");
	var probRegionSpanEl = document.getElementById("probRegionSpan");
	var deptRegionSpanEl = document.getElementById("deptRegionSpan");
	var tableEl = document.getElementById("deptsSelectTable");
	var locationsTableEl = document.getElementById("locationsTable");
	var problemLocationOptionsDivEl = document.getElementById("problemLocationOptionsDiv");
	var problemInfoDivBodyEl = document.getElementById("problemInfoDivBody");
	if(statusSpanEl.style.display == 'none')
		statusSpanEl.style.display = 'block';
	if(cadStr == 'cadre')
		statusSpanEl.style.display = 'none'
	if(probRegionSpanEl.style.display == 'block')
		probRegionSpanEl.style.display = 'none';
	if(deptRegionSpanEl.style.display == 'block')
		deptRegionSpanEl.style.display = 'none';
	tableEl.style.display = 'none';	
	locationsTableEl.style.display = 'none';
	problemLocationOptionsDivEl.style.display = 'none';
	var errorDivEl = document.getElementById("errorDiv");
	errorDivEl.innerHTML = '';	
	problemInfoDivBodyEl.innerHTML = '';
}

function showDeptRegionsSelect()
{
	var statusSpanEl = document.getElementById("statusSpan");
	var probRegionSpanEl = document.getElementById("probRegionSpan");
	var deptRegionSpanEl = document.getElementById("deptRegionSpan");
	var locationsTableEl = document.getElementById("locationsTable");
	var problemLocationOptionsDivEl = document.getElementById("problemLocationOptionsDiv");
	var problemInfoDivBodyEl = document.getElementById("problemInfoDivBody");
	if(deptRegionSpanEl.style.display == 'none')
		deptRegionSpanEl.style.display = 'block';
	if(statusSpanEl.style.display == 'block')
		statusSpanEl.style.display = 'none';
	if(probRegionSpanEl.style.display == 'block')
		probRegionSpanEl.style.display = 'none';
	locationsTableEl.style.display = 'none';
	problemLocationOptionsDivEl.style.display = 'none';	
	var errorDivEl = document.getElementById("errorDiv");
	errorDivEl.innerHTML = '';
	problemInfoDivBodyEl.innerHTML = '';	
}

function getDepartmentsByScope(scope)
{	  
  var jsObj={
	scope: scope,
	task: "departmentsByScope"
	};
  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
  var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
  callAjax(rparam,jsObj,url);		  
}

function populateLocations(val,source)
{
	if(val == 0)
	{
		alert("Select valid Problem Scope");
		return;
	}
	var row1El = document.getElementById("row1");
	var row2El = document.getElementById("row2");
	var row3El = document.getElementById("row3");
	var row4El = document.getElementById("row4");
	var row5El = document.getElementById("row5");
	var row6El = document.getElementById("row6");
	var hiddenEl = document.getElementById("problemLocation");
	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s"); 
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var selectedConst = 0;
	var selectedDistrict = 0;
	var locationsTableEl = document.getElementById("locationsTable");
	var hamletFieldEl = document.getElementById("hamletField_s");
	var pConstituencyFieldEl = document.getElementById("pConstituencyField_s");
	var boothFieldEl = document.getElementById("boothField_s");
	var problemLocationOptionsDivEl = document.getElementById("problemLocationOptionsDiv"); 
	row1El.style.display = 'none';
	row2El.style.display = 'none';
	row3El.style.display = 'none';
	row4El.style.display = 'none';
	row5El.style.display = 'none';
	row6El.style.display = 'none';
	hiddenEl.value='';
	problemLocationOptionsDivEl.style.display = 'block';
	if(districtFieldEl && districtFieldEl.options.length > 0)
		selectedDistrict = districtFieldEl.options[districtFieldEl.selectedIndex].value;	
	if(constituencyFieldEl.options.length > 0)
		 selectedConst = constituencyFieldEl.options[constituencyFieldEl.selectedIndex].value; 
	
	if(locationsTableEl.style.display == 'none')
		locationsTableEl.style.display = '';
	var value = val;
	if(source == 'onLoad')
	{
		setLocationValue(accessValue,'onLoad')
	} else if(source == 'onChange')
	{	
		hiddenEl.value='';
		if(accessType == 'COUNTRY')
		{
			stateFieldEl.selectedIndex = '0';
			districtFieldEl.selectedIndex = '0';
			constituencyFieldEl.selectedIndex = '0';
			mandalFieldEl.selectedIndex = '0';
			hamletFieldEl.selectedIndex = '0';
		} else if(accessType == 'STATE')
		{
			districtFieldEl.selectedIndex = '0';
			constituencyFieldEl.selectedIndex = '0';
			mandalFieldEl.selectedIndex = '0';
			hamletFieldEl.selectedIndex = '0';					
		} else if(accessType == 'DISTRICT' || accessType == 'MP')
		{
			constituencyFieldEl.selectedIndex = '0';
			mandalFieldEl.selectedIndex = '0';
			hamletFieldEl.selectedIndex = '0';
			getSubRegionsInDistrict(selectedDistrict,'cadreReg','constituencyField_s','cadreLevel')
		} else if(accessType == 'MLA')
		{
			mandalFieldEl.selectedIndex = '0';
			hamletFieldEl.selectedIndex = '0';
		}
	}
	if(value == 1)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		
	} else if(value == 2)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
	} else if(value == 3)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';					
	} else if(value == 4)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';			
	} else if(value == 5)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 6)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 7)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';				
	} else if(value == 8)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			 
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';
		if(row5El.style.display == 'none')
			row5El.style.display = '';			
	} else if(value == 9)
	{
		if(row1El.style.display == 'none')
			row1El.style.display = '';			
		if(row2El.style.display == 'none')
			row2El.style.display = '';
		if(row3El.style.display == 'none')
			row3El.style.display = '';
		if(row4El.style.display == 'none')
			row4El.style.display = '';			
	}
	
	setLocationValue(val,'onChange');
}

var accessType = "${accessType}";
var scope = '${scope}';
var accessValue = "${accessValue}";
function setLocationValue(value, source)
{
	if(value == '0')
	{
		alert("Please Select Valid Location");
		return;
	}	
	var hiddenEl = document.getElementById("problemLocation");
	var scopeLevelEl = document.getElementById("scopeLevel");
	var stateFieldEl = document.getElementById("stateField_s");
	var districtFieldEl = document.getElementById("districtField_s");
	var constituencyFieldEl = document.getElementById("constituencyField_s");
	var mandalFieldEl = document.getElementById("mandalField_s");
	var hamletFieldEl = document.getElementById("hamletField_s");
	var boothFieldEl = document.getElementById("boothField_s");

	hiddenEl.value = '';
	var scopeLevelElVal = scopeLevelEl.options[scopeLevelEl.selectedIndex].value;
	if(stateFieldEl.options.length > 0)
		var selectedState = stateFieldEl.options[stateFieldEl.selectedIndex].value;
	if(districtFieldEl.options.length > 0)
		var selectedDistrict = districtFieldEl.options[districtFieldEl.selectedIndex].value;
	if(constituencyFieldEl.options.length > 0)
		 var selectedConstituency = constituencyFieldEl.options[constituencyFieldEl.selectedIndex].value;
	if(mandalFieldEl.options.length > 0)
		var selectedMandal = mandalFieldEl.options[mandalFieldEl.selectedIndex].value;
	if(hamletFieldEl.options.length > 0)
		var selectedHamlet = hamletFieldEl.options[hamletFieldEl.selectedIndex].value;
	if(boothFieldEl.options.length > 0)
		var selectedBooth = boothFieldEl.options[boothFieldEl.selectedIndex].value;
	if(source == 'onChange'){

		if(scopeLevelElVal == 2 && selectedState != 0)
		{	
			hiddenEl.value = selectedState;
		}	
		if(scopeLevelElVal == 3 && selectedDistrict != 0)
		{
			hiddenEl.value = selectedDistrict;
		}		
		if(scopeLevelElVal == 4 && selectedConstituency != 0)
		{
			hiddenEl.value = selectedConstituency;
		}	
		if((scopeLevelElVal == 5 || scopeLevelElVal == 7) && selectedMandal != 0)
		{
			hiddenEl.value = selectedMandal;
		}	
		if((scopeLevelElVal == 6 || scopeLevelElVal == 8) && selectedHamlet != 0)
		{
			hiddenEl.value = selectedHamlet;
		}	
		if(scopeLevelElVal == 9 && selectedBooth != 0)
		{
			hiddenEl.value = selectedBooth;
		}				
		
	} else if(source == 'onLoad'){
		hiddenEl.value = value
		
	}
	
}

function clearReportBody()
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBody");
	var deptWiseProblemInfoDivBodyEle = document.getElementById("deptWiseProblemInfoDivBody");
	var statusWiseProblemInfoDivBodyEle = document.getElementById("statusWiseProblemInfoDivBody");
	
	var str = '';
	if(problemInfoDivBodyEle)
		problemInfoDivBodyEle.innerHTML = str;

	if(deptWiseProblemInfoDivBodyEle)
		deptWiseProblemInfoDivBodyEle.innerHTML = str;

	if(statusWiseProblemInfoDivBodyEle)
		statusWiseProblemInfoDivBodyEle.innerHTML = str;
}

function buildDepartmentWiseProblemsCountInARegionTable(result)
{
	var deptWiseProblemInfoDivBodyEle = document.getElementById("deptWiseProblemInfoDivBody");
	
	if(!deptWiseProblemInfoDivBodyEle || result == null || result.length == 0)
		return

	var str = '';

	str +='<div id="deptProblemDetails_main" style="width:900px;">';
	str +='	<div id="deptProblemDetails_head">';
	str +='		<table cellpadding="0" cellspacing="0" width="100%">';
	str +='			<tr>';
	str +='			<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='			<td><div class="widgetHeader"><span class="widgetHeaderSpan">Status Wise problems Count In All Deaprtments</span></div></td>';
	str +='			<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	str +='	<div id="deptProblemDetails_body" class="problemDetailsBody">';
	str +='		<table class="problemDetailsBody_table">';
	
	for(var i=0; i<result.length;i++)
	{
		str +='<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
		str +='	<th style="width:200px;"><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >'+result[i].deptName+'</th>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Total</th>';
		str +='	<td><a onClick="getDeptWiseProblemsBasedOnStatusInARegion('+result[i].departmentId+',\'Total\')" class="anchorStyle" >'+result[i].departments[4].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >New</th>';
		str +='	<td><a onClick="getDeptWiseProblemsBasedOnStatusInARegion('+result[i].departmentId+',\'NEW\')" class="anchorStyle" >'+result[i].departments[0].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Progress</th>';
		str +='	<td><a onClick="getDeptWiseProblemsBasedOnStatusInARegion('+result[i].departmentId+',\'PROGRESS\')" class="anchorStyle" >'+result[i].departments[1].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Pending</th>';
		str +='	<td><a onClick="getDeptWiseProblemsBasedOnStatusInARegion('+result[i].departmentId+',\'PENDING\')" class="anchorStyle" >'+result[i].departments[2].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Fixed</th>';
		str +='	<td><a onClick="getDeptWiseProblemsBasedOnStatusInARegion('+result[i].departmentId+',\'FIXED\')" class="anchorStyle" >'+result[i].departments[3].id+'</a></td>';
		str +='</tr>';
	}

	str +='		</table>';
	str +=' </div>';
	str +='</div>';

	deptWiseProblemInfoDivBodyEle.innerHTML = str;
	
}

function buildCadreDetailsDataTable(result)
{
	var deptWiseProblemInfoDivBodyEle = document.getElementById("deptWiseProblemInfoDivBody");
	
	if(!deptWiseProblemInfoDivBodyEle || result == null || result.length == 0)
		return

	var str = '';

	str += '<div id="stProblemDetails_head">';
	str +='<table cellpadding="0" cellspacing="0" width="100%">';
	str +='	<tr>';
	str +='		<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='		<td><div class="widgetHeader"><span class="widgetHeaderSpan"> Problem Details </span></div></td>';
	str +='		<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='	</tr>';
	str +='</table>';
	str += '</div>';
	str += '<div id="stProblemDetails_body" class="yui-skin-sam"></div>';

	deptWiseProblemInfoDivBodyEle.innerHTML = str;
	YAHOO.widget.DataTable.cadreLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var cadreName = oData;
		if(cadreName != null)
		{
			var cadreId= oRecord.getData("problemId");
			elLiner.innerHTML ="<a href='javascript:{}' onClick='getCadreInfo("+cadreId+")'>"+cadreName+"</a>";
		}
	};
	
	YAHOO.widget.DataTable.total = function(elLiner, oRecord, oColumn, oData) 
	{
		var count = new Array();
		count = oRecord.getData("departments");
		elLiner.innerHTML ="<a href='javascript:{}'>"+count[4].id+"</a>";
		
	};
	YAHOO.widget.DataTable.pending = function(elLiner, oRecord, oColumn, oData) 
	{
		var count = new Array();
		count = oRecord.getData("departments");
		elLiner.innerHTML ="<a href='javascript:{}'>"+count[2].id+"</a>";
		
	};
	YAHOO.widget.DataTable.fixed = function(elLiner, oRecord, oColumn, oData) 
	{
		var count = new Array();
		count = oRecord.getData("departments");
		elLiner.innerHTML ="<a href='javascript:{}'>"+count[3].id+"</a>";
		
	};
	YAHOO.widget.DataTable.new = function(elLiner, oRecord, oColumn, oData) 
	{
		var count = new Array();
		count = oRecord.getData("departments");
		elLiner.innerHTML ="<a href='javascript:{}'>"+count[0].id+"</a>";
		
	};
	YAHOO.widget.DataTable.progress = function(elLiner, oRecord, oColumn, oData) 
	{
		var count = new Array();
		count = oRecord.getData("departments");
		elLiner.innerHTML ="<a href='javascript:{}'>"+count[1].id+"</a>";
		
	};
	
	var myColumnDefs = [
            {key:"deptName",label:"Cadre Name",sortable:true,formatter:YAHOO.widget.DataTable.cadreLink},
            {label:"Total",formatter:YAHOO.widget.DataTable.total,sortable:true},
            {label:"Fixed",formatter:YAHOO.widget.DataTable.fixed,sortable:true},
			{label:"New", formatter:YAHOO.widget.DataTable.new, sortable:true},
			{label:"Progress",formatter:YAHOO.widget.DataTable.progress,sortable:true},
            {label:"Pending", formatter:YAHOO.widget.DataTable.pending, sortable:true}
     	    ];
			
			var myDataSource = new YAHOO.util.DataSource(result);
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
			myDataSource.responseSchema = {
				fields: ["deptName","departments","problemId"]
			};
			
			var myConfigs = { 
					paginator : new YAHOO.widget.Paginator({ 
					rowsPerPage    : 20,		        
					pageLinks: 20
					}) 
					};	
                      
			var myDataTable = new YAHOO.widget.DataTable("stProblemDetails_body",
					myColumnDefs, myDataSource,myConfigs);
					

			return {
				oDS: myDataSource,
				oDT: myDataTable
			};
}

function buildDepartmentWiseProblemsCountTable(result)
{
	var deptWiseProblemInfoDivBodyEle = document.getElementById("deptWiseProblemInfoDivBody");
	
	if(!deptWiseProblemInfoDivBodyEle || result == null || result.length == 0)
		return

	var str = '';

	str +='<div id="deptProblemDetails_main" style="width:900px;">';
	str +='	<div id="deptProblemDetails_head">';
	str +='		<table cellpadding="0" cellspacing="0" width="100%">';
	str +='			<tr>';
	str +='			<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='			<td><div class="widgetHeader"><span class="widgetHeaderSpan">Status Wise problems Count In All Deaprtments</span></div></td>';
	str +='			<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	str +='	<div id="deptProblemDetails_body" class="problemDetailsBody">';
	str +='		<table class="problemDetailsBody_table">';
	
	for(var i=0; i<result.length;i++)
	{
		str +='<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
		str +='	<th style="width:200px;"><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >'+result[i].deptName+'</th>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Total</th>';
		str +='	<td><a onClick="getDeptWiseProblems('+result[i].departmentId+',\'Total\')" class="anchorStyle" >'+result[i].departments[4].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >New</th>';
		str +='	<td><a onClick="getDeptWiseProblems('+result[i].departmentId+',\'NEW\')" class="anchorStyle" >'+result[i].departments[0].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Progress</th>';
		str +='	<td><a onClick="getDeptWiseProblems('+result[i].departmentId+',\'PROGRESS\')" class="anchorStyle" >'+result[i].departments[1].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Pending</th>';
		str +='	<td><a onClick="getDeptWiseProblems('+result[i].departmentId+',\'PENDING\')" class="anchorStyle" >'+result[i].departments[2].id+'</a></td>';
		str +='	<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Fixed</th>';
		str +='	<td><a onClick="getDeptWiseProblems('+result[i].departmentId+',\'FIXED\')" class="anchorStyle" >'+result[i].departments[3].id+'</a></td>';
		str +='</tr>';
	}

	str +='		</table>';
	str +=' </div>';
	str +='</div>';

	deptWiseProblemInfoDivBodyEle.innerHTML = str;
	
}

function buildDepartmentWiseProblemsCountInARegionChart(result)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBody");
	
	if(!problemInfoDivBodyEle || result == null || result.length == 0)
		return
    
	var total = 0,newp = 0,prog = 0,pend = 0,fix = 0;

	for(var i=0;i<result.length;i++)
	{
		total += result[i].problemsCount;

		for(var j=0;j<result[i].departments.length;j++)
		{
			var name = result[i].departments[j].name;
			var count = result[i].departments[j].id;

			if(name == 'NEW')
				newp += count;
			else if(name == 'PROGRESS')
				prog += count;
			else if(name == 'PENDING')
				pend += count;
			else if(name == 'FIXED')
				fix += count;
		}
	}

	var str = '';

	str +='<div id="problemDetails_main" >';
	str +='	<div id="problemDetails_head">';
	str +='		<table cellpadding="0" cellspacing="0" width="100%">';
	str +='			<tr>';
	str +='			<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='			<td><div class="widgetHeader"><span class="widgetHeaderSpan">Total Problems In All Departments - <a onClick="getDeptProblemsBasedOnStatusInARegion(\'Total\')" class="anchorStyle" > '+total+'</a></span></div></td>';
	str +='			<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	
	str +='	<div id="problemDetails_body" class="problemDetailsBody" >';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">New </th><td><a onClick="getDeptProblemsBasedOnStatusInARegion(\'NEW\')" class="anchorStyle" >'+newp+'</a></td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">Progress </th><td><a onClick="getDeptProblemsBasedOnStatusInARegion(\'PROGRESS\')" class="anchorStyle" >'+prog+'</a></td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">Pending </th><td><a onClick="getDeptProblemsBasedOnStatusInARegion(\'PENDING\')" class="anchorStyle" >'+pend+'</a></td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">Fixed </th><td><a onClick="getDeptProblemsBasedOnStatusInARegion(\'FIXED\')" class="anchorStyle" >'+fix+'</a></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='				</td></tr>';
	str +='			<tr><div id="problemDetails_body_table"></tr>';
	str +='		</table>';
	str +='</div>';

	str +='</div>';

	problemInfoDivBodyEle.innerHTML = str;

	var data = new google.visualization.DataTable();
       
		data.addColumn('string','Department');
		data.addColumn('number','New');
        data.addColumn('number','Progress');
        data.addColumn('number','Pending');
		data.addColumn('number','Fixed');

		data.addRows(result.length);
				
		for(var i=0;i<result.length;i++)
		{
			data.setValue(i,0,result[i].deptName);
			data.setValue(i,1,result[i].departments[0].id);
			data.setValue(i,2,result[i].departments[1].id);
			data.setValue(i,3,result[i].departments[2].id);
			data.setValue(i,4,result[i].departments[3].id);
		}
		
		var chart =  new google.visualization.ColumnChart(document.getElementById('problemDetails_body_table'));
        chart.draw(data, {width: 800, height: 300, title: 'Department Wise Problems'}); 

	return;
}


function buildDepartmentWiseProblemsCountChart(result)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBody");
	
	if(!problemInfoDivBodyEle || result == null || result.length == 0)
		return
    
	var total = 0,newp = 0,prog = 0,pend = 0,fix = 0;

	for(var i=0;i<result.length;i++)
	{
		total += result[i].problemsCount;

		for(var j=0;j<result[i].departments.length;j++)
		{
			var name = result[i].departments[j].name;
			var count = result[i].departments[j].id;

			if(name == 'NEW')
				newp += count;
			else if(name == 'PROGRESS')
				prog += count;
			else if(name == 'PENDING')
				pend += count;
			else if(name == 'FIXED')
				fix += count;
		}
	}

	var str = '';

	str +='<div id="problemDetails_main">';
	str +='	<div id="problemDetails_head">';
	str +='		<table cellpadding="0" cellspacing="0" width="100%">';
	str +='			<tr>';
	str +='			<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='			<td><div class="widgetHeader"><span class="widgetHeaderSpan">Total Problems In All Departments - <a onClick="getProblemsInADeptScopeBasedOnScope('+result[0].problemSourceScopeId+',\'Total\')" class="anchorStyle" > '+total+'</a></span></div></td>';
	str +='			<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	
	str +='	<div id="problemDetails_body" class="problemDetailsBody" >';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">New </th><td><a onClick="getProblemsInADeptScopeBasedOnScope('+result[0].problemSourceScopeId+',\'NEW\')" class="anchorStyle" >'+newp+'</a></td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">Progress </th><td><a onClick="getProblemsInADeptScopeBasedOnScope('+result[0].problemSourceScopeId+',\'PROGRESS\')" class="anchorStyle" >'+prog+'</a></td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">Pending </th><td><a onClick="getProblemsInADeptScopeBasedOnScope('+result[0].problemSourceScopeId+',\'PENDING\')" class="anchorStyle" >'+pend+'</a></td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img">Fixed </th><td><a onClick="getProblemsInADeptScopeBasedOnScope('+result[0].problemSourceScopeId+',\'FIXED\')" class="anchorStyle" >'+fix+'</a></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='				</td></tr>';
	str +='			<tr><div id="problemDetails_body_table"></tr>';
	str +='		</table>';
	str +='</div>';

	str +='</div>';

	problemInfoDivBodyEle.innerHTML = str;
	
	var data = new google.visualization.DataTable();
       
		data.addColumn('string','Department');
		data.addColumn('number','New');
        data.addColumn('number','Progress');
        data.addColumn('number','Pending');
		data.addColumn('number','Fixed');

		data.addRows(result.length);
				
		for(var i=0;i<result.length;i++)
		{
			data.setValue(i,0,result[i].deptName);
			data.setValue(i,1,result[i].departments[0].id);
			data.setValue(i,2,result[i].departments[1].id);
			data.setValue(i,3,result[i].departments[2].id);
			data.setValue(i,4,result[i].departments[3].id);
		}
		
		var chart =  new google.visualization.ColumnChart(document.getElementById('problemDetails_body_table'));
        chart.draw(data, {width: 800, height: 300, title: 'Department Wise Problems'}); 
                         
	return;
}

function buildCadreProblemDetailsChart(result)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBody");

	if(!problemInfoDivBodyEle || result == null || result.length == 0)
		return;
	var tot,personal,assigned,both;

	for(var i=0;i<result.length;i++)
	{
		if(result[i].name == 'Total')
			tot = result[i].id;
		else if(result[i].name == 'PERSONAL')
			personal = result[i].id;
		else if(result[i].name == 'ASSIGNED')
			assigned = result[i].id;
		else if(result[i].name == 'Both')
			both = result[i].id;
	}

	var str = '';
	
	str +='<div id="problemDetails_main" style="width:700px;" >';
	str +='	<div id="problemDetails_head">';
	str +='		<table cellpadding="0" cellspacing="0" width="100%">';
	str +='			<tr>';
	str +='			<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='			<td><div class="widgetHeader"><span class="widgetHeaderSpan"> Total Cadre Problems - <a onClick="getCadreProblemsInaRegion(\'Total\')" class="anchorStyle">'+tot+'</a> </span></div></td>';
	str +='			<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	str +='	<div id="problemDetails_body" class="problemDetailsBody">';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top"  width="450px">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Cadre Personal </th>';if(personal == 0)
	str += '                <td >'+personal+'</td>';
	                           else
	str += '                <td> <a onClick="getCadreProblemsInaRegion(\'PERSONAL\')" class="anchorStyle" >'+personal+'</a></td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Cadre Assigned</th><td><a onClick="getCadreProblemsInaRegion(\'ASSIGNED\')" class="anchorStyle">'+assigned+'</a></td>';
	str +='					</tr>';
	str +='					</table>';
	str +='				</td>';
	str +='			</tr>';
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<div id="statusWiseProbGraphDiv">';
	str +='					</div>';
	str +='				</td>';
	str +='			</tr>';
	str +='		</table>';
	str +='</div>';
	str +='</div>';

	problemInfoDivBodyEle.innerHTML = str;

	var data = new google.visualization.DataTable();
	data.addColumn('string');
    data.addColumn('number');
	data.addRows(4);

	data.setValue(0, 0, " Personal ");
	data.setValue(0, 1, personal);
	data.setValue(1, 0, " Assigned ");
	data.setValue(1, 1, assigned);
	
	var chart = new google.visualization.PieChart(document.getElementById('statusWiseProbGraphDiv'));
        chart.draw(data, {width: 450, height: 250, title: 'Cadre Problems'});
}


function buildStatusWiseProblemDetailsChart(result)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBody");

	if(!problemInfoDivBodyEle || result == null || result.length == 0)
		return;
	var tot,newp,prog,pend,fix;

	for(var i=0;i<result.length;i++)
	{
		if(result[i].name == 'Total')
			tot = result[i].id;
		else if(result[i].name == 'NEW')
			newp = result[i].id;
		else if(result[i].name == 'PROGRESS')
			prog = result[i].id;
		else if(result[i].name == 'PENDING')
			pend = result[i].id;
		else if(result[i].name == 'FIXED')
			fix = result[i].id;
	}

	var str = '';
	
	str +='<div id="problemDetails_main" style="width:550px;" >';
	str +='	<div id="problemDetails_head">';
	str +='		<table cellpadding="0" cellspacing="0" width="100%">';
	str +='			<tr>';
	str +='			<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='			<td><div class="widgetHeader"><span class="widgetHeaderSpan"> Total Problem Posted - '+tot+' </span></div></td>';
	str +='			<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	str +='	<div id="problemDetails_body" class="problemDetailsBody">';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top"  width="150px">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >New</th><td><a onClick="getStatusWiseAllProblems(\'NEW\')" class="anchorStyle" >'+newp+'</td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Progress</th><td><a onClick="getStatusWiseAllProblems(\'PROGRESS\')" class="anchorStyle">'+prog+'</td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Pending</th><td><a onClick="getStatusWiseAllProblems(\'PENDING\')" class="anchorStyle" >'+pend+'</td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Fixed</th><td><a onClick="getStatusWiseAllProblems(\'FIXED\')" class="anchorStyle" >'+fix+'</td>';
	str +='					</tr>';
	str +='					</table>';
	str +='				</td>';
	str +='			</tr>';
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<div id="statusWiseProbGraphDiv">';
	str +='					</div>';
	str +='				</td>';
	str +='			</tr>';
	str +='		</table>';
	str +='</div>';
	str +='</div>';

	problemInfoDivBodyEle.innerHTML = str;

	var data = new google.visualization.DataTable();
	data.addColumn('string');
    data.addColumn('number');
	data.addRows(4);

	data.setValue(0, 0, " New ");
	data.setValue(0, 1, newp);
	data.setValue(1, 0, " Progress ");
	data.setValue(1, 1, prog);
	data.setValue(2, 0, " Pending ");
	data.setValue(2, 1, pend);
	data.setValue(3, 0, " Fixed ");
	data.setValue(3, 1, fix);

	var chart = new google.visualization.PieChart(document.getElementById('statusWiseProbGraphDiv'));
        chart.draw(data, {width: 400, height: 250, title: 'Status Wise All Problems'});
}

function buildProblemDetailsChart(result)
{
	var problemInfoDivBodyEle = document.getElementById("problemInfoDivBody");

	if(!problemInfoDivBodyEle || result == null || result.length == 0)
		return;
	var tot,newp,prog,pend,fix;
	
	for(var i=0;i<result.length;i++)
	{
		if(result[i].name == 'Total')
			tot = result[i].id;
		else if(result[i].name == 'NEW')
			newp = result[i].id;
		else if(result[i].name == 'PROGRESS')
			prog = result[i].id;
		else if(result[i].name == 'PENDING')
			pend = result[i].id;
		else if(result[i].name == 'FIXED')
			fix = result[i].id;
	}
	
	var str = '';
	
	
	str +='<div id="problemDetails_main">';
	str +='<Table width="100%">';
	str +='<tr><td valign="top">';
	str +='	<div id="problemDetails_head">';
	str +='<table cellpadding="0" cellspacing="0" width="100%">';
	str +='<tr>';
	str +='<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='<td><div class="widgetHeader"><span class="widgetHeaderSpan"> Total Problems - <a onClick="getProblems(\'Total\')" class="anchorStyle">'+tot+'</a></span></div></td>';
	str +='<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='</tr>';
	str +='</table>';
	str +=' </div>';
	str +='	<div id="problemDetails_body" class="problemDetailsBody">';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top" width="150px">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Open Problems</th><td>'+(tot-fix)+'</td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Fixed Problems</th><td><a onClick="getProblems(\'FIXED\')" class="anchorStyle" >'+fix+'</a></td>';
	str +='					</tr>';
	str +='					</table>';
	str +='				</td>';
	str +='				</tr>'
	str +='				<tr>'
	str +='				<td valign="top">';
	str +='					<div id="totalProbGraphDiv">';
	str +='					</div>';
	str +='				</td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	str +='</div>';

	str +='</td>';
	str +='<td valign="top">';
	
	str +='<div id="problemStatusDetails_main">';
	str +='	<div id="problemStatusDetails_head">';
	str +='<table cellpadding="0" cellspacing="0" width="100%">';
	str +='<tr>';
	str +='<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='<td><div class="widgetHeader"><span class="widgetHeaderSpan">Status Wise Problem Details </span></div></td>';
	str +='<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='</tr>';
	str +='</table>';
	str +='</div>';
	str +='	<div id="problemStatusDetails_body" class="problemDetailsBody">';
	str +='		<table>';
	str +='			<tr>';
	str +='				<td valign="top"  width="150px">';
	str +='					<table class="problemDetailsBody_table">';
	str +='					<tr style="background-image:url(\'images/icons/electionResultsAnalysisReport/mid.png\');">';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >New</th><td><a onClick="getProblems(\'NEW\')" class="anchorStyle" >'+newp+'</td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Progress</th><td><a onClick="getProblems(\'PROGRESS\')" class="anchorStyle">'+prog+'</td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Pending</th><td><a onClick="getProblems(\'PENDING\')" class="anchorStyle" >'+pend+'</td>';
	str +='					<th><img src="images/icons/districtPage/listIcon.png" class="problemDetailsBody_table_img" >Fixed</th><td><a onClick="getProblems(\'FIXED\')" class="anchorStyle" >'+fix+'</td>';
	str +='					</tr>';
	str +='					</table>';
	str +='				</td>';
	str +='			</tr>';
	str +='			<tr>';
	str +='				<td valign="top">';
	str +='					<div id="statusWiseProbGraphDiv">';
	str +='					</div>';
	str +='				</td>';
	str +='			</tr>';
	str +='		</table>';
	str +='	</div>';
	str +='</td></tr>';
	str +='</Table>';
	str +='</div>';
	
	problemInfoDivBodyEle.innerHTML = str;

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Range');
    data.addColumn('number', 'Constituencies');
	data.addRows(2);

	data.setValue(0, 0, "Open Problems");
	data.setValue(0, 1, tot-fix);
	data.setValue(1, 0, "Fixed Problems");
	data.setValue(1, 1, fix);

	var chart = new google.visualization.PieChart(document.getElementById('totalProbGraphDiv'));
        chart.draw(data, {width: 400, height: 250, title: 'Open Problems / Fixed Problems'});

	var data = new google.visualization.DataTable();
	data.addColumn('string');
    data.addColumn('number');
	data.addRows(4);

	data.setValue(0, 0, " New ");
	data.setValue(0, 1, newp);
	data.setValue(1, 0, " Progress ");
	data.setValue(1, 1, prog);
	data.setValue(2, 0, " Pending ");
	data.setValue(2, 1, pend);
	data.setValue(3, 0, " Fixed ");
	data.setValue(3, 1, fix);

	var chart = new google.visualization.PieChart(document.getElementById('statusWiseProbGraphDiv'));
        chart.draw(data, {width: 400, height: 250, title: 'Status Wise Problems'});

}

function getProblems(status)
{
	var problemOptionEl = document.getElementsByName("problemOption");
	var problemLocationOptionEl = document.getElementsByName("problemLocationOption");
	var hiddenEl = document.getElementById("problemLocation");
	var statusListEl = document.getElementById("statusList");
	var deptsSelectFieldEl = document.getElementById("deptsSelectField");
	var scopeLevelEl = document.getElementById("scopeLevel");
	var selectedLocation;
	var selectedOption = '';
	var selectedStatus;
	var status;
	var selectedDept;
	var selectedProblemScope;
	var selectedSortOption;
	var task ='';
	var subTask = '';
	var errorDivEl = document.getElementById("errorDiv");
	var groupByDept = false;
	var groupByCadre = false;
	var statusWise = '';
	errorDivEl.innerHTML = '';
	
	for(var i = 0; i<problemOptionEl.length; i++)
	{
		if(problemOptionEl[i].checked == true)
			selectedOption = problemOptionEl[i].value;
	}
	if(selectedOption == '')
	{
		errorDivEl.innerHTML = 'Select A Problem Search Option';
		return;
	}
	for(var j = 0; j<problemLocationOptionEl.length; j++)
	{
		if(problemLocationOptionEl[j].checked == true)
			selectedSortOption = problemLocationOptionEl[j].value;
	}

	if(selectedOption == 'location')
	{
		selectedProblemScope = scopeLevelEl.options[scopeLevelEl.selectedIndex].value;
		selectedLocation = hiddenEl.value;
		
		if(selectedSortOption == 'all')
		{

			if(status != null)
			{
				statusWise = status;
				task = "getStatusWiseProblems";
			}
			else
			{
				task = "getTotalProblemsCount";
			}
				

			var jsObj=
			{
				selectedProblemScope : selectedProblemScope,
				selectedLocation	 : selectedLocation,
					task			 : task,
					status			 : statusWise

			}

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			if(task == 'getTotalProblemsCount')
			{
				var url = "<%=request.getContextPath()%>/getTotalProblemsCountInaRegionAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
			}

			if(task == 'getStatusWiseProblems')
			{
				var url = "<%=request.getContextPath()%>/getStatusWiseProblemsInaRegionAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
			}	
			return;
		}

		if(selectedSortOption == 'departmentwise')
		{
			var jsObj=
			{
				selectedProblemScope : selectedProblemScope,
				selectedLocation	 : selectedLocation,
					task			 : "getDeptWiseProblemsStatusInALocation",
			}

			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getStatusWiseProblemsInaRegionAction.action?"+rparam;	
			callAjax(rparam,jsObj,url);

			return;
		}

		if(selectedSortOption == 'cadrewise')
		{
			var jsObj=
			{
				selectedProblemScope : selectedProblemScope,
				selectedLocation	 : selectedLocation,
					task			 : "getCadreProblemsCountInARegion"
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getCadreProblemsCountInARegionAction.action?"+rparam;	
			callAjax(rparam,jsObj,url);

			getCadreProblemsInaRegion('Total');
			return;
		}
	}

	if(selectedOption == 'status')
	{
		selectedStatus = statusListEl.options[statusListEl.selectedIndex].value;
		var statusStr = statusListEl.options[statusListEl.selectedIndex].text;
		
		var jsObj=
		{
			task : "getTotalProblemsStatus"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getTotalProblemsStatusForAnUserAction.action?"+rparam;	
		callAjax(rparam,jsObj,url);
		getStatusWiseAllProblems(statusStr);
		return;
	}

	if(selectedOption == 'cadre')
	{
		var jsObj=
			{ 
				task : "getCadreProblemsCountForAnUser"
			}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getCadreProblemsCountInARegionAction.action?"+rparam;	
		callAjax(rparam,jsObj,url);

		getCadreProblemsInaRegion('Total');
		return;

	}
	
	if(selectedOption == 'department')
	{
		var deptScopeListId = document.getElementById("deptScopeList").value;
		
		if(deptScopeListId == 0)
			return;

		var jsObj=
		{ 
			deptScopeId : deptScopeListId,
			task		: "getDeptWiseProblemsCountForAnUser"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getStatusWiseProblemsInaRegionAction.action?"+rparam;	
		callAjax(rparam,jsObj,url);
		return;
	}
}

function getCadreProblemsInaRegion(cadreStatus)
{
	var	selectedOption = '';
	var problemOptionEl = document.getElementsByName("problemOption");
	var hiddenEl = document.getElementById("problemLocation");
	var scopeLevelEl = document.getElementById("scopeLevel");
	var selectedProblemScope = scopeLevelEl.options[scopeLevelEl.selectedIndex].value;
	var selectedLocation = hiddenEl.value;
	var sLocation = '';
	
	for(var i = 0; i<problemOptionEl.length; i++)
	{
		if(problemOptionEl[i].checked == true)
			selectedOption = problemOptionEl[i].value;
	}

	if(selectedOption == 'location')
		sLocation = "place";
	else if(selectedOption == 'cadre')
		sLocation = "cadre";
	var jsObj=
		{
			selectedProblemScope : selectedProblemScope,
			selectedLocation	 : selectedLocation,
				status			 : cadreStatus,
				sLocation		 : sLocation,
				task			 : "getCadreProblemsInARegion"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getCadreProblemsInARegionAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);
	getCadreDetailsForProblemsInARegion(cadreStatus);
	return;
}

function getCadreDetailsForProblemsInARegion(cadreStatus)
{
	if(cadreStatus == 'Total')
		return;

	var	selectedOption = '';
	var problemOptionEl = document.getElementsByName("problemOption");
	var hiddenEl = document.getElementById("problemLocation");
	var scopeLevelEl = document.getElementById("scopeLevel");
	var selectedProblemScope = scopeLevelEl.options[scopeLevelEl.selectedIndex].value;
	var selectedLocation = hiddenEl.value;
	var sLocation = '';
	
	for(var i = 0; i<problemOptionEl.length; i++)
	{
		if(problemOptionEl[i].checked == true)
			selectedOption = problemOptionEl[i].value;
	}

	if(selectedOption == 'location')
		sLocation = "place";
	else if(selectedOption == 'cadre')
		sLocation = "cadre";
	
	var jsObj=
		{
			selectedProblemScope : selectedProblemScope,
			selectedLocation	 : selectedLocation,
				status			 : cadreStatus,
				sLocation		 : sLocation,
				task			 : "getCadreDetailsForProblemsInARegion"
		}
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getCadreProblemsInARegionAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);
	return;
}

function getStatusWiseAllProblems(status)
{
	if(status == 'All')
		status = 'Total';

	var jsObj=
		{
			status : status,
			task   : "getStatusWiseAllProblems"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getStatusWiseProblemsForAnUserAction.action?"+rparam;	
		callAjax(rparam,jsObj,url);

		return;
}

function openProblemDetailsWindow(pHistoryId)
{
	var problemWindow = window.open("problemDetailsAndStatusAction.action?pHistoryId="+pHistoryId,"problemWindow","scrollbars=yes,height=600,width=850,left=200,top=200");
}

function getCadreInfo(cadreId)
{
	var urlStr = "getCadreInfoAction.action?windowTask=cadreInfoPopup&cadreId="+cadreId;
	var cadreViewBrowser = window.open(urlStr,"cadreInfoPopup","scrollbars=yes,height=600,width=600,left=200,top=50");	
	cadreViewBrowser.focus();
}

function getDeptWiseProblems(deptId,status)
{
	var jsObj=
	{
		status : status,
		deptId : deptId,
		task   : "getDepartmentWiseProblems"
	}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getStatusWiseProblemsInaRegionAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);

	return;
}

function getProblemsInADeptScopeBasedOnScope(scopeId,status)
{
	var jsObj=
	{
		status : status,
		scopeId : scopeId,
		task   : "getProblemsInADeptScopeBasedOnScope"
	}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getStatusWiseProblemsInaRegionAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);

	return;
}

function getDeptProblemsBasedOnStatusInARegion(status)
{
	var hiddenEl = document.getElementById("problemLocation");
	var scopeLevelEl = document.getElementById("scopeLevel");
	var selectedProblemScope = scopeLevelEl.options[scopeLevelEl.selectedIndex].value;
	var selectedLocation = hiddenEl.value;
	
	var jsObj=
	{
		selectedProblemScope : selectedProblemScope,
		selectedLocation	 : selectedLocation,
			status			 : status,
			task			 : "getDeptProblemsBasedOnStatusInARegion"
	}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getStatusWiseProblemsInaRegionAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);

	return;
}

function getDeptWiseProblemsBasedOnStatusInARegion(deptId,status)
{
	var hiddenEl = document.getElementById("problemLocation");
	var scopeLevelEl = document.getElementById("scopeLevel");
	var selectedProblemScope = scopeLevelEl.options[scopeLevelEl.selectedIndex].value;
	var selectedLocation = hiddenEl.value;
	
	var jsObj=
	{
		selectedProblemScope : selectedProblemScope,
		selectedLocation	 : selectedLocation,
			status			 : status,
			deptId			 : deptId,
			task			 : "getDeptWiseProblemsBasedOnStatusInARegion",
	}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getStatusWiseProblemsInaRegionAction.action?"+rparam;	
	callAjax(rparam,jsObj,url);

	return;
}

function buildResultDataTable(result)
{
	 var statusWiseProblemInfoDivBodyEle = document.getElementById("statusWiseProblemInfoDivBody");
	 
	 if(!statusWiseProblemInfoDivBodyEle || result == null)
		 return;
	
	var str = '';

	str += '<div id="stProblemDetails_head">';
	str +='<table cellpadding="0" cellspacing="0" width="100%">';
	str +='	<tr>';
	str +='		<td width="30px"><img src="images/icons/districtPage/header_left.gif"></td>';
	str +='		<td><div class="widgetHeader"><span class="widgetHeaderSpan"> Problem Details </span></div></td>';
	str +='		<td width="5px"><img src="images/icons/districtPage/header_right.gif"></td>';
	str +='	</tr>';
	str +='</table>';
	str += '</div>';
	str += '<div id="stProblemDetails_bod" class="yui-skin-sam"></div>';

	statusWiseProblemInfoDivBodyEle.innerHTML = str;

	YAHOO.widget.DataTable.problemLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var problem = oData;
		var pHId= oRecord.getData("problemHistoryId");
		elLiner.innerHTML ="<a href='javascript:{}' onClick='openProblemDetailsWindow("+pHId+")'>"+problem+"</a>";
			
	};

	YAHOO.widget.DataTable.cadreLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var cadreName = oData;
		if(cadreName != null)
		{
			var cadreId= oRecord.getData("cadreId");
			elLiner.innerHTML ="<a href='javascript:{}' onClick='getCadreInfo("+cadreId+")'>"+cadreName+"</a>";
		}
		else
			elLiner.innerHTML ="Not Assigned";
	};

	YAHOO.widget.DataTable.comments = function(elLiner, oRecord, oColumn, oData) 
	{
		var comments = oData;
		if(comments != null)
			elLiner.innerHTML =""+comments+"";
		else
			elLiner.innerHTML ="N/A";
	};

	YAHOO.widget.DataTable.department = function(elLiner, oRecord, oColumn, oData) 
	{
		var comments = oData;
		if(comments != null)
			elLiner.innerHTML =""+comments+"";
		else
			elLiner.innerHTML ="Not Assigned";
	};


	  var myColumnDefs = [
            {key:"problem",label:"Problem",sortable:true,formatter:YAHOO.widget.DataTable.problemLink},
            {key:"description",label:"Description",sortable:true},
            {key:"reportedDate",label:"Reported Date",formatter:YAHOO.widget.DataTable.formatDate, sortable:true},
            {key:"problemStatus",label:"Status", sortable:true},
			{key:"problemLocation",label:"Location",sortable:true},
     	    {key:"cadreName",label:"Cadre",sortable:true,formatter:YAHOO.widget.DataTable.cadreLink},
            {key:"department",label:"Department", sortable:true,formatter:YAHOO.widget.DataTable.department},
			{key:"recentActivity",label:"Recent Activity", sortable:true},
			{key:"comments",label:"Comments", sortable:true,formatter:YAHOO.widget.DataTable.comments}
			
       ];

	    var myDataSource = new YAHOO.util.DataSource(result);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["problem","description","reportedDate","problemStatus","problemLocation",
				"cadreName","department","recentActivity","comments","problemHistoryId","cadreId"]
        };
		
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 20,		        
				pageLinks: 20
			    }) 
				};	

		var myDataTable = new YAHOO.widget.DataTable("stProblemDetails_bod",
                myColumnDefs, myDataSource,myConfigs);

		return {
            oDS: myDataSource,
            oDT: myDataTable
        };
}

</script>  
<body >

<div class="yui-skin-sam">
<center>
	<div style="margin:15px;color:white;">
		<table border="0" cellpadding="0" cellspacing="0">          
			<tr>
			   <td><img src="images/icons/constituencyManagement/left_blue_main.png"/></td>
			   <td><div id="headerImageCenterDiv"><span id="headerImageCenterSpan">Problems Search</span></div></td>
			   <td><img src="images/icons/constituencyManagement/right_blue_main.png"/></td>
			 </tr>
		</table>
	</div>
</center>
<div id="mainDIV">
<div id="problemHeading"></div>
<div>

<table border="0" width="98%" cellpadding="2"  style="margin:15px;">
<tr>
<td>Search Problems By:</td>
<td class="tdClass"><input  type="radio" name="problemOption" value="location" checked="true" onclick="clearReportBody();showProbRegionsSelect()"/>Problem Location</td>		
<td class="tdClass"><input type="radio" name="problemOption" value="status" onclick="clearReportBody();showProbStatusSelect()"/>Problem Status </td>
<td class="tdClass"><input type="radio" name="problemOption" value="cadre" onclick="clearReportBody();showProbStatusSelect('cadre')"/>Cadre</td>
<td class="tdClass"><input  type="radio" name="problemOption" value="department" onclick="clearReportBody();showDeptRegionsSelect()"/> Department</td>
</tr>
</table>
<div style="text-align:left;margin-left:10px;">

	<table id="probRegionSpan" border="0" style="margin-left:10px;display:none;">
	<tr>
		<th style="width:195px;">Problem Impacted Region</th>	
		<td ><s:select theme="simple" cssClass="selectWidth" value="defaultRegionScope" id="scopeLevel" name="regionScope" list="problemScopes" headerKey="0" headerValue = "Select Problem Region Level" listKey = "id" listValue="name" onchange="populateLocations(this.options[this.selectedIndex].value,'onChange')" /></td>
	</tr>	
	</table>
	<table id="locationsTable" border="0" style="margin-left:10px;margin-top:5px;display:none;">
	<!--<tr>
		<th colspan="2"> <font color='blue'>Select Problem Location</th>
	</tr>-->
	<c:if test="${accessType != 'MP'}">
		<tr id="row1" style="display:none;">
			<th style="width:195px;"><%=STATE%><font class="requiredFont">*</font></th>
			<td><s:select id="stateField_s" theme="simple" cssClass="selectWidth" name="state" value="defaultState" list="stateList" listKey="id" listValue="name"  onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'districtsInState','problemSearch','districtField_s','currentAdd', 'null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
		</tr>
		<tr id="row2" style="display:none;">
			<th style="width:195px;"><%=DISTRICT%><font class="requiredFont"> * </font></th>
			<td><s:select id="districtField_s" theme="simple" value="defaultDistrict"  cssClass="selectWidth" name="district" headerKey="0" headerValue="Select Location" list="districtList" listKey="id" listValue="name" onchange="getSubRegionsInDistrict(this.options[this.selectedIndex].value,'problemSearch','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
		</tr>
	</c:if>
	<c:if test="${accessType == 'MP'}">
		<tr id="row1" style="display:none;">
			<th style="width:195px;"><%=STATE%><font class="requiredFont">*</font></th>
			<td><s:select id="stateField_s" theme="simple" cssClass="selectWidth" value="defaultState" name="state" list="stateList" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'parliamentsInState','problemSearch','pConstituencyField_s','currentAdd','null');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
		</tr>
		<TR id="row2" style="display:none;">	
			<Th style="width:195px;"><%=PCONSTITUENCY%></Th>
			<TD><s:select id="pConstituencyField_s" theme="simple" cssClass="selectWidth" name="pConstituencyId" list="pConstituencyList" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" value="defaultPConstituency" onchange="getLocationHierarchies(this.options[this.selectedIndex].value,'assembliesInParliament','problemSearch','constituencyField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></TD>
		</TR>
	</c:if>
		<tr id="row3" style="display:none;">
			<th style="width:195px;"><%=ACONSTITUENCY%><font class="requiredFont"> * </font></th>
			<td><s:select id="constituencyField_s" theme="simple" value="defaultConstituency" cssClass="selectWidth" name="constituency" list="constituencyList" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" onchange="getSubRegionsInConstituency(this.options[this.selectedIndex].value,'problemSearch','mandalField_s','currentAdd');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
		</tr>								
		<tr id="row4" style="display:none;">
			<th style="width:195px;"><%=MANDAL%><font class="requiredFont"> * </font></th>
			<td><s:select id="mandalField_s" theme="simple" cssClass="selectWidth" name="mandal" list="{}" listKey="id" listValue="name" headerKey="0" headerValue="Select Location" onchange="getSubRegionsInTehsilOrLocalElecBody(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text,'problemSearch','currentAdd','null','constituencyField_s', 'row6', 'row5');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
		</tr>					
		<tr id="row5" style="display:none;">
			<th style="width:195px;"><%=HAMLET%><font class="requiredFont"> * </font></th>
			<td><s:select id="hamletField_s" theme="simple" cssClass="selectWidth" name="village" list="{}" listKey="id" listValue="name" onchange="getBoothsInWard('currentAdd','constituencyField_s','boothField_s',this.options[this.selectedIndex].value,'problemSearch','mandalField_s');setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
		</tr>	
		<tr id="row6" style="display:none;">
			<th style="width:195px;">Booth No</th>
			<td><s:select theme="simple" id="boothField_s" cssClass="selectWidth" name="booth" list="{}" listKey="id" listValue="name" onchange="setLocationValue(this.options[this.selectedIndex].value,'onChange')"></s:select></td>
			<td><input type="button" id="pBoothDetailsPanel" value="View Booths Details" onclick="showBoothsCompleteDetails('boothField_s', 'mandalField_s')"/></td>
		</tr>
	</TABLE>
	</div>
	<div style="text-align:left;margin-left:20px;margin-top:15px;display:none;" id="problemLocationOptionsDiv">
	<table border="0">
		<tr>
			<td><input  type="radio" name="problemLocationOption" value="all" onclick="clearReportBody();showProbRegionsSelect()" checked="true"/>All Problems</td>
			<td><input  type="radio" name="problemLocationOption" value="departmentwise" onclick="clearReportBody();showProbRegionsSelect()"/>Departmentswise</td>
			<td><input  type="radio" name="problemLocationOption" value="cadrewise" onclick="clearReportBody();showProbRegionsSelect()"/>Cadre Problems</td>
		</tr>
	</table>
	</div>
	<div style="text-align:left;margin-left:10px;">
	<table id="statusSpan" width="98%" border="0" style="margin-left:10px;display:none;">
	<tr>
		<th style="width:195px;">Problem Status<font class="requiredFont">*</font></th>	
		<td ><s:select theme="simple" cssClass="selectWidth" id="statusList" name="probStatus" headerKey="-1" headerValue = "Select Problem Status" list="statusList" listKey = "id" listValue="name" /></td>
	</tr>	
	</table>
	</div>
	<div style="text-align:left;margin-left:10px;">
	<table id="deptRegionSpan" width="98%" border="0" style="margin-left:10px;display:none;">
	<tr>
		<th style="width:195px;">Department Scope</th>	
		<td><s:select cssClass="selectWidth2" id="deptScopeList" theme="simple" name="depScope" list="deptScopes" headerKey="0" headerValue = "Select Department Level" listKey = "id" listValue="name" /></td>
	</tr>		
	</table>
	<table border="0" id="deptsSelectTable" width="98%" style="display:none;margin-left:10px;">
	<tr>
		<th style="width:195px;">Department</th>
		<td>
		<select name="dept" id="deptsSelectField" style="width:175px;"></select>
		</td>
	</tr>
	</table>
	
	</div>
	<div id="errorDiv"></div>
	<input type="button" class="btnClass" onclick="clearReportBody();getProblems()" value="Search"/>
	</div>
<div id="constituencyMgmtBodyDiv" class="yui-skin-sam"></div>
</div>
<div id="areaTypes"></div>
<div id="areaTypess"></div>
<div id="buildId"></div>
</div>
<div class="yui-skin-sam">
	<div id="problemInfoDivBody" style="margin:15px;"></div>
</div>
<div class="yui-skin-sam">
	<div id="deptWiseProblemInfoDivBody" style="margin:15px;"></div>
</div>
<div class="yui-skin-sam">
	<div id="statusWiseProblemInfoDivBody" style="margin:15px;"></div>
</div>
<input type="hidden" id="problemLocation" name="problemLocationId"/>
<div class="yui-skin-sam"><div id="boothDetailsPopup"></div></div>
<script type="text/javascript">
executeOnload();
</script>
</body>

</html>
