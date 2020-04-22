package za.co.immedia.repository;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import za.co.immedia.model.SuperHeroModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SuperHeroAPIRepo {

    @GET("/api/{token}/{id}")
    Call<SuperHeroModel> searchSuperHeroById(@Path("token") String token,@Path("id") int id);

    @GET("/api/{token}/search/{name}")
    Call<SuperHeroModel> searchSuperHeroByName(@Path("token") String token,@Path("name") String name);

}
