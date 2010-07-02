<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ResourceBundle;" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting Trends in ${constiName} Constituency</title>

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
		.mainHeading 
		{
			background-image:url("images/icons/electionResultsReport/heading.png");
			border:0 solid #AEE2FF;
			color:#000000;
			font-family:MS Sans-serif;
			font-size:17px;
			font-weight:bold;
			height:25px;
			margin-bottom:15px;
			margin-top:15px;
			padding:10px;
			text-align:center;
		}
		#inputSelectionCriteria
		{
			padding: 10px;
			color: #707070;
			margin: 10px;
			border: 2px solid #E0E0D6;
			
		}
		#selectLocationOptions
		{
			padding: 10px;
			color: #707070;
			margin: 10px;
			border: 2px solid #E0E0D6;
		}
		.selectWidth
		{
			width:150px;
			font-weight: bold;
			color:#909090;
		}
    </style>
	<script  type="text/javascript"><!--
		var districtsInfo = new Array();
		var localizationObj = '';
		var assemblyElectionType='Assembly';
		var constituencyIdGlobal = '${constiId}';
		var constituencyName = '${constiName}';
		var mptcChart2001,mptcChart2006,zptcChart2001,zptcChart2006
		
		var allPartiesCharts = '';
		
		var tehsilDetails={
				zptcArray:[],
				mptcArray:[],
				partyArray:[],
				partyMptcArray:[]
		};
		var tehsilElections={
				zptcElectionYears:[],
				mptcElectionYears:[],
				staticParties:[]
		};
		var mptcElectionType="${mptcElectionType}",zptcElectionType="${zptcElectionType}";

		
		var docOpener = window.opener;
		localizationObj = docOpener.localizationObj;
		<c:forEach var="staticParties" items="${staticPartiesList}">
			var pObj =	{
							partyId:"${staticParties.id}",
							partyName:"${staticParties.name}"
						};
			if(pObj.partyName != 'PRP')
			{
				tehsilElections.staticParties.push(pObj);
			}			
		</c:forEach>
		
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
										} else if(jsObj.task == "constituencyResults")
										{
											showChartData(myResults);
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
		function showChartData(results)
		{
			var selectboxElmtDiv = document.getElementById("selectLocationOptions");
			var checkboxElmtDiv = document.getElementById("inputSelectionCriteria");
			var selectOptionsSelectButtonElmt = document.getElementById("selectOptionsSelectButton");

			if(selectboxElmtDiv && checkboxElmtDiv)
			{
				selectboxElmtDiv.style.display = 'none';
				checkboxElmtDiv.style.display = 'none';
				selectOptionsSelectButtonElmt.style.display = 'block';
				
				selectOptionsSelectButtonElmt.innerHTML = '<input type="button" value="Select Option" onclick="displaySelectionCriteria()">';
			}

			chartName = results.chartName;
			var divEl = document.getElementById("constitutencyResultsChart");
			divEl.innerHTML = '';
			divEl.innerHTML = '<img src="charts/'+chartName+'" border="none" />';
		}
		
		function displaySelectionCriteria()
		{
			var selectboxElmtDiv = document.getElementById("selectLocationOptions");
			var checkboxElmtDiv = document.getElementById("inputSelectionCriteria");
			var selectOptionsSelectButtonElmt = document.getElementById("selectOptionsSelectButton");

			if(selectboxElmtDiv && checkboxElmtDiv)
			{
				selectboxElmtDiv.style.display = 'block';
				checkboxElmtDiv.style.display = 'block';
				selectOptionsSelectButtonElmt.style.display = 'none';				
			}
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

		function hideZptcDiv(){
			var imgElmt = document.getElementById("zptcPartyTrendsDetailsDiv");
			var electionDetails="";
			electionDetails +="<br/>";
			electionDetails +="<b>Zptc Data is not available.</b>";			
			imgElmt.innerHTML = electionDetails;		

			 var candLink = document.getElementById("zptcCandidateLink");
			 var candidateLink="";
			 candLink.innerHTML = candidateLink;
		}
		function hideMptcDiv(){
			var imgElmt = document.getElementById("mptcPartyTrendsDetailsDiv");
			var electionDetails="";
			electionDetails +="<br/>";
			electionDetails +="<b>Mptc Data is not available.</b>";			
			imgElmt.innerHTML = electionDetails;

			 var candLink = document.getElementById("mptcCandidateLink");
			 var candidateLink="";
			 candLink.innerHTML = candidateLink;
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
			var url = "<%=request.getContextPath()%>/constituencyVotingTrendzAjaxAction.action?"+rparam;

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
			
		}

		function getConstituenciesInfo(distId,index)
		{
			var obj = districtsInfo[index];

			var elmt = document.getElementById("constSelectElmt");
			if(!elmt)
				return;

			var str = '';
			str += '<select class="selectWidth" onchange="setValuesForMandalVotingTrendz(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
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
			str += '<div id="selectLocationOptions">';
			str += '<div id="districtsInfoRadioElmtDiv">';
			str += '<table width="60%">';
			str += '<tr>';
			str += '<th width="30%" align="left">';
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
				str += '<table  width="60%">';
				str += '<tr>';
				str += '<th width="30%" align="left">';
				str += 'Select Constituency : ';
				str += '</th>';
				str += '<td>';
				str += '<div id="constSelectElmt"><select class="selectWidth" onchange="setValuesForMandalVotingTrendz(this.options[this.selectedIndex].value,this.options[this.selectedIndex].text)">';
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
				str += '</div>';
			}
			str += '</div>';
			str += '<div id ="inputSelectionCriteria">';
				
			str += '</div>';
			str += '<div id="selectOptionsSelectButton" style="margin-bottom:10px;padding:10px;text-align:right"></div>';
			str += '<div id="constitutencyResultsChart"></div>';

			str += '<div id="mandalVotingTrendzDataDiv_head">Voting Trendz in Constituency</div>';
			str += '<div id="mandalVotingTrendzDataDiv">';
			str += '<div id="mandalVotingTrendzDataDiv_body" class="yui-skin-sam">';
			str += '	<div id="allPartiesResultsChartsPanel"></div>';
			str += '	<div id="mandalsListInConstituency"></div>';	
			/*
			str += '	<div id="mandalDetailsChart_body">';
			str += '	<div style="margin-top:5px;margin-left:300px;">';
			str += '		<div style="color:#ADADAD"> Loading ...</div>';
			str += '		<div> <img src="images/icons/barloader.gif"></img> </div>';
			str += '	</div>';
			str += '	</div>';*/				
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

		function getResultsForSelectedElection()
		{
			var inputSelectionErrorEl = document.getElementById("inputSelectionError");
			var partyCheckboxEls = document.getElementsByName("partywiseCheckBox");
			var electionTypeCheckboxEls = document.getElementsByName("electionCheckBox");
			var allianceCheckboxEls = document.getElementById("allianceChkBox");
			var allainceVal;
			var selectedPartiesIds = new Array();
			var selectedElectionTypesYears = new Array();
			var selectedPartiesCount, electionTypesCount;		
			inputSelectionErrorEl.innerHTML = '';
			if(allianceCheckboxEls.checked == true)
			{
				allainceVal = true;	
			} else 
				{
					allainceVal = false;
				}
			for(var i=0; i < partyCheckboxEls.length; i++)
			{
				if(partyCheckboxEls[i].checked == true)
				{
					selectedPartiesIds.push(partyCheckboxEls[i].id);
				}
			}			
			
			for(var j = 0; j < electionTypeCheckboxEls.length;j++)
			{
				if(electionTypeCheckboxEls[j].checked == true)
				{
					var checkedVal = electionTypeCheckboxEls[j].id;
					var jsObj={
							type: checkedVal.substring(checkedVal.indexOf('_')+1,checkedVal.length),
							year: checkedVal.substring(0,checkedVal.indexOf('_'))
							};
					selectedElectionTypesYears.push(jsObj);
				}	
			}

			selectedPartiesCount =  selectedPartiesIds.length;
			electionTypesCount = 	selectedElectionTypesYears.length;
			if(selectedPartiesCount == 0 && electionTypesCount == 0)
			{
				inputSelectionErrorEl.innerHTML = '';
				inputSelectionErrorEl.innerHTML = 'Please Select Party and Election Type';
				return;
			}
			if(electionTypesCount == 0)
			{
				inputSelectionErrorEl.innerHTML = '';
				inputSelectionErrorEl.innerHTML = 'Please Select Election Type';
				return;
			}
			if(selectedPartiesCount == 0)
			{
				inputSelectionErrorEl.innerHTML = '';
				inputSelectionErrorEl.innerHTML = 'Please Select Party';
				return;
			}
			var jsObj = {
					
					constituencyName: constituencyName,
					constituencyId: constituencyIdGlobal,
					partiesArr: selectedPartiesIds,
					electionTypeArr: selectedElectionTypesYears,
					task: "constituencyResults" 
					};
			
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/constituencyVotingTrendzChartAjaxAction.action?"+rparam;
			callAjax(jsObj, url);
			
		}
		
		function buildMandalVotingTrendzData(jsObj,resultsData)
		{
			
			var headElmt = document.getElementById("mandalVotingTrendzDataDiv_head");
			var bodyElmt = document.getElementById("mandalVotingTrendzData");
			var graphElmt = document.getElementById("mandalDetailsChart_body");
			var mandalsListElmt = document.getElementById("mandalsListInConstituency");
		
			var results = resultsData.biElectionResultsMainVO;
			//Hiding busy cursor Image
			var cursorImgElmt = document.getElementById('cursorImg');
			if(cursorImgElmt)
				cursorImgElmt.style.display = 'none';

			var inputEl = document.getElementById("inputSelectionCriteria");
			inputEl.innerHTML = '';

			var constChartEl = document.getElementById("constitutencyResultsChart");
			constChartEl.innerHTML = '';

			var mainHeadEl = document.getElementById("mainHead");
			mainHeadEl.innerHTML = '';
			mainHeadEl.innerHTML = 'Voting Trendz in '+jsObj.constiName+' Constituency';

			var str1= '';
			str1 += '<P>Select a Party and Election Type to view results</P>';
			str1 += '<div id ="inputSelectionError"></div>';
			str1 += '<table>';
			str1 += '<tr>';
			for(var k in tehsilElections.staticParties)
			{
				str1 += '<td><INPUT type="checkbox" name="partywiseCheckBox" id='+tehsilElections.staticParties[k].partyName+' />'+tehsilElections.staticParties[k].partyName+'</td>';
			}
			str1 += '</tr>';
			str1 += '<tr>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2009_Assembly"  />2009 Assembly</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2004_Assembly"  />2004 Assembly</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2009_Parliament"  />2009 Parliament</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2004_Parliament"  />2004 Parliament</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2006_MPTC"  />2006 MPTC</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2001_MPTC" />2001 MPTC</td>';
			str1 += '<td><INPUT type="checkbox" name="electionCheckBox" id="2006_ZPTC" />2006 ZPTC</td>';
			str1 += '<td colspan="4"><INPUT type="checkbox" name="electionCheckBox" id="2001_ZPTC" />2001 ZPTC</td>';
			str1 += '</tr>';
			str1 += '</table>';
			str1 += '<div style="text-align:right;width:750px;"><INPUT type="checkbox" name="allianceCheckBox" id="allianceChkBox" />Include Alliances</div>';
			str1 += '<div style="text-align:right;"><INPUT type="button" id="getResults" onclick="getResultsForSelectedElection()" value="Show Results" /></div>';
			inputEl.innerHTML = str1;
			
			
			//Rendering Mandal voting trendz data
			var chartDetailsObjArr = resultsData.chartsListForElectionTypes;
			var str = '';
			str += '<table width="100%" height="150" cellspacing = "5" cellpadding="5">';
			for(var i in results)
			{	
				for(var j in results[i].biElectionResultsVO)
				{
					
					var electionHeaderLength = (results[i].biElectionResultsVO[j].partysList.length*2)+2;
					var partyHeaderLength = results[i].biElectionResultsVO[j].partysList.length*2;
					str += '<tr>';
					str += '<td width="100">';
					for(var z in chartDetailsObjArr)
					{
						var chartDetailsObj = chartDetailsObjArr[z]; 
						var electionType = chartDetailsObj.electionType
						var electionYear = chartDetailsObj.electionYear
						var chartName = chartDetailsObj.chartName;	
						if(electionType == results[i].biElectionResultsVO[j].electionType && electionYear ==  results[i].biElectionResultsVO[j].electionYear)
						str += '<img src="charts/'+chartName+'" />';
						if(electionType == 'ZPTC' && electionYear == '2006')
						{
							zptcChart2006 = chartName; 
							
						} else if(electionType == 'ZPTC' && electionYear == '2001')
						{
							zptcChart2001 = chartName;							
						} else if(electionType == 'MPTC' && electionYear == '2006')
						{
							mptcChart2006 = chartName;							
						} else if(electionType == 'MPTC' && electionYear == '2001')
						{
							mptcChart2001 = chartName;							
						}	
					}
					str += '</td>';
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
					str += '<tr>';
					str += '<th colspan="2">Total</th>';
					for(var sum in results[i].biElectionResultsVO[j].partyResultsSum)
					{
						str += '<td><font style="color:GoldenRod;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].votesEarned+'</font></td><td><font style="color:GoldenRod;font-weight:bold;">'+results[i].biElectionResultsVO[j].partyResultsSum[sum].percentage+'</font></td>';
					}
					
					str += '</tr>';
					str += '</table>';
					str += '</td>';
					str += '</tr>';
				
				}
				
			}
			str += '</table>';

			bodyElmt.innerHTML = str;
			getZptcPartyDetails(tehsilElections.zptcElectionYears[0].value);
			getMptcPartyDetails(tehsilElections.mptcElectionYears[0].value);
		}
		function buildZptcResults(results){
			assignToPartyDataArray = new Array();
			var candLink = document.getElementById("zptcCandidateLink");
			var electionIdEl = document.getElementById("staticGrpSelectBox");
			var selectedYearVal = electionIdEl.options[electionIdEl.selectedIndex].text;
			var chartDivEl = document.getElementById("zptcChartDiv");
			var linkRef = '<a href="javascript:{}" onclick="redirectZptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Results</a>';
			candLink.innerHTML = linkRef;
			totalZptcSeats = results[0].totalSeats;		//	var totalZptcSeats,totalMptcSeats;
			if(selectedYearVal == '2006')
			{
				var chartStr = '';
				
				chartStr+='<img src="charts/'+zptcChart2006+'"/>';
				chartDivEl.innerHTML = chartStr;
			} else if(selectedYearVal == '2001')
			{
				var chartStr = '';
				chartStr+='<img src="charts/'+zptcChart2001+'"/>';
				chartDivEl.innerHTML = chartStr;
			}
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

			var zptcCount = document.getElementById("totalZptcCountResultDiv");
			zptcCount.innerHTML ='';

			
			var totalZptcSeats='';
			totalZptcSeats+="<b>"+results[0].totalSeats+"</b>";
			zptcCount.innerHTML +=totalZptcSeats;

			var emptyArr = new Array();
		    if(results.length == 0)
			{	tehsilDetails.partyArray = emptyArr;				
			}
		    initializeResultsTableForParty();
		}

		function buildMptcResults(results){
			assignToPartyDataArray = new Array();
			var electionIdEl = document.getElementById("staticGrpSelectBox");
			var selectedYearVal = electionIdEl.options[electionIdEl.selectedIndex].text;
			var candLink = document.getElementById("mptcCandidateLink");
			var chartDivEl = document.getElementById("mptcChartDiv");
			var linkRef = '<a href="javascript:{}" onclick="redirectMptcCandidateLink()" style="text-decoration:none;" class="candidateDetailsStyle" >Show Results</a>';
			candLink.innerHTML = linkRef;
			  totalMptcSeats = results[0].totalSeats;
			  if(selectedYearVal == '2006')
				{
					var chartStr = '';
					
					chartStr+='<img src="charts/'+mptcChart2006+'"/>';
					chartDivEl.innerHTML = chartStr;
				} else if(selectedYearVal == '2001')
				{
					var chartStr = '';
					chartStr+='<img src="charts/'+mptcChart2001+'"/>';
					chartDivEl.innerHTML = chartStr;
				}
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

			var mptcCount = document.getElementById("totalMptcCountResultDiv");
			mptcCount.innerHTML='';
			
			var totalMptcSeats='';
			totalMptcSeats+="<b>";
			totalMptcSeats+=results[0].totalSeats;
			totalMptcSeats+="</b>";
			mptcCount.innerHTML +=totalMptcSeats;
			
			var emptyArr = new Array();
		    if(results.length == 0)
			{	
		    	tehsilDetails.partyMptcArray = emptyArr;				
			}
		    initializeMptcResultsTableForParty(); 
		}
	</script>

</head>
<body>
	<div style="background-color:#FFFFFF;padding-top:10px;">
	<div id="windowHeader" style="background-color:black">
	<table width="100%">
		<tr>		
			<td width="86%" align="center">
				<TABLE cellspacing="0" cellpadding="0" border="0" >
				<TR>
					<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" style="margin-top:15px;" /></TD>
					<TD valign="top"><DIV id="mainHead" class="mainHeading">Voting Trends in ${constiName} Constituency</DIV></TD>
					<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" style="margin-top:15px;" border="none"/></TD>
				</TR>
				</TABLE>
			</td>
			<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"/></td>
		</tr>
	</table>
	</div>	
	<div id="votingTrendzInfoMain"></div>
	<center>
	<div class="rounded" >
		<table>
			<tr>
					<td style="vertical-align:top;">
						<div id="zptc_main">
							<div id="zptc_head">
								<table border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><img src="images/icons/districtPage/header_left.gif"/></td>
									<td>	
										<div id="zptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:850px;height:18px;padding:9px;">
											<span>Total Number of ZPTC's : </span>
											<span id="totalZptcCountResultDiv"></span>														
										</div>
									</td>
									<td><img src="images/icons/districtPage/header_right.gif"/></td>
								</tr>
								</table>
							</div>
							<div id="zptc_body" style="width:900px;">
								<table>									
									<tr><td>
											<table><tr><td colspan="2" align="left">
															<table ><tr>
														   		<td><div id="zptcElectionIdsSelectDiv" style="padding-left:10px;">
														   		</div></td>
														   		<td><div id="zptcCandidateLink"></div></td>
													   		</tr></table>
													   </td></tr>
												   <tr>
												   	   <td><div id="zptcChartDiv"></div></td>
													   <td class="yui-skin-sam"><div id="zptcPartyTrendsDetailsDiv"></div></td>
											</tr></table>
									</td></tr>
								</table>	
								</div>
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
												   <td class="yui-skin-sam"><div id="zptcPartyTrendsDetailsDiv" style="border:2px solid #9696C0"></div></td>
										</tr></table>
								</td></tr>
							</table>	
							</div>
						</td>			
					</tr>
					<tr>
					<td style="vertical-align:top;">
						<div id="mptc_main">
							<div id="mptc_head">
								<table border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td><img src="images/icons/districtPage/header_left.gif"/></td>
										<td>	
											<div id="mptcInfoDivHead" class="districtPageRoundedHeaders_center" style="width:850px;padding:9px;height:18px;">
												<span>Total Number of MPTC's : </span>
												<span id="totalMptcCountResultDiv"></span>
											</div>
										</td>
										<td><img src="images/icons/districtPage/header_right.gif"/></td>
									</tr>
								</table>
							</div>
						<div id="mptc_body" style="width:900px;">
								<table>									
									<tr><td>
											<table><tr><td colspan="2">
															<table ><tr>
														   		<td><div id="mptcElectionIdsSelectDiv" style="padding-left:10px;" class="yui-skin-sam"></div></td>
														   		<td><div id="mptcCandidateLink"></div></td>
													   		</tr></table>
													   </td></tr>
												   <tr>
												   	   <td><div id="mptcChartDiv"></div></td>		
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
		<div id="index_footer" class="indexLayoutContainer" style="width:100%">
			<div id="index_inner_footer">
			<table width="100%" id="copyrightLinksTable">
				<tr>
					<td align="left"> © Copyright 2010. All rights reserved | IT GRIDS (India) Pvt. Ltd.</td>
					<td align="right"> About Us | Contact Us | API | Terms Of Use | Privacy Policy </td>
				</tr>
			</table>
			</div>
		</div>
	</div>
	<SCRIPT type="text/javascript"> 			
			
			buildMandalsVotingTrendz();			
			getAllZptcYears();	  
			getAllMptcYears();
	</script>
</body>
</html>