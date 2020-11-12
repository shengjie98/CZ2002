package stars.entity;

import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

public interface Database {
	public void loadInformation();
	public void saveInformation();
	public void addItem(Object o);
	public ArrayList getList();
}
