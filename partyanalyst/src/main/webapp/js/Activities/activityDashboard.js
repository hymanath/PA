function getActivityNames(type)
{
	
	if(type=='onload'){
		$('#activityTypeList').val(1);
		$('#activityLevelList').val(1);
	}
	
	$('#ActivityList').find('option').remove();
	$('#ActivityList').append('<option value="0"> Select Activity </option>');	
	var jObj = {
			activityTypeId : $('#activityTypeList').val(),
			activityLevelId:$('#activityLevelList').val(),
			task:"activityDetails"
		};
		
		$.ajax({
          type:'GET',
          url: 'getActivityDetails.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){			
			if(result != null && result.length >0)
			{
				for(var i in result)
					$('#ActivityList').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			}
			if($("#activityTypeList").val()==4){
				$('#ActivityList').val(11);
			}else{
				$('#ActivityList').val(12);
			}
			getQuestionsForReportTypeAction();
			if(type == "onload"){
				//getTeamLeadersByActivityScope();
				//getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
				//getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
				getDetails();
			}
			//getQuestions(); 
			//getActivityQuestionAnswerCountReasonWise();
			
		});
		
}


function buildBDResult(result)
{
	
	$('#stateWiseViewDid').html('');
	var str='';
	for(var i in result.activityVoList){
		if(i==0)
			str+='<div class="table-responsive" >';
		else
			str+='<div class="table-responsive" style="margin-top: 25px;">';
		
	str+='<table class="table table-bordered bg_ff" >';
	str+='<tr class="text-center">';
	if(i==0)
		str+='<td class="pad_0" rowspan="2" style="box-sizing:none;"><img src="dist/activityDashboard/img/andhrap.jpg" class="img-responsive head-td" style="height:176px;" ></td>';
	else 
		str+='<td class="pad_0" rowspan="2" style="box-sizing:none;"><img src="dist/activityDashboard/img/telangana.jpg" class="img-responsive head-td" style="height:176px;" ></td>';
	//var i=0;
		if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].totalCount+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
		var totalMembers = 0;
		var totalCadreMembers = 0;
		var totalPublicMembers = 0;
		
		if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebCadreAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebCadreAttendance >0)
			totalCadreMembers = totalCadreMembers+result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebCadreAttendance;
		if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellCadreAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellCadreAttendance >0)
			totalCadreMembers = totalCadreMembers+result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellCadreAttendance;
		
		
		if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPublicAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPublicAttendance >0)
			totalPublicMembers = totalPublicMembers+result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPublicAttendance;
		if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPublicAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPublicAttendance >0)
			totalPublicMembers = totalPublicMembers+result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPublicAttendance;
		
		totalMembers = totalCadreMembers + totalPublicMembers;
		
		
		if(totalMembers != null && totalMembers >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+totalMembers+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	
	
	
	if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebCadreAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebCadreAttendance >0){
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebCadreAttendance+'';
			
			var perc = result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebCadreAttendance * 100 / totalMembers;
			perc = parseFloat(perc).toFixed(2);
			str+='<small class="font-10 text-danger" title="IVR covered percentage from total activities.">('+perc+'%)</small></h3></td>';
	}
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
		
	if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellCadreAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellCadreAttendance >0){
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellCadreAttendance+'';
			var perc = result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellCadreAttendance * 100 / totalMembers;
			perc = parseFloat(perc).toFixed(2);
			str+='<small class="font-10 text-danger" title="IVR covered percentage from total activities.">('+perc+'%)</small></h3><hr class="custom-hr"/></td>';
	}
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
		if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPublicAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPublicAttendance >0){
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPublicAttendance+'';
			var perc = result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPublicAttendance * 100 / totalMembers;
			perc = parseFloat(perc).toFixed(2);
			str+='<small class="font-10 text-danger" title="IVR covered percentage from total activities.">('+perc+'%)</small></h3><hr class="custom-hr"/></td>';
		}
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	
		
	if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPublicAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPublicAttendance >0){
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPublicAttendance+'';
			var perc = result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPublicAttendance * 100 / totalMembers;
			perc = parseFloat(perc).toFixed(2);
			str+='<small class="font-10 text-danger" title="IVR covered percentage from total activities.">('+perc+'%)</small></h3><hr class="custom-hr"/></td>';
	}
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
	
	if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPhotosAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPhotosAttendance >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].activityAttendanceInfoVOList[0].totalWebPhotosAttendance+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
		if(result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPhotosAttendance != null && result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPhotosAttendance >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].activityAttendanceInfoVOList[0].totalInfoCellPhotosAttendance+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
	str+='</tr>';
	str+='<tr>';

	str+='<td class="bg_ef text-center">TOTAL ACTIVITIES</td>';
	str+='<td class="bg_ef text-center">TOTAL MEMBERS ATTENDED</td>';
	str+='<td class="bg_ef text-center">CADRE ATTENDED (WEB) </td>';
	str+='<td class="bg_ef text-center">CADRE ATTENDED (TAB) </td>';
	str+='<td class="bg_ef text-center">PUBLIC ATTENDED (WEB)</td>';
	str+='<td class="bg_ef text-center">PUBLIC ATTENDED (TAB)</td>';
	str+='<td class="bg_ef text-center">PHOTO ATTACHED (WEB)</td>';
	str+='<td class="bg_ef text-center">PHOTO ATTACHED (TAB)</td>';
	
	str+='</tr>';
	str+='</table>';
	str+='</div>';
}
	$('#stateWiseViewDid').html(str);
	dynamicwidth();
}

function buildResult(result)
{
	$('#stateWiseViewDid').html('');
	var str='';
	
	str+='<div class="table-responsive">';
	str+='<table class="table table-bordered bg_ff" >';
	str+='<tr class="text-center">';
	str+='<td class="pad_0" rowspan="2" style="box-sizing:none;"><img src="dist/activityDashboard/img/andhrap.jpg" class="img-responsive head-td" style="height:176px;" ></td>';
	var i=0;
		if(result.totalCount != null && result.totalCount >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.totalCount+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	
		if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].plannedCount+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		
		if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].ivrcovered+'<small class="font-10 text-danger" title="IVR covered percentage from planned activities.">('+result.activityVoList[i].ivrcoveredPerc+'%)</small></h3><hr class="custom-hr"/>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	/*	if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].ivrcoveredPerc+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';*/
		if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].ivrNotPlanned+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
				str+='<td style="color:#a94442;"><h3 class="m_top20 m_bottom10" >'+result.activityVoList[i].ivrTotal+'<small class="font-10 text-danger" title="IVR covered percentage from total activities.">('+result.activityVoList[i].ivrTotalPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
				str+='<td style="color:#a94442;"><h3 class="m_top20 m_bottom10" >'+result.activityVoList[i].infoCellTotal+'<small class="font-10 text-danger" title="INFO cell covered percentage from total activities.">('+result.activityVoList[i].infoCellTotalPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
			str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].infoCellcovered+'<small class="font-10 text-danger" title="INFO Cell covered percentage from planned activities.">('+result.activityVoList[i].infoCellcoveredPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	/*	if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].infoCellcoveredPerc+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';*/
		if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].infoCellNotPlanned+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
		if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].whatsAppCovered+'<small class="font-10 text-danger" title="Whatsapp covered percentage from total INFO Cell covered activities.">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</small></h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].whatsAppCoveredPerc+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';*/
		if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
				str+='<td><h3 class="m_top20 m_bottom10">'+result.activityVoList[i].imagesCount+'</h3><hr class="custom-hr"/></td>';
		else
			str+='<td><h3 class="m_top20 m_bottom10"> - </h3><hr class="custom-hr"/></td>';
	
	
	str+='</tr>';
	str+='<tr>';

	str+='<td class="bg_ef text-center">TOTAL</td>';
	str+='<td class="bg_ef text-center">PLANNED</td>';
	
	str+='<td class="bg_ef text-center">IVR COVERED   <small>(PLANNED) </small></td>';
	//str+='<td class="bg_ef text-center">IVR COVERED %</td>';
	str+='<td class="bg_ef text-center"> IVR COVERED <small> (NOT-PLANNED)</small> </td>';
	str+='<td class="bg_ef text-center" style="color:#a94442;"> IVR TOTAL </td>';
	
	str+='<td class="bg_ef text-center"  style="color:#a94442;">INFO CELL TOTAL </td>';
	str+='<td class="bg_ef text-center">INFO CELL COVERED   <small>(PLANNED) </small> </td>';
	//str+='<td class="bg_ef text-center">INFO CELL COVERED %</td>';
	str+='<td class="bg_ef text-center">INFO CELL COVERED <small> (NOT-PLANNED)</small></td>';
	
	str+='<td class="bg_ef text-center"> IMAGES COVERED</td>';
	//str+='<td class="bg_ef text-center">WHATSAPP IMAGES COVERED %</td>';
	str+='<td class="bg_ef text-center">NO OF IMAGES RECIEVED</td>';
	
	
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	$('#stateWiseViewDid').html(str);
	dynamicwidth();
}
function getActivityDetailsBySearchCriteria(locationId,searchType,divId,teamSearchType,radioSearch,index)
{
	//alert(radioSearch);
	//aria-expanded
	if(searchType == 'booth'){
		$(".villageNameAnchorCls").addClass('collapsed');
		$(".villageNameAnchorCls").removeClass('bod bod-b');
	}
	/*else if(searchType == 'village'){
		$(".PlusnMinusSignV").addClass('collapsed');
		//$(".PlusnMinusSignV").attr('aria-expanded','true');
		$(".villageListCls").html("");		
		$(".mandalNameAnchorCls").removeClass('bod bod-b');
	}*/
	/*else if(searchType == 'mandal'){
		$(".PlusnMinusSignM").addClass('collapsed');
		//$(".PlusnMinusSignM").attr('aria-expanded','true');
		
		$(".mandalsLsitCls").html("");		
		$(".constiNameAnchorCls").removeClass('bod bod-b');
	}*/
	else if(searchType == 'constituency'){
		$(".PlusnMinusSignd").addClass('collapsed');
		//$(".PlusnMinusSignd").attr('aria-expanded','true');
		$(".constiListCls").html("");		
		$(".constiListCls").parent().find("div").removeClass('bod bod-b');
	}
		
	
	 
	if(locationId >0)
	{
		var name = $('#ActivityList :selected').text();
		var actiivityLevelStr = $('#activityLevelList :selected').text();
		$('#headingDiv').html(''+name.toUpperCase()+' - '+actiivityLevelStr.toUpperCase()+' LEVEL');
		if(searchType != "state" && searchType != "district"){
			if(showHideResults(divId)){
				$("#"+divId).html("");
				return;
			}
		}
		
		$('html,body').animate({
			scrollTop: $("#"+divId).offset().top},
        'slow');
		
		var dates=$('.searchDateCls ').val();
		  var dateArray=dates.split("/");
		  var fromDateStr=dateArray[0];
		  var toDateStr=dateArray[1];
		
		 var activityScopeId = $("#ActivityList").val();
		 var activityLevelId = $("#activityLevelList").val();
		 
		var teamLeaderId = 0;
		
		if(radioSearch == 'location'){
			if(teamSearchType == 'organizersWiseId'){
				if(searchType == 'district'){
					teamLeaderId = locationId;
				}
			}
		}
		 
		//console.log(dateArray.length);
			if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
			var jObj = {
			stateId:1,
			searchType:searchType,
			conditionType:"locationWiseResult",		
			startDate:fromDateStr,     //30-11-2015
			endDate:toDateStr,       //08-12-2015
			locationId:locationId,
			activityScopeId:activityScopeId,   //1
			activityLevelId:activityLevelId,   //1
			teamSearchType:teamSearchType,
			teamLeaderId:teamLeaderId,
			teamMemberId:0,
			radioSearch:radioSearch,
			districtId:0,
			task:"getActivityDetailsBySearchCriteria"
			};
			$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
			
			$.ajax({
			  type:'GET',
			  url: 'getActivityDetailsBySearchCriteria.action',
			 data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				//console.log(result);
				if(result != null)
					if(searchType == 'state'){
						if(activityLevelId == 5 && activityScopeId == 11 )
							buildBDResult(result);
						else if(activityLevelId == 4 && divId == 'alignmentWidth')
							buildsLocationsResult(result,divId,teamSearchType);
						else
							buildResult(result);
					}
					else if(searchType == 'district')
						buildsLocationsResult(result,divId,teamSearchType);
					else if(searchType == 'constituency')
						buildConstituencyResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'mandal' && index == 0)
						buildMandalResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'mandal' && index == 1)
						buildVillageResult(result,divId,locationId,index);
					else if(searchType == 'village')
						buildVillageResult(result,divId,locationId,index);
			});
	}		
	  
}

function getLeadersWiseActivityDetailsBySearchCriteria(locationId,searchType,divId,teamSearchType,radioSearch,index,districtId,leaderId,ledMemsearch)
{
	$('#lcnExcelBtn').hide();
	if(searchType == 'booth'){
		$(".villageNameAnchorCls").addClass('collapsed');
		$(".villageNameAnchorCls").removeClass('bod bod-b');
	}
	else if(searchType == 'village'){
		$(".PlusnMinusSignV").addClass('collapsed');
		//$(".PlusnMinusSignV").attr('aria-expanded','true');
		$(".villageListCls").html("");		
		$(".mandalNameAnchorCls").removeClass('bod bod-b');
	}
	else if(searchType == 'mandal'){
		$(".PlusnMinusSignM").addClass('collapsed');
		//$(".PlusnMinusSignM").attr('aria-expanded','true');
		
		$(".mandalsLsitCls").html("");		
		$(".constiNameAnchorCls").removeClass('bod bod-b');
	}
	/*else if(searchType == 'constituency'){
		$(".PlusnMinusSignd").addClass('collapsed');
		//$(".PlusnMinusSignd").attr('aria-expanded','true');
		$(".constiListCls").html("");		
		$(".constiListCls").parent().find("div").removeClass('bod bod-b');
	}*/
		
	
	 
	if(locationId >0)
	{
		var name = $('#ActivityList :selected').text();
		var actiivityLevelStr = $('#activityLevelList :selected').text();
		$('#headingDiv').html(''+name.toUpperCase()+' - '+actiivityLevelStr.toUpperCase()+' LEVEL');
		//if(searchType != "state" && searchType != "district"){
			if(showHideResults(divId)){
				$("#"+divId).html("");
				return;
			}
		//}
		
		$('html,body').animate({
			scrollTop: $("#"+divId).offset().top},
        'slow');
		
		var dates=$('.searchDateCls ').val();
		  var dateArray=dates.split("/");
		  var fromDateStr=dateArray[0];
		  var toDateStr=dateArray[1];
		
		 var activityScopeId = $("#ActivityList").val();
		 var activityLevelId = $("#activityLevelList").val();
		 //alert(leaderId);
		var teamLeaderId = 0;
		var teamMemberId = 0;
		if(leaderId != 0){
			if(ledMemsearch == 'member')
				teamMemberId = leaderId;
			else
				teamLeaderId = leaderId;
		}
		else if(radioSearch == 'location'){
			if(teamSearchType == 'organizersWiseId'){
				teamLeaderId = locationId;
			}
		}
		if(index == 3){
			locationId = districtId;
		}
		 
		//console.log(dateArray.length);
			if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
			var jObj = {
			stateId:1,
			searchType:searchType,
			conditionType:"locationWiseResult",		
			startDate:fromDateStr,     //30-11-2015
			endDate:toDateStr,       //08-12-2015
			locationId:locationId,
			activityScopeId:activityScopeId,   //1
			activityLevelId:activityLevelId,   //1
			teamSearchType:teamSearchType,
			teamLeaderId:teamLeaderId,
			teamMemberId:teamMemberId,
			radioSearch:radioSearch,
			districtId:districtId,
			task:"getActivityDetailsBySearchCriteria"
			};
			$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
			
			$.ajax({
			  type:'GET',
			  url: 'getActivityDetailsBySearchCriteria.action',
			 data : {task:JSON.stringify(jObj)} ,
			}).done(function(result){
				//console.log(result);
				if(result != null)
					if(searchType == 'state')
						buildResult(result);
					else if(searchType == 'district' && index == 0)
						buildsLocationsResult(result,divId,teamSearchType);
					else if(searchType == 'constituency' && index == 1)
						buildConstituencyResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'constituency' && index == 2)
						buildMandalResult(result,divId,locationId,teamSearchType);
					else if(searchType == 'constituency' && index == 3)
						buildVillageResult(result,divId,locationId);
			});
	}		
	  
}
//vvv

function buildVillageResult(result,divId,locationId,index)
{	
	var str='';
	str+='<button class="btn btn-success" id="villExcelBtn" onclick="generateExcel(\'villageWiseActivityDetailsExcelDivId\')" style="margin-left: 950px; margin-top: 5px; margin-bottom: 5px;">Export Excel</button>';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading panel-village villageNameAnchorCls" role="tab" id="headingOneLevelMandal1'+i+'">';
			if(index == 1){
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'village\',\'boothLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignV collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat11'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			else{
				str+='<span role="button" onclick="getActivityDetailsBySearchCriteria(0,\'booth\',\'\',\'locationWiseId\',\'location\',\'0\');">';
			}
			
				str+='<table class="table table-col table-condensed"  style="display:inline;color:#333;" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='<tr style="background:#F4A460">';
				}
				else
				{
					str+='<tr>';
				}
					str+='<td style="width:180px;">'+result.activityVoList[i].name+'</td>';
					if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
						str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
					else
						str+='<td class="dynChildWidth aligncenter"> - </td>';
					if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
							str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
					else
						str+='<td class="dynChildWidth2 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
							str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth3 aligncenter"> - </td>';
				/*	if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
							str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
					else
						str+='<td class="dynChildWidth2"> - </td>';*/
					if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
							str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
					else
						str+='<td class="dynChildWidth4 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
							str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
							str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
					
					if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
							str+='<td class="dynChildWidth7 aligncenter" >'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth7 aligncenter"> - </td>';
					/*if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
							str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
					else
						str+='<td class="dynChildWidth2"> - </td>';*/
					if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
							str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
					else
						str+='<td class="dynChildWidth8 aligncenter"> - </td>';
					if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
							str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
					else
						str+='<td class="dynChildWidth9 aligncenter"> - </td>';
					/*if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
							str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
					else
						str+='<td class="dynChildWidth2"> - </td>';*/
					if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
							str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
					else
						str+='<td class="dynChildWidth10 aligncenter"> - </td>';
					
					str+='</tr>';
				
				
				str+='</table>';
				if(index == 1)
					str+='</a>';
				else
					str+='</span>';
		//	str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
		if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='';
				}
				else {
					if(index == 1){
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.activityVoList[i].id+',\'dayWiseMandalInfo1'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\',\'\')">Day Wise</button>';
					}
					else{
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'village\','+result.activityVoList[i].id+',\'dayWisePanchayatInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\',\'\')">Day Wise</button>';
					}
				}
				str+='</div>';
			/*str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'village\','+result.activityVoList[i].id+',\'dayWisePanchayatInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
				str+='</div>';*/
				str+='</div>';
				if(index == 1){
					str+='<div id="collapseOne1LevelPanchayat11'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevelMandal1'+i+'">';
					str+='<div id="boothLevelId'+i+'" class="villageListCls"></div>';
					str+='</div>';
					str+='<div id="dayWiseMandalInfo1'+result.activityVoList[i].id+'"  class="daywiseSCls"></div>';
				}
				else{	
					str+='<div id="dayWisePanchayatInfo'+result.activityVoList[i].id+'" class="daywiseSCls"></div>';
					}
				str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
	
	var str1='';
	
	str1+='<table class="table table-bordered" style="border:1px">';
		str1+='<thead>';
			str1+='<th class="dynWidth" style="width:200px">LOCATION NAME</th>';
			str1+='<th class="getChildWidth">TOTAL</th>';
			str1+='<th class="getChildWidth2">PLANNED</th>';
			str1+='<th class="getChildWidth3">IVR COVERED   <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth4"> IVR COVERED (NOT-PLANNED) </th>';
			str1+='<th class="getChildWidth5" style="color:#a94442;"> IVR TOTAL </th>';
			str1+='<th class="getChildWidth6" style="color:#a94442;">INFO CELL TOTAL</th>';
			str1+='<th class="getChildWidth7">INFO CELL COVERED  <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth8">INFO CELL COVERED (NOT-PLANNED)</th>';
			str1+='<th class="getChildWidth9"> IMAGES COVERED</th>';
			str1+='<th class="getChildWidth10">NO OF  IMAGES RECIEVED</th>';
		str1+='</thead>';
		str1+='<tbody>';
		if(result.activityVoList != null && result.activityVoList.length>0)
		{
			for(var i in result.activityVoList)
			{
				str1+='<tr>';
					str1+='<td style="width:190px;">'+result.activityVoList[i].name+'</td>';
					if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
						str1+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
					else
						str1+='<td class="dynChildWidth aligncenter"> - </td>';
					if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
						str1+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
					else
						str1+='<td class="dynChildWidth2 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
						str1+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth3 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
						str1+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth4 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
						str1+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth7 aligncenter"> - </td>';
					if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
						str1+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth8 aligncenter"> - </td>';
					if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
						str1+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth9 aligncenter"> - </td>';
					if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
						str1+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
					else
						str1+='<td class="dynChildWidth10 aligncenter"> - </td>';
				
				str1+='</tr>';
			}
		}
		str1+='</tbody>';
	str1+='</table>';
	$('#villageWiseActivityDetailsExcelDivId').html(str1);
}

//mmm
function buildMandalResult(result,divId,locationId,teamSearchType)
{	
	var str='';
	if(teamSearchType == 'locationWiseId'){
		str+='<button class="btn btn-success" id="mandExcelBtn" onclick="generateExcel(\'mandalWiseActivityDetailsExcelDivId\')" style="margin-left: 950px; margin-top: 5px; margin-bottom: 5px;">Export Excel</button>';
	}
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{			
		//if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0){
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading mandalNameAnchorCls" role="tab" id="headingOneLevelMandal1'+i+'">';
			if(teamSearchType == 'organizersWiseId'){
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'mandal\',\'panchayatLevelId'+i+'\',\'locationWiseId\',\'location\',\'1\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignV collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			else{
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'village\',\'panchayatLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignV collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#collapseOne1LevelPanchayat1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			
			
				str+='<table class="table table-col table-condensed" style="display:inline" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0 )
				{
					str+='<tr style="background:#F4A460">';
				} 
				else{
					str+='<tr>';
				}
				
				str+='<td style="width:175px;">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';
			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+')</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
			/*if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
				str+='<tr>';
				str+='</table>';
				str+='</a>';
				//str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			//str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.activityVoList[i].id+',\'dayWiseMandalInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')">Day Wise</button>';
			if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0 )
				{
					str+='';
				}
				else {
					if(teamSearchType == 'organizersWiseId'){
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'constituency\','+result.activityVoList[i].id+',\'dayWiseMandalInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\',\'\')">Day Wise</button>';
					}
					else{
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.activityVoList[i].id+',\'dayWiseMandalInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\',\'\')">Day Wise</button>';
					}
				}
				str+='</div>';
				str+='<div id="collapseOne1LevelPanchayat1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevelMandal1'+i+'">';
				str+='<div id="panchayatLevelId'+i+'" class="villageListCls"></div>';
				str+='</div>';
				str+='<div id="dayWiseMandalInfo'+result.activityVoList[i].id+'"  class="daywiseSCls"></div>';
				str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			//}
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
	
	var str1='';
	
	str1+='<table class="table table-bordered" style="border:1px">';
		str1+='<thead>';
			str1+='<th class="dynWidth" style="width:200px">LOCATION NAME</th>';
			str1+='<th class="getChildWidth">TOTAL</th>';
			str1+='<th class="getChildWidth2">PLANNED</th>';
			str1+='<th class="getChildWidth3">IVR COVERED   <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth4"> IVR COVERED (NOT-PLANNED) </th>';
			str1+='<th class="getChildWidth5" style="color:#a94442;"> IVR TOTAL </th>';
			str1+='<th class="getChildWidth6" style="color:#a94442;">INFO CELL TOTAL</th>';
			str1+='<th class="getChildWidth7">INFO CELL COVERED  <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth8">INFO CELL COVERED (NOT-PLANNED)</th>';
			str1+='<th class="getChildWidth9"> IMAGES COVERED</th>';
			str1+='<th class="getChildWidth10">NO OF  IMAGES RECIEVED</th>';
		str1+='</thead>';
		str1+='<tbody>';
		if(result.activityVoList != null && result.activityVoList.length>0)
		{
			for(var i in result.activityVoList)
			{
				str1+='<tr>';
					str1+='<td style="width:190px;">'+result.activityVoList[i].name+'</td>';
					if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
						str1+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
					else
						str1+='<td class="dynChildWidth aligncenter"> - </td>';
					if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
						str1+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
					else
						str1+='<td class="dynChildWidth2 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
						str1+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth3 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
						str1+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth4 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
						str1+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth7 aligncenter"> - </td>';
					if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
						str1+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth8 aligncenter"> - </td>';
					if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
						str1+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth9 aligncenter"> - </td>';
					if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
						str1+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
					else
						str1+='<td class="dynChildWidth10 aligncenter"> - </td>';
				
				str1+='</tr>';
			}
		}
		str1+='</tbody>';
	str1+='</table>';
	$('#mandalWiseActivityDetailsExcelDivId').html(str1);
}
//ccc
function buildConstituencyResult(result,divId,locationId,teamSearchType)
{	
	var str='';
	if(teamSearchType == 'locationWiseId'){
		str+='<button class="btn btn-success" id="constiExcelBtn" onclick="generateExcel(\'constWiseActivityDetailsExcelDivId\')" style="margin-left: 950px; margin-top: 5px; margin-bottom: 5px;">Export Excel</button>';
	}
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
			{				
			str+='<div class="panel-body">';
			str+='<div class="panel-group panel-group1 m_0" id="accordion1Level1'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading constiNameAnchorCls" role="tab" id="headingOneLevel1'+i+'">';
			if(teamSearchType == 'organizersWiseId'){
				str+='<a role="button" onclick="getLeadersWiseActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'mandalLevelId'+i+'\',\'organizersWiseId\',\'location\',\'2\',\''+result.activityVoList[i].id+'\',\''+result.activityVoList[i].id+'\',\'member\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1Level1'+i+'" href="#collapseOne1LevelMandal1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			else{
				str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'mandal\',\'mandalLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion1Level1'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1Level1'+i+'" href="#collapseOne1LevelMandal1'+i+'" aria-expanded="true" aria-controls="collapseOne1LevelMandal1'+i+'">';
			}
			str+='<table class="table table-col table-condensed" style="display:inline" >';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='<tr style="background:#F4A460">';
				}
				else {
					str+='<tr>';
				}
				
				str+='<td style="width:185px;" class="removeDiv">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';

			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
			/*if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter" > - </td>';
			/*if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
				
				str+='<tr>';
				str+='</table>';
				str+='</a>';
				//str+='<button type="button" class="btn btn-custom btn-hover btn-xs"><i class="glyphicon glyphicon-align-justify"></i></button>';
			//str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'constituency\','+result.activityVoList[i].id+',\'dayWiseConstituencyInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\')" >Day Wise</button>';
				if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
				{
					str+='';
				}
				else {
					if(teamSearchType == 'organizersWiseId'){
						str+='';
					}
					else{
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'constituency\','+result.activityVoList[i].id+',\'dayWiseConstituencyInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\',\'\')" >Day Wise</button>';
					}
				}
				str+='</div>';
				
				str+='<div id="collapseOne1LevelMandal1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevel1'+i+'">';
				str+='<div id="mandalLevelId'+i+'" class="mandalsLsitCls"></div>';
				str+='</div>';
				str+='<div id="dayWiseConstituencyInfo'+result.activityVoList[i].id+'"  class="daywiseSCls"></div>';
				str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			str+='</div>';
			
			}
	}
	
	$('#'+divId+'').html(str);
	dynamicwidth();
	
	var str1='';
	
	str1+='<table class="table table-bordered" style="border:1px">';
		str1+='<thead>';
			str1+='<th class="dynWidth" style="width:200px">LOCATION NAME</th>';
			str1+='<th class="getChildWidth">TOTAL</th>';
			str1+='<th class="getChildWidth2">PLANNED</th>';
			str1+='<th class="getChildWidth3">IVR COVERED   <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth4"> IVR COVERED (NOT-PLANNED) </th>';
			str1+='<th class="getChildWidth5" style="color:#a94442;"> IVR TOTAL </th>';
			str1+='<th class="getChildWidth6" style="color:#a94442;">INFO CELL TOTAL</th>';
			str1+='<th class="getChildWidth7">INFO CELL COVERED  <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth8">INFO CELL COVERED (NOT-PLANNED)</th>';
			str1+='<th class="getChildWidth9"> IMAGES COVERED</th>';
			str1+='<th class="getChildWidth10">NO OF  IMAGES RECIEVED</th>';
		str1+='</thead>';
		str1+='<tbody>';
		if(result.activityVoList != null && result.activityVoList.length>0)
		{
			for(var i in result.activityVoList)
			{
				str1+='<tr>';
					str1+='<td style="width:190px;">'+result.activityVoList[i].name+'</td>';
					if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
						str1+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
					else
						str1+='<td class="dynChildWidth aligncenter"> - </td>';
					if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
						str1+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
					else
						str1+='<td class="dynChildWidth2 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
						str1+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth3 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
						str1+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth4 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
						str1+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth7 aligncenter"> - </td>';
					if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
						str1+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth8 aligncenter"> - </td>';
					if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
						str1+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth9 aligncenter"> - </td>';
					if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
						str1+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
					else
						str1+='<td class="dynChildWidth10 aligncenter"> - </td>';
				
				str1+='</tr>';
			}
		}
		str1+='</tbody>';
	str1+='</table>';
	$('#constWiseActivityDetailsExcelDivId').html(str1);
}
/*
if(searchType == 'mandal'){
		$(".PlusnMinusSignM").addClass('collapsed');
		$(".mandalsLsitCls").html("");		
		$(".mandalsLsitCls").parent().find("div").removeClass('bod bod-b');
	}
*/

var isAlreadyBuild = false;
//dddd
function buildsLocationsResult(result,divId,teamSearchType){
	
	$('#'+divId+'').html('');
	var str='';
	if(!isAlreadyBuild){
		
		isAlreadyBuild = true;
		
		str+='<table class="table table-col" style="border:1px solid #ccc" >';
		if(teamSearchType == 'organizersWiseId')
			str+='<caption class="cap-custom"><b>GENERAL SECRATORY WISE - VILLAGE/WARD</b></caption>';
		else
			str+='<caption class="cap-custom"><b>DISTRICT WISE - VILLAGE/WARD</b></caption>';
		str+='<tr class="font-12">';
		if(teamSearchType == 'organizersWiseId')
			str+='<td class="dynWidth" style="width:200px">GENERAL SECRETORY</td>';
		else
			str+='<td class="dynWidth" style="width:200px">LOCATION NAME</td>';
			
		str+='<td class="getChildWidth">TOTAL</td>';
		str+='<td class="getChildWidth2">PLANNED</td>';
		
		str+='<td class="getChildWidth3">IVR COVERED   <span style="font-size: 11px;">(PLANNED) </span></td>';
		//str+='<td class="getChildWidth3">IVR COVERED %</td>';
		str+='<td class="getChildWidth4"> IVR COVERED (NOT-PLANNED) </td>';
		str+='<td class="getChildWidth5" style="color:#a94442;"> IVR TOTAL </td>';
		
		str+='<td class="getChildWidth6" style="color:#a94442;">INFO CELL TOTAL</td>';
		str+='<td class="getChildWidth7">INFO CELL COVERED  <span style="font-size: 11px;">(PLANNED) </span></td>';
		//str+='<td class="getChildWidth7">INFO CELL COVERED %</td>';
		str+='<td class="getChildWidth8">INFO CELL COVERED (NOT-PLANNED)</td>';
		
		
		str+='<td class="getChildWidth9"> IMAGES COVERED</td>';
		//str+='<td class="getChildWidth9">WHATSAPP IMAGES COVERED %</td>';
		str+='<td class="getChildWidth10">NO OF  IMAGES RECIEVED</td>';
		
		str+='</tr>';
	}
	
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
		{
			str+='<tr>';
			str+='<td colspan="14" class="pad_0">';
			str+='<div class="panel-group m_0" id="accordion'+i+'" role="tablist" aria-multiselectable="true">';
			str+='<div class="panel panel-default panel-customtd">';
			
			str+='<div class="panel-heading" role="tab" id="headingOneLevel1'+i+'">';
			if(teamSearchType == 'organizersWiseId'){
				str+='<a role="button" onclick="getLeadersWiseActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'constituencyLevelId'+i+'\',\'organizersWiseId\',\'location\',\'1\',\'0\',\'0\',\'\');" class="accordion'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOneLevel1'+i+'" aria-expanded="true" aria-controls="collapseOneLevel1'+i+'">';
			}
			else{
			str+='<a role="button" onclick="getActivityDetailsBySearchCriteria(\''+result.activityVoList[i].id+'\',\'constituency\',\'constituencyLevelId'+i+'\',\'locationWiseId\',\'location\',\'0\');" class="accordion'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapseOneLevel1'+i+'" aria-expanded="true" aria-controls="collapseOneLevel1'+i+'">';
			}
			
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
			{
				str+='<tr style="background:#F4A460">';
			}
			else {
				str+='<tr>';
			}
			str+='<td style="width:190px;">'+result.activityVoList[i].name+'</td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
					str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';
			
			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
			/*if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
					str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<br/><span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
			

			str+='</tr>';
			str+='</table>';
			str+='</a>';
			if(result.activityVoList[i].infoCellTotal == null || result.activityVoList[i].infoCellTotal == 0)
			{
				str+='';
			}
			else {
				if(teamSearchType == 'organizersWiseId'){
					str+='';
				}
				else{
					if($("#activityLevelList").val() == 4)
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'state\','+result.activityVoList[i].id+',\'dayWiseInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\',\'\')">Day Wise</button>';
					else
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs " onclick="getDaywiseInfo(\'district\','+result.activityVoList[i].id+',\'dayWiseInfo'+result.activityVoList[i].id+'\',\''+result.activityVoList[i].name+'\',\'\')">Day Wise</button>';
				}
			}
			str+='</div>';			
			str+='<div id="constituencyLevelId'+i+'" class="constiListCls"> </div>';
			str+='<div id="collapseOneLevel1'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneLevel1'+i+'">';
		
		str+='</div>';
		str+='</div>';
		str+='<div id="dayWiseInfo'+result.activityVoList[i].id+'" class="daywiseSCls">';
		str+='</div>';
		
		str+='</td>';
		str+='</tr>';
		}		
	}

	str+='</table>';
	$('#'+divId+'').html(str);
	dynamicwidth();
	
	var str1='';
	
	str1+='<table class="table table-bordered" style="border:1px">';
		str1+='<thead>';
			str1+='<th class="dynWidth" style="width:200px">LOCATION NAME</th>';
			str1+='<th class="getChildWidth">TOTAL</th>';
			str1+='<th class="getChildWidth2">PLANNED</th>';
			str1+='<th class="getChildWidth3">IVR COVERED   <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth4"> IVR COVERED (NOT-PLANNED) </th>';
			str1+='<th class="getChildWidth5" style="color:#a94442;"> IVR TOTAL </th>';
			str1+='<th class="getChildWidth6" style="color:#a94442;">INFO CELL TOTAL</th>';
			str1+='<th class="getChildWidth7">INFO CELL COVERED  <span style="font-size: 11px;">(PLANNED) </span></th>';
			str1+='<th class="getChildWidth8">INFO CELL COVERED (NOT-PLANNED)</th>';
			str1+='<th class="getChildWidth9"> IMAGES COVERED</th>';
			str1+='<th class="getChildWidth10">NO OF  IMAGES RECIEVED</th>';
		str1+='</thead>';
		str1+='<tbody>';
		if(result.activityVoList != null && result.activityVoList.length>0)
		{
			for(var i in result.activityVoList)
			{
				str1+='<tr>';
					str1+='<td style="width:190px;">'+result.activityVoList[i].name+'</td>';
					if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount >0)
						str1+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
					else
						str1+='<td class="dynChildWidth aligncenter"> - </td>';
					if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
						str1+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
					else
						str1+='<td class="dynChildWidth2 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
						str1+='<td class="dynChildWidth3 aligncenter">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth3 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
						str1+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth4 aligncenter"> - </td>';
					if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth5 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellTotalPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth6 aligncenter" style="color:#a94442;"> - </td>';
					if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
						str1+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth7 aligncenter"> - </td>';
					if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
						str1+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
					else
						str1+='<td class="dynChildWidth8 aligncenter"> - </td>';
					if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
						str1+='<td class="dynChildWidth9 aligncenter">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
					else
						str1+='<td class="dynChildWidth9 aligncenter"> - </td>';
					if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
						str1+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
					else
						str1+='<td class="dynChildWidth10 aligncenter"> - </td>';
				
				str1+='</tr>';
			}
		}
		str1+='</tbody>';
	str1+='</table>';
	$('#districtWiseActivityDetailsExcelDivId').html(str1);
}
function dynamicwidth()
{
	$(".dynChildWidth10").css("width",$(".getChildWidth10").width());
	$(".dynChildWidth9").css("width",$(".getChildWidth9").width()+15);
	$(".dynChildWidth8").css("width",$(".getChildWidth8").width()+15);
	$(".dynChildWidth7").css("width",$(".getChildWidth7").width()+15);
	$(".dynChildWidth6").css("width",$(".getChildWidth6").width()+15);
	$(".dynChildWidth5").css("width",$(".getChildWidth5").width()+15);
	$(".dynChildWidth4").css("width",$(".getChildWidth4").width()+15);
	$(".dynChildWidth3").css("width",$(".getChildWidth3").width()+15);
	$(".dynChildWidth2").css("width",$(".getChildWidth2").width()+15);
	$(".dynChildWidth").css("width",$(".getChildWidth").width()+15);
}

function getDaywiseInfo(searchType,locationId,divId,locationName,callFrom)
{
	$(".daywiseSCls").html("");	
	var isOpened = $("#"+divId+"").hasClass("opened");	
	console.log(isOpened);
	if(!isOpened)
	{	
		$(".daywiseSCls").removeClass("opened");
		
			$('html,body').animate({
				scrollTop: $("#"+divId).offset().top},
				'slow');
			if(searchType == 'district'){
				$(".PlusnMinusSignd").addClass('collapsed');
				$(".constiListCls").html("");	
				$(".constiListCls").parent().find("div").removeClass('bod bod-b');
			}
			/*else if(searchType == 'constituency'){
				$(".PlusnMinusSignM").addClass('collapsed');
				$(".mandalsLsitCls").html("");		
				$(".constiNameAnchorCls").removeClass('bod bod-b');
			}*/
			/*else if(searchType == 'mandal'){
				$(".PlusnMinusSignV").addClass('collapsed');
				$(".villageListCls").html("");		
				$(".mandalNameAnchorCls").removeClass('bod bod-b');
			}*/
			else if(searchType == 'village'){
				$(".villageNameAnchorCls").addClass('collapsed');
			}
			
			if(showHideResults(divId)){
				$("#"+divId).html("");
				//return;
			}
				
			  var dates=$('.searchDateCls ').val();
			  var dateArray=dates.split("/");
			  var fromDateStr=dateArray[0];
			  var toDateStr=dateArray[1];
			
			 var activityScopeId = $("#ActivityList").val();
			 var activityLevelId = $("#activityLevelList").val();
			 
			if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
				var jObj = {
				stateId:1,
				searchType:searchType,
				conditionType:"daywiseResult",
				locationId:locationId,
				activityScopeId:activityScopeId,   //1
				activityLevelId:activityLevelId,   //1
				startDate:fromDateStr,   //30-11-2015
				endDate:toDateStr,     //08-12-2015
				locationName:locationName,
				teamSearchType:"locationWiseId",
				teamLeaderId:0,
				teamMemberId:0,
				radioSearch:'location',
				districtId:0,
				callFrom:callFrom,
				task:"getActivityDetailsBySearchCriteria"
				};
					$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
				
				$.ajax({
				  type:'GET',
				  url: 'getActivityDetailsBySearchCriteria.action',
				 data : {task:JSON.stringify(jObj)} ,
				}).done(function(result){
					//console.log(result);
					$("#"+divId+"").addClass("opened");
					if(result != null){
						if(jObj.callFrom == "BD")
						{
							buildDayWiseResultsForBD(result,divId,jObj);
						}
						else
						buildDayWiseResults(result,divId,jObj);
					}
				});
	}else
		$(".daywiseSCls").removeClass("opened");	
}
function buildDayWiseResultsForBD(result,divId,jObj)
{
	
		
	var str='';
	str+='<div>';
	str+='<ul class="villageDays getwidthForRes" style="padding-left:25px">';
	if(result.activityAttendanceInfoVOList != null && result.activityAttendanceInfoVOList.length>0)
	{
		for(var i in result.activityAttendanceInfoVOList)
		{
			str+='<li>';
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			str+='<tr>';
			str+='<td style="width:125px;"><span class="days" style="border-radius: 20px;  text-shadow: 1px 1px rgba(0, 0, 0, 0.5); font-size: 12px; padding: 6px; line-height: 14px;">'+result.activityAttendanceInfoVOList[i].name+'</span></td>';
			str+='<td class="dynChildWidthbd2 aligncenter">-</td>';
			if(result.activityAttendanceInfoVOList[i].totalMembers != null && result.activityAttendanceInfoVOList[i].totalMembers>0)
				str+='<td class="dynChildWidthbd3 aligncenter">'+result.activityAttendanceInfoVOList[i].totalMembers+'</td>';
			else
				str+='<td class="dynChildWidthbd3 aligncenter"> 0 </td>';
			if(result.activityAttendanceInfoVOList[i].totalWebCadreAttendance != null && result.activityAttendanceInfoVOList[i].totalWebCadreAttendance >0)
					str+='<td class="dynChildWidthbd4">'+result.activityAttendanceInfoVOList[i].totalWebCadreAttendance+'<span style="font-size: 11px;"></span></td>';
			else
				str+='<td class="dynChildWidthbd4 aligncenter"> 0 </td>';
			if(result.activityAttendanceInfoVOList[i].totalInfoCellCadreAttendance != null && result.activityAttendanceInfoVOList[i].totalInfoCellCadreAttendance >0)
					str+='<td class="dynChildWidthbd5 aligncenter">'+result.activityAttendanceInfoVOList[i].totalInfoCellCadreAttendance+'</td>';
			else
				str+='<td class="dynChildWidthbd5 aligncenter"> 0 </td>';
			
		
			if(result.activityAttendanceInfoVOList[i].totalWebPublicAttendance != null && result.activityAttendanceInfoVOList[i].totalWebPublicAttendance >0)
					str+='<td class="dynChildWidthbd6 aligncenter">'+result.activityAttendanceInfoVOList[i].totalWebPublicAttendance+'</td>';
			else
				str+='<td class="dynChildWidthbd6 aligncenter"> 0 </td>';
			if(result.activityAttendanceInfoVOList[i].totalInfoCellPublicAttendance != null && result.activityAttendanceInfoVOList[i].totalInfoCellPublicAttendance >0)
					str+='<td class="dynChildWidthbd7 aligncenter"  style="color:#a94442;">'+result.activityAttendanceInfoVOList[i].totalInfoCellPublicAttendance+'</td>';
			else
				str+='<td class="dynChildWidthbd7 aligncenter"> 0 </td>';
			
			
			if(result.activityAttendanceInfoVOList[i].totalWebPhotosAttendance != null && result.activityAttendanceInfoVOList[i].totalWebPhotosAttendance >0)
					str+='<td class="dynChildWidthbd8 aligncenter"  style="color:#a94442;">'+result.activityAttendanceInfoVOList[i].totalWebPhotosAttendance+'</td>';
			else
				str+='<td class="dynChildWidthbd8 aligncenter"> 0 </td>';
			
			if(result.activityAttendanceInfoVOList[i].totalInfoCellPhotosAttendance != null && result.activityAttendanceInfoVOList[i].totalInfoCellPhotosAttendance >0)
					str+='<td class="dynChildWidthbd9 aligncenter"  style="color:#a94442;">'+result.activityAttendanceInfoVOList[i].totalInfoCellPhotosAttendance+'</td>';
			else
				str+='<td class="dynChildWidthbd9 aligncenter"> 0 </td>';
			str+='</tr>';
			str+='</table>';
			var regularExp = /\((.*)\)/;
			var day = result.activityAttendanceInfoVOList[i].name.match(regularExp)[1];
			str+='<ul class="slick-training slick'+day.trim()+'" id="'+divId+'slick'+day.trim()+'" style="display:none;">';
		
			str+='</ul>';
			

			str+='</li>';
		}
	}
	str+='</ul>';
	str+='</div>';
	$('#'+divId+'').html(str);
	dynamicwidthforBlood123();
	
	getEventDocuments(divId,jObj);

}
function dynamicwidthforBlood123()
{
	$(".dynChildWidthbd9").css("width",$(".getChildWidthbd6").width()+15);
	$(".dynChildWidthbd8").css("width",$(".getChildWidthbd6").width()+15);
	$(".dynChildWidthbd7").css("width",$(".getChildWidthbd6").width()+15);
	$(".dynChildWidthbd6").css("width",$(".getChildWidthbd6").width()+15);
	$(".dynChildWidthbd5").css("width",$(".getChildWidthbd5").width()+15);
	$(".dynChildWidthbd4").css("width",$(".getChildWidthbd4").width()+15);
	$(".dynChildWidthbd3").css("width",$(".getChildWidthbd3").width()+15);
	$(".dynChildWidthbd2").css("width",$(".getChildWidthbd2").width()+15);
	$(".dynChildWidthbd").css("width",$(".getChildWidthbd").width()+15);
}

function buildDayWiseResults(result,divId,jObj)
{
	
		
	var str='';
	str+='<div>';
	str+='<ul class="villageDays getwidthForRes">';
	if(result.activityVoList != null && result.activityVoList.length>0)
	{
		for(var i in result.activityVoList)
		{
			//if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0){
				str+='<li>';
			str+='<table class="table table-col table-condensed" style="display:inline" >';
			str+='<tr>';
			str+='<td style="width:175px;"><span class="days" style="border-radius: 20px; width:90px; text-shadow: 1px 1px rgba(0, 0, 0, 0.5); font-size: 12px; padding: 6px 12px; line-height: 14px;">'+result.activityVoList[i].name+'</span></td>';
			if(result.activityVoList[i].totalCount != null && result.activityVoList[i].totalCount>0)
				str+='<td class="dynChildWidth aligncenter">'+result.activityVoList[i].totalCount+'</td>';
			else
				str+='<td class="dynChildWidth aligncenter"> - </td>';
			
			if(result.activityVoList[i].plannedCount != null && result.activityVoList[i].plannedCount >0)
					str+='<td class="dynChildWidth2 aligncenter">'+result.activityVoList[i].plannedCount+'</td>';
			else
				str+='<td class="dynChildWidth2 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrcovered != null && result.activityVoList[i].ivrcovered >0)
					str+='<td class="dynChildWidth3">'+result.activityVoList[i].ivrcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].ivrcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth3 aligncenter"> - </td>';
		/*	if(result.activityVoList[i].ivrcoveredPerc != null && result.activityVoList[i].ivrcoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].ivrcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].ivrNotPlanned != null && result.activityVoList[i].ivrNotPlanned >0)
					str+='<td class="dynChildWidth4 aligncenter">'+result.activityVoList[i].ivrNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth4 aligncenter"> - </td>';
			if(result.activityVoList[i].ivrTotal != null && result.activityVoList[i].ivrTotal >0)
					str+='<td class="dynChildWidth5 aligncenter"  style="color:#a94442;">'+result.activityVoList[i].ivrTotal+'</td>';
			else
				str+='<td class="dynChildWidth5 aligncenter"> - </td>';
			if(result.activityVoList[i].infoCellTotal != null && result.activityVoList[i].infoCellTotal >0)
			str+='<td class="dynChildWidth6 aligncenter"  style="color:#a94442;">'+result.activityVoList[i].infoCellTotal+'</td>';
		  else
			str+='<td class="dynChildWidth6 aligncenter"> - </td>';
			if(result.activityVoList[i].infoCellcovered != null && result.activityVoList[i].infoCellcovered >0)
					str+='<td class="dynChildWidth7 aligncenter">'+result.activityVoList[i].infoCellcovered+'<span style="font-size: 11px;">('+result.activityVoList[i].infoCellcoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth7 aligncenter"> - </td>';
			/*if(result.activityVoList[i].infoCellcoveredPerc != null && result.activityVoList[i].infoCellcoveredPerc >0)
					str+='<td class="dynChildWidth7">'+result.activityVoList[i].infoCellcoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth7"> - </td>';*/
			if(result.activityVoList[i].infoCellNotPlanned != null && result.activityVoList[i].infoCellNotPlanned >0)
					str+='<td class="dynChildWidth8 aligncenter">'+result.activityVoList[i].infoCellNotPlanned+'</td>';
			else
				str+='<td class="dynChildWidth8 aligncenter"> - </td>';
			if(result.activityVoList[i].whatsAppCovered != null && result.activityVoList[i].whatsAppCovered >0)
					str+='<td class="dynChildWidth9">'+result.activityVoList[i].whatsAppCovered+'<span style="font-size: 11px;">('+result.activityVoList[i].whatsAppCoveredPerc+'%)</span></td>';
			else
				str+='<td class="dynChildWidth9 aligncenter"> - </td>';
			/*if(result.activityVoList[i].whatsAppCoveredPerc != null && result.activityVoList[i].whatsAppCoveredPerc >0)
					str+='<td class="dynChildWidth2">'+result.activityVoList[i].whatsAppCoveredPerc+'</td>';
			else
				str+='<td class="dynChildWidth2"> - </td>';*/
			if(result.activityVoList[i].imagesCount != null && result.activityVoList[i].imagesCount >0)
					str+='<td class="dynChildWidth10 aligncenter">'+result.activityVoList[i].imagesCount+'</td>';
			else
				str+='<td class="dynChildWidth10 aligncenter"> - </td>';
			str+='</tr>';
			str+='</table>';
			var regularExp = /\((.*)\)/;
			var day = result.activityVoList[i].name.match(regularExp)[1];
			str+='<ul class="slick-training slick'+day.trim()+'" id="'+divId+'slick'+day.trim()+'" style="display:none;">';
		
			str+='</ul>';
			

			str+='</li>';
			//}
		}
	}
	
	str+='</ul>';
	str+='</div>';
	$('#'+divId+'').html(str);
	dynamicwidth();
	
	getEventDocuments(divId,jObj);

}

function getDetails(){
	getOptionDetailsForQuestion();
	getQuestions();
	if($("#activityTypeList").val()==4){
		$("#locationWiseActivityDetailsDivId").show();
		$("#excelBtn").show();
		getLocationWiseActivityDetails("district",0,"");
		$("#nonBloodDonationDivId").hide();
		//$("#stateWiseViewDid").show();
		var name = $('#ActivityList :selected').text();
		var actiivityLevelStr = $('#activityLevelList :selected').text();
		$('#headingDiv').html(''+name.toUpperCase()+' - '+actiivityLevelStr.toUpperCase()+' LEVEL');
		getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
	}else{
		$("#locationWiseActivityDetailsDivId").hide();
		$("#excelBtn").hide();
		$("#lcnExcelBtn").show();
		$("#nonBloodDonationDivId").show();
		//$("#stateWiseViewDid").show();
		isAlreadyBuild = false;
		getActivityDetailsBySearchCriteria(1,'state','stateWiseViewDid','locationWiseId','location','0');
		if($("#activityLevelList").val() == 4)
			getActivityDetailsBySearchCriteria(1,'state','alignmentWidth','locationWiseId','location','0');
		else
			getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
	}
	
}

var globallocationScope;
var globallocationValue;
var globallocationName;
var globalActivityScope;
var globalImages;
function getEventDocuments(divId,Obj)
{
	//$('#'+divId+'').html('<div style="text-align: center" ><img src="./images/Loading-data.gif" /></div>');
	 //var dates=$('.searchDateCls ').val();
	 // var dateArray=dates.split("/");
	 // var fromDateStr=dateArray[0];
	 // var toDateStr=dateArray[1];
	 var activityId = $("#ActivityList").val();
	  /* if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} */
		var jObj = {
		activityId:12,
		locationScope:"district",
		locationValue:23,		
		day:1,
		fromDateStr:"01-01-2016",
		toDateStr:"01-01-2017",
		locationName:Obj.locationName,
		startIndex:0,
		maxIndex:0,
		callFrom:"BD",
		task:"daywise"
		};
		/* var jObj = {
		activityId:Obj.activityScopeId,
		locationScope:Obj.searchType,
		locationValue:Obj.locationId,		
		day:0,
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		locationName:Obj.locationName,
		startIndex:0,
		maxIndex:0,
		callFrom:Obj.callFrom,
		task:"daywise"
		}; */
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			//$('#'+divId+'').html('');
			globallocationScope = '';
			globallocationValue = '';
			globallocationName = '';
			globalActivityScope ='';
			globallocationScope = jObj.locationScope;
			globallocationValue = jObj.locationValue;
			globallocationName = jObj.locationName;
			globalActivityScope = jObj.activityId;
			buildDayWiseImages(result,divId);
			
			});
}
var globalTotalImages;
function buildDayWiseImages(result,divId)
{
	
	globalPopupresult = "";
	globalPopupresult = result;
	if(result != null)
	{
		for(var i in result)
	{
	
		$("#"+divId+"slickDay-"+result[i].day).css("display","block");	
		 $("#"+divId+"slickDay-"+result[i].day).html('<img src="./images/Loading-data.gif" />');
		
			var str ='';
			for(var j in result[i].subList)
			{
				if(j < 20)
				{
					str+='<li>';
					str+='<img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'" alt="" style="height:25px;cursor:pointer;" data-toggle="modal" class="Imagepopup"  dayattr="'+result[i].day+'" imgpath="'+result[i].subList[j].path+'"/>';
					str+='</li>';
				}
			
			}
		//console.log("111 : "+"#"+divId+"slickDay-"+result[i].day+" ---- "+str);
		$("#"+divId+"slickDay-"+result[i].day).html(str);
		
	}
	globalTotalImages =0;
	globalTotalImages = result[0].totalResult;
	}
 var getWidth=$(".getwidthForRes").width()-80;
		var responsive=$("#alignmentWidth").width();
		$(".slick-training").css("width",getWidth);
		
		$('.slick-training').slick({
		  dots: false,
		  slide: 'li',
		  infinite: false,
		  speed: 300,
		  variableWidth: true,
		  });
		
}

$(".tbtn").click(function(e){
    $(".themeControll").toggleClass("active");
	e.stopPropagation();
});
$(".linkinner").click(function(e){
	e.stopPropagation();
});

$(document).on("click", "body", function(){
	 $(".themeControll").removeClass("active");
});


$(document).on('click', '.Imagepopup', function(){	
   $("#myModal").modal("show");
   
   var str='';
   
    str+='<div class="row">';
			 str+='<div class="col-md-9">';
				 str+='<nav class="navbar navbar-default navbarCollapseCustom">';
					<!-- Brand and toggle get grouped for better mobile display -->
					 str+='<div class="navbar-header">';
					   str+='<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">';
						 str+='<span class="sr-only">Toggle navigation</span>';
						 str+='<span class="icon-bar"></span>';
						 str+='<span class="icon-bar"></span>';
						 str+='<span class="icon-bar"></span>';
					   str+='</button>';
					 str+='</div>';
					<!-- Collect the nav links, forms, and other content for toggling -->
					 str+='<div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">';
					  str+='<ul class="nav navbar-nav" id="popupDaysDiv">';
						/* str+='<li class="active"><a href="#">Day 1 <span class="sr-only">(current)</span></a></li>';
						 str+='<li><a href="#">Day 2</a></li>';
						 str+='<li><a href="#">Day 3</a></li>';
						 str+='</li>';*/
					   str+='</ul>';
					 str+='</div>';<!-- /.navbar-collapse -->
				 str+='</nav>';
				 str+='<div class="bg_cc pad_10" id="popupImages">';
					 /*str+='<ul class="slider-for">';
						 str+='<li><img src="https://mytdp.com/activity_documents  /Chrysanthemum.jpg"></li>';
						 str+='<li><img src="https://mytdp.com/activity_documents  /Desert.jpg"></li>';
						
					 str+='</ul>';
					 str+='<ul class="slider-nav">';
						 str+='<li><img src="https://mytdp.com/activity_documents  /Chrysanthemum.jpg"></li>';
						 str+='<li><img src="https://mytdp.com/activity_documents  /Desert.jpg"></li>';
					 str+='</ul>';*/
				 str+='</div>';
				str+=' <div id="paginationDivId"></div>';
			 str+='</div>';
			str+='<div class="col-md-3" style="background:#fff;box-shadow:0 2px 10px 0 rgba(0, 0, 0, 0.35);position:absolute;bottom:0px;right:0px;top:0px;padding:0px;overflow:scroll" id="locationsPopup">';
				/* str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
				   str+='<div class="panel panel-default panel-custommodal">';
					 str+='<div class="panel-heading panel-headingModal" role="tab" id="headingOneModal">';
					   str+='<a role="button" class="accordionmodal-toggle" data-toggle="collapse" data-parent="#accordionModal" href="#collapseOneModal" aria-expanded="true" aria-controls="collapseOneModal">';
						 str+='<h4 class="panel-title">Vijayanagaram';
						 str+='</h4>';
					   str+='</a>';
					 str+='</div>';
					 str+='<div id="collapseOneModal" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOneModal">';
					   str+='<div class="panel-body pad_0">';
						  str+='<ul class="villageDaysModal">';
							 str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 2</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 3</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 4</a></li>';
						  str+='</ul>';
					   str+='</div>';
					 str+='</div>';
				   str+='</div>';
				   str+='<div class="panel panel-default panel-custommodal">';
					 str+='<div class="panel-heading panel-headingModal" role="tab" id="headingTwo">';
					 str+='<a class="collapsed accordionmodal-toggle" role="button" data-toggle="collapse" data-parent="#accordionModal" href="#collapseTwoModal" aria-expanded="false" aria-controls="collapseTwoModal">';
						 str+='<h4 class="panel-title">Srikakulam';
						 str+='</h4>';
					 str+='</a>';
					 str+='</div>';
					 str+='<div id="collapseTwoModal" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">';
					   str+='<div class="panel-body pad_0">';
						  str+='<ul class="villageDaysModal">';
							 str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 2</a> </li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 3</a> </li>';
							 str+='<li><span class="line"/></span><a href="#">Constituency 4</a> </li>';
						  str+='</ul>';
					   str+='</div>';
					 str+='</div>';
				   str+='</div>';
				   str+='<div class="panel panel-default panel-custommodal">';
					 str+='<div class="panel-heading panel-headingModal" role="tab" id="headingThreeModal">';
					 str+='<a class="collapsed accordionmodal-toggle" role="button" data-toggle="collapse" data-parent="#accordionModal" href="#collapseThreeModal" aria-expanded="false" aria-controls="collapseThreeModal">';
						 str+='<h4 class="panel-title">Khammam';	
						 str+='</h4>';
					str+='</a>';
					str+='</div>';
					str+='<div id="collapseThreeModal" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThreeModal">';
					 str+='<div class="panel-body pad_0">';
						 str+='<ul class="villageDaysModal">';
							str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							str+='<li><span class="line"/></span><a href="#">Constituency 2</a></li>';
							str+='<li><span class="line"/></span><a href="#">Constituency 3</a></li>';
							str+='<li><span class="line"/></span><a href="#">Constituency 4</a></li>';
						 str+='</ul>';
					  str+='</div>';
					str+='</div>';
				  str+='</div>';
				str+='</div>';*/
			str+='</div>';
		str+='</div>';
		
		$("#buildPoupupImage").html(str);
		$(".panel-headingModal").click(function(){
			if($(this).find(".accordionmodal-toggle").hasClass("collapsed")){
				$(this).parent().parent().find(".bodM").removeClass("bodM");
				$(this).addClass("bodM")
			}else{
				$(this).removeClass("bodM");
			}
		});
	
buildDayWiseImagesForPopup(globalPopupresult,$(this).attr("imgpath"),$(this).attr("dayattr"));
buildLocationsForPopup(globallocationScope,globallocationValue,globalActivityScope);


});
	function getAvailableDatesForPopup(locationScope,locationValue)
	{
	  var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	  var activityScopeId = $("#ActivityList").val();
	 if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
	 var locationScope ="state";
	 var locationValue =0;
		var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildAvailableDatesPopup1(result);
		});
}



function getLocationsForPopupcli(locationScope,locationValue,divId,subLevel)
{
	
	  //var dates=$('.searchDateCls ').val();
	  //var dateArray=dates.split("/");
	 // var fromDateStr=dateArray[0];
	 // var toDateStr=dateArray[1];
	 var activityScopeId = $("#ActivityList").val();
	/*  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} */
	  var locationScope =locationScope;
	 var locationValue =locationValue;
		var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:"01-01-2016",
		toDateStr:"01-01-2017",
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getLocationsHierarchyForEventAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildSublevelLocationsForPopup1(result,divId,subLevel)
		});
}

function getAvailableDatesForPopupcli(locationScope,locationValue)
{
	 $("#popupDaysDiv").html('<img src="./images/Loading-data.gif" />');
	  var dates=$('.searchDateCls ').val();
	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	 var activityScopeId = $("#ActivityList").val();
	 if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			}
	  var locationScope =locationScope;
	 var locationValue =locationValue;
		var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildAvailableDatesPopup1(result,jObj);
		});
}

function buildAvailableDatesPopup1(result,jObj)
{
	var str ='';
	for(var i in result)
	{
		
		
		str+='<li class="daysCls" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
	}
	$("#popupDaysDiv").html(str);
	
			GlobalPopupScope = jObj.locationScope;
				GlobalPopuplocation =jObj.locationValue;
				
	
}


function buildLocationsForPopup(locationScope,locationValue,ActivityScope)
{
	$("#myModalLabel").html(''+globallocationName+'');
	var subLeveldivId = '';
	if(locationScope == "state")
	subLeveldivId = "popupstateConstituencies"+locationValue;
		if(locationScope == "district")
		subLeveldivId = "popupdistConstituencies"+locationValue;
		if(locationScope == "constituency")
		subLeveldivId = "constiSubLevel"+locationValue;
		if(locationScope == "mandal")
		subLeveldivId= "mandalSubLevel"+locationValue;
		var str = '';
		str+='<div class="panel-group" id="accordionModal" role="tablist" aria-multiselectable="true">';
			
					   str+='<div class="panel panel-default panel-custommodal m_0">';
						  str+='<div class="panel-heading panel-headingModal popupLevel" role="tab" id="headinglevel'+locationValue+'Modal" attr='+locationValue+'>';
						  str+='<a role="button" class="accordionmodal-toggle collapsed" data-toggle="collapse" data-parent="#accordionModal" href="#collapselevel'+locationValue+'Modal" aria-expanded="true" aria-controls="collapselevel'+globallocationValue+'Modal">';
							 str+='<h4 class="panel-title popupTitle" attr="'+globallocationName+'">Anantapur District(157)';
							// str+='<h4 class="panel-title popupTitle" attr="'+globallocationName+'">Anantapur District('+globalTotalImages+')';
							 str+='</h4>';
						   str+='</a>';
						 str+='</div>';
						 str+='<div id="collapselevel'+locationValue+'Modal" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headinglevel'+locationValue+'Modal">';
						   str+='<div class="panel-body pad_0">';
							  str+='<div class="" id="'+subLeveldivId+'" style="margin-left:10px;">';
								// str+='<li><span class="line"/></span><a href="#">Constituency 1</a></li>';
							str+='</div>';
						   str+='</div>';
						 str+='</div>';
						 str+='</div>';
					
			  str+='</div>';
				$("#locationsPopup").html(str);
				
			
}
var index=0;
var index1=0;
function buildSublevelLocationsForPopup1(resultList,divId,subLevel)
{

	var str = '';
	var result = resultList.locationsList;
	index1++;
	str+='<div class="panel-group" id="accordionsub'+index1+'" role="tablist" aria-multiselectable="true" style="margin:0px">';
	for(var i in result)
	{
		index++;
		//index1++;
	  str+='<div class="panel panel-default">';
		str+='<div class="panel-heading" role="tab" id="headingOne'+index+'">';
		str+='<a role="button" data-toggle="collapse" attr="'+result[i].id+'" class="'+subLevel+'" data-parent="#accordionsub'+index1+'" href="#collapseOne'+index+'" aria-expanded="true" aria-controls="collapseOne'+index+'">';
			str+='<h4 class="panel-title popupTitle" attr="'+result[i].name+'"> '+result[i].name+'('+result[i].count+')</h4>';
		str+='</a>';
		str+='</div>';
		str+='<div id="collapseOne'+index+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne'+index+'">';
		  str+='<div class="panel-body">';
			str+='<div class="subLevelCls" id="'+subLevel+''+result[i].id+'"></div>';
		  str+='</div>';
		str+='</div>';
	  str+='</div>';
	}
	str+='</div>';
	$("#"+divId).html(str);
}
$(document).on('click','.popupLevel',function(){
	
		callPopupLoad(globallocationScope,globallocationValue,globalActivityScope);
	});

  $(document).on('click','.distSubLevel',function(){
	
		var id = $(this).attr("attr");
		var divId = "popupdistConstituencies"+id;
		getLocationsForPopupcli("district",id,divId,"constiSubLevel");
		getAvailableDatesForPopupcli("district",id);
		GlobalPopupScope = "district";
		GlobalPopuplocation =id;
		 getEventDocumentsForPopup("district",id,0,0);
  });
  
    $(document).on('click','.constiSubLevel',function(){
		GlobalPopupScope = "";
		GlobalPopuplocation = "";
		var id = $(this).attr("attr");
		var divId = "constiSubLevel"+id;
		getLocationsForPopupcli("constituency",id,divId,"mandalSubLevel");
		getAvailableDatesForPopupcli("constituency",id);
		GlobalPopupScope = "constituency";
		GlobalPopuplocation =id;
		 getEventDocumentsForPopup("constituency",id,0,0);
		
  });
  
     $(document).on('click','.mandalSubLevel',function(){
		var id = $(this).attr("attr");
		var divId = "mandalSubLevel"+id;
		getLocationsForPopupcli("mandal",id,divId,"villageSubLevel");
		getAvailableDatesForPopupcli("mandal",id);
		GlobalPopupScope = "mandal";
		GlobalPopuplocation =id;
		 getEventDocumentsForPopup("mandal",id,0,0);
  });
  
  $(document).on('click','.villageSubLevel',function(){
		var id = $(this).attr("attr");
		GlobalPopupScope = "village";
		GlobalPopuplocation =id;
		getAvailableDatesForPopupcli("village",id);
		 getEventDocumentsForPopup("village",id,0,0);
  });
   
  function callPopupLoad(locationScope,id,activityId)
  {
		if(locationScope == "state")
		{
			var divId = "popupstateConstituencies"+id;
			getLocationsForPopupcli("state",id,divId,"distSubLevel");
			getAvailableDatesForPopupcli("state",id);
		}
		
		if(locationScope == "district")
		{
			var divId = "popupdistConstituencies"+id;
			getLocationsForPopupcli("district",id,divId,"constiSubLevel");
			getAvailableDatesForPopupcli("district",id);
			getEventDocumentsForPopup("district",id,0,0);
		}
		if(locationScope == "constituency")
		{
			var divId = "constiSubLevel"+id;
			getLocationsForPopupcli("constituency",id,divId,"mandalSubLevel");
			getAvailableDatesForPopupcli("constituency",id);
			getEventDocumentsForPopup("constituency",id,0,0);
		}
		
		if(locationScope == "mandal")
		{
			var divId = "mandalSubLevel"+id;
			getLocationsForPopupcli("mandal",id,divId,"villageSubLevel");
			getAvailableDatesForPopupcli("mandal",id);
			getEventDocumentsForPopup("mandal",id,0,0);
		}
		
		if(locationScope == "village")
		{
			getEventDocumentsForPopup("village",id,0,0);
		}
		
		
  }
 $(document).on('click','.popupTitle',function(){
	  $("#myModalLabel").html(''+$(this).attr("attr")+'');
  })
  
function getEventDocumentsForPopup(searchType,locationId,day,num,path)
{

	
	 $("#popupImages").html('<img src="./images/Loading-data.gif" />');
	 var dates=$('.searchDateCls ').val();
/* 	  var dateArray=dates.split("/");
	  var fromDateStr=dateArray[0];
	  var toDateStr=dateArray[1];
	  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} */
		var jObj = {
		activityId:12,
		locationScope:"district",
		locationValue:23,		
		day:day,
		fromDateStr:"01-01-2016",
		toDateStr:"01-01-2017",
		type:"popup",
		startIndex:num,
		maxIndex:10,
		callFrom:"BD",
		//locationName:obj.locationName,
		 task:"popupdaywise"
		};
		/* var jObj = {
		activityId:globalActivityScope,
		locationScope:searchType,
		locationValue:locationId,		
		day:day,
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		type:"popup",
		startIndex:num,
		maxIndex:10,
		callFrom:"BD",
		//locationName:obj.locationName,
		 task:"popupdaywise"
		}; */
		$.ajax({
          type:'GET',
          url: 'getEventDocumentsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			buildDayWiseImagesForPopup1(result,jObj,path);
			});
}


function buildDayWiseImagesForPopup1(result,jObj,path)
{
	$("#popupImages").html('');
	var str ='';

	if(result != null)
	{
	
		str+='<ul class="slider-for">';
		if(path != null && path.length>0)
			str+='<li><img src="https://mytdp.com/activity_documents/' +path+'"></li>';
			for(var i in result)
			{
			for(var j in result[i].subList)
			{
				
				str+='<li><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'"></li>';
			}
			}
			  str+='</ul>';
		str+='<ul class="slider-nav">';	
		if(path != null && path.length>0)
			str+='<li><img src="https://mytdp.com/activity_documents/' +path+'" style="cursor:pointer;"></li>';
		for(var i in result)
		{	 
			for(var j in result[i].subList)
			{
				str+='<li><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"/></li>';	
			}
		}
				str+='</ul>';
			$("#popupImages").html(str);
			
			setTimeout(function(){		
			$('.slider-for').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  slide: 'li',
			  arrows: false,
			  fade: true,
			  asNavFor: '.slider-nav'
			});
			$('.slider-nav').slick({
			  slidesToShow: 11,
			  slidesToScroll: 0,
			  slide: 'li',
			  asNavFor: '.slider-for',
			  dots: false,
			 // centerMode: true,
			focusOnSelect: true,
			  variableWidth: true

				})
			$(".slick-list").css("margin-left","17px;");	
			$(".slick-list").css("margin-right","17px;");	
			//$('.slider-nav li:first-child').trigger('click');
			//$('.slider-nav li:first-child').trigger('click');
		},300);
		
			var itemsCount=result[0].totalResult;
			
			
	    var maxResults=jObj.maxIndex;
	   if(jObj.startIndex==0){
		   $("#paginationDivId").html('');
		   $("#paginationDivId").pagination({
			items: itemsCount,
			itemsOnPage: maxResults,
			cssStyle: 'light-theme',
			
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*10;
				getEventDocumentsForPopup(jObj.locationScope,jObj.locationValue,jObj.day,num,"");
				
			}
		});

		}
	GlobalPopupScope = globallocationScope;
	GlobalPopuplocation =globallocationValue;
	
	}
	
	
	
}
	
	

function buildDayWiseImagesForPopup(result,path,day)
{
/*	var str ='';
	if(result != null)
	{
	for(var i in result)
	{
		 if(result[i].day == day)
			{
		
			str+='<ul class="slider-for">';
			for(var j in result[i].subList)
			{
				if(j < 20)
				{
						str+='<li><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'"></li>';
				}
			}
			  str+='</ul>';
			  str+='<ul class="slider-nav">';
			 
			for(var j in result[i].subList)
			{
				if(j < 20)
				{
				if(result[i].subList[j].path==path)
					str+='<li class="imgTrig1"><a class="imgTrig1"><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"></a></li>';
				else
				 str+='<li><img src="https://mytdp.com/activity_documents/' +result[i].subList[j].path+'" style="cursor:pointer;"></li>';		
				}
		  }
				str+='</ul>';
			$("#popupImages").html(str);
			
			
setTimeout(function(){		
			$('.slider-for').slick({
			  slidesToShow: 1,
			  slidesToScroll: 1,
			  slide: 'li',
			  arrows: false,
			  fade: true,
			  asNavFor: '.slider-nav'
			});
			$('.slider-nav').slick({
			  slidesToShow: 3,
			  slidesToScroll: 1,
			  slide: 'li',
			  asNavFor: '.slider-for',
			  dots: false,
			  centerMode: true,
			  focusOnSelect: true,
			  variableWidth: true

				})
			$(".slick-list").css("margin-left","17px;");	
			$(".slick-list").css("margin-right","17px;");	
			//$('.slider-nav li:first-child').trigger('click');
			//$('.slider-nav li:first-child').trigger('click');
		},300);
		
			}
		}
	}*/
	
	getAvailableDates(globallocationScope,globallocationValue,day,path);

	//setTimeout(function(){imgTrigger()},800);
	
}
function imgTrigger()
{

	$('.imgTrig1').trigger('click');
}
function getAvailableDates(locationScope,locationValue,day,path)
	{
		
	  $("#popupDaysDiv").html('<img src="./images/Loading-data.gif" />');
	  var dates = $('.searchDateCls').val();
	 // alert(dates);
	  //var dateArray = dates.split("/");
	  var fromDateStr='01-01-2016';
	  var toDateStr='01-01-2017';
	  var activityScopeId = $("#ActivityList").val();
	/*  if(dateArray.length == 1)
			{
				fromDateStr=" ";
				toDateStr=" ";
			} */
	
		/* var jObj = {
		activityId:activityScopeId,
		locationScope:locationScope,
		locationValue:locationValue,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		}; */
		var jObj = {
		activityId:12,
		locationScope:"district",
		locationValue:22,	
		fromDateStr:fromDateStr,
		toDateStr:toDateStr,
		task:""
		};
		$.ajax({
          type:'GET',
          url: 'getAvailableDatesForActivitiesAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
				var str ='';
				for(var i in result)
				{
					if(result[i].id==day)
					{
						str+='<li class="active daysCls" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
						getEventDocumentsForPopup(jObj.locationScope,jObj.locationValue,day,0,path);
					}
					
					  else
					str+='<li class="daysCls" attr="'+result[i].id+'"><a href="#">Day '+result[i].id+' <span class="sr-only">(current)</span></a></li>';
				}
				$("#popupDaysDiv").html(str);
				GlobalPopupScope = jObj.locationScope;
				GlobalPopuplocation =jObj.locationValue;
				
		});
}
 $(document).on('click','.daysCls',function(){
	
	 $(".daysCls").removeClass( "active" )
	 $(this).addClass("active");
	 var day = $(this).attr("attr");
		getEventDocumentsForPopup(GlobalPopupScope,GlobalPopuplocation,day,0,"");
  });

$(document).on('click', '.searchTypeCls', function(){
	
	var id = $(this).attr('id');
	
	if(id.trim() == 'locationWiseId')
	{
		$('#searchTypeId').val('locationWiseId');
	}
	if(id.trim() == 'organizersWiseId')
	{	
		$('#searchTypeId').val('organizersWiseId');
	}
	isAlreadyBuild = false;
	var searchRadioType = $('#searchTypeId').val();
	
	if(searchRadioType == 'locationWiseId'){
		getActivityDetailsBySearchCriteria(1,'district','alignmentWidth','locationWiseId','location','0');
	}
	else if(searchRadioType == 'organizersWiseId'){
		getLeadersWiseActivityDetailsBySearchCriteria(1,'district','alignmentWidth','organizersWiseId','organizers',0,0,0,'');
	}
	
});
	
	function getLocationWiseActivityDetails(type,id,resultDivNum){
		
		if(type == 'constituency'){
	        $(".PlusnMinusSignd").addClass('collapsed');
	        //$(".PlusnMinusSignd").attr('aria-expanded','true');
	       
	        $(".constiListCls").html("");       
	        $(".constiListCls").parent().find("div").removeClass('bod bod-b');
	        $(".constiListCls").siblings().removeClass('in');
	        $(".constiListCls").removeClass('opened');
	        $(".constiListCls").siblings().attr('aria-expanded','false');
	       
	    }
		
		var acScpeId = $("#ActivityList").val();
		
		var jObj = {
        searchType:type,
        activityScopeId:parseInt(acScpeId),
        locationValue:id,
        task:""
        };
        $.ajax({
          type:'GET',
          url: 'getLocationWiseActivityDetailsAction.action',
         data : {task:JSON.stringify(jObj)} ,
        }).done(function(result){
			if(type=="district"){
				buildDistrictWiseActivityResult(result);
			}else if(type=="constituency"){
				buildConstituencyWiseActivityResult(result,resultDivNum);
			}else if(type=="mandal"){
				buildMandalWiseActivityResult(result,resultDivNum);
			}else if(type=="village"){
				buildPanchayatWiseActivityResult(result,resultDivNum);
			}else if(type=="ward"){
				buildWardWiseActivityResult(result,resultDivNum);
			}
			
			
        });
	}
	
	function buildDistrictWiseActivityResult(result){
		var str='';
		
		str+='<table class="table table-col" style="border:1px solid #ccc">';
		str+='<caption class="cap-custom"><b>	</b></caption>';
		str+='<thead style="background-color:#EFF3F4;">';
			str+='<tr>';
			str+='<th rowspan="2" class="getChildWidthbd aligncenter">Location Name</th>';
			str+='<th rowspan="2" class="getChildWidthbd2 aligncenter">Total Locations</th>';
			str+='<th rowspan="2" class="getChildWidthbd3 aligncenter">Total Members Attended</th>';
			str+='<th colspan="2" class="aligncenter">Total Cadre Attended</th>';
			str+='<th colspan="2" class="aligncenter">Total Public Attended</th>';
			//str+='<th colspan="2" class="aligncenter">Questionnaire Submitted</th>';
			//str+='<th colspan="2" class="aligncenter">Organiser Questionnaire Submitted</th>';
			str+='<th colspan="2" class="aligncenter">Photos Attached</th>';
			str+='</tr>';
			str+='<tr>';
			str+='<th class="getChildWidthbd4 aligncenter">WEB</th>';
			str+='<th class="getChildWidthbd5 aligncenter">TAB</th>';
			str+='<th class="getChildWidthbd6 aligncenter">WEB</th>';
			str+='<th class="getChildWidthbd7 aligncenter">TAB</th>';
			/* str+='<th class="getChildWidth8 aligncenter">WEB</th>';
			str+='<th class="getChildWidth9 aligncenter">Info-cell</th>';
			str+='<th class="getChildWidth10 aligncenter">WEB</th>';
			str+='<th class="getChildWidth11 aligncenter">Info-cell</th>'; */
			str+='<th class="getChildWidthbd8 aligncenter">WEB</th>';
			str+='<th class="getChildWidthbd9 aligncenter">TAB</th>';
			str+='</tr>';
		str+='</thead>';
		if(result != null && result.subList != null && result.subList.length > 0){
			for(var i in result.subList){
				str+='<tr  id="'+result.subList[i].id+'">';
				str+='<td colspan="13" class="pad_0">';
				str+='<div class="panel-group m_0" id="accordionforBlood'+i+'" role="tablist" aria-multiselectable="true">';
				str+='<div class="panel panel-default panel-customtd">';
				str+='<div class="panel-heading" role="tab" id="distHeading'+i+'">';
				//str+='<h4 class="panel-title">';
				if($("#activityLevelList").val()==1 || $("#activityLevelList").val()==2 || $("#activityLevelList").val()==5){
					str+='<a role="button" data-toggle="collapse" class="accordionforBlood'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-parent="#accordionforBlood'+i+'" href="#constituencyResultDivd'+i+'" aria-expanded="true" aria-controls="constituencyResultDivd'+i+'"  onclick=getLocationWiseActivityDetails("constituency","'+result.subList[i].id+'","'+i+'") >';
				}else{
					str+='<a role="button" data-toggle="collapse" class="accordionforBlood'+i+'-toggle accordion-toggle PlusnMinusSignd collapsed" data-parent="#accordionforBlood'+i+'" href="#constituencyResultDivd'+i+'" aria-expanded="true" aria-controls="constituencyResultDivd'+i+'"  style="color: #666 !important;cursor: auto;">';
				}
					
						str+='<table class="table table-col table-condensed" style="display:inline">';
						str+='<tr>';							
							str+='<td class="dynChildWidthbd aligncenter">'+result.subList[i].name+'</td>';
							str+='<td class="dynChildWidthbd2 aligncenter">'+result.subList[i].totalLocations+'</td>';
							str+='<td class="dynChildWidthbd3 aligncenter">'+result.subList[i].totalMembers+'</td>';
							str+='<td class="dynChildWidthbd4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
							str+='<td class="dynChildWidthbd5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
							str+='<td class="dynChildWidthbd6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
							str+='<td class="dynChildWidthbd7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
							/* str+='<td class="dynChildWidth8 aligncenter">NA</td>';
							str+='<td class="dynChildWidth9 aligncenter">NA</td>';
							str+='<td class="dynChildWidth10 aligncenter">NA</td>';
							str+='<td class="dynChildWidth11 aligncenter">NA</td>'; */
							str+='<td class="dynChildWidthbd8 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
							str+='<td class="dynChildWidthbd9 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
						str+='</tr>';
						str+='</table>';
				
					str+='</a>';
					str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'district\','+result.subList[i].id+',\'dayWiseBDDistrictInfo'+result.subList[i].id+'\',\''+result.subList[i].name+'\',\'BD\')">Day Wise</button>';
				//str+='</h4>';
				str+='</div>';
				str+='<div id="dayWiseBDDistrictInfo'+result.subList[i].id+'" class="daywiseSCls"></div>';
				str+='<div id="constituencyResultDiv'+i+'" class="constiListCls"></div>';
				str+='<div id="constituencyResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="distHeading'+i+'"></div>';
				str+='</div>';
				str+='</div>';
				str+='</td>';
				str+='</tr>';
			}
		}else{
			str+='<tr style="background-color:#663300;">';
			str+='<td colspan="13">';
			str+='<h4>No Data Available1.</h4>';
			str+='</td>';
			str+='</tr>';
		}
		str+='</table>';
		str+='</div>';
		
		$("#locationWiseActivityDetailsDivId").html(str);
		
		var str1='';
		
		str1+='<table class="table table-bordered" style="border:1px">';
			str+='<thead style="background-color:#EFF3F4;">';
				str1+='<tr>';
					str1+='<th rowspan="2" class="getChildWidthbd aligncenter">Location Name</th>';
					str1+='<th rowspan="2" class="getChildWidthbd2 aligncenter">Total Locations</th>';
					str1+='<th rowspan="2" class="getChildWidthbd3 aligncenter">Total Members Attended</th>';
					str1+='<th colspan="2" class="aligncenter">Total Cadre Attended</th>';
					str1+='<th colspan="2" class="aligncenter">Total Public Attended</th>';
					str1+='<th colspan="2" class="aligncenter">Photos Attached</th>';
				str1+='</tr>';
				str1+='<tr>';
					str1+='<th class="getChildWidthbd4 aligncenter">WEB</th>';
					str1+='<th class="getChildWidthbd5 aligncenter">TAB</th>';
					str1+='<th class="getChildWidthbd6 aligncenter">WEB</th>';
					str1+='<th class="getChildWidthbd7 aligncenter">TAB</th>';
					str1+='<th class="getChildWidthbd8 aligncenter">WEB</th>';
					str1+='<th class="getChildWidthbd9 aligncenter">TAB</th>';
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str1+='<tr>';							
						str1+='<td class="dynChildWidthbd aligncenter">'+result.subList[i].name+'</td>';
						str1+='<td class="dynChildWidthbd2 aligncenter">'+result.subList[i].totalLocations+'</td>';
						str1+='<td class="dynChildWidthbd3 aligncenter">'+result.subList[i].totalMembers+'</td>';
						str1+='<td class="dynChildWidthbd4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
						str1+='<td class="dynChildWidthbd5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
						str1+='<td class="dynChildWidthbd6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
						str1+='<td class="dynChildWidthbd7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
						str1+='<td class="dynChildWidthbd8 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
						str1+='<td class="dynChildWidthbd9 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
					str1+='</tr>';
					}
				}
			str1+='</tbody>';
		str1+='</table>';
		
		$("#districtWiseActivityDetailsExcelDivId").html(str1);
		
		dynamicwidthforBlood();
	}
	
	function buildConstituencyWiseActivityResult(result,resultDivNum){
		var isOpened = $("#constituencyResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			str+='<button class="btn btn-success" id="constExcelBtn" onclick="generateExcel(\'constWiseActivityDetailsExcelDivId\')" style="margin-left: 950px; margin-top: 5px; margin-bottom: 5px;">Export Excel</button>';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<div class="panel-body">';
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-group panel-group1 m_0" id="accordion1Level1bd'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div class="panel-heading" role="tab" id="constituencyHeading'+i+'">';
					//str+='<h4 class="panel-title">';
					if($("#activityLevelList").val()==1 || $("#activityLevelList").val()==2){
						str+='<a role="button" class="accordion1Level1bd'+i+'-toggle accordion-toggle PlusnMinusSignM" data-toggle="collapse" data-parent="#accordion1Level1bd" href="#mandalResultDivd'+i+'" aria-expanded="true" aria-controls="mandalResultDivd'+i+'" onclick=getLocationWiseActivityDetails("mandal","'+result.subList[i].id+'","'+i+'") >';
					}else{
						str+='<a role="button" class="accordion1Level1bd'+i+'-toggle" data-toggle="collapse" data-parent="#accordion1Level1bd" href="#mandalResultDivd'+i+'" aria-expanded="true" aria-controls="mandalResultDivd'+i+'" style="color: #666 !important;cursor: auto;">';
					}
						
							str+='<table class="table table-col table-condensed" style="display:inline">';
							str+='<tr>';							
								str+='<td class="dynChildWidthbd aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidthbd2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidthbd3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidthbd4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidthbd7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								/* str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>'; */
								str+='<td class="dynChildWidthbd8 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidthbd9 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						
						str+='</a>';
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'constituency\','+result.subList[i].id+',\'dayWiseBDConstituencyInfo'+result.subList[i].id+'\',\''+result.subList[i].name+'\',\'BD\')">Day Wise</button>';
					//str+='</h4>';
					str+='</div>';
					str+='<div id="dayWiseBDConstituencyInfo'+result.subList[i].id+'" class="daywiseSCls"></div>';
					str+='<div id="mandalResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="constituencyHeading'+i+'"></div>';
					str+='<div id="mandalResultDiv'+i+'"></div>';
					str+='<div id="localElectionBodyResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
					str+='</div>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available2.';
				str+='</td>';
				str+='</tr>';
			}
			$("#constituencyResultDiv"+resultDivNum).html(str);
			$("#constituencyResultDiv"+resultDivNum).addClass("opened");
			
			var str1='';
		
		str1+='<table class="table table-bordered" style="border:1px">';
			str+='<thead style="background-color:#EFF3F4;">';
				str1+='<tr>';
					str1+='<th rowspan="2" class="getChildWidthbd aligncenter">Location Name</th>';
					str1+='<th rowspan="2" class="getChildWidthbd2 aligncenter">Total Locations</th>';
					str1+='<th rowspan="2" class="getChildWidthbd3 aligncenter">Total Members Attended</th>';
					str1+='<th colspan="2" class="aligncenter">Total Cadre Attended</th>';
					str1+='<th colspan="2" class="aligncenter">Total Public Attended</th>';
					str1+='<th colspan="2" class="aligncenter">Photos Attached</th>';
				str1+='</tr>';
				str1+='<tr>';
					str1+='<th class="getChildWidthbd4 aligncenter">WEB</th>';
					str1+='<th class="getChildWidthbd5 aligncenter">TAB</th>';
					str1+='<th class="getChildWidthbd6 aligncenter">WEB</th>';
					str1+='<th class="getChildWidthbd7 aligncenter">TAB</th>';
					str1+='<th class="getChildWidthbd8 aligncenter">WEB</th>';
					str1+='<th class="getChildWidthbd9 aligncenter">TAB</th>';
				str1+='</tr>';
			str1+='</thead>';
			str1+='<tbody>';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str1+='<tr>';							
						str1+='<td class="dynChildWidthbd aligncenter">'+result.subList[i].name+'</td>';
						str1+='<td class="dynChildWidthbd2 aligncenter">'+result.subList[i].totalLocations+'</td>';
						str1+='<td class="dynChildWidthbd3 aligncenter">'+result.subList[i].totalMembers+'</td>';
						str1+='<td class="dynChildWidthbd4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
						str1+='<td class="dynChildWidthbd5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
						str1+='<td class="dynChildWidthbd6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
						str1+='<td class="dynChildWidthbd7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
						str1+='<td class="dynChildWidthbd8 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
						str1+='<td class="dynChildWidthbd9 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
					str1+='</tr>';
					}
				}
			str1+='</tbody>';
		str1+='</table>';
		
		$("#constWiseActivityDetailsExcelDivId").html(str1);
		}else{
			$("#constituencyResultDiv"+resultDivNum).removeClass("opened");
			$("#constituencyResultDiv"+resultDivNum).html("");
		}
			dynamicwidthforBlood();
	}
		
	
  
	function buildMandalWiseActivityResult(result,resultDivNum){
		var flag=false;
		var isOpened = $("#mandalResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			if(result != null && result.subList != null && result.subList.length > 0){
				flag=true;
				for(var i in result.subList){
					str+='<div class="panel-body">';
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelMandal1'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div  class="panel-heading" role="tab" id="mandalHeading'+i+'">';
					//str+='<h4 class="panel-title">';
					if($("#activityLevelList").val()==1){
						str+='<a role="button" class=" accordion-toggle PlusnMinusSignV collapsed"  onclick=getLocationWiseActivityDetails("village","'+result.subList[i].id+'","'+i+'") data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#panchayatResultDivd'+i+'" aria-expanded="true" aria-controls="panchayatResultDivd'+i+'">';
					}else{
						str+='<a role="button" class="collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelMandal1'+i+'" href="#panchayatResultDivd'+i+'" aria-expanded="true" aria-controls="panchayatResultDivd'+i+'" style="color: #666 !important;cursor: auto;">';
					}
						
						
							str+='<table class="table table-col table-condensed" style="display:inline">';
							str+='<tr>';							
								str+='<td class="dynChildWidthbd aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidthbd2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidthbd3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidthbd4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidthbd7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								/* str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>'; */
								str+='<td class="dynChildWidthbd8 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidthbd9 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						
						str+='</a>';
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.subList[i].id+',\'dayWiseBDMandalInfo'+result.subList[i].id+'\',\''+result.subList[i].name+'\',\'BD\')">Day Wise</button>';
					//str+='</h4>';
					str+='</div>';
					str+='<div id="dayWiseBDMandalInfo'+result.subList[i].id+'" class="daywiseSCls"></div>';
					str+='<div id="panchayatResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="mandalHeading'+i+'"></div>';
					str+='<div id="panchayatResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
					str+='</div>';
				}
			}
			$("#mandalResultDiv"+resultDivNum).html(str);
			$("#mandalResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#mandalResultDiv"+resultDivNum).removeClass("opened");
			$("#mandalResultDiv"+resultDivNum).html("");
		}
		
		
		var isOpened1 = $("#localElectionBodyResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened1){
			var str='';
			if(result != null && result.localBodyList != null && result.localBodyList.length > 0){
				flag=true;
				for(var i in result.localBodyList){
					str+='<div class="panel-body">';
					str+='<tr  id="'+result.localBodyList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-group panel-group1 m_0" id="collapseOne1LevelLeb1'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div  class="panel-heading" role="tab" id="mandalHeading1'+i+'">';
					//str+='<h4 class="panel-title">';
					if($("#activityLevelList").val()==1){
						str+='<a role="button" class=" accordion-toggle PlusnMinusSignV collapsed"  onclick=getLocationWiseActivityDetails("ward","'+result.localBodyList[i].id+'","'+i+'") data-toggle="collapse" data-parent="#collapseOne1LevelLeb1'+i+'" href="#wardResultDivd'+i+'" aria-expanded="true" aria-controls="wardResultDivd'+i+'">';
					}else{
						str+='<a role="button" class="collapsed" data-toggle="collapse" data-parent="#collapseOne1LevelLeb1'+i+'" href="#wardResultDivd'+i+'" aria-expanded="true" aria-controls="wardResultDivd'+i+'" style="color: #666 !important;cursor: auto;">';
					}
						
						
							str+='<table class="table table-col table-condensed" style="display:inline">';
							str+='<tr>';							
								str+='<td class="dynChildWidthbd aligncenter">'+result.localBodyList[i].name+'</td>';
								str+='<td class="dynChildWidthbd2 aligncenter">'+result.localBodyList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidthbd3 aligncenter">'+result.localBodyList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidthbd4 aligncenter">'+result.localBodyList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd5 aligncenter">'+result.localBodyList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd6 aligncenter">'+result.localBodyList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidthbd7 aligncenter">'+result.localBodyList[i].totalInfoCellPublicAttendance+'</td>';
								/* str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>'; */
								str+='<td class="dynChildWidthbd8 aligncenter">'+result.localBodyList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidthbd9 aligncenter">'+result.localBodyList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						
						str+='</a>';
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'mandal\','+result.subList[i].id+',\'dayWiseBDMandalInfo'+result.subList[i].id+'\',\''+result.subList[i].name+'\',\'BD\')">Day Wise</button>';
					//str+='</h4>';
					str+='</div>';
					str+='<div id="dayWiseBDMandalInfo'+result.subList[i].id+'" class="daywiseSCls"></div>';
					str+='<div id="wardResultDivd'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="mandalHeading1'+i+'"></div>';
					str+='<div id="wardResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
					str+='</div>';
				}
			}
			$("#localElectionBodyResultDiv"+resultDivNum).html(str);
			$("#localElectionBodyResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#localElectionBodyResultDiv"+resultDivNum).removeClass("opened");
			$("#localElectionBodyResultDiv"+resultDivNum).html("");
		}
		
		if(!flag){
			str+='<tr style="background-color:#663300;">';
			str+='<td colspan="13">';
			str+='No Data Available3.';
			str+='</td>';
			str+='</tr>';
		}
		dynamicwidthforBlood();
		
	}
	
	function buildPanchayatWiseActivityResult(result,resultDivNum){
		var isOpened = $("#panchayatResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<div class="panel-body">';
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-group panel-group1 m_0" id="accordion1LevelPanchayatbd'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div class="panel-heading" role="tab" id="panchayatHeading'+i+'">';
					str+='<h4 class="panel-title">';
						str+='<a role="button" class="accordion1LevelPanchayatbd'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1LevelPanchayatbd" href="#panchayatResultDivdummy'+i+'" aria-expanded="true" aria-controls="panchayatResultDivdummy'+i+'">';
						
							str+='<table class="table table-col table-condensed" style="display:inline">';
							str+='<tr>';							
								str+='<td class="dynChildWidthbd aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidthbd2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidthbd3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidthbd4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidthbd7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								/* str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>'; */
								str+='<td class="dynChildWidthbd8 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidthbd9 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						
						str+='</a>';
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'village\','+result.subList[i].id+',\'dayWiseBDVillageInfo'+result.subList[i].id+'\',\''+result.subList[i].name+'\',\'BD\')">Day Wise</button>';
					str+='</h4>';
					str+='</div>';
					str+='<div id="dayWiseBDVillageInfo'+result.subList[i].id+'" class="daywiseSCls"></div>';
					str+='<div id="panchayatResultDivdummy'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panchayatHeading'+i+'"></div>';
					//str+='<div id="mandalResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
					str+='</div>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available5.';
				str+='</td>';
				str+='</tr>';
			}
			$("#panchayatResultDiv"+resultDivNum).html(str);
			$("#panchayatResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#panchayatResultDiv"+resultDivNum).removeClass("opened");
			$("#panchayatResultDiv"+resultDivNum).html("");
		}
			dynamicwidthforBlood();
	}
	
	function buildWardWiseActivityResult(result,resultDivNum){
		var isOpened = $("#wardResultDiv"+resultDivNum).hasClass("opened");
		if(!isOpened){
			var str='';
			if(result != null && result.subList != null && result.subList.length > 0){
				for(var i in result.subList){
					str+='<div class="panel-body">';
					str+='<tr  id="'+result.subList[i].id+'">';
					str+='<td colspan="13">';
					str+='<div class="panel-group panel-group1 m_0" id="accordion1LevelWardbd'+i+'" role="tablist" aria-multiselectable="true">';
					str+='<div class="panel panel-default panel-customtd">';
					str+='<div class="panel-heading" role="tab" id="wardHeading'+i+'">';
					str+='<h4 class="panel-title">';
						str+='<a role="button" class="accordion1LevelWardbd'+i+'-toggle accordion-toggle PlusnMinusSignM collapsed" data-toggle="collapse" data-parent="#accordion1LevelWardbd" href="#wardResultDivdummy'+i+'" aria-expanded="true" aria-controls="wardResultDivdummy'+i+'">';
						
							str+='<table class="table table-col table-condensed" style="display:inline">';
							str+='<tr>';							
								str+='<td class="dynChildWidthbd aligncenter">'+result.subList[i].name+'</td>';
								str+='<td class="dynChildWidthbd2 aligncenter">'+result.subList[i].totalLocations+'</td>';
								str+='<td class="dynChildWidthbd3 aligncenter">'+result.subList[i].totalMembers+'</td>';
								str+='<td class="dynChildWidthbd4 aligncenter">'+result.subList[i].totalWebCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd5 aligncenter">'+result.subList[i].totalInfoCellCadreAttendance+'</td>';
								str+='<td class="dynChildWidthbd6 aligncenter">'+result.subList[i].totalWebPublicAttendance+'</td>';
								str+='<td class="dynChildWidthbd7 aligncenter">'+result.subList[i].totalInfoCellPublicAttendance+'</td>';
								/* str+='<td class="dynChildWidth8 aligncenter">NA</td>';
								str+='<td class="dynChildWidth9 aligncenter">NA</td>';
								str+='<td class="dynChildWidth10 aligncenter">NA</td>';
								str+='<td class="dynChildWidth11 aligncenter">NA</td>'; */
								str+='<td class="dynChildWidthbd8 aligncenter">'+result.subList[i].totalWebPhotosAttendance+'</td>';
								str+='<td class="dynChildWidthbd9 aligncenter">'+result.subList[i].totalInfoCellPhotosAttendance+'</td>';
							str+='</tr>';
							str+='</table>';
						
						str+='</a>';
						str+='<button type="button" class="btn btn-custom btn-hover btn-xs "  onclick="getDaywiseInfo(\'village\','+result.subList[i].id+',\'dayWiseBDVillageInfo'+result.subList[i].id+'\',\''+result.subList[i].name+'\',\'BD\')">Day Wise</button>';
					str+='</h4>';
					str+='</div>';
					str+='<div id="dayWiseBDVillageInfo'+result.subList[i].id+'" class="daywiseSCls"></div>';
					str+='<div id="wardResultDivdummy'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="wardHeading'+i+'"></div>';
					//str+='<div id="mandalResultDiv'+i+'"></div>';
					str+='</div>';
					str+='</div>';
					str+='</td>';
					str+='</tr>';
					str+='</div>';
				}
			}else{
				str+='<tr style="background-color:#663300;">';
				str+='<td colspan="13">';
				str+='No Data Available6.';
				str+='</td>';
				str+='</tr>';
			}
			$("#wardResultDiv"+resultDivNum).html(str);
			$("#wardResultDiv"+resultDivNum).addClass("opened");
		}else{
			$("#wardResultDiv"+resultDivNum).removeClass("opened");
			$("#wardResultDiv"+resultDivNum).html("");
		}
			dynamicwidthforBlood();
	}
	
	function dynamicwidthforBlood()
{
	$(".dynChildWidthbd9").css("width",$(".getChildWidthbd9").width());
	$(".dynChildWidthbd8").css("width",$(".getChildWidthbd8").width()+15); 
	$(".dynChildWidthbd7").css("width",$(".getChildWidthbd7").width()+15);
	$(".dynChildWidthbd6").css("width",$(".getChildWidthbd6").width()+15);
	$(".dynChildWidthbd5").css("width",$(".getChildWidthbd5").width()+15);
	$(".dynChildWidthbd4").css("width",$(".getChildWidthbd4").width()+15);
	$(".dynChildWidthbd3").css("width",$(".getChildWidthbd3").width()+15);
	$(".dynChildWidthbd2").css("width",$(".getChildWidthbd2").width()+15);
	$(".dynChildWidthbd").css("width",$(".getChildWidthbd").width()+15);
}
function getActivityQuestionAnswerCountReasonWise(){
	
	var scopeId = $('#ActivityList').val();
	var questionsId = $('#questionsId').val();
		var jsObj = {
			scopeId:scopeId,
			questionsId :questionsId
		}
	$.ajax({
		type : 'GET',
		url : 'getActivityQuestionAnswerCountReasonWiseAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jsObj)} 
	}).done(function(result){ 
	  if(result!=null && result!=0){
		  buildActivityReasonReport(result);
	  }
	});     
}
function buildActivityReasonReport(result)
{
	$("#activityHeadingDiv").show();
	var str='';
	str+='<table class="table table-bordered table-condensed">';
	 str+='<tbody>';
	  str+='<tr>';
	   for(var i in result){
		 str+='<td attr_option_id='+result[i].id+' class="bg_ef text-center" style="text-transform:uppercase">'+result[i].name+'</td>';
	  }
	  str+='</tr>';
	  str+='<tr>';
	  for(var i in result){
		str+='<td class="text-center">'+result[i].actualCount+'</td>';
	  }
	  str+='</tr>';
	  str+='</tbody>';
	  str+='</table>';
	 $("#buildActivityReasonReportTableId").html(str); 
}
function getQuestions(){
		
	var scopeId = $("#ActivityList").val();
	$('#questionsId').find('option').remove();
	//$('#questionsId').append('<option value="0">All</option>');
	var jsObj={	
			scopeId :scopeId		 
		}
		$.ajax({
				  type:'GET',
				  url: 'getQuestionsAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   if(result != null && result.length >0)
			{
				for(var i in result)
					$('#questionsId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');	
			 getActivityQuestionAnswerCountReasonWise(); 
			}
			 
		   });
	}
$(document).on("change","#questionsId",function(){	
getActivityQuestionAnswerCountReasonWise();
});