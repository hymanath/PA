<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link href="dist/activity/css/bootstrap.min.css" rel="stylesheet"/>
<script src="dist/activity/js/bootstrap.min.js" type="text/javascript"></script> 

</head>
<body>
<div id="statusDiv"></div>

<script>
function showUploadStatus()
	{
		var str = '';
		regex = new RegExp('^' + status + '$', 'i');
		
		if(regex.test('SUCCESS'))
		{
			str+= '<div class="container m_top10 text-center" id="yourElement">';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Thank You For Your Registration</p>';
			str+= '<h3 class="text-align"> Successfully Registration Completed </h3>';
			str+= '</div>';
			str+= '<div class="span12  show-grid" style="position: relative;">';
			str+= '<p class="text-align">Your Membership No : '+membershipNo+' </p>';
			str+= '<p class="text-align">Your Enrollment No : '+entitlementNo+' </p>';			
			str+= '</div>';
			str+= '</div>';
			str+= '<div class="container m_top10" id="yourElement">';
			str+= '<div class="span12 show-grid text-center" style="position: relative;">';
			str+= '<a href="affiliatedCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
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
			str+= '<a href="affiliatedCadreSearchAction.action" class="btn btn-success  offset5 border-radius-0"  >Continue  <span class="glyphicon glyphicon-chevron-right"></span></a>';
			str+= '</div>';
			str+= '</div>';
		}
		$('#statusDiv').html(str);
	}
</script>
<script>	
var membershipNo = '${membershipNo}';
var entitlementNo = '${entitlementNo}';
var status = '${status}';
        showUploadStatus();

</script>	
</body>
</html>
   
    