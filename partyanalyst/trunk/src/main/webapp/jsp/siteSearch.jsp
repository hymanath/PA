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
</style>

</head>
<body>
<div id="siteSeachMainDiv" style="width:800px;">
<div id="cse">Loading</div>

<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script type="text/javascript"> 
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