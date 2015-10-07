
var infoBlock = {

    elem: $("#info-block")
};

// States
infoBlock.setState = function(state, message) {
    var message = typeof message !== 'undefined' ? message : state.message;
    infoBlock.elem.html(message);

    infoBlock._clearClass();
    infoBlock.elem.addClass(state.styleClass);
};

infoBlock.states = {
    SUCCESS: {
        styleClass: "alert-success",
        message: "Сообщение успешно отправлено!"
    },
    ERROR: {
        styleClass: "alert-danger",
        message: "Произошла ошибка. Сообщение не может быть отправлено."
    },
    WAIT: {
        styleClass: "alert-info",
        message: "Идет отправка сообщения..."
    }
};

infoBlock._clearClass = function() {

    for(var keyStyle in infoBlock.states) {
        infoBlock.elem.removeClass(infoBlock.states[keyStyle].styleClass);
    }
};