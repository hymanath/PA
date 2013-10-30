<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Mandalwise Booth Results in ${constituencyName} For ${partyName} Party</TITLE>

	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>
	

	<!-- YUI Skin Sam -->
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">	
	
<STYLE>

.candidateDetailsStyle {
	background-color:#EFF3F7;
	border:1px solid #96B4D3;
	color:#247CD4;
	font-size:13px;
	font-weight:bold;
	margin-bottom:5px;
	margin:10px;
	padding:4px;
}
h3 {
	background-image: url("images/icons/electionResultsReport/heading.png");
	border-left: 1px solid #CCCCCC;
	border-right :1px solid #CCCCCC;
	color: #006221;
	font-family: MS Sans-serif;
	font-size: 17px;
	font-weight: bold;
	height: 25px;
	margin-bottom: 15px;
	margin-top: 0;
	padding:10px;
	width: 600px;	
}
fieldset {
	border:4px solid #CFD6DF;
	margin-bottom:10px;
	padding:10px;		
}
legend {
	background-color:#567AAF;
	color:#FFFFFF;
	font-size:13px;
	padding:5px;
}
.electionTypeHeader
{
	border-bottom:1px solid #D2D6DB;
	border-left:1px solid #D2D6DB;
	border-right:1px solid #D2D6DB;	
}
.anchorColor
{
	color:#DF7401;
	font-weight:bold;
}
.head
{
	background-image:url("images/icons/electionResultsAnalysisReport/mid.png");
	color:#4B74C6;
	font-size:12px;
	font-weight:bold;
	height:20px;
	margin:0;
	padding:5px;
	text-align:left;
	
}
.votesMarginTable
{
	font-size:12px;
}	
.votesMarginTable th {
	color:#4A515A;
	padding:2px;
	text-align:center;
	
}
.middle {
	background-color:#EAEAE9;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	color:#474B51;
	padding:5px;
	text-align:center;
}
.edge
{
	background-color:#EAEAE9;
	border-bottom:1px solid #DBDCDB;
	color:#474B51;
	padding:5px;
	text-align:center;
}
.head1 {
	background-color:#EBEBEB;
	border-bottom:1px solid #DBDCDB;
	border-right:1px solid #DBDCDB;
	padding:5px;
}		

#boothResultsPopUp_main .yui-panel .bd 
{
	overflow:auto;	
	width:845px;
	height:520px;
}

#detailedBoothResults_main .yui-dt td
{
	padding:2px;
	font-size:12px;
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
#boothInfoDiv_head {
	color:#747E84;
	font-weight:bold;
	padding:5px;
	text-decoration:underline;
}
</STYLE>
<SCRIPT type="text/javascript">
var partyId='${partyId}';
var constId='${constituencyId}';
var boothResultsGlobal = "";
function getDetails()
{
	var jsObj=
	{
			partyId: partyId,
			constituencyId: constId,			
			task:"getDetails"
	}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/mandalwiseBoothResultsForPartyAjaxAction.action?"+rparam;

	callAjax(rparam,jsObj, url);
	
}

function showAllBoothResults(tehsilId,partyId,constituencyId,electionType,electionYear, mandalName)
{
	 var browser2 = window.open("<s:url action="allBoothsResultsInTehsilAction.action"/>?tehsilId="+tehsilId+"&electionType="+electionType+"&electionYear="+electionYear+"&constituencyId="+constituencyId+"&partyId="+partyId+"&mandalName="+mandalName,"AllBoothsResults","scrollbars=yes,height=600,width=1000,left=200,top=200");
	 browser2.focus();	
}

function getBoothPageInfo(id){
		
	var urlStr = "<%=request.getContextPath()%>/boothResultsForAllElectionsPopupAction.action?boothId="+id;
	var browser1 = window.open(urlStr,"boothResultsForAllElections","scrollbars=yes,height=600,width=900,left=200,top=200");
	browser1.focus();		
}
function showBoothResults(index1,index2,index3,index4)
{	
	
	if(boothResultsGlobal)
	{
	var arr = boothResultsGlobal.partyVotesMarginResultsInMandal[index1].partyVotesMarginResultsVO[index2].partyVotesMarginInConstituency[index3].partyResultsInVotesMarginVO[index4].boothResultsVO;
	
	if(arr.length == 0)
		return;
	var detailsArr = new Array();
	for(var i in arr)
	{
		
		var detailsObj = {
				partNo: '<A href="javascript:{}" class="anchorColor" onclick="getBoothPageInfo('+arr[i].boothId+')">'+arr[i].partNo+'</A>',
				location: arr[i].location, 
				villagesCovered: arr[i].villagesCovered,
				mandal: arr[i].mandal,
				votesEarned: arr[i].votesEarned,
				percentage: arr[i].percentage,
				oppParty: arr[i].oppParty,
				oppPartyVotesEarned: arr[i].oppPartyVotesEarned,
				oppPartyPercentage: arr[i].oppPartyPercentage					
							
				};
		detailsArr.push(detailsObj);
	}
	var contentStr ='<div id="detailedBoothResults_main" class="yui-skin-sam">';
	contentStr +='<div id="detailedBoothResults_Datatable"></div>';
	contentStr +='</div>';
	
	 var myPanel = new YAHOO.widget.Dialog("boothResultsPopup", {                 
                
                 fixedcenter : true, 
                 visible : true,  
                 constraintoviewport : true, 
        		 iframe :true,
        		 modal :true,
        		 hideaftersubmit:true,
        		 close:true
       });
	   myPanel.setHeader("Detailed Results ");
       myPanel.setBody(contentStr);
       myPanel.render();

       var resultsDataSource = new YAHOO.util.DataSource(detailsArr);
	   	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	   	resultsDataSource.responseSchema = {
	   		fields : [ {
	   			key : "partNo"
	   		}, {
	   			key : "location"
	   		}, {
	   			key : "villagesCovered"
	   		}, {
	   			key : "mandal"
	   		}, {
	   			key : "votesEarned",parser:"number"
	   		}, {
	   			key : "percentage",parser:"number"
	   		}, {
	   			key : "oppParty"
	   		}, {
	   			key : "oppPartyVotesEarned"
	   		}, {
	   			key : "oppPartyPercentage"
	   		}]
	   	};
   	
		var resultsColumnDefs = [ {
			key : "partNo",
			label : "Booth No"
			
		}, {
			key : "location",
			label : "Location",
			sortable : true
		}, {
			key : "villagesCovered",
			label : "Villages Covered",
			sortable : true
		}, {
			key : "mandal",
			label : "Mandal",
			sortable : true
		}, {
			key : "votesEarned",
			label : "Votes Earned",
			sortable : true
		}, {
			key : "percentage",
			label : "Votes %",
			sortable : true
		}, {
			key : "oppParty",
			label : "Opp Party",
			sortable : true
		}, {
			key : "oppPartyVotesEarned",
			label : "Opp Party VE "
			
		}, {
			key : "oppPartyPercentage",
			label : "Opp Party %"
			
		} ];

		var myConfigs = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 20
	    })
	};


		var myDataTable = new YAHOO.widget.DataTable("detailedBoothResults_Datatable",resultsColumnDefs, resultsDataSource,myConfigs);  
       
	}
}
function callAjax(rparam,jsObj,url)
{		
	var callback = {			
				   success : function( o ) {
						try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task == "getDetails")
								{
									boothResultsGlobal = myResults;
								} else if(jsObj.task == "boothPage")
								{								
									showBoothPagePanel(myResults);			
								}										
								
							}
						catch (e) {   
								//alert("Invalid JSON result" + e);   
							}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function redirectRevenueVillageLink(mandalId,mandalName,partyName)
{
   //alert(" MandalId :" + mandalId + "  MandalName :" + mandalName + "  Party :" + partyName + " partyId :" + partyId);
   
	var browser1 = window.open("<s:url action="revenueVillagePartyAllElecAction.action"/>?partyId="+partyId+"&partyName="+partyName+"&tehsilId="+mandalId+"&mandalName="+mandalName,"browser1","scrollbars=yes,height=600,width=900,left=200,top=200");
	browser1.focus();
}

</SCRIPT>
</HEAD>
<BODY>
<CENTER>
	<div id="boothResultsPopUp_main" class="yui-skin-sam"><div id="boothResultsPopup"></div></div>
	<DIV id="biElectionPage_header">
		<H3>Mandalwise Booth Results for ${partyName} party in ${constituencyName} constituency</H3>
	</DIV>
	<DIV id="boothResultsTable" style="text-align:left;">
		<div id="constiVotersDetails" style="margin-top:20px;">
	     <div style="padding:10px;margin-bottom:20px;"> <u><b> Mandals Votes Share In ${constituencyName} Constituency :</b> </u> </div>
	      <center><table width="85%" border="1" cellspacing="3" style="margin-bottom:10px;">
	      <tr>
	      <c:forEach var="votesShareChart" items="${votesMarginResultsMainVO.constituencyVO.pieChartNames}">
	        <td align="center"><img src="charts/${votesShareChart}" border="0"></td>
	      </c:forEach>
	      </tr>
	      <tr>
	      <c:forEach var="infoForChart" items="${votesMarginResultsMainVO.constituencyVO.extraInfo}">
	        <td align="left" style="border: 0px none ; color: rgb(112, 112, 112);">${infoForChart}</td>
          </c:forEach>
	      </tr>
	      </table></center>
	   </div>
		<c:forEach var="mandalsList" varStatus="status0" items="${votesMarginResultsMainVO.partyVotesMarginResultsInMandal}">
				<FIELDSET>
				<LEGEND><B>Mandal:</B> ${mandalsList.mandalName}</LEGEND>
			 <c:if test="${(errorFlag) != 1}">
			        		<center>
			        		<table>
							<tr>
							  <td align="left"><a href="javascript:{}" onclick="redirectRevenueVillageLink(${mandalsList.mandalId},'${mandalsList.mandalName}','${partyName}')" style="text-decoration:none;" class="candidateDetailsStyle" >Show RevenueVillage Wise Trendz</a>
							  </td>
							</tr>
							<tr>
							</tr>
			        		<tr>
			        		  <td><img src="charts/${mandalsList.chartName}" border="0" /></td>
			        		</tr>
			        		</table>
			        		</center>
			 </c:if>     		   
			   	<TABLE cellspacing="5" border="1" width="100%">
					<TR>
						<c:forEach var="electionsList" varStatus="status1"  items="${mandalsList.partyVotesMarginResultsVO}">
							<TD>								
								<DIV class="electionTypeHeader">
									<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
										<TR>								
											<TD class="head">${electionsList.elecionType} Elections - ${electionsList.electionYear}</TD>										
										</TR>
									</TABLE>
									<TABLE width="100%">
										<TR>
											<c:forEach var="constitueciesList" varStatus="status2" items="${electionsList.partyVotesMarginInConstituency}">
											<TD>
												<DIV style="padding:10px;">
												<DIV style="text-align:center;margin:20px;color:#707070;font-size:13px;">Constituency:<b>${constitueciesList.constituencyName}</b></DIV>
                                                <TABLE class="votesMarginTable" cellpadding="0" cellspacing="0" width="100%">
												            
															<TR>
															    <TD>Total Booths </TD><td style="color:#DF7401;">${constitueciesList.partyResultsOverview.totalBooths}</td>
															</TR>
															<TR>
																<TD>Total Voters </TD><td style="color:#DF7401;">${constitueciesList.partyResultsOverview.totalVoters}</td>
															</TR>
															<TR>
																<TD>Total Polled </TD><td style="color:#DF7401;">${constitueciesList.partyResultsOverview.polledVotes}</td>
															</TR>
															<TR>
															    <TD colspan="4"> <HR> </TD>
															</TR>
															<TR>
															    <TD></TD>
																<TD>Main</TD>
																<TD></TD>
																<TD>Opp</TD>
															</TR>
															<TR>
															     <TD>Party </TD><td style="color:#DF7401;">${partyName}</td>
																 <TD></TD><td style="color:#DF7401;">${constitueciesList.partyResultsOverview.oppParty}</td>
															</TR>
															<TR>
															     <TD>Gained </TD><td style="color:#DF7401;">${constitueciesList.partyResultsOverview.votesEarned}</td>
																 <TD></TD><td style="color:#DF7401;">${constitueciesList.partyResultsOverview.oppVotesEarned}</td>
															</TR>
															<TR >
															     <TD>Votes% </TD>
																 <TD style="color:#DF7401;">${constitueciesList.partyResultsOverview.votesPercent}</TD>
																 <TD></TD><td style="color:#DF7401;">${constitueciesList.partyResultsOverview.oppVotesPercent}</td>
															</TR>
													</TABLE>
												    <DIV style="text-align:left;margin:20px;color:#707070;font-size:13px;"><b>Boothwise Votes % Results</b></DIV>
													<c:if test="${! empty constitueciesList.partyResultsInVotesMarginVO}">											
														<TABLE class="votesMarginTable" cellpadding="0" cellspacing="0" width="100%">
															<TR>
																<TH class="head1"></TH>
																<TH class="head1">Votes %</TH>
																<TH class="head1">No of booths</TH>
															</TR>
															<c:forEach var="rangesList" varStatus="status3" items="${constitueciesList.partyResultsInVotesMarginVO}">				
															<TR>
																<TD align="center" class="middle"><img height="5" width="5" src="images/icons/constituencyPage/bullet_blue.png"></TD>
																<TD align="center" class="middle">${rangesList.marginValue1} - ${rangesList.marginValue2}</TD>
																<c:if test="${rangesList.resultsCount != '0'}">																	
																	<TD align="center" class="edge"><A class="anchorColor" href="javascript:{}" onclick="showBoothResults(${status0.index},${status1.index},${status2.index},${status3.index})">${rangesList.resultsCount}</A></TD>
																</c:if>
																<c:if test="${rangesList.resultsCount == '0'}">																	
																	<TD align="center" class="edge">${rangesList.resultsCount}</TD>
																</c:if>														
															</TR>
															</c:forEach>
														</TABLE>
														<A href="javascript:{}" class="anchorColor" style="font-size:12px;" onclick="showAllBoothResults('${mandalsList.mandalId}', '${partyId}','${constitueciesList.constituencyId}','${electionsList.elecionType}','${electionsList.electionYear}', '${mandalsList.mandalName}')">View All Booths Results</A>
													</c:if>	
												</DIV>
											</TD>
											</c:forEach>
										</TR>
									</TABLE>
								</DIV>								
							</TD>
							<c:if test="${(status1.index)%2 == 1}"></TR></c:if>
						</c:forEach>
					</TR>
				</TABLE>
				</FIELDSET>			
		</c:forEach>
	</DIV>
	
</CENTER>

<SCRIPT type="text/javascript">
getDetails();
</SCRIPT>

</BODY>
</HTML>

