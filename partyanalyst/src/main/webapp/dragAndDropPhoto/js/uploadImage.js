function getActivityImages(startIndex)
{
	$("#imagesDiv").html("");
	$(".imgTitleCls").html("");
	$(".jFiler-items-list li").remove();
	var conductDate = $("#conductedDate").val();
	$(".deleteBtnCls").hide();
	
	
	var flag = validateFields();
	
	if(!flag){
		
	 return;

	}else{
		$("#myModal").modal("show")
	}
	
	
	if(gobalTempVar == "dayCalCulationReq"){
       setGobalValues();
	   var d = conductDate.split("/");//dd/mm/yyyy
	   gobalActivityDate = d[1] +'/'+ d[0] +'/'+ d[2];//mm/dd/yyyy
	}
	
	$(".imgTitleCls").html(""+gobalLocName+"");
	
   var jsObj=
   {				
	  levelId         : gobalLevelId,
	  levelValue      : gobalLevelValue,
	  day             : gobalDay,
	  activityScopeId : gobalActivityScopeId,
	  activityDate    : gobalActivityDate,
	  startIndex      : startIndex,
	  maxIndex        : 12,
	  tempVar         : gobalTempVar,
	  task            : ""				
	}
	
	$.ajax({
			  type:'GET',
			  url: 'getActivityDocumentsImagesAction.action',
			  dataType: 'json',
			  data: {task:JSON.stringify(jsObj)}
	   }).done(function(result){
		  buildActivityImages(result,startIndex);
	   });	
}


function buildActivityImages(result,startIndex)
{
	if(result == null || result.length == 0){
		$("#paginationDiv").html("");
		$("#imagesDiv").html("<p class='text-center'>No Data Avaliable.</p>");
		return;
	}
	var str = '';
	str +='<div class="col-md-12"><p><input type="checkbox" class="deleteAllCheckCls" style="cursor:pointer;"/>&nbsp;&nbsp;Select All</p></div>';
	for(var i in result)
	{
		
		str +='<div class="col-md-3">';
		str +='<div class="panel panel-default" style="margin-bottom:10px;">';
			str +='<div class="panel-heading" style="padding:2px">';
				str +='<h4 class="panel-title"><input type="checkbox" style="cursor:pointer;" value="'+result[i].id+'" class="deleteLocationImgCheck"/></h4>';
			str +='</div>';
			str +='<div class="panel-body" style="padding:0px;">';
				str +='<img src="activity_documents/'+result[i].name+'" class="img-responsive" style="height:120px;width:100%" />';
			str +='</div>';
		str +='</div>';
		str +='</div>';
	}
	
	 var itemsCount=result[0].count;
	   if(startIndex==0){
		   $("#paginationDiv").html('');
		   $("#paginationDiv").pagination({
			items: itemsCount,
			itemsOnPage: 12,
			cssStyle: 'light-theme',
			
			onPageClick: function(pageNumber, event) {
				var num=(pageNumber-1)*12;
				getActivityImages(num);
			}
		});

		}
	
	
	$("#imagesDiv").html(str);
}



$(document).on("click",".deleteAllCheckCls",function() {
	if($(this).is(":checked") == true){
		$(".deleteLocationImgCheck" ).prop( "checked", true );
		$(".deleteBtnCls").show();
	}
	else{
	 $(".deleteLocationImgCheck" ).prop( "checked", false );	
	 $(".deleteBtnCls").hide();
	}
});


$(document).on("click",".deleteBtnCls",function() {
 
 var acitivityInfoDocId = "";
 $(".deleteLocationImgCheck").each(function(){
	 if($(this).is(":checked") == true){
	  acitivityInfoDocId += $(this).val()+",";
	 }
 });
 
 acitivityInfoDocId = acitivityInfoDocId.substr(0,acitivityInfoDocId.length-1);
 if(confirm("Are you sure you want to remove this file?")){
	    var jsObj=
	    {				
		  acitivityInfoDocId:acitivityInfoDocId,
		  task:"deleteFile"				
		}
		$.ajax({
				  type:'GET',
				  url: 'deleteUploadedFileAction.action',
				  dataType: 'json',
				  data: {task:JSON.stringify(jsObj)}
		   }).done(function(result){
			   if(result.resultCode == 0){
				 getActivityImages(0);
			   }
		   });
   }

});



$(document).on("click",".deleteLocationImgCheck",function() {
	var imageCheck = false;
	$(".deleteLocationImgCheck").each(function(){
		if($(this).is(":checked") == true){imageCheck = true;}
		});
	if(!imageCheck){
		$(".deleteBtnCls").hide();
	}
    else{
	   $(".deleteBtnCls").show();
    }	
});