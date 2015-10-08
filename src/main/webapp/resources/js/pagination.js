var pagination = {

    visiblePagesCount: 7,
    currentPage: 1
};

pagination.init = function(total) {

    $("#pagination-holder").html("");
    $("#pagination-holder").html("<ul id=\"pagination\" class=\"pagination\"></ul>");

    total = typeof total !== 'undefined' ? total : 1;

    $("#pagination").twbsPagination({
        totalPages: total,
        startPage: pagination.currentPage,
        visiblePages: pagination.visiblePagesCount,
        onPageClick: pagination.getContent
    });
};

pagination.getContent = function(event, page) {

    pagination.currentPage = page;

    table.getContent(event, page);
};