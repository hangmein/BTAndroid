package ntu.nghia.project;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ View
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.navigation_view);
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

        // 2. Lấy NavController
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
        if (navView != null && navController != null) {
            NavigationUI.setupWithNavController(navView, navController);
            navView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_history || id == R.id.nav_settings) {
                    Toast.makeText(MainActivity.this, "Chức năng đang phát triển!", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false; // Không chuyển trang
                }
                else if (id == R.id.nav_ranking) {
                    navController.navigate(R.id.nav_profile);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                else if (id == R.id.nav_home) {
                    navController.navigate(R.id.nav_home);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
            });
        }
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        }
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v -> {
                navController.navigate(R.id.nav_profile);
            });
        }
    }
}