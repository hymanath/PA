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
	 
<style type="text/css">
	@font-face{ font-family: 'eFont'; src: url('fonts/eenadu.eot');}
	@font-face {
	    font-family: "eFont";
	    font-style: normal;
	    font-weight: normal;
	    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
	}
	 .enadu
	{
	font-family: eFont;
	font-size:20px;
	}
	.well {
	  margin-bottom: 7px;
	  padding-top: 10px;
	  padding-bottom: 10px;
	}
	#newsDiv{
	  font-weight: bold;
	}
</style>
</head>
<body>
  <div class="container">
     <div id="newsDiv"></div>
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
						     console.log(e);
							}  
	               },
	               scope : this,
	               failure : function( o ) {
	                			//alert( "Failed to load result" + o.status + " " + o.statusText);
	                         }
	               };

	YAHOO.util.Connect.asyncRequest('POST', "createReportAction1.action?reportId=${reportId}", callback);
}
 function buildData(myResults){
  var str ="";	
  if(myResults.mainArticalsList != null  && myResults.mainArticalsList.length > 0){
		str+='<div class="btn btn-large btn-block btn-info" style="margin-bottom: 8px;"><b>ANDHRA PRADESH STATE</b></div>';
		 for( var i in myResults.mainArticalsList){
			  str+='<div class="row-fluid"><div class="span12 well"><div class="media"><div class="media-body"><hgroup>';
			  if(myResults.mainArticalsList[i].names != null){
				str+='<h4 class="media-heading enadu">'+myResults.mainArticalsList[i].title+'</h4>';
			  }else{
				str+='<h4 class="media-heading">'+myResults.mainArticalsList[i].title+'</h4>';
			  }												  
				str+='<h5 style="border-bottom:1px solid #333"><span class="label">'+myResults.mainArticalsList[i].source+'</span> <i class="pull-right">'+myResults.mainArticalsList[i].locationName+'</i></h5>';
				str+='</hgroup>';

			  if(myResults.mainArticalsList[i].names != null){
				 str+='<p class="enadu">'+myResults.mainArticalsList[i].description+'</p>';	
			  }else{
					str+='<p>'+myResults.mainArticalsList[i].description+'</p>';
			  }
			   
			  str+='</div></div></div></div>';
		 }
		
	}
	if(myResults.fileVOList != null  && myResults.fileVOList.length > 0){
	   for(var i in myResults.fileVOList){
		str+='<div class="btn btn-large btn-block btn-info" style="margin-bottom: 8px;"><b>'+myResults.fileVOList[i].locationName+' District</b></div>';
		 for(var j in myResults.fileVOList[i].fileVOList){
			  str+='<div class="row-fluid"><div class="span12 well"><div class="media"><div class="media-body"><hgroup>';
			  if(myResults.fileVOList[i].fileVOList[j].names != null){
				str+='<h4 class="media-heading enadu">'+myResults.fileVOList[i].fileVOList[j].title+'</h4>';
			  }else{
				str+='<h4 class="media-heading">'+myResults.fileVOList[i].fileVOList[j].title+'</h4>';
			  }												  
				str+='<h5 style="border-bottom:1px solid #333"><span class="label">'+myResults.fileVOList[i].fileVOList[j].source+'</span> <i class="pull-right">'+myResults.fileVOList[i].fileVOList[j].scope+': '+myResults.fileVOList[i].fileVOList[j].locationName+'</i></h5>';
				str+='</hgroup>';

			  if(myResults.fileVOList[i].fileVOList[j].names != null){
				str+='<p class="enadu">'+myResults.fileVOList[i].fileVOList[j].description+'</p>';	
			  }else{
					str+='<p>'+myResults.fileVOList[i].fileVOList[j].description+'</p>';
			  }
			  str+='</div></div></div></div>';
		 }
	   }
	  }
	  document.getElementById("newsDiv").innerHTML =str;
 }
  callAjax();
  </script>
</body>
</html>