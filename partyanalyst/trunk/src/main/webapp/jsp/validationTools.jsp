<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script> --%>

<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
   
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<script type="text/javascript" src="js/jtransform/jquery.custom_radio_checkbox.js" ></script>
<script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" src="js/highcharts/js/highcharts3.js"></script>
<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css">


<title>Voters Validation Tool</title>

<style type="text/css">
.mainDiv{margin-left:auto;margin-right:auto;width:980px;}
.requiredFont{
	color:red;
	font-size:13px;
}
#sublevel{margin-top:10px;}
#sublevel th{background:#CDE6FC;}
</style>
</head>

<body>

<div class="mainDiv">
<div class="widget blue">
<div id="ConstituencyDiv" class="selectDiv" style="margin-top:10px;">
	 Constituency<font class="requiredFont">*</font><s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth" label="Select Your State" name="constituencyList" id="constituencyList" list="constituencyList" listKey="id" listValue="name" onchange="getPublicationDate();"/> &nbsp;&nbsp;

		
	 Publication Date<font class="requiredFont">*</font> <select id="publicationDateList" class="selectWidth" style="width:172px;height:25px;" name="publicationDateList" >
		</select>
	
	
	 
	</div>
<div id="ajaxImgId" style="display:none;margin-right:427px;float:right;"><img src="./images/icons/search.gif" alt="Processing Image"/></div>	
<div style="margin-top:10px;margin-bottom:10px;text-align: center;clear:both;">
<input type="button" style="position: absolute:top: 50%;" value="View" class="btn btn-success" onclick="getSubLevelInfo();">

</div>
<br>
</div>
<div class="widget blue" id="subLevelDataId" style="display:none;">
<div id="subLevelInfo">
</div>
</div>
</div>

<script type="text/javascript">

function getSubLevelInfo()
{
$("#ajaxImgId").show();
$("#subLevelDataId").show();
var constituencyId = $("#constituencyList").val();
var publicationDateId = $("#publicationDateList").val();
var jsObj=
		{		
			constituencyId:constituencyId,
			publicationDateId:publicationDateId,
            task:"getSubLevelInfo"				
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSubLevelInfoAction.action?"+rparam;						
		callAjax(jsObj,url);

}

function callAjax(jsObj,url)
{
 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);					
								if(jsObj.task == "getSubLevelInfo")
								{
								buildData(myResults);	
								}
								else if(jsObj.task == "getPublicationDate")
								{
								
									buildPublicationDateList(myResults);
								}
							}catch (e) {
							    //alert(Exception);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 }
 
 function getPublicationDate()
	{
	var constituencyID = $("#constituencyList").val();
	
	var jsObj=
	{
		selected:constituencyID,
		task:"getPublicationDate"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "voterAnalysisAjaxAction.action?"+rparam;	

	
	callAjax(jsObj,url);
}
var publicationDatesList;
function buildPublicationDateList(results)
	{
	publicationDatesList=results;
	var selectedElmt=document.getElementById("publicationDateList");
	//var selectElmt =jsObj.selectElmt;
	removeSelectElements(selectedElmt);

	var  publicationIdsArray = new Array();

	for(var val in results)
	{	
	publicationIdsArray.push(results[val].id);

		var opElmt = document.createElement('option');
		opElmt.value=results[val].id;
		opElmt.text=results[val].name;

		try
		{
			selectedElmt.add(opElmt,null); // standards compliant
		}
		catch(ex)
		{
			selectedElmt.add(opElmt); // IE only
		}	
	}

	var largest = Math.max.apply(Math, publicationIdsArray);

	$('#publicationDateList').val(largest);
	$('#publicationDateList').trigger("change");

}
function removeSelectElements(selectedElmt)
	{
		var len = selectedElmt.length;
		for(var i=len-1;i>=0;i--)
		{
			selectedElmt.remove(i);
		}
	}
	

function buildData(results)
{
	$("#ajaxImgId").hide();
	var str = '';
	var divele = document.getElementById("subLevelInfo");
	
	str +='<table id="sublevel" class="table table-bordered table-striped table-hover">';
	str += '<tr>'
	str += '<th>Mandal</th>';
	str += '<th>Panchayat</th>';
	str += '<th>Total Voters</th>';
	str += '<th>Assigned Voters</th>';
	str += '<th>Not Assigned Voters</th>';
	str += '<th>Total Hamlets</th>';
	str += '<th>Hamlets Assigned</th>';
	str += '<th>Hamlets Not Assigned</th>';
	str += '</tr>';

	for(var i in results)
	{
		str += '<tr>';	
		str += '<td>'+results[i].tehsilName+'</td>';
		str += '<td>'+results[i].panchayatName+'</td>';
		str += '<td>'+results[i].totalVoters+'</td>';
		str += '<td>'+results[i].hamletAssignedVoters+'</td>';
		str += '<td>'+results[i].hamletsNotAssignedVoters+'</td>';

		str += '<td>';
		for(var j in results[i].hamletsList)
			str += ''+results[i].hamletsList[j].name+'<br>';
		str += '</td>';
	
		str += '<td>';
		for(var k in results[i].assignedHamletsList)
			str += ''+results[i].assignedHamletsList[k].name+' ('+results[i].assignedHamletsList[k].populateId+')<br>';
		str += '</td>';

		str += '<td>';
		for(var l in results[i].notAssignedHamletsList)
			str += ''+results[i].notAssignedHamletsList[l].name+'<br>';
		str += '</td>';

		str += '</tr>';	
	}
	str +='</table>';
	divele.innerHTML = str;
}
</script>
</body>
</html>