<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Party Analyst</title>

	<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
</head>
<body>

<div id="voterManagementMainDiv">
	<div class="voterManagementInnerDiv">
		

		<p>Constituency :<s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="constituenciesList" id="constituencies_List" list="constituenciesList" listKey="id" listValue="name"></s:select></p>
		 <p>Publication Date :<s:select cssClass="selectBoxWidth" theme="simple" label="Select Your State" name="publicationDateList" id="publicationDate_List" list="publicationDateList" listKey="id" listValue="name"></s:select></p>
		<p>Min Result:<input type="text" id="minResults"/></p>
		<p>Max Result :<input type="text" id="maxResults"/></p>
		<p><input type="button" value="submit" id="voterDataInsertId"/></p>
		
	</div>

</div>




<script type="text/javascript">

/*
function callAjax(jsObj, url){


	var callback = {			
				   success : function( o ) {
						try
						{
							
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "getConstituencies")
							{
								clearOptionsListForSelectElmtId(jsObj.elmtId);
								createOptionsForSelectElmtId(jsObj.elmtId,myResults);
								hideBusyImgWithId(jsObj.elmtId);
							}
							
						}
						catch(e)
						{   
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
*/
	
	$(document).ready(function() {
		$("#voterDataInsertId").click(function(){
			var constituencyId = $("#constituencies_List").val();
			var publicationDateId = $("#publicationDate_List").val();
			var minResults = $.trim($("#minResults").val());
			var maxResults = $.trim($("#maxResults").val());

			var jsObj=
			{				
			constituencyId: constituencyId,
			publicationDateId: publicationDateId,
			startIndex : minResults,
			maxResults : maxResults,
			task: "insertVoterData",
			elmtId: element 	
			}

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "insertVoterDataAction.action?"+rparam;						
	callAjax(jsObj,url);
			
		});

	});

</script>
</body>
</html>