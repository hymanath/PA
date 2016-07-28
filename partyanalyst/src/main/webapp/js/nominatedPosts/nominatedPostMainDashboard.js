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
 function getAllPositions(){
	var jsObj={}
      $.ajax({
         type:'GET',
         url:'getAllPositionsAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  buildPositions(result);
    }); 
 }
 function buildPositions(result){
	 var str ='';
	 if(result != null && result.length > 0){
		  str+='<li role="presentation" class="active"><a href="#departments1" aria-controls="departments1" role="tab" data-toggle="tab">Labour </a></li>';
		 for(var i in result){
			str+='<li role="presentation" class="active"><a href="#departments1" aria-controls="departments1" role="tab" data-toggle="tab" attr_positionId="'+result[i].name+'">'+result[i].name+'</a></li>'; 
		 }
	 }
	 $("#positionsDiv").html(str);
 }
 getAllPositions();
	