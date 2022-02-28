package google.architecture.common.ui;

import android.location.Location;
import android.location.LocationListener;

/**
 * Created by clark on 2022/02/28.
 */

public interface SimpleLocationListener extends LocationListener {

    void onLocationChanged(Location location);

}
