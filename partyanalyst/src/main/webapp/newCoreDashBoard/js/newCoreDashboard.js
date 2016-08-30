
	/* function getUserBasicDetails(){
		
		var jsObj ={userId:globalUserId}
		$.ajax({
			type : 'POST',
			url : 'getUserBasicDetailsAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result != null){
				globalUserTypeId = result.userTypeId;
				globalUserAccessLevelId = result.userAccessLevelId;
				globalUserAccessLevelValues = result.userAccessLevelValuesList;
				
				//onLoadCalls();
			}
		});
	} */
    function initialiseDatePicker(){
		
		$("#dateRangeId").daterangepicker({
			singleDatePicker: true,
			minDate:'01/01/2014',
			maxDate:moment(),
			locale: {
			  format: 'DD/MM/YYYY'
			},
			opens:'left'
		})
	}
	
	$(document).on("click",".iconExpand",function(){
		$(".dateRangePickerCls").toggleClass("hide");
		
		$(this).find("i").toggleClass("glyphicon-fullscreen").toggleClass("glyphicon-resize-small");
		$(".committeesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".basicCommitteesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".userTypeCommitteesBlock").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock1").toggleClass("col-md-6").toggleClass("col-md-12");
		$(".committeesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".basicCommitteesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".userTypeCommitteesBlock").css("transition"," ease-in-out, width 0.7s ease-in-out");
		$(".showDatePicker").toggle();
		if( !$(this).find("i").hasClass( "glyphicon glyphicon-resize-small" )){
			$(".moreBlocks").hide();
			
		}
		setTimeout(function(){
			$(".committeesHiddenBlock,.moreBlocksIcon").toggle();
			//initialiseGraph();
			getUserTypeWiseCommitteesCompletedCounts1();
		},500);
	});
	/* Notes Functionality Complete*/
	$(document).on("click",".notesIcon",function(){
		$(this).closest(".panel-heading").find(".notesDropDown").toggle();
	});
	$(document).on("click",".btnCustomCreate",function(){
		var getNewNotes = $(".notesArea").val();
		var todayDate = moment().format("DD MMMM YYYY");
		var commentText = '<span class="notesText">'+getNewNotes+'</span> - <span class="text-muted"><i>'+todayDate+'</i></span> <i class="glyphicon glyphicon-trash pull-right hoverBlock deleteNotes"></i><i class="glyphicon glyphicon-edit pull-right hoverBlock editNotes"></i>'; 
		$(".notesUl").append("<li>"+commentText+"</li>");
		$(".notesArea").val('');	
	});
	$(document).on("click",".editNotes",function(){
		var notesHtml = $(this).closest("li").find(".notesText").html();
		$(".notesArea").val(notesHtml);
	});
	$(document).on("click",".deleteNotes",function(){
		$(this).closest("li").remove();
	});
	$(document).on("click",".moreBlocksIcon",function(){
		$(this).addClass("unExpandBlock");
		$(".moreBlocks").show();
		$(".moreBlocksDetailAndComp").show();
		$(".moreBlocks1").hide();
		customBuildGraph();

		getLevelWiseBasicCommitteesCountReport();
		var tdpCommitteeLevelIdsClickedArray = [];
		tdpCommitteeLevelIdsClickedArray.push(6);
		tdpCommitteeLevelIdsClickedArray.push(8);
		getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray);
		
	});
	
	$(document).on("click",".unExpandBlock",function(){
		$(this).removeClass("unExpandBlock");
		$(".moreBlocks").hide();
		$(".moreBlocks1").hide();
		$(".moreBlocksDetailAndComp").hide();
		$(".comparisionBlock").removeClass("active");
		$(".detailedBlock").addClass("active");
	});
	$(document).on("click",".detailedBlock",function(){
		
		$(".moreBlocks1").hide();
		$(".moreBlocks").show();
		getLevelWiseBasicCommitteesCountReport();
		var tdpCommitteeLevelIdsClickedArray = [];
		tdpCommitteeLevelIdsClickedArray.push(6);
		tdpCommitteeLevelIdsClickedArray.push(8);
		getcommitteesPerformanceCohort(tdpCommitteeLevelIdsClickedArray);
		
	});
	$(document).on("click",".comparisionBlock",function(){
		$("#SelectedUserTypeDetailsDiv").html(''); 
		$("#directChildActivityMemberDiv").html('');
		$("#topPoorPerformanceDiv").html('');
		$("#topPoorLocationsDiv").html(''); 
		
		
		$(".moreBlocks").hide();
		$(".moreBlocks1").show();
		getChildUserTypesByItsParentUserType();
		
		
	});
	
	$(document).on("click",".activeUlCls li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
	});
	
	function customBuildGraph()
	{
		var j = 0;
		$(".graphStructure").each(function(){
			j = j + 1;
			$(this).attr("id","graphStructure_"+j+"");
			var getWidth = $("#graphStructure_"+j+"").width();
			
			var getNoOfLis = getWidth / $("#graphStructure_"+j+" li").length - 4;
			var kk = getNoOfLis.toFixed() + 'px';
			$("#graphStructure_"+j+" li").width(kk);
			
			var myColors = ['#66728C','#F56800','#31AA74'];
			var i = 0;
			$("#graphStructure_"+j+" li").each(function(){
				var l = $(this).find("span").length;
				var getNoOfSpans = $(this).find("span").length;
				$(this).find("span").each(function(){
					$(this).css('background-color', myColors[i]);
					i = (i + 1) % getNoOfSpans;
				});
				$(this).find("span").each(function(){
					var getPercentage = $(this).attr("attr_percent");
					if(l > 1)
					{
						$(this).css("height",getPercentage+'%');
					}else{
						$(this).css("height","100%");
					}
				});
			});
			
		});
	}
	
	$(document).on("click",".comparisonSelect li",function(){
		if($(this).hasClass("active") == true)
		{
			$(this).removeClass("active");
		}else{
			$(".comparisonSelect li").removeClass("active");
			$(this).addClass("active");
		}
	});
	
	$(".stateCls").click(function(event){
      $(".stateCls").parent().removeClass("active");
	  $(this).parent().addClass("active");
	  globalState = $(this).html();
	  event.preventDefault();
    });
	
	$(document).on("click",".basicCommitteesBlockDiv",function(){
		$(this).closest(".moreBlocks").find(".basicCommitteesBlockDropDown").toggle();
	});
	
	$(document).on("click",".settingsDropDown",function(){
		$(this).toggleClass("dropdownOpen")
	});
	$(document).on("click",".toggleViewIcon",function(){
		$(this).toggleClass("dropDownView");
		//$(".settingsDropDownOptionsView").show();
	});
	$(document).on("click",".settingsDropDownOptions li,.toggleViewIcon .settingsDropDownOptionsView",function(e){
		e.stopPropagation();
	});
	function getLoggedInUserStructure(){
		$("#userLevelDetailsDiv").html('<div ><center ><img  src="images/icons/loading.gif" ></center></div>');
		var jsObj ={userId:loggedInUserId}
		
		$.ajax({
			type : 'POST',
			url : 'getLoggedInUserStructureAction.action',
			dataType : 'json',
			data : {task:JSON.stringify(jsObj)}
		}).done(function(result){
			$("#userLevelDetailsDiv").html('');
			buildUserLoginLevelDetails(result);
		});
	}
	
function MainPart(result){
		
	var str='';
	if(result.subList != null && result.subList.length > 0){
			str+='<div class="panel-heading" role="tab" id="headingOne"  style="height:30px;">';
				str+='<a role="button" style="width:30px" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">';
					str+='<span class="profileImageView">';
					str+='<img src="dist/coreDashboard/img/logo.png" />';
					str+='</span>';
				str+=' </a>';
				str+='<ul class="radioStyling" style="padding-left:0px;">';
					str+='<li>';
						str+='<input type="radio" id="mian" name="selector" >';
						var locationName = '';
						if(result.locationName != null && $.trim(result.locationName).length > 0){
							locationName = result.locationName;
						}else{
							locationName = result.userType;
						}
						str+='<label for="mian" class="text-capital userStructureClass" attr_userTypeId='+result.userTypeId+' attr_activityMemberId ='+result.activityMemberId+' attr_userAccessLevelId='+result.locationLevelId+'  attr_selectedmembername="'+result.name+'"   attr_selectedusertype="'+result.userType+'" attr_id ="directChildActivityMemberDiv" attr_userAccessLevelValuesString='+result.locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+result.name+'</i></span>';
						
						str+='<span class="profileImageView">';
						str+='<img src="https://mytdp.com/images/cadre_images/'+result.image+'" />';
						str+='</span>';
						str+='</label>';
						str+='<div class="check"></div>';
					str+='</li>';
				str+='</ul>';
			str+='</div>';
		}else{
			str+='<div class="panel-body">';
				str+=' <ul class="radioStyling">';
					str+='<li>';
						str+='<input type="radio" id="mian" name="selector">';
						var locationName = '';
						if(result.locationName != null && $.trim(result.locationName).length > 0){
							locationName = result.locationName;
						}else{
							locationName = result.userType;
						}
						str+=' <label for="mian" class="userStructureClass" attr_userTypeId='+result.userTypeId+' attr_activityMemberId ='+result.activityMemberId+' attr_userAccessLevelId='+result.locationLevelId+' attr_selectedmembername="'+result.name+'"   attr_selectedusertype="'+result.userType+'" attr_id ="directChildActivityMemberDiv" attr_userAccessLevelValuesString='+result.locationValuesSet+'><span class="hideDropDownView">'+locationName+' - <i>'+result.name+'</i></span>';
						str+='<span class="profileImageView">';
						str+='<img src="https://mytdp.com/images/cadre_images/'+result.image+'" />';
						str+='</span>';
						str+='</label>';
						str+='<div class="check"></div>';
					str+='</li>';
				str+='</ul>';
			str+='</div>';
		}
		return str;
	}
	function getView(i,shortName){
		var str='';
		str+='<div class="panel-heading" role="tab" id="headingOneSubView'+i+'">';
				str+='<a role="button" style="width: 30px; display: inline-block;" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionSub" href="#collapseOneSubView'+i+'" aria-expanded="true" aria-controls="collapseOneSubView'+i+'">';
					str+='<span class="profileImageView">';
					str+='<img src="dist/coreDashboard/img/logo.png" />';
					str+='</span>';
				str+=' </a>';
				str+='<h4 class="panel-title  text-capital"  >'+shortName+' - View</h4>';
		str+='</div>';	
		return str;
	}
	function getMemberName(locationName,shortName){
		var locName = '';
		if(locationName != null && $.trim(locationName).length > 0){
			locName = locationName;
		}else{
			locName =shortName;
		}
		return locName;
	}
	function buildUserLoginLevelDetails(result){
		
	var str='';
	if(result != null ){
		str+='<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">';
		  str+='<div class="panel panel-default panelProfileView">';
			//MAIN level start
			  var str1 = MainPart(result);
			  str = str + "" +str1;
			//MAIN level end
					
			//GENERAL SECRETARY and DISTRICT and... VIEW Start
			str+='<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">';
			str+='<div class="panel-body">';
			str+='<div class="panel-group" id="accordionSub" role="tablist" aria-multiselectable="true">';
			var userType = result.subList;					
			if(userType != null && userType.length > 0){
			  for(var i in userType){
				str+='<div class="panel panel-default panelProfileView">';
				  var str2 = getView(i,userType[i].shortName);
					str = str+ " " + str2;
				   //
					str+='<div id="collapseOneSubView'+i+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneSubView'+i+'">';
					str+='<div class="panel-body">';
					str+=' <div class="panel-group" id="accordionFirstLevelId'+i+'" role="tablist" aria-multiselectable="true">';
					var userTypeMember = userType[i].subList;
					if(userTypeMember != null && userTypeMember.length >0){
					  for(var j in userTypeMember){
						str+='<div class="panel panel-default panelProfileView">';
						if(userTypeMember[j].subList != null && userTypeMember[j].subList.length > 0){
								str+='<div class="panel-heading" role="tab" id="generalSecSubView'+i+''+j+'" style="height:30px;display:inline-block">';
									str+=' <a role="button" style="width:30px" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionFirstLevelId'+i+'" href="#collapseGeneralSecSubView'+i+''+j+'" aria-expanded="true" aria-controls="collapseGeneralSecSubView'+i+''+j+'">';
										str+='<span class="profileImageView">';
										str+='<img src="dist/img/logo.png" />';
										str+='</span>';
									str+=' </a>';
									str+='<ul class="radioStyling" style="float:left;">';
										str+=' <li>';
											str+='<input type="radio" id="'+i+''+j+'" name="selector">';
											var locationName = getMemberName(userTypeMember[j].locationName,userTypeMember[j].shortName);
											str+=' <label for="'+i+''+j+'" class="text-capital userStructureClass" attr_userTypeId='+userTypeMember[j].userTypeId+' attr_activityMemberId ='+userTypeMember[j].activityMemberId+' attr_userAccessLevelId='+userTypeMember[j].locationLevelId+' attr_selectedmembername="'+userTypeMember[j].name+'"   attr_selectedusertype="'+userTypeMember[j].userType+'" attr_id ="directChildActivityMemberDiv" attr_userAccessLevelValuesString='+userTypeMember[j].locationValuesSet+' ><span class="hideDropDownView">'+locationName+' - <i>'+userTypeMember[j].name+'</i></span>';
											str+='<span class="profileImageView">';
											str+='<img src="https://mytdp.com/images/cadre_images/'+userTypeMember[j].image+'" />';
											str+='</span>';
											str+='</label>';
											str+='<div class="check"></div>';
										str+='</li>';
									str+='</ul>';
								str+='</div>';
						}else{
								str+='<div class="panel-body dottedLine" style="float:left;padding-top:0px;padding-bottom:0px;">';
								str+=' <ul class="radioStyling">';
									str+=' <li>';
									str+='<input type="radio" id="'+i+''+j+'" name="selector">';
									var locationName = getMemberName(userTypeMember[j].locationName,userTypeMember[j].shortName);
									str+=' <label for="'+i+''+j+'" class="userStructureClass"  attr_userTypeId='+userTypeMember[j].userTypeId+' attr_activityMemberId ='+userTypeMember[j].activityMemberId+' attr_userAccessLevelId='+userTypeMember[j].locationLevelId+' attr_userAccessLevelValuesString='+userTypeMember[j].locationValuesSet+' attr_selectedmembername="'+userTypeMember[j].name+'"   attr_selectedusertype="'+userTypeMember[j].userType+'" attr_id ="directChildActivityMemberDiv" ><span class="hideDropDownView">'+locationName+' - <i>'+userTypeMember[j].name+'</i></span>';
									str+='<span class="profileImageView">';
									str+='<img src="https://mytdp.com/images/cadre_images/'+userTypeMember[j].image+'" />';
									str+='</span>';
									str+='</label>';
									str+='<div class="check"></div>';
									str+='</li>';
								str+='</ul>';
								str+='</div>';
						}
					    //GENERAL SECRETARY and DISTRICT and... Level End
						//ORGANIZING SECRETARY and CONSTITUENCT and... Level Start
						
					
					    str+='<div id="collapseGeneralSecSubView'+i+''+j+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="generalSecSubView'+i+''+j+'">';
						str+='<div class="panel-body">';
						str+=' <div class="panel-group" id="sree'+i+''+j+'" role="tablist" aria-multiselectable="true">';
						if(userTypeMember[j].subList != null && userTypeMember[j].subList.length > 0){
							childUserType = userTypeMember[j].subList;
						  for(var k in childUserType){
							if(childUserType[k].subList != null && childUserType[k].subList.length > 0){
							  
							  var childMember = childUserType[k].subList;
								for(var l in childMember){
									str+='<div class="panel panel-default panelProfileView">';
									if(childMember[l].subList != null && childMember[l].subList.length > 0){
										str+='<div class="panel-heading" style="height:30px" role="tab" id="organizerSecSubView'+i+''+j+''+k+''+l+'">';
											str+='<a role="button" style="width:30px;" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#sree'+i+''+j+'" href="#collapseOragaizerSecSubView'+i+''+j+''+k+''+l+'" aria-expanded="true" aria-controls="collapseOragaizerSecSubView'+i+''+j+''+k+''+l+'">';
											str+='<span class="profileImageView">';
											str+='<img src="dist/img/logo.png" />';
											str+='</span>';
											str+='</a>';
											str+='<ul class="radioStyling" style="float:left;">';
												str+='<li>';
													str+='<input type="radio" id="'+i+''+j+''+k+''+l+'" name="selector">';
													var locationName = getMemberName(childMember[l].locationName,childMember[l].shortName);
													str+=' <label for="'+i+''+j+''+k+''+l+'"  class="userStructureClass" attr_userTypeId='+childMember[l].userTypeId+' attr_activityMemberId ='+childMember[l].activityMemberId+' attr_userAccessLevelId='+childMember[l].locationLevelId+' attr_selectedmembername="'+childMember[l].name+'"   attr_selectedusertype="'+childMember[l].userType+'" attr_userAccessLevelValuesString='+childMember[l].locationValuesSet+' attr_id ="directChildActivityMemberDiv" ><span class="hideDropDownView">'+locationName+' - <i>'+childMember[l].name+'</i></span>';
													str+='<span class="profileImageView">';
													str+='<img src="https://mytdp.com/images/cadre_images/'+childMember[l].image+'" />';
													str+='</span>';
													str+='</label>';
													str+='<div class="check"></div>';
													str+='</li>';
											str+='</ul>';
										str+='</div>';
									}else{
										str+='<div class="panel-body" style="padding-top:0px;padding-bottom:0px;">';
										str+='<ul class="radioStyling" style="float:left;">';
											str+=' <li class=" dottedLine">';
												str+='<input type="radio" id="'+i+''+j+''+k+''+l+'" name="selector">';
												var locationName = getMemberName(childMember[l].locationName,childMember[l].shortName);
												str+=' <label for="'+i+''+j+''+k+''+l+'" class="userStructureClass" attr_userTypeId='+childMember[l].userTypeId+' attr_activityMemberId ='+childMember[l].activityMemberId+' attr_userAccessLevelId='+childMember[l].locationLevelId+' attr_userAccessLevelValuesString='+childMember[l].locationValuesSet+' attr_selectedmembername="'+childMember[l].name+'"   attr_selectedusertype="'+childMember[l].userType+'" attr_id ="directChildActivityMemberDiv" ><span class="hideDropDownView">'+locationName+' - <i>'+childMember[l].name+'</i></span>';
												str+='<span class="profileImageView">';
												str+='<img src="https://mytdp.com/images/cadre_images/'+childMember[l].image+'" />';
												str+='</span>';
												str+='</label>';
												str+='<div class="check"></div>';
											str+='</li>';
										str+='</ul>';
										str+='</div>';
									}
                                     									
                                     //sub sub childs start
                                    
					    str+='<div id="collapseOragaizerSecSubView'+i+''+j+''+k+''+l+'" class="panel-collapse collapse" role="tabpanel" aria-labelledby="organizerSecSubView'+i+''+j+''+k+''+l+'">';
						str+='<div class="panel-body">';
						str+=' <div class="panel-group" id="accordionThirdLevel" role="tablist" aria-multiselectable="true">';
						if(childMember[l].subList != null && childMember[l].subList.length > 0){
							childChildUserType = childMember[l].subList;
						  for(var m in childChildUserType){
							if(childChildUserType[m].subList != null && childChildUserType[m].subList.length > 0){
							 
							  var childChildMember = childChildUserType[m].subList;
								for(var n in childChildMember){
									 str+='<div class="panel panel-default panelProfileView">';
									if(childChildMember[n].subList != null && childChildMember[n].subList.length > 0){
										str+='<div class="panel-heading" style="height:30px" role="tab" id="organizerSecSubView'+i+''+j+''+k+''+l+''+m+''+n+'">';
											str+='<a role="button" style="width:30px;" class="collapsed profieViewCollapse" data-toggle="collapse" data-parent="#accordionThirdLevel" href="#collapseOragaizerSecSubView'+i+''+j+''+k+''+l+''+m+''+n+'" aria-expanded="true" aria-controls="collapseOragaizerSecSubView'+i+''+j+''+k+''+l+''+m+''+n+'">';
											str+='<span class="profileImageView">';
											str+='<img src="dist/img/logo.png" />';
											str+='</span>';
											str+='</a>';
											str+='<ul class="radioStyling">';
												str+='<li>';
													str+='<input type="radio" id="'+i+''+j+''+k+''+l+''+m+''+n+'" name="selector">';
													var locationName = getMemberName(childChildMember[n].locationName,childChildMember[n].shortName);
													str+=' <label for="'+i+''+j+''+k+''+l+''+m+''+n+'"  class="userStructureClass" attr_userTypeId='+childChildMember[n].userTypeId+' attr_activityMemberId ='+childChildMember[n].activityMemberId+'  attr_userAccessLevelId='+childChildMember[n].locationLevelId+' attr_userAccessLevelValuesString='+childChildMember[n].locationValuesSet+' attr_selectedmembername="'+childChildMember[n].name+'"   attr_selectedusertype="'+childChildMember[n].userType+'" attr_id ="directChildActivityMemberDiv" ><span class="hideDropDownView">'+locationName+' - <i>'+childChildMember[n].name+'</i></span>';
													str+='<span class="profileImageView">';
													str+='<img src="https://mytdp.com/images/cadre_images/'+childChildMember[n].image+'" />';
													str+='</span>';
													str+='</label>';
													str+='<div class="check"></div>';
													str+='</li>';
											str+='</ul>';
										str+='</div>';
									}else{
										str+='<div class="panel-body" style="padding-top:0px;padding-bottom:0px;">';
										str+='<ul class="radioStyling">';
											str+=' <li class=" dottedLine">';
												str+='<input type="radio" id="'+i+''+j+''+k+''+l+''+m+''+n+'" name="selector">';
												var locationName = getMemberName(childChildMember[n].locationName,childChildMember[n].shortName);
												str+=' <label for="'+i+''+j+''+k+''+l+''+m+''+n+'" class="userStructureClass" attr_userTypeId='+childChildMember[n].userTypeId+' attr_activityMemberId ='+childChildMember[n].activityMemberId+' attr_userAccessLevelId='+childChildMember[n].locationLevelId+' attr_userAccessLevelValuesString='+childChildMember[n].locationValuesSet+' attr_selectedmembername="'+childChildMember[n].name+'"   attr_selectedusertype="'+childChildMember[n].userType+'" attr_id ="directChildActivityMemberDiv" ><span class="hideDropDownView">'+locationName+' - <i>'+childChildMember[n].name+'</i></span>';
												str+='<span class="profileImageView">';
												str+='<img src="https://mytdp.com/images/cadre_images/'+childChildMember[n].image+'" />';
												str+='</span>';
												str+='</label>';
												str+='<div class="check"></div>';
											str+='</li>';
										str+='</ul>';
										str+='</div>';
									}
									str+='</div>';
								}//for
						
					}//if
			}//for
		 }
		 str+='</div>';
		 str+='</div>';
		 str+=' </div>';	
		     
		//sub sub childs end

								str+='</div>';	
								}//for
						
					}//if
			}//for
		 }
		 str+='</div>';
		 str+='</div>';
		 str+=' </div>';	
		
		 //ORGANIZING SECRETARY and CONSTITUENCT and... Level End
	  str+='</div>';
	    }//for
	  }//if
				str+='</div>';
				str+='</div>';
				str+=' </div>';
				 str+='</div>';
				}//view for
			}//view if
						str+='</div>';
					str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</div>';
	}
		
	  $("#userLevelDetailsDiv").html(str);

}