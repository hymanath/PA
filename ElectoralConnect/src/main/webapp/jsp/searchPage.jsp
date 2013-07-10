<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Page</title>
</head>
<body>
<style>
	#searchPageBodyId{width:450px;margin-left:auto;margin-right:auto;}
	#byId,#byName{margin:10px;}
	#searchPageBodyId h3{text-align:center;}
	#searchByEPICId{margin-left:80px;}
</style>
<div id='searchPageBodyId' class="container">
	 <!--<div class="input-append" id="searchByEPICId">
    	<input class="span2" id="epicId" type="text">
    	<button class="btn" type="button" id="searchById">Search</button>
    </div>-->
	
	<h3>Search Your Details</h3><hr>
	<div class="input-append" id="searchByEPICId">
    	<span class="btn btn-inverse breadcrumb" id="byId" title="Search Your Details by EPIC ID">Search By Id</span>
		<span class="btn btn-inverse breadcrumb" id="byName" title="Search Your Details by Name/House No/EPIC">Search By Name</span>
	</div>
	<div id="errorMsgId"></div>
	
	<div id="voterDetails" class="breadcrumb" style="display:none;"></div>
</div>

<script>

$('#byId').click(function(){
	window.open('http://123.176.47.59/Epicsearch/Search.aspx','_blank');
});
$('#byName').click(function(){
	window.open('http://123.176.47.59/Search/Search.aspx','_blank');
});

</script>
</body>
</html>