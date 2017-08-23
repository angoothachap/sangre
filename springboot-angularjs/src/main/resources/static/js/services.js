app.factory('PopulationFactory', function ($resource) {
    return $resource('/population/v1', {}, {
        query: {
            method: 'GET',
        }
    })
});
app.factory('LifeExpectancyFactory', function ($resource) {
    return $resource('/lifeExpectancy/v1', {}, {
        query: {
            method: 'GET',
        }
    })
});