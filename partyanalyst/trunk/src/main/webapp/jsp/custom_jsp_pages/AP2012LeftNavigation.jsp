<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<body>
<div class="selectHeading" style="width:187px;">
	<span class="selectDivStyle">View your Constituencies</span>
	<span style="margin-left: 4px;-moz-border-radius:4px;">Know About Your Constituency</span>
	

<select class="selectBoxWidth" id="Apconstituency" name="constituency">
 <option value="0">Select Constituency</option><option value="1">Adilabad</option><option value="16">Kamareddy</option><option value="87">Station Ghanpur</option><option value="67">Kollapur</option><option value="68">Mahaboobnagar</option><option value="233">Kovur</option><option value="70">Nagarkurnool</option></select>
<div id="alertMessage"></div>
<div class="view-results"><a onclick="naviToConstituencyPage()" href="javascript:{}">view results</a></div>

</div>
<script type="text/javascript">
function naviToConstituencyPage()
{
	var errotMsg ="Please Select Constituency"
	var constSelectEl = document.getElementById("Apconstituency");
	var alertEl = document.getElementById("alertMessage");
	var constSelectElVal = constSelectEl.options[constSelectEl.selectedIndex].value
	alertEl.innerHTML = '';
	if(constSelectElVal == 0)
	{
		alertEl.innerHTML = errotMsg;
		return;
	}
	window.location = "constituencyPageAction.action?constituencyId="+constSelectElVal;
	
}
</script>