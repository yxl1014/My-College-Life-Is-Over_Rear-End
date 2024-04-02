package org.commons.common.net;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.entity.EntityBuilder;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.logging.log4j.Logger;
import org.commons.common.GsonUtil;
import org.commons.log.LogComp;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Administrator
 * @Package : org.commons.common.net
 * @Create on : 2024/4/1 下午6:20
 **/


@Component
public class HttpSendComp {
    private final Logger log = LogComp.getLogger(HttpSendComp.class);

    private final PoolingHttpClientConnectionManager connManager;

    public HttpSendComp(PoolingHttpClientConnectionManager connManager) {
        this.connManager = connManager;
    }

    @SneakyThrows
    public CloseableHttpClient createHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(connManager)
                .setConnectionManagerShared(true)
                .build();
    }


    @SneakyThrows
    public String execute(CloseableHttpClient httpClient, ClassicHttpRequest request) {
        ExecuteResult executeResult = httpClient.execute(request, response -> {
            final HttpEntity entity = response.getEntity();
            // do something useful with the response body
            InputStream content = entity.getContent();
            int len = content.available();
            byte[] buffer = new byte[len];
            content.read(buffer);
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
            return new ExecuteResult(response.getCode() == 200, new String(buffer));
        });
        return executeResult.success ? executeResult.message : null;
    }

    private static class ExecuteResult {
        boolean success;
        String message;

        public ExecuteResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }
    }


    /**
     * 构建完整url
     *
     * @param ip   ip
     * @param port port
     * @param url  url
     * @return fullUrl
     */
    public String createUrl(String ip, int port, String url) {
        return "http://" + ip + ":" + port + url;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        HashMap<String,String> map = new HashMap<>();
        map.put("username","aaa");
        System.out.println(GsonUtil.toJson(map));
        // 创建连接池管理器
        HttpConfig httpConfig = new HttpConfig();
        PoolingHttpClientConnectionManager cm = httpConfig.GetHttpClientConnectionManager();
        // 创建CloseableHttpClient
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        // 使用httpClient发送请求...
        ClassicHttpRequest httpGet = ClassicRequestBuilder.get("http://127.0.0.1:8080/test/get")
                .build();
        // by the connection manager.
        Boolean execute1 = httpClient.execute(httpGet, response -> {
            System.out.println(response.getCode() + " " + response.getReasonPhrase());
            final HttpEntity entity1 = response.getEntity();
            // do something useful with the response body
            System.out.println(entity1.getContentType());
            InputStream content = entity1.getContent();
            int len = content.available();
            System.out.println(len);
            byte[] buffer = new byte[len];
            content.read(buffer);
            // and ensure it is fully consumed
            EntityUtils.consume(entity1);
            return response.getCode() == 200;
        });
        System.out.println(execute1);

        ClassicHttpRequest httpPost = ClassicRequestBuilder.post("http://127.0.0.1:8080/test/post")
                .setEntity(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", "vip"),
                        new BasicNameValuePair("password", "secret"))))
                .build();
        Boolean execute = httpClient.execute(httpPost, response -> {
            System.out.println(response.getCode() + " " + response.getReasonPhrase());
            final HttpEntity entity2 = response.getEntity();
            // do something useful with the response body
            System.out.println(entity2.getContentType());
            InputStream content = entity2.getContent();
            int len = content.available();
            System.out.println(len);
            byte[] buffer = new byte[len];
            content.read(buffer);
            System.out.println(new String(buffer));
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
            return true;
        });
        String json = GsonUtil.toJson(new ExecuteResult(true, "xxx"));
        System.out.println(json);
        HttpEntity entity = EntityBuilder.create()
                .setContentType(ContentType.APPLICATION_JSON)
                .setText(json)
                .build();
        httpPost = ClassicRequestBuilder.post("http://127.0.0.1:8080/test/postJson")
                .setEntity(entity)
                .build();
        execute = httpClient.execute(httpPost, response -> {
            System.out.println(response.getCode() + " " + response.getReasonPhrase());
            final HttpEntity entity2 = response.getEntity();
            // do something useful with the response body
            System.out.println(entity2.getContentType());
            InputStream content = entity2.getContent();
            int len = content.available();
            System.out.println(len);
            byte[] buffer = new byte[len];
            content.read(buffer);
            System.out.println(new String(buffer));
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
            return true;
        });

        System.out.println(execute);

        // 关闭httpClient释放资源
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
