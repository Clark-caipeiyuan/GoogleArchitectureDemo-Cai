package google.architecture.common.ui;


import androidx.databinding.ObservableField;

/**
 * Created by clark on 2022/02/28.
 * 简写版支持双向绑定的数据
 */

public class UserDataOF {

    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> userId = new ObservableField<>();

}
