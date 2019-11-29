package org.edb.main;

import org.edb.main.model.PluginModel;
import org.edb.main.network.JsonConverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class ServerResponseHandler {
    private UIManipulator uiManipulator;

    private ServerRequester serverRequester;

    public ServerResponseHandler(UIManipulator uiManipulator) {
        this.uiManipulator = uiManipulator;
    }

    public void setServerRequester(ServerRequester serverRequester) {
        this.serverRequester = serverRequester;
    }

    public void handleLoginResponse(boolean successful, String id, String token) {
        if(successful) {
            User.login(id,token);
            User user = User.getUser();

            serverRequester.requestUserExternalServices();
//            serverRequester.requestPluginConfigs();
            uiManipulator.onLoginSuccessful(id);
        }
        else uiManipulator.onLoginUnsuccessful();
    }

    public void handleAvailableExternalServiceResponse(ArrayList<ExternalService> data) {
        uiManipulator.onResponseAvailableExternalServices(data);
    }

    public void handleUserExternalServiceResponse(ArrayList<ExternalService> data) {
        ExternalServiceManager.getExternalServiceManager().loadExternalServices(data);

        Set<Integer> externalIdxSets = ExternalServiceManager.getExternalServiceManager().getExternalServices().keySet();
        Iterator<Integer> externalIdxItr = externalIdxSets.iterator();

        while(externalIdxItr.hasNext()){
            serverRequester.requestExternalServiceDetails(externalIdxItr.next());
        }
    }

    public void handleExternalServiceDetailsResponse(int externalIdx, ArrayList<ExternalServiceDetail> data) {

        ExternalServiceManager.getExternalServiceManager().loadExternalServiceDetail(externalIdx,data);
    }


    // load plugin user list
    public void handleUserPluginResponse(ArrayList<PluginModel> data){uiManipulator.onResponseUserPlugins(data);}
    // load Available plugin  list
    public void handleAvailablePluginResponse(ArrayList<PluginModel> data) { uiManipulator.onResponseAvailablePlugins(data);}

    public void handlePluginDetailsResponse(int pluginIdx, ArrayList<PluginModel> data) {
        uiManipulator.onResponsePluginDetails(pluginIdx, data);
    }

    //serverResponseHandler.handlePostUserPluginResponse(sucessful,jsonConverter);
    //post configuration
    public void handlePostUserPluginResponse(int pluginIdx, JsonConverter jsonConverter){

        uiManipulator.onPostUserPlugin(pluginIdx,jsonConverter);
    }

}