package ru.dev_server.billing.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**.*/
@Entity
public class TariffChangeRequest {

    @Id @GeneratedValue(generator="system-uuid")
    private String  id;
    private String msisdn;
//    private ChangeTariffPlanParameters changeTariffPlanParameters;
    private WorkflowRequestStatus status;

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

    public WorkflowRequestStatus getStatus() {
        return status;
    }

    public void setStatus(WorkflowRequestStatus status) {
        this.status = status;
    }
}
