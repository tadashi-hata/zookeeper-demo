package com.tadashi.hata.propostasdemo.config.service;

import com.tadashi.hata.propostasdemo.config.service.imp.ZKManagerImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;


@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ZKManagerTest {

    private ZKManagerImpl zkManager;

    @Value("${mongo}")
    private String mongo;

    @Test
//    @Ignore
    public void testCreateConfig () throws Exception {
        zkManager = new ZKManagerImpl();
        String value = new String("192.168.99.100:27017");
        String retorno;
        zkManager.create("/config/mongo/host", value.getBytes());
//        zkManager.create("/config/application", null);
//        zkManager.create("/config/application/mongo", new String("192.168.99.100:8082").getBytes());
//        zkManager.create("/config/application/service1", new String("192.168.99.100:8091").getBytes());
//        zkManager.create("/config/application/service2", new String("192.168.99.100:8091").getBytes());
//        zkManager.create("/config/application/service3", new String("192.168.99.100:8093").getBytes());
//        zkManager.create("/config/application/service4", new String("192.168.99.100:8094").getBytes());

        retorno = (String) zkManager.getZNodeData("/config/application/mongo", true);

        assertEquals(mongo, retorno);
    }
}
