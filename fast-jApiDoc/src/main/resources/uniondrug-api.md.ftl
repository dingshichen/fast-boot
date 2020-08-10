<#list controllerNodes as controller>
# ${controller.description}
> ${controller.description}

<#if reqNode.author??>* **作者** : `${i18n.getMessage('author')}: ${reqNode.author}`</#if>
* **请求路径** : `${i18n.getMessage('requestUrl')}`
* **请求方式** : <#list reqNode.method as method>`${method}` </#list>

    <#list controller.requestNodes as reqNode>
        ## <#if reqNode.deprecated>~~${(reqNode.description)!''}~~<#else>${(reqNode.description)!''}</#if>



        **${i18n.getMessage('requestUrl')}**

        ${reqNode.url} <#list reqNode.method as method>`${method}` </#list>

        <#if reqNode.paramNodes?size != 0>
            **${i18n.getMessage('requestParameters')}**

            <#assign isJsonReqBody = false/>
            <#list reqNode.paramNodes as paramNode>
                <#if paramNode.jsonBody>
                    ```json
                    ${paramNode.description}
                    ```
                    <#assign isJsonReqBody = true/>
                </#if>
            </#list>
            <#if !isJsonReqBody>
                ${i18n.getMessage('parameterName')}|${i18n.getMessage('parameterType')}|${i18n.getMessage('parameterNeed')}|${i18n.getMessage('description')}
                --:|:--:|:--:|:--
                <#list reqNode.paramNodes as paramNode>
                    ${paramNode.name}|${paramNode.type}|${paramNode.required?string(i18n.getMessage('yes'),i18n.getMessage('no'))}|${paramNode.description}
                </#list>
            </#if>
        </#if>
        <#if reqNode.responseNode??>
            **${i18n.getMessage('responseResult')}**

            ```json
            ${reqNode.responseNode.toJsonApi()}
            ```
        </#if>
    </#list>
</#list>