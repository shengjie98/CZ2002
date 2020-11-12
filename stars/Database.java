package stars;

import java.util.ArrayList;

public interface Database {
	public void loadInformation();
	public void saveInformation();
	public void addItem(Object o);
	public ArrayList getList();
}
