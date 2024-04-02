package tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Administrator
 * @Package : tcp
 * @Create on : 2024/4/2 下午3:19
 **/


public class TcpServer {
    public static void main(String[] args) throws IOException {
        int port = 12345; // 服务器端口
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            // 监听并接受客户端的连接请求
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // 处理客户端请求
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("message:" + inputLine);
                out.println(inputLine); // 将接收到的消息原样发送回客户端
                if (inputLine.equals("END")) {
                    break; // 如果接收到'END'，则结束与客户端的通信
                }
            }

            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
        }
    }
}
