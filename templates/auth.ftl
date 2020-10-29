
<#include  "config.ftl">
<@config name="auth"/>
<body style=" margin: 0;  height: 100%;font-family: Times New Roman,serif;font-style: normal;font-weight: 600;">
<#include "nav.ftl">
<div class="backgr" style="margin-top: 56px;">
    <#include  "header.ftl">
    <@header text ="Log in"/>
    <div class="figure2" style="width: 80%;">

        <#if errorMessage?has_content >
            <p class="highlight">${errorMessage}</p>
        </#if>
        <form method="post"  class="form ">
            <label class="highlight">Введите номер карты и пароль</label>
            <input class="form_element" type="number" name="tc" placeholder="tc"/>
            <input class="form_element" type="password" name="password" placeholder="password"/>
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label" for="customCheck1">Запомнить меня</label>
            </div>
            <input class="btn btn-dark main_button" type="submit"  value="AUTH"/>
        </form>
    </div>

</div>

<@bootstrapjs></@bootstrapjs>
</body>
</html>