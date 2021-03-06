(function(){
	angular.module('app.core').config(config);

	function config($stateProvider, $urlRouterProvider){
			$urlRouterProvider.otherwise('/home');

			$stateProvider
				.state('main',{
					abstract:true,
					views:{
						'navbar':{
							templateUrl: 'App/Components/Core/Navbar.view.html',
							controller: 'NavbarController',
							controllerAs: 'nvc'
						}
					}
					})

					.state('main.home',{
					url: '/home',
						views:{
							'main@':{
								templateUrl:'App/Components/Core/Home.html',
									controller: 'NavbarController',
									controllerAs: 'nvc'
							}
						}
					})


					.state('main.login',{
					url: '/login',
						views:{
							'main@':{
								templateUrl:'App/Components/Core/login.html',
									controller: 'LoginController',
									controllerAs: 'lc'
							}
						}
					})
					


					
					.state('main.register',{
					url: '/register',
						views:{
							'main@':{
								templateUrl:'App/Components/Core/Register.view.html',
								controller: 'RegisterController',
								controllerAs: 'rc'
							}
						}
					})
					
					
						.state('main.activated',{
					url: '/activated',
						views:{
							'main@':{
								templateUrl:'App/Components/Core/activated.html',
									controller: 'NavbarController',
									controllerAs: 'nvc'
							}
						}
					})
					
					

							.state('main.city',{
					url: '/city',params: {
				        city: '',
				        state: '',
				        country: ''
				    },
				    data: {
				        authorization: true,
				        redirectTo: 'main.login',
				        memory: true
				      },
						views:{
							'main@':{
								templateUrl:'App/Components/Core/Search.html',
								controller: 'CityController',
								controllerAs: 'cc'
							}
						}
					})
					
					
							.state('main.results',{
					url: '/results?city&state&country&page&pageSize&q',
				    data: {
				        authorization: true,
				        redirectTo: 'main.login',
				        memory: true
				      },
				      reloadOnSearch: false,
						views:{
							'main@':{
								templateUrl:'App/Components/Core/results.html',
								controller: 'ResultsController',
								controllerAs: 'rc'
							}
						}
					});

	}



})();