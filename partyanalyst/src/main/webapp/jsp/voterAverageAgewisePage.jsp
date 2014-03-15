<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voting Trendz</title>

<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>

<script type="text/javascript" src="../js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="../js/yahoo/yui-gallery/gallery-accordion-min.js"></script>

<!-- YUI Skin Sam -->

<link rel="stylesheet" type="text/css" href="../styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<!-- YUI Dependency files (End) -->

<script type="text/javascript" src="../js/constituencyPage/constituencyPage.js"></script>
<link rel="stylesheet" type="text/css" href="../styles/constituencyPage/constituencyPage.css">	
<link href="styles/indexPage/indexPage.css" rel="stylesheet" type="text/css" />
</HEAD>
<style>

</style>
 <BODY>

		<div id="mainDiv">
<div id="innerDiv"></div>
		</div>



 
  <script>
  var constiId= "${constiId}";
	function getAverageVoterDetails(){
		alert("call");
	//var constiId = ("#").val();
	var jsObj = {
			constiId : 232,
			task : "getAverageVoterDetails"
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getAverageVoterDetailsAction.action?"+rparam;
	callAjax(jsObj, url);

	}

function callAjax(jsObj,url){
		 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task =="getAverageVoterDetails")
								{
									buildAverageVoterDetails(myResults);
								}
							}catch (e) {
							     
							}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                		//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}









var reqfieldsArr = new Array();


function buildAverageVoterDetails(results){
var str = "";

str += "<div class='span12 widget' id='div1' >";
str += "<h3>Constituency Voter Average Age Report</h3>";
str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";
	str += "<table  style='margin-bottom: -8px; margin-top: -20px; font-size:small;'>";
	str += "<tr><td><span>Total voters : </span></td>";
	str += "<td><span>"+results.totalCount+"</span></td><td></td>";
	str += "<td><span> AvgVoters</span></td>";
	str += "<td><span>"+results.validCount+"</span></td>";
	str += "</tr>";
	str += "</table>";

//mandalwise
		str += "<div class='row-fluid'>";
		str += "<div class='span12 widget' id='mandalDiv'  style='margin-top: 30px;'>";
		str += "<h4>Mandalwise Voter Average Age Report</h4>";
		str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";

		str += "<table class='table table-bordered' style='margin-bottom: -8px; margin-top: -20px;style='font-size:small''>";
		
		
		for(var i in results.selectOptionsList){
			str += "<tr>";
			str += "<td><span>"+results.selectOptionsList[i].id+"</span></td>";
			str += "<td><span>"+results.selectOptionsList[i].totalCount+"</span></td>";
			str += "<td><span>"+results.selectOptionsList[i].validCount+"</span></td>";
			str += "</tr>";
		}
		str += "</table>";

		str += "</div>";
		str += "</div>";

//panchayatwise
		str += "<div class='row-fluid'>";
		str += "<div class='span12 widget' id='mandalDiv' >";
		str += "<h4>Panchayatwise Voter Average Age Report</h4>";
		str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";

		str += "<table class='table table-bordered' style='margin-bottom: -8px; margin-top: -20px;style='font-size:small'>";
		
		for(var i in results.selectOptionsList1){
			str += "<tr>";
			str += "<td rowspan='"+results.selectOptionsList1[i].selectOptionsList.length+"'><span>mandalname"+results.selectOptionsList1[i].id+"</span></td>";
				for(var j in results.selectOptionsList1[i].selectOptionsList){
					str += "<td><span>"+results.selectOptionsList1[i].selectOptionsList[j].id+"</span></td>";
					str += "<td><span>"+results.selectOptionsList1[i].selectOptionsList[j].totalCount+"</span></td>";
					str += "<td><span>"+results.selectOptionsList1[i].selectOptionsList[j].validCount+"</span></td>";
					str += "</tr>";
				}
		str += "</tr>";
		}
		str += "</table>";

		str += "</div>";
		str += "</div>";

//boothwise
		str += "<div class='row-fluid'>";
		str += "<div class='span12 widget' id='mandalDiv' >";
		str += "<h4>Boothwise Voter Average Age Report</h4>";
		str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";

		str += "<table class='table table-bordered' style='margin-bottom: -8px; margin-top: -20px;style='font-size:small'>";
		
		for(var i in results.selectOptionsList2){
			str += "<tr>";
			str += "<td rowspan='"+results.selectOptionsList2[i].selectOptionsList.length+"'><span>mandalname"+results.selectOptionsList2[i].id+"</span></td>";
				//for(var j in results.selectOptionsList2[i].selectOptionsList){
					str += "<td><span>"+results.selectOptionsList2[i].selectOptionsList[j].id+"</span></td>";
					str += "<td><span>"+results.selectOptionsList2[i].selectOptionsList[j].totalCount+"</span></td>";
					str += "<td><span>"+results.selectOptionsList2[i].selectOptionsList[j].validCount+"</span></td>";
					str += "</tr>";
				//}
		str += "</tr>";
		}
		str += "</table>";

		str += "</div>";
		str += "</div>";



str += "</div>";
str += "</div>";

document.getElementById('innerDiv').innerHTML = str;
}

/*function dummy(){
var str = "";

str += "<div class='span12 widget' id='div1' style='font-size:small'>";
str += "Constituency Voter Average Age Report";
str += "<div class='form-horizontal boothResults ' name='boothSelection' style='display:block;margin: 30px 30px;'>";
	str += "<table  style='margin-bottom: -8px; margin-top: -20px; font-size:small;'>";
	str += "<tr><td><span>Total voters : </span></td>";
	str += "<td><span>totalCount</span></td>";
	str += "<td style='float:right;'><span> AvgVoters</span></td>";
	str += "<td><span>validCount</span></td>";
	str += "</tr>";
	str += "</table>";
//buildAverageVoterDetails();
 
 document.getElementById('innerDiv').innerHTML = str;
}

dummy();*/

getAverageVoterDetails();
  </script>
 </BODY>
</HTML>
