<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ADMIN CARD PRINTING</title>

<link href="dist/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="dist/css/custom.css" rel="stylesheet" type="text/css">
<link href="dist/css/style.css" rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="js/sha512.js"></script>
</head>
<body>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-xs-12 col-sm-12">
			<div class="panel panel-default m_top10">
				<div class="panel-heading">
					<h4 class="panel-title">CONSTITUENCY WISE PRINT DETAILS UPDATION</h4>
				</div>
				<div class="panel-body">
					<div class="row">
						<div id="errorMsgDivId" style="color:red"></div>
						
						   <div class="col-md-4 col-xs-12 col-sm-4">
								<label>SELECT VENDOR:<span style="color:red"> *</span></label>
								<select id="vendorId" class="form-control">
									<option value="0">Select Vendor</option>
									 <c:if test="${basicVOList!= null && fn:length(basicVOList) gt 0}">  
	                                       <c:forEach var="vendor" items="${basicVOList}"> 
										     <option value="${vendor.id}">${vendor.name}</option>
									        </c:forEach>   
	                                 </c:if>
								</select>
						   </div>
						   
							<div class="col-md-4 col-xs-12 col-sm-4">
								<label>SELECT CONSTITUENCY:<span style="color:red"> *</span></label>
								<select id="constituencyId" class="form-control">
									<option value="0">Select Constituency</option>
								</select>
							</div>
							
							<div class="col-md-4 col-xs-12 col-sm-4">
								<button class="buttonCls btn btn-success" onclick="updatePrintDetailsToTdpCadreCardPrint()" style="margin-top: 23px; margin-left: 20px;">SUBMIT</button>
								<img src="images/search.gif" style="padding-left:10px;display:none;" width="50" height="50" id="processingImg"/></button>
							</div>
							<div class="col-md-12 col-xs-12 col-sm-12 m_top20">
							<div id="successMsgDivId"></div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="dist/js/jquery-1.11.3.js" type="text/javascript"></script>
<script src="dist/js/bootstrap.js" type="text/javascript"></script>

<script type="text/javascript">



  $('#vendorId').click(function(){
	  getConstituenciesByPrintVendor();
  });  
	
function getConstituenciesByPrintVendor(){
	 
	 $("#constituencyId option").remove();
	 $("#constituencyId").append('<option value="0"> Select Constituency</option>');
	 
	 var printVendorId = $("#vendorId").val();
	 if(printVendorId == 0){
		 return;
	 }
	 
	var jsObj = {  printVendorId : printVendorId }
	$.ajax({
	     type:'GET',
         url:'getConstituenciesByPrintVendorAction.action',
         dataType: 'json',
         data: {task:JSON.stringify(jsObj)}
      }).done(function(result){
		  if(result != null){
			  for(var i in result){
				  $("#constituencyId").append('<option value='+result[i].id+'>'+result[i].name+'</option>');
			  }
		  }
	  });
}

function updatePrintDetailsToTdpCadreCardPrint(){
	$("#errorMsgDivId").html("");
	$("#successMsgDivId").html("");
	
	var printVendorId = $("#vendorId").val();
	var constituencyId = $("#constituencyId").val();
	
	if(printVendorId == 0){
		$("#errorMsgDivId").html("Select Vendor");
		return;
	}
	if(constituencyId == 0){
		$("#errorMsgDivId").html("Select Constituency");
		return;
	}
	$("#processingImg").show();
	var jsObj = { 
	    printVendorId  : printVendorId,
		constituencyId : constituencyId
	}
	$.ajax({
		 type:'POST',
		 url:'updatePrintDetailsToTdpCadreCardPrintAction.action',
		 dataType: 'json',
		 data: {task:JSON.stringify(jsObj)}
	  }).done(function(result){
		  $("#processingImg").hide();
		 if(result != null){
		    
			if(result.resultCode != null && result.resultCode == 1 && result.id != null && result.id == 1){
				$("#successMsgDivId").html("<h4 style='color:green'> Constituency Print Details Updated Successfully...</h4>");
			}else if( result.resultCode != null && result.resultCode == 1 && result.id != null && result.id == 0){
				$("#successMsgDivId").html("<h4 style='color:blue'> Updating To Local DataBase Success.. Updating To Live DataBase Failure..</h4>");
			}else if( (result.resultCode != null && result.resultCode == 0) || (result.resultCode == null)){
				$("#successMsgDivId").html("<h4 style='color:red'> Exception Occurred.. try Later..</h4>");
			}
		 }else{
			 $("#successMsgDivId").html("<h4 style='color:red'> Exception Occurred.. try Later..</h4>");
		 }
		 
	  })
}
	
</script>

</body>
</html>