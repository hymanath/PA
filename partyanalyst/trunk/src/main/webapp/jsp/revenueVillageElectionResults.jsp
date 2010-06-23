<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>


<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
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
#townshipPartyResultsPanel .hd
{
	background-image:url(images/icons/contHeading.png);
	background-repeat:repeat;
}
#townshipPartyResultsPanel .bd table
{
	width:100%;
}
.commonVotersHeadDiv  {
	color:#323E4E;
	font-weight:bold;
	margin-bottom:5px;
	padding:5px;
	text-decoration:underline;
}
.reportAnchors {
	color:#3B4B58;
	font-weight:bold;
}
.yui-skin-sam .yui-dt table {
	border:1px solid #7F7F7F;
	border-collapse:separate;
	border-spacing:0;
	font-family:arial;
	font-size:12px;
	margin:0;
	padding:0;
	width:100%;
}
#boothInfoTable {
	border:2px solid #EFEFEF;
}
#boothInfoTable th {
	background-color:#567AAF;
	color:#FFFFFF;
	padding:5px;
	width:20%;
	text-align:left;
	font-size:12px;
}
.yui-skin-sam .yui-panel .bd {
	background-color:#FFFFFF;
}
#townshipPartyResultsPanel .bd table {
	width:100%;
	font-size:12px;
}
.censusInfoTable td {
	background-color:#FFFFFF;
	border:1px solid #D2D9DF;
	padding:8px;
}
.censusInfoTable th {
	background-color:#567AAF;
	border:1px solid white;
	color:#FFFFFF;
	padding:8px;
	text-align:left;
	
}
#boothInfoDiv_head {
	color:#747E84;
	font-weight:bold;
	padding:5px;
	text-decoration:underline;
}
#boothInfoTable td {
	background-color:#F2F6F9;
	font-weight:normal;
	padding:5px;
	font-size:12px;
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

			function callAjax(rparam, jsObj, url){
				var resultVO;			
				var callback = {			
			               success : function( o ) {
								try {								
										resultVO = YAHOO.lang.JSON.parse(o.responseText);										
										
										if(jsObj.task == "getRevenueVillagesInfo")
										{								
											showRevenueVillagesInfo(resultVO);				
										} else if(jsObj.task == "getRevenueVillagesElectionInfo")
										{								
											showRevenueVillageElectionInfo(resultVO,jsObj);			
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
			//var electionType = '${electionType}';
			//var electionYear = '${electionYear}';
			var tehsilId = '${tehsilId}';
			var electId = '${electId}';	
			function getRevenueVillagesInfo(){
		         
				
				
				var jsObj=
					{
							electionId: electId,
							mandalId: tehsilId,
							task:"getRevenueVillagesInfo"						
					};
				
					var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
					var url = "<%=request.getContextPath()%>/getRevenueVillagesInfoAjaxAction.action?"+rparam;						
					callAjax(rparam,jsObj,url);
			}

			function getBoothPageInfo(id){
				var urlStr = "<%=request.getContextPath()%>/boothResultsForAllElectionsPopupAction.action?boothId="+id;
				var browser1 = window.open(urlStr,"boothResultsForAllElections","scrollbars=yes,height=600,width=900,left=200,top=200");
				browser1.focus();
			}
			function showRevenueVillagesInfo(resultVO){
				
				//typeSelectElmt = document.getElementById("electionTypeSelect");
				//yearSelectElmt = document.getElementById("electionYearSelect");		

				//var typeVal = ""+typeSelectElmt.options[typeSelectElmt.selectedIndex].text;
				//var yearVal = ""+yearSelectElmt.options[yearSelectElmt.selectedIndex].text;
				
				var rvStrDiv = document.getElementById('revenueVillagesInfo');
				
				var rvStr = '';		
				rvStr += '<a name="votersDiv"></a>';
				rvStr += '<div id="revenueVillageDiv_head" class="commonVotersHeadDiv">';
				rvStr += 'Voting Trendz In Revenue Villages for ${mandalName} Mandal in ${electionYear} ${electionType}  Election ';
				rvStr += '</div>';
				rvStr += '<div class="yui-skin-sam"><div id="revenueVillageDiv">';		
				rvStr += '<table id="revillageInfoTable" >';
						
				for(var i in resultVO.revenueVillagesInfo)
				{			
					rvStr += '<tr>';
					rvStr += '<td><a href="townshipPageAction.action?TOWNSHIP_ID='+resultVO.revenueVillagesInfo[i].locationId+'&TOWNSHIP_NAME='+resultVO.revenueVillagesInfo[i].locationName+'" >'+resultVO.revenueVillagesInfo[i].locationName+'</a></td>';
					rvStr += '<td>'+resultVO.revenueVillagesInfo[i].population+'</td>';
					rvStr += '<td>'+resultVO.revenueVillagesInfo[i].votesPolled+'</td>';
					rvStr += '<td>';
					for(var j in resultVO.revenueVillagesInfo[i].booths)
					{
						if(j%3 == 0 && j!=0)
							rvStr += '<br>';
						rvStr += '<a href="javascript:{}" onclick="getBoothPageInfo('+resultVO.revenueVillagesInfo[i].booths[j].id+')">'+resultVO.revenueVillagesInfo[i].booths[j].name+',';
					}
					rvStr += '</td>';
					rvStr += '<td>';
					for(var k in resultVO.revenueVillagesInfo[i].hamletsOfTownship)
					{
						rvStr += resultVO.revenueVillagesInfo[i].hamletsOfTownship[k].name+'<br>';
					}
					rvStr += '</td>';
					rvStr += '<td>';
					//rvStr += '<a href = "javascript:{}" class="reportAnchors">Census Info</a><br>';
					rvStr += '<a href = "#votersDiv" class="reportAnchors" onclick = "getTownshipElectionsInfo(\''+resultVO.revenueVillagesInfo[i].locationName+'\','+resultVO.revenueVillagesInfo[i].locationId+','+electId+')"> View Voting Trendz </a><br>';
					//rvStr += '<a href = "javascript:{}" class="reportAnchors">Cast Details</a><br>';
					rvStr += '</td>';
					rvStr += '</tr>';
				}
				rvStr += '</table>';
				rvStr += '</div>';
				rvStr += '</div>';

				if(rvStrDiv)
					rvStrDiv.innerHTML = rvStr;
					var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
							.get("revillageInfoTable")); 
					 myDataSource.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE; 
					  myDataSource.responseSchema = { 
					            fields: [
											{
												key : "townshipName"
											},{
												key : "totalVoters",parser:"number"
											},{
												key : "votesPolled",parser:"number"
											},{
												key : "booths"
											},{
												key : "hamlets"
											},{
												key : "links"
											}
										]    
					        }; 
					
					 var myColumnDefs = [ 
					            {key:"townshipName",label:'Revenue Village', sortable:true, resizeable:true}, 
					            {key:"totalVoters", label:'Total Voters', sortable:true, resizeable:true}, 
					            {key:"votesPolled", label:'Votes Polled', sortable:true, resizeable:true},
					            {key:"booths",label:'Total Booths', resizeable:true}, 
					            {key:"hamlets",label:'Total Hamlets', resizeable:true},
					            {key:"links",label:'Links', resizeable:true}
					        ]; 
					 
					var myDataTable = new YAHOO.widget.DataTable("revenueVillageDiv",myColumnDefs, myDataSource);
					/*      var imgElmt = document.getElementById('AjaxImgDiv');
			 if(imgElmt.style.display == "block")
				{
		           imgElmt.style.display = "none";
				}*/

			}
			function getTownshipElectionsInfo(name,townshipId, electionId){
				var jsObj=
				{
						villageName:name,
						townshipId:townshipId,
						electionId:electionId,
						task:"getRevenueVillagesElectionInfo"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/getRevenueVillagesElectionsAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
			}
			function showRevenueVillageElectionInfo(resultVO,jsObj){

				 var rvEleStr = '';
				 rvEleStr += '<div class="commonVotersHeadDiv"> Voting Trendz Of Different Parties In '+jsObj.villageName+' Revenue Village </div>';
				 rvEleStr += '<table class="censusInfoTable" style="border:1px solid #ADADAD;">';
				 rvEleStr += '<tr>';
				 rvEleStr += '<th style="background-color:#8D7463">Party</th>';
				 for(var i in resultVO){
				 rvEleStr += '<td>'+resultVO[i].partyName+'</td>';
				 }
				 rvEleStr += '</tr><tr>';
				 rvEleStr += '<th style="background-color:#8D7463">Votes Earned</th>';
				 for(var i in resultVO){
				 rvEleStr += '<td>'+resultVO[i].votesEarned+'</td>';
				 }
				 rvEleStr += '</tr>';
				 rvEleStr += '</table>';

				 myPanel = new YAHOO.widget.Panel("townshipPartyResultsPanel", {
				 width: "750px",
				 x:210,
				 y:760,
				 constraintoviewport: true,
				 underlay: "none",
				 close: true,
				 visible: true,
				 draggable: false
				 });
				 
				 myPanel.setHeader(" Revenue Village : "+jsObj.villageName);
				 myPanel.setBody(rvEleStr);
				 myPanel.render(); 
			}
					
</script>
</head>
<body>
		<center><div id="revenueVillageHeading"><h4>Revenue Villages Wise ${electionType} ${electionYear } Election Results In ${mandalName} Mandal</h4></div></center> 
		<div>
		<table>
		<tr>
			<td id="labelRadio">View Results By:</td>
			<td><input type="radio" name="dispaly" value="number" checked="checked" onclick="displayVillageElecResults(this.value)">Votes Gained</td>
			<td><input type="radio" name="dispaly" value="percentage" onclick="displayVillageElecResults(this.value)">Votes Percentage</td>
		</tr>
		</table>
		<div id="villageElectionResults" class="yui-skin-sam"></div>			
		</div>
		<HR>
		<div id="revenueVillagesInfo"></div>
		
		<div class="yui-skin-sam"><div id="townshipPartyResultsPanel" ></div></div>


<script type="text/javascript">
	displayVillageElecResults("number");
	if('${windowTask}' == "includeVotingTrendz" )
	{
		getRevenueVillagesInfo();
	}
</script>
</body>
</html>