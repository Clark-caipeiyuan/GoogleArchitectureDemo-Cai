package google.architecture.coremodel.viewmodel;

import android.app.Application;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.apkfuns.logutils.LogUtils;

import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import google.architecture.coremodel.datamodel.http.entities.NewsData;
import google.architecture.coremodel.datamodel.http.repository.GankDataRepository;
import google.architecture.coremodel.util.NetUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dxx on 2017/11/17.
 */

public class NewsViewModel extends AndroidViewModel {

    private static final MutableLiveData ABSENT = new MutableLiveData();
    {
        //noinspection unchecked
        ABSENT.setValue(null);
    }

    //生命周期观察的数据
    private LiveData<NewsData> mLiveObservableData;
    //UI使用可观察的数据 ObservableField是一个包装类
    public ObservableField<NewsData> uiObservableData = new ObservableField<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public NewsViewModel(@NonNull Application application) {
        super(application);
        Log.i("danxx", "GirlsViewModel------>");
        //这里的trigger为网络检测，也可以换成缓存数据是否存在检测
        mLiveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new androidx.arch.core.util.Function<Boolean, LiveData<NewsData>>() {
            @Override
            public LiveData<NewsData> apply(Boolean isNetConnected) {

                Log.i("danxx", "apply------>");
                if (!isNetConnected) {
                    return ABSENT; //网络未连接返回空
                }
                MutableLiveData<NewsData> applyData = new MutableLiveData<>();

                GankDataRepository.getNewsListDataRepository("20", "1","20220224")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<NewsData>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                mDisposable.add(d);
                            }

                            @Override
                            public void onNext(NewsData value) {
                                applyData.setValue(value);
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onComplete() {
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
    public LiveData<NewsData> getLiveObservableData() {
        return mLiveObservableData;
    }

    /**
     * 设置
     * @param product
     */
    public void setUiObservableData(NewsData product) {
        this.uiObservableData.set(product);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        LogUtils.d("========NewsViewModel--onCleared=========");
        mDisposable.clear();
    }
}
