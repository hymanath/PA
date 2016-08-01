$(document).ready(function(){
	getLocationWiseCastePositionCount("overall",0);
    getLocationWiseCasteGroupPositionCount("overallCasteGroup",0);
});
$(document).on("click",".castePositionCls",function(){
	var hrefId = $(this).attr("attr_href_id");
	var levelId = $(this).attr("attr_level_value");
	getLocationWiseCastePositionCount(hrefId,levelId)
});
$(document).on("click",".casteGroupCls",function(){
	var hrefId = $(this).attr("attr_href_id");
	var levelId = $(this).attr("attr_level_value");
	getLocationWiseCasteGroupPositionCount(hrefId,levelId)
	getCastGroupList();
	getApplicationStatusList();
	getPositionList();
	getLocationLevelList();
	getDepartmentList();
	getBoardList();
});
 
 function getLocationWiseCastePositionCount(hrefId,levelId){
   $("#castePostionDivId").html(' ');
  var jsObj={
        LocationLevelId : levelId,
		positionId : 0
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
	    if(result[0].casteList.length==0){
			str+='No DATA AVAILABLE'; 
			$("#castePostionDivId").html(str);
			return;
		 }
	
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
        //str+='</div>';
        //str+='</div>';  
       $("#castePostionDivId").html(str);
        $(".dataTableCaste").dataTable({
	      "sDom": '<"top">rt<"bottom"><"clear"iflp>'
         });	   
      
 }

 function getLocationWiseCasteGroupPositionCount(hrefId,LocationLevelId){
	 
	 $("#casteGroupPostionDivId").html(' ');
  var jsObj={
        LocationLevelId : LocationLevelId,
		positionId : 0
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
		 $(".dataTableCasteGroup").dataTable({
	     "sDom": '<"top">rt<"bottom"><"clear"iflp>'
        });
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
	function getApplicationStatusList(){
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
	}
	function getPositionList(){
		var jsObj={}
		$.ajax({
			type:'GET',
			url:'getPositionListAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
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
					$('#locationLevelId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
				$("#locationLevelId").trigger("chosen:updated");
			}
		});
	}
	function getDepartmentList(){
		var jsObj={}
		$.ajax({
			type:'GET',
			url:'getDepartmentListAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				for(var i in result){
					$('#departmentId').append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
				}
				$("#departmentId").trigger("chosen:updated");
			}
		});
	}  
	function getBoardList(){
		var jsObj={}
		$.ajax({
			type:'GET',
			url:'getBoardListAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
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
		getOverAllTotalCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId);
		getCasteGroupWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId);
	} 
	
	function getOverAllTotalCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId){
		var jsObj={
		positionId:positionId,
		levelId :levelId,
		deptId :deptId,
		boardId :boardId,
		casteGroupId:casteGroupId,
		applStatusId:applStatusId
		}
		$.ajax({
			type:'GET',
			url:'getOverAllTotalCountsByPositionAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null){
				buildOverAllTotalCountsByPosition(result);
			}
			
		});
	}
	
function buildOverAllTotalCountsByPosition(result){
	var totalMFCount=0;
	 for (var i in result.applicatnStatsList){
		if(result.applicatnStatsList[i].statusName =='Female'){		
			totalMFCount=totalMFCount+result.applicatnStatsList[i].statusCount;
			$("#totalFemaleId").html('<p>'+result.applicatnStatsList[i].statusCount+'</p>');
		}
			
		if(result.applicatnStatsList[i].statusName == 'Male'){
					totalMFCount=totalMFCount+result.applicatnStatsList[i].statusCount;
					$("#totalMaleId").html('<p>'+result.applicatnStatsList[i].statusCount+'</p>');
		}			
		}
	   $("#totalMaleFemaleId").html('<p>'+totalMFCount+'</p>');
	   
	   
		var str='';
		str+='<table class="table table-bordered">';
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
		str1+='<table class="table table-bordered">';
		str1+='<thead>';
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
	
	
	function getCasteGroupWiseCountsByPosition(){
		var jsObj={
		positionId:positionId,
		levelId :levelId,
		deptId :deptId,
		boardId :boardId,
		casteGroupId:casteGroupId,
		applStatusId:applStatusId
		}
		
		$.ajax({
			type:'GET',
			url:'getCasteGroupWiseCountsByPositionAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null){
				buildCasteGroupWiseCountsByPosition(result);
			}
			
		});
	
	}
	
	function buildCasteGroupWiseCountsByPosition(result){
		var str='';
		str+='<table>';
		str+='<thead>';
		str+='<tr>'
		str+='<td rowspan="2">Caste Group</td>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total')
					str+='<td colspan="3"> Total </td>'
				else
					str+='<td colspan="2">'+result[0].applicatnStatsList[i].statusName+'</td>'
			}
		}
		str+='</tr>';
		str+='<tr>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<td> T </td>'
					str+='<td> M </td>'
					str+='<td> F </td>'
				}
				else{
					str+='<td> M </td>'
					str+='<td> F </td>'
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
		str+='</tbody>';
		str+='</table>';
		$("#casteAndAgeWiseId").html(str);
		}
		
	
function getPositionsForDistrict(divId){
		var jsObj={
		positionId 			: 1,
		boardLevelId 		: 1,
		deptId				: 1,
		boardId				: 1,
		castegroupId    	: 2,
		positionStatusId 	: 3,
		stateId				: 2,
		districtId			: 23
		}
		$.ajax({
			type:'GET',
			url:'getPositionsForDistrictAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){  
				buildPositionsForDistrict(result,divId);  			
			}
		}); 
	}
	getPositionsForDistrict("districtPositionId");
	function buildPositionsForDistrict(result,divId){
		//<tr class="showHideTr">
		var str = '';
		if(result != null && result.length >0){
        str+='<td colspan="9" class="pad_15">';
        str+='<table class="table table-condensed table-striped">';
        str+='<thead class="text-capital">';
        str+='<th>Position</th>';
        str+='<th>total positions</th>';
        str+='<th>M</th>';
        str+='<th>F</th>';
		for(var i in result[0].ageList){
        str+='<th id="'+result[0].ageList[i].ageRangeId+'">'+result[0].ageList[i].ageRange+'</th>';
		}
        str+='</thead>';
        str+='<tbody class="text-capital">';
		for(var j in result){
        str+='<tr>';
		 str+='<td id="'+result[j].statusId+'">'+result[j].statusName+'</td>';
		 str+='<td>'+result[j].totalCnt+'</td>';
        str+='<td>'+result[j].femaleCount+'</td>';
        str+='<td>'+result[j].maleCount+'</td>';
		for(var k in result[i].ageList){
        str+='<td>'+result[i].ageList[k].ageCount+'</td>';
		}
        str+='</tr>';
		}
		str+='</tbody>';
        str+='</table>';
        str+='</td>';
		}
		
		$("#"+divId).html(str);
        //str+='</tr>
                                                    
	}