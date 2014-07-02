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
	 <script src="js/cufon/cufon.js" type="text/javascript"></script>
     <script src="js/cufon/Eenadu_400.font.js" type="text/javascript"></script>
<style type="text/css">
	.well {
	  margin-bottom: 7px;
	  padding-top: 10px;
	  padding-bottom: 10px;
	}
@font-face{ font-family: 'eFont'; src: url('fonts/eenadu_fonts/eenadu.eot');}
@font-face {
    font-family: "eFont";
    font-style: normal;
    font-weight: normal;
    src: local("?"), url("fonts/eenadu_fonts/eenadu.woff") format("woff"), url("fonts/eenadu_fonts/eenadu.ttf") format("truetype"), url("fonts/eenadu_fonts/eenadu.svg") format("svg");
}
 
.enadu
{
font-family: eFont;
font-size:35px;
line-height: 1;
}
.fontclass{
  font-size: 24px;
  line-height: 1.5;
}
</style>
</head>
<body>
  <div class="container">
     <div id="newsDiv"><div id="ajaximg"><img id="" width="18" height="11" style="width: 150px; height: 15px;margin-left:400px;margin-top:100px;" src="images/icons/goldAjaxLoad.gif"></div></div>
  </div>
  
  <script type="text/javascript">
  var pageVal = '${pageVal}';
  var page = true;
  if(pageVal == '1'){
	  page = false;
  }
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
			str+="<center><div class='text-center widget'><h4>"+myResults.list[i].name+"</h4></div></center>";
			for(var j in myResults.list[i].list){//district list
				 str+='<div class="row-fluid ">';
				 str+='			<div class="span12 widget ">';
				 str+='				<center><div class="boxHeading">';
				 str+='				<h4 style=" font-size:16px;">'+myResults.list[i].list[j].name+' District</h4>	</div></center>';
				 for(var k in  myResults.list[i].list[j].list){	//constituency list			
					str+='				<div class="accordion-inner">';
					str+='					<center><a class="btn btn-small" style=" font-size:15px;"><b>'+myResults.list[i].list[j].list[k].name+'</b></a></center>';
					str+='					<div class="m_top10">';
					for(var l in  myResults.list[i].list[j].list[k].list){//news list
					 if(myResults.list[i].list[j].list[k].list[l].titleFont == null){
					   str+='<hgroup><p class="fontclass">'+myResults.list[i].list[j].list[k].list[l].title+'<span><small style="color:#333333;">  ('+myResults.list[i].list[j].list[k].list[l].paper+')<small></small></small></span></p><h5 style="border-bottom:1px solid #333"> </h5></hgroup>';
					  }else{
					      if(page){ 
					        str+='			<hgroup><p class=" fontclass"><enadu1>'+myResults.list[i].list[j].list[k].list[l].title+'</enadu1><span><small style="color:#333333;">  ('+myResults.list[i].list[j].list[k].list[l].paper+')<small></small></small></span></p><h5 style="border-bottom:1px solid #333"> </h5></hgroup>';
						 }else{
							 str+='			<hgroup><p class=" fontclass"><span class="enadu">'+myResults.list[i].list[j].list[k].list[l].title+'</span><span><small style="color:#333333;">  ('+myResults.list[i].list[j].list[k].list[l].paper+')<small></small></small></span></p><h5 style="border-bottom:1px solid #333"> </h5></hgroup>';
						 }
					  }
					  if(myResults.list[i].list[j].list[k].list[l].font == null){
					    str+='			<p class="fontclass" style="padding-bottom: 15px;">'+myResults.list[i].list[j].list[k].list[l].date+'('+myResults.list[i].list[j].list[k].list[l].id+')  '+myResults.list[i].list[j].list[k].list[l].description+'</p>';
					  }else{
						 if(page){ 
					        str+='			<p class="fontclass" style="padding-bottom: 15px;">'+myResults.list[i].list[j].list[k].list[l].date+'('+myResults.list[i].list[j].list[k].list[l].id+')&nbsp;<enadu>'+myResults.list[i].list[j].list[k].list[l].description+'</enadu></p>';
						 }else{
							 str+='			<p class="fontclass" style="padding-bottom: 15px;">'+myResults.list[i].list[j].list[k].list[l].date+'('+myResults.list[i].list[j].list[k].list[l].id+')&nbsp;<span class="enadu">'+myResults.list[i].list[j].list[k].list[l].description+'</span></p>';
						 }
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
	  if(page){ 
	   Cufon.set('fontSize', '35px').replace('enadu');
	   Cufon.set('fontSize', '35px').replace('enadu1');
	  }
   }
 }
  callAjax();
  </script>
</body>
</html>