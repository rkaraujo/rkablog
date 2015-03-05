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
					$scope.saveErrorMessage = null;
					$('#newPostModal').modal('hide');
				})
				.error(function() {
					$scope.saveErrorMessage = 'Erro ao salvar Post';
				});
		};

		$scope.publish = function(id) {
			$http.post('/admin/posts/' + id + '/publish')
				.success(function(data) {
					$.each($scope.posts, function(index, value) {
						if (value.id == id) {
							$scope.posts[index] = data;
							return false;
						}
					});
					$scope.message = 'Post "' + data.title + '" publicado com sucesso';
					$scope.errorMessage = null;
				})
				.error(function() {
					$scope.errorMessage = 'Erro ao publicar Post';
				});
		};
	}]);
	
})();