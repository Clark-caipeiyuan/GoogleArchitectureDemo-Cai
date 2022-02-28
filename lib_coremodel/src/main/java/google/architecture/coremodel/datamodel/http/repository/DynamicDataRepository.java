package google.architecture.coremodel.datamodel.http.repository;

import android.util.Log;

import google.architecture.coremodel.datamodel.http.ApiClient;
import google.architecture.coremodel.util.JsonUtil;
import google.architecture.coremodel.util.SwitchSchedulers;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * Created by clark on 2022/02/28.
 * 动态url数据获取
 */

public class DynamicDataRepository {

    public static <T>Observable getDynamicData(String pullUrl, final Class<T> clazz) {

        return
                ApiClient
                .getDynamicDataService()
                .getDynamicData(pullUrl)
                .compose(SwitchSchedulers.applySchedulers())
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {
                        Log.d("clark",JsonUtil.JsonBean2Str(responseBody));
                        return JsonUtil.Str2JsonBean(responseBody.string(), clazz);
                    }
                });
    }

}