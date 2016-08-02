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
            	<div class="col-md-9 col-xs-12 col-sm-6">
                	<h2 class="text-capitalize"><span id="headingId"></span> Birthdays</h2>
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

getBirthDayDetails("");
function getBirthDayDetails(searchType){
	alert(32);
		var jsObj ={
			searchType:searchType,
			occasionTypeId:1
		}
		$.ajax({
		type 		: 'GET',
		url 		: 'getBirthDayDetailsAction.action',
		dataType 	: 'json',
		data 		: {task:JSON.stringify(jsObj)}  
		
	}).done(function(result){
		if(searchType=="")
			buildList(result);
		else
			buildAllMemberBdayDetails(result);
	});
		
}
	function buildList(result)
	{
		var str='';
		str+='<ul class="flip-items">';
		var result1 = result.idNameVOList;
		
		for(var i in result1){
			str+='<li data-flip-title="Red" id="daysId" class="allDaysCls" attr_name="'+result1[i].name+'">';
				str+='<img src="Assets/Plugins/Flipster/img/text3.gif" class="rel">';
				str+='<div class="abs">';
					str+='<div>';
						str+='<h3>'+result1[i].name+'</h3>';
						str+='<h1>'+result1[i].count+'/'+result1[i].actualCount+'</h1>';
					str+='</div>';
				str+='</div>';
			str+='</li>';
		}
		str+='</ul>';	
		$("#coverflow").html(str);
		
		 var coverflow = $("#coverflow").flipster({buttons: true,spacing: -0.6});
		 
		 var str1='';
		 if(result.tdpCadreVOList != null && result.tdpCadreVOList.length >0){
		    str1+='<ul class="list-inline">';
                str1+='<li><a href="#">Total (20)</a></li>';
                str1+='<li><a href="#">MLA (10)</a></li>';
                str1+='<li><a href="#">MP</a></li>';
            str1+='</ul>';
             str1+='<div class=" table-responsive">';
            str1+='<table class="table table-bordered tableBirthday">';
                str1+='<thead>';
                str1+='<th>Photo</th>';
                str1+='<th>Name</th>';
                str1+='<th>Designation</th>';
                str1+='<th>Birthday Date</th>';
                str1+='<th>Phone Number</th>';
                str1+='<th>Wished</th>';
                str1+=' </thead>';
             str1+='<tbody>';
		for(var i in result.tdpCadreVOList){		
				str1+='<tr>';
					str1+='<td><img src="images/cadre_images/'+result.tdpCadreVOList[i].imageURL+'" class="img-responsive"/></td>';
					
				 if(result.tdpCadreVOList[i].name == null || result.tdpCadreVOList[i].name == 0){
					 str1+='<td> - </td>';
				 }else{
					 str1+='<td>'+result.tdpCadreVOList[i].name+'</td>';
				 }
				str1+='<td>Designation</td>';
				 if(result.tdpCadreVOList[i].dateOfBirth == null || result.tdpCadreVOList[i].dateOfBirth == 0){
					 str1+='<td> - </td>';
				 }else{
					 str1+='<td>'+result.tdpCadreVOList[i].dateOfBirth+'</td>';
				 }
				  if(result.tdpCadreVOList[i].mobileNo == null || result.tdpCadreVOList[i].mobileNo == 0){
					 str1+='<td> - </td>';
				 }else{
					 str1+='<td>'+result.tdpCadreVOList[i].mobileNo+'</td>';
				 }
					
					str1+='<td>';
					str1+='	<button class="btn btn-success btnNotWished btnClick" attr_id="'+result.tdpCadreVOList[i].id+'">Not Wished</button>';
					str1+='</td>';
				str1+='</tr>';
    	
		}	
		str1+='</tbody>';
		str1+='</table>';
		str1+='</div>';
		
		$("#birthdaysBlockId").html(str1);
		 $(".tableBirthday").dataTable({
			 initComplete: function () {
				this.api().columns().every( function () {
					var column = this;
					var select = $('<select><option value=""></option></select>')
						.appendTo( $(column.footer()).empty() )
						.on( 'change', function () {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);
	 
							column
								.search( val ? '^'+val+'$' : '', true, false )
								.draw();
						} );
	 
					column.data().unique().sort().each( function ( d, j ) {
						select.append( '<option value="'+d+'">'+d+'</option>' )
					} );
				} );
			},
			 "dom": 'Bfrtip',
			 "buttons": [
					{
						extend:    'excelHtml5',
						text:      '<i class="fa fa-file-excel-o"></i>',
						titleAttr: 'Excel'
					},
					{
						extend:    'pdfHtml5',
						text:      '<i class="fa fa-file-pdf-o"></i>',
						titleAttr: 'PDF'
					}
				],
				"pagingType": "full_numbers"
		 });
	}
	}
$(document).on("click",".allDaysCls",function(){
	//$(".allDaysCls").find("img").attr("src","dist/Birthdays/Flipster/img/text3.gif")
	//$(this).find("img").attr("src","dist/Birthdays/Flipster/img/text5.gif")
	/* $("#birthdaysBlockId").html('');
	var searchType = $(this).attr("attr_name"); 
	$("#headingId").html(searchType)
	var jsObj={
			 searchType:searchType,
			 occastionTypeId:1
		}
		$.ajax({
			type:"POST",
			url :"getAllBirthdayDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			buildAllMemberBdayDetails(result);
   });	 */
   $("#birthdaysBlockId").html('');
   getBirthDayDetails($(this).attr("attr_name"));
});
function buildAllMemberBdayDetails(result){
	var str = '';
	
	if(result.tdpCadreVOList != null && result.tdpCadreVOList.length >0){
		    str+='<ul class="list-inline">';
                str+='<li><a href="#">Total (20)</a></li>';
                str+='<li><a href="#">MLA (10)</a></li>';
                str+='<li><a href="#">MP</a></li>';
            str+='</ul>';
             str+='<div class=" table-responsive">';
            str+='<table class="table table-bordered tableBirthday">';
                str+='<thead>';
                str+='<th>Photo</th>';
                str+='<th>Name</th>';
                str+='<th>Designation</th>';
                str+='<th>Birthday Date</th>';
                str+='<th>Phone Number</th>';
                str+='<th>Wished</th>';
                str+=' </thead>';
             str+='<tbody>';
		for(var i in result.tdpCadreVOList){		
				str+='<tr>';
					str+='<td><img src="images/cadre_images/'+result.tdpCadreVOList[i].imageURL+'" class="img-responsive"/></td>';
					if(result.tdpCadreVOList[i].name == null || result.tdpCadreVOList[i].name == 0){
					 str+='<td> - </td>';
					 }else{
						 str+='<td>'+result.tdpCadreVOList[i].name+'</td>';
					 }
					str+='<td>Designation</td>';
					 if(result.tdpCadreVOList[i].dateOfBirth == null || result.tdpCadreVOList[i].dateOfBirth == 0){
						 str+='<td> - </td>';
					 }else{
						 str+='<td>'+result.tdpCadreVOList[i].dateOfBirth+'</td>';
					 }
					  if(result.tdpCadreVOList[i].mobileNo == null || result.tdpCadreVOList[i].mobileNo == 0){
						 str+='<td> - </td>';
					 }else{
						 str+='<td>'+result.tdpCadreVOList[i].mobileNo+'</td>';
					 }
					str+='<td>';
					str+='	<button class="btn btn-success btnNotWished btnClick" attr_id="'+result.tdpCadreVOList[i].id+'">Not Wished</button>';
					str+='</td>';
				str+='</tr>';
    	
		}	
		str+='</tbody>';
		str+='</table>';
		str+='</div>';
		
		$("#birthdaysBlockId").html(str);
		 $(".tableBirthday").dataTable({
			 initComplete: function () {
				this.api().columns().every( function () {
					var column = this;
					var select = $('<select><option value=""></option></select>')
						.appendTo( $(column.footer()).empty() )
						.on( 'change', function () {
							var val = $.fn.dataTable.util.escapeRegex(
								$(this).val()
							);
	 
							column
								.search( val ? '^'+val+'$' : '', true, false )
								.draw();
						} );
	 
					column.data().unique().sort().each( function ( d, j ) {
						select.append( '<option value="'+d+'">'+d+'</option>' )
					} );
				} );
			},
			 "dom": 'Bfrtip',
			 "buttons": [
					{
						extend:    'excelHtml5',
						text:      '<i class="fa fa-file-excel-o"></i>',
						titleAttr: 'Excel'
					},
					{
						extend:    'pdfHtml5',
						text:      '<i class="fa fa-file-pdf-o"></i>',
						titleAttr: 'PDF'
					}
				],
				"pagingType": "full_numbers"
		 });
	}else{
		
		$("#birthdaysBlockId").html("<center>No Data Available</center>");
	}
	
	
}
$(document).on("click",".btnClick",function(){
	//$("#birthdaysBlockId").html('');
	$(this).toggleClass("btnNotWished").toggleClass("btnWished")
	var btnText = $(this).html();
	if(btnText == 'Not Wished')
	{
		$(this).html("Wished")
	}else{
		$(this).html("Not Wished")
	}
	var searchId = $(this).attr("attr_id"); 
	//$("#headingId").html(searchType)
	var jsObj={
			 searchId:searchId
		}
		$.ajax({
			type:"POST",
			url :"getWishingDetailsAction.action",
			dataType: 'json',
			data: {task:JSON.stringify(jsObj)}
		}).done(function(result){
			//buildAllMemberBdayDetails(result);
   });	
});

</script>
</body>
</html>
