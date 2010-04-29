<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Election Results Analysis Report</TITLE>
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
<SCRIPT>
function callAjax(param,jsObj,url){
	var myResults;
					
		var callback = {			
		               success : function( o ) {
						try {												
								if(o.responseText)
									myResults = YAHOO.lang.JSON.parse(o.responseText);
								if(jsObj.task == "getElectionsTypesInState")
								{	
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									populateElectionTypeDropdown(myResults);
								} else if(jsObj.task == "getElectionsYears")
								{	
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									populateElectionYearDropdown(myResults);
								}else if(jsObj.task == "getStaticParties")
								{										
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									populatePartiesDropdown(myResults);
								}
								 else if(jsObj.task == "getBasicAnalysisDetails")
								{										
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									showBasicAnalysisDetails(myResults);
								}
						}catch (e) {   
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
function getEletionTypesInState(id)
{	
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';

	var electionTypesEl = document.getElementById("electionTypeSelectEl"); 
	var noOfElectionTypesElOptions = electionTypesEl.options;
	var electionYearsEl = document.getElementById("electionYearSelectEl");
	var noOfElectionYearsElOptions = electionYearsEl.options;
	var partySelectEl = document.getElementById("partySelectEl");
	if(noOfElectionTypesElOptions.length != 0)
	{
		electionTypesEl.selectedIndex= '0';
	}
	if(noOfElectionYearsElOptions.length != 0)
	{
		electionYearsEl.selectedIndex= '0';
	}
	partySelectEl.selectedIndex= '0';	
	var jsObj= 
	{
	 	stateId: id,				
		task:"getElectionsTypesInState"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionTypesAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getEletionYears(electionType,electionTypeId)
{
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';
	var electionYearsEl = document.getElementById("electionYearSelectEl");
	var noOfElectionYearsElOptions = electionYearsEl.options;
	var partySelectEl = document.getElementById("partySelectEl");
	if(noOfElectionYearsElOptions.length != 0)
	{
		electionYearsEl.selectedIndex= '0';
	}
	partySelectEl.selectedIndex= '0';
	var jsObj= 
	{
		electionType: electionType,
		electionTypeId: electionTypeId,
		stateID:"1",			
		task:"getElectionsYears"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionYearsAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getStaticParties()
{
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';
	var jsObj= 
	{					
		task:"getStaticParties"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/partiesAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getBasicAnalysisDetails(id)
{
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt.style.display == 'none')
	elmt.style.display = 'block';
	var stateSelectEl = document.getElementById("stateSelectEl");
	var electionTypesEl = document.getElementById("electionTypeSelectEl");
	var electionYearsEl = document.getElementById("electionYearSelectEl");
	var stateId =stateSelectEl.value;	
	var electionType = electionTypesEl.options[electionTypesEl.selectedIndex].text;
	var electionYear = electionYearsEl.options[electionYearsEl.selectedIndex].text;	
	
	var jsObj= 
	{
	 	electionYear: electionYear,
		stateId: stateId,
		electionType: electionType,
		partyId: id,		
		task:"getBasicAnalysisDetails"		
	}
	
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionResultsAnalysisAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function openPartyElectionResultsWindow(electionId,partyId,rank,partyName,electionType,stateName,electionYear)
{ 
	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAction.action?electionId="+electionId+"&partyId="+partyId+"&rank="+rank+"&partyName="+partyName+
		"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear;
	var browser1 = window.open(urlStr,"partyElectionResultsPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser1.focus();
}
function openPartyElectionResultsAnalysisWindow(electionId, partyId,status,partyName,electionType,stateName,electionYear)
{
	var urlStr = "<%=request.getContextPath()%>/partyElectionResultsAnalysisAction.action?electionId="+electionId+"&partyId="+partyId+"&status="+status+
	"&partyName="+partyName+"&electionType="+electionType+"&stateName="+stateName+"&electionYear="+electionYear;
	var browser2 = window.open(urlStr,"partyElectionResultsAnalysisPopup","scrollbars=yes,height=600,width=1000,left=200,top=200");
	
	browser2.focus();
	
	
}
</SCRIPT>

</HEAD>
<BODY>
	<DIV id="page_layout_main" class="yui-skin-sam"></DIV>
	<DIV id="page_layout_right">
		<DIV id="sideHeader"><DIV class="sideHeading">Analysis Tools</DIV></DIV>
		<DIV class="toolHead">
			<DIV class="toolHeading">Election Results Analysis</DIV>
			<DIV class="toolBody"> 
				<TABLE width="100%">
						<TR>
						<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">View another year's election results analysis.</P></TD>
						</TR>
						<TR>
							<TD colspan="2"><DIV id="yearAlertS" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>
						</TR>						
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT id="selectYearAnalysisTool" name="selectYearAnalysisTool" style="width: 100px; margin-top: 3px;">
								<OPTION>2009</OPTION>
								<OPTION>2004</OPTION>								
							</SELECT>	
							</TD>
						</TR>							
					</TABLE>
				<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openPreYearStatewiseAnalysisWindow()" /></A></DIV>		 
			</DIV>
		</DIV>
		<DIV class="toolHead">
			<DIV class="toolHeading">Party Performance Report</DIV>
			<DIV class="toolBody"> 
				<TABLE width="100%">
							<TR>
								<TD colspan="2"><DIV id="yearAlertPPR" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>
							</TR>
							<TR>
								<TD>Party Name:</TD>
								<TD>
								<select id="selectPartyPPR" name="selectParty">
									<OPTION value="years.id">TDP</OPTION>
									<OPTION value="years.id">INC</OPTION>
								</select>							
								</TD>
							</TR>	
							<TR>	
								<TD>Year:</TD>
								<TD><SELECT id="selectYearPPR" name="selectYearPPR" style="width: 100px; margin-top: 3px;">
									<OPTION value="years.id">2009</OPTION>
									<OPTION value="years.id">2004</OPTION>									
								</SELECT>
								</TD>
							</TR>	
							<TR>	
								<TD colspan="2"><INPUT type="checkbox" id="pprCheckBox" value="hasAllianceParties" name="alliances"/>Include Alliances</TD>
							</TR>							
						</TABLE>
				<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openPreYearStatewiseAnalysisWindow()" /></A></DIV>		 
			</DIV>
		</DIV>
		<DIV class="toolHead">
			<DIV class="toolHeading">Statewise Election Results</DIV>
			<DIV class="toolBody"> 
				<TABLE width="100%">
							<TR>
								<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze election results Statewise.</P></TD>
							</TR>
							<TR>
								<TD colspan="2"><DIV id="yearAlertPPR" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>
							</TR>
							<TR>	
								<TD>Year:</TD>
								<TD><SELECT id="selectYearPPR" name="selectYearPPR" style="width: 100px; margin-top: 3px;">
									<OPTION value="years.id">2009</OPTION>
									<OPTION value="years.id">2004</OPTION>									
								</SELECT>
								</TD>
							</TR>														
						</TABLE>
				<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openPreYearStatewiseAnalysisWindow()" /></A></DIV>		 
			</DIV>
		</DIV>
		<DIV class="toolHead">
			<DIV class="toolHeading">Districtwise Election Results</DIV>
			<DIV class="toolBody"> 
				<TABLE width="100%">
							<TR>
								<TD colspan="2"><P style="font-size:15px;font-family:Trebuchet MS;text-align:left;">Analyze election results Districtwise.</P></TD>
							</TR>
							<TR>
								<TD colspan="2"><DIV id="yearAlertPPR" style="display:none;color:red;text-align:left;" >Error Message</DIV></TD>
							</TR>
							<TR>	
								<TD>Year:</TD>
								<TD><SELECT id="selectYearPPR" name="selectYearPPR" style="width: 100px; margin-top: 3px;">
									<OPTION value="years.id">2009</OPTION>
									<OPTION value="years.id">2004</OPTION>									
								</SELECT>
								</TD>
							</TR>														
						</TABLE>
				<DIV align="right" style="margin-top:50px;"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" onclick="openPreYearStatewiseAnalysisWindow()" /></A></DIV>		 
			</DIV>
		</DIV>	
	</DIV>
	<DIV id="page_layout_center">
		<DIV id="pageHeading" >
			<TABLE cellspacing="0" cellpadding="0" border="0" width="90%">
				<TR>
					<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/first.png" border="none"/></TD>
					<TD valign="top"><DIV class="mainHeading">Election Results Analysis Report</DIV></TD>
					<TD valign="top"><IMG src="images/icons/electionResultsAnalysisReport/second.png" border="none"/></TD>
				</TR>
			</TABLE>
		</DIV>	
		<DIV id="inputsTags" style="border:2px solid #DBDCDB;margin-left:15px;margin-right:15px;">
				<TABLE width="100%" class="inputsTable">
				<CAPTION>Please select the following options to view Report</CAPTION>
					<TR>	
						<TH >State</TH>
						<TD >
						<s:select id="stateSelectEl" theme="simple" name="stateSelectEl" cssClass="selectWidth" list="statesList" listKey="id" listValue="name" onchange="getEletionTypesInState(this.options[this.selectedIndex].value)"/>												
						</TD>
						<TH>Election Type</TH>
						<TD>
						<SELECT id="electionTypeSelectEl" name="electionTypeSelectEl" class="selectWidth" onchange="getEletionYears(this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)">							
						</SELECT>
						</TD>
					</TR>						
					<TR>
						<TH>Year</TH>
						<TD>
						<SELECT id="electionYearSelectEl" name="electionYearSelectEl" class="selectWidth" onchange="getStaticParties()">							
						</SELECT>
						</TD>
						<TH >Party</TH>
						<TD >
						<select id="partySelectEl" name="partySelectEl" class="selectWidth" onchange="getBasicAnalysisDetails(this.options[this.selectedIndex].value)"/>						
						</TD>
					</TR>					
				</TABLE>			
		</DIV>
		<DIV id="electionPageAjaxImgDiv" style="display:none">
			<DIV>Please Wait..</DIV>
			<IMG src="images/icons/barloader.gif"/>
		</DIV>

		<DIV id="resultInfoDiv">
		<DIV id="basicDetailsHead" style="margin-top:10px;"></DIV>
		<DIV id="basicDetails" class="yui-skin-sam"></DIV>
		<DIV id="analysisDetails" class="analysisDetails">
		<H3>Analysis Details</H3>
		<DIV id="tablerDetails"></DIV>
		</DIV>
		<DIV id="lostPosAnalisisDetails" class="analysisDetails">
			<DIV class="wonLostPosHeading">Analysis in Party Lost Positions</DIV>
			<DIV>
				<TABLE class="wonLostPosTable" width="80%">
					<TR>
						<TH style="width:50%">Seats Lost</TH>
						<TD style="width:30%">40</TD>
					</TR>
					<TR>
						<TH style="width:50%">Analyzed Constituencies</TH>
						<TD style="width:30%">10</TD>
					</TR>
					<TR>
						<TH style="width:50%">Yet to be  Analyzed Constituencies</TH>
						<TD style="width:30%">40</TD>
					</TR>
				</TABLE>								
			</DIV>
			<DIV>
				<DIV style="text-decoration:underline;font-size:15px;font-weight:bold;text-align:left;margin-left:70px;margin-top:10px;">Reasons</DIV>
				<TABLE class="wonLostPosTable" width="80%">
					<TR>
						<TH style="width:50%">No Candidate Ifluence</TH>
						<TD style="width:5%">10</TD>
						<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
					</TR>
					<TR>
						<TH style="width:50%">Alliance Impact</TH>
						<TD style="width:5%">10</TD>
						<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
					</TR>
					<TR>
						<TH style="width:50%">Poor Campaign</TH>
						<TD style="width:5%">20</TD>
						<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
					</TR>
				</TABLE>
				<DIV>
					<H3 style="width:510px;">Constituencies with Multiple Reasons</H3>
						<TABLE  cellpadding="0" cellspacing="0" width="75%"  class="multipleClassificationsTable">
							<TR>
								<TH width="30%">No of Analysis Reasons</TH>
								<TH width="5%">2</TH>
								<TH width="5%">3</TH>
								<TH width="5%">4</TH>
								<TH width="5%">N</TH>								
							</TR>
							<TR>
								<TD width="30%"><B>No of Constituencies</B></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">10</A></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">3</A></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">5</A></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">6</A></TD>								
							</TR>
						</TABLE>							
				</DIV>	
			</DIV>		
		</DIV>
		<DIV id="wonPosAnalisisDetails" class="analysisDetails">
			<DIV class="wonLostPosHeading">Analysis in Party Won Positions</DIV>
			<DIV>
				<TABLE class="wonLostPosTable" width="80%">
					<TR>
						<TH style="width:50%">Seats Won</TH>
						<TD style="width:30%">40</TD>
					</TR>
					<TR>
						<TH style="width:50%">Analyzed Constituencies</TH>
						<TD style="width:30%">10</TD>
					</TR>
					<TR>
						<TH style="width:50%">Yet to be  Analyzed Constituencies</TH>
						<TD style="width:30%">40</TD>
					</TR>
				</TABLE>								
			</DIV>
			<DIV>
				<DIV style="text-decoration:underline;font-size:15px;font-weight:bold;text-align:left;margin-left:70px;margin-top:10px;">Reasons</DIV>
				<TABLE class="wonLostPosTable" width="80%">
					<TR>
						<TH style="width:50%">Candidate Influence</TH>
						<TD style="width:5%">10</TD>
						<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
					</TR>
					<TR>
						<TH style="width:50%">Strong Cadre</TH>
						<TD style="width:5%">10</TD>
						<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
					</TR>
					<TR>
						<TH style="width:50%">Cast Support</TH>
						<TD style="width:5%">20</TD>
						<TD style="width:25%"><A title="Click To View Results" href="javascript:{}">View Results</A></TD>
					</TR>
				</TABLE>
				<DIV>
					<H3 style="width:510px;">Constituencies with Multiple Reasons</H3>
						<TABLE  cellpadding="0" cellspacing="0" width="75%"  class="multipleClassificationsTable">
							<TR>
								<TH width="30%">No of Analysis Reasons</TH>
								<TH width="5%">2</TH>
								<TH width="5%">3</TH>
								<TH width="5%">4</TH>
								<TH width="5%">N</TH>								
							</TR>
							<TR>
								<TD width="30%"><B>No of Constituencies</B></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">10</A></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">3</A></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">5</A></TD>
								<TD width="5%"><A title="Click To View Constituencies" href="javascript:{}">6</A></TD>								
							</TR>
						</TABLE>							
				</DIV>	
			</DIV>
		</DIV>						
		</DIV>			
	</DIV>	
<SCRIPT>
initializePage();
</SCRIPT>
</BODY>
</HTML>