package org.fruitsalad.greenplanet;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.fruitsalad.greenplanet.roomdb.UserDatabase;
import org.fruitsalad.greenplanet.utility.MockData;
import org.fruitsalad.utility.MockData;

public class MainActivity extends AppCompatActivity {

    private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_achievements)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        userDatabase = Room.databaseBuilder(this, UserDatabase.class, "SaviourOfEarth")
                .build();

        initializeDatabaseWithValues();
    }

    private void initializeDatabaseWithValues() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                if (userDatabase.daoAccess().getAllAchievements() == null ||
                        userDatabase.daoAccess().getAllAchievements().isEmpty()) {
                    userDatabase.daoAccess().insertSavioursOfEarth(MockData.getSavioursOfEarth());
                }
                return null;
            }
        }.execute();
    }

}
