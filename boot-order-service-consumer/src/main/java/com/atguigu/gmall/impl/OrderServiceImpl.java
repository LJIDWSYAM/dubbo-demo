package com.atguigu.gmall.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.OrderService;
import com.atguigu.gmall.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 1銆佸皢鏈嶅姟鎻愪緵鑰呮敞鍐屽埌娉ㄥ唽涓績锛堟毚闇叉湇鍔★級
 * 		1锛夈�佸鍏ubbo渚濊禆锛�2.6.2锛塡鎿嶄綔zookeeper鐨勫鎴风(curator)
 * 		2锛夈�侀厤缃湇鍔℃彁渚涜��
 * 
 * 2銆佽鏈嶅姟娑堣垂鑰呭幓娉ㄥ唽涓績璁㈤槄鏈嶅姟鎻愪緵鑰呯殑鏈嶅姟鍦板潃
 * @author lfy
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Reference
	UserService userService;
	@Override
	@HystrixCommand(fallbackMethod = "hello")
	public List<UserAddress> initOrder(String userId) {
		// TODO Auto-generated method stub
		System.out.println("用户id："+userId);
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		return addressList;
	}
    public List<UserAddress> hello(String userId) {
        // TODO Auto-generated method stub

        return Arrays.asList(new UserAddress(10,"测试地址","1","测试","测试","Y"));
    }
}
