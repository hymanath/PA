<#--
	Only show message if errors are available.
	This will be done if ActionSupport is used.
-->
<#assign hasFieldErrors = parameters.name?exists && fieldErrors?exists && fieldErrors[parameters.name]?exists/>
<#if hasFieldErrors>
<#list fieldErrors[parameters.name] as error>
<tr errorFor="${parameters.id}">
	<th/>
	<td align="left" valign="top" ><#rt/>
				<span class="errorMessage">${error?html}</span><#t/>
		</td><#lt/>
</tr>
</#list>
</#if>
<#--
	if the label position is top,
	then give the label it's own row in the table
-->
<tr>
<#if parameters.labelposition?default("") == 'top'>
		<th align="left" valign="top" colspan="2"><#rt/>
<#else>
		<th class="tdLabel"><#rt/>
</#if>
<#if parameters.label?exists>
		<label <#t/>
<#if parameters.id?exists>
				for="${parameters.id?html}" <#t/>
</#if>
<#if hasFieldErrors>
				class="errorLabel"<#t/>
<#else>
				class="label"<#t/>
</#if>
		><#t/>
${parameters.label?html}<#t/>
<#include "/${parameters.templateDir}/xhtml/tooltip.ftl" />
</label><#t/>
</#if>
		</th><#lt/>
<#-- add the extra row -->
<#if parameters.labelposition?default("") == 'top'>
</tr>
<tr>
</#if>
