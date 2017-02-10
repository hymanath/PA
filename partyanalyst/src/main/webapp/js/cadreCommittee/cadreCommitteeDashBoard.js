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
				str+='<th style="padding-left: 19px;"> STATUS </th>';
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
				 str+='<td><img  style="margin-top: 5px;" width="50"  height="50" src="https://www.mytdp.com/images/cadre_images/'+result[i].imagePath+'" onerror="setDefaultImage(this);"/>';
				
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
				str+=' <br/> <span> MemberShipNo : <a target="_blank" href="cadreDetailsAction.action?cadreId='+result[i].id+'"> '+result[i].id+' </a> </span>';
				// }	 
				  str+=' </td>';	  
				// str+='<td style="padding-top: 15px; padding-left: 15px;width:281px;">'+result[i].name+'</td>';
				 //str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].membershipNo+'</td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].mobileNo+'</td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;">'+result[i].age+' </td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].gender+' </td>';
				 str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].casteName+'('+result[i].casteGroupName+') </td>';
				  str+='<td style="padding-left: 15px; padding-top: 13px;"> '+result[i].voterCardNo+' </td>';
				  var status = result[i].occupation;
				  
				  if(status != null && status == "P"){
					  str+='<td style="padding-left: 15px; padding-top: 13px;" class="orangeCls"> Proposed </td>';
				  }else if(status != null && status == "F"){
					  str+='<td style="padding-left: 15px; padding-top: 13px;color:#449D44;" class=""> Finalized </td>';
				  }
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

//999
	function buildResultDistrictSummary(result,mandalCheck,villageCheck,districtCommCheck,jObj){
		$("#distSummaryBody").html("");
		var districtInfoArr = [];
		var villageInfoArr = [];
		$("#headingId").html("DISTRICT WISE COMMITTEES");
		//$("#tableHeadingId").html("DISTRICT");
		$(".excelId").show();
		var str = '';
		
		var panTotal =0; 	
		var panStarted =0; 	
		var panCompleted =0; 	
		var panMembers =0; 	
		var panAffStarted =0; 	
		var panAffCompleted =0;
		
		var panyuvathaStartedCount = 0;
		var panmahilaStartedCount = 0;
		var panrythuStartedCount = 0;
		var panothersStartedCount = 0;
		var panyuvathaCompltdCount = 0;
		var panmahilaCompltdCount = 0;
		var panrythuCompltdCount = 0;
		var panothersCompltdCount = 0;		
		var panbcCellStartedCount = 0;
		var panscCellStartedCount = 0;
		var panstCellStartedCount = 0;
		var panminorityStartedCount = 0;
		var panCristianStartedCount = 0;
		var pantnsfStartedCount = 0;
		var pantntucStartedCount = 0;
		var pantsnvStartedCount = 0;
		var panlegalCellStartedCount = 0;
		var pandoctorStartedCount = 0;
		var pankalluGeethaStartedCount = 0;
		var panchenethaStartedCount = 0;
		var panrakshaVedikaStartedCount = 0;
		var pantnusStartedCount = 0;
		var pancommercialStartedCount = 0;
		var panculturalStartedCount = 0;
		var pantradeStartedCount = 0;
		var  pantradeCmpltdCount = 0;
		var  panbcCellCmpltdCount = 0;
		var  panscCellCmpltdCount = 0;
		var  panstCellCmpltdCount = 0;
		var  panminorityCmpltdCount = 0;
		var  panCristianCmpltdCount = 0;
		var  pantnsfCmpltdCount = 0;
		var  pantntucCmpltdCount = 0;
		var  pantsnvCmpltdCount = 0;
		var  panlegalCmpltdCount = 0;
		var  pandoctorCmpltdCount = 0;
		var  pankalluGeethaCmpltdCount = 0;
		var  panchenethaCmpltdCount = 0;
		var  panrakshaVedikaCmpltdCount = 0;
		var  pantnusCmpltdCount = 0;
		var  pancommercialCmpltdCount = 0;
		var  panculturalCmpltdCount = 0;

		
		
		var mandTotal =0; 	
		var mandStarted =0; 	
		var mandCompleted =0; 	
		var mandMembers =0; 	
		var mandAfStarted =0; 	
		var mandAfCompleted =0;
		
		var yuvathaStartedCount = 0;
		var mahilaStartedCount = 0;
		var rythuStartedCount = 0;
		var othersStartedCount = 0;
		var yuvathaCompltdCount = 0;
		var mahilaCompltdCount = 0;
		var rythuCompltdCount = 0;
		var othersCompltdCount = 0;		
		var bcCellStartedCount = 0;
		var scCellStartedCount = 0;
		var stCellStartedCount = 0;
		var minorityStartedCount = 0;
		var CristianStartedCount = 0;
		var tnsfStartedCount = 0;
		var tntucStartedCount = 0;
		var tsnvStartedCount = 0;
		var legalCellStartedCount = 0;
		var doctorStartedCount = 0;
		var kalluGeethaStartedCount = 0;
		var chenethaStartedCount = 0;
		var rakshaVedikaStartedCount = 0;
		var tnusStartedCount = 0;
		var commercialStartedCount = 0;
		var culturalStartedCount = 0;
		var tradeStartedCount = 0;
		var  tradeCmpltdCount = 0;
		var  bcCellCmpltdCount = 0;
		var  scCellCmpltdCount = 0;
		var  stCellCmpltdCount = 0;
		var  minorityCmpltdCount = 0;
		var  CristianCmpltdCount = 0;
		var  tnsfCmpltdCount = 0;
		var  tntucCmpltdCount = 0;
		var  tsnvCmpltdCount = 0;
		var  legalCmpltdCount = 0;
		var  doctorCmpltdCount = 0;
		var  kalluGeethaCmpltdCount = 0;
		var  chenethaCmpltdCount = 0;
		var  rakshaVedikaCmpltdCount = 0;
		var  tnusCmpltdCount = 0;
		var  commercialCmpltdCount = 0;
		var  culturalCmpltdCount = 0;	

		
		var distTotal =0; 	
		var distStarted =0; 	
		var distCompleted =0; 	
		var distMembers =0; 	
		var distAffStarted =0; 	
		var distAfStarted =0; 	
		var distAffCompleted =0;
		var percentage = 0;
		var perc = 0;
		
		var dstctyuvathaStartedCount = 0;
		var dstctmahilaStartedCount = 0;
		var dstctrythuStartedCount = 0;
		var dstctothersStartedCount = 0;
		var dstctyuvathaCompltdCount = 0;
		var dstctmahilaCompltdCount = 0;
		var dstctrythuCompltdCount = 0;
		var dstctothersCompltdCount = 0;
		var dstctbcCellStartedCount = 0;
		var dstctscCellStartedCount = 0;
		var dstctstCellStartedCount = 0;
		var dstctminorityStartedCount = 0;
		var dstctCristianStartedCount = 0;
		var dstcttnsfStartedCount = 0;
		var dstcttntucStartedCount = 0;
		var dstcttsnvStartedCount = 0;
		var dstctlegalCellStartedCount = 0;
		var dstctdoctorStartedCount = 0;
		var dstctkalluGeethaStartedCount = 0;
		var dstctchenethaStartedCount = 0;
		var dstctrakshaVedikaStartedCount = 0;
		var dstcttnusStartedCount = 0;
		var dstctcommercialStartedCount = 0;
		var dstctculturalStartedCount = 0;
		var dstcttradeStartedCount = 0;
		var  dstcttradeCmpltdCount = 0;
		var  dstctbcCellCmpltdCount = 0;
		var  dstctscCellCmpltdCount = 0;
		var  dstctstCellCmpltdCount = 0;
		var  dstctminorityCmpltdCount = 0;
		var  dstctCristianCmpltdCount = 0;
		var  dstcttnsfCmpltdCount = 0;
		var  dstcttntucCmpltdCount = 0;
		var  dstcttsnvCmpltdCount = 0;
		var  dstctlegalCmpltdCount = 0;
		var  dstctdoctorCmpltdCount = 0;
		var  dstctkalluGeethaCmpltdCount = 0;
		var  dstctchenethaCmpltdCount = 0;
		var  dstctrakshaVedikaCmpltdCount = 0;
		var  dstcttnusCmpltdCount = 0;
		var  dstctcommercialCmpltdCount = 0;
		var  dstctculturalCmpltdCount = 0;	

		
		var isConsiderAffl=  $('#considerAfflId').is(':checked')?"true":"false";
		//console.log("isConsiderAffl  :"+isConsiderAffl);
		str+='<table class="table table-bordered table-condensed " id="districtTableId" style="width:1150px !important; background-color:rgba(0,0,0,0.1);">';
       
		if(mandalCheck == "true" && villageCheck == "true" && districtCommCheck == "true"){//111
			str+='<thead>';
            str+='<tr>';
			str+='<th rowspan="4" style="text-align:center">District No</th>';
			str+='<th rowspan="4" style="text-align:center">District Name</th>';
			if(isConsiderAffl =='true')
				str+='<th style="text-align:center" colspan="47">TOWN / MANDAL / DIVISION  </th>';
			else
				str+='<th style="text-align:center" colspan="15">TOWN / MANDAL / DIVISION  </th>';

		   if(isConsiderAffl =='true')
				str+='<th style="text-align:center" colspan="47">VILLAGE / WARD  </th>';
			else
				str+='<th style="text-align:center" colspan="15">VILLAGE / WARD  </th>';
			
			
			if(result[0].cadreIVRVO != null)
			{
				var length = ((result[0].cadreIVRVO.optionsList.length)-1) * 2;
				//console.log("length  :"+length);
				str+=' <th style="text-align:center" colspan='+length+' rowspan="2"> VILLAGE / WARD IVR DETAILS  </th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length) * 2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS </th>';
			}
			
			if(isConsiderAffl =='true')
				str+=' <th style="text-align:center" colspan="15">DISTRICT LEVEL COMMITTEES  </th>';
			else
				str+=' <th style="text-align:center" colspan="47">DISTRICT LEVEL COMMITTEES  </th>';
					
			
			
            str+='</tr>';
            str+='<tr>';
			
			
			if(mandalCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees  </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}
				else
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}
			if(villageCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees  </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}
				else
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}
			if(districtCommCheck == "true")
			{
				if(isConsiderAffl == "true")
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}		
				else
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}	
			str+='</tr>';
            str+='<tr>';
			
            str+='<th  rowspan="2"  class="mainCls" >Total</th>';
			str+='<th  rowspan="2"  class="mainCls" >Started</th>';
			str+='<th  rowspan="2"  class="mainCls" >Completed</th>';
			str+='<th  rowspan="2"  class="mainCls" >Members</th>';
			 str+='<th  rowspan="2"  class="affilCls">Total</th>';
			str+='<th  rowspan="2" class="affilCls"> Started</th>';
			str+='<th  rowspan="2" class="affilCls"> Completed</th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Rythu </th>';
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls" > Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"   class="affilCls" > Others </th>';
			}
			
			str+='<th  rowspan="2"  class="mainCls" >Total</th>';
			str+='<th  rowspan="2"  class="mainCls" >Started</th>';
			str+='<th  rowspan="2"  class="mainCls" >Completed</th>';
			str+='<th  rowspan="2"  class="mainCls" >Members</th>';
			 str+='<th  rowspan="2"  class="affilCls">Total</th>';
			str+='<th  rowspan="2" class="affilCls"> Started</th>';
			str+='<th  rowspan="2" class="affilCls"> Completed</th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Rythu </th>';
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls" > Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"   class="affilCls" > Others </th>';
			}
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th rowspan="2">%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';				
					}
				}*/
			}
			
			str+='<th  rowspan="2" class="mainCls" >Total</th>';
			str+='<th  rowspan="2" class="mainCls" >Started</th>';
			str+='<th  rowspan="2" class="mainCls" >Completed</th>';
			str+='<th  rowspan="2" class="mainCls" >Members</th>';
			 str+='<th  rowspan="2" class="affilCls">Total</th>';
			str+='<th  rowspan="2" class="affilCls"> Started</th>';
			str+='<th  rowspan="2" class="affilCls"> Completed</th>';
            str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"   class="affilCls"> Telugu Rythu </th>';
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls"> Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"   class="affilCls"> Others </th>';
			}	
			
			 str+='</tr>';
			 str+='<tr>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else//others
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}
			 
			str+='</tr>';	
			str+='</thead>';
		}
		else if((mandalCheck == "true" && villageCheck == "true") || (districtCommCheck == "true" && villageCheck == "true")  || (districtCommCheck == "true" && mandalCheck == "true")){//222
			str+='<thead>';
            str+='<tr>';
			str+='<th rowspan="4" style="text-align:center">District No</th>';
			str+='<th rowspan="4" style="text-align:center">District Name</th>';
			if(mandalCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center" colspan="47">TOWN / MANDAL / DIVISION </th>';
				}
				else
				{
					str+='<th style="text-align:center" colspan="15">TOWN / MANDAL / DIVISION </th>';
				}
			}
				
			if(villageCheck == "true")
				str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
		
			if(result[0].cadreIVRVO != null)
			{
				var length = ((result[0].cadreIVRVO.optionsList.length)-1) * 2;
				str+=' <th style="text-align:center" colspan='+length+' rowspan="2"> VILLAGE / WARD  IVR DETAILS  </th>';
			//	var length1 = (result[0].cadreIVRVO.optionsList1.length -1)*2;
				//str+=' <th style="text-align:center" colspan='+length1+'>IVR DETAILS  </th>';
			}
			
			if(districtCommCheck == "true")
			{
				if(isConsiderAffl == "true")
					str+='<th style="text-align:center" colspan="46">DISTRICT LEVEL COMMITTEES </th>';			
				else
					str+='<th style="text-align:center" colspan="14">DISTRICT LEVEL COMMITTEES </th>';	
			
			}						
		
            str+='</tr>';
            str+='<tr>';
			
			if(mandalCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}
				else
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}
			if(villageCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}
				else
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}
			if(districtCommCheck == "true")
			{
				if(isConsiderAffl == "true")
				{
					str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="43"  class="affilCls"> Affiliated Committees </th>';
				}		
				else
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}	
			str+='</tr>';
            str+='<tr>';
			if(mandalCheck == "true")
			{
				str+='<th rowspan="2"  class="mainCls" >Total</th>';
				str+='<th rowspan="2" class="mainCls" >Started</th>';
				str+='<th rowspan="2" class="mainCls" >Completed</th>';			
				str+='<th rowspan="2" class="mainCls" >Members</th>';
					str+='<th rowspan="2"  class="affilCls">Total</th>';
				str+='<th rowspan="2" class="affilCls">Started</th>';
				str+='<th rowspan="2" class="affilCls"> Completed</th>';
				str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
				str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
				str+='<th colspan="2"  class="affilCls" > Telugu Rythu </th>';
				if(isConsiderAffl =='true')
				{				
					str+='<th colspan="2" class="hideCls"> Trade  </th>';
					str+='<th colspan="2" class="hideCls"> BC Cell </th>';
					str+='<th colspan="2" class="hideCls"> SC Cell </th>';
					str+='<th colspan="2" class="hideCls"> ST Cell </th>';
					str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
					str+='<th colspan="2" class="hideCls"> Christian </th>';
					str+='<th colspan="2" class="hideCls"> TNSF </th>';
					str+='<th colspan="2" class="hideCls"> TNTUC </th>';
					str+='<th colspan="2" class="hideCls"> TSNV </th>';
					str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
					str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
					str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
					str+='<th colspan="2" class="hideCls"> Chenetha </th>';
					str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
					str+='<th colspan="2" class="hideCls"> TNUS   </th>';
					str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
					str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
				}
				else
				{
					str+='<th colspan="2"   class="affilCls"> Others </th>';
				}
			}
			if(villageCheck == "true")
			{
				str+='<th class="mainCls" rowspan="2" >Total</th>';
			str+='<th class="mainCls" rowspan="2"  >Started</th>';
			str+='<th class="mainCls" rowspan="2"  >Completed</th>';
			str+='<th class="mainCls" rowspan="2"  >Members</th>';
			str+='<th class="affilCls" rowspan="2"  >Total</th>';
			str+='<th class="affilCls" rowspan="2" > Started</th>';
			str+='<th class="affilCls" rowspan="2" > Completed</th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Rythu </th>';
				
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls"> Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"   class="affilCls"> Others </th>';
			}
			
				
			}
			
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th  rowspan="2">%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}*/
			}
			
			if(districtCommCheck == "true")
			{
				str+='<th rowspan="2" class="mainCls" >Total</th>';
				str+='<th rowspan="2" class="mainCls" >Started</th>';
				str+='<th rowspan="2" class="mainCls" >Completed</th>';
				str+='<th rowspan="2" class="mainCls" >Members</th>';
				str+='<th rowspan="2"  class="affilCls">Total</th>';
				str+='<th rowspan="2" class="affilCls"> Started</th>';
				str+='<th rowspan="2" class="affilCls"> Completed</th>';
				str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
				str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
				str+='<th colspan="2"   class="affilCls"> Telugu Rythu </th>';
				if(isConsiderAffl =='true')
				{				
					str+='<th colspan="2" class="hideCls"> Trade  </th>';
					str+='<th colspan="2" class="hideCls"> BC Cell </th>';
					str+='<th colspan="2" class="hideCls"> SC Cell </th>';
					str+='<th colspan="2" class="hideCls"> ST Cell </th>';
					str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
					str+='<th colspan="2" class="hideCls"> Christian </th>';
					str+='<th colspan="2" class="hideCls"> TNSF </th>';
					str+='<th colspan="2" class="hideCls"> TNTUC </th>';
					str+='<th colspan="2" class="hideCls"> TSNV </th>';
					str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
					str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
					str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
					str+='<th colspan="2" class="hideCls"> Chenetha </th>';
					str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
					str+='<th colspan="2" class="hideCls"> TNUS   </th>';
					str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
					str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
				}
				else
				{
					str+='<th colspan="2"   class="affilCls"> Others </th>';
				}
			}	
			
			 str+='</tr>';
			 str+='<tr>';
			 
			 if(mandalCheck == "true")
			{		
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';		
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str+='<th  class="hideCls">Started</th>';
						str+='<th  class="hideCls">Completed</th>';
					 }
				}
				else
				{
					str+='<th class="affilCls">Started</th>';
					str+='<th class="affilCls">Completed</th>';
				}
			}
			
			 if(villageCheck == "true")
			{		
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';		
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str+='<th  class="hideCls">Started</th>';
						str+='<th  class="hideCls">Completed</th>';
					 }
				}
				else
				{
					str+='<th class="affilCls">Started</th>';
					str+='<th class="affilCls">Completed</th>';
				}
			}
			 
			if(districtCommCheck == "true")
			{
				str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';
				 str+='<th class="affilCls">Started</th>';
				 str+='<th class="affilCls">Completed</th>';
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str+='<th  class="hideCls">Started</th>';
						str+='<th  class="hideCls">Completed</th>';
					 }
				}
				else
				{
					str+='<th class="affilCls">Started</th>';
					str+='<th class="affilCls">Completed</th>';
				}
			}
			str+='</tr>';	
			str+='</thead>';	
			
		}
		else if(mandalCheck == "true"){//333
			str+='<thead>';
			str+='<tr>';
			str+='<th rowspan="4" style="text-align:center">District No</th>';
			str+='<th rowspan="4" style="text-align:center">District Name</th>';
			if(mandalCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center" colspan="47">TOWN / MANDAL / DIVISION </th>';
				}
				else
				{
					str+='<th style="text-align:center" colspan="15">TOWN / MANDAL / DIVISION </th>';
				}
			}
			
           
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length -1)*2;
				str+=' <th style="text-align:center" colspan='+length+' rowspan="2"> VILLAGE / WARD IVR DETAILS  </th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length -1 ) * 2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS  </th>';
			}
            str+='</tr>';
            str+='<tr>';
			if(mandalCheck == "true")
			{
				if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="43" class="affilCls"> Affiliated Committees </th>';
				}
				else
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="11" class="affilCls"> Affiliated Committees </th>';
				}
			}
			if(villageCheck == "true")
			{
				str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
				str+='<th style="text-align:center;" colspan="2" class="affilCls"> Affiliated Committees </th>';
			}
			str+='</tr>';
            str+='<tr>';
			str+='<th rowspan="2" class="mainCls" >Total</th>';
			str+='<th rowspan="2" class="mainCls" >Started</th>';
			str+='<th rowspan="2" class="mainCls" >Completed</th>';
			str+='<th rowspan="2" class="mainCls" >Members</th>';
			str+='<th rowspan="2" class="affilCls">Total</th>';
			str+='<th rowspan="2" class="affilCls">  Started </th>';
			str+='<th rowspan="2" class="affilCls">  Completed </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Rythu </th>';
				
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls"> Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"   class="affilCls"> Others </th>';
			}
				
			
			
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th  rowspan="2">%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}*/
			}
			 str+='</tr>';
			 str+='<tr>';
			 str+='<th class="affilCls">Started</th>';
			 str+='<th class="affilCls">Completed</th>';
			 str+='<th class="affilCls">Started</th>';
			 str+='<th class="affilCls">Completed</th>';
			 str+='<th class="affilCls">Started</th>';
			 str+='<th class="affilCls">Completed</th>';		
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}
			
			str+='</tr></thead>';	
		}
		else if(villageCheck == "true"){//444
			str+='<thead>';
			str+='<th rowspan="4" style="text-align:center">District No</th>';
			str+='<th rowspan="4" style="text-align:center">District Name</th>';
            str+=' <th style="text-align:center" colspan="6">VILLAGE / WARD</th>';
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length -1 ) * 2;
				str+=' <th style="text-align:center" colspan='+length+' rowspan="2"> VILLAGE / WARD IVR DETAILS </th>';
				
				//var length1 = (result[0].cadreIVRVO.optionsList1.length -1 ) * 2;
				//str+=' <th style="text-align:center" colspan='+length1+'>WARD IVR DETAILS  </th>';
			}
            str+='</tr>';
            str+='<tr>';
			//str+='<th colspan="4"  class="mainCls" > Main Committees </th>';
			//str+='<th colspan="2"   class="affilCls"> Affiliated Committees </th>';			
			
			if(isConsiderAffl == "true")
			{
				str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
				str+='<th style="text-align:center;" colspan="44" class="affilCls"> Affiliated Committees </th>';
			}		
			else
			{
				str+='<th style="text-align:center;" colspan="4"  class="mainCls" > Main Committees </th>';
				str+='<th style="text-align:center;" colspan="12" class="affilCls"> Affiliated Committees </th>';
			}
				
			str+='</tr>';
            str+='<tr>';
			str+='<th class="mainCls" rowspan="2" >Total</th>';
			str+='<th class="mainCls" rowspan="2"  >Started</th>';
			str+='<th class="mainCls" rowspan="2"  >Completed</th>';
			str+='<th class="mainCls" rowspan="2"  >Members</th>';
			str+='<th class="affilCls" rowspan="2"  >Total</th>';
			str+='<th class="affilCls" rowspan="2" > Started</th>';
			str+='<th class="affilCls" rowspan="2" > Completed</th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Rythu </th>';
				
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls"> Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"   class="affilCls"> Others </th>';
			}
				
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th>'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
			/*	for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				*/
			}
			 str+='</tr>';
			 str+='<tr>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else//others
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}
			str+='</tr></thead>';	
		}
		else if(districtCommCheck == "true" ){//555
			str+='<thead>';
			str+='<tr>';
			str+='<th rowspan="4" style="text-align:center">District No</th>';
			str+='<th rowspan="4" style="text-align:center">District Name</th>';
			if(isConsiderAffl == "true")
				str+='<th style="text-align:center" colspan="47">DISTRICT LEVEL </th>';			
			else
				str+='<th style="text-align:center" colspan="15">DISTRICT LEVEL </th>';	
			
			if(result[0].cadreIVRVO != null)
			{
				var length = (result[0].cadreIVRVO.optionsList.length -1)*2;
				str+=' <th style="text-align:center" colspan='+length+'>IVR DETAILS</th>';
			}
            str+='</tr>';
			str+='<tr role="row">';
			if(isConsiderAffl =='true')
				{
					str+='<th style="text-align:center;" colspan="4" class="mainCls" > Main Committees </th>';
					str+='<th style="text-align:center;" colspan="42" class="affilCls"> Affiliated Committees </th>';
				}
			else
				{
					str+='<th class="mainCls" colspan="4" style="text-align:center;" rowspan="1"> Main Committees </th>';
					str+='<th class="affilCls" colspan="11" style="text-align:center;" rowspan="1"> Affiliated Committees </th>';
				}
			str+='</tr>';
            str+='<tr>';
			str+='<th rowspan="2" class="mainCls" >Total</th>';
			str+='<th rowspan="2" class="mainCls" >Started</th>';
			str+='<th rowspan="2" class="mainCls" >Completed</th>';
			str+='<th rowspan="2" class="mainCls" >Members</th>';
			str+='<th rowspan="2" class="affilCls" >Total</th>';
			str+='<th rowspan="2" class="affilCls"> Started </th>';
			str+='<th rowspan="2" class="affilCls"> Completed  </th>';
			str+='<th colspan="2"  class="affilCls" > Telugu Yuvatha  </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Mahila </th>';
			str+='<th colspan="2"  class="affilCls"> Telugu Rythu </th>';
			if(isConsiderAffl =='true')
			{				
				str+='<th colspan="2" class="hideCls"> Trade  </th>';
				str+='<th colspan="2" class="hideCls"> BC Cell </th>';
				str+='<th colspan="2" class="hideCls"> SC Cell </th>';
				str+='<th colspan="2" class="hideCls"> ST Cell </th>';
				str+='<th colspan="2" class="hideCls"> Minority Cell </th>';
				str+='<th colspan="2" class="hideCls"> Christian </th>';
				str+='<th colspan="2" class="hideCls"> TNSF </th>';
				str+='<th colspan="2" class="hideCls"> TNTUC </th>';
				str+='<th colspan="2" class="hideCls"> TSNV </th>';
				str+='<th colspan="2" class="hideCls"> Legal Cell </th>';
				str+='<th colspan="2" class="hideCls"> Doctor Cell </th>';
				str+='<th colspan="2" class="hideCls"> Kallu Geetha Karmikulu </th>';
				str+='<th colspan="2" class="hideCls"> Chenetha </th>';
				str+='<th colspan="2" class="hideCls"> Telugu Rakshana Vedika </th>';
				str+='<th colspan="2" class="hideCls"> TNUS   </th>';
				str+='<th colspan="2" class="hideCls"> Commercial Cell </th>';
				str+='<th colspan="2" class="hideCls"> Cultural Cell </th>';
			}
			else
			{
				str+='<th colspan="2"   class="affilCls"> Others </th>';
			}
			if(result[0].cadreIVRVO != null)
			{
				for(var pr in result[0].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[pr].id != 8){
					str+='<th rowspan="2">'+result[0].cadreIVRVO.optionsList[pr].name+'</th>';
					str+='<th rowspan="2">%</th>';
					}
				}
				/*
				for(var pr in result[0].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[pr].id != 13){
					str+='<th>'+result[0].cadreIVRVO.optionsList1[pr].name+'</th>';
					str+='<th>%</th>';
					}
				}
				*/
			}
			 str+='<tr>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			str+='<th class="affilCls">Started</th>';
			str+='<th class="affilCls">Completed</th>';
			
			if(isConsiderAffl == "true")
			{
				for(var z=0;z<17;z++)
				 {
					str+='<th  class="hideCls">Started</th>';
					str+='<th  class="hideCls">Completed</th>';
				 }
			}
			else
			{
				str+='<th class="affilCls">Started</th>';
				str+='<th class="affilCls">Completed</th>';
			}	
		}
		str+='<tbody>';
		
		for(var i in result){
			if(result[i].townMandalDivisionVO != null || result[i].villageWardVO != null || result[i].districtCommVO != null){
			str += '<tr id='+result[i].districtId+' class="removeCls clearCls'+result[i].districtId+'">';
			str+='<td class="removeCls clearClsTD'+result[i].districtId+'" style="text-align:center;">'+result[i].districtId+'';
			str+='</td>';
			if(districtCommCheck == "true" && mandalCheck == "false" && villageCheck == "false"){
            str += '<td style="color:#333333;font-weight:bold;">'+result[i].districtName+'&nbsp;&nbsp;<span style="cursor: pointer;" title="Click Here For '+result[i].districtName+' Committee Summary Report" onclick="getPopUpForSummaryForDistrict('+result[i].districtId+',\''+result[i].districtName+'\');" class="glyphicon glyphicon-dashboard"></span></td>';
			}else{
			 str += '<td ><a style="color:#333333;font-weight:bold;cursor:pointer;" onclick="getConstituencyWiseCommittesSummaryForSubLevel(\''+jObj.startDate+'\',\''+jObj.endDate+'\',\''+jObj.state+'\',\''+jObj.mandalCheck+'\',\''+jObj.villageCheck+'\',\''+result[i].districtId+'\',\''+result[i].districtName+'\');" style="cursor:pointer;">'+result[i].districtName+'</a>&nbsp;&nbsp;<span style="cursor: pointer;" title="Click Here For '+result[i].districtName+' Committee Summary Report" onclick="getPopUpForSummaryForDistrict('+result[i].districtId+',\''+result[i].districtName+'\');" class="glyphicon glyphicon-dashboard"></span></td>';


			}
            //str += '<td><a onclick="getConstituencyWiseCommittesSummaryForSubLevel(\''+jObj.startDate+'\',\''+jObj.endDate+'\',\''+jObj.state+'\',\''+jObj.mandalCheck+'\',\''+jObj.villageCheck+'\',\''+result[i].districtId+'\',\''+result[i].districtName+'\');" style="cursor:pointer;">'+result[i].districtName+'</a></td>';
			if(mandalCheck == "true"){
			
			if(result[i].townMandalDivisionVO!=null){
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.totalCommittees+'</td>';
					mandTotal=mandTotal+result[i].townMandalDivisionVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainStarted!=null){
					//str += '<td>'+result[i].townMandalDivisionVO.mainStarted+'<span id="mini-pie-chart-district'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainStarted+'</td>';
					mandStarted=mandStarted+result[i].townMandalDivisionVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mainCompleted+'</td>';
					mandCompleted=mandCompleted+result[i].townMandalDivisionVO.mainCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				//888
				if(result[i].townMandalDivisionVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.membersCount+'</td>';
					mandMembers=mandMembers+result[i].townMandalDivisionVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+((result[i].townMandalDivisionVO.totalCommittees)*20)+'</td>';					
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflStarted+'</td>';
					mandAfStarted=mandAfStarted+result[i].townMandalDivisionVO.afflStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.afflCompleted+'</td>';
					mandAfCompleted=mandAfCompleted+result[i].townMandalDivisionVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.youvathaStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.youvathaStarted+'</td>';
					yuvathaStartedCount = yuvathaStartedCount+result[i].townMandalDivisionVO.youvathaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.youvathaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.youvathaCmpltd+'</td>';
					yuvathaCompltdCount = yuvathaCompltdCount+result[i].townMandalDivisionVO.youvathaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mahilaStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mahilaStarted+'</td>';
					mahilaStartedCount = mahilaStartedCount + result[i].townMandalDivisionVO.mahilaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.mahilaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.mahilaCmpltd+'</td>';
					mahilaCompltdCount = mahilaCompltdCount+result[i].townMandalDivisionVO.mahilaCmpltd;					
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				
				if(result[i].townMandalDivisionVO.rythuStarted != null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rythuStarted+'</td>';
					rythuStartedCount = rythuStartedCount+result[i].townMandalDivisionVO.rythuStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].townMandalDivisionVO.rythuCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rythuCmpltd+'</td>';
					rythuCompltdCount = rythuCompltdCount+result[i].townMandalDivisionVO.rythuCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(isConsiderAffl == "true")
				{
						if(result[i].townMandalDivisionVO.tradeStarted != null){
							str += '<td style="text-align:center"  >'+result[i].townMandalDivisionVO.tradeStarted+' </td>';
							tradeStartedCount = tradeStartedCount+result[i].townMandalDivisionVO.tradeStarted;
						}else{
							str += '<td style="text-align:center"  > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tradeCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tradeCmpltd+'</td>';
							tradeCmpltdCount = tradeCmpltdCount+result[i].townMandalDivisionVO.tradeCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}

						if(result[i].townMandalDivisionVO.bcCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.bcCellStarted+' </td>';
							bcCellStartedCount = bcCellStartedCount+result[i].townMandalDivisionVO.bcCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.bcCellCmpltd!=null){
							str += '<td style="text-align:center">'+result[i].townMandalDivisionVO.bcCellCmpltd+'</td>';
							bcCellCmpltdCount = bcCellCmpltdCount+result[i].townMandalDivisionVO.bcCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.scCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.scCellStarted+'</td>';
							scCellStartedCount = scCellStartedCount+result[i].townMandalDivisionVO.scCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.scCellCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.scCellCmpltd+'</td>';
							scCellCmpltdCount = scCellCmpltdCount+result[i].townMandalDivisionVO.scCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.stCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.stCellStarted+'</td>';
							stCellStartedCount = stCellStartedCount+result[i].townMandalDivisionVO.stCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.stCellCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.stCellCmpltd+'</td>';
							stCellCmpltdCount = stCellCmpltdCount+result[i].townMandalDivisionVO.stCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.minorityStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.minorityStarted+'</td>';
							minorityStartedCount = minorityStartedCount+result[i].townMandalDivisionVO.minorityStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.minorityCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.minorityCmpltd+'</td>';
							minorityCmpltdCount = minorityCmpltdCount+result[i].townMandalDivisionVO.minorityCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.CristianStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.CristianStarted+' </td>';
							CristianStartedCount = CristianStartedCount+result[i].townMandalDivisionVO.CristianStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.CristianCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.CristianCmpltd+'</td>';
							CristianCmpltdCount = CristianCmpltdCount+result[i].townMandalDivisionVO.CristianCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnsfStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnsfStarted+'</td>';
							tnsfStartedCount = tnsfStartedCount+result[i].townMandalDivisionVO.tnsfStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnsfCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnsfCmpltd+'</td>';
							tnsfCmpltdCount = tnsfCmpltdCount+result[i].townMandalDivisionVO.tnsfCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tntucStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tntucStarted+'</td>';
							tntucStartedCount = tntucStartedCount+result[i].townMandalDivisionVO.tntucStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tntucCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tntucCmpltd+'</td>';
							tntucCmpltdCount = tntucCmpltdCount+result[i].townMandalDivisionVO.tntucCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tsnvStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tsnvStarted+'</td>';
							tsnvStartedCount = tsnvStartedCount+result[i].townMandalDivisionVO.tsnvStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tsnvCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tsnvCmpltd+'</td>';
							tsnvCmpltdCount = tsnvCmpltdCount+result[i].townMandalDivisionVO.tsnvCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.legalCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.legalCellStarted+' </td>';
							legalCellStartedCount = legalCellStartedCount+result[i].townMandalDivisionVO.legalCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.legalCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.legalCmpltd+'</td>';
							legalCmpltdCount = legalCmpltdCount+result[i].townMandalDivisionVO.legalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.doctorStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.doctorStarted+'<td>';
							doctorStartedCount = doctorStartedCount+result[i].townMandalDivisionVO.doctorStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.doctorCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.doctorCmpltd+'</td>';
							doctorCmpltdCount = doctorCmpltdCount+result[i].townMandalDivisionVO.doctorCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.kalluGeethaStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.kalluGeethaStarted+' </td>';
							kalluGeethaStartedCount = kalluGeethaStartedCount+result[i].townMandalDivisionVO.kalluGeethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.kalluGeethaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.kalluGeethaCmpltd+' </td>';
							kalluGeethaCmpltdCount = kalluGeethaCmpltdCount+result[i].townMandalDivisionVO.kalluGeethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.chenethaStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.chenethaStarted+'</td>';
							chenethaStartedCount = chenethaStartedCount+result[i].townMandalDivisionVO.chenethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.chenethaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.chenethaCmpltd+'</td>';
							chenethaCmpltdCount = chenethaCmpltdCount+result[i].townMandalDivisionVO.chenethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.rakshaVedikaStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rakshaVedikaStarted+' </td>';
							rakshaVedikaStartedCount = rakshaVedikaStartedCount+result[i].townMandalDivisionVO.rakshaVedikaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.rakshaVedikaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.rakshaVedikaCmpltd+' </td>';
							rakshaVedikaCmpltdCount = rakshaVedikaCmpltdCount+result[i].townMandalDivisionVO.rakshaVedikaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnusStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnusStarted+'</td>';
							tnusStartedCount = tnusStartedCount+result[i].townMandalDivisionVO.tnusStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.tnusCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.tnusCmpltd+'</td>';
							tnusCmpltdCount = tnusCmpltdCount+result[i].townMandalDivisionVO.tnusCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.commercialStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.commercialStarted+' </td>';
							commercialStartedCount = commercialStartedCount+result[i].townMandalDivisionVO.commercialStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.commercialCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.commercialCmpltd+'</td>';
							commercialCmpltdCount = commercialCmpltdCount+result[i].townMandalDivisionVO.commercialCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.culturalStarted != null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.culturalStarted+'</td>';
							culturalStartedCount = culturalStartedCount+result[i].townMandalDivisionVO.culturalStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].townMandalDivisionVO.culturalCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.culturalCmpltd+'</td>';
							culturalCmpltdCount = culturalCmpltdCount+result[i].townMandalDivisionVO.culturalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
				}
				else
				{
					if(result[i].townMandalDivisionVO.othersStarted != null){
						str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.othersStarted+'</td>';
						othersStartedCount = othersStartedCount+result[i].townMandalDivisionVO.othersStarted;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
					
					if(result[i].townMandalDivisionVO.othersCmpltd!=null){
						str += '<td style="text-align:center" >'+result[i].townMandalDivisionVO.othersCmpltd+'</td>';
						othersCompltdCount = othersCompltdCount+result[i].townMandalDivisionVO.othersCmpltd;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
				}
				
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str += '<td>  </td>';
						str += '<td>  </td>';
					 }
				}
				else
				{
					str += '<td>  </td>';
					str += '<td>  </td>';
				}			
			}
			}
			
			
			if(villageCheck == "true"){//444
			
			if(result[i].villageWardVO!=null){
				if(result[i].villageWardVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.totalCommittees+'</td>';
					panTotal=panTotal+result[i].villageWardVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				  
				if(result[i].villageWardVO.mainStarted!=null){
					//str += '<td>'+result[i].villageWardVO.mainStarted+'<span id="mini-pie-chart-village'+i+'" class="pull-right mini-pie-chart-village"></span></td>';
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mainStarted+'</td>';
					panStarted=panStarted+result[i].villageWardVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mainCompleted+'</td>';
					panCompleted=panCompleted+result[i].villageWardVO.mainCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.membersCount!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.membersCount+' </td>';
					panMembers=panMembers+result[i].villageWardVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+((result[i].villageWardVO.totalCommittees)*20)+'</td>';
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.afflStarted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.afflStarted+'</td>';
					panAffStarted=panAffStarted+result[i].villageWardVO.afflStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.afflCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.afflCompleted+'</td>';
					panAffCompleted=panAffCompleted+result[i].villageWardVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				   
				if(result[i].villageWardVO.youvathaStarted != null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.youvathaStarted+'</td>';
					panyuvathaStartedCount = panyuvathaStartedCount+result[i].villageWardVO.youvathaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.youvathaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.youvathaCmpltd+'</td>';
					panyuvathaCompltdCount = panyuvathaCompltdCount+result[i].villageWardVO.youvathaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.mahilaStarted != null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mahilaStarted+'</td>';
					panmahilaStartedCount = panmahilaStartedCount + result[i].villageWardVO.mahilaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.mahilaCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.mahilaCmpltd+'</td>';
					panmahilaCompltdCount = panmahilaCompltdCount+result[i].villageWardVO.mahilaCmpltd;					
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				
				if(result[i].villageWardVO.rythuStarted != null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.rythuStarted+'</td>';
					panrythuStartedCount = panrythuStartedCount+result[i].villageWardVO.rythuStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].villageWardVO.rythuCmpltd!=null){
					str += '<td style="text-align:center" >'+result[i].villageWardVO.rythuCmpltd+'</td>';
					panrythuCompltdCount = panrythuCompltdCount+result[i].villageWardVO.rythuCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(isConsiderAffl == "true")
				{
						if(result[i].villageWardVO.tradeStarted != null){
							str += '<td style="text-align:center"  >'+result[i].villageWardVO.tradeStarted+'</td>';
							pantradeStartedCount = pantradeStartedCount+result[i].villageWardVO.tradeStarted;
						}else{
							str += '<td style="text-align:center"  > - </td>';
						}
						
						if(result[i].villageWardVO.tradeCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tradeCmpltd+'</td>';
							pantradeCmpltdCount = pantradeCmpltdCount+result[i].villageWardVO.tradeCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}

						if(result[i].villageWardVO.bcCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.bcCellStarted+'</td>';
							panbcCellStartedCount = panbcCellStartedCount+result[i].villageWardVO.bcCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.bcCellCmpltd!=null){
							str += '<td style="text-align:center">'+result[i].villageWardVO.bcCellCmpltd+'</td>';
							panbcCellCmpltdCount = panbcCellCmpltdCount+result[i].villageWardVO.bcCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.scCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.scCellStarted+'</td>';
							panscCellStartedCount = panscCellStartedCount+result[i].villageWardVO.scCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.scCellCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.scCellCmpltd+'</td>';
							panscCellCmpltdCount = panscCellCmpltdCount+result[i].villageWardVO.scCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.stCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.stCellStarted+'</td>';
							panstCellStartedCount = panstCellStartedCount+result[i].villageWardVO.stCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.stCellCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.stCellCmpltd+'</td>';
							panstCellCmpltdCount = panstCellCmpltdCount+result[i].villageWardVO.stCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.minorityStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.minorityStarted+'</td>';
							panminorityStartedCount = panminorityStartedCount+result[i].villageWardVO.minorityStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.minorityCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.minorityCmpltd+'</td>';
							panminorityCmpltdCount = panminorityCmpltdCount+result[i].villageWardVO.minorityCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.CristianStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.CristianStarted+'</td>';
							panCristianStartedCount = panCristianStartedCount+result[i].villageWardVO.CristianStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.CristianCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.CristianCmpltd+'</td>';
							CristianCmpltdCount = CristianCmpltdCount+result[i].villageWardVO.CristianCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tnsfStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tnsfStarted+'</td>';
							pantnsfStartedCount = pantnsfStartedCount+result[i].villageWardVO.tnsfStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tnsfCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tnsfCmpltd+'</td>';
							pantnsfCmpltdCount = pantnsfCmpltdCount+result[i].villageWardVO.tnsfCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tntucStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tntucStarted+'</td>';
							pantntucStartedCount = pantntucStartedCount+result[i].villageWardVO.tntucStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tntucCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tntucCmpltd+'</td>';
							pantntucCmpltdCount = pantntucCmpltdCount+result[i].villageWardVO.tntucCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tsnvStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tsnvStarted+'</td>';
							pantsnvStartedCount = pantsnvStartedCount+result[i].villageWardVO.tsnvStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tsnvCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tsnvCmpltd+'</td>';
							pantsnvCmpltdCount = pantsnvCmpltdCount+result[i].villageWardVO.tsnvCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.legalCellStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.legalCellStarted+'</td>';
							panlegalCellStartedCount = panlegalCellStartedCount+result[i].villageWardVO.legalCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.legalCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.legalCmpltd+'</td>';
							panlegalCmpltdCount = panlegalCmpltdCount+result[i].villageWardVO.legalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.doctorStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.doctorStarted+'</td>';
							pandoctorStartedCount = pandoctorStartedCount+result[i].villageWardVO.doctorStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.doctorCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.doctorCmpltd+'</td>';
							pandoctorCmpltdCount = pandoctorCmpltdCount+result[i].villageWardVO.doctorCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.kalluGeethaStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.kalluGeethaStarted+'</td>';
							pankalluGeethaStartedCount = pankalluGeethaStartedCount+result[i].villageWardVO.kalluGeethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.kalluGeethaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.kalluGeethaCmpltd+'</td>';
							pankalluGeethaCmpltdCount = pankalluGeethaCmpltdCount+result[i].villageWardVO.kalluGeethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.chenethaStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.chenethaStarted+'</td>';
							panchenethaStartedCount = panchenethaStartedCount+result[i].villageWardVO.chenethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.chenethaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.chenethaCmpltd+'</td>';
							panchenethaCmpltdCount = panchenethaCmpltdCount+result[i].villageWardVO.chenethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.rakshaVedikaStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.rakshaVedikaStarted+'</td>';
							panrakshaVedikaStartedCount = panrakshaVedikaStartedCount+result[i].villageWardVO.rakshaVedikaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.rakshaVedikaCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.rakshaVedikaCmpltd+'</td>';
							panrakshaVedikaCmpltdCount = panrakshaVedikaCmpltdCount+result[i].villageWardVO.rakshaVedikaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tnusStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tnusStarted+'</td>';
							pantnusStartedCount = pantnusStartedCount+result[i].villageWardVO.tnusStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.tnusCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.tnusCmpltd+'</td>';
							pantnusCmpltdCount = pantnusCmpltdCount+result[i].villageWardVO.tnusCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.commercialStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.commercialStarted+'</td>';
							pancommercialStartedCount = pancommercialStartedCount+result[i].villageWardVO.commercialStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.commercialCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.commercialCmpltd+'</td>';
							pancommercialCmpltdCount = pancommercialCmpltdCount+result[i].villageWardVO.commercialCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.culturalStarted != null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.culturalStarted+'</td>';
							panculturalStartedCount = panculturalStartedCount+result[i].villageWardVO.culturalStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].villageWardVO.culturalCmpltd!=null){
							str += '<td style="text-align:center" >'+result[i].villageWardVO.culturalCmpltd+'</td>';
							panculturalCmpltdCount = panculturalCmpltdCount+result[i].villageWardVO.culturalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
				}
				else
				{
					if(result[i].villageWardVO.othersStarted != null){
						str += '<td style="text-align:center" >'+result[i].villageWardVO.othersStarted+'</td>';
						panothersStartedCount = panothersStartedCount+result[i].villageWardVO.othersStarted;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
					
					if(result[i].villageWardVO.othersCmpltd!=null){
						str += '<td style="text-align:center" >'+result[i].villageWardVO.othersCmpltd+'</td>';
						panothersCompltdCount = panothersCompltdCount+result[i].villageWardVO.othersCmpltd;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
				}
				
			}else{
				
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str += '<td>  </td>';
						str += '<td>  </td>';
					 }
				}
				else
				{
					str += '<td>  </td>';
					str += '<td>  </td>';
				}			
			
			}
			}
			
			if(result[i].cadreIVRVO != null)
			{
			
				for(var tp in result[i].cadreIVRVO.optionsList)
				{
					if(result[0].cadreIVRVO.optionsList[tp].id != 8){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}
				}
				
				/*
				for(var tp in result[i].cadreIVRVO.optionsList1)
				{
					if(result[0].cadreIVRVO.optionsList1[tp].id != 13){
					if(result[i].cadreIVRVO.total >0){
					 percentage = (result[i].cadreIVRVO.optionsList1[tp].count *100)/ result[i].cadreIVRVO.total;
					 perc = percentage.toFixed(0);
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">'+perc+'</td>';
					}				
					else{
					str+='<td style="text-align:center">'+result[i].cadreIVRVO.optionsList1[tp].count+'</td>';
					str+='<td style="text-align:center">0</td>';
					}
					}
				}
				*/
			}
			
			if(districtCommCheck == "true"){
			
			$("#performanceId").show();
			
			if(result[i].districtCommVO != null){
				if(result[i].districtCommVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.totalCommittees+'</td>';
					distTotal=distTotal+result[i].districtCommVO.totalCommittees;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mainStarted!=null){
					//str += '<td>'+result[i].districtCommVO.mainStarted+'<span id="mini-pie-chart-district'+i+'" class="pull-right mini-pie-chart-district"></span></td>';
					str += '<td style="text-align:center" >'+result[i].districtCommVO.mainStarted+'</td>';
					distStarted=distStarted+result[i].districtCommVO.mainStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mainCompleted!=null){
					str += '<td style="text-align:center" >'+result[i].districtCommVO.mainCompleted+'</td>';
					distCompleted=distCompleted+result[i].districtCommVO.mainCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.membersCount!=null){
					str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Main Committee \',1,11);}">'+result[i].districtCommVO.membersCount+'</a></td>';
					distMembers=distMembers+result[i].districtCommVO.membersCount;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				if(result[i].districtCommVO.totalCommittees!=null){
					str += '<td style="text-align:center" >'+((result[i].districtCommVO.totalCommittees)*20)+'</td>';					
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				if(result[i].districtCommVO.afflStarted!=null){
					str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Affiliated Committee \',0,11);}">'+result[i].districtCommVO.afflStarted+'</a></td>';
					distAffStarted=distAffStarted+result[i].districtCommVO.afflStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.afflCompleted!=null){
					str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Affiliated Committee \',0,11);}">'+result[i].districtCommVO.afflCompleted+' </a></td>';
					distAffCompleted=distAffCompleted+result[i].districtCommVO.afflCompleted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.youvathaStarted != null){
					str += '<td style="text-align:center" > <a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Yuvatha Committee \',2,11);}">'+result[i].districtCommVO.youvathaStarted+'</a> </td>';
					dstctyuvathaStartedCount = dstctyuvathaStartedCount+result[i].districtCommVO.youvathaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.youvathaCmpltd!=null){
					str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Yuvatha Committee \',2,11);}">'+result[i].districtCommVO.youvathaCmpltd+'</a> </td>';
					dstctyuvathaCompltdCount = dstctyuvathaCompltdCount+result[i].districtCommVO.youvathaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mahilaStarted != null){
					str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Mahila Committee \',3,11);}">'+result[i].districtCommVO.mahilaStarted+' </a></td>';
					dstctmahilaStartedCount = dstctmahilaStartedCount+result[i].districtCommVO.mahilaStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.mahilaCmpltd!=null){
					str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Mahila Committee \',3,11);}">'+result[i].districtCommVO.mahilaCmpltd+'</a> </td>';
					dstctmahilaCompltdCount = dstctmahilaCompltdCount+result[i].districtCommVO.mahilaCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				
				if(result[i].districtCommVO.rythuStarted != null){
					str += '<td style="text-align:center;"> <a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Rythu Committee \',4,11);}">'+result[i].districtCommVO.rythuStarted+'</a> </td>';
						dstctrythuStartedCount = dstctrythuStartedCount+result[i].districtCommVO.rythuStarted;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(result[i].districtCommVO.rythuCmpltd!=null){
					str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Rythu Committee \',4,11);}">'+result[i].districtCommVO.rythuCmpltd+' </a></td>';
						dstctrythuCompltdCount = dstctrythuCompltdCount+result[i].districtCommVO.rythuCmpltd;
				}else{
					str += '<td style="text-align:center" > - </td>';
				}
				
				if(isConsiderAffl == "true")
				{
						if(result[i].districtCommVO.tradeStarted != null){
							str += '<td style="text-align:center"  ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Trade Committee \',17,11);}">'+result[i].districtCommVO.tradeStarted+'</a> </td>';
							dstcttradeStartedCount = dstcttradeStartedCount+result[i].districtCommVO.tradeStarted;
						}else{
							str += '<td style="text-align:center"  > - </td>';
						}
						
						if(result[i].districtCommVO.tradeCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Trade Committee \',17,11);}">'+result[i].districtCommVO.tradeCmpltd+'</a> </td>';
							dstcttradeCmpltdCount = dstcttradeCmpltdCount+result[i].districtCommVO.tradeCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}

						if(result[i].districtCommVO.bcCellStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' BC Cell Committee \',6,11);}">'+result[i].districtCommVO.bcCellStarted+'</a> </td>';
							dstctbcCellStartedCount = dstctbcCellStartedCount+result[i].districtCommVO.bcCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.bcCellCmpltd!=null){
							str += '<td style="text-align:center"><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' BC Cell Committee \',6,11);}">'+result[i].districtCommVO.bcCellCmpltd+' </a></td>';
							dstctbcCellCmpltdCount = dstctbcCellCmpltdCount+result[i].districtCommVO.bcCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.scCellStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' SC Cell Committee \',7,11);}">'+result[i].districtCommVO.scCellStarted+' </a></td>';
							dstctscCellStartedCount = dstctscCellStartedCount+result[i].districtCommVO.scCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.scCellCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' SC Cell Committee \',7,11);}">'+result[i].districtCommVO.scCellCmpltd+' </a></td>';
							dstctscCellCmpltdCount = dstctscCellCmpltdCount+result[i].districtCommVO.scCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.stCellStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' ST Cell Committee \',8,11);}">'+result[i].districtCommVO.stCellStarted+'</a> </td>';
							dstctstCellStartedCount = dstctstCellStartedCount+result[i].districtCommVO.stCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.stCellCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' ST Cell Committee \',8,11);}">'+result[i].districtCommVO.stCellCmpltd+'</a> </td>';
							dstctstCellCmpltdCount = dstctstCellCmpltdCount+result[i].districtCommVO.stCellCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.minorityStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Minority Cell Committee \',9,11);}">'+result[i].districtCommVO.minorityStarted+' </a></td>';
							dstctminorityStartedCount = dstctminorityStartedCount+result[i].districtCommVO.minorityStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.minorityCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Minority Cell Committee \',9,11);}">'+result[i].districtCommVO.minorityCmpltd+'</a> </td>';
							dstctminorityCmpltdCount = dstctminorityCmpltdCount+result[i].districtCommVO.minorityCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.CristianStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Christian Committee \',18,11);}">'+result[i].districtCommVO.CristianStarted+'</a> </td>';
							dstctCristianStartedCount = dstctCristianStartedCount+result[i].districtCommVO.CristianStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.CristianCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Christian Committee \',18,11);}">'+result[i].districtCommVO.CristianCmpltd+' </a></td>';
							dstctCristianCmpltdCount = dstctCristianCmpltdCount+result[i].districtCommVO.CristianCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tnsfStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TNSF (Student Union) Committee \',11,11);}">'+result[i].districtCommVO.tnsfStarted+' </a></td>';
							dstcttnsfStartedCount = dstcttnsfStartedCount+result[i].districtCommVO.tnsfStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tnsfCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TNSF (Student Union) Committee \',11,11);}">'+result[i].districtCommVO.tnsfCmpltd+'</a> </td>';
							dstcttnsfCmpltdCount = dstcttnsfCmpltdCount+result[i].districtCommVO.tnsfCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tntucStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TNTUC Committee \',5,11);}">'+result[i].districtCommVO.tntucStarted+' </a></td>';
							dstcttntucStartedCount = dstcttntucStartedCount+result[i].districtCommVO.tntucStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tntucCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TNTUC Committee \',5,11);}">'+result[i].districtCommVO.tntucCmpltd+'</a> </td>';
							dstcttntucCmpltdCount = dstcttntucCmpltdCount+result[i].districtCommVO.tntucCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tsnvStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TSNV (Technical Expert Cell) Committee \',15,11);}">'+result[i].districtCommVO.tsnvStarted+'</a> </td>';
							dstcttsnvStartedCount = dstcttsnvStartedCount+result[i].districtCommVO.tsnvStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tsnvCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TSNV (Technical Expert Cell) Committee \',15,11);}">'+result[i].districtCommVO.tsnvCmpltd+'</a> </td>';
							dstcttsnvCmpltdCount = dstcttsnvCmpltdCount+result[i].districtCommVO.tsnvCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.legalCellStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Legal Cell Committee \',10,11);}">'+result[i].districtCommVO.legalCellStarted+' </a></td>';
							dstctlegalCellStartedCount = dstctlegalCellStartedCount+result[i].districtCommVO.legalCellStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.legalCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Legal Cell Committee \',10,11);}">'+result[i].districtCommVO.legalCmpltd+'</a> </td>';
							dstctlegalCmpltdCount = dstctlegalCmpltdCount+result[i].districtCommVO.legalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.doctorStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Doctor Cell Committee \',16,11);}">'+result[i].districtCommVO.doctorStarted+' </a></td>';
							dstctdoctorStartedCount = dstctdoctorStartedCount+result[i].districtCommVO.doctorStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.doctorCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Doctor Cell Committee \',16,11);}">'+result[i].districtCommVO.doctorCmpltd+'</a> </td>';
							dstctdoctorCmpltdCount = dstctdoctorCmpltdCount+result[i].districtCommVO.doctorCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.kalluGeethaStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Kallu Geetha Karmikulu Committee \',20,11);}">'+result[i].districtCommVO.kalluGeethaStarted+'</a> </td>';
							dstctkalluGeethaStartedCount = dstctkalluGeethaStartedCount+result[i].districtCommVO.kalluGeethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.kalluGeethaCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Kallu Geetha Karmikulu Committee \',20,11);}">'+result[i].districtCommVO.kalluGeethaCmpltd+' </a></td>';
							dstctkalluGeethaCmpltdCount = dstctkalluGeethaCmpltdCount+result[i].districtCommVO.kalluGeethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.chenethaStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Chenetha Committee \',21,11);}">'+result[i].districtCommVO.chenethaStarted+' </a></td>';
							dstctchenethaStartedCount = dstctchenethaStartedCount+result[i].districtCommVO.chenethaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.chenethaCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Chenetha Committee \',21,11);}">'+result[i].districtCommVO.chenethaCmpltd+' </a></td>';
							dstctchenethaCmpltdCount = dstctchenethaCmpltdCount+result[i].districtCommVO.chenethaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.rakshaVedikaStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Rakshana Vedika Committee \',19,11);}">'+result[i].districtCommVO.rakshaVedikaStarted+' </a></td>';
							dstctrakshaVedikaStartedCount = dstctrakshaVedikaStartedCount+result[i].districtCommVO.rakshaVedikaStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.rakshaVedikaCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Telugu Rakshana Vedika Committee \',19,11);}">'+result[i].districtCommVO.rakshaVedikaCmpltd+' </a></td>';
							dstctrakshaVedikaCmpltdCount = dstctrakshaVedikaCmpltdCount+result[i].districtCommVO.rakshaVedikaCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tnusStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TNUS ( Teachers Union)  Committee \',14,11);}">'+result[i].districtCommVO.tnusStarted+' </a></td>';
							dstcttnusStartedCount = dstcttnusStartedCount+result[i].districtCommVO.tnusStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.tnusCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' TNUS ( Teachers Union)  Committee \',14,11);}">'+result[i].districtCommVO.tnusCmpltd+'</a> </td>';
							dstcttnusCmpltdCount = dstcttnusCmpltdCount+result[i].districtCommVO.tnusCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.commercialStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Commercial Cell Committee \',12,11);}">'+result[i].districtCommVO.commercialStarted+' </a></td>';
							dstctcommercialStartedCount = dstctcommercialStartedCount+result[i].districtCommVO.commercialStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.commercialCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Commercial Cell Committee \',12,11);}">'+result[i].districtCommVO.commercialCmpltd+'</a> </td>';
							dstctcommercialCmpltdCount = dstctcommercialCmpltdCount+result[i].districtCommVO.commercialCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.culturalStarted != null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Cultural Cell Committee \',13,11);}">'+result[i].districtCommVO.culturalStarted+' </a></td>';
							dstctculturalStartedCount = dstctculturalStartedCount+result[i].districtCommVO.culturalStarted;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
						
						if(result[i].districtCommVO.culturalCmpltd!=null){
							str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Cultural Cell Committee \',13,11);}">'+result[i].districtCommVO.culturalCmpltd+'</a></td>';
							dstctculturalCmpltdCount = dstctculturalCmpltdCount+result[i].districtCommVO.culturalCmpltd;
						}else{
							str += '<td style="text-align:center" > - </td>';
						}
				}
				else
				{
					if(result[i].districtCommVO.othersStarted != null){
						str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Affiliated Committee \',0,11);}">'+result[i].districtCommVO.othersStarted+'</a> </td>';
						dstctothersStartedCount = dstctothersStartedCount+result[i].districtCommVO.othersStarted;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
					
					if(result[i].districtCommVO.othersCmpltd != null){
						str += '<td style="text-align:center" ><a href="javascript:{gettingCadreDetails('+result[i].districtId+',\''+result[i].districtName+' District\',\' Affiliated Committee \',0,11);}">'+result[i].districtCommVO.othersCmpltd+'</a> </td>';
						dstctothersCompltdCount = dstctothersCompltdCount+result[i].districtCommVO.othersCmpltd;
					}else{
						str += '<td style="text-align:center" > - </td>';
					}
				}
			}else{
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				str += '<td>  </td>';
				if(isConsiderAffl == "true")
				{
					for(var z=0;z<17;z++)
					 {
						str += '<td>  </td>';
						str += '<td>  </td>';
					 }
				}
				else
				{
					str += '<td>  </td>';
					str += '<td>  </td>';
				}
			}
			}
			
			
			str += '</tr>';
			
			if(result[i].townMandalDivisionVO != null){
				var details = [];
				if(result[i].townMandalDivisionVO.mainStarted != null){
					details = [result[i].townMandalDivisionVO.totalCommittees, result[i].townMandalDivisionVO.mainStarted];
				}else{
					details = [result[i].townMandalDivisionVO.totalCommittees, 0];
				}
				
				districtInfoArr.push(details);
			}else{
				 var details = [0, 0];
				districtInfoArr.push(details);
			}
			
			
			if(result[i].villageWardVO != null){
				var details = [];
				if(result[i].villageWardVO.mainStarted != null){
					details = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				}else{
					details = [result[i].villageWardVO.totalCommittees, 0];
				}
				//var villageDetails  = [result[i].villageWardVO.totalCommittees, result[i].villageWardVO.mainStarted];
				villageInfoArr.push(details);
			}else{
				var villageDetails  = [0, 0];
				villageInfoArr.push(villageDetails);
			}
			
			}
		}
		str += '</tbody><tfoot><tr class="no-sort" style="font-weight:bold;">';
		if(mandalCheck == "true" && villageCheck == "true" && districtCommCheck == "true"){
		str	+= '<td style="text-align:center"></td>';
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>';
		str += '<td style="text-align:center" >'+(mandTotal * 20 )+'</td>';		
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>';

		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
		
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+tradeStartedCount+'</td>'; 
				str += '<td style="text-align:center" >'+tradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+stCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+stCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+tnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+legalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+legalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+chenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+chenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+commercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+commercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+othersCompltdCount+'</td>';
			}
//str	+= '<td style="text-align:center"></td>';
//		str	+= '<td style="text-align:center">TOTAL</td><td >'+panTotal+'</td>'; 			
		str	+= '<td style="text-align:center">'+panTotal+'</td>'; 			
		str += '<td style="text-align:center" >'+panStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+panMembers+'</td>'; 
		str	+= '<td style="text-align:center">'+(panTotal * 20)+'</td>';		
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+panyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+pantradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+pantnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+panchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+pancommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+pancommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+panothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+panothersCompltdCount+'</td>';
			}
		
		//str	+= '<td style="text-align:center" colspan="8"></td>'; 			
		str	+= '<td style="text-align:center">'+distTotal+'</td>'; 			
		str += '<td style="text-align:center">'+distStarted+'</td>'; 	
		str += '<td style="text-align:center">'+distCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+distMembers+'</td>'; 
		str += '<td style="text-align:center">'+(distTotal * 20 )+'</td>';		
		str += '<td style="text-align:center">'+distAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+distAffCompleted+'</td>';
		
		
		str += '<td style="text-align:center" >'+dstctyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuCompltdCount+'</td>';
		
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+dstcttradeStartedCount+'</td>'; 
				str += '<td style="text-align:center" >'+dstcttradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstcttnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstctchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctcommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctcommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+dstctothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+dstctothersCompltdCount+'</td>';
			}
		
		}
		else if((mandalCheck == "true" && villageCheck == "true") || (districtCommCheck == "true" && villageCheck == "true")  || (districtCommCheck == "true" && mandalCheck == "true")){
		if(mandalCheck == "true" && villageCheck == "true"){
		str	+= '<td style="text-align:center"></td>';
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 	
		str += '<td style="text-align:center" >'+(mandTotal * 20 )+'</td>';	
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+tradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+stCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+stCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+tnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+legalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+legalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+chenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+chenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+commercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+commercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+othersCompltdCount+'</td>';
			}
		
//str	+= '<td style="text-align:center"></td>';
		//str	+= '<td style="text-align:center">TOTAL</td><td >'+panTotal+'</td>'; 			
		str	+= '<td style="text-align:center">'+panTotal+'</td>'; 			
		str += '<td style="text-align:center" >'+panStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+panMembers+'</td>'; 
		str	+= '<td style="text-align:center">'+(panTotal * 20)+'</td>';		
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+panyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+pantradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+pantnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+panchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+pancommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+pancommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+panothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+panothersCompltdCount+'</td>';
			}
		
		}else if(districtCommCheck == "true" && villageCheck == "true"){
		str	+= '<td style="text-align:center"></td>';
		str += '<td style="text-align:center">TOTAL</td><td style="text-align:center">'+panTotal+'</td>'; 	
 			
		str += '<td style="text-align:center" >'+panStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+panMembers+'</td>'; 
		str	+= '<td style="text-align:center">'+(panTotal * 20)+'</td>';		
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+panyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+pantradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+pantnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+panchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+pancommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+pancommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+panothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+panothersCompltdCount+'</td>';
			}
		
		//str	+= '<td style="text-align:center" colspan="8"></td>';
		str	+= '<td style="text-align:center" >'+distTotal+'</td>'; 			
		str += '<td style="text-align:center" >'+distStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+distCompleted+'</td>'; 	
		str += '<td style="text-align:center">'+distMembers+'</td>'; 
		str += '<td style="text-align:center">'+(distTotal * 20 )+'</td>';			
		str += '<td style="text-align:center">'+distAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+distAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+dstctyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuCompltdCount+'</td>';
		
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+dstcttradeStartedCount+'</td>'; 
				str += '<td style="text-align:center" >'+dstcttradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstcttnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstctchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctcommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctcommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+dstctothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+dstctothersCompltdCount+'</td>';
			}
			
		}else  if(districtCommCheck == "true" && mandalCheck == "true"){
			str	+= '<td style="text-align:center"></td>';
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 
		str += '<td style="text-align:center" >'+(mandTotal * 20 )+'</td>';		
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+tradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+stCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+stCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+tnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+legalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+legalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+chenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+chenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+commercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+commercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+othersCompltdCount+'</td>';
			}
		
		str	+= '<td style="text-align:center" >'+distTotal+'</td>'; 			
		str += '<td style="text-align:center" >'+distStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+distCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+distMembers+'</td>'; 
		str += '<td style="text-align:center">'+(distTotal * 20 )+'</td>';		
		str += '<td style="text-align:center">'+distAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+distAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+dstctyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuCompltdCount+'</td>';
		
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+dstcttradeStartedCount+'</td>'; 
				str += '<td style="text-align:center" >'+dstcttradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstcttnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstctchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctcommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctcommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+dstctothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+dstctothersCompltdCount+'</td>';
			}
		}
		
		}
		
		else if(mandalCheck=="true"){
		str	+= '<td style="text-align:center"></td>';
		str	+= '<td style="text-align:center">TOTAL</td><td >'+mandTotal+'</td>'; 	
		str += '<td style="text-align:center" >'+mandStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandMembers+'</td>'; 
		str += '<td style="text-align:center" >'+(mandTotal * 20 )+'</td>';		
		str += '<td style="text-align:center" >'+mandAfStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+mandAfCompleted+'</td>'; 

		str += '<td style="text-align:center" >'+yuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+yuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+mahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+rythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+rythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+tradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+bcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+scCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+stCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+stCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+minorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+CristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+tnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+legalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+legalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+doctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+kalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+chenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+chenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+rakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+tnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+commercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+commercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+culturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+othersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+othersCompltdCount+'</td>';
			}
		
		}
		else if(villageCheck=="true"){
		str	+= '<td style="text-align:center"></td>';
		str	+= '<td style="text-align:center">TOTAL</td><td >'+panTotal+'</td>'; 			
		str += '<td style="text-align:center" >'+panStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+panCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+panMembers+'</td>'; 
		str	+= '<td style="text-align:center">'+(panTotal * 20)+'</td>';		
		str += '<td style="text-align:center">'+panAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+panAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+panyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+panrythuCompltdCount+'</td>';
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+pantradeStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+pantnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+panlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+panlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pandoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pankalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+panchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+pantnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+pancommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+pancommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+panculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+panothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+panothersCompltdCount+'</td>';
			}
		
		}
		else if(districtCommCheck=="true"){
		str	+= '<td style="text-align:center"></td>';
		str	+= '<td style="text-align:center">TOTAL</td><td >'+distTotal+'</td>'; 			
		str += '<td style="text-align:center" >'+distStarted+'</td>'; 	
		str += '<td style="text-align:center" >'+distCompleted+'</td>'; 	
		str += '<td style="text-align:center" >'+distMembers+'</td>'; 
		str += '<td style="text-align:center">'+(distTotal * 20 )+'</td>';			
		str += '<td style="text-align:center">'+distAffStarted+'</td>'; 	
		str += '<td style="text-align:center">'+distAffCompleted+'</td>';
		
		str += '<td style="text-align:center" >'+dstctyuvathaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctyuvathaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctmahilaCompltdCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuStartedCount+'</td>';
		str += '<td style="text-align:center" >'+dstctrythuCompltdCount+'</td>';
		
		if(isConsiderAffl == "true")
			{
				str += '<td style="text-align:center" >'+dstcttradeStartedCount+'</td>'; 
				str += '<td style="text-align:center" >'+dstcttradeCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctbcCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctscCellCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctstCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctstCellCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctminorityCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctCristianCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstcttnsfStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnsfCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttntucCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttsnvCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctlegalCellStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctlegalCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctdoctorCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctkalluGeethaCmpltdCount+'</td>';
				
				str += '<td style="text-align:center" >'+dstctchenethaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctchenethaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctrakshaVedikaCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstcttnusCmpltdCount+'</td>';
				str	+= '<td style="text-align:center" >'+dstctcommercialStartedCount+'</td>'; 			
				str += '<td style="text-align:center" >'+dstctcommercialCmpltdCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalStartedCount+'</td>'; 	
				str += '<td style="text-align:center" >'+dstctculturalCmpltdCount+'</td>'; 
			}
			else
			{
				str += '<td style="text-align:center" >'+dstctothersStartedCount+'</td>';
				str += '<td style="text-align:center" >'+dstctothersCompltdCount+'</td>';
			}
		}
			
	    
		str += '</tr></tfoot></table>';		

	
		$("#distSummaryBody").html(str);
		$(".excelId").show();
		$("#districtTableId").dataTable({
			"iDisplayLength": 50,
			"aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]]
		});
		
		if($('.mini-pie-chart-district').length > 0 ) {
			var visitData = districtInfoArr;
			var params = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			
			var vdata = villageInfoArr;
			var params1 = {
				type: "pie",
				 sliceColors: ["#0B3B0B", "#B18904"]

			}
			
			
			for(var t in result){
				$('#mini-pie-chart-district'+t+'').sparkline(visitData[t], params);
				$('#mini-pie-chart-village'+t+'').sparkline(vdata[t], params1);
			}
		}
		
		
			
	}
	