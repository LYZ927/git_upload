var HashMap = function () {
    let obj = {};

    return {
        put: function (a, b) {
            obj[a] = b;
        },
        keys: function () {
            let i = [];
            for (let key in obj) {
                i.push(key)
            }
            return i;
        },
        contains: function (a) {
            if (obj[a]) {
                return true
            } else {
                return false
            }
        },
        get: function (a) {
            return obj[a];
        },
        clear: function () {
            return obj={};
        }
    };
};