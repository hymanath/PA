<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><c:out value='${districtName}'/> District News,Constituencies,MLA, MP,Details,  Elections Results,Parties Performance,MPTC, ZPTC, Municipality, Corporation Election Results</title>
 <META NAME="Keywords" CONTENT="<c:out value='${districtName}'/> District, About <c:out value='${districtName}' />, <c:out value='${districtName}'/> Elections, <c:out value='${districtName}'/> Elections Analysis,<c:out value='${districtName}'/> Elections Results, <c:out value='${districtName}'/> Leaders,  <c:out value='${districtName}'/> Parties, <c:out value='${districtName}'/> Problems, <c:out value='${districtName}'/> Politics, <c:out value='${districtName}'/> MLA's, <c:out value='${districtName}'/> MP's,<c:out value='${districtName}'/> Voting Trends,<c:out value='${districtName}'/> MPTC, <c:out value='${districtName}'/> ZPTC, <c:out value='${districtName}'/> Municipality,<c:out value='${districtName}'/> Cross Voting,<c:out value='${districtName}'/> Constituencies">

<META NAME="Description" CONTENT=" <c:out value='${districtName}'/> district page ,district page news,MLA constituencies information, MP constituencies information, district election results and latest news of the district.<c:out value='${districtName}'/> district page provides Assembly election results, Parliament election results, MPTC election results, ZPTC election results, Municipal election results, Corporation election results of all election years.">


<!-- Combo-handled YUI CSS files: --> 
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/combo?2.8.2r1/build/assets/skins/sam/skin.css"> 
<!-- Combo-handled YUI JS files: --> 
<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/animation/animation-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/carousel/carousel-min.js&2.8.2r1/build/layout/layout-min.js"></script>  

<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<!-- JQuery files (End) -->

	<script type="text/javascript" src="http://www.google.com/jsapi"></script> 
    <script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
	<script type="text/javascript" src="js/connectPeople/connectPeopleContent.js"></script>	
	<script type="text/javascript" src="js/districtPage/districtPage.js"></script>	
	<script type="text/javascript" src="js/googleAnalytics/googleAnalytics.js"></script>

<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage2.css" />

	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<style type="text/css">
	
	#mp_body{border-collapse:collapse;border:1px solid #d3d3d3;width:96%;}
	#mla_body{border-collapse:collapse;border:1px solid #d3d3d3;width:96%;}
	#mpsInfoDivBody > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;width:100%;}
	#mlaInfoDivBody > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;width:100%;}
	#dataTable6 > table,#dataTable3 > table, #corporationDataTable0 > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;width:111%;margin-left:3px;}
	#partyDetails > table,#mptcPartyDetails > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;}
	#mptc_zptc_div_main table2,{border-collapse:collapse;border:1px solid #d3d3d3;width:870px;}
	#dataTable4,#dataTable3{width:429px;}
	#dataTable4 > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;width:80%;}
	#dataTable5 > table * th,#dataTable4 > table * th,#dataTable3 > table * th,#dataTable1 > table * th, 
	#dataTable4 > table * th,#dataTable5 > table * th,#mptcPartyDetails > table * th,#partyDetails > table * th,#mlaInfoDivBody > table * th,#mpsInfoDivBody > table * th{height:20px;text-align:left;background-color:#ceedf0 !important; padding:5px;font-weight:bold;border-collapse:collapse;}
	#dataTable4 > table * tr:nth-child(even),#mpsInfoDivBody > table * tr:nth-child(even){background:#f9f9f9 !important;}
	#corporationDataTable0 > table * th,#dataTable3 > table * th,#dataTable2 > table * th,#dataTable1 > table * th,#dataTable6 > table * th,#dataTable0 > table * th{height:20px;text-align:center;background-color:#ceedf0 !important; padding:5px;font-weight:bold;border-collapse:collapse;}
	#mlaInfoDivBody > table * tr:nth-child(odd){background:#f9f9f9;}
	#corporationDataTable0 > table * tr:nth-child(odd),#dataTable0 > table * tr:nth-child(odd){background:#f9f9f9;}
	#mptcPartyDetails > table * tr:nth-child(odd){background:#f9f9f9;}
	#partyDetails > table * tr:nth-child(odd){background:#f9f9f9;}
	#dataTable3 > table * tr:nth-child(odd){background:#f9f9f9;}
	#dataTable2 > table * tr:nth-child(odd){background:#f9f9f9;}
	#dataTable6 > table * tr:nth-child(odd),#dataTable1 > table * tr:nth-child(odd){background:#f9f9f9;}
	#dataTable5 > table * tr:nth-child(even),#dataTable4 > table * tr:nth-child(even){background:#f9f9f9;}
	#mptcPartyDetails > table * th{font-color:none;text-decoration:none;border-collapse:collapse;}
	#mptc_zptc_div_main a{color:#000000;}

	#allMuncipalitiesDetails1 table,#allMuncipalitiesDetails0 table,#allMuncipalitiesDetails3 table,#allMuncipalitiesDetails2 table,#corporationDataTable0 > table,#dataTable6 > table,#dataTable0 > table,#dataTable2 > table,#dataTable1 > table,#dataTable3 > table,#mptcPartyDetails table,#partyDetails table,#mlaInfoDivBody table,#mpsInfoDivBody table{border-collapse:collapse; }
	#dataTable5 > table,#corporationDiv > table{border:none;border-collapse:none; }
	#muncipalitiesDiv > table{border-collapse:collapse;border:1px solid #d3d3d3;width:102%;}

	#dataTable4 > table{border-collapse:collapse;}
	#dataTable5 > table{border-collapse:collapse;width:100%;}

	#corporationDataTable0 > table * td,#dataTable0 > table * td,#dataTable1 > table * td,#dataTable2 > table * td,#dataTable3 > table * td,#dataTable4 > table * td,#dataTable5 > table * td,#dataTable6 > table * td,#dataTable7 > table * td,#partyDetails > table * td,#mptcPartyDetails > table * td{margin-left:auto;margin-right:auto;float:none;font:13px Arial, Helvetica, sans-serif;text-align:center;font-weight:bold;color:#73787;}
	#mlaInfoDivBody > table * td{text-align:left;}
	#dataTable0,#dataTable1,#dataTable2 ,#dataTable3 ,#dataTable4 ,#dataTable5,#dataTable6,#dataTable7,#corporationDataTable0 {margin-top:11px;}
	#mlaInfoDivBody > table * td ,#mpsInfoDivBody > table * td,#corporationDataTable0 > table * td {font:13px Arial, Helvetica, sans-serif;font-weight:bold;}
	#corporationDataTable0 > table * td{font:13px Arial, Helvetica, sans-serif;font-weight:bold;color:#73787E;}
	.districtPageRoundedHeaders_center
	{
	background-color:#72CAED;
	font-weight:bold;
	padding:11px;
	height:14px;
	}

	#allElectionResultsInDT_body
	{
	width:1000px;
	}

	#zptc_body,#mptc_body
	{
	border-left:1px solid #E0E0D6;
	border-right:1px solid #E0E0D6;
	border-bottom:1px solid #E0E0D6;
	overflow:none;
	height:300px;
	}

	#dataTable5 > table,#dataTable0 > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;margin-left:3px;width:118%;}
	#dataTable1 > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;margin-left:3px;width:129%;}
	#dataTable2 > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;margin-left:3px;width:117%;}
	#allMuncipalitiesDetails3{width:96%;}
	#mptcPartyDetails table{width:114%;margin-left:13px;}
	#partyDetails table{width:130%;margin-left:13px;}
	#allMuncipalitiesDetails10,#allMuncipalitiesDetails9,#allMuncipalitiesDetails7,#allMuncipalitiesDetails8,#allMuncipalitiesDetails6,#allMuncipalitiesDetails5,#allMuncipalitiesDetails4,#allMuncipalitiesDetails1,#allMuncipalitiesDetails3,#allMuncipalitiesDetails2,#allMuncipalitiesDetails0{margin-left:20px;}
	#dataTable10 > table,#dataTable9 > table,#dataTable8 > table,#dataTable7 > table{display:table;border-collapse:collapse;border:1px solid #d3d3d3;width:100%;}
	dataTable10 > table th,#dataTable9 > table th,#dataTable8 > table th,#dataTable7 > table th{background-color:#ceedf0 !important;text-align:left;height:20px;border-collapse:collapse;font-weight:bold;padding:5px;}
	#dataTable10 > table * td,#dataTable9 > table * td,#dataTable8 > table * td,#dataTable7 > table * td {margin-left:auto;margin-right:auto;float:none;font:13px Arial, Helvetica, sans-serif;text-align:center;font-weight:bold;color:#73787;}
	#dataTable10 > table * tr:nth-child(odd),#dataTable9 > table * tr:nth-child(odd),#dataTable8 > table * tr:nth-child(odd),#dataTable7 > table * tr:nth-child(odd){background:#f9f9f9;}
	#dataTable10 > table,#dataTable9 > table,#dataTable8 > table{margin-top:13px;}

	</style>

	<script type="text/javascript">
	google.load("visualization", "1", {packages:["corechart"]});

	google.load("elements", "1", {packages : ["newsshow"]});
	
	var mptcHIde = false;
	var zptcHIde = false;

	var Localization = { <%		
		ResourceBundle rb = ResourceBundle.getBundle("globalmessages");
		String totalMuncipalities = rb.getString("totalMuncipalities");
		String totalCorporations = rb.getString("totalCorporations");
		String muncipalDataAvailability = rb.getString("muncipalDataAvailability");
		String corporationsDataAvailability = rb.getString("corporationsDataAvailability");
	%> }

	var stateName = '${stateDetails.name}';
	districtName = '${districtName}';
	
	var tehsilDetails={
			zptcArray:[],
			mptcArray:[],
			partyArray:[],
			partyMptcArray:[],
			partyMuncipalArray:[],
			partyCorporationArray:[],
			localBodyArray:[]
		};
	var districtId = '${districtId}';
	var myDataTableForParty,myDataTableForMptcParty,zptcElectionYear,mptcElectionYear;
	var mptcElectionTypeId='${mptcElectionTypeId}',zptcElectionTypeId='${zptcElectionTypeId}',muncipalityElectionId='${muncipalityElectionTypeId}',corporationElectionTypeId='${corporationElectionTypeId}';
	var mptcElectionType='${mptcElectionType}',zptcElectionType='${zptcElectionType}',muncipalityElectionType='${muncipalityElectionType}',corporationElectionType='${corporationElectionType}';
	var totalZptcs = 0,totalMptcs = 0,mptcCount=1,zptcCount=1,createGroupDialog;
	var selectedZptcYear,selectedMptcYear,myDataTableForMuncipalParty;
	var totalMuncipalities = "<%=totalMuncipalities%>";
	var totalCorporations = "<%=totalCorporations%>";
	var muncipalDataAvailability = "<%=muncipalDataAvailability%>";
		var corporationsDataAvailability = "<%=corporationsDataAvailability%>";



	function initializeResultsTable() {
	var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("mlaDataSortingTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "constituencyName",formatter:YAHOO.widget.DataTable.formatLink
		}, {
			key : "candidateName",formatter:YAHOO.widget.DataTable.formatLink
		}, {
			key : "partyFlag"
		}, {
			key : "parliamentConstituencyName"
		}, {
			key : ""
		}]
	};

	var resultsColumnDefs = [ {
		key : "constituencyName",
		label : "Constituency Name",
		sortable : true
	}, {
		key : "candidateName",
		label : "Candidate Name",
		sortable : true
	}, {
		key : "parliamentConstituencyName",
		label : "Parliament Constituency",
		sortable : true	
	}, {
		key : "partyFlag",
		label : "Party Flag",
		sortable : true	
	}, {
		key : "",
		label : "Complete Results"	
	}];


	var myDataTable = new YAHOO.widget.DataTable("mlaInfoDivBody",resultsColumnDefs, resultsDataSource);  
}


	function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
	{
   //var browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
   browser1.focus();
	}

	function initializeResultsTableForMp() {

		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get("mpsDataSortingTable"));
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
		resultsDataSourceForTehsil.responseSchema = {
			fields : [  {
				key : "constituencyName"
			},{
				key : "candidateName"
			}, {
				key : "partyFlag"
			}, {
				key : ""
			}]
		};

		var resultsColumnDefsForTehsil = [  {
			key : "constituencyName",
			label : "Constituency",
			sortable : true
		},{
			key : "candidateName",
			label : "Candidate",
			sortable : true
		}, {
			key : "partyFlag",
			label : "Flag",
			sortable : true	
		} , {
			key : "",
			label : "Complete Results"
		} ];

		
		var myDataTableForTehsil = new YAHOO.widget.DataTable("mpsInfoDivBody",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);  
		
	}
	
		function initializeResultsTableForParty(){
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyArray);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSourceForTehsil.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "percentageOfVotesWonByParty"
			}]
		};
	
		var resultsColumnDefsForTehsil = [ {
			key : "partyName",
			label : "Party Name",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Participated Seats",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Seats Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Votes %",
			sortable : true
		} ];

						
		myDataTableForParty = new YAHOO.widget.DataTable("partyDetails",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);

		 var ajaxImgElmt = document.getElementById("zptcAjaxLoadDiv");
		 ajaxImgElmt.style.display = "none"; 
		 
		return {
			oDS:resultsDataSourceForTehsil, 
			oDT:myDataTableForParty			
		};  		
	}
		function initializeMptcResultsTableForParty(){
		
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(tehsilDetails.partyMptcArray);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSourceForTehsil.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "percentageOfVotesWonByParty"
			}]
		};
	
		var resultsColumnDefsForTehsil = [ {
			key : "partyName",
			label : "Party Name",
			sortable : true
		}, {
			key : "participatedSeats",
			label : "Participated Seats",
			sortable : true
		}, {
			key : "seatsWonByParty",
			label : "Seats Won",
			sortable : true
		}, {
			key : "percentageOfVotesWonByParty",
			label : "Votes %",
			sortable : true
		} ];

						
		myDataTableForMptcParty = new YAHOO.widget.DataTable("mptcPartyDetails",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);

		var ajaxImgElmt = document.getElementById("mptcAjaxLoadDiv");
		 ajaxImgElmt.style.display = "none"; 
		 
		return {
			oDS:resultsDataSourceForTehsil, 
			oDT:myDataTableForMptcParty			
		}; 		
	}
	
	function callAjax(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);		
					if(jsObj.task == "getAllElectionYears")
					{
						if(results!= null &&  results.length>0){
							showAllYearsForZptc(results);
						}
					}	
					if(jsObj.task == "getAllMptcElectionYears")
					{
						if(results!= null &&  results.length>0){
							showAllYearsForMptc(results);
						}
					}
					if(jsObj.task == "getPartyDetails") 
					{						
						if(results!= null &&  results.length>0){
							zptcHide = false;
							showAllPartyDetails(results);
						}
						else{
							zptcHide = true;
							 if(mptcHide)
								hideZPTCMPTCDiv();
							 else
								hideZptcDiv();
						}
					}
					if(jsObj.task == "getMptcPartyDetails") 
					{
						if(results!= null &&  results.length>0){
							mptcHide = false;
							showAllMptcPartyDetails(results);
						}
						else{
							mptcHide = true;
							 if(zptcHide)
							  hideZPTCMPTCDiv();
							 else
							  hideMptcDiv();
						}
					}		
					if(jsObj.task == "getmuncipalPartyDetails") 
					{
						if(results.resultStatus.resultCode==0){
							showMuncipalDetailsForLatestElectionYear(results.muncipalityVO,muncipalityElectionType);
						
						}else{
							errorMessageMuncipalitiesDiv();	
						}
					}
					if(jsObj.task == "getCorporationPartyDetails") 
					{
						if(results.resultStatus.resultCode==0){
							showMuncipalDetailsForLatestElectionYear(results.muncipalityVO,corporationElectionType);
							//buildCorporationChart(results.muncipalityVO);
						}
					else{
							errorMessageCorporationDiv();	
						}
					}
					if(jsObj.task == "getAllMptcParties") 
					{
						if(results!= null &&  results.length>0){
							showAllMptcParties(results);
						}
					}
					if(jsObj.task == "getAllZptcParties") 
					{
						if(results!= null &&  results.length>0){
							showAllZptcParties(results);
						}
					}
					if(jsObj.task == "getAllElectionsInDistrict")
					{		
						//JFree graph								
						//showAllElectionsInDistrict(results);

						//Google Graph
						showElectionGraph(results);
					}
					if(jsObj.task == "getElectionTypesAndYears")
					{										
						buildElectionTypesAndYears(results);
					}
					if(jsObj.task == "getPartiesPositions")
					{		
						var imgEl = document.getElementById("ajaxImageEl");	
						imgEl.style.display='none';							
						buildElectionTypesAndYearsGraph(results);
					}if(jsObj.task == "getAllElectionScopes")
					{										
						buildElectionTypesSelect(results);
					}
					if(jsObj.task == "connectToUser")
					{
						closeConnectPanel(jsObj,results);
					}
					if(jsObj.task == "getAllConnectedUsers")
					{
						showAllConnectedUsersInPanel(jsObj,results);
					}		
					if(jsObj.task == "connectUserSet")
					{
						showAllConnectedUsersStatus(jsObj,results);
					}
					if(jsObj.task == "getAllConnectedUsersByFilterView")
					{
						showAllConnectedUsersInPanelByFilterView(jsObj,results);
					}
					else if(jsObj.task == "sendMessageToConnectUser")
					{						
						showMessageConfirmation(results);
					}
					
			}catch (e) {   		
			// alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     		//	alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	
		function hideZPTCMPTCDiv()
		{
		var table2El = document.getElementById("table2");
		  table2El.style.display = 'none';
		}
		function initializeMuncipalResultsTableForParty(divId, dataSrc,electionType)
		{
			
		var resultsDataSourceForTehsil = new YAHOO.util.DataSource(dataSrc);
		resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
		resultsDataSourceForTehsil.responseSchema = {
			fields : [ {
				key : "partyName"
			}, {
				key : "participatedSeats"
			}, {
				key : "seatsWonByParty"
			}, {
				key : "percentageOfVotesWonByParty"
			}]	
		};
		
		var resultsColumnDefsForTehsil = [ 
				{key:"partyName",label : "Party Name",sortable:true, resizeable:true}, 
				{key:"participatedSeats",label : "Participated Seats",sortable:true,resizeable:true}, 
				{key:"seatsWonByParty",label : "Seats Won",sortable:true, resizeable:true}, 
				{key:"percentageOfVotesWonByParty",label : "Votes %", sortable:true, resizeable:true}	           
		];

						
		var myDataTableForMuncipalParty	 = new YAHOO.widget.DataTable(divId,resultsColumnDefsForTehsil, resultsDataSourceForTehsil);

		if(electionType=='CORPORATION')
			{
		
		var data = new google.visualization.DataTable();
		data.addColumn('string','partyName');
		data.addColumn('number','seatsWonByParty');
		data.addRows(dataSrc.length);
		for(var j=0; j<dataSrc.length; j++)
		{
			
			data.setValue(j,0,dataSrc[j].partyName);
			data.setValue(j,1,dataSrc[j].seatsWonByParty);
		}
		var chart = new google.visualization.PieChart(document.getElementById('CorporationPieChartDiv'));
	
		chart.draw(data,{width: 350, height: 230,legend:'right',legendTextStyle:{fontSize:12},title:'All Parties Performance In ${districtName} District',titleTextStyle:{color:'blue',fontName:'verdana',fontSize:9}});
			}
			
	}

		function hideMuncipalitiesDiv(){		
		var muncipalityDIV = document.getElementById("muncipalitiesDiv");	
		if(muncipalityDIV.style.display=='block'){
			muncipalityDIV.style.display = 'none';
		}else{
			muncipalityDIV.style.display = 'block';
		}		
	}	
		function hideCorporationDiv(){
		var muncipalityDIV = document.getElementById("corporationDiv");	
		if(muncipalityDIV.style.display=='block'){
			muncipalityDIV.style.display = 'none';
		}else{
			muncipalityDIV.style.display = 'block';
		}
	}
		function muncipalityHeadConstruction(result){
	
		
		var totalMessage = "<%=totalMuncipalities%>";
		var muncipality = document.getElementById("muncipalitiesDivHead");
	
		var muncipalityDiv='';		
		muncipalityDiv+='<table border="0" cellpadding="0" cellspacing="0"><tr>';
		//muncipalityDiv+='<td><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>';	
		muncipalityDiv+='<td><div id="muncipalityInfoDivHead" class="districtPageRoundedHeaders_center" style="padding: 9px; width: 977px; height: 18px;-moz-border-radius:6px;padding-bottom:7px;margin-top:-37px;background:#72CAED;">';
		muncipalityDiv+='<a class="districtPage_headerAnc" href="javascript:{}" style="color:#ffffff;">'+totalMessage+' : <b class="counterSize"> '+result[0].totalMuncipalities+'</b></a>';
		muncipalityDiv+='</div></td>';
		//muncipalityDiv+='<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>';	
		muncipalityDiv+='</tr></table>';	
		
		muncipality.innerHTML = muncipalityDiv;
		
	}
		
		function corporationHeadConstruction(result){
	
		var totalMessage = "<%=totalCorporations%>";
		var corporation = document.getElementById("corporationDivHead");
		
		var corporationDiv='';	

		corporationDiv+='<table border="0" cellpadding="0" cellspacing="0"><tr>';
		//corporationDiv+='<td width="30"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>';	
		corporationDiv+='<td><div id="corporationInfoDivHead" class="districtPageRoundedHeaders_center" style="padding: 9px; width:976px; height: 18px;-moz-border-radius:6px;padding-bottom:7px;background:#72CAED;">';
		corporationDiv+='<a class="districtPage_headerAnc" href="javascript:{}" style="color:#ffffff;">'+totalMessage+' : <b class="counterSize"> '+result[0].totalMuncipalities+'</b></a>';
		corporationDiv+='</div></td>';
		
		//corporationDiv+='<td><img width="30" height="36" src="images/icons/districtPage/header_right.gif" style="clear:both;margin-right:107px;"/></td>';	
		corporationDiv+='</tr></table>';	
		corporation.innerHTML = corporationDiv;
	}
		function showMuncipalDetailsForLatestElectionYear(result,electionType){		
		
		var muncipalityDIV = '';
		//var muncipalityElectionType='${muncipalityElectionType}';
		if(electionType == muncipalityElectionType){

			localBodyArray = tehsilDetails.partyMuncipalArray;
			muncipalityDIV = document.getElementById("muncipalitiesDiv");
			muncipalityHeadConstruction(result);
		}else{			
			localBodyArray = tehsilDetails.partyCorporationArray;
			muncipalityDIV = document.getElementById("corporationDiv");
		
			corporationHeadConstruction(result);
		}	
		
		var listSize = result[0].totalMuncipalities-1;
		var rvStr = '';
		rvStr+='<table width="98%" border="1" style="border-collapse:collapse;">';		
		for(var i in result)
		{		
			if(i%2==0){
				rvStr+='</tr>';
				rvStr+='<tr>';
			}
			
			if(i == listSize)
				rvStr+='<td colspan="2" style="vertical-align: top;">';
			else
				rvStr+='<td  style="vertical-align: top;">';		
			assignToPartyDataArray = new Array();
			
			rvStr += '<div class="localBodiesElectionHead">';
			rvStr += '<table>';
			rvStr += '<tr>';
			rvStr += '<td width="10px"><img width="8" height="9" src="images/icons/indexPage/listIcon.png"></img></td>';
			if(electionType == muncipalityElectionType)
				rvStr += '<td><u> '+result[i].muncipalityName+' muncipality Election Details</u></td>';
			else
				rvStr += '<td><u> '+result[i].muncipalityName+' corporation Election Details</u></td>';
			rvStr += '</tr>';
			rvStr += '</table>';
			rvStr += '</div>';
			rvStr += '<div id="allMuncipalitiesDetails'+i+'" style="width:100%;vertical-align:top;" >';
			rvStr += '<table width="90%" border="1" style="background-color:#F3F6F7;width:auto;margin-left:5px;">';
			rvStr += '<tr>';
			rvStr += '<th align="left">Muncipality Name :</th><td align="left">'+result[i].muncipalityName+'</td>'; 
			rvStr += '<th align="left">Total Wards :</th><td align="left">'+result[i].totalWards+'</td>';
			rvStr += '</tr>';
			rvStr += '<tr>';
			rvStr += '<th align="left">Total Voters :</th><td align="left">'+result[i].totalVoters+'</td>';
			rvStr += '<th align="left">Total Polled Votes :</th><td align="left">'+result[i].totalPolledVotes+'</td>';
			rvStr += '</tr>';
			rvStr += '</table>';	
			rvStr +='<div>';
			rvStr +='<table>';
			rvStr +='<tr><td style="height:10px;"></td></tr>';																					
			rvStr +='<tr>';																					
			rvStr +='<td style="vertical-align:top;">';			
			if(electionType == muncipalityElectionType){
				rvStr +='<a href="javascript:{}" onclick="redirectMuncipalityCandidateLink('+ result[i].muncipalityId+','+result[i].latestMuncipalElectionYear+',\''+result[i].muncipalityName+'\')"  style="text-decoration:none;color:#ffffff;background:#3897A5" class="candidateDetailsStyle">Show Candidate Details</a></td>';
			}else{
				rvStr +='<a href="javascript:{}" onclick="redirectCorporationCandidateLink('+ result[i].muncipalityId+','+result[i].latestMuncipalElectionYear+',\''+result[i].muncipalityName+'\' )"  style="text-decoration:none;color:#ffffff;background:#3897A5;" class="candidateDetailsStyle">Show Candidate Details</a></td>';
			}			
			rvStr+='</td>';
			rvStr +='</tr>';
		
			rvStr +='<tr>';
			if(electionType == muncipalityElectionType){
				rvStr +='<td style="vertical-align: top;"> <div><div id="dataTable'+i+'"></div></div></td>';
			}else{
				rvStr +='<td style="vertical-align: top;"> <div><div id="corporationDataTable'+i+'"></div></div></td>';
			}
			rvStr +='</tr>';			
			rvStr +='</table></div>';
			rvStr+='</td>';
		}
		rvStr+='</table>';	
		muncipalityDIV.innerHTML = rvStr;	
		
		for(var i in result)
		{
			var localDataArr = new Array();
			for(var j in result[i].muncipalityVO)
			{					
				var muncipalObj =
				 {		
						partyId: result[i].muncipalityVO[j].partyId,
						partyName:result[i].muncipalityVO[j].partyName,
						participatedSeats:result[i].muncipalityVO[j].participatedSeats,
						seatsWonByParty:result[i].muncipalityVO[j].seatsWonByParty,
						percentageOfVotesWonByParty:result[i].muncipalityVO[j].percentageOfVotesWonByParty				
				 };
				localDataArr.push(muncipalObj);
			}	
			if(electionType == muncipalityElectionType){	
				initializeMuncipalResultsTableForParty('dataTable'+i,localDataArr,electionType) ;
			}else{
				initializeMuncipalResultsTableForParty('corporationDataTable'+i,localDataArr,electionType) ;
				//buildCorporationChart('CorporationPieChartDiv'),localDataArr,electionType);




				/*var data = new google.visualization.DataTable();
				data.addColumn('String','Party');
				data.addColumn('number','Seats Won');
				data.addRows(result.length);
		for(var j=0;j<result.length;j++)
		{
			data.setValue(j,0,result[j].partyName);
			data.setValue(j,1,seatsWonByParty:result[j].seatsWonByParty);
		}
		var chart = new google.visualization.PieChart(document.getElementById('CorporationChartDiv'));
		chart.draw(data, {width: 330, height: 230});
	*/
				 
			}
		}
	}

	/*function buildCorporationChart(dataSrc,electionType)
	{
		alert(dataSrc);
		debuggr;
		var data = new google.visualization.DataTable();
		data.addColumn('String','Party');
		data.addColumn('number','Seats Won');
		data.addRows(dataSrc.length);
		for(var j=0;j<dataSrc.length;j++)
		{
			data.setValue(j,0,results[j].partyName);
			data.setValue(j,1,seatsWonByParty:results[j].seatsWonByParty);
		}
		var chart = new google.visualization.PieChart(document.getElementById('CorporationChartDiv'));
		chart.draw(data, {width: 330, height: 230});
	

	}*/






		function errorMessageMuncipalitiesDiv(){
		var table1El = document.getElementById("table1");
		table1El.style.display = 'none';
		/*
		var muncipality = document.getElementById("muncipalitiesDivHead");	
		var muncipalityDIV='';
		muncipalityDIV+='<table border="0" cellpadding="0" cellspacing="0"><tr>';
		muncipalityDIV+='<td width="30"><img src="images/icons/districtPage/header_left.gif"/></td>';
		muncipalityDIV+='<td><div id="muncipalityInfoDivHead" class="districtPageRoundedHeaders_center" style="padding: 9px; width: 857px; height: 18px;">'+totalMuncipalities+' : <b class="counterSize"> </b></div>';
		muncipalityDIV+='</td></tr></table>';
		muncipality.innerHTML += muncipalityDIV;

		var muncipalityDIV = document.getElementById("muncipalitiesDiv");	
		var rvStr = '';
		rvStr+='<b style="font-weight:12px">'+muncipalDataAvailability+'</b>';	
		rvStr+='<br/><br/>';
		muncipalityDIV.innerHTML += rvStr;
		*/	
	}	
	function errorMessageCorporationDiv(){
	var muncipality = document.getElementById("corporationDivHead");	
	var totalCorporations="<%=totalCorporations%>";
	var corporationsDataAvailability="<%=corporationsDataAvailability%>";
	var muncipalityDIV='';
	muncipalityDIV+='<table border="0" cellpadding="0" cellspacing="0"><tr>';
	//muncipalityDIV+='<td width="30"><img width="5" height="36" src="images/icons/districtPage/header_left.gif"/></td>';
	muncipalityDIV+='<td><div id="corporationInfoDivHead" class="districtPageRoundedHeaders_center" style="padding: 9px; width: 897px; height: 18px;-moz-border-radius:6px;color:#ffffff;background:#72CAED;">'+totalCorporations+' : <b class="counterSize"> </b></div>';
	muncipalityDIV+='</td>';
	//muncipalityDIV+='<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif" style="clear:both;margin-right:107px;"/></td></tr></table>';
	muncipality.innerHTML += muncipalityDIV;

	var muncipalityDIV = document.getElementById("corporationDiv");	
	var rvStr = '';
	rvStr+='<b style="font-weight:12px">'+corporationsDataAvailability+'</b>';	
	rvStr+='<br/><br/>';
	muncipalityDIV.innerHTML += rvStr;	
	document.getElementById('corporationDivHead').style.display='none';
	document.getElementById('corporationInfoDivHead').style.display='none';
	document.getElementById('corporationDiv').style.display='none';
	

}

	
		function getAllElections(elecId, type){
		var barloaderImageEl = document.getElementById("barloaderImage");
		barloaderImageEl.style.display = 'block';
		var jsObj=
		{		
				districtId:districtId,
				districtName:"${districtName}",
				electionTypeId:elecId,
				electionType:type,
				task:"getAllElectionsInDistrict"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsInDistrictAction.action?"+rparam;					
		callAjax(jsObj,url);
	}
	
		function buildElectionTypesSelect(result){

		var selectLabel = document.getElementById("graphElectionTypeLabel");
		var selectData = document.getElementById("graphElectionTypeSelect");

		var labelStr = '';			
		
		if(result[0].name != 'Assembly'){
			labelStr += 'To view all parties performance in a specific election, please select an Election Type';
		}
		if(selectLabel)
			selectLabel.innerHTML = labelStr;

		var dataStr = '';
		dataStr += '<select onchange="getAllElections(this.options[this.selectedIndex].value, this.options[this.selectedIndex].text)">';		
		for(var i in result)
		{
			/*if(result[i].name == 'Assembly'){
			dataStr += '<option value="'+result[i].id+'" selected="selected"> '+result[i].name+' </option>';
			getAllElections(result[i].id, result[i].name);
			}
			else*/
            dataStr += '<option value="'+result[i].id+'"> '+result[i].name+' </option>';
		}
		dataStr += '</select>';		
		if(result[0].name != 'Assembly')
			if(selectData)
				selectData.innerHTML = 	dataStr;	
		
		getAllElections(result[0].id, result[0].name);
	}

		function buildElectionTypesAndYearsGraph(results)
		{
		var elmt = document.getElementById("partyPositionsDiv");
		if(!elmt)
			return;

		var str = '';
		str+='<img	src="charts/'+results.pasitionsChart+'"/>';
		elmt.innerHTML=str;
	}
		function buildElectionTypesAndYears(results)
		{
		var elmt = document.getElementById("electionHirarchiDiv");
		if(!elmt)
			return;
		
		var str = '';
		str+='<table>';
		str+='<tr>';
		str+='<td>';
		str +='<span style="font-weight:bold;margin-right:10px;">Select Election</span>';
		str+='</td>';
		str+='<td>';
		str += '<select id="electionTypesSelect" onchange = "getPartiesPositions(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
		for(var i in results)
		{
			var localArr = results[i];
			if(localArr.length == 3)
				str += '<option value="'+localArr[0]+'">'+localArr[1]+'-'+localArr[2]+'</option>';
			
		}
		str += '</select>';
		str+='</td>';
		str+='<td>';
		str += '<span id="ajaxImageEl" style="margin-left:10px;display:none;"><img src="images/icons/search.gif"/></span>';
		str+='</td>';
		str+='</tr>';
		str+='</table>';
		elmt.innerHTML = str;
	
		var elmt = document.getElementById("electionTypesSelect");
		if(elmt)
		{
			var id = elmt.options[elmt.selectedIndex].value;
			var name = elmt.options[elmt.selectedIndex].text;
			getPartiesPositions(id,name);
		}
	}
	
		function AllMptcParties(results){
		assignToPartyDataArray = new Array();
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyMptcArray=assignToPartyDataArray;	
		}
	
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.partyMptcArray = emptyArr;				
		}
	    initializeMptcResultsTableForParty(); 
	}
	
		function showAllZptcParties(results){
		assignToPartyDataArray = new Array();
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyArray=assignToPartyDataArray;	
		}
	
		var emptyArr = new Array();
	    if(results.length == 0)
		{	tehsilDetails.partyArray = emptyArr;				
		}
	    initializeResultsTableForParty();
	}
	
		function redirectCandidateLink()
		{
		 //var browser1 = window.open("<s:url action="districtPageCandidateDetailsAjaxAction.action"/>?disId="+districtId+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear,"browser1","scrollbars=yes,height=630,width=1170,left=200,top=200");
		 var browser1 = window.open("districtPageCandidateDetailsAjaxAction.action?disId="+districtId+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear,"browser1","scrollbars=yes,height=630,width=1170,left=200,top=200");
		 browser1.focus();
	}
		function redirectMptcCandidateLink()
		{
		 //var browser2 = window.open("<s:url action="districtPageCandidateDetailsAjaxAction.action"/>?disId="+districtId+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear,"browser2","scrollbars=yes,height=630,width=1170,left=200,top=200");
		 var browser2 = window.open("districtPageCandidateDetailsAjaxAction.action?disId="+districtId+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear,"browser2","scrollbars=yes,height=630,width=1170,left=200,top=200");
		 browser2.focus();
	}
		function redirectMuncipalityCandidateLink(muncipalityId,latestMuncipalElectionYear,name){
		//var browser3 = window.open("<s:url action="muncipalElectionReportAction.action"/>?muncipalityId="+muncipalityId+"&muncipalityElectionType="+muncipalityElectionType+"&name="+name+"&muncipalityElectionId="+muncipalityElectionId+"&electionYear="+latestMuncipalElectionYear,"browser3","scrollbars=yes,height=670,width=1170,left=200,top=200");
		var browser3 = window.open("muncipalElectionReportAction.action?muncipalityId="+muncipalityId+"&muncipalityElectionType="+muncipalityElectionType+"&name="+name+"&muncipalityElectionId="+muncipalityElectionId+"&electionYear="+latestMuncipalElectionYear,"browser3","scrollbars=yes,height=670,width=1170,left=200,top=200");
		browser3.focus();
	}
		function redirectCorporationCandidateLink(corporationId,latestCorporationElectionYear,name){
		//var browser4 = window.open("<s:url action="muncipalElectionReportAction.action"/>?muncipalityId="+corporationId+"&muncipalityElectionType="+corporationElectionType+"&name="+name+"&muncipalityElectionId="+corporationElectionTypeId+"&electionYear="+latestCorporationElectionYear,"browser4","scrollbars=yes,height=670,width=1170,left=200,top=200");
		var browser4 = window.open("muncipalElectionReportAction.action?muncipalityId="+corporationId+"&muncipalityElectionType="+corporationElectionType+"&name="+name+"&muncipalityElectionId="+corporationElectionTypeId+"&electionYear="+latestCorporationElectionYear,"browser4","scrollbars=yes,height=670,width=1170,left=200,top=200");
		browser4.focus();
	}		
		function showAllPartyDetails(results)
		{
		var candLink = document.getElementById("candidateLink");
		var linkRef = '<a href="javascript:{}" onclick="redirectCandidateLink()"  style="text-decoration:none;color:#ffffff;background:#3897A5;" class="candidateDetailsStyle">Show Candidate Details</a>';
		candLink.innerHTML = linkRef;
		assignToPartyDataArray = new Array();
		totalZptcs = 0;
		for(var i in results)
		{		
			var problemObj=
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			totalZptcs = totalZptcs + results[i].seatsWonByParty;
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyArray=assignToPartyDataArray;	
		}
	
		var totalZptcCountDiv = document.getElementById("totalZptcCountResultDiv");
		var totalStr='';
		totalStr += '<b class="counterSize">'+totalZptcs+'</b>';
		totalZptcCountDiv.innerHTML = totalStr;	
		
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.partyArray = emptyArr;				
		}
	    initializeResultsTableForParty();  
	}

		function showAllMptcPartyDetails(results)
		{
		var candLink = document.getElementById("mptcCandidateLink");
		var linkRef = '<a href="javascript:{}" onclick="redirectMptcCandidateLink()" style="text-decoration:none;color:#ffffff;background:#3897A5;" class="candidateDetailsStyle" >Show Candidate Details</a>';
		candLink.innerHTML = linkRef;
		assignToPartyDataArray = new Array();
		totalMptcs = 0;
		for(var i in results)
		{		
			var problemObj=		
			 {		
					partyName:results[i].partyName,
					participatedSeats:results[i].participatedSeats,
					seatsWonByParty:results[i].seatsWonByParty,
					percentageOfVotesWonByParty:results[i].percentageOfVotesWonByParty				
			 };
			totalMptcs = totalMptcs + results[i].seatsWonByParty;
			assignToPartyDataArray.push(problemObj);
			tehsilDetails.partyMptcArray=assignToPartyDataArray;	
		}
	  	
		var totalMptcCountDiv = document.getElementById("totalMptcCountResultDiv");
		var totalStr='';
		totalStr += '<b class="counterSize">'+totalMptcs+'</b>';
		totalMptcCountDiv.innerHTML = totalStr;	
			 
		var emptyArr = new Array();
	    if(results.length == 0)
		{	
	    	tehsilDetails.partyMptcArray = emptyArr;				
		}
	    initializeMptcResultsTableForParty();  
	}
	
		function showAllYearsForZptc(results)
		{
		var selectedElmt = document.getElementById("zptcElectionYears");
		getPartyDetails(results[0].id);
		for(var i in results)
		{			
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
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

		function showAllYearsForMptc(results)
		{
		var selectedElmt = document.getElementById("mptcElectionYears");
		getMptcPartyDetails(results[0].id);
		for(var i in results)
		{			
			var opElmt=document.createElement('option');
			opElmt.value=results[i].id;
			opElmt.text=results[i].name;
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
	
		function getAllYearsForTeshil()
		{
		var jsObj=
		{		
				eleType:zptcElectionTypeId,
				task:"getAllElectionYears"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageElectionYearsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
		function getAllMptcYearsForTeshil()
		{
		var jsObj=
		{		
				eleType:mptcElectionTypeId,
				task:"getAllMptcElectionYears"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageElectionYearsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
		function getPartyDetails(id)
		{	
		selectedZptcYear = id;
		var ajaxImgElmt = document.getElementById("zptcAjaxLoadDiv");
		ajaxImgElmt.style.display = "block";
		zptcElectionYear = id;
		var jsObj=
		{		
				districtId:districtId,
				electionType:zptcElectionType,
				electionYear:id,
				task:"getPartyDetails"						
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPagePartyDetailsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
		function getMptcPartyDetails(id)
		{
		selectedMptcYear = id;
		var ajaxImgElmt = document.getElementById("mptcAjaxLoadDiv");
		ajaxImgElmt.style.display = "block";
		mptcElectionYear = id;		
		var jsObj=
		{		
				districtId:districtId,
				electionType:mptcElectionType,
				electionYear:id,
				task:"getMptcPartyDetails"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPagePartyDetailsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
		function getAllZptcParties(){
		
		var jsObj=
		{		
				electionTypeId:zptcElectionTypeId,
				districtId:districtId,
				electionType:zptcElectionType,
				electionYear:"null",
				task:"getAllZptcParties"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPagePartyDetailsAjaxAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	
		function getAllMptcParties(){
		var jsObj=
		{		
				electionTypeId:mptcElectionTypeId,
				districtId:districtId,
				electionType:mptcElectionType,
				electionYear:"null",
				task:"getAllMptcParties"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageMptcPartyDetailsAjaxAction.action?"+rparam;					
		callAjax(jsObj,url);
	}
		function hideZptcDiv(){
	     var zptcElmt = document.getElementById("zptcAjaxLoadDiv");
		 var zptcDiv="";
		 zptcElmt.innerHTML = zptcDiv;
		 
		 var candLink = document.getElementById("candidateLink");
		 var candidateLink="";
		 candLink.innerHTML = candidateLink;
		 
		 var zptcDetailsElmt = document.getElementById("partyDetails");
		 var zptcDetailsDiv="";
		 zptcDetailsDiv+="<br/>";
		 zptcDetailsDiv+='<p id="mptcZptcDiv">'+selectedZptcYear+' ZPTC data is not available.</p>';
		 zptcDetailsElmt.innerHTML = zptcDetailsDiv;
		 
		 var totalZptcCountResultDiv = document.getElementById("totalZptcCountResultDiv");
		 var totalZptcCountResult="";
		 totalZptcCountResultDiv.innerHTML = totalZptcCountResult;		 		

		 		 
	}
	
		function hideMptcDiv(){	 
		 var mptcElmt = document.getElementById("mptcAjaxLoadDiv");
		 var mptcDiv="";
		 mptcElmt.innerHTML = mptcDiv;
		 
		 var candLink = document.getElementById("mptcCandidateLink");
		 var candidateLink="";
		 candLink.innerHTML = candidateLink;
		 
		 var mptcElmt = document.getElementById("mptcPartyDetails");
		 var mptcDiv="";
		 mptcDiv+="<br/>";
		 mptcDiv+='<p id="mptcZptcDiv">'+selectedMptcYear+' MPTC data is not available.</p>';
		 mptcElmt.innerHTML = mptcDiv;
		 
		 var totalMptcCountResultDiv = document.getElementById("totalMptcCountResultDiv");
		 var totalMptcCountResult="";
		 totalMptcCountResultDiv.innerHTML = totalMptcCountResult;		 
	}
	
		function getAllZptcYears()
		{	 	 
		 var imgElmt = document.getElementById("zptcInfoDivBody");
		 var electionDetails="";
		 electionDetails+="Select a Election Year :";
		 electionDetails+='<select id="zptcElectionYears" class="selectWidth" list="result" theme="simple" onchange="getPartyDetails(this.value)"/>';
		 imgElmt.innerHTML = electionDetails;
		 
        var spanElmt=document.getElementById("zptcDetails");	
 
		getAllYearsForTeshil();
		if(zptcCount>1){
			getAllZptcParties();
		}		
		zptcCount++;	 
	}
		function getAllMptcYears()
		{	 
		 var imgElmt = document.getElementById("mptcInfoDivBody");
		 var electionDetails="";
		 electionDetails+="Select a Election Year :";
		 electionDetails+='<select id="mptcElectionYears" class="selectWidth" list="result" theme="simple" onchange="getMptcPartyDetails(this.value)"/>';
		 imgElmt.innerHTML = electionDetails;
        var spanElmt=document.getElementById("mptcDetails");  
		getAllMptcYearsForTeshil();
		if(mptcCount>1){
			getAllMptcParties();
		}			
		mptcCount++;	 
	}


		function showElectionGraph(chartResults)
		{
			var allElecDiv = document.getElementById("allElectionResultsInDT_body");
			var allElecDivImg = document.getElementById("barloaderImage");

			if(allElecDivImg)
			allElecDivImg.style.display = 'none';
		
		    var chartColumns = chartResults.result[0].result;
			var chartRows = chartResults.result;

			 var data = new google.visualization.DataTable();
			 var partiesArray = new Array();
			 data.addColumn('string', 'Party');

		     //for chart columns
			 for(var i in chartColumns)
			 {
				 if(chartColumns[i].partyName != null)
				 {
				   var colData = chartColumns[i].partyName;
				   data.addColumn('number', colData);
				   partiesArray.push(chartColumns[i].partyName);
				 }
			 }

			  //for chart rows
			  for(var j in chartRows)
			  {
				  var array = new Array();
				  var electionStr = chartRows[j].electionType+" "+ chartRows[j].electionYear;
				  array.push(electionStr);

				  for(var k in chartRows[j].result)
				  {
					  var percentage = chartRows[j].result[k].votesPercent;
		              array.push(percentage);
				  }
				 
				  data.addRow(array);
			  }

			   //static colors for parties
               var staticColors = setStaticColorsForInteractiveChartsForPartiesArray(partiesArray);
			   if(staticColors != null && staticColors.length > 0)
	           {
				  new google.visualization.LineChart(allElecDiv).
					  draw(data, {curveType: "function",width: 900, height: 350, pointSize: 4,title:"",colors:staticColors,legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:20}});
			   }else
		       {
				  new google.visualization.LineChart(allElecDiv).
					  draw(data, {curveType: "function",width: 900, height: 350, pointSize: 4,title:"",legend:"right",hAxis:{textStyle:{fontSize:11,fontName:"verdana"},slantedText:true,slantedTextAngle:20}});
			   }

	}
//ref
/*function showAllElectionsInDistrictHead(){
		
	var allElecDiv = document.getElementById("allElectionResultsInDT_head");
	var graphDivStr = '';
	graphDivStr += '<table>';
	graphDivStr += '<tr>';
	//graphDivStr += '<td><div style="margin-left:20px;"><input type="button" onclick="showAlliancePartiesWindow()" value="Know About Alliance Parties"></div></td>';
	graphDivStr += '<td><div id="graphElectionTypeLabel"></div></td>';
	graphDivStr += '<td><div id="graphElectionTypeSelect"></div></td>';		
	graphDivStr += '</tr>';
	graphDivStr += '<tr>';
	graphDivStr += '<td colspan="2" align="center"><img id="barloaderImage" src="images/icons/barloader.gif" /></td>';
	graphDivStr += '</tr>';
	graphDivStr += '</table>';	
	 allElecDiv.innerHTML = graphDivStr;
	 
	 getAllElectionScopes();
}*/
		

		function hideComparedResultsDiv()
		{
		var elmt = document.getElementById("detailedChartDiv");
		if(!elmt)
		return;
		
		elmt.style.display = 'none';
	
    }

	function showDetailedChart(chartName)
	{			
		var elmt = document.getElementById('detailedChartDIV');
		var divChild = document.createElement('div');
		divChild.setAttribute('id','createGroupmDiv');
	
	    var str='';
		str+='<img src="charts/'+chartName+'" />';
		divChild.innerHTML=str;
		elmt.appendChild(divChild);	
		if(createGroupDialog)
			createGroupDialog.destroy();
		createGroupDialog = new YAHOO.widget.Dialog("createGroupmDiv",
				{ width : "1000px", 		
	              fixedcenter : false, 
	              visible : true,  
	              constraintoviewport : true, 
				  iframe :true,
				  modal :true,
				  hideaftersubmit:true,
				  close:true,
				  x:800,
				  y:800
	             } );
		createGroupDialog.render();      
	}
	function handleCreateGroupSubmit()
	{
		createGroupDialog.hide();			
	}

	function handleCreateGroupCancel()
	{
		this.cancel();
	}
	function showAllElectionsInDistrictHead(){
		
		var allElecDiv = document.getElementById("allElectionResultsInDT_head");
		var graphDivStr = '';
		graphDivStr += '<table>';
		graphDivStr += '<tr>';
		//graphDivStr += '<td><div style="margin-left:20px;"><input type="button" onclick="showAlliancePartiesWindow()" value="Know About Alliance Parties"></div></td>';
		graphDivStr += '<td><div id="graphElectionTypeLabel" style="margin-top:15px;"></div></td>';
		graphDivStr += '<td><div id="graphElectionTypeSelect" style="margin-top:13px;"></div></td>';		
		graphDivStr += '</tr>';
		graphDivStr += '<tr>';
		graphDivStr += '<td colspan="2" align="center"><img id="barloaderImage" src="images/icons/barloader.gif" style="display:none;"/></td>';
		graphDivStr += '</tr>';
		graphDivStr += '</table>';	
		 allElecDiv.innerHTML = graphDivStr;
		 
		 getAllElectionScopes();
	}
	
	function getElectionTypesAndYears(){
		var jsObj=
		{		
				districtId:districtId,
				task:"getElectionTypesAndYears"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionsSelectInDistrictAction.action?"+rparam;					
		callAjax(jsObj,url);
	}
	
	function getPartiesPositions(id,value){

		var imgEl = document.getElementById("ajaxImageEl");	
		imgEl.style.display='block';
		var jsObj=
		{		electionId:id,
				electionTypeYear:value,
				districtId:districtId,
				districtName:"${districtName}",
				task:"getPartiesPositions"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllPartiesPositionsInDistrictAction.action?"+rparam;					
		callAjax(jsObj,url);
	}

	function showAlliancePartiesWindow(){
		//var brow1 = window.open("<s:url action="alliancePartiesPageAction"/>?districtId=${districtId}&districtName=${districtName}","brow1","width=600,height=400,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		var brow1 = window.open("alliancePartiesPageAction?districtId=${districtId}&districtName=${districtName}","brow1","width=600,height=400,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
		brow1.focus();
	}

	function buildGraphsCarousel(divId,arr)
	{
		var elmt = document.getElementById(divId);
		if(!elmt && arr.length == 0)
			return;

		var contentStr = '';
		contentStr+='<ul>';
		for(var i in arr)
		{				
			contentStr+='<LI style="width:1000px;height:300px;"><IMG src="charts/'+arr[i]+'"></IMG></LI>';		
		}
		contentStr+='</ul>';

		elmt.innerHTML = contentStr;

		graphImagesCarousel = new YAHOO.widget.Carousel(divId,
				{
					carouselEl: "UL",
					isCircular: true,
					isVertical: false,
					numVisible: 1,
					animation: { speed: 1.0 },
					autoPlayInterval: 1000
				});

		graphImagesCarousel.render(); 
		graphImagesCarousel.show();
	}

	function getAllElectionScopes(){

		var jsObj=
		{	    distId:districtId,
				task:"getAllElectionScopes"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/getAllElectionScopesSelectInDistrictAction.action?"+rparam;					
		callAjax(jsObj,url);
	}

	function getMuncipalPartyDetails(){
		var jsObj=
		{	
				electionType:muncipalityElectionType,
				districtId:districtId,
				task:"getmuncipalPartyDetails"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageLocalPartyDetailsAjaxAction.action?"+rparam;					
		callAjax(jsObj,url);
	}
	function getCorporationPartyDetails(){
		var jsObj=
		{	
				electionType:corporationElectionType,
				districtId:districtId,
				task:"getCorporationPartyDetails"						
		};
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "<%=request.getContextPath()%>/districtPageLocalPartyDetailsAjaxAction.action?"+rparam;					
		callAjax(jsObj,url);
	}
	
</script>
 
</head>
<body>
<div id="detailedChartDIV" class="yui-skin-sam"></div>
<div id="connectPeoplePopup_outer" class="yui-skin-sam">
	<div id="connectPeoplePopup" style="display:none;">
	 <div id="allConnectedUsersDisplay_main">
	   <img src="images/icons/barloader.gif"/>
	 </div></div>
</div>

<div class="clear"></div>

    <div class="main-title-sec">
        <div class="main-mbg">${districtName} DISTRICT DETAILS</div>

<span style="margin-top:10px;margin-right:18px;float:right">
<a name="fb_share" type="button_count" 
share_url="www.partyanalyst.com/districtPageAction.action?districtId=${districtId}">Share in Facebook</a> 
<script src="http://static.ak.fbcdn.net/connect.php/js/FB.Share" type="text/javascript"></script>
</span>
        <div class="main-bbg"></div>
      </div>
  <div id="inner-content-mainsec" style="margin-left:auto;margin-right:auto;float:none;">
		
		<div class="cd-left-sec" style="width:676px;background:#ffffff;">
        <div class="cd-mid-cont-sec" style="padding-bottom:9px;">

     
          <p class="pa-fi" style="font-weight:bold;">STATE: <strong></strong><a style="color:#4169E1;text-decoration: none;font-weight:bold;font-sie:15px;"; href="statePageAction.action?stateId=${navigationVO.stateInfo.id}">${stateDetails.name}</a> </p>

		<div class="cl-sub-fields-sec"> <h1 class="org-title"><span style="-moz-border-radius:4px;
    ;">Constituencies In ${districtName} District </span></h1><br><br>
		
		

	<div id="districtMap_body">	
		      <c:if test="${stateDetails.name == 'Andhra Pradesh' || stateDetails.name == 'Tamil Nadu' || stateDetails.name == 'Kerala' || stateDetails.name == 'Puducherry' || stateDetails.name == 'West Bengal' || stateDetails.name == 'Assam' || stateDetails.name == 'Karnataka' || stateDetails.name == 'Uttar Pradesh' || stateDetails.name == 'Gujarat' || stateDetails.name == 'Punjab'}">
				<object width="550" height="430">
					<param name="movie" value="images/districtMaps/${stateDetails.name}/${districtName}.swf">
					 <param name="wmode" value="transparent"> 
					<embed wmode="transparent" src="images/districtMaps/${stateDetails.name}/${districtName}.swf" width="550" height="430">
					</embed>
				</object>
			   </c:if>
				</div>
</div>
	

		
		
		


		<div class="cl-sub-fields-sec" style="width:300px;float:right;padding-top:0px;"><h1 class="org-title"><span style="-moz-border-radius:4px;">New AC's in Delimitation ${constituenciesStatusVO.delimitationYear}</span></h1><br><br>
		
		
		<div id="newConstDiv_body" style="margin-top:-15x;">				
							<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.newConstituencies}">
								<div id="newConstAncSpan" class="mandalNamesDiv">		
								<table>
									<tr>
									<td> <img width="7" height="5" src="images/icons/districtPage/listIcon.png"/></td>
									<td><span class="mandalNameSpan">
										<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}"> ${result.name}
										</a></span>
									</td>
									</tr>
								</table>
							</div>	
							</c:forEach>						
						</div>
		</div>
		
		<div class="cl-sub-fields-sec" style="width:300px;padding-top:20px;"><h1 class="org-title"><span style="-moz-border-radius:4px;">Dissolved AC's in Delimitation ${constituenciesStatusVO.delimitationYear}</span></h1><br><br>

		<div id="delimitMandalsDiv_body" style="margin-top:-45px;">
						<c:forEach var="result" varStatus="stat" items="${constituenciesStatusVO.deletedConstituencies}">			
						<div id="mandalAncSpan" class="mandalNamesDiv">							
							<table>
								<tr>
								<td> <img width="7" height="5" src="images/icons/districtPage/listIcon.png"/></td>
								<td><span class="mandalNameSpan">
									<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${result.id}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${result.name}
									</a></span>
								</td>
								</tr>
							</table>
						</div>			
						</c:forEach>			
					</div>
			</div>


			
	

							<div class="cl-sub-fields-sec">
								<h1 class="org-title"><span style="-moz-border-radius:4px;">Connect To Your District People</span></h1><br><br>
										

		
				
									
	<div id="districtPeopleConnect_body" style="float: left; clear: both;">
						
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
				  <tr>
				  
					  <td width="45%"><div id="districtPeopleConnect_img"><img src="images/usergroups/group2.jpg"/></div></td>
				  
					  <td width="55%"><div id="districtPeopleConnectData_body" class="view-all"></div></td>
				  </tr>
				</table>
			</div>
							</div>
					

	</div>
</div>	
	<!--left section End-->


 <!--CD SUB RIGHT SECTION START-->
        
        <div class="cd-sub-right-sec" style="border-top-width:0px;float:left;">
     
            
            <!--CONNECT PEOPLE SECTION START-->
            
            <div class="connect-people-sec" style="background-color:#ffffff;">
              <div class="cp-title-sec" style="margin-top:40px;" >
                <h1 class="cp-title">${districtName} District News</h1>
               </div>
              
               <div id="district_Politician_news" class="productFeatureBody" style="overflow:hidden;width:300px;height:250px;-moz-border-radius: 3px 3px 3px 3px;"></div>




</div>




<!--VIEW YOUR PROBLEMS SECTION START-->
        
        <div class="vy-problems-sec">
          <h1 class="vyp-title"> <img src="images/view-your-problems-district.png" alt=""/></h1>
        <div class="vy-fields-sec" style="height:100px;">

		<div id="problemViewingDiv" style="margin-top:10px;">
			<div id="problemViewingDiv_Head"></div>
			<div id="problemViewingDiv_Body"></div>
		</div>
        <div class="post-problem" style="top:148px;">
              <div>Post your constituency problem and<br />
                bring it to the all people notice.</div>
				
				
				<div id="problemPostingDiv">
			<div id="problemPostingDiv_Head"></div>
			<div id="problemPostingDiv_Body"></div>
			
								
							</div> </div>
        </div>
        
        <!--VIEW YOUR PROBLEMS SECTION END-->
		<div class="connect-people-sec" style="padding-top:1px;margin-top:78px;">
			<div class="cp-title-sec" style="-moz-border-radius:3px;margin-top:0px;">
                <h1 class="cp-title">Know Your Mandals/Tehsils</h1>
               </div>


			   <div id="mandalsDiv_body" style="height:375px;">
				<c:forEach var="mandalsBeforeDelimitationConstituency" varStatus="stat" items="${mandals}">						
						<div id="mandalAncSpan" class="mandalNamesDiv">							
							<table>
								<tr>
								<td> <img width="7" height="5" src="images/icons/districtPage/listIcon.png"/></td>
								<td><span class="mandalNameSpan">
									<a href="mandalPageElectionInfoAction.action?MANDAL_ID=${mandalsBeforeDelimitationConstituency.id}&MANDAL_NAME=${mandalsBeforeDelimitationConstituency.name}" class="districtAnc">${mandalsBeforeDelimitationConstituency.name}
									</a></span>
								</td>
								</tr>
							</table>
						</div>					
				</c:forEach>	
				</div>			

				 </div>


				



<!--
           
			<div class="cl-sub-fields-sec" style="width:308px;">
			
         <div id="problemViewingDiv" style="margin-top:10px;">
			<div id="problemViewingDiv_Head"></div>
			<div id="problemViewingDiv_Body"></div>
		</div>
	</div>-->
            <!--CONNECT PEOPLE SECTION END--> 

	
			
		<!--<div class="cl-sub-fields-sec" style="width:307px;">
			
			   <div id="problemPostingDiv">
			<div id="problemPostingDiv_Head"></div>
			<div id="problemPostingDiv_Body"></div>
		</div>
		




</div>-->

</div>
</div></div>
<!--Right End-->

<div id="detailedChartDiv" style="width:1000px;background:#ffffff; padding-bottom:20px;margin-left:auto;margin-right:auto;float:none;"> 
	
	<div id="partiesPerformanceGraphDistrict">
		<div id="alliancePartiesCarousel" class="yui-skin-sam" style="float:left;clear: both;margin-left:1px;background:#ffffff;">
			<ul>
			<li>
			<h3 style="margin-top:15px;font-weight:bold;font-family:verdana;">All Parties Performance In Assembly-${electionYear} Of  ${districtName} District</h3>
				<div id="allElectionResultsInDT"  class="allianceListDiv" style="margin-top:-34px;">
					<div id="allElectionResultsInDT_head" style="clear:both;"></div>
					<div id="allElectionResultsInDT_body"></div>
				
				</div>
			</li>
			<li>
				<div id="positionsGraphDiv" class="allianceListDiv">
					<center><div id="electionHirarchiDiv" ></div></center>
					<div id="partyPositionsDiv"></div>
				</div>
			</li>
			</ul>
		</div>
	</div>
	
<div style="width:1000px;background:#ffffff; padding-bottom:20px;margin-left:auto;margin-right:auto;float:none;">
<div id="mp_head" style="clear:both;background:none;padding-top:30px">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
					<td><div id="districtProblemsMgmtBodyDiv" class="yui-skin-sam"><div id="moreDetailsPanelDiv"></div></div></td>
						<!--<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif" style="margin-left:52px;"/></td>
						<td>-->
							<div id="mpInfoDivHead" class="districtPageRoundedHeaders_center" style="width:973px;-moz-border-radius:6px;padding-bottom:7px;background:#72CAED;">
								<span style="float:left;color:#ffffff;font-size:14px;">Member of Parliament (MP) in the  ${districtName} District</span>
							</div></td>
						
						<!--<td><img height="36" width="6" src="images/icons/districtPage/header_right.gif" style="clear:both;margin-right:56px;"></td>-->
					</tr>
				</table>
			</div>


<div id="mp_body"  style="background:#ffffff;">
				<div id="mpsInfoDivBody">
					<table id="mpsDataSortingTable">			
						<c:forEach var="mpsDetails" varStatus="stat" items="${parliamentCandidateDetailsVo.candidateDetails}">			
							<tr>
								<td>
								<span id="districtAncSpan">
										<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${mpsDetails.constituencyId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${mpsDetails.constituencyName}										
											<c:if test="${mpsDetails.reservationZone != ''}">
													<b style="color:green;">( ${mpsDetails.reservationZone} )</b>
											</c:if>	
										</a>
									</span>
								</td>								
								<td>
									<span id="districtAncSpan">
											<a href="candidateElectionResultsAction.action?candidateId=${mpsDetails.candidateId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${mpsDetails.candidateName}
											</a>
									</span>
								</td>
								<td>	
									<img src="<%=request.getContextPath()%>/images/party_flags/${mpsDetails.partyFlag}" height="30" width="40"/>
								</td>
								<td> <a href="javascript:{}" onclick="getConstituencyElecResultsWindow('${mpsDetails.constituencyId}','Parliament','${mpsDetails.electionYear}')">view results</a>
								</td>
							</tr>  
						</c:forEach>
					</table>		
				</div>
			</div>
	
</div>
</div>

	

     <div style="width:1000px;background:#ffffff; padding-bottom:20px;margin-left:auto;margin-right:auto;float:none;">

<div id="mla_main_div" style="background:#ffffff;width:100%;">
			<div id="mla_head" style="clear:both;padding-top:24px;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<!--<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif" style="margin-left:0px;"/></td>
						<td>	-->
							<div id="mlaInfoDivHead" class="districtPageRoundedHeaders_center" style="width:972px;-moz-border-radius:6px;padding-bottom:7px;background:#72CAED;margin-top:-47px;">
								<span style="float:left;color:#ffffff;">Member of Legislative Assembly (MLA) in the  ${districtName} District</span>
							</div>
						</td>
						<!--<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"style="margin-left:0px;"/></td>-->
					</tr>
				</table>
			</div>
			
<div id="mla_body" style="background:#ffffff;margin-top:-14px;">				
				<div id="mlaInfoDivBody">
					<table  id="mlaDataSortingTable">						
						<c:forEach var="candidate" varStatus="stat" items="${constituenciesStatusVO.constituencyWinnerInfoVO}">			
							<tr>
								<td>
									<span id="districtAncSpan">
										<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${candidate.constituencyId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.constituencyName} 
											<c:if test="${candidate.reservationZone != ''}">
												<b style="color:green;"> ( ${candidate.reservationZone} )</b>
											</c:if>	
										</a>
									</span>
								</td>
								<td>
								<span id="districtAncSpan">
										<a href="candidateElectionResultsAction.action?candidateId=${candidate.candidateId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.candidateName}
										</a>
									</span>
								 &nbsp </td>
								<td><img src="<%=request.getContextPath()%>/images/party_flags/${candidate.partyFlag}" height="30" width="40"/></td>


								<td>
									<span id="districtAncSpan">
										<a href="constituencyPageAction.action?districtId=${districtId}&constituencyId=${candidate.parliamentConstituencyId}" class="districtAnc" style="text-decoration:none;" onmouseover="javascript:{this.style.textDecoration='underline';}" onmouseout="javascript:{this.style.textDecoration='none';}">${candidate.parliamentConstituencyName}										 		
										</a>
									</span>
								</td>									
								<td>
								<a href="javascript:{}" onclick="getConstituencyElecResultsWindow('${candidate.constituencyId}','${constituenciesStatusVO.electionType}','${candidate.electionYear}')">view results</a>
							</td>
							</tr>  
						</c:forEach>
					</table>		
				</div>
		</div>
		
		  <div style="width:1000px;background:#ffffff;margin-left:auto;margin-right:auto;float:none;">
<div id="muncipality_corporation_div_main" style="margin-top:14px;width:100%;">
		<table width="100%" id="table1">
			<tr>
				<td>
				
					<div id="corporationDivHead" style="text-align:left;cursor:pointer;" onclick="hideCorporationDiv()"></div>
					
				
					<div id="corporationDiv" style="text-align:left;border-bottom:1px solid #E0E0D6;border-left:1px solid #E0E0D6;border-right:1px solid #E0E0D6;height:auto;overflow:auto;padding:15px;background:#ffffff;width:960px;">
						
					</div>
				<!--<div id="CorporationPieChartDiv" style="float:right;"></div>-->

				<div id="CorporationPieChartDiv"  style="position: relative; width: 350px; height: 300px; top: 6px; right: 0px; left: 510px; bottom: 0px; margin-bottom: 0px; clear: both; margin-top: -263px;"></div>
					</td>
				
				
			</tr>
			<tr>
				<td align="left" width:50%;>
					<div id="muncipalitiesDivHead" style="text-align:left;" onclick="hideMuncipalitiesDiv()"></div>
					<div id="muncipalitiesDiv" style="background:#ffffff;width:98%;"></div>
				</td>
			</tr>
		</table>
	</div>
	
</div>

<!--District Page MPTC, ZPTC Div-->
 
  <div style="width:1000px;background:#ffffff;margin-left:auto;margin-right:auto;float:none;">

	<div id="mptc_zptc_div_main" style="margin-bottom:20px;">
		<table id="table2" style="width: 100%;">
			<tr>
				<td>
					<div id="zptc_main">
						<div id="zptc_head" style="color:#ffffff;">
							<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<!--<td width="30px"><img width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
									<td>-->	
										<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:506px;padding:9px;height:18px;-moz-border-radius:4px;padding-bottom:7px;margin-top:12px;background:#72CAED;">
											<span>Total Number of ZPTC's : </span>
											<span id="totalZptcCountResultDiv"></span>
										</div>
									</td>
									<!--<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>-->
								</tr>
							</table>
						</div>
						<div id="zptc_body" style="background:#ffffff;">
							<div id="zptcDiv"  style="margin-top:2px;">
								<table>
										<td><div id="zptcInfoDivBody" style="font-weight:bold;color:#000000;margin-top:13px;margin-left:15px;"></div></td>
										<td><div id="zptcAjaxLoadDiv" style="display:none;">
											<img id="ajaxImg" height="18" width="19" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
										</div></td><td><div id="candidateLink" style="margin-top:13px;"></div></td>
										</table>
										<table cellpadding="5px">
										<tr><td><div id="partyDetails"></div></td>
										</tr>
								</table>
							</div>	
						</div>
					</div>
				</td>
				<td>
					<div id="mptc_main">
						<div id="mptc_head"> 
							<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
								<tr>
									<!--<td width="30px"><img  width="30" height="36" src="images/icons/districtPage/header_left.gif"/></td>
									<td>	-->
										<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:447px;padding:9px;height:18px;-moz-border-radius:4px;padding-bottom:7px;margin-top:12px;background:#72CAED;">
											<span style="color:#ffffff;">Total Number of MPTC's : </span>
											<span id="totalMptcCountResultDiv"></span>
										</div>
									</td>
									<!--<td><img width="5" height="36" src="images/icons/districtPage/header_right.gif"/></td>-->
								</tr>
							</table>
						</div>
						<div id="mptc_body" style="background:#ffffff;margin-top:-8px;">
							<div id="mptcDiv" style="margin-top: 10px;">
									<table>
										<td><br/></td>
										<td><div id="mptcInfoDivBody" style="font-weight:bold;color:#000000;margin-top:13px;margin-left:15px;"></div></td>
										<td><div id="mptcAjaxLoadDiv" style="display:none;">
											<img id="ajaxImg" height="18" width="19" src="<%=request.getContextPath()%>/images/icons/search.gif"/>			
										</div></td><td><div id="mptcCandidateLink" style="margin-top:13px;"></div></td>
										</table>
										<table cellpadding="5px">
										<tr><td><div id="mptcPartyDetails"></div></td>
										</tr>
									</table>
							</div>
						</div>
					</div>		
				</td>
			</tr>
		</table>
	</div>	

</div>


</div>
</div>


	</div>

<script language="javascript">



var allianceCarousel = new YAHOO.widget.Carousel("alliancePartiesCarousel",
			{
				carouselEl: "UL",
				isCircular: true,
				isVertical: false,
				numVisible: 1,
				animation: { speed: 1.0 },
				autoPlayInterval: 2000,

			});

	allianceCarousel.render(); 
	allianceCarousel.show();
 
 <c:forEach var="candidate" varStatus="stat" items="${constituenciesStatusVO.constituencyWinnerInfoVO}">
		districtMlas.push('${candidate.candidateName}');
 </c:forEach>

 <c:forEach var="mpsDetails" varStatus="stat" items="${parliamentCandidateDetailsVo.candidateDetails}">
		districtMps.push('${mpsDetails.candidateName}');
 </c:forEach>

<c:forEach var="problem" items="${problemBean}">	

    var probDesc = "${problem.description}";
    probDesc = probDesc.replace("&#39;","&#92;&#39;");

	var problemObj={
						problemId:'${problem.problemId}',
						problem:'${problem.problem}',
						//description:'${problem.description}',
						description:probDesc,
						state:'${problem.state}',
						district:'${problem.district}',
						constituency:'${problem.constituency}',
						tehsil:'${problem.tehsil}',
						village:'${problem.village}',
						hamlet:'${problem.hamlet}',
						reportedDate:'${problem.reportedDate}',
						postedDate:'${problem.postedDate}',
						existingFrom:'${problem.existingFrom}',
						name:'${problem.name}',
						postedPersonName:'${problem.postedPersonName}',
						email:'${problem.email}',						
						phone:'${problem.phone}',
						mobile:'${problem.mobile}',
						address:'${problem.address}',
						problemLocationId:'${problem.problemLocationId}',
						problemHistoryId: '${problem.problemHistoryId}',
						acceptedCount: '${problem.acceptedCount}',
						rejectedCount: '${problem.rejectedCount}'
					};
		
	problemsInfo.push(problemObj);
</c:forEach>

<c:forEach var="candidate" items="${userDetails.candidateVO}">	
	var userObj={
						id:'${candidate.id}',
						candidateName:'${candidate.candidateName}',
						status:'${candidate.status}',
						constituencyName:'${candidate.constituencyName}',
						image:'${candidate.image}'
					};
		
	connectedPeople.push(userObj);
</c:forEach>

<c:forEach var="constituency" varStatus="stat" items="${constituenciesStatusVO.constituencyWinnerInfoVO}">
	var obj =	{
					id:'${constituency.constituencyId}',
					name:'${constituency.constituencyName}'
				};
	constituencies.push(obj);
</c:forEach>

<c:forEach var="status" varStatus="stat" items="${messageTypes.messageTypes}">
	var obj =	{
					id:'${status.id}',
					name:'${status.name}'
				};
	connectStatus.push(obj);
</c:forEach>

userLoginStatus = '${userDetails.loginStatus}';
userId = '${userDetails.userId}';
forwardTask = "${redirectLoc}";
function initializeDistrictPage()
{
	buildDistrictLevelProblemWindow();
	buildDistrictConnectPeopleWindow();
	buildDistrictLatestNews();
	
	buildProblemPostingWindowForDistrict();
	initializeResultsTableForMp();
	initializeResultsTable();
	getMuncipalPartyDetails();

	getCorporationPartyDetails();
	
	getAllZptcYears();
	getAllMptcYears();
	
	
	showAllElectionsInDistrictHead();
	getElectionTypesAndYears();
}

initializeDistrictPage();

if(forwardTask != null)
{
	if(forwardTask != "")
	{
      openAddNewProblemWindow();
	}
}

//getMuncipalPartyDetails();

//getCorporationPartyDetails();


buildProblemPostingWindowForDistrict();
</script>
</body>
</html>