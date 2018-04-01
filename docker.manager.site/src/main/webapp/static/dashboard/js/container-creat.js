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

$("#creat").click(function(){
    var appname = $("#appname").val();
    var imanges = $("#images").val();
    var hostname = $("#hostname").val();
    var ports = $("#ports").val();
    var links = $("#links").val();
    var volumes = $("#volumes").val();
    var mem_limit = $("#mem_limit").val();

    var trHTML = "<tr>" +
        "<td>" + num++ + "</td>" +
        "<td>"+ appname +"</td>" +
        "<td>"+ imanges +"</td>" +
        "<td>"+ hostname +"</td>" +
        "<td>"+ ports +"</td>" +
        "<td>"+ links +"</td>" +
        "<td>"+ volumes +"</td>" +
        "<td>"+ mem_limit +"</td>" +
        "</tr>";
    $("#close_creat").click();
    $("#app_add_table").append(trHTML);


})


