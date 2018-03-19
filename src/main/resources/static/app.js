/**
 * Created by othmane on 19/03/2018.
 */
var app = angular.module("MyApp", ['ngResource']);

app.factory("listMetaTables", function($resource) {
    return $resource('http://xrm3.eudonet.com/EudoAPI/MetaInfos/ListTabs\\/'+'/', {}, {
        post: {
            method: 'POST',
            headers: { 'x-auth': '0qQczs0HcOqIH1KDyp+t/zOnS1I0+qwhozwNRjATLSyWnxsnj642uSTLC' +
            's1L5Mdja3+68Z2SoGAeLCrx4p/ggKD+aMajEk3/85e88409iwl9QZNpM/I' +
            'nr1XISOUfUJXEAcYWbPzo3Cw11Z7gV+4HstkJFnftzpFrFxFUYQYiNaHulM' +
            '+cpDRE2zvXlJG6dtpjpbDbJYEhaUQLhXMoJ49N9N6wg7qxg8RDIANBamRFBxK5' +
            'Tu1+/Opk6cWSODLM91ZBGQtutznxTKUK11+1MmglhNqWP1XNi+t9JWkjNEbIcy2Qz' +
            '2wN+3RL6rcivYz4Huij7+slmT8WmMOi4wR2S9niBJMOq/slrOOQsuJQNw==' }
        },
        get: {
            method: 'GET',
            headers: { 'x-auth': '0qQczs0HcOqIH1KDyp+t/zOnS1I0+qwhozwNRjATLSyWnxsnj642uSTLC' +
            's1L5Mdja3+68Z2SoGAeLCrx4p/ggKD+aMajEk3/85e88409iwl9QZNpM/I' +
            'nr1XISOUfUJXEAcYWbPzo3Cw11Z7gV+4HstkJFnftzpFrFxFUYQYiNaHulM' +
            '+cpDRE2zvXlJG6dtpjpbDbJYEhaUQLhXMoJ49N9N6wg7qxg8RDIANBamRFBxK5' +
            'Tu1+/Opk6cWSODLM91ZBGQtutznxTKUK11+1MmglhNqWP1XNi+t9JWkjNEbIcy2Qz' +
            '2wN+3RL6rcivYz4Huij7+slmT8WmMOi4wR2S9niBJMOq/slrOOQsuJQNw==' }
        }
    });
});
app.factory("addDictionnary", function($resource) {
    return $resource("http://localhost:8081/addDictionnary",
        {},
        {
            save: {
                method: 'POST'
            },
            headers: {
                'Content-Type': 'application/json'
            }
        }
    );
});

app.run(function ($rootScope,listMetaTables,addDictionnary)
{
    listMetaTables.get(function(data) {

        $rootScope.info = data.resultMetaData;


        angular.forEach(data.ResultMetaData.Tables, function(item) {
            var info=new Array();
            info=({idTable:item.Descid,tableName:item.Label})

            addDictionnary.save(info,function () {
                console.log("success")
                console.log(info)
            },function () {
                console.log("ERREUR")
            })
        });
    });
    $rootScope.message="Salut"
    console.log($rootScope.message)


});

app.controller("HomeController", function($scope,listMetaTables) {


});

