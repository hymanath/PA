<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Mandalwise Booth Results in ${constituencyName} For ${partyName} Party</TITLE>
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
	
	
<STYLE>
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

	callAjax(jsObj, url);
	
}

function showAllBoothResults(index1,index2,index3)
{
	if(!boothResultsGlobal)
		return;

	var arr = boothResultsGlobal.partyVotesMarginResultsInMandal[index1].partyVotesMarginResultsVO[index2].partyVotesMarginInConstituency[index3].boothResults
	
	if(arr.length == 0)
		return;

	var resultsStr = '';
	resultsStr += '<HTML>';
	resultsStr += '<HEAD><TITLE>All Booth Results </TITLE>';
	resultsStr += '<style>';
	resultsStr += '#boothResultsPopupTable {border-collapse:collapse;}';
	resultsStr += '#boothResultsPopupTable td{	padding:4px;font-size:12px;color:#555E6B;}';
	resultsStr += '#boothResultsPopupTable th{	padding:5px;font-size:13px;background-color:#2760A9;color:#FFFFFF;}';
	resultsStr += '</style>';
	resultsStr += '</HEAD>';
	resultsStr += '<body>';
	resultsStr += '<div id="allPartiesBoothResults">';
	resultsStr += '<table id="boothResultsPopupTable" border="1" width="100%">';
	resultsStr += '<tr>';
	resultsStr += '<th>Booth No</th>';
	resultsStr += '<th>Location</th>';
	resultsStr += '<th>Villages Covered</th>';
	resultsStr += '<th>Mandal</th>';
	resultsStr += '<th>Votes Earned</th>';
	resultsStr += '<th>Votes %</th>';
	resultsStr += '<th>Opp Party</th>';
	resultsStr += '<th>Opp Party VE</th>';
	resultsStr += '<th>Opp Party %</th>';
	resultsStr += '</tr>';
	
	for(var i in arr)
	{
		resultsStr += '<tr>';
		resultsStr += '<td align="center">'+arr[i].partNo+'</td>';
		resultsStr += '<td>'+arr[i].location+'</td>';
		resultsStr += '<td>'+arr[i].villagesCovered+'</td>';
		resultsStr += '<td align="center">'+arr[i].mandal+'</td>';
		resultsStr += '<td align="center">'+arr[i].votesEarned+'</td>';
		resultsStr += '<td align="center">'+arr[i].percentage+'</td>';
		resultsStr += '<td align="center">'+arr[i].oppParty+'</td>';
		resultsStr += '<td align="center">'+arr[i].oppPartyVotesEarned+'</td>';
		resultsStr += '<td align="center">'+arr[i].oppPartyPercentage+'</td>';
		resultsStr += '</tr>';
	}

	resultsStr += '</table>';
	resultsStr += '</div>';
	resultsStr += '</BODY></HTML>';

	var allPartiesBoothResults = window.open("","allPartiesBoothResults","width=1050,height=600,menubar=no,status=no,location=no,toolbar=no,scrollbars=yes");
	allPartiesBoothResults.focus();
	allPartiesBoothResults.document.open("text/html", "replace");
	allPartiesBoothResults.document.write(resultsStr);			
	allPartiesBoothResults.document.close();
	
	/*var elmt = allPartiesBoothResults.document.getElementById("boothResultsTable");
	var divElmt = allPartiesBoothResults.document.getElementById("allPartiesBoothResults");

	var resultsDataSource = new YAHOO.util.DataSource(arr);
	console.log(resultsDataSource);
	   	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	   	resultsDataSource.responseSchema = {
	   		fields : [ {
	   			key : "partNo",parser:"number"
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
			parser:"number",
			label : "Booth No",
			sortable : true
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
			label : "Opp Party VE ",
			sortable : true
			
		}, {
			key : "oppPartyPercentage",
			label : "Opp Party %",
			sortable : true			
		} ];

		var myConfigs = {
	    paginator : new YAHOO.widget.Paginator({
	        rowsPerPage: 20
	    })
	};


	var myDataTable = new YAHOO.widget.DataTable(divElmt.id,resultsColumnDefs, resultsDataSource,myConfigs);  */
}

function showBoothResults(index1,index2,index3,index4)
{	
	if(boothResultsGlobal)
	{
	var arr = boothResultsGlobal.partyVotesMarginResultsInMandal[index1].partyVotesMarginResultsVO[index2].partyVotesMarginInConstituency[index3].partyResultsInVotesMarginVO[index4].boothResultsVO;

	if(arr.length == 0)
		return;

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

       var resultsDataSource = new YAHOO.util.DataSource(arr);
	   	resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	   	resultsDataSource.responseSchema = {
	   		fields : [ {
	   			key : "partNo",parser:"number"
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
			parser:"number",
			label : "Booth No",
			sortable : true
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
function callAjax(jsObj,url)
{					
	var callback = {			
				   success : function( o ) {
						try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task == "getDetails")
								{
									boothResultsGlobal = myResults;
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

</SCRIPT>
</HEAD>
<BODY>
<CENTER>
	<div id="boothResultsPopUp_main" class="yui-skin-sam"><div id="boothResultsPopup"></div></div>
	<DIV id="biElectionPage_header">
		<H3>Mandalwise Booth Results for ${partyName} party in ${constituencyName} constituency</H3>
	</DIV>
	<DIV id="boothResultsTable" style="text-align:left;">
		<c:forEach var="mandalsList" varStatus="status0" items="${votesMarginResultsMainVO.partyVotesMarginResultsInMandal}">
				<FIELDSET>
				<LEGEND><B>Mandal:</B> ${mandalsList.mandalName}</LEGEND>
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
												<DIV style="text-align:center;margin:10px;color:#707070;font-size:13px;">Constituency:${constitueciesList.constituencyName}</DIV>
													<c:if test="${! empty constitueciesList.partyResultsInVotesMarginVO}">											
														<TABLE class="votesMarginTable" cellpadding="0" cellspacing="0" width="100%">
															<TR>
																<TH class="head1"></TH>
																<TH class="head1">Votes %</TH>
																<TH class="head1">No of booths</TH>
															</TR>
															<c:forEach var="rangesList" varStatus="status3" items="${constitueciesList.partyResultsInVotesMarginVO}">				
															<TR>
																<TD align="center" class="middle"><img height="5" width="5" src="/PartyAnalyst/images/icons/constituencyPage/bullet_blue.png"></TD>
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
														<A href="javascript:{}" class="anchorColor" style="font-size:12px;" onclick="showAllBoothResults(${status0.index},${status1.index},${status2.index})">View All Booths Results</A>
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

