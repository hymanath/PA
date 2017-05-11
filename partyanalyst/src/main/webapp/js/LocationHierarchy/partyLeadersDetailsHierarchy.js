

$(document).on("click",".switch-btn li",function(){
    $(this).closest("ul").find("li").removeClass("active");
    $(this).addClass("active");
	var stateId = $(this).attr("attr_type");
	getDistrictsForStates(stateId);
    
});
function getDistrictsForStates(stateId){
   var jsObj=
   {
		stateId:stateId
						
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsListForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   $("#districtId").html('');
     for(var i in result){
	   if(result[i].id == 0){
          $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
	   }else{
	      $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	  }
   });
  }

$(document).on("change","#districtId",function(){
	var districtId = $("#districtId").val();
		
		var jsObj=
        {				
		districtId:districtId						
	    }
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
	   if(result != null){
		   $("#constituencyId").html('');
        for(var i in result){
	      $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
	   }
	 }
	 
   });		
});

    
$(document).on("change","#constituencyId",function(){
	var constituencyId = $('#constituencyId').val();
		var jsObj ={					
			constituencyId:constituencyId
		};
		$.ajax({
			type : "GET",
			url : "getMandalDetailsByConstituencyAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){
			if(result != null)
			{
				$("#mandalId").html('');
			for(var i in result)
			{
			$("#mandalId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
			}	
		}				
	});		
});

		
		
$(document).on("change","#mandalId",function(){	
    var mandalId = $('#mandalId').val();
	var constituencyId = $('#constituencyId').val();
	 var jsObj ={					
			mandalId:mandalId,
			constituencyId :constituencyId
			};
       	 $.ajax({
			type : "GET",
			url : "getPanchayatWardByMandalAction.action",
			data : {task:JSON.stringify(jsObj)} 
		}).done(function(result){			
				if(result != null)
				{
					$("#panchayatDivId").html('');
				 for(var i in result)
				  {
				$("#panchayatDivId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
				}	
			}				
		});
	});
	
	$(document).on('change','#leaderTypeId',function(){
		  var leadrId = $("#leaderTypeId").val();
		if(leadrId == 0){
			getCommitteeRoles();
		}else{
			getPublicRepresentsDetails();
		}
	  });
	  
	  function getCommitteeRoles(){
    	
    	var jsObj={
    			task:"roles"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getAllCommitteesAction.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
		   if(result != null){
			   $("#designationId").html('');
			    $("#designationId").append('<option value="0"> Select Committee</option>');
			for(var i in result)
			{
				$("#designationId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		   }
    	   });		
      }
	  function getPublicRepresentsDetails(){
    	
    	var jsObj={
    			task:"publicRepresentatives"
    		}
    		$.ajax({
    			  type:'GET',
    			  url: 'getPublicRepresentativeTypes.action',
    			  data: {task:JSON.stringify(jsObj)}
    	   }).done(function(result){
		   var str ='';
    	if(result != null){
			  $("#designationId").html('');
             $("#designationId").append('<option value="0"> Select Public Representative</option>');
			for(var i in result)
			{ 
				$("#designationId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			}
		}
			
         
    	   });	
      }
	  
	$(document).on("click","#btnId",function(){
		  var locationArr=[];
		  var designationIdArr=[];
		  locationArr.push(19);
		  designationIdArr.push(1);
		  designationIdArr.push(2);
		  var jsObj =
		  {
			levelId:3,
			representativeTypeId :1,
			locationIds:locationArr,
			designationIds:designationIdArr,
			firstIndex:0,
			maxIndex:1000
		  }
		  $.ajax({  
			type:'GET',            
			url: 'getPartyLeadersDeatailsAction.action',
			data: {task :JSON.stringify(jsObj)}
		  }).done(function(result){
               if(result != null){
				   for(var i in result){
					   var str = '';
					  str +='<table class="table table-bordered">';
							 str +='<thead>';
								 str +='<tr>';
									 str +='<th>District</th>';
									 str +='<th>Constituency</th>';
									 str +='<th>Cader Name</th>';
									 str +='<th>Designation</th>';
									 str +='<th>Name</th>';
									 str +='<th>Contact No</th>';
								 str +='</tr>';
							 str +='</thead>';
					      str +='<tbody>';
								str +='<tr>';
								for(var i in result){
									str +='<td>'+district+'</td>';
									str +='<td>'+constituency+'</td>';
									str +='<td>'+cadreName+'</td>';
									str +='<td>'+designation+'</td>';
									str +='<td>'+mobileNo+'</td>';
									str +='<td>'+imageURL+'</td>';
								}
									
								str +='</tr>';
							str +='</tbody>';
							$("#partyLeaderDetailsId").html(str);
						str +='</table>';
				   }
			   }
		  });
	
    			
});
	  