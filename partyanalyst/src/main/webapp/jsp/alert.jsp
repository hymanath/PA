<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags" %>  
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> Alerts </title>
	<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		

	
	
	
	<link  rel="stylesheet" type="text/css" href="debate/js/jquery.google.api/jquery-ui1.10.3.css"/>
	
	
	<link rel="stylesheet" media="all" type="text/css" href="jquery-ui-timepicker-addon.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.filter.css" />
	<link rel="stylesheet" type="text/css" href="styles/custom-yui-styles.css">	
	<link rel="stylesheet" type="text/css" href="styles/jquery.dataTables.css"> 	
	<link rel="stylesheet" type="text/css" href="debate/pagination/simplePagination.css">
		 
		 		
	
	<!-- YUI Dependency files (Start) -->
		<script type="text/javascript" src="debate/js/yahoo/yahoo-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/yahoo-dom-event.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/element-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/container-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/dom-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/yui-min.js"></script>
		<script type="text/javascript" src="debate/js/json/json-min.js"></script>
		<script type="text/javascript" src="debate/js/yahoo/connection-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datasource-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/datatable-min.js"></script> 
		<script type="text/javascript" src="debate/js/yahoo/paginator-min.js"></script>
		
		
		
		<!-- Skin CSS files resize.css must load before layout.css --> 
		<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
		<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">

		<!-- YUI Dependency files (End) -->
		
		
	
<!-- JQuery files (Start) -->
	<script src="debate/js/jquery.google.api/ajax.googleapis.com.ajax.libs.jquery.1.8.2.jquery.min.js"></script>
	<script type="text/javascript" src="debate/js/bootstrap.min.js"></script>
	<script src="debate/js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
	<script src="debate/js/jquery-ui-themes-1.10.3.js"></script>
	<script src="debate/js/jquery-ui-timepicker-addon.js"></script>
	<script src="debate/js/jquery-ui-sliderAccess.js"></script>
	
	<script src="debate/js/jquery.google.api/trentrichardson.com.examples.timepicker.jquery-ui-sliderAccess.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="js/debate.js"></script>
	
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.js"></script>
	<script type="text/javascript" src="debate/js/multiSelectBox/jquery.multiselect.filter.js"></script>
	<script type="text/javascript" src="debate/js/jquery.dataTables.js"></script>	
	<script type="text/javascript" src="debate/js/jQuery/debate/js/jquery-ui-1.8.24.custom.min.js"/>
	<script type="text/javascript" src="debate/pagination/pagination1.js"></script>
	<script type="text/javascript" src="debate/pagination/jquery.simplePagination.js"></script>
	
	
 </head>                                         							
<body>
<div class="container">
                 <div class="row">
					 <div class="col-md-2 levelShowCls" >
						<label>Level</label>
						<select class="dropkickClass" id="levelId" onchange="disableByLevel();">
						<option value="10">State</option>
				         <option value="11">District</option>
					     <option value="1">Constituency</option>
						 <option value="5">Mandal/Muncipality</option>
						 <option value="6">Village/Ward</option>
						</select>
					</div>
					<div class="col-md-2 stateShowCls" >
					    <label>State</label>
					     <select class="dropkickClass" class="stateCls" id="stateId" onChange="getDistrictsForReferPopup();">
					     <option value="0">All</option>
				         <option value="1">AP</option>
					     <option value="36">TS</option>
					     </select>
					</div>
			       
					  <div class="col-md-2 locationsFilterCls distCls">
					     <label>District</label>
				         <select class="dropkickClass" id="referdistrictId" onChange="getConstituenciesBydistrictForReferPopup();">
				         <option value="0">All</option></select>
					</div>
					<div class="col-md-2 locationsFilterCls constiCls">
						<label>Assembly</label>
					    <select class="dropkickClass" id="referconstituencyId" onChange="getMandalsByConstituencyForReferPopup();">
					    <option value="0">All</option>
						</select>
					</div>
					<div class="col-md-3 locationsFilterCls mandalCls">
						<label>Mandal/ Municipality</label>
					     <select class="dropkickClass" id="refermandalNameId" onChange="getPanchayatsForReferPopup();">
							<option value="0">All</option>
						 </select>
					</div>
					<div class="col-md-3 locationsFilterCls panchayatCls">
						<label>Panchayat</label>
						<select class="dropkickClass" id="referpanchayatId">
						<option value="0">All</option>
						</select>
					</div>
	</div>
</div>
<script src="dist/CreateAlert/createAlert.js" type="text/javascript"></script>
<script type="text/javascript">
</script>
</body>
</html>