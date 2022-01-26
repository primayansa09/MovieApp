package com.example.moviecatalogue.ui.data.response;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TvShowResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("message")
	private String message;

	@SerializedName("results")
	@NotNull
	private List<TvResultsItem> results;

	@SerializedName("total_results")
	private int totalResults;

	public TvShowResponse(int page, int totalPages, String message, @NotNull List<TvResultsItem> results, int totalResults) {
		this.page = page;
		this.totalPages = totalPages;
		this.message = message;
		this.results = results;
		this.totalResults = totalResults;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public String getMessage(){
		return message;
	}

	public List<TvResultsItem> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}