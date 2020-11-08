
<#include  "resources/config.ftl">
<@config name="auth"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">

<div  style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Авторизизацция"/>
    <div class="figure2" style="width: 80%;">
        <#include "resources/errors.ftl">
        <form method="post"  class="form ">
            <label class="highlight">Введите номер карты и пароль</label>
            <input class="form_element" type="number" name="tc" placeholder="tc"/>
            <input class="form_element" type="password" name="password" placeholder="password"/>
            <div class="custom-control custom-checkbox">
                <input type="checkbox" name="remember_me" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label"  for="customCheck1">Запомнить меня</label>
            </div>
            <input class="btn btn-dark main_button" type="submit"  value="AUTH"/>
        </form>
    </div>

</div>
<#include "resources/footer.ftl">

<@bootstrapjs></@bootstrapjs>
</body>
</html>