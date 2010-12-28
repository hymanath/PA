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
	
	callAjax(jsObj, url);
}

function clearError()
{
	var alertDivEl = document.getElementById("alertDiv");
	alertDivEl.innerHTML = '';
}

function showProblemDetails(result)
{
	var divEl = document.getElementById("problemDetails");
	var str = '';
	
	str+='<table width="100%" border="0" cellspacing="0" cellpadding="0">';
	str+='<tr>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>';
	str+='<td width="98%">';
	str+='<div class="productFeatureHeaderBackground_center" style="text-decoration:none;">';
	str+='<span class="headerLabelSpan" style="text-decoration:none;">Problem Complete Details</span>';
	str+='</div>';
	str+='</td>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>';
	str+='</tr>';
	str+='</table>';
	str+='<div class="divInfo">';
	str+='<h3>Problem</h3>';
	str+='<div id="description">'+result.problem+'</div><hr>';
	str+='<h3>Description</h3>';
	str+='<div id="description"><p>'+result.description+'</p></div><hr>';
	str+='<p>';
	if(result.impactLevel == 'STATE')
	{
		str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+'</span><BR>';
	} else if(result.impactLevel == 'DISTRICT')
	{
		str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'CONSTITUENCY')
	{
		if(result.constituency == null)
			str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.district+', '+result.state+'</span><BR>';
		else
			str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.state+'</span><BR>';
		
	} else if(result.impactLevel == 'MANDAL')
	{
		str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.constituency+','+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'VILLAGE')
	{
		str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.tehsil+', '+result.constituency+','+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'WARD')
	{
		str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.localBody+','+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'BOOTH')
	{
		if(result.tehsil != null)
			str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.tehsil+', '+result.constituency+', '+result.district+', '+result.state+'</span><BR>';
		if(result.localBody != null)
			str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.localBody+', '+result.district+', '+result.state+'</span><BR>';
	} else if(result.impactLevel == 'MUNICIPAL-CORP-GMC')
	{
		str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.district+', '+result.state+'</span><BR>';
	}	
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Impact Level: <span class="bluetext">'+result.impactLevel+'</span><BR>';
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Status: <span class="bluetext">'+result.status+'</span></p><hr>';
	str+='<p><img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported Date: <span class="bluetext">'+result.postedDate+'</span><BR>';
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Existing From: <span class="bluetext">'+result.existingFrom+'</span><BR>';
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported By: <span class="bluetext">'+result.name+'</span></p>';
	str+='</div>';
	divEl.innerHTML = str;
}
function showProblemAllComments(results)
{
	var showAllPostsDivEl = document.getElementById("showAllPostsDiv");
	
	var str = '';
	str+='<table width="100%" border="0" cellspacing="0" cellpadding="0">';
	str+='<tr>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_left.jpg"/></td>';
	str+='<td width="98%">';
	str+='<div class="productFeatureHeaderBackground_center" style="text-decoration:none;">';
	str+='<span class="headerLabelSpan" style="text-decoration:none;">Previous Posts</span>';
	str+='</div>';
	str+='</td>';
	str+='<td width="1%"><img width="25" height="40" src="images/icons/homePage_new/blue_header_top_right.jpg"/></td>';
	str+='</tr>';
	str+='</table>';
	str+='<div class="divInfo" style="text-align:left;">';
	if(results.length == 0)
		str+='<div id="description">No Previous Posts</div><hr>';
	for(var i in results)
	{
		str+='<div style="border:1px solid #AAAAAA;margin:2px;padding:5px;">';
		str+='<table width="100%" border="0">';
		str+='<tr>';
		if(results[i].isApproved == 'Accept')
			str+='<td width="2%"><img src="images/icons/accept.png" height="22"/></td>';
		else if(results[i].isApproved == 'Reject')
			str+='<td width="2%"><img src="images/icons/reject.png" height="22"/></td>';
		str+='<td width="68%">';
		str+='<div style="font-family:verdana;color:#707070;font-weight:bold;">'+results[i].userName+'</div>';
		str+='</td>';		
		str+='<th width="10%">';
		str+='Posted On:';
		str+='</th>';
		str+='<td width="20%">';
		str+='<div>'+results[i].lastUpdate+'</div>';
		str+='</td>';
		str+='</tr>';
		str+='</table><hr>';
		str+='<h3>Comment</h3>';
		str+='<div id="description">'+results[i].reason+'</div>';
		str+='</div>';
	}
	str+='<div>';
	showAllPostsDivEl.innerHTML = str;
}
function submitHandler(val)
{
	var rasonTextEl = document.getElementById("rasonText");
	var alertDivEl = document.getElementById("alertDiv");
	var reason = rasonTextEl.value;
	alertDivEl.innerHTML = '';
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
	if(rasonTextEl)
		rasonTextEl.focus();
	else return;
}