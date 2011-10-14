function getProblemCompleteDetails(id)
{
	var jsObj = {
			problemHistoryId: id,
			task: 'getProblemCompleteDetails'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "getProblemCompleteDetailsAction.action?"+rparam;
	
	callAjax(jsObj, url);
}

function getProblemAllComments(id)
{
	var jsObj = {
			id: id,
			task: 'getProblemAllComments'
	};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "problemCommentsAction.action?"+rparam;
	
	//callAjax(jsObj, url);

	custom_paginator.paginator({
		startIndex:0,
		resultsCount:5,
		jsObj:jsObj,
		ajaxCallURL:url,
		paginatorElmt:"custom_paginator_class",
		callBackFunction:function(){
			showProblemAllComments(results);
		}
	});
	custom_paginator.initialize();
}

function clearError()
{
	var alertDivEl = document.getElementById("alertDiv");
	alertDivEl.innerHTML = '';
}

function showFileUpload()
{
	
var fileUploadDivEle = document.getElementById("fileUploadDiv");
var hideRadioDivEle= document.getElementById("hideTd");
if(fileUploadDivEle.style.display =='none')
	fileUploadDivEle.style.display = 'block';
else
    fileUploadDivEle.style.display = 'none';
    hideRadioDivEle.style.display = 'none';
}

 function alltrim(str) {
                return str.replace(/^\s+|\s+$/g, '');
            }
function validateUploadFilds()
{
var titleFieldEle  = document.getElementById("titleField").value;
var fileDescriptionEle  = document.getElementById("fileDescription").value;
var userImageEle  = document.getElementById("userImage").value;
var errMsgDivEle	 = document.getElementById("errorMsgDivId");
var str = '';
var flag = false;


if(alltrim(titleFieldEle).length == 0)
	{
		str += 'Title is Required<BR>';
		flag = true;
	}
if(alltrim(fileDescriptionEle).length == 0)
	{
		str += 'Description is Required<BR>';
		flag = true;
	}
	
if(userImageEle.length == 0)
	{
		str += 'File is Required<BR>';
		flag = true;
	}
	
	errMsgDivEle.innerHTML = str;
	if(flag)
		return false;
	
	return true;
}

function showProblemDetails(result)
{
	
	var divEl = document.getElementById("problemDetails");
	var str = '';
	
	str+='<div align="left">'
	str+='<table width="100%" border="0" cellspacing="0" cellpadding="0">';
	str+='<tr>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>';
	str+='<td width="98%">';
	str+='<div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">';
	str+='<span class="headerLabelSpan2" style="text-decoration:none;">Problem Complete Details</span>';
	str+='</div>';
	str+='</td>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>';
	str+='</tr>';
	str+='</table>';
	str+='<div class="divInfo">';
	str+='<h3><b>Problem</b></h3>';
	str+='<div id="description">'+result.problem+'</div><hr>';
	str+='<h3><b>Description</b></h3>';
	str+='<div id="description"><p>'+result.description+'</p></div><hr>';
	str+='<p>';
	if(result.impactLevel == 'STATE')
	{
		str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+'</span><BR>';
	} else if(result.impactLevel == 'DISTRICT')
	{
		str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'CONSTITUENCY')
	{
		if(result.constituency == null)
			str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.district+', '+result.state+'</span><BR>';
		else
			str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.state+'</span><BR>';
		
	} else if(result.impactLevel == 'MANDAL')
	{
		str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.constituency+','+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'VILLAGE')
	{
		str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.tehsil+', '+result.constituency+','+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'WARD')
	{
		str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.localBody+','+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'BOOTH')
	{
		if(result.tehsil != null)
			str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.tehsil+', '+result.constituency+', '+result.district+', '+result.state+'</span><BR>';
		if(result.localBody != null)
			str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.localBody+', '+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'MUNICIPAL-CORP-GMC')
	{
		str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.district+', '+result.state+'</span><BR>';
	}	
	str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Impact Level: <span class="bluetext">'+result.impactLevel+'</span><BR>';
	str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Status: <span class="bluetext">'+result.status+'</span></p><hr>';
	str+='<p><img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported Date: <span class="bluetext">'+result.postedDate+'</span><BR>';
	str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Existing From: <span class="bluetext">'+result.existingFrom+'</span><BR>';
	str+='<img height="5" width="7" src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported By: <span class="bluetext">'+result.name+'</span></p><hr>';
	str+='</div></div>';
	divEl.innerHTML = str;
	var jsObj = {
				task: 'checkApprovalStatus',
				id: id	
		};
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
	var url = "saveProblemApproveDetailsAction.action?"+rparam;
		
	approvalCallAjax(jsObj,url);		
}


function postFilesAndImages()
{
   if(!uploadFormValidation()){
	var uploadHandler = {
			upload: function(o) {
				uploadResult = o.responseText;
				getMessage();				
			}
		};

	
	YAHOO.util.Connect.setForm('uploadPicForm',true);
	YAHOO.util.Connect.asyncRequest('POST', 'postImagesAndFilesAction.action', uploadHandler);
	}
	return;
   }

      function getMessage()
		{
			alert("File Uploaded Successfully...");
			emptyFields();
		}

function uploadFormValidation()
{
	
	var elmt1 = document.getElementById("titleField");
	var elmt2 = document.getElementById("fileDescription");
	var elmt3 = document.getElementById("userImage");
	
	var textFieldValue = elmt1.value;
	var textAreaValue = elmt2.value;
	var fileValue = elmt3.value;
	document.getElementById("alertMsg1").innerHTML ='';
   	document.getElementById("alertMsg2").innerHTML ='';
    document.getElementById("alertMsg3").innerHTML ='';


	if(alltrim(elmt1.value) ==''){
		document.getElementById("alertMsg1").innerHTML ='<font color="red">Please enter Title</font>';
		
		return true;
	}
	
	if(alltrim(elmt2.value) ==''){
		document.getElementById("alertMsg2").innerHTML ='<font color="red">Please enter Description</font>';
		
		return true;
	}

	if(alltrim(elmt3.value) ==''){
		document.getElementById("alertMsg3").innerHTML ='<font color="red">Please enter File</font>';
		
		return true;
	}
	
   }

   function emptyFields()
   {
    document.getElementById("titleField").value='';
	 document.getElementById("fileDescription").value='';
	 document.getElementById("userImage").value='';
   }


function showProblemAllComments(results)
{
	var showAllPostsDivEl = document.getElementById("showAllPostsDiv");
	var str = '';
	if(results.problemApproovals == null || results.problemApproovals.length == 0)
	{
		str+='<div id="description">No Previous Posts</div><hr>';
		showAllPostsDivEl.innerHTML = str;
		return;
	}
			
	str+='<table width="100%" border="0" cellspacing="0" cellpadding="0">';
	str+='<tr>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>';
	str+='<td width="98%">';
	str+='<div class="productFeatureHeaderBackground_center2" style="text-decoration:none;">';
	str+='<table border="0" width="100%" style="color:white;margin:2px;">';
	str+='<tr>';
	str+='<td width="85%"><span class="headerLabelSpan2" style="text-decoration:none;position:static;">Previous Posts</span></td>';	
	str+='<td width="1%"><img title="No Of People Accepted" height="20" title="Accepted" width="20" src="images/icons/accept.png"/>';
	str+='</td>';
	if(results.acceptedCount == null)
	{
		str+='<th width="1%">0';
		str+='</th>';			
	} else 
	{
		str+='<th width="1%">'+results.acceptedCount+'';
		str+='</th>';
		
	}	
	str+='<td width="1%"><img height="20" title="No of People Rejected" title="Rejected" width="20" src="images/icons/reject.png"/>';
	str+='</td>';
	if(results.rejectedCount == null)
	{
		str+='<th width="1%">0';
		str+='</th>';
		
	} else {
		str+='<th width="1%">'+results.rejectedCount+'';
		str+='</th>';		
	}
	str+='<th width="10%">Total Posts:';
	str+='</th>';
	str+='<th width="1%">'+results.totalResultsCount+'';
	str+='</th>';	
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>';
	str+='</tr>';
	str+='</table>';
	str+='<div class="divInfo" style="text-align:left;">';
	str+='<div class="custom_paginator_class"></div>';
	for(var i in results.problemApproovals)
	{
		str+='<div style="border:1px solid #AAAAAA;margin:2px;padding:5px;">';
		str+='<table width="100%" border="0">';
		str+='<tr>';
		if(results.problemApproovals[i].isApproved == 'Accept')
			str+='<td width="2%"><img title="Accepted" src="images/icons/accept.png" height="22"/></td>';
		else if(results.problemApproovals[i].isApproved == 'Reject')
			str+='<td width="2%"><img title="Rejected" src="images/icons/reject.png" height="22"/></td>';
		else if(results.problemApproovals[i].isApproved == 'FollowUp')
			str+='<th width="2%">RE:</th>';
		str+='<td width="68%">';
		str+='<div style="font-family:verdana;color:#707070;font-weight:bold;">'+results.problemApproovals[i].userName+'</div>';
		str+='</td>';		
		str+='<th width="10%">';
		str+='Posted On:';
		str+='</th>';
		str+='<td width="20%">';
		str+='<div>'+results.problemApproovals[i].lastUpdate+'</div>';
		str+='</td>';
		str+='</tr>';
		str+='</table><hr>';
		str+='<h3>Comment</h3>';
		str+='<div id="description">'+results.problemApproovals[i].reason+'</div>';
		str+='</div>';
	}
	str+='<div class="custom_paginator_class"></div>';
	str+='<div>';
	showAllPostsDivEl.innerHTML = str;
}
function submitHandler(val)
{
	var rasonTextEl = document.getElementById("rasonText");
	var alertDivEl = document.getElementById("alertDiv");
	var rasonTextEl1 = document.getElementById("rasonText1");
	var alertDivEl1 = document.getElementById("alertDiv1");
	alertDivEl.innerHTML = '';
	alertDivEl1.innerHTML = '';
	var reason;
	if(val != 'FollowUp')
		reason = rasonTextEl.value;
	else 
		reason = rasonTextEl1.value;
	if(reason == '')
	{
		alertDivEl.innerHTML = 'Please give any comment';
	} else
	{
		var jsObj = {
				reason : reason,
				isAccepted : val,
				task: 'saveProblemApprovalData',
				id: id	
		};
		var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);				
		var url = "saveProblemApproveDetailsAction.action?"+rparam;
		
		approvalCallAjax(jsObj, url);		
	}
		
}

function executeOnload()
{
	getProblemCompleteDetails(id);
	getProblemAllComments(id);
	var rasonTextEl = document.getElementById("rasonText");
	var rasonTextEl1 = document.getElementById("rasonText1");
	if(rasonTextEl)
		rasonTextEl.focus();
	if(rasonTextEl1)
		rasonTextEl1.focus();	
	
}