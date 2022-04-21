<#import 'tokens.ftlh' as t>

<#macro login path>
    <form method="post" action=${path}>
        <div>
            <label> username :
                <input type="text" name="username"/>
            </label>
        </div>
        <br/>
        <div>
            <label> password:
                <input type="password" name="password"/>
            </label>
        </div>
        <br/>
        <div>
            <input type="submit" value="sign in"/>
        </div>
        <@t.token/>
    </form>
</#macro>

<#macro logout>
    <div>
        <form method="post" action="/logout">
            <input type="submit" value="sign out"/>
            <@t.token/>
        </form>
    </div>
</#macro>