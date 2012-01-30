<%@ page import="com.dsindigo.boletas.Students" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'students.label', default: 'Students')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'students.id.label', default: 'Id')}"/>

                <g:sortableColumn property="name" title="${message(code: 'students.name.label', default: 'Name')}"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${studentsInstanceList}" status="i" var="studentsInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${studentsInstance.id}">${fieldValue(bean: studentsInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: studentsInstance, field: "name")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${studentsInstanceTotal}"/>
    </div>
</div>
</body>
</html>
