/*
 * Это класс, получающий результаты запроса к Foris 
 * через вебсервис обратного вызова.
 */

package stubs.foris.callbackwebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;

/**
 *
 * @author Administrator
 */
public class ForisResultCallbackWebService {
       private static Logger log = LoggerFactory.getLogger(ForisResultCallbackWebService.class);
// создаем объект уровня «Service» - один для всего класса
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("CustomerDataWSClient");

    public String reportForisResultViaCallback(String msisdn){
         // Создаем сообщение уровня «Method» (имя задается явно)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "reportForisResultViaCallback");
        
// запрашиваем балансы согласно id и данным конфигурации        
log.info(msisdn + " received OK, now responding with ForisResult...");
        // метод успешно выполнен. Добавляем метрику KPI_RETURN=result
//        ev.completed(result);
       return msisdn + " ForisResult; ";
    }
    
    
}
