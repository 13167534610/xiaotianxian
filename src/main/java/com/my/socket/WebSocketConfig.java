package com.my.socket;

import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description:
 * @Author: wangqiang
 * @Date:2019/11/14 9:52
 */


//@Configuration
public class WebSocketConfig {
    //@Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
