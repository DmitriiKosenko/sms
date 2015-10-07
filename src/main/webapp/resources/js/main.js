var page = {};

page.sendMessageCallBack = function(data) {

    var result = data[0] * 1;

    if (result) {
        //info.setState();
        messageSendForm.states.DEFAULT();

        return;
    }

    //info.setState();
    messageSendForm.states.ERROR();

};

page.sendSms = function() {
    if (messageSendForm.validate()) {

        messageSendForm.states.WAIT();

        //info.setState();

        messageSendForm.send();
        return;
    }


    messageSendForm.states.ERROR();
    //info.setState();
};