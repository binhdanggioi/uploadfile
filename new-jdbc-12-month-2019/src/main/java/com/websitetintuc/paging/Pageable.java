package com.websitetintuc.paging;

import com.websitetintuc.sort.Sorter;

public interface Pageable {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
