<#include  "resources/config.ftl">
<@config name="profile"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">
<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Профиль"/>
    <#include "resources/errors.ftl">
    <#include "resources/alert.ftl">
    <form  method="post" action="http://localhost:8088/cv/avatar" enctype="multipart/form-data" id="avatarPicking">
        <div class="input-group mb-3" style="padding: 3% 30% 1% 30%;">
            <div class="input-group-prepend">
                <span class="input-group-text" id="bt_sbm" onclick="send()" data-toggle="modal" data-target="#exampleModal">Upload</span>
            </div>
            <div class="custom-file">
                <input type="file" name="avatar" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
            </div>
        </div>
    </form>

    <img class="rounded mx-auto d-block " src="${avatar}" id = "picture" style="border: 5px solid #dee2e6;width: 10%; margin: 3%;">


    <div id="parallelogram1" class="figure top_margin d-flex justify-content-end" style="margin-top: 1%;">
        <p id = "name" class="text skewed">${name}</p>
    </div>
    <div id="parallelogram2" class="figure top_margin d-flex justify-content-end">
        <p id = "surname"  class="text skewed">${surname}</p>
    </div>
    <div id="parallelogram3" class="figure top_margin d-flex justify-content-end">
        <p id = "tc" class="text skewed ">Номер карты: ${tc}</p>
    </div>
    <button id ="change" type="button" class="btn btn-dark main_button top_margin d-flex justify-content-center" onclick="changeProfile()" style="
    margin: auto; margin-bottom: 3%; margin-top: 3%;font-size: 200%;">Изменить профиль</button>
    <a href="http://localhost:8088/cv/tc">
        <button type="button" class="btn btn-dark main_button top_margin d-flex justify-content-center"  style="
        margin: auto; margin-bottom: 3%; margin-top: 3%; font-size: 200%;">Управление тк</button>
    </a>
    <form action="sign_out" method="post">
        <input type="submit" value="Выход" class="btn btn-dark main_button top_margin d-flex justify-content-center"  style="
        margin: auto; margin-bottom: 3%; margin-top: 3%; font-size: 200%;">
    </form>
</div>
<#include "resources/footer.ftl">
<@footer margin=30/>
<script src="templates/resources/profile.js" charset="UTF-8"></script>
<@bootstrapjs></@bootstrapjs>
</body>
</html>