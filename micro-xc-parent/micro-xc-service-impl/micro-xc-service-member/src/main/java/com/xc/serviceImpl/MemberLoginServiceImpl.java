package com.xc.serviceImpl;

import com.xc.enums.ConstantsEnum;
import com.xc.exception.ExceptionCode;
import com.xc.input.dto.UserLoginInpDTO;
import com.xc.mapper.UserMapper;
import com.xc.mapper.UserTokenMapper;
import com.xc.mapper.entity.UserDo;
import com.xc.mapper.entity.UserTokenDo;
import com.xc.result.Result;
import com.xc.service.MemberLoginService;
import com.xc.token.GenerateToken;
import com.xc.utils.MD5Util;
import com.xc.utils.RedisDataSourceTransaction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberLoginServiceImpl implements MemberLoginService {

    @Resource
    UserMapper userMapper;
    @Autowired
    GenerateToken generateToken;
    @Resource
    UserTokenMapper userTokenMapper;
    @Autowired
    RedisDataSourceTransaction redisDataSourceTransaction;

    @Transactional
    @Override
    public Result login(@RequestBody UserLoginInpDTO userLoginInpDTO) throws Exception {
        if(StringUtils.isEmpty(userLoginInpDTO.getMobile())){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        if(StringUtils.isEmpty(userLoginInpDTO.getPassword())){
            return Result.failureResult(ExceptionCode.Failure.NOT_PWD);
        }
        if(StringUtils.isEmpty(userLoginInpDTO.getLoginType())){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }
        if(!(ConstantsEnum.IOS.message.equals(userLoginInpDTO.getLoginType())
          || ConstantsEnum.ANDROID.message.equals(userLoginInpDTO.getLoginType())
                || ConstantsEnum.PC.getMessage().equals(userLoginInpDTO.getLoginType()))){
            return Result.failureResult(ExceptionCode.Failure.ERROR_TYPE);
        }
        String newPwd = MD5Util.getMD5Code(userLoginInpDTO.getPassword());
        UserDo userDo = userMapper.selectByMobileAndPassword(userLoginInpDTO.getMobile(),newPwd);
        if(userDo == null){
            return Result.failureResult(ExceptionCode.Failure.ERROR_USER);
        }
        TransactionStatus transactionStatus = null;
        try {
            Long userId = userDo.getId();
            //根据用id和设备类型查询是否正在登录状态
            UserTokenDo userTokenDo = userTokenMapper.selectByIdAndLoginTypeAndIsVailable(userId,userLoginInpDTO.getLoginType(),0);
            //开启事务
             transactionStatus = redisDataSourceTransaction.begin();
            if(userTokenDo != null){//如果不为空
                String newToken = userTokenDo.getToken();
                userTokenDo.setIsAvailable(1L);
                //redis开启事务会返回false
                Boolean removeToken = generateToken.removeToken(newToken);
                int isFlag = userTokenMapper.updateById(userTokenDo);
                if(isFlag<1){
                    redisDataSourceTransaction.rollback(transactionStatus);
                }
            }

            UserTokenDo newUserTokenDo = new UserTokenDo();
            newUserTokenDo.setUserId(userId);

            //前缀
            String keyPrefix = ConstantsEnum.KEY_PREFIX.getMessage()+userLoginInpDTO.getLoginType();
            String token = generateToken.createToken(keyPrefix, userDo.getId() + "");

            newUserTokenDo.setToken(token);
            newUserTokenDo.setLoginType(userLoginInpDTO.getLoginType());
            newUserTokenDo.setDeviceInfo(userLoginInpDTO.getDeviceInfo());

            int insert = userTokenMapper.insert(newUserTokenDo);
            if(insert<1){
                redisDataSourceTransaction.rollback(transactionStatus);
                return Result.failureResult("500","系统异常");
            }
            Map<Object,String> map = new HashMap<>();
            map.put("token",token);
            redisDataSourceTransaction.commit(transactionStatus);
            return Result.successResult(map);
        }catch (Exception e){
            redisDataSourceTransaction.rollback(transactionStatus);
            return Result.failureResult("500","系统异常");
        }

    }

}
