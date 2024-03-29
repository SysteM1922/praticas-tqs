package tqs.air_quality.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import tqs.air_quality.models.DataSource;
import tqs.air_quality.models.SearchType;
import tqs.air_quality.models.serializers.WBResponse;

public class Cache {
	
	private static Map<String, CacheObject> map = new HashMap<>();
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static Integer requests = 0;
	private static Integer hits = 0;
	private static Integer misses = 0;

	private Cache() {
	}

	static {
		scheduler.scheduleAtFixedRate(new Runnable() {
			public void run() {
				for (Map.Entry<String, CacheObject> entry : map.entrySet()) {
					if (entry.getValue().isExpired()) {
						map.remove(entry.getKey());
					}
				}
			}
		}, 0, 1, java.util.concurrent.TimeUnit.MINUTES);
	}

	public static void add(String searchName, SearchType type, DataSource source, WBResponse data) {
		map.put(searchName, new CacheObject(data, type, source));
	}
	
	public static void add(String searchName, SearchType type, DataSource source,  WBResponse data, long timestamp) {
		map.put(searchName, new CacheObject(data, type, source, timestamp));
	}

	public static WBResponse get(String searchName, SearchType type, DataSource source) {
		requests++;
		if (map.isEmpty() || map.get(searchName) == null) {
			misses++;
			return null;
		}
		for (Map.Entry<String, CacheObject> entry : map.entrySet()) {
			if (entry.getKey().equals(searchName) && entry.getValue().getSearchType().equals(type)
					&& (source == null || entry.getValue().getDataSource().equals(source))) {
				hits++;
				entry.getValue().updateTimestamp();
				return entry.getValue().getData();
			}
		}
		misses++;
		return null;
	}

	public static CacheJson toJson() {
		return new CacheJson(requests, hits, misses);
	}

	@EqualsAndHashCode(callSuper = false)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CacheJson {
		@JsonProperty("requests")
		public int requests;
		@JsonProperty("hits")
		public int hits;
		@JsonProperty("misses")
		public int misses;

		public CacheJson(int requests, int hits, int misses) {
			this.requests = requests;
			this.hits = hits;
			this.misses = misses;
		}

		@JsonCreator
		public CacheJson() {
		}

    }

	public static Map<String, CacheObject> getCache() {
		return map;
	}

	public static Integer getRequests() {
		return requests;
	}

	public static Integer getHits() {
		return hits;
	}

	public static Integer getMisses() {
		return misses;
	}

	public static void reset() {
		map.clear();
		requests = 0;
		hits = 0;
		misses = 0;
	}

}
