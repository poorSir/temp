package com.zjhc.jxzq.temp.network;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zjhc.jxzq.temp.common.AppConfig;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author szh
 * @Date 2018/7/6.
 * @Description 网络请求
 */

public class NetConnect {
    //请求超时时间
    private static final int CONNECTTIMEOUT = 30;
    private static final int READTIMEOUT = 30;
    private static final int WRITETIMEOUT = 30;
    private Retrofit retrofit;
    private static  NetConnect instance;
    public static NetConnect getInstance(){
        if(instance == null){
            instance =  new NetConnect();
        }
        return instance;
    }
    public NetConnect(){
        OkHttpClient.Builder builder =  new OkHttpClient.Builder();
        builder.connectTimeout(CONNECTTIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READTIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(WRITETIMEOUT,TimeUnit.SECONDS);
        //打印参数
        builder.addInterceptor(new BaseParamsInterceptor());
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        // 失败后尝试重新请求
        builder.retryOnConnectionFailure(true);
            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConfig.baseUrl)
                    .client(builder.build())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
    //根据接口的字节码文件对象获取接口对象
    public static <T> T getService(Class<T> mClass){
        return NetConnect.getInstance().retrofit.create(mClass);
    }
    //线程链度转化
    public static <T>ObservableTransformer<T,T> changeThread(){
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
