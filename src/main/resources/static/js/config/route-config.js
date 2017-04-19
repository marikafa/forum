var homeUrl = require('../components/home/home.html');
var topicsUrl = require('../components/forum/topics.html');
var messagesUrl = require('../components/forum/messages.html');

var routes = function($stateProvider, $urlRouterProvider) {
	"ngInject";
	$urlRouterProvider.otherwise("/home");

	$stateProvider
		.state('home', {
		  url: "/forum",
		  templateUrl: homeUrl,
		  controller: require('../components/home/homeController') 
		})
		.state('topics', {
		  url: "/forum/topics",
		  templateUrl: topicsUrl,
		  controller: require('../components/forum/topicsController')
		})
		.state('messages', {
		  url: "/forum/topics/:topicId", // selected topic
		  templateUrl: messagesUrl,
		  controller: require('../components/forum/messagesController')
		});
};

module.exports = routes;
