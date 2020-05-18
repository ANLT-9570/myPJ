package com.xc.bean;

import org.springframework.beans.BeanUtils;

public class BeanUtil<Dto,Do> {

    /**
     * dto转do
     * @param dtoEntity
     * @param doClass
     * @param <Do>
     * @return
     */
    public static <Do> Do dtoToDo(Object dtoEntity,Class<Do> doClass){
        if(dtoEntity == null){
            return null;
        }
        if (doClass==null){
            return null;
        }
        try {
            Do newInstance = doClass.newInstance();
            BeanUtils.copyProperties(dtoEntity,newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * do转dto
     * @param doEntity
     * @param dtoClass
     * @param <Dto>
     * @return
     */
    public static <Dto> Dto doToDto(Object doEntity,Class<Dto> dtoClass){
        if(doEntity == null){
            return null;
        }
        if (dtoClass==null){
            return null;
        }
        try {
            Dto newInstance = dtoClass.newInstance();
            BeanUtils.copyProperties(doEntity,newInstance);
            return newInstance;
        } catch (Exception e) {
            return null;
        }
    }

}
