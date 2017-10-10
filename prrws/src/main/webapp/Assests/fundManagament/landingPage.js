var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var subSpinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="subSpinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var imagesObj = {
"PRIS":"Group 2344.png","DRAINS":"Group 2345.png","LED MONITORING":"Group 2348.png","UGD":"Group 2359.png","RDP":"Group 2343.png","FUND MANAGMENT SYSTEM":"Group 2352.png","ENGINEERING DEPARTMENT":"Group 2346.png","PANACHAYATI RAJ EXPENDITURE":"Group 2343.png","SPIKE ANALYSIS":"Group 2347.png","MGNREGS":"Group 2357.png","RURAL DEVELOPMENT":"Group 2349.png","RURAL WATER SUPPLY":"Group 2350.png","ITEC":"Group 2351.png","SWATCH BHARATH IHHL":"Group 2348.png",'Labour Budget':"Group 2344.png",'Farm Ponds':"Group 2344.png",'IHHL':"Group 2344.png",'Vermi Compost':"Group 2344.png",'SMC Trench':"Group 2344.png",'Imp to CD':"Group 2344.png",'MPT_PT':"Group 2344.png",'GC Works':"Group 2344.png",'CD_CW':"Group 2344.png",'GH':"Group 2344.png",'Check Dam':"Group 2344.png",'Rock fill dams':"Group 2344.png",'Solid Waste Management':"Group 2344.png",'Burial Ground':"Group 2344.png",'Play fields':"Group 2344.png",'Agriculture Activities':"Group 2344.png",'Average Wage':"Group 2344.png",'Average Days of Employment':"Group 2344.png",'HH Completed 100 Days':"Group 2344.png",'Timely Payment':"Group 2344.png",'CC Roads1':"Group 2344.png",'Anganwadi':"Group 2344.png",'GP Buildings1':"Group 2344.png",'Mandal buildings1':"Group 2344.png",'NTR 90 Days':"Group 2344.png",'Production of Bricks':"Group 2344.png",'Mulbery':"Group 2344.png",'Silk Worms':"Group 2344.png",'Horticulture':"Group 2344.png",'Avenue':"Group 2344.png",'Fish Ponds':"Group 2344.png",'Fish Drying Platforms':"Group 2344.png",'NURSERIES':"Group 2344.png",'Payments':"Group 2344.png",'FAperformance':"Group 2344.png",'OPGK-Perinnials':"Group 2344.png",'OPGK-Annuals':"Group 2344.png",'UGDrainage':"Group 2344.png"
}

var blockClassObject = {
"PRIS":"prisOverAchvmntAllCls","DRAINS":"drainsOverAchvmntAllCls","LED MONITORING":"ledOverAchvmntAllCls","UGD":"","RDP":"","FUND MANAGMENT SYSTEM":"fundMngmntSstmOverAchvmntAllCls","ENGINEERING DEPARTMENT":"encOverAchvmntAllCls","PANACHAYATI RAJ EXPENDITURE":"preOverAchvmntAllCls","SPIKE ANALYSIS":"spikeOverAchvmntAllCls","MGNREGS":"mgnregsOverAchvmntAllCls","RURAL DEVELOPMENT":"rdOverAchvmntAllCls","RURAL WATER SUPPLY":"rwsOverAchvmntAllCls","ITEC":"itecOverAchvmntAllCls","SWATCH BHARATH IHHL":"swatchBharathIHHL","SWATCH BHARATH PAYMENTS":"swatchBharathPayments",'Labour Budget':'LabourBudgetAllCls','Farm Ponds':'FarmPondsAllCls','IHHL':'IHHLAllCls','Vermi Compost':'VermiCompostAllCls','SMC Trench':'SMCTrenchAllCls','Imp to CD':'ImptoCDAllCls','MPT_PT':'MPT_PTAllCls','GC Works':'GCWorksAllCls','CD_CW':'CD_CWAllCls','GH':'GHAllCls','Check Dam':'CheckDamAllCls','Rock fill dams':'RockfilldamsAllCls','Solid Waste Management':'SolidWasteManagementAllCls','Burial Ground':'BurialGroundAllCls','Play fields':'PlayfieldsAllCls','Agriculture Activities':'AgricultureActivitiesAllCls','Average Wage':'AverageWageAllCls','Average Days of Employment':'AverageDaysofEmploymentAllCls','HH Completed 100 Days':'HHCompleted100DaysAllCls','Timely Payment':'TimelyPaymentAllCls','CC Roads1':'CCRoads1AllCls','Anganwadi':'AnganwadiAllCls','GP Buildings1':'GPBuildings1AllCls','Mandal buildings1':'Mandalbuildings1AllCls','NTR 90 Days':'NTR90DaysAllCls','Production of Bricks':'ProductionofBricksAllCls','Mulbery':'MulberyAllCls','Silk Worms':'SilkWormsAllCls','Horticulture':'HorticultureAllCls','Avenue':'AvenueAllCls','Fish Ponds':'FishPondsAllCls','Fish Drying Platforms':'FishDryingPlatformsAllCls','Nurseries':'NurseriesAllCls','Payments':'PaymentsAllCls','FAperformance':'FAperformanceAllCls','OPGK-Perinnials':'OPGK-PerinnialsAllCls','OPGK-Annuals':'OPGK-AnnualsAllCls','UGDrainage':'UGDrainageAllCls'
}
var blockHeadingObject = {
"PRIS":"ACHIEVEMENT","DRAINS":"ACHIEVEMENT","LED MONITORING":"TOTAL LIGHTS","UGD":"ACHIEVEMENT","RDP":"ACHIEVEMENT","FUND MANAGMENT SYSTEM":"TOTAL FUNDS","ENGINEERING DEPARTMENT":"ACHIEVEMENT","PANACHAYATI RAJ EXPENDITURE":"GROSS-AMOUNT","SPIKE ANALYSIS":"TOTAL CASES","MGNREGS":"ACHIEVEMENT","RURAL DEVELOPMENT":"ACHIEVEMENT","RURAL WATER SUPPLY":"ACHIEVEMENT","ITEC":"TOTAL TRANSACTIONS","SWATCH BHARATH IHHL":"COMPLETED","SWATCH BHARATH PAYMENTS":"PENDING",'Labour Budget':"ACHEIVED",'Farm Ponds':"ACHEIVED",'IHHL':"ACHEIVED",'Vermi Compost':"ACHEIVED",'SMC Trench':"ACHEIVED",'Imp to CD':"ACHEIVED",'MPT_PT':"ACHEIVED",'GC Works':"ACHEIVED",'CD_CW':"ACHEIVED",'GH':"ACHEIVED",'Check Dam':"ACHEIVED",'Rock fill dams':"ACHEIVED",'Solid Waste Management':"ACHEIVED",'Burial Ground':"ACHEIVED",'Play fields':"ACHEIVED",'Agriculture Activities':"ACHEIVED",'Average Wage':"ACHEIVED",'Average Days of Employment':"ACHEIVED",'HH Completed 100 Days':"ACHEIVED",'Timely Payment':"ACHEIVED",'CC Roads1':"ACHEIVED",'Anganwadi':"ACHEIVED",'GP Buildings1':"ACHEIVED",'Mandal buildings1':"ACHEIVED",'NTR 90 Days':"ACHEIVED",'Production of Bricks':"ACHEIVED",'Mulbery':"ACHEIVED",'Silk Worms':"ACHEIVED",'Horticulture':"ACHEIVED",'Avenue':"ACHEIVED",'Fish Ponds':"ACHEIVED",'Fish Drying Platforms':"ACHEIVED",'Nurseries':"ACHEIVED",'Payments':"ACHEIVED",'FAperformance':"ACHEIVED",'OPGK-Perinnials':"ACHEIVED",'OPGK-Annuals':"ACHEIVED",'UGDrainage':"ACHEIVED"
}
var overViewArrConsolidated = [];
var overViewIdsArr = [];
var windowWidth =$(window).width();
$("#editList,#saveList").tooltip();
if(windowWidth <500){
	$(".landing-menu").css("display","");
	$(".landing-menu li").css("width","100%");
}else{
	$(".landing-menu").css("display","flex");
	$(".landing-menu li").css("width","16%");
}
getAllConvergenceTypesConsolidated();//mgnrega components

var favouritesArr = []
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
		str +='<div id="sortableList">';
		for (var i in result) {
			var compnentName = result[i].name.trim();
			if (result[i].name == "IT E & C") {
				compnentName = "ITEC";
			}
			var componentNameWithoutSpace = compnentName.replace(/\s+/g, '');
			str +='<div class="col-sm-4 draggable-element" order-by="'+result[i].id+'">'
				str +='<div class="whiteBlock">';
					str +='<img src="Assests/img/'+imagesObj[compnentName]+'" >';
					str +='<h5 style="display: inline-block;text-transform:uppercase;">'+result[i].name+'</h5>';
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
		str +='</div>';
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
$(document).on("click","#saveList",function(){
	
	var orderList = []
	$(".draggable-element").each(function(){
		orderList.push($(this).attr("order-by"));
	});
	console.log(orderList)
	var json = {
		componentIds : orderList
	}
	$.ajax({ 
		type:'POST',    
		url: 'saveFavouriteComponentOrderDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result.statusCode==0 && result.message=="success"){
			$("#errorDivId").html("Your Priorities Saved Successfully")
			$("#saveList").hide();
			$("#editList").show();
			Sortable.create(sortableList).destroy();
			setTimeout(function(){
				$("#errorDivId").html(" ")
			},2000)
		}
		 
		 
	});	
	
	
});
$(document).on("click","#editList",function(){
	$("#saveList").show();
	$("#editList").hide();
	Sortable.create(sortableList);
});

$(".showhideCls").css("display","none");
$(document).on("click","[landing-link]",function(){
	$("[landing-link]").removeClass("active");
	$(this).addClass("active");
	$("#showMainBlock").css("display","none")
	var blockName = $(this).attr("landing-link");
	$("[landing-block]").hide();
	$("[landing-block="+blockName+"]").show();
	if (blockName == "favourite") {
		getFavouriteComponents();
	}
});	
	
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
var overViewArr = ['Labour Budget','Farm Ponds','IHHL','Vermi Compost','SMC Trench','Imp to CD','MPT_PT','GC Works','CD_CW','GH','Check Dam','Rock fill dams','Solid Waste Management','Burial Ground','Play fields','Agriculture Activities','Average Wage','Average Days of Employment','HH Completed 100 Days','Timely Payment','CC Roads1','Anganwadi','GP Buildings1','Mandal buildings1','NTR 90 Days','Production of Bricks','Mulbery','Silk Worms','Horticulture','Avenue','Fish Ponds','Fish Drying Platforms','Nurseries','Payments','FAperformance','OPGK-Perinnials','OPGK-Annuals','UGDrainage'];
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
	getIHHLOverviewData();//swatch Bharath IHHL
	getSBPaymentsAbstract();//SWATCH BHARATH PAYMENTS
	for(var i in overViewArr)
	{
		if(overViewArr[i] == 'NTR 90 Days' || overViewArr[i] == 'Production of Bricks' || overViewArr[i] == 'Mulbery' || overViewArr[i] == 'Silk Worms' || overViewArr[i] == 'Cattle Drinking Water Troughs' || overViewArr[i] == 'Raising of Perinnial Fodders' || overViewArr[i] == 'Fish Ponds' || overViewArr[i] == 'Fish Drying Platforms' || overViewArr[i] == 'NTR Rural House' || overViewArr[i] == 'OPGK-Perinnials' || overViewArr[i] == 'OPGK-Annuals')
		{
			getNREGSProjectsAbstractNew(overViewArr[i]);
		}else if(overViewArr[i] == 'Payments')
		{
			getNregaPaymentsAbsAndOverviewDtls(overViewArr[i]);
		}else{
			getNREGSAbstractDataByType(overViewArr[i]);
		}
	}

}

function getPrisOverAllAchievd(){
		 $(".prisOverAchvmntAllCls").html(spinner);
		var locationType='district';
		var locationId=0;
		var json = {
			fromDate:"01-01-1997",
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
			$(".prisOverAchvmntAllCls").html("0%");
			if( result != null){
				if(result.achievedOverallpercent != null && result.achievedOverallpercent > 0){
					$(".prisOverAchvmntAllCls").html(result.achievedOverallpercent+"%");
				}
			}
		});
	}
	
  function getDrainsInfoStateWise(){
	$(".drainsOverAchvmntAllCls").html(spinner);
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
			$(".drainsOverAchvmntAllCls").html("0%");
			if(result != null){
				if(result.percentage != null && result.percentage > 0){
					$(".drainsOverAchvmntAllCls").html(result.percentage+"%");
				}
			}
		});
}

var gblSpikeFmsITCEndDate = moment().format('DD/MM/YYYY');
function getTotalSpikeCases(){
	 $(".spikeOverAchvmntAllCls").html(spinner); 
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
		  $(".spikeOverAchvmntAllCls").html(0);
		  if(ajaxresp != null && ajaxresp.length > 0){
			 $(".spikeOverAchvmntAllCls").html(ajaxresp[0].count);
		  }
     }
  });
}

function getTotalPrExpenditure(){
	$(".preOverAchvmntAllCls").html(spinner);
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
		  $(".preOverAchvmntAllCls").html(0);
		  if(ajaxresp != null){
			 $(".preOverAchvmntAllCls").html(ajaxresp.grossAmount);
		  }
	  }
    });
}

function getBasicLedOverviewDetails(){
	 $(".ledOverAchvmntAllCls").html(spinner);	
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
		 $(".ledOverAchvmntAllCls").html(0);
		if (result != null ) {
		  $(".ledOverAchvmntAllCls").html(result[0].totalLights);
		}
	});		
}

function getHabitationCoverageByStatusByState()
{
	 $(".rwsOverAchvmntAllCls").html(spinner);
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
					$(".rwsOverAchvmntAllCls").html("0"+"%");
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
	  $(".fundMngmntSstmOverAchvmntAllCls").html(spinner);
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
			$(".fundMngmntSstmOverAchvmntAllCls").html(0);
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
	 $(".itecOverAchvmntAllCls").html(spinner);
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
		$(".itecOverAchvmntAllCls").html(0);
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
	$(".mgnregsOverAchvmntAllCls").html(spinner);
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
			$(".mgnregsOverAchvmntAllCls").html("0"+"%");
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
	$(".rdOverAchvmntAllCls").html(spinner);
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
			$(".rdOverAchvmntAllCls").html("0%");
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
function getIHHLOverviewData(){
	$(".swatchBharathIHHL").html(spinner);
	var json = {
			fromMonth	:"201704",
			toMonth		:"201707",
			location	:"state",
			locationId	:"01"
		}
	$.ajax({                
		type:'POST',    
		url: 'getIHHLOverviewData',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		var totalCount = result.subList1[0].target+result.subList1[0].completed;
			totalCount = (result.subList1[0].completed / totalCount) * 100;
		$(".swatchBharathIHHL").html(totalCount.toFixed(2)+'%');
	});
}
function getSBPaymentsAbstract(){
	$(".swatchBharathPayments").html(spinner);
	var json = {
		//fromDate:"201704",
		//toDate:"201708",
		location:"state",
		locationId:"01",
		subLocation :"state"
			
	}
	$.ajax({                
		type:'POST',    
		url: 'getSBPaymentsAbstract',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$(".swatchBharathPayments").html(result.pendingAmount);
	});
}
function getAllConvergenceTypesConsolidated()
{
	var json = {
	}
	
	$.ajax({
		url: 'getAllConvergenceTypes',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildAllConvergenceTypes(ajaxresp);
		}
	});
}
function buildAllConvergenceTypes(result)
{
	var selectionMenu = '';
	selectionMenu+='<div class="navTabsMenuSelection">';
		selectionMenu+='<div class="col-sm-6">';
			selectionMenu+='<ul class="nav nav-tabs" role="tablist">';
				for(var i in result)
				{
					if(i == 0)
					{
						selectionMenu+='<li role="presentation" class="active"><a href="#selectionMenuId'+result[i].id+'" aria-controls="selectionMenuId'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+'</a></li>';
					}else{
						selectionMenu+='<li role="presentation"><a href="#selectionMenuId'+result[i].id+'" aria-controls="selectionMenuId'+result[i].id+'" role="tab" data-toggle="tab">'+result[i].name+'</a></li>';
					}
				}
			selectionMenu+='</ul>';
		selectionMenu+='</div>';
		selectionMenu+='<div class="col-sm-6">';
			selectionMenu+='<div class="tab-content">';
				for(var i in result)
				{
					if(i == 0)
					{
						selectionMenu+='<div role="tabpanel" class="tab-pane active" id="selectionMenuId'+result[i].id+'">A'+result[i].id+'</div>';
					}else{
						selectionMenu+='<div role="tabpanel" class="tab-pane" id="selectionMenuId'+result[i].id+'">A'+result[i].id+'</div>';
					}
				}
				
			selectionMenu+='</div>';
		selectionMenu+='</div>';
	selectionMenu+='</div>';
	$("#navTabsMenuSelectionId").html(selectionMenu);
	for(var i in result)
	{
		var convergenceId = result[i].id;
		var divId = 'selectionMenuId'+result[i].id;
		getComponentByConvergType(convergenceId,divId)
		if(i == 2)
		{
			getFavouriteComponents();
		}
	}
}
$(".menu-top-selection .arrow_box_top").hide();
$(document).on("click",".menu-top-selection-icon",function(e){
	e.stopPropagation();
	//$(".menu-top-selection .arrow_box_top").show();
	$(".menu-top-selection .arrow_box_top").toggle();
});
$(document).on("click",".menu-top-selection",function(e){
	e.stopPropagation();
});
$(document).on("click",function(){
	$(".multi-level-selection-menu,.menu-top-selection .arrow_box_top").hide();
});
function getComponentByConvergType(convergenceId,divId)
{
	$("#"+divId).html(spinner);
	var json = {
		convergenceTypeId : convergenceId
	}
	
	$.ajax({
		url: 'getComponentByConvergType',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			buildComponentByConvergType(ajaxresp,divId,convergenceId);
		}
	});
}
function buildComponentByConvergType(result,divId,convergenceId)
{
	var selectionMenu = '';
	
	selectionMenu+='';
	selectionMenu+='<ul class="menu-selection-body">';
		/* selectionMenu+='<li>';
			selectionMenu+='<label class="checkbox-inline"><input type="checkbox" class="menuSelectionCheckBox selectAllCheckbox" attr_selectAll="'+divId+'"/>Select All</label>';
		selectionMenu+='</li>'; */
		for(var i in result)
		{
			selectionMenu+='<li>';
				selectionMenu+='<label><i class="fa fa-star starcolorChange '+result[i].name.replace(/\s+/g, '')+'Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component='+result[i].name+'" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="'+result[i].name.replace(/\s+/g, '')+'" aria-hidden="true"></i>'+result[i].name+'</label>';
			selectionMenu+='</li>';
			overViewArrConsolidated.push({"name":result[i].name,"id":result[i].id});
			overViewIdsArr.push(result[i].id);
		}		
	selectionMenu+='</ul>';
	$("#"+divId).html(selectionMenu);
	
}
function getNREGSAbstractDataByType(type)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	if(type == 'FAperformance')
	{
		var json = {
			year : "2017",
			fromDate : '2017-04-01',
			toDate : '2017-05-30',
			type : type,
			locationType: "state",
			locationId : "-1"
		}
	}else{
		var json = {
			year : "2017",
			fromDate : '2017-04-01',
			toDate : moment().format("YYYY-MM-DD"),
			type : type,
			locationType: "state",
			locationId : "-1"
		}
	}
	
	
	$.ajax({
		url: 'getNREGSAbstractDataByType',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+"%");
		}
	}); 
}
function getNregaPaymentsAbsAndOverviewDtls(type)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	var json = {
		year : "2017",
		fromDate : '2017-04-01',
		toDate :  moment().format("YYYY-MM-DD"),
		locationType: "state",
		locationId :  "-1",
		sublocaType :"state"
	}
	$.ajax({
		url: 'getNregaPaymentsAbsAndOverviewDtls',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+"%");
		}
	});
}
function getNREGSProjectsAbstractNew(type)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	var json = {
		year : "2017",
		fromDate : '2017-04-01',
		toDate : moment().format("YYYY-MM-DD"),
		type : type,
		locationType: "state",
		locationId :  "-1"
	}
	$.ajax({
		url: 'getNREGSProjectsAbstractNew',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+"%");
		}
	});
}

