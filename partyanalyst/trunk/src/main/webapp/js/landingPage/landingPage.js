
var popupPanel=null;


function callAjax(jsObj,url)
{			
	
	var callback = {			
				   success : function( o ) {
						try {
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							if(jsObj.task == "userLogin")
								submitResult(myResults);								
									
						}catch (e) {   
							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function submitResult(result)
{
	var errSpanElmt = document.getElementById("errorMsgDiv");

	if(!errSpanElmt)
		return;

	if(result == "false")
		errSpanElmt = "The UserName and Password are Incorrect.Please try again ...";
	else if(result == "true")
	{
		errSpanElmt.innerHTML = '';
		document.loginForm.submit();
	}
}

function buildLoginPopup()
{

	var str = '';
	str	+= '<div id="loginBoxMain">';
	str	+= '<div id="loginBoxDesc">Please Login To Analyse ...</div>';
	str	+= '<div id="id="loginBoxFieldsMain">';
	str	+= '<form name="loginForm" action="index.action" method="post">';
	str	+= '<table id="loginDetailsTable" width="100%">';
	str	+= '<tr>';
	str	+= '<td colspan="2"><span id="errorMsgDiv"></span></td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<th align="left">* User Name</th>';
	str	+= '<td><input id="userName" class="input_text" name="userName" type="text"/></td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<th align="left">* Password</th>';
	str	+= '<td><input id="password" class="input_text" name="password" type="password"/></td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<td colspan="2" align="right" style="padding: 20px 10px;"><span class="loginButtonSpan" onclick="checkUserLogin()">Login</span>';
	str	+= '<span class="labelSpan" onclick="closeLoginPopup()">Cancel<span/></td>';
	str	+= '</tr>';
	str	+= '</table>';
	str	+= '</form>';
	str	+= '</div>';
	str	+= '</div>';

	popupPanel = new YAHOO.widget.Dialog("loginPopupDiv", {      
				 width:'350px',
                 fixedcenter : true, 
                 visible : true,
                 constraintoviewport : true, 
        		 iframe :false,
        		 modal :false,
        		 hideaftersubmit:true,
        		 close:false,
				 draggable:false
       });	 
	
    popupPanel.setBody(str);
    popupPanel.render();

}

function checkUserLogin()
{
	var uNameElmt = document.getElementById("userName");
	var pwdElmt = document.getElementById("password");

	var errSpanElmt = document.getElementById("errorMsgDiv");

	if(!uNameElmt || !errSpanElmt || !pwdElmt)
		return;

	var uName = uNameElmt.value;
	var pwd = pwdElmt.value;

	if(uName == "" || pwd == "")
	{
		errSpanElmt.innerHTML = "Fields marked with * are compulsory";
		return;
	}
	else
	{
		errSpanElmt.innerHTML = '';
		var jsObj=
		{
				task:"userLogin",
				userName:uName,
				password:pwd
		}
	
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);						
		var url = "userRegistrationInLandingAction.action?"+rparam;
		callAjax(jsObj, url);
	}
}

function closeLoginPopup(id)
{
	popupPanel.hide();
}