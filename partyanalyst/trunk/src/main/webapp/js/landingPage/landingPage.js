
var popupPanel=null;

function showDetailsPopup(dataType)
{
	var str = '';
	str += getContent(dataType);

	var elmt = document.getElementById("jQueryPopup_content");
	elmt.innerHTML = str;

	$( "#jQueryPopup" ).dialog({
			title	:dataType+" Details",
			autoOpen: true,
			show	: "blind",
			width	: 400,
			minHeight:300,
			modal	: true,
			hide	: "blind"
		});
}


function getContent(type)
{
	var str = '';
	if(type == "Pricing")
	{
		str += '<div class="contentPopup_main">';
		str += '<div class="contentPopup_body">';
		str += '<table>';
		str += '<tr><th colspan=2>For Pricing Information Contact Us @</th></tr>';
		str += '<tr><th>Phone :</th><td>+91-40-40124153</td></tr>';
		str += '<tr><th>Mail :</th><td>info@itgrids.com</td></tr>';
		str += '</table>';

		str += '<table align="center">';
		str += '<tr><td><img src="images/icons/homePage_new/itgrids_logo.gif" style="padding:20px;"></td></tr>';
		str += '</table>';

		str +=' </div>';
		str += '</div>';


	}

	return str;
}



function landingPageAjaxCall(jsObj,url)
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
								//alert( "Failed to load result" + o.status + " " + o.statusText);
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
		errSpanElmt.innerHTML = "The Username or Password you entered is incorrect.Please try again ...";
	else if(result == "true")
	{
		errSpanElmt.innerHTML = '';
		document.loginForm.submit();
	}
}

function testEnterKey(event)
{	
	if (event.keyCode == 13)
	{
		event.cancelBubble = true;
		event.returnValue = false;
		checkUserLogin();
	}
}

function buildLoginPopup()
{

	var str = '';
	str	+= '<div id="loginBoxMain">';
	str	+= '<div id="loginBoxDesc">Please Login To Know - Analyse - Act...</div>';
	str	+= '<div id="id="loginBoxFieldsMain">';
	str	+= '<form name="loginForm" action="index.action" onsubmit="return validateLogin();" method="post">';
	str	+= '<table id="loginDetailsTable" width="100%">';
	str	+= '<tr>';
	str	+= '<td colspan="2"><span id="errorMsgDiv"></span></td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<th align="left">* Username</th>';
	str	+= '<td><input id="userName" class="input_text" name="userName" type="text" onkeypress="testEnterKey(event)"/></td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<th align="left">* Password</th>';
	str	+= '<td><input id="password" class="input_text" name="password" onkeypress="testEnterKey(event)" type="password"/></td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<td colspan="2" align="right" style="padding: 20px 10px;">';
	//str	+= '<span class="loginButtonSpan" onclick="checkUserLogin()">Login</span>';
	//str	+= '<span class="labelSpan" onclick="closeLoginPopup()">Cancel<span/></td>';
	str	+= '<input type="button" class="loginButtonSpan" onclick="checkUserLogin()" value="submit"/>';
	str	+= '<input type="button" class="labelSpan" value="cancel" onclick="closeLoginPopup()"/>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<td colspan="2"  style="padding-left: 20px">';
	str	+= '<table id="accessPrivilegeTable" width="100%">';
	str	+= '<tr>';
	str	+= '<td colspan="3">For access privileges contact us @  </td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<th align="left">Phone </th><td>: </td><td> +91-40-40124153</td>';
	str	+= '</tr>';
	str	+= '<tr>';
	str	+= '<th align="left">Mail </th><td>: </td><td> license@itgrids.com</td>';
	str	+= '</tr>';
	str	+= '</table>';	
	str	+= '</td>';
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
	
	document.loginForm.userName.focus();
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
		landingPageAjaxCall(jsObj, url);
	}
}

function closeLoginPopup(id)
{
	popupPanel.hide();
}