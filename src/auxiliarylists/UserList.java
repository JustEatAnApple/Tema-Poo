package auxiliarylists;

import auxiliaryentities.User;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private ArrayList<User> list = new ArrayList<>();

    public UserList() {
    }

    /**
     * Getter for the list of users
     * @return List of users
     */
    public ArrayList<User> getList() {
        return list;
    }

    /**
     * Setter for the list of users
     * @param list List to be set
     */
    public void setList(final ArrayList<User> list) {
        this.list = list;
    }

    /**
     * Getter for the username of the entity found at the index i
     * @param i Index
     * @return The username of the user found at the i position
     */
    public String getUsername(final int i) {
        return list.get(i).getUsername();
    }

    /**
     * Getter for the size of the list of users
     * @return Number of elements in list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Getter for the user found at the index position
     * @param i Index
     * @return The user entity found at the index
     */
    public User getUser(final int i) {
        return list.get(i);
    }

    /**
     * Method toString to ensure prints
     * @return A string with the elements of the entity
     */
    @Override
    public String toString() {
        return "UserList{" + "list=" + list + '}';
    }

    /**
     * Making a copy of the users given in the input to avoid the corruption of the input
     * while also initialising the list for the users
     * @param a Users given in input
     */
    public void getInputUsers(final List<UserInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i++) {
                User user = new User(a.get(i).getUsername(),  a.get(i).getSubscriptionType(),
                        a.get(i).getHistory(), a.get(i).getFavoriteMovies());
                list.add(user);
            }
        }
    }
}
