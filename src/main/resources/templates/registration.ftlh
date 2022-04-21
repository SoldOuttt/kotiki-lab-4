<#import 'macros/pageTemplate.ftlh' as tmp>
<#import 'macros/authorization.ftlh' as authorization>

<@tmp.page>
    <b>Registration</b>
    <br/>
    <br/>
    <@authorization.login "/register"/>
    <br/>
    <br/>
    ${(message)!}
</@tmp.page>
