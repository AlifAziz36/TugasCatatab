package com.example.tugascatatanuas.data.helper;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.tugascatatanuas.ui.notes.AddNotesActivity;
import com.google.firebase.messaging.FirebaseMessaging;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
    public class FCMHelper {
        public static String token = "keren";
        public static final String FCM_MESSAGE_URL = "https://firebase.google.com/docs/reference/kotlin/com/google/firebase/auth/package-summary?authuser=0&hl=en#classes";

        public static String getToken(Context ctx){
            String TAG = "GGWP";
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(task -> {
                        if (!task.isSuccessful()){
                            Log.w(TAG, "fetching FCM registration token Failed", task.getException());
                            return;
                        }
                        token = task.getResult();
                    });
            return token;
        }
        public static void sendNotifNewNote(String token, String title, String category) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    OkHttpClient client = new OkHttpClient();
                    MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                    JSONObject jsonNotif = new JSONObject();
                    JSONObject wholeObj = new JSONObject();

                    try {
                        jsonNotif.put("title", "New note " + title);
                        jsonNotif.put("body", "Category " + category + " with title " + title);
                        wholeObj.put("to", token);
                        wholeObj.put("notification", jsonNotif);

                    } catch (JSONException e) {
                        Log.d("FCM ERROR", e.toString());
                    }

                    RequestBody rBody = RequestBody.create(wholeObj.toString(), mediaType);
                    Request request = new Request.Builder()
                            .url(FCM_MESSAGE_URL)
                            .post(rBody)
                            .addHeader("Authorization", "key=" + com.example.tugascatatanuas.data.token.getFCM_SERVER_KEY())
                            .addHeader("Content-Type", "application/json")
                            .build();

                    try {
                        Response response = client.newCall(request).execute();

                        Log.d("GGWP", response.toString());
                    } catch (IOException e) {
                        Log.d("FCM ERROR", e.toString());
                    }

                    return null;
                }
            }.execute();
        }
}
