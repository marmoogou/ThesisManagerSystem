$(function(){
	var $text = $('.text-title').val();
	
	$('.text-title').focus(function(){
		if($(this).val() == $text){
			$(this).attr('value','');
		}
	})
	$('.text-title').blur(function(){
		if($(this).val() == ''){
			$(this).val($text);
		}
		
	
	})
	
	$(".isTrue").click(function(){
		if(confirm("确定选择该论题吗？") == true){
			return true;
		}else{
			return false;
		}
	})
	
//	$('.button-title').click(function(){
//		$('.feedback').toggle(600);
//	})
	
	
	
	//设置查重相关
	//var check = '';
	$(".button-title").click(function(){
		if(confirm("确定查重？") == true){
			$(".isTrue")
		}else{
			$(".isTrue")
						
		}
	})
})

	
