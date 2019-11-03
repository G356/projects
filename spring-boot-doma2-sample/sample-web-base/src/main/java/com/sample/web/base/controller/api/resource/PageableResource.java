package com.sample.web.base.controller.api.resource;

public interface PageableResource extends Resource {

    int getPage();

    void setPage(int page);

    int getTotalPages();

    void setTotalPages(int totalPages);
}
