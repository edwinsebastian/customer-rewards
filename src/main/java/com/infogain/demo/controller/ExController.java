package com.infogain.demo.controller;

import com.infogain.demo.exception.BadArgumentsException;
import com.infogain.demo.exception.InternalException;
import com.infogain.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/exception")
public class ExController {

    @GetMapping("/{exception_id}")
    public void getSpecificException(@PathVariable("exception_id") String pException) {
        if("not_found".equals(pException)) {
            throw new ResourceNotFoundException("resource not found");
        }
        else if("bad_arguments".equals(pException)) {
            throw new BadArgumentsException("bad arguments");
        }
        else {
            throw new InternalException("internal error");
        }
    }
}
