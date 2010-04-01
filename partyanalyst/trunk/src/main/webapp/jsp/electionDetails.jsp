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
var partywiseResultsDataTable,allianceResultsDataTable;         
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
									} else if(jsObj.task == "stateLevelGraph")
									{										
										 											
									} else if(jsObj.task == "districtLevelGraph")
									{										
																				
									} else if(jsObj.task == "getResultForAnElection")
									{										
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
function buildStateLevelGraph(electionType){
	var jsObj= 
	{
		electionType: electionType,		
		task:"stateLevelGraph"		
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
function buildDistrictLevelGraph(electionType){
	var jsObj= 
	{
		electionType: electionType,		
		task:"districtLevelGraph"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getPartywiseResults(electionType){
	var jsObj= 
	{
		electionType: electionType,		
		task:"partywiseResults"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function getAllianceDetails(electionType){
	var jsObj= 
	{
		electionType: electionType,	
		task:"getAllianceDetails"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function ajaxCall6(){
	var jsObj= 
	{
		task:"task6"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function ajaxCall7(){
	var jsObj= 
	{
		task:"task7"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function ajaxCall8(){
	var jsObj= 
	{
		task:"task8"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function ajaxCall9(){
	var jsObj= 
	{
		task:"task9"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
}
function ajaxCall10(){
	var jsObj= 
	{
		task:"task10"		
	}
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportAjaxAction.action?"+param;
	callAjax(param,jsObj,url);
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

function showTask2(results)
{
	
	var task2El = document.getElementById("task2");
	var task2Content = '';
	task2Content+='<TABLE border="1" width="100%">';
	task2Content+='<caption><b>Task 2 Details</b></caption>';
	task2Content+='<TR><TH>Election</TH><TH>Year</TH><TH>Details</TH><TH>Task</TH></TR>';
	task2Content+='<TR><TD>'+results.electionType+'</TD><TD>'+results.electionYear+'</TD><TD>'+results.details+'</TD><TD>'+results.task+'</TD></TR>';
	task2Content+='</TABLE>';
	task2El.innerHTML = task2Content;
		
}
function showTask3(results)
{
	
	var task3El = document.getElementById("task3");
	var task3Content = '';
	task3Content+='<TABLE border="1" width="100%">';
	task3Content+='<caption><b>Task 3 Details</b></caption>';
	task3Content+='<TR><TH>Election</TH><TH>Year</TH><TH>Details</TH><TH>Task</TH></TR>';
	task3Content+='<TR><TD>'+results.electionType+'</TD><TD>'+results.electionYear+'</TD><TD>'+results.details+'</TD><TD>'+results.task+'</TD></TR>';
	task3Content+='</TABLE>';
	task3El.innerHTML = task3Content;
	
	
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
                fields: ["party", "totalParticipated", "seatsWon", "second", "third", "fourth","nth","pc","overall" ] 
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
		createDiv.setAttribute("style","margin-top:26px;");
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
                fields: ["partyName", "totalConstiParticipated", "totalSeatsWon", "secondPosWon", "thirdPosWon", "fourthPosWon","nthPosWon","votesPercentage","completeVotesPercent"] 
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
</SCRIPT>
</HEAD>
<BODY>
<TABLE cellspacing="0px" border="0px" >
<TR>
<TD  valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" /></TD><TD valign="top"><DIV class="mainHeading">${stateName} ${electionType} Election Results ${year}</DIV></TD><TD  valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" border="none"/></TD>
</TR>
</TABLE>
<DIV id="task1"></DIV>
<DIV class="graphTop">State Level Overview</DIV>
<DIV id="statewiseGraph">
<IMG id="chartImg" SRC="charts/${sessionScope.partyResultsChartName}" WIDTH="900" HEIGHT="300"></IMG>
<DIV class="yui-skin-sam" style="width:890px;overflow-x:auto;">
	<TABLE border="0" width="95%">
		<TR>
			<TD valign="top"><DIV id="partywiseResultsDataTable"></DIV></TD>
			<TD valign="top"><DIV id="allianceResultsDataTable"></DIV></TD>
		</TR>
		<TR>
			<TD colspan="2"><SPAN style="color:#006221;font-size:13px;font-weight:bold;">TP* -Total Participation, PC* %-Participated Constituencies Percentage </SPAN></TD>
		</TR>
	</TABLE>
</DIV>
</DIV>
<DIV class="graphBottom"></DIV>
<DIV id="task3"></DIV>

<DIV class="graphTop">District Level Overview</DIV>
<DIV id="distwiseGraph"><IMG src="images/icons/temp_graph.png" height="200" width="850"/></DIV>
<DIV class="graphBottom"></DIV>
<DIV class="graphTop">Candidate Details</DIV>
<DIV id="candidateDetails"></DIV>
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
							<TD><select name="electionType" id="electionType" style="width:100px;" ><option value="0">Assembly</option><option value="1">Parliament</option></select></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><select name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><option value="0">2009</option><option value="1">2004</option></select></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><input type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
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
							<TD><select name="electionType" id="electionType" style="width:100px;" ><option value="0">TDP</option><option value="1">BJP</option></select></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><select name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><option value="0">2009</option><option value="1">2004</option></select></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><input type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
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
							<TD><select name="electionType" id="electionType" style="width:100px;" ><option value="0">Assembly</option><option value="1">Parliament</option></select></TD>
						</TR>	
						<TR>	
							<TD>Year:</TD>
							<TD><select name="selectParty" id="selectParty" style="width: 100px; margin-top: 3px;" ><option value="0">2009</option><option value="1">2004</option></select></TD>
						</TR>	
						<TR>	
							<TD colspan="2"><input type="checkbox" value="true" name="alliances"/>Include Alliances</TD>
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
//getPartywiseResults(electionType);
//getAllianceDetails(electionType);
//buildStateLevelGraph(electionType);
//buildDistrictLevelGraph(electionType);
</SCRIPT>
</BODY>

</HTML>