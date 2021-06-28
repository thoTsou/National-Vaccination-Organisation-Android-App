package com.example.nationalvaccinationorganisation.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://data.gov.gr/api/v1/query/";

    @GET("mdg_emvolio")
    Call<List<AreaInformation>> getStatistics(@Header("Authorization") String authHeader , @Query("date_from") String date_from , @Query("date_to") String date_to  );

}
