(function(){

	angular.module('app.core').service('Authorization', function($state,$cookies,$http) {
		
		this.auth= $cookies.get("auth");
		
		  $http({method: "GET", url: "http://188.2.87.248:5000/eventsdemo/api/info"})
		   .then(function(data){ 
		       this.user=data;
		       if(data){
		    	   console.log(" auth true");
		    	   this.authorized = true;
		    	   
		       }else{
		    	   Authorization.clear();
		    	   console.log(" auth false");
		    	   this.authorized = false;
		       }
		   }
		 );
		
 /* if(this.auth){
	  this.authorized = true;
  }else{
	  this.authorized = false;
	}
*/
  var
  clear = function() {
    this.authorized = false;
    $cookies.remove("auth");
  },

  go = function(fallback) {
    this.authorized = true;
  };

  check = function() {
	  $http({method: "GET", url: "http://188.2.87.248:5000/eventsdemo/api/info"})
	   .then(function(data){ 
	       this.user=data;
	       if(data){
	    	   console.log("true");
	    	   return true;
	    	   
	       }else{
	    	   Authorization.clear();
	    	   console.log("false");
	    	   return false;
	       }
	   }
	 );

	   
	  };

  
  
  return {
    authorized: this.authorized,
    clear: clear,
    go: go,
    check: check
  };
});
	
	})();