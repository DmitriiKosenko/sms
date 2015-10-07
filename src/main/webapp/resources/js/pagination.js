var pagination = {
    tableElem: table,

    visiblePagesCount: 7,
    currentPage: 1
};

pagination.init = function(total) {

    total = typeof total !== 'undefined' ? total : 1;

    $("#pagination").twbsPagination({
        totalPages: total,
        startPage: pagination.currentPage,
        visiblePages: pagination.visiblePagesCount,
        onPageClick: pagination.clickEvent
    });
};

pagination.clickEvent = function(event, page) {
    pagination.currentPage = page;
    pagination.tableElem.getContent(event, page);
};

pagination.setTotalCount = function(total) {

    $("#pagination-holder").html("");
    $("#pagination-holder").html("<ul id=\"pagination\" class=\"pagination\"></ul>");

    pagination.init(total);
};