(function(){

	angular.module('app.core').controller('ResultsController', resultscontroller);
	
	function resultscontroller($location,$scope, $http, $state, $stateParams,$cookies){
		var cc=this;
		  $scope.city = $stateParams.city;
		  $scope.state = $stateParams.state;
		  $scope.country = $stateParams.country;
		 
		  if($scope.city != '')
			  $cookies.put("city", $stateParams.city);
		  if($scope.state != '')
			  $cookies.put("state", $stateParams.state);
		  if($scope.country != '')
			  $cookies.put("country", $stateParams.country);
		  
		  
	      console.log('state2 params:', $stateParams);
	          
	         if($scope.city == '')
	        	 $scope.city= $cookies.get("city");
		 
	         if($scope.state == '')
	        	 $scope.state= $cookies.get("state");
		 
	         if($scope.country == '')
	        	 $scope.country= $cookies.get("country");
		 
	         console.log('cookie city:', $scope.city);
	         console.log('cookie state:', $scope.state);
	         console.log('cookie country:', $scope.country);
	         
	         
	         (function(val) {
	 		    return $http.get('http://localhost:8080/eventsdemo/api/eventsincity/', {
	 		       params: {
	 		          city: $scope.city,
	 		         state: $scope.state,
	 		        country: $scope.country
	 		        }
	 		      }).success(function(data, status) { 
	 		            $scope.events = data;
	 		           console.log('result:', $scope.events);
	 				}).error(function(data, status) {
	 					alert("Error while getting events for this city!");
	 	            });
	 		  })();
	         
	         
	         
		  
	}


})();