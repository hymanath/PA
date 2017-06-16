 //Angular Start
	var myApp = angular.module('ruralWaterSupply',[]);
	
	myApp.controller('UserController', function($scope, $http) {
		onloadCalls();

		function onloadCalls(){
			getHabitationCoverageByStatusByLocationType();
			getLabTestDetails();
			getHabitationSupplyDetails();
			getSchemesDetails();
			getSchemeWiseWorkDetails();
			getAssetInfoBetweenDates();
		}
		function highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip)
		{
			'use strict';
			$('#'+id).highcharts({
				 colors: [
						'#ff0000',
						'#00ff00',
						'#0000ff'
					],
				chart: type,
				title: {
					text: null
				},
				subtitle: {
					text: null
				},
				xAxis: xAxis,
				yAxis: yAxis,
				tooltip: tooltip,
				plotOptions: plotOptions,
				legend: legend,
				series: data
			});
		}
		
		
		function getHabitationCoverageByStatusByLocationType()
		{
			var json = {
				locationType:"state",
				year:"2017"
			}
			$http({
				url: 'getHabitationCoverageByStatusByLocationType',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				var dataArr = [];
				for(var i in response.data)
				{
				  for(var j in response.data[i].statusList)
				  {
					var tempArr = [];
					tempArr.push(response.data[i].statusList[j].status);
					tempArr.push(parseInt(response.data[i].statusList[j].count));
					dataArr.push(tempArr);
				  }
				}
				
				var id = 'habitation';
				var type = {
					type: 'column',
					backgroundColor:'transparent'
				};
				var legend = {
					enabled: false
				};
				var yAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
				};
				var xAxis = {
					min: 0,
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					categories: []
				};
				var plotOptions ={ column: {
						colorByPoint: true
					}};
				var tooltip = {
					pointFormat: '{point.y}'
				};

				var data = [{
					name: '',
					data: dataArr,

					dataLabels: {
						enabled: true,
						color: '#FFFFFF',
						align: 'right',
						format: '{point.y}',
					}
				}];
				highcharts(id,type,xAxis,yAxis,legend,data,plotOptions,tooltip);
			});
		}
		function getLabTestDetails()
		{
			var json = {
				year:"2017"
			}
			$http({
				url: 'getLabTestDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 
			}).then(function(response) {
				//$scope.myWelcome = response.data;
				console.log(response.data);
			});
		}
		function getHabitationSupplyDetails(){
			var json = {
				year:"2017"
			}
			$http({
				url: 'getHabitationSupplyDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				console.log(response.data);
			});
		}
		function getSchemesDetails(){
			var json = {
				fromDateStr:"01-12-2016",
				toDateStr:"01-12-2017"
			}
			$http({
				url: 'getSchemesDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				console.log(response.data);
			});
		}
		function getSchemeWiseWorkDetails(){
			var json = {
					formDateStr:"01-01-2016",
					toDateStr:"01-04-2017"
			}
			$http({
				url: 'getSchemeWiseWorkDetails',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				console.log(response.data);
			});
		}
		function getAssetInfoBetweenDates(){ 
			var json = {
				formDateStr:"01-12-2016",
				toDateStr:"01-12-2017"
			}
			$http({
				url: 'getAssetInfoBetweenDates',
				data: JSON.stringify(json),
				method: "POST",
				dataType: 'json', 

			}).then(function(response) {
				//$scope.myWelcome = response.data;
				console.log(response.data);
			});
		}
	});