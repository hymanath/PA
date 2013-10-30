<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Candidates Info</title>

<!--CSS file (default YUI Sam Skin) -->
<link type="text/css" rel="stylesheet" href="js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
 
<!-- Dependencies -->
<script src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js"></script>
<script src="js/yahoo/yui-js-2.8/build/datatable/datatable-min.js"></script>


<style type="text/css">
	body
	{
		background-color:#EAEDEF;
		direction:ltr;
		font-family:"lucida grande",tahoma,verdana,arial,sans-serif;
		font-size:11px;
		margin:0;
		padding:0;
	}	
	
	#groupsCandidateInfo_body table
	{
		width:100%
	}
</style>

</head>
<body>
	Candidates Info Page
	
	<div id="groupsCandidateInfo_main">
		<div id="groupsCandidateInfo_head">
		
		</div>
		<div id="groupsCandidateInfo_body" class="yui-skin-sam">
			<div id="groupsCandidateInfo_body_dataTable"></div>	
		</div>
	</div>
<script type="text/javascript">

	var groupsInfo = new Array();

	<c:forEach var="groups"  items="${userGroupMembersInfoVO}" >
	var ob={
				name:'${groups.name}',
				mobile:'${groups.mobileNumber}',
				location:'${groups.location}',
			};
		groupsInfo.push(ob);
	</c:forEach>

	var myColumnDefs = [ 
	            {key:"name",label:"Name", sortable:true, resizeable:true}, 
	            {key:"mobile", label:"Mobile", sortable:true, resizeable:true}, 
	            {key:"location", label:"Location", sortable:true, resizeable:true}	            
	        ]; 
	 
	        var myDataSource = new YAHOO.util.DataSource(groupsInfo); 
	        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY; 
	        myDataSource.responseSchema = { 
	            fields: [
							{	
								key:"name"
							},
							{
								key:"mobile"
							},
							{
								key:"location"
							}
				] 
	        }; 
	var myConfigs = {
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage: 10
		})
	};

	var myDataTable = new YAHOO.widget.DataTable("groupsCandidateInfo_body_dataTable", 
	                myColumnDefs, myDataSource,myConfigs); 
</script>
</body>
</html>