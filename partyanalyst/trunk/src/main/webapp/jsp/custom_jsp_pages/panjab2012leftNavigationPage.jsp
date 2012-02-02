<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<div class="selectHeading">
	<span class="selectDivStyle">Punjab Districts</span>
	<span style="margin-left: 12px;">Know About Your District</span>
	<select class="selectBoxWidth" id="districtList_d" name="district">
	<option value="0">Select District</option>
     <option value="255">Amritsar</option><option value="267">Bathinda</option><option value="266">Faridkot</option><option value="261">Fatehgarh Sahib</option><option value="264">Firozpur</option><option value="254">Gurdaspur</option><option value="258">Hoshiarpur</option><option value="257">Jalandhar</option><option value="256">Kapurthala</option><option value="262">Ludhiana</option><option value="268">Mansa</option><option value="263">Moga</option><option value="265">Muktsar</option><option value="259">Nawanshahr </option><option value="270">Patiala</option><option value="260">Rupnagar</option><option value="269">Sangrur</option>
   </select>
   <div id="alertMessage_district"></div>

	<div class="view-results"><a onclick="navigateToDistrictPage()" href="javascript:{}">view results</a></div>


</div>
<div class="selectHeading">
	<span class="selectDivStyle">Punjab Constituencies </span>
	<span style="margin-left: 12px;">Know About Your Assembly Constituency</span>
<select class="selectBoxWidth" id="constituency" name="constituency">
<option value="0">Select Constituency</option>
  <option value="38833">Abohar</option><option value="38770">Adampur</option><option value="38762">Ajnala</option><option value="38820">Amloh</option><option value="38760">Amritsar Central</option><option value="38758">Amritsar North</option><option value="38761">Amritsar South</option><option value="38759">Amritsar West</option><option value="38808">Anandpur Sahib Ropar</option><option value="38764">Attari</option><option value="38842">Bagha Purana</option><option value="38786">Balachaur</option><option value="38832">Balluana</option><option value="38779">Banga</option><option value="38812">Banur</option><option value="38825">Barnala</option><option value="38744">Batala</option><option value="38754">Beas</option><option value="38826">Bhadaur</option><option value="38853">Bhatinda</option><option value="38782">Bholath</option><option value="38858">Budhlada</option><option value="38809">Chamkaur Sahib</option><option value="38815">Dakala</option><option value="38797">Dakha</option><option value="38793">Dasuya</option><option value="38827">Dhanaula</option><option value="38840">Dharamkot</option><option value="38748">Dhariwal</option><option value="38822">Dhuri</option><option value="38750">Dina Nagar</option><option value="38829">Dirbha</option><option value="38846">Faridkot</option><option value="38743">Fatehgarh</option><option value="38834">Fazilka</option><option value="38837">Firozepur</option><option value="38838">Firozepur Cantonment</option><option value="38792">Garhdiwala</option><option value="38787">Garhshankar</option><option value="38814">Ghanaur</option><option value="38848">Giddar Baha</option><option value="38749">Gurdaspur</option><option value="38836">Guru Har Sahai</option><option value="38789">Hoshiarpur</option><option value="38795">Jagraon</option><option value="38835">Jalalabad</option><option value="38757">Jandiala</option><option value="38856">Joga</option><option value="38771">Jullundur Cantonment</option><option value="38773">Jullundur Central</option><option value="38772">Jullundur North</option><option value="38774">Jullundur South</option><option value="38747">Kahnuwan</option><option value="38783">Kapurthala</option><option value="38775">Kartarpur</option><option value="38766">Khadoor Sahib</option><option value="38806">Khanna</option><option value="38811">Kharar</option><option value="38845">Kot Kapura</option><option value="38804">Kum Kalan</option><option value="38850">Lambi</option><option value="38831">Lehra</option><option value="38776">Lohian</option><option value="38801">Ludhiana East</option><option value="38799">Ludhiana North</option><option value="38802">Ludhiana Rural</option><option value="38800">Ludhiana West</option><option value="38788">Mahilpur</option><option value="38755">Majitha</option><option value="38823">Malerkotla</option><option value="38849">Malout</option><option value="38857">Mansa</option><option value="38841">Moga</option><option value="38810">Morinda</option><option value="38794">Mukerian</option><option value="38847">Muktsar</option><option value="38819">Nabha</option><option value="38777">Nakodar</option><option value="38807">Nangal</option><option value="38751">Narot Mehra</option><option value="38854">Nathana</option><option value="38767">Naushahra Panwan</option><option value="38780">Nawan Shahr</option><option value="38843">Nihal Singh Wala</option><option value="38778">Nur Mahal</option><option value="38852">Pakka Kalan</option><option value="38844">Panjgrain</option><option value="38752">Pathankot</option><option value="38818">Patiala Town</option><option value="38768">Patti</option><option value="38803">Payal</option><option value="38785">Phagwara</option><option value="38781">Phillaur</option><option value="38745">Qadian</option><option value="38798">Qila Raipur</option><option value="38796">Raikot</option><option value="38763">Raja Sansi</option><option value="38813">Rajpura</option><option value="38855">Rampura Phul</option><option value="38817">Samana</option><option value="38805">Samrala</option><option value="38828">Sangrur</option><option value="38859">Sardulgarh</option><option value="38790">Sham Chaurasi</option><option value="38824">Sherpur</option><option value="38816">Shutrana</option><option value="38821">Sirhind</option><option value="38746">Srihargobindpur</option><option value="38753">Sujanpur</option><option value="38784">Sultanpur</option><option value="38830">Sunam</option><option value="38851">Talwandi Sabo</option><option value="38791">Tanda</option><option value="38765">Tarn Taran</option><option value="38769">Valtoha</option><option value="38756">Verka</option><option value="38839">Zira</option>
 </select>
<div id="alertMessage"></div>

<div class="view-results"><a onclick="navigateToConstituencyPage()" href="javascript:{}">view constituency</a></div>
</div>

