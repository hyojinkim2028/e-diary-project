package org.ediary.api.presentation;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController implements ErrorController {
//
//    @RequestMapping(path = {"/", "/main"})
//    public ModelAndView main() {
//        return new ModelAndView("main"); // -> templates/main.html
//    }


    @RequestMapping({"/", "/error"})
    public String index() {
        return "index.html";
    }

//    @RequestMapping("/order")
//    public ModelAndView order() {
//        return new ModelAndView("order/order"); // -> templates/order/order.html
//    }
}


