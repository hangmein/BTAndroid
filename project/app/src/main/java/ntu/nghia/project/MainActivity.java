package ntu.nghia.project; // Đảm bảo đúng tên package của bạn
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navView = findViewById(R.id.navigation_view);
        ImageButton btnMenu = findViewById(R.id.btnMenu);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        navController = androidx.navigation.fragment.NavHostFragment.findNavController(
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)
        );
        if (navView != null) {
            NavigationUI.setupWithNavController(navView, navController);
        }
        if (btnMenu != null) {
            btnMenu.setOnClickListener(v -> {
                drawerLayout.openDrawer(findViewById(R.id.navigation_view));
            });
        }
        if (btnProfile != null) {
            btnProfile.setOnClickListener(v -> {
                navController.navigate(R.id.nav_profile);
            });
        }

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(findViewById(R.id.navigation_view))) {
            drawerLayout.closeDrawer(findViewById(R.id.navigation_view));
        } else {
            super.onBackPressed();
        }
    }
}