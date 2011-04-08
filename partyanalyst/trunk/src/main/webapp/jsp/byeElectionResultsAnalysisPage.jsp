<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>2010 Bye-Elections Results</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/treeview/treeview-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/dragdrop/dragdrop-min.js"></SCRIPT> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script>
	<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/container/container-min.js"></SCRIPT> 	
	<SCRIPT type="text/javascript" src="js/yahoo/yui-js-2.8/build/button/button-min.js"></SCRIPT>



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
	<link rel="stylesheet" type="text/css" href="styles/biElectionPage/biElectionPage.css">

<style>
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
#inputSelectionCriteria  {
	color:#121922;
	font-size:14px;
	font-weight:bold;
}
.headStyle
{
	border: 1px solid ;
	margin: 10px;
	background-color:#EFEFEF;
	padding:5px;
	width:100%;
}
.tableStyle
{
	font-size:12px;
}
.button {
background:none repeat scroll 0 0 #335291;
color:#FFFFFF;
margin-bottom:5px;
margin-top:5px;
padding:5px;
width:150px;
}
fieldset {
border:4px solid #CFD6DF;
margin-bottom:10px;
}
legend {
background-color:#567AAF;
color:#FFFFFF;
font-size:12px;
padding:5px;
font-weight:bold;
}
.tableClass th {
	background:url("../../js/yahoo/yui-js-2.8/build/assets/skins/sam/sprite.png") repeat scroll 0 0 #D8D8DA;
	padding:5px;
}
.bodyStyle
{
	font-family:tahoma;
	font-size: 13px;
}
#electionPageAjaxImgDiv
{
	border:1px solid #ADADAD;
	font-size:12px;
	font-weight:bold;
	width:300px;
	margin-bottom:10px;
	text-align: center;
}
#votesShareDetailsTable th {
background:url("images/icons/tv9Icons/tableHeader.png") repeat scroll 0 0 transparent;
color:#FFFFFF;
font-size:14px;
padding:2px;
}

#votesShareDetailsTable td {
border:2px solid #212629;
color:#121922;
font-size:16px;
font-weight:bold;
padding:2px;
}
.detailsTableHeader  {
background-image:url("images/icons/indexPage/reportGroupHeader.png");
height:40px;
text-align:left;
}
.detailsTableHeaderSpan  {
color:#FFFFFF;
font-size:20px;
font-weight:bold;
position:relative;
top:5px;
margin-left:10px;
}

.yui-skin-sam .yui-panel .bd 
{
	padding:10px;
	overflow:auto;
}

</style>
<script type="text/javascript">
function showChkBox()
{
	var textBoxEl = document.getElementById("percentTxtBox");
	var radioElArr = document.getElementsByName("percentValue");
	if(textBoxEl.style.display == 'none')
	{
		textBoxEl.style.display = 'block';
		textBoxEl.focus();
	} else if(textBoxEl.style.display == 'block') 
	{	
		textBoxEl.style.display = 'none';
	}
	 
	for (i=0; i< radioElArr.length; i++)
	{
		if(radioElArr[i].checked == true)
			{
				radioElArr[i].checked = false;
			}
	}		
}
function getConstiResults()
{
	var resultsDisplayDivEl = document.getElementById("resultsDisplayDiv");
	resultsDisplayDivEl.innerHTML = '';
	var percentage = null;
	var textBoxEl = document.getElementById("percentTxtBox");
	var percentValueChkboxEl = document.getElementById("percentValueChkbox");
	var mandalElecChkboxEl = document.getElementById("mandalElecChkbox");
	var radioElArr = document.getElementsByName("percentValue");
	var localElections;
	var alertMessageEl = document.getElementById("alertMessage");
	alertMessageEl.innerHTML = '';
	if(percentValueChkboxEl.checked == true)
	{
		percentage = textBoxEl.value; 
	} else 
	{

		for (i=0; i< radioElArr.length; i++)
		{
			if(radioElArr[i].checked == true)
				{
				percentage = radioElArr[i].value; 
				}
		}	
	}
	if(mandalElecChkboxEl.checked == true)
	{
		localElections = "true";
	} else localElections = "false"; 
	if(percentage == null)
	{
		alertMessageEl.innerHTML = 'Please Give Percentage!'; 
		return;
	}
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt)
		elmt.style.display = 'block';
	var jsObj = {						
			percentage: percentage,
			localElections: localElections,			
			task:"getByeElectionResults"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "<%=request.getContextPath()%>/byeElectionResultsAnalysisAjaxAction.action?"+rparam;
	callAjax(jsObj, url);
	
		
}
function callAjax(jsObj,url)
{					
	var callback = {			
				   success : function( o ) {
						try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
								if(jsObj.task == "getByeElectionResults")
								{
									var elmt = document.getElementById("electionPageAjaxImgDiv");
									if(elmt)
										elmt.style.display = 'none';
									displayResults(myResults);
								} else if(jsObj.task == "votesSharingInConstituency")
								{
									buildResultsPopup(myResults);
								} else if("getUrbanRuralResults")
								{
									buildResultsForUrbanRural(jsObj, myResults);
								}
						}
						catch (e) {   
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
function displayResults(results)
{
	var resultsDisplayDivEl = document.getElementById("resultsDisplayDiv");
	resultsDisplayDivEl.innerHTML = '';
	var resultsDisplayDivStr = '';	
	resultsDisplayDivStr += '<TABLE>';
	var flag=0;
		for(var i in results)
		{
			if(i == 0)
			{
				resultsDisplayDivStr += '<tr>';
			}
			var inc = flag++;
			
			if(inc%4 == 0)
			{
				resultsDisplayDivStr += '</tr><tr>';
			}

			
			resultsDisplayDivStr += '<td>';
			resultsDisplayDivStr += '<FIELDSET>';
			//if(results[i].name != 'Nizamabad Urban' && results[i].name != 'Warangal West')
			//{
				resultsDisplayDivStr += '<LEGEND><A href="javascript:{}" style="color:white;" title="View Election Results in '+results[i].name+' constituency" onclick="partyVotesSharing('+results[i].id+',\''+results[i].name+'\')">'+results[i].name.toUpperCase()+'</A></LEGEND>';	
			//} else
				//resultsDisplayDivStr += '<LEGEND>'+results[i].name.toUpperCase()+'</LEGEND>';
			resultsDisplayDivStr += '				<div id="div_'+i+'_body">';
			resultsDisplayDivStr += '					<table class="tableClass">';
			resultsDisplayDivStr += '						<tr>';
			resultsDisplayDivStr += '							<th>Party</td>';
			resultsDisplayDivStr += '							<th>Predicted %</td>';	
			resultsDisplayDivStr += '						</tr>';
			for(var j in results[i].predictedPartiesResults)
			{
				resultsDisplayDivStr += '					<tr>';
							
				if(results[i].predictedPartiesResults[j].isPartyWon == true)
				{
					resultsDisplayDivStr += '						<td style="color:green" align="center"><b>'+results[i].predictedPartiesResults[j].partyName+'</b></td>';	
					resultsDisplayDivStr += '						<td style="color:green" align="center"><b>'+results[i].predictedPartiesResults[j].votesPercent+'</b></td>';
				} else 
				{
					resultsDisplayDivStr += '						<td style="color:red" align="center">'+results[i].predictedPartiesResults[j].partyName+'</td>';
					resultsDisplayDivStr += '						<td style="color:red" align="center">'+results[i].predictedPartiesResults[j].votesPercent+'</td>';
					
				}			
				resultsDisplayDivStr += '					</tr>';		
			}
			resultsDisplayDivStr += '					</table>';			
			resultsDisplayDivStr += '				</div>';
			
			resultsDisplayDivStr += '</FIELDSET>';
			resultsDisplayDivStr += '</td>';
		}
		resultsDisplayDivStr += '</tr>';
	resultsDisplayDivStr += '<TABLE>';
	resultsDisplayDivEl.innerHTML = resultsDisplayDivStr;
}

function partyVotesSharing(constiId, constiName){		
	
	var mandalElecChkboxEl = document.getElementById("mandalElecChkbox");
	
	var electypeSelectedElmts;
	var selectedElectionArray;
	if(mandalElecChkboxEl.checked == false)
	{	
		electypeSelectedElmts = new Array();
		electypeSelectedElmts.push("AC");
		electypeSelectedElmts.push("PC");
		selectedElectionArray = new Array();
		selectedElectionArray.push("Assembly");
		selectedElectionArray.push("Parliament");		
	} else if(mandalElecChkboxEl.checked == true)
	{
		electypeSelectedElmts = new Array();
		electypeSelectedElmts.push("AC");
		electypeSelectedElmts.push("PC");
		electypeSelectedElmts.push("MPTC");
		electypeSelectedElmts.push("ZPTC");
		selectedElectionArray = new Array();
		selectedElectionArray.push("Assembly");
		selectedElectionArray.push("Parliament");
		selectedElectionArray.push("CORPORATION");
		
	}
	if(constiName != 'Nizamabad Urban' && constiName != 'Warangal West')
	{	
		var jsObj = {
				getAll : "-",
				choices : electypeSelectedElmts,
				flag : "0",
				constituencyId: constiId ,
				constiName: constiName,
				task:"votesSharingInConstituency",
				choice:"All"
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "<%=request.getContextPath()%>/votesSharingInConstituencyAjaxAction.action?"+rparam;
	} else {
		var jsObj = {
				
				constituencyName: constiName,
				constituencyId: constiId,
				partiesArr: ["BJP","INC","PRP","TRS","TDP"],
				electionTypeArr: [],
				task: "getUrbanRuralResults",
				alliances: "false" ,
				isElectionType: "true",
				selectedElectionArr: selectedElectionArray,
				//allResults: allResults,
				chartName:" "
				};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);		
		var url = "<%=request.getContextPath()%>/getUrbanRuralResultsAjaxAction.action?"+rparam;
		}
		
			callAjax(jsObj, url);
}
function buildResultsPopup(results)
{
	var electionListLength = results[0].electionList.length+2;
	var str = '<div  style="overflow:auto;width:950px;">';	
	str += '<table id="votesShareDetailsTable" width="100%" cellspacing="2" cellmargin="0">';
	str += '<tr>';
	str += '<td colspan="'+electionListLength+'" style="padding:0px;border:0px">';
	str+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
	str+='			<tr>';
	str+='			<td width="2%" style="padding:0px;border:0px;"> <img src="images/icons/tv9Icons/first.png"></td>';
	str+='			<td width="98%" style="padding:0px;border:0px;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan"> Parties Votes Shares</span></div></td>';
	str+='			<td width="1%" style="padding:0px;border:0px;"><img src="images/icons/tv9Icons/second.png"></td>';
	str+='			</tr>';
	str+='		</table>';
	str += '</td>';
	str += '</tr>';

	str += '<tr>';
	str += '<th>Parties</th>';
	str += '<th>Range</th>';
	

	var length = results[0].electionList.length-1;
	for(var i=length;i>=0;i--)
		str += '<th>'+results[0].electionList[i].name+'</th>';

	
	str += '</tr>';
	
	for(var j in results)
	{
		if(results[j].range == " ")
			continue
		str += '<tr>';
		str += '<td align="center">'+results[0].partiesList[j].name+'</td>';
		str += '<td align="left" style="color:#FF8000;font-weight:bold;">'+results[j].range+'</td>';
		for(var k=results[j].electionWiseResults.length-1;k>=0;k--)
		{
			var info = results[j].electionWiseResults[k];
			
			if(info.percentage != "-1")
			{
				 //for Bye Election Results
				if(info.electionType == 'Parliament' && info.electionYear == '2008' 
					|| info.electionType == 'Parliament' && info.electionYear == '2006')
                   str += '<td align="center" name="'+info.electionType+'" style="background-color:#F6CECE;">'+info.percentage+'</td>';
				else if(info.alliancRes == true){
					str += '<td align="center" name="'+info.electionType+'" style="background-color:#DDEB9B;">'+info.percentage+'<font style="color:red;"> *</td>'; 
				} 
                else if(info.hasAlliance == "true")
					str += '<td align="center" name="'+info.electionType+'" style="background-color:#DDEB9B;">'+info.percentage+'</td>';
				else if(info.hasAlliance == "false")
					str += '<td align="center" name="'+info.electionType+'">'+info.percentage+'</td>';
			}
			else
				str += '<td name="'+info.electionType+'"><div style="visibility:hidden;">NA</div></td>';
		}
		
		str += '</tr>';
	}

	str += '</table></div>';
	
	 var myPanel = new YAHOO.widget.Dialog("resultsPanelDiv", {		
		    	 width : "750px",		    	    
                 fixedcenter : true, 
                 visible : true,  
                 constraintoviewport : true, 
        		 iframe :true,
        		 modal :true,
        		 hideaftersubmit:true,
        		 close:true
       });
	   myPanel.setHeader("Election Results");
       myPanel.setBody(str);
       myPanel.render();
	
}

function buildResultsForUrbanRural(jsObj,results)
{			
	var shareElmt = document.getElementById("votersShareData_main");
	var electionListLength = results.elections.length+2;
	
	var str = '';
	var str = '<div  style="overflow:auto;">';
	str += '<table id="votesShareDetailsTable" cellspacing="4" cellmargin="0">';
	str += '<tr>';
	str += '<td colspan="'+electionListLength+'" style="padding:0px;border:0px">';
	str+='		<table class="participatingPartiestable_inner" width="100%" cellspacing="0" cellpadding="0" border="0">';
	str+='			<tr>';
	str+='			<td width="2%" style="padding:0px;border:0px;"> <img src="images/icons/tv9Icons/first.png"></td>';
	str+='			<td width="98%" style="padding:0px;border:0px;"><div class="detailsTableHeader" style="width:100%;"><span class="detailsTableHeaderSpan"> Parties Votes Shares</span></div></td>';
	str+='			<td width="1%" style="padding:0px;border:0px;"><img src="images/icons/tv9Icons/second.png"></td>';
	str+='			</tr>';
	str+='		</table>';
	str += '</td>';
	str += '</tr>';

	str += '<tr>';
	str += '<th>Parties</th>';
	str += '<th>Range</th>';
	
	var length = results.elections.length-1;
	for(var i=length;i>=0;i--)
		str += '<th>'+results.elections[i].name+'</th>';
		str += '</tr>';
	
	
	for(var j in results.allPartiesAllElectionResults)									
	{
		str += '<tr>';
		if(results.allPartiesAllElectionResults[j].partyName != 'Others *')
		{		
			str += '<td align="center">'+results.allPartiesAllElectionResults[j].partyName+'</td>';
			str += '<td align="center" style="color:#FF8000;">'+results.allPartiesAllElectionResults[j].range+'</td>';
			var info =  results.allPartiesAllElectionResults[j].electionWiseResults;

			for(var k=info.length-1; k>=0; k--)						
			{
				if(info[k].percentage == "-1"){
					str += '<td><div style="visibility:hidden;">NA</div></td>';
				}else if(info[k].alliancRes == true){
					str += '<td align="center" style="background-color:#DDEB9B;">'+info[k].percentage+'<font style="color:red;"> *</td>';
				}else if(info[k].hasAlliance == 'true'){
					str += '<td align="center" style="background-color:#DDEB9B;">'+info[k].percentage+'</td>';
				}else{
					str += '<td align="center">'+info[k].percentage+'</td>';
				}
			}
	}	
		if(results.allPartiesAllElectionResults[j].partyName != 'Others *')
		{
			
			str += '</tr>';
		}		
	}
	str += '</table></div>';
	


	 var myPanel = new YAHOO.widget.Dialog("resultsPanelDiv", {		
		    		    	    
                fixedcenter : true, 
                visible : true,  
                constraintoviewport : true, 
       		 iframe :true,
       		 modal :true,
       		 hideaftersubmit:true,
       		 close:true
      });
	   myPanel.setHeader("Election Results");
      myPanel.setBody(str);
      myPanel.render();

	
		
}


window.history.forward(1);	
</script>
</head>
<body class="bodyStyle">
<div id="windowHeader" style="background-color:black;margin-top:0px;">
	<table width="100%">
		<tr>		
		    <td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"/></td>
			<td width="72%" align="center">
				<TABLE cellspacing="0" cellpadding="0" border="0" >
				<TR>
					<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo1.png" border="none" style="margin-top:15px;" /></TD>
					<TD valign="top"><DIV id="mainHead" class="mainHeading">2010 Bye-Election Results Analysis</DIV></TD>
					<TD valign="top"><IMG src="images/icons/electionResultsReport/elections_logo2.png" style="margin-top:15px;" border="none"/></TD>
				</TR>
				</TABLE>
			</td>
			<td width="14%" align="right"><img src="images/icons/homePage/pa_logo.jpg"/></td>
		</tr>
	</table>
	</div>
<div id="inputSelectionCriteria">
 
	<table>
		<tr>
			<td>
				Please Select Percentage: 		
				<input type="radio" name="percentValue" value="10" />10%
				<input type="radio" name="percentValue"  value="20" />20%
				<input type="radio" name="percentValue"  value="30"/>30%
				<input type="radio" name="percentValue"  value="50"/>50%
				<input type="radio" name="percentValue"  value="75"/>75%
				<input type="radio" name="percentValue"  value="100"/>100%
			</td>	
		</tr>	
		<tr>
			<td>
				<table>
					<tr>
						<td><input type="checkbox" id="percentValueChkbox" onclick="showChkBox()"/>Enter Percentage:</td>	
						<td><input type="text" id="percentTxtBox" style="display:none;"/></td>															
					</tr>
				</table>					
			</td>
		</tr>
		<tr>
			<td><input type="checkbox" id="mandalElecChkbox" />Include Local(ZPTC/MPTC or MUNICIPAL/CORPORATION) Elections</td>
		</tr>				
		</table>
		<div id="alertMessage" style="color:red;font-weight:bold;"></div>
		<input type="button" class="button" value="Show Results" onclick="getConstiResults()"/>
		
</div>
<center>
<DIV id="electionPageAjaxImgDiv" style="display:none;">
	<DIV> Loading!Please Wait..</DIV>
	<IMG src="images/icons/barloader.gif"/>
</DIV>
</center>
<div id="resultsDisplayDiv"></div>
<div class ="yui-skin-sam"><div id="resultsPanelDiv"></div></div>
</body>
</html>