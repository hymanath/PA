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
	<!--
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/connection/connection-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/dragdrop/dragdrop-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/button/button-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/container/container-min.js"></script> 
	-->
	
	
	 
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
		#newProblemTabContentDiv_footer,#newProblemTabContentDiv_head,#classifiedTabContentDiv_footer,#assignedIssuesTabContentDiv_footer
		{
			margin-right:20px;
			text-align:right;
			padding:5px;
		}
		#problemMgmtMainDiv
		{
			height:1000px;
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
		  %> }
	
	var outerTab,problemMgmtTabs;
	var newProblemDialog;
	var problemsMainObj={
							problemSourcesArr:[],	
							newProblemsArr:[],
							categorizedProblemsArr:[],
							assignedProblemsArr:[],
							fixedProblemsArr:[]
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
							localProblemsArr:[]						
							
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

		var localLeaders = results.localLeaders;
		var voters = results.voterDetails;
		var cast = results.voterCastInfodetails.castVOs;
		var totalVoters = results.voterCastInfodetails.totalVoters;
		var maleVoters = results.voterCastInfodetails.maleVoters;
		var femaleVoters = results.voterCastInfodetails.femaleVoters;
		var votersByHouseNo = results.votersByHouseNos;
		var politicalChanges = results.politicalChanges;
		var localProblems = results.hamletProblems;
		var count=0;
		var votersByHouseNoCount = 0;
		var localProbCount = 0;
		
		
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
				//buildLocalLeadersDataTable();
			} if(localProblems.length == 0)
			{
				constMgmtMainObj.localProblemsArr = emptyArr;
				//buildLocalProblemsDataTable();	
			} if(cast.length == 0)
			{
				constMgmtMainObj.castStatsArray = emptyArr;
				//buildLocalCastStatisticsDataTable();
			} if(politicalChanges.length == 0)
			{
				constMgmtMainObj.localPoliticalChangesArray = emptyArr;
				//buildLocalPoliticalChangesDataTable();
			} if (voters.length == 0)
			{	
				constMgmtMainObj.votersArray = emptyArr;
				//buildVotersByLocBoothDataTable();
			} if(votersByHouseNo.length == 0)
			{
				constMgmtMainObj.votersByHouseNoArray = emptyArr;
				//buildImportantVotersDataTable();
			} 
			buildLocalPoliticalChangesDataTable();
			buildLocalLeadersDataTable();
			buildLocalCastStatisticsDataTable();			
			buildVotersByLocBoothDataTable();
			buildImportantVotersDataTable();
			buildLocalProblemsDataTable();			
			
		constMgmtTabs.getTab(0).set("disabled", false);
		constMgmtTabs.getTab(0).set("active", true);
		constMgmtTabs.getTab(1).set("active", false);
		constMgmtTabs.getTab(2).set("active", false);
		constMgmtTabs.getTab(3).set("active", false);
		constMgmtTabs.getTab(4).set("active", false);
		constMgmtTabs.getTab(5).set("active", false);
		constMgmtTabs.getTab(1).set("disabled", false);
		constMgmtTabs.getTab(2).set("disabled", false);
		constMgmtTabs.getTab(3).set("disabled", false);
		constMgmtTabs.getTab(4).set("disabled", false);
		constMgmtTabs.getTab(5).set("disabled", false);				
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
		ajaxImgSpanElmt.style.display = 'block';
		var jsObj=
			{
					selectName:name,
					selected:value,
					task:"findVoters"
			}
		
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
		var url = "<%=request.getContextPath()%>/voterInfoAction.action?"+rparam;
		callAjax(rparam,jsObj,url);
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
	}
	function buildProblemMgmtTabView()
	{	
		problemMgmtTabs = new YAHOO.widget.TabView(); 
		
		var newTabContent='<div id="newProblemTabContentDiv">';
		newTabContent+='<div id="newProblemTabContentDiv_head"></div>';
		newTabContent+='<div id="newProblemTabContentDiv_body"></div>';
		newTabContent+='<div id="newProblemTabContentDiv_footer" align="right"></div>';
		newTabContent+='</div>';

		var classifiedTabContent='<div id="classifiedTabContentDiv">';
		classifiedTabContent+='<div id="classifiedTabContentDiv_head"></div>' ;
		classifiedTabContent+='<div id="classifiedTabContentDiv_body"></div>';
		classifiedTabContent+='<div id="classifiedTabContentDiv_footer"></div>';
		classifiedTabContent+='</div>';
		
		var assignedIssuesTabContent='<div id="assignedIssuesTabContentDiv">';
		assignedIssuesTabContent+='<div id="assignedIssuesTabContentDiv_head"></div>';
		assignedIssuesTabContent+='<div id="assignedIssuesTabContentDiv_body"></div>';
		assignedIssuesTabContent+='<div id="assignedIssuesTabContentDiv_footer"></div>';
		assignedIssuesTabContent+='</div>';				
				
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
		content: '<div id="progressContentDiv"></div>' 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
		label: '<%=pendingIssues%>', 
		content: '<div id="pendingIssuesContentDiv"></div>' 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
		label: '<%=fixedIssues%>', 
		content: '<div id="fixedIssuesContentDiv"></div>' 
		})); 

		problemMgmtTabs.appendTo('problemMgmtTabContentDiv'); 
	      
			var oButton = new YAHOO.widget.Button({ 
	                                            id: "mybuttonid",  
	                                            type: "button",  
	                                            label: "<%=classify%>",  
	                                            container: "newProblemTabContentDiv_footer"  
	                                        }); 

			var oButton = new YAHOO.widget.Button({ 
												id: "reportNewProblem",  
												type: "link",  
												label: "<%=addNewProb%>",
												href: "javascript:{}",
												container: "newProblemTabContentDiv_head"  
												});

			oButton.on("click", buildAddNewProblemPopup); 
			 		

			var oButton = new YAHOO.widget.Button({ 
								                id: "assignButton",  
								                type: "button",  
								                label: "<%=assign%>",  
								                container: "classifiedTabContentDiv_footer"  
            }); 

			var oButton = new YAHOO.widget.Button({ 
								                id: "progressButton",  
								                type: "button",  
								                label: "<%=progress%>",  
								                container: "assignedIssuesTabContentDiv_footer"  
			});	
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
				
		constMgmtTabs.appendTo('constMgmtTabContentDiv_body');

		function handleClick(e) {   
			var id = document.getElementById("hamletField");
			if(id==null || id.value==0)
	        	alert("<%=constMgmtAlertMessage%>"); 
	    }

			constMgmtTabs.getTab(0).addListener('click', handleClick);
			constMgmtTabs.getTab(1).addListener('click', handleClick);
			constMgmtTabs.getTab(2).addListener('click', handleClick);
			constMgmtTabs.getTab(3).addListener('click', handleClick);
			constMgmtTabs.getTab(4).addListener('click', handleClick);
			constMgmtTabs.getTab(5).addListener('click', handleClick);
			
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
	 
	function buildNewProblemsDataTable()
	{
		
		var myColumnDefs = [ 
	            {key:"select", label: "<%=select%>"}, 
	            {key:"title", label: "<%=title%>", sortable:true}, 
	            {key:"description", label: "<%=description%>", sortable:true}, 
				{key:"identifiedDate", label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"location", label: "<%=location%>", sortable:true},	
				{key:"source", label: "<%=source%>", sortable:true},
				{key:"scope", label: "<%=scope%>", sortable:true, editor: new YAHOO.widget.DropdownCellEditor({multiple:true,dropdownOptions:["Village","Mandal","District","State","Country"]})},
				{key:"problemType", label: "<%=problemType%>", sortable:true, editor: new YAHOO.widget.DropdownCellEditor({multiple:false,dropdownOptions:["Social","Economical","Political","Administrative","LegalIssue","Personal"]})}
	        ]; 
		
	        var myDataSource = new YAHOO.util.DataSource(problemsMainObj.newProblemsArr); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["select","title","description","identifiedDate","location","source"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 15 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("newProblemTabContentDiv_body", myColumnDefs, myDataSource,myConfigs); 
	                 
	        
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
	
	
	function buildClassifiedDataTable()
	{
			var myColumnDefs = [ 
	            {key:"select", label: "<%=select%>"}, 
	            {key:"title", label: "<%=title%>", sortable:true}, 
	            {key:"identifiedDate", label: "<%=identifiedDate%>", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"location", label: "<%=location%>", sortable:true},	
				{key:"scope", label: "<%=scope%>", sortable:true},
				{key:"problemType", label: "<%=problemType%>", sortable:true},
				{key:"department", label: "<%=department%>", editor: new YAHOO.widget.DropdownCellEditor({multiple:false,dropdownOptions:["Irrigation","DRDO","R & B","Indian Railways","APSRTC","APSEB","RTA"]})}
				
	        ]; 
	 
	   	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["select","title","identifiedDate","location"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 15 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("classifiedTabContentDiv_body", myColumnDefs, myDataSource,myConfigs); 
	                 
	       
	        problemMgmtTabs.getTab(1).addListener("click", function() {myDataTable.onShow()});         

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

	function buildAssignedIssuesDataTable()
	{
			var myColumnDefs = [ 
	            {key:"select", label: "<%=select%>"}, 
	            {key:"title", label: "<%=title%>", sortable:true}, 
	            {key:"concernedDepartment", label:"<%=concernedDept%>", sortable:true},
				{key:"assignedOfficial" , label: "<%=assignedOfficial%>", sortable:true},	
				{key:"contactNumber", label: "<%=contactnbr%>"}
				//{key:"progress" ,Progress},
				//{key:"fix" ,Fix}
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["select","title","concernedDepartment"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 15 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("assignedIssuesTabContentDiv_body", myColumnDefs, myDataSource,myConfigs); 
	                 
	       
	       problemMgmtTabs.getTab(2).addListener("click", function() {myDataTable.onShow()});         
	 
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable	           
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
		if(value=="External Person" || value=="Call Center") 
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
				
				var todayDate=curr_date + "/" + m_names[curr_month] + "/" + curr_year;
					
		var divChild = document.createElement('div');
		divChild.setAttribute('id','addNewProblemDiv');
		divChild.setAttribute('class','newProbdialog');

		var contentStr=''; 
		
		contentStr+='<div class="hd" align="left">Add New Problem</div>';
		contentStr+='<div class="bd" align="left">';
		contentStr+='<div id="problemDetailsDivBody">';
		contentStr+='<form name="form" action="problemManagementAction" method="POST">';
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
		contentStr+='<td style="padding-left: 15px;"><select id="problemSource" class="selectWidth" name="problemSource" onchange="getPersonDetails(this.options[this.selectedIndex].text)">';
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
		contentStr+='<td style="padding-left: 15px;"><input type="text" size="53" id="problemText" name="problemText"/></td>';
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
		contentStr+='</form>';
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
				  buttons : [ { text:"Add Problem", isDefault:true}, 
	                          { text:"Cancel", handler: handleNewProbCancel}]
	             } ); 
		newProblemDialog.render();
		
	}
	function handleNewProbSubmit()
	{
	
	}

	function handleNewProbCancel()
	{
		this.cancel();
	}
</script>
</head>
<body>

<div id="constituencyMgmtHeaderDiv">Constituency Management</div>

<div id="constituencyMgmtMainDiv">	
	
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
<c:forEach var="problem"  items="${constituencyManagementVO.problemManagementVO.problemDetails}" >	
var newProblemObj=	{
						select:'<input type="checkbox"></input>',
						title:'${problem.definition}',
						description:'${problem.description}',
						identifiedDate:new Date('${problem.identifiedDate}'),
						location:'${problem.location}',
						source:'${problem.source}'
					};
problemsMainObj.newProblemsArr.push(newProblemObj);											
</c:forEach>

buildConstituencyLayout();
buildOuterTabView();
buildProblemMgmtTabView();
buildConstMgmtTabView();
buildNewProblemsDataTable();
buildClassifiedDataTable();
buildAssignedIssuesDataTable();
</script>
</body>
</html>