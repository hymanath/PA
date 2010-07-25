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
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	



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
	var elmt = document.getElementById("electionPageAjaxImgDiv");
	if(elmt)
		elmt.style.display = 'block';
	var percentage;
	var textBoxEl = document.getElementById("percentTxtBox");
	var percentValueChkboxEl = document.getElementById("percentValueChkbox");
	var radioElArr = document.getElementsByName("percentValue");
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
	var jsObj = {						
			percentage: percentage,			
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
function displayResults(results)
{
	var resultsDisplayDivEl = document.getElementById("resultsDisplayDiv");
	resultsDisplayDivEl.innerHTML = '';
	var resultsDisplayDivStr = '';
	/*resultsDisplayDivStr += '<div style="margin:10px;">';
	resultsDisplayDivStr += '<input type="button" class="button" value="Show All" onclick="showAll()"/>';
	resultsDisplayDivStr += '<input type="button" class="button" value="Collapse All" onclick="collapseAll()"/>';
	resultsDisplayDivStr += '</div">';*/
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
			resultsDisplayDivStr += '<LEGEND>'+results[i].name.toUpperCase()+'</LEGEND>';
			resultsDisplayDivStr += '				<div id="div_'+i+'_body">';
			resultsDisplayDivStr += '					<table class="tableClass">';
			resultsDisplayDivStr += '						<tr>';
			resultsDisplayDivStr += '							<th>Party</td>';
			resultsDisplayDivStr += '							<th>Predicted %</td>';	
			resultsDisplayDivStr += '						</tr>';
			for(var j in results[i].partiesResults)
			{
				resultsDisplayDivStr += '					<tr>';
							
				if(results[i].partiesResults[j].isPartyWon == true)
				{
					resultsDisplayDivStr += '						<td style="color:green" align="center"><b>'+results[i].partiesResults[j].partyName+'</b></td>';	
					resultsDisplayDivStr += '						<td style="color:green" align="center"><b>'+results[i].partiesResults[j].votesPercent+'</b></td>';
				} else 
				{
					resultsDisplayDivStr += '						<td style="color:red" align="center">'+results[i].partiesResults[j].partyName+'</td>';
					resultsDisplayDivStr += '						<td style="color:red" align="center">'+results[i].partiesResults[j].votesPercent+'</td>';
					
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
function showDetails(i)
{
	var divEl = document.getElementById("div_"+i+"_body");
	var imageLinkDivEl = document.getElementById("imageLinkDiv_"+i);
	
	
	if(divEl.style.display == 'none')
	{
		divEl.style.display = 'block';
		var imageLinkDivElStr = '';
		imageLinkDivElStr+='<A href="javascript:{}" onclick="hideDetails('+i+')" title="Click to Collapse"><img src="images/icons/minusImage.png" border="0"/></A>';
		imageLinkDivEl.innerHTML = '';
		imageLinkDivEl.innerHTML = imageLinkDivElStr;
		
	} 
	
	
}
function hideDetails(i)
{
	var divEl = document.getElementById("div_"+i+"_body");
	var imageLinkDivEl = document.getElementById("imageLinkDiv_"+i);
	

	if(divEl.style.display == 'block')
	{
		divEl.style.display = 'none';
		var imageLinkDivElStr = '';
		imageLinkDivElStr+='<A href="javascript:{}" onclick="showDetails('+i+')" title="Click to Expand"><img src="images/icons/plusImage.png" border="0"/></A>';
		imageLinkDivEl.innerHTML = '';
		imageLinkDivEl.innerHTML = imageLinkDivElStr;
		
	}
	
	
}


	
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
		</table>
		<input type="button" class="button" value="Show Results" onclick="getConstiResults()"/>
</div>
<center>
<DIV id="electionPageAjaxImgDiv" style="display:none;">
	<DIV> Loading!Please Wait..</DIV>
	<IMG src="images/icons/barloader.gif"/>
</DIV>
</center>
<div id="resultsDisplayDiv"></div>
</body>
</html>