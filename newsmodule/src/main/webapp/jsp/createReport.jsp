<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
font-size:28px;
}
.fontclass{
  font-size:18px;
}
.well {
    background-color: #FFFFFF;
	}
</style>
</head>
<body>
<c:if test="${forPdf}">
   <div class="container" id="showhidemain" style="display:none;">
     <div id="newsDiv">
	    <s:if test="news.name == 'Invalid User'">
           <div style='padding-left:263px;padding-top: 207px;'><font style='color:red;'><b>You Didn't Have Access To View This Report</b></font></div> 
        </s:if>
        <s:elseif test="news.name == 'Invalid Key'">
           <div style='padding-left:166px;padding-top: 207px;'><font style='color:red;'><b>Generated Url Expired, Please Regenerate A New Url To Access This Report</b></font></div>
        </s:elseif>
        <s:elseif test="news.name == 'All Newses Deleted'">
           <div style='padding-left:166px;padding-top: 207px;'><font style='color:red;'><b>All Newses In This Report Were Deleted By Admin</b></font></div>
        </s:elseif>
        <s:else>
		   <h5 style="text-align:center;font-weight:bold;font-size:16px;border-bottom:1px solid #333"><div>${news.gallaryName}</div>
		   <div>${news.gallaryDescription}</div><br/></h5>
		   <s:if test="news.mainArticalsList != null && news.mainArticalsList.size() > 0">
		       <div class="btn btn-block " style="margin-bottom: 8px;"><b>ANDHRA PRADESH STATE</b></div>
			   <s:iterator value="news.mainArticalsList" var="stateNews" status="stateIndex">							 
				   <div class="row-fluid">
				     <div class="span12 well">
					   <div class="media">
					     <div class="media-body">
						   <hgroup>
							 <s:if test="#stateNews.eenadu">
								<stateenadu<s:property value="%{#stateIndex.index}"/>><s:property value="title"/></stateenadu<s:property value="%{#stateIndex.index}"/>>
							 </s:if>
							 <s:else>
								<h4 class="media-heading fontclass"><s:property value="title"/></h4>
							 </s:else>
							 
							 <s:if test="#stateNews.keyWordsList != null">
							   <h5 style="border-bottom:1px solid #333"> <i><s:property value="scope"/>:&nbsp;&nbsp; <s:property value="locationName"/></i><span class="pull-right"><s:text name="%{names}"/></span></h5>
							 </s:if>
							 <s:else>
							   <h5 style="border-bottom:1px solid #333"> <i><s:property value="locationName"/></i></h5>
							 </s:else>
							</hgroup>
							<div><s:property value="fileDate"/></div>
							<s:if test="#stateNews.descEenadu">
							   <stateenadu<s:property value="%{#stateIndex.index}"/>><s:property value="description"/></stateenadu<s:property value="%{#stateIndex.index}"/>>
							</s:if>
							<s:else>
							   <p class="fontclass"><s:property value="description"/></p>
							</s:else>
							<s:if test="#stateNews.candidateName != null && #stateNews.candidateName.trim().length > 0">
							  <span class=""><span class="" >Candidate Name(s):</span>&nbsp;&nbsp;<s:property value="candidateName"/></span>
							</s:if>
						 </div>
					   </div>
				    </div>
			     </div>
				   <script type="text/javascript">
		              Cufon.set('fontSize', '28px').replace('stateenadu<s:property value="%{#stateIndex.index}"/>');
		           </script>
		     </s:iterator>
	       </s:if>
		   <s:if test="news.fileVOList != null && news.fileVOList.size() > 0">
		     <s:iterator value="news.fileVOList" var="districtMain" status="mainIndex">							 
		       <div class="btn btn-block" style="margin-bottom: 8px;"><b><s:property value="locationName"/>&nbsp;&nbsp;District</b></div>
			   <s:iterator value="#districtMain.fileVOList" var="districtNews" status="distIndex">							 
				   <div class="row-fluid">
				     <div class="span12 well">
					   <div class="media">
					     <div class="media-body">
						   <hgroup>
							 <s:if test="#districtNews.eenadu">
								<a<s:property value="%{#mainIndex.index}"/>enadu<s:property value="%{#distIndex.index}"/>><s:property value="title"/></a<s:property value="%{#mainIndex.index}"/>enadu<s:property value="%{#distIndex.index}"/>>
							 </s:if>
							 <s:else>
								<h4 class="media-heading fontclass"><s:property value="title"/></h4>
							 </s:else>
							 <s:if test="#districtNews.scope == 'DISTRICT' || #districtNews.scope == 'CONSTITUENCY' ">
							   <h5 style="border-bottom:1px solid #333"> <i><s:property value="scope"/>:&nbsp;&nbsp; <s:property value="locationName"/></i><span class="pull-right"><s:text name="%{names}"/></span></h5>
							 </s:if>
							 <s:else>
							   <h5 style="border-bottom:1px solid #333"> <i> <s:property value="locationName"/></i><span class="pull-right"><s:text name="%{names}"/></span></h5>
							 </s:else>
							</hgroup>
							<div><s:property value="fileDate"/></div>
							<s:if test="#districtNews.descEenadu">
							   <a<s:property value="%{#mainIndex.index}"/>enadu<s:property value="%{#distIndex.index}"/>><s:property value="description"/></a<s:property value="%{#mainIndex.index}"/>enadu<s:property value="%{#distIndex.index}"/>>
							</s:if>
							<s:else>
							   <p class="fontclass"><s:property value="description"/></p>
							</s:else>
							<s:if test="#districtNews.candidateName != null && #districtNews.candidateName.trim().length > 0">
							  <span class=""><span class="" >Candidate Name(s):</span>&nbsp;&nbsp;<s:property value="candidateName"/></span>
							</s:if>
							
						 </div>
					   </div>
				    </div>
			     </div>
				 <script type="text/javascript">
		          Cufon.set('fontSize', '28px').replace('a<s:property value="%{#mainIndex.index}"/>enadu<s:property value="%{#distIndex.index}"/>');
	             </script>
		     </s:iterator>
			</s:iterator>
	       </s:if>
	      </s:else>
      </div>
     </div>
</c:if>
 <c:if test="${!forPdf}">
   <div class="container">
     <div id="newsDiv"><div id="ajaximg"><img id="" width="18" height="11" style="width: 150px; height: 15px;margin-left:400px;margin-top:100px;" src="images/icons/goldAjaxLoad.gif"></div></div>
  </div>
 </c:if>
  <script type="text/javascript">
  function callAjax()
	{
		 var myResults;

		 var callback = {			
	               success : function( o ) {
						try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								<c:if test="${forPdf}">
								  document.getElementById("showhidemain").style.display="block";
								</c:if>
								<c:if test="${!forPdf}">
								  buildData(myResults);
								</c:if>							
								
							}catch (e) {
								if(document.getElementById('ajaximg') != null)
								 document.getElementById('ajaximg').innerHTML ="";
								if(document.getElementById("showhidemain") != null)
								 document.getElementById("showhidemain").style.display="block";
							}  
	               },
	               scope : this,
	               failure : function( o ) {
	                			//alert( "Failed to load result" + o.status + " " + o.statusText);
	                         }
	               };
   <c:if test="${!forPdf}">
	YAHOO.util.Connect.asyncRequest('POST', "createReportAction1.action?reportId=${reportId}&key=${key}", callback);
   </c:if>
   <c:if test="${forPdf}">
	YAHOO.util.Connect.asyncRequest('POST', "delayAction.action?delaySeconds=30", callback);
   </c:if>
}
<c:if test="${!forPdf}">
 function buildData(myResults){
  var str ="";
  if(myResults.name == "Invalid User"){
	  str+="<div style='padding-left:263px;padding-top: 207px;'><font style='color:red;'><b>You Didn't Have Access To View This Report</b></font></div>"; 
  }else if(myResults.name == "Invalid Key"){
	  str+="<div style='padding-left:166px;padding-top: 207px;'><font style='color:red;'><b>Generated Url Expired, Please Regenerate A New Url To Access This Report</b></font></div>"; 
  }
  else if(myResults.name == "All Newses Deleted"){
	  str+="<div style='padding-left:166px;padding-top: 207px;'><font style='color:red;'><b>All Newses In This Report Were Deleted</b></font></div>"; 
  }
  else{
	  str+='<h5 style="text-align:center;font-weight:bold;font-size:16px;border-bottom:1px solid #333"><div>'+myResults.gallaryName+'</div>';
		  str+='<div>'+myResults.gallaryDescription+'</div><br/></h5>';
	  if(myResults.mainArticalsList != null  && myResults.mainArticalsList.length > 0){
		str+='<div class="btn btn-block" style="margin-bottom: 8px;"><b>ANDHRA PRADESH STATE</b></div>';
		 for( var i in myResults.mainArticalsList){
			  str+='<div class="row-fluid"><div class="span12 well"><div class="media"><div class="media-body"><hgroup>';
			  if(myResults.mainArticalsList[i].eenadu){
				str+='<p class="enadu">'+myResults.mainArticalsList[i].title+'</p>';
			  }else{
				str+='<h4 class="media-heading fontclass">'+myResults.mainArticalsList[i].title+'</h4>';
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
		str1+='<span class="btn btn-small"><span class="">'+sourceVal1+ '</span>&nbsp;'+sourceVal3+ '</span>&nbsp;&nbsp;';
		else
		str1+='<span class="btn btn-small"><span class="">'+sourceVal+ '</span>&nbsp;</span>&nbsp;&nbsp;';
	}
			  
				if(myResults.mainArticalsList[i].keyWordsList != null)
				str+='<h5 style="border-bottom:1px solid #333"><i>'+myResults.mainArticalsList[i].scope+":    "+myResults.mainArticalsList[i].locationName+'</i><span class="pull-right">'+str1+'<span></h5>';
				else{
					str+='<h5 style="border-bottom:1px solid #333"> <i>'+myResults.mainArticalsList[i].locationName+'</i></h5>';
				}
				str+='</hgroup>';
              str+='<div>'+myResults.mainArticalsList[i].fileDate+'</div>';
			  if(myResults.mainArticalsList[i].descEenadu){
				 str+='<p class="enadu">'+myResults.mainArticalsList[i].description+'</p>';	
			  }else{
					str+='<p class="fontclass">'+myResults.mainArticalsList[i].description+'</p>';
			  }
			  if(myResults.mainArticalsList[i].candidateName!=null && myResults.mainArticalsList[i].candidateName.trim().length > 0)
			  str+='<span class=""><span class="" >Candidate Name(s):</span>&nbsp;&nbsp;'+myResults.mainArticalsList[i].candidateName+'</span>';
			  str+='</div></div></div></div>';
		 }
		
	}
	if(myResults.fileVOList != null  && myResults.fileVOList.length > 0){
	   for(var i in myResults.fileVOList){
		str+='<div class="btn btn-block" style="margin-bottom: 8px;"><b>'+myResults.fileVOList[i].locationName+' District</b></div>';
		 for(var j in myResults.fileVOList[i].fileVOList){
			  str+='<div class="row-fluid"><div class="span12 well"><div class="media"><div class="media-body"><hgroup>';
			  if(myResults.fileVOList[i].fileVOList[j].eenadu){
				str+='<p class="enadu">'+myResults.fileVOList[i].fileVOList[j].title+'</p>';
			  }else{
				str+='<h4 class="media-heading fontclass">'+myResults.fileVOList[i].fileVOList[j].title+'</h4>';
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
		str1+='<span class="btn btn-small"><span class="">'+sourceVal1+ '</span>&nbsp;'+sourceVal3+ '</span>&nbsp;&nbsp;';
		else
		str1+='<span class="btn btn-small"><span class="">'+sourceVal+ '</span>&nbsp;</span>&nbsp;&nbsp;';
	}
	if(myResults.fileVOList[i].fileVOList[j].scope == 'DISTRICT' || myResults.fileVOList[i].fileVOList[j].scope == 'CONSTITUENCY'){
		  str+='<h5 style="border-bottom:1px solid #333"> <i>'+myResults.fileVOList[i].fileVOList[j].scope+': '+myResults.fileVOList[i].fileVOList[j].locationName+'</i><span class="pull-right">'+str1+'</span></h5>';
			   str+='</hgroup>';		 
	}
	else{
			  str+='<h5 style="border-bottom:1px solid #333"> <i> '+myResults.fileVOList[i].fileVOList[j].locationName+'</i><span class="pull-right">'+str1+'</span></h5>';
			   str+='</hgroup>';
	}
	          str+='<div>'+myResults.fileVOList[i].fileVOList[j].fileDate+'</div>';
			  if(myResults.fileVOList[i].fileVOList[j].descEenadu){
				str+='<p class="enadu">'+myResults.fileVOList[i].fileVOList[j].description+'</p>';	
			  }else{
					str+='<p class="fontclass">'+myResults.fileVOList[i].fileVOList[j].description+'</p>';
			  }
			  if(myResults.fileVOList[i].fileVOList[j].candidateName!=null && myResults.fileVOList[i].fileVOList[j].candidateName.trim().length > 0)
			  str+='<span class=""><span class="" >Candidate Name(s):</span>&nbsp;&nbsp;'+myResults.fileVOList[i].fileVOList[j].candidateName+'</span>';
              
			  str+='</div></div></div></div>';
		 }
	   }
	  }
 }
	  document.getElementById("newsDiv").innerHTML =str;
	   //Cufon.set('fontSize', '28px').replace('enadu');
 }
 </c:if>
callAjax();
</script>
</body>
</html>