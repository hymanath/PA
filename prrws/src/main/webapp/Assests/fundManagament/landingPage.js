var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var subSpinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="subSpinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
var imagesObj = {
"PRIS":"Group 2344.png","DRAINS":"Group 2345.png","LED MONITORING":"Group 2348.png","UGD":"Group 2359.png","RDP":"Group 2343.png","FUND MANAGMENT SYSTEM":"Group 2352.png","ENGINEERING DEPARTMENT":"Group 2346.png","PANACHAYATI RAJ EXPENDITURE":"Group 2343.png","SPIKE ANALYSIS":"Group 2347.png","MGNREGS":"Group 2357.png","RURAL WATER SUPPLY":"Group 2350.png","ITEC":"Group 2351.png","SWATCH BHARATH IHHL":"Group 2348.png",'Labour Budget':"Group 2344.png",'Farm Ponds':"Group 2344.png",'IHHL':"Group 2344.png",'Vermi Compost':"Group 2344.png",'SMC Trench':"Group 2344.png",'Imp to CD':"Group 2344.png",'MPT_PT':"Group 2344.png",'GC Works':"Group 2344.png",'CD_CW':"Group 2344.png",'GH':"Group 2344.png",'Check Dam':"Group 2344.png",'Rock fill dams':"Group 2344.png",'Solid Waste Management':"Group 2344.png",'Burial Ground':"Group 2344.png",'Play fields':"Group 2344.png",'Agriculture Activities':"Group 2344.png",'Average Wage':"Group 2344.png",'Average Days of Employment':"Group 2344.png",'HH Completed 100 Days':"Group 2344.png",'Timely Payment':"Group 2344.png",'CC Roads1':"Group 2344.png",'Anganwadi':"Group 2344.png",'GP Buildings1':"Group 2344.png",'Mandal buildings1':"Group 2344.png",'NTR 90 Days':"Group 2344.png",'Production of Bricks':"Group 2344.png",'Mulbery':"Group 2344.png",'Silk Worms':"Group 2344.png",'Horticulture':"Group 2344.png",'Avenue':"Group 2344.png",'Fish Ponds':"Group 2344.png",'Fish Drying Platforms':"Group 2344.png",'NURSERIES':"Group 2344.png",'Payments':"Group 2344.png",'FAperformance':"Group 2344.png",'OPGK-Perinnials':"Group 2344.png",'OPGK-Annuals':"Group 2344.png",'UGDrainage':"Group 2344.png",'Ntr Jalasiri':"Group 2344.png",'WaterBudget':"Group 2350.png","SWATCH BHARATH PAYMENTS":"Group 2352.png","JALAVANI":"Jalavani.png","JALAVANI":"Jalavani.png",'ASSETS':"assets","WATER SOURCE":"water source.png","WORKS":"works.png","PROMOTIONS":"promotions.png","E OFFICE":"eOffice.png","MEESEVA - SLA":"meeSevaSla.png","AP INNOVATION SOCIETY":"APInnovationSoc.png","MEESEVA & KPI":"MeeSevaKPI.png"
}

var blockClassObject = {
"PRIS":"prisOverAchvmntAllCls","DRAINS":"drainsOverAchvmntAllCls","LED MONITORING":"ledOverAchvmntAllCls","UGD":"","RDP":"","FUND MANAGMENT SYSTEM":"fundMngmntSstmOverAchvmntAllCls","ENGINEERING DEPARTMENT":"encOverAchvmntAllCls","PANACHAYATI RAJ EXPENDITURE":"preOverAchvmntAllCls","SPIKE ANALYSIS":"spikeOverAchvmntAllCls","MGNREGS":"mgnregsOverAchvmntAllCls","RURAL WATER SUPPLY":"rwsOverAchvmntAllCls","ITEC":"itecOverAchvmntAllCls","SWATCH BHARATH IHHL":"swatchBharathIHHL","SWATCH BHARATH PAYMENTS":"swatchBharathPayments",'Labour Budget':'LabourBudgetAllCls','Farm Ponds':'FarmPondsAllCls','IHHL':'IHHLAllCls','Vermi Compost':'VermiCompostAllCls','SMC Trench':'SMCTrenchAllCls','Imp to CD':'ImptoCDAllCls','MPT_PT':'MPT_PTAllCls','GC Works':'GCWorksAllCls','CD_CW':'CD_CWAllCls','GH':'GHAllCls','Check Dam':'CheckDamAllCls','Rock fill dams':'RockfilldamsAllCls','Solid Waste Management':'SolidWasteManagementAllCls','Burial Ground':'BurialGroundAllCls','Play fields':'PlayfieldsAllCls','Agriculture Activities':'AgricultureActivitiesAllCls','Average Wage':'AverageWageAllCls','Average Days of Employment':'AverageDaysofEmploymentAllCls','HH Completed 100 Days':'HHCompleted100DaysAllCls','Timely Payment':'TimelyPaymentAllCls','CC Roads1':'CCRoads1AllCls','Anganwadi':'AnganwadiAllCls','GP Buildings1':'GPBuildings1AllCls','Mandal buildings1':'Mandalbuildings1AllCls','NTR 90 Days':'NTR90DaysAllCls','Production of Bricks':'ProductionofBricksAllCls','Mulbery':'MulberyAllCls','Silk Worms':'SilkWormsAllCls','Horticulture':'HorticultureAllCls','Avenue':'AvenueAllCls','Fish Ponds':'FishPondsAllCls','Fish Drying Platforms':'FishDryingPlatformsAllCls','Nurseries':'NurseriesAllCls','Payments':'PaymentsAllCls','FAperformance':'FAperformanceAllCls','OPGK-Perinnials':'OPGK-PerinnialsAllCls','OPGK-Annuals':'OPGK-AnnualsAllCls','UGDrainage':'UGDrainageAllCls','Ntr Jalasiri':"NtrJalasiriAllCls",'WaterBudget':"WaterBudgetAllCls",'JALAVANI':"JalavaniAllCls",'ASSETS':"assetsAllCls","WATER SOURCE":"waterSourceAllCls","WORKS":"worksAllCls","PROMOTIONS":"itecPromotionsAllCls","E OFFICE":"itecEOfficeAllCls","MEESEVA - SLA":"itecMeeSevaSlaAllCls","AP INNOVATION SOCIETY":"itecApInnovationAllCls"
}

var blockHeadingObject = {
"PRIS":"ACHIEVEMENT","DRAINS":"ACHIEVEMENT","LED MONITORING":"TOTAL LIGHTS","UGD":"ACHIEVEMENT","RDP":"ACHIEVEMENT","FUND MANAGMENT SYSTEM":"TOTAL FUNDS","ENGINEERING DEPARTMENT":"ACHIEVEMENT","PANACHAYATI RAJ EXPENDITURE":"GROSS-AMOUNT","SPIKE ANALYSIS":"TOTAL CASES","MGNREGS":"ACHIEVEMENT","RURAL WATER SUPPLY":"ACHIEVEMENT","ITEC":"TOTAL TRANSACTIONS","SWATCH BHARATH IHHL":"COMPLETED","SWATCH BHARATH PAYMENTS":"PENDING",'Labour Budget':"ACHIEVED",'Farm Ponds':"ACHIEVED",'IHHL':"ACHIEVED",'Vermi Compost':"ACHIEVED",'SMC Trench':"ACHIEVED",'Imp to CD':"ACHIEVED",'MPT_PT':"ACHIEVED",'GC Works':"ACHIEVED",'CD_CW':"ACHIEVED",'GH':"ACHIEVED",'Check Dam':"ACHIEVED",'Rock fill dams':"ACHIEVED",'Solid Waste Management':"ACHIEVED",'Burial Ground':"ACHIEVED",'Play fields':"ACHIEVED",'Agriculture Activities':"ACHIEVED",'Average Wage':"ACHIEVED",'Average Days of Employment':"ACHIEVED",'HH Completed 100 Days':"ACHIEVED",'Timely Payment':"ACHIEVED",'CC Roads1':"ACHIEVED",'Anganwadi':"ACHIEVED",'GP Buildings1':"ACHIEVED",'Mandal buildings1':"ACHIEVED",'NTR 90 Days':"ACHIEVED",'Production of Bricks':"ACHIEVED",'Mulbery':"ACHIEVED",'Silk Worms':"ACHIEVED",'Horticulture':"ACHIEVED",'Avenue':"ACHIEVED",'Fish Ponds':"ACHIEVED",'Fish Drying Platforms':"ACHIEVED",'Nurseries':"ACHIEVED",'Payments':"PENDING PAYMENTS",'FAperformance':"ACHIEVED",'OPGK-Perinnials':"ACHIEVED",'OPGK-Annuals':"ACHIEVED",'UGDrainage':"ACHIEVED",'Ntr Jalasiri':"ACHIEVED",'WaterBudget':"ACHIEVED",'JALAVANI':"COMPLETED & CLOSED",'ASSETS':"COMPLETED & CLOSED","WATER SOURCE":"TOTAL","WORKS":"TOTAL","PROMOTIONS":"COMMITTED INVESTMENT","E OFFICE":"TOTAL PENDENCY","MEESEVA - SLA":"BEYOND SLA","AP INNOVATION SOCIETY":"STARTUPS","MEESEVA & KPI":"ETAAL - KPI"
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
var globalComponentNameArr =[];
function getFavouriteComponents(){
	globalComponentNameArr = [];
	$("#favouriteComponentDivId").html(spinner);
	var json = {componentNameList:[]} 
	$.ajax({                
		type:'POST',    
		url: 'getFavouriteComponents',
		dataType: 'json',
		data : JSON.stringify(json),
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
			 onloadCallToGetAllBlockAchievent();
		 }
	});		
}
function buildFavouriteComponentsResult(result) {
	   var str = '';
		str +='<div class="col-sm-12 text-right m_top10">';
			//str +='<i class="fa fa-refresh" title="Refresh" id="refreshList" style="cursor:pointer;font-size:18px;"></i>';
			str +='<i class="fa fa-edit" title="edit priorities" id="editList" style="cursor:pointer;font-size:18px;"></i>';
			str +='<i class="fa fa-save" title="save priorities" id="saveList" style="display:none;cursor:pointer;font-size:18px;"></i>';
			str +='<span id="errorDivId"></span>';
		str +='</div>';
		str +='<div id="sortableList">';
		for (var i in result) {
			var compnentName = result[i].name.trim();
			if (result[i].name == "IT E & C") {
				compnentName = "ITEC";
			}else if (result[i].name == "CC Roads") {
				compnentName = "CC Roads1";
			}else if (result[i].name == "Anganwadi Buildings") {
				compnentName = "Anganwadi";
			}else if (result[i].name == "GP Buildings") {
				compnentName = "GP Buildings1";
			}else if (result[i].name == "Mandal Buildings") {
				compnentName = "Mandal buildings1";
			}
			if (compnentName != null && compnentName!="PRIS" && compnentName!="DRAINS" && compnentName!="LED MONITORING" && compnentName!="FUND MANAGMENT SYSTEM" && compnentName!="ENGINEERING DEPARTMENT" && compnentName!="PANACHAYATI RAJ EXPENDITURE" &&  compnentName!="SPIKE ANALYSIS" && compnentName!="MGNREGS" && compnentName!="RURAL DEVELOPMENT" && compnentName!="RURAL WATER SUPPLY" && compnentName!="ITEC" && compnentName!="SWATCH BHARATH IHHL" && compnentName!="SWATCH BHARATH PAYMENTS") {
			   globalComponentNameArr.push(compnentName);
			}
			
			/*if (compnentName != null && compnentName!="PRIS" && compnentName!="DRAINS" && compnentName!="LED MONITORING" && compnentName!="FUND MANAGMENT SYSTEM" && compnentName!="ENGINEERING DEPARTMENT" && compnentName!="PANACHAYATI RAJ EXPENDITURE" &&  compnentName!="SPIKE ANALYSIS" && compnentName!="MGNREGS" && compnentName!="RURAL DEVELOPMENT" && compnentName!="RURAL WATER SUPPLY" && compnentName!="ITEC" && compnentName!="SWATCH BHARATH IHHL" && compnentName!="SWATCH BHARATH PAYMENTS") {
			   globalComponentNameArr.push(compnentName);
			}*/
			
			
			var componentNameWithoutSpace = compnentName.replace(/\s+/g, '');
			str +='<div class="col-sm-4 draggable-element" order-by="'+result[i].id+'">'
				str +='<div class="whiteBlock">';
					str +='<img src="Assests/img/'+imagesObj[compnentName]+'" >';
					str +='<h5 style="display: inline-block;text-transform:uppercase;">'+result[i].name+'</h5>';
					str +='<div class=" " style="text-align: right">';
						str +='<h2 class="'+blockClassObject[compnentName]+'" style="margin-top: 0px"></h2>';
						if(result[i].name == 'ENGINEERING DEPARTMENT')
						{
							str +='<p class="" style="text-align: right;height: 53px;">'+blockHeadingObject[compnentName]+'</p>';
						}else{
							str +='<p class="">'+blockHeadingObject[compnentName]+'</p>';
						}
						
					str +='</div>';
					str +='<div class="block-footer" style="border-top: 1px solid lightgrey;padding-top: 5px">';
						str +='<i class="fa fa-star starcolorChange '+componentNameWithoutSpace+'Color" attr_url="'+result[i].url
						+'" attr_full_block_name="'+compnentName+'" attr_color_name="green" attr_block_name="'+componentNameWithoutSpace+'" aria-hidden="true"></i>';
						str +='<a class="pull-right" href="'+result[i].url+'" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>';
					str +='</div>';
				str +='</div>';
			str +='</div>';
		}
		console.log(globalComponentNameArr);
		str +='</div>';
	$("#favouriteComponentDivId").html(str);
	
	/*adding required filed dynamically*/
	for (var i in result) { 
		var compnentName = result[i].name.trim();;
		if (result[i].name == "IT E & C") {
			compnentName = "ITEC";
		}else if (result[i].name == "CC Roads") {
			compnentName = "CC Roads1";
		}else if (result[i].name == "Anganwadi Buildings") {
			compnentName = "Anganwadi";
		}else if (result[i].name == "GP Buildings") {
			compnentName = "GP Buildings1";
		}else if (result[i].name == "Mandal Buildings") {
			compnentName = "Mandal buildings1";
		}
		var componentNameWithoutSpace = compnentName.replace(/\s+/g, '');
		$("."+componentNameWithoutSpace+"Color").css("color","green");
		$("."+componentNameWithoutSpace+"Color").attr("attr_block_id",result[i].id);
		$("."+componentNameWithoutSpace+"Color").attr("attr_color_name","green");
		$("."+componentNameWithoutSpace+"Color").attr("title","click to remove from favourite list.");
	}
	onloadCallToGetAllBlockAchievent();
}
$(document).on("click","#saveList",function(){
	spinner = '<div class="spinner" style="height:20px;width:20px;display:inline-block"><div class="dot1"></div><div class="dot2"></div></div>';
	$("#errorDivId").html(spinner)
	var orderList = []
	$(".draggable-element").each(function(){
		orderList.push($(this).attr("order-by"));
	});
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
	/* if (blockName == "favourite") {
		getFavouriteComponents();
	} */
});	
	
$(document).on('click','#refreshList',function(){
	getFavouriteComponents();
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
		     $("."+blockName+"Color").css("color","green");
			  $("."+blockName+"Color").attr("attr_color_name","green");
			  $("."+blockName+"Color").attr("title","click to remove from favourite list.");
			  $("#blockModalMessageDivId").modal("show");
			  //getFavouriteComponents();
			   // window[functionNameObject[fullBlockName]](); 
				var compnentName = fullBlockName.trim();;
				updateAddedFavouriteComponentId(compnentName);
				if (fullBlockName == "IT E & C") {
					compnentName = "ITEC";
				}
				var cmpnentNameWithutSpce = compnentName.replace(/\s+/g, '');
				$("."+cmpnentNameWithutSpce+"Color").css("color","green");
				$("."+cmpnentNameWithutSpce+"Color").attr("attr_color_name","green");
				$("."+cmpnentNameWithutSpce+"Color").attr("title","click to remove from favourite list.");
			  getFavouriteComponents()
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
		   var activeName = $(".block.active").attr("id");
		   //alert(activeName);
			  getFavouriteComponents();
		   
		}else {
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
	getHabitationCoverageByStatusByState();//RWS 
	getALlProgramesAmountDetails();//FMS 
	getMeesevaSLAOverviewDtls();//ITEC 
	getNregsLabourBudgetOverAllAchievent();//MGNREGS
	getIHHLOverviewData();//swatch Bharath IHHL
	getSBPaymentsAbstract();//SWATCH BHARATH PAYMENTS
	getNtrJalaSiriAbstract('Ntr Jalasiri','state',"0");//ntr jalasiri
	getRDAbstractDataByType('WaterBudget','state',"0");//ntr jalasiri
	getLocationWiseAlertStatusCounts();//jalavani
	getAssetInfoBetweenDates();//assets
	getKeyPerformanceIndicatorsInfo();//key performance
	getWaterSourceDeatils2();//water source
	getSchemeWiseWorkDetails();//works
	getEOfcDepartWiseOverviewDetails();//itec eoffice
	getITSectorWiseOverviewDetails();//itec promotions
	getAPInnovationSocietyOverview();//itec ap innovation
	for(var i in globalComponentNameArr)
	{
		if(globalComponentNameArr[i] == 'NTR 90 Days' || globalComponentNameArr[i] == 'Production of Bricks' || globalComponentNameArr[i] == 'Mulbery' || globalComponentNameArr[i] == 'Silk Worms' || globalComponentNameArr[i] == 'Cattle Drinking Water Troughs' || globalComponentNameArr[i] == 'Raising of Perinnial Fodders' || globalComponentNameArr[i] == 'Fish Ponds' || globalComponentNameArr[i] == 'Fish Drying Platforms' || globalComponentNameArr[i] == 'NTR Rural House' || globalComponentNameArr[i] == 'OPGK-Perinnials' || globalComponentNameArr[i] == 'OPGK-Annuals')
		{
			getNREGSProjectsAbstractNew(globalComponentNameArr[i]);
		}else if(globalComponentNameArr[i] == 'Payments')
		{
			getNregaPaymentsAbsAndOverviewDtls(globalComponentNameArr[i]);
		}else{
			getNREGSAbstractDataByType(globalComponentNameArr[i]);
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
	 $(".itecOverAchvmntAllCls,.itecMeeSevaSlaAllCls").html(spinner);
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
		$(".itecOverAchvmntAllCls,.itecMeeSevaSlaAllCls").html(0);
		if (result != null && result.length>0) {
			for(var i in result){
				if(result[i].name == "TOTAL TRANSACTIONS"){
					totalTransaction = result[i].totalCount;
				}
			}
			$(".itecOverAchvmntAllCls,.itecMeeSevaSlaAllCls").html(totalTransaction);
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
	

function getIHHLOverviewData(){
	$(".swatchBharathIHHL").html(spinner);
	var json = {
			fromDate:"",
			toDate:"",
			location:"state",
			locationId:"-1",
			subLocation:"state"
		}
	$.ajax({                
		type:'POST',    
		url: 'getSwachhBharatMissionOverviewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		var totalCount = (result.completed *100) / result.target;
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
				if(result[i].name == "CC Roads")
				{
					selectionMenu+='<label><i class="fa fa-star starcolorChange CCRoads1Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=CC Roads1" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="CC Roads1" aria-hidden="true"></i> CC Roads</label>';
				}else if(result[i].name == "Anganwadi Buildings")
				{
					selectionMenu+='<label><i class="fa fa-star starcolorChange AnganwadiColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Anganwadi" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="Anganwadi" aria-hidden="true"></i> Anganwadi Buildings</label>';
				}else if(result[i].name == "GP Buildings1")
				{
					selectionMenu+='<label><i class="fa fa-star starcolorChange GPBuildings1Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=GP Buildings1" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="GP Buildings1" aria-hidden="true"></i> GP Buildings</label>';
				}else if (result[i].name == "Mandal Buildings") {
					selectionMenu+='<label><i class="fa fa-star starcolorChange Mandalbuildings1Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Mandal buildings1" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="Mandal buildings1" aria-hidden="true"></i> Mandal Buildings</label>';
				}else{
					selectionMenu+='<label><i class="fa fa-star starcolorChange '+result[i].name.replace(/\s+/g, '')+'Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component='+result[i].name+'" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="'+result[i].name.replace(/\s+/g, '')+'" aria-hidden="true"></i>'+result[i].name+'</label>';
				}
				
			selectionMenu+='</li>';
		}
		if(convergenceId == 1)
		{
			selectionMenu+='<li>';
				selectionMenu+='<label><i class="fa fa-star starcolorChange PaymentsColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Payments" attr_full_block_name="Payments" style="margin-right:5px;" attr_color_name="gray" attr_block_name="Payments" aria-hidden="true"></i> Payments</label>';
			selectionMenu+='</li>';
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].totalPendinAmount);
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

function updateAddedFavouriteComponentId(componentName){
	
	var componentNameArr = [];
	if (componentName != null && componentName.trim().length > 0 ) {
		componentNameArr.push(componentName);
	}
	var json = {componentNameList:componentNameArr} 
	$.ajax({                
		type:'POST',    
		url: 'getFavouriteComponents',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$("#blockOperationStatusHeadingId").html("Block Successfully added as favourite.");
		 setTimeout(function(){ $("#blockModalMessageDivId").modal("hide"); }, 2000);
		 if (result != null && result.length > 0) {
			 if (componentName == "IT E & C") {
					componentName = "ITEC";
				}
				var cmpNameWithoutSpace = componentName.replace(/\s+/g, '');
			   $("."+cmpNameWithoutSpace+"Color").attr("attr_block_id",result[0].id);
		 } 
	});		
}
function getRDAbstractDataByType(type,locType,locId)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	var json = {
		year : "2017",
		fromDate : '2017-04-01',
		toDate : moment().format("YYYY-MM")+'-30',
		type : type,
		locationType: locType,
		locationId : locId
	}
	
	$.ajax({
		url: 'getRDAbstractDataByType',
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

function getNtrJalaSiriAbstract(type,locType,locId)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	var json = {
		year : "2017",
		fromDate : '2017-04-01',
		toDate : moment().format("YYYY-MM")+'-30',
		locationType : "district",
		locationId : locId
	}
	$.ajax({
		url: 'getNtrJalaSiriAbstract',
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
function getLocationWiseAlertStatusCounts()
{
	$(".JalavaniAllCls").html(spinner);
	var json = {
		deptId:49,
		fromDate:"01-01-1977",
		toDate:moment().format("DD-MM-YYYY"),
		year:2017,
		locationTypeId:2,
		locationValues:[1],
		searchLevelId:2,
		searchLvlVals:[1]
	}
	$.ajax({
		url: 'getLocationWiseAlertStatusCounts',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp){
			var CompleteClosedCount = 0;
			var completeClosed = 0;
			for(var i in ajaxresp)
			{
				for(var j in ajaxresp[i].statusList)
				{
					if(ajaxresp[i].statusList[j].id==4 ||  ajaxresp[i].statusList[j].id == 12){
						if(ajaxresp[i].statusList[j].count !=null && ajaxresp[i].statusList[j].count>0){
							CompleteClosedCount = CompleteClosedCount+ajaxresp[i].statusList[j].count;
							completeClosed = CompleteClosedCount;
						}
					}
				}
			}
			$(".JalavaniAllCls").html(completeClosed);
		}
	});
}
function getAssetInfoBetweenDates()
{
	$(".assetsAllCls").html(spinner);
	var json = {
		fromDateStr:'01-01-1977',
		toDateStr:moment().format("DD-MM-YYYY"),
		year:2017,
		filterType:'',
		filterValue:'',
		districtValue:'',
		locationType:'state'
	}
	$.ajax({
		url: 'getAssetInfoBetweenDates',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(result){
			var totalCount = 0;
			for(var i in result)
			{
				for(var j in result[i].basicList){
					totalCount = totalCount + result[i].basicList[j].count;
				}
			}
			$(".assetsAllCls").html(totalCount);
		}
	});
}
function getKeyPerformanceIndicatorsInfo()
{
	$(".keyPerforamanceAllCls").html(spinner);
	var json={
		fromDateStr:"01-01-1977",
		toDateStr:moment().format("DD-MM-YYYY"),
		locationType:'state',
		filterType:'',
		filterValue:'',
		districtValue:'',
		year:2017
	}
	$.ajax({
		url: 'getKeyPerformanceIndicatorsInfo',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp){
			$(".keyPerforamanceAllCls").html(ajaxresp[0].pcPercentage.toFixed(2));
		}
	});
}
function getWaterSourceDeatils2()
{
	$(".waterSourceAllCls").html(spinner);
	var json = {
		year:"2017",
		locationType:'state',
		fromDateStr:"01-01-1977",
		toDateStr:moment().format("DD-MM-YYYY"),
		filterType:'',
		filterValue:'',
		districtValue:''
	}
	$.ajax({    
		type:'POST',    
		url: 'getWaterSourceDeatils2',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(ajaxresp){
		$(".waterSourceAllCls").html(ajaxresp[0].totalSurfaceWaterSourceCount);
	});
}
function getSchemeWiseWorkDetails()
{
	$(".worksAllCls").html(spinner);
	var json = {
		  fromDateStr:'01-01-1977',
		  toDateStr:moment().format("DD-MM-YYYY"),
		  year:'2017',
		  locationType:'state',
		  type:'',
		  filterType:'',
		  filterValue:'',
		  districtValue:''
	  }
	$.ajax({
		url: 'getSchemeWiseWorkDetails',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp){
			$(".worksAllCls").html(ajaxresp[0].basicList[0].workCompletedCount);
		}
	});
	
}
function getEOfcDepartWiseOverviewDetails(){
	$(".itecEOfficeAllCls").html(spinner);
	var json = {
		departmentid:"",		
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfcDepartWiseOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$(".itecEOfficeAllCls").html(result[0].subList[0].totalCount+'/<small style="color:#333;font-size:14px;top:0px;">'+result[0].subList[0].created+'</small>');
	});		
}
function getAPInnovationSocietyOverview(){
	$(".itecApInnovationAllCls").html(spinner);
	var json = {
		fromDate:"",
		toDate:"",
		year:""
	}
	$.ajax({                
		type:'POST',    
		url: 'getAPInnovationSocietyOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		$(".itecApInnovationAllCls").html(result.startups);
	});	
}
function getITSectorWiseOverviewDetails(){
	$(".itecPromotionsAllCls").html(spinner);
	var json = {
		category:'ALL'
	}
	$.ajax({                
		type:'POST',    
		url: 'getITSectorCategoryWiseDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		for(var i in result)
		{
			if(result[i].sector == "Total")
			{
				$(".itecPromotionsAllCls").html(result[i].investment+"CR");
			}
		}
	});	
}