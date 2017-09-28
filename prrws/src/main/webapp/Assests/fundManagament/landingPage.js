var spinner = '<div class="row"><div class="col-md-12 col-xs-12 col-sm-12"><div class="spinner"><div class="dot1"></div><div class="dot2"></div></div></div></div>';

var imagesObj = {
"PRIS":"Group 2344.png","DRAINS":"Group 2345.png","LED MONITORING":"Group 2348.png","UGD":"Group 2359.png","RDP":"Group 2343.png","FUND MANAGMENT SYSTEM":"Group 2352.png","ENGINEERING DEPARTMENT":"Group 2346.png","PANACHAYATI RAJ EXPENDITURE":"Group 2343.png","SPIKE ANALYSIS":"Group 2347.png","MGNREGS":"Group 2357.png","RURAL DEVELOPMENT":"Group 2349.png","RURAL WATER SUPPLY":"Group 2350.png","ITEC":"Group 2351.png"
}
var windowWidth =$(window).width();
if(windowWidth <500){
	$(".landing-menu").css("display","");
	$(".landing-menu li").css("width","100%");
}else{
	$(".landing-menu").css("display","flex");
	$(".landing-menu li").css("width","16%");
}
getFavouriteComponents();
function getFavouriteComponents(){
	$("#favouriteComponentDivId").html(spinner);
	var json = {}
	$.ajax({                
		type:'GET',    
		url: 'getFavouriteComponents',
		dataType: 'json',
		data : JSON.stringify(),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		 $("#favouriteComponentDivId").html('');
		 if (result != null && result.length > 0) {
			 buildFavouriteComponentsResult(result);
		 } else {
			 $("#favouriteComponentDivId").html("NO Favourite Component.");
		 }
	});		
}
function buildFavouriteComponentsResult(result) {
	   var str = '';
	   for (var i in result) {
		var compnentName = result[i].name.trim();
		if (result[i].name == "IT E & C") {
			compnentName = "ITEC";
		}
		var componentNameWithoutSpace = compnentName.replace(/\s+/g, '');
		str +='<div class="col-sm-4">'
				str +='<div class="whiteBlock">';
					str +='<img src="Assests/img/'+imagesObj[compnentName]+'" >';
					str +='<h5 style="display: inline-block;">'+result[i].name+'</h5>';
					/*str +='<div class=" " style="text-align: right">';
						str +='<h2 class="" style="margin-top: 0px">0</h2>';
						str +='<p class="">ACHIEVEMENT</p>';
					str +='</div>';*/
					str +='<div class="block-footer" style="border-top: 1px solid lightgrey;padding-top: 5px">';
						str +='<i class="fa fa-star starcolorChange '+componentNameWithoutSpace+'Color" attr_url="'+result[i].url
						+'" attr_full_block_name="'+compnentName+'" attr_color_name="red" attr_block_name="'+componentNameWithoutSpace+'" aria-hidden="true"></i>';
						str +='<a class="pull-right" href="'+result[i].url+'" target="_blank" style="font-size: 12px;"><i class="fa fa-external-link-square" aria-hidden="true"></i>Get Details</a>';
					str +='</div>';
				str +='</div>';
			str +='</div>';
	}
	$("#favouriteComponentDivId").html(str);
	/*adding required filed dynamically*/
	for (var i in result) { 
		var compnentName = result[i].name.trim();;
		if (result[i].name == "IT E & C") {
			compnentName = "ITEC";
		}
		var componentNameWithoutSpace = compnentName.replace(/\s+/g, '');
		$("."+componentNameWithoutSpace+"Color").css("color","red");
		$("."+componentNameWithoutSpace+"Color").attr("attr_block_id",result[i].id);
		$("."+componentNameWithoutSpace+"Color").attr("attr_color_name","red");
		$("."+componentNameWithoutSpace+"Color").attr("title","click to remove from favourite list.");
	}
	
}

$(".showhideCls").css("display","none");
$(document).on('click','.landingmenu-block ul li',function(){
		$(this).closest("ul").find("li").removeClass("active");
		$(this).addClass("active");
		$("#showMainBlock").css("display","none")
		var listId = $(this).attr("id");
		var eq = $(this).index();
		$(".showhideCls").removeClass("showBlock");
		$(".showhideCls").eq(eq).addClass("showBlock");
		$(".showhideCls").css("display","none");
		$(".showBlock").css("display","block");
		if (listId == "favourite") {
			getFavouriteComponents();
		}
	})
	
	
	
$(document).on('click','.starcolorChange',function(){
   var blockName = $(this).attr("attr_block_name");
   var colorName = $(this).attr("attr_color_name");
   var fullBlockName = $(this).attr("attr_full_block_name");
   var blockId = $(this).attr("attr_block_id");
   var url = $(this).attr("attr_url");
	if(colorName == "gray"){
		$("."+blockName+"Color").removeClass("removeFav");
		addRemoveComponentToFavourite('Add',blockName,fullBlockName,url,blockId);
	}else{
		$("."+blockName+"Color").addClass("removeFav");
		addRemoveComponentToFavourite('Remove',blockName,fullBlockName,url,blockId);
	}
})

var saveFlag = true;
var deleteFlag = true;
function addRemoveComponentToFavourite(actionType,blockName,fullBlockName,url,blockId){
	if (actionType == "Add") {
		if (saveFlag == true) {
			saveFlag = false;
		    saveFavouriteComponentDtls(url,blockName,fullBlockName);	
		}
		
	} else if (actionType == "Remove") {
		if (deleteFlag == true) {
			deleteFlag = false;
		  deleteFavouriteComponent(blockName,blockId)	
		}
		
	}
}

function saveFavouriteComponentDtls(url,blockName,fullBlockName){
	  $("#blockOperationStatusHeadingId").html(spinner);
	  $("#blockModalMessageDivId").modal("show");
	var json = {
		  url:url, 
		  name:fullBlockName
		}
	$.ajax({                
		type:'POST',    
		url: 'saveFavouriteComponentDtls',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		  saveFlag = true;
		if (result.statusCode==0 && result.message=="success"){
		     $("."+blockName+"Color").css("color","red");
			  $("."+blockName+"Color").attr("attr_color_name","red");
			  $("#blockOperationStatusHeadingId").html("Block Successfully added as favourite.");
			  $("."+blockName+"Color").attr("title","click to remove from favourite list.");
			  $("#blockModalMessageDivId").modal("show");
			  setTimeout(function(){ $("#blockModalMessageDivId").modal("hide"); }, 2000);
			  getFavouriteComponents();
		 } else {
			 alert("Something went wrong,Please try again.");
		 }
	});		
}
function deleteFavouriteComponent(blockName,blockId){
	 $("#blockOperationStatusHeadingId").html(spinner);
	 $("#blockModalMessageDivId").modal("show");
	if (blockId == 0 || blockId == undefined){
		return ;
	}
	var json = {
		id:blockId
	}
	$.ajax({                
		type:'POST',    
		url: 'deleteFavouriteComponent',
		dataType: 'json',
		data : JSON.stringify(json),
		beforeSend :   function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		}
	}).done(function(result){
		  deleteFlag = true;
		if (result.statusCode==0 && result.message=="success"){
			   $("."+blockName+"Color").css("color","gray");
			   $("."+blockName+"Color").attr("attr_color_name","gray");
			   $("#blockOperationStatusHeadingId").html("Block removed from favourite list.");
			   $("#blockModalMessageDivId").modal("show");
			   setTimeout(function(){ $("#blockModalMessageDivId").modal("hide"); }, 2000);
			   $("."+blockName+"Color").attr("title","click to add as favourite component");
			 	 getFavouriteComponents();
		 } else {
			    alert("Something went wrong,Please try again.");
		 }
	});		
}
