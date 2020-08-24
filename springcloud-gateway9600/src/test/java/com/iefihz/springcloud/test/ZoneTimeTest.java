package com.iefihz.springcloud.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

/**
 * @author He Zhifei
 * @date 2020/7/14 2:35
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ZoneTimeTest {

    @Test
    public void testZoneTime() {
        ZonedDateTime time = ZonedDateTime.now();
        System.out.println(time);
    }
}
