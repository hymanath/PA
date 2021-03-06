<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booth Data Upload</title>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 
<style>

.scopeWise_head {
	background-color:#F5EFEA;
	color:#77471D;
	font-weight:bold;
	padding:3px;
	text-align:left;
}
.resultsTable
{
	width:100%;	
}
.resultsTable th
{
	text-align:left;	
}
</style>
<script type="text/javascript">
function getPublicationDates()
 {
				
			var url = "<%=request.getContextPath()%>/getAllPublicationDatesAction.action";

		callAjaxToGetpublicationDate(url);

 }
function callAjaxToGetpublicationDate(url)
	{			
		
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);	
								
									buildOptions(myResults);
								
							}catch (e) {   
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
</head>
	<body>
<s:if test="#session.USER !=null">
  <s:if test="#session.USER.isAdmin == 'true'">
	<s:form name="BoothUploadAction" action="boothUploadAction" method="post" enctype="multipart/form-data" >
	<h3>Booth Data Upload</h3>
		<table style="border-collapse:collapse">
			<tr>
				<td><b>Election Type</b></td>
				<td align="left">
					<select name="electionScopeId">
						<option value="0">Select option..</option>
						<option value="1">Parliament</option>
						<option value="2">AP Assembly</option>
						<option value="4">MH Assembly</option>
					</select>
				</td>
			</tr>
			<tr>
				<td> <b>Election Year</b></td>
				<td align="left">
					<select name="electionYear">
						<option value="0">Select option..</option>
						<option value="2012">2012</option>
						<option value="2011">2011</option>
						<option value="2010">2010</option>
						<option value="2009">2009</option>
						<option value="2008">2008</option>
						<option value="2006">2006</option>
						<option value="2004">2004</option>					
					</select>	
				</td>			
			</tr>
			<tr>
				<td> <b>Publication Date</b></td>
				<td align="left">
					<select  name="publicationDateId"  id="publicationDateId"/>	
				</td>			
			</tr>
			<tr>
				<td> <s:file name="filePath" label="File Path" /></td>
			</tr>			
			<tr>
				<td colspan="2"><s:checkbox name="isValidate" id="isValidate" label="Validate Data"/></td>
				<td colspan="2"><s:submit name="upload" value="Upload" align="center"/></td>
			</tr>
			
		</table>
	</s:form>
	<c:if test="${!empty uploadInfo}">
		<div style="margin-top: 10px;">
			<c:forEach var="result" items="${uploadInfo}" varStatus="">
				<div style="border:2px solid #F5EFEA;width:800px;margin-bottom:15px;text-align:left;">			
					<DIV class="scopeWise_head">${result.constituencyName } Assembly Booth Data Upload Info</DIV>
					<table border="0" width="100%" class="resultsTable">
						<tr>
							<th>Booths Identified:</th>
							<td>${result.boothsIdentified}</td>
							<th>Booths Inserted:</th>
							<td>${result.boothsInserted}</td>
							<th>Urban Booths (Without Mandal Info):</th>
							<td>${result.nullTehsilsCount }</td>
						</tr>
					</table>
					<div>			
						<hr style="margin:10px;">
						<c:if test="${! empty result.uploadInfo}">
							<b>Validations and Corrections:</b>
							<table>
								<c:forEach var="info" items="${result.uploadInfo}" varStatus="">
									<tr><td>${info}</td></tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty result.uploadInfo}">
							<b>No Corrections Or Validations Required For This Constituency</b>
						</c:if>
					</div>	
				</div>
			</c:forEach>
		</div>
	</c:if>	
	<script>
     getPublicationDates();
    </script>
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