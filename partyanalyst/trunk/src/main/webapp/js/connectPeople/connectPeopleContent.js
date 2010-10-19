

function buildConnectUsersContent(connectedPeopleData)
{
	var bodyElmt = document.getElementById('districtPeopleConnect_body');

	var bodyStr='';
	bodyStr+='<div id="connectedNumberDiv"> ';
	bodyStr+='<span><img height="20" width="25" src="/PartyAnalyst/images/icons/constituencyPage/groups.png"></img></span>';
	bodyStr+='<span style="position:relative;left:5px;top:-5px;"> '+connectedPeopleData.length+' people connected to this district </span>';
	bodyStr+='</div>';
	bodyStr+='<div id="connectedPersonsDiv">';
	bodyStr+='<table width="100%">';
	for(var i =0; i<connectedPeopleData.length; i++)
	{
		if(i == 3)
			break;
		bodyStr+='<tr>';
		bodyStr+='<td>';
		bodyStr+='<table width="100%">';
		bodyStr+='<tr>';
		bodyStr+='<td rowspan="2" width="25%"><span><img height="40" width="35" src="/PartyAnalyst/images/icons/constituencyPage/human1.png"/></span></td>';
		bodyStr+='<td align="left"><span class="groupPersonNameSpanClass">'+connectedPeopleData[i].candidateName+'</span></td>';
		bodyStr+='</tr>';
		bodyStr+='<tr>';	
		bodyStr+='<td align="right"><span class="groupPersonMessageSpanClass">';
		if(userLoginStatus == "false")
			bodyStr+='<a href="connectPeopleAction.action?redirectLoc=DISTRICT&districtId='+districtId+'&districtName='+districtName+'">Connect</a>';
		else
		{
			if(connectedPeopleData[i].status == "NOTCONNECTED")
			{
				bodyStr+='<font color="#7F5A22" style="padding-right:10px;">Not Connected</font>';
				bodyStr+=' - <a href="javascript:{}" onclick="showConnectConfirmDialogBox(\''+connectedPeopleData[i].id+'\',\''+connectedPeopleData[i].candidateName+'\',\''+connectedPeopleData[i].constituencyName+'\')">Connect</a>';
			}
			else if(connectedPeopleData[i].status == "CONNECTED")
				bodyStr+='<font color="#4A610B" style="padding-right:10px;">Connected</font>';
			else if(connectedPeopleData[i].status == "PENDING")
				bodyStr+='<font color="#4A610B" style="padding-right:10px;">Request Pending</font>';
		}

		bodyStr+='</span></td>';
		bodyStr+='</tr>';
		bodyStr+='</table>';
		bodyStr+='</td>';
		bodyStr+='</tr>';

		bodyStr+='<tr>';
		bodyStr+='<td><div style="border:1px solid #F1F3F5;margin:0px 5px 0px 5px"></div></td>';
		bodyStr+='</tr>';
	}

	bodyStr+='</table>';
	bodyStr+='</div>';

	bodyStr+='<div id="viewAllPersonConnectDiv">';
	bodyStr+='<table width = "100%"><tr>';
	bodyStr+='<td align="right">';
	if(userLoginStatus == "false")
	{
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&districtId='+districtId+'&districtName='+districtName+'" class="connectAnc">Login</a> </span>';
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="anonymousUserAction.action" class="connectAnc">Register</a> </span>';
		bodyStr+='<span class="connectAncSpan" style="padding-right:10px;font-weight:bold;"> <a href="connectPeopleAction.action?redirectLoc=CONNECT_REDIRECT&districtId='+districtId+'&districtName='+districtName+'" class="connectAnc">View All</a> </span>';
	}
	else
	{
		bodyStr+='<span class="connectAncSpan"> <a href="connectPeopleAction.action" class="connectAnc">Redirect To User Page</a> </span>';
		bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" onclick="showAllConnectPeopleWindow()" class="connectAnc">View All</a> </span>';
	}
	//bodyStr+='<span class="connectAncSpan"> | </span>';
	//bodyStr+='<span class="connectAncSpan"> <a href="javascript:{}" class="connectAnc"> Connect </a> </span>';
	bodyStr+='</td>';
	bodyStr+='</tr></table>';
	bodyStr+='</div>';

	if(bodyElmt)
		bodyElmt.innerHTML=bodyStr;

}