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

	YAHOO.util.Connect.asyncRequest('POST', "createReportAction1.action?reportId=${reportId}&key=${key}", callback);
}
 function buildData(myResults){
  var str ="";
  if(myResults.name == "Invalid User"){
	  str+="<div style='padding-left:263px;padding-top: 207px;'><font style='color:red;'><b>You Didn't Have Access To View This Report</b></font></div>"; 
  }else if(myResults.name == "Invalid Key"){
	  str+="<div style='padding-left:166px;padding-top: 207px;'><font style='color:red;'><b>Generated Url Expired, Please Regenerate A New Url To Access This Report</b></font></div>"; 
  }
  else{
	  if(myResults.mainArticalsList != null  && myResults.mainArticalsList.length > 0){
		str+='<div class="btn btn-large btn-block btn-info" style="margin-bottom: 8px;"><b>ANDHRA PRADESH STATE</b></div>';
		 for( var i in myResults.mainArticalsList){
			  str+='<div class="row-fluid"><div class="span12 well"><div class="media"><div class="media-body"><hgroup>';
			  if(myResults.mainArticalsList[i].eenadu){
				str+='<h4 class="media-heading"><enadu>'+myResults.mainArticalsList[i].title+'</enadu></h4>';
			  }else{
				str+='<h4 class="media-heading">'+myResults.mainArticalsList[i].title+'</h4>';
			  }	
	
	var str1="";
	if(myResults.mainArticalsList[i].keyWordsList != null)
	for(var k=0;k<myResults.mainArticalsList[i].keyWordsList.length;k++){
	    var sourceVal = myResults.mainArticalsList[i].keyWordsList[k];
		var n=sourceVal.indexOf("(");
		var sourceVal1 = sourceVal.substring(0,n);
		var n1=sourceVal.lastIndexOf(")");
		var sourceVal3=sourceVal.substring(n,n1+1);
		sourceVal3 = sourceVal3.replace(')(',' & ');
		if(sourceVal1 != "" && sourceVal3 != "")
		str1+='<span class="btn btn-small"><span class="badge">'+sourceVal1+ '</span>&nbsp;'+sourceVal3+ '</span>&nbsp;&nbsp;';
		else
		str1+='<span class="btn btn-small"><span class="badge">'+sourceVal+ '</span>&nbsp;</span>&nbsp;&nbsp;';
	}
			  
				if(myResults.mainArticalsList[i].keyWordsList != null)
				str+='<h5 style="border-bottom:1px solid #333">'+str1+' <i class="pull-right">'+myResults.mainArticalsList[i].locationName+'</i></h5>';
				else{
					str+='<h5 style="border-bottom:1px solid #333"> <i class="pull-right">'+myResults.mainArticalsList[i].locationName+'</i></h5>';
				}
				str+='</hgroup>';
              str+='<span class="pull-right">'+myResults.mainArticalsList[i].fileDate+'</span>';
			  if(myResults.mainArticalsList[i].descEenadu){
				 str+='<p ><enadu>'+myResults.mainArticalsList[i].description+'</enadu></p>';	
			  }else{
					str+='<p>'+myResults.mainArticalsList[i].description+'</p>';
			  }
			  if(myResults.mainArticalsList[i].candidateName!=null && myResults.mainArticalsList[i].candidateName.trim().length > 0)
			  str+='<span class="label"><span class="badge badge-inverse" >Candidate Names:</span>&nbsp;&nbsp;'+myResults.mainArticalsList[i].candidateName+'</span>';
			  str+='</div></div></div></div>';
		 }
		
	}
	if(myResults.fileVOList != null  && myResults.fileVOList.length > 0){
	   for(var i in myResults.fileVOList){
		str+='<div class="btn btn-large btn-block btn-info" style="margin-bottom: 8px;"><b>'+myResults.fileVOList[i].locationName+' District</b></div>';
		 for(var j in myResults.fileVOList[i].fileVOList){
			  str+='<div class="row-fluid"><div class="span12 well"><div class="media"><div class="media-body"><hgroup>';
			  if(myResults.fileVOList[i].fileVOList[j].eenadu){
				str+='<h4 class="media-heading "><enadu>'+myResults.fileVOList[i].fileVOList[j].title+'</enadu></h4>';
			  }else{
				str+='<h4 class="media-heading">'+myResults.fileVOList[i].fileVOList[j].title+'</h4>';
			  }	
	var str1="";
	if(myResults.fileVOList[i].fileVOList[j].keyWordsList != null)
	for(var k=0;k<myResults.fileVOList[i].fileVOList[j].keyWordsList.length;k++){
	    var sourceVal = myResults.fileVOList[i].fileVOList[j].keyWordsList[k];
		var n=sourceVal.indexOf("(");
		var sourceVal1 = sourceVal.substring(0,n);
		var n1=sourceVal.lastIndexOf(")");
		var sourceVal3=sourceVal.substring(n,n1+1);
		sourceVal3 = sourceVal3.replace(')(',' & ');
		if(sourceVal1 != "" && sourceVal3 != "")
		str1+='<span class="btn btn-small"><span class="badge">'+sourceVal1+ '</span>&nbsp;'+sourceVal3+ '</span>&nbsp;&nbsp;';
		else
		str1+='<span class="btn btn-small"><span class="badge">'+sourceVal+ '</span>&nbsp;</span>&nbsp;&nbsp;';
	}
			  str+='<h5 style="border-bottom:1px solid #333">'+str1+' <i class="pull-right">'+myResults.fileVOList[i].fileVOList[j].scope+': '+myResults.fileVOList[i].fileVOList[j].locationName+'</i></h5>';
			   str+='</hgroup>';

			  if(myResults.fileVOList[i].fileVOList[j].descEenadu){
				str+='<p ><enadu>'+myResults.fileVOList[i].fileVOList[j].description+'</enadu></p>';	
			  }else{
					str+='<p>'+myResults.fileVOList[i].fileVOList[j].description+'</p>';
			  }
			  if(myResults.fileVOList[i].fileVOList[j].candidateName!=null && myResults.fileVOList[i].fileVOList[j].candidateName.trim().length > 0)
			  str+='<span class="label"><span class="badge badge-inverse" >Candidate Names:</span>&nbsp;&nbsp;'+myResults.fileVOList[i].fileVOList[j].candidateName+'</span>';
              str+='<span class="pull-right">'+myResults.fileVOList[i].fileVOList[j].fileDate+'</span>';
			  str+='</div></div></div></div>';
		 }
	   }
	  }
 }
	  document.getElementById("newsDiv").innerHTML =str;
	   Cufon.set('fontSize', '28px').replace('enadu');
 }
  callAjax();
  </script>
</body>
</html>