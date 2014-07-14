<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign User To Web Monitoring</title>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript" src="js/multiSelectBox/jquery.multiselect.js"></script>
<link rel="stylesheet" type="text/css" href="css/multiSelectBox/jquery.multiselect.css" />
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
</head>
<body>
<style>
.requiredFont
{
	font-weight:bold;
	color:red;
}
</style>
<script>
  $(document).ready(function(){
	  $('#userIds').multiselect({
	  noneSelectedText:"Select User(s)"});
});
</script>

	Select User<font class="requiredFont">*</font>
		<s:select theme="simple"  name="user" id="userId" list="usersList" listKey="id" listValue="name" />


											
	Select Constituency<font class="requiredFont">*</font>
		<s:select theme="simple"  name="constituency" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" onchange="getUserTypes()"/>

	 Select User(s)
        <select id="userIds" multiple=true>
		</select>

<script>
function getUserTypes()
{
	$.ajax({
	type:'GET',
	url: 'getAssignedUsersOfAConstituency.action',
	dataType: 'json',
	data: {constituencyId:$('#constituencyId').val()},
	}).done(function(result){
      buildConstituencies(result);
	});
}
function buildConstituencies(result)
{
	$('#userIds').find('option').remove();
   $.each(result,function(index,value){
	   $('#userIds').append('<option value="'+value.id+'">'+value.name+'</option>');
   });
}

function getUserTypes()
{
	var jsObj ={
		userId:'',
        boothIds:[]
	};

	$.ajax({
	type:'GET',
	url: 'saveWebMonioringAssignDetails.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
	});
}
</script>
</body>
</html>