getLocationWiseFundDetails(3,'highest');
getLocationWiseFundDetails(4,'highest');
getLocationWiseFundDetails(3,'lowest');
getLocationWiseFundDetails(4,'lowest');
function getLocationWiseFundDetails(locationId,dataType)
{
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
	deptIdsList:deptIdsArr,
	type:dataType
  }
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
	deptIdsList:deptIdsArr,
	type:type
  }
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
getDistrictIdName();
function getDistrictIdName()
{
   var stateId = 1;
   var json = {stateId:stateId};
    $.ajax({
     url: 'getDistrictIdName',
     data: JSON.stringify(json),
     type: "POST",
     dataType: 'json', 
     beforeSend: function(xhr) {
     xhr.setRequestHeader("Accept", "application/json");
     xhr.setRequestHeader("Content-Type", "application/json");
     },
    }).done(function(result){
      alert(result);
    });
}

function buildSchemewiseReport(result){
	alert("result");
}