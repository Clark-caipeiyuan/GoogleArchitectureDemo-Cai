package google.architecture.common.service;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by clark on 2022/02/28.
 * 实现了一个测试用的服务接口
 */
@Route(path = "/service/test", name = "测试服务")
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello(String name) {
        Log.d("clark", "TestServiceImpl sayHello : "+name);
        return "TestServiceImpl TestServiceImpl TestServiceImpl";
    }

    @Override
    public void init(Context context) {
        Log.d("clark", "TestServiceImpl TestServiceImpl init");
    }
}
