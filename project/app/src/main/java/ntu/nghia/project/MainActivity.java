package ntu.nghia.project;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

// IMPORT QUAN TRỌNG: Phải dùng AppDatabase giống PlayFragment
import ntu.nghia.project.database.AppDatabase;
import ntu.nghia.project.database.db_user.User;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavController navController;
    private TextView tvUser, tvBestScore;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDatabase = AppDatabase.getDatabase(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.navigation_view);
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

        tvUser = findViewById(R.id.tvUser);
        tvBestScore = findViewById(R.id.tvBestScore);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }
        if (navView != null && navController != null) {
            NavigationUI.setupWithNavController(navView, navController);
            navView.setNavigationItemSelectedListener(item -> {
                int id = item.getItemId();
                if (id == R.id.nav_ranking) {
                    navController.navigate(R.id.nav_profile);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else if (id == R.id.nav_home) {
                    navController.navigate(R.id.nav_home);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                else if (id == R.id.nav_logout) {
                    finishAffinity();
                    System.exit(0);
                    return true;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            });
        }

        if (btnMenu != null) btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        if (btnProfile != null) btnProfile.setOnClickListener(v -> navController.navigate(R.id.nav_profile));
        loadTopPlayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTopPlayer();
    }
    private void loadTopPlayer() {
        new Thread(() -> {
            User topUser = appDatabase.userDao().getTopPlayer();

            runOnUiThread(() -> {
                if (topUser != null) {
                    tvUser.setText("Top 1: " + topUser.name);
                    tvBestScore.setText("Score: " + topUser.score);
                } else {
                    tvUser.setText("Top 1: ---");
                    tvBestScore.setText("Score: 0");
                }
            });
        }).start();
    }
}