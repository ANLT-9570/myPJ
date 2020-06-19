package com.xc;

import com.xc.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "秒杀服务")
@RequestMapping("/sp")
public interface SpikeService {

    /**
     * 秒杀接口
     * @param phone
     * @param seckillId
     * @return
     */
    @GetMapping("/spike")
     Result spike(String phone,Long seckillId);

    @GetMapping("/spike2")
    Result spike_v2(String phone,Long seckillId);

    @GetMapping("/addSpikeToken")
    Result addSpikeToken(Long seckillId,Integer tokenQuantity);

}
