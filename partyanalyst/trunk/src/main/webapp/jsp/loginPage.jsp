<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	

<%
String src = "";
//Problem Management
String redirectLoc = "";
String task = "";
String name = "";
String stateId = "";
String districtId = "";
String localBodyId = "";
String constituencyId = "";
String localBodyElectionTypeId = "";
String districtName = "";
String constituencyName = "";
String url = "";

String electionId = "";
String stateID = "";
String electionType = "";
String electionTypeId = "";
String stateName = "";
String year = "";

String parliamentConstiId = "";
String taskType = "";
String problemHistoryId = "";

String electionId1 = "";
String electionId2 = "";
String party = "";
String allianceCheck = "";

String selectedPartyName="";

String selectedPartyShortName= "";
String selectedPartyId= "";
String selectedElectionTypeName= "";
String selectedLocationName= "";
String reportLevel= "";
String stateSelectName= "";
String partySelectName= "";
String constituencySelectName= "";
String districtSelectName= "";
String candidateId = "";
String showMessage = "";


if(request.getParameter("candidateId")!=null){
	candidateId = request.getParameter("candidateId");
}
if(request.getParameter("districtSelectName")!=null){
	districtSelectName = request.getParameter("districtSelectName");
}
if(request.getParameter("selectedPartyShortName")!=null){
	selectedPartyShortName = request.getParameter("selectedPartyShortName");
}

if(request.getParameter("selectedPartyId")!=null){
	selectedPartyId = request.getParameter("selectedPartyId");
}

if(request.getParameter("selectedElectionTypeName")!=null){
	selectedElectionTypeName = request.getParameter("selectedElectionTypeName");
}

if(request.getParameter("selectedLocationName")!=null){
	selectedLocationName = request.getParameter("selectedLocationName");
}
if(request.getParameter("reportLevel")!=null){
	reportLevel = request.getParameter("reportLevel");
}

if(request.getParameter("stateSelectName")!=null){
	stateSelectName = request.getParameter("stateSelectName");
}

if(request.getParameter("partySelectName")!=null){
	partySelectName = request.getParameter("partySelectName");
}

if(request.getParameter("constituencySelectName")!=null){
	constituencySelectName = request.getParameter("constituencySelectName");
}

if(request.getParameter("selectedPartyName")!=null){
	selectedPartyName = request.getParameter("selectedPartyName");
}
if(request.getParameter("electionId1")!=null){
	electionId1 = request.getParameter("electionId1");
}
if(request.getParameter("party")!=null){
	party = request.getParameter("party");
}

if(request.getParameter("allianceCheck")!=null){
	allianceCheck = request.getParameter("allianceCheck");
}

if(request.getParameter("electionId2")!=null){
	electionId2 = request.getParameter("electionId2");
}


if(request.getParameter("parliamentConstiId")!=null){
	parliamentConstiId = request.getParameter("parliamentConstiId");
}
if(request.getParameter("taskType")!=null){
	taskType = request.getParameter("taskType");
}
if(request.getParameter("electionId")!=null){
	electionId = request.getParameter("electionId");
}
if(request.getParameter("stateID")!=null){
	stateID = request.getParameter("stateID");
}
if(request.getParameter("electionType")!=null){
	electionType = request.getParameter("electionType");
}
if(request.getParameter("electionTypeId")!=null){
	electionTypeId = request.getParameter("electionTypeId");
}
if(request.getParameter("stateName")!=null){
	stateName = request.getParameter("stateName");
}
if(request.getParameter("year")!=null){
	year = request.getParameter("year");
}
if(request.getParameter("src")!=null){
	src = request.getParameter("src");
}

if(request.getParameter("url")!=null){
	url = request.getParameter("url");
}
		
src = request.getParameter("src");

if(request.getParameter("redirectLoc")!=null){
	redirectLoc = request.getParameter("redirectLoc");
}

if(request.getParameter("task")!=null){
	task = request.getParameter("task");
}

if(request.getParameter("name")!=null){
	name = request.getParameter("name");
}

if(request.getParameter("stateId")!=null){
	stateId = request.getParameter("stateId");
}

if(request.getParameter("districtId")!=null){
	districtId = request.getParameter("districtId");
}

if(request.getParameter("localBodyId")!=null){
	localBodyId = request.getParameter("localBodyId");
}

if(request.getParameter("constituencyId")!=null){
	constituencyId = request.getParameter("constituencyId");
}

if(request.getParameter("localBodyElectionTypeId")!=null){
	localBodyElectionTypeId = request.getParameter("localBodyElectionTypeId");
}

if(request.getParameter("districtName")!=null){
	districtName = request.getParameter("districtName");
}

if(request.getParameter("constituencyName")!=null){
	constituencyName = request.getParameter("constituencyName");
}

if(request.getParameter("problemHistoryId")!=null){
	problemHistoryId = request.getParameter("problemHistoryId");
}
if(request.getParameter("showMessage")!=null){
	showMessage = request.getParameter("showMessage");
	
}
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


			<style>
				.background{background: url("./images/new_homepage/js-banner-bg1.jpg")}
			</style>
		     <div class="span3 well" style="border: 5px solid #E3E3E3;">
				     
				     <div id = "getLoginErrorMessageDiv" style="color:red;">
					     <s:actionerror />
					 
					 </div>
				       
				   
		            <form name="loginForm" action="loginAction.action" method="POST" cssClass="form-horizontal" onSubmit="return handleErrorMessage();">
		                    <%
							if(src != ""){
							%>
							<input type="hidden" name="src" value="<%=src %>" />
							<% } %>
							<input type="hidden" name="url" value="<%=url %>" />
							<input type="hidden" name="electionId" value="<%=electionId %>" />
							<input type="hidden" name="stateID" value="<%=stateID %>" />
							<input type="hidden" name="electionType" value="<%=electionType %>" />
							<input type="hidden" name="electionTypeId" value="<%=electionTypeId %>" />
							<input type="hidden" name="stateName" value="<%=stateName %>" />
							<input type="hidden" name="year" value="<%=year %>" />
							<input type="hidden" name="redirectLoc" value="<%=redirectLoc %>" />
							<input type="hidden" name="task" value="<%=task %>" />
							<input type="hidden" name="name" value="<%=name %>" />
							<input type="hidden" name="stateId" value="<%=stateId %>" />
							<input type="hidden" name="districtId" value="<%=districtId %>" />
							<input type="hidden" name="localBodyId" value="<%=localBodyId %>" />
							<input type="hidden" name="constituencyId" value="<%=constituencyId %>" />
							<input type="hidden" name="localBodyElectionTypeId" value="<%=localBodyElectionTypeId %>" />
							<input type="hidden" name="districtName" value="<%=districtName %>" />
							<input type="hidden" name="constituencyName" value="<%=constituencyName %>" />
							<input type="hidden" name="taskType" value="<%=taskType %>" />
							<input type="hidden" name="parliamentConstiId" value="<%=parliamentConstiId %>" />
							<input type="hidden" name="problemHistoryId" value="<%=problemHistoryId%>" />
							<input type="hidden" name="electionId1" value="<%=electionId1%>" />
							<input type="hidden" name="electionId2" value="<%=electionId2%>" />
							<input type="hidden" name="party" value="<%=party%>" />
							<input type="hidden" name="allianceCheck" value="<%=allianceCheck%>" />
							<input type="hidden" name="selectedPartyName" value="<%=selectedPartyName%>" />
							<input type="hidden" name="selectedPartyShortName" value="<%=selectedPartyShortName%>"/>
							<input type="hidden" name="selectedPartyId" value="<%=selectedPartyId%>"/>
							<input type="hidden" name="selectedElectionTypeName" value="<%=selectedElectionTypeName%>"/>
							<input type="hidden" name="selectedLocationName" value="<%=selectedLocationName%>"/>
							<input type="hidden" name="reportLevel" value="<%=reportLevel%>"/>
							<input type="hidden" name="stateSelectName" value="<%=stateSelectName%>"/>
							<input type="hidden" name="partySelectName" value="<%=partySelectName%>"/>
							<input type="hidden" name="constituencySelectName" value="<%=constituencySelectName%>"/>
							<input type="hidden" name="districtSelectName" value="<%=districtSelectName%>"/>
							<input type="hidden" name="candidateId" value="<%=candidateId%>"/>
								
							<c:out value="${sessionScope.USER_REG_SUCCESS}" />
							<c:remove var="USER_REG_SUCCESS" scope="session" />               
		       
        	             
			             <div id="validate"></div>
						 <div class="input-prepend">
                               <span class="add-on"><i class="icon-envelope"></i></span><input type="text" class="span2" class="input-small" placeholder="Email" name="userName"  id="userName" style="width: 176px;" required>
                         </div>
						 <div class="input-prepend">
                               <span class="add-on"><i class="icon-lock"></i></span><input type="password" class="span2" class="input-small" placeholder="Password" name="password" style="width: 176px;" id="passWord_Id" required>
                         </div>
                            
			                <span class="divider"></span>
			              <div class = "span3">
                             <a href="javascript:{}" onclick="showForgotPasswordPanel()" style="color:#0174DF;" >Forgot Password</a>
			                 <input id="" class="submitButton btn btn btn-primary" type="submit" style="margin-left: 28px;" value="Sign In">
			              </div>
						  
						  
			
                     </form>
		
			</div>
			<script type="text/javascript">
			    function handleErrorMessage()
                { 
                    //document.getElementById("getLoginErrorMessageDiv").innerHTML = '';
				 
                 var emailId = document.getElementById("userName").value;
                 var securityName = document.getElementById("passWord_Id").value;
                 var errorDivEle = document.getElementById("getLoginErrorMessageDiv");
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
	
	                if(eFlag)
		                return false;
						
					return true;
						  
                }
	function showForgotPasswordPanel()
   {

	document.getElementById("validate").style.display = 'none';
	$("#forgot_password_window").dialog({
		resizable:false,
		width: 600,
		minHeight:200,
		show:'slide',
		modal:true
	});	
	$(".ui-dialog-titlebar").hide();

	var elmt = document.getElementById("forgot_password_window_inner");

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
		$("#forgot_password_window").dialog("destroy");
	});

}
function callAJAX(jsObj,url){

	var results;	
	var callback = {			
	    success : function( o ) {
			try {							
				"",					
					results = YAHOO.lang.JSON.parse(o.responseText);
					if(jsObj.task == "saveUserEmailAndSendPwd")
					{
						
						showEmailStatus(results);
						
					}
					else if(jsObj.task == "forgotPassword")
					{
						showDetails(results);
					}
					else if(jsObj.task == "recoverPassword")
					{
						showPasswordStatus(results);
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



			
	</script>  
		

 