<#include  "resources/config.ftl">
<@config name="help"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">
<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Помощь"/>
    <div class="figure2 top_margin d-flex justify-content-end">
        <p id = "help" class="text">Помощь в эксплуатации сервиса</p>
    </div>
    <div class="figure2 top_margin d-flex justify-content-end">
        <p id = "help" class="text">Выше вы найдете регистрацию,
        ответы на вопросы а также расписание маршрутов.
        В нижней панели находиться ссылка на бронирование автобуса</p>
    </div>

</div>
<#include "resources/footer.ftl">
<@footer margin=30/>

<@bootstrapjs></@bootstrapjs>
</body>
</html>