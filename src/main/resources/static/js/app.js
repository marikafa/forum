console.log("app.js loaded!");
require('angular');
require('angular-ui-router');
require('angular-ui-bootstrap');
require('bootstrap-webpack');
require('font-awesome-webpack');

var templateUrl = require('./app.html');

angular.module('app', [
	'ui.router',
	'ui.bootstrap'

]).directive('app', function() {
	return {
		restrict: 'E',
   		templateUrl: templateUrl,
   	};
}).config(['$stateProvider', '$urlRouterProvider', require('./config/route-config')]);
