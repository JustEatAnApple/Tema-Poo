package main;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private ArrayList<User> list = new ArrayList<>();

    public UserList(){}

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public String getUsername(int i) {
        return list.get(i).getUsername();
    }

    public int getSize() {
        return list.size();
    }

    public User getUser(int i) {
        return list.get(i);
    }

    @Override
    public String toString() {
        return "UserList{" +
                "list=" + list +
                '}';
    }

    public void getInputUsers(List<UserInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i ++) {
                User user = new User(a.get(i).getUsername(),  a.get(i).getSubscriptionType(),
                        a.get(i).getHistory(), a.get(i).getFavoriteMovies());
                list.add(user);
            }
        }
    }
}
