package org.edb.main;

public class ServerResponseHandler {
    private UIManipulator uiManipulator;

    public void setUiManipulator(UIManipulator uiManipulator) {
        this.uiManipulator = uiManipulator;
    }

    public void handleLoginResponse(String id, String token) {
        User.login(id,token);
        uiManipulator.onResponseLogin(id);
    }
}
