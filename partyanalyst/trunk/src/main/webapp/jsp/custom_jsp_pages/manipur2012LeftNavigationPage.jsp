<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<div class="selectHeading">
	<span class="selectDivStyle">Manipur Districts </span>
	<span style="margin-left: 12px;">Know About Your District </span>
<select class="selectBoxWidth" id="districtList_d" name="district">

   <option value="0">Select District</option><option value="288">Bishnupur</option><option value="293">Chandel</option><option value="287">Churachandpur</option><option value="291">Imphal East</option><option value="290">Imphal West</option><option value="285">Senapati</option><option value="286">Tamenglong</option><option value="289">Thoubal</option><option value="292">Ukhrul</option>
 </select>
 <div id="alertMessage_district"></div>
	<div class="view-results"><a onclick="navigateToDistrictPage()" href="javascript:{}">view results</a></div>

	</div>
	<div class="selectHeading">
	<span class="selectDivStyle">Manipur Constituencies </span>
	<span style="margin-left: 12px;">Know About Your Assembly Constituency</span>
	<select class="selectBoxWidth" id="constituency" name="constituency">
    <option value="0">Select Constituency</option><option value="39415">Andro</option><option value="39434">Bishenpur</option><option value="39449">Chandel </option><option value="39453">Chingai </option><option value="39466">Churachandpur </option><option value="39410">Heingang</option><option value="39441">Heirok</option><option value="39465">Henglep </option><option value="39446">Hiyanglam</option><option value="39448">Jiribam</option><option value="39445">Kakching</option><option value="39458">Kangpokpi</option><option value="39455">Karong </option><option value="39414">Keirao</option><option value="39420">Keisamthong</option><option value="39443">Khangabok</option><option value="39412">Khetrigao</option><option value="39409">Khundrakpam</option><option value="39411">Khurai</option><option value="39426">Konthoujam</option><option value="39437">Kumbi</option><option value="39416">Lamlai</option><option value="39425">Lamsang</option><option value="39428">Langthabal</option><option value="39438">Lilong</option><option value="39456">Mao </option><option value="39431">Mayang Imphal</option><option value="39435">Moirang</option><option value="39432">Nambol</option><option value="39429">Naoriya Pakhanglakpa</option><option value="39462">Nungba </option><option value="39433">Oinam</option><option value="39427">Patsoi</option><option value="39451">Phungyar </option><option value="39419">Sagolband</option><option value="39467">Saikot </option><option value="39454">Saikul </option><option value="39459">Saitu </option><option value="39424">Sekmai </option><option value="39468">Singhat </option><option value="39421">Singjamei</option><option value="39447">Sugnoo</option><option value="39457">Tadubi </option><option value="39460">Tamei </option><option value="39461">Tamenglong </option><option value="39450">Tengnoupal </option><option value="39436">Thanga</option><option value="39417">Thangmeiband</option><option value="39464">Thanlon </option><option value="39413">Thongju</option><option value="39439">Thoubal</option><option value="39463">Tipaimukh </option><option value="39452">Ukhrul </option><option value="39418">Uripok</option><option value="39444">Wabgai</option><option value="39442">Wangjing Tentha</option><option value="39423">Wangkhei</option><option value="39440">Wangkhem</option><option value="39430">Wangoi</option><option value="39422">Yaiskul</option>
	</select>
	<div id="alertMessage"></div>
	<div class="view-results"><a onclick="navigateToConstituencyPage()" href="javascript:{}">view constituency</a></div>
	
	</div>