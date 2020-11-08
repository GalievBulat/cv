<#include  "resources/config.ftl">
<@config name="profile"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">
<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Profile"/>
    <form  method="post" action="http://localhost:8088/cv/avatar" enctype="multipart/form-data" id="avatarPicking">
        <div class="input-group mb-3" style="padding: 5% 30% 3% 30%;">
            <div class="input-group-prepend">
                <span class="input-group-text" id="bt_sbm" onclick="send()">Upload</span>
            </div>
            <div class="custom-file">
                <input type="file" name="avatar" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
            </div>
        </div>
    </form>

    <img class="rounded mx-auto d-block img-thumbnail" src="${avatar}" id = "picture" style="width: 20%; margin: 3%;">

    <script>
        function send(){
            $("#bt_sbm").prop("onclick", "");
            let form = $("#avatarPicking")[0];
            let formObj = new FormData(form);
            $.ajax({
                enctype: 'multipart/form-data',
                type: 'POST',
                url: form.action,
                data: formObj,
                processData: false,
                contentType: false,
                data_type: "text/plain",
                success: function (_data) {
                    alert("OK");
                    $("#picture").prop("src",_data);
                },
                error: function (data) {
                    alert(data.responseText);
                }
            });
        }
        let html =
        '<input />';
        window.onload = function() {
            $("#avatarPicking").toggle();
        }
        let name;
        let surname;
        let state = 0;
        function changeProfile(){
            if (state === 1){
                $("#avatarPicking").toggle();
                let root = {};
                name = $("#name").children().val();
                surname = $("#surname").children().val();
                root["name"] ='' + name;
                root["surname"] ='' + surname;
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8088/cv/profile',
                    data: JSON.stringify(root),
                    processData: false,
                    contentType: false,
                    data_type: "json",
                    success: function () {
                        $("#name").text(name);
                        $("#surname").text(surname);
                    },
                    error: function (data) {
                        document.write(data.responseText);
                    }
                });
            }
            if (state === 0) {
                $("#avatarPicking").toggle();
                $("#name").html(html);
                $("#surname").html(html);
                state++
            }
        }
    </script>


    <div id="parallelogram1" class="figure top_margin d-flex justify-content-end" style="margin-top: 1%;">
        <p id = "name" class="text skewed">${name}</p>
    </div>
    <div id="parallelogram2" class="figure top_margin d-flex justify-content-end">
        <p id = "surname"  class="text skewed">${surname}</p>
    </div>
    <div id="parallelogram3" class="figure top_margin d-flex justify-content-end">
        <p id = "tc" class="text skewed ">${tc}</p>
    </div>
    <button type="button" class="btn btn-dark main_button top_margin d-flex justify-content-center" onclick="changeProfile()" style="
    margin: auto; margin-bottom: 3%; margin-top: 3%;">Изменить профиль</button>
</div>

<@bootstrapjs></@bootstrapjs>
</body>
</html>