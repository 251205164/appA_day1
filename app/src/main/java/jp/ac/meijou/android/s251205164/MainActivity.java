package jp.ac.meijou.android.s251205164;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        prefDataStore.getString("name").ifPresent(name -> binding.textView.setText(name));
//        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
// テキストのid設定
//        TextView textView = findViewById(R.id.text_view);
//        textView.setText(R.string.text);
        binding.textView.setText(R.string.text2);
        binding.textView.setTextColor(Color.parseColor("#E30D6A"));


        binding.imageView1.setImageResource(R.drawable.ic_android_2);
        binding.buttonChange.setOnClickListener(view ->
        {
            var text = binding.editTextText.getText().toString();
            binding.textView.setText(R.string.text);
            binding.textView.setTextColor(Color.parseColor("#005800"));
            binding.imageView1.setImageResource(R.drawable.ic_android);
            binding.textView.setText(text);
        });

        binding.buttonSave.setOnClickListener(view ->
        {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
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