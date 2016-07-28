$(document).ready(function(){
	getLocationWiseCastePositionCount();
    getLocationWiseCasteGroupPositionCount();
});

 function getLocationWiseCastePositionCount(){
  var jsObj={
        LocationLevelId : 0
      }
      $.ajax({
         type:'GET',
         url:'getLocationWiseCastePositionCountAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
    });
 }
 function getLocationWiseCasteGroupPositionCount(){
  var jsObj={
        LocationLevelId : 0
      }
      $.ajax({
         type:'GET',
         url:'getLocationWiseCasteGroupPositionCount.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
    });	
 }
	