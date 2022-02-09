package com.example.moviecatalogue.ui.data.response;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tventities")
public class TvResultsItem {

	@SerializedName("first_air_date")
	@PrimaryKey
	@NonNull
	@ColumnInfo(name = "firstAirDate")
	private String firstAirDate;

	@SerializedName("overview")
	@ColumnInfo(name = "overview")
	private String overview;

	@SerializedName("original_language")
	@ColumnInfo(name = "originalLanguage")
	private String originalLanguage;

	@SerializedName("poster_path")
	@ColumnInfo(name = "posterPath")
	private String posterPath;

	@SerializedName("backdrop_path")
	@ColumnInfo(name = "backdropPath")
	private String backdropPath;

	@SerializedName("original_name")
	@ColumnInfo(name = "originalName")
	private String originalName;

	@SerializedName("popularity")
	@ColumnInfo(name = "popularity")
	private double popularity;

	@SerializedName("vote_average")
	@ColumnInfo(name = "voteAvarage")
	private float voteAverage;

	@SerializedName("name")
	@ColumnInfo(name = "name")
	private String name;

	@SerializedName("id")
	@ColumnInfo(name = "tvId")
	private int id;

	@ColumnInfo(name = "isFavorite")
	private boolean favorite = false;

	@SerializedName("vote_count")
	@ColumnInfo(name = "voteCount")
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

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	public int getVoteCount() {
		return voteCount;
	}
}