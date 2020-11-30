package auxiliarylists;

import auxiliaryentities.Serial;
import fileio.SerialInputData;

import java.util.ArrayList;
import java.util.List;

public class SerialList {
    private ArrayList<Serial> list = new ArrayList<>();

    public SerialList() {
    }

    /**
     * Method toString to ensure prints
     * @return A string with the elements of the entity
     */
    @Override
    public String toString() {
        return "SerialList{" + "list=" + list + '}';
    }

    /**
     * Function that gets the title of a certain element in the list
     * @param i Index of the element
     * @return The title
     */
    public String getTitle(final int i) {
        return list.get(i).getTitle();
    }

    /**
     * Function that returns the size of the list
     * @return Number of elements in list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Function that grabs a serial from the list by its title
     * @param title Title to be found
     * @return Serial entity with the given title
     */
    public Serial getSerialbyTitle(final String title) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(title)) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * Getter for the serial at the index i
     * @param i Index
     * @return Element of the list that resides at the index i
     */
    public Serial getSerial(final int i) {
        return list.get(i);
    }

    /**
     * Making a copy of the serials given in the input to assure that we avoid corrupting the
     * the input, while also initialising the list for the serials
     * @param a Serials given in input
     */
    public void getInputSerials(final List<SerialInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i++) {
                Serial serial = new Serial(a.get(i).getTitle(),  a.get(i).getCast(),
                        a.get(i).getGenres(), a.get(i).getNumberSeason(), a.get(i).getSeasons(),
                        a.get(i).getYear());
                list.add(serial);
            }
        }
    }

    /**
     * Getter for list of serials
     * @return The list of serials
     */
    public ArrayList<Serial> getList() {
        return list;
    }

    /**
     * Setter for the list of serials
     * @param list List to be set
     */
    public void setList(final ArrayList<Serial> list) {
        this.list = list;
    }
}
