package jp.ac.meijou.android.s251205164;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205164.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s251205164.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    private  double value_1 = 0;
    private  double result = 0;
    private double value_2 = 0;
    private int cal = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.buttonAC.setOnClickListener(view ->
                binding.calculateView.setText(null));

        binding.button0.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "0";
            binding.calculateView.setText(text_next);
        });

        binding.button1.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "1";
            binding.calculateView.setText(text_next);
        });

        binding.button2.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "2";
            binding.calculateView.setText(text_next);
        });

        binding.button3.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "3";
            binding.calculateView.setText(text_next);
        });

        binding.button4.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "4";
            binding.calculateView.setText(text_next);
        });

        binding.button5.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "5";
            binding.calculateView.setText(text_next);
        });

        binding.button6.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "6";
            binding.calculateView.setText(text_next);
        });

        binding.button7.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "7";
            binding.calculateView.setText(text_next);
        });

        binding.button8.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "8";
            binding.calculateView.setText(text_next);
        });

        binding.button9.setOnClickListener(view ->
        {
            var text = binding.calculateView.getText();;
            var text_next = text + "9";
            binding.calculateView.setText(text_next);
        });

        binding.buttonAdd.setOnClickListener(view ->
        {
            String text = (String) binding.calculateView.getText();
            value_1 = Double.parseDouble(text);
            binding.calculateView.setText(null);
        });

        Intent intent = getIntent();
        String setText = intent.getStringExtra("editText");
        binding.calculateView.setText(setText);

    }

}