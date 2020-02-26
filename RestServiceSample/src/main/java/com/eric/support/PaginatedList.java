package com.eric.support;

import java.util.List;

/**
 * @param <T>
 */
public interface PaginatedList<T> extends List<T> {

    boolean isMiddlePage();

    boolean isLastPage();

    boolean isNextPageAvailable();

    boolean isPreviousPageAvailable();

    int getPageSize();

    void setPageSize(int pageSize);

    int getIndex();

    void setIndex(int index);

    int getTotalItem();

    void setTotalItem(int totalItem);

    int getTotalPage();

    int getStartRow();

    int getEndRow();

    int getNextPage();

    int getPreviousPage();

    boolean isFirstPage();
}
