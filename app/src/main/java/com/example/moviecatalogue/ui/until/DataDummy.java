package com.example.moviecatalogue.ui.until;

import com.example.moviecatalogue.ui.data.MovieEntity;
import com.example.moviecatalogue.ui.data.response.MovieResultsItem;
import com.example.moviecatalogue.ui.data.response.TvResultsItem;

import java.util.ArrayList;


public class DataDummy {

    public static ArrayList<MovieResultsItem> generateDummyMovie(){
        ArrayList<MovieResultsItem> movie = new ArrayList<>();

        movie.add(new MovieResultsItem(
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "en",
                "Spider-Man: No Way Home",
                "Spider-Man: No Way Home",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                "2021-12-15",
                18473.631,
                8.5F,
                634649,
                6194));
        movie.add(new MovieResultsItem(
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "en",
                "Eternals",
                "Eternals",
                "/bcCBq9N1EMo3daNIjWJ8kYvrQm6.jpg",
                "/k2twTjSddgLc1oFFHVibfxp2kQV.jpg",
                "2021-11-03",
                6908.207,
                7.3F,
                524434,
                3603));
        return movie;
    }

    public static ArrayList<TvResultsItem> generateDummyTv(){
        ArrayList<TvResultsItem> tvShow = new ArrayList<>();

        tvShow.add(new TvResultsItem(
                "2022-01-13",
                "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.",
                "en",
                "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
                "/ctxm191q5o3axFzQsvNPlbKoSYv.jpg",
                "Peacemaker",
                3872.816,
                8.5F,
                "Peacemaker",
                110492,
                324));
        tvShow.add(new TvResultsItem(
                "2019-06-16",
                "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                "en",
                "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                "/oKt4J3TFjWirVwBqoHyIvv5IImd.jpg",
                "Euphoria",
                3585.423,
                8.4F,
                "Euphoria",
                85552,
                5857));

        return tvShow;
    }

}
