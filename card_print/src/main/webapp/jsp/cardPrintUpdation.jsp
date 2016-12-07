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
		<div class="col-md-3 col-sm-6 col-xs-12">
			  <label>Constituency</label><span class="text-danger">*</span>
			  <select class="form-control"  id="cardConstituencyId">
				<option value="0"> Select Constituency </option>
				<option value="1"> Nellore</option>
				<option value="2"> Vijayawada Central </option>
				<option value="3"> Jaggaya Peta </option>
				<option value="4"> Nuzvid </option>
				<option value="5"> Gudivada </option>
			  </select>
		 </div>
		 <div class="col-md-3 col-sm-6 col-xs-12">
			  <label>PrintStatus</label><span class="text-danger">*</span>
			  <select class="form-control" id="printStatusId">
			  </select>
		 </div>
		 
	</div>
	<div class ="row"> 
		<div class="col-md-6 col-sm-12 col-xs-12">
			<label>Remarks</label><span class="text-danger">*</span>
			<textarea class="form-control" rows="3"></textarea>
		</div>
	</div>
	<div class ="row" style="margin-top:10px;">
		 <div class="col-md-3 col-sm-6 col-xs-12">
		  <input type="button" class="buttonCls btn btn-success" value="submit" id="submitButtnId">
		</div>
	</div>
		
	
</div>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript">
getAllPrintStatusDetails();
function getAllPrintStatusDetails(){
	alert('hi');
	var jsObj={
		
	}
	$.ajax({
	     type:'GET',
         url:'getAllPrintStatusDetailsAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  console.log(result);
		  if(result != null){
			  for(var i in result){
				  $("#printStatusId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			  }
		  }
		  
})
}
</script>

</body>
</html>