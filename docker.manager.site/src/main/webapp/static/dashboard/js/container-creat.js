var num =1;
$(document).ready(function(){

});

//dom操作
var domFun={

};

//事件操作
var eventFun={
    setStep:function(index){
        for(var i=2;i<=index;i++){
            $("#step"+i+"Li").addClass("blue").removeClass("gray");
            $("#step"+i+"Img").attr("src","assets/img/creat1.png");
        }
        for(var i=index+1;i<=4;i++){
            $("#step"+i+"Li").addClass("gray").removeClass("blue");
            $("#step"+i+"Img").attr("src","assets/img/creat3.png");
        }
        $("#step"+(index+1)+"Img").attr("src","assets/img/creat2.png");
    }
};

$("#app_add").click(function(){

   /* var trHTML = "<tr>" +
        "<td>" + num++ + "</td>" +
        "<td> <input type='text' > </td>" +
        "<td> <input type='text' placeholder='text field'> </td>" +
        "<td> <input type='text' placeholder='text field'> </td>" +
        "<td> <input type='text' placeholder='text field'> </td>" +
        "<td> <input type='text' placeholder='text field'> </td>" +
        "</tr>";
    $("#app_add_table").append(trHTML);*/


})


