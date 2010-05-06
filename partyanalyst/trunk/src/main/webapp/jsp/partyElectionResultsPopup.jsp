<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></SCRIPT> 
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></SCRIPT>
<script type="text/javascript" src="js/CommentsDialog/commentsDialog.js"></script>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></SCRIPT>
<LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/partyElectionResultsReport.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<LINK rel="stylesheet" type="text/css" href="styles/CommentsDialog/commentsDialog.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<c:if test="${electionType != 'Parliament' && rank =='0'}"><TITLE>${stateName} ${electionType} ${electionYear} Lost Constituencies Results</TITLE></c:if>
<c:if test="${electionType != 'Parliament' && rank =='1'}"><TITLE>${stateName} ${electionType} ${electionYear} Won Constituencies Results</TITLE></c:if>
<c:if test="${electionType == 'Parliament' && rank =='0'}"><TITLE>${electionType} ${electionYear} Lost Constituencies Results</TITLE></c:if>
<c:if test="${electionType == 'Parliament' && rank =='1'}"><TITLE>${electionType} ${electionYear} Won Constituencies Results</TITLE></c:if>
<SCRIPT type="text/javascript">
var hidden=1;
function incrementHidden()
{
	hidden++;
}
function callAjax(param,jsObj,url){
	var myResults;

		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);		
								
								var imgElmt = document.getElementById("barloaderGif");

								if(imgElmt.style.display == "block")
										imgElmt.style.display = "none"
								else if(imgElmt.style.display == "none")
									imgElmt.style.display == "block"

								if(jsObj.task == "getConstituencyResults")
								{								
									showConstituencyResults(myResults);
								}
								if(jsObj.task == "getCommentsClassificationsList")
								{
									buildCommentsClassificationsOptions(myResults);
								}
								if(jsObj.task == "getPreviousComments")
								{
									showPreviousComments(myResults,jsObj);
								}
								if(jsObj.task == "addNewComment")
								{
									updatePreviousCommentsDataTable(myResults);
								}
								if(jsObj.task == "getElectionsYears")
								{
									populateYearSelectionBox(myResults);
								}
								if(jsObj.task == "getConstituencyStatusAnalysisForVotesMarginWindow")
								{
									showConstituencyResults(myResults);
								}
						}
						catch (e) {   
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
</SCRIPT>
</HEAD>
<BODY>
	<CENTER>
	<c:if test="${electionType != 'Parliament' && rank =='0'}">
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${stateName} ${electionType} ${electionYear} Lost Constituencies Results </H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>
	</c:if>	
	<c:if test="${electionType != 'Parliament' && rank =='1'}">
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${stateName} ${electionType} ${electionYear} Won Constituencies Results </H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>	
	</c:if>
	<c:if test="${electionType == 'Parliament' && rank =='0'}">
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${electionType} ${electionYear} Lost Constituencies Results</H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>	
	</c:if>
	<c:if test="${electionType == 'Parliament' && rank =='1'}">	
		<TABLE border="0" cellpadding="0" cellspacing="0">
		<TR>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png"/></TD>
		<TD valign="top"><H3>${electionType} ${electionYear} Won Constituencies Results</H3></TD>
		<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png"/></TD>
		</TR>
		</TABLE>	
	</c:if>

		<img id="barloaderGif" style="display:block;" src="images/icons/barloader.gif "/>

	</CENTER>
	<DIV id="showConstituencyResults"></DIV>
	<DIV class="yui-skin-sam"><DIV id="candidateResults"></DIV></DIV>
	<DIV class = "yui-skin-sam"><DIV id="commentsDialogDiv"></DIV></DIV>
	<DIV class = "yui-skin-sam"><DIV id="yearsDialogDiv"></DIV></DIV>
	
	
	<SCRIPT type="text/javascript">
	var electionType="${electionType}";
	var electionYear="${electionYear}";	
	var electionId = '${electionId}';
	var partyId = '${partyId}';
	var rank = '${rank}';
	var electionTypeId = '${electionTypeId}';
	var partyElectionResultsObj={
		candidateResultsArr:[]
	};
	var candidateElectionResultsDataTable,selectYearDialog;
	function showConstituencyResults(results)
	{
		var candidateElectionResults = results.candidateElectionResultsVO;
		var showConstituencyResultsEl = document.getElementById("showConstituencyResults");
		var contentStr='';
		contentStr+='<TABLE width="50%">';
		contentStr+='<TR>';
		contentStr+='<TH >Party:</TH>';
		contentStr+='<TD >'+results.partyLongName+' ( '+results.partyShortName+')</TD>';		
		contentStr+='<TH >Flag:</TH>';
		contentStr+='<TD ><IMG src="images/party_flags/'+results.partyFlag+'" height="30" width="50"/></TD>';
		contentStr+='</TR>';
		contentStr+='</TABLE>';
		showConstituencyResultsEl.innerHTML = contentStr;
		
		for(var i  in candidateElectionResults)
		{
			if(candidateElectionResults[i].rank == null)
			{	
				var ob={
						candidateName: candidateElectionResults[i].candidateName,
						constituencyName: candidateElectionResults[i].constituencyName,
						totalValidVotes: candidateElectionResults[i].totalValidVotes,
						totalVotesEarned: candidateElectionResults[i].totalVotesEarned,
						votesPercentage: candidateElectionResults[i].votesPercentage,
						rank: '1',						
						comments: '<A onclick="showCommentsDialog('+candidateElectionResults[i].candidateId+',\''+candidateElectionResults[i].candidateName+'\',\'candidate\',1,'+candidateElectionResults[i].constituencyId+',\''+candidateElectionResults[i].constituencyName+'\',\''+results.partyLongName+'\')" title="Click to View/Add your comments"   href="javascript:{}">'+candidateElectionResults[i].userComments+'</A>',
						moreDetails: '<A onclick="getMoreDetails('+candidateElectionResults[i].constituencyId+')" title="Click to View Detailed Results" href="javascript:{}">More Details</A>',
						otherResults: '<A onclick="showYearsDialog('+candidateElectionResults[i].constituencyId+','+electionTypeId+',\''+electionType+'\')" title="Click to View Detailed Results" href="javascript:{}">View Other Election Results</A>' 						
					};
				partyElectionResultsObj.candidateResultsArr.push(ob);
			} else if(candidateElectionResults[i].rank != null)
			{
				var obj={
						candidateName: candidateElectionResults[i].candidateName,
						constituencyName: candidateElectionResults[i].constituencyName,
						totalValidVotes: candidateElectionResults[i].totalValidVotes,
						totalVotesEarned: candidateElectionResults[i].totalVotesEarned,
						votesPercentage: candidateElectionResults[i].votesPercentage,
						rank: candidateElectionResults[i].rank,						
						comments: '<A onclick="showCommentsDialog('+candidateElectionResults[i].candidateId+',\''+candidateElectionResults[i].candidateName+'\',\'candidate\','+candidateElectionResults[i].rank+','+candidateElectionResults[i].constituencyId+',\''+candidateElectionResults[i].constituencyName+'\',\''+results.partyLongName+'\')" title="Click to View/Add your comments"   href="javascript:{}">'+candidateElectionResults[i].userComments+'</A>',					
						moreDetails:'<A onclick="getMoreDetails('+candidateElectionResults[i].constituencyId+')" title="Click to View Detailed Results" href="javascript:{}">More Details</A>',
						otherResults: '<A onclick="showYearsDialog('+candidateElectionResults[i].constituencyId+','+electionTypeId+',\''+electionType+'\')" title="Click to View Detailed Results" href="javascript:{}">View Other Election Results</A>' 							
					};
				partyElectionResultsObj.candidateResultsArr.push(obj);
			}	
		}
		buildCandidateElectionResultsDataTable();		
	}
		
		
	function buildCandidateElectionResultsDataTable()
	{	
		var candidateElectionResultsColumnDefs = [
									{key: "candidateName", label: "Candidate", sortable:true},										
			              	 	    {key: "constituencyName", label: "Constituency", sortable:true},
			              	 	 	{key: "totalValidVotes", label: "Valid Votes",formatter:"number", sortable:true},
			              	 	 	{key: "totalVotesEarned", label: "Votes Earned",formatter:"number", sortable:true},
			              	 	 	{key: "votesPercentage", label: "Votes Percentage",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
			              	 	 	{key: "rank", label:"Rank", sortable:true},
			              	 	 	{key: "comments", label:"Comments"},
			              	 	 	{key: "moreDetails", label:"MoreDetails"},
			              	 	    {key: "otherResults", label:"OtherElectionsResults"}			              	 	 			              	 	 		              	 	 	
			              	 	    ];                	 	    

			var candidateElectionResultsDataSource = new YAHOO.util.DataSource(partyElectionResultsObj.candidateResultsArr); 
			candidateElectionResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			candidateElectionResultsDataSource.responseSchema = {
	                fields: [		  {key:"candidateName"},
	                         		  {key:"constituencyName"},
	                         		  {key:"totalValidVotes", parser:"number"},
	                         		  {key:"totalVotesEarned", parser:"number"},
	                         		  {key:"votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
	                         		  "comments", 
	                         		 {key:"rank", parser:"number"},
	                         		 "moreDetails","otherResults"
	                         		  ] 
	        		};

			var myConfigs = { 
				    paginator : new YAHOO.widget.Paginator({ 
				    	rowsPerPage    : 50,
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [50,100,150,200], 
					    pageLinks: 50			        
				    })
				     
					};
			
			candidateElectionResultsDataTable = new YAHOO.widget.DataTable("candidateResults", candidateElectionResultsColumnDefs, candidateElectionResultsDataSource,myConfigs);						
	        	     	
		
	}
	
	function getMoreDetails(constiId)
	{	

	var urlStr = "<%=request.getContextPath()%>/constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+electionType+"&electionYear="+electionYear;
	var browser2 = window.open(urlStr,"candidateResults","scrollbars=yes,height=600,width=750,left=200,top=200");
		 browser2.focus();
	}
	function getConstituencyResults(){
	
		var jsObj= 
		{		
		 	electionId: electionId,
		 	partyId: partyId,
		 	rank:rank,				
			task:"getConstituencyResults"		
		}
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		incrementHidden();//.action?"+rparam+"&hidden="+hidden
		var url = "<%=request.getContextPath()%>/partyElectionResultsAjaxAction.action?"+param+"&hidden="+hidden;
		callAjax(param,jsObj,url);
	}
	function getCommentsClassifications(rank)
	{ 
		var jsObj={
				rank: rank,
				task: "getCommentsClassificationsList"				
			  }	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/commentsClassificationDataAction.action?"+rparam;		
	callAjax(rparam,jsObj,url);
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
				electionId: electionId,
				electionType: electionType,
				year: electionYear,
				candidateId: candidateId,
				constituencyId: constituencyId,
				constituencyName: constituencyName,
				partyName: partyName,			
				task:"getPreviousComments"				
			  }
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		incrementHidden();
		var url = "<%=request.getContextPath()%>/commentsDataAction.action?"+rparam+"&hidden="+hidden;		
		callAjax(rparam,jsObj,url);	
	}
	/*
	function showPreviousComments(results,jsObj)
	{
		var previousComments = results.candidateCommentsVO;
		var previousCommentsEl = document.getElementById("previousComments");
		var candidateInfoEl = document.getElementById("candidateInfo");
		var party = jsObj.partyName;
		var constituencyName = jsObj.constituencyName;
		var candidateName = jsObj.candidateName;
		var commentsData = new Array();	 
		var contentStr = '';	
			contentStr+='<TABLE width="100%" class="commentsInputTable">';
			contentStr+='<TR>';
			contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Candidate:</B></TD>';
			contentStr+='<TD style="width:30%;" class="commentsInputTd">'+candidateName+'</TD>';
			contentStr+='<TD style="width:20%;" class="commentsInputTd"><B>Election:</B></TD>';
			contentStr+='<TD style="width:30%;" class="commentsInputTd">'+electionType+''+electionYear+'</TD>';		
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
	}*/
	/*
	function buildPreviousCommentsDataTable(data)
	{
		
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
		               	
	}*/
	/*
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
	*/	
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
		if(category == "party")
		{
			partyId = id;
			candidateId = '0';
			constituencyId = '0';
			commentCategoryId = '0';	
			
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
					year: electionYear,
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
			callAjax(rparam,jsObj,url);	
			alertMessageEl.innerHTML = '';
		}
				
		
	}
	function showYearsDialog(constituencyId,electionTypeId,electionType)
	{
		var elmt = document.getElementById('commentsDialogDiv');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','addCommentDiv');
		var contentStr='';
		contentStr+='<div class="hd" align="left">Please Select An Election Year</div>';
		contentStr+='<div class="bd" align="left">';
		contentStr+='<TABLE>';
		contentStr+='<TR>';
		contentStr+='<TD>Year:</TD>';
		contentStr+='<TD>';
		contentStr+='<SELECT style="width:200px;" id="yearSelectBox"  name="selectBox" onchange="getOtherYearResults(this.options[this.selectedIndex].text,'+constituencyId+',\''+electionType+'\')">';
		contentStr+='<OPTION id="0" >Select Year</OPTION>';
		contentStr+='</SELECT></TD>';
		contentStr+='</TD>';
		contentStr+='</TR>';
		contentStr+='</TABLE>';
		divChild.innerHTML=contentStr;
		elmt.appendChild(divChild);
		
		if(selectYearDialog)
			selectYearDialog.destroy();
		selectYearDialog = new YAHOO.widget.Dialog("addCommentDiv",
				{ 
				  width : "300px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:400,
				  y:400,				  
				  buttons : []
	             } ); 
		selectYearDialog.render();
		getElectionYears(electionType,electionTypeId);
		
	}
	function getElectionYears(electionType,electionTypeId)
	{
		var jsObj= 
		{
			electionType: electionType,
			electionTypeId: electionTypeId,
			stateID:"1",			
			task:"getElectionsYears"		
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/electionYearsDialogAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	function handleAddCommentsCancel()
	{
		getConstituencyResults();
		addCommentsDialog.hide();

	}
	function populateYearSelectionBox(results)
	{
		var electionYearsEl = document.getElementById("yearSelectBox");
		removeSelectElements(electionYearsEl);
		for(var i in results)
		{
			if(electionYear != results[i].name)
			{	
				var opElmt=document.createElement('option');
				opElmt.value=results[i].id;
				opElmt.text=results[i].name;
			
			try
				{
				electionYearsEl.add(opElmt,null); // standards compliant
				}
			catch(ex)
				{
				electionYearsEl.add(opElmt); // IE only
				}
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
	function getOtherYearResults(electionYear,constiId,electionType)
	{	
		
		var urlStr = "<%=request.getContextPath()%>/constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+electionType+"&electionYear="+electionYear;
		var browser3 = window.open(urlStr,"candidateResults1","scrollbars=yes,height=600,width=750,left=200,top=200");
			 browser3.focus();
			 selectYearDialog.hide();
	}

	function getMainPartyMarginCountAnalysis(index,resultStatus)
	{
		var parent = window.opener;
		var nominationIds1 = new Array();
		
		/*if(rank == 0)
			nominationIds1 = parent.electionAnalysisObj.marginVotesInfoLost[index].nominationIds;
		else if(rank == 1)
			nominationIds1= parent.electionAnalysisObj.marginVotesInfoWon[index].nominationIds;*/
		
		var jsObj= 
		{			
			partyId: partyId,
			electionId:electionId,
			resultStatus:resultStatus,
			clickIndex:index,
			task:"getConstituencyStatusAnalysisForVotesMarginWindow"		
		}
		
		//jsObj.nominationIds = nominationIds1;
		
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);		
		var url = "<%=request.getContextPath()%>/constituencyStatusAnalysisForVotesMarginAjaxAction.action?"+param;
		callAjax(param,jsObj,url);
	}
	

	if('${windowTask}' == "partyElectionResultsAnalysisPopup")
	{
		getConstituencyResults();
	}
	else if('${windowTask}' == "mainPartyMarginCountAnalysisPopup")
	{
		getMainPartyMarginCountAnalysis('${clickIndex}','${resultStatus}');
	}	

	</SCRIPT>
</BODY>
</HTML>