<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <title>SocialAuth Demo</title>
        <style>
                .sectiontableheader {background-color:#C8D7E3;color:#293D6B;font-size:8pt;font-weight:bold;padding:2px;}
                .sectiontableentry2 {background:none repeat scroll 0 0 #F7F7F7;padding:2px;}
                .sectiontableentry1 {background:none repeat scroll 0 0 #FFFFF0;padding:2px;}
        </style>
		<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
		<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery-ui-1.8.5.custom.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.core.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.blind.min.js"></script>
<script src="js/jQuery/development-bundle/ui/jquery.effects.explode.min.js"></script>

<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="js/jQuery/development-bundle/ui/jquery.ui.accordion.js"></script>

<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.core.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.theme.css"/>
<link  rel="stylesheet" type="text/css" href="js/jQuery/development-bundle/themes/base/jquery.ui.accordion.css"/>

<!-- JQuery files (End) -->

        <script>
        function updateStatus(){
                var btn = document.getElementById('btnUpdateStatus');
                btn.disabled=true;
                        var msg = prompt("Enter your status here:");
                        if(msg == null || msg.length == 0){
                                btn.disabled=false;
                        return false;
                }
                        msg = "statusMessage="+msg;
                        var req = new XMLHttpRequest();
                        req.open("POST", "<%=request.getContextPath()%>/socialAuthUpdateStatusAction.do");
                        req.setRequestHeader("Accept", "text/xml");
                        req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        req.setRequestHeader("Content-length", msg.length);
                        req.setRequestHeader("Connection", "close");
                        req.onreadystatechange = function () {
                                if (req.readyState == 4) {
                                        if(req.responseText.length > 0) {
                                                        alert(req.responseText);
                                                        btn.disabled=false;
                                        }
                                }
                        };
                        req.send(msg);
        }
        </script>
</head>
<body>
<%@page import="org.brickred.socialauth.Profile,java.util.*;" %>
<%@ page isELIgnored="false"%>
<!--<h2 align="center">Authentication has been successful.</h2>
<br/>
<div align="center"><a href="index.jsp">Back</a></div>
<br />
<h3 align="center">Profile Information</h3>
<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5" width="60%" align="center">
        <tr class="sectiontableheader">
                <th>Profile Field</th>
                <th>Value</th>
        </tr>
        <tr class="sectiontableentry1">
                <td>Email:</td>
                <td><c:out value="${profile.email}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>First Name:</td>
                <td><c:out value="${profile.firstName}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Last Name:</td>
                <td><c:out value="${profile.lastName}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Country:</td>
                <td><c:out value="${profile.country}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Language:</td>
                <td><c:out value="${profile.language}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Full Name:</td>
                <td><c:out value="${profile.fullName}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Display Name:</td>
                <td><c:out value="${profile.displayName}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>DOB:</td>
                <td><c:out value="${profile.dob}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Gender:</td>
                <td><c:out value="${profile.gender}"/></td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Location:</td>
                <td><c:out value="${profile.location}"/></td>
        </tr>
        <tr class="sectiontableentry1">
                <td>Profile Image:</td>
                <td>
                        <c:if test="${profile.profileImageURL != null}">
                                <img src='<c:out value="${profile.profileImageURL}"/>'/>
                        </c:if>
                </td>
        </tr>
        <tr class="sectiontableentry2">
                <td>Update status:</td>
                <td>
                        <input type="button" value="Click to Update Status" onclick="updateStatus();" 
            id="btnUpdateStatus"/>              
                </td>
        </tr>
</table>-->
<h1 align="center">Contact Details</h1>
<div style="align:left"><input type="button" value="Send" onclick="SendingMailsToFriends();"/></div>
<table cellspacing="1" cellspacing="4" border="0" bgcolor="e5e5e5" align="center" width="60%">
        <tr class="sectiontableheader">
            <th width="5%"><input type="checkbox" checked id="slctall" onclick="checkAll(this.id)"/></th>
                <th width="15%">Name</th>
                <th width="25%">Email</th>
               <!-- <th>Profile URL</th>--> 
        </tr>
        <%int recordsCount = 0; %>
        <c:forEach var="contact" items="${contactsList}" varStatus="index">
             <tr class='<c:if test="${index.count % 2 == 0}">sectiontableentry2</c:if>
        <c:if test="${index.count % 2 != 0}">sectiontableentry1</c:if>'>
                        <td><input type="checkbox" checked name="contactCheckBox<%=recordsCount%>"  id="contactCheckBox<%=recordsCount%>" onclick="preparingdetails(this.id,'<%=recordsCount%>')"></td>
                        <td><c:out value="${contact.firstName}"/> <c:out value="${contact.lastName}"/></td>
                        <td><c:out value="${contact.email}"/></td>
                        <!--<td>
                <a href='<c:out value="${contact.profileUrl}"/>' target="_new">
                    <c:out value="${contact.profileUrl}"/>
                </a>
            </td>-->
                </tr>
				<input type="hidden" id="firstName_<%=recordsCount%>" value="${contact.firstName}" ></input>
				<input type="hidden" id="personEmail_<%=recordsCount%>" value="${contact.email}" ></input>
				  <%recordsCount ++; %>
        </c:forEach>
</table>
<center> <input type="button" value="Send" onclick="SendingMailsToFriends();"/></center>
<div id="mailsuccessPopup"><div id="mailsuccessPopupcontent"></div></div>
<script type="text/javascript">
var invmainobj=[];
checkAll('slctall');
function checkAll(slctallid){
	 if(document.getElementById(slctallid).checked==true){
		 var count=0;
	   <c:forEach var="contact" items="${contactsList}" varStatus="index">
     var chkid="contactCheckBox"+count;
	   if(document.getElementById(chkid)!=null){
	   document.getElementById(chkid).checked=true;
	     var invObj={firstName:'',
	 email:''	
};
for(var i=0;i<invmainobj.length;i++)	{
		if(invmainobj[i].firstName== '${contact.firstName}' && invmainobj[i].email=='${contact.email}'){
			invmainobj.splice(i,1);
		}
		
		}
	   invObj.firstName='${contact.firstName}';
	invObj.email='${contact.email}';
		invmainobj.push(invObj);
	   }
	 
	   count++;
	 </c:forEach>
	 }else{
var count=0;
	   <c:forEach var="contact" items="${contactsList}" varStatus="index">
     var chkid="contactCheckBox"+count;
	   if(document.getElementById(chkid)!=null){
	   document.getElementById(chkid).checked=false;
	   for(var i=0;i<invmainobj.length;i++)	{
		if(invmainobj[i].firstName== '${contact.firstName}' && invmainobj[i].email=='${contact.email}'){
			invmainobj.splice(i,1);
		}
		
		}
	   }
	   count++;
	 </c:forEach>
	 }
	 }
	 function preparingdetails(elmt,index){
	
	 if(document.getElementById(elmt).checked==true)	{
			var invObj={firstName:'',
		 email:''
	 };
	 invObj.firstName= document.getElementById("firstName_"+index).value;
		invObj.email= document.getElementById("personEmail_"+index).value;
			invmainobj.push(invObj);

		}
		else{
			document.getElementById("slctall").checked=false;
			for(var i=0;i<invmainobj.length;i++)	{
			if(invmainobj[i].firstName== document.getElementById("firstName_"+index).value && invmainobj[i].email==document.getElementById("personEmail_"+index).value){

				invmainobj.splice(i,1);
				break;
			}
			
			}
	    
	}
	}
	function SendingMailsToFriends(){
	var jsObj=
	{		
			invmainobj:invmainobj,
			task:"sendingMails"								
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "referralSendingMailsAction.action?"+rparam;					
	callAjax(jsObj,url);
	}
 function callAjax(jsObj,url){
	var results;	
	var callback = {			
	    success : function( o ) {
			try {											
					results = YAHOO.lang.JSON.parse(o.responseText);		
				//	showresults(results);
			$( "#mailsuccessPopup" ).dialog({
			title:"Refer Friends",
			autoOpen: true,
			show: "blind",
			width: "auto",
			minHeight:"auto",
			modal: true,
			hide: "explode",
				buttons: {
									"AddBtn": {
										id: "addBtnId",
										text: "Ok",
										click: function(){
											window.close();
										}
									}
			}
		});
	
	var elmt = document.getElementById("mailsuccessPopupcontent");
	if(elmt)
		elmt.innerHTML = "Successfully Sent";
			}catch (e) {   		
			   	//alert("Invalid JSON result" + e);   
			}  
	    },
	    scope : this,
	    failure : function( o ) {
	     			//alert( "Failed to load result" + o.status + " " + o.statusText);
	              }
	    };

	YAHOO.util.Connect.asyncRequest('POST', url, callback);
}
</script>
</body>

</html>
