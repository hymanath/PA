<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>STRATEGY REPORTS</title>
<script type="text/javascript">
 function getStrategyReport(){
   var id = $("#constituencyId").val();
     $("#errorMsg").html("");
    if(id == 0){
	  return;
	}
	$("#ajaxImg").show();
    $.ajax({
            url: "getstrategyReport.action?constituencyId="+id
    }).done(function(result) {
	      $("#ajaxImg").hide();
		 if(result == "no"){
		   $("#errorMsg").html("<div style='color:red;font-weight:bold;'>Strategy Report Is Not Available For This Constituency.</div>");
		 }else{
		    window.open(result);
		 }
     });
    }
</script>
</head>
<body>
<center> 
 <div id="mainDiv" style="min-height:350px;"> 
  
  <s:if test="{constituenciesList != null && constituenciesList.size >0}">
    <div id="selectDiv" style="margin-top: 50px;"><span style="font-weight:bold;font-size:16px;">Select Constituency : </span> <s:select theme="simple" headerKey="0" headerValue="Select Constituency" name="constituencyId" id="constituencyId" onchange="getStrategyReport();" list="constituenciesList" onChange="getAllCriticalPanchayats();" listKey="id" listValue="name" /></div> 
  </s:if>
  <div id="ajaxImg" style="margin-top:20px;display:none;"><img src="images/icons/goldAjaxLoad.gif"></div>
  <div id="errorMsg" style="margin-top:20px;"></div>
  </div>
</center>  
</body>
</html>