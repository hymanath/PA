<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="js/commonUtilityScript/commonUtilityScript.js">
</script>
<!-- JQuery files (Start) -->
<script type="text/javascript" src="js/jQuery/jquery-1.5.2.js"></script>
 
<script
	src="js/jQuery/jquery-ui.min.js">
</script>
<link
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/jquery-ui-1.8.14.custom.css" />
<link type="text/css" rel="stylesheet" href="styles/jQuery/datepicker/demos.css" />


<!-- JQuery files (End) -->
<title>Call Center</title>
<style type="text/css">
.yui-skin-sam .yui-panel 
		{
			background:#FFFFFF none repeat scroll 0 0;
			border-color:#808080;
			border-style:solid;
			border-width:1px 0;
			left:0;
			position:relative;
			top:0;
			z-index:1;
		}
.yui-skin-sam .yui-dt  {
    border: 2px solid #9696C0;
    border-collapse: separate;
    border-spacing: 0;
    font-family: arial;
    font-size: 12px;
    margin: 0;
    padding: 0;
    width: 100%;
}
		.yui-skin-sam .yui-dt th .yui-dt th a
		{
		background-image:url("images/YUI-images/sprite.png")
		}
		
		#yui-dt0-th-Categorize
		{
			background-color:blue;
		}
	
#searchDiv {
	
    -moz-border-radius-topleft: 14px;
    -moz-border-radius-topright: 14px;
    background: -moz-linear-gradient(center top , #73B8DB 0pt, #4393BB 100%) repeat scroll 0 0 transparent;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 0;
    color: #FFFFFF;
    font-size: 12px;
    font-weight: bold;
    height: 29px;
    padding-left: 12px;
    padding-top: 6px;
    width: 430px;
  }
.tdStyle {
    font-size: 12px;
    font-weight: bold;
	font-family: verdana,arial;
}
#problemDetails_body{
	border: 2px solid;
    font-size: 12px;
    margin-left: 139px;
    margin-right: 139px;
    margin-top: 59px;
}

#tableStyle{
-moz-border-radius-bottomleft: 6px;
    -moz-border-radius-bottomright: 6px;
    background: none repeat scroll 0 0 #FFFFFF;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 1px;
}
#table1{
    -moz-border-radius-topleft: 9px;
    -moz-border-radius-topright: 9px;
    background: -moz-linear-gradient(center top , #73B8DB 0pt, #4393BB 100%) repeat scroll 0 0 transparent;
    color: #FFFFFF;
    margin-left: 1px;
    margin-top: 18px;
    padding: 8px;
	font-size: 12px;
    font-weight: bold;
    height: 29px;
    height: 20px;
    padding: 6px 8px 8px 10px;
}

.table1Style{
    -moz-border-radius-bottomleft: 6px;
    -moz-border-radius-bottomright: 6px;
    background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #DDDDDD;
    padding: 18px 67px 99px;
    height: 300px;
 }
#resultBtnId{
	background: -moz-linear-gradient(center top , #AFD47B 0pt, #4393BB 1px, #4393BB 1px, #4393BB 100%) repeat scroll 0 0 transparent;
	-moz-border-radius: 6px 6px 6px 6px;
    border-color: #669933;
    color: #FFFFFF !important;
    font-weight: bold;
    white-space: nowrap;
}
#btnStyle{
    background: -moz-linear-gradient(center top , #AFD47B 0pt, #AFD47B 1px, #8BC03F 1px, #69A219 100%) repeat scroll 0 0 transparent;
	-moz-border-radius: 6px 6px 6px 6px;
    border-color: #669933;
    color: #FFFFFF !important;
    font-weight: bold;
    white-space: nowrap;
}
.textFieldStyle{
    -moz-border-radius: 6px 6px 6px 6px;
    background: none repeat scroll 0 0 #FFFFFF;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 1px;
	height: 28px;
    padding-left: 8px;

}
 #headerDiv{
   -moz-border-radius: 9px 9px 9px 9px;
    background: -moz-linear-gradient(center top , #F9F9F9 0pt, #E8E8E8 100%) repeat scroll 0 0 transparent;
    border-color: #DDDDDD;
    border-style: solid;
    border-width: 1px 1px 0;
    color: Navy;
    font-size: 20px;
    font-weight: bold;
    height: 33px;
    margin-top: 23px;
    padding-top: 10px;
    text-align: center;
    width: 210px;
 }
</style>
</head>

<script type="text/javascript">

function quickSearch(){
 
  var str='';
  var quickSearchDivElmt = document.getElementById("quickSearchDiv");
  str+='<div id="searchDiv">Quick Search For Problems';
  str+='</div>';
  str+='<table cellspacing="2px" cellpadding="11px" id="tableStyle" >';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byName" value="Name" id="nameId" cssClass="textFieldStyle" size="30px" onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>';
  str+='</td>';
  str+='</tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byMobileNum" value="Mobile Number" id="mobileNumId" cssClass="textFieldStyle" size="30px" onClick="removeTextInTextBoxes(this.id)" onBlur=" numbersonly(this.id);showTextInTextBoxes(this.id);" theme="simple"/>';
  str+='&nbsp<span id="errMsg">';
  str+='</span>';
  str+='</td>';
  
  str+='</tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byEmail" value="Email Id" id="emailId" cssClass="textFieldStyle" size="30px" onClick="removeTextInTextBoxes(this.id)" theme="simple" onBlur="validateEmail(this.id),showTextInTextBoxes(this.id)"/>';
  str+='<span id="emailErrMsg" style="padding-left:37px;">';
  str+='</span>';
  str+='</td>';
  
  str+='</tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="byRefNum" value="Problem Reference" id="refId" cssClass="textFieldStyle"  onClick="removeTextInTextBoxes(this.id)" onBlur="showTextInTextBoxes(this.id)" size="30px" theme="simple"/>';
  str+='</td>';
  str+='</tr>';
  str+='<tr>';
  str+='<tr>';
  str+='<td class="tdStyle">';
  str+='<s:textfield name="fromDate" value="From Date" id="fromDate" size="30px" cssClass="textFieldStyle" onClick="removeTextInTextBoxes(this.id)" onfocus="showCalendar(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>';
  str+='<s:textfield name="endDate" value="To Date" id="endDate" cssClass="textFieldStyle" size="30px" style="margin-left: 20px;" onClick="removeTextInTextBoxes(this.id)" onfocus="showCalendar(this.id)" onBlur="showTextInTextBoxes(this.id)" theme="simple"/>';
  str+='</td>';
  str+='</tr>';
  str+='<tr>';
  str+='<td align="center">';
  str+='<input type="button" style="padding:8px;" id="resultBtnId" value="Search" onClick="getProblemDetails()">';
  str+='</td>';
  str+='</tr>';
  str+='</table>';
  

quickSearchDivElmt.innerHTML = str;

}
function validateEmail(id){
	
	var email=document.getElementById("emailId").value;
	var emailFilter=/^.+@.+\..{2,3}$/
	if(email!="" && email!="Email Id"){
		if(!emailFilter.test(email)){
			document.getElementById("emailErrMsg").innerHTML = '<font color="red">Please enter valid Email</font>';
		}
	else{
		document.getElementById("emailErrMsg").innerHTML ='';
		}
	}
	else{
		document.getElementById("emailErrMsg").innerHTML ='';
		}
}
function numbersonly(id){
	

	var num = document.getElementById(id).value;
	if(num !=''&& num!="Mobile Number"){
	 if(isNaN(num) || num.length<10){
		document.getElementById("errMsg").innerHTML ='<font color="red">Please enter valid Mobile Number</font>';
	}
	else{
		document.getElementById("errMsg").innerHTML ='';
	}
	}
	else{
		document.getElementById("errMsg").innerHTML ='';
	}
}
function removeTextInTextBoxes(id){

  if($("#"+id).val() =="Name" || 
	  $("#"+id).val() =="Mobile Number" ||
	  $("#"+id).val() =="Email Id" || 
	  $("#"+id).val() =="Problem Reference"||
	  $("#"+id).val() =="From Date" ||
	  $("#"+id).val() =="To Date"){
    $("input#"+id).val($(this).html());
	return;
  }
}
function showTextInTextBoxes(id){

   if($("#nameId").val() ==''){
		$("#nameId").val('Name');
	  } 
	  if($("#refId").val() ==''){
		$("#refId").val('Problem Reference');
	  }
	  if($("#mobileNumId").val() ==''){
		$("#mobileNumId").val('Mobile Number'); 
	  }
	  if($("#emailId").val() ==''){
		 $("#emailId").val('Email Id');
	  }
	 if($("#fromDate").val() ==''){
		 $("#fromDate").val('From Date');
	 }
	 if($("#endDate").val() ==''){
         $("#endDate").val('To Date');
	 }
}

function getProblemDetails(){
	
	  if($("#nameId").val() =="Name"){
		$("#nameId").val($(this).html());
	  } 
	  if($("#refId").val() =="Problem Reference"){
		$("#refId").val($(this).html());
	  }
	  if($("#mobileNumId").val() =="Mobile Number"){
		 $("#mobileNumId").val($(this).html()); 
	  }
	  if($("#emailId").val() =="Email Id"){
		 $("#emailId").val($(this).html());
	  }
	 if($("#fromDate").val() =="From Date"){
		 $("#fromDate").val($(this).html());
	 }
	 if($("#endDate").val() =="To Date"){
         $("#endDate").val($(this).html());
	 }

	  var jsObj = {
		name:$("#nameId").val(),
		refNum:$("#refId").val(),
		mobileNum:$("#mobileNumId").val(),
        emailId:$("#emailId").val(),
        fromDate:$("#fromDate").val(),
		endDate:$("#endDate").val(),
		task:"problemSearch"
  };
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "callCenterAjaxAction.action?"+rparam;						
	callAjax(rparam,jsObj,url);
}
function callAjax(param,jsObj,url){
var myResults;	
var callback = {			
    success : function( o ) {
		try {												
			myResults =YAHOO.lang.JSON.parse(o.responseText);	if(task ="problemSearch"){
				
				getProblemsDetailsDatatable(myResults);
			}
							
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

function getProblemsDetailsDatatable(result){

YAHOO.widget.DataTable.problemLink = function(elLiner, oRecord, oColumn, oData) 
	{
		var problem = oData;
		var phId= oRecord.getData("problemHistoryId");
		elLiner.innerHTML ="<a href='javascript:{}' onClick='openProblemWindow("+phId+")'>"+problem+"</a>";
			
	};
var resultsColumnDefs = [ 	
	 {
		key : "problem",
		label : "Problem",
		sortable : true,
		formatter:YAHOO.widget.DataTable.problemLink
	}, 
	  {
		key : "description",
		label : "Description",
		sortable : true
		},
		{
		key : "reportedDate",
		label : "Identified on",
		sortable : true
		},
		 
		{
		key : "impactLevel",
		label : "Problem Scope",
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
		var myDataSource = new YAHOO.util.DataSource(result);
myDataSource.response = YAHOO.util.DataSource.TYPE_JSARRAY
					myDataSource.responseschema = {
						 fields : [ "problem","description" , "reportedDate" ,"impactLevel"]
					};

				
	var myDataTable = new YAHOO.widget.DataTable("problemDetails_body",resultsColumnDefs, myDataSource,myConfigs);  


}
function openProblemWindow(problemHistoryId){

var browser2 = 
window.open("addNewProblemAction.action?problemHistoryId="+problemHistoryId,"editProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
						 
		 browser2.focus();

}
function openAddNewProblemWindow()
{	
var browser = 
window.open("<s:url action="addNewProblemAction.action"/>","addNewProblem","scrollbars=yes,height=600,width=600,left=200,top=200");
						 
		 browser.focus();
	}
function openManageProblemWindow()
{	
var browser1 = 
window.open("<s:url action="constituencyManagementAction.action"/>","ManageProblem","scrollbars=yes,height=900,width=900,left=5,top=200");
						 
		 browser1.focus();
}

function openProblemSearchWindow()
{	
var browser1 = 
window.open("<s:url action="problemManagementReportAction.action"/>","ManageProblem","scrollbars=yes,height=900,width=1000,left=5,top=200");
						 
		 browser1.focus();
}


</script>
<body>
<div id="headerDiv">Call Center</div>
<div id="mainDiv">
<table>
<tr><td>
<div id="table1">Quick Links</div>
<table class="table1Style">

 <tr>
   <td>
  <img  style="margin-left: -43px;" src="images/icons/homePage_new/widgetHeaderIcon.jpeg">
   </img>&nbsp&nbsp
<a class="tdStyle" href="" onclick="openAddNewProblemWindow()">
Add New Problem</a>
  </td>
</tr>
<tr>
   <td>
   <img  style="margin-left: -43px;" src="images/icons/homePage_new/widgetHeaderIcon.jpeg">
   </img>&nbsp&nbsp
   <a class="tdStyle" href="" onclick="openManageProblemWindow()">
   Manage Problem</a>
   </td>
</tr>
<tr>
   <td>
   <img  style="margin-left: -43px;" src="images/icons/homePage_new/widgetHeaderIcon.jpeg">
   </img>&nbsp&nbsp
   <a class="tdStyle" href="" onclick="openProblemSearchWindow()">
   Detailed Search on Problems</a>
   </td>
</tr>
</table>

</td>
<td>
<table style="margin-left: 62px; margin-top: 65px;">
<tr>
<td><div id="quickSearchDiv"></div>
</td>
</tr>
</table>
</td>
</tr>
</table>
</div>
<div>
<div id="problemDetails_body" class="yui-skin-sam" ></div>
</div>

<script type="text/javascript">
quickSearch();
</script>
<script>
$(function() {
	
		$( "input:button", ".demo" ).button();
		$( "button", ".demo" ).click(function() { return false; });
	});
</script>
<script>
	$(function() {
		$( "#fromDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			maxDate:new Date(),
			dateFormat:"yy-mm-dd"
		});

		$( "#endDate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange:"c-10",
			maxDate:new Date(),
			dateFormat:"yy-mm-dd"
        });
		
	});

	</script>




</body>

</html>
