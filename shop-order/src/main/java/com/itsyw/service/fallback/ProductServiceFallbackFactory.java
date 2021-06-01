package com.itsyw.service.fallback;

import com.itsyw.domain.Product;
import com.itsyw.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/3/10 14:20
 * @Version: 1.0
 * TODO: 这是一个容错的工厂类 ，要求我们要实现一个接口FalbackFactory<要为哪个接口产生容错类>
 */
@Slf4j
@Service
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {

    // Throwable 这就是fegin再调用过程中产生的异常
    @Override
    public ProductService create(Throwable throwable) {

        return new ProductService() {
            @Override
            public Product findByPId(Integer pid) {
                log.error("{}", throwable);
                Product product = new Product();
                product.setPid(-100);
                product.setPanem("远程调用商品微服务出现异常了，进入了容错逻辑");

                return null;
            }
        };
    }
}
