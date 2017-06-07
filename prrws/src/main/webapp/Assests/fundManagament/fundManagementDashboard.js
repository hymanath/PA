+function ($) {
	onLoadCalls();
	onLoadClicks();
	
	///Please do write the onload calls in the onLoadCalls function and the clicks in the onLoadClicks and initialisation of any kind of plugin in the onLoadInitialisations
	function onLoadCalls()
	{
		getLocationWiseAmountDetails();
		getAverageFundForAnyLevel();
		getAverageFundForScheme();
		getTotalFunds();
		getLocationWiseFundDetails();
	}
	function onLoadClicks()
	{
		
	}
	function onLoadInitialisations()   
	{
		
	}
	
	function getAverageFundForScheme(){
		var financialYrIdList = [1]; 
		var deptId = 0;
		var sourceId = 0;
		var schemeId = 0;
		var json = {
			deptId : deptId,
			sourceId : sourceId,
			schemeId : schemeId,
			financialYrIdList : financialYrIdList,
			fromDateStr : "01/06/2017",       
			toDateStr : "10/06/2017"
		}
		$.ajax({
			url : "getAverageFundForScheme",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				alert(ajaxresp);
			},
			error : function(request,error){
				alert(error);
			}
		});
	}
	function getAverageFundForAnyLevel(){
		var levelValues = [];
		var financialYrIdList = [1]; 
		var deptId = 0;
		var sourceId = 0;
		var json = {
			levelId : 3, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			fromDateStr : "01/06/2017",       
			toDateStr : "10/06/2017"   
		}
		$.ajax({
			url : "getAverageFundForAnyLevel",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				alert(ajaxresp);
			},
			error : function(request,error){
				alert(error);
			}
		});
	}
	function getLocationWiseAmountDetails(){   
		var levelValues = [];
		var financialYrIdList = [1];
		var json = {
			levelId : 3, 
			levelValues : levelValues ,
			financialYrIdList : financialYrIdList,
			sortingType : "name",      //name,count    
			order : "desc",   //asc,desc
			fromDateStr : "01/06/2017",       
			toDateStr : "10/06/2017"
		}
		$.ajax({
			url : "getLocationWiseAmountDetails",  
			data : JSON.stringify(json),
			type : "POST",
			dataTypa : 'json',   
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(ajaxresp){
				buildLocationWiseAmountDetails(ajaxresp);
			},
			error : function(request,error){
				alert(error);
			}
		});
	}
	function buildLocationWiseAmountDetails(result)
	{
		var str='';
		
	}
	function getLocationWiseFundDetails()
	{
		var financialYrId = 3;
		var departmentId =0;
		var sourceId = 0;
		var startDate ='02/06/2017';
		var endDate='02/06/2017';
		var locationScopeId=4;
		var type ='highest';

		var json = {financialYrId:financialYrId,departmentId:departmentId,sourceId:sourceId,
		startDate:startDate,endDate:endDate,locationScopeId:locationScopeId,type:type}
		$.ajax({
			url: 'getLocationWiseFundDetails',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp) {
				
			},
			error: function(request,error) { 
				//alert(request.responseText);
				//alert(error);
			}
		});
	}
	function buildLocationWiseFundDetails()
	{
		
	}
   function getTotalFunds()
   {
		var financialYrId;
		var departmentId;
		var sourceId;
		var startDate;
		var endDate;

		var json = {financialYrId:financialYrId,departmentId:departmentId,sourceId:sourceId,
		startDate:startDate,endDate:endDate}
		$.ajax({
			url: 'getTotalFunds',
			data: JSON.stringify(json),
			type: "POST",
			dataType: 'json', 
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success: function(ajaxresp) {
			
			},
			error: function(request,error) { 
		
		
			}
		});
   }


}(jQuery);