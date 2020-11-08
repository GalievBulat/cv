<#include  "resources/config.ftl">
<@config name="profile"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">
<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Profile"/>
    <div id="parallelogram3" class="figure top_margin d-flex justify-content-end">
        <p id = "tc" class="text skewed ">${tc}</p>
    </div>
    <div id="parallelogram3" class="figure top_margin d-flex justify-content-end">
        <p id = "tc" class="text skewed ">${balance}</p>
    </div>
    <a href="http://google.com">
        <button type="button" class="btn btn-dark main_button top_margin d-flex justify-content-center"  style="
        margin: auto; margin-bottom: 3%; margin-top: 3%; font-size: 200%;">Пополнить карту</button>
    </a>
</div>

<@bootstrapjs></@bootstrapjs>
</body>
</html>