package google.architecture.coremodel.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;

import google.architecture.coremodel.datamodel.http.entities.GirlsData;

/**
 * Created by Clark on 2022/02/28.
 * 动态的url
 */

public class DynamicGirlsViewModel extends BaseViewModel<GirlsData> {

    public DynamicGirlsViewModel(@NonNull Application application) {
        super(application);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
