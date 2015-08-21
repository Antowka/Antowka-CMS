// FORM VALIDATION

// Name and Surname validation
CommissionApp.directive('checkname', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(checkname) {
                if (/[a-zA-zА-яЁё]{2,20}/.test(checkname)) {
                    ctrl.$setValidity('checkname', true);
                    return checkname;
                } else {
                    ctrl.$setValidity('checkname', false);
                    return undefined;
                }
            });
        }
    };
});
// Email validation
CommissionApp.directive('checkemail', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(checkemail) {
                if (/^([a-zA-Z0-9])+([a-zA-Z0-9._%+-])+@([a-zA-Z0-9_.-])+\.(([a-zA-Z]){2,6})$/.test(checkemail)) {
                    ctrl.$setValidity('checkemail', true);
                    return checkemail;
                } else {
                    ctrl.$setValidity('checkemail', false);
                    return undefined;
                }
            });
        }
    };
});
// Phone validation
CommissionApp.directive('checkphone', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$parsers.unshift(function(checkphone) {
                if (/^[+\(\)0-9-\s]{5,18}$/.test(checkphone)) {
                    ctrl.$setValidity('checkphone', true);
                    return checkphone;
                } else {
                    ctrl.$setValidity('checkphone', false);
                    return undefined;
                }
            });
        }
    };
});
// File validation
CommissionApp.directive('checkfile', function () {

    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ngModel) {

            elem.bind('change', function () {
                validate(elem[0].files);
            });

            function validate(files) {
                var filePattern = new RegExp("^([a-zA-Z0-9А-яЁё\s_\\.\-:])+\.(jpg|JPG|jpeg|JPEG|png|PNG|gif|GIF|doc|DOC|pdf|PDF)$");

                // Check file type and size of each file
                if (filePattern.test(files[0].name) && files[0] && files[0].size < 10485760) {
                    ngModel.$setValidity('checkfile', true);
                } else {
                    ngModel.$setValidity('checkfile', false);
                    return undefined;
                }

            }
        }
    };

    function clearFileInput(ctrl) {
        try {
            ctrl.value = null;
        } catch(ex) { }
        if (ctrl.value) {
            ctrl.parentNode.replaceChild(ctrl.cloneNode(true), ctrl);
        }
    }
});