package cn.byxll.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品搜索参数 dto
 * @author By-Lin
 */
public class SearchParam implements Serializable {
    private Integer page;
    private Integer pageSize;
    private String keywords;
    private Integer minPrice;
    private Integer maxPrice;
    private String sortField;
    private String sortRule;

    public SearchParam() {
    }

    public SearchParam(Integer page, Integer pageSize, String keywords, Integer minPrice, Integer maxPrice, String sortField, String sortRule) {
        this.page = page;
        this.pageSize = pageSize;
        this.keywords = keywords;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortField = sortField;
        this.sortRule = sortRule;
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

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortRule() {
        return sortRule;
    }

    public void setSortRule(String sortRule) {
        this.sortRule = sortRule;
    }
}
