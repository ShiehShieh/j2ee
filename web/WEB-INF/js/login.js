/**
 * Created by Administrator on 2015/4/14.
 */

//This code is a bit rudimentary.
//This is more of a proof of concept than code for production.
//The only thing it needs to do, however, is to check if the field has any value. The rest is done with CSS

$(document).ready(function(){
    function updateText(event){
        var input=$(this);
        setTimeout(function(){
            var val=input.val();
            if(val!="")
                input.parent().addClass("floating-placeholder-float");
            else
                input.parent().removeClass("floating-placeholder-float");
        },1)
    }
    $(".floating-placeholder input").keydown(updateText);
    $(".floating-placeholder input").change(updateText);
});