var topicsController = function topicsController($scope, $http ) {
    "ngInject";
    console.log("topicsController loaded!");
    $scope.greeting = 'This is a nice forum, please behave.';
    $scope.topics = [];
    $scope.newTopic = [];

    $scope.modalID = undefined;
    $scope.modalIDforDelete = undefined;
    $scope.myModalDeleteConfirm = undefined;

    $scope.openModal = function(id, title) {

        if(id) {
            $scope.modalTitle = 'Edit topic';
            $scope.topicModel = title;
            $scope.modalID = id;
        }
        else {
            $scope.modalTitle = 'Add topic';
        }

        $("#myModal").modal();

    };

    $scope.openModalDelete = function(id) {

        if(id) {
            console.log("id " + id);

            $scope.modalIDforDelete = id;
            $("#myModalDeleteConfirm").modal();
        }
        else {
            console.log("error in delete (modal)");
            alert("Error in deleting.");
        }

    };

    $scope.manageCancel = function() {
        $scope.topicModel = "";
        $scope.modalID = undefined;
    };

    $scope.createOrEditTopic = function(id) {

        var data= {title:$scope.topicModel};

        if(id) {

            $http.put('/topics/' + id, data)
            .then(
            function(response) {

                console.log("success, status: " + response.status );

                $scope.getTopics();

                $('#myModal').modal('hide');
                $scope.topicModel = "";
                $scope.modalID = undefined;

            },
            function(response) {
                console.log("failure to edit topic");
                alert("Error occurred in editing topic.");
            }
            );

        }
        else {

            $http.post('/topics', data)
            .then(
            function(response) {
                console.log("success, status: " + response.status );

                $scope.getTopics();

                $('#myModal').modal('hide');
                $scope.topicModel = "";

            },
            function(response) {
                console.log("failure to add new topic");
                alert("Error occurred in adding new topic.");
            }
            );
        }

    };

    $scope.deleteTopic = function(id) {

        $http.delete('/topics/' + id)
        .then(
        function(response) {

            console.log("success, status: " + response.status );

            $scope.getTopics();

            $("#myModalDeleteConfirm").modal('hide');

        },
        function(response) {
            console.log("failure to delete topic");
            alert("Error occurred in deleting topic.");
        }
        );

    };


    $scope.getTopics = function() {
        $http.get('/topics')
        .then(function(response) {
            if (response.data.length) {
                $scope.topics = response.data;
            } else {
                $scope.topics = [];
            }
        },
        function(response) {
            console.log('Could not get topics ' +  response.status);
            alert("Error occurred in getting/displaying topics.");
        });
    };

    $scope.getTopics();

};

module.exports = topicsController;