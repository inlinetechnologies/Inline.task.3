/*
 * Заглушка вебсервиса получения конфигурационных данных от CustomerData3.
 * При наличии данных об реальном интерфейсе CustomerData3 может быть
 * добавлена реальная обработка и получение данных, 
 * формирование правильных данных, кодов ошибок и при необходимости их описания
 */

package stubs.customerdata.wsclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;

//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;

/**
 *
 * @author Administrator
 */
public class CustomerDataWSClient {
       private static Logger log = LoggerFactory.getLogger(CustomerDataWSClient.class);
// создаем объект уровня «Service» - один для всего класса
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("CustomerDataWSClient");

    public WorkflowRequest getForisConfigurationData(WorkflowRequest wr){
         // Создаем сообщение уровня «Method» (имя задается явно)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "getForisConfigurationData");
        String msisdn = wr.getMsisdn();
        String debugString = wr.getDebugString();
        wr.setDebugString(debugString + " ForisConfigurationData; ");
// запрашиваем балансы согласно id и данным конфигурации        
log.info(msisdn + " received OK, now responding with ForisConfigurationData...");
        // метод успешно выполнен. Добавляем метрику KPI_RETURN=result
//        ev.completed(result);
       return wr;
    }
    
}
