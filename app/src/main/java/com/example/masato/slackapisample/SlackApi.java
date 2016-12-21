package com.example.masato.slackapisample;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by masato on 16/12/21.
 */

public interface SlackApi {

    String TOKEN = "<your-token-goes-here>";
    String CHANNEL = "#random";


    @Multipart
    @POST("api/files.upload")
    Observable<UploadFileResponse> uploadFile(
            @Query("token") String token,
            @PartMap Map<String, RequestBody> params,
            @Query("filetype") String filetype,
            @Query("filename") String filename, @Query("title") String title,
            @Query("initial_comment") String initialComment, @Query("channels") String channels);


    public static class UploadFileResponse {
        boolean ok;
        String error;

        @Override
        public String toString() {
            return "UploadFileResponse{" +
                    "ok=" + ok +
                    ", error='" + error + '\'' +
                    '}';
        }
    }

}
