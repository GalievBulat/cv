<#include  "config.ftl">
<@config name="profile"/>
<body style=" margin: 0;  height: 100%;font-family: Times New Roman,serif;font-style: normal;font-weight: 600;">
<#include "nav.ftl">
<div class="backgr" style="margin-top: 56px;">
    <#include  "header.ftl">
    <@header text ="Profile"/>
    <form method="post" enctype="multipart/form-data" id="avatarPicking">
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
    <img class="rounded mx-auto d-block img-thumbnail" src="${avatar}" id = "picture" style="width: 20%; margin-bottom: 3%;">

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
                    alert("OK")
                    $("#picture").prop("src",_data)
                },
                error: function (_data) {
                    alert(_data.responseText);
                }
            });
        }
    </script>

    <div id="parallelogram1" class="figure top_margin d-flex justify-content-end" style="margin-top: 1%;">
        <p class="text skewed">${name}</p>
    </div>
    <div id="parallelogram2" class="figure top_margin d-flex justify-content-end">
        <p   class="text skewed">${surname}</p>
    </div>
    <div id="parallelogram3" class="figure top_margin d-flex justify-content-end">
        <p  class="text skewed ">${tc}</p>
    </div>
</div>

<@bootstrapjs></@bootstrapjs>
</body>
</html>