package cn.byxll.goods.dto;

import cn.byxll.goods.pojo.Sku;
import cn.byxll.goods.pojo.Spu;

import java.io.Serializable;
import java.util.List;

/**
 * 商品dto
 * @author By-Lin
 */
public class Goods implements Serializable {
    private List<Sku> skuList;
    private Spu spu;

    public Goods() {
    }

    public Goods(List<Sku> skuList, Spu spu) {
        this.skuList = skuList;
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }
}
