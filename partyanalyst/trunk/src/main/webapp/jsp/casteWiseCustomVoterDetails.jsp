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
</style>

<script type="text/javascript">

var customVoterGroupId = "${customVoterGroupId}";
var casteStateId = "${casteStateId}";
var casteId = "${casteId}";
var casteName = "${casteName}";
var casteCategoryName = "${casteCategoryName}";
var groupName = "${groupName}";

</script>
</head>
<body>
<div id="customVotersMainDiv">
<div id="ajaxImg" style="display:none;"><img src="./images/icons/goldAjaxLoad.gif"></div>
<div id ="localCastStatsVotersTitle" ></div>
<div id="localCastStatsTabContent_subbody1" class="yui-skin-sam yui-dt-sortable"></div>
</div>

<script type="text/javascript">

function getCasteWiseCustomVoters()
{
  $("#ajaxImg").css("display","block");
  var jsObj=
  {
	customVoterGroupId:1,
	casteStateId:casteStateId,
	casteId:casteId,
	task:"getCasteWiseCustomVoters"
  };

  var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
  var url = "getCasteWiseCustomVotersAction.action?"+rparam+"&save=";	
  callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
	var myResults;

	var callback = {			
 	 success : function( o ) {
	  try {												
		myResults = YAHOO.lang.JSON.parse(o.responseText);
		
			if(jsObj.task == "getCasteWiseCustomVoters")
			  buildCasteWiseCustomVoters(myResults,jsObj);

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


function buildCasteWiseCustomVoters(results,jsObj)
{

 $("#localCastStatsVotersTitle").html('');
 $("#localCastStatsTabContent_subbody1").html('');
 $("#ajaxImg").css("display","none");
 $("#localCastStatsVotersTitle").addClass("localCastStatsVotersTitle").html(" "+casteName+"("+casteCategoryName+") Caste voters Details in "+groupName+"");
 var votersDetails = [ 
	               {key: "name", label: "Name", sortable: true},
				   {key: "gender", label: "Gender", sortable: true},
				   {key: "age", label: "Age" ,sortable: true},
				   {key: "houseNo", label: "House No" ,sortable: true},
				   {key: "gaurdian", label: "Guardian Name" ,sortable: true},
				   {key: "relationship", label: "Relationship" ,sortable: true},
				   {key: "voterIdCardNo", label: "Voter Id" ,sortable: true},
				   {key: "mobileNo", label: "MobileNo" ,sortable: true}
				 ];

var myConfigs = { 
			    paginator : new YAHOO.widget.Paginator({ 
		        rowsPerPage    : 20
			    }) 
				};

var myDataSource = new YAHOO.util.DataSource(results);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : ["name","gender","age","houseNo","gaurdian","relationship","voterIdCardNo","mobileNo"]
					};
		var familesDataSource = new YAHOO.widget.DataTable("localCastStatsTabContent_subbody1", votersDetails,myDataSource, myConfigs);


}
 	
getCasteWiseCustomVoters();
</script>
</body>
</html>