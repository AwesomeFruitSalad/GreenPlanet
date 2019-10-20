package org.fruitsalad.greenplanet.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import org.fruitsalad.greenplanet.model.SaviourOfEarth;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertSaviourOfEarth(SaviourOfEarth saviourOfEarth);

    @Insert
    void insertSavioursOfEarth(List<SaviourOfEarth> savioursOfEarth);

    @Query("SELECT * from SaviourOfEarth WHERE name = :name")
    SaviourOfEarth getSaviourOfEarthByName(String name);

    @Query("SELECT * from SaviourOfEarth")
    List<SaviourOfEarth> getAllSavioursOfEarth();

    @Query("SELECT achievements from SaviourOfEarth")
    List<String> getAllAchievements();

    @Query("SELECT score from SaviourOfEarth where name = :name")
    int getScoreByName(String name);

    @Query("SELECT score from SaviourOfEarth")
    List<Integer> getAllScores();

    @Query("UPDATE SaviourOfEarth SET score = :score WHERE name = :name")
    int updateSaviourOfEarth(String name, int score);
}
