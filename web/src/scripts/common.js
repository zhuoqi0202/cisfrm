/**
 * Created by Dawn on 16/3/9.
 */
//序列化Form参数
var serializeObject = function(form) {
    var o = {};
    $.each(form.serializeArray(), function(index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']]+","+this['value'];
        }else{
            o[this['name']]=this['value'];
        }
    });
    return o;
};
