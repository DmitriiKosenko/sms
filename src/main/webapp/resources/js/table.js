var table = {
    elem: $("#history_page"),
    serviceUrl: "/get_history",
    defaultPageNumber: 1,
    defaultPageSize: 10
};

table.init = function() {

    table.elem.dataTable( {
        "ordering" : false,
        "paging": false,
        "info": false,
        "columns": [
            {data: "telNumber"},
            {data: "dateSend"},
            {data: "status"},
            {data: "message"}
        ],
        "searching": false
    } );
};

table.initData = function(data) {

    if (data == undefined) {
        console.log("There is no data defined in function 'initTable'");
        return;
    }

    table.elem.dataTable().fnClearTable();

    if (data.length != 0) {
        table.elem.dataTable().fnAddData(data);
    }
};

table.getContentCallback = function(data) {
    pagination.init(data[0] * 1);
    table.initData(data[1]);
};

table.getContent = function(event, pageNumber, pageSize) {

    var pageNumber = typeof pageNumber !== 'undefined' ? pageNumber : table.defaultPageNumber;
    var pageSize = typeof pageSize !== 'undefined' ? pageSize : table.defaultPageSize;

    $.ajax({
        url: table.serviceUrl,
        dataType: 'json',
        data: {
            page: pageNumber,
            size: pageSize
        },
        success: table.getContentCallback
    });
};