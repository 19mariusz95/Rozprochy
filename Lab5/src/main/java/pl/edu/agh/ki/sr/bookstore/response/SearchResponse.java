package pl.edu.agh.ki.sr.bookstore.response;

import java.io.Serializable;

public class SearchResponse implements Serializable {
    private final boolean found;
    private final String infoLine;

    public SearchResponse(boolean found, String infoLine) {
        this.found = found;
        this.infoLine = infoLine;
    }

    public String getMessage() {
        if(found) {
            return infoLine;
        }
        return infoLine + " not found";
    }

    public boolean isFound() {
        return found;
    }

    public String getInfoLine() {
        return infoLine;
    }
}
