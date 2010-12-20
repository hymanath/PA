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

function showProblemDetails(result)
{
	var divEl = document.getElementById("problemDetails");
	var str = '';
	str+='<div class="bluebar_heading">'+result.problem+'</div><hr>';
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
	//str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;">Location: <span class="bluetext">'+result.problemLocation+', '+result.district+', '+result.state+'</span><BR>';
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Impact Level: <span class="bluetext">'+result.impactLevel+'</span><BR>';
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Status: <span class="bluetext">'+result.status+'</span></p><hr>';
	str+='<p><img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported Date: <span class="bluetext">'+result.postedDate+'</span><BR>';
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Problem Existing From: <span class="bluetext">'+result.existingFrom+'</span><BR>';
	str+='<img src="images/icons/districtPage/listIcon.png" style="margin-right:5px;margin-bottom:3px;">Reported By: <span class="bluetext">'+result.name+'</span></p><hr>';
	str+='';
	str+='';
	divEl.innerHTML = str;
}