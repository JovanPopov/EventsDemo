(function(){

	angular.module('app.core').controller('ResultsController', resultscontroller);
	
	function resultscontroller($location,$scope, $http, $state, $stateParams,$cookies){
		var cc=this;
		
		  $scope.city = $stateParams.city;
		  $scope.state = $stateParams.state;
		  $scope.country = $stateParams.country;
		  $scope.page = $stateParams.page;

	         
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
	         
	         
	         $scope.myMethod = function(newPageNumber, oldPageNumber){
	        	 $scope.yearIsChanged(newPageNumber);
	         };
	         
	         $scope.yearIsChanged = function (newPageNumber) {
	            /* $location.url('/results?city='+ $scope.city + '&state=' + $scope.state + '&country=' + $scope.country + '&page=' + newPageNumber);*/
	        	 $location.search('city', $scope.city);
	        	 $location.search( 'state', $scope.state);
	        	 $location.search( 'country', $scope.country);
	        	 $location.search( 'page', $scope.page); 
	        	 
	         };   
		  
	}


})();