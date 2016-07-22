(function(){

	angular.module('app.core').service('Authorization', function($state,$cookies) {
		
		this.auth= $cookies.get("auth");
		
  if(this.auth){
	  this.authorized = true;
  }else{
	  this.authorized = false;
	}

  var
  clear = function() {
    this.authorized = false;
    $cookies.remove("auth");
  },

  go = function(fallback) {
    this.authorized = true;
  };

  return {
    authorized: this.authorized,
    clear: clear,
    go: go
  };
});
	
	})();