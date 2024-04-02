package org.task.common.net;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.net.HttpSendComp;
import org.commons.log.LogComp;
import org.database.mysql.domain.task.TaskShell;
import org.database.mysql.domain.task.shell.HttpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Administrator
 * @Package : org.task.common.net
 * @Create on : 2024/4/2 下午2:03
 **/


@Component
public class TaskShellCheckComp {
    private final Logger logger = LogComp.getLogger(TaskShellCheckComp.class);

    @Autowired
    private HttpSendComp httpSendComp;


    /**
     * 检查任务脚本是否可行
     *
     * @param taskShell 任务脚本
     * @return 可行
     */
    public boolean checkTaskShell(TaskShell taskShell) {
        if (taskShell == null) {
            return false;
        }
        switch (taskShell.getShellType()) {
            case TCP: {
                return checkTcpRequest(taskShell);
            }
            case HTTP: {
                return checkHttpRequest(taskShell);
            }
            default: {
                return false;
            }
        }
    }

    @SneakyThrows
    private boolean checkHttpRequest(TaskShell taskShell) {
        try (CloseableHttpClient httpClient = httpSendComp.createHttpClient()) {
            String url = httpSendComp.createUrl(taskShell.getShellIp(), taskShell.getShellPort(), taskShell.getShellUrl());
            ClassicRequestBuilder httpRequestBuilder = createHttpRequest(taskShell.getShellHttpType(), url);
            // 设置参数头
            if (taskShell.getShellHeader() != null) {
                for (Map.Entry<String, String> entry : taskShell.getShellHeader().entrySet()) {
                    httpRequestBuilder.setHeader(entry.getKey(), entry.getValue());
                }
            }

            // 设置窗体参数
            if (taskShell.getShellParam() != null) {
                for (Map.Entry<String, String> entry : taskShell.getShellParam().entrySet()) {
                    httpRequestBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }


            if (!Strings.isEmpty(taskShell.getShellBody())) {
                httpRequestBuilder.setEntity(new StringEntity(taskShell.getShellBody(), ContentType.APPLICATION_JSON));
            }

            ClassicHttpRequest httpRequest = httpRequestBuilder.build();

            // 原则上只要是200就行 之后就是之后具体测试的问题了
            String execute = httpSendComp.execute(httpClient, httpRequest);
            return execute != null;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    private ClassicRequestBuilder createHttpRequest(HttpType httpType, String url) {
        switch (httpType) {
            case POST:
                return ClassicRequestBuilder.post(url);
            case PUT:
                return ClassicRequestBuilder.put(url);
            case DELETE:
                return ClassicRequestBuilder.delete(url);
            case HEAD:
                return ClassicRequestBuilder.head(url);
            case TRACE:
                return ClassicRequestBuilder.trace(url);
            case OPTIONS:
                return ClassicRequestBuilder.options(url);
            default:
            case GET:
                return ClassicRequestBuilder.get(url);
        }

    }

    @SneakyThrows
    private boolean checkTcpRequest(TaskShell taskShell) {
        try (Socket socket = new Socket(taskShell.getShellIp(), taskShell.getShellPort())) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(taskShell.getShellBody().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            int len = inputStream.available();
            byte[] buffer = new byte[len];
            inputStream.read(buffer);
            return !Strings.isEmpty(new String(buffer));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}
