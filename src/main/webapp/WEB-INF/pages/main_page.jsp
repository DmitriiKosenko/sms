<html>
<head>
	<%@ page language="java" pageEncoding="UTF-8"%>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
</head>
<body>
	<div class="col-lg-6" style="float: none; margin: 0 auto">
		<br />
		<div class="row">
			<textarea class="form-control" style="height: 100px" placeholder="Текст сообщения"></textarea>
		</div>
		<div class="row">
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Номер телефона">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button">Отправить</button>
			</span>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="alert alert-info" role="alert">Статус</div>
		</div>
		<br />
		<%--<div class="alert alert-success" role="alert">...</div>--%>
		<%--<div class="alert alert-warning" role="alert">...</div>--%>
		<%--<div class="alert alert-danger" role="alert">...</div>--%>

		<div class="row">
			<table id="example" class="table display" cellspacing="0">
				<thead>
				<tr>
					<th>Телефон</th>
					<th>Дата отправления</th>
					<th>Статус</th>
					<th>Сообщение</th>
				</tr>
				</thead>
			</table>
		</div>
	</div>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<%--<script src="resources/js/main_page.js"></script>--%>

	<script type="text/javascript" src="//cdn.datatables.net/1.11.0/js/jquery.dataTables.js"></script>
</body>
</html>