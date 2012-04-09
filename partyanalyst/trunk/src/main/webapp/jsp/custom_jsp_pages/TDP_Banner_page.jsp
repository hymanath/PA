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
<td>
<div id="videoGallaryDiv" style="background:#FFF;margin-bottom:5px;margin-top:5px;">
	<div  style="width: 220px; margin-left: 9px; padding-top: 10px;"><h1 class="VGheadingStyle">Video gallaries Of TDP Party <span style=" position: relative;
    bottom: -14px;right: 69px;"><img alt="" src="images/candidatePage/or-down-arrow.png"></span></h1></div>
	<div style="overflow-x: hidden; margin-left: 10px;margin-right: 10px; border: 1px solid #d3d3d3;">
		<MARQUEE WIDTH="900px" BEHAVIOR="SCROLL" direction="left">
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
						<font color="red">Chandrababu Naidu <br> Election Campaign In Kadapa By Election</font>
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
</tr>
<tr>
<td>
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
<option value="30238">A P Jithender Reddy</option>
<option value="30373">Appalanaidu Kondapalli</option>
<option value="30550">B T Naidu</option>
<option value="30182">Bheemsen  T</option>
<Option Value="30322">Dommati Sambaiah</Option>
<Option Value="30382">Dr V V S Murthy Mathukamilli</Option>
<Option Value="30420">Gedela Varalakshmi</Option>
<Option Value="30560">Kalava Srinivasulu</Option>
<Option Value="30460">Konakalla Narayana Rao</Option>
<Option Value="30571">Kristappa Nimmala</Option>
<Option Value="30430">M Murali Mohan</Option>
<Option Value="30489">Madala Rajendra</Option>
<Option Value="30522">Madduluri Malakondaiahyadav</Option>
<Option Value="30451">Maganti Venkateswara Rao</Option>
<Option Value="30512">Malyadri Sriram</Option>
<Option Value="30498">Modugula Venu Gopala Reddy</Option>
<Option Value="30345">Nama Nageswara Rao</Option>
<Option Value="30634">Naramalli Sivaprasad</Option>
<Option Value="30532">Nasyam Mohammed Farook</Option>
<Option Value="30394">Nookarapu Surya Prakasa Rao</Option>
<Option Value="30581">Palem Srikanth Reddy</Option>
<Option Value="30621">Ramesh Kumar Reddy Reddappagari</Option>
<Option Value="6887">Rathod Ramesh</Option>
<Option Value="30199">Srinivasa Sudhish Rambhotla</Option>
<Option Value="30441">Thota Sita Rama Lakshmi</Option>
<Option Value="30471">Vamsi Mohan Vallabhaneni</Option>
<Option Value="30600">Vanteru Venu Gopala Reddy</Option>
<Option Value="30611">Varla Ramaiah</Option>
<Option Value="30404">Vasamsetty Satya</Option>
<Option Value="7209">Yerrannaidu Kinjarapu</Option>
<Option Value="30219">Zahid Ali Khan</Option>
</Select>
</div>
<div style="margin-left: 60px; margin-top: 6px;">
<!-- assembly candidates -->

<select style="width:200px;display:none;" id="assemblyCandidateId">
<option value="0">Select Candidate</option>
<Option Value="1295">Abbaiah  Vooke</Option>
<Option Value="1854">Aithabathula  Anandarao</Option>
<Option Value="2888">Ajay Babu  Nandavaram Benjiman</Option>
<Option Value="741">Ali Bin Ibrahim Masqati</Option>
<Option Value="1079">Alimineti  Uma Madhava Reddy</Option>
<Option Value="3412">Amaranatha Reddy N</Option>
<Option Value="2201">Ambati  Brahmanaiah</Option>
<Option Value="2075">Ambika Krishna</Option>
<Option Value="2393">Anagani  Satya Prasad</Option>
<Option Value="2381">Ananda Babu  Nakka</Option>
<Option Value="1274">Anasuya  Dansari</Option>
<Option Value="3480">Anipi Reddy  Venkata Praveen Kumar Reddy</Option>
<Option Value="3615">Anjaneya Raju  N R</Option>
<Option Value="99">Annapurna  Aleti</Option>
<Option Value="816">Anumula  Revanth Reddy</Option>
<Option Value="1425">Appala Suryanarayana Gunda</Option>
<Option Value="1522">Aruna Padala</Option>
<Option Value="1540">Ashok Gajapathiraju Pusanati</Option>
<Option Value="1411">Atchannaidu  Kinjarapu</Option>
<Option Value="1704">Ayyanna Patrudu Chintakayala</Option>
<Option Value="3233">B  K  Partha Sarathi</Option>
<Option Value="3111">B V Mohan Reddy</Option>
<Option Value="1450">Baggu  Lakshmana Rao</Option>
<Option Value="1676">Bandaru  Satyanarayana Murthy</Option>
<Option Value="2045">Bapiraju  Mullapudi</Option>
<Option Value="1862">Battula  Ramu</Option>
<Option Value="2757">Beeda  Masthan Rao</Option>
<Option Value="1589">Bharanikana Jaya</Option>
<Option Value="1482">Bobbili  Chiranjeevulu</Option>
<Option Value="1777">Boddu  Bhaskara Ramarao</Option>
<Option Value="1998">Bommidi  Narayana Rao</Option>
<Option Value="2979">Budda  Rajasekhar Reddy</Option>
<Option Value="1958">Burugupalli  Sesha Rao</Option>
<Option Value="3023">Byreddy  Rajasekhara Reddy</Option>
<Option Value="2226">Chalasani  Venkateswara Rao</Option>
<Option Value="1926">Chandana  Ramesh</Option>
<Option Value="1024">Chandar Rao  Vanepalli</Option>
<Option Value="2813">Chandra Mohana Reddy  Somireddy</Option>
<Option Value="1985">Chavatapalli  Satyanarayana Murty (Dr Babji)</Option>
<Option Value="1694">Chengala Venkatarao</Option>
<Option Value="2851">Chennaiah  Lakkineni</Option>
<Option Value="2989">Chimme  Bitchanna</Option>
<Option Value="1712">Chinnam  Babu Ramesh</Option>
<Option Value="2130">Chinnam  Ramakotaiah</Option>
<Option Value="373">Chinta  Prabhaker</Option>
<Option Value="965">Chintalapally  Jagadeeswar Rao</Option>
<Option Value="229">Chinthakunta Vijaya Ramana Rao</Option>
<Option Value="2428">Chirala  Govardhana Reddy</Option>
<Option Value="2455">Chukkapalli  Ramesh</Option>
<Option Value="1664">Dadi Veerabhadra Rao</Option>
<Option Value="2145">Dasari Venkata Balavardhana Rao</Option>
<Option Value="2576">David Raju  Palaparthi</Option>
<Option Value="2366">Dhulipalla Narendra Kumar</Option>
<Option Value="2682">Dr  Divi Siva Ram</Option>
<Option Value="934">Dr Nagam Janardhan Reddy</Option>
<Option Value="915">Dr Prasanna Kumar R</Option>
<Option Value="2824">Durga Prasad Rao Balli</Option>
<Option Value="2657">Edara  Hari Babu</Option>
<Option Value="2967">Erigela  Rampullareddy</Option>
<Option Value="1160">Errabelli Dayakar Rao</Option>
<Option Value="956">G Jaipal Yadav</Option>
<Option Value="724">G S  Bugga Rao</Option>
<Option Value="808">G Sayanna</Option>
<Option Value="2268">Gadde  Rama Mohan</Option>
<Option Value="5407">Gadde Babu Rao</Option>
<Option Value="2010">Gadiraju  Satyanarayana Raju (Gadiraju Babu)</Option>
<Option Value="3470">Gali Muddukrishnama Naidu</Option>
<Option Value="143">Gampa  Goverdhan</Option>
<Option Value="3391">Gandhi</Option>
<Option Value="1578">Ganesh Kumar  Vasupalli</Option>
<Option Value="3528">Gangula Kamalakar</Option>
<Option Value="2056">Ganni  Laxmi Kantam</Option>
<Option Value="1635">Gavireddy  Ramanaidu</Option>
<Option Value="71">Godam Nagesh</Option>
<Option Value="2525">Gonuguntla  Venkata Seeta Rama Anjaneyulu</Option>
<Option Value="6165">Gopala Krishna Reddy Bojjala</Option>
<Option Value="1467">Gopala Rao Nimmaka</Option>
<Option Value="1917">Gorantala Buchaiah Chowdary</Option>
<Option Value="2606">Gottipati  Narasimha Rao</Option>
<Option Value="1403">Gowthu  Syamasundarasivaji</Option>
<Option Value="1603">Gudivada  Nagamani</Option>
<Option Value="1496">Gummidi  Sandhya Rani</Option>
<Option Value="3381">H  Hemalatha</Option>
<Option Value="116">Hanmanth  Shinde</Option>
<Option Value="3298">Imtiyaz Ahmed  Shaik</Option>
<Option Value="2628">Janjanam  Srinivasa Rao</Option>
<Option Value="2170">Jayamangala  Venkata Ramana</Option>
<Option Value="1890">Jogeswara Rao V</Option>
<Option Value="57">Jogu Ramanna</Option>
<Option Value="2554">Julakanti  Brahmananda Reddy</Option>
<Option Value="1938">Jyothula Naga Veera Venkata Vishnu Satya Marthandarao</Option>
<Option Value="3215">K  Eranna</Option>
<Option Value="3129">K  Meenakshi Naidu</Option>
<Option Value="565">K  S  Ratnam</Option>
<Option Value="3349">K  Sankara Reddy</Option>
<Option Value="874">K Dayakar Reddy</Option>
<Option Value="3070">K E Krishna Murthy</Option>
<Option Value="666">K Vijaya Rama Rao</Option>
<Option Value="1150">Kadiyam  Sreehari</Option>
<Option Value="2181">Kagita  Venkata Rao</Option>
<Option Value="1417">Kalamata  Venkataramana Murthy</Option>
<Option Value="1626">Kalidindi  Surya Naga Sanyasi Raju</Option>
<Option Value="3085">Kambalapadu  Ediga Prabhakar</Option>
<Option Value="3583">Kambham  Vijayarami Reddy</Option>
<Option Value="3281">Kandikunta  Venkata Prasad</Option>
<Option Value="2441">Kandukuri  Veeraiah</Option>
<Option Value="2709">Kandula  Narayana Reddy</Option>
<Option Value="2875">Kandula  Sivananda Reddy</Option>
<Option Value="2616">Karanam Balarama Krishna Murthy</Option>
<Option Value="2103">Karra  Raja Rao</Option>
<Option Value="1970">Karri  Radha Krishna Reddy</Option>
<Option Value="5547">Karri Adilakshmi</Option>
<Option Value="1459">Kavali  Prathibha Bharathi</Option>
<Option Value="2157">Kodali  Sri Venkateswara Rao (Nani)</Option>
<Option Value="2502">Kodela Siva Prasada Rao</Option>
<Option Value="1550">Kolla  Lalitha Kumari</Option>
<Option Value="2191">Kollu  Ravindra</Option>
<Option Value="2328">Kommalapati  Sridhar</Option>
<Option Value="575">Koppula Harishwar Reddy</Option>
<Option Value="901">Krishna Mohan Reddy  Bandla</Option>
<Option Value="1435">Kuna  Ravi Kumar</Option>
<Option Value="2841">Kurugondla  Rama Krishna</Option>
<Option Value="191">L  Ramana</Option>
<Option Value="3625">L Lalitha Kumari</Option>
<Option Value="2768">Lakshmaiah Naidu  Kommi</Option>
<Option Value="1686">Lalam  Bhaskara Rao</Option>
<Option Value="406">Lasmannagari  Prathap Reddy</Option>
<Option Value="2727">Linga Reddy  Chegireddy</Option>
<Option Value="2938">Lingaa Reddy  Mallela</Option>
<Option Value="3099">M  Manigandhi</Option>
<Option Value="849">M Chandra Shekar</Option>
<Option Value="3324">M Venkata Ramana Raju</Option>
<Option Value="2863">Madan Mohan Reddy  K</Option>
<Option Value="3435">Mahalakshmi Sreenivasulu</Option>
<Option Value="477">Manchireddy  Kishan Reddy</Option>
<Option Value="5044">Mandava Venkateshwara Rao</Option>
<Option Value="2591">Mannam  Venkata Ramana Rao</Option>
<Option Value="636">Md  Muzaffar Ali Khan</Option>
<Option Value="3463">Mettu  Govinda Reddy</Option>
<Option Value="682">Mohammed  Saleem</Option>
<Option Value="1106">Mothukupalli Narsimhulu</Option>
<Option Value="548">Movva  Satyanarayana</Option>
<Option Value="3555">Mynampalli  Hanumanth Rao</Option>
<Option Value="3569">N  Sapanadev</Option>
<Option Value="3040">N H Bhaskar Reddy</Option>
<Option Value="415">Nakka  Prabhakar Goud</Option>
<Option Value="1791">Nallamilli Moola Reddy</Option>
<Option Value="2778">Nallapareddy  Prasanna Kumar Reddy</Option>
<Option Value="3424">Nara Chandrababu Naidu</Option>
<Option Value="1530">Narayanaswamy Naidu  Pathivada</Option>
<Option Value="1442">Nayana  Suryanarayana Reddi</Option>
<Option Value="2515">Nimmakayala  Rajanarayana</Option>
<Option Value="712">Osman Bin  Mohammed Al Hajri</Option>
<Option Value="3220">P  Abdul Ghani</Option>
<Option Value="343">P  Babumohan</Option>
<Option Value="3403">P  Balaji</Option>
<Option Value="948">P Ramulu</Option>
<Option Value="3244">Palle  Raghunatha Reddy</Option>
<Option Value="3175">Pamidi  Samanthakamani</Option>
<Option Value="6152">Parasa Venkata Ratnaiah</Option>
<Option Value="3194">Paritala  Suneethamma</Option>
<Option Value="1732">Parvatha  Sri Satyanarayana Murthy</Option>
<Option Value="599">Patnam Mahender Reddy</Option>
<Option Value="3140">Payyavula Keshav</Option>
<Option Value="1906">Pendurthi  Venkatesh</Option>
<Option Value="3160">Peram  Nagi Reddy</Option>
<Option Value="1397">Piriya Sairaj</Option>
<Option Value="123">Pocharam  Srinivas Reddy (Parige)</Option>
<Option Value="2064">Prabhakar  Chintamaneni</Option>
<Option Value="2293">Prabhakara Rao  Tangirala</Option>
<Option Value="1191">Prakash Reddy  Revuri</Option>
<Option Value="2486">Prathipati  Pulla Rao</Option>
<Option Value="1872">Pulaparthi  Narayanamurthy</Option>
<Option Value="2093">Punem  Singanna Dora</Option>
<Option Value="2909">Putta  Narasimha Reddy</Option>
<Option Value="3309">R  Krishna Sagar Reddy</Option>
<Option Value="3336">R K Roja</Option>
<Option Value="2952">Raghurami Reddy  Settipalle</Option>
<Option Value="2309">Raja Gopal Sreeram</Option>
<Option Value="2410">Rajendra Prasad  Alapati</Option>
<Option Value="2924">Rama Subba Reddy  Ponnapureddy</Option>
<Option Value="1562">Ramakrishna Babu  Velagapudi</Option>
<Option Value="255">Ramesh Chennamaneni</Option>
<Option Value="888">Ravula  Chanddra Shekar Reddy</Option>
<Option Value="1881">Reddy  Subrahmanyam</Option>
<Option Value="2473">S  M  Ziauddin</Option>
<Option Value="489">S  V  Krishna Prasad</Option>
<Option Value="3153">Sainath Goud  Ramagowni</Option>
<Option Value="1355">Sandra  Venkata Veeraiah</Option>
<Option Value="1764">Satyanarayana Murty  Pilli</Option>
<Option Value="1172">Satyavathi Rathod</Option>
<Option Value="860">Seetha  Dayakar Reddy</Option>
<Option Value="3598">Sharada Mahesh  V</Option>
<Option Value="1644">Siveri  Soma</Option>
<Option Value="2342">Sravan Kumar  Tenali</Option>
<Option Value="2694">Sree Bala Veeranjaneya Swami  Dola</Option>
<Option Value="1837">Srinivasa Raju  Nadimpalli</Option>
<Option Value="241">Suddala Devaiah</Option>
<Option Value="3494">Sugavasi Palakondrayudu</Option>
<Option Value="47">Suman Rathod</Option>
<Option Value="2115">Swamydas  Nallagatla</Option>
<Option Value="526">T  Prakash Goud</Option>
<Option Value="509">T Krisna Reddy</Option>
<Option Value="1946">T V Rama Rao</Option>
<Option Value="6665">Talasani Srinivas Yadav</Option>
<Option Value="2791">Tallapaka  Ramesh Reddy</Option>
<Option Value="2082">Taneti  Vanita</Option>
<Option Value="979">Tera  Chinnapa Reddy</Option>
<Option Value="1503">Thentu  Lakshmunaidu</Option>
<Option Value="1309">Thummala  Nageswara Rao</Option>
<Option Value="2280">Uma Maheswara Rao Devineni</Option>
<Option Value="3187">Unnam  Hanumantharaya Chowdary</Option>
<Option Value="2213">Uppuleti  Kalpana</Option>
<Option Value="2023">V V Siva Rama Raju (Kalavapudi Siva)</Option>
<Option Value="1811">Vanamadi  Venkateswararao (Kondababu)</Option>
<Option Value="2899">Venkata Satish Kumar Reddy  Singareddy</Option>
<Option Value="87">Venugopala Chary  S</Option>
<Option Value="1749">Verma S V S N</Option>
<Option Value="3121">Y  Balanagi Reddy</Option>
<Option Value="359">Y  Narotham</Option>
<Option Value="2034">Y T Raja</Option>
<Option Value="3880">Yanamala Ramakrishnudu</Option>
<Option Value="2538">Yarapatineni Srinivasa Rao</Option>
<Option Value="825">Yelkoti  Yella Reddy</Option>
<Option Value="3055">Yerrabothula  Venkata Reddy</Option>
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