<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Comments Control Admin Page</title>

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/calendar/assets/skins/sam/calendar.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/calendar/calendar-min.js"></script> 
<script type="text/Javascript" src="js/homePage/jquery.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

<style type="text/css">
	
	#commentsControlMain
	{
		margin-left:30px;
		padding:20px;
		text-align:left;
	}
	
	.tinyDateCal 
	{
		position:absolute;
	}
	
	.reasonsAdminPanels_main
	{
		-moz-border-radius:10px 10px 10px 10px;
		background-color:#EEF2F3;
		border:1px outset #ADADAD;
		margin:10px;		
		color:#544132;
	}

	#reasonsAdminPageHeader
	{
		color:#643918;
		font-size:20px;
		text-align:center;
		text-decoration:underline;
	}

	.headerLabelDiv_main
	{
		background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
		color:#4B74C6;
		font-size:12px;
		font-weight:bold;
		height:30px;		
		text-align:left;
		width:100%;
	}

	.headerLabelSpan_main
	{
		position:relative;
		top:7px;
		left:10px;
	}

	.reasonsAdminPanels_content
	{
		padding:20px;
	}

	.errorMsgDiv
	{
		color:red;
	}

	.buttonClass
	{
		-moz-border-radius:6px;
		background-color:#9FB9D0;
		color:#FFFFFF;
		cursor:pointer;		
		font-weight:bold;
		padding:4px;
	}

</style>

<script type="text/javascript">

var day = new Date().getDate();
var month = new Date().getMonth()+1;
var year = new Date().getFullYear();

var maxDate = (new Date().getMonth() + 1) + "/" + new Date().getDate() + "/" + new Date().getFullYear();
var textBoxDivId;
function showDateCal(divId, textBoxId,pageDate) {
	
	textBoxDivId = textBoxId;
	var id = document.getElementById(divId);
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
		pagedate: pageDate,
		maxdate: maxDate,
		title : "Choose a date:",
		close : true
	});
	dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true);
	dateCalendar.render();
	dateCalendar.show();
}
function displayDateText(type, args, obj) {
	var dates = args[0];
	var date = dates[0];
	var year = date[0], month = date[1], day = date[2];
	var divId = obj.containerId;
	var divElmt = document.getElementById(divId);

	var txtDate1 = document.getElementById(textBoxDivId);
	txtDate1.value = year + "/" + month + "/" + day;
	minDate = month + "/" + day + "/" + year;
	divElmt.style.display = 'none';
}

function buildCommentsDatatable(commentsArray,divId)
{
	var resultsDataSource = new YAHOO.util.DataSource(commentsArray);
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "checkbox"
		}, {
			key : "candidate"
		}, {
			key : "partyName"
		}, {
			key : "electionYear",parser:"number"
		}, {
			key : "rank"
		}, {
			key : "commentDesc"
		} , {
			key : "commentedBy"
		} , {
			key : "commentedOn"
		} , {
			key : "constituencyName"
		},  {
			key : "electionType"
		}]
	};

	var resultsColumnDefs = [ {
		key : "checkbox",
		label : "Select",
		sortable : true
	}, {
		key : "candidate",
		label : "Candidate",
		sortable : true
	}, {
		key : "partyName",
		label : "Party",
		sortable : true
	}, {
		key : "electionYear",
		label : "Year",
		sortable : true
	}, {
		key : "rank",
		label : "Status",
		sortable : true
	}, {
		key : "commentDesc",
		label : "Reason",
		sortable : true
	}, {
		key : "commentedBy",
		label : "Posted By",
		sortable : true
	} , {
		key : "commentedOn",
		label : "Posted On",
		sortable : true
	} , {
		key : "constituencyName",
		label : "Constituency",
		sortable : true
	} , {
		key : "electionType",
		label : "Election",
		sortable: true
		
	} ];

    var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,		        
				template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
				rowsPerPageOptions: [20,40,60], 
			    pageLinks: 20
			    }) 
				};	
	var myDataTable = new YAHOO.widget.DataTable(divId,resultsColumnDefs, resultsDataSource,myConfigs);  
}

function animateShowdiv(divId)
{
	$("#"+divId).slideToggle();
}

function callAjax(jsObj,url)
{		
	var results;	
	var callback = {			
		success : function( o ) {
			try {																
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllNewPostedReasons" || jsObj.task == "getAllNewPostedReasonsBetweenDates")
					{	
						showNewPostedReasons(jsObj,results);	
					} else if(jsObj.task == "approved" || jsObj.task == "rejected")
					{
						showNewPostedReasons(jsObj,results);
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

function showNewPostedReasons(jsObj,results)
{
	var startDate = '';
	var endDate = '';

	if(results.length > 0)
	{
		startDate = results[0].commentedOn.replace(/-/g, "/");
		endDate = results[results.length-1].commentedOn.replace(/-/g, "/");		
	}

	var commentsArray = new Array();
	for(var i=0; i<results.length; i++)
	{
		var status = "Lost";
		if(results[i].rank == 1)
			status = "Won"
		var obj={	
					checkbox:"<input type='checkbox' name='commentsCheck' value='"+results[i].commentId+"_"+startDate+"_"+endDate+"'>",
					candidateId:results[i].candidateId,
					candidate:results[i].candidate,
					commentId:results[i].commentId,
					commentDesc:results[i].commentDesc,
					commentedBy:results[i].commentedBy,
					commentedOn:results[i].commentedOn,
					constituencyName:results[i].constituencyName,
					electionType:results[i].electionType,
					electionYear:results[i].electionYear,							
					partyName:results[i].partyName,							
					rank:status
				};
		commentsArray.push(obj);
	}

	buildCommentsDatatable(commentsArray,"commentsData");
}

function getAllProblemsBetweenDates(task)
{
	var startDate = '';
	var endDate = '';

	if(task == "getAllNewPostedReasons")
	{
		startDate = year+'/'+month+'/'+day;
		endDate = year+'/'+month+'/'+day;
	}
	else
	{
		startDate = document.getElementById("identifiedFromText").value;
		endDate = document.getElementById("reportedFromText").value;
	}
	
	var selection = document.getElementsByName("commentType");
	var selectedRadio =null;
	if(selection)
	{
		for(var i=0;i<selection.length;i++){
			if(selection[i].checked == true){
				selectedRadio = selection[i].value;					
			}
		}			
	}
	var jsObj=
	{		
			choice:selectedRadio,
			fromDate:startDate,
			toDate:endDate,					
			task:task						
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getAllComments.action?"+rparam;					
	callAjax(jsObj,url);
}

function controlSelectedReasons(task)
{
	var elmts = document.getElementsByName("commentsCheck");
	var errorElmt = document.getElementById("approveCommentsErrorDiv");

	var checkedElmts = new Array();
	var startDate = '';
	var endDate = '';
	
	var initialValue = elmts[0].value;

	startDate = initialValue.substring(initialValue.indexOf('_')+1,initialValue.lastIndexOf('_'));
	endDate = initialValue.substring(initialValue.lastIndexOf('_')+1,initialValue.length);

	for(var i=0; i<elmts.length; i++)
	{		
		if(elmts[i].checked == true)
		{		
			checkedElmts.push(elmts[i].value.substring(0,elmts[i].value.indexOf('_')));
		}
	}
	
	if(checkedElmts.length == 0)
	{
		errorElmt.innerHTML = 'Atleast one reason has to be selected to perform some action';
		return;
	}
	else
	{
		errorElmt.innerHTML = '';
	}
		
	var jsObj=
	{		
			fromDate:startDate,
			toDate:endDate,	
			checkedElmts:checkedElmts,					
			task:task						
	};
	
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/controlReasonsAction.action?"+rparam;					
	callAjax(jsObj,url);
}

function showAllNewPostedReasons()
{
	var elmt = document.getElementById("openedReasons_dates");
	if(!elmt)
		return;
	
	

	var str = '';
	str += '<div id="openedReasonsDateDiv" style="font-size:13px;font-weight:bold;">';
	str += 'Showing all reasons posted today - '+day+'/'+month+'/'+year;
	str += '<input type="button" class="buttonClass" value="Change" onclick="animateShowdiv(\'openedReasonsDateSelectDiv\')">';
	str +'</div>';
	str += '<div id="openedReasonsDateSelectDiv" style="font-size:13px;font-weight:bold;display:none;">';
	str += '<table>';
	str += '<tr>';
	str += '	<th>View posted reasons between</th>';
	str += '	<td><font class="requiredFont"> * </font></td>	';
	str += '	<td>';
	str += '		<input type="text"  READONLY="READONLY" name ="occuredDate" id="identifiedFromText" size="25"/>';
	str += '		<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div>';
	str += '	</td>';
	str += '	<td valign="top">';
	str += '		<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal(\'identifiedFromText_Div\',\'identifiedFromText\',\'9/2010\')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>';
	str += '	</td>';				
		
	str += '	<td><font class="requiredFont"> * </font></td>';
	str += '	<td>';
	str += '		<input type="text" READONLY="READONLY" name ="reportedDate" id="reportedFromText" size="25"/>';
	str += '		<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal"></div></div>';
	str += '	</td>';				
	str += '	<td valign="top">';
	str += '		<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal(\'reportedFromText_Div\',\'reportedFromText\',\'9/2010\')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>';
	str += '	</td>';
	str += '	<td>';
	str += '	<input type="button" class="buttonClass" value="view" onclick="getAllProblemsBetweenDates(\'getAllNewPostedReasonsBetweenDates\')">';
	str += '	</td>';
	str += '</tr>';
	str += '</table>';
	str += '</div>';

	elmt.innerHTML = str;

	getAllProblemsBetweenDates("getAllNewPostedReasons");
}

window.history.forward(1);

</script>

</head>
<body>	
	<div id="commentsControlMain">		
		
		<div id="reasonsAdminPageHeader"> Reasons Control Admin Page</div>

		<div id="allOpenedReasons_main" class="reasonsAdminPanels_main">
			<div>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td width="3px"><img src="images/icons/electionResultsAnalysisReport/first.png"></td>
						<td><div class="headerLabelDiv_main"><span class="headerLabelSpan_main">Newely Posted Reasons</span></div></td>
						<td width="3px"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>
					</tr>
				</table>
			</div>
			<div id="openedReasons_dates" class="reasonsAdminPanels_content">
				
			</div>
			<div id="commentsData_outer" class="reasonsAdminPanels_content yui-skin-sam">
				<div id="commentsData"></div>
			</div>
			<div id="reasonsApprovediv" style="text-align:right;padding:10px;">
				<table width="100%">
					<tr>
						<td width="80%" align="right"><div id="approveCommentsErrorDiv" class="errorMsgDiv"></div></td>					
						<td width="20%">
							<input type="button" class="buttonClass" value="Approve" onclick="controlSelectedReasons('approved')"></input>
							<input type="button" class="buttonClass" value="Reject" onclick="controlSelectedReasons('rejected')"></input>
						</td>
					</tr>
				</table>
				
			</div>
		</div>
		

		<!--<div id="commentsByTwoDates" class="reasonsAdminPanels_main">
			<table>
				<tr>
					<th colspan="7">Select to view Reasons posted between two dates</th>
				</tr>
				<tr>
					<th>Reason Type</th>
					<td colspan="6">
						<input type="radio" name="commentType" checked="checked" value="all">All</input>
						<input type="radio" name="commentType" value="approved">Approved</input>
						<input type="radio" name="commentType" value="rejected">Rejeced</input>
					</td>
				</tr>
				<tr>
					<th>Dates Between</th>
					<td><font class="requiredFont"> * </font></td>	
					<td>
						<input type="text"  READONLY="READONLY" name ="occuredDate" id="identifiedFromText" size="25"/>
						<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div>
					</td>					
					<td valign="top">
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('identifiedFromText_Div','identifiedFromText','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>
					</td>						
					
					<td><font class="requiredFont"> * </font></td>
					<td>
						<input type="text" READONLY="READONLY" name ="reportedDate" id="reportedFromText" size="25"/>
						<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal"></div></div>
					</td>					
					<td valign="top">
						<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal('reportedFromText_Div','reportedFromText','9/2010')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>
					</td>
				</tr>

				<tr>
					<td colspan="7"><input type="button" value="view" onclick="getAllProblemsBetweenDates('getAnalyzedReasonsBetweenDates')"></input></td>
				</tr>
			</table>
		</div>-->
	</div>

		<script type="text/javascript">
			showAllNewPostedReasons();			
		</script>
	
</body>
</html>