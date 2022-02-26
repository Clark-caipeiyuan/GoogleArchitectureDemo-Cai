package google.architecture.girls;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;

import google.architecture.common.base.ARouterPath;
import google.architecture.common.base.BaseActivity;
import google.architecture.common.util.Utils;
import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import google.architecture.coremodel.viewmodel.GirlsViewModel;
import google.architecture.coremodel.viewmodel.ViewModelProviders;
import google.architecture.girls.databinding.ActivityGirlsBinding;

@Route(path = ARouterPath.GirlsListAty)
public class ActivityGirls extends BaseActivity {

    GirlsAdapter            girlsAdapter;
    ActivityGirlsBinding    activityGirlsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Module_ActivityGirls");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityGirlsBinding = DataBindingUtil.setContentView(ActivityGirls.this,R.layout.activity_girls);
//        GirlsViewModel girlsViewModel = new GirlsViewModel(ActivityGirls.this.getApplication());
        GirlsViewModel girlsViewModel =
        ViewModelProviders.of(ActivityGirls.this).get(GirlsViewModel.class);
        girlsAdapter = new GirlsAdapter(girlItemClickCallback);
        activityGirlsBinding.setRecyclerAdapter(girlsAdapter);
        subscribeToModel(girlsViewModel);

    }

    GirlItemClickCallback   girlItemClickCallback = new GirlItemClickCallback() {
        @Override
        public void onClick(GirlsData.ResultsBean fuliItem) {
            Toast.makeText(ActivityGirls.this, fuliItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 订阅数据变化来刷新UI
     * @param model
     */
    private void subscribeToModel(final GirlsViewModel model){
        //观察数据变化来刷新UI
        model.getLiveObservableData().observe(this, new Observer<GirlsData>() {
            @Override
            public void onChanged(@Nullable GirlsData girlsData) {
                Log.i("danxx", "subscribeToModel onChanged onChanged");
                model.setUiObservableData(girlsData);
                girlsAdapter.setGirlsList(girlsData.getResults());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
