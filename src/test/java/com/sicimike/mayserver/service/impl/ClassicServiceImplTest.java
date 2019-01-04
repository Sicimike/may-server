package com.sicimike.mayserver.service.impl;

import com.sicimike.mayserver.MayServerApplicationTests;
import com.sicimike.mayserver.dto.ClassicDTO;
import com.sicimike.mayserver.entity.Classic;
import com.sicimike.mayserver.service.ClassicService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
public class ClassicServiceImplTest extends MayServerApplicationTests {

    @Autowired
    private ClassicService classicService;

    @Test
    public void findLatestOne() throws Exception {
        Classic classic = classicService.findLatestOne();
        Assert.notNull(classic, "fail");
        log.info(classic.toString());
    }

    @Test
    public void findPreOne() throws Exception {
        Classic classic = classicService.findPreOne(7);
        Assert.notNull(classic, "fail");
        log.info(classic.toString());
    }

    @Test
    public void findNextOne() throws Exception {
        Classic classic = classicService.findNextOne(7);
        Assert.notNull(classic, "fail");
        log.info(classic.toString());
    }

    @Test
    public void findFavorInfo() throws Exception {
        ClassicDTO classicDTO = classicService.findFavorInfo(6L, 200);
        Assert.notNull(classicDTO, "fail");
        log.info(classicDTO.toString());
    }

    @Test
    public void favorCount() throws Exception {
        Long count = classicService.favorCount("oxjF45M8SrrSx1q6eQkwze_vYkkc", 1);
        Assert.notNull(count, "fail");
        log.info("count is {}", count);
    }

    @Test
    public void classicFavor() throws Exception {
        List<Classic> classicList = classicService.classicFavor("oxjF45M8SrrSx1q6eQkwze_vYkkc", 1);
        Assert.notEmpty(classicList, "fail");
        log.info(classicList.toString());

    }


}