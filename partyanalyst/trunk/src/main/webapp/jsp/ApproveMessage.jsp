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
#commentsData table{border-collapse:collapse;border:1px solid #d3d3d3;width:100%;background:#fff;}
#commentsData table tr td{border-collapse:collapse;border:1px solid #d3d3d3;}
#commentsData table tr th{border:1px solid #d3d3d3;background:url("images/icons/electionResultsAnalysisReport/mid.png");}
#commentsData table tr:nth-child(odd){background:#f9f9f9;}
	
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
	.headerLabel
	{
		background-color:#9FB9D0;
		
    }
</style>

<script type="text/javascript">

var day = new Date().getDate();
var month = new Date().getMonth()+1;
var year = new Date().getFullYear();
var queryType = "";
var res = "";

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
					} 
					else if(jsObj.task == "approved" || jsObj.task == "rejected")
					{						
					 getAllProblemsBetweenDates(queryType);
						checkingData(results);
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
	var elmt = document.getElementById("commentsData");
	if(!elmt)
		return;
		
	 var str = '';
     if(results.length >0)
     {
		
		
		
		str +='<div id="" style="text-align:left;padding:10px;">';
		str +='		<table width="100%" style="border:0px; ">';
		str +='			<tr>';
		str +='				<td width="25%"   style="border:0px; border:0px;background-color: #EEF2F3; ">';
		str +='                <input  class="buttonClass" type="button" value="Select All" onclick="selectAll(\'selectValue\');"/ >';
	    str +='                <input class="buttonClass" type="button" value="UnSelect All" onclick="selectAll(\'unselectValue\');"/ >';
		str +='				</td>';
		str +='		       <td width="80%"  style="border:0px;background-color: #EEF2F3; " ><div id="commentsDisplay" ></div></td>';	
		str +='			</tr>';
		str +='		</table>';
		str +='</div>';
	
	    
	    str += '<table border="1px" CELLSPACING="0" align="left">';
        str += '     <tr style="text-align:center">';
	    str += '       <th style="color:#643918;">SELECT</th><th style="color:#643918;">CANDIDATE</th><th style="color:#643918;">POSTED BY</th><th style="color:#643918;">MESSAGE</th><th style="color:#643918;">CONSTITUENCY</th><th  style="color:#643918;">STATUS</th><th  style="color:#643918;">VISIBILITY</th>';
        str += '     </tr>';
		for(i=0 ; i < results.length ; i++)
        {
			str += '<tr style="text-align:center">';
			str += '<td> <form><input type="checkbox" name="check"  value='+results[i].messageToCandidateId+' /></form> </td>';
			str += '<td><a href="candidateElectionResultsAction.action?candidateId='+results[i].candidateId+' ">'+results[i].candidate+'</a></td>';
			str += '<td>'+results[i].postedBY+'</td>';
			str += '<td><textarea cols="20" id="userMessageId'+i+'" value='+results[i].message+'>'+results[i].message+'</textarea></td>';
			str += '<td><a href="constituencyPageAction.action?constituencyId='+results[i].consituencyId+' ">'+results[i].constituency+'</a></td>'; 
			str += '<td>'+results[i].status+'</td>';
			str += '<td>'+results[i].visibility+'</td>';
			str += '</tr>';
		} 
		str+= '</table>';
	}
	else
	{
		str += 'No Comments has been Posted';
	}
	elmt.innerHTML = str;
      if(document.getElementById('commentsDisplay') != null)
	    document.getElementById('commentsDisplay').innerHTML = res;
 }

 function selectAll(varify)
 {
	 var ele = document.getElementsByName("check");
	 if(varify=="selectValue")
	 {
	    for(var i=0;i<ele.length;i++)
	   {
		 ele[i].checked=true;
	   }
    
	 }
	if(varify=="unselectValue")
	{
	   for(var i=0;i<ele.length;i++)
	   {
		 ele[i].checked=false;
	   }
	}
 }

 function checkingData(results)
 {
    var errorDivEle = document.getElementById('commentsDisplay');
	var str = '';

	if(results.resultState == 1)
	{
		
		str += '<font color="green"><b> Message Updated Successfully.</b>';
	}
	else 
	{
		str += '<font color="red"><b>Error Ocuured, Try Again.</b>';
	}
	errorDivEle.innerHTML = str;
	res = str;
 
 }
function controlSelectedReasons(task)
{
	var elmts = document.getElementsByName("check");
	
	var errorElmt = document.getElementById("approveCommentsErrorDiv");

	var checkedElmts = new Array();
	for(var i=0;i<elmts.length;i++){
	   if(elmts[i].checked == true )
		{
			var message_id_obj={
					 updated_message :document.getElementById("userMessageId"+i+"").value,
					 message_candidate_id : elmts[i].value
			}		
			
			 checkedElmts.push(message_id_obj);
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
			
			checkedElmts:checkedElmts,					
			task:task						
	};
	
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/controlMessagesAction.action?"+rparam;					
	callAjax(jsObj,url);
}
function getAllProblemsBetweenDates(task)
{
	var startDate = '';
	var endDate = '';
	var selectstatusEle=document.getElementById("select_staus");
	var selectstatus=selectstatusEle.options[selectstatusEle.selectedIndex].value;

	if(task == "getAllNewPostedReasons")
	{
		queryType =  task;
		startDate = year+'/'+month+'/'+day;
		endDate = year+'/'+month+'/'+day;
	}
	else
	{
        queryType = task;
		startDate = document.getElementById("identifiedFromText").value;
		endDate = document.getElementById("reportedFromText").value;
	}
	
	
	var jsObj=
	{		
			
			fromDate:startDate,
			toDate:endDate,	
			selectstaus:selectstatus,
			task:task						
	};
		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAllMessagesAction.action?"+rparam;					
	callAjax(jsObj,url);
}


function showAllNewPostedReasons()
{
	var elmt = document.getElementById("openedReasons_dates");
	if(!elmt)
		return;
	
	var str = '';
	str += '<div id="openedReasonsDateDiv" style="font-size:13px;font-weight:bold;">';
	str += 'Showing all messages posted today - '+day+'/'+month+'/'+year ;
	
	str += '<input type="button" class="buttonClass" value="Change" onclick="animateShowdiv(\'openedReasonsDateSelectDiv\')">&nbsp;&nbsp;&nbsp;Select Status<select id="select_staus" name="selectstatus" style="width:125px;"><option value="All">All</option><option value="New">New</option><option value="Approved">Approved</option><option value="Rejected">Rejected</option></select>';

	str +'</div>';
	str += '<div id="openedReasonsDateSelectDiv" style="font-size:13px;font-weight:bold;display:none;">';
	str += '<table>';
	str += '<tr>';
	str += '	<th>View posted reasons between </th>';
	str += '	<td><font class="requiredFont"> * </font></td>	';
	str += '	<td>';
	str += '		<input type="text"  READONLY="READONLY" name ="occuredDate" id="identifiedFromText" size="25"/>';
	str += '		<div class="yui-skin-sam"><div id="identifiedFromText_Div" class="tinyDateCal"></div></div>';
	str += '	</td>';
	str += '	<td valign="top">';
	str += '		<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal(\'identifiedFromText_Div\',\'identifiedFromText\',\''+month+'/'+year+'\')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>';
	str += '	</td>';				
		
	str += '	<td><font class="requiredFont"> * </font></td>';
	str += '	<td>';
	str += '		<input type="text" READONLY="READONLY" name ="reportedDate" id="reportedFromText" size="25"/>';
	str += '		<div class="yui-skin-sam"><div id="reportedFromText_Div" class="tinyDateCal"></div></div>';
	str += '	</td>';				
	str += '	<td valign="top">';
	str += '		<a href="javascript:{}" title="Click To Select A Date" onclick="showDateCal(\'reportedFromText_Div\',\'reportedFromText\',\''+month+'/'+year+'\')"><IMG src="images/icons/constituencyManagement/calendar.jpeg" class="calendarWidth" border="0"/></a>';
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

</script>

</head>
<body>	
	<div id="commentsControlMain">		
		
		<div id="reasonsAdminPageHeader"> Control Messages From Candidate page </div>

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
		

		
	</div>

		<script type="text/javascript">
			showAllNewPostedReasons();	
		</script>
	
</body>
</html>