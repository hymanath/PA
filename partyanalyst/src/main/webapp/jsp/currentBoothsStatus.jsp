<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Current Booths Status</title>
<style>
	.show-grid:hover .block-hover-addBtn{display:table-cell; margin-right:-22px; top:-10px;}/*visibility: visible;*/
	.block-hover-addBtn{display:none; position: relative;}/*visibility: hidden;*/
	.border-none{border:none;}
	.text-lowercase{text-transform:lowercase;}
	.text-uppercase{text-transform:uppercase;}
	.text-capitalize{text-transform:capitalize;}
	.text-center{text-align: center;}
	.text-red{color:#dc504a;}
	.text-green{color:#4dbd74;}
	.text-orange{color:#f9a834;}
	.text-skyblue{color:#46acca;}
	.mb-0{margin-bottom:0px}
	.mb-10{margin-bottom:10px}
	.Previousmembercount td{width:20%;}
	.membercount td{width:25%;}
	.membercount td h2, .Previousmembercount td h2{margin:0px;}
	.progress{height:10px;}
	.height-300{height: 300px; overflow: auto;}
	.height-500{height: 500px; overflow: auto;}
	.height-320{height: 300px; overflow: auto;width: 440px;}
	.f-16{font-size: 16px;}
	body {
    color: #333333;
    font-size: 14px;
    line-height: 20px;
    margin: 0;
    }
	p {
    color: #333;
    font-size: 14px;
   }
   .background {
    background: none repeat scroll 0 0 #e5e5e5;
   }
   .text-right {
    text-align: right;
   }
   
  .dataTables_length, .dataTables_filter , .dataTables_info {
		color : #666666 !important;
	}
	
table.dataTable tr.odd td.sorting_1 {
    background-color: #d3d3d3;
}
table.dataTable tr.even td.sorting_1 {
    background-color: #fafafa;
}
table.dataTable tr.odd {
    background-color: #f3f3f3;
}

	.summary td {
		//color: #666699;
		padding: 7px 17px;
		cursor:pointer;
		border-bottom:1px solid lightblue;
		border-left:1px solid lightblue;
		border-right:1px solid lightblue;
	}
	.summary th {
		border:1px solid lightblue;
		color: #003399;
		font-size: 14px;
		font-weight: normal;
		padding: 12px;
	}
	
	.summary{
		margin-left:25px;margin-bottom:10px;
	}
	
	.info td {
		color: #666699;
		padding: 7px 17px;
		cursor:pointer;
		border-left:    1px solid #6699CC;
		border-right:  1px solid #6699CC; 
		border-bottom: 1px solid #6699CC;
	}
	.info th {
		border-left:    1px solid #6699CC;
		border-right:  1px solid #6699CC; 
		border-top: 1px solid #6699CC;
		color: #003399;
		font-size: 14px;
		font-weight: normal;
		padding: 12px;
	}
	
	.typeRd{margin:5px;}
	.cCodeDiv{height:8px;width:8px;margin:6px;float:left;}
	
	
.progress {
    height: 3px !important;
}
#subCount{
 display:none;
}	
	</style>
</head>
<body>
<div class="container m_top10">

		<!-- Title Row -->
		<div class="row-fluid" id="fadeInDown">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="background:#ffffff;">
				<h3 class="text-center text-uppercase">Booth Wise Polling Info</h3>
			</div>
		</div><!-- Title Row End-->
		<div class="row-fluid " id="mainCount">
			<div id="mainInfo" class="span12 show-grid well well-small border-radius-0 mb-10">
				    
			</div>
		</div>
		<div class="row-fluid " id="subCount">
			<div id="subInfo" class="span12 show-grid well well-small border-radius-0 mb-10">
				    
			</div>
		</div>
</div>
<script type="text/javascript">
  function getBoothsCurrentStatus(){
	      $("#mainInfo").html('<center><img style="" id="ajaxImgStyle" src="images/icons/loading.gif"></center>');
		  $("#mainCount").show(); 
	           $.ajax({
					type : "POST",
					url : "getBoothsCurrentStatus.action"
				}).done(function(result){
	                  if((result.knownList != null && result.knownList.length > 0) || (result.unKnownList != null && result.unKnownList.length > 0) ){
						  if(result.knownList != null && result.knownList.length > 0){
							  $("#mainCount").show(); 
							  var boothData = result.knownList;
							  var str ='';
							  str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><tbody>'
						      str+='  <tr><th>Booth No</th><th>Booth Type</th><th>Total Voters</th><th>Votes Polled</th><th>Time</th><th>Mobile</th></tr>';
							 for(var i in boothData){
								str+='  <tr>'; 
								str+='    <td>'+boothData[i].name+'</td>'; 
								str+='    <td>'+boothData[i].role+'</td>'; 
								str+='    <td>'+boothData[i].total+'</td>'; 
								str+='    <td>'+boothData[i].status+'</td>'; 
								str+='    <td>'+boothData[i].locationName+'</td>'; 
								str+='    <td>'+boothData[i].membershipNo+'</td>'; 
								str+='  </tr>'; 
							 }
							
						      str+=' </tbody>';
					          str+='</table>';
							  $("#mainInfo").html(str);
						  }else{
							$("#mainInfo").html("");
							$("#mainCount").hide();  
						  }
						  if(result.unKnownList != null && result.unKnownList.length > 0){
							$("#subCount").show(); 
							var boothData = result.unKnownList;
							  var str ='<div style="font-size: 18px;margin-bottom: 5px;text-align: center;"><b>Messages From Un Known Mobile</b></div>';
							  str+='<table class="table table-bordered border-radius-0 mb-0 Previousmembercount table-hover" ><tbody>'
						      str+='  <tr><th>Votes Polled</th><th>Time</th><th>Mobile</th></tr>';
							 for(var i in boothData){
								str+='  <tr>'; 
								str+='    <td>'+boothData[i].status+'</td>'; 
								str+='    <td>'+boothData[i].locationName+'</td>'; 
								str+='    <td>'+boothData[i].membershipNo+'</td>'; 
								str+='  </tr>'; 
							 }
						      str+=' </tbody>';
					          str+='</table>';
							  $("#subInfo").html(str);
						  }else{
							$("#subInfo").html("");
							$("#subCount").hide();  
						  }
					  }else{
						   $("#mainCount").show();
							$("#subCount").hide();  						   
						  $("#mainInfo").html("No Data Available!");
					  }
				});
  }
getBoothsCurrentStatus();
</script>
</body>
</html>