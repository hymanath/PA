<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News - Telugudesham Party</title>

<script type="text/javascript" src="js/jQuery/jquery-1.4.2.min.js"></script>


<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yahoo-dom-event/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/animation/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/calendar/calendar-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/json/json-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/element/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/datasource/datasource-min.js" ></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection-min.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/get/get-min.js" ></script>
	 
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/connection/connection.js"></script> 	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/yuiloader/yuiloader-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/dom/dom-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/build/event/event-min.js"></script>
	<script type="text/javascript" src="js/LocationHierarchy/locationHierarchy.js"></script>	
	<script type="text/javascript" src="js/yahoo/yui-js-3.0/build/yui/yui-min.js"></script>
	 
	<!-- YUI Skin Sam -->
<!-- YUI Dependency files (End) -->

	
	
	<!-- Bootstrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="css/style.css">
	<!-------PT-sans font---->
	<link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>

	<style type="text/css">
	#signin{clear: both;
    float: left;}
	</style>
</head>
<body>
		<div class="container-fluid headerBg" style="padding-left: 0px; padding-right: 0px;">
		<!---Header----->
		<div class="container">	
			<div class="row">
				<!----Logo----->
				<div class="span12">
					<img src="images/Logo.png" alt="Telugudhesham party logo" />
				</div>
				
			</div>
		</div>
		<!----Header End--->
		</div>
		<!----Container---->
		<div class="container">
		<!--------- Row-1 --------<img alt="" src="images/bootstrap-mdo-sfmoma-02.jpg">-->
			<div class="row m_top10">
				<div class="span12 m_top10">
					<div class="carousel slide" id="myCarousel">
						<div class="carousel-inner">
							<div class="item active">
							<img alt="" src="images/bootstrap-mdo-sfmoma-02.jpg"">
								<div class="container">
								<div class="carousel-caption text-center">
									<h4>--: WELCOME :--</h4>
									<p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
									<button class="btn btn-primary" type="submit"  href="#test_modal" data-toggle="modal">Sign in</button>
								</div>
						  </div>
						</div>
						<!-------login pop up  div--------->
						<div class="modal fade" id="test_modal">								 
							<!-- <div class="modal-body">
								<form class="form-signin modal-body ">
									<h2 class="form-signin-heading">Please sign in</h2>
									<input type="text" placeholder="Email address" class="input-block-level">
									<input type="password" placeholder="Password" class="input-block-level">
									<label class="checkbox">
									<input type="checkbox" value="remember-me"> Remember me
									</label>
									<button type="submit" class="btn btn-large btn-primary">Sign in</button>
								</form>
							</div>
							<div class="modal-footer">									
								<a href="#" class="btn btn-primary" data-dismiss="modal">Close</a>
							</div> -->
						</div>
						<!----------popup end------>
        
        
      </div>
      
    </div>
				</div>
				
				
			</div>
		<!--------- Row-1 End -------->
		
		<!-----Footer---->
	 <footer class="span12" >
        <p class="text-center">&copy; Telugudesham Party 2013</p>
      </footer>
		<!----<div class="row">
			<div class="span12">
				
			</div>
		</div>---->
		
	</div>
		<!------JS------>
		
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.carousel.js"></script>	
	<script>
	var redirectTo = false;
			$(document).ready(function(){	
			$(".carousel-indicators li:eq(0)").trigger("click");
			});
			$(document).ready(function() {

    //$('#test_modal').modal('show')

	openDialogForLoginWindow();

});




function openDialogForLoginWindow(){
	$('#test_modal').modal('show');

var str='';
str +='<div class="modal-body">';
str +='<div class="form-signin modal-body ">';
str +='<h2 class="form-signin-heading">Please sign in</h2>';
str+='<div id ="LoginErrorMessageDiv" style="color:red; margin-left: 55px;margin-bottom:10px;"></div>';

str +='<input type="text" id="userName1" placeholder="Email address" class="input-block-level" />';
str +='<input type="password" placeholder="Password" class="input-block-level" id="passWord_Id1" />';

str +='<label class="checkbox" style="float:left;">';
str +='<input type="checkbox" value="remember-me"> Remember me';
str +='</label>';

str+='<input id="signin" value="Sign In" onclick="ajaxCallForLoginPopup();" class="btn btn-large btn-primary" style="width: 63px;"/>';
//str +='<button type="submit" class="btn btn-large btn-primary" //onclick="ajaxCallForLoginPopup();">Sign in</button>';
str +='</div>';
str+='<div id="ajaxcallimage"  class="span3" style="display:none;margin-top:10px;font-weight:bold;color: #0174DF;font-size:small;width: 345px; height: 17px;">';
str+='<font  style="font-size:small;">Sending Your Request. Please wait...</font>';
str+='<img src="images/icons/loading.gif" style="padding-left:10px;" width="18" height="11"/>';
str+='</div>';
str +='<div class="modal-footer">';									
str +='<a href="#" class="btn btn-primary" data-dismiss="modal">Close</a>';
str +='</div>';
str +='</div>';


$("#test_modal").html(str);
	

}

function ajaxCallForLoginPopup(){

var validInd=handleErrorMessageForpopup();

if(!validInd)
	return false;

var userName1=document.getElementById('userName1').value;
var password1=document.getElementById('passWord_Id1').value;

task="validateUserForLogin";
 
 		
 var jsObj=
	 {
	//userName:userName1,
	//password:password1,
	task:task		
};
		//var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
		var url = "loginPopUpsAction.action?&userName="+userName1+"&password="+password1+"&task="+task;	
		callHomePageAjax11(jsObj,url);
		

}

function handleErrorMessageForpopup()
                { 
				
                 var emailId = document.getElementById("userName1").value;
                 var securityName = document.getElementById("passWord_Id1").value;
                 var errorDivEle = document.getElementById("LoginErrorMessageDiv");
	             var eFlag = false;

	             var str = '<font color="red">';

	              if(emailId.length == 0 || emailId == "Email" )
	               {
		              str += ' username is required<br>';
		              eFlag = true;
	               }
	
	              if(securityName.length == 0 || securityName =="Password")
	               {
		               str += ' password is required<br>';
		                eFlag = true;
	               }
	              	                str += '</font>';
	                errorDivEle.innerHTML = str;

	
	                if(eFlag){
		                return false;
					}
					return true;	
				}


				function callHomePageAjax11(jsObj,url){
	 
	var callback = {			
				   success : function( o ) {
					   
						try
						{
							myResults = YAHOO.lang.JSON.parse(o.responseText);	
							
							if(jsObj.task == "validateUserForLogin")
							{	
								
								if(myResults.resultCode == 0)
								{
									$("#ajaxcallimage").show();
									$('#ajaxcallimage').html('Login Successfull,Page is refreshing Please wait...');
									
									  window.location.href = "homePage.action";
								}
								else{
									
									$('#ajaxcallimage').html('');
									$("#LoginErrorMessageDiv").html('Invalid username or password! Please try again!');
								} 
								
							}	
							
						}catch(e)
						{   

							alert("Invalid JSON result" + e);   
						}  
				   },
				   scope : this,
				   failure : function( o ) {
								//alert( "Failed to load result" + o.status + " " + o.statusText);
							 }
				   };

	YAHOO.util.Connect.asyncRequest('GET',url, callback);



}

$(document).keypress(function(e) {

if(e.which == 13) {
var form = document.getElementById("signin");
if(form != null)
{
ajaxCallForLoginPopup();
}
}
});

	</script>
</body>
</html>