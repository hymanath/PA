<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css"> 
	
	 
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
		#newProblemTabContentDiv_footer,#newProblemTabContentDiv_head,#classifiedTabContentDiv_footer
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

	</style>

	<script type="text/javascript">

	var outerTab,problemMgmtTabs;
	var problemsMainObj={
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
							localLeadersArray:[]						
							
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
			selectedElmt=document.getElementById("districtField");
		}	
		else if(selectedValue=="constituency")
		{
			selectedElmt=document.getElementById("constituencyField");			
		}
		else if(selectedValue=="Constituencies")
		{
			selectedElmt=document.getElementById("mandalField");
		}
		else if(selectedValue=="mandal")
		{
			selectedElmt=document.getElementById("villageField");
		}
		else if(selectedValue=="village")
		{
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
		var localLeaders = results.localLeaders;
		var voters = results.voterDetails;
		var cast = results.voterCastInfodetails.castVOs;
		var totalVoters = results.voterCastInfodetails.totalVoters;
		var maleVoters = results.voterCastInfodetails.maleVoters;
		var femaleVoters = results.voterCastInfodetails.femaleVoters;
		var votersByHouseNo = results.votersByHouseNos;
		var count=0;
		var votersByHouseNoCount = 0;
		
		
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
						SNo:votersByHouseNoCount,
						HouseNo:votersByHouseNo[i].houseNo,
						MembersInFamily:votersByHouseNo[i].numberOfPeople,
						EldestPersonName:votersByHouseNo[i].elder,
						YoungestPersonName:votersByHouseNo[i].younger,
						Cast:votersByHouseNo[i].cast						                    	
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
		
		var localCastStatsTabContent_headerEl = document.getElementById("localCastStatsTabContent_header");

		var localCastStatsTabContent = '<table width="80%">';
		localCastStatsTabContent+='<tr colspan="2">';
		localCastStatsTabContent+='<th>Total Number of Voters:</th>';
		localCastStatsTabContent+='<td align="left">'+totalVoters+'</td>';
		localCastStatsTabContent+='</tr>';
		localCastStatsTabContent+='<tr>';
		localCastStatsTabContent+='<th>Male Voters:</th>';
		localCastStatsTabContent+='<td align="left">'+maleVoters+'</td>';
		localCastStatsTabContent+='<th>Female Voters:</th>';
		localCastStatsTabContent+='<td>'+femaleVoters+'</td>';
		localCastStatsTabContent+='</tr>';
		localCastStatsTabContent+='</table>';

		localCastStatsTabContent_headerEl.innerHTML=localCastStatsTabContent;

		if(cast.length == 0)
		{			
			var emptyArr = new Array();
			constMgmtMainObj.votersByHouseNoArray = emptyArr;
			constMgmtMainObj.castStatsArray = emptyArr;
			constMgmtMainObj.votersArray = emptyArr;
			constMgmtMainObj.votersByHouseNoArray = emptyArr;
			
			buildLocalLeadersDataTable();
			buildLocalCastStatisticsDataTable();			
			buildVotersByLocBoothDataTable();	
			buildImportantVotersDataTable();		
			return;
		}
		else
		{	
			buildLocalLeadersDataTable();
			buildLocalCastStatisticsDataTable();			
			buildVotersByLocBoothDataTable();
			buildImportantVotersDataTable();
			
		}
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
		constTabContent+='<td>State</td>';
		constTabContent+='<td><select id="stateField" class="selectWidth" name="state" onchange="getnextList(this.name,this.options[this.selectedIndex].value,false)" width="10">';
		for(var i in locationDetails.stateArr)
		{
			constTabContent+='<option value='+locationDetails.stateArr[i].id+'>'+locationDetails.stateArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td>District</td>';
		constTabContent+='<td><select id="districtField" class="selectWidth" name="district"  onchange="getConstituencyList(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.districtArr)
		{
			constTabContent+='<option value='+locationDetails.districtArr[i].id+'>'+locationDetails.districtArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td>Constituency</td>';
		constTabContent+='<td><select id="constituencyField" class="selectWidth" name="constituency"  onchange="getMandalList(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.constituencyArr)
		{
			constTabContent+='<option value='+locationDetails.constituencyArr[i].id+'>'+locationDetails.constituencyArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='</tr>';
		constTabContent+='<tr>';
		constTabContent+='<td>Mandal</td>';
		constTabContent+='<td><select id="mandalField" class="selectWidth" name="mandal" onchange="getTownshipsForMandal(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.mandalArr)
		{
			constTabContent+='<option value='+locationDetails.mandalArr[i].id+'>'+locationDetails.mandalArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td>Village</td>';
		constTabContent+='<td><select class="selectWidth" id="villageField" name="village" onchange="getnextList(this.name,this.options[this.selectedIndex].value,false)">';
		for(var i in locationDetails.villageArr)
		{
			constTabContent+='<option value='+locationDetails.villageArr[i].id+'>'+locationDetails.villageArr[i].value+'</option>';
		}
		constTabContent+='</select></td>';
		constTabContent+='<td>Hamlet</td>';
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
	    label: 'Constituency Management', 
	    content:constTabContent, 
	    active: true 
		})); 

		outerTab.addTab( new YAHOO.widget.Tab({ 
	    label: 'Problem Management', 
	    content: '<div id="problemMgmtTabContentDiv"></div>'	   
		})); 
		
		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: 'User Groups', 
			content: '<div id="userGroupsTabContent">User Groups Content</div>' 
		 
		})); 
		 
		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: 'Recommendation Letters', 
			content: '<div id="recomLettTabContent">Recommendation Letters Content</div>' 
		})); 

		outerTab.addTab( new YAHOO.widget.Tab({ 
			label: 'District E Papers', 
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
		
		
				
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
	    label: 'New Issues', 
	    content:newTabContent, 
	    active: true 
		})); 

		var classifiedTabContent='<div id="classifiedTabContentDiv">';
		classifiedTabContent+='<div id="classifiedTabContentDiv_head"></div>' ;
		classifiedTabContent+='<div id="classifiedTabContentDiv_body"></div>';
		classifiedTabContent+='<div id="classifiedTabContentDiv_footer"></div>';
		classifiedTabContent+='</div>';
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Classified Issues', 
			content:classifiedTabContent 
		 
		})); 
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Assigned Issues', 
			content: '<div id="assignedIssuesContentDiv"></div>' 
		 
		})); 
		 
		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Progress', 
			content: '<div id="progressContentDiv"></div>' 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Pending Issues', 
			content: '<div id="pendingIssuesContentDiv"></div>' 
		})); 

		problemMgmtTabs.addTab( new YAHOO.widget.Tab({ 
			label: 'Fixed Issues', 
			content: '<div id="fixedIssuesContentDiv"></div>' 
		})); 

		problemMgmtTabs.appendTo('problemMgmtTabContentDiv'); 
	      
			var oButton = new YAHOO.widget.Button({ 
	                                            id: "mybuttonid",  
	                                            type: "button",  
	                                            label: "Categorize",  
	                                            container: "newProblemTabContentDiv_footer"  
	                                        }); 

			var oButton = new YAHOO.widget.Button({ 
												id: "reportNewProblem",  
												type: "link",  
												label: "Add New Problem",
												href:"problemManagementAction.action",
												container: "newProblemTabContentDiv_head"  
												}); 		

			var oButton = new YAHOO.widget.Button({ 
								                id: "assignButton",  
								                type: "button",  
								                label: "Assign",  
								                container: "classifiedTabContentDiv_footer"  
            }); 	
	}

	function buildConstMgmtTabView()
	{	
		 constMgmtTabs = new YAHOO.widget.TabView(); 

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Leaders',
			content: '<div id="localLeadersTabContent"></div>',
			disabled: true			
			
				
			
		}));

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Problems',
			content: '<div id="localProblemsTabContent"></div>',
			disabled: true
		}));
		var castStatistics='<div id="localCastStatsTabContent">';
		castStatistics+='<div id="localCastStatsTabContent_header" align="left"></div>';
		castStatistics+='<div id="localCastStatsTabContent_body"></div>';
		castStatistics+='<div id="localCastStatsTabContent_footer"></div>';
		castStatistics+='</div>';
			constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Cast Statistics',
			content: castStatistics,
			disabled: true
		}));

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Local Polictical Changes',
			content: '<div id="localPoliticalChangesTabContent"></div>',
			disabled: true
		}));

		var votersByLocBoothContent = '<div id="votersByLocationTabContentDiv">';
		votersByLocBoothContent+='<div id="votersByLocationTabContentDiv_head" align="left"></div>';
		votersByLocBoothContent+='<div id="votersByLocationTabContentDiv_body"></div>';
		votersByLocBoothContent+='<div id="votersByLocationTabContentDiv_footer"></div>';
		votersByLocBoothContent+='</div>';

		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Voters By Location/Booth',
			content: votersByLocBoothContent,
			disabled: true
			
		}));

		var importantVotersTabContent='<div id="importantVotersTabContent">';
		importantVotersTabContent+='<div id="importantVotersTabContent_header"></div>';
		importantVotersTabContent+='<div id="importantVotersTabContent_body"></div>';
		importantVotersTabContent+='<div id="importantVotersTabContent_footer"></div>';
		importantVotersTabContent+='</div>';
		constMgmtTabs.addTab( new YAHOO.widget.Tab({
			label: 'Important Voters',
			content: importantVotersTabContent,
			disabled: true
			
		}));
				
		constMgmtTabs.appendTo('constMgmtTabContentDiv_body');
	}
	
	YAHOO.example.Data = { 
	    
	        problems: [ 
		        {select:"<input type='checkbox' id='check_1'></input>", title:"Impurified Water Supply", description: "Polluted water is being supplied since two weeks", identifiedDate:new Date(2009, 2, 4), location:"MadanaPalle", source:"Party Analyst", status:"New"}, 
				{select:"<input type='checkbox' id='check_1'></input>", title:"No Bus Service", description: "APS RTC Suddenly cancelled their Bus service to NagaVaram Village", identifiedDate:new Date("January 3, 2009"),
				location:"NagaVaram", source:"Victim", status:"Assigned"}, 
		        {select:"<input type='checkbox' id='check_1'></input>", title:"No White Ration Cards issued in Hamlet", description: "White Ration card is not at all issued to eligible families even after the preliminary process", identifiedDate:new Date(2009, 11, 12), location:"Eluru", source:"Call Centre", status:"Categorized"}, 
		        {select:"<input type='checkbox' id='check_1'></input>", title:"AarogyaSri", description: "Delay for Cardiac Surgery with AarogyaSri Scheme", identifiedDate:new Date("March 11,2009") , location:"Eluru", source:"User", status:"Categorized"}, 
				{select:"<input type='checkbox' id='check_1'></input>", title:"Delay in payment of Exgratia", description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party", identifiedDate:new Date(1980, 2, 4), location:"MadanaPalle", source:"Party Analyst", status:"New"}		
	    ], 
		    localproblems: [ 
				 {sNo:"1", description: "Polluted water is being supplied since two weeks", identifiedDate: new Date(2009, 2, 4), source: "Party Analyst", status:"New"},
				 {sNo:"2", description: "APS RTC Suddenly cancelled their Bus service", identifiedDate: new Date("December3 , 2009"), source: "Party Analyst", status:"Assigned" },
				 {sNo:"3", description: "White Ration card is not at all issued to eligible families even after the preliminary process", identifiedDate: new Date(2009, 11, 12), source: "Call Center", status:"Categorized" },
				 {sNo:"4", description: "Delay for Cardiac Surgery with AarogyaSri Scheme", identifiedDate: new Date("March 11,2009"), source: "User", status:"New" },
				 {sNo:"5", description: "An activist named Ravi died while participating in the in the Rally conducted by the ruling party, but no remuneration is paid to his family from the party", IdentifiedDate: new Date(2009, 2, 4), source: "Victim", status:"New" },
				 {sNo:"6", description: "Polluted water is being supplied since two weeks", identifiedDate: new Date("December 11, 2009"), source: "Party Analyst", status:"New" } 
		],

			
			localPoliticalChanges: [
					{SNo:"1", Description: "Brief Description about the political changes", Date: new Date("December3 , 2009"),Impact: "The impact for that particular Context"},
					{SNo:"2", Description: "Brief Description about the political changes", Date: new Date("December3 , 2009"),Impact: "The impact for that particular Context"},
					{SNo:"3", Description: "Brief Description about the political changes", Date: new Date("December3 , 2009"),Impact: "The impact for that particular Context"},
					{SNo:"4", Description: "Brief Description about the political changes", Date: new Date("December3 , 2009"),Impact: "The impact for that particular Context"},
					{SNo:"5", Description: "Brief Description about the political changes", Date: new Date("December3 , 2009"),Impact: "The impact for that particular Context"}
		]
				           			
		}					
	 

		
	function buildNewProblemsDataTable()
	{
		
		var myColumnDefs = [ 
	            {key:"select", label: "Select"}, 
	            {key:"title", label: "Title", sortable:true}, 
	            {key:"description", label: "Description", sortable:true}, 
				{key:"identifiedDate", label: "Identified Date", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"location", label: "Location", sortable:true},	
				{key:"source", label: "Source", sortable:true},
				{key:"scope", label: "Scope", sortable:true, editor: new YAHOO.widget.DropdownCellEditor({multiple:true,dropdownOptions:["Village","Mandal","District","State","Country"]})},
				{key:"problemType", label: "Problem Type", sortable:true, editor: new YAHOO.widget.DropdownCellEditor({multiple:false,dropdownOptions:["Social","Economical","Political","Administrative","LegalIssue","Personal"]})}
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
	            {key:"select", label: "Select"}, 
	            {key:"title", label: "Title", sortable:true}, 
	            {key:"identifiedDate", label: "Identified Date", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
				{key:"location", label: "Location", sortable:true},	
				{key:"scope", label: "Scope", sortable:true},
				{key:"problemType", label: "Problem Type", sortable:true},
				{key:"department", label: "Department", editor: new YAHOO.widget.DropdownCellEditor({multiple:false,dropdownOptions:["Irrigation","DRDO","R & B","Indian Railways","APSRTC","APSEB","RTA"]})}
				
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
	            {key:"SNo"}, 
	            {key:"Title", sortable:true}, 
	            {key:"Concerned Department",sortable:true},
				{key:"Assigned Official" ,sortable:true},	
				{key:"Contact Number"},
				{key:"Progress"},
				{key:"Fix"}
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.problems); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: ["SNo","Title","Concerned Department","Progress"] 
	        }; 
			
			var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 15 
		    }) 
			}; 

			var myDataTable =  
	            new YAHOO.widget.DataTable("assignedIssuesContentDiv", myColumnDefs, myDataSource,myConfigs); 
	                 
	       
	       problemMgmtTabs.getTab(2).addListener("click", function() {myDataTable.onShow()});         
	 
	        return { 
	            oDS: myDataSource, 
	            oDT: myDataTable	           
	      }; 
	   
	}
	
	function buildLocalLeadersDataTable()
	{
		var localLeadersColumnDefs = [ 
		    	             
		    	            {key:"name", label: "Name", sortable:true}, 
		    	            {key:"occupation", label: "Occupation", sortable:true}, 
		    				{key:"position", label: "Position", sortable:true},
		    				{key:"influenceScope", label: "Influence Scope", sortable:true},	
		    				{key:"contactNumber", label: "Contact Number"}
		    				
		    				
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
		    	            {key:"sNo", label: "SNo", formatter:"number", sortable:true}, 
		    	            {key:"description", label: "Description", sortable:true},
		    				{key:"identifiedDate", label: "Identified Date",formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
		    				{key:"source", label: "Source", sortable:true},
		    				{key:"status", label: "Status", sortable:true}
		    						    				
		    	        ]; 
		var localProbDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.localproblems); 
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
		    	            
		    	            {key:"caste", label: "Caste", sortable: true}, 
		    	           	{key:"castePopulation", label: "Caste Population", formatter:"number", sortable: true},
		    				{key:"castePercentage", label: "Caste Percentage", formatter:YAHOO.widget.DataTable.formatFloat, sortable:true}	
		    					    			    				
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
		                                 {key: "SNo", formatter:"number", sortable:true},
		                                 {key: "Description", sortable:true},
		                                 {key: "Date", formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC}},
		                                 {key: "Impact", sortable:true}

		                                 ];

		var localPolChangesDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.localPoliticalChanges);
		localPolChangesDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		localPolChangesDataSource.responseSchema = { 
	            fields: [{key:"SNo", parser:"number"}, "Description", "Date", "Impact"]
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
		    	            {key:"sNo", label: "SNo", formatter:"number", sortable:true}, 
		    	            {key:"name", label: "Name", sortable: true}, 
		    	            {key:"gender", label: "Gender", sortable: true},
		    				{key:"age", label: "Age", sortable:true},
		    				{key:"hNo", label: "HNo", sortable:true},
		    				{key:"guardianName", label: "Guardian Name", sortable:true},
		    				{key:"relationship", label: "Relationship", sortable:true},	
		    				{key:"cast", label: "Cast", sortable:true},
		    				{key:"castCategory", label: "Cast Category", sortable:true}
		    					
		    					    			    				
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
		    	            {key:"SNo", formatter:"number", sortable:true}, 
		    	            {key:"HouseNo", sortable: true}, 
		    	            {key:"MembersInFamily", sortable: true},
		    				{key:"EldestPersonName", sortable:true},
		    				{key:"YoungestPersonName", sortable:true},
		    				{key:"Cast", sortable:true}
		    				//{key:"Mobile"}
		    				  					    			    				
		    	        ]; 
		var myDataSource = new YAHOO.util.DataSource(constMgmtMainObj.votersByHouseNoArray); 
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
        myDataSource.responseSchema = { 
            fields: [
                     
                         {key:"SNo", parser:"number"}, 
                         "HouseNo", "MembersInFamily", "EldestPersonName", "YoungestPersonName", "Cast"] 
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

buildConstituencyLayout();
buildOuterTabView();
buildProblemMgmtTabView();
buildConstMgmtTabView();

	
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

buildNewProblemsDataTable();
buildClassifiedDataTable();
buildAssignedIssuesDataTable();	
buildLocalProblemsDataTable();
buildLocalPoliticalChangesDataTable();

</script>
</body>
</html>