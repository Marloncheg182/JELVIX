package com.jelvixtest.jelvixtest.di.modules.auth;

import com.jelvixtest.jelvixtest.api.AuthAPI;
import com.jelvixtest.jelvixtest.di.modules.RetrofitModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Oleg Romanenchuk on 27.09.2017.
 */

@Module(includes = {RetrofitModule.class})
public class AuthModule {
    @Provides
    @Singleton
    public AuthAPI provideAuthApi(Retrofit retrofit){
        return retrofit.create(AuthAPI.class);
    }
}
