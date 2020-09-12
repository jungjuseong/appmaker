package com.clbee.appmaker;

import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class MemberLister implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MemberService memberService;

    MemberLister(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public void run(ApplicationArguments args) {
        //memberRepo.findAll().forEach(member -> logger.info("{}", member.getUserId()));
        Member member = memberService.findByUserName("devicemanager");
        logger.info("{}", member);
    }
}