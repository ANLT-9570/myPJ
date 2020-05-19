package com.xc.web.feign;

import com.xc.service.MemberRegisterService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("xc-member")
public interface MemberRegisterServiceFeign extends MemberRegisterService {
}
