package com.example.moviecatalogue.ui.data.response;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TvResultsItem {

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;
	private List<Integer> genreIds1;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("origin_country")
	private List<String> originCountry;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_name")
	private String originalName;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("vote_average")
	private float voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("vote_count")
	private int voteCount;

	public TvResultsItem(String firstAirDate, String overview, String originalLanguage, String posterPath, String backdropPath, String originalName, double popularity, float voteAverage, String name, int id, int voteCount) {
		this.firstAirDate = firstAirDate;
		this.overview = overview;
		this.originalLanguage = originalLanguage;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.originalName = originalName;
		this.popularity = popularity;
		this.voteAverage = voteAverage;
		this.name = name;
		this.id = id;
		this.voteCount = voteCount;
	}


	public String getFirstAirDate() {
		return firstAirDate;
	}

	public String getOverview() {
		return overview;
	}

	public String getOriginalLanguage() {
		return originalLanguage;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public List<String> getOriginCountry() {
		return originCountry;
	}

	public String getBackdropPath() {
		return backdropPath;
	}

	public String getOriginalName() {
		return originalName;
	}

	public double getPopularity() {
		return popularity;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getVoteCount() {
		return voteCount;
	}
}