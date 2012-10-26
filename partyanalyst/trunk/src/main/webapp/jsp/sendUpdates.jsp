<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>


<title>Send Updates to Mobile</title>
<SCRIPT type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></SCRIPT>
<SCRIPT type="text/javascript" src="js/AddNewProblem/addNewProblem.js"></SCRIPT>

<!--<SCRIPT type="text/javascript" src="js/AddNewProblem/addFileInput.js"></SCRIPT>-->

<LINK rel="stylesheet" type="text/css" href="styles/addNewProblem/addNewProblem.css">
<link rel="SHORTCUT ICON" type="image/x-icon" href="images/icons/homePage/faviIcon.jpg">
 <link href="calendar.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	 
	<!-- YUI Skin Sam -->

	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<style type="text/css">
#descTextArea{background:#ffffff;margin-left:365px;}

.questionHeader {
    background-image: url("images/icons/cadreReport/bg_center.png");
    background-repeat: repeat-x;
    color: #FFFFFF;
    font-size: 14px;
    font-weight: bold;
    height: 24px;
    padding-top: 1px;
    text-align: center;
     width: 210px;
    margin-left: 396px;
    border-radius: 3px;
}
</style>
<script type="text/javascript">
var dataArr = new Array();


function limitText(limitField, limitCount, limitNum)
{	
	
	var limitFieldElmt = document.getElementById(limitField);
	var limitCountElmt = document.getElementById(limitCount);

	if (limitFieldElmt.value.length > limitNum) 
	{
		limitFieldElmt.value = limitFieldElmt.value.substring(0, limitNum);			
	}
	else
	{			
		limitCountElmt.innerHTML = limitNum - limitFieldElmt.value.length+"";
	}
}

function selectCheckBox()
{
	
	var elements = document.getElementsByName("userCheckBox");
	
	for(var i in elements)
	{
		elements[i].checked = true;
	}	
}

	function deSelectCheckBox()
	{
	
	var elements = document.getElementsByName("userCheckBox");
	
	for(var i in elements)
	{
		elements[i].checked = false;
	}
	}
	function hideDivForSelectedUSer()
	{
		document.getElementById('descTextArea').value='';
		document.getElementById('errorDiv').innerHTML='';
	}
	function hideDiv()
	{
	document.getElementById('table').innerHTML = '';
	document.getElementById('descTextArea').value='';
	document.getElementById('errorDiv').innerHTML='';
	}

	function sendSmsForSelectedUser()
	{

	var elements = document.getElementsByName("userCheckBox");
	var errorEle = document.getElementById('errorDiv');
	selectedUsersArray = [];
	/*var radioElmt = document.getElementsByName("user");
	for(var i=0;i<radioElmt.length;i++){
				if(radioElmt[i].checked == true){
					radioElmt = radioElmt[i].value;					
				}}
		if(radioElmt == "All")
		{
	
		 sendSMSForALLUSers();
		}*/
		var radioElmt = document.getElementById("radioButtionId");
		if(radioElmt.checked == true)
		{
	
		 sendSMSForALLUSers();
		}
	else
	{

	if(!elements || !errorEle)
		return;
	label:for(var i=0;i<elements.length;i++)
	{
		if(elements[i].checked == true)
			
		{

		//var userId  = elements[i].value;
		var mobile = elements[i].value;
		for(var j=0;j<selectedUsersArray.length;j++)
			{
		if(selectedUsersArray[j]==mobile)
			continue label;
			}
		//var count = mobile.length;
		/*var obj = {
					//count:count,
					mobile:mobile
					
		          };*/
		
		
		selectedUsersArray.push(mobile);
		}
	}
	
if(selectedUsersArray.length == 0)
	{
		errorEle.innerHTML = '<font color="red">Atleast One user need to selected to send SMS</font>';
		return;
	}
	
		//var count =selectedUsersArray.length(); 
	
		sendSms();
	}
}

function sendSms()
{	

	var textAreaElmt = document.getElementById("descTextArea");
	var errorEle = document.getElementById('errorDiv');
	var str ='';

	
	if(textAreaElmt)
		var txtElmtValue = textAreaElmt.value;
	
	if(txtElmtValue.length == 0)
	{
	errorEle.innerHTML='<font color="red">message should not be empty</font>';
	return;
	}
	if(textAreaElmt.value.trim() == "")
	{
	errorEle.innerHTML='<font color="red">message should not be empty</font>';
	return;
	}

	var jsObj=
		{						
				txtAreaValue:txtElmtValue,
				
				userIds:selectedUsersArray,
				task:"sendSMSForUserIds"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendSMSForSelectedUsersAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function sendSMSForALLUSers()
{

var textAreaElmt = document.getElementById("descTextArea");
var errorEle = document.getElementById('errorDiv');
var str ='';


	if(textAreaElmt)
		var txtElmtValue = textAreaElmt.value;

	if(txtElmtValue.length == 0)
	{
	errorEle.innerHTML='<font color="red">message should not be empty</font>';
	return;
	}
	if(textAreaElmt.value.trim() == "")
	{
	errorEle.innerHTML='<font color="red">message should not be empty</font>';
	return false;
	}
		var jsObj=
		{						
				txtAreaValue:txtElmtValue,
				
				
				task:"sendSMSForALLUSers"
		}

		
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendSMSForALLUsersAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function callAjax(jsObj,url)
{
	var callback = {			
				   success : function( o ) {
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);
							if(jsObj.task == 'sendSMSForUserIds')
							{
								showSmsStatus(myResults);
							}	
							
						}
						catch(e)
						{   
							//alert("Invalid JSON result" + e);  
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}



function sendSmsToUsersTable()
	{
	
    var resultsColumnDefs = [
		{
		key : "checkBox",
		label : "Select",
		sortable : true
	}, 

	{
		key : "userName",
		label : "userName",
		sortable : true

	},
		{
		key : "mobile",
		label : "mobile",
		sortable : true

	},
		{
		key : "constituency",
		label : "constituency",
		sortable : true

	}
	];
	 var myConfigs = {    
				
						paginator : new YAHOO.widget.Paginator({ 
						rowsPerPage    : 10,		        
						template: "{PageLinks} Show {RowsPerPageDropdown} Rows Per Page",
						rowsPerPageOptions: [10,20,30], 
						pageLinks: 10
						})
						
					};	

					var myDataSource = new YAHOO.util.DataSource(dataArr);
					myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "checkBox","userName" , "mobile" ,  "constituency"]
					};

				
	var myDataTable = new YAHOO.widget.DataTable("table",resultsColumnDefs, myDataSource,myConfigs);  
}
function showSmsStatus(result)
{
	
	var errorEle = document.getElementById('errorDiv');
	
	if(result == null)
	{
		errorEle.innerHTML='<font color="green">message send successfully</font>';
		return;
	}
errorEle.innerHTML ="";
}

</script>
</head>
<body>
<div style="width:998px;background:#ffffff;margin-left:auto;margin-right:auto;">
<br>
<div class="questionHeader">
<span style="margin-top:2px;">Send Updates to Mobile</span>
</div>
	<!--<h3 style="text-align:center;font-family:verdana,arial,san-serif;">Send Updates to Mobile</h3>--><br>
		<div id="selectDiv" style="margin-left:395px;">
			<span>Select Users 
			</span> : 
<input type="radio" id="radioButtionId" name="user" value="All" 
checked="checked" onclick="hideDiv()"/>&nbsp;All
<input type="radio" name="user" onclick="sendSmsToUsersTable(),hideDivForSelectedUSer()" value="Selected User"
/>&nbsp;Selected User
		</div><br>
		<div id="commentsData_outer" class="yui-skin-sam">
		<div id="table">
		</div>
		</div>
	<div id="errorDiv" style="margin-left:372px;"></div>
		<div id="textarea">
			<s:textarea cols="40" id="descTextArea" theme="simple" onkeyup="limitText('descTextArea','maxcount',200)"/>
					
		</div>
		<div id="buttonsDiv" style="margin-left: 686px;
		margin-top: -26px;">
		
<input type="button" style="width:75px;height:30px;
background:#3E678F;border-radius:5px;color:#ffffff;" value="Send" onclick="sendSmsForSelectedUser();"/>
	</div>
		
		<div id="limitDiv">
									<table style="width:100%;"><tr>
										<td style="width:50%;"><div id="remainChars" style="margin-left:266px;">
										<span id="maxcount">200 </span> <span>chars remaining..</span></div></td>
										<td style="width:50%;"><div>Should not exceed 200 chars</div></td>
									</tr></table>
								</div>	
		


<script type="text/javascript">

	
	<c:forEach var="userDetails" items="${regVO}">
	var obj = {

					checkBox:"<input type='checkbox' name='userCheckBox' value='${userDetails.mobile}'>",
					
					userName : "${userDetails.name}",
					mobile:"${userDetails.mobile}",
					constituency:"${userDetails.constituency}"
	};
	dataArr.push(obj);
	</c:forEach>
	
//sendSmsToUsersTable();
//selectCheckBox();

</script>
</body>

</html>