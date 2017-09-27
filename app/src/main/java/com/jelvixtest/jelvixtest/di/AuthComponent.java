package com.jelvixtest.jelvixtest.di;

import android.content.Context;

import com.jelvixtest.jelvixtest.di.modules.ContextModule;
import com.jelvixtest.jelvixtest.di.modules.auth.AuthRepoModule;
import com.jelvixtest.jelvixtest.mvp.presenters.SignInPresenter;
import com.jelvixtest.jelvixtest.mvp.presenters.SignUpPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, AuthRepoModule.class})
public interface AuthComponent {

    Context getContext();

    void inject(SignInPresenter presenter);

    void inject(SignUpPresenter presenter);
}
