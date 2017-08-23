var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider) {
    $routeProvider
        .when('/population', {
            templateUrl: '/views/population.html',
            controller: 'populationController'
        })
        .when('/lifeExpectancy', {
            templateUrl: '/views/lifeExpectancy.html',
            controller: 'lifeExpectancyController'
        })
        .otherwise(
            {redirectTo: '/'}
        );
});

