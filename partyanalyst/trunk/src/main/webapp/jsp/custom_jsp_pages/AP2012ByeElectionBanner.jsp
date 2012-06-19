<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
      <!-- For image display on mouseover -->	
<script type="text/javascript" src="js/overlib_mini.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script> 
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});

	  </script>

<style>
 .main-mbg {
    width:962px;
}


.resulttableclass a{text-decoration:none;}
.resulttableclass a:hover{text-decoration:underline;}
.resulttableclass{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.resulttableclass td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;height:35px;}
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
.winTD{background-color : GreenYellow;}

.resulth3{border-bottom:2px solid #999999;margin-top:0px;background:#ED5B21;color:#fff;padding:2px;width:550px; -moz-border-radius:2px;margin-left:3px; padding-left: 10px;}



.resulttableclass1 a{text-decoration:none;}
.resulttableclass1 a:hover{text-decoration:underline;}
.resulttableclass1{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.resulttableclass1 td{border:1px solid #d3d3d3;padding:5px 3px;color:#3d3d3d;height:35px;}
.resulttableclass1 th:nth-child(2){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(2) a{color:#fff;}
.resulttableclass1 th:nth-child(3){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(3) a{color:#fff;}
.resulttableclass1 td a{color:#000;}
.resulttableclass1 th:nth-child(4){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(4) a{color:#fff;}
.resulttableclass1 th:nth-child(5){background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(5) a{color:#fff;}
.resulttableclass1 th:nth-child(6) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(6) a{color:#fff;}
.resulttableclass1 th:nth-child(7) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(7) a{color:#fff;}
.resulttableclass1 th:nth-child(8) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(8) a{color:#fff;}
.resulttableclass1 th:nth-child(9) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(9) a{color:#fff;}
.resulttableclass1 th:nth-child(10) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(10) a{color:#fff;}
.resulttableclass1 th:nth-child(11) {background:#21B2ED;color:#fff;}
.resulttableclass1 th:nth-child(11) a{color:#fff;}

.resulttableclass1 th{border:1px solid #d3d3d3;padding:10px 5px;}
.resulttableclass1 tr:nth-child(odd){background:#f3f3f3;}
.resulttableclass1 th:nth-child(1){color:#000;}
.resulttableclass1 th:nth-child(1) a{color:#000;}
.resulttableclass1 td {text-align:center;font-weight:bold}
.winTD{background-color : GreenYellow;}

.resulth3{border-bottom:2px solid #999999;margin-top:0px;background:#ED5B21;color:#fff;padding:2px;width:550px; -moz-border-radius:2px;margin-left:3px; padding-left: 10px;}

.buttonClass {
	background-color: background;
    border-radius: 6px 6px 6px 6px;
    color: white;
    cursor: pointer;
    font-weight: bold;
    padding: 6px;
}
.thStyle{background:#5e5e5e;color:#fff;font-weight:bold;text-align: center;}
.spltableclass a{text-decoration:none;}
.spltableclass a:hover{text-decoration:underline;}
.spltableclass{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.spltableclass td{border:1px solid #d3d3d3;width:1%;padding:5px 3px;color:#3d3d3d;height:35px;}
.spltableclass th:nth-child(2){background:#fcfd03;color:#000;}
.spltableclass th:nth-child(2) a{color:#000;}
.spltableclass th:nth-child(3){background:#ffbfcd;color:#000;}
.spltableclass th:nth-child(3) a{color:#000;}
.spltableclass td a{color:#000;}
.spltableclass th:nth-child(4){background:#006524;color:#fff;}
.spltableclass th:nth-child(4) a{color:#fff;}
.spltableclass th:nth-child(5){background:#0c35ad;color:#fff;}
.spltableclass th:nth-child(5) a{color:#fff;}
.spltableclass th:nth-child(6) {background:#F58220;color:#fff;}
.spltableclass th:nth-child(6) a{color:#fff;}
.spltableclass th{border:1px solid #d3d3d3;width:15%;padding:10px 5px;}
.spltableclass tr:nth-child(odd){background:#f3f3f3;}
.spltableclass th:nth-child(1){background:#5e5e5e;color:#fff;padding:5px;text-align:left;padding-left:15px;}
.spltableclass th:nth-child(1) a{color:#fff;}
#presidentelection ul li a {
    background: none repeat scroll 0 0 #D2E888;
    border-radius: 5px 5px 5px 5px;
    color: #3D3D3D;
    display: block;
    margin-left: auto;
    margin-right: auto;
    margin-top: 20px;
    padding: 10px;
    width: 360px;
}
#presidentelection ul li a:hover{
    background: none repeat scroll 0 0 #21B2ED;
	text-decoration: none;
}
#presidentelection{
  margin-left:100px;
  border:1px solid #CCCCCC;
  padding:0px 10px 10px 10px;
}
#presidentelectionDiv ul li a:hover
{
	text-decoration: none;
}
</style>

<div>

<div>
<IMG src="images/specialPage/AP_BYE_ELECTION_2012.jpg" style="width: 974px;" alt="Andhra Pradesh 2012 Bye Election"/>
</div>
<br>
<div id="upComing" style="background:#FFF;padding-top: 12px;">
<span class="resulth3" style="font-weight:bold;font-family:verdana;margin:13px;padding:5px;width:560px;">Andhra Pradesh 2012 Bi Elections</span>

<br><br><span>&nbsp;&nbsp;&nbsp;&nbsp;Total Assembly Constituencies - <font color="#05A8E9">18</font></span> <span style="padding:10px;"> SC Constituencies - <font color="#05A8E9">3</font> </span> <span style="padding:10px;">ST Constituencies - <font color="#05A8E9">1</font></span> <span style="padding:10px;">General Constituencies - <font color="#05A8E9">14</font></span>

<div style="text-align:justify;margin:10px;padding:10px;"> 
<!--<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">AP Bi Election Schedule</span> -

<span style="font-family:verdana;font-size:13px;">Election Notification - May 18,  Last date for Nomination - May 25, Nomination withdraw Last date - May 28, <br />Polling - June 12, Counting - June 15.</span><br /><br />
-->
<span style="color:#ED5B21;font-weight:bold;font-size: 13px;">AP 2012 Bi Election Results</span> -

<span style="font-family:verdana;font-size:13px;">Andhra Pradesh 2012 Bi-Election results will be announced today. The counting started at 8:00 A.M. <br/>The first result will be <b>Narsapuram</b> announced by 11 A.M. Last result will be <b>Nellore</b> Parliament.</span><br /><br />

<span style="font-family:verdana;font-size:13px;">
YSRC won in Nellore Parliament(2,91,745 majority), Yemmiganuru(20,103 majority), Rayadurg (32,472 majority), Polavaram (35,767 majority), Prathipadu(16,779 majority), Macherla (15,479 majority), Rayachoti (56,891 majority), Railway Kodur (31,991 majority), Ananthapur(24,701 majority), Rajampet (38,219 majority), Payakaraopet(14,362 majority), Allagadda (36,896 lead), Narsannapeta(7,312 majority), Ongole, Udayagiri (30555 majority), Tirupathi (17,975 majority).<br><br>
</span><br>

</span>
<!-- <div>
  <table>
     <tr>
	   <td>INC won in Narsapuram (4,702) and Ramachandra Puram.<br><br>
           TRS won in Parkal with only 1562 (Votes).</td>
	   <td><div id="presidentelection"><ul><li><a title="Indian Presidential Elections 2012" href="specialPageAction.action?specialPageId=10"><span style="font-weight: bold;">Click Here To View Indian Presidential Election 2012</span></a></li>
	   </ul></div></td>
	 </tr>
  </table>
</div> -->

<div>
INC won in Narsapuram (4,702) and Ramachandra Puram.<br><br>
           TRS won in Parkal with only 1562 (Votes).
</div>
<div id="presidentelectionDiv" style="margin-left: 478px; margin-bottom: 0px; margin-top: -51px; clear: both;">
	<table>
		<tr>
			<td>
				<div style="padding: 8px; font-weight: bold; margin-top: -25px; font-size: 15px; border-radius: 3px 3px 3px 3px; border: 1px solid #d3d3d3;">
				<ul>
				<li style="background:#D2E888; padding: 5px 0px; border-radius: 2px 2px 2px 2px; width: 402px; margin-left: 5px;">
				<a title="Indian Presidential Elections 2012" href="specialPageAction.action?specialPageId=10"><span style="font-weight: bold; color: black; margin-left: 16px;">Click Here To View Indian Presidential Election 2012</span></a></li>
				</ul>
				</div>
			</td>
		</tr>
		<tr>
			<td>
			<div style="padding: 8px; font-weight: bold; margin-top: 6px; font-size: 15px; border-radius: 3px 3px 3px 3px; border: 1px solid #d3d3d3;">
				<ul>
				<li style="background:#D2E888; padding: 5px 0px; border-radius: 2px 2px 2px 2px; width: 402px; margin-left: 0px;text-align: center;"><a title="Click Here To View CBI Cases Against Politicians" href="specialPageAction.action?specialPageId=9"><span style="font-weight: bold; color: black; margin-left: 0px;">Click Here To View CBI Cases Against Politicians</span></a></li>
				</ul>
				</div>
			</td>
		</tr>
	</table>
</div>

<br/>
<!--
<div style="padding-left:5px;width:433px;border-radius: 3px;font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;">
<b>Kerala, TN, UP, MP, Jharkhand, WB 2012 Bi-Election Results </b></div>
<span style="font-family:verdana;font-size:13px;">
Kerala - In Neyyattinkara UDF Wins by 6,358 votes.
</span><br><br> -->


<div style="padding-left:5px;width:433px;border-radius: 3px;font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;">
<b>Party Wise Leading/Winning Count in AP 2012 Bi-Election</b></div>
<table>

<tr>
<td>
<div>		
  <div style="font-family: verdana;border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 180px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#7CDB74;"><font style="color:;">INC</font></div>
  <div style="padding-left:5px;font-family: verdana;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 175px;">
     <b>Participated :</b>18<br />
     <b>Lead         :</b>0<br />
	 <b>Won:</b>2
  </div>
</div>
</td>
<td>
<div>		
  <div style="font-family: verdana;border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 180px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#5A93D5;"><font style="color:">YSRC</font></div>
  <div style="padding-left:5px;font-family: verdana;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 175px;">
     <b>Participated :</b>18<br />
     <b>Lead         :</b>0<br />
	 <b>Won          :</b>15
  </div>
</div>
</td>
<td>
<div>		
  <div style="font-family: verdana;border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 180px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#FFFF00;"><font style="">TDP</font></div>
  <div style="padding-left:5px;font-family: verdana;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 175px;">
     <b>Participated :</b>18<br />
     <b>Lead         :</b>0<br />
	 <b>Won          :</b>0
  </div>
</div>
</td>
<td>
<div>		
  <div style="font-family: verdana;border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 180px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#FF9933;"><font style="">BJP</font></div>
  <div style="padding-left:5px;font-family: verdana;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 175px;">
     <b>Participated :</b>11<br />
     <b>Lead         :</b>0<br />
	 <b>Won		     :</b>0
  </div>
</div>
</td>
<td>
<div>		
  <div style="font-family: verdana;border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 180px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#F89BEC;"><font style="">TRS</font></div>
  <div style="padding-left:5px;font-family: verdana;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 175px;">
     <b>Participated :</b>1<br />
     <b>Lead         :</b>0<br />
	 <b>Won          :</b>1
  </div>
</div>
</td>
</tr>
</table>
<div style="padding-left:5px;width:237px;margin-top:10px;border-radius: 3px;font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;">
<b>Winners in AP 2012 Bi-Election</b></div>
<table>

<tr>
<td valign="top">
		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View Yemmiganuru Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=265"><font style="color:#FFFFFF;">YEMMIGANURU</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
	 <td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/ChennakesavaReddyYSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;&nbsp;<b>Majority :</b>20,103
	</td></tr>
	<tr><td colspan="2"><a title="click here to view K.Chenna Kesava Reddy  profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=4658" >K.Chenna kesava Reddy</a></td></tr>
   </table>
  </div>
</div>
</td>
<td valign="top">
<div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View MACHERLA Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=205"><font style="color:#FFFFFF;">MACHERLA</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
     <table><tr>
	 <td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/Pinnelli ramakrishnareddy1.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC</br>
	 &nbsp;&nbsp;<b>Majority :</b>15,479
	 </td></tr>
	<tr><td colspan="2"><a title="click here to Rama Krishna Reddy Pinnelli profile-previous election result, Updated News, Photos ,Videos" href="candidateElectionResultsAction.action?candidateId=2553" id="RAMA KRISHNA REDDY  PINNELLI">Rama Krishna Reddy Pinnelli</a></td></tr>
   </table>
  </div>
</div>
</td>
<td valign="top">
<div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View PRATHIPADU Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=212"><font style="color:#FFFFFF;">PRATHIPADU</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
<table><tr>
	 <td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/Mekatoti Sucharitha YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC</br>
	 &nbsp;&nbsp;<b>Majority :</b>16,779
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to Sucharitha Mekathoti profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=2440" id="Sucharitha Mekathoti">Sucharitha Mekathoti</a></td></tr>
    </table>
  </div>
</div>
</td>
<td valign="top">
<div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View POLAVARAM Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=176"><font style="color:#FFFFFF;">POLAVARAM</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
   <table><tr>
    <td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/Tellam Balaraju YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 	 &nbsp;&nbsp;<b>Majority :</b>35,767
		 </td></tr>
	 <tr><td colspan="2"><a title="click here to view Tellam Bala Raju profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=2092" id="TELLAM  BALA RAJU">Tellam Bala Raju</a></td></tr>
    </table>
  </div>
</div>
</td>
</tr>
<tr>
 <td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View TIRUPATHI Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=291"><font style="color:#FFFFFF;">TIRUPATHI</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
     <table><tr>
	 <td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Karunakar Reddy YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party :</b>YSRC<br/>
	 &nbsp;<b>Majority :</b>17,975
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to Karunakar Reddy  Bhumana profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=3348" >Karunakar Reddy  Bhumana</a></td></tr>
	 </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View ONGOLE Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=227"><font style="color:#FFFFFF;">ONGOLE</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
   <table><tr>
   <td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/Balineni Srinivas Reddy YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;&nbsp;<b>Majority :</b>13,241
	</td></tr>
	 <tr><td colspan="2"><a title="click here to view Balineni Srinivasa Reddy (Vasu) profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=2656" >Balineni Srinivasa Reddy </a></td></tr>
   </table>
 </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View RAYACHOTI Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=248"><font style="color:#FFFFFF;">RAYACHOTI</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
	<td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/Gadikota Srikantreddy YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;&nbsp;<b>Majority :</b>56,891
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to Gadikota Srikanth Reddy profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=3493" >Gadikota Srikanth Reddy</a></td></tr>
  </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View RAILWAY KODUR Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=246"><font style="color:#FFFFFF;">RAILWAY KODUR</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
	<td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/Koramutla Srinivasulu  YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;&nbsp;<b>Majority :</b>31,991</td></tr>
	 <tr><td colspan="2"><a title="click here to Koramutla Srinivasulu  profile-previous election result, Updated News, Photos ,Videos" href="candidateElectionResultsAction.action?candidateId=2887" >Koramutla Srinivasulu</a></td></tr>
 </table>
 </div>
</div>
</td>
</tr>
<tr>
 <td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View RAJAMPETA Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=252"><font style="color:#FFFFFF;">RAJAMPETA</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
	<td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Amaranath reddy.JPG"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;<b>Majority :</b>38,219
	</td></tr>
	 <tr><td colspan="2"><a title="click here to Akepati Amarnath Reddy profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=2862">Amarnath Reddy</a></td></tr>
     </table>
	</div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View ANANTAPUR Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=298"><font style="color:#FFFFFF;">ANANTAPUR</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
	<td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Gurunath Reddy YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;<b>Majority :</b>24,701
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to B.Gurunatha Reddy profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=3434" >B.Gurunatha Reddy</a></td></tr>
  </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View RAYADURGAM Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=276"><font style="color:#FFFFFF;">RAYADURGAM</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
   <table><tr>
	 <td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Kapu Ramachandra Reddy YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;<b>Majority :</b>32,472
      </td></tr>	 
	 <tr><td colspan="2"><a title="click here to Kapu Ramachandra Reddy  profile-previous election result, Updated News, Photos ,Videos" href="candidateElectionResultsAction.action?candidateId=3462" >Kapu Ramchandra Reddy</a></td></tr>
    </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View NARSANNAPETA Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=112"><font style="color:#FFFFFF;">NARSANNAPETA</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
     <table><tr>
	 <td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Krishnadas, YSR Congress.JPG"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;&nbsp;<b>Majority :</b>7,312
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to view Dharmana Krishna Das profile-previous election results, Updated News, Photos ,Videos" href="candidateElectionResultsAction.action?candidateId=1449" >Dharmana Krishna Das</a></td></tr>
</table>
  </div>
</div>
</td>
</tr>
<tr>
 <td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View PAYAKARAOPET Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=140"><font style="color:#FFFFFF;">PAYAKARAOPET</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
     <table><tr>
	 <td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Golla Baburao YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;<b>Majority :</b>14,362
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to Golla Babu Rao profile-previous election result, Updated News, Photos ,Videos" href="candidateElectionResultsAction.action?candidateId=1693" id="GOLLA  BABURAO">Golla Babu Rao</a></td></tr>
  </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View ALLAGADDDA Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=254"><font style="color:#FFFFFF;">ALLAGADDDA</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
     <table><tr>
	 <td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Sobha Nagireddy YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;<b>Majority :</b>36,896</td></tr>
	 <tr><td colspan="2"><a title="click here to view Bhooma Shobha Nagireddy  profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=2965" >Bhuma Shobha Reddy</a></td></tr>
    </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View UDAYAGIRI Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=254"><font style="color:#FFFFFF;">UDAYAGIRI</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
     <table><tr>
	 <td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Mekapati Chandrasekhar YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>YSRC<br/>
	 &nbsp;<b>Majority :</b>30,555</td></tr>
	 <tr><td colspan="2"><a title="click here to view Mekapati Chandrashekar Reddy profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=3582" > Mekapati Chandrashekar Reddy</a></td></tr>
  </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#0266B4;"><font style="color:#ffffff;"><a title="Click here to View NELLORE PARLIAMENT Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=495"><font style="color:#FFFFFF;">NELLORE PARLIAMENT</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
    <td><img style="padding-left:2px;" width="100" height="100" src="images/bielections/Mekapati Rajamohan YSRCP.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/YSRC.PNG"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party :</b>YSRC<br/>
	 <b>Majority :</b>2,91,745
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to Karunakar Reddy  Bhumana profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=13626" >Mekapati Rajamohan Reddy</a></td></tr>
   </table>
  </div>
</div>
</td>
</tr>
<tr>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#56EC58;"><font style="color:#ffffff;"><a title="Click here to View RAMACHANDRAPURAM Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=159"><font style="color:#FFFFFF;">RAMACHANDRAPURAM</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
	<td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Thota Trimurtulu RCPM.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/INC.png"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party :</b>INC<br/>
	 &nbsp;<b>Majority :</b>11,919
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to view Thota Trimurthulu  profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=1825" >Thota Trimurthulu</a></td></tr>
  </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#56EC58;"><font style="color:#ffffff;"><a title="Click here to View NARSAPURAM Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=173"><font style="color:#FFFFFF;">NARSAPURAM</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
     <table><tr>
	 <td><img style="padding-left:5px;" width="100" height="100" src="images/bielections/Kottapalli Subbarayudu Cong.JPG"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/INC.png"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party:</b>INC<br/>
	 &nbsp;&nbsp;<b>Majority :</b>4,472 </td></tr>
	 <tr><td colspan="2"><a title="click here to Kothapalli Subbarayudu (Peda Babu) profile-previous election result, Updated News, Photos ,Videos" href="candidateElectionResultsAction.action?candidateId=3952" id="KOTHAPALLI SUBBARAYUDU (PEDA BABU)">Kothapalli Subbarayudu </a></td></tr>
   </table>
  </div>
</div>
</td>
<td valign="top">
  <div>		
  <div style="border: 1px solid #CCCCCC;border-radius: 5px 5px 0px 0px;width: 231px;text-align: center;font-weight: bold;margin-top: 10px;background-color:#F89BEC;"><font style="color:#ffffff;"><a title="Click here to View PARAKAL Constituency Details, Previous Election Results, Voting Trendz" href="constituencyPageAction.action?constituencyId=94"><font style="color:#FFFFFF;">PARAKAL</font></a></font></div>
  <div style="padding-left:5px;border-bottom: 1px solid #CCCCCC;border-left: 1px solid #CCCCCC;border-right: 1px solid #CCCCCC;border-radius: 0px 0px 5px 5px;width: 226px;">
    <table><tr>
	 <td><img style="padding-left:10px;" width="100" height="100" src="images/bielections/Moluguri Bikshapathi1.jpg"></img></td><td><img style="padding-left:28px;" width="50" height="50" src="images/party_flags/TRS.png"></img><br />
	 
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Party :</b>TRS<br/>
	 &nbsp;&nbsp;<b>Majority :</b>1562
	 </td></tr>
	 <tr><td colspan="2"><a title="click here to view Moluguri Bikshapathi profile-previous election result, Updated News, Photos ,Videos"  href="candidateElectionResultsAction.action?candidateId=10200" >Moluguri Bikshapathi</a></td></tr>
</table>
	 </div>
</div>
</td>
</tr>
</table>


<br/>
<div id="constituencyInformationDiv">
   <span style="font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;font-weight:bold;border-radius: 3px;">Comparision BetWeen 2009 And 2012 Each Party Wise</span>
   <br>
 <table width="100%" class="resulttableclass1" style="margin-top:20px;margin-bottom:30px;text-align:center" id="byeElectionResultInfo">
  <tr>
     <th style="background:#21B2ED;color:#FFF;width:20px;">Constituency</th>
	<th colspan="2" style="padding:0px;"><span>Total Voters</span>
	<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); padding: 5px 24px 19px 0px; margin-right: 9px; text-align: left; margin-left: 0px;">2009</span>
		<span style="margin-right: 0px; padding-left: 11px; border-bottom-width: 0px; margin-bottom: 0px;"> 2012</span>
	</th>
		<th colspan="2" STYLE="padding:0PX;">Polled Votes
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); padding: 5px 24px 19px 0px; margin-right: 9px; text-align: left; margin-left: 0px;">2009</span>
		<span style="margin-right: 0px; padding-left: 11px; border-bottom-width: 0px; margin-bottom: 0px;"> 2012</span>
	</th>
	<th>YSRC<br>
		2012
	</th>
		<th colspan="2" style="width:60px;padding:0px;"><span>TDP</span>
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); margin-right: -6px; text-align: left; margin-left: 0px; padding: 5px 5px 3px;">2009</span>
        <span style="margin-right: 0px; padding-left: 11px; border-bottom-width: 0px; margin-bottom: 0px;"> 2012</span>
		
	</th>
		<th colspan="2" style="width:60px;padding:0px;"><span>INC</span>
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); text-align: left; margin-left: 0px; padding: 5px 8px 4px 28px; margin-right: 0px;">2009</span>
       <span style="margin-right: 10px; border-bottom-width: 0px; margin-bottom: 0px; padding-left: 5px; margin-left: 0px;"> 2012</span>
	</th>

	<th><span>PRP</span>
		<br> 2009</span>
	</th>
		<th colspan="2" style="width:60px;padding:0px;"><span>TRS</span>
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); text-align: left; margin-left: 0px; padding: 5px 8px 4px 10px; margin-right: 0px;">2009</span>
<span style="margin-right: 10px; border-bottom-width: 0px; margin-bottom: 0px; padding-left: 5px; margin-left: 0px;"> 2012</span>
	</th>
		
	
	
   </tr>

   <tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=254" title="Click here to View Allagadda Constituency Details, Previous Election Results, Voting Trendz" >Allagadda</a>
    </td>
    <td style="width: 70px;">200691</td>
	<td style="width: 70px;">203308</td>
    <td style="width: 70px;">153925</td>
	<td style="width: 70px;">168843</td>
	<td style="width: 63px;color:#FFF;background:green;">88697</td>
	<td style="width:40px">23800</td>
	<td style="width:40px">20374</td>
    <td style="width:40px">59597</td>
	<td style="width:40px">51801</td>
	<td style="width:40px" class="winTD">61555</td>
	<td style="width:40px"></td>
	<td style="width:40px"></td>
   </tr>

   <tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=298" title="Click here to View Ananthapur Constituency Details, Previous Election Results, Voting Trendz" >Ananthapur</a></td>
	<td>229876</td>
	<td>188236</td>
    <td>116008</td>
	<td>124047</td>
	<td style="width:40px;color:#FFF;background:green;">65560</td>
	<td style="width:40px">32033</td>
	<td style="width:40px">40859</td>
    <td style="width:40px" class="winTD">24701</td>
	<td style="width:40px">9402</td>
	<td style="width:40px">28489</td>
	<td style="width:40px"></td>
	<td style="width:40px"></td>
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=205" title="Click here to View Macherla Constituency Details, Previous Election Results, Voting Trendz" >Macherla</a></td>
	<td style="width: 70px;">201861</td>
	<td style="width: 70px;">206603</td>
    <td>152057</td>
	<td>170881</td>
    <td style="color:#FFF;background:green;">79751</td>
	<td>57168</td>
    <td>64272</td>
	<td class="winTD">66953</td>
	<td>19065</td>
	<td>16386</td>
	<td></td>
	<td></td>
	</tr>

<tr>
	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=112" title="Click here to View Narasannapeta Constituency Details, Previous Election Results, Voting Trendz">Narasannapeta </a></td>
	<td>172020</td>
    <td>174930</td>
	<td>136923</td>
	<td>140258</td>
	<td style="color:#FFF;background:green;">54454</td>
	<td>42837</td>
	<td>32401</td>
	<td class="winTD">60426</td>
	<td>47142</td>
	<td>25280</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=173" title="Click here to View Narsapur Constituency Details, Previous Election Results, Voting Trendz" >Narsapuram</a></td>
	<td>132956</td>
	<td>141921</td>
	<td>115957</td>
	<td>125983</td>
	<td>53896</td>
	<td>10841</td>
	<td>8813</td>
	<td class="winTD">58560</td>
	<td style="color:#FFF;background:green;">58368</td>
	<td>41235</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=227" title="Click here to View Ongole Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ongole</a></td>
	<td>234265</td>
	<td>200644</td>
	<td>156530</td>
	<td>158047</td>
	<td style="color:#FFF;background:green;">77125</td>
	<td>44228</td>
	<td>49722</td>
	<td class="winTD">67214</td>
	<td>23014</td>
	<td>33716</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=94" title="Click here to View Parkal Constituency Details, Previous Election Results, Voting Trendz" >Parkal</a></td>
	<td>193381</td>
	<td>187268</td>
	<td>147872</td>
	<td>157267</td>
	<td>50374</td>
	<td>NP</td>
	<td>30850</td>
	<td class="winTD">69135</td>
	<td>5099</td>
	<td>11968</td>
	<td>56335</td>
	<td style="color:#FFF;background:green;">51936</td>
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=140" title="Click here to View Payakaraopet Constituency Details, Previous Election Results, Voting Trendz" >Payakaraopet (SC)</a></td>
	<td>210830</td>
	<td>202953</td>
	<td>157926</td>
	<td>172510</td>
	<td style="color:#FFF;background:green;">71963</td>
	<td>50042</td>
	<td>57601</td>
	<td class="winTD">50698</td>
	<td>33867</td>
	<td>49264</td>
	<td></td>
	<td></td>
	
</tr>

<tr>
  	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=176" title="Click here to View Polavaram Constituency Details, Previous Election Results, Voting Trendz" >Polavaram</a></td>
	<td>166300</td>
	<td>172199</td>
	<td>144687</td>
	<td>149968</td>
	<td style="color:#FFF;background:green;">80790</td>
	<td>44634</td>
	<td>45023</td>
	<td class="winTD">50298</td>
	<td>16081</td>
	<td>36483</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=212" title="Click here to View Prathipadu Constituency Details, Previous Election Results, Voting Trendz" >Prathipadu (SC)</a></td>
	<td>207396</td>
	<td>213228</td>
	<td>168896</td>
	<td>179943</td>
	<td style="color:#FFF;background:green;">87738</td>
	<td>64282</td>
	<td>70959</td>
	<td class="winTD">66324</td>
	<td>15949</td>
	<td>33889</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=246" title="Click here to View Railway Kodur Constituency Details, Previous Election Results, Voting Trendz">Railway Kodur (SC)</a></td>
	
	<td>158139</td>
	<td>159900</td>
	<td>117703</td>
	<td>123586</td>
	<td style="color:#FFF;background:green;">66456</td>
	<td>39359</td>
	<td>17594</td>
	<td class="winTD">51747</td>
	<td>34465</td>
	<td>22122</td>
	<td></td>
	<td></td>
	
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=252" title="Click here to View Rajampet Constituency Details, Previous Election Results, Voting Trendz" >Rajampet</td>
	<td>185475</td>
	<td>186832</td>
	<td>138346</td>
	<td>146663</td>
	<td style="color:#FFF;background:green;">76951</td>
	<td>48055</td>
	<td>21417</td>
	<td class="winTD">60397</td>
	<td>38732</td>
	<td>21499</td>
	<td></td>
	<td></td>
	</tr>
	
	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=159" title="Click here to View Ramachandrapuram Constituency Details, Previous Election Results, Voting Trendz" >Ramachandrapuram </a></td>
	<td>175344</td>
	<td>178513</td>
	<td>142302</td>
	<td>154378</td>
	<td>65373</td>
	<td>23252</td>
	<td>6256</td>
	<td class="winTD">56589</td>
	<td style="color:#FFF;background:green;">77292</td>
	<td>52558</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
    
	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=248" title="Click here to View Rayachoti Constituency Details, Previous Election Results, Voting Trendz">Rayachoti</a></td>
	
	<td>186579</td>
	<td>200226</td>
	<td>143194</td>
	<td>159239</td>
	<td style="color:#FFF;background:green;">90978</td>
	<td>57069</td>
	<td>25344</td>
	<td class="winTD">71901</td>
	<td>34087</td>
	<td>6469</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
   	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=276" title="Click here to View Rayadurg Constituency Details, Previous Election Results, Voting Trendz">Rayadurg</a></td>
	<td>198167</td>
	<td>194646</td>
	<td>155854</td>
	<td>170996</td>
	<td style="color:#FFF;background:green;">79167</td>
	<td>62168</td>
	<td>46695</td>
	<td class="winTD">76259</td>
	<td>32990</td>
	<td>9670</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
   
	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=291" title="Click here to View Tirupathi Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Tirupathi</a></td>
	<td>247160</td>
	<td>250258</td>
	<td>127627</td>
	<td>136866</td>
	<td style="color:#FFF;background:green;">59195</td>
	<td>21307</td>
	<td>30453</td>
	<td>40379</td>
	<td>41220</td>
	<td class="winTD">17975</td>
	<td></td>
	<td></td>

	</tr>

	<tr>
    
	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=238" title="Click here to View Udayagiri Constituency Details, Previous Election Results, Voting Trendz" >Udayagiri</a> </td>
	<td>203782</td>
	<td>196159</td>
	<td>148133</td>
	<td>160850</td>
	<td style="color:#FFF;background:green;">74976</td>
	<td>55870</td>
	<td>44421</td>
	<td class="winTD">69352</td>
	<td>34451</td>
	<td>14512</td>
	<td></td>
	<td></td>
	</tr>

	<tr>
    
	<td style="text-align: left;"><a href="constituencyPageAction.action?constituencyId=265" title="Click here to View Yemmiganuru Constituency Details, Previous Election Results, Voting Trendz" >Yemmiganuru</a></td>
	<td>189278</td>
	<td>199631</td>
	<td>128816</td>
	<td>152518</td>
	<td style="color:#FFF;background:green;">64155</td>
	<td>51443</td>
	<td>44052</td>
	<td class="winTD">53766</td>
	<td>33733</td>
	<td>16638</td>
	<td></td>
	<td></td>
	</tr>

<tr>
   	<td style="text-align: left;">Total</td>
	<td>34,93,500</td>
	<td>34,57,455</td>
	<td>25,52,756</td>
	<td>27,52,843</td>
	<td>12,87,599</td>
	<td>6,72,053</td>
	<td>6,57,106</td>
	<td>10,74,870</td>
	<td>6,06,758</td>
	<td>5,38,042</td>
	<td>56,335</td>
	<td>51,936
</td>
	</tr>
	
   </table>
   </div>

<div>
   <span style="font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;font-weight:bold;border-radius: 3px;">Comparision Between 2009 and 2012 in Nellore Parliament Constituency Bi Election</span> 
   <br>
 <table width="100%" class="resulttableclass1" style="margin-top:20px;margin-bottom:30px;text-align:center" id="byeElectionResultInfo">
  <tr>
     <th style="background:#21B2ED;color:#FFF;width:20px;">Constituency</th>
	<th colspan="2" style="padding:0px;"><span>Total Voters</span>
	<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); padding: 5px 18px 5px 0px; margin-right: 16px; text-align: left; margin-left: 0px;">2009</span><span style="margin-right: -9px; padding-left: 6px;"> 2012</span>
	</th>
		<th colspan="2" style="padding:0px;">Polled Votes
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); padding: 5px 18px 5px 0px; margin-right: 16px; text-align: left; margin-left: 0px;">2009</span><span style="margin-right: -9px; padding-left: 6px;"> 2012</span>
	</th>
	<th>YSRC<br>
		2012
	</th>
		<th colspan="2" style="width:50px;padding:0px;"><span>TDP</span>
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); padding: 5px 18px 5px 0px; margin-right: 16px; text-align: left; margin-left: 0px;">2009</span><span style="margin-right: -9px; padding-left: 6px;"> 2012</span>
		
	</th>
		<th colspan="2" style="width:50px;padding:0px;"><span>INC</span>
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); padding: 5px 18px 5px 0px; margin-right: 16px; text-align: left; margin-left: 0px;">2009</span><span style="margin-right: -9px; padding-left: 6px;"> 2012</span>
	</th>

	<th><span>PRP</span>
		<br> 2009</span>
	</th>
		<!-- <th colspan="2" style="width:50px;padding:0px;"><span>TRS</span>
		<hr style="color:#d3d3d3;">
		<span style="border-right: 1px solid rgb(211, 211, 211); padding: 5px 18px 5px 0px; margin-right: 16px; text-align: left; margin-left: 0px;">2009</span><span style="margin-right: -9px; padding-left: 6px;"> 2012</span>
	</th>-->
		
	
	
   </tr>

   <tr>
   	<td><a href="constituencyPageAction.action?constituencyId=495" title="Click here to View Nellore Constituency Details, Previous Election Results, Voting Trendz" >Nellore</a>
    </td>
    <td style="width: 50px;">1450938</td>
	<td style="width: 50px;">1352293</td>
    <td style="width: 50px;">1002483</td>
	<td style="width: 50px;">969323</td>
	<td style="width:40px;background:green;color:#FFF;">535436</td>
	<td style="width:40px">375242</td>
	<td style="width:40px">154103</td>
    <td style="width:40px">430235</td>
	<td style="width:40px">243691</td>
	<td style="width:40px">138111</td>
	<!-- <td style="width:40px"></td>
	<td style="width:40px"></td>-->
   </tr>

  </table>
</div>
<!-- chart 

<div style="margin-left:20px;">
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;"><b>Party wise seats Difference Between 2009 and 2012</b></span>

<table width="90%"><tr>
	<td width="50%">
			<table>
				<tr>
				
			</tr>
			<tr>

		<td id="2012seatschartDiv" style="width:300px; height: 300px;"></td>
		</td>
		<td>
			</td>
		</tr>
		</table>
</td>
<td width="40%">
		<table>
			<tr>
		
		</tr>
		<tr>
		<td id="2009seatschartDiv" style="width:300px; height: 300px;"></td>
		</td>
		<td>
		</td>
		</tr>
			</table>
</td>
</tr></table>
</div><br>-->




<fieldset style="verdana,sans-serif;font-weight:bold;">
<legend style=" border-radius: 3px;">Party wise Votes Comparision Between 2009 and 2012</legend>
<div style="margin-left:20px;">
<!--<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;"><b>Party wise Votes Comparision Between 2009 and 2012</b></span>-->

<table width="100%"><tr>
	<td width="50%">
			<table>
				<tr>
				
			</tr>
			<tr>

		<td id="2012voteschartDiv" style="width:300px; height: 300px;"></td>
		</td>
		<td>
			</td>
		</tr>
		</table>
</td>
<td width="50%">
		<table>
			<tr>
		
		</tr>
		<tr>
		<td id="2009voteschartDiv" style="width:300px; height: 300px;"></td>
		</td>
		<td>
		</td>
		</tr>
			</table>
</td>
</tr></table>
</div>
</fieldset>

<!-- end -->







<br>
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;">
<b>Party Analyst Detailed Analysis on Andhra Pradesh 2012 Bi Elections</b></span><span style="margin-left:10px;"><a href="javascript:{}" onClick="getAP2012ByeElectionInformation()" style="color:#21B2ED;font-family:verdana;"><b>Read Now >></b></a></span>
<div id="show2012byeelectionreport"></div>
<br /><br />
<div id="byeElectionAnalysis" style="display:none;">
<div style="margin-left:20px;margin-top:15px;">
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;">
<b>What factors makes TDP and INC to fight with YSRC ?</b></span>
<ul style="margin: 18px 18px 18px 0px; font-family: verdana; font-size: 13px;">
<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png"> INC and TDP are able to give good fight in some constituencies where candidates are very strong (Kothapalli Subbarayudu from &nbsp;&nbsp;&nbsp;Narsapur, Thota Trimurthulu from Ramachandrapuram, Chengala Venkata Rao from Payakaraopeta, Deepak Reddy from Raydurg &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;etc...).</li>

<li style="margin-bottom: 14px;"><img src="images/icons/diamond.png" style="padding: 5px 5px 0px;">Interestingly most of these leaders in INC came from PRP. Thus, PRP merger benefited to congress in this way rather than &nbsp;&nbsp;&nbsp;&nbsp;Chiranjeevi image.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Present government is INC. So, if people vote for INC, they will at least get some development activities in these two years.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png"> Everyone in YSRC are sitting MLAs, they are having lot of opposition from the voters. Even this opposition has been overcome &nbsp;&nbsp;&nbsp;&nbsp;with YSR factor.</li>

 <li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Caste factor for TDP, especially from Kamma Community.</li>

</ul>
</div>
<div style="margin-left:20px;">
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;"><b>What factors makes YSRC to get this much hype    ?</b></span>
<ul style="margin: 18px 18px 18px 0px;font-family: verdana; font-size: 13px;">
<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">YSR - Low and middle class people are feeling that all the developments and benefits they got in INC government, is only due to &nbsp;&nbsp;&nbsp;&nbsp;YSR. No one is able to create confidence in these people after YSR death. Voters are feeling that, if YSRC comes into ruling, only &nbsp;&nbsp;&nbsp; then,they will get better beneficial schemes. This hope is making the voters to move with YSRCP.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Sympathy (Especially with YSR family on roads), Why the government is harassing their leader's family, who has done something &nbsp;&nbsp;&nbsp;for them.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Mr. Chandra Babu Naidu is working hard to bring his party into power. Unfortunately YSR created some trademark in the voters &nbsp;&nbsp;(middle class and lower class people), not to believe Chandra Babu Naidu's promises. This has become a bottleneck to improve &nbsp;TDP.</li>
</ul>
</div>

<div style="margin-left:20px;">
<span style="border-radius: 3px;font-family:verdana;font-size:13px;background:YellowGreen;padding:5px;color:#ffffff;"><b>Election Commission's factor in increasing Voting Percentage</b></span>
<ul style="margin: 18px 18px 18px 0px;font-family: verdana; font-size: 13px;">
<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">It is in circulation that the polling percentage has increased in AP Bye elections, because of voters voluntary participation or their    opposition to government or favoring one party.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">But we need to consider one important factor which is missed, a great step which Election Commission has taken in 2011 and must    appreciate it for this.</li>

<li style="margin-bottom: 14px;"><img style="padding: 5px 5px 0px;" src="images/icons/diamond.png">Election Commission has taken certain steps to eliminate duplicate, transferred and dead voters from 2011 voters list, before    publishing 2012 voter rolls. In this process, around 10-12% of voters are cleaned up from old list. Every time, this 10-12% won't get    polled. Then this 10-12% of voters will not be polled, even when everyone votes.</li>
</ul>
</div>


</div>
<span style="font-family:verdana;font-size:13px;background:#ED5B21;padding:5px;color:#ffffff;"><b>Party Analyst View on Coming Results of 18 Assembly & Nellore Parliament of Andhra Pradesh 2012 Bye Election</b> </span><br><br>

<span style="font-family:verdana;font-size:13px;">Party Analyst announcing its Views on Coming Results of Andhra Pradesh 2012 Bi Elections. In 18 Assembly Constituencies <b>YSRC Party may Win 12 - 15 Seats, INC Party may Win 1 - 2 Seats, TDP Party may Win 1 - 3 Seats, TRS may Win 1 Seat (Parakala Constituency) and YSRC will Win in Nellore Parliament Constituency</b>.</span><br /><br>

<style>
	

.resultclass{
width:275px;
color:#fff;
display:inline-block;
position:relative;
margin:5px 10px 5px 10px;
text-align:center;
}
.resultclass span.favour{float:left;padding:5px;background:#aaa;width:265px;height:33px;over-flow:auto;}

.resultclass span.cons{background:#f3f3f3;width:100%;
color:#000;font-size:15px;display:inline-block;float:left;
border-radius:5px 5px 0px 0px;box-shadow:0px -2px 1px #888;
padding:5px 0px;
}

.resultclass .borderperc{position:relative;display:inline-block;width:275px;
background:#efefef;
padding:1px 0px; clear:both;
text-shadow:0px 1px 2px #fff;color:#000;
}
.perc1{float:left;display:inline-block;box-shadow:0px 0px 2px #aaa;border-radius:0px 0px 0px 5px;padding:5px 0px;}
.perc2{float:left;display:inline-block;box-shadow:0px 0px 2px #aaa;border-radius:0px 0px 5px 0px;padding:5px 0px;}
.perc3{float:left;display:inline-block;box-shadow:0px 0px 2px #aaa;border-radius:0px 0px 5px 5px;padding:5px 0px;}

</style>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=252" title="Click here to View Rajampet Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Rajampet</a></span>
	<span class="favour">YSRC Party (Amarnath Reddy) will Win</span>

	
<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=248" title="Click here to View Rayachoti Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Rayachoti</a></span>
	<span class="favour">YSRC Party (Gadikota Srikanth Reddy) will Win</span>

	
<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117"  title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=298" title="Click here to View Ananthapur Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ananthapur</a></span>
	<span class="favour">YSRC Party (B.Gurunatha Reddy) will Win</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;">

<a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=254" title="Click here to View Allagadda Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Allagadda</a></span>
	<span class="favour">YSRC Party (Bhuma Shobha Reddy) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;">

<a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=291" title="Click here to View Tirupathi Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Tirupathi</a></span>
	<span class="favour">YSRC Party (Karunakar Reddy Bhumana) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=238" title="Click here to View Udayagiri Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Udayagiri</a></span>
	<span class="favour">YSRC Party (Mekapati Chandrashekar Reddy ) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=265" title="Click here to View Yemmiganuru Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Yemmiganuru</a></span>
	<span class="favour">YSRC Party (K.Chenna kesava Reddy) will Win(TDP Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=246" title="Click here to View Railway Kodur Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Railway Kodur (SC)</a></span>
	<span class="favour">YSRC Party (Koramutla Srinivasulu) will Win(INC Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=227" title="Click here to View Ongole Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ongole</a></span>
	<span class="favour">YSRC Party (Balineni Srinivasa Reddy (Vasu)) will Win(TDP Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=140" title="Click here to View Payakaraopet Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Payakaraopet (SC)</a></span>
	<span class="favour">YSRC Party (Golla Babu Rao) will Win(TDP Second Place)</span>

	<div class="borderperc">
	<div class="perc3" style="background:#0266B4;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=205" title="Click here to View Macherla Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Macherla</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>
	
	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=176" title="Click here to View Polavaram Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Polavaram</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117"  title="Click here to View YSRC Party Details"style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=212" title="Click here to View Prathipadu Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Prathipadu (SC)</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=276" title="Click here to View Rayadurg Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Rayadurg</a><img width="25" height="20" src="images/party_flags/TDP.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and TDP</span>

	<div class="borderperc">
	
<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#ffff00;width:50%;"><a href="partyPageAction.action?partyId=872" title="Click here to View TDP Party Details" style="color:#000000;">TDP (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/TRS.png" style="float:left;margin-left:5px;"></span>
<a href="constituencyPageAction.action?constituencyId=94" title="Click here to View Parakala Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;" >Parakala</a><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between TRS and YSRC</span>

	<div class="borderperc">
	<div class="perc1" style="background:#F89BEC;width:50%;"><a href="partyPageAction.action?partyId=886" title="Click here to View TRS Party Details" style="color:#000000;">TRS (50%)</div>
	<div class="perc2" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	</div>
</div>

<div class="resultclass">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=112" title="Click here to View Narasannapeta Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Narasannapeta</a><img width="25" height="20" src="images/party_flags/INC.png" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRC and INC</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:75%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (75%)</a></div>
	<div class="perc2" style="background:#56EC58;width:25%;"><a href="partyPageAction.action?partyId=362" title="Click here to View INC Party Details" style="color:#ffffff;">INC (25%)</a></div>
	</div>
</div>

<div class="resultclass" style="margin-left:31px;">
	<span class="cons"><span><img width="25" height="20" src="images/party_flags/INC.png" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=173" title="Click here to View Narsapur Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Narsapuram</a></span>
	<span class="favour">INC Party (Kothapalli Subbarayudu (Peda Babu)) will Win</span>

	<div class="borderperc">
	<div class="perc3" style="background:#56EC58;color:#FFFFFF;width:100%;"><a href="partyPageAction.action?partyId=362" title="Click here to View INC Party Details" style="color:#ffffff;">INC</a></div>
	</div>
</div>

<div class="resultclass" style="margin-left:31px;">
	<span class="cons">
	<span><img width="25" height="20" src="images/party_flags/YSRC.PNG" style="float:left;margin-left:5px;"></span>
	<a href="constituencyPageAction.action?constituencyId=159" title="Click here to View Ramachandrapuram Constituency Details, Previous Election Results, Voting Trendz" style="color:#000000;">Ramachandrapuram</a><img width="25" height="20" src="images/party_flags/INC.png" style="float:right;margin-right:5px;"></span>
	<span class="favour">Close Fight between YSRCand INC</span>

	<div class="borderperc">
	<div class="perc1" style="background:#0266B4;color:#FFFFFF;width:50%;"><a href="partyPageAction.action?partyId=1117" title="Click here to View YSRC Party Details" style="color:#ffffff;">YSRC (50%)</a></div>
	<div class="perc2" style="background:#56EC58;width:50%;"><a href="partyPageAction.action?partyId=362" style="color:#ffffff;"><a href="partyPageAction.action?partyId=362" title="Click here to View INC Party Details" style="color:#ffffff;">INC (50%)</a></a></div>
	</div>
</div>
<br><br>

<div style="margin-left: 610px; margin-top: 20px; margin-bottom: 0px;">
<span id="imageId"><img title="Click Here To Expand" style="" src="images/icons/plusImg .png" onclick="showVotesInfo('votesInfoId')"></img></span></div>

<div style="margin-left: 10px; margin-bottom: 13px; padding-bottom: 19px; margin-top: -21px;">
<span style="background: none repeat scroll 0 0 #ED5B21;border-radius: 5px 5px 5px 5px;color: #FFFFFF;font-family: verdana;font-size: 13px;font-weight: bold;margin-bottom: 20px;margin-left: 0;padding: 5px;cursor:pointer;"  onclick="showVotesInfo('votesInfoId')">View 2012 Andhra Pradesh Bi Election Results Completed In 7 Constituencies</span>
</div>
<table width="100%" class="resulttableclass" style="margin-top:9px;display:none;margin-bottom:10px;" id="votesInfoId">
  <tr>
    <th style="background-color:#21B2ED;color:#fff;">Constituency</th>
    <th>Total Voters</th>
    <th>Polled Votes</th>
	<th>TRS</th>
	<th>INC</th>
	<th>TDP</th>
    <th>YSRC</th>
	<th>BJP</th>
	<th>IND</th>
	<th>Majority in 2012</th>
	<th>Majority in 2009</th>
   </tr>
  <tr>
    <th><a href="constituencyPageAction.action?constituencyId=1">Adilabad</a></th>
    <td>172045</td>
    <td>120450</td>
	<td class="winTD">59452</td>
    <td>28056</td>
	<td>24288</td>
    <td></td>
	<td></td>
    <td></td>
	<td>31396</td>
   	<td>25580</td>
  </tr>
  <tr>
    <th><a href="constituencyPageAction.action?constituencyId=16">Kamareddy</a></th>
    <td>203111</td>
	<td>137051</td>
    <td class="winTD">75699</td>
	<td>31234</td>
    <td>17839</td>
	<td></td>
    <td></td>
	<td></td>
    <td>44465</td>
	<td>47708</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=67">Kollapur</a></th>
    <td>195798</td>
    <td>151217</td>
	<td class="winTD">58107</td>
    <td>43083</td>
	<td>35287</td>
    <td></td>
	<td></td>
    <td></td>
	<td>15024</td>
    <td>1508</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=233">Kovur</a></th>
    <td>209626</td>
    <td>175912</td>
	<td></td>
    <td>41397</td>
	<td>50382</td>
    <td class="winTD">&nbsp;&nbsp;73876&nbsp;&nbsp;</td>
	<td></td>
    <td></td>
	<td>23494</td>
    <td>7444</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=68">Mahabubnagar</a></th>
	<td>188156</td>
    <td>124706</td>
	<td>37506</td>
    <td>25333</td>
	<td>17518</td>
    <td></td>
	<td class="winTD">&nbsp;&nbsp;39385&nbsp;&nbsp;</td>
    <td></td>
	<td>1879</td>
    <td>--</td>
  </tr>
  <tr>
    <th ><a href="constituencyPageAction.action?constituencyId=70">Nagarkurnool</a></th>
    <td>191379</td>
    <td>140394</td>
	<td></td>
    <td>43676</td>
	<td>18608</td>
    <td></td>
	<td></td>
    <td class="winTD">&nbsp;&nbsp;71001&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;27325&nbsp;&nbsp;</td>
	<td>&nbsp;&nbsp;6593&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <th><a href="constituencyPageAction.action?constituencyId=87">Station Ghanpur</a></th>
    <td>221451</td>
    <td>169911</td>
	<td class="winTD">81279</td>
    <td>28965</td>
	<td>48641</td>
    <td></td>
	<td></td>
    <td></td>
	<td>32638</td>
    <td>11210</td>
  </tr>
</table>
</div></div>

<script type="text/javascript">

function showVotesInfo(tableId)
{
	
	var imageIdElmt = document.getElementById("imageId");
	var str = '';
	
	if(document.getElementById("votesInfoId").style.display == 'block')
	{
		$("#votesInfoId").slideToggle("slow");

	   document.getElementById("votesInfoId").style.display = 'none';
	   str += '<img title="Click Here To Expand" style="" src="images/icons/plusImg .png" onclick="showVotesInfo()"></img>';
	}
	else if(document.getElementById("votesInfoId").style.display == 'none')
	{
		$("#votesInfoId").slideToggle("slow");

	   document.getElementById("votesInfoId").style.display = 'block';

	   str += '<img title="Click here to hide Table" style="" src="images/icons/minusImage.png" onclick="showVotesInfo()"></img>';
	}
	
	
	imageIdElmt.innerHTML = str;

}

function getConstituencyElecResultsWindow(constiId,elecType,elecYear)
	{
	   
	   var browser1 = window.open("constituencyElectionResultsAction.action?constituencyId="+constiId+"&electionType="+elecType+"&electionYear="+elecYear,"constituencyElectionResults","scrollbars=yes,height=600,width=750,left=200,top=200");
	   browser1.focus();
	}
	/*function displayImage(candidateName){
		overlib('<div><img style="height:115px;width:115px;" src="images/candidates/'+candidateName+'.jpg" alt="'+candidateName+' Image not available"></img></div>');
     }*/

	

	 function byeEleParticipatedCandidateInfo()
	 {
		var str = '';
		var byeEleInfo = document.getElementById("byeElePaticipatedCandInfoSpan");
		 if(document.getElementById("byeEleParticipatedCandidatetableId").style.display == 'none') 
		 {
		 
			$("#byeEleParticipatedCandidatetableId").slideToggle("slow");
			document.getElementById("byeEleParticipatedCandidatetableId").style.display = 'block';
			str += '<img id="minusImageId" title="Click here to Hide the Table" style="" src="images/icons/minusImage.png" onclick="byeEleParticipatedCandidateInfo()"></img>';
		 }
		 else if(document.getElementById("byeEleParticipatedCandidatetableId").style.display == 'block')
		 {
			 
			$("#byeEleParticipatedCandidatetableId").slideToggle("slow");
			document.getElementById("byeEleParticipatedCandidatetableId").style.display = 'none';
			str += '<img id="plusImgId" title="Click Here To View Andhra Pradesh Bye Election Participating Candidates" style="" src="images/icons/plusImg .png" onclick="byeEleParticipatedCandidateInfo()"></img>';
		 }
		byeEleInfo.innerHTML = str;
	 }
	 
	 function getAP2012ByeElectionInformation()
	 {
	 $.fx.speeds._default = 900;
	$("#show2012byeelectionreport").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 'auto',
								width:600,
								position:[130,130],								
								modal: true,
								title:'<font color="Navy"><b>Detailed Analysis on Andhra Pradesh 2012 Bi Elections</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#show2012byeelectionreport").dialog();
	
	
	 
	 var str=$('#byeElectionAnalysis').html();
	 $("#show2012byeelectionreport").html(str);
	 
	 }








/*
	function partywiseVotesChart()
{

var arrData = [
          ['YSRC', 18],
          ['INC',   18],
          ['TDP',      18],
          ['Others',  21],
          
        ];

var data = new google.visualization.DataTable();
	
	data.addRows(3);

	data.addColumn('string');
    data.addColumn('number', 'pieSliceText');
	data.addRows(4);

	data.setValue(0, 0, " YSRC ");
	data.setValue(0, 1, 15);
	

	data.setValue(1, 0, " INC ");
	data.setValue(1, 1, 2);
	data.setValue(2, 0, " TRS ");
	data.setValue(2, 1, 1);
	
	
	
	
	
			var chart = new google.visualization.PieChart(document.getElementById('2012seatschartDiv'));
	
	chart.draw(data,{width: 400, height: 280,legend:'right',
	legendTextStyle:{fontSize:12},title:'2012 Party Seats share in Bye Election',colors: ['#0266B4','#56EC58', '#F89BEC'],	pieSliceText: ['9','1','1']

,titleTextStyle:{fontName:'verdana',fontSize:9}});
	



      }




function partywiseSeatsChart()
{


var arrData = [
          ['TDP', 28.53],
          ['INC',    42.11],
          ['PRP',      21.08],
          ['Others',  6.01],
          
        ];

var data = new google.visualization.DataTable();
	
	data.addRows(4);

	data.addColumn('string');
    data.addColumn('number');
	data.addRows(2);

	data.setValue(0, 0, " INC ");
	data.setValue(0, 1, 16);
	data.setValue(1, 0, " PRP ");
	
	data.setValue(1, 1,  2 );
	
	
			var chart = new google.visualization.PieChart(document.getElementById('2009seatschartDiv'));
	
	chart.draw(data,{width: 400, height: 280,legend:'right', 
	legendTextStyle:{fontSize:12},title:'2009 Party Seats share in Bye Election ',colors: ['#56EC58','red']
,titleTextStyle:{fontName:'verdana',fontSize:9}});
	



      }

*/




	function partywise2012VotesChart()
{

var arrData = [
          ['YSRC', 18],
          ['INC',   18],
          ['TDP',      18],
          ['Others',  21],
          
        ];

var data = new google.visualization.DataTable();
	
	data.addRows(3);

	data.addColumn('string');
    data.addColumn('number');
	data.addRows(4);

	data.setValue(0, 0, " YSRC ");
	data.setValue(0, 1, 1287599);
	data.setValue(1, 0, " INC ");
	data.setValue(1, 1, 606758);
	data.setValue(2, 0, " TDP ");
	data.setValue(2, 1, 657106);
	data.setValue(3, 0, " Others ");
	data.setValue(3, 1, 201380);
	
	
	
			var chart = new google.visualization.PieChart(document.getElementById('2012voteschartDiv'));
	
	chart.draw(data,{width: 400, height: 280,legend:'right', 
	legendTextStyle:{fontSize:12},title:'Party Votes share in 2012 Bye Election',colors: ['#0266B4','#56EC58','#FFD700','#FF9933']
,titleTextStyle:{fontName:'verdana',fontSize:9,color:'#C71585'}});
	



      }




  


function partywise2012SeatsChart()
{


var arrData = [
          ['TDP', 28.53],
          ['INC',    42.11],
          ['PRP',      21.08],
          ['Others',  6.01],
          
        ];

var data = new google.visualization.DataTable();
	
	data.addRows(4);

	data.addColumn('string');
    data.addColumn('number');
	data.addRows(4);

	data.setValue(0, 0, " INC ");
	data.setValue(0, 1, 1074870);
	
	data.setValue(1, 0, " PRP ");
	data.setValue(1, 1, 538042);
	data.setValue(2, 0, " TDP ");
	
	data.setValue(2, 1, 728388);
	data.setValue(3, 0, " OTHERS ");
	
	data.setValue(3, 1, 209741);
	
	
	
			var chart = new google.visualization.PieChart(document.getElementById('2009voteschartDiv'));
	
	chart.draw(data,{width: 400, height: 280,legend:'right', 
	legendTextStyle:{fontSize:12},title:'Party Votes share in 2009 Election',colors: ['#56EC58','#008F01','#FFD700','#FF9933']
,titleTextStyle:{fontName:'verdana',fontSize:9,color:'#C71585'}});
	



      }

 partywise2012VotesChart();
 partywise2012SeatsChart();
// partywiseVotesChart();	
// partywiseSeatsChart();
	
</script>