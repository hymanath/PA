<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link type="text/css" href="styles/bootstrapInHome/bootstrap.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" media="screen">
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
	 <script src="js/cufon/cufon.js" type="text/javascript"></script>
     <script src="js/cufon/Eenadu_400.font.js" type="text/javascript"></script>
<style type="text/css">
	.well {
	  margin-bottom: 7px;
	  padding-top: 10px;
	  padding-bottom: 10px;
	}
	
</style>
</head>
<body>
  <div class="container">
     <div id="newsDiv"><div id="ajaximg"><img id="" width="18" height="11" style="width: 150px; height: 15px;margin-left:400px;margin-top:100px;" src="images/icons/goldAjaxLoad.gif"></div></div>
  </div>
  
  <script type="text/javascript">
  
  function callAjax()
	{
		 var myResults;

		 var callback = {			
	               success : function( o ) {
						try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
                                buildData(myResults);
															
								
							}catch (e) {
								if(document.getElementById('ajaximg') != null)
									document.getElementById('ajaximg').innerHTML ="";
							}  
	               },
	               scope : this,
	               failure : function( o ) {
	                			//alert( "Failed to load result" + o.status + " " + o.statusText);
	                         }
	               };

	YAHOO.util.Connect.asyncRequest('POST', "createActivitiesReportAction1.action?key=${key}", callback);
}
 function buildData(myResults){
  var str ="";
     if(myResults != null && myResults.list != null && myResults.list.length > 0){
	  str+="<div class='container'><div class='row-fluid'><div class='span12 content_widget'>	";
	    for(var i in myResults.list){//category list
			str+="<div class='text-center widget'><h4>"+myResults.list[i].name+"</h4></div>";
			for(var j in myResults.list[i].list){//district list
				 str+='<div class="row-fluid ">';
				 str+='			<div class="span12 widget ">';
				 str+='				<div class="boxHeading">';
				 str+='				<h4>'+myResults.list[i].list[j].name+' District</h4>	</div>';
				 for(var k in  myResults.list[i].list[j].list){	//constituency list			
					str+='				<div class="accordion-inner">';
					str+='					<a class="btn btn-small">'+myResults.list[i].list[j].list[k].name+'</a>';
					str+='					<div class="m_top10">';
					for(var l in  myResults.list[i].list[j].list[k].list){//news list
					  if(myResults.list[i].list[j].list[k].list[l].font == null){
					    str+='			<p>'+myResults.list[i].list[j].list[k].list[l].date+' '+myResults.list[i].list[j].list[k].list[l].description+'</p>';
					  }else{
					    str+='			<p>'+myResults.list[i].list[j].list[k].list[l].date+'&nbsp;<enadu>'+myResults.list[i].list[j].list[k].list[l].description+'</enadu></p>';
					  }
					}
					str+='					</div>';
					str+='				</div>';
                }					
				str+='			</div>	';					
				str+='		</div>';
		    }
		}
	  str+="</div></div></div>";
	  document.getElementById("newsDiv").innerHTML =str;
	  Cufon.set('fontSize', '28px').replace('enadu');
   }
 }
  callAjax();
  </script>
</body>
</html>