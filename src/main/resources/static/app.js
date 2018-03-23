/**
 * Created by othmane on 19/03/2018.
 */
var app = angular.module("MyApp", ['ngResource']);



app.factory("Authentification", function($resource)
{
    return $resource('http://xrm3.eudonet.com/EudoAPI/Authenticate/Token',
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
app.factory("listMetaTables", function($resource) {
    return $resource('http://xrm3.eudonet.com/EudoAPI/MetaInfos/ListTabs\\/'+'/', {}, {

        get: {
            method: 'GET',
            headers: { 'x-auth': 'Ns76s0JCf6VPKt73aoLzwzboWI7ZPoxT8wzzsVO0DYJiHzli4DJUCpjGdk8jR0tcHJLA0S3JvfQUR+' +
            'asiJo8Sl4Ux4MG8Um4KWuLQm8P8/RV266rrVL+KgU56XbMQLiWXFCHtkpUQlZxfFcqA' +
            'BrPzbdBXOB1TAxhb0zafdtuHc2IytYpEegV/7zLr6dCjNLIScio4rIjACmZ4xKHWDOMO9' +
            '/HpX8TCTlKIZD1Et3oH0x8dOOFo4QCadtK4dm2iwTZFziWaPiR7rI/+xEuZq1rz/dytiDrU' +
            'pnfytaNv8YZo/BaVXOyyghIB1pYl2CS2MJw7TtMZDVf4X1J+MziS8cPsQ22+Ml3GC8JCnbCiA==' }
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
app.run(function (listMetaTables,addDictionnary,Authentification)
{
    /*var header=({
        "SubscriberLogin": "ISTIC INFO",
        "SubscriberPassword": "isticinfo2017",
        "BaseName": "eudo_07011",
        "UserLogin": "UTILISATEUR01_ISTIC",
        "UserPassword": "projet2018",
        "UserLang": "lang_00",
        "ProductName": "TEST"
    });*/
    /*Authentification.save(header,function(data){
        console.log(data.ResultData.Token)
    })*/
    listMetaTables.get(function(data) {

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
});
app.controller("HomeController", function($scope,listMetaTables) {


});

