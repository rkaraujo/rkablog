(function() {
	
	var app = angular.module('admin', []);
	
	app.controller('AdminPostCtrl', ['$scope', '$http', function($scope, $http) {
		$scope.posts = [];
		
		$http.get('/admin/posts').success(function(data) {
			$scope.posts = data;
		});
		
		$scope.create = function(post) {
			$http.put('/admin/posts', post)
				.success(function(data) {
					$scope.posts.unshift(data);
					$scope.message = 'Post "' + post.title + '" salvo com sucesso';
					$scope.post = {};
					$scope.errorMessage = null;
					$('#newPostModal').modal('hide');
				})
				.error(function() {
					$scope.errorMessage = 'Erro ao salvar Post';
				});
		};
	}]);
	
})();