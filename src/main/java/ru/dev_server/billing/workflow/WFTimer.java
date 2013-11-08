package ru.dev_server.billing.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dev_server.billing.WorkflowRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ejb.Stateless;
import javax.ejb.Schedule;
import javax.ejb.Timer;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;


@Stateless
public class WFTimer {
    private static Logger log = LoggerFactory.getLogger(WFTimer.class);

    //    @PersistenceContext EntityManager em;
    RAMStorage em;

    @Schedule(second = "*/600", minute = "*", hour = "*", info = "Automatic Timer To spin up hanged processing.")
    public void timerTick(Timer t) {
        // Создаем сообщение уровня «Method» (имя задается явно)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "timerTick");

        log.info("Now be less hanged requests...");

        //Java calendar in default timezone and default locale
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+3"));

//        log.info("current date: " + getTime(cal));

        //adding -8 hours
        cal.add(Calendar.HOUR, -8);
//        log.info("date before 8 hours : " + getTime(cal));
//        ...... more processing

        // метод успешно выполнен. Добавляем метрику KPI_RETURN=result
//        ev.completed(result);

    }

    /**
     * @return current Date from Calendar in HH:mm:SS format
     *         adding 1 into month because Calendar month starts from zero
     */
    public static String getTime(Calendar cal) {
        return "" +
                cal.get(Calendar.HOUR_OF_DAY) + ":" +
                cal.get(Calendar.MINUTE) + ":" +
                cal.get(Calendar.SECOND);
    }

/*    public WorkflowRequest getNextDatedRequest() {
//        TO_DO select from storage dated - 8 or 24 hour requests
        return new WorkflowRequest();
    }*/
}
