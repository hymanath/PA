<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change User Password</title>
<script type="text/javascript" src="js/md5.js"></script>
<style>
	#msgDiv{margin-top:10px;}
</style>
</head>
<body>
	
	<div style="margin-left:500px;margin-top:25px;margin-bottom:25px;">
	<div id="errorDiv" class="offset1" style="font-weight:bold;color:red;font-size:15px;height:25px;"></div>
	<div><h4> Select User : <input style="margin-left:31px;" type="userName" id="userNameId" />
	<button type="button" onclick="getUserNameDetails()">view</button></h4>
	<h4>Enter UserName: <select style="margin-left: 53px; width: 220px;" id="usersListId" onchange="diplayValues()"></select></h4>
	<img id="ajaxImage" src="./images/icons/goldAjaxLoad.gif" alt="Processing Image" style="display:none";/>
	</div>
	<div class="user_details" id="user_details" style="display: none;margin-top:8px;">
			<!--<b>First Name :</b><input type="text" id="first_name" /><br/>
			<b>Last	Name :</b><input type="text" id="last_name" /><br/>
			<b>User Id :</b><input type="text" id="user_id" /><br/>-->
			<h4>New Password :<input style="margin-left:31px;" type="password" id="newPasswordId" /><br/></h4>
			<h4>Confirm Password :<input type="password" id="confirmPasswordId" /><br/></h4>
			<input class="btn-info btn-small btnStyle" id="changePWDButton" type="button" value="Change Password" style="margin-left:100px;"></input>
			<div id="msgDiv" class="offset1"></div>
	</div>
	</div>
	<script type="text/javascript">
	
		/*$(document).ready(function() {
			getUserDetails();
		});*/
		function diplayValues(){
			$(".user_details").show();
		}
	var  userNameStr=$("#userNameId").val();	
	function getUserDetails(){
		var  userNameStr=$("#userNameId").val();
		var jsObj =
		        {
			userNameStr : userNameStr
		          }
		//$('#ajaxImage').show();
				$.ajax({
					  type:'GET',
					  url: 'getUserDetailsAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
					$('#usersListId').append('<option value="0"> Select User </option>');
					if(result != null)
					{
						for(var i in result)
						{			
							$('#usersListId').append('<option value='+result[i].id+'>'+result[i].name+'  --  '+result[i].mandalName+' '+result[i].hamletName+' </option>');
						}
					}
				  
				});
		}
		
		$("#changePWDButton").live("click",function(){	
			var uName=$("#usersListId option:selected").text();
			var userName_opt = uName.trim();
			var arr = userName_opt.split('--');
			var userName = arr[0].trim();
			
			var id=$("#usersListId option:selected").val();
			if(id==0){
				$("#errorDiv").html(" Please Select User ");
				return;
			}
			
			var newPassword= $.trim($("#newPasswordId").val());
			var conformPassword=$.trim($("#confirmPasswordId").val());
			if(newPassword.length<=0){
				$("#errorDiv").html("Enter New Password");
				return;
			}
			if(conformPassword.length<=0){
				$("#errorDiv").html("Enter Conform Password");
				return;
			}
			if(newPassword!=conformPassword){
				$("#errorDiv").html("New Password and Conform Password Must Be Same");
				return;
			}
			if(newPassword==conformPassword){
				$("#errorDiv").html("");
				newPassword=MD5(MD5(userName.toLowerCase())+MD5(newPassword));
				var jsObj = 
				   {
					  id:id,
					  newPassword:newPassword,
					  task:"SendValuesToSave"             
				   }				   
				   $.ajax({
						type : "POST",
						url : "updatingPassword.action",
						data : {task:JSON.stringify(jsObj)} ,
					}).done(function(result){
						updateStatus(result);
					});
			}
		});
		
		function updateStatus(result){
			$("#msgDiv").html("");
			
			$("#confirmPasswordId").val("");
			$("#newPasswordId").val("");
			$("#usersListId").val(0);
			
			if(result.resultCode == 0){
				$("#msgDiv").html("<h5 style='color:green;'>Password Updated Successfully</h5>");
			}else{
				$("#msgDiv").html("<h5 style='color:red;'>Unable to Update Password, Please Try Again</h5>");
			}
		}
		function getUserNameDetails()
		{
		 $("#errorDiv").html('');
		 var userNameStr=$("#userNameId").val();
		 if(userNameStr.trim().length<=3 || userNameStr ==""){
          $("#errorDiv").html('plz enter minimum 3 characters ');
	      return;
         }	
		 getUserDetails();
		}
	</script>
</body>
</html>