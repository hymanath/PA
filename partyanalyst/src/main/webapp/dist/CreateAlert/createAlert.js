
function disableByLevel()
  {
	  setDefault();	
	  var levelId = $("#levelId").val();
	  var districtId = $("#referdistrictId").val();
	  var constituencyId = $("#referconstituencyId").val();
	  var panchayatId = $("#referpanchayatId").val();
	  var mandalId = $("#refermandalNameId").val();
	  var select = new Dropkick("#referdistrictId");
		select.refresh();
		
		if(levelId != 2 && levelId != 0)
		{
			getDistrictsForReferPopup();
		}
		
		if(levelId == 2 || levelId == 0)
		{
			  $("#referdistrictId").find('option').not(':first').remove();
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referdistrictId");
				select.refresh();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").hide();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
				
		}
		else if(levelId == 3)
		{
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").hide();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
		}
		else if(levelId == 4)
		{
			  $("#referconstituencyId").find('option').not(':first').remove();
			  $("#refermandalNameId").find('option').not(':first').remove();
			  $("#referpanchayatId").find('option').not(':first').remove();
			   var select = new Dropkick("#referconstituencyId");
				select.refresh();
				var select = new Dropkick("#refermandalNameId");
				select.refresh();
				var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").hide();
				$(".panchayatCls").hide();
		}
		else if(levelId == 5)
		{
			$("#referpanchayatId").find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").show();
				$(".panchayatCls").hide();
		}
		else if(levelId == 6)
		{
			$("#referpanchayatId").find('option').not(':first').remove();
			var select = new Dropkick("#referpanchayatId");
				select.refresh();
				$(".stateCls").show();
				$(".distCls").show();
				$(".constiCls").show();
				$(".mandalCls").show();
				$(".panchayatCls").show();
		}
		
  }
 function setDefault()
  {
	  $("#referconstituencyId").find('option').not(':first').remove();
	  $("#refermandalNameId").find('option').not(':first').remove();
	  $("#referpanchayatId").find('option').not(':first').remove();
	    var select = new Dropkick("#referconstituencyId");
		select.refresh();
		var select = new Dropkick("#refermandalNameId");
		select.refresh();
		var select = new Dropkick("#referpanchayatId");
		select.refresh();
  } 
  
  
  
  function getDistrictsForReferPopup() {
{
	var stateId = $("#stateId").val();
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
		str+='<option value="0">Select District</option>';
		if(result != null && result.length > 0){
			for(var i in result){
				str+='<option value="'+result[i].id+'">'+result[i].name+'</option>';
			}
		}
		$("#referdistrictId").html(str);
		$("#referdistrictId").dropkick();
		var select1 = new Dropkick("#referdistrictId");
		select1.refresh();
	});
 }
  }
 function getConstituenciesBydistrictForReferPopup(){
	 var districtId = $("#referdistrictId").val();
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
			    constiStr +='<option value="0">Select Assembly</option>';
				for(var i in result){
					constiStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#referconstituencyId").html(constiStr);
			 $("#referconstituencyId").dropkick();
			var select = new Dropkick("#referconstituencyId");
			 select.refresh();
			}
		});
 }

 function getMandalsByConstituencyForReferPopup(){
	 var constituencyId = $('#referconstituencyId').val();
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
			    mandalStr +='<option value="0">Select Mandal/ Municipality</option>';
				for(var i in result){
					mandalStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
					}
			 $("#refermandalNameId").html(mandalStr);
			 $("#refermandalNameId").dropkick();
			 var select = new Dropkick("#refermandalNameId");
			 select.refresh();
			}
		});
 }
 
 function getPanchayatsForReferPopup(){
	 $("#referpanchayatId").find('option').not(':first').remove();
	 var mandalId = $('#refermandalNameId').val();
	 var  type = $("#refermandalNameId option:selected").text();
			   
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
							panchyatStr +='<option value="0">Select Panchayat</option>';
			            for(var i in result){
				 panchyatStr +='<option value='+result[i].id+'>'+result[i].name+'</option>';
			 }
			    $("#referpanchayatId").html(panchyatStr);
			    $("#referpanchayatId").dropkick();
			 var select = new Dropkick("#referpanchayatId");
			 select.refresh();
			}
		   });
		}