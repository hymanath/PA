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


   
function getSchemeWiseLocationWiseAmountDetails(){
  var levelValues = [];
  var financialYrIdArr = [1,2];
  var sourceIdsArr = [0];
  var schemeIdsArr = [0];
  var deptIdsArr = [0];
  
  var json = {
    levelId : 4, 
    levelValues : levelValues ,
    financialYrIdList : financialYrIdArr,
    fromDateStr : "01-06-2013",       
    toDateStr : "10-06-2020",
	sourceIdsList:sourceIdsArr,
	schemeIdsList:schemeIdsArr,
	deptIdsList:deptIdsArr
  }
  $.ajax({
    url : "getFinancialYearWiseScheameDetails",  
    data : JSON.stringify(json),
    type : "POST",
    dataTypa : 'json',   
    beforeSend: function(xhr) {
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    },
    success : function(ajaxresp){
		if(ajaxresp != null && ajaxresp.length>0)
			buildSchemewiseReport(ajaxresp);
		else
			alert('No data available..');
    },
    error : function(request,error){
      alert(error);
    }
  });
}


function buildSchemewiseReport(result){
	alert("result");
}