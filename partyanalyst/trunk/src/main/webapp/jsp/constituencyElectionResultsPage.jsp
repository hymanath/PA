<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> ${constituencyDetails.constituencyName} Constituency Election Results </title>


<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>

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

<link rel="stylesheet" type="text/css" href="styles/constituencyPage/constituencyPage.css">	
<style type="text/css">
.legendTable td 
{
color:Black;
text-align:left;
font-style:italic;
}

.legendTable th 
{
color:DarkSeaGreen;
text-align:left;
}

#constituencyPageMain 
{
background-color:#FFFFFF;
font-family:verdana;
font-size:14px;
padding:10px;
}

.yui-skin-sam .yui-dt table 
{
border-collapse:separate;
border-spacing:0;
color:background;
font-family:verdana;
font-size:inherit;
margin:0;
padding:0;
}

#detailsHead {
color:#247CD4;
font-size:19px;
font-weight:bold;
padding:10px;
margin-left:134px;
text-align:center;
}

</style>
<script type="text/javascript"><!--
var constiId = '${constituencyElectionResultsVO.constituencyId}';
var elecType = '${constituencyElectionResultsVO.electionType}';
var elecYear = '${constituencyElectionResultsVO.electionYear}';

var constituencyElecMainObj=	{
		constituencyAddress:'',
		contextPath:'',
		constituencyBasicInfo: {
									constituencyId:'',
									constituencyName:'',
									stateName:'',
									districtName:'',
									constituencyType:''
							   },
		constituencyElectionInfo: {
                                     electionType:'',
								     electionYear:'',
								     totalVoters:'',
									 validVotes:''
								  },
		wonCandidateInfo: {
						     candidateId:'',
							 candidateName:'',
							 partyId:'',
                             partyName:'',
							 partyFlag:'',
							 votesEarned:'',
							 votesPercent:'',
							 rank:'',
							 votesMargin:'',
							 votesPercentMargin:''
						  },
		oppCandidateElecResults:[]
};

function redirectCandidateLink(elecYear)
{
	var browser1;	
		if(elecYear != 'Select Year')
		{	
		   browser1 = window.open("<s:url action="constituencyElectionResultsAction.action"/>?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
		   browser1.focus();
		} else return;  
}
function buildDataForConstituencyResults()
{	
	var year ='',count=0;
	var allYears = document.getElementById("allYears");
	var electionYears = '';
	electionYears+='<IMG src="images/icons/infoicon.png" border="none" style="margin-right:5px;"/>';
	electionYears+='<SPAN style="color:#606060;">Select to view Election Results for other year';
	electionYears+='<select class="selectWidth" id="yearSelectDropDown" style="width: 100px; margin-left: 5px;" onchange="redirectCandidateLink(this.options[this.selectedIndex].text)">';
	electionYears+='<OPTION value="0">Select Year</OPTION>';		
	<c:forEach var="years" items="${constituencyElectionResultsVO.allElectionYears}">
		<c:if test="${constituencyElectionResultsVO.electionYear != years.name}">
			electionYears+='<OPTION value="years.id">${years.name}</OPTION>';
		</c:if>		
	</c:forEach>		
	electionYears+='</select>';			
		
		allYears.innerHTML = electionYears;
		
		 constituencyElecMainObj.constituencyBasicInfo.constituencyId='${constituencyElectionResultsVO.constituencyId}';
		 constituencyElecMainObj.constituencyBasicInfo.constituencyName='${constituencyElectionResultsVO.constituencyName}';
         constituencyElecMainObj.constituencyBasicInfo.stateName='${constituencyElectionResultsVO.stateName}';
       	 constituencyElecMainObj.constituencyBasicInfo.districtName='${constituencyElectionResultsVO.districtName}';
      	 constituencyElecMainObj.constituencyBasicInfo.constituencyType='${constituencyElectionResultsVO.electionType}';


	    constituencyElecMainObj.constituencyElectionInfo.electionType='${constituencyElectionResultsVO.electionType}';
		constituencyElecMainObj.constituencyElectionInfo.electionYear='${constituencyElectionResultsVO.electionYear}';
	                    
	 
		constituencyElecMainObj.wonCandidateInfo.candidateId='${constituencyElectionResultsVO.candidateResultsVO.candidateId}';
		constituencyElecMainObj.wonCandidateInfo.candidateName='${constituencyElectionResultsVO.candidateResultsVO.candidateName}';
		constituencyElecMainObj.wonCandidateInfo.partyId='${constituencyElectionResultsVO.candidateResultsVO.partyId}';
		constituencyElecMainObj.wonCandidateInfo.partyName='${constituencyElectionResultsVO.candidateResultsVO.partyName}';
		<c:if test="${constituencyElectionResultsVO.candidateResultsVO.partyFlag != null}"> 
        constituencyElecMainObj.wonCandidateInfo.partyFlag='<img src="<%=request.getContextPath()%>/images/party_flags/${constituencyElectionResultsVO.candidateResultsVO.partyFlag}" height="30" width="40"/>';
        </c:if> 
		<c:if test="${constituencyElectionResultsVO.candidateResultsVO.partyFlag == null}"> 
        constituencyElecMainObj.wonCandidateInfo.partyFlag='<img src="<%=request.getContextPath()%>/images/party_flags/no_Image.png" height="30" width="40"/>';
        </c:if> 
		constituencyElecMainObj.wonCandidateInfo.partyFlag='<img src="<%=request.getContextPath()%>/images/party_flags/${constituencyElectionResultsVO.candidateResultsVO.partyFlag}" height="30" width="40"/>';
		constituencyElecMainObj.wonCandidateInfo.votesEarned='${constituencyElectionResultsVO.candidateResultsVO.votesEarned}',
		constituencyElecMainObj.wonCandidateInfo.votesPercent='${constituencyElectionResultsVO.candidateResultsVO.votesPercentage}';
		constituencyElecMainObj.wonCandidateInfo.rank='${constituencyElectionResultsVO.candidateResultsVO.rank}';
		constituencyElecMainObj.wonCandidateInfo.votesMargin='${constituencyElectionResultsVO.candidateResultsVO.votesMargin}';
		constituencyElecMainObj.wonCandidateInfo.votesPercentMargin='${constituencyElectionResultsVO.candidateResultsVO.votesPercentMargin}';

		
	 <c:forEach var="oppCandResults" items="${constituencyElectionResultsVO.candidateOppositionList}">

     var partyFlg = '';
     <c:if test="${oppCandResults.partyFlag != null}">
	 partyFlg = '<img src="<%=request.getContextPath()%>/images/party_flags/${oppCandResults.partyFlag}" height="30" width="40"/>';
	 </c:if> 
	 <c:if test="${oppCandResults.partyFlag == null}">
	 partyFlg = '<img src="<%=request.getContextPath()%>/images/party_flags/no_Image.png" height="30" width="40"/>';
	 </c:if>
	 
	 var oppCandInfo={
			         candidateId:'${oppCandResults.candidateId}',
			         candidateName:'${oppCandResults.candidateName}',
			         partyId:'${oppCandResults.partyId}',
			         partyName:'${oppCandResults.partyName}',
					 partyFlag:partyFlg,
			         votesEarned:'${oppCandResults.votesEarned}',
			         votesPercent:'${oppCandResults.votesPercentage}',
			         rank:'${oppCandResults.rank}',
					 votesMargin:'${oppCandResults.votesMargin}',
					 votesPercentMargin:'${oppCandResults.votesPercentMargin}'
	                 }
	 constituencyElecMainObj.oppCandidateElecResults.push(oppCandInfo);
	 </c:forEach>

}

function displayConstituencyElectionResults()
{
		var smallerCase = constituencyElecMainObj.constituencyBasicInfo.constituencyName;
		//smallerCase=smallerCase.replace(smallerCase[0],smallerCase[0].toUpperCase());

		var smallerCaseElecType = constituencyElecMainObj.constituencyBasicInfo.constituencyType;
		//smallerCaseElecType=smallerCaseElecType.replace(smallerCaseElecType[0],smallerCaseElecType[0].toUpperCase());
		
		var elementHead = document.getElementById("detailsHead");
		var headDiv = '';
		headDiv+='<centre><table><tr>';
		headDiv+='<td>'+smallerCase+'</td>';
		headDiv+='<td>'+smallerCaseElecType+'</td>';
		headDiv+='<td> Election Result For - </td>';
		headDiv+='<td>'+constituencyElecMainObj.constituencyElectionInfo.electionYear+'</td>';
		headDiv+='</tr></table></centre>';
		elementHead.innerHTML = headDiv;

		
		var elmt = document.getElementById("electionResults_Panel");

		var str='';
		str+='<fieldset id="constituencyInfoFieldSet">';
		str+='<legend> Constituency Info </legend>';
		str+='<div id="constituencyInfoDiv">';
		str+='<table id="constituencyInfoTableClass" class="legendTable" width="100%">';
		str+='<tr>';
		str+='<th width="25%">Constituency</th>';
		str+='<td width="25%">'+constituencyElecMainObj.constituencyBasicInfo.constituencyName+'</td>';
		str+='<th width="25%">Constituency Type</th>';
		str+='<td width="25%">'+constituencyElecMainObj.constituencyBasicInfo.constituencyType+'</td>';	
		str+='</tr>';

		str+='<tr>';	
		str+='<th>District</th>';
		str+='<td>'+constituencyElecMainObj.constituencyBasicInfo.districtName+'</td>';
		str+='<th>State </th>';
		str+='<td>'+constituencyElecMainObj.constituencyBasicInfo.stateName+'</td>';	
		str+='</tr>';	
		str+='<tr>';
		str+='<td></td>';
		str+='<td></td>';
		str+='<th>Year</th>';
		str+='<td>'+constituencyElecMainObj.constituencyElectionInfo.electionYear+'</td>';
		str+='</tr>';	
		str+='</table>';
		str+='</div>';
		str+='</fieldset>';
		//--------------
		str+='<fieldset id="WinningCandidateFieldSet">';
		str+='<legend> Winning Candidate Info </legend>';
		str+='<div id="WinningCandidateDiv">';
		str+='<table id="WinningCandidateTableClass" class="legendTable" width="100%">';
		str+='<tr>';
		str+='<th></th>';
		str+='<td></td>';
		str+='<th>Votes Earned</th>';
		str+='<td>'+constituencyElecMainObj.wonCandidateInfo.votesEarned+'</td>';
		str+='</tr>';

		str+='<tr>';
		str+='<th>Name</th>';
		str+='<td>'+constituencyElecMainObj.wonCandidateInfo.candidateName+'</td>';
		str+='<th>Votes Percentage</th>';
		str+='<td>'+constituencyElecMainObj.wonCandidateInfo.votesPercent+' %</td>'
		str+='</tr>';

		str+='<tr>';
		str+='<th>Party</th>';
		str+='<td>'+constituencyElecMainObj.wonCandidateInfo.partyName+'</td>';	
		str+='<th>Votes Margin Gained</th>';
		str+='<td>'+constituencyElecMainObj.wonCandidateInfo.votesMargin+'</td>'
		str+='</tr>';

		str+='<tr>';
		str+='<th>Party Flag</th>';
		str+='<td>'+constituencyElecMainObj.wonCandidateInfo.partyFlag+'</td>';
		str+='<th>Votes Margin % Gained</th>';
		str+='<td>'+constituencyElecMainObj.wonCandidateInfo.votesPercentMargin+' %</td>'
		str+='</tr>';

		str+='</table>';
		str+='</div>';	
		str+='</fieldset>';
		//--------
		str+='<fieldset id="oppositionResultsField">';
		str+='<legend> Opposition\'s Results Info </legend>';
		str+='<div id="oppCandResultsDiv" style="font-size:13px;">';	
		str+='</div>';
		str+='</fieldset>';
			
		if(elmt)
			elmt.innerHTML = str;
	
		var myDataSource = new YAHOO.util.DataSource(constituencyElecMainObj.oppCandidateElecResults); 
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		myDataSource.responseSchema = { 
				fields: [
							{key:"candidateName"},
							{key:"partyName"},
							{key:"partyFlag"},
							{key:"votesEarned",parser:"number"},
							{key:"votesPercent",parser:"number"},
							{key:"rank",parser:"number"}
						] 
			}; 

		var myColumnDefs = [ 
				{key:"candidateName",label:'Candidate Name', sortable:true}, 
				{key:"partyName", label:'Party Name', sortable:true}, 
				{key:"partyFlag", label:'Party Flag',sortable:true}, 
				{key:"votesEarned",label:'Votes Earned',formatter:YAHOO.widget.DataTable.formatNumber, sortable:true}, 
				{key:"votesPercent",label:'Votes %', sortable:true}, 
	            {key:"rank",label:'Rank', sortable:true} 
			]; 

		var myDataTable = new YAHOO.widget.DataTable("oppCandResultsDiv",myColumnDefs, myDataSource);
	
}
</script>
</head>

<body>
<div id="constituencyPageMain" style="background-color:none;">
	<div id="detailsHead"></div>
	<div id="allYears" style="margin:15px;"></div>
	<div id="electionResults_Panel_Main" class="yui-skin-sam">
			<div id="electionResults_Panel"></div>
	</div>	
</div>
<script type="text/javascript">
buildDataForConstituencyResults();
displayConstituencyElectionResults();
</script>
</body>

</html>
