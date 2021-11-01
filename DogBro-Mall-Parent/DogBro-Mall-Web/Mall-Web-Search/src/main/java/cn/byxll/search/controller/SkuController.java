package cn.byxll.search.controller;


import cn.byxll.search.dto.SearchParam;
import cn.byxll.search.feign.SkuFeign;
import cn.byxll.search.pojo.SkuInfo;
import cn.byxll.search.vo.GoodsListVO;
import entity.Page;
import entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * web前端页面控制器
 * @author By-Lin
 */
@Controller
@RequestMapping("/search")
public class SkuController {
    private final SkuFeign skuFeign;

    public SkuController(SkuFeign skuFeign) {
        this.skuFeign = skuFeign;
    }

    /**
     * 搜索默认路由
     * @param searchParam       查询条件
     * @param model             页面对象
     * @return                  跳转的页面模板名称
     */
    @GetMapping("/list")
    public String search(SearchParam searchParam, Model model) throws IllegalAccessException {
        Result<GoodsListVO> result = skuFeign.search(searchParam);
        model.addAttribute("result",result.getData());
        model.addAttribute("searchMap",searchParam);
        // 搜索的url回传
        String url = url(searchParam);
        model.addAttribute("url",url);

        //创建一个分页的对象  可以获取当前页 和总个记录数和显示的页码(以当前页为中心的5个页码)
        Page<SkuInfo> infoPage = new Page<>(
                Long.parseLong(result.getData().getTotal().toString()),
                result.getData().getPageNum(),
                result.getData().getPageSize()
        );

        model.addAttribute("page",infoPage);
        return "search";
    }


    /**
     * 内部方法 将参数实体拼接成url
     * @param searchMap     参数实体
     * @return              url 字符串
     */
    private String url(SearchParam searchMap) throws IllegalAccessException {
        String url = "/search/list";
        Class<? extends SearchParam> cls = searchMap.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(searchMap));
        }



//            //去掉多余的&
//            if(url.lastIndexOf("&")!=-1){
//                url =  url.substring(0,url.lastIndexOf("&"));
//            }

        return url;
    }
}
