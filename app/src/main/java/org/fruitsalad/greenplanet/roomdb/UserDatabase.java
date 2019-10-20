package org.fruitsalad.greenplanet.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import org.fruitsalad.model.SaviourOfEarth;

@Database(entities = {SaviourOfEarth.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();

}
