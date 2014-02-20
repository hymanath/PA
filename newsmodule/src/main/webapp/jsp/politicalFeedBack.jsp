<%@ page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Political Feed Back</title>

<!-- Bootstrap -->
		<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- Bootstrap -->


<!-- JQUERY -->
	<script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.24.custom.min.js"/>
	<script src="js/jquery.google.api/code.jquery.com.ui.1.10.2.jquery-ui.js"></script>
	<link  rel="stylesheet" type="text/css" href="js/jquery.google.api/jquery-ui1.10.3.css"/>
<!-- JQUERY -->

<!-- YUI Dependency files (Start) -->
		<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
		<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
		<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
		<script type="text/javascript" src="js/json/json-min.js"></script>
		<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
		<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
<!-- YUI Dependency files (End) -->		
		
		
<!-- Skin CSS files resize.css must load before layout.css --> 
		<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
		<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">

<!-- YUI Dependency files (End) -->

</head>
<body>
<div id='mainDiv' class="container">
	<div id="debateManagementDiv">	
	
				<!---Tab Header --Menu--->
				<ul class="nav nav-tabs" id="myTab"  style="margin-top: 23px;">
				<c:if test="${sessionScope.USER != null}">
				<c:if test="${sessionScope.USER.userAccessType == 'pfb'}">
					<li id="feedBackForm" class="active"><a data-toggle="tab" value="Upload News" style="cursor:pointer;color: #005580;" onclick="showPoliticalFeedBack();" style="cursor:pointer;color: #005580;" > PoliticalFeedBack Form</a>	
					</li>
					<li id="viewPoliticalFeedBach" class="">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="viewPoliticalFeedBack()" style="cursor:pointer;color: #005580;">View PoliticalFeedBack</a>
					</li>
				</c:if>	
				</c:if>
				<c:if test="${sessionScope.USER != null}">
				<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
					<li class="active">
					<a data-toggle="tab" value="News Gallery" id="responseNewsId" onClick="viewPoliticalFeedBack()" style="cursor:pointer;color: #005580;">View PoliticalFeedBack</a>
					</li>
				</c:if>	
				</c:if>
				</ul>
	</div>
<c:if test="${sessionScope.USER != null}">
<c:if test="${sessionScope.USER.userAccessType == 'pfb'}">
<div id="formFillDiv"> 

</c:if>	
</c:if>	
<c:if test="${sessionScope.USER != null}">
<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
<div id="formFillDiv" style="display:none;"> 
</c:if>	
</c:if>	
<form id="politicalFeedBackForm" method="post" action="savePoliticalFeedBackAction.action" name="politicalFeedBackForm">
<div id="successMsg" align="center"></div>
<div id="pcWiseDiv">
<legend class="boxHeading"> Parliament Wise Daily Political Feedback </legend>
	<div class="row-fluid" >
		
		<div class="span3">
			<label style="font-size: 17px;font-weight: bold;line-height: 1.5;"> Date : <font class="requiredFont">*</font></label>
			<input type="text" class="input-block-level selectWidth" name="politicalFeedBackVO.createdDate" id="pcDate"/>
		</div>	
		
		<div class="span3">
			<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Parliment : <font class="requiredFont">*</font></label>
					
			<s:select name="politicalFeedBackVO.parlimentId"  id="parlimentList" list="constituencysList" theme="simple" listKey="id" listValue="name" onChange="getAssemblyConstituencyes(this.value)"/>
		</div>
	</div>
	<div class="row-fluid" >
		<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Action Items :<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Action Item" onClick="addMoreActionItem();"><i class="icon-plus"></i></a></label>
		<!--<textarea placeholder="Please Enter Action Item  ..." rows="2" cols="50" class="span12" name="politicalFeedBackVO.actionItemsList[0].name" id="actionItem0" ></textarea>-->
		<table class="table table-bordered" id="actionItemsTable">
		<tr>
			<th>ACTION ITEM</th>
			<th>SOURCE</th>
			<th>DELETE</th>
		</tr>
		<tr>
			<td>
				<textarea placeholder="Please Enter Action Item  ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList[0].name" id="actionItem0" style="width: 600px;"></textarea>
			</td>
			<td>
				<textarea placeholder="Please Enter Sorce ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList[0].location" id="actionItem0" ></textarea>
			</td>
			<td></td>
		</tr>
		</table>
		<div id="addedActionItemDiv"></div>
	</div>
	<div class="row-fluid" >
		<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Summary : <font class="requiredFont">*</font></label>
		<textarea placeholder="Please Enter Summary  ..." rows="4" cols="50" class="span12" name="politicalFeedBackVO.summary" id="pcSummary" ></textarea>
	</div>

</div>

<div id="acWiseDiv" style="overflow-x: scroll;">	
</div>
<input type="hidden" value="save" name="politicalFeedBackVO.type"></input>
	<div align="center"><input value="Submit" onClick="validateFields('save')" class="btn btn-success" style="width: 57px;"></div>

</form>
</div>
<c:if test="${sessionScope.USER != null}">
<c:if test="${sessionScope.USER.userAccessType == 'pfb'}">
<div id="viewPoliticalDetailsDiv" style="display:none;">
</c:if>	
</c:if>	
<c:if test="${sessionScope.USER != null}">
<c:if test="${sessionScope.USER.userAccessType == 'Admin'}">
<div id="viewPoliticalDetailsDiv" >
</c:if>	
</c:if>	
<div id="errorDiv" align="center"></div>
	<div class="row-fluid" >
		
		<div class="span3">
			<label style="font-size: 17px;font-weight: bold;line-height: 1.5;"> Date : <font class="requiredFont">*</font></label>
			<input type="text" class="input-block-level selectWidth" name="politicalFeedBackVO.createdDate" id="pcDateview"/>
		</div>	
		
		<div class="span3">
			<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Parliment : <font class="requiredFont">*</font></label>
					
			<s:select name="politicalFeedBackVO.parlimentId"  id="parlimentListview" list="constituencysList" theme="simple" listKey="id" listValue="name"/>
		</div>
		<div class="span3" > 
			<a class="btn btn-success" style="margin-top:30px;" onClick="getPoliticalFeedBack();">Submit</a>
		</div>
		
	</div>
<div id="politicalFeedBackResultDiv"></div>
</div>
</div>

<script>
var subjCount = 1;
var acCount = 1;

$( document ).ready(function() {
	$('#pcDate').datepicker({
		maxDate:new Date(),
	});
	$('#pcDateview').datepicker({
		maxDate:new Date(),
	});
});
function addMoreActionItem()
{
	var str = "";
	
	str += '<tr id="row'+subjCount+'">';
	str += '<td>';
	str += '<textarea placeholder="Please Enter Action Item  ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList['+subjCount+'].name" id="actionItem0" style="width: 600px;"></textarea>';
	str += '</td>';
	str += '<td>';
	str += '<textarea placeholder="Please Enter Sorce ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList['+subjCount+'].location" id="actionItem0" ></textarea>';
	str += '</td>';
	str +='<td><a  name="row'+subjCount+'" class="icon-trash" title="Click here to remove Action Item.." onClick="removeActionItems(this.name);" style="cursor: pointer;"></a></td>';
	str += '</tr>';
	subjCount++;
	$('#actionItemsTable').append(str);
	
}

function removeSubject(id)
{
   var isConfirm = confirm("Do you want to continue ?");
   if( isConfirm == true ){
      	$('#'+id+'').html("");
	  return true;
   }else{
	  return false;
   }
}

function submitForm(type)
{
	
	 var uploadHandler = {
	 success: function(o) {
	 var uploadResult = YAHOO.lang.JSON.parse(o.responseText);
		$('#successMsg').show();
		if(uploadResult.resultCode == 0)
		{
			$('#successMsg').html('<b style="color:green;">Political FeedBack Saved Successfully</b>');
			$('html, body').animate({
				 scrollTop: $("#successMsg").offset().top
			 }, 2000);
			 
			 setTimeout('window.location = "politicalFeedBackAction.action"',5000);
		}
		else
		{
			$('#successMsg').html('<b style="color:red;">Error Occured While Saving the debate information</b>');
		}
		
	 }
	 };
	 YAHOO.util.Connect.setForm('politicalFeedBackForm',false);
	 YAHOO.util.Connect.asyncRequest('POST','savePoliticalFeedBackAction.action',uploadHandler); 
}


function callAjax(jsObj,url)
{

	var callback =
	{			
		success : function( o )
		{
			try
			{ 
				myResults = YAHOO.lang.JSON.parse(o.responseText); 
				if(jsObj.task == "getAssemblyConstituencyes")
				{
					if(myResults != null && myResults.length > 0)
					{
						buildAssemblyConstituencysPoliticalFeedBack(myResults);
					}
				}
				else if(jsObj.task == "editSelectedPoliticalFeedBack")
				{
					if(myResults != null)
					{
						buildPoliticalFeedBackFormForEdit(myResults,jsObj.pfbId);
					}
				}
				else if(jsObj.task == "deletedPoliticalFeedBack")
				{
					if(myResults != null)
					{
						$('#politicalFeedBackResultDiv').html('');
						alert("Deleted Successfully");
					}
				}
				else if(jsObj.task == "getPoliticalFeedBack")
				{
					$('#politicalFeedBackResultDiv').html('');
					if(myResults != null && myResults.length > 0)
					{
						buildPoliticalFeedBack(myResults);
					}
					else
					{
						$('#errorDiv').show();
						$('#errorDiv').html('<b style="color:red">No Data Avaliable</b>').fadeOut(5000);
					}
				}
			
			}catch(e)
			{   
			 
			}  
		},
		scope : this,
		failure : function( o )
		{
			
		}
	};

	YAHOO.util.Connect.asyncRequest('GET', url, callback);
}
	

function getAssemblyConstituencyes(id)
{
	var jsObj =
	{ 
		constituencyId : id,
		task:"getAssemblyConstituencyes"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getAssemblyConstituencyesAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function buildAssemblyConstituencysPoliticalFeedBack(result)
{
    var str = '';
	str += '<legend class="boxHeading"> Assembly Wise Daily Political Feedback </legend>';
	str += '<div class="row-fluid">';
	str += '<table class="table table-bordered" id="acWiseDetailsTable">';
	str += '<tr>';
	str += '<th>C_CODE</th>';
	str += '<th>CONSTITUENCY</th>';
	str += '<th>IMP NEWS</th>';
	str += '<th>SOURCE</th>';
	str += '<th>CM POLITICAL FEEDBACK</th>';
	str += '<th>SOURCE</th>';
	str += '<th>OTHER POLITICAL FEEDBACK</th>';
	str += '<th>SOURCE</th>';
	str += '</tr>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].count+'</td>';
		str += '<td>'+result[i].name+'';
		
		str += '<input type="hidden" id="assemblyList'+i+'" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].constituencyId" value="'+result[i].id+'">';
		str += '</input>';
		str += '</td>';
		
		str += '<td>';
		str += '<textarea placeholder="Please Enter IMP News  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].impNews" id="impNews'+i+'" class="impNews" style="width: 255px;"></textarea>';
		str += '</td>';
		str += '<td>';
		str += '<textarea  placeholder="Please Enter Imp News Source ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].impSource"></textarea>';
		str += '</td>';
		str += '<td>';
		str += '<textarea placeholder="Please Enter CM Political Feedback ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].cmPoliticalFeedBack" id="cmPoliticalFeedBack'+i+'" class="cmPoliticalFeedBack" style="width: 255px;"></textarea>';
		str += '</td>';
		str += '<td>';
		str += '<textarea placeholder="Please Enter Cm Source ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].cmSource"></textarea>';
		str += '</td>';
		str += '<td>';
		str += '<textarea placeholder="Please Enter Other Political Feedback  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].otherPoliticalFeedBack" id="OtherPoliticaFeedback'+i+'" class="OtherPoliticaFeedback" style="width: 255px;"></textarea>';
		str += '</td>';
		str += '<td>';
		str += '<textarea placeholder="Please Enter Other Source ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].otherSource"></textarea>';
		str += '</td>';
		str += '</tr>';
	}
	str += '</table>';
	str += '</div>';
	$('#acWiseDiv').html(str);
}

function getPoliticalFeedBack()
{
	var id = $('#parlimentListview').val();
	if(id > 0 && $.trim($('#pcDateview').val()).length > 0)
	{
	
		var jsObj =
		{ 
			constituencyId : id,
			date  : $('#pcDateview').val(),
			task:"getPoliticalFeedBack"
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "getSelectedPolitialFeedBackDetailsAction.action?"+rparam;						
		callAjax(jsObj,url);
	}
	else
	{
		$('#errorDiv').show();
		var str = '';
		if($.trim($('#pcDateview').val()).length == 0)
		{
			str += ' <b style="color:red">Please Select Date</b></br>';
		}
		if(id == 0)
		{
			str += '<b style="color:red">Please Select Parliment</b></br>';
		}
		
		
		$('#errorDiv').html(str).fadeOut(3000);
	}
}

function buildPoliticalFeedBack(result)
{
	var str = '';
	for(var i in result)
	{
		str += '<div style="float:right;">';
		str += '<a class="btn btn-success" style="margin-right: 15px;" onClick="editSelectedPoliticalFeedBack('+result[i].id+')">Edit</a>';
		str += '<a class="btn btn-success" onClick="deletedPoliticalFeedBack('+result[i].id+')">Delete</a>';
		str += '</div>';
		str += '<legend class="boxHeading"> Parliment Wise Daily Political Feedback </legend>';
		str += '<div class="row-fluid">';
		str += '<table class="table table-bordered ">';
		str += '<tr>';
		str += '<th rowspan = 2>P_CODE</th>';
		str += '<th rowspan = 2>PARLIMENT_NAME</th>';
		str += '<th colspan=2 style="text-align: center;" >ACTION ITEMS</th>';
		str += '<th rowspan = 2>SUMMARY</th>';
		str += '</tr>';
		str += '<tr>';
		str += '<th>ACTION ITEM</th>';
		str += '<th>SOURCE</th>';
		str += '</tr>';
		var length = result[i].actionItemsList.length;
		str += '<tr>';
		str += '<td rowspan='+length+'>'+result[i].constituencyNo+'</td>';
		str += '<td rowspan='+length+'>'+result[i].parlimentName+'</td>';
		for(var j in result[i].actionItemsList)
		{
			if(j  != 0)
			{
				str += '<tr>';
			}
			if(result[i].actionItemsList[j].name != null)
			{
				str += '<td>'+result[i].actionItemsList[j].name+'</td>';
				str += '<td>'+result[i].actionItemsList[j].location+'</td>';
			}
			else
			{
				str += '<td></td>';
				str += '<td></td>';
			}
			if(j  != 0)
			{
				str += '</tr>';
			}
			if(j == 0)
			{
				str += '<td rowspan='+length+'>'+result[i].summary+'</td>';
				str += '</tr>';
			}
		}
		
		str += '</table>';
		str += '</div>';
		str += '<legend class="boxHeading"> Assembly Wise Daily Political Feedback </legend>';
		str += '<div class="row-fluid">';
		str += '<table class="table table-bordered ">';
		str += '<tr>';
		str += '<th>C_CODE</th>';
		str += '<th>CONSTITUENCY_NAME</th>';
		str += '<th>IMP NEWS</th>';
		str += '<th>SOURCE</th>';
		str += '<th>CM POLITICAL FEEDBACK</th>';
		str += '<th>SOURCE</th>';
		str += '<th>OTHER POLITICAL FEEDBACK</th>';
		str += '<th>SOURCE</th>';
		str += '</tr>';
		for(var j in result[i].politicalFeedBackVOList)
		{
			str += '<tr>';
			str += '<td>'+result[i].politicalFeedBackVOList[j].constituencyNo+'</td>';
			str += '<td>'+result[i].politicalFeedBackVOList[j].assemblyName+'</td>';
			if(result[i].politicalFeedBackVOList[j].impNews != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].impNews+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].impSource != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].impSource+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].cmPoliticalFeedBack != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].cmPoliticalFeedBack+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].cmSource != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].cmSource+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].otherPoliticalFeedBack != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].otherPoliticalFeedBack+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			if(result[i].politicalFeedBackVOList[j].otherSource != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].otherSource+'</td>';
			}
			else
			{
				str += '<td></td>';
			}
			str += '</tr>';
		}
		
		str += '<table>';
		str += '</div>';
	}
	$('#politicalFeedBackResultDiv').html(str);
}

function showPoliticalFeedBack()
{
	$('#formFillDiv').show();
	$('#viewPoliticalDetailsDiv').hide();
}

function viewPoliticalFeedBack()
{
	$('#viewPoliticalDetailsDiv').show();
	$('#politicalFeedBackResultDiv').html('');
	$('#pcDateview').val('');
	$('#parlimentListview').val(0);
	$('#formFillDiv').hide();
}

function validateFields(type)
{
	var flag = true;
	str = '';
	if($.trim($('#pcDate').val()).length == 0)
	{
		str += '<b style="color:red">Please Select Date</b></br>';
		flag = false
	}
	if($('#parlimentList').val() == 0)
	{
		str += '<b style="color:red">Please Select Parliment</b></br>';
		flag = false
	}
	if($.trim($('#pcSummary').val()).length == 0)
	{
		str += '<b style="color:red">Please Enter Summary</b>';
		flag = false
	}
	
	if(flag)
	{
		submitForm(type);
	}
	else
	{
		$('#successMsg').show();
		$('#successMsg').html(str).fadeOut(5000);
		$('html, body').animate({
			scrollTop: $("#successMsg").offset().top
		 }, 1000);
							 
	}
}
//editSelectedPoliticalFeedBack(28);
function editSelectedPoliticalFeedBack(id)
{
	var jsObj =
	{ 
		pfbId : id,
		task:"editSelectedPoliticalFeedBack"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSelectedPoliticalFeedBackDetailsAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function buildPoliticalFeedBackFormForEdit(result,id)
{
	subjCount = 0;
	$('#feedBackForm').addClass("active");
	$('#viewPoliticalFeedBach').removeClass("active");
	var str = '';
	
	$('#formFillDiv').show();
	$('#formFillDiv').html('');
	$('#viewPoliticalDetailsDiv').hide();
	str += '<form id="politicalFeedBackForm" method="post" ';
	str += 'action="savePoliticalFeedBackAction.action" name="politicalFeedBackForm">';
	str += '<div id="successMsg" align="center"></div>';
	str += '<input type="hidden" value="'+id+'" name="politicalFeedBackVO.id"></input>';
	str += '<div id="pcWiseDiv">';
	str += '<legend class="boxHeading"> Parliament Wise Daily Political Feedback </legend>';
	str += '<div class="row-fluid" >';
		
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;"> Date : <font class="requiredFont">*</font></label>';
	str += '<input type="text" class="input-block-level selectWidth" name="politicalFeedBackVO.createdDate" id="pcDate" value = '+result.createdDate+' onClick="selectDatePicker();"/>';
	str += '</div>	';
		
	str += '<div class="span3">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Parliment : <font class="requiredFont">*</font></label>';
					
	str += '<select name="politicalFeedBackVO.parlimentId"  id="parlimentList" list="constituencysList" theme="simple" listKey="id" listValue="name" onChange=""><option value='+result.parlimentId+'>'+result.parlimentName+'</option></select>';
	str += '</div>';
	str += '</div>';
	
	str += '<div class="row-fluid" >';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Action Items :<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Action Item" onClick="addMoreActionItem();"><i class="icon-plus"></i></a></label>';

	str += '<table class="table table-bordered" id="actionItemsTable">';
	str += '<tr>';
	str += '<th>ACTION ITEM</th>';
	str += '<th>SOURCE</th>';
	str += '<th>DELETE</th>';
	str += '</tr>';
	for(var i in result.actionItemsList)
	{
		str += '<tr id="row'+subjCount+'">';
		if(result.actionItemsList[i].name != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Action Item  ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList['+i+'].name" id="actionItem'+i+'" style="width: 600px;">'+result.actionItemsList[i].name+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Action Item  ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList['+i+'].name" id="actionItem'+i+'" style="width: 600px;"></textarea>';
			str += '</td>';
		}
		if(result.actionItemsList[i].location != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Sorce ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList['+i+'].location" id="actionSource'+i+'" >'+result.actionItemsList[i].location+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Sorce ..." rows="2" cols="50"  name="politicalFeedBackVO.actionItemsList['+i+'].location" id="actionSource'+i+'" ></textarea>';
			str += '</td>';
		}
		
		
		if(i > 0)
		{
			str +='<td><a  name="row'+subjCount+'" class="icon-trash" title="Click here to remove Action Item.." onClick="removeActionItems(this.name);" style="cursor: pointer;"></a></td>';
			
		}
		else
		{
			str += '<td></td>';
		}
		str += '</tr>';
		subjCount++;
	}
	str += '	</table>';
	str += '	<div id="addedActionItemDiv"></div>';
	str += '</div>';
	str += '<div class="row-fluid" >';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Summary : <font class="requiredFont">*</font></label>';
	str += '<textarea placeholder="Please Enter Summary  ..." rows="4" cols="50" class="span12" name="politicalFeedBackVO.summary" id="pcSummary" >'+result.summary+'</textarea>';
	str += '</div>';
	
	str += '</div>';
	str += '<div id="acWiseDiv" style="overflow-x: scroll;">';	
	
	str += '<legend class="boxHeading"> Assembly Wise Daily Political Feedback </legend>';
	str += '<div class="row-fluid">';
	str += '<table class="table table-bordered" id="acWiseDetailsTable">';
	str += '<tr>';
	str += '<th>C_CODE</th>';
	str += '<th>CONSTITUENCY</th>';
	str += '<th>IMP NEWS</th>';
	str += '<th>SOURCE</th>';
	str += '<th>CM POLITICAL FEEDBACK</th>';
	str += '<th>SOURCE</th>';
	str += '<th>OTHER POLITICAL FEEDBACK</th>';
	str += '<th>SOURCE</th>';
	str += '</tr>';
	for(var i in result.politicalFeedBackVOList)
	{
		str += '<tr>';
		str += '<td>'+result.politicalFeedBackVOList[i].constituencyNo+'</td>';
		str += '<td>'+result.politicalFeedBackVOList[i].assemblyName+'';
		
		str += '<input type="hidden" id="assemblyList'+i+'" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].constituencyId" value="'+result.politicalFeedBackVOList[i].constituencyId+'">';
		str += '</input>';
		str += '</td>';
		if(result.politicalFeedBackVOList[i].impNews != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter IMP News  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].impNews" id="impNews'+i+'" class="impNews" style="width: 255px;">'+result.politicalFeedBackVOList[i].impNews+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter IMP News  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].impNews" id="impNews'+i+'" class="impNews" style="width: 255px;"></textarea>';
			str += '</td>';
		}
		
		if(result.politicalFeedBackVOList[i].impSource != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter IMP Souce  ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].impSource" >'+result.politicalFeedBackVOList[i].impSource+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter IMP Souce  ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].impSource" ></textarea>';
			str += '</td>';
		}
		if(result.politicalFeedBackVOList[i].cmPoliticalFeedBack != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter CM Political Feedback ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].cmPoliticalFeedBack" id="cmPoliticalFeedBack'+i+'" class="cmPoliticalFeedBack" style="width: 255px;">'+result.politicalFeedBackVOList[i].cmPoliticalFeedBack+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter CM Political Feedback ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].cmPoliticalFeedBack" id="cmPoliticalFeedBack'+i+'" class="cmPoliticalFeedBack" style="width: 255px;"></textarea>';
			str += '</td>';
		}
		if(result.politicalFeedBackVOList[i].cmSource != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter CM Political Source ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].cmSource">'+result.politicalFeedBackVOList[i].cmSource+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter CM Political Source ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].cmSource" ></textarea>';
			str += '</td>';
		}
		if(result.politicalFeedBackVOList[i].otherPoliticalFeedBack != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Other Political Feedback  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].otherPoliticalFeedBack" id="OtherPoliticaFeedback'+i+'" class="OtherPoliticaFeedback" style="width: 255px;">'+result.politicalFeedBackVOList[i].otherPoliticalFeedBack+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Other Political Feedback  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].otherPoliticalFeedBack" id="OtherPoliticaFeedback'+i+'" class="OtherPoliticaFeedback" style="width: 255px;"></textarea>';
			str += '</td>';
		}
		if(result.politicalFeedBackVOList[i].otherSource != null)
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Other Political Source  ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].otherSource" >'+result.politicalFeedBackVOList[i].otherSource+'</textarea>';
			str += '</td>';
		}
		else
		{
			str += '<td>';
			str += '<textarea placeholder="Please Enter Other Political Source  ..." rows="2" cols="25" class="span12" style="width: 100px;" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].otherSource" ></textarea>';
			str += '</td>';
		}
		
		str += '</tr>';
	}
	str += '</table>';
	str += '</div>';
	str += '</div>';
	str += '<div align="center"><input value="Submit" onClick="validateFields(\'edit\')" class="btn btn-success" style="width: 57px;"></div>';
	str += '<input type="hidden" value="edit" name="politicalFeedBackVO.type"></input>';
	str += '</form>';

	$('#formFillDiv').html(str);
}

function selectDatePicker()
{
	$('#pcDate').datepicker({
		maxDate:new Date(),
	});
}

function deletedPoliticalFeedBack(id)
{
	var jsObj =
	{ 
		pfbId : id,
		task:"deletedPoliticalFeedBack"
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "deletedPoliticalFeedBadkAction.action?"+rparam;						
	callAjax(jsObj,url);
}

function removeActionItems(name){
var isConfirm = confirm("Are you want to delete ?")
  if( isConfirm == true ){
     $("#"+name+"").remove();
	  return true;
   }else{
	  return false;
   }
  }
</script>
</body>
</html>