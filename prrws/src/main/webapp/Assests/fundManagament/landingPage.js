var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

var imagesObj = {
"PRIS":"Group 2344.png","DRAINS":"Group 2345.png","LED MONITORING":"Group 2348.png","UGD":"Group 2359.png","RDP":"Group 2343.png","FUND MANAGMENT SYSTEM":"Group 2352.png","ENGINEERING DEPARTMENT":"Group 2346.png","PANACHAYATI RAJ EXPENDITURE":"Group 2343.png","SPIKE ANALYSIS":"Group 2347.png","MGNREGS":"Group 2357.png","RURAL DEVELOPMENT":"Group 2349.png","RURAL WATER SUPPLY":"Group 2350.png","ITEC":"Group 2351.png"
}
var blockClassObject = {
"PRIS":"prisOverAchvmntAllCls","DRAINS":"drainsOverAchvmntAllCls","LED MONITORING":"ledOverAchvmntAllCls","UGD":"","RDP":"","FUND MANAGMENT SYSTEM":"fundMngmntSstmOverAchvmntAllCls","ENGINEERING DEPARTMENT":"encOverAchvmntAllCls","PANACHAYATI RAJ EXPENDITURE":"preOverAchvmntAllCls","SPIKE ANALYSIS":"spikeOverAchvmntAllCls","MGNREGS":"mgnregsOverAchvmntAllCls","RURAL DEVELOPMENT":"rdOverAchvmntAllCls","RURAL WATER SUPPLY":"rwsOverAchvmntAllCls","ITEC":"itecOverAchvmntAllCls"
}
var blockHeadingObject = {
"PRIS":"ACHIEVEMENT","DRAINS":"ACHIEVEMENT","LED MONITORING":"TOTAL LIGHTS","UGD":"ACHIEVEMENT","RDP":"ACHIEVEMENT","FUND MANAGMENT SYSTEM":"TOTAL FUNDS","ENGINEERING DEPARTMENT":"ACHIEVEMENT","PANACHAYATI RAJ EXPENDITURE":"GROSS-AMOUNT","SPIKE ANALYSIS":"TOTAL CASES","MGNREGS":"ACHIEVEMENT","RURAL DEVELOPMENT":"ACHIEVEMENT","RURAL WATER SUPPLY":"ACHIEVEMENT","ITEC":"TOTAL TRANSACTIONS"
}
var windowWidth =$(window).width();
if(windowWidth <500){
	$(".landing-menu").css("display","");
	$(".landing-menu li").css("width","100%");
}else{
	$(".landing-menu").css("display","flex");
	$(".landing-menu li").css("width","16%");
}
getFavouriteComponents();
function getFavouriteComponents(){
	$("#favouriteComponentDivId").html(spinner);
	var json = {}
	$.ajax({                
		type:'GET',    
		url: 'getFavouriteComponents',
		dataType: 'json',
		data : JSON.stringify(),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 $("#favouriteComponentDivId").html('');
		 if (result != null && result.length > 0) {
			 buildFavouriteComponentsResult(result);
		 } else {
			 $("#favouriteComponentDivId").html("NO Favourite Component.");
		 }
	});		
}
function buildFavouriteComponentsResult(result) {
	   var str = '';
	   for (var i in result) {
		var compnentName = result[i].name.trim();
		if (result[i].name == "IT E & C") {
			compnentName = "ITEC";
		}
		var componentNameWithoutSpace = compnentName.replace(/\s+/g, '');
		str +='<div class="col-sm-4">'
				str +='<div class="whiteBlock">';
					str +='<img src="Assests/img/'+imagesObj[compnentName]+'" >';
					str +='<h5 style="display: inline-block;">'+result[i].name+'</h5>';
					str +='<div class=" " style="text-align: right">';
						str +='<h2 class="'+blockClassObject[compnentName]+'" style="margin-top: 0px"></h2>';
						str +='<p class="">'+blockHeadingObject[compnentName]+'</p>';
					str +='</div>';
					str +='<div class="block-footer" style="border-top: 1px solid lightgrey;padding-top: 5px">';
						str +='<i class="fa fa-star starcolorChange '+componentNameWithoutSpace+'Color" attr_url="'+result[i].url
						+'" attr_full_block_name="'+compnentName+'" attr_color_name="red" attr_block_name="'+componentNameWithoutSpace+'" aria-hidden="true"></i>';
						str +='<a class="pull-right" href="'+result[i].url+'" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>';
					str +='</div>';
				str +='</div>';
			str +='</div>';
	}
	$("#favouriteComponentDivId").html(str);
	/*adding required filed dynamically*/
	for (var i in result) { 
		var compnentName = result[i].name.trim();;
		if (result[i].name == "IT E & C") {
			compnentName = "ITEC";
		}
		var componentNameWithoutSpace = compnentName.replace(/\s+/g, '');
		$("."+componentNameWithoutSpace+"Color").css("color","red");
		$("."+componentNameWithoutSpace+"Color").attr("attr_block_id",result[i].id);
		$("."+componentNameWithoutSpace+"Color").attr("attr_color_name","red");
		$("."+componentNameWithoutSpace+"Color").attr("title","click to remove from favourite list.");
	}
	onloadCallToGetAllBlockAchievent();
}

$(".showhideCls").css("display","none");
$(document).on('click','.landingmenu-block ul li',function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		$("#showMainBlock").css("display","none")
		var listId = $(this).attr("id");
		var eq = $(this).index();
		$(".showhideCls").removeClass("showBlock");
		$(".showhideCls").eq(eq).addClass("showBlock");
		$(".showhideCls").css("display","none");
		$(".showBlock").css("display","block");
		if (listId == "favourite") {
			getFavouriteComponents();
		}
	})
	
	
	
$(document).on('click','.starcolorChange',function(){
   var blockName = $(this).attr("attr_block_name");
   var colorName = $(this).attr("attr_color_name");
   var fullBlockName = $(this).attr("attr_full_block_name");
   var blockId = $(this).attr("attr_block_id");
   var url = $(this).attr("attr_url");
	if(colorName == "gray"){
		$("."+blockName+"Color").removeClass("removeFav");
		addRemoveComponentToFavourite('Add',blockName,fullBlockName,url,blockId);
	}else{
		$("."+blockName+"Color").addClass("removeFav");
		addRemoveComponentToFavourite('Remove',blockName,fullBlockName,url,blockId);
	}
})

var saveFlag = true;
var deleteFlag = true;
function addRemoveComponentToFavourite(actionType,blockName,fullBlockName,url,blockId){
	if (actionType == "Add") {
		if (saveFlag == true) {
			saveFlag = false;
		    saveFavouriteComponentDtls(url,blockName,fullBlockName);	
		}
		
	} else if (actionType == "Remove") {
		if (deleteFlag == true) {
			deleteFlag = false;
		  deleteFavouriteComponent(blockName,blockId)	
		}
		
	}
}

function saveFavouriteComponentDtls(url,blockName,fullBlockName){
	  $("#blockOperationStatusHeadingId").html(spinner);
	  $("#blockModalMessageDivId").modal("show");
	var json = {
		  url:url, 
		  name:fullBlockName
		}
	$.ajax({                
		type:'POST',    
		url: 'saveFavouriteComponentDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		  saveFlag = true;
		if (result.statusCode==0 && result.message=="success"){
		     $("."+blockName+"Color").css("color","red");
			  $("."+blockName+"Color").attr("attr_color_name","red");
			  $("#blockOperationStatusHeadingId").html("Block Successfully added as favourite.");
			  $("."+blockName+"Color").attr("title","click to remove from favourite list.");
			  $("#blockModalMessageDivId").modal("show");
			  setTimeout(function(){ $("#blockModalMessageDivId").modal("hide"); }, 2000);
			  getFavouriteComponents();
		 } else {
			 alert("Something went wrong,Please try again.");
		 }
	});		
}
function deleteFavouriteComponent(blockName,blockId){
	 $("#blockOperationStatusHeadingId").html(spinner);
	 $("#blockModalMessageDivId").modal("show");
	if (blockId == 0 || blockId == undefined){
		return ;
	}
	var json = {
		id:blockId
	}
	$.ajax({                
		type:'POST',    
		url: 'deleteFavouriteComponent',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		  deleteFlag = true;
		if (result.statusCode==0 && result.message=="success"){
			   $("."+blockName+"Color").css("color","gray");
			   $("."+blockName+"Color").attr("attr_color_name","gray");
			   $("#blockOperationStatusHeadingId").html("Block removed from favourite list.");
			   $("#blockModalMessageDivId").modal("show");
			   setTimeout(function(){ $("#blockModalMessageDivId").modal("hide"); }, 2000);
			   $("."+blockName+"Color").attr("title","click to add as favourite component");
			 	 getFavouriteComponents();
		 } else {
			    alert("Something went wrong,Please try again.");
		 }
	});		
}

var gblEndDate =  moment().format('DD-MM-YYYY');

function onloadCallToGetAllBlockAchievent () {
	getPrisOverAllAchievd(); // Pris
	getDrainsInfoStateWise();//Drains
	getTotalSpikeCases();//SPIKE 
	getTotalPrExpenditure();//PR EXPENDITURE
	getBasicLedOverviewDetails(); // LED 
	getHabitationCoverageByStatusByState();//FMS 
	getALlProgramesAmountDetails();//FMS 
	getMeesevaSLAOverviewDtls();//ITEC 
	getNregsLabourBudgetOverAllAchievent();//MGNREGS
	getRuralDevelopmentAllAchievent();//Rural DEVELOPMENT
}

function getPrisOverAllAchievd(){
		 $(".prisOverAchvmntAllCls").html("0%");
		var locationType='district';
		var locationId=0;
		var json = {
			fromDate:"01-09-2017",
			toDate:gblEndDate,
			locationId:locationId,
			locationType:locationType,
			type:"Overall"
			}
		$.ajax({                
			type:'POST',    
			url: 'getPrisSurveyBasicData',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if( result != null){
				if(result.achievedOverallpercent != null && result.achievedOverallpercent > 0){
					$(".prisOverAchvmntAllCls").html(result.achievedOverallpercent+"%");
				}
			}
		});
	}
	
  function getDrainsInfoStateWise(){
	$(".drainsOverAchvmntAllCls").html("0%");
	var json = {
			fromDate : "01-01-2002",
			toDate : gblEndDate,
			locationType : "district" ,
			locationId:0
		}
		$.ajax({                
			type:'POST',    
			url: 'getDrainsInfoStateWise',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result != null){
				if(result.percentage != null && result.percentage > 0){
					$(".drainsOverAchvmntAllCls").html(result.percentage+"%");
				}
			}
		});
}

var gblSpikeFmsITCEndDate = moment().format('DD/MM/YYYY');
function getTotalSpikeCases(){
	 $(".spikeOverAchvmntAllCls").html(0); 
	var diseasesIdArr=[];
	diseasesIdArr.push(1);  
	diseasesIdArr.push(2);
	var json = {
		fromDate : "01/01/1997",
		toDate : gblSpikeFmsITCEndDate , 
		diseasesIdList : diseasesIdArr
    }
    $.ajax({
      url : "getCaseCountDiseasesWise",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null && ajaxresp.length > 0){
			 $(".spikeOverAchvmntAllCls").html(ajaxresp[0].count);
		  }
     }
  });
}

function getTotalPrExpenditure(){
	$(".preOverAchvmntAllCls").html(0);
	var json = {
	    filterType:"",
		locationIds:[]
    }
    $.ajax({
      url : "getTotalAmountForOverview",       
      data : JSON.stringify(json),
      type : "POST",  
      dataTypa : 'json',   
      beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
      },
      success : function(ajaxresp){
		  if(ajaxresp != null){
			 $(".preOverAchvmntAllCls").html(ajaxresp.grossAmount);
		  }
	  }
    });
}

function getBasicLedOverviewDetails(){
	 $(".ledOverAchvmntAllCls").html(0);	
	var locationType="";
	var locationValue=0;
	var json = {
		fromDate:gblEndDate, // for led start date and end date is smae
		toDate:gblEndDate,
		locationType:locationType,
		locationValue:locationValue
	}
	$.ajax({                
		type:'POST',    
		url: 'getBasicLedOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result != null ) {
		  $(".ledOverAchvmntAllCls").html(result[0].totalLights);
		}
	});		
}

function getHabitationCoverageByStatusByState()
{
	 $(".rwsOverAchvmntAllCls").html("0"+"%");
	 var fullyCoveredHabitationPer=0;
			var json = {
				fromDateStr:"01-01-1977",
				toDateStr:gblEndDate,
				locationType:"state",
				year:gblEndDate.split("-")[2],
				filterType:"",
				filterValue:"",
				districtValue:"",
				type:"graph"
			}
			$.ajax({
				url: 'getHabitationCoverageByStatusByLocationType',
				data: JSON.stringify(json),
				type: "POST",
				dataType: 'json', 
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json");
				},
				success: function(response){
					if(response !=null && response.length>0){
						for(var i in response){
						  for(var j in response[i].statusList){
							if(response[i].statusList[j].status == "FC"){
								fullyCoveredHabitationPer = response[i].statusList[j].percentage;
							}
						 }
						}
				     }
					 $(".rwsOverAchvmntAllCls").html(fullyCoveredHabitationPer+"%");
				}
			});
}

  function getALlProgramesAmountDetails(){
	  $(".fundMngmntSstmOverAchvmntAllCls").html(0);
		var json = {
			financialYrIdList:[0],
			deptIdsList : [0],
			sourceIdsList : [],
			fromDateStr : "01/01/1997",       
			toDateStr : gblSpikeFmsITCEndDate,		
			searchLevelId:0,
			searchLvlVals:[]
			
		}
		$.ajax({
			url : "getALlProgramesAmountDetails",     
			data : JSON.stringify(json),
			type : "POST",  
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(result){  
				if(result !=null && result.length>0){
					var  totalAmount = 0;
					for(var i in result){
						if (result[i].id == 0) {
							continue;
						}
						if(result[i].count !=null && result[i].count>0){
							totalAmount = totalAmount + result[i].count;
						}
					}
					var amountinCrocr = totalAmount/10000000;
					if (amountinCrocr != null && amountinCrocr > 0) {
						$(".fundMngmntSstmOverAchvmntAllCls").html(amountinCrocr.toFixed(3)+"&nbspCR");
					}
			   }
			}
		  });
	}
	
 function getMeesevaSLAOverviewDtls(){
	 $(".itecOverAchvmntAllCls").html(0);
	 var totalTransaction = 0;
	 var json = {
	    fromDate:"01/01/1997",
		toDate:gblSpikeFmsITCEndDate,
		filterId:"2",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLAOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result != null && result.length>0) {
			for(var i in result){
				if(result[i].name == "TOTAL TRANSACTIONS"){
					totalTransaction = result[i].totalCount;
				}
			}
			$(".itecOverAchvmntAllCls").html(totalTransaction);
		}
	});		
} 
var nregsDate = moment().format('YYYY-MM-DD');
function getNregsLabourBudgetOverAllAchievent()
{
	$(".mgnregsOverAchvmntAllCls").html("0"+"%");
	var json = {
		year : "2017",
		fromDate : "2016-04-31",
		toDate : nregsDate,
		locationType: "state",
		divType : "Labour Budget",
		locationId : "-1",
		sublocaType : "state",
		districtId:""
	}
	$.ajax({
		url: 'getNregaLevelwiseOverviewForLabourBudgetData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			var labourBudgetPer=0;
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					if (ajaxresp[i].perAppLB != null && ajaxresp[i].perAppLB > 0) {
						labourBudgetPer = ajaxresp[i].perAppLB;
					}
				}
			}
			$(".mgnregsOverAchvmntAllCls").html(labourBudgetPer+"%");
		}
	});
}
	
function getRuralDevelopmentAllAchievent()
{
	$(".rdOverAchvmntAllCls").html("0%")
	 var json = {
		year : "2017",
		fromDate : "2016-04-31",
		toDate : nregsDate,
		locationType: "state",
		divType : "WaterBudget",
		locationId : "-1",
		sublocaType : "state"
	}
	$.ajax({
		url: 'getRDLevelsWiseData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					if (ajaxresp[i].percSant != null && ajaxresp[i].percSant>0) {
						$(".rdOverAchvmntAllCls").html(ajaxresp[i].percSant+"%");
					}
				}
			}
		} 
	});
}