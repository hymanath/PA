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
<div style="background:#FFFFFF;">
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
</div>
<table width="100%">
<tr>
<td width="50%">
<div id="videoGallaryDiv" style="background:#FFF;margin-bottom:5px;margin-top:5px;">
	<div  style="width: 220px; margin-left: 9px; padding-top: 10px;"><h1 class="VGheadingStyle">Video gallaries Of TDP Party <span style=" position: relative;
    bottom: -14px;right: 69px;"><img alt="" src="images/candidatePage/or-down-arrow.png"></span></h1></div>
	<div style="overflow-x: hidden; margin-left: 10px;margin-right: 10px; border: 1px solid #d3d3d3;">
		<MARQUEE WIDTH=100% BEHAVIOR=SCROLL direction="left">
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
						<td><a title="Click here to View Jr NTR Election Campaign Video Gallery" onclick="getVideoDetails(2544)" href="javascript:{}">
						<font color="red">Jr NTR Election <br> Campaign</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Jr NTR Election Campaign Video Gallery" onclick="getVideoDetails(2544)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/FV0kyasqwzs/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Chandrababu Naidu Election Campaign In Kadapa By Election Video Gallery" onclick="getVideoDetails(2550)" href="javascript:{}">
						<font color="red">Chandrababu Naidu <br> Election Campaign In  <br>Kadapa By Election</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Chandrababu Naidu Election Campaign In Kadapa By Election Video Gallery" onclick="getVideoDetails(2550)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/04zgATSNJe0/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Chandrababu Naidu Rythu Poru Yatra Video Gallery" onclick="getVideoDetails(2556)" href="javascript:{}">
						<font color="red">Chandrababu Naidu <br> Rythu Poru Yatra</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Chandrababu Naidu Rythu Poru Yatra Video Gallery" onclick="getVideoDetails(2556)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/8t5ku0ASW0k/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View TDP 30th Anniversary Video Gallery" onclick="getVideoDetails(4992)" href="javascript:{}">
						<font color="red">TDP 30th Anniversary</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View TDP 30th Anniversary Video Gallery" onclick="getVideoDetails(4992)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/wTq1prJOz9M/1.jpg">
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
						<td><a title="Click here to View Mahanadu 2011 Video Gallery" onclick="getVideoDetails(1582)" href="javascript:{}">
						<font color="red">Mahanadu 2011</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Mahanadu 2011 Video Gallery" onclick="getVideoDetails(1582)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/Na3HiqI1Zts/1.jpg">
						</a></td>
					</tr>
				</table>
			</td>
			<td>
				<table>
					<tr>
						<td><a title="Click here to View Mahanadu 2010 Video Gallery" onclick="getVideoDetails(1584)" href="javascript:{}">
						<font color="red">Mahanadu 2010</font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Mahanadu 2010 Video Gallery" onclick="getVideoDetails(1584)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/Cn1Ke8Qpglo/1.jpg">
						</a></td>
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
						<td><a title="Click here to View Rythu Porubata in Anantapur Video Gallery" onclick="getVideoDetails(1663)" href="javascript:{}">
						<font color="red">Rythu Porubata <br> in Anantapur </font>
						</a></td>
					</tr>
					<tr>
						<td><a title="Click here to View Rythu Porubata in Anantapur Video Gallery" onclick="getVideoDetails(1663)" href="javascript:{}">
						<img src="http://img.youtube.com/vi/69YEkvEfuFA/1.jpg">
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
<td width="30%">
<div id="analyzeConstituency" style="background:#FFFFFF;height:215px;">
 <div style="width: 219px; padding-top: 5px; margin-left: 9px;"><h1 class="VGheadingStyle">Assess Your TDP Party Leader<span style=" position: relative;
    bottom: -14px;right: 69px;font-family: verdana;"><img alt="" src="images/candidatePage/or-down-arrow.png"></span></h1></div>
	<div style=" border: 1px solid #d3d3d3;margin-left: 10px;margin-right: 10px;">
<div id="analyzeConstituencyContentDiv" class="problemPostingContentDivClass">
<div style="padding-left: 15px; padding-right: 15px;margin-top: 8px; text-align: justify; line-height: 1.4em;">Assess your TDP Party Leader and post your reasons for winning/loosing in 2009 .</div>
</div>
<div id="errorDivEle" style="margin-top: 0px; margin-bottom: 4px; margin-left: 189px;color:red;font-weight:bold;"></div>
<input type="radio" name="electionTypeRadio" id="parliamentId" checked="true" style="padding-left: 0px; margin-left: 60px; margin-bottom: 13px;" value="17" onclick="ShowHideForParliament()" />
<span style="font-weight:bold;">Parliament</span>&nbsp;&nbsp;
<input type="radio" name="electionTypeRadio" id="assemblyId" value="38" onclick="ShowHideForAssembly()"/>
<span style="font-weight:bold;">Assembly</span>
<!-- parliament candidates -->
<div style="margin-left: 60px;">
<select style="width:200px;display:block;margin-top: 3px;" id="parliamentCandidateId">
<option value="0">Select Candidate</option>
<option value="30238">A P JITHENDER REDDY</option>
<option value="30373">APPALANAIDU KONDAPALLI</option>
<option value="30550">B T NAIDU</option>
<option value="30182">BHEEMSEN  T</option>
<option value="30322">DOMMATI SAMBAIAH</option>
<option value="30382">DR V V S MURTHY MATHUKAMILLI</option>
<option value="30420">GEDELA VARALAKSHMI</option>
<option value="30560">KALAVA SRINIVASULU</option>
<option value="30460">KONAKALLA NARAYANA RAO</option>
<option value="30571">KRISTAPPA NIMMALA</option>
<option value="30430">M MURALI MOHAN</option>
<option value="30489">MADALA RAJENDRA</option>
<option value="30522">MADDULURI MALAKONDAIAHYADAV</option>
<option value="30451">MAGANTI VENKATESWARA RAO</option>
<option value="30512">MALYADRI SRIRAM</option>
<option value="30498">MODUGULA VENU GOPALA REDDY</option>
<option value="30345">NAMA NAGESWARA RAO</option>
<option value="30634">NARAMALLI SIVAPRASAD</option>
<option value="30532">NASYAM MOHAMMED FAROOK</option>
<option value="30394">NOOKARAPU SURYA PRAKASA RAO</option>
<option value="30581">PALEM SRIKANTH REDDY</option>
<option value="30621">RAMESH KUMAR REDDY REDDAPPAGARI</option>
<option value="6887">RATHOD RAMESH</option>
<option value="30199">SRINIVASA SUDHISH RAMBHOTLA</option>
<option value="30441">THOTA SITA RAMA LAKSHMI</option>
<option value="30471">VAMSI MOHAN VALLABHANENI</option>
<option value="30600">VANTERU VENU GOPALA REDDY</option>
<option value="30611">VARLA RAMAIAH</option>
<option value="30404">VASAMSETTY SATYA</option>
<option value="7209">YERRANNAIDU KINJARAPU</option>
<option value="30219">ZAHID ALI KHAN</option>
</select>
</div>
<div style="margin-left: 60px; margin-top: 6px;">
<!-- assembly candidates -->

<select style="width:200px;display:none;" id="assemblyCandidateId">
<option value="0">Select Candidate</option>
<option value="1295">ABBAIAH  VOOKE</option>
<option value="1854">AITHABATHULA  ANANDARAO</option>
<option value="2888">AJAY BABU  NANDAVARAM BENJIMAN</option>
<option value="741">ALI BIN IBRAHIM MASQATI</option>
<option value="1079">ALIMINETI  UMA MADHAVA REDDY</option>
<option value="3412">AMARANATHA REDDY N</option>
<option value="2201">AMBATI  BRAHMANAIAH</option>
<option value="2075">AMBIKA KRISHNA</option>
<option value="2393">ANAGANI  SATYA PRASAD</option>
<option value="2381">ANANDA BABU  NAKKA</option>
<option value="1274">ANASUYA  DANSARI</option>
<option value="3480">ANIPI REDDY  VENKATA PRAVEEN KUMAR REDDY</option>
<option value="3615">ANJANEYA RAJU  N R</option>
<option value="99">ANNAPURNA  ALETI</option>
<option value="816">ANUMULA  REVANTH REDDY</option>
<option value="1425">APPALA SURYANARAYANA GUNDA</option>
<option value="1522">ARUNA PADALA</option>
<option value="1540">ASHOK GAJAPATHIRAJU PUSANATI</option>
<option value="1411">ATCHANNAIDU  KINJARAPU</option>
<option value="1704">AYYANNA PATRUDU CHINTAKAYALA</option>
<option value="3233">B  K  PARTHA SARATHI</option>
<option value="3111">B V MOHAN REDDY</option>
<option value="1450">BAGGU  LAKSHMANA RAO</option>
<option value="1676">BANDARU  SATYANARAYANA MURTHY</option>
<option value="2045">BAPIRAJU  MULLAPUDI</option>
<option value="1862">BATTULA  RAMU</option>
<option value="2757">BEEDA  MASTHAN RAO</option>
<option value="1589">BHARANIKANA JAYA</option>
<option value="1482">BOBBILI  CHIRANJEEVULU</option>
<option value="1777">BODDU  BHASKARA RAMARAO</option>
<option value="1998">BOMMIDI  NARAYANA RAO</option>
<option value="2979">BUDDA  RAJASEKHAR REDDY</option>
<option value="1958">BURUGUPALLI  SESHA RAO</option>
<option value="3023">BYREDDY  RAJASEKHARA REDDY</option>
<option value="2226">CHALASANI  VENKATESWARA RAO</option>
<option value="1926">CHANDANA  RAMESH</option>
<option value="1024">CHANDAR RAO  VANEPALLI</option>
<option value="2813">CHANDRA MOHANA REDDY  SOMIREDDY</option>
<option value="1985">CHAVATAPALLI  SATYANARAYANA MURTY (DR BABJI)</option>
<option value="1694">CHENGALA VENKATARAO</option>
<option value="2851">CHENNAIAH  LAKKINENI</option>
<option value="2989">CHIMME  BITCHANNA</option>
<option value="1712">CHINNAM  BABU RAMESH</option>
<option value="2130">CHINNAM  RAMAKOTAIAH</option>
<option value="373">CHINTA  PRABHAKER</option>
<option value="965">CHINTALAPALLY  JAGADEESWAR RAO</option>
<option value="229">CHINTHAKUNTA VIJAYA RAMANA RAO</option>
<option value="2428">CHIRALA  GOVARDHANA REDDY</option>
<option value="2455">CHUKKAPALLI  RAMESH</option>
<option value="1664">DADI VEERABHADRA RAO</option>
<option value="2145">DASARI VENKATA BALAVARDHANA RAO</option>
<option value="2576">DAVID RAJU  PALAPARTHI</option>
<option value="2366">DHULIPALLA NARENDRA KUMAR</option>
<option value="2682">DR  DIVI SIVA RAM</option>
<option value="934">DR NAGAM JANARDHAN REDDY</option>
<option value="915">DR PRASANNA KUMAR R</option>
<option value="2824">DURGA PRASAD RAO BALLI</option>
<option value="2657">EDARA  HARI BABU</option>
<option value="2967">ERIGELA  RAMPULLAREDDY</option>
<option value="1160">ERRABELLI DAYAKAR RAO</option>
<option value="956">G JAIPAL YADAV</option>
<option value="724">G S  BUGGA RAO</option>
<option value="808">G SAYANNA</option>
<option value="2268">GADDE  RAMA MOHAN</option>
<option value="5407">GADDE BABU RAO</option>
<option value="2010">GADIRAJU  SATYANARAYANA RAJU (GADIRAJU BABU)</option>
<option value="3470">GALI MUDDUKRISHNAMA NAIDU</option>
<option value="143">GAMPA  GOVERDHAN</option>
<option value="3391">GANDHI</option>
<option value="1578">GANESH KUMAR  VASUPALLI</option>
<option value="3528">GANGULA KAMALAKAR</option>
<option value="2056">GANNI  LAXMI KANTAM</option>
<option value="1635">GAVIREDDY  RAMANAIDU</option>
<option value="71">GODAM NAGESH</option>
<option value="2525">GONUGUNTLA  VENKATA SEETA RAMA ANJANEYULU</option>
<option value="6165">GOPALA KRISHNA REDDY BOJJALA</option>
<option value="1467">GOPALA RAO NIMMAKA</option>
<option value="1917">GORANTALA BUCHAIAH CHOWDARY</option>
<option value="2606">GOTTIPATI  NARASIMHA RAO</option>
<option value="1403">GOWTHU  SYAMASUNDARASIVAJI</option>
<option value="1603">GUDIVADA  NAGAMANI</option>
<option value="1496">GUMMIDI  SANDHYA RANI</option>
<option value="3381">H  HEMALATHA</option>
<option value="116">HANMANTH  SHINDE</option>
<option value="3298">IMTIYAZ AHMED  SHAIK</option>
<option value="2628">JANJANAM  SRINIVASA RAO</option>
<option value="2170">JAYAMANGALA  VENKATA RAMANA</option>
<option value="1890">JOGESWARA RAO V</option>
<option value="57">JOGU RAMANNA</option>
<option value="2554">JULAKANTI  BRAHMANANDA REDDY</option>
<option value="1938">JYOTHULA NAGA VEERA VENKATA VISHNU SATYA MARTHANDARAO</option>
<option value="3215">K  ERANNA</option>
<option value="3129">K  MEENAKSHI NAIDU</option>
<option value="565">K  S  RATNAM</option>
<option value="3349">K  SANKARA REDDY</option>
<option value="874">K DAYAKAR REDDY</option>
<option value="3070">K E KRISHNA MURTHY</option>
<option value="666">K VIJAYA RAMA RAO</option>
<option value="1150">KADIYAM  SREEHARI</option>
<option value="2181">KAGITA  VENKATA RAO</option>
<option value="1417">KALAMATA  VENKATARAMANA MURTHY</option>
<option value="1626">KALIDINDI  SURYA NAGA SANYASI RAJU</option>
<option value="3085">KAMBALAPADU  EDIGA PRABHAKAR</option>
<option value="3583">KAMBHAM  VIJAYARAMI REDDY</option>
<option value="3281">KANDIKUNTA  VENKATA PRASAD</option>
<option value="2441">KANDUKURI  VEERAIAH</option>
<option value="2709">KANDULA  NARAYANA REDDY</option>
<option value="2875">KANDULA  SIVANANDA REDDY</option>
<option value="2616">KARANAM BALARAMA KRISHNA MURTHY</option>
<option value="2103">KARRA  RAJA RAO</option>
<option value="1970">KARRI  RADHA KRISHNA REDDY</option>
<option value="5547">KARRI ADILAKSHMI</option>
<option value="1459">KAVALI  PRATHIBHA BHARATHI</option>
<option value="2157">KODALI  SRI VENKATESWARA RAO (NANI)</option>
<option value="2502">KODELA SIVA PRASADA RAO</option>
<option value="1550">KOLLA  LALITHA KUMARI</option>
<option value="2191">KOLLU  RAVINDRA</option>
<option value="2328">KOMMALAPATI  SRIDHAR</option>
<option value="575">KOPPULA HARISHWAR REDDY</option>
<option value="901">KRISHNA MOHAN REDDY  BANDLA</option>
<option value="1435">KUNA  RAVI KUMAR</option>
<option value="2841">KURUGONDLA  RAMA KRISHNA</option>
<option value="191">L  RAMANA</option>
<option value="3625">L LALITHA KUMARI</option>
<option value="2768">LAKSHMAIAH NAIDU  KOMMI</option>
<option value="1686">LALAM  BHASKARA RAO</option>
<option value="406">LASMANNAGARI  PRATHAP REDDY</option>
<option value="2727">LINGA REDDY  CHEGIREDDY</option>
<option value="2938">LINGAA REDDY  MALLELA</option>
<option value="3099">M  MANIGANDHI</option>
<option value="849">M CHANDRA SHEKAR</option>
<option value="3324">M VENKATA RAMANA RAJU</option>
<option value="2863">MADAN MOHAN REDDY  K</option>
<option value="3435">MAHALAKSHMI SREENIVASULU</option>
<option value="477">MANCHIREDDY  KISHAN REDDY</option>
<option value="5044">MANDAVA VENKATESHWARA RAO</option>
<option value="2591">MANNAM  VENKATA RAMANA RAO</option>
<option value="636">MD  MUZAFFAR ALI KHAN</option>
<option value="3463">METTU  GOVINDA REDDY</option>
<option value="682">MOHAMMED  SALEEM</option>
<option value="1106">MOTHUKUPALLI NARSIMHULU</option>
<option value="548">MOVVA  SATYANARAYANA</option>
<option value="3555">MYNAMPALLI  HANUMANTH RAO</option>
<option value="3569">N  SAPANADEV</option>
<option value="3040">N H BHASKAR REDDY</option>
<option value="415">NAKKA  PRABHAKAR GOUD</option>
<option value="1791">NALLAMILLI MOOLA REDDY</option>
<option value="2778">NALLAPAREDDY  PRASANNA KUMAR REDDY</option>
<option value="3424">NARA CHANDRABABU NAIDU</option>
<option value="1530">NARAYANASWAMY NAIDU  PATHIVADA</option>
<option value="1442">NAYANA  SURYANARAYANA REDDI</option>
<option value="2515">NIMMAKAYALA  RAJANARAYANA</option>
<option value="712">OSMAN BIN  MOHAMMED AL HAJRI</option>
<option value="3220">P  ABDUL GHANI</option>
<option value="343">P  BABUMOHAN</option>
<option value="3403">P  BALAJI</option>
<option value="948">P RAMULU</option>
<option value="3244">PALLE  RAGHUNATHA REDDY</option>
<option value="3175">PAMIDI  SAMANTHAKAMANI</option>
<option value="6152">PARASA VENKATA RATNAIAH</option>
<option value="3194">PARITALA  SUNEETHAMMA</option>
<option value="1732">PARVATHA  SRI SATYANARAYANA MURTHY</option>
<option value="599">PATNAM MAHENDER REDDY</option>
<option value="3140">PAYYAVULA KESHAV</option>
<option value="1906">PENDURTHI  VENKATESH</option>
<option value="3160">PERAM  NAGI REDDY</option>
<option value="1397">PIRIYA SAIRAJ</option>
<option value="123">POCHARAM  SRINIVAS REDDY (PARIGE)</option>
<option value="2064">PRABHAKAR  CHINTAMANENI</option>
<option value="2293">PRABHAKARA RAO  TANGIRALA</option>
<option value="1191">PRAKASH REDDY  REVURI</option>
<option value="2486">PRATHIPATI  PULLA RAO</option>
<option value="1872">PULAPARTHI  NARAYANAMURTHY</option>
<option value="2093">PUNEM  SINGANNA DORA</option>
<option value="2909">PUTTA  NARASIMHA REDDY</option>
<option value="3309">R  KRISHNA SAGAR REDDY</option>
<option value="3336">R K ROJA</option>
<option value="2952">RAGHURAMI REDDY  SETTIPALLE</option>
<option value="2309">RAJA GOPAL SREERAM</option>
<option value="2410">RAJENDRA PRASAD  ALAPATI</option>
<option value="2924">RAMA SUBBA REDDY  PONNAPUREDDY</option>
<option value="1562">RAMAKRISHNA BABU  VELAGAPUDI</option>
<option value="255">RAMESH CHENNAMANENI</option>
<option value="888">RAVULA  CHANDDRA SHEKAR REDDY</option>
<option value="1881">REDDY  SUBRAHMANYAM</option>
<option value="2473">S  M  ZIAUDDIN</option>
<option value="489">S  V  KRISHNA PRASAD</option>
<option value="3153">SAINATH GOUD  RAMAGOWNI</option>
<option value="1355">SANDRA  VENKATA VEERAIAH</option>
<option value="1764">SATYANARAYANA MURTY  PILLI</option>
<option value="1172">SATYAVATHI RATHOD</option>
<option value="860">SEETHA  DAYAKAR REDDY</option>
<option value="3598">SHARADA MAHESH  V</option>
<option value="1644">SIVERI  SOMA</option>
<option value="2342">SRAVAN KUMAR  TENALI</option>
<option value="2694">SREE BALA VEERANJANEYA SWAMI  DOLA</option>
<option value="1837">SRINIVASA RAJU  NADIMPALLI</option>
<option value="241">SUDDALA DEVAIAH</option>
<option value="3494">SUGAVASI PALAKONDRAYUDU</option>
<option value="47">SUMAN RATHOD</option>
<option value="2115">SWAMYDAS  NALLAGATLA</option>
<option value="526">T  PRAKASH GOUD</option>
<option value="509">T KRISNA REDDY</option>
<option value="1946">T V RAMA RAO</option>
<option value="6665">TALASANI SRINIVAS YADAV</option>
<option value="2791">TALLAPAKA  RAMESH REDDY</option>
<option value="2082">TANETI  VANITA</option>
<option value="979">TERA  CHINNAPA REDDY</option>
<option value="1503">THENTU  LAKSHMUNAIDU</option>
<option value="1309">THUMMALA  NAGESWARA RAO</option>
<option value="2280">UMA MAHESWARA RAO DEVINENI</option>
<option value="3187">UNNAM  HANUMANTHARAYA CHOWDARY</option>
<option value="2213">UPPULETI  KALPANA</option>
<option value="2023">V V SIVA RAMA RAJU (KALAVAPUDI SIVA)</option>
<option value="1811">VANAMADI  VENKATESWARARAO (KONDABABU)</option>
<option value="2899">VENKATA SATISH KUMAR REDDY  SINGAREDDY</option>
<option value="87">VENUGOPALA CHARY  S</option>
<option value="1749">VERMA S V S N</option>
<option value="3121">Y  BALANAGI REDDY</option>
<option value="359">Y  NAROTHAM</option>
<option value="2034">Y T RAJA</option>
<option value="3880">YANAMALA RAMAKRISHNUDU</option>
<option value="2538">YARAPATINENI SRINIVASA RAO</option>
<option value="825">YELKOTI  YELLA REDDY</option>
<option value="3055">YERRABOTHULA  VENKATA REDDY</option>
</select>
</div>

	<div style="margin-top: 20px; margin-left: 26px; margin-bottom: 11px;" 		id="analyzeConstituencyButtonDiv">
		<a href="javascript:{}" style="background:#5CB275;color: #FFFFFF;font-weight: bold;margin-left: 20px;padding: 3px 14px;text-decoration:none;border-radius:4px;" onclick="getCandidateDetailsForAsses('analyze');">Assess</a>
		<a href="javascript:{}" style="background:#5CB275;color: #FFFFFF;font-weight: bold;margin-left: 20px;padding: 3px 14px;text-decoration:none;border-radius:4px;" onclick="getCandidateDetailsForAsses('viewResults')">Previous Posts</a>
	</div>
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

function getCandidateDetailsForAsses(selectType)
{
	var electionId = null;
	var candidateId = null;
	
	if(document.getElementById("parliamentId").checked == true)
	{
		var pcEle = document.getElementById("parliamentCandidateId");
		candidateId = pcEle.options[pcEle.selectedIndex].value;
		electionId = document.getElementById("parliamentId").value;
	}

	else if(document.getElementById("assemblyId").checked == true)
	{	
		var acEle = document.getElementById("assemblyCandidateId");
		candidateId = acEle.options[acEle.selectedIndex].value;
		electionId = document.getElementById("assemblyId").value;
	}

	if(candidateId == 0)
	{
		document.getElementById("errorDivEle").innerHTML = 'Please Select Candidate.';
		return;
	}
	else
	{
		document.getElementById("errorDivEle").innerHTML = '';
	}

	var jsObj =
		{   
			selectType  : selectType,
		    candidateId : candidateId,
			electionId : electionId,
			task:"getCandidateDetailsForAsses"
		};
	
	var rparam ="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getCandidateDetailsForAssesAction.action?"+rparam;			callAjaxForTDPPartyPage(jsObj,url); 
	
}

function callAjaxForTDPPartyPage(jsObj,url)
{
	
	var callback = {			
	 success : function( o ) {
		try
		{ 
			myResults = YAHOO.lang.JSON.parse(o.responseText);
			if(jsObj.task == "getCandidateDetailsForAsses")
			{
				openAnalyzeConstituencyWindow(myResults,jsObj.selectType);
			}
		}
		
		catch(e)
		{   
		 alert("Invalid JSON result" + e);   
		}  
	 },
	scope : this,
	failure : function( o )
	{}
  };

 YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function ShowHideForParliament()
{
	document.getElementById("assemblyCandidateId").style.display = 'none';
	document.getElementById("parliamentCandidateId").style.display = 'block';
	document.getElementById('parliamentCandidateId').selectedIndex = 0;
	document.getElementById('assemblyCandidateId').selectedIndex = 0;


}

function ShowHideForAssembly()
{
	document.getElementById("parliamentCandidateId").style.display = 'none';
	document.getElementById("assemblyCandidateId").style.display = 'block';
	document.getElementById('parliamentCandidateId').selectedIndex = 0;
	document.getElementById('assemblyCandidateId').selectedIndex = 0;
}

function openAnalyzeConstituencyWindow(result,type)
{
	var candidateId = result.candidateId;
	var constituencyId = result.constituencyId;
	var constituencyName = result.constituencyName;
	var userId = result.districtId;
	var userType = result.districtName;
	var parliamentConstiId = result.partyId;
	var parliamentConstiName = result.partyName;
	
	var taskType = type;


	if(userId == null && taskType == 'analyze')
	{
		alert("Please Login To Post Comment");
		return;
	}
	
	if(taskType == 'viewResults')
	{
		var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType+"&candidateId="+candidateId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
		browser1.focus();
	}

	if(userType == "FREE_USER" && taskType == 'analyze')
	{
		var browser1 = window.open("analyzeConstituencyPopupAction.action?redirectLoc=assessCandidatePopUp&constituencyId="+constituencyId+"&parliamentConstiId="+parliamentConstiId+"&parliamentConstiName="+parliamentConstiName+"&constituencyName="+constituencyName+"&userId="+userId+"&taskType="+taskType+"&candidateId="+candidateId,"analyzeConstituencyPopup","scrollbars=yes,height=800,width=700,left=200,top=200");				 
		browser1.focus();
	}
	
	if(userType != "FREE_USER" && taskType == 'analyze')
	{
		alert("Comment For Free User Only");
	}

}

</script>