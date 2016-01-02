<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">


<title>Cadre Registration Report</title>

<script>

/* $('document').ready(function(){
	$( "#date" ).datepicker();
}); */

</script>
<style>
#trigger{
  font-size: 12px !important;
    height: 15px;
    line-height: 15px;
    padding: 3px;
}
.background {
    background: none repeat scroll 0 0 #e5e5e5;
	}
	.errors{color:red; margin-left: 200px;}
	.success{color:green; margin-left: 200px;}
</style>

</head>

<body>
	

	<div class="container">
	
	<div class="span12 well well-small border-radius-0 mb-0 " style="background:#ffffff;"">
		<h3 class="text-center text-uppercase">Cadre SMS Status Upload</h3>
	</div>	
	
			
		<div class="span12 well well-small" style="min-height:300px;background:#ffffff;">
		 <div class="errors">
	 <s:if test="hasActionErrors()">
        <s:actionerror />
    </s:if>
	</div>
	 <div class="success">
    <s:if test="hasActionMessages()">
        <s:actionmessage />    
    </s:if>
	</div>
		<div id="errorDiv" style="margin-bottom:10px;margin-left:215px;color:red" > </div>
			
			<div id="contentDivId" align="center" style="margin-bottom:100px;">
						
			<s:form name="cadreSMSFileUpload" action="cadreSMSFileUpload.action" method="post" enctype="multipart/form-data" onsubmit="return validatefields();">
	
			<table style= "width:500px;"">
				<tr>
					<td><label>Select File : </label></td>
					<td><input type="file" id="file" name="file"></input></td>
				</tr>
				<tr>
					<td></td>
					<td align="centre"><input type="submit" value=" Submit "></td>
				</tr>
			</table>
			<br>
	
			<table style="margin-left: auto; margin-right: auto; width:505px;">
				<tr><td></td><td><b>Instructions to Upload</b></td></tr>
				<tr><td></td><td><b></b></td></tr>
				<tr><td></td><td><b></b></td></tr>
				<tr><td></td><td><b>1. Xls should be in Ms-Excel 97-2003 format with extention .xls .</b></td></tr>
				<tr><td></td><td><b>2. First row should contains column headers .</b></td></tr>
				<tr><td></td><td><b>3. Data should e in bellow format .</b></td></tr>
					<tr><td></td><td>&nbsp;&nbsp;&nbsp;I. Column - TRNO</td></tr>
					<tr><td></td><td>&nbsp;&nbsp;&nbsp;III. Column - Mobile No</td></tr>
					<tr><td></td><td>&nbsp;&nbsp;&nbsp;V. Column - Status</td></tr>
				<tr><td></td><td><b>4. Data should be in First work sheet .</b></td></tr>
			</table>
			</s:form>
	
	</div>	
	</div>
	</div>
<script>	
function validatefields(){   

	var flag = true;
	$(".errors").html('');
	 $(".success").html('');
	  var fileName = $("#file").val().trim();
	  if(fileName == 0){
		$("#errorDiv").text("Please Select file to Upload");
		flag= false;
		return flag;
	  }
	 var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
	 if(ext != "xls")
	 {
		$("#errorDiv").text("Please Select only .xls file");
		flag= false;
		return flag;
	 } 
	
}

</script>
</body>
</html>
