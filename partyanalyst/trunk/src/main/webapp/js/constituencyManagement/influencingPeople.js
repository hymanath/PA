

function populateInfluencingPeople(jsObj,results)
{		
	if(jsObj.task != "reGetInfluencingPeopleInAConstituency")
		buildDifferentViewsRadio(results,"differentViewsRadioDiv_influencingPeople","influence people");

	createCoulmnChart(results.regionWiseOverview,"influencePeopleChartDiv_main");
	buildRegionWiseOverViewData(results.regionWiseOverview,"influencePeopleRegionWiseOverView_main");
	buildScopeWiseOverViewData(results.influenceScopeOverview,"influencePeopleScopeWiseOverView_main");
	getSubLevelInfluenceData(results.regionWiseOverview.regionId,results.regionWiseOverview.regionName,results.regionWiseOverview.regionType,"VILLAGE/WARD","",0,true);
}

function createCoulmnChart(regionData,divId)
{
	var subRegions = regionData.subRegionWiseOverview;

	var data = new google.visualization.DataTable();
	
	var regions = new Array();
	for(var i=0; i<subRegions.length; i++)
	{
		regions.push(''+subRegions[i].subRegionName);
	}


	data.addColumn('string', 'Region');
	data.addColumn('number', 'Influence People');	

	data.addRows(regions.length);
	
	for(var i=0; i<subRegions.length; i++)
	{
		data.setValue(i, 0, ''+subRegions[i].subRegionName);
		data.setValue(i, 1, subRegions[i].countValue);
	}

	var chart = new google.visualization.ColumnChart(document.getElementById(divId));
		chart.draw(data, {width: 850, height: 280,legend:'right',legendTextStyle:{fontSize:10}, title: 'Influence People',
				  hAxis: {textStyle:{fontSize:'10'},slantedText:true, slantedTextAngle:75, titleTextStyle: {color: 'red'}}
				 });

}

function buildRegionWiseOverViewData(data,divId)
{
	var elmt = document.getElementById(divId);
	
	if(!elmt)
		return;

	loginUserRegionId = data.regionId;
	loginUserRegionName = data.regionName;
	loginUserRegionType = data.regionType;
	
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
	str += '<div id="regionScope_main">';
	str += '<div id="region_available">';
	str += '<table cellspacing="0" cellpadding="0" border="0" width="100%" style="width: 100%;">';
	str += '	<tr>';
	str += '		<td width="3px"><img src="images/icons/electionResultsAnalysisReport/first.png"></td>';
	str += '		<td align="left"><div class="regionsHead_center" style="height:30px;padding:0px;">';
	str += '			<span class="regionsHead_center_label">';
	str +=                 data.regionName+'('+data.regionType+')-';
	if(data.countValue !=0){
	str += '				<a href="javascript:{}" style="color:#4B74C6" class="regionCountAnc" onclick="openCandidatesPopup(\'0\',\''+data.regionId+'\',\''+data.regionName+'\',\''+data.regionType+'\',\'region\')">'+data.countValue+' </a>';
	}
	else{
     str += '<td style="color:#4B74C6">'+data.countValue+' </td>';
	}
	str += '			</span>';
	str += '		</div></td>';
	str += '		<td width="3px"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
	str += '	</tr>';
	str += '</table>';
	
	if(subRegions.length == 0)
	{
		str += '<p class="zeroPeoplePara"> No regions under the '+data.regionName+' '+data.regionType+' are having influencing people.</p>';
		elmt.innerHTML = str;
		return;
	}

	if(availablePeople.length == 0)
	{
		str += '<p class="zeroPeoplePara"> No regions under the '+data.regionName+' '+data.regionType+' are having influencing people.</p>';
	}
	else
	{
		str += '<table width="100%" class="region_available_head">';
		for(var j=0; j<availablePeople.length; j++)
		{				
			str += '<tr>';
			str += '<td width="10px"><img src="images/icons/districtPage/listIcon.png"></td>';
			str += '<td align="left">'+availablePeople[j].subRegionName+'</td>';
			str += '<td><a href="javascript:{}" style="color:#494237" class="regionCountAnc" onclick="openCandidatesPopup(\''+data.regionId+'\',\''+availablePeople[j].subRegionId+'\',\''+availablePeople[j].subRegionName+'\',\''+availablePeople[j].subRegionType+'\',\'region\')">'+availablePeople[j].countValue+'</a></td>';
			str += '</tr>';
		}
		str += '</table>';		
	}
	str += '</div>';

	str += '<div id="region_unavailable">';
	str += '<div id="region_unavailable_head"> Regions Having Zero Influence People </div>';
	if(zeroPeople.length == 0)
	{
		str += '<p class="zeroPeoplePara"> All the regions under the '+data.regionName+' '+data.regionType+' are having influencing people.</p>';
	}
	else
	{
		str += '<table width="100%" class="region_available_head">';
		for(var j=0; j<zeroPeople.length; j++)
		{				
			str += '<tr>';
			str += '<td width="10px"><img src="images/icons/districtPage/listIcon.png"></td>';
			str += '<td align="left">'+zeroPeople[j].subRegionName+'</td>';
			//str += '<td>'+zeroPeople[j].countValue+'</td>';
			str += '</tr>';
		}
		str += '</table>';
	}

	str += '</div>';
	str += '</div>';

	elmt.innerHTML = str;

}

function buildScopeWiseOverViewData(data,divId)
{
	var elmt = document.getElementById(divId);
	if(!elmt)
		return;
	
	var str = '';
	str += '<div id="influencePeopleScopeWiseOverView_head">';
	str += '<table cellspacing="0" cellpadding="0" border="0" width="100%" style="width: 100%;">';
	str += '	<tr>';
	str += '		<td width="3px"><img src="images/icons/electionResultsAnalysisReport/first.png"></td>';
	str += '		<td align="left"><div class="regionsHead_center" style="height:30px;padding:0px;"><span class="regionsHead_center_label"> Based On Influence Scope </span></div></td>';
	str += '		<td width="3px"><img src="images/icons/electionResultsAnalysisReport/second.png"></td>';
	str += '	</tr>';
	str += '</table>';
	str += '</div>';

	str += '<div id="influencePeopleScopeWiseOverView_body">';	
	if( data == null || data.length == 0)
	{
		str += '<p class="zeroPeoplePara"> No regions are having influence People.</p>';
	}
	else
	{
		for(var i=0; i<data.length; i++)
		{
			str += '<div class="scopeWise_data_main">';
			str += '<div class="scopeWise_head">';
			str += '<table width="100%" class="scopeWise_head_table">';
			str += '<tr>';
			str += '<td><img src="images/icons/system_grps.png"></td>';
			str += '<td align="left">'+data[i].influenceScope+' level scope - <a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\'0\',\'0\',\'\',\''+data[i].influenceScope+'\',\'scope\')">'+data[i].countValue+'</a></td>';
			str += '</tr>';
			str += '</table>';
			str += '</div>';
			str += '<div class="scopeWise_body">';
			str += '<table class="region_available_head" width="100%">';		
			for(var j=0 ; j<data[i].influenceScopeDetails.length; j++)
			{
				str += '<tr>';
				str += '<td><img src="images/icons/districtPage/listIcon.png"></td>';
				str += '<td align="left">'+data[i].influenceScopeDetails[j].influenceScopeRegion+'</td>';
				str += '<td><a href="javascript:{}" style="color:#494237" class="regionCountAnc" onclick="openCandidatesPopup(\'0\',\''+data[i].influenceScopeDetails[j].influenceScopeRegionId+'\',\''+data[i].influenceScopeDetails[j].influenceScopeRegion+'\',\''+data[i].influenceScope+'\',\'scope\')">'+data[i].influenceScopeDetails[j].countValue+'</a></td>';			
				str += '</tr>';
			}
			str += '</table>';
			str += '</div>';
			str += '</div>';
		}
	}
	str += '</div>';
	
	elmt.innerHTML = str;
}

function buildSubLevelInfluencePeople(jsObj,data)
{
	var results = data.regionWiseOverview;
	var relmt = document.getElementById("influencePeopleRegionsList");
	var radioelmt = document.getElementById("influencePeopleRegionsTypeRadio");
	var elmt = document.getElementById("influencePeopleRegionsData_main");
	
	if(!relmt || !elmt || !radioelmt)
		return;
	
	var img = document.getElementById("influenceBusyCursor");
	if(img)
		img.style.display = "none";
	
	var aStr = '';
	if(jsObj.status && (data.isAreaTypeRadio || data.isAreaTypeRadio == null)  && data.areaTypeRadioOptions != null && data.areaTypeRadioOptions.length > 0)
	{
		aStr += '<table>';
		aStr += '<tr>';
		aStr += '<th>Please select report level to view its influence people</th>';
		aStr += '<td>'
		for(var radio = 0; radio<data.areaTypeRadioOptions.length; radio++)
		{
			if(radio == 0)
				aStr += '<input type="radio" checked="checked" onclick="showInfluencePeopleByArea(this.value)" name="areaTypeRadio_influencePeople" value="'+data.areaTypeRadioOptions[radio].name+'">'+data.areaTypeRadioOptions[radio].name;
			else
				aStr += '<input type="radio" name="areaTypeRadio_influencePeople" onclick="showInfluencePeopleByArea(this.value)" value="'+data.areaTypeRadioOptions[radio].name+'">'+data.areaTypeRadioOptions[radio].name;
		}
		aStr += '</td>'
		aStr += '</tr>';
		aStr += '</table>';

		radioelmt.innerHTML = aStr;
	}	
	else if(!data.isAreaTypeRadio || data.areaTypeRadioOptions == null || data.areaTypeRadioOptions.length == 0)
	{
		radioelmt.innerHTML = '';
	}

	/*var rStr = '';	
	if(jsObj.status && data.regionsList.length > 0)
	{
		rStr += '<table width="100%">';
		rStr += '<tr>';
		rStr += '<td style="font-weight:bold;font-size:11px;color:#4B74C6">Select sub regions under '+jsObj.regionName+' '+jsObj.regionType+' to view its influence people</td>';
		rStr += '<td>';
		rStr += '<select onchange="getSubLevelInfluenceDataLabel(this)">';
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
		
		//relmt.innerHTML = rStr;
	}*/
	
	var display = true;
	var str = '';
	for(var i=0; i<results.length; i++)
	{
		if(results[i].countValue == 0)
			continue;
		
		availableLength++;
		var availableRegions = new Array();
		var zeroRegions = new Array();

		if(results[i].subRegionWiseOverview != null)
		{
			
			for(var k=0; k<results[i].subRegionWiseOverview.length; k++)
			{
				if(results[i].subRegionWiseOverview[k].countValue == 0)
					zeroRegions.push(results[i].subRegionWiseOverview[k]);
				else 
					availableRegions.push(results[i].subRegionWiseOverview[k]);
			}

		}

		str += '<div id="influenceDetailData_'+i+'_main" class="influenceDetailData_main">';
		str += '<div id="influenceDetailData_'+i+'_head" class="influenceDetailData_head" onclick="showInfluenceDetailDataBody(this.id)" style="cursor:pointer">';	
		str += '<div class="scopeWise_head" style="font-size:11px;">';
		str += '<table><tr>';
		str += '<td><img src="images/icons/system_grps.png"></td>';
		str += '<td>'+results[i].regionName+' ( '+results[i].regionType+' ) - ';
		str += '<a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+jsObj.regionId+'\',\''+results[i].regionId+'\',\''+results[i].regionName+'\',\''+results[i].regionType+'\',\'region\')">'+results[i].countValue+'</a></td>';
		str += '</tr></table>';
		str += '</div>';
		str += '</div>';
		if(display)
		{
			str += '<div id="influenceDetailData_'+i+'_body" class="influenceDetailData_body" style="display:block;">';
			display = false;
		}
		else
			str += '<div id="influenceDetailData_'+i+'_body" class="influenceDetailData_body">';
		str += '<table width="100%">';
		str += '	<tr>';			
		str += '		<td width="50%" valign="top">';
		str += '			<div id="subRegionChartDiv_'+i+'_main"></div>';
		str += '		</td>';
		str += '		<td width="50%" valign="top">';
		str += '			<div id="influenceDetailData_available" class="availableRegionsData_main">';
		str += '			<div id="influenceDetailData_available_head" class="availableRegionsData_head"> Regions Having Influencing People</div>';
		str += '			<div id="influenceDetailData_zero_body" class="availableRegionsData_body">';
		if(availableRegions.length == 0)
		{
			str += '<p class="zeroPeoplePara"> No regions under the '+results[i].regionName+' '+results[i].regionType+' are having influencing people.</p>';
		}
		else
		{
			str += '			<table width="100%" border="0" class="influenceDetailData_table">';
			for(var j=0; j<availableRegions.length; j++)
			{
				str += '			<tr>';
				str += '			<td><img width="8" height="8" src="images/icons/constituencyPage/bullet_blue.png"></td>';
				str += '			<th width="80%" align="left">'+availableRegions[j].subRegionName+'</th>';
				str += '			<td width="15%" align="left"><a href="javascript:{}" style="color:#77471D" class="regionCountAnc" onclick="openCandidatesPopup(\''+results[i].regionId+'\',\''+availableRegions[j].subRegionId+'\',\''+availableRegions[j].subRegionName+'\',\''+availableRegions[j].subRegionType+'\',\'region\')">'+availableRegions[j].countValue+'</a></td>';
				str += '			</tr>';
			}
			str += '			</table>';
		}
		str += '			</div>';
		str += '			</div>';
		str += '			<div id="influenceDetailData_zero" class="zeroRegionsData_main">';
		str += '			<div id="influenceDetailData_zero_head" class="zeroRegionsData_head"> Regions Having Zero Influencing People</div>';
		str += '			<div id="influenceDetailData_zero_body" class="zeroRegionsData_body">';
		if(zeroRegions.length == 0)
		{
			str += '<p class="zeroPeoplePara"> All regions under the '+results[i].regionName+' '+results[i].regionType+' are having influencing people.</p>';
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
	
	if(availableLength == 0)
	{
		str = '<p class="zeroPeoplePara"> No regions are having influence people.</p>';
	}

	elmt.innerHTML = str;
	
	buildSubRegionsPieChart(results,"influencePeople");
}

function showInfluencePeopleByArea(value)
{
	var regionId = loginUserRegionId;
	var regionName = loginUserRegionName;
	var regionType = loginUserRegionType;
	var areaType = value;
	

	getSubLevelInfluenceData(regionId,regionName,regionType,areaType,"",0,false);
}

function getSubLevelInfluenceDataLabel(elmt)
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
		regionId = loginUserRegionId;
		regionName = loginUserRegionName;
		regionType = loginUserRegionType;
	}
	else
	{
		regionName = elmt.options[elmt.selectedIndex].text;
		regionId = value.substring(0,value.indexOf('_'));
		regionType = value.substring(value.indexOf('_')+1,value.length);
	}	

	getSubLevelInfluenceData(regionId,regionName,regionType,"",0,false);
}

function openCandidatesPopup(parentRegionId,regionId,regionName,regionType,scopeType)
{
	var urlStr = "influencingPeopleDataAction.action?windowTask=influencingPersonInfoPopup&parentRegionId="+parentRegionId+"&regionId="+regionId+"&regionName="+regionName+"&regionType="+regionType+"&scopeType="+scopeType;
	var browser2 = window.open(urlStr,"influencingPersonInfoPopup","scrollbars=yes,height=570,width=1300,left=200,top=50");	
	browser2.focus();
}