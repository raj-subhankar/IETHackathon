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

    @FormUrlEncoded
    @POST("users/add")
    Call<AuthResult> register(@Field("email") String email,
                              @Field("password") String password);

    @Multipart
    @PUT("users/{id}")
    Call<AuthResult> createProfile(@Path("id") String id,
                                   @Part("name") RequestBody name,
                                   @Part MultipartBody.Part file);

    @Multipart
    @POST("posts/add")
    Call<ResponseBody> upload(@Part("user") RequestBody name,
                              @Part MultipartBody.Part file,
                              @Part("postBody") RequestBody postBody,
                              @Part("category") RequestBody category);

    @GET("posts/all")
    Call<List<Post>> getNewposts();

    @GET("posts/all")
    Call<List<Post>> getMoreNewposts(@Query("lastid") String id);

    @FormUrlEncoded
    @POST("posts/like")
    Call<Result> like(@Field("post_id") String id,
                      @Field("userId") String user);

    @GET("posts/all/{userid}")
    Call<List<Post>> getUserPosts(@Path("userid") String userId);

    @Multipart
    @POST("categories/add")
    Call<ResponseBody> createCategory(@Part("user") RequestBody name,
                              @Part MultipartBody.Part file,
                              @Part("name") RequestBody postBody);

    @GET("categories/all/{userid}")
    Call<List<Category>> getUserCategories(@Path("userid") String userId);

    @GET("posts/all/category/{categoryid}")
    Call<List<Post>> getPostsByCategories(@Path("categoryid") String categoryId);
}

