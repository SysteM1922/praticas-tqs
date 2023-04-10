package tqs.air_quality.models.serializers;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBCurrentResponse implements WBResponse {

	@JsonProperty("city_name")
	public String city_name;
	@JsonProperty("country_code")
	public String country_code;
	@JsonProperty("lat")
	public Double lat;
	@JsonProperty("lon")
	public Double lon;
	@JsonProperty("data")
	public List<WBCurrentData> data;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class WBCurrentData implements WBData {

		@JsonProperty("aqi")
		public Integer aqi;
		@JsonProperty("co")
		public Double co;
		@JsonProperty("mold_level")
		public Integer mold_level;
		@JsonProperty("no2")
		public Double no2;
		@JsonProperty("o3")
		public Double o3;
		@JsonProperty("pm10")
		public Double pm10;
		@JsonProperty("pm25")
		public Double pm25;
		@JsonProperty("pollen_level_grass")
		public Integer pollen_level_grass;
		@JsonProperty("pollen_level_tree")
		public Integer pollen_level_tree;
		@JsonProperty("pollen_level_weed")
		public Integer pollen_level_weed;
		@JsonProperty("predominant_pollen_type")
		public String predominant_pollen_type;
		@JsonProperty("so2")
		public Double so2;

	}

}
