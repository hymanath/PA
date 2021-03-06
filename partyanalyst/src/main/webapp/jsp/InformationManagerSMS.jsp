<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Information Monitoring System</title>

<!-- YUI Dependency files (Start) -->

<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/history/history.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/container/container-min.js"></script> 
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/button/button-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/resize/resize-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/layout/layout-min.js"></script>
<script type="text/javascript" src="../js/yahoo/yui-js-2.8/build/datatable/datatable-min.js" ></script>

<script type="text/javascript" src="../js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>

<script type="text/javascript" src="../js/yahoo/yui-gallery/gallery-accordion-min.js"></script>
<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.columnFilter.js"></script>
<!-- YUI Skin Sam -->
 <link rel="stylesheet" type="text/css" href="styles/userProfile/userProfilePage.css"> 
<link rel="stylesheet" type="text/css" href="../styles/yuiStyles/yui-gallery-styles/gallery-accordion.css">	
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/datatable/assets/skins/sam/datatable.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/treeview/assets/skins/sam/treeview.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/paginator/assets/skins/sam/paginator.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/resize.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/assets/skins/sam/layout.css">
<link rel="stylesheet" type="text/css" href="../js/yahoo/yui-js-2.8/build/carousel/assets/skins/sam/carousel.css">

<!-- YUI Dependency files (End) -->
<script type="text/javascript" src="js/infromationManager.js" ></script>
<script type="text/javascript" src="js/voterAnalysis/regionSelect.js"></script>
<script type="text/javascript" src="js/voterAnalysis/commonUtilityScript.js"></script>



<link rel="stylesheet" href="js/jQuery/development-bundle/themes/base/jquery.ui.all.css" type="text/css" media="all" />
 <script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.js"></script>
 <link rel="stylesheet" href="styles/jQ_datatables/css/demo_page.css" />
<link rel="stylesheet" href="styles/jQ_datatables/css/demo_table.css" />

 <link rel="stylesheet" type="text/css" href="https://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">


<LINK type="text/css" rel="stylesheet" href="styles/ElectionsReslutsPage/datatable.css">

<LINK rel="stylesheet" type="text/css" href="styles/jqueryDataTable/css/datatable.css">

<style>
#mainDiv{width:940px;margin-left:auto;margin-right:auto;}

input {width:60px !important;}


table.dataTable tr.odd td.sorting_1  {
background-color: #F9F9F9 !important;
}
table.dataTable tr.even td.sorting_1
{
background-color:  #FFFFFF !important;
}

.widget {border-top:5px solid #FFDC2D;}
.dataTables_filter,.dataTables_length{display:none !important;}

#tableID{font-size:12px}
#errorMsgDiv{font-weight:bold;}

.paginate_disabled_next, .paginate_enabled_next {
    margin-left: 10px !important;
    padding-right: 31px !important;
}
.paginate_disabled_previous, .paginate_enabled_previous, .paginate_disabled_next, .paginate_enabled_next {
   
    margin-left: 40px !important;
  
}

</style>

 </HEAD>

 <BODY>
 <script type="text/javascript">
 var loginUserId = "${sessionScope.USER.registrationID}";
 $( document ).ready(function() {
  $('#dateId').val(' ');
  $('#nameSearch').val('');
$("#parentUserCheck").live("click",function(){
var th = $(this);
if(th.is(':checked')) {
	$("label[for=" + th.attr('id') + "]").text("Unselect All");
	$(".userCheck").attr("checked",true);
} else {
	$("label[for=" + th.attr('id') + "]").text("Select All");
	$(".userCheck").attr("checked",false);
}
});

$("#parentViewSmsCheckbox").live("click",function(){
var th = $(this);
if(th.is(':checked')) {
	$("label[for=" + th.attr('id') + "]").text("Unselect All");
	$(".viewSmsCheckbox").attr("checked",true);
} else {
	$("label[for=" + th.attr('id') + "]").text("Select All");
	$(".viewSmsCheckbox").attr("checked",false);
}
});

$("#dateId").live("click", function(){
$(this).datepicker({
dateFormat: "dd/mm/yy",
changeMonth: true,
changeYear: true,
maxDate: new Date()

}).datepicker("show");
});

});

 </script>
<!--<div class="container m-top15" style="margin-top:20px">-->
<div class="container m-top15 clearfix" style="background: none repeat scroll 0% 0% rgb(255, 255, 255) !important; margin-top: 0px; padding: 20px !important; border-right: 1px solid rgb(255, 220, 45) !important; border-left: 1px solid rgb(255, 220, 45) !important;">

<h1 style="margin-bottom: 10px !important; text-align: center; margin-left: -1px !important;background:#FFDC2D;color:#FE0000;" class="alert alert-info">Information Monitoring System</h1>
<div class=" widget clearfix " style="margin-bottom: 10px !important; ">

<h2>Send Text SMS </h2>

<!-- <h4>Existing Phone Numbers</h4> -->
<h4>Existing Incharge Details </h4>

	<div id="errorMsgDiv" style="font-size: 12px; font-family: verdana;"> </div>
	<div id="userInfoDiv" class="span6 m-top15" style="width: 900px; margin-right: auto; margin-left: auto;">
</div>
</div>




<div class="widget clearfix " id="viewSmsMainDiv" style="display:none;">
    
        <h2>View All Sent SMS</h2>
		<span id="errorDivName" style="margin-left:382px;display:none;"></span>
		<span id="errorDivMobile" style="margin-left:382px;display:none;"></span>

<!--<div class="well well-samll form-inline" style="padding:9px;margin-left:-20px;width:941px"><label>Select Date: <input type="text" class="input-small" id="dateId" placeholder="Enter Date" style="width:120px !important;" onChange="getSortedInfoByDate(this.value);">-->

<div class="well well-samll form-inline" style="padding:9px;margin-left:-20px;width:941px"><label>Select Date: <input type="text" class="input-small" id="dateId" placeholder="Enter Date" style="width:120px !important;" readonly="true">
              <span class="add-on"></span></label>&nbsp;&nbsp;<label>Enter Name: <input type="text" onchange="" style="width:120px !important;" id="nameSearch" class="input-small hasDatepicker" placeholder="Enter Name"></label>&nbsp;&nbsp; 

<a class="btn btn-info" onClick="getLocationSearchDetails();">search</a></div>


<div class="well well-small" style="margin-left: -20px; width: 940px; display: inline-block;" id="viewSmsDiv">

</div>

<span  class="btn btn-small btn-danger" id="deleteAll" onClick="deleteAllRows();"><i class="icon-trash"></i> Delete Selected Messages</span>
</div>





	</div>

  <script>

	function callAjax(jsObj,url)
	{					
 		var callback = {			
 		               success : function( o ) {
							try {
								myResults = YAHOO.lang.JSON.parse(o.responseText);									
								
								if(jsObj.task == "getUsers")
								{
									buildUsers(myResults,jsObj);
								}	
								if(jsObj.task == "sendSms")
								{
									buildSendSmsStatus(myResults);
								}									
								if(jsObj.task == "getSmsDetails")
								{
									buildSmsDetails(myResults);
								}
								if(jsObj.task == "datewiseSorting")
								{
									buildSmsDetails(myResults);
								}
								if(jsObj.task == "reSendSms")
								{
									getSmsDetails();

									setTimeout(function(){
									$("#successDiv").html("Message Resended  Successfully..").css({"color":"green","width":"600px","float":"right"});
									setTimeout(function(){
											$("#successDiv").html('');
											},3000);
									},1000);
								}
								if(jsObj.task == "deleteSmsDetails")
								{
									getSmsDetails();
									
									setTimeout(function(){
										$("#successDiv").html("Message Deleted Successfully..").css({"color":"green","width":"600px","float":"right"});
										setTimeout(function(){
											$("#successDiv").html('');
											},3000);
									},1000);
									
								}
								if(jsObj.task == "forwardSms")
								{
									getSmsDetails();

									setTimeout(function(){
									$("#successDiv").html("Message Forwarded Successfully..").css({"color":"green","width":"600px","float":"right"});
									setTimeout(function(){
											$("#successDiv").html('');
											},3000);
									},1000);
								}
										
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
	function buildSendSmsStatus(result)
	{
		 maxcount = 0;
		 count = 2;
         limitno = 160;
		$("#ajaxImage").css("display","none");
		document.getElementById("description").value = '';

		$(".userCheck").attr("checked",false);
	if(result.resultCode == 0)
	{
	getSmsDetails();
	
	return setTimeout(function(){
					$("#errorMsgDiv").html('SMS sent successfully').css("color","green");
					setTimeout(function(){
							$("#errorMsgDiv").html('');
							$("#maxcount").html('200');
						},3000);
					},1000);
	}
	else
		return setTimeout(function(){
					$("#errorMsgDiv").html('Exception Occured due to some Exception').css("color","red");
						setTimeout(function(){
							$("#errorMsgDiv").html('');
						},3000);
					},1000);
	}
function selectAll()
{
$(".userCheck").attr("checked",true);
}
function unselectAll()
{
$(".userCheck").attr("checked",false);
}
	function sendSMS(){

	$("#errorMsgDiv").html('');
	var userIds = new Array();
	var errorMsgDiv = document.getElementById("errorMsgDiv");
	var str='';
	var flag = false;
	$(".userCheck").each(function(){
	 if($(this).is(':checked')){
     var mobileNo = $(this).closest("tr").find("td.mobile").attr("value");
	
	 var userId = $(this).val();
		       var obj={
				 mobileNo:mobileNo,
				 userId:userId
			   }
	 
		     userIds.push(obj);
			  
			  }
	});
	var description = $("#description").val().trim();
	if(userIds.length == 0)
	{
	str+='<font color="red">Select atleast one user</font><br/>';
	flag = true;
	}
	if(description == "" || description.length == 0 )
	{
	str += '<font color="red">Description Required</font><br>';
		flag = true;
	}
	if(flag == true)
	{
	errorMsgDiv.innerHTML = str;
	return;
	}
	else
	{
		
		
		$("#ajaxImage").css("display","inline-block");
		errorMsgDiv.innerHTML = '';
	
	var jsObj=
				{					
					userIds:userIds,	
					description:description,
					task:"sendSms"
				}
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "sendSmsToInformationManagers.action?"+rparam;						
			callAjax(jsObj,url);
}
	}
function getInformationManagers(){
 
   var jsObj=
				{					
				
					task:"getUsers"
				}
			
				var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
				var url = "getInformationManagersAction.action?"+rparam;						
			callAjax(jsObj,url);

	}
var count = 2;
var limitno = 160;

function limitText(limitField, limitCount, limitNum)
{

//var limitFieldElmt = document.getElementById(limitField);
var limitFieldElmt = $("#"+limitField).val().trim();
var limitCountElmt = document.getElementById(limitCount);
limitCountElmt.innerHTML = limitNum + limitFieldElmt.length+" Characters";
if(limitFieldElmt.length == 0 || limitFieldElmt.length < 160)
	{
$("#alertMsg").html('');
count = 2;
limitno = 160;
	}
	
if(limitFieldElmt.length > limitno)
	{
$("#alertMsg").html('Exceeding the limit '+limitno+' characters and you are in to '+count+' nd message');
count ++;
limitno = limitno + 160;
	}
var count1 = limitFieldElmt.length;
if(count1 < limitno && count >= 3)
	{
	 count--;
	limitno = limitno - 160;
	$("#alertMsg").html('exceeding the limit '+limitno+' characters and you are in to '+count+' nd message');
	}

}

function getLocationSearchDetails()
{
$("#viewSmsDiv").html('');
var nameText = $("#nameSearch").val().trim();
var jsObj=
{
typeId : 2,
locationsearchText :nameText,

task : "getSmsDetails"
}
var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
var url = "getSmsDetailsLocationSeacrhAction.action?"+rparam;

callAjax(jsObj,url);
}

getInformationManagers();
getSmsDetails();

</script>

 </BODY>
</HTML>
