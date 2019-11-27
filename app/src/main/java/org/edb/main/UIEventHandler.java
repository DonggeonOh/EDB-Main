package org.edb.main;

import java.util.ArrayList;
import java.util.Map;

public class UIEventHandler {
    private ServerRequester serverRequester;
    private UIManipulator uiManipulator;

    public void setUiManipulator(UIManipulator uiManipulator) {
        this.uiManipulator = uiManipulator;
    }

    public UIEventHandler(ServerRequester serverRequester) {
        this.serverRequester = serverRequester;
    }

    public void onClickLoginBtn(String id, String pw) {
        serverRequester.requestLogin(id,pw);
    }

    public void onAvailableExternalServiceListLoaded(){
        serverRequester.requestAvailableExternalServices();
    }

    public void onUserExternalServicesRequested(){
        Map<Integer,ExternalService> data= ExternalServiceManager.getExternalServiceManager().getExternalServices();
        uiManipulator.onResponseUserExternalServices(new ArrayList<ExternalService>(data.values()));

    }

    public void onExternalServiceDetailRequested(int externalIdx) {
        Iterable<ExternalServiceDetail> externalServiceDetail = ExternalServiceManager.getExternalServiceManager().getExternalServiceDetail(externalIdx);

        uiManipulator.onResponseExternalServiceDetail(externalIdx, externalServiceDetail);
    }

    public void onExternalServiceDetailRefreshRequested(int externalIdx){

    }

}
