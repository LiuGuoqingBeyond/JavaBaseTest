package com.uppayplugin.unionpay.javabasetes.bottomnavigationview.fragment;

import android.support.v4.app.Fragment;

import com.uppayplugin.unionpay.javabasetes.R;

/**
 * User: LiuGuoqing
 * Data: 2018/8/17 0017.
 */

public class HomeTabManager {

    private HomePageFragment homePageFragment;
    private KnowledgeFragment knowledgeFragment;
    private ProjectFragment projectFragment;
    private GankFragment gankFragment;
    private PersonalFragment personalFragment;

    private Fragment fragment;
    private static HomeTabManager instance;

    public static HomeTabManager getInstance() {
        if (instance == null) {
            synchronized (HomeTabManager.class) {
                if(instance==null){
                    instance = new HomeTabManager();
                }
            }
        }
        return  instance;
    }

    public Fragment getFragmentByIndex(int index){
        switch (index){
            case R.id.tab_main:
                if (homePageFragment == null){
                    homePageFragment = new HomePageFragment();
                }
                fragment = homePageFragment;
                break;
            case R.id.tab_knowledge:
                if (knowledgeFragment == null){
                    knowledgeFragment = new KnowledgeFragment();
                }
                fragment = knowledgeFragment;
                break;
            case R.id.tab_project:
                if (projectFragment == null){
                    projectFragment = new ProjectFragment();
                }
                fragment = projectFragment;
                break;
            case R.id.tab_gank:
                if (gankFragment == null){
                    gankFragment = new GankFragment();
                }
                fragment = gankFragment;
                break;
            case R.id.tab_mine:
                if (personalFragment == null){
                    personalFragment = new PersonalFragment();
                }
                fragment = personalFragment;
                break;
        }
        return fragment;
    }
    public void destory(){
        homePageFragment = null;
        knowledgeFragment = null;
        projectFragment = null;
        gankFragment = null;
        personalFragment = null;
    }
}
