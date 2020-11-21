<#include  "resources/config.ftl">
<@config name="forum"/>
<#include "resources/nav.ftl">
<#include "resources/background.ftl">

<div style="margin-top: 56px">
    <#include  "resources/header.ftl">
    <@header text ="Форум"/>
    <div class="figure2" style="width: 80%;padding: 2%">
        <#include "resources/errors.ftl">
        <form id="comment">
            <div class="form-group">
                <label for="exampleFormControlSelect2">Категория</label>
                <select id = "categories" multiple class="form-control" id="exampleFormControlSelect2">
                    <#list categories as item>
                        <option value="${item?counter-1}">${item}</option>
                    </#list>
                </select>
            </div>
            <div class="form-group">
                <label id ="parent" for="exampleFormControlTextarea1">Комментарий</label>
                <input id = "message" class="form-control form-control-lg" id="exampleFormControlTextarea1" rows="2"/>
    </div>
            <button type="button" class="btn btn-dark" onclick="sendComment()">Отправить комментарий</button>
        </form>
        <div >
        </div>
        <p class="highlight">Обсуждения</p>
        <#include "resources/post.ftl">
        <div id = "discussions">
        </div>
</div>


<@bootstrapjs></@bootstrapjs>

</div>
<#include "resources/footer.ftl">
<@footer margin=30/>
<script src="templates/resources/forum.js" charset="UTF-8"></script>
</body>
<script>
    let currentPost= <#if currentPost?has_content>${currentPost}<#else>0</#if>;
    let authorId =<#outputformat "plainText"> <#noautoesc><#if author_id?has_content>${author_id?replace('&nbsp;', ' ')}<#else>0</#if></#noautoesc></#outputformat>;
</script>
</html>