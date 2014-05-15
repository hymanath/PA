<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/overlib_mini.js"></script> 
<script type="text/javascript" src="js/commonUtilityScript/displayImage.js"></script>
<LINK rel="stylesheet" type="text/css" href="styles/jqueryDataTable/css/datatable.css"> 
<script src="js/jqueryDataTable/jquery.dataTables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jqueryDataTable/jquery.dataTables.columnFilter.js"></script>

<style>
.pre-year-links a{margin:5px;}
body{font:14px "Helvetica Neue",Helvetica,Arial,sans-serif;}
	.main-mbg{
		width:962px;
		margin:0px;
		border-radius:6px 6px 0px 0px;
		
	}

.electionresulttable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.electionresulttable td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;}
.electionresulttable th{background:#21B2ED;color:#fff;}
.nominationresulttable{border-collapse:collapse;font:13px Arial, Helvetica, sans-serif;}
.nominationresulttable td{border:1px solid #d3d3d3;width:15%;padding:5px 3px;color:#3d3d3d;}
.nominationresulttable tr:nth-child(odd){background:#e5e5e5;}
.nominationresulttable tr:nth-child(even){background:#f3f3f3;}
.nominationresulttable th{padding:5px;background:#489CDF;color:#fff;text-align:center;}
.buttonClass {
	background-color: background;
    border-radius: 6px 6px 6px 6px;
    color: white;
    cursor: pointer;
    font-weight: bold;
    padding: 6px;
}
.thStyle{background:#5e5e5e;color:#fff;font-weight:bold;text-align: center;}

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
.pft-sec{ border: 1px solid #DDDDDD;padding-left: 10px;padding-right: 5px;padding-bottom: 65px;}
.voterLinksCls{clear: both; padding-right: 6px;}
.voterLinksCls p{padding:0px;font-size: 13px;
    text-align: justify;margin-top:8px;padding-bottom: 10px; display: table;}
	.voterLinksCls span{font-family:arial;font-size:12px;line-height:1.5em;}
	.voterLinksCls p a{float:right;}

.hglgts {
   text-align: left;
    width: 880px;
	float :left;
}

.hglgts li {
    -moz-font-feature-settings: normal;
    -moz-font-language-override: normal;
    -x-system-font: none;
    background-attachment: scroll;
    background-clip: border-box;
    background-color: transparent;
   /* background-image: url("http://static.ibnlive.in.com/ibnlive/pix/ibnhome/blts_c.jpg");*/
	background-image:url("images/icons/diamond.png");
    background-origin: padding-box;
    background-position: 0 8px;
    background-repeat: no-repeat;
    background-size: auto auto;
    color: #333333;
    font-family: verdana;
    font-size: 14px;
    font-size-adjust: none;
    font-stretch: normal;
    font-style: normal;
    font-variant: normal;
    font-weight: normal;
	line-height: 20px;
    list-style-image: none;
    list-style-position: outside;
    list-style-type: none;
    margin-bottom: 15px;
    padding-left: 15px;
}
.importantPersonsDivClass{

	height:196px;
	width:126px;
	float:left;
	background:#fff;
	padding: 11px;
	margin:3px;
	border:1px solid #c3c3c3;
	border-radius:3px;

}


.alignCenter{
	text-align:center;
}

.leadStatusClass{

	color: green;
	font-weight: bold;
}

.candidateNameClass{

color: #21B2ED;
font-weight: bold;
font-family:arial;
font-size:11px;
/*width: 200px;*/
}
table.gujaratTableDiv {background-color:transparent;border-collapse:collapse;width:100%;}
table.gujaratTableDiv th, table.gujaratTableDiv td {text-align:center;}
table.gujaratTableDiv th {}
table.gujaratTableDiv td:first-child {width:50%;}

.textalignright{
  text-align:right;
}
.textalignclass{
  text-align:center;
}

#GujaratResultBody{display:inline-block;background:#EDF9FF;width:100%;padding:4px;}
.popover {
     padding: 5px;
    position: relative;
	display:inline-block;
    }
.popover-title {
    background-color: #777;
    border-bottom: 1px solid #f8f8f8;
    border-radius: 3px 3px 0 0;
    line-height: 1;
    padding: 9px 15px;
	color:#fff;

}	
.popover-content{	height:115px;
	position:relative;}
.popover-content  a{position:absolute;bottom:5px;right:5px;}
.mainwrapper {background:#5d5d5d;}


.parlResultTable{background:#ffffff;}
.parlResultTable tbody tr.bodyRows:nth-child(even){
	background:#F7FAFD;
}

.exitPolls tbody tr:nth-child(even){
	background:#F7FAFD;
}
.exitPolls tr{font-family: Tahoma;font-size: 12px;color:black;}
.prevResults a{margin:10px;}

</style>


<div id='impTxt'></div>
<!--<input type="button" class="btn btn-success" value="TestAjax" onClick="getImportantCandidatesInfo()"/>-->

<div class="parliamentResultsDiv" style="background:#ffffff;float:left;width:975px;"></div>
<div class="partyWiseResultDiv" style="background:#ffffff;float:left;width:975px;"></div>
<div style="background:#ffffff;float:left;width:975px;"> 
	
		
		<h2 class='offset2' style="margin-bottom:5px;color:#27AFA6;">Exit Polls From Different Sources</h2>
		<table width='800' cellspacing='0' cellpadding='2' border='0' class='exitPolls offset1'>
			<tbody>
				<tr style='font-weight:bold;'>
					<td style='border-bottom:1px solid #B0BDDA;'>CHANNEL</td>
					<td style='border-bottom:1px solid #B0BDDA;'>NDA</td>
					<td style='border-bottom:1px solid #B0BDDA;'>UPA</td>
					<td style='border-bottom:1px solid #B0BDDA;'>OTHERS</td>
				</tr>
			
				<tr>
					<td>Today's Chanakya</td>
					<td>340</td>
					<td>70</td>
					<td>133</td>
				</tr>
				<tr>
					<td>Times Now</td>
					<td>249</td>
					<td>148</td>
					<td>146</td>
				</tr>
				<tr>
					<td>CNN-IBN</td>
					<td>270 - 282</td>
					<td>92 - 102</td>
					<td>150 - 160</td>
				</tr>
				<tr>
					<td>HeadLines Today</td>
					<td>272(+/- 11)</td>
					<td>115(+/- 5)</td>
					<td>156</td>
				</tr>
				<tr>
					<td>ABP News</td>
					<td>281</td>
					<td>97</td>
					<td>161</td>
				</tr>
				<tr>
					<td>C-Voter</td>
					<td>289</td>
					<td>101</td>
					<td>153</td>
				</tr>
				<tr>
					<td>Aaj tdak</td>
					<td>272</td>
					<td>115</td>
					<td> - </td>
				</tr>
				<tr>
					<td>India TV</td>
					<td>289</td>
					<td>101</td>
					<td> - </td>
				</tr>
			</tbody>
		</table>
	<div class="span12" style="margin-left:102px;margin-top:25px;">
		<div class="selectHeading" style="width:258px;float:left;">
	<span class="selectDivStyle" style="width:251px;">Parliament Constituencies </span>
	<span style="margin-left: 12px;">Know About Your Parliament Constituency</span>
<select class="selectBoxWidth" id="constituency_parliament" name="constituency">
<option value="0">Select Constituency</option>
<option value="461">Adilabad</option>
<option value="988">Agra</option>
<option value="601">Ahmedabad East</option>
<option value="602">Ahmedabad West</option>
<option value="779">Ahmednagar</option>
<option value="901">Ajmer</option>
<option value="989">Akbarpur</option>
<option value="780">Akola</option>
<option value="712">Alappuzha</option>
<option value="713">Alathur</option>
<option value="990">Aligarh</option>
<option value="1084">Alipurduars</option>
<option value="991">Allahabad</option>
<option value="1078">Almora</option>
<option value="902">Alwar</option>
<option value="463">Amalapuram</option>
<option value="637">Ambala</option>
<option value="992">Ambedkar Nagar</option>
<option value="993">Amethi</option>
<option value="781">Amravati</option>
<option value="603">Amreli</option>
<option value="883">Amritsar</option>
<option value="994">Amroha</option>
<option value="464">Anakapalli</option>
<option value="604">Anand</option>
<option value="884">Anandpur Sahib</option>
<option value="465">Anantapur</option>
<option value="656">Anantnag</option>
<option value="462">Andaman &amp; Nicobar Islands</option>
<option value="995">Aonla</option>
<option value="931">Arakkonam</option>
<option value="466">Araku</option>
<option value="1085">Arambagh</option>
<option value="932">Arani</option>
<option value="528">Araria</option>
<option value="529">Arrah</option>
<option value="512">Arunachal East</option>
<option value="513">Arunachal West</option>
<option value="1086">Asansol</option>
<option value="858">Aska</option>
<option value="715">Attingal</option>
<option value="530">Aurangabad</option>
<option value="778">Aurangabad</option>
<option value="514">Autonomous District</option>
<option value="996">Azamgarh</option>
<option value="997">Badaun</option>
<option value="676">Bagalkot</option>
<option value="998">Baghpat</option>
<option value="1087">Baharampur</option>
<option value="999">Bahraich</option>
<option value="746">Balaghat</option>
<option value="859">Balasore</option>
<option value="1000">Ballia</option>
<option value="1088">Balurghat</option>
<option value="605">Banaskantha</option>
<option value="1002">Banda</option>
<option value="677">Bangalore Central</option>
<option value="678">Bangalore North</option>
<option value="679">Bangalore Rural</option>
<option value="680">Bangalore South</option>
<option value="1089">Bangaon</option>
<option value="533">Banka</option>
<option value="1090">Bankura</option>
<option value="1003">Bansgaon</option>
<option value="903">Banswara</option>
<option value="467">Bapatla</option>
<option value="1004">Bara Banki</option>
<option value="782">Baramati</option>
<option value="657">Baramulla</option>
<option value="1091">Barasat</option>
<option value="1093">Bardhaman Durgapur</option>
<option value="1092">Bardhaman Purba</option>
<option value="606">Bardoli</option>
<option value="1005">Bareilly</option>
<option value="860">Bargarh</option>
<option value="904">Barmer</option>
<option value="515">Barpeta</option>
<option value="1094">Barrackpur</option>
<option value="1095">Basirhat</option>
<option value="580">Bastar</option>
<option value="1006">Basti</option>
<option value="885">Bathinda</option>
<option value="783">Beed</option>
<option value="535">Begusarai</option>
<option value="681">Belgaum</option>
<option value="682">Bellary</option>
<option value="1096">Berhampore</option>
<option value="861">Berhampur</option>
<option value="747">Betul</option>
<option value="1007">Bhadohi</option>
<option value="862">Bhadrak</option>
<option value="537">Bhagalpur</option>
<option value="785">Bhandara Gondiya</option>
<option value="906">Bharatpur</option>
<option value="608">Bharuch</option>
<option value="886">Bhatinda</option>
<option value="609">Bhavnagar</option>
<option value="907">Bhilwara</option>
<option value="748">Bhind</option>
<option value="786">Bhiwandi</option>
<option value="639">Bhiwani Mahendragarh</option>
<option value="469">Bhongir</option>
<option value="749">Bhopal</option>
<option value="863">Bhubaneswar</option>
<option value="683">Bidar</option>
<option value="684">Bijapur</option>
<option value="1008">Bijnor</option>
<option value="908">Bikaner</option>
<option value="581">Bilaspur</option>
<option value="1097">Birbhum</option>
<option value="1098">Bishnupur</option>
<option value="864">Bolangir</option>
<option value="1099">Bolpur</option>
<option value="1011">Bulandshahr</option>
<option value="787">Buldhana</option>
<option value="539">Buxar</option>
<option value="719">Chalakudy</option>
<option value="685">Chamarajanagar</option>
<option value="1013">Chandauli</option>
<option value="579">Chandigarh</option>
<option value="848">Chandni Chowk</option>
<option value="788">Chandrapur</option>
<option value="662">Chatra</option>
<option value="934">Chennai Central</option>
<option value="935">Chennai North</option>
<option value="936">Chennai South</option>
<option value="471">Chevella</option>
<option value="750">Chhindwara</option>
<option value="612">Chhota Udaipur</option>
<option value="937">Chidambaram</option>
<option value="686">Chikballapur</option>
<option value="687">Chikkodi</option>
<option value="689">Chitradurga</option>
<option value="472">Chittoor</option>
<option value="909">Chittorgarh</option>
<option value="910">Churu</option>
<option value="938">Coimbatore</option>
<option value="1105">Cooch Behar</option>
<option value="939">Cuddalore</option>
<option value="865">Cuttack</option>
<option value="594">Dadra &amp; Nagar Haveli</option>
<option value="613">Dahod</option>
<option value="690">Dakshina Kannada</option>
<option value="595">Daman &amp; Diu</option>
<option value="751">Damoh</option>
<option value="541">Darbhanga</option>
<option value="1106">Darjeeling</option>
<option value="911">Dausa</option>
<option value="691">Davanagere</option>
<option value="1014">Deoria</option>
<option value="752">Dewas</option>
<option value="663">Dhanbad</option>
<option value="753">Dhar</option>
<option value="940">Dharmapuri</option>
<option value="692">Dharwad</option>
<option value="1015">Dhaurahra</option>
<option value="867">Dhenkanal</option>
<option value="516">Dhubri</option>
<option value="791">Dhule</option>
<option value="1107">Diamond Harbour</option>
<option value="517">Dibrugarh</option>
<option value="941">Dindigul</option>
<option value="792">Dindori</option>
<option value="1016">Domariaganj</option>
<option value="1108">Dum Dum</option>
<option value="664">Dumka</option>
<option value="582">Durg</option>
<option value="850">East Delhi</option>
<option value="473">Eluru</option>
<option value="721">Ernakulam</option>
<option value="942">Erode</option>
<option value="1017">Etah</option>
<option value="1018">Etawah</option>
<option value="1019">Faizabad</option>
<option value="640">Faridabad</option>
<option value="887">Faridkot</option>
<option value="1020">Farrukhabad</option>
<option value="888">Fatehgarh Sahib</option>
<option value="1021">Fatehpur</option>
<option value="1022">Fatehpur Sikri</option>
<option value="1023">Firozabad</option>
<option value="889">Firozpur</option>
<option value="794">Gadchiroli Chimur</option>
<option value="616">Gandhinagar</option>
<option value="912">Ganganagar</option>
<option value="1079">Garhwal</option>
<option value="518">Gauhati</option>
<option value="1024">Gautam Buddha Nagar</option>
<option value="542">Gaya</option>
<option value="1110">Ghatal</option>
<option value="1026">Ghaziabad</option>
<option value="1027">Ghazipur</option>
<option value="1028">Ghosi</option>
<option value="665">Giridih</option>
<option value="666">Godda</option>
<option value="1029">Gonda</option>
<option value="543">Gopalganj</option>
<option value="1030">Gorakhpur</option>
<option value="695">Gulbarga</option>
<option value="754">Guna</option>
<option value="474">Guntur</option>
<option value="890">Gurdaspur</option>
<option value="641">Gurgaon</option>
<option value="755">Gwalior</option>
<option value="544">Hajipur</option>
<option value="650">Hamirpur</option>
<option value="651">Hamirpur</option>
<option value="1032">Hardoi</option>
<option value="1080">Hardwar</option>
<option value="696">Hassan</option>
<option value="1033">Hathras</option>
<option value="795">Hatkanangle</option>
<option value="697">Haveri</option>
<option value="667">Hazaribagh</option>
<option value="476">Hindupur</option>
<option value="796">Hingoli</option>
<option value="642">Hissar</option>
<option value="1111">Hooghly</option>
<option value="756">Hoshangabad</option>
<option value="891">Hoshiarpur</option>
<option value="1112">Howrah</option>
<option value="477">Hyderabad</option>
<option value="722">Idukki</option>
<option value="757">Indore</option>
<option value="842">Inner Manipur</option>
<option value="758">Jabalpur</option>
<option value="1113">Jadavpur</option>
<option value="868">Jagatsinghpur</option>
<option value="545">Jahanabad</option>
<option value="913">Jaipur</option>
<option value="914">Jaipur Rural</option>
<option value="869">Jajpur</option>
<option value="892">Jalandhar</option>
<option value="1034">Jalaun</option>
<option value="798">Jalgaon</option>
<option value="799">Jalna</option>
<option value="915">Jalore</option>
<option value="1114">Jalpaiguri</option>
<option value="658">Jammu</option>
<option value="618">Jamnagar</option>
<option value="668">Jamshedpur</option>
<option value="546">Jamui</option>
<option value="1115">Jangipur</option>
<option value="584">Janjgir Champa</option>
<option value="1036">Jaunpur</option>
<option value="917">Jhalawar Baran</option>
<option value="547">Jhanjharpur</option>
<option value="1037">Jhansi</option>
<option value="1116">Jhargram</option>
<option value="918">Jhunjhunu</option>
<option value="919">Jodhpur</option>
<option value="519">Jorhat</option>
<option value="1117">Joynagar</option>
<option value="619">Junagadh</option>
<option value="620">Kachchh</option>
<option value="478">Kadapa</option>
<option value="1038">Kairana</option>
<option value="1039">Kaiserganj</option>
<option value="479">Kakinada</option>
<option value="870">Kalahandi</option>
<option value="520">Kaliabor</option>
<option value="944">Kallakurichi</option>
<option value="800">Kalyan</option>
<option value="945">Kancheepuram</option>
<option value="871">Kandhamal</option>
<option value="652">Kangra</option>
<option value="585">Kanker</option>
<option value="1040">Kannauj</option>
<option value="946">Kanniyakumari</option>
<option value="723">Kannur</option>
<option value="1041">Kanpur</option>
<option value="548">Karakat</option>
<option value="920">Karauli Dholpur</option>
<option value="521">Karimganj</option>
<option value="480">Karimnagar</option>
<option value="643">Karnal</option>
<option value="947">Karur</option>
<option value="724">Kasaragod</option>
<option value="549">Katihar</option>
<option value="1042">Kaushambi</option>
<option value="872">Kendrapara</option>
<option value="873">Keonjhar</option>
<option value="894">Khadoor Sahib</option>
<option value="550">Khagaria</option>
<option value="760">Khajuraho</option>
<option value="481">Khammam</option>
<option value="761">Khandwa</option>
<option value="762">Khargone</option>
<option value="623">Kheda</option>
<option value="1044">Kheri</option>
<option value="669">Khunti</option>
<option value="551">Kishanganj</option>
<option value="670">Kodarma</option>
<option value="522">Kokrajhar</option>
<option value="700">Kolar</option>
<option value="804">Kolhapur</option>
<option value="1120">Kolkata Dakshin</option>
<option value="1121">Kolkata Uttar</option>
<option value="725">Kollam</option>
<option value="701">Koppal</option>
<option value="874">Koraput</option>
<option value="586">Korba</option>
<option value="921">Kota</option>
<option value="726">Kottayam</option>
<option value="727">Kozhikode</option>
<option value="948">Krishnagiri</option>
<option value="1122">Krishnanagar</option>
<option value="1118">Kunthi</option>
<option value="482">Kurnool</option>
<option value="644">Kurukshetra</option>
<option value="1046">Kushi Nagar</option>
<option value="659">Ladakh</option>
<option value="523">Lakhimpur</option>
<option value="745">Lakshadweep</option>
<option value="1047">Lalganj</option>
<option value="806">Latur</option>
<option value="671">Lohardaga</option>
<option value="1048">Lucknow</option>
<option value="895">Ludhiana</option>
<option value="1049">Machhlishahr</option>
<option value="483">Machilipatnam</option>
<option value="807">Madha</option>
<option value="552">Madhepura</option>
<option value="553">Madhubani</option>
<option value="952">Madurai</option>
<option value="484">Mahabubabad</option>
<option value="554">Maharajganj</option>
<option value="587">Mahasamund</option>
<option value="485">Mahbubnagar</option>
<option value="625">Mahesana</option>
<option value="1050">Mainpuri</option>
<option value="728">Malappuram</option>
<option value="1124">Maldaha Dakshin</option>
<option value="1125">Maldaha Uttar</option>
<option value="486">Malkajgiri</option>
<option value="653">Mandi</option>
<option value="763">Mandla</option>
<option value="764">Mandsour</option>
<option value="702">Mandya</option>
<option value="524">Mangaldoi</option>
<option value="1051">Mathura</option>
<option value="1126">Mathurapur</option>
<option value="809">Maval</option>
<option value="730">Mavelikara</option>
<option value="953">Mayiladuthurai</option>
<option value="875">Mayurbhanj</option>
<option value="487">Medak</option>
<option value="1127">Medinipur</option>
<option value="1052">Meerut</option>
<option value="1053">Mirzapur</option>
<option value="1054">Misrikh</option>
<option value="846">Mizoram</option>
<option value="1055">Mohanlalganj</option>
<option value="1056">Moradabad</option>
<option value="765">Morena</option>
<option value="810">Mumbai North</option>
<option value="811">Mumbai North Central</option>
<option value="812">Mumbai North East</option>
<option value="813">Mumbai North West</option>
<option value="814">Mumbai South</option>
<option value="815">Mumbai South Central</option>
<option value="557">Munger</option>
<option value="1129">Murshidabad</option>
<option value="1057">Muzaffarnagar</option>
<option value="558">Muzaffarpur</option>
<option value="704">Mysore</option>
<option value="876">Nabarangpur</option>
<option value="847">Nagaland</option>
<option value="954">Nagapattinam</option>
<option value="489">Nagarkurnool</option>
<option value="922">Nagaur</option>
<option value="1058">Nagina</option>
<option value="816">Nagpur</option>
<option value="1082">Nainital Udhamsingh Nagar</option>
<option value="559">Nalanda</option>
<option value="490">Nalgonda</option>
<option value="956">Namakkal</option>
<option value="817">Nanded</option>
<option value="818">Nandurbar</option>
<option value="491">Nandyal</option>
<option value="493">Narasaraopet</option>
<option value="494">Narsapuram</option>
<option value="819">Nashik</option>
<option value="627">Navsari</option>
<option value="560">Nawada</option>
<option value="495">Nellore</option>
<option value="852">New Delhi</option>
<option value="957">Nilgiris</option>
<option value="496">Nizamabad</option>
<option value="853">North East Delhi</option>
<option value="597">North Goa</option>
<option value="854">North West Delhi</option>
<option value="525">Nowgong</option>
<option value="497">Ongole</option>
<option value="820">Osmanabad</option>
<option value="843">Outer Manipur</option>
<option value="734">Palakkad</option>
<option value="672">Palamu</option>
<option value="958">Palani</option>
<option value="821">Palghar</option>
<option value="923">Pali</option>
<option value="628">Panchmahal</option>
<option value="823">Parbhani</option>
<option value="561">Paschim Champaran</option>
<option value="562">Pataliputra</option>
<option value="629">Patan</option>
<option value="736">Pathanamthitta</option>
<option value="896">Patiala</option>
<option value="564">Patna Sahib</option>
<option value="499">Peddapalli</option>
<option value="959">Perambalur</option>
<option value="1060">Phulpur</option>
<option value="1061">Pilibhit</option>
<option value="961">Pollachi</option>
<option value="737">Ponnani</option>
<option value="630">Porbandar</option>
<option value="1062">Pratapgarh</option>
<option value="882">Puducherry</option>
<option value="824">Pune</option>
<option value="879">Puri</option>
<option value="565">Purnia</option>
<option value="1132">Purulia</option>
<option value="566">Purvi Champaran</option>
<option value="1063">Rae Bareli</option>
<option value="705">Raichur</option>
<option value="825">Raigad</option>
<option value="1133">Raiganj</option>
<option value="588">Raigarh</option>
<option value="589">Raipur</option>
<option value="500">Rajahmundry</option>
<option value="501">Rajampet</option>
<option value="766">Rajgarh</option>
<option value="631">Rajkot</option>
<option value="673">Rajmahal</option>
<option value="590">Rajnandgaon</option>
<option value="924">Rajsamand</option>
<option value="963">Ramanathapuram</option>
<option value="1064">Rampur</option>
<option value="827">Ramtek</option>
<option value="1134">Ranaghat</option>
<option value="674">Ranchi</option>
<option value="767">Ratlam</option>
<option value="829">Ratnagiri Sindhudurg</option>
<option value="830">Raver</option>
<option value="768">Rewa</option>
<option value="1065">Robertsganj</option>
<option value="646">Rohtak</option>
<option value="632">Sabarkantha</option>
<option value="769">Sagar</option>
<option value="1066">Saharanpur</option>
<option value="965">Salem</option>
<option value="1068">Salempur</option>
<option value="569">Samastipur</option>
<option value="880">Sambalpur</option>
<option value="1069">Sambhal</option>
<option value="831">Sangli</option>
<option value="899">Sangrur</option>
<option value="1070">Sant Kabir Nagar</option>
<option value="570">Saran</option>
<option value="592">Sarguja</option>
<option value="571">Sasaram</option>
<option value="832">Satara</option>
<option value="770">Satna</option>
<option value="502">Secunderabad</option>
<option value="772">Shahdol</option>
<option value="1072">Shahjahanpur</option>
<option value="572">Sheohar</option>
<option value="844">Shillong</option>
<option value="654">Shimla</option>
<option value="706">Shimoga</option>
<option value="833">Shirdi</option>
<option value="834">Shirur</option>
<option value="835">Sholapur</option>
<option value="1073">Shrawasti</option>
<option value="774">Sidhi</option>
<option value="927">Sikar</option>
<option value="930">Sikkim</option>
<option value="526">Silchar</option>
<option value="675">Singhbhum</option>
<option value="647">Sirsa</option>
<option value="573">Sitamarhi</option>
<option value="1074">Sitapur</option>
<option value="966">Sivaganga</option>
<option value="574">Siwan</option>
<option value="836">Solapur</option>
<option value="648">Sonepat</option>
<option value="649">Sonipat</option>
<option value="856">South Delhi</option>
<option value="599">South Goa</option>
<option value="1136">Sreerampur</option>
<option value="504">Srikakulam</option>
<option value="660">Srinagar</option>
<option value="968">Sriperumbudur</option>
<option value="1075">Sultanpur</option>
<option value="881">Sundargarh</option>
<option value="575">Supaul</option>
<option value="633">Surat</option>
<option value="634">Surendranagar</option>
<option value="593">Surguja</option>
<option value="1137">Tamluk</option>
<option value="1083">Tehri Garhwal</option>
<option value="969">Tenkasi</option>
<option value="527">Tezpur</option>
<option value="837">Thane</option>
<option value="970">Thanjavur</option>
<option value="971">Theni</option>
<option value="739">Thiruvananthapuram</option>
<option value="972">Thoothukkudi</option>
<option value="740">Thrissur</option>
<option value="775">Tikamgarh</option>
<option value="976">Tiruchirappalli</option>
<option value="977">Tirunelveli</option>
<option value="506">Tirupati</option>
<option value="979">Tiruppur</option>
<option value="980">Tiruvallur</option>
<option value="981">Tiruvannamalai</option>
<option value="40707">Tonk Sawai Madhopur</option>
<option value="986">Tripura East</option>
<option value="987">Tripura West</option>
<option value="707">Tumkur</option>
<option value="845">Tura</option>
<option value="929">Udaipur</option>
<option value="661">Udhampur</option>
<option value="709">Udupi Chikmagalur</option>
<option value="576">Ujiarpur</option>
<option value="776">Ujjain</option>
<option value="1138">Uluberia</option>
<option value="1076">Unnao</option>
<option value="710">Uttara Kannada</option>
<option value="743">Vadakara</option>
<option value="635">Vadodara</option>
<option value="577">Vaishali</option>
<option value="578">Valmiki Nagar</option>
<option value="636">Valsad</option>
<option value="1077">Varanasi</option>
<option value="983">Vellore</option>
<option value="777">Vidisha</option>
<option value="507">Vijayawada</option>
<option value="984">Viluppuram</option>
<option value="985">Virudhunagar</option>
<option value="508">Visakhapatnam</option>
<option value="509">Vizianagaram</option>
<option value="510">Warangal</option>
<option value="838">Wardha</option>
<option value="744">Wayanad</option>
<option value="857">West Delhi</option>
<option value="841">Yavatmal Washim</option>
<option value="511">Zahirabad</option>

</select>
<div id="alertMessage_cons_Himachal Pradesh"></div>

<div class="view-results"><a onclick="navigateToConstituencyPageFrmSpeclPge('constituency_parliament','alertMessage_cons_Himachal Pradesh')" href="javascript:{}">view constituency</a></div>
</div>
		
		<div style="width:373px;margin-left:10px;float:left;" class="prevResults selectHeading">
		
		<span class="selectDivStyle" style="width:365px;">Previous Parliament Elections Results </span>
			

<a  href="electionDetailsReportAction.action?electionId=17&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=2009" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2009</a>


<a  href="electionDetailsReportAction.action?electionId=18&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=2004" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 2004</a>


<a href="electionDetailsReportAction.action?electionId=19&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1999" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png">1999</a>

<a href="electionDetailsReportAction.action?electionId=20&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1998" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png">1998</a>

<a href="electionDetailsReportAction.action?electionId=51&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1996" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png">1996</a>

<a href="electionDetailsReportAction.action?electionId=52&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1991" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png">1991</a>


<a  href="electionDetailsReportAction.action?electionId=74&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1989" class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1989</a>


<a  href="electionDetailsReportAction.action?electionId=113&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1984" class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1984</a>


<a href="electionDetailsReportAction.action?electionId=117&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1980"class="btn btn-primary btn-mini"> <img src="images/icons/diamond.png"> 1980</a>


<a  href="electionDetailsReportAction.action?electionId=118&stateID=1&stateName=Select State&electionType=Parliament&electionTypeId=1&year=1977"class="btn btn-primary btn-mini"><img src="images/icons/diamond.png"> 1977</a>

		</div>
		
	</div>
	
	<div class="span12" style="margin-left:80px;">
		
		<div class="span5">
		<h4 class="btn btn-success upaAlliances" style="margin:10px;"><span>Hide UPA And Alliances</span></h4>
		<table class="table table-striped UPAAllianceTable breadcrumb">
		<thead>
			<tr>
				<th>Party</th>
				<th>Seats Contested</th>
			</tr>
		</thead>
		<tbody>
<tr>
<td>Indian National Congress</td>
<td align="center">462</td>
</tr>
<tr>
<td>Rashtriya Janata Dal</td>
<td align="center">28</td>
</tr>
<tr>
<td>Nationalist Congress Party</td>
<td align="center">23</td>
</tr>
<tr>
<td>Rashtriya Lok Dal</td>
<td align="center">8</td>
</tr>
<tr>
<td>Jharkhand Mukti Morcha</td>
<td align="center">4</td>
</tr>
<tr>
<td>Jammu &amp; Kashmir National Conference</td>
<td align="center">3</td>
</tr>
<tr>
<td>Mahan Dal</td>
<td align="center">3</td>
</tr>
<tr>
<td>Indian Union Muslim League</td>
<td align="center">2</td>
</tr>
<tr>
<td>Socialist Janata (Democratic)</td>
<td align="center">1</td>
</tr>
<tr>
<td>Kerala Congress (M)</td>
<td align="center">1</td>
</tr>
<tr>
<td>Revolutionary Socialist Party</td>
<td align="center">1</td>
</tr>
<tr>
<td>Bodoland People's Front</td>
<td align="center">1</td>
</tr>
<tr>
<td>Communist Party of India</td>
<td align="center">1</td>
</tr>

</tbody><tfoot><tr>
<th>United Progressive Alliance</th>
<th>538</th>
</tr></tfoot></table>
		</div>
		<div class="span5">
			<h4 class="btn btn-success ndaAlliances " style="margin:10px;"><span> Hide NDA And Alliances </span></h4>
<table class="table table-striped NDAAllianceTable breadcrumb">
<thead><tr>
<th>Party</th>
<th>Seats Contested</th>
</tr></thead><tbody>
<tr>
<td>Bharatiya Janata Party</td>
<td align="center">427</td>
</tr>
<tr>
<td>Telugu Desam Party</td>
<td align="center">30</td>
</tr>
<tr>
<td>Shiv Sena</td>
<td align="center">20</td>
</tr>
<tr>
<td>Desiya Murpokku Dravida Kazhagam</td>
<td align="center">14</td>
</tr>
<tr>
<td>Shiromani Akali Dal</td>
<td align="center">10</td>
</tr>
<tr>
<td>Pattali Makkal Katchi</td>
<td align="center">8</td>
</tr>
<tr>
<td>Marumalarchi Dravida Munnetra Kazhagam</td>
<td align="center">7</td>
</tr>
<tr>
<td>Lok Janshakti Party</td>
<td align="center">7</td>
</tr>
<tr>
<td>Rashtriya Lok Samata Party</td>
<td align="center">3</td>
</tr>
<tr>
<td>Apna Dal</td>
<td align="center">2</td>
</tr>
<tr>
<td>Haryana Janhit Congress</td>
<td align="center">2</td>
</tr>
<tr>
<td>Swabhimani Paksha</td>
<td align="center">2</td>
</tr>
<tr>
<td>Indhiya Jananayaga Katchi</td>
<td align="center">1</td>
</tr>
<tr>
<td>Kongunadu Makkal Desia Katchi</td>
<td align="center">1</td>
</tr>
<tr>
<td>All India N.R. Congress</td>
<td align="center">1</td>
</tr>
<tr>
<td>Republican Party of India (Athvale)</td>
<td align="center">1</td>
</tr>
<tr>
<td>Rashtriya Samaj Paksha</td>
<td align="center">1</td>
</tr>
<tr>
<td>Revolutionary Socialist Party (Bolshevik)</td>
<td align="center">1</td>
</tr>
<tr>
<td>Kerala Congress (Nationalist)</td>
<td align="center">1</td>
</tr>
<tr>
<td>National People's Party (India)</td>
<td align="center">1</td>
</tr>
<tr>
<td>Naga People's Front</td>
<td align="center">1</td>
</tr>
<tr>
<td>Mizo National Front</td>
<td align="center">1</td>
</tr>

</tbody><tfoot><tr>
<th>National Democratic Alliance</th>
<th>542</th>
</tr></tfoot></table>
		</div>
	</div>
</div>

<div id="genderInfoDiv">
<div id="genderAnalysisDiv"></div></div>

<script type="text/javascript">


$(".upaAlliances").click(function(){
	$(".UPAAllianceTable").toggle();
	  $(this).text(function(i, text){
          return text === "Show UPA Alliances" ? "Hide UPA Alliances" : "Show UPA Alliances";
      })
});
$(".ndaAlliances").click(function(){
	$(".NDAAllianceTable").toggle();
	 $(this).text(function(i, text){
          return text === "Show NDA Alliances" ? "Hide NDA Alliances" : "Show NDA Alliances";
      })
});

getStateWideParliamentsSummary();

function getStateWideParliamentsSummary()
{
	var jsObj = {
				task:"getStateWideParliamentLiveResults"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getStateWideParliamentLiveResults.action?"+param;
	
	callAjax(jsObj,url);
}

getPartyWiseWonLeadCountInLive();

function getPartyWiseWonLeadCountInLive()
{
	var jsObj = {
				task:"getPartyWiseWonLeadCountInLive"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getPartyWiseWonLeadCountInLive.action?"+param;
	
	callAjax(jsObj,url);
}



google.load("visualization", "1", {packages:["corechart"]});

$(document).ready(function() {
	//$('#selectedElectionYears').selectmenu('refresh');
  getElectionInfo();
  getDistrictWiseElectionResults();
  getWonLeadCandidates();
   
  $("#dynamicText").insertBefore($("#dynaUpload"));
  $("#dynamicText").css("margin","10px 0px");
});

function getDistrictWiseElectionResults(){
  var districtId = $("#selectDistrictWise").val();
  if(districtId == 0)
     return;
  var jsObj = {
				electionId:216,
	            time:new Date().getTime(),
				districtId:districtId,
				task:"getDistrictWiseLiveResults"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getDistrictWiseLiveResultsAction.action?"+param;
	callAjax(jsObj,url);

}
function getWonLeadCandidates(){
  
  var jsObj = {
				electionId:216,
	            time:new Date().getTime(),
				task:"getWonLeadResults"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getDistrictWiseLiveResultsAction.action?"+param;
	callAjax(jsObj,url);

}
function getGenderInfo(selectedYear,elecYearId)
{
	var jsObj = {
				elecYearId:elecYearId,
	            time:new Date().getTime(),
				electionId:selectedYear,
				task:"getPartyGenderInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/electionDetailsReportWithGenderAction.action?"+param;
	callAjax(jsObj,url);
}
function getElectionInfo()
{
	var jsObj = {
				electionId:216,
	            time:new Date().getTime(),
				task:"getPartyElectionInfo"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "<%=request.getContextPath()%>/getLiveResultsAction.action?"+param;
	callAjax(jsObj,url);
}

function callAjax(jsObj,url){
		var myResults;
 					
 		var callback = {			
 		               success : function( o ) {
							try {												
									if(o.responseText)
										myResults = YAHOO.lang.JSON.parse(o.responseText);
									if(jsObj.task =="getPartyGenderInfo")
									{
										buildGenderCountResultsDataTable(myResults,jsObj.elecYearId);
									}
									else if(jsObj.task =="getHighLights")
								    {
										buildSpecialPageHightLights(myResults);
								    }
									else if(jsObj.task =="getPartyElectionInfo")
									{
										buildGujaratElectionResult(myResults);
										
									}
									else if(jsObj.task == "getImportantCandidatesInfo")
									{
									
										buildImportantCnadidatesData(myResults)
									}
									else if(jsObj.task == "getDistrictWiseLiveResults")
									{
									
										buildDistrictWiseLiveResults(myResults)
									}
									else if(jsObj.task == "getWonLeadResults")
									{
									
										buildWonLeadResults(myResults)
									}
									else if(jsObj.task == "getStateWideParliamentLiveResults")
									{
										buildStateWideParliaments(myResults);
									}
									else if(jsObj.task == "getPartyWiseWonLeadCountInLive")
									{
										buildPartyWideWonLeadCount(myResults);
									}
								}
							catch (e) {   
							   	//alert("Invalid JSON result" + e);   
						}  
		               },
		               scope : this,
		               failure : function( o ) {
		                			//alert( "Failed to load result" + o.status + " " + o.statusText);
		                         }
		               };

		YAHOO.util.Connect.asyncRequest('GET', url, callback);
}

function buildPartyWideWonLeadCount(results){
	var str = "";
		str += "<h2 class='offset3' style='margin-bottom:5px;margin-top:10px;color:#27AFA6;'>Party Wise Won/Lead Counts</h2>";
		str +="<table class='parlResultTable offset1' width='800' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody style='font-family: Tahoma;font-size: 12px;'>";
			str+="<tr style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #B0BDDA;'>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> PARTY </td>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> ALLIANCE </td>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> WON </td>";
				str+="<td style='border-bottom:1px solid #B0BDDA;'> LEAD </td>";
			str+="</tr>";
			for(var i in results.statesList){
				str+="<tr class='bodyRows' style='color:black;'>";
					var path = "images/party_flags/"+results.statesList[i].party+"01.jpg";
					
					str+="<td style='border-bottom:1px solid #B0BDDA;height:25px;'><img src="+path+" alt="+results.statesList[i].party+"></td>";
					str+="<td style='border-bottom:1px solid #B0BDDA;'>"+results.statesList[i].allianceGroup+"</td>";
					if(results.statesList[i].partyWonCount == null){
						str+="<td style='border-bottom:1px solid #B0BDDA;'> - </td>";
					}else{
						str+="<td style='border-bottom:1px solid #B0BDDA;'>"+results.statesList[i].partyWonCount+"</td>";
					}
					if(results.statesList[i].partyLeadCount == null){
						str+="<td style='border-bottom:1px solid #B0BDDA;'> - </td>";
					}else{
						str+="<td style='border-bottom:1px solid #B0BDDA;'>"+results.statesList[i].partyLeadCount+"</td>";
					}
				str+="</tr>";
			}
			
		str+="</tbody>";
	str+="</table>";
	
	$(".partyWiseResultDiv").html(str);
	
}

function buildStateWideParliaments(results){
	var str = "";
	str +="<table class='parlResultTable offset1' width='800' cellspacing='0' cellpadding='2' border='0'>";
		str +="<tbody style='font-family: Tahoma;font-size: 12px;'>";
			str +="<tr>";
				str +="<td colspan='5'><img width='300' height='130' src='images/specialPage/2014Ele.png'></td>";
				str +="<td colspan='3'><img width='140' height='150' src='images/specialPage/Modi.png'></td>";
				str +="<td colspan='3'><img width='140' height='150' src='images/specialPage/Rahul.png'></td>";
			str +="</tr>";
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='14'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
			str +="<tr>";
				str +="<td rowspan='3' style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #B0BDDA;'>STATE</td>";
				str +="<td rowspan='3' align='center' style='font-weight:bold;color:black;vertical-align:bottom;border-bottom:1px solid #B0BDDA;'>TOTAL</td>";
				str +="<td colspan='6' style='font-weight:bold;color:black;text-align:center;border-bottom:1px solid #B0BDDA;'>2014</td>";
				str +="<td width='4%'> </td>";
				str +="<td colspan='4' style='font-weight:bold;color:black;text-align:center;border-bottom:1px solid #B0BDDA'>2009</td>";
			str +="</tr>";
			
			
			str +="<tr>";
				
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#f2be8e;'>NDA</td>";
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#8dbfa0;'>UPA</td>";
				str +="<td colspan='2' align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#f4f4a4'>OTHERS</td>";
				str +="<td width='4%'> </td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#f2be8e;'>NDA</td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA;background:#8dbfa0'>UPA</td>";
				str +="<td  align='center' style='font-weight:bold;color:black;border-bottom:1px solid #B0BDDA ;background:#f4f4a4'>OTHERS</td>";
			str +="</tr>";
			str +="<tr style='color:black;'>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #B0BDDA;'>LEAD</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #B0BDDA;'>LEAD</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #B0BDDA;'>LEAD</td>";
				str +="<td width='4%'> </td>";
				str +="<td align='right' style='background:#f2be8e;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#8dbfa0;border-bottom:1px solid #B0BDDA;'>WON</td>";
				str +="<td align='right' style='background:#f4f4a4;border-bottom:1px solid #B0BDDA;'>WON</td>";
			str +="</tr>";
			
		
			
			for(var i in results.statesList){
				
			str +="<tr class='bodyRows' style='color:black;'>";
				str +="<td align='left' style='color:#000066'>"+results.statesList[i].state+"</td>";
				str +="<td align='center' >"+results.statesList[i].statesTotalCount+"</td>";
				if(results.statesList[i].ndaWonCount !=  null){
					str +="<td align='right' style='background:#f2be8e'>"+results.statesList[i].ndaWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e'> 0 </td>";
				}
				if(results.statesList[i].ndaLeadCount !=  null){
					str +="<td align='right' style='background:#f2be8e'>"+results.statesList[i].ndaLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e'> 0 </td>";
				}
				if(results.statesList[i].upaWonCount !=  null){
					str +="<td align='right' style='background:#8dbfa0'>"+results.statesList[i].upaWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0'> 0 </td>";
				}
				if(results.statesList[i].upaLeadCount !=  null){
					str +="<td align='right' style='background:#8dbfa0'>"+results.statesList[i].upaLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0'> 0 </td>";
				}
				
				if(results.statesList[i].othersWonCount !=  null){
					str +="<td align='right' style='background:#f4f4a4'>"+results.statesList[i].othersWonCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4'> 0 </td>";
				}
				
				if(results.statesList[i].othersLeadCount !=  null){
					str +="<td align='right' style='background:#f4f4a4'>"+results.statesList[i].othersLeadCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4'> 0 </td>";
				}
				
				str +="<td width='4%'> </td>";
				
				if(results.statesList[i].ndaAlliancesCount !=  null){
					str +="<td align='right' style='background:#f2be8e'>"+results.statesList[i].ndaAlliancesCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f2be8e'> 0 </td>";
				}
				
				if(results.statesList[i].upaAlliancesCount !=  null){
					str +="<td align='right' style='background:#8dbfa0'>"+results.statesList[i].upaAlliancesCount+"</td>";
				}else{
					str +="<td align='right' style='background:#8dbfa0'> 0 </td>";
				}
				
				if(results.statesList[i].othersCount !=  null){
					str +="<td align='right' style='background:#f4f4a4'>"+results.statesList[i].othersCount+"</td>";
				}else{
					str +="<td align='right' style='background:#f4f4a4'> 0 </td>";
				}
			str +="</tr>";
			
			}
			
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='12'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
			str+="<tr style='font-weight:bold;color:black;'>";
				str +="<td>Totals</td>";
				str +="<td align='center'>"+results.overallStatesCount+"</td>";
				str +="<td align='right'>"+results.ttlNdaWonCount+"</td>";
				str +="<td align='right'>"+results.ttlNdaLeadCount+"</td>";
				str +="<td align='right'>"+results.ttlUpaWonCount+"</td>";
				str +="<td align='right'>"+results.ttlUpaLeadCount+"</td>";
				str +="<td align='right'>"+results.ttlOthersWonCount+"</td>";
				str +="<td align='right'>"+results.ttlOthersLeadCount+"</td>";
				str +="<td width='4%'> </td>";
				str +="<td align='right'>"+results.overAllNdaCount+"</td>";
				str +="<td align='right'>"+results.overAllUpaCount+"</td>";
				str +="<td align='right'>"+results.overAllOthersCount+"</td>";
			str+="</tr>";
			str +="<tr><td bgcolor='#AACAEA' style='padding: 0px;' colspan='12'><img width='1' height='1' src='images/specialPage/spacer.gif'></td></tr>";
		str +="</tbody>";
	str +="</table>";
	
	$(".parliamentResultsDiv").html(str);
}


function buildWonLeadResults(results){
      if(results != null && results.length >0){
	var str='';
	
	  str+='<table id="candidateWinLeadResultTableDiv" style="width:95%;margin-left:auto;margin-right:auto;">';
	     str+='<thead>';
		 str+=' <tr>';
		 str+='  <th style="width: 153px;">Constituency</th>';
		 str+='  <th style="width: 103px;">District</th>';
		 str+='  <th style="width: 325px;">Candidate</th>';
		 str+='  <th style="width: 75px;">Party</th> ';
		 str+='  <th style="width: 75px;">Status</th>';
		 str+='  <th style="width: 85px;">Assests</th>';
		 str+='  <th style="width: 85px;">liabilities</th>';
		 str+=' </tr>';
		
		 str+='</thead>';
		 
		 str+='<tbody>';
		  for(var i in results){
		  
		  str+=' <tr> ';
		  str+='    <td><a href="constituencyPageAction.action?constituencyId='+results[i].constiId+'">'+results[i].constiName+'</a></td>';
		  str+='    <td><a href="districtPageAction.action?districtId='+results[i].districtId+'">'+results[i].districtName+'</a></td>';
		  str+='	  <td><a href="candidateElectionResultsAction.action?candidateId='+results[i].candidateId+'">'+results[i].candidateName+'</a></td>';
		  if(results[i].partyName != 'IND'){
		    str+='	    <td class="textalignclass"><a href="partyPageAction.action?partyId='+results[i].partyId+'" >'+results[i].partyName+'</a></td>';
		  }else{
		    str+='	    <td class="textalignclass">'+results[i].partyName+'</td>';
		}
		str+='	  <td class="textalignclass">'+results[i].status+'</td>';
		if(results[i].assets != null)
		  str+='	  <td class="textalignright">'+results[i].assets+'</td>';
		else
		   str+='	  <td ></td>';
		if(results[i].liabilities != null)
		  str+='	  <td class="textalignright">'+results[i].liabilities+'</td>';
		else
		 str+='	  <td ></td>';
		str+='   </tr>';
		}
		 str+='</tbody>';
		str+=' <tfoot>';
		str+='  <tr>';
		str+='   <th>Constituency</th>';
		str+='   <th>District</th>';
		str+='   <th>Candidate</th>';
		str+='   <th>Party</th> ';
		str+='   <th>Status</th>';
		str+='   <th>Assests</th>';
		str+='   <th>liabilities</th>';
		str+='  </tr>';
		str+=' </tfoot>';
	  str+='</table>';
		$("#candidateWinLeadResultDisplayDiv").html(str);
		

		  $('#candidateWinLeadResultTableDiv').dataTable({
		"aLengthMenu": [[10,25,50,100, -1], [10, 25,50,100,"All"]]
	})
		  .columnFilter({ 
		  	
			aoColumns: [ { type: "text"},
			             { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "number"},
						 { type: "number"}
						 
				]

		});
	}
}
function buildDistrictWiseLiveResults(results){
    if(results != null && results.length >0){
	var str='';
	
	  str+='<table id="districtResultTableDiv" style="width:95%;margin-left:auto;margin-right:auto;">';
	     str+='<thead>';
		 str+=' <tr>';
		 str+='  <th style="width: 135px;">Constituency</th>';
		 str+='  <th style="width: 285px;">Candidate</th>';
		 str+='  <th style="width: 217px;">Party</th> ';
		 str+='  <th  style="width: 69px;">Status</th>';
		 str+='  <th style="width: 80px;">Assests</th>';
		 str+='  <th style="width: 85px;">liabilities</th>';
		 str+=' </tr>';
		
		 str+='</thead>';
		 
		 str+='<tbody>';
		  for(var i in results){
		  
		  str+=' <tr> ';
		  str+='    <td><a href="constituencyPageAction.action?constituencyId='+results[i].constiId+'">'+results[i].constiName+'</a></td>';
		  str+='	  <td><a href="candidateElectionResultsAction.action?candidateId='+results[i].candidateId+'">'+results[i].candidateName+'</a></td>';
		  if(results[i].partyName != 'IND'){
		    str+='	    <td class="textalignclass"><a href="partyPageAction.action?partyId='+results[i].partyId+'" >'+results[i].partyName+'</a></td>';
		  }else{
		    str+='	    <td class="textalignclass">'+results[i].partyName+'</td>';
		}
		str+='	  <td class="textalignclass">'+results[i].status+'</td>';
		if(results[i].assets != null)
		  str+='	  <td class="textalignright">'+results[i].assets+'</td>';
		else
		   str+='	  <td ></td>';
		if(results[i].liabilities != null)
		  str+='	  <td class="textalignright">'+results[i].liabilities+'</td>';
		else
		 str+='	  <td ></td>';
		str+='   </tr>';
		}
		 str+='</tbody>';
		str+=' <tfoot>';
		str+='  <tr>';
		str+='   <th>Constituency</th>';
		str+='   <th>Candidate</th>';
		str+='   <th>Party</th> ';
		str+='   <th>Status</th>';
		str+='   <th>Assests</th>';
		str+='   <th>liabilities</th>';
		str+='  </tr>';
		str+=' </tfoot>';
	  str+='</table>';
		$("#districtWiseElectionResultDisplayDiv").html(str);
		

		  $('#districtResultTableDiv').dataTable({
		"aLengthMenu": [[10,25,50,100, -1], [10, 25,50,100,"All"]]
	})
		  .columnFilter({ 
		  	
			aoColumns: [ { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "text"},
				         { type: "number"},
						 { type: "number"}
						 
				]

		});
	}
}

function buildSpecialPageHightLights(results)
{
	
	var str ='';
	var specialPageHighLight = document.getElementById('specialPageHighLight');
	
	str +='<fieldset  style="verdana,sans-serif;font-weight:bold;margin-top:14px;">';
	str +='<legend style="border-radius: 3px;background:#21B2ED;font-family: verdana;">Gujarat HighLights</legend>';
	str +='<div class="hglgts">';
	if(results != null && results!='')
	{
	for(var i in results)
	{
	str += '<ul><li>';
	str += ' '+results[i].description+'</li></ul>';
	}
	
	str +='</div>';
	str +='</fieldset>';
	}
	specialPageHighLight.innerHTML = str;
}


function getSpecialPageHighLights()
{
var jsObj = {
				specialPageId:"17",
	           
				task:"getHighLights"
			};
	var param="task="+YAHOO.lang.JSON.stringify(jsObj);
	var url = "getSpecialPageHighLights.action?"+param;						
		
	callAjax(jsObj,url);
}
function buildGujaratElectionResult(myResults)
{
	if(myResults!=null && myResults.electionLiveResultVOList!=null &&  myResults.electionLiveResultVOList.length>0)
	{
	var electionResult= '';	
	electionResult+='<div id="gujratResultsHeader">';
	electionResult+='<h3 style="padding:4px;background-color: #21B2ED;color:#ffffff;-moz-border-radius:3px;border-radius:3px;width: 100%;">';
	electionResult+='Party Wise Results Of Tripura Vidhan Sabha 2013</h3>';
	electionResult+='</div>';
	electionResult+='<div id="GujaratResultBody" >';
	electionResult+='<span id="gujratResultsBody1" style ="width:450px;float:left;margin:11px;">';
	electionResult+='<table class="table table-bordered table-striped">';
	electionResult+='<tr>';
	electionResult+='<th>Total Seats - <font color="#05A8E9">'+myResults.totalSeats+'</font></th>';
	electionResult+='<th> Result Known - <font color="#05A8E9">'+myResults.totalKnownCount+'</font></th>';
	electionResult+='</tr>';
	electionResult+='<tr>';
	electionResult+='<th>Total Lead - <font color="#05A8E9">'+myResults.newKnownCount+'</font></th>';
	electionResult+='<th> Won - <font color="#05A8E9">'+myResults.retainedCount+'</font></th>';
	electionResult+='</tr>';
	electionResult+='<table>';
	electionResult+='</span>';
	electionResult+='<span id="gujratResultsBody2" style ="width:450px;float:left">';
	electionResult+='<table class="gujaratTableDiv table table-bordered table-striped">';
	electionResult+='<tr>';
	electionResult+='<th>party</th>';
	electionResult+='<th>TP*</th>';
	electionResult+='<th>Lead</th>';
	electionResult+='<th>Won</th>';
	electionResult+='<th>Lead/Won</th>';
	electionResult+='</tr>';
	for(var i=0 ; i<myResults.electionLiveResultVOList.length ; i++)
	{
	electionResult+='<tr>';
	electionResult+='<th>'+myResults.electionLiveResultVOList[i].partyName+'</th>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].totalSeatsParticipated+'</td>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].leadCountInNew+'</td>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].wonCountInNew+'</td>';
	electionResult+='<td>'+myResults.electionLiveResultVOList[i].wonOrLeadCount+'</td>';
	electionResult+='</tr>';
	}
	electionResult+='</table>';
	electionResult+='</span>';
	electionResult+='<span  id="GujaratResultGraph" style ="width:400px;float:right;border-radius:5px;box-shadow:0px 1px 2px #aaa;margin-right:5px;padding:2px;">';
	electionResult+='<b></b>';
	electionResult+='</span>';
	electionResult+='</div>';
	document.getElementById('electionResultDiv').innerHTML = electionResult;
	
	var data = new google.visualization.DataTable();
	data.addColumn('string','Area Type');
	data.addColumn('number','Count');
	data.addRows(myResults.electionLiveResultVOList.length);
	
	for(var j=0; j<myResults.electionLiveResultVOList.length; j++)
	{
		data.setValue(j,0,myResults.electionLiveResultVOList[j].partyName);
		data.setValue(j,1,myResults.electionLiveResultVOList[j].wonOrLeadCount);
	}
	var chart = new google.visualization.PieChart(document.getElementById('GujaratResultGraph')); 
	chart.draw(data,{width:400, height: 250, title:'Tripura Vidhan Sabha Election 2013 Lead/Won Chart'});
	}
}

 getSpecialPageHighLights();

 
 getMarginsCountOfParties();
function getMarginsCountOfParties(){
		var locations = [];
		locations.push(18);
		locations.push(17);
		
		var jsObj =	{
			electionId : 38,
			type:2,
			locationIds:locations
		}
		
		$.ajax(
		  {
				type: "POST",
				url:"getMarginAnalysisOnLiveResultsForAssemblies.action",
				data:{task :JSON.stringify(jsObj)}
		  }
		  ).done(function(result){
				console.log(result);
				if(result.partiesList != null && result.partiesList>0){
					buildPartyWiseMarginCount(result);
				}
		  });
}

function buildPartyWiseMarginCount(result){
	
	var str = "<table class='table table-striped'>";
		str +="<thead>";
			str +="<tr>";
				str+="<td>PARTY</td>";
				for(var i in result.partiesList[0].marginsVO){
					str+="<td>"+result.partiesList[0].marginsVO[i].margin+"</td>";
				}
			str +="</tr>";
		str +="</thead>";
		str +="<tbody>";
			for(var i in result.partiesList){
				for(var j in result.partiesList[i].marginsVO){
					str +="<tr>";
						if(result.partiesList[i].marginsVO[j].count == null){
							str+="<td> - </td>";
						}else{
							str+="<td>"+result.partiesList[i].marginsVO[j].count+"</td>";
						}
					str +="</tr>";
				}
			}
		str +="</tbody>";
	str += "</table>";
	}

</script>