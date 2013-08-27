<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>

<link type="text/css" rel="stylesheet" href="styles/newsstyle.css">

<link rel="stylesheet" type="text/css" href="styles/colortip-1.0/colortip-1.0-jquery.css"/>

 
<script type="text/javascript" src="js/colortip-1.0/colortip-1.0-jquery.js"></script>
<script type="text/javascript" src="js/colortip-1.0/script.js"></script>
<!-- YUI Dependency files (Start) -->
	<script type="text/javascript" src="js/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="js/yahoo/yahoo-dom-event.js"></script> 
	<script type="text/javascript" src="js/yahoo/animation-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script>
	<script type="text/javascript" src="js/yahoo/element-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/button-min.js"></script> 	
	<script src="js/yahoo/resize-min.js"></script> 
	<script src="js/yahoo/layout-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/container-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dom-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/yui-min.js"></script>
	<script type="text/javascript" src="js/json/json-min.js"></script>
	<script type="text/javascript" src="js/yahoo/connection-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/tabview-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datasource-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/get-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/dragdrop-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/datatable-min.js"></script> 
	<script type="text/javascript" src="js/yahoo/paginator-min.js"></script>
	
	<script type="text/javascript" src="js/yahoo/yui-js-2.8/calendar-min.js"></script>
	<!-- Skin CSS files resize.css must load before layout.css --> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/resize.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/layout.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/container.css"> 
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/button.css"> 
 	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/tabview.css">
	<link type="text/css" rel="stylesheet" href="styles/yuiStyles/datatable.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/paginator.css">
	<link rel="stylesheet" type="text/css" href="styles/yuiStyles/calendar.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/calendar/assets/skins/sam/calendar.css">    
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/container/assets/skins/sam/container.css"> 
	<link rel="stylesheet" type="text/css" href="js/yahoo/yui-js-2.8/build/button/assets/skins/sam/button.css">	

	<!-- YUI Dependency files (End) -->
<style>
.imgStyle{
	border: medium none;
	color:#000;
	font-weight:bold;
	clear:both;
	display:inline-block;
	text-decoration:none;
}
.promos{display:inline-block;width:860px;box-shadow:inset 0px 0px 5px #333;padding-top:5px;border-radius:5px;background:url('images/new_homepage/banner-bg.gif');color:#000;}

.boxdiv{width:380px;float:left;margin:5px;background:#f9f9f9;padding:5px;border-radius:2px;box-shadow: 0 0 2px #888888;}
.promoheader{display:table;clear:both;height:100px;width:100%;}
.promoheader .pheaderleft{float:left;display:table;padding:5px;padding-left:20px;}
.promoheader .pheaderright{float:left;display:table;padding:5px;text-align:center;width:70%;}
.promoheader .pheaderright h1{font:bold 36px Arial ;border-bottom:1px solid #cccccc;color:red;text-shadow:0px 0px 2px #333;}
.boxdiv h1{font-size:22px;color:#12A3EB;text-shadow:0px 0px 3px #fff;}
.Newsmontior a{position:relative;display:table;padding:7px 20px;border-radius:3px;background:#C93033;color:#fff; text-decoration:none;
box-shadow:0px 0px 5px #888;margin:5px;margin-top:18px;}
.Newsmontior{margin-left:20px;height:200px;}
.Newsmontior a:before{content:" ";
border:10px solid transparent;
border-bottom:10px solid #C93033;
border-right:10px solid #C60003;
position:absolute;
top:-64%;
left:50%;
}
.Election2014{height:200px;}
.Election2014 span{display:table;color:navy;}
.ElectionInfo{display:block;margin-left: 89px;}
.ElectionInfo a{background: none repeat scroll 0 0 #C93033;border-radius: 3px 3px 3px 3px;box-shadow: 0 0 5px #888888;color: #FFFFFF;display: table;font-family: helvetica;font-weight: bold;font-size: 13px;margin: 22px 15px 0px;padding: 6px 7px;position: relative;text-decoration: none;}
.ElectionInfo a:before{content:" ";
border:10px solid transparent;
border-bottom:10px solid #C93033;
border-right:10px solid #C60003;
position:absolute;
top:-18px;
left:50%;
}
#contactDiv span{background: none repeat scroll 0 0 #C93033;border-radius: 3px 3px 3px 3px;box-shadow: 0 0 5px #888888;color: #FFFFFF;display: table;font-family: helvetica;font-size: 13px;margin: 22px 15px 0px;padding: 6px 7px;position: relative;text-decoration: none;}
#contactDiv span:before{content:" ";
border:10px solid transparent;
border-bottom:10px solid #C93033;
border-right:10px solid #C60003;
position:absolute;
top:-18px;
left:50%;
}
.service-box{
	width: 193px;
	height: 270px;
	text-align:left;
	background: threeDFace;
}
.service-box a{
	background: none repeat scroll 0 0 #000000;
    color: ActiveCaption;
    margin-left: 3px;
    opacity: 0.55;
	width: 107px;
}
.service-box:hover a, .service-box a:hover{
-webkit-transition: 200ms linear 0s;
-moz-transition: 200ms linear 0s;
-o-transition: 200ms linear 0s;

}
.tdhref td{background:#FFF;text-align:center;border:1px solid #ddd;padding:7px 0px;}
.tdhref a{
margin :0px;
padding:0px;
background:#93CE56 !important;
color:#000 !important;
box-shadow:0px 0px 0px #fff;width:100%;
text-align:center;
border-radius:0px;
font:bold 14px Tahoma;
text-shadow:0px 1px 1px #fff;
}

.tdhref td:hover{background:#F4FFDB;box-shadow:0px 1px 2px #333;}
 .tdhref a:before{border-bottom-color:#93CE56;left:65%;border-right-color:#4F9307;}
.tdhref{margin:4px 0px;}
.ElectionInfo-new h3,.padhayatra h3{font:bold 18px Arial;text-shadow:0px 0px 2px #d3d3d3;padding:5px;color:#000;}
.ElectionInfo-new div{float:left;padding:5px;width:40%;display:inline-block;background:#fff;border:1px solid #ddd;margin:5px;margin-left:13px;text-align:center;}
.ElectionInfo-new div a{margin-top:0px !important;}
.ElectionInfo-new div:hover{background:#ddd;}
#fancybox-wrap{z-index:9999999;}
.services-block div{margin:10px 6px; box-shadow:3px 3px 3px #fff;opacity:0.8;}
.services-block div:hover{box-shadow:0px 2px 2px #000;opacity:1;}
.free-user .alert-info{text-align:center;}
<!------------------>

<!------------------------>
</style>
<div class="whitegloss">
<div>	
								
								  <div class="modal-header ">
									<h3>Win 2014 Election by gaining 3% more votes with our product at a very low price</h3>
								  </div>
								  
								  <div class="modal-body "><div class="row-fluid">
									    

<div class="span8">
<img src="images/new_homepage/explain.gif"/></div>
<form class=" span4">
										  <h2><small>For our Product</small> DEMO</h2><span id="errorMsg" style="display:none;color:red;font-family:verdana;"></span><fieldset>
											<div class="control-group">
												<label class="control-label">MLA/Aspirant Name</label>
												<div class="controls">
												 <input type="text" placeholder="Enter MLA/Aspirant Name..." id="name">
												</div>
											</div>
											
											<div class="control-group">
												<label class="control-label">Mobile Number</label>
												<div class="controls">
												 <input type="text" placeholder="Enter Mobile Number..." id="mobileId">
												</div>
											</div>
											
											<div class="control-group">
												<label class="control-label">Constituency</label>
												<div class="controls">
												 <input type="text" placeholder="Enter Constituency..."  id="constituencyName">
												</div>
											</div>
<div class="control-group">
												<label class="control-label">Email Id</label>
												<div class="controls">
												 <input type="text" placeholder="Enter Email Id..." id="emailId">
												</div>
											</div>
											
											
											
											
											
											
											
											
											
											
											
										  </fieldset>	
										</form>
								  </div></div>
								  
								  <div class="modal-footer">
								  <div id="textMsg" style="text-align:center;display:none;font-family:verdana;"></div>
								  <img id='AjaxImg' style='width: 20px; padding-left: 30px; display: none;' src='images/icons/loading.gif'>
									<a href="#" class="btn btn-primary" id="sendMailToAdminGroup" onclick="validate()">Submit</a>
									<a href="#" class="btn " id="skipId" onclick="closeDialogue();">Skip</a>
								  </div>
								</div>
</div>
<script>
$(document).ready(function(){
$(".ElectionInfo a").parent().click(function(){
$(location).attr('href',$(this).find("a").attr("href"));

 });
setTimeout(function(){
$("#fancybox-wrap").css({"width":"880px","margin-left":"auto","margin-right":"auto"});
$("#fancybox-wrap").addClass("centerdiv");
$("#fancybox-content").css({"width":"860px"});
},0);

});

function validate()
{
var mobileNO = $("#mobileId").val();
if(mobileNO.length == 0)
	{
	$("#errorMsg").css("display","block");
	$("#errorMsg").html('Mobile No is Mandatory').css("display","red");;
    return;
	}
$("#textMsg").html('Your request is submitted successfully and we will get back to you soon..........').css({ 'display': 'block', 'color': 'green' });
$("#AjaxImg").show();
$("#errorMsg").html("");
	var constituencyName = $("#constituencyName").val();
	var name = $("#name").val();
	var email =$("#emailId").val();
	 $("#sendMailToAdminGroup").attr('disabled','disabled');
	var jsObj=
				{ 
					constituencyName:constituencyName,
					mobileNO:mobileNO,
					name:name,
					email:email,
					task:"sendEmail"
				};

	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "sendMailtoAdminGroupAction.action?"+rparam;
	ajaxResultInPopUpPage(jsObj,url);
	setTimeout("$.fancybox.close();",3000);
}
function closeDialogue()
{
  $.fancybox.close();
}

function ajaxResultInPopUpPage(jsObj,url)
		{
			 var myResults;

			 var callback = {			
 		               success : function( o ) {
							try {												
									myResults = YAHOO.lang.JSON.parse(o.responseText);	
									
									}catch (e) {
							     
								}  
 		               },
 		               scope : this,
 		               failure : function( o ) {
 		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
 		                         }
 		               };

 		YAHOO.util.Connect.asyncRequest('POST', url, callback);
 	}
	
</script>