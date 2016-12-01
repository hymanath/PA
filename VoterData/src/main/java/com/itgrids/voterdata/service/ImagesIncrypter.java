package com.itgrids.voterdata.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ImagesIncrypter {
	
	static Map<Integer,Integer> pwdMap = new HashMap<Integer,Integer>(0);
	
	
	public static void makeZipWithAESEncryption(String sourceDirPath,String targetZipFilePath,String password) {
		
		try {
			
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
			parameters.setPassword(password);
			
			ZipFile zipFile = new ZipFile(targetZipFilePath);
			
			ArrayList<File> filesToAdd = new ArrayList<File>();
			
			File sourceDir = new File(sourceDirPath);
			
			if(sourceDir.isDirectory())
			{
				for(File f1 : sourceDir.listFiles())
				{
					if(f1.isDirectory())
					{
						zipFile.addFolder(f1, parameters);
						System.out.println(f1.getName()+" Completed");
					}
					else
					filesToAdd.add(f1);
				}
			}
			
			if(filesToAdd.size() > 0)
			zipFile.addFiles(filesToAdd, parameters);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		pwdMap.put(1,37824799);
		pwdMap.put(2,86641151);
		pwdMap.put(3,19356432);
		pwdMap.put(4,15630604);
		pwdMap.put(5,27647098);
		pwdMap.put(6,38840569);
		pwdMap.put(7,24730217);
		pwdMap.put(8,47831322);
		pwdMap.put(10,22059848);
		pwdMap.put(11,61977778);
		pwdMap.put(12,73302512);
		pwdMap.put(13,90189289);
		pwdMap.put(15,21983859);
		pwdMap.put(16,64917380);
		pwdMap.put(18,78462799);
		pwdMap.put(20,85566427);
		pwdMap.put(21,52789767);
		pwdMap.put(23,27119788);
		pwdMap.put(24,93834328);
		pwdMap.put(26,59807714);
		pwdMap.put(30,30024732);
		pwdMap.put(31,50854448);
		pwdMap.put(32,66967829);
		pwdMap.put(34,62625158);
		pwdMap.put(35,51502426);
		pwdMap.put(36,88751624);
		pwdMap.put(37,22433532);
		pwdMap.put(39,42635377);
		pwdMap.put(40,99586806);
		pwdMap.put(41,11473423);
		pwdMap.put(43,13895272);
		pwdMap.put(44,35795548);
		pwdMap.put(46,40255478);
		pwdMap.put(47,26521671);
		pwdMap.put(49,45494436);
		pwdMap.put(50,30641744);
		pwdMap.put(51,65716177);
		pwdMap.put(52,47538439);
		pwdMap.put(53,16960196);
		pwdMap.put(54,68765379);
		pwdMap.put(55,60110503);
		pwdMap.put(56,40789262);
		pwdMap.put(57,32548717);
		pwdMap.put(58,76188989);
		pwdMap.put(59,71000692);
		pwdMap.put(60,96891234);
		pwdMap.put(61,26316889);
		pwdMap.put(62,34257595);
		pwdMap.put(63,96910718);
		pwdMap.put(64,93583629);
		pwdMap.put(65,61141450);
		pwdMap.put(66,63808372);
		pwdMap.put(67,21591384);
		pwdMap.put(68,81142104);
		pwdMap.put(69,70783846);
		pwdMap.put(70,17395237);
		pwdMap.put(71,67418272);
		pwdMap.put(73,28579288);
		pwdMap.put(74,89024478);
		pwdMap.put(75,91177123);
		pwdMap.put(77,88159442);
		pwdMap.put(78,63687107);
		pwdMap.put(79,15949406);
		pwdMap.put(80,93905917);
		pwdMap.put(81,26256074);
		pwdMap.put(82,70491628);
		pwdMap.put(84,94865986);
		pwdMap.put(85,49322809);
		pwdMap.put(86,15589797);
		pwdMap.put(87,43592691);
		pwdMap.put(89,71109373);
		pwdMap.put(91,72130438);
		pwdMap.put(92,52444544);
		pwdMap.put(93,32949325);
		pwdMap.put(94,36236045);
		pwdMap.put(97,55867729);
		pwdMap.put(100,87247435);
		pwdMap.put(101,65594810);
		pwdMap.put(102,55837480);
		pwdMap.put(103,62316307);
		pwdMap.put(104,30113336);
		pwdMap.put(105,73892380);
		pwdMap.put(107,90442913);
		pwdMap.put(108,78021011);
		pwdMap.put(109,69301570);
		pwdMap.put(111,93532341);
		pwdMap.put(112,21986491);
		pwdMap.put(113,45834691);
		pwdMap.put(114,46817014);
		pwdMap.put(116,96730229);
		pwdMap.put(117,25149218);
		pwdMap.put(120,68201130);
		pwdMap.put(121,63635829);
		pwdMap.put(122,42449820);
		pwdMap.put(124,11422567);
		pwdMap.put(125,50332825);
		pwdMap.put(127,62675138);
		pwdMap.put(129,87455905);
		pwdMap.put(133,75701453);
		pwdMap.put(134,59381371);
		pwdMap.put(135,88988021);
		pwdMap.put(136,39927884);
		pwdMap.put(137,62378468);
		pwdMap.put(138,67757686);
		pwdMap.put(140,75256723);
		pwdMap.put(141,33761013);
		pwdMap.put(146,78716998);
		pwdMap.put(147,97475151);
		pwdMap.put(149,41556738);
		pwdMap.put(152,31714816);
		pwdMap.put(153,72813189);
		pwdMap.put(155,54216347);
		pwdMap.put(156,54453985);
		pwdMap.put(157,17069244);
		pwdMap.put(159,84647225);
		pwdMap.put(160,14494357);
		pwdMap.put(163,79182603);
		pwdMap.put(167,26591246);
		pwdMap.put(168,40378056);
		pwdMap.put(169,80834723);
		pwdMap.put(170,97876176);
		pwdMap.put(171,18207483);
		pwdMap.put(172,68407446);
		pwdMap.put(173,56447803);
		pwdMap.put(174,98526148);
		pwdMap.put(176,96793576);
		pwdMap.put(177,15162975);
		pwdMap.put(178,79518203);
		pwdMap.put(179,87733690);
		pwdMap.put(180,99366022);
		pwdMap.put(181,25962511);
		pwdMap.put(182,63472017);
		pwdMap.put(184,21519719);
		pwdMap.put(185,84836564);
		pwdMap.put(186,11853914);
		pwdMap.put(187,37925524);
		pwdMap.put(191,15943220);
		pwdMap.put(192,22151275);
		pwdMap.put(193,58427189);
		pwdMap.put(194,23743234);
		pwdMap.put(195,93882788);
		pwdMap.put(196,86489832);
		pwdMap.put(199,92451573);
		pwdMap.put(203,34305058);
		pwdMap.put(205,49187531);
		pwdMap.put(206,45327847);
		pwdMap.put(207,51345539);
		pwdMap.put(208,49531333);
		pwdMap.put(209,81386152);
		pwdMap.put(210,75356738);
		pwdMap.put(211,33660444);
		pwdMap.put(212,66021243);
		pwdMap.put(213,81207231);
		pwdMap.put(214,47928365);
		pwdMap.put(215,61904369);
		pwdMap.put(216,73799586);
		pwdMap.put(217,96085957);
		pwdMap.put(218,44855577);
		pwdMap.put(219,38645783);
		pwdMap.put(221,72929821);
		pwdMap.put(222,31447989);
		pwdMap.put(223,65279997);
		pwdMap.put(224,43758488);
		pwdMap.put(225,28274129);
		pwdMap.put(226,23014396);
		pwdMap.put(227,12649172);
		pwdMap.put(228,59236624);
		pwdMap.put(229,14338721);
		pwdMap.put(231,52770239);
		pwdMap.put(232,88292333);
		pwdMap.put(233,93686981);
		pwdMap.put(236,74059983);
		pwdMap.put(237,33612960);
		pwdMap.put(238,98970807);
		pwdMap.put(239,98040216);
		pwdMap.put(241,84834010);
		pwdMap.put(242,29245870);
		pwdMap.put(243,73452582);
		pwdMap.put(244,23001440);
		pwdMap.put(245,52338029);
		pwdMap.put(246,84545480);
		pwdMap.put(248,77369444);
		pwdMap.put(249,13780448);
		pwdMap.put(250,94677711);
		pwdMap.put(251,27500042);
		pwdMap.put(252,65326603);
		pwdMap.put(253,56855156);
		pwdMap.put(254,68014021);
		pwdMap.put(255,58761994);
		pwdMap.put(257,14186442);
		pwdMap.put(258,30780130);
		pwdMap.put(260,82879764);
		pwdMap.put(261,22547017);
		pwdMap.put(262,98897810);
		pwdMap.put(263,82846194);
		pwdMap.put(264,16038693);
		pwdMap.put(265,46602032);
		pwdMap.put(267,91251932);
		pwdMap.put(270,79499311);
		pwdMap.put(271,78771488);
		pwdMap.put(272,80756073);
		pwdMap.put(273,46975885);
		pwdMap.put(275,93301404);
		pwdMap.put(276,33263019);
		pwdMap.put(277,87625478);
		pwdMap.put(278,32103142);
		pwdMap.put(279,11985500);
		pwdMap.put(280,98198491);
		pwdMap.put(281,93180598);
		pwdMap.put(282,79168749);
		pwdMap.put(283,63285392);
		pwdMap.put(284,95867191);
		pwdMap.put(285,45077622);
		pwdMap.put(286,22197960);
		pwdMap.put(288,47108689);
		pwdMap.put(289,40470119);
		pwdMap.put(290,90175148);
		pwdMap.put(291,69935863);
		pwdMap.put(294,41487896);
		pwdMap.put(295,83541704);
		pwdMap.put(296,71899630);
		pwdMap.put(297,47616767);
		pwdMap.put(298,66356529);
		pwdMap.put(299,39431998);
		pwdMap.put(300,34276861);
		pwdMap.put(301,33510810);
		pwdMap.put(302,64813636);
		pwdMap.put(303,14658098);
		pwdMap.put(304,26607275);
		pwdMap.put(305,77988414);
		pwdMap.put(306,17532374);
		pwdMap.put(307,73433379);
		pwdMap.put(308,74180105);
		pwdMap.put(309,28499958);
		pwdMap.put(310,94376129);
		pwdMap.put(311,16659338);
		pwdMap.put(312,72788984);
		pwdMap.put(313,46749079);
		pwdMap.put(314,35528010);
		pwdMap.put(315,57981221);
		pwdMap.put(316,57544847);
		pwdMap.put(317,40881545);
		pwdMap.put(318,80061015);
		pwdMap.put(319,20182254);
		pwdMap.put(320,18383748);
		pwdMap.put(321,42107138);
		pwdMap.put(322,78763274);
		pwdMap.put(323,79099420);
		pwdMap.put(324,31499325);
		pwdMap.put(325,51209187);
		pwdMap.put(326,29478907);
		pwdMap.put(327,33036357);
		pwdMap.put(328,58478695);
		pwdMap.put(329,27812399);
		pwdMap.put(330,31274297);
		pwdMap.put(331,93404692);
		pwdMap.put(332,98807370);
		pwdMap.put(333,16930964);
		pwdMap.put(334,34985913);
		pwdMap.put(335,91062850);
		pwdMap.put(336,21446961);
		pwdMap.put(337,83185275);
		pwdMap.put(338,98787836);
		pwdMap.put(339,52882073);
		pwdMap.put(340,18239995);
		pwdMap.put(341,97809398);
		pwdMap.put(342,32759527);
		pwdMap.put(343,26859953);
		pwdMap.put(344,89563676);
		pwdMap.put(345,43224637);
		pwdMap.put(346,63351676);
		pwdMap.put(347,42794994);
		pwdMap.put(348,72371736);
		pwdMap.put(349,27300213);
		pwdMap.put(350,38218171);
		pwdMap.put(351,60147641);
		pwdMap.put(352,15220516);
		pwdMap.put(353,60061591);
		pwdMap.put(354,12711947);
		pwdMap.put(355,71858778);
		pwdMap.put(356,35232722);
		pwdMap.put(357,73842803);
		pwdMap.put(358,67368535);
		pwdMap.put(359,72387407);
		pwdMap.put(360,59050406);
		pwdMap.put(361,54916803);
		pwdMap.put(362,50897480);
		pwdMap.put(363,28572786);
		pwdMap.put(364,80844616);
		pwdMap.put(365,68201198);
		pwdMap.put(366,14511243);
		pwdMap.put(367,54685071);
		pwdMap.put(368,58323077);
		pwdMap.put(369,49402100);
		
		List<Integer> constList = new ArrayList<Integer>(0);
		constList.add(348);
		
		for(Integer constituencyId : constList)
		{
			System.out.println("------------------- Constituency : "+constituencyId+" ------------ Started");
			Date d1 = new Date();
			encryptImages(constituencyId,"D:/Voter_Images_2016/TS/Voter_images/Images/"+constituencyId,Integer.valueOf(pwdMap.get(constituencyId)).toString());
			System.out.println("------------------- Constituency : "+constituencyId+" ------------ Ended");
			Date d2 = new Date();
			System.out.println("Time Taken For Constituency - "+constituencyId + " -- "+(d2.getTime()-d1.getTime())/(1000*60)+" Mins");
		}
		//"Kupp@m!292$"
		//imagesIncrypter.makeZipWithAESEncryption("C:/Voter_Images_2016/cadre_images/282/","C:/Voter_Images_2016_Encrypt/282/cadre_images.zip");
	}
	
	public static void encryptImages(Integer constituencyId,String conDirFolder,String password)
	{
		File dir = new File(conDirFolder);
		for(File file : dir.listFiles())
		{
			System.out.println(file.getName()+" Started - "+new Date());
			File targetFile = new File("D:/Voter_Images_2016/TS/Encpted_Images/"+constituencyId+"/"+file.getName()+".zip");
			File targetFileParent = new File(targetFile.getParent());
			targetFileParent.mkdirs();
			makeZipWithAESEncryption(file.getAbsolutePath(),targetFile.getAbsolutePath(),password);
		}
	}
	
}
