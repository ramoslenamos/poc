/**
 * Created by othmane on 19/03/2018.
 */
var app = angular.module("MyApp", ['ngResource','ui.router','datatables', 'datatables.buttons',
    'datatables.bootstrap','datatables.columnfilter']);

app.factory("getEtudiant", function($resource) {
    return $resource("http://localhost:8081/api/personne/etudiants");
});
app.factory("getStagiaires", function($resource) {
    return $resource("http://localhost:8081/api/personne/stagiaire/:stage");
});
app.factory("getDomaines", function($resource) {
    return $resource("http://localhost:8081/api/domaines");
});
app.factory("getProprietiesByDomaine", function($resource) {
    return $resource("http://localhost:8081/api/proprietes/:domaine");
});
app.factory("chercherParDomaine", function($resource) {
    return $resource("http://localhost:8081/api/search/:domaine",
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
app.factory("getTypes", function($resource) {
    return $resource("http://localhost:8081/api/personne/types");
});
app.factory("getPersonParType", function($resource) {
    return $resource("http://localhost:8081/api/personne/:type");
});
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
app.run(function() {
    console.log("salut")
});
app.controller("getEtudiants", function($scope,listMetaTables,getEtudiant,DTOptionsBuilder, DTColumnDefBuilder) {


    var vm = this;
    vm.persons = [];

    vm.dtOptions = DTOptionsBuilder.newOptions().withDOM('Blfrtip').withBootstrap().withPaginationType('full_numbers').withDisplayLength(15).withButtons([

        {
            extend: "excelHtml5",
            name: 'Excel',
            title: 'DeviseTable',

            text : '<i class="fa fa-file-excel-o black  growExport "></i>',


            titleAttr: 'Exporter en Excel',

            //CharSet: "utf8",
            exportData: { decodeEntities: true }

        },
        {
            extend: "csvHtml5",

            text: ' <i class="fa fa-table black growExport" aria-hidden="true"></i>',
            title: 'DeviseTable',
            fileName:  "ExcelVersion" + ".xlsx",
            titleAttr: 'Exporter en CSV',

            exportData: {decodeEntities:true}
        }
        ,{
            extend: "copy",
            exportOptions: {
                columns: ':contains("Office")'
            },
            name: 'Copier',
            text : '<i class="fa fa-copy  growExport black"></i>',
            className: 'btn btn-default btn-sm ',
            titleAttr: 'Copier',


            exportData: {decodeEntities:true}
        } ,{
            extend: "print",
            name: 'Imprimer',
            text: '<span aria-hidden="true" class=" fa fa-print"></span>',
            titleAttr: 'Imprimer',

            exportData: {decodeEntities:true}
        } ,
        {
            extend: "pdfHtml5",
            name: 'Pdf',
            title: 'DeviseTable',
            text: '<i class="fa fa-file-pdf-o  ">',
            fileName:  "Pdf" ,
            message: 'Table de devises',
            orientation: 'landscape',
            titleAttr: 'Exporter en PDF',
            pageSize: 'LEGAL',
            exportData: {decodeEntities:true}
        }, {
            text: '<i class="fa fa-file-code-o growExport black" aria-hidden="true" ></i>',
            titleAttr: 'Exporter en JSON',
            action: function ( e, dt, button, config ) {
                var data = dt.buttons.exportData();

                $.fn.dataTable.fileSave(
                    new Blob( [ JSON.stringify( data ) ] ),
                    'Export.json'
                );
            }
        }

    ]).withBootstrap()
    ;

    vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1),
        DTColumnDefBuilder.newColumnDef(2),
        DTColumnDefBuilder.newColumnDef(3)


    ];

    getEtudiant.get(function (data) {
        console.log(data)
        console.log(data.Results)
        vm.persons=data.Results
    })
});

app.controller("getStagiaires", function(getStagiaires,$scope,listMetaTables,getEtudiant,DTOptionsBuilder, DTColumnDefBuilder) {
$scope.clicked=0;
    var vm = this;
    vm.persons = [];

    vm.dtOptions = DTOptionsBuilder.newOptions().withDOM('Blfrtip').withBootstrap().withPaginationType('full_numbers').withDisplayLength(15).withButtons([

        {
            extend: "excelHtml5",
            name: 'Excel',
            title: 'DeviseTable',

            text : '<i class="fa fa-file-excel-o black  growExport "></i>',


            titleAttr: 'Exporter en Excel',

            //CharSet: "utf8",
            exportData: { decodeEntities: true }

        },
        {
            extend: "csvHtml5",

            text: ' <i class="fa fa-table black growExport" aria-hidden="true"></i>',
            title: 'DeviseTable',
            fileName:  "ExcelVersion" + ".xlsx",
            titleAttr: 'Exporter en CSV',

            exportData: {decodeEntities:true}
        }
        ,{
            extend: "copy",
            exportOptions: {
                columns: ':contains("Office")'
            },
            name: 'Copier',
            text : '<i class="fa fa-copy  growExport black"></i>',
            className: 'btn btn-default btn-sm ',
            titleAttr: 'Copier',


            exportData: {decodeEntities:true}
        } ,{
            extend: "print",
            name: 'Imprimer',
            text: '<span aria-hidden="true" class=" fa fa-print"></span>',
            titleAttr: 'Imprimer',

            exportData: {decodeEntities:true}
        } ,
        {
            extend: "pdfHtml5",
            name: 'Pdf',
            title: 'DeviseTable',
            text: '<i class="fa fa-file-pdf-o  ">',
            fileName:  "Pdf" ,
            message: 'Table de devises',
            orientation: 'landscape',
            titleAttr: 'Exporter en PDF',
            pageSize: 'LEGAL',
            exportData: {decodeEntities:true}
        }, {
            text: '<i class="fa fa-file-code-o growExport black" aria-hidden="true" ></i>',
            titleAttr: 'Exporter en JSON',
            action: function ( e, dt, button, config ) {
                var data = dt.buttons.exportData();

                $.fn.dataTable.fileSave(
                    new Blob( [ JSON.stringify( data ) ] ),
                    'Export.json'
                );
            }
        }

    ]).withBootstrap()
    ;

    vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1)



    ];
    $scope.chercherEtudiants=function (stage) {
        $scope.clicked=1;
        getStagiaires.get({ stage: stage}, function(data){
            console.log(data)
            vm.persons=data.Results;
        })
    }


})
app.controller("getParType", function(getPersonParType,getTypes,$scope,DTOptionsBuilder, DTColumnDefBuilder) {
getTypes.get(function (data) {
    $scope.types=data.Types;
})
    var type="étudiant";


    $scope.clicked=0;
    var vm = this;
    vm.persons = [];

    vm.dtOptions = DTOptionsBuilder.newOptions().withDOM('Blfrtip').withBootstrap().withPaginationType('full_numbers').withDisplayLength(15).withButtons([

        {
            extend: "excelHtml5",
            name: 'Excel',
            title: 'DeviseTable',

            text : '<i class="fa fa-file-excel-o black  growExport "></i>',


            titleAttr: 'Exporter en Excel',

            //CharSet: "utf8",
            exportData: { decodeEntities: true }

        },
        {
            extend: "csvHtml5",

            text: ' <i class="fa fa-table black growExport" aria-hidden="true"></i>',
            title: 'DeviseTable',
            fileName:  "ExcelVersion" + ".xlsx",
            titleAttr: 'Exporter en CSV',

            exportData: {decodeEntities:true}
        }
        ,{
            extend: "copy",
            exportOptions: {
                columns: ':contains("Office")'
            },
            name: 'Copier',
            text : '<i class="fa fa-copy  growExport black"></i>',
            className: 'btn btn-default btn-sm ',
            titleAttr: 'Copier',


            exportData: {decodeEntities:true}
        } ,{
            extend: "print",
            name: 'Imprimer',
            text: '<span aria-hidden="true" class=" fa fa-print"></span>',
            titleAttr: 'Imprimer',

            exportData: {decodeEntities:true}
        } ,
        {
            extend: "pdfHtml5",
            name: 'Pdf',
            title: 'DeviseTable',
            text: '<i class="fa fa-file-pdf-o  ">',
            fileName:  "Pdf" ,
            message: 'Table de devises',
            orientation: 'landscape',
            titleAttr: 'Exporter en PDF',
            pageSize: 'LEGAL',
            exportData: {decodeEntities:true}
        }, {
            text: '<i class="fa fa-file-code-o growExport black" aria-hidden="true" ></i>',
            titleAttr: 'Exporter en JSON',
            action: function ( e, dt, button, config ) {
                var data = dt.buttons.exportData();

                $.fn.dataTable.fileSave(
                    new Blob( [ JSON.stringify( data ) ] ),
                    'Export.json'
                );
            }
        }

    ]).withBootstrap()
    ;

    vm.dtColumnDefs = [
        DTColumnDefBuilder.newColumnDef(0),
        DTColumnDefBuilder.newColumnDef(1)



    ];


$scope.selectType=function (typeSelected) {
    getPersonParType.get({ type: typeSelected},function (data) {
        vm.persons=data.Results;
        $scope.clicked=1;
    })
}




})
app.controller("getByDomaine", function(getProprietiesByDomaine,getDomaines,chercherParDomaine,getTypes,$scope,DTOptionsBuilder, DTColumnDefBuilder) {

    getDomaines.get(function (data) {
        $scope.domaines=data.Domaines
        console.log($scope.domaines)

    })

    $scope.operateurs=new Array()
    $scope.operateurs=["Egal","Inférieur","Inférieur ou égal","Supérieur","Supérieur ou égal","Différent","Débute par"
    ,"Finit par","Est dans la liste","Contient","Est vide","Vrai","Faux","Ne débute pas par","Ne finit pas par"
        ,"N'est pas dans la liste","Ne contient pas","N'est pas vide","Soundex"]



    $scope.domaineIsSelected=0;
$scope.selectDomain=function (domainSelected) {
$scope.domaineSelected=domainSelected
    $scope.domaineIsSelected=1
    getProprietiesByDomaine.get({domaine: domainSelected},function (data) {
        $scope.proprietes=data.Proprietes
    })
}
$scope.op=""
$scope.selectOp=function (op) {

console.log(op)
    switch(op) {
        case "Egal":
            op=0
            break;
        case "Inférieur":
            op=1
            break;
        case "Inférieur ou égal":
            op=2
            break;
        case "Supérieur":
            op=3
            break;
        case "Supérieur ou égal":
            op=4
            break;
        case "Différent":
            op=5
            break;
        case "Débute par":
            op=6
            break;
        case "Finit par":
            op=7
            break;
        case "Est dans la liste":
            op=8
            break;
        case "Contient":
            op=9
            break;
        case "Est vide":
            op=10
            break;
        case "Vrai":
            op=11
            break;
        case "Faux":
            op=12
            break;
        case "Ne débute pas par":
            op=13
            break;
        case "Ne finit pas par":
            op=14
            break;
        case "N'est pas dans la liste":
            op=15
            break;
        case "Ne contient pas":
            op=16
            break;
        case "N'est pas vide":
            op=17
            break;
        case "Soundex":
            op=18
            break;

        default:
            console.log("erreyr")
    }
    $scope.op=op
}
$scope.selection=""
$scope.getSelection=function (selection) {
    $scope.selection=selection
}
$scope.proprieteSelected=""
$scope.selectPropriete=function (proprieteSelected) {
    $scope.proprieteSelected=proprieteSelected
}

$scope.clicked=0;
$scope.buildJson=function () {
    var json=new Array();

    json=({
        filtre : {critere:{champ: $scope.proprieteSelected,
            operateur:$scope.op ,

            valeur: $scope.valeur},filtres:[null],operateurInterFiltre:0},listeProprietes:$scope.selection});

    console.log(json)
    $scope.clicked=1;
    chercherParDomaine.save({domaine:$scope.domaineSelected},json,function (data) {
        $scope.resultats=data.Results
    })
}


})
