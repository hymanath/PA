 var globalStateId=1;	 
   $(document).on("click","#myonoffswitch",function(){
	   $("#chartdiv").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
		if($(this).prop('checked')){
		globalStateId =1;
		getLocationWiseCastePositionCount("overall",2,globalStateId,postionId,0);
		getLocationWiseCasteGroupPositionCount("overallCasteGroup",2,globalStateId,0);
		getPositionAndApplicationDetailsCntPositionWise(0,2,"position",globalStateId);
		getPositionAndApplicationDetailsCntLocationWise(0,2,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId",globalStateId);
		//getNominatedCandidateGroupByDistrict(0,2,0,0,0,0,"state");
      //  getOverAllTotalCountsByPosition(0,2,0,0,0,0,globalStateId);
	    getCasteGroupWiseCountsByPosition(0,2,0,0,0,0,globalStateId);
	    getCasteWiseCountsByPosition(0,0,0,2,0,0,globalStateId);
		$( ".overviewUlCls li:nth-child(1) a" ).trigger("click");	
		}else{
		globalStateId = 36;
		getLocationWiseCastePositionCount("overall",2,globalStateId,postionId,0);
		getLocationWiseCasteGroupPositionCount("overallCasteGroup",2,globalStateId,0);
		getPositionAndApplicationDetailsCntPositionWise(0,2,"position",globalStateId);
		getPositionAndApplicationDetailsCntLocationWise(0,2,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId",globalStateId);
		//getNominatedCandidateGroupByDistrict(0,2,0,0,0,0,"state");
        //getOverAllTotalCountsByPosition(0,2,0,0,0,0,globalStateId);
	    getCasteGroupWiseCountsByPosition(0,2,0,0,0,0,globalStateId);
	    getCasteWiseCountsByPosition(0,0,0,2,0,0,globalStateId);
		$( ".overviewUlCls li:nth-child(1) a" ).trigger("click");	
		}
	});
$(document).ready(function(){
	$("#chartdiv").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	getLocationWiseCastePositionCount("overall",2,globalStateId,postionId,0);
    getLocationWiseCasteGroupPositionCount("overallCasteGroup",2,globalStateId,0);
	getPositionAndApplicationDetailsCntPositionWise(0,2,"position",globalStateId);
	getPositionAndApplicationDetailsCntLocationWise(0,2,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId",globalStateId);
	getCastGroupList();
	//getApplicationStatusList();
	getPositionList();
	getLocationLevelList();
	getDepartmentList(2);   
	getBoardList(0);
	//getNominatedCandidateGroupByDistrict(0,2,0,0,0,0,"state");
    //getOverAllTotalCountsByPosition(0,2,0,0,0,0,globalStateId);
	getCasteGroupWiseCountsByPosition(0,2,0,0,0,0,globalStateId);
	getCasteWiseCountsByPosition(0,0,0,2,0,0,globalStateId);
});
$(document).on("click",".castePositionCls",function(){
		$('.selectCls').removeClass('active');
		$(this).closest('li').addClass('active');
		
	var index = $(this).closest('li').attr('data-slick-index');
	var hrefId = $(this).attr("attr_href_id");
	var levelId = $(this).attr("attr_level_value");
	getLocationWiseCastePositionCount(hrefId,levelId,globalStateId,postionId,index);
});
$(document).on("click",".casteGroupCls",function(){
		$('.castGrupCls').removeClass('active');
		$(this).closest('li').addClass('active');
		
	var index = $(this).closest('li').attr('data-slick-index');
	var hrefId = $(this).attr("attr_href_id");
	var levelId = $(this).attr("attr_level_value");
	getLocationWiseCasteGroupPositionCount(hrefId,levelId,globalStateId,index);
});
 
 function getLocationWiseCastePositionCount(hrefId,levelId,stateId,postionId,index){
	
    $("#castePostionDivId").html(' <img style="margin-left: 255px;height:20px;widht:20px;" src="images/icons/loading.gif">');
	var localLevelId = levelId;
	console.log(index);
	if(index == 0)
		localLevelId = 0;
  var jsObj={
        LocationLevelId : localLevelId,
		positionId      : postionId,
		stateId         : stateId
      };
      $.ajax({
         type:'GET',
         url:'getLocationWiseCastePositionCountAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  //alert(12345);
		  if(result != null && result.length > 0){
			  buildCasteWisePositionRslt(result,hrefId);   
		  }else{
			 $("#castePostionDivId").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>"); 
		  }
    });
 }
 function buildCasteWisePositionRslt(result,hrefId){
	 
	 	var str='';
	   str+='<div class="col-md-12 pad_left0">';
         str+='<div class="table-responsive">';
        str+='<table class="table table-bordered dataTableCaste" style="margin:0px !important" id ="cstePstionTableDetilsId">';
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
					if(positionList[j].count == 0){
						str+='<td attr_pstn_id='+result[i].positionList[j].positionId+' attr_caste_state_id ='+result[i].casteId+' class="" style ="color:green;font-weight:bold;" attr_pstn_name='+result[i].positionList[j].positionName+' attr_caste_state_name =\''+result[i].casteName+'\'>'+positionList[j].count+'</td>'; 
					}else{
						str+='<td attr_pstn_id='+result[i].positionList[j].positionId+' attr_caste_state_id ='+result[i].casteId+' class="castePstnCls" style ="cursor:pointer;color:green;font-weight:bold;" attr_pstn_name='+result[i].positionList[j].positionName+' attr_caste_state_name =\''+result[i].casteName+'\'><u>'+positionList[j].count+'</u></td>';
					}
				 }
				str+='</tr>';
			 }
		}
	    str+='</tbody>';
		str+='</table>';
        str+='</div>';
        str+='</div>';
       $("#castePostionDivId").html(str);
         $(".dataTableCaste").dataTable({
	      "sDom": '<"top">rt<"bottom"><"clear"iflp>',
			"scrollY":        "320px",
			"scrollX":        "200px",
			"scrollCollapse": true,
			"paging":         false
        });  
 }

 function getLocationWiseCasteGroupPositionCount(hrefId,LocationLevelId,stateId,index){
	 
	 $("#casteGroupPostionDivId").html(' <img style="margin-left: 255px;height:20px;widht:20px;" src="images/icons/loading.gif">');
	 var localLevelId = LocationLevelId;
	if(index == 0)
		localLevelId = 0;
  var jsObj={
        LocationLevelId : localLevelId,
		positionId      : postionId,
		stateId         : stateId
      };

      $.ajax({
         type:'GET',
         url:'getLocationWiseCasteGroupPositionCountAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  if(result != null && result.length > 0){
			  buildCasteGroupWisePositionRslt(result,hrefId);
		  }else{
		      $("#casteGroupPostionDivId").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>");			  
		  }
    });	
 }
 function buildCasteGroupWisePositionRslt(result,hrefId){
	 var str='';
      if(result[0].casteList.length==0){
		str+='<span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span>'; 
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
					if(casteList[j].count == 0){
						str+='<td attr_pstn_id='+result[i].positionId+'  attr_caste_category_id ='+casteList[j].casteId+' class="" style ="color:green;font-weight:bold;" attr_pstn_name='+result[i].positionName+' attr_caste_category_name =\''+casteList[j].casteName+'\'>'+casteList[j].count+'</td>';
					}else{
						str+='<td attr_pstn_id='+result[i].positionId+'  attr_caste_category_id ='+casteList[j].casteId+' class="casteCategryPstnCls" style ="cursor:pointer;color:green;font-weight:bold;" attr_pstn_name='+result[i].positionName+' attr_caste_category_name =\''+casteList[j].casteName+'\'><u>'+casteList[j].count+'</u></td>';
					}
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
		var locationLevelId = $("#locationLevelId").val();		
		var locationLevelName = $("#locationLevelId option:selected").text();  
		if(locationLevelId==0){
		 $(".locationCls").html("All location level");	
		}else{
		 $(".locationCls").html(""+locationLevelName+" Level");		
		}
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
		if(levelId == null){
		levelId = 2;
		}
		deptId = $("#departmentId").val();
		boardId = $("#corporationId").val();
		casteGroupId =$("#casteGroupId").val();
		applStatusId = $("#positionStatusId").val();
		getOverAllTotalCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,globalStateId);
		getCasteGroupWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,globalStateId);
		getCasteWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,globalStateId);
	} 
	
	function getOverAllTotalCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId,stateId){
		$("#totalCasteId").html("  <img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
		$("#totalAgeWiseId").html(" <img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'> ");
		
		$("#totalFemaleId").html(" <img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
		$("#totalMaleId").html(" <img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	    $("#totalMaleFemaleId").html(" <img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
		var jsObj={
		positionId  : positionId,
		levelId     : levelId,
		deptId      : deptId,
		boardId     : boardId,
		casteGroupId: casteGroupId,
		applStatusId: applStatusId,
		stateId     : stateId
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
				$("#totalCasteId").html('<p><span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span></p>');
				$("#totalAgeWiseId").html("<p>No Data Available</p>");
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
		$("#casteAndAgeWiseId").html(" <img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
		$("#casteGroup").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'> ");
		//$("#casteGrpDivId").hide();
		var jsObj={
		positionId  : positionId,
		levelId     : levelId,
		deptId      : deptId,
		boardId     : boardId,
		casteGroupId: casteGroupId,
		applStatusId: applStatusId,
		stateId     : stateId
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
				$("#casteAndAgeWiseId").html('<p><span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span></p>');
				$('#casteGroup').html('<p><span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span></p>');
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
							if(result[i].applicatnStatsList[j].statusCount == 0){
								str+='<td class="" attr_gender_type="" attr_age_range_id="0" attr_cstGrup_id="'+result[i].id+'" attr_caste_categry_name="'+result[i].name+'" style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].statusCount+'</td>';
							}else{
								str+='<td class="cateGrupCls" attr_gender_type="" attr_age_range_id="0" attr_cstGrup_id="'+result[i].id+'" attr_caste_categry_name="'+result[i].name+'" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].statusCount+'</u></td>';
							}
							if(result[i].applicatnStatsList[j].statusCount == 0){
								str+='<td class="" attr_gender_type="M" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id="0" style="color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].maleCount+'</u></td>';
							}else{
								str+='<td class="cateGrupCls" attr_gender_type="M" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id="0" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].maleCount+'</u></td>';
							}
							if(result[i].applicatnStatsList[j].statusCount == 0){
								str+='<td class="" attr_gender_type="F" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id="0" style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].femaleCount+'</td>';
							}else{
								str+='<td class="cateGrupCls" attr_gender_type="F" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id="0" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].femaleCount+'</u></td>';
							}
						}
						else{
							if(result[i].applicatnStatsList[j].maleCount == 0){
								str+='<td class="" attr_gender_type="M" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id='+result[i].applicatnStatsList[j].statusId+' style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].maleCount+'</td>';
							}else{
								str+='<td class="cateGrupCls" attr_gender_type="M" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id='+result[i].applicatnStatsList[j].statusId+' style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].maleCount+'</u></td>';
							}
							if(result[i].applicatnStatsList[j].femaleCount == 0){
								str+='<td class="" attr_gender_type="F" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id='+result[i].applicatnStatsList[j].statusId+' style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].femaleCount+'</td>';
							}else{
								str+='<td class="cateGrupCls" attr_gender_type="F" attr_cstGrup_id="'+result[i].id+'" attr_age_range_id='+result[i].applicatnStatsList[j].statusId+' style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].femaleCount+'</u></td>';
							}
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
		$("#casteNameWiseTotlaCntsId").html(" <img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
		$("#casteWisePositions").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'> ");
		$("#casteGrpDivId").hide();
		$("#casteNameWiseTotlaCntsId").hide();
		var jsObj={
		positionId  : positionId,
		levelId     : levelId,
		deptId      : deptId,
		boardId     : boardId,
		casteGroupId: casteGroupId,
		applStatusId: applStatusId,
		stateId     : stateId
		}
		$.ajax({
			type:'GET',
			url:'getCasteWiseCountsByPositionAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				$("#casteNameWiseTotlaCntsId").show();
				buildCasteWiseCountsByPosition(result,positionId,levelId,deptId,boardId,casteGroupId,applStatusId);
				buildCasteWiseCountsChart(result);
			}else{
				$("#casteNameWiseTotlaCntsId").html('<p><span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span></p>');
				$('#casteWisePositions').html('<p><span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span></p>');
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
							if(result[i].applicatnStatsList[j].statusCount == 0){
								str+='<td style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].statusCount+'</td>';
							}else{
								str+='<td attr_caste_id="'+result[i].id+'" attr_caste_name="'+result[i].name+'" attr_age_range_id="0" attr_gender_type="" class="casteAgeCls" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].statusCount+'</u></td>';
							}
							if(result[i].applicatnStatsList[j].maleCount == 0){
								str+='<td style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].maleCount+'</td>';
							}else{
								str+='<td  attr_caste_id="'+result[i].id+'" attr_caste_name="'+result[i].name+'" attr_age_range_id="0" attr_gender_type="M"  class="casteAgeCls" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].maleCount+'</u></td>';
							}
							if(result[i].applicatnStatsList[j].femaleCount == 0){
								str+='<td  style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].femaleCount+'</td>';
							}else{
								str+='<td td attr_caste_id="'+result[i].id+'" attr_caste_name="'+result[i].name+'" attr_age_range_id="0" attr_gender_type="F" class="casteAgeCls" style="cursor:pointer;color:green;font-weight:bold;"><u>'+result[i].applicatnStatsList[j].femaleCount+'</u></td>';
							}
						}
						else{
							var maleCount=result[i].applicatnStatsList[j].maleCount;
							var femaleCount = result[i].applicatnStatsList[j].femaleCount;
							var count = maleCount+femaleCount;
							if(count == 0){
								str+='<td colspan="2" style="color:green;font-weight:bold;">'+(maleCount+femaleCount)+'</td>';
							}else{
								str+='<td colspan="2" attr_caste_id="'+result[i].id+'" attr_caste_name="'+result[i].name+'" attr_age_range_id="'+result[i].applicatnStatsList[j].statusId+'" attr_gender_type="" class="casteAgeCls" style="cursor:pointer;color:green;font-weight:bold;"><u>'+(maleCount+femaleCount)+'</u></td>';
							}
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
			positionId  : positionId,
			levelId     : levelId,
			deptId      : deptId,
			boardId     : boardId,
			casteGroupId: castegroupId,
			applStatusId: positionStatusId,
			casteId     : casteId,
			stateId     : stateId
		}
		$.ajax({
			type:'GET',
				url:'getCasteWisePositionsCountsByPositionAction.action',  
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){ 
				buildCasteWisePositionsCountsByPosition(result,innerTableId,casteId);
			}
		});
	}
	
	function buildCasteWisePositionsCountsByPosition(result,innerTableId,casteId){
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
							if(result[i].applicatnStatsList[j].statusCount == 0){
								str+='<td style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].statusCount+'</td>';
							}else{
								str+='<td attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'"   attr_caste_id="'+casteId+'" attr_age_range_id="0" attr_gender_type=""  style="cursor:pointer;color:green;font-weight:bold;" class="positionCountCls"><u>'+result[i].applicatnStatsList[j].statusCount+'</u></td>';
							}
							if(result[i].applicatnStatsList[j].maleCount == 0){
								str+='<td style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].maleCount+'</td>';
							}else{
								str+='<td attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'"  attr_caste_id="'+casteId+'" attr_age_range_id="0" attr_gender_type="M"  style="cursor:pointer;color:green;font-weight:bold;" class="positionCountCls"><u>'+result[i].applicatnStatsList[j].maleCount+'</u></td>';
							}
							if(result[i].applicatnStatsList[j].femaleCount == 0){
								str+='<td style="color:green;font-weight:bold;">'+result[i].applicatnStatsList[j].femaleCount+'</td>';
							}else{
								str+='<td attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'"  attr_caste_id="'+casteId+'" attr_age_range_id="0" attr_gender_type="F"  style="cursor:pointer;color:green;font-weight:bold;" class="positionCountCls"><u>'+result[i].applicatnStatsList[j].femaleCount+'</u></td>';
							}
						}else{
							 var totalMaleCount=result[i].applicatnStatsList[j].maleCount;
							 var totalFemaleCount=result[i].applicatnStatsList[j].femaleCount;
							 var count = totalMaleCount+totalFemaleCount;
							 if(count == 0){
								str+='<td style="color:green;font-weight:bold;">'+(totalMaleCount+totalFemaleCount)+'</td>';
							}else{
								str+='<td attr_position_id="'+result[i].id+'" attr_position_name="'+result[i].name+'"  attr_caste_id="'+casteId+'" attr_age_range_id="'+result[i].applicatnStatsList[j].statusId+'" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="positionCountCls"><u>'+(totalMaleCount+totalFemaleCount)+'</u></td>';
							}
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
     str+='<li role="presentation" attr_id="0" class="active postionliCls positinCls0"><a href="#positionoverView" aria-controls="positionoverView" role="tab"  data-toggle="tab">Completed Overview</a></li>';
		for(var i in result){
		  str+='<li attr_id="'+result[i].id+'" class="postionliCls positinCls'+result[i].id+'" role="presentation"><a href="#positon'+i+'" aria-controls="positon'+i+'" role="tab" data-toggle="tab">'+result[i].name+'</li>';	
		}
	str+='</ul>';
	$("#positonDivId").html(str);
}	
function getPositionAndApplicationDetailsCntPositionWise(positionId,locationLevelId,reportType,stateId){
  
  var jsObj={
        locationLevelId : globalLocationLevelId,
		positionId      : positionId,
		reportType      : reportType,
		stateId         : stateId
      }
      $.ajax({
         type:'GET',
         url:'getPositionAndApplicationDetailsCntPositionWiseAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}      
      }).done(function(result){
		     if(result != null){   
				 if(reportType=="position"){
					 if(result.totalPostsCnt > 0)
						buildPostionRslt(result); 
					else
						$("#chartdiv").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>");
				}else if(reportType=="application"){
					 if(result.totalAppReceivedCnt > 0)
						buildApplicationOverviewRslt(result); 
					else
						$("#chartdiv").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>");				     
				}  
		   }
    });		
}
var postionId=0;
$(document).on("click",".postionliCls",function(){
	 postionId= $(this).attr("attr_id");
	 $('.positinCls'+postionId+'').addClass('active');
	
	  $( ".overviewUlCls li:nth-child(1) a" ).trigger("click");	
	  $("#chartdiv").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	
	$("#positionId").val(postionId);
	$("#positionId").trigger("chosen:updated");
	$('#statusDetailsId').trigger('click');
	
	 getPositionAndApplicationDetailsCntPositionWise(postionId,globalLocationLevelId,"position",globalStateId);
     getPositionAndApplicationDetailsCntLocationWise(postionId,globalLocationLevelId,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId",globalStateId);
	 getLocationWiseCastePositionCount("overall",globalLocationLevelId,globalStateId,postionId,0);
	 getLocationWiseCasteGroupPositionCount("overallCasteGroup",globalLocationLevelId,globalStateId,0);

	//getCastGroupList();
	//getApplicationStatusList();
	
	//getLocationLevelList();
	//getDepartmentList(globalLocationLevelId);   
	//getBoardList(0);
});

$(document).on("click",".overviewHrfCls",function(){
	var reportType = $(this).attr("attr_report_type");
	$("#chartdiv").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	getPositionAndApplicationDetailsCntPositionWise(postionId,2,reportType,globalStateId);
});
function buildPostionRslt(result){
	var positionCntArr =[];
	var positionCntPerArr =[];
	var LevelArr=[];
	var circleColorArr=[];
	if(result.totalPostsCnt>0){
		positionCntArr.push(result.totalPostsCnt);
		positionCntPerArr.push(result.totalPostsCntPer);
		LevelArr.push("TOTAL POSTS");
		circleColorArr.push("#FED403");
	}
	if(result.openPostCnt>0){
		positionCntArr.push(result.openPostCnt);
		positionCntPerArr.push(result.openPostCntPer);
		LevelArr.push("OPEN POSTS");
		circleColorArr.push("#E58D45");
	}
	if(result.confirmCntCnt>0){
		positionCntArr.push(result.confirmCntCnt);
		positionCntPerArr.push(result.confirmCntPer);
		LevelArr.push("FINALIZED POSTS");
		circleColorArr.push("#DE685E");
	}
	if(result.goIssuedCnt>0){
		positionCntArr.push(result.goIssuedCnt);
		positionCntPerArr.push(result.goIssuedPer);
		LevelArr.push("G.O ISSUED");
		circleColorArr.push("#65A7E1");
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
		LevelArr.push("RECEIVED");
		circleColorArr.push("#FFD200");
	}
	if(result.rejectedCnt>0){
		applicationCntArr.push(result.rejectedCnt);
		applicationCntPerArr.push(result.rejectedAppPer);
		LevelArr.push("REJECTED");
		circleColorArr.push("#E78C45");
	}
	if(result.shortedListedCndtCnt>0){
		applicationCntArr.push(result.shortedListedCndtCnt);
		applicationCntPerArr.push(result.shortListedCntper);
		LevelArr.push("SHORTLISTED");
		circleColorArr.push("#DC675D");
	}
	if(result.readyForFinalReviewCnt>0){
	    applicationCntArr.push(result.readyForFinalReviewCnt);
		applicationCntPerArr.push(result.readyForFinalReviewCntPer);
		LevelArr.push("READY FOR FINAL REVIEW");
		circleColorArr.push("#66A6E3");	
	}
	if(result.confirmCntCnt>0){
		applicationCntArr.push(result.confirmCntCnt);
		applicationCntPerArr.push(result.completedAppPer);
		LevelArr.push("COMPLETED");
		circleColorArr.push("#5C2D25");
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
var globalLocationLevelId = 2;
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
	 

	 
	$('.castGrupCls,.selectCls').removeClass('active');
	
	$('.statusSCls').removeClass('active');
	$('#postsTabId').addClass('active');
	$("#chartdiv").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	
	globalLocationLevelId = locationLevelId;
	getPositionAndApplicationDetailsCntLocationWise(postionId,locationLevelId," ",collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId,globalStateId);
	
	getLocationWiseCastePositionCount("overall",locationLevelId,globalStateId,postionId,locationLevelId);
    getLocationWiseCasteGroupPositionCount("overallCasteGroup",locationLevelId,globalStateId,locationLevelId);
	getPositionAndApplicationDetailsCntPositionWise(postionId,locationLevelId,"position",globalStateId);
	//getCastGroupList();
	//getApplicationStatusList();
	//getPositionList();
	//getLocationLevelList();
	//getDepartmentList(locationLevelId);   
	//getBoardList(0);
	//getNominatedCandidateGroupByDistrict(postionId,locationLevelId,0,0,0,0,"state");
    getOverAllTotalCountsByPosition(postionId,locationLevelId,0,0,0,0,globalStateId);
	getCasteGroupWiseCountsByPosition(postionId,locationLevelId,0,0,0,0,globalStateId);
	getCasteWiseCountsByPosition(postionId,0,0,locationLevelId,0,0,globalStateId);
	
});
function getPositionAndApplicationDetailsCntLocationWise(positionId,locationLevelId,reportType,collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId,stateId){
	if(locationLevelId==2){
	   $(".stateBodyDivCls").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	   $('#locationLevelId').val(2);
	   
	    if(!$('#allCasteId').hasClass('active'))
			$('#stateCasteId').addClass('active');
		if(!$('#allCasteGrpId').hasClass('active'))
			$('#stateCasteGrpId').addClass('active');	   
	}else if(locationLevelId == 3){
	 $(".districtBodyDivCls").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	 $('#locationLevelId').val(3);
	  $('#districtCasteId').addClass('active');
	  $('#districtCasteGrpId').addClass('active');
	}else if(locationLevelId == 4){
	  $(".constituencyBodyDivCls").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	  $('#locationLevelId').val(4);
	   $('#assemblyCasteId').addClass('active');
	   $('#assemblyCasteGrpId').addClass('active');
	}else if(locationLevelId == 5){
	  $(".manMunCorBodyDivCls").html("<img style='margin-left: 85px;height:20px;widht:20px;' src='images/icons/loading.gif'>");
	  $('#locationLevelId').val(5);
	   $('#mandalCasteId').addClass('active');
	   $('#mandalCasteGrpId').addClass('active');
	}else{
		//$('#allCasteId').addClass('active');
	    //$('#allCasteGrpId').addClass('active');
	}
	$("#locationLevelId").trigger("chosen:updated");
  var jsObj={
        locationLevelId : locationLevelId,
		positionId      : positionId,
		reportType      : reportType,
		stateId         : stateId
      }
      $.ajax({
         type:'GET',
         url:'getPositionAndApplicationDetailsCntLocationWiseAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  $('#statusDetailsId').trigger('click');
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
				str+='<h4 class="panel-tite"><u>Posts</u></h4>'
					str+='<div id="'+postionHighChartId+'" style="height:180px;margin-bottom: 20px;"></div>';
					str+='<ul class="positionsUl" style="margin-top:20px !imortant;">';
						str+='<li class="total"><span class="statusBox"></span>TOTAL POSTS<span class="count pull-right">'+positionRslt[0].totalPostsCnt+'</span></li>';
						str+='<li class="openPosts"><span class="statusBox"></span>OPEN POSTS<span class="count pull-right">'+positionRslt[0].openPostCnt+'</span></li>';
						str+='<li class="finalized"><span class="statusBox"></span>FINALIZED POSTS<span class="count pull-right">'+positionRslt[0].confirmCntCnt+'</span></li>';
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
							str+='<li class="shortedListed"><span class="statusBox"></span>SHORTLISTED<span class="count pull-right">'+applicationRslt[0].shortedListedCndtCnt+'<small class="text-muted">('+applicationRslt[0].shortListedCntper+'%)</small></span></li>';
							str+='<li class="readyForFinalReview"><span class="statusBox"></span>READY FOR FINAL REVIEW<span class="count pull-right">'+applicationRslt[0].readyForFinalReviewCnt+'<small class="text-muted">('+applicationRslt[0].readyForFinalReviewCntPer+'%)</small></span></li>';
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
 headingArr.push('TOTAL POSTS');
 headingArr.push('OPEN POSTS');
 headingArr.push('FINALIZED POSTS');
 headingArr.push('G.O ISSUED');
var jsonDataArr=[];
	jsonDataArr.push({y:parseFloat(positionRslt[0].totalPostsCntPer),color:'#FED501'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].openPostCntPer),color:'#E58D45'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].confirmCntPer),color:'#DD675D'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].goIssuedPer),color:'#65A7E1'})
	buildPositionHighchartLocationWise(headingArr,jsonDataArr,postionHighChartId)
}
function buildPositionHighchartLocationWise(headingArr,jsonDataArr,postionHighChartId){
	$("#"+postionHighChartId).highcharts({
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
                borderWidth: 0,
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
 colorArr.push('#E58D42');	
 colorArr.push('#E58D45');	
 colorArr.push('#DD665D');	
 colorArr.push('#65A7E1');	
 colorArr.push('#FDD501');	 
 var jsonDataArr=[];
  jsonDataArr.push(['RECEIVED',parseFloat(applicationRslt[0].totalAppReceivedCnt)]);
  jsonDataArr.push(['REJECTED',parseFloat(applicationRslt[0].rejectedCnt)]);
  jsonDataArr.push(['SHORTLISTED',parseFloat(applicationRslt[0].shortedListedCndtCnt)]);
  jsonDataArr.push(['READY FOR FINAL REVIEW',parseFloat(applicationRslt[0].readyForFinalReviewCnt)]);  
  jsonDataArr.push(['COMPLETED',parseFloat(applicationRslt[0].confirmCntCnt)]);  
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
		if(locationLevelId == null){
			locationLevelId = 2;
		
		}
		var locationLevel= $("#locationLevelId option:selected").text();
		var deptId = $("#departmentId").val();
		var deptName = $("#departmentId option:selected").text();
		var corporationId = $("#corporationId").val();
		var corporationName = $("#corporationId option:selected").text();
		var castGroupId = $("#casteGroupId").val();
		var casteGroup = $("#casteGroupId option:selected").text();
		var positionStatusId = $("#positionStatusId").val();   
		var locationLevelName = $("#locationLevelId option:selected").text(); 
			if(locationLevelName == ""){
			locationLevelName = "state";
			}
		getNominatedCandidateGroupByDistrict(positionId, locationLevelId, deptId, corporationId, castGroupId, positionStatusId, locationLevelName);
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
		$(".gOssuedCls").html(" All Application Status ");	
		}else{
		$(".gOssuedCls").html(postionStatus+" Applications ");		
		}
	});  
	
	function getNominatedCandidateGroupByDistrict(positionId, locationLevelId, deptId, corporationId, castGroupId, positionStatusId, locationLevelName){
		$("#statePositionId").html("<img style='margin-left: 85px;' src='images/icons/loading.gif'>");
		var positionId = positionId;
		var locationLevelId = locationLevelId;
		var deptId = deptId;
		var corporationId = corporationId;
		var castGroupId = castGroupId;   
		var positionStatusId = positionStatusId;
		var locationLevelName = locationLevelName;  
		var stateId = globalStateId;
		var jsObj={
			positionId : positionId,
			locationLevelId : locationLevelId,    
			deptId : deptId,
			corporationId : corporationId,
			castGroupId : castGroupId,
			positionStatusId : positionStatusId,
			locationLevelName : locationLevelName,
			stateId : stateId   
		} 
		//console.log(jsObj);    
		$.ajax({
			type:'GET',
			url:'getNominatedCandidateGroupByDistAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}    
		}).done(function(result){ 
			if(result != null && result.length > 0){  
				buildStatePositionCluntTable(result,positionId,locationLevelId,deptId,corporationId,castGroupId,positionStatusId,stateId,locationLevelName);
			}else{
				$("#statePositionId").html("<span style='padding:15px;font-weight:bold;'>NO DATA AVAILABLE</span>");
			}
		}); 
	} 
	function buildStatePositionCluntTable(result,positionId,locationLevelId,deptId,corporationId,castGroupId,positionStatusId,stateId,locationLevelName){
		var str = '';
		str+='<table class="table table-condensed" style="border:1px solid #ddd;background-color:#ECF1F5">';  
		str+='<thead class="bg_ef text-capital">';
			str+='<th style="width:200px;" id="locationHeadingId"></th>';
			str+='<th style="width:200px;text-align:center">Finalised Positions Total</th>';  
			str+='<th style="width:100px;">Male</th>';
			str+='<th style="width:100px;">Female</th>';
			str+='<th style="width:100px;">18-29 AGE</th>';        
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
				if(result[i].totalPositions == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].totalPositions+'</td>';
				}else{
					str+='<td attr_age_range_id="0" attr_gender_type="" class="stateCountCls" style="cursor:pointer;color:green;font-weight:bold;" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].totalPositions+'</u></td>';
				}
				if(result[i].maleCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].maleCount+'</td>';
				}else{
					str+='<td attr_age_range_id="0" attr_gender_type="M" style="cursor:pointer;color:green;font-weight:bold;" class="stateCountCls" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].maleCount+'</u></td>';
				}
				if(result[i].femaleCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].femaleCount+'</td>';
				}else{
					str+='<td attr_age_range_id="0" attr_gender_type="F" style="cursor:pointer;color:green;font-weight:bold;" class="stateCountCls" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].femaleCount+'</u></td>';
				}
				if(result[i].firstAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].firstAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="1" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="stateCountCls" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].firstAgeGroupCount+'</u></td>';
				}
				if(result[i].secondAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].secondAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="2" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="stateCountCls" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].secondAgeGroupCount+'</u></td>';
				}
				if(result[i].thirdAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].thirdAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="3" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="stateCountCls" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].thirdAgeGroupCount+'</u></td>';
				}
				if(result[i].fourthAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].fourthAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="4" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="stateCountCls" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].fourthAgeGroupCount+'</u></td>';
				}
				if(result[i].fifthAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].fifthAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="5" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="stateCountCls" attr_location_id="'+result[i].districtId+'" attr_location_name="'+result[i].name+'"><u>'+result[i].fifthAgeGroupCount+'</u></td>';
				}
				str+='<td style="text-align:right;"><i class="glyphicon glyphicon-plus districtCls" attr_id="districtPositionId'+i+'" attr_dist_id="'+result[i].districtId+'" attr_position_id="'+positionId+'" attr_locationLevel_id="'+locationLevelId+'" attr_dept_id="'+deptId+'" attr_corporation_id="'+corporationId+'" attr_castGroup_id="'+castGroupId+'" attr_positionStatus_id="'+positionStatusId+'" attr_state_id="'+stateId+'" attr_location_level_name="'+locationLevelName+'" attr_location_name="'+result[i].name+'"></i></td>';       
			str+='</tr>';
			str+='<tr class="showHideTr" style="display:none" id="districtPositionId'+i+'">';    
			str+='</tr>';
		}
		str+='</tbody>';
		$("#statePositionId").html(str);
		$("#locationHeadingId").html($("#locationLevelId option:selected").text());    
		if(result.length > 20){
			$("#tableschrollId").mCustomScrollbar({setHeight: '440px'});  
		}
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
		var locationLevelName = $(this).attr("attr_location_level_name");  
		var locationName=$(this).attr("attr_location_name");
		
		$("#"+sectionId+"").show();
		$("#"+sectionId+"").html("<img style='margin-left: 85px;widht:30px;height:30px;' src='images/icons/loading.gif'>");
		
		
		if($(this).hasClass("glyphicon-minus"))
		{
		$("#"+sectionId+"").show();
		$("#"+sectionId+"").html("<img style='margin-left: 85px;widht:30px;height:30px;' src='images/icons/loading.gif'>");
			getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,locationLevelName,"expand",locationName);    
		}else{
		$("#"+sectionId+"").hide();
		$("#"+sectionId+"").html("<img style='margin-left: 85px;widht:30px;height:30px;display:none;' src='images/icons/loading.gif'>");
			getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,locationLevelName,"close",locationName);
		}
	});
	
	function getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,locationLevelName,actionType,locationName){
	  
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
		locationId			: districtId,  
		locationLevelName 	: locationLevelName 
		}
		$.ajax({
			type:'GET',
			url:'getPositionsForDistrictAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){  
				buildPositionsForDistrict(result,sectionId,districtId,locationName);  			
			}
		}); 
	}
	
	function buildPositionsForDistrict(result,sectionId,districtId,locationName){
		//<tr class="showHideTr"> 
		var str = '';
		if(result != null && result.length >0){
        str+='<td colspan="10" class="pad_15">';  
        str+='<table class="table table-condensed table-striped" >';
        str+='<thead class="text-capital" style="width:100px;">';
        str+='<th style="width:200px;">&nbsp&nbsp&nbspPosition</th>';  
        str+='<th style="width:200px;">total positions</th>';    
        str+='<th style="width:100px;">M</th>';
        str+='<th style="width:100px;">F</th>';
		str+='<th style="width:100px;">18-29</th>';
		str+='<th style="width:100px;">30-39</th>';
		str+='<th style="width:100px;">40-49</th>';
		str+='<th style="width:100px;">50-59</th>';
		str+='<th style="width:100px;">60+</th>';
		
		str+='<th style="width:100px;"></th>';
        str+='</thead>';
        str+='<tbody class="text-capital">';
		for(var i in result){
        str+='<tr style="width:100px;">';
		str+='<td >&nbsp&nbsp&nbsp'+result[i].name+'</td>';
				if(result[i].totalPositions == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].totalPositions+'</td>';
				}else{
					str+='<td attr_age_range_id="0" attr_gender_type="" class="positionContCls" style="cursor:pointer;color:green;font-weight:bold;" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+result[i].name+'" attr_location_name="'+locationName+'"><u>'+result[i].totalPositions+'</u></td>';
				}
				if(result[i].maleCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].maleCount+'</td>';
				}else{
					str+='<td attr_age_range_id="0" attr_gender_type="M" style="cursor:pointer;color:green;font-weight:bold;" class="positionContCls" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+result[i].name+'" attr_location_name="'+locationName+'"><u>'+result[i].maleCount+'</u></td>';
				}
				if(result[i].femaleCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].femaleCount+'</td>';
				}else{
					str+='<td attr_age_range_id="0" attr_gender_type="F" style="cursor:pointer;color:green;font-weight:bold;" class="positionContCls" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+result[i].name+'" attr_location_name="'+locationName+'"><u>'+result[i].femaleCount+'</u></td>';
				}
				if(result[i].firstAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].firstAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="1" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="positionContCls" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+locationName+'"><u>'+result[i].firstAgeGroupCount+'</u></td>';
				}
				if(result[i].secondAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].secondAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="2" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="positionContCls" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+result[i].name+'" attr_location_name="'+locationName+'"><u>'+result[i].secondAgeGroupCount+'</u></td>';
				}
				if(result[i].thirdAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].thirdAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="3" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="positionContCls" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+result[i].name+'" attr_location_name="'+locationName+'"><u>'+result[i].thirdAgeGroupCount+'</u></td>';
				}
				if(result[i].fourthAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].fourthAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="4" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="positionContCls" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+result[i].name+'" attr_location_name="'+locationName+'"><u>'+result[i].fourthAgeGroupCount+'</u></td>';
				}
				if(result[i].fifthAgeGroupCount == 0){
					str+='<td style="color:green;font-weight:bold;">'+result[i].fifthAgeGroupCount+'</td>';
				}else{
					str+='<td attr_age_range_id="5" attr_gender_type="" style="cursor:pointer;color:green;font-weight:bold;" class="positionContCls" attr_position_id="'+result[i].districtId+'" attr_location_id="'+districtId+'" attr_position_name="'+result[i].name+'" attr_location_name="'+locationName+'"><u>'+result[i].fifthAgeGroupCount+'</u></td>';
				}		
		/*str+='<td>'+result[i].totalPositions+'</td>';
        str+='<td>'+result[i].maleCount+'</td>';
        str+='<td>'+result[i].femaleCount+'</td>';
		str+='<td>'+result[i].firstAgeGroupCount+'</td>';
		str+='<td>'+result[i].secondAgeGroupCount+'</td>';
		str+='<td>'+result[i].thirdAgeGroupCount+'</td>';
		str+='<td>'+result[i].fourthAgeGroupCount+'</td>';
		str+='<td>'+result[i].fifthAgeGroupCount+'</td>'; */   
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
		$('#casteGroup').html('<p><span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span></p>')
	}	
}
function buildCasteWiseCountsChart(result){
	if(result!= null && result.length > 10){
	var highChartDivHight = result.length*20;
	$("#casteWisePositions").height(highChartDivHight);	
	}else{
	$("#casteWisePositions").height(220);		
	}
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
		$('#casteWisePositions').html('<p><span style="padding:15px;font-weight:bold;">NO DATA AVAILABLE</span></p>');
	}
}
$(document).on("click",".castePstnCls",function(){
var positionId = $(this).attr("attr_pstn_id");
var positionName = $(this).attr("attr_pstn_name").toUpperCase();
var casteStateId = $(this).attr("attr_caste_state_id");
var casteStateNAme = $(this).attr("attr_caste_state_name").toUpperCase();
var casteCategoryId = 0;
var departmntId = 0;
var corporationId = 0;
var ageRangeTypeId = 0;
var genderType = "";
var boardLevelId;
var boardLevel;
var postStatusIdsLst = [5,7];
var locationId =0;

$(".casteWiseDetaislCls li").each(function(){
	if($(this).hasClass("active")){
		 boardLevelId = $(this).find("a").attr("attr_level_value");
		 boardLevel = $(this).find("a").attr("attr_heading");
		 var index = $(this).closest('li').attr('data-slick-index');
		 if(index==0){
		 boardLevelId =0;
		 boardLevel="OVERALL"		 
		 } 
		 }
});

getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId);
$(".modelHeading").html("");
$(".modelHeading").html(boardLevel+ " " +casteStateNAme+ " CASTE - " +positionName+ " POSITION - CANDIDATES" );

$("#nominatedPostCandidateDetailsId").html("");
$("#nominatedCandadteModalId").modal("show");

});
$(document).on("click",".casteCategryPstnCls",function(){
var positionId = $(this).attr("attr_pstn_id");
var positionName = $(this).attr("attr_pstn_name").toUpperCase();
var casteCategoryName = $(this).attr("attr_caste_category_name");
var casteCategoryId = $(this).attr("attr_caste_category_id");
var casteStateId = 0;
var departmntId = 0;
var corporationId = 0;
var ageRangeTypeId = 0;
var genderType = "";
var boardLevelId;
var boardLevel;
var postStatusIdsLst = [5,7];
var locationId = 0;

$(".casteCategryWiseDetaislCls li").each(function(){
	if($(this).hasClass("active")){
		 boardLevelId = $(this).find("a").attr("attr_level_value");
		 boardLevel = $(this).find("a").attr("attr_heading");
		 var index = $(this).closest('li').attr('data-slick-index');
		 if(index==0){
		 boardLevelId =0;
		boardLevel="OVERALL";			 
		 } 
		 }
});
getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId);
$(".modelHeading").html("");
$(".modelHeading").html(boardLevel+ " " +casteCategoryName+ " CASTE CATEGORY- " +positionName+ " POSITION - CANDIDATES" );
$("#nominatedPostCandidateDetailsId").html("");
$("#nominatedCandadteModalId").modal("show");
});

function getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId){
$("#loadingImgId").html("<img style='margin-left: 85px;widht:30px;height:30px;' src='images/icons/loading.gif'>");
  var jsObj={
        stateId : globalStateId,
		casteStateId : casteStateId,
		positionId : positionId,
		boardLevelId : boardLevelId,
		postStatusIdsLst : postStatusIdsLst,
		casteCategoryId :casteCategoryId,
		ageRangeTypeId : ageRangeTypeId,
		genderType : genderType,
		departmentId :departmntId,
		corporationId :corporationId,
		locationId:locationId
		
      }
      $.ajax({
         type:'GET',
         url:'getNominatedPostCandidateDetilsAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}      
      }).done(function(result){
    	  if(result != null){
    		  buildNominatedCandidateDetails(result)
    	  }else {
			  $("#nominatedPostCandidateDetailsId").html('No Data AVailabale');
		  }
		     
    });		
}

function buildNominatedCandidateDetails(result){
$("#loadingImgId").html("<img style='margin-left: 85px;widht:30px;height:30px;display:none;' src='images/icons/loading.gif'>");
	var str ='';
	str+='<table class="table table-bordered nomtedPstCndteId" id ="nomtedPstCndteId">';
	str +='<thead>';
	str +='<th>IMAGE</th>';
	str +='<th>CANDIDATE NAME</th>';
	str +='<th>MOBILE NO</th>';
	str +='<th>RELATIVE NAME</th>';
	str +='<th>MEMBERSHIPNO</th>';
	str +='<th>DISTRICT NAME</th>';
	str +='<th>CONSTITUENCY NAME</th>';
	str +='<th>DESIGNATION</th>';
	str +='</thead>';
	str +='<tbody>';
	for(var i in result){
		str+='<tr>';
		var name =result[i].name;
		var mobNo = result[i].mobileNo;
		var membershipNo = result[i].membershipNo;
		if(membershipNo != null && membershipNo !=0){
			str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[i].imageUrl+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
		}else{
			str+='<td><img src="http://www.mytdp.com/images/not_cadre_images/'+result[i].imageUrl+'" onerror="setDefaultImage(this);" style="width: 50px; height: 50px;"></img></td>';
		}
		if(name != null){
			str+='<td>'+result[i].name+'</td>';
		}else{
			str+='<td>'-'</td>';
		}
		
		if(mobNo != null && mobNo != 0){
			str+='<td>'+mobNo+'</td>';
		}else {
			str +='<td>-</td>';
		}
		if(result[i].relativeName != null && result[i].relativeName != ""){
			str+='<td>'+result[i].relativeName+'</td>';
		}else{
			str +='<td>-</td>';
		}
		if(membershipNo != null && membershipNo !=0){
			str+='<td>'+membershipNo+'</td>';
		}else {
			str +='<td>-</td>';
		}
		
		if(result[i].districtName != null)
		str+='<td>'+result[i].districtName+'</td>';
		
		if(result[i].constituencyName != null)
		str+='<td>'+result[i].constituencyName+'</td>';
		
		if(result[i].publicRepr != null){
			str+='<td>'+result[i].publicRepr+'</td>';
		}else{
			str +='<td>-</td>';
		}
		str +='</tr>';
	}
	str+='</tbody>';
	str +='</table>';
	
	$("#nominatedPostCandidateDetailsId").html(str);
	$(".nomtedPstCndteId").dataTable();
	
}
function setDefaultImage(img){
    img.onerror = "";
    img.src = "images/cadre_images/human.jpg";
    return true;
  }
	
$(document).on("click",".cateGrupCls",function(){
if($("#positionStatusId").val() == 0){
 var postStatusIdsLst = [3,6,5,7];
}else{
 var postStatusIdsLst = [$("#positionStatusId").val()];
}
var statusName = $("#positionStatusId option:selected").text();
var positionId = $("#positionId").val();
var positionName = $("#positionId option:selected").text().toUpperCase();
var casteCategoryName = $(this).attr("attr_caste_categry_name");
var casteCategoryId = $(this).attr("attr_cstGrup_id");
var casteStateId = 0;
var departmntId = $("#departmentId").val();
var corporationId = $("#corporationId").val();
var ageRangeTypeId = $(this).attr("attr_age_range_id");
var genderType = $(this).attr("attr_gender_type");
var boardLevelId = $("#locationLevelId").val();
var boardLevel = $("#locationLevelId option:selected").text().toUpperCase();
var locationId = 0;

getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId);
$(".modelHeading").html("");
$(".modelHeading").html(boardLevel+ " LEVEL " +casteCategoryName+ " CASTE CATEGORY- " +positionName+ " POSITION - CANDIDATES" );

$("#nominatedPostCandidateDetailsId").html("");
$("#nominatedCandadteModalId").modal("show");
});

$(document).on("click",".casteAgeCls",function(){
if($("#positionStatusId").val() == 0){
 var postStatusIdsLst = [3,6,5,7];
}else{
 var postStatusIdsLst = [$("#positionStatusId").val()];
}
var statusName = $("#positionStatusId option:selected").text();
var positionId = $("#positionId").val();
var positionName = $("#positionId option:selected").text().toUpperCase();
var casteCategoryName = "";
var casteCategoryId = $("#casteGroupId").val();
var casteStateId =  $(this).attr("attr_caste_id");
var casteName = $(this).attr("attr_caste_name");
var departmntId = $("#departmentId").val();
var corporationId = $("#corporationId").val();
var ageRangeTypeId = $(this).attr("attr_age_range_id");
var genderType = $(this).attr("attr_gender_type");
var boardLevelId = $("#locationLevelId").val();
var boardLevel = $("#locationLevelId option:selected").text().toUpperCase();
var locationId = 0;

getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId);
$(".modelHeading").html("");
$(".modelHeading").html(boardLevel+ " LEVEL " +casteName+ " CASTE - " +positionName+ " POSITION - CANDIDATES" );

$("#nominatedPostCandidateDetailsId").html("");
$("#nominatedCandadteModalId").modal("show");
});

$(document).on("click",".positionCountCls",function(){
if($("#positionStatusId").val() == 0){
 var postStatusIdsLst = [3,6,5,7];
}else{
 var postStatusIdsLst = [$("#positionStatusId").val()];
}
var statusName = $("#positionStatusId option:selected").text();
var positionId = $(this).attr("attr_position_id");
var positionName = $(this).attr("attr_position_name");
var casteCategoryName = "";
var casteCategoryId = $("#casteGroupId").val();
var casteStateId =  $(this).attr("attr_caste_id");
var casteName = $(this).attr("attr_caste_name");
var departmntId = $("#departmentId").val();
var corporationId = $("#corporationId").val();
var ageRangeTypeId = $(this).attr("attr_age_range_id");
var genderType = $(this).attr("attr_gender_type");
var boardLevelId = $("#locationLevelId").val();
var boardLevel = $("#locationLevelId option:selected").text().toUpperCase();
var locationId = 0;

getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId);
$(".modelHeading").html("");
$(".modelHeading").html(boardLevel+ " LEVEL " +casteName+ " CASTE - " +positionName+ " POSITION - CANDIDATES" );

$("#nominatedPostCandidateDetailsId").html("");
$("#nominatedCandadteModalId").modal("show");
});

$(document).on("click",".stateCountCls",function(){
if($("#positionStatusId").val() == 0){
 var postStatusIdsLst = [3,6,5,7];
}else{
 var postStatusIdsLst = [$("#positionStatusId").val()];
}
var statusName = $("#positionStatusId option:selected").text();
var positionId = 0;
var positionName = "";
var casteCategoryName = "";
var casteCategoryId = $("#casteGroupId").val();
var casteStateId =  0;
var casteName = "";
var departmntId = $("#departmentId").val();
var corporationId = $("#corporationId").val();
var ageRangeTypeId = $(this).attr("attr_age_range_id");
var genderType = $(this).attr("attr_gender_type");
var boardLevelId = $("#locationLevelId").val();
var boardLevel = $("#locationLevelId option:selected").text().toUpperCase();
var locationId = $(this).attr("attr_location_id");
var locationName = $(this).attr("attr_location_name").toUpperCase();

getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId);
$(".modelHeading").html("");
$(".modelHeading").html(locationName+ "  CANDIDATES" );

$("#nominatedPostCandidateDetailsId").html("");
$("#nominatedCandadteModalId").modal("show");
});
$(document).on("click",".positionContCls",function(){
if($("#positionStatusId").val() == 0){
 var postStatusIdsLst = [3,6,5,7];
}else{
 var postStatusIdsLst = [$("#positionStatusId").val()];
}
var statusName = $("#positionStatusId option:selected").text();
var positionId = $(this).attr("attr_position_id");
var positionName = $(this).attr("attr_position_name").toUpperCase();
var casteCategoryName = "";
var casteCategoryId = $("#casteGroupId").val();
var casteStateId =  0;
var casteName = "";
var departmntId = $("#departmentId").val();
var corporationId = $("#corporationId").val();
var ageRangeTypeId = $(this).attr("attr_age_range_id");
var genderType = $(this).attr("attr_gender_type");
var boardLevelId = $("#locationLevelId").val();
var boardLevel = $("#locationLevelId option:selected").text().toUpperCase();
var locationId = $(this).attr("attr_location_id");
var locationName = $(this).attr("attr_location_name").toUpperCase();

getNominatedPostCandidateDetils(positionId,casteStateId,boardLevelId,departmntId,corporationId,casteCategoryId,ageRangeTypeId,genderType,postStatusIdsLst,locationId);
$(".modelHeading").html("");
$(".modelHeading").html(locationName+ "  " +positionName+ "  POSITION CANDIDATES" );

$("#nominatedPostCandidateDetailsId").html("");
$("#nominatedCandadteModalId").modal("show");
});