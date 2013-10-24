/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mts.tender.inline.asynch;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
//import ru.inmetrix.imx.japi.*;
//import ru.inmetrix.imx.japi.eventtypes.*;

/**
 *
 * @author Administrator
 */
@WebService(serviceName = "ChangeTPService")
@Stateless()
public class ChangeTPService {
// создаем объект уровня «Service» - один для всего класса
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("V1FasadeForisILBulkServiceOperationsWS");

    /**
     * Web service operation
     */
    @WebMethod(operationName = "changeTariff")
    public String changeTariff(@WebParam(name = "msisdn") String msisdn) {
        // Создаем сообщение уровня «Method» (имя задается явно)
//        SimpleMethodEvent ev = SimpleMethodEvent.startMethod(ntf, "getBalances");

        // методуспешновыполнен. Добавляем метрику KPI_RETURN=result
//        ev.completed(result);
        //предполагается, что здесь исключений возникнуть не должно,
        // в противном получимKPI_RESULT=UNKNOWN
        return msisdn + " - at new tariff";
    }
}
