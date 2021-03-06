package hifive.hifivespring.controller;


import hifive.hifivespring.domain.Member;
import hifive.hifivespring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //private final MemberService memberService = new MemberService();

    // 스프링 컨테이너에 등록
    private final MemberService memberService;

    // Dependency Injection
    @Autowired   // 생성자
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //http://localhost:8999/members/new
    //회원 등록
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String crate(MemberForm form){
        Member member = new Member();

        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }
    
    
    //회원 조회
    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}
