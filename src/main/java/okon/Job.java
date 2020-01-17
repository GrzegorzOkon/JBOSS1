package okon;

import java.util.List;

public class Job {
    private final List<String> results;

    public Job(List <String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }
}
