var url = window.location.href;
	var wurl = url.substr(0,(url.indexOf(".com")+4));
	if(wurl.length == 3)
		wurl = url.substr(0,(url.indexOf(".in")+3));

getAlertData();
getAlertStatusCommentsTrackingDetails();
getAlertAssignedCandidates(alertId);
getAlertAssignedCandidate(alertId);
$(document).on("click",".assignModel",function(){
	clearAssignFields();
	$("#ModalShow").modal('show');
	
});
function clearAssignFields()
{
commontdpCadreIds = [];
$("#involvedCandidatesDiv").hide();
 $(".membersBlock").html('');
 $("#apptmemberDetailsDiv").html('');
	$("#advanceSearchTypeId").val(0);
	  var select = new Dropkick("#advanceSearchTypeId");
		select.refresh();
	showHideBySearchType();//Clear Fields	
}
function saveAlertAssignedUser ()
	{
	
		if(commontdpCadreIds.length == 0)
		{
			$("#assignEroorDiv").html("at least one Candidate Required").css("color","red");
			return;
		}
		var jsObj =
		     {
				 tdpCadreIds : commontdpCadreIds,
				 alertId : alertId,
				task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'saveAlertAssignedUserAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
				   if(result.resultCode == 0)
				    $("#assignEroorDiv").html("assigned Successfully").css("color","green");
				 setTimeout(function(){ $("#assignEroorDiv").html("");
					 }, 1000);
					$("#involvedCandidatesDiv").hide();
					$(".membersBlock").html('');
					$("#apptmemberDetailsDiv").html('');
					clearCommonFields();
					getAlertAssignedCandidates(alertId);
					getAlertAssignedCandidate(alertId);
			 })
	}
function getAlertData()
{
    var jsObj =
		     {
			alertId  :alertId,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getAlertsDataAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			      buildAlertData(result);
				});
}


function buildAlertData(result)
{
	
	for(var i in result)
	{
		var severityTdId=result[i].categoryId;
		if(result[i].category !=null)
			$("#headingSpanId").html(''+result[i].category+'');
		
			$("#typeId").html(''+result[i].alertType+'');
			$("#severityId").html(''+result[i].severity+'');
			if(severityTdId ==1){
				$("#severityTdId").show();
			}else{
				$("#severityTdId").hide();
			}
			$(".severityIdColorCls").addClass(''+result[i].severity+'');
			$("#createdDate").html(''+result[i].date+'');
			$("#levelId").html(''+result[i].regionScope+'');
			var location ='';
			/* if(result[i].regionScope == "STATE")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p>';
			}
			else if(result[i].regionScope == "DISTRICT")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p>';
			}
			
			else if(result[i].regionScope == "CONSTITUENCY")
			{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p>';
			}
			else if(result[i].regionScope == "MANDAL" || result[i].regionScope == "MUNICIPAL-CORP-GMC" )
			{
				
					if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p><p> T : '+result[i].locationVO.localEleBodyName+' </p>';	
				}
				else{
					location +='<p>S:'+result[i].locationVO.state+' </p><p>D : '+result[i].locationVO.districtName+' </p><p>C :'+result[i].locationVO.constituencyName+'</p><p> M : '+result[i].locationVO.tehsilName+'</p>';
				}
			}
			
			else if(result[i].regionScope == "VILLAGE" || result[i].regionScope == "WARD" )
			{
			
				if(result[i].locationVO.localEleBodyName != null && result[i].locationVO.localEleBodyName.length > 0)
				{
				location +='<p>S:'+result[i].locationVO.state+'</p><p> D : '+result[i].locationVO.districtName+'</p><p> C :'+result[i].locationVO.constituencyName+'</p><p> T : '+result[i].locationVO.localEleBodyName+' </p><p>W : '+result[i].locationVO.wardName+'</p>';	
				}
				else{
					location +='<p>S:'+result[i].locationVO.state+' </p><p>D : '+result[i].locationVO.districtName+' </p><p>C :'+result[i].locationVO.constituencyName+'</p><p> M : '+result[i].locationVO.tehsilName+'</p><p> P : '+result[i].locationVO.villageName+'</p>';
				}
				
			}	 */
			
			if(result[i].locationVO.stateId !=null){
				location +='<p>S:'+result[i].locationVO.state+' </p>';
			}
			if(result[i].locationVO.districtId !=null){
				location +='<p>D:'+result[i].locationVO.districtName+' </p>';
			}
			if(result[i].locationVO.constituencyId !=null){
				location +='<p>C:'+result[i].locationVO.constituencyName+' </p>';
			}
			
			if(result[i].locationVO.localEleBodyName !=null && result[i].locationVO.localEleBodyName.length>0){
				location +='<p>M:'+result[i].locationVO.localEleBodyName+' </p>';
			}
			if(result[i].locationVO.tehsilName !=null && result[i].locationVO.tehsilName.length>0){
				location +='<p>T:'+result[i].locationVO.tehsilName+' </p>';
			}
			if(result[i].locationVO.wardName !=null && result[i].locationVO.wardName.length>0){
				location +='<p>W:'+result[i].locationVO.wardName+' </p>';
			}
			if(result[i].locationVO.villageName !=null && result[i].locationVO.villageName.length>0){
				location +='<p>P:'+result[i].locationVO.villageName+' </p>';
			}
			
			
			
			$("#LocationId").html(''+location+'');
			$("#statusId").val(''+result[i].statusId+'');
			  $("#statusId").dropkick('refresh')
			$("#descriptionId").html(''+result[i].desc+'');
			$("#titleId").html(''+result[i].title+'');
			$("#alertStatus").html(''+result[i].status+'');
			
			if(result[i].imageUrl !=null && result[i].imageUrl.length>0){
				$(".imageUrlUlCls").html("<li class='articleDetailsCls' attr_articleId="+result[i].alertCategoryTypeId+" style='cursor:pointer'><img src='http://mytdp.com/NewsReaderImages/"+result[i].imageUrl+"' style='width: 150px; height: 150px;'></img></li>");
				$("#imageUrlTrId").show();
			}else{
				$(".imageUrlUlCls").html("");
				$("#imageUrlTrId").hide();
			}
			
			buildAlertCandidateData(result[i].subList,result[i].categoryId);
		
	}	
}
function getAlertStatusCommentsTrackingDetails()
	{
		
		$("#alertCommentsDiv").html('<img src="images/search.gif" />');
		var jsObj={
	    			alertId:alertId,
					task:""
	    		}
		$.ajax({
		  type : 'GET',
		  url : 'getAlertStatusCommentsTrackingDetails.action',
		  dataType : 'json',
		  data : {task:JSON.stringify(jsObj)}
		}).done(function(result){ 
			alertComments(result);
			//buildAlertCommentsForTracking(result);
		});
		
	}
	function buildAlertCommentsForTracking(result){
		
		var str='';
		
		if(result == null || result.length == 0)
		{
			$("#alertCommentsDiv").html('No Data Available');
			return;
		}
			
			str+='<ul class="alertStatusTracking">';
				for(var i in result){
					str+='<li>';
						str+='<div class="arrow_box_left">';
						if(result[i].id == 1)
						{
						str+='<p> <span class="text-success"></span> Alert <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';	
						}
						else
						{
							str+='<p>Alert status changed to <span class="text-success"><b>'+result[i].status+'</b></span> on '+result[i].date+' By <b>'+result[i].uname+'</b> </p>';
						}
						if(result[i].subList != null && result[i].subList.length > 0){
							
								str+='<u style="font-size:16px;margin-bottom:10px;"><b>Comments</b></u>';
								str+='<ul class="commentsUlCls">';
							for(var j in result[i].subList){
								if(result[i].subList[j].subList1 != null && result[i].subList[j].subList1.length > 0)
								{
									str+='<u>Alert Owners</u> : ';
									for(var k in result[i].subList[j].subList1)
									{
										if(k == result[i].subList[j].subList1.length -1)
										str+='<b>'+result[i].subList[j].subList1[k].name+'</b> ';
										else									
										str+='<b>'+result[i].subList[j].subList1[k].name+',</b> ';
									}
									
									str+='<li>'+result[i].subList[j].name+' on '+result[i].subList[j].dateStr+' By <b>'+result[i].subList[j].status+'</b></li>';
								}
								else
								{
									str+='<li>'+result[i].subList[j].name+' on '+result[i].subList[j].dateStr+' By <b>'+result[i].subList[j].status+'</b></li>';
								}
									
							}
								str+='</ul>';
						}
					
					str+='</div>';
					str+='</li>';	
				}
			str+='</ul>';
		
		$("#alertCommentsDiv").html(str);
		if(result.length >= 3)
		{
			$("#alertCommentsDiv").mCustomScrollbar({setHeight:'320px'});
		}
		
		
		//$('.modal-content').animate({scrollTop: $("#alertCommentsDiv").offset().top}, 'slow');
	}
$(document).on("click",".locationLevelCls",function(){
	var levelId = $(this).attr("attr-levelId");
	var statusId=$(this).attr("attr-statusId");
	var fromDate = $(this).attr("attr-fromDate");
	var toDate=$(this).attr("attr-toDate");
	$("#errorId").html("");
	getLocationLevelAlertData(levelId,statusId,fromDate,toDate);
});
/*
function getAlertCandidatesData(alertId)
{
	alert('aa')
	$("#alertCandidateDataId").html('<img src="images/search.gif" />');
    GlobalAlertData = [];
		var jsObj =
		     {
			alertId  : alertId,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getAlertCandidatesDataAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			      buildAlertCandidateData(result);
				});
}*/
function buildAlertCandidateData(result,categoryId)
{
	
	if(result == null || result.length == 0)
	{
		$("#alertCandidateDataId").html('No Involved Members..');
		return;
	}
	var str='';
   if(status == 'false'){
	$("#alertcandteDivId").show();
	$("#invledCandtDivId").hide();
	if(categoryId !=null && categoryId>1){
		for(var i in result)
		{
			str+='<div class="row">';
			str+='<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<div class="media">';
					str+='<div class="media-left">';						
					if(result[i].image !=null && result[i].image.length>0){
						str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}else{
						str+=' <img src="dist/Appointment/img/thumb.jpg"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}					   					  
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
						//str+='  <p>'+result[i].committeeName+' Committee '+result[i].committeePosition+' </p>';
					str+='  <p><b class="text-capital text-danger">Designation : </b>'+result[i].committeePosition+' </p>';
					 // str+='  <p>'+result[i].mobileNo+'</p>';
					 // str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral">N</span>';
					  }
					  
					  if(result[i].organization !=null){
						  str+='<p><b class="text-capital text-danger">Organization</b> : '+result[i].organization+'</p>';
					  }
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><b class="text-capital text-danger">Membership No </b> : '+result[i].membershipNo+'</p>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		str+='</div>';
		str+='</div>';
		}
	}else{
		for(var i in result)
		{
			str+='<div class="col-md-4 col-xs-12 col-sm-4">';
				str+='<div class="media">';
					str+='<div class="media-left">';
					   str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
						str+='  <p>'+result[i].committeeName+' Committee '+result[i].committeePosition+' </p>';
					  str+='  <p>'+result[i].mobileNo+'</p>';
					  str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><a>'+result[i].membershipNo+'</a></p>';
					  }
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral">N</span>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </div>';
		}
	}
	$("#cadreInvolvedCandidatesCnt").html('-' +result.length);	
	$("#cadreAlertCandidateDataId").html(str);
   }else{
	   if(categoryId !=null && categoryId>1){
		for(var i in result)
		{
			str+='<tr>'
			str+='<li>';
				str+='<div class="media">';
					str+='<div class="media-left">';						
					if(result[i].image !=null && result[i].image.length>0){
						str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}else{
						str+=' <img src="dist/Appointment/img/thumb.jpg"  onerror="setDefaultImage(this);" alt="dist/Appointment/img/thumb.jpg" style="width:50px;"/>';
					}					   					  
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
						//str+='  <p>'+result[i].committeeName+' Committee '+result[i].committeePosition+' </p>';
					str+='  <p><b class="text-capital text-danger">Designation : </b>'+result[i].committeePosition+' </p>';
					 // str+='  <p>'+result[i].mobileNo+'</p>';
					 // str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral">N</span>';
					  }
					  
					  if(result[i].organization !=null){
						  str+='<p><b class="text-capital text-danger">Organization</b> : '+result[i].organization+'</p>';
					  }
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><b class="text-capital text-danger">Membership No </b> : '+result[i].membershipNo+'</p>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </li>';
	str+='</tr>';
		}
	}else{
		for(var i in result)
		{
			str+='<li>';
				str+='<div class="media">';
					str+='<div class="media-left">';
					   str+=' <img src="images/cadre_images/'+result[i].image+'"  onerror="setDefaultImage(this);" alt="Profile Image" style="width:50px;"/>';
				   str+=' </div>';
				   str+=' <div class="media-body">';
					   str+=' <p class="text-capital"><b>'+result[i].name+'</b></p>';
					   if(result[i].committeePosition != null && result[i].committeePosition.length > 0)
						str+='  <p>'+result[i].committeeName+' Committee '+result[i].committeePosition+' </p>';
					  str+='  <p>'+result[i].mobileNo+'</p>';
					  str+='  <p>'+result[i].locationVO.constituencyName+' </p>';
					  
					  if(result[i].membershipNo !=null && result[i].membershipNo.length>0){
						  str+='<p><a>'+result[i].membershipNo+'</a></p>';
					  }
					  if(result[i].impactId == 1)
					  {
						 str+=' <span class="label label-success">+ Ve</span>'; 
					  }else if(result[i].impactId == 2){
						  str+=' <span class="label label-danger">- Ve</span>';
					  }else{
						  str+=' <span class="label label-neutral">N</span>';
					  }
					  
				  str+='  </div>';
				str+='</div>';
		   str+=' </li>';
		   

		}
	}
	$("#involvedCandidatesCnt").html('-' +result.length);	
	$("#alertCandidateDataId").html(str);
   }
	if(result.length > 3)
	{
		$("#alertCandidateDataId").mCustomScrollbar({setHeight:'250px'});
	}
	
}
function setDefaultImage(img){
	  img.src = "dist/Appointment/img/thumb.jpg";
   }

function updateAlertStatus()
{
	var comments = $("#commentsId").val();
	var statusId=$("#statusId").val();
	 $('#errorId').html('');
	 var tdpCadreIdarr =[];
	 var tdpCadreId = $("#assignedCadreId").val();
	 
	if(statusId==0)
	{
	 $('#errorId').html(' Please Select Status ').css("color","red"); 
        return;	   
	}	
	if(statusId !=6 && statusId !=7){
		if(tdpCadreId == null || tdpCadreId == 0){	
			 $('#errorId').html(' Please Assign Cadre ').css("color","red"); 
				return;	       
		}
	}
	if(comments.length==0||comments=='')
	{
		  $('#errorId').html(' Please Enter Comment ').css("color","red");
		  return; 
	}
	$("#updateAlertajaxImg").html('<img src="images/search.gif"/>');
	if(tdpCadreId == null)
		tdpCadreIdarr = [];
	else
		 tdpCadreIdarr = tdpCadreId;
	var jsObj =
		     {
		alertId : alertId,
		alertStatusId :statusId,
		comments:comments,
		tdpCadreId:tdpCadreIdarr,
			task : ""
		      }
			$.ajax({
					  type:'POST',
					  url: 'updateAlertStatusAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
				  $("#commentsId").val('');
				  $("#updateAlertajaxImg").html('');
					if(result="success")
					{
						$("#errorId").html(" Alert Updated Successfully ").css("color","green");
						 setTimeout(function(){ $("#errorId").html("");
					 }, 1000);
						getAlertStatusCommentsTrackingDetails();
						getAlertAssignedCandidate(alertId);
					}
					
				});
			
}
$(document).on("click",".updateAlertStatusCls",function(){
	
	updateAlertStatus();
});


function getAlertAssignedCandidates(alertId)
{
	$("#alertAssignedCandidateDataId").html('<img src="images/search.gif" />');
    GlobalAlertData = [];
		var jsObj =
		     {
			alertId  : alertId,
			task : ""
		      }
			$.ajax({
					  type:'GET',
					  url: 'getAlertAssignedCandidatesAction.action',
					  data: {task :JSON.stringify(jsObj)}
			   }).done(function(result){
			      buildAlertAssignedCandidateData(result);
				});
}
function getConfirmation(tdpCadreId){
               var retVal = confirm("Are you sure want to delete?");
			
			    if(retVal == true){
				   deleteAlertAssignedCandidates(tdpCadreId);
                  return true;
               }
               else{
                  return false;
               }
            }


// $("#assignedCadreId").multiselect({ noneSelectedText:"Select Assign Cadre"}).multiselectfilter({});
function getAlertAssignedCandidate(alertId,type)
{
	
	//$("#alertCommentsDiv").html('<img src="images/search.gif" />');
	   $("#assignedCadreId option").remove();
    // $("#assignedCadreId").multiselect('refresh'); 
	var jsObj={
    			alertId:alertId,
				task:""
    		}
	$.ajax({
	  type : 'GET',
	  url : 'getAlertAssignedCandidateAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){ 
	 
	  var str='';
	   str+='<option value="0">Select Assign Cadre</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				if(result[i].id > 0)
				str+='<option value="'+result[i].id+'">'+result[i].uname+'</option>';
			}
		}
		$("#assignedCadreId").html(str);
		//$("#assignedCadreId").multiselect('refresh'); 
		
		$("#assignedCadreId").trigger("chosen:updated");
		
		if(type !=null && type=="updateStatus"){
			if(result == null || result.length <= 0){
				updateCandidateStatusOfAlert(alertId);								
			}
		}
		
	});	
}
function updateCandidateStatusOfAlert(alertId){
	var jsObj={
    			alertId:alertId,
				task:""
    		}
	$.ajax({
	  type : 'GET',
	  url : 'updateCandidateStatusOfAlertAction.action',
	  dataType : 'json',
	  data : {task:JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result=="success"){
			$("#statusId").val(1);
			$("#statusId").dropkick('refresh')
		}
	});
}

$("#assignedCadreId").chosen();

$(document).on("click",".articleDetailsCls",function(){
	var articleId= $(this).attr("attr_articleId");
	getTotalArticledetails(articleId);
});

function getTotalArticledetails(articleId){
	$("#myModalShowNew").modal('show');
	$("#myModalShowNew").html('<div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div>');
	$.ajax({
		  type : 'GET',      
		  url: wurl+"/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
		  //url: "http://localhost:8080/CommunityNewsPortal/webservice/getArticlesFullDetails/"+articleId+""
	}).then(function(results){
			var obj = ["","State","District","Constituency","Parliament","Mandal","Panchayat","Village","Muncipality/Corporation/GHMC/GVMC","Ward"];
				var result = results[0];
				var str = '';
					str+='<div class="modal-dialog modal-lg" role="document">';
					str+='<div class="modal-content">';
					str+='<div class="modal-header">';
					str+='<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
					str+='<h4 class="modal-title" id="myModalLabel">';
					str+='<p class="m_bottom0" style="height:40px;" id="mdlArtclTtl">'+result.articleTitle+'</p>';
					str+='<p class="m_bottom0 text-italic font-16" id="mdlArtclDesc"><i>Edition Source :'+result.editionSource+' ['+result.articleInsertedTime+' ]</i></p>';
					str+='</h4>';
					str+='</div>';
					str+='<div class="modal-body">';
					str+='<div class="row">';
					str+='<div class="col-md-12">';
					str+='<img class="mainImage"  src="http://mytdp.com/NewsReaderImages/'+result.imageURL+'" style="display:block;margin:auto;width:100%;" alt="Img Title"/>';
					str+='</div>';
					str+='<div class="col-md-12 m_top10">';
					str+='<h4 class="panel-title text-success">Description</h4>';
					str+='<p class="m_0 f_14">'+result.description+'</p>';
					str+='</div>';
					str+='<div class="col-md-12">';
					if( result.subList != null && result.subList.length > 0){
						for(var i in result.subList){
							/* Candidate*/
							str+='<div class="row ">';
							str+='<div class="col-md-6">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">FROM WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* From Table*/
								if(result.subList[i].fromList != null && result.subList[i].fromList.length > 0){
									for( var j in result.subList[i].fromList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].fromList[j].organizationName != null && $.trim(result.subList[i].fromList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].fromList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].fromList[j].organizationName+'</td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].fromList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].fromList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].fromList[j].candidateName != null && $.trim(result.subList[i].fromList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].fromList[j].candidateName;
										}
										if( result.subList[i].fromList[j].designation != null && $.trim(result.subList[i].fromList[j].designation).length > 0 ){
											candidataExist = true; 
											str+=' ('+result.subList[i].fromList[j].designation + ")";
										}
										if(!candidataExist){
											str+=' - ';
										}
										str+='</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										if(result.subList[i].fromList[j].impactLevel != null && $.trim(result.subList[i].fromList[j].impactLevel).length > 0){
											str+='<p class="m_0">Impact Level : '+result.subList[i].fromList[j].impactLevel+'</p>';	
										}else{ 
											str+='<p class="m_0">Impact Level : - </p>';	
										}
										if(result.subList[i].fromList[j].categories != null && $.trim(result.subList[i].fromList[j].categories).length > 0){
											str+='<p class="m_0">Category : '+result.subList[i].fromList[j].categories+'</p>';	
										}else{ 
											str+='<p class="m_0">Category : - </p>';	
										}
										if(result.subList[i].fromList[j].newsActivity != null && $.trim(result.subList[i].fromList[j].newsActivity).length > 0){
											str+='<p class="m_0">News Activity : '+result.subList[i].fromList[j].newsActivity+' </p>';
										}else{ 
											str+='<p class="m_0">News Activity : - </p>';	
										}
										if(result.subList[i].fromList[j].newsType != null && $.trim(result.subList[i].fromList[j].newsType).length > 0){
											str+='<p class="m_0">News type : '+result.subList[i].fromList[j].newsType+' </p>';
										}else{ 
											str+='<p class="m_0">News type : - </p>';	
										}
										if( result.subList[i].fromList[j].newsType != null && result.subList[i].fromList[j].newsType == "Problems"){
											if(result.subList[i].fromList[j].newsRelated != null && $.trim(result.subList[i].fromList[j].newsRelated).length > 0){
												str+='<p class="m_0">News Related : '+result.subList[i].fromList[j].newsRelated+' </p>';
											}else{ 
												str+='<p class="m_0">News Related : - </p>';	
											}
											if(result.subList[i].fromList[j].priority != null && $.trim(result.subList[i].fromList[j].priority).length > 0){
												str+='<p class="m_0">Priority : '+result.subList[i].fromList[j].priority+' </p>';
											}else{ 
												str+='<p class="m_0">Priority : - </p>';	
											}
											if(result.subList[i].fromList[j].solution != null && $.trim(result.subList[i].fromList[j].solution).length > 0){
												str+='<p class="m_0">Solution : '+result.subList[i].fromList[j].solution+' </p>';
											}else{ 
												str+='<p class="m_0">Solution : - </p>';	
											}
										}
										str+='</td>';
										str+='</tr>';
										str+='</table>';
									}
								}
							str+='</div>';//panel-body
							str+='</div>';//panel
							str+='</div>';//colmd6
							str+='<div class="col-md-6">';
							str+='<div class="panel panel-default panelArticleGroup">';
							str+='<div class="panel-heading">';
							str+='<h4 class="panel-title">TO WHOM</h4>';
							str+='</div>';
							str+='<div class="panel-body">';
								/* TO Table*/
								if(result.subList[i].toList != null && result.subList[i].toList.length > 0){
									for( var j in result.subList[i].toList){
										str+='<table class="table table-bordered m_top10">';
										str+='<tr>';
										if( result.subList[i].toList[j].organizationName != null && $.trim(result.subList[i].toList[j].organizationName).length > 0 ){
											str+='<td><img class="img-circle" src="newCoreDashBoard/img/'+result.subList[i].toList[j].organizationName+'.png" style="width:30px;height:30px;" onerror="setDefaultImage(this);"/> '+result.subList[i].toList[j].organizationName+'</td>';
										}else{
											str+='<td> - </td>';
										}
										str+='<td><img class="img-circle" src="images/'+result.subList[i].toList[j].benefit+'.png" style="width:20px;height:20px;" alt=""/> '+result.subList[i].toList[j].benefit+'</td>';
										str+='</tr>';
										str+='<tr>';
										str+='<td colspan="2">';
										var candidataExist = false;
										if( result.subList[i].toList[j].candidateName != null && $.trim(result.subList[i].toList[j].candidateName).length > 0 ){
											candidataExist = true; 
											str+=''+result.subList[i].toList[j].candidateName;
																			}
																			if( result.subList[i].toList[j].designation != null && $.trim(result.subList[i].toList[j].designation).length > 0 ){
																				candidataExist = true; 
																				str+=' ('+result.subList[i].toList[j].designation + ")";
																			}
																			if(!candidataExist){
																				str+=' - ';
																			}
																		   str+='</td>';
																	str+='</tr>';
																	str+='<tr>';
																		str+='<td colspan="2">';
																		    
																			if(result.subList[i].toList[j].impactLevel != null && $.trim(result.subList[i].toList[j].impactLevel).length > 0){
																			  str+='<p class="m_0">Impact Level : '+result.subList[i].toList[j].impactLevel+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Impact Level : - </p>';	
																			}
																		
																		    if(result.subList[i].toList[j].categories != null && $.trim(result.subList[i].toList[j].categories).length > 0){
																			  str+='<p class="m_0">Category : '+result.subList[i].toList[j].categories+'</p>';	
																			}else{ 
																			  str+='<p class="m_0">Category : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsActivity != null && $.trim(result.subList[i].toList[j].newsActivity).length > 0){
																			  str+='<p class="m_0">News Activity : '+result.subList[i].toList[j].newsActivity+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News Activity : - </p>';	
																			}
																			if(result.subList[i].toList[j].newsType != null && $.trim(result.subList[i].toList[j].newsType).length > 0){
																			  str+='<p class="m_0">News type : '+result.subList[i].toList[j].newsType+' </p>';
																			}else{ 
																			  str+='<p class="m_0">News type : - </p>';	
																			}
																			if( result.subList[i].toList[j].newsType != null && result.subList[i].toList[j].newsType == "Problems"){
																				
																				if(result.subList[i].toList[j].newsRelated != null && $.trim(result.subList[i].toList[j].newsRelated).length > 0){
																				  str+='<p class="m_0">News Related : '+result.subList[i].toList[j].newsRelated+' </p>';
																				}else{ 
																				  str+='<p class="m_0">News Related : - </p>';	
																				}
																				if(result.subList[i].toList[j].priority != null && $.trim(result.subList[i].toList[j].priority).length > 0){
																				  str+='<p class="m_0">Priority : '+result.subList[i].toList[j].priority+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Priority : - </p>';	
																				}
																				if(result.subList[i].toList[j].solution != null && $.trim(result.subList[i].toList[j].solution).length > 0){
																				  str+='<p class="m_0">Solution : '+result.subList[i].toList[j].solution+' </p>';
																				}else{ 
																				  str+='<p class="m_0">Solution : - </p>';	
																				}
																			}
																		str+='</td>';
																	str+='</tr>';
																str+='</table>';
															}
														}
														
													str+='</div>';//panelbody
												str+='</div>';//panel
											str+='</div>';//colmd6
											
										str+='</div>';//row
								  }
								}
								
								str+='</div>';//colmd12
							str+='</div>';//row
							
							/* Tracking*/
							/* str+='<div class="row">';
								str+='<div class="col-md-6 m_top10">';
									str+='<div class="panel panel-default panelArticleGroup">';
									if(result.trackLocationScope!=null && $.trim(result.trackLocationScope.length > 0)){
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">Article Tracking &nbsp;&nbsp;&nbsp<i class="glyphicon glyphicon-ok text-success"></i></h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-bordered">';
												str+='<tr>';
												   if(result.trackLocationScope!=null && $.trim(result.trackLocationScope.length > 0)){
													 str+='<td>Tracking Location Scope : '+result.trackLocationScope+'</td>';  
												   }else{
													  str+='<td>Tracking Location Scope : - </td>';   
												   }
												str+='</tr>';
												
												str+='<tr>';
												 if(result.trackLocationValue!=null && $.trim(result.trackLocationValue.length > 0)){
													 str+='<td>Tracking Location  : '+result.trackLocationValue+'</td>';  
												 }else{
													  str+='<td>Tracking Location : - </td>';   
												 }
												str+='</tr>';
												
												str+='<tr>';
												if(result.trackLabelName!=null && $.trim(result.trackLabelName.length > 0)){
													 str+='<td>Label Name  : '+result.trackLabelName+'</td>';  
												 }else{
													  str+='<td>Label Name : - </td>';   
												 }
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									}else{
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">Article Tracking </h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										str+='<h4 class="panel-title" style="text-align:center;">Tracking &nbsp;&nbsp;&nbsp<i class="glyphicon glyphicon-remove text-danger"></i></h4>';
										str+='</div>';
									}
										
									str+='</div>';
								str+='</div>'; */ 
						
							/* Characteristics */
						
								/* str+='<div class="col-md-6 m_top10">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">Article Characteristics</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed" style="border:1px solid #ddd;">';
												str+='<tr>';
												   if(result.important == 'Y'){
													str+='<td>Is Important : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												   }else{
													   str+='<td>Is Important :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												   }
												str+='</tr>';
												str+='<tr>';
												   if(result.actionable == 'Y'){
													   str+='<td>Actionable : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												    }else{
													   str+='<td>Actionable :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												    }
												str+='</tr>';
												str+='<tr>';
													if(result.newsBulliten == 'Y'){
													   str+='<td>News Bulletin : <i class="glyphicon glyphicon-ok text-success"></i></td>';   
												    }else{
													   str+='<td>News Bulletin :<i class="glyphicon glyphicon-remove text-danger"></i></td>';
												    }
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>'; */
							
							 /* NewsType */
							/* str+='<div class="row">';							   
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">NEWS TYPE</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed">';
												str+='<tr>';
													str+='<td>Published Article</td>';
													if(result.publishedArticle!=null){
													   str+='<td>'+result.publishedArticle+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
												str+='<tr>';
													str+='<td>Article Nature</td>';
													if(result.articleNature != null){
													  str+='<td>'+result.articleNature+'</td>';	
													}else{
														str+='<td> - </td>';	
													}
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									str+='</div>';
								str+='</div>'; */
								
								/* Article Scope Location */
								str+='<div class="col-md-12">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LOCATION DETAILS</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
											str+='<table class="table table-condensed">';
												str+='<tr>';
													str+='<td>Impact Scope : </td>';
													if(result.impactScopeId!=null){
														str+='<td>'+obj[result.impactScopeId]+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
												str+='<tr>';
													str+='<td>Location : </td>';
													if(result.scopeLocation!=null){
														str+='<td>'+result.scopeLocation+'</td>';
													}else{
														str+='<td> - </td>';
													}
												str+='</tr>';
											str+='</table>';
										str+='</div>';
									str+='</div>';
								str+='</div>';
							str+='</div>';
							
							str+='<div class="row">';
							     /*Lnking*/
								str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">LINKED ARTICLES</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										     if( result.linkedList != null && result.linkedList.length > 1){
											str+='<div class="row">';
												for( var i in result.linkedList){
													if(result.linkedList[i].articleId !=articleId ){
														str+='<div class="col-md-4" style="margin-top:5px;">';
															str+='<img  class="thumbnail img-responsive linkedArticlesClickId" src="http://mytdp.com/NewsReaderImages/'+result.linkedList[i].imageURL+'" style="display:block;margin:auto;height:90px;cursor:pointer"/>';
														str+='</div>';
													}
												}
											str+='</div>';
											}else{
												str+="<h5> No Linked Articles Available </h5>";
											}
											
										str+='</div>';
									str+='</div>';
								str+='</div>';  
								/*Grouping*/
								/* str+='<div class="col-md-6">';
									str+='<div class="panel panel-default panelArticleGroup">';
										str+='<div class="panel-heading">';
											str+='<h4 class="panel-title">GROUPED ARTICLES</h4>';
										str+='</div>';
										str+='<div class="panel-body">';
										    if( result.groupedArticlesList != null && result.groupedArticlesList.length > 1){
												if( result.groupedArticlesList.length > 6){
													str+='<div class="row" style="height:200px;overflow-y:scroll">';
												}else{
													str+='<div class="row">';
												}
												for( var i in result.groupedArticlesList){
													if(result.groupedArticlesList[i].articleId !=articleId ){
														str+='<div class="col-md-4 m_top10">';
															str+='<img class="img-responsive thumbnail groupedArticleId" attr_articleId='+result.groupedArticlesList[i].articleId+' src="http://mytdp.com/NewsReaderImages/'+result.groupedArticlesList[i].imageURL+'" style="display:block;margin:auto;height:90px;"/>';
														str+='</div>';
													}
												}
											str+='</div>';
											}else{
												str+="<h5> No Grouped Articles Available </h5>";
											}
										str+='</div>';
									str+='</div>';
								str+='</div>';  */
							str+='</div>';
							
						  str+='</div>';
						str+='</div>';
					 str+='</div>';
					 
					$("#myModalShowNew").html(str);
					
		        
		});    
}
$(document).on("click",".linkedArticlesClickId",function(){	 
		var temp=$(this).attr('src');
		$(this).attr('src',$(".mainImage").attr('src'));
		$(".mainImage").attr('src',temp);
	});
function alertComments(result)
{
	var statusId = 0;
	var length = result.length;
	length = length - 1;
	var str = '';
	if(status == 'false'){
		$("#cadreAlertCommentsDivId").show();
		$("#invledCandtDivId").hide();
		
		$("#cadreAlertCommentsDivIdNew").html(str);
	}else{
	str+='<div class="panel-group alertCommentsCollapse m_top10" id="accordion" role="tablist" aria-multiselectable="true">';
	for(var i in result)
	{
		statusId = result[i].statusId;
		str+='<div class="panel panel-default">';
			str+='<div class="panel-heading" role="tab" id="heading'+i+'">';
			if(length == i)  
			{
				str+='<a role="button" class="alertCommentColapse" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="true" aria-controls="collapse'+i+'">';
			}else{
				str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse'+i+'" aria-expanded="false" aria-controls="collapse'+i+'">';
			}
			if(length != i){
				str+='<h4 class="panel-title">'+result[i].status+'';
						str+='<i class="glyphicon glyphicon-ok"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';    
				str+='</h4>';
			}else{
				str+='<h4 class="panel-title">'+result[i].status+'';
					str+='<i class="glyphicon glyphicon-hourglass"></i><span class="pull-right" style="padding-right:20px;">'+result[i].sublist2[0].date+'</span>';
				str+='</h4>';
			} 
				str+='</a>';  
			str+='</div>';
			if(length == i)  
			{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading'+i+'">';
			}else{
				str+='<div id="collapse'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading'+i+'">';
			}
				str+='<div class="panel-body" style="padding:5px;">';
					str+='<div class="row">';
						for(var j in result[i].sublist2){
							str+='<div class="col-md-2 col-xs-12 col-sm-2">';
								var date = result[i].sublist2[j].date
								var dateArr = date.split("-");
								var year = dateArr[0];
								var month = dateArr[1];
								var day = dateArr[2];
								str+='<table class="table tableCalendar">';
									str+='<tr>';
										str+='<td colspan="2">';
											str+='<h3>'+day+'</h3>';
										str+='</td>';
									str+='</tr>';
									str+='<tr>';
										str+='<td>'+getMonth(month)+'</td>';        
										str+='<td>'+year+'</td>';
									str+='</tr>';
								str+='</table>';
							str+='</div>';
							str+='<div class="col-md-10 col-xs-12 col-sm-10" style="padding-left:0px;">';
								str+='<ul class="alertStatusTracking">';
									str+='<li>';
										str+='<div class="arrow_box_left">';
										for(var k in result[i].sublist2[j].sublist)
										{	
											str+='<div>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT SOURCE:'+result[i].sublist2[j].sublist[k][0].timeString+'</span><br>';
												for(var l in result[i].sublist2[j].sublist[k])
												{
													str+='<img src="dist/Appointment/img/thumb.jpg" style="width:10px;display:inline-block"/> '+result[i].sublist2[j].sublist[k][l].cadreName+'<br>';
												}
												str+='</p>';
												str+='<p><span style="color:#A286C0;font-size:13px;">COMMENT:</span><br>';
												str+='<p>'+result[i].sublist2[j].sublist[k][0].comment+'</p>';
												str+='<p><span class="pull-right" style="color:#A286C0;font-size:13px;">UPDATED BY: '+result[i].sublist2[j].sublist[k][0].userName+'</span></p>';
												str+='<hr style="margin-top:20px;"/>';
											str+='</div>';   
										}
										str+='</div>';    
									str+='</li>';
								str+='</ul>';
							str+='</div>';
						}           
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
	/* var statusArr = {"1":"Pending","2":"Notified","3":"Action In Progess","4":"Completed","5":"Unable to Resolve","6":"Action Not Required"};
	statusId = statusId + 1;
	for(var i = statusId ; i <= 6 ; i++){
		str+='<div class="panel panel-default" style="cursor:no-drop;pointer-events: none;">';
		str+='<div class="panel-heading" role="tab" id="headingThree" style="cursor:no-drop;pointer-events: none;background-color:rgba(162, 134, 192,0.4)">';
		str+='<a class="collapsed alertCommentColapse" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" style="cursor:no-drop;pointer-events: none;color:rgba(0,0,0,0.4)">';
		str+='<h4 class="panel-title">'+statusArr[i]+'</h4>';
		str+='</a>';     
		str+='</div>';
		str+='<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">';
		str+='</div>';
		str+='</div>';
	}   */      
	$("#alertCommentsDivIdNew").html(str)
}
}
function getMonth(month){
	if(month=="01"){
		return "Jan"
	}else if(month=="02"){
		return "Feb"
	}else if(month=="03"){
		return "Mar"
	}else if(month=="04"){
		return "Apr"
	}else if(month=="05"){
		return "May"
	}else if(month=="06"){
		return "Jun"
	}else if(month=="07"){
		return "Jul"
	}else if(month=="08"){
		return "Aug"
	}else if(month=="09"){
		return "Sep"
	}else if(month=="10"){
		return "Oct"
	}else if(month=="11"){
		return "Nov"
	}else if(month=="12"){  
		return "Dec"
	}  
}