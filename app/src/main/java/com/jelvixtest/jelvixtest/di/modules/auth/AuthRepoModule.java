package com.jelvixtest.jelvixtest.di.modules.auth;

import com.jelvixtest.jelvixtest.api.AuthAPI;
import com.jelvixtest.jelvixtest.api.JelvixRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Oleg Romanenchuk on 27.09.2017.
 */

@Module(includes = {AuthModule.class})
public class AuthRepoModule {
    @Provides
    @Singleton
    public JelvixRepository provideAuthRepository(AuthAPI authAPI){
        return new JelvixRepository(authAPI);
    }
}
