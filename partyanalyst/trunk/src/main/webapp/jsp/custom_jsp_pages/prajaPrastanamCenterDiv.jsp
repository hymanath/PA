<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<style>
#votesShareSpan{background:#ED5B21;border-radius: 5px; color: #FFF; font-family: verdana; font-size: 13px; font-weight: bold; padding: 5px;}

</style>

<div id="listOfscheduleDates"></div>
<div id="scheduleDates" style="margin-top:20px;font-family:arial;font-size:13px;display:none;">
<table class="table table-striped" style="font-size: 12px;">
<tr>
		<th style="background:#21B2ED;color:#ffffff;">SNO</th>
		<th style="background:#21B2ED;color:#ffffff;">DAYWISE</th>
		<th style="background:#21B2ED;color:#ffffff;">DATE</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE  District</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE  CONSTITUENCY</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE MANDAL</th>
		<th style="background:#21B2ED;color:#ffffff;">NAME OF THE VILLAGE</th>
	</tr>
<tr><td>1</td><td>Day1</td><td>18.10.12</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>IDUPULAPAYA	</td></tr>
<tr><td>2</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>VEERANNAGATTU PALLE	</td></tr>
<tr><td>3</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>VEMPALLE	</td></tr>
<tr><td>4</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>KUMMARAM PALLE	</td></tr>
<tr><td>5</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>VEMPALLE	</td></tr>
<tr><td>6</td><td>Day2</td><td>19.10.12</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>VEMPALLE	</td></tr>
<tr><td>7</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>NANDIPALLE	</td></tr>
<tr><td>8</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>TALLAPALLE	</td></tr>
<tr><td>9</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>MUALREDDY PALLE	</td></tr>
<tr><td>10</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMPALLE</td><td>SUDDANA PALLE CROSS	</td></tr>
<tr><td>11</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>AMMIYA GARI PALLE	</td></tr>
<tr><td>12</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>CHAGALERU CROSS	</td></tr>
<tr><td>13</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>V KOTTAPALLE	</td></tr>
<tr><td>14</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>GONDI PALLE CROSS	</td></tr>
<tr><td>15</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>VEMULA	</td></tr>
<tr><td>16</td><td>	Day3</td><td>20.10.12</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>BHUMMAIAH GARI PALLE	</td></tr>
<tr><td>17</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>VELLPULA	</td></tr>
<tr><td>18</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>BESTAVARI PALLE	</td></tr>
<tr><td>19</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>VEMULA</td><td>K.VELAMAVARI PALLE CROSS	</td></tr>
<tr><td>20</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>PULIVENDULA</td><td>CHINNA RANGA PURAM	</td></tr>
<tr><td>21</td><td>Day4</td><td>21.10.12</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>PULIVENDULA</td><td>IIPATLA CROSS	</td></tr>
<tr><td>22</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>PULIVENDULA</td><td>KOMA NUTNALA	</td></tr>
<tr><td>23</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>PULIVENDULA</td><td>PEDDA KUDALA	</td></tr>
<tr><td>24</td><td>	-</td><td>-</td><td>	KADAPA</td><td>PULIVENDULA</td>	<td>LINGALA</td><td>LINGALA	</td></tr>

</table>
</div>

<div >

<div style="margin-left:10px;margin-bottom:5px;">
<span title="Click here to view Schedule Padha yatra by chandrababu" onclick="getSchedule();" style="background:#21B2ED;cursor:pointer;margin-top:20px;"
id="votesShareSpan">Click here to view Maro Praja Prasthanam Schedule</span>
</div>
<br>
<div>

<fieldset >
 <legend style="font-weight:bold;font-family:Verdana;">View Profiles</legend>
<table>
<tr><td><a href="candidateElectionResultsAction.action?candidateId=30580" title="Click here to view Y S Jagan Mohan Reddy Profile,News,Photos,Vedio Gallaries"><img width="125" height="120" src="images/candidates/Y S JAGAN MOHAN REDDY.jpg"></img></a></td>
<td><a href="candidateElectionResultsAction.action?candidateId=156693"> <img width="125" height="120" src="images/candidates/Y S VIJAYAMMA.jpg" style="margin-left:5px;" title="Click here to view YS Vijayamma Profile,News,Photos,Vedio Gallaries"></img></a></td>
  
<td><a href="partyPageAction.action?partyId=1117
" style="font-family:helvetica;" title="Click here to view 
YSR Congress Party Details"><img width="125" height="120" src="images/party_flags/YSRC.PNG" style="margin-left:5px;"></a></td></tr>
<tr><td><span><a href="candidateElectionResultsAction.action?candidateId=30580
" style="font-family:helvetica;" title="Click here to view  Y S Jagan Mohan Reddy Profile,News,Photos,Vedio Gallaries">Y S Jagan Mohan Reddy</a></span></td>
<td><span><a href="candidateElectionResultsAction.action?candidateId=156693
" style="font-family:helvetica;" title="Click here to view YS Vijayamma Profile,News,Photos,Vedio Gallaries"> YS Vijayamma </a></span></td>
<td><span><a href="partyPageAction.action?partyId=1117" title="Click here to view YSR Congress Party Details">
YSR Congress Party </a></span></td>
</tr>
 </table> 
</fieldset>
</div>
<script>
function getSchedule()
	 {
	 $.fx.speeds._default = 900;
	 $("#listOfscheduleDates").dialog({ stack: false,
                                show: "clip",
			                    hide: "clip",
							    height: 500,
								width:850,
								position:[130,130],								
								modal: true,
								title:'<font color="Navy"><b>Maro Praja Prasthanam(Padha Yathra) Schedule</b></font>',
								overlay: { opacity: 0.5, background: 'black'}
								});
	$("#listOfscheduleDates").dialog();
	var str=$('#scheduleDates').html();
	$("#listOfscheduleDates").html(str);
	}
</script>
