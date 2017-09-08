'use strict';
 angular.module('prexpenditureApp').controller('PrexpenditureController', ['$scope','PrexpenditureService','NgTableParams', function($scope,PrexpenditureService,NgTableParams,$log) {  
    var cntrl = this;  
	cntrl.blockParamas=['District','Division'];
	cntrl.headingConsolidatedIds=['headingConsolidatedDistrict','headingConsolidatedDivision'];
	cntrl.collapseConsolidatedIds=['collapseConsolidatedDistrict','collapseConsolidatedDivision'];
	cntrl.collapseConsolidatedLinkIds=['#collapseConsolidatedDistrict','#collapseConsolidatedDivision'];
	cntrl.ngTableArr=['cntrl.districtParams','cntrl.divisionParams'];
	cntrl.ngTableHideShow=['cntrl.showHideDistrictTable','cntrl.showHideDivisionTable'];
	cntrl.showHideSpinner=['cntrl.showHideDistrictSpinner','cntrl.showHideDivisionSpinner'];
	cntrl.showHideSearchSpinner=['cntrl.showHideDistrictSearchSpinner','cntrl.showHideDivisionSearchSpinner'];
	cntrl.showHideBlockDataAvailable=['cntrl.showHideDistrictBlockDataAvailable','cntrl.showHideDivisionBlockDataAvailable'];
	   
	cntrl.locationIdArr = ['districtId','DivisionId'];
	cntrl.showHideDistrictSpinner=true;          
	cntrl.showHideDivisionSpinner=true;
	
	cntrl.showHideDistrictSearchSpinner = false;
	cntrl.showHideDivisionSearchSpinner = false;
	
	cntrl.showHideDivisionTable=false;  
	cntrl.showHideDistrictTable=false;
	
	cntrl.showHideDistrictBlockDataAvailable = false;
	cntrl.showHideDivisionBlockDataAvailable = false;
	
	cntrl.showHideGrossAmountSpinner = true;
	cntrl.showHideGrossAmount = false;
	cntrl.showHideDeductionsSpinner = true;
	cntrl.showHiDedeductions = false;
	cntrl.showHideNetAmountSpinner = true;
	cntrl.showHiNetAmount = false;
	
    var url = 'getTotalAmountForOverview';
	var  data = {
		"filterType":"",
		"locationIds":[]
	}
	getPrExpenditureOverAllData(url,data);
	function getPrExpenditureOverAllData(url,data) {
		PrexpenditureService.postData(url,data).
		then(
		function(responceData){
			cntrl.showHideGrossAmountSpinner = false;  
			cntrl.showHideGrossAmount = true;
			cntrl.showHideDeductionsSpinner = false;
			cntrl.showHiDedeductions = true;
			cntrl.showHideNetAmountSpinner = false;
			cntrl.showHiNetAmount = true;   
		    cntrl.grossAmount=responceData.grossAmount;
			cntrl.deductions=responceData.deductions;
			cntrl.netAmount=responceData.netAmount;
			
		},
		function(errResponse){
			console.error("Error While fetching data from server.");
		});
	}
	
	  getLocationWisePrExpenditureData("",0,"district");
	  getLocationWisePrExpenditureData("",0,"division");
	  
	function getLocationWisePrExpenditureData(filterType,locationId,locationType) {
	   var locationIds = [];
		if (locationId != null && locationId > 0 ){
			locationIds.push(locationId);
		}
		if (locationType == null){
			locationType =""
		}
		var  data = {
					"locationType":locationType,
		   			"filterType"  :filterType ,
		   			"locationIds"  :locationIds
		}
		PrexpenditureService.postData('getLocationWisePrExpenditureDtls',data).
		then(
		function(responceData){
		    if (locationType == "district") {
				if(responceData != null && responceData.length > 0){
					 cntrl.districtData = responceData;
					 
					 cntrl.districtParams = createUsingFullOptions(cntrl.districtData,"district");
					 cntrl.showHideDistrictSpinner=false;
					 cntrl.showHideDistrictSearchSpinner = true;
					 cntrl.showHideDistrictTable=true;
				}else{
					cntrl.showHideDistrictSpinner=false;
					cntrl.showHideDistrictBlockDataAvailable = true;
				}
			} else if (locationType == "division") {
				if(responceData != null && responceData.length > 0){
					cntrl.divisionData = responceData;		
					
					cntrl.divisionParams = createUsingFullOptions(cntrl.divisionData,"division");
					cntrl.showHideDivisionSpinner=false;  
					cntrl.showHideDivisionSearchSpinner = true;
					cntrl.showHideDivisionTable=true;
				}else{
					cntrl.showHideDivisionSpinner=false; 
					cntrl.showHideDivisionBlockDataAvailable = true;
				} 
			}
			
			function createUsingFullOptions(dataList,blockLevel) {
				if(blockLevel == 'district'){
					var initialParams = {
						  // initial sort order
						  sorting: { grossAmount: "desc" }
					};
					var initialSettings = {
							counts: [],
							dataset: dataList
					};
					return new NgTableParams(initialParams, initialSettings);
				}else{
					var initialParams = {      
						// initial sort order
						sorting: { grossAmount: "desc" },      
						count: 10 // initial page size
					};
					var initialSettings = {
					// page size buttons (right set of buttons in demo)

					counts: [10,25,50,100],
					// determines the pager buttons (left set of buttons in demo)
					paginationMaxBlocks: 3,
					paginationMinBlocks: 2,
					dataset: dataList
					};
					return new NgTableParams(initialParams, initialSettings);
				}
				
			}  
			
		},
		function(errResponse){
			console.error("Error While fetching data from server.");
		});
	}
	
	
}]);
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});