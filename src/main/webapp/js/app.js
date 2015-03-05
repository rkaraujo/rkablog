(function() {
	
	var app = angular.module('admin', []);
	
	app.controller('AdminPostCtrl', ['$scope', '$http', function($scope, $http) {
		$scope.posts = [];
		
		$http.get('/admin/posts').success(function(data) {
			$scope.posts = data;
		});
		
		$scope.create = function(post) {
			$http.post('/admin/posts', post)
				.success(function(data) {
					if (post.id == null) {
						$scope.posts.unshift(data);
					} else {
						$.each($scope.posts, function(index, value) {
							if (value.id == data.id) {
								$scope.posts[index] = data;
								return false;
							}
						});
					} 
					$scope.message = 'Post "' + post.title + '" salvo com sucesso';
					$scope.saveErrorMessage = null;
					$('#newPostModal').modal('hide');
				})
				.error(function() {
					$scope.saveErrorMessage = 'Erro ao salvar Post';
				});
		};

		$scope.new = function() {
			$scope.editingPost = {};
		};

		$scope.edit = function(post) {
			$scope.editingPost = angular.copy(post);
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