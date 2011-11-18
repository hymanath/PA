<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst-Know | Analyze | Act</title>
<!--<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>-->


<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	 
	<!-- YUI Skin Sam -->
<!--<link  rel="stylesheet" type="text/css" href="styles/landingPage/landingPage.css"/> -->
<!--<script type="text/javascript" src="js/problemCompleteDetails.js"></script>-->
	
<!-- JQuery files (Start) -->

<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>
<script type="text/javascript" src="js/customPaginator/customPaginator.js"></script>
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.4.css" media="screen" />
<link rel="stylesheet" href="styles/style.css" />

<link rel="stylesheet" href="styles/jquery.treeview.css" />
<link rel="stylesheet" href="styles/screen.css" />
<script src="js/lib/jquery.js" type="text/javascript"></script>
<script src="js/lib/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.treeview.js" type="text/javascript"></script>

<!-- JQuery files (End) -->

<style>
a {
    color : blue;
    font-weight: bold;
    text-decoration: underline;
}
li.expandable
{
text-align : left;
}

.treeview li {
  text-align: left;
}
#statePageHeading {
    background-image: url("images/icons/electionResultsAnalysisReport/mid.png");
    background-repeat: repeat-x;
    color: #1C4B7A;
    font-size: 15px;
    font-weight: bold;
    height: 30px;
}
#stateNameSpan {
    padding-left: 30px;
    padding-right: 30px;
    position: relative;
    top: 7px;
}

</style>

<script type="text/javascript">

$(function() {
			$("#tree").treeview({
				collapsed: true,
				animated: "fast",
				control:"#sidetreecontrol",
				prerendered: true,
				persist: "location"
			});
		})
		
$(function() {
			$("#tree1").treeview({
				collapsed: true,
				animated: "fast",
				control:"#sidetreecontrol",
				prerendered: true,
				persist: "location"
			});
		})		
		
</script>
</head>
<body>
            <table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td><img border="none" src="images/icons/electionResultsAnalysisReport/first.png"></td>
						<td><div id="statePageHeading"><span id="stateNameSpan">Party Analyst Sitemap</span></div></td>
						<td><img border="none" src="images/icons/electionResultsAnalysisReport/second.png"></td>
					</tr>
				</table>
 
 
<div id="main">
<div id="sidetree">

<Table style="width:90%"><tr><td style="width:50%;" valign = "top"><div>

  <ul class="treeview" id="tree">
    <li class="last"><div class="hitarea expandable-hitarea"></div>Parliament and Assembly Election Results
		<ul style="display: none;">
		<li class="expandable"><div class="hitarea expandable-hitarea"></div>Parliament Election Results
		    <ul style="display: none;">
			   
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=17&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=2009">Parliament Election Results 2009 </a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=18&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=2004">Parliament Election Results 2004 </a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=19&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1999">Parliament Election Results 1999</a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=20&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1998">Parliament Election Results 1998</a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=51&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1996">Parliament Election Results 1996</a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=52&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1991">Parliament Election Results 1991</a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=74&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1989">Parliament Election Results 1989</a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=113&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1984">Parliament Election Results 1984</a></li>
				<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=117&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1980">Parliament Election Results 1980</a></li>
				<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=118&stateID=1&stateName=Andhra%20Pradesh&electionType=Parliament&electionTypeId=1&year=1977">Parliament Election Results 1977</a></li>

			
		  </ul>
		</li>
		<li class="last"><div class="hitarea expandable-hitarea"></div>Assembly Election Results
		     
			  <ul style="display: none;">
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=1">Andhra Pradesh </a>
				
				<ul style="display: none;">
				    
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=38&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=2009">Andhra Pradesh Assembly Election Results 2009</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=3&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=2004">Andhra Pradesh Assembly Election Results 2004</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=4&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=1999">Andhra Pradesh Assembly Election Results 1999</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=5&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=1994">Andhra Pradesh Assembly Election Results 1994</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=6&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=1989">Andhra Pradesh Assembly Election Results 1989</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=147&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=1985">Andhra Pradesh Assembly Election Results 1985</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=146&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=1983">Andhra Pradesh Assembly Election Results 1983</a></li>
					<li class="last"><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=145&stateID=1&stateName=Andhra%20Pradesh&electionType=Assembly&electionTypeId=2&year=1978">Andhra Pradesh Assembly Election Results 1978 </a></li>
                 </ul>
				</li>
				
				
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=3">Assam</a>
				
				<ul style="display: none;">
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=107&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=2011">Assam Assembly Election Results 2011</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=56&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=2006">Assam Assembly Election Results 2006</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=57&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=2001">Assam Assembly Election Results 2001</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=58&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=1996">Assam Assembly Election Results 1996</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=59&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=1991">Assam Assembly Election Results 1991</a></li>
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=68&stateID=3&stateName=Assam&electionType=Assembly&electionTypeId=2&year=1985">Assam Assembly Election Results 1985</a></li>
                  </ul>
				
				
				</li>
				
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=7">Gujarat</a>
				<ul style="display: none;">
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=126&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=2007">Gujarat Assembly Election Results 2007</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=127&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=2002">Gujarat Assembly Election Results 2002</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=128&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1998">Gujarat Assembly Election Results 1998</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=129&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1995">Gujarat Assembly Election Results 1995</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=130&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1990">Gujarat Assembly Election Results 1990</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=131&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1985">Gujarat Assembly Election Results 1985</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=132&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1980">Gujarat Assembly Election Results 1980</a></li>
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=133&stateID=7&stateName=Gujarat&electionType=Assembly&electionTypeId=2&year=1975">Gujarat Assembly Election Results 1975</a></li>
                  </ul>
				
				</li>
				
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=9">Himachal Pradesh</a>
				<ul style="display: none;">
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=148&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=2007">Himachal Pradesh Assembly Election Results 2007
	</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=149&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=2003">Himachal Pradesh Assembly Election Results 2003
	</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=150&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=1998">Himachal Pradesh Assembly Election Results 1998
	</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=151&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=1993">Himachal Pradesh Assembly Election Results 1993
	</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=152&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=1990">Himachal Pradesh Assembly Election Results 1990
	</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=153&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=1985">Himachal Pradesh Assembly Election Results 1985
	</a></li>
	                <li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=154&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=1982">Himachal Pradesh Assembly Election Results 1982
	</a></li>
	                
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=155&stateID=9&stateName=Himachal%20Pradesh&electionType=Assembly&electionTypeId=2&year=1977">Himachal Pradesh Assembly Election Results 1977</a></li>
                  </ul>
				
				</li>
				
				
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=12">Karnataka</a>
				<ul style="display: none;">
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=119&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=2008">Karnataka Assembly Election Results 2008</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=125&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=2004">Karnataka Assembly Election Results 2004 </a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=120&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1999">Karnataka Assembly Election Results 1999</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=121&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1994">Karnataka Assembly Election Results 1994</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=122&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1989">Karnataka Assembly Election Results 1989</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=123&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1985">Karnataka Assembly Election Results 1985</a></li>
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=124&stateID=12&stateName=Karnataka&electionType=Assembly&electionTypeId=2&year=1983">Karnataka Assembly Election Results 1983</a></li>
                  </ul>
				
				</li>
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=13">Kerala</a>
				<ul style="display: none;">
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=81&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=2011">Kerala Assembly Election Results 2011</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=21&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=2006">Kerala Assembly Election Results 2006</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=22&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=2001">Kerala Assembly Election Results 2001</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=23&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=1996">Kerala Assembly Election Results 1996</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=24&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=1991">Kerala Assembly Election Results 1991</a></li>
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=25&stateID=13&stateName=Kerala&electionType=Assembly&electionTypeId=2&year=1987">Kerala Assembly Election Results 1987</a></li>
                  </ul>
				
				</li>
				
				
				
				
			
			
			
				
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=35">Puducherry</a>
				  <ul style="display: none;">
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=106&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=2011">Puducherry Assembly Election Results 2011</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=33&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=2006">Puducherry Assembly Election Results 2006 </a><li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=73&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=2001">Puducherry Assembly Election Results 2001</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=46&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=1996">Puducherry Assembly Election Results 1996</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=47&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=1991">Puducherry Assembly Election Results 1991</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=48&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=1989">Puducherry Assembly Election Results 1989</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=49&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=1985">Puducherry Assembly Election Results 1985</a></li>
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=50&stateID=35&stateName=Puducherry&electionType=Assembly&electionTypeId=2&year=1980">Puducherry Assembly Election Results 1980</a></li>
                   </ul>
				
				</li>
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=21">Punjab</a>
				<ul style="display: none;">
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=136&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=2007">Punjab Assembly Election Results 2007</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=138&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=2002">Punjab Assembly Election Results 2002</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=139&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1997">Punjab Assembly Election Results 1997</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=140&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1992">Punjab Assembly Election Results 1992</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=141&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1985">Punjab Assembly Election Results 1985</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=142&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1980">Punjab Assembly Election Results 1980</a></li>
					<li class="last"><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=144&stateID=21&stateName=Punjab&electionType=Assembly&electionTypeId=2&year=1977">Punjab Assembly Election Results 1977</a></li>
                  </ul>
				
				</li>
				
			
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=24">Tamil Nadu</a>
				<ul style="display: none;">
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=111&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=2011">Tamil Nadu Assembly Election Results 2011</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=10&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=2006">Tamil Nadu Assembly Election Results 2006</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=9&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=2001">Tamil Nadu Assembly Election Results 2001</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=11&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=1996">Tamil Nadu Assembly Election Results 1996</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=12&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=1991">Tamil Nadu Assembly Election Results 1991</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=13&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=1989">Tamil Nadu Assembly Election Results 1989</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=14&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=1984">Tamil Nadu Assembly Election Results 1984</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=15&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=1980">Tamil Nadu Assembly Election Results 1980</a></li>
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=16&stateID=24&stateName=Tamil%20Nadu&electionType=Assembly&electionTypeId=2&year=1977">Tamil Nadu Assembly Election Results 1977</a></li>
                  </ul>
				
				</li>
				
				
				<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=26">Uttaranchal</a>
				<ul style="display: none;">
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=134&stateID=26&stateName=Uttaranchal&electionType=Assembly&electionTypeId=2&year=2007">Uttaranchal Assembly Election Results 2007</a></li>
					<li class="last"><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=135&stateID=26&stateName=Uttaranchal&electionType=Assembly&electionTypeId=2&year=2002">Uttaranchal Assembly Election Results 2002</a></li>
                  </ul>
				
				</li>
				<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=28">West Bengal</a>
				<ul style="display: none;">
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=112&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=2011">West Bengal Assembly Election Results 2011</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=31&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=2006">West Bengal Assembly Election Results 2006</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=32&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=2001">West Bengal Assembly Election Results 2001</a></li>
					<li><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=43&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=1996">West Bengal Assembly Election Results 1996</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=44&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=1991">West Bengal Assembly Election Results 1991</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=45&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=1987">West Bengal Assembly Election Results 1987</a></li>
					<li><a href="http://partyanalyst.com/electionDetailsReportAction.action?electionId=53&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=1982">West Bengal Assembly Election Results 1982</a></li>
					<li class="last"><a href="http://www.partyanalyst.com/electionDetailsReportAction.action?electionId=54&stateID=28&stateName=West%20Bengal&electionType=Assembly&electionTypeId=2&year=1977">West Bengal Assembly Election Results 1977</a></li>
                  </ul>
				
				</li>

			
		      </ul>
		
		</li>
	    </ul>
	</li>
	

</ul>

   </div></td>
	<td style="width:50%;" valign = "top"><div>
	  
  <ul class="treeview" id="tree1">
    <li class="last"><div class="hitarea expandable-hitarea"></div>Parliament and Assembly Constituencies
		<ul style="display: none;">
		<li class="expandable"><div class="hitarea expandable-hitarea"></div>Parliament Constituencies
		    <ul style="display: none;">
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=1">Andhra Pradesh Parliament Constituencies </a>
				
				<ul style="display: none;">
				    
					
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=461">Adilabad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=463">Amalapuram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=464">Anakapalli</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=465">Anantapur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=466">Araku</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=467">Bapatla</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=469">Bhongir</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=471">Chevella</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=472">Chittoor</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=473">Eluru</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=474">Guntur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=476">Hindupur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=477">Hyderabad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=478">Kadapa</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=479">Kakinada</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=480">Karimnagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=481">Khammam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=482">Kurnool</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=483">Machilipatnam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=484">Mahabubabad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=485">Mahbubnagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=486">Malkajgiri</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=487">Medak</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=489">Nagarkurnool</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=490">Nalgonda</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=491">Nandyal</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=493">Narasaraopet</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=494">Narsapuram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=495">Nellore</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=496">Nizamabad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=497">Ongole</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=499">Peddapalli</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=500">Rajahmundry</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=501">Rajampet</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=502">Secunderabad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=504">Srikakulam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=506">Tirupati</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=507">Vijayawada</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=508">Visakhapatnam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=509">Vizianagaram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=510">Warangal</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=511">Zahirabad</a></li>
			    </ul>
				</li>
		
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=3">Assam Parliament Constituencies </a>
			<ul style="display: none;">
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=514">Autonomous District</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=515">Barpeta</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=516">Dhubri</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=517">Dibrugarh</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=518">Gauhati</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=519">Jorhat</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=520">Kaliabor</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=521">Karimganj</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=522">Kokrajhar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=523">Lakhimpur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=524">Mangaldoi</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=525">Nowgong</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=526">Silchar</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=527">Tezpur</a></li>
  
			</ul>
			</li>
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=7">Gujarat Parliament Constituencies </a>
			<ul style="display: none;">
			
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=601">Ahmedabad East</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=602">Ahmedabad West</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=603">Amreli</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=604">Anand</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=605">Banaskantha</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=606">Bardoli</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=608">Bharuch</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=609">Bhavnagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=612">Chhota Udaipur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=613">Dahod</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=616">Gandhinagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=618">Jamnagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=619">Junagadh</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=620">Kachchh</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=623">Kheda</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=625">Mahesana</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=627">Navsari</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=628">Panchmahal</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=629">Patan</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=630">Porbandar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=631">Rajkot</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=632">Sabarkantha</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=633">Surat</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=634">Surendranagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=635">Vadodara</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=636">Valsad</a></li>
 
			</ul>
			</li>
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=9">Himachal Pradesh Parliament Constituencies </a>
			<ul style="display: none;">
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=650">Hamipur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=652">Kangra</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=653">Mandi</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=654">Shimla</a></li>
 
			</ul>
			</li>
			
			 
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=12">Karnataka Parliament Constituencies </a>
			<ul style="display: none;">
			
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=676">Bagalkot</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=677">Bangalore Central</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=678">Bangalore North</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=679">Bangalore Rural</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=680">Bangalore South</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=681">Belgaum</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=682">Bellary</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=683">Bidar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=684">Bijapur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=685">Chamarajanagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=686">Chikballapur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=687">Chikkodi</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=689">Chitradurga</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=690">Dakshina Kannada</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=691">Davanagere</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=692">Dharwad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=695">Gulbarga</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=696">Hassan</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=697">Haveri</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=700">Kolar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=701">Koppal</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=702">Mandya</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=704">Mysore</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=705">Raichur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=706">Shimoga</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=707">Tumkur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=709">Udupi Chikmagalur</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=710">Uttara Kannada</a></li>
 
			</ul>
			</li>
			
			
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=13">Kerala Parliament Constituencies </a>
			<ul style="display: none;">
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=712">Alappuzha</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=713">Alathur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=715">Attingal</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=719">Chalakudy</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=721">Ernakulam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=722">Idukki</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=723">Kannur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=724">Kasaragod</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=725">Kollam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=726">Kottayam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=727">Kozhikode</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=728">Malappuram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=730">Mavelikara</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=734">Palakkad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=736">Pathanamthitta</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=737">Ponnani</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=739">Thiruvananthapuram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=740">Thrissur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=743">Vadakara</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=744">Wayanad</a></li>
 
			</ul>
			</li>
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=35">Puducherry Parliament Constituencies </a>
			<ul style="display: none;">
			
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=882">Puducherry</a></li>

			</ul>
			</li>
			
			  		
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=21">Punjab Parliament Constituencies </a>
			<ul style="display: none;">
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=883">Amritsar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=884">Anandpur Sahib</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=885">Bathinda</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=886">Bhatinda</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=887">Faridkot</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=888">Fatehgarh Sahib</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=889">Firozpur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=890">Gurdaspur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=891">Hoshiarpur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=892">Jalandhar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=894">Khadoor Sahib</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=895">Ludhiana</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=896">Patiala</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=899">Sangrur</a></li>
 
			</ul>
			</li>
			
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=24">Tamil Nadu Parliament Constituencies </a>
			<ul style="display: none;">
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=931">Arakkonam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=932">Arani</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=934">Chennai Central</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=935">Chennai North</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=936">Chennai South</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=937">Chidambaram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=938">Coimbatore</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=939">Cuddalore</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=940">Dharmapuri</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=941">Dindigul</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=942">Erode</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=944">Kallakurichi</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=945">Kancheepuram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=946">Kanniyakumari</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=947">Karur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=948">Krishnagiri</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=952">Madurai</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=953">Mayiladuthurai</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=954">Nagapattinam</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=956">Namakkal</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=957">Nilgiris</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=958">Palani</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=959">Perambalur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=961">Pollachi</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=963">Ramanathapuram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=965">Salem</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=966">Sivaganga</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=968">Sriperumbudur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=969">Tenkasi</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=970">Thanjavur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=971">Theni</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=972">Thoothukkudi</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=976">Tiruchirappalli</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=977">Tirunelveli</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=979">Tiruppur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=980">Tiruvallur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=981">Tiruvannamalai</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=983">Vellore</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=984">Viluppuram</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=985">Virudhunagar</a></li>
 
			</ul>
			</li>
			
			 
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=26">Uttaranchal Parliament Constituencies </a>
			<ul style="display: none;">
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1078">Almora</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1079">Garhwal</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1080">Hardwar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1082">Nainital Udhamsingh Nagar</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1083">Tehri Garhwal</a></li>
 
			</ul>
			</li>
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=28">West Bengal Parliament Constituencies </a>
			<ul style="display: none;">
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1084">Alipurduars</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1085">Arambagh</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1086">Asansol</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1087">Baharampur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1088">Balurghat</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1089">Bangaon</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1090">Bankura</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1091">Barasat</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1092">Bardhaman Purba</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1093">Bardhaman Durgapur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1094">Barrackpur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1095">Basirhat</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1096">Berhampore</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1097">Birbhum</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1098">Bishnupur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1099">Bolpur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1105">Cooch Behar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1106">Darjeeling</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1107">Diamond Harbour</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1108">Dum Dum</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1110">Ghatal</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1111">Hooghly</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1112">Howrah</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1113">Jadavpur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1114">Jalpaiguri</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1115">Jangipur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1116">Jhargram</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1117">Joynagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1118">Kunthi</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1120">Kolkata Dakshin</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1121">Kolkata Uttar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1122">Krishnanagar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1124">Maldaha Dakshin</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1125">Maldaha Uttar</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1126">Mathurapur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1127">Medinipur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1129">Murshidabad</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1132">Purulia</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1133">Raiganj</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1134">Ranaghat</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1136">Sreerampur</a></li>
							<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1137">Tamluk</a></li>
							<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1138">Uluberia</a></li>
  
			</ul>
			</li>
			
			
			
			
			 
		</ul>
		</li>
		
		<li class="last"><div class="hitarea expandable-hitarea"></div>Assembly Constituencies
		    <ul style="display: none;">	

			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=1">Andhra Pradesh Assembly Constituencies </a>
			<ul style="display: none;">
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=1&districtName=Adilabad">Adilabad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1">ADILABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=242">BADVEL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2">ASIFABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=295">BELLAMPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=3">BOATH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=4">CHENNUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=5">KHANAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=296">MANCHERIAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=6">MUDHOLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=7">NIRMAL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=8">SIRPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=22&districtName=Anantapur">Anantapur District </a>
			<ul style="display: none;">
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=298">ANANTAPUR URBAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=267">DHARMAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=297">GUNTAKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=270">HINDUPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=271">KADIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=272">KALYANDURG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=273">MADAKASIRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=275">PENUKONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=300">PUTTAPARTHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=299">RAPTADU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=276">RAYADURG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=277">SINGANAMALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=278">TADPATRI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=279">URAVAKONDA</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=23&districtName=Chittoor">Chittoor District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=280">CHANDRAGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=281">CHITTOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=301">GANGADHARA NELLORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=282">KUPPAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=294">MADANPALLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=283">NAGARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=284">Palamaner</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=285">PILERU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=286">PUNGANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=302">PUTHALAPATTU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=288">SATYAVEDU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=289">SRIKALAHASTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=290">THAMBALLAPALLE</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=291">TIRUPATI</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=20&districtName=Cuddapah">Cuddapah District </a>
			<ul style="display: none;">
 
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=244">JAMMALAMADUGU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=243">Kadapa</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=245">KAMALAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=246">KODUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=249">MYDUKUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=250">PRODDATUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=251">Pulivendla</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=252">RAJAMPET</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=248">RAYACHOTY</a></li>

			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=14&districtName=East Godavari">East Godavari District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=146">Amalapuram</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=147">ANAPARTHY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=310">GANNAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=149">JAGGAMPETA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=308">KAKINADA CITY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=307">KAKINADA RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=152">KOTHAPETA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=309">MANDAPETA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=153">MUMMIDIVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=155">PEDDAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=156">PITHAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=157">PRATHIPAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=304">RAJAHMUNDRY CITY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=305">RAJAHMUNDRY RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=303">RAJANAGARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=159">RAMACHANDRAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=306">RAMPACHODAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=160">RAZOLE</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=163">TUNI</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=17&districtName=Guntur">Guntur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=209">BAPATLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=199">CHILAKALURIPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=311">GUNTUR EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=312">GUNTUR WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=203">GURZALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=205">MACHERLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=206">MANGALAGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=208">NARASARAOPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=210">PEDDAKURAPADU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=211">PONNUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=212">PRATHIPADU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=213">REPALLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=214">SATTENAPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=215">TADIKONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=216">TENALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=217">VEMURU</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=207">VINUKONDA</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=5&districtName=Hyderabad">Hyderabad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=315">AMBERPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=313">BAHADURPURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=43">CHANDRAYANGUTTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=44">CHARMINAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=317">GOSHAMAHAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=314">JUBILEE HILLS</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=46">KARWAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=47">KHAIRATABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=49">MALAKPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=50">MUSHEERABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=316">NAMPALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=51">SANATHNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=52">SECUNDERABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=53">SECUNDERABAD CANTONMENT</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=54">YAKUTPURA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=3&districtName=Karimnagar">Karimnagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=20">CHOPPADANDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=322">DHARMAPURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=320">HUSNABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=21">HUZURABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=23">JAGTIAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=24">KARIMNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=321">KORATLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=319">MANAKONDUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=26">MANTHANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=30">PEDDAPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=318">RAMAGUNDAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31">SIRCILLA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=323">VEMULAWADA</a></li>
  
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=10&districtName=Khammam">Khammam District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=326">ASWARAOPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=100">BHADRACHALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=101">KHAMMAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=102">KOTHAGUDEM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=103">MADHIRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=104">PALAIR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=324">PINAPAKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=105">SATHUPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=325">WYRA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=107">YELLANDU</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=16&districtName=Krishna">Krishna District </a>
			<ul style="display: none;">
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=182">AVANIGADDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=184">GANNAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=185">GUDIVADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=186">JAGGAYYAPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=187">KAIKALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=328">MACHILIPATNAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=191">MYLAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=192">NANDIGAMA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=193">NUZVID</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=329">PAMARRU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=327">PEDANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=330">PENAMALURU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=194">TIRUVURU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=331">VIJAYAWADA CENTRAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=195">VIJAYAWADA EAST</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=196">VIJAYAWADA WEST</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=21&districtName=Kurnool">Kurnool District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=253">ADONI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=254">ALLAGADDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=255">ALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=333">BANAGANAPALLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=257">DHONE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=258">KODUMUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=260">KURNOOL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=334">MANTRALAYAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=261">NANDIKOTKUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=262">NANDYAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=263">PANYAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=264">PATTIKONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=332">SRISAILAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=265">YEMMIGANUR</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=7&districtName=Mahbubnagar">Mahbubnagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=61">ACHAMPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=62">ALAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=369">DEVARKADRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=63">GADWAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=64">JADCHERLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=65">KALWAKURTHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=66">KODANGAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=67">KOLLAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=68">MAHBUBNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=69">MAKTHAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=70">NAGARKURNOOL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=335">NARAYANPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=71">SHADNAGAR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=73">WANAPARTHY</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=4&districtName=Medak">Medak District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32">ANDOLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=336">DUBBAK</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34">GAJWEL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=35">MEDAK</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=36">NARAYANKHED</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=37">NARSAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=337">PATANCHERU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=39">SANGAREDDY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=40">SIDDIPET</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=41">ZAHIRABAD</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=8&districtName=Nalgonda">Nalgonda District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=74">ALAIR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=75">BHONGIR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=77">DEVERKONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=339">HUZURNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=78">KODAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=79">MIRYALGUDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=80">MUNGODE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=338">NAGARJUNA SAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=81">NAKREKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=82">NALGONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=84">SURYAPET</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=85">TUNGATURTHI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=19&districtName=Nellore">Nellore District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=241">Atmakur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=231">GUDUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=232">KAVALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=233">KOVUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=340">NELLORE CITY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=341">NELLORE RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=236">SARVEPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=237">SULLURPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=238">UDAYAGIRI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=239">VENKATAGIRI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=2&districtName=Nizamabad">Nizamabad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=10">ARMOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=11">BALKONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=12">BANSWADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=13">BODHAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=15">JUKKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=16">KAMAREDDY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=343">NIZAMABAD RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=342">NIZAMABAD URBAN</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=18">YELLAREDDY</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=18&districtName=Prakasam">Prakasam District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=218">ADDANKI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=219">CHIRALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=221">DARSI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=222">GIDDALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=223">KANDUKUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=224">KANIGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=225">KONDEPI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=226">MARKAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=227">ONGOLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=228">PARCHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=229">SANTHANUTHALAPADU</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=344">YERRAGONDAPALEM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=6&districtName=Rangareddi">Rangareddi District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=55">CHEVELLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=56">IBRAHIMPATNAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=346">KUKATPALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=348">LAL BAHADUR NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=349">MAHESWARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=367">MALKAJGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=57">MEDCHAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=58">PARGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=345">QUTHBULLAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=351">RAJENDRANAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=350">SERLINGAMPALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=59">TANDUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=347">UPPAL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=60">VICARABAD</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=11&districtName=Srikakulam">Srikakulam District </a>
			<ul style="display: none;">
 
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=108">AMADALAVALASA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=109">ETCHERLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=111">ICHAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=112">NARASANNAPETA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=113">PALAKONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=352">PALASA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=114">PATHAPATNAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=353">RAJAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=116">SRIKAKULAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=117">TEKKALI</a></li>

			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=13&districtName=Visakhapatnam">Visakhapatnam District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=133">ANAKAPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=359">ARAKU VALLEY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=368">BHIMILI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=134">CHODAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=135">ELAMANCHILI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=358">GAJUWAKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=136">MADUGULA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=137">NARSIPATNAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=138">PADERU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=140">PAYAKARAOPETA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=141">PENDURTHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=354">VISAKHAPATNAM EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=356">VISAKHAPATNAM NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=355">VISAKHAPATNAM SOUTH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=357">VISAKHAPATNAM WEST</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=12&districtName=Vizianagaram">Vizianagaram District </a>
			<ul style="display: none;">
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=122">BOBBILI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=120">CHEEPURUPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=121">GAJAPATHINAGARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=360">KURUPAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=361">NELLIMARLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=124">PARVATHIPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=125">SALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=127">SRUNGAVARAPUKOTA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=129">VIZIANAGARAM</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=9&districtName=Warangal">Warangal District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=365">BHUPALPALLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=86">DORNAKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=87">GHANPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=89">JANGAON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=91">MAHBUBABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=92">MULUG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=93">NARSAMPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=362">PALAKURTHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=94">PARKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=364">WARANGAL EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=363">WARANGAL WEST</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=97">WARDHANNAPET</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=15&districtName=West Godavari">West Godavari District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=181">ACHANTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=167">BHIMAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=168">CHINTALAPUDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=169">DENDULUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=170">ELURU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=171">GOPALPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=172">KOVVUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=173">NARASAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=366">NIDADAVOLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=174">PALACOLE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=176">POLAVARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=177">TADEPALLIGUDEM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=178">TANUKU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=179">UNDI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=180">UNGUTUR</a></li>


			</ul>
			</li>
			
			</ul>
			</li>
			
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=3">Assam Assembly Constituencies </a>
			<ul style="display: none;">
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=92&districtName=BAKSA">Baksa District </a>
			<ul style="display: none;">

			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31668">CHAPAGURI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=73&districtName=Barpeta">Barpeta District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31674">BAGHBAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31672">BARPETA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31670">BHABANIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31676">CHENGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31673">JANIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31671">PATACHARKUCHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31675">SARUKHETRI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31669">SORBHOG</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=72&districtName=Bongaigaon">Bongaigaon District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31679">ABHAYAPURI NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31680">ABHAYAPURI SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31678">BIJNI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31677">BONGAIGAON</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=89&districtName=Cachar">Cachar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31686">BARKHOLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31683">DHOLAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31687">KATIGORA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31685">LAKHIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31681">SILCHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31682">SONAI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31684">UDHARBOND</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=93&districtName=CHIRANG">CHIRANG District </a>
			<ul style="display: none;">

			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31688">SIDLI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=76&districtName=Darrang">Darrang District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31692">DALGAON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31689">KALAIGAON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31691">MANGALDOI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31690">SIPAJHAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=81&districtName=Dhemaji">Dhemaji District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31693">DHEMAJI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31694">JONAI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=70&districtName=Dhubri">Dhubri District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31701">BILASIPARA EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31700">BILASIPARA WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31697">DHUBRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31698">GAURIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31699">GOLAKGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31695">MANKACHAR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31696">SALMARA SOUTH</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=83&districtName=Dibrugarh">Dibrugarh District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31708">CHABUA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31703">DIBRUGARH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31705">DULIAJAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31704">LAHOWAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31702">MORAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31707">NAHARKATIA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31706">TINGKHONG</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=71&districtName=Goalpara">Goalpara District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31710">DUDHNAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31711">GOALPARA EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31712">GOALPARA WEST</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31713">JALESWAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=86&districtName=Golaghat">Golaghat District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31714">BOKAKHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31716">GOLAGHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31717">KHUMTAI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31715">SARUPATHAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=91&districtName=Hailakandi">Hailakandi District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31720">ALAGAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31718">HAILAKANDI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31719">KATLICHARA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=85&districtName=Jorhat">Jorhat District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31721">DERGAON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31722">JORHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31723">MAJULI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31725">MARIANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31726">TEOK</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31724">TITABAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=74&districtName=Kamrup">Kamrup District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31727">BOKO</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31728">CHAYGAON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31730">HAJO</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31731">KAMALPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31729">PALASBARI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31732">RANGIYA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=94&districtName=KAMRUP (METRO)">KAMRUP (METRO) District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31734">DISPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31735">GAUHATI EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31736">GAUHATI WEST</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31733">JALUKBARI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=87&districtName=Karbi Anglong">Karbi Anglong District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31740">BAITHALANGSO</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31737">BOKAJAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31739">DIPHU</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31738">HOWRAGHAT</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=90&districtName=Karimganj">Karimganj District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31745">BADARPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31743">KARIMGANJ NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31744">KARIMGANJ SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31742">PATHARKANDI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31741">RATABARI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=69&districtName=Kokrajhar">Kokrajhar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31746">GOSSAIGAON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31748">KOKRAJHAR (EAST)</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31747">KOKRAJHAR (WEST)</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=80&districtName=Lakhimpur">Lakhimpur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31749">BIHPURIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31752">DHAKUAKHANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31751">LAKHIMPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31750">NAOBOISA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=77&districtName=Marigaon">Marigaon District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31753">JAGIROAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31755">LAHARIGHAT</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31754">MARIGAON</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=78&districtName=Nagaon">Nagaon District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31761">BARHAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31758">BATADROBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31757">DHING</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31765">HOJAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31764">JAMUNAMUKH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31763">KALIABOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31766">LUMDING</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31760">NOWGONG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31756">RAHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31759">RUPOHIHAT</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31762">SAMAGURI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=75&districtName=Nalbari">Nalbari District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31771">BARAMA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31769">BARKHETRY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31770">DHARMAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31768">NALBARI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31767">TAMULPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=88&districtName=North Cachar Hills">North Cachar Hills District </a>
			<ul style="display: none;">

			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31709">HAFLONG</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=84&districtName=Sibsagar">Sibsagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31772">AMGURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31774">MAHMARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31773">NAZIRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31777">SIBSAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31775">SONARI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31776">THOWRA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=79&districtName=Sonitpur">Sonitpur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31779">BARCHALLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31784">BEHALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31783">BISWANATH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31778">DHEKIAJULI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31785">GOHPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31781">RANGAPARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31782">SOOTEA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31780">TEZPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=82&districtName=Tinsukia">Tinsukia District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31787">DIGBOI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31789">DOOM DOOMA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31788">MARGHERITA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31790">SADIYA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31786">TINSUKIA</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=95&districtName=UDALGURI">UDALGURI District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31793">MAJBAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31791">PANERY</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31792">UDALGURI</a></li>


			</ul>
			</li>

			
			
			
			</ul>
			</li>
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=7">Gujarat Assembly Constituencies </a>
			<ul style="display: none;">
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=152&districtName=Ahmadabad">Ahmadabad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34848">ASARWA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34837">BAVLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34845">DARIAPUR KAZIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34841">DASKROI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34835">DHANDHUKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34836">DHOLKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34844">ELLIS BRIDGE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34852">JAMALPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34847">KALUPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34851">KHADIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34838">MANDAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34853">MANINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34854">NARODA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34849">RAKHIAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34843">SABARMATI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34840">SARKHEJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34850">SHAHER KOTDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34846">SHAHPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34839">VIRAMGAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=158&districtName=Amreli">Amreli District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34821">AMRELI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34819">BABRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34822">DHARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34829">KUNDLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34820">LATHI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34824">RAJULA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=160&districtName=Anand">Anand District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34909">ANAND</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34915">BHADRAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34914">BORSAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34916">CAMBAY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34911">PETLAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34910">SARSA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34912">SOJITRA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34903">UMRETH</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=147&districtName=Banas Kantha">Banas Kantha District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34878">DANTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34874">DEESA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34872">DEODAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34875">DHANERA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34873">KANKREJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34876">PALANPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34877">VADGAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34871">VAV</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=166&districtName=Bharuch">Bharuch District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34933">ANKLESHWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34932">BROACH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34930">JAMBUSAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34934">JHAGADIA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34931">VAGRA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=159&districtName=Bhavnagar">Bhavnagar District </a>
			<ul style="display: none;">
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34833">BHAVNAGAR NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34834">BHAVNAGAR SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34825">BOTAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34826">GADHADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34832">GHOGHO</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34830">MAHUVA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34827">PALITANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34828">SIHOR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34831">TALAJA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=163&districtName=Dohad">Dohad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34892">DEVGADH BARIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34890">DOHAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34888">JHALOD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34889">LIMDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34891">LIMKHEDA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34899">RANDHIKPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=151&districtName=Gandhinagar">Gandhinagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34842">DEHGAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34855">GANDHINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34856">KALOL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34860">MANSA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=155&districtName=Jamnagar">Jamnagar District </a>
			<ul style="display: none;">
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34805">BHANVAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34807">DWARKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34804">JAMJODHPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34801">JAMNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34802">JAMNAGAR RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34800">JODIYA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34803">KALAWAD</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34806">KHAMBHALIA</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=157&districtName=Junagadh">Junagadh District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34818">JUNAGADH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34812">KESHOD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34823">KODINAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34817">MALIYA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34811">MANAVADAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34810">MANGROL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34814">SOMNATH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34813">TALALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34815">UNA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34816">VISAVADAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=146&districtName=Kachchh">Kachchh District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34777">ABDASA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34781">ANJAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34779">BHUJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34778">MANDVI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34780">MUNDRA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34782">RAPAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=161&districtName=Kheda">Kheda District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34900">BALASINOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34908">CHAKALASI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34901">KAPADVANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34904">KATHLAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34906">MAHUDHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34913">MATAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34905">MEHMEDABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34907">NADIAD</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34902">THASRA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=149&districtName=Mahesana">Mahesana District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34858">JOTANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34857">KADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34863">KHERALU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34859">MEHSANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34864">UNJHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34861">VIJAPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34862">VISNAGAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=165&districtName=Narmada">Narmada District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34935">DEDIAPADA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34936">RAJPIPLA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=169&districtName=Navsari">Navsari District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34952">CHIKHLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34951">GANDEVI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34949">JALALPORE</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34950">NAVSARI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=162&districtName=Panch Mahals">Panch Mahals District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34896">GODHRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34894">HALOL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34895">KALOL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34898">LUNAVADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34893">RAJGADH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34887">SANTRAMPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34897">SHEHRA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=148&districtName=Patan">Patan District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34868">CHANASMA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34867">PATAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34870">RADHANPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34869">SAMI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34865">SIDHPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34866">VAGDOD</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=156&districtName=Porbandar">Porbandar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34809">KUTIYANA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34808">PORBANDAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=154&districtName=Rajkot">Rajkot District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34798">DHORAJI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34796">GONDAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34792">JASDAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34797">JETPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34789">MORVI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34793">RAJKOT I</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34794">RAJKOT II</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34795">RAJKOT RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34790">TANKARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34799">UPLETA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34791">WANKANER</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=150&districtName=Sabar Kantha">Sabar Kantha District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34885">BAYAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34881">BHILODA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34882">HIMATNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34880">IDAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34879">KHEDBRAHMA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34886">MEGHRAJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34884">MODASA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34883">PRANTIJ</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=167&districtName=Surat">Surat District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34942">BARDOLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34948">CHORASI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34943">KAMREJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34941">MAHUVA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34938">MANGROL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34937">NIJHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34944">OLPAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34939">SONGADH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34946">SURAT CITY EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34945">SURAT CITY NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34947">SURAT CITY WEST</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34940">VYARA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=153&districtName=Surendranagar">Surendranagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34786">CHOTILA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34783">DASADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34788">DHRANGADHRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34787">HALVAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34785">LIMBDI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34784">WADHWAN</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=168&districtName=The Dangs">The Dangs District </a>
			<ul style="display: none;">

			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34953">DANGS BANSDA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=164&districtName=Vadodara">Vadodara District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34923">BARODA CITY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34927">BARODA RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34917">CHHOTA UDAIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34921">DABHOI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34918">JETPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34929">KARJAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34919">NASVADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34928">PADRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34925">RAOPURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34920">SANKHEDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34922">SAVLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34924">SAYAJIGANJ</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34926">VAGHODIA</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=170&districtName=Valsad">Valsad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34954">BULSAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34955">DHARAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34956">MOTA PONDHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34957">PARDI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=34958">UMBERGAON</a></li>


			</ul>
			</li>	
			</ul>
			</li>
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=9">Himachal Pradesh Assembly Constituencies </a>
			<ul style="display: none;">
			
			

			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=278&districtName=Bilaspur">Bilaspur District </a>
			<ul style="display: none;">
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38880">BILASPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38882">GEHARWIN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38881">GHUMARWIN</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38879">KOTKEHLOOR</a></li>
 
			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=271&districtName=Chamba">Chamba District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38910">BANIKHET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38913">BHARMOUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38909">BHATTIYAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38912">CHAMBA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38911">RAJNAGAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=276&districtName=Hamirpur">Hamirpur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38885">BAMSAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38884">HAMIRPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38886">MEWA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38883">NADAUN</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38887">NADAUNTA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=272&districtName=Kangra">Kangra District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38902">BAIJNATH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38907">DHARAMSALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38894">GANGATH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38896">GULER</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38897">JASWAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38899">JAWALAMUKHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38895">JAWALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38908">KANGRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38905">NAGROTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38893">NURPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38903">PALAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38898">PRAGPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38901">RAJGIR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38906">SHAHPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38904">SULAH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38900">THURAL</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=282&districtName=Kinnaur">Kinnaur District </a>
			<ul style="display: none;">

			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38860">KINNAUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=274&districtName=Kullu">Kullu District </a>
			<ul style="display: none;">
 
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38917">ANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38916">BANJAR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38915">KULU</a></li>

			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=273&districtName=Lahul & Spiti">Lahul & Spiti District </a>
			<ul style="display: none;">

			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38914">LAHAUL AND SPITI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=275&districtName=Mandi">Mandi District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38922">BALH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38919">CHACHIOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38926">DARANG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38924">DHARAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38923">GOPALPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38925">JOGINDER NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38918">KARSOG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38927">MANDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38920">NACHAN</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38921">SUNDERNAGAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=281&districtName=Shimla">Shimla District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38864">CHOPAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38863">JUBBAL KOTKHAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38868">KASUMPTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38865">KUMARSAIN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38861">RAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38862">ROHRU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38867">SIMLA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38866">THEOG</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=280&districtName=Sirmaur">Sirmaur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38878">NAHAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38874">PACHHAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38877">PAONTA DOON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38875">RAINKA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38876">SHILLAI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=279&districtName=Solan">Solan District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38869">ARKI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38870">DOON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38872">KASAULI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38871">NALAGARH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38873">SOLAN</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=277&districtName=Una">Una District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38889">CHINTPURNI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38888">GAGRET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38892">KUTLEHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38890">SANTOKGARH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38891">UNA</a></li>


			</ul>
			</li>
			</ul>
			</li>
			
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=12">Karnataka Assembly Constituencies </a>
			<ul style="display: none;">
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=120&districtName=Bagalkot">Bagalkot District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32696">BADAMI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32697">BAGALKOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32695">BILGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32698">HUNGUND</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32694">JAMKHANDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32692">MUDHOL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32693">TERDAL</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=139&districtName=Bangalore Rural">Bangalore Rural District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32858">CHANNAPATNA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32852">DEVANAHALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32853">DODDABALLAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32851">HOSAKOTE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32857">KANAKAPURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32855">MAGADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32854">NELAMANGALA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32856">RAMANAGARAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=138&districtName=Bangalore Urban">Bangalore Urban District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32850">ANEKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32845">B T M LAYOUT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32849">BANGALORE SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32843">BASAVANAGUDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32848">BOMMANAHALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32825">BYATARAYANA PURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32834">C V RAMANNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32841">CHAMRAJPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32842">CHICKPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32828">DASARAHALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32837">GANDHI NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32839">GOVINDRAJ NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32831">HEBBAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32846">JAYANAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32824">K R PURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32847">MAHADEVAPURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32829">MAHALAKSHMI LAYOUT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32830">MALLESHWARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32844">PADMANABA NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32832">PULAKESHINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32838">RAJAJI NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32827">RAJARAJESHWARINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32833">SARVAGNANAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32836">SHANTI NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32835">SHIVAJINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32840">VIJAY NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32823">YELAHANKA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32826">YESHVANTHAPURA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=119&districtName=Belgaum">Belgaum District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32681">ARABHAVI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32676">ATHANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32689">BAILHONGAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32685">BELGAUM DAKSHIN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32686">BELGAUM RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32684">BELGAUM UTTAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32675">CHIKKODI SADALGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32682">GOKAK</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32680">HUKKERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32677">KAGWAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32687">KHANAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32688">KITTUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32678">KUDACHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32674">NIPPANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32691">RAMDURG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32679">RAYBAG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32690">SAUNDATTIYELLAMMA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32683">YEMKANMARDI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=130&districtName=Bellary">Bellary District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32766">BELLARY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32767">BELLARY CITY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32761">HADAGALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32762">HAGARIBOMMANA HALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32764">KAMPLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32769">KUDLIGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32768">SANDUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32765">SIRUGUPPA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32763">VIJAYANAGARA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=123&districtName=Bidar">Bidar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32725">AURAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32720">BASAVAKALYAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32724">BHALKI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32723">BIDAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32722">BIDAR SOUTH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32721">HOMNABAD</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=121&districtName=Bijapur">Bijapur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32702">BABALESHWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32701">BASAVANA BAGEVADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32703">BIJAPUR CITY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32700">DEVAR HIPPARGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32705">INDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32699">MUDDEBIHAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32704">NAGTHAN</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32706">SINDGI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=145&districtName=Chamarajanagar">Chamarajanagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32896">CHAMARAJANAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32897">GUNDLUPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32894">HANUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32895">KOLLEGAL</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=135&districtName=Chikmagalur">Chikmagalur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32798">CHIKMAGALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32800">KADUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32797">MUDIGERE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32796">SRINGERI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32799">TARIKERE</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=131&districtName=Chitradurga">Chitradurga District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32771">CHALLAKERE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32772">CHITRADURGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32773">HIRIYUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32775">HOLALKERE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32774">HOSADURGA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32770">MOLAKALMURU</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=142&districtName=Dakshina Kannada">Dakshina Kannada District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32878">BANTVAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32873">BELTHANGADY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32877">MANGALORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32875">MANGALORE CITY NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32876">MANGALORE CITY SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32874">MOODABIDRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32879">PUTTUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32880">SULLIA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=132&districtName=Davanagere">Davanagere District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32782">CHANNAGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32779">DAVANAGERE NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32780">DAVANAGERE SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32777">HARAPANAHALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32778">HARIHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32783">HONNALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32776">JAGALUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32781">MAYAKONDA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=127&districtName=Dharwad">Dharwad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32744">DHARWAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32746">HUBLI DHARWAD CENTRAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32745">HUBLI DHARWAD EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32747">HUBLI DHARWAD WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32748">KALGHATGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32743">KUNDGOL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32742">NAVALGUND</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=126&districtName=Gadag">Gadag District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32739">GADAG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32741">NARGUND</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32740">RON</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32738">SHIRAHATTI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=122&districtName=Gulbarga">Gulbarga District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32707">AFZALPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32719">ALAND</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32715">CHINCHOLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32713">CHITTAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32717">GULBARGA DAKSHIN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32716">GULBARGA RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32718">GULBARGA UTTAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32712">GURMITKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32708">JEWARGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32714">SEDAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32710">SHAHAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32709">SHORAPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32711">YADGIR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=141&districtName=Hassan">Hassan District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32871">ARKALGUD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32867">ARSIKERE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32868">BELUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32869">HASSAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32870">HOLENARASIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32872">SAKLESHPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32866">SHRAVANABELAGOLA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=129&districtName=Haveri">Haveri District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32758">BYADGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32755">HANGAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32757">HAVERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32759">HIREKERUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32760">RANIBENNUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32756">SHIGGAON</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=143&districtName=Kodagu">Kodagu District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32881">MADIKERI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32882">VIRAJPET</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=137&districtName=Kolar">Kolar District </a>
			<ul style="display: none;">


			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32881">MADIKERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32882">VIRAJPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32813">BAGEPALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32820">BANGARAPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32814">CHIKKABALLAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32816">CHINTAMANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32812">GAURIBIDANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32821">KOLAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32819">KOLAR GOLD FIELD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32822">MALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32818">MULBAGAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32815">SIDLAGHATTA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32817">SRINIVASPUR</a></li>

			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=125&districtName=Koppal">Koppal District </a>
			<ul style="display: none;">
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32735">GANGAWATI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32734">KANAKAGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32737">KOPPAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32733">KUSHTAGI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32736">YELBURGA</a></li>



			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=140&districtName=Mandya">Mandya District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32865">KRISHNARAJPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32860">MADDUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32859">MALAVALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32862">MANDYA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32861">MELUKOTE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32864">NAGAMANGALA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32863">SRIRANGAPATNA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=144&districtName=Mysore">Mysore District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32890">CHAMARAJA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32888">CHAMUNDESH WARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32886">HEGGADADEVANKOTE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32885">HUNSUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32889">KRISHNARAJA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32884">KRISHNARAJA NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32887">NANJANGUD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32891">NARASIMHARAJA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32883">PIRIYAPATNA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32893">T NARASIPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32892">VARUNA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=124&districtName=Raichur">Raichur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32729">DEVADURGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32730">LINGSUGUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32728">MANVI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32732">MASKI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32727">RAICHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32726">RAICHUR RURAL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32731">SINDHANUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=133&districtName=Shimoga">Shimoga District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32785">BHADRAVATI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32790">SAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32788">SHIKARIPURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32786">SHIMOGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32784">SHIMOGA RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32789">SORAB</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32787">TIRTHAHALLI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=136&districtName=Tumkur">Tumkur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32801">CHIKKANAYAKANAHALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32808">GUBBI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32807">KORATAGERE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32804">KUNIGAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32811">MADHUGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32810">PAVAGADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32809">SIRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32802">TIPTUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32805">TUMKUR CITY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32806">TUMKUR RURAL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32803">TURUVEKERE</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=134&districtName=Udupi">Udupi District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32791">BYNDOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32794">KAPU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32795">KARKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32792">KUNDAPURA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32793">UDUPI</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=128&districtName=Uttara Kannada">Uttara Kannada District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32752">BHATKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32749">HALIYAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32750">KARWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32751">KUMTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32753">SIRSI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=32754">YELLAPUR</a></li>


			</ul>
			</li>		
			</ul>
			</li>
			
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=13">Kerala Assembly Constituencies </a>
			<ul style="display: none;">
			
			

			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=65&districtName=Alappuzha">Alappuzha District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1788">ALAPPUZHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1716">AMBALAPUZHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1712">AROOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1723">CHENGANNUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1787">CHERTHALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1718">HARIPAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1789">KARUNAGAPPALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1719">KAYAMKULAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1717">KUTTANAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1724">MAVELIKARA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1713">SHERTHALAI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=62&districtName=Ernakulam">Ernakulam District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1691">ALWAYE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1683">ANKAMALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1687">ERNAKULAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1776">KALAMASSERY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1778">KOCHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1696">KOTHAMANGALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1693">KUNNATHUNAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1695">MUVATTUPUZHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1685">PARUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1692">PERUMBAVOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1694">PIRAVOM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1779">THRIKKAKARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1690">TRIPPUNITHURA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1777">VYPEEN</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=63&districtName=Idukki">Idukki District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1698">DEVICOLAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1699">IDUKKI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1701">PEERMADE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1697">THODUPUZHA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1700">UDUMBANCHOLA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=56&districtName=Kannur">Kannur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1624">AZHICODE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1625">CANNANORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1758">DHARMADAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1621">IRIKKUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1757">KALLIASSERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1629">KUTHUPARAMBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1759">MATTANNUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1622">PAYYANNUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1630">PERAVOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1623">TALIPARAMBA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1627">TELLICHERRY</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=55&districtName=Kasaragod">Kasaragod District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1756">KANHANGAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1617">KASARAGOD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1616">MANJESWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1620">TRIKKARIPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1618">UDMA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=67&districtName=Kollam">Kollam District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1731">CHADAYAMANGALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1741">CHATHANOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1737">CHAVARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1740">ERAVIPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1736">KARUNAGAPALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1786">KOLLAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1732">KOTTARAKARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1738">KUNDARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1735">KUNNATHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1729">PATHANAPURAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1730">PUNALOOR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=64&districtName=Kottayam">Kottayam District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1704">CHANGANACHERRY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1706">ETTUMANOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1710">KADUTHURUTHY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1702">KANJIRAPPALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1705">KOTTAYAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1709">PALAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1708">POONJAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1707">PUTHUPPALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1711">VAIKOM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=58&districtName=Kozhikode">Kozhikode District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1637">BALUSSERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1641">BEYPORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1762">ELATHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1638">KODUVALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1763">KOZHIKODE NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1764">KOZHIKODE SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1642">KUNNAMANGALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1761">KUTTIADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1633">NADAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1636">PERAMBRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1635">QUILANDY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1643">THIRUVAMBADI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1785">VADAKARA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=59&districtName=Malappuram">Malappuram District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1765">ERNAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1650">KONDOTTY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1768">KOTTAKKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1649">MALAPPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1648">MANJERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1656">MANKADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1647">NILAMBUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1657">PERINTHALMANNA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1654">PONNANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1652">TANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1769">THAVANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1653">TIRUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1651">TIRURANGADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1767">VALLIKUNNU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1766">VENGARA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1646">WANDOOR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=60&districtName=Palakkad">Palakkad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1668">ALATHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1665">CHITTUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1771">KONGAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1663">MALAMPUZHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1662">MANNARKKAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1773">NEMMARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1660">OTTAPALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1664">PALGHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1659">PATTAMBI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1770">SHORNUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1772">TARUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1658">THRITHALA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=66&districtName=Pathanamthitta">Pathanamthitta District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1734">ADOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1722">ARANMULA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1728">KONNI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1726">RANNI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1720">THIRUVALLA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=68&districtName=Thiruvananthapuram">Thiruvananthapuram District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1783">ARUVIKKARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1743">ATTINGAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1780">CHIRAYINKEEZHU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1784">KATTAKKADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1748">KAZHAKUTTAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1753">KOVALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1747">NEDUMANGAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1752">NEMOM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1754">NEYYATTINKARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1755">PARASSALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1782">THIRUVANANTHAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1745">VAMANAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1742">VARKALA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1781">VATTIYOORKAVU</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=61&districtName=Thrissur">Thrissur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1676">CHALAKUDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1669">CHELAKARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1680">GURUVAYOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1678">IRINJALAKUDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1774">KAIPAMANGALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1682">KODUNGALLUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1671">KUNNAMKULAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1679">MANALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1681">NATTIKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1674">OLLUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1775">PUDUKKAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1673">TRICHUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1670">WADAKKANCHERRY</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=57&districtName=Wayanad">Wayanad District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1644">KALPETTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1760">MANANTHAVADY</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1645">SULTANS BATTERY</a></li>


			</ul>
			</li>			
			</ul>
			</li>
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=35">Puducherry Assembly Constituencies </a>
			<ul style="display: none;">
			
			

			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=116&districtName=KARAIKAL">KARAIKAL District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2350">KARAIKAL NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2337">KARAIKAL SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2340">NEDUNCADU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2338">NERAVY GRAND ALDEE</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2339">TIRUNALLAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=117&districtName=MAHE">MAHE District </a>
			<ul style="display: none;">


			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2341">MAHE</a></li>

			</ul>
			</li>
						<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=115&districtName=PUDUCHERRY">PUDUCHERRY District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2322">ARIANKUPPAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2326">BAHOUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2323">EMBALOM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2346">Indira Nagar</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2345">Kadirgamam</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2348">Kalapet</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2347">Kamraj Nagar</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2334">LAWSPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2349">Manavely</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2344">Mangalam</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2328">MANNADIPETH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2321">MODELIARPETH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2314">MUTHIALPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2320">NELLITHOPE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2324">NETTAPAKKAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2319">ORLEAMPETH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2329">OSSUDU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2318">OUPALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2331">OZHUKARAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2316">RAJ BHAVAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2332">THATTANCHAVADY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2327">THIRUBUVANAI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2330">VILLENOUR</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=118&districtName=YANAM">YANAM District </a>
			<ul style="display: none;">


			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2343">YANAM</a></li>


			</ul>
			</li>	
			</ul>
			</li>
			
			
			
			
			
			
						
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=21">Punjab Assembly Constituencies </a>
			<ul style="display: none;">
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=255&districtName=Amritsar">Amritsar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38762">AJNALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38760">AMRITSAR CENTRAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38758">AMRITSAR NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38761">AMRITSAR SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38759">AMRITSAR WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38764">ATTARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38754">BEAS</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38757">JANDIALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38766">KHADOOR SAHIB</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38755">MAJITHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38767">NAUSHAHRA PANWAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38768">PATTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38763">RAJA SANSI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38765">TARN TARAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38769">VALTOHA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38756">VERKA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=267&districtName=Bathinda">Bathinda District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38853">BHATINDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38854">NATHANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38852">PAKKA KALAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38855">RAMPURA PHUL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38851">TALWANDI SABO</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=266&districtName=Faridkot">Faridkot District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38846">FARIDKOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38845">KOT KAPURA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38844">PANJGRAIN</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=261&districtName=Fatehgarh Sahib">Fatehgarh Sahib District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38820">AMLOH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38821">SIRHIND</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=264&districtName=Firozpur">Firozpur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38833">ABOHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38832">BALLUANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38834">FAZILKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38837">FIROZEPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38838">FIROZEPUR CANTONMENT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38836">GURU HAR SAHAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38835">JALALABAD</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38839">ZIRA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=254&districtName=Gurdaspur">Gurdaspur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38744">BATALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38748">DHARIWAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38750">DINA NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38743">FATEHGARH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38749">GURDASPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38747">KAHNUWAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38751">NAROT MEHRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38752">PATHANKOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38745">QADIAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38746">SRIHARGOBINDPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38753">SUJANPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=258&districtName=Hoshiarpur">Hoshiarpur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38793">DASUYA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38792">GARHDIWALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38787">GARHSHANKAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38789">HOSHIARPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38788">MAHILPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38794">MUKERIAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38790">SHAM CHAURASI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38791">TANDA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=257&districtName=Jalandhar">Jalandhar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38770">ADAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38771">JULLUNDUR CANTONMENT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38773">JULLUNDUR CENTRAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38772">JULLUNDUR NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38774">JULLUNDUR SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38775">KARTARPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38776">LOHIAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38777">NAKODAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38778">NUR MAHAL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38781">PHILLAUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=256&districtName=Kapurthala">Kapurthala District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38782">BHOLATH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38783">KAPURTHALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38785">PHAGWARA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38784">SULTANPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=262&districtName=Ludhiana">Ludhiana District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38797">DAKHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38795">JAGRAON</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38806">KHANNA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38804">KUM KALAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38801">LUDHIANA EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38799">LUDHIANA NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38802">LUDHIANA RURAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38800">LUDHIANA WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38803">PAYAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38798">QILA RAIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38796">RAIKOT</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38805">SAMRALA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=268&districtName=Mansa">Mansa District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38858">BUDHLADA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38856">JOGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38857">MANSA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38859">SARDULGARH</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=263&districtName=Moga">Moga District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38842">BAGHA PURANA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38840">DHARAMKOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38841">MOGA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38843">NIHAL SINGH WALA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=265&districtName=Muktsar">Muktsar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38848">GIDDAR BAHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38850">LAMBI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38849">MALOUT</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38847">MUKTSAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=259&districtName=Nawanshahr">Nawanshahr District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38786">BALACHAUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38779">BANGA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38780">NAWAN SHAHR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=270&districtName=Patiala">Patiala District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38812">BANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38815">DAKALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38814">GHANAUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38819">NABHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38818">PATIALA TOWN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38813">RAJPURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38817">SAMANA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38816">SHUTRANA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=260&districtName=Rupnagar">Rupnagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38808">ANANDPUR SAHIB ROPAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38809">CHAMKAUR SAHIB</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38811">KHARAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38810">MORINDA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38807">NANGAL</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=269&districtName=Sangrur">Sangrur District </a>
			<ul style="display: none;">
			
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38825">BARNALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38826">BHADAUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38827">DHANAULA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38822">DHURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38829">DIRBHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38831">LEHRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38823">MALERKOTLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38828">SANGRUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38824">SHERPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38830">SUNAM</a></li>

			</ul>
			</li>		
			</ul>
			</li>
			
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=24">Tamil Nadu Assembly Constituencies </a>
			<ul style="display: none;">
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=40&districtName=Ariyalur">Ariyalur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1280">ARIYALUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1282">JAYANKONDAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=25&districtName=Chennai">Chennai District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1156">ANNA NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1381">Chepauk Thiruvallikeni</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1151">DR RADHAKRISHNAN NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1155">EGMORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1150">HARBOUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1165">KOLATHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1161">MYLAPORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1153">PERAMBUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1149">ROYAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1162">SAIDAPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1157">THEAGARAYA NAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1380">Thiru Vi Ka Nagar</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1158">THOUSAND LIGHTS</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1383">Velachery</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1164">VILLIVAKKAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1382">Virugampakkam</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=35&districtName=Coimbatore">Coimbatore District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1245">AVANASHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1411">Coimbatore North</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1412">Coimbatore South</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1410">Kavundampalayam</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1251">KINATHUKKADAVU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1413">Madathukulam</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1244">METTUPALAYAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1255">PALLADAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1252">POLLACHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1247">SINGANALLUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1409">Sulur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1246">THONDAMUTHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1407">Tiruppur North</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1408">Tiruppur South</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1254">UDUMALPET</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1253">VALPARAI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=41&districtName=Cuddalore">Cuddalore District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1289">BHUVANAGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1291">CHIDAMBARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1286">CUDDALORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1290">KATTUMANNARKOIL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1288">KURINJIPADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1421">Neyveli</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1287">PANRUTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1420">Tittakudi</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1292">VRIDHACHALAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=28&districtName=Dharmapuri">Dharmapuri District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1190">DHARMAPURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1187">HARUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1189">PALACODE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1392">Pappireddippatti</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1191">PENNAGARAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=36&districtName=Dindigul">Dindigul District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1262">ATHOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1261">DINDIGUL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1260">NATHAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1259">NILAKOTTAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1258">ODDANCHATRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1257">PALANI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1263">VEDASANDUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=33&districtName=Erode">Erode District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1237">ANDHIYUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1236">BHAVANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1239">BHAVANISAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1230">DHARAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1404">Erode East</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1405">Erode West</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1238">GOBICHETTIPALAYAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1232">KANGAYAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1233">MODAKURICHI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1234">PERUNDURAI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=26&districtName=Kancheepuram">Kancheepuram District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1166">ALANDUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1169">CHENGALPATTU</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1386">Cheyyur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1173">KANCHEEPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1170">MADURANTHAKAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1385">Pallavaram</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1384">Shozhinganallur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1174">SRIPERUMBUDUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1167">TAMBARAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1168">TIRUPPORUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1172">UTHIRAMERUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=53&districtName=Kanniyakumari">Kanniyakumari District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1366">COLACHEL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1364">KANNIYAKUMARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1370">KILLIYOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1365">NAGERCOIL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1367">PADMANABHAPURAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1369">VILAVANCODE</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=37&districtName=Karur">Karur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1265">ARAVAKURICHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1266">KARUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1267">KRISHNARAYAPURAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1268">KULITHALAI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=54&districtName=Krishnagiri">Krishnagiri District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1374">BARGUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1371">HOSUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1373">KRISHNAGIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1372">THALLI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1390">Uthangarai</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1391">Veppanahalli</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=47&districtName=Madurai">Madurai District </a>
			<ul style="display: none;">


			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1324">MADURAI CENTRAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1325">MADURAI EAST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1431">Madurai North</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1432">Madurai South</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1323">MADURAI WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1327">MELUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1321">SHOLAVANDAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1319">THIRUMANGALAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1322">TIRUPPARANKUNDRAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1320">USILAMPATTI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=42&districtName=Nagapattinam">Nagapattinam District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1423">Kilvelur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1424">Mayiladuthurai</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1295">NAGAPATTINAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1294">POOMPUHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1422">Sirkazhi</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1296">VEDARANYAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=32&districtName=Namakkal">Namakkal District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1403">Kumarapalayam</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1228">NAMAKKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1402">Paramathi Velur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1226">RASIPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1227">SENDAMANGALAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1229">TIRUCHENGODE</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=34&districtName=Nilgiris">Nilgiris District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1241">COONOOR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1243">GUDALUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1406">Udhagamandalam</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=39&districtName=Perambalur">Perambalur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1429">Alangudi</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1419">Kunnam</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1278">PERAMBALUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=45&districtName=Pudukkottai">Pudukkottai District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1283">ALANGUDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1430">Aranthangi</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1427">Gandharvakottai</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1313">PUDUKKOTTAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1312">THIRUMAYAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1428">Viralimalai</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=50&districtName=Ramanathapuram">Ramanathapuram District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1344">MUDUKULATHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1341">PARAMAKUDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1342">RAMANATHAPURAM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1339">TIRUVADANAI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=31&districtName=Salem">Salem District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1221">ATTUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1225">EDAPADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1398">Gangavalli</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1213">METTUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1215">OMALUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1400">Salem North</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1401">Salem South</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1399">Salem West</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1224">SANKARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1219">VEERAPANDI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1216">YERCAUD</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=46&districtName=Sivaganga">Sivaganga District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1315">KARAIKUDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1317">MANAMADURAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1316">SIVAGANGA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1314">TIRUPPATTUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=44&districtName=Thanjavur">Thanjavur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1310">KUMBAKONAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1305">ORATHANAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1309">PAPANASAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1303">PATTUKKOTTAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1304">PERAVURANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1307">THANJAVUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1311">THIRUVIDAMARUDUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1308">TIRUVAIYARU</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=48&districtName=Theni">Theni District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1332">ANDIPATTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1330">BODINAYAKKANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1331">CUMBUM</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1328">PERIYAKULAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=24&districtName=Thiruvallur">Thiruvallur District </a>
			<ul style="display: none;">


			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1377">Ambattur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1375">Avadi</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1141">GUMMIDIPUNDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1379">Madavaram</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1376">Maduravoyal</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1142">PONNERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1143">POONAMALLEE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1145">TIRUTTANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1144">TIRUVALLUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1378">Tiruvottiyur</a></li>

			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=43&districtName=Thiruvarur">Thiruvarur District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1299">NANNILAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1425">Thiruthuraipoondi</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1426">Thiruvarur</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=51&districtName=Thoothukkudi">Thoothukkudi District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1347">KOILPATTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1435">Ottapidaram</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1350">SRIVAIKUNTAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1434">Thoothukkudi</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1349">TIRUCHENDUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1345">VILATHIKULAM</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=38&districtName=Tiruchirappalli">Tiruchirappalli District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1273">LALGUDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1417">Manachanallur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1414">Manapparai</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1272">MUSIRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1274">SRIRANGAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1277">THIRUVERAMBUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1418">Thuraiyur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1416">Tiruchirappalli East</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1415">Tiruchirappalli West</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=52&districtName=Tirunelveli">Tirunelveli District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1357">ALANGULAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1361">AMBASAMUDRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1355">KADAYANALLUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1362">NANGUNERI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1359">PALAYAMCOTTAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1363">RADHAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1353">SANKARANAYANARKOIL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1356">TENKASI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1358">TIRUNELVELI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1354">VASUDEVANALLUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=29&districtName=Tiruvannamalai">Tiruvannamalai District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1197">ARNI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1192">CHENGAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1198">CHEYYAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1195">KALASAPAKKAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1393">Kilpennathur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1196">POLUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1194">TIRUVANNAMALAI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1199">VANDAVASI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=27&districtName=Vellore">Vellore District </a>
			<ul style="display: none;">


			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1388">Ambur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1185">ANAICUT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1178">ARCOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1175">ARKONAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1180">GUDIYATHAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1389">Jolarpet</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1179">KATPADI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1387">Kilvaithinankuppam</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1177">RANIPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1176">SHOLINGHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1184">TIRUPPATTUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1182">VANIAYAMBADI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1186">VELLORE</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=30&districtName=Viluppuram">Viluppuram District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1202">GINGEE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1397">Kallakurichi</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1394">Mailam</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1210">RISHIVANDIAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1212">SANKARAPURAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1203">TINDIVANAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1396">Tirukkoyilur</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1209">ULUNDURPET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1204">VANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1395">Vikravandi</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1206">VILLUPURAM</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=49&districtName=Virudhunagar">Virudhunagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1333">ARUPPUKOTTAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1338">RAJAPALAYAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1334">SATTUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1336">SIVAKASI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1337">SRIVILLIPUTHUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1433">Tiruchuli</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1335">VIRUDHUNAGAR</a></li>


			</ul>
			</li>
			</ul>
			</li>
			
			
			
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=26">Uttaranchal Assembly Constituencies </a>
			<ul style="display: none;">
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=249&districtName=Almora">Almora District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38718">ALMORA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38714">BHIKIASAIN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38713">DWARAHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38719">JAGESHWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38716">RANIKHET</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38715">SALT</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38717">SOMESHWAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=248&districtName=Bageshwar">Bageshwar District </a>
			<ul style="display: none;">


			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38712">BAGESHWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38711">KANDA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38710">KAPKOT</a></li>

			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=242&districtName=Chamoli">Chamoli District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38676">BADRINATH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38678">KARANPRAYAG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38677">NANDPRAYAG</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38679">PINDAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=250&districtName=Champawat">Champawat District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38720">CHAMPAWAT</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38721">LOHAGHAT</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=245&districtName=Dehradun">Dehradun District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38688">CHAKRATA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38692">DEHRADUN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38696">DOIWALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38691">LAXMAN CHOWK</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38694">MUSSOORIE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38693">RAJPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38695">RISHIKESH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38690">SAHASPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38689">VIKASNAGAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=246&districtName=Garhwal">Garhwal District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38700">BIRONKHAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38699">DHUMAKOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38698">KOTDWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38701">LANSDOWNE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38702">PAURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38703">SRINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38704">THALISAIN</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38697">YAMAKESHWAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=253&districtName=Hardwar">Hardwar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38740">BAHADRABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38734">BHAGWANPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38741">HARDWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38736">IQBALPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38739">LAKSAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38742">LALDHANG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38738">LANDHAURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38737">MANGLOR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38735">ROORKEE</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=251&districtName=Nainital">Nainital District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38723">DHARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38724">HALDWANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38722">MUKTESHWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38725">NAINITAL</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38726">RAMNAGAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=247&districtName=Pithoragarh">Pithoragarh District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38709">DHARCHULA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38707">DIDIHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38706">GANGOLIHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38708">KANALICHHINA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38705">PITHORAGARH</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=243&districtName=Rudraprayag">Rudraprayag District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38681">KEDARNATH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38680">RUDRAPRAYAG</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=244&districtName=Tehri Garhwal">Tehri Garhwal District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38685">DEOPRAYAG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38687">DHANOLTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38684">GHANSALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38686">NARENDRANAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38682">PRATAPNAGAR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38683">TEHRI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=252&districtName=Udham Singh Nagar">Udham Singh Nagar District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38729">BAJPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38727">JASPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38728">KASHIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38733">KHATIMA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38730">PANTNAGAR GADARPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38731">RUDRAPUR KICHHA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38732">SITARGANJ</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=241&districtName=Uttarkashi">Uttarkashi District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38674">GANGOTRI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38673">PUROLA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=38675">YAMUNOTRI</a></li>


			</ul>
			</li>			
			</ul>
			</li>
			
			
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://www.partyanalyst.com/statePageAction.action?stateId=28">West Bengal Assembly Constituencies </a>
			<ul style="display: none;">
			
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=112&districtName=BANKURA">BANKURA District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2198">BANKURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2197">BARJORA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2195">CHHATNA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2202">INDAS</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=31644">KATULPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2201">KOTULPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2199">ONDA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2192">RAIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2193">RANIBANDH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2301">SALTORA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2203">SONAMUKHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2191">TALDANGRA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2200">VISHNUPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=113&districtName=BARDHAMAN">BARDHAMAN District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2308">ASANSOL NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2307">ASANSOL SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2214">AUSGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2205">BARABANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2215">BHATAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2217">BURDWAN NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2218">BURDWAN SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2305">DURGAPUR PURBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2306">DURGAPUR WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2216">GALSI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2221">JAMALPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2209">JAMURIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2223">KALNA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2227">KATWA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2229">KETUGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2219">KHANDAGHOSH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2204">KULTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2228">MANGALKOT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2225">MANTESWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2222">MEMARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2304">PANDABESWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2303">PURBASTHALI NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2302">PURBASTHALI SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2220">RAINA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2208">RANIGANJ</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=114&districtName=BIRBHUM">BIRBHUM District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2231">BOLPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2233">DUBRAJPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2239">HANSAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2232">LABHPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2237">MAYURESWAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2241">MURARAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2240">NALHATI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2230">NANUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2238">RAMPURHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2309">SAINTHIA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2235">SURI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=100&districtName=DAKSHIN DINAJPUR">DAKSHIN DINAJPUR District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1985">BALURGHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1981">GANGARAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2247">HARIRAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1984">KUMARGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1982">KUSHMANDI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1983">TAPAN</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=98&districtName=DARJEELING">DARJEELING District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1970">DARJEELING</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1969">KALIMPONG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1971">KURSEONG</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2244">MATIGARANAXALBARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1973">PHANSIDEWA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1972">SILIGURI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=108&districtName=HOOGHLY">HOOGHLY District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2141">ARAMBAGH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2135">BALAGARH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2128">CHAMPDANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2129">CHANDERNAGORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2125">CHANDITALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2291">CHUNCHURA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2138">DHANIAKHALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2142">GOGHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2131">HARIPAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2124">JANGIPARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2140">KHANAKUL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2136">PANDUA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2139">PURSURAH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2292">SAPTAGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2127">SERAMPORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2130">SINGUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2132">TARAKESWAR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2126">UTTARPARA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=107&districtName=HOWRAH">HOWRAH District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2122">AMTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2120">BAGNAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2108">BALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2113">DOMJUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2110">HOWRAH CENTRAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2109">HOWRAH NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2111">HOWRAH SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2114">JAGATBALLAVPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2115">PANCHLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2116">SANKRAIL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2112">SHIBPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2119">SHYAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2123">UDAYNARAYANPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2117">ULUBERIA NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2290">ULUBERIA PURBA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2118">ULUBERIA SOUTH</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=97&districtName=JALPAIGURI">JALPAIGURI District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2243">ABGRAM FULBARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1959">ALIPURDUARS</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1962">DHUPGURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1960">FALAKATA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1967">JALPAIGURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1958">KALCHINI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1957">KUMARGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1961">MADARIHAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1964">MAINAGURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1965">MAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1963">NAGRAKATA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1968">RAJGANJ</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=96&districtName=KOCH BIHAR">KOCH BIHAR District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1951">COOCH BEHAR NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1952">COOCH BEHAR SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1954">DINHATA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1950">MATHABHANGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1948">MEKLIGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1955">NATABARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1953">SITAI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1949">SITALKUCHI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1956">TUFANGANJ</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=106&districtName=KOLKATA">KOLKATA District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2099">BALLYGUNGE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2102">BELIAGHATA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2289">BHABANIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2093">CHOWRINGHEE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2100">ENTALLY</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2090">JORASANKO</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2288">KASHIPURBELGACHHIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2287">KOLKATA PORT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2106">MANICKTOLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2096">RASHBEHARI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2088">SHYAMPUKUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=101&districtName=MALDAHA">MALDAHA District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2251">BAISNABNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2248">CHANCHAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1993">ENGLISH BAZAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1987">GAJOL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1986">HABIBPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1989">HARISHCHANDRAPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2249">MALATIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1992">MALDAHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1994">MANIKCHAK</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2250">MOTHABARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1990">RATUA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1995">SUZAPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=102&districtName=MURSHIDABAD">MURSHIDABAD District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2014">BARWAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2011">BELDANGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2010">BERHAMPORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2003">BHAGABANGOLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2015">BHARATPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2007">DOMKAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1997">FARAKKA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2009">HARIHARPARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2006">JALANGI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2001">JANGIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2012">KANDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2013">KHARGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2002">LALGOLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2005">MURSHIDABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2004">NABAGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2008">NAODA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2253">RAGHUNATHGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2254">RANINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2255">REJINAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2000">SAGARDIGHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2252">SAMSERGANJ</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1999">SUTI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=103&districtName=NADIA">NADIA District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2029">CHAKDAHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2020">CHAPRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2030">HARINGHATA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2019">KALIGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2259">KALYANI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2016">KARIMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2021">KRISHNAGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2257">KRISHNANAGAR NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2258">KRISHNANAGAR SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2024">NABADWIP</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2018">NAKASHIPARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2017">PALASHIPARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2262">RANAGHAT SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2260">RANAGHAT UTTAR PASCHIM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2261">RANAGHAT UTTAR PURBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2025">SANTIPUR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2256">TEHATTA</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=104&districtName=NORTH 24 PARGANAS">NORTH 24 PARGANAS District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2036">AMDANGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2035">ASHOKENAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2041">BADURIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2031">BAGDAHA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2263">BANGAON NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2264">BANGAON SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2054">BARANAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2037">BARASAT</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2265">BARRACKPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2273">BASIRHAT NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2272">BASIRHAT SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2047">BHATPARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2268">BIDHANNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2045">BIJPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2039">DEGANGA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2055">DUM DUM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2266">DUM DUM NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2033">GAIGHATA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2034">HABRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2044">HAROA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2057">HINGALGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2048">JAGATDAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2053">KAMARHATI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2051">KHARDAH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2270">MADHYAMGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2271">MINAKHAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2046">NAIHATI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2049">NOAPARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2052">PANIHATI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2269">RAJARHAT GOPALPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2267">RAJARHAT NEW TOWN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2056">SANDESHKHALI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2040">SWARUPNAGAR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=110&districtName=PASCHIM MEDINIPUR">PASCHIM MEDINIPUR District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2179">BINPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2143">CHANDRAKONA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2175">DANTAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2145">DASPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2165">DEBRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2299">GARBETA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2144">GHATAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2177">GOPIBALLAVPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2178">JHARGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2173">KESHIARI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2166">KESHPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2298">KHARAGPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2297">KHARAGPUR SADAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2170">MIDNAPORE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2174">NARAYANGARH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2176">NAYAGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2164">PINGLA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2163">SABANG</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2169">SALBANI</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=109&districtName=PURBO MEDINIPUR">PURBO MEDINIPUR District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2155">BHAGABANPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2296">CHANDIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2157">CONTAI NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2158">CONTAI SOUTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2160">EGRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2295">HALDIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2156">KHAJURI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2151">MAHISHADAL</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2150">MOYNA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2294">NANDAKUMAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2153">NANDIGRAM</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2293">PANSKURA PURBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2147">PANSKURA WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2162">PATASPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2159">RAMNAGAR</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2149">TAMLUK</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=111&districtName=PURULIA">PURULIA District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2300">BAGHMUNDI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2182">BALRAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2180">BANDUAN</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2185">JAIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2189">KASHIPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2181">MANBAZAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2187">PARA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2186">PURULIA</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2188">RAGHUNATHPUR</a></li>


			</ul>
			</li>
			<li class="expandable"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=105&districtName=SOUTH 24 PARGANAS">SOUTH 24 PARGANAS District </a>
			<ul style="display: none;">

			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2276">BARUIPUR PURBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2278">BARUIPUR WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2059">BASANTI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2285">BEHALA PURBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2071">BEHALA WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2065">BHANGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2281">BISHNUPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2074">BUDGE BUDGE</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2277">CANNING PURBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2063">CANNING WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2077">DIAMOND HARBOUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2076">FALTA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2058">GOSABA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2066">JADAVPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2275">JAYNAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2084">KAKDWIP</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2283">KASBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2082">KULPI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2060">KULTALI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2279">MAGRAHAT PURBA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2078">MAGRAHAT WEST</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2073">MAHESHTALA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2080">MANDIRBAZAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2286">METIABURUZ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2083">PATHARPRATIMA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2274">RAIDIGHI</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2085">SAGAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2280">SATGACHHIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2075">SATGACHIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2284">SONARPUR NORTH</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2282">SONARPUR SOUTH</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2097">TOLLYGUNGE</a></li>


			</ul>
			</li>
			<li class="last"><div class="hitarea expandable-hitarea"></div><a href="http://partyanalyst.com/districtPageAction.action?districtId=99&districtName=UTTAR DINAJPUR">UTTAR DINAJPUR District </a>
			<ul style="display: none;">
			
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2245">CHAKULIA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1974">CHOPRA</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1976">GOALPOKHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=2246">HEMTABAD</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1975">ISLAMPUR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1980">ITAHAR</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1979">KALIAGANJ</a></li>
			<li><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1977">KARANDIGHI</a></li>
			<li class="last"><a href="http://www.partyanalyst.com/constituencyPageAction.action?constituencyId=1978">RAIGANJ</a></li>
			</ul>
			</li>

			</ul>
			</li>
			</ul>
		
		</li>
	    </ul>
	</li>
	

</ul>
	</div></td></tr></table>  

</div>

</div>



</body>
</html>