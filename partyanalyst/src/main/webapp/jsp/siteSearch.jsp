<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search in PartyAnalyst for Politician and Constituency</title>

<style type="text/css">
#siteSeachMainDiv
{
margin-left:auto;
margin-right:auto;
float:none;
margin-top: 35px; 
margin-bottom: 110px;
}
#newsAnalyse{ background-color: #CADEF4;
    border: 1px solid #CCCCCC;
    direction: ltr;
    line-height: 3.5;
    overflow: visible;
    padding: 10px;
    text-align: center;
    width: 20em;
	 height: 50px;
}
.grad {
        border-radius: 5px;
	background-color:#5189C6;
}
#newsAnalyse a:hover {
    background: none repeat scroll 0 0 	#C00000  
	}
.searchId
{
background-position : left -54px;
}
.searchSpan
{
background-position :right -81px;
}
</style>

</head>
<body>
<div id="siteSeachMainDiv" style="width:800px;">
<table><tr>
<td><div id="cse" style="width: 569px;">Loading</div></td>
<td valign="top" style = "matrgin-top:10px;">
<div id="newsAnalyse">
<a class="grad" style="text-decoration:none;padding:5px; padding-top:5px;font-weight:bold;text-align:center;color:#ffffff"  href="searchPartyAnalystAction.action">Use PartyAnalyst Custom Search</a>

</div></td>
</tr>
</table>
<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#searchId").css("background-position","left -54px");
	$("#searchSpanId").css("background-position","right -81px");
	$("#searchSpanId").closest("li").addClass("active");
  }); 
  google.load('search', '1', {language : 'en', style : google.loader.themes.V2_DEFAULT});
  google.setOnLoadCallback(function() {
    var customSearchOptions = {};  var customSearchControl = new google.search.CustomSearchControl(
      '014717670535677142302:jwoy3_wo5rw', customSearchOptions);
    customSearchControl.setResultSetSize(google.search.Search.LINK_TARGET_BLANK);
    customSearchControl.draw('cse');
  }, true);
  
</script>
</div>
</body>
</html>