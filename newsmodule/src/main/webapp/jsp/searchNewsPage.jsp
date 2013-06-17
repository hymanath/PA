<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ResourceBundle;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>


<script type="text/javascript" src="http://yui.yahooapis.com/combo?2.8.2r1/build/yahoo-dom-event/yahoo-dom-event.js&2.8.2r1/build/connection/connection-min.js&2.8.2r1/build/datasource/datasource-min.js&2.8.2r1/build/autocomplete/autocomplete-min.js&2.8.2r1/build/element/element-min.js&2.8.2r1/build/container/container-min.js&2.8.2r1/build/menu/menu-min.js&2.8.2r1/build/button/button-min.js&2.8.2r1/build/paginator/paginator-min.js&2.8.2r1/build/datatable/datatable-min.js&2.8.2r1/build/json/json-min.js&2.8.2r1/build/tabview/tabview-min.js"></script>
   
<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.dialog.css"/> 
  
<script type="text/javascript" src="js/simplePagination/simplePagination.js" ></script>
<link rel="stylesheet" type="text/css" href="styles/simplePagination/simplePagination.css"/> 


<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<style>
	#candidatesListId{width:250px;}
	#candidateDivId{margin-left:200px;margin:20px;float:left;}
</style>


</head>
<body>
	<div id='candidateDivId'>
		Select Candidate : <s:select theme="simple" label="Candidates" name="candidates" id="candidatesListId" list="candidatesList" listKey="id" listValue="name" onchange=""/>
	</div>	
    <div class="input-append" style="margin-left:200px;margin-top:20px;">
		
		<input class="span4" placeholder="Search News" id="searchNewsId" type="text">
		<button class="btn" type="button" onclick="searchNews()">Search</button>
    </div>
	<script>
		getCandidates();
		
		function searchNews(){
			var text=$('#searchNewsId').val();
			var jsObj =
			{  	
				text:text,
				task:'getNewsBySearch'
			};
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "getNewsBySearchAction.action?"+rparam;
			callAjax(jsObj, url);
		}
		function getCandidates(){
			var jsObj={
				task:'getCandidateNames'
			}
			var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
			var url = "getCandidateNamesAction.action?"+rparam;
			callAjax(jsObj, url);
		}
		
		function callAjax(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
								myResults = YAHOO.lang.JSON.parse(o.responseText);
								
									if(jsObj.task =="getNewsBySearch")
									{
										alert('in');
										//buildSearchedNews(myResults,jsObj);
									}
									if(jsObj.task =="getCandidateNames")
									{
										alert('in2');
										//buildSearchedNews(myResults,jsObj);
									}
								}catch (e) {
							      console.log(e);
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

			YAHOO.util.Connect.asyncRequest('POST', url, callback);
		}
	</script>
</body>

</html>