package movement;

import java.util.Objects;

public class DebugCustom {
    public String currentSearch;
    public String prev = "";
    public DebugCustom(){}
    public void setCurrentSearch(String currentSearch) {
        prev =this.currentSearch;
        this.currentSearch = currentSearch;
    }
    public String getCurrentSearch() {
        return currentSearch;
    }

    public void print(){
        if (!Objects.equals(prev, currentSearch)){
            System.out.println("log "+currentSearch);
        }
    }
}
