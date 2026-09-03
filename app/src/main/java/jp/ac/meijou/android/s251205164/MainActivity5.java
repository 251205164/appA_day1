package jp.ac.meijou.android.s251205164;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.s251205164.databinding.ActivityMain4Binding;
import jp.ac.meijou.android.s251205164.databinding.ActivityMain5Binding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity5 extends AppCompatActivity {

    private ActivityMain5Binding binding;

    private final OkHttpClient okHttpClient = new OkHttpClient();

    private void getImage(String url){
        var request = new Request.Builder()
                .url(url)
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                runOnUiThread(() -> binding.image.setImageBitmap(bitmap));

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.buttonGet.setOnClickListener(view ->
        {
            var text = binding.editText.getText().toString();
            var text_color = binding.editTextColor.getText().toString();
            var text_background_color = binding.editTextBackrround.getText().toString();
            var url = Uri.parse("https://placehold.jp/"  + text_color + "/" + text_background_color +  "/500x500.png")
                    .buildUpon()
                    .appendQueryParameter("text", text)
                    .build()
                    .toString();

            getImage(url);
        });

//        var request = new Request.Builder()
//                .url("https://m.media-amazon.com/images/I/61OFPFRSiXL._AC_UF1000,1000_QL80_.jpg")
//                .build();

//        var request = new Request.Builder()
//                .url("https://placehold.jp/350x350.png")
//                .build();
//
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//            }
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
//                runOnUiThread(() -> binding.image.setImageBitmap(bitmap));
//
//            }
//        });

    }
}