var spinner = '<div class="row"><div class="col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var globalStrongPoorColor={"VERY GOOD":'#009900',"OK":"#FFCC00","POOR":"#FF9966","VERY POOR":"#CC6600","WORST":"#FF3E3E","GOOD":"#428AE9","EXCELLENT":"#009999"}
var globalCrossVotionBgColor={"TDP":'#FFFFB8',"YSRC":"#CEE8DF","BJP":"#f4c36e","AIMIM":"#7bf7a6","INC":"#CEDFEA","CPI":"#fc713f","CPM":"#f995b5","PRP":"#b6f986","TRS":"#F7E8D9"}
var electionScopeValArr=[];
var eletionSubType=["MAIN"];
var electionYrVal = [];
var globalParliamentIds = [];
var mainArr = [];
var boothWiseDetailsArr;
$(window).scroll(function(){
		var windowScrollTop = $(window).scrollTop();
		var header = $('.scrollHeading')
		//var header = $('.dummy')
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
},1500);

function onLoadCalls()
{
	$(".chosen-select").chosen();
	$("#mianHeadingId").html((locationName)+"  "+locationLevelName);
	if(locationLevelId < "5"){
		$(".searchLevelCls").show();
	}else{
		$(".searchLevelCls").hide();
	}
	$('#partyId,#partyBoothWiseId').multiselect({
		enableFiltering: true,
		includeSelectAllOption: true,
		selectAllText: 'All Parties',
		maxHeight: 300,
		buttonWidth: '100%',
		dropDown: true,
		selectAllNumber: true,
		allSelectedText: 'All Parties selected'
	});
	
	if(locationLevelId == '4'){
		getDetailedElectionInformaction();
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
		getElectionYears("partyTrends","onload");
		getElectionYears("strong","onload");
		getElectionYears("cross","onload");
		getElectionYears("booth","onload");
		
	});	
}
$(document).on("click",".electionTypeWiseCls",function(){
	var value = $(this).val();
	if(value != 0){
		$(".checkUncheckCls").prop("checked",false);
		$("#electionYearId").multiselect("destroy");
		getElectionYears("partyTrends","change");
	}else if(value == 0){
		if ($(this).is(':checked')){
			$(".checkUncheckCls").prop("checked",true);
			$(".electionTypeWiseCls").prop("checked",true);
			$("#electionYearId").multiselect("destroy");
			getElectionYears("partyTrends","change");
		}else{
			$(".checkUncheckCls").prop("checked",false);
			$(".electionTypeWiseCls").prop("checked",false);
		}
	}
});
$(document).on("click",".electionTypeWiseStrongCls",function(){
	var value = $(this).val();
	if ($(this).is(':checked')){
		$("#electionYearForStrongId").multiselect("destroy");
		getElectionYears("strong","change");
	}
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
	if(value == "3" || value == "4"){
		$("#searchLevelId").removeClass("addSearchValCls")
		$(".searchLevelCls").hide();
	}else{
		$("#searchLevelId").addClass("addSearchValCls")
		$(".searchLevelCls").show();
	}
});
$(document).on("click",".electionTypeWiseCrossCls",function(){
	var value = $(this).val();
	if ($(this).is(':checked')){
		getElectionYears("cross","change");
	}
	
});
$(document).on("click",".electionSubTypeCls",function(){
	var type = $(this).attr("attr_type");
	eletionSubType=[];
		if(type == "partyTrends"){
			if ($(this).is(':checked')){
				 eletionSubType.push($(this).val())
			}
			$("#electionYearId").multiselect("destroy");
			getElectionYears("partyTrends","change");
		}else if(type == "strong"){
			if ($(this).is(':checked')){
				  eletionSubType.push($(this).val())
			}
			$("#electionYearForStrongId").multiselect("destroy");
			getElectionYears("strong","change");
		}else if(type == "cross"){
			if ($(this).is(':checked')){
				  eletionSubType.push($(this).val())
			}
			getElectionYears("cross","change");
		}else if(type == "booth"){
			if ($(this).is(':checked')){
				  eletionSubType.push($(this).val())
			}
			getElectionYears("booth","change");
		}
		
});
$(document).on("click","[role='tabSwitch'] li",function(){
	$(this).closest("ul").find("li").removeClass("active");
	$(this).addClass("active");	
	getElectionYears("booth","change");
	
});
$(document).on("click",".getDetailsCls",function(){
	
	electionYrVal=[];
	electionYrVal = $("#electionYearId").val();
	var partyIdArr=[];
	partyIdArr = $("#partyId").val();
	
	var j = 0;	
	 eletionSubType = [];
	 $(".electionSubTypeCls").each(function(){
		 var type = $(this).attr("attr_type")
		 if(type == "partyTrends"){
			 if ($(this).is(':checked')){
				eletionSubType.push($(this).val());
			}
		}

	});
	
	var i = 0;	
	electionScopeValArr = [];
	$('.electionTypeWiseCls').each(function(){
		if ($(this).is(':checked') && $(this).val() != 0){
			electionScopeValArr[i++] = $(this).val();
		}
	});
	
	//party wise election trends Block
	getLocationWiseElectionResults(electionYrVal,eletionSubType,partyIdArr,electionScopeValArr);
	//Party Election Results Block
	getElectionDetailsData(electionYrVal,eletionSubType,partyIdArr,electionScopeValArr);
		
	});
$(document).on("click",".getDetailsForStrongCls,.removeFilterCls",function(){
		
	electionYrValForStrong=[];
	electionYrValForStrong = $("#electionYearForStrongId").val();
	var partyIdForStrong = $("#partyIdForStrongBlock").val();
	var partyNameForStrong = $("#partyIdForStrongBlock option:selected").text();
	var searchLevelVal = $("#searchLevelId").val();
	
	var electionScopetext='';
	var electionScopeVal=0;
	 $('.electionTypeWiseStrongCls').each(function(){
		if ($(this).is(':checked')){
			electionScopetext =$(this).parent().text().replace(/\s/g, '');
			electionScopeVal = $(this).val();
		}
	 });
	 
	 eletionSubType=[];
	 $('.electionSubTypeCls').each(function(){
		 var type = $(this).attr("attr_type");
		 if(type == "strong"){
			 if ($(this).is(':checked')){
				eletionSubType.push($(this).val());
			}
		 }
		
	 });
	 
	 var year=0;
	 if($("#searchLevelId").hasClass("addSearchValCls")){
		 getElectionInformationLocationWiseStatus(eletionSubType,electionYrValForStrong,partyIdForStrong,searchLevelVal,electionScopeVal,partyNameForStrong,electionScopetext,year);	
	 }else{
		 getElectionInformationLocationWiseStatus(eletionSubType,electionYrValForStrong,partyIdForStrong,"constituency",electionScopeVal,partyNameForStrong,electionScopetext,year);	
	 }
	
});		
$(document).on("click",".getDetailsForCrossVotingCls",function(){
	
		electionYrValForCross=[];
		electionYrValForCross.push($("#electionYearForCrossId").val())
		var partyIdForCross = $("#partyIdForCrossBlock").val();
		
		var electionScopeVal=0;
		 $('.electionTypeWiseCrossCls').each(function(){
			if ($(this).is(':checked')){
				electionScopeVal = $(this).val();
			}
		 });
		 var j = 0;	
		 eletionSubType = [];
		 $(".electionSubTypeCls").each(function(){
			 var type = $(this).attr("attr_type");
			 if(type == "cross"){
				 if ($(this).is(':checked')){
					eletionSubType.push($(this).val());
				}
			 }
		});
		getLocationWiseCrossVotingDetails(eletionSubType,electionYrValForCross,partyIdForCross,electionScopeVal,"mainView",0)
		
	});
$(document).on("click",".getDetailsForBoothWiseCls",function(){
		
		$(".switch-btn-New li").removeClass("active");
		$(".activeCls").addClass("active");
		var electionYrValForBooth = $("#electionYearBoothWiseId").val();
		var partyIdForBoothArr=[];
		partyIdForBoothArr = $("#partyBoothWiseId").val();
		 
		var constituencyId = $('#assemblyConsBoothWiseId').val();
		var constituencyName = $('#assemblyConsBoothWiseId option:selected').text();
		
		var electionScopeVal=0;
		
		$("[role='tabSwitch'] li").each(function(){
			if ($(this).hasClass("active")){
				electionScopeVal = $(this).attr("attr_val");
			}
		});
		
		getboothWiseResults(electionYrValForBooth,partyIdForBoothArr,constituencyId,electionScopeVal,constituencyName)
		
});
function getElectionYears(type,typeLoad){
	if(type == "strong"){
		$('#electionYearForStrongId').html('');
		var i = 0;	
		electionScopeValArr = [];
		$('.electionTypeWiseStrongCls').each(function(){
			if ($(this).is(':checked') && $(this).val() != 0){
				electionScopeValArr[i++] = $(this).val();
			}
		});
	}else if(type == "partyTrends"){
		$('#electionYearId').html('');
		
		var i = 0;	
		electionScopeValArr = [];
		$('.electionTypeWiseCls').each(function(){
			if ($(this).is(':checked') && $(this).val() != 0){
				electionScopeValArr[i++] = $(this).val();
			}
		});
	}else if(type == "cross"){
		$('#electionYearForCrossId').html('');
		electionScopeValArr = ["1","2"];
		
	}else if(type == "booth"){
		electionScopeValArr = [];
		$('#electionYearForCrossId').html('');

		$("[role='tabSwitch'] li").each(function(){
			if ($(this).hasClass("active")){
				electionScopeValArr.push($(this).attr("attr_val"))
			}
		});
	}
	
	
	var jsObj={
		electionScopeIds:electionScopeValArr,
		electionSubTypeArr:eletionSubType
	}
    $.ajax({   
      type:'POST',
      url:'getElectionYearAndPartiesAction.action',  
      dataType: 'json',
      data: {task:JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
				var str='';
				if(type == "cross"){
					str+='<option value="0">Select Election Year</option>';
				}else if(type == "booth"){
					str+='<option value="0">Select Election Year</option>';
				}
				for(var i in result){
					if(type == "cross" || type == "booth"){
						if(result[i].electionYear == "2014" || result[i].electionYear == 2014){
							str+='<option value="'+result[i].electionYear+'" selected>'+result[i].electionYear+'</option>';
						}else{
							if(i == 0){
								str+='<option value="'+result[i].electionYear+'" selected>'+result[i].electionYear+'</option>';
							}else{
								str+='<option value="'+result[i].electionYear+'">'+result[i].electionYear+'</option>';
							}
							
						}
					}else{
						str+='<option value="'+result[i].electionYear+'" selected>'+result[i].electionYear+'</option>';
					}
					
				}
				if(type == "strong"){
					$('#electionYearForStrongId').html(str);
					$('#electionYearForStrongId').multiselect({
						enableFiltering: true,
						includeSelectAllOption: true,
						selectAllText: 'All Election Years',
						maxHeight: 300,
						buttonWidth: '100%',
						dropDown: true,
						selectAllNumber: true,
						allSelectedText: 'All Election Years selected'
					});
				}else if(type == "partyTrends"){
					$('#electionYearId').html(str);
					$('#electionYearId').multiselect({
						enableFiltering: true,
						includeSelectAllOption: true,
						selectAllText: 'All Election Years',
						maxHeight: 300,
						buttonWidth: '100%',
						dropDown: true,
						selectAllNumber: true,
						allSelectedText: 'All Election Years selected'
					});
				}else if(type == "cross"){
					$('#electionYearForCrossId').html(str);
					$('#electionYearForCrossId').trigger("chosen:updated");
				}else if(type == "booth"){
					$('#electionYearBoothWiseId').html(str);
					$('#electionYearBoothWiseId').trigger("chosen:updated");
				}	
				
		}else{
			if(type == "strong"){
				$("#electionYearForStrongId").append("<select id='electionYearForStrongId' multiple><option value='0' selected>No Election Years</option></select>");
				$('#electionYearForStrongId').multiselect({
					enableFiltering: true,
					includeSelectAllOption: true,
					selectAllText: 'No Election Years',
					maxHeight: 300,
					buttonWidth: '100%',
					dropDown: true,
					selectAllNumber: true,
					allSelectedText: 'No Election Years'
				});
			}else if(type == "partyTrends"){
				$("#electionYearId").append("<select id='electionYearId' multiple><option value='0' selected>No Election Years</option></select>");
				$('#electionYearId').multiselect({
					enableFiltering: true,
					includeSelectAllOption: true,
					selectAllText: 'No Election Years',
					maxHeight: 300,
					buttonWidth: '100%',
					dropDown: true,
					selectAllNumber: true,
					allSelectedText: 'No Election Years'
				});
			}
		}
			
			if(type == "partyTrends" && typeLoad == "onload"){
				electionYrVal=[];
				electionYrVal = $("#electionYearId").val();
				var partyIdArr=[];
				partyIdArr = $("#partyId").val();
				
				//party wise election trends Block
				getLocationWiseElectionResults(electionYrVal,eletionSubType,partyIdArr,electionScopeValArr);
				//Party Election Results Block
				getElectionDetailsData(electionYrVal,eletionSubType,partyIdArr,electionScopeValArr);
				//strong Block
				
			}else if(type == "strong" && typeLoad == "onload"){
				electionYrValForStrong=[];
				electionYrValForStrong = $("#electionYearForStrongId").val();
				var partyIdForStrong = $("#partyIdForStrongBlock").val();
				var partyNameForStrong = $("#partyIdForStrongBlock option:selected").text();
				var searchLevelVal = $("#searchLevelId").val();
				
				var electionScopetext='';
				var electionScopeVal=0;
				 $('.electionTypeWiseStrongCls').each(function(){
					if ($(this).is(':checked')){
						 var idVal = $(this).attr("id");
						electionScopetext =$(this).parent().text().replace(/\s/g, '');
						electionScopeVal = $(this).val();
					}
				 });
				 
				 var year=0;
				 
				getElectionInformationLocationWiseStatus(eletionSubType,electionYrValForStrong,partyIdForStrong,searchLevelVal,electionScopeVal,partyNameForStrong,electionScopetext,year)
			}else if(type == "cross" && typeLoad == "onload"){
				electionYrValForCross=[];
				electionYrValForCross.push($("#electionYearForCrossId").val())
				var partyIdForCross = $("#partyIdForCrossBlock").val();
				
				var electionScopeVal=0;
				 $('.electionTypeWiseCrossCls').each(function(){
					if ($(this).is(':checked')){
						electionScopeVal = $(this).val();
					}
				 });
				getLocationWiseCrossVotingDetails(eletionSubType,electionYrValForCross,partyIdForCross,electionScopeVal,"mainView",0)
			}else if(type == "booth" && typeLoad == "onload"){
				if(locationLevelId == '2'){
					getConstituenciesByDistrict(0);
				}else if(locationLevelId == '3'){
					getConstituenciesByDistrict(districtId);
				}else if(locationLevelId == '10'){
					getAllConstituencyByParliments(parliamentId);
				}else if(locationLevelId == '4' || locationLevelId == '5' || locationLevelId == '6'){
					
					var electionYrValForBooth = $("#electionYearBoothWiseId").val();
					var partyIdForBoothArr=[];
					partyIdForBoothArr = $("#partyBoothWiseId").val();
					$("#assemblyConsBoothWiseId").html('');
					$("#assemblyConsBoothWiseId").append('<option value="'+constituencyId+'">'+locationName+'</option>');	
					$("#assemblyConsBoothWiseId").trigger("chosen:updated");
					 
					//var constituencyName = $('#assemblyConsBoothWiseId option:selected').text();
					//var constituencyId = $('#assemblyConsBoothWiseId').val();
					
					var electionScopeVal=0;
					
					$("[role='tabSwitch'] li").each(function(){
						if ($(this).hasClass("active")){
							electionScopeVal = $(this).attr("attr_val");
						}
					});
					getboothWiseResults(electionYrValForBooth,partyIdForBoothArr,constituencyId,electionScopeVal,locationName)
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
			
			var electionYrValForBooth = $("#electionYearBoothWiseId").val();
			var partyIdForBoothArr=[];
			partyIdForBoothArr = $("#partyBoothWiseId").val();
			var constituencyId = $('#assemblyConsBoothWiseId').val();
			var constituencyName = $('#assemblyConsBoothWiseId option:selected').text();
			var electionScopeVal=0;
			$("[role='tabSwitch'] li").each(function(){
				if ($(this).hasClass("active")){
					electionScopeVal = $(this).attr("attr_val");
				}
			});
			getboothWiseResults(electionYrValForBooth,partyIdForBoothArr,constituencyId,electionScopeVal,constituencyName)
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
			}
			var electionYrValForBooth = $("#electionYearBoothWiseId").val();
			var partyIdForBoothArr=[];
			partyIdForBoothArr = $("#partyBoothWiseId").val();
			var constituencyId = $('#assemblyConsBoothWiseId').val();
			var constituencyName = $('#assemblyConsBoothWiseId option:selected').text();
			var electionScopeVal=0;
			
			$("[role='tabSwitch'] li").each(function(){
				if ($(this).hasClass("active")){
					electionScopeVal = $(this).attr("attr_val");
				}
			});
			getboothWiseResults(electionYrValForBooth,partyIdForBoothArr,constituencyId,electionScopeVal,constituencyName)
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
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'_01.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
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
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'_01.PNG" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
															}else{
																navTabs+='<img class="" src="images/party_flags/'+result[i].candidateResultsVO.partyShortName+'.png" alt="'+result[i].candidateResultsVO.partyShortName+'" style="height:25px;width:25px;"></img>';
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
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'_01.PNG" alt="'+result[i].candidateOppositionList[j].partyShortName+'" style="height:25px;width:25px;"></img></td>';
														}else{
															navTabs+='<td><img class="" src="images/party_flags/'+result[i].candidateOppositionList[j].partyShortName+'.png" alt="'+result[i].candidateOppositionList[j].partyShortName+'" style="height:25px;width:25px;"></img></td>';
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
function getLocationWiseElectionResults(electionYrVal,eletionSubType,partyId,electionScopeValArr){
	
	$("#levelWiseCandidatesResultsDivId").html(spinner);
	
	var partyIdsArr=[];
	var yearsArr=[];
	var constituencyId;
	
	if(partyId == 0){
		partyIdsArr = [872,362,1117,886,72,269,265,163];
	}else{
		partyIdsArr = partyId
	}
	if(electionYrVal =="null" || electionYrVal == null){
		yearsArr=[];
	}else{
		yearsArr = electionYrVal;
	}
	if(locationLevelId != null &&(locationLevelId == 2 || locationLevelId == 3 || locationLevelId == 10)){
		constituencyId = 0;
	}else{
		constituencyId = searchParams
	}
	
	var withAllance="false";
	if($('#allaincePartiFieldId').is(':checked')){
		withAllance="true";
	}
	var jsObj={
		
		electionScopeIdsArr:electionScopeValArr,
		electionSubTypeArr:eletionSubType,
		lelevlId:locationLevelId,
		locationValuesArr:userAccessLevelValuesArray,
		yearsArr:yearsArr,
		partyIdsArr:partyIdsArr,
		constituencyId :constituencyId,
		withAllaince:withAllance
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
			$("#levelWiseCandidatesResultsDivId").html("");
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
										var specialCharacter = result[i].list[j].partyName;
										if(result[i].list[j].partyName == "OTHERS"){
											str+='<td>'+result[i].list[j].partyName+'</td>';
										}else{
											if(/^[a-zA-Z0-9- ]*$/.test(specialCharacter) == false) {
												str+='<td>'+result[i].list[j].partyName+'</td>';
											}else{
												if(result[i].list[j].partyName == "TDP" || result[i].list[j].partyName == "YSRC"){
													str+='<td><p><img class="" src="images/party_flags/'+result[i].list[j].partyName+'_01.PNG" alt="'+result[i].list[j].partyName+'" style="height:25px;width:25px;"></img>  '+result[i].list[j].partyName+'</p></td>';
												}else{
													str+='<td><p><img class="" src="images/party_flags/'+result[i].list[j].partyName+'.png" alt="'+result[i].list[j].partyName+'" style="height:25px;width:25px;"></img>   '+result[i].list[j].partyName+'</p></td>';
												}
											}
											
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
				"aaSorting": [[2,"desc"]],
				"sDom": '<"top"iflp>rt<"bottom"><"clear">',
				
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
function getElectionDetailsData(electionYrVal,eletionSubType,partyId,electionScopeValArr){
	$("#locationWiseCandidatesResultsDivId").html(spinner);
	
	var partyIdsArr=[];
	var electionYearArr=[];
	if(partyId == 0){
		partyIdsArr = [872,362,1117,886,72,269,265,163];
	}else{
		partyIdsArr = partyId
	}
	if(electionYrVal =="null" || electionYrVal == null){
		electionYearArr=[];
	}else{
		electionYearArr = electionYrVal;
	}
	var withAllance="false";
	if($('#allaincePartiFieldId').is(':checked')){
		withAllance="true";
	}
	
	var jsObj={
		locationTypeId     	:locationLevelId,
		locationValuesArr   :userAccessLevelValuesArray,
		electionId       	:0,
		electionYearArr     :electionYearArr,
		partyIdsArr       	:partyIdsArr,
		electionSubTypeArr  :eletionSubType,
		electionScopeIdsArr:electionScopeValArr,
		withAllaince:withAllance
    }
    $.ajax({
      type : "GET",
      url : "getElectionDetailsDataAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			return buildElectionDetailsData(result);
		}else{
			$("#locationWiseCandidatesResultsDivId").html("");
		}
	});
	
	function buildElectionDetailsData(result){
		
		var str='';
		str+='<div class="block boderBlock">';
			if(locationLevelId == '2')
			{
				str+='<h4 class="theme-title-color">District wise State Level Party Election Results</h4>';
			}else if(locationLevelId == '3')
			{
				str+='<h4 class="theme-title-color">Assembly wise District Level Party Election Results</h4>';		
			}else if(locationLevelId == '10')
			{
				str+='<h4 class="theme-title-color">Assembly wise  Parliamenr Level Party Election Results</h4>';
			}else if(locationLevelId == '4')
			{
				str+='<h4 class="theme-title-color">Mandal/Muncipality wise Constituency Level Party Election Results</h4>';
			}else if(locationLevelId == '5')
			{
				str+='<h4 class="theme-title-color">Panchayat wise Mandal Level Party Election Results</h4>';
			}else if(locationLevelId == '6')
			{
				str+='<h4 class="theme-title-color">Panchayat Level Party Election Results</h4>';
			}else
			{
				str+='<h4 class="theme-title-color">Location Level Party Election Results</h4>';
			}
			
			str+='<div class="table-responsive m_top10">';
				str+='<table class="table table-condensed table-bordered table-striped table_custom" id="dataTableElecTypeRsts">';
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
								str+='<th rowspan="2">Assembly Name</th>';
							}else if(locationLevelId == '4')
							{
								str+='<th rowspan="2">Mandal/Muncipality Name</th>';
							}else if(locationLevelId == '5')
							{
								str+='<th rowspan="2">Mandal Name</th>';
							}else if(locationLevelId == '6')
							{
								str+='<th rowspan="2">Panchayat Name</th>';
							}else if(locationLevelId == '7')
							{
								str+='<th rowspan="2">Mandal/Muncipality Name</th>';
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
												str+='<th class="text-center"><img class="" src="images/party_flags/'+result[0].subList1[i].subList1[j].partyName+'_01.PNG" alt="'+result[0].subList1[i].subList1[j].partyName+'" style="height:25px;width:25px;"></img></th>';
											}else{
												str+='<th class="text-center"><img class="" src="images/party_flags/'+result[0].subList1[i].subList1[j].partyName+'.png" alt="'+result[0].subList1[i].subList1[j].partyName+'" style="height:25px;width:25px;"></img></th>';
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
												str+='<td>'+result[i].subList1[j].subList1[k].earnedVote;
												if(result[i].subList1[j].subList1[k].earnedVote !=null && result[i].subList1[j].subList1[k].wonSeatsCount>0){
													str+='<span style="margin-left:5px;color:green;">('+result[i].subList1[j].subList1[k].wonSeatsCount+')</span>';
												}
												str+='</td>';
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
function getElectionInformationLocationWiseStatus(eletionSubType,electionYrValForStrong,partyIdForStrong,searchLevelVal,electionScopeVal,partyNameForStrong,electionScopetext,year){
	
	$("#locationWiseStrongVsPoor").html(spinner);
	var partyIdsList=[];
	var electionScopeValArr=[];
	var electionYears=[];
	
	if(partyIdForStrong !=0){
		partyIdsList.push(partyIdForStrong)
	}else{
		partyIdsList=[]
	}
	
	if(electionScopeVal !=0){
		electionScopeValArr.push(electionScopeVal)
	}else{
		electionScopeValArr=[]
	}
	if(electionYrValForStrong == "null" || electionYrValForStrong == null){
		electionYears=[];
	}else{
		electionYears = electionYrValForStrong;
	}
	var withAllance="false";
	if($('#allainceStatusPartyFieldId').is(':checked')){
		withAllance="true";
	}
	var jsObj={
	  	locationTypeId 		:locationLevelId,
		locationValue 		:userAccessLevelValue,
		electionScopeIds	:electionScopeValArr,
		partyIdsList  		:partyIdsList,
		electionYears     	:electionYears,
		electionSubTypeArr 	:eletionSubType,
		searchType			:searchLevelVal,
		withAllaince:withAllance
    }
    $.ajax({
      type : "GET",
      url : "getElectionInformationLocationWiseStatusAction.action",
      dataType : 'json',
      data : {task :JSON.stringify(jsObj)}
    }).done(function(result){
		if(result !=null && result.length>0){
			 buildElectionInformationLocationWiseStatus(result,electionScopeVal,searchLevelVal,partyNameForStrong,electionScopetext,year);
		}else{
			$("#locationWiseStrongVsPoor").html("No Data Available");
		}
	});
}

function buildElectionInformationLocationWiseStatus(result,electionTypeVal,searchLevelVal,partyNameForStrong,electionScopetext,year){
		
	var str='';
	str+='<h4 class="text-capital m_top10">'+searchLevelVal+' Level '+electionScopetext+' Election Comparision Results For '+partyNameForStrong+' Party</h4>';
	
	
	str+='<div class="table-responsive m_top10">';
	
	str+='<table class="table table-condensed table-bordered" >';
	str+='<thead>';
	str+='<tr>';
	str+='<th style="background-color:#FF3E3E;">WORST %</th>';
	str+='<th style="background-color:#CC6600;">VERY POOR %</th>';
	str+='<th style="background-color:#FF9966;">POOR %</th>';
	str+='<th style="background-color:#FFCC00;">OK %</th>';
	str+='<th style="background-color:#428AE9;">GOOD %</th>';
	str+='<th style="background-color:#009900;">VERY GOOD %</th>';
	str+='<th style="background-color:#009999;">EXCELLENT %</th>';
	str+='</tr>';
	str+='</thead>';
	str+='<tbody>';
	str+='<tr>';
	str+='<td> Below (-10) </td>';
	str+='<td> -10 to -5 </td>';
	str+='<td> 0 to 5 </th>';
	str+='<td> -5 to <0 </td>';
	str+='<td> 5 to 10 </th>';
	str+='<td> 10 to 20 </th>';
	str+='<td> 20 Above  </th>';
	
	str+='</tr>';
	str+='</tbody>';
	str+='</table>';
	str+='</div>';
	
	
	str+='<div class="table-responsive m_top10">';
	if(searchLevelVal == "constituency" || searchLevelVal == "mandal" || searchLevelVal == "panchayat"){
		str+='<table class="table table-election1" id="electionResults">';
	}else{
		str+='<table class="table table-election" id="electionResults">';
	}
		
			str+='<thead>';
				str+='<tr>';
					if(locationLevelId == '2' || locationLevelId == 2){
						if(electionScopetext == "Assembly" && searchLevelVal == "district"){
							str+='<th>District</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "district"){
							str+='<th>Parliament</th>';
						}else if(electionScopetext == "MPTC"){
							str+='<th>Location</th>';
						}else if(electionScopetext == "ZPTC"){
							str+='<th>Location</th>';
						}else if(electionScopetext == "Assembly" && searchLevelVal == "parliament"){
							str+='<th>Parliament</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "parliament"){
							str+='<th>Parliament</th>';
						}else if(electionScopetext == "Assembly" && searchLevelVal == "constituency"){
							str+='<th>District</th>';
							str+='<th>Constituency</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "constituency"){
							str+='<th>Parliament</th>';
						}else{
							str+='<th>Location</th>';
						}
						
					}else if(locationLevelId == '3' || locationLevelId == 3){
						if(electionScopetext == "Assembly" && (searchLevelVal == "mandal" || searchLevelVal == "Mandal")){
							str+='<th>Constituency</th>';
							str+='<th>Mandal</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "mandal"){
							str+='<th>Location</th>';
						}else if(electionScopetext == "MPTC"){
							str+='<th>Panchayat</th>';
						}else if(electionScopetext == "ZPTC"){
							str+='<th>Mandal</th>';
						}else if(electionScopetext == "Assembly" && searchLevelVal == "constituency"){
							str+='<th>District</th>';
							str+='<th>Constituency</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "constituency"){
							str+='<th>Location</th>';
						}else{
							str+='<th>Location</th>';
						}
					}else if(locationLevelId == '4' || locationLevelId == 4){
						if(electionScopetext == "Assembly" && (searchLevelVal == "mandal" || searchLevelVal == "Mandal")){
							str+='<th>Constituency</th>';
							str+='<th>Mandal</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "mandal"){
							str+='<th>Mandal</th>';
						}else if(electionScopetext == "MPTC"){
							str+='<th>Location</th>';
						}else if(electionScopetext == "ZPTC"){
							str+='<th>Location</th>';
						}else if(electionScopetext == "Assembly" && (searchLevelVal == "Panchayat" || searchLevelVal == "panchayat")){
							str+='<th>Mandal</th>';
							str+='<th>Panchayat</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "Panchayat"){
							str+='<th>Mandal</th>';
						}else{
							str+='<th>Mandal</th>';
						}
					}else if(locationLevelId == '10' || locationLevelId == 10){
						if(electionScopetext == "MPTC"){
							str+='<th>Location</th>';
						}else if(electionScopetext == "ZPTC"){
							str+='<th>Location</th>';
						}else if(electionScopetext == "Assembly" && searchLevelVal == "constituency"){
							str+='<th>District</th>';
							str+='<th>Constituency</th>';
						}else if(electionScopetext == "Parliament" && searchLevelVal == "constituency"){
							str+='<th>Constituency</th>';
						}else{
							str+='<th>Constituency</th>';
						}
					}else if(locationLevelId == '5' || locationLevelId == 5){
						str+='<th>Mandal</th>';
					}else{
						str+='<th>Location</th>';
					}
					
					if(result[0].list !=null && result[0].list.length>0){
						for(var j in result[0].list){
							
							if(year == 0){
								str+='<th style="min-width:210px;">Election Year<h3>'+result[0].list[j].electionYear+'</h3>';
									str+='<div class="row">';
										for(var k in result[0].list[j].subList1){
											str+='<div class="col-sm-1" style="margin-left:5px">';
											if(result[0].list[j].subList1[k].wonSeatsCount !=null && result[0].list[j].subList1[k].wonSeatsCount>0){
												str+='<span class="statusClr statusClickCls" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;cursor:pointer"  status_attr="'+result[0].list[j].subList1[k].status+'" year_attr="'+result[0].list[j].electionYear+'" title="click here to view  '+result[0].list[j].subList1[k].status+' locations details" locationids_attr="'+result[0].list[j].subList1[k].idsList+'">'+result[0].list[j].subList1[k].wonSeatsCount+'</span>';
											}else{
												str+='<span class="statusClr" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;text-decoration:none;cursor: auto;">0</span>';
											}
												
											str+='</div>';
										}
										
										
									str+='</div>';
								str+='</th>';
							}else if(result[0].list[j].electionYear==year){
								str+='<th style="min-width:210px;background-color:#ccc;"><div class="row"><div class="col-sm-6"> Election Year<h3>'+result[0].list[j].electionYear+'</h3></div>';
								str+='<div class="col-sm-6"><button class="btn btn-xs btn-success pull-right removeFilterCls">Back </button></div></div>';
									str+='<div class="row">';
										for(var k in result[0].list[j].subList1){
											str+='<div class="col-sm-1" style="margin-left:5px">';
											if(result[0].list[j].subList1[k].wonSeatsCount !=null && result[0].list[j].subList1[k].wonSeatsCount>0){
												str+='<span class="statusClr statusClickCls" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;cursor:pointer"  status_attr="'+result[0].list[j].subList1[k].status+'" year_attr="'+result[0].list[j].electionYear+'" title="click here to view  '+result[0].list[j].subList1[k].status+' locations details"  locationids_attr="'+result[0].list[j].subList1[k].idsList+'">'+result[0].list[j].subList1[k].wonSeatsCount+'</span>';
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
												str+='<span class="statusClr" style="background-color:'+globalStrongPoorColor[result[0].list[j].subList1[k].status.trim()]+';color:#fff;text-decoration:none;"  title="'+result[0].list[j].subList1[k].status+' Locations "  status_attr="'+result[0].list[j].subList1[k].status+'" year_attr="'+result[0].list[j].electionYear+'"   locationids_attr="'+result[0].list[j].subList1[k].idsList+'">'+result[0].list[j].subList1[k].wonSeatsCount+'</span>';
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
				if(electionScopetext == "Assembly" && (searchLevelVal == "constituency" || searchLevelVal == "Mandal" || searchLevelVal == "mandal" || searchLevelVal == "Panchayat" || searchLevelVal == "panchayat" ))
					if(result[i].name != null){
						str+='<td>'+result[i].name+'</td>';
					}else{
						str+='<td>-</td>';
					}
					
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
	
	var partyIdForStrong = $("#partyIdForStrongBlock").val();
	var partyNameForStrong = $("#partyIdForStrongBlock option:selected").text();
	
	electionYrValForStrong=[];
	electionYrValForStrong = $("#electionYearForStrongId").val();
	
	var electionScopetext='';
	var electionScopeVal=0;
	 $('.electionTypeWiseStrongCls').each(function(){
		if ($(this).is(':checked')){
			electionScopetext =$(this).parent().text();
			electionScopeVal = $(this).val();
		}
	 });
	 
	 eletionSubType=[];
	 $('.electionSubTypeCls').each(function(){
		 var type = $(this).attr("attr_type");
		 if(type == "strong"){
			 if ($(this).is(':checked')){
				eletionSubType.push($(this).val());
			}
		 }
		
	 });
	 
	var loctionIds='';
	if($(this).attr("locationids_attr") !=null && $(this).attr("locationids_attr") !="" && $(this).attr("locationids_attr") != "undefined"){
		loctionIds = $(this).attr("locationids_attr");
	}

	getElectionInformationLocationWiseStatusAndYearWise(eletionSubType,electionYrValForStrong,partyIdForStrong,searchLevelVal,electionScopeVal,partyNameForStrong,electionScopetext,statusTpe,year,loctionIds);
});


function  getElectionInformationLocationWiseStatusAndYearWise(eletionSubType,electionYrValForStrong,partyIdForStrong,searchLevelVal,electionScopeVal,partyNameForStrong,electionScopetext,statusTpe,year,loctionIds){
	
	$("#locationWiseStrongVsPoor").html(spinner);
	
	var locationIds=[]
	if(loctionIds.length >0){
		var stringIds = loctionIds,
			strx   = stringIds.split(',');
		locationIds = locationIds.concat(strx);
	}
	
	var partyIdsList=[];
	var electionScopeValArr=[];
	var electionYears=[];
	
	if(partyIdForStrong !=0){
		partyIdsList.push(partyIdForStrong)
	}else{
		partyIdsList=[]
	}
	
	if(electionScopeVal !=0){
		electionScopeValArr.push(electionScopeVal)
	}else{
		electionScopeValArr=[]
	}
	if(electionYrValForStrong == "null" || electionYrValForStrong == null){
		electionYears=[];
	}else{
		electionYears = electionYrValForStrong;
	}
	
	var withAllance="false";
	if($('#allainceStatusPartyFieldId').is(':checked')){
		withAllance="true";
	}
	
	var jsObj={
		locationTypeId 		:locationLevelId,
		locationValue 		:userAccessLevelValue,
		electionScopeIds	:electionScopeValArr,
		partyIdsList  		:partyIdsList,
		electionYears     	:electionYears,
		electionSubTypeArr 	:eletionSubType,
		searchType			:searchLevelVal,
		statusType			:statusTpe,
		year				:year,
		locationIds			:locationIds,
		withAllaince:withAllance
		
	}
	$.ajax({
	  type : "GET",
	  url : "getElectionInformationLocationWiseStatusAndYearWiseAction.action",
	  dataType : 'json',
	  data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			buildElectionInformationLocationWiseStatus(result,electionYrValForStrong,searchLevelVal,partyNameForStrong,electionScopetext,year);
		}else{
			$("#locationWiseStrongVsPoor").html("No Data Available");
		}
	});
}
function getLocationWiseCrossVotingDetails(eletionSubType,electionYrValForCross,partyIdForCross,electionScopeVal,type,parliamentId){
	if(type == "mainView"){
		$("#crossVotingDetailsBlockId").html(spinner);
	}else{
		$("#votingDetailsSubLevelBlockId").html(spinner);
	}
	
	var partyIdsList=[];
	var electionScopeValArr=[];
	var electionYears=[];
	
	if(partyIdForCross !=0){
		partyIdsList.push(partyIdForCross)
	}else{
		partyIdsList=[]
	}
	
	if(electionScopeVal !=0){
		electionScopeValArr.push(electionScopeVal)
	}else{
		electionScopeValArr=[]
	}
	if(electionYrValForCross == "null" || electionYrValForCross == null || electionYrValForCross == 0 || electionYrValForCross == "0"){
		electionYears=[];
	}else{
		electionYears = electionYrValForCross;
	}
	
	jsObj={
		electionYearArr		:electionYears,
		parliamentIdsArr	:[],
		assemblyIdsArr 		:[],
		partyIdsArr 		:partyIdsList,
		locationValue		:userAccessLevelValuesArray,
		withAlliance		:"YES",
		subTypesArr			:eletionSubType,
		locationLevelId		:locationLevelId,
		electionScopeIdsArr:["1","2"]
	}
	$.ajax({
		type : "GET",
		url : "getLocationWiseCrossVotingDetailsAction.action",
		dataType : 'json',
		data : {task :JSON.stringify(jsObj)}
	}).done(function(result){
		if(result !=null && result.length>0){
			if(type == "mainView"){
				buildTableData(result,type);
			}else{
				buildExpandTableData(result,type,parliamentId);
			}
			
		}else{
			if(type == "mainView"){
				$("#crossVotingDetailsBlockId").html("No Data Available");
			}else{
				$("#votingDetailsSubLevelBlockId").html("No Data Available");
			}
			
		}	
		
	});
}
	function buildTableData(result,type)
	{
		var table='';
		table+='<h4 class="theme-title-color">Cross Voting Report</h4>';
		table+='<div class="table-responsive m_top10">';
		table+='<table class="table table-bordered table_custom" id="dataTableCrossMainView">';
			table+='<thead>';
			table+='<tr>';
				table+='<th>Parliament Name</th>';
				table+='<th>Polled Votes(PC)</th>';
				table+='<th>Polled Votes(AC)</th>';
				table+='<th>Rank(PC)</th>';
				table+='<th>Margin Votes(PC)</th>';
				table+='<th>Margin %(PC)</th>';
				table+='<th>Cross Voting Votes</th>';
				table+='<th>Cross Voting %</th>';
				table+='</tr>';
			table+='</thead>';
			table+='<tbody>';
			for(var i in result){
				table+='<tr>';
					table+='<td class="votingDtsClickCls" attr_type="crossVoting" attr_name="'+result[i].locationName+'" attr_parliamentId="'+result[i].locationId+'">'+result[i].locationName+'</td>';
					table+='<td>'+result[i].validVoters+'</td>';
					table+='<td>'+result[i].assemblyEarndVotes+'</td>';
					for(var j in result[i].subList1){
						table+='<td>'+result[i].subList1[j].rank+'</td>';
						table+='<td>'+result[i].subList1[j].marginVotes+'</td>';
						table+='<td>'+result[i].subList1[j].perc+'</td>';
						table+='<td>'+result[i].subList1[j].crossVotingCount+'</td>';
						if(parseFloat(result[i].subList1[j].crossVotingPerc)>0){
							table+='<td>'+parseFloat(result[i].subList1[j].crossVotingPerc).toFixed(2)+'';
							table+='<i class="fa fa-arrow-up" style="color:#3BB878 !important;"></i></td>';
						}else{
							table+='<td>'+parseFloat(result[i].subList1[j].crossVotingPerc).toFixed(2)+'';
							table+='<i class="fa fa-arrow-down" style="color:#F56666 !important;"></i></td>';
						}
					}
					
				table+='</tr>';
			}
				
			table+='</tbody>';
			
		table+='</table>';
		table+='</div>';
		$("#crossVotingDetailsBlockId").html(table);
		$("#dataTableCrossMainView").dataTable({
			"iDisplayLength": 10,
			 "aaSorting": [[ 1, "desc" ]], 
			"aLengthMenu": [[10, 30, 50, -1], [10, 30, 50, "All"]]
		});
	}	

	function buildExpandTableData(result,type,parliamentId)
	{
		var table='';
		table+='<div class="table-responsive">';
		table+='<table class="table table-bordered table_custom" id="dataTableCrossExpand">';
			table+='<thead>';
			table+='<tr>';
				table+='<th>Parliament Name</th>';
				table+='<th>Polled Votes(PC)</th>';
				table+='<th>Polled Votes(AC)</th>';
				table+='<th>Rank(PC)</th>';
				table+='<th>Margin Votes(PC)</th>';
				table+='<th>Margin %(PC)</th>';
				table+='<th>Cross Voting Votes</th>';
				table+='<th>Cross Voting %</th>';
				table+='</tr>';
			table+='</thead>';
			table+='<tbody>';
			for(var i in result){
				if(parliamentId == result[i].locationId){
					for(var j in result[i].list){
						table+='<tr>';
							table+='<td>'+result[i].list[j].locationName+'</td>';
							table+='<td>'+result[i].list[j].parliamentValidVoters+'</td>';
							table+='<td>'+result[i].list[j].assemblyValidVoters+'</td>';
							for(var k in result[i].list[j].subList1){
								table+='<td>'+result[i].list[j].subList1[k].rank+'</td>';
								table+='<td>'+result[i].list[j].subList1[k].marginVotes+'</td>';
								table+='<td>'+result[i].list[j].subList1[k].perc+'</td>';
								table+='<td>'+result[i].list[j].subList1[k].crossVotingCount+'</td>';
								if(parseFloat(result[i].list[j].subList1[k].crossVotingPerc)>0){
									table+='<td>'+parseFloat(result[i].list[j].subList1[k].crossVotingPerc).toFixed(2)+'';
									table+='<i class="fa fa-arrow-up" style="color:#3BB878 !important;"></i></td>';
								}else{
									table+='<td>'+parseFloat(result[i].list[j].subList1[k].crossVotingPerc).toFixed(2)+'';
									table+='<i class="fa fa-arrow-down" style="color:#F56666 !important;"></i></td>';
								}
							}
						table+='</tr>';
					}
				}
			}
			table+='</tbody>';
		table+='</table>';
		table+='</div>';
		
		$("#votingDetailsSubLevelBlockId").html(table);
		$("#dataTableCrossExpand").dataTable({
			"iDisplayLength": 10,
			 "aaSorting": [[ 1, "desc" ]], 
			"aLengthMenu": [[10, 30, 50, -1], [10, 30, 50, "All"]]
		});
		
	}
$(document).on("click",".votingDtsClickCls",function(){
	var type = $(this).attr("attr_type");
	if(type == "crossVoting"){
		var locationName = $(this).attr("attr_name");
		var parliamentId = $(this).attr("attr_parliamentId");
		electionYrValForCross=[];
		electionYrValForCross.push($("#electionYearForCrossId").val())
		var partyIdForCross = $("#partyIdForStrongBlock").val();
		
		var electionScopeVal=0;
		 $('.electionTypeWiseCrossCls').each(function(){
			if ($(this).is(':checked')){
				electionScopeVal = $(this).val();
			}
		 });
		 var j = 0;	
		 eletionSubType = [];
		 $(".electionSubTypeCls").each(function(){
			 var type = $(this).attr("attr_type");
			 if(type == "cross"){
				 if ($(this).is(':checked')){
					eletionSubType.push($(this).val());
				}
			 }
		});
		$("#openModalDivId").modal("show");
		$("#titleId").html(locationName+"  Parliament Assembly Wise Cross Voting Report");
		getLocationWiseCrossVotingDetails(eletionSubType,electionYrValForCross,partyIdForCross,electionScopeVal,"expand",parliamentId)
	}
	
});
function getboothWiseResults(electionYrValForBooth,partyIdForBoothArr,constituencyId,electionScopeVal,constituencyName){
	
	$("#boothWiseResultsBlockId").html(spinner);
	$("#locationWiseBoothResultsId").html(spinner);
	var electionYears=[];
	var partyIdsArr=[];
	if(electionYrValForBooth == "null" || electionYrValForBooth == null || electionYrValForBooth == 0 || electionYrValForBooth == "0"){
		electionYears=0;
	}else{
		electionYears = electionYrValForBooth;
	}
	
	if(partyIdForBoothArr == 0){
		partyIdsArr=[872,362,1117,886,72,269,265,163]
	}else{
		partyIdsArr = partyIdForBoothArr;
	}
	
	jsObj={
		constituencyId		:constituencyId,
		partyList			:partyIdsArr,
		electionyears 		:parseInt(electionYears),
		electionScopeId		:electionScopeVal,
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
			buildData(result,constituencyName);
		}else if(result !=null && electionScopeId == 0){
			//return buildBothData(result,electionScopeId);
		}else{
			$("#locationWiseBoothResultsId").html('');
			$("#boothWiseResultsBlockId").html("No Data Available");
		}
		
	});
}
function buildData(result,constituencyName)
	{
		var table = '';
		var table1 = '';
		mainArr = [];
		boothWiseDetailsArr = [];
		mainArr.push(result);
		var isWonCandidateAvailable = false;
		var remainingVotes = 0;
		var remainingPercentage = 0.00;
		var totalVotes = result.partyBoothPerformanceVOList[0].totalValidVotes;
		
		table1+='<h4 class="theme-title-color m_top10">Booth Wise Election Results - '+constituencyName+'</h4>';
		table1+='<div class="row m_top20">';
			table1+='<div>';
			table1+='<div class="col-sm-3">';
				table1+='<h5 class="text-capital m_top10">Assembly Results</h5>';
				table1+='<div id="boothWiseRstsGraphId" style="height:200px;"></div>';
			table1+='</div>';
			table1+='<div class="col-sm-9">';
				table1+='<h5 class="text-capital m_top10">Candidate Details</h5>';
				table1+='<div class="table-responsive m_top10">';
					table1+='<table class="table table_custom table-bordered">';
						table1+='<thead>';
							table1+='<tr>';
								table1+='<th>Candidate Name</th>';
								table1+='<th>Party Name</th>';
								table1+='<th>Total Votes Gained</th>';
								table1+='<th>%</th>';
								table1+='<th>Margin Votes</th>';
							table1+='</tr>';	
						table1+='</thead>';
						table1 +='<tbody>';
						for(var k in result.partyBoothPerformanceVOList)
						{
							if(result.partyBoothPerformanceVOList[k].rank ==1)
							{
								isWonCandidateAvailable = true;
								table1 +='<tr>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
									table1 +='<td >'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
									table1 +='<td >'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
									table1 +='<td >'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
								table1 +='</tr>';
								remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
								remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
							}
							else
							{
								table1 +='<tr>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[k].candidateName+'</td>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[k].partyName+'</td>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[k].votesGained+'</td>';						
									table1 +='<td>'+result.partyBoothPerformanceVOList[k].percentage+'  </td>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[k].marginVotes+'  ( Rank - '+result.partyBoothPerformanceVOList[k].rank+') </td>';
								table1 +='</tr>';
								remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[k].votesGained);
								remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);
							}
																	
						}		
						if(!isWonCandidateAvailable)
							{
								table1 +='<tr>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[0].wonCandidate[0].candidateName+'</td>';
									table1 +='<td>'+result.partyBoothPerformanceVOList[0].wonCandidate[0].partyName+'</td>';
									table1 +='<td >'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned+'</td>';						
									table1 +='<td >'+result.partyBoothPerformanceVOList[0].wonCandidate[0].votesPercengate+'  </td>';
									table1 +='<td >'+result.partyBoothPerformanceVOList[0].wonCandidate[0].marginVotes+' ( Rank - '+result.partyBoothPerformanceVOList[0].wonCandidate[0].rank+') </td>';
								table1 +='</tr>';
								remainingVotes = remainingVotes + parseInt(result.partyBoothPerformanceVOList[0].wonCandidate[0].votesEarned);
								remainingPercentage = parseFloat(remainingPercentage) + parseFloat(result.partyBoothPerformanceVOList[k].percentage);

							}
						
								table1 +='<tr>';
									table1 +='<td> Others </td>';
									table1 +='<td> --  </td>';
									table1 +='<td> '+(totalVotes - remainingVotes)+' </td>';						
									table1 +='<td> '+(parseFloat(100.00) - remainingPercentage).toFixed(2)+' </td>';
									table1 +='<td> --  </td>';
								table1 +='</tr>';
							
					table1 +='</tbody>';
					table1+='</table>';
				table1+='</div>';
			table1+='</div>';
			table1+='</div>';
		table1+='</div>';
		
		var str1='';
		var str='';
		str+='<div class="row">';
		str+='<div class="col-sm-12">';
		str +='<h5 class="text-capital m_top10">Booth Wise Performance</h5>';
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
			str1 +='<table class="table table-bordered table_custom" id="boothWiseResultsPage">';	
			str1 +='<thead>';
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
		
		table+='<div class="row">';
			table+='<div class="col-sm-12">';
				table+='<h5 class="text-capital m_top10">Polling Percentage Range Wise Party Voters Percentage <span class="hideShowBtn" attr_type="polling">+</span></h5>';
				var partiesSize=0;
				table+='<div class="poolingBlockDiv" style="display:none;">';
				table+='<div class="table-responsive m_top10">';
					table+='<table class="table table_custom table-bordered" id="pollingPercTableId" style="width:100%">';
						table+='<thead>';
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
		table+='</div>';
		
		table+='<div class="row">';
			table+='<div class="col-sm-12">';
				table +='<h5 class="text-capital m_top10">Party Votes Percentage Range Wise Polling Percentage <span class="hideShowBtn" attr_type="party">+</span></h5>';
				var partiesSize = 0;
				if(result.perWiseboothResults != null && result.perWiseboothResults.length >0)
				{
					table+='<div class="partyBlockDiv" style="display:none;">';
					table +='<div class="table-responsive m_top20">';
						table +='<table class="table m_top20 table_custom table-majority table-bordered" id="majarityCandidatesTab" style="width:100%">';	
							table +='<thead>';
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
					table +='</div>';
				}
			table+='</div>';
		table+='</div>';
	
		$("#boothWiseResultsBlockId").html(table);
		$("#boothWiseResultsMainBlockId").html(table1);
		$("#locationWiseBoothResultsId").html(str1);
		$("#hideShowlocationId").html(str);
		$("#pollingPercTableId,#majarityCandidatesTab,#boothWiseResultsPage").dataTable({
			"paging"		: true,
			"info"			: false,
			"searching"		: false,
			"autoWidth"		: true,
			"iDisplayLength": 10,
			"aaSorting"		: [],
			"aLengthMenu"	: [[10, 15,20,50, -1], [10, 15, 20,50, "All"]],
			"sDom"			: '<"top"ifl>rt<"bottom"p><"clear">'
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
			str1 +='<table class="table table-bordered m_top10 table_custom" id="boothWiseResultsLocPage">';	
			str1 +='<thead class="">';
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
$(document).on("click",".hideShowBtn",function(){
	var type  = $(this).attr("attr_type");
	var blockType = $(this).html();
	if(blockType == '+')
	{
		$(this).html("-");
	}else{
		$(this).html("+");
	}
	if(type == "polling"){
		
		$(".poolingBlockDiv").toggle();
	}else{
		$(".partyBlockDiv").toggle();
	}

});