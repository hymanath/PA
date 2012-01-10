<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
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
<link rel="stylesheet" type="text/css" href="styles/districtPage/districtPage.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panchayat Wise Election Results</title>

<style type="text/css">
 
		.mainHeading 
		{
			background-image:url("images/icons/electionResultsReport/heading.png");
			border:0 solid #AEE2FF;
			color:#000000;
			font-family:MS Sans-serif;
			font-size:18px;
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
			font-family: verdana;
			
		}

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

#allPartiesResultsAnc
{
	text-decoration:none;	
	background-image:url('images/icons/electionResultsAnalysisReport/mid.png');
	padding:5px;
	color:#24455E;
}

.votesShareTable th
{
	background:url("js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat scroll 0 0 #D8D8DA;
	border:1px solid #ADADAD;
	padding:5px;
	color:#18325A;
}
.votesShareTable td
{
	border:1px solid #DFDFDF;
	color:#303030;
	font-family:Verdana;
	font-size:12px;
	padding:5px;
	text-align:center;
}

.detailsTableHeader
{
	background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
	height:30px;
	text-align:left;
}

.detailsTableHeaderSpan
{
	position:relative;
	top:5px;
	color:#4B74C6;
	font-weight:bold;
}
#revenueDataTable
{
	width: 950px;
	overflow: auto;
}


</style>

<link rel="stylesheet" type="text/css" href="styles/tv9Styles/tv9Styles.css">

<script type="text/javascript">
			var mandalId = "${tehsilId}" ;
			var mandalName = "${mandalName}";
			var electionType = "${electionType}";
			
			function getTownshipwisePartiesVotesShare(rank)
			{
				var jsObj=
				{
						electionType: electionType,
						mandalId: mandalId,
						rank:rank,
						electionYear:'${electionYear}',
						task:"getTownshipwiseVotesShareInfo"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/getTownshipWiseVotesShareAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
			}

			function displayVillageElecResults(value){
				
				var resultDiv = document.getElementById("villageElectionResults");
				var rvStr = '';
				<c:forEach var="constiElec" items="${townshipWiseElectionResults}">
				
					rvStr += '<fieldset>';
					rvStr += '<legend style="background-color: rgb(86, 122, 175); font-family: verdana,helvetica,clean,sans-serif; color: rgb(255, 255, 255);';
					rvStr += 'font-weight: bold; padding: 10px; font-size: 20px;">${constiElec.constituencyName}</legend>';	

					rvStr += '<table width="100%" border="0">';
					rvStr += '<tr>';
					rvStr += '<td style="vertical-align:top;" align="left">';
					rvStr += '<img width="750" src="charts/${constiElec.chartPath}"/>';
					rvStr += '</td>';
					rvStr += '</tr>';
					rvStr += '<tr>';
					rvStr += '<td><div id="villagewiseVotesShare" style="margin:20px 0px 20px 0px;"></div></td>';
					rvStr += '</tr>';			
					rvStr += '</table>';				
					
					rvStr += '<table cellspacing="0" cellpadding="0" border="0">';
					rvStr += '	<tr>';
					rvStr += '	<td><img src="images/icons/tv9Icons/left_blue_main.png"></td>';
					rvStr += '	<td>';
					rvStr += '	<div style="height: 40px; background-image: url(\'images/icons/tv9Icons/header_body_blue.png\');"><span style="color:#0C2640; font-size: 20px; font-weight: bold; position: relative; top: 7px;">All Parties Performance In Panchayats </span></div></td>';
					rvStr += '	<td><img src="images/icons/tv9Icons/right_blue_main.png"></td>';
					rvStr += '</table>';

					
					rvStr += '<div id="votesPollingInMandalDIV" style="margin-top:40px;">';
					rvStr += '<table id="votesPollingInMandal" width="90%">';
					rvStr += '<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">';
					rvStr += '<tr>';
				    rvStr += '<td width="40%" style="vertical-align:top;">';
				    rvStr += '<div id="votesPolledDtTableDiv_outer" class="yui-skin-sam">';
				    rvStr += '<div id="votesPolledDtTableDiv">';
				    rvStr += '<table id="votesPolledTable">';
				    rvStr += '<c:forEach var="votesPolling" items="${votesPollingInMandal.townshipVotingTrends}">';
				    rvStr += '<tr>';
				    rvStr += '<td>';
				    rvStr += '${votesPolling.townshipName}';
				    rvStr += '</td>';
				    rvStr += '<td>';
				    rvStr += '${votesPolling.percentageOfValidVotes}';
				    rvStr += '</td>';	
				    rvStr += '</tr>';																														
				    rvStr += '</c:forEach>';	
				    rvStr += '</table>';	
				    rvStr += '</div>';
				    rvStr += '</div>';	
				    rvStr += '</td>';
				    rvStr += '<td width="60%" style="vertical-align:top;padding-left:50px;" align="left">';						
				    rvStr += '<c:forEach var="votesPollingInMandal" items="${townshipBoothDetailsVO}">';										
				    rvStr += '<table>';	
				    rvStr += '<tr>';		
				    rvStr += '<td align="left">';	
				    rvStr += '<img src="charts/${votesPollingInMandal.chartName}">';	
				    rvStr += '</td>';		
				    rvStr += '</tr>';		
				    rvStr += '</table>';								
				    rvStr += '</c:forEach>';		
				    rvStr += '</td>';	
				    rvStr += '</tr>';																					
				    rvStr += '</c:forEach>';	
				    rvStr += '</table>';	
				    rvStr += '</div>';		



						
					//rvStr += '<br/>';

					rvStr += '<div id="revenueDataTable" class="yui-skin-sam"><div id="div_${constiElec.constituencyId}" class="revenueDtTable">';
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
					rvStr += '</div></div>';
					
					rvStr += '<br/><br/>';

					rvStr += '<div id="candidateDetails" class="yui-skin-sam">';
					rvStr += '<table align="left" width="50%" border="0">';
					rvStr += '	<tr>';
					rvStr += '	 <td align="left">';
					rvStr += '		<table cellspacing="0" cellpadding="0" border="0">';
					rvStr += '			<tr>';
					rvStr += '				<td width="24px"><img src="images/icons/tv9Icons/left_blue_main.png"></td>';
					rvStr += '				<td>';
					rvStr += '					<div style="height: 40px; background-image: url(\'images/icons/tv9Icons/header_body_blue.png\');"><span style="color:#0C2640; font-size: 20px; font-weight: bold; position: relative; top: 7px;">Participated Candidate Details</span></div></td>';
					rvStr += '				<td><img src="images/icons/tv9Icons/right_blue_main.png"></td>';
					rvStr += '		</table>';					
					rvStr += '	 </td>';
					rvStr += '	</tr>';
					rvStr += '	<tr>';
					rvStr += '	<td>';
						rvStr += '<div id="candiDiv_${constiElec.constituencyId}"  style="font-size:12px;">'
						rvStr += '<table id="candiTable_${constiElec.constituencyId}" width="60%" align="center">';
						<c:forEach var="candidateInfo" items="${constiElec.candidateNamePartyAndStatus}">
							rvStr += '<tr>';
							rvStr += '<td><A href="javascript:{}" title="Click to see Revenue Villageswise Performance Of \'${candidateInfo.party}\'" onclick="openPartywiseResultsWin('+mandalId+',\''+mandalName+'\',${candidateInfo.partyId},\'${candidateInfo.party}\')">${candidateInfo.party}</A></td>';
							rvStr += '<td>${candidateInfo.candidateName}</td>';
							rvStr += '<td>${candidateInfo.rank}</td>';
							rvStr += '</tr>';
						</c:forEach>
						rvStr += '</table>';		
						rvStr += '</div>';		
					rvStr += '</td></tr></table>';											
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
			function openPartywiseResultsWin(mandalId, mandalName, partyId, partyName)
			{
				var browser1 = window.open("<s:url action="revenueVillagePartyAllElecAction.action"/>?partyId="+partyId+"&partyName="+partyName+"&tehsilId="+mandalId+"&mandalName="+mandalName,"browser1","scrollbars=yes,height=630,width=1020,left=200,top=200");
				 browser1.focus();				 					
			}

			function buildVotesPolledDataTable() {
				var resultsDataSourceForTehsil = new YAHOO.util.DataSource(YAHOO.util.Dom
						.get("votesPolledTable"));
				resultsDataSourceForTehsil.responseType = YAHOO.util.DataSource.TYPE_HTMLTABLE;
				resultsDataSourceForTehsil.responseSchema = {
					fields : [  {
						key : "townshipName"
					},{
						key : "percentageOfValidVotes",
						parser:YAHOO.util.DataSourceBase.parseNumber
					}]
				};
		
				var resultsColumnDefsForTehsil = [  {
					key : "townshipName",
					label : "Panchayat Name",
					sortable : true
				},{
					key : "percentageOfValidVotes",
					label : "Votes %",
					sortable : true,
					formatter:YAHOO.widget.DataTable.formatFloat
				}];	
				
				var myDataTableForTehsil = new YAHOO.widget.DataTable("votesPolledDtTableDiv",resultsColumnDefsForTehsil, resultsDataSourceForTehsil);			
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
										else if(jsObj.task == "getTownshipwiseVotesShareInfo")
										{
											buildVillagewiseVotesShare(jsObj,resultVO);
										}
															
								}catch (e)  {   
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
			//var electionType = '${electionType}';
			//var electionYear = '${electionYear}';
			var tehsilId = '${tehsilId}';
			var electId = '${electId}';	

			function getRadioStr(rank)
			{
				var str = '';
				if(rank == '1')
					str+='<input type="radio" checked="checked" name="rank" value="1" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 1';
				else
					str+='<input type="radio" name="rank" value="1" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 1';

				if(rank == '2')
					str+='<input type="radio" checked="checked" name="rank" value="2" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 2';
				else
					str+='<input type="radio" name="rank" value="2" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 2';

				if(rank == '3')
					str+='<input type="radio" checked="checked" name="rank" value="3" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 3';
				else
					str+='<input type="radio" name="rank" value="3" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 3';

				if(rank == '4')
					str+='<input type="radio" checked="checked" name="rank" value="4" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 4';
				else
					str+='<input type="radio" name="rank" value="4" onclick="getTownshipwisePartiesVotesShare(this.value);"/> 4';

				return str;
			}

			function buildVillagewiseVotesShare(jsObj,results)
			{
				var elmt = document.getElementById("villagewiseVotesShare");
				if(!elmt)
					return;

				var str = '';
				
				str += '<table width="950px" class="votesShareTable">';
				str += '<tr>';
				str += '<td colspan="9" style="padding:0px;border:0px;">';
				str+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
				str+='			<tr>';
				str+='			<td width="2%" style="padding:0px;border:none;">';
				str+='				<img src="images/icons/tv9Icons/first.png"></td>';
				str+='			<td width="98%" style="padding:0px;border:none;">';
				str+='				<div class="detailsTableHeader" style="width:100%;">';
				str+='				<table width="100%" border="0" cellpadding="0" cellmargin="0">';
				str+='				<tr>';
				str+='				<td width="60%" align="left" style="border:0px;padding:0px;text-align:left;"><span class="detailsTableHeaderSpan">  All Parties Vote Bank Based On Position - '+jsObj.rank+'</span></td>';
				str+='				<td width="40%" style="border:none;padding:0px;text-align:right;" align="right">';
				str+='					<span class="detailsTableHeaderSpan">';
				str+='						Select Rank - ';
				str+=						getRadioStr(jsObj.rank);
				str+='					</span>';
				str+='				</td>';
				str+='				</tr>';
				str+='				</table>';				
				str+='				</div>';
				str+='			</td>';
				str+='			<td width="1%" style="padding:0px;border:none;"><img src="images/icons/tv9Icons/second.png"></td>';
				str+='			</tr>';
				str+='		</table>';
				str += '</td>';
				str += '</tr>';
				str += '<tr>';
				str += '<th>Party</th>';
				str += '<th>No. Of Panchayats</th>';
				str += '<th>Panchayats</th>';
				str += '<th>Total Voters</th>';
				str += '<th>Valid Votes</th>';
				str += '<th>Votes Polled</th>';
				str += '<th>Votes %</th>';
				str += '<th>Votes % Share In Mandal</th>';
				str += '<th>Votes % Share In Constituency</th>';
				str += '</tr>';
				for(var i in results)
				{
					str += '<tr>';
					str += '<td>'+results[i].partyName+'</td>';
					str += '<td>'+results[i].wonVillagesCount+'</td>';
					str += '<td style="text-align:left;">'+results[i].townships+'</td>';
					str += '<td>'+results[i].totVoters+'</td>';
					str += '<td>'+results[i].totValidVotes+'</td>';
					str += '<td>'+results[i].totVotesEarned+'</td>';
					str += '<td>'+results[i].votesShareInVill+'</td>';
					str += '<td>'+results[i].votesShareInMandal+'</td>';
					str += '<td>'+results[i].votesShareInConsti+'</td>';
					str += '</tr>';
				}
				str += '</table>';
						
				elmt.innerHTML = str;
			}

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
												
				var rvStrDiv = document.getElementById('revenueVillagesInfo');
			
				var rvStr = '';		
				rvStr += '<a name="votersDiv"></a>';

				rvStr += '		<table cellspacing="0" cellpadding="0" border="0">';
				rvStr += '			<tr>';
				rvStr += '				<td><img src="images/icons/tv9Icons/left_blue_main.png"></td>';
				rvStr += '				<td>';
				rvStr += '					<div id="revenueVillageDiv_head" style="height: 40px; background-image: url(\'images/icons/tv9Icons/header_body_blue.png\');">';
				rvStr +='<span style="color:#0C2640; font-size: 20px; font-weight: bold; position: relative; top: 7px;">';
				rvStr +=' Voting Trendz In Revenue Villages for ${mandalInfoVO.mandalName} Mandal in  ${electionYear} ${electionType} Election </span></div></td>';
				rvStr += '				<td><img src="images/icons/tv9Icons/right_blue_main.png"></td>';
				rvStr += '		</table>';
		
			
				for(var k in resultVO.partiesResultsInVillages){                                                                                  
					rvStr += '<div style="margin-top:10px;margin-bottom:10px;"><b>'+resultVO.partiesResultsInVillages[k].constituencyName+' ${electionType} In ${electionYear}</b></div>';
					rvStr += '<div id="villagesVotingTrendz" class="yui-skin-sam"><div id="revenueVillageDiv_'+k+'">';	
					rvStr += '<table id="revillageInfoTable_'+k+'" >';
					for(var i in resultVO.partiesResultsInVillages[k].revenueVillagesInfo)
					{			
						rvStr += '<tr>';
						rvStr += '<td><a href="townshipPageAction.action?TOWNSHIP_ID='+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationId+'&TOWNSHIP_NAME='+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationName+'" >'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationName+'</a></td>';
						rvStr += '<td>'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].population+'</td>';
						rvStr += '<td>'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].votesPolled+'</td>';
						rvStr += '<td>';
						for(var j in resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].booths)
						{
							if(j%3 == 0 && j!=0)
								rvStr += '<br>';
							rvStr += '<a href="javascript:{}" onclick="getBoothPageInfo('+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].booths[j].id+')">'+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].booths[j].name+',';
						}
						rvStr += '</td>';
						rvStr += '<td>';
						for(var j in resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].hamletsOfTownship)
						{
							rvStr += resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].hamletsOfTownship[j].name+'<br>';
						}
						rvStr += '</td>';
						rvStr += '<td>';
						//rvStr += '<a href = "javascript:{}" class="reportAnchors">Census Info</a><br>';
						//rvStr += '<a href = "#votersDiv" class="reportAnchors" onclick = "getTownshipElectionsInfo(\''+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationName+'\','+resultVO.partiesResultsInVillages[k].revenueVillagesInfo[i].locationId+','+electionId+')"> View Voting Trendz </a><br>';
						//rvStr += '<a href = "javascript:{}" class="reportAnchors">Cast Details</a><br>';
						rvStr += '</td>';
						rvStr += '</tr>';
					}
					rvStr += '</table>';
					rvStr += '</div>';
					rvStr += '</div>';
					
				}			

				if(rvStrDiv)
					rvStrDiv.innerHTML = rvStr;
				for(var k in resultVO.partiesResultsInVillages){
					var myDataSource = new YAHOO.util.DataSource(YAHOO.util.Dom
							.get("revillageInfoTable_"+k)); 
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
					            {key:"townshipName",label:'Panchayat', sortable:true, resizeable:true}, 
					            {key:"totalVoters", label:'Total Voters', sortable:true, resizeable:true}, 
					            {key:"votesPolled", label:'Votes Polled', sortable:true, resizeable:true},
					            {key:"booths",label:'Total Booths', resizeable:true}, 
					            {key:"hamlets",label:'Total Hamlets', resizeable:true},
					            {key:"links",label:'Links', resizeable:true}
					        ]; 
					 
					var myDataTable = new YAHOO.widget.DataTable("revenueVillageDiv_"+k,myColumnDefs, myDataSource);
				}
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
			
			function getRevenueVillageElectionResults(selectedYear,electionId){
				var brow1 = window.open("<s:url action="townshipElectionResultsAction"/>?mandalId="+mandalId+"&electionId="+electionId+"&mandalName="+mandalName+"&electionType="+electionType+"&electionYear="+selectedYear+"&windowTask=includeVotingTrendz","brow1","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
				brow1.focus();
			}	
</script>
</head>
<body style="width:130%">
	
	
<table width="100%" bgcolor="black" cellpadding="0" cellspacing="0">
		<tbody><tr>		
		<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"></td>
			<td width="72%" align="left">
				<table cellspacing="0" cellpadding="0" border="0" style="margin-left:30px;">
				<tbody><tr>
					<td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo1.png"></td>
					<td valign="top"><div class="mainHeading" id="mainHead"> Panchayat Wise ${electionType} ${electionYear } Results In ${mandalName} Mandal</div></td>
					<td valign="top"><img border="none" style="margin-top: 15px;" src="images/icons/electionResultsReport/elections_logo2.png"></td>
				</tr>
				</tbody></table>
			</td>
			<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"></td>
		</tr>
	</tbody></table>

		<div class="inputSelectionCriteria">
		<table width="80%" border="0">
		<tr>
			<td id="labelRadio">Select Election Year</td>
			<td>		
				<s:select id="selectPartyPPR" theme="simple"  name="selectParty" list="allElectionYears" listKey="id" listValue="name" onchange="getRevenueVillageElectionResults(this.options[this.selectedIndex].text,this.options[this.selectedIndex].value)"></s:select>			
			</td>
			<td>
				<table border="0" cellspacing="0" cellpadding="0" style="margin-left:50px;">
				<tr>
				<td><img src="images/icons/electionResultsAnalysisReport/first.png"/></td>

				<td>
				<a id="allPartiesResultsAnc" href="mandalRevenueVillagesElecInputAction.action?tehsilName=${mandalName}&tehsilId=${tehsilId}"><b>View All Parties Results In All Elections</b></a>
				</td>
				<td><img src="images/icons/electionResultsAnalysisReport/second.png"/></td>
				</tr>
				</table>
			</td>
		</tr>		
		<tr>
			<td id="labelRadio">View Results By:</td>
			<td><input type="radio" name="dispaly" value="number" checked="checked" onClick="displayVillageElecResults(this.value)">Votes Polled
			<input type="radio" name="dispaly" value="percentage" onClick="displayVillageElecResults(this.value)">Votes Percentage</td>
			<td>&nbsp;</td>
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
	buildVotesPolledDataTable();
	getTownshipwisePartiesVotesShare('1');
</script>
</body>
</html>