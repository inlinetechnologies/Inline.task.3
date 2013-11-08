package ru.dev_server.billing.workflow;

import ru.dev_server.billing.WorkflowRequest;
import ru.dev_server.billing.model.TariffChangeRequest;
import ru.dev_server.billing.model.WorkflowRequestStatus;

import java.util.LinkedHashMap;

public class RAMStorage {
    LinkedHashMap<String, WorkflowRequest> map = new LinkedHashMap<String, WorkflowRequest>();

    public WorkflowRequest findByAboId(String key) {
        WorkflowRequest request = map.get(key);
        return request;
    }

    public WorkflowRequest merge(WorkflowRequest wr) {
        String id = wr.getStorageId();
        map.remove(id);
        return map.put(id, wr);
    }

    public boolean persist(WorkflowRequest request) {
        request.setStorageId(String.valueOf(map.size()));
        return request.equals(map.put(request.getStorageId(), request));
    }
    private WorkflowRequest findByStorageId(String s){
        return map.get(s);
    }
    public WorkflowRequest findByStorageId(WorkflowRequest request){
        System.out.println("Getting from storage request by storage id: " + request.getStorageId());
        return map.get(request.getStorageId());
    }

    public static void main(String[] args) {
        RAMStorage storage = new RAMStorage();
        System.out.println(storage.map.size());
        TariffChangeRequest tariffChangeRequest = new TariffChangeRequest("0", "1234", "Parameters", WorkflowRequestStatus.CREATED);
        WorkflowRequest workflowRequest = new WorkflowRequest(tariffChangeRequest);
        System.out.println("The status of new request is : " + workflowRequest.getStatus());
        System.out.println("The persist result is: " + storage.persist(workflowRequest));
        System.out.println("Strorage size is : " + storage.map.size());

        workflowRequest.setStatus(WorkflowRequestStatus.CONFIGURED);
        storage.merge(workflowRequest);
        WorkflowRequest changedRequest = storage.findByStorageId(workflowRequest);
        System.out.println("The status of persisted request is : " + changedRequest.getStatus());

        TariffChangeRequest secondTChRequest = new TariffChangeRequest("0", "1234", "Parameters", WorkflowRequestStatus.CREATED);
        WorkflowRequest secondWFRequest = new WorkflowRequest(secondTChRequest);
        storage.persist(secondWFRequest);
        WorkflowRequest secondWFRequestFromStorage = storage.findByStorageId(secondWFRequest);
        secondWFRequestFromStorage.setStatus(WorkflowRequestStatus.COMPLETED);
        storage.merge(secondWFRequestFromStorage);
        System.out.println("The status of second persisted request is : " + storage.findByStorageId(secondWFRequest).getStatus());


    }
}
