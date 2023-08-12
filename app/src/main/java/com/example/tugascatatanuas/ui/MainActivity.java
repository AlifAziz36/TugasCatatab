package com.example.tugascatatanuas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.tugascatatanuas.R;
import com.example.tugascatatanuas.databinding.ActivityMainBinding;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        askNotificationPermission();

        BottomNavigationView navView =findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_profile, R.id.navigation_notes, R.id.navigation_about)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

    }
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    Toast.makeText(this, "Your app will not able to get notification !", Toast.LENGTH_SHORT).show();
                }
            });

    private void askNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED) {
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
            // TODO: display an educational UI explaining to the user the features that will be enabled
            //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
            //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
            //       If the user selects "No thanks," allow the user to continue without notifications.
        } else {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }
        // }
    }
}