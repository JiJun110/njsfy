package com.gx.soft.njsfy_index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by adminstrator on 2019/9/23.
 */
@Controller
@RequestMapping("njsfy-index")
public class NjsfyWebController {
    @RequestMapping("home")
    public void home(String name1,String name2,String name3){

        System.out.println("123456");

    }
}
