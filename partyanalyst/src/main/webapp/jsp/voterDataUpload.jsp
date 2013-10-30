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

<script type="text/javascript">

 $(document).ready(function() {
        $("#description").cleditor();
      });


function getPublicationDates()
 {
				var jsObj=
				{
						
						task:"publicationDates"						
				};
			var url = "<%=request.getContextPath()%>/getAllPublicationDatesAction.action";

		callAjax('',jsObj,url);

 }
		
		
		function callAjax(rparam, jsObj, url){
			var resultVO;			
			var callback = {			
		               success : function( o ) {
							try {								
									resultVO = YAHOO.lang.JSON.parse(o.responseText);										
									
									 if(jsObj.task == "publicationDates"){
                                          buildOptions(resultVO);
                                     }									
							}catch (e)  {   
							   	//alert("Invalid JSON result" + e);   
							}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };
		
			YAHOO.util.Connect.asyncRequest('GET', url, callback);			
		}
function buildOptions(results){
    
  var elmt = document.getElementById("publicationDateId");
		for(var i in results)
	   {
		 var option = document.createElement('option');
 
		  option.value=results[i].id;
		  option.text=results[i].name;
		
		  try
		  {
			elmt.add(option,null); // standards compliant
		  }
		  catch(ex)
		  {
			elmt.add(option); // IE only
		  }
	   }
 
}

</script>

<script>
getPublicationDates();

</script>
</head>
<body>
<s:if test="#session.USER !=null">
<s:if test="#session.USER.isAdmin == 'true'">
<s:form action="voterDataUploadAction" method="POST" enctype="multipart/form-data">


 <select id="specialPage">
<option value="1">SpecialPage1</option>
<option value="2">SpecialPage2</option>
<option value="3">SpecialPage3</option>
<option value="4">SpecialPage4</option>
<option value="5">SpecialPage5</option>
 </select>
 <textarea id="description" style="width:618px;height:92px;margin:2px 0px 0px 20px;" class='jbDscrptnReq' placeholder="Enter job description here...."></textarea>

 <input type="button" class="btn btn-success" value="Test" onClick="saveTextForSpecialPage();"/>

<script>
var specialPageText={
	id:'',
   text:''
};

function saveTextForSpecialPage(){

	specialPageText.id = $('#specialPage').val();
	specialPageText.text = $('#description').val();

	$.ajaxSetup({
	   jsonp: null,
	   jsonpCallback: null
	});

	$.ajax({
		type:'POST',
		url: 'saveSpecialPageTextAction',
		dataType: 'json',
		data: {specialPageText:JSON.stringify(specialPageText)},
		success: function(data){
			
			console.log(data);
			
		},
		error:function() { 
			alert("failure");
			
		}
	});
}
 </script>

	<h3>Voter Data Upload</h3>
	<table border="1">
		<tr>
			<td>Election Type</td>
			<td><select name="electionTypeId">
				<option value="0">Select option..</option>
				<option value="2">Assembly</option>
				<option value="1">Parliament</option>
			</select></td>
		</tr>
		<tr>
			<td>State</td>
			<td><select name="stateId">
				<option value="0">Select option..</option>
				<option value="1">Andhra Pradesh</option>
				<option value="24">Tamilnadu</option>
			</select></td>
		</tr>
		<tr>
			<td>Election Year</td>
			<td><select name="electionYear">
				<option value="0">Select option..</option>
				<option value="2009">2009</option>
				<option value="2004">2004</option>
			</select></td>
		</tr>

		<tr>
			<td>Publication date</td>
			<td><select id="publicationDateId" name="publicationDate">
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><s:file name="filePath" label="File Path" /></td>
		</tr>
		<tr><td><s:checkbox label="%{getText('validateData')}" name="validateData"/></td></tr>
		<tr><TD><s:submit name="upload" value="Upload" /></TD></tr>	
	</table>
	<c:if test="${!empty corrections}">
		<div style="text-align:left;margin-left:50px;">
			<c:forEach var="result" items="${corrections}" varStatus="">
				<c:out value="${result}"></c:out><br/>
			</c:forEach>
		</div>
	</c:if>	
</s:form>
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
</body>

</html>