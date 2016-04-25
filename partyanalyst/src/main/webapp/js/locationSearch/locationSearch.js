//getConstituenciesForState(1)
function getConstituenciesForState(stateId){
   var jsObj=
	{				
		stateId:stateId,
		elmtId:"stateList",
		type:"default",
		task:"getConstituenciesForState"	     			
	}
    $.ajax({
			type:'GET',
			url: 'getConstituenciesForStateAjaxAction.action',
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
	}).done(function(result){
		$("#constituencyId  option").remove();
		for(var i in result){
			if(result[i].id == 0){
				$("#constituencyId").append('<option value='+result[i].id+'>ALL</option>');
			}else{
				$("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			}
		}
		$("#constituencyId").dropkick();
		var select = new Dropkick("#constituencyId");
		select.refresh();
	});
}