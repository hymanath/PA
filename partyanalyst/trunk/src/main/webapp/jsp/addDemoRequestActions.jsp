<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
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
	<script type="text/javascript" src="js/voterAnalysis/voterAnalysis1.js"></script>
	<script type="text/javascript" src="js/voterAnalysis/showGallaries1.js"></script>
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
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	
<title>Party Analyst</title>
<script type="text/javascript">
var demoRequestId = '${demoRequestId}';
</script>

<style type="text/css">
#demoRequestMainDiv{float: none;
    margin: 39px auto;margin-top: 25px;
    width: 500px;}
#demoRequestInnerDiv{ border: 2px solid #D3D3D3;
    border-radius: 7px 7px 7px 7px;
    padding: 18px 25px 41px 70px;}

#errorMsgDiv{   margin-bottom: 11px;}
#actiontype{margin-left: 27px;
    width: 215px;}
#content{margin-left: 50px;}
#response{ margin-left: 40px;}
#createDemoActions{margin-left: 140px;
    margin-top: 9px;}

.headingCls{ text-align: center;margin-bottom: 17px;}
.headingSpan{ color: #fff; font-weight: bold; padding: 2px 30px 3px; border-radius: 3px 3px 3px 3px; font-size: 18px; background: none repeat scroll 0 0 #06ABEA;}
#ajaxImg{margin-left: 9px;
    margin-top: 8px;}
</style>
</head>
<body>
<div id="demoRequestMainDiv">
<div class="headingCls">
  <span class="headingSpan">Add New Action</span>
</div>
<div id="demoRequestInnerDiv">
<div id="errorMsgDiv"></div>
 <div>
  <p>Action Type: 
  
	 <s:select theme="simple" style="margin-left:27px;" cssClass="selectWidth"  name="actiontypeList" id="actiontype" list="selectOptionVOList" listKey="name" listValue="name" /> 
	 </p>
  <p>Content: <textarea rows="4" cols="50" id="content"></textarea> </p>
  <p>Response: <textarea rows="4" cols="50" id="response"></textarea></p>
  <input type="button" value="Create" id="createDemoActions" class="btn btn-info" onclick="createDemoActions()"/><img style="display:none;" src="images/icons/search.gif" id="ajaxImg"/>

 </div>
</div>

</div>

<script type="text/javascript">
function createDemoActions()
{
	$("#errorMsgDiv").html('');
	var actiontype = $("#actiontype").val();
	var content = $("#content").val();
	var response = $("#response").val();
    $("#ajaxImg").css("display","inline-block");
	var jsObj=
	{
		
	  type:actiontype,
	  content:content,
	  response:response,
	  demoRequestId:demoRequestId,
	  task:"saveDemoRequestActionsData"				
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveDemoRequestActionsData.action?"+rparam;						
	callAjax(jsObj,url);

}


function callAjax(jsObj,url)
{
	 var myResults;

	 var callback = {			
	               success : function( o ) {
					try {												
						myResults = YAHOO.lang.JSON.parse(o.responseText);					
						if(jsObj.task == "saveDemoRequestActionsData")
						  showStatus(myResults);
								
						
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

function showStatus(results)
{
  $("#errorMsgDiv").html('');
   $("#ajaxImg").css("display","none");
  if(results.resultCode == 0)
  {
	$("#content").val('');
	$("#response").val('');
    $("#errorMsgDiv").html('Demo Request Action Added Successfully.').css("color","green");
    window.opener.parentWindowRefresh();
	return;
  }
  else
  {
	$("#errorMsgDiv").html('Error Occured! Try Again.').css("color","red");
	return; 
  }
}

</script>
</body>
</html>