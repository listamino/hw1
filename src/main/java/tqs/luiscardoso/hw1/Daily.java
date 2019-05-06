package tqs.luiscardoso.hw1;

import java.util.List;

public class Daily {
    private String summary;
    private List<Weather> data;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Weather> getData() {
        return data;
    }

    public void setData(List<Weather> data) {
        this.data = data;
    }
}
