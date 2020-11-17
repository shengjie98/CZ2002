package stars.entity;

/**
 * This interface allows objects that implement it to display their information
 */
public interface Selectable {

    /**
     * Retrieves student information
     * 
     * @return String This formatted string containing information of the Selectable
     *         to be printed
     */
    public String print();
}
