<html>
<head>
    <title>Welcome to E-school</title>
    <meta name="layout" content="main"/>
</head>

<body>

    <div class="content">
        <div class="page-header">
            <h1>E-School</h1>
        </div>

        <div class="row">
            <div class="span10">
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                    </g:each>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
