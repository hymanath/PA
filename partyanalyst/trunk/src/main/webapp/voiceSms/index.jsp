	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
		<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Welcome JSP-Applet Page</title>

</head>
<body>
<!--<jsp:plugin type="applet" code="AudioApplet.class" 
  width="400" height="400">
  <jsp:fallback>
   <p>Unable to load applet</p>
   </jsp:fallback>
</jsp:plugin>-->
<div style="text-align:center;margin-top:125px;">
<applet
  code="AudioApplet.class"
  archive="voice.jar"
  permissions="all-permissions"
  width="550" height="50">
</applet>
</div>

<div style="text-align:center;color:green;"><s:property value="status"/></div>

<form action="uploadAudioFile.action" method="POST" enctype="multipart/form-data">


<div style="width:328px;margin:5px;background-color:#e5e5e5;padding:5px;margin:17px 0px 0px 303px;border-radius:5px;">
 
   <div style="margin:15px;">Enter File Name : <input type="text" name="voiceFileName"/></div>
   <div style="margin:15px;">Enter File Description: <textarea  name="voiceDescription" style="width:300px;"></textarea></div>

   <div style="margin:15px;"><input type="file" name="recordedVoice"  style="margin-top:8px;"/></div>

   <div style="margin:0px 0px 10px 193px;"><input type="submit" value="Upload To Server"/></div>

</div>
 
 <form>

</body>
</html>