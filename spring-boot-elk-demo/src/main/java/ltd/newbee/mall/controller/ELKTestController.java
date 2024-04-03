/*
  严肃声明：
  开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
  本系统已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
  可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
  Copyright (c) 2019-2020 十三 all rights reserved.
  版权所有，侵权必究！
 */
package ltd.newbee.mall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 十三
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@Controller
public class ELKTestController {

    private static final Logger log = LoggerFactory.getLogger(ELKTestController.class);

    @GetMapping("/elk-logs")
    @ResponseBody
    public String elkTest() {
        log.debug("DEBUG级别日志输出 --> ELK");
        log.info("INFO级别日志输出 --> ELK");
        log.error("ERROR级别日志输出 --> ELK");

        return "hello,elk!";
    }
}
