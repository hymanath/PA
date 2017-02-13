function getDistrictsForStates(state,id,num){
	$('#districtIdImg').show();
	if(id == "statesDivId"){
			$("#districtId").empty();
			$("#constituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();			
			$("#districtId").append('<option value="0">Select District</option>');		
			$("#constituencyId").append('<option value="0">Select Constituency</option>');		
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
			
			$("#districtId").trigger("chosen:updated");
			$("#constituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
			
			 
	}
	//document.getElementById('membershipId').checked = true;
	
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
		//$("#searchDataImgForDist").hide();
	     //$("#districtId").append('<option value="-1">Please Select District</option>');
     for(var i in result){
		 $("#statesDivIdImg").hide();
		 if(id == "statesDivId"){
			   if(result[i].id == 0){
				  $("#districtId").append('<option value='+result[i].id+'>ALL</option>');
			   }else{
				   if(result[i].id != 517)
				  $("#districtId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				   
			   }
		 }
	 }
	 if(id == "statesDivId"){
			$("#districtId").trigger("chosen:updated");
	 }
   });
  }
  
  function getConstituenciesForDistricts(district,id,num){
	
	 //debugger;
	 if(id == "districtId"){
		 //hideDetails();
		 $("#constituencyIdImg").show();
			$("#searchDataImgForConst").show();
			//refreshExistingDetails();
			$("#constituencyId").empty();
			$("#mandalList").empty();
			$("#panchaytList").empty();					
			$("#constituencyId").append('<option value="0">Select Constituency</option>');		
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');		
			$("#panchaytList").append('<option value="0">Select Village/ward</option>');			
			$("#constituencyId").trigger("chosen:updated");
			$("#mandalList").trigger("chosen:updated");
			$("#panchaytList").trigger("chosen:updated");
			
	 }
	
	/* if(district == 0){
		if(id == "districtId")
			getConstituenciesForState($('#statesDivId').val(),'constituencyId');
		/*else if(id == "notCadreDistId")
			getConstituenciesForState($('#notCadreStateId').val(),'notCadreConstId');
		else if(id == "changedistrictId")
			getConstituenciesForState($('#changestateId').val(),'changeConstiId');
		else
			getConstituenciesForState($('#nominatedStaeId').val(),'nominatdConstId');		
		return;
	} */
	
	//document.getElementById('membershipId').checked = true;
	
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
	   if(id == "districtId"){
		    $("#constituencyIdImg").hide();
			$("#constituencyId").empty();
	   }
	   $("#searchDataImgForConst").hide();
	    //$("#constituencyId").append('<option value="-1">Please Select Constituency</option>');
     for(var i in result){
		 if(id == "districtId"){
		   if(result[i].id == 0){
			  $("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
		   }else{
			  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
		   }
		 }
	 }
	 if(id == "districtId"){
		$("#constituencyId").trigger("chosen:updated");
	 }
   });
  }
  
  function getMandalCorporationsByConstituency(num,id)
	{	
	
	var constituencyId  =0;
	if(id == "constituencyId"){
		//hideDetails();
		$("#mandalListImg").show();
			$("#searchDataImgForMandl").show();
			//refreshExistingDetails();
			constituencyId = $('#constituencyId').val();
			$("#mandalList  option").remove();
			$("#mandalList").append('<option value="0">Select Mandal/Municipality</option>');
			$("#panchaytList  option").remove();
			$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
			//document.getElementById('membershipId').checked = true;
	}
			
				var jsObj ={					
					constituencyId:constituencyId
				};
				 $.ajax({
					type : "GET",
					url : "getMandalDetailsByConstituencyAction.action",
					data : {task:JSON.stringify(jsObj)} 
				}).done(function(result){
				if(result !=null)
				{
					if(id == "constituencyId"){
						$("#mandalListImg").hide();
						$("#mandalList").empty();
						//$("#mandalList").append('<option value="0">Select Mandal</option>');
					}
					for(var i in result)
					{
						if(id == "constituencyId"){
							if(result[i].locationId == 120 || result[i].locationId == 1124)
								$("#mandalList").append('<option value="0">Select Mandal/Muncipality</option>');
							$("#mandalList").append('<option value="'+result[i].locationId+'">'+result[i].locationName+'</option>');
						}
					}	
				}
				if(id == "constituencyId"){
					$("#mandalList").trigger("chosen:updated");
				}			
				});
	}
	function getPanchayatWardByMandal(num,id){

			var mandalId=0;
			var constituencyId = 0; //cadreSearchDtls
		if(id == "mandalList"){	
			//hideDetails();
                $("#panchaytListImg").show();		
				$("#searchDataImgForPanc").show();
				//refreshExistingDetails();
				mandalId=$("#mandalList").val();
				constituencyId = $('#constituencyId').val();
				$("#panchaytList  option").remove();
				$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
		}
			var jsObj={
				mandalId:mandalId,
				constituencyId : constituencyId
			}
			$.ajax({
				type : "POST",
				url : "getPanchayatWardByMandalAction.action",
				data : {task:JSON.stringify(jsObj)} 
			}).done(function(result){
				if(id == "mandalList"){
					$("#panchaytListImg").hide();
						$("#panchaytList").empty();
						//$("#panchaytList").append('<option value="0">Select Panchayat</option>');
				}
			for(var i in result){
				if(id == "mandalList"){
					if(result[i].locationId == 0){
						$("#panchaytList").append('<option value="0">Select Village/Ward</option>');
					}
					else{
					$("#panchaytList").append('<option value='+result[i].locationId+'>'+result[i].locationName+'</option>');
					}
				}
			}
			if(id == "mandalList"){
				$("#panchaytList").trigger("chosen:updated");	
			}
		});	
	}
	function getRolesBasedReport(){
		var committeeLevelIdsListArr = []; 
		var designationIdsArr = [];
		var committeeTypeId = $("#committeeTypeId").val();
		alert(committeeTypeId);
		var committeeLvlId = $("#committeeLevelTypeId").val();
		alert(committeeLvlId);
		if(committeeLvlId == 1){
			committeeLevelIdsListArr.push(6);
			committeeLevelIdsListArr.push(8);
		}else if(committeeLvlId == 2){
			committeeLevelIdsListArr.push(5);
			committeeLevelIdsListArr.push(7);
			committeeLevelIdsListArr.push(9);
		}else if(committeeLvlId == 3){
			committeeLevelIdsListArr.push(11);
		}else if(committeeLvlId == 4){
			committeeLevelIdsListArr.push(10);
		}
		designationIdsArr.push($("#committeePostitionId").val());
		
		var locationLevelId =8;//region_Scopes
		var panchayatId=$('#panchaytList').val();
		var mandalId=$('#mandalList').val();
		var cosntiteucnyId=$('#constituencyId').val();
		var districtId=$('#districtId').val();
		var stateId=$('#statesDivId').val();
		
		var locationLevelValuesList = [];
			if(panchayatId != null && panchayatId.length>0 && panchayatId>0){
				locationLevelId =8;
				locationLevelValuesList.push(panchayatId); 
			}else if(mandalId != null && mandalId.length>0 && mandalId>0){
				locationLevelId =5;
				locationLevelValuesList.push(mandalId); 
			} else if(cosntiteucnyId != null && cosntiteucnyId.length>0 && cosntiteucnyId>0){
				locationLevelId =4;
				locationLevelValuesList.push(cosntiteucnyId); 
			}else if(districtId != null && districtId.length>0 && districtId>0){
				locationLevelId =3;
				locationLevelValuesList.push(districtId); 
			}else if(stateId != null && stateId.length>0 && stateId>0){
				locationLevelId =2;
				locationLevelValuesList.push(stateId); 
			}
			
			
		var obj = {
			enrollmentYearIdsArr: [$('#tdpCommitteeYearId').val()],
			basicCommiteeTypeId: committeeTypeId,
			committeeLevelIdsArr: [committeeLevelIdsListArr],
			roleIdsArr: [designationIdsArr],
			locationLevelId: locationLevelId,
			locationLevelValuesArr: locationLevelValuesList,
			statId: stateId
		}
		
		
	}