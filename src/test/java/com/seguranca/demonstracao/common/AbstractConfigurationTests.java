package com.seguranca.demonstracao.common;

import com.seguranca.demonstracao.DemonstracaoApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = DemonstracaoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class AbstractConfigurationTests implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private boolean beanInitialized = false;

    @Ignore
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Ignore
    @Override
    public void afterPropertiesSet() throws Exception {
        this.beanInitialized = true;
    }

    @Test
    public void teste(){
        assertEquals(1, 1);
    }
}
