<#import '../macros/pageTemplate.ftlh' as tmp>
<#import '../macros/authorization.ftlh' as authorization>
<#import '../macros/forms.ftlh' as forms>
<#import '../macros/labels.ftlh' as labels>
<#import '../macros/filters.ftlh' as filters>

<@tmp.page>
    <br/>
    <@authorization.logout/>
    <br/>
    <br/>

    <a href="/"><b><i>&#9664; to main page</i></b></a>
    <br/>
    <br/>

    <@forms.postForm "/uv/catOwners/add">
        <@labels.fullName/>
        <@labels.dayOfBirth/>
        <button type="submit">add personal info</button>
    </@forms.postForm>
    <br/>

    <@forms.postForm "/uv/catOwners/update">
        <@labels.fullName/>
        <@labels.dayOfBirth/>
        <button type="submit">update personal info</button>
    </@forms.postForm>
    <br/>

    <@forms.postForm "/uv/catOwners/delete">
        <button type="submit">delete personal info</button>
    </@forms.postForm>
    <br/>

    <b>Full name:  ${catOwner.fullName!"<none>"}</b>
    <br/>
    <br/>

    <b>Day of birth:  ${catOwner.dayOfBirth!"<none>"}</b>
</@tmp.page>