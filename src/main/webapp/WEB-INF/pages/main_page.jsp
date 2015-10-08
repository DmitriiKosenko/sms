<html>
<head>
	<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
</head>
<body>
	<div class="col-lg-6" style="float: none; margin: 0 auto">
		<br />
		<div class="row">
			<textarea id="text" class="form-control" style="height: 100px" placeholder="Текст сообщения"></textarea>
		</div>
		<div class="row">
			<div class="input-group">
				<input id="phone" type="text" class="form-control" placeholder="Номер телефона">
			<span class="input-group-btn">
				<button id="send" class="btn btn-default" type="button">Отправить</button>
			</span>
			</div>
		</div>
		<br />
		<div class="row">
			<div id="info-block" class="alert alert-info" role="alert">Статус</div>
		</div>
		<br />

		<div id="pagination-holder" class="row">
			<ul id="pagination" class="pagination"></ul>
		</div>
		<div class="row">
			<table id="history_page" class="table display" cellspacing="0">
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
	<script type="text/javascript" src="//cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.twbsPagination.min.js"></script>

	<script src="resources/js/table.js"></script>
	<script src="resources/js/message.js"></script>
	<script src="resources/js/info.js"></script>
	<script src="resources/js/pagination.js"></script>
	<script src="resources/js/main.js"></script>

	<script type="text/javascript">

		table.init();
		pagination.init();

		$("#send").bind('click', (page.sendSms));
		// in this case we haven't  guarantee that we get the current list of messages
		$("#send").bind('click', (table.getContent));

		table.getContent();
	</script>
</body>
</html>