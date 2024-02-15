package com.kitri.todo.controller;

import com.kitri.todo.dto.request.RequestLogin;
import com.kitri.todo.dto.request.RequestSignUp;
import com.kitri.todo.dto.response.Member;
import com.kitri.todo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberApiController {
    @Autowired
    MemberService memberService;
//    @GetMapping("/login")
//    public String loginPage() {
//        return "redirect:/html/login.html";
//    }

    @PostMapping("/login")
    public String login(RequestLogin loginInfo, HttpServletRequest request, HttpServletResponse response) {
//        if (loginInfo.getRememberEmail() != null) {
//            response.addCookie(new Cookie("loginEmail", loginInfo.getEmail()));
//        }

        Member loginMember = memberService.login(loginInfo);
        if (loginMember == null) {
            return "redirect:/html/login.html";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        return "redirect:/todos";
    }

    @GetMapping("/logout")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/login";
    }

    @PostMapping("/signup")
    public String signUp(@Valid RequestSignUp form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/login/signup.html";
        }

        if (memberService.isDuplicated(form)) return "redirect:/login/signup.html";
        if (!memberService.isPasswordsSame(form)) return "redirect:/login/signup.html";

        memberService.joinMember(form);

        return "redirect:/html/success.html";
    }


}
