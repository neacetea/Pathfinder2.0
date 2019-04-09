package sample;

import java.util.Comparator;

public class SortElementByAStarCost implements Comparator<Element> {

    public int compare(Element e1,Element e2){
        if(e1.getFCost() > e2.getFCost()){
            return 1;
        }
        else if(e1.getFCost() < e2.getFCost()){
            return -1;
        }
        else{
            return 0;
        }
    }
}
