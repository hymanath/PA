<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Trends</title>

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



	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

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

	<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
	<link rel="stylesheet" type="text/css" href="styles/statePage/statePage.css">
	<link rel="stylesheet" type="text/css" href="styles/indexPage/indexPage.css">
	<link rel="stylesheet" type="text/css" href="styles/biElectionPage/biElectionPage.css">

	<script type="text/javascript" src="js/constituencyPage/constituencyPage.js"></script>
    <style type="text/css">
	
    </style>
	<script  type="text/javascript">
		var districtsInfo = new Array();
		var localizationObj = '';
		var assemblyElectionType='Assembly';
		var constituencyIdGlobal = '${constiId}';
		var allPartiesCharts = '';
		var totalZptcSeats,totalMptcSeats;
		var tehsilDetails={
				zptcArray:[],
				mptcArray:[],
				partyArray:[],
				partyMptcArray:[]
		};
		var tehsilElections={
				zptcElectionYears:[],
				mptcElectionYears:[]
		};
		var mptcElectionType="${mptcElectionType}",zptcElectionType="${zptcElectionType}";

		
		var docOpener = window.opener;
		localizationObj = docOpener.localizationObj;

		<c:forEach var="district" items="${districtsAndConsts}">
			var districtObj={
									districtName:"${district.districtName}",
									districtId:"${district.districtId}",
									constituencies:[]								
							};
				
			<c:forEach var="constituencies" items="${district.constituenciesList}">
					var cObj =	{
									constId:"${constituencies.id}",
									constName:"${constituencies.name}"
								};
					districtObj.constituencies.push(cObj);			
			</c:forEach>
			districtsInfo.push(districtObj);	
		</c:forEach>

			<c:forEach var="zptcElectionYears"  items="${zptcElectionYears}" >
				var ob={
							id:'${zptcElectionYears.id}',
							value:'${zptcElectionYears.name}'
						};
				tehsilElections.zptcElectionYears.push(ob);	
				</c:forEach>

				<c:forEach var="mptcElectionYears"  items="${mptcElectionYears}" >
				var ob={
							id:'${mptcElectionYears.id}',
							value:'${mptcElectionYears.name}'
						};
				tehsilElections.mptcElectionYears.push(ob);	
			</c:forEach>

		function callAjax(jsObj,url)
		{					
			var callback = {			
						   success : function( o ) {
								try {
										myResults = YAHOO.lang.JSON.parse(o.responseText);	
										
										if(jsObj.task == "getMandalVotingTrendz")
										{
											buildMandalVotingTrendzData(jsObj,myResults);
										}
										else if(jsObj.task == "getZptcElectionResults")
										{		
											if(myResults!= null &&  myResults.length>0){
												buildZptcResults(myResults);	
											}else{
												hideZptcDiv();			
											}	
										}
										else if(jsObj.task == "getMptcElectionResults")
										{		
											if(myResults!= null &&  myResults.length>0){
												buildMptcResults(myResults);
											}else{
												hideMptcDiv();			
											}	
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
		

		function getZptcPartyDetails(elecYear){
			zptcElectionYear = elecYear;
			constituencyTYPE = assemblyElectionType;
			var jsObj = {
					constituencyType: assemblyElectionType,
					constituencyId:constituencyIdGlobal,
					electionYear:elecYear,
					task:"getZptcElectionResults"
				};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
			callAjax(jsObj, url);
		}

		function getMptcPartyDetails(elecYear){
			mptcElectionYear = elecYear;
			var jsObj = {
					constituencyType: assemblyElectionType,
					constituencyId: constituencyIdGlobal,
					electionYear:elecYear,
					task:"getMptcElectionResults"
				};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/constituencyWiseMandalElectionsResultAction.action?"+rparam;
			callAjax(jsObj, url);
		}

		function redirectZptcCandidateLink(){												
			 var browser1 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyIdGlobal+"&eleType="+zptcElectionType+"&eleYear="+zptcElectionYear+"&constTYPE="+constituencyTYPE,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser1.focus();
		}

		function redirectMptcCandidateLink(){
			 var browser2 = window.open("<s:url action="constituencyPageCandidateDetailsAjaxAction.action"/>?constId="+constituencyIdGlobal+"&eleType="+mptcElectionType+"&eleYear="+mptcElectionYear+"&constTYPE="+constituencyTYPE,"browser2","scrollbars=yes,height=630,width=1020,left=200,top=200");
			 browser2.focus();
		}

		function getMandalVotingTrendz(distId,constId,constName)
		{
			var jsObj=
			{
					districtId:distId,
					constituencyId:constId, 
					constiName:constName,
					task:"getMandalVotingTrendz"
			}
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/getMandalsVotingTrendzForBiElection.action?"+rparam;

			callAjax(jsObj, url);
		}
		
		function setValuesForMandalVotingTrendz(value,text)
		{
			var radioValue = '';
			var cursorImgElmt = document.getElementById('cursorImg');
			if(cursorImgElmt)
				cursorImgElmt.style.display = 'block';

			var elements = document.getElementsByTagName('input'); 

			for(var i=0;i<elements.length;i++)
			{
				if(elements[i].type=="radio" && elements[i].name=="districtRadio" && elements[i].checked==true)
					radioValue = elements[i].value;
			}
			
			getMandalVotingTrendz(radioValue,value,text);
			constituencyIdGlobal = value;
			getZptcPartyDetails(tehsilElections.zptcElectionYears[0].value);
			getMptcPartyDetails(tehsilElections.mptcElectionYears[0].value);
		}

		function getConstituenciesInfo(distId,index)
		{
			var obj = districtsInfo[index];

			var elmt = document.getElementById("constSelectElmt");
			if(!elmt)
				return;

			var str = '';
			str += '<select onchange="setValuesForMandalVotingTrendz(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
			str += '<option value="0">Select</option>';
			for(var j in obj.constituencies)
			{
				str += '<option value="'+obj.constituencies[j].constId+'"> '+obj.constituencies[j].constName+' </option>';
			}	
			str += '</select>';
			
			elmt.innerHTML = str;
		}
		
		function openwin(mandalId,name, electionType,electionYear,electionId){
			
			var brow1 = window.open("<s:url action="townshipElectionResultsAction"/>?mandalId="+mandalId+"&electionId="+electionId+"&mandalName="+name+"&electionType="+electionType+"&electionYear="+electionYear+"&windowTask=includeVotingTrendz","brow1","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			brow1.focus();
		}

		function buildMandalsVotingTrendz()
		{
			var distObj = null;
			var elmt = document.getElementById("votingTrendzInfoMain");

			if(!elmt)
				return;

			var str='';
			str += '<div id="districtsInfoRadioElmtDiv" style="margin-top:10px;">';
			str += '<table width="100%">';
			str += '<tr>';
			str += '<th>';
			str += 'Select District : ';
			str += '</th>';
			str += '<td>';
			for(var i in districtsInfo)
			{
				if(districtsInfo[i].districtId == ${districtId})
				{
					str += '<input type="radio" checked="checked" name="districtRadio" value="'+districtsInfo[i].districtId+'" onclick="getConstituenciesInfo(this.value,\''+i+'\')"/> '+districtsInfo[i].districtName;
					distObj = districtsInfo[i];
				}
				else
					str += '<input type="radio" name="districtRadio" value="'+districtsInfo[i].districtId+'" onclick="getConstituenciesInfo(this.value,\''+i+'\')"/> '+districtsInfo[i].districtName;

			}
			str += '</td>';
			str += '</tr>';
			str += '</table>';
			str += '</div>';
			
			str += '<div id="constituenciesInfoSelectElmtDiv">';
			if(distObj)
			{
				str += '<table>';
				str += '<tr>';
				str += '<th>';
				str += 'Select Constituency : ';
				str += '</th>';
				str += '<td>';
				str += '<div id="constSelectElmt"><select onchange="setValuesForMandalVotingTrendz(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
				for(var j in distObj.constituencies)
				{
					if(distObj.constituencies[j].constId == ${constiId})
						str += '<option value="'+distObj.constituencies[j].constId+'" selected="selected"> '+distObj.constituencies[j].constName+' </option>';
					else
						str += '<option value="'+distObj.constituencies[j].constId+'"> '+distObj.constituencies[j].constName+' </option>';
				}	
				str += '</select></div>';
				str += '</td>';
				str += '<td><img id="cursorImg" style="display:none;" src="images/icons/search.gif"/></td>';	
				str += '</table>';
			}
			str += '<div id="mandalVotingTrendzDataDiv">';
			str += '<div id="mandalVotingTrendzDataDiv_head">Mandal Voting Trendz</div>';
			str += '<div id="mandalVotingTrendzDataDiv_body" class="yui-skin-sam">';
			str += '	<div id="allPartiesResultsChartsPanel"></div>';
			str += '	<div id="mandalsListInConstituency"></div>';	
			str += '	<div id="mandalDetailsChart_body">';
			str += '	<div style="margin-top:5px;margin-left:300px;">';
			str += '		<div style="color:#ADADAD"> Loading ...</div>';
			str += '		<div> <img src="images/icons/barloader.gif"></img> </div>';
			str += '	</div>';
			str += '	</div>';	
			str += '	<div id="mandalVotingTrendzData"></div>';	
			
			str += '</div>';
			str += '</div>';
			elmt.innerHTML = str;

			getMandalVotingTrendz('${districtId}','${constiId}','${constiName}');
		}

		function displayAllPartiesChart()
		{			
			var graphStr = '';
			graphStr += '<HTML><HEAD><TITLE>All Parties Election Results in ${constiName} Constituency</TITLE></HEAD>';
			graphStr += '<body>';
			graphStr += '<table>';
			graphStr += '<tr>';
			for(var graph in allPartiesCharts)
			{
				graphStr += '<td>';
				graphStr += '<img src="charts/'+allPartiesCharts[graph]+'"/>';
				graphStr += '</td>';

				if(graph == 0)
					continue;
				if(graph % 3 == 0)
					graphStr += '</tr><tr>';
			}
			graphStr += '</tr>';
			graphStr += '</table>';
			graphStr += '</BODY></HTML>';
				
				

			var allPartiesChartsWindow = window.open("","allPartiesChartsWindow","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
			allPartiesChartsWindow.focus();
			allPartiesChartsWindow.document.open("text/html", "replace");
			allPartiesChartsWindow.document.write(graphStr);			
			allPartiesChartsWindow.document.close();

			/*var allPartiesPanel = new YAHOO.widget.Panel("allPartiesResultsChartsPanel", {
                 width: "800", 
                 fixedcenter: false, 
                 constraintoviewport: false, 
                 underlay: "none", 
                 close: true, 
                 visible: true, 
				 x:'200',
				 y:'800',
                 draggable: true
			   });
			allPartiesPanel.setHeader("All Parties Results");
			allPartiesPanel.setBody(graphStr);
			allPartiesPanel.render();*/
		}

		function buildMandalVotingTrendzData(jsObj,resultsData)
		{
			
			var headElmt = document.getElementById("mandalVotingTrendzDataDiv_head");
			var bodyElmt = document.getElementById("mandalVotingTrendzData");
			var graphElmt = document.getElementById("mandalDetailsChart_body");
			var mandalsListElmt = document.getElementById("mandalsListInConstituency");

			var results = resultsData.biElectionResultsMainVO;
			allPartiesCharts = resultsData.allPartiesElectionResultsChart;

			if(!headElmt || !bodyElmt || !resultsData || !graphElmt || !mandalsListElmt)
				return;
			
			//Hiding busy cursor Image
			var cursorImgElmt = document.getElementById('cursorImg');
			if(cursorImgElmt)
				cursorImgElmt.style.display = 'none';

			// Rendering Graph Elmt in the respective div
			var graphStr = '';
			graphStr += '<center>';
			//graphStr += '<img height="300" width="820" src="charts/'+resultsData.mandalWiseResultsChart+'"/>';
			graphStr += '<fieldset>';
			graphStr += '<legend>Election Results in '+jsObj.constiName+'</legend>';
			graphStr += '<div style="text-align:right"><span style="background-color:#4B74C6;cursor:pointer;font-weight:bold;padding:3px;" onclick="displayAllPartiesChart()"> View All Parties Chart </span></div>';
			graphStr += '<table>';
			graphStr += '<tr>';
			for(var graph in resultsData.electionResultsChart)
			{
				graphStr += '<td>';
				graphStr += '<img src="charts/'+resultsData.electionResultsChart[graph]+'"/>';
				graphStr += '</td>';

				if(graph == 0)
					continue;
				if(graph % 3 == 0)
					graphStr += '</tr><tr>';
			}
			graphStr += '</tr>';
			graphStr += '</table>';
			graphStr += '</fieldset>';

			graphStr += '<P style="font-family:verdana;font-size:12px;margin-top:25px;">'+localizationObj.desc1+' <font style="font-weight:bold;color:#4B74C6;">'+jsObj.constiName+'</font> '+localizationObj.desc2+'</P></center>';
			graphElmt.innerHTML = graphStr;

			//Rendering  mandals list in the constituency
			mandalStr = '';
			mandalStr += '<fieldset>';
			mandalStr += '<legend> Mandals In '+jsObj.constiName+' Constituency </legend>';
			mandalStr += '<table>';
			mandalStr += '<tr>';
			mandalStr += '<th> <u>Mandals </u>: </th>';
			for(var mandal in results[0].biElectionResultsVO[0].electionResultsForMandal)
			{
				var mandalData = results[0].biElectionResultsVO[0].electionResultsForMandal[mandal];
				mandalStr += '<td><div class="mandalNameDivClass"><a href="mandalPageElectionInfoAction.action?MANDAL_ID='+mandalData.mandalId+'&MANDAL_NAME='+mandalData.mandalName+'"> '+mandalData.mandalName+'</a></div></td>';		
			}
			mandalStr += '</tr>';
			mandalStr += '</table>';
			mandalStr += '<div id="constiVotersDetails" style="margin-top:20px;">';
			mandalStr += '<div style="padding:10px;margin-bottom:20px;"> <u><b> Mandals Votes Share In Constituency :</b> </u> </div>';
			mandalStr += '<center><table width="85%" border="1" cellspacing="3">';
			mandalStr += '	<tr>';
			for(var chart in resultsData.constituencyVO.pieChartNames)
				mandalStr += '		<td align="center"><img src="charts/'+resultsData.constituencyVO.pieChartNames[chart]+'" border="0"></td>';	
			mandalStr += '	</tr>';
			mandalStr += '	<tr>';
			for(var info in resultsData.constituencyVO.extraInfo)
				mandalStr += '		<td align="left" style="border: 0px none ; color: rgb(112, 112, 112);">'+resultsData.constituencyVO.extraInfo[info]+'</td>';	
			mandalStr += '	</tr>';
			mandalStr += '</table></center>';
			mandalStr += '</div>';
			mandalStr += '</fieldset>';
			
			mandalsListElmt.innerHTML = mandalStr;	

			//Rendering Mandal voting trendz data
			var str = '';
			str += '<table width="100%">';
			for(var i in results)
			{	
				for(var j in results[i].biElectionResultsVO)
				{
					
					var electionHeaderLength = (results[i].biElectionResultsVO[j].partysList.length*2)+2;
					var partyHeaderLength = results[i].biElectionResultsVO[j].partysList.length*2;
					str += '<tr>';
					str += '<td style="vertical-align:top;padding-bottom:20px;">';
					str += '<table width="100%" class="mandalResultsTable" border="1">';
					str += '<tr>';
					str += '<th colspan="'+electionHeaderLength+'" align="left">'+results[i].biElectionResultsVO[j].electionType+' - '+results[i].biElectionResultsVO[j].electionYear+'</th>';
					str += '</tr>';
					str += '<tr>';
					str += '<th rowspan="3">Mandal</th>';
					str += '<th rowspan="3">Constituency</th>';
					str += '<th colspan="'+partyHeaderLength+'" align="center">Party</th>';
					str += '</tr>';
					str += '<tr>';
					for(var p in results[i].biElectionResultsVO[j].partysList)
					{
						str += '<th colspan="2">'+results[i].biElectionResultsVO[j].partysList[p].name+'</th>';
					}
					str += '</tr>';
					str += '<tr>';
					for(var q in results[i].biElectionResultsVO[j].partysList)
					{
						str += '<th>V*</th><th>%</th>';
					}
					str += '</tr>';
					for(var k in results[i].biElectionResultsVO[j].electionResultsForMandal)
					{
						var info = results[i].biElectionResultsVO[j].electionResultsForMandal[k];
						str += '<tr>';
						if(info.partyElecResultsInConstituency.length == 0)
						{
							var cols = partyHeaderLength+1;
							str += '<th><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs"  onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';
							for(var colsno = 0;colsno < cols; colsno++)
								str += '<td> N/A </td>';	
						}
						else
						{
							str += '<th rowspan="'+info.partyElecResultsInConstituency.length+'"><A href="javascript:{}" title="Click to view results and voting trendz in '+info.mandalName+' mandal" class="viewAncs" onclick="openwin('+info.mandalId+',\''+info.mandalName+'\',\''+results[i].biElectionResultsVO[j].electionType+'\','+results[i].biElectionResultsVO[j].electionYear+','+results[i].biElectionResultsVO[j].electionId+')">'+info.mandalName+'</A></th>';				
							for(var l in info.partyElecResultsInConstituency)
							{
								str += '<th style="color:#73787E;width:150px;font-size:10px;">'+info.partyElecResultsInConstituency[l].constituencyName.toUpperCase()+'</th>';
												
								for(var m in info.partyElecResultsInConstituency[l].partyElecResults)
								{
									var data = info.partyElecResultsInConstituency[l].partyElecResults[m];	
									if(data.votesEarned == 0)
										str += '<td> N/A </td>';
									else
										str += '<td>'+data.votesEarned+'</td>';

									str += '<td>'+data.percentage+'</td>';					
								}
								
								str+='</tr><tr>';
							}
						}
						str += '</tr>';
					}
					
					str += '</table>';
					str += '</td>';
					str += '</tr>';
				
				}
				
			}
			str += '</table>';

			bodyElmt.innerHTML = str;
		}
window.history.forward(1);
	</script>

</head>
<body>

	<div id="votingTrendzInfoMain"></div>
	<center>
	<div class="rounded" style="width:910px;">
		<table>
			<tr>
				<td style="vertical-align:top;">
					<div id="zptc_main">
						<div id="zptc_head">
							<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td><img src="images/icons/districtPage/header_left.gif"/></td>
								<td>	
									<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:401px;height:18px;padding:9px;">
										<span>Total Number of ZPTC's : </span>
										<span id="totalZptcCountResultDiv"></span>												
									</div>
								</td>
								<td><img src="images/icons/districtPage/header_right.gif"/></td>
							</tr>
							</table>
						</div>
						<div id="zptc_body" style="width:452px;">
							<table>									
								<tr><td>
										<table><tr><td>
														<table ><tr>
													   		<td><div id="zptcElectionIdsSelectDiv" style="padding-left:10px;">
													   		</div></td>
													   		<td><div id="zptcCandidateLink"></div></td>
												   		</tr></table>
												   </td></tr>
											   <tr>
												   <td class="yui-skin-sam"><div id="zptcPartyTrendsDetailsDiv"></div></td>
										</tr></table>
								</td></tr>
							</table>	
							</div>
						</div>
					</td>			
					
					<td style="vertical-align:top;">
						<div id="mptc_main">
							<div id="mptc_head">
								<table border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td><img src="images/icons/districtPage/header_left.gif"/></td>
										<td>	
											<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:401px;padding:9px;height:18px;">
												<span>MPTC Voting Trends : </span>
												<span id="totalMptcCountResultDiv"></span>
											</div>		  
										</td>
										<td><img src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
						<div id="mptc_body" style="width:452px;">
								<table>									
									<tr><td>
											<table><tr><td>
															<table ><tr>
														   		<td><div id="mptcElectionIdsSelectDiv" style="padding-left:10px;" class="yui-skin-sam"></div></td>
														   		<td><div id="mptcCandidateLink"></div></td>
													   		</tr></table>
													   </td></tr>
												   <tr>
													   <td class="yui-skin-sam"><div id="mptcPartyTrendsDetailsDiv"></div></td>
											</tr></table>
									</td></tr>
								</table>	
							</div>
						</div>
					</td>	
				</tr>
				</table>
				</div>
			</center>
	<SCRIPT type="text/javascript"> 			
			
			buildMandalsVotingTrendz();			
			getAllZptcYears();	  
			getAllMptcYears();
	</script>
</body>
</html>