
function getDistrictsForStates(state,id,num){
	
	$('#districtIdImg').show();
	if(id == "statesDivId"){
			$("#detsRptdistrictId").empty();
			$("#detsRptConstituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();			
			$("#detsRptdistrictId").append('<option value="0">Select District</option>');		
			$("#detsRptConstituencyId").append('<option value="0">Select Constituency</option>');		
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
			
			$("#detsRptdistrictId").trigger("chosen:updated");
			$("#detsRptConstituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
			
			 
	}
	//document.getElementById('membershipId').checked = true;
	
   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsListForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
		//$("#searchDataImgForDist").hide();
	     //$("#districtId").append('<option value="-1">Please Select District</option>');
		 $("#detsRptdistrictId").find('option').remove();
     for(var i in result){
		 $("#statesDivIdImg").hide();
		 if(id == "statesDivId"){
			// if(i>0){
				  if(result[i].id == 0){
				  $("#detsRptdistrictId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				   if(result[i].id != 517)
				  $("#detsRptdistrictId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				   
			   }
			// }
		 }
	 }
	 if(id == "statesDivId"){
			$("#detsRptdistrictId").trigger("chosen:updated");
	 }
   });
  }
  
  function getConstituenciesForDistricts(district,id,num){
	
	 //debugger;
	 if(id == "detsRptdistrictId"){
		 //hideDetails();
		 $("#constituencyIdImg").show();
			$("#searchDataImgForConst").show();
			//refreshExistingDetails();
			$("#detsRptConstituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();					
			$("#detsRptConstituencyId").append('<option value="0">Select Constituency</option>');		
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#panchaytList").append('<option value="0">Select Village/ward</option>');			
			$("#detsRptConstituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
			
	 } else if(id =="popUpdistrictId"){
		 $("#popUpConstiesId").empty();
		 //$("#popUpConstiesId").append('<option value="0">Select Constituency</option>');
		 $("#popUpConstiesId").trigger("chosen:updated");
	 }/* else if(id == "distUsrdistrictId"){
		 $("#assblyConstituencyId").empty();
		 //$("#popUpConstiesId").append('<option value="0">Select Constituency</option>');
		 $("#assblyConstituencyId").trigger("chosen:updated");
	 } */
	 
	/* if(district == 0){
		if(id == "districtId")
			getConstituenciesForState($('#statesDivId').val(),'constituencyId');
		/*else if(id == "notCadreDistId")
			getConstituenciesForState($('#notCadreStateId').val(),'notCadreConstId');
		else if(id == "changedistrictId")
			getConstituenciesForState($('#changestateId').val(),'changeConstiId');
		else
			getConstituenciesForState($('#nominatedStaeId').val(),'nominatdConstId');		
		return;
	} */
	
	//document.getElementById('membershipId').checked = true;
	
	var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   if(id == "detsRptdistrictId"){
		    $("#constituencyIdImg").hide();
			$("#detsRptConstituencyId").empty();
	   }
	   $("#searchDataImgForConst").hide();
	    //$("#constituencyId").append('<option value="-1">Please Select Constituency</option>');
		$("#detsRptConstituencyId").find('option').remove();
     for(var i in result){
		 if(id == "detsRptdistrictId"){
		   if(result[i].id == 0){
			  $("#detsRptConstituencyId").append('<option value='+result[i].id+'>ALL</option>');
		   }else{
			  $("#detsRptConstituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }else if(id == "popUpdistrictId"){
			if(result[i].id == 0){
			  $("#popUpConstiesId").append('<option value='+result[i].id+'>ALL</option>');
		   }else{
			  $("#popUpConstiesId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   } 
		 } else if(id == "distUsrdistrictId"){
			if(result[i].id == 0){
			  $("#detsRptConstituencyId").append('<option value='+result[i].id+'>ALL</option>');
		   }else{
			  $("#detsRptConstituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   } 
		 } 
	 }
	 if(id == "detsRptdistrictId"){
		$("#detsRptConstituencyId").trigger("chosen:updated");
	 } else if(id =="popUpdistrictId"){
		 $("#popUpConstiesId").trigger("chosen:updated");
	 } else if(id == "distUsrdistrictId"){
		 $("#detsRptConstituencyId").trigger("chosen:updated");
	 }
   });
  }
  
  function getMandalCorporationsByConstituency(num,id)
	{	
	
	var constituencyId  =0;
	if(id == "detsRptConstituencyId"){
		//hideDetails();
		$("#mandalListImg").show();
			$("#searchDataImgForMandl").show();
			$("#mandalList").empty();
			//refreshExistingDetails();
			constituencyId = $('#detsRptConstituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
			//document.getElementById('membershipId').checked = true;
	}else if(id == "assblyConstituencyId"){
		$("#mandalListImg").show();
			$("#searchDataImgForMandl").show();
			$("#mandalList").empty();
			//refreshExistingDetails();
			constituencyId = $('#assblyConstituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
	}
			
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				if(result !=null)
				{
					$("#mandalList").find('option').remove();
					if(id == "detsRptConstituencyId"){
						$("#mandalListImg").hide();
						$("#mandalList").empty();
						//$("#mandalList").append('<option value="0">Select Mandal</option>');
					}
					for(var i in result)
					{
						if(i>0){
							if(id == "detsRptConstituencyId"){
								if(result[i].locationId == 120 || result[i].locationId == 1124)
									$("#mandalList").append('<option value="0">Select Mandal/Muncipality</option>');
								$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
							}else if(id == "assblyConstituencyId"){
								//$("#mandalList").append('<option value="0">Select Mandal/Muncipality</option>');
								$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
							}
						}else{
							$("#mandalList").append('<option value="0">All</option>');
						}
					}	
				}
				if(id == "detsRptConstituencyId"){
					$("#mandalList").trigger("chosen:updated");
				}else if(id == "assblyConstituencyId")	{
					$("#mandalList").trigger("chosen:updated");
				}		
				});
	}
	function getPanchayatWardByMandal(num,id){

			var mandalId=0;
			var constituencyId = 0; //cadreSearchDtls
		if(id == "mandalList"){	
			//hideDetails();
                $("#panchaytListImg").show();		
				$("#searchDataImgForPanc").show();
				//refreshExistingDetails();
				mandalId=$("#mandalList").val();
				constituencyId = $('#detsRptConstituencyId').val();
				$("#panchaytList  option").remove();
				$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
		}
			var jsObj={
				mandalId:mandalId,
				constituencyId : constituencyId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				if(id == "mandalList"){
					$("#panchaytListImg").hide();
						$("#panchaytList").empty();
						//$("#panchaytList").append('<option value="0">Select Panchayat</option>');
				}
				$("#panchaytList").find('option').remove();
			for(var i in result){
				if(id == "mandalList"){
					if(result[i].locationId == 0){
						$("#panchaytList").append('<option value="0">All</option>');
					}
					else{
					$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
				}
			}
			if(id == "mandalList"){
				$("#panchaytList").trigger("chosen:updated");	
			}
		});	
	}
	
	function getRolesBasedReport(){
		
		$("#detailedReportId").html("");
		var committeeLevelIdsListArr = []; 
		var designationIdsArr = [];
		var cosntiteucnyId;
		var districtId;
		var committeeTypeId = $("#committeeTypeId").val();
		var committeeLvlId = $("#committeeLevelTypeId").val();
		if(committeeLvlId == 1){
			committeeLevelIdsListArr.push(6);
			committeeLevelIdsListArr.push(8);
		}else if(committeeLvlId == 2){
			committeeLevelIdsListArr.push(5);
			committeeLevelIdsListArr.push(7);
			committeeLevelIdsListArr.push(9);
		}else if(committeeLvlId == 3){
			committeeLevelIdsListArr.push(11);
		}else if(committeeLvlId == 4){
			committeeLevelIdsListArr.push(10);
		}
		if($("#committeePostitionId").val() > 0)
		designationIdsArr.push($("#committeePostitionId").val());
		
		var locationLevelId =8;//region_Scopes
		var panchayatId=$('#panchaytList').val();
		var mandalId=$('#mandalList').val();
		//var cosntiteucnyId=$('#detsRptConstituencyId').val();
		if(userAccessType == "MP" || userAccessType.indexOf("District") >= 0){
			cosntiteucnyId=$('#assblyConstituencyId').val();
		}else{
			cosntiteucnyId=$('#detsRptConstituencyId').val();
		}
		//var districtId=$('#detsRptdistrictId').val();
		if(userAccessType == "AP" ||  userAccessType == "TS"){
			districtId = $("#distUsrdistrictId").val();
		}else{
			districtId=$('#detsRptdistrictId').val();
		}
		var stateId=$('#statesDivId').val();
		var locationLevelValuesList = [];
		if(userAccessType == "MP" || userAccessType.indexOf("District") >= 0){
			if(panchayatId != null && panchayatId.length>0 && panchayatId>0){
				locationLevelId =6;
				locationLevelValuesList.push(panchayatId); 
			}else if(mandalId != null && mandalId.length>0 && mandalId>0){
				locationLevelId =5;
				locationLevelValuesList.push(mandalId); 
			} else if((cosntiteucnyId != null && cosntiteucnyId.length>0) || cosntiteucnyId == 0){
				if(cosntiteucnyId == 0){
					locationLevelId =4;
					locationLevelValuesList = globalAssbConstIdsArr ; 
				}else{
					locationLevelId =4;
					locationLevelValuesList.push(cosntiteucnyId);
				}
			}
		}else if(userAccessType == "AP" ||  userAccessType == "TS"){
			if(panchayatId != null && panchayatId.length>0 && panchayatId>0){
				locationLevelId =6;
				locationLevelValuesList.push(panchayatId); 
			}else if(mandalId != null && mandalId.length>0 && mandalId>0){
				locationLevelId =5;
				locationLevelValuesList.push(mandalId); 
			} else if(cosntiteucnyId != null && cosntiteucnyId.length>0 && cosntiteucnyId>0){
				locationLevelId =4;
				locationLevelValuesList.push(cosntiteucnyId); 
			}else if((districtId != null && districtId.length>0) || districtId == 0){
				if(districtId == 0){
					locationLevelId =3;
					locationLevelValuesList = globalDistrIdsArr ; 
				}else{
					locationLevelId =3;
					locationLevelValuesList.push(districtId);
				}
			}
		}else{
			if(panchayatId != null && panchayatId.length>0 && panchayatId>0){
				locationLevelId =6;
				locationLevelValuesList.push(panchayatId); 
			}else if(mandalId != null && mandalId.length>0 && mandalId>0){
				locationLevelId =5;
				locationLevelValuesList.push(mandalId); 
			} else if(cosntiteucnyId != null && cosntiteucnyId.length>0 && cosntiteucnyId>0){
				locationLevelId =4;
				locationLevelValuesList.push(cosntiteucnyId); 
			}else if(districtId != null && districtId.length>0 && districtId>0){
				locationLevelId =3;
				locationLevelValuesList.push(districtId); 
			}else if(stateId != null && stateId.length>0 && stateId>0){
				locationLevelId =2;
				locationLevelValuesList.push(stateId); 
			}
		}
		var statusId = $("#committeeStatusId").val();
		var status;
		if(statusId == 1){
			status = "started";
		}else if(statusId == 2){
			status = "completed";
		}else if(statusId == 3){
			status = "notYetStarted";
		}	
				$("#detailedReportId").html('<img id="" style="width:100px;height:100px;margin-left:250px;"  src="./images/Loading-data.gif" alt="Processing Image"/>');
		var jsObj = {
			committeeEnrollmntIds: [$('#tdpCommitteeYearId').val()],
			committeeTypeId: committeeTypeId,
			committeeLevlIdsList: committeeLevelIdsListArr,
			designationsList: [],
			locationLvlId: locationLevelId,
			loctnLevlValues: locationLevelValuesList,
			stateId: stateId,
			searchType: status
		};
		$.ajax({
				type : "POST",
				url : "getCommitteeCreationDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				if(result != null && result.length>0)
				{
					buildRolesBasedReport(result,committeeLevelIdsListArr,designationIdsArr)
				}
				else{
					$("#detailedReportId").html('No Data Available');
				}
			});
	}
function buildRolesBasedReport(result,committeeLevelIdsListArr,designationIdsArr)
{
	var str='';
	str+='<div class="row">';
		str+='<div class="col-md-12 col-xs-12 col-sm-12 m_top20"><h4 class="panel-title">Committee Detailed Report<button class="btn btn-success btn-xs pull-right" id="excelReport" >Export To Excel</button></h4></div>';
		str+='<div class="col-md-12 col-xs-12 col-sm-12" style="">';
			str+='<div class="table-responsive m_top20">';
				str+='<table class="table table-bordered" id="dataTableReport" style="text-transform: uppercase;">';
					str+='<thead class="text-capital" style="background-color:#f2f2f2;font-size:11px;cursor:pointer;text-align:center;">';
						str+='<tr>';
						if(committeeLevelIdsListArr == '11')
						{
							str+='<th rowspan="2">District Name</th>';
						}else if(committeeLevelIdsListArr == '10')
						{
							str+='<th rowspan="2">Constituency Name</th>';
						}else if(committeeLevelIdsListArr.length == 2 )
						{
							str+='<th rowspan="2">District Name</th>';
							str+='<th rowspan="2">Constituency Name</th>';
							str+='<th rowspan="2">Mandal/Muncipality name</th>';
							str+='<th rowspan="2">village/ward</th>';
						}else if(committeeLevelIdsListArr.length == 3 )
						{
							str+='<th rowspan="2">District Name</th>';
							str+='<th rowspan="2">Constituency Name</th>';
							str+='<th rowspan="2">Mandal/muncipality name</th>';
						}
						str+='<th rowspan="2">Committee Name</th>';
						
						for(var k in result[0].hamletsOfTownship[0].result)
						{
							str+='<td colspan="3">'+result[0].hamletsOfTownship[0].result[k].name+'</td>';
						}
						str+='</tr>';
						str+='<tr>';
							var constant = 0;							
							for(var k in result[0].hamletsOfTownship[0].result)
							{
								str+='<th  style="background:grey;" >Total</th>';
								//str+='<th  style="background:orange;" >Proposed</th>';
								str+='<th  style="background:Green;">Entered</th>';
								str+='<th  style="background:red;">Vacancy</th>';
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody style="font-size:12px">';
					for(var i in result)
					{
						for(var j in result[i].hamletsOfTownship)
						{
							str+='<tr>';
								if(committeeLevelIdsListArr == 11)
								{
									str+='<td>'+result[i].districtName+'</td>';
								}else if(committeeLevelIdsListArr == 10)
								{
									str+='<td>'+result[i].constituencyName+'</td>';
								}else if(committeeLevelIdsListArr.length == 2 )
								{
									str+='<td>'+result[i].districtName+'</td>';
									str+='<td>'+result[i].constituencyName+'</td>';
									str+='<td>'+result[i].mandalName+'</td>';
									str+='<td>'+result[i].villageName+'</td>';
								}else if(committeeLevelIdsListArr.length == 3 )
								{
									str+='<td>'+result[i].districtName+'</td>';
									str+='<td>'+result[i].constituencyName+'</td>';
									str+='<td>'+result[i].mandalName+'</td>';
								}
								str+='<td>'+result[i].hamletsOfTownship[j].name+'</td>';
								for(var k in result[i].hamletsOfTownship[j].result)
								{
									var enteredCnt = result[i].hamletsOfTownship[j].result[k].proposedCount+result[i].hamletsOfTownship[j].result[k].finalizedCount;
									if(result[i].hamletsOfTownship[j].result[k].totalCount != null && result[i].hamletsOfTownship[j].result[k].totalCount > 0){
										var vacancyCount = result[i].hamletsOfTownship[j].result[k].totalCount-enteredCnt;
									}else{
										var vacancyCount =  0;
									}
									str+='<td>'+result[i].hamletsOfTownship[j].result[k].totalCount+'</td>';
									//str+='<td>'+result[i].hamletsOfTownship[j].result[k].proposedCount+'</td>';
									str+='<td>'+enteredCnt+'</td>';
									str+='<td>'+vacancyCount+'</td>';
								}
							str+='</tr>';
						}
					}
					str+='</tbody>';
				str+='</table>';
			str+='</div>';
		str+='</div>';
	str+='</div>';
	$("#detailedReportId").html(str)
	$("#dataTableReport").dataTable({
		"aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]],
	});
	$("#dataTableReport").removeClass("dataTable");
}

$(document).on("click","#excelReport",function(){
	tableToExcel(dataTableReport, " STATE - CONSTITUENCY WISE AGE REPORT"); 
});
$(document).on("click","#detailReportId",function(){
	$( "#detailedReportModalDivId" ).modal("show");
});
var globalAssbConstIdsArr = [];
var globalDistrIdsArr = [];
function getUserWiseDetails(){
	$("#distUsrdistrictId").find('option').remove();
	$("#distUsrdistrictId").append('<option value="0">All</option>');
	$("#distUsrdistrictId").trigger("chosen:updated");
	$("#assblyConstituencyId").empty();
	$("#assblyConstituencyId").append('<option value="0">All</option>');
	$("#assblyConstituencyId").trigger("chosen:updated");
	var jsObj=
   {				
							
	}
    $.ajax({
          type:'GET',
          url: 'getUserwiseDetailsListAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){ 
      if(result != null){
		  for(var i in result){
		   if(userAccessType == "AP" ||  userAccessType == "TS"){
			   globalDistrIdsArr.push(result[i].districtId);
				$("#distUsrdistrictId").append('<option value='+result[i].districtId+'>'+result[i].name+'</option>');
		  }else if(userAccessType == "MP" || userAccessType.indexOf("District") >= 0){
			  globalAssbConstIdsArr.push(result[i].parlimentId);
			  $("#assblyConstituencyId").append('<option value='+result[i].parlimentId+'>'+result[i].parliament+'</option>');
		  }
		}
		if(userAccessType == "AP" ||  userAccessType == "TS"){
			$("#distUsrdistrictId").trigger("chosen:updated");	
		}else if(userAccessType == "MP" || userAccessType.indexOf("District") >= 0){
			$("#assblyConstituencyId").trigger("chosen:updated");	
		}
	  }
   });
}
