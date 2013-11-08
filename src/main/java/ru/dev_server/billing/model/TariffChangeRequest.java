package ru.dev_server.billing.model;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import java.io.Serializable;

/**.*/
//@Entity
public class TariffChangeRequest implements Serializable {

//    @Id @GeneratedValue(generator="system-uuid")
    private String  id;
    private String msisdn;
    private String changeTariffPlanParameters;


    public TariffChangeRequest(String id, String msisdn, String changeTariffPlanParameters, WorkflowRequestStatus status) {
        this.id = id;
        this.msisdn = msisdn;
        this.changeTariffPlanParameters = changeTariffPlanParameters;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String aboId) {
        this.msisdn = aboId;
    }

//    public ChangeTariffPlanParameters getChangeTariffPlanParameters() {
//        return changeTariffPlanParameters;
//    }
//
//    public void setChangeTariffPlanParameters(ChangeTariffPlanParameters changeTariffPlanParameters) {
//        this.changeTariffPlanParameters = changeTariffPlanParameters;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
