package com.example.moviecatalogue.ui.data.response;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public final class MovieResponse {

	@SerializedName("page")
	private final int page;
	@SerializedName("total_pages")
	private final int totalPages;
	@SerializedName("results")
	@NotNull
	private final List<MovieResultsItem> results;
	@SerializedName("total_results")
	private final int totalResults;

	public MovieResponse(int page, int totalPages, @NotNull List results, int totalResults) {
		this.page = page;
		this.totalPages = totalPages;
		this.results = results;
		this.totalResults = totalResults;
	}

	@NotNull
	public final List getResults() {
		return this.results;
	}

}