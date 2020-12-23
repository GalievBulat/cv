<#include  "resources/config.ftl">
<@config name="manager"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">
<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Управление тк"/>
    <div id="parallelogram3" class="figure top_margin d-flex justify-content-end">
        <p id = "tc" class="text skewed ">Номер карты: ${tc}</p>
    </div>
    <div id="parallelogram3" class="figure top_margin d-flex justify-content-end">
        <p id = "tc" class="text skewed ">Баланс: ${balance}</p>
    </div>
    <a href="http://google.com">
        <button type="button" class="btn btn-dark main_button top_margin d-flex justify-content-center"  style="
        margin: auto; margin-bottom: 3%; margin-top: 3%; font-size: 200%;">Пополнить карту</button>
    </a>
</div>
<#include "resources/footer.ftl">
<@footer margin=30/>

<@bootstrapjs></@bootstrapjs>
</body>
</html>