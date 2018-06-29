package hnpbc.springboot.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@ResponseBody
public class test {
    @RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})
    public String authentication(HttpServletRequest request){
        System.out.println("==========");
        request.getSession().setAttribute("username","aa");
        return "hello world";
    }
}
