package cn.byxll.goods.vo;

import cn.byxll.search.SkuInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 商品搜索页 商品列表视图模型
 * @author By-Lin
 */
public class GoodsListVO implements Serializable {
    private Integer total;
    private Integer totalPages;
    private List<String> categoryList;
    private List<SkuInfo> row;

    public GoodsListVO() {
    }

    public GoodsListVO(Integer total, Integer totalPages, List<String> categoryList, List<SkuInfo> row) {
        this.total = total;
        this.totalPages = totalPages;
        this.categoryList = categoryList;
        this.row = row;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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

    public List<SkuInfo> getRow() {
        return row;
    }

    public void setRow(List<SkuInfo> row) {
        this.row = row;
    }
}
