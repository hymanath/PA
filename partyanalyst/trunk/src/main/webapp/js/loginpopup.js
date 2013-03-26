
function openDialogForLoginWindowForPostProblem(){
	$('#logInDiv').prev().find('a').trigger('click');
	$('#login').trigger('click');
}

function openDialogForLoginWindow(){
var str='';
str+='<div id="forgot_password_window1" style="background-color: #C7CFD2;">';
str+='<div id="forgot_password_window_inner1" style="font-size:0.8em"></div>';
str+='</div>';

str+='<div class="span3 well" style="border: 5px solid rgb(227, 227, 227); width: 360px;align:center">';
str+='<form name="loginForm" method="POST" onsubmit="javascript: ajaxCallForLoginPopup(); return false;">';
str+='<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 55px;">';
str+='</div>';
//str+='<div id="validate"></div>';
str+='<div class="input-prepend" style="margin-left: 55px;">';
str+='<span class="add-on"><i class="icon-envelope"></i></span><input type="text"  class="span2" class="input-small" placeholder="Email" name="userName"  id="userName1" style="width: 200px; position: static;"/>';
str+='</div>';
str+='<div  class="input-prepend" style="margin-left: 55px;">';
str+='<span class="add-on"><i class="icon-lock"></i></span><input type="password"  class="span2" class="input-small" placeholder="Password" name="password" style="width: 200px;position: static;" id="passWord_Id1"/>';
str+='</div>';
str+='<div class = "span3">';
str+='<a href="javascript:{}" onclick="showForgotPasswordPanelForPopup()" style="color: rgb(1, 116, 223); font-size: small; margin-left: 50px;">Forgot Password</a>';
str+='<input id="signin" class="submitButton btn btn btn-primary" type="submit" style="margin-left: 180px; margin-top: -20px; height: 25px; width: 85px; padding-top: 2px;" value="Sign In"/>';
str+='</div>';
str+='<div id="ajaxcallimage"  class = "span3" style="display:none;font-weight:bold;color: #0174DF;font-size:small;width: 345px; height: 17px;">';
str+='<font  style="font-size:small;">Sending Your Request. Please wait...</font>';
str+='<img src="images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>';
str+='</div>';
str+='</form><br><br>';
str+='</div>';

/*

str+='<div class="span3 well" style="border: 5px solid rgb(227, 227, 227); width: 360px;">';
str+='<h4 style="margin-bottom: 7px; margin-left: 55px;">New to Party Analyst?</h4>';
str+='<a href="freeUserRegistration.action" class="btn btn-success pull-right">Create an Account</a>';
str+='</div>';

*/


$("#login_window_inner").html(str);

	$("#login_window").dialog({
				resizable:false,
				title:'Please Login',
				height: 'auto',
				width:480,
				top:250,
				left:100,
				modal: true
				
	});
				
}

//ajax call 

function ajaxCallForLoginPopup(){

var validInd=handleErrorMessageForpopup();

if(!validInd)
	return false;

var userName1=document.getElementById('userName1').value;
var password1=document.getElementById('passWord_Id1').value;

task="validateUserForLogin";
 
 		
 var jsObj=
	 {
	//userName:userName1,
	//password:password1,
	task:task		
};
		//var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "loginPopUpsAction.action?&userName="+userName1+"&password="+password1+"&task="+task;	
		callHomePageAjax11(jsObj,url);
		$("#ajaxcallimage").show();

}

function ajaxComplete()
{
  document.loginForm.submit();
}

function callHomePageAjax11(jsObj,url){
	 
	var callback = {			
				   success : function( o ) {
					   
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "validateUserForLogin")
							{	
								if(myResults=="success"){
									$('#ajaxcallimage').html('Login Successfull,Page is refreshing Please wait...');
									window.location.reload();
								}
								
								else{
									//alert('else');
									$("#ajaxcallimage").hide();

									$("#LoginErrorMessageDiv").html('Invalid username or password! Please try again!');
								}
								
							}	
							
						}catch(e)
						{   

							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET',url, callback);



}


// starting validations for username and password

 function handleErrorMessageForpopup()
                { 
				
                 var emailId = document.getElementById("userName1").value;
                 var securityName = document.getElementById("passWord_Id1").value;
                 var errorDivEle = document.getElementById("LoginErrorMessageDiv");
	             var eFlag = false;

	             var str = '<font color="red">';

	              if(emailId.length == 0 || emailId == "Email" )
	               {
		              str += ' username is required<br>';
		              eFlag = true;
	               }
	
	              if(securityName.length == 0 || securityName =="Password")
	               {
		               str += ' password is required<br>';
		                eFlag = true;
	               }
	              	                str += '</font>';
	                errorDivEle.innerHTML = str;

	
	                if(eFlag){
		                return false;
					}
					return true;	
				}

// ending validations for username and password
// starting  password recovery
	function showForgotPasswordPanelForPopup()
   {
//alert("password");
	//document.getElementById("validate").style.display = 'none';
	$("#forgot_password_window1").dialog({
		autoOpen:false,
		resizable:false,
		width: 600,
		minHeight:200,
		show:'slide',
		modal:true
	});	
	 $("#forgot_password_window1").closest(".ui-dialog").find(".ui-dialog-titlebar").hide();

	var elmt = document.getElementById("forgot_password_window_inner1");

	var str = '';
	str += '<div id="feedback_window_head">Forgot Password ?</div>';
	str += '<div id="feedback_window_body">';
	str += '	<div id="feedBackNote_div">';
	str += '		<table>';
	str += '		<tr>';
	str += '		<td><img src="images/icons/infoicon.png"></td>';
	str += '		<td>Fields marked with (<font color="red">*</font>) are mandatory</td>';
	str += '		</tr>';
	str += '		</table>';
	str += '	</div>';
	str += '	<div id="feedBackForm_div">';
	str += '		<table id="feedbackTable" width="100%">';
	str += '		<tr>';
	str += '		<th><font color="red">*</font>UserName </th>';
	str += '		<td>';
	str += '			<input type="text" id="userName_FP" size="25"/>';
	str += '		</td>';
	str += '		</tr>';
	str += '		</table>';
	str += '	</div>';
	str += '</div>';
	str += '<div id="feedback_window_footer" class="yui-skin-sam">';
	str += '	<table width="100%">';
	str += '	<tr>';
	str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
	str += '	<td width="35%" align="right">';
	str += '		<input  style="text-align:center;" id="submitButton" type="button" value="Submit"></input>';
	str += '		<input style="text-align:center;" id="cancelButton" type="button" value="Cancel"></input>';
	str += '	</td>';
	str += '	</tr>';
	str += '	</table>';	
	str += '</div>';
	elmt.innerHTML = str;

	var oPushButton1 = new YAHOO.widget.Button("submitButton");  
	var oPushButton2 = new YAHOO.widget.Button("cancelButton");

	oPushButton1.on("click",function(){
		checkAvailability();
	});

	oPushButton2.on("click",function(){
		 
		$("#forgot_password_window1").dialog("destroy");
	});

	$("#forgot_password_window1").dialog("open");

}
// ending password recovery


function checkAvailability()
{
	//alert("checkAvailability");

	var name = document.getElementById("userName_FP").value;
 	
	if(name=="")
	{
		document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName field cannot be empty</font>";
 	}
	 else if (name.charAt(0).indexOf(" ")==0){
		 document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName Should not contain spaces</font>";
	 }
	 else if(name.length < 3)
	{
		document.getElementById("feedback_window_errorMsg").innerHTML = "<font color='red'>UserName must be between 6 and 20 characters long.</font>";
	}
	else{ 
		var str = '<font color="#000000">Sending Your Request. Please wait...</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
 		document.getElementById("feedback_window_errorMsg").innerHTML = str;
 		var jsObj=
		{		
 				userName:name,
				task:"forgotPassword",
		};	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "recoverPasswordAnanymousUserAction.action?"+rparam;						
		callAJAX1(jsObj,url);
 	 }
}

function callAJAX1(jsObj,url){
//alert("callAJAX1");
	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);
					if(jsObj.task == "saveUserEmailAndSendPwd")
					{
						
						showEmailStatus1(results);
						
					}
					else if(jsObj.task == "forgotPassword")
					{
						//alert("forgotPassword");
						showDetails1(results);
					}
					else if(jsObj.task == "recoverPassword")
					{
						showPasswordStatus1(results);
					}

					
			}catch (e) {   		
			   	//alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);//
	              }
	    };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
function showEmailStatus1(results)
{

	var errorDivEle = document.getElementById('ErrorMsgDivId');
	var str = '';
	//alert("hai");
	if(results.resultCode == 0)
	{
		clearEmailFields1();
		
	
		str += '<font color="#000000">Sending Your Request. Please wait...</font>';
		str += '<img src="images/icons/partypositions.gif" style="padding-left:10px;" width="18" height="11">'
		
	}
	else
		
		str += '<font color="red"><b>Error Ocuured, Try Again.</b></font>';

	errorDivEle.innerHTML = str;
	sendPasswordToUser1();
}

function clearEmailFields1()
{

	 document.getElementById('emailIdOfUser').value = '';

	
}

function sendPasswordToUser1()
{

	var usernameVal = document.getElementById('hiddenUserId').value;
	//var email = document.getElementById('textid').value;
	var jsObj=
	{
			userName  : usernameVal,
			task:"recoverPassword",

	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "sendPasswordAnanymousUserAction.action?"+rparam;						
		callAJAX1(jsObj,url);
 	 }

function showDetails1(results)
{	
	//alert("showDetails1");
	var result = document.getElementById("feedback_window_errorMsg");
	 str='';
	
	if(results == null){
		str+='<div style="color:red"> Your request not submitted. Please try again.</div>';
	}
	else if(results.userName == null){		
		str+='<div style="color:red"><b> Username Doesnot exist </b></div>';
	}
	else{
		$("#forgot_password_window1").dialog("destroy");
		afterPasswordSubmit1(results.userName,results.email);
		return;
	}
	result.innerHTML = str;
	
}

function afterPasswordSubmit1(username,email){

 $("#forgot_password_window1").dialog({
			resizable:false,
			width: 600,
			minHeight:150,
			show:'slide',
			modal:true
		});	
		//$(".ui-dialog-titlebar").hide();
		 $("#forgot_password_window1").closest(".ui-dialog").find(".ui-dialog-titlebar").hide();


		var elmt = document.getElementById("forgot_password_window_inner1");
		
		var str = '';
		str += '<div id="feedback_window_head">ForgotPassword?</div>';
		str += '<div id="feedback_window_body" style="font-weight:bold;color:green;text-align:center;">';
		
	if(email == null)
	{
		//alert("email == null");
		str +='<div id="ErrorMsgDivId"></div>';
		str += 'Enter your Email :';
		str +='<input id="emailIdOfUser" type="text">';
		str += '<input type="hidden" id="hiddenUserId" value="'+username+'">';
		//str +='<input type="button"  value="submit" onclick="saveEmailAndSendPassword()" style="width:70px;margin-left: 8px;">';
		
		//str +='<button type="button" onclick="clearDialogBox()" style="margin-left:6px;">Cancel</button>';
		
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr>';
		str += '	<td width="65%" align="left"><div id="feedback_window_errorMsg"></div></td>';
		str += '	<td width="35%" align="right">';
		str += '<input id="submitButtonId" type="button" value="Submit"></input>';
		str += '<input type="button" id="cancelButtonId" value="Cancel"></input>';
		str +='</td>';
		str +='</tr>';
		str +='</div>';
		
		elmt.innerHTML = str;
		var oPushButton2 = new YAHOO.widget.Button("cancelButtonId");
		var oPushButton1 = new YAHOO.widget.Button("submitButtonId");
		
		oPushButton1.on("click",function(){
			saveEmailAndSendPassword1();
		});
		
		oPushButton2.on("click",function(){
			//$(".ui-dialog-titlebar").show();
			$("#forgot_password_window1").dialog("destroy");
		});
		
		
	}
	else
	{
		str += 'Your password has been mailed to your email address :'+email+'</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr><td>';
		str += '	<input id="OkButton" type="button" width="50px" align="center"' ;
		str += '   value="OK"></input></td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton2 = new YAHOO.widget.Button("OkButton");

		oPushButton2.on("click",function(){
			$("#forgot_password_window1").dialog("destroy");
		});
		
		}
}

function saveEmailAndSendPassword1()
{
	var errorDivEle = document.getElementById('ErrorMsgDivId');

	var username = document.getElementById('hiddenUserId').value;
	var email = document.getElementById('emailIdOfUser').value;
	var eFlag = false;
	var str = '<font color="red">';
	if(email.length == 0)
	{
		str +='<b>enter Email</b>';
		eFlag = true;
	}
	var email = document.getElementById("emailIdOfUser").value;
		var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
      if(email !='' && email!='your email'){
          
		  if(!email.match(emailExp)){

				document.getElementById("ErrorMsgDivId").innerHTML = '<font color="red">Please enter valid Email</font>';
				return;
		  }
	  }
	 else {
		document.getElementById("ErrorMsgDivId").innerHTML ='<font color="red">Please enter Email id</font>';  
		return;
	 }

	str += '</font>';

	errorDivEle.innerHTML = str;
	if(eFlag)
		return;

	var jsObj = {

		userName : username,
		email : email,
		task : "saveUserEmailAndSendPwd",
	};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "saveUserEmailAction.action?"+rparam;						
	callAJAX1(jsObj,url);

}



function showPasswordStatus1(results)
{
	
	var errorDivEle = document.getElementById('ErrorMsgDivId');
	var str='';

	if(results == null){
		str+='<div style="color:red"> Your request not submitted. Please try again.</div>';
	}
	else if(results.userName == null){		
		str+='<div style="color:red"><b> Username Doesnot exist </b></div>';
	}
	else{
		//$("#forgot_password_window").dialog("destroy");
		afterPasswordSubmitToUser1(results.userName,results.email);
		return;
	}
	errorDivEle.innerHTML = str;
	
}




function afterPasswordSubmitToUser1(username,email){
	
		var elmt = document.getElementById("forgot_password_window_inner1");
		var username = document.getElementById("");


		var str = '';
		str += '<div id="feedback_window_head">ForgotPassword?</div>';
		str += '<div id="feedback_window_body" style="font-weight:bold;color:green;text-align:center;">';
		
		str += 'Your password has been mailed to your email address :'+email+'</div>';
		str += '</div>';
		str += '<div id="feedback_window_footer" class="yui-skin-sam">';
		str += '	<table width="100%">';
		str += '	<tr><td>';
		str += '	<input id="OkButton" type="button" width="50px" align="center"' ;
		str += '   value="OK"></input></td>';
		str += '	</tr>';
		str += '	</table>';	
		str += '</div>';
		elmt.innerHTML = str;

		var oPushButton2 = new YAHOO.widget.Button("OkButton");

		oPushButton2.on("click",function(){
			$("#forgot_password_window1").dialog("destroy");
		});
}


