package cn.dsc.parser.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dingshichen
 */
@Getter
@Setter
public class Paging {

    private int first = 1;
    private int before;
    private int current;
    private int last;
    private int next;
    private int limit;
    private int totalPages;
    private long totalItems;

    public Paging(Integer pageNo, Integer pageSize, Integer totalPage, Long totalCount) {
        this.before = pageNo <= 1 ? 1 : pageNo - 1;
        this.current = pageNo;
        this.last = totalPage;
        this.next = pageNo + 1 >= totalPage ? totalPage : pageNo + 1;
        this.limit = pageSize;
        this.totalPages = totalPage;
        this.totalItems = totalCount;
    }
}
