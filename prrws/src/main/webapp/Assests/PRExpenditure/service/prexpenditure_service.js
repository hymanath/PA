'use strict';
angular.module('prexpenditureApp').factory('PrexpenditureService', ['$http', function($http){
    var factory = {
    	postData:postData
    };
    return factory;
    function postData(url, data) {   
		var config = {
			headers : {
				'Content-Type': 'application/json'
			}
		};
		return $http.post(url, data, config)
			.then(getDataComplete)
			.catch(getDataFailed);

		function getDataComplete(response, status, headers, config) {
			return response.data;               
		}

		function getDataFailed(data, status, headers, config) {
			logger.error('XHR Failed for postData.' +
						 "\nData: " + data +
						 "\nStatus: " + status +
						 "\nHeaders: " + headers +
						 "\nConfig: " + config);
		}
	}
}]);
  