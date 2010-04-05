<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Village Booth Mapping Upload</title>
<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
<script type="text/javascript">

		function getElectionYears(id){
			var jsObj=
				{
						electionTypeId:id,
						task:"getElectionYears"						
				};
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "<%=request.getContextPath()%>/getElectionYearsForMappingAjaxAction.action?"+rparam;						
				callAjax(rparam,jsObj,url);
		}
		
		function callAjax(rparam, jsObj, url){
			var resultVO;			
			var callback = {			
		               success : function( o ) {
							try {								
									resultVO = YAHOO.lang.JSON.parse(o.responseText);										
									
									if(jsObj.task == "getElectionYears")
									{								
										clearOptionsListForSelectElmtId("electionYear");
										createOptionsForSelectElmtId("electionYear",resultVO);		
									}				
							}catch (e)  {   
							   	alert("Invalid JSON result" + e);   
							}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };
		
			YAHOO.util.Connect.asyncRequest('GET', url, callback);			
		}

</script>
</head>
<body>
	<s:form name="VillageBoothElectionAction" action="villageBoothElectionMapAction" method="post" enctype="multipart/form-data" >
		<table>
			<tr>
				<td>Election Type:</td>
				<td>
					<select id="electionTypeSelect" onchange = "getElectionYears(this.options[this.selectedIndex].value)" class = "selectWidth">
						<option value="0">Select </option>
						<option value="1">Parliament</option>
						<option value="2">Assembly</option>
					</select>
				</td>
				<td>Election Year:</td>
				<td>
					<select id="electionYear" name="electionId">
						<option value="0">Select </option>
					</select>
				</td>
				<td><s:file name="filePath" label="File Path"/></td>
				<td colspan="2"><s:submit name="upload" value="Upload" align="center"/></td>
			</tr>
		</table>
	</s:form>
</body>
</html>