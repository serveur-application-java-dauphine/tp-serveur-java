package fr.dauphine.etrade.Constantes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

public class TestBean {
	  private List<Data> myDataList = new ArrayList<Data>();
	  public TestBean() {
	    myDataList.add(new Data("id 1", "val 1"));
	    myDataList.add(new Data("id 2", "val 2"));
	  }
	  public List<Data> getMyDataList() {
	    return myDataList;
	  }
	  public void setMyDataList(List<Data> myDataList) {
	    this.myDataList = myDataList;
	  }
	  public void process(ActionEvent event) {
	    for (Data data : myDataList) {
	      System.out.println("value1=" + data.getValue1() + ", value2=" + data.getValue2());
	    }
	  }
}