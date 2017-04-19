var messagesController = function messagesController($scope, $http, $stateParams) {
    "ngInject";
    console.log("messagesController loaded!");
    $scope.messages = [];

    $scope.modalID = undefined;
    $scope.modalIDforDelete = undefined;
    $scope.myModalDeleteConfirm = undefined;


    $scope.openModalDelete = function(id) {

        if((id)) {
            console.log("id " + id);

            $scope.modalIDforDelete = id;
            $("#myModalDeleteConfirm").modal();
        }
        else {
            console.log("error in delete (modal)");
            alert("Error in deleting.");
        }

    };


    $scope.openModal = function(id, content) {

        if(id) {
            $scope.modalTitle = 'Edit message';
            $scope.messageModel = content;
            $scope.modalID = id;
        }
        else {
            $scope.modalTitle = 'Add message';
        }

        $("#myModalForMessage").modal();

    };
    $scope.manageCancel = function() {
        $scope.messageModel = "";
        $scope.modalID = undefined;
    };


    $scope.getMessages = function() {
        $http.get('/topics/' + $stateParams.topicId + '/messages')
        .then(function(response) {
            if (response.data.length) {
                $scope.messages = response.data;
                console.log(response.data);


            } else {
                $scope.messages = [];
                console.log("no messages");

            }
        },
        function(response) {
            console.log('Could not get messages' +  response.status);
             alert("Error occurred in getting/displaying messages.");
        });
    };


    $scope.createOrEditMessage = function(id) {
        var data= {content:$scope.messageModel};

        if(id) {
            $http.put('/topics/' + $stateParams.topicId + '/messages/'+ id, data)
            .then(
            function(response) {
                console.log("success, status: " + response.status );
                $scope.getMessages();

                $('#myModalForMessage').modal('hide');
                $scope.messageModel = "";
                $scope.modalID = undefined;
            },
            function(response) {
                console.log("failure to edit message");
                alert("Error occurred in editing message.");
            }
            );
        }
        else {
            console.log("add");
            $http.post('/topics/'+ $stateParams.topicId + '/messages/', data )
            .then(
            function(response) {
                console.log("success, status: " + response.status );
                console.log("response: " + response);

                $scope.getMessages();

                $('#myModalForMessage').modal('hide');
                $scope.messageModel = "";

            },
            function(response) {
                console.log("failure to add new message");
                alert("Error occurred in adding new message.");

            }
            );
        }

    };


    $scope.deleteMessage = function(id) {
        $http.delete('/topics/' + $stateParams.topicId + '/messages/' + id)
        .then(
        function(response) {

            console.log("success, status: " + response.status );
            $("#myModalDeleteConfirm").modal('hide');

            $scope.getMessages();

        },
        function(response) {
            console.log("failure to delete message");
            alert("Error occurred in deleting message.");
        }
        );
    };

    // prints search matches to console
    $scope.openSearch = function(text, messages) {
        console.log("to search for word: " + text);

        for(var i = 0; i < messages.length; i++)  {
            if(messages[i].content.search(text) > -1) {
                console.log("matching message: " + messages[i].content);
            }
        }
    };


    $scope.getMessages();
};

module.exports = messagesController;
