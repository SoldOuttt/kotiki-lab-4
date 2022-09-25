<#import '../macros/pageTemplate.ftlh' as tmp>
<#import '../macros/tokens.ftlh' as tokens>
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

    <a href="/catOwners"><b><i>&#9664; to cat owners page</i></b></a>
    <br/>
    <br/>

    <a href="/users"><b><i>&#9664; to users page</i></b></a>
    <br/>
    <br/>

    <@forms.postForm "/cats/add">
        <@labels.name/>
        <@labels.dayOfBirth/>
        <@labels.breed/>
        <@labels.color/>
        <@labels.ownerId/>
        <button type="submit">add new cat</button>
    </@forms.postForm>
    <br/>

    <@forms.postForm "/cats/delete">
        <@labels.id/>
        <button type="submit">delete by id</button>
    </@forms.postForm>
    <br/>

    <@forms.postForm "/cats/update">
        <@labels.id/>
        <@labels.name/>
        <@labels.dayOfBirth/>
        <@labels.breed/>
        <@labels.color/>
        <@labels.ownerId/>
        <button type="submit">update cat</button>
    </@forms.postForm>
    <br/>

    <@filters.byId "/cats/filterById"/>
    <br>

    <@filters.byName "/cats/filterByName"/>
    <br/>

    <@filters.byDayOfBirth "/cats/filterByDayOfBirth"/>
    <br/>

    <@filters.byBreed "/cats/filterByBreed"/>
    <br>

    <@filters.byColor "/cats/filterByColor"/>
    <br>


    <@filters.byOwnerId "/cats/filterByOwnerId"/>
    <br>

    <div><i>Cats</i></div>
    <br>
    <table>
        <tr>
            <th>id&emsp;</th>
            <th>name&emsp;</th>
            <th>breed&emsp;</th>
            <th>color&emsp;</th>
            <th>owner id&emsp;</th>
            <th>day of birth&emsp;</th>
        </tr>
        <#list cats as cat>
            <tr>
                <td>${cat.id!}</td>
                <td>${cat.name!}</td>
                <td>${cat.breed!}</td>
                <td style="text-transform: lowercase">${cat.color!}</td>
                <td>&emsp;${cat.ownerId!}</td>
                <td><i>${cat.dayOfBirth!}</i></td>
            </tr>
        </#list>
    </table>
</@tmp.page>