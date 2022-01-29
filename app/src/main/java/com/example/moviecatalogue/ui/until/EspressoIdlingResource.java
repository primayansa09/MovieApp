package com.example.moviecatalogue.ui.until;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.idling.CountingIdlingResource;

public class EspressoIdlingResource {
   private static final String MOVIE = "GLOBAL";
   private static CountingIdlingResource idlingResource = new CountingIdlingResource(MOVIE);

   public static void increment(){
      idlingResource.increment();
   }

   public static void decrement(){
      idlingResource.decrement();
   }

   public static IdlingResource getEspressoIdResource(){
      return idlingResource;
   }
}
