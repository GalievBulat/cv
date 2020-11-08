<#macro post(autor,message,parent_id)>
    <div<#if parent_id!=0> style="margin-left: 10%"</#if> class="card">
        <div class="card-body">
            <h5 class="card-title">${autor}</h5>
            <h6 class="card-subtitle mb-2 text-muted">Categories</h6>
            <#if parent_id!=0><p class="card-text">В ответ ${parent_id}</p></#if>
            <p class="card-text">${message}</p>
            <a href="#" class="card-link">Comment</a>
        </div>
    </div>
</#macro>