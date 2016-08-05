$(document).ready(function(){
	getLocationWiseCastePositionCount("overall",0);
    getLocationWiseCasteGroupPositionCount("overallCasteGroup",0);
	getPositionAndApplicationDetailsCntPositionWise(0,0,"position");
	getPositionAndApplicationDetailsCntLocationWise(0,2,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId");
	getCastGroupList();
	getApplicationStatusList();
	getPositionList();
	getLocationLevelList();
	getDepartmentList();
	getBoardList();
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
	//getPositionList();
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
		getCasteWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId);
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
			}else{
				$("#totalCasteId").html('<p>No Data Available</p>');
				$("#totalAgeWiseId").html('<p>No Data Available</p>');
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
	
	
	function getCasteGroupWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId){
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
		
	
	
	function getCasteWiseCountsByPosition(positionId,levelId,deptId,boardId,casteGroupId,applStatusId){
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
			url:'getCasteWiseCountsByPositionAction.action',  
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null && result.length > 0){
				buildCasteWiseCountsByPosition(result);
				buildCasteWiseCountsChart(result);
			}else{
				$("#casteNameWiseTotlaCntsId").html('<p>No Data Available</p>');
				$('#casteWisePositions').html('<p>No Data Available</p>');
			}
		});
	}
	
	function buildCasteWiseCountsByPosition(result){
		var str='';
		str+='<table class="table table-bordered">';
		str+='<thead>';
		str+='<tr>';
		str+='<td rowspan="2">CASTE NAME</td>';
	if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<td colspan="3">Total</td>'
				}else{
					str+='<td colspan="2">'+result[0].applicatnStatsList[i].statusName+'</td>'
			}
		}
	}
		str+='</td>';
		str+='</tr>';
		str+='<tr>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length>0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<td> T </td>';
					str+='<td> M </td>';
					str+='<td> F </td>';
				}else{
					str+='<td> M </td>';
					str+='<td> F </td>';
				}
			}
		}
		str+='</tr>';
		str+='</thead>';
		str+='<tbody>';
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
							str+='<td>'+result[i].applicatnStatsList[j].maleCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].femaleCount+'</td>';
						}
					       
					}
				}
				str+='<td><i class="glyphicon glyphicon-plus changeIconClass " attr_id="'+result[i].id+'"></i></td>';
			str+='</tr>';
			
			str+='<tr class="tableStrOuterCls" id="tableStrOuterId'+result[i].id+'" style="display:none;">';
			str+='<td colspan="15"><div class="innerTableDivCls" id="tableStrId'+result[i].id+'"></div></td>';
			str+='</tr>';
		}
		str+='</tbody>'
		str+='</table>';
		$("#casteNameWiseTotlaCntsId").html(str);
		
	}
	
	
	function casteWisePositionsCountsByPosition(casteId,actionType){
		 $(".innerTableDivCls").html("");
	     $(".tableStrOuterCls").hide();
		 if(actionType == "close"){
			 return;
		  }
		var jsObj={
			positionId:positionId,
			levelId :levelId,
			deptId :deptId,
			boardId :boardId,
			casteGroupId:casteGroupId,
			applStatusId:applStatusId,
			casteId:casteId
		}
		$.ajax({
			type:'GET',
				url:'getCasteWisePositionsCountsByPositionAction.action',  
				dataType: 'json',
				data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result!=null){ 
				buildCasteWisePositionsCountsByPosition(result,casteId);
			}
		});
	}
	
	function buildCasteWisePositionsCountsByPosition(result,casteId){
		$("#tableStrOuterId"+casteId).show();
		var str='';
		str+='<table class="table table-hover table-bordered">';
		str+='<thead style="background:#ddd;">';
		str+='<tr>';
		str+='<td>Position</td>';
		if(result[0].applicatnStatsList != null && result[0].applicatnStatsList.length > 0){
			for(var i in result[0].applicatnStatsList){
				if(result[0].applicatnStatsList[i].statusName == 'Total'){
					str+='<td>Total</td>';
					str+='<td>M</td>';
					str+='<td>F</td>';
				}else{
					str+='<td >'+result[0].applicatnStatsList[i].statusName+'</td>';
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
						    totalMaleCount=result[i].applicatnStatsList[j].maleCount;
							totalFemaleCount=result[i].applicatnStatsList[j].maleCount;
						if(result[i].applicatnStatsList[j].statusName == 'Total'){
							str+='<td>'+result[i].applicatnStatsList[j].statusCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].maleCount+'</td>';
							str+='<td>'+result[i].applicatnStatsList[j].femaleCount+'</td>';
						}else{
							totalMfCount=totalMaleCount+totalFemaleCount;
							str+='<td>'+totalMfCount+'</td>';	
						}
					}
			}
			str+='</tr>';
			str+='</tbody>';
			str+='</table>';
		}
		$("#tableStrId"+casteId).html(str);
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
function getPositionAndApplicationDetailsCntPositionWise(positionId,locationLevelId,reportType){
   $("#chartdiv").html(' ');
  var jsObj={
        locationLevelId : locationLevelId,
		positionId : positionId,
		reportType : reportType
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
	 getPositionAndApplicationDetailsCntPositionWise(postionId,0,"position");
     getPositionAndApplicationDetailsCntLocationWise(postionId,2,"","stateLevlId","collapseOne","sttLvlPstnHghChrtId","sttLvlApplctnHghChrtId");
});

$(document).on("click",".overviewHrfCls",function(){
	var reportType = $(this).attr("attr_report_type");
	getPositionAndApplicationDetailsCntPositionWise(postionId,0,reportType);
});
function buildPostionRslt(result){
	var totalPostionCnt = result.totalPositionCn;
	var totalPositionCntPer=result.totalPositionCntPer;
	var noCandidateCnt = result.noCandidateCnt;
	var noCandidateCntPer= result.noCandidateCntPer;
	var shortedListedCndtCnt = result.shortedListedCndtCnt;
	var shortListedCntper = result.shortListedCntper;
	var finalReviewCantCnt = result.finalReviewCantCnt;
	var finalReviewPer = result.finalReviewPer;
	var confirmCntCnt = result.confirmCntCnt;
	var confirmCntPern = result.confirmCntPer;
	var goIssuedCnt= result.goIssuedCnt;
	var goIssuedPer = result.goIssuedPer;
	buildPostionGaugeHighChart(totalPostionCnt,noCandidateCnt,shortedListedCndtCnt,
	finalReviewCantCnt,confirmCntCnt,goIssuedCnt,totalPositionCntPer,noCandidateCntPer,shortListedCntper,finalReviewPer,confirmCntPern,goIssuedPer);
}
function buildPostionGaugeHighChart(totalPostionCnt,noCandidateCnt,shortedListedCndtCnt,finalReviewCantCnt,confirmCntCnt,goIssuedCnt,totalPositionCntPer,
noCandidateCntPer,shortListedCntper,finalReviewPer,confirmCntPern,goIssuedPer){
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
    "bands": [{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "100%",
      "innerRadius": "85%"
    }, {
      "color": "#FDD401",
      "startValue": 0,
      "endValue": totalPositionCntPer,
      "radius": "100%",
      "innerRadius": "95%",
      "balloonText": totalPostionCnt
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "88%",
      "innerRadius": "88%"
    }, {
      "color": "#E48D45",
      "startValue": 0,
      "endValue": noCandidateCntPer,
      "radius": "88%",
      "innerRadius": "83%",
      "balloonText": noCandidateCnt
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "75%",
      "innerRadius": "75%"
    }, {
      "color": "#DE675D",
      "startValue": 0,
      "endValue": shortListedCntper,
      "radius": "75%",
      "innerRadius": "70%",
      "balloonText": shortedListedCndtCnt
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "65%",
      "innerRadius": "65%"
    }, {
      "color": "#65A6E2",
      "startValue": 0,
      "endValue": finalReviewPer,
      "radius": "65%",
      "innerRadius": "60%",
      "balloonText":finalReviewCantCnt
    }, {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "50%",
      "innerRadius": "50%"
    }, {
      "color": "#867CC0",
      "startValue": 0,
      "endValue": confirmCntPern,
      "radius": "50%",
      "innerRadius": "45%",
      "balloonText": confirmCntCnt
    }, {
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "35%",
      "innerRadius": "35%"
    }, {
      "color": "#7DC0C2",
      "startValue": 0,
      "endValue": goIssuedPer,
      "radius": "35%",
      "innerRadius": "30%",
      "balloonText": goIssuedCnt
    }]
  }],
  "allLabels": [{
    "text": "Total",
    "x": "49%",
    "y": "5%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "No Candidate ",
    "x": "49%",
    "y": "10%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "Short Listed",
    "x": "49%",
    "y": "15%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "Ready For Review",
    "x": "49%",
    "y": "20%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "Finalized",
    "x": "49%",
    "y": "26%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "G.O.Issued",
    "x": "49%",
    "y": "33%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }],
  "export": {
    "enabled": false
  }
});
}

function buildApplicationOverviewRslt(result){
	var totalAppReceivedCnt = result.totalAppReceivedCnt;
	var totalAppRecevidPer = result.totalAppRecevidPer;
	var rejectedCnt = result.rejectedCnt;
	var rejectedAppPer = result.rejectedAppPer;
	var inProgressCnt = result.inProgressCnt;
	var inProgressAppPer = result.inProgressAppPer;
	var confirmCntCnt = result.confirmCntCnt;
	var confirmCntPer = result.completedAppPer;
	buildAppOverviewHighCharts(totalAppReceivedCnt,totalAppRecevidPer,rejectedCnt,rejectedAppPer,inProgressCnt,inProgressAppPer,confirmCntCnt,confirmCntPer);
}
function buildAppOverviewHighCharts(totalAppReceivedCnt,totalAppRecevidPer,rejectedCnt,rejectedAppPer,inProgressCnt,inProgressAppPer,confirmCntCnt,confirmCntPer){
	var gaugeChart = AmCharts.makeChart("chartdiv1", {
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
    "bands": [{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "100%",
      "innerRadius": "85%"
    }, {
      "color": "#FDD401",
      "startValue": 0,
      "endValue": totalAppRecevidPer,
      "radius": "100%",
      "innerRadius": "95%",
      "balloonText": totalAppReceivedCnt
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "88%",
      "innerRadius": "88%"
    }, {
      "color": "#E48D45",
      "startValue": 0,
      "endValue": rejectedAppPer,
      "radius": "88%",
      "innerRadius": "83%",
      "balloonText": rejectedCnt
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "75%",
      "innerRadius": "75%"
    }, {
      "color": "#DE675D",
      "startValue": 0,
      "endValue": inProgressAppPer,
      "radius": "75%",
      "innerRadius": "70%",
      "balloonText": inProgressCnt
    },{
      "color": "#fff",
      "startValue": 0,
      "endValue": 100,
      "radius": "65%",
      "innerRadius": "65%"
    }, {
      "color": "#65A6E2",
      "startValue": 0,
      "endValue": confirmCntPer,
      "radius": "65%",
      "innerRadius": "60%",
      "balloonText":confirmCntCnt
    }]
  }],
  "allLabels": [{
    "text": "Received",
    "x": "49%",
    "y": "5%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "Rejected",
    "x": "49%",
    "y": "10%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "In Progress",
    "x": "49%",
    "y": "15%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }, {
    "text": "Completed",
    "x": "49%",
    "y": "20%",
    "size": 10,
    "bold": true,
    "color": "#333",
    "align": "right"
  }],
  "export": {
    "enabled": false
  }
});
}
$(document).on("click",".locationLevelcollapseCls",function(){
	var  collapseLevelId = $(this).attr("attr_collapse_level_Id");
	var collapseHrefId = $(this).attr("attr_collapse_href_id");
	var locationLevelId = $(this).attr("attr_level_Id");
	var postionHighChartId = $(this).attr("attr_postion_highChart_id");
	var appHighChartId = $(this).attr("attr_app_highChart_id");
	 getPositionAndApplicationDetailsCntLocationWise(postionId,locationLevelId," ",collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId)
	
});
function getPositionAndApplicationDetailsCntLocationWise(positionId,locationLevelId,reportType,collapseLevelId,collapseHrefId,postionHighChartId,appHighChartId){
  var jsObj={
        locationLevelId : locationLevelId,
		positionId : positionId,
		reportType : reportType
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
					str+='<div id="'+postionHighChartId+'" style="height:150px;"></div>';
					str+='<ul class="positionsUl" style="margin-top:20px !imortant;">';
						str+='<li class="total"><span class="statusBox"></span>TOTAL POSITIONS<span class="count pull-right">'+positionRslt[0].totalPositionCn+'</span></li>';
						str+='<li class="noCandidate"><span class="statusBox"></span>NO CANDIDATE POSITIONS<span class="count pull-right">'+positionRslt[0].noCandidateCnt+'</span></li>';
						str+='<li class="shortListed"><span class="statusBox"></span>SHORT LISTED POSITIONS<span class="count pull-right">'+positionRslt[0].shortedListedCndtCnt+'</span></li>';
					   // str+='<li class="notStarted"><span class="statusBox"></span>Not Started<span class="count">1200</span></li>';
						str+='<li class="readyForReview"><span class="statusBox"></span>READY FOR REVIEW POSITIONS<span class="count pull-right">'+positionRslt[0].finalReviewCantCnt+'</span></li>';
						str+='<li class="finalised"><span class="statusBox"></span>FINALIZED POSITIONS<span class="count pull-right">'+positionRslt[0].confirmCntCnt+'</span></li>';
						str+='<li class="goIssued"><span class="statusBox"></span>GO ISSUED POSITIONS<span class="count pull-right">'+positionRslt[0].goIssuedCnt+'</span></li>';
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
/* 		if(collapseLevelId != null && collapseLevelId=="stateLevlId"){
		  $("#collapseOne").collapse('show');
		} */
	  buildLocationWisePostionHighCahrtRslt(positionRslt,postionHighChartId);	
	}
	 if(applicationRslt != null && applicationRslt.length > 0){
		 buildLocatinWiseApplicationHighChartsRslt(applicationRslt,appHighChartId);  
	}  
}
function buildLocationWisePostionHighCahrtRslt(positionRslt,postionHighChartId){
var headingArr=[];
 headingArr.push('TOTAL POSITIONS');
 headingArr.push('NO CANDIDATE POSITIONS');
 headingArr.push('SHORT LISTED POSITIONS');
 headingArr.push('READY FOR REVIEW POSITIONS');
 headingArr.push('FINALIZED POSITIONS');
 headingArr.push('GO ISSUED POSITIONS');
var jsonDataArr=[];
	jsonDataArr.push({y:parseFloat(positionRslt[0].totalPositionCntPer),color:'#fff2b3'})
    jsonDataArr.push({y:parseFloat(positionRslt[0].noCandidateCntPer),color:'#f1c5a1'})
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
 colorArr.push('#E58D45');	
 colorArr.push('#867DC0');	
 colorArr.push('#65A7E1');	
 colorArr.push('#7DC1C2');	
 var jsonDataArr=[];
  jsonDataArr.push(['Received',parseFloat(applicationRslt[0].totalAppReceivedCnt)]);
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
		var locationLevelId = $("#locationLevelId").val();
		var deptId = $("#departmentId").val();
		var corporationId = $("#corporationId").val();
		var castGroupId = $("#casteGroupId").val();
		var positionStatusId = $("#positionStatusId").val(); 
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
				str+='<td style="text-align:right;"><i class="glyphicon glyphicon-plus changeIconClass districtCls" attr_id="districtPositionId'+i+'" attr_dist_id="'+result[i].districtId+'" attr_position_id="'+positionId+'" attr_locationLevel_id="'+locationLevelId+'" attr_dept_id="'+deptId+'" attr_corporation_id="'+corporationId+'" attr_castGroup_id="'+castGroupId+'" attr_positionStatus_id="'+positionStatusId+'" attr_state_id="'+stateId+'"></i></td>';     
			str+='</tr>';
			str+='<tr class="showHideTr" style="display:none" id="districtPositionId'+i+'">';    
			str+='</tr>';
		}
		str+='</tbody>';
		$("#statePositionId").html(str);
	}
	$(document).on('click','.districtCls',function(){
		$(".showHideTr").hide();
		$(".districtCls").removeClass("glyphicon-plus").addClass("glyphicon-plus").removeClass("glyphicon-minus");
		$(this).closest('tr').next('tr.showHideTr').toggle();
		$(this).toggleClass("glyphicon-minus");
		var distId = $(this).attr("attr_id");
		if($(this).hasClass("glyphicon-minus"))
		{
			getPositionsForDistrict(distId,"expand");
		}else{
			getPositionsForDistrict(distId,"close");
		}
		var sectionId = $(this).attr("attr_id");
		var districtId = $(this).attr("attr_dist_id");
		var stateId = $(this).attr("attr_state_id");
		var positionId = $(this).attr("attr_position_id");
		var boardLevelId = $(this).attr("attr_locationLevel_id");    
		var deptId = $(this).attr("attr_dept_id");
		var boardId = $(this).attr("attr_corporation_id");
		var castegroupId = $(this).attr("attr_castGroup_id");
		var positionStatusId = $(this).attr("attr_positionStatus_id");
		getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId);
		
	});
	
	function getPositionsForDistrict(sectionId,positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId){
	
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
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' CASTE'
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
	