package cn.byxll.search.dao;


import cn.byxll.search.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * SkuEs dao 接口
 * 实现将数据导入到ES索引库中
 * @author By-Lin
 */
@Repository
public interface SkuEsMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
