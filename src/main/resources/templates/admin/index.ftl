<!DOCTYPE html>
<html ng-app="admin">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Admin</title>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<script src="/js/angular.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/jquery-2.1.3.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</head>

<body ng-controller="AdminPostCtrl">
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h1>Posts</h1>
				<table class="table">
					<thead>
						<tr>
							<th>Título</th>
							<th>Data</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="post in posts">
							<td>{{post.title}}</td>
							<td>{{post.updatedAt | date:'medium'}}</td>
						</tr>
					</tbody>
				</table>
				<input class="btn btn-success" type="button" value="Novo Post"
					data-toggle="modal" data-target="#newPostModal">
			</div>
			<div class="col-md-4">
				<h2>Menu</h2>
			</div>
		</div>
	</div>

	<div class="modal" id="newPostModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Novo Post</h4>
				</div>
				<div class="modal-body">
					<form novalidate>
						<div class="form-group">
							<label for="title" class="control-label">Título:</label>
							<input type="text" class="form-control" id="title" ng-model="post.title">
						</div>
						<div class="form-group">
							<label for="content" class="control-label">Texto:</label>
							<textarea class="form-control" id="content" ng-model="post.content"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					<button type="button" class="btn btn-primary" ng-click="create(post)">Salvar</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>