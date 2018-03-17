package cn.edu.neusoft.lixu524.viewpager_fragment.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.neusoft.lixu524.viewpager_fragment.R;

/**
 * Created by www44 on 2018/3/17.
 */

public class Fragment1 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment1,null);
        return view;
    }
}
