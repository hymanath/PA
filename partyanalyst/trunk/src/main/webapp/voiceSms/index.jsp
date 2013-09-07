	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<%@taglib prefix="s" uri="/struts-tags" %>
		<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@ page import="java.util.ResourceBundle;" %>
		<html xmlns="http://www.w3.org/1999/xhtml">


<head>
<title>Record And Upload Audio File</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<style>
ul li {list-style-type:square;}

	.requiredFont{
		color:red;
	}
	.main-title-sec {
  background-color:#06ABEA;
  background-position:initial initial;
  background-repeat:initial initial;
  border-bottom-left-radius:0;
  border-bottom-right-radius:0;
  border-top-left-radius:5px;
  border-top-right-radius:5px;
  display:table;
  height:36px;
  width:942px;
}

.main-mbg {
  color:#FFFFFF;
  display:table-cell;
  font-family:'Trebuchet MS', Arial, Helvetica, sans-serif;
  font-size:20px;
  font-style:normal;
  font-variant:normal;
  font-weight:bold;
  height:25px;
  line-height:normal;
  padding-left:10px;
  text-transform:uppercase;
 text-align:center;
  padding-top:9px;
  padding-left:31px;
}
.widget{
  background-attachment:scroll;
  background-color:#FAFAFA;
  background-image:none;
  background-position:0 0;
  background-repeat:repeat repeat;
  border-top-color:#000000;
  border-top-style:solid;
  border-top-width:5px;
  box-shadow:rgba(0, 0, 0, 0.298039) 0 0 1px;
  padding:8px;
}
	</style>
</head>
<body>


<div class="container" style="margin-top:12px;">
<div class="row" style="margin:0px;">

 <div class="main-title-sec">
    <div class="main-mbg">UPLOAD AUDIO FILES</div>
 </div>
  <div style="border:1px solid #06ABEA;margin-left:0px;" class="span12">

  
<div class="widget whitegloss">
<h5 style="margin:7px 0px 0px 199px;">Instructions To Upload Audio</h5>
 <ul style="margin:7px 0px 0px 199px;"> 
  <li>You can upload existing audio files from your computer or you can record a new voice message now and upload it to the server.(<a href="javascript:{showRecordDiv();}">Click here to record now</a>)</li>
  <li>Upload only .wav , .mp3 files only.</li>
  <li>You can not upload promotional audios.</li>
  <li>File name should not contain any special characters, spaces except underscore.</li>
 </ul>
</div>


<div id="recordingDiv" style="display:none;">



<div class="widget whitegloss">
<h5  style="margin:7px 0px 0px 199px;">Instructions To Record Voice</h5>

<ul  style="margin:7px 0px 0px 199px;">
 <li>Please install the java latest version to record your voice.<a href="http://java.com/en/download/index.jsp" target="_blank"><b>Install</b></a></li>
 <li>To record your voice message, click on 'Record' button and speak using microphone.</li>
 <li>Click on 'Stop' button when you are done with the voice message and then click on 'Save' button to save the file to your computer with .wav extension.</li>
</ul>


</div>
</div>

<!--<div class='alert alert-info' style='background:#f0f0f0;border-radius:0px;text-align:center;position:relative;margin-bottom:-45px;margin-top:12px;'><h4>UPLOAD AUDIO FILES TO SERVER</h4></div>-->



<div>
<div style="" class=""><!--
<h5>Instructions To Upload Audio</h5>
<label> .You can upload existing audio files or you can record now.(<a href="javascript:{showRecordDiv();}">Click here to record now</a>)</label>
<label> .Upload only .wav , .mp3 files only.</label>
<label> .You can not upload promotional audios.</label>
<label> .File name must not include any special characters ,spaces except underscore.</label>
</div>-->

<div id="recordingDiv1" style="margin-top:15px;display:none;">

<div>
<h5>Instructions To Record Voice</h5>
<label> .Please install the java latest version to record your voice.<a href="http://java.com/en/download/index.jsp" target="_blank"><b>Install</b></a></label>


</div>
</div>
<div style="text-align:center;display:none;margin:5px;" id="recordingMenuDiv">
<applet
  code="AudioApplet.class"
  archive="voice-sms.jar"
  permissions="all-permissions"
  width="550" height="50">
</applet>
</div>
</div> 

<div style="background-color:#fff;padding:5px;">
<div style="text-align:center;color:green;"><s:property value="status"/></div>

<form action="uploadAudioFile.action" method="POST" enctype="multipart/form-data" onSubmit="return validateForm();">

<div>

<div id="errorDiv" style="color:red;font-weight:bold;margin:5px 5px 5px 188px;"></div>
	<div class="widget">
	 <h4 style="text-align:center;">Upload Audio</h4>

	 <div style="width:auto;text-align:center;margin-left:-66px;">
	   <div style="margin:15px 0 0 50px;"><span>Enter File Name : <font style="color:red;">*</font></span><input type="text" name="voiceFileName" id="fileName" style="margin-left:44px;" onBlur="checkUploadFileExists();"/></div>
	    <div id="errormsg" style="margin-left: 143px;color:#ff0000"></div>

	    
	   <div style="margin: 15px 0px 0px 114px;"><span>Enter File Description:<font style="color:red;">*</font></span> <textarea  name="voiceDescription" style="width:300px;background-color:#fff;margin-left:38px;" id="description"></textarea></div>

	   <div style="margin:16px 0 0 129px;"><span>Select File:<font style="color:red;">*</font></span>
	   <input type="file"  name="recordedVoice"  style="margin-top: 8px; margin-left: 45px;" id="audioFile"/>
	   </div>

	   <div style="margin:0px 0px 0px 456px;"><input class="btn btn-info" type="submit" value="Upload To Server"  /></div>



  </div>

	</div>
</div>

 <form>
</div>
 </div>

<script>
var error = false;

function showRecordDiv()
{
 $('#recordingDiv , #recordingMenuDiv').slideToggle('slow');

}

function validateForm()
{	 
var str='';
var errorDiv = document.getElementById("errorDiv");
errorDiv.innerHTML="";
	
	
	

   if($('#fileName').val() == "")
   {
	   str+='File name is required</br>';
	   error = true;
    }
   if($('#fileName').val().length >10)
   {
	   str+='File name should not exceed 10 characters.</br>';
	   error = true;
  
   }
  

   
   if($('#description').val() == "")
   {
	   str+='File description is required</br>';
	   error = true;
      

   }

   if($('#description').val().length >500)
   {
	   str+='File description should not exceed 500 characters.</br>';
	   error = true;
      
   }


   if($('#audioFile').val() =="")
   {
	   str+='File  is required</br>';
	   error = true; 
       

   }

   //validation for file type
  if($('#audioFile').val() !="")
 {
  var fileName=document.getElementById("audioFile").value;
  var fileNameInUpperCase=fileName.toUpperCase();
  var ext = fileNameInUpperCase.substring(fileName.lastIndexOf('.') + 1);
 if(ext !="WAV" && ext != "MP3") 
 {
	 str+='file should be in .wav or .mp3 format only.</br>';
	 error=true;
 }else{
	error=false;
 }
 
 }
//validation for file size
if($('#audioFile').val() !="")
{
  var fileSize=Math.round( (Math.round(document.getElementById("audioFile").files[0].size /1024)) /1024) ;
  
  if(fileSize>2)
	{
      str+='File size should not exceed 2 MB</br>';
       error = true;
	   
    }
	else{
	error=false;
	}
 
}


errorDiv.innerHTML+= str;
  if(error == true)
	  return false;
  else
	  return true;
}

function checkUploadFileExists()
{ 
	
   var patt1=/^[a-zA-Z0-9_]+$/;
   var str1=' ';
    if($('#fileName').val() != "")
	{
     if( !$('#fileName').val().match(patt1))
     {  
		str1+='filename should not contain any special characters and spaces.</br>';
	    error = true;
        $("#errormsg").html(str1);
        return false;
	  } 
    
     else{
          str1='';
          $("#errormsg").html(str1);
         }
	}
  $.ajaxSetup({
			  
			   jsonp: null,
			   jsonpCallback: null
			 });


$.ajax({
		 
		 type:'GET', 
		 url: 'uploadFileExists.action', 
		 dataType: 'json',
		 data: {   
				 fileName:document.getElementById("fileName").value
	            },
					 
		 success: function(data){ 				 
				 
                                  if(data==true)
			                       {
									 $("#errormsg").html("A file already exists with this name.");
						              error=true;
									}

								  
								  else
			                       {
									  $("#errormsg").html('');
                                      error=false;
                                   }   
								   
								
								  },
								 
							
		 error:function(){ 
						    alert("failure");
						 }
							    
		});
}					 

</script>
</body>
</html>