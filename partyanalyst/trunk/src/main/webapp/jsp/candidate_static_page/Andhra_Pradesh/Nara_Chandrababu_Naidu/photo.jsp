


<style type="text/css">
	
	.photoIndexDesc
	{
		color:#30465B;
	}
	.yui-carousel-element li 
	{ 
			height: 100px; 
			width:100px;
	} 
	#photo_gallery_main
	{
		border:1px solid #adadad;
	}
	
	.indexImgClass
	{
		border:2px solid #798FAF;
	}
	#galleryDivMain
	{
		padding-left:150px;
	}
	#galleryHead
	{		
		font-weight:bold;
		margin-right:20px;
		text-align:right;
	}
	#photoIndexAnc
	{
		color:#20425F;
		text-decoration:none;
	}

	#photoGroupsMain
	{
		height:200px;
		overflow:auto;
		padding-left:15px;		
	}
	
	.imgGroupNameSpan
	{
		padding-left:10px;
		text-decoration:underline;
	}

	.imagesGroupHeader
	{
		font-weight:bold;
		margin-top:10px;
	}

</style>

<script type="text/javascript">

/*
	{
						groupName:'Samples',
						imagesUrl:[
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Blue_hills.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Sunset.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Water lilies.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Winter.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Blue_hills.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Sunset.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Water lilies.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/sample/Winter.jpg"/>'
								  ]
					},
*/
var imagesList = [					
					{
						groupName:'Political Tour',
						imagesUrl:[
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/election_tour/cbn_elec1.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/election_tour/cbn_elec2.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/election_tour/cbn_elec3.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/election_tour/cbn_elec4.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/election_tour/cbn_elec5.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/election_tour/cbn_elec6.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/Nara_Chandrababu_Naidu/election_tour/cbn_elec7.jpeg"/>',
								  ]
					}

				 ];

</script>

<div id="photoTitleMainDiv">	

	<div id="photoGroupsMain">
		
	</div>

	<script type="text/javascript">
		buildImagesType("photoGroupsMain",imagesList);
	</script>
</div>

<div id="galleryDivMain" style="display:none;">
	<div id="galleryHead"> <a href="javascript:{}" id="photoIndexAnc" onclick="showPhotoIndexDiv()"> Back </a></div>
	<div id="spotLightDiv"></div>	
	<div id="caourselDiv" class="yui-skin-sam"></div>
</div>
