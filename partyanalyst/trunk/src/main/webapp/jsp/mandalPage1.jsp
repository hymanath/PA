<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<html>
<head>
<!--CSS file (default YUI Sam Skin) --> 
	<link type="text/css" rel="stylesheet" href="http://yui.yahooapis.com/2.8.0r4/build/datatable/assets/skins/sam/datatable.css"> 	 
	<!-- Dependencies --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/datasource/datasource-min.js"></script> 
	 
	<!-- OPTIONAL: JSON Utility (for DataSource) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/json/json-min.js"></script> 
	 
	<!-- OPTIONAL: Connection Manager (enables XHR for DataSource) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/connection/connection-min.js"></script> 
	 
	<!-- OPTIONAL: Get Utility (enables dynamic script nodes for DataSource) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/get/get-min.js"></script> 
	 
	<!-- OPTIONAL: Drag Drop (enables resizeable or reorderable columns) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/dragdrop/dragdrop-min.js"></script> 
	 
	<!-- OPTIONAL: Calendar (enables calendar editors) --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/calendar/calendar-min.js"></script> 
 
	<!-- Source files --> 
	<script type="text/javascript" src="http://yui.yahooapis.com/2.8.0r4/build/datatable/datatable-min.js"></script> 
	<!--  dependencies for tab view -->
	<!-- Sam Skin CSS for TabView -->
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/tabview/assets/skins/sam/tabview.css">
 
<!-- JavaScript Dependencies for Tabview: -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="http://yui.yahooapis.com/2.8.0r4/build/element/element-min.js"></script>
 
<!-- OPTIONAL: Connection (required for dynamic loading of data) -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/connection/connection-min.js"></script>
 
<!-- Source file for TabView -->
<script src="http://yui.yahooapis.com/2.8.0r4/build/tabview/tabview-min.js"></script>
<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>	
<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.8.0r4/build/paginator/assets/skins/sam/paginator.css">
	
<style type="text/css">



#VillageTable th
{
	background-color:#afafaf;
}

#boothResultsDiv {
	text-align: left;
	margin-left: 50px;
	font-size: 12px;
	margin-right:10px;
	
}
#villageCensusDivHead,#mandalCensusDiv
{
	color:#0D3A5C;
}
#villageCensusDivBody
{
	border:2px solid #A5CCFF;
}
.yui-skin-sam th.yui-dt-asc, .yui-skin-sam th.yui-dt-desc 
{
	background-image:none;
}

.yui-skin-sam thead .yui-dt-sortable {

	color:#3F546F;
	text-decoration:none;
}

.searchresultsTable td {
	background-color:#F8FBFF;
}
.searchresultsTable th {
	background-color:#C4DEFF;
}
.yui-skin-sam .yui-dt-liner {
	padding:4px 8px;
}
.ConstituencyElectionsTable th
{
	text-align:left;
	background-color:#C4DEFF;
}
.ConstituencyElectionsTable td
{
	text-align:right;
}
fieldset
{
border:4px solid #CFD6DF;
margin-bottom:10px;
}

legend
{
background-color:#567AAF;
color:#FFFFFF;
font-size:12px;
padding:5px;
}

.censusInfoTable th
{
	background-color:#567AAF;
	border:1px solid white;
	color:#FFFFFF;
	padding:8px;
}

.censusInfoTable td
{
	background-color:#FFFFFF;
	border:1px solid #D2D9DF;
	padding:8px;
}
.dataTableSize table{
width:100%;
}
.selectWidth {
padding:2px;
width:120px;
}

</style>



<script type="text/javascript">
	
var candidateElectionResultPanel;
var allBoothElecInfo = new Array();
<c:forEach var="electionResult" items="${electionWiseMandalPartyResultListVO.electionWiseMandalPartyResultVOList}" >
		var electionInfo = {
				year:'${electionResult.electionYear}',
				electionType:'${electionResult.electionType}',
				url:'${electionResult.genderBoothURL}',
				partyVotes:[],
				maleBooths:[],
				femaleBooths:[],
				mfBooths:[]
		};
		<c:forEach var="partyInfo" items="${electionResult.partyVotes}">
			var singleParty = {
					partyId:'${partyInfo.partyID}',
					partyName:'${partyInfo.partyName}',
					candidateId:'${partyInfo.candidateID}',
					candidateName:'${partyInfo.candidateNameWithStatus}',
					mandalVotes:'${partyInfo.totalVotesEarned}',
					maleVotes:'${partyInfo.maleBoothResults}',
					femaleVotes:'${partyInfo.femaleBoothResults}',
					bothVotes:'${partyInfo.fmBoothResults}'
			};
			electionInfo.partyVotes.push(singleParty);
		</c:forEach>		
		<c:forEach var="maleBooth" items="${electionResult.boothTypeDetailsVO.maleBoothVotes}">
			var maleBooth = {
					partNo:'${maleBooth.partNo}',
					villagesCovered:'${maleBooth.villagesCovered}',
					maleVotes:'${maleBooth.maleVotes}',
					femaleVotes:'${maleBooth.femaleVotes}',
					totalVotes:'${maleBooth.totalVotes}',
					validVotes:'${maleBooth.validVotes}'
			}
			electionInfo.maleBooths.push(maleBooth);
		</c:forEach> 
		<c:forEach var="femaleBooth" items="${electionResult.boothTypeDetailsVO.femaleBoothVotes}">
			var femaleBooth = {
					partNo:'${femaleBooth.partNo}',
					villagesCovered:'${femaleBooth.villagesCovered}',
					maleVotes:'${femaleBooth.maleVotes}',
					femaleVotes:'${femaleBooth.femaleVotes}',
					totalVotes:'${femaleBooth.totalVotes}',
					validVotes:'${femaleBooth.validVotes}'
			}
			electionInfo.femaleBooths.push(femaleBooth);
		</c:forEach> 
		<c:forEach var="mfBooth" items="${electionResult.boothTypeDetailsVO.maleFemailBoothVotes}">
			var mfBooth = {
					partNo:'${mfBooth.partNo}',
					villagesCovered:'${mfBooth.villagesCovered}',
					maleVotes:'${mfBooth.maleVotes}',
					femaleVotes:'${mfBooth.femaleVotes}',
					totalVotes:'${mfBooth.totalVotes}',
					validVotes:'${mfBooth.validVotes}'
			}
			electionInfo.mfBooths.push(mfBooth);
		</c:forEach> 
		allBoothElecInfo.push(electionInfo);
</c:forEach>


	function buildCensusDataTable()
	{
		var resultsDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
			.get("villageCensusTable"));
	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
	resultsDataSource.responseSchema = {
		fields : [ {
			key : "townshipName"
		}, {
			key : "totalPersons",parser:"number"
		}, {
			key : "totalSCPersons",parser:"number"
		}, {
			key : "totalSTPersons",parser:"number"
		}, {
			key : "totalLiteratePersons",parser:"number"
		}, {
			key : "totalIlliteratePersons",parser:"number"
		} , {
			key : "totalWorkingPersons",parser:"number"
		} ]
	};

	var resultsColumnDefs = [ {
		key : "townshipName",		
		label : "Village Name",
		sortable : true
	}, {
		key : "totalPersons",
		parser:"number",
		label : "Total Populations",
		sortable : true
	}, {
		key : "totalSCPersons",
		parser:"number",
		label : "SC Populations",
		sortable : true
	}, {
		key : "totalSTPersons",
		parser:"number",
		label : "ST Populations",
		sortable : true
	}, {
		key : "totalLiteratePersons",
		parser:"number",
		label : "Literate Populations",
		sortable : true
	}, {
		key : "totalIlliteratePersons",
		parser:"number",
		label : "Illiterate Populations",
		sortable : true
	}, {
		key : "totalWorkingPersons",
		parser:"number",
		label : "Working Populations",
		sortable : true
	} ];

	var myConfigs = { 
		    paginator : new YAHOO.widget.Paginator({ 
	        rowsPerPage    : 15 
		    }) 
		};
	
	var myDataTable = new YAHOO.widget.DataTable("dTTableDiv2",resultsColumnDefs, resultsDataSource,myConfigs);  

	}

	function buildRevenueVillagesInfoTab(){
		var revenueInfo = '';
		revenueInfo += '<div id="div3_revenue">';
		revenueInfo += '<div id="revenueVillageTable">';
		revenueInfo += '</div>';
		revenueInfo += '<div id="revenueVillagesMainDiv">';
		revenueInfo += '<table>';
		revenueInfo += '<tr><td>Election Type:</td>';
		revenueInfo += '<td><select onchange = "getElectionYears(this.options[this.selectedIndex].value)" class = "selectWidth">';
		revenueInfo += '<option value="0">Select </option>';
		revenueInfo += '<option value="1">Parliament</option>';
		revenueInfo += '<option value="2">Assembly</option>';
		revenueInfo += '</select></td></tr>';
		revenueInfo += '<tr>';
		revenueInfo += '<td><div id="electionIdSelectDivLabel"></div></td>';
		revenueInfo += '<td><div id="electionIdSelectDivData"></div></td>';
		revenueInfo += '</tr>';
		revenueInfo += '</table>';
		revenueInfo += '</div>';
		revenueInfo += '</div>';
		
		return revenueInfo;
	}

	function getElectionYears(id){
		alert("electionTypeId::"+id);
		var jsObj=
			{
					electionTypeId:id,
					task:"getElectionYears"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getElectionYearsAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}

	function getRevenueVillagesInfo(id){
		alert("electionTypeId::"+id);
		var jsObj=
			{
					electionId:id,
					task:"getRevenueVillagesInfo"						
			};
		
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
			var url = "<%=request.getContextPath()%>/getRevenueVillagesElectionsAjaxAction.action?"+rparam;						
			callAjax(rparam,jsObj,url);
	}
	
	function callAjax(rparam, jsObj, url){
		var resultVO;			
		var callback = {			
	               success : function( o ) {
						try {								
								resultVO = YAHOO.lang.JSON.parse(o.responseText);										
								
								if(jsObj.task == "getElectionYears")
								{								
									showElectionYearTextBox(resultVO);				
								}
								else if(jsObj.task == "getRevenueVillagesInfo")
								{								
									showRevenueVillagesElectionInfo(resultVO);				
								}								
						}catch (e)  {   
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

	function showElectionYearTextBox(resultVO){
		
		var electionYearSelect = '';
		var elmtLabel = document.getElementById('electionIdSelectDivLabel');
		var elmtData = document.getElementById('electionIdSelectDivData');
		
		electionYearSelect += '<select class = "selectWidth" onchange = "getRevenueVillagesInfo(this.options[this.selectedIndex].value)">';
		for(var i in resultVO)
		{			
			electionYearSelect += '<option value='+resultVO[i].id+'>'+resultVO[i].name+'</option>';
		}
	
		electionYearSelect += '</select>';

		if(elmtLabel)
			elmtLabel.innerHTML='Election Year:';
		if(elmtData)
			elmtData.innerHTML=electionYearSelect;
	}

	function showRevenueVillagesElectionInfo(resultVO){
		alert("Revenue Village Election Info::"+resultVO);
	}
	
	function buildTabNavigator(){
		var myTabs = new YAHOO.widget.TabView();
		var mandalElections = '';
		mandalElections+='<div id="div1" >';
		mandalElections+='<div id="electionsInfoMainDiv"></div>';
		mandalElections+='</div>';
		
		var cencusInfo = '';
		cencusInfo+='<div id="div2" >';
		cencusInfo+='<div id="dTTableDiv2"></div>';
		cencusInfo+='</div>';

		
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'All Election In Mandal',
		    active:true,
		    content: mandalElections
		}));
		
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Census Info in Revenue Villages',
		    content: cencusInfo
		}));
		
		myTabs.addTab( new YAHOO.widget.Tab({
		    label: 'Revenue Villages Info',
		    content: buildRevenueVillagesInfoTab()
		    
		}));

		myTabs.appendTo('mandalPageTab');
				
	}

	function showElectionResultsInPopup()
	{
	
		var elmt = document.getElementById("electionsInfoMainDiv");
		for(var i in allBoothElecInfo)
		{
			var divChild = document.createElement("div");
			var electionInfo = '';
			electionInfo += '<fieldset>';
			electionInfo += '<legend>'+allBoothElecInfo[i].year+' '+allBoothElecInfo[i].electionType+'</legend>';
			electionInfo += '<div id = "div_'+i+'" class="dataTableSize">';
			electionInfo += '</div>';
			electionInfo += '</fieldset>';
			divChild.innerHTML = electionInfo;
				
			if(elmt)
				elmt.appendChild(divChild);
	
			
			 var myDataSource = new YAHOO.util.DataSource(allBoothElecInfo[i].partyVotes); 
			 myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
			 myDataSource.responseSchema = { 
			            fields: [
									{
										key : "partyName"
									},{
										key : "candidateName"
									},{
										key : "mandalVotes",parser:"number"
									},{
										key : "maleVotes",parser:"number"
									},{
										key : "femaleVotes",parser:"number"
									},{
										key : "bothVotes",parser:"number"
									}
								]    
			        }; 
			
			 var myColumnDefs = [ 
			            {key:"partyName",label:'Party Name', sortable:true, resizeable:true}, 
			            {key:"candidateName", label:'Candidate Name', sortable:true, resizeable:true}, 
			            {key:"mandalVotes", label:'Mandal Votes',sortable:true, resizeable:true}, 
			            {key:"maleVotes",label:'Male Votes',formatter:YAHOO.widget.DataTable.formatNumber, sortable:true, resizeable:true}, 
			            {key:"femaleVotes",label:'Female Votes', sortable:true, resizeable:true},
			            {key:"bothVotes",label:'Both Votes', sortable:true, resizeable:true} 
			        ]; 
			 
			var myDataTable = new YAHOO.widget.DataTable("div_"+i,myColumnDefs, myDataSource);

			
		}		
		
		
			
	}

	</script>
</head>
<body> 

<h3><u><c:out value="${mandalInfoVO.mandalName}"/> Tehsil / Mandal Details</u></h3>

<div id="boothResultsDiv">
	<div id="mandalCensusDiv">
		<div id="mandalCensusDivHead"><h4><u>Mandal Details..</u></h4></div>
		<div id="mandalCensusDivBody" align="center" class="yui-skin-sam">
		<table class="censusInfoTable" >		
				<tr>
					<th></th>
					<th>Population</th>
					<th>SC Population</th>
					<th>ST Population</th>
					<th>Literate Populations</th>
					<th>Illiterate Population</th>
					<th>Working Population</th>
					
				</tr>
				<tr>
					<th>Male</th>
					<td><c:out value="${mandalInfoVO.totalMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliterateMalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingMalePersons}"/></td>
				</tr>
				<tr>
					<th>Female</th>
					<td><c:out value="${mandalInfoVO.totalFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliterateFemalePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingFemalePersons}"/></td>
				</tr>
				<tr>
					<th>Total</th>
					<td><c:out value="${mandalInfoVO.totalPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSCPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalSTPersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalLiteratePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalIlliteratePersons}"/></td>
					<td><c:out value="${mandalInfoVO.totalWorkingPersons}"/></td>
				</tr>
				
			</table>
		</div>
	</div>
	<br/>
	<div id="villageCensusDiv" style="display: none;">
		<div id="villageCensusDivHead"><h4><u>Villages Details..</u></h4></div>
		<div id="villageCensusDivBody" class="yui-skin-sam">
			<display:table id="villageCensusTable" class="searchresultsTable"
			 name="${villageDetailsVO.villageCensusList}"
			defaultorder="ascending" defaultsort="2"
			style="width:auto;margin-right:20px;">
				<display:column style="text-align: left;" title="Village Name" 
					property="townshipNameURL" sortable="true" />
				<display:column style="text-align: left;" title="Total Populations"
					property="totalPersons" sortable="true" />
				<display:column style="text-align: left;" title="SC Population"
					property="totalSCPersons" sortable="true" />
				<display:column style="text-align: center;" title="ST Population"
					property="totalSTPersons" sortable="true" />
				<display:column style="text-align: center;" title="Literate Population"
					property="totalLiteratePersons" sortable="true" />
				<display:column style="text-align: center;" title="Illiterate Population"
					property="totalIlliteratePersons"  sortable="true"/>
				<display:column style="text-align: center;" title="Working Population"
					property="totalWorkingPersons" sortable="true" />
			</display:table>`
		</div>
	</div>
	<div id="mandalPageTab" class="yui-skin-sam"></div>
</div>

<script type="text/javascript">


	buildTabNavigator();
	showElectionResultsInPopup();
	buildCensusDataTable();
	
</script>
</body>



</html>