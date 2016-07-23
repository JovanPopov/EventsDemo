(function(){
	
	angular.module('app.core').controller('RegisterController', RegisterController);
	
	function RegisterController($scope,$http,$q,$location){
		var rc=this;
		rc.user={name:'', email:'',username:'', password:''};
		
	rc.register= function(){
		  var data = JSON.stringify(rc.user);
		$http.post('http://188.2.87.248:5000/eventsdemo/api/register', data)
        .then(
                function(response){
                	alert("Account created, activation mail has been sent!");
                	 $location.path('main.home');
                    return response.data;
                   
                }, 
                function(errResponse){
                   alert('Error: Username or email are allready in use!');
                    return $q.reject(errResponse);
                }
        );
		
	}
		
	};
	
})();
