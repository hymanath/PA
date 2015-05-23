var constiArr;
function gettingConstituenciesByDistrict(locationId){	
		 var jsObj={
		         districtId:locationId
		       };
			   
		 $.ajax({
			type : "GET",
			url : "getConstituencyByDistrictAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
		
			constiArr = new Array();
			
			for(var  i in result){
				constiArr.push(result[i]);
			}		
		});
}

function buildingResults(result,locationName,basicCmmtyName,basicCmmtyId,locationTypeId,lctnId)
{
		if(result!=null)
		  {
			var name = '';
			
			
			if(result.length>0){
				name = result[0].locationName;
			var str='';
			str+='<div>';
			str+='<table class="table">';
				str+='<thead>';
				str+='<tr>';
				str+='<td>';
				str+='<span class="">';
				str+='<table class="table table-bordered" style="width:200px">';
				str+='<thead>';
				str+='<tr>';
				str+='<th style="background-color:#EDEDED;"> Total Candidates : </th>';
				str+='<td> '+result[0].total+' </td>';
				str+='</tr>';
				str+='</thead>';
				str+='</table>';
				str+='</span>';
				str+='</td>';
				str+='<td>';
				str+='<span class="">';
				str+='<table class="table table-bordered" style="width:200px">';
				str+='<thead>';
				str+='<tr>';
				str+='<th  style="background-color:#EDEDED;"> Male Candidates : </th>';
				str+='<td> '+result[0].maleCount+' </td>';
				str+='</tr>';
				str+='</thead>';
				str+='</table>';
				str+='</span>';
				str+='</td>';
				str+='<td>';
				str+='<span class="">';
				str+='<table class="table table-bordered" style="width:250px">';
				str+='<thead>';
				str+='<tr>';
				str+='<th  style="background-color:#EDEDED;"> Female Candidates : </th>';
				str+='<td> '+result[0].femaleCount+' </td>';
				str+='</tr>';
				str+='</thead>';
				str+='</table>';
				str+='</span>';
				str+='</td>';
				str+='</tr>';
				str+='</thead>';
				str+='</table>';
				str+='</div>';
				
				if(result[0].rolesList != null && result[0].rolesList.length > 0){
				str += '<div>';
				str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
				str += '<caption class="tablecaption" >Role Wise Information';
				str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str += '</caption>';
				str += ' <thead>';
				str += ' <tr>';
				str += '<th width="25%">Role</th>';
				str += '<th width="25%">Total</th>';
				str += '<th width="25%">Filled Positions</th>';				
				str += '<th width="25%">Vacant Positions</th>';
				str += '</tr>';
				str += '</thead>';
				for(var role in result[0].rolesList)
				{
					str += ' <tr>';
					str += '<td>'+result[0].rolesList[role].name+'</td>';
					str += '<td>'+result[0].rolesList[role].total+'</td>';
					str += '<td>'+result[0].rolesList[role].filledCount+'</td>';
					str += '<td>'+result[0].rolesList[role].vacancyCount+'</td>';
					str += '</tr>';
				}
				

				str += '</table>';
				str += '</div>';
				}
				
				var array1 = [" SC"," ST"," BC"," OC"," Minority"];
				var array2 = new Array();
				for(var pr1 in result[0].casteGroupVO)
				{
					array2.push(" "+result[0].casteGroupVO[pr1].castName);
				}
				var diff = $(array1).not(array2).get();
				var notParticipatedMandals = result[0].notParticipatedMandals;
				var notParticipatedLocalBodys = result[0].notParticipatedLocalBodys;
				var notParticioatedDivisions = result[0].notParticioatedOthers;
				
				var actualMandals = result[0].actualMandals;
				var actualLocalBodys = result[0].actualLocalBodys;
				var actualOthers = result[0].actualOthers;
				
				if((actualMandals != null && actualMandals > 0) || (actualLocalBodys != null && actualLocalBodys > 0)||(actualOthers != null && actualOthers > 0))
				{ 		
					str += '<div>';
						str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
						str += '<caption class="tablecaption " > No Committee Members Available from ';
						str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
						str += '</caption>';
						str += ' <thead>';
						str += ' <tr>';
						str += '<th width="25%"> Mandals </th>';
						str += '<th width="25%"> Muncipalities </th>';
						str += '<th width="25%"> Corporations </th>';
						str += '</tr>';
						str += '</thead>';

						str += ' <tr>';
						
							if(actualMandals != null && actualMandals > 0){ 
			str += '<td><a href="javascript:{tablesHideAndShow(1)}">'+(notParticipatedMandals.length)+' </a>  out of '+actualMandals+'</td>';
							}
							else
							{
								str += '<td>0</td>';
							}
							if(actualLocalBodys != null && actualLocalBodys > 0){ 
			str += '<td><a href="javascript:{tablesHideAndShow(2)}">'+(notParticipatedLocalBodys.length)+'</a>   out of '+actualLocalBodys+'</td>';
							}
							else
							{
								str += '<td>0</td>';
							}
							
							if(actualOthers != null && actualOthers > 0){ 
				str += '<td><a href="javascript:{tablesHideAndShow(3)}">'+(notParticioatedDivisions.length)+'</a>  out of '+actualOthers+'</td>';
							}
							else
							{
								str += '<td>0</td>';
							}
						
						str += '</tr>';
						str += '</table>';
				str += '</div>';
				}
				if(notParticipatedMandals != null && notParticipatedMandals.length>0)
				{
						str += '<div>';
						str += '<table class="table table-bordered areaDetailsCls" style="border:2px solid #FC6 !important" id="mandaldtls" >';
						str += '<caption class="tablecaption" >No Committee Member available from these Mandals';
						str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
						str += '</caption>';
						str += ' <thead>';
						str += ' <tr>';
						str += '<th width="25%"> Mandal Name </th>';
						//str += '<th width="25%"> Total Count </th>';
						str += '</tr>';
						str += '</thead>';
						for(var n in notParticipatedMandals)
						{
							str += ' <tr>';
							str += '<td>'+notParticipatedMandals[n].castName+'</td>';
							str += '</tr>';
						}
						
						str += '</table>';
						str += '</div>';
				}
				
				if(notParticipatedLocalBodys != null && notParticipatedLocalBodys.length>0)
				{
						str += '<div>';
						str += '<table class="table table-bordered areaDetailsCls" style="border:2px solid #FC6 !important" id="localbodydtls" >';
						str += '<caption class="tablecaption" >No Committee Member available from these Muncipalities/Corporations';
						str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
						str += '</caption>';
						str += ' <thead>';
						str += ' <tr>';
						str += '<th width="25%"> Mandal Name </th>';
						//str += '<th width="25%"> Total Count </th>';
						str += '</tr>';
						str += '</thead>';
						for(var n in notParticipatedLocalBodys)
						{
							str += ' <tr>';
							str += '<td>'+notParticipatedLocalBodys[n].castName+'</td>';
							str += '</tr>';
						}
						
						str += '</table>';
						str += '</div>';
				}
				
				if(notParticioatedDivisions != null && notParticioatedDivisions.length>0)
				{
						str += '<div>';
						str += '<table class="table table-bordered areaDetailsCls" style="border:2px solid #FC6 !important" id="divisionsdtls" >';
						str += '<caption class="tablecaption" >No Committee Member available from these Divisions';
						str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
						str += '</caption>';
						str += ' <thead>';
						str += ' <tr>';
						str += '<th width="25%"> Mandal Name </th>';
						//str += '<th width="25%"> Total Count </th>';
						str += '</tr>';
						str += '</thead>';
						for(var n in notParticioatedDivisions)
						{
							str += ' <tr>';
							str += '<td>'+notParticioatedDivisions[n].castName+'</td>';
							str += '</tr>';
						}
						
						str += '</table>';
						str += '</div>';
				}
				
				
				
				var constiResultArr = new Array;
				for(var consti in result[0].constiVOList)
				{			
					constiResultArr.push(result[0].constiVOList[consti].castName);
				}

				var difference= $(constiArr).not(constiResultArr).get();
		
				if(difference.length > 0){
				str+='<div id="constiInfo">';
				str+='<p class="alert alert-info" style="margin-top: -15px;text-transform: uppercase">      <a href="javascript:{getConstituency()}">No  Committee  Members  In <span style="color:green;font-weight:bold;">'+ difference.length+' </span>Constituencies </a></p>';			
				//str+='<div id="constiNamesInfo" style="display:none;">';
				str+='<p id="constiNamesInfo" class="alert alert-info" style="margin-top: -40px;text-transform: uppercase;display:none;border-color:none; !important">'+ difference.join("  ,  ")+' </p>';
				str+='</div>';
				//str+='</div>';				
				}	
				
				if(diff.length > 0){
				str+='<div id="casteInfo">';
				str+='<p class="alert alert-info" style="margin-top: -15px;text-transform: uppercase">No  Committee  Members  From       '+ diff.toString()+' </p>';
				str+='</div>';
				}				
				str += '<div>';
				str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
				str += '<caption class="tablecaption" >Caste Group Wise Information';
				str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str += '</caption>';
				str += ' <thead>';
				str += ' <tr>';
				str += '<th width="25%">Caste Group</th>';
				str += '<th width="25%">Total Count</th>';
				str += '<th width="25%">Male</th>';
				str += '<th width="25%">Female</th>';
				str += '</tr>';
				str += '</thead>';
				for(var pr in result[0].casteGroupVO)
				{
					str += ' <tr>';
					str += '<td>'+result[0].casteGroupVO[pr].castName+'</td>';
					str += '<td>'+result[0].casteGroupVO[pr].casteId+'</td>';
					str += '<td>'+result[0].casteGroupVO[pr].stateId+'</td>';
					str += '<td>'+result[0].casteGroupVO[pr].castStateId+'</td>';
					str += '</tr>';
				}
				

				str += '</table>';
				str += '</div>';
				
				
				str += '<div>';
				str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
				str += '<caption class="tablecaption" >Caste Wise Information';
				str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str += '</caption>';
				str += ' <thead>';
				str += ' <tr>';
				str += '<th width="25%">Caste Name</th>';
				str += '<th width="25%">Total Count</th>';
				str += '<th width="25%">Male</th>';
				str += '<th width="25%">Female</th>';
				str += '</tr>';
				str += '</thead>';
				for(var pr in result[0].casteNameVO)
				{
					var malecount = result[0].casteNameVO[pr].maleCount != null ? result[0].casteNameVO[pr].maleCount:0;
					var femaleCount = result[0].casteNameVO[pr].femaleCount != null ? result[0].casteNameVO[pr].femaleCount:0;
					var allCount = parseInt(malecount)+parseInt(femaleCount);
					
					str += ' <tr>';
					str += '<td>'+result[0].casteNameVO[pr].castName+'</td>';
					str += '<td>'+allCount+'</td>';
					str += '<td>'+malecount+'</td>';
					str += '<td>'+femaleCount+'</td>';
					str += '</tr>';
				}
				

				str += '</table>';
				str += '</div>';
				
				
				str += '<div>';
				str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
				str += '<caption class="tablecaption">Age Range Wise Information';
				str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>                </caption>';
				str += '<thead>';
				str += '<tr>';
				str += ' <th width="25%">Between Age</th>';
				str += '<th width="25%">Total Count</th>';
				str += '<th width="25%">Male</th>';
				str += '<th width="25%">Female</th>';
				str += '</tr>';
				str += '</thead>';
				for(var tp in result[0].ageDetailsIfoVO)
				{
					str += ' <tr>';
					str += '<td>'+result[0].ageDetailsIfoVO[tp].castName+'</td>';
					str += '<td>'+result[0].ageDetailsIfoVO[tp].casteId+'</td>';
					str += '<td>'+result[0].ageDetailsIfoVO[tp].stateId+'</td>';
					str += '<td>'+result[0].ageDetailsIfoVO[tp].castStateId+'</td>';
					str += '</tr>';
				}

				str += ' </table> ';
				str += '</div>';
				if(result[0].constiVOList != null){
				str += '<div>';
				str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
				str += '<caption class="tablecaption" >Constituency Wise Information';
				str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str += '</caption>';
				str += ' <thead>';
				str += ' <tr>';
				str += '<th width="25%">Constituency Name</th>';
				str += '<th width="25%">Total Count</th>';
				str += '<th width="25%">Male</th>';
				str += '<th width="25%">Female</th>';
				str += '</tr>';
				str += '</thead>';
				for(var consti in result[0].constiVOList)
				{
					var malecount = result[0].constiVOList[consti].maleCount != null ? result[0].constiVOList[consti].maleCount:0;
					var femaleCount = result[0].constiVOList[consti].femaleCount != null ? result[0].constiVOList[consti].femaleCount:0;

					str += ' <tr>';
					str += '<td>'+result[0].constiVOList[consti].castName+'</td>';
					str += '<td>'+result[0].constiVOList[consti].casteCount+'</td>';
					str += '<td>'+malecount+'</td>';
					str += '<td>'+femaleCount+'</td>';
					str += '</tr>';
				}
				

				str += '</table>';
				str += '</div>';
				}
				if(result[0].mandalLevelDetails != null && result[0].mandalLevelDetails.length > 0){
				str += '<div>';
				str += '<table class="table table-bordered" style="border:2px solid #FC6 !important">';
				str += '<caption class="tablecaption" >Mandal/Municipality/Corp Wise Information';
				str += '<hr style="margin-top:0px;margin-bottom:0px;margin-right:50%;"/>';
				str += '</caption>';
				str += ' <thead>';
				str += ' <tr>';
				str += '<th width="50%">Mandal/Municipality/Corp</th>';
				str += '<th width="50%">Total Count</th>';
				str += '</tr>';
				str += '</thead>';
				for(var consti in result[0].mandalLevelDetails)
				{
					str += ' <tr>';
					str += '<td>'+result[0].mandalLevelDetails[consti].castName+'</td>';
					str += '<td>'+result[0].mandalLevelDetails[consti].casteCount+'</td>';
					str += '</tr>';
				}
				str += '</table>';
				str += '</div>';
				}
				
				str += '<span id="performanceId" class="btn btn-info pull-right" attr_distId="'+lctnId+'">Cadre Members Booths Performance </span>';
				str+='<table class="table table-bordered" id="constiTableId">';
				str+='<thead>';
				//if(basicCmmtyId == 1)
					//str+='<th  style="width: 178px; padding-left: 34px;">Designation</th>';
				
				str+='<th style="width:50px;"> </th>';
				str+='<th style="padding-left: 72px;"> MEMBER </th>';
				//str+='<th style="padding-left: 19px;">MemberShipNo</th>';
				str+='<th style="padding-left: 19px;"> MOBILE NO </th>';
				str+='<th style="padding-left: 19px;"> AGE </th>';
				str+='<th style="padding-left: 19px;"> GENDER </th>';
				str+='<th style="padding-left: 19px;"> CASTE NAME </th>';
				str+='<th style="padding-left: 19px;"> VOTER ID </th>';
				str+='</thead>';
				for(var i in result){
				 str+='<tr>';
				/* if(basicCmmtyId == 1){
				 if(result[i].role!=null){
					str+='<td  style="padding-top: 14px; padding-left: 37px;">'+result[i].role+'</td>';
				 }else{
					str+='<td  style="padding-top: 14px; padding-left: 37px;">  </td>';
				 }
				 }*/
				 str+='<td><img  style="margin-top: 5px;" width="50"  height="50" src="http://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/>';
				
				 str+=' </td>';
				 str+='<td> '+result[i].name+' ';
				 if(basicCmmtyId != 1){
				 if(result[i].commiteeName!=null){
					 str+='<br>'+result[i].commiteeName+' - ';
				 }else{
					 str+='<br>';
				 }
				 }
				else{
					 str+='<br>';
				 }
				 
				  //if(basicCmmtyId == 1){
				 if(result[i].role!=null){
					 str+=' '+result[i].role+'';
				 }else{
					 str+='';
				 }
				str+=' <br/> <span> Constituency : '+result[i].constituencyName+' </span>';
				// }	 
				  str+=' </td>';	  
				// str+='<td style="padding-top: 15px; padding-left: 15px;width:281px;">'+result[i].name+'</td>';
				 //str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].membershipNo+'</td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
				  str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].voterCardNo+' </td>';
				 str+='</tr>';
				}
			   str+='</tbody>';
			   str+='</table>';
				str+=' <div class="modal-footer" style="margin-top: 50px;">';
				str+=' <button data-dismiss="modal" class="btn btn-default" type="button">Close</button>';
				str+='</div>';
		

		   $("#cadreDetailsDiv").html(str);
		   
		   //$('#dialogSummary').find('h3').html('<span>'+ capitalize(name)+' '+areaType+' '+ basicCmmtyName +' '+ capitalize(type) +' </span>');
		   $('#dialogSummaryDistsrict').find('h3').html('<span>'+ capitalize(name)+'  ('+ basicCmmtyName +') </span>');
		   $("#dialogSummaryDistsrict").modal("show");
		  
		  } 
		  else{
				$("#cadreDetailsDiv").html("No Data Available.");
			}
			
			/*$("#constiTableId").dataTable({
					"iDisplayLength": 20,
					"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
				});
			*/
			$(".areaDetailsCls").hide();
		}
}

function tablesHideAndShow(typeId)
{
	$(".areaDetailsCls").hide();
	if(typeId == 1)
	{
		$("#mandaldtls").show();
	}
	else if(typeId == 2)
	{
		$("#localbodydtls").show();
	}
	else if(typeId == 3)
	{
		$("#divisionsdtls").show();
	}
}

function getConstituency(){
	$("#constiNamesInfo").toggle();
}