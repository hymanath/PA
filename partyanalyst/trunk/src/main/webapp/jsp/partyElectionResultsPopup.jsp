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
<LINK rel="stylesheet" type="text/css" href="styles/ElectionResultsAnalysisReport/partyElectionResultsReport.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<c:if test="${electionType != 'Parliament' && rank =='0'}"><TITLE>${stateName} ${electionType} ${electionYear} Lost Constituencies Results</TITLE></c:if>
<c:if test="${electionType != 'Parliament' && rank =='1'}"><TITLE>${stateName} ${electionType} ${electionYear} Won Constituencies Results</TITLE></c:if>
<c:if test="${electionType == 'Parliament' && rank =='0'}"><TITLE>${electionType} ${electionYear} Lost Constituencies Results</TITLE></c:if>
<c:if test="${electionType == 'Parliament' && rank =='1'}"><TITLE>${electionType} ${electionYear} Won Constituencies Results</TITLE></c:if>
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
	</CENTER>
	<DIV>
	<TABLE width="50%">
	<TR>
		<TH >Party:</TH>
		<TD >${electionResultPartyVO.partyLongName}(${electionResultPartyVO.partyShortName})</TD>		
		<TH >Flag:</TH>
		<TD ><IMG src="images/party_flags/${electionResultPartyVO.partyFlag}" height="30" width="50"/></TD>
	</TR>
	</TABLE>	
	<DIV class="yui-skin-sam"><DIV id="candidateResults"></DIV></DIV>
	</DIV>
	
	<SCRIPT type="text/javascript">
	var electionType="${electionType}";
	var electionYear="${electionYear}";	
	var partyElectionResultsObj={
		candidateResultsArr:[]
	};
	var candidateElectionResultsDataTable;	
	<c:forEach var="candidateResults"  items="${electionResultPartyVO.candidateElectionResultsVO}" >

	if('${candidateResults.rank}' == '')
		{ 	//alert("if");
			var ob={
			candidateName:'${candidateResults.candidateName}',
			constituencyName:'${candidateResults.constituencyName}',
			totalValidVotes:'${candidateResults.totalValidVotes}',
			totalVotesEarned:'${candidateResults.totalVotesEarned}',
			votesPercentage:'${candidateResults.votesPercentage}',
			rank:"1",
			moreDetails:'<A onclick="getMoreDetails(${candidateResults.constituencyId})" href="javascript:{}">More Details</A>'			
										
		};
	partyElectionResultsObj.candidateResultsArr.push(ob);

		} else 
		{
			//alert("else");
			var obj={
					candidateName:'${candidateResults.candidateName}',
					constituencyName:'${candidateResults.constituencyName}',
					totalValidVotes:'${candidateResults.totalValidVotes}',
					totalVotesEarned:'${candidateResults.totalVotesEarned}',
					votesPercentage:'${candidateResults.votesPercentage}',
					rank:'${candidateResults.rank}',					
					moreDetails:'<A onclick="getMoreDetails(${candidateResults.constituencyId})" href="#">More Details</A>'			
												
				};
			partyElectionResultsObj.candidateResultsArr.push(obj);
		}	
				
</c:forEach>
	var candidateElectionResultsDataTable;
	function buildCandidateElectionResultsDataTable()
	{	
		var candidateElectionResultsColumnDefs = [
									{key: "candidateName", label: "Candidate", sortable:true},										
			              	 	    {key: "constituencyName", label: "Constituency", sortable:true},
			              	 	 	{key: "totalValidVotes", label: "Valid Votes",formatter:"number", sortable:true},
			              	 	 	{key: "totalVotesEarned", label: "Votes Earned",formatter:"number", sortable:true},
			              	 	 	{key: "votesPercentage", label: "Votes Percentage",formatter:YAHOO.widget.DataTable.formatFloat, sortable:true},
			              	 	 	{key: "rank", label:"Rank"},
			              	 	 	{key: "moreDetails", label:"MoreDetails"}			              	 	 		              	 	 	
			              	 	    ];                	 	    

			var candidateElectionResultsDataSource = new YAHOO.util.DataSource(partyElectionResultsObj.candidateResultsArr); 
			candidateElectionResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			candidateElectionResultsDataSource.responseSchema = {
	                fields: [		  {key:"candidateName"},
	                         		  {key:"constituencyName"},
	                         		  {key:"totalValidVotes", parser:"number"},
	                         		  {key:"totalVotesEarned", parser:"number"},
	                         		  {key:"votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
	                         		  {key:"rank", parser:"number"},"moreDetails"
	                         		  ] 
	        		};

			var myConfigs = { 
				    paginator : new YAHOO.widget.Paginator({ 
			        rowsPerPage    : 10			        
				    })
				     
					};
			
			candidateElectionResultsDataTable = new YAHOO.widget.DataTable("candidateResults", candidateElectionResultsColumnDefs, candidateElectionResultsDataSource,myConfigs);						
	        	     	
		
	}
	buildCandidateElectionResultsDataTable();
	function getMoreDetails(constiId)
	{	

	var urlStr = "<%=request.getContextPath()%>/constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+electionType+"&electionYear="+electionYear;
	//var browser1 = window.open(urlStr,"partyElectionResultsPopup","scrollbars=yes,height=600,width=1200,left=200,top=200");
		
			 var browser2 = window.open(urlStr,"candidateResults","scrollbars=yes,height=600,width=750,left=200,top=200");
		 browser2.focus();
	}
	</SCRIPT>
</BODY>
</HTML>