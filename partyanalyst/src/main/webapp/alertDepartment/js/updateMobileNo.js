 $(document).on("click","#getOTPBtnId",function(){
	  var mobileNo = $("#mobileNoTextBoxId").val();
	  if(mobileNo == null || mobileNo.trim().length == 0){
		  $(".errorMsgCls").html("Please enter mobile number");
		  return;
	  }
	  var testMobile=/^(\d{10})$/;
	  if(mobileNo != null && !testMobile.test(mobileNo.trim())){
		  $(".errorMsgCls").html("Mobile number must be 10 digits only");
		  return;
	  } 
	  var mobilePattern="0000000000";
	  if(mobileNo != null && mobilePattern==mobileNo.trim()){
		  $(".errorMsgCls").html("Please enter valid mobile number");
		  return;
	  }
	  $(".errorMsgCls").html("Please wait you will get OTP soon");
	  generateAndSendOTPSmsAction(mobileNo);
	  clearMessage();
	  
	  $(".otptextBoxCls").show();
 });
 function clearMessage(){
	  setTimeout(function(){$(".errorMsgCls").html('')}, 10000);
 }
 function generateAndSendOTPSmsAction(mobileNo)
{
    var jsObj ={
      mobileNo : mobileNo
    }
    $.ajax({
      type:'POST',
      url: 'generateAndSendOTPSmsAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
	    if(result == null || result.length == 0){
			$(".errorMsgCls").html("Something Went Wrong.Please Try Again");
			 clearMessage();
		}else{
		   $("#referenceId").html("Your Referenced Id #"+result);	
		}
    });
}
$(document).on("click","#validateOtpBtnId",function(){
	  var mobileNo = $("#mobileNoTextBoxId").val();
	  var otp = $("#otpNumberTextboxId").val();
	  if(otp == null || otp.trim().length == 0){
		  $(".errorMsgCls").html("Please Enter OTP");
		  return;
	  }
	  validateOTP(mobileNo,otp);
 });

function validateOTP(mobileNo,otp)
{
    var jsObj ={
      mobileNo : mobileNo,
	  otp:otp
    }
    $.ajax({
      type:'POST',
      url: 'validateOTPAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
	   if(result != null && result=="success"){
		     $(".submitBtnCls").show();
			 $(".errorMsgCls").css("color", "green");
			 $(".errorMsgCls").html("Your OTP has been verified successfully.Please Update")
		}else{
		    $(".errorMsgCls").html("Your OTP did not match.Please try again");
		}
		clearMessage();
    });
}

 $(document).on("click","#updateMobileNoBtnId",function(){
	  var mobileNo = $("#mobileNoTextBoxId").val();
	  var otp = $("#otpNumberTextboxId").val();
	  if(mobileNo == null || mobileNo.trim().length == 0){
		  $(".errorMsgCls").html("Please Enter Mobile For Update");
		  return;
	  }
	  updateMobileNo(mobileNo,otp);
 });
 function updateMobileNo(mobileNo,otp)
{
    var jsObj ={
      mobileNo:mobileNo,
	  otp : otp
    }
    $.ajax({
      type:'POST',
      url: 'updateOfficerMobileNoAction.action',
      data: {task :JSON.stringify(jsObj)}
    }).done(function(result){
	   if(result != null && result=="success"){
		    $(".errorMsgCls").css("color", "green");
			$(".errorMsgCls").html("Mobile Number has updated successfully");
			$("#mobileNoTextBoxId").val('');
			$("#otpNumberTextboxId").val('');
			window.location.reload(true);
		}else{
		   $(".errorMsgCls").html("Something Went Wrong.Please Try Again");
		}
		clearMessage();
    });
}