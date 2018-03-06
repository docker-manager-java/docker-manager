/**
 * Created by Administrator on 2018/3/4.
 */
var success = "<span class='label label-success'>" +"SUCCESS"+"</span>";
var danger ="<span class='label label-danger'>"+"DANGER"+"</span>"
//创建compose项目

$(function(){
    loadlist();
})
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

function loadlist(){
    $.ajax({
        url:"/engine/compose/model/listmodels",
        method: "GET",
        dataType: "json",
        success : function(data){
            var json = data.resultInfo.data;
            var obj = JSON.parse(json);
           var p = obj.projects;
            var tr = "";
            var n =1;
            var operation_start = "<button type='button' class='btn btn-danger' onclick='deletemodel(this)'><i class='fa fa-trash-o'></i> Danger</button>";//data-toggle='modal' data-target='#myModal_Prompt'
            var ind;

            for(var k in p) {
                ind = inspectprojects(k)?success:danger
                tr += "<tr>" +
                    "<td>" + n++ + "</td>" +
                    "<td>" + k + "</td>" +
                    "<td>" + p[k] + "</td>" +
                    "<td>" + ind  +"</td>" +
                    "<td>" + operation_start  +"</td>" +
                    "</tr>";
            }
            $("#project-list tbody").html(tr);
        }
    })

}
var b ;
function inspectprojects(name){

    $.ajax({
        url:"/engine/compose/model/inspectprojects",
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
function deletemodel(element){
    var name = $(element).parent().parent().children()[1].innerText;
    $.ajax({
        url:"/engine/compose/model/deletemodelbyname",
        method: "GET",
        dataType: "json",
        async:false,
        data: {"name":name},
        success : function(data){
            $ ("#btn-success").click();
           loadlist();
        }
    })
}

