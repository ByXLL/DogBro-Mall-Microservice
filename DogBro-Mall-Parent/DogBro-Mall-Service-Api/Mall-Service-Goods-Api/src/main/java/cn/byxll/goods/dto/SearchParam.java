package cn.byxll.goods.dto;

import java.io.Serializable;

/**
 * 商品搜索参数 dto
 * @author By-Lin
 */
public class SearchParam implements Serializable {
    private Integer page;
    private Integer pageSize;
    private String keywords;

    public SearchParam() {
    }

    public SearchParam(Integer page, Integer pageSize, String keywords) {
        this.page = page;
        this.pageSize = pageSize;
        this.keywords = keywords;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
