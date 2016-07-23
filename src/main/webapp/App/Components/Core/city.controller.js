(function(){

	angular.module('app.core').controller('CityController', citycontroller);
	
	function citycontroller($location,$scope, $http, $state, $stateParams){
		var cc=this;
		  $scope.params = $stateParams;
		 var _selected;
		 
		  $scope.go = function (st) {
	            $state.go('main.results', { id : st });
	            console.log('st:', st);
	          };
	          
	          console.log('state1 params:', $stateParams);
	          
	          
		  $scope.selected = undefined;
		  // Any function returning a promise object can be used to load values asynchronously
		  $scope.getLocation = function(val) {
		    return $http.get('http://188.2.87.248:5000/eventsdemo/api/citySuggestions/' + val).then(function(response){
		      return response.data.map(function(item){
		        return item;
		      });
		    });
		  };

		  
		  $scope.onSelect = function ($item, $model, $label) {
			    $scope.$item = $item;
			    $scope.$model = $model;
			    $scope.$label = $label;
			    console.log('item:', $item);
			    console.log('model', $model);
			    console.log('label', $label);
			    
			    $state.go('main.results', { city : $model.city , state: $model.region_code, country:$model.country, page:1 , pageSize:10});
			    console.log('state1 params:', $stateParams);
			};
		  
		  
		  
		
		  
		  
		  
		  
		  
	}


})();