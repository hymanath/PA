<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<body>
<div class="selectHeading" style="width:187px;">
	<span class="selectDivStyle">View your Constituencies</span>
	<span style="margin-left: 4px;-moz-border-radius:4px;">Know About Your Constituency</span>
	

<select class="selectBoxWidth" id="Apconstituency" name="constituency">
 <option value="0">Select Constituency</option>
 <option value="1">Adilabad</option>
 <option value="298">Ananthapur</option>
 <option value="254">Allagadda</option>
 <option value="16">Kamareddy</option>
 <option value="67">Kollapur</option>
 <option value="233">Kovur</option>
 <option value="68">Mahaboobnagar</option>
 <option value="205">Macherla</option>
 <option value="112">Narasannapeta </option>
 <option value="173">Narsapuram</option>
 <option value="70">Nagarkurnool</option>
 <option value="227">Ongole</option>
 <option value="140">Payakaraopet (SC)</option>
 <option value="176">Polavaram</option>
 <option value="212">Prathipadu (SC)</option>
 <option value="94">Parkal</option>
 <option value="159">Ramachandrapuram</option>
 <option value="246">Railway Kodur (SC)</option>
 <option value="252">Rajampet</option>
 <option value="248">Rayachoti</option>
 <option value="276">Rayadurg</option>
 <option value="87">Station Ghanpur</option>
 <option value="291">Tirupathi</option>
 <option value="238">Udayagiri </option>
 <option value="265">Yemmiganuru</option>
 </select>
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