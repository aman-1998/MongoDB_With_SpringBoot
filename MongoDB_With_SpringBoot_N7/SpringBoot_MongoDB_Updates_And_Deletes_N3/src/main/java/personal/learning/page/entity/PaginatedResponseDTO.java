package personal.learning.page.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginatedResponseDTO<T> {
	
	@JsonProperty("data")
	private List<T> data;
	
	@JsonProperty("totalNumberOfElements")
    private long totalNumberOfElements;
	
	@JsonProperty("numberOfElementsInCurrentPage")
	private long numberOfElementsInCurrentPage;
	
	@JsonProperty("totalNumberOfPages")
    private int totalNumberOfPages;
	
	@JsonProperty("currentPageNumber")
    private int currentPageNumber;
	
	@JsonProperty("maxPageSize")
    private int maxPageSize;
	
	@JsonProperty("lastPage")
    private boolean lastPage;

    public PaginatedResponseDTO() {}

	public PaginatedResponseDTO(List<T> data, long totalNumberOfElements, long numberOfElementsInCurrentPage,
			int totalNumberOfPages, int currentPageNumber, int maxPageSize, boolean lastPage) {
		this.data = data;
		this.totalNumberOfElements = totalNumberOfElements;
		this.numberOfElementsInCurrentPage = numberOfElementsInCurrentPage;
		this.totalNumberOfPages = totalNumberOfPages;
		this.currentPageNumber = currentPageNumber;
		this.maxPageSize = maxPageSize;
		this.lastPage = lastPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotalNumberOfElements() {
		return totalNumberOfElements;
	}

	public void setTotalNumberOfElements(long totalNumberOfElements) {
		this.totalNumberOfElements = totalNumberOfElements;
	}

	public long getNumberOfElementsInCurrentPage() {
		return numberOfElementsInCurrentPage;
	}

	public void setNumberOfElementsInCurrentPage(long numberOfElementsInCurrentPage) {
		this.numberOfElementsInCurrentPage = numberOfElementsInCurrentPage;
	}

	public int getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public void setTotalNumberOfPages(int totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getMaxPageSize() {
		return maxPageSize;
	}

	public void setMaxPageSize(int maxPageSize) {
		this.maxPageSize = maxPageSize;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	@Override
	public String toString() {
		return "PaginatedResponseDTO [data=" + data + ", totalNumberOfElements=" + totalNumberOfElements
				+ ", numberOfElementsInCurrentPage=" + numberOfElementsInCurrentPage + ", totalNumberOfPages="
				+ totalNumberOfPages + ", currentPageNumber=" + currentPageNumber + ", maxPageSize=" + maxPageSize
				+ ", lastPage=" + lastPage + "]";
	}
	
}
