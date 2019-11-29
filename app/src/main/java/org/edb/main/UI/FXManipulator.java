package org.edb.main.UI;

import javafx.application.Platform;
import org.edb.main.UIManipulator;

import org.edb.main.ExternalService;
import org.edb.main.ExternalServiceDetail;

import org.edb.main.model.PluginModel;
import org.edb.main.network.JsonConverter;

import java.util.ArrayList;

public class FXManipulator implements UIManipulator {
    private MainUIController mainUIController;
    private LoginDialogController loginDialogController;
    private AvailableExternalServiceListController availableExternalServicelListController;
    private UserExternalServiceListController userExternalServicelListController;

    @Override
    public void onLoginSuccessful(String id) {
        Platform.runLater(()->{
            mainUIController.closeLoginDialog();
            mainUIController.setUILoggedIn(id);
        });
    }

    @Override
    public void onLoginUnsuccessful() {
        Platform.runLater(()->{
//            loginDialogController에 대해 문제상황 알리기
        });
    }

    @Override
    public void onResponseAvailableExternalServices(ArrayList<ExternalService> data) {
        Platform.runLater(()->{
           availableExternalServicelListController.handleExternalServiceResponse(data);
        });
    }

    @Override
    public void onResponseUserExternalServices(Iterable<ExternalService> data) {
        Platform.runLater(()->{
            userExternalServicelListController.handleUserExternalServiceResponse(data);
        });
    }

    @Override
    public void onResponseExternalServiceDetails(int externalIdx, ArrayList<ExternalServiceDetail> data) {
        Platform.runLater(()->{
            userExternalServicelListController.showExternalServiceDetailTableList(externalIdx,data);
        });
    }

    @Override
    public void onResponseExternalServiceDetail(int externalIdx, Iterable<ExternalServiceDetail> externalServiceDetail) {
        Platform.runLater(()->{
            userExternalServicelListController.showExternalServiceDetailTableList(externalIdx,externalServiceDetail);
        });
    }

    public void onResponseUserPlugins(ArrayList<PluginModel> data){
        Platform.runLater(()->{
            System.out.print("test fxmaniipulator\n");
            System.out.print(data);
        });
    }

    @Override
    public void  onResponseAvailablePlugins(ArrayList<PluginModel> data){
        Platform.runLater(()->{
            System.out.print("test AVAILABLE PLUGIN LIST\n");
            System.out.print(data);
        });
    }

    @Override
    public void onResponsePluginDetails(int pluginIdx, ArrayList<PluginModel> data){
        Platform.runLater(()->{
            System.out.print("test AVAILABLE PLUGIN LIST\n");
            System.out.print(data);
        });
    }

    @Override
    public void onPostUserPlugin(int pluginIdx, JsonConverter jsonConverter){
        Platform.runLater(()->{
            System.out.print("test post plugin configuration\n");
            System.out.print(jsonConverter.getInactivateConditionList());
            System.out.print(jsonConverter.getObjectList());
            System.out.print(jsonConverter.getTime());
        });
    }
    public void setMainUIController(MainUIController mainUIController) {
        this.mainUIController = mainUIController;
    }

    public <T> void setLoginDialogController(LoginDialogController loginDialogController) {
        this.loginDialogController = loginDialogController;
    }

    public <T> void setAvailableExternalServicelListController(AvailableExternalServiceListController availableExternalServicelListController) {
        this.availableExternalServicelListController = availableExternalServicelListController;
    }

    public <T> void setUserExternalServicelListController(UserExternalServiceListController userExternalServicelListController) {
        this.userExternalServicelListController = userExternalServicelListController;
    }

}
