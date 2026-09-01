package jp.ac.meijou.android.s251205164;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205164.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private  PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore = PrefDataStore.getInstance(this);
        prefDataStore.getString("name").ifPresent(name ->
        {
            binding.textView.setText(name);
            binding.textView.setTextColor(Color.parseColor("#950000"));
            var modText = "pref" + name;
            Log.d("maijo", modText);
        });
//        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// テキストのid設定
//        TextView textView = findViewById(R.id.text_view);
//        textView.setText(R.string.text);
//        binding.textView.setText(R.string.text2);
        binding.textView.setTextColor(Color.parseColor("#E30D6A"));

        var text1 = binding.textView.getText();
        if("unkown".equals(text1)){
            binding.imageView1.setImageResource(R.drawable.ic_android_6);
        } else if("".equals(text1)){
            binding.textView.setText("データなし");
            binding.imageView1.setImageResource(R.drawable.ic_android_7);
        }else{
            binding.imageView1.setImageResource(R.drawable.ic_android_2);
        }
        binding.buttonChange.setOnClickListener(view ->
        {
            var text = binding.editTextText.getText().toString();
            binding.textView.setText(R.string.text);
            binding.textView.setTextColor(Color.parseColor("#008900"));
            binding.imageView1.setImageResource(R.drawable.ic_android);
            binding.textView.setText(text);
        });

        binding.buttonSave.setOnClickListener(view ->
        {
            var text = binding.editTextText.getText().toString();
            binding.imageView1.setImageResource(R.drawable.ic_android_3);
//            try {
//                Thread.sleep(50000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            binding.imageView1.setImageResource(R.drawable.ic_android_2);
            if(text.contains("あ")){
                binding.imageView1.setImageResource(R.drawable.ic_android_4);
                binding.textView.setTextColor(Color.parseColor("#008989"));
            }else if(text.contains("い")){
                binding.imageView1.setImageResource(R.drawable.ic_android_5);
                binding.textView.setTextColor(Color.parseColor("#908900"));
            }else{
                if("".equals(text)){

                }else{
                    text = "unkown";
                }
            }
            prefDataStore.setString("name", text);
        });

        binding.buttonDelete.setOnClickListener(view -> {
            binding.textView.setText(null);
        });

        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                binding.textView.setText(editable.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
        });
    }
}