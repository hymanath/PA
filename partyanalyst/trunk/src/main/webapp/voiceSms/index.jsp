	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
		<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Record And Upload Audio File</title>

</head>
<body>


<div class='alert alert-info' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;margin-bottom:-45px;margin-top:12px;'><h4>UPLOAD AUDIO FILES TO SERVER</h4></div>


<div>
<div style="width:583px;margin:90px 0 0 205px;" class="breadcrumb">
<h5>Instructions To Upload Audio</h5>
<label> .You Can Upload Existing Audio Files Or You Can Record Now.(<a href="javascript:{showRecordDiv();}">Click here To Record Now</a>)</label>
<label> .Upload Only .wav , .mp3 Files Only.</label>
<label> .You Can Not Upload Promotional Audios.</label>
<label> .File Name Must Not Include Any Special Characters ,Spaces Except Underscore.</label>
</div>

<div id="recordingDiv" style="margin-top:15px;display:none;">

<div style="width:583px;margin:10px 0 0 205px;" class="breadcrumb">
<h5>Instructions To Record Voice</h5>
<label> .Please Install The Java Latest Version To Record Your Voice.</label>

</div>
</div>
<div style="text-align:center;display:none;margin:5px;" id="recordingMenuDiv">
<applet
  code="AudioApplet.class"
  archive="voice.jar"
  permissions="all-permissions"
  width="550" height="50">
</applet>
</div>
</div> 

<div style="background-color:#fff;padding:5px;">
<div style="text-align:center;color:green;"><s:property value="status"/></div>

<form action="uploadAudioFile.action" method="POST" enctype="multipart/form-data">

<div style="width:439px;margin:0 0 0 227px;padding:50px;border:2px solid #e5e5e5">
	<div class="alert alert-info" style=";">
	 <h4 style="text-align:center;">Upload Audio</h4>
	   <div style="margin:15px;">Enter File Name : <input type="text" name="voiceFileName"/></div>
	   <div style="margin:15px;">Enter File Description: <textarea  name="voiceDescription" style="width:300px;background-color:#fff;"></textarea></div>

	   <div style="margin:15px;"><input type="file" name="recordedVoice"  style="margin-top:8px;"/></div>

	   <div style="margin:0px 0px 10px 193px;"><input class="btn" type="submit" value="Upload To Server"/></div>

	</div>
</div>

 <form>

<script>
function showRecordDiv()
{
 $('#recordingDiv , #recordingMenuDiv').slideToggle('slow');

}
</script>
</body>
</html>