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


$("#creat_submit").click(function(){
    var creat_name = $("#creat_name").val();
    var creat_ip = $("#creat_ip").val();
    var creat_ver = $("#creat_ver").val();
    var datas ={};
    datas.name =creat_name;
    datas.ip = creat_ip;
    datas.ver  =creat_ver;
    datas.data = {};
    var tb=document.getElementById("app_add_table");    //获取table对像
    var rows=tb.rows;
    var list_0 =[]//记录列名称
    var list = [];//记录行级成员
    for(var i=0;i<rows.length;i++){    //--循环所有的行
        var list_obj ={};
        var map_=new Map();
        var cells=rows[i].cells;
        for(var j=1;j<cells.length;j++){   //--循环所有的列
            if(i === 0){
                list_0.push(cells[j].innerHTML)
            }else{
                list_obj[[list_0[j-1]]] =cells[j].innerHTML
            }
        }
        if(i != 0){
        list.push(list_obj)
        }
    }
    datas.data ="{ data:"+JSON.stringify(list)+"}";
    $.ajax({
        url:"/engine/compose/model/creat",
        method: "POST",
        dataType: "json",
        data:datas,
        success : function(data){
        }
    })

})

