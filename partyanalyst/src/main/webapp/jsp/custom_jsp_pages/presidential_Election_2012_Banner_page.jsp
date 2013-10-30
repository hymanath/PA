<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<style>

.resulttableclass tr:nth-child(odd){background:#f3f3f3;}
.resulttableclass tr:nth-child(even){background:#e5e5e5;}
.resulttableclass{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.resulttableclass td{border:1px solid #d3d3d3;color:#3d3d3d;padding-top:2px;width:265px;}



.electionResultsTable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.electionResultsTable td{border:1px solid #d3d3d3;padding:5px 3px;color:#3d3d3d;height:35px;}
.electionResultsTable th:nth-child(2){background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(2) a{color:#fff;}
.electionResultsTable th:nth-child(3){background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(3) a{color:#fff;}

.electionResultsTable th:nth-child(4){background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(4) a{color:#fff;}
.electionResultsTable th:nth-child(5){background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(5) a{color:#fff;}
.electionResultsTable th:nth-child(6) {background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(6) a{color:#fff;}
.electionResultsTable th:nth-child(7) {background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(7) a{color:#fff;}
.electionResultsTable th:nth-child(8) {background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(8) a{color:#fff;}
.electionResultsTable th:nth-child(9) {background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(9) a{color:#fff;}
.electionResultsTable th:nth-child(10) {background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(10) a{color:#fff;}
.electionResultsTable th:nth-child(11) {background:#21B2ED;color:#fff;}
.electionResultsTable th:nth-child(11) a{color:#fff;}

.electionResultsTable th{border:1px solid #d3d3d3;padding:10px 5px;}
.electionResultsTable tr:nth-child(odd){background:#f3f3f3;}
.electionResultsTable th:nth-child(1){color:#000;}
.electionResultsTable th:nth-child(1) a{color:#000;}
.electionResultsTable td {text-align:left;font-weight:bold}
.winTD{background-color : GreenYellow;}

.resulth3{border-bottom:2px solid #999999;margin-top:0px;background:#ED5B21;color:#fff;padding:2px;width:550px; -moz-border-radius:2px;margin-left:3px; padding-left: 10px;}

 #electionResultsDiv
 {
background: #FFF;
width: 987px;
border: 1px solid #5E5E5E;
border-top: none;
margin-top: -5px;
margin-bottom: 0px;
padding-bottom: 38px;
 }
 
 .table-striped tbody tr:nth-child(2n+1) td, .table-striped tbody tr:nth-child(2n+1) th{background:#F3F3F3;}
 .table thead th,.table-bordered td {text-align:center;}

 #votesShareSpan{background:#ED5B21;border-radius: 5px; color: #FFF; font-family: verdana; font-size: 13px; font-weight: bold; padding: 5px;}
 #votesShareSpanul li{padding:6px;}
 #impUnder li {
  font-family:verdana;
  margin-left:40px;
  padding:6px;
}
 
 .table-bordered th, .table-bordered td {
    border-left: 1px solid #DDDDDD;
    text-align: center;
    width: 50px;
}
 
#electionResultsMLAWise a{text-decoration:none;color:#3d3d3d;}
</style>
</head>

<div style="width:80%;">
<img src="images/specialPage/Presidential_Elections_2012_banner.png" style="align:center;width:973px;">
</div>

<div style="width:987px;background-color:#FFFFFF;border:1px solid #5e5e5e;margin-top:10px;border-radius:2px 2px 2px 2px;margin-bottom:3px;">

<div class="row">
	
	<table><tr>
	<td valign="top"><div class="span4" style="margin-left:35px;margin-top:0px;margin-right:70px;"><img src="images/specialPage/pranab-mukherjee.jpg" width="295" height="295" style="margin-bottom:40px;margin-top:20px;"></img>
	</div></td>

	<td valign="top"><div class="span8" style="margin-left:0px;margin-top:10px;">
		<h2 style="margin-left:46px;;margin-bottom:10px;">Congratulations to Pranab Mukherjee, for becoming First Citizen of India</h2>

		<div class="span8" style="margin-left:0px;">
			<ul id="impUnder">
				<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Pranab Mukherjee sworn in as the 13th President of India on 25th July 2012.</li>
				<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;He Acquired  69.3 % votes in 14 th Indian Presidential Elections.</li>
			</ul>

			<ul id="impUnder">
				<li><img src="images/icons/diamond.png" /><span>&nbsp;&nbsp;Total Polled Votes - 4659</li>
				<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Valid Votes 	   - 4578</li>
				<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Invalid Votes - 81</li>
				<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Pranab Acquired Votes - 3095(Value of Votes-713763)</li>
				<li><img src="images/icons/diamond.png" />&nbsp;&nbsp;Sangma Acquired Votes- 1483(Value of Votes-315987)</li>
			</ul>
		</div>

	</div>
	</td></tr></table>
</div>

<div class="row" style="margin-left:0px;">
<div class="span6" style="float:left; margin-top:-35px;margin-right:55px;margin-left:358px;">
<span id="votesShareSpan" style="background:#21B2ED;cursor:pointer;margin-top:20px;" onClick="getPresidents();" title="Click here to view Former Indian Presidents & their duration">Former Indian Presidents & their duration</span>
</div></div>

<div class="span12" 
style="margin-bottom:10px;margin-left:16px;margin-right:20px;margin-top:20px;">
<span id="votesShareSpan">Highlights of 2012 Presidential Elections</span>
<div style="margin-top:15px;margin-bottom:10px;margin-left:10px;">
<span style="line-height:1.7em;font-family:verdana,arial,sans-serif;font-size:12px;"><img src="images/icons/diamond.png" />&nbsp;&nbsp;Cross-voting in favour of Mr. Mukherjee in the BJP-ruled Karnataka
he got votes of 117 MLAs, against the BJP’s 103 in the 224-member Assembly.
While three votes were declared invalid, one MLA did not vote.</span><br>
<span style="line-height: 1.7em;font-family: verdana,arial,sans-serif;font-size: 12px;"><img src="images/icons/diamond.png" />&nbsp;&nbsp;In Kerala, Mr. Mukherjee made a clean sweep, polling all 124 votes; one was invalid. Mr. Sangma drew a blank. The CPI and RSP MLAs abstained from voting.</span><br>
<span style="line-height: 1.7em;font-family: verdana,arial,sans-serif;font-size: 12px;"><img src="images/icons/diamond.png" />&nbsp;&nbsp;In Nagaland Mr. Sangma draws a blank. Pranab Mukherjee gets all the state legislator's votes.</span><br>
<span style="line-height: 1.7em;font-family: verdana,arial,sans-serif;font-size: 12px;"><img src="images/icons/diamond.png" />&nbsp;&nbsp;Mukherjee got 56 votes in Tripura while Sangma managed only one vote.</span><br>
</div>
</div>

<div class="span12" style="margin:20px;margin-left:16px;margin-top:20px;">
<span id="votesShareSpan">Votes Share in Presidential Election 2012 </span>

<table class="electionResultsTable" width='650' style="margin-top:20px;font-family:sans-serif;">

<thead>
<th colspan='2' style="background:#21B2ED;color:#fff;">MP's</th><th colspan='2'>MLA's</th>
</thead>

<tbody>

<tr style="background:#fff;">
	<td>Total Polled MP Votes</td><td>733</td><td>Total Polled MLA Votes</td><td>3873</td>
<tr>
<tr>
	<td>Value of MP Votes</td><td>518964</td><td>Value of MLA Votes</td><td>502983</td>
</tr>
<tr>
	<td>Pranab Gained(MP Votes)</td><td>527</td><td>Pranab Gained(MLA Votes)</td><td>2601</td>
</tr>
<tr>
	<td>Value of Votes Gained by Pranab</td><td>3,73,116</td><td>Value of Votes Gained by Pranab</td><td>3,34,821</td>
</tr>
<tr>
	<td>Sangma Gained(MP Votes)</td><td>206</td><td>Sangma Gained(MLA Votes)</td><td>1272</td>
</tr>

<tr>
	<td>Value of Votes Gained by Sangma</td><td>1,45,848</td><td>Value of Votes Gained by Sangma</td><td>1,68,162</td>
</tr>

</tbody>

</table>
</div>




<div class="span12" style="margin:20px;margin-left:16px;margin-top:20px;">
<span id="votesShareSpan" style="margin-left:15px;">MLA'S Votes Share in Presidential Election 2012  </span>

<table width="955px" class="electionResultsTable" id="electionResultsMLAWise" style="margin-top:20px;font-family:sans-serif;margin-left:15px;margin-bottom:20px;">
  
  <tr>
	 <th rowspan='2' style="background:#F3F3F3;">State</th>
	 <th colspan='2'>Pranab Mukherjee</th>
	 <th colspan='2'>Sangma</th>
	 <th rowspan='2' style="background:#F3F3F3;color:#3D3D3D;">State</th>
	 <th colspan='2'>Pranab Mukherjee</th>
	 <th colspan='2'>Sangma</th>
  </tr>
  
  <tr>
	<th style="background:#F3F3F3;color:#3D3D3D;">Total</th>
	<th style="background:#F3F3F3;color:#3D3D3D;">Value Of Votes</th>
	<th style="background:#F3F3F3;color:#3D3D3D;">Total</th>
	<th style="background:#F3F3F3;color:#3D3D3D;">Value Of Votes</th>
	<th style="background:#F3F3F3;color:#3D3D3D;">Total</th>
	<th style="background:#F3F3F3;color:#3D3D3D;">Value Of Votes</th>
	<th style="background:#F3F3F3;color:#3D3D3D;">Total</th>
	<th style="background:#F3F3F3;color:#3D3D3D;">Value Of Votes</th>
  </tr>
  </thead>
   
  <tbody>
    <tr>
		<td><a href="statePageAction.action?stateId=1" title="Click here to View Andhra Pradesh State Details, Election Results, Census Details and Ministry Details">Andhrapradesh</a></td>
		<td>182</td>
		<td>26,936</td>
		<td>3</td>
		<td>444</td>
		<td>Arunachal pradesh</td>
		<td>84</td>
		<td>432</td>
		<td>2</td>
		<td>16</td>
    </tr>

	<tr>
	<td><a href="statePageAction.action?stateId=3" title="Click here to View Assam State Details, Election Results, Census Details and Ministry Details" >Assam</a></td>
	<td>110</td>
	<td>12,760</td>
	<td>13</td>
	<td>1508</td>
	<td>Bihar</td>
	<td>146</td>
	<td>25,258</td>
	<td>90</td>
	<td>15,570</td>
	</tr>

	<tr>
	<td>Chatisghad</td>
	<td>39</td>
	<td>5,031</td>
	<td>50</td>
	<td>6,450</td>
	<td>Delhi</td>
	<td>45</td>
	<td>2,610</td>
	<td>23</td>
	<td>1,334</td>
	</tr>
	
	<tr>
	<td><a href="statePageAction.action?stateId=6" title="Click here to View Goa State Details, Election Results, Census Details and Ministry Details">Goa</a></td>
	<td>9</td>
	<td>180</td>
	<td>31</td>
	<td>620</td>
	<td><a href="statePageAction.action?stateId=7" title="Click here to View Gujarat State Details, Election Results, Census Details and Ministry Details" >Gujarat</a></td>
	<td>59</td>
	<td>8,673</td>
	<td>123</td>
	<td>18,081</td>
	</tr>

	<tr><td>Haryana</td> <td>53</td> <td>5,936</td> <td>29</td> <td>3,248</td><td><a href="statePageAction.action?stateId=9" title="Click here to View HimachalPradesh State Details, Election Results, Census Details and Ministry Details" >Himachalpradesh</a></td> <td>23</td> <td>1,173</td> <td>41</td> <td>2,091</td></tr>

	<tr><td>Jammu and kashmir</td> <td>68</td> <td>4,896</td> <td>15</td> <td>1,080</td><td>Jharkand</td> <td>60</td> <td>4,560</td> <td>20</td> <td>1,520</td></tr>

	<tr><td><a href="statePageAction.action?stateId=12" title="Click here to View Karnataka State Details, Election Results, Census Details and Ministry Details">Karnataka</a></td> <td>117</td> <td>15,327</td> <td>103</td> <td>13,493</td> <td><a href="statePageAction.action?stateId=13" title="Click here to View Kerala State Details, Election Results, Census Details and Ministry Details" >Kerala</a></td> <td>124</td> <td>18,848</td> <td>0</td> <td>0</td> </tr>

	<tr><td>Madhyapradesh</td> <td>74</td> <td>9,694</td> <td>149</td> <td>19,519</td> <td>Maharastra</td> <td>225</td> <td>39,375</td> <td>47</td> <td>8,225</td> </tr>

	<tr><td><a href="statePageAction.action?stateId=16" title="Click here to View Manipur State Details, Election Results, Census Details and Ministry Details" >Manipur</a></td> <td>58</td> <td>1,044</td> <td>1</td> <td>18</td> <td>Mizoram</td> <td>32</td> <td>256</td> <td>7</td> <td>56</td> </tr>

	<tr><td>Meghalaya</td> <td>34</td> <td>578</td> <td>23</td> <td>391</td><td>Nagaland</td> <td>58</td> <td>522</td> <td>0</td> <td>0</td> </tr>

	<tr><td>Odisha</td> <td>26</td> <td>3,874</td> <td>115</td> <td>17,135</td><td><a href="statePageAction.action?stateId=21" title="Click here to View Punjab State Details, Election Results, Census Details and Ministry Details">Punjab</a></td> <td>44</td> <td>5,104</td> <td>70</td> <td>8,120</td> </tr>

	<tr><td>Rajastan</td> <td>113</td> <td>14,577</td> <td>85</td> <td>10,965</td> <td>Sikkim</td> <td>28</td> <td>196</td> <td>1</td> <td>7</td> </tr>

	<tr><td><a href="statePageAction.action?stateId=24" title="Click here to View Tamilnadu State Details, Election Results, Census Details and Ministry Details">Tamilanadu</a></td> <td>45</td> <td>7,920</td> <td>149</td> <td>26,224</td> <td>Tripura</td> <td>56</td> <td>1,456</td> <td>1</td> <td>26</td> </tr>

	<tr><td>Uttarakand</td> <td>39</td> <td>2,496</td> <td>30</td> <td>1,920</td> <td><a href="statePageAction.action?stateId=27" title="Click here to View UttarPradesh State Details, Election Results, Census Details and Ministry Details">Uttarpradesh</a></td> <td>352</td> <td>73,216</td> <td>46</td> <td>9,568</td> </tr>

	<tr><td><a href="statePageAction.action?stateId=28" title="Click here to View Westbengal State Details, Election Results, Census Details and Ministry Details" >Westbengal</a></td> <td>275</td> <td>41,525</td> <td>3</td> <td>453</td> <td><a href="statePageAction.action?stateId=35" title="Click here to View Puducherry State Details, Election Results, Census Details and Ministry Details">Puducherry</a></td> <td>23</td> <td>368</td> <td>5</td> <td>80</td> </tr>
	</tbody>
</table>
</div>

   <div id="listOfPresidentsInIndia"></div>
   
   <div style="width:400px;float:right;margin-right:160px;">
   <div style="margin-top:10px;font-family:arial;font-size:13px;width:500px;float:left;">

   <div id="listOfPresidents" style="margin-top:20px;font-family:arial;font-size:13px;display:none;">
	   <table class="table table-striped" >
	   
	   <tr><th style="background:#21B2ED;color:#ffffff;">Name</th><th style="background:#21B2ED;color:#ffffff;">In Office</th></tr>
		<tr><td>Rajendra Prasad</td> <td>26 January 1950 - 13 May 1962</td></tr>
		<tr><td>Sarvepalli Radhakrishnan </td> <td> 13 May 1962 - 13 May 1967</td></tr>
		<tr><td><span style="color:red;" >*</span>Zakir Hussain</td> <td>13 May 1967 - 3 May 1969</td></tr>
		<tr><td>Varahagiri Venkata Giri</td>  <td>24 August 1969 - 24 August 1974  </td></tr>
		<tr><td>Mohammad Hidayatullah </td>   <td>20 July 1969 - 24 August 1969</td></tr>
		<tr><td><span style="color:red;" >*</span>Fakhruddin Ali Ahmed </td>    <td>24 August 1974 - 11 February 1977</td></tr>
		<tr><td>Basappa Danappa Jatti</td>    <td>11 February 1977 - 25 July 1977</td></tr>
		<tr><td>Neelam Sanjiva Reddy </td>    <td>25 July 1977 - 25 July 1982</td></tr>
		<tr><td> Giani Zail Singh </td>       <td>25 July 1982 - 25 July  1987</td></tr>
		<tr><td>Ramaswamy Venkataraman</td>    <td>25 July 1987 - 25 July 1992</td></tr>
		<tr><td>Shankar Dayal Sharma </td>     <td> 25 July 1992 - 25 July 1997</td></tr>
		<tr><td>Kocheril Raman Narayanan</td>  <td> 25 July 1997 - 25 July 2002</td></tr>
		<tr><td>A.P.J. Abdul Kalam </td><td> 25 July 2002 - 25 July 2007</td></tr>
		<tr><td>Pratibha Devi Singh Patil</td>  <td>25 July 2007 - 25 July 2012</td></tr>
		<tr><td>Pranab Mukherjee</td>  <td>25 July 2012 - Till Date</td></tr>
	    </table>
		<span style="color:#000080;"><span style="color:red;" >*</span> Indicates Presidents Died in their Presidential Duration</span>
	   </div>
	   </div>
   </div>
  </div>
<div id="electionResultsDiv">
<span style="background:#ED5B21;border-radius: 5px; color: #FFF; font-family: verdana; font-size: 13px; font-weight: bold; padding: 5px; margin-left: 16px;">Previous Indian Presidential Election Results</span>

<table class="electionResultsTable" style="margin-left: auto; margin-right: auto; float: none; width: 97%;margin-top: 15px;">
<tr>
	<th style="background:#21B2ED;color:#FFF;">S.No</th>
	<th>Date</th>
	<th>Winner</th>
	<th>Votes Gained</th>
	<th>2nd Position</th>
	<th>Votes Gained</th>
	<th>Majority</th>
</tr>
<tr>
	<td>1</td>
	<td>02-05-1952</td>
	<td>Dr. Rajendra Prasad</td>
	<td>5,07,400</td>
	<td>Keti Sha</td>
	<td>92,827</td>
	<td>4,14,573</td>
</tr>
<tr>
	<td>2</td>
	<td>06-05-1957</td>
	<td>Dr. Rajendra Prasad</td>
	<td>4,59,698</td>
	<td>Nagendra Narayan Das</td>
	<td>2,000</td>
	<td>4,57,698</td>
</tr>
<tr>
	<td>3</td>
	<td>07-05-1962</td>
	<td>Dr. Sarvepalli Radhakrishnan </td>
	<td>5,53,067</td>
	<td>Chowdary Hari Ram</td>
	<td>6,341</td>
	<td>5,46,726</td>
</tr>
<tr>
	<td>4</td>
	<td>06-05-1967</td>
	<td>Dr. Zakir Hussain</td>
	<td>4,71,244</td>
	<td>Kota Subba Rao</td>
	<td>3,63,971</td>
	<td>1,07,273</td>
</tr>
<tr>
	<td>5</td>
	<td>16-08-1969</td>
	<td>Varahagiri Venkata Giri</td>
	<td>4,01,515</td>
	<td>Neelam Sanjiva Reddy</td>
	<td>3,13,548</td>
	<td>89,967</td>
</tr>

<tr>
	<td>6</td>
	<td>17-08-1974</td>
	<td>Fakhruddin Ali Ahmed</td>
	<td>7,65,587</td>
	<td>Tridub Chowdhury</td>
	<td>1,89,196</td>
	<td>5,76,391</td>
</tr>
<tr>
	<td>7</td>
	<td>06-08-1977</td>
	<td>Neelam Sanjiva Reddy</td>
	<td>-</td>
	<td>-</td>
	<td>-</td>
	<td>-</td>
</tr>
<tr>
	<td>8</td>
	<td>12-07-1982</td>
	<td>Giani Zail Singh</td>
	<td>7,54,113</td>
	<td>H.R. Khanna</td>
	<td>2,82,685</td>
	<td>4,71,428</td>
</tr>
<tr>
	<td>9</td>
	<td>13-07-1987</td>
	<td>Ramaswamy Venkataraman</td>
	<td>7,40,148</td>
	<td>V.R. Krishna Ayyar</td>
	<td>2,81,550</td>
	<td>4,58,598</td>
</tr>
<tr>
	<td>10</td>
	<td>13-07-1992</td>
	<td>Shankar Dayal Sharma </td>
	<td>6,75,804</td>
	<td>G.G. Swal</td>
	<td>3,46,485</td>
	<td>3,29,319</td>
</tr>
<tr>
	<td>11</td>
	<td>14-07-1997</td>
	<td>Kocheril Raman Narayanan</td>
	<td>9,56,290</td>
	<td>T.N. Shesan</td>
	<td>50,631</td>
	<td>9,05,659</td>
</tr>
<tr>
	<td>12</td>
	<td>15-07-2002</td>
	<td>A.P.J. Abdul Kalam </td>
	<td>9,22,884</td>
	<td>Lakshmi Shewgal</td>
	<td>1,07,366</td>
	<td>8,15,518</td>
</tr>
<tr>
	<td>13</td>
	<td>19-07-2007</td>
	<td>Pratibha Devi Singh Patil</td>
	<td>6,38,116</td>
	<td>Biran Singh Shekavath</td>
	<td>3,31,306</td>
	<td>3,06,810</td>
</tr>

</table>

<div id="presidentialEleHighlightsDiv" style="margin-top: 16px; margin-left: 19px;line-height: 1.9em;">
<span style="background:#21B2ED;border-radius: 5px; color: #FFF; font-family: verdana; font-size: 13px; font-weight: bold; padding: 5px;  margin-left: -4px;">Highlights of Indian President Elections:</span>
<ul style="margin-top: 10px;">
<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>Dr Rajendra Prasad is the only person who workred as india president two times till now.</span>
</li>
<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>Neelam Sanjeeva reddy Elected as President unanimously, it is happended only once in 1977.</span>
</li>
<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>Jakeer Hussain and Fakhruddin Ali are died in their Presidency Duration.</span>
</li>
<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>Till now only one time Ruling Party Candidate Defeated. He is Neelam Sanjeeva reeddy defeated by V.V Giri.</span>
</li>
<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>If the Voters are arrested by PD Act, they can use their vote through Postel Ballot.</span>
</li>
<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>In 1974 Elections, First time Changes done in Presidential and Vice-Presidential Elections Act - 1952, According to new changes, President Candidate should be supported by 20 Voters and 2500 Rupees have to pay as Deposit.In 1997, during 11 th Presidentail elections second time Presidential and Vice-Presidential Elections Act is changed, according to this President Candidate requires 100 voters support, and vice president Candidate requires 40 voters and 10000 rupees have to pay as Deposit.</span>
</li>

<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>In 1997 KR narayanan win on TN Sheshan with 905659 Votes Majority. This is the highest Majory till now.</span>
</li>

<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>The first three elections conducted based on 1951 Census Details, 4th and 5 th elections based on 1961 census details, and 1974 to 2012 elections based on 1971 Census Details.</span>
</li>

<li style="line-height: 1.7em;font-family: verdana,arial,sans-serif;
    font-size: 12px;">
	<img src="images/icons/diamond.png" />&nbsp;
	<span>In the 4th Presidential Elections 17 candidates are participated, in this 9 Candidates got Zero Votes and In 5th Election 15 candidates are participated, in this 5 Candidates got Zero Votes.</span>
</li>
</ul>

</div>
</div>

<script>
function getPresidents()
	 {
	 $.fx.speeds._default = 900;
	 $("#listOfPresidentsInIndia").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 'auto',
								width:600,
								position:[130,130],								
								modal: true,
								title:'<font color="Navy"><b>Former Indian Presidents</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#listOfPresidentsInIndia").dialog();
	var str=$('#listOfPresidents').html();
	$("#listOfPresidentsInIndia").html(str);
	}
</script>
