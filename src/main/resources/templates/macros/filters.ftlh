<#import 'labels.ftlh' as labels>
<#import 'forms.ftlh' as forms>

<#macro byId path>
    <@forms.postForm path>
        <@labels.id/>
        <button type="submit">filter by id</button>
    </@forms.postForm>
</#macro>

<#macro byName path>
    <@forms.postForm path>
        <@labels.name/>
        <button type="submit">filter by name</button>
    </@forms.postForm>
</#macro>

<#macro byFullName path>
    <@forms.postForm path>
        <@labels.fullName/>
        <button type="submit">filter by name</button>
    </@forms.postForm>
</#macro>

<#macro byDayOfBirth path>
    <@forms.postForm path>
        <@labels.dayOfBirth/>
        <button type="submit">filter by day of birth</button>
    </@forms.postForm>
</#macro>

<#macro byBreed path>
    <@forms.postForm path>
        <@labels.breed/>
        <button type="submit">filter by breed</button>
    </@forms.postForm>
</#macro>

<#macro byColor path>
    <@forms.postForm path>
        <@labels.color/>
        <button type="submit">filter by color</button>
    </@forms.postForm>
</#macro>

<#macro byOwnerId path>
    <@forms.postForm path>
        <@labels.ownerId/>
        <button type="submit">filter by owner id</button>
    </@forms.postForm>
</#macro>

