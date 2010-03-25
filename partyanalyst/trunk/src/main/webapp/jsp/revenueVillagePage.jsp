<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revenue Village Page</title>

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
<script type="text/javascript">

function boothDatatable()
{
	<c:forEach var="election" items="${electionWiseMandalPartyResultListVO.electionWiseMandalPartyResultVOList}">									
		<c:forEach var="constituencyInfo" items="${election.constituencyWiseDataForMandalVOs}">

		var tableId = "tableId_"+${constituencyInfo.constituencyId};
		var divId = "divId_"+${constituencyInfo.constituencyId};

		var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
				.get(tableId)); 
		 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
		 myDataSource.responseSchema = { 
		            fields: [
								{
									key : "partyName"
								},{
									key : "candidateName"
								},{
									key : "mandalVotes",parser:"number"
								},{
									key : "mandalpercentage",parser:"float"
								},{
									key : "maleVotes",parser:"number"
								},{
									key : "malepercentage",parser:"float"
								},{
									key : "femaleVotes",parser:"number"
								},{
									key : "femalepercentage",parser:"float"
								},{
									key : "bothVotes",parser:"number"
								},{
									key : "bothpercentage",parser:"float"
								}
							]    
		        }; 
		
		 var myColumnDefs = [ 
		            {key:"partyName",label:'Party', sortable:true, resizeable:true}, 
		            {key:"candidateName", label:'Candidate Name', sortable:true, resizeable:true}, 
		            {key:"mandalVotes", label:'Total Votes Earned',sortable:true, resizeable:true}, 
		            {key:"mandalpercentage",label:'%', sortable:true, resizeable:true}, 
		            {key:"maleVotes",label:'Male Votes', sortable:true, resizeable:true}, 
		            {key:"malepercentage",label:'%', sortable:true, resizeable:true}, 
		            {key:"femaleVotes",label:'Female Votes', sortable:true, resizeable:true},
		            {key:"femalepercentage",label:'%', sortable:true, resizeable:true}, 
		            {key:"bothVotes",label:'Male&Female Votes', sortable:true, resizeable:true},
		            {key:"bothpercentage",label:'%', sortable:true, resizeable:true} 
		        ]; 
		var myDataTable = new YAHOO.widget.DataTable(divId,myColumnDefs, myDataSource);	

		
		</c:forEach>			
	</c:forEach>
}

		function getGraph(value)
		{	
			var str = '';
			var township = ${townshipId};
					
			var elmt = document.getElementById("townshipGraphDiv");	
			if(value=="0")
			{
				elmt.innerHTML='';
				return;
			}	
		
			str+='<img src="charts/partyPerformanceInAllRVElections_'+township+'_'+value+'.png"/>'; 
			if(elmt)
				elmt.innerHTML=str;
			
		}
		
		function showACPCResults(){
		
			var allZMElmt = document.getElementById("partyElectionsGraph");
			var str = '';
			str+='<div>';
			str+='<table>';
			str+='<tr>';
			str+='<td> View Graph For:</td>';		
			str+='<td>';
			str+='<select onchange="getGraph(this.value)">';
			str+='<option value="0">Select Party</option>';
			<c:forEach var="party" items="${partiesInTownship}">
				str+='<option value="${party.id}">${party.name}</option>';
			</c:forEach>		
			str+='</select>';
			str+='</td>';
			str+='<td><div id="townshipGraphDiv"></div></td>';
			str+='</tr>';
			str+='</table>';		
			str+='</div>';

			if(allZMElmt)
				allZMElmt.innerHTML = str;
			
		}	


</script>

<style type="text/css">
	
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

#revenuePageHeading
{
	color:#4D5E70;
	font-size:18px;
	font-weight:bold;
	padding:10px;
	text-decoration:underline;
}
</style>

</head>
<body>
 <div id="revenuePageHeading"><s:property value="townshipName"/> &nbsp;Revenue Village Page</div>
 <div id="partyElectionsGraph"></div>
	<c:choose>
		<c:when test="${ empty electionWiseMandalPartyResultListVO.electionWiseMandalPartyResultVOList}">
			No Records Found
		</c:when>
		<c:otherwise>
			<c:forEach var="election" items="${electionWiseMandalPartyResultListVO.electionWiseMandalPartyResultVOList}">									
				<c:forEach var="constituencyInfo" items="${election.constituencyWiseDataForMandalVOs}">
					<fieldset>
						<legend><c:out value="${constituencyInfo.constituencyName}"/>&nbsp;<c:out value="${election.electionType}"/>&nbsp;<c:out value="${election.electionYear}"/></legend>
						<div class="yui-skin-sam">
						<div id="divId_${constituencyInfo.constituencyId}">			
						<table id = "tableId_${constituencyInfo.constituencyId}">
							
							<c:forEach var="partyInfo" items="${constituencyInfo.partyVotes}">
								<TR>
									<td><c:out value="${partyInfo.partyName}"/></td>
									<td><c:out value="${partyInfo.candidateNameWithStatus}"/></td>
									<td><c:out value="${partyInfo.totalVotesEarned}"/></td>
									<td><c:out value="${partyInfo.totalVotesEarnedPercentage}"/></td>
									<td><c:out value="${partyInfo.maleBoothResults}"/></td>
									<td><c:out value="${partyInfo.maleBoothResultsPercentage}"/></td>
									<td><c:out value="${partyInfo.femaleBoothResults}"/></td>
									<td><c:out value="${partyInfo.femaleBoothResultsPercentage}"/></td>
									<td><c:out value="${partyInfo.fmBoothResults}"/></td>
									<td><c:out value="${partyInfo.fmBoothResultsPercentage}"/></td>
								</TR>			
							</c:forEach>	
						</table>
						</div>
						</div>
					</fieldset>
				</c:forEach>			
			</c:forEach>
		</c:otherwise>
	</c:choose>	
	
	<script type="text/javascript">
		boothDatatable();
		showACPCResults();
	</script>
</body>
</html>