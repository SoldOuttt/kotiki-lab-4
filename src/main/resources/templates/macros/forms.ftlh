<#import 'tokens.ftlh' as tokens>

<#macro postForm path>
    <div>
        <form method="post" action=${path}>
            <#nested>
            <@tokens.token/>
        </form>
    </div>
</#macro>

<#macro getForm path>
    <div>
        <form method="post" action=${path}>
            <#nested>
        </form>
    </div>
</#macro>