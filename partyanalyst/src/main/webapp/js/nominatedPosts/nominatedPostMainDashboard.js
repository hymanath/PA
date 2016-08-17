 var globalStateId=1;	 
   $(document).on("click","#myonoffswitch",function(){
		if($(this).prop('checked')){
		globalStateId =1;
		}else{
		globalStateId = 36;
		}
	});
$(document).ready(function(){
	getLocationWiseCastePositionCount("overall",2,globalStateId);
    getLocationWiseCasteGroupPositionCount("overallCasteGroup",2,globalStateId);
	getPositionAndApplicationDetailsCntPositionWise(0,2,"position",globalStateId);
	getPositionAndApplicationDetailsCntLocationWise(0,2,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId",globalStateId);
	getCastGroupList();
	//getApplicationStatusList();
	getPositionList();
	getLocationLevelList();
	getDepartmentList(2);   
	getBoardList(0);
	getNominatedCandidateGroupByDistrict(0,0,0,0,0,0);
    getOverAllTotalCountsByPosition(0,2,0,0,0,0,globalStateId);
	getCasteGroupWiseCountsByPosition(0,2,0,0,0,0,globalStateId);
	getCasteWiseCountsByPosition(0,0,0,2,0,0,globalStateId);
});
$(document).on("click",".castePositionCls",function(){
	var hrefId = $(this).attr("attr_href_id");
	var levelId = $(this).attr("attr_level_value");
	getLocationWiseCastePositionCount(hrefId,levelId,globalStateId);
});
$(document).on("click",".casteGroupCls",function(){
	var hrefId = $(this).attr("attr_href_id");
	var levelId = $(this).attr("attr_level_value");
	getLocationWiseCasteGroupPositionCount(hrefId,levelId,globalStateId);
});
 
 function getLocationWiseCastePositionCount(hrefId,levelId,stateId){
   $("#castePostionDivId").html(' ');
  var jsObj={
        LocationLevelId : levelId,
		positionId : 0,
		stateId :stateId
      }
      $.ajax({
         type:'GET',
         url:'getLocationWiseCastePositionCountAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  if(result != null && result.length > 0){
			  buildCasteWisePositionRslt(result,hrefId);
		  }else{
			 $("#castePostionDivId").html("NO DATA AVAILABLE"); 
		  }
    });
 }
 function buildCasteWisePositionRslt(result,hrefId){
	 
	 	var str='';
	   /*  if(result[0].casteList.length==0){
			str+='No DATA AVAILABLE'; 
			$("#castePostionDivId").html(str);
			return;
		 } */
	
/* 	     str+='<div role="tabpanel" class="tab-pane active pad_10 pad_right0" id="'+hrefId+'">'
         str+='<div class="row">';
	     str+='<div class="col-md-2 pad_right0" style="-webkit-box-shadow: 10px 0 5px -2px #888; box-shadow: 10px 0 5px -2px #888;">';
         str+='<table class="table table-bordered">';
		 str+='<tr>';
         str+='	<td>&nbsp;</td>';                                     
         str+='</tr>'
		 for(var i in result){
			 str+='<tr>';
			   str+='<td id="'+result[i].positionId+'">'+result[i].positionName+'</td>';
			 str+='</tr>';
		 }
		 str+='</table>';
		 str+='</div>'; */
		 str+='<div class="col-md-12 pad_left0">';
         str+='<div class="table-responsive">';
        str+='<table class="table table-bordered dataTableCaste" style="margin:0px !important">';
		if(result[0].positionList != null && result[0].positionList.length > 0){
		  str+='<thead class="text-capital" style="font-size:12px;">';
		  str+='<th></th>';
		  for(var i in result[0].positionList){
			  str+='<th id="'+result[0].positionList[i].positionId+'">'+result[0].positionList[i].positionName+'</th>';
		  }	
          str+='</thead>';		  
		}
		str+='<tbody>';
		for(var i in result){
			var positionList = result[i].positionList;
			 if(positionList != null && positionList.length > 0){
				str+='<tr>';
				str+='<td id="'+result[i].casteId+'">'+result[i].casteName+'</td>';
				 for(var j in result[i].positionList){
					str+='<td>'+positionList[j].count+'</td>'; 
				 }
				str+='</tr>';
			 }
		}
	    str+='</tbody>';
		str+='</table>';
        str+='</div>';
        str+='</div>';
        //str+='</div>';
        //str+='</div>';  
	   $("#castePostionDivId").html(str);
         $(".dataTableCaste").dataTable({
	      "sDom": '<"top">rt<"bottom"><"clear"iflp>',
			"scrollY":        "320px",
			"scrollX":        "200px",
			"scrollCollapse": true,
			"paging":         false
        });  
      
 }

 function getLocationWiseCasteGroupPositionCount(hrefId,LocationLevelId,stateId){
	 
	 $("#casteGroupPostionDivId").html(' ');
  var jsObj={
        LocationLevelId : LocationLevelId,
		positionId : 0,
		stateId :stateId
      }
      $.ajax({
         type:'GET',
         url:'getLocationWiseCasteGroupPositionCountAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  if(result != null && result.length > 0){
			  buildCasteGroupWisePositionRslt(result,hrefId);
		  }else{
		      $("#casteGroupPostionDivId").html("NO DATA AVAILABLE");			  
		  }
    });	
 }
 function buildCasteGroupWisePositionRslt(result,hrefId){
	 var str='';
      if(result[0].casteList.length==0){
		str+='No DATA AVAILABLE'; 
		$("#casteGroupPostionDivId").html(str);
		return;
	 }
	 str+='<div class="tab-content navTabsSelectionBody">';
     str+='<div role="tabpanel" class="tab-pane active" id="'+hrefId+'">';
     str+='<div class="table-responsive">';
     str+='<table class="table table-bordered dataTableCasteGroup">';
	 if(result[0].casteList != null && result[0].casteList.length > 0){
		  str+='<thead class="text-capital">';
		  str+='<th></th>';
		  for(var i in result[0].casteList){
			  str+='<th id="'+result[0].casteList[i].casteId+'">'+result[0].casteList[i].casteName+'</th>';
		  }	
          str+='</thead>';		  
		}
      str+='<tbody>';
		for(var i in result){
			var casteList = result[i].casteList;
			 if(casteList != null && casteList.length > 0){
				str+='<tr>';
				str+='<td id="'+result[i].positionId+'">'+result[i].positionName+'</td>';
				 for(var j in result[i].casteList){
					str+='<td>'+casteList[j].count+'</td>'; 
				 }
				str+='</tr>';
			 }
		}
	    str+='</tbody>';	 
		str+='</table>';
		str+='</div>';
		str+='</div>';
		str+='</div>';
		$("#casteGroupPostionDivId").html(str);	
		if(result.length > 10)
		{ 
			$(".dataTableCasteGroup").dataTable({
			 "sDom": '<"top">rt<"bottom"><"clear"iflp>',
			 "scrollY": "380px"
			});	
		}else{
		  $(".dataTableCasteGroup").dataTable({
			 "sDom": '<"top">rt<"bottom"><"clear"iflp>'
			});		
		}
		 
 }
 
 function getAllPositions(){
	var jsObj={}
      $.ajax({
         type:'GET',
         url:'getAllPositionsAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  buildPositions(result);
    }); 
 }
 function buildPositions(result){
	 var str ='';
	 if(result != null && result.length > 0){
		  str+='<li role="presentation" class="active"><a href="#departments1" aria-controls="departments1" role="tab" data-toggle="tab">Labour </a></li>';
		 for(var i in result){
			str+='<li role="presentation" class="active"><a href="#departments1" aria-controls="departments1" role="tab" data-toggle="tab" attr_positionId="'+result[i].name+'">'+result[i].name+'</a></li>'; 
		 }
	 }
	 $("#positionsDiv").html(str);
 }
 	$(document).on('change','#locationLevelId',function(){
		var boardLevelId = $("#locationLevelId").val();  
		getDepartmentList(boardLevelId);
	});
	$(document).on('change','#departmentId',function(){
		var departmentId = $("#departmentId").val();  
		getBoardList(departmentId);
	});
	function getCastGroupList(){
		var jsObj={}
		$.ajax({   
			type:'GET',
			url:'getCastGroupListAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					$('#casteGroupId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
				$('#casteGroupId').trigger("chosen:updated");  
			}
		});
	}
	/*function getApplicationStatusList(){
		var jsObj={}
		$.ajax({
			type:'GET',
			url:'getApplicationStatusListAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					$('#positionStatusId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
				$('#positionStatusId').trigger("chosen:updated");      
			}
		});     
	}*/
	function getPositionList(){
		var jsObj={}
		$.ajax({
			type:'GET',
			url:'getPositionListAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildPositionTabMenu(result);
				for(var i in result){
					$('#positionId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
				$("#positionId").trigger("chosen:updated");
			}
		});
	}
	function getLocationLevelList(){
    var jsObj={}  
    $.ajax({
      type:'GET',
      url:'getLocationLevelListAction.action',
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
      if(result != null && result.length > 0){
        for(var i in result){
          if(result[i].id == "2"){  
            $('#locationLevelId').append('<option value="'+result[i].id+'" selected>'+result[i].name+'</option>');  
          }else{
            $('#locationLevelId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>'); 
          }
          
        }
        $("#locationLevelId").trigger("chosen:updated");
      }
    });
  }
	function getDepartmentList(boardLevelId){
		var jsObj={
			boardLevelId : boardLevelId  
		}  
		$.ajax({
			type:'GET',
			url:'getDepartmentListAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$('#departmentId').html('<option value="0">ALL</option>');
			if(result != null && result.length > 0){
				for(var i in result){
					$('#departmentId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
				$("#departmentId").trigger("chosen:updated");
			}
		});
	}  
	function getBoardList(deptId){  
		var jsObj={
			deptId : deptId
		}
		$.ajax({
			type:'GET',
			url:'getBoardListAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$('#corporationId').html('<option value="0">ALL</option>');
			if(result != null && result.length > 0){  
				for(var i in result){
					$('#corporationId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');  
				}
				$("#corporationId").trigger("chosen:updated");	  			
			}
		});
	}

	 function getAllStatusDetails(){
		positionId =$("#positionId option:selected").val();
		levelId = $("#locationLevelId").val();
		deptId = $("#departmentId").val();
		boardId = $("#corporationId").val();
		casteGroupId =$("#casteGroupId").val();
		applStatusId = $("#positionStatusId").val();
		getOverAllTotalCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,globalStateId);
		getCasteGroupWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,globalStateId);
		getCasteWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,globalStateId);
	} 
	
	function getOverAllTotalCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,stateId){
		$("#totalCasteId").html(" ");
		$("#totalAgeWiseId").html(" ");
		var jsObj={
		positionId:positionId,
		levelId :levelId,
		deptId :deptId,
		boardId :boardId,
		casteGroupId:casteGroupId,
		applStatusId:applStatusId,
		stateId : stateId
		}
		$.ajax({
			type:'GET',
			url:'getOverAllTotalCountsByPositionAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null){
				buildOverAllTotalCountsByPosition(result);
			}else{
				$("#totalCasteId").html('<p>No Data Available</p>');
				$("#totalAgeWiseId").html('<p>No Data Available</p>');
			}
			
		});
	}
	
function buildOverAllTotalCountsByPosition(result){
	var totalMFCount=0;
	var totalmaleCnt = 0;
	var totalFemaleCnt = 0;
	 for (var i in result.applicatnStatsList){
		if(result.applicatnStatsList[i].statusName =='Female' || result.applicatnStatsList[i].statusName == 'F'){		
			totalMFCount=totalMFCount+result.applicatnStatsList[i].statusCount;
			totalFemaleCnt = totalFemaleCnt+result.applicatnStatsList[i].statusCount;
		}
		if(result.applicatnStatsList[i].statusName == 'Male' || result.applicatnStatsList[i].statusName == 'M'){
					totalMFCount=totalMFCount+result.applicatnStatsList[i].statusCount;
					totalmaleCnt = totalmaleCnt + result.applicatnStatsList[i].statusCount;
		}			
		}
		$("#totalFemaleId").html(totalFemaleCnt);
		$("#totalMaleId").html(totalmaleCnt);
	    $("#totalMaleFemaleId").html(totalMFCount);
	    if(result.applicatnStatsList.length > 0){
		$("#totalApplicationId").html(result.applicatnStatsList[0].totalApplicationCount);	
		}
	  
		var str='';
		str+='<table class="table table-bordered bg_D4 tableNew1">';
		str+='<thead>';
		for(var i in result.nominatedStatusList) {
		str+='<th>'+result.nominatedStatusList[i].statusName+'</th>';
		}
		str+='</thead>';
		str+='<tbody>';
		str+='<tr>';
		for(var i in result.nominatedStatusList)
		{
			str+='<td>'+result.nominatedStatusList[i].statusCount+'</td>';

		}
		str+='</tr>';
		str+='</tbody>';
		str+='</table>';
		
		
		var str1='';
		str1+='<table class="table table-bordered bg_CB tableNew1">';
		str1+='<thead style="font-size:12px;">';
		for(var i in result.positinsList){
		str1+='<th>'+result.positinsList[i].statusName+' Age</th>';
		}
		str1+='</thead>';
		str1+='<tbody>';
		str1+='<tr>';
		for(var i in result.positinsList)
		{
			str1+='<td>'+result.positinsList[i].statusCount+'</td>';
	
		}
		str1+='</tr>';
		str1+='</tbody>';
		str1+='</table>';
		$("#totalCasteId").html(str);
		$("#totalAgeWiseId").html(str1);
		
	}
	
	
	function getCasteGroupWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,stateId){
		$("#casteAndAgeWiseId").html(" ");
		$("#casteGroup").html(" ");
		var jsObj={
		positionId:positionId,
		levelId :levelId,
		deptId :deptId,
		boardId :boardId,
		casteGroupId:casteGroupId,
		applStatusId:applStatusId,
		stateId : stateId
		}
		
		$.ajax({
			type:'GET',
			url:'getCasteGroupWiseCountsByPositionAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildCasteGroupWiseCountsByPosition(result);
				buildCasteGroupWiseChart(result);
			}else{
				$("#casteAndAgeWiseId").html('<p>No Data Available</p>');
				$('#casteGroup').html('<p>No Data Available</p>');
			}
			
		});
	
	}
	
	function buildCasteGroupWiseCountsByPosition(result){
		var str='';
		str+='<table>';
		str+='<thead class="bg_ef">';
		str+='<tr>'
		str+='<th rowspan="2">Caste Group</th>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total')
					str+='<th colspan="3"> Total </th>'
				else
					str+='<th colspan="2">'+result[0].applicatnStatsList[i].statusName+'</th>'
			}
		}
		str+='</tr>';
		str+='<tr>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<th> T </th>'
					str+='<th> M </th>'
					str+='<th> F </th>'
				}
				else{
					str+='<th> M </th>'
					str+='<th> F </th>'
				}
			}
		}
		str+='</tr>';
		str+='</thead>'
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
				str+='<td>'+result[i].name+'</td>';
				if(result[i].applicatnStatsList != null && result[i].applicatnStatsList.length > 0){
					for(var j in result[i].applicatnStatsList){
						if(result[i].applicatnStatsList[j].statusName == 'Total'){
							str+='<td>'+result[i].applicatnStatsList[j].statusCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].maleCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].femaleCount+'</td>';
						}
						else{
							str+='<td>'+result[i].applicatnStatsList[j].maleCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].femaleCount+'</td>';
						}
					}
				}
			str+='</tr>';
		}
		str+='<tr>';
		 str+='<td >TOTAL</td>';
		  str+='<td colspan="3">'+result[0].overAllCount+'</td>';
		  str+='<td colspan="2">'+result[0].twentyTo29AgeRangeCount+'</td>';
		  str+='<td colspan="2">'+result[0].thirtyTo39AgeRangeCount+'</td>';
		  str+='<td colspan="2">'+result[0].fourtyTo49AgeRangeCount+'</td>';
		  str+='<td colspan="2">'+result[0].fiftyTo59AgeRangeCount+'</td>';
		  str+='<td colspan="2">'+result[0].sixtyAvoveAgeRangeCount+'</td>';
		str+='</tr>';
		str+='</tbody>';
		str+='</table>';
		$("#casteAndAgeWiseId").html(str);
		}
		
	
	
	function getCasteWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,stateId){
		$("#casteNameWiseTotlaCntsId").html(" ");
		$("#casteWisePositions").html(" ");
		var jsObj={
		positionId:positionId,
		levelId :levelId,
		deptId :deptId,
		boardId :boardId,
		casteGroupId:casteGroupId,
		applStatusId:applStatusId,
		stateId : stateId
		}
		$.ajax({
			type:'GET',
			url:'getCasteWiseCountsByPositionAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildCasteWiseCountsByPosition(result,positionId,levelId,deptId,boardId,casteGroupId,applStatusId);
				buildCasteWiseCountsChart(result);
			}else{
				$("#casteNameWiseTotlaCntsId").html('<p>No Data Available</p>');
				$('#casteWisePositions').html('<p>No Data Available</p>');
			}
		});
	}
	
	function buildCasteWiseCountsByPosition(result,positionId,levelId,deptId,boardId,casteGroupId,applStatusId){
		var str='';
		str+='<table class="table table-bordered">';
		str+='<thead class="bg_ef">';
		str+='<tr>';
		str+='<th rowspan="2">CASTE NAME</th>';
	if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<th colspan="3"></th>'
				}else{
					str+='<th colspan="2">'+result[0].applicatnStatsList[i].statusName+'</th>'
			}
		}
	}
		str+='</th>';
		str+='<th></th>';
		str+='</tr>';
		str+='<tr>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length>0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<th> Total </th>';
					str+='<th> Male </th>';
					str+='<th> Female </th>';
				}else{
					str+='<th colspan="2"> Age </th>';
					//str+='<th> F </th>';
				}
			}
		}
		str+='<th></th>';
		str+='</tr>';
		str+='</thead>';
		str+='<tbody class="bg_ef">';
		for(var i in result){
			str+='<tr>';
			str+='<td id="'+result[i].id+'">'+result[i].name+'</td>';
				if(result[i].applicatnStatsList != null && result[i].applicatnStatsList.length > 0){
					for(var j in result[i].applicatnStatsList){
						if(result[i].applicatnStatsList[j].statusName == 'Total'){
							str+='<td>'+result[i].applicatnStatsList[j].statusCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].maleCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].femaleCount+'</td>';
						}
						else{
							var maleCount=result[i].applicatnStatsList[j].maleCount;
							var femaleCount = result[i].applicatnStatsList[j].femaleCount;
							str+='<td colspan="2">'+(maleCount+femaleCount)+'</td>';
							//str+='<td>'+result[i].applicatnStatsList[j].femaleCount+'</td>';
						}
					       
					}
				}
				str+='<td style="text-align:right;"><i class="glyphicon glyphicon-plus casteCls" attr_id="tableStrId'+result[i].id+'" attr_caste_id="'+result[i].id+'" attr_position_id="'+positionId+'" attr_locationLevel_id="'+levelId+'" attr_dept_id="'+deptId+'" attr_corporation_id="'+boardId+'" attr_castGroup_id="'+casteGroupId+'" attr_positionStatus_id="'+applStatusId+'" </i></td>';  
			   str+='</tr>';
				str+='<tr class="showHideTr" style="display:none" id="tableStrId'+result[i].id+'">'; 
			str+='</tr>';
		}
		str+='</tbody>'
		str+='</table>';
		$("#casteNameWiseTotlaCntsId").html(str);
		if(result.length > 15 )
		{
			$("#casteNameWiseTotlaCntsId").mCustomScrollbar({setHeight: '440px'})
			$("#casteWisePositions").parent().mCustomScrollbar({setHeight: '440px'})
		}
	}
	 $(document).on('click','.casteCls',function(){
		$(this).toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
		$(this).closest('tr').next('tr.showHideTr').toggle();    
		
		var innerTableId = $(this).attr("attr_id");   
		var casteId = $(this).attr("attr_caste_id");
		var positionId = $(this).attr("attr_position_id");
		var boardLevelId = $(this).attr("attr_locationLevel_id");    
		var deptId = $(this).attr("attr_dept_id");
		var boardId = $(this).attr("attr_corporation_id");
		var castegroupId = $(this).attr("attr_castgroup_id");
		var positionStatusId = $(this).attr("attr_positionStatus_id");
		if($(this).hasClass("glyphicon-minus"))
		{
			casteWisePositionsCountsByPosition(innerTableId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,casteId,globalStateId,"expand");
		}else{
			casteWisePositionsCountsByPosition(innerTableId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,casteId,globalStateId,"close");
		}
	});
	
function casteWisePositionsCountsByPosition(innerTableId,positionId,levelId,deptId,boardId,castegroupId,positionStatusId,casteId,stateId,actionType){
		//$(".innerTableDivCls").html("");
	    //$(".tableStrOuterCls").hide();
		 if(actionType == "close"){
			 return;
		  }
		var jsObj={
			positionId:positionId,
			levelId :levelId,
			deptId :deptId,
			boardId :boardId,
			casteGroupId:castegroupId,
			applStatusId:positionStatusId,
			casteId:casteId,
			stateId : stateId
		}
		$.ajax({
			type:'GET',
				url:'getCasteWisePositionsCountsByPositionAction.action',  
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null){ 
				buildCasteWisePositionsCountsByPosition(result,innerTableId);
			}
		});
	}
	
	function buildCasteWisePositionsCountsByPosition(result,innerTableId){
	    $("#"+innerTableId).html(" ");
	//	$("#tableStrOuterId"+innerTableId).show();
		var str='';
		str+='<td colspan="15">';
		str+='<table class="table table-hover table-bordered">';
		str+='<thead style="background:#ddd;">';
		str+='<tr>';
		str+='<th>Position</th>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<th>Total Positions</th>';
					str+='<th>M</th>';
					str+='<th>F</th>';
				}else{
					str+='<th>'+result[0].applicatnStatsList[i].statusName+'</th>';
				}
			}
		}
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr>';
			str+='<td>'+result[i].name+'</td>';
			if(result[i].applicatnStatsList != null && result[i].applicatnStatsList.length > 0){
					for(var j in result[i].applicatnStatsList){
						if(result[i].applicatnStatsList[j].statusName == 'Total'){
							str+='<td>'+result[i].applicatnStatsList[j].statusCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].maleCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].femaleCount+'</td>';
						}else{
							 var totalMaleCount=result[i].applicatnStatsList[j].maleCount;
							 var totalFemaleCount=result[i].applicatnStatsList[j].femaleCount;
							str+='<td>'+(totalMaleCount+totalFemaleCount)+'</td>';	
						}
					}
			}
			str+='</tr>';
			
		}
		str+='</tbody>';
		str+='</table>';
		str+='</td>';
		$("#"+innerTableId).html(str);
	}
function buildPositionTabMenu(result){
	var str='';
	 str+='<ul class="nav nav-tabs tabsCustomFinal ulCls" role="tablist">';
     str+='<li role="presentation" attr_id="0" class="active postionliCls"><a href="#positionoverView" aria-controls="positionoverView" role="tab"  data-toggle="tab">Completed Overview</a></li>';
		for(var i in result){
		  str+='<li attr_id="'+result[i].id+'" class="postionliCls" role="presentation"><a href="#positon'+i+'" aria-controls="positon'+i+'" role="tab" data-toggle="tab">'+result[i].name+'</li>';	
		}
	str+='</ul>';
	$("#positonDivId").html(str);
}	
function getPositionAndApplicationDetailsCntPositionWise(positionId,locationLevelId,reportType,stateId){
  
  var jsObj={
        locationLevelId : locationLevelId,
		positionId : positionId,
		reportType : reportType,
		stateId : stateId
      }
      $.ajax({
         type:'GET',
         url:'getPositionAndApplicationDetailsCntPositionWiseAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}      
      }).done(function(result){
		     if(result != null){   
			 if(reportType=="position"){
			  buildPostionRslt(result); 
		    }else if(reportType=="application"){
			   buildApplicationOverviewRslt(result);   
		    }  
		   }
    });		
}
var postionId=0;
$(document).on("click",".postionliCls",function(){
	 postionId= $(this).attr("attr_id");
	  $( ".overviewUlCls li:nth-child(1) a" ).trigger("click");	
	 getPositionAndApplicationDetailsCntPositionWise(postionId,0,"position",globalStateId);
     getPositionAndApplicationDetailsCntLocationWise(postionId,2,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId",globalStateId);
});

$(document).on("click",".overviewHrfCls",function(){
	var reportType = $(this).attr("attr_report_type");
	getPositionAndApplicationDetailsCntPositionWise(postionId,0,reportType,globalStateId);
});
function buildPostionRslt(result){
	var positionCntArr =[];
	var positionCntPerArr =[];
	var LevelArr=[];
	var circleColorArr=[];
	if(result.totalPositionCn>0){
		positionCntArr.push(result.totalPositionCn);
		positionCntPerArr.push(result.totalPositionCntPer);
		LevelArr.push("TOTAL POSITIONS");
		circleColorArr.push("#fff2b3");
	}
	if(result.shortedListedCndtCnt>0){
		positionCntArr.push(result.shortedListedCndtCnt);
		positionCntPerArr.push(result.shortListedCntper);
		LevelArr.push("SHORT LISTED");
		circleColorArr.push("#eeb3af");
	}
	if(result.finalReviewCantCnt>0){
		positionCntArr.push(result.finalReviewCantCnt);
		positionCntPerArr.push(result.finalReviewPer);
		LevelArr.push("READY FOR FINAL REVIEW");
		circleColorArr.push("#D7D5EA");
	}
	if(result.confirmCntCnt>0){
		positionCntArr.push(result.confirmCntCnt);
		positionCntPerArr.push(result.confirmCntPer);
		LevelArr.push("FINALIZED");
		circleColorArr.push("#bee0e1");
	}
	if(result.goIssuedCnt>0){
		positionCntArr.push(result.goIssuedCnt);
		positionCntPerArr.push(result.goIssuedPer);
		LevelArr.push("G.O ISSUED");
		circleColorArr.push("#ad9591");
	}
	var radiusArrForBackBround=[];
	var innerRadiusArrForBackBround=[];
	var radiusArrForValue=[];
	var innerRadiusArrForValue=[];
	var yAxisArrForLevel=[];
	var radiusForBackground = 100;
	var radiusForValue = 100;
	var y = 5;
	var i = 0;
	for (i = 0; i < positionCntArr.length; ++i) {
		radiusArrForBackBround.push(radiusForBackground);
        innerRadiusArrForBackBround.push(radiusForBackground);
        radiusArrForValue.push(radiusForValue);
        innerRadiusArrForValue.push(radiusForValue-5);
        yAxisArrForLevel.push(y);
		radiusForBackground=radiusForBackground-12;
		radiusForValue = radiusForValue-12;
        y=y+5;		
	}
   	var josnDataArr=[];
	var jsonLevelArr=[];
	var j=0;
	for (j = 0; j < positionCntArr.length; ++j) {
		josnDataArr.push({"color":"#fff","startValue":0,"endValue":100,"radius":radiusArrForBackBround[j]+"%","innerRadius":innerRadiusArrForBackBround[j]+"%"});
		josnDataArr.push({"color":circleColorArr[j],"startValue":0,"endValue":positionCntPerArr[j],"radius":radiusArrForValue[j]+"%","innerRadius":innerRadiusArrForValue[j]+"%","balloonText":positionCntArr[j]});
		jsonLevelArr.push({"text":LevelArr[j],"x": "49%","y":yAxisArrForLevel[j]+"%","size": 10,"bold": true,"color": "#333","align": "right"});
	}
	buildPostionGaugeHighChart(josnDataArr,jsonLevelArr);
}
function buildPostionGaugeHighChart(josnDataArr,jsonLevelArr){
	$("#chartdiv").html("");
var gaugeChart = AmCharts.makeChart("chartdiv", {
  "type": "gauge",
  "theme": "light",
  "axes": [{
    "axisAlpha": 0,
    "tickAlpha": 0,
    "labelsEnabled": false,
    "startValue": 0,
    "endValue": 100,
    "startAngle": 0,
    "endAngle": 270,
    "bands":josnDataArr 
  }],
  "allLabels":jsonLevelArr,
  "export": {
    "enabled": false
  } 
});
}
function buildApplicationOverviewRslt(result){
	var applicationCntArr =[];
	var applicationCntPerArr =[];
	var LevelArr=[];
	var circleColorArr=[];
	if(result.totalAppReceivedCnt>0){
		applicationCntArr.push(result.totalAppReceivedCnt);
		applicationCntPerArr.push(result.totalAppRecevidPer);
		LevelArr.push("Received");
		circleColorArr.push("#E58D45");
	}
	if(result.rejectedCnt>0){
		applicationCntArr.push(result.rejectedCnt);
		applicationCntPerArr.push(result.rejectedAppPer);
		LevelArr.push("Rejected");
		circleColorArr.push("#DD665D");
	}
	if(result.inProgressCnt>0){
		applicationCntArr.push(result.inProgressCnt);
		applicationCntPerArr.push(result.inProgressAppPer);
		LevelArr.push("In Progress");
		circleColorArr.push("#65A7E1");
	}
	if(result.confirmCntCnt>0){
		applicationCntArr.push(result.confirmCntCnt);
		applicationCntPerArr.push(result.completedAppPer);
		LevelArr.push("Completed");
		circleColorArr.push("#FDD501");
	}
	var radiusArrForBackBround=[];
	var innerRadiusArrForBackBround=[];
	var radiusArrForValue=[];
	var innerRadiusArrForValue=[];
	var yAxisArrForLevel=[];
	var radiusForBackground = 100;
	var radiusForValue = 100;
	var y = 5;
	var i = 0;
	for (i = 0; i < applicationCntArr.length; ++i) {
		radiusArrForBackBround.push(radiusForBackground);
        innerRadiusArrForBackBround.push(radiusForBackground);
        radiusArrForValue.push(radiusForValue);
        innerRadiusArrForValue.push(radiusForValue-5);
        yAxisArrForLevel.push(y);
		radiusForBackground=radiusForBackground-12;
		radiusForValue = radiusForValue-12;
        y=y+5;		
	}
   	var josnDataArr=[];
	var jsonLevelArr=[];
	var j=0;
	for (j = 0; j < applicationCntArr.length; ++j) {
		josnDataArr.push({"color":"#fff","startValue":0,"endValue":100,"radius":radiusArrForBackBround[j]+"%","innerRadius":innerRadiusArrForBackBround[j]+"%"});
		josnDataArr.push({"color":circleColorArr[j],"startValue":0,"endValue":applicationCntPerArr[j],"radius":radiusArrForValue[j]+"%","innerRadius":innerRadiusArrForValue[j]+"%","balloonText":applicationCntArr[j]});
		jsonLevelArr.push({"text":LevelArr[j],"x": "49%","y":yAxisArrForLevel[j]+"%","size": 10,"bold": true,"color": "#333","align": "right"});
	}
	buildAppOverviewHighCharts(josnDataArr,jsonLevelArr);

}
function buildAppOverviewHighCharts(josnDataArr,jsonLevelArr){
	$("#chartdiv").html("");
	var gaugeChart = AmCharts.makeChart("chartdiv", {
  "type": "gauge",
  "theme": "light",
  "axes": [{
    "axisAlpha": 0,
    "tickAlpha": 0,
    "labelsEnabled": false,
    "startValue": 0,
    "endValue": 100,
    "startAngle": 0,
    "endAngle": 270,
    "bands": josnDataArr
  }],
  "allLabels":jsonLevelArr,
  "export": {
    "enabled": false
  }
});
}
$(document).on("click",".locationLevelcollapseCls",function(){
	     $("#collapseOne").collapse('hide');
		  $("#collapseTwo").collapse('hide');
		  $("#collapseThree").collapse('hide');
		  $("#collapseFour").collapse('hide');
		  $(this).collapse('show');
	var  collapseLevelId = $(this).attr("attr_collapse_level_Id");
	var collapseHrefId = $(this).attr("attr_collapse_href_id");
	var locationLevelId = $(this).attr("attr_level_Id");
	var postionHighChartId = $(this).attr("attr_postion_highChart_id");
	var appHighChartId = $(this).attr("attr_app_highChart_id");
	 getPositionAndApplicationDetailsCntLocationWise(postionId,locationLevelId," ",collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId,1);
	
});
function getPositionAndApplicationDetailsCntLocationWise(positionId,locationLevelId,reportType,collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId,stateId){
  var jsObj={
        locationLevelId : locationLevelId,
		positionId : positionId,
		reportType : reportType,
		stateId : stateId
      }
      $.ajax({
         type:'GET',
         url:'getPositionAndApplicationDetailsCntLocationWiseAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  if(result!=null){
			  buildLocationLevelPositionAndAppRslt(result,locationLevelId,collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId);
		  }
    });			
}
function buildLocationLevelPositionAndAppRslt(result,locationLevelId,collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId){
	var positionRslt = result.positionList;
	var applicationRslt = result.applicationList;
	if(positionRslt != null && positionRslt.length > 0){
	 var str='';
			str+='<div class="row">';
				str+='<div class="col-md-6 col-xs-12 col-sm-6" style="border-right:1px solid #ddd">';
				str+='<h4 class="panel-tite"><u>Positions</u></h4>'
					str+='<div id="'+postionHighChartId+'" style="height:150px;margin-bottom: 20px;"></div>';
					str+='<ul class="positionsUl" style="margin-top:20px !imortant;">';
						str+='<li class="total"><span class="statusBox"></span>TOTAL POSITIONS<span class="count pull-right">'+positionRslt[0].totalPositionCn+'</span></li>';
						str+='<li class="totalOpendPositioned"><span class="statusBox"></span>TOTAL OPEN POSITIONS<span class="count pull-right">'+positionRslt[0].totalOpendPositionCnt+'</span></li>';
					  	/*str+='<li class="noCandidate"><span class="statusBox"></span>APPLICATION NOT RECEIVED FOR POSITION<span class="count pull-right">'+positionRslt[0].noCandidateCnt+'</span></li>'; */
					str+='</ul>';
					str+='<ul class="positionsUl subPositions" style="margin-top:20px !imortant;">';
						str+='<li class="shortListed"><span class="statusBox"></span>SHORT LISTED<span class="count pull-right">'+positionRslt[0].shortedListedCndtCnt+'</span></li>';
					   // str+='<li class="notStarted"><span class="statusBox"></span>Not Started<span class="count">1200</span></li>';
						str+='<li class="readyForReview"><span class="statusBox"></span>READY FOR FINAL REVIEW<span class="count pull-right">'+positionRslt[0].finalReviewCantCnt+'</span></li>';
						str+='<li class="finalised"><span class="statusBox"></span>FINALIZED<span class="count pull-right">'+positionRslt[0].confirmCntCnt+'</span></li>';
						str+='<li class="goIssued"><span class="statusBox"></span>G.O ISSUED<span class="count pull-right">'+positionRslt[0].goIssuedCnt+'</span></li>';
					str+='</ul>';
				str+='</div>';
				str+='<div class="col-md-6 col-xs-12 col-sm-6">';
				str+='<h4 class="panel-tite"><u>Applications</u></h4>';
					str+='<div id="'+appHighChartId+'" style="height:200px;"></div>';
					if(applicationRslt != null && applicationRslt.length > 0){
						str+='<ul class="positionsUlPie m_top10">';
							str+='<li class="received"><span class="statusBox"></span>RECEIVED<span class="count pull-right">'+applicationRslt[0].totalAppReceivedCnt+'</span></li>';					
							str+='<li class="rejected"><span class="statusBox"></span>REJECTED<span class="count pull-right">'+applicationRslt[0].rejectedCnt+'<small class="text-muted">('+applicationRslt[0].rejectedAppPer+'%)</small></span></li>';
							str+='<li class="inProgress"><span class="statusBox"></span>IN PROGRESS<span class="count pull-right">'+applicationRslt[0].inProgressCnt+'<small class="text-muted">('+applicationRslt[0].inProgressAppPer+'%)</small></span></li>';
							str+='<li class="completed"><span class="statusBox"></span>COMPLETED<span class="count pull-right">'+applicationRslt[0].confirmCntCnt+'<small class="text-muted">('+applicationRslt[0].completedAppPer+'%)</small></span></li>';
						str+='</ul>';
	               } 
				str+='</div>';
			str+='</div>';
		if(locationLevelId==2){
		  $(".stateBodyDivCls").html(str);	
		}else if(locationLevelId == 3){
		 $(".districtBodyDivCls").html(str);
		}else if(locationLevelId == 4){
		 $(".constituencyBodyDivCls").html(str);
		}else if(locationLevelId == 5){
		 $(".manMunCorBodyDivCls").html(str);
		}
		if(collapseLevelId != null && collapseLevelId=="stateLevlId"){
		  $("#collapseOne").collapse('show');
		  $("#collapseTwo").collapse('hide');
		  $("#collapseThree").collapse('hide');
		  $("#collapseFour").collapse('hide');
		} 
	  buildLocationWisePostionHighCahrtRslt(positionRslt,postionHighChartId);	
	}
	 if(applicationRslt != null && applicationRslt.length > 0){
		 buildLocatinWiseApplicationHighChartsRslt(applicationRslt,appHighChartId);  
	}  
}
function buildLocationWisePostionHighCahrtRslt(positionRslt,postionHighChartId){
var headingArr=[];
 headingArr.push('TOTAL POSITIONS');
 headingArr.push('TOTAL OPEN POSITIONS');
 headingArr.push('SHORT LISTED');
 headingArr.push('READY FOR FINAL REVIEW');
 headingArr.push('FINALIZED');
 headingArr.push('G.O ISSUED');
var jsonDataArr=[];
	jsonDataArr.push({y:parseFloat(positionRslt[0].totalPositionCntPer),color:'#fff2b3'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].totalOpendPositionCntPer),color:'#D7D5EA'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].shortListedCntper),color:'#eeb3af'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].finalReviewPer),color:'#c2bedf'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].confirmCntPer),color:'#bee0e1'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].goIssuedPer),color:'#ad9591'})
	buildPositionHighchartLocationWise(headingArr,jsonDataArr,postionHighChartId)
}
function buildPositionHighchartLocationWise(headingArr,jsonDataArr,postionHighChartId){
	$("#"+postionHighChartId).highcharts({
		//colors:['#fff2b3','#f1c5a1','#eeb3af','#c2bedf','#bee0e1','#ad9591'],
        chart: {
            type: 'column'
        },
        xAxis: {
			labels: {
                enabled: false
            },
            categories:headingArr,
            crosshair: true,
			gridLineWidth: 0,
            minorGridLineWidth: 0
        },
        yAxis: {
			gridLineWidth: 0,
            minorGridLineWidth: 0,
            min: 0,
            title: {
                text: ' '
            },
			labels: {
                enabled: false
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f}%</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: ' ',
            data: jsonDataArr

        }]
    });
}
function buildLocatinWiseApplicationHighChartsRslt(applicationRslt,appHighChartId){
var colorArr=[];
 //colorArr.push('#E58D45');	
 colorArr.push('#DD665D');	
 colorArr.push('#65A7E1');	
 colorArr.push('#FDD501');	 
 var jsonDataArr=[];
  //jsonDataArr.push(['Received',parseFloat(applicationRslt[0].totalAppReceivedCnt)]);
  jsonDataArr.push(['Rejected',parseFloat(applicationRslt[0].rejectedCnt)]);
  jsonDataArr.push(['In Progress',parseFloat(applicationRslt[0].inProgressCnt)]);
  jsonDataArr.push(['Completed',parseFloat(applicationRslt[0].confirmCntCnt)]);  
  buildAppHighChartsLocationWise(colorArr,jsonDataArr,appHighChartId);
}
function buildAppHighChartsLocationWise(colorArr,jsonDataArr,appHighChartId){
	$("#"+appHighChartId).highcharts({
		colors:colorArr,
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
         plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: false
				},
				showInLegend: true
			}
		},
		legend: {
                enabled: false,
				useHTML: true,
				labelFormatter: function() { 
					return '<p style="width:200px;">' + this.name + ' <span class"text-right">' + this.y + '(' + this.percentage.toFixed(1) + '%)</span></p>';
				}
			 },
        series: [{
            name: ' ',
            data:jsonDataArr
        }]
    });     
}
	$(document).on('click','#statusDetailsId',function(){
		var positionId = $("#positionId").val();
		var position = $("#positionId option:selected").text();
		var locationLevelId = $("#locationLevelId").val();
		var locationLevel= $("#locationLevelId option:selected").text();
		var deptId = $("#departmentId").val();
		var deptName = $("#departmentId option:selected").text();
		var corporationId = $("#corporationId").val();
		var corporationName = $("#corporationId option:selected").text();
		var castGroupId = $("#casteGroupId").val();
		var casteGroup = $("#casteGroupId option:selected").text();
		var positionStatusId = $("#positionStatusId").val(); 
		var postionStatus =$("#positionStatusId option:selected").text();
		if(positionId==0){
		 $(".positionCls").html("All Positions");	
		}else{
		 $(".positionCls").html(position);		
		}
		if(locationLevelId==0){
		 $(".locationCls").html("All location level");	
		}else{
		 $(".locationCls").html(locationLevel);		
		}
		if(deptId==0){
		 $(".departmentCls").html("All department");
		}else{
		 $(".departmentCls").html(deptName);	
		}
		if(corporationId==0){
		 $(".corporationCls").html("All corporation");	
		}else{
		 $(".corporationCls").html(corporationName)	
		}
		if(castGroupId==0){
		$(".casteGroupsCls").html("All caste groups");	
		}else{
		$(".casteGroupsCls").html(casteGroup);		
		}
		if(positionStatusId==0){
		$(".gOssuedCls").html("G.O Issued");	
		}else{
		$(".gOssuedCls").html(postionStatus);		
		}
		getNominatedCandidateGroupByDistrict(positionId,locationLevelId,deptId,corporationId,castGroupId,positionStatusId);
		
	});
	
	function getNominatedCandidateGroupByDistrict(positionId,locationLevelId,deptId,corporationId,castGroupId,positionStatusId){
		var positionId = positionId;
		var locationLevelId = locationLevelId;
		var deptId = deptId;
		var corporationId = corporationId;
		var castGroupId = castGroupId;   
		var positionStatusId = positionStatusId;   
		var stateId = 1;  
		var jsObj={
			positionId : positionId,
			locationLevelId : locationLevelId,    
			deptId : deptId,
			corporationId : corporationId,
			castGroupId : castGroupId,
			positionStatusId : positionStatusId,
			stateId : stateId  
		} 
		$.ajax({
			type:'GET',
			url:'getNominatedCandidateGroupByDistAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			if(result != null && result.length > 0){  
				buildStatePositionCluntTable(result,positionId,locationLevelId,deptId,corporationId,castGroupId,positionStatusId,stateId);
			}else{
				$("#statePositionId").html("No Data Available");
			}
		}); 
	} 
	function buildStatePositionCluntTable(result,positionId,locationLevelId,deptId,corporationId,castGroupId,positionStatusId,stateId){
		var str = '';
		str+='<table class="table table-condensed" style="border:1px solid #ddd;background-color:#ECF1F5">';  
		str+='<thead class="bg_ef text-capital">';
			str+='<th style="width:200px;">District</th>';
			str+='<th style="width:200px;text-align:center">Finalised Positions Total</th>';  
			str+='<th style="width:100px;">Male</th>';
			str+='<th style="width:100px;">Female</th>';
			str+='<th style="width:100px;">20-29 AGE</th>';   
			str+='<th style="width:100px;">30-39 AGE</th>';
			str+='<th style="width:100px;">40-49 AGE</th>';
			str+='<th style="width:100px;">50-59 AGE</th>';
			str+='<th style="width:100px;">60+ AGE</th>';
			str+='<th style="width:100px;"></th>';
		str+='</thead>';
		str+='<tbody>';
		for(var i in result){
			str+='<tr class="text-capital">';
				str+='<td>'+result[i].name+'</td>';
				str+='<td>'+result[i].totalPositions+'</td>';
				str+='<td>'+result[i].maleCount+'</td>';
				str+='<td>'+result[i].femaleCount+'</td>';
				str+='<td>'+result[i].firstAgeGroupCount+'</td>';
				str+='<td>'+result[i].secondAgeGroupCount+'</td>';
				str+='<td>'+result[i].thirdAgeGroupCount+'</td>';
				str+='<td>'+result[i].fourthAgeGroupCount+'</td>';
				str+='<td>'+result[i].fifthAgeGroupCount+'</td>';
				str+='<td style="text-align:right;"><i class="glyphicon glyphicon-plus districtCls" attr_id="districtPositionId'+i+'" attr_dist_id="'+result[i].districtId+'" attr_position_id="'+positionId+'" attr_locationLevel_id="'+locationLevelId+'" attr_dept_id="'+deptId+'" attr_corporation_id="'+corporationId+'" attr_castGroup_id="'+castGroupId+'" attr_positionStatus_id="'+positionStatusId+'" attr_state_id="'+stateId+'"></i></td>';     
			str+='</tr>';
			str+='<tr class="showHideTr" style="display:none" id="districtPositionId'+i+'">';    
			str+='</tr>';
		}
		str+='</tbody>';
		$("#statePositionId").html(str);
	}
	$(document).on('click','.districtCls',function(){
		$(this).toggleClass("glyphicon-plus").toggleClass("glyphicon-minus");
		$(this).closest('tr').next('tr.showHideTr').toggle();    
		
		var sectionId = $(this).attr("attr_id");   
		var districtId = $(this).attr("attr_dist_id");
		var stateId = $(this).attr("attr_state_id");
		var positionId = $(this).attr("attr_position_id");
		var boardLevelId = $(this).attr("attr_locationLevel_id");    
		var deptId = $(this).attr("attr_dept_id");
		var boardId = $(this).attr("attr_corporation_id");
		var castegroupId = $(this).attr("attr_castGroup_id");
		var positionStatusId = $(this).attr("attr_positionStatus_id");
		if($(this).hasClass("glyphicon-minus"))
		{
			getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,"expand");
		}else{
			getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,"close");
		}
	});
	
	function getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,actionType){
	
		if(actionType == "close"){
			 return;
		}
		var jsObj={
		positionId 			: positionId,    
		boardLevelId 		: boardLevelId,  
		deptId				: deptId,
		boardId				: boardId,
		castegroupId    	: castegroupId,      
		positionStatusId 	: positionStatusId,   
		stateId				: stateId,    
		districtId			: districtId
		}
		$.ajax({
			type:'GET',
			url:'getPositionsForDistrictAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){  
				buildPositionsForDistrict(result,sectionId);  			
			}
		}); 
	}
	
	function buildPositionsForDistrict(result,sectionId){
		//<tr class="showHideTr"> 
		var str = '';
		if(result != null && result.length >0){
        str+='<td colspan="10" class="pad_15">';  
        str+='<table class="table table-condensed table-striped" >';
        str+='<thead class="text-capital" style="width:100px;">';
        str+='<th style="width:200px;">Position</th>';  
        str+='<th style="width:200px;">total positions</th>';    
        str+='<th style="width:100px;">M</th>';
        str+='<th style="width:100px;">F</th>';
		for(var i in result[0].ageList){
        str+='<th style="width:100px;" id="'+result[0].ageList[i].ageRangeId+'">'+result[0].ageList[i].ageRange+'</th>';
		}
		str+='<th style="width:100px;"></th>';
        str+='</thead>';
        str+='<tbody class="text-capital">';
		for(var j in result){
        str+='<tr style="width:100px;">';
		str+='<td id="'+result[j].statusId+'">'+result[j].statusName+'</td>';
		str+='<td>'+result[j].totalCnt+'</td>';
        str+='<td>'+result[j].femaleCount+'</td>';
        str+='<td>'+result[j].maleCount+'</td>';
		for(var k in result[i].ageList){
        str+='<td>'+result[i].ageList[k].ageCount+'</td>';
		}
		str+='<td></td>';
        str+='</tr>';
		}
		str+='</tbody>';
        str+='</table>';
        str+='</td>';
		}
		
		$("#"+sectionId).html(str);  
        //str+='</tr>
                                                    
	}
function buildCasteGroupWiseChart(result){
	$("#casteGrpDivId").show();
	var jsonDataArr=[];
	for(var i in result){
		if(result[i].percentage != 0){
			jsonDataArr.push([''+result[i].name+'',parseFloat(result[i].percentage)]);
		}
	}
	if(jsonDataArr.length >0){
		
      $('#casteGroup').highcharts({
		colors:['#E58D45','#867DC0','#65A7E1','#7DC1C2'],
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45
            }
        },
        plotOptions: {
            pie: {
                innerSize: 100,
                depth: 45
            }
        },
		tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        series: [{
            name: 'CASTE GROUP',
            data: jsonDataArr
        }]
    });
	}else{
		$('#casteGroup').html('<p>No Data Available</p>')
	}	
}
function buildCasteWiseCountsChart(result){
	$("#casteGrpDivId").show();
	var casteNamesArr=[];
	var castePercArr=[];
	for(var i in result){
		if(result[i].name != null){
		casteNamesArr.push([''+result[i].name+'']);
		}
		//if(result[i].percentage != null){
			castePercArr.push([parseFloat(result[i].percentage)]);
		//}
	}
	//console.log(casteNamesArr);
	//console.log(castePercArr);
	if(casteNamesArr.length >0){
	 $('#casteWisePositions').highcharts({
        chart: {
            type: 'bar'
        },
        xAxis: {
            categories: casteNamesArr,
            title: {
                text: null
            }
        },

        yAxis: {
            min: 0,
            title: {
                text: 'POSITIONS',
                align: 'high'
            },
            labels: {
                overflow: 'justify',
              formatter: function() {
					return this.value+"%";
				}
            }
        },
        tooltip: {
            valueSuffix: '%',
			// pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 80,
            floating: true,
            borderWidth: 1,
            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'CASTE',
            data: castePercArr
        }]
    });
	
	}else{
		$('#casteWisePositions').html('<p>No Data Available</p>');
	}
}
	