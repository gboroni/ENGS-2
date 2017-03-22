<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width" />
<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<title>Lista de Turmas</title>
</head>
<body>
	<div class="panel panel-default" style="margin: 10px">
		<div class="panel-heading">
			<h1 class="panel-title">Lista de turmas</h1>
		</div>
	</div>
	<div class="panel-body">
		<form class="form-inline" method="POST" style="margin: 20px 0"
			th:object="${turma}" th:action="@{/turmas}">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Código"
					th:field="*{codigo}" />
				<form:select path="country">
					<form:options items="${countryList}" />
				</form:select>

				<button type="submit" class="btn btn-primary">Adicionar</button>
			</div>
		</form>
		<table class="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>Código</th>
				</tr>
			</thead>
			<tbody>
				<tr th:each="turma : ${turmas}">
					<td th:text="${turma.id}"></td>
					<td th:text="${turma.codigo}"></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>