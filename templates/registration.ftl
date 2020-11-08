<#include  "resources/config.ftl">
<@config name="reg"/>
<#include "resources/background.ftl">
<#include "resources/nav.ftl">

<div style="margin-top: 56px;">
    <#include  "resources/header.ftl">
    <@header text ="Sign up"/>

    <div class="figure2" style="width: 80%;">
        <#if errorMessage?has_content >
        <p class="highlight">${errorMessage}</p>
        </#if>
        <form method="post"  class="form">
            <label class="highlight">Введите регестрационные данные:</label>
            <input class="form_element" type="text" name="name" placeholder="имя"/>
            <input class="form_element" type="text" name="surname" placeholder="фамилия"/>
            <input class="form_element" type="tel" name="phone_num" placeholder="тел номер (+7**********)"/>
            <input class="form_element" type="number" name="tc" placeholder="тр карта"/>
            <input class="form_element" type="email" name="email" placeholder="эл почта"/>
            <input class="form_element" type="password" name="password" placeholder="пароль" id = "password_input" oninput="onPrint()"/>
            <div id="invalid password"></div>
            <script>
                function onPrint(){
                    let pas = document.getElementById("password_input").value;
                    if(pas.length <=8){
                        let pasHolder= document.getElementById("invalid password");
                        pasHolder.innerText = "";
                        let el = document.createElement("p");
                        el.innerText = "слишком короткий пароль";
                        pasHolder.appendChild(el);
                    }else {
                        let pasHolder= document.getElementById("invalid password");
                        pasHolder.innerText = "";
                    }
                }
            </script>
            <input class="form_element" type="date" name="birth_day" placeholder="день рождения"/>
            <input class="figure3 unskewed btn btn-dark" type="submit" value="REGISTER"/>
        </form>
    </div>

</div>

<@bootstrapjs></@bootstrapjs>
</body>
</html>