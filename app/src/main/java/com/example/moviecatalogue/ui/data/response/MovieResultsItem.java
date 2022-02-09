package com.example.moviecatalogue.ui.data.response;

import android.os.Parcel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;

@Entity(tableName = "movieentities")
public final class MovieResultsItem {

	@SerializedName("overview")
	@PrimaryKey
	@NotNull
	@ColumnInfo(name = "overview")
	private final String overview;

	@SerializedName("original_language")
	@NotNull
	@ColumnInfo(name = "originalLanguage")
	private final String originalLanguage;

	@SerializedName("original_title")
	@NotNull
	@ColumnInfo(name = "originalTitle")
	private final String originalTitle;

	@SerializedName("title")
	@NotNull
	@ColumnInfo(name = "title")
	private final String title;

	@SerializedName("poster_path")
	@NotNull
	@ColumnInfo(name = "posterPath")
	private final String posterPath;

	@SerializedName("backdrop_path")
	@NotNull
	@ColumnInfo(name = "backdropPath")
	private final String backdropPath;

	@SerializedName("release_date")
	@NotNull
	@ColumnInfo(name = "releaseDate")
	private final String releaseDate;

	@SerializedName("popularity")
	@ColumnInfo(name = "popularity")
	private final double popularity;

	@SerializedName("vote_average")
	@ColumnInfo(name = "voteAverage")
	private final float voteAverage;

	@SerializedName("id")
	@ColumnInfo(name = "movieId")
	private final int id;

	@ColumnInfo(name = "isFavorite")
	private boolean favorite = false;

	@SerializedName("vote_count")
	@ColumnInfo(name = "vaoteCount")
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

	protected MovieResultsItem(Parcel in) {
		overview = in.readString();
		originalLanguage = in.readString();
		originalTitle = in.readString();
		title = in.readString();
		posterPath = in.readString();
		backdropPath = in.readString();
		releaseDate = in.readString();
		popularity = in.readDouble();
		voteAverage = in.readFloat();
		id = in.readInt();
		voteCount = in.readInt();
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