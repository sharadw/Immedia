package za.co.immedia.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import za.co.immedia.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static APIClient mApiClient;
    private Retrofit retrofit;

    private APIClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build()).build();
    }

    public static synchronized APIClient getInstance() {
        if (mApiClient == null) {
            mApiClient = new APIClient();
        }
        return mApiClient;
    }

    public SuperHeroAPIRepo getAPIService() {
        return retrofit.create(SuperHeroAPIRepo.class);
    }

}
