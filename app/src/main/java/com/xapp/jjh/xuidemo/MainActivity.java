package com.xapp.jjh.xuidemo;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.xapp.jjh.xui.activity.TopBarActivity;
import com.xapp.jjh.xui.bean.BaseMenuItem;
import com.xapp.jjh.xui.fragment.IBaseFragment;
import com.xapp.jjh.xui.inter.DialogCallBack;
import com.xapp.jjh.xui.inter.MenuType;
import com.xapp.jjh.xui.inter.OnMenuItemClickListener;
import com.xapp.jjh.xui.lib.permissiongen.PermissionFail;
import com.xapp.jjh.xui.lib.permissiongen.PermissionGen;
import com.xapp.jjh.xui.lib.permissiongen.PermissionSuccess;
import com.xapp.jjh.xui.utils.ActivityStackManager;
import com.xapp.jjh.xuidemo.bean.Event;
import com.xapp.jjh.xuidemo.bean.TestMenuItem;
import com.xapp.jjh.xuidemo.fragment.CityFragment;
import com.xapp.jjh.xuidemo.fragment.CountyFragment;
import com.xapp.jjh.xuidemo.fragment.ProvinceFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TopBarActivity {

    private String TAG = "MainActivity";
    private RadioButton rbProvince;
    private RadioButton rbCity;
    private RadioButton rbCounty;

    private FragmentManager fragmentManager;
    private int mCurrentItem = -1;
    private ProvinceFragment provinceFragment;
    private CityFragment cityFragment;
    private CountyFragment countyFragment;

    private IBaseFragment[] fragments = new IBaseFragment[3];

    @Override
    public View getContentView(LayoutInflater layoutInflater, ViewGroup container) {
        return inflate(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        showDialog("Sure ?", new DialogCallBack() {
            @Override
            public void onRightClick(Dialog dialog) {
                ActivityStackManager.getInstance().AppExit();
            }
            @Override
            public void onLeftClick(Dialog dialog) {
                super.onLeftClick(dialog);
                showSnackBar("hello", null,null);
            }
        });
    }

    @Override
    public void onMenuClick() {
        super.onMenuClick();
        List<TestMenuItem> menuItems = new ArrayList<>();
        menuItems.add(new TestMenuItem(-1,"SecondActivity"));
        menuItems.add(new TestMenuItem(-1,"menu02"));
        showMenuList(menuItems, new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(BaseMenuItem menuItem, int position) {
                if(position == 0){
                    Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                    startActivity(intent);
                }else{
                    showSnackBar(menuItem.getItemText(),null,null);
                }
            }
        });
    }

    @Override
    public void findViewById() {
        rbProvince = findView(R.id.rb01);
        rbCity = findView(R.id.rb02);
        rbCounty = findView(R.id.rb03);
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        fragmentManager = getSupportFragmentManager();
        setSwipeBackEnable(false);
        setNavigationVisible(false);
        setTopBarTitle("XUI_DEMO");
        setMenuType(MenuType.ICON,R.mipmap.icon_menu);
        showFragment(0,-1);
        rbProvince.setOnClickListener(this);
        rbCity.setOnClickListener(this);
        rbCounty.setOnClickListener(this);
        PermissionGen.with(MainActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething(){

    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        showSnackBar("Permission Deny !",null,null);
    }

    @Override
    public void setListener() {
        super.setListener();
    }

    @Subscribe
    public void onEvent(Event event){
        if(event.getType() == Event.TYPE_LOCATION_CITY){
            showFragment(1,event.getId());
            rbCity.setChecked(true);
        }else if(event.getType() == Event.TYPE_LOCATION_COUNTY){
            showFragment(2,event.getId());
            rbCounty.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rb01:
                showFragment(0,-1);
                break;

            case R.id.rb02:
                showFragment(1,-1);
                break;

            case R.id.rb03:
                showFragment(2,-1);
                break;
        }
    }

    private void showFragment(int index, int id){
        mCurrentItem = index;
        hiddenAll(index);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (index){
            case 0:
                if(provinceFragment==null){
                    provinceFragment = new ProvinceFragment();
                    fragmentTransaction.add(R.id.container, provinceFragment);
                    fragments[0] = provinceFragment;
                }else{
                    fragmentTransaction.show(provinceFragment);
                }
                break;

            case 1:
                if(cityFragment==null){
                    cityFragment = new CityFragment();
                    fragmentTransaction.add(R.id.container, cityFragment);
                    fragments[1] = cityFragment;
                }else{
                    fragmentTransaction.show(cityFragment);
                }
                cityFragment.setId(id);
                break;

            case 2:
                if(countyFragment==null){
                    countyFragment = new CountyFragment();
                    fragmentTransaction.add(R.id.container, countyFragment);
                    fragments[2] = countyFragment;
                }else{
                    fragmentTransaction.show(countyFragment);
                }
                countyFragment.setId(id);
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void hiddenAll(int showIndex){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int len = fragments.length;
        for(int i=0;i<len;i++){
            Fragment f = fragments[i];
            if(i!=showIndex && f!=null){
                fragmentTransaction.hide(f);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
