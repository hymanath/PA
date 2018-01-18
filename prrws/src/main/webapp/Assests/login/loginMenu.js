$("header").on("click",".menu-cls",function(e){
	e.stopPropagation();
	$(".menuCls").toggle();
});
 
$(document).on("click",function(e){

    var searchContainerCls = $(".menuCls");

    if (!searchContainerCls.is(e.target) 
        && searchContainerCls.has(e.target).length === 0) 
    {
        searchContainerCls.hide();
    }
});


userIdsByEntitlementsLogin();
function userIdsByEntitlementsLogin(){
 var json = {
	  username:"minister_test_user",
	  passwordHashText:123456
} 
$.ajax({ 
 type:'POST', 
 url: 'userIdsByEntitlementsLogin',
 dataType: 'json',
 data : JSON.stringify(json),
 beforeSend : function(xhr){
 xhr.setRequestHeader("Accept", "application/json");
 xhr.setRequestHeader("Content-Type", "application/json");
 }
}).done(function(result){
 
 if(result != null && result.length >0){
	 buildMenu(result);
	 
 }else{
	$("#menuId").append('<li>No Data Available</li>');
 }
}); 
}


 function buildMenu(result){
	var iconsArr=["fa-pencil-square-o","fa-eye","fa-qrcode"];
	var str='';	
	for(var i in result){
		str+='<div class="panel-group" style="padding:5px;">';
			str+='<div class="panel panel-default">';
				str+='<div class="panel-heading">';
					str+='<h4 class="panel-title">';
						str+='<a data-toggle="collapse" href="#collapse'+i+'">'+result[i].name+'&nbsp;&nbsp;<i class="fa fa-bars fa-lg" aria-hidden="true"></i></a>';
					str+='</h4>';
				str+='</div>';
				str+='<div id="collapse'+i+'" class="panel-collapse collapse">';
					str+='<ul class="list-group">';
				
						for(var j in result[i].subList){
							
						str+='<li class="list-group-item"><a href="'+result[i].subList[j].url+'">'+result[0].subList[j].name+'</a><span class="fa fa-lg '+iconsArr[j]+' pull-right"></span></li>';
						}
					
					str+='</ul>	';
				str+='</div>';
			str+='</div>';
		str+='</div>';		
	
	}
	
$("#menuId").html(str);


 } 