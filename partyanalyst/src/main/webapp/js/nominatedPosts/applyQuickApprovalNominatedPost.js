var positionNames = ['CHAIRMAN','VICECHAIRMAN'];
var positionDetails = ['Nominated Post Level','Department','Corporation/Board Name','Position Name'];
var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var enrollmentYears = [2010,2012,2014,2016];
onLoadCalls();
var globalObjArr = [];
function onLoadCalls(){
  getDepartments();
}
$(".dropkickClass").dropkick();
$(document).on('click','#searchbtn',function(){
	$("#searchResultsBlock").show();
	$("#errMessageId").html('');
	var searchValue = $("#searchBy").val() ;
	var selOption = $("#deptBoardPostnId option:selected").val();
	if(selOption == null || selOption == 0){
		$("#errdeptBoardPostnId").html("Please Select Position");
		return;
	}else{
		$("#errdeptBoardPostnId").html(" ");
		
	}
	if(searchValue.length == 0 || searchValue == null || searchValue == " ")
			{
				$('#notCadreErrMsg').html('Please Enter Value.');
				return;
			}else{
				$('#notCadreErrMsg').html('');
				var type = $("input[type='radio']:checked").attr("attr_type");
				if(type == 'memberShipNumber'){
					getNominatedPostApplication();
				}else if(type == 'voterId'){
					getSearchDetails("mainSearch");
				}
				
			}
	
	//$("#addedRefferalsDiv").hide();
	
	
})
$(document).on('change','#deptBoardPostnId',function(){
	var selPosition = $(this).find(":selected").text();
	var postionArr = selPosition.split("(");
	selPosition = postionArr[0];
	$("#searchResultsBlock").html("");
	$("#searchBy").val("");
	$("#showSearchResult").show();
	if($(this).val() !=0 ){
		$("#errdeptBoardPostnId").html(" ");
	}
})

getBoardLevels("boardLvlId");	
function getBoardLevels(id){
	$("#searchResultsBlock").html("");
	$("#searchBy").val("");
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
		 if(result[i].id != 1 && result[i].id != 2 )
			$("#"+id).append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	 }
   }
	  $("#"+id).trigger("chosen:updated");
   });
  }		
		
$('.chosenSelect').chosen({width:'100%'});



function searchResultBlock(myresult){
	var result = myresult.previousRoles;
	var postitonCunt =  "";
	var level = $("#boardLvlId option:selected").text();
  var board = $("#deptBoardId option:selected").text();
	var position = $("#deptBoardPostnId option:selected").text();
	var postionArr = position.split("(");
	
	if(postionArr != "Select Board Position"){
		position = postionArr[0];
		postitonCunt = postionArr[1].trim().replace(")", "");
	}
	var boardId = $("#deptBoardId").val();
	var levelId = $("#boardLvlId").val();
	var uniqueId = ''+levelId+''+boardId+''+position+'';
	var block='';
	
	var isEligibleToDelete=false;
	if(entitlementsArr != null && entitlementsArr.length>0){
		for(var i in entitlementsArr){
			if(entitlementsArr[i].trim() =='CADRE_DELETE_ENTITLEMENT'){
				isEligibleToDelete=true;
			}
		}
	}
	console.log("isEligibleToDelete :"+isEligibleToDelete);
	block+='<h5 style="font-weight:600">SEARCH RESULTS  <span style="font-size:12px;font-weight:normal">   -Found '+result.length+' Results</span></h5>';
	block+='<ul class="nav navbar-nav col-sm-12"  style="margin-top:15px !important">';
	for(var i in result){
		var array = JSON.parse("[" + result[i].enrollmentYears + "]");
		enrollmentYears = enrollmentYears.filter(function(val) {
		  return array.indexOf(val) == -1;
		});
		
		var nominationPostCandidateId=0;
		var nominatedCanCadreId=0;
		if(myresult.previousElections != null && myresult.previousElections.length>0 ){
				for(var s in myresult.previousElections){
					nominatedCanCadreId= myresult.previousElections[s].tdpCadreId;
					nominationPostCandidateId= myresult.previousElections[s].nominatedPostCandidateId;
				}
		}
	block+='<li class="">';
        block+='<div class="panel panel-default">';
            block+='<div class="panel-heading">';
				/*block+='<div class="deleteMember"><i class="fa fa-times" aria-hidden="true" style="padding: 0px 6px 5px;"></i></div>';*/
                block+='<div class="text-center"><p class="iconBlock memberDetails"><img src="http://www.mytdp.com/images/cadre_images/'+result[i].imageURL+'" style="width: 50px; height: 50px; margin-left: -2px; margin-top: -6px;border-radius:50%;"></p>';
				/*if(isEligibleToDelete){
					block+='<i style ="float:right;;cursor:pointer;color:red;" attr_nomination_post_candidate_id="'+nominationPostCandidateId+'" attr_tdp_cadre_id="'+result[i].tdpCadreId+'" class="glyphicon glyphicon-remove remove-icon removeIconCls" data-toggle="tooltip" data-placement="bottom" title="Remove Candidate"></i>';
				}*/
				block+='</div>';
            block+='</div>';
            block+='<div class="panel-body">';
			
				block+='<p class="memberDetails cadreName" value="5" data-toggle="tooltip" data-placement="top" title="Cadre Name"><span>N</span>'+result[i].cadreName+'</p>';
				
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Voter Id"><span style="padding:4px 8px">V</span>'+result[i].voterCardNo+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="MemberShip Number"><span style="padding:4px 6px">M</span>'+result[i].memberShipCardId+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Mobile Number"><span style="padding:4px 6px">M</span>'+result[i].mobileNo+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Constituency Name"><span>A</span>'+result[i].addressVO.constituencyName+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Enrollment Years"><span>C</span>';
				for(var l in array)
				{
					block+='<span style="margin-right:2px;">'+array[l]+'</span>';
				}
				if(enrollmentYears != null && enrollmentYears.length >= 1)
				{
					for(var k in enrollmentYears)
					{
						block+='<span style="border:1px solid red;margin-right:2px;text-decoration:line-through">'+enrollmentYears[k]+'</span>';
					}
				}
				
				block+='</p>';
			block+='</div>';
            block+='<div class="panel-footer">';
			//myresult.previousElections[0].isEligile =='true'
			if(myresult.previousElections != null && myresult.previousElections.length>0 && myresult.previousElections[0].isEligile != null && myresult.previousElections[0].isEligile =='false'){
				for(var s in myresult.previousElections){
					block+=''+parseInt(parseInt(s)+1)+') <label>'+myresult.previousElections[s].casteName+' &nbsp&nbsp&nbsp<i class="fa fa-info tooltipClass" style="cursor:pointer;" title="'+myresult.previousElections[s].electionType+':'+myresult.previousElections[s].roleName+' --> '+myresult.previousElections[s].occupation+' Dept --> '+myresult.previousElections[s].role+' --> '+myresult.previousElections[s].casteCategory+'"> </i></label>  <br> ';
				}
			}else if(myresult.previousElections != null && myresult.previousElections.length>0 && myresult.previousElections[0].isEligile != null && myresult.previousElections[0].isEligile =='true'){
				for(var s in myresult.previousElections){
					block+=''+parseInt(parseInt(s)+1)+') <label>'+myresult.previousElections[s].casteName+' &nbsp&nbsp&nbsp<i class="fa fa-info tooltipClass" style="cursor:pointer;" title="'+myresult.previousElections[s].electionType+':'+myresult.previousElections[s].roleName+' --> '+myresult.previousElections[s].occupation+' Dept --> '+myresult.previousElections[s].role+' --> '+myresult.previousElections[s].casteCategory+'"> </i></label>  <br> ';
				}
				 block+='<label class="checkbox-inline" > <input type="checkbox" value="" attr_cadreId="'+result[i].tdpCadreId+'" class="selectMember" attr_position_type="'+level+' &nbsp;Level - '+board+' &nbsp;Board - '+position+' &nbsp;Position " attr_postion_count="'+postitonCunt+'" attr_unique_id='+uniqueId+' attr_candidate_id="'+nominationPostCandidateId+'">Select Member</label>';
				 
			}else{
				 block+='<label class="checkbox-inline" > <input type="checkbox" value="" attr_cadreId="'+result[i].tdpCadreId+'" class="selectMember" attr_position_type="'+level+' &nbsp;Level - '+board+' &nbsp;Board - '+position+' &nbsp;Position " attr_candidate_id="'+nominationPostCandidateId+'" attr_postion_count="'+postitonCunt+'" attr_unique_id='+uniqueId+'>Select Member</label>';
			}
               
				
            block+='</div>';
        block+='</div>';
    block+='</li>';
	}
	block+='<ul>';
	block+='<div class="clearfix"></div>';
	block+='<p class="m_top20">Note:   1) Please select matches profile     2) You Can add Multiple members to above selected Post Name</p>';
    $("#searchResultsBlock").html(block);
	$(".tooltipClass").tooltip();
	
}

function updateAddedPosiAndAddedMemCnt(){
	$("#selTotPosCnt").text(globalPosiDivs);
	$("#selTotMemCnt").text(globalMemAddedCunt);
}
var globalPosiDivs = 0;
var globalPositionsArr = [];
var globalCadreIds =[];
var globalMembersCount = 0;
var globalMemAddedCunt = 0;
$(document).on('click','.selectMember',function(){
	var selPosition = $(this).attr("attr_position_type");
	var position = $("#deptBoardPostnId option:selected").text();
	var postionArr = position.split("(");
	var uniqueId =  $(this).attr("attr_unique_id");
	var nominatedCandId = $(this).attr("attr_candidate_id");
	if(postionArr != "Select Board Position"){
		position = postionArr[0];
	}
	if(!(globalPositionsArr.indexOf(uniqueId) > -1)){
		globalMembersCount = 0;
	}
	$("#errMessageId").html('');
	var positionCount = $(this).attr("attr_postion_count");
	if(positionCount != globalMembersCount ){
		var departmentId=$("#depmtsId").val();
		var boardId = $("#deptBoardId").val();
		var positionId = $("#deptBoardPostnId").val();
		
		$("#addmember"+uniqueId).closest(".panel-group").parent().show();
			if($(this).is(':checked')){
				var appendBlock = $(this).closest("li").html();
				var cadreId = $(this).attr("attr_cadreId");
				$("#searchBy").val(" ");
				$("#searchResultsBlock").hide("slow");
				$("#searchResultsBlock").html(" ");
				if(globalCadreIds.indexOf(cadreId) > -1){
					$("#errMessageId").html('Duplicate person adding.');
				}else{
					if(globalPositionsArr == null || globalPositionsArr == ""){
						globalCadreIds.push(cadreId);
						globalMemAddedCunt = globalMemAddedCunt+1;
						globalMembersCount++;
						buildPanelBlock(selPosition,appendBlock,cadreId,uniqueId,nominatedCandId);
						$("#addmember"+uniqueId).find("li div.panel-footer").remove();
					}else{
						var posiMemCnt = $("#posiMemCnt"+uniqueId).text();
						//if((globalPositionsArr.indexOf(uniqueId) > -1  || $("[attr_selected_position="+uniqueId+"]").length != 0 )
						//&& positionCount > posiMemCnt)
						var divCheck = (globalPositionsArr.indexOf(uniqueId) > -1);
						//var isExistDiv=$("[attr_selected_position="+uniqueId+"]").length != 0;
						var countChk =(parseInt(positionCount) > parseInt(posiMemCnt));
						if(countChk && divCheck  ){
							globalCadreIds.push(cadreId);
							globalMemAddedCunt = globalMemAddedCunt+1;
							//globalMembersCount++;
							var count = $("#addmember"+uniqueId).attr("attr_member_count");
							var posiCnt = $("#addmember"+uniqueId).attr("attr_posi_count");
							count++;
							updateAddedPosiAndAddedMemCnt();
							posiMemCnt++;
							$("#posiMemCnt"+uniqueId).text(posiMemCnt);
							$("#addmember"+uniqueId).attr("attr_member_count",count);
							$("#addmember"+uniqueId).append('<li style="margin:2px 5px;"><input type="hidden" value="'+cadreId+'" name="nominatedPostDetailsVO.subList['+posiCnt+'].subList1['+count+'].tdpCadreId"><input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+posiCnt+'].subList1['+count+'].departmentId" value="'+departmentId+'"><input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+posiCnt+'].subList1['+count+'].boardId" value="'+boardId+'"><input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+posiCnt+'].subList1['+count+'].positionId" value="'+positionId+'"><i class="fa fa-times removeMember-icon" attr_cadre_id="'+cadreId+'" aria-hidden="true" attr_posi_text="'+uniqueId+'"></i>'+appendBlock+'</li>');
							
							$("#addmember"+uniqueId).find("li div.panel-footer").remove();
						}else if(!(globalPositionsArr.indexOf(uniqueId) > -1)){
							globalCadreIds.push(cadreId);
							globalMemAddedCunt = globalMemAddedCunt+1;
							globalMembersCount++;
							buildPanelBlock(selPosition,appendBlock,cadreId,uniqueId,nominatedCandId);
							$("#addmember"+uniqueId).find("li div.panel-footer").remove();
						}else{
							alert("Posts Are completed");
						}
					}
				}
				
			}
		}else{
			alert("Posts Are completed");
		}
});

function buildPanelBlock(selPosition,appendBlock,cadreId,uniqueId,nominatedCandId){
	var departmentId=$("#depmtsId").val();
	var boardId = $("#deptBoardId").val();
	var positionId = $("#deptBoardPostnId").val();                                                                    
	if(globalPositionsArr == null || globalPositionsArr == ""){
	var collapse='';
	var count = 0;
	globalPosiDivs++;
	collapse+='<input type="hidden" id="positionDiv'+uniqueId+'" name="nominatedPostDetailsVO.subList['+globalPosiDivs+']">';
	collapse+='<div class="panel-group" id="accordionOne" role="tablist" aria-multiselectable="true">';
		collapse+='<div class="panel panel-default">';
			collapse+='<div class="panel-heading" role="tab" id="headingTwo">';
				collapse+=' <a role="button" class="panelCollapseIconChange" data-toggle="collapse" data-parent="#accordionOne" href="#collapsetwo" aria-expanded="true" aria-controls="collapseTwo">';
					collapse+='<h4 class="panel-title" style="font-weight:600">SELECTED POSITION & SEARCH MEMBERS</h4>';
					collapse+='<p><span id="selTotPosCnt">'+globalPosiDivs+'</span> Positions & <span id="selTotMemCnt">'+globalMemAddedCunt+'</span> Members</p>';
				collapse+='</a>';
			collapse+='</div>';
			collapse+='<div id="collapsetwo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingTwo">';
				collapse+='<div class="panel-body">';
				collapse+='<div class="row">';
					collapse+='<div class="col-sm-9" style="border-right:1px solid grey" id="buildPositionWiseBlock'+globalPosiDivs+'">';
						collapse+='<h5 ><span style="color:#FF0000">STEP-3</span></h5>';
						
						collapse+='<div class="col-sm-12 m_top20" style="border:1px solid grey;" attr_selected_position="'+uniqueId+'">';
						collapse+='<div class="panel-group m_top20" id="accordion'+uniqueId+'" role="tablist" aria-multiselectable="true">';
							collapse+='<div class="panel panel-default">';
								collapse+='<div class="panel-heading" role="tab" id="headingOne'+uniqueId+'" style="background-color:transparent">';
										collapse+='<a role="button" class="panelCollapseIconChange" data-toggle="collapse" data-parent="#accordion'+uniqueId+'" href="#collapseOne'+uniqueId+'" aria-expanded="true" aria-controls="collapseOne'+uniqueId+'">';
										collapse+='<h4 class="panel-title">'+selPosition+' -<span id="posiMemCnt'+uniqueId+'">1</span> Members Added</h4>';
									collapse+='</a>';
								collapse+='</div>';
									collapse+=' <div id="collapseOne'+uniqueId+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+uniqueId+'">';
								
									collapse+='<div class="panel-body" id="show'+uniqueId+'">';
										collapse+='<ul class="nav navbar-nav membersSelectionDivCls" id="addmember'+uniqueId+'" attr_remove_member="remove-member" attr_member_count="'+count+'" attr_posi_count="'+globalPosiDivs+'">';
										collapse+='<li style="margin:2px 5px;">';
											collapse+='<i class="fa fa-times removeMember-icon" attr_posi_text="'+uniqueId+'" attr_cadre_id="'+cadreId+'"  aria-hidden="true"></i>';
											collapse+='<input type="hidden" value="'+cadreId+'" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].tdpCadreId">';
											collapse+='<input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].departmentId" value="'+departmentId+'">';
											collapse+='<input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].boardId" value="'+boardId+'">';
											collapse+='<input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].positionId" value="'+positionId+'">';
											collapse+='<input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].nominatedPostCandiateId" value="'+nominatedCandId+'">';
											collapse+=appendBlock;
											collapse+='</li>';
										collapse+='</ul>';
									collapse+='</div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
					collapse+='</div>';
					 
					collapse+='</div>';
	
	
					collapse+='<div class="col-sm-3">';
						collapse+='<h5 style="font-weight:600"><span style="color:#FF0000">STEP-4</span></h5>';
								collapse+='<div class="row m_top10" id="addedRefferalsDiv" style="overflow-Y:scroll;height:450px;">';
							collapse+='<div class="col-md-12 col-sm-12 col-xs-12 m_top10">';
								collapse+='<h4 class="text-success text-capital">refered members to this candidate<small class="text-muted text-capitalize" id="involvedMembers">(0 - Members added)</small></h4>';
							collapse+='</div>';
							
							collapse+='<div class="membersBlock">';
							collapse+='</div>';
							collapse+='<div class="col-md-12 col-xs-12 col-sm-6 m_top10">';
								collapse+='<div class="involveBlockNew" btn-attr = "involve">';
									collapse+='<div class="media">';
                                	collapse+='<div class="media-left" style="font-size:36px">+</div>';
                                    collapse+='<div class="media-body">Click to Search Referral Details to this Candidate</div>';
                                collapse+='</div>';
								collapse+='</div>';
							collapse+='</div>';
						collapse+='</div>';
				collapse+='</div>';
				collapse+='<div class="col-sm-12 m_top20">';
							collapse+='<h5 style="font-weight:600"><span style="color:#FF0000">STEP-5</span></h5>';
							collapse+='<h5>UPLOAD SCAN COPY</h5>';
							collapse+='<div class="hideDivCls m_top10" id="uploadFlDivId" style="border:1px solid grey;padding:18px">';
								collapse+='<input type="file" id="imageId" multiple="multiple"  name="fileImage" class="m_top20"/>';
							collapse+='</div>';
						collapse+='</div>';
						collapse+='<div class="col-sm-4 col-sm-offset-4 m_top10">';
							collapse+='<button class="btn btn-success btn-block btn-lg submitBtnId" type="button" onclick="savingApplication();">SUBMIT APPLICATION</button>';
							collapse+='<div style="margin-top:12px;"><img id="savingAjaxImg" src="images/icons/loading.gif" style="display:none;"/></div>';
						collapse+='</div>';
			collapse+='</div>';
			collapse+='</div>';
		collapse+='</div>';
	collapse+='</div>';
	 $("#addPositionsBlock").html(collapse);
	 
	$("#addmember"+uniqueId).find("li div.panel-footer").remove();
	 initializeFile()
	}else{
		globalPosiDivs++;
		var  collapse1 = "";
		var count = 0;
		updateAddedPosiAndAddedMemCnt();
		collapse1+='<input type="hidden" id="positionDiv'+uniqueId+'" name="nominatedPostDetailsVO.subList['+globalPosiDivs+']">';
		collapse1+='<div class="col-sm-12 m_top20" style="border:1px solid grey;" attr_selected_position="'+uniqueId+'">';
        collapse1+='<div class="panel-group m_top20" id="accordion'+uniqueId+'" role="tablist" aria-multiselectable="true">';
            collapse1+='<div class="panel panel-default">';
                collapse1+='<div class="panel-heading" role="tab" id="headingOne'+uniqueId+'" style="background-color:transparent">';
                        collapse1+='<a role="button" class="panelCollapseIconChange" data-toggle="collapse" data-parent="#accordion'+uniqueId+'" href="#collapseOne'+uniqueId+'" aria-expanded="true" aria-controls="collapseOne'+uniqueId+'">';
						collapse1+='<h4 class="panel-title">'+selPosition+' - <span id="posiMemCnt'+uniqueId+'">1</span>Members Added</h4>';
                    collapse1+='</a>';
                collapse1+='</div>';
					collapse1+=' <div id="collapseOne'+uniqueId+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne'+uniqueId+'">';
                
                    collapse1+='<div class="panel-body" id="show'+uniqueId+'">';
						collapse1+='<ul class="nav navbar-nav col-sm-12 membersSelectionDivCls" id="addmember'+uniqueId+'" attr_remove_member="remove-member" attr_member_count="'+count+'" attr_posi_count="'+globalPosiDivs+'">';
						collapse1+='<li>';
						collapse1+='<i class="fa fa-times removeMember-icon" attr_posi_text="'+uniqueId+'" attr_cadre_id="'+cadreId+'" aria-hidden="true" style="margin:2px 12px"></i>';
						collapse1+='<input type="hidden" value="'+cadreId+'" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].tdpCadreId">';
						collapse1+='<input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].departmentId" value="'+departmentId+'">';
							collapse1+='<input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].boardId" value="'+boardId+'">';
							collapse1+='<input type="hidden" class="cadreVoterId" name="nominatedPostDetailsVO.subList['+globalPosiDivs+'].subList1['+count+'].positionId" value="'+positionId+'">';
							collapse1+=appendBlock;
							collapse1+='</li>';
						collapse1+='</ul>';
                    collapse1+='</div>';
                collapse1+='</div>';
            collapse1+='</div>';
        collapse1+='</div>';
    collapse1+='</div>';
	
	var currentDiv=1;
	$("#buildPositionWiseBlock"+currentDiv).append(collapse1);
	
	}
	globalPositionsArr.push(uniqueId);
}
/* $(document).on('click','[attr_remove_member="remove-member"] li',function(){
	$(this).toggleClass("active");
	$(this).find("i").toggleClass("active");
}) */
$(document).on('click','.removeMember-icon',function(){
	$("#errMessageId").html('');
	var ulLength = $(this).closest("ul").attr("id"); 
	var selPosition = $(this).attr("attr_posi_text");
	ulLength = $("#"+ulLength+" li").length
	if(ulLength == 1)
	{
		globalPosiDivs--;
		$(this).closest(".panel-group").parent().remove();
		for(var i in globalPositionsArr){
			if(globalPositionsArr[i] == selPosition){
			globalPositionsArr.splice(i, 1);
				if(globalMembersCount >= 1){
					globalMembersCount--;
				}
			}
		}
	}
	
	var posiMemCnt = $("#posiMemCnt"+selPosition).text();
	posiMemCnt--;
	$("#posiMemCnt"+selPosition).text(posiMemCnt);
	$(this).closest("li").remove();
	$(".selectMember").prop("checked",false);
	var cadreId = $(this).attr("attr_cadre_id");
	for(var i in globalPositionsArr){
		if(globalPositionsArr[i] == selPosition){
			if(globalMembersCount >= 1){
				globalMembersCount--;
			}
		}
	}
	if(globalMemAddedCunt >= 1){
		globalMemAddedCunt--;
	}
	for(var i in globalCadreIds){
		if(globalCadreIds[i] == cadreId)
		globalCadreIds.splice(i, 1);
	}
	
	updateAddedPosiAndAddedMemCnt();
});

function savingApplication(){
$("#savingAjaxImg").css("display","block");
$(".submitBtnId").attr("disabled",true)
			var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					showSubmitStatus(uploadResult);
				}
			};
			
			YAHOO.util.Connect.setForm('submitApplication',true);
			YAHOO.util.Connect.asyncRequest('POST','saveNominatedPostProfileDtlsAction.action',uploadHandler);
			
	
	}
		


function showSubmitStatus(myResult){
	
	//console.log(myResult);
	var result = (String)(myResult);
		   var resultAray = result.split(',');
		   var temp =resultAray[7].replace("\"","");
		   var tempArr = resultAray[7].split(":");
		  // alert(tempArr[0]+" : "+tempArr[1]);
		   var statusCode = tempArr[1];
		 // console.log(statusCode);
	if(statusCode !=null && statusCode== 1 ){	
		alert("Application  Submitted Successfully");
		
		location.reload(); 
	}else{
		//$("#submitBtnId").show();
		$(".submitBtnId").attr("disabled",false)
		alert("Application  Not Submitted Please Try Again ")
	}
	
}	

function getDepartments(){
	  //$("#searchDataImgForDist").show();
	  $("#searchResultsBlock").html("");
		$("#searchBy").val("");
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
		   searchLevelValue = $('#nominatedPanchayatId').val();
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
		   
			//$("#depmtsId").append('<option value="0">Any</option>'); 
		
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
		$("#searchResultsBlock").html("");
		$("#searchBy").val("");
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
		  searchLevelValue = $('#nominatedPanchayatId').val();
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
			//$("#deptBoardId").append('<option value="0">Any</option>');
	   
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
  
  function getDepartmentBoardPositions(){
	$("#searchDataImgForPos").show();
	$("#errdeptBoardPostnId").html("");
	$("#searchResultsBlock").html("");
	$("#searchBy").val("");
	$("#deptBoardPostnId").html('');
     $("#deptBoardPostnId").trigger("chosen:updated");
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
		  searchLevelValue = $('#nominatedPanchayatId').val();;
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
	  $("#deptBoardPostnId").append('<option value="0" >Select Board Position</option>');
	 
	  
	   /* if(result[0].status != "Applied"){
		   $("#deptBoardPostnId").append('<option value="0">Any</option>');
	  }  */
		for(var i in result){
			if(result[i].name != null && result[i].id == globalposId){
					$("#deptBoardPostnId").append('<option selected="selected" value='+result[i].id+' id="position'+result[i].name+'" attr_getposition="selected-position">'+result[i].name+'(' +result[i].count+ ')</option>');
					
					
				}else if(result[i].name != null){
					$("#deptBoardPostnId").append('<option value='+result[i].id+' id="position'+result[i].name+'" attr_getposition="selected-position">'+result[i].name+'(' +result[i].count+')</option>');
					
				}
				/* var object = {
					memberId : result[i].MemberId,
					positionId : result[i].id,
					positionName : result[i].name,
					vacancies : result[i].count,
					filled : 0
				}
				globalObjArr.push(object); */
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
		$('#searchResultsBlock').html(spinner);
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
			memberShipCardNo=$("#searchBy").val().trim();
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
					searchResultBlock(result);
					
				}
				 else
				{
					$('#searchResultsBlock').html('');
					//$('#cadreSearchDtls').html("");
					  $('#textId').html("");
					  $("#searchResultsBlock").html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
					//$('#cadreSearchDtls').html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
				} 
			});  
}
	//hideDetails();
function hideDetails(){
	$('.ramakrishnaCls').hide();
	//$('#searchDivId').hide();
	//$("#uploadFlDivId").hide();
	$(".submitBtnId").hide();
	//$("#addedRefferalsDiv").hide();
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
  
 $(document).on("click",".involveBlockNew",function(){
	$("#searchMemberAjax").css("display","none");
	$("#myModal").modal('show')
	$("#apptmemberDetailsDiv").html('');
	commontdpCadreIds = [];;
	 $("#apptmemberDetailsDiv").html("");
	 $("#advanceSearchTypeId").val(0);
	var select = new Dropkick("#advanceSearchTypeId");
				select.refresh();	
		showHideBySearchType();	
					disableByLevel(1);			
					
});
function setToDefaultAdvancedSearch()
	{	
			$("#advanceDesignationId").val(0);
			$("#advanceDesignationId").dropkick('reset');
			
			$("#alertlevelId").val(0);	
			$("#alertlevelId").dropkick('reset');
			
			$("#stateId").val(0);	
			$("#stateId").dropkick('reset');
			
			$("#referCommitteeId").val(0);	
			$("#referCommitteeId").dropkick('reset');
			
			$("#referdistrictId").val(0);
			$("#referdistrictId").dropkick('reset');
			
		   $("#referconstituencyId").val(0);
			$("#referconstituencyId").dropkick('reset');
			
		   $("#refermandalNameId").val(0);
			$("#refermandalNameId").dropkick('reset');
			
		   $("#referpanchayatId").val(0);
		   $("#referpanchayatId").dropkick('reset');
		   
		   $("#filterStateId").val(0);
		   $("#filterStateId").dropkick('reset');
		   $("#filterDistrictId").val(0);
		   $("#filterDistrictId").dropkick('reset');
		   $("#filterConstituencyId").val(0);
		   $("#filterConstituencyId").dropkick('reset');
		   $("#filterManTowDivId").val(0);
		   $("#filterManTowDivId").dropkick('reset');
		   $("#filterCasteId").val(0);
		   $("#filterCasteId").dropkick('reset');
		   $("#filterAgeId").val(0);
		   $("#filterAgeId").dropkick('reset');
		   $("#filterCasteGroupId").val(0);
		   $("#filterCasteGroupId").dropkick('reset');
		   $("#filterGenderId").val(0);
		   $("#filterGenderId").dropkick('reset');
		   $("#filterEducationId").val(0);
		   $("#filterEducationId").dropkick('reset');
		
	}
function showHideBySearchType(){
		$(".hideStateDivCls").hide();
		$(".hideDistrictDivCls").hide();
		$(".hideConstituencyDivCls").hide();
		$(".hidemanTowDivCls").hide();
		$(".hideCasteCls").hide();
		$(".hideGenderCls").hide();
		$(".hideAgeCls").hide();
		$(".hideCasreGroupCls").hide();
		$(".hideEducationCls").hide();
		$("#apptmemberDetailsDiv").html(" ");
		//setToDefaultAdvancedSearch();
			$('#errorDivId').html('');
			var selectVal = $("#advanceSearchTypeId").val();
			//alert(selectVal);
			if(selectVal == 2)
			{
				$(".advancePRCls,#searchBtnId").show();
				$(".advancePRCls").parent().show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee,.advanceprclsDiv").hide();
				$(".locationsFilterCls").show();
				$(".advanceprcls").show();
				$("#cadreCommitteeDiv_chosen").hide();
				$(".stateShowCls").show();
				$(".levelShowCls").show();
				setToDefaultAdvancedSearch();
				$("#advanceDesignationId").css("display","none");
				getPublicRepresentsDetails();
				//disableByLevel();
				
			}
			else if(selectVal == 3)
			{
				$("#searchBtnId").show();
				$(".advancePRCls").hide();
				$(".advancePRCls").parent().hide();
				$(".advanceNameCls").hide();
				$(".advanceCadreCommittee").show();
				$(".locationsFilterCls").show();
				$(".advanceprcls").hide();
				$(".stateShowCls").show();
				$(".levelShowCls").show();
				$(".advanceprclsDiv").hide();
				$("#cadreCommitteeDiv_chosen").show();
				$("#cadreCommitteeDiv").css("display","none");
				$(".chosen-choices").css("display","block");
				getCommitteeRoles();
				$(".referRolesCheck").removeAttr("checked");
				setToDefaultAdvancedSearch();
				//disableByLevel();
			}
			else if(selectVal == 1)
			{
				$("#searchBtnId").show();
				$(".stateShowCls").show();
				$(".advanceprclsDiv").show();
				$(".advanceNameCls").attr("placeholder"," Search by Name");
				$(".advanceNameCls").show();
				$(".levelShowCls").show();
				$(".advancePRCls").hide();
				$(".advancePRCls").parent().hide();
				$("#cadreCommitteeDiv_chosen").hide();
				$("#referCommitteeDiv").hide();
				clearNameSearchTypeFields();
				$("#advanceSearchValueId").val('');
				
			}
			else if(selectVal == "mobileno" || selectVal == "mebershipno" || selectVal == "votercardno")
			{
				$(".levelShowCls").hide();
				$(".stateShowCls").hide();
				$(".advanceprcls").show();
				if(selectVal == "mobileno")
				$(".advanceNameCls").attr("placeholder"," Search by MobileNo");
			    if(selectVal == "mebershipno")
			    $(".advanceNameCls").attr("placeholder","Search by MembershipNo");
			    if(selectVal == "votercardno")
					 $(".advanceNameCls").attr("placeholder","Search by VotercardNo");
				$(".advanceNameCls").show();
				$(".advancePRCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").show();
				$("#advanceSearchValueId").val("");
				$(".advanceprclsDiv").show();
				$("#searchBtnId").show();
			}
			else if(selectVal == 4 || selectVal == 5 || selectVal == 6 || selectVal == 7 || selectVal == 8)
			{
				setToDefaultAdvancedSearch();
				$("#searchBtnId").show();
				$(".hideStateDivCls").show();
				$(".hideDistrictDivCls").show();
				$(".hideConstituencyDivCls").show();
				$(".hidemanTowDivCls").show();
				$(".advanceNameCls").hide(); 
				$(".levelShowCls").hide();
                $(".stateShowCls").hide();
		        $(".locationsFilterCls").hide();
                $(".advanceCadreCommittee").hide();	
                $(".advancePRCls").hide();							

				if(selectVal == 4)
					$(".hideCasteCls").show();
				else if(selectVal == 5)
					$(".hideGenderCls").show();
				else if(selectVal == 6)
					$(".hideAgeCls").show();
				else if(selectVal == 7)
					$(".hideCasreGroupCls").show();
				else if(selectVal == 8)
					$(".hideEducationCls").show();
			}
			else
			{
				
				$(".levelShowCls").hide();
				$(".stateShowCls").hide();
				$(".advanceprcls").show();
				$(".advanceNameCls").show();
				$(".advancePRCls").hide();
				$(".advanceCadreCommittee").hide();
				$(".locationsFilterCls").show();
				$("#advanceSearchValueId").val("");
				$(".advanceprclsDiv").hide();
			}
				disableByLevel('');
				$(".stateCls").show();
				$(".distCls").hide();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
	}
	function  clearNameSearchTypeFields(){
		  clearLevelField();
		  clearStateField();
	  }
	   function clearLevelField(){
		  $("#alertlevelId").val(0);	
		  $("#alertlevelId").dropkick('reset'); 
	   }
	   function clearStateField(){
		 $("#stateId").val(0);	
		 $("#stateId").dropkick('reset');
	   }
	function getCommitteeRoles(){
    	var jsObj={
    			task:"roles"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAllCommitteesAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
				
				var str ='';
				str +='<option id="0" attr_value="All"  >All</option>';
					for(var i in result){
						str +='<option value="'+result[i].id+'" attr_value="'+result[i].name+'"  >'+result[i].name+'</option>';
					}
				
				$("#cadreCommitteeDiv").html(str);
				$("#cadreCommitteeDiv").chosen();
				$("#cadreCommitteeDiv").trigger("chosen:updated");
				$("#cadreCommitteeDiv_chosen").addClass("m_top20");
				$("#cadreCommitteeDiv_chosen").addClass("addwidth");
				});			  
    }
	function getPublicRepresentsDetails(){
    	 $("#advanceDesignationId").html('');
    	var jsObj={
    			task:"publicRepresentatives"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getPublicRepresentativeTypes.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    		if(result != null && result.length > 0){
				str +='<option value="0">All</option>';
				for(var i in result){
					str +='<option value='+result[i].id+'>'+result[i].name+'</option>';
				}
				 $("#advanceDesignationId").html(''+str+'');
				 $("#advanceDesignationId").dropkick();
				 var select = new Dropkick("#advanceDesignationId");
				 select.refresh();
			}
		   });	
	}
function disableByLevel(index)
  {
	  setDefault(index);	
	  var levelId = $("#alertlevelId"+index).val();
	  var districtId = $("#referdistrictId"+index).val();
	  var constituencyId = $("#referconstituencyId"+index).val();
	  var panchayatId = $("#referpanchayatId"+index).val();
	  var mandalId = $("#refermandalNameId"+index).val();
	  var select = new Dropkick("#referdistrictId"+index);
		select.refresh();
		
		if(levelId != 2 && levelId != 0)
		{
			getDistrictsForReferPopup(index);
		}
		
		if(levelId == 2 || levelId == 0)
		{
			  $("#referdistrictId"+index).find('option').not(':first').remove();
			  $("#referconstituencyId"+index).find('option').not(':first').remove();
			  $("#refermandalNameId"+index).find('option').not(':first').remove();
			  $("#referpanchayatId"+index).find('option').not(':first').remove();
			   var select = new Dropkick("#referdistrictId"+index);
				select.refresh();
			   var select = new Dropkick("#referconstituencyId"+index);
				select.refresh();
				var select = new Dropkick("#refermandalNameId"+index);
				select.refresh();
				var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).hide();
				$(".constiCls"+index).hide();
				$(".mandalCls"+index).hide();
				$(".panchayatCls"+index).hide();
				
		}
		else if(levelId == 3)
		{
			  $("#referconstituencyId"+index).find('option').not(':first').remove();
			  $("#refermandalNameId"+index).find('option').not(':first').remove();
			  $("#referpanchayatId"+index).find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId"+index);
				select.refresh();
				var select = new Dropkick("#refermandalNameId"+index);
				select.refresh();
				var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).hide();
				$(".mandalCls"+index).hide();
				$(".panchayatCls"+index).hide();
		}
		else if(levelId == 4)
		{
			  $("#referconstituencyId"+index).find('option').not(':first').remove();
			  $("#refermandalNameId"+index).find('option').not(':first').remove();
			  $("#referpanchayatId"+index).find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId"+index);
				select.refresh();
				var select = new Dropkick("#refermandalNameId"+index);
				select.refresh();
				var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).show();
				$(".mandalCls"+index).hide();
				$(".panchayatCls"+index).hide();
		}
		else if(levelId == 5)
		{
			$("#referpanchayatId"+index).find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).show();
				$(".mandalCls"+index).show();
				$(".panchayatCls"+index).hide();
		}
		else if(levelId == 6)
		{
			$("#referpanchayatId"+index).find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId"+index);
				select.refresh();
				$(".stateCls"+index).show();
				$(".distCls"+index).show();
				$(".constiCls"+index).show();
				$(".mandalCls"+index).show();
				$(".panchayatCls"+index).show();
		}
		
  }
  function getDistrictsForReferPopup(index) {
{
	var stateId = $("#stateId"+index).val();
	var jobj = {
		stateId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsForStateAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		var str='';
		str+='<option value="0">All</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				//if(result[i].id != 517){
					str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
				//}
			}
		}
		$("#referdistrictId"+index).html(str);
		$("#referdistrictId"+index).dropkick();
		var select1 = new Dropkick("#referdistrictId"+index);
		select1.refresh();
	});
 }
  }
function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }
	var cloneCount=1;
	var involvedCadreIds = [];
	var globalSelectedMemberIdsArr = [];
	
   $(document).on("click",".apptDetailsDiv",function(){
		
		 if($(this).is(':checked')){
		
			 $("#involvedCandidatesDiv").show();
			 $(".membersBlock").show();
			 $("#addedRefferalsDiv").show();
			  var name  = $(this).attr("attr_name");
			  var image = $(this).attr("attr_img_url");
			  var attrId = $(this).attr("attr_id");
			  var attrConsti =  $(this).attr("attr-consti");
			   var mobile = $(this).attr("attr_mobile");
			
			var str ='';
			str+='<div id="involveBlockParent'+attrId+'" class="col-md-12 col-xs-12 col-sm-6 m_top10">';
            str+='<div class="involveBlock" attr_cadreId="'+attrId+'">';
			str+='<div class="media"><div class="media-left">';
			str+='<img src="http://www.mytdp.com/'+image+'" onerror="setDefaultImage(this);" alt="image" style="height:30px;width:30px;" class="img-circle">';
			str+='</div>';
			str+='<div class="media-body">';
			str+='<input type="hidden" class="form-control memberDatacls" name="nominatedPostDetailsVO.subList2['+cloneCount+'].tdpCadreId" value="'+attrId+'"/>';
			str+='<div class="col-md-12"><b>'+name+'</b></div>';
			str+='<div class="col-md-12"><b>'+mobile+'</b></div>';
			str+='<div class="col-md-12"><label>'+attrConsti+'</label></div>';
			str+='<div class="col-md-12"><div class="form-inline">';
			str+='<div class="onoffswitch" style="display:inline-block">';
			str+='<label class="onoffswitch-label" for="myonoffswitch'+cloneCount+'">';
            str+='<span class="onoffswitch-inner"></span>';
            str+='<span class="onoffswitch-switch"></span>';
			str+='</label>';
			str+='</div>';
			str+='</div></div></div></div><span class="closeIcon" id="'+attrId+'" clone_block_count="'+cloneCount+'"><i class="glyphicon glyphicon-remove removeIconNew" style="display: block;"  ></i></span></div></div>';
			$("#duplicateCandidateBlock").html('');
			
			if(jQuery.inArray(attrId, involvedCadreIds) == -1)
			{
				involvedCadreIds.push(attrId);	
				
				$(".membersBlock").append(str);
				$("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
			}else{
				var duplicateStr ='';
				duplicateStr+='<p class="text-capital" >'+name+'</p>';
				duplicateStr+='<p>'+mobile+'</p>';
				duplicateStr+='<p class="text-capitalize">'+attrConsti+'</p>';
				$("#duplicateCandidateBlock").html(''+duplicateStr+'');
				$("#myModalConformation").modal('show');
			}
			 cloneCount = cloneCount+1;
			globalSelectedMemberIdsArr.push(attrId);
		 }else{ 
			var attrId = $(this).attr("attr_id");
			cloneCount = cloneCount-1;
			var index = involvedCadreIds.indexOf(attrId);
			if (index >= 0) {
				  involvedCadreIds.splice( index, 1 );
				}
			$("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
			$("#involveBlockParent"+attrId).remove();
			
			removeParticularValue(globalSelectedMemberIdsArr,attrId);
		 }
   });
   
    function removeParticularValue(arr, item) {
	      for(var i = arr.length; i--;) {
	          if(arr[i] === item) {
	              arr.splice(i, 1);
	          }
	      }
	  }
    $(document).on("click",".closeIcon",function(){
	var id =0;
	var retVal = confirm("Are you sure want to remove this refer ?");
               if( retVal == true ){
				    id=$(this).attr("id");
					  $(".involveBlock").each(function(){
						var cadreId = $(this).attr("attr_cadreId") ; 
							if(id == cadreId)
							{
								$("#involveBlockParent"+id).remove();
							}
					  });
				  $(".candidatecls"+id).prop('checked', false); 
				  $(".close"+id).prop('checked', false); 
				 // involvedCadreIds.pop(id);	
				  removeParticularValue(globalSelectedMemberIdsArr,id);
				  removeParticularValue(involvedCadreIds,id);
				  $("#involvedMembers").html('('+involvedCadreIds.length+' - Members added)');
				return true;
               }
               else{
                  return false;
               }
			   
}); 
function getDetailsBySrch()
	{
		$("#apptmemberDetailsDiv").html("");
		$("#errorDivId").html(" ");
		
		var searchValue=$("#advanceSearchValueId").val();
		
		var errStr=false;
		if($("#advanceSearchTypeId").val()==0){
			 $("#errorDivId").html("Please Select Search Type");
			 errStr=true;
		}
		else if($("#advanceSearchTypeId").val()=="mobileno"){			
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errorDivId").html("Please enter Mobile No");
					  errStr=true;
				 }	
				else if(searchValue.length != 10 || isNaN(searchValue)){		
					$("#errorDivId").html("Please Enter Valid Mobile Number");
					 errStr=true;
				}
		}else if($("#advanceSearchTypeId").val() == "mebershipno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errorDivId").html("Please Enter MembershipNo ");
					  errStr=true;
			}else if(searchValue.length !=8 || isNaN(searchValue)){
				  $("#errorDivId").html("Please Enter valid Membership ");
					  errStr=true;
			}
		}else if($("#advanceSearchTypeId").val() == "votercardno"){
			if(searchValue ==null || searchValue.length ==0 || searchValue == undefined || searchValue ==""){
					  $("#errorDivId").html("Please Enter Votercardno ");
					  errStr=true;
			}
		}
		
		else
		{
			 if((searchValue.length >0 && searchValue.length < 10) || isNaN(searchValue)){
								 
					$("#errorDivId").html("Please Enter Valid Mobile Number");
					 errStr=true;
				}
		}
		if(errStr){
			return;
		}else{
				getSearchDetails("refferSearch");
			}
		
	}
	function getSearchDetails(type){
		$('#searchResultsBlock').html(spinner);
	  var aptUserId = 0;
	  if(type == "mainSearch"){
		  var searchType = "votercardno";
		 var searchValue = $("#searchBy").val().trim();
	  }else{
		  $("#searchMemberAjax").css("display","block");
		  var searchType = $("#advanceSearchTypeId").val();
		  var searchValue = $("#advanceSearchValueId").val();
		  
	  }
		
		var jsObj={
			searchType:searchType,
			searchValue:searchValue,
			aptUserId:aptUserId
		  }
		$("#apptmemberDetailsDiv").html('');
		  	$.ajax({
				type : 'POST',
				url : 'getsearchRequestedMembersAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				if(type == "mainSearch"){
					if(result !=null && result.length>0){
						voterSearchResultBlock(result);
					}else
					{
						$('#searchResultsBlock').html('');
						$('#textId').html("");
						  $("#searchResultsBlock").html("<span style='font-weight:bold;text-align:center;'> No Data Available...</span>");
					} 					
				}else{
					$("#searchMemberAjax").css("display","none");
					
					if(result !=null && result.length>0){
						buildapptmemberDetails(result);
					
					}else{
						$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
					}
					
				}
		  }); 
	 }
function voterSearchResultBlock(result){
	var position = $("#deptBoardPostnId option:selected").text();
	var postitonCunt = "";
	var postionArr = position.split("(");
	var level = $("#boardLvlId option:selected").text();
  var board = $("#deptBoardId option:selected").text();
	if(postionArr != "Select Board Position"){
	position = postionArr[0];
	var postitonCunt = postionArr[1].trim().replace(")", "");
	}
	var boardId = $("#deptBoardId").val();
	var levelId = $("#boardLvlId").val();
	var uniqueId = ''+levelId+''+boardId+''+position+'';
	var block='';
	block+='<h5 style="font-weight:600">SEARCH RESULTS  <span style="font-size:12px;font-weight:normal">   -Found '+result.length+' Results</span></h5>';
	block+='<ul class="nav navbar-nav col-sm-12"  style="margin-top:15px !important">';
	for(var i in result){
		var array = JSON.parse("[" + result[i].enrollmentYears + "]");
		enrollmentYears = enrollmentYears.filter(function(val) {
		  return array.indexOf(val) == -1;
		});
	block+='<li class="">';
        block+='<div class="panel panel-default">';
            block+='<div class="panel-heading">';
				/*block+='<div class="deleteMember"><i class="fa fa-times" aria-hidden="true" style="padding: 0px 6px 5px;"></i></div>';*/
                block+='<div class="text-center"><p class="iconBlock memberDetails"><img src="http://www.mytdp.com/'+result[i].imageURL+'" style="width: 50px; height: 50px; margin-left: -2px; margin-top: -6px;border-radius:50%;"></p></div>';
            block+='</div>';
            block+='<div class="panel-body">';
				block+='<p class="memberDetails cadreName" value="5" data-toggle="tooltip" data-placement="top" title="Cadre Name"><span>N</span>'+result[i].name+'</p>';
				
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Voter Id"><span style="padding:4px 8px">V</span>'+result[i].voterCardNo+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="MemberShip Number"><span style="padding:4px 6px">M</span>'+result[i].memberShipId+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Mobile Number"><span style="padding:4px 6px">M</span>'+result[i].mobileNo+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Constituency Name"><span>A</span>'+result[i].constituency+'</p>';
                block+='<p class="memberDetails" data-toggle="tooltip" data-placement="top" title="Enrollment Years"><span>C</span>';
				var nonRepeatedEnrollmnts = [];
				for(var l in array)
				{
					if(!(nonRepeatedEnrollmnts.indexOf(array[l]) > -1)){
							nonRepeatedEnrollmnts.push(array[l]);
					block+='<span style="margin-right:2px;">'+array[l]+'</span>';
					}
				}
				
				if(enrollmentYears != null && enrollmentYears.length >= 1)
				{
					for(var k in enrollmentYears)
					{
						block+='<span style="border:1px solid red;margin-right:2px;text-decoration:line-through">'+enrollmentYears[k]+'</span>';
					}
				}
				
				block+='</p>';
			block+='</div>';
            block+='<div class="panel-footer">';
			
			if(result[i].previousElections != null && result[i].previousElections.length>0){
				for(var s in result[i].previousElections){
					block+=''+parseInt(parseInt(s)+1)+') <label>'+result[i].previousElections[s].casteName+' &nbsp&nbsp&nbsp<i class="fa fa-info tooltipClass" style="cursor:pointer;" title="'+result[i].previousElections[s].electionType+':'+result[i].previousElections[s].roleName+' --> '+result[i].previousElections[s].occupation+' Dept --> '+result[i].previousElections[s].role+' --> '+result[i].previousElections[s].casteCategory+'"> </i></label> <br> ';
				}
			}else{
				 block+='<label class="checkbox-inline" ><input type="checkbox" value="" attr_cadreId="'+result[i].tdpCadreId+'" class="selectMember" attr_position_type="'+level+' &nbsp;Level - '+board+' &nbsp;Board - '+position+' &nbsp;Position " attr_postion_count="'+postitonCunt+'" attr_unique_id='+uniqueId+' attr_candidate_id="'+nominationPostCandidateId+'">Select Member</label>';
			}
               
				
            block+='</div>';
        block+='</div>';
    block+='</li>';
	}
	block+='<ul>';
	block+='<div class="clearfix"></div>';
	block+='<p class="m_top20">Note:   1) Please select matches profile     2) You Can add Multiple members to above selected Post Name</p>';
    $("#searchResultsBlock").html(block);
	$(".tooltipClass").tooltip();
	
}	 
	 function handleBySearchType()
	{
		getAdvancedSearchDetails();
				
	}
	function getAdvancedSearchDetails(){
		$("#apptmemberDetailsDiv").html("");
		var statusArr=[];
		var tdpCadreIds=[];
		var level;
		var levelValue;
		var tehsilId = 0;
		var committeeId = 0;
		var referCommitteeId;
		var errorStr='';
		var levelStr;
		$("#errorDivId").html('');
		var searchType;
		var searchValue = "";
		var districtId=0;
		var constituencyId=0;
		var mandalId = 0;
		var panchayatId=0;
		var levelId=0;
		var stateId=0;
		var advanceSearchType = $("#advanceSearchTypeId").val();
		stateId = $("#stateId").val();
		 if(advanceSearchType==0){
			 errorStr='Please Select Search Type';
			 $("#errorDivId").html(errorStr);
			 return;
		 }
		  var aptUserId = 0;
		if(advanceSearchType == 1)
		{
			levelStr ="";
			 searchType = "name";
			 searchValue = $("#advanceSearchValueId").val().trim();
			 if(searchValue == null || searchValue.length == 0 ){						 
				       errorStr='Please Enter Name';
				       $("#errorDivId").html(errorStr);
				     return;
			     }else if(searchValue.length<3){
				 errorStr='Name should be minimum three characters.';
				 $("#errorDivId").html(errorStr);
				 return; 
			 }
			 var numericExpression =  /^[a-z,A-Z," "]+$/;
					if(!$("#advanceSearchValueId").val().match(numericExpression)){
						 errorStr='Enter characters Only';
						  $("#errorDivId").html(errorStr);
					 return;
					 }
	    }
		else if(advanceSearchType == 2)
		{
			 searchType = "publicRepresentative";
			 searchValue = $("#advanceDesignationId").val();
			
		}
		else if(advanceSearchType == 3)
		{
			 searchType = "CadreCommittee";
				$("#cadreCommitteeDiv option:selected").each(function ()
			{		
				var desgnaValue = $(this).attr("value");
				if(desgnaValue ==null || desgnaValue =="" || desgnaValue == undefined){
					return false;
				}
				else{
					statusArr.push($(this).attr("value"));
				}		
			});
			 referCommitteeId = $("#referCommitteeId").val();
		}
		else if(advanceSearchType == 4 || advanceSearchType == 5 || advanceSearchType == 6 || advanceSearchType == 7 || advanceSearchType == 8){
			getSearchDetailsByFilter();
			return;
		}
		else 
		{
			getDetailsBySrch();
			return;
		}
		 districtId = $("#referdistrictId").val();
		 constituencyId = $("#referconstituencyId").val();
		var tehsilName =  $("#refermandalNameId selected:option").text();
		if($("#refermandalNameId").val() > 0){
			if(tehsilName.indexOf('Mandal') == -1)
		tehsilId = "2"+$("#refermandalNameId").val();
		else
		tehsilId = "1"+$("#refermandalNameId").val();
		}
		if($("#refermandalNameId").val() == 0)
		tehsilId = $("#refermandalNameId").val();
	     panchayatId = $("#referpanchayatId").val();
		 
		 var panchayatName =  $("#referpanchayatId selected:option").text();
		 if($("#referpanchayatId").val() > 0){
			if(panchayatName.indexOf('WARD') == -1)
		panchayatId = "1"+$("#referpanchayatId").val();
		
		}
		
		 levelId  = $("#alertlevelId").val();
		 var alertLevelId =levelId;
		if(levelId == 2)
		{
			
			level = "state";
			
			alertLevelId = 10; 
		
		}
			
		if(levelId == 3)
		{
			level = "district";
			alertLevelId = 11; 
		}
			
		if(levelId == 4)
		{
			level = "constituency";
			alertLevelId = 1; 
		}
			
		if(levelId == 5)
		{
			level = "mandal";
			alertLevelId = 6;
		}
			
		if(levelId == 6)
		{
			level = "village";
			alertLevelId = 6;
		}
			
		if(districtId == 0)
		{
			levelStr = "state";
			levelValue = 0;
			
		}
		else if(districtId > 0 && constituencyId == 0)
		{
			levelStr = "district";
			levelValue = districtId;
			
		}
		else if(districtId > 0 && constituencyId > 0 && tehsilId == 0)
		{
			levelStr = "constituency";
			levelValue = constituencyId;
			
		}
		
		else if(districtId > 0 && constituencyId > 0 && tehsilId > 0 && panchayatId == 0)
		{
			
			levelStr = "mandal";
			levelValue = tehsilId;
			
		}
		
		else if(districtId > 0 && constituencyId > 0 && tehsilId > 0 && panchayatId > 0)
		{
			levelStr = "village";
			levelValue = panchayatId;
			
		}
		$('#errorDivId').html(errorStr);
		if(levelId == 2){
		/*if(stateId==0 || stateId=='select'){
			
				errorStr +="Please Select State";
			}*/
		}
          if(levelId == 3){
		
			 districtId = $("#referdistrictId").val();
			
			/*if(districtId==0 || districtId=='select'){
			
				errorStr +="Please Select District";
			}*/
		}
		
		 else if(levelId == 4){
			 districtId = $("#referdistrictId").val();
			/*if(districtId==0 || districtId=='select'){
				
				errorStr +="Please Select District";
				$("#errorDivId").html(errorStr);
				return ;
				
			}
			  if(constituencyId == 0 || constituencyId=='select'){
				 constituencyId = $("#referconstituencyId").val();
				errorStr +="Please Select Assembly";
				$("#errorDivId").html(errorStr);
				return;
			}*/
		}
		
		else if(levelId == 5 || levelId == 6){
			 districtId = $("#referdistrictId").val();
			if(districtId==0 || districtId=='select'){
				
				errorStr +="Please Select District";
				$("#errorDivId").html(errorStr);
				return ;
				
			}
		}
		if(errorStr.length >0)
       {
	  $('#errorDivId').html(errorStr);
	  return ;
       } 
	   
	//Party Commitee Members	
	if(advanceSearchType !=null && advanceSearchType == 3){
		$("#cadreCommitteeDiv option:selected").each(function ()
		{		
			var desgnaValue = $(this).attr("value");
			if(desgnaValue ==null || desgnaValue =="" || desgnaValue == undefined){
				return false;
			}
			else{
				statusArr.push($(this).attr("value"));
			}		
		});
		committeeId = referCommitteeId;	
	}
	
	//Public Representatives
	if(advanceSearchType !=null && advanceSearchType == 2){
		var desgnaValue = $("#advanceDesignationId").val();
		if(desgnaValue > 0)
		statusArr.push(desgnaValue);
		committeeId = "0";	
	}
		if(errorStr.length>0){
			$("#errorDivId").html(errorStr);
			return;
		}
		$("#searchMemberAjax").css("display","block");
	
		$("#apptmemberDetailsDiv").html("<center><img src='images/search.gif'/> </center>");
		
		var jsObj={
			searchType:searchType,
			searchValue:searchValue,
			designations:statusArr,
			committeeId:committeeId, // "PR" -- if public representatives
			levelId:alertLevelId,
			districtId:districtId,
			constituencyId:constituencyId,
			mandalId:tehsilId,
			panchayatId:panchayatId,
			stateId:stateId,
			levelStr:levelStr,
			aptUserId:aptUserId
		}
		$("#apptmemberDetailsDiv").html('');
		  	$.ajax({
				type : 'POST',
				url : 'getAdvancedSearchDetailsAction.action',
				dataType : 'json',
				data: {task:JSON.stringify(jsObj)}
			}).done(function(result){
				$("#searchMemberAjax").css("display","none");
				$("#apptmemberDetailsDiv").html("");
				if(result !=null && result.length>0){
				buildapptmemberDetails(result);
				
				}else{
					$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
				}
		  }); 
	}
	function buildLevels()
	{

		var searchType = $("#advanceSearchTypeId").val();

		if(searchType != null && searchType=="mobileno"){
		 $("#searchNameLabel").html("Mobile Number");	
		}else if(searchType =="mebershipno"){
			$("#searchNameLabel").html("MemberShip Number");	
		}else if(searchType == "votercardno"){
			$("#searchNameLabel").html("VoterCard Number");	
		}else if(searchType =="1"){
			$("#searchNameLabel").html("Name");	
		}
		var str='';
		 $("#alertlevelId").find('option').remove();
		  str+='<option value="2">State</option>';
		  str+='<option value="3">District</option>';
		  if(searchType != 3)
		  str+='<option value="4">Constituency</option>';
		  str+='<option value="5">Mandal/Muncipality</option>';
		  str+='<option value="6">Village/Ward</option>';
		  $("#alertlevelId").append(str);
		    $("#alertlevelId").dropkick();
			 var select = new Dropkick("#alertlevelId");
			 select.refresh();
	}
	function setDefault(index)
  {
	  $("#referconstituencyId"+index).find('option').not(':first').remove();
	  $("#refermandalNameId"+index).find('option').not(':first').remove();
	  $("#referpanchayatId"+index).find('option').not(':first').remove();
	    var select = new Dropkick("#referconstituencyId"+index);
		select.refresh();
		var select = new Dropkick("#refermandalNameId"+index);
		select.refresh();
		var select = new Dropkick("#referpanchayatId"+index);
		select.refresh();
  } 
function getLevelByDesignation()
 {
  
    $("#alertlevelId").find('option').remove();
     var stateGrpIds = ["7","12","16"];
   var distGrpIds = ["6","14","15","23","19","20"];
   var mandalGrpIds =["3","25"];
   var constiGrpIds =["2","4","5","8","10","1","9","11","13","17","18","22","21"];
   var designationId =$("#advanceDesignationId").val();
  
   var str ='';
    if(jQuery.inArray(designationId, stateGrpIds ) > -1)
   {
     str+='<option value="2">State</option>';
    
     $("#alertlevelId").append(str);
   }
  else if(jQuery.inArray(designationId, distGrpIds ) > -1)
   {
     str+='<option value="2">State</option>';
     str+='<option value="3">District</option>';
     $("#alertlevelId").append(str);
   }
   else if(jQuery.inArray(designationId, distGrpIds ) > -1)
   {
     str+='<option value="2">State</option>';
     str+='<option value="3">District</option>';
      str+='<option value="4">Constituency</option>';
     $("#alertlevelId").append(str);
   }
   else if(jQuery.inArray(designationId, mandalGrpIds ) > -1)
   {
     str+='<option value="2">State</option>';
     str+='<option value="3">District</option>';
     str+='<option value="5">Mandal/Muncipality</option>';
     $("#alertlevelId").append(str);
   }
   
  else
   {
      str+='<option value="2">State</option>';
      str+='<option value="3">District</option>';
       str+='<option value="4">Constituency</option>';
      //str+='<option value="5">Mandal/Muncipality</option>';
      //str+='<option value="6">Village/Ward</option>';
      $("#alertlevelId").append(str);
   }
     $("#alertlevelId").dropkick();
       var select = new Dropkick("#alertlevelId");
       select.refresh();
           disableByLevel('');
     } 
	 
	 $(document).on("change","#filterStateId",function(){
	var stateId = $(this).val();
	getFilterDistrictsForStates(stateId);
});

function getFilterDistrictsForStates(state){
   var jsObj=
   {				
		stateId:state,				
		task:"getDistrictsForState"		
	}
	$.ajax({
		  type:'GET',
		  url: 'getNewDistrictsOfStateSplittedAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		$("#filterDistrictId  option").remove();
		$("#filterDistrictId").append('<option value="0">Select District</option>');			
		if(result !=null && result.length>0){
			for(var i in result){
				//if(result[i].id != 517){
					$("#filterDistrictId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				//}
			}
		}
		$("#filterDistrictId").dropkick();
		 var select = new Dropkick("#filterDistrictId");
		 select.refresh();
	});
}

$(document).on("change","#filterDistrictId",function(){
	var districtId = $(this).val();
	var stateId = $("#filterStateId").val();
	//getConstituenciesForDistrictsOfAddChnge(districtId,stateId);
	getConstituenciesForDistricts(districtId);
	//	getConstituenciesForDistricts(districtId,stateId);
});

function getConstituenciesForDistrictsOfAddChnge(district,stateId){
	var jsObj={				
		districtId:district
	}
	$.ajax({
		  type:'GET',
		  url: 'getConstituenciesListForDistrictAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	 $("#filterManTowDivId  option").remove();
	 $("#filterConstituencyId  option").remove();
	 $("#filterManTowDivId").append('<option value="0">Select Mandal/Muncipality/Corporation</option>');
	 $("#filterConstituencyId").append('<option value="0">Select Constituency</option>');
	 if(result !=null && result.length>0){
		 for(var i in result){			  
			$("#filterConstituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		}	
		$("#filterConstituencyId").dropkick();
		 var select = new Dropkick("#filterConstituencyId");
		 select.refresh();
	});
}
/*function getConstituenciesForDistricts(district,stateId){
	var distArrTemp = [];
	    distArrTemp.push(district);
		var jsObj={				
			districtId:distArrTemp,
			stateId:stateId,
			task:"getConstituenciesForDistricts"				
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesOfDistrictWithSplittedAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   
		   $("#filterManTowDivId  option").remove();
	 $("#filterConstituencyId  option").remove();
	 $("#filterManTowDivId").append('<option value="0">Select Mandal/Muncipality/Corporation</option>');
	 $("#filterConstituencyId").append('<option value="0">Select Constituency</option>');
	 if(result !=null && result.length>0){
		 for(var i in result){			  
			$("#filterConstituencyId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
		   }
		}	
		$("#filterConstituencyId").dropkick();
		 var select = new Dropkick("#filterConstituencyId");
		 select.refresh();
		});
	}*/
function getConstituenciesForDistricts(district){
	var distArrTemp = [];
	    distArrTemp.push(district);
		var jsObj={				
			districtId:district		
		}
		$.ajax({
			  type:'GET',
			  url: 'getConstituenciesByDistrictAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   $("#filterManTowDivId  option").remove();
	 $("#filterConstituencyId  option").remove();
	 $("#filterManTowDivId").append('<option value="0">Select Mandal/Muncipality/Corporation</option>');
	 $("#filterConstituencyId").append('<option value="0">Select Constituency</option>');
	 if(result !=null && result.length>0){
		 for(var i in result){			  
			$("#filterConstituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		}	
		$("#filterConstituencyId").dropkick();
		 var select = new Dropkick("#filterConstituencyId");
		 select.refresh();
		});
	}
$(document).on("change","#filterConstituencyId",function(){
	var constituencyId=$(this).val();
	var stateId = $("#filterStateId").val();
	getMandalVillageDetails(constituencyId,stateId);
});
function getMandalVillageDetails(constituencyId,stateId){
   var jsObj={				
		constituencyId : constituencyId
	}
	$.ajax({
		  type:'GET',
		  url: 'getMandalsForConstituencyAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
		$("#filterManTowDivId  option").remove();
		$("#filterManTowDivId").append("<option value='0'>Select Mandal/Muncipality</option>");			  
		for(var i in result){
			$("#filterManTowDivId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		}
		$("#filterManTowDivId").dropkick();
		 var select = new Dropkick("#filterManTowDivId");
		 select.refresh();
	});
}
getAllAgeRangesByOrder("filterAgeId");
getAllCasteDetailsForVoters("filterCasteId");
getAllCasteCategoryDetails("filterCasteGroupId");
getEducationalQualifications("filterEducationId");

function getAllAgeRangesByOrder(divId){
   var jsObj={				
	}
	$.ajax({
		  type:'GET',
		  url: 'getAllAgeRangesByOrderAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(divId == "filterAgeId"){
		   $("#filterAgeId  option").remove();
			$("#filterAgeId").append("<option value='0'>Select Age Range</option>");			  
			for(var i in result){
				$("#filterAgeId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#filterAgeId").dropkick();
			 var select = new Dropkick("#filterAgeId");
			 select.refresh();
	   }
		else{
			$("#advancSearchSelectId").append("<option value='0'>Select Age Range</option>");			  
			for(var i in result){
				$("#advancSearchSelectId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#advancSearchSelectId").trigger('chosen:updated');
		}
	});
}

function getAllCasteDetailsForVoters(divId){
   var jsObj={				
	}
	$.ajax({
		  type:'GET',
		  url: 'getAllCasteDetailsForVotersAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(divId == "filterCasteId"){
		   $("#filterCasteId  option").remove();
			$("#filterCasteId").append("<option value='0'>Select Caste</option>");			  
			for(var i in result){
				$("#filterCasteId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#filterCasteId").dropkick();
			 var select = new Dropkick("#filterCasteId");
			 select.refresh();
	   }
		else{
			$("#advancSearchSelectId").append("<option value='0'>Select Caste</option>");			  
			for(var i in result){
				$("#advancSearchSelectId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#advancSearchSelectId").trigger('chosen:updated');
		}
	});
}

function getAllCasteCategoryDetails(divId){
   var jsObj={				
	}
	$.ajax({
		  type:'GET',
		  url: 'getAllCasteCategoryDetailsAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(divId == "filterCasteGroupId"){
		   $("#filterCasteGroupId  option").remove();
			$("#filterCasteGroupId").append("<option value='0'>Select Caste Group</option>");			  
			for(var i in result){
				$("#filterCasteGroupId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#filterCasteGroupId").dropkick();
			 var select = new Dropkick("#filterCasteGroupId");
			 select.refresh();
	   }
		else{
			$("#advancSearchSelectId").append("<option value='0'>Select Caste Group</option>");			  
			for(var i in result){
				$("#advancSearchSelectId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#advancSearchSelectId").trigger('chosen:updated');
		}
	});
}

function getEducationalQualifications(divId){
   var jsObj={				
	}
	$.ajax({
		  type:'GET',
		  url: 'getEducationalQualificationsAction.action',
		  dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(divId == "filterEducationId"){
		   $("#filterEducationId  option").remove();
			$("#filterEducationId").append("<option value='0'>Select Education</option>");			  
			for(var i in result){
				$("#filterEducationId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#filterEducationId").dropkick();
			 var select = new Dropkick("#filterEducationId");
			 select.refresh();
	   }
		else{
			$("#advancSearchSelectId").append("<option value='0'>Select Education</option>");			  
			for(var i in result){
				$("#advancSearchSelectId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
			$("#advancSearchSelectId").trigger('chosen:updated');
		}
	});
}
function getConstituenciesBydistrictForReferPopup(index){
	 var districtId = $("#referdistrictId"+index).val();
	var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getConstituenciesByDistrictAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var constiStr='';
			if(result != null && result.length > 0){
			    constiStr +='<option value="0">Select Assembly</option>';
				for(var i in result){
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#referconstituencyId"+index).html(constiStr);
			 $("#referconstituencyId"+index).dropkick();
			var select = new Dropkick("#referconstituencyId"+index);
			 select.refresh();
			}
		});
 }
function getMandalsByConstituencyForReferPopup(index){
	 var constituencyId = $('#referconstituencyId'+index).val();
	 var jobj = {
		constituencyId : constituencyId
	 }
		$.ajax({
			type : "POST",
			url  : "getMandalsByConstituencyAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
			    mandalStr +='<option value="0">Select Mandal/ Municipality</option>';
				for(var i in result){
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#refermandalNameId"+index).html(mandalStr);
			 $("#refermandalNameId"+index).dropkick();
			 var select = new Dropkick("#refermandalNameId"+index);
			 select.refresh();
			}
		});
 }
 function getPanchayatsForReferPopup(index){
	 $("#referpanchayatId"+index).find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId'+index).val();
	 var  type = $("#refermandalNameId"+index+" option:selected").text();
			   
			 if(type.indexOf("Mandal") == -1) 
				type = "muncipality" ;
			else
				type = "mandal" ; 
			  var jsObj={
						mandalId :mandalId,
						type:type,
						task:""
						
					}
			 $.ajax({
						type:"POST",
						url :"getPanchayatDetailsAction.action",
						 dataType: 'json',
						data: {task:JSON.stringify(jsObj)}
					}).done(function(result){
						var panchyatStr='';
						if(result!=null && result.length>0){
							panchyatStr +='<option value="0">Select Panchayat</option>';
			            for(var i in result){
				 panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			 }
			    $("#referpanchayatId"+index).html(panchyatStr);
			    $("#referpanchayatId"+index).dropkick();
			 var select = new Dropkick("#referpanchayatId"+index);
			 select.refresh();
			}
		   });
		}
		
		function getSearchDetailsByFilter(){
	var searchType;
	var searchValue = 0;
	var locationType;
	var locationVal;
	var gender = "";
	
	var stateId = $("#filterStateId").val();
	var districtId = $("#filterDistrictId").val();
	var constiId = $("#filterConstituencyId").val();
	var manMunId = $("#filterManTowDivId").val();
	if(stateId == 0){
		$("#errorDivId").html("Select State");
		return;
	}
	else if(districtId == 0){
		$("#errorDivId").html("Select District");
		return;
	}
	else if(constiId == 0){
		$("#errorDivId").html("Select Constituency");
		return;
	}
	else if(manMunId == 0){
		$("#errorDivId").html("Select Mandal/Muncipality");
		return;
	}
	
	if(manMunId > 0){
		if(manMunId.substr(0,1) == 1){
			  locationType = "mandal";
		}
		else if(manMunId.substr(0,1) == 2){
			 locationType = "muncipality";
			 
		}								
		locationVal = manMunId.substr(1);
	}
	
	var advanceSearchType = $("#advanceSearchTypeId").val();
	if(advanceSearchType == 4){
		searchType = "caste";
		searchValue = $("#filterCasteId").val();
		if(searchValue == 0){
			$("#errorDivId").html("Select Caste");
			return;
		}
	}
	else if(advanceSearchType == 5){
		searchType = "gender";
		gender = $("#filterGenderId").val();
		if(gender == 0){
			$("#errorDivId").html("Select Gender");
			return;
		}
	}
	else if(advanceSearchType == 6){
		searchType = "age";
		searchValue = $("#filterAgeId").val();
		if(searchValue == 0){
			$("#errorDivId").html("Select Age");
			return;
		}
	}
	else if(advanceSearchType == 7){
		searchType = "casteGroup";
		searchValue = $("#filterCasteGroupId").val();
		if(searchValue == 0){
			$("#errorDivId").html("Select Caste Group");
			return;
		}
	}
	else if(advanceSearchType == 8){
		searchType = "education";
		searchValue = $("#filterEducationId").val();
		if(searchValue == 0){
			$("#errorDivId").html("Select Education");
			return;
		}
	}
	$("#searchMemberAjax").css("display","block");
	var jsObj={
		searchType:searchType,
		searchValue:searchValue,
		locationType:locationType,
		locationVal:locationVal,
		gender:gender
	}
	$("#apptmemberDetailsDiv").html('');
		$.ajax({
			type : 'POST',
			url : 'getNewCadreSearchBySearchTypeAction.action',
			dataType : 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#searchMemberAjax").css("display","none");
			$("#apptmemberDetailsDiv").html("");
			if(result !=null && result.length>0){
			buildapptmemberDetails(result);
			
			}else{
				$("#apptmemberDetailsDiv").html("<center><h4>No Data Available</h4></center>");
			}
	  }); 
}