package com.itgrids.partyanalyst.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anilkumar Ravula May 14, 2014
 *
 */
public class BasePartyIdsMAp {
	
private static  Map<String,Integer> map=new HashMap<String,Integer>();
private static  Map<Integer,String> stateMap=new HashMap<Integer,String>();
private static  Map<String,Integer> stateNoMap=new HashMap<String, Integer>();


public static Map<String, Integer> getStateNoMap() {
	return stateNoMap;
}


public static void setStateNoMap(Map<String, Integer> stateNoMap) {
	BasePartyIdsMAp.stateNoMap = stateNoMap;
}


public static Map<Integer, String> getStateMap() {
	return stateMap;
}


public static void setStateMap(Map<Integer, String> stateMap) {
	BasePartyIdsMAp.stateMap = stateMap;
}


public static Map<String, Integer> getMap() {
	return map;
}


public static void setMap(Map<String, Integer> map) {
	BasePartyIdsMAp.map = map;
}


static
{
	System.out.println("inside static block of partyIds=============");
	/*map.put("Aihra National Party",99);
	map.put("All India Forward Bloc",64);
	map.put("All India Forward Bloc (Subhasist)",65);
	map.put("All India Liberal Party",361);
	map.put("All India Majlis-E-Ittehadul Muslimeen",72);
	map.put("Ambedkar National Congress",97);
	map.put("Andhra Rastra Praja Samithi",1567);
	map.put("B. C. United Front",128);
	map.put("Bahujan Samaj Party",239);
	map.put("Bahujan Samaj Party (Ambedkar)",240);
	map.put("Bharat Uday Mission",251);
	map.put("Bharatiya Janata Party",163);
	map.put("Communist Party of India",265);
	map.put("Communist Party of India (Marxist)",1213);
	map.put("Communist Party of India (Marxist-Leninist) (Liberation)",1226);//Communist Party of India (Marxist-Leninist) Red Star
	map.put("Dalita Bahujana Party",273);
	map.put("Ekta Samaj Party",300);
	map.put("Gondvana Gantantra Party",309);
	map.put("Great India Party",322);
	map.put("Hindustan Janta Party",334);
	map.put("Independent",366);
	map.put("Indian Christian Secular Party",353);
	map.put("Indian National Congress",362);
	map.put("Indian Union Muslim League",381);
	map.put("Jai Maha Bharath Party",423);
	map.put("Jan Sangh Party",1593);
	map.put("Janata Dal (Secular)",1218);
	map.put("Janata Dal (United)",393);
	map.put("Janata Party",429);
	map.put("Lok Jan Shakti Party",492);
	map.put("Lok Satta Party",514);
	map.put("Maa Telangana Party",1161);
	map.put("Mahabharath Mahajan Sabha",552);
	map.put("Majlis Bachao Tahreek",531);
	map.put("Nationalist Congress Party",579);
	map.put("New India Party",586);
	map.put("Peoples Republican Party",656);
	map.put("Pyramid  Party of India",651);
	map.put("Pyramid Party of India",651);
	map.put("Rajyadhikara Party",701);
	map.put("Rashtriya Janata Dal",1568);
	map.put("Rashtriya Krantikari Samajwadi Party",728);
	map.put("Rashtriya Lok Dal",682);
	map.put("Rashtriya Lokwadi Party",683);
	map.put("Rayalaseema Rashtra Samithi",760);
	map.put("Republican Paksha (Khoripa)",740);
	map.put("Republican Party of India",744);
	map.put("Republican Party of India (A)",745);
	map.put("Republican Party of India (Khobragade)",750);
	map.put("Revolutionary Socialist Party",771);
	map.put("Revolutionary Socialist Party of India(Marxist)",774);
	map.put("Samajwadi Janata Party (Rashtriya)",822);
	map.put("Samajwadi Party",839);
	map.put("Shivsena",816);
	map.put("SOCIAL DEMOCRATIC PARTY OF INDIA",1087);
	map.put("Socialist Party (India)",1532);
	map.put("SOCIALIST UNITY CENTRE OF INDIA (COMMUNIST)",1554);
	map.put("Telangana Communist Party of India",1061);
	map.put("Telangana Rashtra Samithi",886);
	map.put("Telugu Desam",872);
	map.put("Voters Party",1603);
	map.put("Welfare Party Of India",1608);
	map.put("Yuvajana Sramika Rythu Congress Party",1117);
	map.put("None of the Above",232323);
	
	map.put("Shakti Sena (Bharat Desh)",848);
	map.put("Bharatiya Bahujan Party",140);
	map.put("Communist Party of India (Marxist-Leninist) Red Star",1711);
	map.put("Chattisgarh Swabhiman Manch",1710);*/
	map.put("Telangana Rashtra Samithi",886);
	map.put("Indian National Congress",362);
	map.put("Telugu Desam",872);
	map.put("Bahujan Samaj Party",239);
	map.put("Independent",366);
	map.put("Pyramid Party of India",651);
	map.put("Republican Party of India",744);
	map.put("B. C. United Front",128);
	map.put("New India Party",586);
	map.put("Mahajana Socialist Party",1713);
	map.put("Republican Paksha (Khoripa)",740);
	map.put("Bharatiya Janata Party",163);
	map.put("Nationalist Congress Party",579);
	map.put("Bahujan Mukti Party",1714);
	map.put("Yuvajana Sramika Rythu Congress Party",1117);
	map.put("Republican Party of India (A)",745);
	map.put("Welfare Party Of India",1608);
	map.put("Aam Aadmi Party",1681);
	map.put("Majlis Bachao Tahreek",531);
	map.put("Lok Satta Party",514);
	map.put("Shramajeevi Party",1708);
	map.put("All India Majlis-E-Ittehadul Muslimeen",72);
	map.put("Telangana Loksatta Party",1688);
	map.put("Jai Samaikyandhra Party",1712);
	map.put("Shivsena",816);
	map.put("B.C.Bharata Desam Party",1715);
	map.put("Andhra Pradesh Rashtra Samaikya Samithi Party",1690);
	map.put("Akhil Bharatiya Muslim League (Secular)",1612);
	map.put("Prem Janata Dal",1683);
	map.put("All India Forward Bloc",64);
	map.put("Samaikyandhra Parirakshana Samithi",1687);
	map.put("Majlis Markaz-e-Siyasee Party",1693);
	map.put("Republican Party of India (Khobragade)",750);
	map.put("Jantantra Party",1692);
	map.put("SOCIALIST UNITY CENTRE OF INDIA (COMMUNIST)",1120);
	map.put("Indian Union Muslim League",381);
	map.put("Revolutionary Socialist Party",771);
	map.put("Indian Christian Secular Party",353);
	map.put("Telangana Communist Party of India",1061);
	map.put("All India Azaad Congress Party",1697);
	map.put("Socialist Party (India)",1532);
	map.put("Nava Bharat National Party",1694);
	map.put("Marxist Communist Party of India (United)",1682);
	map.put("Communist Party of India (Marxist)",269);
	map.put("Rashtriya Lok Dal",682);
	map.put("Communist Party of India",265);
	map.put("Communist Party of India (Marxist-Leninist) (Liberation)",268);
	map.put("Dalita Bahujana Party",273);
	map.put("Rashtriya Janata Dal",1568);
	map.put("Gareeb Aadmi Party",1689);
	map.put("Rashtriya Praja Congress (Secular)",741);
	map.put("Indian Labour Party (Ambedkar Phule)",1701);
	map.put("Andhra Rastra Praja Samithi",1633);
	map.put("Navodyam Party",1635);
	map.put("Great India Party",322);
	map.put("Socialistic Democratic Party",809);
	map.put("Samaikya Telugu Rajyam",1709);
	map.put("Rayalaseema Parirakshana Samithi",1706);
	map.put("Janata Dal (United)",393);
	map.put("Ambedkar National Congress",97);
	map.put("Janata Dal (Secular)",1218);
	map.put("Rajyadhikara Party",701);
	map.put("Hindustan Janta Party",334);
	map.put("All India Trinamool Congress",76);
	map.put("Samajwadi Party",839);
	map.put("People's Party of Arunachal",647);
	map.put("Lok Bharati",488);
	map.put("Jharkhand Mukti Morcha",425);
	map.put("All India United Democratic Front",77);
	map.put("Asom Gana Parishad",53);
	map.put("Regional Democratic Secular Congress",1716);
	map.put("Bodoland Peoples Front",206);
	map.put("Janta Dal Rashtravadi",1717);
	map.put("Bharat Vikas Morcha",254);
	map.put("Lok Dal",491);
	map.put("Samajwadi Janata Party (Rashtriya)",822);
	map.put("Akhil Bharatiya Jan Sangh",20);
	map.put("Hindustan Vikas Dal",1718);
	map.put("Shoshit Samaj Dal",849);
	map.put("SHS",816);
	map.put("Bharatiya Momin Front",192);
	map.put("National Loktantrik Party",590);
	map.put("Bhartiya Ekta Manch Party",1719);
	map.put("Rajnaitik Vikalp Party",1720);
	map.put("Bharatiya Bahujan Congress",1721);
	map.put("National Tiger Party",1722);
	map.put("Sankhyanupati Bhagidari Party",1723);
	map.put("Pragatisheel Manav Samaj Party",644);
	map.put("Suheldev Bhartiya Samaj Party",805);
	map.put("Janvadi Party(Socialist)",438);
	map.put("Bajjikanchal Vikas Party",161);
	map.put("Akhil Bhartiya Mithila Party",1724);
	map.put("Rashtriya Ahinsa Manch",1725);
	map.put("Bharat Bhrashtachar Mitao Party",1726);
	map.put("Rashtriya Jan-Jagram Morcha",715);
	map.put("Rashtriya Bahujan Congress Party",692);
	map.put("Bharatiya Ekta Dal",134);
	map.put("Lok Jan Shakti Party",492);
	map.put("Rashtriya Lok Samta Party",1727);
	map.put("Krantikari Vikas Dal",1728);
	map.put("Jai Prakash Janata Dal",436);
	map.put("VANCHITSAMAJ INSAAF PARTY",1564);
	map.put("Rashtra Sewa Dal",690);
	map.put("Proutist Sarva Samaj",674);
	map.put("Rashtravadi Janata Party",787);
	map.put("Sarvajan Kalyan Loktantrik Party",1729);
	map.put("Rashtriya Naujawan Dal",737);
	map.put("Loktantrik Janata Party (Secular)",499);
	map.put("Peace Party",623);
	map.put("Jai Maha Bharath Party",423);
	map.put("Rashtriya Krantikari Samajwadi Party",728);
	map.put("Proutist Bloc, India",618);
	map.put("Bharatiya Inqalab Party",1730);
	map.put("Ati Picchara party",1731);
	map.put("Moolniwasi Samaj Party",1549);
	map.put("Bharatiya Jan Kranti Dal (Democratic)",159);
	map.put("Bhartiya Jantantrik Janata Dal",157);
	map.put("Jharkhand Disom Party",394);
	map.put("Kalinga Sena",478);
	map.put("Jammu & Kashmir National Panthers Party",415);
	map.put("Bihar Janta Party",1732);
	map.put("National Lokmat Party",1733);
	map.put("Gondvana Gantantra Party",309);
	map.put("Swatantra Samaj Party",1734);
	map.put("Bharatiya Grameen Dal",138);
	map.put("Jai Hind Party",401);
	map.put("Naya Daur Party",576);
	map.put("Mahamukti Dal",1735);
	map.put("Lokpriya Samaj Party",509);
	map.put("Rashtriya Samanta Dal",768);
	map.put("Inqalab Vikas Dal",382);
	map.put("Hindustan Ekta Party",1736);
	map.put("Ambedkarite Party of India",1737);
	map.put("Communist Party of India (Marxist-Leninist) Red Star",1668);
	map.put("Chhattisgarh Swabhiman Manch",1738);
	map.put("Bhrashtachar Mukti Morcha",1739);
	map.put("Shakti Sena (Bharat Desh)",848);
	map.put("Rashtriya Gondvana Party",708);
	map.put("Bhartiya Shakti Chetna Party",1740);
	map.put("Chhattisgarhiya Party",1741);
	map.put("Apna Dal",1742);
	map.put("Aazadi Ka Antim Aandolan Dal",1743);
	map.put("Goemcarancho Otrec Astro",1235);
	map.put("Goa Su-Raj Party",324);
	map.put("Vishva Hindustani Sangathan",914);
	map.put("Akhil Bharatiya Congress Dal (Ambedkar)",7);
	map.put("Bahujan Suraksha Dal",1644);
	map.put("Prajatantra Aadhar Party",1540);
	map.put("Hindusthan Nirman Dal",1229);
	map.put("Loktantrik Samajwadi Party",516);
	map.put("Yuva Sarkar",1648);
	map.put("Rashtriya Samaj Paksha",775);
	map.put("Bharatiya National Janta Dal",201);
	map.put("Aadivasi Sena Party",52);
	map.put("Apna Desh Party",1744);
	map.put("National Youth Party",611);
	map.put("Loktantrik Rashrtavadi Party",1745);
	map.put("Rashtriya Komi Ekta Party",724);
	map.put("Voters Party",1603);
	map.put("Republican Party of India Ektavadi",1746);
	map.put("Indian National Lok Dal",370);
	map.put("Rashtriya Karmyog Party",1747);
	map.put("Lok Parivartan Party(DC)",500);
	map.put("Republican Backward Congress",1748);
	map.put("All India Peoples' Front (Radical)",1749);
	map.put("Akhil Bharatiya Hind Kranti Party",14);
	map.put("AARAKSHAN VIRODHI PARTY",1750);
	map.put("Akhil Bharat Hindu Mahasabha",15);
	map.put("Hindustan Kranti Dal",1751);
	map.put("Rashtriya Ulama Council",1752);
	map.put("National Janhit Congress (AB)",1753);
	map.put("RASHTRIYA MORCHA PARTY",1754);
	map.put("Haryana Janhit Congress (BL)",332);
	map.put("Bharatiya Sant Mat Party",1755);
	map.put("Rashtrawadi Parivartan Party (L.B,)",1756);
	map.put("Rashtriya Janshakti Party(Eklavya)",1757);
	map.put("Indian Bahujan Sandesh Party (Kanshiram)",1758);
	map.put("Rashtriya Bahujan Hitay Party",1598);
	map.put("Jan Morcha",422);
	map.put("Rashtriya Jatigat Aarakshan Virodhi Party",1759);
	map.put("Poorvanchal Rashtriya Congress",657);
	map.put("Himachal Swabhiman Party",1760);
	map.put("Jammu & Kashmir National Conference",414);
	map.put("Jammu & Kashmir Peoples Democratic Party",419);
	map.put("All Jammu and Kashmir Republican Party",1761);
	map.put("Ambedkar Samaj Party",110);
	map.put("Prism",659);
	map.put("Jammu & Kashmir People Conference",435);
	map.put("All J & K Kisan Majdoor Party",82);
	map.put("Bharatiya Bahujan Party",140);
	map.put("AJSU Party",88);
	map.put("Jharkhand Vikas Morcha (Prajatantrik)",453);
	map.put("Jharkhand Party",402);
	map.put("Rashtriya Jankranti Morcha",1762);
	map.put("Rashtriya Deshaj Party",1763);
	map.put("Marxist Co-Ordination",533);
	map.put("All India Minorities Front",71);
	map.put("Amra Bangalee",94);
	map.put("Socialist Party (Lohia)",829);
	map.put("Samata Party",798);
	map.put("Jharkhand Vikas Dal",452);
	map.put("Pragatisheel Magahi Samaj",1764);
	map.put("Jharkhand Party (Naren)",418);
	map.put("Akhil Bhartiya Jharkhand Party",1765);
	map.put("Manav Mukti Morcha",551);
	map.put("Jai Bharat Samanta Party",386);
	map.put("Karunaadu Party",1766);
	map.put("Kannada Chalavali Vatal Paksha",457);
	map.put("Kamarajar Deseeya Congress",459);
	map.put("Jai Vijaya Bharathi Party",1630);
	map.put("Bharatiya Dr. B.R.Ambedkar Janta Party",1631);
	map.put("Bharatiya Peoples Party",148);
	map.put("Dr. Ambedkar Samajvadi Democratic Party",1767);
	map.put("Sarva Janata Party",1768);
	map.put("SOCIAL DEMOCRATIC PARTY OF INDIA",1087);
	map.put("Vichara Jagruthi Congress Paksha",1237);
	map.put("Rani Chennamma Party",1769);
	map.put("Democratic Prajakranthi Party Secularist",1770);
	map.put("National Development Party",581);
	map.put("Revolutionary Socialist Party of Kerala (Bolshevik) RSP.B",1771);
	map.put("Socialist Republican Party",845);
	map.put("Kerala Congress (M)",463);
	map.put("Socialist Janata (Democratic)",1772);
	map.put("Social Action Party",826);
	map.put("Indian Gandhiyan Party",1773);
	map.put("Minorities Democratic Party",539);
	map.put("Samajwadi Jan Parishad",832);
	map.put("Bahujan Sangharshh Dal",1774);
	map.put("Prajatantrik Samadhan Party",666);
	map.put("Samata Samadhan Party",1775);
	map.put("Bhartiya Jan Yug Party",1776);
	map.put("Samta Vikas Party",1777);
	map.put("Jan-Nyay Dal",1538);
	map.put("Bharatiya Rashtriya Mazdoor Dal",1778);
	map.put("Al-Hind Party",1542);
	map.put("Bharatiya Navyuvak Party",1779);
	map.put("Mahanwadi Party",1780);
	map.put("Brihattar Bharat Prajatantra Sewa Party",1781);
	map.put("Bundelkhand Congress",1578);
	map.put("Akhil Bhartiya Vikas Congress Party",1782);
	map.put("Aadijan Mukti Sena",96);
	map.put("Bhartiya Satya Sangharsh Party",150);
	map.put("National Peoples Party",600);
	map.put("Bharatiya Minorities Suraksha Mahasangh",197);
	map.put("Dalit Vikas Party(Bharat)",1783);
	map.put("Bhartiya Shramik Dal Socialist",1784);
	map.put("Rashtriya Apna Dal",1558);
	map.put("Bhartiya Navjawan Sena (Paksha)",1785);
	map.put("Maharashtra Parivartan Sena (T)",1786);
	map.put("Bharipa Bahujan Mahasangh",122);
	map.put("Prabuddha Republican Party",658);
	map.put("Ambedkarist Republican Party",104);
	map.put("Shivrajya Party",862);
	map.put("Akhil Bharatiya Manavata Paksha",30);
	map.put("Maharashtra Navnirman sena",556);
	map.put("Narayani Sena",1541);
	map.put("Awami Vikas Party",1787);
	map.put("Swabhimani Paksha",867);
	map.put("Rashtriya Sant Sandesh Party",782);
	map.put("Republican Bahujan Sena",1788);
	map.put("Peasants And Workers Party of India",677);
	map.put("Bharatiya Jawala Shakti Paksha",168);
	map.put("Hindusthan Praja Paksha",1789);
	map.put("Sardar Vallabhbhai Patel Party",861);
	map.put("People's Party of India(secular)",649);
	map.put("The Lok Party of India",1790);
	map.put("Hindustan Swaraj Congress Party",1791);
	map.put("Rashtriya Aam Party",1792);
	map.put("Democratic Secular Party",280);
	map.put("Bahujan Vikas Aaghadi",253);
	map.put("Peoples Guardian",634);
	map.put("Maharashtra Vikas Aghadi",1793);
	map.put("Dharmarajya Paksha",1794);
	map.put("Manipur Democratic Peoples's Front",1795);
	map.put("Naga Peoples Front",1796);
	map.put("United Democratic Party",892);
	map.put("Kalyankari Jantantrik Party",1797);
	map.put("Bhartiya Janta Dal (Integrated)",1798);
	map.put("Bharatiya Gaon Taj Dal",139);
	map.put("Atulya Bharat Party",1799);
	map.put("Vishva SHakti Party",1800);
	map.put("Rashtriya Janmorcha",1801);
	map.put("Bhartiya Pragatisheel Congress",208);
	map.put("Braj Vikas Party",1619);
	map.put("Samyak Parivartan Party",1802);
	map.put("Bhartiya Jan Manch",1803);
	map.put("Rashtriya Jankranti Party",1704);
	map.put("Bharat Vishal Party",1884);
	map.put("Agar Jan Party",55);
	map.put("Rashtriya Ekta Party",1804);
	map.put("Bharatiya Republican Paksha",223);
	map.put("Bharatiya Sarvodaya Kranti Party",236);
	map.put("Asankhya Samaj Party",1627);
	map.put("Jan Samanta Party",443);
	map.put("Akhand Bharat Samaj Party",43);
	map.put("Rashtriya Janadhikar Party",711);
	map.put("Biju Janata Dal",156);
	map.put("Paschimanchal Vikas Party",1805);
	map.put("Samata Kranti Dal",1806);
	map.put("Aama Odisha Party",1807);
	map.put("Samruddha Odisha",797);
	map.put("Odisha Jan Morcha",1808);
	map.put("Rashtriya Indepndent Morcha",1809);
	map.put("Kosal Kranti Dal",473);
	map.put("Pattali Makkal Katchi",642);
	map.put("All India N.R. Congress",1081);
	map.put("All India Anna Dravida Munnetra Kazhagam",50);
	map.put("Dravida Munnetra Kazhagam",285);
	map.put("Democratic Congress Party",277);
	map.put("Nav Bharat Democratic Party",1810);
	map.put("Democratic Bharatiya Samaj Party",276);
	map.put("Bahujan Samaj Party (Ambedkar)",240);
	map.put("Shiromani Akali Dal",794);
	map.put("Shiromani Akali Dal (Amritsar)(Simranjit Singh Mann)",795);
	map.put("Janral Samaj Party",1811);
	map.put("Akhil Bharatiya Shivsena Rashtrawadi",44);
	map.put("Indian Krantikari Lehar",1812);
	map.put("Punjab Labour Party",640);
	map.put("Megh Desham Party",1813);
	map.put("SARVAJAN SAMAJ PARTY (D)",1814);
	map.put("Bharti Jan Suraksha Party",1815);
	map.put("All India Mazdoor Party (Rangreta)",1816);
	map.put("New All India Congress Party",1817);
	map.put("Akhil Bhartiya Aamjan Party",1818);
	map.put("Bharatiya Yuva Shakti",1819);
	map.put("Jago Party",396);
	map.put("Rajasthan Vikas Party",721);
	map.put("Indian Peoples Green Party",1820);
	map.put("National Unionist Zamindara Party",1821);
	map.put("Bharat Nav Nirman Party",1822);
	map.put("Lok Shakti",511);
	map.put("awami aamjan party",1823);
	map.put("Bhartiya Party",1824);
	map.put("Sikkim Democratic Front",807);
	map.put("Sikkim Krantikari Morcha",1825);
	map.put("Anaithindia Dravidar Samudaya Munnetra Kazhagam",51);
	map.put("Desiya Murpokku Dravida Kazhagam",284);
	map.put("Makkal Manadu Katchi",550);
	map.put("Tamil Nadu Makkal Congress",881);
	map.put("National Organisation Congress",596);
	map.put("Ambedkar People's Movement",1826);
	map.put("Viduthalai Chiruthaigal Katchi",913);
	map.put("Ulzaipali Makkal Katchy",898);
	map.put("Union Party of India",1827);
	map.put("Jharkhand Mukti Morcha (Ulgulan)",427);
	map.put("Marumalarchi Dravida Munnetra Kazhagam",521);
	map.put("Indians Victory Party",383);
	map.put("United Communist Party of India",891);
	map.put("Desiya Forward Bloc",1828);
	map.put("Manithaneya Makkal Katchi",525);
	map.put("Thrinamool Tamil Nadu Congress",1829);
	map.put("Ezhuchi Tamilargal Munnetra Kazhagam",1830);
	map.put("Puthiya Tamilagam",671);
	map.put("Namadhu Makkal Katchi",591);
	map.put("Jebamani Janata",404);
	map.put("Indigenousn People's Front Of Tripura",1831);
	map.put("Tripura Pragatishil Gramin Congress",1832);
	map.put("Bharatiya Kisan Parivartan Party",178);
	map.put("Adarsh Samaj Party",1588);
	map.put("Samtawadi Republican Party",1833);
	map.put("Sarva Samaj Kalyan Party",1834);
	map.put("Rashtriya Sawarn Dal",763);
	map.put("Qaumi Ekta Dal",1835);
	map.put("Bharatiya Kisan Sena Loktantrik",1836);
	map.put("Pragatisheel Samaj Party",1837);
	map.put("Sanatan Sanskriti Raksha Dal",1838);
	map.put("Naitik Party",1036);
	map.put("Most Backward Classes Of India",1537);
	map.put("Adarsh Rashtriya Vikas Party",1234);
	map.put("Moulik Adhikar Party",1528);
	map.put("Kisan Majdoor Berojgar Sangh",1601);
	map.put("Rashtriya Manav samman Party",1572);
	map.put("Manavtawadi Samaj Party",1839);
	map.put("Rashtriya Congress (Babu Jagjivanram)",1840);
	map.put("Bhartiya Vikas Party",1624);
	map.put("Bhartiya Naujawan Inklav Party",1841);
	map.put("Bhartiya Krishak Dal",1546);
	map.put("Jawan Kisan Morcha",413);
	map.put("Indian Savarn Samaj Party",1842);
	map.put("Labour Party of India (V.V. Prasad)",1843);
	map.put("Mahan Dal",536);
	map.put("National Party",1625);
	map.put("Navbharat Nirman Party",578);
	map.put("Jai Hind Samaj Party",1844);
	map.put("Bharatiya Samaj Dal",232);
	map.put("Bhartiya Sarvjan Party",1845);
	map.put("Rashtriya Janwadi Party (Krantikari)",1548);
	map.put("Indian National League",369);
	map.put("Bahujan Sangharsh Party (Kanshiram)",1221);
	map.put("Adarsh Manavtawadi Party",1543);
	map.put("Rashtrawadi Samaj Party",791);
	map.put("Rashtriya Kranti Party",785);
	map.put("Rashtriya Janpriya Party",1594);
	map.put("Janta Raj Party",1846);
	map.put("Jan Raksha Party",1847);
	map.put("Bhartiya Samajik Kranti Dal",1848);
	map.put("Ex-Sainik Kissan Party",1849);
	map.put("Moderate Party",558);
	map.put("Swaraj (J)",1850);
	map.put("Aam Janata Party",1851);
	map.put("Bharatiya Rashtriya Bahujan Samaj Vikas Party",1571);
	map.put("RASHTRIYA VIKLANG PARTY",1574);
	map.put("Rashtriya mahan Gantantra Party",1562);
	map.put("Sanyukt Samajwadi Dal",847);
	map.put("Rashtriya Janta Party",1587);
	map.put("Indian Oceanic Party",1536);
	map.put("Dharam Nirpeksh Dal",288);
	map.put("Akhil Bharatiya Samajwadi Congress",1852);
	map.put("Rashtriya Parivartan Dal",742);
	map.put("Eklavya Samaj Party",298);
	map.put("Rashtriya Congress(J) Party",1853);
	map.put("Jan Raajya Party",1606);
	map.put("Swarajya Party Of India",868);
	map.put("Sarvshreshth Dal",1854);
	map.put("Akhil Rashtrawadi Party",106);
	map.put("Rashtriya Sarvajan Party",1855);
	map.put("Bhartiya Vanchitsamaj Party",1856);
	map.put("Nagrik Ekta Party",1857);
	map.put("Akhil Bharatiya Ashok Sena",3);
	map.put("Parivartan Samaj Party",667);
	map.put("Akhil Bharatiya Rajarya Sabha",39);
	map.put("Apna Dal United Party",1858);
	map.put("Deshbhakt Nirman Party",1859);
	map.put("Jan Shakti Ekta Party",1860);
	map.put("Uttar Pradesh Republican Party",902);
	map.put("Bharatiya Nav Kranti Party",1575);
	map.put("Bahujan Kranti Party (Marxwad-Ambedkarwad)",1861);
	map.put("Rashtriya Shoshit Samaj Party",1862);
	map.put("Rashtriya Vikas Party",789);
	map.put("Bhartiya Republican Party (Insan)",1863);
	map.put("Rashtriya Rashtrawadi Party",1864);
	map.put("Awami Samta Party",1865);
	map.put("Mahila Swabhiman Party",1866);
	map.put("Indian Bahujan Samajwadi Party",349);
	map.put("Vanchit Jamat Party",911);
	map.put("Majdoor Kisan Union Party",548);
	map.put("All India Ravidas Samata Party",1613);
	map.put("Rashtriya Bandhutwa Party",1867);
	map.put("Rashtriya Garib Dal",706);
	map.put("Rashtriya Janshanti Party",1868);
	map.put("Hindustan Krantikari Dal",1869);
	map.put("Nehru Janhit Congress",1600);
	map.put("Shoshit Sandesh Party",1870);
	map.put("Samajwadi Samaj Party",1871);
	map.put("Indian Peace Party",378);
	map.put("Parcham Party of India",648);
	map.put("Jan Sevak Party",1872);
	map.put("Rastriya Insaaf Party",1582);
	map.put("Rashtriya Ambedkar Dal",1583);
	map.put("Manavadhikar Janshakti Party",1223);
	map.put("Bharat Nirman Party",1873);
	map.put("Rashtriya Vikas Manch Party",1874);
	map.put("UTTARAKHAND PARIVARTAN PARTY",1212);
	map.put("Bhartiya Chaitanya Party",127);
	map.put("Bharat Ki Lok Jimmedar Party",177);
	map.put("Bharatiya Mool Niwasi Samaj Party",1875);
	map.put("Rashtriya Uttarakhand Party",1876);
	map.put("Rashtriya Janadhikar Suraksha Party",1877);
	map.put("Jamat-E-Seratul Mustakim",1878);
	map.put("The Religion of Man Revolving Political Party of India",885);
	map.put("Party for Democratic Socialism",632);
	map.put("Jharkhand Anushilan Party",1879);
	map.put("Gorkha Rashtriya Congress",1880);
	map.put("Rashtriya Janasachetan Party (R.J.P.)",1881);
	map.put("Indian Unity Centre",1882);
	map.put("Indian Justice Party",359);
	map.put("Nirjatita Samaj Biplabi Party",1883);

	
	stateMap.put(1,"S01");
	stateMap.put(2,"S02");
	stateMap.put(3,"S03");
	stateMap.put(4,"S04");
	stateMap.put(5,"S26");
	stateMap.put(6,"S05");
	stateMap.put(7,"S06");
	stateMap.put(8,"S07");
	stateMap.put(9,"S08");
	stateMap.put(10,"S09");
	stateMap.put(11,"S27");
	stateMap.put(12,"S10");
	stateMap.put(13,"S11");
	stateMap.put(14,"S12");
	stateMap.put(15,"S13");
	stateMap.put(16,"S14");
	stateMap.put(17,"S15");
	stateMap.put(18,"S16");
	stateMap.put(19,"S17");
	stateMap.put(20,"S18");
	stateMap.put(21,"S19");
	stateMap.put(22,"S20");
	stateMap.put(23,"S21");
	stateMap.put(24,"S22");
	stateMap.put(25,"S23");
	stateMap.put(26,"S28");
	stateMap.put(27,"S24");
	stateMap.put(28,"S25");
	stateMap.put(29,"U01");
	stateMap.put(30,"U02");
	stateMap.put(31,"U03");
	stateMap.put(32,"U04");
	stateMap.put(33,"U05");
	stateMap.put(34,"U06");
	stateMap.put(35,"U07");
	
	stateNoMap.put("S01",1);
	stateNoMap.put("S02",2);
	stateNoMap.put("S03",3);
	stateNoMap.put("S04",4);
	stateNoMap.put("S26",5);
	stateNoMap.put("S05",6);
	stateNoMap.put("S06",7);
	stateNoMap.put("S07",8);
	stateNoMap.put("S08",9);
	stateNoMap.put("S09",10);
	stateNoMap.put("S27",11);
	stateNoMap.put("S10",12);
	stateNoMap.put("S11",13);
	stateNoMap.put("S12",14);
	stateNoMap.put("S13",15);
	stateNoMap.put("S14",16);
	stateNoMap.put("S15",17);
	stateNoMap.put("S16",18);
	stateNoMap.put("S17",19);
	stateNoMap.put("S18",20);
	stateNoMap.put("S19",21);
	stateNoMap.put("S20",22);
	stateNoMap.put("S21",23);
	stateNoMap.put("S22",24);
	stateNoMap.put("S23",25);
	stateNoMap.put("S28",26);
	stateNoMap.put("S24",27);
	stateNoMap.put("S25",28);
	stateNoMap.put("U01",29);
	stateNoMap.put("U02",30);
	stateNoMap.put("U03",31);
	stateNoMap.put("U04",32);
	stateNoMap.put("U05",33);
	stateNoMap.put("U06",34);
	stateNoMap.put("U07",35);




}
	

}
