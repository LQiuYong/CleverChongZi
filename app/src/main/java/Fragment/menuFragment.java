package Fragment;

import android.view.View;

import com.example.chongzi007.cleverchongzi.R;

/**
 * Created by chongzi007 on 2016/8/10.
 */
public class menuFragment extends BaseFragment {
    @Override
    public View initViews() {
        View view = View.inflate(mactivity, R.layout.fragment_left_menu, null);
        return view;
    }
}
