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
					<li class="active"><a data-toggle="tab" value="Upload News" style="cursor:pointer;color: #005580;" onclick="showPoliticalFeedBack();" style="cursor:pointer;color: #005580;" > PoliticalFeedBack Form</a>	
					</li>
					<li class="">
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
		<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Action Item :<a class="btn btn-mini pull-right" href="javascript:{}"  title="Click here to add another Action Item" onClick="addMoreActionItem();"><i class="icon-plus"></i></a></label>
		<textarea placeholder="Please Enter Action Item  ..." rows="2" cols="50" class="span12" name="politicalFeedBackVO.actionItems[0]" id="actionItem0" ></textarea>
		<div id="addedActionItemDiv"></div>
	</div>
	<div class="row-fluid" >
		<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Summary : <font class="requiredFont">*</font></label>
		<textarea placeholder="Please Enter Summary  ..." rows="4" cols="50" class="span12" name="politicalFeedBackVO.summary" id="pcSummary" ></textarea>
	</div>

</div>

<div id="acWiseDiv">
	<!--<legend class="boxHeading"> Assembly Wise Daily Political Feedback </legend>
	<div class="row-fluid">
	<table class="table table-bordered" id="acWiseDetailsTable">
	<tr>
		<th>Assembly</th>
		<th>IMP News</th>
		<th>CM Political Feedback</th>
		<th>Other political feedback</th>
		<th>Delete</th>
	</tr>
	<tr>
		<td>
		<select id="assemblyList0" name = "politicalFeedBackVO.politicalFeedBackVOList[0].constituencyId">
		<option value="0">Select Assembly</option>
		<option value="1">Adilabad</option>
		</select>
		</td>
		<td>
		<textarea placeholder="Please Enter IMP News  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList[0].impNews" id="impNews0" class="impNews"></textarea>
		</td>
		<td>
		<textarea placeholder="Please Enter CM Political Feedback ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList[0].cmPoliticalFeedBack" id="cmPoliticalFeedBack0" class="cmPoliticalFeedBack"></textarea>
		</td>
		<td>
		<textarea placeholder="Please Enter Other Political Feedback  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList[0].otherPoliticalFeedBack" id="OtherPoliticaFeedback0" class="OtherPoliticaFeedback"></textarea>
		</td>
		<td></td>
	</tr>
	</table>
	</div>-->
	<!--<div><a class="btn btn-success" onClick="addnewTd()" style="float:right">Add More</a></div>-->
	
</div>
	<div align="center"><input value="Submit" onClick="validateFields()" class="btn btn-success" style="width: 57px;"></div>

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
					
			<s:select name="politicalFeedBackVO.parlimentId"  id="parlimentListview" list="constituencysList" theme="simple" listKey="id" listValue="name" onChange="getPoliticalFeedBack(this.value)"/>
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
	str += ' <span class="row-fluid" id="addedActionItem'+subjCount+'">';
	str += '<label style="font-size: 17px;font-weight: bold;line-height: 1.5;">Action Item : <a href="javascript:{}"  title="Click here to remove Action Item" onclick="removeSubject(\'addedActionItem'+subjCount+'\');"><i class="icon-trash pull-right" style="margin-left:15px;"></i></a></label>';
	str += '<textarea placeholder="Please Enter Action Item  ..." rows="2" cols="50" class="span12" name="politicalFeedBackVO.actionItems['+subjCount+']" id="actionItem'+subjCount+'" ></textarea></span>';
	subjCount++;
	$('#addedActionItemDiv').append(str);
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

/* function addnewTd()
{
	var str = "";
	str += '<tr>';
	str += '<td>';
	str += '<select id="assemblyList'+acCount+'" name = "politicalFeedBackVO.politicalFeedBackVOList['+acCount+'].constituencyId">';
	str += '<option value="0">Select Assembly</option>';
	str += '</select>';
	str += '</td>';
	str += '<td>';
	str += '<textarea placeholder="Please Enter IMP News  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+acCount+'].impNews" id="impNews'+acCount+'" class="impNews"></textarea>';
	str += '</td>';
	str += '<td>';
	str += '<textarea placeholder="Please Enter CM Political Feedback ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+acCount+'].cmPoliticalFeedBack" id="cmPoliticalFeedBack'+acCount+'" class="cmPoliticalFeedBack"></textarea>';
	str += '</td>';
	str += '<td>';
	str += '<textarea placeholder="Please Enter Other Political Feedback  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+acCount+'].otherPoliticalFeedBack" id="OtherPoliticaFeedback'+acCount+'" class="OtherPoliticaFeedback"></textarea>';
	str += '</td>';
	str += '<td></td>';
	str += '</tr>';
	$('#acWiseDetailsTable').append(str);
	acCount++;
} */
function submitForm()
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
	str += '<th>CM POLITICAL FEEDBACK</th>';
	str += '<th>OTHER POLITICAL FEEDBACK</th>';
	str += '</tr>';
	for(var i in result)
	{
		str += '<tr>';
		str += '<td>'+result[i].id+'</td>';
		str += '<td>'+result[i].name+'';
		
		str += '<input type="hidden" id="assemblyList'+i+'" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].constituencyId" value="'+result[i].id+'">';
		str += '</input>';

		str += '<td>';
		str += '<textarea placeholder="Please Enter IMP News  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].impNews" id="impNews'+i+'" class="impNews"></textarea>';
		str += '</td>';
		str += '<td>';
		str += '<textarea placeholder="Please Enter CM Political Feedback ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].cmPoliticalFeedBack" id="cmPoliticalFeedBack'+i+'" class="cmPoliticalFeedBack"></textarea>';
		str += '</td>';
		str += '<td>';
		str += '<textarea placeholder="Please Enter Other Political Feedback  ..." rows="2" cols="25" class="span12" name = "politicalFeedBackVO.politicalFeedBackVOList['+i+'].otherPoliticalFeedBack" id="OtherPoliticaFeedback'+i+'" class="OtherPoliticaFeedback"></textarea>';
		str += '</td>';
		str += '</tr>';
	}
	str += '</table>';
	str += '</div>';
	$('#acWiseDiv').html(str);
}

function getPoliticalFeedBack(id)
{
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
		str += '<legend class="boxHeading"> Parliment Wise Daily Political Feedback </legend>';
		str += '<div class="row-fluid">';
		str += '<table class="table table-bordered ">';
		str += '<tr>';
		str += '<th>P_CODE</th>';
		str += '<th>PARLIMENT_NAME</th>';
		str += '<th>ACTION ITEM(IF ANY)</th>';
		str += '<th>SUMMAR</th>';
		str += '</tr>';
		var length = result[i].actionItems.length;
		str += '<tr>';
		str += '<td rowspan='+length+'>'+result[i].parlimentId+'</td>';
		str += '<td rowspan='+length+'>'+result[i].parlimentName+'</td>';
		for(var j in result[i].actionItems)
		{
			if(j  != 0)
			{
				str += '<tr>';
			}
			str += '<td>'+result[i].actionItems[j]+'</td>';
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
		str += '<th>CM POLITICAL FEEDBACK</th>';
		str += '<th>OTHER POLITICAL FEEDBACK</th>';
		str += '</tr>';
		for(var j in result[i].politicalFeedBackVOList)
		{
			str += '<tr>';
			str += '<td>'+result[i].politicalFeedBackVOList[j].constituencyId+'</td>';
			str += '<td>'+result[i].politicalFeedBackVOList[j].assemblyName+'</td>';
			if(result[i].politicalFeedBackVOList[j].impNews != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].impNews+'</td>';
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
			if(result[i].politicalFeedBackVOList[j].otherPoliticalFeedBack != null)
			{
				str += '<td>'+result[i].politicalFeedBackVOList[j].otherPoliticalFeedBack+'</td>';
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

function validateFields()
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
		submitForm();
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
</script>
</body>
</html>