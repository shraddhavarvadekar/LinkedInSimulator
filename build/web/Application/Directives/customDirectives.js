/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// http://stackoverflow.com/questions/14703517/angular-js-set-element-height-on-page-load

angular.module('AngularInstance').directive('resize', function ($window) {
    return function (scope, element) {
        var w = angular.element($window);
        scope.getWindowDimensions = function () {
            console.log( { 'h': w.height(), 'w': w.width() })
            return { 'h': w.height(), 'w': w.width() };
        };
        scope.$watch(scope.getWindowDimensions, function (newValue, oldValue) {
            scope.windowHeight = newValue.h;
            scope.windowWidth = newValue.w;

            scope.style = function () {
                return { 
                    'height': (newValue.h - 100) + 'px',
                    'width': (newValue.w - 100) + 'px' 
                };
            };

        }, true);

        w.bind('resize', function () {
            scope.$apply();
        });
    }
})
