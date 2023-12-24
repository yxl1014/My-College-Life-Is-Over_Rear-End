package org.commons.response;

/**
 * 1xx系列 (Informational): 提示信息，表示请求已被接收，继续处理。
 * <p>
 * 100 Continue: 服务器已经接收到请求头，并且客户端应继续发送请求体。
 * 2xx系列 (Successful): 表示请求被成功接收、理解、接受或处理。
 * <p>
 * 200 OK: 请求成功，服务器正常返回数据。
 * 201 Created: 请求已经被成功处理，并且服务器创建了新的资源。
 * 204 No Content: 服务器成功处理请求，但没有返回任何内容。
 * <p>
 * 3xx系列 (Redirection): 表示需要进行附加操作以完成请求。*
 * 301 Moved Permanently: 资源被永久移动到其他位置。
 * 302 Found (or Moved Temporarily): 请求的资源临时从不同的URI响应请求。
 * 304 Not Modified: 资源未被修改，可以使用缓存的版本。
 * <p>
 * 4xx系列 (Client Error): 表示客户端发生错误，请求包含无效信息。
 * 400 Bad Request: 服务器无法理解请求的语法。
 * 401 Unauthorized: 请求要求用户身份验证。
 * 403 Forbidden: 服务器理解请求，但拒绝执行。
 * 404 Not Found: 请求的资源未找到。
 * <p>
 * 5xx系列 (Server Error): 表示服务器在处理请求时发生错误。
 * 500 Internal Server Error: 通用错误消息，服务器遇到了一个未预料的情况。
 * 502 Bad Gateway: 服务器作为网关或代理，从上游服务器接收到无效响应。
 * 503 Service Unavailable: 服务器目前无法处理请求，通常是由于过载或维护。
 *
 * @author yxl17
 * @Package : org.example.response
 * @Create on : 2023/11/11 20:55
 **/
public enum RepCode {
    //成功
    R_Ok(200, "成功"),
    //失败
    R_Fail(400, "失败"),
    //异常
    R_Error(500, "异常"),
    //参数异常
    R_ParamError(400, "参数异常"),
    R_ControllerError(401, "接口不存在"),
    R_TokenError(402,"token已失效"),
    ;
    private int code;
    private String msg;

    RepCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}