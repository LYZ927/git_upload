var HashMap = function () {

    let map = {};
    return {
        put: function (key, value) {
            map[key] = value;
        },

        keys: function () {
            //object.key改寫
            return Object.keys(obj);
        },
        contains: function (key) {
            //array.includes(key)改寫
            return Object.keys(obj).contains(key);
        },
        get: function (key) {
            return map[key];
        },
        clear: function () {
            return map = {};
        }
    };
};