package com.example.moviecatalogue.ui.local.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;


@Database(entities = {MovieResultsItem.class, TvResultsItem.class},
         version = 1,
        exportSchema = false)
public abstract class MovieDataBase extends RoomDatabase {
   public abstract MovieTvDao movieTvDao();

   private static volatile MovieDataBase INSTANCE;

   public static MovieDataBase getInstance(Context context){
      if (INSTANCE == null){
         synchronized (MovieDataBase.class){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDataBase.class, "Movies.db")
                    .build();
         }
      }
      return INSTANCE;
   }
}
