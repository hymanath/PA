getLocationWiseFundDetails(3,'highest');
getLocationWiseFundDetails(4,'highest');
getLocationWiseFundDetails(3,'lowest');
getLocationWiseFundDetails(4,'lowest');
function getLocationWiseFundDetails(locationId,dataType)
{
	var financialYrId = 3;
   var departmentId =0;
   var sourceId = 0;
   var startDate ='02-06-2017';
   var endDate='02-06-2017';
   var locationScopeId=locationId;
   var type =dataType;
   
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
getTotalFunds();
   function getTotalFunds()
   {
		var financialYrId = 3;
		var departmentId =0;
		var sourceId = 0;
		var startDate ='02-06-2017';
		var endDate='02-06-2017';
   
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
       //alert(request.responseText);
       //alert(error);
        }
          });
   }
   getTotalLocationsByScopeId(4);
   getTotalLocationsByScopeId(5);
   getTotalLocationsByScopeId(6);
   function getTotalLocationsByScopeId(locScopeId)
   {
		var financialYrId = 3;
		var departmentId =0;
		var sourceId = 0;
		var startDate ='02-06-2017';
		var endDate='02-06-2017';
		var locationScopeId=locScopeId;
   
      var json = {financialYrId:financialYrId,departmentId:departmentId,sourceId:sourceId,
      startDate:startDate,endDate:endDate,locationScopeId:locationScopeId}
       $.ajax({
        url: 'getTotalLocationsByScopeId',
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
   getSchemeWiseHighestAndLowestFund("highest");
   getSchemeWiseHighestAndLowestFund("lowest");
    function getSchemeWiseHighestAndLowestFund(type)
   {
		var financialYrId = 3;
		var departmentId =0;
		var sourceId = 0;
		var startDate ='02-06-2017';
		var endDate='02-06-2017';
		var type=type;
   
      var json = {financialYrId:financialYrId,departmentId:departmentId,sourceId:sourceId,
      startDate:startDate,endDate:endDate,type:type}
       $.ajax({
        url: 'getSchemeWiseHighestAndLowestFund',
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
    getTotalSchemes();
function getTotalSchemes()
   {
		var financialYrId = 3;
		var departmentId =0;
		var sourceId = 0;
		var startDate ='02-06-2017';
		var endDate='02-06-2017';
   
      var json = {financialYrId:financialYrId,departmentId:departmentId,sourceId:sourceId,
      startDate:startDate,endDate:endDate}
       $.ajax({
        url: 'getTotalSchemes',
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
