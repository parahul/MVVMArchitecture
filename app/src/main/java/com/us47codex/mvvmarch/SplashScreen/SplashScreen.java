package com.us47codex.mvvmarch.SplashScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.us47codex.mvvmarch.R;
import com.us47codex.mvvmarch.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class SplashScreen extends BaseFragment {
    private static final String TAG = SplashScreen.class.getSimpleName();
private CompositeDisposable compositeDisposable = new CompositeDisposable();
private FrameLayout frameMain;
private ProgressBar horizontalProgress;

    @Override
    protected int getLayoutId() {
        return R.layout.splash_screen_fragment;
    }

    @Override
    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    protected String getToolbarTitle() {
        return null;
    }

    @Override
    protected boolean shouldShowToolbar() {
        return false;
    }

    @Override
    protected boolean shouldShowBackArrow() {
        return false;
    }

    @Override
    protected boolean shouldShowSecondImageIcon() {
        return false;
    }

    @Override
    protected boolean shouldShowFirstImageIcon() {
        return false;
    }

    @Override
    protected boolean shouldLoaderImplement() {
        return false;
    }

    @Override
    protected boolean shouldShowDrawer() {
        return false;
    }

    @Override
    protected int getCurrentFragmentId() {
        return R.id.splashScreen;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        frameMain = view.findViewById(R.id.frameMain);
        horizontalProgress = view.findViewById(R.id.horizontalProgress);
        jumpToLoginOrHomeScreen();
    }

    private void jumpToLoginOrHomeScreen(){
        initSplashTimer();
    }

    /**
     * check department Call Here..
     */
    private void initSplashTimer() {
        getCompositeDisposable().add(
                Completable.timer(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete(() -> {
                            horizontalProgress.setVisibility(View.INVISIBLE);
                            SplashScreen.this.jumpToDestinationFragment(SplashScreen.this.getCurrentFragmentId(), R.id.toLoginFragment, frameMain, null, true);
                        })
                        .subscribe()
        );
    }

}

