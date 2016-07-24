(function(){

angular.module('app.core').controller('NavbarController', navbarController);

function navbarController($http,$state,Authorization,$cookies){
	var nvc=this;
	nvc.user=$cookies.get("auth");
	
	   $http({method: "GET", url: "http://188.2.87.248:5000/eventsdemo/api/info"})
	   .success(function(data){ 
	       
	       if(data){
	    	   nvc.username="Current user: " + data;
	    	   $cookies.put("auth", data);
	    	   Authorization.go();
	       }else{
	    	   Authorization.clear();
	    	   console.log("cookies clear");
	       }
	   }
	 );

	  
	   
	   nvc.logoutold = function(){
		   Authorization.clear();
		   $cookies.remove("auth");
	   }
	   
	   nvc.userf = function(){
		  return  $cookies.get("auth");
		 
	   }
	   
	   nvc.logout = function(){
		   
		   $http.get('logout').success(function(data, status) { 
			   
			   Authorization.clear();
			   $state.go('main.home');
 		            
 				}).error(function(data, status) {
 					alert("Error while logging out");
 	            });
		   
			 
		   }
	   
};

})();