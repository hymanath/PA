function showHideResults(divId){
	
	var check = $("#"+divId).hasClass("opened");
	if(check){
		$("#"+divId).removeClass("opened");
		return true;
	}else{
		$("#"+divId).addClass("opened");
		return false;
	}
	
}