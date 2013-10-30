<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
<TITLE>Party Analyst - Customer Contact Details</TITLE>
  <script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js"></script>
 <script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.js"></script>
 <!--<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.min.js"></script>-->
  <!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>-->
<link rel="stylesheet" href="/resources/demos/style.css" />
<!--<link rel="stylesheet" href="styles/jqueryDataTable/css/datatable.css" />-->
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_page.css" />
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_table.css" />
<link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css">
 <script type="text/javascript" src="http://www.google.com/jsapi"></script>
 <script type="text/javascript" src="js/googleAnalytics/googleChartsColourPicker.js"></script>
 <script type="text/javascript" src="js/suggestiveModel.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>


	<!-- YUI Dependency files (End) -->


<style>
.requiredFont{
		color:red;
	}
select {
    background-color: #FFFFFF;
    border: 1px solid #CCCCCC;
    width: 200px;
}

select, textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input,#fromDate,#toDate {
    border-radius: 4px ;
	color: #000000;
    display: inline-block;
    font-size: 13px;
    line-height: 18px;
    padding: 4px;
}

#mainDiv{
	font-weight:bold;
	border:solid  #33ccff;
	float: none;
    margin-left: auto;
    margin-right: auto;
    width: 950px;
	margin-top: 20px;
	margin-bottom:20px;
	padding: 15px;
	border-radius:5px;
	}
	#heading{
	color: white;
	width: 900px;
	margin: 10px; 
	border: medium solid #33ccff; 
	border-radius:5px;
	background-color: #33ccff;
	}
</style>
</HEAD>
<script>
$('document').ready(function(){
	$('#customerList').change(function(){
			$('#statusDiv').html('');
	});
	$('#contactNo').keydown(function(){
			$('#statusDiv').html('');
	});
});
function saveMobile(){
	$('#statusDiv').html('');
	var customerId =  $('#customerList').val();
	var mobileNo = $('#contactNo').val();
	
	if(customerId == 0){
	$('#statusDiv').html("<font style='color:#CC0000;font-weight:normal;font-size:12px;'> Select Customer Name </font>");
	return;
	}
	if(isNaN(mobileNo)){
		$('#statusDiv').html("<font style='color:#CC0000;font-weight:normal;font-size:12px;'> Mobile Number contains only Numbers </font>");
		return;
	}
	if(mobileNo == '' ){
		$('#statusDiv').html("<font style='color:#CC0000;font-weight:normal;font-size:12px;'> Enter Mobile Number  </font>");
		return ;
	}
	if(trim(mobileNo).length <= 9){
		$('#statusDiv').html("<font style='color:#CC0000;font-weight:normal;font-size:12px;'> Enter a Valid Mobile Number  </font>");
		return ;
	}
	var jsObj={
		candidateId : customerId,
		mobileNo : mobileNo,
		task :"saveMobileNo"
		}
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "saveCustomerContactUpdationAction.action?"+rparam;		callAjaxForContacts(rparam,jsObj,url);
}

function callAjaxForContacts(rparam,jsObj,url){
	var myResults;					
		var callback = {			
				success : function( o ) {
					try 
					{
					if(o.responseText)
							myResults = YAHOO.lang.JSON.parse(o.responseText);
					
					if(jsObj.task == "saveMobileNo"){
					var customerName = $("#customerList option:selected").text();
						if(myResults == "success"){
							$('#statusDiv').html("<font style='color:#009900;'> "+customerName+"\'s </font><font style='color:green;font-weight:normal;font-size:12px;'> Mobile Number saved successfully </font>");
							$('#contactNo').val('');
							}
						else
						{
							$('#statusDiv').html("<font style='color:#CC0000;'> "+customerName+"\'s </font><font style='color:green;font-weight:normal;font-size:12px;'> Mobile Number Not saved successfully,internal error occured </font>");					
							}
					}
					}catch (e){
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
</script>
<body>
<div id="mainDiv" align="center">
 <h4 id="heading"  class=""> INSERT CUSTOMER VERIFIED MOBILE NUMBERS TO SEND VOICE SMS </h4>
 <div id="statusDiv" style="width: 800px; height: 20px;"> </div>
<div style="width: 900px; margin: 10px 10px 10px 2px;">
Select Customer<font class="requiredFont">*</font> <span  style="margin-left: 25px;">:</span> 
<select id="customerList">
	<c:forEach var="allUsers" varStatus="stat" items="${allRegisteredUsersData.listOfUsers}">		
					<option value="${allUsers.id}"> ${allUsers.name} </option>	
				  </c:forEach>
	</select>
</div>
<div style="width: 900px;margin-left: -10px;">
Enter Mobile No.<font class="requiredFont">*</font> <span  style="margin-left: 25px;">:</span>  <input id="contactNo" type="text" style="width: 190px;" maxlength="10"/>
</div>

<input type="button" id="saveButton" class="btn btn-success" value="save" onClick="saveMobile();"/>
</div>
</body>
</HTML>