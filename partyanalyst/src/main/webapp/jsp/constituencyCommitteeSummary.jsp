<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Request</title>
	
    <!-- Bootstrap -->
    <link href="css/cadreCommitee/bootstrap.min.css" rel="stylesheet">
	 <!-- Custom Styles -->
    <link href="css/cadreCommitee/style.css" rel="stylesheet">
	<!----slick.css----->
	<link rel="stylesheet" type="text/css" href="css/cadreCommitee/slick/slick.css"/>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->

	<!----slick Js----->
	<script type="text/javascript" src="js/cadreCommittee/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/cadreCommitteeRequest/cadreCommitteeRequest.js"></script>
	
</head>
<body>

<div class="container">
    <div class="row m_top20">
  	   <div class="col-md-4 col-md-offset-2 col-sm-6 col-xs-6">Select District:<select id="districtsId" class="form-control" onChange="getAllConstituencysForADistrict()"><option value="0">Select District</option></select> </div>
       <div class="col-md-4  col-sm-6 col-xs-6">Select Constituency:<select id="constituencysId" class="form-control"><option value="0">Select Constituency</option></select> </div>
    </div>
</div>
<script type="text/javascript">
getAllDistricts();
function getAllDistricts()
{

 $.ajax({
			type : "GET",
			url : "getAllDistrictsAction.action",
			data : {} ,
		}).done(function(result){
			$("#districtsId  option").remove();
			$("#districtsId").append('<option value="0">Select District</option>');
			if(result!=null){
			for(var i in result){
				   $("#districtsId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
				 }
			}	 
		});
}
function getAllConstituencysForADistrict()
{
    
 
    var districtId = $("#districtsId").val();
	 $("#constituencysId  option").remove();
	 $("#constituencysId").append('<option value="0">Select Constituency</option>');
	if(districtId==0){
	  return;	 
	}
 
    $.ajax({
			type : "GET",
			url : "getAllConstituencysForADistrictAction.action",
			data : {districtId:districtId} ,
		}).done(function(result){
			if(result!=null){
			   for(var i in result){
				   $("#constituencysId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			    }
			}	 
		});
}
</script>

</body>
</html>