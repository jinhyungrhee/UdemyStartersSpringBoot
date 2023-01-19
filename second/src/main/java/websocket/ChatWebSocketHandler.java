package websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

// 1개 웹소켓 클라이언트 - 여러개 웹소켓 클라이언트 공유
@Component
public class ChatWebSocketHandler implements WebSocketHandler{

	public static List<WebSocketSession> list = new ArrayList();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 클라이언트 요청 시 즉각 호출
		list.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 클라이언트로부터 수신시 즉각 호출
		// session - 웹소켓 클라이언트 객체 1개
		// message - 웹소켓 클라이언트가 보낸 문자열 데이터 - nickname:xxxxx
		String msg = (String)message.getPayload();
		for(WebSocketSession s : list) {
			WebSocketMessage<String> sendmsg = new TextMessage(msg);
			s.sendMessage(sendmsg);
			
		}
		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// 클라이언트 연결 해제시 즉각 호출
		list.remove(session);		
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	
	
}
