$(function(){
	$( '#example' ).photobooth().on( "image", function( event, dataUrl ){
		$("#uploadImg").html( '<img src="' + dataUrl + '"  style="width: 140px; height: 120px;">');
		$("#base64Image").val(dataUrl.replace("data:image/png;base64,",""));
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

	/**
	* Link highlighting
	*/
	$( "a" ).click(function(){
		$( "#nav a.selected" ).removeClass( "selected" );
		$( "#nav a[href=" + $(this).attr( "href" ) + "]" ).addClass( "selected" );
	});
});