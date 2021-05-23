var app = angular.module('tree', []);

function check_add() {
    if($('#value_add').val() !== '')
        $('#Add_but').removeAttr('disabled');
    else $('#Add_but').attr('disabled','disable');
}
function check_del() {
    if($('#value_del').val() !== '')
        $('#Del_but').removeAttr('disabled');
    else $('#Del_but').attr('disabled','disable');
}

app.controller("TreeRest", function ($scope, $http) {

    $scope.successGetTreeCallback = function (response) {
        $scope.str = response.data;
        console.log(response);
    };

    $scope.errorGetTreeCallback = function (error) {
        console.log(error);
    };

    $scope.getTree = function () {
        $http.get('/public/rest/tree').then($scope.successGetTreeCallback, $scope.errorGetTreeCallback);
    };



    $scope.successDeleteValueCallback = function (response) {
        window.location.reload();
    };

    $scope.errorDeleteValueCallback = function (error) {
        console.log(error);
    };

    $scope.deleteValue = function () {
        $http.delete('/public/rest/tree/' + $scope.inputValue_DEL).then($scope.successDeleteValueCallback, $scope.errorDeleteValueCallback);
    };



    $scope.successAddValueCallback = function (response) {
        window.location.reload();
    };

    $scope.errorAddValueCallback = function (error) {
        console.log(error);
    };

    $scope.addValue = function () {
        $http.post('/public/rest/tree/' + $scope.inputValue_ADD).then($scope.successAddValueCallback, $scope.errorAddValueCallback);
    };
});
