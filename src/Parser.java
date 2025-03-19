import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public static TrafficFlow[] read(String filePath, int maxLines) {
        TrafficFlow[] records = new TrafficFlow[maxLines];
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            int linesRead = 0;
            while ((line = br.readLine()) != null && linesRead < maxLines) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String timeStamp = parts[1].trim();
                    int flowDuration = Integer.parseInt(parts[2].trim());
                    records[linesRead++] = new TrafficFlow(id, timeStamp, flowDuration);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // for (TrafficFlow t : records) {
        //     System.out.println(t);
        // }
        return records;
    }

}
