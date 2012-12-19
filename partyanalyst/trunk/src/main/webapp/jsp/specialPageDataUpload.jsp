<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script src="js/cleditor/jquery.cleditor.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/cleditor/jquery.cleditor.css">
<style>
.selectBox{
 padding:3px;
 width:249px;

}
</style>
<script type="text/javascript">

 $(document).ready(function() {
        $("#description").cleditor();
      });
</script>


</head>
<body>
<s:if test="#session.USER !=null">
<s:if test="#session.USER.isAdmin == 'true'">

<div style="margin:10px;">
	<s:select cssClass="selectBox" theme="simple" label="Select Your State" name="state" id="specialPage" list="specialPagesList" listKey="id" listValue="name"/>
</div>

 <textarea id="description" style="width:618px;height:92px;margin:2px 0px 0px 20px;" ></textarea>

 <input type="button" style="background:#c3c3c3;" value="UploadData" onClick="saveTextForSpecialPage();"/>

 <input type="button" style="background:#c3c3c3;" value="Preview" onClick="showPreview();"/>

 <div id="previewDiv" style="background-color:#fff;border:1px solid #e5e5e5;padding:20px;">${specilaPageText}</div>


 <div id="successDiv"></div>

	
</s:if>
<s:else>
<%
	response.sendRedirect("userEntitlementAction.action");
%>
</s:else>
</s:if>
<s:else>
<%
	response.sendRedirect("loginInputAction.action");
%>
</s:else>

<script>
var specialPageText={
	id:'',
   text:''
};

function showPreview(){
	$('#previewDiv').html($('#description').val());

}

function saveTextForSpecialPage(){

	specialPageText.id = $('#specialPage').val();
	specialPageText.text = $('#description').val();

	$.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});

	$.ajax({
		type:'POST',
		url: 'saveSpecialPageTextAction.action',
		dataType: 'json',
		data: {specialPageText:JSON.stringify(specialPageText)},
		success: function(data){
			
			if(data == "Success"){

				var cssObj = {    
				'font-weight' : 'bold',
				'color' : 'green'
			    }

		   $("#successDiv").text("Your data uploaded successfully for "+$('#specialPage :selected').text()).css(cssObj).show().delay(2000).fadeOut(400);

		   $('#description').val('')

              var editor = $("#description").cleditor()[0];
	           editor.updateFrame();

			}
			
		},
		error:function() { 
			alert("failure");
			
		}
	});
}
 </script>

</body>

</html>