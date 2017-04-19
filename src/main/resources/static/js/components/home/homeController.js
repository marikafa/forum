var homeController = function homeController($scope) {

	console.log("homeController loaded!");

	$scope.homeGreeting = 'Welcome to topic forum. Here you can find topics, add new topics and find related messages to the topics.';
	$scope.homeInstruction = '* Add, update, delete topic. Add, update, delete message. ';
};

module.exports = homeController; 