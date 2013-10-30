<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="js/videoGallary/videolightbox.js" ></script>
<link rel="stylesheet" type="text/css" href="styles/videoGallary/overlay-minimal.css"/>
<style type="text/css">
.headingStyle{
	background:#9966CC;
	font-weight:bold;
	color:#FFFFFF;
    padding: 2px 8px;
    font-size: 15px;
    border-radius: 3px;
    }
	.VGheadingStyle{
	 background-color: #ED5B21;
    border-radius: 2px 2px 2px 2px;
    color: #FFFFFF;
    font: bold 11px/24px "Trebuchet MS",Arial,Helvetica,sans-serif;
    height: 24px;
    margin-bottom: 13px;
    padding: 0 15px;
    position: relative;
    text-align: center;
    text-transform: uppercase;
	}

.resulttableclass a{text-decoration:none;}
.resulttableclass a:hover{text-decoration:underline;}
.resulttableclass{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.resulttableclass td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;height:35px;}
.resulttableclass th:nth-child(1){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(2){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(2) a{color:#fff;}
.resulttableclass th:nth-child(3){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(3) a{color:#fff;}
.resulttableclass td a{color:#000;}
.resulttableclass th:nth-child(4){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(4) a{color:#fff;}
.resulttableclass th:nth-child(5){background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(5) a{color:#fff;}
.resulttableclass th:nth-child(6) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(6) a{color:#fff;}
.resulttableclass th:nth-child(7) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(7) a{color:#fff;}
.resulttableclass th:nth-child(8) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(8) a{color:#fff;}
.resulttableclass th:nth-child(9) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(9) a{color:#fff;}
.resulttableclass th:nth-child(10) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(10) a{color:#fff;}
.resulttableclass th:nth-child(11) {background:#21B2ED;color:#fff;}
.resulttableclass th:nth-child(11) a{color:#fff;}

.resulttableclass th{border:1px solid #d3d3d3;width:15%;padding:10px 5px;}
.resulttableclass tr:nth-child(odd){background:#f3f3f3;}
.resulttableclass th:nth-child(1){color:#000;}
.resulttableclass th:nth-child(1) a{color:#000;}
.resulttableclass td {text-align:center;font-weight:bold}

</style>
<div id="mainDiv" style="margin-left:auto;margin-right:auto;float:none;width: 999px;">
<div style="background:#ffffff;padding-bottom: 13px;padding-left: 11px;">
	<img src="images/specialPage/TDP_30_YearsBanner.png" style="align:center;width:977px;" alt="TDP 30th Anniversary" title="TDP 30th Anniversary" />
	</div>
	<!--<div style="background:#FFFFFF;">
	<table style="width:100%">
	<tr>
	<td style="width:50%">
	<div style="margin-top: -51px;"><span class="headingStyle">TDP Party Performance in Parliament Election</span>
	</div>
	<div id="parliamentDiv" style="margin-top: 12px;">
	<table class="resulttableclass" border="1" style="border-collapse:collapse;border-color:#d3d3d3;" width="100%" valign="top">
	 <tr>
		<th style="color:#FFFFFF;">Year</th>
		<th>Total Seats</th>
		<th>TP*</th>
		<th>Won</th>
		<th>Voting % </th>
		<th>	
		Overall %</th>
		<th>PC %</th>
		</tr>
	<tr>
		<td>2009</td>
		<td>543</td>
		<td>31</td>
		<td>6</td>
		<td>58.46</td>
		<td>2.5</td>
		<td>33.55</td>
	</tr>
	<tr>
		<td>2004</td>
		<td>543</td>
		<td>33</td>
		<td>5</td>
		<td>58.09</td>
		<td>3.04</td>
		<td>42.83</td>
	</tr>
	<tr>
		<td>1999</td>
		<td>543</td>
		<td>34</td>
		<td>29</td>
		<td>59.99</td>
		<td>50</td>
		<td>3.65</td>
	</tr>
	<tr>
		<td>1998</td>
		<td>543</td>
		<td>34</td>
		<td>29</td>
		<td>59.99</td>
		<td>50</td>
		<td>3.65</td>
	</tr>
	<tr>
		<td>1996</td>
		<td>543</td>
		<td>36</td>
		<td>16</td>
		<td>57.94</td>
		<td>2.97</td>
		<td>38.24</td>
	</tr>
	<tr>
		<td>1991</td>
		<td>521</td>
		<td>35</td>
		<td>13</td>
		<td>56.73</td>
		<td>2.94</td>
		<td>38.56</td>
	</tr>
	<tr>
		<td>1989</td>
		<td>529</td>
		<td>33</td>
		<td>2</td>
		<td>61.95</td>
		<td>3.29</td>
		<td>43.58</td>
	</tr>
	<tr>
		<td>1984</td>
		<td>514</td>
		<td>34</td>
		<td>30</td>
		<td>63.61</td>
		<td>4.31</td>
		<td>51.79</td>
	</tr>
</table>
</div>
</td>
<td></td>
<td style="width:50%">
<div><span class="headingStyle">TDP Party Performance in Assembly Election</span></div>
<div id="assemblyDiv" style="margin-top: 12px;">
<table class="resulttableclass" border="1" style="border-collapse:collapse;border-color:#d3d3d3;" width="100%" valign="top">
	<tr>
		<th style="color:#FFFFFF;">Year</th>
		<th>Total Seats</th>
		<th>TP*</th>
		<th>Won</th>
		<th>Voting % </th>
		<th>Overall %</th>
		<th>PC %</th>
	</tr>
	<tr>
		<td>TDP 2009</td>
		<td>294</td>
		<td>225</td>
		<td>92</td>
		<td>72.69</td>
		<td>28.12</td>
		<td>36.31</td>
	</tr>
	<tr>
		<td>MahaKutami 2009</td>
		<td>294</td>
		<td></td>
		<td>107</td>
		<td>72.69</td>
		<td>34.77</td>
		<td>36.31</td>
	</tr>
	<tr>
		<td>TDP 2004</td>
		<td>294</td>
		<td>267</td>
		<td>47</td>
		<td>69.96</td>
		<td>37.59</td>
		<td>41.24</td>
		
	</tr>
	<tr>
		<td>TDP-BJP 2004</td>
		<td>294</td>
		<td></td>
		<td>49</td>
		<td>69.96</td>
		<td>40.22</td>
		<td>41.24</td>
	</tr>
	<tr>
		<td>1999</td>
		<td>294</td>
		<td>269</td>
		<td>180</td>
		<td>69.15</td>
		<td>43.85</td>
		<td>48.15</td>
	</tr>
	<tr>
		<td>1994</td>
		<td>294</td>
		<td>251</td>
		<td>216</td>
		<td>71.02</td>
		<td>44.14</td>
		<td>52.07</td>
	</tr>
	<tr>
		<td>1989</td>
		<td>294</td>
		<td>241</td>
		<td>74</td>
		<td>70.44</td>
		<td>36.54</td>
		<td>44.49</td>
	</tr>
	<tr>
		<td>1985</td>
		<td>294</td>
		<td>250</td>
		<td>202</td>
		<td>67.57</td>
		<td>46.21</td>
		<td>54.46</td>
	</tr>
	<tr>
		<td>1983</td>
		<td>294</td>
		<td>263</td>
		<td>218</td>
		<td>67.7</td>
		<td>46.14</td>
		<td>51.51</td>
	</tr>
</table>
</div>
</td>
</tr>
</table>
	<div>
		<table>
			<tr>
				<td>TP* =Total Participation</td>
			</tr>
			<tr>
				<td>PC* %=Participated Constituencies Percentage</td>
			</tr>
		</table>
	</div>
</div>-->

<table width="999px">
<tr>
<td width="500px">
<div id="videoGallaryDiv" style="background:#FFF;margin-bottom:5px;margin-top:5px;">
	<div  style="width: 220px; margin-left: 9px; padding-top: 10px;"><h1 class="VGheadingStyle">Video gallaries Of TDP Party <span style=" position: relative;
    bottom: -14px;right: 69px;"><img alt="" src="images/candidatePage/or-down-arrow.png"></span></h1></div>
	<div style="overflow-x: hidden; margin-left: 10px;margin-right: 10px; border: 1px solid #d3d3d3;">
		<MARQUEE WIDTH="920px" BEHAVIOR="SCROLL" direction="left">
			<table>
			<tr>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View N T Rama Rao Speeches Video Gallery" onclick="getVideoDetails(2534)" href="javascript:{}">
						<font color="red">N T Rama Rao <br> Speeches</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View N T Rama Rao Speeches Video Gallery" onclick="getVideoDetails(2534)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/Xcdzou_IR4Q/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Rythu Poru Video Gallery" onclick="getVideoDetails(1579)" href="javascript:{}">
						<font color="red">Rythu Poru</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Rythu Poru Video Gallery" onclick="getVideoDetails(1579)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/Qa3wAeJCQ-g/1.jpg">
						</a>
						</td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Mahanadu 2009 Video Gallery" onclick="getVideoDetails(1587)" href="javascript:{}">
						<font color="red">Mahanadu 2009</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Mahanadu 2009 Video Gallery" onclick="getVideoDetails(1587)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/1rnQ08e6Hp4/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Rythu Poru - Ranga Reddy Dist Video Gallery" onclick="getVideoDetails(1644)" href="javascript:{}">
						<font color="red">Rythu Poru - Ranga <br> Reddy Dist</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Rythu Poru - Ranga Reddy Dist Video Gallery" onclick="getVideoDetails(1644)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/Hjn66Z0lgVU/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Rythu Porubata in Khammam Video Gallery" onclick="getVideoDetails(1651)" href="javascript:{}">
						<font color="red">Rythu Porubata in <br> Khammam</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Rythu Porubata in Khammam Video Gallery" onclick="getVideoDetails(1651)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/lYVSi6_c6V4/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Rythu Porubata in Prakasam Video Gallery" onclick="getVideoDetails(1657)" href="javascript:{}">
						<font color="red">Rythu Porubata <br> in Prakasam</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Rythu Porubata in Prakasam Video Gallery" onclick="getVideoDetails(1657)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/7OEFvX9h958/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Rythu Poru - Visakhapatnam Dist Video Gallery" onclick="getVideoDetails(1927)" href="javascript:{}">
						<font color="red">Rythu Poru - <br> Visakhapatnam Dist</font>
						</a>
						</td>
					</tr>
					<tr>
						<td><a title="Click here to View Rythu Poru - Visakhapatnam Dist Video Gallery" onclick="getVideoDetails(1927)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/sx_SHnxwHyc/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			
			</tr>
			</table> 
		</marquee>
		</div>
	
</div>
</td>

</tr>
</table>

</div>

<script>
function getVideoDetails(contentId)
{
	$.fx.speeds._default = 1000;
	  $("#showContentDiv").dialog({ stack: false,
								height: 'auto',
								width: 950,
								closeOnEscape: true,
								position:[30,30],
								show: "blind",
								hide: "explode",
								modal: true,
								maxWidth : 950,
								minHeight: 650,
								overlay: { opacity: 0.5, background: 'black'}
								});
		$("#showContentDiv").dialog();
		getContentDetails(contentId);
}


function callAjaxForTDPPartyPage(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			
		}
		
		catch(e)
		{   
		 //alert("Invalid JSON result" + e);   
		}  
	 },
	scope : this,
	failure : function( o )
	{}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}




</script>