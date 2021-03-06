/**
 * Created by Administrator on 2018/3/4.
 */
var success = "<span class='label label-success'>" +"SUCCESS"+"</span>";
var danger ="<span class='label label-danger'>"+"DANGER"+"</span>"
var runing = "<span class='label label-success'>" +"RUN"+"</span>";
var stop = "<span class='label label-danger'>" +"STOP"+"</span>";
var warning ="<span class='label label-warning'>WARNING</span>";

var sign="";//记录要删除的对象

$(function(){
    loadlist();
    projetslist();
})

//创建模板
$("#creatfromyaml").click(function(){
    var  yamltext = $("#template_yaml").val();
    var  name = $("#template_name").val();
  // var p = ReplaceSeperator(s);
    $.ajax({
        url:"/engine/compose/model/creatfromyaml",
        method: "POST",
        dataType: "text",
        data: {"yaml":yamltext,"name":name},
        success : function(data){
            $ ("#btn-success").click();
            $("#close_creat_yaml_div").click();
            loadlist();

        }
    })

})

//加载模板列表
function loadlist(){
    $.ajax({
        url:"/engine/compose/model/listmodels",
        method: "GET",
        dataType: "json",
        success : function(data){
            var json = data.resultInfo.data;
            var obj = JSON.parse(json);
            var p = obj.projects;
            var active = data.resultInfo.data;
            var active_obj = JSON.parse(active);
            var active_list = active_obj.active;
            var tr = "";
            var n =1;
            var operation_del = "<button type='button' class='btn btn-danger' data-toggle='modal' data-target='#myModal_Prompt' onclick='getmodel(this)'> Del</button>";//
            var operation_down = " <button class='btn btn-warning ladda-button' data-style='expand-left' onclick='down_project(this)'><span class='ladda-label'>Down</span></button>"
            var up_project = "<button class='btn btn-primary ladda-button' data-style='expand-left' onclick='up_project(this)'><span class='ladda-label'>UP</span></button>";//

            for(var k in p) {
                var ind = stop;
                var operation  = up_project ;
                if (inspectprojects(k)) {
                    for (var j = 0; j < active_list.length - 1; j++) {
                        if (active_list.length > 1 && j % 2 == 0 && active_list[j] === k) {
                            ind = runing;
                            operation = operation_down;
                        }
                    }
                }else{
                    ind = warning;
                    operation="";
                }

                tr += "<tr>" +
                    "<td>" + n++ + "</td>" +
                    "<td>" + k + "</td>" +
                    "<td>" + p[k] + "</td>" +
                    "<td>" + ind  +"</td>" +
                    "<td>" + operation + operation_del+"</td>" +
                    "</tr>";
            }
            $("#templatesbadge").text(n-1);
            $("#template-list tbody").html(tr);

        }
    })

}


function projetslist(){
    $.ajax({
        url:"/engine/compose/model/listmodels",
        method: "GET",
        dataType: "json",
        success : function(data){
            var json = data.resultInfo.data;
            var obj = JSON.parse(json);
            var p = obj.active;
            var tr = "";
            var n =1;
            var up_project = "<button type='button' class='btn btn-primary' onclick='topan(this)'>info</button>";//
            var a = "<a href='http://192.168.46.195:8080/dfc'>http://192.168.46.195:8080/dfc</a>"
            var ind;

            for (var k=0;k<p.length-1;k++) {
                ind =  runing;//
                if (p.length > 1 && k % 2 == 0) {
                    tr += "<tr>" +
                        "<td>" + n++ + "</td>" +    /*后期得改造*/
                        "<td>" + p[k] + "</td>" +
                        "<td>" + a + "</td>" +
                        "<td>" + ind + "</td>" +
                        "<td>" + up_project +"</td>" +
                        "</tr>";
                }
            }
            $("#projectsbadge").text(n-1);
            $("#project-list tbody").html(tr);

        }
    })

}


var b ;
//检查模板的健康状况
function inspectprojects(name){
    $.ajax({
        url:"/engine/compose/model/showprojectdetails",
        method: "GET",
        dataType: "json",
        async:false,
        data: {"name":name},
        success : function(data){
           b = data.resultInfo.success;
        }
    })
    return b;

}

//删除模板
$("#delete_true").click(function(){
    $.ajax({
        url:"/engine/compose/model/deletemodelbyname",
        method: "GET",
        dataType: "json",
        async:false,
        data: {"name":sign},
        success : function(data){
            $ ("#btn-success").click();
            $("#delete_false").click();
            loadlist();
        }
    })
})

//记录选中的模板，便于删除
function getmodel(element){
    sign = $(element).parent().parent().children()[1].innerText;
}
//通过模板来创建/启动一个项目
function up_project(element){
   var name =  $(element).parent().parent().children()[1].innerText;
    $(element).addClass("loading")
    var gg = Ladda.create( document.querySelector('.loading'));
     $.ajax({
         url:"/engine/compose/model/startproject",
         method: "GET",
         dataType: "json",
         async:true,
         data: {"name":name},
         beforeSend: function(){gg.start()},
         success : function(data){
             $ ("#btn-success").click();
             $(element).removeClass("loading");
             loadlist();
             projetslist();
            // $("#projectsbadge").click();
         }
     })

}

function topan(element){
    var name =  $(element).parent().parent().children()[1].innerText;
    window.location.href='container-a.html?name='+name;
}

function down_project(element){
   var  name =  $(element).parent().parent().children()[1].innerText;
    $(element).addClass("loading")
    var gg = Ladda.create( document.querySelector('.loading'));

    $.ajax({
        url:"/engine/compose/model/downproject",
        method: "GET",
        dataType: "json",
        async:true,
        data: {"name":name},
        beforeSend: function(){gg.start()},
        success : function(data){
            $ ("#btn-success").click();
            loadlist();
            projetslist();
            // $("#projectsbadge").click();
        }
    })



}



