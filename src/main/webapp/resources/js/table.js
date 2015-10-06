var table = {
    elem: $("#history_page"),
    serviceUrl: "/get_history",
    defaultPageNumber: 1,
    defaultPageSize: 10
};

table.init = function() {

    this.elem.dataTable( {
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

table.getContent = function(event, pageNumber, pageSize) {

    var pageNumber = typeof pageNumber !== 'undefined' ? pageNumber : this.defaultPageNumber;
    var pageSize = typeof pageSize !== 'undefined' ? pageSize : this.defaultPageSize;

    $.ajax({
        url: this.serviceUrl,
        dataType: 'json',
        data: {
            page: pageNumber,
            size: pageSize
        },
        success: this.initData
    });
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