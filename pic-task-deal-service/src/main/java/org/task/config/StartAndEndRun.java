package org.task.config;

import org.apache.logging.log4j.Logger;
import org.commons.log.LogComp;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.task.service.ITaskConsumerService;

import java.util.Arrays;


/**
 * @author yxl17
 * @Package : org.starter.config
 * @Create on : 2024/5/21 22:53
 **/

@Component
public class StartAndEndRun implements CommandLineRunner, DisposableBean {
    private final Logger logger = LogComp.getLogger(StartAndEndRun.class);

    private final ITaskConsumerService consumerService;

    public StartAndEndRun(ITaskConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @Override
    public void destroy() throws Exception {
        logger.info("我关闭咯~");
        consumerService.onServerClose();
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("我启动咯~"+ Arrays.toString(args));
    }
}
