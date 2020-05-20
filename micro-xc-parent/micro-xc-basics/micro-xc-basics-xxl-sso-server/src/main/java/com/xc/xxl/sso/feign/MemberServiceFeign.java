package com.xc.xxl.sso.feign;

import com.xc.service.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("xc-member")
public interface MemberServiceFeign extends MemberService {
}
