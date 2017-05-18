<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url value="/webjars/bootstrap/3.3.1" var="boots"/>
<head>
    <title>Solfest Sandbox</title>
    <link rel="stylesheet" href="${boots}/css/bootstrap.min.css"/>
</head>

<spring:url value="/" var="urlHome" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Solfest Sandbox</a>
		</div>
	</div>
</nav>
