(function(){
	
	angular.module('app.core').controller('RegisterController', RegisterController);
	
	function RegisterController($scope,$http,$q,$location){
		var rc=this;
		rc.user={name:'', email:'',username:'', password:''};
		
	rc.register= function(){
		  var data = JSON.stringify(rc.user);
		$http.post('http://localhost:8080/eventsdemo/api/register', data)
        .then(
                function(response){
                	 $location.path('main.home');
                    return response.data;
                   
                }, 
                function(errResponse){
                    console.error('Error while registering');
                    return $q.reject(errResponse);
                }
        );
		
	}
		
	};
	
})();