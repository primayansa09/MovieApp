package com.example.moviecatalogue.ui.until;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;
import com.example.moviecatalogue.ui.vo.Resource;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class LiveDataTestUtil {
   @SuppressWarnings("unchecked")
   public static <T> T getValue(LiveData<T> liveData){
      Object[] data = new Object[1];
      CountDownLatch latch = new CountDownLatch(1);

      Observer<T> observer = new Observer<T>() {
         @Override
         public void onChanged(T t) {
            data[0] = t;
            latch.countDown();
            liveData.removeObserver(this);
         }
      };

      liveData.observeForever(observer);

      try {
         latch.await(2, TimeUnit.SECONDS);
      }catch (InterruptedException e){
         e.printStackTrace();
      }

      return (T) data[0];
   }
}
