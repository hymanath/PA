 var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-7871302-2']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  
  function setStaticPartyColorsForInteractiveCharts(parties)
  {
    var colorStatic = new Array('#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B',
		'#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B',
		'#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B');

	 var colorArray = new Array();
	 
	 //Iterate Parties and Set Colors 
	 var colorCount = 0;
	 for(var i=0; i<parties.length; i++)
	 {
		 if(parties[i].partyName == 'TDP')
			 colorArray.push('#C7005D');
		 else if(parties[i].partyName == 'TRS')
             colorArray.push('#F5A9F2');
		 else if(parties[i].partyName == 'INC')
             colorArray.push('#2A00D2');
		 else if(parties[i].partyName == 'BJP')
             colorArray.push('#FE9A2E');
		 else if(parties[i].partyName == 'PRP')
             colorArray.push('#74DF00');
		 else if(parties[i].partyName == 'CPI')
             colorArray.push('#FA5858');
		 else if(parties[i].partyName == 'CPM')
             colorArray.push('gray');
		 else
             colorArray.push(colorStatic[colorCount++]);
	 }

   return colorArray;
    
  }

  function setStaticColorsForInteractiveChartsForPartiesArray(parties)
  {
    var colorStatic = new Array('#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B',
		'#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B',
		'#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B');

	var colorArray = new Array();
	 
	 //Iterate Parties and Set Colors 
	 var colorCount = 0;
	 for(var i=0; i<parties.length; i++)
	 {
		 if(parties[i] == 'TDP')
			 colorArray.push('#C7005D');
		 else if(parties[i] == 'TRS')
             colorArray.push('#F5A9F2');
		 else if(parties[i] == 'INC')
             colorArray.push('#2A00D2');
		 else if(parties[i] == 'BJP')
             colorArray.push('#FE9A2E');
		 else if(parties[i] == 'PRP')
             colorArray.push('#74DF00');
		 else if(parties[i] == 'CPI')
             colorArray.push('#FA5858');
		 else if(parties[i] == 'CPM')
             colorArray.push('gray');
		 else{
             colorArray.push(colorStatic[colorCount]);
             colorCount++;
		 }
	 }

   return colorArray;
    
  }

  function setStaticColorsForInteractiveChartsForCensusAndPartiesArray(parties)
  {
    var colorStatic = new Array('#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B',
		'#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B',
		'#088A85','#81BEF7','#B45F04','#585858','#AEB404','#ADE8E0','#C4D296','#E5C55A','#F7C37E','#9C8AE2','#B4045F','#D0A9F5','#CBBEAB','#BCE5BF','#FAE2BD','#95B8E5','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#AEB404','#E3F6CE','#CEE3F6','#D0A9F5','#E0F8E0','#5E610B');

	var colorArray = new Array();
	 
	 //Iterate Parties and Set Colors 
	 var colorCount = 0;
	 for(var i=0; i<parties.length; i++)
	 {
		 if(parties[i] == 'Total Population')
			 colorArray.push('#82CAFF');
		 else if(parties[i] == 'Male Population')
             colorArray.push('#82CAFF');
		 else if(parties[i] == 'Female Polpultion')
             colorArray.push('#82CAFF');
		 else if(parties[i] == 'SC Population')
             colorArray.push('#82CAFF');
		 else if(parties[i] == 'ST Population')
             colorArray.push('#82CAFF');
		 else if(parties[i] == 'TDP')
			 colorArray.push('#C7005D');
		 else if(parties[i] == 'TRS')
             colorArray.push('#F5A9F2');
		 else if(parties[i] == 'INC')
             colorArray.push('#2A00D2');
		 else if(parties[i] == 'BJP')
             colorArray.push('#FE9A2E');
		 else if(parties[i] == 'PRP')
             colorArray.push('#74DF00');
		 else if(parties[i] == 'CPI')
             colorArray.push('#FA5858');
		 else if(parties[i] == 'CPM')
             colorArray.push('gray');
		 else{
             colorArray.push(colorStatic[colorCount]);
             colorCount++;
		 }
	 }

   return colorArray;
    
  }