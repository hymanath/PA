var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var subSpinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="subSpinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';silk
var imagesObj = {
"PRIS":"Group 2344.png","DRAINS":"Group 2345.png","LED MONITORING":"Group 2348.png","UGD":"Group 2359.png","RDP":"Group 2343.png","FUND MANAGMENT SYSTEM":"Group 2352.png","ENGINEERING DEPARTMENT":"Group 2346.png","PANACHAYATI RAJ EXPENDITURE":"Group 2343.png","SPIKE ANALYSIS":"Group 2347.png","MGNREGS":"Group 2357.png","RURAL WATER SUPPLY":"Group 2350.png","ITEC":"Group 2351.png","SWATCH BHARATH IHHL":"Group 2362.png","MGNREGS IHHL":"Group 2362.png",'Labour Budget':"Group 2344.png",'Farm Ponds':"Group 2344.png",'IHHL':"Group 2344.png",'Vermi Compost':"Group 2344.png",'GH':"Group 2344.png",'Check Dams':"Group 2344.png",'Rock Fill Dams':"Group 2344.png",'Solid Waste Management':"Group 2344.png",'Burial Ground':"Group 2344.png",'Play fields':"Group 2344.png",'Agriculture Activities':"Group 2344.png",'Average Wage':"Group 2344.png",'Average Days of Employment':"Group 2344.png",'HH Completed 100 Days':"Group 2344.png",'Timely Payment':"Group 2344.png",'CC Roads1':"Group 2344.png",'Anganwadi':"Group 2344.png",'GP Buildings1':"Group 2344.png",'Mandal buildings1':"Group 2344.png",'NTR 90 Days':"Group 2344.png",'Production of Bricks':"Group 2344.png",'Mulbery New':"Group 2344.png",'Silk worm New':"Group 2344.png",'Horticulture':"Group 2344.png",'Avenue':"Group 2344.png",'Fish Ponds':"Group 2344.png",'Fish Drying Platforms':"Group 2344.png",'Payments':"Group 2344.png",'FAperformance':"Group 2344.png",'OPGK-Perinnials':"Group 2344.png",'OPGK-Annuals':"Group 2344.png",'UGDrainage':"Group 2344.png",'Ntr Jalasiri':"Group 2349.png",'WaterBudget':"Group 2350.png","SWATCH BHARATH PAYMENTS":"Group 2352.png","JALAVANI":"Jalavani.png","JALAVANI":"Jalavani.png",'ASSETS':"assets","WATER SOURCE":"water source.png","WORKS":"works.png","PROMOTIONS":"promotions.png","E OFFICE":"eOffice.png","MEESEVA-SLA KPI":"meeSevaSla.png","AP INNOVATION SOCIETY":"APInnovationSoc.png","MEESEVA & KPI":"MeeSevaKPI.png","WATER TANK CHLORINATION":"waterTank.png","coffee":"Group 2907.png","Raising and Maintenance of Nursery":"RaisingandMaintenanceofNursery.png","Desilting of Perculation Tanks and Check Dams":"Desilting of Perculation Tanks and Check Dams.png","Mini Percolation Tanks":"Mini Percolation Tanks.png","Continuous Contour Trenches":"Continuous Contour Trenches.png","Greenary works Chettu":"Greenary works Chettu.png","Agriculture Related Works":"Agriculture Related Works.png","Rural Sanitation Works":"Rural Sanitation Works.png","Soil Moisture Conservation works Neeru":"Soil Moisture Conservation works Neeru.png","Works in community lands":"Works in community lands.png","OTHERS":"OTHERS.png","Institutional Development Works":"Institutional Development Works.png","Road Works":"Road Works.png","Water Harvesting Structures Neeru":"Water Harvesting Structures Neeru.png","Fisheries work":"Fisheries work.png","AH-Live Stock Related works":"AH-Live Stock Related works.png",
"Renovation and Improvements to existing Check Dams Check Wall":"Group 2352.png","Road Formation Upto WBM GR II Including CD works":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works Connecting SC habitation or Locality in Plain areas":"Group 2352.png","Construction Of Animal Hostel":"Group 2352.png","Roads for Unconnected Habitations 2011-12":"Group 2352.png","Construction of New Check Dam":"Group 2352.png","Formation of internal road upto WBM Gr II surface including CD works and Drains in SC Habitation or Locality":"Group 2352.png","Construction of Food Grains Storage Structures of 250MT":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works in Tribal areas":"Group 2352.png","Construction of Village Haats Infrastructure fecilities":"Group 2352.png","Providing BT road for Sri Anantha Padmanabha Swamy Temple Hill top Road at Padmabnabham":"Group 2352.png","Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT":"Group 2352.png","Comprehensive Restoration of minor Irrigation Tank":"Group 2352.png","Construction of Buildings for women self help group federation":"Group 2352.png","Work Site Facilities":"Group 2352.png","Renovation and Improvements to existing Percolation Tank  Mini Percolation tank":"Group 2352.png","GP level BNRGSK knowledge resource centre 2012-13 and afterwards":"Group 2352.png","Formation of internal road upto WBM Gr II surface including CD works and Drains in other Habitation or Locality":"Group 2352.png","Production of Grafts in HNTC":"Group 2352.png","Improvements of RYTHU BAZAR":"Group 2352.png","Roads for Unconnected Habitations 2012-13 and afterwards":"Group 2352.png","HNTC Development":"Group 2352.png","New Open Well for Drinking water purpose":"Group 2352.png","Construction of Crematoria Burial Grounds":"Group 2352.png","Repairs to Existing Check Dam":"Group 2352.png","Formation of Road upto Gravel surface including CD works to agriculture fields":"Group 2352.png","Formation of Approach Road upto Gravel surface including CD works to Burial ground":"Group 2352.png","Construction of Food Grains Storage Structures of 500MT":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works Connecting other habitation or Locality in Plain areas":"Group 2352.png","Raising of Cashew bag seedlings for 2014-15":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works Connecting ST habitation or Locality in Plain areas":"Group 2352.png","NTR Rural House":"Group 2352.png","Cattle Ponds":"Group 2352.png","Desilting of Drinking Water Tanks":"Group 2352.png","Animal Husbandry Others":"Group 2352.png","Comprehensive Restoration of minor Irrigation Tank1":"Group 2352.png",
"Avenue Plantation":"Group 2352.png","Forest Others":"Group 2352.png","Scooping and Dibbling of seed":"Group 2352.png","IJP PROGRAM WORKS":"Group 2352.png",
"Azolla Production Unit":"Group 2352.png","Construction of silopits of 3 MTs capacity":"Group 2352.png","Fodder trough for Cattle Cattle drinking water trough":"Group 2352.png","Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze":"Group 2352.png","Raising of Perinnial Fodder":"Group 2352.png","Raising of Silvipasture clubbed with subabul plantation":"Group 2352.png","Raising and Maintenance of Avenue plantations":"Group 2352.png","Raising and Maintenance of Block Plantations":"Group 2352.png","Raising and Maintenance of nurseries":"Group 2352.png","Soil and Moisture Conservation Works":"Group 2352.png","CM EODB":"MeeSevaKPI.png","bioMetricDashBoard":"BioMetricL.png"

}

var blockClassObject = {
"PRIS":"prisOverAchvmntAllCls","DRAINS":"drainsOverAchvmntAllCls","LED MONITORING":"ledOverAchvmntAllCls","UGD":"","RDP":"","FUND MANAGMENT SYSTEM":"fundMngmntSstmOverAchvmntAllCls","ENGINEERING DEPARTMENT":"encOverAchvmntAllCls","PANACHAYATI RAJ EXPENDITURE":"preOverAchvmntAllCls","SPIKE ANALYSIS":"spikeOverAchvmntAllCls","MGNREGS":"mgnregsOverAchvmntAllCls","RURAL WATER SUPPLY":"rwsOverAchvmntAllCls","ITEC":"itecOverAchvmntAllCls","SWATCH BHARATH IHHL":"swatchBharathIHHL","MGNREGS IHHL":"MGNREGSIHHL","SWATCH BHARATH PAYMENTS":"swatchBharathPayments",'Labour Budget':'LabourBudgetAllCls','Farm Ponds':'FarmPondsAllCls','IHHL':'IHHLAllCls','Vermi Compost':'VermiCompostAllCls','GH':'GHAllCls','Check Dams':'CheckDamsAllCls','Rock Fill Dams':'RockFillDamsAllCls','Solid Waste Management':'SolidWasteManagementAllCls','Burial Ground':'BurialGroundAllCls','Play fields':'PlayfieldsAllCls','Agriculture Activities':'AgricultureActivitiesAllCls','Average Wage':'AverageWageAllCls','Average Days of Employment':'AverageDaysofEmploymentAllCls','HH Completed 100 Days':'HHCompleted100DaysAllCls','Timely Payment':'TimelyPaymentAllCls','CC Roads1':'CCRoads1AllCls','Anganwadi':'AnganwadiAllCls','GP Buildings1':'GPBuildings1AllCls','Mandal buildings1':'Mandalbuildings1AllCls','NTR 90 Days':'NTR90DaysAllCls','Production of Bricks':'ProductionofBricksAllCls','Mulbery New':'MulberyNewAllCls','Silk worm New':'SilkwormNewAllCls','Horticulture':'HorticultureAllCls','Avenue':'AvenueAllCls','Fish Ponds':'FishPondsAllCls','Fish Drying Platforms':'FishDryingPlatformsAllCls','Payments':'PaymentsAllCls','FAperformance':'FAperformanceAllCls','OPGK-Perinnials':'OPGK-PerinnialsAllCls','OPGK-Annuals':'OPGK-AnnualsAllCls','UGDrainage':'UGDrainageAllCls','Ntr Jalasiri':"NtrJalasiriAllCls",'WaterBudget':"WaterBudgetAllCls",'JALAVANI':"JalavaniAllCls",'ASSETS':"assetsAllCls","WATER SOURCE":"waterSourceAllCls","WORKS":"worksAllCls","PROMOTIONS":"itecPromotionsAllCls","E OFFICE":"itecEOfficeAllCls","MEESEVA-SLA KPI":"itecMeeSevaSlaKpiAllCls","AP INNOVATION SOCIETY":"itecApInnovationAllCls","WATER TANK CHLORINATION":"waterTankChlorinationAllCls","coffee":"coffeeAllCls","Raising and Maintenance of Nursery":"RaisingandMaintenanceofNurseryAllCls","Desilting of Perculation Tanks and Check Dams":"DesiltingofPerculationTanksandCheckDamsAllCls","Mini Percolation Tanks":"MiniPercolationTanksAllCls","Continuous Contour Trenches":"ContinuousContourTrenchesAllCls","Greenary works Chettu":"GreenaryworksChettuAllCls","Agriculture Related Works":"AgricultureRelatedWorksAllCls","Rural Sanitation Works":"RuralSanitationWorksAllCls","Soil Moisture Conservation works Neeru":"SoilMoistureConservationworksNeeruAllCls","Works in community lands":"WorksincommunitylandsAllCls","OTHERS":"OTHERSAllCls","Institutional Development Works":"InstitutionalDevelopmentWorksAllCls","Road Works":"RoadWorksAllCls","Water Harvesting Structures Neeru":"WaterHarvestingStructuresNeeruAllCls","Fisheries work":"FisheriesworkAllCls","AH-Live Stock Related works":"AH-LiveStockRelatedworksAllCls",
"Renovation and Improvements to existing Check Dams Check Wall":"RenovationandImprovementstoexistingCheckDamsCheckWallAllCls","Road Formation Upto WBM GR II Including CD works":"RoadFormationUptoWBMGRIIIncludingCDworksAllCls","Formation of Road upto WBM Gr II surface including CD works Connecting SC habitation or Locality in Plain areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksConnectingSChabitationorLocalityinPlainareasAllCls","Construction Of Animal Hostel":"ConstructionOfAnimalHostelAllCls","Roads for Unconnected Habitations 2011-12":"RoadsforUnconnectedHabitations2011-12AllCls","Construction of New Check Dam":"ConstructionofNewCheckDamAllCls","Formation of internal road upto WBM Gr II surface including CD works and Drains in SC Habitation or Locality":"FormationofinternalroaduptoWBMGrIIsurfaceincludingCDworksandDrainsinSCHabitationorLocalityAllCls","Construction of Food Grains Storage Structures of 250MT":"ConstructionofFoodGrainsStorageStructuresof250MTAllCls","Formation of Road upto WBM Gr II surface including CD works in Tribal areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksinTribalareasAllCls","Construction of Village Haats Infrastructure fecilities":"ConstructionofVillageHaatsInfrastructurefecilitiesAllCls","Providing BT road for Sri Anantha Padmanabha Swamy Temple Hill top Road at Padmabnabham":"ProvidingBTroadforSriAnanthaPadmanabhaSwamyTempleHilltopRoadatPadmabnabhamAllCls","Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT":"ConstructionofPostHarvestfacilitiesDryingPlatformincludingPuccastoragefacilitiesof100MTAllCls","Comprehensive Restoration of minor Irrigation Tank":"ComprehensiveRestorationofminorIrrigationTankAllCls","Construction of Buildings for women self help group federation":"ConstructionofBuildingsforwomenselfhelpgroupfederationAllCls","Work Site Facilities":"WorkSiteFacilitiesAllCls","Renovation and Improvements to existing Percolation Tank  Mini Percolation tank":"RenovationandImprovementstoexistingPercolationTankMiniPercolationtankAllCls","GP level BNRGSK knowledge resource centre 2012-13 and afterwards":"GPlevelBNRGSKknowledgeresourcecentre2012-13andafterwardsAllCls","Formation of internal road upto WBM Gr II surface including CD works and Drains in other Habitation or Locality":"FormationofinternalroaduptoWBMGrIIsurfaceincludingCDworksandDrainsinotherHabitationorLocalityAllCls","Production of Grafts in HNTC":"ProductionofGraftsinHNTCAllCls","Improvements of RYTHU BAZAR":"ImprovementsofRYTHUBAZARAllCls","Roads for Unconnected Habitations 2012-13 and afterwards":"RoadsforUnconnectedHabitations2012-13andafterwardsAllCls","HNTC Development":"HNTCDevelopmentAllCls","New Open Well for Drinking water purpose":"NewOpenWellforDrinkingwaterpurposeAllCls","Construction of Crematoria Burial Grounds":"ConstructionofCrematoriaBurialGroundsAllCls","Repairs to Existing Check Dam":"RepairstoExistingCheckDamAllCls","Formation of Road upto Gravel surface including CD works to agriculture fields":"FormationofRoaduptoGravelsurfaceincludingCDworkstoagriculturefieldsAllCls","Formation of Approach Road upto Gravel surface including CD works to Burial ground":"FormationofApproachRoaduptoGravelsurfaceincludingCDworkstoBurialgroundAllCls","Construction of Food Grains Storage Structures of 500MT":"ConstructionofFoodGrainsStorageStructuresof500MTAllCls","Formation of Road upto WBM Gr II surface including CD works Connecting other habitation or Locality in Plain areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksConnectingotherhabitationorLocalityinPlainareasAllCls","Raising of Cashew bag seedlings for 2014-15":"RaisingofCashewbagseedlingsfor2014-15AllCls","Formation of Road upto WBM Gr II surface including CD works Connecting ST habitation or Locality in Plain areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksConnectingSThabitationorLocalityinPlainareasAllCls","NTR Rural House":"NTRRuralHouseAllCls","Cattle Ponds":"CattlePondsAllCls","Desilting of Drinking Water Tanks":"DesiltingofDrinkingWaterTanksAllCls","Animal Husbandry Others":"AnimalHusbandryOthersAllCls","Comprehensive Restoration of minor Irrigation Tank1":"ComprehensiveRestorationofminorIrrigationTank1AllCls","Avenue Plantation":"AvenuePlantationAllCls","Forest Others":"ForestOthersAllcls","Scooping and Dibbling of seed":"ScoopingandDibblingofseedAllCls","IJP PROGRAM WORKS":"IJPPROGRAMWORKSAllCls",
"Azolla Production Unit":"AzollaProductionUnitAllCls","Construction of silopits of 3 MTs capacity":"Constructionofsilopitsof3MTscapacityAllCls","Fodder trough for Cattle Cattle drinking water trough":"FoddertroughforCattleCattledrinkingwatertroughAllCls","Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze":"RaisingofFodderMaizeFodderJowarNutrifeedSugargrazeAllCls","Raising of Perinnial Fodder":"RaisingofPerinnialFodderAllCls","Raising of Silvipasture clubbed with subabul plantation":"RaisingofSilvipastureclubbedwithsubabulplantationAllCls","Raising and Maintenance of Avenue plantations":"RaisingandMaintenanceofAvenueplantationsAllCls","Raising and Maintenance of Block Plantations":"RaisingandMaintenanceofBlockPlantationsAllCls","Raising and Maintenance of nurseries":"RaisingandMaintenanceofnurseriesAllCls","Soil and Moisture Conservation Works":"SoilandMoistureConservationWorksAllCls","CM EODB":"cmeodbAllCls","bioMetricDashBoard":"bioMetricDashBoardAllCls"

}

var blockHeadingObject = {
"PRIS":"ACHIEVEMENT","DRAINS":"ACHIEVEMENT","LED MONITORING":"","UGD":"ACHIEVEMENT","RDP":"ACHIEVEMENT","FUND MANAGMENT SYSTEM":"TOTAL FUNDS","ENGINEERING DEPARTMENT":"ACHIEVEMENT","PANACHAYATI RAJ EXPENDITURE":"GROSS-AMOUNT","SPIKE ANALYSIS":"TOTAL CASES","MGNREGS":"ACHIEVEMENT","RURAL WATER SUPPLY":"ACHIEVEMENT","ITEC":"TOTAL TRANSACTIONS","SWATCH BHARATH IHHL":"COMPLETED","SWATCH BHARATH PAYMENTS":"PENDING",'Labour Budget':"ACHIEVED",'Farm Ponds':"ACHIEVED","MGNREGS IHHL":"COMPLETED",'IHHL':"ACHIEVED",'Vermi Compost':"ACHIEVED",'GH':"ACHIEVED",'Check Dams':"ACHIEVED",'Rock Fill Dams':"ACHIEVED",'Solid Waste Management':"ACHIEVED",'Burial Ground':"ACHIEVED",'Play fields':"ACHIEVED",'Agriculture Activities':"ACHIEVED",'Average Wage':"ACHIEVED",'Average Days of Employment':"ACHIEVED",'HH Completed 100 Days':"ACHIEVED",'Timely Payment':"ACHIEVED",'CC Roads1':"ACHIEVED",'Anganwadi':"ACHIEVED",'GP Buildings1':"ACHIEVED",'Mandal buildings1':"ACHIEVED",'NTR 90 Days':"ACHIEVED",'Production of Bricks':"ACHIEVED",'Mulbery New':"ACHIEVED",'Silk worm New':"ACHIEVED",'Horticulture':"ACHIEVED",'Avenue':"ACHIEVED",'Fish Ponds':"ACHIEVED",'Fish Drying Platforms':"ACHIEVED",'Payments':"PENDING PAYMENTS",'FAperformance':"ACHIEVED",'OPGK-Perinnials':"ACHIEVED",'OPGK-Annuals':"ACHIEVED",'UGDrainage':"ACHIEVED",'Ntr Jalasiri':"ACHIEVED",'WaterBudget':"ACHIEVED",'JALAVANI':"NOTIFIED & IN PROGRESS",'ASSETS':"COMPLETED & CLOSED","WATER SOURCE":"TOTAL","WORKS":"COMPLETED","PROMOTIONS":"COMMITTED INVESTMENT","E OFFICE":"TOTAL PENDENCY","MEESEVA-SLA KPI":"Beyond SLA/etaal - KPI","AP INNOVATION SOCIETY":"STARTUPS","MEESEVA & KPI":"ETAAL - KPI","WATER TANK CHLORINATION":"chlorinated","coffee":"COFFEE","Raising and Maintenance of Nursery":"Raising and Maintenance of Nursery","Desilting of Perculation Tanks and Check Dams":"Desilting of Perculation Tanks and CheckDams","Mini Percolation Tanks":"Mini Percolation Tanks","Continuous Contour Trenches":"Continuous Contour Trenches","Greenary works Chettu":"Greenary works Chettu","Agriculture Related Works":"Agriculture Related Works","Rural Sanitation Works":"Rural Sanitation Works","Soil Moisture Conservation works Neeru":"Soil Moisture Conservation works Neeru","Works in community lands":"Works in community lands","OTHERS":"OTHERS","Institutional Development Works":"Institutional Development Works","Road Works":"Road Works","Water Harvesting Structures Neeru":"Water Harvesting Structures Neeru","Fisheries work":"Fisheries work","AH-Live Stock Related works":"AH-Live Stock Related works",
"Renovation and Improvements to existing Check Dams Check Wall":"ACHIEVED","Road Formation Upto WBM GR II Including CD works":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works Connecting SC habitation or Locality in Plain areas":"ACHIEVED","Construction Of Animal Hostel":"ACHIEVED","Roads for Unconnected Habitations 2011-12":"ACHIEVED","Construction of New Check Dam":"ACHIEVED","Formation of internal road upto WBM Gr II surface including CD works and Drains in SC Habitation or Locality":"ACHIEVED","Construction of Food Grains Storage Structures of 250MT":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works in Tribal areas":"ACHIEVED","Construction of Village Haats Infrastructure fecilities":"ACHIEVED","Providing BT road for Sri Anantha Padmanabha Swamy Temple Hill top Road at Padmabnabham":"ACHIEVED","Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT":"ACHIEVED","Comprehensive Restoration of minor Irrigation Tank":"ACHIEVED","Construction of Buildings for women self help group federation":"ACHIEVED","Work Site Facilities":"ACHIEVED","Renovation and Improvements to existing Percolation Tank  Mini Percolation tank":"ACHIEVED","GP level BNRGSK knowledge resource centre 2012-13 and afterwards":"ACHIEVED","Formation of internal road upto WBM Gr II surface including CD works and Drains in other Habitation or Locality":"ACHIEVED","Production of Grafts in HNTC":"ACHIEVED","Improvements of RYTHU BAZAR":"ACHIEVED","Roads for Unconnected Habitations 2012-13 and afterwards":"ACHIEVED","HNTC Development":"ACHIEVED","New Open Well for Drinking water purpose":"ACHIEVED","Construction of Crematoria Burial Grounds":"ACHIEVED","Repairs to Existing Check Dam":"ACHIEVED","Formation of Road upto Gravel surface including CD works to agriculture fields":"ACHIEVED","Formation of Approach Road upto Gravel surface including CD works to Burial ground":"ACHIEVED","Construction of Food Grains Storage Structures of 500MT":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works Connecting other habitation or Locality in Plain areas":"ACHIEVED","Raising of Cashew bag seedlings for 2014-15":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works Connecting ST habitation or Locality in Plain areas":"ACHIEVED","NTR Rural House":"NTR Rural House","Cattle Ponds":"Cattle Ponds","Desilting of Drinking Water Tanks":"Desilting of Drinking Water Tanks","Animal Husbandry Others":"ACHIEVED","Comprehensive Restoration of minor Irrigation Tank1":"ACHIEVED","Avenue Plantation":"ACHIEVED","Forest Others":"ACHIEVED","Scooping and Dibbling of seed":"ACHIEVED","IJP PROGRAM WORKS":"ACHIEVED",
"Azolla Production Unit":"ACHIEVED","Construction of silopits of 3 MTs capacity":"ACHIEVED","Fodder trough for Cattle Cattle drinking water trough":"ACHIEVED","Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze":"ACHIEVED","Raising of Perinnial Fodder":"ACHIEVED","Raising of Silvipasture clubbed with subabul plantation":"ACHIEVED","Raising and Maintenance of Avenue plantations":"ACHIEVED","Raising and Maintenance of Block Plantations":"ACHIEVED","Raising and Maintenance of nurseries":"ACHIEVED","Soil and Moisture Conservation Works":"ACHIEVED","CM EODB":"Total/Approved","bioMetricDashBoard":"TotalEmployeement/Present"
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
$(document).on('cut copy paste', function (e) {
	e.preventDefault();
});
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
		str+='<div class="col-sm-12 text-right m_top10">';
			//str+='<i class="fa fa-refresh" title="Refresh" id="refreshList" style="cursor:pointer;font-size:18px;"></i>';
			str+='<i class="fa fa-edit" title="edit priorities" id="editList" style="cursor:pointer;font-size:18px;"></i>';
			str+='<i class="fa fa-save" title="save priorities" id="saveList" style="display:none;cursor:pointer;font-size:18px;"></i>';
			str+='<span id="errorDivId"></span>';
		str+='</div>';
		str+='<div id="sortableList">';
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
			
			
			var componentNameWithoutSpace = compnentName.replace(/[\(\)\-\s]+/g, '');
			
				str+='<div class="col-sm-4 draggable-element" order-by="'+result[i].id+'">'
					str+='<div class="whiteBlock">';
						str+='<img src="Assests/img/'+imagesObj[compnentName]+'" >';
						if(result[i].name !=null && result[i].name.length>15){
							str+='<h5 class="tooltipCls" style="display: inline-block;text-transform:uppercase;cursor:pointer;" data-toogle="tooltip" title="'+result[i].name+'">'+result[i].name.substring(0, 15)+'...</h5>';
							
						}else{
							str+='<h5 style="display: inline-block;text-transform:uppercase;">'+result[i].name+'</h5>';
						}
						if(result[i].name == 'LED MONITORING' || result[i].name == 'Led Monitoring')
						{
							str+='<div class="ledOverAchvmntAllCls"></div>';
						}else{
							str+='<div class=" " style="text-align: right">';
								str+='<h2 class="'+blockClassObject[compnentName]+'" style="margin-top: 0px"></h2>';
								if(result[i].name == 'ENGINEERING DEPARTMENT')
								{
									str+='<p class="" style="text-align: right;height: 53px;">'+blockHeadingObject[compnentName]+'</p>';
								}else{
									str+='<p class="">'+blockHeadingObject[compnentName]+'</p>';
								}
								
							str+='</div>';
						}
						str+='<div class="block-footer" style="border-top: 1px solid lightgrey;padding-top: 5px">';
							str+='<i class="fa fa-star starcolorChange '+componentNameWithoutSpace+'Color" attr_url="'+result[i].url
							+'" attr_full_block_name="'+compnentName+'" attr_color_name="green" attr_block_name="'+componentNameWithoutSpace+'" aria-hidden="true"></i>';
							str+='<a class="pull-right" href="'+result[i].url+'" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>';
						str+='</div>';
					str+='</div>';
				str+='</div>';
			
			
		}
		console.log(globalComponentNameArr);
		str+='</div>';
	$("#favouriteComponentDivId").html(str);
	$(".tooltipCls").tooltip();
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
		var componentNameWithoutSpace = compnentName.replace(/[\(\)\-\s]+/g, '');
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
   var pageId = $(this).attr("attr_page_id");
  
  
	if(colorName == "gray"){
		$("."+blockName+"Color").removeClass("removeFav");
		addRemoveComponentToFavourite('Add',blockName,fullBlockName,url,blockId,pageId);
	}else{
		$("."+blockName+"Color").addClass("removeFav");
		addRemoveComponentToFavourite('Remove',blockName,fullBlockName,url,blockId,pageId);
	}
})

var saveFlag = true;
var deleteFlag = true;
function addRemoveComponentToFavourite(actionType,blockName,fullBlockName,url,blockId,pageId){
	if (actionType == "Add") {
		if (saveFlag == true) {
			saveFlag = false;
		    saveFavouriteComponentDtls(url,blockName,fullBlockName,pageId);	
		}
		
	} else if (actionType == "Remove") {
		if (deleteFlag == true) {
			deleteFlag = false;
			deleteFavouriteComponent(blockName,blockId)	
		}
		
	}
	
}

function saveFavouriteComponentDtls(url,blockName,fullBlockName,pageId){
	  $("#blockOperationStatusHeadingId").html(spinner);
	  $("#blockModalMessageDivId").modal("show");
	var json = {
		  url:url, 
		  name:fullBlockName,
		  id : pageId
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
var overViewArr = ['Labour Budget','Farm Ponds','IHHL','Vermi Compost','SMC Trench','Imp to CD','MPT_PT','GC Works','CD_CW','GH','Check Dam','Rock fill dams','Solid Waste Management','Burial Ground','Play fields','Agriculture Activities','Average Wage','Average Days of Employment','HH Completed 100 Days','Timely Payment','CC Roads1','Anganwadi','GP Buildings1','Mandal buildings1','NTR 90 Days','Production of Bricks','Mulbery New','Silk worm New','Horticulture','Avenue','Fish Ponds','Fish Drying Platforms','Nurseries','Payments','FAperformance','OPGK-Perinnials','OPGK-Annuals','UGDrainage'];
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
	getMGNREGSIHHLOverviewData();//mgnregs IHHL
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
	getSessionToken();//Water Tank
	getCMEDOBOverview()//Cmeodb
	getBioMetricDashboardOverViewDtls();//BioMetric
	for(var i in globalComponentNameArr)
	{
		if(globalComponentNameArr[i] == 'NTR 90 Days' || globalComponentNameArr[i] == 'Production of Bricks' || globalComponentNameArr[i] == 'Cattle Drinking Water Troughs' || globalComponentNameArr[i] == 'Raising of Perinnial Fodders' || globalComponentNameArr[i] == 'Fish Ponds' || globalComponentNameArr[i] == 'Fish Drying Platforms' || globalComponentNameArr[i] == 'NTR Rural House' || globalComponentNameArr[i] == 'OPGK-Perinnials' || globalComponentNameArr[i] == 'OPGK-Annuals')
		{
			getNREGSProjectsAbstractNew(globalComponentNameArr[i]);
		}else if(globalComponentNameArr[i] == 'Payments')
		{
			getNregaPaymentsAbsAndOverviewDtls(globalComponentNameArr[i]);
		}else if(globalComponentNameArr[i] == 'Rock Fill Dams' || globalComponentNameArr[i] == "Check Dams" || globalComponentNameArr[i] == "Raising and Maintenance of Nursery" || globalComponentNameArr[i] == "Desilting of Perculation Tanks and Check Dams" || globalComponentNameArr[i] == "Mini Percolation Tanks" || globalComponentNameArr[i] == "Continuous Contour Trenches" || globalComponentNameArr[i] == "Avenue Plantation" || globalComponentNameArr[i] == "Forest Others" || globalComponentNameArr[i] == "Scooping and Dibbling of seed"){
			
			getNREGSForestAbstact(globalComponentNameArr[i]);
		
		}else if(globalComponentNameArr[i] == 'Greenary works Chettu' || globalComponentNameArr[i] == 'Agriculture Related Works' || globalComponentNameArr[i] == 'Rural Sanitation Works' || globalComponentNameArr[i] == 'Soil Moisture Conservation works Neeru' || globalComponentNameArr[i] == 'Works in community lands' || globalComponentNameArr[i] == 'OTHERS' || globalComponentNameArr[i] == 'Institutional Development Works' || globalComponentNameArr[i] == 'Road Works' || globalComponentNameArr[i] == 'Water Harvesting Structures Neeru' || globalComponentNameArr[i] == 'Fisheries work' || globalComponentNameArr[i] == 'AH-Live Stock Related works'|| globalComponentNameArr[i] == 'IJP PROGRAM WORKS'){
			getNregaOtherMCCAbstarctData(globalComponentNameArr[i])
			
		}else if(globalComponentNameArr[i] == 'Renovation and Improvements to existing Check Dams Check Wall' || globalComponentNameArr[i] == 'Road Formation Upto WBM GR II Including CD works' || globalComponentNameArr[i] == 'Formation of Road upto WBM Gr II surface including CD works Connecting SC habitation or Locality in Plain areas' || globalComponentNameArr[i] == 'Construction Of Animal Hostel' || globalComponentNameArr[i] == 'Roads for Unconnected Habitations 2011-12' || globalComponentNameArr[i] == 'Construction of New Check Dam' || globalComponentNameArr[i] == 'Formation of internal road upto WBM Gr II surface including CD works and Drains in SC Habitation or Locality' || globalComponentNameArr[i] == 'Construction of Food Grains Storage Structures of 250MT' || globalComponentNameArr[i] == 'Formation of Road upto WBM Gr II surface including CD works in Tribal areas' || globalComponentNameArr[i] == 'Construction of Village Haats Infrastructure fecilities' || globalComponentNameArr[i] == 'Providing BT road for Sri Anantha Padmanabha Swamy Temple Hill top Road at Padmabnabham' || globalComponentNameArr[i] == 'Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT' || globalComponentNameArr[i] == 'Comprehensive Restoration of minor Irrigation Tank' || globalComponentNameArr[i] == 'Construction of Buildings for women self help group federation' || globalComponentNameArr[i] == 'Work Site Facilities' || globalComponentNameArr[i] == 'Renovation and Improvements to existing Percolation Tank  Mini Percolation tank' || globalComponentNameArr[i] == 'GP level BNRGSK knowledge resource centre 2012-13 and afterwards' || globalComponentNameArr[i] == 'Formation of internal road upto WBM Gr II surface including CD works and Drains in other Habitation or Locality' || globalComponentNameArr[i] == 'Production of Grafts in HNTC' || globalComponentNameArr[i] == 'Improvements of RYTHU BAZAR' || globalComponentNameArr[i] == 'Roads for Unconnected Habitations 2012-13 and afterwards' || globalComponentNameArr[i] == 'HNTC Development' || globalComponentNameArr[i] == 'New Open Well for Drinking water purpose' || globalComponentNameArr[i] == 'Construction of Crematoria Burial Grounds' || globalComponentNameArr[i] == 'Repairs to Existing Check Dam' || globalComponentNameArr[i] == 'Formation of Road upto Gravel surface including CD works to agriculture fields' || globalComponentNameArr[i] == 'Formation of Approach Road upto Gravel surface including CD works to Burial ground' || globalComponentNameArr[i] == 'Construction of Food Grains Storage Structures of 500MT' || globalComponentNameArr[i] == 'Formation of Road upto WBM Gr II surface including CD works Connecting other habitation or Locality in Plain areas' || globalComponentNameArr[i] == 'Raising of Cashew bag seedlings for 2014-15' || globalComponentNameArr[i] == 'Formation of Road upto WBM Gr II surface including CD works Connecting ST habitation or Locality in Plain areas' || globalComponentNameArr[i] == "Azolla Production Unit" || globalComponentNameArr[i] == "Construction of silopits of 3 MTs capacity" || globalComponentNameArr[i] == "Fodder trough for Cattle Cattle drinking water trough" || globalComponentNameArr[i] == "Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze" || globalComponentNameArr[i] == "Raising of Perinnial Fodder" || globalComponentNameArr[i] == "Raising of Silvipasture clubbed with subabul plantation"){
			getNregaCovergancePROtherLevelData(globalComponentNameArr[i]);
		}else if(globalComponentNameArr[i] == 'Cattle Ponds' || globalComponentNameArr[i] == 'Desilting of Drinking Water Tanks' || globalComponentNameArr[i] == 'Animal Husbandry Others' || globalComponentNameArr[i] == 'Comprehensive Restoration of minor Irrigation Tank1'){
			getNregaMCCNewComponetsLevelData(globalComponentNameArr[i])
		}else if(globalComponentNameArr[i] == "Raising and Maintenance of Avenue plantations" || globalComponentNameArr[i] == "Raising and Maintenance of Block Plantations" || globalComponentNameArr[i] =="Raising and Maintenance of nurseries" || globalComponentNameArr[i] == "Soil and Moisture Conservation Works"){
			getNREGSForestProjectsAbstract(globalComponentNameArr[i]);
		}else if(globalComponentNameArr[i] != 'WATER TANK CHLORINATION'){
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
		diseasesIdList : diseasesIdArr
    }
    $.ajax({
      url : "getCaseCountDiseasesFrWeekAndMonth",       
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
			var onLights = result[0].onLights;
			var offLights = result[0].offLights;
			var totalLights = result[0].totalLights;
			//$("#ledMonitoring").html(ledMonitoringData('15/20','10'))
			$(".ledOverAchvmntAllCls").html(ledMonitoringData(''+onLights+'/'+offLights+'',''+totalLights+'',''));
		 // $(".ledOverAchvmntAllCls").html(result[0].onLights+"/"+result[0].offLights+"/"+result[0].totalLights);
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
	var totalTransaction = "";
 function getMeesevaSLAOverviewDtls(){
	 $(".itecMeeSevaSlaKpiAllCls").html(spinner);
	 //var totalTransaction = 0;
	 var json = {
	    
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaSLACatWiseAbstarctDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		//$(".itecMeeSevaSlaKpiAllCls").html(0);
		if (result != null) {
			totalTransaction = parseInt(result.catgryABeyondSLACount)+parseInt(result.catgryBBeyondSLACount);
			getMeesevaKPIOverViewDetails();
			//$(".itecMeeSevaSlaKpiAllCls").html(totalTransaction.toFixed(2)+" Cr / 3499");
		}
	});		
}

function getMeesevaKPIOverViewDetails(){
	
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIOverViewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			if(result.totalMeesevaCentres !=null && result.totalMeesevaCentres>0){
				totalTransaction = totalTransaction + " / "+ result.totalMeesevaCentres;
				$(".itecMeeSevaSlaKpiAllCls").html(totalTransaction);
			}
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
function getMGNREGSIHHLOverviewData(){
	$(".MGNREGSIHHLAllCls").html(spinner);
	
	var json = {
		fromMonth:"201704",
		toMonth:"201707",
		location:"state",
		locationId:"01"
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
		if(result != null )
		{
			var ComPerc =result.subList1[0].completed*100/result.subList1[0].target;
			$(".MGNREGSIHHLAllCls").html(ComPerc.toFixed(2)+'%');
		}else{
			$(".MGNREGSIHHLAllCls").html(" - ");
		}
		
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
					selectionMenu+='<label><i class="fa fa-star starcolorChange CCRoads1Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=CC Roads1" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="CC Roads1" aria-hidden="true" attr_page_id="4"></i> CC Roads</label>';
				}else if(result[i].name == "Anganwadi Buildings")
				{
					selectionMenu+='<label><i class="fa fa-star starcolorChange AnganwadiColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Anganwadi" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="Anganwadi" aria-hidden="true" attr_page_id="4"></i> Anganwadi Buildings</label>';
				}else if(result[i].name == "GP Buildings1")
				{
					selectionMenu+='<label><i class="fa fa-star starcolorChange GPBuildings1Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=GP Buildings1" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="GP Buildings1" aria-hidden="true" attr_page_id="4"></i> GP Buildings</label>';
				}else if (result[i].name == "Mandal Buildings") {
					selectionMenu+='<label><i class="fa fa-star starcolorChange Mandalbuildings1Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Mandal buildings1" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="Mandal buildings1" aria-hidden="true" attr_page_id="4"></i> Mandal Buildings</label>';
					
				}else if(result[i].name == "Greenary works (Chettu)"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange GreenaryworksChettuColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Greenary works Chettu" attr_full_block_name="Greenary works Chettu" style="margin-right:5px;" attr_color_name="gray" attr_block_name="GreenaryworksChettu" aria-hidden="true" attr_page_id="4"></i>Greenary works Chettu</label>';
				}else if(result[i].name == "Soil Moisture Conservation works (Neeru)"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange SoilMoistureConservationworksNeeruColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Soil Moisture Conservation works Neeru" attr_full_block_name="Soil Moisture Conservation works Neeru" style="margin-right:5px;" attr_color_name="gray" attr_block_name="SoilMoistureConservationworksNeeru" aria-hidden="true" attr_page_id="4"></i>Soil Moisture Conservation works Neeru</label>';
				}else if(result[i].name == "Water Harvesting Structures (Neeru)"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange WaterHarvestingStructuresNeeruColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Water Harvesting Structures Neeru" attr_full_block_name="Water Harvesting Structures Neeru" style="margin-right:5px;" attr_color_name="gray" attr_block_name="WaterHarvestingStructuresNeeru" aria-hidden="true" attr_page_id="4"></i>Water Harvesting Structures Neeru</label>';
				}else if(result[i].name == "Renovation and Improvements to existing Check Dams / Check Wall"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange RenovationandImprovementstoexistingCheckDamsCheckWallColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Renovation and Improvements to existing Check Dams Check Wall" attr_full_block_name="Renovation and Improvements to existing Check Dams Check Wall" style="margin-right:5px;" attr_color_name="gray" attr_block_name="RenovationandImprovementstoexistingCheckDamsCheckWall" aria-hidden="true" attr_page_id="4"></i>Renovation and Improvements to existing Check Dams Check Wall</label>';
				}else if(result[i].name == "Construction of Post Harvest facilities (Drying Platform) including Pucca storage facilities of 100MT"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange ConstructionofPostHarvestfacilitiesDryingPlatformincludingPuccastoragefacilitiesof100MTColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT" attr_full_block_name="Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT" style="margin-right:5px;" attr_color_name="gray" attr_block_name="ConstructionofPostHarvestfacilitiesDryingPlatformincludingPuccastoragefacilitiesof100MT" aria-hidden="true" attr_page_id="4"></i>Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT</label>';
				}else if(result[i].name == "Renovation and Improvements to existing Percolation Tank / Mini Percolation tank"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange RenovationandImprovementstoexistingPercolationTankMiniPercolationtankColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Renovation and Improvements to existing Percolation Tank Mini Percolation tank" attr_full_block_name="Renovation and Improvements to existing Percolation Tank Mini Percolation tank" style="margin-right:5px;" attr_color_name="gray" attr_block_name="RenovationandImprovementstoexistingPercolationTankMiniPercolationtank" aria-hidden="true" attr_page_id="4"></i>Renovation and Improvements to existing Percolation Tank Mini Percolation tank</label>';
				}else if(result[i].name == "Construction of Crematoria/Burial Grounds"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange ConstructionofCrematoriaBurialGroundsColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Construction of Crematoria Burial Grounds" attr_full_block_name="Construction of Crematoria Burial Grounds" style="margin-right:5px;" attr_color_name="gray" attr_block_name="ConstructionofCrematoriaBurialGrounds" aria-hidden="true" attr_page_id="4"></i>Construction of Crematoria Burial Grounds</label>';
				}
				else if(result[i].name == "Mulbery"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange MulberyNewColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Mulbery New" attr_full_block_name="Mulbery New" style="margin-right:5px;" attr_color_name="gray" attr_block_name="MulberyNew" aria-hidden="true" attr_page_id="4"></i>Mulbery</label>';
				}
				else if(result[i].name == "Silk Worms"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange SilkwormNewColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Silk worm New" attr_full_block_name="Silk warm New" style="margin-right:5px;" attr_color_name="gray" attr_block_name="SilkwormNew" aria-hidden="true" attr_page_id="4"></i>Silk Worms</label>';
				}else if(result[i].name == "Play Fields"){
					
					selectionMenu+='<label><i class="fa fa-star starcolorChange PlayfieldsColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Play fields" attr_full_block_name="Play fields" style="margin-right:5px;" attr_color_name="gray" attr_block_name="Playfields" aria-hidden="true" attr_page_id="4"></i>Play fields</label>';
				}
				else{
					selectionMenu+='<label><i class="fa fa-star starcolorChange '+result[i].name.replace(/\s+/g, '')+'Color" title="click to add as favourite component." attr_url="MGNREGSDashboard?component='+result[i].name+'" attr_full_block_name="'+result[i].name+'" style="margin-right:5px;" attr_color_name="gray" attr_block_name="'+result[i].name.replace(/\s+/g, '')+'" aria-hidden="true" attr_page_id="4"></i>'+result[i].name+'</label>';
				}
				
			selectionMenu+='</li>';
		}
		if(convergenceId == 1)
		{
			/* selectionMenu+='<li>';
				selectionMenu+='<label><i class="fa fa-star starcolorChange OthersMCCColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Others MCC" attr_full_block_name="Others MCC" style="margin-right:5px;" attr_color_name="gray" attr_block_name="OthersMCC" aria-hidden="true"></i>Others MCC</label>';
			selectionMenu+='</li>'; */
			selectionMenu+='<li>';
				selectionMenu+='<label><i class="fa fa-star starcolorChange PaymentsColor" title="click to add as favourite component." attr_url="MGNREGSDashboard?component=Payments" attr_full_block_name="Payments" style="margin-right:5px;" attr_color_name="gray" attr_block_name="Payments" aria-hidden="true" attr_page_id="4"></i> Payments</label>';
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

function getNREGSForestProjectsAbstract(type,locType,locId,blockName,levelId)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	var json = {
		year : "2017",
		fromDate : '2017-04-01',
		toDate : moment().format("YYYY-MM-DD"),
		type : type,
		locationType: "state",
		locationId : "-1"
	}
	$.ajax({
		url: 'getNREGSForestAbstact',
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
			var notifiedAct = 0;
			for(var i in ajaxresp)
			{
				for(var j in ajaxresp[i].statusList)
				{
					if(ajaxresp[i].statusList[j].id==2){
						notifiedAct = notifiedAct + ajaxresp[i].statusList[j].count;
					}else if(ajaxresp[i].statusList[j].id==3){
						notifiedAct = notifiedAct + ajaxresp[i].statusList[j].count;
					}
				}
			}
			$(".JalavaniAllCls").html(notifiedAct);
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
		fromDate:"2017-11-01",	
		toDate:"2017-12-31"			
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfcDepartOverviewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null && result.length>0){
			for(var i in result){
				if(result[i].departmentName != null && result[i].departmentName == "ITE & C")
					$(".itecEOfficeAllCls").html(result[i].totalCount+'/<small style="font-size:14px;top:0px;">'+result[i].created+'</small>');
			}
		}else{
			$(".itecEOfficeAllCls").html("0/<small style='color:#fff;font-size:14px;top:0px;'>0</small>");
		}
		
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
				$(".itecPromotionsAllCls").html(result[i].investment+" Cr");
			}
		}
	});	
}
function getSessionToken(){
	$(".waterTankChlorinationAllCls").html(spinner);	
	var json = {
		leadName:"village_chlorination@psmri.com",
		category:"vc_2991_12",
		year	:0
	}
	$.ajax({                
		type:'POST',    
		url: 'getSessionToken',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			getWaterBodyCumulativeCounts(result.sessionToken);
		}
		
	});
}
function getWaterBodyCumulativeCounts(sessionToken){
		
		var json = {
			fromDateStr	:moment().subtract(1,'month').startOf("month").format('DD-MM-YYYY'),
			toDateStr 	:moment().format('DD-MM-YYYY'),
			session		:sessionToken
		}
		$.ajax({                
			type:'POST',    
			url: 'getWaterBodyCumulativeCounts',
			dataType: 'json',
			data : JSON.stringify(json),
			beforeSend :   function(xhr){
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			}
		}).done(function(result){
			if(result !=null){
				$(".waterTankChlorinationAllCls").html(result.clorinated);
			}else{
				$(".waterTankChlorinationAllCls").html("0");
			}
				
		});
	}
function getNREGSForestAbstact(type)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	
	var json = {
	   year : "2017",
	   fromDate :"2017-04-01",
	   toDate :moment().format("YYYY-MM-DD"),
	   locationType: "state" ,
	   locationId: "-1",
	   type: type 
	}
	
	$.ajax({
		url: 'getNREGSForestAbstact',
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
function getNregaOtherMCCAbstarctData(type)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	
	var json = {
	   year : "2017",
	   fromDate :"2017-04-01",
	   toDate :moment().format("YYYY-MM-DD"),
	   locationType: "state",
	   locationId: "-1",
	   sublocaType: "state",
	   groupName:type,
	   districtId:""

	}
	
	$.ajax({
		url: 'getNregaOtherMCCAbstarctData',
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
function getNregaMCCNewComponetsLevelData(type)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	
	var json = {
	   year : "2017",
	   fromDate :"2017-04-01",
	   toDate :moment().format("YYYY-MM-DD"),
	   locationType: "state",
	   locationId: "-1",
	   sublocaType: "state",
	   groupName:type,
	   districtId:""

	}
	
	$.ajax({
		url: 'getNregaMCCNewComponetsLevelData',
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
function getNregaCovergancePROtherLevelData(type)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	
	var json = {
	   year : "2017",
	   fromDate :"2017-04-01",
	   toDate :moment().format("YYYY-MM-DD"),
	   locationType: "state",
	   locationId: "-1",
	   sublocaType: "state",
	   groupName:type,
	   districtId:""

	}
	
	$.ajax({
		url: 'getNregaCovergancePROtherLevelData',
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
function getCMEDOBOverview(){
	$(".cmeodbAllCls").html(spinner)
	var json = {
		 sector:"B",
		 fromDate:moment().subtract(20, 'years').startOf('year').format("YYYY-MM-DD"),
		 toDate:moment().format("YYYY-MM-DD")
	}
	$.ajax({                
		type:'POST',    
		url: 'getCMEDOBOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			$(".cmeodbAllCls").html(result.overviewDtls.total+ "/" +result.overviewDtls.aprooved)
		}else{
			$(".cmeodbAllCls").html("0 /0")
		}
	});		
}
function getBioMetricDashboardOverViewDtls(){
	$(".bioMetricDashBoardAllCls").html(spinner);
	var json = {
		deptCode:"27001701024"
	 }
	$.ajax({                
		type:'POST',    
		url: 'getBioMetricDashboardOverViewDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			$(".bioMetricDashBoardAllCls").html(result.totalCount+"/"+result.presentCount);
		} else {
			$(".bioMetricDashBoardAllCls").html("0/0");
		}
		
	});	
}
function ledMonitoringData(onOffVal,totalLightsVal)
{
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-6 text-right">';
			str+='<h2 style="margin-top: 0px">'+onOffVal+'</h2>';
			str+='<p class="">ON/OFF Lights</p>';
		str+='</div>';
		str+='<div class="col-sm-1 text-right">';
			str+='<h2 style="font-size:45px;">/</h2>';
		str+='</div>';
		str+='<div class="col-sm-5 text-right">';
			str+='<h2 class="" style="margin-top: 0px">'+totalLightsVal+'</h2>';
			str+='<p class="">TOTAL LIGHTS</p>';
		str+='</div>';
	str+='</div>';				
	return str;
}