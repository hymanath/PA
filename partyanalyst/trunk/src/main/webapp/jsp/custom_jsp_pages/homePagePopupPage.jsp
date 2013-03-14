<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>

<link type="text/css" rel="stylesheet" href="styles/newsstyle.css">

<link rel="stylesheet" type="text/css" href="styles/colortip-1.0/colortip-1.0-jquery.css"/>

 
<script type="text/javascript" src="js/colortip-1.0/colortip-1.0-jquery.js"></script>
<script type="text/javascript" src="js/colortip-1.0/script.js"></script>

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
<div class="promoheader">
<div class="pheaderleft">
<img src="images/icons/logoPA.png" alt="logo"/></div>
<div class="pheaderright">
<h1><img src="images/Popup-add-600x90.gif" style="width: 601px;"></h1>
<span style="font:bold 16px Arial;text-shadow:0px 0px 2px #d3d3d3;">CONNECT WITH PARTY ANALYST AND GET FREQUENT UPDATES.</span>
</div>
</div>

<!--<div class="Newsmontior boxdiv" style="height:194px;">
<h1 style="background:url('images/icons/homePage/LocationNews.png') no-repeat left;background-size:50px 50px;padding-left:55px;padding:10px 55px;">News Monitoring Service</h1>
<p style="margin-top:13px;line-height:18px;">
<b>Analysis of your Media Coverage:</b> As a Politician or Political Party, News Monitoring and analyzing is very important to keep upper hand in the political arena.
<a href="newsMonitoringService.action">Click Here to Know More</a></p>
</div>

<div class="Election2014 boxdiv" style="height:127px;">
<h1 style="background:url('images/icons/homePage/Flag.png') no-repeat left;background-size:50px 50px;padding-left:55px;padding-top:10px;font-size:20px;">Are you Aspirant candidate for<br/>
2014 elections in Andhra Pradesh</h1>
<p style="margin-top:13px;line-height:18px;">
Get the complete picture of your constituency to strategize or to understand the political picture of the constituency village wise by <font style="font-weight:bold;color:Crimson;">Constituency Profile Report</font>.</p>

</div>-->



<div class="breadcrumb span11 free-user"  style="width:830px;">
<h3>The New Party Analyst - Modified, Enhanced and Focused with the abilities to share, connect and spread the updates across the network.</h3>
<div class="whitegloss" >
  

<a class=" alert alert-info span3 btn   green "   title3="You can find your constituency, district people and connect with them to share your views." ><h4 >Connect and Share</h4></a> 


<a class="alert alert-info span3 btn green"  title3="Are you facing any social problems? Post your problems." ><h4>Post Problems</h4></a>

<a class="alert alert-info span3 btn green"  title3="Get frequent updates of your favorite party or politicians and latest hot topics." ><h4>Subscriptions</h4></a>

<a class="alert alert-info span3 btn green"  title3=" Stay tuned with the political trends." ><h4>Stay Tuned</h4></a>

<a class="alert alert-info span3 btn green"  title3="Know public schemes, current affairs and important facts." ><h4>Awareness</h4></a>

<a  class="alert alert-info span3 btn green"  title3="you may like different pages in Party Analyst, just add and view them in a single click." ><h4>Ease of Access</h4></a>

<c:if test="${sessionScope.loginStatus == null || sessionScope.loginStatus == 'in'}">
	<div class="alert alert-info">
	Join Party Analyst Now and Explore, It's Free!! <a href="freeUserRegistration.action" class="btn btn-primary">Register Now</a> Already Registered, <a href="loginInputAction.action" class="btn btn-primary">Login</a> and Explore Now.
	</div>
</c:if>

 </div>
 
 </div>



 
<table style="background:#fff;width:100%">
<tr><td><h2 style="color: #12A3EB;margin-left: 11px;">Our Services</h2></td>
<td colspan=4 class="breadcrumb"><h3 style="float:left;display:inline-block;">Are you Aspirant Candidate for 2014 Elections?</h3><a class="btn btn-primary" style="margin-left:10px;" href="images/PartyAnalyst_V1.0.pdf" target="_blank">Click Here To Know More</a></td>
</tr>
<tr>
<td></td></tr></table>
<div class="services-block">
  
				<div class="pull-left">
				    <a  href="VotersPulse.action" >
					<img src="images/voters_pluse_New.gif" style="  border:1px solid #ccc;" width="200px">
					</a>
				</div>
				<div class="pull-left">
				    <a   href="constituencyProfileReport.action">
					<img src="images/Constituency_A.gif" style=" border:1px solid #ccc;" width="200px">
					</a>
				</div>
				<div class="pull-left">
				    <a  href="electionAnalysisAndManagementTool.action">
					<img src="images/Election-Analysis-and-Management-Tool_A_1.gif" style="border:1px solid #ccc;" width="200px">
					</a>
				</div>
				<div class="pull-left">
				    <a  href="newsMonitoringService.action">
					<img src="images/News-Monitoring-dervices.gif" style="border:1px solid #ccc;" width="200px">
					</a>
				</div>
			</div>
			

<!--<div id="contactDiv"><span style="font-weight:bold;margin-top:5px;">
To know more call us : +91  9676696760 &nbsp;(OR) <br />Email us : <a href="mailto:info@partyanalyst.com">info@partyanalyst.com</a></span></div>-->
<!-- <div class="ElectionInfo boxdiv" style="height: 92px; padding-left: 10px; width: 425px; padding-right: 10px; margin-right: -15px; margin-left: 70px;">
<h1 style="background:url('images/icons/homePage/report.png') no-repeat left;background-size:50px 50px;padding-left:55px;padding-top:10px;font-size: 20px;">Election Information</h1>
<p style="margin:1px;"><a href="specialPageAction.action?specialPageId=13">Gujarat Elections 2012</a>

<a href="specialPageAction.action?specialPageId=14" style="float: right; margin-top: -31px;">Himachal Pradesh Elections 2012</a>
</p>
</div>
<div class="ElectionInfo boxdiv" style="height: 92px; width: 275px; padding-left: 0px; padding-right: 0px; margin-left: 21px;">
<h1 style="background-size:50px 50px;padding-left: 17px;padding-top:10px;font-size: 20px;">Latest Updates</h1>
<p style="margin:1px;"><a style="margin-left: 50px; font-weight: bold;" href="specialPageAction.action?specialPageId=12">Vastunna Meekosam</a></p>
</div>-->
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
</script>