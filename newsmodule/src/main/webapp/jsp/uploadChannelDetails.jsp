<%@ page language="java" contentType="text/html; charset=utf-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <META http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> Telugudesam Party </title>

<!-- <script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>-->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
	<script type="text/javascript" src="js/jquery.google.api/jquery.min.js"></script>

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
<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/simplePagination/gallaryResponsePagination.js" ></script>
<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 
<!--  <script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script> -->
<script type="text/javascript" src="js/jquery.google.api/jquery.2.8.2.combo.js"></script>
 <!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> -->
 <link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui1.10.3.css"/>
<!-- <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->
 <script type="text/javascript" src="js/jquery.google.api/jquery-ui.js"></script>
<style>


#partyList{margin-left: 48px; width: 220px;}
#designationId{margin-left: 10px; width: 220px;}
</style>

<script>
function insertChannelDetails()
{
$('#errStatusDiv').html('');
 
  	var jsObj =
		{   
			channelName : $('#channelName').val(),
            
			task:"insertChannelDetails"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "insertChannelDetails.action?"+rparam;					
	callAjax(jsObj,url); 
 
}

function callAjax(jsObj,url)
{
	 var myResults;
	 var callback = {			
	success : function( o ) {
		try {												
				myResults = YAHOO.lang.JSON.parse(o.responseText);	
			
				if(jsObj.task == "insertChannelDetails")
				{
				
					
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
</script>

</head>
<body>


<div style="margin-left:295px;">
<div id="errStatusDiv" style="height: 30px;"></div>

  <div>Enter Channel Name :<input type="text"  id="channelName" /></div>
 
 <a class="btn btn-primary" style="margin-left:198px;" onclick="insertChannelDetails();">submit</a>
  <div id="successDiv"></div>
 </div>


</body>
</html>