<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<style type="text/css">
.main-bbg
	{
		background:none;
	}
.fontStyle
{
	background: #ED5B21;
	color: #FFF;
	font-weight: bold;
	padding: 5px 8px;
    border-radius: 3px 3px 3px 3px;
	font-family: verdana;
}
#meyyanForthSpanId,#meyyanPoliticalAspirationSpanId
{
	cursor:pointer;
}
.ui-widget-header {
	font-weight:normal;
}
.politicalCarrerTable{font:13px Arial, Helvetica, sans-serif;}
.politicalCarrerTable td{border:1px solid #d3d3d3;padding:5px 3px;color:#3d3d3d;height:35px;}
.politicalCarrerTable td {text-align:left;font-weight:bold}
.politicalCarrerTable th:nth-child(2){background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(3){background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(4){background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(5){background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(6) {background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(7) {background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(8) {background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(9) {background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(10) {background:#21B2ED;color:#fff;}
.politicalCarrerTable th:nth-child(11) {background:#21B2ED;color:#fff;}
.politicalCarrerTable th{border:1px solid #d3d3d3;padding:10px 5px;}
.politicalCarrerTable tr:nth-child(odd){background:#f3f3f3;}
.politicalCarrerTable th:nth-child(1){color:#000;}
#meyyanForthOuterDiv ul li ,#meyyappanPoliAspirationOuterDivDiv ul li
{
	margin-bottom: 4px;
	font-size: 13px;
}
</style>
<script type="text/javascript">

function showMeyyappanForte()
{
 $.fx.speeds._default = 900;
	$("#meyyanForthOuterDiv").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 'auto',
								width:600,
								position:[130,130],	
								modal: true,
								title:'<font color="Navy"><b>C D MEYYAPPAN FORTE</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#meyyanForthOuterDiv").dialog();
	
	
	 
	 var str=$('#meyyanForthInnerDiv').html();
	 $("#meyyanForthOuterDiv").html(str);
	 
	 }

function showMeyyappanPoliticalAps()
{
	$.fx.speeds._default = 900;
	$("#meyyanForthOuterDiv").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 'auto',
								width:600,
								position:[130,130],	
								modal: true,
								title:'<font color="Navy"><b>C D MEYYAPPAN Political Aspiration & Promise</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#meyyanForthOuterDiv").dialog();
	
	
	 
	 var str=$('#meyyanForthInnerDiv').html();
	 $("#meyyanForthOuterDiv").html(str);
	 
}

</script>

<div id="cdMeyyanCenterMainDiv">

	<div style="margin-top: 20px; margin-bottom: 16px;">
	<span class="fontStyle">Political Career</span>
	</div>
	<div id="politicalCarrerDiv" style="margin-bottom: 25px;">
			  <table class="politicalCarrerTable" border="1" style="border-collapse:collapse;">
				 <tr>
				  <th style="background:#21B2ED;color:#FFF;">Year</th>
				  <th>Position</th>
				  </tr>
				  <tr>
					<td>1978</td>
					<td>Joined NSUI</td>
				  </tr>
				  <tr>
					<td>1984-1986</td>
					<td>General Secretary, South Arcot District Youth Congress</td>
				  </tr>
				  <tr>
					<td>1986 to 1989</td>
					<td>Executive Committee Member, Tamil Nadu Youth Congress</td>
				  </tr>
				  <tr>
					<td>1989</td>
					<td>Member, Chidambaram Parliamentary Constituency committee</td>
				  </tr>
				  <tr>
					<td>1989 to 1997</td>
					<td>General Secretary, Tamil Nadu Youth Congress</td>
				  </tr>
				  <tr>
					<td>1997 to 2003</td>
					<td>President, Tamil Nadu Youth Congress</td>
				  </tr>
				  <tr>
					<td>1997 to Till Date</td>
					<td>Member, All India Congress Committee</td>
				  </tr>
				  <tr>
					<td>2003 to 2009</td>
					<td>Secretary, Tamil Nadu Congress Committee</td>
				  </tr>
			  </table>
		</div>

		
	<div id="meyyaForthDiv" style="margin-bottom: 25px;">
		<span class="fontStyle" style="background:#ED5B21;margin-left: 13px;" id="meyyanForthSpanId" title="Click Here to view the Meyyappan Forte" onclick="showMeyyappanForte()">His Forte</span>
		<span class="fontStyle" style="background:#ED5B21;margin-left: 20px;" id="meyyanPoliticalAspirationSpanId" onclick="showMeyyappanPoliticalAps()" title="Click Here to view the Meyyappan Political Aspiration & Promise">His Political Aspiration & Promise</span>
	<div id="meyyanForthOuterDiv"></div>
		<div id="meyyanForthInnerDiv" style="display:none">
		<ul style="margin-top: 13px; margin-left: 8px;">
	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Constant interactions with party workers at the grassroot level & organizing such workers up to the state level.</li>
	</span>
	<span class="meyyForthSpanStyle">
	   <li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Organising regular meetings of party workers on matters of topical interest impinging on Local, State & National Issues.
	  </li>
    </span>
	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Organising Membership Enrolment Camps as a regular method of strengthening the party at the grass root level.</li>
	</span>
	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Organising of Booth Level Committees.</li>
	</span>

	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Crisis Management and diffusion of such Crisis.</li>
	</span>
	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Use of modern tools of Communication.</li>
	</span>

	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Adaptability - with all ranks of the party.</li>
	</span>
	<span>
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Availability to all, at all times.</li>
	</span>
	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Maintaining highest level of Integrity & Political Ethics transcending factional compulsions.</li>
	</span>
	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Loyalty to the Party throughout my political career.</li>
	</span>
	<span class="meyyForthSpanStyle">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Group Leanings – Strictly None – It is party first, every time and always.</li>
	</span>
	</ul>
	</div>
	</div>
	
<div id="meyyappanPoliAspirationOuterDivDiv"></div>
	<div id="meyyappanPoliAspirationDiv" style="display:none;">
	<ul style="margin-top: 13px; margin-left: 8px; margin-bottom: 10px;">
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;The privilege accorded to me by the party by encouraging, nurturing and supporting me has enabled me to rise literally from the grass roots. </li>
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;I led the Pradesh Youth Congress as its longest serving president. </li>
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;As youth Congress President, a singular achievement was the bringing forth of a high level of visibility to the movement all over the State of Tamil Nadu. </li>
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;The over three decades of my involvement in the party and the rare opportunity to serve the party, have enabled me to have a firm finger on pulse of the grass root worker. </li>
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;This has enabled me to have a first hand image of the socio-political happenings and the imperative to uplift the Aam Aadmi of our beloved Nation. </li>
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;This insight has made me hungry to shoulder some responsibility in the Organisation, in any capacity deemed fit by the High Command.</li>
		<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Any such responsibility, I promise would fuel my aspirations and desire of working tirelessly towards the upliftment of the downtrodden and the underprivileged Aam Aadmi of our beloved Nation. </li>
		
	</ul>
	</div>
</div>