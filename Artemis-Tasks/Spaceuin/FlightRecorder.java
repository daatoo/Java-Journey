package w12space;

import java.util.List;

public interface FlightRecorder {

	void recordArrival(Beacon beacon);

	void recordDeparture(Beacon beacon);

	FlightRecorder createCopy();

	void tellStory();
}
