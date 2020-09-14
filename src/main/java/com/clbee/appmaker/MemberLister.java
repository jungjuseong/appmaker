package com.clbee.appmaker;

import com.clbee.appmaker.model.Member;
import com.clbee.appmaker.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class MemberLister implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberService memberService;

    @Override
    public void run(ApplicationArguments args) {
        //memberRepo.findAll().forEach(member -> logger.info("{}", member.getUserId()));
        Member member = memberService.findByUserName("devicemanager");
        logger.info("{}", member);
    }
}