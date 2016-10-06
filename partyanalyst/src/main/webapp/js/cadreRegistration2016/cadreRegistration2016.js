function getRegistrationPersonDetails(){

var jsObj={  
			status :"new",
			voterId : 10,
			familyVoterId :0 ,
			cadreId : 1400698
		};
		$.ajax({          
			type : 'GET',    
			url : 'getRegistrationPersonDetailsAction.action',  
			dataType : 'json',
			data : {task :JSON.stringify(jsObj)} 
		}).done(function(result){
		  console.log(result);
		});

}
getRegistrationPersonDetails();