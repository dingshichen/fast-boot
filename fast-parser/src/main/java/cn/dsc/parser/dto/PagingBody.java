package cn.dsc.parser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author dingshichen
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingBody<T> {

    private List<T> body;
    private Paging paging;

    public PagingBody(List<T> body) {
        this.body = body;
    }
}
