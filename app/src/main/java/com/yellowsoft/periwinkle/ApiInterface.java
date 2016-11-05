package com.yellowsoft.periwinkle;

/**
 * Created by subhankar on 11/5/2016.
 */

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("authenticate")
    Call<AuthResult> authenticate(@Field("name") String name,
                                  @Field("password") String password);
}

