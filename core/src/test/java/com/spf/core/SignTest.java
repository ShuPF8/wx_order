package com.spf.core;

import com.spf.core.utlis.SignUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-07-06 17:48
 */
public class SignTest {

    @Test
    public void test() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("timestamp",System.currentTimeMillis());
        params.put("index","1");
        params.put("sign", new SignUtil().sign(params,"utf-8"));
        System.out.println(params.toString());
    }

    @Test
    public void test2() throws Exception {
        System.out.println(DigestUtils.md5Hex("weixin_order |/index"));
    }

}
