package com.example.moviecatalogue.ui.data.response;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public final class MovieResultsItem {

	@SerializedName("overview")
	@NotNull
	private final String overview;
	@SerializedName("original_language")
	@NotNull
	private final String originalLanguage;
	@SerializedName("original_title")
	@NotNull
	private final String originalTitle;
	@SerializedName("title")
	@NotNull
	private final String title;
	@SerializedName("poster_path")
	@NotNull
	private final String posterPath;
	@SerializedName("backdrop_path")
	@NotNull
	private final String backdropPath;
	@SerializedName("release_date")
	@NotNull
	private final String releaseDate;
	@SerializedName("popularity")
	private final double popularity;
	@SerializedName("vote_average")
	private final float voteAverage;
	@SerializedName("id")
	private final int id;
	@SerializedName("vote_count")
	private final int voteCount;

	public MovieResultsItem(@NotNull String overview, @NotNull String originalLanguage, @NotNull String originalTitle, @NotNull String title, @NotNull String posterPath, @NotNull String backdropPath, @NotNull String releaseDate, double popularity, float voteAverage, int id, int voteCount) {
		this.overview = overview;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.title = title;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.releaseDate = releaseDate;
		this.popularity = popularity;
		this.voteAverage = voteAverage;
		this.id = id;
		this.voteCount = voteCount;
	}

	@NotNull
	public String getOverview() {
		return overview;
	}

	@NotNull
	public String getOriginalLanguage() {
		return originalLanguage;
	}

	@NotNull
	public String getOriginalTitle() {
		return originalTitle;
	}

	@NotNull
	public String getTitle() {
		return title;
	}

	@NotNull
	public String getPosterPath() {
		return posterPath;
	}

	@NotNull
	public String getBackdropPath() {
		return backdropPath;
	}

	@NotNull
	public String getReleaseDate() {
		return releaseDate;
	}

	public double getPopularity() {
		return popularity;
	}

	public float getVoteAverage() {
		return voteAverage;
	}

	public int getId() {
		return id;
	}

	public int getVoteCount() {
		return voteCount;
	}
}