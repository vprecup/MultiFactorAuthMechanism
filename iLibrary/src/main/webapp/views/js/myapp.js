$( document ).ready(function() {
	/**/
	$('#registerForm').submit(function(e)
    {     
        e.preventDefault();
        var $form = $(this);

        $.ajax({
	  		method: "POST",
	  		dataType: "json",
	  		data: $(this).serialize(),
	  		url: "validateRegister"
		})
	  	.done(function(data) {
	    	if(data.result === "success")
	    	{
	    		$('#registerForm').unbind('submit').submit();
	    	}
	    	else
	    	{
	    		$('span.error').html("");
	    		$.each(data.errors, function(i, item) {
			    	$('#'+i).prev('span').html(item);
		  		});
		  		$('span.error').show();
		  		setTimeout(function(){
				  	$('span.error').hide();
				}, 2000);

	    		return false;
	    	}
	  	});
    });
});


