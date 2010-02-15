<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Constituency Management</title>

	<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/button/assets/skins/sam/button.css"> 
		 
	<!-- YUI Dependency files (End) -->
	
		<style type="text/css">
		#constituencyMgmtHeaderDiv
		{
			color:#1C487A;
			font-size:17px;
			font-weight:bold;
			text-decoration:underline;
			margin-bottom:20px;
		}
					
		#constituencyMgmtBodyDiv
		{
			height:900px;
			
		}
			
		.yui-skin-sam .yui-navset .yui-nav, .yui-skin-sam .yui-navset .yui-navset-top .yui-nav 
		{
			text-align:left;
			font-weight:bold;

		}
		.yui-skin-sam .yui-dt th
		{
			background-image:url(images/YUI-images/sprite.png)
		}
		
		#yui-dt0-th-Categorize
		{
			background-color:blue;
		}

		#statisticalDataMainDiv
		{
			background-color:#EDF5FF;
			border:2px solid #99A8B7;
			height:97%;
			text-align:left;
			width:99%;
		}
		#statisticalDataHeadDiv
		{
			padding:5px;
			background-color:#B2C2D2;
		}
		#statisticalDataBodyDiv
		{
			padding:5px;
		}
		#newProblemTabContentDiv_footer,#newProblemTabContentDiv_head,#classifiedTabContentDiv_footer,#assignedIssuesTabContentDiv_footer,#pendingTabContentDiv_footer,#progressTabContentDiv_footer
		{
			margin-right:20px;
			text-align:right;
			padding:5px;
		}
		
		.selectWidth
		{
			width:120px;
		}
		#ajaxImgSpan
		{
			margin-top:10px;
			display:none;
		}
		.yui-skin-sam .yui-dt table 
		{
			width:100%;
		}
		.requiredFont
		{
		color:red;
		margin-left:5px;
		}
		.tinyDateCal
		{
		position:absolute;
		}
		.newProbdialog
		{
			background-color:#EDF5FF;
		}
		#alertMessage,#NewIssuesAlertMessage, #clsfdIssuesAlertMsg,#assignedIssuesAlertMsg, #progressedIssAlertMsg,#pendingIssAlertMsg
		{
			text-align:left;
			font-weight:bold;
			color:red;			
		}
		#pendingTabContentDiv
		{
			width: 900px;
			overflow-x:auto;
		}

	</style>

	<script type="text/javascript">
	
	var Localization = { <%
			
			ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
			String problemLabel = rb.getString("problem");
			String constituencyMgmt = rb.getString("constMgmt");
			String probMgmt = rb.getString("probMgmt");
			String userGroups = rb.getString("userGrps");
			String recommLetters = rb.getString("recommLetters");
			String distEPapers = rb.getString("distEPapers");
			String localLeaders = rb.getString("localLeaders");
			String localProbs = rb.getString("localProbs");
			String localCastStats = rb.getString("localCastStats");
			String localPolChanges = rb.getString("localPolChanges");
			String voterByLoc= rb.getString("voterByLoc");
			String impVoters= rb.getString("impVoters");
			String name = rb.getString("name");
			String occupation = rb.getString("occupation");
			String position = rb.getString("position");
			String inflScope = rb.getString("inflScope");
			String contactnbr= rb.getString("contactnbr");
			String description = rb.getString("description");
			String identifiedDate = rb.getString("identifiedDate");
			String source = rb.getString("source");
			String status = rb.getString("status");
			String cast = rb.getString("cast");
			String castPopulation = rb.getString("castPopulation");
			String castPercentage = rb.getString("castPercentage");
			String date = rb.getString("date");
			String impact = rb.getString("impact");
			String gender = rb.getString("gender");
			String age = rb.getString("age");
			String hNo = rb.getString("hNo");
			String guardName= rb.getString("guardName");
			String relationship = rb.getString("relationship");
			String castCategory = rb.getString("castCategory");
			String mbrsInFamily = rb.getString("mbrsInFamily");
			String eldstPersonName = rb.getString("eldstPersonName");
			String ygstPersonName = rb.getString("ygstPersonName");
			String newIssues= rb.getString("newIssues");
			String clasfdIssues = rb.getString("clasfdIssues");
			String assignedIssues = rb.getString("assignedIssues");
			String progress = rb.getString("progress");
			String fixedIssues = rb.getString("fixedIssues");
			String sNo = rb.getString("sNo");
			String title = rb.getString("title");
			String location = rb.getString("location");
			String scope = rb.getString("scope");
			String problemType = rb.getString("problemType");
			String department = rb.getString("department");
			String concernedDept = rb.getString("concernedDept");
			String assignedOfficial = rb.getString("assignedOfficial");
			String addNewProb = rb.getString("addNewProb");
			String pendingIssues = rb.getString("pendingIssues");
			String select = rb.getString("select");
			String STATE = rb.getString("STATE");
			String DISTRICT = rb.getString("DISTRICT");
			String CONSTITUENCY = rb.getString("CONSTITUENCY");
			String MANDAL  = rb.getString("MANDAL");
			String VILLAGE = rb.getString("VILLAGE");
			String HAMLET   = rb.getString("HAMLET");
			String classify = rb.getString("classify");
			String assign = rb.getString("assign");
			String totalNumOfVoters = rb.getString("totalNumOfVoters");
			String maleVoters = rb.getString("maleVoters");
			String femaleVoters = rb.getString("femaleVoters");
			String email = rb.getString("email");
			String address = rb.getString("address");
			String telephoneNo = rb.getString("telephoneNo");
			String reportedDate = rb.getString("reportedDate");
			String existingFrom = rb.getString("existingFrom");
			String problemSource = rb.getString("problemSource");
			String mobile  = rb.getString("mobile");
			String constMgmtAlertMessage = rb.getString("constMgmtAlertMessage");
			String mandalLeaders= rb.getString("mandalLeaders");
			String electionYear= rb.getString("electionYear");
			String rank= rb.getString("rank");
			String mptcName= rb.getString("mptcName");
			String party= rb.getString("party");
			String candidateName= rb.getString("candidateName");
			String votesEarned= rb.getString("votesEarned");
			String totalValidVotes= rb.getString("totalValidVotes");
			String reason= rb.getString("reason");
			String fixedDate= rb.getString("fixedDate");
			String pending=rb.getString("pending");
			String fixed=rb.getString("fixed"); 
			String newIssuesAlertMessage = rb.getString("newIssuesAlertMessage");
			String pendingFrom = rb.getString("pendingFrom");
			String comments = rb.getString("comments");
		  %> }
	
	var outerTab,problemMgmtTabs,newProbDataTable, classifiedDataTable,assignedIssDataTable, progessIssuesDataTable, classifyDTRecord, deptCellEditor,pendingIssuesDataTable, fixedIssuesDataTable;
	var newProblemDialog,elCheckbox,elCheckboxInClassifyDT,elCheckboxInAssignDT,elCheckboxInProgressDT,elCheckboxInPendingDT,EditClassifyProbsDialog ;
	var classifyButton, assignButton, progressButton,pFixedButton,pPendingButton,progressButtonInPending;
	var recordsArray = new Array();
	var clasifyDtRecordsArray = new Array();
	var assignDtRecordsArray = new Array();
	var progressDtRecordsArray = new Array();
	var pendingDtRecordsArray = new Array();
	var problemsMainObj={	
							probTypesArr:[],
							problemSourcesArr:[],
							problemRegionScopeArr:[],	
							newProblemsArr:[],
							classifiedProblemsArr:[],
							assignedProblemsArr:[],
							progressedProblemsArr:[],
							pendingProblemArr:[],
							fixedProblemsArr:[],
							probConcDeptsArr:[]
						};

	var locationDetails={
							stateArr:[],
							districtArr:[],
							constituencyArr:[],
							mandalArr:[],
							villageArr:[],
							hamletArr:[]
						};
	var constMgmtMainObj={
							votersArray:[],
							castStatsArray:[],
							totalvotersStatsArray:[],
							votersByHouseNoArray:[],
							localLeadersArray:[],
							localPoliticalChangesArray:[],
							localProblemsArr:[],
							totalLeadersArr:[],
							electedMandalLeadersArr:[]							
						};	 
	function buildConstituencyLayout()
	{	
		var layoutEl = new YAHOO.widget.Layout('constituencyMgmtBodyDiv', { 
			units: [	 
					{ 
						position: 'center',
						body: 'problemMgmtMainDiv',							
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: false,						
						animate: false
					},
					{
						position: 'bottom',
						body: 'statisticalDataMainDiv',
						resize: false,
						gutter: '5px',
						collapse: false,
						scroll: false,						
						animate: false,		
						height: '150'
					}
	    ] 
		}); 
		layoutEl.render(); 
	}
	
	function buildSelectOption(results,jsObj)
	{
		var selectedValue=jsObj.reportLevel;
		
		var selectedElmt;
		if(selectedValue=="state")
		{
			if(jsObj.changed=="addProblem")
				selectedElmt=document.getElementById("districtField");
			else
				selectedElmt=document.getElementById("districtField");
		}	
		else if(selectedValue=="constituency")
		{
			if(jsObj.changed=="addProblem")
				selectedElmt=document.getElementById("pconstituencyField");	
			else
				selectedElmt=document.getElementById("constituencyField");			
		}
		else if(selectedValue=="Constituencies")
		{
			if(jsObj.changed=="addProblem")
				selectedElmt=document.getElementById("pmandalField");	
			else
				selectedElmt=document.getElementById("mandalField");
		}
		else if(selectedValue=="mandal")
		{
			if(jsObj.changed=="addProblem")
				selectedElmt=document.getElementById("pvillageField");	
			else
				selectedElmt=document.getElementById("villageField");
		}
		else if(selectedValue=="village")
		{
			if(jsObj.changed=="addProblem")
				selectedElmt=document.getElementById("phamletField");	
			else
				selectedElmt=document.getElementById("hamletField");
		}
	
		var len=selectedElmt.length;			
		for(i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}	
		for(var val in results)
		{			
			var opElmt=document.createElement('option');
			opElmt.value=results[val].id;
			opElmt.text=results[val].name;
			
			try
			{
				selectedElmt.add(opElmt,null); // standards compliant
			}
			catch(ex)
			{
				selectedElmt.add(opElmt); // IE only
			}			
		}
	}
	
	function callAjax(param,jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);												
									var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
									ajaxImgSpanElmt.style.display = 'none';		
									if(jsObj.task == "findVoters")
									{										
										showVotersData(myResults)										
									}
									else if(jsObj.task == "addNewProblem")
									{										
										updateNewProblemData(myResults)										
									} else if(jsObj.task == "newProblemsByUserID")
									{
										showNewProblemsForUser(myResults);																					
									} else if (jsObj.task == "classifyProblem")
									{
										updateClassifyDataTable(myResults);
									} else if(jsObj.task == "classifiedProblemsByUserID") 
									{
										showClassifiedProblemsForUser(myResults)	
									} else if(jsObj.task == "departmentsByScope")
									{
										showEditClassifyProbsDialog(myResults)
									} else if(jsObj.task == "assignProblem")
									{
										updateAssignedTable(myResults)
									} else if(jsObj.task == "assignedProblemsByUserID")
									{
										showAssignedProblemsForUser(myResults)
									} else if(jsObj.task == "moveAssignedProblemsToProgress") 
									{
										updateProgressDataTable(myResults)
									} else if(jsObj.task == "progressedProblemsByUserID")
									{
										showProgressedProblemsForUser(myResults)
									}  else if(jsObj.task == "moveProgressProblemsToPending")
									{
										updatePendingProblemsTable(myResults)
									}  else if(jsObj.task == "moveProgressProblemsToFixed")
									{
										updateFixedProblemsTable(myResults)
									} else if(jsObj.task == "pendingProblemsByUserID")
									{
										showPendingProblemsForUser(myResults);
									} else if(jsObj.task == "fixedProblemsByUserID")
									{
										showFixedProblemsForUser(myResults);
									} else if(jsObj.task == "movePendingProblemsToProgress")
									{
										updateProgressTableWithProblemsFromPendingToProgress(myResults)
									} 
									else
									{
										buildSelectOption(myResults,jsObj);									
									}
							}catch (e) {   
								var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
								ajaxImgSpanElmt.style.display = 'none';
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
	
	function showVotersData(results)
	{	
		assignToVotersArray = new Array();
		assignToVotersCastStats = new Array();
		assignToVotersByHouseNo = new Array();
		assignToLocalLeaders = new Array();
		assignToPoliticalChanges = new Array();
		assignToLocalProblems = new Array();
		assignToTotalLeaders = new Array();
		assignToElectedLeaders = new Array();

		var localLeaders = results.localLeaders;
		var voters = results.voterDetails;
		var cast = results.voterCastInfodetails.castVOs;
		var totalVoters = results.voterCastInfodetails.totalVoters;
		var maleVoters = results.voterCastInfodetails.maleVoters;
		var femaleVoters = results.voterCastInfodetails.femaleVoters;
		var votersByHouseNo = results.votersByHouseNos;
		var politicalChanges = results.politicalChanges;
		var localProblems = results.hamletProblems;
		var totalLeaders = results.totalMPTCMandalLeaderVO.totalLeaders;
		var electedLeaders = results.totalMPTCMandalLeaderVO.winners;
		var count=0;
		var votersByHouseNoCount = 0;
		var localProbCount = 0;
		var elmt = document.getElementById("alertMessage");
		var totalLeadersCount = 0;
		var electedLeadersCount = 0;		
		for(var i in voters)
		{
			count = count + 1;
			var voterObj= {
								sNo: count,
								name: voters[i].voterFirstName,
								gender: voters[i].gender,
								age: voters[i].age,
								hNo: voters[i].houseNo,
								guardianName: voters[i].relativeFirstName,
								relationship: voters[i].relationshipType,
								cast: voters[i].cast,
								castCategory: voters[i].castCatagery
							};
			
			assignToVotersArray.push(voterObj);
			constMgmtMainObj.votersArray=assignToVotersArray;

			
		}
		
		for(var i in cast)
		{	
			var castStats= { 

								caste: cast[i].castName,
								castePopulation: cast[i].castCount,
								castePercentage: cast[i].castPercentage				
				
							};
			assignToVotersCastStats.push(castStats);
			constMgmtMainObj.castStatsArray = assignToVotersCastStats;
		}

		for(var i in votersByHouseNo)
		{
			votersByHouseNoCount = votersByHouseNoCount + 1;		
			var totalVotersByHouseNos = {
						sNo: votersByHouseNoCount,
						houseNo: votersByHouseNo[i].houseNo,
						membersInFamily: votersByHouseNo[i].numberOfPeople,
						eldestPersonName: votersByHouseNo[i].elder,
						youngestPersonName: votersByHouseNo[i].younger,
						cast: votersByHouseNo[i].cast						                    	
					}; 
			assignToVotersByHouseNo.push(totalVotersByHouseNos);
			constMgmtMainObj.votersByHouseNoArray=assignToVotersByHouseNo;
		}
		
		for(var i in localLeaders)
		{
			var localLeadersDetails = {

					name: localLeaders[i].name,
					occupation: localLeaders[i].occupation,
					position: localLeaders[i].position,
					influenceScope: localLeaders[i].influenceScope,
					contactNumber: localLeaders[i].contactNumber
					};
			assignToLocalLeaders.push(localLeadersDetails);
			constMgmtMainObj.localLeadersArray=assignToLocalLeaders;
				
		}

		for(var i in politicalChanges)
		{

			var localPoliticalChanges = {
					 
					description: politicalChanges[i].description,
					date: politicalChanges[i].date,
					impact: politicalChanges[i].impact
			};
			assignToPoliticalChanges.push(localPoliticalChanges);
			constMgmtMainObj.localPoliticalChangesArray=assignToPoliticalChanges;
			
		}
		
		for(var i in localProblems)
		{
			localProbCount = localProbCount + 1;
			var localProblemsObj = {
					sNo: localProbCount,
					description: localProblems[i].problemDesc,
					identifiedDate: localProblems[i].identifiedDate,
					source: localProblems[i].problemSource,
					status: localProblems[i].problemStatus
			};
			assignToLocalProblems.push(localProblemsObj);
			constMgmtMainObj.localProblemsArr=assignToLocalProblems;
			
		}
		for(var i in totalLeaders)
		{	
			totalLeadersCount = totalLeadersCount + 1;
			var totalLeadersObj = {
					sNo: totalLeadersCount,
					electionYear: totalLeaders[i].electionYear,
					rank: totalLeaders[i].rank,
					mptcName: totalLeaders[i].mptcName,
					party: totalLeaders[i].party,
					candidateName: totalLeaders[i].candidateName,
					votesEarned: totalLeaders[i].candidateEarnedVotes,
					totalValidVotes: totalLeaders[i].validVotes	
			};
			assignToTotalLeaders.push(totalLeadersObj);
			constMgmtMainObj.totalLeadersArr=assignToTotalLeaders;

		}
		for(var i in electedLeaders )
		{	
			electedLeadersCount = electedLeadersCount + 1;
			var electedLeadersObj = {
				sNo: electedLeadersCount,
				electionYear: electedLeaders[i].electionYear,
				mptcName: electedLeaders[i].mptcName,
				party: electedLeaders[i].party,
				candidateName: electedLeaders[i].candidateName,
				votesEarned: electedLeaders[i].candidateEarnedVotes,
				totalValidVotes: electedLeaders[i].validVotes	
			};
			
			assignToElectedLeaders.push(electedLeadersObj);
			constMgmtMainObj.electedMandalLeadersArr = assignToElectedLeaders;

		}
		var localCastStatsTabContent_headerEl = document.getElementById("localCastStatsTabContent_header");
		var localCastStatsTabContent = '<table width="80%">';
		localCastStatsTabContent+='<tr colspan="2">';
		localCastStatsTabContent+='<th><%=totalNumOfVoters%></th>';
		localCastStatsTabContent+='<td align="left">'+totalVoters+'</td>';
		localCastStatsTabContent+='</tr>';
		localCastStatsTabContent+='<tr>';
		localCastStatsTabContent+='<th><%=maleVoters%></th>';
		localCastStatsTabContent+='<td align="left">'+maleVoters+'</td>';
		localCastStatsTabContent+='<th><%=femaleVoters%></th>';
		localCastStatsTabContent+='<td>'+femaleVoters+'</td>';
		localCastStatsTabContent+='</tr>';
		localCastStatsTabContent+='</table>';
		localCastStatsTabContent_headerEl.innerHTML=localCastStatsTabContent;
		var emptyArr = new Array();
		    if(localLeaders.length == 0)
			{	
				constMgmtMainObj.localLeadersArray = emptyArr;				
			} if(localProblems.length == 0)
			{
				constMgmtMainObj.localProblemsArr = emptyArr;				
			} if(cast.length == 0)
			{
				constMgmtMainObj.castStatsArray = emptyArr;				
			} if(politicalChanges.length == 0)
			{
				constMgmtMainObj.localPoliticalChangesArray = emptyArr;				
			} if (voters.length == 0)
			{	
				constMgmtMainObj.votersArray = emptyArr;				
			} if(votersByHouseNo.length == 0)
			{
				constMgmtMainObj.votersByHouseNoArray = emptyArr;				
			} if(totalLeaders.length == 0)
			{
				constMgmtMainObj.totalLeadersArr = emptyArr;
			} if(electedLeaders == 0)
			{
				constMgmtMainObj.electedMandalLeadersArr = emptyArr;
			}
			buildLocalPoliticalChangesDataTable();
			buildLocalLeadersDataTable();
			buildLocalCastStatisticsDataTable();			
			buildVotersByLocBoothDataTable();
			buildImportantVotersDataTable();
			buildLocalProblemsDataTable();
			buildAllMandalLeadersDataTable();
			buildElectedMandalLeadersDataTable();		
			
		constMgmtTabs.getTab(0).set("disabled", false);
		constMgmtTabs.getTab(0).set("active", true);
		constMgmtTabs.getTab(1).set("active", false);
		constMgmtTabs.getTab(2).set("active", false);
		constMgmtTabs.getTab(3).set("active", false);
		constMgmtTabs.getTab(4).set("active", false);
		constMgmtTabs.getTab(5).set("active", false);
		constMgmtTabs.getTab(6).set("active", false);
		constMgmtTabs.getTab(1).set("disabled", false);
		constMgmtTabs.getTab(2).set("disabled", false);
		constMgmtTabs.getTab(3).set("disabled", false);
		constMgmtTabs.getTab(4).set("disabled", false);
		constMgmtTabs.getTab(5).set("disabled", false);
		constMgmtTabs.getTab(6).set("disabled", false);	
		elmt.style.display = 'none';			
	}

	function getTownshipsForMandal(name,value,choice)
	{
		var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
		ajaxImgSpanElmt.style.display = 'block';
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:name,
					selected:value,
					changed:choice,
					task:"findTownships"
					
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/townshipsForMandalAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}
	
	function getnextList(name,value,choice)
	{
		var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
		ajaxImgSpanElmt.style.display = 'block';
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:name,
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}
	
	function getConstituencyList(name,value,choice)
	{
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:"constituency",
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
		var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
	}
	
	function getMandalList(name,value,choice)
	{
		var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
		ajaxImgSpanElmt.style.display = 'block';
		var jsObj=
			{
					type:"cadreDetails",
					reportLevel:"Constituencies",
					selected:value,
					changed:choice
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
		var url = "<%=request.getContextPath()%>/cadreRegisterAjaxAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
	}
	
	function getVotersForHamlet(name,value)
	{
		var ajaxImgSpanElmt = document.getElementById("ajaxImgSpan");
		var mandalID = document.getElementById("mandalField").value;
		ajaxImgSpanElmt.style.display = 'block';
		var jsObj=
			{
					selectName:name,
					selected:value,
					MANDAL:mandalID,
					task:"findVoters"
			}
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
		var url = "<%=request.getContextPath()%>/voterInfoAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
	}

	function getTodayDateTime()
	{		now = new Date()
			hour = now.getHours();
		 	min = now.getMinutes();
		  	sec = now.getSeconds();
		    if (min <= 9) {
		      min = "0" + min;
		    }
		    if (sec <= 9) {
		      sec = "0" + sec;
		    }
		    if (hour > 12) {
		      hour = hour - 12;
		      add = " p.m.";
		    } else {
		      hour = hour;
		      add = " a.m.";
		    }
		    if (hour == 12) {
		      add = " p.m.";
		    }
		    if (hour == 00) {
		      hour = "12";
		    }

		    currentTime = ((hour<=9) ? "0" + hour : hour) + ":" + min + ":" +  add;
		    return todaysDateTime = new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear()+" "+currentTime;
		    
	}
	
	function getNewProblemsForUser()
	{	
		var elmt = document.getElementById("alertMessage");
		elmt.style.display = 'none';
		var jsObj= 
		{
			task:"newProblemsByUserID"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+param;
		callAjax(param,jsObj,url);		
	}
	
	function updateNewProblemData(results)
	{	
		
		var newProbDataObj=
		{		
				title: results.problemBeanVO.problem,
				description: results.problemBeanVO.description,
				identifiedDate: results.problemBeanVO.reportedDate,
				existingFrom: results.problemBeanVO.existingFrom,
				location: results.problemBeanVO.hamlet,
				source: results.problemBeanVO.probSource,
				probLocationId: results.problemBeanVO.problemLocationId,
				probHistoryId: results.problemBeanVO.problemHistoryId  		
		};
		
		newProbDataTable.addRow(newProbDataObj,0);				
	}
	
	function showNewProblemsForUser(results)
	{	
		var assignToProbRegScopesArr = new Array();
		var assignToNewProblemsArr = new Array();
		var assignToProbTypeArr = new Array();
		var newProblems = results.problemsOfUserVO.problemsByUser;		
		var probScopes = results.problemsOfUserVO.problemRegionScopes;
		var probTypes = results.problemsOfUserVO.problemTypes;
		for (var i in newProblems )
		{
			var newProblemObj={
					
					 probId: newProblems[i].problemId, 
					 title: newProblems[i].problem,
					 description: newProblems[i].description,
					 identifiedDate: newProblems[i].reportedDate,
					 existingFrom: newProblems[i].existingFrom,
					 location : newProblems[i].hamlet,
					 probLocationId: newProblems[i].problemLocationId,
					 hamletID: newProblems[i].hamletId,
					 source: newProblems[i].probSource,
					 probHistoryId: newProblems[i].problemHistoryId	 
					 };
			assignToNewProblemsArr.push(newProblemObj);
			problemsMainObj.newProblemsArr = assignToNewProblemsArr;						
		}
		
		for (var j in probScopes)
		{
			var probScopeObj= probScopes[j].name;
			assignToProbRegScopesArr.push(probScopeObj);
			problemsMainObj.problemRegionScopeArr = assignToProbRegScopesArr;
		
		}
		for(var k in probTypes)
		{
			var probTypesObj = probTypes[k].name;
			assignToProbTypeArr.push(probTypesObj);
			problemsMainObj.probTypesArr = assignToProbTypeArr;
		}		
		buildNewProblemsDataTable();
		//newProbDataTable.addRows(problemsMainObj.newProblemsArr);		
		}
	
	function handleUserGrpsTabClick ()
	{
		var elmt = document.getElementById("alertMessage");
		elmt.style.display = 'none';	
	}
	
	function handleRecommLetrsTabClick()
	{
		var elmt = document.getElementById("alertMessage");
		elmt.style.display = 'none';	
	} 	

	function handleDistPapersTabClick ()
	{
		var elmt = document.getElementById("alertMessage");
		elmt.style.display = 'none';
	}
	
	function updateClassifyDataTable(results)
	{	
		var assignToClsfdDtArr = new Array();
		var clsfdProblems = results.classifiedProblems;
		for(var i in clsfdProblems)
		{
			var clsfdProObj = {
				title: '',
				identifiedDate: '',
				location: '',
				scope: '',
				problemType: '',
				problemHistoryId: '',
				department: ''
			};
			clsfdProObj.title = clsfdProblems[i].problem;
			clsfdProObj.identifiedDate = clsfdProblems[i].reportedDate;
			clsfdProObj.location =  clsfdProblems[i].address;
			clsfdProObj.scope = clsfdProblems[i].problemSourceScope;
			clsfdProObj.problemType = clsfdProblems[i].problemType;
			clsfdProObj.problemHistoryId = clsfdProblems[i].problemHistoryId;
			clsfdProObj.department = "<input type=button value=SelectDepartment id=showdepts>";			
			assignToClsfdDtArr.push(clsfdProObj);			
			problemsMainObj.classifiedProblemsArr = assignToClsfdDtArr;	
					
		}
		
		classifiedDataTable.addRows(problemsMainObj.classifiedProblemsArr);
	}

	function handleNewIssuesTabClick()
	{	
		var emptyArray = new Array();
		recordsArray = emptyArray; 	
	}
	
	function handleClassifiedIssTabClick()
	{		
		if(recordsArray.length>0)
		{					
			var confirmEl = confirm("Do You Wish To Continue With out Saving Changes!");
			if(confirmEl == true)
			{
				recordsArray = new Array();					
				problemMgmtTabs.getTab(1).set("disabled", false);
				problemMgmtTabs.getTab(1).set("active", true);
				problemMgmtTabs.set('activeIndex', 1);
				newProbDataTable.scopeCellEd.cancel();
			}
			else
				problemMgmtTabs.set('activeIndex', 0);								
		}

		var jsObj= 
		{
			task: "classifiedProblemsByUserID",
			status: "2"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+param;
		callAjax(param,jsObj,url);				
	}

	
	function showClassifiedProblemsForUser(results)
	{
		
		var assignToClassifiedArray = new Array();
		var classifiedProblems = results.classifiedProblems;			
		for (var i in classifiedProblems)
		{				
			var classifiedProblemsObj = 
			{
				title: '',
				identifiedDate: '',
				location: '',
				scope: '',
				problemType: '',
				problemHistoryId: '',
				department:	''			 								
			};					
			classifiedProblemsObj.title = classifiedProblems[i].problem;
			classifiedProblemsObj.identifiedDate = classifiedProblems[i].reportedDate;
			classifiedProblemsObj.location =  classifiedProblems[i].address;
			classifiedProblemsObj.scope = classifiedProblems[i].problemSourceScope;
			classifiedProblemsObj.problemType = classifiedProblems[i].problemType;
			classifiedProblemsObj.problemHistoryId = classifiedProblems[i].problemHistoryId;
			classifiedProblemsObj.department = "<input type=button value=SelectDepartment id=showdepts>";

			if(!hasRecordInClassifiedArray(classifiedProblems[i].problemHistoryId))			
				assignToClassifiedArray.push(classifiedProblemsObj);													 			
		}
		
		buildClassifiedDataTable();
		if(assignToClassifiedArray.length > 0)
		{
			classifiedDataTable.addRows(assignToClassifiedArray);
		}				  	
	}


	function hasRecordInClassifiedArray(id)
	{
		var status = false;
		if(problemsMainObj.classifiedProblemsArr.length == 0)
			return status;
		for(var i=0;i<problemsMainObj.classifiedProblemsArr.length;i++)
		{
			if(problemsMainObj.classifiedProblemsArr[i].problemHistoryId == id)
				status=true; 
		}
		return status;
	}
			
	function updateAssignedTable(results)
	{
		var assignToAssignedArr = new Array();
		var assignedProblems = results.assignedProblems;
		for (var i in assignedProblems)
		{
			var asignedProbObj ={
					title: assignedProblems[i].problem,
					concernedDepartment: assignedProblems[i].department,
					assignedProblemProgressId: assignedProblems[i].assignedProblemProgressId
										  
					};
			assignToAssignedArr.push(asignedProbObj);
			problemsMainObj.assignedProblemsArr = assignToAssignedArr;
		}
		assignedIssDataTable.addRows(problemsMainObj.assignedProblemsArr);
	}

	function handleAssignedIssTabClick()
	{
	
		if(clasifyDtRecordsArray.length>0)
		{					
			var confirmEl = confirm("Do You Wish To Continue With out Saving Changes!");
			if(confirmEl == true)
			{
				clasifyDtRecordsArray = new Array();					
				problemMgmtTabs.getTab(2).set("disabled", false);
				problemMgmtTabs.getTab(0).set("disabled", false);
				
				problemMgmtTabs.set('activeIndex', 2);
			}
			else
				problemMgmtTabs.set('activeIndex', 1);								
		}
		var jsObj= 
		{
			task: "assignedProblemsByUserID",
			status: "3"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+param;
		callAjax(param,jsObj,url);
	}	
	
	function showAssignedProblemsForUser(results)
	{
		var assignToAssignedProbsForUserArr = new Array();
		var assignedProbsForUser = results.assignedProblems;
		for(var i in assignedProbsForUser)
		{
			var assignedProbObj = {
					title: assignedProbsForUser[i].problem ,
					concernedDepartment: assignedProbsForUser[i].department,
					assignedProblemProgressId: assignedProbsForUser[i].assignedProblemProgressId
					};
			if(!hasRecordInAssignedDTArray(assignedProbsForUser[i].assignedProblemProgressId))		
				assignToAssignedProbsForUserArr.push(assignedProbObj);
			}
		
		buildAssignedIssuesDataTable();
		if(assignToAssignedProbsForUserArr.length > 0 )
		{
			assignedIssDataTable.addRows(assignToAssignedProbsForUserArr);
		}
	}

	function hasRecordInAssignedDTArray(id)
	{
		
		var status = false;
		if(problemsMainObj.assignedProblemsArr.length == 0)
			return status;
		for(var i=0;i<problemsMainObj.assignedProblemsArr.length;i++)
		{
			if(problemsMainObj.assignedProblemsArr[i].assignedProblemProgressId == id)
				status=true; 
		}
		return status;
		
	}


	function updateProgressDataTable(results)
	{
		var assignToProgressArray = new Array();
		var progressedProblems = results.progressedProblems;
		for(var i in progressedProblems)
		{
			var progressedProblemsObj={

					title: progressedProblems[i].problem,
					identifiedDate: progressedProblems[i].reportedDate,
					concernedDepartment: progressedProblems[i].department,
					assignedOfficial: progressedProblems[i].designation,
					name: progressedProblems[i].departmentConcernedPersonName,
					contactNumber: progressedProblems[i].departmentConcernedPersonPhoneNumber,
					assignedProblemProgressId: progressedProblems[i].assignedProblemProgressId					 
					};
			assignToProgressArray.push(progressedProblemsObj);
			problemsMainObj.progressedProblemsArr = assignToProgressArray;
			}
		 
		progessIssuesDataTable.addRows(problemsMainObj.progressedProblemsArr);
	}

	function handleProgressedIssTabClick()
	{
		if(assignDtRecordsArray.length>0)
		{					
			var confirmEl = confirm("Do You Wish To Continue With out Saving Changes!");
			if(confirmEl == true)
			{
				assignDtRecordsArray = new Array();
				problemMgmtTabs.getTab(3).set("disabled", false);					
				problemMgmtTabs.getTab(1).set("disabled", false);
				problemMgmtTabs.getTab(0).set("disabled", false);
				
				problemMgmtTabs.set('activeIndex', 3);
			}
			else
				problemMgmtTabs.set('activeIndex', 2);								
		}
		var jsObj= 
		{
			task: "progressedProblemsByUserID",
			status: "4"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+param;
		callAjax(param,jsObj,url);		
	}

	function showProgressedProblemsForUser(results)
	{
		var assignToProgressedProbsArr = new Array();
		var progressedProblems = results.progressedProblems;
		for(var i in progressedProblems)
		{
			var progressedProblemsObj={

					title: progressedProblems[i].problem,
					identifiedDate: progressedProblems[i].reportedDate,
					concernedDepartment: progressedProblems[i].department,
					assignedOfficial: progressedProblems[i].designation,
					name: progressedProblems[i].departmentConcernedPersonName,
					contactNumber: progressedProblems[i].contactNo,
					assignedProblemProgressId: progressedProblems[i].assignedProblemProgressId					 
					};

			if(!hasRecordInProgressedDTArray(progressedProblems[i].assignedProblemProgressId))	
					assignToProgressedProbsArr.push(progressedProblemsObj);	
			//problemsMainObj.progressedProblemsArr = assignToProgressedProbsArr;		
			}
		
		//problemsMainObj.progressedProblemsArr = assignToProgressedProbsArr;
		buildProgressDataTable();
		if(assignToProgressedProbsArr.length>0)
		{		 
			progessIssuesDataTable.addRows(assignToProgressedProbsArr);
		}
	//	progessIssuesDataTable.addRows(problemsMainObj.progressedProblemsArr);		
				
	}

	function hasRecordInProgressedDTArray(id)
	{
		var status = false;
		if(problemsMainObj.progressedProblemsArr.length == 0)
			return status;
		for(var i=0;i<problemsMainObj.progressedProblemsArr.length;i++)
		{
			if(problemsMainObj.progressedProblemsArr[i].assignedProblemProgressId == id)
				status=true; 
		}
		return status;
	}


	function handlePendingIssTabClick()
	{
		if(progressDtRecordsArray.length>0)
		{					
			var confirmEl = confirm("Do You Wish To Continue With out Saving Changes!");
			if(confirmEl == true)
			{
				progressDtRecordsArray = new Array();					
				problemMgmtTabs.getTab(0).set("disabled", false);
				problemMgmtTabs.getTab(1).set("disabled", false);
				problemMgmtTabs.getTab(2).set("disabled", false);
				problemMgmtTabs.getTab(4).set("disabled", false);
				problemMgmtTabs.set('activeIndex', 4);
			}
			else
				problemMgmtTabs.set('activeIndex', 3);								
		}
		
		var jsObj= 
		{
			task: "pendingProblemsByUserID",
			status: "5"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+param;
		callAjax(param,jsObj,url);		
	}
	
	function updatePendingProblemsTable(results)
	{
		var assignToPendingProblemsArr = new Array();
		var pendingProblems =results.pendingProblems;
		for (var i in pendingProblems)
		{
			var pendingProblemsObj = {
					 title: pendingProblems[i].problem,
					 identifiedDate: pendingProblems[i].reportedDate,
					 concernedDepartment: pendingProblems[i].department,
					 assignedOfficial: pendingProblems[i].designation,
					 name: pendingProblems[i].departmentConcernedPersonName,
					 contactNumber: pendingProblems[i].contactNo,
					 pendingFrom: pendingProblems[i].updatedDate,
					 reason: pendingProblems[i].reasonForPending,
					 assignedProblemProgressId: pendingProblems[i].assignedProblemProgressId
					};
			assignToPendingProblemsArr.push(pendingProblemsObj);
			problemsMainObj.pendingProblemArr = assignToPendingProblemsArr;			
		}
		pendingIssuesDataTable.addRows(problemsMainObj.pendingProblemArr);
		
	}

	function showPendingProblemsForUser(results)
	{

		var assignToPendingProblemsArr = new Array();
		var pendingProblems =results.pendingProblems;
		for (var i in pendingProblems)
		{
			var pendingProblemsObj = {
					 title: pendingProblems[i].problem,
					 identifiedDate: pendingProblems[i].reportedDate,
					 concernedDepartment: pendingProblems[i].department,
					 assignedOfficial: pendingProblems[i].designation,
					 name: pendingProblems[i].departmentConcernedPersonName,
					 contactNumber: pendingProblems[i].contactNo,
					 pendingFrom: pendingProblems[i].updatedDate,
					 reason: pendingProblems[i].reasonForPending,
					 assignedProblemProgressId: pendingProblems[i].assignedProblemProgressId
					};
			if(!hasRecordInPendingDTArray(pendingProblems[i].assignedProblemProgressId))
				assignToPendingProblemsArr.push(pendingProblemsObj);
			//problemsMainObj.pendingProblemArr = assignToPendingProblemsArr;			
		}
		buildPendingDataTable();
		if(assignToPendingProblemsArr.length > 0)
		{
		pendingIssuesDataTable.addRows(assignToPendingProblemsArr);
		}			
	}	

	function hasRecordInPendingDTArray(id)
	{
		var status = false;
		if(problemsMainObj.pendingProblemArr.length == 0)
			return status;
		for(var i=0;i<problemsMainObj.pendingProblemArr.length;i++)
		{
			if(problemsMainObj.pendingProblemArr[i].assignedProblemProgressId == id)
				status=true; 
		}
		return status;
	}
	
	function handleFixedIssTabClick()
	{
		if(pendingDtRecordsArray.length>0)
		{					
			var confirmEl = confirm("Do You Wish To Continue With out Saving Changes!");
			if(confirmEl == true)
			{
				pendingDtRecordsArray = new Array();					
				problemMgmtTabs.getTab(0).set("disabled", false);
				problemMgmtTabs.getTab(1).set("disabled", false);
				problemMgmtTabs.getTab(2).set("disabled", false);
				problemMgmtTabs.getTab(3).set("disabled", false);
				problemMgmtTabs.getTab(5).set("disabled", false);
				
				
				problemMgmtTabs.set('activeIndex', 5);
			}
			else
				problemMgmtTabs.set('activeIndex', 4);								
		}
		var jsObj= 
		{
			task: "fixedProblemsByUserID",
			status: "6"
		}
		var param="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+param;
		callAjax(param,jsObj,url);		
	}
	
	function updateFixedProblemsTable(results)
	{
		var assignToFixedProblemsArr = new Array();
		var fixedProblems = results.fixedProblems;
		for (var i in fixedProblems)
		{
			var fixedProblemsObj = {
					 title: fixedProblems[i].problem,
					 identifiedDate: fixedProblems[i].reportedDate,
					 concernedDepartment: fixedProblems[i].department,
					 fixedDate: fixedProblems[i].updatedDate,
					 comments: fixedProblems[i].comments,
					 assignedProblemProgressId: fixedProblems[i].assignedProblemProgressId
					};
			assignToFixedProblemsArr.push(fixedProblemsObj);
			problemsMainObj.fixedProblemsArr = assignToFixedProblemsArr;			
		}
		fixedIssuesDataTable.addRows(problemsMainObj.fixedProblemsArr);
		
	}

	function showFixedProblemsForUser(results)
		{
		var assignToFixedProblemsArr = new Array();
		var fixedProblems = results.fixedProblems;
		for (var i in fixedProblems)
		{
			var fixedProblemsObj = {
					 title: fixedProblems[i].problem,
					 identifiedDate: fixedProblems[i].reportedDate,
					 concernedDepartment: fixedProblems[i].department,
					 fixedDate: fixedProblems[i].updatedDate,
					 comments: fixedProblems[i].comments,
					 assignedProblemProgressId: fixedProblems[i].assignedProblemProgressId
					};
			if(!hasRecordInFixedDTArray(fixedProblems[i].assignedProblemProgressId))
				assignToFixedProblemsArr.push(fixedProblemsObj);
			//problemsMainObj.fixedProblemsArr = assignToFixedProblemsArr;			
		}
		buildFixedDataTable();
		if(assignToFixedProblemsArr.length>0)
		{
			fixedIssuesDataTable.addRows(assignToFixedProblemsArr);
		}
		
		}

	function hasRecordInFixedDTArray(id)
	{
		var status = false;
		if(problemsMainObj.fixedProblemsArr.length == 0)
			return status;
		for(var i=0;i<problemsMainObj.fixedProblemsArr.length;i++)
		{
			if(problemsMainObj.fixedProblemsArr[i].assignedProblemProgressId == id)
				status=true; 
		}
		return status;
	} 

	function updateProgressTableWithProblemsFromPendingToProgress(results)
	{
		var assignToProgressArray = new Array();
		var progressedProblems = results.progressedProblems;
		for(var i in progressedProblems)
		{
			var progressedProblemsObj={

					title: progressedProblems[i].problem,
					identifiedDate: progressedProblems[i].reportedDate,
					concernedDepartment: progressedProblems[i].department,
					assignedOfficial: progressedProblems[i].designation,
					name: progressedProblems[i].departmentConcernedPersonName,
					contactNumber: progressedProblems[i].departmentConcernedPersonPhoneNumber,
					assignedProblemProgressId: progressedProblems[i].assignedProblemProgressId					 
					};
			assignToProgressArray.push(progressedProblemsObj);
			//problemsMainObj.progressedProblemsArr = assignToProgressArray;
			}
		 
		progessIssuesDataTable.addRows(assignToProgressArray);
		
	}
	
	function buildOuterTabView()
	{
		outerTab = new YAHOO.widget.TabView();		
		var constTabContent ='<div id="constituencyMgmtTabContentDiv">';		
		constTabContent+='<div id="constMgmtTabContentDiv_head" align="left">';
		constTabContent+='<table width="100%">';
		constTabContent+='<tr>';
		constTabContent+='<td><%=STATE%></td>';
		constTabContent+='<td><select id="stateField" class="selectWidth" name="state" onchange="getnextList(this.name,this.options[this.selectedIndex].value,false)" width="10">';
		for(var i in locationDetails.stateArr)
		{
			constTabContent+='<option value='+locationDetails.stateArr[i].id+'>'+locationDetails.stateArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td><%=DISTRICT%></td>';
		constTabContent+='<td><select id="districtField" class="selectWidth" name="district"  onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.districtArr)
		{
			constTabContent+='<option value='+locationDetails.districtArr[i].id+'>'+locationDetails.districtArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td><%=CONSTITUENCY%></td>';
		constTabContent+='<td><select id="constituencyField" class="selectWidth" name="constituency"  onchange="getMandalList(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.constituencyArr)
		{
			constTabContent+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
		} 
		constTabContent+='</select></td>';
		constTabContent+='</tr>';
		
		constTabContent+='<tr>';
		constTabContent+='<td><%=MANDAL%></td>';
		constTabContent+='<td><select id="mandalField" class="selectWidth" name="mandal" onchange="getTownshipsForMandal(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.mandalArr)
		{
			constTabContent+='<option value='+locationDetails.mandalArr[i].id+'>'+locationDetails.mandalArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td><%=VILLAGE%></td>';
		constTabContent+='<td><select class="selectWidth" id="villageField" name="village" onchange="getnextList(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.villageArr)
		{
			constTabContent+='<option value='+locationDetails.villageArr[i].id+'>'+locationDetails.villageArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td><%=HAMLET%></td>';
		constTabContent+='<td><select class="selectWidth" id="hamletField" name="hamlet" onchange="getVotersForHamlet(this.name,this.options[this.selectedIndex].value)">';
		for(var i in locationDetails.hamletArr)
		{
			constTabContent+='<option value='+locationDetails.hamletArr[i].id+'>'+locationDetails.hamletArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='</tr>';
		constTabContent+='<tr><td colspan="6" align="center">';
		constTabContent+='<span id="ajaxImgSpan"><img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/></span>';
		constTabContent+='</td></tr>';
		constTabContent+='</table>';				
		constTabContent+='</div>';
		constTabContent+='<div id="constMgmtTabContentDiv_body"></div>';
		constTabContent+='<div id="constMgmtTabContentDiv_footer"></div>';
		constTabContent+='</div>';
		outerTab.addTab( new YAHOO.widget.Tab({ 
			
		label: '<%=constituencyMgmt%>', 
	    content:constTabContent, 
	    active: true 
		})); 
		
		outerTab.addTab( new YAHOO.widget.Tab({ 
	    label: '<%=probMgmt%>', 
	    content: '<div id="problemMgmtTabContentDiv"></div>'	   
		})); 
		
		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: '<%=userGroups%>', 
			content: '<div id="userGroupsTabContent">User Groups Content</div>' 
		 
		})); 
		 
		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: '<%=recommLetters%>', 
			content: '<div id="recomLettTabContent">Recommendation Letters Content</div>' 
		})); 

		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: '<%=distEPapers%>', 
			content: '<div id="distEPapersTabContent">District E Papers Content</div>' 
		})); 

		outerTab.appendTo('problemMgmtMainDiv');
		outerTab.getTab(1).addListener('click',getNewProblemsForUser);
		outerTab.getTab(2).addListener('click',handleUserGrpsTabClick);
		outerTab.getTab(3).addListener('click',handleRecommLetrsTabClick);
		outerTab.getTab(4).addListener('click',handleDistPapersTabClick); 
	}
	
	function buildProblemMgmtTabView()
	{	
		problemMgmtTabs = new YAHOO.widget.TabView(); 
		
		var newTabContent='<div id="newProblemTabContentDiv">';
		newTabContent+='<div id="NewIssuesAlertMessage" style="display: none;"><%=newIssuesAlertMessage%></div>';
		newTabContent+='</div>';
		newTabContent+='<div id="newProblemTabContentDiv_head"></div>';
		newTabContent+='<div id="newProblemTabContentDiv_body"></div>';
		newTabContent+='<div id="newProblemTabContentDiv_footer" align="right"></div>';
		newTabContent+='</div>';

		var classifiedTabContent='<div id="classifiedTabContentDiv">';
		classifiedTabContent+='<div id="classifiedTabContentDiv_head">';
		classifiedTabContent+='<div id="clsfdIssuesAlertMsg" style="display: none;">Please Assign Problem To A Department!</div>';
		classifiedTabContent+='</div>';
		classifiedTabContent+='<div id="classifiedTabContentDiv_body"></div>';
		classifiedTabContent+='<div id="classifiedTabContentDiv_footer"></div>';
		classifiedTabContent+='</div>';
		
		var assignedIssuesTabContent='<div id="assignedIssuesTabContentDiv">';
		assignedIssuesTabContent+='<div id="assignedIssuesTabContentDiv_head">';
		assignedIssuesTabContent+='<div id="assignedIssuesAlertMsg" style="display: none;">Please Update Concerned Official Details!</div>';	
		assignedIssuesTabContent+='</div>';
		assignedIssuesTabContent+='<div id="assignedIssuesTabContentDiv_body"></div>';
		assignedIssuesTabContent+='<div id="assignedIssuesTabContentDiv_footer"></div>';
		assignedIssuesTabContent+='</div>';	

		var progressTabContent='<div id="progressTabContentDiv">';
		progressTabContent+='<div id="progressTabContentDiv_header">';
		progressTabContent+='<div id="progressedIssAlertMsg" style="display: none;">Please Give Comments!</div>';
		progressTabContent+='</div>';
		progressTabContent+='<div id="progressTabContentDiv_body"></div>';
		progressTabContent+='<div id="progressTabContentDiv_footer"></div>';
		progressTabContent+='</div>';			

		var pendingTabContent='<div id="pendingTabContentDiv">'; 
		pendingTabContent+='<div id="pendingTabContentDiv_head">';
		pendingTabContent+='<div id="pendingIssAlertMsg" style="display: none;">Please Give Comments</div>';
		pendingTabContent+='</div>';
		pendingTabContent+='<div id="pendingTabContentDiv_body"></div>';
		pendingTabContent+='<div id="pendingTabContentDiv_footer"></div>';
		//pendingTabContent+='<div id="pendingTabContentDiv_footer1"></div>';
		pendingTabContent+='</div>';		

		var fixedIssuesTabContent='<div id="fixedIssuesContentDiv"></div>';
		fixedIssuesTabContent+='<div id="fixedIssuesContentDiv_header"></div>';
		fixedIssuesTabContent+='<div id="fixedIssuesContentDiv_body"></div>';
		fixedIssuesTabContent+='<div id="fixedIssuesContentDiv_footer"></div>';
		fixedIssuesTabContent+='</div>';
		
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
	    label: '<%=newIssues%>', 
	    content:newTabContent, 
	    active: true 
		})); 
		
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
		label: '<%=clasfdIssues%>', 
		content:classifiedTabContent		 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
		label: '<%=assignedIssues%>', 
		content: assignedIssuesTabContent		 
		})); 
		 
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
		label: '<%=progress%>', 
		content: progressTabContent  
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
		label: '<%=pendingIssues%>', 
		content: pendingTabContent 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
		label: '<%=fixedIssues%>', 
		content: fixedIssuesTabContent
		})); 

		problemMgmtTabs.appendTo('problemMgmtTabContentDiv'); 
	      
			classifyButton = new YAHOO.widget.Button({ 
	                                            id: "mybuttonid",  
	                                            type: "button",  
	                                     		label: "<%=classify%>",  
	                                            container: "newProblemTabContentDiv_footer"  
	                                        }); 
			classifyButton.set("disabled", true );
			classifyButton.on("click", classifyBtnHandler);

			var addProblemButton = new YAHOO.widget.Button({ 
												id: "reportNewProblem",  
												type: "link",  
												label: "<%=addNewProb%>",
												href: "javascript:{}",
												container: "newProblemTabContentDiv_footer"  
												});

			addProblemButton.on("click", buildAddNewProblemPopup); 
			 		

			assignButton = new YAHOO.widget.Button({ 
								                id: "assignButton",  
								                type: "button",  
								                label: "<%=assign%>",  
								                container: "classifiedTabContentDiv_footer"  
            });
			assignButton.set("disabled", true ); 
			assignButton.on("click", assignButtonHandler);
			progressButton = new YAHOO.widget.Button({ 
								                id: "progressButton",  
								                type: "button",  
								                label: "<%=progress%>",  
								                container: "assignedIssuesTabContentDiv_footer"  
			});
			
			progressButton.set("disabled", true );
			progressButton.on("click",progressButtonClickHandler );
			
			pPendingButton = new YAHOO.widget.Button({ 
                id: "pPendingButton",  
                type: "button",  
                label: "<%=pending%>",  
                container: "progressTabContentDiv_footer"  
			});

			pPendingButton.set("disabled", true );
			pPendingButton.on("click",pPendingButtonClickHandler);
			pFixedButton = new YAHOO.widget.Button({ 
                id: "pFixedButton",  
                type: "button",  
                label: "<%=fixed%>",  
                container: "progressTabContentDiv_footer"  
			});

			pFixedButton.set("disabled", true );
			pFixedButton.on("click",pFixedButtonClickHandler);
			
			progressButtonInPending = new YAHOO.widget.Button({ 
                id: "pendingButton",  
                type: "button",  
                label: "<%=progress%>",  
                container: "pendingTabContentDiv_footer"  
			});

			progressButtonInPending.set("disabled", true );
			progressButtonInPending.on("click",progressButtonInPendingClickHandler);
			problemMgmtTabs.getTab(0).addListener('click',handleNewIssuesTabClick);
			problemMgmtTabs.getTab(1).addListener('click',handleClassifiedIssTabClick);	
			problemMgmtTabs.getTab(2).addListener('click',handleAssignedIssTabClick);
			problemMgmtTabs.getTab(3).addListener('click',handleProgressedIssTabClick);	
			problemMgmtTabs.getTab(4).addListener('click',handlePendingIssTabClick);
			problemMgmtTabs.getTab(5).addListener('click',handleFixedIssTabClick);			
	}
			
	function buildConstMgmtTabView()
	{	
		 constMgmtTabs = new YAHOO.widget.TabView(); 

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: '<%=localLeaders%>',
			content: '<div id="localLeadersTabContent"></div>',
			disabled: true			
		}));

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: '<%=localProbs%>',
			content: '<div id="localProblemsTabContent"></div>',
			disabled: true
		}));
		var castStatistics='<div id="localCastStatsTabContent">';
		castStatistics+='<div id="localCastStatsTabContent_header" align="left"></div>';
		castStatistics+='<div id="localCastStatsTabContent_body"></div>';
		castStatistics+='<div id="localCastStatsTabContent_footer"></div>';
		castStatistics+='</div>';
			constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: '<%=localCastStats%>',
			content: castStatistics,
			disabled: true
		}));

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: '<%=localPolChanges%>',
			content: '<div id="localPoliticalChangesTabContent"></div>',
			disabled: true
		}));

		var votersByLocBoothContent = '<div id="votersByLocationTabContentDiv">';
		votersByLocBoothContent+='<div id="votersByLocationTabContentDiv_head" align="left"></div>';
		votersByLocBoothContent+='<div id="votersByLocationTabContentDiv_body"></div>';
		votersByLocBoothContent+='<div id="votersByLocationTabContentDiv_footer"></div>';
		votersByLocBoothContent+='</div>';

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: '<%=voterByLoc%>',
			content: votersByLocBoothContent,
			disabled: true
			
		}));

		var importantVotersTabContent='<div id="importantVotersTabContent">';
		importantVotersTabContent+='<div id="importantVotersTabContent_header"></div>';
		importantVotersTabContent+='<div id="importantVotersTabContent_body"></div>';
		importantVotersTabContent+='<div id="importantVotersTabContent_footer"></div>';
		importantVotersTabContent+='</div>';

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: '<%=impVoters%>',
			content: importantVotersTabContent,
			disabled: true
			
		}));
		var mandalLeadersTabContent='<div id="mandalLeadersTabContent">';
		mandalLeadersTabContent+='<div id="mandalLeadersTabContent_header">';
		mandalLeadersTabContent+='<input type="radio" name="mandalLeaders" value="All" onclick="displayDiv(\'mandalLeadersAll\');hideDiv(\'mandalLeadersWinners\')"> All';
		mandalLeadersTabContent+='<input type="radio" name="mandalLeaders" value="Winners" onclick="displayDiv(\'mandalLeadersWinners\');hideDiv(\'mandalLeadersAll\')"> Winners';
		mandalLeadersTabContent+='</div>';
		mandalLeadersTabContent+='<div id="mandalLeadersTabContent_body">';
		mandalLeadersTabContent+='<div id="mandalLeadersAll" style="display:none;">1</div><div id="mandalLeadersWinners" style="display:none;">1</div></div>';
		mandalLeadersTabContent+='</div>';
		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: '<%=mandalLeaders%>',
			content: mandalLeadersTabContent,
			disabled: true
			
		}));
				
		constMgmtTabs.appendTo('constMgmtTabContentDiv_body');
		function handleClick(e) {   
			var id = document.getElementById("hamletField");
			if(id==null || id.value==0)
			var elmt = document.getElementById("alertMessage"); 
			elmt.style.display = 'block'; 
	    }
			constMgmtTabs.getTab(0).addListener('click', handleClick);
			constMgmtTabs.getTab(1).addListener('click', handleClick);
			constMgmtTabs.getTab(2).addListener('click', handleClick);
			constMgmtTabs.getTab(3).addListener('click', handleClick);
			constMgmtTabs.getTab(4).addListener('click', handleClick);
			constMgmtTabs.getTab(5).addListener('click', handleClick);
			constMgmtTabs.getTab(6).addListener('click', handleClick);			
	}
	
	YAHOO.example.Data = { 
	    
	        problems: [ 
		        {select:"<input type='checkbox' id='check_1'></input>", title:"Impurified Water Supply", description: "Polluted water is being supplied since two weeks", identifiedDate:new Date(2009, 2, 4), location:"MadanaPalle", source:"Party Analyst", status:"New"}, 
				{select:"<input type='checkbox' id='check_1'></input>", title:"No Bus Service", description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", identifiedDate:new Date("January 3, 2009"),
				location:"NagaVaram", source:"Victim", status:"Assigned"}, 
		        {select:"<input type='checkbox' id='check_1'></input>", title:"No White Ration Cards issued in Hamlet", description: "White Ration card is not at all issued to eligible families even after the preliminary process", identifiedDate:new Date(2009, 11, 12), location:"Eluru", source:"Call Centre", status:"Categorized"}, 
		        {select:"<input type='checkbox' id='check_1'></input>", title:"AarogyaSri", description: "Delay for Cardiac Surgery with AarogyaSri Scheme", identifiedDate:new Date("March 11,2009") , location:"Eluru", source:"User", status:"Categorized"}, 
				{select:"<input type='checkbox' id='check_1'></input>", title:"Delay in payment of Exgratia", description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party", identifiedDate:new Date(1980, 2, 4), location:"MadanaPalle", source:"Party Analyst", status:"New"}		
	    ]		           			
	}					
	function buildAllMandalLeadersDataTable()
		{
			
			var myColumnDefs = [ 
					{key:"sNo", label: "<%=sNo%>", formatter:"number", sortable:true},
		            {key:"electionYear", label: "<%=electionYear%>"}, 
		            {key:"rank", label: "<%=rank%>", sortable:true}, 
		            {key:"mptcName", label: "<%=mptcName%>", sortable:true}, 
					{key:"party", label: "<%=party%>",  sortable:true},
					{key:"candidateName", label: "<%=candidateName%>", sortable:true},	
					{key:"votesEarned", label: "<%=votesEarned%>", sortable:true},	
					{key:"totalValidVotes", label: "<%=totalValidVotes%>", sortable:true}
		        ]; 
			
		        var myDataSource = new YAHOO.util.DataSource(constMgmtMainObj.totalLeadersArr); 
		        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		        myDataSource.responseSchema = { 
		            fields: [{key:"sNo", parser:"number"},"electionYear","rank","mptcName","party","candidateName","votesEarned","totalValidVotes"] 
		        }; 
				
				var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				}; 

				var myDataTable =  
		            new YAHOO.widget.DataTable("mandalLeadersAll", myColumnDefs, myDataSource,myConfigs); 
		                 
		        
				problemMgmtTabs.getTab(0).addListener("click", function() {myDataTable.onShow()});         
							
				var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  }; 

				

				myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
				myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);
				myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
				
		        return { 
		            oDS: myDataSource, 
		            oDT: myDataTable 		           
		      }; 		    
		}
		
		function displayDiv(id){
			var ele = document.getElementById(id);
			ele.style.display='block';
		}
		function hideDiv(id){
			var ele = document.getElementById(id);
			ele.style.display='none';
		}

		function buildElectedMandalLeadersDataTable()
		{
			
			var myColumnDefs = [
					{key:"sNo", label: "<%=sNo%>", formatter:"number", sortable:true},
		            {key:"electionYear", label: "<%=electionYear%>"}, 
		            {key:"mptcName", label: "<%=mptcName%>", sortable:true}, 
					{key:"party", label: "<%=party%>",  sortable:true},
					{key:"candidateName", label: "<%=candidateName%>", sortable:true},	
					{key:"votesEarned", label: "<%=votesEarned%>", sortable:true},	
					{key:"totalValidVotes", label: "<%=totalValidVotes%>", sortable:true}
		        ]; 
			
		        var myDataSource = new YAHOO.util.DataSource(constMgmtMainObj.electedMandalLeadersArr); 
		        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		        myDataSource.responseSchema = { 
		            fields: [{key:"sNo", parser:"number"},"electionYear","mptcName","party","candidateName","votesEarned","totalValidVotes"] 
		        }; 
				
				var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				}; 

				var myDataTable =  
		            new YAHOO.widget.DataTable("mandalLeadersWinners", myColumnDefs, myDataSource,myConfigs); 
		                 
		        
				problemMgmtTabs.getTab(0).addListener("click", function() {myDataTable.onShow()});         
							
				var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  }; 

				

				myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
				myDataTable.subscribe("cellMouseoutEvent", myDataTable.onEventUnhighlightCell);
				myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
				
		        return { 
		            oDS: myDataSource, 
		            oDT: myDataTable 
		           
		      }; 
		    
		} 
		
	function buildNewProblemsDataTable()
	{	
		var scopeCellEd = new YAHOO.widget.DropdownCellEditor({multiple:false, LABEL_SAVE:"Select", dropdownOptions: problemsMainObj.problemRegionScopeArr});
		var probtype = new YAHOO.widget.DropdownCellEditor({multiple:false,  LABEL_SAVE:"Select", dropdownOptions:problemsMainObj.probTypesArr});
		 	
		var myColumnDefs = [ 
	            {key:"select", label: "<%=select%>", formatter:"checkbox"},
	            {key:"probId", hidden: true}, 
	            {key:"title", label: "<%=problemLabel%>", sortable:true, maxAutoWidth:150}, 
	            {key:"description", label: "<%=description%>", sortable:true, maxAutoWidth:250}, 
				{key:"identifiedDate", label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"existingFrom", label: "<%=existingFrom%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"location", label: "<%=location%>", sortable:true},
				{key:"probLocationId", hidden: true},
				{key:"hamletID", hidden: true},	
				{key:"source", label: "<%=source%>", sortable:true},
				{key:"scope", label: "<%=scope%>", sortable:true, editor: scopeCellEd},
				{key:"problemType", label: "<%=problemType%>", sortable:true, editor: probtype},
				{key:"probHistoryId", hidden: true}
	        ]; 
		
	        var myDataSource = new YAHOO.util.DataSource(problemsMainObj.newProblemsArr); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["probId","title","description","identifiedDate","existingFrom","location","probLocationId","hamletID","source","probHistoryId"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			newProbDataTable = new YAHOO.widget.DataTable("newProblemTabContentDiv_body", myColumnDefs, myDataSource,myConfigs); 
	                 
	        
			problemMgmtTabs.getTab(0).addListener("click", function() {myDataTable.onShow()});         
						
			var highlightEditableCell = function(oArgs) {   
             var elCell = oArgs.target;   
             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
                 this.highlightCell(elCell);   
             } 
			  }; 
			  newProbDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
			  newProbDataTable.subscribe("cellMouseoutEvent", newProbDataTable.onEventUnhighlightCell);
			  newProbDataTable.subscribe("cellClickEvent", newProbDataTable.onEventShowCellEditor);
			  newProbDataTable.subscribe("checkboxClickEvent", function(oArgs) { 
				  	    elCheckbox = oArgs.target; 
				  	    var newValue = elCheckbox.checked; 
				  	    var record = this.getRecord(elCheckbox); 
				  	    var column = this.getColumn(elCheckbox); 
				  	    record.setData(column.key,newValue);				  	
				  	    
				  	    if(record._oData.scope && record._oData.problemType)
				  	    {    
				  	    	var newIssAlertElmt = document.getElementById("NewIssuesAlertMessage"); 
				  	    	newIssAlertElmt.style.display = 'none'; 
							classifyButton.set("disabled", false);				  	  	
				  	  		if(newValue && hasRecordInArray(record))
				  	  		{
					  	  	recordsArray.push(record);					  	    
					  	  	problemMgmtTabs.getTab(1).set("disabled", true);					  	  	
				  	  		}
							else
							{
								deleteRecordFromArray(record);
								problemMgmtTabs.getTab(1).set("disabled", false);
							}
				  	    }
				  	    else
				  	    {	
				  	    	var newIssAlertElmt = document.getElementById("NewIssuesAlertMessage"); 
				  	    	newIssAlertElmt.style.display = 'block';
					  	    elCheckbox.checked = false;
					  	 }		  	  				  	  	
					  	});	
			  		  
		
			return { 
	            oDS: myDataSource, 
	            oDT: newProbDataTable 
	           
	      };	    
	}

	function deleteRecordFromArray(record)
	{
		for(i=0;i<recordsArray.length;i++)
		{	
			if(recordsArray[i]._sId == record._sId)
				recordsArray.splice(i,1);
		}	

		if(recordsArray.length == 0)
			classifyButton.set("disabled", true);	
	}
	function hasRecordInArray(record)
	{	
		var status = true;
		for(i=0;i<recordsArray.length;i++)
		{	
			if(recordsArray[i]._sId == record._sId)
				status=false;
		}
		return status;
	} 
	//wkg
	function classifyBtnHandler (e) 
	{	
		var emptyArray = new Array();
		for(var j in recordsArray)
		{
			newProbDataTable.deleteRow(recordsArray[j]);			
		}		
		var classifiedProbArray = new Array();
		
		for(var i in recordsArray)
		{					
			var probObj={
					problemId: recordsArray[i]._oData.probId,
					problem: recordsArray[i]._oData.title,
					description: recordsArray[i]._oData.description,
					hamletId: recordsArray[i]._oData.hamletID,
					location: recordsArray[i]._oData.location,
					locationId: recordsArray[i]._oData.probLocationId,
					reportedDate: recordsArray[i]._oData.identifiedDate,  
					existingFrom: recordsArray[i]._oData.existingFrom, 
					probSource: recordsArray[i]._oData.source,
					probScope: recordsArray[i]._oData.scope,
					probType: recordsArray[i]._oData.problemType,
					status: "2",
					probHistoryId: recordsArray[i]._oData.probHistoryId 				 				
				  };
			classifiedProbArray.push(probObj);		
		}
		var jsObj={
				prob:classifiedProbArray,
				task:"classifyProblem"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
	callAjax(rparam,jsObj,url);
	recordsArray = emptyArray;
	problemMgmtTabs.getTab(1).set("disabled", false);
	classifyButton.set("disabled", true );
	
				 	 
	}
		
	function buildClassifiedDataTable()
	{		
			var classifiedDataTableColumnDefs = [ 
	            {key:"select", label: "<%=select%>" ,formatter:"checkbox"}, 
	            {key:"title", label: "<%=problemLabel%>", sortable:true}, 
	            {key:"identifiedDate", label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"location", label: "<%=location%>", sortable:true},	
				{key:"scope", label: "<%=scope%>", sortable:true},
				{key:"problemType", label: "<%=problemType%>", sortable:true},
				{key:"department", label: "<%=department%>", sortable:true},
				{key:"problemHistoryId", hidden: true},
				{key:"departmentId", hidden: true}				
	        ];	 
	   	 
	        var classifiedDataTableDataSource = new YAHOO.util.DataSource(problemsMainObj.classifiedProblemsArr); 
	        classifiedDataTableDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        classifiedDataTableDataSource.responseSchema = { 
	            fields: ["title","identifiedDate","location","scope","problemType","problemHistoryId","department"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			classifiedDataTable = new YAHOO.widget.DataTable("classifiedTabContentDiv_body", classifiedDataTableColumnDefs, classifiedDataTableDataSource,myConfigs); 
	                 
	       problemMgmtTabs.getTab(1).addListener("click", function() {myDataTable.onShow()});     

	        var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  }; 
			
				  classifiedDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
				  classifiedDataTable.subscribe("cellMouseoutEvent", classifiedDataTable.onEventUnhighlightCell);			
				  classifiedDataTable.subscribe("buttonClickEvent",classifyDTEditButtonClickHandler); 

				  function classifyDTEditButtonClickHandler(oArgs){ 
					  classifyDTRecord = classifiedDataTable.getRecord(oArgs.target); 
					  
					   var jsObj={
								scope: classifyDTRecord._oData.scope,
								task: "departmentsByScope"
						};
					   var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
						var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
						callAjax(rparam,jsObj,url);					   
					   }	
				  classifiedDataTable.subscribe("checkboxClickEvent", function(oArgs) { 
						
				  	    elCheckboxInClassifyDT = oArgs.target; 
				  	    var newValue = elCheckboxInClassifyDT.checked; 
				  	    var clasifyDtRecord = this.getRecord(elCheckboxInClassifyDT); 
				  	    var clasifyDtColumn = this.getColumn(elCheckboxInClassifyDT); 
				  	 	clasifyDtRecord.setData(clasifyDtColumn.key,newValue);		
							
						if(clasifyDtRecord._oData.department == "<input type=button value=SelectDepartment id=showdepts>")
						{
						var classifiedIssAlertMsg =	document.getElementById("clsfdIssuesAlertMsg");	
						classifiedIssAlertMsg.style.display = 'block';
						elCheckboxInClassifyDT.checked = false;
									  	  	
						} else if(newValue && hasRecordInClasifyArray(clasifyDtRecord))
						{
							
							var classifiedIssAlertMsg =	document.getElementById("clsfdIssuesAlertMsg");	
							classifiedIssAlertMsg.style.display = 'none';
							clasifyDtRecordsArray.push(clasifyDtRecord);
							problemMgmtTabs.getTab(2).set("disabled", true);
							problemMgmtTabs.getTab(0).set("disabled", true);
							assignButton.set("disabled", false);							
						} else
						{
							deleteRecordFromClasifyArray(clasifyDtRecord);							
						}			  	  					  	  					  	  				  	  	
					  	});	
	 		
	        return { 
	            oDS: classifiedDataTableDataSource, 
	            oDT: classifiedDataTable 	            
	      }; 	     
	}
		
	function hasRecordInClasifyArray(clasifyDtRecord)
	{	
		var status = true;
		for(i=0;i<clasifyDtRecordsArray.length;i++)
		{	
			if(clasifyDtRecordsArray[i]._sId == clasifyDtRecord._sId)
				status=false;
		}
		return status;
	}	

	function deleteRecordFromClasifyArray(clasifyDtRecord)
	{
		for(i=0;i<clasifyDtRecordsArray.length;i++)
		{	
			if(clasifyDtRecordsArray[i]._sId == clasifyDtRecord._sId)
				clasifyDtRecordsArray.splice(i,1);
		}	

		if(clasifyDtRecordsArray.length == 0)
			assignButton.set("disabled", true);	
	}

	function showEditClassifyProbsDialog(results)
	{	
		var probConcernedDepts = results.probConcernedDepts;
		var ClasifyDTRow = classifyDTRecord._sId;
		var probTitle = classifyDTRecord._oData.title;		
		var elmt = document.getElementById('constituencyMgmtBodyDiv');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','editClassifiedProbsDiv');
		
		var editClsfyProbsContent = '';

		editClsfyProbsContent+='<div class="hd" align="left">Select Department</div>';
		editClsfyProbsContent+='<div class="bd" align="left">';
		editClsfyProbsContent+='<table>';
		editClsfyProbsContent+='<tr>';
		editClsfyProbsContent+='<td align="left">Assign Problem to a Department:</td>';
		editClsfyProbsContent+='<td style="padding-left: 15px;"><select id="deptList" name="deptList">';
		for(var i in probConcernedDepts)
		{
			editClsfyProbsContent+='<option value='+probConcernedDepts[i].id+'>'+probConcernedDepts[i].name+'</option>';	
		}
		editClsfyProbsContent+='</select>';
		editClsfyProbsContent+='</td>';
		editClsfyProbsContent+='</tr>';						
		editClsfyProbsContent+='</table>';
			
		divChild.innerHTML=editClsfyProbsContent;
		elmt.appendChild(divChild);	 
	
		if(EditClassifyProbsDialog)
			EditClassifyProbsDialog.destroy();
		EditClassifyProbsDialog = new YAHOO.widget.Dialog("editClassifiedProbsDiv",
				{ width : "400px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:600,
				  y:300,				  
				  buttons : [ { text:"Ok", handler: handleEditClassifyProbSubmit, isDefault:true}, 
	                          { text:"Cancel", handler: handleDialogCancel}]
	             } ); 
		EditClassifyProbsDialog.render();
	}	

	function handleEditClassifyProbSubmit()
	{
		var assignedDeptEl = document.getElementById("deptList");
		var assignedDeptVal = assignedDeptEl.options[assignedDeptEl.selectedIndex].value;
		var assignedDeptText = assignedDeptEl.options[assignedDeptEl.selectedIndex].text;
		var assignedDeptValue = assignedDeptEl.options[assignedDeptEl.selectedIndex].value;		
		classifiedDataTable.updateCell(classifyDTRecord , "department" , assignedDeptText );
		classifiedDataTable.updateCell(classifyDTRecord , "departmentId" , assignedDeptValue );				
		EditClassifyProbsDialog.hide();
	}
	//wkg
	function assignButtonHandler()
	{
		problemMgmtTabs.getTab(2).set("disabled", false);
		problemMgmtTabs.getTab(0).set("disabled", false);
		var emptyArray = new Array();
		var assignedProbArray = new Array();
		for(var i in clasifyDtRecordsArray)
		{
			classifiedDataTable.deleteRow(clasifyDtRecordsArray[i]);
		}
		for (var j in clasifyDtRecordsArray)
		{
			var classifiedProbObj ={
				title: clasifyDtRecordsArray[j]._oData.title, 
				identifiedDate: clasifyDtRecordsArray[j]._oData.identifiedDate,
				location: clasifyDtRecordsArray[j]._oData.location,
				scope: clasifyDtRecordsArray[j]._oData.scope,
				problemType: clasifyDtRecordsArray[j]._oData.problemType,
				problemHistoryId: clasifyDtRecordsArray[j]._oData.problemHistoryId,
				department: clasifyDtRecordsArray[j]._oData.department,
				status: "3"	,
				departmentId: clasifyDtRecordsArray[j]._oData.departmentId				 		 
					};
			assignedProbArray.push(classifiedProbObj);
		}
		var jsObj={
				assignedProblemsArray: assignedProbArray,
				task:"assignProblem"
				};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
		callAjax(rparam,jsObj,url);
		clasifyDtRecordsArray = emptyArray;
		//problemMgmtTabs.getTab(2).set("disabled", false);   
		assignButton.set("disabled", true);
		problemMgmtTabs.getTab(0).set("disabled", false);
	 	problemMgmtTabs.getTab(1).set("disabled", false);
	 	problemMgmtTabs.getTab(3).set("disabled", false);
				
	}

	function progressButtonClickHandler()
	{	
		
		var emptyArray = new Array();	
		var progressProbArray = new Array();
		for(var i in assignDtRecordsArray)
		{
			assignedIssDataTable.deleteRow(assignDtRecordsArray[i]);
		}
		for(var j in assignDtRecordsArray)
		{
			var assignedProbObj ={
					title: assignDtRecordsArray[j]._oData.title,
					concernedDepartment: assignDtRecordsArray[j]._oData.concernedDepartment,
					assignedOfficial: assignDtRecordsArray[j]._oData.assignedOfficial,
					name: assignDtRecordsArray[j]._oData.name,
					contactNumber: assignDtRecordsArray[j]._oData.contactNumber,
					comments: assignDtRecordsArray[j]._oData.comments,					 
					assignedProblemProgressId: assignDtRecordsArray[j]._oData.assignedProblemProgressId,
					status: "4"
					};
			progressProbArray.push(assignedProbObj);
		}
		var jsObj={
				progressProblemsArray: progressProbArray,
				task: "moveAssignedProblemsToProgress"
				};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
		callAjax(rparam,jsObj,url);
		assignDtRecordsArray = emptyArray;
		progressButton.set("disabled", true);
		problemMgmtTabs.getTab(0).set("disabled", false);
	 	problemMgmtTabs.getTab(1).set("disabled", false);
	 	problemMgmtTabs.getTab(3).set("disabled", false);		
	}
		
	function buildAssignedIssuesDataTable()
	{
			var assignedIssDataTableColumnDefs = [ 
	            {key:"select", label: "<%=select%>",formatter:"checkbox"}, 
	            {key:"title", label: "<%=problemLabel%>", sortable:true}, 
	            {key:"concernedDepartment", label:"<%=concernedDept%>", sortable:true},
				{key:"assignedOfficial" , label: "<%=assignedOfficial%>", editor: new YAHOO.widget.TextboxCellEditor(),sortable:true},	
				{key:"name", label: "<%=name%>", sortable:true, editor: new YAHOO.widget.TextboxCellEditor(),sortable:true},
				{key:"contactNumber", label: "<%=telephoneNo%>", editor: new YAHOO.widget.TextboxCellEditor()},
				{key:"comments", label: "<%=comments%>",editor: new YAHOO.widget.TextareaCellEditor({defaultValue:""})},
				{key:"assignedProblemProgressId", hidden: true}
				//{key:"departmentId", hidden: true}
				
				
	        ]; 
	 
			var assignedIssDataTableDataSource = new YAHOO.util.DataSource(problemsMainObj.assignedProblemsArr); 
			assignedIssDataTableDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			assignedIssDataTableDataSource.responseSchema = { 
	            fields: ["title","concernedDepartment","assignedProblemProgressId"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			assignedIssDataTable =  
	            new YAHOO.widget.DataTable("assignedIssuesTabContentDiv_body", assignedIssDataTableColumnDefs, assignedIssDataTableDataSource,myConfigs); 
	                 
	       
	       problemMgmtTabs.getTab(2).addListener("click", function() {assignedIssDataTable.onShow()});
	       var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  };  

				  assignedIssDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
				  assignedIssDataTable.subscribe("cellMouseoutEvent", assignedIssDataTable.onEventUnhighlightCell);
				  assignedIssDataTable.subscribe("cellClickEvent", assignedIssDataTable.onEventShowCellEditor);
				  assignedIssDataTable.subscribe("checkboxClickEvent", function(oArgs) {
					  	elCheckboxInAssignDT = oArgs.target; 
				  	    var newValue = elCheckboxInAssignDT.checked; 
				  	    var assignDtRecord = this.getRecord(elCheckboxInAssignDT); 
				  	    var assignDtColumn = this.getColumn(elCheckboxInAssignDT); 
				  	  	assignDtRecord.setData(assignDtColumn.key,newValue);
				  	  
				  	  	if(assignDtRecord._oData.assignedOfficial && assignDtRecord._oData.name && assignDtRecord._oData.contactNumber && assignDtRecord._oData.comments)
				  	  	{	
					  	  	var assignedIssAlertElmt = document.getElementById("assignedIssuesAlertMsg"); 
					  		assignedIssAlertElmt.style.display = 'none';  	
					  		
					  	 	if(newValue && hasRecordInAssignedArray(assignDtRecord))
					  		{	
					  	 		assignDtRecordsArray.push(assignDtRecord);
					  	 		problemMgmtTabs.getTab(0).set("disabled", true);
					  	 		problemMgmtTabs.getTab(1).set("disabled", true);
					  	 		problemMgmtTabs.getTab(3).set("disabled", true);
						  		
						  		progressButton.set("disabled", false );						  							  							  			  			
						  	}
					  		else
					  		{	
						  		deleteRecordFromAssignedArray(assignDtRecord);
						  		
					  		}
					  	}
					  	else
						{
					  		var assignedIssAlertElmt = document.getElementById("assignedIssuesAlertMsg"); 
					  		assignedIssAlertElmt.style.display = 'block';
					  		elCheckboxInAssignDT.checked = false;
						}				  	  
					  });       
	 
	        return { 
	            oDS: assignedIssDataTableDataSource, 
	            oDT: assignedIssDataTable	           
	      }; 
	   
	}
	
	function hasRecordInAssignedArray(assignDtRecord)
	{	
		var status = true;
		for(i=0;i<assignDtRecordsArray.length;i++)
		{	
			if(assignDtRecordsArray[i]._sId == assignDtRecord._sId)
				status=false;
		}
		return status;
	} 

	function deleteRecordFromAssignedArray(assignDtRecord)
	{
		for(i=0;i<assignDtRecordsArray.length;i++)
		{	
			if(assignDtRecordsArray[i]._sId == assignDtRecord._sId)
				assignDtRecordsArray.splice(i,1);
		}
		if(assignDtRecordsArray.length == 0)
			progressButton.set("disabled", true );
	}
	
	function buildProgressDataTable()
	{
			var progessIssuesColumnDefs = [ 
	            {key:"select", label: "<%=select%>",formatter:"checkbox"}, 
	            {key:"title", label: "<%=problemLabel%>", sortable:true}, 
	            {key:"identifiedDate", label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
	            {key:"concernedDepartment", label:"<%=concernedDept%>", sortable:true},
				{key:"assignedOfficial" , label: "<%=assignedOfficial%>", sortable:true},
				{key:"name", label: "<%=name%>", sortable:true},	
				{key:"contactNumber", label: "<%=telephoneNo%>"},
				{key:"assignedProblemProgressId", hidden: true},
				{key:"comments", label: "<%=comments%>",editor: new YAHOO.widget.TextareaCellEditor({defaultValue:""})}
				//{key:"fix" ,Fix}
	        ]; 
	 
	        var progessIssuesDataSource = new YAHOO.util.DataSource(problemsMainObj.progressedProblemsArr); 
	        progessIssuesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        progessIssuesDataSource.responseSchema = { 
	            fields: ["title","identifiedDate","concernedDepartment","assignedOfficial","name","contactNumber","assignedProblemProgressId","comments"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			progessIssuesDataTable = new YAHOO.widget.DataTable("progressTabContentDiv_body", progessIssuesColumnDefs, progessIssuesDataSource,myConfigs); 
	                 
	       
	       problemMgmtTabs.getTab(3).addListener("click", function() {progessIssuesDataTable.onShow()});         
	       var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  };  
		  progessIssuesDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
		  progessIssuesDataTable.subscribe("cellMouseoutEvent", progessIssuesDataTable.onEventUnhighlightCell);
		  progessIssuesDataTable.subscribe("cellClickEvent", progessIssuesDataTable.onEventShowCellEditor);
		  progessIssuesDataTable.subscribe("checkboxClickEvent", function(oArgs) {
			  elCheckboxInProgressDT = oArgs.target;
			  var newValue = elCheckboxInProgressDT.checked; 
		  	    var progressDtRecord = this.getRecord(elCheckboxInProgressDT); 
		  	    var progressDtColumn = this.getColumn(elCheckboxInProgressDT); 
		  	  	progressDtRecord.setData(progressDtColumn.key,newValue);
			  	if(progressDtRecord._oData.comments)
			  	{
					var progressedIssAlert = document.getElementById("progressedIssAlertMsg");
					progressedIssAlert.style.display = 'none'; 
					if(newValue && hasRecordInProgressedArray(progressDtRecord))
					{
						progressDtRecordsArray.push(progressDtRecord);
						problemMgmtTabs.getTab(0).set("disabled", true);		
						problemMgmtTabs.getTab(1).set("disabled", true);		
						problemMgmtTabs.getTab(2).set("disabled", true);
						problemMgmtTabs.getTab(4).set("disabled", true);
								
						pPendingButton.set("disabled", false);
						pFixedButton.set("disabled", false);						
					}else
			  		{	
				  		deleteRecordFromProgressedArray(progressDtRecord);
			  		}					
				} else
				{
			  		var progressedIssAlert = document.getElementById("progressedIssAlertMsg"); 
			  		progressedIssAlert.style.display = 'block';
			  		elCheckboxInProgressDT.checked = false;
				}			  
			  });
	 	   	
	        return { 
	            oDS: progessIssuesDataSource, 
	            oDT: progessIssuesDataTable	           
	      }; 
	   
	}

	function hasRecordInProgressedArray(progressDtRecord)
	{
		var status = true;
		for(i=0;i<progressDtRecordsArray.length;i++)
		{	
			if(progressDtRecordsArray[i]._sId == progressDtRecord._sId)
				status=false;
		}
		return status;
	}

	function deleteRecordFromProgressedArray(progressDtRecord)
	{
		for(i=0;i<progressDtRecordsArray.length;i++)
		{	
			if(progressDtRecordsArray[i]._sId == progressDtRecord._sId)
				progressDtRecordsArray.splice(i,1);
		}
		if(progressDtRecordsArray.length == 0)
			pFixedButton.set("disabled", true );
			pPendingButton.set("disabled", true );
	}

	function pPendingButtonClickHandler()
	{
		var currentTime = getTodayDateTime();
		
		var emptyArray = new Array();
		var pendingProbArray = new Array();
		for(var i in progressDtRecordsArray)
		{
			progessIssuesDataTable.deleteRow(progressDtRecordsArray[i]);
		}
		for(var j in progressDtRecordsArray)
		{
			
		
			var pendingProbObj = {
					title: progressDtRecordsArray[j]._oData.title,
					identifiedDate: progressDtRecordsArray[j]._oData.identifiedDate,
					concernedDepartment: progressDtRecordsArray[j]._oData.concernedDepartment,
					assignedOfficial: progressDtRecordsArray[j]._oData.assignedOfficial,
					name: progressDtRecordsArray[j]._oData.name,
					contactNumber: progressDtRecordsArray[j]._oData.contactNumber,
					assignedProblemProgressId: progressDtRecordsArray[j]._oData.assignedProblemProgressId,
					comments: progressDtRecordsArray[j]._oData.comments,
					status: "5",
					updatedDate: currentTime
					
					};
			pendingProbArray.push(pendingProbObj);			 
		}

		var jsObj={
				pendingProbArray: pendingProbArray,
				task: "moveProgressProblemsToPending"
				};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
		callAjax(rparam,jsObj,url);
		progressDtRecordsArray = emptyArray;
		pPendingButton.set("disabled", true);
		pFixedButton.set("disabled", true);
		problemMgmtTabs.getTab(0).set("disabled", false);		
		problemMgmtTabs.getTab(1).set("disabled", false);		
		problemMgmtTabs.getTab(2).set("disabled", false);
		problemMgmtTabs.getTab(4).set("disabled", false);
						
	}

	function pFixedButtonClickHandler()
	{
		var currentTime = getTodayDateTime();
		var emptyArray = new Array();
		var fixedProblemsArray = new Array();
		for(var i in progressDtRecordsArray)
		{
			progessIssuesDataTable.deleteRow(progressDtRecordsArray[i]);
		}
		for(var j in progressDtRecordsArray)
		{
			
			var fixedProbObj = {
					title: progressDtRecordsArray[j]._oData.title,
					identifiedDate: progressDtRecordsArray[j]._oData.identifiedDate,
					concernedDepartment: progressDtRecordsArray[j]._oData.concernedDepartment,
					assignedOfficial: progressDtRecordsArray[j]._oData.assignedOfficial,
					name: progressDtRecordsArray[j]._oData.name,
					contactNumber: progressDtRecordsArray[j]._oData.contactNumber,
					assignedProblemProgressId: progressDtRecordsArray[j]._oData.assignedProblemProgressId,
					comments: progressDtRecordsArray[j]._oData.comments,
					status: "6",
					updatedDate: currentTime
					};
			fixedProblemsArray.push(fixedProbObj);			 
		}

		var jsObj={
				fixedProblemsArray: fixedProblemsArray,
				task: "moveProgressProblemsToFixed"
				};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
		callAjax(rparam,jsObj,url);
		progressDtRecordsArray = emptyArray;
		pPendingButton.set("disabled", true);
		pFixedButton.set("disabled", true);	
		problemMgmtTabs.getTab(0).set("disabled", false);		
		problemMgmtTabs.getTab(1).set("disabled", false);		
		problemMgmtTabs.getTab(2).set("disabled", false);
		problemMgmtTabs.getTab(4).set("disabled", false);			

	}
	function buildPendingDataTable()
	{
			var pendingIssuesDataTableColumnDefs = [ 
	            {key:"select", label: "<%=select%>",formatter:"checkbox"}, 
	            {key:"title", label: "<%=problemLabel%>", sortable:true}, 
	            {key:"identifiedDate", label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
	            {key:"concernedDepartment", label:"<%=concernedDept%>", sortable:true},
				{key:"assignedOfficial" , label: "<%=assignedOfficial%>", sortable:true},
				{key:"name", label: "<%=name%>", sortable:true},	
				{key:"contactNumber", label: "<%=telephoneNo%>"},
				{key:"pendingFrom", label: "<%=pendingFrom%>"},				
				{key:"reason", label: "<%=reason%>"},
				{key:"comments", label: "<%=comments%>",editor: new YAHOO.widget.TextareaCellEditor({defaultValue:""})},
				{key:"assignedProblemProgressId", hidden: true}				
	        ]; 
	 
	        var pendingIssuesDataTableDataSource = new YAHOO.util.DataSource(problemsMainObj.pendingProblemArr); 
	        pendingIssuesDataTableDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        pendingIssuesDataTableDataSource.responseSchema = { 
	            fields: ["title","identifiedDate","concernedDepartment","assignedOfficial","name","contactNumber","pendingFrom","reason","assignedProblemProgressId"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			pendingIssuesDataTable = new YAHOO.widget.DataTable("pendingTabContentDiv_body", pendingIssuesDataTableColumnDefs, pendingIssuesDataTableDataSource,myConfigs); 
	                 
	       
	       problemMgmtTabs.getTab(3).addListener("click", function() {pendingIssuesDataTable.onShow()});
	       var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  };  
		  pendingIssuesDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);  			
		  pendingIssuesDataTable.subscribe("cellMouseoutEvent", pendingIssuesDataTable.onEventUnhighlightCell);
		  pendingIssuesDataTable.subscribe("cellClickEvent", pendingIssuesDataTable.onEventShowCellEditor);
		  pendingIssuesDataTable.subscribe("checkboxClickEvent", function(oArgs) {
			    elCheckboxInPendingDT = oArgs.target;
			  	var newValue = elCheckboxInPendingDT.checked; 
		  	    var pendingDtRecord = this.getRecord(elCheckboxInPendingDT); 
		  	    var pendingDtColumn = this.getColumn(elCheckboxInPendingDT); 
		  	  pendingDtRecord.setData(pendingDtColumn.key,newValue);
			  	if(pendingDtRecord._oData.comments)
			  	{
					var pendingIssAlert = document.getElementById("pendingIssAlertMsg");
					pendingIssAlert.style.display = 'none'; 
					if(newValue && hasRecordInPendingArray(pendingDtRecord))
					{
						pendingDtRecordsArray.push(pendingDtRecord);
						progressButtonInPending.set("disabled", false);
						problemMgmtTabs.getTab(0).set("disabled", true);
						problemMgmtTabs.getTab(1).set("disabled", true);
						problemMgmtTabs.getTab(2).set("disabled", true);
						problemMgmtTabs.getTab(3).set("disabled", true);
						problemMgmtTabs.getTab(5).set("disabled", true);
												
					}else
			  		{	
				  		deleteRecordFromPendingArray(pendingDtRecord);
			  		}					
				} else
				{
			  		var pendingIssAlert = document.getElementById("pendingIssAlertMsg"); 
			  		pendingIssAlert.style.display = 'block';
			  		elCheckboxInPendingDT.checked = false;
				}			  
			  });         
	 	 	
	        return { 
	            oDS: pendingIssuesDataTableDataSource, 
	            oDT: pendingIssuesDataTable	           
	      }; 
	   
	}

	 function hasRecordInPendingArray(pendingDtRecord)
		{
			var status = true;
			for(i=0;i<pendingDtRecordsArray.length;i++)
			{	
				if(pendingDtRecordsArray[i]._sId == pendingDtRecord._sId)
					status=false;
			}
			return status;
		}

		function deleteRecordFromPendingArray(pendingDtRecord)
		{
			for(i=0;i<pendingDtRecordsArray.length;i++)
			{	
				if(pendingDtRecordsArray[i]._sId == pendingDtRecord._sId)
				pendingDtRecordsArray.splice(i,1);
			}
			if(pendingDtRecordsArray.length == 0)
				progressButtonInPending.set("disabled", true );
				
		}

		function progressButtonInPendingClickHandler()
		{
			var currentTime = getTodayDateTime();
			var emptyArray = new Array();
			var progressedProblemsArray = new Array();
			for(var i in  pendingDtRecordsArray)
			{
				pendingIssuesDataTable.deleteRow( pendingDtRecordsArray[i]);
			}
			for(var j in  pendingDtRecordsArray)
			{
				
				var progressedProbObj = {
						title:  pendingDtRecordsArray[j]._oData.title,
						identifiedDate:  pendingDtRecordsArray[j]._oData.identifiedDate,
						concernedDepartment:  pendingDtRecordsArray[j]._oData.concernedDepartment,
						assignedOfficial:  pendingDtRecordsArray[j]._oData.assignedOfficial,
						name:  pendingDtRecordsArray[j]._oData.name,
						contactNumber:  pendingDtRecordsArray[j]._oData.contactNumber,
						assignedProblemProgressId:  pendingDtRecordsArray[j]._oData.assignedProblemProgressId,
						comments:  pendingDtRecordsArray[j]._oData.comments,
						status: "4",
						updatedDate: currentTime
						};
				progressedProblemsArray.push(progressedProbObj);			 
			}

			var jsObj={
					progressedProblemsArray: progressedProblemsArray,
					task: "movePendingProblemsToProgress"
					};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
			callAjax(rparam,jsObj,url);
			pendingDtRecordsArray = emptyArray;
			progressButtonInPending.set("disabled", true);
			problemMgmtTabs.getTab(0).set("disabled", false);
			problemMgmtTabs.getTab(1).set("disabled", false);
			problemMgmtTabs.getTab(2).set("disabled", false);
			problemMgmtTabs.getTab(3).set("disabled", false);
			problemMgmtTabs.getTab(5).set("disabled", false);
			
							
				
		}

	function buildFixedDataTable()
	{
			var fixedIssuesDataTableColumnDefs = [ 
	           
	            {key:"title", label: "<%=problemLabel%>", sortable:true}, 
	            {key:"identifiedDate", label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
	            {key:"concernedDepartment", label:"<%=concernedDept%>", sortable:true},
	            {key:"fixedDate", label: "<%=fixedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
	            {key:"comments", label:"<%=reason%>",editor: new YAHOO.widget.TextareaCellEditor({defaultValue:""})},
	            {key:"assignedProblemProgressId", hidden: true}
	            				
	        ]; 
	 
	        var fixedIssuesDataTableDataSource = new YAHOO.util.DataSource(problemsMainObj.fixedProblemsArr); 
	        fixedIssuesDataTableDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        fixedIssuesDataTableDataSource.responseSchema = { 
	            fields: ["title","identifiedDate","concernedDepartment","fixedDate","comments","assignedProblemProgressId"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 10 
		    }) 
			}; 

			fixedIssuesDataTable =new YAHOO.widget.DataTable("fixedIssuesContentDiv_body", fixedIssuesDataTableColumnDefs, fixedIssuesDataTableDataSource,myConfigs); 
	                 
	       
	       problemMgmtTabs.getTab(3).addListener("click", function() {fixedIssuesDataTable.onShow()});
	       var highlightEditableCell = function(oArgs) {   
	             var elCell = oArgs.target;   
	             if(YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {   
	                 this.highlightCell(elCell);   
	             } 
				  };	                
	 
	        return { 
	            oDS: fixedIssuesDataTableDataSource, 
	            oDT: fixedIssuesDataTable	           
	      }; 
	   
	}
	
	function buildLocalLeadersDataTable()
	{
		var localLeadersColumnDefs = [ 
		    	             
		    	            {key:"name", label: "<%=name%>", sortable:true}, 
		    	            {key:"occupation", label: "<%=occupation%>", sortable:true}, 
		    				{key:"position", label: "<%=position%>", sortable:true},
		    				{key:"influenceScope", label: "<%=inflScope%>", sortable:true},	
		    				{key:"contactNumber", label: "<%=contactnbr%>"}
		    				
		    				
		    	        ]; 
		var localLeadersDataSource = new YAHOO.util.DataSource(constMgmtMainObj.localLeadersArray); 
		localLeadersDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		localLeadersDataSource.responseSchema = { 
            fields: ["name","occupation","position","influenceScope","contactNumber"] 
        };

        
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				};

		var localLeadersDataTable =  new YAHOO.widget.DataTable("localLeadersTabContent", localLeadersColumnDefs, localLeadersDataSource, myConfigs);
		constMgmtTabs.getTab(0).addListener("click", function() {localLeadersDataTable.onShow()});

			return {
				oDS: localLeadersDataSource,
				oDT: localLeadersDataTable
			};
	}

	function buildLocalProblemsDataTable()
	{
		var localProbColumnDefs = [ 
		    	            {key:"sNo", label: "<%=sNo%>", formatter:"number", sortable:true}, 
		    	            {key:"description", label: "<%=description%>", sortable:true},
		    				{key:"identifiedDate", label: "<%=identifiedDate%>",formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
		    				{key:"source", label: "<%=scope%>", sortable:true},
		    				{key:"status", label: "<%=status%>", sortable:true}
		    						    				
		    	        ]; 
		var localProbDataSource = new YAHOO.util.DataSource(constMgmtMainObj.localProblemsArr); 
		localProbDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		localProbDataSource.responseSchema = { 
            fields: [{key:"sNo", parser:"number"}, "description", "identifiedDate", "source", "status"] 
        };

        
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				};

		var localProbDataTable =  new YAHOO.widget.DataTable("localProblemsTabContent", localProbColumnDefs, localProbDataSource, myConfigs);
		constMgmtTabs.getTab(1).addListener("click", function() {localProbDataTable.onShow()});

			return {
				oDS: localProbDataSource,
				oDT: localProbDataTable
			};
	} 
	
	function buildLocalCastStatisticsDataTable()
	{
		var localCastStatsColumnDefs = [ 
		    	            
		    	            {key:"caste", label: "<%=cast%>", sortable: true}, 
		    	           	{key:"castePopulation", label: "<%=castPopulation%>", formatter:"number", sortable: true},
		    				{key:"castePercentage", label: "<%=castPercentage%>", formatter:YAHOO.widget.DataTable.formatFloat, sortable:true}	
		    					    			    				
		    	        ]; 
		var localCastStatsDataSource = new YAHOO.util.DataSource(constMgmtMainObj.castStatsArray); 
		localCastStatsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		localCastStatsDataSource.responseSchema = { 
            fields: ["caste", {key: "castePopulation", parser:"number"},{key: "castePercentage", parser:YAHOO.util.DataSourceBase.parseNumber}] 
        };

        
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				};

		var localCastStatsDataTable =  new YAHOO.widget.DataTable("localCastStatsTabContent_body", localCastStatsColumnDefs, localCastStatsDataSource, myConfigs);
		constMgmtTabs.getTab(2).addListener("click", function() {localCastStatsDataTable.onShow()});

			return {
				oDS: localCastStatsDataSource,
				oDT: localCastStatsDataTable
			};
	} 

	function buildLocalPoliticalChangesDataTable()
	{

		var localPolChangesColumnDefs = [
		                                 //{key: "sNo", label: "SNo", formatter:"number", sortable:true},
		                                 {key: "description", label: "<%=description%>", sortable:true},
		                                 {key: "date", label: "<%=date%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
		                                 {key: "impact", label: "<%=impact%>", sortable:true}

		                                 ];

		var localPolChangesDataSource = new YAHOO.util.DataSource(constMgmtMainObj.localPoliticalChangesArray);
		localPolChangesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		localPolChangesDataSource.responseSchema = { 
	            fields: [
	     	            //{key:"sNo", parser:"number"}, 
	     	            "description", "date", "impact"]
		};

		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				};
		
		var localPoliticalChangesDataTable = new YAHOO.widget.DataTable("localPoliticalChangesTabContent", localPolChangesColumnDefs, localPolChangesDataSource, myConfigs);
		constMgmtTabs.getTab(3).addListener("click", function() {localPoliticalChangesDataTable.onShow()});

		return {
			oDS: localPolChangesDataSource,
			oDT: localPoliticalChangesDataTable
		};
	}
	
	function buildVotersByLocBoothDataTable()
	{
		var votersByLocBoothColumnDefs = [ 
		    	            {key:"sNo", label: "<%=sNo%>", formatter:"number", sortable:true}, 
		    	            {key:"name", label: "<%=name%>", sortable: true}, 
		    	            {key:"gender", label: "<%=gender%>", sortable: true},
		    				{key:"age", label: "<%=age%>", sortable:true},
		    				{key:"hNo", label: "<%=hNo%>", sortable:true},
		    				{key:"guardianName", label: "<%=guardName%>", sortable:true},
		    				{key:"relationship", label: "<%=relationship%>", sortable:true},	
		    				{key:"cast", label: "<%=cast%>", sortable:true},
		    				{key:"castCategory", label: "<%=castCategory%>", sortable:true}
		    					
		    					    			    				
		    	        ]; 
		var votersByLocBoothDataSource = new YAHOO.util.DataSource(constMgmtMainObj.votersArray); 
		votersByLocBoothDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		votersByLocBoothDataSource.responseSchema = { 
            fields: [
                     {key:"sNo", parser:"number"}, 
                     "name", "gender", "age", "hNo","guardianName","relationship","cast","castCategory"] 
        };

        
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				};

		var votersByLocBoothDataTable =  new YAHOO.widget.DataTable("votersByLocationTabContentDiv_body", votersByLocBoothColumnDefs, votersByLocBoothDataSource, myConfigs);
		constMgmtTabs.getTab(4).addListener("click", function() {votersByLocBoothDataTable.onShow()});

			return {
				oDS: votersByLocBoothDataSource,
				oDT: votersByLocBoothDataTable
			};
	}

	function buildImportantVotersDataTable()
	{
		var myColumnDefs = [ 
		    	            {key:"sNo", label:"<%=sNo%>", formatter:"number", sortable:true}, 
		    	            {key:"houseNo", label:"<%=hNo%>", sortable: true}, 
		    	            {key:"membersInFamily", label:"<%=mbrsInFamily%>", sortable: true},
		    				{key:"eldestPersonName", label:"<%=eldstPersonName%>", sortable:true},
		    				{key:"youngestPersonName", label:"<%=ygstPersonName%>", sortable:true},
		    				{key:"cast", label:"<%=cast%>", sortable:true}
		    				//{key:"Mobile"}
		    				  					    			    				
		    	        ]; 
		var myDataSource = new YAHOO.util.DataSource(constMgmtMainObj.votersByHouseNoArray); 
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
        myDataSource.responseSchema = { 
            fields: [
                     
                         {key:"sNo", parser:"number"}, 
                         "houseNo", "membersInFamily", "eldestPersonName", "youngestPersonName", "cast"] 
        };

        
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 15 
			    }) 
				};

		var myDataTable =  new YAHOO.widget.DataTable("importantVotersTabContent_body", myColumnDefs, myDataSource, myConfigs);
		constMgmtTabs.getTab(5).addListener("click", function() {myDataTable.onShow()});

			return {
				oDS: myDataSource,
				oDT: myDataTable
			};
	}

	function showDateCal(id)
	{
		if(dateCalendar)
			dateCalendar.destroy();
		
		var navConfig = { 
	      strings : { 
	          month: "Choose Month", 
	          year: "Enter Year", 
	          submit: "OK", 
	          cancel: "Cancel", 
	          invalidYear: "Please enter a valid year" 
	      }, 
	      monthFormat: YAHOO.widget.Calendar.SHORT, 
	      initialFocus: "year" 
	}; 

		var dateCalendar = new YAHOO.widget.Calendar(id, {navigator:navConfig, title:"Choose a date:", close:true }); 
		dateCalendar.selectEvent.subscribe(displayDateText, dateCalendar, true); 		
		dateCalendar.render(); 
		dateCalendar.show();	
	}
	function displayDateText(type,args,obj)
	{			
		var dates = args[0]; 
		var date = dates[0]; 
		var year = date[0], month = date[1], day = date[2]; 

		var txtDate1 = document.getElementById("existingFromText"); 
		txtDate1.value = day + "/" + month + "/" + year; 
		

	}
	function getPersonDetails(value)
	{
		
		var elmt = document.getElementById("personDetailsDiv");
		if(!elmt)
			alert("No div present to display personal details");
		if(value != 1) 
		{		
			elmt.style.display = 'block';
		}
		else
		{	
			elmt.style.display = 'none';
		}
	}
	
	function buildAddNewProblemPopup()
	{
		var elmt = document.getElementById('constituencyMgmtBodyDiv');
		var m_names = new Array("January", "February", "March", 
				"April", "May", "June", "July", "August", "September", 
				"October", "November", "December");


				var d = new Date();
				var curr_date = d.getDate();
				var curr_month = d.getMonth();
				var curr_year = d.getFullYear();
				
				var todayDate=new Date().getDate()+"/"+(new Date().getMonth()+1)+"/"+new Date().getFullYear();
					
		var divChild = document.createElement('div');
		divChild.setAttribute('id','addNewProblemDiv');
		divChild.setAttribute('class','newProbdialog');

		var contentStr=''; 
		
		contentStr+='<div class="hd" align="left">Add New Problem</div>';
		contentStr+='<div class="bd" align="left">';
		contentStr+='<div id="problemDetailsDivBody">';
		contentStr+='<table>';
		contentStr+='<tr>';
		contentStr+='<th align="left" colspan="3"><u>Problem Details</u></th>';
		contentStr+='</tr>';
		contentStr+='<tr></tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=problemLabel%></td>';
		contentStr+='<td style="padding-left: 15px;"><input type="text" size="53" id="problemText" name="problemText"/></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=description%></td>';
		contentStr+='<td style="padding-left: 15px;"><textarea cols="50" id="descTextArea" name="descTextArea"></textarea></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=STATE%></td>';
		contentStr+='<td style="padding-left: 15px;"><select id="pstateField" name="state" onchange="getnextList(this.name,this.options[this.selectedIndex].value,\'addProblem\')">';
		for(var i in locationDetails.stateArr)
		{
			contentStr+='<option value='+locationDetails.stateArr[i].id+'>'+locationDetails.stateArr[i].value+'</option>';
		}
		contentStr+='</select></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=DISTRICT%></td>';
		contentStr+='<td style="padding-left: 15px;"><select id="pdistrictField" class="selectWidth" name="district"  onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,\'addProblem\')">';
		for(var i in locationDetails.districtArr)
		{
			contentStr+='<option value='+locationDetails.districtArr[i].id+'>'+locationDetails.districtArr[i].value+'</option>';
		}
		contentStr+='</select></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=CONSTITUENCY%></td>';
		contentStr+='<td style="padding-left: 15px;"><select id="pconstituencyField" class="selectWidth" name="constituency"  onchange="getMandalList(this.name,this.options[this.selectedIndex].value,\'addProblem\')">';
		for(var i in locationDetails.constituencyArr)
		{
			contentStr+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
		}
		contentStr+='</select></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=MANDAL%></td>';
		contentStr+='<td style="padding-left: 15px;"><select id="pmandalField" class="selectWidth" name="mandal" onchange="getTownshipsForMandal(this.name,this.options[this.selectedIndex].value,\'addProblem\')">';
		for(var i in locationDetails.mandalArr)
		{
			contentStr+='<option value='+locationDetails.mandalArr[i].id+'>'+locationDetails.mandalArr[i].value+'</option>';
		}
		contentStr+='</select></td>';
		contentStr+='</tr>';
		
		contentStr+='<tr>';
		contentStr+='<td><%=VILLAGE%></td>';
		contentStr+='<td style="padding-left: 15px;"><select class="selectWidth" id="pvillageField" name="village" onchange="getnextList(this.name,this.options[this.selectedIndex].value,\'addProblem\')">';
		for(var i in locationDetails.villageArr)
		{
			contentStr+='<option value='+locationDetails.villageArr[i].id+'>'+locationDetails.villageArr[i].value+'</option>';
		}
		contentStr+='</select></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=HAMLET%></td>';
		contentStr+='<td style="padding-left: 15px;"><select class="selectWidth" id="phamletField" name="hamlet">';
		for(var i in locationDetails.hamletArr)
		{
			contentStr+='<option value='+locationDetails.hamletArr[i].id+'>'+locationDetails.hamletArr[i].value+'</option>';
		}
		contentStr+='</select></td>';
		contentStr+='</tr>';		
		contentStr+='<tr>';
		contentStr+='<td><%=reportedDate%></td>';
		contentStr+='<td style="padding-left: 15px;"><input type="text" value="'+todayDate+'" size="53" id="reportedDateText" name="reportedDateText"/></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=existingFrom%></td>';
		contentStr+='<td style="padding-left: 15px;">';
		contentStr+='<div><input type="text" id="existingFromText" name="existingFromText" size="53" onfocus="showDateCal(\'existingFromText_Div\')"/></div>';
		contentStr+='<div id="existingFromText_Div" class="tinyDateCal"></div>';
		contentStr+='</td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=problemSource%></td>';
		contentStr+='<td style="padding-left: 15px;"><select id="problemSource" class="selectWidth" name="problemSource" onchange="getPersonDetails(this.options[this.selectedIndex].value)">';
		for(var i in problemsMainObj.problemSourcesArr)
		{
			contentStr+='<option value='+problemsMainObj.problemSourcesArr[i].id+'>'+problemsMainObj.problemSourcesArr[i].value+'</option>';
		}
		contentStr+='</td>';	
		contentStr+='</tr>';
		contentStr+='</table>';
		contentStr+='<div id="personDetailsDiv" style="display: none;">';
		contentStr+='<table>';
		contentStr+='<tr>';
		contentStr+='<th align="left" colspan="2"><u>Complained Person Details</u></th>';
		contentStr+='</tr>';
		contentStr+='<tr></tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=name%></td>';
		contentStr+='<td style="padding-left: 15px;"><input type="text" size="53" id="nameText" name="problemText"/></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=mobile%></td>';
		contentStr+='<td style="padding-left: 15px;"><input type="text" size="53" id="mobileText" name="mobileText"/></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=telephoneNo%></td>';
		contentStr+='<td style="padding-left:15px;"><input type="text" size="53" id="telePhoneText" name="telePhoneText"/></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td><%=email%></td>';
		contentStr+='<td style="padding-left:15px;"><input type="text" size="53" id="emailText" name="emailText"/></td>';
		contentStr+='</tr>';
		contentStr+='<tr>';
		contentStr+='<td width="100px;"><%=address%></td>';
		contentStr+='<td style="padding-left:15px;"><input type="text" size="53" id="addressText" name="addressText"/></td>';
		contentStr+='</tr>';
		contentStr+='</table>';
		contentStr+='</div>';
		contentStr+='</div>';
		contentStr+='</div>';
		divChild.innerHTML=contentStr;
		elmt.appendChild(divChild);	 
	
		if(newProblemDialog)
			newProblemDialog.destroy();
		newProblemDialog = new YAHOO.widget.Dialog("addNewProblemDiv",
				{ width : "600px", 
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:400,
				  y:300,				  
				  buttons : [ { text:"Add Problem", handler: handleNewProbSubmit, isDefault:true}, 
	                          { text:"Cancel", handler: handleDialogCancel}]
	             } ); 
		newProblemDialog.render();		
	}
		
	function handleNewProbSubmit()
	{
		
		var problemVal = document.getElementById("problemText").value;
		var descriptionVal = document.getElementById("descTextArea").value;
		var stateEl = document.getElementById("pstateField");
		var	stateVal = stateEl.options[stateEl.selectedIndex].value;
		var districtEl = document.getElementById("pdistrictField");
		var	districtVal = districtEl.options[districtEl.selectedIndex].value; 
		var constEl	= document.getElementById("pconstituencyField");
		var	constVal = constEl.options[constEl.selectedIndex].value;
		var tehsilEl = document.getElementById("pmandalField");
		var	tehsilVal = tehsilEl.options[tehsilEl.selectedIndex].value;	
		var villageEl = document.getElementById("pvillageField");
		var	villageVal = villageEl.options[villageEl.selectedIndex].value; 
		var hmletEl = document.getElementById("phamletField");
		var	hmletVal = hmletEl.options[hmletEl.selectedIndex].value; 
		var reportedDateVal = document.getElementById("reportedDateText").value;
		var existingFromVal = document.getElementById("existingFromText").value;
		var problemSourceEl = document.getElementById("problemSource");
		var	problemSourceVal = problemSourceEl.options[problemSourceEl.selectedIndex].value; 
		
			if(problemSourceVal != 1)
		{
			var nameVal = document.getElementById("nameText").value;
			var mobileVal = document.getElementById("mobileText").value;
			var telePhoneVal = document.getElementById("telePhoneText").value;
			var emailVal = document.getElementById("emailText").value;
			var addressVal = document.getElementById("addressText").value;			
		} else {
			var nameVal = "";
			var mobileVal = "";
			var telePhoneVal = "";
			var emailVal = "";
			var addressVal = "";
			}	
		
		var jsObj={
				problem: problemVal,
				description: descriptionVal,
				state: stateVal,
				district: districtVal,
				constituency: constVal ,
				tehsil: tehsilVal, 
				village: villageVal,
				hamlet: hmletVal,
				reportedDate: reportedDateVal,  
				existingFrom: existingFromVal, 
				name: nameVal, 
				email: emailVal,
				phone: telePhoneVal, 
				mobile: mobileVal, 
				address: addressVal,
				probSource: problemSourceVal,
				status:"1",	
				task:"addNewProblem"
			  }
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/problemManagementAction.action?"+rparam;		
	callAjax(rparam,jsObj,url);
	newProblemDialog.hide();
	
	}

	function handleDialogCancel()
	{
		this.cancel();
	}
</script>
</head>
<body>

<div id="constituencyMgmtHeaderDiv">Constituency Management</div>

<div id="constituencyMgmtMainDiv">	
	<div id="alertMessage" style="display: none;"><%=constMgmtAlertMessage%></div>
	<div id="constituencyMgmtBodyDiv" class="yui-skin-sam"></div>
	<div id="statisticalDataMainDiv">
		<div id="statisticalDataHeadDiv"> Statistical Data </div>
		<div id="statisticalDataBodyDiv"> Statistical Data Content</div>
	</div>
	<div id="problemMgmtMainDiv"></div>
</div>



<script type="text/javascript">

<c:forEach var="state"  items="${stateList}" >
	var ob={
				id:'${state.id}',
				value:'${state.name}'
			};
	locationDetails.stateArr.push(ob);	
</c:forEach>

<c:forEach var="district"  items="${districtList}" >
	var ob={
				id:'${district.id}',
				value:'${district.name}'
			};
	locationDetails.districtArr.push(ob);	
</c:forEach>

<c:forEach var="constituency"  items="${constituencyList}" >
	var ob={
				id:'${constituency.id}',
				value:'${constituency.name}'
			};
	locationDetails.constituencyArr.push(ob);	
</c:forEach>

<c:forEach var="mandal"  items="${mandalList}" >
	var ob={
				id:'${mandal.id}',
				value:'${mandal.name}'
			};
	locationDetails.mandalArr.push(ob);	
</c:forEach>

<c:forEach var="village"  items="${villageList}" >
	var ob={
				id:'${village.id}',
				value:'${village.name}'
			};
	locationDetails.villageArr.push(ob);	
</c:forEach>

<c:forEach var="hamlet"  items="${hamletList}" >
	var ob={
				id:'${hamlet.id}',
				value:'${hamlet.name}'
			};
	locationDetails.hamletArr.push(ob);	
</c:forEach>
<c:forEach var="probSources"  items="${problemSources}" >
var ob={
			id:'${probSources.id}',
			value:'${probSources.name}'
		};
problemsMainObj.problemSourcesArr.push(ob);	
</c:forEach>
getTodayDateTime();
buildConstituencyLayout();
buildOuterTabView();
buildProblemMgmtTabView();
buildConstMgmtTabView();
buildClassifiedDataTable();
buildAssignedIssuesDataTable();
buildProgressDataTable();
buildPendingDataTable();
buildFixedDataTable();
</script>
</body>
</html>