var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';
//var subSpinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="subSpinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';silk key
var imagesObj = {
"PRIS":"Group 2344.png","DRAINS":"Group 2345.png","LED MONITORING":"Group 2348.png","UGD":"Group 2359.png","RDP":"Group 2343.png","FUND MANAGMENT SYSTEM":"Group 2352.png","ENGINEERING DEPARTMENT":"Group 2346.png","PANACHAYATI RAJ EXPENDITURE":"Group 2343.png","SPIKE ANALYSIS":"Group 2347.png","MGNREGS":"Group 2357.png","RURAL WATER SUPPLY":"Group 2350.png","ITEC":"Group 2351.png","SWATCH BHARATH IHHL":"Group 2362.png","MGNREGS IHHL":"Group 2362.png",'Labour Budget':"Group 2344.png",'Farm Ponds':"Group 2344.png",'IHHL':"Group 2344.png",'Vermi Compost':"Group 2344.png",'GH':"Group 2344.png",'Check Dams':"Group 2344.png",'Rock Fill Dams':"Group 2344.png",'Solid Waste Management':"Group 2344.png",'Burial Ground':"Group 2344.png",'Play fields':"Group 2344.png",'Agriculture Activities':"Group 2344.png",'Average Wage':"Group 2344.png",'Average Days of Employment':"Group 2344.png",'HH Completed 100 Days':"Group 2344.png",'Timely Payment':"Group 2344.png",'CC Roads1':"Group 2344.png",'Anganwadi':"Group 2344.png",'GP Buildings1':"Group 2344.png",'Mandal buildings1':"Group 2344.png",'NTR 90 Days':"Group 2344.png",'Production of Bricks':"Group 2344.png",'Mulbery New':"Group 2344.png",'Silk worm New':"Group 2344.png",'Horticulture':"Group 2344.png",'Avenue':"Group 2344.png",'Fish Ponds':"Group 2344.png",'Fish Drying Platforms':"Group 2344.png",'Payments':"Group 2344.png",'FAperformance':"Group 2344.png",'OPGK-Perinnials':"Group 2344.png",'OPGK-Annuals':"Group 2344.png",'UGDrainage':"Group 2344.png",'Ntr Jalasiri':"Group 2349.png",'WaterBudget':"Group 2350.png","SWATCH BHARATH PAYMENTS":"Group 2352.png","JALAVANI":"Jalavani.png","JALAVANI":"Jalavani.png",'ASSETS':"assets.png","WATER SOURCE":"water source.png","WORKS":"works.png","PROMOTIONS":"promotions.png","E OFFICE":"eOffice.png","MEESEVA-SLA KPI":"meeSevaSla.png","AP INNOVATION SOCIETY":"APInnovationSoc.png","MEESEVA & KPI":"MeeSevaKPI.png","WATER TANK CHLORINATION":"waterTank.png","coffee":"Group 2907.png","Raising and Maintenance of Nursery":"RaisingandMaintenanceofNursery.png","Desilting of Perculation Tanks and Check Dams":"Desilting of Perculation Tanks and Check Dams.png","Mini Percolation Tanks":"Mini Percolation Tanks.png","Continuous Contour Trenches":"Continuous Contour Trenches.png","Greenary works Chettu":"Greenary works Chettu.png","Agriculture Related Works":"Agriculture Related Works.png","Rural Sanitation Works":"Rural Sanitation Works.png","Soil Moisture Conservation works Neeru":"Soil Moisture Conservation works Neeru.png","Works in community lands":"Works in community lands.png","OTHERS":"OTHERS.png","Institutional Development Works":"Institutional Development Works.png","Road Works":"Road Works.png","Water Harvesting Structures Neeru":"Water Harvesting Structures Neeru.png","Fisheries work":"Fisheries work.png","AH-Live Stock Related works":"AH-Live Stock Related works.png",
"Renovation and Improvements to existing Check Dams Check Wall":"Group 2352.png","Road Formation Upto WBM GR II Including CD works":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works Connecting SC habitation or Locality in Plain areas":"Group 2352.png","Construction Of Animal Hostel":"Group 2352.png","Roads for Unconnected Habitations 2011-12":"Group 2352.png","Construction of New Check Dam":"Group 2352.png","Formation of internal road upto WBM Gr II surface including CD works and Drains in SC Habitation or Locality":"Group 2352.png","Construction of Food Grains Storage Structures of 250MT":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works in Tribal areas":"Group 2352.png","Construction of Village Haats Infrastructure fecilities":"Group 2352.png","Providing BT road for Sri Anantha Padmanabha Swamy Temple Hill top Road at Padmabnabham":"Group 2352.png","Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT":"Group 2352.png","Comprehensive Restoration of minor Irrigation Tank":"Group 2352.png","Construction of Buildings for women self help group federation":"Group 2352.png","Work Site Facilities":"Group 2352.png","Renovation and Improvements to existing Percolation Tank  Mini Percolation tank":"Group 2352.png","GP level BNRGSK knowledge resource centre 2012-13 and afterwards":"Group 2352.png","Formation of internal road upto WBM Gr II surface including CD works and Drains in other Habitation or Locality":"Group 2352.png","Production of Grafts in HNTC":"Group 2352.png","Improvements of RYTHU BAZAR":"Group 2352.png","Roads for Unconnected Habitations 2012-13 and afterwards":"Group 2352.png","HNTC Development":"Group 2352.png","New Open Well for Drinking water purpose":"Group 2352.png","Construction of Crematoria Burial Grounds":"Group 2352.png","Repairs to Existing Check Dam":"Group 2352.png","Formation of Road upto Gravel surface including CD works to agriculture fields":"Group 2352.png","Formation of Approach Road upto Gravel surface including CD works to Burial ground":"Group 2352.png","Construction of Food Grains Storage Structures of 500MT":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works Connecting other habitation or Locality in Plain areas":"Group 2352.png","Raising of Cashew bag seedlings for 2014-15":"Group 2352.png","Formation of Road upto WBM Gr II surface including CD works Connecting ST habitation or Locality in Plain areas":"Group 2352.png","NTR Rural House":"Group 2352.png","Cattle Ponds":"Group 2352.png","Desilting of Drinking Water Tanks":"Group 2352.png","Animal Husbandry Others":"Group 2352.png","Comprehensive Restoration of minor Irrigation Tank1":"Group 2352.png",
"Avenue Plantation":"Group 2352.png","Forest Others":"Group 2352.png","Scooping and Dibbling of seed":"Group 2352.png","IJP PROGRAM WORKS":"Group 2352.png",
"Azolla Production Unit":"Group 2352.png","Construction of silopits of 3 MTs capacity":"Group 2352.png","Fodder trough for Cattle Cattle drinking water trough":"Group 2352.png","Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze":"Group 2352.png","Raising of Perinnial Fodder":"Group 2352.png","Raising of Silvipasture clubbed with subabul plantation":"Group 2352.png","Raising and Maintenance of Avenue plantations":"Group 2352.png","Raising and Maintenance of Block Plantations":"Group 2352.png","Raising and Maintenance of nurseries":"Group 2352.png","Soil and Moisture Conservation Works":"Group 2352.png","CM EODB":"MeeSevaKPI.png","BIOMETRIC DASHBOARD":"BioMetricL.png","Man Days Comparision":"Group 2352.png","AC WORKS":"Group 2352.png","Expenditure":"Group 2352.png","Field Man Days":"Group 2352.png","KEY PERFORMANCE":"Group 2352.png","FA Vacancies":"Group 2352.png","Solid Waste Management":"Group 2352.png","PR e Office":"eOffice.png","Enc Works":"Road Works.png","Enc Roads":"Road Works.png","NEWS":"Cnp News.png","PR BioMetric": "BioMetricL.png","PR NEWS":"Cnp News.png","RD NEWS":"Cnp News.png","RWS NEWS":"Cnp News.png","IT E& C NEWS":"Cnp News.png"
}

var blockClassObject = {
"PRIS":"prisOverAchvmntAllCls","DRAINS":"DRAINSAllCls","LED MONITORING":"LEDMONITORINGAllCls","UGD":"","RDP":"","FUND MANAGMENT SYSTEM":"FUNDMANAGMENTSYSTEMAllCls","ENGINEERING DEPARTMENT":"encOverAchvmntAllCls","PANACHAYATI RAJ EXPENDITURE":"preOverAchvmntAllCls","SPIKE ANALYSIS":"SPIKEANALYSISAllCls","MGNREGS":"MGNREGSAllCls","RURAL WATER SUPPLY":"RURALWATERSUPPLYAllCls","ITEC":"itecOverAchvmntAllCls","SWATCH BHARATH IHHL":"SWATCHBHARATHIHHLAllCls","MGNREGS IHHL":"MGNREGSIHHLAllCls","SWATCH BHARATH PAYMENTS":"SWATCHBHARATHPAYMENTSAllCls",'Labour Budget':'LabourBudgetAllCls','Farm Ponds':'FarmPondsAllCls','IHHL':'IHHLAllCls','Vermi Compost':'VermiCompostAllCls','GH':'GHAllCls','Check Dams':'CheckDamsAllCls','Rock Fill Dams':'RockFillDamsAllCls','Solid Waste Management':'SolidWasteManagementAllCls','Burial Ground':'BurialGroundAllCls','Play fields':'PlayfieldsAllCls','Agriculture Activities':'AgricultureActivitiesAllCls','Average Wage':'AverageWageAllCls','Average Days of Employment':'AverageDaysofEmploymentAllCls','HH Completed 100 Days':'HHCompleted100DaysAllCls','Timely Payment':'TimelyPaymentAllCls','CC Roads1':'CCRoads1AllCls','Anganwadi':'AnganwadiAllCls','GP Buildings1':'GPBuildings1AllCls','Mandal buildings1':'Mandalbuildings1AllCls','NTR 90 Days':'NTR90DaysAllCls','Production of Bricks':'ProductionofBricksAllCls','Mulbery New':'MulberyNewAllCls','Silk worm New':'SilkwormNewAllCls','Horticulture':'HorticultureAllCls','Avenue':'AvenueAllCls','Fish Ponds':'FishPondsAllCls','Fish Drying Platforms':'FishDryingPlatformsAllCls','Payments':'PaymentsAllCls','FAperformance':'FAperformanceAllCls','OPGK-Perinnials':'OPGK-PerinnialsAllCls','OPGK-Annuals':'OPGK-AnnualsAllCls','UGDrainage':'UGDrainageAllCls','Ntr Jalasiri':"NtrJalasiriAllCls",'WaterBudget':"WaterBudgetAllCls",'JALAVANI':"JALAVANIAllCls",'ASSETS':"ASSETSAllCls","WATER SOURCE":"WATERSOURCEAllCls","WORKS":"WORKSAllCls","PROMOTIONS":"PROMOTIONSAllCls","E OFFICE":"EOFFICEAllCls","MEESEVA-SLA KPI":"MEESEVA-SLAKPIAllCls","AP INNOVATION SOCIETY":"APINNOVATIONSOCIETYAllCls","WATER TANK CHLORINATION":"WATERTANKCHLORINATIONAllCls","coffee":"coffeeAllCls","Raising and Maintenance of Nursery":"RaisingandMaintenanceofNurseryAllCls","Desilting of Perculation Tanks and Check Dams":"DesiltingofPerculationTanksandCheckDamsAllCls","Mini Percolation Tanks":"MiniPercolationTanksAllCls","Continuous Contour Trenches":"ContinuousContourTrenchesAllCls","Greenary works Chettu":"GreenaryworksChettuAllCls","Agriculture Related Works":"AgricultureRelatedWorksAllCls","Rural Sanitation Works":"RuralSanitationWorksAllCls","Soil Moisture Conservation works Neeru":"SoilMoistureConservationworksNeeruAllCls","Works in community lands":"WorksincommunitylandsAllCls","OTHERS":"OTHERSAllCls","Institutional Development Works":"InstitutionalDevelopmentWorksAllCls","Road Works":"RoadWorksAllCls","Water Harvesting Structures Neeru":"WaterHarvestingStructuresNeeruAllCls","Fisheries work":"FisheriesworkAllCls","AH-Live Stock Related works":"AH-LiveStockRelatedworksAllCls",
"Renovation and Improvements to existing Check Dams Check Wall":"RenovationandImprovementstoexistingCheckDamsCheckWallAllCls","Road Formation Upto WBM GR II Including CD works":"RoadFormationUptoWBMGRIIIncludingCDworksAllCls","Formation of Road upto WBM Gr II surface including CD works Connecting SC habitation or Locality in Plain areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksConnectingSChabitationorLocalityinPlainareasAllCls","Construction Of Animal Hostel":"ConstructionOfAnimalHostelAllCls","Roads for Unconnected Habitations 2011-12":"RoadsforUnconnectedHabitations2011-12AllCls","Construction of New Check Dam":"ConstructionofNewCheckDamAllCls","Formation of internal road upto WBM Gr II surface including CD works and Drains in SC Habitation or Locality":"FormationofinternalroaduptoWBMGrIIsurfaceincludingCDworksandDrainsinSCHabitationorLocalityAllCls","Construction of Food Grains Storage Structures of 250MT":"ConstructionofFoodGrainsStorageStructuresof250MTAllCls","Formation of Road upto WBM Gr II surface including CD works in Tribal areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksinTribalareasAllCls","Construction of Village Haats Infrastructure fecilities":"ConstructionofVillageHaatsInfrastructurefecilitiesAllCls","Providing BT road for Sri Anantha Padmanabha Swamy Temple Hill top Road at Padmabnabham":"ProvidingBTroadforSriAnanthaPadmanabhaSwamyTempleHilltopRoadatPadmabnabhamAllCls","Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT":"ConstructionofPostHarvestfacilitiesDryingPlatformincludingPuccastoragefacilitiesof100MTAllCls","Comprehensive Restoration of minor Irrigation Tank":"ComprehensiveRestorationofminorIrrigationTankAllCls","Construction of Buildings for women self help group federation":"ConstructionofBuildingsforwomenselfhelpgroupfederationAllCls","Work Site Facilities":"WorkSiteFacilitiesAllCls","Renovation and Improvements to existing Percolation Tank  Mini Percolation tank":"RenovationandImprovementstoexistingPercolationTankMiniPercolationtankAllCls","GP level BNRGSK knowledge resource centre 2012-13 and afterwards":"GPlevelBNRGSKknowledgeresourcecentre2012-13andafterwardsAllCls","Formation of internal road upto WBM Gr II surface including CD works and Drains in other Habitation or Locality":"FormationofinternalroaduptoWBMGrIIsurfaceincludingCDworksandDrainsinotherHabitationorLocalityAllCls","Production of Grafts in HNTC":"ProductionofGraftsinHNTCAllCls","Improvements of RYTHU BAZAR":"ImprovementsofRYTHUBAZARAllCls","Roads for Unconnected Habitations 2012-13 and afterwards":"RoadsforUnconnectedHabitations2012-13andafterwardsAllCls","HNTC Development":"HNTCDevelopmentAllCls","New Open Well for Drinking water purpose":"NewOpenWellforDrinkingwaterpurposeAllCls","Construction of Crematoria Burial Grounds":"ConstructionofCrematoriaBurialGroundsAllCls","Repairs to Existing Check Dam":"RepairstoExistingCheckDamAllCls","Formation of Road upto Gravel surface including CD works to agriculture fields":"FormationofRoaduptoGravelsurfaceincludingCDworkstoagriculturefieldsAllCls","Formation of Approach Road upto Gravel surface including CD works to Burial ground":"FormationofApproachRoaduptoGravelsurfaceincludingCDworkstoBurialgroundAllCls","Construction of Food Grains Storage Structures of 500MT":"ConstructionofFoodGrainsStorageStructuresof500MTAllCls","Formation of Road upto WBM Gr II surface including CD works Connecting other habitation or Locality in Plain areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksConnectingotherhabitationorLocalityinPlainareasAllCls","Raising of Cashew bag seedlings for 2014-15":"RaisingofCashewbagseedlingsfor2014-15AllCls","Formation of Road upto WBM Gr II surface including CD works Connecting ST habitation or Locality in Plain areas":"FormationofRoaduptoWBMGrIIsurfaceincludingCDworksConnectingSThabitationorLocalityinPlainareasAllCls","NTR Rural House":"NTRRuralHouseAllCls","Cattle Ponds":"CattlePondsAllCls","Desilting of Drinking Water Tanks":"DesiltingofDrinkingWaterTanksAllCls","Animal Husbandry Others":"AnimalHusbandryOthersAllCls","Comprehensive Restoration of minor Irrigation Tank1":"ComprehensiveRestorationofminorIrrigationTank1AllCls","Avenue Plantation":"AvenuePlantationAllCls","Forest Others":"ForestOthersAllcls","Scooping and Dibbling of seed":"ScoopingandDibblingofseedAllCls","IJP PROGRAM WORKS":"IJPPROGRAMWORKSAllCls",
"Azolla Production Unit":"AzollaProductionUnitAllCls","Construction of silopits of 3 MTs capacity":"Constructionofsilopitsof3MTscapacityAllCls","Fodder trough for Cattle Cattle drinking water trough":"FoddertroughforCattleCattledrinkingwatertroughAllCls","Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze":"RaisingofFodderMaizeFodderJowarNutrifeedSugargrazeAllCls","Raising of Perinnial Fodder":"RaisingofPerinnialFodderAllCls","Raising of Silvipasture clubbed with subabul plantation":"RaisingofSilvipastureclubbedwithsubabulplantationAllCls","Raising and Maintenance of Avenue plantations":"RaisingandMaintenanceofAvenueplantationsAllCls","Raising and Maintenance of Block Plantations":"RaisingandMaintenanceofBlockPlantationsAllCls","Raising and Maintenance of nurseries":"RaisingandMaintenanceofnurseriesAllCls","Soil and Moisture Conservation Works":"SoilandMoistureConservationWorksAllCls","CM EODB":"CMEODBAllCls","BIOMETRIC DASHBOARD":"BIOMETRICDASHBOARDAllCls","Man Days Comparision":"ManDaysComparisionAllCls","AC WORKS":"ACWORKSAllCls","Expenditure":"ExpenditureAllCls","Field Man Days":"FieldManDaysAllCls","KEY PERFORMANCE":"KEYPERFORMANCEAllCls","FA Vacancies":"FAVacanciesAllCls","Solid Waste Management":"SolidWasteManagementAllCls","PR e Office":"PReOfficeAllCls","Enc Works":"EncWorksAllCls","Enc Roads":"EncRoadsAllCls","NEWS":"NewsAllCls","PR BioMetric":"PRBioMetricAllCls","PR NEWS":"PRNewsAllCls","RD NEWS":"RDNewsAllCls","RWS NEWS":"RWSNewsAllCls","IT E& C NEWS":"ITENewsAllCls"

}

var blockHeadingObject = {
"PRIS":"ACHIEVEMENT","DRAINS":"ACHIEVEMENT","LED MONITORING":"ON&nbsp;/&nbsp;OFF&nbsp LIGHTS","UGD":"ACHIEVEMENT","RDP":"ACHIEVEMENT","FUND MANAGMENT SYSTEM":"TOTAL FUNDS","ENGINEERING DEPARTMENT":"ACHIEVEMENT","PANACHAYATI RAJ EXPENDITURE":"GROSS-AMOUNT","SPIKE ANALYSIS":"7&nbsp; DAYS&nbsp; / &nbsp;30 &nbsp;DAYS&nbsp; DIFFERENCE","MGNREGS":"ACHIEVEMENT","RURAL WATER SUPPLY":"NO.OF HABITATIONS COVERED","ITEC":"TOTAL TRANSACTIONS","SWATCH BHARATH IHHL":"ACHIEVEMENT","SWATCH BHARATH PAYMENTS":"PENDING",'Labour Budget':"ACHIEVED",'Farm Ponds':"ACHIEVED","MGNREGS IHHL":"COMPLETED",'IHHL':"ACHIEVED",'Vermi Compost':"ACHIEVED",'GH':"ACHIEVED",'Check Dams':"ACHIEVED",'Rock Fill Dams':"ACHIEVED",'Solid Waste Management':"HOUSES&nbsp;&nbsp/&nbsp;&nbspFARMERS &nbsp;TONS",'Burial Ground':"ACHIEVED",'Play fields':"ACHIEVED",'Agriculture Activities':"ACHIEVED",'Average Wage':"ACHIEVED",'Average Days of Employment':"ACHIEVED",'HH Completed 100 Days':"ACHIEVED",'Timely Payment':"ACHIEVED",'CC Roads1':"ACHIEVED",'Anganwadi':"ACHIEVED",'GP Buildings1':"ACHIEVED",'Mandal buildings1':"ACHIEVED",'NTR 90 Days':"ACHIEVED",'Production of Bricks':"ACHIEVED",'Mulbery New':"ACHIEVED",'Silk worm New':"ACHIEVED",'Horticulture':"ACHIEVED",'Avenue':"ACHIEVED",'Fish Ponds':"ACHIEVED",'Fish Drying Platforms':"ACHIEVED",'Payments':"PENDING PAYMENTS",'FAperformance':"ACHIEVED",'OPGK-Perinnials':"ACHIEVED",'OPGK-Annuals':"ACHIEVED",'UGDrainage':"ACHIEVED",'Ntr Jalasiri':"ACHIEVED",'WaterBudget':"ACHIEVED",'JALAVANI':"NOTIFIED&nbsp;&nbsp/&nbsp;&nbspIN PROGRESS",'ASSETS':"RWS ASSETS","WATER SOURCE":"UNSAFE","WORKS":"NOT GROUNDED&nbsp;&nbsp;/&nbsp;&nbsp;EXCEED TIME ONGOING WORKS","PROMOTIONS":"COMMITTED INVESTMENT","E OFFICE":"TOTAL PENDENCY","MEESEVA-SLA KPI":"BEYOND SLA&nbsp;&nbsp/&nbsp;&nbspETAAL - KPI","AP INNOVATION SOCIETY":"STARTUPS","MEESEVA & KPI":"ETAAL - KPI","WATER TANK CHLORINATION":"MONTH&nbsp;&nbsp/&nbsp;&nbspTODAY&nbsp; CHLORINATED","coffee":"COFFEE","Raising and Maintenance of Nursery":"ACHIEVED","Desilting of Perculation Tanks and Check Dams":"ACHIEVED","Mini Percolation Tanks":"ACHIEVED","Continuous Contour Trenches":"ACHIEVED","Greenary works Chettu":"ACHIEVED","Agriculture Related Works":"ACHIEVED","Rural Sanitation Works":"ACHIEVED","Soil Moisture Conservation works Neeru":"ACHIEVED","Works in community lands":"ACHIEVED","OTHERS":"ACHIEVED","Institutional Development Works":"ACHIEVED","Road Works":"ACHIEVED","Water Harvesting Structures Neeru":"ACHIEVED","Fisheries work":"ACHIEVED","AH-Live Stock Related works":"ACHIEVED",
"Renovation and Improvements to existing Check Dams Check Wall":"ACHIEVED","Road Formation Upto WBM GR II Including CD works":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works Connecting SC habitation or Locality in Plain areas":"ACHIEVED","Construction Of Animal Hostel":"ACHIEVED","Roads for Unconnected Habitations 2011-12":"ACHIEVED","Construction of New Check Dam":"ACHIEVED","Formation of internal road upto WBM Gr II surface including CD works and Drains in SC Habitation or Locality":"ACHIEVED","Construction of Food Grains Storage Structures of 250MT":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works in Tribal areas":"ACHIEVED","Construction of Village Haats Infrastructure fecilities":"ACHIEVED","Providing BT road for Sri Anantha Padmanabha Swamy Temple Hill top Road at Padmabnabham":"ACHIEVED","Construction of Post Harvest facilities Drying Platform including Pucca storage facilities of 100MT":"ACHIEVED","Comprehensive Restoration of minor Irrigation Tank":"ACHIEVED","Construction of Buildings for women self help group federation":"ACHIEVED","Work Site Facilities":"ACHIEVED","Renovation and Improvements to existing Percolation Tank  Mini Percolation tank":"ACHIEVED","GP level BNRGSK knowledge resource centre 2012-13 and afterwards":"ACHIEVED","Formation of internal road upto WBM Gr II surface including CD works and Drains in other Habitation or Locality":"ACHIEVED","Production of Grafts in HNTC":"ACHIEVED","Improvements of RYTHU BAZAR":"ACHIEVED","Roads for Unconnected Habitations 2012-13 and afterwards":"ACHIEVED","HNTC Development":"ACHIEVED","New Open Well for Drinking water purpose":"ACHIEVED","Construction of Crematoria Burial Grounds":"ACHIEVED","Repairs to Existing Check Dam":"ACHIEVED","Formation of Road upto Gravel surface including CD works to agriculture fields":"ACHIEVED","Formation of Approach Road upto Gravel surface including CD works to Burial ground":"ACHIEVED","Construction of Food Grains Storage Structures of 500MT":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works Connecting other habitation or Locality in Plain areas":"ACHIEVED","Raising of Cashew bag seedlings for 2014-15":"ACHIEVED","Formation of Road upto WBM Gr II surface including CD works Connecting ST habitation or Locality in Plain areas":"ACHIEVED","NTR Rural House":"NTR Rural House","Cattle Ponds":"Cattle Ponds","Desilting of Drinking Water Tanks":"Desilting of Drinking Water Tanks","Animal Husbandry Others":"ACHIEVED","Comprehensive Restoration of minor Irrigation Tank1":"ACHIEVED","Avenue Plantation":"ACHIEVED","Forest Others":"ACHIEVED","Scooping and Dibbling of seed":"ACHIEVED","IJP PROGRAM WORKS":"ACHIEVED",
"Azolla Production Unit":"ACHIEVED","Construction of silopits of 3 MTs capacity":"ACHIEVED","Fodder trough for Cattle Cattle drinking water trough":"ACHIEVED","Raising of Fodder Maize Fodder Jowar Nutrifeed Sugargraze":"ACHIEVED","Raising of Perinnial Fodder":"ACHIEVED","Raising of Silvipasture clubbed with subabul plantation":"ACHIEVED","Raising and Maintenance of Avenue plantations":"ACHIEVED","Raising and Maintenance of Block Plantations":"ACHIEVED","Raising and Maintenance of nurseries":"ACHIEVED","Soil and Moisture Conservation Works":"ACHIEVED","CM EODB":"APPROVED &nbsp;&nbsp / &nbsp;&nbsp TOTAL","BIOMETRIC DASHBOARD":"PRESENT&nbsp;&nbsp/&nbsp;&nbspTOTAL EMPLOYEE","Man Days Comparision":"","AC WORKS":"RURAL CONSTITUENCIES","Expenditure":"LAST&nbsp;YEAR&nbsp; Vs &nbsp;THIS&nbsp;YEAR <small style='font-size:9px'>(LAST&nbspMONTH)</small>","Field Man Days":"TODAY","KEY PERFORMANCE":"PARTIALLY COVERED&nbsp;&nbsp;/&nbsp;&nbsp;QUALITY AFFECTED PERCENTAGE","FA Vacancies":"TOTAL VACANCIES","PR e Office":"TOTAL PENDENCY","Enc Works":"COMPLETED&nbsp;&nbsp/&nbsp;&nbspTECHNICAL SANCATIONED","Enc Roads":"TOTAL ROAD LENGTH","NEWS":"NEWS","PR BioMetric":"PRESENT&nbsp;&nbsp/&nbsp;&nbspTOTAL EMPLOYEE","PR NEWS":"","RD NEWS":"","RWS NEWS":"","IT E& C NEWS":""
}
var overViewArrConsolidated = [];
var overViewIdsArr = [];
var windowWidth =$(window).width();
var componentpageArr=[2,3,4,5,6];
for(var i in componentpageArr){
	getPageWiseComponents(componentpageArr[i]);
}
$("#editList,#saveList").tooltip();
if(windowWidth <500){
	$(".landing-menu").css("display","");
	$(".landing-menu li").css("width","100%");
}else{
	$(".landing-menu").css("display","flex");
	$(".landing-menu li").css("width","16%");
}
/* $(document).on('cut copy paste', function (e) {
	e.preventDefault();
}); */
//getAllConvergenceTypesConsolidated();//mgnrega components

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
			if (compnentName != null && compnentName != "PRIS" && compnentName != "DRAINS" && compnentName != "LED MONITORING" && compnentName != "FUND MANAGMENT SYSTEM" && compnentName != "ENGINEERING DEPARTMENT" && compnentName != "PANACHAYATI RAJ EXPENDITURE" &&  compnentName != "SPIKE ANALYSIS" && compnentName != "MGNREGS" && compnentName != "RURAL DEVELOPMENT" && compnentName != "RURAL WATER SUPPLY" && compnentName != "ITEC" && compnentName != "SWATCH BHARATH IHHL" && compnentName != "SWATCH BHARATH PAYMENTS" && compnentName != "PR e Office" && compnentName != "Enc Works" && compnentName != "Enc Roads" && compnentName != "PROMOTIONS" && compnentName != "Man Days Comparision" && compnentName != "WATER TANK CHLORINATION" && compnentName != "WaterBudget") {
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
							str+='<h5 class="tooltipCls" style="display: inline-block;text-transform:uppercase;cursor:pointer;display: inline-block;position: relative;top: -40px;" data-toogle="tooltip" title="'+result[i].name+'">'+result[i].name.substring(0, 15)+'...</h5>';
							
						}else{
							if(result[i].name != null && result[i].name == 'E OFFICE'){
								str+='<h5 style="display: inline-block;display: inline-block;position: relative;top: -40px;">e Office</h5>';
							}else if(result[i].name != null && result[i].name == 'PR e Office'){
								str+='<h5 style="display: inline-block;display: inline-block;position: relative;top: -40px;">PR e Office</h5>';
							}else if(result[i].name != null && result[i].name == 'NEWS'){
								str+='<h5 style="display: inline-block;text-transform:uppercase;display: inline-block;position: relative;top: -40px;">NEWS - RD,PR,RWS & ITE&C</h5>';
							}else if(result[i].name != null && result[i].name == 'IT E& C NEWS'){
								str+='<h5 style="display: inline-block;text-transform:uppercase;display: inline-block;position: relative;top: -40px;">IT E&C NEWS</h5>';
							}else if(result[i].name != null && result[i].name == 'WaterBudget'){
								str+='<h5 style="display: inline-block;text-transform:uppercase;display: inline-block;position: relative;top: -40px;">water budget</h5>';
							}else{
								str+='<h5 style="display: inline-block;text-transform:uppercase;display: inline-block;position: relative;top: -40px;">'+result[i].name+'</h5>';
							}
							
						}
						if(result[i].name == 'Expenditure')
						{
							str+='<div class="" style="text-align: right">';
								str+='<h3 class="'+blockClassObject[compnentName]+'" style="margin-top: 0px;font-size:24px;"></h3>';
								str+='<p class="" style="margin-top:5px;font-size:12px;">'+blockHeadingObject[compnentName]+'</p>';
								
							str+='</div>';
						}/*else if(result[i].name == 'WATER TANK CHLORINATION'){
							str+='<div class=" text-right"></div>';
							str+='<h3 class="WATERTANKCHLORINATIONAllCls" style="margin-top: 0px"></h3>';
						}*/else if(result[i].name == 'AC WORKS'){
							str+='<div class=" " style="text-align: right">';
							str+='<h3 class="" style="margin-top: 0px">161</h3>';
							str+='<p class="" style="margin-top:5px;font-size:12px;">RURAL CONSTITUENCIES</p>';
							str+='</div>';
						}else if(result[i].name == 'NEWS'){
							str+='<div class="row" style="margin-top: -15px;">';
								str+='<div id="printMediaCountId"></div>';
								//str+='<div id="ElectronicMediaCountId"></div>';
								str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
							str+='</div>';
						}else if(result[i].name == "PR NEWS"){
							 	str+='<div class="row" style="margin-top: -15px;">';
									str+='<div id="printMediaCountIdPR"></div>';
									//str+='<div id="ElectronicMediaCountIdPR"></div>';
									str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
								str+='</div>';
						}else if(result[i].name == "RD NEWS"){
								str+='<div class="row" style="margin-top: -15px;">';
									str+='<div id="printMediaCountIdRD"></div>';
									//str+='<div id="ElectronicMediaCountIdRD"></div>';
									str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
								str+='</div>';
						}else if(result[i].name == "RWS NEWS"){
								str+='<div class="row" style="margin-top: -15px;">';
									str+='<div id="printMediaCountIdRWS"></div>';
									//str+='<div id="ElectronicMediaCountIdRWS"></div>';
									str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
								str+='</div>';
						}else if(result[i].name == "IT E& C NEWS"){
								str+='<div class="row" style="margin-top: -15px;">';
									str+='<div id="printMediaCountIdIT"></div>';
									//str+='<div id="ElectronicMediaCountIdIT"></div>';
								str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
								str+='</div>';
						}
						else{
							str+='<div class="" style="text-align: right">';
								str+='<h3 class="'+blockClassObject[compnentName]+'" style="margin-top: 0px"></h3>';
								if(result[i].name == 'ENGINEERING DEPARTMENT')
								{
									str+='<p class="" style="text-align: right;height: 53px;margin-top:5px;font-size:12px;">'+blockHeadingObject[compnentName]+'</p>';
								}else{
									str+='<p class="" style="margin-top:5px;font-size:12px;">'+blockHeadingObject[compnentName]+'</p>';
								}
								
							str+='</div>';
						}
						
						str+='<div class="block-footer" style="border-top: 1px solid lightgrey;padding-top: 5px;padding-bottom: 5px;">';
							if(result[i].name == 'NEWS'){
								str+='<a class="pull-right" href="'+result[i].url+'" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>';
							}else{
								str+='<i class="fa fa-star starcolorChange '+componentNameWithoutSpace+'Color" attr_url="'+result[i].url
							+'" attr_full_block_name="'+compnentName+'" attr_color_name="green" attr_block_name="'+componentNameWithoutSpace+'" aria-hidden="true"></i>';
							str+='<a class="pull-right" href="'+result[i].url+'" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>';
							}
							
							
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
	var blockId = $(this).attr("attr_blockId");
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
	getNtrJalaSiriLvlWiseData('Ntr Jalasiri','state',"0");//ntr jalasiri
	getRDAbstractDataByType('WaterBudget','state',"0");//Water Budget
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
	getManWorkDaysOfNrega();//ManDays work
	getManWorksExpenditureAbstarct();//ManDays Expenditure
	getFieldManDaysWorkDetails();// Field Man Days
	getLocationWiseFAVacencies();
	getSolidWasteManagementOverAllCounts();//Solid Waste Management
	getEOfcOtherDepartWiseOverviewDetails();//PR eOffice
	getLocationWiseWorksInformation();//ENC Works.
	getStateWiseRoadsInformation();//ENC ROADS.
	getNewsPapersInformation(0);
	getDepartmentWiSeBlockDetails(0);	
	getPRBioMetricDashboardOverViewDtls();
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
	$(".DRAINSAllCls").html(spinner);
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
			$(".DRAINSAllCls").html("0%");
			if(result != null){
				if(result.percentage != null && result.percentage > 0){
					$(".DRAINSAllCls").html(result.percentage+" %");
				}
			}
		});
}

var gblSpikeFmsITCEndDate = moment().format('DD/MM/YYYY');
function getTotalSpikeCases(){
	 $(".SPIKEANALYSISAllCls").html(spinner); 
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
		  if(ajaxresp != null){
			 //$(".SPIKEANALYSISAllCls").html(ajaxresp[0].count);
			 //$(".SPIKEANALYSISAllCls").html(spikeData(ajaxresp));
			 var str ='';
			 if(ajaxresp.weekType == "Decrement"){
				str+='<h5 style="margin-top: 0px"><span style="font-size:24px;">'+ajaxresp.weekPerc+' % </span><span style="font-size:14px;"><i class="fa fa-arrow-down text-success" aria-hidden="true"></i></span>';
			}else{
				str+='<h5 style="margin-top: 0px;"><span style="font-size:24px;">'+ajaxresp.weekPerc+' % </span><span style="font-size:14px;"><i class="fa fa-arrow-up text-danger" aria-hidden="true"></i></span>';
			}
			 if(ajaxresp.monthType == "Decrement"){
				str+='<span style="font-size:24px;"> / '+ajaxresp.mnthPerc+' % </span><span style="font-size:14px;"><i class="fa fa-arrow-down text-success" aria-hidden="true"></i></span>';
				str+='</h5>';
			}else{
				str+='<span style="font-size:24px;"> / '+ajaxresp.mnthPerc+' % </span><span style="font-size:14px;"><i class="fa fa-arrow-up text-danger" aria-hidden="true"></i></span>';
				str+='</h5>';
			}
			$(".SPIKEANALYSISAllCls").html(str);
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
	 $(".LEDMONITORINGAllCls").html(spinner);	
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
		 $(".LEDMONITORINGAllCls").html(0);
		if (result != null ) {
			/* if(result[0].onLights !=null && result[0].onLights>0){
				var onLights = result[0].onLights;
			}else{
				var onLights =0;
			}
			if(result[0].offLights !=null && result[0].offLights>0){
				var offLights = result[0].offLights;
			}else{
				var offLights = 0;
			}
			if(result[0].totalLights !=null && result[0].totalLights>0){
				var totalLights = result[0].totalLights;
			}else{
				var totalLights  = 0;
			} */
			var onLights = result[0].onLights;
			var offLights = result[0].offLights;
			var totalLights = result[0].totalLights;
			//$("#ledMonitoring").html(ledMonitoringData('15/20','10'))
			//$(".LEDMONITORINGAllCls").html(ledMonitoringData(''+onLights+' / '+offLights+'',''+totalLights+'',''));
			//$(".LEDMONITORINGAllCls").html(onLights+" / "+offLights+' / <small style="font-size:14px;top:0px;">'+totalLights+'</small>');
			$(".LEDMONITORINGAllCls").html(onLights+" / "+offLights);
		}
	});		
}

function getHabitationCoverageByStatusByState()
{
	 $(".RURALWATERSUPPLYAllCls").html(spinner);
	 var fullyCoveredHabitationPer=0;
			var json = {
				fromDateStr:'01-04-2017',//"01-01-1977",
				toDateStr:'01-04-2018',//gblEndDate,
				locationType:"state",
				year:"2017",//gblEndDate.split("-")[2],
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
					$(".RURALWATERSUPPLYAllCls").html("0");
					if(response !=null && response.length>0){
						//alert(response[0].totalCount)
						$(".RURALWATERSUPPLYAllCls").html(response[0].totalCount);
						/* for(var i in response){
						  for(var j in response[i].statusList){
							if(response[i].statusList[j].status == "FC"){
								fullyCoveredHabitationPer = response[i].statusList[j].percentage;
							}
						 }
						} */
				     }
					 
				}
			});
}

  function getALlProgramesAmountDetails(){
	  $(".FUNDMANAGMENTSYSTEMAllCls").html(spinner);
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
			$(".FUNDMANAGMENTSYSTEMAllCls").html(0);
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
					for(var i in result){
						if (result[i].id == 0) {
							if(result[i].subList != null && result[i].subList.length > 0){
								for(var j in result[i].subList){
									if(result[i].subList[j].id == 2)
										amountinCrocr = parseFloat(amountinCrocr)+parseFloat(result[i].subList[j].totl);
								}
							}
						}
					}
					if (amountinCrocr != null && amountinCrocr > 0) {
						$(".FUNDMANAGMENTSYSTEMAllCls").html(amountinCrocr.toFixed(3)+"&nbspCr");
					}
			   }
			}
		  });
	}
	var totalTransaction = "";
 function getMeesevaSLAOverviewDtls(){
	 $(".MEESEVA-SLAKPIAllCls").html(spinner);
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
		//$(".MEESEVA-SLAKPIAllCls").html(0);
		if (result != null) {
			totalTransaction = parseInt(result.catgryABeyondSLACount)+parseInt(result.catgryBBeyondSLACount);
			getMeesevaKPIOverViewDetails();
			//$(".MEESEVA-SLAKPIAllCls").html(totalTransaction.toFixed(2)+" Cr / 3499");
		}
	});		
}

function getMeesevaKPIOverViewDetails(){
	
	var json = {
		
	}
	$.ajax({                
		type:'POST',    
		url: 'getMeesevaKPIOverViewDetailsNew',//'getMeesevaKPIOverViewDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result !=null){
			var totalCentres = 0;
			for(var i in result){
					if(i == 0){
						totalCentres=result[0].ruralCount+result[0].urbanCount;
					}
				}
				totalTransaction = totalTransaction + " / "+ totalCentres;
				$(".MEESEVA-SLAKPIAllCls").html(totalTransaction);
			/* if(result.totalMeesevaCentres !=null && result.totalMeesevaCentres>0){
				totalTransaction = totalTransaction + " / "+ result.totalMeesevaCentres;
				$(".MEESEVA-SLAKPIAllCls").html(totalTransaction);
			} */
		}
	});	
}
 
var nregsDate = moment().format('YYYY-MM-DD');
function getNregsLabourBudgetOverAllAchievent()
{
	$(".MGNREGSAllCls").html(spinner);
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
			$(".MGNREGSAllCls").html("0"+"%");
			var labourBudgetPer=0;
			if(ajaxresp != null && ajaxresp.length > 0){
				for(var i in ajaxresp){
					if (ajaxresp[i].perAppLB != null && ajaxresp[i].perAppLB > 0) {
						labourBudgetPer = ajaxresp[i].perAppLB;
					}
				}
			}
			$(".MGNREGSAllCls").html(labourBudgetPer+" %");
		}
	});
}
function getIHHLOverviewData(){
	$(".SWATCHBHARATHIHHLAllCls").html(spinner);
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
		$(".SWATCHBHARATHIHHLAllCls").html(totalCount.toFixed(2)+' %');
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
			$(".MGNREGSIHHLAllCls").html(ComPerc.toFixed(2)+' %');
		}else{
			$(".MGNREGSIHHLAllCls").html(" - ");
		}
		
	});
}
function getSBPaymentsAbstract(){
	$(".SWATCHBHARATHPAYMENTSAllCls").html(spinner);
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
		$(".SWATCHBHARATHPAYMENTSAllCls").html(result.pendingAmount);
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
		}
	}); 
}

function getNtrJalaSiriLvlWiseData(type,locType,locId)
{
	$("."+type.replace(/\s+/g, '')+"AllCls").html(spinner);
	var json = {
		year : "2017",
		fromDate : '2017-04-01',
		toDate : moment().format("YYYY-MM")+'-30',
		locationType : "state"
	}
	$.ajax({
		url: 'getNtrJalaSiriLvlWiseData',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
		}
	});
}
function getLocationWiseAlertStatusCounts()
{
	$(".JALAVANIAllCls").html(spinner);
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
			var inProgressAct = 0;
			for(var i in ajaxresp)
			{
				for(var j in ajaxresp[i].statusList)
				{
					if(ajaxresp[i].statusList[j].id==2){
						notifiedAct = notifiedAct + ajaxresp[i].statusList[j].count;
					}else if(ajaxresp[i].statusList[j].id==3){
						inProgressAct = inProgressAct + ajaxresp[i].statusList[j].count;
					}
				}
			}
			//$(".JALAVANIAllCls").html(jalavaniData(notifiedAct,inProgressAct));
			$(".JALAVANIAllCls").html(notifiedAct+' / '+inProgressAct);
		}
	});
}
function getAssetInfoBetweenDates()
{
	$(".ASSETSAllCls").html(spinner);
	var json = {
		fromDateStr:'01-01-1978',//'01-01-1977',
		toDateStr:'31-12-2028',//moment().format("DD-MM-YYYY"),
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
			$(".ASSETSAllCls").html(totalCount);
		}
	});
}
function getKeyPerformanceIndicatorsInfo()
{
	$(".KEYPERFORMANCEAllCls").html(spinner);
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
			$(".KEYPERFORMANCEAllCls").html(ajaxresp[0].pcPercentage.toFixed(2)+' % / '+ajaxresp[0].qaPercentage.toFixed(2)+' %');
		}
	});
}
function getWaterSourceDeatils2()
{
	$(".WATERSOURCEAllCls").html(spinner);
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
		$(".WATERSOURCEAllCls").html(ajaxresp[0].unSafeGroundWaterSourceCount+ajaxresp[0].unSafeSurfaceWaterSourceCount);
	});
}
function getSchemeWiseWorkDetails()
{
	$(".WORKSAllCls").html(spinner);
	var json = {
		  fromDateStr:'01-04-1978',
		  toDateStr:'01-04-2028',//moment().format("DD-MM-YYYY"),
		  year:'',
		  locationType:'state',
		  type:'graph',
		  filterType:'',
		  filterValue:'',
		  districtValue:''
	  }
	$.ajax({
		url: 'getSchemeWiseWorkDetails2',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp){
			if(ajaxresp != null && ajaxresp.length > 0){
				var notGrounded = parseInt(ajaxresp[0].subList[3].workOngoingCount)+parseInt(ajaxresp[3].subList[3].workOngoingCount); 
				var ongoingExceed = parseInt(ajaxresp[0].subList[0].ongoingPWSExceededCount)+parseInt(ajaxresp[3].subList[0].ongoingPWSExceededCount); 
				$(".WORKSAllCls").html(notGrounded+' / '+ongoingExceed);
			}else{
				$(".WORKSAllCls").html('0 / 0');
			}
		}
	});
	
}
function getEOfcDepartWiseOverviewDetails(){
	$(".EOFFICEAllCls").html(spinner);
	var json = {
		//fromDate:"2017-11-01",	
		//toDate:"2017-12-31"			
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfcDepartOverviewDetailsNew', //'getEOfcDepartOverviewDetails',
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
					$(".EOFFICEAllCls").html(result[i].totalCount+' / <small style="font-size:14px;top:0px;">'+result[i].created+'</small>');
			}
		}else{
			$(".EOFFICEAllCls").html("0 / <small style='color:#fff;font-size:14px;top:0px;'>0</small>");
		}
		
	});		
}
function getAPInnovationSocietyOverview(){
	$(".APINNOVATIONSOCIETYAllCls").html(spinner);
	var json = {
		districtId : 0
	}
	$.ajax({                
		type:'POST',    
		url: 'getCompleteOverviewForAPIS',//'getAPInnovationSocietyOverview',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		var startUpsCount = 0;
			for(var i in result){
				if(result[i].startUps != null && result[i].startUps !='NA'){
					startUpsCount = startUpsCount+parseInt(result[i].startUps);
				}
			}
		$(".APINNOVATIONSOCIETYAllCls").html(startUpsCount);
	});	
}
function getITSectorWiseOverviewDetails(){

$(".PROMOTIONSAllCls").html("10,301.43 Cr");
/*	$(".PROMOTIONSAllCls").html(spinner);
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
				$(".PROMOTIONSAllCls").html("10,301.43 Cr");
			}
		}
	});	*/
}
function getSessionToken(){
	$(".WATERTANKCHLORINATIONAllCls").html(spinner);	
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
		var oneMnthChlValue = 0;
		var oneMnthCheckedValue = 0;
		var oneMnthChrlPerc =0;
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
				oneMnthChlValue = result.clorinated;
				oneMnthCheckedValue = result.checked;
				if(oneMnthChlValue > 0 && oneMnthCheckedValue > 0){
					oneMnthChrlPerc = oneMnthChlValue*100/oneMnthCheckedValue;
				}
			}
			getWaterBodyCumulativeCountsDay(sessionToken,oneMnthChrlPerc);	
		});
		
	}
function getWaterBodyCumulativeCountsDay(sessionToken,oneMnthChrlPerc){
		var oneDayChlValue = 0;
		var oneDayCheckedValue = 0;
		var oneDayChrlPerc =0;
		var json = {
			fromDateStr	:moment().format('DD-MM-YYYY'),
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
				oneDayChlValue = result.clorinated;
				oneDayCheckedValue = result.checked;
				if(oneDayChlValue > 0 && oneDayCheckedValue > 0){
					 oneDayChrlPerc = oneDayChlValue*100/oneDayCheckedValue;
				}
				 //$(".WATERTANKCHLORINATIONAllCls").html(waterClorinationData(oneMnthChrlPerc,oneDayChrlPerc));
				 $(".WATERTANKCHLORINATIONAllCls").html(oneMnthChrlPerc.toFixed(2)+' % / '+oneDayChrlPerc.toFixed(2)+' %');
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
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
			$("."+type.replace(/\s+/g, '')+"AllCls").html(ajaxresp[0].percentage+" %");
		}
	}); 
}
function getCMEDOBOverview(){
	$(".CMEODBAllCls").html(spinner)
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
			$(".CMEODBAllCls").html(result.overviewDtls.aprooved+' / <small style="font-size:14px;top:0px;">'+result.overviewDtls.total+'</small>');
			//$(".EOFFICEAllCls").html(result[i].totalCount+'/<small style="font-size:14px;top:0px;">'+result[i].created+'</small>');
		}else{
			$(".CMEODBAllCls").html("0 / <small style='color:#fff;font-size:14px;top:0px;'>0</small>");
			//$(".EOFFICEAllCls").html("0/<small style='color:#fff;font-size:14px;top:0px;'>0</small>");
		}
	});		
}
function getBioMetricDashboardOverViewDtls(){
	$(".BIOMETRICDASHBOARDAllCls").html(spinner);
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
			$(".BIOMETRICDASHBOARDAllCls").html(result.presentCount+' / <small style="font-size:14px;top:0px;">'+result.totalCount+'</small>');
			//$(".EOFFICEAllCls").html(result[i].totalCount+'/<small style="font-size:14px;top:0px;">'+result[i].created+'</small>');
		} else {
			$(".BIOMETRICDASHBOARDAllCls").html("0 / <small style='color:#fff;font-size:14px;top:0px;'>0</small>");
			//$(".EOFFICEAllCls").html("0/<small style='color:#fff;font-size:14px;top:0px;'>0</small>");
		}
		
	});	
}
function ledMonitoringData(onOffVal,totalLightsVal)
{
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-7 text-right">';
			str+='<h3 style="margin-top: 0px;font-size:20px;">'+onOffVal+'</h3>';
			str+='<p class="">ON / OFF Lights</p>';
		str+='</div>';
		str+='<div class="col-sm-1 text-right">';
			str+='<h3 style="font-size:25px;">/</h3>';
		str+='</div>';
		str+='<div class="col-sm-4 text-right">';
			str+='<h3 class="" style="margin-top: 0px;font-size:12px;">'+totalLightsVal+'</h3>';
			str+='<p class="">TOTAL LIGHTS</p>';
		str+='</div>';
	str+='</div>';				
	return str;
}
function spikeData(ajaxresp)
{
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-12 text-right">';
		if(ajaxresp.weekType == "Decrement"){
			str+='<h5 style="margin-top: 0px"><span class="m_top5" style="font-size:12px;">PREV/LAST&nbsp;7 DAYS - </span> <b>'+ajaxresp.previousWeekCount+'/'+ajaxresp.thisWeekCount+'&nbsp;&nbsp;&nbsp;-<span style="font-size:18px;">'+ajaxresp.weekPerc+'%</span></b><span><i class="fa fa-arrow-down text-success" aria-hidden="true"></i></span>';
			str+='</h5>';
		}else{
			str+='<h5 style="margin-top: 0px;"><span class="m_top5" style="font-size:12px;">PREV/LAST&nbsp;7 DAYS - </span> <b>'+ajaxresp.previousWeekCount+'/'+ajaxresp.thisWeekCount+'&nbsp;&nbsp;&nbsp;-<span style="font-size:18px;">'+ajaxresp.weekPerc+'%</span></b><span><i class="fa fa-arrow-up text-danger" aria-hidden="true"></i></span>';
			str+='</h5>';
		}
			
		str+='</div>';
		str+='<div class="col-sm-12 text-right m_top10">';
		if(ajaxresp.monthType == "Decrement"){
			str+='<h5 class="" style="margin-top: 0px;margin-bottom: 10px;"><span class="m_top5" style="font-size:12px;">PREV/LAST&nbsp;30 DAYS - </span><b>'+ajaxresp.previousMonthCount+'/'+ajaxresp.thisMonthCount+'&nbsp;&nbsp;&nbsp;-<span style="font-size:18px;">'+ajaxresp.mnthPerc+'%</span></b><span><i class="fa fa-arrow-down text-success" aria-hidden="true"></i></span>';
			str+='</h5>';
		}else{
			str+='<h5 class="" style="margin-top: 0px; margin-bottom: 10px;"><span class="m_top5" style="font-size:12px;">PREV/LAST&nbsp;30 DAYS - </span><b>'+ajaxresp.previousMonthCount+'/'+ajaxresp.thisMonthCount+'&nbsp;&nbsp;&nbsp;-<span style="font-size:18px;">'+ajaxresp.mnthPerc+'%</span></b><span><i class="fa fa-arrow-up text-danger" aria-hidden="true"></i></span>';
			str+='</h5>';
		}
			
		str+='</div>';
	str+='</div>';		
	return str;
}
function waterClorinationData(oneMnthChrlPerc,oneDayChrlPerc){
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-6 text-right">';
			str+='<h3 style="margin-top: 0px">'+oneMnthChrlPerc.toFixed(2)+'%</h3>';
			str+='<p class="">MONTH</p>';
		str+='</div>';
		str+='<div class="col-sm-1 text-right">';
			str+='<h3 style="font-size:45px;">/</h3>';
		str+='</div>';
		str+='<div class="col-sm-5 text-right">';
			str+='<h3 class="" style="margin-top: 0px">'+oneDayChrlPerc.toFixed(2)+'%</h3>';
			str+='<p class="">TODAY</p>';
		str+='</div>';
	str+='</div>';
	return str;
}

function getManWorkDaysOfNrega()
{
	$(".ManDaysComparisionAllCls").html(spinner);
	var startDate = moment().format("DD/MM/YYYY");
	var toDateArr = startDate.split('/');
	var json = {
		month : toDateArr[1],
		locationType: "state",
		locationId : "-1",
		sublocationType : "state"
	}
	
	$.ajax({
		url: 'getManWorkDaysOfNregaMonthWise',   //'getManWorkDaysOfNrega',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			$(".ManDaysComparisionAllCls").html("0%");
			if( ajaxresp != null){
				 $(".ManDaysComparisionAllCls").html(mandaysData(ajaxresp));
			}	
		}
	});
}

function getManWorksExpenditureAbstarct(){
	 $(".ExpenditureAllCls").html(spinner); 
	 var month;
	var lastMonthDate = moment().subtract(29,'days').format('YYYY-MM')
	var toDateArr = lastMonthDate.split("-");
	if(toDateArr[1] == 01){
		month = "January"
	}else if(toDateArr[1] == 02){
		month = "February"
	}else if(toDateArr[1] == 03){
		month = "March"
	}else if(toDateArr[1] == 04){
		month = "April"
	}else if(toDateArr[1] == 05){
		month = "May"
	}else if(toDateArr[1] == 06){
		month = "June"
	}else if(toDateArr[1] == 07){
		month = "July"
	}else if(toDateArr[1] == 08){
		month = "August"
	}else if(toDateArr[1] == 09){
		month = "September"
	}else if(toDateArr[1] == 10){
		month = "October"
	}else if(toDateArr[1] == 11){
		month = "November"
	}else if(toDateArr[1] == 12){
		month = "December"
	}
	var json = {
		locationType: "state",
		locationId : "-1",
		monthType : month
		
	}
	
	$.ajax({
		url: 'getManWorksExpenditureAbstarct',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
		  if(ajaxresp != null){
			 //$(".ExpenditureAllCls").html(ajaxresp[0].count);
			 //$(".ExpenditureAllCls").html(mandaysExpData(ajaxresp));
			 if(ajaxresp[0].parameter == 'Increment'){
				$(".ExpenditureAllCls").html(ajaxresp[0].total1617+' / '+ajaxresp[0].total1718+'<small> ('+ajaxresp[0].percentage+'%<span><i class="fa fa-arrow-up text-success" aria-hidden="true"></i></span>)</small>');
				//str+='<h5>('+ajaxresp[0].percentage+'%<span><i class="fa fa-arrow-up text-success" aria-hidden="true"></i></span>)</h5>';
			}else{
				$(".ExpenditureAllCls").html(ajaxresp[0].total1617+' / '+ajaxresp[0].total1718+'<small> ('+ajaxresp[0].percentage+'%<span><i class="fa fa-arrow-up text-success" aria-hidden="true"></i></span>)</small>');
				//str+='<h5>('+ajaxresp[0].percentage+'<span><i class="fa fa-arrow-down text-danger" aria-hidden="true"></i></span>)</h5>';
			}	
		  }
     }
  });
}

function mandaysData(ajaxresp)
{
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-12 text-right">';
		if(ajaxresp[0].percentage != null && ajaxresp[0].percentage > 0){
			str+='<h3 style="margin-top: 0px">'+ajaxresp[0].percentage+' %</h3>';
		}else{
			str+='<h3 style="margin-top: 0px">0%</h3>';
		}
		str+='<p style="text-transform:uppercase;font-size:12px;margin-top:5px;">'+ajaxresp[0].parameter+'</p>';
			
		str+='</div>';
	str+='</div>';		
	return str;
}
$(document).on('click','.validateLoginCls',function(){
	 $("#validateModalId").modal("show");
	 $("#loginNmeIds").val('');
	 $("#loginPassIds").val('');
 });
function userLoginPopUpDetails(){
	 $("#statusUserId").html("");
	 $('#statusMessagePwdId').html("");
	 $("#statusMessageId").html("");
	 
	var userName = $("#loginNmeIds").val();
	var password = $("#loginPassIds").val();
	
	var errorStr = '';
	var errorPwdStr='';
	if(userName == 0 || userName == null || userName == '' || userName.trim().length == 0){
		errorStr += "<p style='color:red'>Username is required</p>";
	}
	if(password == 0 || password == null || password == '' || password.trim().length == 0){
		errorPwdStr += "<p style='color:red'>Password is required</p>";
	}
	if(errorStr.length >0)
	{
		    $('#statusUserId').html(errorStr);
			
		return ;
	}else{
		$("#statusUserId").html("");
	}
	if(errorPwdStr.length >0)
	{   
        $('#statusMessagePwdId').html(errorPwdStr);
	   
		return ;
	}else{
		$('#statusMessagePwdId').html('');
	}
	var json = {
		username: $("#loginNmeIds").val(),
		passwordHashText:$("#loginPassIds").val()
		};
	$("#spinnerImg").show();
   $.ajax({
    url: 'userLogin',
    data: JSON.stringify(json),
    type: "POST",
    dataType: 'json', 
    beforeSend: function(xhr) {
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    },
     success: function(ajaxresp) {
		  if(ajaxresp.responceCode == 0){
			 $("#statusMessageId").html("<span style='color:red;margin-left:110px;'> UserName or Password Invalid</span>"); 
			 $("#spinnerImg").hide();
		  }else{
			  $("#statusMessageId").html("<span style='color:green'>Login Successfull</span>");
			  var redirectWindow=window.open(ajaxresp.url,'_self');
			  $("#spinnerImg").hide();
		  }
     },
   error: function(request,error) { 
   
    }
      });
	
} 
function mandaysExpData(ajaxresp)
{
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-4 text-right">';
			str+='<h4 style="margin-top: 0px"><b>'+ajaxresp[0].total1617+'</b></h4>';
			str+='<p style="font-size: 15px;">Last Fin</br>Last Mnth</p>';
		str+='</div>';
		str+='<div class="col-sm-1 text-right">';
			str+='<h3 style="font-size:45px;">/</h3>';
		str+='</div>';
		str+='<div class="col-sm-3 text-right">';
			str+='<h4 class="" style="margin-top: 0px"><b>'+ajaxresp[0].total1718+'</b></h4>';
			str+='<p style="font-size: 15px;">This Fin</br>Last&nbsp;Mnth</p>';
		str+='</div>';
		str+='<div class="col-sm-4 text-right">';
		if(ajaxresp[0].parameter == 'Increment'){
			str+='<h5>('+ajaxresp[0].percentage+'%<span><i class="fa fa-arrow-up text-success" aria-hidden="true"></i></span>)</h5>';
		}else{
			str+='<h5>('+ajaxresp[0].percentage+'<span><i class="fa fa-arrow-down text-danger" aria-hidden="true"></i></span>)</h5>';
		}	
		str+='</div>';
	str+='</div>';
	
	return str;
}


function getPageWiseComponents(pageId){
	var json = {pageId:pageId} 
	$.ajax({                
		type:'POST',    
		url: 'getPageWiseComponentDetails',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 if(result !=null){
			 buildPageWiseComponents(result,pageId);
		 }
	});		
}

function buildPageWiseComponents(result,pageId){
	var str='';
	for(var i in result){
		str+='<section style="margin-bottom: 100px;display:none;" landing-block="'+result[i].shortName+'"  class="showhideCls">';
			str+='<div class="container">';
			
				str+='<div class="row text-right m_top10">';
					str+='<i class="fa fa-edit editBlockListCls" title="edit priorities" id="editBlockList'+result[i].shortName+'" style="cursor:pointer;font-size:18px;" attr_name="'+result[i].shortName+'"></i>';
					str+='<i class="fa fa-save saveBlockListCls" title="save priorities" id="saveBlockList'+result[i].shortName+'" style="display:none;cursor:pointer;font-size:18px;" attr_name="'+result[i].shortName+'"></i>';
					str+='<span id="errorBlockDivId'+result[i].shortName+'"></span>';
				str+='</div>';
				str+='<div class="row">';
				str+='<div id="sortableListBlocks'+result[i].shortName+'">';
			for(var j in result[i].subList){
					str+='<div class="col-sm-4 draggable-element'+result[i].shortName+'" attr_blockId="'+result[i].id+'" attr_block_listIds="'+result[i].subList[j].id+'">';
							str+='<div class="whiteBlock">';
								str+='<img src="Assests/img/'+imagesObj[result[i].subList[j].name]+'" >';
								if(result[i].subList[j].name != null && result[i].subList[j].name == "E OFFICE"){
									str+='<h5 style="display: inline-block;display: inline-block;position: relative;top: -40px;">e Office</h5>';
								}else if(result[i].subList[j].name != null && result[i].subList[j].name == "PR e Office"){
									str+='<h5 style="display: inline-block;display: inline-block;position: relative;top: -40px;">PR e Office</h5>';
								}else if(result[i].subList[j].name == "IT E& C NEWS"){
									str+='<h5 style="display: inline-block;display: inline-block;position: relative;top: -40px;text-transform:uppercase">IT E&C NEWS</h5>';
								}else if(result[i].subList[j].name == "WaterBudget"){
									str+='<h5 style="display: inline-block;display: inline-block;position: relative;top: -40px;text-transform:uppercase">water budget</h5>';
								}else{
									str+='<h5 style="display: inline-block;display: inline-block;position: relative;top: -40px;text-transform:uppercase">'+result[i].subList[j].name+'</h5>';
								}
								
								
								if(result[i].subList[j].name == "AC WORKS"){
									str+='<div class=" " style="text-align: right">';
									str+='<h3 class="'+result[i].subList[j].name.replace(/\s+/g, '')+'AllCls" style="margin-top: 0px"><span>161</span></h3>';
								}else if(result[i].subList[j].name == "Expenditure"){
									str+='<div class="" style="text-align: right">';
									str+='<h3 class="'+result[i].subList[j].name.replace(/\s+/g, '')+'AllCls" style="margin-top: 0px;font-size:24px;"><span></span></h3>';
								}else if(result[i].subList[j].name == "WATER TANK CHLORINATION"){
									str+='<div class="" style="text-align: right">';
									str+='<h3 class="'+result[i].subList[j].name.replace(/\s+/g, '')+'AllCls" style="margin-top: 0px"><span></span></h3>';
								}else if(result[i].subList[j].name == "Man Days Comparision"){
									str+='<div class="" style="text-align: right">';
									str+='<h3 class="'+result[i].subList[j].name.replace(/\s+/g, '')+'AllCls" style="margin-top: 0px"><span></span></h3>';
								}else if(result[i].subList[j].name == "PR NEWS"){
									 str+='<div  style="text-align: right">';	
										str+='<div class="row" style="margin-top:-15px;">';
											str+='<div id="printMediaCountId1"></div>';
											//str+='<div id="ElectronicMediaCountId1"></div>';
											str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
										str+='</div>';
								}else if(result[i].subList[j].name == "RD NEWS"){
									str+='<div style="text-align: right">';
										str+='<div class="row" style="margin-top:-15px;">';
											str+='<div id="printMediaCountId2"></div>';
											//str+='<div id="ElectronicMediaCountId2"></div>';
									str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
									str+='</div>';
								}else if(result[i].subList[j].name == "RWS NEWS"){
									str+='<div class="" style="text-align: right">';	
										str+='<div class="row" style="margin-top:-15px;">';
											str+='<div id="printMediaCountId3"></div>';
											//str+='<div id="ElectronicMediaCountId3"></div>';
									str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';	
									str+='</div>';
								}else if(result[i].subList[j].name == "IT E& C NEWS"){
										str+='<div class="" style="text-align: right">';	
										str+='<div class="row" style="margin-top:-15px;">';
											str+='<div id="printMediaCountId4"></div>';
											//str+='<div id="ElectronicMediaCountId4"></div>';
										str+='<div class="col-sm-12"><span class="pull-right" style="text-transform:uppercase;margin-top:5px;font-size:12px;">This&nbsp;&nbspMonth&nbsp;&nbsp-ve&nbsp;&nbspArticles</span></div>';
										str+='</div>';
								}else{
									str+='<div class=" " style="text-align: right">';
									str+='<h3 class="'+result[i].subList[j].name.replace(/\s+/g, '')+'AllCls" style="margin-top: 0px"><span></span></h3>';
								}
								
									
									str+='<p class="" style="margin-top:5px;font-size:12px;">'+blockHeadingObject[result[i].subList[j].name]+'</p>';
								str+='</div>';
								str+='<div class="block-footer" style="padding-top: 5px;padding-bottom: 5px;">';
								if(result[i].subList[j].name == 'MGNREGS'){
									str+='<div class="menu-top-selection" style="float:left">';
										str+='<i class="fa fa-star menu-top-selection-icon"></i>';
										str+='<div class="arrow_box_top">';
											str+='<div class="row">';
												str+='<div id="navTabsMenuSelectionId"></div>';
											str+='</div>';
										str+='</div>';
									str+='</div>';
								}else{
									str+='<i class="fa fa-star starcolorChange '+result[i].subList[j].name.replace(/\s+/g, '')+'Color" title="click to add as favourite component."  attr_url="'+result[i].subList[j].url+'" attr_full_block_name="'+result[i].subList[j].name+'" attr_color_name="gray" attr_block_name="'+result[i].subList[j].name.replace(/\s+/g, '')+'" aria-hidden="true" attr_page_id="'+pageId+'"></i>';
								}
								str+='<a class="pull-right" href="'+result[i].subList[j].url+'" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get More Details...</a>';
								
								str+='</div>';
							str+='</div>';
						str+='</div>';
		}
				str+='</div>';
				str+='</div>';
			str+='</div>';
		str+='</section>';
	}
	$("#blockWiseComponentDivId"+pageId).html(str);
	if(pageId == 6)
		getAllConvergenceTypesConsolidated();//mgnrega components
}
$(document).on("click",".editBlockListCls",function(){
	var blockName = $(this).attr("attr_name")
	$("#saveBlockList"+blockName).show();
	$("#editBlockList"+blockName).hide();
	if(blockName == "fms"){
		Sortable.create(sortableListBlocksfms);
	}else if(blockName == "panchayat"){
		Sortable.create(sortableListBlockspanchayat);
	}else if(blockName == "rd"){
		Sortable.create(sortableListBlocksrd);
	}else if(blockName == "rws"){
		Sortable.create(sortableListBlocksrws);
	}else if(blockName == "itec"){
		Sortable.create(sortableListBlocksitec);
	} 
	
	
});
$(document).on("click",".saveBlockListCls",function(){
	var blockName = $(this).attr("attr_name")
	spinner = '<div class="spinner" style="height:20px;width:20px;display:inline-block"><div class="dot1"></div><div class="dot2"></div></div>';
	
	
	var orderList = [];
	var blockId='';
	
	if(blockName == "fms"){
		$("#errorBlockDivId"+blockName).html(spinner);
		 orderList = [];
		 blockId='';
		$(".draggable-elementfms").each(function(){
			orderList.push($(this).attr("attr_block_listIds"));
			blockId = $(this).attr("attr_blockId")
		});
	}else if(blockName == "panchayat"){
		$("#errorBlockDivId"+blockName).html(spinner);
		orderList = [];
		 blockId='';
		$(".draggable-elementpanchayat").each(function(){
			orderList.push($(this).attr("attr_block_listIds"));
			blockId = $(this).attr("attr_blockId")
		});
	}else if(blockName == "rd"){
		$("#errorBlockDivId"+blockName).html(spinner);
		orderList = [];
		 blockId='';
		$(".draggable-elementrd").each(function(){
			orderList.push($(this).attr("attr_block_listIds"));
			blockId = $(this).attr("attr_blockId")
		});
	}else if(blockName == "rws"){
		$("#errorBlockDivId"+blockName).html(spinner);
		orderList = [];
		 blockId='';
		$(".draggable-elementrws").each(function(){
			orderList.push($(this).attr("attr_block_listIds"));
			blockId = $(this).attr("attr_blockId")
		});
	}else if(blockName == "itec"){
		$("#errorBlockDivId"+blockName).html(spinner);
		orderList = [];
		 blockId='';
		$(".draggable-elementitec").each(function(){
			orderList.push($(this).attr("attr_block_listIds"));
			blockId = $(this).attr("attr_blockId")
		});
	} 
	
	var json = {
		id			 :blockId,
		componentIds : orderList
	}
	$.ajax({ 
		type:'POST',    
		url: 'savePageWiseComponents',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if (result.statusCode==0 && result.message=="success"){
			
			if(blockName == "fms"){
				$("#errorBlockDivIdfms").html("Your Priorities Saved Successfully")
				$("#saveBlockListfms").hide();
				$("#editBlockListfms").show();
				Sortable.create(sortableListBlocksfms).destroy();
				setTimeout(function(){
					$("#errorBlockDivIdfms").html(" ")
				},2000)
			}else if(blockName == "panchayat"){
				$("#errorBlockDivIdpanchayat").html("Your Priorities Saved Successfully")
				$("#saveBlockListpanchayat").hide();
				$("#editBlockListpanchayat").show();
				Sortable.create(sortableListBlockspanchayat).destroy();
				setTimeout(function(){
					$("#errorBlockDivIdpanchayat").html(" ")
				},2000)
			}else if(blockName == "rd"){
				$("#errorBlockDivIdrd").html("Your Priorities Saved Successfully")
				$("#saveBlockListrd").hide();
				$("#editBlockListrd").show();
				Sortable.create(sortableListBlocksrd).destroy();
				setTimeout(function(){
					$("#errorBlockDivIdrd").html(" ")
				},2000)
			}else if(blockName == "rws"){
				$("#errorBlockDivIdrws").html("Your Priorities Saved Successfully")
				$("#saveBlockListrws").hide();
				$("#editBlockListrws").show();
				Sortable.create(sortableListBlocksrws).destroy();
				setTimeout(function(){
					$("#errorBlockDivIdrws").html(" ")
				},2000)
			}else if(blockName == "itec"){
				$("#errorBlockDivIditec").html("Your Priorities Saved Successfully")
				$("#saveBlockListitec").hide();
				$("#editBlockListitec").show();
				Sortable.create(sortableListBlocksitec).destroy();
				setTimeout(function(){
					$("#errorBlockDivIditec").html(" ")
				},2000)
			} 
			
		}
	});	
});

function getFieldManDaysWorkDetails(){
	 $(".FieldManDaysAllCls").html(spinner); 
	var json = {
		locationType: "state",
		locationId : "-1",
		sublocationType : "state"
	}
	
	$.ajax({
		url: 'getFieldManDaysWorkDetails',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
		  if(ajaxresp != null){
			 //$(".FieldManDaysAllCls").html(ajaxresp[0].count);
			 $(".FieldManDaysAllCls").html(ajaxresp[0].today);
		  }
     }
  });
}

function getLocationWiseFAVacencies()
{
	$(".FAVacanciesAllCls").html(spinner);
	var json = {
		locationType: "state",
		locationId : "-1",
		sublocationType : "state"
	}
	$.ajax({
		url: 'getLocationWiseEmptyVacenciesDetails',
		data: JSON.stringify(json),
		type: "POST",
		dataType: 'json', 
		beforeSend: function(xhr) {
		  xhr.setRequestHeader("Accept", "application/json");
		  xhr.setRequestHeader("Content-Type", "application/json");
		},
		success: function(ajaxresp) {
			if(ajaxresp != null){
			 //$(".ExpenditureAllCls").html(ajaxresp[0].count);
			 $(".FAVacanciesAllCls").html(ajaxresp[0].count);
		  }
		}
	});
}
var swmStartDate = moment().subtract(1,"month").format("DD-MM-YYYY");
var swmEndDate = moment().format("DD-MM-YYYY");
function getSolidWasteManagementOverAllCounts(){
	$(".SolidWasteManagementAllCls").html(spinner);
	
	var json = {
		fromDate : swmStartDate,
		toDate : swmEndDate,
		locationType :"district" ,
		locationId:0
	}
	$.ajax({                
		type:'POST',    
		url: 'getSolidWasteManagementOverAllCounts',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			var houses = result.houseCollecion.toFixed(2);
			var farmers = result.farmerCollection.toFixed(2);
			var total = result.swmCollection.toFixed(2);
			//$(".SolidWasteManagementAllCls").html(swmData(''+houses+' / '+farmers+'',''+total+'',''));
			//$(".SolidWasteManagementAllCls").html(''+houses+' / '+farmers+' / <small style="font-size:14px;top:0px;">'+total+'</small>');
			$(".SolidWasteManagementAllCls").html(houses+' / '+farmers);
		}
	});
}
function swmData(houseFarmerVal,totalVal)
{
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-8 text-right">';
			str+='<h3 style="margin-top: 0px;font-size:18px;">'+houseFarmerVal+'</h3>';
			str+='<p style="font-size:13px;">HOUSES&nbsp; / &nbsp;FARMERS</p>';
		str+='</div>';
		str+='<div class="col-sm-1 text-right">';
			str+='<h3 style="font-size:30px;">/</h3>';
		str+='</div>';
		str+='<div class="col-sm-3 text-right">';
			str+='<h3 class="" style="margin-top: 0px;font-size:15px;">'+totalVal+'</h3>';
			str+='<p style="font-size:13px;">TOTAL TONS</p>';
		str+='</div>';
	str+='</div>';				
	return str;
}
function getEOfcOtherDepartWiseOverviewDetails(){
	$(".PReOfficeAllCls").html(spinner);
	var json = {
		//fromDate:"2017-11-01",	
		//toDate:"2017-12-31"			
	}
	$.ajax({                
		type:'POST',    
		url: 'getEOfcPrAndRdDepartsOverviewDetails', //'getEOfcDepartOverviewDetails',
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
					$(".PReOfficeAllCls").html(result[i].totalCount+' / <small style="font-size:14px;top:0px;">'+result[i].created+'</small>');
			}
		}else{
			$(".PReOfficeAllCls").html("0 / <small style='color:#fff;font-size:14px;top:0px;'>0</small>");
		}
		
	});		
}

function getLocationWiseWorksInformation(){
	$(".EncWorksAllCls").html(spinner);
	
	var json = {"locationType":"s"}
	$.ajax({                
		type:'POST',    
		url: 'getLocationWiseWorksInformation',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null && result.length > 0){
			$(".EncWorksAllCls").html(''+result[0].completedCount+' / <small style="font-size:14px;top:0px;">'+result[0].technicallySanctionedCount+'</small>');
		}else{
			$(".EncWorksAllCls").html("0/<small style='color:#fff;font-size:14px;top:0px;'>0</small>");
		}
	});
}

function getStateWiseRoadsInformation(){
	$(".EncRoadsAllCls").html(spinner);
	
	var json = {"locationType":"s"}
	$.ajax({                
		type:'POST',    
		url: 'getStateWiseRoadsInformation',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		if(result != null){
			$(".EncRoadsAllCls").html(''+result.totalRoadsLength+'');
		}
	});
}

function jalavaniData(notifiedValue,inProgressValue)
{
	var str='';
	str+='<div class="row" >';
		str+='<div class="col-sm-12 text-right">';
			str+='<h5 style="margin-top: 0px;"><span class="m_top5" style="font-size:15px;">NOTIFIED - </span><b>'+notifiedValue+'</b></h5>';
			//str+='</h5>';
				
		str+='</div>';
		str+='<div class="col-sm-12 text-right m_top10">';
			str+='<h5 style="margin-top: 0px;"><span class="m_top5" style="font-size:15px;">IN&nbsp;PROGRESS - </span><b>'+inProgressValue+'</b></h5>';
		str+='</div>';
	str+='</div>';		
	return str;
}

function getPRBioMetricDashboardOverViewDtls(){
	$(".PRBioMetricAllCls").html(spinner);
	var json = {
		deptCode:"27001701035"
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
			$(".PRBioMetricAllCls").html(result.presentCount+' / <small style="font-size:14px;top:0px;">'+result.totalCount+'</small>');
		} else {
			$(".PRBioMetricAllCls").html("0 / <small style='color:#fff;font-size:14px;top:0px;'>0</small>");
		}
		
	});	
}

var newsStartDate = moment().startOf('month').format("DD-MM-YYYY");
var newsEndDate = moment().format("DD-MM-YYYY");
var url = window.location.href;
var wurl = url.substr(0,(url.indexOf(".com")+4));
if(wurl.length == 3)
	wurl = url.substr(0,(url.indexOf(".in")+3));
var printCount =0,printCount1 =0,printCount2 =0,printCount3 =0,printCount4 =0;
var totalCount=0,totalCount1=0,totalCount2=0,totalCount3=0,totalCount4 =0;
var electCount=0,electCount1=0,electCount2=0,electCount3=0,electCount4=0;
var mainNews=0.0,PRPerc=0.0,RDPerc=0.0,RWSPerc=0.0,ITPerc=0.0;
function getNewsPapersInformation(deptId){
		 $("#printMediaCountId").html(spinner);
		 $("#printMediaCountId1").html(spinner);
		 $("#printMediaCountId2").html(spinner);
		 $("#printMediaCountId3").html(spinner);
		 $("#printMediaCountId4").html(spinner);
		 $("#printMediaCountIdIT").html(spinner);
		 $("#printMediaCountIdRD").html(spinner);
		 $("#printMediaCountIdRWS").html(spinner);
		 $("#printMediaCountIdPR").html(spinner);
		 
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+newsStartDate+"/"+newsEndDate+"/"+deptId
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getPrintMediaCountsDetailsInfo/"+newsStartDate+"/"+newsEndDate+"/"+deptId
		}).then(function(result){
			if(result !=null && result.length>0){
				if(deptId ==0){
					for(var i in result){
					printCount =printCount+result[i].negativCountMain+result[i].negativCountGrpMain;
					totalCount =totalCount+result[i].count;
					}
				}
			}
			if(deptId ==0){
				getBulletinNewsPapersInformation(0);
			}
		});	
}
function getBulletinNewsPapersInformation(deptId){
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndPrograms/"+newsStartDate+"/"+newsEndDate+"/All"+"/"+deptId
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartMentWiseAllNewsBulletinsAndPrograms/"+newsStartDate+"/"+newsEndDate+"/All"+"/"+deptId
		}).then(function(result){
			if(result !=null && result.length>0){
				if(deptId ==0){
					for(var i in result){
						electCount =electCount+result[i].negativCountMain+result[i].negativCountGrpMain;
						totalCount =totalCount+result[i].count;
						
					}
					var count =printCount+electCount;
					mainNews = (count/totalCount*100).toFixed(2);
					var str='';
					str+='<div class="col-sm-12 text-right">';
						str+='<h3 class="" style="margin-top: 0px">'+count+'<small style="color:red;font-size:12px;">('+mainNews+'%)</small></h3>';
					str+='</div>';
					$("#printMediaCountId").html(str);
				}
			 }			 
		});
} 
function getDepartmentWiSeBlockDetails(deptId){
		 $("#printMediaCountId").html(spinner);
		 $("#printMediaCountId1").html(spinner);
		 $("#printMediaCountId2").html(spinner);
		 $("#printMediaCountId3").html(spinner);
		 $("#printMediaCountId4").html(spinner);
		 $("#printMediaCountIdIT").html(spinner);
		 $("#printMediaCountIdRD").html(spinner);
		 $("#printMediaCountIdRWS").html(spinner);
		 $("#printMediaCountIdPR").html(spinner); 
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetails/"+newsStartDate+"/"+newsEndDate+"/"+deptId
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetails/"+newsStartDate+"/"+newsEndDate+"/"+deptId
		}).then(function(result){
			if(result !=null && result.length>0){
			 for(var i in result){
				if(result[i].organizationId ==1699){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									printCount1 =printCount1+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountGrpMain;
									totalCount1 =totalCount1+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].grpCount;
								}
							}
						 }
					}
				}else if(result[i].organizationId ==2170){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									printCount2 =printCount2+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountGrpMain;
									totalCount2 =totalCount2+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].grpCount;
								}
							}
						 }
					}
				}else if(result[i].organizationId ==2171){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									printCount3 =printCount3+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountGrpMain;
									totalCount3 =totalCount3+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].grpCount;
								}
							}
						 }
					}
				}else if(result[i].organizationId ==1698){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									printCount4 =printCount4+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountGrpMain;
									totalCount4 =totalCount4+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].grpCount;
								}
							}
						 }
					}
				}
			  }
		    }
			if(deptId ==0){
				getDepartmentWiSeBlockDetailsForEMN(0);
			}
		});	
}
function getDepartmentWiSeBlockDetailsForEMN(deptId){
		$.ajax({
			url: wurl+"/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetailsForEMN/"+newsStartDate+"/"+newsEndDate+"/"+deptId
			//url: "http://localhost:8085/CommunityNewsPortal/webservice/getDepartmentWiSeBlockDetailsForEMN/"+newsStartDate+"/"+newsEndDate+"/"+deptId
		}).then(function(result){
			if(result !=null && result.length>0){
				for(var i in result){
				 if(result[i].organizationId ==1699){
					 if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){ 
									electCount1 =electCount1+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain;
									totalCount1 =totalCount1+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count;
								}
							  }
						    }
						  }
						var str='';
						str+='<div class="col-sm-12 text-right">';
						var count1 =printCount1+electCount1;
					
						PRPerc = (count1/totalCount1*100).toFixed(2);
						
							str+='<h3 class="" style="margin-top: 0px">'+count1+'<small style="color:red;font-size:12px;">('+PRPerc+'%)</small></h3>';
						str+='</div>';
						$("#printMediaCountId1").html(str);
						$("#printMediaCountIdPR").html(str);
				}else if(result[i].organizationId ==2170){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									electCount2 =electCount2+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain;
									totalCount2 =totalCount2+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count;
								}
							}
						 }
					  }
					var str='';
					str+='<div class="col-sm-12 text-right">';
					var count2 =printCount2+electCount2;
					
					RDPerc = (count2/totalCount2*100).toFixed(2);
						str+='<h3 class="" style="margin-top: 0px">'+count2+'<small style="color:red;font-size:12px;">('+RDPerc+'%)</small></h3>';
					str+='</div>';
					$("#printMediaCountId2").html(str);
					$("#printMediaCountIdRD").html(str);
				}else if(result[i].organizationId ==2171){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){	
									electCount3 =electCount3+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain;
									totalCount3 =totalCount3+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count;
								}
							}
						 }
					}
					var str='';
					str+='<div class="col-sm-12 text-right">';
					var count3 =printCount3+electCount3;
					RWSPerc = (count3/totalCount3*100).toFixed(2);
						str+='<h3 class="" style="margin-top: 0px">'+count3+'<small style="color:red;font-size:12px;">('+RWSPerc+'%)</small></h3>';
					str+='</div>';
					$("#printMediaCountId3").html(str);
					$("#printMediaCountIdRWS").html(str);
				}else if(result[i].organizationId ==1698){
					if(result[i].coreDashBoardVOList !=null && result[i].coreDashBoardVOList.length >0){
						 for( var j in result[i].coreDashBoardVOList){
							if(result[i].coreDashBoardVOList[j].coreDashBoardVOList !=null && result[i].coreDashBoardVOList[j].coreDashBoardVOList.length >0){
								for( var k in result[i].coreDashBoardVOList[j].coreDashBoardVOList){
									electCount4 =electCount4+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].negativCountMain;
									totalCount4 =totalCount4+result[i].coreDashBoardVOList[j].coreDashBoardVOList[k].count;
								}
							}
						 }
					 }
					var str='';
					str+='<div class="col-sm-12 text-right">';
					var count4 =printCount4+electCount4;
					ITPerc = (count4/totalCount4*100).toFixed(2);
						str+='<h3 class="" style="margin-top: 0px">'+count4+'<small style="color:red;font-size:12px;">('+ITPerc+'%)</small></h3>';
					str+='</div>';
					$("#printMediaCountId4").html(str);
					$("#printMediaCountIdIT").html(str);
					}
				}
		     }	
		});
} 