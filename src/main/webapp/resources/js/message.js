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
    INCORRECT_PHONE: "Номер телефона введен не корректно"
};


messageSendForm.validate = function() {
    // Now if you want to send empty message, so... good luck
    var testPhone = /^79\d{9}$/.test(this.phone.val());

    if (testPhone) {
        return {
            status: true,
            message: ""
        };
    }

    return {
        status: false,
        message: messageSendForm.validationMessages.INCORRECT_PHONE
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