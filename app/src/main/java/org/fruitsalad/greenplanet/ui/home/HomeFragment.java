package org.fruitsalad.greenplanet.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.HeatMap;
import com.anychart.enums.SelectionMode;
import com.anychart.graphics.vector.SolidFill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.fruitsalad.greenplanet.R;
import org.fruitsalad.greenplanet.roomdb.UserDatabase;
import org.fruitsalad.greenplanet.utility.MockData;

import ir.alirezaiyan.progressbar.LevelProgressBar;

public class HomeFragment extends Fragment {

    private FloatingActionButton fab;
    private TextView textScore;
    private int score;
    private UserDatabase userDatabase;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        fab = root.findViewById(R.id.fab_increase_score);
        textScore = root.findViewById(R.id.textView_score);

        userDatabase = Room.databaseBuilder(getActivity().getBaseContext(), UserDatabase.class, "SaviourOfEarth")
                .build();
        initializeViewsTask();
        onFABClicked();
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        AnyChartView anyChartView = getActivity().findViewById(R.id.any_chart_view);
        anyChartView.setProgressBar(getActivity().findViewById(R.id.progress_bar));

        HeatMap riskMap = AnyChart.heatMap();

        riskMap.stroke("1 #fff");
        riskMap.hovered()
                .stroke("6 #fff")
                .fill(new SolidFill("#545f69", 1d))
                .labels("{ fontColor: '#fff' }");

        riskMap.interactivity().selectionMode(SelectionMode.NONE);

        riskMap.title().enabled(true);
        riskMap.title()
                .text("Oxygen Heat Map")
                .padding(0d, 0d, 20d, 0d);

        riskMap.labels().enabled(true);
        riskMap.labels()
                .minFontSize(14d)
                .format("function() {\n" +
                        "      var namesList = [\"Low\", \"Medium\", \"High\", \"Extreme\"];\n" +
                        "      return namesList[this.heat];\n" +
                        "    }");

        riskMap.yAxis(0).stroke(null);
        riskMap.yAxis(0).labels().padding(0d, 15d, 0d, 0d);
        riskMap.yAxis(0).ticks(false);
        riskMap.xAxis(0).stroke(null);
        riskMap.xAxis(0).ticks(false);

        riskMap.tooltip().title().useHtml(true);
        riskMap.tooltip()
                .useHtml(true)
                .titleFormat("function() {\n" +
                        "      var namesList = [\"Low\", \"Medium\", \"High\", \"Extreme\"];\n" +
                        "      return '<b>' + namesList[this.heat] + '</b> Residual Risk';\n" +
                        "    }")
                .format("function () {\n" +
                        "       return '<span style=\"color: #CECECE\">Likelihood: </span>' + this.x + '<br/>' +\n" +
                        "           '<span style=\"color: #CECECE\">Consequence: </span>' + this.y;\n" +
                        "   }");

        riskMap.data(MockData.getMockHeatMapData());

        anyChartView.setChart(riskMap);
        super.onViewCreated(view, savedInstanceState);
    }


    private void onFABClicked() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ObjectDetection.class);
                startActivity(intent);
                //increaseScoreTask();
            }
        });
    }

    private void increaseScoreTask() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                score = userDatabase.daoAccess().getScoreByName("Abhilash");
                score++;
                userDatabase.daoAccess().updateSaviourOfEarth("Abhilash", score);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                textScore.setText(Integer.toString(score));
            }
        }.execute();
    }

    private void initializeViewsTask() {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                score = userDatabase.daoAccess().getScoreByName("Abhilash");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                textScore.setText(Integer.toString(score));
                // TODO : Initialize other views like HEATMAP, PROFILE PICTURE
            }
        }.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        LevelProgressBar levelProgressBar = getActivity().findViewById(R.id.p1);
        levelProgressBar.setProgressWithAnimation(5F);
        CircularImageView circularImageView = getActivity().findViewById(R.id.circularImageView);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.vector_abhijit_1);
        circularImageView.setImageBitmap(largeIcon);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}