<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
      
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delimitation Mappings Upload</title>
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
#censusResults
{
	background-color:#F5EFEA;
	color: #77471D;
    font-family: Arial;
    font-size: 14px;
    text-align: left;

}
</style>
</head>
	<body>
	<s:form name="DelimitationMappingsUploadAction" action="delimitationMappingsUploadAction" method="post" enctype="multipart/form-data" >
	<h3>Delimitation Mappings Upload</h3>
		<table style="border-collapse:collapse" border="2">
			<tr>
				<td align="center"><b>Country</b></td>
				<td align="left">
					<select name="country">
						<option value="India">India</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center"><b> Delimitation Year </b></td>
				<td align="left">
					<select name="delimitationYear">
						<option value="0">Select option..</option>
						<option value="2009">2009</option>
						<option value="2004">2004</option>					
					</select>	
				</td>			
			</tr>
			<tr>
				<td align="center"><b> Census Year </b></td>
				<td align="left">
					<select name="censusYear">
						<option value="0">Select option..</option>
						<option value="2001">2001</option>					
					</select>	
				</td>			
			</tr>
			<tr>
				<td> <s:file name="filePath" label="Upload  File Path" /></td>
			</tr>			
			<tr>
			
				<td colspan="2"><s:checkbox name="isValidate" id="isValidate" label="Validate Data"/></td>
				<td colspan="2" align="center"><s:submit name="upload" value="Upload" align="center"/></td>
			</tr>
			
		</table>
	</s:form>

	
	<c:if test="${delimitationMappingUploadVO != null}">

	<c:if test="${isValidate == 'true'}">
        
		<BR><BR>
		<H2>Validation Results ...</H2><BR><BR>
		
		<c:forEach var="validation" items="${delimitationMappingUploadVO.delimitationUploadValidationVO}" varStatus="status">

		
          
          <table border="0">
		    
			<tr>
			   <td><h4>State </h4></td>
			   <td style="color:Blue;font-size:12px;"><c:out value="${validation.state}" /></td>
			</tr>
			<tr>

			   <td><h4>District </h4></td>
			   <td style="color:Blue;font-size:12px;"><c:out value="${validation.district}" /></td>
			</tr>
			<tr>

			   <td><h4>Constituency </h4></td>
			   <td style="color:Blue;font-size:12px;"><c:out value="${validation.constituency}" /></td>
            </tr>
			<tr>
			   <td colspan="2" style="font-size:14px;" align="center">Existing Mandals </td>
			   <td colspan="2" style="font-size:14px;" align="center"> Mandals In Sheet </td>
			</tr>
			<tr>
			   <td colspan="2">
			       <table border="2" cellspacing="2">
				      <tr>
					      <td><h4> Mandal </h4></td>
						  <td><h4> Is Partial </h4></td>
					  </tr>
				      <c:forEach var="existing" items="${validation.existingMandals}" varStatus="status">
					    <tr>
						  
						  <c:if test="${existing.name == 'N/A'}">
						  <td style="color:red;font-size:14px;"><c:out value="${existing.name}" /></td>
                          </c:if>

						  <c:if test="${existing.name != 'N/A'}">
						  <td style="color:green;font-size:14px;"><c:out value="${existing.name}" /></td>
                          </c:if>


						  <c:if test="${existing.id == 0}">
						   <td align="center"><c:out value="full" /></td>
						  </c:if>
						  <c:if test="${existing.id == 1}">
						   <td align="center"><c:out value="part" /></td>
						  </c:if>
						</tr>
					  </c:forEach>
				   </table>
			   </td>
		
			    <td colspan="2">
			       <table border="2" cellspacing="2">
				      <tr>
					      <td><h4> Mandal </h4></td>
						  <td><h4> Is Partial </h4></td>
					  </tr>
				      <c:forEach var="toSave" items="${validation.mandalsToSave}" varStatus="status">
					    <tr>

						  <c:if test="${toSave.name == 'N/A'}">
						  <td style="color:red;font-size:14px;"><c:out value="${toSave.name}" /></td>
                          </c:if>

						  <c:if test="${toSave.name != 'N/A'}">
						  <td style="color:green;font-size:14px;"><c:out value="${toSave.name}" /></td>
                          </c:if>

						  <c:if test="${toSave.id == 0}">
						   <td align="center"><c:out value="full" /></td>
						  </c:if>
						  <c:if test="${toSave.id == 1}">
						   <td align="center"><c:out value="part" /></td>
						  </c:if>
						</tr>
					  </c:forEach>
				   </table>
			   </td>
			 </tr>
			 
		  </table>

		  <h3>....................................................................................................</h3>
        </c:forEach>
    </c:if>

	

   <c:if test="${isValidate == 'false' || isValidate == null}">
	<c:if test="${delimitationMappingUploadVO.exceptionEncountered == null}">
		    
		   <BR><BR>
		   <H2>Upload Process Completed Successfully ...</H2><BR><BR>
			
	</c:if>
	  <c:if test="${delimitationMappingUploadVO.delimitationYear != null}">
	   <table id = "censusResults">
	      <tr>
		     <td><b>Delimitation Year :<b></td>
		     <td><c:out value="${delimitationMappingUploadVO.delimitationYear}" /></td>
		  </tr>

		  <tr>
		     <td>Executed Last Row No :</td>
		     <td><c:out value="${delimitationMappingUploadVO.currentRow}" /></td>
		   </tr>

		  <tr>
		     <td>Executed Last Sheet No:</td>
		     <td><c:out value="${delimitationMappingUploadVO.currentSheet}" /></td>
		  </tr>

          <c:if test="${delimitationMappingUploadVO.mappedConstituencies != null}">
		  <tr>
		   <td><b>Mapped Constituencies :<b></td>
		     
			  <c:forEach var="consti" items="${delimitationMappingUploadVO.mappedConstituencies}" varStatus="status">
					
				<c:if test="${status.count%3 == 0}">
				   </tr>
				   <tr>
				</c:if>
				<td><c:out value="${consti}" /></td>
							  
			  </c:forEach>
		  </tr>
		  <tr>
		     <td>.........................................</td>
		  </tr>
		  </c:if>

		  <c:if test="${delimitationMappingUploadVO.exceptionEncountered != null}">
		    <tr>
			</td><H2>Exception Encountered :</H2></td>
		    <c:out value="${delimitationMappingUploadVO.exceptionEncountered}" />
			</tr>
		  </c:if>

	   </table>

	  </c:if>
	</c:if>
  </c:if>
	</body>
</html>