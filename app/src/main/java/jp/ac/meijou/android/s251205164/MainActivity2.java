package jp.ac.meijou.android.s251205164;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s251205164.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s251205164.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s251205164.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.buttonA.setOnClickListener(view ->
        {
            var intent = new Intent(this, MainActivity3.class);
            startActivity(intent);
        });

        binding.buttonB.setOnClickListener(view ->
        {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        binding.transport.setOnClickListener(view ->
        {
            String settext = binding.editText.getText().toString();

            var intent = new Intent(this, MainActivity3.class);
            intent.putExtra("editText", settext);
            startActivity(intent);
        });

        binding.buttonAction.setOnClickListener(view ->
        {
            var intent = new Intent(this, MainActivity3.class);
            getActivityResult.launch(intent);
        });
    }

    //activity result
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->{
                switch (result.getResultCode()){
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                            .map(data -> data.getStringExtra("ret"))
                            .map((text->"result : " + text))
                            .ifPresent(text -> binding.editTextResult.setText(text));
                            break;
                    case RESULT_CANCELED:
                        binding.editTextResult.setText("Result: Canceled");
                        break;
                    default:
                        binding.editTextResult.setText("Result: unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
            );
}