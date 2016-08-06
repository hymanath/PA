<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Birthdays</title>
<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/Birthdays/custom.css" rel="stylesheet" type="text/css">
<link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<link href="dist/Birthdays/Flipster/jquery.flipster.css" rel="stylesheet" type="text/css">
<link href="dist/Birthdays/Datatables/datatables.css" rel="stylesheet" type="text/css">
<link href="dist/Birthdays/Datatables/buttons.dataTables.css" rel="stylesheet" type="text/css">
<link href="dist/Plugins/Daterange/daterangepicker.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.flip-items li:first-child.flipster__item--current .flipster__button--prev
{
   pointer-events: none !important;
   cursor: default !important;
}
</style>
</head>     
<body>
<div class="container">
	<div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<h2 class="text-capitalize">Leaders Birthday</h2>
        	<div id="coverflow">
            </div>
        </div>
    </div>
    <div class="row">
    	<div class="col-md-12 col-xs-12 col-sm-12">
        	<div class="row">
			<center><img src="images/search.gif" id="birthDayDetailsImg" style="display:none;margin-top: 15px;margin-right:140px;width:30px;"/></center>
            	<div class="col-md-9 col-xs-12 col-sm-6" id="birthDivId">
                	<h4 class="text-capital text-success" id="birthDayHeadingId" ><span id="headingId"></span></h4>
                </div>
            </div>
			<div class="birthdaysBlock">
				<div id="birthdaysBlockId"></div>
			</div>
        </div>
    </div>
</div>
<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script src="dist/Birthdays/Flipster/jquery.flipster.min.js" type="text/javascript"></script>
<script src="dist/Birthdays/Datatables/datatables.js" type="text/javascript"></script>
<script src="dist/Birthdays/Datatables/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="dist/Birthdays/Datatables/jszip.min.js" type="text/javascript"></script>
<script src="dist/Birthdays/Datatables/pdfmake.min.js" type="text/javascript"></script>
<script src="dist/Birthdays/Datatables/vfs_fonts.js" type="text/javascript"></script>
<script src="dist/Birthdays/Datatables/buttons.html5.min.js " type="text/javascript"></script>
<script src="dist/Plugins/Daterange/moment.js" type="text/javascript"></script>
<script src="dist/Plugins/Daterange/daterangepicker.js" type="text/javascript"></script>
<script type="text/javascript">
//$(".flip-items li:first-child.flipster__item--current").attr("")
function disableBtns(){
	if($(".flip-items li:first-child").hasClass("flipster__item--current"))
	{
		$(".flipster__button--prev").prop('disabled', true).css('opacity', '0.4');
		$(".flipster__button--next").prop('disabled', false).css('opacity', '1');
	}else if($(".flip-items li:last-child").hasClass("flipster__item--current")){
		$(".flipster__button--next").prop('disabled', true).css('opacity', '0.4');
		$(".flipster__button--prev").prop('disabled', false).css('opacity', '1');
	}else if($(".flip-items li:nth-child(2),.flip-items li:nth-child(3),.flip-items li:nth-child(4),.flip-items li:nth-child(5),.flip-items li:nth-child(6)").hasClass("flipster__item--current"))
	{
		$(".flipster__button--prev,.flipster__button--next").prop('disabled', false).css('opacity', '1');
	}
}
$(document).on("click",".flipster__button--prev",function(){
	disableBtns();
});
$(document).on("click",".flipster__button--next",function(){
	disableBtns();
});
var memberTypeGlobal=0;
$(".dateRange").daterangepicker({
	opens:'left',
	ranges:{
          'Today': [moment(), moment()],
		  'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
		  'Last 7 Days': [moment().subtract(6, 'days'), moment()],
		  'Last 30 Days': [moment().subtract(29, 'days'), moment()],
		  'This Month': [moment().startOf('month'), moment().endOf('month')],
		  'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
	}
});

getBirthDayDetails("",memberTypeGlobal);
function getBirthDayDetails(searchType,memberTypeGlobal){
	$("#birthDayDetailsImg").show();
$("#birthDayHeadingId").html("");
		var jsObj ={
			searchType:searchType,
			occasionTypeId:1,
			memberTypeGlobal:memberTypeGlobal
		}
		$.ajax({
		type 		: 'GET',
		url 		: 'getBirthDayDetailsAction.action',
		dataType 	: 'json',
		data 		: {task:JSON.stringify(jsObj)}  
		
	}).done(function(result){
		if(searchType=="")
			buildList(result);
		
			buildAllMemberBdayDetails(result,searchType);
	});
		
}
function buildList(result)
	{

		var str='';
		var membersAvailableIndexNo = 0;
		str+='<ul class="flip-items">';
		for(var i in result){
			str+='<li id="currentItem'+i+'" data-flip-title="Red" class="allDaysCls" style="cursor:hand;" attr_name="'+result[i].name+'">';
				str+='<img src="Assets/Plugins/Flipster/img/text3.gif" class="rel">';
				str+='<div class="abs">';
					str+='<div>';
						str+='<h3>'+result[i].name+'</h3>';
						str+='<h1>'+result[i].wishCount+'/'+result[i].totalCount+'</h1>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
			if(result[i].subList != null && result[i].subList.length>0)
				membersAvailableIndexNo = i;
		}
		str+='</ul>';	
		$("#coverflow").html(str);
		 var coverflow = $("#coverflow").flipster({buttons: true,spacing: -0.7,touch:true, click: true,scrollwheel: false});
		$(".flip-items li.flipster__item--current").find("img").attr("src","Assets/Plugins/Flipster/img/text5.gif"); 
	var str1='';

	for(var j in result){

	if(parseInt(j) == parseInt(membersAvailableIndexNo)){

	if(result[j].subList != null && result[j].subList.length>0)
	{
		//debugger;
		$("#birthDayHeadingId").html(result[membersAvailableIndexNo].name+" BirthDays");
		  	str+='<ul class="list-inline">';
					str+='<li><a href="#" class="memberCls" attr_member_name="Total">ALL ('+result.totalCount+')</a></li>';
					for(var k in result.idNameVOList){
						str+=', <li><a href="#" class="memberCls" attr_member_name="'+result.idNameVOList[k].name+'">'+result.idNameVOList[k].name+' ('+result.idNameVOList[k].count+')</a></li>';
					}
					str+='</ul>';
             str1+='<div class=" table-responsive">';
            str1+='<table class="table table-bordered tableBirthday" id="birthdayTableId">';
                str1+='<thead>';
                str1+='<th>Photo</th>';
                str1+='<th>Name</th>';
                str1+='<th>Designation</th>';
                str1+='<th>Date</th>';
                str1+='<th>Mobile</th>';
                str1+='<th>Wished</th>';
                str1+=' </thead>';
             str1+='<tbody>';	
			i=0;
			for(var i in result[j].subList){ 
				str1+='<tr>';
					str1+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result[j].subList[i].imageStr+'" class="img-responsive"/></td>';
					
				 if(result[j].subList[i].name == null || result[j].subList[i].name == 0){
					 str1+='<td> - </td>';
				 }else{
					 str1+='<td>'+result[j].subList[i].name+'</td>';
				 }
					
				var str2='';var flag=false;
					if(result[j].subList[i].designation != null){
						flag=true;
						str2+=result[j].subList[i].designation;
					}
					if(result[j].subList[i].pubRepDesignation != null){
						if(flag){
							str2+=', ';
						}
						str2+=result[j].subList[i].pubRepDesignation;
					}
						str1+='<td class="text-center">'+str2+'</td>'; 
					

			    if(result[j].subList[i].bDayDate == null || result[j].subList[i].bDayDate == 0){
					 str1+='<td> - </td>';
				 }else{
					 str1+='<td>'+result[j].subList[i].bDayDate+'</td>';
				 }
				  if(result[j].subList[i].mobileNo == null || result[j].subList[i].mobileNo == 0){
					 str1+='<td> - </td>';
				 }else{
					 str1+='<td>'+result[j].subList[i].mobileNo+'</td>';
				 }
					
					str1+='<td class="text-center">';
				if(result[j].subList[i].wished == true){
					str+='<button class="btn btn-success btnWished btnClick wishedCls" attr_id="'+result[j].subList[i].occasionId+'">Wished</button>';
				}
				else {
						str+='<button class="btn btn-success btnNotWished btnClick wishedCls" attr_id="'+result[j].subList[i].occasionId+'">Not Wished</button>';
				}
					str1+='</td>';
				str1+='</tr>';
    	
		}	
		str1+='</tbody>';
		str1+='</table>';
		str1+='</div>';
		
		$("#birthdaysBlockId").html(str1);
		if(todayGlobal == " Tomorrow " || todayGlobal == 	" Next 7 Days" || todayGlobal == " Next 30 Days" ){
			$(".wishedCls").hide();
		}
		 $(".tableBirthday").dataTable({
			 
				"pagingType": "full_numbers"
		 });
	}
	else{
		str1+='<center>No Data Available</center>';
	}
  }
}
$("#birthDayDetailsImg").hide();
}
var todayGlobal = 0;
var past = null;
$(document).on("click",".allDaysCls",function(){
disableBtns();
   $("#birthdaysBlockId").html('');
   getBirthDayDetails($(this).attr("attr_name"),memberTypeGlobal);
   todayGlobal = $(this).attr("attr_name");
   if(past == null)
   {
	 $(".flip-items li.flipster__item--current").find("img").attr("src","Assets/Plugins/Flipster/img/text3.gif");  
	 $(".flipster__item--past").find("img").attr("src","Assets/Plugins/Flipster/img/text3.gif");
   }
   else{
	$("#"+past).find("img").attr("src","Assets/Plugins/Flipster/img/text3.gif");   
   } 
   $(".flip-items li.flipster__item--current").find("img").attr("src","Assets/Plugins/Flipster/img/text5.gif");
   past =  $(".flip-items li.flipster__item--current").attr("id");
});
function buildAllMemberBdayDetails(result1,searchType){
	var str = '';
	if(result1 != null && result1.length>0){
		for(var j in result1){
	if(result1[j].subList != null && result1[j].subList.length>0){
		if(result1[j].name == searchType){
			var result = result1[j];
			if(result.subList != null && result.subList.length >0){
				$("#birthDayHeadingId").html(todayGlobal+" BirthDays");
				str+='<ul class="list-inline">';
					str+='<li><a href="#" class="memberCls" attr_member_name="Total">ALL ('+result.totalCount+')</a></li>';
					for(var i in result.idNameVOList){
						str+=', <li><a href="#" class="memberCls" attr_member_name="'+result.idNameVOList[i].name+'">'+result.idNameVOList[i].name+' ('+result.idNameVOList[i].count+')</a></li>';
					}
					str+='</ul>';	
			str+='<div class=" table-responsive">';
            str+='<table class="table table-bordered tableBirthday">';
                str+='<thead>';
                str+='<th>Photo</th>';
                str+='<th>Name</th>';
                str+='<th style="width:104px;">Designation</th>';
                str+='<th>Date</th>';
                str+='<th>Mobile</th>';
                str+='<th>Wished</th>';
                str+=' </thead>';
             str+='<tbody>';
			 
		for(var i in result.subList){	
					str+='<tr>';
					str+='<td><img src="http://www.mytdp.com/images/cadre_images/'+result.subList[i].imageStr+'" class="img-responsive"/></td>';
					if(result.subList[i].name == null || result.subList[i].name == 0){
					 str+='<td> - </td>';
					 }else{
						 str+='<td><a href="cadreDetailsAction.action?cadreId='+result.subList[i].id+'" style="cursor:pointer;" title="" data-placement="top" data-toggle="tooltip" target="_blank" data-original-title="Cadre Details">'+result.subList[i].name+' - '+result.subList[i].addressVO.constituencyName+' Constituency</a></td>';
					 }
					
					 var str1='';var flag=false;
					if(result.subList[i].designation != null){
						flag=true;
						str1+=result.subList[i].designation;
					}
					if(result.subList[i].pubRepDesignation != null){
						if(flag){
							str1+=', ';
						}
						str1+=result.subList[i].pubRepDesignation;
					}
						str+='<td class="text-center">'+str1+'</td>'; 
					if(result.subList[i].bDayDate == null || result.subList[i].bDayDate == 0){
						 str+='<td> - </td>';
					 }else{
						 str+='<td>'+result.subList[i].bDayDate+'</td>';
					 }
					  if(result.subList[i].mobileNo == null || result.subList[i].mobileNo == 0){
						 str+='<td> - </td>';
					 }else{
						 str+='<td>'+result.subList[i].mobileNo+'</td>';
					 }
					str+='<td class="text-center">';
					if(result.subList[i].wished == true){
						str+='<button class="btn btn-success btnWished btnClick wishedCls" attr_id="'+result.subList[i].occasionId+'">Wished</button>';
					}
					else {
						str+='<button class="btn btn-success btnNotWished btnClick wishedCls" attr_id="'+result.subList[i].occasionId+'">Not Wished</button>';
					}
					str+='</td>';
				str+='</tr>';
    	
		}	
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		
		$("#birthdaysBlockId").html(str);
		if(todayGlobal == " Tomorrow " || todayGlobal == 	" Next 7 Days" || todayGlobal == " Next 30 Days" ){
			$(".wishedCls").hide();
		}
		 $(".tableBirthday").dataTable({
			
				"pagingType": "full_numbers"
		 });
	}else{
		str+='<center>No Data Available</center>';
		}
	  }
	}
  }
}	
$("#birthDayDetailsImg").hide();
}
$(document).on("click",".btnClick",function(){
	$(this).toggleClass("btnNotWished").toggleClass("btnWished")
	var btnText = $(this).html();
	if(btnText == 'Not Wished')
	{
		$(this).html("Wished")
	}else{
		$(this).html("Not Wished")
	}
	var searchId = $(this).attr("attr_id"); 
	var jsObj={
			 searchId:searchId
		}
		$.ajax({
			type:"POST",
			url :"getWishingDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			if(result=="success"){
				str+='<center>Wished successfully....</center>';
			}
			else{
				str+='<center>Error Occured Try Again....</center>';
			}
   });	
});
$(document).on("click",".memberCls",function(){
	memberTypeGlobal = $(this).attr("attr_member_name");
	getBirthDayDetails(todayGlobal,memberTypeGlobal);
});

$(document).on("click",".cadreDetailsCls",function(){
		var cadreId=$(this).attr("attr_cadre_id");
		var redirectWindow=window.open('cadreDetailsAction.action?cadreId='+cadreId+'','_blank');
	});
</script>
</body>
</html>
