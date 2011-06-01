<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ResourceBundle;" %>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

<!-- JQuery files (Start) -->
 <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<!-- JQuery files (End) -->

<!-- Dependencies --> 
<script src="http://yui.yahooapis.com/2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="http://yui.yahooapis.com/2.8.2r1/build/dragdrop/dragdrop-min.js"></script>

<!-- Slider skin (optional) --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.2r1/build/slider/assets/skins/sam/slider.css">

<!-- Slider source file --> 
<script src="http://yui.yahooapis.com/2.8.2r1/build/slider/slider-min.js"></script>

<script type="text/javascript" src="js/CommentsDialog/commentsDialog.js"></script>
<LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/partyElectionResultsReport.css">
<LINK rel="stylesheet" type="text/css" href="styles/CommentsDialog/commentsDialog.css">


<c:if test="${electionType != 'Parliament' && rank =='0'}"><TITLE>${stateName} ${electionType} ${electionYear} Lost Constituencies Results</TITLE></c:if>
<c:if test="${electionType != 'Parliament' && rank =='1'}"><TITLE>${stateName} ${electionType} ${electionYear} Won Constituencies Results</TITLE></c:if>
<c:if test="${electionType == 'Parliament' && rank =='0'}"><TITLE>${electionType} ${electionYear} Lost Constituencies Results</TITLE></c:if>
<c:if test="${electionType == 'Parliament' && rank =='1'}"><TITLE>${electionType} ${electionYear} Won Constituencies Results</TITLE></c:if>

<style type="text/css">

#slider-bg
{ 
	background:url(http://yui.yahooapis.com/2.8.2r1/build/slider/assets/bg-fader.gif) 5px 0 no-repeat; 
}
.yui-skin-sam .yui-h-slider {		
	width:110px;
}
</style>

<SCRIPT type="text/javascript">
var labelResources = { <%		
		ResourceBundle rb = ResourceBundle.getBundle("common_Lables");
		
		String candidate  = rb.getString("candidate");		
		String constituency = rb.getString("constituency");
		String validVotes = rb.getString("validVotes");
		String party = rb.getString("party");
		String votesPercentage = rb.getString("votesPercentage");
		String comments = rb.getString("comments");
		String votesMarginPcnt = rb.getString("votesMarginPcnt");
		String moreDetails = rb.getString("moreDetails");
		String flag = rb.getString("flag");
		String rank = rb.getString("rank");
		String votesEarned = rb.getString("votesEarned");
		
%>}
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
									showConstituencyResults(jsObj,myResults);
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
									showConstituencyResults(jsObj,myResults);
								}
						}
						catch (e) {   
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

	
	<DIV id="showConstituencyResults"></DIV>
	<DIV class="yui-skin-sam"><DIV id="candidateResults"></DIV></DIV>
	<DIV class = "yui-skin-sam"><DIV id="commentsDialogDiv"></DIV></DIV>
	<DIV class = "yui-skin-sam"><DIV id="yearsDialogDiv"></DIV></DIV>
	</CENTER>
	
	<SCRIPT type="text/javascript">
	var electionType="${electionType}";
	var electionYear="${electionYear}";	
	var electionId = '${electionId}';
	var partyId = '${partyId}';
	var rank = '${rank}';
	var electionTypeId = '${electionTypeId}';
    var locationId="${locationId}";
	var reportLevel="${reportLevel}";


	var partyElectionResultsObj={
		candidateResultsArr:[]
	};
	var candidateElectionResultsDataTable,selectYearDialog;
	function showConstituencyResults(jsObj,results)
	{		
		var candidateElectionResults = results.candidateElectionResultsVO;
		var constituencyResultsArray = new Array();
		var showConstituencyResultsEl = document.getElementById("showConstituencyResults");
		var contentStr='';
		contentStr+='<TABLE width="50%">';
		contentStr+='<TR>';
		contentStr+='<TH ><%=party%>:</TH>';
		contentStr+='<TD >'+results.partyLongName+' ( '+results.partyShortName+')</TD>';		
		contentStr+='<TH ><%=flag%>:</TH>';
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
						votesMargin: candidateElectionResults[i].votesMargin,
						rank: '1',						
						comments: '<A onclick="showCommentsDialog('+candidateElectionResults[i].candidateId+',\''+candidateElectionResults[i].candidateName+'\',\'candidate\',1,'+candidateElectionResults[i].constituencyId+',\''+candidateElectionResults[i].constituencyName+'\',\''+results.partyLongName+'\',\''+jsObj.task+'\')" title="Click to View/Add your comments"   href="javascript:{}">'+candidateElectionResults[i].userComments+'</A>',
						moreDetails: '<A onclick="getMoreDetails('+candidateElectionResults[i].constituencyId+')" title="Click to View Detailed Results" href="javascript:{}"><%=moreDetails%></A>'
						//otherResults: '<A onclick="showYearsDialog('+candidateElectionResults[i].constituencyId+','+electionTypeId+',\''+electionType+'\')" title="Click to View Detailed Results" href="javascript:{}">View Other Election Results</A>' 						
					};
				//partyElectionResultsObj.candidateResultsArr.push(ob);
				constituencyResultsArray.push(ob);
			} else if(candidateElectionResults[i].rank != null)
			{
				var obj={
						candidateName: candidateElectionResults[i].candidateName,
						constituencyName: candidateElectionResults[i].constituencyName,
						totalValidVotes: candidateElectionResults[i].totalValidVotes,
						totalVotesEarned: candidateElectionResults[i].totalVotesEarned,
						votesPercentage: candidateElectionResults[i].votesPercentage,
						votesMargin: candidateElectionResults[i].votesMargin,
						rank: candidateElectionResults[i].rank,						
						comments: '<A onclick="showCommentsDialog('+candidateElectionResults[i].candidateId+',\''+candidateElectionResults[i].candidateName+'\',\'candidate\','+candidateElectionResults[i].rank+','+candidateElectionResults[i].constituencyId+',\''+candidateElectionResults[i].constituencyName+'\',\''+results.partyLongName+'\',\''+jsObj.task+'\')" title="Click to View/Add your comments"   href="javascript:{}">'+candidateElectionResults[i].userComments+'</A>',			
						moreDetails:'<A onclick="getMoreDetails('+candidateElectionResults[i].constituencyId+')" title="Click to View Detailed Results" href="javascript:{}">More Details</A>'
						//otherResults: '<A onclick="showYearsDialog('+candidateElectionResults[i].constituencyId+','+electionTypeId+',\''+electionType+'\')" title="Click to View Detailed Results" href="javascript:{}">View Other Election Results</A>' 							
					};
				//partyElectionResultsObj.candidateResultsArr.push(obj);
				constituencyResultsArray.push(obj);
			}	
		}
		buildCandidateElectionResultsDataTable(constituencyResultsArray);		
	}
		
		
	function buildCandidateElectionResultsDataTable(resultsArray)
	{	
		var candidateElectionResultsColumnDefs = [
									{key: "candidateName", label: "<%=candidate%>", sortable:true},										
			              	 	    {key: "constituencyName", label: "<%=constituency%>", sortable:true},
			              	 	 	{key: "totalValidVotes", label: "<%=validVotes%>",formatter:"number", sortable:true},
			              	 	 	{key: "totalVotesEarned", label: "<%=votesEarned%>",formatter:"number", sortable:true},
			              	 	 	{key: "votesPercentage", label: "<%=votesPercentage%>",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
			              	 	    {key: "votesMargin", label: "<%=votesMarginPcnt%>",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
			              	 	 	{key: "rank", label:"<%=rank%>", sortable:true},
			              	 	 	{key: "comments", label:"<%=comments%>"},
			              	 	 	{key: "moreDetails", label:"<%=moreDetails%>"}			              	 	     	 	 	
			              	 	    ];                	 	    

			var candidateElectionResultsDataSource = new YAHOO.util.DataSource(resultsArray); 
			candidateElectionResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			candidateElectionResultsDataSource.responseSchema = {
	                fields: [		  {key:"candidateName"},
	                         		  {key:"constituencyName"},
	                         		  {key:"totalValidVotes", parser:"number"},
	                         		  {key:"totalVotesEarned", parser:"number"},
	                         		  {key:"votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
	                         		  {key:"votesMargin", parser:YAHOO.util.DataSourceBase.parseNumber},
	                         		  "comments", 
	                         		 {key:"rank", parser:"number"},
	                         		 "moreDetails"
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
	var browser2 = window.open(urlStr,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
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
	
	function handleAddCommentsSubmit(id,category,constituencyId)
	{
		var commentVal = document.getElementById("commentText").value; 
		var postedByVal = document.getElementById("commentPostedByText").value;
		var partyId;
		var candidateId;
		var constituencyId;
		var commentCategoryId;
		var alertMessageEl = document.getElementById("alertMessage"); 
		var reasonSeverityElmt = document.getElementById("slider-value");
		var reasonSeverityvalue = reasonSeverityElmt.innerHTML;

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
					reasonSeverityvalue: reasonSeverityvalue,
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
	function handleAddCommentsCancel(task,status)
	{
		if(task == "getConstituencyStatusAnalysisForVotesMarginWindow")
			getMainPartyMarginCountAnalysis('${clickIndex}','${resultStatus}');
		else if(task == "getConstituencyResults")
			getConstituencyResults();

		addCommentsDialog.hide();
		 $( "#commentsDialogDiv" ).dialog("destroy");
		//openerDoc.ajaxCallForBasicAnalysisDetails(openerDoc.electionYear,openerDoc.stateId,openerDoc.electionType,openerDoc.electionTypeId,openerDoc.partyId);
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
        // changes made
        var reportLvl="0";
		var loctnId="0";
		
		if(reportLevel != null && reportLevel != "")
		{
			reportLvl= reportLevel;
            loctnId=locationId;
		}
        // changes made
		
		var jsObj= 
		{			
			partyId: partyId,
			electionId:electionId,
			resultStatus:resultStatus,
			clickIndex:index,
			reportLevel:reportLvl,
			locationId:loctnId,
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
	if('${windowTask}' == "partyElectionResultsPopup")
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