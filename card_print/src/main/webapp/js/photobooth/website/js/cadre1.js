$(function(){
	$( '#example' ).photobooth().on( "image", function( event, dataUrl ){
		$("#uploadImg").html( '<img src="' + dataUrl + '"  style="width: 140px; height: 120px;">');
		newCamPhotoTaken = true;
		newPhotoUploaded = false;
		$("#base64Image").val(dataUrl.replace("data:image/png;base64,",""));
		showNewTakenImg();
	});

	/**
	* Tab boxes
	*/
	$( '.tab_container' ).each(function( i, elem ){
		$( elem ).find( ".tabs li" ).click(function(){
			$( elem ).find( ".tabs li.selected" ).removeClass( "selected" );
			$( this ).addClass( "selected" );
			$( elem ).find( ".tab_content" ).hide();
			$( elem ).find( ".tab_content." + $(this).attr( "calls" ) ).show();
		});
	});

});