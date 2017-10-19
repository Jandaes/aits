package com.liu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jared
 */
@RestController
@RequestMapping("/api")
public class HelloController {
    @RequestMapping("/bb")
        public String a(){
            return "index";
        }
}
