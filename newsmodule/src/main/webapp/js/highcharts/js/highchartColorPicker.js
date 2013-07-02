 var colorCount = 0;
 /* updated by srishailam (21st May,2013)
	getColorCodeForParty('INC') method is used to get a separate Static Color for corresponding
	Parties in High-charts */
function getColorCodeForParty(partyName)
	{
		var colorStatic = new Array('#19A3D1','#6A6A8A','#5C1F1F','#8A00B8','#006BB2','#7AA300','#7A7ACC','#5CB8E6','#2E5C00','#194775','#73008C','#1F8F99','#7A637A','#A6A88A','#996633','#2E0F2E','#5C5C8A','#1F7A7A','#944DDB','#738ACF','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#E3F6CE','#CEE3F6','#E0F8E0','#5E610B');

		var colorArray = new Array();
	 
	 //Iterate Parties and Set Colors 	
	 
		if(partyName.indexOf("BJP") == 0)
			colorArray.push('#FF7519');
		else if(partyName.indexOf("CPM") == 0)
			colorArray.push('#336699')
		else if(partyName.indexOf("CPI") == 0)
			colorArray.push('#FA5858');
		else if(partyName.indexOf("INC") == 0)
			colorArray.push('#006600');	
		else if(partyName.indexOf("TDP") == 0)
			colorArray.push('#FFFF00');			
		else if(partyName.indexOf("TRS") == 0)
			colorArray.push('#FF3399');
		else if(partyName.indexOf("PRP") == 0)
			colorArray.push('#00CC99');
		else if(partyName.indexOf("YSRC") == 0)
			colorArray.push('#1919FF');
		else if(partyName.indexOf("IND") == 0)
			colorArray.push('#000000');
		else{		
            colorArray.push(colorStatic[colorCount]);
            colorCount++;
			if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
		 }
	 
   return colorArray;    
  }