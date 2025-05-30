package personal.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackDetail {

    @JsonProperty("track_id")
    private int trackId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("album_id")
    private int albumId;

    @JsonProperty("media_type_id")
    private int mediaTypeId;

    @JsonProperty("genre_id")
    private int genreId;

    @JsonProperty("composer")
    private String composer;

    @JsonProperty("milliseconds")
    private int milliseconds;

    @JsonProperty("bytes")
    private int bytes;

    @JsonProperty("unit_price")
    private double unitPrice;
    
    public TrackDetail() { }

	public TrackDetail(int trackId, String name, int albumId, int mediaTypeId, int genreId, String composer,
			int milliseconds, int bytes, double unitPrice) {
		this.trackId = trackId;
		this.name = name;
		this.albumId = albumId;
		this.mediaTypeId = mediaTypeId;
		this.genreId = genreId;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitPrice = unitPrice;
	}

	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public int getMediaTypeId() {
		return mediaTypeId;
	}

	public void setMediaTypeId(int mediaTypeId) {
		this.mediaTypeId = mediaTypeId;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
    public String toString() {
        return "TrackDetail [trackId=" + trackId + ", name=" + name + ", albumId=" + albumId +
                ", mediaTypeId=" + mediaTypeId + ", genreId=" + genreId +
                ", composer=" + composer + ", milliseconds=" + milliseconds +
                ", bytes=" + bytes + ", unitPrice=" + unitPrice + "]";
    }
}

