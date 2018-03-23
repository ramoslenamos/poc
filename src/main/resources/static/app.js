/**
 * Created by othmane on 19/03/2018.
 */
var app = angular.module("MyApp", ['ngResource']);





app.factory("getTabInfo", function($resource)
    {
        return $resource('http://xrm3.eudonet.com/EudoAPI/Search/:tabId',
            {},
            {
                get: {
                    method: 'POST',
                    headers: { 'x-auth': 'sG9PrOVVVwNI2W8IGxe9h+MjAT+eR4ppfNW5Cz61aiGUx+YhY7kL3603QqK9HYuhvNz61' +
                    '0qIaHfEgnMjRJ1Azyu8ppm5+liUJI/NtOhE8IB+2W/OTjRZne5WR7/OAC2r4r3G8LMbtIGv9c5NDnZUUi37VL' +
                    '4TuHj8Bq/dpD7QgjdWuMnZqngDjuGtzYKy0VK1P5IJaPDApQnKLqPq6C2BGT9orHZSjKRwdjTW9UbLdPWgYi' +
                    'sXXmZqJ9rZqpBl6sqsjK9N2cWfWZR5i9QlxBe6i3mWvEKqN9W3kmg9tdiZ3iY5B3H+b8ov4x3Sqv8wmyx' +
                    'xxGWYj4bLAUOohp9gzqVqJAbM46t/r3wqIF/tbw==' ,
                        'Content-Type': 'application/json' }

                }
            }
        );
    });
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
            headers: { 'x-auth': 'V/Taw6l8zsqwDnZ+bwL/bzIgLE27' +
            'gX8rbKuhMA73EifQzbNSvYIVg4wdRzTlMkc892xf/lqKp' +
            'wC/FMBZ2Jc0k0doEu+PYx7aUyCYvzL3pb4HGgASMdXr4l5yc8qh' +
            'BjjUwPKdkvRPcVzOwL1N4S2hZZF0YmlDBKzO3Nv5Rm0NN84kbA3V3L' +
            'oljUP4WOwm5Sh5n29CdyCUxH/FX1iPXnEuWAFVarQ+DfT6ZZIIJIAc0v' +
            'RXR5X/6i8k84wK76zn/GcWbZPW0frLKJnVNyclTPfq48GMhyA+6UF021S/uE3+' +
            'ODusmBOPUkxaTJWUItnpqZZB0MesAKyKCzMgCMyMsm+beV12FsFgAlFkKU0Cqg==' }
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

app.factory("listMetaColumn", function($resource) {
    return $resource('http://xrm3.eudonet.com/EudoAPI/MetaInfos\\/'+'/', {}, {

        get: {
            method: 'POST',
            headers: { 'x-auth': 'sG9PrOVVVwNI2W8IGxe9h+MjAT+eR4ppfNW5Cz61aiGUx+YhY7kL3603QqK9HYuhvNz610qIaHfEgnMjRJ1Azyu8ppm5+liUJI/NtOhE8IB+2W/OTjRZne5WR7/OAC2r4r3G8LMbtIGv9c5NDnZUUi37VL4TuHj8Bq/dpD7QgjdWuMnZqngDjuGtzYKy0VK1P5IJaPDApQnKLqPq6C2BGT9orHZSjKRwdjTW9UbLdPWgYisXXmZqJ9rZqpBl6sqsjK9N2cWfWZR5i9QlxBe6i3mWvEKqN9W3kmg9tdiZ3iY5B3H+b8ov4x3Sqv8wmyxxxGWYj4bLAUOohp9gzqVqJAbM46t/r3wqIF/tbw==' ,
                'Content-Type': 'application/json' }

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
app.run(function (listMetaTables,addDictionnary,Authentification,listMetaColumn,getTabInfo)
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

            },function () {
                console.log("ERREUR")
            })
        });
    });
    listMetaColumn.get({
        "Tables": [
            {
                "DescId": 200,
                "AllFields": true,
                "Fields": [
                    0
                ]
            }
        ]
    },function (data) {
        console.log(data)
    },function () {
        console.log("ERREUR")
    })
    var tabInfo={
        "ShowMetadata": true,
        "RowsPerPage": 50,
        "NumPage": 10,
        "ListCols": [
            201, 202, 209
        ],
        "WhereCustom": {
            "WhereCustoms": [
                {}
            ],
            "Criteria": {
                "Operator": 0,
                "Field": "209"
            },
            "InterOperator": "0"
        }
    }
    var tabId=200
    getTabInfo.get({tabId : tabId},tabInfo,function (data) {
    console.log(data)
    })
});
app.controller("HomeController", function($scope,listMetaTables) {


});

