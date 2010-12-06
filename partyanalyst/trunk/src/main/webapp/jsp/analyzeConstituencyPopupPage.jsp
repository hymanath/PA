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

<!-- Local Files-->
	<SCRIPT type="text/javascript" src="js/ElectionResultsAnalysisReport/electionResultsAnalysisReport.js"></SCRIPT>
	<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
	<LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/electionResultsAnalysisReport.css">
	
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

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>

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

#commentsResults_body
{
	padding:5px;
}

.commentsContent_head
{
	font-size:14px;
	font-weight:normal;
	height:20px;
}

.commentsContent_body
{
	padding:10px 10px 10px 25px;
}



.commentContentTable td
{
	color:#39342E;
	font-size:12px;
	padding:5px;
}

.commentsContent_head_table th
{
	color:#5F3F08;
}

.commentsDetailsLabel
{
	color:#04325A;
	font-size:11px;
	font-weight:bold;
	text-decoration:underline;
	font-size:12px;
	height:25px;
}

.commentContent_candidate_table th
{
	padding:5px;
	font-size:12px;
	color:#39342E;
}

.commentContent_comment
{
	padding:7px;
}

.noCommentFont
{
	color:#39342E;
	font-size:12px;
}

.commentsDataMainDiv
{
	margin-top:10px;
	margin-bottom:20px;
}

</style>

<script type="text/javascript">
	
	var constituencyId = "${constituencyId}";
	var parliamentConstiId = "${parliamentConstiId}";
	var constituencyName = "${constituencyName}";
	var electionId = '';
	var electionType = '';
	var year = '';
	var taskType = "${taskType}";	
	var userId = "${userId}";
	var hidden=1;
	var displayBody;

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
								else if(jsObj.task == "getCommentsResults")
								{
									buildCommentsResults(jsObj,myResults);
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
							radio:'<input type="radio" name="candidateRadio" value="'+results[i].id+'" onclick="showPostComments(this.value,\''+results[i].candidateName+'\',\'candidate\',\''+results[i].status+'\',\''+constituencyId+'\',\''+constituencyName+'\',\''+results[i].party+'\',\''+jsObj.task+'\',\'0\')"></input>',
							candidateName:results[i].candidateName,
							party:results[i].party,
							status:results[i].status,
							rankStatus:rankStatus,							
							comment: '<A href="javascript:{}" title="Click To View/Add Reasons" onclick="showCommentsDialog(\''+results[i].id+'\',\''+results[i].candidateName+'\',\'candidate\',\''+results[i].status+'\',\''+constituencyId+'\',\''+constituencyName+'\',\''+results[i].party+'\',\''+jsObj.task+'\',\'0\')"><IMG src="images/icons/electionResultsReport/notes.png" border="none"></IMG></A>'
						};
			jsArray.push(obj);
		}

		var myColumnDefs = [
			{key:"radio",  sortable:false, label:"Select", resizeable:false},
            {key:"candidateName", sortable:true, label:"Canidate Name", resizeable:true},
            {key:"party",  sortable:true, label:"Party", resizeable:true},
            {key:"status", formatter:YAHOO.widget.DataTable.formatNumber, label:"Rank", sortable:true, resizeable:true},
            {key:"rankStatus",  sortable:true, label:"Status", resizeable:true}
        ];

        var myDataSource = new YAHOO.util.DataSource(jsArray);
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["radio","candidateName","party","status","rankStatus"]
        };

        var myDataTable = new YAHOO.widget.DataTable("candidateResults_body",
                myColumnDefs, myDataSource);

	}
	
	function showPostComments(id,candidateName,category, rank,constituencyId,constituencyName,partyName,task,status)
	{
		var elmtHead = document.getElementById("candidateComments_head");
		var elmtBody = document.getElementById("candidateComments_body");

		var hStr = '';
		hStr += '<font class="analyzeHeadingDiv" style="font-size:14px;font-weight:bold;"> Candidate Details </font>';
		elmtHead.innerHTML = hStr;

		var str = '';
		//str += '<div id="candidateDetails">';
		//str += '</div>';
		//str += '<div id="candidateComments">';
		//str	+= '<FIELDSET align="center">';
		//str	+= '<LEGEND><B>Comments Details</B></LEGEND>';
		str += '<div id="candidateInfo_main" class="commentsDataMainDiv">';
		str	+= '<div id="candidateInfo_head" align="left"></div>';
		str += '<div id="candidateInfo_body" align="left"></div>';
		str += '</div>';
		
		str += '<div id="previousComments_main" class="commentsDataMainDiv">';
		str += '<div id="previousComments_head">';
		str += '	<font class="analyzeHeadingDiv" style="font-size:14px;font-weight:bold;"> Previous Comments </font>';
		str += '</div>';
		str	+= '<div id="previousComments"></div>';
		str += '</div>';
		//str	+= '</FIELDSET>';	

		//str+='<FIELDSET align="center">';
		//str+='<LEGEND><B>Add New Comment</B></LEGEND>';
		
		str+='<div id="postNewComment_main" class="commentsDataMainDiv">';
		str+='<div id="postNewComment_head"><font class="analyzeHeadingDiv" style="font-size:14px;font-weight:bold;"> Add New Comment</font></div>';
		str+='<DIV id="alertMessage" style="padding:10px;">Fields marked with * are Mandatory</DIV>';
		str+='<DIV>';
		str+='<TABLE width="100%" class="commentsInputTable" border="1" cellspacing="0" cellpadding="0">';
		if(rank != 'null' && category == 'candidate')
		{
			str+='<TR>';
			str+='<TD align="left" class="commentsInputTd">Reasons*</TD>';	
			str+='<TD class="commentsInputTd" align="left"><SELECT style="width:300px;" id="commentsClassificaitonSelectBox"  name="selectBox" style="display:block;">';
			str+='<OPTION id="0" >Select Reason</OPTION>';
			str+='</SELECT></TD>';
			str+='</TR>';
			getCommentsClassifications(rank);
			
		}	
		str+='<TR>';
		str+='<TD align="left" valign="top" class="commentsInputTd">Comment*</TD>';	
		str+='<TD class="commentsInputTd" valign="top" align="left"><TEXTAREA style="width:300px;" id="commentText" name="commentText"></TEXTAREA></TD>';
		str+='</TR>';
		str+='<TR>';
		str+='<TD align="left" class="commentsInputTd" valign="top">Posted By*</TD>';	
		str+='<TD class="commentsInputTd" valign="top" align="left"><input type="text" style="width:300px;" id="commentPostedByText" name="commentPostedByText"/></TD>';
		str+='</TR>';
		str+='</TABLE>';
		str+='</DIV>';
		//str+='</FIELDSET>';
		
		str+='<DIV style="text-align:right;"><INPUT type="button" class="button" id="addCommentsButton" style="width:50px;" onclick="handleAddCommentsSubmit('+id+',\''+category+'\','+constituencyId+')" value="Save"/>';
		str+='<INPUT type="button" id="addCommentsButton" style="width:50px;" class="button" onclick="handleAddCommentsCancel(\''+task+'\',\''+status+'\')" value="Exit"/></DIV>';
		str+='</div>';
		str+='</div>';

		str += '</div>';		
		
		elmtBody.innerHTML = str;

		showExistingComments(id,candidateName,category,constituencyId,constituencyName,partyName);

	}
	
	function buildCommentsClassificationsOptions(results)
	{
		
		var commentClassifyEl = document.getElementById("commentsClassificaitonSelectBox");
		for(var i in results)
		{
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
		
			try
				{
				commentClassifyEl.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				commentClassifyEl.add(opElmt); // IE only
				}
		}
	}
	
	function updatePreviousCommentsDataTable(results)
	{
		var dtArray = new Array();
		var commentVal = document.getElementById("commentText"); 
		var postedByVal = document.getElementById("commentPostedByText"); 
		var commentCategoryEl = document.getElementById("commentsClassificaitonSelectBox");
		var previousCommentsEl = document.getElementById("previousComments");
			 
		
		if(previousCommentsEl.innerHTML == 'No Previous Comments')
		{
			var newCommentDataObj=
			{		
					comment: results.candidateCommentsSaved.commentDesc,
					classification: results.candidateCommentsSaved.commentCategory,
					commentedBy: results.candidateCommentsSaved.commentedBy, 
					date: results.candidateCommentsSaved.commentedOn  		
			};

			dtArray.push(newCommentDataObj);
			buildPreviousCommentsDataTable(dtArray);		
			
		} else
		{
			var newCommentDataObj=
			{		
					comment: results.candidateCommentsSaved.commentDesc,
					classification: results.candidateCommentsSaved.commentCategory,
					commentedBy: results.candidateCommentsSaved.commentedBy, 
					date: results.candidateCommentsSaved.commentedOn  		
			};
			
			previousCommentsDataTable.addRow(newCommentDataObj,0);
		}
		commentVal.value='';
		postedByVal.value='';
		commentCategoryEl.selectedIndex='0';		
	}

	function showPreviousComments(results,jsObj)
	{
		var previousComments = results.candidateCommentsVO;
		var previousCommentsEl = document.getElementById("previousComments");
		var candidateInfoEl = document.getElementById("candidateInfo_body");
		var party = jsObj.partyName;
		var constituencyName = jsObj.constituencyName;
		var candidateName = jsObj.candidateName;
		var commentsData = new Array();	
		var year = jsObj.year;
		var contentStr = '';	
			contentStr+='<TABLE width="100%" class="commentsInputTable">';
			contentStr+='<TR>';
			contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Candidate:</B></TD>';
			contentStr+='<TD style="width:30%;" class="commentsInputTd">'+candidateName+'</TD>';
			contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Election:</B></TD>';
			contentStr+='<TD style="width:30%;" class="commentsInputTd">'+electionType+''+year+'</TD>';		
			contentStr+='</TR>';
			contentStr+='<TR>';
			contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Party:</B></TD>';
			contentStr+='<TD style="width:30%;" class="commentsInputTd">'+party+'</TD>';
			contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Constituency:</B></TD>';
			contentStr+='<TD style="width:30%;" class="commentsInputTd">'+constituencyName+'</TD>';		
			contentStr+='</TR>';
			contentStr+='</TABLE>';
			candidateInfoEl.innerHTML = contentStr;		
			if(previousComments != null)
			{	
				
				for(var i in previousComments)
				{
					var commentObj = {
							comment: previousComments[i].commentDesc,
							classification: previousComments[i].commentCategory,
							commentedBy: previousComments[i].commentedBy, 
							date: previousComments[i].commentedOn							
							};
					commentsData.push(commentObj);					
				}			
				buildPreviousCommentsDataTable(commentsData);
			} else 
				{
				previousCommentsEl.innerHTML="No Previous Comments";
				}	
	}

	function buildPreviousCommentsDataTable(data)
	{
		//previousComments
		var previousCommentsColumnDefs = [
													{key: "comment", label: "Comment", sortable:true},	
													{key: "classification", label: "Classification", sortable:true},		
													{key: "commentedBy", label: "CommentedBy", sortable:true},	
													{key: "date", label: "Date", sortable:true}	               								             		              	 	 		              	 	 				              	 	 		              	 	 			              	 	 	
													];                	 	    

							var previousCommentsDataSource = new YAHOO.util.DataSource(data); 
							previousCommentsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
							previousCommentsDataSource.responseSchema = {
									  fields: [ "comment", "classification", "commentedBy", "date"]     
							};
							var myConfigs = { 
									paginator : new YAHOO.widget.Paginator({ 
									rowsPerPage    : 5	               							        
									}),
									caption:"Previous Comments" 
									};
							
							previousCommentsDataTable = new YAHOO.widget.DataTable("previousComments", previousCommentsColumnDefs, previousCommentsDataSource,myConfigs);

							return { 
								oDS: previousCommentsDataSource, 
								oDT: previousCommentsDataTable
						 };		            	
						
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
	
	function getCommentsResults(constElecId)
	{
		if(constElecId == 0)
			return;

		var jsObj=
		{
				constElecId:constElecId,
				task:"getCommentsResults"						
		};

		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getCommentsResultsForElectionYearAction.action?"+rparam;
		callAjax(jsObj,url);
	}
	
	function buildCommentsResults(jsObj,results)
	{
		var elmtHead = document.getElementById("commentsResults_head");
		var elmtBody = document.getElementById("commentsResults_body");

		if(!elmtHead || !elmtBody)
			return;
		
		var hStr = '';
		hStr += '<font class="analyzeHeadingDiv" style="font-size:14px;font-weight:bold;"> Coments Results </font>';
		elmtHead.innerHTML = hStr;

		var str = '';
		for(var i=0; i<results.length; i++)
		{
			str += '<div id="commentsContent_main_'+i+'" class="commentsContentMain">';
			if(results[i].rank == 1)
			{
				str += '<div class="commentsContent_head">';
				str += '<table class="commentsContent_head_table">';
				str += '<tr>';
				str += '<td><img width="15" height="30" src="images/icons/jQuery/next.png"></img></td>';
				str += '<th>Resons for '+results[i].partyName+' Won in this constituency</th>';
				str += '</table>';
				str += '</div>';
			}
			else
			{
				str += '<div class="commentsContent_head">';
				str += '<table class="commentsContent_head_table">';
				str += '<tr>';
				str += '<td><img width="15" height="30" src="images/icons/jQuery/next.png"></img></td>';
				str += '<th>Resons for '+results[i].partyName+' Lost in this constituency</th>';
				str += '</table>';
				str += '</div>';
			}

			str += '<div class="commentsContent_body">';

			str += '<div class="commentContent_candidate">';
			str += '<table class="commentContent_candidate_table">';
			str += '<tr>';
			str += '<th align="left">Candidate Name</th>';
			str += '<td>:</td>';
			str += '<th align="left">'+results[i].candidate+'</th>';			
			str += '</tr>';
			str += '<tr>';
			str += '<th align="left">Posted Users</th>';
			str += '<td>:</td>';
			str += '<th align="left">'+results[i].postedUsersCount+'</th>';			
			str += '</tr>';
			str += '</table>';
			str += '</div>';

			str += '<div class="commentContent_comment">';
			str += '<div class="commentsDetailsLabel"> Reasons </div>';
			if(results[i].commetsAndScores == null || results[i].commetsAndScores.length == 0)
			{
				str += '<font class="noCommentFont">No comments has been posted </font>';
			}
			else
			{
				str += '<table class="commentContentTable">';
				for(var j=0; j<results[i].commetsAndScores.length; j++)
				{
					var data = results[i].commetsAndScores[j];				
					
					str += '<tr>';
					str += '<td><img src="images/icons/districtPage/listIcon.png"></img></td>';
					str += '<td>'+data.commentCategory+'</td>';
					str += '<td>'+data.commentScore+'</td>';
					str += '</tr>';
				}
				str += '</table>';
			}
			str += '</div>';

			str += '</div>';

			str += '</div>';
		}			

		elmtBody.innerHTML = str;
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
					<c:if test="${taskType == 'analyze'}">
						<td style="font-weight:bold;">Please select election type and election year to view candidates results.</td>
					</c:if>
					<c:if test="${taskType == 'viewResults'}">
						<td style="font-weight:bold;">Please select election type and election year to view previous comments.</td>
					</c:if>
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
						<c:if test="${taskType == 'analyze'}">
							<s:select theme="simple" id="electionYears" list="electionYears" listKey="id" listValue="name" headerKey="0" headerValue="select" onchange="getCandidatesResults(this.options[this.selectedIndex].value)"></s:select>					
						</c:if>
						<c:if test="${taskType == 'viewResults'}">
							<s:select theme="simple" id="electionYears" list="electionYears" listKey="id" listValue="name" headerKey="0" headerValue="select" onchange="getCommentsResults(this.options[this.selectedIndex].value)"></s:select>					
						</c:if>
							
						</td>
					</tr>
				</table>
			</div>

			<div id="commentsResults">
				<div id="commentsResults_head" style="height:50px;"></div>
				<div id="commentsResults_body"></div>
			</div>

			<div id="candidateResults">
				<div id="candidateResults_head" style="height:50px;"></div>
				<div id="candidateResults_body"></div>
			</div>

			<div id="candidateComments">
				<div id="candidateComments_head" style="height:50px;"></div>
				<div id="candidateComments_body"></div>
			</div>			

		</div>		
	</div>

</body>
</html>