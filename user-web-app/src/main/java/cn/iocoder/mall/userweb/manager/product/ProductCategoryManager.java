package cn.iocoder.mall.userweb.manager.product;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.category.ProductCategoryRpc;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.category.dto.ProductCategoryRespDTO;
import cn.iocoder.mall.userweb.controller.product.vo.category.ProductCategoryRespVO;
import cn.iocoder.mall.userweb.convert.product.ProductCategoryConvert;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Product 分类 Manager
 */
@Service
@Validated
public class ProductCategoryManager {

    @Reference(version = "${dubbo.consumer.ProductCategoryRpc.version}")
    private ProductCategoryRpc productCategoryRpc;

    public List<ProductCategoryRespVO> listProductCategories(Integer pid) {
        CommonResult<List<ProductCategoryRespDTO>> listProductCategoriesResult = productCategoryRpc.listProductCategories(
                new ProductCategoryListQueryReqDTO().setPid(pid).setStatus(CommonStatusEnum.ENABLE.getValue()));
        listProductCategoriesResult.checkError();
        return ProductCategoryConvert.INSTANCE.convertList(listProductCategoriesResult.getData());
    }

}
