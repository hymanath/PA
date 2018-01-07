var candidateReferralDoc=[];
var projectDocuments=[];
var coveringLetterDoc=[];
var detailedReportDoc=[];
var globalStatusArr=[];
var selectdWorksArr=[];
var departmentSelectArr=[];
var mainWorkCoveringDocuments=[];
$(document).on("click",".viewBtnCls",function(){
	var petionId = $(this).attr("attr_petiotion_id");
	var endorsNo = $(this).attr("attr_enrorsNo");
	$("#representeeViewId").html("");
	$("#representeeDetailsModelDivId").modal("show");
	getPetitionDetails(petionId,endorsNo);
 });
 
 function getPetitionDetails(petitionId,endorsNo){
	$("#representeeViewId").html(spinner);
	$('#petitionId').val(petitionId);
   var json = {
       petitionId:petitionId,//38,//100031
	   pageType:"viewPage"
    };
  $.ajax({              
    type:'POST',    
    url: 'setPmRepresenteeDataToResultView',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
    if(result != null){
		buildPetitionDetailsView(result);
	}else{
		$("#representeeViewId").html("NO DATA AVAILABLE");
	}
  });
}

function buildPetitionDetailsView(result){
	var str='';
	str+='<div class="pad_pink_bg">';
		str+='<div class="row">';
			str+='<div class="col-sm-6">';
				str+='<h4 class="text-capital m_top10">PETITION STATUS & DETAILS</h4>';
			str+='</div>';
			str+='<div class="col-sm-2 border_left_yash border_right_yash">';
				str+='<p>Representation Type</p>';
				str+='<h5 class="font_weight">'+result.representationType+'</h5>';
			str+='</div>';
			str+='<div class="col-sm-2 border_right_yash">';
				str+='<p>Petition ID</p>';
				str+='<h5 class="font_weight">'+result.petitionId+'</h5>';
			str+='</div>';
			str+='<div class="col-sm-2">';
				str+='<p>Representation Date</p>';
				str+='<h5 class="font_weight">'+result.representationdate+'</h5>';
			str+='</div>';
			
		str+='</div>';
	str+='</div>';
	
	if(result.representationType == "REPRESENTEE"){
		str+='<div class="pad_yash_bg m_top20">';
			str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<h4>MEMBER DETAILS</h4>';
				str+='</div>';
					for(var i in result.representeeDetailsList){
						str+='<div class="col-sm-12 m_top10">';
							str+='<div class="pad_white_bg">';
								str+='<div class="row">';
								
									str+='<div class="col-sm-1">';
										if(result.representeeDetailsList[i].candidatePath != null && result.representeeDetailsList[i].candidatePath>0){
											str+='<img src="'+result.representeeDetailsList[i].candidatePath+'" class="imageCssPopUp"></img>';
										}else{
											str+='<img src="http://www.mytdp.com/images/User.png" class="imageCssPopUp"></img>';
										}
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<div class="pad_light_yash_bg">';
											str+='<p>Name:</p>';
											str+='<h5 class="font_weight m_top5">'+result.representeeDetailsList[i].name+'</h5>';
											if(result.representeeDetailsList[i].tdpCadreId !=null && result.representeeDetailsList[i].tdpCadreId>0){
												str+='<h5 class="font_weight f_12 m_top10">TDP Cadre</h5>';
											}else{
												str+='<h5 class="font_weight f_12 m_top20"></h5>';
											}
											//str+='<h5 class="font_weight f_10">'+result.referDetailsList[i].+'</h5>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<div class="pad_light_yash_bg">';
											str+='<p>Contact Details:</p>';
											if(result.representeeDetailsList[i].email !=null && result.representeeDetailsList[i].email.length>0){
												str+='<p class="m_top5">Email Id:<b class="margin_left">'+result.representeeDetailsList[i].email+'</b></p>';
											}
											if(result.representeeDetailsList[i].mobileNO !=null && result.representeeDetailsList[i].mobileNO>0){
												str+='<p>Contact No:<b class="margin_left">'+result.representeeDetailsList[i].mobileNO+'</b></p>';
											}
											
										str+='</div>';
									str+='</div>';
									
									str+='<div class="col-sm-5">';
										str+='<div class="pad_light_yash_bg">';
											str+='<p>Address Details:</p>';
											str+='<div class="row m_top5">';
											if((result.representeeDetailsList[i].addressVO.panchayatName !=null && result.representeeDetailsList[i].addressVO.panchayatName.length>0) && (result.representeeDetailsList[i].addressVO.tehsilName !=null && result.representeeDetailsList[i].addressVO.tehsilName.length>0)){
												str+='<div class="col-sm-7">';
													if(result.representeeDetailsList[i].addressVO.panchayatName !=null && result.representeeDetailsList[i].addressVO.panchayatName.length>0){
														str+='<p>Village:<b class="margin_left">'+result.representeeDetailsList[i].addressVO.panchayatName+'</b></p>';
													}
													if(result.representeeDetailsList[i].addressVO.tehsilName !=null && result.representeeDetailsList[i].addressVO.tehsilName.length>0){
														str+='<p>Mandal:<b class="margin_left">'+result.representeeDetailsList[i].addressVO.tehsilName+'</b></p>';
													}
													
												str+='</div>';
											}
												
											str+='<div class="col-sm-4">';
													if(result.representeeDetailsList[i].addressVO.assemblyName !=null && result.representeeDetailsList[i].addressVO.assemblyName.length>0){
														str+='<p>Constituency:<b class="margin_left">'+result.representeeDetailsList[i].addressVO.assemblyName+'</b></p>';
													}
													if(result.representeeDetailsList[i].addressVO.districtName !=null && result.representeeDetailsList[i].addressVO.districtName.length>0){
														str+='<p>District:<b class="margin_left">'+result.representeeDetailsList[i].addressVO.districtName+'</b></p>';
													}
												
											str+='</div>';
											str+='</div>';
											
										str+='</div>';
									str+='</div>';
									
								str+='</div>';
							str+='</div>';
						str+='</div>';
					}
				
			str+='</div>';
		str+='</div>';
	}
	
	str+='<div class="pad_yash_bg m_top20">';
			str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<h4>REFERRED BY</h4>';
				str+='</div>';
				
					str+='<div class="col-sm-12 m_top10">';
						for(var i in result.referDetailsList){
							str+='<div class="col-sm-6">';
								str+='<div class="pad_white_bg">';
									str+='<div class="row">';
										str+='<div class="col-sm-3">';
											if(result.referDetailsList[i].candidatePath != null && result.referDetailsList[i].candidatePath.trim().length>0){
												str+='<img src="'+result.referDetailsList[i].candidatePath+'" class="imageCss" style="width:120px;height:120px;"></img>';
												str+='<span style="position: absolute; left: 100px;"><img src="Assests/images/'+result.referDetailsList[i].partyName+'.PNG" class="smallerImg" ></img></span>';
											}else{
												str+='<img src="http://www.mytdp.com/images/User.png" class="imageCss" style="width:120px;height:120px;"></img>';
											}
											if(result.referDetailsList[i].fileNamesList !=null && result.referDetailsList[i].fileNamesList.length>0){
												str+='<h5 class="view_referral_Doc docsViewCls m_top10 f_10" attr_docs="referral" attr_candidate_id="'+result.referDetailsList[i].id+'" style="cursor:pointer;padding:10px;">REFERRAL LETTER </h5>';
												candidateReferralDoc = result.referDetailsList[i].fileNamesList;
											}else{
												str+='<h5 class="view_referral_Doc_empty m_top10 f_10" style="cursor:no-drop;padding:10px;">REFERRAL LETTER </h5>';
												
											}
											
										str+='</div>';
										str+='<div class="col-sm-9">';
											str+='<div class="pad_light_yash_bg">';
												str+='<h5>Name:</h5>';
												if(result.referDetailsList[i].name !=null && result.referDetailsList[i].name.trim().length>0){
													str+='<h4 class="font_weight m_top5">'+result.referDetailsList[i].name+'</h4>';
												}else{
													str+='<h4 class="font_weight m_top5"> - </h4>';
												}
												
												str+='<h5 class="font_weight m_top5">('+result.referDetailsList[i].designation+'),'+result.referDetailsList[i].candidateAddressVO.assemblyName+','+result.referDetailsList[i].candidateAddressVO.districtName+'</h5>';
												
											str+='</div>';
											str+='<div class="pad_light_yash_bg m_top10">';
												str+='<div class="row m_top5">';
													str+='<div class="col-sm-6">';
														str+='<h5>Party:</h5>';
														if(result.referDetailsList[i].partyName !=null && result.referDetailsList[i].partyName.trim().length>0){
															str+='<h5 class="font_weight m_top10">'+result.referDetailsList[i].partyName+'</h5>';
														}else{
															str+='<h5 class="font_weight m_top10"> - </h5>';
														}
														
													str+='</div>';
													str+='<div class="col-sm-6">';
														str+='<h5>Contact Details:</h5>';
														if(result.referDetailsList[i].email !=null && result.referDetailsList[i].email.trim().length>0){
															str+='<h5 class="m_top5 font_weight">Email Id:<b class="margin_left">'+result.referDetailsList[i].email+'</b></h5>';
														}
														if(result.referDetailsList[i].mobileNO !=null && result.referDetailsList[i].mobileNO>0){
															str+='<h5 class="font_weight">Contact No:<b class="margin_left">'+result.referDetailsList[i].mobileNO+'</b></h5>';
														}else{
															str+='<h5 class="font_weight">Contact No:<b class="margin_left"> - </b></h5>';
														}
														
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
						}
					str+='</div>';
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="pad_yash_bg m_top20">';
			str+='<div class="row">';
				str+='<div class="col-sm-12">';
					str+='<div class="col-sm-10">';
						str+='<h4>WORK TYPE DETAILS</h4>';
							str+='<div class="pad_white_bg m_top5">';
								str+='<div class="row">';
									str+='<div class="col-sm-7 border_right_yash">';
										str+='<h5>Name of the Work</h5>';
										if(result.workName !=null && result.workName.trim().length>0){
											if(result.workName !=null && result.workName.length>120){
												str+='<h5 class="font_weight tooltipCls m_top10" data-toggle="tooltip" title="'+result.workName+'">'+result.workName.substring(0,120)+'...</h5>';
											}else{
												str+='<h5 class="font_weight  m_top10">'+result.workName+'</h5>';
											}
											
										}else{
											str+='<h5 class="font_weight  m_top10"> - </h5>';
										}
										
									str+='</div>';
									str+='<div class="col-sm-2 border_right_yash">';
										str+='<div style="padding:5px;background-color:#D1AB66;">';
											str+='<h5>No of Works</h5>';
											str+='<h4 class="font_weight m_top10">'+result.noOfWorks+'</h4>';
										str+='</div>';
									str+='</div>';
									str+='<div class="col-sm-3">';
										str+='<div style="padding:5px;background-color:#D1AB66;">';
											str+='<h5>Work&nbsp;Cost&nbsp;(Est.&nbsp;Cost&nbsp;in&nbsp;Lakh)</h5>';
											if(result.estimateCostStr !=null && result.estimateCostStr>0){
												str+='<h4 class="font_weight m_top10">'+result.estimateCostStr+'</h4>';
											}else{
												str+='<h4 class="font_weight m_top10">0</h4>';
											}
											
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
					str+='</div>';
					str+='<div class="col-sm-2">';
						str+='<h5>DOCUMENTS</h5>';
						if(result.fileList !=null && result.fileList.length>0){
							str+='<div class="view_referral_Doc docsViewCls m_top15 text-center" attr_docs="fileList" style="cursor:pointer;"><i class="fa fa-file-text" aria-hidden="true" style="font-size: 22px;"></i><br/> <h5 class="m_top10">VIEW DOCUMENTS</h5></div>';
							projectDocuments = result.fileList;
						}else{
							str+='<div class="view_referral_Doc_empty m_top15 text-center" attr_docs="fileList" style="cursor:no-drop;"><i class="fa fa-file-text" aria-hidden="true" style="font-size: 22px;"></i><br/> <h5 class="m_top10">VIEW DOCUMENTS</h5></div>';
							
						}
						
					str+='</div>';
				str+='</div>';
				
				str+='<div class="col-sm-12 m_top20">';
					str+='<div class="col-sm-10">';
						str+='<h4 class="text-capital">Status Wise Works Overview</h4>';
							str+='<div class="pad_white_bg m_top5" style="border:1px solid #D1AB66;">';
								str+='<div class="row">';
									str+='<div class="col-sm-3">';
										str+='<div style="padding:5px;background-color:#D1AB66;">';
											str+='<h5>Total Works</h5>';
											str+='<h4 class="font_weight m_top10">'+result.noOfWorks+'</h4>';
										str+='</div>';
									str+='</div>';
									var statusList = 0;
									for(var i in result.statusList){
										if(i==0){
											globalStatusArr=result.statusList[i].subList;
										}
										
										statusList = statusList+1;
										str+='<div class="col-sm-3 m_top5">';
											str+='<div style="padding:5px;background-color:#F5F5F5;">';
												str+='<h5><span class="rankingColor"><span style="top: -3px;position: relative;font-size: 12px;">'+statusList+'</span></span>'+result.statusList[i].value+'</h5>';
												str+='<h4 class="font_weight m_top10">'+result.statusList[i].count+'</h4>';
											str+='</div>';
										str+='</div>';
									}
									
									
								str+='</div>';
							str+='</div>';
					str+='</div>';
					
				str+='</div>';
				
			str+='</div>';
		str+='</div>';
		
		str+='<div class="row m_top20">';
			str+='<div class="col-sm-12">';
		for(var i in result.subWorksList){
				str+='<div class="panel-group" id="accordion'+i+'">';
					str+='<div class="panel panel-default panel-blue">';
						str+='<div class="panel-heading" id="heading'+i+'">';
							if(i==0){
								str+='<a role="button" class="panelCollapseIcon"  data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
							}else{
								str+='<a role="button" class="panelCollapseIcon collapsed"  data-toggle="collapse" data-parent="#accordion'+i+'" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
							}
							
							if(result.subWorksList[i].endorsmentNo !=null && result.subWorksList[i].endorsmentNo !=0){
								str+='<h4 class="panel-title text-capital">Endorsed - '+result.subWorksList[i].endorsmentNo+'</h4>';
							}else{
								str+='<h4 class="panel-title text-capital">Pending Endorsed</h4>';
							}
						 	str+='</a>';
						 str+='</h4>';
						str+='</div>';
						if(i==0){
							str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
						}else{
							str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
						}
						  str+='<div class="panel-body" style="background-color: #F7F7F7;">';
						  if(result.subWorksList[i].endorsmentNo !=null && result.subWorksList[i].endorsmentNo !=0){
									str+='<div class="row">';
										str+='<div class="col-sm-9">';
											str+='<div class="pad_white_bg border_yash">';
												str+='<div class="row">';
													str+='<div class="col-sm-3">';
														str+='<h5 class="font_weight">Endorsment ID</h5>';
														str+='<h5 class="font_weight m_top10">'+result.subWorksList[i].endorsmentNo+'</h5>';
													str+='</div>';
													str+='<div class="col-sm-3">';
														str+='<h5 class="font_weight">No of works</h5>';
														str+='<h5 class="font_weight m_top10">'+result.subWorksList[i].noOfWorks+'</h5>';
													str+='</div>';
													str+='<div class="col-sm-6">';
														str+='<h5 class="font_weight">Endorsment Date</h5>';
														str+='<h5 class="font_weight m_top10">'+result.subWorksList[i].endorsmentDate+'</h5>';
													str+='</div>';
												str+='</div>';
											str+='</div>';
										str+='</div>';
										str+='<div class="col-sm-3">';
										for(var k in result.subWorksList[i].reportTypeFilesList){
											if(result.subWorksList[i].reportTypeFilesList[k].key == "COVERING LETTER"){
												if(result.subWorksList[i].reportTypeFilesList[k].filesList !=null && result.subWorksList[i].reportTypeFilesList[k].filesList.length>0){
													str+='<div class="view_referral_Doc docsViewCls text-center" attr_docs="mainWorkCovering" style="cursor:pointer;"><i class="fa fa-file-text" aria-hidden="true" style="font-size: 22px;"></i> <h5 class="m_top10">VIEW COVERING DOCUMENTS</h5></div>';
													
													mainWorkCoveringDocuments.push(result.subWorksList[i].reportTypeFilesList[k].filesList[0]);
												}else{
													str+='<div class="view_referral_Doc_empty  text-center" attr_docs="mainWorkCovering" style="cursor:no-drop;"><i class="fa fa-file-text" aria-hidden="true" style="font-size: 22px;"></i> <h5 class="m_top10">VIEW COVERING DOCUMENTS</h5></div>';
												}
											}
											
										}
											
										str+='</div>';
									str+='</div>';
								}
									//row closed
									str+='<div class="row m_top10">';
									str+='<div class="col-sm-12">';
										str+='<h5 class="pull-right">';
										str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
											str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT ALL</span> <input type="checkbox" id="inlineCheckbox1" value="" class="workStatusSelectedAllCls" style="margin-top: 2px;" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
										str+='</label>';
										str+='</h5>';
									str+='</div>';
									str+='</div>';
									var workCount = 0;
								str+='<div class="row m_top10">';
									for(var j in result.subWorksList[i].subWorksList){
										workCount = workCount+1;
										str+='<div class="col-sm-6">';
												if(result.subWorksList[i].subWorksList[j].status == "Completed"){
													str+='<div class="panel panel-default panel-completed">';
												}else{
													str+='<div class="panel panel-default panel-pending">';
												}
											  str+='<div class="panel-heading">';
												if(result.subWorksList[i].subWorksList[j].status == "Completed"){
													str+='<div class="row">';
														str+='<div class="col-sm-5">';
															str+='<h3 class="panel-title">WORK No - '+workCount+'</h3>';
														str+='</div>';
														str+='<div class="col-sm-4">';
															str+='<span class="pull-right completed_color"><i class="fa fa-check round_status_completed" aria-hidden="true"></i> Completed</span>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h5 class="pull-right">';
															str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
																str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT</span> <input type="checkbox" id="" value="'+result.subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: 2px;" attr_department_id="'+result.subWorksList[i].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
															str+='</label>';
															str+='</h5>';
														str+='</div>';
													str+='</div>';
												}else if(result.subWorksList[i].subWorksList[j].status == "Pending Endorsement"){
													str+='<div class="row">';
														str+='<div class="col-sm-5">';
															str+='<h3 class="panel-title">WORK No - '+workCount+'</h3>';
														str+='</div>';
														str+='<div class="col-sm-4">';
															str+='<span class="pull-right pending_color"> <i class="fa fa-pause round_status_pending" aria-hidden="true"></i>Pending Endorsement</span>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h5 class="pull-right">';
															str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
																str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT</span> <input type="checkbox" id="" value="'+result.subWorksList[i].subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: 2px;" attr_department_id="'+result.subWorksList[i].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
															str+='</label>';
															str+='</h5>';
														str+='</div>';
													str+='</div>';
												}else if(result.subWorksList[i].subWorksList[j].status == "Pending Final Approval" || result.subWorksList[i].subWorksList[j].status == "Pending - Action Memo" || result.subWorksList[i].subWorksList[j].status == "Pending - Detailed Report"){
													str+='<div class="row">';
														str+='<div class="col-sm-5">';
															str+='<h3 class="panel-title">WORK No - '+workCount+'</h3>';
														str+='</div>';
														str+='<div class="col-sm-4">';
															str+='<span class="pull-right pending_color"> <i class="fa fa-pause round_status_pending" aria-hidden="true"></i>'+result.subWorksList[i].subWorksList[j].status+'</span>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h5 class="pull-right">';
															str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
																str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT</span> <input type="checkbox" id="" value="'+result.subWorksList[i].subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: 2px;" attr_department_id="'+result.subWorksList[i].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
															str+='</label>';
															str+='</h5>';
														str+='</div>';
													str+='</div>';
												}else{
													str+='<div class="row">';
														str+='<div class="col-sm-5">';
															str+='<h3 class="panel-title">WORK No - '+workCount+'</h3>';
														str+='</div>';
														str+='<div class="col-sm-4">';
															str+='<span class="pull-right pending_color"> <i class="fa fa-pause round_status_pending" aria-hidden="true"></i>'+result.subWorksList[i].subWorksList[j].status+'</span>';
														str+='</div>';
														str+='<div class="col-sm-3">';
															str+='<h5 class="pull-right">';
															str+='<label class="checkbox-inline" style="background-color: #fff;padding: 5px;border: 1px solid #ddd;">';
																str+='<span style="margin-left: 0px;margin-right: 25px;">SELECT</span> <input type="checkbox" id="" value="'+result.subWorksList[i].subWorksList[j].workId+'" class="workStatusUpdateCls checkbox'+result.subWorksList[i].endorsmentNo+'" style="margin-top: 2px;" attr_department_id="'+result.subWorksList[i].subWorksList[j].deptId+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">';
															str+='</label>';
															str+='</h5>';
														str+='</div>';
													str+='</div>';
												}
												
												
											 str+='</div>';
											 str+='<div class="panel-body">';
													str+='<div class="row">';
															str+='<div class="col-sm-3" style="border-right: 1px solid #ddd;">';
																str+='<div class="m_top10">';
																	str+='<h5 class="">Work Type</h5>';
																	if(result.subWorksList[i].subWorksList[j].workType !=null && result.subWorksList[i].subWorksList[j].workType.trim().length>0){
																		str+='<h5 class="font_weight m_top10">'+result.subWorksList[i].subWorksList[j].workType+'</h5>';
																	}else{
																		str+='<h5 class="font_weight m_top10"> - </h5>';
																	}
																	
																str+='</div>';
																
																str+='<div class="m_top15">';
																	str+='<h5 class="">Brief Lead</h5>';
																	if(result.subWorksList[i].subWorksList[j].briefLeadName !=null && result.subWorksList[i].subWorksList[j].briefLeadName.trim().length>0){
																		str+='<h5 class="font_weight m_top10">'+result.subWorksList[i].subWorksList[j].briefLeadName+'</h5>';
																	}else{
																		str+='<h5 class="font_weight m_top10"> - </h5>';
																	}
																	
																str+='</div>';
																
																str+='<div class="m_top15">';
																	str+='<h5 class="">Grant Under</h5>';
																	if(result.subWorksList[i].subWorksList[j].grantName !=null && result.subWorksList[i].subWorksList[j].grantName.trim().length>0){
																		str+='<h5 class="font_weight m_top10">'+result.subWorksList[i].subWorksList[j].grantName+'</h5>';
																	}else{
																		str+='<h5 class="font_weight m_top10"> - </h5>';
																	}
																	
																str+='</div>';
																
																str+='<div class="m_top15 margin_bottom">';
																	str+='<h5 class="">Est Budget</h5>';
																	if(result.subWorksList[i].subWorksList[j].estimateCostStr !=null && result.subWorksList[i].subWorksList[j].estimateCostStr>0){
																		str+='<h5 class="font_weight m_top10"><i class="fa fa-inr" aria-hidden="true" style="color:green;"></i> '+result.subWorksList[i].subWorksList[j].estimateCostStr+'</h5>';
																	}else{
																		str+='<h5 class="font_weight m_top10"> - </h5>';
																	}
																	
																str+='</div>';
															str+='</div>';
															str+='<div class="col-sm-9">';
																
																str+='<div class="pad_light_yash_bg border_yash border_radius_5">';
																	str+='<div class="row">';
																		str+='<div class="col-sm-4">';
																			str+='<h5 class="">Subject</h5>';
																			if(result.subWorksList[i].subWorksList[j].subject !=null && result.subWorksList[i].subWorksList[j].subject.trim().length>0){
																				str+='<h5 class="font_weight m_top5">'+result.subWorksList[i].subWorksList[j].subject+'</h5>';
																			}else{
																				str+='<h5 class="font_weight m_top5"> - </h5>';
																			}
																			 
																		str+='</div>';
																		str+='<div class="col-sm-4">';
																			str+='<h5 class="">Sub-Subject</h5>';
																			if(result.subWorksList[i].subWorksList[j].subSubject !=null && result.subWorksList[i].subWorksList[j].subSubject.trim().length>0){
																				str+='<h5 class="font_weight m_top5">'+result.subWorksList[i].subWorksList[j].subSubject+'</h5>';
																			}else{
																				str+='<h5 class="font_weight m_top5"> - </h5>';
																			}
																		str+='</div>';
																		str+='<div class="col-sm-4">';
																			str+='<h5 class="">Department</h5>';
																			if(result.subWorksList[i].subWorksList[j].deptName != null && result.subWorksList[i].subWorksList[j].deptName.trim().length>0){
																				str+='<h5 class="font_weight m_top5">'+result.subWorksList[i].subWorksList[j].deptName+'</h5>';
																			}else{
																				str+='<h5 class="font_weight m_top5"> - </h5>';
																			}
																		str+='</div>';
																	str+='</div>';
																str+='</div>';
																
																str+='<div class="pad_light_yash_bg m_top10 border_yash border_radius_5">';
																	str+='<div class="row">';
																		str+='<div class="col-sm-12">';
																			if(result.subWorksList[i].subWorksList[j].leadName != null && result.subWorksList[i].subWorksList[j].leadName.trim().length>0){
																				if(result.subWorksList[i].subWorksList[j].leadName !=null && result.subWorksList[i].subWorksList[j].leadName.length>35){
																					str+='<h5 class="">Lead: <span class="font_weight f_12 tooltipCls" data-toggle="tooltip" title="'+result.subWorksList[i].subWorksList[j].leadName+'">'+result.subWorksList[i].subWorksList[j].leadName+'</span></h5>';
																				}else{
																					str+='<h5 class="">Lead: <span class="font_weight f_12">'+result.subWorksList[i].subWorksList[j].leadName+'</span></h5>';
																				}
																				
																			}else{
																				str+='<h5 class="">Lead:  - </h5>';
																			}
																			
																			
																		str+='</div>';
																	str+='</div>';
																str+='</div>';
																
																str+='<div class="pad_light_yash_bg m_top10 border_yash border_radius_5">';
																	str+='<div class="row">';
																		str+='<div class="col-sm-4">';
																			str+='<h5 class="">District</h5>'
																			if(result.subWorksList[i].subWorksList[j].addressVO.districtName != null && result.subWorksList[i].subWorksList[j].addressVO.districtName.trim().length>0){
																				str+='<h5 class="font_weight m_top5">'+result.subWorksList[i].subWorksList[j].addressVO.districtName+'</h5>';
																			}else{
																				str+='<h5 class="font_weight m_top5"> - </h5>';
																			}
																		str+='</div>';
																		str+='<div class="col-sm-4">';
																			str+='<h5 class="">Constituency</h5>'
																			if(result.subWorksList[i].subWorksList[j].addressVO.assemblyName != null && result.subWorksList[i].subWorksList[j].addressVO.assemblyName.trim().length>0){
																				str+='<h5 class="font_weight m_top5">'+result.subWorksList[i].subWorksList[j].addressVO.assemblyName+'</h5>';
																			}else{
																				str+='<h5 class="font_weight m_top5"> - </h5>';
																			}
																		str+='</div>';
																		str+='<div class="col-sm-4">';
																			str+='<h5 class="">Mandal</h5>'
																			if(result.subWorksList[i].subWorksList[j].addressVO.tehsilName !=null && result.subWorksList[i].subWorksList[j].addressVO.tehsilName.trim().length>0){
																				str+='<h5 class="font_weight m_top5">'+result.subWorksList[i].subWorksList[j].addressVO.tehsilName+'</h5>';
																			}else{
																				str+='<h5 class="font_weight m_top5"> - </h5>';
																			}
																		str+='</div>';
																	str+='</div>';
																str+='</div>';
																
																str+='<div class="pad_light_yash_bg m_top20 border_yash border_radius_5">';
																	str+='<div class="row">';
																		str+='<div class="col-sm-12">';
																			str+='<h5 class="">WORK DISCRIPTION</h5>'
																			if(result.subWorksList[i].subWorksList[j].workName != null && result.subWorksList[i].subWorksList[j].workName.trim().length>0){
																				if(result.subWorksList[i].subWorksList[j].workName !=null && result.subWorksList[i].subWorksList[j].workName.length>80){
																					str+='<h5 class="font_weight m_top5 tooltipCls" data-toggle="tooltip" title="'+result.subWorksList[i].subWorksList[j].workName+'">'+result.subWorksList[i].subWorksList[j].workName.substring(0,80)+'...</h5>';
																				}else{
																					str+='<h5 class="font_weight m_top5">'+result.subWorksList[i].subWorksList[j].workName+'</h5>';
																				}
																				
																			}else{
																				str+='<h5 class="font_weight m_top5"> - </h5>';
																			}
																		str+='</div>';
																	str+='</div>';
																str+='</div>';
																
															
														str+='</div>';
														
													str+='</div>';
													//row closed
													str+='<div class="pad_light_yash_bg m_top10 border_yash border_radius_5">';
														str+='<div class="row">';
													for(var k in result.subWorksList[i].subWorksList[j].reportTypeFilesList){
														if(result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].key == "DETAILED REPORT"){
															str+='<div class="col-sm-4">';
																if(result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].filesList !=null && result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].filesList.length>0){
																	str+='<h5 class="view_referral_Doc docsViewCls f_11" attr_docs="detailed" style="cursor:pointer;">VIEW&nbsp;DETAILED&nbsp;REPORT</h5>';
																	detailedReportDoc=result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].filesList;
																}else{
																	str+='<h5 class="view_referral_Doc_empty f_11" attr_docs="detailed" style="cursor:no-drop;">VIEW&nbsp;DETAILED&nbsp;REPORT</h5>';
																}
																
															str+='</div>';
															
														}else if(result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].key == "COVERING LETTER"){
															str+='<div class="col-sm-4">';
																if(result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].filesList !=null && result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].filesList.length>0){
																	str+='<h5 class="view_referral_Doc docsViewCls f_11" attr_docs="covering" style="cursor:pointer;">VIEW&nbsp;COVERING&nbsp;LETTER</h5>';
																	coveringLetterDoc=result.subWorksList[i].subWorksList[j].reportTypeFilesList[k].filesList;
																}else{
																	str+='<h5 class="view_referral_Doc_empty f_11" attr_docs="covering" style="cursor:no-drop;">VIEW&nbsp;COVERING&nbsp;LETTER</h5>';
																}
																
																
																
															str+='</div>';
															
														}
													}
													str+='</div>';
												str+='</div>';
													
											  str+='</div>';
											str+='</div>';
										str+='</div>';
										
									}
								str+='</div>';
								str+='<div class="row m_top10">';
									str+='<div class="col-sm-12">';
										str+='<button type="button" class="btn btn-primary btn-sm pull-right updateStatusChangeCls" attr_total_works="'+result.subWorksList[i].noOfWorks+'" attr_enrorsNo="'+result.subWorksList[i].endorsmentNo+'">Small button</button>';
									str+='</div>';
								str+='</div>';
						  str+='</div>';
						str+='</div>';
					 str+='</div>';
				 str+='</div>';
			}
		
			str+='</div>';
		str+='</div>';
			
	$("#representeeViewId").html(str);
	$(".tooltipCls").tooltip();
}
$(document).on("click",".updateStatusChangeCls",function(){
	var totalWorks = $(this).attr("attr_total_works");
	var enrorsNo = $(this).attr("attr_enrorsNo");
	selectdWorksArr=[];
	$(".workStatusUpdateCls").each(function(){
		if($(this).is(":checked")){
			selectdWorksArr.push($(this).val());
			departmentSelectArr.push($(this).attr("attr_department_id"));
		}
	});
	var notSeleWorks = totalWorks - selectdWorksArr.length;
	
	$("#statusChangeId").html('');
	if(globalStatusArr !=null && globalStatusArr.length>0){
		 $("#statusChangeId").append('<option value="0" attr_next_status_id="0" >Select Status</option>');
		for(var i in globalStatusArr){
			var nextStatusId=6;
			if(globalStatusArr[i].key == 1)
				nextStatusId=6;
			else if(globalStatusArr[i].key == 6)
				nextStatusId=7;
			else if(globalStatusArr[i].key == 7)
				nextStatusId=3;
			else if(globalStatusArr[i].key == 3)
				nextStatusId=8;
			else if(globalStatusArr[i].key == 4)
				nextStatusId=4;
			else if(globalStatusArr[i].key == 5)
				nextStatusId=5;	
			if(enrorsNo !=null && enrorsNo>0){
				if(globalStatusArr[i].key !=1){
					$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'">'+globalStatusArr[i].value+' </option>');
				}
				
			}else{
				$("#statusChangeId").append('<option attr_next_status_id="'+nextStatusId+'" value="'+globalStatusArr[i].key+'">'+globalStatusArr[i].value+' </option>');
			}	
			
		}
	}
	$("#statusChangeId").chosen();
	$("#statusChangeId").trigger('chosen:updated');
	
	$("#endorseMentModalDivId").modal("show");
	$("#endorseMentHeadingId").html("Endorsed No - "+enrorsNo+"")
	
	$("#totalWorksId").html(totalWorks)
	$("#selectdWorksId").html(selectdWorksArr.length)
	$("#notSeleWorksId").html(notSeleWorks)
	
});	
$(document).on("change","#statusChangeId",function(){
	var statusId = $(this).val();
	var nextStatusId = $('option:selected', this).attr('attr_next_status_id') ;
	$('#nextStatusId').val(0);
	if(nextStatusId != null && nextStatusId>0)
		$('#nextStatusId').val(nextStatusId);
	if(statusId == 1){
		$("#endorsementDivId").show();
		$("#commentsDivId").show();
		$("#fileUploadingDivId").show();
		$("#leadDivId").show();
		$("#grantDivId").show();
		$("#assignOfficerDivId").show();
		$("#assignDesignationDivId").show();
		$("#fileUploadDiv").show();
		$("#buttonNameId").html("Forward")
		$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');
		initializeSingleUploadDocument("uploadEndorsementDocId");
		getPmBriefLeadList();
		getPmGrantList();
		getLoginUserAccessSubDeptDesignationDetail(departmentSelectArr);
	}else if(statusId == 6){
		$("#commentsDivId").show();
		$("#fileUploadingDivId").show();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").show();
		$("#assignDesignationDivId").show();
		$("#endorsementDivId").hide();
		$("#buttonNameId").html("Forward")
		$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');
		initializeSingleUploadDocument("uploadEndorsementDocId");
		$("#fileUploadDiv").show();
		getLoginUserAccessSubDeptDesignationDetail(departmentSelectArr);
	}else if(statusId == 7){
		$("#fileUploadDiv").show();
		$("#commentsDivId").show();
		$("#fileUploadingDivId").hide();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").hide();
		$("#assignDesignationDivId").hide();
		$("#endorsementDivId").hide();
		$("#buttonNameId").html("Save Details")
		$("#uploadFile").html('<input type="file" attr_name="" name="" attr_image_tyep=""  id="uploadEndorsementDocId" class="m_top10"/>');
		initializeSingleUploadDocument("uploadEndorsementDocId");
	}else if(statusId == 3 || statusId == 4 || statusId == 5){
		$("#fileUploadDiv").hide();
		$("#commentsDivId").show();
		$("#fileUploadingDivId").hide();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").hide();
		$("#assignDesignationDivId").hide();
		$("#endorsementDivId").hide();
		$("#buttonNameId").html("Save Details")
	}
	else if(statusId == 0){
		$("#fileUploadDiv").hide();
		$("#remarksDivId").hide();
		$("#fileUploadingDivId").hide();
		$("#leadDivId").hide();
		$("#grantDivId").hide();
		$("#assignOfficerDivId").hide();
		$("#assignDesignationDivId").hide();
		$("#endorsementDivId").hide();
		return;
	}
});	
$(document).on("click",".docsViewCls",function(){
	$("#docsModalDivId").modal("show");
	var docsList = [];
	var str="";
	
	if($(this).attr("attr_docs") == "referral"){
		$("#viewDocumentHeading").html("Referral Documents")
		 docsList = candidateReferralDoc;
	}else if($(this).attr("attr_docs") == "fileList"){
		$("#viewDocumentHeading").html("Work Documents")
		 docsList = projectDocuments;
	}else if($(this).attr("attr_docs") == "covering"){
		$("#viewDocumentHeading").html("Covering Letters")
		 docsList = coveringLetterDoc;
	}else if($(this).attr("attr_docs") == "detailed"){
		$("#viewDocumentHeading").html("Detailed Reports")
		 docsList = detailedReportDoc;
	}else if($(this).attr("attr_docs") == "mainWorkCovering"){
		$("#viewDocumentHeading").html("Detailed Reports")
		 docsList = mainWorkCoveringDocuments;
	}
	
	if(docsList != null && docsList.length >0){
			for(var j in docsList){
				var scanCopySpl = docsList[j].value.split("."); 
				var scanCopyExt = $.trim(scanCopySpl[scanCopySpl.length-1].toLowerCase()); 
					str+='<div class="col-sm-6">';
						
						str+='<div class="viewImageCss">';
						if(scanCopyExt =="pdf"){
							str+='<a class="fancyboxView" href="#inline'+j+'">';
							str+='<div class="mouse-over">Expand</div>';
								str+='<object data="'+docsList[j].value+'" type="application/pdf" width="100%"height="300px;"></object>';
								
							str+='</a>';
							str+='<div id="inline'+j+'" style="width:100%;display: none;">';
								str+='<object data="'+docsList[j].value+'" type="application/pdf"   style="cursor:pointer;height:1000px;width:1000px"></object>';
								
							str+='</div>';
							
						}else if( scanCopyExt =="jpeg" || scanCopyExt =="jpg"  || scanCopyExt =="gif"  || scanCopyExt =="bmp"  || scanCopyExt =="png"){
							str+='<a class="fancyboxView" href="#inline'+j+'">';
								str+='<img src="'+docsList[j].value+'"  width="100%" height="300px;"></img>';
								
							str+='</a>';
							str+='<div id="inline'+j+'" style="width:100%;display: none;">';
								str+='<img src="'+docsList[j].value+'"    style="cursor:pointer;height:1000px;width:1000px"></object>';
								
							str+='</div>';
						}else{
							str+='<b>Click <a href="javascript:{};" onclick="openDoc(\''+docsList[j].value+'\')">Here</a> To View Document</b>';
							
						}
			
				str+='</div>';
			str+='</div>';
	
		}
	}

		$("#docsViewModalId").html(str);
		$(".fancyboxView").fancybox();
});

function endorsingSubWorksAndAssigningToOfficer(){
	
	 $('#errorEndsmentStr').html('');
	 $('#leadIdErr').html('');
	 $('#grantIdErr').html('');
	 $('#assignToIdErr').html('');
	 $('#officerIdErr').html('');
	 $('#remarkIdErr').html('');
	 $('#fileUploadIdErr').html('');
	
	 var errorEndsmentStr = '';
	 var errorLeadStr = '';
	 var errorGrantStr = '';
	 var errorAssignToStr = '';
	 var errorOfficerStr = '';
	 var errRemarksStr = '';
	 //var errFileUploadStr = '';
	 
	 var endorsementId =$("#endorsmentNo").val();
	 var leadIdValue = $("#leadId").val();
	 var grantIdValue = $("#grantId").val();
	 var assignToIdValue = $("#assignToId").val();
	 var officerIdValue = $("#officerId").val();
	 var remarksId =$("#remarksId").val();
	 //var fileUploadId = $("#uploadFile").val();
	/*
	 $('#endorsementNoErr').html('');
	 if(endorsementId == 0 || endorsementId == '' || endorsementId == null || endorsementId.trim().length == 0){
		 errorEndsmentStr += "<p style='color:red'>Endosment no is required</p>"; 
	 }	 
	 if(leadIdValue == null || leadIdValue ==0){
		errorLeadStr += "<p style='color:red'>Please select brief lead</p>"; 
	 }
	 if(grantIdValue == null || grantIdValue ==0){
		errorGrantStr += "<p style='color:red'>Please select grant</p>"; 
	 }
	  if(assignToIdValue == null || assignToIdValue == 0){
		errorAssignToStr += "<p style='color:red'>Please select assign to</p>"; 
	 }
	 if(officerIdValue == null || officerIdValue == 0){
		errorOfficerStr += "<p style='color:red'>Please select officer name</p>"; 
	 } 
	 if(remarksId == 0 || remarksId == '' || remarksId == null || remarksId.trim().length == 0){
		 errRemarksStr += "<p style='color:red'>Comment is required</p>"; 
	 }*/
	 /*if(fileUploadId == 0 || fileUploadId == '' || fileUploadId == null || fileUploadId.trim().length == 0){
		// errFileUploadStr += "<p style='color:red'>Please file is required</p>"; 
	 }
	*/
	/*
	if(errorLeadStr.length >0)
	{
		$('#leadIdErr').html(errorLeadStr);
		return ;
	}else{
		$('#leadIdErr').html('');
	}
	if(errorGrantStr.length >0)
	{
		$('#grantIdErr').html(errorGrantStr);
		return ;
	}else{
		$('#grantIdErr').html('');
	}
	
	if(errorAssignToStr.length >0)
	{
		$('#assignToIdErr').html(errorAssignToStr);
		return ;
	}else{
		$('#assignToIdErr').html('');
	}
	if(errorOfficerStr.length >0)
	{
		$('#officerIdErr').html(errorOfficerStr);
		return ;
	}else{
		$('#officerIdErr').html('');
	}
	
	if(errRemarksStr.length >0)
	{
		$('#remarkIdErr').html(errRemarksStr);
		return ;
	}else{
		$('#remarkIdErr').html('');
	}
	*/
	/*if(errFileUploadStr.length >0)
	{
		$('#fileUploadIdErr').html(errFileUploadStr);
		return ;
	}else{
		$('#fileUploadIdErr').html('');
	}
	*/
	   $('#endorsWorksId').hide();
	   var endorsementNO="";
	   var formData = new FormData();
	   $('#endorsingSubWorksId input').each(
		  function(){			  
			var input = $(this);
			var text =input.attr('type');
			var id = input.attr('id');
			//debugger;
			if (typeof id !== typeof undefined && id !== false) {
				if(text=='text' || text=='hidden'){
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
					
					if(name=="petitionId")
						petitionId = $('#'+id+'').val();
					else if(name=="endorsementNO")
						endorsementNO = $('#'+id+'').val();
						
				}else if(text=='radio'){
					if($('#'+id+'').is(':checked')){
						var name = $('#'+id+'').attr('name');
						formData.append(name, $('#'+id+'').val());
					}
				}else if(text=='file'){
					if(this.files !=null && this.files.length>0){
						for(var i = 0; i < this.files.length; i++){
								formData.append("filesList["+i+"]", this.files[i]);
						}
					}
				}
			}			
		}
	);
	
	$('#endorsingSubWorksId textarea').each(
		  function(){			  
			var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
				var name = $('#'+id+'').attr('name');
				formData.append(name, $('#'+id+'').val());
			}
		}
	);
	
	$('#endorsingSubWorksId select').each(
		  function(){			  
				var input = $(this);
				var id = input.attr('id');
				if (typeof id !== typeof undefined && id !== false) {
					var name = $('#'+id+'').attr('name');
					formData.append(name, $('#'+id+'').val());
			}
		}
	);
	if(selectdWorksArr !=null && selectdWorksArr.length>0){
							for(var i = 0; i < selectdWorksArr.length; i++){
								if($("#nextStatusId").val()==6){
									formData.append("statusType", "COVERING LETTER");
								}else if($("#nextStatusId").val()==7){
									formData.append("statusType", "ACTION COPY");
								}else if($("#nextStatusId").val()==3){
									formData.append("statusType", "DETAILED REPORT");
								}
								formData.append("workIds["+i+"]", selectdWorksArr[i]);
						}
	}
	//formData.append("petitionId", petitionId);
$.ajax({
			url: $("#endorsingSubWorksId").attr("action"),
			data: formData,
			type: "POST",               
			processData: false,
			contentType: false,
			success: function(result) {
				$("#savingDetailsSpinner").html('');
				
					if(result!=null){
					  if(result.exceptionMsg == "SUCCESS"){
						  alert("Work(s) details updated successfully");
						  $("#endorseMentModalDivId").modal("hide");
						  $("#representeeDetailsModelDivId").modal("hide");
						  getPetitionDetails(petitionId,endorsementNO);
						 // $("#statusMsgAppntReqt").html("<center><h3 style='color: green;margin-top:-25px;'>Application Saved Successfully</h3></center>").fadeOut(4000);
					  }else{
						  $('#endorsWorksId').show();
					  }
					}else{
					 $('#endorsWorksId').show();
					}
			},
			error: function(request,error) { 
				$("#savingDetailsSpinner").html('');
				//console.log(request);
				//console.log(error);
				alert("Error occured while updating details.Pelase check once any required data missing to fill.Then try again.");	
				$('#saveButtonId').show();				
			}
     });
 }	
 
 $(document).on("click",".workStatusSelectedAllCls",function(){
	var endorsNo = $(this).attr("attr_enrorsNo");
	if($(this).is(":checked")){
		$(".checkbox"+endorsNo).prop("checked",true);
	}else{
		$(".checkbox"+endorsNo).prop("checked",false);
	}
 });