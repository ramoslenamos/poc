/**
 * Created by othmane on 19/03/2018.
 */
var app = angular.module("MyApp", ['ngResource']);



app.factory("Authentification", function($resource) {
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
            headers: { 'x-auth': 'PiP+FkI9KeCxOedXRZfYm+SwaQsOkAmOEF7wHpD8VOm7T11R3J1+RibpwYyNDS' +
            'qFJpKpsl9rHb4oS7FOymMvoTPwcM/or4pzeHIeR1ZHXbfBAwk9WbcdrTD+A9RgZ66bNflITsG' +
            'mQuFAuvPO8TjUnop3u2dgdsHb9PVbrtCSFm3Mcz8UXq6Y41q1Lf5GyCJCZzG2i32RDDl2eZQC' +
            'f9WvZFXMX9Up22hxb6j5eZPwRdVVvNOnoTG0MNOv7Bit/fSErwbHv8gMlDilMosisDmFR9cMn' +
            'Q+zJ3dMnPzNUytcj3DQzy8WusNr/K7CB/MsOYu3TaOP/Zxn8rZIkIE/0wi3tXvwerxuvUsuQYoaZg==' }
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

