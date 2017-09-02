'use strict';
 angular.module('prexpenditureApp').controller('PrexpenditureController', ['$scope','PrexpenditureService','NgTableParams', function($scope,PrexpenditureService,NgTableParams) {  
    var cntrl = this;
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
				 cntrl.districtData = responceData;
				 cntrl.districtParams = new NgTableParams({}, {dataset: cntrl.districtData});
				 cntrl.districtParams = createUsingFullOptions(cntrl.districtData);
			} else if (locationType == "division") {
				cntrl.divisionData = responceData;		
	            cntrl.divisionParams = new NgTableParams({}, {dataset: cntrl.divisionData});
				cntrl.divisionParams = createUsingFullOptions(cntrl.divisionData);
			}
			
			function createUsingFullOptions(dataList) {
				  var initialParams = {
					count: 5 // initial page size
				  };
				  var initialSettings = {
					// page size buttons (right set of buttons in demo)
					
					counts: [5,10,25,50,100],
					// determines the pager buttons (left set of buttons in demo)
					paginationMaxBlocks: 3,
					paginationMinBlocks: 2,
					dataset: dataList
				  };
				  return new NgTableParams(initialParams, initialSettings);
			}  
			
		},
		function(errResponse){
			console.error("Error While fetching data from server.");
		});
	}
	
	cntrl.textSearchDiv = textSearchDiv;
	function textSearchDiv(field, value) {
		var filter = {};
		filter[field] = value;
		angular.extend(cntrl.divisionParams.filter(), filter);
	}
	
	cntrl.textSearchDist = textSearchDist;
	function textSearchDist(field, value) {
		var filter = {};
		filter[field] = value;
		angular.extend(cntrl.districtParams.filter(), filter);
	}
}]);
$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menu-data-cls").toggle();
});
$(document).on("click",function(){
	$(".menu-data-cls").hide();
});