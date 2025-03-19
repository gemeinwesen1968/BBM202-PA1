public class TrafficFlow implements Comparable<TrafficFlow> {
    private String flow_id;
    private String timestamp;
    private int flow_duration;

    public void setFlow_duration(int flow_duration) {
        this.flow_duration = flow_duration;
    }

    public void setFlow_id(String flow_id) {
        this.flow_id = flow_id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getFlow_duration() {
        return flow_duration;
    }

    public String getFlow_id() {
        return flow_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public TrafficFlow(String flow_id, String timestamp, int flow_duration) {
        setFlow_duration(flow_duration);
        setFlow_id(flow_id);
        setTimestamp(timestamp);
    }

    @Override
    public int compareTo(TrafficFlow o) {
        return Integer.compare(this.getFlow_duration(), o.getFlow_duration());
    }

    @Override
    public String toString() {
        return getFlow_id() + " | " + getTimestamp() + " | " + getFlow_duration();
    }
}
