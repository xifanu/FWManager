package com.fwm.controller;

import com.fwm.pojo.ClientPojo;
import com.fwm.utils.FWMShell;
import com.fwm.utils.IPAddrUtil;
import com.fwm.utils.IPPattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * UFW防火墙管理
 */
@Controller
public class UFWController {

    @Autowired
    private HttpServletRequest request;

    private static final String SS = "添加成功！";
    private static final String FF = "失败！";
    private static final String RR = "重复添加！";

    @GetMapping("")
    public String ufwAddIP(Model model) throws Exception {
        ClientPojo build = ClientPojo.builder().ip(IPAddrUtil.getIpAddress(request)).build();
        model.addAttribute("client", build);
        return "index";
    }

    @PostMapping("")
    public synchronized String ufwAddIP(Model model,
                             @RequestParam(value = "ip",defaultValue = "1.1.1.1") String ip) {
        ClientPojo client = ClientPojo.builder().build();
        try{
            if(IPPattern.isboolIp(ip)){
                client.setIp(ip);
                //执行防火墙命令
                String shellLog = FWMShell.run("ufw allow from " + ip);
                Thread.sleep(200);//确保shell执行的稳定性
                FWMShell.run("ufw reload");
                client.setMsg(SS);
                //执行命令完毕
                if (!StringUtils.isEmpty(shellLog)){
                    //判断执行结果，赋值msg
                    int added = shellLog.indexOf("added");
                    int existing = shellLog.indexOf("existing");
                    int bad = shellLog.indexOf("Bad");
                    if(added > 0){
                        client.setMsg(SS);
                    }else if(existing > 0){
                        client.setMsg(ip+RR);
                    }else if(bad > 0){
                        client.setMsg(FF + shellLog);
                    }
                }

                model.addAttribute("client", client);
            }else{
                client.setMsg(" IP 格式不正确！");
                model.addAttribute("client", client);
            }

            return "index";
        }catch (Exception e){
            client.setMsg(e.getMessage());
            model.addAttribute("client", client);
            return "index";
        }


    }

    @GetMapping("ping")
    public String ping(Model model) throws Exception {
        ClientPojo client = ClientPojo.builder().build();
        String shellLog = FWMShell.run("ping -c 1 8.8.8.8");
        client.setIp("8.8.8.8");
        client.setMsg(shellLog);
        model.addAttribute("client", client);
        return "index";
    }

}
