
getLocationWiseFundDetails();
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
       //alert(request.responseText);
       //alert(error);
        }
          });
   }
}
