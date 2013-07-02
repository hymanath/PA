<%@ taglib prefix="s" uri="/struts-tags" %>  
<html>  
<head>  
<title> Registration</title>

</head>  
<body>  
<style>
	#bodyId{width:995px;margin-left:auto;margin-right:auto;}
	
</style>
<script>

$(document).ready(function () {
	var passwordChanged = '${passwordChanged}';
	alert(passwordChanged);
	if(passwordChanged == "NO")
		{
			$('#passwordWindow').show();
		}
		
		else if(passwordChanged == "YES")
		{
			$('#passwordWindow').hide();
		}
});

$('#changePassword').click(function(){
$('#passwordModal').modal('show');
});
</script>
<div id="bodyId">Hello Welcome to Electoral Connect </div>
<span style="float:right;display:none;" id="passwordWindow"><a class="btn btn-primary" id="changePassword" >Change Password</a></span>
</body>  
</html>