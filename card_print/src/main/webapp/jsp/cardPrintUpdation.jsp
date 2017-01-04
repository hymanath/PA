<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Card Print Updation</title>

<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="js/sha512.js"></script>
</head>
<body>

<div class="container" style="margin-top:20px;">
    
	<div class ="row" >
	     <div id="errDivId" style="color:red; margin-left:13px;"></div>
		<div class="col-md-3 col-sm-6 col-xs-12">
			  <label>Constituency</label><span class="text-danger">*</span>
			  <select class="form-control"  id="constituencyId">
			  <option value="0">Please Select Constituency</option>
			  <option value="1">Nandigama</option>
			  <option value="2">Jaggaya Peta</option>
			  <option value="3">Vijayawada Central</option>
			  <option value="4">Kavali</option>
			  </select>
		 </div>
		 
		 <div class="col-md-3 col-sm-6 col-xs-12">
			  <label>Print Status</label><span class="text-danger">*</span>
			  <select class="form-control" id="printStatusId">
			  </select>
		 </div>
		 
	</div>
	<div class ="row"> 
		<div class="col-md-6 col-sm-12 col-xs-12">
			<label>Remarks</label><span class="text-danger">*</span>
			<textarea class="form-control" rows="3" id="remarksId"></textarea>
		</div>
	</div>
	<div class ="row" style="margin-top:10px;">
		 <div class="col-md-3 col-sm-6 col-xs-12">
		  <input type="button" class="buttonCls btn btn-success" value="submit" id="submitButtnId"><span id="successMsgId"></span></input>
		</div>
	</div>
	
	<!-- <div class ="row" style="margin-top:10px;">
	   <input type="button" class="buttonCls btn btn-success" value="updatePrintDetailsToTdpCadreCardPrint" onclick="updatePrintDetailsToTdpCadreCardPrint()"></input>	
	</div> -->
	
	
</div>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">


getAllPrintStatusDetails();
getAllAssemblyConstituencies();

	/*function updatePrintDetailsToTdpCadreCardPrint(){
		var jsObj = { constituencyId:120 }
		$.ajax({
			 type:'POST',
			 url:'updatePrintDetailsToTdpCadreCardPrintAction.action',
			 dataType: 'json',
			 data: {task:JSON.stringify(jsObj)}
		  }).done(function(result){
			  alert("success");
		  })
	}*/
function getAllAssemblyConstituencies(){
	$.ajax({
	     type:'GET',
         url:'getAllAssemblyConstituenciesAction.action',
         dataType: 'json',
         data: {}
      }).done(function(result){
		  if(result != null){
			  $("#constituencyId").append('<option value="0"> Select Constituency</option>');
			  for(var i in result){
				  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			  }
		  }
	  })
}
function getAllPrintStatusDetails(){
	$.ajax({
	     type:'GET',
         url:'getAllPrintStatusDetailsAction.action',
         dataType: 'json',
         data: {}
      }).done(function(result){
		  if(result != null){
			  $("#printStatusId").append('<option value="0"> Select Print Status</option>');
			  for(var i in result){
				  $("#printStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			  }
		  }
	  })
}

	$('#submitButtnId').click(function(){
		
		var constituencyId = $('#constituencyId').val();
		var printStatusId = $('#printStatusId').val();
		var remarks = $('#remarksId').val().trim();
		
		if(constituencyId ==0){
			$("#errDivId").html('Please Select Constituency');
			return;
		}
		   $("#errDivId").html(' ');
			
		if(printStatusId ==0){
			$("#errDivId").html('Please Select Print Status');
			return;
		 }	
		 $("#errDivId").html(' ');
		 
		 if(remarks == '' || remarks ==""){
		  $("#errDivId").html('Please Write Some Description');
		  return ;
	    }	
		 $("#errDivId").html(' ');
		 
		var jsObj = {
			constituencyId:constituencyId,
			printStatusId : printStatusId,
			remarks : remarks
		}
		
		$.ajax({
	     type:'GET',
         url:'saveConstituencyPrintStatusAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  
		  $('#constituencyId').val(0);
		  $('#printStatusId').val(0);
		  $('#remarksId').val(' ');
		    
		  if(result != null){
			if(result.exceptionMsg =='success'){
			setTimeout(function () {
			$("#successMsgId").html("<center style='color: green; font-size: 16px;'>Saved Successfully</center>").fadeOut(3000);
			}, 500);  
			 $("#successMsgId").show();
		     $("#successMsgId").html("");
			}else{
			setTimeout(function () {
			$("#successMsgId").html("<center style='color: green; font-size: 16px;'>Failed</center>").fadeOut(3000);
			}, 500);  
			$("#successMsgId").show();
			$("#successMsgId").html("");
			}
		  }
	  })
		
	});
</script>

</body>
</html>