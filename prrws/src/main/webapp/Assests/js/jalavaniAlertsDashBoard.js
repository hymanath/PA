getJalavaniDashBoardOverview();
 function getJalavaniDashBoardOverview(){
	 var json={
		fromDateStr:"01-01-2017",
		toDateStr:"31-01-2018"
	}
	$.ajax({                
    type:'POST',    
    url: 'getJalavaniDashBoardOverview',
    dataType: 'json',
    data : JSON.stringify(json),
    beforeSend :   function(xhr){
      xhr.setRequestHeader("Accept", "application/json");
      xhr.setRequestHeader("Content-Type", "application/json");
    }
  }).done(function(result){
});
}