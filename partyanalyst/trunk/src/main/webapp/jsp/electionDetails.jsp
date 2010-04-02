<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ResourceBundle;" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<SCRIPT src="js/yahoo/yui-js-2.8/build/container/container-min.js"></SCRIPT>
<SCRIPT src="js/yahoo/yui-js-2.8/build/button/button-min.js"></SCRIPT>

<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<LINK rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">

<LINK rel="stylesheet" type="text/css" href="styles/ElectionsReslutsPage/electionResultsPage.css">
<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable.css">
<TITLE>${stateName} ${electionType} Election Results Page ${year}</TITLE>
<SCRIPT type="text/javascript">
var electionId = '${electionId}';
var electionType = '${electionType}';
var stateID =  '${stateID}' ;
var stateName = '${stateName}';
var year = '${year}';
var electionResultsObj = {
	partyWiseResultsArr:[],
	allianceResultsArr:[]
};
var Localization = { <%
		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String party = rb.getString("party");
		String totalParticipated = rb.getString("totalParticipated"); 
		String seatsWon = rb.getString("seatsWon");
		String seatsLost = rb.getString("seatsLost");
		String votesEarned = rb.getString("votesEarned");
		String percentage  = rb.getString("percentage");
		//String address  = rb.getString("address");
		//String location  = rb.getString("location");
		//String name = rb.getString("name");
		//String email = rb.getString("email");
		//String telephoneNo = rb.getString("telephoneNo");
		//String designation = rb.getString("designation"); 
		
%> }
var partywiseResultsDataTable,allianceResultsDataTable,candidateDetailsDialog;         
function callAjax(param,jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task == "elctionsBasicInfo")
									{										
										showElectionBasicInfo(myResults);											
									} else if(jsObj.task == "getResultForAnElection")
									{	
										showStatewiseResultsBarChart(myResults);									
										showPartywiseDetailsDataTable(myResults);
										showAllianceDetails(myResults);										
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
function getElctionsBasicInfo(electionType){
	var jsObj= 
	{	
			
		electionType: electionType,			
		task: "elctionsBasicInfo"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getResultsForAnElection(stateID,electionType,year)
{
	var jsObj= 
	{
		stateID: stateID,
		electionType: electionType,
		year: year,		
		task:"getResultForAnElection"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}

function showStatewiseResultsBarChart(results)
{
	var chartName = results.statewiseElectionResultsChartName;
	var statewiseGraphEl = document.getElementById("graphImage");

	var contentStr = '';
	contentStr+='<IMG src="charts/'+chartName+'"></IMG>';
	statewiseGraphEl.innerHTML = contentStr;	 
}

function showElectionBasicInfo(results)
{
	
	var task1El = document.getElementById("task1");
	var task1Content = '';
	task1Content = '<DIV class="basicDetailsDiv">';
	//task1Content+='<DIV class="basicDetailsHeadingDiv">Overview</DIV>';
	task1Content+='<TABLE class="basicDetailsTable" width="100%">';
	task1Content+='<tr><TH>Total Voters:</TH><TD>'+results.totalVoters+'</TD><TH>Male Voters:</TH><TD>'+results.maleVoters+'</TD><TH>Female Voters:</TH><TD>'+results.femaleVoters+'</TD></TR>';
	task1Content+='<tr><TH>Total Constituencies:</TH><TD>'+results.totalConstituencies+'</TD><TH>Total Polled Votes:</TH><TD>1,05,34343</TD></TR>';
	task1Content+='</TABLE>';
	task1Content+='</DIV>';
	task1El.innerHTML = task1Content;
	
}

function showPartywiseDetailsDataTable(results)
{   
	var partywiseResultsArr = results.electionBasicResultsVO.allPartiesResults;
	var assignToPartywiseResultsArr = new Array();
	for(var i in  partywiseResultsArr){
		var partywiseResultsObj = { 
				party: partywiseResultsArr[i].partyName, 
				totalParticipated: partywiseResultsArr[i].totalConstiParticipated, 
				seatsWon: partywiseResultsArr[i].totalSeatsWon,
				second: partywiseResultsArr[i].secondPosWon,
				third: partywiseResultsArr[i].thirdPosWon,
				fourth: partywiseResultsArr[i].fourthPosWon,
				nth: partywiseResultsArr[i].nthPosWon,
				pc: partywiseResultsArr[i].votesPercentage,
				overall: partywiseResultsArr[i].completeVotesPercent						 
				};
		assignToPartywiseResultsArr.push(partywiseResultsObj);	
	} 
	electionResultsObj.partyWiseResultsArr = assignToPartywiseResultsArr;
	buildPartywiseResultsDataTable();
	
}

function buildPartywiseResultsDataTable()
{	
	var partywiseResultsColumnDefs = [
								{key: "party", label: "<%=party%>", sortable:true},		
								//{key: "totalParticipated", label: "TP*", formatter:"number", sortable:true},	
		              	 	    {key: "seatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "second", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "third", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourth", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nth", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "pc", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "overall", label:"Overall %",formatter:YAHOO.widget.DataTable.formatFloat, sortable: true}		              	 	 	
		              	 	    ];                	 	    

		var partywiseResultsDataSource = new YAHOO.util.DataSource(electionResultsObj.partyWiseResultsArr); 
		partywiseResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		partywiseResultsDataSource.responseSchema = {
                fields: ["party", {key: "totalParticipated", parser:"number"},
                         		  {key: "seatsWon", parser:"number"},
                         		  {key: "second", parser:"number"},
                         		  {key:  "third", parser:"number"},
                         		  {key:  "fourth", parser:"number"},
                         		  {key: "nth", parser:"number"},
                         		  {key: "pc", parser:YAHOO.util.DataSourceBase.parseNumber},
                         		  {key: "overall", parser:YAHOO.util.DataSourceBase.parseNumber} ] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    }),
			    caption:"Partywise Results" 
				};
		
		partywiseResultsDataTable = new YAHOO.widget.DataTable("partywiseResultsDataTable", partywiseResultsColumnDefs, partywiseResultsDataSource,myConfigs);
					
        return { 
            oDS: partywiseResultsDataSource, 
            oDT: partywiseResultsDataTable 
           
      };	     	
	
}
function showAllianceDetails(results)
{
	var allianceResultsArr = results.electionBasicResultsVO.alliancePartiesList;
	var assignToAllianceResultsArr = new Array();
	var allianceResultsDataTableEl = document.getElementById("allianceResultsDataTable");
	
	for(var i in  allianceResultsArr){
		var createDiv = document.createElement("div");
		createDiv.setAttribute("id","allianceResults_"+i);
		//createDiv.setAttribute("style","margin-top:26px;");
		createDiv.style.cssText = 'margin-top:26px;';
		allianceResultsDataTableEl.appendChild(createDiv);
	
		buildAllianceResultsDataTable("allianceResults_"+i,allianceResultsArr[i].partiesInAlliance,allianceResultsArr[i].allianceGroupName+" Alliance Details");	
	}		
}

function buildAllianceResultsDataTable(id,dtSource,dtCaption)
{	
	
	var allianceResultsColumnDefs = [
								{key: "partyName", label: "<%=party%>", sortable:true},		
								{key: "totalConstiParticipated", label: "TP*",formatter:"number", sortable:true},	
		              	 	    {key: "totalSeatsWon", label: "<%=seatsWon%>",formatter:"number", sortable:true},
		              	 	 	{key: "secondPosWon", label: "2nd",formatter:"number", sortable:true},
		              	 	 	{key: "thirdPosWon", label: "3rd",formatter:"number", sortable:true},
		              	 	 	{key: "fourthPosWon", label: "4th",formatter:"number", sortable:true},
		              	 	 	{key: "nthPosWon", label: "Nth",formatter:"number", sortable:true},   	
		              	 	 	{key: "votesPercentage", label:"PC* %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true},
		              	 	 	{key: "completeVotesPercent", label:"Overall %", formatter:YAHOO.widget.DataTable.formatFloat,sortable: true}		              	 	 	
		              	 	    ];                	 	    

		var allianceResultsDataSource = new YAHOO.util.DataSource(dtSource); 
		allianceResultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		allianceResultsDataSource.responseSchema = {
                fields: ["partyName", {key: "totalConstiParticipated", parser:"number"},
                					  {key:  "totalSeatsWon", parser:"number"},
                					  {key:  "secondPosWon", parser:"number"},
                					  {key:  "thirdPosWon", parser:"number"},
                					  {key:  "fourthPosWon", parser:"number"},
                					  {key: "nthPosWon", parser:"number"},
                					  {key: "votesPercentage", parser:YAHOO.util.DataSourceBase.parseNumber},
                					  {key: "completeVotesPercent", parser:YAHOO.util.DataSourceBase.parseNumber}] 
        		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10			        
			    }),
			    caption:dtCaption
				};
		
		allianceResultsDataTable = new YAHOO.widget.DataTable(id, allianceResultsColumnDefs, allianceResultsDataSource,{caption:dtCaption});
					
        return { 
            oDS: allianceResultsDataSource, 
            oDT: allianceResultsDataTable            
      };	
	
}
function showCandidateDetailsWindow(stateName,electionType,year)
{
	var windowLabel;
	if(electionType == 'Parliament')
	{
		windowLabel = electionType + ' ' +  year + ' ' + 'Candidates Details';
		
	} else if(electionType == 'Assembly')
	{
		windowLabel = stateName + ' ' + electionType + ' ' +  year + ' ' + 'Candidates Details';
	}		

	var urlStr = "<%=request.getContextPath()%>/candidateDetailsForElectionDetailsReportAction.action";
	var browser1 = window.open(urlStr,"browser1","scrollbars=yes,height=600,width=750,left=200,top=200");
	
	browser1.focus();
	
	/*var elmt = document.getElementById('viewCandidate');
	var divChild = document.createElement('div');
	divChild.setAttribute('id','viewCandidateDialog');
	
	var candidateDetailsContentStr='';
	candidateDetailsContentStr+='<DIV class="hd" align="left">${stateName} ${electionType} ${year} Winning Candidates</DIV>';
	candidateDetailsContentStr+='<DIV class="bd" align="left">';
	candidateDetailsContentStr+='<TABLE cellspacing="0" cellpadding="0" border="1">';
	candidateDetailsContentStr+='<TR>';
	candidateDetailsContentStr+='<TD>Party:</TD>';
	candidateDetailsContentStr+='<TD colspan="2"><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">Select Party</OPTION><OPTION value="1">BJP</OPTION><OPTION value="2">TDP</OPTION><OPTION value="3">TRS</OPTION></SELECT></TD>';
	candidateDetailsContentStr+='</TR>';
	if(electionType == 'Parliament')
	{		
		candidateDetailsContentStr+='<TR>';
		candidateDetailsContentStr+='<TD>';
		candidateDetailsContentStr+='<INPUT type="radio" name="countryLevel" id="countryLevel" value="Country Level" onClick=""/>All Winners';
		candidateDetailsContentStr+='</TD>';
		candidateDetailsContentStr+='<TD>';
		candidateDetailsContentStr+='<INPUT type="radio" name="stateLevelP" id="stateLevelP" value="State Level" onClick="showStatesDropDown()"/>State Wise';
		candidateDetailsContentStr+='</TD>';
		candidateDetailsContentStr+='<TD>';
		candidateDetailsContentStr+='<SELECT name="selectState" id="selectState" style="width: 100px; margin-top: 3px;" ><OPTION value="0">Select State</OPTION><OPTION value="1">Andhra Pradesh</OPTION><OPTION value="2">Karnataka</OPTION><OPTION value="3">Tamil Nadu</OPTION></SELECT>';
		candidateDetailsContentStr+='</TD>';
		candidateDetailsContentStr+='</TR>';
	} else if(electionType == 'Assembly')
	{	
	candidateDetailsContentStr+='<TR>';
	candidateDetailsContentStr+='<TD>';
	candidateDetailsContentStr+='<INPUT type="radio" name="stateLevelA" id="stateLevelA" value="State Level" onClick=""/>State Wise';
	candidateDetailsContentStr+='</TD>';
	candidateDetailsContentStr+='<TD>';
	candidateDetailsContentStr+='<INPUT type="radio" name="distLevel" id="distLevel" value="District Level" onClick=""/>District Wise';
	candidateDetailsContentStr+='</TD>';
	candidateDetailsContentStr+='</TR>';
	}
	candidateDetailsContentStr+='</TABLE>';
	candidateDetailsContentStr+='</DIV>';
	candidateDetailsContentStr+='';
	candidateDetailsContentStr+='';
	candidateDetailsContentStr+='';
	candidateDetailsContentStr+=''; 
	divChild.innerHTML=candidateDetailsContentStr;		
	elmt.appendChild(divChild);	
	if(candidateDetailsDialog)
		candidateDetailsDialog.destroy();
	candidateDetailsDialog = new YAHOO.widget.Dialog("viewCandidateDialog",
			{ width : "750px", 
              fixedcenter : false, 
              visible : true,  
              constraintoviewport : true, 
			  iframe :true,
			  modal :false,
			  hideaftersubmit:true,
			  close:true,
			  x:400,
			  y:300,
			  buttons : [ { text:"Exit"}]				 
             }); 
	candidateDetailsDialog.render();*/
}
function showStatesDropDown()
{
	alert("hi");
		
}
</SCRIPT>
</HEAD>
<BODY>
<TABLE cellspacing="0" cellpadding="0" border="0" >
<TR>
<TD  valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" /></TD><TD valign="top"><DIV class="mainHeading">${stateName} ${electionType} Election Results ${year}</DIV></TD><TD  valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" border="none"/></TD>
</TR>
</TABLE>
<DIV id="task1"></DIV>
<DIV class="graphTop">State Level Overview</DIV>
<DIV id="statewiseGraph">
<DIV id="graphImage"></DIV>
<DIV class="yui-skin-sam" style="width:890px;overflow-x:auto;border-top:2px solid #008DCF;">
	<TABLE border="0" width="95%">
		<TR>
			<TD valign="top"><DIV id="partywiseResultsDataTable"></DIV></TD>
			<TD valign="top"><DIV id="allianceResultsDataTable"></DIV></TD>
		</TR>
		<TR>
			<TD colspan="2" align="left"><SPAN style="color:#006221;font-size:13px;font-weight:bold;">TP* =Total Participation, PC* %=Participated Constituencies Percentage </SPAN></TD>
		</TR>
		<TR>
			<TD colspan="2" align="right"><A href="javascript:{}" class="link" onclick="showCandidateDetailsWindow(stateName,electionType,year)">View Candidates</A></TD>
		</TR>
	</TABLE>
</DIV>
</DIV>
<DIV id="viewCandidate" class="yui-skin-sam"></DIV>
<DIV class="graphBottom"></DIV>
<DIV class="graphTop">District Level Overview</DIV>
<DIV id="distwiseGraph"><IMG src="images/icons/temp_graph.png" height="200" width="850"/></DIV>
<DIV class="graphBottom"></DIV>
<DIV class="graphTop">Analysis Tools</DIV>
<DIV id="toolsDiv" align="left">
	<TABLE class="toolsTable"><TR>
		<TD class="td">
			<DIV id="viewPPR" class="toolsDisplay">
					<h3>District wise Analysis</h3>
					<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can compare previous elections results district wise.</P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Election Type:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">Assembly</OPTION><OPTION value="1">Parliament</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>				
			</DIV>
		</TD>
		<TD class="td">
			<DIV id="viewPPR" class="toolsDisplay">
				<h3>Compare Votes% & Seats %</h3>
				<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can compare previous elections by votes % and seats %.</P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Election Type:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">Assembly</OPTION><OPTION value="1">Parliament</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>
			</DIV>	
		</TD>
		<TD class="td">
			<DIV class="toolsDisplay">
				<h3>Party Performance</h3>
				<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can analysis a party's performance for an election in previous years. </P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Party Name:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">TDP</OPTION><OPTION value="1">BJP</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>
			</DIV>
		</TD>
		<TD class="td">
			<DIV id="comparePrevElection" class="toolsDisplay">
				<h3>Elections Comparison</h3>
				<P style="font-size:15px;font-family:Trebuchet MS;">By this tool you can compare current election results with previous election results</P>
				<DIV style="font-weight:bold">
					<TABLE width="100%">
						<TR>
							<TD>Election Type:</TD>
							<TD><SELECT name="electionType" id="electionType" style="width:100px;" ><OPTION value="0">Assembly</OPTION><OPTION value="1">Parliament</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><SELECT name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><OPTION value="0">2009</OPTION><OPTION value="1">2004</OPTION></SELECT></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><INPUT type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
						</TR>
						<TR>	
							<TD colspan="2"><DIV align="right"><A href="javascript:{}" ><IMG src="images/icons/electionResultsReport/viewLink.png" border="none" height="23px" /></A></DIV></TD>
						</TR>	
					</TABLE>		 
				</DIV>
			</DIV>
		</TD></TR>
	</TABLE>
</DIV>
<DIV class="graphBottom"></DIV>
<div id="task9"></DIV>
<DIV id="task10"></DIV>

<SCRIPT type="text/javascript">
//getElctionsBasicInfo(electionType);
getResultsForAnElection(stateID,electionType,year);

</SCRIPT>
</BODY>

</HTML>