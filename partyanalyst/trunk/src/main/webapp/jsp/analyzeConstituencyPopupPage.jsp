<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Analyze Constituency</title>

<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript" src="js/CommentsDialog/commentsDialog.js"></script>

<link type="text/css" rel="stylesheet" href="styles/CommentsDialog/commentsDialog.css"></link>

<style type="text/css">
	
body
{
	background-color:#FFFFFF;
	direction:ltr;
	font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
	font-size:11px;
	margin:0;
	padding:0;
}

#analyzeContainerMain
{
	padding:40px;
}

.analyzeHeadingDiv
{
	color:#804900;
	font-size:20px;
	text-decoration:underline;
	text-align:center;
}

.analyzeTable th
{
	color:#114057;
	font-size:14px;
	padding:5px;
}

.analyzeTable td
{
	padding:5px;
	font-size:14px;
	color: #5B514A;
}

#analyzebodyDiv
{
	padding:20px;
}

#analyzeTableLabel
{
	border-bottom:2px solid #BC997E;
	margin-bottom:10px;
	padding:5px;
}

#candidateResults
{
	padding:5px;
}

#candidateResults_body table
{
	width:90%;
}

.commentImg
{
	border:0 none;
}

</style>

<script type="text/javascript">
	
	var constituencyId = "${constituencyId}";
	var parliamentConstiId = "${parliamentConstiId}";
	var constituencyName = "${constituencyName}";
	var electionId = '';
	var electionType = '';
	var year = '';
	
	var hidden=1;
	function incrementHidden()
	{
		hidden++;
	}

	function callAjax(jsObj,url)
	{	
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								
								if(jsObj.task == "getCandidateResults")
								{
									buildCandidateResults(jsObj,myResults);
								}
								else if(jsObj.task == "getElectionYearsForConstituency")
								{
									clearOptionsListForSelectElmtId("electionYears");
									createOptionsForSelectElmtIdWithSelectOption("electionYears",myResults);
								}
								else if(jsObj.task == "getCommentsClassificationsList")
								{
									buildCommentsClassificationsOptions(myResults);
								}
								else if(jsObj.task == "getPreviousComments")
								{
									showPreviousComments(myResults,jsObj);
								}
								else if(jsObj.task == "addNewComment")
								{
									updatePreviousCommentsDataTable(myResults);
								}
								
							}catch (e) {   
							  // 	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//	alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}	

	function getCommentsClassifications(rank)
	{ 
		var jsObj={
				rank: rank,
				task: "getCommentsClassificationsList"				
			  }	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/commentsClassificationDataAction.action?"+rparam;		
	callAjax(jsObj,url);
	}

	function showExistingComments(id,candidateName,category, constituencyId,constituencyName,partyName)
	{
		
		var candidateId;
		var constituencyId;
		if(category == "candidate")
		{
			candidateId = id;
			constituencyId = constituencyId;		
		}

		var jsObj={
				
				candidateId: candidateId,
				candidateName: candidateName,
				constituencyId: constituencyId,
				constituencyName: constituencyName,
				electionId: electionId,
				electionType: electionType,
				year: year,				
				partyName: partyName,			
				task:"getPreviousComments"				
			  }

		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);//+rparam+"&hidden="+hidden incrementHidden();
		incrementHidden();
		var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam+"&hidden="+hidden;		
		callAjax(jsObj,url);	
	}

	function handleAddCommentsSubmit(id,category,constituencyId)
	{
		var commentVal = document.getElementById("commentText").value; 
		var postedByVal = document.getElementById("commentPostedByText").value;
		var partyId;
		var candidateId;
		var constituencyId;
		var commentCategoryId;
		var alertMessageEl = document.getElementById("alertMessage"); 
		if(category == "candidate")
		{
			var commentCategoryEl = document.getElementById("commentsClassificaitonSelectBox");
			if(commentCategoryEl)
			{
				commentCategoryId = commentCategoryEl.value;
				
			}	
			partyId = '0';
			candidateId = id;
			constituencyId = constituencyId;		
		}
		
		
		if(commentCategoryId == '' || commentVal == '' || postedByVal == '' || commentCategoryId == 'Select Classification' )		
		{
			alertMessageEl.innerHTML = 'Please Fill Mandatory Fields!';
			return;		
		}	
		if(commentCategoryId != '' && commentVal != '' && postedByVal != '')		
		{
			var jsObj={
					electionId: electionId,
					electionType: electionType,
					year: year,
					partyId: partyId,
					candidateId: candidateId,
					constituencyId: constituencyId,
					commentDesc: commentVal,
					postedBy: postedByVal,
					category: category,
					commentCategoryId: commentCategoryId,
					task:"addNewComment"				
				  }	 
				
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam;		
			callAjax(jsObj,url);	
			alertMessageEl.innerHTML = '';
		}
				
		
	}
	function getCandidatesResults(constElecId)
	{
		if(constElecId == 0)
			return;

		var jsObj=
		{
				constElecId:constElecId,
				task:"getCandidateResults"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getCandidateResultsInConstituencyForAnalyzeAction.action?"+rparam;
		callAjax(jsObj,url);
	}

	function buildCandidateResults(jsObj,results)
	{
		var elmtHead = document.getElementById("candidateResults_head");
		var elmtBody = document.getElementById("candidateResults_body");

		if(!elmtHead || !elmtBody)
			return;
		
		var hStr = '';
		hStr += '<font class="analyzeHeadingDiv" style="font-size:14px;font-weight:bold;"> Candidate Results </font>';
		elmtHead.innerHTML = hStr;
		
		if(results == null || results.length == 0)
		{
			elmtBody.innerHTML = 'No Candidate Results found.';
			return;
		}

		var jsArray =new Array();
		
		electionId = results[0].electionId;
		electionType = results[0].electionType;
		year = results[0].year;

		for(var i=0; i<results.length; i++)
		{
			var rankStatus;
			
			if(results[i].status == 1)
				rankStatus = "Won";
			else
				rankStatus = "Lost";
			var obj =	{
							candidateName:results[i].candidateName,
							party:results[i].party,
							status:results[i].status,
							rankStatus:rankStatus,							
							comment: '<A href="javascript:{}" title="Click To View/Add Reasons" onclick="showCommentsDialog(\''+results[i].id+'\',\''+results[i].candidateName+'\',\'candidate\',\''+results[i].status+'\',\''+constituencyId+'\',\''+constituencyName+'\',\''+results[i].party+'\',\''+jsObj.task+'\',\'0\')"><IMG src="images/icons/electionResultsReport/notes.png" border="none"></IMG></A>'
						};
			jsArray.push(obj);
		}

		var myColumnDefs = [
            {key:"candidateName", sortable:true, label:"Canidate Name", resizeable:true},
            {key:"party",  sortable:true, label:"Party", resizeable:true},
            {key:"status", formatter:YAHOO.widget.DataTable.formatNumber, label:"Rank", sortable:true, resizeable:true},
            {key:"rankStatus",  sortable:true, label:"Status", resizeable:true},
			{key:"comment",  sortable:false, label:"Comment", resizeable:false}
        ];

        var myDataSource = new YAHOO.util.DataSource(jsArray);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["candidateName","party","status","rankStatus","comment"]
        };

        var myDataTable = new YAHOO.widget.DataTable("candidateResults_body",
                myColumnDefs, myDataSource);

	}
	
	function getElectionYears(value)
	{
		var id;

		if(value == "assembly")
			id = constituencyId;
		else if(value == "parliament")
			id = parliamentConstiId;
		
		
		var jsObj=
		{
				
				constituencyId:id,
				task:"getElectionYearsForConstituency"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getElectionYearsInConstituencyForAnalyzeAction.action?"+rparam;
		callAjax(jsObj,url);
	}

</script>
</head>
<body class="yui-skin-sam">
	<div id="commentsDialogDiv"></div>
	<div id="analyzeContainerMain">
		<div id="analyzeHeadingDiv" class="analyzeHeadingDiv"> Analyze Constituency</div>
		<div id="analyzebodyDiv">
			<div id="analyzeTableLabel">
				<table class="analyzeTable">	
				<tr>
					<td><img src="images/icons/infoicon.png"></td>
					<td style="font-weight:bold;">Please select election type and election year to view candidates results.</td>
				</tr>
				</table>
			</div>

			<div style="height:50px;">
				<table class="analyzeTable">						
					<tr>
						<th>Election Type</th>
						<td width="250px">
							<input type="radio" name="electionTypeRadio" value="assembly" checked="checked" onclick="getElectionYears(this.value)"/>Assembly
							<input type="radio" name="electionTypeRadio" value="parliament" onclick="getElectionYears(this.value)"/>Parliament
						</td>

						<th>Election Year</th>
						<td>
							<s:select theme="simple" id="electionYears" list="electionYears" listKey="id" listValue="name" headerKey="0" headerValue="select" onchange="getCandidatesResults(this.options[this.selectedIndex].value)"></s:select>				
						</td>
					</tr>
				</table>
			</div>
			<div id="candidateResults">
				<div id="candidateResults_head" style="height:50px;"></div>
				<div id="candidateResults_body"></div>
			</div>
		</div>		
	</div>

</body>
</html>