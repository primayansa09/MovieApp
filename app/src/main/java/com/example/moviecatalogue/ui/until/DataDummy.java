package com.example.moviecatalogue.ui.until;
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
                "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                "en",
                "Encanto",
                "Encanto",
                "/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                "/3G1Q5xF40HkUBJXxt2DQgQzKTp5.jpg",
                "2021-11-24",
                3729.951,
                7.8F,
                568124,
                4319));
        return movie;
    }

    public static ArrayList<TvResultsItem> generateDummyTv(){
        ArrayList<TvResultsItem> tvShow = new ArrayList<>();

        tvShow.add(new TvResultsItem(
                "2022-01-28",
                "A high school becomes ground zero for a zombie virus outbreak. Trapped students must fight their way out — or turn into one of the rabid infected.",
                "ko",
                "/pTEFqAjLd5YTsMD6NSUxV6Dq7A6.jpg",
                "/8Xs20y8gFR0W9u8Yy9NKdpZtSu7.jpg",
                "지금 우리 학교는",
                6862.339,
                8.8F,
                "All of Us Are Dead",
                99966,
                973));
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
