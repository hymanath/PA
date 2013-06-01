<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Party Analyst</title>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>

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
   <script type="text/javascript" src="js/jquery.dataTables.js"></script>
   <link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 
	
   <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
   <script type="text/javascript" src="http://www.google.com/jsapi"></script>
   <%--  <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/facescroll.js"></script>
	   <script type="text/javascript" src="http://www.dynamicdrive.com/dynamicindex11/facescroll/jquery.ui.touch-punch.min.js"></script> --%>

  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
  <link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/>
  <link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<style type="text/css">
#localCastStatsTabContent_subbody1 table{border:1px solid #d3d3d3;border-collapse:collapse;padding:10px;margin-left:auto;margin-right:auto;width:100%;}
#localCastStatsTabContent_subbody1 table td{padding:8px;padding-left:10px;font-weight:normal;font:small-caption;color: #676A67;}
#localCastStatsTabContent_subbody1 table tr:nth-child(even){background:#EdF5FF;}
#localCastStatsTabContent_subbody1 table th{
	background-color: #CDE6FC;
    font-size: 13px;
    font-weight: bold;
    padding-bottom: 10px;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 10px;
    text-align: left;
	color:#333333;
	}

#localCastStatsTabContent_subbody1 table th a{
color:#333333;
}
.localCastStatsVotersTitle{
	border-radius: 3px 3px 3px 3px;
    color: #FFFFFF !important;
    font-family: verdana;
    font-size: 15px !important;
    font-weight: bold;
    padding: 5px;
    width: 650px;
    }
.localCastStatsVotersTitle{
	background:#06ABEA;
}
#localCastStatsVotersTitle{float: none;
    margin-left: auto;
    margin-right: auto;
    text-align: center;}

	#casteSelectDiv{margin-top:20px;margin-bottom:30px;}
#casteSelectDiv {
    margin-bottom: 15px;
    margin-left: 150px;
    margin-right: 150px;
    padding: 5px;
    width: 700px;
	 padding-bottom: 10px;
}
.casteSelectDivCls{border: 2px solid #4499EE;}
#castSelectdId{height:55px;}
#castAllRadio,#castSelectRadio{margin-right: 4px;margin-top: 0;}
#castSelectRadio,#castAllRadio{ margin-left: 16px;}
#castErrorDiv{color: red;font-family: verdana;text-align: center;}
#casteHideAndShowOptionsDiv{ margin-left: 188px;
    margin-top: -21px;}
#casteSelectDiv h4{ margin-bottom: 13px;margin-top: 5px; font-size: 15px;text-align:center;}
</style>
<script type="text/javascript">
var areaType = "${areaType}";
var locationValue = "${locationValue}";
</script>
</head>
<body>
<dir id="customVotersMainDiv">
<div id="ajaxImg" style="display:none;"><img src="./images/icons/goldAjaxLoad.gif"></div>
<div id ="localCastStatsVotersTitle" ></div>
<div id="casteSelectDiv"></div>
<div id="localCastStatsTabContent_subbody1" class="yui-skin-sam yui-dt-sortable"></div>
</dir>

<script type="text/javascript">
function getCasteWiseCustomGroupVoters()
{
  $("#ajaxImg").css("display","block");
  var jsObj=
  {
	areaType:"Rural",
	locationValue:1,
	task:"getCustomGroupWiseCasteVoters"
  };

  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
  var url = "getCustomGroupWiseCasteVotersAction.action?"+rparam+"&save=";	
  callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 	 success : function( o ) {
	  try {												
		myResults = YAHOO.lang.JSON.parse(o.responseText);
		
			if(jsObj.task == "getCustomGroupWiseCasteVoters")
			  buildCustomGroupWiseCasteVoters(myResults,jsObj);

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

function buildCustomGroupWiseCasteVoters(results,jsObj)
{
	$("#ajaxImg").css("display","none");
	var votersDetails = [
				   {key: "name", label: "Group Name", sortable: true},
				   {key: "castName", label: "Caste", sortable: true},
				   {key: "casteCategoryName", label: "Caste Category", sortable: true},
				   {key: "maleVoters", label: "Male Voters", sortable: true},
				   {key: "femaleVoters", label: "Female Voters" ,sortable: true},
				   {key: "totalVoters", label: "Caste Voters" ,sortable: true},
				   {key: "groupWiseTotalVoters", label: "Total Voters" ,sortable: true},
				   {key: "castePercentage", label: "Caste Percentage" ,sortable: true},
				   
			];
			
			var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 20
			    }) 
				};

var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","castName","casteCategoryName","maleVoters","femaleVoters","totalVoters","groupWiseTotalVoters","castePercentage"]
					};
		var familesDataSource = new YAHOO.widget.DataTable("localCastStatsTabContent_subbody1", votersDetails,myDataSource, myConfigs);

	//buildCustomGroupWiseCasteVotersGraph(results,jsObj);
}

var castTemp = new Array();


function buildCustomGroupWiseCasteVotersGraph(results,jsObj)
{
   if(results == null || results.length == 0)
	return;


   
   $("#casteSelectDiv").html('');
   $("#casteSelectDiv").addClass('casteSelectDivCls');
   var castIdsArray = new Array();
   var castesSortedArray = [];

	for(var i in results)
	{
	  if(castIdsArray.indexOf(results[i].casteId) == -1)
	  {
        castIdsArray.push(results[i].casteId);
		var obj = {
				   caste:results[i].castName,
			       id:results[i].casteId
			      }
		castesSortedArray.push(obj);
		castTemp.push(results[i].castName);

	  }
	}

	

   castesSortedArray.sort(sort_by('caste', true, function(a){return a.toUpperCase()}));
   var str = '';
   str +='<div>';
   str +='<div id="casteErrorMsgDiv"></div>';
   str +='<h4>Select Options To View Caste Wise Voter Analysis</h4>';
   str +='<input type="radio" checked="true" name="castTypeRadio" id="castSelectRadio" value="All" onclick="buildCastInfoBasedOnOptions(\'all\')" /><b>All</b>';

   str +='<input type="radio" id="castAllRadio" name="castTypeRadio" value="castWise" onclick="buildCastInfoBasedOnOptions(\'selected\')" /><b>Caste Wise</b>';
   
   str += '<div id="casteHideAndShowOptionsDiv" style="display:none;">';
   str += '<select class="selectBoxStyle" id="castSelectdId" multiple="multiple">';
   
   for(var k in castesSortedArray)
   	str +='<option value='+castesSortedArray[k].id+'>'+castesSortedArray[k].caste+'</option>';
   
   str +='</select>';

   str +='<input type="button" style="margin-left: 24px;font-weight:bold;margin-top: -33px;" onclick="buildCastWiseChart(tempObj)" value="View" class="btn btn-info">';
   str +='<p id="notePara">Press Ctrl Key To Select Multiple Castes</p>';

   str +='</div>';
   str +='</div>';
   $("#casteSelectDiv").html(str);
}

var sort_by = function(field, reverse, primer){

   var key = function (x) {return primer ? primer(x[field]) : x[field]};

   return function (a,b) {
       var A = key(a), B = key(b);
       return ((A < B) ? -1 :
               (A > B) ? +1 : 0) * [-1,1][+!!reverse];                  
   }
}

function buildCastInfoBasedOnOptions(option)
{
	
  if(option == "all")
  {
	buildCustomGroupWiseCastResultsGraph(null,null);
    $("#casteHideAndShowOptionsDiv").css("display","none");
  }
  else
  {
   $("#casteHideAndShowOptionsDiv").css("display","block");
  }
}

function buildCastWiseChart(tempObj)
{
 buildCustomGroupWiseCastResultsGraph(null,null);
}

function sort_unique(a) {
     var temp = {};
    for (var i = 0; i < a.length; i++)
        temp[a[i]] = true;
    var r = [];
    for (var k in temp)
        r.push(k);
    return r;
}

function buildCustomGroupWiseCastResultsGraph(selectedCast,percentage)
{
  if(percentage==null)
		percentage=1;
	
	var myChart1 = new Array();
	var castMain = null;

	if(selectedCast == null)
	{
	  castMain = sort_unique(castTemp);
	  var radios = document.getElementsByName("castTypeRadio");
	  if(radios != null && radios.length > 0)
	  {
		var selectValue;
		for(var r=0;r<radios.length;r++)
		{
		  if(radios[r].checked)
			selectValue = radios[r].value;
		  if(selectValue != 'All')
		  {
			 castMain=[];
			 $("#castSelectdId option:selected").each(function()
			 {
				castMain.push($(this).text());
			});
		  }

		}
	  }
     else
		castMain = sort_unique(castTemp);

	 

	

	}
}


getCasteWiseCustomGroupVoters();
</script>
</body>
</html>