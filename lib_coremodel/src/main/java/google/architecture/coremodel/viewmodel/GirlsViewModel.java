package google.architecture.coremodel.viewmodel;

import android.app.Application;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.databinding.ObservableField;
import androidx.annotation.NonNull;
import android.util.Log;


import com.apkfuns.logutils.LogUtils;

import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import google.architecture.coremodel.datamodel.http.repository.GankDataRepository;
import google.architecture.coremodel.util.NetUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 保存生命周期和UI所使用的数据
 * Created by Clark on 2022/02/28.
 */

public class GirlsViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    //生命周期观察的数据
    private LiveData<GirlsData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<GirlsData> uiObservableData = new ObservableField<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public GirlsViewModel(@NonNull Application application) {
        super(application);
        Log.i("clark", "GirlsViewModel------>");
        //这里的trigger为网络检测，也可以换成缓存数据是否存在检测
        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<GirlsData>>() {
            @Override
            public LiveData<GirlsData> apply(Boolean isNetConnected) {
                Log.i("clark", "apply------>"+isNetConnected);
                if (!isNetConnected) {
                    return ABSENT; //网络未连接返回空
                }
                MutableLiveData<GirlsData> applyData = new MutableLiveData<>();

                GankDataRepository.getFuliDataRepository("20", "1")
                .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<GirlsData>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                    mDisposable.add(d);
                            }

                            @Override
                            public void onNext(GirlsData value) {
                                Log.i("clark", "setValue------>");
                                applyData.setValue(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("clark", "onError------>");
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.i("clark", "onComplete------>");
                            }
                        });
                return applyData;
            }
        });
    }
    /**
     * LiveData支持了lifecycle生命周期检测
     * @return
     */
    public LiveData<GirlsData> getLiveObservableData() {
        return mLiveObservableData;
    }

    /**
     * 设置
     * @param product
     */
    public void setUiObservableData(GirlsData product) {
        this.uiObservableData.set(product);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
        LogUtils.d("=======GirlsViewModel--onCleared=========");
    }
}
