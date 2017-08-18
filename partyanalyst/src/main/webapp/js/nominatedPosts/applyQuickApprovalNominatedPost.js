var positionNames = ['CHAIRMAN','VICECHAIRMAN'];
var positionDetails = ['Nominated Post Level','Department','Corporation/Board Name','Position Name'];
onLoadCalls();

function onLoadCalls(){
  getDepartments();
}

$(document).on('click','#searchbtn',function(){
	getNominatedPostApplication();
})

getBoardLevels("boardLvlId");	
function getBoardLevels(id){
	var jsObj = {}
    $.ajax({
          type:'GET',
          url: 'getOpenedPositionsBoardLevelsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#"+id).empty();
    
   if(result != null && result.length >0){
		 $("#"+id).append('<option value="0">Select Post Level </option>');
     for(var i in result){
		 if(result[i].id != 7)
			$("#"+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   }
	  $("#"+id).trigger("chosen:updated");
   });
  }		
		
$('.chosenSelect').chosen({width:'100%'});

function searchResultBlock(result){
	var position = $("#deptBoardPostnId option:selected").text();
	var block='';
	block+='<h5 style="font-weight:600">SEARCH RESULTS  <span style="font-size:12px;font-weight:normal">   -Found '+result.length+' Results</span></h5>';
	block+='<ul class="nav navbar-nav col-sm-12" id="membersScrollId" style="margin-top:15px !important">';
	for(var i in result){
	block+='<li class="">';
        block+='<div class="panel panel-default">';
            block+='<div class="panel-heading">';
				/*block+='<div class="deleteMember"><i class="fa fa-times" aria-hidden="true" style="padding: 0px 6px 5px;"></i></div>';*/
                block+='<div class="text-center"><p class="iconBlock"><i class="fa fa-user" aria-hidden="true" style="font-size:36px;"></i></p></div>'
            block+='</div>';
            block+='<div class="panel-body">';
				block+='<p class="memberDetails cadreName" value="5"><span>N</span>'+result[i].cadreName+'</p>';
				
                block+='<p class="memberDetails"><span style="padding:4px 8px">V</span>'+result[i].voterCardNo+'</p>';
                block+='<p class="memberDetails"><span style="padding:4px 6px">M</span>'+result[i].memberShipCardId+'</p>';
                block+='<p class="memberDetails"><span style="padding:4px 6px">M</span>'+result[i].mobileNo+'</p>';
                block+='<p class="memberDetails"><span>A</span>'+result[i].addressVO.constituencyName+'</p>';
                block+='<p class="memberDetails"><span>C</span>'+result[i].enrollmentYears+'</p>';
			block+='</div>';
            block+='<div class="panel-footer">';
                block+='<div class="checkbox">';
					block+='<label class="checkbox " ><input type="checkbox" value="" class="selectMember" attr_position_type="'+position+'">Select Member</label>';
                block+='</div>';
            block+='</div>';
        block+='</div>';
    block+='</li>';
	}
	block+='<ul>';
	
    $("#searchResultsBlock").html(block);
	
	$("#membersScrollId").slick({
		slides:'li',
		infinite: false,
		slidesToShow: 5,    
		slidesToScroll: 2,
		variableWidth: true
	}); 
	
}
var globalPosiDivs = 0;
	var globalPositionsArr = [];
$(document).on('click','.selectMember',function(){
		var selPosition = $(this).attr("attr_position_type");
			var appendBlock=$(this).closest("ul").html();
			if(globalPositionsArr == null || globalPositionsArr == ""){
				buildPanelBlock(selPosition,appendBlock);
			}else{
				if(globalPositionsArr.indexOf(selPosition) > -1){
					$("#addmember"+selPosition).append(appendBlock);
				}else{
					buildPanelBlock(selPosition,appendBlock);
				}
			}
					
				
	});
	
function buildPanelBlock(selPosition,appendBlock){
	
	if(globalPositionsArr == null || globalPositionsArr == ""){
	var collapse='';
	collapse+='<div class="panel-group" id="accordionOne" role="tablist" aria-multiselectable="true">';
		collapse+='<div class="panel panel-default">';
			collapse+='<div class="panel-heading" role="tab" id="headingTwo">';
				collapse+=' <a role="button" class="panelCollapseIconChange" data-toggle="collapse" data-parent="#accordionOne" href="#collapsetwo" aria-expanded="true" aria-controls="collapseTwo">';
					collapse+='<h4 class="panel-title" style="font-weight:600">SELECTED POSITON & SEARCH MEMBERS</h4>';
					collapse+='<p>2 Positions & 10 Members</p>';
				collapse+='</a>';
			collapse+='</div>';
			collapse+='<div id="collapsetwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">';
				collapse+='<div class="panel-body">';
					collapse+='<div class="col-sm-9" style="border-right:1px solid grey" id="buildPositionWiseBlock'+globalPosiDivs+'">';
						collapse+='<h5 ><span style="color:#FF0000">STEP-3</span></h5>';
						
    collapse+='<div class="col-sm-12 m_top20" style="border:1px solid grey;" attr_selected_position="'+selPosition+'">';
        collapse+='<div class="panel-group m_top20" id="accordion'+selPosition+'" role="tablist" aria-multiselectable="true">';
            collapse+='<div class="panel panel-default">';
                collapse+='<div class="panel-heading" role="tab" id="headingOne'+selPosition+'" style="background-color:transparent">';
                        collapse+='<a role="button" class="panelCollapseIconChange" data-toggle="collapse" data-parent="#accordion'+selPosition+'" href="#collapseOne'+selPosition+'" aria-expanded="true" aria-controls="collapseOne'+selPosition+'">';
						collapse+='<h4 class="panel-title">'+selPosition+' - Members Added</h4>';
                    collapse+='</a>';
                collapse+='</div>';
					collapse+=' <div id="collapseOne'+selPosition+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+selPosition+'">';
                
                    collapse+='<div class="panel-body" id="show'+selPosition+'">';
						collapse+='<ul class="nav navbar-nav col-sm-12" id="addmember'+selPosition+'">';
							collapse+=appendBlock;
						collapse+='</ul>';
                    collapse+='</div>';
                collapse+='</div>';
            collapse+='</div>';
        collapse+='</div>';
    collapse+='</div>';
    
					collapse+='</div>';
					collapse+='<div class="col-sm-3">';
						collapse+='<h5 style="font-weight:600"><span style="color:#FF0000">STEP-4</span></h5>';
						collapse+='<div class="col-sm-12 m_top20">';
							collapse+='<h5>REFERED MEMBER</h5>';
						collapse+='</div>';
						collapse+='<div class="col-sm-12 m_top20">';
							collapse+='<h5>UPLOAD SCAN COPY</h5>';
						collapse+='</div>';
						collapse+='<div class="col-sm-12">';
							collapse+='<button class="btn btn-success btn-block btn-lg" type="button">SUBMIT APPLICATION</button>';
						collapse+='</div>';
					collapse+='</div>';
				collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</div>';
	 $("#addPositionsBlock").html(collapse);
	}else{
		var  collapse1 = "";
		collapse1+='<div class="col-sm-12 m_top20" style="border:1px solid grey;" attr_selected_position="'+selPosition+'">';
        collapse1+='<div class="panel-group m_top20" id="accordion'+selPosition+'" role="tablist" aria-multiselectable="true">';
            collapse1+='<div class="panel panel-default">';
                collapse1+='<div class="panel-heading" role="tab" id="headingOne'+selPosition+'" style="background-color:transparent">';
                        collapse1+='<a role="button" class="panelCollapseIconChange" data-toggle="collapse" data-parent="#accordion'+selPosition+'" href="#collapseOne'+selPosition+'" aria-expanded="true" aria-controls="collapseOne'+selPosition+'">';
						collapse1+='<h4 class="panel-title">'+selPosition+' - Members Added</h4>';
                    collapse1+='</a>';
                collapse1+='</div>';
					collapse1+=' <div id="collapseOne'+selPosition+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+selPosition+'">';
                
                    collapse1+='<div class="panel-body" id="show'+selPosition+'">';
						collapse1+='<ul class="nav navbar-nav col-sm-12" id="addmember'+selPosition+'">';
							collapse1+=appendBlock;
						collapse1+='</ul>';
                    collapse1+='</div>';
                collapse1+='</div>';
            collapse1+='</div>';
        collapse1+='</div>';
    collapse1+='</div>';
	var currentDiv = globalPosiDivs-1;
	$("#buildPositionWiseBlock"+currentDiv).append(collapse1);
	}
  
   
	$(".slick-slide").css("display","inline-block");
	globalPosiDivs++;
	globalPositionsArr.push(selPosition);
}


function getDepartments(){
	  //$("#searchDataImgForDist").show();
	  $("#errdeptBoardPostnId").html("");
	 var postTypeId=1;
	 var boardLevelId = $("#boardLvlId").val();
   
		var searchLevelValue=1;
	   if(boardLevelId == 1){
		   searchLevelValue = 1;
		   buildDepartments(postTypeId,boardLevelId,searchLevelValue);
	   }
	   else if(boardLevelId == 2){
				searchLevelValue = $('#nominatedStaeId').val();	
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue);
	   }
	   else if(boardLevelId == 3){
		   searchLevelValue = $('#nominatedDistId').val();
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue);
	   }else if(boardLevelId == 4){
		   searchLevelValue = $('#nominatdConstId').val();
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue);
	   }else if(boardLevelId == 5 || boardLevelId == 6){
		   searchLevelValue = $('#nominatedMandlId').val();
			if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue);
	   }else if(boardLevelId == 7){
		  if(searchLevelValue >0)
				buildDepartments(postTypeId,boardLevelId,searchLevelValue);
	   }
  }
  function buildDepartments(postTypeId,boardLevelId,searchLevelValue){
	$("#searchDataImgForDistrict").show();
	  var jsObj = {
		postType:postTypeId,
		boardLevelId:boardLevelId,
		searchLevelValue : searchLevelValue,
		searchLevelId:0,
		positionId:0
		}
		
    $.ajax({
          type:'GET',
          url: 'getDepartmentsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#searchDataImgForDistrict").hide();
	   $("#deptBoardId").html('');
	   $("#deptBoardId").trigger("chosen:updated")
	   $("#deptBoardPostnId").html('');
	   $("#deptBoardPostnId").trigger("chosen:updated");
       $("#depmtsId").html('');
	   $("#depmtsId").trigger("chosen:updated")
     
	   if(result != null && result.length >0){
		   $("#depmtsId").append('<option value=" ">Select Department</option>'); 
		   
			$("#depmtsId").append('<option value="0">Any</option>'); 
		
		 for(var i in result){
			 if(globaldeptId == result[i].id){
				 $("#depmtsId").append('<option selected value='+result[i].id+'>'+result[i].name+'</option>');
			 }else{
				$("#depmtsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			 }
			 
		 }
	   }else{
		  // $("#errdepmtsId"+num).html('<b style="color:red;"> All Departments are filled out.</b>');
		   $("#depmtsId").append('<option value=" "> No Departmets Available </option>'); 
	   }
		  $("#depmtsId").trigger("chosen:updated");
		  if(globaldeptId > 0){
			getDepartmentBoards();
		  }
   });
  }
  
   function getDepartmentBoards(){
		$("#errdeptBoardPostnId").html("");
	$("#searchDataImgForDep").show();
	 var postTypeId=1;
	 var boardLevelId = $("#boardLvlId").val();
	   var searchLevelValue=1;
	   if(boardLevelId == 1){
		   searchLevelValue = 1;
	   }
	   else if(boardLevelId == 2){
			searchLevelValue = $('#nominatedStaeId').val();	;
	   }
	   else if(boardLevelId == 3){
		   searchLevelValue = $('#nominatedDistId').val();
		}else if(boardLevelId == 4){
		   searchLevelValue = $('#nominatdConstId').val();;
	   }else if(boardLevelId == 5 || boardLevelId == 6){
		   searchLevelValue = $('#nominatedMandlId').val();;
	   }else if(boardLevelId == 7){
		  ;
	   }
	  
	   var depmtId = $("#depmtsId").val();
   var jsObj = {
		depmtId : depmtId,
		boardLevelId : $("#boardLvlId").val(),
		searchLevelValue:searchLevelValue,
		searchLevelId:0,
		applicationId:0,
		positionId :0
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   $("#searchDataImgForDep").hide();
     $("#deptBoardId").empty(); 
     $("#deptBoardPostnId").empty();   
   if(result != null && result.length >0){
	       $("#deptBoardId").append('<option value=" ">Select Department Board</option>');
			$("#deptBoardId").append('<option value="0">Any</option>');
	   
	   if(depmtId > 0 ){
				for(var i in result){
					if(globalboardId == result[i].id){
						$("#deptBoardId").append('<option selected value='+result[i].id+'>'+result[i].name+'</option>');
					}else{
						$("#deptBoardId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
					}
			}
	   }
	   if(globalboardId > 0)
		getDepartmentBoardPositions();   
   }
   else{
	   $("#deptBoardId").append('<option value=" ">  </option>'); 
		$("#deptBoardPostnId").val('').trigger('chosen'); 
	}
	  $("#deptBoardId").trigger("chosen:updated");
	   $("#deptBoardPostnId").trigger("chosen:updated");
   });
  }
   $(document).on('change','#deptBoardPostnId',function(){
		var getPosition = $(this).find("option:selected").text();
		//buildPanelBlock(getPosition);
		})
  function getDepartmentBoardPositions(){
	$("#searchDataImgForPos").show();
	$("#errdeptBoardPostnId").html("");
	
	var postTypeId=1;
	 var boardLevelId = $("#boardLvlId").val();

	   var searchLevelValue=1;
	   if(boardLevelId == 1){
		   searchLevelValue = 1;
	   }
	   else if(boardLevelId == 2){
			searchLevelValue = $('#nominatedStaeId').val();	;
	   }
	   else if(boardLevelId == 3){
		   searchLevelValue = $('#nominatedDistId').val();;
	   }else if(boardLevelId == 4){
		   searchLevelValue = $('#nominatdConstId').val();;
	   }else if(boardLevelId == 5 || boardLevelId == 6){
		   searchLevelValue = $('#nominatedMandlId').val();;
	   }else if(boardLevelId == 7){
		  ;
	   }
	   
	   var boardId =  $("#deptBoardId").val();
	   if(boardId == " "){
		   return;
	   }
	   
	var jsObj = {
		
		depmtId : $("#depmtsId").val(),
		boardId :boardId,
		boardLevelId : $("#boardLvlId").val(),
		searchLevelValue:searchLevelValue,
		searchLevelId:0,
		nominatedPostCandId:0
	}
    $.ajax({
          type:'GET',
          url: 'getDepartmentBoardPositionsAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   $("#searchDataImgForPos").hide();
    $("#deptBoardPostnId").empty();
   if(result != null && result.length >0){
	  //$("#deptBoardPostnId"+num).append('<option value="" >Select Board Position</option>');
	  
	   if(result[0].status != "Applied"){
		   $("#deptBoardPostnId").append('<option value="0">Any</option>');
	  } 
		for(var i in result){
			if(result[i].name != null && result[i].id == globalposId){
					$("#deptBoardPostnId").append('<option selected="selected" value='+result[i].id+' id="position'+result[i].name+'">'+result[i].name+'</option>');
				}else if(result[i].name != null){
					$("#deptBoardPostnId").append('<option value='+result[i].id+' id="position'+result[i].name+'">'+result[i].name+'</option>');
					
				}
			}
		$("#deptBoardPostnId").trigger("chosen:updated");
   }else{
	   $("#errdeptBoardPostnId").html('<b style="color:red;"> Already applied to this position.</b>');
	   $("#deptBoardPostnId").trigger("chosen:updated");
   }
   });
  }
  
  function getOpenPositionConstituenciesForDistrict(district,id,num){
	if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#errdeptBoardPostnId"+num).html("");
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
	$("#searchImgForDistr"+num).show();
	$("#nominatdConstId"+num+"").empty();
	$("#nominatedMandlId"+num+"").empty();
	$("#nominatedPanchayatId"+num+"").empty();					
	$("#nominatdConstId"+num+"").append('<option value="0">Select Constituency</option>');		
	$("#nominatedMandlId"+num+"").append('<option value="0">Select Mandal/Municipality</option>');		
	$("#nominatedPanchayatId"+num+"").append('<option value="0">Select Panchayat</option>');
	
	$("#nominatdConstId"+num+"").trigger("chosen:updated");
	$("#nominatedMandlId"+num+"").trigger("chosen:updated");
	$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	
	var jsObj=
   {				
		districtId:district,
		boardLevelId:$('#boardLvlId'+num+'').val()			
	}
    $.ajax({
	  type:'GET',
	  url: 'getOpenPositionConstituenciesForDistrictAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   $("#searchImgForDistr"+num).hide();
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   $("#nominatdConstId"+num).empty();
	   $("#nominatdConstId"+num).append('<option value="0">Select Constituency</option>');
	   for(var i in result){
			$("#nominatdConstId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
		$("#nominatdConstId"+num).trigger("chosen:updated");
		if(globallevelId ==  4  || globallevelId ==  5 || globallevelId ==  7){
			   getOpenPositionMandalsForConstituency('','nominatdConstId');
			   $("#nominatdConstId").val(gConsId).trigger('chosen:updated');
			}
		   if(globallevelId == 4){
			   getDepartments();
		   }
   });
}

function getOpenPositionMandalsForConstituency(num,id){
	if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#errdeptBoardPostnId"+num).html("");
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
	var constituencyId  =0;
	
	 $("#searchImgForConst"+num).show();
	constituencyId = $("#nominatdConstId"+num).val();
	 if(gConsId != null && gConsId >0){
		constituencyId =gConsId; 
	} 
	//$("#nominatdConstId").val(constituencyId).trigger('chosen:updated');
	$("#nominatedMandlId"+num+"  option").remove();
	$("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Municipality</option>');
	$("#nominatedPanchayatId"+num+" option").remove();
	$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
	
	$("#nominatedMandlId"+num+"").trigger("chosen:updated");
	$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	
	var jsObj=
   {				
		constituencyId:constituencyId,
		boardLevelId:$("#boardLvlId"+num).val()			
	}
    $.ajax({
	  type:'GET',
	  url: 'getMandalMuncilIdsForConstituencyAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   
   $("#searchImgForConst"+num).hide();
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	    
	   $("#nominatedMandlId"+num).empty();
	   $("#nominatedMandlId"+num).append('<option value="0">Select Mandal/Municipality</option>');
	   for(var i in result){
			$("#nominatedMandlId"+num).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
	   }
		$("#nominatedMandlId"+num).trigger("chosen:updated");
		 if(globallevelId ==  5 || globallevelId ==  7){
			  getOpenPositionVillagesForMandal('','nominatedMandlId');
			  $("#nominatedMandlId"+num).val(gMandlId).trigger('chosen:updated');
			}
		   
		   if(globallevelId == 5){
			   getDepartments(0);
		   }
   });
}
function getOpenPositionVillagesForMandal(num,id){
	var mandalId=0;
	$("#errdeptBoardPostnId"+num).html("");
	
	var constituencyId = 0; 
		 $("#searchImgForMandl"+num).show();
	mandalId=$("#nominatedMandlId"+num).val();
	constituencyId = $("#nominatdConstId"+num).val();
	if(gConsId != null && gConsId.length >0){
		constituencyId =gConsId; 
	}
	if(gMandlId != null && gMandlId.length > 0){
		mandalId =gMandlId; 
	}
	
	$("#nominatedPanchayatId"+num+"  option").remove();
	$("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat</option>');
	$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	
	var jsObj=
	{			
		mandalId	:mandalId,
		constituencyId:constituencyId,
		boardLevelId:$('#boardLvlId'+num+'').val()			
	}
    $.ajax({
	  type:'GET',
	  url: 'getPanchaytWardForMandalAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	   	 $("#searchImgForMandl"+num).hide();
	   $("#nominatedPanchayatId"+num).empty();
	   $("#nominatedPanchayatId"+num).append('<option value="0">Select Panchayat/Ward</option>');
	   for(var i in result){
			$("#nominatedPanchayatId"+num).append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
	   }
		$("#nominatedPanchayatId"+num).trigger("chosen:updated");
		
		if(globallevelId == 7){
			   getDepartments(0);
		   }
   });
}
  function showHideByNominatedPost()
{
	var selectVal = $("#boardLvlId").val();
	if(selectVal==0||selectVal==1)
	{
	$("#statesShowDivId").hide();
	$("#nominatedStaeId").hide();
	$("#districtShowDivId").hide();
	$("#nominatedDistId").hide();
	$("#constituencyshowDivId").hide();
	$("#nominatdConstId").hide();
	$("#mondalShowDivId").hide();
	$("#nominatedMandlId").hide();
	$("#panchayatShowDivId").hide();
	$("#nominatedPanchayatId").hide();
	
	}
else if(selectVal==2)
{
	$("#statesShowDivId").show();
	$("#nominatedStaeId").hide();
	$("#districtShowDivId").hide();
	$("#nominatedDistId").hide();
	$("#constituencyshowDivId").hide();
	$("#nominatdConstId").hide();
	$("#mondalShowDivId").hide();
	$("#nominatedMandlId").hide();
	$("#panchayatShowDivId").hide();
	$("#nominatedPanchayatId").hide();
}else if(selectVal==3)
{
	$("#statesShowDivId").show();
	$("#nominatedStaeId").hide();
	$("#districtShowDivId").show();
	$("#nominatedDistId").hide();
	$("#constituencyshowDivId").hide();
	$("#nominatdConstId").hide();
	$("#mondalShowDivId").hide();
	$("#nominatedMandlId").hide();
	$("#panchayatShowDivId").hide();
	$("#nominatedPanchayatId").hide();
}else if(selectVal==4)
{
    $("#statesShowDivId").show();
	$("#nominatedStaeId").hide();
	$("#districtShowDivId").show();
	$("#nominatedDistId").hide();
	$("#constituencyshowDivId").show();
	$("#nominatdConstId").hide();
	$("#mondalShowDivId").hide();
	$("#nominatedMandlId").hide();
	$("#panchayatShowDivId").hide();
	$("#nominatedPanchayatId").hide();	
}else if(selectVal==5||selectVal==6)
{
	$("#statesShowDivId").show();
	$("#nominatedStaeId").hide();
	$("#districtShowDivId").show();
	$("#nominatedDistId").hide();
	$("#constituencyshowDivId").show();
	$("#nominatdConstId").hide();
	$("#mondalShowDivId").show();
	$("#nominatedMandlId").hide();
	$("#panchayatShowDivId").hide();
	$("#nominatedPanchayatId").hide();
}else if(selectVal=7)
{
	$("#statesShowDivId").show();
	$("#nominatedStaeId").hide();
	$("#districtShowDivId").show();
	$("#nominatedDistId").hide();
	$("#constituencyshowDivId").show();
	$("#nominatdConstId").hide();
	$("#mondalShowDivId").show();
	$("#nominatedMandlId").hide();
	$("#panchayatShowDivId").show();
	$("#nominatedPanchayatId").hide();
}
	
	
}
	//getOpenedPostionsStates('nominatedStaeId');
  	function getOpenedPostionsStates(id){
		//if(num == 0)
		  //num='';
	   $("#deptBoardId").html('');
	   $("#deptBoardId").trigger("chosen:updated")
	   $("#deptBoardPostnId").html('');
	   $("#deptBoardPostnId").trigger("chosen:updated")
       $("#depmtsId").html('');
	   $("#depmtsId").trigger("chosen:updated")
		state = $("#nominatedStaeId").val();
		$("#nominatedDistId").empty();
		$("#nominatdConstId").empty();
	$("#nominatedMandlId").empty();
	$("#nominatedPanchayatId").empty();			
	$("#nominatedDistId").append('<option value="0">Select District</option>');		
	$("#nominatdConstId").append('<option value="0">Select Constituency</option>');		
	$("#nominatedMandlId").append('<option value="0">Select Mandal/Municipality</option>');		
	$("#nominatedPanchayatId").append('<option value="0">Select Panchayat</option>');
	
	$("#nominatedDistId").trigger("chosen:updated");
	$("#nominatdConstId").trigger("chosen:updated");
	$("#nominatedMandlId").trigger("chosen:updated");
	$("#nominatedPanchayatId").trigger("chosen:updated");
		$("#searchDataImgForState").show();
		var jsObj = {			
			boardLevelId:$('#boardLvlId').val()
		}
	    $.ajax({
	          type:'GET',
	          url: 'getStatesForOpenedPositionsAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#searchDataImgForState").hide();
	   $("#"+id).empty();
	    
	   if(result != null && result.length >0){
			 $("#"+id).append('<option value="0">Select State </option>');
	     for(var i in result){
			 $("#"+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		 }
	   }
		  $("#"+id).trigger("chosen:updated");
		  $("#nominatedStaeId").val(gsId).trigger('chosen:updated');
		  getOpenPositionDistrictsForState(gsId,'nominatedStaeId','');
		  if(globallevelId == 2){
			   getDepartments(0);
		   }
	   });
	  }
 function getOpenPositionDistrictsForState(state,id,num){
	if(num == 0)
		  num='';
	   $("#deptBoardId"+num).html('');
	   $("#errdeptBoardPostnId"+num).html("");
	   $("#deptBoardId"+num).trigger("chosen:updated")
	   $("#deptBoardPostnId"+num).html('');
	   $("#deptBoardPostnId"+num).trigger("chosen:updated")
       $("#depmtsId"+num).html('');
	   $("#depmtsId"+num).trigger("chosen:updated")
	$("#searchDataImgForDistrict"+num).show();
	//state = $("#nominatedStaeId"+num).val();
	//$("#nominatedDistId  option").remove();
	$("#nominatedDistId"+num+"").empty();
	$("#nominatdConstId"+num+"").empty();
	$("#nominatedMandlId"+num+"").empty();
	$("#nominatedPanchayatId"+num+"").empty();			
	$("#nominatedDistId"+num+"").append('<option value="0">Select District</option>');		
	$("#nominatdConstId"+num+"").append('<option value="0">Select Constituency</option>');		
	$("#nominatedMandlId"+num+"").append('<option value="0">Select Mandal/Municipality</option>');		
	$("#nominatedPanchayatId"+num+"").append('<option value="0">Select Panchayat</option>');
	
	$("#nominatedDistId"+num+"").trigger("chosen:updated");
	$("#nominatdConstId"+num+"").trigger("chosen:updated");
	$("#nominatedMandlId"+num+"").trigger("chosen:updated");
	$("#nominatedPanchayatId"+num+"").trigger("chosen:updated");
	if(state == 0){
		$("#searchDataImgForDistrict"+num).hide();
		return;
	}else{
		$("#searchDataImgForDistrict"+num).show();
	}
	
	var jsObj=
   {				
		stateId:state,
		boardLevelId:$('#boardLvlId'+num+'').val()
	}
    $.ajax({
	  type:'GET',
	  url: 'getOpenPositionDistrictsForStateAction.action',
	  dataType: 'json',
	  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){   
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   //location.reload(); 
	   }
	   $("#searchDataImgForDistrict"+num).hide();
	   $("#nominatedDistId"+num).empty();
	   $("#nominatedDistId"+num).append('<option value="0">Select District</option>');
	   for(var i in result){
			$("#nominatedDistId"+num).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
		$("#nominatedDistId"+num).trigger("chosen:updated");
		 if(globallevelId ==  3 || globallevelId ==  4 || globallevelId ==  5 || globallevelId ==  7){
			  getOpenPositionConstituenciesForDistrict(gDisId,'nominatedDistId','');
			  $("#nominatedDistId").val(gDisId).trigger('chosen:updated');
			   
		   }
		   if(globallevelId == 3){
			   getDepartments(0);
		   }
   });
}

var isFree =true;
function getNominatedPostApplication()
		{
		//var searchRadioType =$('.searchTypeCls:checked').attr("attr_type");
		var locationLevel = 0;
		var locationValue = 0;
		var searchName = '';
		var mobileNo = '';
		var casteCategory = '';
		var casteStateId = 0;
		var fromAge = 0;
		var toAge = 0;
		var memberShipCardNo = '12345678';
		var trNumber = '';
		var voterCardNo = '';
		var gender = '';
		var houseNo = '';
		var membershipAndMobileNo = '';
		var type = $("input[type='radio']:checked").attr("attr_type");
		if(type == 'memberShipNumber'){
			memberShipCardNo=$("#searchBy").val();
		}else if(type == 'voterId'){
			voterCardNo=$("#searchBy").val();
		}
		var jsObj =
		{
			locationLevel :locationLevel,
			locationValue:locationValue,
			searchName : searchName,
			mobileNo: mobileNo,
			casteCategory : casteCategory,
			fromAge : fromAge,
			toAge : toAge,
			memberShipCardNo: memberShipCardNo,
			casteStateId : casteStateId,
			trNumber : trNumber,
			voterCardNo:voterCardNo,
			gender:gender,
			houseNo:houseNo,
			//startIndex:startIndex,
			maxIndex : 50,
			removedStatus:false,
			enrollmentId : 0,
			task:"NominatedPostSearch"
		}
		
		$.ajax({
				type : "POST",
				url : "getCadreSearchDetailsAction.action",
				data : {task:JSON.stringify(jsObj)} ,
			}).done(function(result){
				searchResultBlock(result.previousRoles);
				globalSelectedMemberIdsArr = []; // Clearing Array 
				$("#textId").hide();
				 isFree =true;
				  //$("#cadreSearchDtls").html('');
			$(".paginationDivId").show();
				 if(typeof result == "string"){
					if(result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
					  location.reload(); 
					}
				}
				//$("#searchDataImg").hide();
				//$('#cadreDetailsDiv').show();
				if(result != null && result.previousRoles != null && result.previousRoles.length>0)
				{
					//$('#membersCountId').show();
					//buildCadreDetails(result.previousRoles,"cadre"); 
					
				}
				 else
				{
					//$('#cadreSearchDtls').html("");
					  $('#textId').html("");
					//$('#cadreSearchDtls').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				} 
			});  
		}
	//hideDetails();
	function hideDetails(){
	$('.ramakrishnaCls').hide();
	//$('#searchDivId').hide();
	$("#uploadFlDivId").hide();
	$("#submitBtnId").hide();
	$("#addedRefferalsDiv").hide();
}
function refreshExistingDetails(){ 	
		hideDetails();    
		$("#searchErrDiv").html(""); 
		$("#notCadreErrMsg").html("");
		$("#searchById").val("");
	}

 function refreshOnLoadFields(){
	$('#searchBy').val('');
	$("#membershipId").prop("checked", true);
 }
	
/*check box*/
$('input[type="checkbox"]').click(function(){
			if($(this).prop("checked") == true){
			   //var appendBlock=$(this).closest("li").html();
			   
			}
 });