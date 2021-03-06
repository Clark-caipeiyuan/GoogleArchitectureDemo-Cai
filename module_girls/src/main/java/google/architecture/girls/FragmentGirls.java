package google.architecture.girls;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import google.architecture.common.base.ARouterPath;
import google.architecture.common.base.BaseFragment;
import google.architecture.coremodel.datamodel.http.entities.GirlsData;
import google.architecture.coremodel.viewmodel.GirlsViewModel;
import google.architecture.coremodel.viewmodel.ViewModelProviders;
import google.architecture.girls.databinding.FragmentGirlsBinding;


/**
 * @Desc Created by Clark on 2022/02/28.
 */
@Route(path = ARouterPath.GirlsListFgt)
public class FragmentGirls extends BaseFragment {

    FragmentGirlsBinding girlsBinding;

    GirlsAdapter            girlsAdapter;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ARouter.getInstance().inject(FragmentGirls.this);
        girlsBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_girls,container,false);

        girlsAdapter = new GirlsAdapter(girlItemClickCallback);
        girlsBinding.setRecyclerAdapter(girlsAdapter);
//        final GirlsViewModel girlsViewModel = new GirlsViewModel(getActivity().getApplication());
        final GirlsViewModel girlsViewModel =
                ViewModelProviders.of(FragmentGirls.this).get(GirlsViewModel.class);

        subscribeToModel(girlsViewModel);

        return girlsBinding.getRoot();
    }


    GirlItemClickCallback   girlItemClickCallback = new GirlItemClickCallback() {
        @Override
        public void onClick(GirlsData.ResultsBean fuliItem) {
            Toast.makeText(getContext(), fuliItem.getDesc(), Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * ???????????????????????????UI
     * @param model
     */
    private void subscribeToModel(final GirlsViewModel model){
        //???????????????????????????UI
        model.getLiveObservableData().observe(getViewLifecycleOwner(), new Observer<GirlsData>() {
            @Override
            public void onChanged(@Nullable GirlsData girlsData) {
                Log.i("clark", "subscribeToModel onChanged onChanged");
                model.setUiObservableData(girlsData);
                girlsAdapter.setGirlsList(girlsData.getResults());
            }
        });
    }

}
