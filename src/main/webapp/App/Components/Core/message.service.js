angular.module('app.core').service("messageService", function() {
    this.user = null;

    this.getUser = function() {
        return this.user;
    };

    this.setUser = function(message) {
      this.user=message;
    };
});