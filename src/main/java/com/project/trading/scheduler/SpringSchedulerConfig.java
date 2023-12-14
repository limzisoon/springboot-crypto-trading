package com.project.trading.scheduler;

import com.project.trading.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@Configuration
@EnableScheduling
public class SpringSchedulerConfig {

    @Autowired
    CryptoService cryptoService;

    /*
    * interval 10 seconds to refresh the aggregated price from source
     */
    @Scheduled(cron= "0/10 * * ? * *")
    public void retrieveCryptosFromSource() throws Exception
    {
        cryptoService.retrieveCryptosFromSource();
    }
}
