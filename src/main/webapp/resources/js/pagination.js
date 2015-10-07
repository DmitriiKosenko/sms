var pagination = {
    elem: $("#pagination"),
    tableElem: table,

    visiblePagesCount: 7,
    totalPagesCount: 0
};


pagination.init = function() {

    pagination.elem.twbsPagination({
        totalPages: pagination.totalPagesCount,
        visiblePages: pagination.visiblePagesCount,
        onPageClick: pagination.clickEvent
    });
};

pagination.clickEvent = function(event, page) {
    pagination.tableElem.getContent(event, page);
};

pagination.setTotalCount = function(total) {
    console.log("total: " + total);
};