/*
 * ��� �����, ���������� ���������� ������� � Foris 
 * ����� ��������� ��������� ������.
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
// ������� ������ ������ �Service� - ���� ��� ����� ������
//    private static final MonitoringNotifier ntf = MonitoringNotifier.create("CustomerDataWSClient");

    public String reportForisResultViaCallback(String msisdn){
         // ������� ��������� ������ �Method� (��� �������� ����)
//        SimpleMethodEventev = SimpleMethodEvent.startMethod(ntf, "reportForisResultViaCallback");
        
// ����������� ������� �������� id � ������ ������������        
log.info(msisdn + " received OK, now responding with ForisResult...");
        // ����� ������� ��������. ��������� ������� KPI_RETURN=result
//        ev.completed(result);
       return msisdn + " ForisResult; ";
    }
    
    
}
