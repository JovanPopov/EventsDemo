(function(){

angular.module('app.core').controller('NavbarController', navbarController);

function navbarController($http,$state,Authorization,$cookies){
	var nvc=this;

	  
	   $http({method: "GET", url: "http://localhost:8080/eventsdemo/api/info"})
	   .success(function(data){ 
	       nvc.user=data;
	       if(data){
	    	   Authorization.go();
	    	   $cookies.put("auth", true);
	       }
	   }
	 );

	  
	   
	   nvc.logout = function(){
		   Authorization.clear();
	   }
	   

};

})();