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

    <a href="/catOwners"><b><i>&#9664; to cat owners page</i></b></a>
    <br/>
    <br/>

    <@forms.postForm "/users/add">
        <@labels.username/>
        <@labels.password/>
        <#list roles as role>
            <label>
                &emsp;${role}
                <input type="checkbox" name=${role}>
            </label>
        </#list>
        &emsp;
        <button type="submit">add new user</button>
    </@forms.postForm>
    <br/>

    <@forms.postForm "/users/delete">
        <@labels.id/>
        <button type="submit">delete by id</button>
    </@forms.postForm>
    <br/>

    <div><i>Users</i></div>
    <br>
    <table>
        <tr>
            <th>id&emsp;</th>
            <th>username&emsp;</th>
            <th>is active&emsp;</th>
            <th>roles&emsp;</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.active?string('true', 'false')}</td>
                <td style="text-transform: lowercase">
                    <#list user.roles as role>
                        ${role}
                    </#list>
                </td>
            </tr>
        </#list>
    </table>
</@tmp.page>