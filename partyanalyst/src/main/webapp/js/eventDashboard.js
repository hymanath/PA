var eventColorArr = [];
function setcolorsForEvents()
	{
		eventColorArr = new Array();
		var colorStatic = new Array('#FFEE1A','#48D1CC','#00B17D','#D64D54','#5C2D25','#7AA300','#7A7ACC','#5CB8E6','#2E5C00','#194775','#73008C','#1F8F99','#7A637A','#A6A88A','#996633','#2E0F2E','#5C5C8A','#1F7A7A','#944DDB','#738ACF','#120B0B','#800B0B','#1FCB9D','#90446A','#A9F5A9','#F8E0F7','#CECEF6','#A9F5D0','#8000FF','#E3F6CE','#CEE3F6','#E0F8E0','#5E610B','#4682B4','#008080','#9ACD32','#800080');
		var colorArray = new Array();
		var colorCount = 0;
		if(subEvents.length > 0)
		for(var i in subEvents)
		{
		 
				var obj = {
				 eventId : subEvents[i],
				 color : colorStatic[colorCount]
				}
				eventColorArr.push(obj)
			  
				if(colorCount == (colorStatic.length)-1)
				colorCount = 0;
				 colorCount++;
		}
		
		return eventColorArr;
	}

	function getColorCodeByEvent(eventId)
	{
		if(eventColorArr != null && eventColorArr.length > 0)
		{
			for(var i in eventColorArr)
			{
				if(eventColorArr[i].eventId == eventId)
					return eventColorArr[i].color;
			}
		}
	}
