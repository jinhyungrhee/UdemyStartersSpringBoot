package websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// @RequestMapping이 클래스명 위에 붙는 것은 지양되어야 함
@Controller
public class ChatController {
	
	@RequestMapping("/chat")
	public String chat(String id) {
		return "websocket/websocket";
	}

}
