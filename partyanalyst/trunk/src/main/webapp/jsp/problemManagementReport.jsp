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
<title>Problem Management Report</title>



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
		}

		#problemHeading
		{
		margin-left:45px;
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
  %> }

	
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

function initializeResultsTable() {

var resultsDataSource = new YAHOO.util.DataSource(problemDetails.problemArray);
resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
resultsDataSource.responseSchema = {
	fields : [ {
		key : "name"
	}, {
		key : "description"
	},{
		key : "postedPersonName"
	},  {
		key : "hamlet"
	}, {
		key : "existingFrom"
	}, {
		key : "status"
	}, {
		key : "department"
	}, {
		key : "departmentConcernedPersonName"
	}, {
		key : "updatedDate"
	},{
		key : "comments"
	},{
		key : "MoreDetails"
	},{
		key : "email"
	},{
		key : "phone"
	},{
		key : "mobile"
	},{
		key : "address"
	},{
		key : "contactNo"
	},{
		key : "designation"
	},{
		key : "problemLocationId"
	}]   
};	

var resultsColumnDefs = [ {
	key : "name",
	label : "Problem",
	sortable : true
}, {
	key : "description",
	label : "Description",
	sortable : true,
	resizable:true
},{
	key : "postedPersonName",
	label : "Source",
	sortable : true
	},{
	key : "hamlet",
	label : "Location",
	sortable : true
}, {
	key : "existingFrom",
	label : "Identified Date",
	sortable : true
}, {
	key : "department",
	label : "Department",
	sortable : true
}, {
	key : "status",
	label : "Status",
	sortable : true
}, {
	key : "MoreDetails",
	label : "View Details",
	sortable : true
}, {
	key : "departmentConcernedPersonName",
	label : "Official",
	sortable : true,
	hidden:true	
}, {
	key : "updatedDate",
	label : "Updated Date",
	sortable : true,
	hidden:true		
}, {
	key : "comments",
	label : "Comments",
	sortable : true,
	hidden:true		
}, {
	key : "email",
	label : "Email",
	sortable : true,
	hidden:true		
}, {
	key : "phone",
	label : "Phone",
	sortable : true,
	hidden:true		
}, {
	key : "mobile",
	label : "Mobile",
	sortable : true,
	hidden:true		
}, {
	key : "address",
	label : "Address",
	sortable : true,
	hidden:true		
}, {
	key : "contactNo",
	label : "ContactNo",
	sortable : true,
	hidden:true		
}, {
	key : "designation",
	label : "Designation",
	sortable : true,
	hidden:true		
}, {
	key : "problemLocationId",
	label : "LocationId",
	sortable : true,
	hidden:true		
} ];	

var myConfigs = {		
paginator : new YAHOO.widget.Paginator({
    rowsPerPage: 5,
    template: "{PageLinks} Show {RowsPerPageDropdown} per page",
    rowsPerPageOptions: [5,10,15,20], 
    pageLinks: 5 
    }),   
};


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
				if(jsObj.task == "getVillages")
				{
					showVillagesInSelectOption(myResults);
				}
				if(jsObj.task == "findVoters")
				{
					showProblemsReport(myResults);	
				}  
				if(jsObj.task == "findTownshipVoters")
				{
					showProblemsReport(myResults);		
				}
				if(jsObj.task == "findConstituencyVoters")
				{
					showProblemsReport(myResults);			
				}
				if(jsObj.task == "getProblemDetails")
				{
					showProblemsHistoryReport(myResults);			
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
function showProblemsReport(results){
	assignToProblemsArray = new Array();

			for(var i in results)
			{
				var problemObj= {
						 name:results[i].name,
						 description:results[i].description,
						 postedPersonName:results[i].postedPersonName,
						 hamlet:results[i].hamlet,
						 existingFrom:results[i].existingFrom,
						 status:results[i].status,
						 department:results[i].department,
						 departmentConcernedPersonName:results[i].departmentConcernedPersonName,
						 updatedDate:results[i].updatedDate,
						 comments:results[i].comments,
						 email:results[i].email,
						 phone:results[i].phone,
						 mobile:results[i].mobile,
						 address:results[i].address,
						 contactNo:results[i].contactNo,
						 designation:results[i].designation,
						 designation:results[i].designation,
						 problemLocationId:results[i].problemLocationId,
						 MoreDetails:'<a href="#">More Details..</a>'						
					};
				
				assignToProblemsArray.push(problemObj);
				problemDetails.problemArray=assignToProblemsArray;	
			}
			var emptyArr = new Array();
		    if(results.length == 0)
			{	
		    	problemDetails.problemArray = emptyArr;				
			}
			initializeResultsTable();		
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

function getVotersForConstituency(id)
{
	var jsObj=
		{
				locationId:id,
				task:"findConstituencyVoters",
				taskType:type						
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportResults.action?"+rparam;						
		callAjax(rparam,jsObj,url);
}

function getVotersForTownships(id)
{
	var jsObj=
		{
				locationId:id,
				task:"findTownshipVoters",
				taskType:type						
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportResults.action?"+rparam;						
		callAjax(rparam,jsObj,url);
} 


function getHamlet(id)
{
	var jsObj=
		{
				locationId:id,
				task:"findVoters",
				taskType:type						
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementReportResults.action?"+rparam;						
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
function clearContents(){
	
	var areaSelection;
	areaSelection = document.getElementById("areaTypes");
	areaSelection.innerHTML = "";
	var locationSelectBox;
	locationSelectBox = document.getElementById("buildId");
	locationSelectBox.innerHTML = "";
	var dataTable;
	dataTable = document.getElementById("problemInfoDivBody");
	dataTable.innerHTML = "";
	areaType();
}
function clearContent()
{
	var areaSelection;
	areaSelection = document.getElementById("areaTypes");
	areaSelection.innerHTML = "";
	var locationSelectBox;
	locationSelectBox = document.getElementById("buildId");
	locationSelectBox.innerHTML = "";
	var dataTable;
	dataTable = document.getElementById("problemInfoDivBody");
	dataTable.innerHTML = "";
	var areaTypess;
	areaTypess = document.getElementById("areaTypess");
	areaTypess.innerHTML = "";
}
function areaType()
{	
	type="";
	clearContent();
	var areaTypes;
	areaTypes = document.getElementById("areaTypes");
	removeSelectElements(areaTypes);
	var hr='';
	hr+='<br/><br/>';
	hr+='Select Type of area to generate the report';
	hr+='<table>';
	hr+='<tr><td><input  type="radio" name="locationType" value="location" onclick="populateConstituencyLocations()" > Constituency</input></td>';		
	hr+='<td><input  type="radio" name="locationType" value="status" onclick="populateMandalLocations()"> Mandal</input></td>';
	hr+='<td><input  type="radio" name="locationType" value="department" onclick="populateHamletLocations()"> Hamlet</input></td></tr></table>';
	hr+='';
	areaTypes.innerHTML = hr;
}
function areaTypes(typeId)
{
	if(typeId=="new"){
		type=typeId;
	}
	else if(typeId=="classify"){
		type=typeId;
	}
	else if(typeId=="assigned"){
		type=typeId;
	}
	else if(typeId=="progress"){
		type=typeId;
	}
	else if(typeId=="pending"){
		type=typeId;
	}
	else if(typeId=="fixed"){
		type=typeId;
	}
	else if(typeId=="deo"){
		type=typeId;
	}
	else if(typeId=="collectorate"){
		type=typeId;
	}
	else if(typeId=="mro"){
		type=typeId;
	}
	else if(typeId=="VILLAGE SECRETARY"){
		type=typeId;
	}
	else{
		type="";
		}
	var locationSelectBox;
	locationSelectBox = document.getElementById("buildId");
	locationSelectBox.innerHTML = "";
	var dataTable;
	dataTable = document.getElementById("problemInfoDivBody");
	dataTable.innerHTML = "";
	var areaTypess;
	var areaTypess;
	areaTypess = document.getElementById("areaTypess");
	var hr='';
	hr+='<br/><br/>';
	hr+='Select location to generate the report';
	hr+='<table>';
	hr+='<tr><td><input  type="radio" name="locationTypes" value="constituency" onclick="populateConstituencyLocations()" > Constituency</input></td>';		
	hr+='<td><input  type="radio" name="locationTypes" value="mandal" onclick="populateMandalLocations()"> Mandal</input></td>';
	hr+='<td><input  type="radio" name="locationTypes" value="hamlet" onclick="populateHamletLocations()"> Hamlet</input></td></tr></table>';
	hr+='';
	areaTypess.innerHTML = hr;
}
function statusType()
{
	var statusTypeId = statusTypeId;
	clearContent();
	var areaTypess;
	areaTypess = document.getElementById("areaTypes");
	var hr='';
	hr+='<br/><br/>';
	hr+='Select a Status to generate the report';
	hr+='<table>';
	hr+='<tr><td><input  type="radio" name="locationStatus" value="new" onclick="areaTypes(this.value)"> New</input></td>';		
	hr+='<td><input  type="radio" name="locationStatus" value="classify" onclick="areaTypes(this.value)"> Classify</input></td>';
	hr+='<td><input  type="radio" name="locationStatus" value="assigned" onclick="areaTypes(this.value)"> Assigned</input></td>';
	hr+='<tr><td><input  type="radio" name="locationStatus" value="progress" onclick="areaTypes(this.value)"> Progress</input></td>';		
	hr+='<td><input  type="radio" name="locationStatus" value="pending" onclick="areaTypes(this.value)"> Pending</input></td>';
	hr+='<td><input  type="radio" name="locationStatus" value="fixed" onclick="areaTypes(this.value)"> Fixed</input></td>';	
	hr+='</tr></table>';
	hr+='';
	areaTypess.innerHTML = hr;
}

function departmentType(departmentTypeId)
{
	var departmentTypeId = departmentTypeId;
	clearContent();
	var areaTypess;
	areaTypess = document.getElementById("areaTypes");
	var hr='';
	hr+='<br/><br/>';
	hr+='Select a Department to generate the report';
	hr+='<table>';
	hr+='<tr><td><input  type="radio" name="departmentLocation" value="deo" onclick="areaTypes(this.value)"> DEO</input></td>';		
	hr+='<td><input  type="radio" name="departmentLocation" value="collectorate" onclick="areaTypes(this.value)"> COLLECTORATE</input></td>';
	hr+='<td><input  type="radio" name="departmentLocation" value="mro" onclick="areaTypes(this.value)"> MRO</input></td>';
	hr+='<td><input  type="radio" name="departmentLocation" value="VILLAGE SECRETARY" onclick="areaTypes(this.value)"> VILAGE SECRETARY</input></td>';		
	hr+='</tr></table>';
	hr+='';
	areaTypess.innerHTML = hr;
}

function populateHamletLocations()
{
	var dataTable;
	dataTable = document.getElementById("problemInfoDivBody");
	dataTable.innerHTML = "";
	var hel;
	hel = document.getElementById("buildId");
	var hr='';
	getStateList(this.value);
	hr+='<br/><br/>';
	hr+='<table><tr>';
	hr+='<td><%=STATE%></td>';
	hr+='<td>';
	hr+='<select id="stateId" class="selectWidth" list="result" theme="simple" listKey="id" listValue="name" onchange="getDistrictsList(this.value)"/>';
	hr+='</td>';	
	hr+='<td><%=DISTRICT%></td>';
	hr+='<td>';
	hr+='<select id="districtField" class="selectWidth" name="district"  onchange="getConstituencyList(this.options[this.selectedIndex].value,false)"/>';			
	hr+='</select>';
	hr+='</td>';
		
	hr+='<td><%=CONSTITUENCY%></td>';
		
	hr+='<td>';
	hr+='<select id="constituencyField" class="selectWidth" name="constituency"  onchange="getMandalList(this.options[this.selectedIndex].value,false)"/>';
	hr+='</select>';
	hr+='</td>';
	hr+='</tr>';
	hr+='<tr>';	
	hr+='<td><%=MANDAL%></td>';
		
	hr+='<td>';
	hr+='<select id="mandalField" class="selectWidth" name="mandal" onchange="getTownshipsForMandal(this.options[this.selectedIndex].value,false)"/>';
				
	hr+='</select>';
	hr+='</td>';
		
	hr+='<td><%=VILLAGE%></td>';
		
	hr+='<td>';
	hr+='<select class="selectWidth" id="villageField" name="village" onchange="getVillagesForMandal(this.options[this.selectedIndex].value,false)"/>';
				
	hr+='</select>';
	hr+='</td>';
		
	hr+='<td><%=HAMLET%></td>';
	hr+='<td>';
	hr+='<select class="selectWidth" id="hamletField" name="hamlet" onchange="getHamlet(this.options[this.selectedIndex].value)"/>';	
	hr+='</select>';
	hr+='</td>';
	hr+='</tr></table>';
	hr+='<br/><br/>';
	hel.innerHTML = hr;
}
function populateMandalLocations()
{
	var dataTable;
	dataTable = document.getElementById("problemInfoDivBody");
	dataTable.innerHTML = "";
	var hel;
	hel = document.getElementById("buildId");
	var hr='';
	getStateList(this.value);
	hr+='<br/><br/>';
	hr+='<table><tr>';
	hr+='<td><%=STATE%></td>';
	hr+='<td>';
	hr+='<select id="stateId" class="selectWidth" list="result" theme="simple" listKey="id" listValue="name" onchange="getDistrictsList(this.value)"/>';
	hr+='</td>';	
	hr+='<td><%=DISTRICT%></td>';
	hr+='<td>';
	hr+='<select id="districtField" class="selectWidth" name="district"  onchange="getConstituencyList(this.options[this.selectedIndex].value,false)"/>';			
	hr+='</select>';
	hr+='</td>';
		
	hr+='<td><%=CONSTITUENCY%></td>';
		
	hr+='<td>';
	hr+='<select id="constituencyField" class="selectWidth" name="constituency"  onchange="getMandalList(this.options[this.selectedIndex].value,false)"/>';
	hr+='</select>';
	hr+='</td>';
	hr+='</tr>';
	hr+='<tr>';	
	hr+='<td><%=MANDAL%></td>';
		
	hr+='<td>';
	hr+='<select id="mandalField" class="selectWidth" name="mandal" onchange="getVotersForTownships(this.options[this.selectedIndex].value)"/>';
				
	hr+='</select>';
	hr+='</td>';
	hr+='</tr></table>';
	hr+='<br/><br/>';
	hel.innerHTML = hr;
}
function populateConstituencyLocations()
{
	var dataTable;
	dataTable = document.getElementById("problemInfoDivBody");
	dataTable.innerHTML = "";
	var hel;
	hel = document.getElementById("buildId");
	var hr='';
	getStateList(this.value);
	hr+='<br/><br/>';
	hr+='<table><tr>';
	hr+='<td><%=STATE%></td>';
	hr+='<td>';
	hr+='<select id="stateId" class="selectWidth" list="result" theme="simple" listKey="id" listValue="name" onchange="getDistrictsList(this.value)"/>';
	hr+='</td>';	
	hr+='<td><%=DISTRICT%></td>';
	hr+='<td>';
	hr+='<select id="districtField" class="selectWidth" name="district"  onchange="getConstituencyList(this.options[this.selectedIndex].value,false)"/>';			
	hr+='</select>';
	hr+='</td>';		
	hr+='<td><%=CONSTITUENCY%></td>';		
	hr+='<td>';
	hr+='<select id="constituencyField" class="selectWidth" name="constituency"  onchange="getVotersForConstituency(this.options[this.selectedIndex].value)"/>';
	hr+='</select>';
	hr+='</td>';
	hr+='</tr></table>';
	hr+='<br/><br/>';
	hel.innerHTML = hr;
	
}
</script>

  
<body >

<div id="mainDIV" class="yui-skin-sam">
<div class="problemHeader">Problem Management Report</div><br/><br/>
<div id="problemHeading">Find Problems By </div>
<div id="radioLocationHeader">
<table><tr>
<td><input  type="radio" name="location" value="location" onclick="areaType()"> Location</input></td>		
<td><input  type="radio" name="location" value="status" onclick="statusType()"> Status </input></td>
<td><input  type="radio" name="location" value="department" onclick="departmentType()"> Department</input></td>
</tr></table>
<div id="constituencyMgmtBodyDiv" class="yui-skin-sam"></div>
</div>
<div id="areaTypes"></div>
<div id="areaTypess"></div>
<div id="buildId"></div>
</div>
<div class="yui-skin-sam">
<div id="problemInfoDivBody" >
</div>
</div>
</body>

</html>
