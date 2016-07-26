
function getDistrictsForStatesForNotCadre(state){

   var jsObj=
   {				
				stateId:state,
				elmtId:"districtList_d",
                type:"default",
				task:"getDistrictsForState"				
	}
    $.ajax({
          type:'GET',
          url: 'getDistrictsListForStateAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
      
 for(var i in result) {
if(result[i].id == 0){
				  $("#notCadreDistId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#notCadreDistId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
	 }	 
	 $("#notCadreDistId").trigger('chosen:updated');
  });
}
 

 function getConstituenciesForDistrictsForNotcadre(district){
	 
	 var jsObj=
   {				
				districtId:district,
				elmtId:"districtList_d",
                type:"default",
				task:"getConstituenciesForDistricts"				
	}
    $.ajax({
          type:'GET',
          url: 'getConstituenciesForADistrictAjaxAction.action',
          dataType: 'json',
		  data: {task:JSON.stringify(jsObj)}
   }).done(function(result){
   if(result == "noAccess" || result.indexOf("TDP Party's Election Analysis &amp; Management Platform") > -1){
		   location.reload(); 
	   }
	for(var i in result){   
	 if(result[i].id == 0){
				  $("#notCadreConstId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				  $("#notCadreConstId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			   }
	}
	
	$("#notCadreConstId").trigger('chosen:updated');
	});
 }

function getMandalCorporationsByConstituencyForNotcadre(constituency)
	{	
	var jsObj ={					
					constituencyId:constituency
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",//getMandalsForAConstituencyAjaxAction
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				if(result !=null)
				{
					$("#notCadreMandlId").empty();
					
					for(var i in result)
					{
						if(result[i].id == 0){
							$("#notCadreMandlId").append('<option value='+result[i].id+'>Select Mandal/Muncipality/Corporation</option>');
						}
						else{
							$("#notCadreMandlId").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}
					}	
				}
				$("#notCadreMandlId").trigger("chosen:updated");
				});
	}

	function getPanchayatWardByMandalForNotcadre(mandalId){
		var constituencyId = $('#notCadreConstId').val();
			var jsObj={
				mandalId:mandalId,
				constituencyId : constituencyId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				
						$("#notCadrePanchayatId").empty();
					
			for(var i in result){
				if(result[i].id == 0){
							$("#notCadrePanchayatId").append('<option value='+result[i].id+'>Select Panchayat/Ward/Division/City</option>');
						}
						else{
					$("#notCadrePanchayatId").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
						}
			}
			
				$("#notCadrePanchayatId").trigger("chosen:updated");	
			
		});	
			
	}

	function getRelationTypeDetails(){
		$("#relativetypeId").empty();
  var jsObj={
        task:""
      }
      $.ajax({
         type:'GET',
         url:'getAllRelationDetails.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
      if(result !=null){
      $("#relativetypeId").append("<option value='0'>Select RelationType</option>");
        for(var i in result)
            $("#relativetypeId").append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
        }
		$("#relativetypeId").trigger("chosen:updated");
		});

	}

  
	function saveNotCadreDetails(){
		
		var uploadHandler = {
				upload: function(o) {
					$("#savingAjaxImg").css("display","none");
					uploadResult = o.responseText;
					
					showNotCadreSavingStatus(uploadResult);
				}
			};
			
			YAHOO.util.Connect.setForm('saveNotCadre',true);
			YAHOO.util.Connect.asyncRequest('POST','saveNotCadreDetailsAction.action',uploadHandler);
	
	}
	function showNotCadreSavingStatus(result){
		globalNominatedCandId = "";
		if(result.indexOf("SUCCESS") > -1){
			globalNominatedCandId = result.replace( /[^\d.]/g, '' );
			
			$("#notCadreSavId").html("<span style='color: green;font-size:22px;'>Not Cadre Saved Successfully</span>");
			setTimeout(function(){
			$("#notCadreSavId").html("");
			}, 5000);
			getNotCadreDetails(globalNominatedCandId);
		}else {
			setTimeout(function(){
			$("#notCadreSavId").html("<span style='color: red;font-size:22px;'>Application Saved Failed. Please Try Again.</span>");
			}, 1000);
		}
	}
		
	function getCastesForAP(){
	
		var jsObj={}
		$.ajax({
	          type:'GET',
	          url: 'getCastesForAPAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null && result.length > 0){
			   $("#casteId").append('<option value="0">Select Caste</option>');
			   for(var i in result){
				   $("#casteId").append('<option value="'+result[i].id+'">'+result[i].name+'</option>');
			   }
			  
			   $("#casteId").trigger("chosen:updated");	
		   }
	   });
	}
getCastesForAP();

function getNotCadreDetails(globalNominatedCandId){
	
	var jsObj={
		nominatedPostCandiId : globalNominatedCandId
	}
		$.ajax({
	          type:'GET',
	          url: 'getNotCadreDetailsAction.action',
	          dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		   if(result != null)
					{
					buildCadreDetails(result);
					$("#addMemberModalBlock").modal('hide');
					$("#searchDivId").show();
					}
	   });
}
getRelationTypeDetails();