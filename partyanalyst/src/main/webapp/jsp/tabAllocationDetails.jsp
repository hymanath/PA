<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DeAllocate Tab From User</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script type="text/javascript" src="js/jQuery/js/jquery-ui-1.8.5.custom.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">	
<style>
  #errMsgDiv{
    
}
</style>
</head>
<body>
    <div class="container m_top10">
      <div id="locationWiseCadreInfoDiv">
		   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
				<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;">
					<h3 class="text-center text-uppercase">DeAllocate Tab From User</h3>
				</div>
			</div>
	  </div>
	   <div class="row-fluid" id="fadeInDown" style="padding-top: 5px;">
			<div class="span12 well well-small  border-radius-0 mb-10 " style="padding:0px;min-height:200px;">
				<div>
				     <div id="errMsgDiv"></div>
				     <table  style="margin-left: 270px;margin-top: 15px;">
				       <tr>
					      <td><b>Select Error Message :</b></td>
					      <td>
						       <select id="errorMessageSelId" onchange="changeShowDetails();" style="height:35px;">
							     <option value="1">Same User</option>
								 <option value="2">Same Tab</option>
							   </select>
						  </td>
					  </tr>
					  <tr id="sameUserTrDiv">
					      <td><b>Enter UserName :</b></td>
					      <td><input type="text" id="userNameId" style="height:25px;"/></td>
					  </tr>
					  <tr id="sameTabTrDiv" style="display:none;">
					      <td><b>Enter IMEI Number :</b></td>
					      <td><input type="text" id="imeiId" style="height:25px;"/></td>
					  </tr>
					  <tr>
					      <td></td>
					      <td><input type="button" value="Submit" onclick="getDetails();" class="btn btn-success" id="getDtlsSubmitBtn"/></td>
					  </tr>
					</table>
					<div id="resultTableDiv" style="margin-top:15px;background-color: #fff;"></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	  function changeShowDetails(){
	     $("#userNameId").val("");
		 $("#imeiId").val("");
		 $("#errMsgDiv").html("");
		 $("#resultTableDiv").html("");
	     var id = $("#errorMessageSelId").val();
	     if(id == 1){
		    $("#sameUserTrDiv").show();
		    $("#sameTabTrDiv").hide();
		 }else{
		    $("#sameUserTrDiv").hide();
		    $("#sameTabTrDiv").show();
		 }
	  
	  }
	  function getDetails(){
	     $("#errMsgDiv").html("");
		 $("#resultTableDiv").html("");
		 var reqId = $("#errorMessageSelId").val();
		 var reqVariable = "";
	     if(reqId == 1){
		   reqVariable = $.trim($("#userNameId").val());
		   if(reqVariable.length == 0){
		     $("#errMsgDiv").html("<div style='color:red;font-weight:bold;margin-bottom:-10px;margin-left:435px;margin-top:10px;'>Please Enter UserName</div>");
			 return;
		   }
		 }else{
		   reqVariable = $.trim($("#imeiId").val());
		   if(reqVariable.length == 0){
		     $("#errMsgDiv").html("<div style='color:red;font-weight:bold;margin-bottom:-10px;margin-left:435px;margin-top:10px;'>Please Enter IMEI Number</div>");
			 return;
		   }
		 }
		  $.ajax({
			  type:'GET',
			  url: 'getAuthDetailsAction.action',
			  data: {id:reqId,variable:reqVariable}
          }).done(function(result){
		       var str ="";
	           if(result != null && result.length > 0){
			     str+="<table class='table table-bordered m_top20 '>";
				  str+=" <tr>";
				  str+="   <th>USER NAME</th>";
				  str+="   <th>NAME</th>";
				  str+="   <th>MOBILE</th>";
				  str+="   <th>IMEI</th>";
				  str+="   <th>CAUSE FOR DEALLOCATE&nbsp;<b style='color:red;font-size: 20px;'>*</b></th>";
				  str+="   <th>DEALLOCATE</th>";
				  str+=" </tr>";
				 for(var i in result){
				  str+="<tr>";
				  str+="   <td>"+result[i].uname+"</td>";
				  str+="   <td>"+result[i].name+"</td>";
				  str+="   <td>"+result[i].number+"</td>";
				  str+="   <td>"+result[i].tabNo+"</td>";
				  str+="   <td><textarea id='textArea"+i+"'></textarea></td>";
				  str+="   <td><input type='button' value='Deallocate' onclick='deallocateTab("+result[i].id+",\"textArea"+i+"\");' class='btn btn-warning'/></td>";
				  str+="</tr>";
				 }
				 str+="</table>";
			   }else{
			     str+="<div style='font-weight:bold;margin-left:415px;'>No Data Available</div>";
			   }
			   $("#resultTableDiv").html(str);
	      });
	  }
	  function deallocateTab(authId,textAreaid){
	    if(confirm("Do You Want To Deallocate Tab From User?")){
		   if($.trim($("#"+textAreaid).val()).length == 0){
		      alert("Please Enter Cause For Deallocate Of Tab From User");
			  return;
		   }
		    $.ajax({
			  type:'GET',
			  url: 'updateTabAllocationDetailsAction.action',
			  data: {authId:authId,cause:$.trim($("#"+textAreaid).val())}
          }).done(function(result){
		     if(result == "success"){
			   alert("Tab Deallocate From User Successfully");
			   $("#resultTableDiv").html("");
			 }else{
			   alert("Please Try Again Latter");
			 }
		  });
		}
	  }
	</script>
</body>
</html>