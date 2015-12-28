<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>

<title>Cadre Registration Report</title>

<script>

jQuery( document ).ready(function( $ ) {
	$( "#date" ).datepicker();
});

</script>
<style>

.background {
    background: none repeat scroll 0 0 #e5e5e5;
	}
</style>

</head>

<body>
	

	<div class="container">
	
	<div class="span12 well well-small border-radius-0 mb-0 " style="background:#ffffff;"">
		<h3 class="text-center text-uppercase">Cadre Registration Amount Upload</h3>
	</div>	
	
			
		<div class="span12 well well-small" style="min-height:300px;background:#ffffff;">
		<div id="errorDiv" style="margin-bottom:10px;margin-left:215px;color:red" > </div>
			
			<div id="contentDivId" align="center" style="margin-bottom:100px;">
						
			<s:form name="cadreRegAmountFileUpload" action="cadreRegAmountFileUpload.action" method="post" enctype="multipart/form-data" onsubmit="return validatefields();">
	
			<table style= "width:500px;"">
				<tr>
					<td><label>Select Date : </label></td>
					<td><input type="text" id="date" name="date" readonly></input></td>
				</tr>
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
					<tr><td></td><td>&nbsp;&nbsp;&nbsp;I. Column - Branch name</td></tr>
					<tr><td></td><td>&nbsp;&nbsp;&nbsp;II. Column - Amount</td></tr>
					<tr><td></td><td>&nbsp;&nbsp;&nbsp;III. Column - Username</td></tr>
				<tr><td></td><td><b>4. Data should be in First work sheet .</b></td></tr>
			</table>
			</s:form>
	
	</div>	
	</div>
	</div>
<script>	
function validatefields(){   

	var flag = true;
	
	  if($("#date").val().length == 0){
		$("#errorDiv").text("Please Select Date");
		flag= false;
		return flag;
	  } 
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
