package jp.ac.meijou.android.s251205164;

import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s251205164.databinding.ActivityMain5Binding;
import jp.ac.meijou.android.s251205164.databinding.ActivityMain6Binding;

public class MainActivity6 extends AppCompatActivity {

    private ActivityMain6Binding binding;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectivityManager = getSystemService(ConnectivityManager.class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        updateNetwork();

        binding.buttonGet.setOnClickListener(view ->
        {
            updateNetwork();
//            binding.textAddress.setTextColor(Color.parseColor("#456198"));
//            binding.textAddress.setTextColor(Color.parseColor("#780002"));
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        updateNetwork();
    }


    private void updateNetwork(){
        var currentNetwork = connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);
    }
    private void updateTransport(Network network){
        var caps = connectivityManager.getNetworkCapabilities(network);

        if(caps != null){
            String transport;
            if(caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                transport = "モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "WiFi";
            }else{
                transport = "その他";
            }
            binding.textTransport.setText(transport);
        }
    }

    private void updateIpAddress(Network network){
        var linkProperties = connectivityManager.getLinkProperties(network);

        if (linkProperties != null){
            var addresses = linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.textAddress.setText(addresses);
        }
    }
}