package com.kitri.todo.service;

import com.kitri.todo.dto.request.RequestLogin;
import com.kitri.todo.dto.request.RequestSignUp;
import com.kitri.todo.dto.response.Member;
import com.kitri.todo.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    public boolean isDuplicated(RequestSignUp form) {
        return memberMapper.findByEmail(form.getEmail()) != null ? true : false;
    }

    public boolean isPasswordsSame(RequestSignUp form) {
        return form.getPassword().equals(form.getPassword2());
    }

    public void joinMember(RequestSignUp form) {
        memberMapper.save(form);
    }

    public Member login(RequestLogin loginInfo) {
        Member member = memberMapper.findByEmail(loginInfo.getEmail());
        if (member == null) return null;
        if (loginInfo.getPassword().equals(memberMapper.findPasswordByEmail(loginInfo.getEmail()))) {
            return member;
        }
        return null;
    }
}

