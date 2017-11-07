getSolidInfoLocationWise();
function getSolidInfoLocationWise()
{
	
	var json ={
		
	}
	$.ajax({                
		type:'Post',    
		url: 'getSolidInfoLocationWise',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
	});
}