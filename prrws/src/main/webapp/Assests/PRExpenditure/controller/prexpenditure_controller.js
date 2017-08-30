'use strict';
 angular.module('prexpenditureApp').controller('PrexpenditureController', ['$scope', 'PrexpenditureService', function($scope, PrexpenditureService) {
    var self = this;
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
		    self.grossAmount=responceData.grossAmount;
			self.deductions=responceData.deductions;
			self.netAmount=responceData.netAmount;
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
			console.log(responceData);
		    if (locationType == "district") {
				 self.districtData = responceData;
			} else if (locationType == "division") {
				self.divisionData = responceData;
			}
		},
		function(errResponse){
			console.error("Error While fetching data from server.");
		});
	}
	
}]);
