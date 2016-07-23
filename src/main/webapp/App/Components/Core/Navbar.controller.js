(function(){

angular.module('app.core').controller('NavbarController', navbarController);

function navbarController($http,$state,Authorization,$cookies){
	var nvc=this;

	  
	   $http({method: "GET", url: "http://188.2.87.248:5000/eventsdemo/api/info"})
	   .success(function(data){ 
	       nvc.user=data;
	       if(data){
	    	   Authorization.go();
	    	   $cookies.put("auth", true);
	    	   console.log("cookie" + $cookies.get("auth"));
	       }else{
	    	   Authorization.clear();
	    	   console.log("cookies clear");
	       }
	   }
	 );

	  
	   
	   nvc.logout = function(){
		   Authorization.clear();
	   }
	   

};

})();