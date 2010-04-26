function buildCandidatePhotoGallery(arr)
{
	var photoElmt = document.getElementById("photoTitleMainDiv");
	var divElmt = document.getElementById("galleryDivMain");
	
	if(photoElmt && divElmt)
	{
		photoElmt.style.display = 'none';
		divElmt.style.display = 'block';
	}

	var courselElmt = document.getElementById("caourselDiv");
	var spotLightElmt = document.getElementById("spotLightDiv");

	

	var str = '';
	str +='<ul>'; 
	for(var i in arr)
	{
		str +='  <li>';
		str +=arr[i]; 
		str +='  </li>';
	}
	str +='</ul>';
	
	courselElmt.innerHTML = str;

	var carousel = new YAHOO.widget.Carousel("caourselDiv",
						{
							carouselEl: "UL",
							isCircular: true,
							isVertical: false,
							numVisible: 3,
							animation: { speed: 1.0 },
							autoPlayInterval: 2000
						});
	carousel.on("itemSelected", function (index) { 
		var item = carousel.getElementForItem(index); 
		
		if (item)
		{ 
			spotLightElmt.innerHTML = '<img src="' + getImage(item) + '" height="250" width="312">';
		} 
	}); 

	carousel.render(); 
	carousel.show();
}

function getImage(parent)
{
	 var el = parent.firstChild; 
	 return el.src;
}

function showPhotoIndexDiv()
{
	var photoElmt = document.getElementById("photoTitleMainDiv");
	var divElmt = document.getElementById("galleryDivMain");

	photoElmt.style.display = 'block';
	divElmt.style.display = 'none';
}