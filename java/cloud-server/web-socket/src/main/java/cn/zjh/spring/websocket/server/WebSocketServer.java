package cn.zjh.spring.websocket.server;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocketServer {
    private static int onlineCount = 0;
    private static Map<String, WebSocketServer> clients = new ConcurrentHashMap();
    private Session session;
    private String username;

    public WebSocketServer() {
    }

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接" + username);
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(this.username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        synchronized(session) {
            this.session.getAsyncRemote().sendText(message);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        Iterator var3 = clients.values().iterator();

        while(var3.hasNext()) {
            WebSocketServer item = (WebSocketServer)var3.next();
            if (item.username.equals(To)) {
                item.session.getAsyncRemote().sendText(message);
            }
        }

    }

    public void sendMessageAll(String message) throws IOException {
        Iterator var2 = clients.values().iterator();

        while(var2.hasNext()) {
            WebSocketServer item = (WebSocketServer)var2.next();
            item.session.getAsyncRemote().sendText(message);
        }

    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ++onlineCount;
    }

    public static synchronized void subOnlineCount() {
        --onlineCount;
    }

    public static synchronized Map<String, WebSocketServer> getClients() {
        return clients;
    }

}
