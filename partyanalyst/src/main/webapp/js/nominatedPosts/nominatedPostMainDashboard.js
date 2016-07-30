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

	