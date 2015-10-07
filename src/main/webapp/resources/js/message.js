var messageSendForm = {
    phone: $("#phone"),
    text: $("#text"),

    serviceUrl: "/send_sms"
};

// States
messageSendForm.defaultState = function() {
    messageSendForm._enabled();
    messageSendForm._clear();
};

messageSendForm.errorState = function() {
    messageSendForm._enabled();
};

messageSendForm.waitState = function() {
    messageSendForm._disable();
};

messageSendForm.states = {
    DEFAULT: messageSendForm.defaultState,
    ERROR: messageSendForm.errorState,
    WAIT: messageSendForm.waitState
};

messageSendForm.validationMessages = {
    INCORRECT_PHONE: "Номер телефона введен не корректно.",
    EMPTY_MESSAGE: "Введите текст сообщения."
};

messageSendForm.validatePhone = function() {
    return /^79\d{9}$/.test(messageSendForm.phone.val());
};

messageSendForm.validateMessage = function() {
    return !!messageSendForm.text.val()
};


messageSendForm.validate = function() {
    if (!messageSendForm.validatePhone()) {
        return {
            status: false,
            message: messageSendForm.validationMessages.INCORRECT_PHONE
        };
    }

    if (!messageSendForm.validateMessage()) {
        return {
            status: false,
            message: messageSendForm.validationMessages.EMPTY_MESSAGE
        };
    }

    return {
        status: true,
        message: ""
    };
};

messageSendForm.send = function() {
    $.ajax({
        url: this.serviceUrl,
        dataType: 'json',
        data: {
            tel_number: messageSendForm.getPhone(),
            message: messageSendForm.getText()
        },
        success: page.sendMessageCallBack
    });
};

messageSendForm.getPhone = function() {
    return messageSendForm.phone.val();
};

messageSendForm.getText = function() {
    return messageSendForm.text.val();
};


// private
messageSendForm._clear = function() {
    this.phone.val('');
    this.text.val('');
};

messageSendForm._disable = function() {
    this.phone.attr("disabled", "disabled");
    this.text.attr("disabled", "disabled");
};

messageSendForm._enabled = function() {
    this.phone.removeAttr("disabled");
    this.text.removeAttr("disabled");
};