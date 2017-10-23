package com.liu.ticket.controller;

/**
 * Created by Janda on 2017/10/24.
 */
@Controller
@RequestMapping("/person")
public class PersonController {


    @RequestMapping("/")
    @ResponBody
    public List<Person> queryAll(){

        return null;
    }
}
