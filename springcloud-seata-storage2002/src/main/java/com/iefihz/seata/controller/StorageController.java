package com.iefihz.seata.controller;

import com.iefihz.seata.service.StorageService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author He Zhifei
 * @date 2020/8/19 12:48
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @PatchMapping("/decrease/{providerId}")
    public void decrease(@PathVariable("providerId") Long providerId) {
        storageService.decrease(providerId);
        int i = 1/0;
        System.out.println("storageService.decrease() === success");
    }
}
