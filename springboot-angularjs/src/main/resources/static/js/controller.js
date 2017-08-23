app.controller('populationController', ['$scope', 'PopulationFactory', function ($scope, PopulationFactory) {
    $scope.headingTitle = "Total population today";
    $scope.population = [];
    PopulationFactory.get({}, function (population) {
        $scope.population = population.countryPopulationMap;
    },function (reason) {
        alert("Problem with Population workflow");
    })
}]);
app.controller('lifeExpectancyController', ['$scope', 'LifeExpectancyFactory', function ($scope, LifeExpectancyFactory) {
    $scope.headingTitle = "Average life expectancy for persons born on 1952-01-01";
    $scope.lifeExpectancy = [];
    LifeExpectancyFactory.get({}, function (lifeExpectancy) {
        $scope.lifeExpectancy = lifeExpectancy.countryLifeExpectancyMap;
    },function (reason) {
        alert("Problem with Life Expectancy workflow");
    })
}]);