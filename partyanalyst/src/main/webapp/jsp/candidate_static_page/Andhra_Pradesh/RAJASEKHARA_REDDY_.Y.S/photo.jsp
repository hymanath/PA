
Photo Gallery Will Be Updated Soon.


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
	.yui-carousel {
    overflow: hidden;
    position: absolute;
    text-align: left;
   }
	#photo_gallery_main
	{
		border:1px solid #adadad;
	}
	#photoTitleDiv
	{
		height:150px;
		margin-bottom:10px;
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
</style>

<script type="text/javascript">

var ImagesURL = {
					sampleImagesURL:[
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Blue_hills.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Sunset.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Water lilies.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Winter.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Blue_hills.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Sunset.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Water lilies.jpg"/>',
										 '<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Winter.jpg"/>'
									],
					politicalTourImages:[
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr1.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr2.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr3.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr4.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr5.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr6.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr7.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr8.jpeg"/>',
											'<img height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr9.jpeg"/>',
											
										]
					
				};

</script>

<div id="photoTitleMainDiv">
	<div id="photoTitleDiv">
		<div id="photoTitleDivHead"> ${candidateVO.candidateName}'s Photo Gallery :</div>
		<div id="photoTitleDivBody"> Welcome to photo gallery.This gallery has a collection of photos divided into different categories.</div>
	</div>
	<div id="photoGroupsDiv">
		<fieldset id="photoGroupsField">
			<legend> Photo Group's</legend>
			<table width="100%">
			<tr>
			<td width="50%">
				<div id="samplePhotosIndexDiv">
					<table>
						<tr>
							<td>
								<a href="javascript:{}" onclick="buildCandidatePhotoGallery(ImagesURL.sampleImagesURL)">
									<img class="indexImgClass" height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/sample/Blue_hills.jpg"/>
								</a>
							</td>
							<td>
								<div class="photoIndexDesc">
									<div>Sample photos</div>
									<div style="padding-left:100px;padding-top:25px;cursor:pointer;"> Click Here </div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
			<td width="50%">
				<div id="electionTourPhotosIndexDiv">
					<table>
						<tr>
							<td>
								<a href="javascript:{}" onclick="buildCandidatePhotoGallery(ImagesURL.politicalTourImages)">
									<img class="indexImgClass" height="100" width="100" src="images/photo_gallery/Andhra_Pradesh/RAJASEKHARA_REDDY_.Y.S/election_tour/ysr1.jpeg"/>
								</a>
							</td>
							<td>
								<div class="photoIndexDesc">
									<div>Y S RAJASHEKAR REDDY'S Election <br/>Tour photo's <br/></div>
									<div style="padding-left:100px;padding-top:25px;cursor:pointer;"> Click Here </div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</td>
			</tr>
			</table>	
		</fieldset>
	</div>	
</div>

<div id="galleryDivMain" >
	<div id="galleryHead"> <a href="javascript:{}" id="photoIndexAnc" onclick="showPhotoIndexDiv()"> Back </a></div>
	<div id="spotLightDiv"></div>	
	<div id="caourselDiv" class="yui-skin-sam"></div>
</div>
-->