<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="dist/activity/css/bootstrap.min.css" rel="stylesheet"/>
<script src="dist/activity/js/bootstrap.min.js" type="text/javascript"></script> 
 <link href="css/style.css" rel="stylesheet"/>
<style type="text/css">
.eventsheader , .footerCls,.line_heightDiv
{
	display:none;
}
</style> 
</head>
<body>
<div id="statusDiv"></div>

<script>
var membershipNo = '${membershipNo}';
var enrollMentNO = '${enrollMentNO}';
var status = '${status}';


$(document).ready(function(){
	$("#wrapper").hide();
	$("#trigger").hide();
	$("#loginId").hide();
	$('.eventsheader,.footerCls,.line_heightDiv').addClass('hide');
	showUploadStatus();
});

		
function startSearchingPage(){

	//window.location.href="http://telugudesam.org/emembership/teluguNaduGraduatesRegistration.html";
	//window.location.href="http://www.telugudesam.org/cadre-registration-portal/registration";
	  window.location = "https://mytdp.com/cadreOnlineRegistrationAction.action";
}
function showUploadStatus()
	{
		var str = '';
		regex = new RegExp('^' + status + '$', 'i');
		
		if(regex.test('SUCCESS'))
		{
			str+= '<div class="container m_top10 text-center" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<h3 class="text-align">CADRE REGISTRATION</h3>';
			str+= '</div>';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align"> Successfully Registration Completed </h3>';
			str+= '</div>';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Your Membership No : '+membershipNo+' </p>';
			str+= '<p class="text-align">Your Enrollment No : '+enrollMentNO+' </p>';			
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12 show-grid text-center" style="position: relative;">';
			str+= '<a href="javascript:{startSearchingPage();}" class="btn btn-success  offset5 border-radius-0"  >Another Registration <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		else if(regex.test('error'))
		{
			str+= '<div class="container m_top10 text-center" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<h3 class="text-align">Error raised while cadre registration</h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12 show-grid text-center" style="position: relative;">';
			str+= '<a href="javascript:{startSearchingPage();}" class="btn btn-success  offset5 border-radius-0"  >New Registration <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		else if(regex.test('failure'))
		{
			str+= '<div class="container m_top10 text-center" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<h3 class="text-align"> Your Payment Transaction is failed </h3>';
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12 show-grid text-center" style="position: relative;">';
			str+= '<a href="javascript:{startSearchingPage();}" class="btn btn-success  offset5 border-radius-0"  > New Registration <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		$('#statusDiv').html(str);
	}
</script>

</body>
</html>
   
    