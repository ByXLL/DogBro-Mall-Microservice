package cn.byxll.goods.vo;

import cn.byxll.search.pojo.SkuInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 商品搜索页 商品列表视图模型
 * @author By-Lin
 */
public class GoodsListVO implements Serializable {
    private Long total;
    private Integer totalPages;
    private Integer pageNum;
    private Integer pageSize;
    private List<String> categoryList;
    private List<String> brandList;
    private Map<String, Set<String>> specList;
    private List<SkuInfo> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<SkuInfo> getRows() {
        return rows;
    }

    public void setRows(List<SkuInfo> rows) {
        this.rows = rows;
    }

    public List<String> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<String> brandList) {
        this.brandList = brandList;
    }

    public Map<String, Set<String>> getSpecList() {
        return specList;
    }

    public void setSpecList(Map<String, Set<String>> specList) {
        this.specList = specList;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "GoodsListVO{" +
                "total=" + total +
                ", totalPages=" + totalPages +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", categoryList=" + categoryList +
                ", brandList=" + brandList +
                ", specList=" + specList +
                ", rows=" + rows +
                '}';
    }
}
