<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>STRATEGY REPORTS</title>
</head>
<body>
<center> 
 <div id="mainDiv"> 
  <s:if test="{constituenciesList != null && constituenciesList.size >0}">
    <div id="selectDiv"><span style="font-weight:bold;font-size:16px;">Select Constituency : </span> <s:select theme="simple"    name="constituencyId" id="constituencyId" list="constituenciesList" onChange="getAllCriticalPanchayats();" listKey="id" listValue="name" /></div> 
  </s:if>
  
  </div>
</center>  
</body>
</html>