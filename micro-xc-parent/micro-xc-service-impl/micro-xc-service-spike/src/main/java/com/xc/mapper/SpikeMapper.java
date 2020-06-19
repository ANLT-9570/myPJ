package com.xc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.mapper.entity.seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SpikeMapper extends BaseMapper<seckill> {


    @Update("update seckill set inventory = inventory-1 where seckill_id = #{seckillId} and inventory > 0")
    int inventoryDeduction(@Param("seckillId") Long seckillId);

    //基于乐观锁
    @Update("update seckill set inventory = inventory-1 ,version=version+1 where seckill_id = #{seckill} and inventory>0 and version=#{version}")
    int inventoryDeductionWithVersion(@Param("seckill") Long seckill,@Param("version") int version);
}
