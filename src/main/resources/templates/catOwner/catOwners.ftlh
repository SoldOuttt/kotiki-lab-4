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

    <a href="/cats"><b><i>&#9664; to cats page</i></b></a>
    <br/>
    <br/>

    <a href="/users"><b><i>&#9664; to users page</i></b></a>
    <br/>
    <br/>

    <@forms.postForm "/catOwners/add">
        <@labels.fullName/>
        <@labels.dayOfBirth/>
        <@labels.userId/>
        <button type="submit">add new cat owner</button>
    </@forms.postForm>
    <br/>

    <@forms.postForm "/catOwners/delete">
        <@labels.id/>
        <button type="submit">delete by id</button>
    </@forms.postForm>
    <br/>

    <@forms.postForm "/catOwners/update">
        <@labels.id/>
        <@labels.fullName/>
        <@labels.dayOfBirth/>
        <button type="submit">update cat owner</button>
    </@forms.postForm>
    <br/>

    <@filters.byId "/catOwners/filterById"/>
    <br>

    <@filters.byFullName "/catOwners/filterByName"/>
    <br/>

    <@filters.byDayOfBirth "/catOwners/filterByDayOfBirth"/>
    <br/>

    <div><i>Cat Owners</i></div>
    <br>
    <table>
        <tr>
            <th>id&emsp;</th>
            <th>full name&emsp;</th>
            <th>day of birth&emsp;</th>
            <th>user id&emsp;</th>
            <th>username&emsp;</th>
        </tr>
        <#list catOwners as catOwner>
            <tr>
                <td>${catOwner.id!}</td>
                <td>${catOwner.fullName!}</td>
                <td><i>${catOwner.dayOfBirth!}</i></td>
                <td>${catOwner.userId!}</td>
                <td>${catOwner.username!}</td>
            </tr>
        </#list>
    </table>
</@tmp.page>