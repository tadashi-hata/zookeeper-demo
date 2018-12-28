package com.tadashi.hata.propostasdemo.web;

import com.tadashi.hata.propostasdemo.config.service.ZKManager;
import com.tadashi.hata.propostasdemo.config.service.imp.ZKManagerImpl;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropostasController {

    @Value("${mongo}")
    private String mongoHost;

    @Value("${service1}")
    private String service1;

    @Value("${service2}")
    private String service2;

    @Value("${service3}")
    private String service3;

    @Value("${service4}")
    private String service4;

//    private ZKManager zkManager;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/configurations", method = RequestMethod.GET)
    public String getConfigs() throws Exception {
        ZKManager zkManager  = new ZKManagerImpl();

        return "Hello darkness my old friend:" +
                "<br> mongo: " +mongoHost +
                "<br> service1: " + service1 +
                "<br> service2: " + service2 +
                "<br> service3: " + service3 +
                "<br> service4: " + service4 +
                "<br> env: " + zkManager.getZNodeData("/config/zookeeper-demo/mongo", false);
    }

    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public ResponseEntity<String> setupConfig() throws Exception {

        ZKManager zkManager = new ZKManagerImpl();
        try {
            zkManager.create("/config/zookeeper-demo", new String("localhost:8080").getBytes());
            zkManager.create("/config/zookeeper-demo/mongo", new String("192.168.99.100:8082").getBytes());
            zkManager.create("/config/zookeeper-demo/service1", new String("192.168.99.100:8091").getBytes());
            zkManager.create("/config/zookeeper-demo/service2", new String("192.168.99.100:8091").getBytes());
            zkManager.create("/config/zookeeper-demo/service3", new String("192.168.99.100:8093").getBytes());
            zkManager.create("/config/zookeeper-demo/service4", new String("192.168.99.100:8094").getBytes());
        }
        catch (Exception e){
            zkManager.update("/config/zookeeper-demo/mongo", new String("192.168.99.100:8082").getBytes());
            zkManager.update("/config/zookeeper-demo/service1", new String("192.168.99.100:8091").getBytes());
            zkManager.update("/config/zookeeper-demo/service2", new String("192.168.99.100:8091").getBytes());
            zkManager.update("/config/zookeeper-demo/service3", new String("192.168.99.100:8093").getBytes());
            zkManager.update("/config/zookeeper-demo/service4", new String("192.168.99.100:8094").getBytes());
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
