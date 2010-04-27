<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/tabview/tabview-min.js"></script>


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
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/tabview/assets/skins/sam/tabview.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Revenue Villages Wise Election Results</title>

<style type="text/css">
	#revenueVillageHeading
	{
		color:#2F4C72;
		text-decoration:underline;
	}
	#labelRadio
	{
		font-weight:bold;
		color:#2F4C72;
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
	font-size:14px;
	padding:5px;
}

.revenueDtTable
{
	font-size:12px;
	margin-left:5px;
	margin-top:20px;
}
.revenueDtTable table
{
	width:95%;
}
</style>
<script type="text/javascript">
			function displayVillageElecResults(value){
				
				var resultDiv = document.getElementById("villageElectionResults");
				var rvStr = '';
				<c:forEach var="constiElec" items="${townshipWiseElectionResults}">
					rvStr += '<fieldset>';
					rvStr += '<legend>${constiElec.constituencyName}</legend>';
					rvStr += '<table>';
					rvStr += '<tr>';
					rvStr += '<td style="vertical-align:top;">';
						rvStr += '<div id="candiDiv_${constiElec.constituencyId}"  style="font-size:12px;">'
						rvStr += '<table id="candiTable_${constiElec.constituencyId}">';
						<c:forEach var="candidateInfo" items="${constiElec.candidateNamePartyAndStatus}">
							rvStr += '<tr>';
							rvStr += '<td>${candidateInfo.party}</td>';
							rvStr += '<td>${candidateInfo.candidateName}</td>';
							rvStr += '<td>${candidateInfo.rank}</td>';
							rvStr += '</tr>';
						</c:forEach>
						rvStr += '</table>';		
						rvStr += '</div>';		
					rvStr += '</td>';
					rvStr += '<td style="vertical-align:top;">';
					rvStr += '<img width="700" height="260" src="charts/${constiElec.chartPath}"/>';
					rvStr += '</td>';
					rvStr += '</tr>';
					rvStr += '</table>';					
					rvStr += '<div id="div_${constiElec.constituencyId}" class="revenueDtTable">';
					rvStr += '<table class="censusInfoTable" style="border:1px solid #ADADAD;" id="table_${constiElec.constituencyId}">';
					<c:forEach var="villageElec" items="${constiElec.revenueVillageElectionVO}">
						rvStr += '<tr>';	
						rvStr += '<td>${villageElec.townshipName}</td>';
						<c:forEach var="elecResult" items="${villageElec.partyElectionResultVOs}">
							if(value == "percentage")
								rvStr += '<td>${elecResult.votesPercentage}</td>';
							if(value == "number")
								rvStr += '<td>${elecResult.votesEarned}</td>';
						</c:forEach>
 						rvStr += '</tr>';
					</c:forEach>
					rvStr += '</table>';
					rvStr += '</div>';
					rvStr += '</fieldset>';
				</c:forEach>
				resultDiv.innerHTML = rvStr;			

				
				<c:forEach var="constiElec" items="${townshipWiseElectionResults}">
					 var myColumnDefs = new Array();
					 var myFields = new Array();
			
					 var villageHead = {
								 			key:"Revenue Village",
								 			lable: "Village",
								 			sortable:true
									   }
			
					 var villageValue = {key:"Revenue Village"}
			
					 myColumnDefs.push(villageHead);
					 myFields.push(villageValue);

					 <c:forEach var="candidateInfo" items="${constiElec.candidateNamePartyAndStatus}">
							var obj1 = {
										key:"${candidateInfo.party} [${candidateInfo.rank}]",
										label:"${candidateInfo.party} [${candidateInfo.rank}]",
										sortable:true
									}
							var obj2 = {
										key:"${candidateInfo.party} [${candidateInfo.rank}]",
										parser:"number"
									}
							myColumnDefs.push(obj1);
							myFields.push(obj2);
					 </c:forEach>
					 var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
								.get("table_${constiElec.constituencyId}")); 
					 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
					 myDataSource.responseSchema = { 
											            fields:myFields    
											        };
					        
					var villageDataTable = new YAHOO.widget.DataTable("div_${constiElec.constituencyId}",myColumnDefs, myDataSource);

					//Candidate Party Rank Data Table
					var candidateDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
								.get("candiTable_${constiElec.constituencyId}"));
				    candidateDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
				    candidateDataSource.responseSchema = { 
													            fields: [
																			{
																				key : "pary"
																			},{
																				key : "candidate"
																			},{
																				key : "rank",parser:"number"
																			}
																		]    
													      }; 
					var candidateColumnDefs = [ 
											{key:"pary",label:'Party', sortable:true, resizeable:true}, 
											{key:"candidate", label:'Candidate Name', sortable:true, resizeable:true}, 
											{key:"rank", label:'Rank',sortable:true, resizeable:true}
							        ]; 					 
				        
					var candidateDataTable = new YAHOO.widget.DataTable("candiDiv_${constiElec.constituencyId}",candidateColumnDefs, candidateDataSource);
					 
				</c:forEach>
			}



</script>
</head>
<body>
		<center><div id="revenueVillageHeading"><h4>Revenue Villages Wise ${electionType} ${electionYear } Election Results In ${mandalName} Mandal</h4></div></center> 
		<div>
		<table>
		<tr>
			<td id="labelRadio">Select The Format You Want::</td>
			<td><input type="radio" name="dispaly" value="number" checked="checked" onclick="displayVillageElecResults(this.value)">By Votes</td>
			<td><input type="radio" name="dispaly" value="percentage" onclick="displayVillageElecResults(this.value)">By Percentage</td>
		</tr>
		</table>
			<div id="villageElectionResults" class="yui-skin-sam"></div>
		</div>


<script type="text/javascript">
	displayVillageElecResults("number");
</script>
</body>
</html>