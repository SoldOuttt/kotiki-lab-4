<#import 'macros/pageTemplate.ftlh' as tmp>
<#import 'macros/authorization.ftlh' as authorization>

<@tmp.page>
    <@authorization.login "/login"/>
    <br/>
    <br/>
    <b><i>Don't have an account?</i></b>
    <br/>
    <a href="/register"><b><i>sign up</i></b></a>
</@tmp.page>