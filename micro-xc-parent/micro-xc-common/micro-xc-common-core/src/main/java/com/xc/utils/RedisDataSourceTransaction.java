package com.xc.utils;

import com.xc.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Component
@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE)//多列
public class RedisDataSourceTransaction {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开启事务 采用默认传播行为
     *
     * @return
     */
    public TransactionStatus begin(){
        //开启数据库事务
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        //开启redis事务
        redisUtil.begin();
        return transactionStatus;
    }

    public void commit(TransactionStatus transactionStatus) throws Exception{
        if(transactionStatus == null){
            throw new Exception("transactionStatus is null");
        }
        //支持redis与data数据的提交
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * 事务回滚
     * @param transactionStatus
     * @throws Exception
     */
    public void rollback(TransactionStatus transactionStatus)throws Exception{
        if(transactionStatus == null){
            throw new Exception("transactionStatus is null");
        }
        dataSourceTransactionManager.rollback(transactionStatus);
        redisUtil.discard();
    }

}
