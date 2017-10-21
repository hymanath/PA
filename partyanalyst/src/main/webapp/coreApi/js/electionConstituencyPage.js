var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStrongPoorColor={"VERY GOOD":'#009900',"OK":"#FFCC00","POOR":"#FF9966","VERY POOR":"#CC6600","WORST":"#FF3E3E","GOOD":"#428AE9","EXCELLENT":"#009999"}
var globalCrossVotionBgColor={"TDP":'#FFFFB8',"YSRC":"#CEE8DF","BJP":"#f4c36e","AIMIM":"#7bf7a6","INC":"#CEDFEA","CPI":"#fc713f","CPM":"#f995b5","PRP":"#b6f986","TRS":"#F7E8D9"}
var eletionSubType=["MAIN"];
var electionYrVal = [];
var globalParliamentIds = [];
var mainArr = [];
var boothWiseDetailsArr;
$(window).scroll(function(){
		var windowScrollTop = $(window).scrollTop();
		//var header = $('.scrollHeading')
		var header = $('.dummy')
		if (windowScrollTop>50) {
			header.addClass("header-fixed");
		} else{
			header.removeClass("header-fixed");
		}
		scrollAnimation();
	});
	function scrollAnimation(){
		var winScroll = $(window).scrollTop(),
            winHeight = $(window).outerHeight();
        $('.scroll-animation.scroll-animate').each(function(){
            if (winScroll > $(this).offset().top - winHeight + 200) {
                $(this).removeClass("scroll-animate");
            }
        });
        $('.dotted-path').each(function(){
        	if (winScroll > $(this).offset().top - winHeight + 200) {
        		$(this).removeClass("hidden-path");
        	}
        });
	}

	
setTimeout(function(){ 
	onLoadCalls();
},1500)

function onLoadCalls()
{
	$(".chosen-select").chosen();
	$('#partyBoothWiseId, #partyId,#partyCrossVotingId').multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All Parties',
		maxHeight: 300,
		maxWidth: 350,
		dropDown: true,
		selectAllNumber: true,
		allSelectedText: 'All Parties selected'
	});
	
	$("#mianHeadingId").html((locationName)+"  "+locationLevelName);
	
	if(locationLevelId == '4'){
		getDetailedElectionInformaction();
	}
	
	if(locationLevelId < "5"){
		$(".searchLevelCls").show();
	}else{
		$(".searchLevelCls").hide();
	}
	if(locationLevelId == '3'){
		$("#searchLevelId").html('');
		$("#searchLevelId").append('<option value="constituency">Constituency</option>');
		$("#searchLevelId").append('<option value="mandal">Mandal</option>');
		$("#searchLevelId").trigger("chosen:updated");
	}else if(locationLevelId == '4'){
		$("#searchLevelId").html('');
		$("#searchLevelId").append('<option value="mandal">Mandal</option>');
		$("#searchLevelId").append('<option value="panchayat">Panchayat</option>');
		$("#searchLevelId").trigger("chosen:updated");
	}else if(locationLevelId == '10'){
		$("#searchLevelId").html('');
		$("#searchLevelId").append('<option value="constituency">constituency</option>');
		$("#searchLevelId").trigger("chosen:updated");
	}
	
	getElectionTypes();
	
}

$(document).on("click",".electionTypeWiseCls",function(){
	var value = $(this).val();
	if(value != 0){
		$(".checkUncheckCls").prop("checked",false);
	}else if(value == 0){
		if ($(this).is(':checked')){
			$(".checkUncheckCls").prop("checked",true);
			$(".electionTypeWiseCls").prop("checked",true);
		}else{
			$(".checkUncheckCls").prop("checked",false);
			$(".electionTypeWiseCls").prop("checked",false);
		}
		
	}
});
$(document).on("click",".electionSubTypeCls",function(){
	$('#electionYearId').multiselect('destroy');
	var type = $(this).attr("attr_type");
	eletionSubType=[];
		if ($(this).is(':checked')){
			eletionSubType.push($(this).val());
		}
	getElectionYears(eletionSubType,"change");
	
});
$(document).on("click",".electionSubTypeBoothWiseCls",function(){
	var type = $(this).attr("attr_type");
	eletionSubType=[];
		if ($(this).is(':checked')){
			eletionSubType.push($(this).val());
		}
	getElectionYearsForBooth(eletionSubType,"change");
	
});

$(document).on("change","#parliamentConsId",function(){
		var parliamentId = $(this).val();
		getAllConstituencyByParlimentId(parliamentId)
});
//Main
	$(document).on("click",".getDetailsCls",function(){
		
		var i = 0;	
		 var electionScopeId =[];
		 $(".electionTypeWiseCls").each(function(){
			if ($(this).is(':checked')){
				 electionScopeId[i++] = $(this).val();
			}
		});
		
		eletionSubType=[];
		 $('.electionSubTypeCls').each(function(){
			if ($(this).is(':checked')){
				eletionSubType.push($(this).val());
			}
		 });
		electionYrVal=[];
		electionYrVal = $("#electionYearId").val();
		
		var partyIdArr=[];
		partyIdArr = $("#partyId").val();
		var parliamentId=0;var assemblyId=0;
		
		//party wise election trends Block
		getLocationWiseElectionResults(electionYrVal,eletionSubType,partyIdArr,electionScopeId);
		//Party Election Results Block
		getElectionDetailsData(electionYrVal,eletionSubType,partyIdArr,electionScopeId);
		//Cross Voting Block
		getLocationWiseCrossVotingDetails(electionYrVal,parliamentId,assemblyId,partyIdArr,eletionSubType,electionScopeId);
		//getLocationWiseVotingDetails(electionYrVal,eletionSubType,"",userAccessLevelValuesArray,locationLevelId)
		//Booth Wise Results
		getElectionYearsForBooth(eletionSubType,"onload");
		//Very Poor Strong
		
		var partyIdStrong = $("#partyIdForStrongBlock").val();
		
		var searchLevelVal = $("#searchLevelId").val();
		var partyNameStrong = $("#partyIdForStrongBlock option:selected").text();
		var electionTypetext = $("#elctionTypeValId option:selected").text();
		
		var electionScopeIdStrong = $("#elctionTypeValId").val();
		
		getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,partyIdStrong,searchLevelVal,electionScopeIdStrong,partyNameStrong,electionTypetext,0);
		
		if(locationLevelId == '4'){
			getDetailedElectionInformaction();
		}
	});
	
	
//Cross Voting Start
	$(document).on("click",".getDetailsForCrossVotingCls",function(){
		
		var parliamentId = $("#parliamentConsId").val();
		var assemblyId = $("#assemblyConsId").val();
		var partyIdArr=[];
		partyIdArr = $("#partyCrossVotingId").val();
		
		var i = 0;	
		 var electionScopeId =[];
		 $(".electionTypeWiseCls").each(function(){
			if ($(this).is(':checked')){
				 electionScopeId[i++] = $(this).val();
			}
		});
		
		eletionSubType=[];
		 $('.electionSubTypeCls').each(function(){
			if ($(this).is(':checked')){
				eletionSubType.push($(this).val());
			}
		 });
		electionYrVal=[];
		electionYrVal = $("#electionYearId").val();
		userAccessLevelValuesArray=[];
		userAccessLevelValuesArray.push(userAccessLevelValue);
		
		getLocationWiseCrossVotingDetails(electionYrVal,parliamentId,assemblyId,partyIdArr,eletionSubType,electionScopeId);
		//getLocationWiseVotingDetails(electionYrVal,eletionSubType,"",userAccessLevelValuesArray,locationLevelId)
			
		
	});
	$(document).on("click","[role='tabSwitch'] li",function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");	
		
		var electionScopeId=$(this).attr("attr_val");
		var constituencyId = $("#assemblyConsBoothWiseId").val();
		var partiesArr=[];
		partiesArr = $("#partyBoothWiseId").val();
		var electionYrVal = $("#electionYearBoothWiseId").val();
		
		boothWiseResults(constituencyId,partiesArr,electionYrVal,electionScopeId)
		
	});
$(document).on("click",".getDetailsForBoothWiseCls",function(){
		
		$(".switch-btn-New li").removeClass("active");
		$(".activeCls").addClass("active");
		var constituencyId = $("#assemblyConsBoothWiseId").val();

		var partiesArr=[];
		partiesArr = $("#partyBoothWiseId").val();
		
		var electionYrVal = $("#electionYearBoothWiseId").val();
		
		var electionScopeId =2;
		
		boothWiseResults(constituencyId,partiesArr,electionYrVal,electionScopeId)	
		
});
$(document).on("click",".getDetailsForStrongCls",function(){
		var partyId = $("#partyIdForStrongBlock").val();
		
		var searchLevelVal = $("#searchLevelId").val();
		var partyName = $("#partyIdForStrongBlock option:selected").text();
		var electionTypetext = $("#elctionTypeValId option:selected").text();
		
		var electionScopeId = $("#elctionTypeValId").val();
		
		eletionSubType=[];
		 $('.electionSubTypeCls').each(function(){
			if ($(this).is(':checked')){
				eletionSubType.push($(this).val());
			}
		 });
		electionYrVal=[];
		electionYrVal = $("#electionYearId").val();
		
	getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,partyId,searchLevelVal,electionScopeId,partyName,electionTypetext,0);	
});		
function getElectionTypes(){
	$("#electionScopeDivIds").html(spinner);
	var jsObj={
			
	}
	$.ajax({
		type : "GET",	
		url : "getElectionTypesAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
    }).done(function(result){  
    	if(result !=null && result.length>0){
			var str='';
			str+='<label class="text-capital m_left5" style="margin-right: 10px;">';
				str+='<input value="0" type="checkbox" class="electionTypeWiseCls checkUncheckCls" /><span class="f-12">All</span>';
			str+='</label>';
			for(var i in result){
				str+='<label class="text-capital m_left5" style="margin-right: 10px;">';
					if(result[i].id != "5" && result[i].id != "6" && result[i].id != "7" && result[i].id != "8" && result[i].id != "9"){
						if(result[i].id == "1" || result[i].id == "2"){
							if(locationLevelId == 2){
								str+='<input value ="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
							}else if(locationLevelId == 3 || locationLevelId == 10){
								if(result[i].id != "1"){
									str+='<input value ="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
								}
							}else if(locationLevelId == 4){
								//if(result[i].id != "2"){
									str+='<input value ="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
								//}
							}else if(locationLevelId == 5){
								if(result[i].id != "4"){
									str+='<input value ="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
								}
							}else{
								str+='<input value ="'+result[i].id+'" type="checkbox" checked class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
							}
						}else{
							if(locationLevelId == 2){
								str+='<input value ="'+result[i].id+'" type="checkbox"  class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
							}else if(locationLevelId == 3 || locationLevelId == 10){
								if(result[i].id != "1"){
									str+='<input value ="'+result[i].id+'" type="checkbox"  class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
								}
							}else if(locationLevelId == 4){
								//if(result[i].id != "2"){
									str+='<input value ="'+result[i].id+'" type="checkbox"  class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
								//}
							}else if(locationLevelId == 5){
								if(result[i].id != "4"){
									str+='<input value ="'+result[i].id+'" type="checkbox"  class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
								}
							}else{
								str+='<input value ="'+result[i].id+'" type="checkbox"  class="electionTypeWiseCls" /><span class="f-12">'+result[i].name+'</span>';
							}
						}
					}
					
				str+='</label>';
			}
		}
		$("#electionScopeDivIds").html(str);
		//don't deleted this call
		getAllParliamentConstituencyByAllLevels("");
		getElectionYearsForBooth(eletionSubType,"onload");
		
		
		
		/* if(locationLevelId == '4'){
			getAllParliamentConstituencyByAllLevels("");
			getElectionYearsForBooth(eletionSubType,"onload");
		}else if(locationLevelId == '10'){
			getAllParliamentConstituencyByAllLevels("booth");
		}else{
			getAllParliamentConstituencyByAllLevels("");
		} */
		
		
	});	
}
function getElectionYearsForBooth(eletionSubType,type){
	
	$('#electionYearBoothWiseId').html(spinner);
  var jsObj={
      electionSubTypeArr:eletionSubType
    }
    $.ajax({   
      type:'POST',
      url:'getAllElectionYearsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.imageList !=null && result.imageList.length>0){
			var str='';
			for(var i in result.imageList){
				if(result.imageList[i] == "2014"){
					str+='<option value="'+result.imageList[i]+'" selected>'+result.imageList[i]+'</option>';
				}else{
					str+='<option value="'+result.imageList[i]+'">'+result.imageList[i]+'</option>';
				}
			} 
			$('#electionYearBoothWiseId').html(str);
			$('#electionYearBoothWiseId').trigger("chosen:updated");
		}
		if(type == "onload"){
			if(locationLevelId == '10'){
				getAllConstituencyByParliments(parliamentId);
			}else if(locationLevelId == '4' || locationLevelId == '5' || locationLevelId == '6'){
				//var constituencyId= $("#assemblyConsBoothWiseId").val();
				var electionScopeId=0;var electionScopeId =2;
				var partyIdArr=[];
				partyIdArr = $("#partyId").val();
				var electionYrVal = $("#electionYearBoothWiseId").val();
				boothWiseResults(constituencyId,partyIdArr,electionYrVal,electionScopeId)
				$("#assemblyConsBoothWiseId").html('');
				 $("#assemblyConsBoothWiseId").append('<option value="'+constituencyId+'">'+locationName+'</option>');	
				 $("#assemblyConsBoothWiseId").trigger("chosen:updated");
				 var locationNameText = $("#assemblyConsBoothWiseId option:selected").text();
				$("#levelNameDivId").html(locationNameText);	
			}else if(locationLevelId == '3'){
				getConstituenciesByDistrict(districtId);
			}else{
				getConstituenciesByDistrict(0);
			}
			
		}
		
	});
}
function getAllConstituencyByParliments(id){
	$('#assemblyConsBoothWiseId').html('');
	var consId = constituencyId;
	  var jsObj={
		 "parlimentId":id
		}
		$.ajax({
		  type : "GET",
		  url : "getAllConstituencyByParlimentIdForLoationDashBoardAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				var str='';
				for(var i in result){
					if(result[i].locationId == consId){
						str+='<option value="'+result[i].locationId+'" selected>'+result[i].locationName+'</option>';
					}else{
						str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';
					}
					
				} 
				$('#assemblyConsBoothWiseId').html(str);
				$('#assemblyConsBoothWiseId').trigger("chosen:updated");
			}
			
			var constituencyId= $("#assemblyConsBoothWiseId").val();
			var electionScopeId=0;var electionScopeId =2;
			var partyIdArr=[];
			partyIdArr = $("#partyId").val();
			var electionYrVal = $("#electionYearBoothWiseId").val();
			boothWiseResults(constituencyId,partyIdArr,electionYrVal,electionScopeId)
		});
   }
function getConstituenciesByDistrict(id){
	$('#assemblyConsBoothWiseId').html('');
	var consId = constituencyId;
	  var jsObj={
		 "districtId":id
		}
		$.ajax({
		  type : "GET",
		  url : "getConstituenciesByDistrictForLoationDashBoardAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			if(result !=null && result.length>0){
				var str='';
				for(var i in result){
					if(result[i].locationId == consId){
						str+='<option value="'+result[i].locationId+'" selected>'+result[i].locationName+'</option>';
					}else{
						str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';
					}
					
				}
				
				$('#assemblyConsBoothWiseId').html(str);
				$('#assemblyConsBoothWiseId').trigger("chosen:updated");
				var locationNameText = $("#assemblyConsBoothWiseId option:selected").text();
				$("#levelNameDivId").html(locationNameText);	
			}
			
			var constituencyId= $("#assemblyConsBoothWiseId").val();
			var electionScopeId=0;var electionScopeId =2;
			var partyIdArr=[];
			partyIdArr = $("#partyId").val();
			var electionYrVal = $("#electionYearBoothWiseId").val();
			boothWiseResults(constituencyId,partyIdArr,electionYrVal,electionScopeId)
		});
   }
function getAllParliamentConstituencyByAllLevels(type){
	  $('#parliamentConsId').html('');
	  var jsObj={
		  locationValue:userAccessLevelValuesArray,
		  loactionTypeId:locationLevelId
		}
		$.ajax({
		  type : "GET",
		  url : "getAllParliamentConstituencyByAllLevelsAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
		  if(result !=null && result.length>0){
				var str='';
				str+='<option value="0" selected>All</option>';
				for(var i in result){
					str+='<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>';
					globalParliamentIds.push(result[i].locationId)
						
				}
				if(type =="booth"){
					$('#assemblyConsBoothWiseId').html(str);
					$('#assemblyConsBoothWiseId').trigger("chosen:updated");
				}else{
					$('#parliamentConsId').html(str);
					$('#parliamentConsId').trigger("chosen:updated");
				}	
				
			}
			if(type =="booth"){
				var constituencyId= $("#assemblyConsBoothWiseId").val();
				var electionScopeId=0;var electionScopeId =2;
				var electionYrVal = $("#electionYearBoothWiseId").val();
				var partyIdArr=[];
				partyIdArr = $("#partyId").val();
				boothWiseResults(constituencyId,partyIdArr,electionYrVal,electionScopeId)
			}else{
				getElectionYears(eletionSubType,"onload");
			}
				
		});
}
function getAllConstituencyByParlimentId(parliamentId){
	  $('#assemblyConsId').html('');
	  var jsObj={
		 "parlimentId":parliamentId
		}
		$.ajax({
		  type : "GET",
		  url : "getAllConstituencyByParlimentIdForLoationDashBoardAction.action",
		  dataType : 'json',
		  data : {task :JSON.stringify(jsObj)}
		}).done(function(result){
			var str='';
			str+='<option value="0" >All</option>';
		  if(result !=null && result.length>0){
				for(var i in result){
					str+='<option value="'+result[i].locationId+'" >'+result[i].locationName+'</option>';
				} 
			}
			$('#assemblyConsId').html(str);
			$('#assemblyConsId').trigger("chosen:updated");
		});
}   
function getElectionYears(eletionSubType,type){
	
	$('#electionYearId').html(spinner);
  var jsObj={
      electionSubTypeArr:eletionSubType
    }
    $.ajax({   
      type:'POST',
      url:'getAllElectionYearsAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.imageList !=null && result.imageList.length>0){
			var str='';
			for(var i in result.imageList){
				if(result.imageList[i] == "2014" || result.imageList[i] == "2009"){
					str+='<option value="'+result.imageList[i]+'" selected>'+result.imageList[i]+'</option>';
				}else{
					str+='<option value="'+result.imageList[i]+'">'+result.imageList[i]+'</option>';
				}
			} 
			$('#electionYearId').html(str);
			$('#electionYearId').multiselect({
				enableFiltering: true,
				includeSelectAllOption: true,
				selectAllText: 'All Election Years',
				maxHeight: 300,
				maxWidth: 300,
				dropDown: true,
				selectAllNumber: true,
				allSelectedText: 'All Election Years selected'
			});
			
		}
		
		if(type == "onload"){
			electionYrVal=[];
			electionYrVal = $("#electionYearId").val();
			var electionScopeId=0;var parliamentId=0;var assemblyId=0;
			var partyIdArr=[];
			partyIdArr = $("#partyId").val();
			//party wise election trends Block
			getLocationWiseElectionResults(electionYrVal,eletionSubType,partyIdArr,electionScopeId);
			//Party Election Results Block
			getElectionDetailsData(electionYrVal,eletionSubType,partyIdArr,electionScopeId);
			//Cross Voting Block
			getLocationWiseCrossVotingDetails(electionYrVal,parliamentId,assemblyId,partyIdArr,eletionSubType,electionScopeId);
			//getLocationWiseVotingDetails(electionYrVal,eletionSubType,"",userAccessLevelValuesArray,locationLevelId)
			//StrongVsPoor Block
			getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,"872","district","2","TDP","Assembly",0);
		}
    });
}

function getDetailedElectionInformaction(){
	$("#candidatesResultsDivId").html(spinner);
	jsObj={
	  	constituencyId: constituencyId
    }
    $.ajax({
      type : "GET",
      url : "getDetailedElectionInformactionAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildDetailedElectionInformaction(result);
		}
	});
	
	function buildDetailedElectionInformaction(result){
		var navTabs = '';
		navTabs+='<div class="block">';
			navTabs+='<h4 class="theme-title-color">Assembly Election Year Wise Details</h4>';
		navTabs+='<div class="row">';
			navTabs+='<div class="col-sm-5" style="padding-right: 10px;">';
				navTabs+='<div class="panel panel-default panel-border">';
					navTabs+='<div class="panel-heading winnersColor" >';
						navTabs+='<div class="media">';
							navTabs+='<div class="media-left">';
								navTabs+='<img src="coreApi/img/winner_icon.png" alt="winner_icon"></img>';
							navTabs+='</div>';
							navTabs+='<div class="media-body">';
								navTabs+='<h3 class="m_top20">Winners</h3>';
							navTabs+=' </div>';
						navTabs+='</div>';
					navTabs+='</div>';
					navTabs+='<div class="panel-body">';
						navTabs+='<ul class="nav nav-tabs nav-tabs-horizontal_Elec" role="tablist">';
							for(var i in result){
								if(i == 0){
									navTabs+='<li class="active">';
										navTabs+='<a href="#candidatesResult'+i+'" navTabs-click="" aria-controls="candidatesResult'+i+'" role="tab" data-toggle="tab">';
											navTabs+='<div class="media">';
												navTabs+='<div class="media-left">';
													navTabs+='<div class="row">';
															navTabs+='<div class="col-sm-5">';
																navTabs+='<h4 class="m_top10">'+result[i].electionYear+'</h4>';
															navTabs+=' </div>';
															navTabs+='<div class="col-sm-5">';
															if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}
																navTabs+='<p class="">'+result[i].candidateResultsVO.partyShortName+'</p>';
															navTabs+=' </div>';
													navTabs+='</div>';
													
												navTabs+='</div>';
													navTabs+='<div class="media_body_custom">';
														navTabs+='<h5>'+result[i].candidateResultsVO.candidateName+'</h5>';
															navTabs+='<div class="row m_top5">';
																navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Votes&nbsp;Earned</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesEarned+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentage+'%</span></h6>';
															navTabs+='</div>';
															navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Margin&nbsp;Gained</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesMargin+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentMargin+'%</span></h6>';
															navTabs+='</div>';
														navTabs+=' </div>';
													navTabs+=' </div>';
											navTabs+='</div>';
										navTabs+='</a>';
									navTabs+='</li>'; 
								}else{
									navTabs+='<li class="">';
										navTabs+='<a href="#candidatesResult'+i+'" navTabs-click="" aria-controls="candidatesResult'+i+'" role="tab" data-toggle="tab">';
											navTabs+='<div class="media">';
												navTabs+='<div class="media-left">';
													navTabs+='<div class="row">';
															navTabs+='<div class="col-sm-5">';
																navTabs+='<h4 class="m_top10">'+result[i].electionYear+'</h4>';
															navTabs+=' </div>';
															navTabs+='<div class="col-sm-5">';
															if(result[i].candidateResultsVO.partyShortName == "TDP" || result[i].candidateResultsVO.partyShortName == "YSRC"){
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'"></img>';
															}
																navTabs+='<p class="">'+result[i].candidateResultsVO.partyShortName+'</p>';
															navTabs+=' </div>';
													navTabs+='</div>';
													
												navTabs+='</div>';
													navTabs+='<div class="media_body_custom">';
														navTabs+='<h5>'+result[i].candidateResultsVO.candidateName+'</h5>';
															navTabs+='<div class="row m_top5">';
																navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Votes&nbsp;Earned</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesEarned+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentage+'%</span></h6>';
															navTabs+='</div>';
															navTabs+='<div class="col-sm-6">';
																navTabs+='<h6 class="f-11 normal_font">Margin&nbsp;Gained</h6>';
																navTabs+='<h6 class="m_top5 f-11 normal_font">'+result[i].candidateResultsVO.votesMargin+'   <span style="color:green;">'+result[i].candidateResultsVO.votesPercentMargin+'%</span></h6>';
															navTabs+='</div>';
														navTabs+=' </div>';
													navTabs+=' </div>';
											navTabs+='</div>';
										navTabs+='</a>';
									navTabs+='</li>';
								}
							}
							
							
						navTabs+='</ul>';
					navTabs+='</div>';
				navTabs+='</div>';
			navTabs+='</div>';
			
				
			
			navTabs+='<div class="col-sm-7 pad_left0">';
				navTabs+='<div class="panel panel-default panel-border">';
					navTabs+='<div class="panel-heading looserColor" >';
					
						navTabs+='<div class="media">';
							navTabs+='<div class="media-left">';
								navTabs+='<img src="coreApi/img/loser_icon.png" alt="loser_icon"></img>';
							navTabs+='</div>';
							navTabs+='<div class="media-body">';
								navTabs+='<h3 class="m_top20">Losers</h3>';
							navTabs+=' </div>';
						navTabs+='</div>';
						
					navTabs+='</div>';
					navTabs+='<div class="panel-body panel_body_custom">';
						navTabs+='<div class="tab-content">';
								for(var i in result){
									if(i == 0){
										navTabs+='<div role="tabpanel" class="tab-pane active pad_10 block" id="candidatesResult'+i+'" style="border-radius: 10px;">';
									}else{
										navTabs+='<div role="tabpanel" class="tab-pane pad_10 block" id="candidatesResult'+i+'" style="border-radius: 10px;">';
									}
										navTabs+='<div class="table-responsive">';
										navTabs+='<table class="table table-condensed table_padding" id="candidatesResultsDT">';
											navTabs+='<thead>';
												navTabs+='<tr>';
													navTabs+='<th>Candidate Name</th>';
													navTabs+='<th>Party</th>';
													navTabs+='<th>Party Flag</th>';
													navTabs+='<th>Votes Earned</th>';
													navTabs+='<th>Earned %</th>';
													navTabs+='<th>Rank</th>';
												navTabs+='</tr>';
											navTabs+='</thead>';
											
											navTabs+='<tbody>';
												for(var j in result[i].candidateOppositionList){
													navTabs+='<tr>';
														if(result[i].candidateOppositionList[j].candidateName !=null && result[i].candidateOppositionList[j].candidateName.length>10){
															navTabs+='<td><span class="candidate_rounded">'+result[i].candidateOppositionList[j].candidateName.charAt(0)+'</span><span class="tooltipCls" style="cursor:pointer; data-toggle="tooltip" data-placement="right" title="'+result[i].candidateOppositionList[j].candidateName+'">'+result[i].candidateOppositionList[j].candidateName.substring(0,10)+'...</span></td>';
														}else{
															navTabs+='<td><span class="candidate_rounded">'+result[i].candidateOppositionList[j].candidateName.charAt(0)+'</span>'+result[i].candidateOppositionList[j].candidateName+'</td>';
														}
														
														
														navTabs+='<td>'+result[i].candidateOppositionList[j].partyShortName+'</td>';
														
														if(result[i].candidateOppositionList[j].partyShortName == "TDP" || result[i].candidateOppositionList[j].partyShortName == "YSRC"){
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'.PNG" alt="'+result[i].candidateOppositionList[j].partyShortName+'"></img></td>';
														}else{
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'.png" alt="'+result[i].candidateOppositionList[j].partyShortName+'"></img></td>';
														}
														
														navTabs+='<td>'+result[i].candidateOppositionList[j].votesEarned+'</td>';
														navTabs+='<td>'+result[i].candidateOppositionList[j].votesPercentage+'</td>';
														navTabs+='<td>'+result[i].candidateOppositionList[j].rank+'</td>';
													navTabs+='</tr>';
												}
											navTabs+='</tbody>';
										navTabs+='</table>';
										navTabs+='</div>';
									navTabs+='</div>';
								}	
								
							
						navTabs+='</div>'; 
					navTabs+='</div>';
				navTabs+='</div>';
			navTabs+='</div>';
		navTabs+='</div>';
		navTabs+='</div>';
		$("#candidatesResultsDivId").html(navTabs);
		$(".tooltipCls").tooltip();
		$("#candidatesResultsDT").dataTable({
			"paging":   true,
			"info":     false,
			"searching": false,
			"autoWidth": true,
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
}
var searchParams = new URLSearchParams(window.location.search);
 searchParams = searchParams.get("constituencyId");
function getLocationWiseElectionResults(electionYrVal,eletionSubType,partyId,electionScopeId){
	
	$("#levelWiseCandidatesResultsDivId").html(spinner);
	
	var partyIdsArr=[];
	var electionScopeIdsArr=[];
	var constituencyId;
	if(partyId == 0){
		partyIdsArr = [872,362,1117,886,72,269,265,163];
	}else{
		partyIdsArr = partyId
	}
	
	if(electionScopeId == 0){
		 electionScopeIdsArr = [];
		 $(".electionTypeWiseCls").each(function(){
			if ($(this).is(':checked')){
				 electionScopeIdsArr.push(parseInt($(this).val()))
			}
		});
	}else{
		electionScopeIdsArr = electionScopeId
	}
	if(locationLevelId != null &&(locationLevelId == 2 || locationLevelId == 3 || locationLevelId == 10)){
		constituencyId = 0;
	}else{
		constituencyId = searchParams
	}
	var jsObj={
		
		electionScopeIdsArr:electionScopeIdsArr,
		electionSubTypeArr:eletionSubType,
		lelevlId:locationLevelId,
		locationValuesArr:userAccessLevelValuesArray,
		yearsArr:electionYrVal,
		partyIdsArr:partyIdsArr,
		constituencyId :constituencyId
    }
    $.ajax({
      type : "GET",
      url : "getLocationWiseElectionResultsAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildLocationWiseElectionResults(result);
		}else{
			$("#levelWiseCandidatesResultsDivId").html("No Data Available");
		}
	});
	
	function buildLocationWiseElectionResults(result){
		
		var str='';
		str+='<h4 class="theme-title-color">'+locationLevelName+' level party wise election trends</h4>';
		str+='<ul class="list-inline levelWiseCandiRstsCls m_top10">';
		for(var i in result){
			str+='<li style="margin-right:15px;margin-left:15px;">';
				str+='<div class="block">';
					str+='<h4>'+result[i].electionType+' Election Results</h4>';
					str+='<p>'+result[i].electionYear+'</p>';
					
					str+='<div class="bg_ED" style="padding:10px;">';
						str+='<div class="row">';
							str+='<div class="col-sm-5">';
								str+='<h6>Total Seats: '+result[i].totalSeatsCount+'</h6>';
							str+='</div>';
							str+='<div class="col-sm-7">';
								str+='<h6>Participated Members: '+result[i].participatedSeatsCount+'</h6>';
							str+='</div>';
						str+='</div>';
					str+='</div>';
					if(result[i].list.length > 4)
					{
						str+='<div class="table-scroll">';	
					}
					
						str+='<table class="table table-condensed table_custom" id="dataTablelevelWiseBlock'+i+'">';
							str+='<thead>';
								str+='<tr>';
									str+='<th>Party</th>';
									str+='<th class="text-center">Seats(P)</th>';
									str+='<th class="text-center">Won</th>';
									str+='<th class="text-center">Voting%</th>';
								str+='</tr>';
							str+='</thead>';
							str+='<tbody>';
								for(var j in result[i].list){
									str+='<tr>';
										if(result[i].list[j].partyName == "TDP" || result[i].list[j].partyName == "YSRC"){
											str+='<td><p><img class="" src="images/party_flags/'+result[i].list[j].partyName+'.PNG" alt="'+result[i].list[j].partyName+'"></img>  '+result[i].list[j].partyName+'</p></td>';
										}else{
											str+='<td><p><img class="" src="images/party_flags/'+result[i].list[j].partyName+'.png" alt="'+result[i].list[j].partyName+'"></img>   '+result[i].list[j].partyName+'</p></td>';
										}
										str+='<td class="text-center">'+result[i].list[j].participatedSeatsCount+'</td>';
										str+='<td class="text-center">'+result[i].list[j].wonSeatsCount+'</td>';
										str+='<td class="text-center">'+result[i].list[j].perc+' %</td>';
									str+='</tr>';
								}
							str+='</tbody>';
						str+='</table>';
					if(result[i].list.length > 6)
					{
						str+='</div>';
					}
					
				str+='</div>';
			
			str+='</li>';
		}
		str+='</ul>';
		
		$("#levelWiseCandidatesResultsDivId").html(str);
		$(".table-scroll").mCustomScrollbar({setHeight:'254px'});
		for(var i in result){
			$("#dataTablelevelWiseBlock"+i).dataTable({
				"paging":   false,
				"info":     false,
				"searching": false,
				"autoWidth": true,
				"sDom": '<"top"iflp>rt<"bottom"><"clear">'
			});
		}
		$('.levelWiseCandiRstsCls').slick({
				slide: 'li',
				slidesToShow: 3,
				slidesToScroll: 1,
				infinite: false,
				swipe:false,
				touchMove:false,
				variableWidth: false,
				responsive: [
					{
						breakpoint: 1024,
						settings: {
							slidesToShow: 3,
							slidesToScroll: 3,
						}
					},
					{
						breakpoint: 600,
						settings: {
							slidesToShow: 2,
							slidesToScroll: 2
						}
					},
					{
						breakpoint: 480,
						settings: {
							slidesToShow: 1,
							slidesToScroll: 1
						}
					}
				]
			});
		
	}
}
function getElectionDetailsData(electionYrVal,eletionSubType,partyId,electionScopeId){
	$("#locationWiseCandidatesResultsDivId").html(spinner);
	
	var partyIdsArr=[];
	var electionScopeIdsArr=[];
	
	if(partyId == 0){
		partyIdsArr = [872,362,1117,886,72,269,265,163];
	}else{
		partyIdsArr = partyId
	}
	
	if(electionScopeId == 0){
		 electionScopeIdsArr = [];
		 $(".electionTypeWiseCls").each(function(){
			if ($(this).is(':checked')){
				 electionScopeIdsArr.push(parseInt($(this).val()))
			}
		});
	}else{
		electionScopeIdsArr = electionScopeId
	}
	
	var jsObj={
		locationTypeId     	:locationLevelId,
		locationValuesArr   :userAccessLevelValuesArray,
		electionId       	:258,
		electionYearArr     :electionYrVal,
		partyIdsArr       	:partyIdsArr,
		electionSubTypeArr  :eletionSubType,
		electionScopeIdsArr:electionScopeIdsArr,
    }
    $.ajax({
      type : "GET",
      url : "getElectionDetailsDataAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildElectionDetailsData(result);
		}
	});
	
	function buildElectionDetailsData(result){
		
		var str='';
		str+='<div class="block boderBlock">';
			if(locationLevelId == '2')
			{
				str+='<h4 class="theme-title-color">District Level Party Election Results</h4>';
			}else if(locationLevelId == '3')
			{
				str+='<h4 class="theme-title-color">District Level Party Election Results</h4>';		
			}else if(locationLevelId == '10')
			{
				str+='<h4 class="theme-title-color">Parliament Level Party Election Results</h4>';
			}else if(locationLevelId == '4')
			{
				str+='<h4 class="theme-title-color">Constituency Level Party Election Results</h4>';
			}else if(locationLevelId == '5')
			{
				str+='<h4 class="theme-title-color">Mandal Level Party Election Results</h4>';
			}else if(locationLevelId == '6')
			{
				str+='<h4 class="theme-title-color">Panchayat Level Party Election Results</h4>';
			}else
			{
				str+='<h4 class="theme-title-color">Location Level Party Election Results</h4>';
			}
			
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table table-condensed table-bordered table-striped" id="dataTableElecTypeRsts">';
					str+='<thead class="">';
						str+='<tr>';
							if(locationLevelId == '2')
							{
								str+='<th rowspan="2">District Name</th>';
							}else if(locationLevelId == '3')
							{
								str+='<th rowspan="2">District Name</th>';	
							}else if(locationLevelId == '10')
							{
								str+='<th rowspan="2">Parliament Name</th>';
							}else if(locationLevelId == '4')
							{
								str+='<th rowspan="2">Constituency Name</th>';
							}else if(locationLevelId == '5')
							{
								str+='<th rowspan="2">Mandal Name</th>';
							}else if(locationLevelId == '6')
							{
								str+='<th rowspan="2">Panchayat Name</th>';
							}else if(locationLevelId == '7')
							{
								str+='<th rowspan="2">Location Name</th>';
							}else
							{
								str+='<th rowspan="2">Location Name</th>';
							}
							
							if(result[0].subList1 !=null && result[0].subList1.length>0){
								for(var i in result[0].subList1){
									var length = result[0].subList1[i].subList1.length+result[0].subList1[i].subList1.length;
									str+='<th colspan="'+length+'" class="text-center">'+result[0].subList1[i].electionYear+' ('+result[0].subList1[i].electionType+')</th>';
									
								}
							}
								
						str+='</tr>';
						str+='<tr>';
							if(result[0].subList1 !=null && result[0].subList1.length>0){
								for(var i in result[0].subList1){
									if(result[0].subList1[i].subList1 !=null && result[0].subList1[i].subList1.length>0){
										for(var j in result[0].subList1[i].subList1){
											if(result[0].subList1[i].subList1[j].partyName == "TDP" || result[0].subList1[i].subList1[j].partyName == "YSRC"){
												str+='<th class="text-center"><img class="" src="images/party_flags/'+result[0].subList1[i].subList1[j].partyName+'.PNG" alt="'+result[0].subList1[i].subList1[j].partyName+'"></img></th>';
											}else{
												str+='<th class="text-center"><img class="" src="images/party_flags/'+result[0].subList1[i].subList1[j].partyName+'.png" alt="'+result[0].subList1[i].subList1[j].partyName+'"></img></th>';
											}
											str+='<th class="text-center">%</th>';
										}
									}
								}
							}
						str+='</tr>';
					str+='</thead>';
					str+='<tbody>';
						for(var i in result){
							str+='<tr>';
								str+='<td>'+result[i].locationName+'</td>';
								for(var j in result[i].subList1){
									if(result[i].subList1[j].subList1 !=null && result[i].subList1[j].subList1.length>0){
										for(var k in result[i].subList1[j].subList1){
											if(result[i].subList1[j].subList1[k].earnedVote !=null && result[i].subList1[j].subList1[k].earnedVote>0){
												str+='<td>'+result[i].subList1[j].subList1[k].earnedVote+'</td>';
											}else{
												str+='<td> - </td>';
											}
											if(result[i].subList1[j].subList1[k].perc !=null && result[i].subList1[j].subList1[k].perc>0){
												str+='<td>'+result[i].subList1[j].subList1[k].perc+'</td>';
											}else{
												str+='<td> - </td>';
											}
											
										}
									}
								}
								
							str+='</tr>';
						}
					str+='</tbody>';
			str+='</div>';
		str+='</div>';
		
		$("#locationWiseCandidatesResultsDivId").html(str);
		$("#dataTableElecTypeRsts").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
		});
	}
}
function getLocationWiseCrossVotingDetails(electionYrVal,parliamentId,assemblyId,partyId,eletionSubType,electionScopeId){
	$("#crossVotingDetailsBlockId").html(spinner);
	
	var assemblyIdsArr=[];
	var partyIdsArr=[];
	var parliamentIdsArr=[];
	var electionScopeIdsArr=[];
	
	if(assemblyId == 0){
		assemblyIdsArr=[]
	}else{
		assemblyIdsArr.push(assemblyId)
	}
	
	if(partyId == 0){
		partyIdsArr=[872,362,1117,886,72,269,265,163]
	}else{
		partyIdsArr = partyId
	}
	if(parliamentId == 0){
		parliamentIdsArr = globalParliamentIds;
	}else{
		parliamentIdsArr.push(parliamentId)
	}
	
	if(electionScopeId == 0){
		 electionScopeIdsArr = [];
		 $(".electionTypeWiseCls").each(function(){
			if ($(this).is(':checked')){
				 electionScopeIdsArr.push(parseInt($(this).val()))
			}
		});
	}else{
		electionScopeIdsArr = electionScopeId
	}
	
	jsObj={
		electionYearArr		:electionYrVal,
		parliamentIdsArr	:parliamentIdsArr,
		assemblyIdsArr 		:assemblyIdsArr,
		partyIdsArr 		:partyIdsArr,
		locationValue		:userAccessLevelValuesArray,
		withAlliance		:"YES",
		subTypesArr			:eletionSubType,
		locationLevelId		:locationLevelId,
		electionScopeIdsArr:electionScopeIdsArr
	}
	$.ajax({
		type : "GET",
		url : "getLocationWiseCrossVotingDetailsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.subList1 !=null && result.subList1.length>0){
			buildTableData(result);
		}else{
			$("#crossVotingDetailsBlockId").html("No Data Available");
		}	
		
	});
	function buildTableData(result)
	{
		var table='';
		table+='<div class="table-responsive">';
		table+='<table class="table table-cross-voting">';
			table+='<thead>';
			table+='<tr>';
				table+='<th>Election&nbsp;Year</th>';
				table+='<th>Assembly&nbsp;Polled</th>';
				table+='<th>Parliament&nbsp;Polled</th>';
				table+='<th>Cross&nbsp;Voting</th>';
				table+='</tr>';
			table+='</thead>';
			for(var i in result.subList1){
				table+='<tr>';
					table+='<td>'+result.subList1[i].electionYear+'</td>';
					table+='<td>'+result.subList1[i].assemblyValidVoters+'</td>';
					table+='<td>'+result.subList1[i].parliamentValidVoters+'</td>';
						for(var j in result.subList1[i].list){
							if(result.subList1[i].list[j].perc != null ){
								table+='<td>';
									table+='<ul class="cross-voting-list">';
									table+='<li >';
										table+='<div class="media">';
											table+='<div class="media-left" style="background-color:'+globalCrossVotionBgColor[result.subList1[i].list[j].partyName.trim()]+'">';
												table+='<img class="" src="images/party_flags/'+result.subList1[i].list[j].partyFlag+'" alt="'+result.subList1[i].list[j].partyFlag+'"></img>';
												table+='<span>'+result.subList1[i].list[j].partyName+'</span>';
											table+='</div>';
											table+='<div class="media-body">';
											if(parseFloat(result.subList1[i].list[j].perc)>0){
												table+='<p>'+parseFloat(result.subList1[i].list[j].perc).toFixed(2)+'</p>';
												table+='<p class="pull-right"><i class="fa fa-arrow-up" style="color:#3BB878 !important;"></i> <i class="fa fa-info-circle"></i></p>';
											}else{
												table+='<p>'+parseFloat(result.subList1[i].list[j].perc).toFixed(2)+'</p>';
												table+='<p class="pull-right"><i class="fa fa-arrow-down" style="color:#F56666 !important;"></i> <i class="fa fa-info-circle"></i></p>';
											}
												
											table+='</div>';
										table+='</div>';
									table+='</li>';
									table+='</ul>';
									table+='</td>';
							}
							
						}
				table+='</tr>';
			}
			
		table+='</table>';
		table+='</div>';
		
		$("#crossVotingDetailsBlockId").html(table);
	}	
}

function boothWiseResults(constituencyId,partiesArr,electionYrVal,electionScopeId){
	
	$("#boothWiseResultsBlockId").html(spinner);
	$("#locationWiseBoothResultsId").html(spinner);
	var partyIdsArr=[];
	var electionScopeIdsArr=[];
	
	if(partyId == 0){
		partyIdsArr=[872,362,1117,886,72,269,265,163]
	}else{
		partyIdsArr = partiesArr;
	}
	
	jsObj={
		constituencyId		:constituencyId,
		partyList			:partyIdsArr,
		electionyears 		:parseInt(electionYrVal),
		electionScopeId		:electionScopeId,
		task				:"assemblyWiseResults",
		locationTypeId 		: locationLevelId,
		locationValue		:userAccessLevelValue
	}
	$.ajax({
		type : "GET",
		url : "locationBasedBoothWiseResultAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){ 
		if(result !=null){
			return buildData(result);
		}else{
			$("#locationWiseBoothResultsId").html('');
			$("#boothWiseResultsBlockId").html("No Data Available");
		}
		
	});
}

function buildData(result)
	{
		
		var table = '';
		mainArr = [];
		boothWiseDetailsArr = [];
		mainArr.push(result);
		var isWonCandidateAvailable = false;
		var remainingVotes = 0;
		var remainingPercentage = 0.00;
		var totalVotes = result.partyBoothPerformanceVOList[0].totalValidVotes;
		
		table+='<div class="row m_top20">';
			table+='<div>';
			table+='<div class="col-sm-3">';
				table+='<h4 class="panel-title text-capitalize">Assembly Results</h4>';
				table+='<div id="boothWiseRstsGraphId" style="height:200px;"></div>';
			table+='</div>';
			table+='<div class="col-sm-9">';
				table+='<h4 class="panel-title text-capitalize">Candidate Details</h4>';
				table+='<div class="table-responsive m_top10">';
					table+='<table class="table tableBoothWise table-bordered">';
						table+='<tr>';
						table+='<thead class="bg-E9">';
							table+='<th>Candidate Name</th>';
							table+='<th>Party Name</th>';
							table+='<th>Total Votes Gained</th>';
							table+='<th>%</th>';
							table+='<th>Margin Votes</th>';
						table+='</tr>';	
						table+='</thead>';
						table +='<tbody>';
						for(var k in result.partyBoothPerformanceVOList)
						{
							if(result.partyBoothPerformanceVOList[k].rank ==1)
							{
								isWonCandidateAvailable = true;
								table +='<tr>';
									table +='<td>'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
									table +='<td>'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
									table +='<td >'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
									table +='<td >'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
									table +='<td >'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
								table +='</tr>';
								remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
								remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
							}
							else
							{
								table +='<tr>';
									table +='<td>'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
									table +='<td>'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
									table +='<td>'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
									table +='<td>'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
									table +='<td>'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
								table +='</tr>';
								remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
								remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
							}
																	
						}		
						if(!isWonCandidateAvailable)
							{
								table +='<tr>';
									table +='<td>'+result.partyBoothPerformanceVOList[0].wonCandidate[0].candidateName+'</td>';
									table +='<td>'+result.partyBoothPerformanceVOList[0].wonCandidate[0].partyName+'</td>';
									table +='<td >'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned+'</td>';						
									table +='<td >'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesPercengate+'  </td>';
									table +='<td >'+result.partyBoothPerformanceVOList[0].wonCandidate[0].marginVotes+' ( Rank - '+result.partyBoothPerformanceVOList[0].wonCandidate[0].rank+') </td>';
								table +='</tr>';
								remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned);
								remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);

							}
						
								table +='<tr>';
									table +='<td> Others </td>';
									table +='<td> --  </td>';
									table +='<td> '+(totalVotes - remainingVotes)+' </td>';						
									table +='<td> '+(parseFloat(100.00) - remainingPercentage).toFixed(2)+' </td>';
									table +='<td> --  </td>';
								table +='</tr>';
							
					table +='</tbody>';
					table+='</table>';
				table+='</div>';
			table+='</div>';
			table+='</div>';
		table+='</div>';
		table+='<div class="row">';
			table+='<div class="col-sm-12">';
				table+='<h3 class="panel-title text-capitalize">Polling Percentage Range Wise Party Voters Percentage</h3>';
				var partiesSize=0;
				table+='<div class="table-responsive m_top10">';
					table+='<table class="table tableBoothWise table-bordered" id="pollingPercTableId">';
						table+='<thead class="bg-E9">';
							table+='<tr>';				
								table+='<th rowspan="2"> Polling % Range </th>';
								table+='<th rowspan="2"> Total No of Booths </th>';
								if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
								{
								partiesSize =  result.partyBoothPerformanceVOList.length;
									for(var j in result.partyBoothPerformanceVOList)
									{
										table+='<th colspan="2" style="text-align:center;"> '+result.partyBoothPerformanceVOList[j].partyName+' </th>';
									}
								}
								table+='<th colspan="2" style="text-align:center"> Others </th>';
							table+='</tr>';

							table+='<tr>';
									for(var j in result.partyBoothPerformanceVOList)
									{						
										table+='<th> Party Votes % </th>';
										table+='<th> Won Booths  </th>';					
									}	
								table+='<th> Party Votes % </th>';
								table+='<th> Won Booths  </th>';						
							table+='</tr>';
						table+='</thead>';
						table+='<tbody>';
						for(var k in result.partyPerWiseboothResults)
						{
							var totalBooths = result.partyPerWiseboothResults[k].boothResultVOList[0].votesEarned;
							var remainingBooths = 0;
							var percentage = 0.00;
							
								table+='<tr>';
									table+='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].location+'</td>';
									table+='<td  style="text-align:center">'+totalBooths+'</td>';
									if(result.partyPerWiseboothResults[k].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList.length >0 )
									{
										for(var p = 0; p<partiesSize ; p++)
										{
											if(result.partyPerWiseboothResults[k].boothResultVOList[p].percentage != null)
											{
												table+='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[p].percentage+'</td> ';
												if( (result.partyPerWiseboothResults[k].boothResultVOList[p].percentage).trim() != '--')
												{
													percentage = parseFloat(percentage) + parseFloat(result.partyPerWiseboothResults[k].boothResultVOList[p].percentage);
												}
											}
											else
											{
												table+='<td  style="text-align:center"> -- </td> ';
											}
											if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList != null && result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList.length >0)
											{
												var isBuild = true;
												for(var r in result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList)
												{
													if(result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].wonParty == result.partyPerWiseboothResults[k].boothResultVOList[p].message)
													{
														isBuild = false;
														table+='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[p].boothResultVOList[r].resultState+'</td>';
													}
												}
												if(isBuild)
												{
													table+='<td  style="text-align:center"> -- </td>';
												}										
											}
											else
											{
												table+='<td  style="text-align:center"> -- </td>';
											}
										}
							
									}
									if(percentage == 0.00)
										table+='<td style="text-align:center"> -- </td>';
									else
										table+='<td style="text-align:center"> '+(parseFloat(100.00) - parseFloat(percentage)).toFixed(2)+' </td>';
									
									if(result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1 != null && result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1.length >0)
									{
										table+='<td  style="text-align:center">'+result.partyPerWiseboothResults[k].boothResultVOList[0].boothResultVOList1.length+'</td>';
									}
									else
										table+='<td  style="text-align:center"> -- </td>';
								table+='</tr>';						
						}
						table+='</tbody>';
					table+='</table>';
				table+='</div>';
			table+='</div>';
		table+='</div>';
		table+='<div class="row">';
			table+='<div class="col-sm-12">';
				table +='<h4 class="panel-title m_top20 text-capitalize">Party Votes Percentage Range Wise Polling Percentage</h4>';
				var partiesSize = 0;
				if(result.perWiseboothResults != null && result.perWiseboothResults.length >0)
				{
					table +='<div class="table-responsive m_top20">';
						table +='<table class="table table-bordered m_top20 tableBoothWise" id="majarityCandidatesTab">';	
							table +='<thead class="bg-E9">';
								table +='<tr>';				
									table +='<th rowspan="2"> Party Votes % Range </th>';
									if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
									{
										partiesSize =  result.partyBoothPerformanceVOList.length;
										for(var j in result.partyBoothPerformanceVOList)
										{
											table +='<th colspan="2" style="text-align:center"> '+result.partyBoothPerformanceVOList[j].partyName+' </th>';
										}
									}
								table +='</tr>';
								table +='<tr>';
									for(var p=0; p<partiesSize ; p++)
									{
										table +='<th> Total No of Booths </th>';
										table +='<th> Polling % </th>';	
									}								
								table +='</tr>';
							table +='</thead>';	
							table +='<tbody>';
							for(var k in result.perWiseboothResults)
							{
								table +='<tr>';
									table +='<td  style="text-align:center">'+result.perWiseboothResults[k].location+'</td>';
									
									if(result.perWiseboothResults[k].boothResultVOList != null && result.perWiseboothResults[k].boothResultVOList.length >0 )
									{
										for(var p=0; p<partiesSize ; p++)
										{
											if(result.perWiseboothResults[k].boothResultVOList[p].votesEarned != null)
												table +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].votesEarned+'</td>';
											else
												table+='<td  style="text-align:center"> -- </td>';
											if(result.perWiseboothResults[k].boothResultVOList[p].percentage != null)
												table +='<td  style="text-align:center">'+result.perWiseboothResults[k].boothResultVOList[p].percentage+' </td>';
											else
												table+='<td  style="text-align:center"> -- </td>';
										}
									}
								table +='</tr>';						
							}
							table +='</tbody>';
						table +='</table>';
					table +='</div>';
				}
			table+='</div>';
		table+='</div>';
	
		var str1='';
		var str='';
		str+='<div class="row">';
		str+='<div class="col-sm-12">';
		str +='<h4 class="panel-title m_top10 text-capitalize">Booth Wise Performance</h4>';
		if(result.boothResults != null && result.boothResults.length >0)
		{
			str+='<div class="row m_top10">';
			str+='<div class="col-sm-12">';
			str+='  <label class="checkbox-inline"> <input type="checkbox"  id="locationId" onclick="hideAndShow();"><h6 class="m_top5">Show Location</h6></label>';
			str+='  <label class="checkbox-inline"> <input type="checkbox" id="villageId" onclick="hideAndShow();"><h6 class="m_top5">Show Villages</h6></label>';
			str+='  <label class="checkbox-inline"> <input type="checkbox"  id="mandalId" onclick="hideAndShow();"><h6 class="m_top5">Show Mandal</h6></label>';		
			str+='</div>';
			str+='</div>';
			
			str1+='<div class="table-responsive m_top20">';	
			str1 +='<table class="table table-bordered tableBoothWise" id="boothWiseResultsPage">';	
			str1 +='<thead class="bg-E9">';
				str1 +='<tr>';				
					str1 +='<th>S.No </th>';
					str1 +='<th>Booth No</th>';
					str1 +='<th>Total Votes</th>';
					//str1 +='<th> Location  </th>';
					//str1 +='<th> Village Covered  </th>';
					//str1 +='<th> Mandal </th>';
					str1 +='<th >   Polled Votes </th>';	
					str1 +='<th > Polling %   </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
						partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polled Votes </th>';
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polling % </th>';
						}
					}
					str1 +='<th >  Others Polled Votes </th>';	
					str1 +='<th > Others Polling %   </th>';
					//str1 +='<th > Won Party   </th>';
									
				str1 +='</tr>';

				str1 +='</thead>';	
			str1 +='<tbody>';	
			for(var k in result.boothResults)
			{
				var votersCount = result.boothResults[k].boothResultVOList[0].totalVoters;
				var percentage = 0.00;
					str1 +='<tr>';
					str1 +='<td>'+(parseInt(k)+parseInt(1))+'</td>';
					str1 +='<td> Booth-'+result.boothResults[k].partNo+'</td>';
						if(result.boothResults[k].boothResultVOList != null && result.boothResults[k].boothResultVOList.length>0)
						{
							
							
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[0].location+'</td>';
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[0].villagesCovered+'</td>';
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[0].mandal+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalBoothVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].pollingPercentage+'</td>';
							
							for(var m in result.boothResults[k].boothResultVOList)
							{
								votersCount = parseInt(votersCount) - parseInt(result.boothResults[k].boothResultVOList[m].votesEarned);
								percentage = parseFloat(percentage) + parseFloat(parseInt(result.boothResults[k].boothResultVOList[m].percentage));
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].votesEarned+'</td>';
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].percentage+'</td>';
							}
						}
						
						str1 +='<td>'+votersCount+'</td>';
						str1 +='<td>'+(parseFloat(100.00) - parseFloat(percentage) ).toFixed(2)+'</td>';
							//str1 +='<td>'+result.boothResults[k].boothResultVOList[m].wonParty+'</td>';	
					str1 +='</tr>';						
			}
			
			str1 +='</tbody>';
			str1 +='</table>';
			str1 +='</div>';
		}
		str+='</div>';
		str+='</div>';
		
		$("#boothWiseResultsBlockId").html(table);
		$("#locationWiseBoothResultsId").html(str1);
		$("#hideShowlocationId").html(str);
		$("#pollingPercTableId,#majarityCandidatesTab,#boothWiseResultsPage").dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15,20,50, -1], [10, 15, 20,50, "All"]]
		});
		
		var dataArr=[];
		var polledPerc=0;
		dataArr.push(result.partyBoothPerformanceVOList[0].totalVotes)
		dataArr.push(result.partyBoothPerformanceVOList[0].totalValidVotes)
		polledPerc = result.partyBoothPerformanceVOList[0].votingPercentage;
		
		$("#boothWiseRstsGraphId").highcharts({
			colors:["#339900","#3BB878"],
			chart: {
				type: 'column'
			},
			title: {
				text: '',
				style: {
				 color: '#000',
				 font: 'bold 13px "Lato", sans-serif'
			  }
			},
			subtitle: {
				text: ''
			},
			xAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,	
				type: 'category',
				categories: ['Voters','Polled'],
				
			},
			yAxis: {
				min: 0,
				gridLineWidth: 0,
				minorGridLineWidth: 0,
				title: {
					text: null
				}
			},
			legend: {
				enabled: false
			},
			tooltip: {
				useHTML:true,	
				formatter: function () {
					if(this.x !="Voters"){
						var pcnt = polledPerc;
						return '<b>' + this.x + '</b><br/>' +
						this.y+"-"+((Highcharts.numberFormat(pcnt)))+'%';
					}else{
						return '<b>' + this.x + '</b><br/>' +
						this.y+'';
					}
					
				}
			},
			plotOptions: { 
				column: {
					pointWidth: 30,
					gridLineWidth: 25,
					colorByPoint: true
				}
			},
			series: [{
				name: 'Count',
				data: dataArr,
				dataLabels: {
					enabled: true,
					color: '#000',
					align: 'center',
					formatter: function() {
						if(this.x !="Voters"){
							var pcnt = polledPerc;
							return '<span>'+this.y+'<br>('+Highcharts.numberFormat(pcnt)+'%)</span>';
						}else{
							var pcnt = polledPerc;
							return '<span>'+this.y+'</span>';
						}
						
					}
				}
			}]
		});
	}	


function hideAndShow()
{
	var isVillage = $('#villageId').is(":checked");
	var isMandal = $('#mandalId').is(":checked");
	var isLocation = $('#locationId').is(":checked");
	$('#locationWiseBoothResultsId').html('');
	buildSelectionWiseTable(isVillage,isMandal,isLocation);

}
function buildSelectionWiseTable(isVillage,isMandal,isLocation)
{

var result = mainArr[0];
	var str1 = '';
			str1 +='<div class="table-responsive">';
			str1 +='<table class="table table-bordered m_top10 tableBoothWise" id="boothWiseResultsLocPage">';	
			str1 +='<thead class="bg-E9">';
				str1 +='<tr>';				
					str1 +='<th>S.No  </th>';
					str1 +='<th>Booth No  </th>';
					if(isLocation)
						str1 +='<th>Location  </th>';
					if(isVillage)
						str1 +='<th>Village Covered  </th>';
					if(isMandal)
						str1 +='<th>Mandal </th>';
					
					str1 +='<th >Total Votes </th>';	
					str1 +='<th >Polled Votes </th>';	
					str1 +='<th >Polling %   </th>';
					if(result.partyBoothPerformanceVOList != null && result.partyBoothPerformanceVOList.length >0)
					{
						partiesSize =  result.partyBoothPerformanceVOList.length;
						for(var j in result.partyBoothPerformanceVOList)
						{
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polled Votes </th>';
							str1 +='<th> '+result.partyBoothPerformanceVOList[j].partyName+' Polling % </th>';
						}
					}
					str1 +='<th >  Others Polled Votes </th>';	
					str1 +='<th > Others Polling %   </th>';
					//str1 +='<th > Won Party   </th>';
									
				str1 +='</tr>';
	
			str1 +='</thead>';	
			str1 +='<tbody>';	
			for(var k in result.boothResults)
			{
				var votersCount = result.boothResults[k].boothResultVOList[0].totalVoters;
				var percentage = 0.00;
					str1 +='<tr>';
					str1 +='<td>'+(parseInt(k)+parseInt(1))+'</td>';
					str1 +='<td> Booth-'+result.boothResults[k].partNo+'</td>';
						if(result.boothResults[k].boothResultVOList != null && result.boothResults[k].boothResultVOList.length>0)
						{
							if(isLocation)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].location+'</td>';
							if(isVillage)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].villagesCovered+'</td>';
							if(isMandal)
								str1 +='<td>'+result.boothResults[k].boothResultVOList[0].mandal+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalBoothVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].totalVoters+'</td>';
							str1 +='<td>'+result.boothResults[k].boothResultVOList[0].pollingPercentage+'</td>';
							
							for(var m in result.boothResults[k].boothResultVOList)
							{
								votersCount = parseInt(votersCount) - parseInt(result.boothResults[k].boothResultVOList[m].votesEarned);
								percentage = parseFloat(percentage) + parseFloat(parseInt(result.boothResults[k].boothResultVOList[m].percentage));
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].votesEarned+'</td>';
								str1 +='<td>'+result.boothResults[k].boothResultVOList[m].percentage+'</td>';
							}
						}
						
						str1 +='<td>'+votersCount+'</td>';
						str1 +='<td>'+(parseFloat(100.00) - parseFloat(percentage) ).toFixed(2)+'</td>';
								//str1 +='<td>'+result.boothResults[k].boothResultVOList[m].wonParty+'</td>';	
					str1 +='</tr>';						
			}
			
			str1 +='</tbody>';
			str1 +='</table>';
			str1 +='</div>';
		

		$('#locationWiseBoothResultsId').html(str1);
		$('#boothWiseResultsLocPage').dataTable({
			"iDisplayLength": 10,
			"aaSorting": [],
			"aLengthMenu": [[10, 15,20,50, -1], [10, 15, 20,50, "All"]]
		});
}
function getElectionInformationLocationWiseStatus(eletionSubType,electionYrVal,partyId,searchLevelVal,electionScopeId,partyName,electionTypetext,year){
	$("#locationWiseStrongVsPoor").html(spinner);
	
	var partyIdsList=[];
	var electionScopeIdsArr=[];
	
	if(partyId !=0){
		partyIdsList.push(partyId)
	}else{
		partyIdsList=[]
	}
	
	electionScopeIdsArr.push(electionScopeId);
	
	var jsObj={
	  	locationTypeId 		:locationLevelId,
		locationValue 		:userAccessLevelValue,
		electionScopeIds	:electionScopeIdsArr,
		partyIdsList  		:partyIdsList,
		electionYears     	:electionYrVal,
		electionSubTypeArr 	:eletionSubType,
		searchType			:searchLevelVal
    }
    $.ajax({
      type : "GET",
      url : "getElectionInformationLocationWiseStatusAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			 buildElectionInformationLocationWiseStatus(result,electionScopeId,searchLevelVal,partyName,electionTypetext,year);
		}else{
			$("#locationWiseStrongVsPoor").html("No Data Available");
		}
	});
}

function buildElectionInformationLocationWiseStatus(result,electionTypeVal,searchLevelVal,partyName,electionTypetext,year){
		
	var str='';
	if(locationLevelId == '2')
	{
		if(electionTypeVal == "2" && searchLevelVal == "constituency" || searchLevelVal == "parliament"){
			str+='<h4 class="text-capital m_top10">Constituency Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else if(electionTypeVal == "3" && searchLevelVal =="constituency"){
			str+='<h4 class="text-capital m_top10">Panchayat Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else if(electionTypeVal == "4" && searchLevelVal == "constituency"){
			str+='<h4 class="text-capital m_top10">Mandal Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else{
			str+='<h4 class="text-capital m_top10">District Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}
		
	}else if(locationLevelId == '3')
	{
		if(electionTypeVal == "3"){
			str+='<h4 class="text-capital m_top10">Mandal Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
		}else if(electionTypeVal == "4"){
			str+='<h4 class="text-capital m_top10"> Panchayat '+electionTypetext+' Level Election Comparision Results For '+partyName+' Party</h4>';
		}else{
			str+='<h4 class="text-capital m_top10">Constituency '+electionTypetext+' Level Election Comparision Results For '+partyName+' Party</h4>';
		}
		
	}else if(locationLevelId == '10')
	{
		str+='<h4 class="text-capital m_top10">Constituency Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
	}else if(locationLevelId == '4' || electionTypeVal == "3")
	{
		str+='<h4 class="text-capital m_top10">Mandal Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
	}else if(locationLevelId == '5' || electionTypeVal == "4")
	{
		str+='<h4 class="text-capital m_top10">panchayat Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
	}else if(locationLevelId == '6')
	{
		str+='<h4 class="text-capital m_top10">Panchayat Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
	}else if(locationLevelId == '7' || electionTypeVal == "5")
	{
		str+='<h4 class="text-capital m_top10">Municipality Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
	}else
	{
		str+='<h4 class="text-capital m_top10">Location Level '+electionTypetext+' Election Comparision Results For '+partyName+' Party</h4>';
	}
	
	str+='<div class="table-responsive m_top10">';
	
	str+='<table class="table table-condensed table-bordered" >';
	str+='<tr>';
	str+='<th style="background-color:#FF3E3E;">WORST %</th>';
	str+='<th style="background-color:#CC6600;">VERY POOR %</th>';
	str+='<th style="background-color:#FF9966;">POOR %</th>';
	str+='<th style="background-color:#FFCC00;">OK %</th>';
	str+='<th style="background-color:#428AE9;">GOOD %</th>';
	str+='<th style="background-color:#009900;">VERY GOOD %</th>';
	str+='<th style="background-color:#009999;">EXCELLENT %</th>';
	str+='</tr>';
	str+='<th> Below (-11) </th>';
	str+='<th> -10 to -6 </th>';
	str+='<th> -5 to 0 </th>';
	str+='<th> 0 to 5 </th>';
	str+='<th> 6 to 10 </th>';
	str+='<th> 11 to 20 </th>';
	str+='<th> 21 Above  </th>';
	
	str+='</tr>';
	str+='</table>';
	str+='</div>';
	
	str+='<div class="table-responsive m_top10">';
		str+='<table class="table table-election" id="electionResults">';
			str+='<thead>';
				str+='<tr>';
					if(locationLevelId == '2')
					{
						if(electionTypeVal == "2" && searchLevelVal == "constituency" || searchLevelVal == "parliament"){
							str+='<th>Constituency Name</th>';
						}else if(electionTypeVal == "3" && searchLevelVal =="constituency"){
							str+='<th>Panchayat Name</th>';
						}else if(electionTypeVal == "4" && searchLevelVal == "constituency"){
							str+='<th>Mandal Name</th>';
						}else{
							str+='<th>District Name</th>';
						}
						
					}else if(locationLevelId == '3')
					{
						if(electionTypeVal == "3"){
							str+='<th>Mandal Name</th>';
						}else if(electionTypeVal == "4"){
							str+='<th>Panchayat Name</th>';
						}else{
							str+='<th>Constituency Name</th>';
						}
						
					}else if(locationLevelId == '10')
					{
						str+='<th>Constituency Name</th>';
					}else if(locationLevelId == '4' || electionTypeVal == "3")
					{
						str+='<th>Mandal Name</th>';
					}else if(locationLevelId == '5' || electionTypeVal == "4")
					{
						str+='<th>Panchayat Name</th>';
					}else if(locationLevelId == '6')
					{
						str+='<th>Panchayat Name</th>';
					}else if(locationLevelId == '7' || electionTypeVal == "5")
					{
						str+='<th>Municipality Name</th>';
					}else
					{
						str+='<th>Location Name</th>';
					}
					
					if(result[0].list !=null && result[0].list.length>0){
						for(var j in result[0].list){
							
							if(year == 0){
								str+='<th style="min-width:210px;">Election Year<h3>'+result[0].list[j].electionYear+'</h3>';
									str+='<div class="row">';
										for(var k in result[0].list[j].subList1){
											str+='<div class="col-sm-1" style="margin-left:5px">';
											if(result[0].list[j].subList1[k].wonSeatsCount !=null && result[0].list[j].subList1[k].wonSeatsCount>0){
												str+='<span class="statusClr statusClickCls" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;"  status_attr="'+result[0].list[j].subList1[k].status+'" year_attr="'+result[0].list[j].electionYear+'" title="click here to view  '+result[0].list[j].subList1[k].status+' locations details" locationids_attr="'+result[0].list[j].subList1[k].idsList+'">'+result[0].list[j].subList1[k].wonSeatsCount+'</span>';
											}else{
												str+='<span class="statusClr" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;text-decoration:none;cursor: auto;">0</span>';
											}
												
											str+='</div>';
										}
										
										
									str+='</div>';
								str+='</th>';
							}else if(result[0].list[j].electionYear==year){
								str+='<th style="min-width:210px;background-color:#ccc;">Election Year<h3>'+result[0].list[j].electionYear+'</h3>';
									str+='<div class="row">';
										for(var k in result[0].list[j].subList1){
											str+='<div class="col-sm-1" style="margin-left:5px">';
											if(result[0].list[j].subList1[k].wonSeatsCount !=null && result[0].list[j].subList1[k].wonSeatsCount>0){
												str+='<span class="statusClr statusClickCls" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;"  status_attr="'+result[0].list[j].subList1[k].status+'" year_attr="'+result[0].list[j].electionYear+'" title="click here to view  '+result[0].list[j].subList1[k].status+' locations details"  locationids_attr="'+result[0].list[j].subList1[k].idsList+'">'+result[0].list[j].subList1[k].wonSeatsCount+'</span>';
											}else{
												str+='<span class="statusClr" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;text-decoration:none;cursor: auto;">0</span>';
											}
												
											str+='</div>';
										}
										
										
									str+='</div>';
								str+='</th>';
							}else{
								str+='<th style="min-width:210px;">Election Year<h3>'+result[0].list[j].electionYear+'</h3>';
									str+='<div class="row">';
										for(var k in result[0].list[j].subList1){
											str+='<div class="col-sm-1" style="margin-left:5px">';
											if(result[0].list[j].subList1[k].wonSeatsCount !=null && result[0].list[j].subList1[k].wonSeatsCount>0){
												str+='<span class="statusClr statusClickCls" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;"  status_attr="'+result[0].list[j].subList1[k].status+'" year_attr="'+result[0].list[j].electionYear+'" title="click here to view  '+result[0].list[j].subList1[k].status+' locations details"  locationids_attr="'+result[0].list[j].subList1[k].idsList+'">'+result[0].list[j].subList1[k].wonSeatsCount+'</span>';
											}else{
												str+='<span class="statusClr" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;text-decoration:none;cursor: auto;">0</span>';
											}
												
											str+='</div>';
										}
										
										
									str+='</div>';
								str+='</th>';
							}		
							
						}
					}
				str+='</tr>';
			str+='</thead>';
			str+='<tbody>';
			for(var i in result){
				str+='<tr>';
					str+='<td>'+result[i].locationName+'</td>';
					for(var j in result[i].list){							
						if(result[i].list[j].status == 'GOOD')
						{
							str+='<td style="background-color:#428AE9;color:#fff">';
							str+='<h5 ><b>'+result[i].list[j].status+'</b></h5>';
								str+='<h6><b>Margin  Votes: '+result[i].list[j].marginVotes+' ('+result[i].list[j].perc+'%)</b></h6>';
						}
						else if(result[i].list[j].status == 'EXCELLENT')
						{
							str+='<td style="background-color:#009999;color:#fff">';
							str+='<h5 ><b>'+result[i].list[j].status+'</b></h5>';
								str+='<h6><b>Margin Votes: '+result[i].list[j].marginVotes+' ('+result[i].list[j].perc+'%)</b></h6>';
						}else if(result[i].list[j].status == 'VERY GOOD')
						{
							str+='<td style="background-color:#009900;color:#fff">';
							str+='<h5 ><b>'+result[i].list[j].status+'</b></h5>';
								str+='<h6><b>Margin Votes: '+result[i].list[j].marginVotes+' ('+result[i].list[j].perc+'%)</b></h6>';
						}else if(result[i].list[j].status == 'OK')
						{
							str+='<td style="background-color:#FFCC00;color:#fff">';
							str+='<h5 ><b>'+result[i].list[j].status+'</b></h5>';
								str+='<h6><b>Margin Votes: '+result[i].list[j].marginVotes+' ('+result[i].list[j].perc+'%)</b></h6>';
						}else if(result[i].list[j].status == 'POOR')
						{
							str+='<td style="background-color:#FF9966;color:#fff">';
							str+='<h5 ><b>'+result[i].list[j].status+'</b></h5>';
								str+='<h6><b>Margin Votes: '+result[i].list[j].marginVotes+' ('+result[i].list[j].perc+'%)</b></h6>';
						}else if(result[i].list[j].status == 'VERY POOR')
						{
							str+='<td style="background-color:#CC6600;color:#fff">';
							str+='<h5 ><b>'+result[i].list[j].status+'</b></h5>';
								str+='<h6><b>Margin Votes: '+result[i].list[j].marginVotes+' ('+result[i].list[j].perc+'%)</b></h6>';
						}else if(result[i].list[j].status == 'WORST')
						{
							str+='<td style="background-color:#FF3E3E;color:#fff">';
							str+='<h5 ><b>'+result[i].list[j].status+'</b></h5>';
								str+='<h6><b>Margin Votes: '+result[i].list[j].marginVotes+' ('+result[i].list[j].perc+'%)</b></h6>';
						}else  if(result[i].list[j].status == null || result[i].list[j].status.length == 0 ){
							str+='<td class="text-center"  style="background-color:grey;" >';
							str+='<h5 title="Removed after delimitation in 2009 "> NOT AVAILABLE </h5>';
						}else {
							str+='<td class="text-center" >';
							str+='<h5 >'+result[i].list[j].status+'</h5>';								
						}	
							str+='</td>';
					}
				str+='</tr>';
			}
			str+='</tbody>';
		str+='</table>';
	str+='</div>';
	
	
	
	
	
	$("#locationWiseStrongVsPoor").html(str);
	$("#electionResults").dataTable({
		"iDisplayLength": 15,
		"aaSorting": [],
		"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
	});
}
$(document).on("click",".statusClickCls",function(){
	
	var statusTpe=$(this).attr("status_attr");
	var year=$(this).attr("year_attr");
	var searchLevelVal = $("#searchLevelId").val();
	var electionTypeVal = $("#elctionTypeValId").val();
	var partyId = $("#partyIdForStrongBlock").val();
	var partyName = $("#partyIdForStrongBlock option:selected").text();
	var electionTypetext = $("#elctionTypeValId option:selected").text();
	var eletionSubType=[];
	 $('.electionSubTypeCls').each(function(){
		if ($(this).is(':checked')){
			eletionSubType.push($(this).val());
		}
	 });
	var	electionYrVal=[];
	electionYrVal = $("#electionYearId").val();		
	var loctionIds='';
	if($(this).attr("locationids_attr") !=null && $(this).attr("locationids_attr") !="" && $(this).attr("locationids_attr") != "undefined"){
		loctionIds = $(this).attr("locationids_attr");
	}
	getElectionInformationLocationWiseStatusAndYearWise(eletionSubType,electionYrVal,partyId,searchLevelVal,electionTypeVal,partyName,electionTypetext,statusTpe,year,loctionIds);
});


function  getElectionInformationLocationWiseStatusAndYearWise(eletionSubType,electionYrVal,partyId,searchLevelVal,electionTypeVal,partyName,electionTypetext,statusTpe,year,loctionIdsList){
	$("#locationWiseStrongVsPoor").html(spinner);
	var partyIdsList=[];
	var electionScopeIds=[];
	if(partyId !=0){
		partyIdsList.push(partyId)
	}else{
		partyIdsList=[];
	}
	electionScopeIds.push(electionTypeVal);
	var locationIds=[]
	if(loctionIdsList.length >0){
		var stringIds = loctionIdsList,
			strx   = stringIds.split(',');
		locationIds = locationIds.concat(strx);
	}
	var jsObj={
		locationTypeId 		:locationLevelId,
		locationValue 		:userAccessLevelValue,
		electionScopeIds	:electionScopeIds,
		partyIdsList  		:partyIdsList,
		electionYears     	:electionYrVal,
		electionYears     	:electionYrVal,
		electionSubTypeArr 	:eletionSubType,
		searchType			:searchLevelVal,
		statusType			:statusTpe,
		year				:year,
		locationIds			:locationIds
		
	}
	$.ajax({
	  type : "GET",
	  url : "getElectionInformationLocationWiseStatusAndYearWiseAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildElectionInformationLocationWiseStatus(result,electionTypeVal,searchLevelVal,partyName,electionTypetext,year);
		}else{
			$("#locationWiseStrongVsPoor").html("No Data Available");
		}
	});
}
function getLocationWiseVotingDetails(electionYrVal,subTypesArr,clickType,userAccessLevelValuesArray,locationLevelId){
  if(clickType == "clickFunction"){
		  $("#votingDetailsSubLevelBlockId").html(spinner);
	  }else{
		  $("#votingDetailsBlockId").html(spinner);
	  }
  jsObj={
    electionYearArr  :electionYrVal,
    locationValue    :userAccessLevelValuesArray, //Id
    subTypesArr      :subTypesArr,
    locationLevelId  :locationLevelId, //locationId
    searchLevel:"tehsil",//panchayat,tehsil only if locationLevelId=4 
    clickType:clickType,
	partyIds:[872,1117,163,362]
  }
  $.ajax({
    type : "GET",
    url : "getLocationWiseVotingDetailsAction.action",
    dataType : 'json',
    data : {task :JSON.stringify(jsObj)}
  }).done(function(result){  
	if(result !=null && result.length>0){
		return buildLocationWiseVotingDetails(result,clickType);
	}else{
		if(clickType == "clickFunction"){
		  $("#votingDetailsSubLevelBlockId").html("No Data Available");
		}else{
		  $("#votingDetailsBlockId").html("No Data Available");
		}
	}	
  });
  
  function buildLocationWiseVotingDetails(result,clickType){
	  
	  var str='';
	  
	  str+='<div class="table-responsive">';
	  if(clickType == "clickFunction"){
		  str+='<table class="table table-condensed" id="dataTableVotingSubLevelDts">';
		}else{
		  str+='<table class="table table-condensed" id="dataTableVotingDts">';
		} 
		
			str+='<thead class="bg-E9">';
				
					if(clickType == "clickFunction"){
						str+='<tr>';
						//str+='<th>Part No</th>';
						str+='<th>Locations Covered</th>';
						str+='<th>Total Voters</th>';
						str+='<th>(A)Polled Votes</th>';
						str+='<th>AC* Votes</th>';
						str+='<th>PC* Votes</th>';
						str+='<th>AC* %</th>';
						str+='<th>PC* %</th>';
						str+='<th>% Diff</th>';
						str+='</tr>';
					}else{
						str+='<tr>';
						if(locationLevelId == '2')
						{
							str+='<th>District</th>';
						}else if(locationLevelId == '3')
						{
							str+='<th>Constituency</th>';
						}else if(locationLevelId == '10')
						{
							str+='<th>Constituency</th>';
						}else if(locationLevelId == '4' || locationLevelId == '11' )
						{
							str+='<th>Mandal</th>';
						}else if(locationLevelId == '5' || locationLevelId == '12' )
						{
							str+='<th>Panchayat</th>';
						}else if(locationLevelId == '6'){
							str+='<th>Panchayat</th>';
						}else{
							str+='<th>Location</th>';
						}
						str+='<th>Polled Votes</th>';
						str+='<th>Assembly Candidate</th>';
						str+='<th>Parliament Candidate</th>';
						str+='<th>Cross Voting</th>';
						str+='</tr>';
					}
					
				
			str+='</thead>';
			str+='<tbody>';
				
					for(var i in result){
						if(clickType == "clickFunction"){
							
							var assemblyCandPerc = (parseFloat(result[i].earnedVotersPerc)).toFixed(2);
							var parliamentCandPerc =  (parseFloat(result[i].earnedVotersPerc1)).toFixed(2);
							var crossVotingPerc = (parseFloat(result[i].perc)).toFixed(2);
							
							str+='<tr>';
								str+='<td class="" attr_locationId="'+result[i].locationId+'" attr_locationValue="'+result[i].id+'">'+result[i].name+'</td>';
								str+='<td>'+result[i].totalVoters+'</td>';
								str+='<td>'+result[i].validVoters+'</td>';
								str+='<td>'+result[i].assemblyEarndVotes+'</td>';
								str+='<td>'+result[i].parliamentEarnedVotes+'</td>';
								str+='<td>'+assemblyCandPerc+' %</td>';
								str+='<td>'+parliamentCandPerc+' %</td>';
								str+='<td>'+crossVotingPerc+' %</td>';
							str+='</tr>';
							
						}else{
							var assemblyCandPerc = (parseFloat(result[i].earnedVotersPerc)).toFixed(2);
							var parliamentCandPerc =  (parseFloat(result[i].earnedVotersPerc1)).toFixed(2);
							var crossVotingPerc = (parseFloat(result[i].perc)).toFixed(2);
							
							str+='<tr>';
								str+='<td style="cursor:pointer;" class="votingDtsClickCls" attr_locationId="'+result[i].locationId+'" attr_locationValue="'+result[i].id+'" attr_name="'+result[i].name+'">'+result[i].name+'</td>';
								str+='<td>'+result[i].validVoters+'</td>';
								str+='<td>'+assemblyCandPerc+' %</td>';
								str+='<td>'+parliamentCandPerc+' %</td>';
								str+='<td>'+crossVotingPerc+' %</td>';
							str+='</tr>';
						}
						
					}
				
			str+='</tbody>';
		str+='</table>';
	  str+='</div>';
	  if(clickType == "clickFunction"){
		  $("#votingDetailsSubLevelBlockId").html(str);
	 		  $("#dataTableVotingSubLevelDts").dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			}); 
	  }else{
		  $("#votingDetailsBlockId").html(str);
			   $("#dataTableVotingDts").dataTable({
				"iDisplayLength": 10,
				"aaSorting": [],
				"aLengthMenu": [[10, 15, 20, -1], [10, 15, 20, "All"]]
			}); 
	  }
	  
  }
}
$(document).on("click",".votingDtsClickCls",function(){
	
	var locationLevelId = $(this).attr("attr_locationId");
	var locationLevelValue = $(this).attr("attr_locationValue");
	var locationName = $(this).attr("attr_name");
	
	userAccessLevelValuesArray=[];
	userAccessLevelValuesArray.push(locationLevelValue)
	
	
	eletionSubType=[];
		 $('.electionSubTypeCls').each(function(){
			if ($(this).is(':checked')){
				eletionSubType.push($(this).val());
			}
		 });
		electionYrVal=[];
		electionYrVal = $("#electionYearId").val();
	
	$("#openModalDivId").modal("show");
		$("#titleId").html(locationName+"  Voting Details");
	//getLocationWiseVotingDetails(electionYrVal,eletionSubType,"clickFunction",userAccessLevelValuesArray,locationLevelId)
});
$(document).on("change","#elctionTypeValId",function(){
	var value = $(this).val();
	
	if(locationLevelId == "2"){
		
		if(value == "1" || value == 1){
			
			$("#searchLevelId").html('');
			$("#searchLevelId").append('<option value="parliament">Parliament</option>');	
			$("#searchLevelId").trigger("chosen:updated");
		}else{
			$("#searchLevelId").html('');
			$("#searchLevelId").append('<option value="district">District</option>');	
			$("#searchLevelId").append('<option value="constituency">Constituency</option>');	
			$("#searchLevelId").append('<option value="parliament">Parliament</option>');	
			$("#searchLevelId").trigger("chosen:updated");
		}
	}
	
});