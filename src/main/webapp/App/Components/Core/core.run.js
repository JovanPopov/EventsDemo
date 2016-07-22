(function(){
	angular.module('app.core').run(function(lodash, $rootScope, $state, Authorization) {

		  $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
		    if (!Authorization.authorized && lodash.has(toState, 'data.authorization') && lodash.has(toState, 'data.redirectTo')) {
		      $state.go(toState.data.redirectTo);
		    }
		  });
		});
	
	})();