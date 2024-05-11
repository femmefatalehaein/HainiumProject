package com.example.packpagedosirak.api;

import com.example.packpagedosirak.DTO.JoinDTO;
import com.example.packpagedosirak.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/joinform")
    public String showForm(Model model) {
        model.addAttribute("message", ""); // 초기 메시지는 비워둠
        // 이 메소드는 뷰 리졸버를 사용하여 'join.html'을 찾고 렌더링합니다.
        return "join";
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO, Model model) {
        // @ResponseBody 어노테이션을 이 메소드에만 적용하여, 반환값을 HTTP 응답 본문으로 직접 쓸 수 있습니다.
        boolean isJoined = joinService.joinProcess(joinDTO);
        if (!isJoined) {
            model.addAttribute("message", "이미 가입된 회원입니다.");
            return "join"; // 동일한 페이지에 메시지를 표시
        }
        model.addAttribute("message", "환영합니다! 가입이 완료되었습니다.");
        return "join"; // 성공 메시지도 같은 페이지에 표시
    }

}
