(function(){

angular.module('app.core').controller('NavbarController', navbarController);

function navbarController($http){
	var nvc=this;

	  
	   $http({method: "GET", url: "http://localhost:8080/eventsdemo/api/info"})
	   .success(function(data){ 
	       nvc.user=data;
	   }
	 );


};


})();