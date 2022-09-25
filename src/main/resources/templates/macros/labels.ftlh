<#macro id>
    <label>
        <input type="number" name="id" placeholder="enter id">
    </label>
</#macro>

<#macro fullName>
    <label>
        <input type="text" name="fullName" placeholder="enter full name"/>
    </label>
</#macro>

<#macro dayOfBirth>
    <label>
        <input type="date" name="dayOfBirth" placeholder="enter day of birth">
    </label>
</#macro>

<#macro name>
    <label>
        <input type="text" name="name" placeholder="enter name"/>
    </label>
</#macro>

<#macro breed>
    <label>
        <input type="text" name="breed" placeholder="enter breed">
    </label>
</#macro>

<#macro color>
    <label>
        <input type="text" name="color" placeholder="enter color">
    </label>
</#macro>

<#macro ownerId>
    <label>
        <input type="number" name="ownerId" placeholder="enter owner id">
    </label>
</#macro>

<#macro username>
    <label>
        <input type="text" name="username" placeholder="enter username">
    </label>
</#macro>

<#macro password>
    <label>
        <input type="password" name="password" placeholder="enter password">
    </label>
</#macro>

<#macro roles>
    <#list roles as role>
        <label>
            ${role}
            <input type="checkbox" name=${role}>
        </label>
    </#list>
</#macro>

<#macro active>
    <label>
        is active
        <input type="checkbox" name="active" placeholder="enter active">
    </label>
</#macro>

<#macro userId>
    <label>
        <input type="number" name="userId" placeholder="enter user id">
    </label>
</#macro>