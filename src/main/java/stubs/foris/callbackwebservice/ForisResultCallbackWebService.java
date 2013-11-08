/*
 * Это класс, получающий результаты запроса к Foris 
 * через вебсервис обратного вызова.
 */

package stubs.foris.callbackwebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;
import ru.dev_server.billing.workflow.V2WorkflowForis;

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

    public void reportForisResultViaCallback(WorkflowRequest workflowRequest){
         // Создаем сообщение уровня «Method» (имя задается явно)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "reportForisResultViaCallback");
        String msisdn = workflowRequest.getMsisdn();
// запрашиваем балансы согласно id и данным конфигурации        
log.info(msisdn + " received OK, now responding with ForisResult...");
        V2WorkflowForis workflowForis = new V2WorkflowForis();
        workflowForis.startWorkflow(workflowRequest);
        // метод успешно выполнен. Добавляем метрику KPI_RETURN=result
//        ev.completed(result);
       return;
    }
    
    
}
