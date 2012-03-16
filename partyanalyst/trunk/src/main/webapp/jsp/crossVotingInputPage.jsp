<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cross Voting Report</title>

	<!-- YUI Dependency Files-->
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
	<script src="js/yahoo/yui-js-2.8/build/paginator/paginator-min.js"></script>

	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

	<script type="text/javascript" src="js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
	<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
	
	<link rel="stylesheet" type="text/css" href="styles/mandalPage/mandalPage.css">

<style type="text/css">

#crossVotingInputDiv
{
	float: left;
    margin-left: 36px;
    padding: 16px;
}
#labelspan
{
	color:#724400;
	text-decoration:underline;
}
.crossVotingInputTable td
{
	color:#724400;
	padding:3px;
}
#crossVotingResultDiv
{
	text-align: left; margin-top: 30px; margin-left: 50px;
}
#candidateDetailsDiv
{
	padding-left: 10px;
}
#assemblyCandidateDiv
{
	float:left;	
	background-color:#EFEFEF;
	border:2px outset #CCCCCC;
}
#parliamentCandidateDiv
{
	background-color:#EFEFEF;
	margin-left:480px;
	margin-right:20px;
	border:2px outset #CCCCCC;
}
#treeDiv
{
	margin-top: 30px;
}
.detailsTable td
{
	padding:5px;
	color:#156FAB;
}
.detailsTable th
{
	padding:5px;
	color:#234E7A
}
.detailsTable a
{
	color:#156FAB;
	font-size:12px;
}

.mandalDatatable th
{
	color:#94A3AC;	
	padding-right:5px;
}
.mandalDatatable td
{
	color:#275D81;
	padding-right:5px;	
}
.mandalDiv
{
	padding:5px;
}
.msgspan
{
	font-weight:bold;
	margin-left:100px;
}

#constDetails
{
	padding:5px;
	background-color:#efefef;
	margin-top:10px;
	margin-right:10px;
}
#constDetailsHead
{
	color:#234E7A;
	padding:5px;
	font-weight:bold;
	text-decoration:underline;
}
#constDetailsBody
{
	color:#AAAAAA;
}
#indexDataDiv
{
	color:#AAAAAA;
	font-size:11px;
	padding:10px 100px 10px 10px;
	text-align:right;
}

.yui-skin-sam .yui-dt table
	{
		border:medium none Chocolate;
		border-collapse:separate;
		border-spacing:3px;
		color:RoyalBlue;
		font-family:Verdana;
		font-size:12px;
		text-align:center;
		
	}

 .yui-skin-sam .yui-pg-container {paginator.css (line 7)
	display:block;
	margin:6px 0;
	text-align:center;
	white-space:nowrap;
 }
#headDivStyle{
	color: #ffffff;
    font-size: 20px;
    font-weight: normal;
    padding:20px;
}
#headingImgStyle{
	 background-color: #0B3861;
	background-repeat:no-repeat;
	background-size:190px auto;
	height: 25px;
    width: 190px;
}
h4.headStyle
{
	font-size:14px;
}
td.tdStyle{
    font-family: times New Roman;
    font-size: 16px;
    font-weight: bold;
}
 select.selectstyle{
 width:150px;
 }
 #checkBoxStyle{
	 font-family: times New Roman;
    font-size: 14px;
    font-weight: bold;
 }
</style>
<script type="text/javascript">

function getParty()
{
    var img = document.getElementById("ajaxImg3");
	img.style.display = 'block';
	
	var elecYearElmt = document.getElementById("electionYearField");
	var assemblyElmt = document.getElementById("AssemblySelect");

	if(!elecYearElmt || !assemblyElmt)
		return;

	var elecValue =  elecYearElmt.options[elecYearElmt.selectedIndex].value;
	var assemblyValue =  parseInt(assemblyElmt.options[assemblyElmt.selectedIndex].value);
		
	if(elecValue == -1 || assemblyValue == -1 || isNaN(assemblyValue))
		return;
	else
	{
		var jsObj={						
					electionYear:elecValue,
					assemblyVal:assemblyValue ,
					task:"getParty"
			  }
		
		var bparam="assemblyValue="+jsObj.assemblyVal+"&election="+jsObj.electionYear;
		callAjax(jsObj,bparam);
	}
}


	
function getAssembly()
{
	var img = document.getElementById("ajaxImg2");
	img.style.display = 'block';

	var parliamentSelectElmt =  document.getElementById("parliamentField");
	var electionYearElmt =  document.getElementById("electionYearField");	

	if(!parliamentSelectElmt || !electionYearElmt)
		return;

	var parliamentValue =  parseInt(parliamentSelectElmt.options[parliamentSelectElmt.selectedIndex].value);
	var electionYearValue =  parseInt(electionYearElmt.options[electionYearElmt.selectedIndex].value);
					
	if(parliamentValue == -1 || electionYearValue == -1 || isNaN(parliamentValue) )
		return;
	else
	{
		var jsObj={
					parliamentValue : parliamentValue,
					electionYear:electionYearValue,
					task:"Assembly"
			  }
		
		var bparam="parliamentValue="+jsObj.parliamentValue+"&election="+jsObj.electionYear;
		callAjax(jsObj,bparam);
	}
}


	function getBoothPageInfo(id){
		var urlStr = "<%=request.getContextPath()%>/boothResultsForAllElectionsPopupAction.action?boothId="+id;
		var browser1 = window.open(urlStr,"boothResultsForAllElections","scrollbars=yes,height=600,width=900,left=200,top=200");
		browser1.focus();	
	}
	
	function callAjax(jObj,param)
	{
		var myResults;		
		var boothUrl = "<%=request.getContextPath()%>/crossVotingReportAjaxAction.action?"+param; 
 		var callback = {			
 		               success : function( o ) {
							try {
								var img1=document.getElementById('ajaxImg1');
								img1.style.display='none';

								var img2=document.getElementById('ajaxImg2');
								img2.style.display='none';

								var img3=document.getElementById('ajaxImg3');
								img3.style.display='none';

								myResults = YAHOO.lang.JSON.parse(o.responseText); 										
								if(jObj.task == "Assembly" || jObj.task=="getParty" || jObj.task=="getParliament")
									buildParliamemtSelect(jObj,myResults.dataList);
								else if(jObj.task == "crossVotingReport")
									buildCrossVotingReport(jObj,myResults.crossVotingConsolidateVO);
												
							}catch (e) {   
							   	alert("Invalid JSON result" + e);   
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('GET', boothUrl , callback);
	}
    function setDefaultImage(img)
    {
		img.src = "images/candidates/default.JPG";
     }

	function buildCrossVotingReport(obj,result)
	{
		
		var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
		ajaxImgElmt.style.display = "none";


		var resultDiv = document.getElementById("crossVotingResultDiv");
		resultDiv.style.display="block";
		

		var elecYearElmt = document.getElementById("electionYearField");
		var partyElmt = document.getElementById("PartySelect");
		var parliamentSelectElmt =  document.getElementById("parliamentField");
		var assemblySelectElmt = document.getElementById("AssemblySelect");

        var partyValue = partyElmt.options[partyElmt.selectedIndex].text;
		var parliamentValue = parliamentSelectElmt.options[parliamentSelectElmt.selectedIndex].text;
		var electionValue = elecYearElmt.options[elecYearElmt.selectedIndex].text;
		var assemblyValue = assemblySelectElmt.options[assemblySelectElmt.selectedIndex].text;
		
		if(result.partyPartisipated == false)
		{	
			var str='';
			str+='<span class="msgspan">';
			str+=partyValue+" did not participate in "+parliamentValue+" Parliament in the year "+electionValue;
			str+='</span>';
			resultDiv.innerHTML=str;
			return;
		}
		else if(result.hasAlliance == false)
		{
			var str='';
			str+='<span class="msgspan">';
			str+=partyValue+" didn't have alliance in the year "+electionValue;
			str+='</span>';
			resultDiv.innerHTML=str;
			return;
		}
		else
		{
			var str='';
			str+='<div id="headingDiv"><h3><u>Cross Voting Details for <a href="partyPageAction.action?partyId ='+partyValueId+'">'+ partyValue +' </a> Party in '+ electionValue +' <a href="constituencyPageAction.action?constituencyId = '+assemblyValueId+'">'+ assemblyValue +'</a> Assembly Constituency</u></h3></div>';
			str+='<div id="candidateDetailsDiv">';
				str+='<div id="assemblyCandidateDiv">';
					str+='<table id="assemblyTable" class="detailsTable" style="width:auto;" >';
					str+='<tr><th colspan="2"><u>Assembly Candidate Details..</u></th>';		
					str+='</tr>';
					str+='<tr>';
					str+='<th>Name</th>';
					str+='<td><a href="candidateElectionResultsAction.action?candidateId='+result.acCandidateData.candidateId+'">'+result.acCandidateData.candidateName+'</a></td>';
				    str+='<td rowspan="3"><img height="90" width="90" onerror="setDefaultImage(this)" src="images/candidates/'+result.acCandidateData.candidateName+'.jpg"></td>';
					//str+='<td rowspan="3"><img  height="90" width="90" src="<%=request.getContextPath()%><s:property value="getText('imageURL')" />default.JPG" ></td>';
					str+='</tr>';
					str+='<tr><th>Rank</th><td>'+result.acCandidateData.rank+' &nbsp;&nbsp;&nbsp; <b>Party:</b> '+ result.acCandidateData.party +'</td></tr>';
					
					str+='<tr><th>Votes Gained</th><td>'+result.acCandidateData.votesEarned+' ('+result.acCandidateData.votesPercentage+' %)</td></tr>';
					str+='<tr><td colspan="3"><table cellpadding="0" cellspacing="0"><tr><th>Total Voters (AC<font color="red">*</font>) :</th><td>'+result.totalVotersInAC+' </td><th>Total Polled Votes</th><td>'+ result.acCandidateData.polledVotes +'</td></tr></table></td></tr>';
					str+='</table>';		
				str+='</div>';
				str+='<div id="parliamentCandidateDiv">';
					str+='<table id="parliamentTable" class="detailsTable" style="width:auto;">';
					str+='<tr><th colspan="2"><u>Parliament Candidate Details..</u></th></tr>';
					str+='<tr><th>Name</th>';
					str+='<td> <a href="candidateElectionResultsAction.action?candidateId='+result.pcCandidateData.candidateId+'">'+result.pcCandidateData.candidateName+'</a></td>';
					str+='<td rowspan="3"><img height="90" width="90" onerror="setDefaultImage(this)" src="images/candidates/'+result.pcCandidateData.candidateName+'.jpg"></td>';
				    str+='</tr>';
					str+='<tr><th>Rank</th><td>'+result.pcCandidateData.rank+' &nbsp;&nbsp;&nbsp;<b>Party:</b> '+ result.pcCandidateData.party+'</td></tr>';

					str+='<tr><th>Votes Gained</th><td>'+result.pcCandidateData.votesEarned+' ('+result.pcCandidateData.votesPercentage+' %)</td></tr>';
					str+='<tr><td colspan="3"><table cellpadding="0" cellspacing="0"><tr><th>Total Voters (PC<font color="red">*</font>) :</th><td>'+result.totalVotersInPC+' </td><th>Total Polled Votes</th><td>'+ result.pcCandidateData.polledVotes +'</td></tr></table></td></tr>';
					str+='</table>';	
				str+='</div>';		
				str+='<div style="margin-top:5px; font-weight:bold;">NOTE: Votes Gained & Total Polled Votes = Boothwise Votes Polled In An Assembly Constituency</div>';
				str+='<div id="constDetails">';
					str+='<div id="constDetailsHead">Cross Voting & Impact Details </div>'
					
					str+='<table>';
					str+='<tr><td colspan="2"><br><b>Votes Percentage Difference between Assembly Candidate and Parlament Candidate: '+result.differenceInACAndPC+'%</b></td></tr>';
					str+='<tr><td colspan="2"><br><b>Votes Percentage Difference in Assembly Impact On Parliament: '+result.impactOfAssemblyOnParliament+'%</b></td></tr>';	
					str+='</table>';
					
				str+='</div>';
				str+='<div id="treeDiv">';
				str+='	<span><h4><u>Voting Details in Mandal/s:</u></h4></span>';
				str+='	<div id="treeDataDiv" class="yui-skin-sam"></div>';
				str+='	<div  id="indexDataDiv"> AC* - Assembly Candidate, PC* - Parliament Candidate, IC* - Impact On Constituency</div>';
				str+='</div>';
			str+='</div>';


			resultDiv.innerHTML=str;
			
			for(var i in result.mandals)
			{
				buildTreeView(result.mandals[i]);
			}
		}
	}

	function hideAndUnhide(name)
	{
		var divElmt = "mandalNewDiv_" + name;
		var dataTableDiv = document.getElementById(divElmt);
		var imgDivElmt = "hideUnhideImg_" + name;
        var imgId = document.getElementById(imgDivElmt);

        if(dataTableDiv.style.display == "none")
		{
        dataTableDiv.style.display="block";
		imgId.src = "images/icons/minusNew.png";
        }
		else if(dataTableDiv.style.display == "block")
		{
        dataTableDiv.style.display="none";
		imgId.src = "images/icons/plusNew.png";
		}
	}

	function buildTreeView(mandal)
	{
		
		var divElmt = document.getElementById("treeDataDiv");
						
		var childDivElmt = document.createElement('div');
		childDivElmt.setAttribute('id','mandalDiv_'+mandal.mandalName);
		childDivElmt.setAttribute('class','mandalDiv');
		divElmt.appendChild(childDivElmt);

		var tree;	
		tree = new YAHOO.widget.TreeView(childDivElmt.id);			
		var rootNode = tree.getRoot(); 	

		var mstr='';
		mstr+='<table class="mandalDatatable">';
		mstr+='<tr>';
		mstr+='<td><a href="#" onclick="hideAndUnhide(\''+mandal.mandalName+'\')" ><img id="hideUnhideImg_'+mandal.mandalName+'" src="images/icons/plusNew.png" border="none"/></a></td>';
		mstr+='<th style="width:140px;">'+mandal.mandalName+'</th>';		
		mstr+='<th>|Polled Votes :</th>';
		mstr+='<td style="width:50px;">'+mandal.polledVotes+'</td>';
		mstr+='<th>| AC<font color="red">*</font> :</th>';
		mstr+='<td style="width:50px;">'+mandal.acPercentageInMandal+'  %</td>';
		mstr+='<th>| PC<font color="red">*</font> :</th>';
		mstr+='<td style="width: 50px;">'+mandal.pcPercentageInMandal+'  %</td>';
		mstr+='<th>| Votes Flown :</th>';
		mstr+='<td style="width: 50px;" colspan="2">'+mandal.percentageDifferenceInMandal+' %</td>';	
		mstr+='<th>| IC<font color="red">*</font>:</th>';
		mstr+='<td style="width: 50px;">'+mandal.percentageImpactOnConstituency+' %</td>';	
		mstr+='</tr>';
		mstr+='</table>'

		var label = mandal.mandalName;	
		var mandalNode = new YAHOO.widget.TextNode(mstr, rootNode);
		
		var childNewDivElmt = document.createElement('div');
		childNewDivElmt.setAttribute('id','mandalNewDiv_'+mandal.mandalName);
		childNewDivElmt.style.display="none";
		childNewDivElmt.style.width = "880px"
		childNewDivElmt.style.overflow = "scroll";	
		divElmt.appendChild(childNewDivElmt);
		
		for(var i in mandal.crossVotedBooths){
			mandal.crossVotedBooths[i].partNO = '<a href="javascript:{}" onclick="getBoothPageInfo('+mandal.crossVotedBooths[i].boothId+')">'+mandal.crossVotedBooths[i].partNO+'</a>';
		}
		
		var resultsDataSource = new YAHOO.util.DataSource(mandal.crossVotedBooths);
		resultsDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
		resultsDataSource.responseSchema = {
			fields : [ {
				key : "partNO"
			}, {
				key : "villagesCovered"
			}, {
				key : "totalVoters",parser:"number"
			}, {
				key : "acValidVotes",parser:"number"
			}, {
				key : "acVotesEarned",parser:"number"
			}, {
				key : "pcVotesEarned",parser:"number"
			}, {
				key : "acPercentage",parser:"number"
			}, {
				key : "pcPercentage",parser:"number"
			}, {
				key : "percentageDifference",parser:"number"
			}]
		};		

		var resultsColumnDefs = [ {
			key : "partNO",
			parser:"number",
			label : "Part No",
			sortable : true
		}, {
			key : "villagesCovered",
			label : "Villages Covered",
			sortable : true
		}, {
			key : "totalVoters",
			parser:"number",
			label : "Total Voters",
			sortable : true
		}, {
			key : "acValidVotes",
			parser:"number",
			label : "(A)Polled Votes",
			sortable : true
		}, {
			key : "acVotesEarned",
			parser:"number",
			label : "AC* Votes",
			sortable : true
		}, {
			key : "pcVotesEarned",
			parser:"number",
			label : "PC* Votes",
			sortable : true
		}, {
			key : "acPercentage",
			parser:"number",
			label : "AC* %",
			sortable : true
		}, {
			key : "pcPercentage",
			label : "PC* %",
			parser:"number",
			sortable : true
		}, {
			key : "percentageDifference",
			parser:"number",
			label : "% Diff",
			sortable : true
		} ];
			
		var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 10,
				template : "{PageLinks} {RowsPerPageDropdown}",
                pageLinks : 5, 
                rowsPerPageOptions : [ 5, 10, 15, 20 ]
			    }) 
				};

    	var myDataTable = new YAHOO.widget.DataTable(childNewDivElmt.id,resultsColumnDefs, resultsDataSource,myConfigs);
        
		tree.draw(); 

      
	}
	
	function buildParliamemtSelect(jObj,results)
	{
		if(jObj.task=="getParliament")
		{				
			var pSelectElmt = document.getElementById("parliamentField");	
		}		
		else if(jObj.task=="Assembly")
		{			
			
			var pSelectElmt = document.getElementById("AssemblySelect");	
		}
		else if(jObj.task=="getParty")
		{			
			var pSelectElmt = document.getElementById("PartySelect");	
		}
		else
			return;
		
		removeSelectElements(pSelectElmt);

		var y=document.createElement('option');				
		y.text="Select";						
		try
		{
			pSelectElmt.add(y,null); // standards compliant
		}
		catch(ex)
		{
			pSelectElmt.add(y); // IE only
		}

		for(var i in results)
		{
			var childElmt = document.createElement('option');
			childElmt.value = results[i].id;
			childElmt.text = results[i].name;
		
			try
			{
				pSelectElmt.add(childElmt,null);	
			}
			catch (e)
			{
				pSelectElmt.add(childElmt);
			}				
		}		
	}
	
	function removeSelectElements(elmt)
	{
		if(!elmt)
			return;

		var len=elmt.length;			
		for(i=len-1;i>=0;i--)
		{
			elmt.remove(i);
		}	
	}
	function getCrossVoting()
	{
		var ajaxImgElmt = document.getElementById("ajaxLoadDiv");
		ajaxImgElmt.style.display = "block";

		var elecYearElmt = document.getElementById("electionYearField");
		var partyElmt = document.getElementById("PartySelect");
		var parliamentSelectElmt =  document.getElementById("parliamentField");
		var assemblySelectElmt =  document.getElementById("AssemblySelect");
		var allianceCheckElmt =  document.getElementById("allianceCheck");

		var elecValue =  elecYearElmt.options[elecYearElmt.selectedIndex].value;
		var partyValue =  partyElmt.options[partyElmt.selectedIndex].value;
		var parliamentValue =  parliamentSelectElmt.options[parliamentSelectElmt.selectedIndex].value;
		var assemblyValue =  assemblySelectElmt.options[assemblySelectElmt.selectedIndex].value;

		if(allianceCheckElmt.checked==true)
			var allianceValue = "true";
		else
			var allianceValue = "false";

		var jsObj={
						electionValue : elecValue,
						partyValue : partyValue,
						parliamentValue : parliamentValue,
						assemblyValue : assemblyValue,
						alliances:allianceValue,
						task:"crossVotingReport"
				  }
			
			var bparam="election="+jsObj.electionValue+"&party="+jsObj.partyValue+"&parliamentValue="+jsObj.parliamentValue+"&assemblyValue="+jsObj.assemblyValue+"&includeAliance="+jsObj.alliances;
			callAjax(jsObj,bparam);
	}
	function getParliament()
	{
		var img = document.getElementById("ajaxImg1");
		img.style.display = 'block';

		var elecYearElmt = document.getElementById("electionYearField");
		var elecValue =  elecYearElmt.options[elecYearElmt.selectedIndex].value;

		var jsObj={
						electionValue : elecValue,
						task:"getParliament"
				  }

		var bparam="election="+jsObj.electionValue;
		callAjax(jsObj,bparam);	

	}

	function forGetCrossVoting()
	{
		var parliamentFieldElmt = document.getElementById("parliamentField");
		var parliamentFieldValue =  parseInt(parliamentFieldElmt.options[parliamentFieldElmt.selectedIndex].value);
		
		
		var assemblyElmt = document.getElementById("AssemblySelect");
		var assemblyValue =  parseInt(assemblyElmt.options[assemblyElmt.selectedIndex].value);
					
		var partyElmt = document.getElementById("PartySelect");
		var partyValue =  parseInt(partyElmt.options[partyElmt.selectedIndex].value);

		if( !(isNaN(partyValue)) && (assemblyValue != -1) && !(isNaN(assemblyValue)) && (parliamentFieldValue != -1) && (!isNaN(parliamentFieldValue)) )
		{
			getCrossVoting();
		}
	}
			
			
</script>
</head>
<body>
 	<div id= "headDivStyle">
	<table border="0" cellpadding="0" cellspacing="0">          
		<tr><td><img src="images/icons/cadreReport/bg_left.png"/></td>
			<td><div id="headingImgStyle"><span>&nbsp;Cross Voting Report</span></div></td>
	       <td><img src="images/icons/cadreReport/bg_right.png"/></td>
	   </tr>
		</table>
	</div>
		<div id="crossVotingInputDiv"  style="border: 3px solid #194155;">
			<table class="crossVotingInputTable" border='0'>
				<tr>
					<td colspan="2"><h4 class="headStyle"><b><span id="labelspan">Select All Mandatory Fields :</span></b></h4></td>
				</tr>
				<tr>
					<td align="left" class="tdStyle"><s:label theme="simple" for="electionYearField" id="electionYearLabel" value="Election Year"></s:label><font color="red">*</font></td>
					<td align="left">
						<s:select cssClass="selectstyle" theme="simple" id="electionYearField" name="electionYearField" list="electionYearList" listKey="id" listValue="name" headerKey="-1" headerValue="Select Year" onchange="getParliament()"></s:select>
					</td>
					<td><img id="ajaxImg1" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/arrows.gif"/></td>
				
					<td align="left" class="tdStyle" style="padding-left:10px;"><s:label theme="simple" for="parliamentField" id="parliamentLabel" value="Parliament Constituency"></s:label><font color="red">*</font></td>
					<td align="left"> 
						<select class="selectstyle" id="parliamentField" onchange="getAssembly()">
							<option value="-1">Select</option>
						</select>
					</td>
					<td><img id="ajaxImg2" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/arrows.gif"/></td>
				</tr>			
				<tr>
					<td align="left" class="tdStyle">Assembly Constituency<font color="red">*</font></td>
					<td align="left">
						<select class="selectstyle" id="AssemblySelect" onchange="getParty()">
							<option value="-1">Select</option>
						</select>

						<input type="checkbox" name="includeAliance" id="allianceCheck" value="alliance" onclick="forGetCrossVoting()"/><span id="checkBoxStyle">Include Aliance Parties</span>
					</td>
					<td><img id="ajaxImg3" style="display:none;" height="15" width="15" src="<%=request.getContextPath()%>/images/icons/arrows.gif"/></td>
					
					<td align="left" class="tdStyle" style="padding-left:10px;">Party<font color="red">*</font></td>
					<td align="left">
							<select id="PartySelect" onchange="forGetCrossVoting()">
								<option value="-1" style="width:124px;">Select </option>			
							</select>						
					</td>
					
				</tr>
				<tr>
					<td colspan="4">
							<br><b>
							Note: <b><font color="red">*</font></b>Indicates mandatory field
					</b></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<div id="ajaxLoadDiv" style="display:none;padding-top:20px;">
							<span><b>Processing Request ...</b> </span>
							<img id="ajaxImg" height="13" width="100" src="<%=request.getContextPath()%>/images/icons/goldAjaxLoad.gif"/>
						</div>
					</td>					
				</tr>
				
			</table>
		</div>
		
		
		<div id="crossVotingResultDiv" style="display:none;"></div>
        
			
	
</body>
</html>