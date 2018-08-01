package hnpbc;

import hnpbc.bean.Route;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class DemoApplication{

    @RequestMapping("/")
    public List<Route> index(){
//        return "hello world";
        Route route1 = new Route();
        route1.setPath("/access");
        route1.setName("access");
        Route route2 = new Route();
        route2.setPath("/sys-mgmt");
        route2.setName("sys-mgmt");
        Route route3 = new Route();
        route3.setPath("/sys-mgmt/test");
        route3.setName("test");
        Route route4 = new Route();
        route4.setPath("/sys-mgmt/user-mgmt");
        route4.setName("user-mgmt");
        Route route5 = new Route();
        route5.setPath("/sys-mgmt/dept-mgmt");
        route5.setName("dept-mgmt");
        Route route6 = new Route();
        route6.setPath("/sys-mgmt/role-mgmt");
        route6.setName("role-mgmt");
        Route route7 = new Route();
        route7.setPath("/sys-mgmt/router-mgmt");
        route7.setName("router-mgmt");

        List<Route> list = new ArrayList<Route>();
        list.add(route1);
        list.add(route2);
        list.add(route3);
        list.add(route4);
        list.add(route5);
        list.add(route6);
        list.add(route7);
        return list;
    }

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
        System.out.println("uuid = " + UUID.randomUUID().toString());
    }
}
