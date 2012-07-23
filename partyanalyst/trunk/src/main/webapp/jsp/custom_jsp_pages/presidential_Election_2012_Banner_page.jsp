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
.electionResultsTable td {text-align:center;font-weight:bold}
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

</style>
</head>

<div style="width:80%;">
<img src="images/specialPage/Presidential_Elections_2012_banner.png" style="align:center;width:973px;">
</div>


<div style="width:987px;height:450px;background-color:#FFFFFF;border:1px solid #5e5e5e;margin-top:10px;border-radius:2px 2px 2px 2px;margin-bottom:3px;">

 <div style="padding-top:20px;padding-left:10px;font-weight:bold;font-family:verdana;"><p>The 14th indirect presidential election will be held in India on July 19th 2012 to elect the 13th President of India. The last date for filing nominations is the 30 of June while the counting of votes is set to be done on the 22nd of July 2012.</p></div>
 
<div style="width:400px;float:left;">
	<div style="margin-top:15px;font-family:arial;font-size:13px;width:385px;float:right;margin-bottom:10px;padding-left:10px;">
		   <h4 style="color:brown;">Important Dates:</h4><br>

				<b>Notification:</b>June 16th<br>
				<b>Last Date For Nomination :</b> June 30th<br>
				<b>Nomination Scrutinisation :</b> July 2nd<br>
				<b>Last Date For WithDrawl :</b> July 4th<br>
				<b>Polling Date :</b> July 19th<br>
				<b>Results Declared :</b> July 22nd<br>
	  </div>

	  <div style="margin-top:40px;font-family:arial;font-size:13px;padding-left:10px;">
		   <h4 style="color:brown;">Participating Candidates in 2012 Presidential Elections:</h4><br>
			<b>Pranab Mukherjee,</b> Finance Minister of India (UPA candidate)<br><br>
			<b>P. A. Sangma,</b> senior NCP leader from Meghalaya and former Speaker of the Loksabha (NDA candidate)<br>
	  </div>
 </div>

   <div style="width:400px;float:right;margin-right:160px;">
   <div style="margin-top:10px;font-family:arial;font-size:13px;width:500px;float:left;">
	   <h4 style="color:brown;">List Of Presidents In India</h4>
	   <table class="resulttableclass" style="margin-top:20px;font-family:arial;font-size:13px;">
	   <tr><th style="background:#21B2ED;color:#ffffff;">Name</th><th style="background:#21B2ED;color:#ffffff;">In Office</th></tr>
	   <tr><td>Rajendra Prasad</td> <td>26 January 1950 - 13 May 1962</td></tr>
	   <tr><td>Sarvepalli Radhakrishnan </td> <td> 13 May 1962 - 13 May 1967</td></tr>
		<tr><td>Zakir Hussain</td> <td>13 May 1967 - 3 May 1969</td></tr>
		<tr><td>Varahagiri Venkata Giri</td>  <td>24 August 1969 - 24 August 1974  </td></tr>
		<tr><td>Mohammad Hidayatullah </td>   <td>20 July 1969 - 24 August 1969</td></tr>
		<tr><td>Fakhruddin Ali Ahmed </td>    <td>24 August 1974 - 11 February 1977</td></tr>
		<tr><td>Basappa Danappa Jatti</td>    <td>11 February 1977 - 25 July 1977</td></tr>
		<tr><td>Neelam Sanjiva Reddy </td>    <td>25 July 1977 - 25 July 1982</td></tr>
		<tr><td> Giani Zail Singh </td>       <td>25 July 1982 - July 25, 1987</td></tr>
		<tr><td>Ramaswamy Venkataraman</td>    <td>25 July 1987 - 25 July 1992</td></tr>
		<tr><td>Shankar Dayal Sharma </td>     <td> 25 July 1992 - 25 July 1997</td></tr>
		<tr><td>Kocheril Raman Narayanan</td>  <td> 25 July 1997 - 25 July 2002</td></tr>
		<tr><td>A.P.J. Abdul Kalam </td><td> 26 July 2002 - 24 July 2007</td></tr>
		<tr><td>Pratibha Devi Singh Patil</td>  <td>25 July 2007-till Date</td></tr>
	   </table>
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
