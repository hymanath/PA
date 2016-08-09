function getDistrictsForReferPopup(index) {
{
	var stateId = $("#stateId"+index).val();
	var jobj = {
		stateId : stateId
	}
	$.ajax({
		type : 'GET',
		url : 'getDistrictsForStateAction.action',
		dataType : 'json',
		data : {task:JSON.stringify(jobj)} 
	}).done(function(result){
		var str='';
		if(index == "")
		{
			    str +='<option value="0">ALL</option>';	
		}
		else
		str+='<option value="0">Select District</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				if(result[i].id > 0)
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#referdistrictId"+index).html(str);
		$("#referdistrictId"+index).dropkick();
		var select1 = new Dropkick("#referdistrictId"+index);
		select1.refresh();
	});
 }
  }
 function getConstituenciesBydistrictForReferPopup(index){
	 var districtId = $("#referdistrictId"+index).val();
	var jobj = {
		districtId : districtId
	}
		$.ajax({
			type : "POST",
			url  : "getConstituenciesByDistrictAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var constiStr='';
			if(result != null && result.length > 0){
				if(index == "")
			    constiStr +='<option value="0">ALL</option>';
			else
				 constiStr +='<option value="0">Select Assembly</option>';
				for(var i in result){
					if(result[i].id > 0)
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#referconstituencyId"+index).html(constiStr);
			 $("#referconstituencyId"+index).dropkick();
			var select = new Dropkick("#referconstituencyId"+index);
			 select.refresh();
			}
		});
 }

 function getMandalsByConstituencyForReferPopup(index){
	 var constituencyId = $('#referconstituencyId'+index).val();
	var jobj = {
		constituencyId : constituencyId
	}
		$.ajax({
			type : "POST",
			url  : "getMandalsByConstituencyAction.action",
			data : {task:JSON.stringify(jobj)}
		}).done(function(result){
			var mandalStr='';
			if(result != null && result.length > 0){
				if(index == "")
			    mandalStr +='<option value="0">ALL</option>';
					else
			    mandalStr +='<option value="0">Select Mandal/ Municipality</option>';
				for(var i in result){
					if(result[i].id > 0)
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#refermandalNameId"+index).html(mandalStr);
			 $("#refermandalNameId"+index).dropkick();
			 var select = new Dropkick("#refermandalNameId"+index);
			 select.refresh();
			}
		});
 }
 
 function getPanchayatsForReferPopup(index){
	
	 $("#referpanchayatId"+index).find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId'+index).val();
	 var  type = $("#refermandalNameId"+index+" option:selected").text();
			   
			 if(type.indexOf("Mandal") == -1) 
				type = "muncipality" ;
			else
				type = "mandal" ; 
			  var jsObj={
						mandalId :mandalId,
						type:type,
						task:""
						
					}
			 $.ajax({
						type:"POST",
						url :"getPanchayatDetailsAction.action",
						 dataType: 'json',
						data: {task:JSON.stringify(jsObj)}
					}).done(function(result){
						var panchyatStr='';
						if(result!=null && result.length>0){
							if(index == "")
			    panchyatStr +='<option value="0">ALL</option>';
					else
				panchyatStr +='<option value="0">Select Panchayat</option>';
			            for(var i in result){
							if(result[i].id > 0)
				 panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			 }
			    $("#referpanchayatId"+index).html(panchyatStr);
			    $("#referpanchayatId"+index).dropkick();
			 var select = new Dropkick("#referpanchayatId"+index);
			 select.refresh();
			}
		   });
		}
		
		