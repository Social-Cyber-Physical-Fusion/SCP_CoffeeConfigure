package edu.fudan.selab.controller;

import edu.fudan.selab.entity.Coffee;
import edu.fudan.selab.ontology.HttpRequestor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static edu.fudan.selab.globle.Constants.*;

@Controller
public class CoffeeController {

    //public static String url_back = "";
    //public static String userId = "";

    //http://123.207.47.155:8003/notifyAsyncTaskIsFinished?processInstanceId=1&serviceDescription=hh&level=1&mode=2&num=3


    @GetMapping("/coffee")
    public String coffeeForm(Model model) {
        model.addAttribute("coffee", new Coffee());
        return "coffee";
    }

    @PostMapping("/old_coffee")
    public String coffeeSubmit(@ModelAttribute Coffee coffee) throws Exception {
        Map<String,String> map = new HashMap<String,String>();
        map.put("level",coffee.getLevel());
        map.put("mode",coffee.getMode());
        map.put("num",coffee.getNum());
        url_back = url_back + "&level="+coffee.getLevel()+"&mode="+coffee.getMode()+"&num="+coffee.getNum();
        //String backUrl = broker_url + "/notifyAsyncTaskIsFinished";
        new Thread() {
            public void run() {
                try {
                    String state = new HttpRequestor().doGet(url_back);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //String res1 = new HttpRequestor().doPost(notifyApp,map1);
            }
        }.start();
        //String state = new HttpRequestor().doGet(url_back);
        return "result";
    }

//        "url": "http://ordercoffee-service-proxy.default:8888/receive_result",
//            "method": "POST",
//            "headers": {
//                "Content-Type": "application/x-www-form-urlencoded"
//        },
//        "data": {
//            "workflow_instance_id": "workflow_1",
//            "result": "{\"state\": \"Completed\", \"data\": \"111\"}"
//        }
    @PostMapping("/coffee")
    public String newcoffeeSubmit(@ModelAttribute Coffee coffee) throws Exception {

        String data = "{\\\"action\\\": \\\"start\\\", \\\"mode\\\": \\\""+coffee.getMode()+"\\\", \\\"level\\\": \\\""+coffee.getLevel()+"\\\", \\\"num\\\": \\\""+coffee.getNum()+"\\\"}";
        String result = "{\"state\": \"Completed\", \"data\": \""+data+"\"}";

        Map<String,String> map = new HashMap<String,String>();
        map.put("workflow_instance_id", workflow_instance_id);
        map.put("result", result);

        new Thread() {
            public void run() {
                try {
                    String state = new HttpRequestor().doPost(Proxy_url, map);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return "result";
    }
}
