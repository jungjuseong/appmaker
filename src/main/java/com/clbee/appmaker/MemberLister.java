package com.clbee.appmaker;

import com.clbee.appmaker.repo.MemberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class MemberLister implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final MemberRepo memberRepo;

    MemberLister(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    @Override
    public void run(ApplicationArguments args) {
        memberRepo.findAll().forEach(member -> logger.info("{}", member.getUserId()));
        logger.info("{}", memberRepo.findByUserName("devicemanager"));
    }
}