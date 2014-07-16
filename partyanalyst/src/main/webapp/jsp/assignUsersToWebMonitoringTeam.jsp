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
.requiredFont,#errorDiv
{
	font-weight:bold;
	color:red;
}
#statusDiv
{
	font-weight:bold;
	color:green;
}
h4
{
  margin-bottom:40px;
}
</style>
<script>
  $(document).ready(function(){
	  $('#constituencyId').change(function(){

	  });
	 // $('#userIds').multiselect({
	  //noneSelectedText:"Select User(s)"});
});
</script>

<div class="container well">

<div id="errorDiv"></div>
<div id="statusDiv"></div>

	<h4 class="offset4">ASSIGN USERS TO WEB MONITORS</h4>
   <div class="offset3">
    <div class="span7">
		<div class="span2">
			Select User<font class="requiredFont">*</font>
		</div>
		<div class="span4">
			<s:select theme="simple"  name="user" id="userId" list="usersList" listKey="id" listValue="name" />

		</div>
    </div>

	<div class="span7">
		<div class="span2">
			Select Constituency<font class="requiredFont">*</font>
		</div>
		<div class="span4">
			<s:select theme="simple"  name="constituency" id="constituencyId" list="constituenciesList" listKey="id" listValue="name" onchange="getAssignedUsersOfConstituency()"/>
			<img  id="constituencyImage"  src="./images/icons/search.gif" alt="Processing Image"  class="hide"></img>
		</div>
    </div>


	<div class="span7">
		<div class="span2">
			Select User(s)<font class="requiredFont">*</font>
		</div>
		<div class="span4">
			 <select id="userIds" multiple>
		</select>
		</div>
    </div>

	<div class="span10 offset3">
 	 <a href="javascript:{saveWebMonioringAssignDetails()}" class="btn btn-success">ASSIGN</a>
	 <img  src="./images/icons/search.gif" alt="Processing Image"  id="submitImage" class="hide"></img>

	</div>
 </div>
</div>
<script>
function getAssignedUsersOfConstituency()
{
	$('#constituencyImage').show();

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
   $('#constituencyImage').hide();

   // $('#userIds').multiselect('refresh');
}

function saveWebMonioringAssignDetails()
{


	$('#errorDiv').html('');
	 var errorStr ='';
	if($('#userIds').val() == null)
	  errorStr += "Select Atleast one user to assign .";

	if(errorStr.length > 0)
	{
		$('#errorDiv').html(errorStr);
		return;
	}
		$('#submitImage').show();
	var jsObj ={
		webMonitorUserId:'',
        userIds:[]
	};

		jsObj.webMonitorUserId = $('#userId').val();
		jsObj.userIds = $('#userIds').val();


	$.ajax({
	type:'GET',
	url: 'saveWebMonioringAssignDetails.action',
	dataType: 'json',
	data: {task:JSON.stringify(jsObj)},
	}).done(function(result){
		$('#submitImage').hide();
		if(result == "success")
		{
           $('#statusDiv').html("Assigned Success fully..");
		}
	});
}
</script>
</body>
</html>