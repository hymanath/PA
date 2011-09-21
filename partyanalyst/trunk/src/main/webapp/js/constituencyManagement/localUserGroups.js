

function buildLocalUserGroupsCriteria(jsObj,results)
{	
	if(jsObj.task != "reGetLocalGroupsInAConstituency")
		buildDifferentViewsRadio(results,"differentViewsRadioDiv_localGroups","local groups");

	createCoulmnChartForLocalUserGroups(results,"localGroupsChartDiv_main");
	buildRegionWiseOverViewDataForLocalUserGroups(results,"localGroupsRegionWiseOverView_main");	

	if(results.regionWiseOverview != null)
		getSubLevelLocalGroupData(results.regionWiseOverview.regionId,results.regionWiseOverview.regionName,results.regionWiseOverview.regionType,"VILLAGE/WARD",results.regionWiseOverview.regionTitle,results.regionWiseOverview.regionTitleId,true);
	else
	{
		var elmt = document.getElementById("localGroupsRegionsData_main");
		if(elmt)
			elmt.innerHTML = '<p class="zeroPeoplePara"> No groups to display.</p>';
	}
}

function createCoulmnChartForLocalUserGroups(data,divId)
{
	var categories = data.categoryListOverview;
	var regionData = data.regionWiseOverview;
	
	if(categories == null || regionData == null)
	{
		var elmt = document.getElementById(divId);

		if(elmt)
			elmt.innerHTML = '<p class="zeroPeoplePara"> No groups to build a graph.</p>';

		return;
	}

	
	var subRegions = regionData.subRegionWiseOverview;

	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Region');
	data.addColumn('number', ''+regionData.regionTitle);

	for(var i=0; i<categories.length; i++)
		data.addColumn('number', ''+categories[i].regionTitle);
	
	var regions = new Array();
	for(var i=0; i<subRegions.length; i++)
	{
		regions.push(''+subRegions[i].subRegionName);
	}

	data.addRows(regions.length);

	for(var i=0; i<subRegions.length; i++)
	{
		data.setValue(i, 0, ''+subRegions[i].subRegionName);
		data.setValue(i, 1, subRegions[i].countValue);
		for(var j=0; j<categories.length; j++)
			data.setValue(i, j+2, categories[j].subRegionWiseOverview[i].countValue);
	}

	var chart = new google.visualization.ColumnChart(document.getElementById(divId));
		chart.draw(data, {width: 850, height: 400,legend:'right',legendTextStyle:{fontSize:10}, title: 'Local Groups',
				  hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:25, titleTextStyle: {color: 'red'}}
				 });
	

}

function buildRegionWiseOverViewDataForLocalUserGroups(info,divId)
{
	var elmt = document.getElementById(divId);
	
	if(!elmt)
		return;
	
	var data = info.regionWiseOverview;
	
	if(data == null)
	{
		elmt.innerHTML = '<p class="zeroPeoplePara"> No groups to display.</p>';
		return;
	}

	localGroupsLoginUserRegionId = data.regionId;
	localGroupsLoginUserRegionName = data.regionName;
	localGroupsLoginUserRegionType = data.regionType;

	var str = '';
	str += getRegionWiseOverviewString(data,"show");
	
	if(info != null && info.length !=0)
	{
		for(var i=0; i<info.categoryListOverview.length; i++)
			str += getRegionWiseOverviewString(info.categoryListOverview[i],"hide");
	}

	elmt.innerHTML = str;
}

function getRegionWiseOverviewString(data,type)
{	
	var id; 

	if(data.regionTitle.indexOf(' ') != -1)
	{
		id = data.regionTitle.substring(0,data.regionTitle.indexOf(' '));
	}
	else
	{
		id = data.regionTitle;
	}

	
	localGroupsArray.push(id);
	var availablePeople = new Array();
	var zeroPeople = new Array();
	var subRegions = data.subRegionWiseOverview;

	for(var i=0; i<subRegions.length; i++)
	{
		if(subRegions[i].countValue == 0)
			zeroPeople.push(subRegions[i]);
		else
			availablePeople.push(subRegions[i]);
	}

	var str = '';
	str += '<div id="'+id+'_main" class="regionScope_main" style="cursor:pointer;">';
	str += '<div id="'+id+'_head" onclick="showGroupDetails(this.id,\''+data.regionId+'\',\''+data.regionName+'\',\''+data.regionType+'\',\''+data.regionTitle+'\',\''+data.regionTitleId+'\')">';
	str += '<table cellspacing="0" cellpadding="0" border="0" width="100%" style="width: 100%;">';
	str += '	<tr>';
	str += '		<td width="3px"><img src="images/icons/electionResultsAnalysisReport/first.png"></td>';
	str += '		<td align="left"><div class="regionsHead_center" style="height:30px;padding:0px;"><span class="regionsHead_center_label">';
	str +=				data.regionTitle +' - ';
	str += '			<a href="javascript:{}" style="color:#4B74C6" class="regionCountAnc" onclick="openLocalGroupsCandidatesPopup(\''+data.regionId+'\',\''+data.regionName+'\',\''+data.regionType+'\',\''+data.regionTitle+'\',\''+data.regionTitleId+'\')">'+data.countValue+' </a></span></div></td>';
	str += '		<td width="3px"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
	str += '	</tr>';
	str += '</table>';
	str += '</div>';
	
	if(type == "show")
		str += '<div id="'+id+'_body" style="display:block;">';
	else if(type == "hide")
		str += '<div id="'+id+'_body" style="display:none;">';
	
	str += '<div class="regions_overview_data">';
	str += '<table width="100%"><tr>';
	str += '<th align="left">Region:</th>';
	str += '<td align="left">'+data.regionName+'('+data.regionType+')</td>';
	str += '</tr></table>';
	str += '</div>';

	str += '<div id="region_available">';	
	if(subRegions.length == 0)
	{
		str += '<p class="zeroPeoplePara"> No regions under the '+data.regionName+' '+data.regionType+' are having local Groups.</p>';
		elmt.innerHTML = str;
		return;
	}

	if(availablePeople.length == 0)
	{
		str += '<p class="zeroPeoplePara"> No regions under the '+data.regionName+' '+data.regionType+' are having local Groups.</p>';
	}
	else
	{
		str += '<table class="region_available_head" width="100%">';
		for(var j=0; j<availablePeople.length; j++)
		{				
			str += '<tr>';
			str += '<td align="left" width="10px"><img src="images/icons/districtPage/listIcon.png"></td>';
			str += '<td align="left">'+availablePeople[j].subRegionName+'</td>';
			str += '<td><a href="javascript:{}" style="color:#494237" class="regionCountAnc" onclick="openLocalGroupsCandidatesPopup(\''+availablePeople[j].subRegionId+'\',\''+availablePeople[j].subRegionName+'\',\''+availablePeople[j].subRegionType+'\',\''+data.regionTitle+'\',\''+data.regionTitleId+'\')">'+availablePeople[j].countValue+'</a></td>';
			str += '</tr>';
		}
		str += '</table>';		
	}
	str += '</div>';

	str += '<div id="region_unavailable">';
	str += '<div id="region_unavailable_head"> Regions Having No local Groups </div>';
	if(zeroPeople.length == 0)
	{
		str += '<p class="zeroPeoplePara"> All the regions under the '+data.regionName+' '+data.regionType+' are having local Groups.</p>';
	}
	else
	{
		str += '<table class="region_available_head" width="100%">';
		for(var j=0; j<zeroPeople.length; j++)
		{				
			str += '<tr>';
			str += '<td align="left" width="10px"><img src="images/icons/districtPage/listIcon.png"></td>';
			str += '<td align="left">'+zeroPeople[j].subRegionName+' </td>';
			//str += '<td>'+zeroPeople[j].countValue+'</td>';
			str += '</tr>';
		}
		str += '</table>';
	}

	str += '</div>';
	str += '</div>';
	str += '</div>';

	return str;
}

function buildSubLevelLocalGroupPeople(jsObj,data)
{
	var results = data.regionWiseOverview;

	var lelmt = document.getElementById("localGroupsLabelSpan");
	var relmt = document.getElementById("localGroupsRegionsList");
	var radioelmt = document.getElementById("localGroupsRegionsTypeRadio");
	var elmt = document.getElementById("localGroupsRegionsData_main");
	
	if(!lelmt || !relmt || !elmt || !radioelmt)
		return;

	lelmt.innerHTML = jsObj.regionTitle+' Detail Info';
	
	var img = document.getElementById("influenceBusyCursor");
	if(img)
		img.style.display = "none";
	
	var aStr = '';
	if(jsObj.status && (data.isAreaTypeRadio || data.isAreaTypeRadio == null) && data.areaTypeRadioOptions != null && data.areaTypeRadioOptions.length > 0)
	{
		aStr += '<table>';
		aStr += '<tr>';
		aStr += '<th>Please select report level to view its influence people</th>';
		aStr += '<td>'
		for(var radio = 0; radio<data.areaTypeRadioOptions.length; radio++)
		{
			if(radio == 0)
				aStr += '<input type="radio" checked="checked" onclick="showLocalGroupPeopleByArea(this.value,\''+jsObj.regionTitle+'\',\''+jsObj.regionTitleId+'\')" name="areaTypeRadio_influencePeople" value="'+data.areaTypeRadioOptions[radio].name+'">'+data.areaTypeRadioOptions[radio].name;
			else
				aStr += '<input type="radio" name="areaTypeRadio_influencePeople" onclick="showLocalGroupPeopleByArea(this.value,\''+jsObj.regionTitle+'\',\''+jsObj.regionTitleId+'\')" value="'+data.areaTypeRadioOptions[radio].name+'">'+data.areaTypeRadioOptions[radio].name;
		}
		aStr += '</td>'
		aStr += '</tr>';
		aStr += '</table>';

		radioelmt.innerHTML = aStr;
	}	
	else if(data.areaTypeRadioOptions == null || data.areaTypeRadioOptions.length == 0)
	{
		radioelmt.innerHTML = '';
	}

	/*var rStr = '';	
	
	if(jsObj.status && data.regionsList.length > 0)
	{
		rStr += '<table width="100%">';
		rStr += '<tr>';
		rStr += '<td style="font-weight:bold;font-size:11px;color:#4B74C6">Select sub regions under '+jsObj.regionName+' '+jsObj.regionType+' to view its Local Groups</td>';
		rStr += '<td>';
		rStr += '<select onchange="getSubLevelLocalGroupDataLabel(this,\''+jsObj.regionTitle+'\',\''+jsObj.regionTitleId+'\')">';
		rStr += '<option value="0">All</option>';
		for(var option=0; option<data.regionsList.length; option++)
		{
			rStr += '<option value="'+data.regionsList[option].subRegionId+'_'+data.regionsList[option].subRegionType+'">'+data.regionsList[option].subRegionName+'</option>';
		}
		rStr += '</select>';
		rStr += '</td>';
		rStr += '<td><img id="influenceBusyCursor" style="display:none;" src="images/icons/partypositions.gif"></td>';
		rStr += '</tr>';
		rStr += '</table>';
		
		relmt.innerHTML = rStr;
	}*/
	
	var display = true;

	var str = '';
	for(var i=0; i<results.length; i++)
	{
		if(results[i].countValue == 0)
			continue;
		
		localGroupIndex.push(i);
		var availableRegions = new Array();
		var zeroRegions = new Array();
			
		for(var k=0; k<results[i].subRegionWiseOverview.length; k++)
		{
			if(results[i].subRegionWiseOverview[k].countValue == 0)
				zeroRegions.push(results[i].subRegionWiseOverview[k]);
			else 
				availableRegions.push(results[i].subRegionWiseOverview[k]);
		}

		str += '<div id="localGroupsDetailData_'+i+'_main" class="influenceDetailData_main">';
		str += '<div id="localGroupsDetailData_'+i+'_head" class="influenceDetailData_head" onclick="showLocalGroupDetailDataBody(this.id)" style="cursor:pointer">';	
		str += '<div class="scopeWise_head" style="font-size:11px;">';
		str += '<table><tr>';
		str += '<td><img src="images/icons/system_grps.png"></td>';
		str += '<td>'+results[i].regionName+' ( '+results[i].regionType+' ) - ';
		str += '<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openLocalGroupsCandidatesPopup(\''+results[i].regionId+'\',\''+results[i].regionName+'\',\''+results[i].regionType+'\',\''+jsObj.regionTitle+'\',\''+jsObj.regionTitleId+'\')">'+results[i].countValue+'</a></td>';
		str += '</tr></table>';
		str += '</div>';
		str += '</div>';
		if(display)
		{
			str += '<div id="localGroupsDetailData_'+i+'_body" class="influenceDetailData_body" style="display:block;">';
			display = false;
		}
		else
			str += '<div id="localGroupsDetailData_'+i+'_body" class="influenceDetailData_body">';

		str += '<table width="100%">';
		str += '	<tr>';			
		str += '		<td width="50%" valign="top">';
		str += '			<div id="LGSubRegionChartDiv_'+i+'_main"></div>';
		str += '		</td>';
		str += '		<td width="50%" valign="top">';
		str += '			<div id="localGroupsDetailData_available" class="availableRegionsData_main">';
		str += '			<div id="localGroupsDetailData_available_head" class="availableRegionsData_head"> Regions Having Local Groups</div>';
		str += '			<div id="localGroupsDetailData_zero_body" class="availableRegionsData_body">';
		if(availableRegions.length == 0)
		{
			str += '<p class="zeroPeoplePara"> No regions under the '+results[i].regionName+' '+results[i].regionType+' are having Local Groups.</p>';
		}
		else
		{
			str += '			<table width="100%" border="0" class="influenceDetailData_table">';
			for(var j=0; j<availableRegions.length; j++)
			{
				str += '			<tr>';
				str += '			<td><img width="8" height="8" src="images/icons/constituencyPage/bullet_blue.png"></td>';
				str += '			<th width="80%" align="left">'+availableRegions[j].subRegionName+'</th>';
				str += '			<td width="15%" align="left"><a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openLocalGroupsCandidatesPopup(\''+availableRegions[j].subRegionId+'\',\''+availableRegions[j].subRegionName+'\',\''+availableRegions[j].subRegionType+'\',\''+jsObj.regionTitle+'\',\''+jsObj.regionTitleId+'\')">'+availableRegions[j].countValue+'</a></td>';
				str += '			</tr>';
			}
			str += '			</table>';
		}
		str += '			</div>';
		str += '			</div>';
		str += '			<div id="localGroupsDetailData_zero" class="zeroRegionsData_main">';
		str += '			<div id="localGroupsDetailData_zero_head" class="zeroRegionsData_head"> Regions Having No Local Groups</div>';
		str += '			<div id="localGroupsDetailData_zero_body" class="zeroRegionsData_body">';
		if(zeroRegions.length == 0)
		{
			str += '<p class="zeroPeoplePara"> All regions under the '+results[i].regionName+' '+results[i].regionType+' are having Local Groups.</p>';
		}
		else
		{
			str += '			<table width="100%" border="0" class="influenceDetailData_table">';
			for(var j=0; j<zeroRegions.length; j++)
			{
				str += '			<tr>';
				str += '			<td><img width="8" height="8" src="images/icons/constituencyPage/bullet_blue.png"></td>';
				str += '			<th width="80%" align="left">'+zeroRegions[j].subRegionName+'</th>';			
				str += '			</tr>';
			}
			str += '			</table>';
		}
		str += '			</div>';
		str += '			</div>';
		str += '		</td>';	
		str += '	</tr>';
		str += '</table>';
		str += '</div>';
		str += '</div>';
	}
	
	elmt.innerHTML = str;

	buildSubRegionsPieChart(results,"localGroups");
}

function showLocalGroupPeopleByArea(value,regionTitle,regionTitleId)
{
	var regionId = localGroupsLoginUserRegionId;
	var regionName = localGroupsLoginUserRegionName;
	var regionType = localGroupsLoginUserRegionType;
	var regionTitle = regionTitle;
	var regionTitleId = regionTitleId;
	var areaType = value;
	
	getSubLevelLocalGroupData(regionId,regionName,regionType,areaType,regionTitle,regionTitleId,false)
}

function showGroupDetails(id,regionId,regionName,regionType,regionTitle,regionTitleId)
{
	var bodyId = id.substring(0,id.indexOf('_'))+"_body";
	
	for(var i=0; i<localGroupsArray.length; i++)
	{
		var elmt = document.getElementById(localGroupsArray[i]+"_body");
		if(elmt && elmt.style.display == "block")		
			$("#"+localGroupsArray[i]+"_body").slideUp("slow");
	}

	$("#"+bodyId).slideDown("slow");	

	getSubLevelLocalGroupData(regionId,regionName,regionType,"VILLAGE/WARD",regionTitle,regionTitleId,true);
}

function getSubLevelLocalGroupDataLabel(elmt,regionTitle,regionTitleId)
{
	var img = document.getElementById("influenceBusyCursor");
	if(img)
		img.style.display = "block";

	var value = elmt.options[elmt.selectedIndex].value;
	var regionName;
	var regionId;
	var regionType;
	
	if(value == 0)
	{
		regionId = localGroupsLoginUserRegionId;
		regionName = localGroupsLoginUserRegionName;
		regionType = localGroupsLoginUserRegionType;
		regionTitle = regionTitle;
		regionTitleId = regionTitleId;
	}
	else
	{
		regionName = elmt.options[elmt.selectedIndex].text;
		regionId = value.substring(0,value.indexOf('_'));
		regionType = value.substring(value.indexOf('_')+1,value.length);
		regionTitle = regionTitle;
		regionTitleId = regionTitleId;
	}	

	getSubLevelLocalGroupData(regionId,regionName,regionType,regionTitle,regionTitleId,false)
}

function showLocalGroupDetailDataBody(divId)
{	
	var bodyDivId = divId.substring(0,divId.lastIndexOf('_'))+"_body";

	for(var i=0; i<localGroupIndex.length; i++)
	{
		var elmt = document.getElementById("localGroupsDetailData_"+localGroupIndex[i]+"_body");
		if(elmt && elmt.style.display == "block")		
			$("#localGroupsDetailData_"+localGroupIndex[i]+"_body").slideUp("slow");
	}

	$("#"+bodyDivId).slideDown("slow");
}

function showInfluenceDetailDataBody(divId)
{	
	var bodyDivId = divId.substring(0,divId.lastIndexOf('_'))+"_body";

	for(var i=0; i<availableLength; i++)
	{
		var elmt = document.getElementById("influenceDetailData_"+i+"_body");
		if(elmt && elmt.style.display == "block")		
			$("#influenceDetailData_"+i+"_body").slideUp("slow");
	}

	$("#"+bodyDivId).slideDown("slow");
}

function openLocalGroupsCandidatesPopup(regionId,regionName,regionType,regionTitle,regionTitleId)
{	
	var urlStr = "localGroupsPeopleDataAction.action?windowTask=influencingPersonInfoPopup&regionId="+regionId+"&regionName="+regionName+"&regionType="+regionType+"&regionTitle="+regionTitle+"&regionTitleId="+regionTitleId;
	var browser2 = window.open(urlStr,"influencingPersonInfoPopup","scrollbars=yes,height=570,width=1300,left=200,top=50");	
	browser2.focus();
}