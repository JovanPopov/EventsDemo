(function(){
	angular.module('app.core').run(function(lodash, $rootScope, $state, Authorization,$cookies, $location) {

		  $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
		    if (!Authorization.authorized && lodash.has(toState, 'data.authorization') && lodash.has(toState, 'data.redirectTo')) {
		      if (lodash.has(toState, 'data.memory') && toState.data.memory) {
		    	  var d = $location.url();
		        Authorization.memorizedState =d;
		      }
		      $state.go(toState.data.redirectTo);
		    }
		  });

		});
	
	
	})();



