var page = {};

page.errorState = function (message) {
    messageSendForm.states.ERROR();
    infoBlock.setState(infoBlock.states.ERROR, message);
};

page.successState = function(message) {
    messageSendForm.states.DEFAULT();
    infoBlock.setState(infoBlock.states.SUCCESS);
};

page.waitState = function(message) {
    messageSendForm.states.WAIT();
    infoBlock.setState(infoBlock.states.WAIT);
};

page.sendMessageCallBack = function(data) {

    var result = data[0] * 1;

    if (result) {
        page.successState();

        return;
    }

    page.errorState(data[1] + "");
};

page.sendSms = function() {
    var validateResult = messageSendForm.validate();

    if (validateResult.status) {

        page.waitState();

        messageSendForm.send();
        return;
    }

    page.errorState(validateResult.message);
};

page.getHistoryCallback = function(data) {

    pagination.setTotalCount(data[0] * 1);
    table.initData(data[1]);
};