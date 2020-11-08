<#include  "resources/config.ftl">
<@config name="reg"/>
<#include "resources/nav.ftl">
<#include "resources/background.ftl">

<div style="margin-top: 56px">
    <#include  "resources/header.ftl">
    <@header text ="Forum"/>
    <div class="figure2" style="width: 80%;padding: 10px">
        <#if errorMessage?has_content >
            <p class="highlight">${errorMessage}</p>
        </#if>
        <form id="comment">
            <div class="form-group">
                <label for="exampleFormControlSelect2">Категория</label>
                <select id = "categories" multiple class="form-control" id="exampleFormControlSelect2">
                    <#list categories as item>
                        <option value="${item?counter}">${item}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label id ="parent" for="exampleFormControlTextarea1">Комментарий</label>
                <input id = "message" class="form-control" id="exampleFormControlTextarea1" rows="2"/>
            </div>
            <button type="button" class="btn btn-dark" onclick="sendComment()">Отправить комментарий</button>
        </form>
        <script>
            let parentId=0;
            function sendComment() {
                let root = {};
                let message= document.getElementById('message').value;
                let categories= $('#categories').val();
                root["parent_id"] = '' + parentId;
                root["author_id"] = '' + <#if author_id?has_content>${author_id}<#else>0</#if>;
                root["message"] = message;
                root["categories"] = '' + categories;
                let json = JSON.stringify(root);
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8088/cv/forum',
                    data: json,
                    processData: false,
                    contentType: false,
                    data_type: "json",
                    success: function (data) {
                    },
                    error: function (_data) {
                        alert(JSON.stringify(_data));
                    }
                });
            }
        </script>

        <p>Обсуждения</p>
        <#include "resources/post.ftl">
        <div id = "discussions">
            <#--<#list postsList as item>
                <div<#if item.getParentId()!=0> style="margin-left: 10%"</#if> class="card">
                    <div class="card-body">
                        <h5 class="card-title">${item.getAuthorId()}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">Categories</h6>
                        <#if item.getParentId()!=0><p class="card-text">В ответ ${item.getParentId()}</p></#if>
                        <p class="card-text">${item.getMessage()}</p>
                        <a onclick="getComments(${item.getId()})" class="card-link">Коментарии</a>
                    </div>
                </div>
            </#list>-->
        </div>

        <script>
            function addParent(id,parent_name) {
                parentId=id;
                $("#parent").text( 'Ответьте '+ parent_name);
            }
            function printPosts(data){
                let json = JSON.parse(data);
                if (json.length !== 0) {
                    for (it in json) {
                        let head = '<div id ="'+json[it]["id"] +'" class=\"card\" class=\"card-body\"> <div class="card-body"> ';
                        let answer = '';
                        if (json[it]["parent_id"] !== 0) {
                            head = '<div id ="'+json[it]["id"] + '" style="margin-left: 10% " ' + ' class=\"card\" class=\"card-body\"> <div class="card-body"> <h5 class="card-title">';
                            answer = '<p class="card-text">В ответ ' + json[it]["parent_name"] +'</p>';
                        }
                        let author='<h5 class="card-title">' + json[it]["author_name"] + '</h5> ';
                        let categories = '';
                        if(json[it]["categories"].length>0){
                            let catIds = "";
                            for (iter in json[it]["categories"]) {
                                catIds+= json[it]["categories"][iter] + ' ';
                            }
                            categories = '<h6 class="card-subtitle mb-2 text-muted">Категории: ' + catIds + '</h6>';
                        }
                        let addressedMessage = answer +'<p class="card-text">'+ json[it]["message"] +'</p>';
                        let end= '</div></div>';
                        let html = head + author + categories+ addressedMessage;
                        let comment = '<a class="card-link" onclick="addParent('+json[it]["id"]+',\''+ json[it]["author_name"] +'\')">Коментировать </a>';
                        if (json[it]["comments_num"]!== 0){
                            html+='<a onclick="getComments(' + json[it]["id"] + ')" class="card-link">Коментарии</a>';
                        }
                        html+=comment
                        html+=end;
                        if (json[it]["parent_id"] === 0) {

                            $("#discussions").append($(html));
                            currentPost++;
                        }else{
                            let num = (json[it]["parent_id"]);
                            ($(html)).insertAfter($("#" + num));
                        }

                    }
                }
            }
            function getComments(id){
                let json = JSON.stringify(id);
                $.ajax({
                    type: 'POST',
                    url: "http://localhost:8088/cv/get_comments",
                    data: json,
                    processData: false,
                    contentType: "json",
                    data_type: "json",
                    success: function (data) {
                        printPosts(data);
                        $("#" + id).children(".card-body").children("a.card-link").remove();
                    },
                    error: function (data) {
                        document.write(data.responseText);
                    }
                });
            }
        </script>
</div>


<@bootstrapjs></@bootstrapjs>
<script>
    let currentPost= <#if currentPost?has_content>${currentPost}<#else>0</#if>;
    let i =0;
        $(window).scroll(function(){
            if($(window).scrollTop()+$(window).height()>=$(document).height()*0.95) {
                i++;
                if(i === 1) {
                    $.ajax({
                        type: 'POST',
                        url: "http://localhost:8088/cv/update_comments",
                        data: currentPost,
                        processData: false,
                        contentType: "text/plain",
                        data_type: "json",
                        success: function (data) {
                            printPosts(data);
                            i=0;
                        },
                        error: function (data) {
                            document.write(data.responseText);
                            i=0;
                        }
                    });
                }
            }
        });
</script>
</div>
</body>
</html>