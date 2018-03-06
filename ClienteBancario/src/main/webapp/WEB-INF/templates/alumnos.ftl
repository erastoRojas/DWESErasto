<#ftl strip_whitespace = true>

<#assign charset="UTF-8">
<#assign title="Example">

<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="${charset}">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        </head>
    <body>  
    <#if content??>
        <div>${content}</div>
    <#else>
        <div>No content</div>
    </#if>
    <#list alumnos as alumno>
     ${alumno.nombre},
${alumno.fecha_nacimiento?string["dd-MM-yyyy"]},
            ${alumno.mayor_edad?c}
    </#list>	


        </body>
    </html>
