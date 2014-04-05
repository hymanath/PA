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

 <link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">


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

<h1 style="margin-bottom: 10px !important; text-align: center; margin-left: -1px !important;background:#FFDC2D;color:#FA764E;" class="alert alert-info">Information Monitoring System</h1>
<div class=" widget clearfix " style="margin-bottom: 10px !important; ">

<h2>Send Text SMS </h2>

<h4>Existing Phone Numbers</h4>
		 <!--<div class="">
    <div class="span4 offset2 m-top15">
    <label>Select Parliament: <select id="constituency" onchange="getInformationManagers();"></select><label>
</label></label></div>
 <div class="span6 m-top15">
<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Select One Option: <select><option>01</option><option>02</option><option>03</option></select><label>
        </label></label></div>
		
	
		</div>-->
	<div id="errorMsgDiv" style="font-size: 12px; font-family: verdana;"> </div>
	<div id="userInfoDiv" class="span6 m-top15" style="width: 900px; margin-right: auto; margin-left: auto;">
</div>
</div>




<div class="widget clearfix " id="viewSmsMainDiv" style="display:none;">
    
        <h2>View All Sent SMS</h2>
		<span id="errorDivName" style="margin-left:382px;display:none;"></span>
		<span id="errorDivMobile" style="margin-left:382px;display:none;"></span>

<div class="well well-samll form-inline" style="padding:9px;margin-left:-20px;width:941px"><label>Select Date: <input type="text" class="input-small" id="dateId" placeholder="Enter Date" style="width:120px !important;" onChange="getSortedInfoByDate(this.value);">
              <span class="add-on"></span></label>&nbsp;&nbsp;<label>Enter Name: <input type="text" onchange="" style="width:120px !important;" id="nameSearch" class="input-small hasDatepicker" placeholder="Enter Name"></label>&nbsp;&nbsp; <label>Enter Number: <input type="text" class="input-small hasDatepicker" id="MobileSearch" style="width:120px !important;" placeholder="Enter Number"  maxlength="12"></label>&nbsp;&nbsp;
<a class="btn btn-info" onClick="getSearchDetails();">search</a></div>




		<!--<div class="well-small input-form">
		<label><input type="text" class="input-small" id="dateId" placeholder="Enter Date" style="width:120px !important;" onChange="getSortedInfoByDate(this.value);">
              <span class="add-on"><i class="icon-calendar"></i></span></label>
            </div>
<div class="span3 m-top15"  style="width: 235px;">
    <label>Select Name:&nbsp;<input type="text" class="input-small" id="nameSearch"  placeholder="Enter Date"  style="width:120px !important;"><label>
</label></label></div>
<div class="span3 m-top15"  style="width: 235px;">
    <label>Select Number:&nbsp;<input type="text" class="input-small" id="MobileSearch" placeholder="Enter Number"  style="width:120px !important;"><label>
</label></label></div>

<button type="submit" class="btn btn-info" style="margin-left: 20px; margin-top: 10px;" onClick="getSearchDetails();"><i class="icon-search icon-white"></i>Search</button>-->
<div class="well well-small" style="margin-left: -20px; width: 940px; display: inline-block;" id="viewSmsDiv">
    

 <!--<div class="span3   m-top15">
<label>Name:&nbsp; <input type="text" placeholder="Enter Name" class="input-small"></label>
</div>
<div class="span3   m-top15">
<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Contact No: <input type="text" class="input-small" placeholder="Enter Contact Number"></label></div>-->
<!--<div class="span3   m-top15">
<span class="btn btn-info"><i class="icon-search icon-white"></i> Search</span></div>-->

</div>

<span  class="btn btn-small btn-danger" id="deleteAll" onClick="deleteAllRows();"><i class="icon-trash"></i> Delete Selected Meassages</span>
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

 		YAHOO.util.Connect.asyncRequest('GET', url, callback);
	}
	function buildSendSmsStatus(result)
	{
		 maxcount = 200;
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
	str+='Select atleast one user<br/>';
	flag = true;
	}
	if(description == "" || description.length == 0 )
	{
	str += 'Description Required<br>';
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
	/*if($('#mobileNumber').val().trim() != "")
  {

	  var othrNums= $('#mobileNumber').val().split(",");
	 
	 for(var i=0;i<othrNums.length;i++)
		 otherNos.push(othrNums[i]);
 }
 
	 $(".otherInfo").each(function(){
		 if( $(this).find(".firstname").val() != "")
		 {
	 var firstName = $(this).find(".firstname").val();
	 var lastName = $(this).find(".lastName").val();
	 var mobileNo = $(this).find(".mobileNumber").val();
	 var obj ={
		firstName :firstName, 
		lastName : lastName,
		mobileNo :mobileNo
		}
		otherNos.push(obj);
		 }
	 });
	 */
	

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

/*function buildStatus(result)
{
  
}*/
	  </script>
	  <script type="text/javascript">
	  getAllParliamentConstInCountry("constituency");
	  getInformationManagers();
	  getSmsDetails();
	   </script> 

	    <script type="text/javascript">
      
		
   </script>
 </BODY>
</HTML>
