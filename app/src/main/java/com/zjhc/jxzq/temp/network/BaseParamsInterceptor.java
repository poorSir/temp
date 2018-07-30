package com.zjhc.jxzq.temp.network;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @Author szh
 * @Date 2018/7/20.
 * @Description
 */

public class BaseParamsInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Request newRequest = addHeader(oldRequest);
        return chain.proceed(newRequest);
    }
    /**添加表头*/
    public Request addHeader(Request oldRequest){
//        String token = (String) SpUtil.getInstance().getValue(BaseParam.TOKEN, SpUtil.DataType.STRING);
        String token =null;
        if(token == null){
            token ="";
        }
        Request request = oldRequest.newBuilder()
                .header("token", token)
                .method(oldRequest.method(), oldRequest.body())
                .build();
        return request;
    }
    /**添加参数*/
    public Request addParam(Request oldRequest){
//        String token = (String) SpUtil.getInstance().getValue(BaseParam.TOKEN, SpUtil.DataType.STRING);
        String token =null;
        if(token == null){
            token ="";
        }
        RequestBody newFormBody = new FormBody.Builder()
                .add("token",token)
                .build();
        //默认添加formBody后不能添加新的form表单，需要先将RequestBody转成string去拼接
        String postBodyString = bodyToString(oldRequest.body());
        postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(newFormBody);
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(),oldRequest.body())
                .post(RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), postBodyString))
                .build();
        return newRequest;
    }
    /**RequestBody转String的方法*/
    private static String bodyToString(final RequestBody request){
        try {
            final RequestBody copy   = request;
            final Buffer      buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }
}
