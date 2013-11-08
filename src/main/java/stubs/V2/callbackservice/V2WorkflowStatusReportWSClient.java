/*
 * Заглушка клиента вебсервиса в V2, отправляющего информацию о статусе запроса.
 * При наличии данных об реальном интерфейсе V2 может быть
 * добавлена реальная обработка и получение данных, 
 * формирование правильных данных, кодов ошибок и при необходимости их описания
 */

package stubs.V2.callbackservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;

//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;

/**
 *
 * @author Administrator
 */
public class V2WorkflowStatusReportWSClient {
       private static Logger log = LoggerFactory.getLogger(V2WorkflowStatusReportWSClient.class);
// создаем объект уровня «Service» - один для всего класса
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("V2WorkflowStatusReportWSClient");

    public WorkflowRequest reportWorkflowStatus(WorkflowRequest wr){
         // Создаем сообщение уровня «Method» (имя задается явно)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "reportWorkflowStatus");
        String msisdn = wr.getMsisdn();
        String debugString = wr.getDebugString();
        wr.setDebugString(debugString + " reportWorkflowStatus reported; " + wr.getStatus());
// отправляем информацию о соcтоянии запроса в V2       
log.info(msisdn + " " + wr.getStatus() + " received OK, now reported Foris response fulfilled...");
        // метод успешно выполнен. Добавляем метрику KPI_RETURN=result
//        ev.completed(result);
       return wr;
    }
}