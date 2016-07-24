(function(){
angular.module('app.core').controller('LoginController', LoginController);
	
	function LoginController($scope,$http,$q,$location,$state,Authorization,$cookies){
var lc=this;
	
	lc.user={username:'', password:''};
	lc.message=null;
	lc.allert=null;
		 

		  lc.login1 = function() {
		  $http.post("j_spring_security_check", "username=" + lc.user.username +
			        "&password=" + lc.user.password, {
			            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
			        } ).then(function(data) {
			        	$cookies.put("auth", lc.user.username);
				    	 Authorization.go();
				    	  if(Authorization.memorizedState){   	
				    	    	$location.url(Authorization.memorizedState);
				    	    	
				    	  	}else{
				    	  
			        	$state.go('main.home');
			        	
			        }
			            //localStorage.setItem("session", {});
			        }, function(data) {
			        	console.log(data);
			        	lc.message=data.data;
			        	lc.allert=true;
			        });
		  
		  };
		  
		  
		  lc.close = function() {
			  lc.allert=false;
			  };
			  
		  
		  
	}


})();